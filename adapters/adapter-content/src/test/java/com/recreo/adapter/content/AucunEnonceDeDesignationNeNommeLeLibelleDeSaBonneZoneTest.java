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
 * ADR-0080, garde-fou 5 : l'enonce d'un item de designation ne nomme pas le libelle de sa bonne
 * zone, avec l'operateur d'ADR-0076 (decoupage en mots, jamais sous-chaine) et son tokeniseur
 * reutilise tel quel - {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest#jetons} et
 * {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest#contientCommeJetonsContigus}, pas un
 * second tokeniseur ecrit a cote.
 *
 * <p><b>Ce que cette clause interdit</b> : la contrefacon par enonce - "Montre le tableau." avec une
 * zone nommee "le tableau" se repond par le nom accessible seul, image effacee, ce que l'arbitrage
 * de {@code nadia-prof} du 2026-09-03 nomme {@code CON-1} en echec.</p>
 *
 * <p><b>Ce que cette clause ne voit pas, dit pour ne pas la faire passer pour plus qu'elle
 * n'est</b> ({@code nadia-prof}, meme arbitrage, section 2 et 6, {@code ZON-2}) : un libelle qui
 * porte la relation que la question demande <b>dans d'autres mots que ceux de l'enonce</b> - "la
 * case a droite de la case bleue" sur une question de deplacement - fuit tout autant sans qu'aucun
 * recouvrement lexical n'existe entre l'enonce et le libelle. Cette moitie-la reste entierement a la
 * relecture de {@code nadia-prof}, item par item ; ce garde-fou n'y pretend pas.</p>
 *
 * <p><b>Cablee sur le contenu de production depuis S33-PAL2-1 (s34)</b>, meme motif
 * qu'{@link LaBonneZoneNEstPasPredictibleSurUnPaquetTest} : le premier paquet de designation servi
 * ({@code cp-frise-de-la-journee}) donne enfin une population reelle a la clause ci-dessous.</p>
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les items viennent de la chaine reelle
 * ({@link YamlContentPackLoader}) ; derivation totale - chaque item de designation d'un paquet est
 * examine ; mise a l'epreuve - un paquet fautif ou l'enonce nomme le libelle de sa bonne zone, et un
 * paquet sain a enonce relationnel qui doit rester vert.</p>
 */
class AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest {

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

    static List<String> verifierQuAucunEnonceNeNommeLeLibelleDeSaBonneZone(String paquetId,
                                                                            List<ZoneADesigner> items) {
        List<String> defauts = new ArrayList<>();
        for (ZoneADesigner item : items) {
            Zone bonneZone = item.zones().stream()
                    .filter(zone -> zone.id().equals(item.bonneZone()))
                    .findFirst()
                    .orElseThrow();
            List<String> jetonsDuLibelle =
                    AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest.jetons(bonneZone.libelle());
            List<String> jetonsDeLEnonce =
                    AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest.jetons(item.enonce());
            if (AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                    .contientCommeJetonsContigus(jetonsDuLibelle, jetonsDeLEnonce)) {
                defauts.add("paquet " + paquetId + ", item " + item.id().value() + " : l'enonce \""
                        + item.enonce() + "\" nomme le libelle de sa bonne zone (\""
                        + bonneZone.libelle() + "\") comme suite contigue de mots - la reponse se"
                        + " trouve alors image effacee (ADR-0080, CON-1 en echec)");
            }
        }
        return defauts;
    }

    private static void verifier(List<String> defauts) {
        assertThat(defauts)
                .as("ADR-0080, garde-fou 5 : l'enonce d'un item de designation ne nomme pas le"
                        + " libelle de sa bonne zone")
                .isEmpty();
    }

    private static List<ZoneADesigner> itemsDuPaquetFixture(String sousRepertoire, String paquetId) {
        String racineClasspath = "/contreexemple/AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest/"
                + sousRepertoire;
        ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId);
        return itemsDeDesignation(contenu);
    }

    @Test
    void unEnonceQuiNommeLeLibelleDeSaBonneZoneFaitEchouerLeGardeFou() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("enonce-nomme-le-libelle",
                "faux-paquet-designation");
        assertThat(items).isNotEmpty();

        assertThatThrownBy(() -> verifier(
                verifierQuAucunEnonceNeNommeLeLibelleDeSaBonneZone("faux-paquet-designation", items)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le tableau")
                .hasMessageContaining("CON-1");
    }

    /**
     * L'enonce relationnel reste vert : c'est exactement le cas que {@code ZON-2} nomme comme non vu
     * par ce garde-fou - la relation ("a cote de la porte") n'est dans aucun mot du libelle de la
     * bonne zone ("la fenetre") - et il doit rester vert, sans quoi ce garde-fou refuserait toute
     * question relationnelle.
     */
    @Test
    void unEnonceRelationnelResteVert() {
        List<ZoneADesigner> items = itemsDuPaquetFixture("enonce-relationnel", "vrai-paquet-designation");
        assertThat(items).isNotEmpty();

        verifier(verifierQuAucunEnonceNeNommeLeLibelleDeSaBonneZone("vrai-paquet-designation", items));
    }

    /**
     * <b>S33-PAL2-1 : le balayage de production, du depuis ADR-0080 et jamais ecrit avant ce lot.</b>
     * Meme motif et meme forme que {@link LaBonneZoneNEstPasPredictibleSurUnPaquetTest#surLeContenuReelDeProductionLaBonneZoneNEstPasPredictible}.
     */
    @Test
    void surLeContenuReelDeProductionAucunEnonceNeNommeLeLibelleDeSaBonneZone() {
        List<String> defauts = new ArrayList<>();
        int paquetsDeDesignationExamines = 0;
        for (GameDefinition jeu : new YamlContentPackLoader().games()) {
            List<ZoneADesigner> items = itemsDeDesignation(jeu.contenu());
            if (items.isEmpty()) {
                continue;
            }
            paquetsDeDesignationExamines++;
            defauts.addAll(verifierQuAucunEnonceNeNommeLeLibelleDeSaBonneZone(jeu.id(), items));
        }

        assertThat(paquetsDeDesignationExamines)
                .as("ADR-0051 : ce garde-fou n'a examine aucun paquet de designation reel")
                .isPositive();
        assertThat(defauts).isEmpty();
    }
}
