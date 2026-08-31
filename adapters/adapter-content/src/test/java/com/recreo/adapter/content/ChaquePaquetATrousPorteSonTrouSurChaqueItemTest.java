package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Defaut signale par l'utilisatrice en jouant a "Le bon petit mot" : douze items de S5-6 livraient
 * une phrase deja complete, la bonne reponse ecrite en clair, alors que la consigne du jeu demande
 * de completer un trou ("Quel mot complete la phrase ?"). Aucun garde-fou n'existait pour attraper
 * cela.
 *
 * <p>ADR-0013, obligation 1 : le perimetre se derive, il ne s'enumere pas. Ce garde-fou ne nomme
 * pas "cm1-homophones" : il derive du repertoire de contenu quel paquet utilise deja la convention
 * du trou (le marqueur "___" present sur au moins un de ses items), et exige alors qu'elle soit
 * tenue sur tous ses items - pour ce paquet comme pour tout paquet a trous a venir. Un paquet qui
 * n'utilise jamais ce marqueur (par exemple "cp-le-bon-son", qui presente un mot entier a lire)
 * n'entre pas dans son perimetre.</p>
 *
 * <p><b>Lot 5a de s24, decision 7 : etendu a l'item de saisie.</b> Le critere ne lit que l'enonce,
 * et son perimetre se derive du paquet lui-meme. Un paquet de saisie est le contenu ou la convention
 * du trou est la plus tentante - {@code CPL-6} demande que l'enfant comprenne que sa saisie prend la
 * place du signe qui manque. Le garde-fou ne choisit pas entre le marqueur de trou et un autre
 * signe : ce choix est pedagogique. Il exige la coherence de celui qui a ete choisi.</p>
 */
class ChaquePaquetATrousPorteSonTrouSurChaqueItemTest {

    private static final String MARQUEUR_DE_TROU = "___";

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, un paquet qui n'a jamais ete livre. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/ChaquePaquetATrousPorteSonTrouSurChaqueItemTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/ChaquePaquetATrousPorteSonTrouSurChaqueItemTest";

    /**
     * Tirages effectues sur un paquet <b>combinatoire</b>, le seul qui n'enumere aucun item et ne
     * s'epuise donc jamais. ADR-0064 : sur un paquet enumere, la borne n'est pas ecrite ici, elle
     * est derivee de ce que le paquet declare, et une lecture partielle est un echec nomme.
     */
    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 60;

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .map(fichier -> fichier.getFileName().toString())
                    .filter(nom -> nom.endsWith(".yaml"))
                    .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Item> itemsTires(ContentSource contenu, int tirages) {
        RandomSource hasard = new Random(11)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < tirages; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            items.add(item);
            dejaVus.add(item.id());
        }
        return items;
    }

    static void verifierQueChaquePaquetATrousPorteSonTrouSurChaqueItem(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        for (String paquet : paquets) {
            // ADR-0064 : la borne vient du paquet, jamais d'un nombre ecrit ici. De quoi
            // l'epuiser, plus un tirage pour constater l'epuisement.
            int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racine, paquet);
            int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
            // Lot 5a de s24, decision 7 : etendu a l'item de saisie. Le critere ne lit que
            // l'enonce, et son perimetre se derive du paquet lui-meme - un paquet qui n'emploie
            // jamais le marqueur n'y entre pas. Un paquet de saisie est justement le contenu ou la
            // convention du trou est la plus tentante : CPL-6 demande que l'enfant comprenne que sa
            // saisie prend la place du signe qui manque.
            List<ClassementDesItems.ItemLu> items = ClassementDesItems.lus(
                    itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet), tirages));
            assertThat(items)
                    .as("%s : un paquet qui ne rend aucun item ne peut rien promettre", paquet)
                    .isNotEmpty();
            if (declares > 0) {
                // ADR-0064 : un paquet enumere est lu EN ENTIER, ou ce garde-fou est rouge et le dit.
                assertThat(items)
                        .as("%s declare %d items sur disque et ce garde-fou n'en a tire que %d : la"
                                + " clause porterait sur un echantillon sans le dire", paquet, declares,
                                items.size())
                        .hasSize(declares);
            }

            boolean paquetATrous = items.stream().anyMatch(item -> item.enonce().contains(MARQUEUR_DE_TROU));
            if (!paquetATrous) {
                continue;
            }
            for (ClassementDesItems.ItemLu item : items) {
                assertThat(item.enonce())
                        .as("paquet %s, item \"%s\" : ce paquet utilise deja le marqueur de trou '___'"
                                + " sur au moins un item, donc la reponse ne peut plus etre ecrite en"
                                + " clair dans la phrase - elle doit toujours etre cachee derriere un trou",
                                paquet, item.enonce())
                        .contains(MARQUEUR_DE_TROU);
            }
        }
    }

    @Test
    void chaquePaquetDeProductionQuiUtiliseLeTrouLePorteSurChacunDeSesItems() {
        verifierQueChaquePaquetATrousPorteSonTrouSurChaqueItem(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a inscrit nulle part. Il
     * porte deja le trou sur un item et l'ecrit en clair sur l'autre : seul un perimetre derive de
     * l'usage reel du marqueur peut le voir venir.
     */
    @Test
    void unPaquetATrousAvecUnItemSansTrouFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaquePaquetATrousPorteSonTrouSurChaqueItem(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-a-trous-avec-un-trou-manquant")
                .hasMessageContaining("ecrite en clair dans la phrase");
    }

    /** Le meme paquet, une fois son trou manquant ferme, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisSonTrouFermePasseLeGardeFou() {
        verifierQueChaquePaquetATrousPorteSonTrouSurChaqueItem(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }
}
