package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0080, garde-fou 4 : sur un paquet de designation, la bonne zone n'est pas le meme identifiant
 * sur une majorite d'items, et n'est pas la plus grande zone de son item sur une majorite d'items.
 *
 * <p>Transposition d'un defaut mesure, pas une precaution : {@code S12-3} a ete refuse parce qu'un
 * enfant qui pressait toujours la plus grande proposition gagnait 64,6 % des manches sans lire un
 * enonce ({@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}). <b>Le seuil retenu est
 * le meme</b> : une strategie qui ignore le contenu ne doit jamais etre majoritaire (plus de la
 * moitie des items), la moitie pile n'echouant pas - meme comparaison stricte que la clause soeur.</p>
 *
 * <p><b>Troisieme clause, amendement A2 du 2026-10-06 (s52)</b> : la bonne zone n'est pas la
 * premiere zone declaree de son item sur une majorite d'items, ni la derniere. L'ordre de
 * declaration est l'ordre de tabulation des boutons (ADR-0080, 7.1) : une strategie qui presse
 * toujours le premier ou le dernier bouton ignore le contenu comme les deux autres. Demande de
 * {@code nadia-prof} (arbitrage prealable de s29, section 7 : "la troisieme forme de la meme
 * contrefacon"), verifiee a la main par elle jusqu'a cet amendement. Chaque rang est juge seul,
 * avec le meme seuil.</p>
 *
 * <p><b>Cablee sur le contenu de production depuis S33-PAL2-1 (s34).</b> Le premier paquet de
 * designation servi ({@code cp-frise-de-la-journee}) donne enfin une population reelle a la clause
 * ci-dessous, ecrite et eprouvee sur des paquets fictifs depuis s28 en attendant ce jour.</p>
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les items viennent de la chaine reelle
 * ({@link YamlContentPackLoader}), jamais d'une lecture a la main du YAML ; derivation totale -
 * chaque item de designation d'un paquet est examine, sans exception ; mise a l'epreuve - deux
 * paquets fautifs distincts, l'un pour chaque moitie de la clause, et un paquet sain qui doit rester
 * vert.</p>
 */
class LaBonneZoneNEstPasPredictibleSurUnPaquetTest {

    private static final int BUDGET_DE_TIRAGES = 500;

    private static List<ZoneADesigner> itemsDeDesignation(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<ZoneADesigner> items = new ArrayList<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            Item item = tire.orElseThrow();
            if (item instanceof ZoneADesigner zone) {
                items.add(zone);
            }
            dejaVus.add(item.id());
        }
        return items;
    }

    private static int aire(Zone zone) {
        return zone.cadre().largeur() * zone.cadre().hauteur();
    }

    /** Vrai quand {@code bonneZone} est strictement la plus grande des zones de son item. */
    private static boolean bonneZoneEstLaPlusGrande(ZoneADesigner item) {
        Zone bonne = item.zones().stream()
                .filter(zone -> zone.id().equals(item.bonneZone()))
                .findFirst()
                .orElseThrow();
        int aireDeLaBonne = aire(bonne);
        return item.zones().stream()
                .allMatch(zone -> zone.id().equals(item.bonneZone()) || aire(zone) < aireDeLaBonne);
    }

    /**
     * Les quatre formes de la clause, chacune avec son propre defaut - une seule depassant le seuil
     * ne doit pas etre masquee par une autre.
     */
    static List<String> verifierQueLaBonneZoneNEstPasPredictible(String paquetId,
                                                                   List<ZoneADesigner> items) {
        List<String> defauts = new ArrayList<>();
        if (items.isEmpty()) {
            return defauts;
        }
        Map<String, Integer> occurrencesParIdentifiant = new HashMap<>();
        int plusGrande = 0;
        int premiere = 0;
        int derniere = 0;
        for (ZoneADesigner item : items) {
            occurrencesParIdentifiant.merge(item.bonneZone(), 1, Integer::sum);
            if (bonneZoneEstLaPlusGrande(item)) {
                plusGrande++;
            }
            if (item.zones().get(0).id().equals(item.bonneZone())) {
                premiere++;
            }
            if (item.zones().get(item.zones().size() - 1).id().equals(item.bonneZone())) {
                derniere++;
            }
        }
        int maxMemeIdentifiant = occurrencesParIdentifiant.values().stream()
                .mapToInt(Integer::intValue).max().orElse(0);

        if (maxMemeIdentifiant * 2 > items.size()) {
            defauts.add("paquet " + paquetId + " : la bonne zone porte le meme identifiant sur "
                    + maxMemeIdentifiant + " items sur " + items.size() + " - une strategie qui"
                    + " designerait toujours cet identifiant, sans lire l'enonce, gagnerait plus de"
                    + " la moitie des manches");
        }
        if (plusGrande * 2 > items.size()) {
            defauts.add("paquet " + paquetId + " : la bonne zone est la plus grande zone de son item"
                    + " sur " + plusGrande + " items sur " + items.size() + " - une strategie qui"
                    + " designerait toujours la plus grande zone, sans lire l'enonce, gagnerait plus"
                    + " de la moitie des manches");
        }
        if (premiere * 2 > items.size()) {
            defauts.add("paquet " + paquetId + " : la bonne zone est la premiere zone declaree de son"
                    + " item sur " + premiere + " items sur " + items.size() + " - une strategie qui"
                    + " presserait toujours le premier bouton, sans lire l'enonce, gagnerait plus de"
                    + " la moitie des manches");
        }
        if (derniere * 2 > items.size()) {
            defauts.add("paquet " + paquetId + " : la bonne zone est la derniere zone declaree de son"
                    + " item sur " + derniere + " items sur " + items.size() + " - une strategie qui"
                    + " presserait toujours le dernier bouton, sans lire l'enonce, gagnerait plus de"
                    + " la moitie des manches");
        }
        return defauts;
    }

    private static void verifier(List<String> defauts) {
        assertThat(defauts)
                .as("ADR-0080, garde-fou 4 : la bonne zone d'un paquet de designation n'est"
                        + " predictible ni par son identifiant, ni par sa taille, ni par son rang de"
                        + " declaration")
                .isEmpty();
    }

    private static List<ZoneADesigner> itemsDuPaquetFixture(String sousRepertoire, String paquetId) {
        String racineClasspath = "/contreexemple/LaBonneZoneNEstPasPredictibleSurUnPaquetTest/"
                + sousRepertoire;
        ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId);
        return itemsDeDesignation(contenu);
    }

    @Test
    void laBonneZoneToujoursSurLeMemeIdentifiantFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("toujours-le-meme-identifiant",
                "faux-paquet-designation");
        assertThat(items).isNotEmpty();

        assertThatThrownBy(() -> verifier(
                verifierQueLaBonneZoneNEstPasPredictible("faux-paquet-designation", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("meme identifiant")
                .hasMessageContaining("3 items sur 4")
                .hasMessageNotContaining("plus grande zone")
                .hasMessageNotContaining("zone declaree");
    }

    @Test
    void laBonneZoneToujoursLaPlusGrandeFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("toujours-la-plus-grande",
                "faux-paquet-designation");
        assertThat(items).isNotEmpty();

        assertThatThrownBy(() -> verifier(
                verifierQueLaBonneZoneNEstPasPredictible("faux-paquet-designation", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("plus grande zone")
                .hasMessageContaining("3 items sur 4")
                .hasMessageNotContaining("meme identifiant")
                .hasMessageNotContaining("zone declaree");
    }

    @Test
    void laBonneZoneLaPlusSouventPremiereDeclareeFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("toujours-la-premiere",
                "faux-paquet-designation");
        assertThat(items).hasSize(4);

        assertThatThrownBy(() -> verifier(
                verifierQueLaBonneZoneNEstPasPredictible("faux-paquet-designation", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("premiere zone declaree")
                .hasMessageContaining("3 items sur 4")
                .hasMessageNotContaining("derniere zone declaree")
                .hasMessageNotContaining("meme identifiant")
                .hasMessageNotContaining("plus grande zone");
    }

    @Test
    void laBonneZoneLaPlusSouventDerniereDeclareeFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("toujours-la-derniere",
                "faux-paquet-designation");
        assertThat(items).hasSize(4);

        assertThatThrownBy(() -> verifier(
                verifierQueLaBonneZoneNEstPasPredictible("faux-paquet-designation", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("derniere zone declaree")
                .hasMessageContaining("3 items sur 4")
                .hasMessageNotContaining("premiere zone declaree")
                .hasMessageNotContaining("meme identifiant")
                .hasMessageNotContaining("plus grande zone");
    }

    /**
     * Un paquet dont ni l'identifiant ni la taille de la bonne zone ne sont majoritaires doit rester
     * vert, sans quoi ce garde-fou refuserait n'importe quel paquet de designation.
     */
    @Test
    void unPaquetVarieResteVert() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("varie", "vrai-paquet-designation");
        assertThat(items).isNotEmpty();

        verifier(verifierQueLaBonneZoneNEstPasPredictible("vrai-paquet-designation", items));
    }

    /**
     * <b>S33-PAL2-1 : le balayage de production, du depuis ADR-0080 et jamais ecrit avant ce lot.</b>
     * Perimetre derive - chaque jeu du catalogue de production ({@link YamlContentPackLoader}, le
     * meme appel que {@code ContenuConfiguration}), chaque item de designation qu'il tire. ADR-0051 :
     * au moins un paquet de designation reellement examine, sans quoi cette clause serait verte sur
     * une population vide.
     */
    @Test
    void surLeContenuReelDeProductionLaBonneZoneNEstPasPredictible() {
        List<String> defauts = new ArrayList<>();
        int paquetsDeDesignationExamines = 0;
        for (GameDefinition jeu : new YamlContentPackLoader().games()) {
            List<ZoneADesigner> items = itemsDeDesignation(jeu.contenu());
            if (items.isEmpty()) {
                continue;
            }
            paquetsDeDesignationExamines++;
            defauts.addAll(verifierQueLaBonneZoneNEstPasPredictible(jeu.id(), items));
        }

        assertThat(paquetsDeDesignationExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun paquet de designation reel")
                .isPositive();
        assertThat(defauts).isEmpty();
    }
}
