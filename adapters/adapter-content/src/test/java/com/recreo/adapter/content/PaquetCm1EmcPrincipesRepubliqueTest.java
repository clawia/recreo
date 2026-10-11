package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>S55-BR-EMC-CM1-06, le paquet {@code cm1-emc-principes-republique}, ligne {@code EMC-CM1-06} de
 * l'enseignement moral et civique (CM1).</b> Referentiel :
 * {@code squad/programme/enseignement-moral-et-civique/competences-cm1.md}, ligne 6. Conditions :
 * l'en-tete du paquet, {@code squad/conformite/ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md}
 * (P3, P4) et {@code squad/conformite/ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md}, section 3
 * (items 6 et 8 corriges dans le commit de branchement).
 *
 * <p><b>Deux taches (FER-3)</b>, lues sur la forme de la question : nommer le principe qu'illustre
 * une situation, dire le sens d'un principe.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-4}, {@code EMC-5} sur les items 1 a 5,
 * {@code BIA-5}, l'exactitude des sources de l'en-tete, le vocabulaire a l'ecran et la regle de s53
 * section 4.1 (un indice ne redit pas la bonne reponse de son item, P4) demandent une lecture :
 * garde de {@code nadia-prof}, en jouant. Ne sont gardes que ce que son arbitrage a refuse mot pour
 * mot : "la liberte" parmi les distracteurs de l'item 4 (P3) et les deux textes des items 6 et 8.</p>
 *
 * <p>ADR-0013 obligation 3 : chaque verificateur est mis a l'epreuve par un jeu fautif commite
 * ({@code src/test/resources/contreexemple/PaquetCm1EmcPrincipesRepubliqueTest}).</p>
 */
class PaquetCm1EmcPrincipesRepubliqueTest {

    private static final String PAQUET = "cm1-emc-principes-republique";

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCm1EmcPrincipesRepubliqueTest";

    private static final String PAQUET_FAUTIF = "cm1-emc-principes-republique-fautif";

    /** FER-3 : la tache la moins servie porte au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    private static final String TACHE_NOMMER = "Quel principe de la République";

    private static final List<String> TACHE_SENS = List.of("Que veut dire", "Que garantit", "Jusqu'où va");

    /** FER-1 : les cinq principes de la liste du referentiel, chacun en bonne reponse d'un item. */
    private static final Set<String> PRINCIPES =
            Set.of("la liberté", "l'égalité", "la fraternité", "la laïcité", "le refus des discriminations");

    /** P3 : l'item 4 se reconnait a son enonce ; "la liberte" sort de ses distracteurs. */
    private static final String ENONCE_DE_L_ITEM_4 = "ne favorise aucune religion";

    private static final String DISTRACTEUR_REFUSE_A_L_ITEM_4 = "la liberté";

    /**
     * Textes refuses par nadia-prof (arbitrage prealable de s55, instance B, sections 3.1 et 3.2),
     * chacun avec son motif. Un synonyme de ces textes n'est pas garde : il reste a sa lecture.
     */
    private static final Map<String, String> TEXTES_REFUSES = new LinkedHashMap<>();

    static {
        TEXTES_REFUSES.put("jusqu'à ce qu'un adulte la refuse",
                "item 6 : vrai pour un enfant de neuf ans, que ses parents et son enseignant limitent (EMC-5)");
        TEXTES_REFUSES.put("s'entraider entre citoyens",
                "item 8 : la fraternite n'est pas reservee aux citoyens (decision 2018-717/718 QPC, HIS-3)");
        TEXTES_REFUSES.put("se soutiennent les uns les autres",
                "item 3 : glose fausse, ces eleves aident d'autres enfants et ne s'entraident pas entre eux"
                        + " (EMC-CM1-R9, s55)");
    }

    /** EMC-1, EMC-8 : aucune adresse a l'enfant, en mot entier. */
    private static final Pattern ADRESSE_A_L_ENFANT = Pattern.compile(
            "\\b(tu|ton|ta|tes|toi|te|vous|votre|vos)\\b|\\bt['\\u2019]",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);

    private static List<Question> tousLesItems(ContentSource contenu) {
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

    private static List<Question> items() {
        return tousLesItems(new YamlContentPackLoader("").chargerPaquet(PAQUET));
    }

    private static List<Question> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH).chargerPaquet(PAQUET_FAUTIF));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static List<String> textesLusParLEnfant(Question item) {
        List<String> textes = new ArrayList<>();
        textes.add(item.enonce());
        textes.addAll(propositions(item));
        item.indice().ifPresent(textes::add);
        item.explication().ifPresent(textes::add);
        return textes;
    }

    private static List<Question> itemsDeTache(List<Question> items, List<String> formes) {
        return items.stream().filter(item -> formes.stream().anyMatch(item.enonce()::contains)).toList();
    }

    /** FER-3 : deux taches, chaque item dans exactement une, la moins servie a au moins trois items. */
    private static void verifierLesDeuxTaches(List<Question> items) {
        int nommer = itemsDeTache(items, List.of(TACHE_NOMMER)).size();
        int sens = itemsDeTache(items, TACHE_SENS).size();
        assertThat(nommer + sens)
                .as("FER-3 : chaque item appartient à une seule tâche (%d + %d sur %d items)",
                        nommer, sens, items.size())
                .isEqualTo(items.size());
        assertThat(Math.min(nommer, sens))
                .as("FER-3 : la tâche la moins servie porte au moins %d items (%d, %d)",
                        MINIMUM_PAR_TACHE, nommer, sens)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** FER-1 : chacun des cinq principes est la bonne reponse d'un item de la tache "nommer". */
    private static void verifierLesCinqPrincipes(List<Question> items) {
        Set<String> nommes = new HashSet<>();
        for (Question item : itemsDeTache(items, List.of(TACHE_NOMMER))) {
            nommes.add(item.bonneReponse());
        }
        assertThat(nommes).as("FER-1 : les cinq principes sont chacun en bonne réponse")
                .containsAll(PRINCIPES);
    }

    /** P3, s53 section 3 : "la liberte" sort des distracteurs de l'item 4, un item et un seul. */
    private static void verifierQueLaLiberteNEstPasUnDistracteurDeLItem4(List<Question> items) {
        List<Question> item4 = items.stream()
                .filter(item -> item.enonce().contains(ENONCE_DE_L_ITEM_4)).toList();
        assertThat(item4).as("P3 : un seul item porte l'énoncé « %s »", ENONCE_DE_L_ITEM_4).hasSize(1);
        assertThat(item4.get(0).distracteurs())
                .as("P3 : « %s » est le principe voisin que l'énoncé permet aussi de nommer (EMC-5)",
                        DISTRACTEUR_REFUSE_A_L_ITEM_4)
                .noneMatch(distracteur -> distracteur.contains(DISTRACTEUR_REFUSE_A_L_ITEM_4));
    }

    private static void verifierQueLeTexteRefuseNeRevientPas(List<Question> items, String refuse, String motif) {
        for (Question item : items) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(texte)
                        .as("texte refusé par nadia-prof, %s", motif)
                        .doesNotContain(refuse);
            }
        }
    }

    private static void verifierQueAucunTexteNAdresseLEnfant(List<Question> items) {
        for (Question item : items) {
            for (String texte : textesLusParLEnfant(item)) {
                assertThat(ADRESSE_A_L_ENFANT.matcher(texte).find())
                        .as("texte '%s' : EMC-1 et EMC-8 interdisent d'adresser l'enfant", texte)
                        .isFalse();
            }
        }
    }

    @Test
    void laCompetenceEstLeLibelleDeEmcCm106() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        assertThat(contenu.descriptor().niveau()).isEqualTo("CM1");
        assertThat(contenu.descriptor().competence())
                .isEqualTo("Comprendre le sens des principes de la République : liberté, égalité,"
                        + " fraternité, laïcité, refus des discriminations.");
    }

    @Test
    void fer2OnzeItemsDeuxDistracteursEtTroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("FER-2 : onze items").hasSize(11);
        for (Question item : items) {
            assertThat(item.distracteurs()).as("item '%s' : deux distracteurs", item.enonce()).hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("item '%s' : trois propositions distinctes", item.enonce()).hasSize(3);
        }
    }

    @Test
    void fer3LesDeuxTachesPortentChacuneAuMoinsTroisItems() {
        verifierLesDeuxTaches(items());
    }

    @Test
    void fer1LesCinqPrincipesSontChacunEnBonneReponse() {
        verifierLesCinqPrincipes(items());
    }

    @Test
    void p3LaLiberteNEstPasUnDistracteurDeLItemDeLaLaicite() {
        verifierQueLaLiberteNEstPasUnDistracteurDeLItem4(items());
    }

    @Test
    void aucunTexteRefuseParNadiaProfNeRevient() {
        List<Question> items = items();
        TEXTES_REFUSES.forEach((refuse, motif) -> verifierQueLeTexteRefuseNeRevientPas(items, refuse, motif));
    }

    /**
     * EMC-CM1-R9 : l'indice de l'item 3, mot pour mot ({@code FICHE-bandit-manchot-principes-republique-
     * cm1.md}, section 5). Il decrit ce que font les eleves de l'enonce, sans nommer le principe.
     */
    @Test
    void lIndiceDeLItem3EstCeluiDeLaFicheDeNadiaProf() {
        List<String> indices = items().stream().map(item -> item.indice().orElse("")).toList();

        assertThat(indices).contains("Ces élèves donnent pour aider d'autres enfants.");
    }

    /**
     * EMC-CM1-R9, l'en-tete : la citation de la decision de 2018 ne vise que ce que le Conseil
     * constitutionnel a dit, une liberte d'aider autrui dans un but humanitaire. Un commentaire n'est lu
     * par personne, mais HIS-3 porte sur l'exactitude des sources.
     */
    @Test
    void lEnTeteCiteLaDecisionDe2018DansSonPerimetreExact() throws java.io.IOException {
        try (java.io.InputStream flux = getClass().getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            assertThat(flux).isNotNull();
            String paquet = new String(flux.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);

            assertThat(paquet).contains("dans un but humanitaire");
        }
    }

    @Test
    void emc1Emc8AucunTexteNAdresseLEnfantALaDeuxiemePersonne() {
        verifierQueAucunTexteNAdresseLEnfant(items());
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSesDeuxTaches() {
        assertThatThrownBy(() -> verifierLesDeuxTaches(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSesCinqPrincipes() {
        assertThatThrownBy(() -> verifierLesCinqPrincipes(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLaLiberteDeLItem4() {
        assertThatThrownBy(() -> verifierQueLaLiberteNEstPasUnDistracteurDeLItem4(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("P3");
    }

    @Test
    void leJeuFautifCommiteContientChaqueTexteRefuseEtChacunEstRefuse() {
        List<Question> fautifs = itemsFautifs();
        TEXTES_REFUSES.forEach((refuse, motif) -> assertThatThrownBy(
                () -> verifierQueLeTexteRefuseNeRevientPas(fautifs, refuse, motif))
                .as("le texte refusé '%s' doit être détecté", refuse)
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("refusé par nadia-prof"));
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSonAdresseALEnfant() {
        assertThatThrownBy(() -> verifierQueAucunTexteNAdresseLEnfant(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-1");
    }

    @Test
    void lElisionDeTuEstReconnueAvecLApostropheDroiteEtLApostropheTypographique() {
        assertThat(ADRESSE_A_L_ENFANT.matcher("Quand t'appelle-t-on ?").find()).isTrue();
        assertThat(ADRESSE_A_L_ENFANT.matcher("Quand t\u2019appelle-t-on ?").find()).isTrue();
    }
}
