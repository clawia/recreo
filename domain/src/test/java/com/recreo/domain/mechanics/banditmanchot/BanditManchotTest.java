package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
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
import java.util.Optional;
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

    /** Assez grand pour qu'aucun test existant ne soit borne par lui : seul le vivier s'epuise. */
    private static final int MANCHES_LARGEMENT_SUFFISANTES = 100;

    private static ContentSource troisManches() {
        return new ListContentSource(DESCRIPTEUR, List.of(
                new Question(new ItemId("q1"), "un", "1", List.of("2", "3")),
                new Question(new ItemId("q2"), "deux", "2", List.of("3")),
                new Question(new ItemId("q3"), "trois", "3", List.of("1", "4"))));
    }

    /** Contenu combinatoire, jamais epuise (ADR-0012) : ignore dejaVus, boucle sur les questions donnees. */
    private static ContentSource sequenceInfinie(Question... questions) {
        return new ContentSource() {
            private int position = 0;

            @Override
            public ContentDescriptor descriptor() {
                return DESCRIPTEUR;
            }

            @Override
            public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
                Question question = questions[position % questions.length];
                position++;
                return Optional.of(question);
            }
        };
    }

    private static BanditManchotSession demarrer(ContentSource contenu) {
        return demarrer(contenu, MANCHES_LARGEMENT_SUFFISANTES);
    }

    private static BanditManchotSession demarrer(ContentSource contenu, int nombreDeManches) {
        BanditManchot mecanique = new BanditManchot();
        GameSession session = mecanique.start(contenu, TOUJOURS_ZERO, nombreDeManches);
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

    /**
     * S3-2, ADR-0013 : la mecanique publie la longueur minimale en dessous de laquelle son aide
     * (manche plus simple, indice) ne peut jamais s'afficher. CatalogueChargeableIT compare cette
     * valeur a nombreDeManches pour chaque jeu du catalogue reel plutot que de l'ecrire en dur.
     */
    @Test
    void publieSaLongueurMinimalePourQueLAideResteAtteignable() {
        assertThat(new BanditManchot().longueurMinimale()).isEqualTo(4);
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
                10, 3, "Compte sur tes doigts.");
        ContentSource vocabulaireAnglaisCM1 = new ListContentSource(
                new ContentDescriptor("Anglais", "CM1", "Vocabulaire", "les couleurs"),
                List.of(new Question(new ItemId("v1"), "red", "rouge", List.of("bleu", "vert"))));

        BanditManchotSession sessionMaths = (BanditManchotSession)
                mecanique.start(additionsMathsCP, TOUJOURS_ZERO, MANCHES_LARGEMENT_SUFFISANTES);
        assertThat(sessionMaths.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(sessionMaths.enonceManche()).isPresent();

        BanditManchotSession sessionAnglais = (BanditManchotSession)
                mecanique.start(vocabulaireAnglaisCM1, TOUJOURS_ZERO, MANCHES_LARGEMENT_SUFFISANTES);
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

    /**
     * ADR-0012 : la mecanique compte les manches jouees, independamment de ce que rend le
     * contenu. AdditionsBorneesContentSource ne renvoie jamais Optional.empty() (Javadoc citee par
     * Nadia) : seul nombreDeManches peut ici mettre fin a la partie.
     */
    @Test
    void laPartieSeTermineApresLeNombreDeManchesMemeAvecUnContenuCombinatoireInfini() {
        ContentSource combinatoire = new AdditionsBorneesContentSource(DESCRIPTEUR, 10, 3, "Compte sur tes doigts.");
        BanditManchotSession session = demarrer(combinatoire, 10);

        for (int reponse = 1; reponse <= 9; reponse++) {
            assertThat(session.status()).as("avant la reponse %d sur 10", reponse).isEqualTo(SessionStatus.EN_COURS);
            session = jouer(session, "pas la bonne reponse");
        }

        assertThat(session.status()).isEqualTo(SessionStatus.EN_COURS);
        session = jouer(session, "pas la bonne reponse");

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.score().tentatives()).isEqualTo(10);
        assertThat(session.enonceManche()).isEmpty();
    }

    /**
     * S3-2 (DEC-0007, point 3) : aucun indice avant trois echecs consecutifs, l'indice de la
     * manche presentee au dela - ici, le meme item redonne puisque le contenu n'en propose qu'un.
     */
    @Test
    void unItemRateTroisFoisEstRepresenteAvecUnIndiceLaFoisSuivante() {
        Question question = new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.of("une astuce"));
        BanditManchotSession session = demarrer(sequenceInfinie(question));

        assertThat(session.indice()).isEmpty();
        session = jouer(session, "mauvaise");
        assertThat(session.indice()).isEmpty();
        session = jouer(session, "mauvaise");
        assertThat(session.indice()).isEmpty();
        session = jouer(session, "mauvaise");
        assertThat(session.indice()).contains("une astuce");
    }

    /**
     * S3-2, 1er critere : le declencheur est un compte d'echecs consecutifs, quel que soit
     * l'item concerne par chacun - contrairement au declenchement initial de S1-5, qui exigeait
     * trois echecs sur le meme item et rendait l'indice inatteignable en jeu reel.
     */
    @Test
    void troisEchecsConsecutifsSurDesItemsDifferentsDeclenchentAussiLIndice() {
        Question q1 = new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.of("astuce"));
        Question q2 = new Question(new ItemId("q2"), "deux", "2", List.of("3"), Optional.of("astuce"));
        Question q3 = new Question(new ItemId("q3"), "trois", "3", List.of("4"), Optional.of("astuce"));
        BanditManchotSession session = demarrer(sequenceInfinie(q1, q2, q3));

        assertThat(session.indice()).isEmpty();
        session = jouer(session, "faux");
        assertThat(session.indice()).isEmpty();
        session = jouer(session, "faux");
        assertThat(session.indice()).isEmpty();
        session = jouer(session, "faux");

        assertThat(session.indice())
                .as("trois echecs consecutifs sur trois items differents : l'indice se declenche quand meme")
                .contains("astuce");
    }

    /**
     * S3-2, 4e critere : quand la manche plus simple (item deja reussi) et l'indice se
     * declenchent sur le meme evenement, les deux s'appliquent a la meme question suivante sans
     * se contredire ni s'annuler l'un l'autre. Piege nomme par theo-archi : l'indice doit
     * dependre de la valeur entrante d'echecsConsecutifs, pas de sa remise a zero pour la manche
     * plus simple.
     */
    @Test
    void laMancheSimplifieeEtLIndiceSeDeclenchentEnsembleSansSAnnuler() {
        Question succes = new Question(new ItemId("reussi"), "succes", "ok", List.of("non"), Optional.of("astuce succes"));
        Question q1 = new Question(new ItemId("q1"), "un", "1", List.of("2"));
        Question q2 = new Question(new ItemId("q2"), "deux", "2", List.of("3"));
        Question q3 = new Question(new ItemId("q3"), "trois", "3", List.of("4"));
        BanditManchotSession session = demarrer(sequenceInfinie(succes, q1, q2, q3));

        session = jouer(session, "ok");
        assertThat(session.indice()).as("aucun echec consecutif : pas d'indice").isEmpty();
        session = jouer(session, "faux");
        session = jouer(session, "faux");
        session = jouer(session, "faux");

        assertThat(session.enonceManche()).as("manche plus simple : item deja reussi").contains("succes");
        assertThat(session.indice()).as("indice du meme item, sur la meme manche").contains("astuce succes");
    }

    /**
     * S3-2, 3e critere : le compteur d'echecs consecutifs se remet a zero apres chaque reussite
     * (DEC-0004, point 4, inchange), et l'indice se declenche de nouveau sur un second groupe de
     * trois echecs consecutifs plus tard dans la meme partie.
     */
    @Test
    void lIndiceReapparaitApresUnSecondGroupeDeTroisEchecsConsecutifs() {
        Question question = new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.of("astuce"));
        BanditManchotSession session = demarrer(sequenceInfinie(question));

        session = jouer(session, "faux");
        session = jouer(session, "faux");
        session = jouer(session, "faux");
        assertThat(session.indice()).contains("astuce");

        session = jouer(session, "1");
        assertThat(session.indice()).as("reussite : le compteur d'echecs consecutifs repart a zero").isEmpty();
        session = jouer(session, "faux");
        session = jouer(session, "faux");
        assertThat(session.indice()).isEmpty();
        session = jouer(session, "faux");

        assertThat(session.indice())
                .as("second groupe de trois echecs consecutifs : l'indice reapparait")
                .contains("astuce");
    }

    /**
     * DEC-0004, point 4 : trois echecs consecutifs, quel que soit l'item, representent un item
     * deja reussi ; la manche d'apres revient au tirage normal.
     */
    @Test
    void troisEchecsConsecutifsRepresententUnItemDejaReussiPuisLeTirageNormalReprend() {
        Question succes = new Question(new ItemId("reussi"), "succes", "ok", List.of("non"));
        Question q1 = new Question(new ItemId("q1"), "un", "1", List.of("2"));
        Question q2 = new Question(new ItemId("q2"), "deux", "2", List.of("3"));
        Question q3 = new Question(new ItemId("q3"), "trois", "3", List.of("4"));
        Question q4 = new Question(new ItemId("q4"), "quatre", "4", List.of("5"));
        BanditManchotSession session = demarrer(sequenceInfinie(succes, q1, q2, q3, q4));

        session = jouer(session, "ok");
        assertThat(session.enonceManche()).contains("un");
        session = jouer(session, "faux");
        assertThat(session.enonceManche()).contains("deux");
        session = jouer(session, "faux");
        assertThat(session.enonceManche()).contains("trois");
        session = jouer(session, "faux");

        assertThat(session.enonceManche())
                .as("troisieme echec consecutif : represente l'item deja reussi")
                .contains("succes");

        session = jouer(session, "non");
        assertThat(session.enonceManche())
                .as("la manche d'apres revient au tirage normal")
                .contains("quatre");
    }

    /** DEC-0004, point 4 : sans aucun item reussi, le repli est le tirage normal, sans erreur. */
    @Test
    void troisEchecsConsecutifsSansAucunItemReussiSuiventLeTirageNormal() {
        Question q1 = new Question(new ItemId("q1"), "un", "1", List.of("2"));
        Question q2 = new Question(new ItemId("q2"), "deux", "2", List.of("3"));
        Question q3 = new Question(new ItemId("q3"), "trois", "3", List.of("4"));
        Question q4 = new Question(new ItemId("q4"), "quatre", "4", List.of("5"));
        BanditManchotSession session = demarrer(sequenceInfinie(q1, q2, q3, q4));

        session = jouer(session, "faux");
        session = jouer(session, "faux");
        session = jouer(session, "faux");

        assertThat(session.enonceManche()).contains("quatre");
    }
}
