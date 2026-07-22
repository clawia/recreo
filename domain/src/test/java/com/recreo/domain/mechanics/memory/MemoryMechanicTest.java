package com.recreo.domain.mechanics.memory;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.impl.MemoryGenerator;
import com.recreo.domain.content.impl.MemoryGenerator.Paire;
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
 * La mecanique "memory" (cf. ADR-0016) delegue au moteur de session PARTAGE (comme
 * {@code GlisserDeposerMechanic}) : elle demarre une session valide, expose son
 * identite/vue propres, score +10 quand toutes les paires du plateau sont appariees, et
 * applique la regle des jokers (DEC-0009) sur un appariement faux (retente, un coeur en moins).
 */
class MemoryMechanicTest {

    private static final MemoryMechanic MECHANIC = new MemoryMechanic();

    private static GameConfig config() {
        ContentGenerator generator = new MemoryGenerator(
                new ContentDescriptor("Anglais", "CP", "vocabulaire des animaux"),
                "Retrouve les paires",
                List.of(new Paire("chat", "cat"), new Paire("chien", "dog")));
        return new GameConfig(MemoryMechanic.ID, generator, Map.of());
    }

    /** L'appariement attendu (gauche -> droite) du plateau ci-dessus : la bonne reponse. */
    private static Map<String, String> correctPairs() {
        return Map.of("chat", "cat", "chien", "dog");
    }

    @Test
    void exposesItsOwnIdAndView() {
        assertThat(MECHANIC.id()).isEqualTo(MemoryMechanic.ID);
        assertThat(MECHANIC.id().value()).isEqualTo("memory");
        assertThat(MECHANIC.view()).isEqualTo("memory");
    }

    @Test
    void startsAValidSessionWithABoard() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(5L));

        assertThat(session.mechanic()).isEqualTo(MemoryMechanic.ID);
        assertThat(session.status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(session.round()).isEqualTo(1);
        assertThat(session.score()).isZero();
        // Contenu "memory" : les faces des cartes, dans l'ordre g0,d0,g1,d1.
        assertThat(session.currentItem().reelSymbols()).containsExactly("chat", "cat", "chien", "dog");
    }

    @Test
    void aCompletedBoardScoresViaSharedEngine() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));

        MoveResult result = MECHANIC.apply(session, new SubmitAssociations(correctPairs()));

        assertThat(result.correct()).isTrue();
        assertThat(result.session().score()).isEqualTo(10);
        assertThat(result.session().masteredKeys()).contains(session.currentItem().key());
    }

    @Test
    void wrongPairsConsumeAJokerAndRetryTheSameBoard() {
        GameSession session = MECHANIC.start(config(), new SeededRandomSource(7L));
        // Appariement faux : on croise les cotes droits.
        Map<String, String> wrong = Map.of("chat", "dog", "chien", "cat");

        MoveResult result = MECHANIC.apply(session, new SubmitAssociations(wrong));

        assertThat(result.correct()).isFalse();
        assertThat(result.session().score()).isZero();
        // Regle des jokers (DEC-0009) : meme plateau, ACTIVE, un coeur en moins.
        assertThat(result.session().status()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(result.session().currentItem().key()).isEqualTo(session.currentItem().key());
        assertThat(result.session().jokers()).isEqualTo(GameSession.DEFAULT_JOKERS - 1);
    }
}
