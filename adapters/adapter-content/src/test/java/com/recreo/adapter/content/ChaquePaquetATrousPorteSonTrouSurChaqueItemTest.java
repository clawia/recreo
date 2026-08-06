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
     * Tirages effectues sur chaque paquet. Un paquet enumere s'epuise bien avant ; un paquet
     * combinatoire ne s'epuise jamais, d'ou la borne.
     */
    private static final int TIRAGES = 60;

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

    private static List<Question> itemsTires(ContentSource contenu) {
        RandomSource hasard = new Random(11)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < TIRAGES; tirage++) {
            Item item = contenu.next(hasard, dejaVus).orElse(null);
            if (item == null) {
                break;
            }
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    static void verifierQueChaquePaquetATrousPorteSonTrouSurChaqueItem(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        for (String paquet : paquets) {
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            assertThat(items)
                    .as("%s : un paquet qui ne rend aucun item ne peut rien promettre", paquet)
                    .isNotEmpty();

            boolean paquetATrous = items.stream().anyMatch(item -> item.enonce().contains(MARQUEUR_DE_TROU));
            if (!paquetATrous) {
                continue;
            }
            for (Question item : items) {
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
