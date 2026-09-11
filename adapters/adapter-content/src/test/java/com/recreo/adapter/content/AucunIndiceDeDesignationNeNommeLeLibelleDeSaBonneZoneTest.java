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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0091, clause 2 : l'indice d'un item de designation ne porte aucun jeton distinctif du libelle
 * de sa bonne zone - un jeton distinctif etant un jeton du libelle de la bonne zone qu'aucun autre
 * libelle du meme item ne porte, derive de l'item lui-meme, jamais d'une liste de mots-outils ecrite
 * a la main.
 *
 * <p><b>Ce que cette clause interdit</b> : sur {@code cp-frise-de-la-journee}, item 1, l'indice
 * "C'est le tout premier moment de la journee." donnait le libelle de sa bonne zone ("le premier")
 * mot pour mot - {@code nadia-prof}, {@code FRI-R1}, refus bloquant de {@code QLM-CP-14} a s34.</p>
 *
 * <p><b>Ce que cette clause ne garde pas</b> ({@code ADR-0091}) : un item dont toutes les zones
 * portent le meme libelle n'a aucun jeton distinctif, et cette clause est alors muette sur lui - un
 * tel item est injouable pour une autre raison (deux boutons indistincts), qu'aucun garde-fou du
 * depot ne couvre a ce jour.</p>
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les items viennent de la chaine reelle
 * ({@link YamlContentPackLoader}) ; derivation totale - chaque item a indice est examine, un item
 * sans indice n'a rien a fuir ; mise a l'epreuve - un paquet fautif ou l'indice nomme le libelle de sa
 * bonne zone, un paquet sain ou l'indice nomme le libelle d'une AUTRE zone (cas vert obligatoire,
 * sans quoi cette clause interdirait les indices relationnels), et la mutation de l'indice reel de
 * {@code cp-frise-de-la-journee}, item 1, dans son etat fautif du 2026-09-11.</p>
 */
class AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest {

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

    static List<String> verifierQuAucunIndiceNeNommeLeLibelleDeSaBonneZone(String paquetId,
                                                                            List<ZoneADesigner> items) {
        List<String> defauts = new ArrayList<>();
        for (ZoneADesigner item : items) {
            if (item.indice().isEmpty()) {
                continue;
            }
            Zone bonneZone = item.zones().stream()
                    .filter(zone -> zone.id().equals(item.bonneZone()))
                    .findFirst()
                    .orElseThrow();
            Set<String> jetonsDesAutresLibelles = item.zones().stream()
                    .filter(zone -> !zone.id().equals(item.bonneZone()))
                    .flatMap(zone -> AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                            .jetons(zone.libelle()).stream())
                    .collect(Collectors.toSet());
            List<String> jetonsDeLIndice = AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                    .jetons(item.indice().orElseThrow());
            for (String jeton : AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                    .jetons(bonneZone.libelle())) {
                if (!jetonsDesAutresLibelles.contains(jeton) && jetonsDeLIndice.contains(jeton)) {
                    defauts.add("paquet " + paquetId + ", item " + item.id().value()
                            + " : l'indice porte \"" + jeton + "\", jeton distinctif du libelle de sa"
                            + " bonne zone (\"" + bonneZone.libelle() + "\"). L'indice designe alors le"
                            + " bouton a presser au lieu d'aider a lire le dessin (ADR-0091 clause 2,"
                            + " FRI-R1)");
                }
            }
        }
        return defauts;
    }

    private static void verifier(List<String> defauts) {
        assertThat(defauts)
                .as("ADR-0091, clause 2 : l'indice d'un item de designation ne porte aucun jeton"
                        + " distinctif du libelle de sa bonne zone")
                .isEmpty();
    }

    private static List<ZoneADesigner> itemsDuPaquetFixture(String sousRepertoire, String paquetId) {
        String racineClasspath = "/contreexemple/AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest/"
                + sousRepertoire;
        ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId);
        return itemsDeDesignation(contenu);
    }

    @Test
    void unIndiceQuiNommeLeLibelleDeSaBonneZoneFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("indice-nomme-la-bonne-zone",
                "faux-paquet-indice");
        assertThat(items).isNotEmpty();

        assertThatThrownBy(() -> verifier(
                verifierQuAucunIndiceNeNommeLeLibelleDeSaBonneZone("faux-paquet-indice", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("premier")
                .hasMessageContaining("ADR-0091 clause 2");
    }

    /**
     * Cas vert obligatoire ({@code ADR-0091}) : un indice qui porte le jeton distinctif d'une AUTRE
     * zone que la bonne reste vert - c'est un indice relationnel ("ce n'est pas le deuxieme"), pas
     * une fuite de la bonne reponse.
     */
    @Test
    void unIndiceQuiNommeLeLibelleDUneAutreZoneResteVert() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("indice-nomme-une-autre-zone",
                "vrai-paquet-indice");
        assertThat(items).isNotEmpty();

        verifier(verifierQuAucunIndiceNeNommeLeLibelleDeSaBonneZone("vrai-paquet-indice", items));
    }

    /**
     * <b>S35-QLM14-FRISE : le balayage de production.</b> Mesure sur le seul paquet de designation
     * servi, {@code cp-frise-de-la-journee}, corrige dans ce meme lot ({@code FRI-R1}) : l'indice de
     * l'item 1 donnait le libelle de sa bonne zone mot pour mot avant correction.
     */
    @Test
    void surLeContenuReelDeProductionAucunIndiceNeNommeLeLibelleDeSaBonneZone() {
        List<String> defauts = new ArrayList<>();
        int paquetsDeDesignationExamines = 0;
        for (GameDefinition jeu : new YamlContentPackLoader().games()) {
            List<ZoneADesigner> items = itemsDeDesignation(jeu.contenu());
            if (items.isEmpty()) {
                continue;
            }
            paquetsDeDesignationExamines++;
            defauts.addAll(verifierQuAucunIndiceNeNommeLeLibelleDeSaBonneZone(jeu.id(), items));
        }

        assertThat(paquetsDeDesignationExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun paquet de designation reel")
                .isPositive();
        assertThat(defauts).isEmpty();
    }

    /**
     * <b>ADR-0013, obligation 3, mise a l'epreuve sur du materiau reel</b> : l'indice de l'item 1 de
     * {@code cp-frise-de-la-journee}, remis dans son etat fautif du 2026-09-11 ("C'est le tout
     * premier moment de la journee.", quand le libelle de sa bonne zone est "le premier"), fait
     * rougir le garde-fou. Cette morsure porte sur l'item reel du chargeur, pas sur une fixture de
     * ma main - la lecon de s05, appliquee a cette regle.
     */
    @Test
    void laMutationDeLIndiceDeLItemUnDansSonEtatFautifDu20260911FaitRougirLeGardeFou() {
        ZoneADesigner itemUnReel = itemsDeDesignation(
                new YamlContentPackLoader("").chargerPaquet("cp-frise-de-la-journee")).stream()
                .filter(item -> item.id().value().equals("item-0"))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "ADR-0013 : cette morsure mute item-0 de cp-frise-de-la-journee, introuvable"));
        assertThat(itemUnReel.bonneZone()).isEqualTo("lever");

        ZoneADesigner itemUnMuteDansSonEtatFautif = new ZoneADesigner(itemUnReel.id(),
                itemUnReel.enonce(), itemUnReel.illustration(), itemUnReel.illustrationTexteAlternatif(),
                itemUnReel.zones(), itemUnReel.bonneZone(),
                Optional.of("C'est le tout premier moment de la journée."), itemUnReel.explication(),
                itemUnReel.texteAVoixHaute());

        assertThat(verifierQuAucunIndiceNeNommeLeLibelleDeSaBonneZone("cp-frise-de-la-journee",
                List.of(itemUnMuteDansSonEtatFautif)))
                .as("l'indice fautif du 2026-09-11 doit faire rougir le garde-fou")
                .isNotEmpty();
    }
}
