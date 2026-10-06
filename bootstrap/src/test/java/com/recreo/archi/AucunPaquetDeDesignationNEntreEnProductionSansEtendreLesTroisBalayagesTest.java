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
 * <p><b>Amendement du 2026-10-06 (s52, A1).</b> T6 est livre depuis s34 : les trois balayages lisent
 * la production. Le message ne demande donc plus de les etendre ; il dit qu'ils doivent etre verts
 * sur le paquet neuf et que le plafond monte dans le diff qui le sert. Le nom de la classe garde la
 * trace de sa dette d'origine.
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
     * Les trois garde-fous qui jugent cette population. Ils balaient la production depuis s34 (T6) :
     * ils sont nommes ici pour que le message d'echec dise lesquels doivent etre verts sur le paquet
     * neuf, pas seulement qu'un compte a change.
     */
    private static final List<String> BALAYAGES_DE_PRODUCTION = List.of(
            "LaBonneZoneNEstPasPredictibleSurUnPaquetTest",
            "AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest",
            "ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest");

    /**
     * Le plafond vaut exactement le compte reel. Il valait zero du 2026-09-04 (mesure sur l'arbre a
     * cette date) au s34 : aucun paquet de type {@code designation} n'etait alors servi sous
     * {@code src/main/resources}. Il monte a un avec le premier paquet reel
     * ({@code cp-frise-de-la-journee}, QLM-CP-14), comme {@code PLAFOND_DE_DETTE} d'ADR-0013 et
     * {@code PLAFOND_DE_DECLARATION} d'ADR-0081 l'exigent de leur propre dette.
     *
     * <p><b>Il monte a deux avec {@code cp-plan-de-la-classe} (QLM-CP-11, S35-QLM11-PLANCLASSE,
     * s35, lea-dev)</b> : les deux cadres trop etroits (`armoire`, `porte`) sont agrandis par
     * `ines-design`, et le recouvrement du dessin par le libelle cesse d'etre gardable par une
     * arithmetique (`ADR-0092`) - il reste tenu a l'oeil par `nadia-prof`, paquet par paquet, et par
     * le cliquet exact d'{@code AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest}.
     *
     * <p><b>Il monte a trois avec {@code ce1-symetrie} (MAT-CE1-17, S51-MAT-SYMETRIE, s52,
     * lea-dev)</b> : les trois balayages nommes ci-dessus sont verts sur le paquet neuf dans le meme
     * build, et l'avis de faisabilite de s52 (4.3) demandait exactement cette valeur.
     */
    private static final int PLAFOND_DE_DESIGNATION_EN_PRODUCTION = 3;

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
                    + "' servi(s) sous src/main/resources, contre " + plafond + " tolere(s). "
                    + BALAYAGES_DE_PRODUCTION + " lisent la production depuis s34 (T6, ADR-0080) :"
                    + " le paquet neuf y entre sans toucher a leur code, et ils doivent etre verts"
                    + " sur lui dans ce build. Monter PLAFOND_DE_DESIGNATION_EN_PRODUCTION a "
                    + compte + " dans le diff qui sert ce paquet.");
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
     * Le garde-fou tel qu'il tourne en CI : la population reelle, le plafond reel. Vert depuis s34
     * parce que le compte et le plafond valent tous les deux un ({@code cp-frise-de-la-journee},
     * S33-PAL2-1) ; rouge le jour ou un second paquet de designation est servi sans que le plafond
     * ne monte avec lui.
     */
    @Test
    void aucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayages() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Path> repertoires =
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine);

        verifier(corpsCharges(repertoires), PLAFOND_DE_DESIGNATION_EN_PRODUCTION);
    }

    /**
     * <b>Morsure 1.</b> Un paquet de designation fabrique, ajoute a la population reelle (qui en
     * porte deja un depuis S33-PAL2-1), fait rougir le garde-fou avec un message qui nomme les trois
     * balayages a etendre - pas seulement qu'un compte a change. Le compte attendu est derive du
     * compte reel plutot qu'ecrit en dur, pour ne pas se perimer au prochain paquet de designation.
     */
    @Test
    void unPaquetDeDesignationEnProductionFaitEchouerLeGardeFouEtNommeLesTroisBalayages() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Object> corpsReels = corpsCharges(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));
        long compteReel = paquetsDeDesignation(corpsReels);
        List<Object> corps = new ArrayList<>(corpsReels);
        corps.add(Map.of(CLE_DE_TYPE, TYPE_DESIGNATION));

        assertThatThrownBy(() -> verifier(corps, PLAFOND_DE_DESIGNATION_EN_PRODUCTION))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining((compteReel + 1) + " paquet(s)")
                .hasMessageContaining("LaBonneZoneNEstPasPredictibleSurUnPaquetTest")
                .hasMessageContaining("AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest")
                .hasMessageContaining("ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest")
                .hasMessageContaining("lisent la production depuis s34")
                .hasMessageNotContaining("Etendre le balayage");
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
     *
     * <p>Le plafond utilise ici est le litteral <b>0</b>, et non
     * {@code PLAFOND_DE_DESIGNATION_EN_PRODUCTION} : ce test isole le mecanisme de lecture sur son
     * propre jeu fautif, independant du compte reel de production - qui vaut desormais un depuis
     * S33-PAL2-1. Coupler les deux ferait echouer ce test des que la production sert un pack de
     * plus, ce qui n'a rien a voir avec ce qu'il prouve.</p>
     */
    @Test
    void leJeuFautifCommiteEstRefuseEtLePaquetSainDuMemeJeuNeLEstPas() {
        int plafondDuJeuFautif = 0;
        List<Object> sain = corpsCharges(List.of(CONTRE_EXEMPLE.resolve("sans-designation")));
        assertThat(sain).as("le jeu fautif ne prouve rien sans paquet sain").isNotEmpty();
        verifier(sain, plafondDuJeuFautif);

        List<Object> fautif = corpsCharges(List.of(CONTRE_EXEMPLE.resolve("avec-designation")));
        assertThat(fautif).as("le jeu fautif ne prouve rien sans paquet").isNotEmpty();
        assertThatThrownBy(() -> verifier(fautif, plafondDuJeuFautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LaBonneZoneNEstPasPredictibleSurUnPaquetTest")
                .hasMessageContaining("AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest")
                .hasMessageContaining("ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest");
    }
}
