package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesTierces;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.declarationsDe;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurALaPosition;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static com.recreo.adapter.web.ToutEcranEchangeEstUnDocumentJouableTest.zoneEchangee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0054. {@code nadia-prof} mesure au navigateur reel, le 2026-08-22, que la largeur offerte a la
 * rangee de propositions est une <b>fonction en escalier du viewport</b> : 500 px sur toute la bande
 * 576-767 px, 680 px des 768 px, la marche etant decidee par une declaration de la feuille tierce que
 * ce depot ne possede pas. Vingt-quatre pixels de viewport font passer les seize items du paquet
 * {@code cm1-synonymes} de "cinq a deux rangees" a "tous sur une rangee"
 * ({@code squad/conformite/mesure-hauteur-utile-nouvelle-cible.md}, section 2.2). <b>Aucun test de ce
 * depot n'assertionnait cette fonction</b>, et c'est le trou que ce fichier ferme.
 *
 * <p><b>Ce qui est garde ici, et ce qui ne l'est pas.</b> La largeur obtenue a un viewport donne
 * n'est pas mesurable sans navigateur (ADR-0025, ADR-0028) et ce fichier ne la mesure pas. Ce qu'il
 * epingle est la <b>chaine declarative qui la produit</b> : pour chaque rangee repliable du document
 * que l'enfant a sous les yeux, l'element qui gouverne sa largeur, l'escalier que la feuille tierce
 * declare sur cet element, et <b>tout</b> terme lateral qu'une regle - maison ou tierce, sous
 * {@code @media} comprise - declare sur un element de la chaine. Un vert ne dit pas que la rangee est
 * large ; il dit que la fonction est celle contre laquelle {@code nadia-prof} a mesure.
 *
 * <p><b>Egalite, jamais un plancher, et le motif est un fait mesure.</b> {@code nadia-prof} nomme
 * comme risque "un retrait de la classe {@code container} sur {@code #jeu}". Ce geste-la ne retrecit
 * pas la rangee, il l'<b>elargit</b> : {@code main} prend alors toute la largeur du viewport, sans le
 * plafond de 540 px. Un cliquet a plancher - "la rangee n'est jamais plus etroite qu'aujourd'hui" -
 * resterait donc vert sur le geste meme que la mesure designe, alors qu'il rend caduc chacun des seize
 * chiffres du tableau 2.3 de cette mesure. Ce que la mesure appuie n'est pas une inegalite, c'est un
 * couple (fonction, mesure) : tout changement de la fonction, dans un sens comme dans l'autre, rend la
 * mesure muette et rouvre une question pour {@code nadia-prof}. C'est le meme choix qu'ADR-0053, pour
 * la meme raison : comparer l'issue plutot qu'une inegalite qui laisse passer la moitie des facons de
 * perdre.
 *
 * <p><b>Le materiau est le document assemble, produit par le serveur.</b> La chaine traverse une
 * frontiere de fichier - {@code container} est declare dans {@code accueil.html}, la rangee vit dans
 * {@code fragments/manche.html} - et aucune lecture de gabarit isole ne la voit. Lire les deux
 * fichiers et deduire le raccord serait une inference de ma main ; l'assemblage qu'ADR-0020 encode
 * (innerHTML de la zone echangee) est celui que le navigateur fait. Les sept morsures mutent
 * ce meme materiau reel - la sortie du serveur, la feuille tierce de 227 Kio telle qu'elle est
 * publiee - jamais une fixture ecrite pour ressembler a ce que j'imagine (lecon d'ADR-0015, appliquee
 * en s05 et depuis).
 *
 * <p><b>ADR-0013, les trois obligations.</b>
 * <ul>
 *   <li><b>Perimetre derive</b> : une rangee est un element que designe une regle maison declarant
 *       {@code flex-wrap} autre que {@code nowrap} - la declaration dont j'ai ecrit en s15 qu'elle
 *       est le seul garde-fou possible du defaut de largeur, "cette declaration-la et non son effet".
 *       L'element gouvernant est derive de la feuille tierce : la classe portee par un ancetre sur
 *       laquelle une regle tierce declare {@code max-width} sous {@code @media}. Ni {@code container},
 *       ni {@code propositions}, ni aucun seuil n'est ecrit dans ce fichier.</li>
 *   <li><b>Derivation totale</b> : aucune rangee, aucun element gouvernant sur la chaine d'une rangee,
 *       plusieurs elements gouvernants sur la meme chaine, ou un selecteur que le moteur de selection
 *       ne sait pas evaluer, sont des <b>echecs</b>, jamais des cas ignores.</li>
 *   <li><b>Mise a l'epreuve</b> : sept mutations du materiau reel, decrites une par une sur leur
 *       methode, <b>plus</b> un jeu fautif commite dont aucun nom ni aucun seuil ne coincide avec
 *       ceux de la production - les sept premieres prouvent que la regle mord sur les gestes reels,
 *       le jeu renomme prouve que la derivation est derivee. Voir ADR-0054, section sur le jeu
 *       fautif commite.</li>
 * </ul>
 *
 * <p><b>Ou la chaine s'arrete, et pourquoi ce n'est pas un oubli.</b> Elle s'arrete a l'element
 * gouvernant, {@code body} et {@code html} exclus : un plafond rend sans effet ce qui est au-dessus de
 * lui, aussi longtemps que ce qui est au-dessus est au moins aussi large. Cela cesse d'etre vrai
 * <b>sous le premier seuil de l'escalier</b> (576 px ici), ou il n'y a plus de plafond du tout et ou
 * la largeur remonte jusqu'au document. Cette bande-la est exactement celle du telephone, hors du
 * perimetre du produit depuis {@code DEC-0040} (2026-08-22) ; je n'etends pas la regle pour la
 * couvrir. <b>Consequence a nommer si le perimetre revient en arriere</b> : une reference produit
 * sous 576 px sortirait de ce que ce fichier garde, et ce serait a redecider, pas a supposer.
 *
 * <p><b>Ce que la table epinglee n'est pas.</b> Elle n'est pas une exigence sur la largeur - fixer une
 * largeur de reference est une decision de {@code camille-po}, en cours au moment de ce commit. Elle
 * est le releve de ce que les declarations produisent aujourd'hui. Un lot qui change la mise en page
 * volontairement met cette table a jour <b>dans le meme commit</b>, et ce rouge-la est le seul canal
 * par lequel une remesure de {@code nadia-prof} devient due.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** Le jeu du catalogue de test dont une manche rend le cabinet et ses propositions. */
    private static final String JEU_ID = "un-jeu";

    /**
     * Les proprietes par lesquelles une declaration peut changer la largeur offerte a ce qui est a
     * l'interieur d'un element. {@code box-sizing} en fait partie et ce n'est pas un ajout par
     * prudence : c'est lui qui decide si les 24 px de rembourrage de {@code .container} sont pris
     * <b>dans</b> les 540 px du plafond ou ajoutes autour. Les 500 px mesures par {@code nadia-prof}
     * ne se derivent que sous {@code border-box}.
     */
    private static final Set<String> TERMES_LATERAUX = Set.of(
            "box-sizing",
            "width", "min-width", "max-width",
            "padding", "padding-left", "padding-right",
            "margin", "margin-left", "margin-right",
            "border", "border-width", "border-left", "border-right",
            "border-left-width", "border-right-width");

    /** La propriete qui fait d'un conteneur une rangee repliable, donc un sujet de cette regle. */
    private static final String REPLI = "flex-wrap";

    /**
     * La fonction epinglee : une ligne par (rangee, element de sa chaine, regle qui declare un terme
     * lateral sur cet element), dans l'ordre du document puis de la chaine puis des feuilles.
     *
     * <p>Les deux lignes qui portent {@code 540px} et {@code 720px} sont les seules dont une mesure au
     * navigateur reel etablit la consequence : 500 px et 680 px de rangee, mesures par
     * {@code nadia-prof} sur 160 tirages le 2026-08-22. Les trois marches au-dela (960, 1140, 1320)
     * sont derivees de la meme arithmetique, et n'ont ete mesurees par personne : leur presence ici
     * garde l'escalier entier contre un changement muet, elle n'affirme rien sur ce qu'un enfant voit
     * a ces largeurs. <b>Correction du 2026-08-23 (S18, {@code SYN-11}), exigee par la consigne
     * permanente ci-dessous</b> : la consequence de la ligne des 540 px, sur la chaine
     * {@code form.propositions <- main#jeu.container.zone-jeu}, est <b>perimee</b> sur la portion de
     * son palier ou une declaration maison l'emporte desormais (720 a 767,98 px de viewport, ce qui
     * couvre la reference de {@code DEC-0040}, 744 px) ; elle reste valable en-deca (576 a 719,98 px),
     * ou aucune declaration maison ne s'applique (SYN-14, voir {@code recreo.css}). A la reference, la
     * rangee offre desormais 680 px, mesures par {@code nadia-prof} sur une regle injectee identique
     * ({@code FICHE-bandit-manchot-synonymes-cm1.md}, arbitrage du 2026-08-23) ; la remesure sur le
     * servi, que la consigne exige, reste due et n'est pas prononcee par ce fichier.
     *
     * <p><b>Consigne permanente attachee a ces deux lignes, et non correction anticipee.</b> La
     * phrase ci-dessus est vraie aussi longtemps qu'aucune declaration <b>maison</b> ne l'emporte sur
     * l'une de ces deux lignes au palier de reference. Le jour ou une declaration maison le fait -
     * c'est ce que la variante d'{@code ines-design} pour {@code SYN-11} pose sur {@code .zone-jeu},
     * en attente d'arbitrage au moment ou j'ecris - la consequence mesuree de la ligne qu'elle
     * recouvre devient <b>perimee</b> : la ligne reste dans la table, puisque la declaration tierce
     * reste declaree, mais les 500 px que {@code nadia-prof} a mesures ne sont plus ce que le
     * navigateur rend a ce palier. <b>Cette phrase est alors a corriger dans le commit du
     * correctif</b>, avec la remesure demandee - et la table rougit precisement ici, sur les lignes
     * concernees, ce qui met le rouge et la consigne au meme endroit. Elle n'est pas corrigee
     * d'avance : ecrire aujourd'hui qu'une mesure est perimee alors qu'elle ne l'est pas serait
     * exactement la reference fausse que cette consigne existe pour eviter.
     *
     * <p><b>Ce jour est arrive (lea-dev, S18, 2026-08-23).</b> La variante d'{@code ines-design} est
     * livree ci-dessous, portee a {@code .entete-contenu} et {@code .zone-jeu} (arbitrage de
     * {@code nadia-prof} du meme jour, section 4 de son arbitrage), et scopee a 720-767,98 px plutot
     * qu'a 576-767,98 px comme la variante initialement mesuree - correction que {@code SYN-14}
     * (meme arbitrage) exige : en dessous de 720 px, un plafond maison superieur au viewport ne mord
     * plus et le conteneur perd son centrage, ce que la bande initiale aurait produit sur toute la
     * portion 576-719,98 px sans y ameliorer aucun repli mesure. La table gagne deux lignes, pas une
     * seule : {@code .entete-contenu} et {@code .zone-jeu} sont deux selecteurs distincts du meme
     * bloc, chacun sur sa propre chaine.
     */
    private static final List<String> FONCTION_EPINGLEE = List.of(
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | - | .container | width: 100%; padding-right: calc(var(--bs-gutter-x) * .5); padding-left: calc(var(--bs-gutter-x) * .5); margin-right: auto; margin-left: auto",
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | @media (min-width:576px) | .container | max-width: 540px",
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | @media (min-width:768px) | .container | max-width: 720px",
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | @media (min-width:992px) | .container | max-width: 960px",
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | @media (min-width:1200px) | .container | max-width: 1140px",
            "nav.container.entete-contenu <- nav.container.entete-contenu | bootstrap.min.css | @media (min-width:1400px) | .container | max-width: 1320px",
            "nav.container.entete-contenu <- nav.container.entete-contenu | recreo.css | - | * | box-sizing: border-box",
            "nav.container.entete-contenu <- nav.container.entete-contenu | recreo.css | @media (min-width: 720px) and (max-width: 767.98px) | .entete-contenu | max-width: 720px",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | - | * | box-sizing: border-box",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | - | .container | width: 100%; padding-right: calc(var(--bs-gutter-x) * .5); padding-left: calc(var(--bs-gutter-x) * .5); margin-right: auto; margin-left: auto",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | @media (min-width:576px) | .container | max-width: 540px",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | @media (min-width:768px) | .container | max-width: 720px",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | @media (min-width:992px) | .container | max-width: 960px",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | @media (min-width:1200px) | .container | max-width: 1140px",
            "form.propositions <- main#jeu.container.zone-jeu | bootstrap.min.css | @media (min-width:1400px) | .container | max-width: 1320px",
            "form.propositions <- main#jeu.container.zone-jeu | recreo.css | - | * | box-sizing: border-box",
            "form.propositions <- main#jeu.container.zone-jeu | recreo.css | @media (min-width: 720px) and (max-width: 767.98px) | .zone-jeu | max-width: 720px",
            "form.propositions <- div.ecran-de-jeu | bootstrap.min.css | - | * | box-sizing: border-box",
            "form.propositions <- div.ecran-de-jeu | recreo.css | - | * | box-sizing: border-box",
            "form.propositions <- div.cabinet | bootstrap.min.css | - | * | box-sizing: border-box",
            "form.propositions <- div.cabinet | recreo.css | - | * | box-sizing: border-box",
            "form.propositions <- div.cabinet | recreo.css | - | .cabinet | border: 0.5rem solid var(--recreo-entete-fond)",
            "form.propositions <- form.propositions | bootstrap.min.css | - | * | box-sizing: border-box",
            "form.propositions <- form.propositions | recreo.css | - | * | box-sizing: border-box",
            "form.propositions <- form.propositions | recreo.css | - | .propositions | margin: 0.75rem 0; padding: 0",
            "form.propositions <- form.propositions | recreo.css | - | .cabinet > .propositions | margin: 0");

    @Autowired
    private MockMvc mockMvc;

    @TempDir
    private Path tempo;

    private String corps(MockHttpServletRequestBuilder requete) throws Exception {
        return mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
    }

    /**
     * Le document que l'enfant a sous les yeux sur une manche : la coquille permanente dont la zone
     * echangee recoit le fragment que le serveur vient de rendre (ADR-0020).
     */
    private Document mancheAssemblee() throws Exception {
        String fragment = corps(post("/parties/{jeuId}", JEU_ID).session(new MockHttpSession()));
        Document coquille = Jsoup.parse(corps(get("/")));
        coquille.select(zoneEchangee(coquille)).html(fragment);
        return coquille;
    }

    /** Le nom stable d'un element du document : sa balise, son identifiant, ses classes. */
    static String etiquette(Element element) {
        StringBuilder nom = new StringBuilder(element.tagName());
        if (!element.id().isEmpty()) {
            nom.append('#').append(element.id());
        }
        for (String classe : new TreeSet<>(element.classNames())) {
            nom.append('.').append(classe);
        }
        return nom.toString();
    }

    /**
     * Le selecteur prive de ses pseudo-classes, parentheses comprises. Un selecteur conditionnel
     * designe un <b>etat</b> ou une position, pas l'element au repos que cette regle juge ; le retirer
     * plutot que d'ecarter la regle fait entrer sa forme inconditionnelle dans le perimetre, donc
     * rougir une regle qui bornerait la largeur d'un element de la chaine sous condition. Sur-collecte
     * voulue : ce garde-fou prefere exiger une decision a laisser passer une largeur conditionnelle.
     */
    static String sansPseudoClasses(String selecteur) {
        StringBuilder net = new StringBuilder();
        StringBuilder compose = new StringBuilder();
        int position = 0;
        while (position < selecteur.length()) {
            char caractere = selecteur.charAt(position);
            if (caractere == ':') {
                position = finDeLaPseudoClasse(selecteur, position);
            } else if (caractere == '[') {
                int fin = selecteur.indexOf(']', position);
                fin = fin < 0 ? selecteur.length() - 1 : fin;
                compose.append(selecteur, position, fin + 1);
                position = fin + 1;
            } else if (estCombinateur(caractere)) {
                int fin = position;
                char combinateur = ' ';
                while (fin < selecteur.length() && estCombinateur(selecteur.charAt(fin))) {
                    if (selecteur.charAt(fin) != ' ') {
                        combinateur = selecteur.charAt(fin);
                    }
                    fin++;
                }
                net.append(vide(compose)).append(combinateur);
                compose.setLength(0);
                position = fin;
            } else {
                compose.append(caractere);
                position++;
            }
        }
        return net.append(vide(compose)).toString().strip();
    }

    /**
     * Un compose vide de tout sauf de sa condition redevient le selecteur universel :
     * {@code :not(caption)} designe "n'importe quel element", et c'est ce que ce garde-fou doit
     * evaluer. Defaut trouve sur la feuille tierce reelle : sans cette conversion,
     * {@code .table>:not(caption)>*>*} rendait {@code .table>>*>*}, que le moteur de selection refuse -
     * ma propre regle echouait sur du materiau que je n'avais pas imagine.
     */
    private static String vide(StringBuilder compose) {
        return compose.isEmpty() ? "*" : compose.toString();
    }

    private static boolean estCombinateur(char caractere) {
        return caractere == ' ' || caractere == '>' || caractere == '+' || caractere == '~'
                || Character.isWhitespace(caractere);
    }

    /** La position juste apres la pseudo-classe qui commence a {@code deuxPoints}, arguments compris. */
    private static int finDeLaPseudoClasse(String selecteur, int deuxPoints) {
        int position = deuxPoints + 1;
        while (position < selecteur.length() && selecteur.charAt(position) == ':') {
            position++;
        }
        while (position < selecteur.length()
                && (Character.isLetterOrDigit(selecteur.charAt(position))
                        || selecteur.charAt(position) == '-')) {
            position++;
        }
        if (position >= selecteur.length() || selecteur.charAt(position) != '(') {
            return position;
        }
        int profondeur = 0;
        while (position < selecteur.length()) {
            char courant = selecteur.charAt(position);
            if (courant == '(') {
                profondeur++;
            } else if (courant == ')') {
                profondeur--;
                if (profondeur == 0) {
                    return position + 1;
                }
            }
            position++;
        }
        return position;
    }

    /** Les termes lateraux qu'un corps de regle declare, dans l'ordre ou son auteur les a ecrits. */
    private static List<String> termesLateraux(String corps) {
        return termesDeclares(corps, TERMES_LATERAUX);
    }

    /**
     * Les declarations de {@code proprietes} que ce corps de regle porte, dans l'ordre ou son auteur
     * les a ecrites. ADR-0055 lit le meme corps avec un autre jeu de proprietes : la lecture est la
     * meme, le jeu de proprietes est ce qui distingue la largeur offerte de la largeur demandee.
     *
     * <p><b>Correction du 2026-08-27 (s21, theo-archi), trouvee en instruisant le defaut de
     * cascade de {@code LActionNeDeclarePasMoinsQueLaSortieTest}.</b> Cette methode parcourait bien
     * chaque declaration du corps, mais relisait chaque fois la valeur par
     * {@code debutDeDeclaration}, qui rend toujours la <b>premiere</b> declaration de la propriete.
     * Une propriete declaree plusieurs fois dans un meme corps rendait donc N fois la meme valeur -
     * la premiere - et les N-1 autres n'etaient <b>jamais lues</b>. Le cas est reel et non
     * hypothetique : la feuille vendorisee declare {@code width: -webkit-max-content},
     * {@code width: -moz-max-content} puis {@code width: max-content} dans le meme corps, la
     * triplette de repli de prefixes habituelle, et cette table epinglait trois fois
     * {@code -webkit-max-content}. Un changement de la valeur reellement appliquee
     * ({@code max-content}) ne bougeait pas d'un caractere dans la table censee l'epingler.
     *
     * <p>Chaque occurrence est desormais lue <b>a sa propre position</b>. La table epinglee change
     * de contenu sans qu'un seul pixel rendu change : ce qui change, c'est ce que le garde-fou
     * voit.
     */
    static List<String> termesDeclares(String corps, Set<String> proprietes) {
        List<int[]> occurrences = new ArrayList<>();
        List<String> nomsParOccurrence = new ArrayList<>();
        for (String propriete : proprietes) {
            for (int position : declarationsDe(corps, propriete)) {
                occurrences.add(new int[] {position, nomsParOccurrence.size()});
                nomsParOccurrence.add(propriete);
            }
        }
        occurrences.sort((gauche, droite) -> Integer.compare(gauche[0], droite[0]));
        List<String> termes = new ArrayList<>();
        for (int[] occurrence : occurrences) {
            String propriete = nomsParOccurrence.get(occurrence[1]);
            termes.add(propriete + ": " + valeurALaPosition(corps, occurrence[0], propriete));
        }
        return termes;
    }


    /** Vrai si cette regle declare un repli, donc si son sujet est une rangee repliable. */
    static boolean declareUnRepli(Regle regle) {
        int position = debutDeDeclaration(regle.corps(), REPLI);
        return position >= 0
                && !valeurDeclaree(regle.corps(), REPLI).strip().equals("nowrap");
    }

    /**
     * Les classes sur lesquelles une regle tierce declare {@code max-width} sous une regle-@ : les
     * seules classes dont la largeur est une fonction du viewport decidee ailleurs que chez nous.
     * Derivee de la feuille servie, jamais d'un nom ecrit ici.
     */
    private static Set<String> classesGouvernantes(Path racine) {
        Set<String> classes = new LinkedHashSet<>();
        for (Regle regle : reglesTierces(racine)) {
            if (regle.contexte().isEmpty() || debutDeDeclaration(regle.corps(), "max-width") < 0) {
                continue;
            }
            String selecteur = sansPseudoClasses(regle.selecteur());
            if (selecteur.length() > 1 && selecteur.charAt(0) == '.'
                    && selecteur.indexOf(' ') < 0 && selecteur.indexOf('>') < 0) {
                classes.add(selecteur.substring(1));
            }
        }
        return classes;
    }

    /** Les elements que ce selecteur designe dans ce document ; un selecteur illisible est un echec. */
    static List<Element> designes(Document document, String selecteur) {
        try {
            return document.select(selecteur).stream().toList();
        } catch (Selector.SelectorParseException | IllegalArgumentException illisible) {
            throw new AssertionError("selecteur que le moteur de selection ne sait pas evaluer, "
                    + "donc regle dont ce garde-fou ne peut pas dire si elle borne une largeur de la "
                    + "chaine : \"" + selecteur + "\"", illisible);
        }
    }

    /**
     * La fonction que les declarations donnent a la largeur offerte a chaque rangee repliable de ce
     * document : une ligne par (rangee, element de sa chaine, regle qui y declare un terme lateral).
     */
    static List<String> fonctionDeclaree(Document document, Path racine) {
        List<Regle> tierces = reglesTierces(racine);
        List<Regle> maison = reglesMaison(racine);
        assertThat(tierces).as("aucune regle tierce lue sous %s : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();
        assertThat(maison).as("aucune regle maison lue sous %s : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();

        Set<String> gouvernantes = classesGouvernantes(racine);
        assertThat(gouvernantes)
                .as("aucune classe tierce ne borne une largeur sous une regle-@ : ce garde-fou ne "
                        + "prouve rien, la largeur d'une rangee ne serait plus une fonction du viewport")
                .isNotEmpty();

        List<Element> rangees = rangeesRepliables(document, maison);

        List<String> lignes = new ArrayList<>();
        for (Element rangee : rangees) {
            List<Element> chaine = chaineGouvernee(rangee, gouvernantes);
            for (Element element : chaine) {
                for (Regle regle : Stream.concat(tierces.stream(), maison.stream()).toList()) {
                    List<String> termes = termesLateraux(regle.corps());
                    if (termes.isEmpty() || regle.selecteur().contains("::")) {
                        continue;
                    }
                    if (!designes(document, sansPseudoClasses(regle.selecteur())).contains(element)) {
                        continue;
                    }
                    lignes.add(etiquette(rangee) + " <- " + etiquette(element) + " | "
                            + regle.fichier() + " | "
                            + (regle.contexte().isEmpty() ? "-" : regle.contexte()) + " | "
                            + regle.selecteur() + " | " + String.join("; ", termes));
                }
            }
        }
        return lignes;
    }

    /**
     * Les rangees repliables de ce document : les elements que designe une regle maison declarant un
     * repli. Population derivee de la feuille servie, jamais d'un nom ecrit ici. ADR-0055 juge la
     * meme population : deux tables qui decriraient deux ensembles de rangees differents ne
     * s'additionneraient pas, elles se contrediraient.
     */
    static List<Element> rangeesRepliables(Document document, List<Regle> maison) {
        List<Element> rangees = new ArrayList<>();
        for (Regle regle : maison) {
            if (!declareUnRepli(regle)) {
                continue;
            }
            for (Element designe : designes(document, sansPseudoClasses(regle.selecteur()))) {
                if (!rangees.contains(designe)) {
                    rangees.add(designe);
                }
            }
        }
        assertThat(rangees)
                .as("aucune rangee repliable dans ce document : ce garde-fou ne prouve rien, la "
                        + "propriete qu'il garde n'existe que pour un conteneur qui se replie")
                .isNotEmpty();
        return rangees;
    }

    /**
     * La chaine qui porte la largeur d'une rangee : l'element gouvernant, puis chaque descendant
     * jusqu'a la rangee comprise. Zero element gouvernant signifie que la rangee prend la largeur du
     * viewport sans plafond - c'est exactement le geste que {@code nadia-prof} nomme comme risque, et
     * c'est un echec. Plusieurs signifie que ce garde-fou ne sait plus lequel gouverne, et c'est le
     * meme echec.
     */
    private static List<Element> chaineGouvernee(Element rangee, Set<String> gouvernantes) {
        List<Element> deLaRangeeVersLeHaut = new ArrayList<>();
        deLaRangeeVersLeHaut.add(rangee);
        for (Element ancetre : rangee.parents()) {
            deLaRangeeVersLeHaut.add(ancetre);
        }

        List<Element> gouvernants = deLaRangeeVersLeHaut.stream()
                .filter(element -> element.classNames().stream().anyMatch(gouvernantes::contains))
                .toList();
        assertThat(gouvernants)
                .as("la rangee repliable \"%s\" doit avoir une et une seule largeur gouvernee sur sa "
                        + "chaine d'ancetres : aucune veut dire qu'elle prend la largeur du viewport "
                        + "sans plafond, plusieurs veut dire que ce garde-fou ne sait plus laquelle "
                        + "gouverne. Dans les deux cas la fonction contre laquelle nadia-prof a mesure "
                        + "n'existe plus. Classes gouvernantes derivees de la feuille tierce : %s",
                        etiquette(rangee), gouvernantes)
                .hasSize(1);

        Element gouvernant = gouvernants.get(0);
        List<Element> chaine = new ArrayList<>();
        for (Element element : deLaRangeeVersLeHaut) {
            chaine.add(0, element);
            if (element == gouvernant) {
                break;
            }
        }
        return chaine;
    }

    static void verifierQueLaFonctionEstCelleEpinglee(Document document, Path racine,
            List<String> epinglee) {
        assertThat(fonctionDeclaree(document, racine))
                .as("la fonction qui donne la largeur offerte a une rangee repliable a change. Toute "
                        + "mesure de nadia-prof faite contre l'ancienne est muette sur la nouvelle : "
                        + "ADR-0054 exige que cette table soit mise a jour dans le commit qui change "
                        + "la mise en page, et qu'une remesure soit demandee.")
                .containsExactlyElementsOf(epinglee);
    }

    @Test
    void laFonctionDeLaProductionEstCelleEpinglee() throws Exception {
        verifierQueLaFonctionEstCelleEpinglee(mancheAssemblee(), RACINE_PRODUCTION, FONCTION_EPINGLEE);
    }

    /**
     * ADR-0059 : visibilite de paquet plutot qu'une troisieme copie. Le declencheur qu'ADR-0056 a
     * ecrit pour {@code muter} vaut ici mot pour mot - le jour ou un troisieme fichier de ce module
     * copie l'arborescence servie pour la muter, il l'emprunte au lieu de la recopier.
     */
    static Path copieDesFeuilles(Path destination) {
        try {
            Path source = RACINE_PRODUCTION;
            try (Stream<Path> arborescence = Files.walk(source)) {
                for (Path fichier : arborescence.toList()) {
                    Path cible = destination.resolve(source.relativize(fichier).toString());
                    if (Files.isDirectory(fichier)) {
                        Files.createDirectories(cible);
                    } else {
                        Files.createDirectories(cible.getParent());
                        Files.copy(fichier, cible, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            return destination;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Une mutation du materiau reel. Les sauts de ligne du motif recherche sont ramenes a ceux du
     * fichier : une fixture qui ne se retrouverait pas dans le fichier faute de terminateur de ligne
     * rendrait un test vert sans avoir rien mute, et c'est l'assertion ci-dessous qui l'attrape.
     */
    private static void muter(Path racine, String chemin, String avant, String apres) {
        muterUneSeuleFois(racine.resolve(chemin), avant, apres);
    }

    /**
     * ADR-0057 : l'implementation unique de la mutation d'un fichier de production, partagee par les
     * trois garde-fous de ce module qui mutent du materiau reel. ADR-0056 avait ecrit le declencheur
     * de cette extraction - "le jour ou un troisieme fichier de ce module mute du materiau reel, les
     * trois copies partent dans un seul endroit" - et ce jour est celui du lot de S17-C. Le geste est
     * celui de l'extraction de {@code rangeesRepliables} en ADR-0055 : l'implementation vit dans le
     * fichier ou elle est nee, les autres l'importent, et chacun garde le tour d'appel qui lui va.
     *
     * <p><b>Ce que le partage protege, et c'est le motif</b> : l'unicite d'ancrage decidee par
     * ADR-0056 est une decision, pas une commodite. En trois copies, elle pouvait deriver en trois
     * endroits ; en un seul, elle est prouvee une fois par la clause qui suit.
     */
    static void muterUneSeuleFois(Path fichier, String avant, String apres) {
        try {
            String source = Files.readString(fichier);
            String saut = source.contains("\r\n") ? "\r\n" : "\n";
            String motif = avant.replace("\n", saut);
            assertThat(occurrences(source, motif))
                    .as("ADR-0056 : une mise a l'epreuve mute l'objet qu'elle nomme, et un seul. "
                            + "L'ancrage cherche dans %s n'y apparait pas une fois mais %d, et "
                            + "String.replace reecrirait chacune : a zero la morsure ne mute rien, "
                            + "au-dela de un elle porte sur autre chose que ce qu'elle prouve, sans "
                            + "que rien ne le dise. Ancrage : %s", fichier,
                            occurrences(source, motif), motif)
                    .isEqualTo(1);
            Files.writeString(fichier, source.replace(motif, apres.replace("\n", saut)));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le nombre de fois que ce texte apparait dans cette source, sans chevauchement. Compte par
     * {@code indexOf} et non par une expression reguliere : {@code AssertionsWebTest} l'interdit dans
     * ce module (ADR-0008), et un ancrage de mutation contient des accolades et des points, qui ne
     * sont pas des caracteres neutres pour un moteur d'expressions.
     */
    private static int occurrences(String source, String texte) {
        int total = 0;
        int position = source.indexOf(texte);
        while (position >= 0) {
            total++;
            position = source.indexOf(texte, position + texte.length());
        }
        return total;
    }

    /**
     * ADR-0056, la mise a l'epreuve de la mise a l'epreuve. Elle mord sur du materiau reel : la
     * feuille maison servie declare plus d'une fois le texte cherche ici, et c'est le seul fait dont
     * ce test depend. Avant ce lot, {@code muter} se contentait de verifier que l'ancrage existait ;
     * un ancrage qui en designait plusieurs faisait donc porter la morsure sur des endroits que son
     * auteur n'avait pas nommes, en silence. Le declencheur n'est pas theorique : la declaration
     * proposee pour {@code SYN-11} ajoute un second bloc {@code .zone-jeu}, ancrage de deux des
     * morsures de ce module.
     */
    @Test
    void unAncrageDeMutationAmbiguFaitEchouerLaMiseALEpreuve() {
        Path racine = copieDesFeuilles(tempo.resolve("ancrage-ambigu"));

        assertThatThrownBy(() -> muter(racine, "static/css/recreo.css", "font-size:", "font-size:"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("et un seul");
    }

    /**
     * Morsure 1, le geste que {@code nadia-prof} nomme comme risque : la classe gouvernante retiree de
     * la zone echangee. Materiau reel - la sortie du serveur - mute exactement de ce geste-la, et par
     * les classes derivees de la feuille tierce, jamais par un nom ecrit ici. La rangee prend alors la
     * largeur du viewport, sans plafond : ses seize chiffres mesures ne veulent plus rien dire.
     */
    @Test
    void leRetraitDeLaClasseGouvernanteSurLaZoneEchangeeFaitEchouerLeGardeFou() throws Exception {
        Document mute = mancheAssemblee();
        Element zone = mute.select(zoneEchangee(mute)).first();
        classesGouvernantes(RACINE_PRODUCTION).forEach(zone::removeClass);

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mute, RACINE_PRODUCTION, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une et une seule largeur gouvernee");
    }

    /**
     * Morsure 2 : la classe gouvernante remplacee par une classe tierce qui n'a pas d'escalier. C'est
     * une faute plus vraisemblable que la precedente - la bibliotheque publie une variante pleine
     * largeur, et l'echanger se lit comme un geste anodin. La classe de remplacement est derivee de la
     * feuille tierce, pour prouver que le predicat porte sur <b>l'escalier</b> et non sur "porte une
     * classe de la bibliotheque".
     */
    @Test
    void uneClasseTierceSansEscalierNeGouverneAucuneLargeur() throws Exception {
        Document mute = mancheAssemblee();
        Element zone = mute.select(zoneEchangee(mute)).first();
        Set<String> gouvernantes = classesGouvernantes(RACINE_PRODUCTION);
        gouvernantes.forEach(zone::removeClass);
        zone.addClass(uneClasseTierceSansEscalier(gouvernantes));

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mute, RACINE_PRODUCTION, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une et une seule largeur gouvernee");
    }

    private static String uneClasseTierceSansEscalier(Set<String> gouvernantes) {
        for (Regle regle : reglesTierces(RACINE_PRODUCTION)) {
            String selecteur = regle.selecteur();
            boolean classeSeule = selecteur.length() > 1
                    && selecteur.charAt(0) == '.'
                    && selecteur.indexOf(' ', 1) < 0
                    && selecteur.indexOf('>', 1) < 0
                    && selecteur.indexOf(':', 1) < 0
                    && selecteur.indexOf('[', 1) < 0
                    && selecteur.indexOf('.', 1) < 0;
            if (classeSeule && !gouvernantes.contains(selecteur.substring(1))) {
                return selecteur.substring(1);
            }
        }
        throw new AssertionError("la feuille tierce ne declare aucune classe hors des gouvernantes : "
                + "cette mise a l'epreuve ne prouverait rien");
    }

    /**
     * Morsure 3, la seule que l'empreinte de la ressource tierce ne couvre pas. {@code ADR-0014} et
     * {@code RessourcesTiercesTest} epinglent le SHA-256 de la feuille vendorisee : une mise a jour de
     * la bibliotheque <b>ne peut pas</b> etre muette, elle rougit sur l'empreinte. Mais celui qui la
     * fait recalcule l'empreinte, la CI redevient verte, et <b>rien ne lui dit que la largeur offerte a
     * la rangee a change</b>. C'est ce que cette morsure reproduit : la feuille reelle, une seule valeur
     * de marche changee, l'empreinte supposee mise a jour. Sans cette clause, une mise a jour de
     * bibliotheque defait une mesure pedagogique sans qu'aucun automate ne l'annonce.
     */
    @Test
    void uneMarcheDeLEscalierTiersQuiChangeFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("escalier-tiers-mute"));
        muter(racine, "static/vendor/bootstrap.min.css", "max-width:540px", "max-width:320px");

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mancheAssemblee(), racine, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("max-width: 320px");
    }

    /**
     * Morsure 4 : une regle maison qui borne la largeur d'un element de la chaine. Elle passe sous
     * {@code ViewportEtLargeurTelephoneTest}, dont la population est celle des largeurs declarees
     * <b>au-dela</b> de la cible telephone : 320px n'y depasse rien, et {@code max-width} n'est pas
     * {@code width}. La premiere assertion le constate sur la feuille mutee plutot que de l'affirmer.
     *
     * <p>Ancrage etendu a {@code ".zone-jeu {\n    padding-top: 2rem;"} depuis S18 (ADR-0056) : le
     * correctif de {@code SYN-11} ajoute un second bloc {@code .zone-jeu} (sous {@code @media}), ce
     * qui rendait {@code ".zone-jeu {"} seul ambigu - la mise a l'epreuve de la mise a l'epreuve,
     * plus bas dans ce fichier, l'aurait fait rougir sans cette extension.
     */
    @Test
    void uneRegleMaisonQuiBorneLaLargeurDeLaChaineFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("borne-maison"));
        muter(racine, "static/css/recreo.css", ".zone-jeu {\n    padding-top: 2rem;",
                ".zone-jeu {\n    max-width: 320px;\n    padding-top: 2rem;");

        ViewportEtLargeurTelephoneTest.verifierAucuneLargeurFixeeAuDelaDeLaCible(racine);

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mancheAssemblee(), racine, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("max-width: 320px");
    }

    /**
     * Morsure 5, celle qui prouve que la limite nommee par {@code ADR-0046} est fermee <b>pour cette
     * propriete-ci</b> : la meme borne, nichee sous une requete de media. La lecture aplatie du module
     * ({@code blocsMaison}) attribue ce corps a la cle {@code "@media (...)"} et ne le voit jamais sur
     * {@code .zone-jeu} - la premiere assertion le constate sur la feuille mutee, elle ne le suppose
     * pas. Le balayage de ce fichier garde le contexte de regle-@ et rougit.
     *
     * <p>Ancrage etendu, meme motif et meme date que la morsure 4 ci-dessus.
     */
    @Test
    void uneBorneMaisonNicheeSousUneRequeteDeMediaFaitAussiEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("borne-maison-sous-media"));
        muter(racine, "static/css/recreo.css", ".zone-jeu {\n    padding-top: 2rem;",
                "@media (min-width: 576px) {\n    .zone-jeu { max-width: 320px; }\n}\n\n.zone-jeu {\n    padding-top: 2rem;");

        assertThat(TailleDesTextesEtBoutonsTest.blocsMaison(racine).get(".zone-jeu"))
                .as("la lecture aplatie du module ne doit pas voir cette borne : c'est la limite "
                        + "nommee par ADR-0046, et c'est ce que le balayage de ce fichier ne partage pas")
                .doesNotContain("max-width");

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mancheAssemblee(), racine, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("@media (min-width: 576px)");
    }

    /**
     * Morsure 6 : un rembourrage lateral ajoute sur un element de la chaine qui ne porte aucune classe
     * tierce. Il mange la rangee sans toucher au plafond, et
     * {@code LeConteneurTierGardeSonRembourrageLateralTest} ne le voit pas - sa population est celle des
     * elements qui combinent une classe de conteneur tiers et une classe maison, et sa faute est un
     * rembourrage remis <b>a zero</b>, pas un rembourrage ajoute. La premiere assertion le verifie sur
     * la feuille mutee plutot que de l'affirmer.
     */
    @Test
    void unRembourrageLateralAjouteSurLaChaineFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("rembourrage-ajoute"));
        muter(racine, "static/css/recreo.css", ".cabinet {", ".cabinet {\n    padding: 0 3rem;");

        LeConteneurTierGardeSonRembourrageLateralTest
                .verifierQueLeConteneurTierGardeSonRembourrageLateral(racine);

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mancheAssemblee(), racine, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("padding: 0 3rem");
    }

    /**
     * Morsure 7 : le repli retire de la rangee. Elle quitte alors la population de cette regle - une
     * rangee qui ne se replie plus deborde au lieu de passer a la ligne, et c'est le defaut
     * qu'{@code ADR-0025} declare non gardable. La fonction epinglee perd ses lignes, donc c'est rouge :
     * le seul garde-fou possible de ce mode de panne est la declaration, et elle ne peut pas disparaitre
     * en silence.
     */
    @Test
    void leRepliRetireDeLaRangeeFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("repli-retire"));
        muter(racine, "static/css/recreo.css",
                "    flex-wrap: wrap;\n    gap: 1rem;\n    margin",
                "    flex-wrap: nowrap;\n    gap: 1rem;\n    margin");

        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                mancheAssemblee(), racine, FONCTION_EPINGLEE))
                .isInstanceOf(AssertionError.class);
    }

    /**
     * ADR-0013 obligation 3, dans la forme que ce depot exige et que
     * {@code LesGardeFousSontMisALEpreuveTest} verifie : un jeu fautif commite, sous
     * {@code src/test/resources/contreexemple/<NomDeLaClasse>}. Les sept morsures ci-dessus mutent le
     * materiau reel et prouvent que la regle mord sur les gestes reels ; <b>elles ne prouvent pas que
     * la derivation est derivee</b>, puisqu'elles travaillent toutes sur les noms de la production. Ce
     * jeu-ci le prouve, et c'est la raison pour laquelle il existe : aucun de ses noms ni aucun de ses
     * seuils ne coincide avec ceux de la production - {@code .enveloppe} au lieu de {@code container},
     * {@code .colonnes} au lieu de {@code .propositions}, {@code .cadre} au lieu de {@code .cabinet},
     * deux marches a 640 et 900 px au lieu de cinq a partir de 576.
     */
    private static final Path JEUX_FAUTIFS =
            Path.of("src/test/resources/contreexemple",
                    "LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest");

    /** La fonction que le jeu fautif sain declare, epinglee comme celle de la production. */
    private static final List<String> FONCTION_DU_JEU_FAUTIF = List.of(
            "form.colonnes <- div#zone.enveloppe.large-zone | bibliotheque.css | - | * | box-sizing: border-box",
            "form.colonnes <- div#zone.enveloppe.large-zone | bibliotheque.css | - | .enveloppe | width: 100%; padding-left: 1rem; padding-right: 1rem; margin-left: auto; margin-right: auto",
            "form.colonnes <- div#zone.enveloppe.large-zone | bibliotheque.css | @media (min-width: 640px) | .enveloppe | max-width: 600px",
            "form.colonnes <- div#zone.enveloppe.large-zone | bibliotheque.css | @media (min-width: 900px) | .enveloppe | max-width: 860px",
            "form.colonnes <- div.cadre | bibliotheque.css | - | * | box-sizing: border-box",
            "form.colonnes <- div.cadre | maison.css | - | .cadre | border: 0.25rem solid #000000",
            "form.colonnes <- form.colonnes | bibliotheque.css | - | * | box-sizing: border-box",
            "form.colonnes <- form.colonnes | maison.css | - | .colonnes | margin: 0.5rem 0; padding: 0");

    private static Document pageDuJeuFautif(String jeu) {
        try {
            Path fichier = JEUX_FAUTIFS.resolve(jeu).resolve("templates/page.html");
            return Jsoup.parse(Files.readString(fichier), fichier.toString());
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le cas vert du jeu fautif : sous d'autres noms et d'autres seuils, la regle trouve la rangee, son
     * element gouvernant, son escalier a deux marches et les termes de sa chaine. Si ce test etait
     * rouge, tout ce qui precede ne prouverait qu'une chose : que la regle reconnait Bootstrap.
     */
    @Test
    void laDerivationTrouveLaChaineSousDAutresNomsEtDAutresSeuils() {
        verifierQueLaFonctionEstCelleEpinglee(pageDuJeuFautif("escalier-renomme"),
                JEUX_FAUTIFS.resolve("escalier-renomme"), FONCTION_DU_JEU_FAUTIF);
    }

    /**
     * Le cas rouge du meme jeu : la classe gouvernante retiree, sous ces autres noms. Il double la
     * morsure 1 sur du materiau qui ne partage aucun nom avec la production.
     */
    @Test
    void laChaineSansPlafondEstRougeSousDAutresNomsAussi() {
        assertThatThrownBy(() -> verifierQueLaFonctionEstCelleEpinglee(
                pageDuJeuFautif("escalier-renomme-sans-plafond"),
                JEUX_FAUTIFS.resolve("escalier-renomme-sans-plafond"), FONCTION_DU_JEU_FAUTIF))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une et une seule largeur gouvernee");
    }
}
