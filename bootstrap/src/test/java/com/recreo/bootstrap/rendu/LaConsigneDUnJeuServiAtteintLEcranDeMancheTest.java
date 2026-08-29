package com.recreo.bootstrap.rendu;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0052 : la consigne que l'entree de catalogue declare atteint l'ecran de manche de chaque jeu
 * reellement servi, et l'enonce n'y apparait jamais seul.</b>
 *
 * <p><b>Pourquoi ce test existe, et il ne double pas celui d'adapter-web.</b> La clause generale
 * d'ADR-0052 - {@code ToutEcranEchangeEstUnDocumentJouableTest} - juge le document assemble avec un
 * perimetre derive des routes du controleur, mais sa population est le <b>catalogue de test</b>
 * ({@code @WebMvcTest} plus {@code JeuDeTestConfiguration}, jeu fictif "un-jeu"). Elle prouve donc la
 * <b>chaine de rendu</b> : si le gabarit cessait de rendre la consigne, elle rougirait. Elle ne prouve
 * rien du <b>materiau servi</b> : aucun jeu du catalogue reel ne la traverse, et son compte n'a pas
 * bouge d'une unite quand l'entree {@code bandit-manchot-synonymes-cm1} est arrivee. Trouve par
 * {@code sofia-sm} en lisant le mecanisme au lieu du chiffre - troisieme instance de cette famille
 * dans ce seul sprint.
 *
 * <p><b>Ce que ce test ajoute, exactement.</b> Il fait jouer <b>chaque entree du catalogue reel</b> par
 * la chaine complete - contexte Spring entier, chargeur YAML reel, controleur, Thymeleaf - et verifie
 * sur le document rendu que la consigne y est, <b>non vide</b>, et qu'elle est <b>celle que l'entree
 * declare</b>. Depuis ADR-0052 la consigne peut porter la question elle-meme, la part invariante que
 * les items ne repetent plus : une consigne absente, vide, ou etrangere au jeu joue laisserait
 * l'enfant devant une phrase et trois mots. {@code bandit-manchot-synonymes-cm1} est le premier jeu
 * servi dans ce cas.
 *
 * <p><b>Ce que ce test ne fait pas.</b> Il ne mesure <b>aucune hauteur</b> et ne dit pas que la
 * consigne tient au-dessus du pli : ADR-0025 et ADR-0028 valent ici comme ailleurs, et {@code SYN-5}
 * reste sans garde-fou executable - {@code nadia-prof} en est seule garde. Il ne juge pas non plus le
 * <b>texte</b> de la consigne : qu'elle designe le bon mot, qu'un enfant la comprenne, qu'elle nomme
 * la marque exacte du mot cible ({@code SYN-6}) sont de sa competence, pas de la mienne.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : la population vient de {@code PartieUseCase.jeuxDisponibles()}, donc
 *       du catalogue reel charge par la chaine reelle - jamais d'une liste ecrite ici. Une entree neuve
 *       y entre sans que ce fichier change, et c'est precisement ce qui manquait ;</li>
 *   <li><b>derivation totale</b> : chaque entree est jouee, aucune n'est ecartee, et un catalogue vide
 *       fait echouer la clause de non-vacuite au lieu de la rendre verte a vide ;</li>
 *   <li><b>mise a l'epreuve</b> : sur <b>la sortie reelle du serveur</b>, mutee - consigne retiree,
 *       consigne videe, consigne etrangere au jeu joue. Une fixture ecrite de ma main ressemblerait a
 *       ce que j'imagine (lecon d'ADR-0015) ; l'octet que Thymeleaf vient de rendre, non.</li>
 * </ul>
 */
@SpringBootTest
@AutoConfigureMockMvc
class LaConsigneDUnJeuServiAtteintLEcranDeMancheTest {

    @Autowired
    private PartieUseCase partie;

    @Autowired
    private MockMvc mockMvc;

    /** L'espace insecable, moyen ordinaire de TYP-1 : le signe double ne peut plus partir seul. */
    private static final char ESPACE_INSECABLE = (char) 0x00A0;

    /**
     * Ramene l'espace insecable a l'espace ordinaire, <b>des deux cotes de la comparaison</b>.
     *
     * <p><b>Ce n'est pas une tolerance de confort, c'est la seule comparaison que ce garde-fou
     * puisse faire, et le motif est mesure.</b> {@code Element.text()} de jsoup (1.23.1) tient
     * {@code U+00A0} pour une espace et la <b>normalise en {@code U+0020}</b> : sur la consigne
     * "Combien ca fait&#160;?" il rend le point de code {@code 0020} devant le {@code 003F}. Le
     * document servi, lui, porte l'insecable intacte - {@code lea-dev} l'a verifie en hexadecimal
     * sur le jar reellement lance, et la mesure de jsoup ci-dessus a ete refaite sur la
     * bibliotheque telle qu'elle est, jamais supposee.
     *
     * <p><b>Sans cette normalisation, ce garde-fou serait rouge le jour ou {@code catalogue.yaml}
     * appliquerait le moyen de {@code TYP-1}</b> - une insecable devant chaque signe double - alors
     * que le serveur ferait exactement ce qu'on lui demande. Un rouge sur un correctif juste
     * apprend a defaire le correctif : c'est le garde-fou qui pousserait le depot <b>hors</b> d'une
     * condition pedagogique validee.
     *
     * <p><b>Ce que cette normalisation coute, ecrit plutot que taisu.</b> Ce garde-fou ne distingue
     * plus une insecable d'une espace ordinaire dans le texte rendu. Il ne le pouvait deja pas :
     * jsoup les a confondues avant lui. La propriete "le document servi porte bien l'insecable"
     * reste donc <b>sans garde-fou executable</b>, et son declencheur est ecrit dans ADR-0064,
     * section 7 du rapport de porte technique de s22.
     */
    private static String espacesComparables(String texte) {
        return texte.replace(ESPACE_INSECABLE, ' ');
    }

    /** Ce qui manque a un ecran de manche pour porter la consigne du jeu qu'il joue. */
    static Set<String> manquements(Document ecran, String consigneAttendue) {
        Set<String> defauts = new TreeSet<>();
        Element enonce = ecran.selectFirst(".enonce");
        if (enonce == null || enonce.text().isBlank()) {
            defauts.add("aucun enonce lisible sur cet ecran de manche");
        }
        List<Element> consignes = ecran.select(".consigne").stream()
                .filter(bloc -> !bloc.text().isBlank())
                .toList();
        if (consignes.isEmpty()) {
            defauts.add("aucune consigne lisible sur cet ecran de manche");
            return defauts;
        }
        String attendue = espacesComparables(consigneAttendue).strip();
        if (consignes.stream().noneMatch(bloc -> attendue.equals(espacesComparables(bloc.text()).strip()))) {
            defauts.add("la consigne rendue n'est pas celle que l'entree de catalogue declare :"
                    + " attendue \"" + consigneAttendue + "\", rendue \""
                    + consignes.get(0).text().strip() + "\"");
        }
        return defauts;
    }

    private Document ecranDeManche(String jeuId) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(new MockHttpSession()))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    @Test
    void chaqueJeuServiRendLaConsigneQueSonEntreeDeCatalogueDeclare() throws Exception {
        List<GameDefinition> jeux = partie.jeuxDisponibles();
        assertThat(jeux)
                .as("ADR-0003 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucun jeu")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeux) {
            for (String manquement : manquements(ecranDeManche(jeu.id()), jeu.consigne())) {
                defauts.add("jeu \"" + jeu.id() + "\" : " + manquement);
            }
        }

        assertThat(defauts)
                .as("ADR-0052 : la consigne d'une entree de catalogue atteint l'ecran de manche du jeu"
                        + " qu'elle declare. Depuis cet ADR elle peut porter la question elle-meme - la"
                        + " part invariante que les items ne repetent plus - et un ecran qui ne la"
                        + " rendrait pas laisserait l'enfant devant une phrase et trois mots, sans rien"
                        + " qui dise quoi en faire")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : la morsure sur la sortie reelle du serveur, mutee. Le premier jeu du
     * catalogue suffit - ce qui est eprouve est le predicat, pas le catalogue, et le predicat est le
     * meme pour les douze entrees.
     */
    @Test
    void unEcranDeMancheDontLaConsigneDisparaitFaitEchouerLeGardeFou() throws Exception {
        GameDefinition jeu = partie.jeuxDisponibles().get(0);
        Document ecran = ecranDeManche(jeu.id());
        assertThat(manquements(ecran, jeu.consigne()))
                .as("l'ecran reel doit etre sain avant mutation")
                .isEmpty();

        Document sansConsigne = ecran.clone();
        sansConsigne.select(".consigne").remove();
        assertThat(manquements(sansConsigne, jeu.consigne()))
                .as("une consigne retiree du document rendu doit rendre ce garde-fou rouge")
                .isNotEmpty();

        Document consigneVidee = ecran.clone();
        consigneVidee.select(".consigne").first().text("");
        assertThat(manquements(consigneVidee, jeu.consigne()))
                .as("une consigne que le modele n'a pas nourrie doit rendre ce garde-fou rouge - la"
                        + " balise reste, l'enfant ne lit rien : meme forme que l'ecran mort"
                        + " d'ADR-0020, qu'une suppression franche ne couvre pas")
                .isNotEmpty();

        Document consigneEtrangere = ecran.clone();
        consigneEtrangere.select(".consigne").first().text("Une consigne qui n'est pas la sienne");
        assertThat(manquements(consigneEtrangere, jeu.consigne()))
                .as("une consigne qui n'est pas celle de l'entree jouee doit rendre ce garde-fou"
                        + " rouge : c'est la forme qu'un croisement de jeux produirait, et l'egalite"
                        + " est le seul critere qui la voie")
                .isNotEmpty();
    }

    /**
     * <b>Angle mort de {@code TYP-1}, mesure par {@code lea-dev} le 2026-08-29 et route a
     * {@code theo-archi} : une consigne qui porte l'espace insecable de {@code TYP-1} ne doit pas
     * rendre ce garde-fou rouge.</b>
     *
     * <p>Le materiau n'est pas une fixture de mon imagination : c'est la sortie de la bibliotheque
     * telle qu'elle est. {@code Element.text()} de jsoup normalise {@code U+00A0} en {@code U+0020}
     * - mesure sur jsoup 1.23.1 - la ou le document servi porte l'insecable intacte. Sans la
     * normalisation des deux cotes, l'egalite exacte serait fausse, et ce garde-fou rougirait sur
     * un correctif juste.
     */
    @Test
    void uneConsigneQuiPorteLEspaceInsecableDeTyp1NeFaitPasRougirLeGardeFou() {
        String consigneDuCatalogue = "Combien ca fait" + ESPACE_INSECABLE + "?";
        Document ecranServi = Jsoup.parseBodyFragment(
                "<p class=\"consigne\">" + consigneDuCatalogue + "</p>"
                        + "<p class=\"enonce\">3 + 4</p>");

        assertThat(manquements(ecranServi, consigneDuCatalogue))
                .as("TYP-1 : le jour ou catalogue.yaml applique l'espace insecable, ce garde-fou doit"
                        + " rester vert. jsoup rend U+0020 la ou le serveur emet U+00A0 : comparer les"
                        + " deux formes brutes ferait rougir un correctif juste, et apprendrait a"
                        + " defaire le correctif plutot qu'a corriger le garde-fou")
                .isEmpty();
    }

    /**
     * Le contrepoids du test ci-dessus, sans lequel il ne prouverait que la moitie de ce qu'il
     * annonce : la normalisation des espaces ne doit rien laisser passer d'autre. Une consigne
     * etrangere reste refusee, insecable ou pas.
     */
    @Test
    void laNormalisationDesEspacesNeLaissePasPasserUneConsigneEtrangere() {
        String consigneDuCatalogue = "Combien ca fait" + ESPACE_INSECABLE + "?";
        Document ecranServi = Jsoup.parseBodyFragment(
                "<p class=\"consigne\">Quel mot complete la phrase" + ESPACE_INSECABLE + "?</p>"
                        + "<p class=\"enonce\">3 + 4</p>");

        assertThat(manquements(ecranServi, consigneDuCatalogue))
                .as("la normalisation porte sur l'espace insecable, et sur rien d'autre : une consigne"
                        + " qui n'est pas celle de l'entree jouee reste refusee")
                .isNotEmpty();
    }
}
