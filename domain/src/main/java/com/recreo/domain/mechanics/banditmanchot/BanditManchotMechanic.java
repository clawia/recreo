package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.Feedback;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.Reel;
import com.recreo.domain.mechanic.SessionId;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SpinAndPick;
import com.recreo.domain.mechanic.Skip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Bandit manchot : tire un {@link Item}, construit les rouleaux (bonne reponse +
 * distracteurs melanges), valide le choix et met a jour le score.
 *
 * <p>Ne reference JAMAIS {@code CalculMentalGenerator} ni le package
 * {@code content.impl} : elle consomme uniquement l'abstraction
 * {@link ContentGenerator} (garanti par ArchUnit).</p>
 */
public final class BanditManchotMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("bandit-manchot");

    private static final int POINTS_PER_CORRECT = 10;

    @Override
    public MechanicId id() {
        return ID;
    }

    @Override
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
                ID,
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

    @Override
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
        // le vivier), puis passee via GameConfig.settings. La mecanique ne lit qu'un
        // entier final ; defaut si absent.
        Object raw = config.settings().get("questionsTarget");
        return raw instanceof Integer target ? target : GameSession.DEFAULT_QUESTIONS_TARGET;
    }

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
