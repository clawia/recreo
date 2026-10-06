package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S51-MAT-SYMETRIE (MAT-CE1-17) : le paquet "ce1-symetrie", joue par "designation-symetrie-ce1".
 * Arbitrage prealable de nadia-prof (squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md,
 * section 3) : SYM-CE1-1 a SYM-CE1-3, bornes de grille de DEP-CE1-2 ; amendement A.7 de DEC-0111 pour le
 * plancher de zone.
 *
 * <p>Ce test porte ce qui se decide sur le YAML. Ce que les dessins montrent (un axe est un axe, une
 * ligne qui coupe en deux parties de meme aire, la case manquante) est juge par
 * {@code LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest}, module bootstrap, seul module qui voie
 * a la fois le contenu et les dessins. Les garde-fous generiques de designation (ZON-8, ZON-13, rang de
 * la bonne zone, enonce, indice et texte alternatif qui ne nomment pas la zone) jugent ce paquet sans
 * l'etre ici une seconde fois.</p>
 */
class PaquetCe1SymetrieTest {

    private static final String PAQUET = "ce1-symetrie";

    private static final String ENONCE_D_AXE = "Touche le numéro de l'axe de symétrie.";

    private static final String ENONCE_DE_COMPLETION =
            "Touche le numéro de la case qui complète la figure.";

    /** DEC-0111 A.7 : la barre de refus est 15 unites sur un carre de 100 (44 px a 296 px). */
    private static final int BARRE_DE_ZONE_EN_UNITES = 15;

    private static List<ZoneADesigner> tousLesItems() {
        ContentSource contenu = new YamlContentPackLoader().chargerPaquet(PAQUET);
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<ZoneADesigner> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((ZoneADesigner) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static List<ZoneADesigner> reconnaissances() {
        return tousLesItems().stream().filter(item -> ENONCE_D_AXE.equals(item.enonce())).toList();
    }

    private static List<ZoneADesigner> completions() {
        return tousLesItems().stream().filter(item -> ENONCE_DE_COMPLETION.equals(item.enonce())).toList();
    }

    private static Map<?, ?> catalogue(String idDuJeu) {
        try (java.io.InputStream flux = PaquetCe1SymetrieTest.class.getResourceAsStream("/catalogue.yaml")) {
            Object corps = new Yaml().load(flux);
            for (Object entree : (List<?>) ((Map<?, ?>) corps).get("jeux")) {
                if (entree instanceof Map<?, ?> jeu && idDuJeu.equals(jeu.get("id"))) {
                    return jeu;
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        throw new AssertionError("le catalogue servi ne porte pas " + idDuJeu);
    }

    @Test
    void lePaquetCompteAuMoinsDixItemsEtPlusQueLesHuitManches() {
        Map<?, ?> jeu = catalogue("designation-symetrie-ce1");
        assertThat(jeu.get("nombreDeManches")).as("DEC-0111 5.4 : huit manches").isEqualTo(8);
        assertThat(tousLesItems()).as("FER-2 et FER-3 : dix items au moins, plus que de manches")
                .hasSizeGreaterThanOrEqualTo(10).hasSizeGreaterThan(8);
    }

    @Test
    void chaqueItemEstUneReconnaissanceOuUneCompletionEtChacuneEnPorteTroisAuMoins() {
        assertThat(reconnaissances().size() + completions().size())
                .as("aucun item hors des deux taches").isEqualTo(tousLesItems().size());
        assertThat(reconnaissances()).as("FER-3 : reconnaitre un axe").hasSizeGreaterThanOrEqualTo(3);
        assertThat(completions()).as("FER-3 : completer par symetrie").hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    void chaqueItemAsonDessinEtDeuxItemsNePartagentJamaisLeMemeDessin() {
        List<String> dessins = tousLesItems().stream().map(ZoneADesigner::illustration).toList();
        assertThat(new HashSet<>(dessins)).as("un dessin par item, sinon deux items sont le meme ecran")
                .hasSameSizeAs(dessins);
    }

    @Test
    void leTexteAlternatifEstUnPourChaqueTypeDeDessinEtNeDonneAucuneReponse() {
        Set<String> parReconnaissance = new HashSet<>();
        Set<String> parCompletion = new HashSet<>();
        reconnaissances().forEach(item -> parReconnaissance.add(item.illustrationTexteAlternatif()));
        completions().forEach(item -> parCompletion.add(item.illustrationTexteAlternatif()));
        assertThat(parReconnaissance).as("un seul texte alternatif pour les figures").hasSize(1);
        assertThat(parCompletion).as("un seul texte alternatif pour les quadrillages").hasSize(1);
        for (String texte : List.of(parReconnaissance.iterator().next(), parCompletion.iterator().next())) {
            assertThat(texte.toLowerCase()).as("le texte alternatif ne nomme ni l'axe ni une zone")
                    .doesNotContain("axe", "zone", "case manquante")
                    .doesNotContainPattern("\\d");
        }
    }

    @Test
    void lEnonceNeDecritPasLaProprieteDeLAxeNiLaPositionDeLaCaseAttendue() {
        for (ZoneADesigner item : tousLesItems()) {
            String enonce = item.enonce().toLowerCase();
            assertThat(enonce).as("SYM-CE1-1 : '%s' ne decrit pas la propriete", item.enonce())
                    .doesNotContain("pli", "superpos", "deux parties", "moitié", "miroir", "autre côté")
                    .doesNotContainPattern("\\d");
        }
    }

    @Test
    void chaqueItemPorteAuMoinsTroisZonesDeMemeTailleSousLaBarreDeNadiaProf() {
        for (ZoneADesigner item : tousLesItems()) {
            assertThat(item.zones()).as("ZON-8 : trois zones au moins pour '%s'", item.illustration())
                    .hasSizeGreaterThanOrEqualTo(3);
            assertThat(item.zones().size()).as("legende de cinq libelles au plus (ines-design, 4.3)")
                    .isLessThanOrEqualTo(5);
            Set<String> tailles = new HashSet<>();
            for (Zone zone : item.zones()) {
                tailles.add(zone.cadre().largeur() + "x" + zone.cadre().hauteur());
                assertThat(Math.min(zone.cadre().largeur(), zone.cadre().hauteur()))
                        .as("DEC-0111 A.7 : '%s' au moins %d unites", item.illustration(),
                                BARRE_DE_ZONE_EN_UNITES)
                        .isGreaterThanOrEqualTo(BARRE_DE_ZONE_EN_UNITES);
            }
            assertThat(tailles).as("zones de meme taille dans '%s'", item.illustration()).hasSize(1);
        }
    }

    @Test
    void lesZonesDesFiguresFontSeizeUnitesEtCellesDuQuadrillageSontLaCaseEntiere() {
        for (ZoneADesigner item : reconnaissances()) {
            item.zones().forEach(zone -> assertThat(zone.cadre().largeur() + "x" + zone.cadre().hauteur())
                    .as("marge de dessin d'ines-design : 16 unites").isEqualTo("16x16"));
        }
        for (ZoneADesigner item : completions()) {
            Zone premiere = item.zones().get(0);
            assertThat(premiere.cadre().largeur()).as("une case de n x n : 96 / n unites, n pair")
                    .isIn(16, 24);
            assertThat(premiere.cadre().largeur()).isEqualTo(premiere.cadre().hauteur());
        }
    }

    @Test
    void laBonneZoneEstDeclareeEtSonLibelleEstDistinctDeCeluiDesAutres() {
        for (ZoneADesigner item : tousLesItems()) {
            List<String> libelles = item.zones().stream().map(Zone::libelle).toList();
            assertThat(new HashSet<>(libelles)).as("libelles distincts dans '%s'", item.illustration())
                    .hasSameSizeAs(libelles);
            assertThat(item.zones().stream().map(Zone::id)).as("la bonne zone est l'une des zones")
                    .contains(item.bonneZone());
        }
    }
}
