package com.recreo.bootstrap.rendu;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.content.source.ListContentSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0083, REV-1 : aucune valeur de contenu n'est enchassee dans une phrase que la mecanique
 * compose. Les mots invariants vivent dans le gabarit, la valeur voyage seule, et ce garde-fou joue
 * mal - jusqu'a la troisieme erreur - sur la population reelle des paquets servis.</b>
 *
 * <p><b>Le defaut que ce garde-fou aurait attrape.</b> {@code PartieController} composait la phrase
 * de revelation par {@code "C'est " + reponse + ". Appuie sur " + reponse + "."} et
 * {@code retour.html} composait {@code "Non, c'est " + reponse + "."}. Les deux se lisent
 * correctement quand la bonne reponse est un groupe nominal ou une valeur, et faux des qu'elle est
 * une proposition : <i>"C'est ce n'est pas un etre vivant. Appuie sur ce n'est pas un etre
 * vivant."</i> {@code nadia-prof} l'a mesure sur <b>douze items de quatre paquets</b>, verifie a
 * l'ecran sur trois d'entre eux (PASSE-TRANSVERSE-s29, section 1).
 *
 * <p><b>Pourquoi rien ne le mordait, et ce que ce fichier change.</b> La phrase n'existe dans aucun
 * fichier de contenu : aucun audit de paquet ne pouvait la voir. Elle n'apparait qu'a la
 * <b>troisieme erreur consecutive</b> : aucun test qui joue proprement ne la rencontre - ceux
 * d'{@code adapter-web} qui atteignent la revelation le font sur un catalogue de test dont la
 * reponse est le mot "bonne". Les deux moities manquaient ensemble, et c'est leur produit qui a
 * laisse le defaut traverser tous les sprints de contenu du depot.
 *
 * <p><b>ADR-0013, les trois obligations.</b>
 * <ul>
 *   <li><b>Perimetre derive</b> : la population est <b>un ecran par item reellement servi</b>. Les
 *       jeux viennent du chargeur de production ({@link YamlContentPackLoader}, l'appel meme que
 *       {@code ContenuConfiguration} fait), les items viennent du tirage de chaque
 *       {@link ContentSource} reelle. Aucun paquet, aucun item, aucun texte n'est nomme ici. Un
 *       paquet neuf entre dans la population le jour ou il entre au catalogue.</li>
 *   <li><b>Derivation totale</b> : chaque item produit un ecran de revelation et un ecran de fin,
 *       et chaque noeud de texte de ces ecrans produit une assertion. Une forme d'item qu'aucune
 *       mecanique servie ne sait jouer ({@link ZoneADesigner} aujourd'hui) fait <b>echouer</b> la
 *       construction de la population au lieu d'etre ecartee en silence.</li>
 *   <li><b>Mise a l'epreuve</b> : sur la sortie reelle du serveur, mutee par <b>l'expression
 *       fautive elle-meme</b>, mot pour mot telle qu'elle vivait dans le produit jusqu'a s29. Le
 *       rouge tombe sur des items reels, avec leur nom - jamais sur une fixture de ma main, dont
 *       ADR-0015 a etabli qu'elle ressemble a ce que son auteur imagine.</li>
 * </ul>
 *
 * <p><b>Le critere, et il tient en une phrase.</b> Tout noeud de texte servi est de l'un de ces
 * trois genres : la reponse revelee, <b>seule et entiere</b> dans son element ; un extrait litteral
 * d'un champ de l'item ou de l'entree de catalogue ; ou un mot de la mecanique, et alors
 * <b>l'emplacement qui le porte dit toujours la meme chose</b> sur toute la population. Une phrase
 * composee autour de la reponse n'est aucun des trois : elle n'est pas la reponse, elle n'est ecrite
 * dans aucun fichier, et l'emplacement qui la porte change de texte a chaque item.
 *
 * <p><b>Pourquoi l'emplacement, et non l'ecran entier.</b> Premiere ecriture de ce garde-fou :
 * l'ensemble des mots d'un ecran devait etre le meme sur toute la population. Mesure : deux faux
 * rouges, sur les seize items a voix de {@code cp-voyelle-du-son} et sur {@code cp-mot-dicte} - ces
 * ecrans-la portent en plus l'etiquette "Adulte, a voix haute :", parce que leurs items declarent un
 * texte a prononcer (ADR-0075) et que le bloc qui la porte est conditionnel. <b>La presence d'un
 * bloc depend legitimement de l'item ; c'est le texte d'un bloc present qui ne doit pas en
 * dependre.</b> La cle d'invariance est donc l'emplacement - la classe du parent, la balise et la
 * classe de l'element -, jamais l'ecran entier. Un bloc qui apparait ou disparait est libre ; un
 * bloc qui parle autrement selon l'item est rouge.
 *
 * <p><b>Ce que ce garde-fou ne garde pas, dit plutot que tu.</b> Il ne juge <b>aucun texte</b> : que
 * "Appuie sur la bonne reponse :" soit la bonne formulation pour un enfant de six ans appartient a
 * {@code nadia-prof} et a elle seule (M12 : toute variante revient devant elle). Il ne voit pas non
 * plus une composition dont les deux membres seraient invariants - "Tu as ecrit : " suivi de la
 * chaine que l'<b>enfant</b> a tapee reste invariante ici parce que ce test tape toujours la meme
 * chaine ; ce n'est pas une valeur de contenu, et SAI-2c la gouverne ailleurs. Enfin il ne couvre
 * que les deux ecrans ou la mecanique parle de la reponse : l'accueil et l'ecran de niveau, qui ne
 * servent aucun item, sont hors de sa population.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(AucunTexteServiNEnchasseLaReponseDansUnePhraseTest.UnJeuParItemServiConfiguration.class)
class AucunTexteServiNEnchasseLaReponseDansUnePhraseTest {

    /** Un paquet combinatoire ne s'epuise jamais : meme budget que PropositionsAccentueesJouablesIT. */
    private static final int BUDGET_DE_TIRAGE = 200;

    /** Une chaine qu'aucun bareme de saisie n'accepte, identique sur toute la population. */
    private static final String CHAINE_REFUSEE = "qqqq";

    private static final char ESPACE_INSECABLE = (char) 0x00A0;

    /** L'ecran de la troisieme erreur, et celui de la fin de partie qui suit. */
    private static final String ECRAN_REVELATION = "revelation";
    private static final String ECRAN_FIN_DE_PARTIE = "fin de partie";

    @Autowired
    private PartieUseCase partie;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Un ecran servi, avec ce qu'il fallait deja savoir pour le juger : la reponse que le produit y
     * revele, et les textes que le contenu declare. {@code html} est garde tel que le serveur l'a
     * rendu, pour que la mise a l'epreuve mute la sortie reelle et non une fixture.
     */
    private record EcranServi(String jeuServi, String mecanique, String ecran, String reponse,
                              String enonce, Set<String> textesDeContenu, String html) {

        String cleDInvariance() {
            return mecanique + " / " + ecran;
        }
    }

    /**
     * jsoup (1.23.1) normalise U+00A0 en U+0020 dans le texte qu'il rend : la comparaison se fait
     * donc des deux cotes sur la meme forme, sans quoi TYP-1 ferait rougir un correctif juste. Meme
     * mesure et meme motif que {@code LaConsigneDUnJeuServiAtteintLEcranDeMancheTest}.
     */
    private static String normaliser(String texte) {
        return texte.replace(ESPACE_INSECABLE, ' ').replaceAll("\\s+", " ").strip();
    }

    /**
     * <b>Le critere.</b> Rend la liste des griefs, vide quand la population est saine. Ecrit comme
     * une fonction de la population qu'on lui donne, jamais de la population reelle : c'est ce qui
     * permet a la mise a l'epreuve de lui presenter la meme matiere, mutee.
     */
    static List<String> griefs(List<EcranServi> population) {
        List<String> griefs = new ArrayList<>();
        Map<String, String> motDeChaqueEmplacement = new LinkedHashMap<>();
        Map<String, String> premierEcranDeLEmplacement = new LinkedHashMap<>();

        for (EcranServi ecran : population) {
            boolean reponseSeuleDansSonElement = false;
            int motsDeLaMecanique = 0;
            String reponse = normaliser(ecran.reponse());
            String enonce = normaliser(ecran.enonce());

            for (Element element : Jsoup.parseBodyFragment(ecran.html()).select("*")) {
                for (TextNode noeud : element.textNodes()) {
                    String texte = normaliser(noeud.text());
                    if (texte.isEmpty()) {
                        continue;
                    }
                    if (texte.equals(reponse)) {
                        reponseSeuleDansSonElement |= !"button".equals(element.tagName())
                                && normaliser(element.ownText()).equals(reponse);
                        continue;
                    }
                    if (ecran.textesDeContenu().contains(texte) || enonce.contains(texte)) {
                        continue;
                    }
                    motsDeLaMecanique++;
                    String cle = ecran.cleDInvariance() + " | " + emplacement(element);
                    String connu = motDeChaqueEmplacement.putIfAbsent(cle, texte);
                    if (connu == null) {
                        premierEcranDeLEmplacement.put(cle, ecran.jeuServi());
                    } else if (!connu.equals(texte)) {
                        griefs.add(cle + " : cet emplacement dit autre chose selon l'item joue - \""
                                + connu + "\" sur " + premierEcranDeLEmplacement.get(cle) + ", \""
                                + texte + "\" sur " + ecran.jeuServi() + ". Un texte de la mecanique"
                                + " qui varie avec la reponse est une phrase composee autour"
                                + " d'elle, et elle ne se lit juste que si la reponse a la forme"
                                + " grammaticale que cette phrase suppose (REV-1)");
                    }
                }
            }

            if (!reponseSeuleDansSonElement) {
                griefs.add(ecran.jeuServi() + " (" + ecran.ecran() + ") : aucun element hors bouton"
                        + " ne porte \"" + ecran.reponse() + "\" seule et entiere. L'enfant qui a"
                        + " echoue trois fois lit alors la reponse noyee dans une phrase, ou ne la"
                        + " lit pas du tout");
            }
            if (motsDeLaMecanique == 0) {
                griefs.add(ecran.jeuServi() + " (" + ecran.ecran() + ") : cet ecran n'adresse aucun"
                        + " mot a l'enfant, ce garde-fou n'y prouve donc rien");
            }
        }
        return griefs;
    }

    /**
     * L'emplacement d'un texte dans l'ecran servi : la classe du parent, puis la balise et la classe
     * de l'element qui le porte. Derive du document rendu, jamais ecrit ici. Le parent est retenu
     * parce qu'il distingue deux emplacements que la seule balise confondrait ; les rangs
     * (nth-child) ne le sont pas, parce qu'un bloc conditionnel absent les decale sans qu'aucun
     * texte n'ait change.
     */
    private static String emplacement(Element element) {
        Element parent = element.parent();
        return (parent == null ? "" : parent.className()) + " > " + element.tagName()
                + (element.className().isEmpty() ? "" : "." + element.className());
    }

    @Test
    void aucunEcranServiNEnchasseLaReponseDansUnePhraseDeLaMecanique() throws Exception {
        List<EcranServi> population = population();

        assertThat(griefs(population))
                .as("ADR-0083 : une bonne reponse n'est pas toujours un groupe nominal - \"ce n'est"
                        + " pas un etre vivant\" est une proposition, \"en 1789\" un complement. Un"
                        + " cadre qui l'enchasse dans une phrase suppose l'une des formes et se lit"
                        + " faux sur les autres, au moment precis ou l'enfant a echoue trois fois et"
                        + " ou le produit est cense l'aider")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, et la lecon d'ADR-0015 : la matiere est reelle, la mutation est
     * l'expression fautive telle qu'elle vivait dans le produit. Elle est appliquee a <b>toute</b>
     * la population, et le rouge nomme des paquets que ce fichier n'enumere pas.
     */
    @Test
    void leCadreQuiEnchasseLaReponseFaitRougirCeGardeFouSurDesItemsReels() throws Exception {
        List<EcranServi> population = population();
        assertThat(griefs(population)).as("la population reelle doit etre saine avant mutation")
                .isEmpty();

        List<EcranServi> populationFautive = new ArrayList<>();
        for (EcranServi ecran : population) {
            populationFautive.add(commeAvantLeCorrectif(ecran));
        }

        assertThat(griefs(populationFautive))
                .as("le cadre d'avant s29, remis sur la sortie reelle du serveur, doit rendre ce"
                        + " garde-fou rouge")
                .isNotEmpty();
    }

    /**
     * La mise a l'epreuve du second membre du critere, celui qu'une invariance seule ne couvre pas :
     * un correctif qui rendrait le cadre invariant en <b>cessant de nommer la reponse</b> laisserait
     * l'enfant devant trois boutons et aucun moyen de savoir lequel presser.
     */
    @Test
    void unEcranQuiCesseDeNommerLaReponseFaitRougirCeGardeFou() throws Exception {
        EcranServi ecran = population().get(0);
        Document sansLaReponse = Jsoup.parseBodyFragment(ecran.html());
        sansLaReponse.select(".reponse-attendue").remove();

        assertThat(griefs(List.of(avecHtml(ecran, sansLaReponse.body().html()))))
                .as("un ecran de revelation qui ne nomme plus la reponse doit rendre ce garde-fou"
                        + " rouge, meme si tous ses mots sont invariants")
                .isNotEmpty();
    }

    /**
     * Remet, sur un ecran reellement servi, le cadre que {@code PartieController} et
     * {@code retour.html} portaient jusqu'a s29 : la reponse enchassee dans la phrase, et plus aucun
     * element qui la porte seule.
     */
    private static EcranServi commeAvantLeCorrectif(EcranServi ecran) {
        Document mute = Jsoup.parseBodyFragment(ecran.html());
        Element verdict = mute.selectFirst(".verdict");
        if (verdict != null) {
            String reponse = ecran.reponse();
            verdict.text(ECRAN_REVELATION.equals(ecran.ecran())
                    ? "C'est " + reponse + ". Appuie sur " + reponse + "."
                    : "Non, c'est " + reponse + ".");
        }
        mute.select(".reponse-revelee, .reponse-attendue").remove();
        return avecHtml(ecran, mute.body().html());
    }

    private static EcranServi avecHtml(EcranServi ecran, String html) {
        return new EcranServi(ecran.jeuServi(), ecran.mecanique(), ecran.ecran(), ecran.reponse(),
                ecran.enonce(), ecran.textesDeContenu(), html);
    }

    /**
     * La population : pour chaque item servi, l'ecran de la troisieme erreur et celui de la fin de
     * partie. Chaque jeu de la configuration ne porte qu'un item et qu'une manche, ce qui rend ces
     * deux ecrans atteignables item par item - le catalogue reel, lui, tire au hasard dans tout le
     * paquet et n'en laisserait atteindre qu'une poignee.
     */
    private List<EcranServi> population() throws Exception {
        List<GameDefinition> jeux = partie.jeuxDisponibles();
        assertThat(jeux)
                .as("ADR-0003 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucun item")
                .isNotEmpty();

        List<EcranServi> population = new ArrayList<>();
        for (GameDefinition jeu : jeux) {
            Item item = premierItem(jeu.contenu());
            if (item instanceof Question question) {
                population.addAll(ecransDUneQuestion(jeu, question));
            } else if (item instanceof ReponseAEcrire aEcrire) {
                population.addAll(ecransDUneReponseAEcrire(jeu, aEcrire));
            } else if (item instanceof ZoneADesigner) {
                // ADR-0088 clause B (S33-PAL2-1, s34) : une designation ne nomme jamais de reponse en
                // texte, sur aucun de ses deux ecrans - la revelation marque la zone elle-meme
                // (data-focus-cible), jamais par un mot, et la fin de partie ne porte plus de zone du
                // tout. Ce garde-fou verifie qu'une reponse nommee n'est pas enchassee dans une
                // phrase : sans reponse nommee, il n'a rien a examiner sur ce jeu, decision et non
                // omission.
                continue;
            } else {
                throw new IllegalStateException("forme d'item qu'aucun ecran servi ne sait jouer, et"
                        + " que ce garde-fou ne sait donc pas juger : " + item.getClass().getName()
                        + " (jeu " + jeu.id() + "). ADR-0013 obligation 2 : un membre de la"
                        + " population qui ne porte pas ce dont la derivation a besoin est un echec"
                        + " du garde-fou, jamais un membre ignore");
            }
        }
        return population;
    }

    private List<EcranServi> ecransDUneQuestion(GameDefinition jeu, Question question)
            throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", jeu.id()).session(session));

        String faux = question.distracteurs().get(0);
        String revelation = null;
        for (int essai = 0; essai < 3; essai++) {
            revelation = mockMvc.perform(post("/parties/{jeuId}/coups", jeu.id())
                            .session(session).param("propositionChoisie", faux))
                    .andReturn().getResponse().getContentAsString();
        }
        String finDePartie = mockMvc.perform(post("/parties/{jeuId}/coups", jeu.id())
                        .session(session).param("propositionChoisie", question.bonneReponse()))
                .andReturn().getResponse().getContentAsString();

        Set<String> contenu = textesDeContenu(jeu);
        contenu.addAll(normalises(question.distracteurs()));
        ajouter(contenu, question.indice());
        ajouter(contenu, question.explication());
        ajouter(contenu, question.texteSupport());
        ajouter(contenu, question.illustrationTexteAlternatif());
        ajouter(contenu, question.texteAVoixHaute());
        return List.of(
                new EcranServi(jeu.id(), jeu.mecanique().value(), ECRAN_REVELATION,
                        question.bonneReponse(), question.enonce(), contenu, revelation),
                new EcranServi(jeu.id(), jeu.mecanique().value(), ECRAN_FIN_DE_PARTIE,
                        question.bonneReponse(), question.enonce(), contenu, finDePartie));
    }

    private List<EcranServi> ecransDUneReponseAEcrire(GameDefinition jeu, ReponseAEcrire item)
            throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", jeu.id()).session(session));

        String revelation = null;
        for (int essai = 0; essai < 3; essai++) {
            revelation = mockMvc.perform(post("/parties/{jeuId}/reponses", jeu.id())
                            .session(session).param("reponseEcrite", CHAINE_REFUSEE))
                    .andReturn().getResponse().getContentAsString();
        }
        String finDePartie = mockMvc.perform(post("/parties/{jeuId}/manche", jeu.id())
                        .session(session))
                .andReturn().getResponse().getContentAsString();

        Set<String> contenu = textesDeContenu(jeu);
        contenu.addAll(normalises(item.formesAcceptees()));
        ajouter(contenu, item.indice());
        ajouter(contenu, item.explication());
        ajouter(contenu, item.texteAVoixHaute());
        return List.of(
                new EcranServi(jeu.id(), jeu.mecanique().value(), ECRAN_REVELATION,
                        item.formeDeReference(), item.enonce(), contenu, revelation),
                new EcranServi(jeu.id(), jeu.mecanique().value(), ECRAN_FIN_DE_PARTIE,
                        item.formeDeReference(), item.enonce(), contenu, finDePartie));
    }

    /** Les textes que l'entree de catalogue declare, communs a tous les items du jeu. */
    private static Set<String> textesDeContenu(GameDefinition jeu) {
        Set<String> textes = new HashSet<>();
        textes.add(normaliser(jeu.titre()));
        textes.add(normaliser(jeu.consigne()));
        ajouter(textes, jeu.consigneAdulte());
        return textes;
    }

    private static void ajouter(Set<String> textes, Optional<String> champ) {
        champ.ifPresent(valeur -> textes.add(normaliser(valeur)));
    }

    private static Set<String> normalises(List<String> champs) {
        Set<String> textes = new HashSet<>();
        champs.forEach(champ -> textes.add(normaliser(champ)));
        return textes;
    }

    private static Item premierItem(ContentSource contenu) {
        return contenu.next(borne -> 0, Set.of()).orElseThrow(() -> new IllegalStateException(
                "un jeu servi dont la source ne rend aucun item : ADR-0013 obligation 2"));
    }

    /**
     * <b>Un jeu par item servi, derive du catalogue de production.</b> Le catalogue reel tire au
     * hasard dans un paquet entier : jouer ses items un par un demanderait de connaitre son tirage,
     * ce qu'aucune API du domaine n'expose et qu'il ne faut pas lui demander. Chaque item devient
     * donc un jeu d'une seule manche, qui garde l'entree de catalogue de son paquet - titre,
     * consigne, consigne adulte, mecanique, descripteur - et la clause M21 de sa source.
     *
     * <p>Le catalogue de depart vient de l'appel meme que {@code ContenuConfiguration} fait
     * ({@code new YamlContentPackLoader().games()}), et non d'une liste ecrite ici. Ce bean prend la
     * place du bean de production parce que celui-ci est un {@code ConditionalOnMissingBean} : les
     * deux ne peuvent pas coexister, et c'est le seul motif de cet appel direct.
     */
    @TestConfiguration
    static class UnJeuParItemServiConfiguration {

        @Bean
        ContentCatalogPort unJeuParItemServi() {
            List<GameDefinition> exploses = new ArrayList<>();
            for (GameDefinition jeu : new YamlContentPackLoader().games()) {
                int rang = 0;
                for (Item item : itemsDe(jeu.contenu())) {
                    exploses.add(new GameDefinition(jeu.id() + "--item-" + rang++, jeu.titre(),
                            jeu.consigne(), jeu.mecanique(), jeu.descripteur(),
                            new ListContentSource(jeu.contenu().descriptor(), List.of(item),
                                    jeu.contenu().indicesNommentLaReponse()),
                            1, jeu.consigneAdulte()));
                }
            }
            return () -> exploses;
        }

        /** Le tirage n'a plus rien a choisir - un item par jeu - mais il reste rejouable. */
        @Bean
        RandomSource hasard() {
            return borne -> 0;
        }

        /**
         * Rejoue le tirage de la mecanique pour parcourir le paquet, sans exposer d'API
         * supplementaire sur le domaine - meme geste que {@code PropositionsAccentueesJouablesIT}.
         * Un paquet enumere s'epuise avant le budget ; un paquet combinatoire s'arrete au budget, et
         * ce garde-fou juge alors les deux cents premiers items qu'il produit.
         */
        private static List<Item> itemsDe(ContentSource contenu) {
            Set<ItemId> dejaVus = new LinkedHashSet<>();
            List<Item> items = new ArrayList<>();
            for (int tirage = 0; tirage < BUDGET_DE_TIRAGE; tirage++) {
                Optional<Item> tire = contenu.next(borne -> 0, dejaVus);
                if (tire.isEmpty()) {
                    break;
                }
                items.add(tire.orElseThrow());
                dejaVus.add(tire.orElseThrow().id());
            }
            return items;
        }
    }
}
