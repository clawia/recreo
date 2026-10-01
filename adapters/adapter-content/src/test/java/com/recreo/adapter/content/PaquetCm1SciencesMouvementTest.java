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
 * <b>S46-SCI-MOUVEMENT-CM1, le paquet {@code cm1-sciences-mouvement}, ligne {@code SCI-CM1-03} de
 * "Sciences et technologie" (CM1), instance B.</b> Referentiel :
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-
 * des-quinze-reserves.md}, section 2.2, ligne 3. Avis de faisabilite de {@code theo-archi} :
 * {@code squad/rapports/avis-faisabilite-s46-cinq-lots.md}, section 6.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par ce que la bonne reponse enonce : la
 * tache A, <b>"decrire une trajectoire"</b> (la bonne reponse nomme une forme de trajectoire ou un
 * point de repere), et la tache B, <b>"comparer deux mouvements par leur vitesse"</b> (la bonne
 * reponse nomme le mobile le plus rapide, le plus lent, ou l'egalite des deux).
 *
 * <p><b>Refus d'avance tenu</b> (avis de faisabilite, section 6, citant l'arbitrage prealable de
 * s30) : aucun item de ce paquet ne calcule une vitesse, une duree ou une longueur - verifie ici en
 * s'assurant qu'aucun enonce ni aucune proposition ne porte le moindre chiffre, condition plus
 * forte que la seule absence d'unite, et qui evite du meme geste le piege de fabrication nomme par
 * {@code theo-archi} (une proposition qui commence par un nombre suivi de {@code h}, {@code min}...
 * serait lue comme une duree par {@code ADR-0102}).
 *
 * <p><b>Ce que ce test ne juge pas.</b> L'exactitude physique de chaque situation retenue, et la
 * plausibilite de chaque distracteur, restent la garde de {@code nadia-prof}, instance B, en
 * jouant.
 */
class PaquetCm1SciencesMouvementTest {

    private static final String PAQUET = "cm1-sciences-mouvement";

    /** FER-2 : au moins onze items (DEC-0064, un paquet de fermeture porte strictement plus
     * d'items que de manches, nombreDeManches = 10 dans catalogue.yaml). */
    private static final int PLANCHER_FER_2 = 11;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** SCI-3 : au plus n/6 items dont la bonne reponse porte un chiffre. */
    private static final int DIVISEUR_SCI_3 = 6;

    /** FER-1 : les trois elements de la parenthese conjonctive du libelle. */
    private static final Set<String> ELEMENTS_DU_LIBELLE =
            Set.of("trajectoire", "repere", "vitesse");

    /** FER-1 : marqueur par item, lu dans le fichier brut. */
    private static final Pattern MARQUEUR_ELEMENT =
            Pattern.compile("# element: (trajectoire|repere|vitesse)");

    /** Bonne reponse des items de la tache A, "decrire une trajectoire" (forme ou repere). */
    private static final Set<String> REPONSES_DE_TRAJECTOIRE = Set.of(
            "une trajectoire circulaire", "une trajectoire rectiligne", "une trajectoire en zigzag",
            "le quai de la gare", "les arbres au bord de la route");

    /** Bonne reponse des items de la tache B, "comparer deux mouvements par leur vitesse". */
    private static final Set<String> REPONSES_DE_VITESSE = Set.of(
            "le cycliste", "l'escargot", "ils vont à la même vitesse", "la voiture",
            "la voiture de sport", "le nageur");

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

    private static List<Question> itemsDeTrajectoire() {
        return items().stream()
                .filter(item -> REPONSES_DE_TRAJECTOIRE.contains(item.bonneReponse())).toList();
    }

    private static List<Question> itemsDeVitesse() {
        return items().stream()
                .filter(item -> REPONSES_DE_VITESSE.contains(item.bonneReponse())).toList();
    }

    /** Le texte brut du paquet, tel qu'ecrit sur le classpath - commentaires compris. */
    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCm1SciencesMouvementTest.class
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

    /** FER-2 : au moins onze items servent cette ligne. */
    @Test
    void fer2AuMoinsOnzeItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1, parenthese conjonctive : les trois elements du libelle (trajectoire, vitesse, point
     * de repere) sont chacun servis par au moins un item, marque par item dans le fichier brut.
     */
    @Test
    void fer1LesTroisElementsDeLaParentheseSontServis() {
        Set<String> elementsServis = new HashSet<>();
        Matcher marqueurs = MARQUEUR_ELEMENT.matcher(texteBrutDuPaquet());
        while (marqueurs.find()) {
            elementsServis.add(marqueurs.group(1));
        }

        assertThat(ELEMENTS_DU_LIBELLE)
                .as("tout marqueur # element: designe l'un des trois elements du libelle")
                .containsAll(elementsServis);
        assertThat(elementsServis)
                .as("FER-1 : les trois elements de la parenthese conjonctive sont tous servis")
                .isEqualTo(ELEMENTS_DU_LIBELLE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int trajectoire = itemsDeTrajectoire().size();
        int vitesse = itemsDeVitesse().size();

        assertThat(trajectoire).as("la tache \"decrire une trajectoire\" est servie")
                .isGreaterThan(0);
        assertThat(vitesse).as("la tache \"comparer deux mouvements par leur vitesse\" est servie")
                .isGreaterThan(0);
        assertThat(Math.min(trajectoire, vitesse))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement douze items (les deux taches additionnees). */
    @Test
    void lePaquetPorteExactementDouzeItems() {
        assertThat(items()).hasSize(12);
        assertThat(itemsDeTrajectoire().size() + itemsDeVitesse().size())
                .as("chaque item du paquet appartient a l'une des deux taches, aucun aux deux")
                .isEqualTo(12);
    }

    /** SCI-4, simplification assumee (avis de faisabilite, section 6) : chaque item porte deux distracteurs. */
    @Test
    void sci4ChaqueItemPorteDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus (SCI-4)", item.enonce())
                    .hasSize(2);
        }
    }

    /**
     * SCI-1, simplification assumee (avis de faisabilite, section 6) : chaque indice compte une
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

    /** SCI-3 : au plus floor(n/6) items dont la bonne reponse porte un chiffre (zero ici). */
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
     * Refus d'avance (avis de faisabilite, section 6, citant l'arbitrage prealable de s30) :
     * aucun item ne calcule une vitesse, une duree ou une longueur. Verifie ici sous une forme
     * plus forte que la seule absence d'unite : aucun chiffre du tout, ni dans l'enonce, ni dans
     * aucune proposition - ce qui evite du meme geste le piege de fabrication nomme par
     * theo-archi (une proposition qui commence par un nombre suivi de h/min serait lue comme une
     * duree par ADR-0102).
     */
    @Test
    void refusDAvanceAucunEnonceNiAucuneDesPropositionsNePorteDeChiffre() {
        for (Question item : items()) {
            assertThat(VALEUR_NUMERIQUE.matcher(item.enonce()).find())
                    .as("item '%s' : refus d'avance, aucun chiffre dans l'enonce", item.enonce())
                    .isFalse();
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(VALEUR_NUMERIQUE.matcher(proposition).find())
                        .as("item '%s', proposition '%s' : refus d'avance, aucun chiffre",
                                item.enonce(), proposition)
                        .isFalse();
            }
        }
    }

    /**
     * HIS-3 (chaque fait porte sa source) : chaque ligne {@code - enonce:} du fichier brut porte
     * un commentaire {@code # HIS-3:} suivi d'un texte non vide, et jamais {@code # HIS-1:} - le
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
            assertThat(ligne.substring(index + "# HIS-3:".length()).strip())
                    .as("ligne '%s' : le commentaire HIS-3 porte un texte non vide", ligne)
                    .isNotEmpty();
        }
    }

    /** Ligne de referentiel : SCI-CM1-03 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementSciCm103() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("trajectoire");
    }
}
