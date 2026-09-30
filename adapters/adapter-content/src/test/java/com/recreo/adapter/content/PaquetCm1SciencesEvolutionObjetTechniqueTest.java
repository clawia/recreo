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
 * <b>S44-SCI-OBJET-TECHNIQUE, le paquet {@code cm1-sciences-evolution-objet-technique}, ligne
 * {@code SCI-CM1-13} de "Sciences et technologie" (CM1).</b> Referentiel :
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s44-musique-et-sciences-cm1.md}, section 3 (EVO-1,
 * EVO-2) ; conditions de fermeture citees par la story corrigee (avis de faisabilite de
 * theo-archi, {@code squad/rapports/avis-faisabilite-s44-mus-sci.md}, section 5.2).
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par ce que la bonne reponse enonce : la
 * tache A, <b>"ce qui a change"</b> (la bonne reponse nomme le passage d'un etat ancien a un etat
 * recent, forme "X a laisse la place a Y") et la tache B, <b>"ce qui n'a pas change"</b> (la bonne
 * reponse nomme l'usage qui persiste, EVO-2). Aucun item de la tache B ne porte sur le velo, la
 * lampe de poche, le taille-crayon ou le ventilateur, dont la fonction est deja servie par
 * {@code cm1-sciences-objet-technique} (SCI-CM1-12).
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque evolution retenue, et le
 * sens des distracteurs d'EVO-2 (vrais d'un etat, faux de l'autre) restent la garde de
 * {@code nadia-prof}, en jouant.
 */
class PaquetCm1SciencesEvolutionObjetTechniqueTest {

    private static final String PAQUET = "cm1-sciences-evolution-objet-technique";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** SCI-3 : au plus n/6 items dont la bonne reponse porte un chiffre ou un siecle en chiffres romains. */
    private static final int DIVISEUR_SCI_3 = 6;

    /**
     * Les quatre objets de {@code cm1-sciences-objet-technique} (SCI-CM1-12) : la tache "ce qui
     * n'a pas change" (EVO-2) ne porte jamais sur l'un d'eux, leur fonction etant deja servie en
     * bonne reponse sur cette ligne-la.
     */
    private static final Set<String> OBJETS_DE_SCI_CM1_12 = Set.of(
            "vélo", "lampe de poche", "taille-crayon", "ventilateur");

    /** Bonne reponse des items de la tache A, "ce qui a change" : le passage d'un etat a l'autre. */
    private static final Set<String> REPONSES_DE_CE_QUI_A_CHANGE = Set.of(
            "le bois a laissé la place au plastique",
            "la flamme a laissé la place au courant électrique",
            "la force de l'animal a laissé la place à un moteur",
            "le bloc de glace a laissé la place à un moteur électrique",
            "la force de l'eau a laissé la place à l'électricité");

    /** Bonne reponse des items de la tache B, "ce qui n'a pas change" (EVO-2). */
    private static final Set<String> REPONSES_DE_CE_QUI_N_A_PAS_CHANGE = Set.of(
            "on s'en sert pour écrire",
            "on l'utilise pour parler à quelqu'un qui est loin",
            "on l'utilise pour nettoyer des vêtements sales",
            "on l'utilise pour avancer sur l'eau",
            "on l'utilise pour savoir combien de temps s'est écoulé",
            "on l'utilise pour cuire les aliments");

    /** Un chiffre, ou un siecle en chiffres romains (convention SCI-3). */
    private static final Pattern VALEUR_NUMERIQUE = Pattern.compile("\\d|\\b[IVXL]+e\\b");

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

    private static List<Question> itemsDeCeQuiAChange() {
        return items().stream()
                .filter(item -> REPONSES_DE_CE_QUI_A_CHANGE.contains(item.bonneReponse())).toList();
    }

    private static List<Question> itemsDeCeQuiNAPasChange() {
        return items().stream()
                .filter(item -> REPONSES_DE_CE_QUI_N_A_PAS_CHANGE.contains(item.bonneReponse()))
                .toList();
    }

    /** Le texte brut du paquet, tel qu'ecrit sur le classpath - commentaires compris. */
    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCm1SciencesEvolutionObjetTechniqueTest.class
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

    /** FER-2 : au moins dix items servent cette ligne (onze a la livraison). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1, extension disjonctive ("dans sa fabrication ou dans son usage") : l'evolution est en
     * position de bonne reponse sur chaque item (EVO-1, chaque bonne reponse enonce une relation
     * entre deux etats) ; la tache A sert l'element "fabrication" (passage d'une matiere ou d'une
     * source d'energie a une autre).
     */
    @Test
    void fer1LElementFabricationEstServiParLaTacheDeCeQuiAChange() {
        assertThat(itemsDeCeQuiAChange())
                .as("FER-1 : au moins un item sert l'element \"fabrication\" du libelle disjonctif")
                .isNotEmpty();
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int ceQuiAChange = itemsDeCeQuiAChange().size();
        int ceQuiNAPasChange = itemsDeCeQuiNAPasChange().size();

        assertThat(ceQuiAChange).as("la tache \"ce qui a change\" est servie").isGreaterThan(0);
        assertThat(ceQuiNAPasChange).as("la tache \"ce qui n'a pas change\" est servie")
                .isGreaterThan(0);
        assertThat(Math.min(ceQuiAChange, ceQuiNAPasChange))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (les deux taches additionnees). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
        assertThat(itemsDeCeQuiAChange().size() + itemsDeCeQuiNAPasChange().size())
                .as("chaque item du paquet appartient a l'une des deux taches, aucun aux deux")
                .isEqualTo(11);
    }

    /**
     * EVO-2 : aucun item de la tache "ce qui n'a pas change" ne porte sur le velo, la lampe de
     * poche, le taille-crayon ou le ventilateur - les quatre objets de {@code
     * cm1-sciences-objet-technique} (SCI-CM1-12), dont la fonction y est deja servie en bonne
     * reponse.
     */
    @Test
    void evo2AucunItemDeCeQuiNAPasChangeNePorteSurLesQuatreObjetsDeSciCm112() {
        for (Question item : itemsDeCeQuiNAPasChange()) {
            for (String objet : OBJETS_DE_SCI_CM1_12) {
                assertThat(item.enonce())
                        .as("item '%s' : la tache \"ce qui n'a pas change\" ne porte pas sur les"
                                + " objets de SCI-CM1-12 (EVO-2)", item.enonce())
                        .doesNotContainIgnoringCase(objet);
            }
        }
    }

    /**
     * EVO-1 : aucune bonne reponse n'est un terme nu deja servi par SCI-CM1-11 (une famille de
     * materiau seule) ou par SCI-CM1-12 (un composant seul, ou la fonction d'un objet pris
     * isolement) - chaque bonne reponse compte au moins trois mots, jamais un seul terme nu.
     */
    @Test
    void evo1AucuneBonneReponseNEstUnTermeNu() {
        Set<String> familles = Set.of("le bois", "le métal", "le verre", "le plastique", "le tissu");
        for (Question item : items()) {
            assertThat(familles)
                    .as("item '%s' : la bonne reponse n'est pas une famille de materiau seule"
                            + " (EVO-1)", item.enonce())
                    .doesNotContain(item.bonneReponse());
            assertThat(item.bonneReponse().strip().split("\\s+"))
                    .as("item '%s' : la bonne reponse enonce une relation entre deux etats, jamais"
                            + " un terme nu (EVO-1)", item.enonce())
                    .hasSizeGreaterThanOrEqualTo(3);
        }
    }

    /** SCI-4, simplification assumee (avis de faisabilite, section 5.2) : chaque item porte deux distracteurs. */
    @Test
    void sci4ChaqueItemPorteDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus (SCI-4)", item.enonce())
                    .hasSize(2);
        }
    }

    /**
     * SCI-1, simplification assumee (avis de faisabilite, section 5.2) : chaque indice compte une
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

    /**
     * SCI-3 : au plus floor(n/6) items dont la bonne reponse porte un chiffre ou un siecle en
     * chiffres romains.
     */
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
     * BIA-3 (transpose depuis "autrefois et aujourd'hui") : aucun texte du paquet ne qualifie
     * l'objet ancien de difficile, dur, pauvre ou pas pratique - la chronologie n'est pas un
     * progres.
     */
    @Test
    void bia3AucunTexteNeQualifieLObjetAncienDeDifficileDurPauvreOuPasPratique() {
        Pattern motsInterdits = Pattern.compile(
                "\\bdifficiles?\\b|\\bpauvres?\\b|\\bpas pratiques?\\b|\\bdurs?\\b",
                Pattern.CASE_INSENSITIVE);
        for (Question item : items()) {
            List<String> textes = new ArrayList<>();
            textes.add(item.enonce());
            textes.add(item.bonneReponse());
            textes.addAll(item.distracteurs());
            item.indice().ifPresent(textes::add);
            for (String texte : textes) {
                assertThat(motsInterdits.matcher(texte).find())
                        .as("item '%s', texte '%s' : BIA-3 interdit difficile, pauvre, pas"
                                + " pratique et dur (hors le verbe durer)", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /**
     * HIS-3 (chaque fait porte sa source) : chaque ligne {@code - enonce:} du fichier brut porte
     * un commentaire {@code # HIS-3:} suivi d'un texte non vide, et jamais {@code # HIS-1:} - le
     * marqueur du lot d'histoire-geographie ne se recopie pas ici (avis de faisabilite, section
     * 5.3).
     */
    @Test
    void his3ChaqueLigneDItemPorteUnMarqueurHis3NonVideEtJamaisHis1() {
        List<String> lignesDItem = texteBrutDuPaquet().lines()
                .filter(ligne -> ligne.contains("- enonce:")).toList();

        assertThat(lignesDItem).as("le paquet porte bien des lignes d'item").hasSize(11);

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

    /** Ligne de referentiel : SCI-CM1-13 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementSciCm113() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("évolution");
    }
}
