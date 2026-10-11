package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0105, regle 4 : <b>tout element {@code <a>} d'un gabarit porte un attribut {@code href}
 * litteral</b>, meme quand {@code th:href} le recalcule au rendu.
 *
 * <p><b>Ce que cette regle interdit reellement.</b> Cinq garde-fous derivent leur population de
 * pressables du gabarit brut, avant tout traitement Thymeleaf, par le selecteur
 * {@link LHabillageNeRogneJamaisUnElementPressableTest#PRESSABLES} : la taille des textes et des
 * boutons, la saillance de l'action face a la sortie, l'immobilite de l'habillage, le rognage, et
 * l'entree de menu. Un {@code <a th:href="...">} sans {@code href} litteral n'est pas un
 * {@code a[href]} pour eux : il sort de leur perimetre <b>en silence</b>, et la tuile de matiere de
 * l'accueil en deux vues est exactement ce lien-la, ecrit de la facon la plus naturelle. Le
 * commentaire de la puce de niveau d'{@code accueil.html} nommait ce piege ; aucun test ne le
 * tenait.
 *
 * <p><b>Pourquoi tout {@code <a>} et non tout {@code <a th:href>}.</b> {@code th:attr="href=..."}
 * produit le meme lien par un autre attribut : enumerer les formes Thymeleaf qui posent un href
 * echouerait par omission. La propriete jugee est l'absence du litteral, quelle que soit la facon
 * dont le rendu le pose.
 *
 * <p><b>Ce qu'elle ne voit pas</b> : un pressable fait d'un {@code <div>} cliquable par htmx.
 * {@code FrontieresDuJavaScriptTest} et les gardes d'ADR-0017 portent ce cas, pas celle-ci.
 */
class ToutLienDeGabaritPorteUnHrefLitteralTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/ToutLienDeGabaritPorteUnHrefLitteralTest");

    private static List<Path> gabarits(Path racine) {
        try (Stream<Path> arbre = Files.walk(racine.resolve("templates"))) {
            return arbre.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Le juge pur : les liens d'un gabarit qui ne portent pas de href litteral. */
    static List<String> liensSansHrefLitteral(String nom, String source) {
        List<String> fautifs = new ArrayList<>();
        for (Element lien : Jsoup.parse(source).select("a")) {
            if (!lien.hasAttr("href")) {
                fautifs.add(nom + " : <a class=\"" + lien.className() + "\"> ne porte pas de href "
                        + "litteral ; les garde-fous qui lisent \"" + LHabillageNeRogneJamaisUnElementPressableTest.PRESSABLES
                        + "\" sur le gabarit brut ne le voient pas");
            }
        }
        return fautifs;
    }

    static void verifier(Path racine) {
        List<Path> releves = gabarits(racine);
        assertThat(releves)
                .as("ADR-0013 : ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s", racine)
                .isNotEmpty();
        assertThat(releves.stream().anyMatch(gabarit -> !Jsoup.parse(lire(gabarit)).select("a").isEmpty()))
                .as("ADR-0051 : aucun lien dans les gabarits de %s ; la clause suivante serait verte "
                        + "sans rien juger", racine)
                .isTrue();
        List<String> fautifs = new ArrayList<>();
        for (Path gabarit : releves) {
            fautifs.addAll(liensSansHrefLitteral(gabarit.getFileName().toString(), lire(gabarit)));
        }
        assertThat(fautifs)
                .as("ADR-0105 : un lien sans href litteral sort en silence du perimetre des "
                        + "garde-fous de pressable ; pose un href litteral, que th:href ecrase au rendu")
                .isEmpty();
    }

    @Test
    void toutLienDesGabaritsDeProductionPorteUnHrefLitteral() {
        verifier(RACINE_PRODUCTION);
    }

    /** Le jeu fautif commite : la tuile de matiere telle qu'un lot l'ecrirait sans y penser. */
    @Test
    void uneTuileDeMatiereSansHrefLitteralFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE.resolve("tuile-sans-href")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("tuile-matiere")
                .hasMessageContaining("ne porte pas de href litteral");
    }

    /**
     * Sur le materiau reel : la puce de niveau d'{@code accueil.html} privee de son espace reserve,
     * le geste exact que son commentaire interdit en prose.
     */
    @Test
    void laPuceDeNiveauReellePriveeDeSonHrefFaitEchouerLeJuge() {
        String accueil = lire(RACINE_PRODUCTION.resolve("templates/accueil.html"));
        String mute = accueil.replace("href=\"/niveaux/CP\"", "");
        assertThat(mute).as("la mutation doit trouver l'espace reserve de la puce").isNotEqualTo(accueil);

        assertThat(liensSansHrefLitteral("accueil.html", accueil)).isEmpty();
        assertThat(liensSansHrefLitteral("accueil.html", mute))
                .singleElement().asString().contains("chip-niveau");
    }
}
