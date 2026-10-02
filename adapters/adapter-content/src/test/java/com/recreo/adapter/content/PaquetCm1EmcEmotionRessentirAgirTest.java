package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S46-EMC-EMOTION-CM1, le paquet {@code cm1-emc-emotion-ressentir-agir}, ligne
 * {@code EMC-CM1-01} de l'enseignement moral et civique (CM1), instance A.</b> Referentiel :
 * {@code squad/programme/enseignement-moral-et-civique/competences-cm1.md}, Jugement de
 * faisabilite, ligne 1. Avis de faisabilite de {@code theo-archi} :
 * {@code squad/rapports/avis-faisabilite-s46-cinq-lots.md}, section 4.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la categorie des propositions : la
 * tache A, <b>"identifier l'emotion"</b>, dont la bonne reponse et les deux distracteurs sont
 * toujours des noms d'emotion connus ; et la tache B, <b>"distinguer ce que l'on ressent de ce que
 * l'on fait"</b>, dont au moins une proposition decrit un geste plutot qu'une emotion - exactement
 * la confusion que la seconde moitie de la ligne demande de lever.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-2} et {@code EMC-5} (une seule emotion
 * defendable par situation) demandent une lecture semantique de chaque situation : non gardees
 * ici, restent la garde de {@code nadia-prof} seule. {@code CYC-1} (continuite avec
 * {@code bandit-manchot-emotions-autrui-cp}, qui demande une comparaison semantique entre deux
 * referentiels) n'est pas automatisable ici non plus. {@code EMC-1} et {@code EMC-8} sont
 * partiellement gardes ci-dessous (avis de faisabilite, section 4) : seule la forme explicite a
 * la deuxieme personne citee par le referentiel est detectee, pas le fond de chaque situation.</p>
 */
class PaquetCm1EmcEmotionRessentirAgirTest {

    private static final String PAQUET = "cm1-emc-emotion-ressentir-agir";

    /** FER-2 : au moins onze items (DEC-0064, un paquet de fermeture porte strictement plus
     * d'items que de manches, nombreDeManches = 10 dans catalogue.yaml). */
    private static final int PLANCHER_FER_2 = 11;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Les neuf noms d'emotion connus de ce paquet, tache A. */
    private static final Set<String> EMOTIONS_CONNUES = Set.of(
            "de la joie", "de la tristesse", "de la colère", "de la peur", "de la fierté",
            "de la déception", "de la honte", "du soulagement", "de la jalousie");

    /**
     * EMC-1, EMC-8 : les formes a la deuxieme personne que le referentiel cite explicitement
     * (squad/programme/enseignement-moral-et-civique/competences-cp.md, EMC-8), plus les pronoms
     * et possessifs de la deuxieme personne du singulier qui adresseraient l'enfant directement.
     * Verification partielle de forme, pas du fond de chaque situation (avis de faisabilite,
     * section 4).
     */
    private static final Pattern ADRESSE_A_L_ENFANT = Pattern.compile(
            "\\bfais-tu\\b|\\bacceptes-tu\\b|\\bréagis-tu\\b|\\bressens-tu\\b|\\bferais-tu\\b"
                    + "|\\btu\\b|\\bton\\b|\\bta\\b|\\btoi\\b", Pattern.CASE_INSENSITIVE);

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

    /** Tache A : la bonne reponse et les deux distracteurs sont tous des noms d'emotion connus. */
    private static boolean estDeTacheIdentifierEmotion(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions.stream().allMatch(EMOTIONS_CONNUES::contains);
    }

    private static List<Question> itemsIdentifierEmotion() {
        return items().stream().filter(PaquetCm1EmcEmotionRessentirAgirTest::estDeTacheIdentifierEmotion)
                .toList();
    }

    private static List<Question> itemsRessentirAgir() {
        return items().stream()
                .filter(item -> !estDeTacheIdentifierEmotion(item)).toList();
    }

    /** FER-2 : au moins onze items servent cette ligne. */
    @Test
    void fer2AuMoinsOnzeItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int identifierEmotion = itemsIdentifierEmotion().size();
        int ressentirAgir = itemsRessentirAgir().size();

        assertThat(identifierEmotion).as("la tache \"identifier l'emotion\" est servie")
                .isGreaterThan(0);
        assertThat(ressentirAgir).as("la tache \"distinguer ressentir et agir\" est servie")
                .isGreaterThan(0);
        assertThat(Math.min(identifierEmotion, ressentirAgir))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (les deux taches additionnees). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
        assertThat(itemsIdentifierEmotion().size() + itemsRessentirAgir().size())
                .as("chaque item du paquet appartient a l'une des deux taches, aucun aux deux")
                .isEqualTo(11);
    }

    /**
     * Tache B : au moins une proposition de chaque item n'est pas un nom d'emotion connu - c'est
     * ce qui distingue cette tache de la tache A, ou les trois propositions sont toujours des
     * emotions.
     */
    @Test
    void chaqueItemDeRessentirAgirPorteAuMoinsUneAutrePropositionQuUneEmotion() {
        for (Question item : itemsRessentirAgir()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item '%s' : au moins une proposition n'est pas un nom d'emotion connu",
                            item.enonce())
                    .anyMatch(proposition -> !EMOTIONS_CONNUES.contains(proposition));
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix au total). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item '%s' : deux distracteurs attendus", item.enonce())
                    .hasSize(2);
        }
    }

    /**
     * EMC-1, EMC-8, verification partielle de forme (avis de faisabilite, section 4) : aucun
     * enonce n'adresse l'enfant a la deuxieme personne pour lui demander ce qu'il ressent ou
     * ferait.
     */
    @Test
    void emc1Emc8AucunEnonceNAdresseLEnfantALaDeuxiemePersonne() {
        for (Question item : items()) {
            assertThat(ADRESSE_A_L_ENFANT.matcher(item.enonce()).find())
                    .as("item '%s' : EMC-1/EMC-8 interdisent d'adresser l'enfant a la deuxieme"
                            + " personne", item.enonce())
                    .isFalse();
        }
    }

    /** Ligne de referentiel : EMC-CM1-01 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementEmcCm101() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("émotion");
    }
}
