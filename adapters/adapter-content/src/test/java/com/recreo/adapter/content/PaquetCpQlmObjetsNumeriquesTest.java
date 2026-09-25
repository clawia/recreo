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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S43-QLM-NUMERIQUE, le paquet {@code cp-qlm-objets-numeriques}, ligne {@code QLM-CP-08} de
 * "Questionner le monde" (CP).</b> Referentiel :
 * {@code squad/programme/questionner-le-monde/competences-cp.md}. Arbitrage prealable cite par la
 * story (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
 * lignes.md, section 4.1) : "moyenne | Les regles d'usage au CP sont peu nombreuses ; risque de
 * moraliser plutot que d'enseigner."
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de l'enonce : reconnaitre un objet
 * numerique parmi des objets non numeriques du quotidien, ou l'inverse ("Lequel de ces objets est
 * un objet numerique ?" / "Lequel de ces objets n'est pas un objet numerique ?"), et une regle
 * simple d'usage motivee par une consequence factuelle ("Pourquoi une tablette a-t-elle besoin
 * d'etre rechargee ?" -&gt; "parce que sa batterie se vide en fonctionnant").</p>
 *
 * <p><b>Vigilance centrale de la ligne</b> : aucun enonce ni aucune bonne reponse de la tache de
 * regle d'usage ne s'adresse a l'enfant a la deuxieme personne, ni ne porte un jugement moral sur
 * lui - chaque bonne reponse enonce une consequence factuelle ("parce que...").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : qu'aucun item ne moralise reste une lecture de sens, garde
 * de {@code nadia-prof} (Done pedagogique).</p>
 */
class PaquetCpQlmObjetsNumeriquesTest {

    private static final String PAQUET = "cp-qlm-objets-numeriques";

    /** FER-2 : au moins dix items servent une ligne fermee (DEC-0064). */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

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

    /** Tache de reconnaissance : un enonce qui commence par "Lequel de ces objets". */
    private static boolean estDeTacheDeReconnaissance(Question item) {
        return item.enonce().startsWith("Lequel de ces objets");
    }

    private static List<Question> itemsDeReconnaissance() {
        return items().stream().filter(PaquetCpQlmObjetsNumeriquesTest::estDeTacheDeReconnaissance)
                .toList();
    }

    private static List<Question> itemsDeRegleDUsage() {
        return items().stream()
                .filter(item -> !estDeTacheDeReconnaissance(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze sur cp-qlm-objets-numeriques). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * Les deux moities du libelle (objets numeriques du quotidien, regles simples de leur usage)
     * sont chacune servies par plusieurs items distincts.
     */
    @Test
    void lesDeuxMoitiesDuLibelleSontChacuneServiesParPlusieursItems() {
        int reconnaissance = itemsDeReconnaissance().size();
        int regleDUsage = itemsDeRegleDUsage().size();

        assertThat(reconnaissance).as("la tache de reconnaissance est servie").isGreaterThan(0);
        assertThat(regleDUsage).as("la tache de regle d'usage est servie").isGreaterThan(0);
        assertThat(Math.min(reconnaissance, regleDUsage))
                .as("la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Vigilance de la ligne : aucun enonce ni aucune bonne reponse ne s'adresse a l'enfant a la
     * deuxieme personne du singulier.
     */
    @Test
    void aucunItemNeSAdresseALEnfantALaDeuxiemePersonne() {
        java.util.regex.Pattern adressePersonnelle =
                java.util.regex.Pattern.compile("\\btu\\b|\\btes\\b|\\bton\\b|\\bta\\b");
        for (Question item : items()) {
            assertThat(adressePersonnelle.matcher(item.enonce()).find())
                    .as("item %s : l'enonce ne s'adresse jamais a l'enfant a la deuxieme personne"
                            + " (\"%s\")", item.id().value(), item.enonce())
                    .isFalse();
            assertThat(adressePersonnelle.matcher(item.bonneReponse()).find())
                    .as("item %s : la bonne reponse ne s'adresse jamais a l'enfant a la deuxieme"
                            + " personne (\"%s\")", item.id().value(), item.bonneReponse())
                    .isFalse();
        }
    }

    /**
     * Chaque item de la tache de regle d'usage motive sa bonne reponse par une consequence
     * factuelle ("parce que..."), jamais par un ordre adresse a l'enfant.
     */
    @Test
    void chaqueItemDeRegleDUsagePorteUneConsequenceFactuelle() {
        for (Question item : itemsDeRegleDUsage()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse enonce une consequence factuelle",
                            item.id().value())
                    .startsWith("parce que");
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

    /** La bonne reponse d'un item ne figure jamais parmi ses propres distracteurs. */
    @Test
    void laBonneReponseNeFigureJamaisParmiSesPropresDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : la bonne reponse ne doit pas se retrouver dans les distracteurs",
                            item.id().value())
                    .doesNotContain(item.bonneReponse());
        }
    }

    /**
     * Chaque item declare uniquement QLM-CP-08, sa ligne de referentiel. Le paquet ne sert qu'une
     * seule ligne : sous ADR-0081 clause 6, elle est declaree une fois au niveau du paquet et
     * aucun item n'a besoin de porter sa propre cle {@code ligneDeReferentiel}.
     */
    @Test
    @SuppressWarnings("unchecked")
    void chaqueItemDeclareUniquementQlmCp08() {
        Map<String, Object> corps;
        try (InputStream flux = PaquetCpQlmObjetsNumeriquesTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            corps = (Map<String, Object>) new Yaml().load(
                    new String(flux.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        assertThat((List<Object>) corps.get("lignesDeReferentiel"))
                .as("le paquet ne declare que sa ligne")
                .containsExactly("QLM-CP-08");
        for (Map<String, Object> item : (List<Map<String, Object>>) corps.get("items")) {
            assertThat(item.get("ligneDeReferentiel"))
                    .as("un paquet a une seule ligne ne porte aucune surcharge par item"
                            + " (ADR-0081 clause 6)")
                    .isNull();
        }
    }
}
