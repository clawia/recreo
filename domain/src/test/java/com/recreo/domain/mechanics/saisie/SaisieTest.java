package com.recreo.domain.mechanics.saisie;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.SessionStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Le comportement de la mecanique de saisie, cote serveur (ADR-0008), contre les conditions de
 * l'arbitrage prealable de nadia-prof du 2026-08-24 et ses trois amendements
 * (squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md).
 *
 * <p>Porte les trois mecanismes qu'ADR-0058 declare dus avec le code de ce lot et qui ne sont pas
 * encodables en ArchUnit - ArchUnit voit des types, pas la valeur d'un objet a un instant d'une
 * transition ni l'ordre d'une collection :</p>
 * <ul>
 *   <li><b>R-B</b> - aucune distance entre chaines nulle part (SAI-5), sur la table de couples que
 *   nadia-prof a nommee elle-meme ;</li>
 *   <li><b>R-C</b> - l'ordre des tentatives rendues est l'ordre d'ecriture (SAI-12), avec la
 *   seconde moitie qui distingue "ordonne" de "ordonne par hasard comme je l'attendais" ;</li>
 *   <li><b>R-D</b> - sur un item en forme exacte resolu par une reussite, le verdict porte la
 *   chaine recue <b>et</b> la manche a deja change, dans la meme assertion ; la resolution par
 *   revelation la porte aussi.</li>
 * </ul>
 */
class SaisieTest {

    private static final RandomSource PREMIER = borne -> 0;

    private static final ContentDescriptor DESCRIPTEUR =
            new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "sonde de test");

    private static ReponseAEcrire item(String identifiant, String enonce, String formeDeReference,
                                        List<String> formesAcceptees, ExigenceDeForme exigence) {
        return new ReponseAEcrire(new ItemId(identifiant), enonce, formeDeReference, formesAcceptees,
                Optional.of("Indice de " + identifiant), Optional.empty(), exigence);
    }

    private static ContentSource paquetDe(ReponseAEcrire... items) {
        return new ListContentSource(DESCRIPTEUR, List.of(items), false);
    }

    private static SaisieSession partieDe(int nombreDeManches, ReponseAEcrire... items) {
        return (SaisieSession) new Saisie().start(paquetDe(items), PREMIER, nombreDeManches);
    }

    private static SaisieSession ecrire(SaisieSession session, String texte) {
        return (SaisieSession) new Saisie().apply(session, new SaisieMove(texte));
    }

    private static SaisieSession continuer(SaisieSession session) {
        return (SaisieSession) new Saisie().apply(session, new ContinuerMove());
    }

    private static ReponseAEcrire libre(String identifiant, String reference, String... acceptees) {
        return item(identifiant, "Enonce de " + identifiant, reference, List.of(acceptees),
                ExigenceDeForme.FORME_LIBRE);
    }

    private static ReponseAEcrire exacte(String identifiant, String reference) {
        return item(identifiant, "Enonce de " + identifiant, reference, List.of(),
                ExigenceDeForme.FORME_EXACTE);
    }

    @Test
    void laMecaniquePorteSonIdentifiantEtSaLongueurMinimale() {
        assertThat(new Saisie().id().value()).isEqualTo("saisie");
        assertThat(new Saisie().longueurMinimale())
                .as("M1 : l'indice se declenche des le premier echec, une manche suffit")
                .isEqualTo(1);
    }

    @Test
    void unePartieCommenceSurUneMancheFraicheSansMessageNiIndice() {
        SaisieSession session = partieDe(2, libre("item-0", "7"));

        assertThat(session.enonceManche()).contains("Enonce de item-0");
        assertThat(session.tentatives()).isEmpty();
        assertThat(session.derniereTentative()).isEmpty();
        assertThat(session.indice())
                .as("M1 : rien tant que l'enfant n'a rien envoye")
                .isEmpty();
        assertThat(session.revelee()).isFalse();
        assertThat(session.dernierVerdict()).isEmpty();
        assertThat(session.score().reussites()).isZero();
    }

    @Test
    void uneReponseExacteResoutLaMancheEtFaitMonterLeScore() {
        SaisieSession session = ecrire(partieDe(2, libre("item-0", "7"), libre("item-1", "5")), "7");

        assertThat(session.score().reussites()).isEqualTo(1);
        assertThat(session.score().tentatives())
                .as("M3 : le score compte des manches resolues, jamais des essais bruts")
                .isEqualTo(1);
        assertThat(session.dernierVerdict().orElseThrow().juste()).isTrue();
    }

    /**
     * SAI-1 (les signes poses sur les lettres), SAI-3 (la casse) et SAI-4 (les blancs, y compris le
     * blanc manquant entre deux mots). Le blanc insecable est du lot : c'est celui qu'un
     * copier-coller depose, et {@code Character.isWhitespace} ne le voit pas.
     */
    @Test
    void lesTroisTolerancesRendentLaReponseJusteSurUnItemEnFormeLibre() {
        List<String> ecrituresJustes = List.of("eleve", "ÉLÈVE", "  élève  ", "él ève", "ELEVE");

        for (String ecriture : ecrituresJustes) {
            SaisieSession session = ecrire(partieDe(2, libre("item-0", "élève"),
                    libre("item-1", "autre")), ecriture);

            assertThat(session.dernierVerdict().orElseThrow().juste())
                    .as("SAI-1, SAI-3, SAI-4 : '%s' vaut 'élève'", ecriture)
                    .isTrue();
        }
    }

    @Test
    void leBlancManquantEntreDeuxMotsNeDecidePasDuVerdict() {
        SaisieSession session = ecrire(partieDe(2, libre("item-0", "deux cent cinq"),
                libre("item-1", "autre")), "deuxcentcinq");

        assertThat(session.dernierVerdict().orElseThrow().juste())
                .as("SAI-4 : les lettres sont la, dans l'ordre")
                .isTrue();
    }

    /**
     * SAI-2a : le mode strict s'obtient par la declaration du contenu et jamais autrement. Le meme
     * ecart d'accent qui est juste sur un item en forme libre est faux sur un item en forme exacte,
     * et rien d'autre ne change entre les deux.
     */
    @Test
    void surUnItemEnFormeExacteUnEcartDAccentRendLaReponseFausse() {
        SaisieSession session = ecrire(partieDe(2, exacte("item-0", "à"), exacte("item-1", "ou")), "a");

        assertThat(session.dernierVerdict())
                .as("la manche n'est pas resolue : rien n'a encore ete verdicte")
                .isEmpty();
        assertThat(session.tentatives()).containsExactly("a");
    }

    @Test
    void surUnItemEnFormeExacteLEcritureExacteResoutLaManche() {
        SaisieSession session = ecrire(partieDe(2, exacte("item-0", "à"), exacte("item-1", "ou")), "à");

        assertThat(session.dernierVerdict().orElseThrow().juste()).isTrue();
    }

    /**
     * SAI-6, SAI-7 : la liste des formes acceptees est le bareme, et elle vit dans la donnee. La
     * mecanique n'en connait aucune - elle lit celle de l'item.
     */
    @Test
    void uneFormeAccepteeDuPaquetRendLaReponseJuste() {
        SaisieSession session = ecrire(partieDe(2, libre("item-0", "7", "sept"),
                libre("item-1", "autre")), "sept");

        assertThat(session.dernierVerdict().orElseThrow().juste()).isTrue();
    }

    /**
     * SEUIL-2 : une reponse juste ne coute jamais un essai. Le bareme est confronte avant qu'un
     * essai soit compte, donc une reponse juste ecrite sous une forme acceptee, ou sous une forme
     * que les tolerances couvrent, laisse les trois essais intacts.
     */
    @Test
    void uneReponseJusteEcriteSousUneFormeAccepteeNeConsommeAucunEssai() {
        SaisieSession session = partieDe(3, libre("item-0", "élève", "eleve"),
                libre("item-1", "autre"), libre("item-2", "encore"));

        SaisieSession apresUneJuste = ecrire(session, "ELEVE");

        assertThat(apresUneJuste.tentatives())
                .as("SEUIL-2 : aucun essai n'a ete depense")
                .isEmpty();
        assertThat(apresUneJuste.revelee()).isFalse();
        assertThat(apresUneJuste.score().reussites()).isEqualTo(1);
    }

    @Test
    void unePremiereReponseFausseLaisseLEnfantSurLaMemeMancheEtMontreLIndice() {
        SaisieSession session = ecrire(partieDe(2, libre("item-0", "7"), libre("item-1", "5")), "3");

        assertThat(session.enonceManche())
                .as("M5 : une reponse fausse ne tire pas la manche suivante")
                .contains("Enonce de item-0");
        assertThat(session.indice())
                .as("M1 : l'indice arrive des le premier echec sur cette manche")
                .contains("Indice de item-0");
        assertThat(session.revelee()).isFalse();
        assertThat(session.formeDeReferenceRevelee())
                .as("M9 clause 5, SAI-9a : la reponse attendue n'est jamais nommee avant la revelation")
                .isEmpty();
        assertThat(session.score().tentatives()).isZero();
    }

    @Test
    void leTroisiemeEchecReveleLaReponseSansResoudreLaManche() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));
        session = ecrire(ecrire(ecrire(session, "1"), "2"), "3");

        assertThat(session.revelee())
                .as("M5 : trois essais, puis la revelation")
                .isTrue();
        assertThat(session.formeDeReferenceRevelee())
                .as("SAI-9b : c'est la, et seulement la, que la reponse attendue est nommee")
                .contains("7");
        assertThat(session.score().tentatives())
                .as("la manche n'est pas encore resolue : elle attend le geste de l'enfant")
                .isZero();
    }

    @Test
    void uneReponseEcriteSurUneMancheReveleeNeChangeRien() {
        SaisieSession revelee = revelerLaPremiereManche();

        SaisieSession apres = ecrire(revelee, "7");

        assertThat(apres).isSameAs(revelee);
    }

    @Test
    void leGesteDeSuiteResoutLaMancheReveleeSansLaCompterCommeUneReussite() {
        SaisieSession session = continuer(revelerLaPremiereManche());

        assertThat(session.score().tentatives())
                .as("M3 : la manche est resolue")
                .isEqualTo(1);
        assertThat(session.score().reussites())
                .as("M11 clause 3 : une manche resolue par revelation n'est pas une reussite")
                .isZero();
        assertThat(session.enonceManche()).contains("Enonce de item-1");
    }

    @Test
    void leGesteDeSuiteNeChangeRienHorsRevelation() {
        SaisieSession fraiche = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));

        assertThat(continuer(fraiche)).isSameAs(fraiche);
    }

    @Test
    void lePaquetQuiDeclareQueSesIndicesNommentLaReponseNEnMontreAucun() {
        ContentSource paquet = new ListContentSource(DESCRIPTEUR,
                List.of(libre("item-0", "7"), libre("item-1", "5")), true);
        SaisieSession session = (SaisieSession) new Saisie().start(paquet, PREMIER, 2);

        assertThat(ecrire(session, "3").indice())
                .as("M21 : un indice qui nomme la reponse n'est pas une aide")
                .isEmpty();
    }

    @Test
    void unePartieSeTermineQuandToutesSesManchesSontResolues() {
        SaisieSession terminee = ecrire(partieDe(1, libre("item-0", "7"), libre("item-1", "5")), "7");

        assertThat(terminee.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(terminee.enonceManche()).isEmpty();
        assertThat(terminee.dernierVerdict()).isPresent();
        assertThatThrownBy(() -> ecrire(terminee, "5"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("la partie est terminee");
    }

    @Test
    void unePartieSeTermineQuandLeViverEstEpuise() {
        SaisieSession session = ecrire(partieDe(5, libre("item-0", "7")), "7");

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
    }

    @Test
    void laMecaniqueRefuseUneSessionOuUnCoupQuiNeSontPasLesSiens() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));
        GameSession etrangere = new GameSession() {
            @Override
            public SessionStatus status() {
                return SessionStatus.EN_COURS;
            }

            @Override
            public com.recreo.domain.mechanic.Score score() {
                return new com.recreo.domain.mechanic.Score(0, 0);
            }
        };
        Move coupEtranger = new Move() {
        };

        assertThatThrownBy(() -> new Saisie().apply(etrangere, new SaisieMove("7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("session inconnue");
        assertThatThrownBy(() -> new Saisie().apply(session, coupEtranger))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("coup inconnu");
    }

    /**
     * ADR-0004, couture scellee d'Item : un paquet de choix associe a cette mecanique-ci est un
     * defaut de catalogue, et la mecanique le nomme au lieu de le deviner.
     */
    @Test
    void unItemDeChoixArreteLaMecaniqueDeSaisieEnLeNommant() {
        ContentSource paquetDeChoix = new ListContentSource(DESCRIPTEUR,
                List.of(new Question(new ItemId("item-0"), "Enonce", "7", List.of("3", "5"))), false);

        assertThatThrownBy(() -> new Saisie().start(paquetDeChoix, PREMIER, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("item inconnu de la mecanique de saisie")
                .hasMessageContaining("item-0");
    }

    // ------------------------------------------------------------------------------------------
    // R-B : aucune distance entre chaines, nulle part (SAI-5)
    // ------------------------------------------------------------------------------------------

    /**
     * R-B, due avec ce code par ADR-0058 : la moitie "bibliotheque tierce" de SAI-5 est deja gardee
     * par la clause 3 d'ADR-0004, qui interdit a une mecanique toute dependance hors du JDK et des
     * deux contrats. La moitie "distance ecrite a la main" n'est pas encodable en ArchUnit - une
     * boucle sur {@code java.lang.String} ne cite aucun type de particulier - et c'est ce test qui
     * la tient.
     *
     * <p>La table est celle que nadia-prof a nommee, mot pour mot, et elle porte les deux motifs
     * qu'elle distingue : sur une reponse chiffree, un signe de difference est une autre valeur
     * (300/200, 47/42) ; sur une reponse en mots, une tolerance d'un signe confond des mots reels
     * (chevaux/cheveux, pere/perte, sept/cept). C'est la condition sur laquelle elle a ecrit qu'elle
     * refuserait un increment sans discuter.</p>
     */
    @Test
    void aucuneReponseVoisineNEstAccepteeSurLesCouplesQueNadiaProfANommes() {
        List<List<String>> couples = List.of(
                List.of("300", "200"),
                List.of("47", "42"),
                List.of("chevaux", "cheveux"),
                List.of("pere", "perte"),
                List.of("sept", "cept"));

        for (List<String> couple : couples) {
            String attendu = couple.get(0);
            String voisin = couple.get(1);
            SaisieSession session = ecrire(partieDe(2, libre("item-0", attendu),
                    libre("item-1", "autre")), voisin);

            assertThat(session.dernierVerdict())
                    .as("SAI-5 : '%s' n'est pas '%s', et aucune distance ne les rapproche",
                            voisin, attendu)
                    .isEmpty();
            assertThat(session.tentatives()).containsExactly(voisin);
        }
    }

    /**
     * La seconde moitie de R-B, sans laquelle le test ci-dessus serait vert sur une mecanique qui
     * refuserait tout : les cinq formes attendues elles-memes doivent etre acceptees.
     */
    @Test
    void lesCinqFormesAttenduesDeLaMemeTableRestentAcceptees() {
        for (String attendu : List.of("300", "47", "chevaux", "pere", "sept")) {
            SaisieSession session = ecrire(partieDe(2, libre("item-0", attendu),
                    libre("item-1", "autre")), attendu);

            assertThat(session.dernierVerdict().orElseThrow().juste())
                    .as("'%s' ecrit exactement reste juste", attendu)
                    .isTrue();
        }
    }

    // ------------------------------------------------------------------------------------------
    // R-C : l'ordre des tentatives est l'ordre d'ecriture (SAI-12)
    // ------------------------------------------------------------------------------------------

    /**
     * R-C, due avec ce code par ADR-0058 (amendement du 2026-08-24). SAI-12 demande que l'enfant
     * voie, aux deuxieme et troisieme essais, la liste de ce qu'il a deja ecrit sur cette manche -
     * pas seulement sa derniere tentative - et nadia-prof precise le 2026-08-30 que la liste porte
     * les chaines <b>brutes</b> et qu'une repetition y figure deux fois.
     */
    @Test
    void lesTentativesReviennentDansLOrdreOuLEnfantLesAEcrites() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));

        session = ecrire(ecrire(ecrire(session, "1"), "2"), "3");

        assertThat(session.tentatives()).containsExactly("1", "2", "3");
        assertThat(session.derniereTentative()).contains("3");
    }

    /**
     * La seconde moitie de R-C, et c'est elle qui distingue "ordonne" de "ordonne par hasard comme
     * je l'attendais" : le meme test sur un autre ordre d'ecriture rend cet autre ordre. Un
     * {@code Set.copyOf}, dont l'ordre d'iteration n'est pas specifie, fait rougir l'une des deux.
     */
    @Test
    void unAutreOrdreDEcritureRendCetAutreOrdre() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));

        session = ecrire(ecrire(ecrire(session, "3"), "1"), "2");

        assertThat(session.tentatives()).containsExactly("3", "1", "2");
    }

    /**
     * SAI-12, reponse 2 de nadia-prof du 2026-08-30 : une chaine retapee a l'identique apparait
     * deux fois. Deduplier reviendrait a cacher a l'enfant que sa correction n'a rien corrige.
     */
    @Test
    void uneChaineRetapeeALIdentiqueFigureDeuxFoisDansLaListe() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));

        session = ecrire(ecrire(session, "3"), "3");

        assertThat(session.tentatives()).containsExactly("3", "3");
    }

    /**
     * SAI-12, reponse 3 de nadia-prof : la liste montre les chaines telles que l'enfant les a
     * tapees, jamais repliees. Un enfant qui ecrit "sept" puis "Sept" doit voir les deux - c'est
     * ainsi qu'il apprend que la majuscule n'etait pas le probleme.
     */
    @Test
    void laListeMontreLesChainesBrutesEtNonRepliees() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));

        session = ecrire(ecrire(session, "sept"), "Sept");

        assertThat(session.tentatives()).containsExactly("sept", "Sept");
    }

    // ------------------------------------------------------------------------------------------
    // R-D : le verdict porte la chaine recue apres resolution, revelation comprise
    // ------------------------------------------------------------------------------------------

    /**
     * R-D, due avec ce code par ADR-0058 (amendement du 2026-08-25). SAI-2c demande que la chaine
     * jugee soit montree <b>aussi</b> a la reussite sur un item en forme exacte.
     *
     * <p><b>La seconde moitie de l'enonce est la seule qui rende ce test non decoratif</b>, et elle
     * est dans la meme assertion : un test qui se contenterait de lire la chaine apres une bonne
     * reponse pourrait la lire sur une session qui n'a pas encore resolu, et serait vert sur une
     * implementation ou la chaine disparait a la resolution - le defaut exact que l'amendement
     * existe pour fermer. La manche a change : la suivante est tiree.</p>
     */
    @Test
    void surUnItemEnFormeExacteResoluParUneReussiteLeVerdictPorteLaChaineEtLaMancheADejaChange() {
        SaisieSession session = partieDe(3, exacte("item-0", "à"), exacte("item-1", "où"),
                exacte("item-2", "ces"));

        session = ecrire(session, "à");

        SaisieVerdict verdict = session.dernierVerdict().orElseThrow();
        assertThat(verdict.juste()).isTrue();
        assertThat(verdict.chaineJugee()).isEqualTo("à");
        assertThat(verdict.exigenceDeForme()).isEqualTo(ExigenceDeForme.FORME_EXACTE);
        assertThat(session.enonceManche())
                .as("R-D : la manche a deja change au moment ou le verdict est rendu")
                .contains("Enonce de item-1");
    }

    /** Le meme fait sur la derniere manche : la partie est terminee et la chaine est toujours la. */
    @Test
    void surLaDerniereMancheLeVerdictPorteLaChaineEtLaPartieEstTerminee() {
        SaisieSession session = ecrire(partieDe(1, exacte("item-0", "à")), "à");

        assertThat(session.status()).isEqualTo(SessionStatus.TERMINEE);
        assertThat(session.dernierVerdict().orElseThrow().chaineJugee()).isEqualTo("à");
    }

    /**
     * La seconde moitie du perimetre de R-D, contre l'omission : ne juger que la reussite
     * laisserait passer une implementation qui ne remplit la composante que dans une branche de la
     * resolution. La resolution par revelation porte la chaine autant que la reussite (SAI-9b).
     */
    @Test
    void uneResolutionParRevelationPorteElleAussiLaChaineRecue() {
        SaisieSession session = continuer(revelerLaPremiereManche());

        SaisieVerdict verdict = session.dernierVerdict().orElseThrow();
        assertThat(verdict.juste()).isFalse();
        assertThat(verdict.chaineJugee())
                .as("SAI-9b : c'est la derniere chaine ecrite qui est montree a cote de la reponse")
                .isEqualTo("3");
        assertThat(verdict.formeDeReference()).isEqualTo("7");
        assertThat(session.enonceManche())
                .as("R-D : la manche a deja change au moment ou le verdict est rendu")
                .contains("Enonce de item-1");
    }

    /** Le verdict porte l'explication de l'item joue, vide quand le contenu n'en porte pas. */
    @Test
    void leVerdictPorteLExplicationDeLItemJoue() {
        ReponseAEcrire avecExplication = new ReponseAEcrire(new ItemId("item-0"), "Enonce", "7",
                List.of(), Optional.empty(), Optional.of("Sept, parce que trois et sept font dix."),
                ExigenceDeForme.FORME_LIBRE);
        SaisieSession session = ecrire(partieDe(2, avecExplication, libre("item-1", "5")), "7");

        assertThat(session.dernierVerdict().orElseThrow().explication())
                .contains("Sept, parce que trois et sept font dix.");
    }

    /**
     * ADR-0075 : le texte a voix haute de la manche courante suit l'item joue, vide quand il n'en
     * declare aucun - le cas ordinaire. VOI-8 : un refus sur la manche ne le fait pas disparaitre,
     * puisqu'il ne depend que de {@code mancheCourante}, jamais du nombre de tentatives.
     */
    @Test
    void leTexteAVoixHauteDeLaMancheSuitLItemJoueEtSurvitAUnRefus() {
        ReponseAEcrire avecVoix = new ReponseAEcrire(new ItemId("item-0"), "Ecris le mot.", "velo",
                List.of(), Optional.empty(), Optional.empty(), ExigenceDeForme.FORME_LIBRE,
                Optional.of("Le mot est velo."));
        SaisieSession session = partieDe(2, avecVoix, libre("item-1", "7"));

        assertThat(session.texteAVoixHauteManche())
                .as("le premier item declare son texte a voix haute")
                .contains("Le mot est velo.");

        SaisieSession apresRefus = ecrire(session, "xyz");
        assertThat(apresRefus.texteAVoixHauteManche())
                .as("VOI-8 : un refus sur la meme manche ne fait pas disparaitre le texte a voix haute")
                .contains("Le mot est velo.");

        SaisieSession suivante = ecrire(apresRefus, "velo");
        assertThat(suivante.texteAVoixHauteManche())
                .as("le second item ne s'appuie sur aucune voix : le cas ordinaire")
                .isEmpty();
    }

    private static SaisieSession revelerLaPremiereManche() {
        SaisieSession session = partieDe(2, libre("item-0", "7"), libre("item-1", "5"));
        return ecrire(ecrire(ecrire(session, "1"), "2"), "3");
    }
}
