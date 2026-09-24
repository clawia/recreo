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
 * <b>S41-EMC-SYMBOLES, le paquet {@code cp-emc-symboles}, ligne {@code EMC-CP-08} de
 * l'enseignement moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-
 * moral-et-civique/competences-cp.md}. Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md,
 * ligne 234) : connaissance exacte des quatre symboles, aucune reponse portee par une couleur
 * percue (ART-1).
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : nommer le
 * symbole decrit a partir d'un indice ("Il flotte au-dessus des mairies et des écoles. Comment
 * s'appelle-t-il ?" -&gt; "le drapeau"), et dire ce qu'il represente, sur un enonce qui commence
 * toujours par "Que représente" ("Que représente Marianne ?" -&gt; "la République").</p>
 *
 * <p><b>FER-1</b> : les quatre symboles du libelle (le drapeau, Marianne, la Marseillaise, la
 * fête nationale) sont chacun en bonne reponse de la tache de reconnaissance.</p>
 *
 * <p><b>ART-1</b> : aucun item ne demande la couleur d'un element - non gardable par une regle
 * generique (theo-archi, avis de faisabilite section 6.1), verifie ici par l'absence de tout mot
 * de couleur dans le paquet.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> Que chaque distracteur soit un symbole ou une notion
 * reelle du registre republicain, et jamais un element sans rapport, reste une lecture de sens -
 * garde de {@code nadia-prof}.</p>
 */
class PaquetCpEmcSymbolesTest {

    private static final String PAQUET = "cp-emc-symboles";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : bonne reponse des items de la tache de reconnaissance, les quatre symboles. */
    private static final Set<String> LES_QUATRE_SYMBOLES =
            Set.of("le drapeau", "Marianne", "la Marseillaise", "la fête nationale");

    /** ART-1 : aucun mot de couleur, sous aucune forme, dans tout le paquet. */
    private static final List<String> MOTS_DE_COULEUR =
            List.of("bleu", "blanc", "rouge", "tricolore");

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

    /** Tache "que represente" : un enonce qui commence par "Que représente". */
    private static boolean estDeTacheDeSens(Question item) {
        return item.enonce().startsWith("Que représente");
    }

    private static List<Question> itemsDeReconnaissance() {
        return items().stream().filter(item -> !estDeTacheDeSens(item)).toList();
    }

    private static List<Question> itemsDeSens() {
        return items().stream().filter(PaquetCpEmcSymbolesTest::estDeTacheDeSens).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (douze depuis S41-EMC-SYMBOLES). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les quatre symboles sont tous representes en bonne reponse de la tache de
     * reconnaissance, et rien d'autre.
     */
    @Test
    void fer1LesQuatreSymbolesSontEnBonneReponseDeLaTacheDeReconnaissance() {
        Set<String> reponses = itemsDeReconnaissance().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les quatre symboles doivent tous etre representes")
                .containsExactlyInAnyOrderElementsOf(LES_QUATRE_SYMBOLES);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int reconnaissance = itemsDeReconnaissance().size();
        int sens = itemsDeSens().size();

        assertThat(reconnaissance).as("la tache de reconnaissance est servie").isGreaterThan(0);
        assertThat(sens).as("la tache de sens est servie").isGreaterThan(0);
        assertThat(Math.min(reconnaissance, sens))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * ART-1 : aucune reponse portee par une couleur percue. Verifie ici sur la forme la plus
     * simple a mordre sans lire le sens - aucun mot de couleur n'apparait nulle part dans le
     * paquet, ni dans un enonce, ni dans une proposition, ni dans un indice.
     */
    @Test
    void art1AucunMotDeCouleurDansLePaquet() {
        for (Question item : items()) {
            for (String couleur : MOTS_DE_COULEUR) {
                assertThat(item.enonce().toLowerCase())
                        .as("item %s : l'enonce ne doit porter aucun mot de couleur (ART-1)",
                                item.id().value())
                        .doesNotContain(couleur);
                assertThat(item.bonneReponse().toLowerCase())
                        .as("item %s : la bonne reponse ne doit porter aucun mot de couleur"
                                + " (ART-1)", item.id().value())
                        .doesNotContain(couleur);
                for (String distracteur : item.distracteurs()) {
                    assertThat(distracteur.toLowerCase())
                            .as("item %s : un distracteur ne doit porter aucun mot de couleur"
                                    + " (ART-1)", item.id().value())
                            .doesNotContain(couleur);
                }
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
     * Chaque item declare uniquement EMC-CP-08, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et
     * aucun item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementEmcCp08() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpEmcSymbolesTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("EMC-CP-08");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
