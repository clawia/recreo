package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0080, amendement du 2026-09-04 (theo-archi) : le cliquet promis pour la dette des
 * garde-fous 4, 5 et 6 - {@code LaBonneZoneNEstPasPredictibleSurUnPaquetTest},
 * {@code AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest} et
 * {@code ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest} (adapter-content) - qui
 * mordent sur des fixtures passant par la chaine reelle mais ne balaient pas
 * {@code src/main/resources} : aucun paquet de type {@code designation} n'y existe a ce jour, et
 * une clause qui scannerait cette population viderait ADR-0051.
 *
 * <p><b>Le fait, dans les mots de l'ADR</b> : <i>"un garde-fou qui compte ces paquets et qui
 * exige, des que le compte depasse zero, que les trois balayages les traversent, devient rouge le
 * jour meme ou la dette est due - pas au sprint suivant, pas a la relecture"</i>. Ce fichier est ce
 * garde-fou, ecrit en avance sur {@code T6} plutot qu'avec lui - motif dans {@code CLAUDE.md},
 * section "Prerequis de build" : ce depot a deja documente quatre fois, sans jamais la payer, la
 * dette du refus de {@code mvn-jdk21.sh}. Un plafond disponible et gratuit aujourd'hui n'attend
 * pas le lot qui creera la dette qu'il garde.
 *
 * <p><b>Ce qu'il garde, et ce qu'il ne garde pas.</b> Il ne rejoue aucune des trois proprietes
 * ("la bonne zone n'est pas previsible", "l'enonce ne nomme pas le libelle", "chaque cle
 * facultative est lue") : ce sont les trois fichiers cites qui les portent, et eux seuls. Il
 * verifie seulement que la population qu'ils jugent aujourd'hui sur fixture - les paquets de
 * type {@code designation} sous {@code src/main/resources} - reste vide. Le jour ou elle ne l'est
 * plus, il echoue et nomme les trois fichiers a etendre : c'est {@code T6} qui les etend
 * reellement, ce garde-fou ne fait que constater que le moment est venu, dans le geste meme qui
 * fait entrer le premier paquet.
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les repertoires de contenu viennent de
 * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest#repertoiresDeContenu}, jamais d'une
 * liste ecrite ici ; derivation totale - chaque fichier YAML de chaque repertoire est charge par
 * la meme bibliotheque que le chargeur de production ; mise a l'epreuve - un paquet de
 * designation fabrique en memoire, ajoute a la population reelle, fait rougir le garde-fou et son
 * message nomme les trois balayages a etendre.
 */
class AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest {

    /** La cle et la valeur qui font d'un paquet un paquet de designation (ADR-0080). */
    private static final String CLE_DE_TYPE = "type";
    private static final String TYPE_DESIGNATION = "designation";

    /**
     * Les trois garde-fous de production qui devront etendre leur balayage a cette population le
     * jour ou elle n'est plus vide. Nommes ici pour que le message d'echec dise quoi faire, pas
     * seulement que quelque chose ne va pas.
     */
    private static final List<String> BALAYAGES_A_ETENDRE = List.of(
            "LaBonneZoneNEstPasPredictibleSurUnPaquetTest",
            "AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest",
            "ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest");

    /**
     * Le plafond vaut exactement la dette : zero paquet de type {@code designation} servi sous
     * {@code src/main/resources} a ce jour (mesure le 2026-09-04). Ce nombre ne remonte jamais
     * sans que le premier paquet reel ne l'accompagne dans le meme diff, comme
     * {@code PLAFOND_DE_DETTE} d'ADR-0013 et {@code PLAFOND_DE_DECLARATION} d'ADR-0081.
     */
    private static final int PLAFOND_DE_DESIGNATION_EN_PRODUCTION = 0;

    /** ADR-0013 obligation 3 : le jeu fautif commite de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest");

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Path> fichiersYaml(Path repertoire) {
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les paquets d'une population de repertoires de contenu, charges par la meme bibliotheque que
     * le chargeur de production - jamais lus a la main.
     */
    static List<Object> corpsCharges(List<Path> repertoiresDeContenu) {
        List<Object> corps = new ArrayList<>();
        for (Path repertoire : repertoiresDeContenu) {
            for (Path fichier : fichiersYaml(repertoire)) {
                corps.add(new Yaml().load(lire(fichier)));
            }
        }
        return corps;
    }

    /** Le compte que la dette d'ADR-0080 surveille : les paquets dont le type est designation. */
    static long paquetsDeDesignation(List<Object> corpsCharges) {
        return corpsCharges.stream()
                .filter(corps -> corps instanceof Map<?, ?> champs
                        && TYPE_DESIGNATION.equals(champs.get(CLE_DE_TYPE)))
                .count();
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath. Le plafond est un parametre plutot qu'une
     * constante lue directement, pour que la mise a l'epreuve puisse mordre dans les deux sens -
     * le compte qui monte, et un plafond que rien ne justifie plus.
     */
    static void verifier(List<Object> corpsCharges, int plafond) {
        assertThat(corpsCharges)
                .as("ADR-0051 : aucun paquet lu sous cette population, ce garde-fou ne prouverait"
                        + " rien")
                .isNotEmpty();

        long compte = paquetsDeDesignation(corpsCharges);
        if (compte > plafond) {
            throw new AssertionError(compte + " paquet(s) de type '" + TYPE_DESIGNATION
                    + "' servi(s) sous src/main/resources, contre " + plafond + " tolere(s) : la"
                    + " dette d'ADR-0080 (amendement du 2026-09-04) est due. Etendre le balayage"
                    + " de production de " + BALAYAGES_A_ETENDRE + " (T6), puis monter"
                    + " PLAFOND_DE_DESIGNATION_EN_PRODUCTION a " + compte + " dans le meme diff.");
        }
        if (compte < plafond) {
            throw new AssertionError("PLAFOND_DE_DESIGNATION_EN_PRODUCTION vaut " + plafond
                    + " mais seulement " + compte + " paquet(s) de type '" + TYPE_DESIGNATION
                    + "' sont servis : un plafond superieur au compte reel est une place libre,"
                    + " pas un cliquet (ADR-0013). Baisser PLAFOND_DE_DESIGNATION_EN_PRODUCTION"
                    + " a " + compte + ".");
        }
    }

    /**
     * Le garde-fou tel qu'il tourne en CI : la population reelle, le plafond reel. Vert
     * aujourd'hui parce que le compte et le plafond valent tous les deux zero ; rouge le jour ou
     * un premier paquet de designation est servi, tant que le plafond n'a pas monte avec lui.
     */
    @Test
    void aucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayages() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Path> repertoires =
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine);

        verifier(corpsCharges(repertoires), PLAFOND_DE_DESIGNATION_EN_PRODUCTION);
    }

    /**
     * <b>Morsure 1.</b> Un paquet de designation fabrique, ajoute a la population reelle, fait
     * rougir le garde-fou avec un message qui nomme les trois balayages a etendre - pas
     * seulement qu'un compte a change.
     */
    @Test
    void unPaquetDeDesignationEnProductionFaitEchouerLeGardeFouEtNommeLesTroisBalayages() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Object> corps = new ArrayList<>(corpsCharges(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine)));
        corps.add(Map.of(CLE_DE_TYPE, TYPE_DESIGNATION));

        assertThatThrownBy(() -> verifier(corps, PLAFOND_DE_DESIGNATION_EN_PRODUCTION))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("1 paquet(s)")
                .hasMessageContaining("LaBonneZoneNEstPasPredictibleSurUnPaquetTest")
                .hasMessageContaining("AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest")
                .hasMessageContaining("ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest")
                .hasMessageContaining("balayage de production");
    }

    /**
     * <b>Morsure 2.</b> Le compte n'est pas trompe par la simple presence d'une cle {@code type} :
     * un paquet d'un autre type ajoute a la meme population ne fait pas bouger le compte de
     * designation. Meme famille de precaution qu'ADR-0070 - une detection large passerait sa
     * propre epreuve et echouerait dans le monde.
     */
    @Test
    void unPaquetDUnAutreTypeNeFaitJamaisRougirLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Object> corps = new ArrayList<>(corpsCharges(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine)));
        corps.add(Map.of(CLE_DE_TYPE, "question"));

        verifier(corps, PLAFOND_DE_DESIGNATION_EN_PRODUCTION);
    }

    /**
     * <b>Morsure 3.</b> L'autre borne : un plafond que le compte reel ne justifie plus est rouge
     * lui aussi, comme {@code AucunSigneDoubleNEstDetachableDeSonMotTest} l'exige de sa propre
     * dette (ADR-0067, "en egalite, jamais en plancher").
     */
    @Test
    void unPlafondSuperieurAuCompteReelFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Object> corps = corpsCharges(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));

        assertThatThrownBy(() -> verifier(corps, PLAFOND_DE_DESIGNATION_EN_PRODUCTION + 1))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("place libre");
    }

    /** La clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of(), PLAFOND_DE_DESIGNATION_EN_PRODUCTION))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, sur le <b>jeu fautif commite</b> plutot que sur une mutation en
     * memoire : {@code avec-designation/} porte un paquet de type designation et rougit, tandis
     * que {@code sans-designation/} du meme jeu, charge separement, reste vert. Preuve que la
     * lecture disque -> SnakeYAML -> comptage fonctionne reellement, pas seulement le juge pur.
     */
    @Test
    void leJeuFautifCommiteEstRefuseEtLePaquetSainDuMemeJeuNeLEstPas() {
        List<Object> sain = corpsCharges(List.of(CONTRE_EXEMPLE.resolve("sans-designation")));
        assertThat(sain).as("le jeu fautif ne prouve rien sans paquet sain").isNotEmpty();
        verifier(sain, PLAFOND_DE_DESIGNATION_EN_PRODUCTION);

        List<Object> fautif = corpsCharges(List.of(CONTRE_EXEMPLE.resolve("avec-designation")));
        assertThat(fautif).as("le jeu fautif ne prouve rien sans paquet").isNotEmpty();
        assertThatThrownBy(() -> verifier(fautif, PLAFOND_DE_DESIGNATION_EN_PRODUCTION))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LaBonneZoneNEstPasPredictibleSurUnPaquetTest")
                .hasMessageContaining("AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest")
                .hasMessageContaining("ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest");
    }
}
