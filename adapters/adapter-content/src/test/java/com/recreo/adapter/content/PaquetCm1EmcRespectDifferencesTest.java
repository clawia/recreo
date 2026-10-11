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
 * <b>S55-BR-EMC-CM1-03, le paquet {@code cm1-emc-respect-differences}, ligne {@code EMC-CM1-03} de
 * l'enseignement moral et civique (CM1).</b> Referentiel :
 * {@code squad/programme/enseignement-moral-et-civique/competences-cm1.md}, ligne 3. Conditions :
 * l'en-tete du paquet et {@code squad/conformite/ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md},
 * section 2 (items 4, 7 et 8 corriges dans le commit de branchement).
 *
 * <p><b>Trois taches (FER-3)</b>, lues sur la forme de la question : reconnaitre le cas ou le respect
 * d'autrui n'est pas respecte, dire ce que fonde le respect des differences, reconnaitre le motif
 * qui peut valablement ecarter un eleve.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-4} sur les distracteurs, {@code EMC-5} (une seule
 * reponse defendable), la regle de s53 section 4.1 (un indice ne redit pas la bonne reponse de son
 * item), la lourdeur de la repetition de l'enonce des items 1 a 5 et {@code CYC-1} demandent une
 * lecture : garde de {@code nadia-prof}, en jouant. Ne sont gardes que les textes que son arbitrage
 * a refuses mot pour mot, et la forme de l'item 4.</p>
 *
 * <p>ADR-0013 obligation 3 : chaque verificateur est mis a l'epreuve par un jeu fautif commite
 * ({@code src/test/resources/contreexemple/PaquetCm1EmcRespectDifferencesTest}).</p>
 */
class PaquetCm1EmcRespectDifferencesTest {

    private static final String PAQUET = "cm1-emc-respect-differences";

    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCm1EmcRespectDifferencesTest";

    private static final String PAQUET_FAUTIF = "cm1-emc-respect-differences-fautif";

    private static final int NOMBRE_DE_MANCHES = 10;

    /** FER-3 : la tache la moins servie porte au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    private static final String TACHE_CAS = "n'est-il pas respecté";

    private static final List<String> TACHE_SENS = List.of("Que veut dire", "Quelle phrase", "Pourquoi");

    private static final String TACHE_MOTIF = "peut justifier";

    /** FER-1 : les quatre termes de la parenthese du libelle, chacun en bonne reponse. */
    private static final Map<String, String> TERMES_DU_LIBELLE = new LinkedHashMap<>();

    static {
        TERMES_DU_LIBELLE.put("apparence", "vêtements|plus petit");
        TERMES_DU_LIBELLE.put("origine", "autre pays");
        TERMES_DU_LIBELLE.put("convictions", "croyances");
        TERMES_DU_LIBELLE.put("situation de handicap", "fauteuil roulant");
    }

    /**
     * Textes refuses par nadia-prof (arbitrage prealable de s55, instance B, sections 2.1 a 2.3),
     * chacun avec son motif. Un synonyme de ces textes n'est pas garde : il reste a sa lecture.
     */
    private static final Map<String, String> TEXTES_REFUSES = new LinkedHashMap<>();

    static {
        TEXTES_REFUSES.put("jeu de relais",
                "item 4 : le relais, un jeu de vitesse, ouvre la lecture \"raison du jeu\" (EMC-5)");
        TEXTES_REFUSES.put("et que les autres refusent de l'attendre",
                "item 4 : suppose qu'une enfant en fauteuil est plus lente, une capacite attribuee"
                        + " a une categorie (EMC-4)");
        TEXTES_REFUSES.put("On peut penser autrement sans manquer de respect.",
                "item 7 : l'indice redit la bonne reponse (s53, section 4.1)");
        TEXTES_REFUSES.put("parce que les différences disparaissent avec le temps",
                "item 8 : un distracteur que personne ne croit (PLA-1)");
        TEXTES_REFUSES.put("Cela concerne la valeur et les droits de chaque personne.",
                "item 8 : l'indice redit la bonne reponse (s53, section 4.1)");
        TEXTES_REFUSES.put("Cherche le motif qui parle du jeu.",
                "item 10 : l'indice designe la seule proposition qui contient son mot (EMC-CM1-R8, s55)");
        TEXTES_REFUSES.put("Cherche le motif qui parle de ce qui s'est déjà passé.",
                "item 11 : l'indice designe la seule proposition qui contient son mot (EMC-CM1-R8, s55)");
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

    /** FER-3 : trois taches, chaque item dans exactement une, la moins servie a au moins trois items. */
    private static void verifierLesTroisTaches(List<Question> items) {
        int cas = itemsDeTache(items, List.of(TACHE_CAS)).size();
        int sens = itemsDeTache(items, TACHE_SENS).size();
        int motif = itemsDeTache(items, List.of(TACHE_MOTIF)).size();
        assertThat(cas + sens + motif)
                .as("FER-3 : chaque item appartient à une seule tâche (%d + %d + %d sur %d items)",
                        cas, sens, motif, items.size())
                .isEqualTo(items.size());
        assertThat(Math.min(cas, Math.min(sens, motif)))
                .as("FER-3 : la tâche la moins servie porte au moins %d items (%d, %d, %d)",
                        MINIMUM_PAR_TACHE, cas, sens, motif)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** FER-1 : chaque terme de la parenthese du libelle est la bonne reponse d'un item de la tache des cas. */
    private static void verifierLesQuatreTermes(List<Question> items) {
        for (Map.Entry<String, String> terme : TERMES_DU_LIBELLE.entrySet()) {
            Pattern marque = Pattern.compile(terme.getValue());
            assertThat(itemsDeTache(items, List.of(TACHE_CAS)).stream()
                    .anyMatch(item -> marque.matcher(item.bonneReponse()).find()))
                    .as("FER-1 : le terme '%s' est la bonne reponse d'un item", terme.getKey())
                    .isTrue();
        }
    }

    /**
     * EMC-4, forme de l'item 4 : la proposition qui nomme le fauteuil roulant ne dit rien de plus que
     * le motif (le handicap seul), jamais ce que l'enfant peut ou ne peut pas faire.
     */
    private static void verifierLaFormeDeLItemDuFauteuil(List<Question> items) {
        List<String> propositionsDuFauteuil = items.stream().flatMap(item -> propositions(item).stream())
                .filter(proposition -> proposition.contains("fauteuil roulant")).toList();
        assertThat(propositionsDuFauteuil).as("une proposition nomme le fauteuil roulant").isNotEmpty();
        for (String proposition : propositionsDuFauteuil) {
            assertThat(proposition)
                    .as("EMC-4 : la proposition ne dit rien après « fauteuil roulant » - '%s'", proposition)
                    .endsWith("fauteuil roulant");
        }
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
    void laCompetenceEstLeLibelleDeEmcCm103() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        assertThat(contenu.descriptor().niveau()).isEqualTo("CM1");
        assertThat(contenu.descriptor().competence())
                .isEqualTo("Identifier ce qu'interdit le respect d'autrui dans ses différences (apparence,"
                        + " origine, convictions, situation de handicap).");
    }

    @Test
    void fer2OnzeItemsDeuxDistracteursEtTroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("FER-2 : onze items, plus que nombreDeManches (%d)", NOMBRE_DE_MANCHES)
                .hasSize(11);
        for (Question item : items) {
            assertThat(item.distracteurs()).as("item '%s' : deux distracteurs", item.enonce()).hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("item '%s' : trois propositions distinctes", item.enonce()).hasSize(3);
        }
    }

    @Test
    void fer3LesTroisTachesPortentChacuneAuMoinsTroisItems() {
        verifierLesTroisTaches(items());
    }

    @Test
    void fer1LesQuatreTermesDuLibelleSontChacunEnBonneReponse() {
        verifierLesQuatreTermes(items());
    }

    @Test
    void emc4LaPropositionDuFauteuilRoulantNeDitQueLeHandicap() {
        verifierLaFormeDeLItemDuFauteuil(items());
    }

    @Test
    void aucunTexteRefuseParNadiaProfNeRevient() {
        List<Question> items = items();
        TEXTES_REFUSES.forEach((refuse, motif) -> verifierQueLeTexteRefuseNeRevientPas(items, refuse, motif));
    }

    /**
     * EMC-CM1-R8 : les deux indices de la fiche, mot pour mot ({@code FICHE-bandit-manchot-respect-
     * differences-cm1.md}, section 5). Chacun dit ce qu'est un motif valable sans contenir un mot propre
     * a la bonne reponse de son item.
     */
    @Test
    void lesIndicesDesItems10Et11SontCeuxDeLaFicheDeNadiaProf() {
        List<String> indices = items().stream().map(item -> item.indice().orElse("")).toList();

        assertThat(indices)
                .contains("Un motif valable ne parle pas de l'élève lui-même.")
                .contains("Un motif valable pourrait concerner n'importe quel élève.");
    }

    @Test
    void emc1Emc8AucunTexteNAdresseLEnfantALaDeuxiemePersonne() {
        verifierQueAucunTexteNAdresseLEnfant(items());
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSesTroisTaches() {
        assertThatThrownBy(() -> verifierLesTroisTaches(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSesQuatreTermes() {
        assertThatThrownBy(() -> verifierLesQuatreTermes(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLaPropositionDuFauteuilRoulant() {
        assertThatThrownBy(() -> verifierLaFormeDeLItemDuFauteuil(itemsFautifs()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-4");
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
