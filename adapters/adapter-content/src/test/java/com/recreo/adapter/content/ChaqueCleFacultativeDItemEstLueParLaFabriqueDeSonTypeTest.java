package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0080, garde-fou 6 : ferme le trou qu'ADR-0070 nomme dans sa propre Javadoc - "ce que ce
 * garde-fou ne prouve pas [...] : qu'une cle citee quelque part soit lue au bon endroit. Un
 * litteral pose dans un test qui n'ouvre aucun paquet le satisferait." Une cle facultative lue par
 * deux fabriques sur trois est aujourd'hui verte partout et perdue a l'execution : le paquet
 * compile, {@code AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest} reste vert parce que le nom
 * de la cle figure ailleurs dans le depot, et l'enfant ne recoit jamais la valeur que son auteur
 * avait ecrite.
 *
 * <p><b>Ce qu'est une cle facultative, ici, et pourquoi ce n'est pas une liste ecrite a la main.</b>
 * Une composante d'un item de type {@code Optional<String>} - {@code indice}, {@code explication},
 * {@code texteSupport}, {@code illustration} et {@code illustrationTexteAlternatif} (sur
 * {@link Question} seulement - elles sont obligatoires sur un item de designation, ADR-0080),
 * {@code partDesignee}, {@code texteAVoixHaute}. Le critere est <b>derive par reflexion</b> sur la
 * forme reelle de l'item charge (ADR-0013 obligation 1) : une composante facultative de plus, sur
 * n'importe quelle forme d'item, entre dans ce garde-fou sans qu'une ligne y change.</p>
 *
 * <p><b>Ce que ce garde-fou verifie</b> : pour chaque item d'un paquet enumere qui declare une
 * valeur non nulle sous une cle correspondant a une composante facultative de la forme d'item
 * chargee, la valeur chargee est presente et identique a la valeur ecrite. Une cle declaree et
 * perdue (composante chargee vide) ou alteree (valeur differente) est un defaut.</p>
 *
 * <p><b>Ce qu'il ne verifie pas</b>, dit pour ne pas le faire passer pour plus qu'il n'est : les
 * composantes facultatives d'un autre type que {@code Optional<String>} - {@code exigenceDeForme}
 * (un enum) suit une traduction texte vers enum que ce garde-fou ne rejoue pas - et les cles
 * imbriquees sous une liste (les champs d'une {@code Zone} de designation), qui ne sont pas des
 * composantes de l'item lui-meme.</p>
 *
 * <p><b>Pourquoi la mise a l'epreuve ne passe pas par un paquet servi.</b> Les trois fabriques du
 * depot lisent aujourd'hui correctement toutes leurs cles facultatives - c'est un fait constate, pas
 * suppose, et c'est exactement pour qu'il le reste que ce garde-fou existe. Le defaut qu'il garde -
 * une fabrique qui ignore une cle facultative - ne peut donc pas etre reproduit avec les fabriques
 * reelles sur un fichier reel sans introduire volontairement la regression dans le code de
 * production. La comparaison qui detecte le defaut est donc mise a l'epreuve directement, sur un
 * item brut et un item charge construits a la main - la meme relation exacte qu'une fabrique fautive
 * produirait, sans en ecrire une.</p>
 *
 * <p>ADR-0013, les trois obligations : perimetre derive - les paquets viennent du balayage de
 * {@code contenu/}, comme {@link AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest#paquets} ;
 * derivation totale - chaque item de chaque paquet enumere est examine, un paquet combinatoire (sans
 * cle {@code items}) etant hors perimetre par construction, au meme titre que pour ADR-0032 ; mise a
 * l'epreuve - sur la comparaison elle-meme, pour le motif ci-dessus.</p>
 */
class ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /**
     * ADR-0013 : le jeu fautif de ce garde-fou. Chaque sous-repertoire porte un unique item.yaml -
     * l'item brut declare, tel qu'un auteur l'ecrirait - lu par ce test exactement comme
     * {@code AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest} lit un paquet fautif. L'item
     * <b>charge</b>, lui, est construit a la main dans chaque test : aucune fabrique reelle ne
     * produit aujourd'hui la perte ou l'alteration que ce garde-fou garde (voir la Javadoc de
     * classe), donc rien ne le simulerait fidelement en le faisant passer par le chargeur reel.
     */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest");

    private static final int BUDGET_DE_TIRAGES = 500;

    @SuppressWarnings("unchecked")
    private static Map<?, ?> itemBrutDeFixture(String sousRepertoire) {
        Object charge = new Yaml().load(lire(CONTRE_EXEMPLE.resolve(sousRepertoire).resolve("item.yaml")));
        return (Map<?, ?>) charge;
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Tous les items charges par la chaine reelle, indexes par identifiant - meme methode que ADR-0032. */
    private static Map<String, Item> itemsCharges(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        Map<String, Item> items = new HashMap<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                break;
            }
            Item item = tire.orElseThrow();
            items.put(item.id().value(), item);
            dejaVus.add(item.id());
        }
        return items;
    }

    /** La composante facultative de meme nom que la cle, si la forme de l'item chargee en porte une. */
    private static Optional<RecordComponent> composantFacultatif(Item item, String cle) {
        for (RecordComponent composant : item.getClass().getRecordComponents()) {
            if (composant.getName().equals(cle) && composant.getType().equals(Optional.class)) {
                return Optional.of(composant);
            }
        }
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    private static Optional<String> valeurChargee(Item item, RecordComponent composant) {
        try {
            return (Optional<String>) composant.getAccessor().invoke(item);
        } catch (IllegalAccessException | InvocationTargetException echec) {
            throw new IllegalStateException(
                    "impossible de lire " + composant.getName() + " sur " + item.getClass(), echec);
        }
    }

    /**
     * La comparaison elle-meme, testable independamment de tout fichier : pour chaque cle de
     * {@code itemBrut} qui correspond a une composante facultative de {@code itemCharge}, la valeur
     * chargee doit etre presente et identique a la valeur ecrite.
     */
    static List<String> defautsDeLItem(String paquetId, String itemId, Map<?, ?> itemBrut,
                                        Item itemCharge) {
        List<String> defauts = new ArrayList<>();
        for (Map.Entry<?, ?> entree : itemBrut.entrySet()) {
            String cle = String.valueOf(entree.getKey());
            Object valeurBrute = entree.getValue();
            if (valeurBrute == null) {
                continue;
            }
            Optional<RecordComponent> composant = composantFacultatif(itemCharge, cle);
            if (composant.isEmpty()) {
                continue;
            }
            Optional<String> chargee = valeurChargee(itemCharge, composant.orElseThrow());
            String bruteTexte = String.valueOf(valeurBrute);
            if (chargee.isEmpty()) {
                defauts.add("paquet " + paquetId + ", item " + itemId + " : la cle facultative '" + cle
                        + "' est ecrite dans le YAML (\"" + bruteTexte + "\") mais l'item charge par la"
                        + " chaine reelle ne la porte pas - la fabrique de ce type de paquet ne la lit"
                        + " pas");
            } else if (!chargee.orElseThrow().equals(bruteTexte)) {
                defauts.add("paquet " + paquetId + ", item " + itemId + " : la cle facultative '" + cle
                        + "' vaut \"" + bruteTexte + "\" dans le YAML et \"" + chargee.orElseThrow()
                        + "\" dans l'item charge");
            }
        }
        return defauts;
    }

    /** Le nombre de cles de {@code itemBrut} qui correspondent a une composante facultative. */
    private static long clesFacultativesDeLItem(Map<?, ?> itemBrut, Item itemCharge) {
        return itemBrut.entrySet().stream()
                .filter(entree -> entree.getValue() != null)
                .filter(entree -> composantFacultatif(itemCharge, String.valueOf(entree.getKey())).isPresent())
                .count();
    }

    static List<String> verifierQueChaqueCleFacultativeEstLue(Path racine, String racineClasspath) {
        List<String> paquets = AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        int paquetsEnumeresJuges = 0;
        long clesExaminees = 0;

        for (String paquetId : paquets) {
            Path fichier = racine.resolve("contenu").resolve(paquetId + ".yaml");
            Object charge = new Yaml().load(lire(fichier));
            if (!(charge instanceof Map<?, ?> corps) || !(corps.get("items") instanceof List<?> itemsBruts)) {
                continue;
            }
            paquetsEnumeresJuges++;
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquetId);
            Map<String, Item> itemsChargesParId = itemsCharges(contenu);

            int position = 0;
            for (Object itemBrut : itemsBruts) {
                String itemId = "item-" + position;
                if (itemBrut instanceof Map<?, ?> itemMap && itemsChargesParId.get(itemId) != null) {
                    Item itemCharge = itemsChargesParId.get(itemId);
                    clesExaminees += clesFacultativesDeLItem(itemMap, itemCharge);
                    defauts.addAll(defautsDeLItem(paquetId, itemId, itemMap, itemCharge));
                }
                position++;
            }
        }

        assertThat(paquetsEnumeresJuges)
                .as("ADR-0003 : ce garde-fou ne prouve rien si aucun paquet enumere de %s n'a ete juge -"
                        + " un paquet combinatoire (sans cle 'items') est hors perimetre par"
                        + " construction", racine)
                .isGreaterThan(0);
        assertThat(clesExaminees)
                .as("ADR-0051 : ce garde-fou ne prouve rien s'il n'a examine aucune cle facultative"
                        + " sous %s", racine)
                .isGreaterThan(0);
        return defauts;
    }

    private static void verifier(List<String> defauts) {
        assertThat(defauts)
                .as("ADR-0080 : une cle facultative ecrite dans un paquet servi se retrouve dans"
                        + " l'item charge par la chaine reelle")
                .isEmpty();
    }

    @Test
    void chaqueCleFacultativeDUnItemDeProductionEstLueParSaFabrique() {
        verifier(verifierQueChaqueCleFacultativeEstLue(CONTENU_DE_PRODUCTION, ""));
    }

    /**
     * ADR-0013 obligation 3 : la comparaison eprouvee directement, sur la forme exacte du defaut
     * qu'une fabrique fautive produirait - une cle facultative ecrite dans le YAML et jamais passee
     * au constructeur de l'item.
     */
    @Test
    void uneCleFacultativePerdueParLaFabriqueFaitEchouerLeGardeFou() {
        Map<?, ?> itemBrut = itemBrutDeFixture("cle-facultative-perdue");

        Question itemChargeSansIndice = new Question(new ItemId("item-0"), "Combien font 2 + 2 ?", "4",
                List.of("3", "5"));

        assertThatThrownBy(() -> verifier(
                defautsDeLItem("faux-paquet", "item-0", itemBrut, itemChargeSansIndice)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("indice")
                .hasMessageContaining("ne la porte pas");
    }

    /**
     * Le meme item brut, cette fois charge avec son indice : ne doit plus rien reprocher, sans quoi
     * "la cle est perdue" et "toute cle facultative est un defaut" seraient le meme rouge.
     */
    @Test
    void uneCleFacultativeCorrectementLuePasseLeGardeFou() {
        Map<?, ?> itemBrut = itemBrutDeFixture("cle-facultative-correcte");

        Question itemChargeAvecIndice = new Question(new ItemId("item-0"), "Combien font 2 + 2 ?", "4",
                List.of("3", "5"), Optional.of("Compte sur tes doigts."));

        verifier(defautsDeLItem("vrai-paquet", "item-0", itemBrut, itemChargeAvecIndice));
    }

    /**
     * ADR-0013 obligation 3 : l'autre forme du meme defaut - la cle est lue, mais alteree en chemin.
     */
    @Test
    void uneCleFacultativeAltereeParLaFabriqueFaitEchouerLeGardeFou() {
        Map<?, ?> itemBrut = itemBrutDeFixture("cle-facultative-alteree");

        Question itemChargeAvecExplicationAlteree = new Question(new ItemId("item-0"),
                "Combien font 2 + 2 ?", "4", List.of("3", "5"), Optional.empty(),
                Optional.of("Explication tronquee"));

        assertThatThrownBy(() -> verifier(defautsDeLItem("faux-paquet", "item-0", itemBrut,
                itemChargeAvecExplicationAlteree)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("explication")
                .hasMessageContaining("Deux plus deux egale quatre.")
                .hasMessageContaining("Explication tronquee");
    }

    /**
     * Une cle qui ne correspond a aucune composante facultative de la forme d'item chargee -
     * {@code distracteurs} est une liste, pas un {@code Optional<String>} - n'est jamais jugee ici :
     * elle est hors du perimetre de ce garde-fou, pas une omission.
     */
    @Test
    void uneCleQuiNEstPasUneComposanteFacultativeDeTexteEstIgnoree() {
        Map<?, ?> itemBrut = itemBrutDeFixture("cle-hors-perimetre");

        Question itemCharge = new Question(new ItemId("item-0"), "Combien font 2 + 2 ?", "4",
                List.of("3", "5"));

        verifier(defautsDeLItem("vrai-paquet", "item-0", itemBrut, itemCharge));
    }
}
