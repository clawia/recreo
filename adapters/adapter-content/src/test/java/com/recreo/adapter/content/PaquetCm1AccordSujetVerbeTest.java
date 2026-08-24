package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S19-3 (FICHE-bandit-manchot-accord-sujet-verbe-cm1.md, docs/backlog/backlog.md) : le paquet
 * "cm1-accord-sujet-verbe". Aucun arbitrage prealable requis (nadia-prof, meme fiche) ; les
 * criteres ci-dessous sont ceux de camille-po, verifies a l'audit.
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables. La plausibilite du risque d'accord
 * pour un enfant de CM1, l'homogeneite des trois temps et la clarte des phrases restent le jugement
 * de nadia-prof - aucun automate de ce depot ne juge le sens d'une phrase.</p>
 *
 * <p>Les proprietes generiques (indice non vide, marqueur de trou sur chaque item, aucun item ne
 * repete l'ecran d'un autre du meme paquet, aucune proposition egale a une autre dans un item, une
 * seule graphie de matiere, l'entree de catalogue charge bien ce paquet) sont deja derivees du
 * repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCm1AccordSujetVerbeTest {

    private static final String PAQUET = "cm1-accord-sujet-verbe";

    private static final String ID_DE_CATALOGUE = "bandit-manchot-accord-sujet-verbe-cm1";

    /** Le compte est garde exactement, pas seulement atteint (plancher de 20 fixe par la fiche). */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 21;

    private static final String MARQUEUR_DE_TROU = "___";

    /**
     * Distinction a garder de la ligne conjugaison deja couverte : le sujet n'est jamais un pronom
     * personnel clitique seul, immediatement suivi du verbe - a la difference des pronoms toniques
     * ("moi", "toi") qui, eux, entrent legitimement dans un sujet compose ("mon frère et moi").
     */
    private static final Set<String> PRONOMS_SUJETS_CLITIQUES =
            Set.of("je", "j", "tu", "il", "elle", "on", "nous", "vous", "ils", "elles");

    /**
     * Classement par temps, tenu a la main plutot que devine par un motif sur la terminaison :
     * present et imparfait partagent trop de terminaisons pour qu'un automate les distingue sans se
     * tromper. Le classement verifie que la competence du referentiel - present, futur et
     * imparfait chacun representes - est bien tenue, a sept items pres chacun.
     */
    private static final Map<String, String> TEMPS_PAR_ENONCE = Map.ofEntries(
            Map.entry("Les enfants du voisin ___ dans le jardin tous les après-midis.", "present"),
            Map.entry("Le chat de ma grand-mère, malgré son âge, ___ encore sur le mur du jardin.", "present"),
            Map.entry("Mon frère et moi ___ tous les mercredis au foot.", "present"),
            Map.entry("Les élèves de la classe, après la récréation, ___ leur cahier de mathématiques.", "present"),
            Map.entry("Toi et ton frère ___ la vaisselle après le repas.", "present"),
            Map.entry("Le vieux chêne du parc ___ de l'ombre sur tout le banc.", "present"),
            Map.entry("Les vagues de la mer, malgré le vent, ___ doucement sur le sable.", "present"),
            Map.entry("Demain, les enfants du quartier ___ un grand tournoi de football.", "futur"),
            Map.entry("La semaine prochaine, mon cousin et moi ___ à la piscine tous les jours.", "futur"),
            Map.entry("Bientôt, les hirondelles, comme chaque année, ___ vers les pays chauds.", "futur"),
            Map.entry("L'année prochaine, toi et tes amis ___ dans une nouvelle école.", "futur"),
            Map.entry("Dans quelques minutes, le train de Paris ___ en gare.", "futur"),
            Map.entry("Cet été, mes cousins ___ une cabane au fond du jardin.", "futur"),
            Map.entry("Dans deux jours, le chien des voisins ___ un nouveau panier.", "futur"),
            Map.entry("Autrefois, les paysans du village ___ leurs champs à la main.", "imparfait"),
            Map.entry("Quand j'étais petit, mon frère et moi ___ ensemble dans la même chambre.", "imparfait"),
            Map.entry("Chaque hiver, les habitants de la vallée ___ beaucoup de neige.", "imparfait"),
            Map.entry("Avant les vacances, toi et ta sœur ___ tous les soirs à la bibliothèque.", "imparfait"),
            Map.entry("Il y a longtemps, le vieux moulin de la rivière ___ encore le blé du village.", "imparfait"),
            Map.entry("Autrefois, les enfants, même les jours de pluie, ___ dehors après l'école.", "imparfait"),
            Map.entry("Quand j'étais au CP, mes amis et moi ___ toujours les premiers dans la cour.", "imparfait"));

    @Test
    void lePaquetCompteExactementVingtEtUnItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void aucunSujetNEstUnPronomPersonnelClitiqueSeulImmediatementSuiviDuVerbe() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            int position = enonce.indexOf(MARQUEUR_DE_TROU);
            assertThat(position).as("'%s' : le marqueur de trou est present", enonce).isGreaterThan(-1);

            String avantLeTrou = enonce.substring(0, position).strip().replaceAll("[,;:.!?]+$", "");
            String[] mots = avantLeTrou.split("\\s+");
            String dernierMot = mots[mots.length - 1].toLowerCase().replace("'", "");

            assertThat(PRONOMS_SUJETS_CLITIQUES)
                    .as("'%s' : le mot juste avant le trou ('%s') ne doit jamais etre un pronom"
                            + " personnel clitique seul - ce serait redemander la conjugaison deja"
                            + " testee par cm1-conjugaison-present, pas l'accord avec un sujet"
                            + " eloigne, compose ou nominal", enonce, dernierMot)
                    .doesNotContain(dernierMot);
        }
    }

    @Test
    void lesTroisTempsDuReferentielSontChacunRepresentes() {
        List<Question> items = tousLesItems(paquet());
        assertThat(items.size())
                .as("le classement par temps doit couvrir tout le paquet")
                .isEqualTo(TEMPS_PAR_ENONCE.size());

        Map<String, Long> parTemps = new java.util.HashMap<>();
        for (Question item : items) {
            String temps = TEMPS_PAR_ENONCE.get(item.enonce());
            assertThat(temps)
                    .as("'%s' : chaque item du paquet servi doit figurer dans le classement par"
                            + " temps de ce test", item.enonce())
                    .isNotNull();
            parTemps.merge(temps, 1L, Long::sum);
        }

        assertThat(parTemps.get("present")).as("present represente").isEqualTo(7L);
        assertThat(parTemps.get("futur")).as("futur represente").isEqualTo(7L);
        assertThat(parTemps.get("imparfait")).as("imparfait represente").isEqualTo(7L);
    }

    @Test
    void leDescripteurEstFrancaisCm1OrthographeEtGrammaireAccordSujetVerbe() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Orthographe et grammaire");
        assertThat(descripteur.competence())
                .isEqualTo("Accorder le verbe avec son sujet (cas fréquents, temps simples :"
                        + " présent, futur, imparfait)");
    }

    /**
     * La consigne de catalogue reste generique : elle ne nomme aucune bonne reponse d'aucun item,
     * exactement comme "bandit-manchot-synonymes-cm1" (ADR-0052).
     */
    @Test
    void laConsigneDeCatalogueEstGeneriqueEtNeNommeAucuneBonneReponse() {
        Map<String, Object> catalogue = catalogueCharge();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) catalogue.get("jeux");
        Map<String, Object> entree = jeux.stream()
                .filter(jeu -> ID_DE_CATALOGUE.equals(jeu.get("id")))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        ID_DE_CATALOGUE + " : aucune entree de ce nom dans catalogue.yaml"));

        assertThat(entree.get("paquet")).isEqualTo(PAQUET);
        assertThat(entree.get("nombreDeManches")).isEqualTo(10);
        String consigne = String.valueOf(entree.get("consigne"));

        for (Question item : tousLesItems(paquet())) {
            assertThat(consigne)
                    .as("la consigne ne nomme aucune bonne reponse d'aucun item", item.bonneReponse())
                    .doesNotContain(item.bonneReponse());
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> catalogueCharge() {
        try (var flux = PaquetCm1AccordSujetVerbeTest.class.getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            return (Map<String, Object>) new org.yaml.snakeyaml.Yaml().load(flux);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
