package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
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
 * ADR-0044, le versant que le bytecode ne voit pas : aucun gabarit ne lit le descripteur de contenu
 * d'un jeu. Choisir quels jeux sont montres est une regle de catalogue, elle vit derriere le port
 * entrant ; une vue qui sait ou un jeu range son niveau la ramene dans la couche la plus externe,
 * et dans la seule qu'aucune regle ArchUnit ne peut atteindre.
 *
 * <p><b>Pourquoi ce garde-fou existe a cote d'une regle ArchUnit et non a sa place.</b>
 * {@code LeChoixDesJeuxResteDerriereLePortTest} ferme le chemin du code compile - un
 * {@code .filter(jeu -> jeu.descripteur().niveau()...)} dans le controleur. Il ne peut rien contre
 * {@code th:if="${jeu.descripteur().niveau() == niveau}"}, qui n'est pas du bytecode et qui est le
 * <b>second</b> chemin le moins cher vers le meme defaut. Les deux ensemble ferment les deux
 * chemins ; ni l'un ni l'autre ne suffit, et c'est pourquoi aucun des deux n'est decoratif.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les gabarits viennent d'un balayage de {@code templates/}, jamais
 *       d'une liste ecrite ici. Un gabarit neuf entre dans le controle sans que ce fichier
 *       change ;</li>
 *   <li><b>derivation totale</b> : aucun gabarit releve, ou aucun gabarit qui lise le moindre
 *       attribut du modele, sont des <b>echecs</b> - le premier parce que la clause ne jugerait
 *       rien, le second parce qu'une application dont les vues ne lisent aucun modele n'a rien a
 *       garder ici et que ce vert-la ne doit pas passer pour une garantie ;</li>
 *   <li><b>mise a l'epreuve</b> : sur un jeu de gabarits fautif qui ecrit le filtre par niveau dans
 *       la vue, avec la syntaxe reelle que Thymeleaf demande.</li>
 * </ul>
 *
 * <p><b>Ce qu'il ne prouve pas.</b> Il lit le nom de l'accesseur, pas le sens de l'expression. Une
 * vue qui recevrait du controleur une valeur deja extraite du descripteur sous un autre nom
 * passerait verte - le defaut serait alors dans le controleur, ou la regle ArchUnit l'attend. Et il
 * ne dit rien de ce qu'un enfant voit : quels jeux doivent apparaitre est une decision produit.
 */
class LeChoixDesJeuxNeVitPasDansLaVueTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, jamais servi. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/LeChoixDesJeuxNeVitPasDansLaVueTest");

    /**
     * Le nom par lequel un jeu du catalogue expose son descripteur de contenu
     * ({@code GameDefinition.descripteur()}). C'est le seul chemin par lequel une vue peut atteindre
     * la matiere, le niveau, le domaine ou la competence d'un jeu : le fermer ferme les quatre a la
     * fois, sans enumerer aucun des quatre - une enumeration manquerait le champ ajoute demain.
     */
    private static final String ACCES_AU_DESCRIPTEUR = "descripteur";

    /** La marque d'une expression Thymeleaf : ce par quoi une vue lit son modele. */
    private static final String LECTURE_DU_MODELE = "${";

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

    /**
     * Les endroits ou un gabarit nomme le descripteur : la valeur d'un attribut, ou le texte ecrit
     * en clair. Les commentaires en sont exclus a dessein - un commentaire qui explique pourquoi la
     * vue ne lit pas le descripteur ne doit pas rougir a cause du mot qu'il cite.
     */
    private static List<String> lecturesDuDescripteur(Path racine) {
        List<String> fautifs = new ArrayList<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            String source = gabarit.getFileName().toString();
            for (Element element : document.getAllElements()) {
                for (Attribute attribut : element.attributes()) {
                    if (attribut.getValue().contains(ACCES_AU_DESCRIPTEUR)) {
                        fautifs.add(source + " : <" + element.tagName() + "> lit le descripteur de "
                                + "contenu dans " + attribut.getKey() + "=\"" + attribut.getValue()
                                + "\"");
                    }
                }
                if (element.ownText().contains(ACCES_AU_DESCRIPTEUR)) {
                    fautifs.add(source + " : <" + element.tagName() + "> ecrit \""
                            + ACCES_AU_DESCRIPTEUR + "\" dans son texte");
                }
            }
        }
        return fautifs;
    }

    static void verifier(Path racine) {
        List<Path> releves = gabarits(racine);
        assertThat(releves)
                .as("ADR-0013 : ce garde-fou ne prouve rien s'il ne releve aucun gabarit sous %s",
                        racine)
                .isNotEmpty();
        assertThat(releves.stream().anyMatch(gabarit -> lire(gabarit).contains(LECTURE_DU_MODELE)))
                .as("ADR-0013, derivation totale : aucun gabarit ne lit le modele sous %s. La clause "
                        + "suivante serait alors verte sans rien garder, et ce vert-la ne doit pas "
                        + "passer pour une garantie", racine)
                .isTrue();
        assertThat(lecturesDuDescripteur(racine))
                .as("ADR-0044 : le choix des jeux montres vit derriere le port entrant. Une vue qui "
                        + "lit le descripteur de contenu sait ou un jeu range son niveau, donc "
                        + "changera le jour ou un niveau entrera - ADR-0006 veut qu'un niveau soit "
                        + "une donnee")
                .isEmpty();
    }

    @Test
    void aucunGabaritDeProductionNeLitLeDescripteurDeContenu() {
        verifier(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif est le filtre par niveau ecrit dans la vue, avec la
     * syntaxe que Thymeleaf demande reellement. C'est la forme qu'un lot presse ecrirait, pas une
     * faute inventee pour la circonstance.
     */
    @Test
    void unGabaritQuiFiltreLeCatalogueParNiveauFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE.resolve("filtre-dans-la-vue")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("lit le descripteur de contenu dans th:if");
    }
}
