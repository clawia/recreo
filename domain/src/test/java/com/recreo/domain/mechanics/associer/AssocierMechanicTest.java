package com.recreo.domain.mechanics.associer;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.impl.AssociationGenerator;
import com.recreo.domain.content.impl.AssociationGenerator.Association;
import com.recreo.domain.content.impl.AssociationGenerator.Puzzle;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SubmitAssociations;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * La mecanique "associer / classer" (cf. ADR-0016) delegue au moteur de session PARTAGE
 * (comme {@code GlisserDeposerMechanic}) : elle demarre une session valide, expose son
 * identite/vue propres, score +10 sur un appariement correct et applique la regle des
 * jokers (DEC-0009) sur un appariement faux (retente le meme item, un coeur consomme).
 */
class AssocierMechanicTest {

    private static final AssocierMechanic MECHANIC = new AssocierMechanic();

    private static GameConfig config() {
        ContentGenerator generator = new AssociationGenerator(
                new ContentDescriptor("Sciences", "CP", "classer les animaux"),
                "Range chaque animal dans sa famille",
                List.of(new Puzzle(List.of(
                        new Association("chien", "mammifère"),
                        new Association("aigle", "oiseau")))));
        return new GameConfig(AssocierMechanic.ID, generator, Map.of());
    }

    /** L'appariement attendu (item -> cible) du contenu ci-dessus : la bonne reponse. */
    private static Map<String, String> correctAssociations() {
        return Map.of("chien", "mammifère", "aigle", "oiseau");
    }

    @Test
    void exposesItsOwnIdAndView() {
        assertThat(MECHANIC.id()).isEqualTo(AssocierMechanic.ID);
        assertThat(MECHANIC.id().value()).isEqualTo("associer");
        assertThat(MECHANIC.view()).isEqualTo("associer");
    }

    @Test
    void startsAValidSessionWithAssociationContent() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(5L));

        assertThat(session.mechanic()).isEqualTo(AssocierMechanic.ID);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.score()).isZero();
        // Contenu "associer" : les elements a ranger.
        assertThat(session.currentItem().reelSymbols()).containsExactly("chien", "aigle");
    }

    @Test
    void correctAssociationsScoreAndAdvanceViaSharedEngine() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));

        MoveResult result = MECHANIC.apply(session, new SubmitAssociations(correctAssociations()));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().masteredKeys()).contains(session.currentItem().key());
    }

    @Test
    void wrongAssociationsConsumeAJokerAndRetryTheSameItem() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        // Appariement faux : on inverse les cibles.
        Map<String, String> wrong = Map.of("chien", "oiseau", "aigle", "mammifère");

        MoveResult result = MECHANIC.apply(session, new SubmitAssociations(wrong));

        assertThat(result.correct()).isFalse();
        assertThat(result.session().score()).isZero();
        // Regle des jokers (DEC-0009) : on reste sur le MEME item, ACTIVE, un coeur en moins.
        assertThat(result.session().status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(result.session().currentItem().key()).isEqualTo(session.currentItem().key());
        assertThat(result.session().jokers()).isEqualTo(GameSession.DEFAULT_JOKERS - 1);
    }
}
