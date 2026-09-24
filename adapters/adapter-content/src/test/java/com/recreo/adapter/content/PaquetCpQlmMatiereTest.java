package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S42-QLM-MATIERE, le paquet {@code cp-qlm-matiere}, ligne {@code QLM-CP-06} de "questionner le
 * monde" (CP).</b> Referentiel : {@code squad/programme/questionner-le-monde/competences-cp.md}.
 * Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md, section 7.1, ligne 6) : vigilance
 * sur le nom de l'objet, qui donne souvent la matiere ("une cuillere en bois").
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : identifier la
 * matiere d'un objet decrit sans que son nom ne la revele ("Un seau de plage est léger et coloré.
 * En quelle matière est-il souvent fait ?" -&gt; "plastique"), et trier des materiaux selon une
 * propriete simple, sur un enonce qui commence toujours par "Parmi ces trois matières" ("Parmi ces
 * trois matières, laquelle flotte sur l'eau ?" -&gt; "le bois").</p>
 *
 * <p><b>FER-1</b> : bois, metal, plastique et verre - les quatre materiaux du critere d'acceptation
 * - sont chacun en bonne reponse au moins une fois. Le tissu s'y ajoute.</p>
 *
 * <p><b>Vigilance nadia-prof</b> : aucun enonce ne nomme, dans son propre texte, la matiere qu'il
 * interroge - verifie ici par l'absence de tout nom de materiau dans le texte des cinq enonces de
 * la tache d'identification.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> Que chaque distracteur soit un materiau reellement
 * plausible pour l'objet decrit, et jamais un element sans rapport, reste une lecture de sens -
 * garde de {@code nadia-prof}.</p>
 */
class PaquetCpQlmMatiereTest {

    private static final String PAQUET = "cp-qlm-matiere";

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : bois, metal, plastique et verre, les quatre materiaux du critere d'acceptation. */
    private static final Set<String> MATERIAUX_DU_CRITERE = Set.of("bois", "métal", "plastique",
            "verre");

    /** Les materiaux que le paquet peut nommer, sous leurs deux formes ("bois" et "le bois"). */
    private static final List<String> NOMS_DE_MATERIAUX =
            List.of("bois", "métal", "plastique", "verre", "tissu");

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    /** Tache de tri : un enonce qui commence par "Parmi ces trois matières". */
    private static boolean estDeTacheDeTri(Question item) {
        return item.enonce().startsWith("Parmi ces trois matières");
    }

    private static List<Question> itemsDIdentification() {
        return items().stream().filter(item -> !estDeTacheDeTri(item)).toList();
    }

    private static List<Question> itemsDeTri() {
        return items().stream().filter(PaquetCpQlmMatiereTest::estDeTacheDeTri).toList();
    }

    /** FER-1 : les quatre materiaux du critere d'acceptation sont tous en bonne reponse. */
    @Test
    void fer1LesQuatreMateriauxDuCritereSontEnBonneReponse() {
        Set<String> reponses = items().stream()
                .map(item -> item.bonneReponse().replace("le ", ""))
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : bois, métal, plastique et verre doivent tous être représentés")
                .containsAll(MATERIAUX_DU_CRITERE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int identification = itemsDIdentification().size();
        int tri = itemsDeTri().size();

        assertThat(identification).as("la tâche d'identification est servie").isGreaterThan(0);
        assertThat(tri).as("la tâche de tri est servie").isGreaterThan(0);
        assertThat(Math.min(identification, tri))
                .as("FER-3 : la tâche la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Vigilance nadia-prof (ARBITRAGE-PREALABLE-s27, section 7.1, ligne 6) : l'enonce de la tache
     * d'identification ne nomme aucun materiau, sous aucune forme - le nom de l'objet ne doit pas
     * révéler la matière attendue avant réponse.
     */
    @Test
    void aucunEnonceDeLaTacheDIdentificationNeRevelePasLaMatiere() {
        for (Question item : itemsDIdentification()) {
            for (String materiau : NOMS_DE_MATERIAUX) {
                assertThat(item.enonce().toLowerCase())
                        .as("item %s : l'énoncé ne doit pas révéler la matière attendue",
                                item.id().value())
                        .doesNotContain(materiau);
            }
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Chaque item declare uniquement QLM-CP-06, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et
     * aucun item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementQlmCp06() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpQlmMatiereTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("QLM-CP-06");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
