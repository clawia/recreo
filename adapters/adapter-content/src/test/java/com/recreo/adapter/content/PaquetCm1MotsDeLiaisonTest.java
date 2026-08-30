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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S23-LIA (planning de s23, arbitrage prealable de nadia-prof du 2026-08-30,
 * {@code ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md},
 * section 6, `LIA-1` a `LIA-10`) : le paquet "cm1-mots-de-liaison". Paquet integralement neuf.
 *
 * <p><b>Couverture partielle nommee (`LIA-10`)</b> : ce paquet fait choisir un mot de liaison entre
 * deux ou trois phrases ; il ne fait jamais ecrire un texte, ni ordonner plus de trois phrases. La
 * mention exacte que `nadia-prof` exige sur la matrice le jour ou la ligne bouge est : "choix du
 * mot de liaison entre deux ou trois phrases, sans production de texte".
 *
 * <p><b>Ne couvre que les criteres d'acceptation automatisables.</b> `LIA-3` est du type `COMP-2` :
 * que la relation declaree pour la bonne reponse soit reellement celle du texte, et que chaque
 * distracteur rende le texte <i>faux</i> et non seulement moins bon, est un jugement de sens
 * qu'aucun automate de ce depot ne porte - c'est le jugement de `nadia-prof`, item par item, en
 * lisant les cles `relationBonneReponse` et `relationsDistracteurs` que ce paquet declare. Ce que ce
 * garde-fou peut prouver a sa place : que ces deux cles existent sur chaque item, que la relation
 * declaree est bien l'une des quatre nommees par l'arbitrage, et que les trois relations d'un item
 * (bonne reponse et deux distracteurs) sont deux a deux distinctes (`LIA-4`) - la declaration ne
 * dispense pas de la verifier, elle permet de la <i>recompter</i> au lieu de l'<i>etablir</i>.</p>
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, l'entree de catalogue charge bien ce
 * paquet, aucun signe double detachable, aucun mot servi ne s'ecarte de son accent) sont deja
 * derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCm1MotsDeLiaisonTest {

    private static final String PAQUET = "cm1-mots-de-liaison";
    private static final String RESSOURCE_YAML = "/contenu/cm1-mots-de-liaison.yaml";

    /** LIA-9 : au moins vingt items. */
    private static final int NOMBRE_D_ITEMS_MINIMUM = 20;

    /** LIA-9 : aucun mot de liaison n'est la bonne reponse de plus de six items. */
    private static final int LIA_9_PLAFOND_PAR_MOT = 6;

    /** LIA-2 : population fermee des dix mots de liaison admis, et seulement ceux-la. */
    private static final Set<String> MOTS_DE_LIAISON_ADMIS = Set.of(
            "mais", "donc", "car", "puis", "ensuite", "enfin", "d'abord", "alors", "pourtant",
            "parce que");

    /** LIA-5 : les quatre relations que la ligne "structurer un texte court" doit servir. */
    private static final Set<String> RELATIONS_ADMISES =
            Set.of("opposition", "consequence", "cause", "succession");

    /** LIA-6 : au plus 35 mots par item, sur les phrases de l'item (le champ `enonce`). */
    private static final int LIA_6_PLAFOND_MOTS = 35;

    /** LIA-7 : aucun mot de l'item ne depasse onze lettres, apostrophe non comptee. */
    private static final int LIA_7_PLAFOND_LETTRES = 11;

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

    /** Les items tels qu'ecrits dans le YAML servi, avec leurs cles d'auteur (`relationBonneReponse`,
     * `relationsDistracteurs`) qu'aucune fabrique ne lit et que le chargeur reel ne transporte donc
     * pas jusqu'au {@link Question}. */
    private static List<Map<?, ?>> itemsEcrits() {
        try (InputStream flux = PaquetCm1MotsDeLiaisonTest.class.getResourceAsStream(RESSOURCE_YAML)) {
            assertThat(flux).as("%s introuvable sur le classpath", RESSOURCE_YAML).isNotNull();
            @SuppressWarnings("unchecked")
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            List<Map<?, ?>> items = new ArrayList<>();
            for (Object itemBrut : (List<?>) corps.get("items")) {
                items.add((Map<?, ?>) itemBrut);
            }
            return items;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Le nom de la relation, extrait de la valeur declaree ("opposition - faux : ..."). */
    private static String relationDeclaree(String valeur) {
        return valeur.substring(0, valeur.indexOf(" - ")).strip();
    }

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet()))
                .as("LIA-9 : au moins vingt items")
                .hasSizeGreaterThanOrEqualTo(NOMBRE_D_ITEMS_MINIMUM);
    }

    @Test
    void leDescripteurEstFrancaisCm1Vocabulaire() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Vocabulaire");
        assertThat(descripteur.competence())
                .isEqualTo("Utiliser à bon escient des mots de liaison simples pour structurer"
                        + " un texte court");
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("'%s' : deux distracteurs, pour trois propositions au total", item.enonce())
                    .hasSize(2);
            Set<String> propositions = new HashSet<>();
            propositions.add(item.bonneReponse());
            propositions.addAll(item.distracteurs());
            assertThat(propositions)
                    .as("'%s' : les trois propositions sont distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    /**
     * LIA-2 : la bonne reponse et les deux distracteurs de chaque item appartiennent tous les trois
     * a la population fermee des dix mots de liaison admis, "et" et le registre soutenu exclus.
     */
    @Test
    void chaqueItemNUtiliseQueLesDixMotsDeLiaisonAdmis() {
        for (Question item : tousLesItems(paquet())) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(MOTS_DE_LIAISON_ADMIS)
                        .as("'%s' : la proposition '%s' n'appartient pas a la population fermee de"
                                + " LIA-2", item.enonce(), proposition)
                        .contains(proposition);
            }
        }
    }

    @Test
    void chaqueItemPorteExactementUnTrouMarqueParTroisTirets() {
        for (Question item : tousLesItems(paquet())) {
            long trous = item.enonce().chars().filter(c -> c == '_').count();
            assertThat(trous)
                    .as("'%s' : exactement un trou de trois soulignes (LIA-1)", item.enonce())
                    .isEqualTo(3);
        }
    }

    /**
     * LIA-3, la part recomptable, et LIA-4. Chaque item declare, dans le YAML servi,
     * `relationBonneReponse` et une entree `relationsDistracteurs` par distracteur, dont le prefixe
     * avant " - " nomme une relation parmi les quatre de LIA-5. Cette clause verifie que les trois
     * relations d'un meme item (bonne reponse et deux distracteurs) sont deux a deux distinctes -
     * la seule part de LIA-3/LIA-4 qu'un automate peut trancher. Que la relation nommee soit
     * effectivement celle du texte, et que chaque distracteur rende le texte faux plutot que moins
     * bon, reste le jugement de nadia-prof.
     */
    @Test
    void lesTroisRelationsDeChaqueItemSontDeuxADeuxDistinctes() {
        List<Map<?, ?>> items = itemsEcrits();
        assertThat(items).as("aucun item lu : cette clause ne peut rien recompter").isNotEmpty();

        for (Map<?, ?> item : items) {
            String enonce = String.valueOf(item.get("enonce"));
            String relationBonneReponse = String.valueOf(item.get("relationBonneReponse"));
            assertThat(RELATIONS_ADMISES)
                    .as("'%s' : relationBonneReponse '%s' n'est pas une des quatre relations de"
                            + " LIA-5", enonce, relationBonneReponse)
                    .contains(relationBonneReponse);

            @SuppressWarnings("unchecked")
            Map<String, String> relationsDistracteurs =
                    (Map<String, String>) item.get("relationsDistracteurs");
            List<?> distracteurs = (List<?>) item.get("distracteurs");
            assertThat(relationsDistracteurs.keySet())
                    .as("'%s' : relationsDistracteurs doit porter exactement les deux distracteurs"
                            + " de l'item", enonce)
                    .containsExactlyInAnyOrderElementsOf(
                            distracteurs.stream().map(String::valueOf).toList());

            Set<String> relations = new LinkedHashSet<>();
            relations.add(relationBonneReponse);
            for (String valeurDeclaree : relationsDistracteurs.values()) {
                String relation = relationDeclaree(valeurDeclaree);
                assertThat(RELATIONS_ADMISES)
                        .as("'%s' : la relation declaree '%s' n'est pas une des quatre relations de"
                                + " LIA-5", enonce, relation)
                        .contains(relation);
                relations.add(relation);
            }
            assertThat(relations)
                    .as("'%s' : les trois relations (bonne reponse et deux distracteurs) doivent"
                            + " etre deux a deux distinctes (LIA-4)", enonce)
                    .hasSize(3);
        }
    }

    /**
     * LIA-5 : chacune des quatre relations est la relation de la bonne reponse sur au moins
     * arrondi-inferieur(N/6) items du paquet, N etant recalcule sur le paquet reel plutot qu'ecrit
     * en dur, pour que cette clause reste correcte si le paquet grandit.
     */
    @Test
    void chacuneDesQuatreRelationsEstServieAuMoinsUnSixiemeDuPaquet() {
        List<Map<?, ?>> items = itemsEcrits();
        int plancher = Math.floorDiv(items.size(), 6);

        Map<String, Integer> occurrences = new java.util.HashMap<>();
        for (Map<?, ?> item : items) {
            String relation = String.valueOf(item.get("relationBonneReponse"));
            occurrences.merge(relation, 1, Integer::sum);
        }

        for (String relation : RELATIONS_ADMISES) {
            assertThat(occurrences.getOrDefault(relation, 0))
                    .as("LIA-5 : la relation '%s' doit etre la relation de la bonne reponse sur au"
                            + " moins %d items sur %d", relation, plancher, items.size())
                    .isGreaterThanOrEqualTo(plancher);
        }
    }

    /** LIA-9 : aucun mot de liaison n'est la bonne reponse de plus de six items. */
    @Test
    void aucunMotDeLiaisonNEstLaBonneReponseDePlusDeSixItems() {
        Map<String, Integer> occurrences = new java.util.HashMap<>();
        for (Question item : tousLesItems(paquet())) {
            occurrences.merge(item.bonneReponse(), 1, Integer::sum);
        }
        occurrences.forEach((mot, compte) -> assertThat(compte)
                .as("LIA-9 : '%s' est la bonne reponse de %d items, au plus %d autorises", mot,
                        compte, LIA_9_PLAFOND_PAR_MOT)
                .isLessThanOrEqualTo(LIA_9_PLAFOND_PAR_MOT));
    }

    /** Les tokens separes par une espace d'une chaine, une fois la ponctuation de fin retiree. */
    private static List<String> motsDe(String valeur) {
        String sansPonctuationDeFin = valeur.replaceAll("[.,?!;:]", "").strip();
        if (sansPonctuationDeFin.isEmpty()) {
            return List.of();
        }
        return List.of(sansPonctuationDeFin.split("\\s+"));
    }

    /** Le nombre de lettres d'un mot, l'apostrophe n'etant pas une lettre et donc jamais comptee. */
    private static long nombreDeLettres(String mot) {
        return mot.chars().filter(Character::isLetter).count();
    }

    /** LIA-6 : au plus 35 mots au total sur les phrases de l'item (le champ `enonce`). */
    @Test
    void chaqueEnonceTientEnAuPlusTrenteCinqMots() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(motsDe(item.enonce()))
                    .as("'%s' : au plus %d mots (LIA-6)", item.enonce(), LIA_6_PLAFOND_MOTS)
                    .hasSizeLessThanOrEqualTo(LIA_6_PLAFOND_MOTS);
        }
    }

    /**
     * LIA-7 : aucun mot de l'item ne depasse onze lettres. Perimetre mesure par nadia-prof sur
     * enonce, indice (et texteSupport/explication quand ils existent, absents ici) : ce paquet
     * verifie donc l'enonce, l'indice, la bonne reponse et les distracteurs.
     */
    @Test
    void aucunMotDeLItemNeDepasseOnzeLettres() {
        for (Question item : tousLesItems(paquet())) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            propositions.add(item.enonce());
            propositions.add(item.indice().orElseThrow());
            for (String champ : propositions) {
                for (String mot : motsDe(champ)) {
                    assertThat(nombreDeLettres(mot))
                            .as("'%s' : le mot '%s' depasse onze lettres (LIA-7)", champ, mot)
                            .isLessThanOrEqualTo(LIA_7_PLAFOND_LETTRES);
                }
            }
        }
    }
}
