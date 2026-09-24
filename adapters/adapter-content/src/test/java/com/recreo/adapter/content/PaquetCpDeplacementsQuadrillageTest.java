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
 * <b>S42-MAT-TEXTUEL, le paquet {@code cp-deplacements-quadrillage}, ligne {@code MAT-CP-10} de
 * mathematiques (CP), moitie textuelle seulement.</b> Referentiel :
 * {@code squad/programme/mathematiques/competences-cp.md}, ligne 31. Arbitrage prealable deja
 * rendu, cite en entier par la story (`ARBITRAGE-PREALABLE-s32...`, section 17.7) : decrire un
 * deplacement n'est pas une designation et se sert en choix textuel ; la moitie "se reperer et se
 * deplacer" (designation sur quadrillage, palier 2) reste hors de portee ici.
 *
 * <p><b>Le plafond de 20 items</b> (nadia-prof, addendum, section 12.2) et <b>les quatre formes de
 * deplacement du critere d'acceptation</b> - avancer, reculer, tourner a gauche, tourner a droite -
 * chacune en bonne reponse au moins une fois.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : que chaque scene decrite en mots soit plausible et
 * lisible par un enfant de CP reste une lecture de sens, garde de {@code nadia-prof}. Ce paquet
 * n'a aucune reserve de longueur : ses enonces sont mesures conformes a {@code LON-1}/{@code LON-2}
 * (ADR-0089) des leur premier commit.</p>
 */
class PaquetCpDeplacementsQuadrillageTest {

    private static final String PAQUET = "cp-deplacements-quadrillage";

    private static final int PLAFOND_ITEMS = 20;

    /** Les quatre formes de deplacement du critere d'acceptation de la story. */
    private static final Set<String> FORMES_DE_DEPLACEMENT =
            Set.of("avancer", "reculer", "tourner à gauche", "tourner à droite");

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

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * Critere d'acceptation : plusieurs formes de deplacement distinctes sont chacune en bonne
     * reponse au moins une fois.
     */
    @Test
    void lesQuatreFormesDeDeplacementSontChacuneEnBonneReponseAuMoinsUneFois() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("les quatre formes de deplacement du critere d'acceptation doivent toutes"
                        + " etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(FORMES_DE_DEPLACEMENT);
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
     * Critere d'acceptation : la bonne reponse figure exactement une fois parmi les propositions,
     * et les distracteurs sont d'autres deplacements plausibles sur un quadrillage, jamais des
     * elements sans rapport - verifie ici par appartenance a l'ensemble ferme des quatre formes.
     */
    @Test
    void touteLesPropositionsSontLUneDesQuatreFormesDeDeplacementEtLaBonneReponseNYFigureQuUneFois() {
        for (Question item : items()) {
            assertThat(FORMES_DE_DEPLACEMENT)
                    .as("item %s : la bonne reponse est une des quatre formes de deplacement",
                            item.id().value())
                    .contains(item.bonneReponse());
            for (String distracteur : item.distracteurs()) {
                assertThat(FORMES_DE_DEPLACEMENT)
                        .as("item %s : un distracteur est un autre deplacement plausible, jamais"
                                + " un element sans rapport", item.id().value())
                        .contains(distracteur);
            }
            assertThat(item.distracteurs())
                    .as("item %s : la bonne reponse n'est jamais aussi un de ses distracteurs",
                            item.id().value())
                    .doesNotContain(item.bonneReponse());
        }
    }

    /**
     * Critere d'acceptation et section 7.5 de l'avis de faisabilite : aucun item ne demande une
     * designation d'une case ou d'un point sur une image, palier 2 hors de portee ici. Ce paquet ne
     * porte aucune illustration.
     */
    @Test
    void aucunItemNePorteDIllustration() {
        for (Question item : items()) {
            assertThat(item.illustration())
                    .as("item %s : ce paquet est en choix textuel seul, aucune designation sur"
                            + " une image (palier 2, hors de portee ici)", item.id().value())
                    .isEmpty();
        }
    }

    /**
     * Chaque item declare uniquement MAT-CP-10, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne, et sa moitie textuelle seulement : sous ADR-0081 clause 6, elle est declaree une
     * fois au niveau du paquet et aucun item n'a besoin de porter sa propre cle
     * {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementMatCp10() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpDeplacementsQuadrillageTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("MAT-CP-10");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
