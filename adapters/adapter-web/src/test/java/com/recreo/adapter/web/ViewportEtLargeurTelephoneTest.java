package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S3-8, prealable seul (avis de faisabilite theo-archi, sprint 3) : accueil.html ne portait aucune
 * balise &lt;meta name="viewport"&gt;. Sans elle, un telephone rend la page dans une fenetre
 * virtuelle d'environ 980px puis reduit l'ensemble : la mise en page a la largeur de l'appareil
 * n'est jamais reellement exercee sur un vrai telephone. (375 px etait la cible produit au sprint 3 ;
 * la reference est 744 x 551 depuis DEC-0040, et le plafond de ce fichier a suivi - voir
 * {@link #LARGEUR_CIBLE_PX}.) Le reste de la story (chevauchement et depassement reellement
 * rendus) reste hors de portee, aucun moteur de rendu n'existant dans ce depot.
 *
 * <p>Perimetre derive, jamais enumere (ADR-0013) : les gabarits de page viennent du systeme de
 * fichiers sous templates/ (les fichiers directement sous ce repertoire ; ceux de
 * templates/fragments sont des fragments htmx inseres dans une page qui porte deja son propre
 * &lt;head&gt;, jamais rendus seuls, donc hors de ce perimetre par construction du repertoire et non
 * par une liste). Le balayage de largeur porte sur toutes les feuilles maison et sur tous les
 * gabarits, y compris les fragments ; ADR-0014 dit ce qui distingue une feuille maison d'une copie
 * tierce. Lecture directe, jamais par expression reguliere (ADR-0008, AssertionsWebTest).
 *
 * <p><b>Ce que ce garde-fou ne peut pas voir, et qui n'est pas un oubli (ADR-0025).</b> Sa population
 * est celle des <b>largeurs declarees en pixels</b>. Un element pousse hors du cadre visible par la
 * mise en page - une rangee flex sans repli, dont les elements additionnes depassent la largeur
 * disponible - ne declare aucune largeur : il n'entre donc dans aucune population de ce test, et le
 * vert de ce fichier ne dit rien de lui. Ce n'est pas un perimetre a elargir : le debordement est une
 * propriete du <b>rendu</b>, que les memes declarations produisent ou non selon la police, la longueur
 * du texte et l'algorithme de mise en page. ADR-0025 la declare non gardable par un automate de ce
 * depot et la confie a nadia-prof, avec la methode de mesure que ce constat exige
 * ({@code outils/mesurer-rendu.sh}).</p>
 */
class ViewportEtLargeurTelephoneTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/ViewportEtLargeurTelephoneTest");
    /**
     * La largeur de reference du produit, et le plafond que ce garde-fou oppose a toute largeur
     * declaree en pixels dans une feuille maison ou dans un attribut de style.
     *
     * <p><b>744 px depuis le 2026-08-23, decision de camille-po ; 375 px auparavant.</b> Les 375 px
     * venaient de S3-8, sprint 3, quand le telephone etait la cible du produit. DEC-0040
     * (2026-08-22) a sorti la bande sous 576 px du perimetre et fixe la reference a 744 x 551 : la
     * premisse du seuil avait disparu. ADR-0056 a d'abord rendu ce seuil <b>visible</b> - il
     * s'evitait en nichant la declaration sous une regle-@, et ne voyait donc pas la moitie des
     * gestes qu'il interdit - puis a route sa valeur a camille-po sans la trancher, une largeur de
     * reference n'etant pas une decision d'architecture.
     *
     * <p><b>Ce que sa decision retient, et ce qu'elle ecarte.</b> Le seuil est <b>revise, jamais
     * supprime</b> : le meme risque, contre le meme mode de defaillance, s'est redeplace avec la
     * cible - toute largeur maison au-dela de l'appareil le plus etroit reellement vise force un
     * debordement horizontal. Il est revise <b>uniformement sur les trois proprietes</b> que ce
     * fichier examine. L'argument que je lui avais fourni - exempter {@code max-width}, au motif
     * qu'un plafond au-dela de la cible ne peut pas faire deborder la ou {@code width} et
     * {@code min-width} le peuvent - est <b>ecarte</b>, avec son motif : il relacherait un garde-fou
     * exactement dans le lot qui en beneficie, et laisserait tout {@code max-width} maison hors de
     * la chaine d'ADR-0054 sans aucune garde. La valeur retenue est la reference deja fixee, pas un
     * troisieme nombre invente.
     */
    private static final int LARGEUR_CIBLE_PX = 744;

    private static Path templates(Path racine) {
        return racine.resolve("templates");
    }

    /**
     * Gabarits reellement servis comme document HTML complet : les fichiers directement sous
     * templates/. Un fragment (templates/fragments/...) est toujours insere par htmx dans une page
     * qui a deja son &lt;head&gt; ; il n'en rend jamais un lui-meme.
     */
    private static List<Path> gabaritsDePage(Path racine) throws IOException {
        try (Stream<Path> fichiers = Files.list(templates(racine))) {
            return fichiers.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        }
    }

    /** Tous les gabarits, fragments compris : perimetre du balayage de largeur. */
    private static List<Path> tousLesGabarits(Path racine) throws IOException {
        try (Stream<Path> arborescence = Files.walk(templates(racine))) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        }
    }

    static void verifierViewport(Path racine) throws IOException {
        List<Path> gabarits = gabaritsDePage(racine);
        assertThat(gabarits)
                .as("aucun gabarit de page sous %s : ce garde-fou ne prouve rien", templates(racine))
                .isNotEmpty();

        for (Path gabarit : gabarits) {
            Document document = Jsoup.parse(Files.readString(gabarit), gabarit.toString());
            Elements viewports = document.select("head > meta[name=viewport]");
            assertThat(viewports)
                    .as("%s doit porter <meta name=\"viewport\"> dans son <head>", gabarit)
                    .isNotEmpty();

            String premierSegment = viewports.first().attr("content").split(",")[0].strip();
            assertThat(premierSegment)
                    .as("%s : le premier segment de content doit valoir exactement width=device-width",
                            gabarit)
                    .isEqualTo("width=device-width");
        }
    }

    /** Une declaration "propriete: valeurpx;" trouvee dans un bloc CSS ou un attribut style. */
    private record LargeurPx(String origine, String propriete, int valeurPx) {
    }

    /** Un commentaire CSS peut contenir des points-virgules ; il ne doit jamais entrer dans une
     * declaration. */
    private static String sansCommentaires(String source) {
        StringBuilder resultat = new StringBuilder();
        int position = 0;
        while (position < source.length()) {
            int debut = source.indexOf("/*", position);
            if (debut < 0) {
                resultat.append(source, position, source.length());
                break;
            }
            resultat.append(source, position, debut);
            int fin = source.indexOf("*/", debut);
            position = fin < 0 ? source.length() : fin + 2;
        }
        return resultat.toString();
    }

    private static List<LargeurPx> largeursPxDuBloc(String corps, String origine) {
        List<LargeurPx> trouvees = new ArrayList<>();
        for (String declaration : corps.split(";")) {
            int deuxPoints = declaration.indexOf(':');
            if (deuxPoints < 0) {
                continue;
            }
            String propriete = declaration.substring(0, deuxPoints).strip();
            String valeur = declaration.substring(deuxPoints + 1).strip();
            if (isLargeurFixeeOuBornee(propriete) && valeur.endsWith("px")) {
                int nombre = Integer.parseInt(valeur.substring(0, valeur.length() - "px".length()).strip());
                trouvees.add(new LargeurPx(origine, propriete, nombre));
            }
        }
        return trouvees;
    }

    private static boolean isLargeurFixeeOuBornee(String propriete) {
        return propriete.equals("width") || propriete.equals("min-width") || propriete.equals("max-width");
    }

    /**
     * ADR-0056 : les largeurs en pixels des feuilles maison, lues par le balayage qui <b>garde le
     * contexte de regles-@</b> plutot que par un decoupage sur les accolades.
     *
     * <p>Le decoupage precedent attribuait a une regle-@ le texte entier de son bloc : sa premiere
     * declaration se lisait alors comme une propriete nommee "SELECTEUR ACCOLADE max-width", que
     * {@link #isLargeurFixeeOuBornee} ne reconnait pas, donc comme une largeur qui n'entrait dans
     * aucune population. <b>Une meme declaration etait jugee au niveau superieur du fichier et
     * invisible sous une requete de media</b> - un seuil qu'il suffisait de nicher pour ne plus le
     * rencontrer. Mesure du 2026-08-23, sur la declaration maison proposee pour {@code SYN-11} : le
     * plafond de 720 px passait cette clause en vert, la meme declaration au niveau superieur du
     * fichier la faisant rougir. Le balayage appele ici est celui qu'ADR-0054 avait deja generalise
     * pour la meme cause ; le reutiliser evite un troisieme decoupage de blocs CSS dans ce module
     * (ADR-0030).
     *
     * <p><b>Le perimetre reste celui d'ADR-0014, derive de la provenance et non d'un fichier nomme</b>
     * : {@code reglesMaison} lit toute feuille servie sauf celles de {@code static/vendor/}, qui sont
     * des copies octet pour octet de bibliotheques tierces. Deux consequences voulues, inchangees par
     * ce lot : une feuille maison neuve est jugee du jour ou elle existe, sans qu'on pense a
     * l'inscrire ; et une feuille tierce ne recoit jamais le reproche d'un choix de mise en page qui
     * n'est pas le notre. Une bibliotheque deposee ailleurs que sous {@code vendor/} n'echappe a rien.
     */
    private static List<LargeurPx> largeursPxDesFeuillesMaison(Path racine) {
        List<LargeurPx> trouvees = new ArrayList<>();
        for (Regle regle : reglesMaison(racine)) {
            String origine = regle.fichier()
                    + (regle.contexte().isEmpty() ? "" : " " + regle.contexte())
                    + " (" + regle.selecteur() + ")";
            trouvees.addAll(largeursPxDuBloc(regle.corps(), origine));
        }
        return trouvees;
    }

    private static List<LargeurPx> largeursPxDesGabarits(List<Path> gabarits) throws IOException {
        List<LargeurPx> trouvees = new ArrayList<>();
        for (Path gabarit : gabarits) {
            Document document = Jsoup.parse(Files.readString(gabarit), gabarit.toString());
            for (Element element : document.select("[style]")) {
                trouvees.addAll(largeursPxDuBloc(element.attr("style"),
                        gabarit + " (<" + element.tagName() + " style>)"));
            }
        }
        return trouvees;
    }

    static void verifierAucuneLargeurFixeeAuDelaDeLaCible(Path racine) throws IOException {
        assertThat(reglesMaison(racine))
                .as("aucune regle maison sous %s/static : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();

        List<LargeurPx> largeurs = new ArrayList<>(largeursPxDesFeuillesMaison(racine));
        largeurs.addAll(largeursPxDesGabarits(tousLesGabarits(racine)));

        for (LargeurPx largeur : largeurs) {
            assertThat(largeur.valeurPx())
                    .as("%s : %s ne doit jamais depasser %dpx, la largeur de reference du produit "
                            + "(DEC-0040 ; seuil revise a cette valeur par camille-po le 2026-08-23, "
                            + "revise et non supprime, uniformement sur width, min-width et "
                            + "max-width). Une largeur maison au-dela de l'appareil le plus etroit "
                            + "reellement vise force un debordement horizontal",
                            largeur.origine(), largeur.propriete(), LARGEUR_CIBLE_PX)
                    .isLessThanOrEqualTo(LARGEUR_CIBLE_PX);
            assertThat(largeur.propriete())
                    .as("%s : width fixe une largeur absolue sur le conteneur, utiliser max-width "
                            + "pour rester capable de retrecir sous %dpx, la largeur de reference du "
                            + "produit (DEC-0040)", largeur.origine(), LARGEUR_CIBLE_PX)
                    .isNotEqualTo("width");
        }
    }

    @Test
    void accueilPorteLaBaliseViewport() throws IOException {
        verifierViewport(RACINE_PRODUCTION);
    }

    @Test
    void aucuneLargeurNestFixeeAuDelaDeLaCibleTelephone() throws IOException {
        verifierAucuneLargeurFixeeAuDelaDeLaCible(RACINE_PRODUCTION);
    }

    @Test
    void laBaliseViewportManquanteFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("sans-viewport");
        assertThatThrownBy(() -> verifierViewport(racine)).isInstanceOf(AssertionError.class);
    }

    /**
     * Le seuil est prouve <b>a sa valeur</b>, pas seulement dans son existence : le jeu fautif
     * declare un plafond a {@code 745px}, un pixel au-dela de la cible, et l'assertion exige de
     * retrouver ce nombre dans le message. Il porte {@code max-width} et non {@code width} pour une
     * raison qui compte : {@code width} est interdit par son nom seul, quelle que soit sa valeur -
     * un jeu fautif ecrit avec {@code width} resterait rouge meme si le seuil etait relache a
     * l'infini, et ce test-la prouverait l'interdiction de {@code width}, pas le seuil.
     * <b>C'etait le cas avant le 2026-08-23</b> : ce jeu fautif portait {@code width: 600px}, valeur
     * qui passe desormais sous la cible de 744 px ; le test restait vert par l'autre assertion, en
     * ayant cesse de prouver ce que son nom annonce.
     */
    @Test
    void unPlafondUnPixelAuDelaDeLaCibleFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("largeur-excessive");
        assertThatThrownBy(() -> verifierAucuneLargeurFixeeAuDelaDeLaCible(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("745")
                .hasMessageContaining("744");
    }

    /**
     * L'autre cote du meme seuil, et le seul qui prouve que la cible vaut 744 px plutot qu'un nombre
     * plus bas : deux plafonds que la revision autorise doivent laisser ce garde-fou <b>vert</b>.
     * {@code 720px} est la valeur exacte que la variante d'{@code ines-design} pose sur
     * {@code .zone-jeu} pour {@code SYN-11} - elle passe <b>sans exception nommee</b>, ce qui est ce
     * que la decision de {@code camille-po} voulait obtenir en retenant la reference deja fixee
     * plutot qu'un troisieme nombre. {@code 744px} est la borne elle-meme, et pinne le sens large de
     * la comparaison.
     */
    @Test
    void unPlafondSousLaCibleOuAExactementLaCibleLaisseLeGardeFouVert() throws IOException {
        verifierAucuneLargeurFixeeAuDelaDeLaCible(
                CONTRE_EXEMPLES.resolve("largeur-plafonnee-sous-la-cible"));
    }

    @Test
    void uneLargeurFixeSousLaCibleFaitAussiEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("largeur-fixe-sous-la-cible");
        assertThatThrownBy(() -> verifierAucuneLargeurFixeeAuDelaDeLaCible(racine))
                .isInstanceOf(AssertionError.class);
    }

    /**
     * ADR-0056 : la meme faute, nichee sous une requete de media. Avant ce lot, ce cas etait
     * <b>vert</b> : le seuil de ce fichier s'evitait en deplacant la declaration d'un cran vers
     * l'interieur, sans rien changer a ce qu'elle produit. Le jeu fautif porte le meme selecteur
     * fictif et la meme valeur que {@code largeur-excessive}, a la seule difference du nichage, pour
     * que ce qui est prouve ici soit le nichage et rien d'autre.
     */
    @Test
    void uneLargeurExcessiveNicheeSousUneRequeteDeMediaFaitAussiEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("largeur-excessive-sous-media");
        assertThatThrownBy(() -> verifierAucuneLargeurFixeeAuDelaDeLaCible(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("745");
    }
}
