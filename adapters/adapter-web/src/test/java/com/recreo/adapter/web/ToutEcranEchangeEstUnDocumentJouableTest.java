package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0020 : ce que l'enfant a sous les yeux apres un echange htmx est un document assemble - la
 * coquille permanente, moins le contenu de la zone echangee, plus le fragment recu. Aucun test du
 * depot ne regardait ce document-la : chacun verifiait soit la page entiere, soit un fragment isole.
 * Les deux defauts que cet ADR ferme vivaient exactement dans cet angle mort.
 *
 * <p>Deux clauses, une par defaut constate :</p>
 * <ol>
 *   <li>aucun ecran ne porte deux fois le meme identifiant. Un document qui en porte deux rend le
 *       comportement de {@code hx-target} indefini : c'est le premier element trouve qui gagne, et
 *       rien dans la specification ne dit lequel c'est ;</li>
 *   <li>aucun ecran ne rend un paragraphe vide. Un {@code th:text} nourri d'une valeur absente
 *       produit une balise ouverte-fermee : c'est la forme exacte que prenait l'ecran mort de
 *       {@code GET /parties/{jeuId}/manche} sur une partie terminee - une consigne, un enonce vide,
 *       aucune proposition.</li>
 * </ol>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : la liste des routes vient de {@code RequestMappingHandlerMapping},
 *       pas d'une enumeration ecrite ici ; une route neuve non exercee rend ce garde-fou rouge. Le
 *       selecteur de la zone echangee est lu dans la sortie du serveur ({@code hx-target}), jamais
 *       ecrit ici ;</li>
 *   <li><b>derivation totale</b> : plusieurs cibles {@code hx-target} distinctes, ou aucune, sont
 *       des echecs, jamais des cas ignores - le garde-fou ne saurait plus quoi assembler ;</li>
 *   <li><b>mise a l'epreuve</b> : sur la sortie reelle du serveur, mutee pour reprendre exactement
 *       la forme des deux defauts mesures. Une fixture ecrite de ma main ressemblerait a ce que
 *       j'imagine (lecon d'ADR-0015) ; l'octet que Thymeleaf a rendu, non.</li>
 * </ul>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class ToutEcranEchangeEstUnDocumentJouableTest {

    /** Deux items, donc deux coups suffisent a terminer la partie (PartieControllerTest). */
    private static final String JEU_ID = "un-jeu";

    /**
     * ADR-0034 : le meme item redonne indefiniment sur une partie de dix manches
     * (PartieControllerTest). C'est le seul jeu du catalogue de test sur lequel une reponse fausse
     * laisse la partie en cours, ce que la partie a deux items ne permet pas : sa deuxieme manche est
     * aussi sa derniere.
     */
    private static final String JEU_REPETE_ID = "jeu-repete";

    /** La proposition fausse de l'item de {@code JEU_REPETE_ID} (PartieControllerTest). */
    private static final String PROPOSITION_FAUSSE = "mauvaise";

    private static final String ATTRIBUT_DE_CIBLE = "hx-target";

    /** Le gabarit qui rend le verdict, et donc la source qui gouverne ses deux marqueurs. */
    private static final Path GABARIT_DE_RETOUR =
            Path.of("src/main/resources/templates/fragments/retour.html");

    /** ADR-0013 : le jeu fautif de la lecture des marqueurs de verdict. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/ToutEcranEchangeEstUnDocumentJouableTest");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestMappingHandlerMapping routes;

    /**
     * Un ecran tel que l'enfant le voit : {@code document} est la page assemblee, pas le fragment
     * seul. {@code route} sert au recensement des routes exercees.
     */
    private record Ecran(String nom, String route, Document document) {
    }

    private String corps(MockHttpServletRequestBuilder requete) throws Exception {
        return mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
    }

    /**
     * La zone que htmx echange, lue dans la sortie du serveur. Plusieurs cibles distinctes, ou
     * aucune, sont un defaut : ce garde-fou ne saurait plus quel element remplacer, donc quel
     * document l'enfant obtient.
     */
    private static String zoneEchangee(Document coquille) {
        Set<String> cibles = new LinkedHashSet<>();
        for (Element porteur : coquille.select("[" + ATTRIBUT_DE_CIBLE + "]")) {
            cibles.add(porteur.attr(ATTRIBUT_DE_CIBLE).strip());
        }
        assertThat(cibles)
                .as("la coquille doit declarer une et une seule zone %s ; ce garde-fou assemble le "
                        + "document que l'enfant obtient, il ne peut pas le faire sur plusieurs zones "
                        + "ni sur aucune", ATTRIBUT_DE_CIBLE)
                .hasSize(1);
        return cibles.iterator().next();
    }

    /** L'assemblage que htmx fait dans le navigateur : innerHTML de la zone remplace par le fragment. */
    private Document assembler(String fragment) throws Exception {
        Document coquille = Jsoup.parse(corps(get("/")));
        coquille.select(zoneEchangee(coquille)).html(fragment);
        return coquille;
    }

    /**
     * Le corpus d'ecrans, produit en jouant une vraie partie de bout en bout puis en lisant ce que
     * les routes rendent une fois cette partie terminee. Chaque entree est un document assemble.
     */
    private List<Ecran> ecrans() throws Exception {
        List<Ecran> ecrans = new ArrayList<>();
        MockHttpSession partie = new MockHttpSession();

        ecrans.add(new Ecran("accueil, aucune partie en cours", "GET /",
                Jsoup.parse(corps(get("/")))));
        ecrans.add(new Ecran("manche 1", "POST /parties/{jeuId}",
                assembler(corps(post("/parties/{jeuId}", JEU_ID).session(partie)))));
        ecrans.add(new Ecran("accueil pendant une partie en cours (reprise)", "GET /",
                Jsoup.parse(corps(get("/").session(partie)))));
        ecrans.add(new Ecran("retour juste, la partie continue", "POST /parties/{jeuId}/coups",
                assembler(corps(post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "1")))));
        ecrans.add(new Ecran("retour faux, la partie continue", "POST /parties/{jeuId}/coups",
                assembler(corpsDUnCoupFaux())));
        ecrans.add(new Ecran("manche 2, lue apres un retour", "GET /parties/{jeuId}/manche",
                assembler(corps(get("/parties/{jeuId}/manche", JEU_ID).session(partie)))));
        ecrans.add(new Ecran("retour de la derniere manche, fin de partie",
                "POST /parties/{jeuId}/coups",
                assembler(corps(post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "2")))));
        ecrans.add(new Ecran("lecture d'une manche sur une partie terminee",
                "GET /parties/{jeuId}/manche",
                assembler(corps(get("/parties/{jeuId}/manche", JEU_ID).session(partie)))));
        ecrans.add(new Ecran("retour explicite a la liste des jeux", "POST /retour",
                assembler(corps(post("/retour").session(partie)))));
        ecrans.add(new Ecran("ecran d'erreur, partie introuvable", "POST /parties/{jeuId}/coups",
                assembler(corps(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(new MockHttpSession()).param("propositionChoisie", "1")))));

        return ecrans;
    }

    /**
     * Une reponse fausse jouee sur une partie qui continue. Elle vit sur sa propre session : la partie
     * a deux items ne peut pas porter a la fois un retour faux et un retour juste qui la laissent en
     * cours.
     */
    private String corpsDUnCoupFaux() throws Exception {
        MockHttpSession partieFaussee = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_REPETE_ID).session(partieFaussee));
        return corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(partieFaussee)
                .param("propositionChoisie", PROPOSITION_FAUSSE));
    }

    /**
     * Les deux marqueurs que le gabarit de retour ecrit selon le verdict, lus <b>dans le gabarit</b> et
     * non ecrits ici (ADR-0015 : le perimetre se derive de la source qui gouverne le rendu). Le jour ou
     * le verdict cesse de s'exprimer par une paire de litteraux sur {@code ${juste}}, ce garde-fou
     * rougit avec la marche a suivre plutot que de chercher une classe qui n'existe plus.
     */
    static List<String> marqueursDeVerdict() throws IOException {
        return marqueursDeVerdict(GABARIT_DE_RETOUR);
    }

    static List<String> marqueursDeVerdict(Path chemin) throws IOException {
        Document gabarit = Jsoup.parse(chemin.toFile(), "UTF-8");
        List<String> marqueurs = new ArrayList<>();
        for (Element porteur : gabarit.getAllElements()) {
            for (Attribute attribut : porteur.attributes()) {
                String valeur = attribut.getValue();
                if (!valeur.contains("${juste}")) {
                    continue;
                }
                String[] morceaux = valeur.split("'");
                for (int i = 1; i < morceaux.length; i += 2) {
                    marqueurs.add(morceaux[i].strip());
                }
            }
        }
        assertThat(marqueurs)
                .as("ADR-0034 : les deux marqueurs du verdict se lisent dans %s, sur l'attribut qui "
                        + "choisit entre deux litteraux selon ${juste}. Deux valeurs exactement sont "
                        + "attendues - une par verdict. Si le gabarit exprime desormais le verdict "
                        + "autrement, c'est ici qu'il faut re-deriver la lecture, pas ailleurs qu'il "
                        + "faut ecrire les classes a la main", chemin)
                .hasSize(2);
        return marqueurs;
    }

    /** Les identifiants portes plus d'une fois par le meme document, avec leur nombre d'occurrences. */
    static Map<String, Integer> identifiantsEnDouble(Document document) {
        Map<String, Integer> occurrences = new TreeMap<>();
        for (Element porteur : document.select("[id]")) {
            occurrences.merge(porteur.attr("id").strip(), 1, Integer::sum);
        }
        occurrences.values().removeIf(nombre -> nombre < 2);
        return occurrences;
    }

    /** Les paragraphes rendus sans aucun texte : une balise que le modele n'a pas nourrie. */
    static Set<String> paragraphesVides(Document document) {
        Set<String> vides = new TreeSet<>();
        for (Element paragraphe : document.select("p")) {
            if (paragraphe.text().isBlank()) {
                vides.add(paragraphe.outerHtml());
            }
        }
        return vides;
    }

    private static String identifiantDeLaZone(Document coquille) {
        String cible = zoneEchangee(coquille);
        assertThat(cible)
                .as("la zone echangee est designee par un identifiant ; ce garde-fou ne sait pas "
                        + "assembler sur un autre genre de selecteur")
                .startsWith("#");
        return cible.substring(1);
    }

    @Test
    void aucunEcranNePorteDeuxFoisLeMemeIdentifiant() throws Exception {
        for (Ecran ecran : ecrans()) {
            assertThat(identifiantsEnDouble(ecran.document()))
                    .as("ADR-0020 : sur l'ecran \"%s\" (%s), un identifiant est porte deux fois ; "
                            + "hx-target ne designe alors plus un element determine, et une aide "
                            + "technique qui suit un identifiant n'a plus de cible unique",
                            ecran.nom(), ecran.route())
                    .isEmpty();
        }
    }

    @Test
    void aucunEcranNeRendUnParagrapheVide() throws Exception {
        for (Ecran ecran : ecrans()) {
            assertThat(paragraphesVides(ecran.document()))
                    .as("ADR-0020 : sur l'ecran \"%s\" (%s), un paragraphe est rendu sans texte ; le "
                            + "modele ne l'a pas nourri, et l'enfant a devant lui la place d'une "
                            + "phrase qui manque", ecran.nom(), ecran.route())
                    .isEmpty();
        }
    }

    /**
     * ADR-0013 obligation 1 : le perimetre vient du controleur, pas de la liste ci-dessus. Une route
     * neuve que ce garde-fou n'exerce pas le rend rouge, au lieu de sortir du controle en silence.
     */
    @Test
    void chaqueRouteDuControleurEstExerceeParCeGardeFou() throws Exception {
        Set<String> exercees = new TreeSet<>();
        ecrans().forEach(ecran -> exercees.add(ecran.route()));

        assertThat(exercees)
                .as("ADR-0020 : chaque route du controleur rend un ecran a l'enfant, donc chacune "
                        + "entre dans le corpus assemble ci-dessus")
                .containsExactlyInAnyOrderElementsOf(routesDeclarees());
    }

    private Set<String> routesDeclarees() {
        Set<String> declarees = new TreeSet<>();
        routes.getHandlerMethods().forEach((information, methode) -> {
            if (!PartieController.class.equals(methode.getBeanType())) {
                return;
            }
            for (String verbe : verbes(information)) {
                for (String chemin : information.getPathPatternsCondition().getPatternValues()) {
                    declarees.add(verbe + " " + chemin);
                }
            }
        });
        assertThat(declarees)
                .as("le controleur doit declarer au moins une route, sans quoi ce recensement est vide")
                .isNotEmpty();
        return declarees;
    }

    private static Set<String> verbes(RequestMappingInfo information) {
        Set<String> verbes = new TreeSet<>();
        information.getMethodsCondition().getMethods()
                .forEach(methode -> verbes.add(methode.name()));
        assertThat(verbes)
                .as("une route sans verbe declare repond a tous les verbes ; ce garde-fou ne saurait "
                        + "pas lesquels exercer")
                .isNotEmpty();
        return verbes;
    }

    /**
     * Mise a l'epreuve de la clause 1 sur la sortie reelle du serveur : le fragment tel que
     * Thymeleaf le rend, avec l'identifiant de la zone repose sur sa racine. C'est l'etat du depot
     * la veille d'ADR-0020, octet pour octet - les trois fragments portaient {@code id="jeu"} comme
     * la zone qui les recoit.
     */
    @Test
    void unFragmentQuiReprendLIdentifiantDeLaZoneFaitEchouerLeGardeFou() throws Exception {
        String fragment = corps(post("/parties/{jeuId}", JEU_ID).session(new MockHttpSession()));
        Document coquille = Jsoup.parse(corps(get("/")));
        String identifiant = identifiantDeLaZone(coquille);

        Document racineDuFragment = Jsoup.parseBodyFragment(fragment);
        racineDuFragment.body().children().first().attr("id", identifiant);

        assertThat(identifiantsEnDouble(assembler(racineDuFragment.body().html())))
                .as("un fragment qui reprend l'identifiant de la zone qui le recoit doit rendre ce "
                        + "garde-fou rouge")
                .containsEntry(identifiant, 2);
    }

    /**
     * Mise a l'epreuve de la clause 2 sur la sortie reelle du serveur : l'enonce vide de l'ecran
     * mort, repose sur l'ecran de jeu tel que Thymeleaf le rend. C'est la forme exacte que
     * {@code GET /parties/{jeuId}/manche} rendait sur une partie terminee avant ADR-0020 - la
     * mecanique ne porte plus de manche, {@code th:text} n'a rien a ecrire.
     */
    @Test
    void unEcranDontLEnonceEstVideFaitEchouerLeGardeFou() throws Exception {
        Document manche =
                assembler(corps(post("/parties/{jeuId}", JEU_ID).session(new MockHttpSession())));
        Element enonce = manche.selectFirst(".enonce");
        assertThat(enonce).as("l'ecran de jeu porte un enonce").isNotNull();
        assertThat(enonce.tagName()).as("cet enonce est un paragraphe").isEqualTo("p");
        enonce.text("");

        assertThat(paragraphesVides(manche))
                .as("un enonce que le modele n'a pas nourri doit rendre ce garde-fou rouge")
                .isNotEmpty();
    }

    /**
     * ADR-0020, clause 2, versant positif : ce que la lecture d'une partie terminee rend a la place
     * de l'ecran mort. ADR-0019 avait ferme ce cas cote commande (un coup rejoue rend l'ecran de fin
     * de partie) ; ce chemin-ci est en lecture et l'ignorait.
     */
    @Test
    void laLectureDUneMancheSurUnePartieTermineeRendLEcranDeFinDePartie() throws Exception {
        MockHttpSession partie = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(partie));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                .param("propositionChoisie", "1"));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                .param("propositionChoisie", "2"));

        Document ecran = Jsoup.parseBodyFragment(
                corps(get("/parties/{jeuId}/manche", JEU_ID).session(partie)));

        assertThat(ecran.select(".fin-de-partie").text()).isEqualTo("C'est fini !");
        assertThat(ecran.select(".score").text())
                .as("le score de la partie terminee est celui qu'elle avait, la lecture ne le refait pas")
                .isEqualTo("Score : 2");
        assertThat(ecran.select(".proposition"))
                .as("une partie terminee ne propose plus de reponse a choisir")
                .isEmpty();
        assertThat(ecran.text())
                .as("ADR-0011 : ce n'est pas un defaut, l'ecran d'erreur n'a rien a faire ici")
                .doesNotContain("Oups ! Le jeu a un souci.");
    }

    /**
     * ADR-0034 : un perimetre derive par route ne dit rien des <b>etats</b> qu'une route rend. Les deux
     * clauses ci-dessus jugeaient neuf ecrans sans qu'aucun ne vienne d'une reponse fausse, alors que
     * {@code POST /parties/{jeuId}/coups} en rend un - la moitie du jeu, et celle ou l'enfant se
     * trompe, ne passait devant aucun automate de cette classe. Mesure a {@code b15bda1} :
     * {@code propositionChoisie=1} et {@code =2} sont les bonnes reponses des deux items du jeu de
     * test.
     *
     * <p>Le verdict est un booleen : son perimetre est total par construction, deux valeurs, sans
     * exemption possible. Les marqueurs qui les distinguent sont lus dans le gabarit.</p>
     */
    @Test
    void leCorpusExerceLesDeuxVerdicts() throws Exception {
        List<Ecran> ecrans = ecrans();

        for (String marqueur : marqueursDeVerdict()) {
            assertThat(ecrans)
                    .as("ADR-0034 : aucun ecran de ce corpus ne porte \"%s\". Une clause de cette classe "
                            + "ne juge que les etats que le corpus produit : sans cet ecran, tout ce que "
                            + "le serveur rend sur ce verdict echappe aux deux clauses ci-dessus",
                            marqueur)
                    .anyMatch(ecran -> !ecran.document().select("." + marqueur).isEmpty());
        }
    }

    /**
     * ADR-0013 obligation 3, mise a l'epreuve de la lecture des marqueurs : un gabarit qui rend encore
     * le verdict mais dont les deux noms de classe ont demenage dans le modele. La lecture n'a plus
     * rien a lire, et elle doit le dire au lieu de rendre une liste incomplete - avec une liste vide,
     * {@code leCorpusExerceLesDeuxVerdicts} passerait vert sans avoir rien verifie.
     */
    @Test
    void unGabaritQuiNExprimePlusLeVerdictParUnePaireDeMarqueursFaitEchouerLaLecture() {
        Path fautif = CONTRE_EXEMPLE
                .resolve("verdict-sans-paire-de-marqueurs/templates/fragments/retour.html");

        assertThatThrownBy(() -> marqueursDeVerdict(fautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("les deux marqueurs du verdict se lisent dans");
    }

    /** Le corpus doit vraiment contenir des ecrans a juger, sinon les deux clauses passent a vide. */
    @Test
    void leCorpusDEcransNEstPasVide() throws Exception {
        List<Ecran> ecrans = ecrans();

        assertThat(ecrans).as("aucun ecran assemble : les deux clauses ne prouveraient rien").isNotEmpty();
        assertThat(ecrans).allSatisfy(ecran -> assertThat(ecran.document().select("[id]"))
                .as("l'ecran \"%s\" ne porte aucun identifiant : la clause 1 y serait vide", ecran.nom())
                .isNotEmpty());
        assertThat(ecrans).anySatisfy(ecran -> assertThat(ecran.document().select("p"))
                .as("aucun ecran ne porte de paragraphe : la clause 2 serait vide")
                .isNotEmpty());
    }
}
