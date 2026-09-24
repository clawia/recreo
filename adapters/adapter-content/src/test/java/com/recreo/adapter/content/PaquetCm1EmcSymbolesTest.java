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
 * <b>S42-EMC-SYMBOLES-CM1, le paquet {@code cm1-emc-symboles}, ligne {@code EMC-CM1-05}
 * d'enseignement moral et civique (CM1).</b> Referentiel : {@code squad/programme/enseignement-
 * moral-et-civique/competences-cm1.md}, ligne 5, jugement de faisabilite du 2026-09-03.
 *
 * <p><b>Discriminant vis-a-vis du CP</b> (avis de faisabilite de theo-archi, section 8.1) : les
 * cinq symboles du libelle sont deja servis au CP par {@code cp-emc-symboles} (EMC-CP-08, s41) et
 * {@code cp-emc-devise} (EMC-CP-09, s40), sur les taches "nommer" et "que represente". Ce paquet ne
 * reprend aucune des deux : il pose l'origine historique de chaque symbole, son contexte
 * institutionnel d'usage, et sa nature par opposition aux quatre autres - trois taches absentes du
 * CP. Ce test ne verifie pas le fond de cette difference (lecture de sens, garde de
 * {@code nadia-prof}), mais verifie que les trois familles d'enonce existent bien et ne reprennent
 * pas le patron du CP ("Comment s'appelle...", "Que represente...").</p>
 *
 * <p><b>FER-1</b> : les cinq symboles du libelle (drapeau, devise, Marianne, hymne, fete
 * nationale) sont chacun en bonne reponse au moins une fois.</p>
 *
 * <p><b>ART-1</b> : aucune reponse ne se porte par une couleur percue - aucun mot de couleur dans
 * ce paquet.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude de chaque fait historique et institutionnel
 * (HIS-3) et qu'aucune reponse ne soit discutable (HIS-2) - relecture de {@code nadia-prof} au Done
 * pedagogique.</p>
 */
class PaquetCm1EmcSymbolesTest {

    private static final String PAQUET = "cm1-emc-symboles";

    /** DEC-0064 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** FER-1 : les cinq symboles du libelle, chacun attendu en bonne reponse (tache de nature). */
    private static final Set<String> LES_CINQ_SYMBOLES =
            Set.of("le drapeau", "la devise", "Marianne", "la Marseillaise", "le 14 juillet");

    /** ART-1 : aucun mot de couleur, sous aucune forme, dans tout le paquet. */
    private static final List<String> MOTS_DE_COULEUR =
            List.of("bleu", "blanc", "rouge", "tricolore", "jaune", "vert", "orange", "violet");

    /** Les deux patrons de tache que le CP epuise deja, absents de ce paquet de CM1. */
    private static final List<String> PATRONS_DEJA_SERVIS_AU_CP =
            List.of("comment s'appelle", "que représente");

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

    /** DEC-0064 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("DEC-0064 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** FER-1 : les cinq symboles sont tous representes en bonne reponse. */
    @Test
    void fer1LesCinqSymbolesSontEnBonneReponse() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les cinq symboles doivent tous etre representes")
                .containsAll(LES_CINQ_SYMBOLES);
    }

    /**
     * ART-1 : aucune reponse portee par une couleur percue. Verifie sur la forme la plus simple a
     * mordre sans lire le sens - aucun mot de couleur n'apparait nulle part dans le paquet.
     */
    @Test
    void art1AucunMotDeCouleurDansLePaquet() {
        for (Question item : items()) {
            for (String couleur : MOTS_DE_COULEUR) {
                assertThat(item.enonce().toLowerCase(Locale.FRENCH))
                        .as("item %s : l'enonce ne doit porter aucun mot de couleur (ART-1)",
                                item.id().value())
                        .doesNotContain(couleur);
                assertThat(item.bonneReponse().toLowerCase(Locale.FRENCH))
                        .as("item %s : la bonne reponse ne doit porter aucun mot de couleur"
                                + " (ART-1)", item.id().value())
                        .doesNotContain(couleur);
                for (String distracteur : item.distracteurs()) {
                    assertThat(distracteur.toLowerCase(Locale.FRENCH))
                            .as("item %s : un distracteur ne doit porter aucun mot de couleur"
                                    + " (ART-1)", item.id().value())
                            .doesNotContain(couleur);
                }
            }
        }
    }

    /**
     * Discriminant vis-a-vis du CP : aucun enonce de ce paquet ne reprend le patron de question
     * deja epuise par {@code cp-emc-symboles} ("Comment s'appelle...", "Que represente...").
     */
    @Test
    void aucunEnonceNeReprendLePatronDejaServiAuCp() {
        for (Question item : items()) {
            String enonce = item.enonce().toLowerCase(Locale.FRENCH);
            for (String patron : PATRONS_DEJA_SERVIS_AU_CP) {
                assertThat(enonce)
                        .as("item %s : ce patron de question est deja epuise au CP par"
                                + " cp-emc-symboles, ce paquet de CM1 doit poser une autre tache",
                                item.id().value())
                        .doesNotContain(patron);
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
     * Chaque item declare uniquement EMC-CM1-05, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et
     * aucun item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementEmcCm105() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCm1EmcSymbolesTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("EMC-CM1-05");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
