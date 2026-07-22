package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SpinAndPick;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Mode session (cf. ADR-0006) : sur une session complete, aucun item reussi n'est
 * repropose ; un item rate peut revenir ; la session se termine a la cible ET a
 * l'epuisement du vivier ; le score final est coherent. Utilise un generateur de
 * test FINI et DETERMINISTE (renvoie toujours le premier item non exclu) pour des
 * assertions exactes, sans coupler le test a un contenu concret reel.
 */
class BanditManchotSessionTest {

    private static final BanditManchotMechanic MECHANIC = new BanditManchotMechanic();
    private static final RandomSource RANDOM = new SeededRandomSource(1L);

    /**
     * Generateur fini deterministe : items {@code q0..q(size-1)}, reponse correcte
     * {@code R{i}}. Renvoie toujours le PREMIER item non exclu (ordre stable) et
     * {@code empty} quand tout est exclu (epuisement).
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

    private static GameConfig config(ContentGenerator generator, int target) {
        return new GameConfig(BanditManchotMechanic.ID, generator, Map.of("questionsTarget", target));
    }

    private static GameSession answerCorrectly(GameSession session) {
        MoveResult result = MECHANIC.apply(session, new SpinAndPick(session.currentItem().expected()));
        return result.session();
    }

    @Test
    void aFullyCorrectSessionNeverRepeatsAMasteredItemAndEndsAtTarget() {
        int target = 5;
        GameSession session = MECHANIC.start(config(new FiniteTestGenerator(20), target), RANDOM);
        assertThat(session.questionsTarget()).isEqualTo(target);

        List<String> playedKeys = new ArrayList<>();
        while (session.status() == SessionStatus.ACTIVE) {
            playedKeys.add(session.currentItem().key());
            session = answerCorrectly(session);
        }

        // Termine a la cible : exactement `target` questions repondues.
        assertThat(session.status()).isEqualTo(SessionStatus.FINISHED);
        assertThat(session.answered()).isEqualTo(target);
        // Aucun item reussi n'a ete repropose : toutes les cles jouees sont distinctes.
        assertThat(playedKeys).doesNotHaveDuplicates().hasSize(target);
        // Score coherent : 10 points par bonne reponse ; mastered = 1 cle par reussite.
        assertThat(session.score()).isEqualTo(10 * target);
        assertThat(session.masteredKeys()).hasSize(target);
    }

    @Test
    void aFailedItemStaysUntilMasteredThenNeverComesBack() {
        GameSession session = MECHANIC.start(config(new FiniteTestGenerator(3), 10), RANDOM);
        String firstKey = session.currentItem().key();

        // Mauvaise reponse (DEC-0009) : l'item n'est PAS maitrise -> il n'est pas encore
        // acquis ; on RETENTE le meme item (session ACTIVE, un joker consomme).
        MoveResult wrong = MECHANIC.apply(session, new SpinAndPick(new Answer("jamais-correct")));
        GameSession afterWrong = wrong.session();
        assertThat(wrong.correct()).isFalse();
        assertThat(afterWrong.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(afterWrong.masteredKeys()).doesNotContain(firstKey);
        assertThat(afterWrong.currentItem().key()).isEqualTo(firstKey); // meme item a retenter
        assertThat(afterWrong.jokers()).isEqualTo(session.jokers() - 1);

        // Bonne reponse sur ce meme item : desormais maitrise -> ne reviendra plus.
        GameSession afterCorrect = answerCorrectly(afterWrong);
        assertThat(afterCorrect.masteredKeys()).contains(firstKey);
        assertThat(afterCorrect.currentItem().key()).isNotEqualTo(firstKey);
    }

    @Test
    void sessionEndsWhenTheDistinctPoolIsExhaustedBeforeTarget() {
        int poolSize = 3;
        int target = 10; // superieur au vivier
        GameSession session = MECHANIC.start(config(new FiniteTestGenerator(poolSize), target), RANDOM);

        while (session.status() == SessionStatus.ACTIVE) {
            session = answerCorrectly(session);
        }

        // Vivier epuise avant la cible : termine a `poolSize`, pas a `target`.
        assertThat(session.status()).isEqualTo(SessionStatus.FINISHED);
        assertThat(session.answered()).isEqualTo(poolSize);
        assertThat(session.answered()).isLessThan(target);
        assertThat(session.score()).isEqualTo(10 * poolSize);
    }
}
