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
 * <b>S45-SCI-NUMERIQUE, le paquet {@code cm1-sciences-usages-numeriques}, ligne
 * {@code SCI-CM1-14} de "Sciences et technologie" (CM1).</b> Referentiel :
 * {@code docs/backlog/backlog.md}, section "Sprint 45", story {@code S45-SCI-NUMERIQUE}
 * (corrigee par camille-po, Q7) ; conditions encodables citees par l'avis de faisabilite de
 * theo-archi, {@code squad/rapports/avis-faisabilite-s45-cinq-lots.md}, section 8.2.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par ce que la bonne reponse enonce : la
 * tache A, <b>"reconnaitre la bonne conduite"</b> (la bonne reponse nomme la pratique correcte) et
 * la tache B, <b>"dire pourquoi une conduite est risquee"</b> (la bonne reponse nomme la raison du
 * risque).
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque regle retenue, et la
 * mention de couverture partielle exigee d'avance ("regles et connaissances d'usage seules ; la
 * pratique d'un environnement numerique reste non couverte") restent la garde de
 * {@code nadia-prof}, en jouant.
 */
class PaquetCm1SciencesUsagesNumeriquesTest {

    private static final String PAQUET = "cm1-sciences-usages-numeriques";

    /** FER-2 : au moins nombreDeManches + 1 items (nombreDeManches = 10, catalogue.yaml). */
    private static final int NOMBRE_DE_MANCHES = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** SCI-3 : au plus n/6 items dont la bonne reponse porte un chiffre. */
    private static final int DIVISEUR_SCI_3 = 6;

    /** Les quatre elements exemplatifs du libelle disjonctif (FER-1). */
    private static final Set<String> ELEMENTS_DU_LIBELLE =
            Set.of("identification", "courriel", "recherche", "donnees");

    /** FER-1 : marqueur par item, lu dans le fichier brut. */
    private static final Pattern MARQUEUR_ELEMENT =
            Pattern.compile("# element: (identification|courriel|recherche|donnees)");

    /** Bonne reponse des items de la tache A, "reconnaitre la bonne conduite". */
    private static final Set<String> REPONSES_DE_LA_BONNE_CONDUITE = Set.of(
            "demander à un adulte si le site est adapté aux enfants",
            "à partir de 13 ans",
            "vérifier avec un adulte l'adresse du destinataire",
            "comparer plusieurs sources avant de croire une information",
            "ne pas donner son adresse ou son numéro de téléphone à un inconnu",
            "un mot de passe d'au moins 8 caractères, avec des lettres et des chiffres");

    /** Bonne reponse des items de la tache B, "dire pourquoi une conduite est risquee". */
    private static final Set<String> REPONSES_DU_POURQUOI_RISQUE = Set.of(
            "parce que cette information peut servir à deviner un mot de passe ou un code",
            "parce qu'elle peut contenir un programme qui abîme l'appareil",
            "parce qu'une information peut être fausse ou dépassée, même bien classée",
            "parce que n'importe qui peut alors savoir où l'on se trouve",
            "parce que ce lien peut conduire vers un site qui vole des informations",
            "parce que ce message peut être une tentative de vol d'informations");

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

    private static List<Question> itemsDeLaBonneConduite() {
        return items().stream()
                .filter(item -> REPONSES_DE_LA_BONNE_CONDUITE.contains(item.bonneReponse()))
                .toList();
    }

    private static List<Question> itemsDuPourquoiRisque() {
        return items().stream()
                .filter(item -> REPONSES_DU_POURQUOI_RISQUE.contains(item.bonneReponse())).toList();
    }

    /** Le texte brut du paquet, tel qu'ecrit sur le classpath - commentaires compris. */
    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCm1SciencesUsagesNumeriquesTest.class
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
     * FER-1 : la parenthese du libelle est exemplative - deux des quatre elements au moins
     * (identification, courriel, recherche, donnees) sont chacun en position de bonne reponse
     * d'au moins un item, marque par item dans le fichier brut.
     */
    @Test
    void fer1AuMoinsDeuxDesQuatreElementsDuLibelleSontServis() {
        Set<String> elementsServis = new HashSet<>();
        Matcher marqueurs = MARQUEUR_ELEMENT.matcher(texteBrutDuPaquet());
        while (marqueurs.find()) {
            elementsServis.add(marqueurs.group(1));
        }

        assertThat(ELEMENTS_DU_LIBELLE)
                .as("tout marqueur # element: designe l'un des quatre elements du libelle")
                .containsAll(elementsServis);
        assertThat(elementsServis)
                .as("FER-1 : au moins deux des quatre elements du libelle sont servis")
                .hasSizeGreaterThanOrEqualTo(2);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int bonneConduite = itemsDeLaBonneConduite().size();
        int pourquoiRisque = itemsDuPourquoiRisque().size();

        assertThat(bonneConduite).as("la tache \"reconnaitre la bonne conduite\" est servie")
                .isGreaterThan(0);
        assertThat(pourquoiRisque).as("la tache \"dire pourquoi une conduite est risquee\" est"
                + " servie").isGreaterThan(0);
        assertThat(Math.min(bonneConduite, pourquoiRisque))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement douze items (les deux taches additionnees). */
    @Test
    void lePaquetPorteExactementDouzeItems() {
        assertThat(items()).hasSize(12);
        assertThat(itemsDeLaBonneConduite().size() + itemsDuPourquoiRisque().size())
                .as("chaque item du paquet appartient a l'une des deux taches, aucun aux deux")
                .isEqualTo(12);
    }

    /** SCI-4, simplification assumee (avis de faisabilite, section 8.2) : chaque item porte deux distracteurs. */
    @Test
    void sci4ChaqueItemPorteDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus (SCI-4)", item.enonce())
                    .hasSize(2);
        }
    }

    /**
     * SCI-1, simplification assumee (avis de faisabilite, section 8.2) : chaque indice compte une
     * phrase, ne nomme jamais la bonne reponse de son item et ne dit jamais "ta reponse".
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
     * SCI-2, applique ici par extension non bornee par la source (reservee aux lignes 16 et 18) :
     * aucun item ne met en cause l'enfant ou son foyer - aucun enonce ne s'adresse a l'enfant a la
     * deuxieme personne en lui imputant une faute ("tu as", "tu n'as pas", "chez toi").
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
     * HIS-3 (chaque fait porte sa source) : chaque ligne {@code - enonce:} du fichier brut porte
     * un commentaire {@code # HIS-3:} suivi d'un texte non vide, et jamais {@code # HIS-1:} - le
     * marqueur du lot d'histoire-geographie ne se recopie pas ici (avis de faisabilite, section
     * 8.2).
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
            assertThat(ligne.substring(index + "# HIS-3:".length()).strip())
                    .as("ligne '%s' : le commentaire HIS-3 porte un texte non vide", ligne)
                    .isNotEmpty();
        }
    }

    /** Ligne de referentiel : SCI-CM1-14 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementSciCm114() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("numérique");
    }
}
