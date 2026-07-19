package com.recreo.domain.mechanics.texteatrou;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.ConjugaisonCm1Generator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SpinAndPick;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * La mecanique "texte a trous" delegue au moteur de session PARTAGE (cf. ADR-0007) :
 * elle demarre une session valide, expose la bonne reponse parmi les options, score
 * +10 et avance sur un coup correct — exactement comme le bandit manchot, avec une
 * VUE distincte. Un contenu "texte a trous" a bien {@code reelSymbols} vide et une
 * consigne contenant le trou.
 */
class TexteATrouMechanicTest {

    private static final TexteATrouMechanic MECHANIC = new TexteATrouMechanic();

    private static GameConfig config() {
        ContentGenerator generator = new ConjugaisonCm1Generator(
                new ContentDescriptor("Français", "CM1", "conjugaison"));
        return new GameConfig(TexteATrouMechanic.ID, generator, Map.of());
    }

    @Test
    void exposesItsOwnIdAndView() {
        assertThat(MECHANIC.id()).isEqualTo(TexteATrouMechanic.ID);
        assertThat(MECHANIC.id().value()).isEqualTo("texte-a-trou");
        assertThat(MECHANIC.view()).isEqualTo("texteatrou");
    }

    @Test
    void startsAValidSessionWithHoleContent() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(5L));

        assertThat(session.mechanic()).isEqualTo(TexteATrouMechanic.ID);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.score()).isZero();
        // Contenu "texte a trous" : pas de rouleaux, consigne = phrase avec le trou.
        assertThat(session.currentItem().reelSymbols()).isEmpty();
        assertThat(session.currentItem().question()).contains("___");
        // Options = bonne reponse + distracteurs (melangees par le moteur partage).
        assertThat(session.reels()).hasSize(1);
        assertThat(session.reels().get(0).symbols()).contains(session.currentItem().expected());
    }

    @Test
    void correctPickScoresAndAdvancesViaSharedEngine() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        Answer correct = session.currentItem().expected();

        MoveResult result = MECHANIC.apply(session, new SpinAndPick(correct));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().round()).isEqualTo(2);
        assertThat(result.session().masteredKeys()).contains(session.currentItem().key());
    }
}
