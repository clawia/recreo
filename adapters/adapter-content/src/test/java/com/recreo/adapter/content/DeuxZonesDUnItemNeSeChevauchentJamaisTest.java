package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.CadreDeZone;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0080, amendement A2 du 2026-10-06 (s52) : {@code ZON-13}, moitie derivable - deux zones d'un
 * meme item ne se chevauchent pas. Motif de {@code nadia-prof} (arbitrage prealable de s29, ZON-13) :
 * deux boutons superposes rendent la designation ambigue pour l'enfant, il croit montrer l'un et
 * presse l'autre, alors que le verdict reste determine cote serveur. Arbitrage de s51, section 6.3 :
 * la clause ne se relache pas pour les lignes candidates qui se croisent.
 *
 * <p><b>Le critere</b> : deux cadres se chevauchent quand leurs interieurs se coupent, c'est-a-dire
 * quand leurs projections se recouvrent strictement sur les deux axes. Deux cadres qui partagent un
 * bord ne se chevauchent pas : les cases voisines d'un quadrillage sont bord a bord, et un critere
 * large les refuserait toutes. La comparaison est faite <b>dans un item</b>, jamais entre items : les
 * items d'un plan reprennent les memes cadres, et une comparaison au paquet les refuserait.</p>
 *
 * <p><b>Ce que ce garde-fou ne voit pas</b> : la moitie debordement de {@code ZON-13} (une zone qui
 * sort de ce que le dessin montre) et une zone qui contient un segment d'une autre ligne du dessin.
 * Les deux demandent la geometrie du dessin, qui vit dans le SVG et pas dans le paquet ; elles
 * restent a {@code nadia-prof}, en jouant.</p>
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les items viennent de la chaine reelle
 * ({@link YamlContentPackLoader}), chaque jeu du catalogue de production dont les items sont des
 * designations ; derivation totale - chaque paire de zones de chaque item ; mise a l'epreuve - un
 * paquet fautif (un recouvrement d'une unite, en coin) et un paquet sain (cases bord a bord, items
 * qui reprennent les memes cadres), plus un recouvrement injecte dans un item reel de production.</p>
 */
class DeuxZonesDUnItemNeSeChevauchentJamaisTest {

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

    static boolean seChevauchent(CadreDeZone a, CadreDeZone b) {
        return a.x() < b.x() + b.largeur() && b.x() < a.x() + a.largeur()
                && a.y() < b.y() + b.hauteur() && b.y() < a.y() + a.hauteur();
    }

    private static String decrire(CadreDeZone cadre) {
        return "{ x: " + cadre.x() + ", y: " + cadre.y() + ", largeur: " + cadre.largeur()
                + ", hauteur: " + cadre.hauteur() + " }";
    }

    static List<String> chevauchements(String paquetId, List<ZoneADesigner> items) {
        List<String> defauts = new ArrayList<>();
        for (ZoneADesigner item : items) {
            List<Zone> zones = item.zones();
            for (int i = 0; i < zones.size(); i++) {
                for (int j = i + 1; j < zones.size(); j++) {
                    Zone une = zones.get(i);
                    Zone autre = zones.get(j);
                    if (seChevauchent(une.cadre(), autre.cadre())) {
                        defauts.add("paquet " + paquetId + ", item " + item.id().value()
                                + " : les zones \"" + une.id() + "\" " + decrire(une.cadre())
                                + " et \"" + autre.id() + "\" " + decrire(autre.cadre())
                                + " se chevauchent - l'enfant croit montrer l'une et presse l'autre"
                                + " (ZON-13)");
                    }
                }
            }
        }
        return defauts;
    }

    private static void verifier(List<String> defauts) {
        assertThat(defauts)
                .as("ADR-0080, amendement A2 : deux zones d'un item de designation ne se chevauchent"
                        + " pas (ZON-13)")
                .isEmpty();
    }

    private static List<ZoneADesigner> itemsDuPaquetFixture(String sousRepertoire, String paquetId) {
        String racineClasspath = "/contreexemple/DeuxZonesDUnItemNeSeChevauchentJamaisTest/"
                + sousRepertoire;
        return itemsDeDesignation(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId));
    }

    @Test
    void unRecouvrementDUneUniteEnCoinFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("recouvrement-d-une-unite",
                "faux-paquet-designation");
        assertThat(items).hasSize(2);

        assertThatThrownBy(() -> verifier(chevauchements("faux-paquet-designation", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("\"axe\"")
                .hasMessageContaining("\"diagonale\"")
                .hasMessageContaining("ZON-13");
        assertThat(chevauchements("faux-paquet-designation", items)).hasSize(1);
    }

    /**
     * Cases bord a bord et memes cadres repris d'un item a l'autre : les deux formes qu'un critere
     * trop large refuserait a tort.
     */
    @Test
    void desCasesBordABordEtDesCadresReprisDUnItemALAutreRestentVerts() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("bord-a-bord", "vrai-paquet-designation");
        assertThat(items).hasSize(2);

        verifier(chevauchements("vrai-paquet-designation", items));
    }

    /**
     * Balayage de production de src/main/resources par la chaine reelle. ADR-0051 : au moins un
     * paquet de designation reellement examine.
     */
    @Test
    void surLeContenuReelDeProductionDeuxZonesDUnItemNeSeChevauchentJamais() {
        List<String> defauts = new ArrayList<>();
        int paquetsDeDesignationExamines = 0;
        for (GameDefinition jeu : new YamlContentPackLoader().games()) {
            List<ZoneADesigner> items = itemsDeDesignation(jeu.contenu());
            if (items.isEmpty()) {
                continue;
            }
            paquetsDeDesignationExamines++;
            defauts.addAll(chevauchements(jeu.id(), items));
        }

        assertThat(paquetsDeDesignationExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun paquet de designation reel")
                .isPositive();
        verifier(defauts);
    }

    /**
     * Morsure sur materiau reel : dans le premier item de designation de production, la deuxieme
     * zone declaree recoit le cadre de la premiere - le copier-coller de cadre, qu'un paquet dont
     * les items reprennent les memes cadres rend facile.
     */
    @Test
    void unCadreRecopieDansUnItemReelDeProductionFaitEchouerLeGardeFou() {
        GameDefinition jeu = new YamlContentPackLoader().games().stream()
                .filter(candidat -> !itemsDeDesignation(candidat.contenu()).isEmpty())
                .findFirst()
                .orElseThrow();
        ZoneADesigner reel = itemsDeDesignation(jeu.contenu()).get(0);
        List<Zone> zones = new ArrayList<>(reel.zones());
        Zone deuxieme = zones.get(1);
        zones.set(1, new Zone(deuxieme.id(), deuxieme.libelle(), zones.get(0).cadre()));
        ZoneADesigner mute = new ZoneADesigner(reel.id(), reel.enonce(), reel.illustration(),
                reel.illustrationTexteAlternatif(), zones, reel.bonneZone(), reel.indice(),
                reel.explication(), reel.texteAVoixHaute(), reel.emplacementDuLibelleDeZone());

        verifier(chevauchements(jeu.id(), List.of(reel)));
        assertThatThrownBy(() -> verifier(chevauchements(jeu.id(), List.of(mute))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(jeu.id())
                .hasMessageContaining("\"" + deuxieme.id() + "\"");
    }
}
