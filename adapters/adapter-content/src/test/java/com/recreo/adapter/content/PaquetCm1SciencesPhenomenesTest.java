package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S46-SCI-PHENOMENES-CM1, le paquet {@code cm1-sciences-phenomenes}, ligne {@code SCI-CM1-16}
 * de "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/programme/sciences-et-
 * technologie/competences-cm1.md}. Arbitrage prealable cite integralement par la story
 * (ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md,
 * section 2.2, ligne 16) ; conditions encodables citees par l'avis de faisabilite de theo-archi,
 * {@code squad/rapports/avis-faisabilite-s46-cinq-lots.md}, section 7.
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : tache A, <b>reconnaitre le phenomene decrit</b> (la
 * bonne reponse nomme un phenomene - items 1 a 6) ; tache B, <b>dire quel geste protege du risque
 * associe</b> (la bonne reponse decrit un geste ou son motif, jamais un nom de phenomene - items 7
 * a 12).
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque phenomene et de chaque
 * consigne de securite retenue reste la garde de {@code nadia-prof}, en jouant.
 */
class PaquetCm1SciencesPhenomenesTest {

    private static final String PAQUET = "cm1-sciences-phenomenes";

    /** FER-2 : au moins nombreDeManches + 1 items (nombreDeManches = 10, catalogue.yaml). */
    private static final int NOMBRE_DE_MANCHES = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** SCI-3 : au plus n/6 items dont la bonne reponse porte un chiffre. */
    private static final int DIVISEUR_SCI_3 = 6;

    /** Les trois elements exiges par FER-1 (geologique, meteorologique, risque). */
    private static final Set<String> ELEMENTS_DU_LIBELLE =
            Set.of("geologique", "meteorologique", "risque");

    /** FER-1 : marqueur par item, lu dans le fichier brut. */
    private static final Pattern MARQUEUR_ELEMENT =
            Pattern.compile("# element: (geologique|meteorologique|risque)");

    /** BIA-2 : les quatre valeurs admises du marqueur de lieu. */
    private static final Set<String> LIEUX_ADMIS = Set.of("france", "europe", "ailleurs", "non-situe");

    /** BIA-2 : marqueur par item, lu dans le fichier brut. */
    private static final Pattern MARQUEUR_LIEU =
            Pattern.compile("# lieu: (france|europe|ailleurs|non-situe)");

    /** Bonne reponse des items de la tache A, "reconnaitre le phenomene decrit". */
    private static final Set<String> REPONSES_DU_PHENOMENE = Set.of(
            "un séisme", "une éruption volcanique", "une tempête", "une inondation");

    /** Un chiffre, convention SCI-3 de ce lot. */
    private static final Pattern VALEUR_NUMERIQUE = Pattern.compile("\\d");

    /** Une phrase, sous un critere simplifie : un seul signe de fin ([.?!]), en fin de texte. */
    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!]");

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

    private static List<Question> itemsDuPhenomene() {
        return items().stream().filter(item -> REPONSES_DU_PHENOMENE.contains(item.bonneReponse()))
                .toList();
    }

    private static List<Question> itemsDuGeste() {
        return items().stream()
                .filter(item -> !REPONSES_DU_PHENOMENE.contains(item.bonneReponse())).toList();
    }

    /** Le texte brut du paquet, tel qu'ecrit sur le classpath - commentaires compris. */
    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCm1SciencesPhenomenesTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + PAQUET + ".yaml introuvable sur le"
                        + " classpath");
            }
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** FER-2 : au moins nombreDeManches + 1 items (douze a la livraison). */
    @Test
    void fer2AuMoinsNombreDeManchesPlusUnItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items (nombreDeManches + 1)", NOMBRE_DE_MANCHES + 1)
                .isGreaterThanOrEqualTo(NOMBRE_DE_MANCHES + 1);
    }

    /**
     * FER-1 : les trois elements du libelle (geologique, meteorologique, risque) sont chacun
     * marques par item et tous presents.
     */
    @Test
    void fer1LesTroisElementsDuLibelleSontTousServis() {
        Set<String> elementsServis = new HashSet<>();
        Matcher marqueurs = MARQUEUR_ELEMENT.matcher(texteBrutDuPaquet());
        while (marqueurs.find()) {
            elementsServis.add(marqueurs.group(1));
        }

        assertThat(ELEMENTS_DU_LIBELLE)
                .as("tout marqueur # element: designe l'un des trois elements du libelle")
                .containsAll(elementsServis);
        assertThat(elementsServis)
                .as("FER-1 : les trois elements du libelle (geologique, meteorologique, risque)"
                        + " sont tous servis")
                .isEqualTo(ELEMENTS_DU_LIBELLE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int phenomene = itemsDuPhenomene().size();
        int geste = itemsDuGeste().size();

        assertThat(phenomene).as("la tache 'reconnaitre le phenomene decrit' est servie")
                .isGreaterThan(0);
        assertThat(geste).as("la tache 'dire quel geste protege' est servie").isGreaterThan(0);
        assertThat(Math.min(phenomene, geste))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement douze items (les deux taches additionnees). */
    @Test
    void lePaquetPorteExactementDouzeItems() {
        assertThat(items()).hasSize(12);
        assertThat(itemsDuPhenomene().size() + itemsDuGeste().size())
                .as("chaque item du paquet appartient a l'une des deux taches, aucun aux deux")
                .isEqualTo(12);
    }

    /**
     * BIA-2 : marqueur de lieu par item, parmi les quatre valeurs admises ; parmi les items situes
     * (france, europe ou ailleurs - "non-situe" exclu de cette population), au moins un est en
     * France ou en Europe - le paquet ne situe jamais systematiquement les phenomenes decrits
     * ailleurs qu'en France ou en Europe.
     */
    @Test
    void bia2ParmiLesItemsSituesAuMoinsUnEstEnFranceOuEnEurope() {
        Matcher marqueurs = MARQUEUR_LIEU.matcher(texteBrutDuPaquet());
        List<String> lieux = new ArrayList<>();
        while (marqueurs.find()) {
            lieux.add(marqueurs.group(1));
        }

        assertThat(lieux).as("un marqueur # lieu: par item").hasSize(12);
        for (String lieu : lieux) {
            assertThat(LIEUX_ADMIS).as("tout marqueur # lieu: designe l'une des quatre valeurs admises")
                    .contains(lieu);
        }

        List<String> situes = lieux.stream().filter(lieu -> !lieu.equals("non-situe")).toList();
        assertThat(situes).as("BIA-2 : au moins un item est situe (france, europe ou ailleurs)")
                .isNotEmpty();
        assertThat(situes.stream().anyMatch(lieu -> lieu.equals("france") || lieu.equals("europe")))
                .as("BIA-2 : parmi les items situes, au moins un est en France ou en Europe - jamais"
                        + " systematiquement ailleurs")
                .isTrue();
    }

    /** SCI-4, simplification assumee : chaque item porte deux distracteurs. */
    @Test
    void sci4ChaqueItemPorteDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus (SCI-4)", item.enonce())
                    .hasSize(2);
        }
    }

    /**
     * SCI-1, simplification assumee : chaque indice compte une phrase, ne nomme jamais la bonne
     * reponse de son item et ne dit jamais "ta reponse".
     */
    @Test
    void sci1ChaqueIndiceCompteUnePhraseEtNeNommeNiLaBonneReponseNiTaReponse() {
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(() -> new AssertionError(
                    "item '" + item.enonce() + "' : indice attendu"));
            Matcher fins = FIN_DE_PHRASE.matcher(indice);
            int occurrences = 0;
            while (fins.find()) {
                occurrences++;
            }
            assertThat(occurrences)
                    .as("item '%s' : l'indice compte une seule phrase (SCI-1) - \"%s\"",
                            item.enonce(), indice)
                    .isEqualTo(1);
            String indiceStrippe = indice.strip();
            assertThat(indiceStrippe.endsWith(".") || indiceStrippe.endsWith("!")
                    || indiceStrippe.endsWith("?"))
                    .as("item '%s' : l'indice se termine par un signe de fin de phrase (. ! ou ?)",
                            item.enonce())
                    .isTrue();
            assertThat(indice)
                    .as("item '%s' : l'indice ne nomme pas la bonne reponse (SCI-1)", item.enonce())
                    .doesNotContain(item.bonneReponse());
            assertThat(indice.toLowerCase(java.util.Locale.FRENCH))
                    .as("item '%s' : l'indice ne dit jamais \"ta reponse\" (SCI-1)", item.enonce())
                    .doesNotContain("ta réponse")
                    .doesNotContain("ta reponse");
        }
    }

    /** SCI-3 : au plus floor(n/6) items dont la bonne reponse porte un chiffre. */
    @Test
    void sci3AuPlusUnSixiemeDesBonnesReponsesPortentUneValeurNumerique() {
        long numeriques = items().stream()
                .filter(item -> VALEUR_NUMERIQUE.matcher(item.bonneReponse()).find()).count();

        assertThat(numeriques)
                .as("SCI-3 : au plus un item sur %d porte une valeur numerique en bonne reponse",
                        DIVISEUR_SCI_3)
                .isLessThanOrEqualTo(items().size() / DIVISEUR_SCI_3);
    }

    /**
     * SCI-2 : aucun enonce ne s'adresse a l'enfant a la deuxieme personne en lui imputant une
     * faute ("tu as", "tu n'as pas", "chez toi").
     */
    @Test
    void sci2AucunEnonceNeMetEnCauseLEnfantOuSonFoyer() {
        Pattern misesEnCause = Pattern.compile(
                "\\btu as\\b|\\btu n'as pas\\b|\\bchez toi\\b|\\bta famille\\b|\\btes parents\\b",
                Pattern.CASE_INSENSITIVE);
        for (Question item : items()) {
            assertThat(misesEnCause.matcher(item.enonce()).find())
                    .as("item '%s' : SCI-2 interdit de mettre en cause l'enfant ou son foyer",
                            item.enonce())
                    .isFalse();
        }
    }

    /**
     * HIS-3 (chaque fait porte sa source) : chaque ligne {@code - enonce:} du fichier brut porte un
     * commentaire {@code # HIS-3:} suivi d'un texte non vide, et jamais {@code # HIS-1:} - le
     * marqueur du lot d'histoire-geographie ne se recopie pas ici.
     */
    @Test
    void his3ChaqueLigneDItemPorteUnMarqueurHis3NonVideEtJamaisHis1() {
        List<String> lignesDItem = texteBrutDuPaquet().lines()
                .filter(ligne -> ligne.contains("- enonce:")).toList();

        assertThat(lignesDItem).as("le paquet porte bien des lignes d'item").hasSize(12);

        for (String ligne : lignesDItem) {
            assertThat(ligne)
                    .as("ligne '%s' : ne porte jamais le marqueur HIS-1, reserve au lot"
                            + " d'histoire-geographie", ligne)
                    .doesNotContain("# HIS-1:");

            int index = ligne.indexOf("# HIS-3:");
            assertThat(index)
                    .as("ligne '%s' : porte un commentaire # HIS-3: (HIS-3)", ligne)
                    .isGreaterThan(-1);
            int finDuCommentaire = ligne.indexOf(" # lieu:", index);
            String texteSource = finDuCommentaire > -1
                    ? ligne.substring(index + "# HIS-3:".length(), finDuCommentaire)
                    : ligne.substring(index + "# HIS-3:".length());
            assertThat(texteSource.strip())
                    .as("ligne '%s' : le commentaire HIS-3 porte un texte non vide", ligne)
                    .isNotEmpty();
        }
    }

    /** Ligne de referentiel : SCI-CM1-16 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementSciCm116() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("géologiques");
    }
}
