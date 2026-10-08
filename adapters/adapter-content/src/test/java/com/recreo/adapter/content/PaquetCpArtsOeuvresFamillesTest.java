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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Random;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S54-BR-ART-CP-10, le paquet {@code cp-arts-oeuvres-familles}, paquet DEGRADE.</b> Referentiel :
 * {@code squad/programme/arts-plastiques/competences-cp.md}, ligne ART-CP-10, qui reste non
 * couverte : ce paquet ne sert que le vocabulaire des familles d'oeuvres, ni la reconnaissance ni le
 * rapprochement d'oeuvres (en-tete du paquet, DEC-0114 A.1).
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude des faits du paquet, et que le titre et la
 * consigne soient justes pour un enfant de CP - jugement de {@code nadia-prof}.</p>
 */
class PaquetCpArtsOeuvresFamillesTest {

    private static final String PAQUET = "cp-arts-oeuvres-familles";

    private static final Path CATALOGUE_SERVI = Path.of("src", "main", "resources", "catalogue.yaml");

    /** ADR-0023 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    private static final Pattern PROMESSE_INTERDITE =
            Pattern.compile("reconna|rapproch", Pattern.CASE_INSENSITIVE);

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

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> entreesDuPaquet() {
        try (InputStream flux = Files.newInputStream(CATALOGUE_SERVI)) {
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            List<Map<String, Object>> jeux = (List<Map<String, Object>>) corps.get("jeux");
            return jeux.stream().filter(jeu -> PAQUET.equals(jeu.get("paquet"))).toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** ADR-0023 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("ADR-0023 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** Le paquet est branche au catalogue par exactement une entree. */
    @Test
    void lePaquetEstBrancheUneSeuleEntreeDuCatalogue() {
        assertThat(entreesDuPaquet()).hasSize(1);
    }

    /**
     * L'en-tete du paquet dit qu'il ne sert ni la reconnaissance ni le rapprochement d'oeuvres : ni
     * l'identifiant, ni le titre, ni la consigne de son entree ne le promettent.
     */
    @Test
    void lEntreeNePrometNiReconnaissanceNiRapprochement() {
        Map<String, Object> entree = entreesDuPaquet().get(0);
        for (String champ : List.of("id", "titre", "consigne")) {
            assertThat(String.valueOf(entree.get(champ)))
                    .as("entree du catalogue, champ %s", champ)
                    .doesNotContainPattern(PROMESSE_INTERDITE);
        }
    }

    /** ART-1 : aucune reponse ni aucun distracteur ne nomme une couleur. */
    @Test
    void art1AucuneReponseNiDistracteurNeNommeUneCouleur() {
        Set<String> couleurs = Set.of("rouge", "bleu", "bleue", "jaune", "vert", "verte", "noir",
                "noire", "blanc", "blanche", "orange", "violet", "violette", "rose", "marron",
                "gris", "grise");
        for (Question item : items()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                for (String couleur : couleurs) {
                    assertThat(proposition.toLowerCase(Locale.FRENCH))
                            .as("item %s : ART-1, aucune proposition ne nomme une couleur",
                                    item.id().value())
                            .doesNotContain(couleur);
                }
            }
        }
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
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
