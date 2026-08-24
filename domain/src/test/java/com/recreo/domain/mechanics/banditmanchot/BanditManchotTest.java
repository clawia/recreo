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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test de comportement exige par ADR-0008 : le nom exact de la classe est verifie par
 * ToutesLesMecaniquesSontTesteesTest. Graine fixe, sans navigateur.
 *
 * <p>S10 : reecrit pour l'arbitrage de mecanique de nadia-prof (M1-M13,
 * {@code squad/conformite/FICHE-bandit-manchot-additions-cp.md}). Une reponse fausse ne tire plus
 * la manche suivante ; plusieurs tests qui avancaient dans la partie en repondant faux, parce que
 * c'etait le moyen le plus court, sont reecrits pour repondre juste. L'aide de S3-2 (indice apres
 * trois echecs consecutifs sur des items differents, manche simplifiee representant un item deja
 * reussi) a disparu : elle se declenchait dans le tirage de la manche suivante et devenait
 * inatteignable des qu'une reponse fausse cesse d'en tirer une (avis de faisabilite de theo-archi,
 * s10, section 2.b) ; l'indice est desormais une propriete de la manche bloquante elle-meme.</p>
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

            @Override
            public boolean indicesNommentLaReponse() {
                return false;
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
     * M1 : l'indice se declenche des le premier echec sur la manche courante - une partie d'une
     * seule manche peut deja le montrer. CatalogueChargeableIT compare cette valeur a
     * nombreDeManches pour chaque jeu du catalogue reel plutot que de l'ecrire en dur.
     */
    @Test
    void publieSaLongueurMinimalePourQueLAideResteAtteignable() {
        assertThat(new BanditManchot().longueurMinimale()).isEqualTo(1);
    }

    @Test
    void laPremiereMancheEstTireeAuDemarrage() {
        BanditManchotSession session = demarrer(troisManches());

        assertThat(session.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(session.enonceManche()).contains("un");
        assertThat(session.propositions()).containsExactly("2", "3", "1");
        assertThat(session.score()).isEqualTo(new Score(0, 0));
        assertThat(session.dernierVerdict()).isEmpty();
        assertThat(session.indice()).as("aucun echec encore : pas d'indice").isEmpty();
        assertThat(session.propositionsEssayees()).isEmpty();
        assertThat(session.derniereTentative()).isEmpty();
        assertThat(session.revelee()).isFalse();
    }

    @Test
    void unChoixCorrectEstJugeJusteEtLeScoreAvance() {
        BanditManchotSession premiere = demarrer(troisManches());

        BanditManchotSession suivante = jouer(premiere, "1");

        assertThat(suivante.dernierVerdict()).contains(new Verdict(true, "1", Optional.empty()));
        assertThat(suivante.score()).isEqualTo(new Score(1, 1));
        assertThat(suivante.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(suivante.enonceManche()).contains("deux");
        assertThat(suivante.propositions()).containsExactly("3", "2");
        assertThat(suivante.propositionsEssayees()).as("manche fraiche : aucun essai encore").isEmpty();
    }

    /**
     * M5, M9 : un echec ne resout plus la manche. Elle reste la meme, l'essai est marque, et la
     * bonne reponse n'est pas revelee.
     */
    @Test
    void unChoixIncorrectResteSurLaMemeMancheSansRevelerLaBonneReponse() {
        BanditManchotSession premiere = demarrer(troisManches());

        BanditManchotSession apresLEchec = jouer(premiere, "3");

        assertThat(apresLEchec.enonceManche()).as("la manche n'a pas avance").contains("un");
        assertThat(apresLEchec.propositions())
                .as("M8 : les propositions gardent le meme ordre")
                .containsExactly("2", "3", "1");
        assertThat(apresLEchec.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(apresLEchec.score())
                .as("aucune manche resolue : le score ne bouge pas (M3)")
                .isEqualTo(new Score(0, 0));
        assertThat(apresLEchec.propositionsEssayees()).containsExactly("3");
        assertThat(apresLEchec.derniereTentative()).contains("3");
        assertThat(apresLEchec.revelee()).as("un seul echec : pas encore de revelation").isFalse();
    }

    /**
     * ADR-0024 : l'explication de l'item joue voyage dans le verdict qui resout sa manche, que la
     * resolution vienne d'une reussite immediate ou d'une revelation (M7 : elle survit du cote
     * faux). Un item sans explication n'en fait pas apparaitre une.
     */
    @Test
    void leVerdictPorteLExplicationDeLItemJoueEtRienQuandLItemNEnPorteAucune() {
        String explicationQ1 = "xyzzy 42 - ceci n'est pas une phrase";
        ContentSource contenu = new ListContentSource(DESCRIPTEUR, List.of(
                new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.empty(),
                        Optional.of(explicationQ1)),
                new Question(new ItemId("q2"), "deux", "2", List.of("3"))));
        BanditManchotSession session = demarrer(contenu);

        BanditManchotSession apresUneReponseJuste = jouer(session, "1");
        assertThat(apresUneReponseJuste.dernierVerdict().orElseThrow().explication())
                .as("reussite immediate : l'explication de l'item joue voyage telle quelle")
                .contains(explicationQ1);

        // q2 n'a pas d'explication ; trois echecs puis la revelation la resolvent (M5, M7).
        BanditManchotSession apresTroisEchecs = apresUneReponseJuste;
        for (int echec = 0; echec < 3; echec++) {
            apresTroisEchecs = jouer(apresTroisEchecs, "pas 2");
        }
        assertThat(apresTroisEchecs.revelee()).isTrue();

        BanditManchotSession apresRevelation = jouer(apresTroisEchecs, "2");
        assertThat(apresRevelation.dernierVerdict().orElseThrow().juste())
                .as("M11 clause 3 : une manche resolue par revelation n'est pas une reussite")
                .isFalse();
        assertThat(apresRevelation.dernierVerdict().orElseThrow().explication())
                .as("ADR-0024 : un item sans explication n'en fait pas apparaitre une")
                .isEmpty();
    }

    @Test
    void lEnchainementDesManchesConserveLeScoreCumule() {
        BanditManchotSession session = demarrer(troisManches());
        session = jouer(session, "1");
        session = jouer(session, "2");

        assertThat(session.score()).isEqualTo(new Score(2, 2));
        assertThat(session.dernierVerdict()).contains(new Verdict(true, "2", Optional.empty()));
        assertThat(session.enonceManche()).contains("trois");
    }

    @Test
    void laPartieSeTermineQuandLeVivierEstEpuise() {
        BanditManchotSession session = demarrer(troisManches());
        session = jouer(session, "1");
        session = jouer(session, "2");
        session = jouer(session, "3");

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.enonceManche()).isEmpty();
        assertThat(session.propositions()).isEmpty();
        assertThat(session.score()).isEqualTo(new Score(3, 3));
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
        assertThat(suivanteAnglais.dernierVerdict()).contains(new Verdict(true, "rouge", Optional.empty()));
        assertThat(suivanteAnglais.score()).isEqualTo(new Score(1, 1));
    }

    @Test
    void leTirageExcluBienLesManchesDejaVues() {
        BanditManchotSession session = demarrer(troisManches());
        Set<String> enonces = new LinkedHashSet<>();
        enonces.add(session.enonceManche().orElseThrow());

        session = jouer(session, "1");
        enonces.add(session.enonceManche().orElseThrow());
        session = jouer(session, "2");
        enonces.add(session.enonceManche().orElseThrow());

        assertThat(enonces).containsExactly("un", "deux", "trois");
    }

    /**
     * M3, amendement d'ADR-0012 : la mecanique compte les manches <b>resolues</b>, jamais les
     * essais, et le contenu combinatoire d'AdditionsBorneesContentSource ne renvoie jamais
     * Optional.empty() (Javadoc citee par nadia-prof) : avec un hasard fixe, il represente
     * toujours la meme addition (0 + 1) - seul nombreDeManches peut ici mettre fin a la partie.
     */
    @Test
    void laPartieSeTermineApresLeNombreDeManchesMemeAvecUnContenuCombinatoireInfini() {
        ContentSource combinatoire = new AdditionsBorneesContentSource(DESCRIPTEUR, 10, 3, "Compte sur tes doigts.");
        BanditManchotSession session = demarrer(combinatoire, 10);

        for (int manche = 1; manche <= 9; manche++) {
            assertThat(session.status()).as("avant la manche %d sur 10", manche).isEqualTo(SessionStatus.EN_COURS);
            session = jouer(session, "1");
        }

        assertThat(session.status()).isEqualTo(SessionStatus.EN_COURS);
        session = jouer(session, "1");

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.score().tentatives()).isEqualTo(10);
        assertThat(session.score().reussites()).isEqualTo(10);
        assertThat(session.enonceManche()).isEmpty();
    }

    /**
     * M1 : l'indice de la manche bloquante apparait des le premier echec sur cette manche, et
     * reste affiche pour les essais restants - il n'attend plus trois echecs consecutifs sur des
     * items differents comme l'ancienne aide de S3-2.
     */
    @Test
    void unIndiceApparaitDesLePremierEchecSurLaMancheBloquante() {
        Question question = new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.of("une astuce"));
        BanditManchotSession session = demarrer(sequenceInfinie(question));

        assertThat(session.indice()).isEmpty();
        session = jouer(session, "2");
        assertThat(session.indice())
                .as("premier echec sur la manche courante : l'indice apparait immediatement")
                .contains("une astuce");
        session = jouer(session, "2");
        assertThat(session.indice()).as("deuxieme echec : le meme indice, pas un second").contains("une astuce");
    }

    /** M1 : l'indice est une propriete de la manche courante ; une manche fraiche n'en porte pas. */
    @Test
    void lIndiceDisparaitSurUneMancheFraicheEtReapparaitApresUnNouvelEchec() {
        Question q1 = new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.of("astuce un"));
        Question q2 = new Question(new ItemId("q2"), "deux", "2", List.of("3"), Optional.of("astuce deux"));
        BanditManchotSession session = demarrer(sequenceInfinie(q1, q2));

        session = jouer(session, "2");
        assertThat(session.indice()).contains("astuce un");

        session = jouer(session, "1");
        assertThat(session.enonceManche()).as("reussite : manche suivante").contains("deux");
        assertThat(session.indice()).as("manche fraiche : aucun indice avant le premier echec").isEmpty();

        session = jouer(session, "3");
        assertThat(session.indice()).contains("astuce deux");
    }

    /**
     * M21 (arbitrage de nadia-prof du 2026-08-13) : sur un paquet qui declare que ses indices
     * nomment la bonne reponse, aucun indice n'apparait, quel que soit le nombre d'echecs sur la
     * manche - le repli habituel de M1 reste inatteignable pour ce paquet-la.
     */
    @Test
    void aucunIndiceNApparaitSurUnPaquetDontLesIndicesNommentLaReponse() {
        Question question = new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.of("c'est 1"));
        ContentSource paquetRevelateur = new ListContentSource(DESCRIPTEUR, List.of(question), true);
        BanditManchotSession session = demarrer(paquetRevelateur);

        session = jouer(session, "2");
        assertThat(session.indice())
                .as("M21 : ce paquet declare que ses indices nomment la reponse, aucun indice ne doit "
                        + "jamais apparaitre, meme apres un echec")
                .isEmpty();
    }

    /**
     * M5 : au troisieme echec sur la manche, la reponse est revelee - la manche n'est pas pour
     * autant resolue, et aucune manche resolue ne fait bouger le score (M3).
     */
    @Test
    void troisEchecsSurLaMemeMancheDeclenchentLaRevelationSansResoudreLaManche() {
        BanditManchotSession session = demarrer(troisManches());

        session = jouer(session, "3");
        assertThat(session.revelee()).isFalse();
        session = jouer(session, "3");
        assertThat(session.revelee()).isFalse();
        session = jouer(session, "3");

        assertThat(session.revelee()).isTrue();
        assertThat(session.enonceManche()).as("la manche n'a toujours pas avance").contains("un");
        assertThat(session.status()).isEqualTo(SessionStatus.EN_COURS);
        assertThat(session.score()).isEqualTo(new Score(0, 0));
        assertThat(session.propositionsEssayees()).containsExactly("3");
    }

    /**
     * M5 : sur une manche revelee, un appui sur une autre proposition que la reponse nommee ne
     * change rien - ni essai compte, ni etat modifie.
     */
    @Test
    void unAppuiSurUneAutrePropositionQueLaReponseReveleeNeChangeRien() {
        BanditManchotSession session = demarrer(troisManches());
        session = jouer(session, "3");
        session = jouer(session, "3");
        session = jouer(session, "3");
        assertThat(session.revelee()).isTrue();

        BanditManchotSession apresUnAppuiIgnore = jouer(session, "2");

        assertThat(apresUnAppuiIgnore.enonceManche()).contains("un");
        assertThat(apresUnAppuiIgnore.score()).isEqualTo(new Score(0, 0));
        assertThat(apresUnAppuiIgnore.revelee()).isTrue();
        assertThat(apresUnAppuiIgnore.propositionsEssayees())
                .as("l'appui ignore ne marque pas une proposition de plus")
                .containsExactly("3");
    }

    /**
     * M5, M11 clause 3 : presser la reponse revelee fait avancer la partie, mais ne compte pas
     * comme une reussite - le score de reussites ne monte pas, seul le compte de manches resolues
     * avance (M3).
     */
    @Test
    void presserLaReponseReveleeAvanceLaPartieSansCompterCommeUneReussite() {
        BanditManchotSession session = demarrer(troisManches());
        session = jouer(session, "3");
        session = jouer(session, "3");
        session = jouer(session, "3");

        BanditManchotSession apresRevelation = jouer(session, "1");

        assertThat(apresRevelation.enonceManche()).as("la manche a enfin avance").contains("deux");
        assertThat(apresRevelation.dernierVerdict().orElseThrow().juste()).isFalse();
        assertThat(apresRevelation.score()).isEqualTo(new Score(0, 1));
        assertThat(apresRevelation.revelee()).as("la manche neuve n'est pas revelee").isFalse();
        assertThat(apresRevelation.propositionsEssayees()).isEmpty();
    }

    /**
     * M9 : un echec repete sur la meme proposition consomme quand meme un essai - c'est ce qui
     * rend la revelation atteignable sur un jeu a quatre propositions (fiche de nadia-prof,
     * section "2 bis") sans exiger que l'enfant epuise chaque distracteur distinct.
     */
    @Test
    void unEchecRepeteSurLaMemePropositionCompteCommeUnEssaiDePlus() {
        Question quatrePropositions = new Question(new ItemId("q1"), "question", "bonne",
                List.of("faux1", "faux2", "faux3"));
        BanditManchotSession session = demarrer(sequenceInfinie(quatrePropositions));

        session = jouer(session, "faux1");
        session = jouer(session, "faux1");
        assertThat(session.revelee()).as("deux essais, meme proposition : pas encore revele").isFalse();
        assertThat(session.propositionsEssayees())
                .as("une seule proposition distincte a ete essayee")
                .containsExactly("faux1");

        session = jouer(session, "faux1");
        assertThat(session.revelee()).as("trois essais, la revelation se declenche").isTrue();
    }

    /**
     * M3 : la partie presente exactement le nombre de manches annonce, jamais moins, jamais plus,
     * et ne se termine jamais au milieu d'une question - meme quand l'enfant echoue les trois
     * essais de chacune et n'avance que par la revelation.
     */
    @Test
    void laPartiePresenteExactementLeNombreDeManchesAnnonceMemeEnEchouantToutesLesQuestions() {
        Question q1 = new Question(new ItemId("q1"), "un", "1", List.of("2"));
        Question q2 = new Question(new ItemId("q2"), "deux", "2", List.of("3"));
        Question q3 = new Question(new ItemId("q3"), "trois", "3", List.of("4"));
        BanditManchotSession session = demarrer(sequenceInfinie(q1, q2, q3), 3);
        List<String> enoncesDistincts = new java.util.ArrayList<>();
        enoncesDistincts.add(session.enonceManche().orElseThrow());

        for (int question = 0; question < 3; question++) {
            String bonneReponse = String.valueOf(question + 1);
            for (int echec = 0; echec < 3; echec++) {
                session = jouer(session, "pas la bonne reponse");
                assertThat(session.status())
                        .as("jamais termine au milieu d'une question (M3)")
                        .isEqualTo(SessionStatus.EN_COURS);
            }
            assertThat(session.revelee()).isTrue();
            session = jouer(session, bonneReponse);
            session.enonceManche().ifPresent(enoncesDistincts::add);
        }

        assertThat(enoncesDistincts).containsExactly("un", "deux", "trois");
        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.score().tentatives()).isEqualTo(3);
        assertThat(session.score().reussites()).as("les trois ont ete resolues par revelation").isEqualTo(0);
    }

    /**
     * M5, M9 clause 5, M7 : la bonne reponse et l'explication de la manche courante ne sont
     * exposees qu'une fois la manche revelee - jamais avant, sur aucune des deux methodes.
     */
    @Test
    void laReponseEtLExplicationNeSontExposeesQuUneFoisLaMancheRevelee() {
        ContentSource contenu = new ListContentSource(DESCRIPTEUR, List.of(
                new Question(new ItemId("q1"), "un", "1", List.of("2"), Optional.empty(),
                        Optional.of("explication de q1"))));
        BanditManchotSession session = demarrer(contenu);

        assertThat(session.reponseRevelee()).isEmpty();
        assertThat(session.explicationRevelee()).isEmpty();

        session = jouer(session, "2");
        session = jouer(session, "2");
        assertThat(session.reponseRevelee()).as("deux echecs : toujours pas revelee").isEmpty();
        assertThat(session.explicationRevelee()).isEmpty();

        session = jouer(session, "2");
        assertThat(session.revelee()).isTrue();
        assertThat(session.reponseRevelee()).contains("1");
        assertThat(session.explicationRevelee()).contains("explication de q1");
    }

    /** M13 : la derniere tentative sert au point de focus ; elle est neuve sur chaque manche. */
    @Test
    void laDerniereTentativeEstReinitialiseeSurUneMancheFraiche() {
        BanditManchotSession session = demarrer(troisManches());

        session = jouer(session, "3");
        assertThat(session.derniereTentative()).contains("3");

        session = jouer(session, "1");
        assertThat(session.derniereTentative())
                .as("manche resolue : la manche suivante est fraiche")
                .isEmpty();
    }
}
