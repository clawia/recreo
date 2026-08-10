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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S3-8, prealable seul (avis de faisabilite theo-archi, sprint 3) : accueil.html ne portait aucune
 * balise &lt;meta name="viewport"&gt;. Sans elle, un telephone rend la page dans une fenetre
 * virtuelle d'environ 980px puis reduit l'ensemble : la mise en page a 375px n'est jamais reellement
 * exercee sur un vrai telephone. Le reste de la story (chevauchement et depassement reellement
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
    private static final int LARGEUR_CIBLE_PX = 375;

    private static Path templates(Path racine) {
        return racine.resolve("templates");
    }

    /**
     * ADR-0014 : le perimetre juge ici n'est plus un fichier nomme, il est derive de la provenance.
     * Toute feuille servie est jugee, sauf celles de static/vendor/, qui sont des copies octet pour
     * octet de bibliotheques tierces. Deux consequences voulues : une feuille maison neuve est jugee
     * du jour ou elle existe, sans qu'on pense a l'inscrire ; et une feuille tierce ne recoit jamais
     * le reproche d'un choix de mise en page qui n'est pas le notre. Une bibliotheque deposee
     * ailleurs que sous vendor/ n'echappe a rien : elle est jugee comme du CSS maison, et une
     * largeur en pixels la fait echouer immediatement.
     */
    private static List<Path> feuillesMaison(Path racine) throws IOException {
        Path statique = racine.resolve("static");
        if (!Files.isDirectory(statique)) {
            return List.of();
        }
        Path tierces = statique.resolve("vendor");
        try (Stream<Path> arborescence = Files.walk(statique)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".css"))
                    .filter(fichier -> !fichier.startsWith(tierces))
                    .sorted()
                    .toList();
        }
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

    private static List<LargeurPx> largeursPxDuCss(Path fichierCss) throws IOException {
        String source = sansCommentaires(Files.readString(fichierCss));
        List<LargeurPx> trouvees = new ArrayList<>();
        for (String bloc : source.split("\\}")) {
            int accolade = bloc.indexOf('{');
            if (accolade < 0) {
                continue;
            }
            String selecteur = bloc.substring(0, accolade).strip();
            String corps = bloc.substring(accolade + 1);
            trouvees.addAll(largeursPxDuBloc(corps, fichierCss + " (" + selecteur + ")"));
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
        List<Path> feuilles = feuillesMaison(racine);
        assertThat(feuilles)
                .as("aucune feuille maison sous %s/static : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();

        List<LargeurPx> largeurs = new ArrayList<>();
        for (Path feuille : feuilles) {
            largeurs.addAll(largeursPxDuCss(feuille));
        }
        largeurs.addAll(largeursPxDesGabarits(tousLesGabarits(racine)));

        for (LargeurPx largeur : largeurs) {
            assertThat(largeur.valeurPx())
                    .as("%s : %s ne doit jamais depasser %dpx, la largeur cible telephone",
                            largeur.origine(), largeur.propriete(), LARGEUR_CIBLE_PX)
                    .isLessThanOrEqualTo(LARGEUR_CIBLE_PX);
            assertThat(largeur.propriete())
                    .as("%s : width fixe une largeur absolue sur le conteneur, utiliser max-width pour "
                            + "rester capable de retrecir sous %dpx", largeur.origine(), LARGEUR_CIBLE_PX)
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

    @Test
    void uneLargeurQuiDepasseLaCibleFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("largeur-excessive");
        assertThatThrownBy(() -> verifierAucuneLargeurFixeeAuDelaDeLaCible(racine))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void uneLargeurFixeSousLaCibleFaitAussiEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("largeur-fixe-sous-la-cible");
        assertThatThrownBy(() -> verifierAucuneLargeurFixeeAuDelaDeLaCible(racine))
                .isInstanceOf(AssertionError.class);
    }
}
