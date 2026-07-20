package com.recreo.domain.mechanics.glisserdeposer;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.impl.RangeNombresGenerator;
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
 * La mecanique "glisser-deposer" delegue au moteur de session PARTAGE (cf. ADR-0007) :
 * elle demarre une session valide, affiche des tuiles a ranger, score +10 et avance sur
 * un arrangement correct — exactement comme les autres mecaniques, avec une VUE
 * distincte. Le contenu "arrangement" a bien {@code reelSymbols} non vide (les tuiles) et
 * un {@code expected} = l'ordre croissant encode.
 */
class GlisserDeposerMechanicTest {

    private static final GlisserDeposerMechanic MECHANIC = new GlisserDeposerMechanic();

    private static GameConfig config() {
        ContentGenerator generator = new RangeNombresGenerator(
                new ContentDescriptor("Mathématiques", "CP", "ordre"));
        return new GameConfig(GlisserDeposerMechanic.ID, generator, Map.of());
    }

    /** Ordre croissant (bonne reponse) : les tuiles triees numeriquement. */
    private static List<String> sortedOrder(GameSession session) {
        List<String> order = new ArrayList<>(session.currentItem().reelSymbols());
        order.sort((a, b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)));
        return order;
    }

    @Test
    void exposesItsOwnIdAndView() {
        assertThat(MECHANIC.id()).isEqualTo(GlisserDeposerMechanic.ID);
        assertThat(MECHANIC.id().value()).isEqualTo("glisser-deposer");
        assertThat(MECHANIC.view()).isEqualTo("glisserdeposer");
    }

    @Test
    void startsAValidSessionWithArrangementContent() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(5L));

        assertThat(session.mechanic()).isEqualTo(GlisserDeposerMechanic.ID);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.score()).isZero();
        // Contenu "arrangement" : des tuiles a ranger.
        assertThat(session.currentItem().reelSymbols()).isNotEmpty();
    }

    @Test
    void correctArrangementScoresAndAdvancesViaSharedEngine() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        List<String> order = sortedOrder(session);

        MoveResult result = MECHANIC.apply(session, new SubmitArrangement(order));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().round()).isEqualTo(2);
        assertThat(result.session().masteredKeys()).contains(session.currentItem().key());
    }

    @Test
    void wrongArrangementDoesNotScore() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        // Ordre surement faux : l'inverse de l'ordre croissant.
        List<String> wrong = sortedOrder(session);
        Collections.reverse(wrong);

        MoveResult result = MECHANIC.apply(session, new SubmitArrangement(wrong));

        assertThat(result.correct()).isFalse();
        assertThat(result.session().score()).isZero();
        // UX "ranger" (DEC-0008) : un arrangement faux ne fait PAS avancer -> on reste sur
        // le MEME item (meme round), pour reessayer apres remise a l'etat initial.
        assertThat(result.session().round()).isEqualTo(1);
        assertThat(result.session().currentItem().key()).isEqualTo(session.currentItem().key());
        assertThat(result.session().status()).isEqualTo(SessionStatus.ACTIVE);
    }
}
