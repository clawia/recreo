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
 * ADR-0016 : la mecanique bandit manchot promet une aide apres trois echecs consecutifs (S3-2) ;
 * cette aide est un texte que seul le contenu peut fournir. Un paquet qui n'en porte pas laisse la
 * promesse sans objet, et l'enfant sans recours - constate en jeu par nadia-prof sur
 * bandit-manchot-le-bon-son-cp, motif 5 de son refus, dix manches ratees sans qu'aucune aide
 * n'apparaisse.
 *
 * <p>Le perimetre derive du <b>repertoire de contenu</b>, jamais d'une liste de paquets
 * (ADR-0013, obligation 1). C'est deliberement le repertoire et non le catalogue : DEC-0009 vient
 * de retirer un jeu du catalogue en laissant son paquet en place, et c'est precisement ce
 * paquet-la que la story S5-5 doit corriger. Un garde-fou qui derivait du catalogue ne l'aurait
 * plus vu.</p>
 *
 * <p>La derivation est totale (obligation 2) : chaque paquet trouve est charge par le chargeur
 * reel, ses items sont tires par la meme chaine que celle qu'un enfant traverse, et un item qui ne
 * porte pas d'indice fait echouer le test. Un type de contenu dont la fabrique perd l'indice en
 * route est donc rouge des son premier paquet, quel que soit ce que dit son YAML.</p>
 */
class ChaquePaquetPorteUnIndiceTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : le jeu fautif de ce garde-fou, un paquet qui n'a jamais ete livre. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/ChaquePaquetPorteUnIndiceTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/ChaquePaquetPorteUnIndiceTest";

    /**
     * Tirages effectues sur un paquet <b>combinatoire</b>, le seul qui n'enumere aucun item et ne
     * s'epuise donc jamais. ADR-0064 : sur un paquet enumere, la borne n'est pas ecrite ici, elle
     * est derivee de ce que le paquet declare, et une lecture partielle est un echec nomme.
     */
    private static final int TIRAGES_SUR_UN_PAQUET_COMBINATOIRE = 60;

    /**
     * Paquets ecrits avant ADR-0016, dont aucun item ne porte encore d'indice. Cette liste exempte,
     * elle n'absout pas : elle ne peut pas grandir (PLAFOND_DE_DETTE), elle ne peut pas pourrir
     * (chaque nom doit designer un paquet qui existe), et un paquet neuf sans indice echoue au lieu
     * d'y entrer. Ecrire ces indices est du contenu pedagogique : il appartient aux stories S5-2 a
     * S5-6 et repasse devant nadia-prof, pas devant un test.
     *
     * <p>S5-5 (residuel) : "cp-le-bon-son" et "cm1-homophones" ont ecrit leurs indices et sortent
     * de cette dette. L'ensemble est vide, mais reste ecrit (et non supprime) : le plafond
     * ci-dessous est la trace de cette fermeture, et une regression future y redeviendrait
     * visible.</p>
     */
    private static final Set<String> DETTE_DE_CONTENU = Set.of();

    /**
     * Ouverture de s05 : 2. Porte a 0 le 2026-08-07, a la confirmation de la porte architecture,
     * comme le planning de s05 le demandait au cas ou les deux lots fermeraient chacun leur part.
     * L'ensemble etait deja vide, mais le plafond etait reste a 2 : un paquet neuf sans indice
     * pouvait encore etre exempte en silence, et le commentaire ci-dessus disait donc faux quand il
     * appelait ce plafond "la trace de cette fermeture". Ce nombre ne remonte jamais ; a 0, la seule
     * facon d'exempter un paquet est de modifier cette ligne, ce qui se voit dans un diff.
     */
    private static final int PLAFOND_DE_DETTE = 0;

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

    /**
     * Le nombre d'items qu'un paquet declare sur disque, 0 pour un paquet combinatoire qui n'en
     * enumere aucun. ADR-0064 : lu dans le fichier <b>uniquement pour borner les tirages</b>, les
     * valeurs jugees venant toujours du chargeur reel (ADR-0013, obligation 1).
     *
     * <p>Partage avec les garde-fous voisins qui tirent sur les memes paquets, comme
     * {@link #paquets(Path)} l'est deja. Ce qui est partage est un comptage de lignes, jamais le
     * critere garde : chaque garde-fou garde sa boucle de tirage et sa propre assertion
     * d'exhaustivite, sans quoi une seule regle vaudrait pour cinq coquilles vides.</p>
     */
    static int itemsDeclares(Path racine, String paquet) {
        Path fichier = racine.resolve("contenu").resolve(paquet + ".yaml");
        try {
            return (int) Files.readAllLines(fichier).stream()
                    .filter(ligne -> ligne.strip().startsWith("- enonce:"))
                    .count();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les items que la mecanique peut reellement recevoir de ce paquet, tires par le chargeur reel
     * et non relus dans le YAML : c'est la chaine complete (schema, fabrique, source de contenu)
     * qui doit conserver l'indice, et c'est en son milieu qu'il se perdait.
     */
    private static List<Question> itemsTires(ContentSource contenu, int tirages) {
        RandomSource hasard = new Random(7)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (int tirage = 0; tirage < tirages; tirage++) {
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

    static void verifierQueChaquePaquetPorteUnIndice(Path racine, String racineClasspath, Set<String> dette) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        for (String paquet : paquets) {
            if (dette.contains(paquet)) {
                continue;
            }
            // ADR-0064 : la borne vient du paquet, jamais d'un nombre ecrit ici. De quoi
            // l'epuiser, plus un tirage pour constater l'epuisement.
            int declares = itemsDeclares(racine, paquet);
            int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
            List<Question> items =
                    itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet), tirages);
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
            for (Question item : items) {
                assertThat(item.indice())
                        .as("paquet %s, item \"%s\" : la mecanique promet une aide apres trois echecs"
                                + " consecutifs (S3-2) et ne peut afficher que ce que le contenu lui"
                                + " donne. Un item sans indice rend cette promesse muette pour"
                                + " l'enfant qui echoue", paquet, item.enonce())
                        .isPresent();
                assertThat(item.indice().orElseThrow())
                        .as("paquet %s, item \"%s\" : un indice vide est un indice absent",
                                paquet, item.enonce())
                        .isNotBlank();
            }
        }
    }

    @Test
    void chaquePaquetDeProductionPorteUnIndiceSurChacunDeSesItems() {
        verifierQueChaquePaquetPorteUnIndice(CONTENU_DE_PRODUCTION, "", DETTE_DE_CONTENU);
    }

    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_CONTENU)
                .as("ADR-0016 : la dette de paquets sans aide ne remonte jamais")
                .hasSizeLessThanOrEqualTo(PLAFOND_DE_DETTE);

        assertThat(paquets(CONTENU_DE_PRODUCTION))
                .as("ADR-0016 : une dette qui nomme un paquet disparu ou renomme exempte un nom que"
                        + " plus rien ne porte")
                .containsAll(DETTE_DE_CONTENU);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a inscrit nulle part - ni
     * dans le catalogue, ni dans une liste de test. Seul un perimetre derive du repertoire peut le
     * voir, et c'est la forme exacte qu'aurait un troisieme type de contenu arrivant demain sans
     * savoir porter d'indice.
     */
    @Test
    void unPaquetNeufSansIndiceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaquePaquetPorteUnIndice(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-sans-indice")
                .hasMessageContaining("promet une aide");
    }

    /** Le meme paquet neuf, une fois son indice ecrit, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetMuniDUnIndicePasseLeGardeFou() {
        verifierQueChaquePaquetPorteUnIndice(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige", Set.of());
    }
}
