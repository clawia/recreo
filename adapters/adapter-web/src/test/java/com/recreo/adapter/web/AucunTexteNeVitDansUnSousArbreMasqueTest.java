package com.recreo.adapter.web;

import org.jsoup.Jsoup;
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
 * ADR-0035 : aucun texte rendu par le serveur ne vit dans un sous-arbre que {@code aria-hidden}
 * retire de l'arbre d'accessibilite. Un enfant qui utilise un lecteur d'ecran n'entend rien de ce
 * texte-la, et rien dans le depot ne le disait.
 *
 * <p>La question est venue d'{@code ines-design} : l'enseigne du meuble - le nom de la machine -
 * vivrait dans {@code .cabinet-bandeau}, qui porte {@code aria-hidden="true"} depuis S8-1 parce
 * qu'il ne contenait alors qu'une etoile decorative. Le masque etait juste sur son contenu d'hier et
 * devient faux le jour ou du texte y entre, sans qu'aucune ligne ne change de sens.</p>
 *
 * <p><b>Pourquoi le gabarit et non le rendu.</b> Le defaut est une propriete de la
 * <b>declaration</b> : il existe des l'ecriture du gabarit, avant qu'aucune requete ne soit servie,
 * et il ne depend d'aucune valeur de modele. Le lire dans le gabarit le prend donc plus tot et sur
 * une population complete - les quatre gabarits, y compris les branches qu'un corpus d'ecrans ne
 * traverse pas (la lecon d'ADR-0034, dans l'autre sens).</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les gabarits viennent d'un balayage de {@code templates/}, les
 *       porteurs de masque d'une selection sur l'attribut, et le texte de la lecture de chaque
 *       sous-arbre. Aucune classe, aucun nom de fichier, aucun selecteur n'est ecrit ici. Un gabarit
 *       neuf, un masque neuf entrent dans le controle sans que ce fichier change ;</li>
 *   <li><b>derivation totale</b> : le texte compte sous ses deux formes, celle que le gabarit ecrit
 *       en clair et celle que Thymeleaf pose au rendu ({@code th:text}, {@code th:utext}). Ne
 *       compter que la premiere aurait laisse passer exactement le cas d'{@code ines-design}, dont
 *       le texte vient du modele ;</li>
 *   <li><b>mise a l'epreuve</b> : sur le balisage qu'{@code ines-design} a propose, repris tel
 *       quel. Une fixture de ma main ressemblerait a ce que j'imagine (lecon de s05) ; celle-ci est
 *       ce qu'une conceptrice a reellement ecrit avant qu'on lui reponde.</li>
 * </ul>
 */
class AucunTexteNeVitDansUnSousArbreMasqueTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunTexteNeVitDansUnSousArbreMasqueTest");

    private static final String MASQUE = "aria-hidden";

    /**
     * Les deux attributs par lesquels Thymeleaf pose du texte au rendu. Un sous-arbre masque qui n'en
     * porte aucun et n'ecrit rien en clair est muet pour tout le monde, ce qui est le cas normal d'un
     * decor.
     */
    private static final List<String> ATTRIBUTS_DE_TEXTE = List.of("th:text", "th:utext");

    /** Un porteur de masque, avec le gabarit ou il se trouve, pour que le message le nomme. */
    private record Masque(Path gabarit, Element porteur) {
    }

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

    static List<Masque> masques(Path racine) {
        List<Masque> masques = new ArrayList<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element porteur : document.select("[" + MASQUE + "=true]")) {
                masques.add(new Masque(gabarit, porteur));
            }
        }
        return masques;
    }

    /** Un nom court et sans ambiguite pour designer un element dans un message d'echec. */
    private static String designer(Element element) {
        String classes = element.className().strip();
        return classes.isEmpty() ? element.tagName() : element.tagName() + "." + classes;
    }

    static List<String> textesDansUnSousArbreMasque(Path racine) {
        List<String> fautifs = new ArrayList<>();
        for (Masque masque : masques(racine)) {
            for (Element noeud : masque.porteur().getAllElements()) {
                if (!noeud.ownText().isBlank()) {
                    fautifs.add(masque.gabarit().getFileName() + " : " + designer(noeud)
                            + " ecrit du texte dans le sous-arbre masque de "
                            + designer(masque.porteur()));
                }
                for (String attribut : ATTRIBUTS_DE_TEXTE) {
                    if (noeud.hasAttr(attribut)) {
                        fautifs.add(masque.gabarit().getFileName() + " : " + designer(noeud)
                                + " porte " + attribut + " dans le sous-arbre masque de "
                                + designer(masque.porteur()));
                    }
                }
            }
        }
        return fautifs;
    }

    static void verifier(Path racine) {
        assertThat(textesDansUnSousArbreMasque(racine))
                .as("ADR-0035 : un texte pose sous un element %s=\"true\" ne parvient pas a un enfant "
                        + "qui ecoute la page. Soit le texte sort du sous-arbre masque, soit le masque "
                        + "se resserre sur ce qui est reellement decoratif - le choix appartient a "
                        + "nadia-prof, mais le silence n'est pas une option", MASQUE)
                .isEmpty();
    }

    @Test
    void aucunGabaritNePoseDeTexteSousUnElementMasque() {
        verifier(RACINE_PRODUCTION);
    }

    /**
     * ADR-0003 : sans cette clause, la precedente serait verte le jour ou plus aucun masque n'existe,
     * et personne ne le saurait. Le message dit quoi faire dans ce cas plutot que de laisser deviner.
     */
    @Test
    void laProductionPorteBienDesSousArbresMasques() {
        assertThat(masques(RACINE_PRODUCTION))
                .as("ADR-0035 : aucun element %s=\"true\" dans les gabarits de production. La clause "
                        + "precedente ne juge alors rien du tout. Si le masquage a reellement disparu, "
                        + "c'est ce garde-fou qu'il faut supprimer, avec son motif ecrit", MASQUE)
                .isNotEmpty();
    }

    /**
     * ADR-0013 obligation 3, sur du materiau reel : le balisage de la section 2.2 de
     * {@code proposition-ines-meuble.md}, repris tel quel - l'enseigne posee par {@code th:text} dans
     * le bandeau masque. C'est un texte que le modele fournit, donc invisible a une lecture qui ne
     * chercherait que du texte ecrit en clair.
     */
    @Test
    void lEnseigneDansLeBandeauMasqueFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE.resolve("enseigne-dans-le-bandeau-masque")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("span.cabinet-enseigne")
                .hasMessageContaining("th:text")
                .hasMessageContaining("div.cabinet-bandeau");
    }

    /**
     * L'autre forme du meme defaut, pour que la clause ne soit pas lue comme une regle sur Thymeleaf :
     * un texte ecrit en clair sous un masque est refuse de la meme facon.
     */
    @Test
    void unTexteEcritEnClairSousUnMasqueFaitAussiEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLE.resolve("enseigne-en-clair")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ecrit du texte dans le sous-arbre masque");
    }
}
