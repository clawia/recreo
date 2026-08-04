package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.AdditionsBorneesContentSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanic.SessionStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test de comportement exige par ADR-0008 : le nom exact de la classe est verifie par
 * ToutesLesMecaniquesSontTesteesTest. Graine fixe, sans navigateur.
 */
class BanditManchotTest {

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

    /** Toujours 0, quel que soit le tirage : deterministe pour le tirage et pour le melange. */
    private static final RandomSource TOUJOURS_ZERO = bound -> 0;

    private static ContentSource troisManches() {
        return new ListContentSource(DESCRIPTEUR, List.of(
                new Question(new ItemId("q1"), "un", "1", List.of("2", "3")),
                new Question(new ItemId("q2"), "deux", "2", List.of("3")),
                new Question(new ItemId("q3"), "trois", "3", List.of("1", "4"))));
    }

    private static BanditManchotSession demarrer(ContentSource contenu) {
        BanditManchot mecanique = new BanditManchot();
        GameSession session = mecanique.start(contenu, TOUJOURS_ZERO);
        return (BanditManchotSession) session;
    }

    private static BanditManchotSession jouer(BanditManchotSession session, String proposition) {
        BanditManchot mecanique = new BanditManchot();
        GameSession suivante = mecanique.apply(session, new BanditManchotMove(proposition));
        return (BanditManchotSession) suivante;
    }

    @Test
    void connaitSonIdentifiant() {
        assertThat(new BanditManchot().id()).isEqualTo(new MechanicId("bandit-manchot"));
    }

    @Test
    void laPremiereMancheEstTireeAuDemarrage() {
        BanditManchotSession session = demarrer(troisManches());

        assertThat(session.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(session.enonceManche()).contains("un");
        assertThat(session.propositions()).containsExactly("2", "3", "1");
        assertThat(session.score()).isEqualTo(new Score(0, 0));
        assertThat(session.dernierVerdict()).isEmpty();
    }

    @Test
    void unChoixCorrectEstJugeJusteEtLeScoreAvance() {
        BanditManchotSession premiere = demarrer(troisManches());

        BanditManchotSession suivante = jouer(premiere, "1");

        assertThat(suivante.dernierVerdict()).contains(new Verdict(true, "1"));
        assertThat(suivante.score()).isEqualTo(new Score(1, 1));
        assertThat(suivante.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(suivante.enonceManche()).contains("deux");
        assertThat(suivante.propositions()).containsExactly("3", "2");
    }

    @Test
    void unChoixIncorrectEstJugeFauxDesigneLaBonneReponseEtNAvancePasLeScore() {
        BanditManchotSession premiere = demarrer(troisManches());
        BanditManchotSession deuxieme = jouer(premiere, "1");

        BanditManchotSession troisieme = jouer(deuxieme, "3");

        assertThat(troisieme.dernierVerdict()).contains(new Verdict(false, "2"));
        assertThat(troisieme.score()).isEqualTo(new Score(1, 2));
        assertThat(troisieme.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(troisieme.enonceManche()).contains("trois");
        assertThat(troisieme.propositions()).containsExactly("1", "4", "3");
    }

    @Test
    void lEnchainementDesManchesConserveLeScoreCumule() {
        BanditManchotSession session = demarrer(troisManches());
        session = jouer(session, "1");
        session = jouer(session, "3");
        session = jouer(session, "3");

        assertThat(session.score()).isEqualTo(new Score(2, 3));
        assertThat(session.dernierVerdict()).contains(new Verdict(true, "3"));
    }

    @Test
    void laPartieSeTermineQuandLeVivierEstEpuise() {
        BanditManchotSession session = demarrer(troisManches());
        session = jouer(session, "1");
        session = jouer(session, "3");
        session = jouer(session, "3");

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.enonceManche()).isEmpty();
        assertThat(session.propositions()).isEmpty();
        assertThat(session.score()).isEqualTo(new Score(2, 3));
    }

    @Test
    void demarrerSurUnContenuVideTermineImmediatementLaPartie() {
        ContentSource vide = new ListContentSource(DESCRIPTEUR, List.of());

        BanditManchotSession session = demarrer(vide);

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.enonceManche()).isEmpty();
        assertThat(session.propositions()).isEmpty();
        assertThat(session.score()).isEqualTo(new Score(0, 0));
        assertThat(session.dernierVerdict()).isEmpty();
    }

    @Test
    void jouerUneMancheApresLaFinDeLaPartieEstRefuse() {
        BanditManchotSession session = demarrer(new ListContentSource(DESCRIPTEUR, List.of()));
        BanditManchot mecanique = new BanditManchot();

        assertThatThrownBy(() -> mecanique.apply(session, new BanditManchotMove("1")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void appliquerUnCoupSurUneSessionEtrangereEstRefuse() {
        BanditManchot mecanique = new BanditManchot();
        GameSession sessionEtrangere = new GameSession() {
            @Override
            public SessionStatus status() {
                return SessionStatus.EN_COURS;
            }

            @Override
            public Score score() {
                return new Score(0, 0);
            }
        };

        assertThatThrownBy(() -> mecanique.apply(sessionEtrangere, new BanditManchotMove("1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void appliquerUnCoupEtrangerEstRefuse() {
        BanditManchot mecanique = new BanditManchot();
        BanditManchotSession session = demarrer(troisManches());
        Move coupEtranger = new Move() { };

        assertThatThrownBy(() -> mecanique.apply(session, coupEtranger))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * S0-3 : preuve d'execution du decouplage, pas seulement structurelle (ArchUnit, cote
     * theo-archi). La meme instance de mecanique, sans aucun changement de code, joue un contenu
     * combinatoire de mathematiques CP et un contenu enumere d'une toute autre matiere et d'un
     * tout autre niveau.
     */
    @Test
    void laMemeMecaniqueJoueDeuxContenusDifferentsSansAucunChangementDeCode() {
        BanditManchot mecanique = new BanditManchot();

        ContentSource additionsMathsCP = new AdditionsBorneesContentSource(
                new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs",
                        "additions dans la limite de 10"),
                10, 3);
        ContentSource vocabulaireAnglaisCM1 = new ListContentSource(
                new ContentDescriptor("Anglais", "CM1", "Vocabulaire", "les couleurs"),
                List.of(new Question(new ItemId("v1"), "red", "rouge", List.of("bleu", "vert"))));

        BanditManchotSession sessionMaths = (BanditManchotSession) mecanique.start(additionsMathsCP, TOUJOURS_ZERO);
        assertThat(sessionMaths.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(sessionMaths.enonceManche()).isPresent();

        BanditManchotSession sessionAnglais = (BanditManchotSession) mecanique.start(vocabulaireAnglaisCM1, TOUJOURS_ZERO);
        assertThat(sessionAnglais.enonceManche()).contains("red");

        BanditManchotSession suivanteAnglais =
                (BanditManchotSession) mecanique.apply(sessionAnglais, new BanditManchotMove("rouge"));
        assertThat(suivanteAnglais.dernierVerdict()).contains(new Verdict(true, "rouge"));
        assertThat(suivanteAnglais.score()).isEqualTo(new Score(1, 1));
    }

    @Test
    void leTirageExcluBienLesManchesDejaVues() {
        BanditManchotSession session = demarrer(troisManches());
        Set<String> enonces = new java.util.LinkedHashSet<>();
        enonces.add(session.enonceManche().orElseThrow());
        for (int i = 0; i < 2; i++) {
            session = jouer(session, "peu importe");
            session.enonceManche().ifPresent(enonces::add);
        }

        assertThat(enonces).containsExactly("un", "deux", "trois");
    }
}
