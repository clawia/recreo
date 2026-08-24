package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
import java.util.ArrayList;
import java.util.List;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.copieDesFeuilles;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.designes;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.rangeesRepliables;
import static com.recreo.adapter.web.LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.sansPseudoClasses;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static com.recreo.adapter.web.ToutEcranEchangeEstUnDocumentJouableTest.zoneEchangee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0059. {@code LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest} (ADR-0054) est un
 * <b>detecteur de changement</b> : il dit qu'une declaration a bouge, jamais si la valeur nouvelle
 * vaut quelque chose. Ce fichier est le <b>critere</b> qui manquait a cote : un plafond maison pose
 * sur la chaine d'une rangee repliable doit laisser a cette rangee au moins la largeur dont
 * {@code nadia-prof} a mesure qu'elle lui est necessaire.
 *
 * <p><b>Le mode de defaillance vise, et il n'est pas theorique.</b> Le correctif de pli qui s'ouvre
 * apres la mesure du 2026-08-24 est une declaration de plafond bornee a la bande 576-719,98 px. Le
 * chemin le moins cher pour l'ecrire est de recopier la valeur que la feuille tierce sert deja au
 * palier - {@code max-width: 540px} - ou n'importe quelle valeur ronde en dessous du necessaire. La
 * table epinglee d'ADR-0054 rougirait, son auteur ajouterait deux lignes, la CI redeviendrait verte,
 * et <b>le pli resterait exactement ou il est</b> : un correctif qui ne corrige rien, livre sous une
 * CI verte. C'est ce que ce fichier refuse.
 *
 * <p><b>Les trois constantes, leur source et leur proprietaire.</b> Aucune n'est de l'invention de
 * l'architecte, et aucune n'est la valeur du correctif lui-meme - celle-la vit dans
 * {@code recreo.css} et n'est ecrite nulle part ici.
 * <ul>
 *   <li>{@code LARGEUR_DE_RANGEE_NECESSAIRE_PX} - mesure de {@code nadia-prof} du 2026-08-24 au
 *       navigateur reel, seize items sur seize de {@code cm1-synonymes}, seuil au pixel par item,
 *       maximum de la colonne ({@code FICHE-bandit-manchot-synonymes-cm1.md}, section "Mesure du
 *       2026-08-24"). Elle seule la remesure, elle seule la change.</li>
 *   <li>{@code RETRANCHEMENT_MESURE_PX} - l'arithmetique d'ADR-0054, confirmee quatre fois sur le
 *       rendu : plafond 540 -> rangee 500, plafond 720 -> rangee 680, plafond 579 -> rangee 539.
 *       Le retranchement est le rembourrage de {@code .container} (24 px) plus la bordure de
 *       {@code .cabinet} (16 px). Il n'est pas derive ici : il est mesure, et c'est ce qui le rend
 *       opposable.</li>
 *   <li>{@code RESERVE_DE_POLICE_PX} - la marge que le produit garde au-dessus du strict
 *       necessaire pour qu'une pile de polices plus large ne defasse pas le correctif.
 *       {@code nadia-prof} nomme la reserve comme sa condition ; {@code ines-design} propose et
 *       {@code camille-po} decide la tolerance. <b>Elle vaut zero tant que personne ne l'a
 *       tranchee</b>, et zero est ici la valeur qui n'affirme rien, pas une decision d'architecture.
 *       Le jour ou elle est tranchee, c'est ce nombre-ci qui change et rien d'autre.</li>
 * </ul>
 *
 * <p><b>Ce que ce garde-fou ne dit pas.</b> Il ne mesure aucune position rendue et ne dit pas qu'un
 * item tient sur une rangee (ADR-0025, ADR-0028) : il compare une <b>declaration</b> a un
 * <b>chiffre mesure ailleurs</b>. Il ne dit rien du budget vertical, dont depend le fait que le
 * verdict passe au-dessus du pli, et aucun garde-fou de ce depot n'en dit rien non plus - c'est la
 * dette que la section "Ce qui reste ouvert" d'ADR-0059 nomme. Il ne dit rien des viewports sous
 * {@code LARGEUR_DE_RANGEE_NECESSAIRE_PX + RETRANCHEMENT_MESURE_PX}, ou aucune declaration ne peut
 * rien : la borne {@code V - 40} est une propriete du viewport, pas de la feuille.
 *
 * <p><b>ADR-0013, les trois obligations.</b>
 * <ul>
 *   <li><b>Perimetre derive</b> : les regles <b>maison</b> qui declarent un plafond lateral sur un
 *       element qui est une rangee repliable ou l'ancetre d'une rangee repliable, la population des
 *       rangees etant celle qu'ADR-0054 et ADR-0055 derivent deja ({@code flex-wrap} autre que
 *       {@code nowrap}). Aucun nom de classe, aucun seuil de media, aucune bande n'est ecrit ici.
 *       La feuille tierce est hors perimetre : son escalier n'est pas notre decision, et ADR-0054
 *       le garde deja contre un changement muet.</li>
 *   <li><b>Derivation totale</b> : un plafond exprime autrement qu'en pixels est un <b>echec</b>,
 *       jamais un cas ignore - ce garde-fou ne saurait pas dire ce qu'il laisse a la rangee. Zero
 *       plafond maison examine est un echec aussi (ADR-0051) : la population vide voudrait dire que
 *       le correctif de S18 a disparu, et une regle qui n'examine rien passerait verte a vide.</li>
 *   <li><b>Mise a l'epreuve</b> : quatre morsures sur le materiau reel - la feuille maison servie -
 *       dont la paire au pixel qui prouve que le plancher est bien le critere et non un ordre de
 *       grandeur, <b>plus</b> un jeu fautif commite dont aucun nom ne coincide avec ceux de la
 *       production, en un cas vert et un cas rouge : les quatre premieres prouvent que la regle mord
 *       sur des gestes reels, le jeu renomme prouve que la derivation est derivee.</li>
 * </ul>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class UnPlafondMaisonNAffamePasLaRangeeTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** Le jeu du catalogue de test dont une manche rend le cabinet et ses propositions. */
    private static final String JEU_ID = "un-jeu";

    /** La propriete par laquelle une regle maison plafonne la largeur offerte a ce qu'elle contient. */
    private static final String PLAFOND = "max-width";

    /**
     * La largeur de rangee dont les seize items de {@code cm1-synonymes} ont besoin pour tenir chacun
     * sur une seule rangee. Mesuree par {@code nadia-prof} le 2026-08-24, pas derivee ici.
     */
    private static final int LARGEUR_DE_RANGEE_NECESSAIRE_PX = 539;

    /** Ce que la chaine retranche entre le plafond et la rangee. Mesure d'ADR-0054, reproduite quatre fois. */
    private static final int RETRANCHEMENT_MESURE_PX = 40;

    /**
     * La reserve gardee au-dessus du strict necessaire pour une pile de polices plus large.
     * Zero tant que {@code camille-po} n'a pas tranche la tolerance ; c'est cette ligne-la qui bouge
     * alors, et elle seule.
     */
    private static final int RESERVE_DE_POLICE_PX = 0;

    /** Le plancher qu'un plafond maison ne descend pas sous peine de ne rien corriger. */
    static final int PLAFOND_MINIMAL_PX =
            LARGEUR_DE_RANGEE_NECESSAIRE_PX + RETRANCHEMENT_MESURE_PX + RESERVE_DE_POLICE_PX;

    @Autowired
    private MockMvc mockMvc;

    @TempDir
    private Path tempo;

    private String corps(MockHttpServletRequestBuilder requete) throws Exception {
        return mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
    }

    /** Le document que l'enfant a sous les yeux sur une manche (ADR-0020). */
    private Document mancheAssemblee() throws Exception {
        String fragment = corps(post("/parties/{jeuId}", JEU_ID).session(new MockHttpSession()));
        Document coquille = Jsoup.parse(corps(get("/")));
        coquille.select(zoneEchangee(coquille)).html(fragment);
        return coquille;
    }

    /**
     * Vrai si cet element est une rangee repliable ou l'ancetre de l'une d'elles, donc si un plafond
     * pose sur lui borne la largeur offerte a une rangee. Comparaison par identite : {@code Element}
     * ne redefinit pas l'egalite structurelle sur laquelle ce garde-fou pourrait se tromper de noeud.
     */
    private static boolean gouverneUneRangee(Element element, List<Element> rangees) {
        for (Element rangee : rangees) {
            if (rangee == element) {
                return true;
            }
            for (Element ancetre : rangee.parents()) {
                if (ancetre == element) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Le plafond declare, en pixels. Une valeur qui n'est pas en pixels est un echec et non un cas
     * ignore : ce garde-fou ne saurait pas dire combien elle laisse a la rangee, et un plafond dont
     * personne ne sait la largeur n'est pas un correctif.
     */
    private static double enPixels(Regle regle, String valeur) {
        String texte = valeur.strip();
        String nombre = texte.endsWith("px") ? texte.substring(0, texte.length() - 2).strip() : null;
        try {
            if (nombre == null) {
                throw new NumberFormatException(texte);
            }
            return Double.parseDouble(nombre);
        } catch (NumberFormatException illisible) {
            throw new AssertionError("ADR-0059 : le plafond \"" + texte + "\" declare par "
                    + regle.origine() + " n'est pas exprime en pixels. Ce garde-fou compare une "
                    + "declaration au chiffre que nadia-prof a mesure au pixel ; une valeur qu'il ne "
                    + "sait pas convertir le laisserait passer sans rien verifier. Declarer ce "
                    + "plafond en pixels, ou rouvrir ADR-0059.", illisible);
        }
    }

    /**
     * Le critere : aucun plafond maison pose sur la chaine d'une rangee repliable ne laisse a cette
     * rangee moins que la largeur mesuree necessaire, reserve comprise.
     */
    static void verifierQuAucunPlafondMaisonNAffameUneRangee(Document document, Path racine) {
        List<Regle> maison = reglesMaison(racine);
        List<Element> rangees = rangeesRepliables(document, maison);

        List<String> examines = new ArrayList<>();
        for (Regle regle : maison) {
            int position = debutDeDeclaration(regle.corps(), PLAFOND);
            if (position < 0 || regle.selecteur().contains("::")) {
                continue;
            }
            if (designes(document, sansPseudoClasses(regle.selecteur())).stream()
                    .noneMatch(element -> gouverneUneRangee(element, rangees))) {
                continue;
            }
            String valeur = valeurDeclaree(regle.corps(), position, PLAFOND);
            examines.add(regle.origine() + " -> " + valeur);
            assertThat(enPixels(regle, valeur))
                    .as("ADR-0059 : le plafond maison declare par %s affame la rangee. Il lui laisse "
                            + "%s px une fois retranches les %d px de la chaine, la ou les seize items "
                            + "de cm1-synonymes en demandent %d (mesure de nadia-prof du 2026-08-24) "
                            + "plus %d px de reserve de police. Un plafond sous ce plancher est un "
                            + "correctif qui ne corrige rien : la table d'ADR-0054 rougirait puis "
                            + "serait mise a jour, la CI redeviendrait verte, et l'enfant verrait le "
                            + "meme pli. Relever la valeur, ou faire remesurer le necessaire.",
                            regle.origine(), valeur, RETRANCHEMENT_MESURE_PX,
                            LARGEUR_DE_RANGEE_NECESSAIRE_PX, RESERVE_DE_POLICE_PX)
                    .isGreaterThanOrEqualTo(PLAFOND_MINIMAL_PX);
        }

        assertThat(examines)
                .as("ADR-0051 : aucun plafond maison ne borne la largeur d'une rangee dans ce "
                        + "document, donc ce garde-fou n'examine aucun objet et ne prouve rien. La "
                        + "feuille maison en declare un depuis S18 (SYN-11) : zero veut dire qu'il a "
                        + "ete retire, et le retirer est une decision de forme qui appartient a "
                        + "nadia-prof, pas un effet de bord de refactorisation.")
                .isNotEmpty();
    }

    private static void muter(Path racine, String chemin, String avant, String apres) {
        muterUneSeuleFois(racine.resolve(chemin), avant, apres);
    }

    @Test
    void aucunPlafondMaisonDeLaProductionNAffameUneRangee() throws Exception {
        verifierQuAucunPlafondMaisonNAffameUneRangee(mancheAssemblee(), RACINE_PRODUCTION);
    }

    /**
     * Morsure 1, la faute la plus vraisemblable et celle que ce fichier existe pour attraper : le
     * correctif recopie la valeur que la feuille tierce sert deja au palier. Elle se lit comme un
     * geste prudent - "la meme valeur que la bibliotheque" - et elle laisse 500 px a une rangee qui
     * en demande 539.
     */
    @Test
    void unPlafondMaisonRecopieDeLaValeurTierceAffameLaRangee() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("plafond-recopie"));
        muter(racine, "static/css/recreo.css", "max-width: 720px;", "max-width: 540px;");

        assertThatThrownBy(() -> verifierQuAucunPlafondMaisonNAffameUneRangee(mancheAssemblee(), racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("affame la rangee");
    }

    /**
     * Morsure 2 : le plancher est un critere au pixel, pas un ordre de grandeur. Un pixel sous le
     * plancher rougit, le plancher exact passe. C'est la paire qui prouve que la valeur de la
     * constante est bien ce que ce garde-fou compare - sans elle, une regle qui refuserait tout
     * plafond inferieur a 300 px rendrait le meme vert sur la production.
     */
    @Test
    void laBorneDuPlancherEstAuPixel() throws Exception {
        Path sousLePlancher = copieDesFeuilles(tempo.resolve("un-pixel-sous"));
        muter(sousLePlancher, "static/css/recreo.css", "max-width: 720px;",
                "max-width: " + (PLAFOND_MINIMAL_PX - 1) + "px;");
        assertThatThrownBy(() ->
                verifierQuAucunPlafondMaisonNAffameUneRangee(mancheAssemblee(), sousLePlancher))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("affame la rangee");

        Path auPlancher = copieDesFeuilles(tempo.resolve("au-plancher"));
        muter(auPlancher, "static/css/recreo.css", "max-width: 720px;",
                "max-width: " + PLAFOND_MINIMAL_PX + "px;");
        assertThatCode(() -> verifierQuAucunPlafondMaisonNAffameUneRangee(mancheAssemblee(), auPlancher))
                .doesNotThrowAnyException();
    }

    /**
     * Morsure 3 : un plafond exprime dans une unite que ce garde-fou ne convertit pas. Il ne s'agit
     * pas d'une faute de frappe - {@code rem} est l'unite que cette feuille emploie partout ailleurs,
     * donc l'ecrire ici est le geste naturel de quelqu'un qui suit le style du fichier. Ce que la
     * morsure prouve est que ce cas-la est un echec et non un cas saute en silence.
     */
    @Test
    void unPlafondMaisonQuiNEstPasEnPixelsFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("plafond-en-rem"));
        muter(racine, "static/css/recreo.css", "max-width: 720px;", "max-width: 45rem;");

        assertThatThrownBy(() -> verifierQuAucunPlafondMaisonNAffameUneRangee(mancheAssemblee(), racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas exprime en pixels");
    }

    /**
     * Morsure 4, ADR-0051 : tous les plafonds maison retires. Sans cette clause, une refactorisation
     * qui emporte la declaration de S18 rendrait ce garde-fou vert a vide - il n'examinerait plus
     * rien et le dirait en passant.
     */
    @Test
    void leRetraitDeToutPlafondMaisonFaitEchouerLeGardeFou() throws Exception {
        Path racine = copieDesFeuilles(tempo.resolve("plafond-retire"));
        muter(racine, "static/css/recreo.css", "        max-width: 720px;\n", "");

        assertThatThrownBy(() -> verifierQuAucunPlafondMaisonNAffameUneRangee(mancheAssemblee(), racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'examine aucun objet");
    }

    /**
     * ADR-0013 obligation 3, dans la forme que {@code LesGardeFousSontMisALEpreuveTest} verifie : un
     * jeu fautif commite, sous {@code src/test/resources/contreexemple/<NomDeLaClasse>}. Les quatre
     * morsures ci-dessus mutent le materiau reel et prouvent que la regle mord sur des gestes reels ;
     * <b>elles ne prouvent pas que la derivation est derivee</b>, puisqu'elles travaillent toutes sur
     * les noms de la production. Ce jeu-ci le prouve : {@code .rangee-de-choix} au lieu de
     * {@code .propositions}, {@code .panneau} au lieu de {@code .zone-jeu}, une bande 620-899,98 px
     * qui n'est celle d'aucune feuille de ce depot.
     */
    private static final Path JEUX_FAUTIFS =
            Path.of("src/test/resources/contreexemple", "UnPlafondMaisonNAffamePasLaRangeeTest");

    private static Document pageDuJeuFautif(String jeu) {
        try {
            Path fichier = JEUX_FAUTIFS.resolve(jeu).resolve("templates/page.html");
            return Jsoup.parse(Files.readString(fichier), fichier.toString());
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le cas vert du jeu fautif : sous d'autres noms, la regle trouve la rangee, l'element qui porte
     * son plafond, et la valeur de ce plafond. Si ce test etait rouge, le cas rouge d'a cote ne
     * prouverait qu'une chose - que la regle reconnait les noms de la production.
     */
    @Test
    void laDerivationTrouveLePlafondSousDAutresNoms() {
        assertThatCode(() -> verifierQuAucunPlafondMaisonNAffameUneRangee(
                pageDuJeuFautif("plafond-maison-suffisant"),
                JEUX_FAUTIFS.resolve("plafond-maison-suffisant")))
                .doesNotThrowAnyException();
    }

    /**
     * Le cas rouge du meme jeu : le meme document, un plafond qui laisse 480 px a une rangee qui en
     * demande 539. Il double la morsure 1 sur du materiau qui ne partage aucun nom avec la
     * production.
     */
    @Test
    void unPlafondAvareEstRougeSousDAutresNomsAussi() {
        assertThatThrownBy(() -> verifierQuAucunPlafondMaisonNAffameUneRangee(
                pageDuJeuFautif("plafond-maison-avare"),
                JEUX_FAUTIFS.resolve("plafond-maison-avare")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("affame la rangee");
    }
}
