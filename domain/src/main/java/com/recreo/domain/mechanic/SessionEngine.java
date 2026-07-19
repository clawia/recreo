package com.recreo.domain.mechanic;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Moteur de session PARTAGE par toutes les mecaniques (cf. ADR-0007). Il porte la
 * logique commune du mode session : demarrage (tirage du 1er item + construction
 * des options melangees), scoring (+10 si correct), comptage des reponses, marquage
 * des items REUSSIS (par {@link Item#key()}) pour ne plus les reproposer, tirage de
 * l'item suivant en excluant les cles maitrisees, et fin de session a la cible OU a
 * l'epuisement du vivier (cf. ADR-0006).
 *
 * <p><b>Isolation des mecaniques</b> : ce moteur vit dans le paquet PARENT
 * {@code domain.mechanic}. Chaque mecanique concrete ({@code domain.mechanics.*}) y
 * delegue sa logique SANS jamais dependre d'une autre mecanique — la regle ArchUnit
 * "slices notDependOnEachOther" reste verte. Le moteur ne connait rien du contenu
 * concret : il ne consomme que {@link ContentGenerator}/{@link Item}, jamais un
 * generateur concret (regle ArchUnit 4 verte).</p>
 *
 * <p>La difference entre deux mecaniques n'est donc PAS dans le deroule d'une
 * session (identique), mais dans leur identite ({@link MechanicId}) et leur
 * presentation (fragment de vue) — decidees par la mecanique, pas par ce moteur.</p>
 */
public final class SessionEngine {

    private static final int POINTS_PER_CORRECT = 10;

    private final MechanicId mechanicId;

    public SessionEngine(MechanicId mechanicId) {
        this.mechanicId = mechanicId;
    }

    /** Demarre une session : tire le 1er item, construit les options, cible lue des reglages. */
    public GameSession start(GameConfig config, RandomSource random) {
        ContentGenerator generator = config.content();
        int questionsTarget = questionsTarget(config);
        // Premier item : aucun item maitrise a exclure. Un vivier non filtre n'est
        // jamais vide (delegation via next(random)).
        Item item = generator.next(random);
        List<Reel> reels = buildReels(item, random);
        SessionId sessionId = new SessionId(UUID.randomUUID().toString());
        return new GameSession(
                sessionId,
                mechanicId,
                null,
                item,
                reels,
                0,
                1,
                SessionStatus.ACTIVE,
                generator,
                random)
                .withQuestionsTarget(questionsTarget);
    }

    /** Applique un coup : score, comptage, maitrise, item suivant / fin de session. */
    public MoveResult apply(GameSession session, Move move) {
        ContentGenerator generator = session.content();
        RandomSource random = session.random();

        return switch (move) {
            case SpinAndPick pick -> {
                boolean correct = pick.chosen().equals(session.currentItem().expected());
                int newScore = session.score() + (correct ? POINTS_PER_CORRECT : 0);
                int answered = session.answered() + 1;
                // Un item REUSSI est marque maitrise (par sa cle) : il ne sera plus
                // repropose. Un item RATE n'entre pas dans mastered -> peut revenir.
                Set<String> mastered = session.masteredKeys();
                if (correct) {
                    mastered = new LinkedHashSet<>(mastered);
                    mastered.add(session.currentItem().key());
                }
                Feedback feedback = correct
                        ? new Feedback("Bravo ! +" + POINTS_PER_CORRECT + " points")
                        : new Feedback("Presque ! La bonne reponse etait "
                                + session.currentItem().expected().value());
                GameSession updated = advance(session, newScore, answered, mastered, generator, random);
                yield new MoveResult(updated, correct, feedback);
            }
            case Skip ignored -> {
                // Passer ne compte pas comme une reponse et ne maitrise rien.
                GameSession updated = advance(
                        session, session.score(), session.answered(), session.masteredKeys(), generator, random);
                yield new MoveResult(updated, false, new Feedback("Item passe."));
            }
        };
    }

    private GameSession advance(GameSession session, int newScore, int answered,
                                Set<String> mastered, ContentGenerator generator, RandomSource random) {
        GameSession base = session
                .withScore(newScore)
                .withAnswered(answered)
                .withMasteredKeys(mastered);

        // Cible atteinte : session terminee, pas d'item suivant.
        if (answered >= session.questionsTarget()) {
            return base.withStatus(SessionStatus.FINISHED);
        }

        // Tire l'item suivant en excluant les items maitrises (reussis).
        Optional<Item> next = generator.next(random, mastered);
        if (next.isEmpty()) {
            // Vivier distinct epuise : session terminee.
            return base.withStatus(SessionStatus.FINISHED);
        }
        Item item = next.get();
        return base
                .withRound(session.round() + 1)
                .withCurrentItem(item)
                .withReels(buildReels(item, random));
    }

    private static int questionsTarget(GameConfig config) {
        // La longueur de session est resolue et bornee dans le bootstrap (qui connait
        // le vivier), puis passee via GameConfig.settings. Le moteur ne lit qu'un
        // entier final ; defaut si absent.
        Object raw = config.settings().get("questionsTarget");
        return raw instanceof Integer target ? target : GameSession.DEFAULT_QUESTIONS_TARGET;
    }

    /**
     * Construit les options d'un item (bonne reponse + distracteurs melanges) dans un
     * unique {@link Reel}. Commun aux deux mecaniques : la donnee "options" est
     * identique ; seule leur PRESENTATION (machine a sous vs mots a cliquer) differe,
     * cote vue.
     */
    private List<Reel> buildReels(Item item, RandomSource random) {
        List<Answer> options = new ArrayList<>();
        options.add(item.expected());
        options.addAll(item.distractors());
        shuffle(options, random);
        return List.of(new Reel(List.copyOf(options)));
    }

    private void shuffle(List<Answer> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
    }
}
