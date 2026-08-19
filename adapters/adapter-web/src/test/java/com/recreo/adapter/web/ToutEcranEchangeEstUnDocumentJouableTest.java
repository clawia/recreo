package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import com.recreo.domain.mechanic.SessionStatus;
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
import java.util.Optional;
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

    /** Le seul niveau du catalogue de test (PartieControllerTest.JeuDeTestConfiguration). */
    private static final String NIVEAU = "CP";

    /**
     * ADR-0034, amendement : deux manches et un contenu combinatoire (PartieControllerTest), donc une
     * partie que deux reponses fausses terminent sur un verdict faux.
     */
    private static final String JEU_INFINI_ID = "jeu-infini";

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
        return assembler(Jsoup.parse(corps(get("/"))), fragment);
    }

    /**
     * S16-6 : variante qui recoit la coquille plutot que de toujours en refaire une avec {@code
     * GET /}. Necessaire des qu'une coquille porte un etat que {@code GET /} ne porte jamais - la
     * puce de niveau active - sans quoi l'assemblage recompose un document que le navigateur n'a
     * jamais montre : un enfant qui confirme un changement de niveau vient de {@code GET
     * /niveaux/{niveau}}, dont l'en-tete porte deja la puce active de ce niveau, jamais de
     * {@code GET /}.
     */
    private Document assembler(Document coquille, String fragment) {
        coquille.select(zoneEchangee(coquille)).html(fragment);
        return coquille;
    }

    /**
     * Le corpus d'ecrans, produit en jouant une vraie partie de bout en bout puis en lisant ce que
     * les routes rendent une fois cette partie terminee. Chaque entree est un document assemble.
     *
     * <p>S10 : depuis que le cycle de manche absorbe la reponse juste comme la fausse, {@code POST
     * /parties/{jeuId}/coups} rend un ecran de manche tant que la partie continue - juste ou faux -
     * et ne rend l'ecran de retour que sur la fin de partie. {@code GET /parties/{jeuId}/manche} a
     * disparu avec son seul appelant ("Encore !") : aucun ecran de ce corpus n'y fait plus appel.</p>
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
        ecrans.add(new Ecran("manche apres une reponse juste, la partie continue",
                "POST /parties/{jeuId}/coups",
                assembler(corps(post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "1")))));
        ecrans.add(new Ecran("manche apres une reponse fausse, la partie continue",
                "POST /parties/{jeuId}/coups", assembler(corpsDUnCoupFaux())));
        ecrans.add(new Ecran("manche en revelation, la partie continue",
                "POST /parties/{jeuId}/coups", assembler(corpsDUneRevelation())));
        ecrans.add(new Ecran("retour de la derniere manche, fin de partie",
                "POST /parties/{jeuId}/coups",
                assembler(corps(post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "2")))));
        ecrans.add(new Ecran("fin de partie sur une reponse fausse", "POST /parties/{jeuId}/coups",
                assembler(corpsDUneFinDePartieFaussee())));
        ecrans.add(new Ecran("retour explicite a la liste des jeux", "POST /retour",
                assembler(corps(post("/retour").session(partie)))));
        ecrans.add(new Ecran("ecran d'erreur, partie introuvable", "POST /parties/{jeuId}/coups",
                assembler(corps(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(new MockHttpSession()).param("propositionChoisie", "1")))));

        // ADR-0038, DEC-0026 (story 2, s11) : deux routes neuves, et un etat neuf sur /retour.
        MockHttpSession partieDemandantSaSortie = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_ID).session(partieDemandantSaSortie));
        ecrans.add(new Ecran("demande de confirmation avant d'abandonner, la partie continue",
                "POST /retour",
                assembler(corps(post("/retour").session(partieDemandantSaSortie)))));
        ecrans.add(new Ecran("annulation de l'abandon, retour a la manche, la partie continue",
                "POST /retour/annuler",
                assembler(corps(post("/retour/annuler").session(partieDemandantSaSortie)))));

        MockHttpSession partieQuiConfirmeSaSortie = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_ID).session(partieQuiConfirmeSaSortie));
        corps(post("/retour").session(partieQuiConfirmeSaSortie));
        ecrans.add(new Ecran("confirmation de l'abandon, retour a la liste des jeux",
                "POST /retour/confirmer",
                assembler(corps(post("/retour/confirmer").session(partieQuiConfirmeSaSortie)))));

        // S14-1 (ADR-0043, ADR-0044) : deux routes neuves, le point d'entree par niveau.
        // S15, amendement d'ADR-0044 point 3 : ces deux entrees ne passent plus par assembler().
        // Motif ecrit, exige par ADR-0013 : la puce de niveau a perdu son hx-get (theo-archi,
        // exigences-theo-etat-actif.md, section 1.1) et GET /niveaux/{niveau} rend desormais un
        // document complet - en-tete comprise, comme GET / - sur ses deux branches. L'assembler
        // sur un document deja complet imbriquait un second <main id="jeu"> dans le #jeu de la
        // coquille (ADR-0020, "un identifiant porte deux fois"), exactement le defaut que ce
        // meme fichier existe pour attraper - c'est lui qui a mesure la regression, pas une
        // relecture.
        ecrans.add(new Ecran("liste filtree par niveau, aucune partie en cours", "GET /niveaux/{niveau}",
                Jsoup.parse(corps(get("/niveaux/{niveau}", NIVEAU)))));

        MockHttpSession partieQuiChangeDeNiveau = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_ID).session(partieQuiChangeDeNiveau));
        ecrans.add(new Ecran("demande de confirmation avant de changer de niveau, la partie continue",
                "GET /niveaux/{niveau}",
                Jsoup.parse(corps(get("/niveaux/{niveau}", NIVEAU).session(partieQuiChangeDeNiveau)))));

        MockHttpSession partieQuiConfirmeSonChangementDeNiveau = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_ID).session(partieQuiConfirmeSonChangementDeNiveau));
        // S16-6 : la coquille de cet ecran compose n'est pas GET / (par ou assembler() passe par
        // defaut) - un enfant qui confirme un changement de niveau vient de GET /niveaux/{niveau},
        // dont l'en-tete porte deja la puce active de ce niveau. C'est cette coquille-la, capturee
        // ici, que l'echange htmx suivant recompose - jamais une coquille neutre qui ne l'a jamais
        // demandee.
        Document coquilleDuNiveauConfirme = Jsoup.parse(corps(
                get("/niveaux/{niveau}", NIVEAU).session(partieQuiConfirmeSonChangementDeNiveau)));
        ecrans.add(new Ecran("confirmation du changement de niveau, retour a la liste filtree",
                "POST /niveaux/{niveau}/confirmer",
                assembler(coquilleDuNiveauConfirme, corps(post("/niveaux/{niveau}/confirmer", NIVEAU)
                        .session(partieQuiConfirmeSonChangementDeNiveau)))));

        return ecrans;
    }

    /**
     * Une reponse fausse jouee sur une partie qui continue, avec des essais restants (M5) : la
     * manche ne bouge pas, elle vit sur sa propre session.
     */
    private String corpsDUnCoupFaux() throws Exception {
        MockHttpSession partieFaussee = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_REPETE_ID).session(partieFaussee));
        return corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(partieFaussee)
                .param("propositionChoisie", PROPOSITION_FAUSSE));
    }

    /**
     * M5 : trois echecs sur la meme manche declenchent la revelation - la manche continue, sur sa
     * propre session.
     */
    private String corpsDUneRevelation() throws Exception {
        MockHttpSession session = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_REPETE_ID).session(session));
        corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                .param("propositionChoisie", PROPOSITION_FAUSSE));
        corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                .param("propositionChoisie", PROPOSITION_FAUSSE));
        return corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                .param("propositionChoisie", PROPOSITION_FAUSSE));
    }

    /**
     * ADR-0034, amendement : la derniere manche resolue par revelation, donc une partie qui se
     * termine sur un verdict faux (M11 clause 3 : une manche resolue par revelation n'est pas une
     * reussite). Le jeu infini, a hasard fixe, represente toujours la meme addition (0 + 1) - sa
     * bonne reponse, "1", est connue sans lire aucune proposition rendue. Chacune de ses deux
     * manches est resolue par trois echecs puis la reponse revelee.
     */
    private String corpsDUneFinDePartieFaussee() throws Exception {
        MockHttpSession partie = new MockHttpSession();
        corps(post("/parties/{jeuId}", JEU_INFINI_ID).session(partie));
        String derniere = "";
        for (int manche = 0; manche < 2; manche++) {
            corps(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "0"));
            corps(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "0"));
            corps(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "0"));
            derniere = corps(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                    .param("propositionChoisie", "1"));
        }
        return derniere;
    }

    /**
     * Le marqueur que le gabarit de retour ecrit sur son unique etat, lu dans le gabarit comme les
     * deux marqueurs de verdict (ADR-0015).
     *
     * <p>S10, amendement : depuis que le cycle de manche absorbe la reponse juste comme la fausse,
     * {@code fragments/retour.html} ne rend plus qu'un seul etat - la fin de partie - et n'a donc
     * plus de condition a lire pour le distinguer (la branche "la partie continue", "Encore !", a
     * disparu avec son seul appelant). Le marqueur se lit desormais sur l'element dont le texte fixe
     * est "C'est fini !" (deja un litteral stable pour ce depot : TexteAccentueDesGabaritsTest le lit
     * de la meme facon).</p>
     */
    static String marqueurDeFinDePartie() throws IOException {
        Document gabarit = Jsoup.parse(GABARIT_DE_RETOUR.toFile(), "UTF-8");
        List<String> marqueurs = new ArrayList<>();
        for (Element porteur : gabarit.getAllElements()) {
            if ("C'est fini !".equals(porteur.ownText().strip())) {
                marqueurs.add(porteur.className().strip());
            }
        }
        assertThat(marqueurs)
                .as("ADR-0034 : le marqueur de fin de partie se lit dans %s, sur l'element dont le "
                        + "texte fixe est \"C'est fini !\" - ce gabarit ne rend plus qu'un seul etat, "
                        + "donc plus aucune condition a lire pour le distinguer. Un seul est attendu ; "
                        + "si le gabarit exprime desormais la fin de partie autrement, c'est ici qu'il "
                        + "faut re-deriver la lecture", GABARIT_DE_RETOUR)
                .hasSize(1);
        return marqueurs.get(0);
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
    void leCorpusExerceChaqueCoupleDeVerdictEtDeSuite() throws Exception {
        List<Ecran> ecrans = ecrans();
        String finDePartie = marqueurDeFinDePartie();

        assertThat(SessionStatus.values())
                .as("ADR-0034 : ce garde-fou croise le verdict avec le statut de session, et il ne sait "
                        + "traduire que deux statuts en ecran - la partie continue, ou elle est finie. Un "
                        + "statut de plus doit lui apprendre quel ecran il rend, sans quoi il le "
                        + "confondrait en silence avec la partie qui continue")
                .hasSize(2);

        for (String verdict : marqueursDeVerdict()) {
            for (SessionStatus statut : SessionStatus.values()) {
                boolean termineeAttendue = statut == SessionStatus.TERMINEE;
                assertThat(ecrans)
                        .as("ADR-0034 : aucun ecran de ce corpus ne joint \"%s\" a un statut %s. Une "
                                + "clause de cette classe ne juge que les etats que le corpus produit ; "
                                + "sans cet ecran, tout ce que le serveur rend dans cet etat echappe aux "
                                + "deux clauses ci-dessus. Si cet etat n'a plus d'ecran a lui - parce que "
                                + "le verdict a cesse d'avoir le sien - c'est le marqueur de cet etat "
                                + "qu'il faut re-deriver ici, pas cette clause qu'il faut affaiblir",
                                verdict, statut)
                        .anyMatch(ecran -> !ecran.document().select("." + verdict).isEmpty()
                                && !ecran.document().select("." + finDePartie).isEmpty() == termineeAttendue);
            }
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

    /** La marque de la phrase de filtre, source du niveau qu'elle nomme (accueil.html). */
    private static final String SELECTEUR_PHRASE_FILTRE = ".filtre-niveau";

    /** La marque de la puce de niveau active, source du niveau qu'elle nomme (accueil.html). */
    private static final String SELECTEUR_PUCE_ACTIVE = ".chip-niveau.est-actif";

    /** Le prefixe que le gabarit concatene devant ${niveauActif} (accueil.html, ".filtre-niveau"). */
    private static final String PREFIXE_PHRASE_FILTRE = "Pour le ";

    /**
     * S16-6 : le niveau nomme par la phrase de filtre, sur un ecran qui en porte une. Derive du
     * texte du gabarit, jamais ecrit ici : le prefixe est celui qu'accueil.html concatene devant
     * {@code ${niveauActif}}.
     */
    private static Optional<String> niveauDeLaPhraseDeFiltre(Document document) {
        var phrases = document.select(SELECTEUR_PHRASE_FILTRE);
        assertThat(phrases.size())
                .as("un ecran ne porte au plus qu'une phrase de filtre")
                .isLessThanOrEqualTo(1);
        if (phrases.isEmpty()) {
            return Optional.empty();
        }
        String texte = phrases.first().text();
        assertThat(texte)
                .as("la phrase de filtre doit commencer par \"%s\"", PREFIXE_PHRASE_FILTRE)
                .startsWith(PREFIXE_PHRASE_FILTRE);
        return Optional.of(texte.substring(PREFIXE_PHRASE_FILTRE.length()));
    }

    /** Le niveau nomme par la puce active, sur un ecran qui en porte une. */
    private static Optional<String> niveauDeLaPuceActive(Document document) {
        var puces = document.select(SELECTEUR_PUCE_ACTIVE);
        assertThat(puces.size())
                .as("un ecran ne porte au plus qu'une puce de niveau active")
                .isLessThanOrEqualTo(1);
        return puces.isEmpty() ? Optional.empty() : Optional.of(puces.first().text());
    }

    /**
     * S16-6 (ferme S15-D, forme renforcee de theo-archi, avis de faisabilite du sprint 16) : sur
     * tout ecran <b>compose</b> par un echange htmx, le niveau nomme par la phrase de filtre et
     * celui de la puce de niveau active sont le meme, et l'un n'apparait jamais sans l'autre.
     *
     * <p>Restreint aux ecrans composes - les routes dont le verbe est {@code POST}, qui echangent
     * un fragment dans la coquille. Les deux routes {@code GET} du controleur ({@code accueil()},
     * {@code niveaux()}) rendent un document complet en un seul passage Thymeleaf, ou la puce
     * active peut legitimement porter sa classe pendant que la confirmation d'abandon remplace la
     * phrase par son propre contenu (l'apparence active reste eteinte tant que ".filtre-niveau"
     * n'est pas dans le document, amendement d'ADR-0046 s15) : une propriete distincte, deja
     * arbitree, hors du perimetre de cette clause. La distinction vient du verbe de la route, pas
     * d'un nom de route enumere : c'est la convention deja portee par le controleur lui-meme (GET
     * rend une page, POST rend un fragment), jamais une liste ecrite ici.</p>
     */
    @Test
    void surToutEcranComposeLaPhraseDeFiltreEtLaPuceActiveNommentLeMemeNiveau() throws Exception {
        for (Ecran ecran : ecrans()) {
            if (!ecran.route().startsWith("POST ")) {
                continue;
            }
            Optional<String> phrase = niveauDeLaPhraseDeFiltre(ecran.document());
            Optional<String> puce = niveauDeLaPuceActive(ecran.document());
            assertThat(phrase)
                    .as("S15-D : sur l'ecran compose \"%s\" (%s), la phrase de filtre (%s) et la "
                            + "puce de niveau active (%s) doivent nommer le meme niveau, ou etre "
                            + "toutes deux absentes", ecran.nom(), ecran.route(), phrase, puce)
                    .isEqualTo(puce);
        }
    }

    /**
     * ADR-0013 obligation 3 : mise a l'epreuve sur la sortie reelle du serveur - l'ecran compose
     * de la confirmation de changement de niveau, dont on retire la phrase de filtre qu'il porte
     * reellement. C'est exactement la forme du defaut de S15-D avant ce lot : la puce reste
     * active dans la coquille, la phrase a disparu du fragment.
     */
    @Test
    void unEcranComposeDontLaPhraseDeFiltreDisparaitFaitEchouerLaClauseS15D() throws Exception {
        Ecran ecran = ecrans().stream()
                .filter(candidat -> candidat.nom().equals(
                        "confirmation du changement de niveau, retour a la liste filtree"))
                .findFirst()
                .orElseThrow();
        Element phrase = ecran.document().selectFirst(SELECTEUR_PHRASE_FILTRE);
        assertThat(phrase).as("cet ecran doit porter la phrase de filtre avant mutation").isNotNull();
        phrase.remove();

        assertThat(niveauDeLaPhraseDeFiltre(ecran.document()))
                .as("apres mutation, la phrase est absente alors que la puce active reste presente")
                .isNotEqualTo(niveauDeLaPuceActive(ecran.document()));
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
