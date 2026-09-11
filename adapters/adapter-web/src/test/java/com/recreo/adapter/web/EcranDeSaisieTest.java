package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * S24, lot 4 : l'ecran de la mecanique de saisie, verifie par jsoup sur le document que le serveur
 * rend (ADR-0008, jamais par expression reguliere).
 *
 * <p>Le catalogue de test est celui de {@link PartieControllerTest} : {@code jeu-saisie} porte deux
 * items, le premier en forme libre avec une forme acceptee ("sept" pour "7"), le second en forme
 * exacte. Les conditions verifiees ici sont celles de l'arbitrage prealable de nadia-prof du
 * 2026-08-24 et de ses amendements, plus son arbitrage de {@code D5} du 2026-08-31.</p>
 *
 * <p><b>Ce que ces tests ne disent pas, et il faut le lire avec eux</b> : qu'un enfant de six ans
 * comprenne ce qu'il lit. Un test verifie qu'une chaine est a l'ecran et qu'une autre n'y est pas ;
 * la formulation de tout ce que l'enfant lit apres avoir repondu est une propriete dont nadia-prof
 * reste la seule garde, et elle la jugera en jouant.</p>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class EcranDeSaisieTest {

    private static final String JEU = PartieControllerTest.JEU_SAISIE_ID;

    @Autowired
    private MockMvc mockMvc;

    private Document demarrer(MockHttpSession session) throws Exception {
        return Jsoup.parse(mockMvc.perform(post("/parties/{jeuId}", JEU).session(session))
                .andReturn().getResponse().getContentAsString());
    }

    private Document ecrire(MockHttpSession session, String texte) throws Exception {
        return Jsoup.parse(mockMvc.perform(post("/parties/{jeuId}/reponses", JEU).session(session)
                        .param("reponseEcrite", texte))
                .andReturn().getResponse().getContentAsString());
    }

    private Document continuer(MockHttpSession session) throws Exception {
        return Jsoup.parse(mockMvc.perform(post("/parties/{jeuId}/manche", JEU).session(session))
                .andReturn().getResponse().getContentAsString());
    }

    /**
     * SEUIL-4 : un champ ou ecrire et un moyen de valider, tous deux atteignables au clavier seul -
     * un {@code <input>} et un {@code <button type="submit">} le sont nativement. SEUIL-3 : le champ
     * est un champ de texte ordinaire, donc effacable et retapable avant l'envoi.
     */
    @Test
    void uneMancheFraicheOffreUnChampOuEcrireEtUnMoyenDeValider() throws Exception {
        Document ecran = demarrer(new MockHttpSession());

        assertThat(ecran.select("input.champ-reponse")).hasSize(1);
        assertThat(ecran.select("input.champ-reponse").attr("type")).isEqualTo("text");
        assertThat(ecran.select("input.champ-reponse").attr("value")).isEmpty();
        assertThat(ecran.select("button.valider")).hasSize(1);
        assertThat(ecran.select(".enonce").text()).isEqualTo("3 + ? = 10");
        assertThat(ecran.select(".message-jeu"))
                .as("le jeu ne parle pas tant que l'enfant n'a rien envoye")
                .isEmpty();
    }

    /**
     * Le champ porte un nom accessible reel sans qu'un second texte soit ajoute a l'ecran : la
     * consigne du paquet <b>est</b> l'etiquette du champ (proposition d'ines-design, section 3.1
     * point 1).
     */
    @Test
    void laConsigneEstLEtiquetteDuChampEtAucunTexteNEstAjoute() throws Exception {
        Document ecran = demarrer(new MockHttpSession());

        assertThat(ecran.select("label.consigne").attr("for"))
                .isEqualTo(ecran.select("input.champ-reponse").attr("id"));
        assertThat(ecran.select("label.consigne").text()).isEqualTo("Ecris ta reponse.");
        assertThat(ecran.select("input.champ-reponse").hasAttr("placeholder"))
                .as("aucun placeholder : un texte fantome est gris donc sous le contraste, et il"
                        + " disparait au moment ou l'enfant en aurait besoin")
                .isFalse();
    }

    /**
     * SAI-9a : a chaque refus avant la revelation, la chaine que l'enfant a ecrite reste visible et
     * la reponse attendue n'est <b>jamais</b> nommee - ni par un mot, ni par une marque.
     */
    @Test
    void unRefusGardeLaChaineDeLEnfantEtNeNommeJamaisLaReponseAttendue() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);

        Document ecran = ecrire(session, "3");

        assertThat(ecran.select(".signal.est-faux")).isNotEmpty();
        assertThat(ecran.select("input.champ-reponse").attr("value"))
                .as("D5, variante A : la tentative refusee reste dans le champ, corrigeable")
                .isEqualTo("3");
        assertThat(ecran.select(".forme-de-reference"))
                .as("SAI-9a : la reponse attendue n'est pas nommee avant la revelation")
                .isEmpty();
        assertThat(ecran.text())
                .as("SAI-9a : ni le mot, ni sa forme acceptee ne figurent nulle part sur cet ecran")
                .doesNotContain("sept");
        assertThat(ecran.select(".indice").text())
                .as("M1 : l'indice arrive des le premier echec sur cette manche")
                .isEqualTo("Compte ceux qui manquent.");
    }

    /**
     * <b>D5, condition de nadia-prof du 2026-08-31</b> : au moment ou l'enfant peut appuyer pour
     * renvoyer, la chaine du champ doit lui etre montree <b>comme deja refusee</b>. La forme retenue
     * est celle qu'elle decrit - la meme chaine barree dans la liste au-dessus du champ. Sans elle,
     * la chaine du champ porte deux sens a la fois, "ce que tu as ecrit, refuse" et "ce que tu vas
     * envoyer", et un enfant de six ans n'en tient qu'un.
     *
     * <p><b>SAI-12, reponse du 2026-08-30 reconfirmee le 2026-08-31</b> : la liste porte
     * <b>toutes</b> les tentatives de la manche, la derniere comprise - une au deuxieme essai, deux
     * au troisieme. La description de la variante A la laissait vide au deuxieme essai, et c'est
     * l'ecart que nadia-prof a releve avant que ce lot ne parte avec.</p>
     */
    @Test
    void laListeDesTentativesPorteToutesCellesDeLaMancheDesLePremierRefus() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);

        Document apresUnRefus = ecrire(session, "3");
        assertThat(apresUnRefus.select(".tentatives .tentative-passee").eachText())
                .as("SAI-12 : la seule tentative depensee y figure, et elle est aussi dans le champ")
                .containsExactly("3");
        assertThat(apresUnRefus.select("input.champ-reponse").attr("value")).isEqualTo("3");

        Document apresDeuxRefus = ecrire(session, "4");
        assertThat(apresDeuxRefus.select(".tentatives .tentative-passee").eachText())
                .as("SAI-12 : toutes celles de la manche, dans l'ordre d'ecriture")
                .containsExactly("3", "4");
        assertThat(apresDeuxRefus.select("input.champ-reponse").attr("value")).isEqualTo("4");
    }

    /**
     * SAI-12, reponse 2 et 3 de nadia-prof du 2026-08-30 : une chaine retapee a l'identique figure
     * deux fois - deduplier reviendrait a cacher a l'enfant que sa correction n'a rien corrige - et
     * les chaines sont brutes, telles qu'il les a tapees, jamais repliees.
     */
    @Test
    void uneChaineRetapeeALIdentiqueFigureDeuxFoisEtLesChainesRestentBrutes() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);

        ecrire(session, "3");
        Document ecran = ecrire(session, "3");

        assertThat(ecran.select(".tentatives .tentative-passee").eachText())
                .containsExactly("3", "3");
    }

    /**
     * M5 et SAI-9b : au troisieme echec, le champ disparait - il n'y a plus rien a ecrire - et les
     * deux chaines sont montrees ensemble. C'est le seul moment ou la reponse attendue est nommee.
     */
    @Test
    void laRevelationRetireLeChampEtMontreLesDeuxChainesEnsemble() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);
        ecrire(session, "1");
        ecrire(session, "2");

        Document ecran = ecrire(session, "3");

        assertThat(ecran.select("input.champ-reponse"))
                .as("un champ qui subsisterait, meme desactive, resterait une zone que l'enfant tape"
                        + " sans effet")
                .isEmpty();
        assertThat(ecran.select("form.saisie")).isEmpty();
        assertThat(ecran.select("button.continuer-manche")).hasSize(1);
        assertThat(ecran.select(".chaine-jugee").text())
                .as("SAI-9b : ce que l'enfant a ecrit")
                .isEqualTo("Tu as écrit : 3.");
        assertThat(ecran.select(".forme-de-reference .forme-attendue").text())
                .as("SAI-9b : et la reponse attendue, a cote")
                .isEqualTo("7");
        assertThat(ecran.select(".tentatives .tentative-passee").eachText())
                .containsExactly("1", "2", "3");
        assertThat(ecran.select(".explication").text())
                .as("M7 : l'explication survit du cote faux avec la revelation")
                .isEqualTo("explication de s1");
    }

    /** Le geste de suite resout la manche revelee et rend la manche suivante, fraiche. */
    @Test
    void leGesteDeSuiteRendLaMancheSuivanteFraiche() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);
        for (String faux : List.of("1", "2", "3")) {
            ecrire(session, faux);
        }

        Document ecran = continuer(session);

        assertThat(ecran.select(".enonce").text()).isEqualTo("Ecris le mot");
        assertThat(ecran.select("input.champ-reponse").attr("value")).isEmpty();
        assertThat(ecran.select(".tentatives")).isEmpty();
        assertThat(ecran.select(".score").text())
                .as("M11 clause 3 : une manche resolue par revelation n'est pas une reussite")
                .isEqualTo("Score : 0");
    }

    /**
     * SAI-8 : quand une reponse est acceptee par une tolerance, le verdict est juste, le score
     * monte, <b>et</b> le retour montre la forme de reference. L'enfant qui ecrit "sept" a repondu
     * juste et apprend quand meme comment cela s'ecrit en chiffres.
     */
    @Test
    void uneReussiteParToleranceMontreLaFormeDeReference() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);

        Document ecran = ecrire(session, "sept");

        assertThat(ecran.select(".signal.est-juste")).isNotEmpty();
        assertThat(ecran.select(".score").text()).isEqualTo("Score : 1");
        assertThat(ecran.select(".forme-de-reference .forme-attendue").text()).isEqualTo("7");
        assertThat(ecran.select(".chaine-jugee"))
                .as("SAI-2c ne porte que sur un item en forme exacte ; celui-ci est en forme libre")
                .isEmpty();
    }

    /** Sur une reussite ecrite exactement, aucune tolerance n'a joue : rien de plus a montrer. */
    @Test
    void uneReussiteEcriteExactementNeMontrePasLaFormeDeReference() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);

        Document ecran = ecrire(session, "7");

        assertThat(ecran.select(".signal.est-juste")).isNotEmpty();
        assertThat(ecran.select(".forme-de-reference")).isEmpty();
    }

    /**
     * SAI-2c : sur un item en forme exacte, la chaine jugee est montree <b>aussi</b> a la reussite.
     * C'est la seule protection ecrite contre un clavier de tablette qui aurait corrige la frappe a
     * l'insu de l'enfant et du serveur : elle rend une telle defaillance visible, pas impossible.
     */
    @Test
    void uneReussiteSurUnItemEnFormeExacteMontreLaChaineJugee() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);
        ecrire(session, "7");

        Document ecran = ecrire(session, "elle");

        assertThat(ecran.select(".signal.est-juste")).isNotEmpty();
        assertThat(ecran.select(".chaine-jugee").text()).isEqualTo("Tu as écrit : elle.");
    }

    /**
     * M13 : le point de focus est declare par le serveur, jamais devine par un script. Sur une
     * manche fraiche il vaut l'enonce ; apres un refus il designe le champ, pour que l'enfant
     * corrige ce qu'il a ecrit ; a la revelation il designe le bouton de suite, seule action
     * possible.
     */
    @Test
    void lePointDeFocusEstDeclareParLeServeurSurLesTroisEtats() throws Exception {
        MockHttpSession session = new MockHttpSession();

        Document fraiche = demarrer(session);
        assertThat(fraiche.select("[data-focus]").attr("data-focus")).isEqualTo("enonce");

        Document apresUnRefus = ecrire(session, "3");
        assertThat(apresUnRefus.select("[data-focus]").attr("data-focus")).isNotEqualTo("enonce");
        assertThat(apresUnRefus.select("input.champ-reponse").attr("data-focus-cible"))
                .isEqualTo("true");

        ecrire(session, "4");
        Document revelation = ecrire(session, "5");
        assertThat(revelation.select("[data-focus]").attr("data-focus")).isNotEqualTo("enonce");
        assertThat(revelation.select("button.continuer-manche").attr("data-focus-cible"))
                .isEqualTo("true");
    }

    /**
     * ADR-0030 point 1.d : la garantie que le meuble n'atteint ni l'ecran de retour ni la fin de
     * partie tient au seul fait que son balisage n'est ecrit que dans {@code fragments/manche.html}.
     * Le recopier ici la casserait, et un ecran de saisie n'a d'ailleurs pas de colonnes a encadrer.
     */
    @Test
    void lEcranDeSaisieNeReprendNiLeCabinetNiLesPropositions() throws Exception {
        Document ecran = demarrer(new MockHttpSession());

        assertThat(ecran.select(".cabinet")).isEmpty();
        assertThat(ecran.select(".propositions")).isEmpty();
        assertThat(ecran.select(".proposition")).isEmpty();
    }

    /**
     * ADR-0020 : une partie terminee n'a plus de manche a montrer. Le controle est dans le seul point
     * de passage de l'ecran de jeu, donc il vaut pour cette mecanique-ci sans une ligne de plus.
     */
    @Test
    void unePartieDeSaisieTermineeRendLEcranDeFinDePartie() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(session);
        ecrire(session, "7");
        ecrire(session, "elle");

        Document ecran = ecrire(session, "5");

        assertThat(ecran.select("input.champ-reponse"))
                .as("le vivier est epuise : plus aucune manche a jouer")
                .isEmpty();
        // S35-SCO1-ECRAN-FIN, SCO-R1 : les trois manches sont trouvees du premier coup, les deux
        // nombres neufs de l'ecran de fin (reussites, tentatives) valent donc tous deux 3.
        assertThat(ecran.select(".score-trouve-nombre").text()).isEqualTo("3");
        assertThat(ecran.select(".score-joue-nombre").text()).isEqualTo("3");
    }

    /**
     * ADR-0011, ADR-0019 : un coup de saisie recu sans partie en cours rend l'ecran d'erreur en
     * francais, jamais une trace technique. Le meme comportement que sur l'autre mecanique, par le
     * meme chemin - c'est {@code EtatDePartieEnSession} qui refuse, pas ce controleur.
     */
    @Test
    void unCoupDeSaisieSansPartieEnCoursRendLEcranDErreur() throws Exception {
        Document ecran = ecrire(new MockHttpSession(), "7");

        assertThat(ecran.select("input.champ-reponse")).isEmpty();
        assertThat(ecran.text()).contains("Oups");
    }
}
