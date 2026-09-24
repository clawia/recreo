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
 * <b>S41-EMC-DROITS, le paquet {@code cp-emc-droits-enfant}, ligne {@code EMC-CP-10} de
 * l'enseignement moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-
 * moral-et-civique/competences-cp.md}. Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md,
 * ligne 236) : ligne la moins risquee des cinq d'EMC CP a ce tarif, aucun item n'interroge
 * l'enfant sur sa propre situation (BIA-4, EMC-1).
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : reconnaitre le
 * droit dont parle une situation decrite ("Le médecin examine Aya quand elle est malade. Quel
 * droit est illustré ?" -&gt; "être soigné"), et dire ce que le droit garantit, sur un enonce qui
 * commence toujours par "Que garantit" ("Que garantit le droit de jouer ?" -&gt; "qu'il a du
 * temps pour s'amuser").</p>
 *
 * <p><b>FER-1</b> : les quatre droits du libelle (être protégé, être soigné, aller à l'école,
 * jouer) sont chacun en bonne reponse de la tache de reconnaissance.</p>
 *
 * <p><b>BIA-4, EMC-1</b> : aucun item ne s'adresse directement a l'enfant sur sa propre
 * situation - non gardable par une regle generique (theo-archi, avis de faisabilite section 6.2),
 * verifie ici par l'absence de toute forme d'adresse directe ("tu", "toi", "ta", "ton", "tes").</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> Que chaque distracteur soit un droit ou une notion reelle
 * du registre des droits de l'enfant, et jamais un element sans rapport, reste une lecture de
 * sens - garde de {@code nadia-prof}.</p>
 */
class PaquetCpEmcDroitsEnfantTest {

    private static final String PAQUET = "cp-emc-droits-enfant";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : bonne reponse des items de la tache de reconnaissance, les quatre droits. */
    private static final Set<String> LES_QUATRE_DROITS =
            Set.of("être protégé", "être soigné", "aller à l'école", "jouer");

    /** BIA-4, EMC-1 : aucune forme d'adresse directe a l'enfant. */
    private static final List<String> FORMES_D_ADRESSE_DIRECTE =
            List.of(" tu ", " toi", " ta ", " ton ", " tes ", "as-tu");

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

    /** Tache "que garantit" : un enonce qui commence par "Que garantit". */
    private static boolean estDeTacheDeGarantie(Question item) {
        return item.enonce().startsWith("Que garantit");
    }

    private static List<Question> itemsDeReconnaissance() {
        return items().stream().filter(item -> !estDeTacheDeGarantie(item)).toList();
    }

    private static List<Question> itemsDeGarantie() {
        return items().stream().filter(PaquetCpEmcDroitsEnfantTest::estDeTacheDeGarantie).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (douze depuis S41-EMC-DROITS). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les quatre droits sont tous representes en bonne reponse de la tache de
     * reconnaissance, et rien d'autre.
     */
    @Test
    void fer1LesQuatreDroitsSontEnBonneReponseDeLaTacheDeReconnaissance() {
        Set<String> reponses = itemsDeReconnaissance().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les quatre droits doivent tous etre representes")
                .containsExactlyInAnyOrderElementsOf(LES_QUATRE_DROITS);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int reconnaissance = itemsDeReconnaissance().size();
        int garantie = itemsDeGarantie().size();

        assertThat(reconnaissance).as("la tache de reconnaissance est servie").isGreaterThan(0);
        assertThat(garantie).as("la tache de garantie est servie").isGreaterThan(0);
        assertThat(Math.min(reconnaissance, garantie))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * BIA-4, EMC-1 : aucun item ne s'adresse directement a l'enfant sur sa propre situation.
     * Verifie ici sur la forme la plus simple a mordre sans lire le sens - aucune forme d'adresse
     * directe n'apparait dans un enonce.
     */
    @Test
    void bia4Emc1AucuneAdresseDirecteALEnfant() {
        for (Question item : items()) {
            String enonce = " " + item.enonce().toLowerCase() + " ";
            for (String forme : FORMES_D_ADRESSE_DIRECTE) {
                assertThat(enonce)
                        .as("item %s : l'enonce ne doit jamais s'adresser directement a l'enfant"
                                + " (BIA-4, EMC-1)", item.id().value())
                        .doesNotContain(forme);
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
     * Chaque item declare uniquement EMC-CP-10, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et
     * aucun item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementEmcCp10() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpEmcDroitsEnfantTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("EMC-CP-10");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
