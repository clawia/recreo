package com.recreo.domain.mechanic;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Regle des « 3 coeurs » / jokers (cf. DEC-0009) portee par le {@link SessionEngine}
 * PARTAGE, exercee ici a travers une mecanique concrete ({@link BanditManchotMechanic}).
 *
 * <p>Un coup FAUX ne fait plus avancer : tant qu'il reste un joker, on le CONSOMME et on
 * RETENTE le meme item (session inchangee, ACTIVE) ; a 0 joker, une erreur TERMINE la
 * session. Un coup CORRECT score +10, avance, remet {@code triedWrong} a vide et CONSERVE
 * le capital de jokers restant.</p>
 */
class SessionEngineJokerTest {

    private static final BanditManchotMechanic MECHANIC = new BanditManchotMechanic();

    /**
     * Generateur fini deterministe : items {@code q0..q(size-1)}, bonne reponse {@code R{i}},
     * renvoie toujours le PREMIER item non exclu (ordre stable), {@code empty} si tout exclu.
     */
    private static final class FiniteTestGenerator implements ContentGenerator {
        private final List<Item> pool = new ArrayList<>();

        FiniteTestGenerator(int size) {
            for (int i = 0; i < size; i++) {
                pool.add(new Item(
                        new ItemId("id" + i),
                        List.of("q" + i),
                        "Question ?",
                        new Answer("R" + i),
                        List.of(new Answer("X"), new Answer("Y")),
                        Map.of()));
            }
        }

        @Override
        public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
            for (Item item : pool) {
                if (!excludeKeys.contains(item.key())) {
                    return Optional.of(item);
                }
            }
            return Optional.empty();
        }

        @Override
        public OptionalInt distinctPoolSize() {
            return OptionalInt.of(pool.size());
        }

        @Override
        public ContentDescriptor descriptor() {
            return new ContentDescriptor("Test", "CP", "test");
        }
    }

    private static GameConfig config() {
        // Cible large : on veut observer l'avance sur un coup correct, pas la fin sur cible.
        return new GameConfig(BanditManchotMechanic.ID, new FiniteTestGenerator(10), Map.of("questionsTarget", 10));
    }

    private static GameSession start() {
        return MECHANIC.start(config(), new SeededRandomSource(1L));
    }

    private static final Answer WRONG = new Answer("jamais-correct");

    @Test
    void aWrongPickWithJokersLeftConsumesAJokerAndRetriesTheSameItem() {
        GameSession session = start();
        assertThat(session.jokers()).isEqualTo(GameSession.DEFAULT_JOKERS); // 3 coeurs au depart

        MoveResult result = MECHANIC.apply(session, new SpinAndPick(WRONG));
        GameSession after = result.session();

        assertThat(result.correct()).isFalse();
        // Session INCHANGEE hormis le joker et l'option grisee.
        assertThat(after.jokers()).isEqualTo(GameSession.DEFAULT_JOKERS - 1); // 3 -> 2
        assertThat(after.currentItem().key()).isEqualTo(session.currentItem().key());
        assertThat(after.score()).isEqualTo(session.score());
        assertThat(after.answered()).isEqualTo(session.answered());
        assertThat(after.round()).isEqualTo(session.round());
        assertThat(after.status()).isEqualTo(SessionStatus.ACTIVE);
        // L'option cliquee a tort est memorisee (grisee a la retente).
        assertThat(after.triedWrong()).contains(WRONG.value());
    }

    @Test
    void threeWrongPicksStayActiveThenTheFourthEndsTheSession() {
        GameSession session = start();

        // 1er faux : 3 -> 2, ACTIVE.
        session = MECHANIC.apply(session, new SpinAndPick(WRONG)).session();
        assertThat(session.jokers()).isEqualTo(2);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);

        // 2e faux : 2 -> 1, ACTIVE.
        session = MECHANIC.apply(session, new SpinAndPick(WRONG)).session();
        assertThat(session.jokers()).isEqualTo(1);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);

        // 3e faux : 1 -> 0, ENCORE ACTIVE (dernier coeur consomme).
        session = MECHANIC.apply(session, new SpinAndPick(WRONG)).session();
        assertThat(session.jokers()).isZero();
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);

        // 4e faux a 0 joker : la partie est terminee.
        int answeredBefore = session.answered();
        MoveResult fourth = MECHANIC.apply(session, new SpinAndPick(WRONG));
        assertThat(fourth.correct()).isFalse();
        assertThat(fourth.session().status()).isEqualTo(SessionStatus.FINISHED);
        // Correctif score max (s18) : la question EN COURS (ratee) est comptee dans le total
        // joue, pour que l'ecran de fin affiche (repondues + 1) x 10 comme score max.
        assertThat(fourth.session().answered()).isEqualTo(answeredBefore + 1);
    }

    @Test
    void aCorrectPickAfterWrongOnesScoresAdvancesResetsTriedWrongAndKeepsJokers() {
        GameSession session = start();
        String firstKey = session.currentItem().key();

        // Un faux d'abord : consomme un joker, l'item est memorise dans triedWrong.
        session = MECHANIC.apply(session, new SpinAndPick(WRONG)).session();
        assertThat(session.jokers()).isEqualTo(2);
        assertThat(session.triedWrong()).contains(WRONG.value());

        // Puis la bonne reponse du MEME item.
        MoveResult correct = MECHANIC.apply(session, new SpinAndPick(session.currentItem().expected()));
        GameSession after = correct.session();

        assertThat(correct.correct()).isTrue();
        assertThat(after.score()).isEqualTo(10);
        assertThat(after.answered()).isEqualTo(1);
        // Avance a l'item suivant : nouveau round, nouvel item, item precedent maitrise.
        assertThat(after.round()).isEqualTo(2);
        assertThat(after.currentItem().key()).isNotEqualTo(firstKey);
        assertThat(after.masteredKeys()).contains(firstKey);
        // Nouvel item : plus aucune option grisee.
        assertThat(after.triedWrong()).isEmpty();
        // Le capital de jokers est CONSERVE (pas de reset au nouvel item).
        assertThat(after.jokers()).isEqualTo(2);
    }
}
