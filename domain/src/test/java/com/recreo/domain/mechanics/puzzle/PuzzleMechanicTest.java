package com.recreo.domain.mechanics.puzzle;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.impl.MotPuzzleGenerator;
import com.recreo.domain.content.impl.MotPuzzleGenerator.Mot;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SubmitArrangement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * La mecanique "puzzle" (7e mecanique, cf. ADR-0017) delegue au moteur de session PARTAGE
 * (comme {@code GlisserDeposerMechanic}) : elle demarre une session valide, expose son
 * identite/vue propres, REUTILISE le coup {@code SubmitArrangement}, score +10 et avance sur
 * un arrangement correct (les lettres du mot dans l'ordre), et applique la regle des jokers
 * (DEC-0009) sur un ordre faux (retente, un coeur en moins, meme item).
 */
class PuzzleMechanicTest {

    private static final PuzzleMechanic MECHANIC = new PuzzleMechanic();

    private static GameConfig config() {
        ContentGenerator generator = new MotPuzzleGenerator(
                new ContentDescriptor("Français", "CP", "lecture"),
                "Remets les lettres dans l'ordre",
                List.of(new Mot("chat", "🐱"), new Mot("lune", "🌙")));
        return new GameConfig(PuzzleMechanic.ID, generator, Map.of());
    }

    /** Ordre correct (bonne reponse) : les lettres du mot dans l'ordre, lues depuis {@code expected}. */
    private static List<String> correctOrder(GameSession session) {
        return new ArrayList<>(List.of(session.currentItem().expected().value().split(", ")));
    }

    @Test
    void exposesItsOwnIdAndView() {
        assertThat(MECHANIC.id()).isEqualTo(PuzzleMechanic.ID);
        assertThat(MECHANIC.id().value()).isEqualTo("puzzle");
        assertThat(MECHANIC.view()).isEqualTo("puzzle");
    }

    @Test
    void startsAValidSessionWithArrangementContent() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(5L));

        assertThat(session.mechanic()).isEqualTo(PuzzleMechanic.ID);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.score()).isZero();
        // Contenu "arrangement" : des tuiles-lettres a ranger.
        assertThat(session.currentItem().reelSymbols()).isNotEmpty();
    }

    @Test
    void correctArrangementScoresAndAdvancesViaSharedEngine() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        List<String> order = correctOrder(session);

        MoveResult result = MECHANIC.apply(session, new SubmitArrangement(order));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().round()).isEqualTo(2);
        assertThat(result.session().masteredKeys()).contains(session.currentItem().key());
    }

    @Test
    void wrongArrangementConsumesAJokerAndRetriesTheSameItem() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        // Ordre surement faux : l'inverse de l'ordre correct (mots a lettres distinctes).
        List<String> wrong = correctOrder(session);
        Collections.reverse(wrong);

        MoveResult result = MECHANIC.apply(session, new SubmitArrangement(wrong));

        assertThat(result.correct()).isFalse();
        assertThat(result.session().score()).isZero();
        // UX "ranger" (DEC-0008) : on reste sur le MEME item (meme round) pour reessayer.
        assertThat(result.session().round()).isEqualTo(1);
        assertThat(result.session().currentItem().key()).isEqualTo(session.currentItem().key());
        assertThat(result.session().status()).isEqualTo(SessionStatus.ACTIVE);
        // Regle des jokers (DEC-0009) : un essai faux consomme un coeur (defaut 3 -> 2).
        assertThat(result.session().jokers()).isEqualTo(GameSession.DEFAULT_JOKERS - 1);
    }
}
