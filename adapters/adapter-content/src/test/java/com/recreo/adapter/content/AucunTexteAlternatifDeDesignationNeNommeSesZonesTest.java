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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0091, clause 1 : le texte alternatif d'un item de designation ne nomme aucune de ses zones -
 * l'ancre est l'identifiant de zone, pas son libelle (les libelles sont positionnels, ZON-10), avec
 * l'operateur d'ADR-0076 (decoupage en mots, jamais sous-chaine) et son tokeniseur reutilise tel
 * quel - {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest#jetons} et
 * {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest#contientCommeJetonsContigus}, pas un
 * second tokeniseur ecrit a cote.
 *
 * <p><b>Ce que cette clause interdit</b> : la contrefacon par texte alternatif - un texte alternatif
 * qui enumere ses zones rend l'item jouable image effacee, exactement le defaut que
 * {@code nadia-prof} a releve sur {@code cp-frise-de-la-journee} ({@code FRI-R1}, refus bloquant de
 * {@code QLM-CP-14} a s34) : le texte alternatif listait les six moments dans l'ordre de la frise, et
 * les libelles de zone etant des rangs, la composition des deux textes formait une table de
 * correspondance complete.</p>
 *
 * <p><b>Ce que cette clause ne voit pas</b> ({@code ADR-0091}, "Ce que ces deux clauses ne gardent
 * pas") : un identifiant de zone opaque ({@code z1}...{@code z6}) passerait cette clause tout en
 * rendant le paquet illisible - elle ferme la porte qu'on a vu franchir, pas celle-la.</p>
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les items viennent de la chaine reelle
 * ({@link YamlContentPackLoader}) ; derivation totale - chaque zone de chaque item de designation est
 * examinee, aucune exemption ; mise a l'epreuve - un paquet fautif ou le texte alternatif enumere ses
 * zones, et un paquet sain ou le texte alternatif dit ce que l'image est, qui doit rester vert.</p>
 */
class AucunTexteAlternatifDeDesignationNeNommeSesZonesTest {

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

    static List<String> verifierQuAucunTexteAlternatifNeNommeSesZones(String paquetId,
                                                                       List<ZoneADesigner> items) {
        List<String> defauts = new ArrayList<>();
        for (ZoneADesigner item : items) {
            List<String> jetonsDuTexte = AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                    .jetons(item.illustrationTexteAlternatif());
            for (Zone zone : item.zones()) {
                List<String> jetonsDeLIdentifiant = AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                        .jetons(zone.id());
                if (AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                        .contientCommeJetonsContigus(jetonsDeLIdentifiant, jetonsDuTexte)) {
                    defauts.add("paquet " + paquetId + ", item " + item.id().value()
                            + " : le texte alternatif nomme la zone \"" + zone.id() + "\". Un texte"
                            + " alternatif dit ce que l'image EST, pas ce qu'elle contient : enumerer"
                            + " les zones rend l'item jouable image effacee (ADR-0091 clause 1,"
                            + " FRI-R1)");
                }
            }
        }
        return defauts;
    }

    private static void verifier(List<String> defauts) {
        assertThat(defauts)
                .as("ADR-0091, clause 1 : le texte alternatif d'un item de designation ne nomme"
                        + " aucune de ses zones")
                .isEmpty();
    }

    private static List<ZoneADesigner> itemsDuPaquetFixture(String sousRepertoire, String paquetId) {
        String racineClasspath = "/contreexemple/AucunTexteAlternatifDeDesignationNeNommeSesZonesTest/"
                + sousRepertoire;
        ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId);
        return itemsDeDesignation(contenu);
    }

    @Test
    void unTexteAlternatifQuiEnumereSesZonesFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("texte-alternatif-enumere",
                "faux-paquet-texte-alternatif");
        assertThat(items).isNotEmpty();

        assertThatThrownBy(() -> verifier(
                verifierQuAucunTexteAlternatifNeNommeSesZones("faux-paquet-texte-alternatif", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("tableau")
                .hasMessageContaining("ADR-0091 clause 1");
    }

    /**
     * Cas vert obligatoire ({@code ADR-0091}) : un texte alternatif qui dit ce que l'image est, sans
     * enumerer ses zones, doit rester vert - sans quoi cette clause refuserait tout texte alternatif.
     */
    @Test
    void unTexteAlternatifHonneteResteVert() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("texte-alternatif-honnete",
                "vrai-paquet-texte-alternatif");
        assertThat(items).isNotEmpty();

        verifier(verifierQuAucunTexteAlternatifNeNommeSesZones("vrai-paquet-texte-alternatif", items));
    }

    /**
     * <b>S35-QLM14-FRISE : le balayage de production.</b> Mesure sur le seul paquet de designation
     * servi, {@code cp-frise-de-la-journee}, corrige dans ce meme lot ({@code FRI-R1}) : les deux
     * textes alternatifs du paquet enumeraient chacun trois ou quatre de leurs zones avant
     * correction.
     */
    @Test
    void surLeContenuReelDeProductionAucunTexteAlternatifNeNommeSesZones() {
        List<String> defauts = new ArrayList<>();
        int paquetsDeDesignationExamines = 0;
        for (GameDefinition jeu : new YamlContentPackLoader().games()) {
            List<ZoneADesigner> items = itemsDeDesignation(jeu.contenu());
            if (items.isEmpty()) {
                continue;
            }
            paquetsDeDesignationExamines++;
            defauts.addAll(verifierQuAucunTexteAlternatifNeNommeSesZones(jeu.id(), items));
        }

        assertThat(paquetsDeDesignationExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun paquet de designation reel")
                .isPositive();
        assertThat(defauts).isEmpty();
    }
}
