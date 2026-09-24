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
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S42-ART-COULEURS, le paquet {@code cp-arts-couleurs}, ligne {@code ART-CP-05} d'arts
 * plastiques (CP).</b> Referentiel : {@code squad/programme/arts-plastiques/competences-cp.md},
 * ligne 5, jugement de faisabilite du 2026-09-03. Arbitrage prealable deja rendu : "jouable au
 * choix textuel, sous ART-1".
 *
 * <p><b>ART-1</b> : aucune reponse ne se porte par une couleur percue. Ce test ne verifie pas
 * l'absence de mot de couleur - le paquet en est fait, a la difference des lots ou ART-1 interdit
 * tout mot de couleur - mais l'absence de toute pastille ou illustration (une composante optionnelle
 * absente sur tous les items) et l'absence du patron "de quelle couleur est" dans un enonce.</p>
 *
 * <p><b>FER-1</b> : les trois couleurs primaires (bleu, jaune, rouge) et trois melanges reels
 * (vert, orange, violet) sont chacun en bonne reponse au moins une fois.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : que chaque fait de melange soit exact - relecture de
 * {@code nadia-prof} au Done pedagogique.</p>
 */
class PaquetCpArtsCouleursTest {

    private static final String PAQUET = "cp-arts-couleurs";

    /** ADR-0023 / DEC-0064 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** FER-1 : les trois couleurs primaires, chacune attendue en bonne reponse. */
    private static final Set<String> COULEURS_PRIMAIRES = Set.of("bleu", "jaune", "rouge");

    /** FER-1 : trois melanges reels du programme, chacun attendu en bonne reponse. */
    private static final Set<String> MELANGES_REELS = Set.of("vert", "orange", "violet");

    /** ART-1 : le patron d'enonce que la condition interdit explicitement. */
    private static final String PATRON_INTERDIT = "de quelle couleur est";

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

    /** ADR-0023 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("ADR-0023 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** FER-1 : les trois couleurs primaires sont chacune en bonne reponse au moins une fois. */
    @Test
    void fer1LesTroisCouleursPrimairesSontEnBonneReponse() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois couleurs primaires doivent toutes etre representees")
                .containsAll(COULEURS_PRIMAIRES);
    }

    /** FER-1 : les trois melanges reels du programme sont chacun en bonne reponse au moins une fois. */
    @Test
    void fer1LesTroisMelangesReelsSontEnBonneReponse() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois melanges reels doivent tous etre representes")
                .containsAll(MELANGES_REELS);
    }

    /**
     * ART-1 : aucun item ne montre de couleur percue. Verifie ici sur la forme la plus simple a
     * mordre sans lire le sens - aucun item ne declare d'illustration, et aucun enonce ne porte le
     * patron "de quelle couleur est" que la condition interdit explicitement.
     */
    @Test
    void art1AucunItemNeMontreUneCouleurPercue() {
        for (Question item : items()) {
            assertThat(item.illustration())
                    .as("item %s : ART-1, aucune image ne porte ce paquet (palier 1 texte seul)",
                            item.id().value())
                    .isEmpty();
            assertThat(item.enonce().toLowerCase(Locale.FRENCH))
                    .as("item %s : ART-1, l'enonce ne demande jamais 'de quelle couleur est...'",
                            item.id().value())
                    .doesNotContain(PATRON_INTERDIT);
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
     * Chaque item declare uniquement ART-CP-05, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et
     * aucun item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementArtCp05() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpArtsCouleursTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("ART-CP-05");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }

    /** Chaque item porte un indice. */
    @Test
    void chaqueItemPorteUnIndice() {
        for (Question item : items()) {
            assertThat(item.indice())
                    .as("item %s : indice present et non vide", item.id().value())
                    .isPresent();
            assertThat(item.indice().orElseThrow()).isNotBlank();
        }
    }
}
