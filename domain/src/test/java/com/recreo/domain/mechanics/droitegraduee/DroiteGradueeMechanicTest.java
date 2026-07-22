package com.recreo.domain.mechanics.droitegraduee;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.DroiteGradueeGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SpinAndPick;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * La 8e mecanique « droite graduee » (cf. ADR-0018) delegue au moteur de session PARTAGE
 * (comme le bandit manchot) : elle demarre une session valide, expose son identite/vue
 * propres, REUTILISE le coup {@code SpinAndPick} (cliquer un tick = choisir sa valeur),
 * score +10 et avance sur la BONNE valeur (celle attendue), et applique la regle des jokers
 * (DEC-0009) sur une valeur fausse (retente, un coeur en moins, meme item).
 */
class DroiteGradueeMechanicTest {

    private static final DroiteGradueeMechanic MECHANIC = new DroiteGradueeMechanic();

    private static GameConfig config() {
        ContentGenerator generator = new DroiteGradueeGenerator(
                new ContentDescriptor("Mathématiques", "CP", "Nombres et calculs — situer un nombre"),
                0, 10);
        return new GameConfig(DroiteGradueeMechanic.ID, generator, Map.of());
    }

    @Test
    void exposesItsOwnIdAndView() {
        assertThat(MECHANIC.id()).isEqualTo(DroiteGradueeMechanic.ID);
        assertThat(MECHANIC.id().value()).isEqualTo("droite-graduee");
        assertThat(MECHANIC.view()).isEqualTo("droitegraduee");
    }

    @Test
    void startsAValidSession() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(5L));

        assertThat(session.mechanic()).isEqualTo(DroiteGradueeMechanic.ID);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.score()).isZero();
        assertThat(session.currentItem()).isNotNull();
    }

    @Test
    void correctPlacementScoresAndAdvancesViaSharedEngine() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        // La bonne valeur = le nombre a placer (expected), comme cliquer la bonne graduation.
        Answer correct = session.currentItem().expected();

        MoveResult result = MECHANIC.apply(session, new SpinAndPick(correct));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().round()).isEqualTo(2);
        assertThat(result.session().masteredKeys()).contains(session.currentItem().key());
    }

    @Test
    void wrongPlacementConsumesAJokerAndRetriesTheSameItem() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        // Valeur surement fausse (hors droite) : ne peut correspondre a aucune graduation.
        Answer wrong = new Answer("this-is-never-correct");

        MoveResult result = MECHANIC.apply(session, new SpinAndPick(wrong));

        assertThat(result.correct()).isFalse();
        assertThat(result.session().score()).isZero();
        // On reste sur le MEME item, meme round : pas d'avance sur un coup faux.
        assertThat(result.session().round()).isEqualTo(1);
        assertThat(result.session().currentItem().key()).isEqualTo(session.currentItem().key());
        assertThat(result.session().status()).isEqualTo(SessionStatus.ACTIVE);
        // Regle des jokers (DEC-0009) : un essai faux consomme un coeur (defaut 3 -> 2).
        assertThat(result.session().jokers()).isEqualTo(GameSession.DEFAULT_JOKERS - 1);
    }
}
