package com.recreo.domain.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0004, complement au bytecode : ArchUnit voit les types, pas les litteraux. Ce test lit les
 * sources et refuse le vocabulaire de matiere a deux endroits, pour deux raisons distinctes.
 *
 * <p><b>Les mecaniques</b> (recursivement) : une mecanique qui nomme une matiere ne sert plus qu'a
 * elle, ce qui defait le principe directeur du produit.</p>
 *
 * <p><b>Les types partages de {@code domain/content}</b> (le paquet racine seul, ajoute par
 * ADR-0024) : ce sont les types que <i>toutes</i> les matieres traversent - Item, Question,
 * ContentDescriptor, ContentSource. Un champ de francais sur {@code Question} obligerait chaque
 * paquet de mathematiques a porter un mot qui ne le concerne pas, et c'est mot pour mot la dette 2
 * de la version precedente, dans l'autre sens : une configuration taillee pour l'arithmetique que
 * chaque contenu de francais devait remplir de valeurs factices. Le sous-paquet {@code content/source}
 * est <b>volontairement</b> hors perimetre : ADR-0006 y loge les bornes d'une matiere dans la source
 * qui en a besoin, et {@code AdditionsBorneesContentSource} nomme donc legitimement une somme et un
 * operande. Ce n'est pas une exception concedee, c'est la frontiere que la decision trace.</p>
 *
 * <p><b>ADR-0039, critere enumere assume.</b> Le vocabulaire est une liste ecrite a la main, et une
 * liste de refus est verte par defaut : une mecanique qui nommerait "verbe", "division" ou "triangle"
 * passerait sans etre reprise. C'est la meme famille que les deux criteres corriges en s12, et non
 * une autre comme la distinction de {@code yanis-qa} le proposait - "addition" generalise a tout
 * fichier futur qui emploie ce mot-la, exactement comme "bootstrap" generalisait a tout fichier
 * futur nomme ainsi ; ce qui separe les cas n'est pas la nature des membres mais la <b>polarite</b> de
 * la liste, et celle-ci est du cote refuse.
 *
 * <p>Elle y reste malgre cela, pour une raison de portee et non de nature : son complement ne
 * s'atteint que le jour ou une matiere neuve entre au catalogue, ce qui est une decision de
 * {@code camille-po} et de {@code nadia-prof} et non un fichier copie sans decision - la difference
 * avec le script tiers qui est entre vert en s11. Le declencheur d'extension est donc ecrit ici :
 * <b>toute matiere neuve au catalogue etend cette liste dans le meme lot</b>. Le recensement 3 de
 * {@code LesGardeFousSontMisALEpreuveTest} compte et plafonne cet aveu.
 *
 * <p>Son mode de defaillance est donc un mot oublie, un vert incomplet - jamais un objet non juge.
 * Un mot n'y entre qu'a deux conditions verifiees a la main :
 * il est specifique a une matiere, et il n'est la sous-chaine d'aucun mot courant. C'est pourquoi
 * "graphie", "phoneme" et "nasale" y entrent avec ADR-0024, et pourquoi "son" n'y entre pas : il est
 * dans "raison", "comparaison", "saison". Une regle qui rougit sur un mot etranger a son objet est
 * la faute mesuree en s05, et elle coute plus cher qu'une regle absente.</p>
 *
 * <p><b>Le declencheur qu'ADR-0058 section 2 avait ecrit d'avance est leve, et la mesure est
 * ici.</b> Il disait : "le lot qui ouvre la saisie decide de l'entree d'<i>accent</i> dans cette
 * liste, en la mesurant sur ses propres sources". Le lot 2 de s24 a ouvert la saisie ; la mesure du
 * 2026-08-31, sur les dix-huit sources des deux arbres que cette regle balaie, rend <b>une</b>
 * occurrence de la sous-chaine "accent" : la Javadoc de {@code ReponseAEcrire}, ou elle est dans
 * "integralement accentue" - la formulation de SAI-8 elle-meme, recopiee de l'arbitrage de
 * nadia-prof. Ce mot n'y entre donc pas : il tomberait sur le premier usage legitime, et c'est
 * exactement la seconde condition d'entree ci-dessus. Le motif de prudence de theo-archi est
 * confirme par une mesure au lieu d'etre reconduit sur une intuition, et le declencheur est
 * clos.</p>
 */
class AucunVocabulaireDeMatiereTest {

    private static final Path MECANIQUES = Path.of("src/main/java/com/recreo/domain/mechanics");

    /** ADR-0024 : les types que toutes les matieres traversent, sans le sous-paquet source. */
    private static final Path CONTENU_PARTAGE = Path.of("src/main/java/com/recreo/domain/content");

    /** ADR-0013 : le jeu fautif de ce garde-fou, deux arbres de sources jamais compiles. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/AucunVocabulaireDeMatiereTest");

    private static final List<String> VOCABULAIRE_DE_MATIERE = List.of(
            "addition", "soustraction", "multiplication", "operande", "somme",
            "calcul", "syllabe", "conjugaison", "fraction", "graphie", "phoneme", "nasale");

    static void verifierQuAucuneSourcePartageeNeNommeUneMatiere(Path racine) {
        List<Path> sources = new ArrayList<>();
        sources.addAll(sourcesSousLArbre(racine.resolve(MECANIQUES)));
        sources.addAll(sourcesDuPaquet(racine.resolve(CONTENU_PARTAGE)));

        assertThat(sources)
                .as("ADR-0004 : cette regle ne prouve rien s'il n'y a aucune source a lire sous %s",
                        racine)
                .isNotEmpty();

        for (Path source : sources) {
            String texte = lire(source).toLowerCase(Locale.ROOT);
            assertThat(VOCABULAIRE_DE_MATIERE.stream().filter(texte::contains).toList())
                    .as("ADR-0004, ADR-0024 : %s nomme une matiere. Une mecanique n'en connait"
                            + " aucune, et un type traverse par toutes les matieres n'en privilegie"
                            + " aucune", source)
                    .isEmpty();
        }
    }

    /** Les mecaniques : tout l'arbre, chaque mecanique ayant son propre paquet. */
    private static List<Path> sourcesSousLArbre(Path repertoire) {
        assertThat(repertoire)
                .as("ADR-0004 : le paquet des mecaniques doit exister pour que cette regle morde")
                .isDirectory();
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(fichier -> fichier.toString().endsWith(".java")).sorted().toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Le paquet racine du contenu, sans ses sous-paquets : voir la frontiere dans la Javadoc. */
    private static List<Path> sourcesDuPaquet(Path repertoire) {
        assertThat(repertoire)
                .as("ADR-0024 : le paquet des types de contenu partages doit exister")
                .isDirectory();
        try (Stream<Path> entrees = Files.list(repertoire)) {
            return entrees.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.toString().endsWith(".java"))
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

    @Test
    void aucuneMecaniqueNiTypePartageNeNommeUneMatiere() {
        verifierQuAucuneSourcePartageeNeNommeUneMatiere(Path.of(""));
    }

    /** ADR-0013 obligation 3, premier perimetre : une mecanique qui nomme une matiere. */
    @Test
    void uneMecaniqueQuiNommeUneMatiereFaitEchouerLaRegle() {
        assertThatThrownBy(() -> verifierQuAucuneSourcePartageeNeNommeUneMatiere(
                CONTRE_EXEMPLE.resolve("mecanique-qui-nomme-une-matiere")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("syllabe");
    }

    /**
     * Second perimetre, celui qu'ADR-0024 ajoute : un type traverse par toutes les matieres qui
     * gagne un champ de francais. C'est la tentation exacte du lot "Le bon son" - poser la graphie
     * sur {@code Question} plutot que de la laisser dans le paquet qui la declare.
     */
    @Test
    void unTypePartageQuiNommeUneGraphieFaitEchouerLaRegle() {
        assertThatThrownBy(() -> verifierQuAucuneSourcePartageeNeNommeUneMatiere(
                CONTRE_EXEMPLE.resolve("type-partage-qui-nomme-une-graphie")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("graphie");
    }

    /** Les memes arbres, le vocabulaire retire, ne doivent plus rien reprocher. */
    @Test
    void lesMemesArbresSansVocabulaireDeMatierePassentLaRegle() {
        verifierQuAucuneSourcePartageeNeNommeUneMatiere(CONTRE_EXEMPLE.resolve("corrige"));
    }
}
