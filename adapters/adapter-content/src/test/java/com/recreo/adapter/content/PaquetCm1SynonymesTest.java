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
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S17-3 (DEC-0039, arbitrage prealable de nadia-prof du 2026-08-19, section 2) : le paquet
 * "cm1-synonymes". Matiere premiere reformulee, pas convertie
 * ({@code contenu-repris/associer/cm1-synonymes.yml}, 12 paires mot/synonyme sans phrase ni
 * distracteur).
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables (SYN-1, comptage, forme du
 * descripteur) : la justesse du sens de chaque synonyme et de chaque phrase pour un enfant de CM1
 * reste le jugement de nadia-prof (FICHE-bandit-manchot-synonymes-cm1.md), de meme que le critere
 * "aucun distracteur n'est reellement substituable" (SYN-2) et "aucun item ne presente deux mots
 * interchangeables que dans cette phrase precise" (SYN-3), qui exigent un jugement de sens qu'aucun
 * automate de ce depot ne porte.</p>
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, une seule graphie de matiere) sont
 * deja derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 *
 * <p><b>ADR-0052 : le paquet est passe a la forme validee sur l'item pilote.</b> La clause
 * invariante ("Dans cette phrase, quel mot peut remplacer X sans changer le sens ?") a quitte
 * l'enonce pour la consigne, generique, de l'entree de catalogue ("bandit-manchot-synonymes-cm1").
 * L'item pilote ("triste"/"malheureux", le plus long du paquet a l'ouverture, 132 caracteres) a
 * d'abord ete transforme seul et mesure par nadia-prof - VALIDE (gain net de 162 px, marge de
 * 2,81 px au pli) - avant que la forme ne soit etendue au reste du paquet.</p>
 *
 * <p><b>Le paquet compte 16 items, pas 20 (camille-po, 2026-08-20).</b> Quatre items dont
 * l'etiquette de la bonne reponse fait passer les propositions a trois rangees plutot que deux -
 * "vite" (rapidement), "ancien" (vieux), "poli" (courtois), "drole" (amusante) - sortent du paquet
 * plutot que d'etre reecrits : repasser leurs etiquettes par SYN-2/SYN-3 comme du contenu neuf,
 * sans garantie qu'un synonyme aussi juste et plus court existe, pour preserver un compte qui est
 * un critere de checklist d'ouverture et non une condition pedagogique - SYN-1 a SYN-9 ne
 * mentionnent aucun nombre d'items - n'est pas justifie. ADR-0023 (plus d'items que de manches)
 * reste largement tenu, 16 contre 10.</p>
 *
 * <p><b>SYN-9 (bloquante, datee du 2026-08-20)</b> : la mesure de nadia-prof a trouve une ligne qui
 * se coupe entre le guillemet ouvrant et le mot, sur l'item pilote lui-meme - les espaces a
 * l'interieur des guillemets etaient ordinaires (U+0020), pas insecables. Corrige sur les seize
 * items : chaque mot cible est entoure de deux espaces insecables (U+00A0), invisibles a la
 * lecture mais verifiees ci-dessous par leur code point, pas par leur rendu.</p>
 *
 * <p><b>SYN-8</b> : aucun mot de phrase n'a change sur les quinze items reecrits dans ce lot -
 * seule la clause invariante est retiree et la marque du mot cible passee du guillemet droit
 * repete au guillemet francais avec espaces insecables. Preuve par egalite stricte
 * ({@link #lesSeizeEnoncesSontExactementCeuxAttendus()}).</p>
 */
class PaquetCm1SynonymesTest {

    private static final String PAQUET = "cm1-synonymes";

    private static final String ID_DE_CATALOGUE = "bandit-manchot-synonymes-cm1";

    /**
     * Le compte est garde exactement, pas seulement atteint : camille-po a retire quatre items
     * (2026-08-20) precisement pour ne plus le laisser gonfler comme un critere en soi. Une
     * regression qui ajouterait ou retirerait un item sans toucher ce fichier doit rougir ici.
     */
    private static final int NOMBRE_D_ITEMS_ATTENDU = 16;

    /**
     * SYN-6, SYN-9 : le mot cible est marque par des guillemets francais, avec un espace insecable
     * (U+00A0, jamais l'espace ordinaire U+0020) immediatement a l'interieur de chaque guillemet -
     * sans lui, une ligne peut se couper entre le guillemet et le mot, ce que nadia-prof a mesure
     * sur l'item pilote avant cette correction.
     */
    private static final Pattern MOT_ENTRE_GUILLEMETS_FRANCAIS =
            Pattern.compile("«\u00A0([^»]+?)\u00A0»");

    private static final String CLAUSE_INVARIANTE = "Dans cette phrase, quel mot peut remplacer";

    /**
     * SYN-8, preuve par egalite stricte : les seize enonces exacts apres le passage a la forme
     * validee, generes depuis le paquet servi et non ecrits a la main, pour qu'aucune transcription
     * ne puisse introduire l'ecart qu'elle est censee prouver absent. Chaque enonce porte un espace
     * insecable (U+00A0) immediatement a l'interieur de chaque guillemet francais - invisible dans
     * ce fichier comme dans le paquet, verifie par code point ailleurs dans cette classe, jamais par
     * une relecture visuelle.
     */
    private static final List<Question> SEIZE_ITEMS_ATTENDUS = List.of(
            new Question(new ItemId("item-0"), "Léa est « contente » de son cadeau.", "heureuse", List.of("inquiète", "surprise"),
                    Optional.of("Ce mot décrit une émotion joyeuse, comme quand on sourit parce que tout va bien.")),
            new Question(new ItemId("item-1"), "Ce paysage de montagne est « beau ».", "joli", List.of("vaste", "aride"),
                    Optional.of("Ce mot dit qu'une chose est agréable à regarder.")),
            new Question(new ItemId("item-2"), "Le petit garçon est « triste » depuis le départ de son ami.", "malheureux", List.of("inquiet", "gêné"),
                    Optional.of("Ce mot décrit ce que l'on ressent quand on a envie de pleurer.")),
            new Question(new ItemId("item-3"), "Le village est très « calme » le dimanche matin.", "tranquille", List.of("bruyant", "animé"),
                    Optional.of("Ce mot dit qu'il n'y a ni bruit ni agitation autour de soi.")),
            new Question(new ItemId("item-4"), "Après la course, Tom est « fatigué ».", "épuisé", List.of("content", "inquiet"),
                    Optional.of("Ce mot dit qu'on n'a plus du tout de force.")),
            new Question(new ItemId("item-5"), "Le pompier reste « courageux » face au danger.", "brave", List.of("maladroit", "discret"),
                    Optional.of("Ce mot dit qu'on n'a pas peur d'affronter une difficulté.")),
            new Question(new ItemId("item-6"), "Dans les fables, le renard est « malin ».", "rusé", List.of("gentil", "peureux"),
                    Optional.of("Ce mot dit qu'on trouve toujours une astuce pour se sortir d'une situation.")),
            new Question(new ItemId("item-7"), "Ce moteur de camion est très « fort ».", "puissant", List.of("léger", "bruyant"),
                    Optional.of("Ce mot dit qu'une chose a beaucoup de force ou d'énergie.")),
            new Question(new ItemId("item-8"), "La maman parle à son bébé d'une voix « douce ».", "tendre", List.of("forte", "grave"),
                    Optional.of("Ce mot décrit une voix pleine d'affection, comme celle qui rassure quelqu'un.")),
            new Question(new ItemId("item-9"), "Ce désert de sable est très « grand ».", "immense", List.of("chaud", "vide"),
                    Optional.of("Ce mot dit qu'un espace semble sans limite, à perte de vue.")),
            new Question(new ItemId("item-10"), "Cette fourmi est « petite ».", "minuscule", List.of("rapide", "noire"),
                    Optional.of("Ce mot dit qu'une chose est extrêmement petite, presque impossible à voir.")),
            new Question(new ItemId("item-11"), "Le nouveau voisin est « gentil » avec tout le monde.", "aimable", List.of("célèbre", "élégant"),
                    Optional.of("Ce mot décrit quelqu'un qui se comporte bien avec les autres, poliment et avec bonté.")),
            new Question(new ItemId("item-12"), "Cet exercice de mathématiques est « difficile ».", "compliqué", List.of("amusant", "court"),
                    Optional.of("Ce mot dit qu'une chose est dure à comprendre ou à réussir.")),
            new Question(new ItemId("item-13"), "Ce jeu de société est « facile » pour les débutants.", "simple", List.of("dangereux", "coûteux"),
                    Optional.of("Ce mot dit qu'une chose ne demande pas beaucoup d'effort pour être réussie.")),
            new Question(new ItemId("item-14"), "La musique du carnaval est « gaie ».", "joyeuse", List.of("lente", "grave"),
                    Optional.of("Ce mot dit qu'une musique donne envie de sourire et de danser.")),
            new Question(new ItemId("item-15"), "Le bureau de Léa est « propre ».", "net", List.of("encombré", "sombre"),
                    Optional.of("Ce mot dit qu'un endroit est bien rangé et sans saleté.")));

    @Test
    void lePaquetCompteExactementSeizeItems() {
        assertThat(tousLesItems(paquet()))
                .as("le compte est garde exactement a %d, pas seulement atteint (camille-po,"
                        + " 2026-08-20) : vite, ancien, poli et drole sont sortis du paquet plutot"
                        + " que reecrits", NOMBRE_D_ITEMS_ATTENDU)
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void lesQuatreItemsATroisRangeesNeSontPlusDansLePaquet() {
        Set<String> bonnesReponsesRetirees = Set.of("rapidement", "vieux", "courtois", "amusante");
        for (Question item : tousLesItems(paquet())) {
            assertThat(bonnesReponsesRetirees)
                    .as("'%s' : les quatre items a trois rangees de propositions (vite, ancien,"
                            + " poli, drole) sont retires du paquet, pas seulement de la forme"
                            + " validee", item.enonce())
                    .doesNotContain(item.bonneReponse());
        }
    }

    @Test
    void lesSeizeEnoncesSontExactementCeuxAttendus() {
        assertThat(tousLesItems(paquet()))
                .as("SYN-8 : sur les items reecrits, seules la clause invariante et la marque du mot"
                        + " cible ont change - aucun mot de la phrase de contexte n'a bouge, aucune"
                        + " bonne reponse ni aucun distracteur n'a change")
                .containsExactlyInAnyOrderElementsOf(SEIZE_ITEMS_ATTENDUS);
    }

    @Test
    void chaqueItemMarqueSonMotCibleParDesGuillemetsFrancaisAvecDesEspacesInsecables() {
        for (Question item : tousLesItems(paquet())) {
            String enonce = item.enonce();
            Matcher trouve = MOT_ENTRE_GUILLEMETS_FRANCAIS.matcher(enonce);

            assertThat(trouve.find())
                    .as("'%s' (SYN-6, SYN-9) : le mot cible est marque par des guillemets francais"
                            + " avec des espaces insecables immediatement a l'interieur", enonce)
                    .isTrue();
            assertThat(trouve.find())
                    .as("'%s' (SYN-1) : le mot cible n'est cite qu'une seule fois - la clause"
                            + " invariante ne le repete plus dans l'enonce", enonce)
                    .isFalse();
            assertThat(enonce)
                    .as("'%s' (SYN-6) : plus aucun guillemet droit", enonce)
                    .doesNotContain("\"");
            assertThat(enonce)
                    .as("'%s' (SYN-7) : la clause invariante a quitte l'enonce, elle vit dans la"
                            + " consigne de catalogue", enonce)
                    .doesNotContain(CLAUSE_INVARIANTE);
        }
    }

    /**
     * ADR-0052, SYN-6, SYN-7 : l'entree de catalogue porte une consigne generique qui designe le
     * mot cible par sa marque exacte ("guillemets"), sans nommer aucun mot d'aucun item - une
     * consigne qui citerait un mot precis figerait le jeu sur cet item.
     */
    @Test
    void laConsigneDeCatalogueDesigneLeMotEntreGuillemetsSansNommerAucunItem() {
        Map<String, Object> catalogue = catalogueCharge();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> jeux = (List<Map<String, Object>>) catalogue.get("jeux");
        Map<String, Object> entree = jeux.stream()
                .filter(jeu -> ID_DE_CATALOGUE.equals(jeu.get("id")))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        ID_DE_CATALOGUE + " : aucune entree de ce nom dans catalogue.yaml"));

        assertThat(entree.get("paquet")).isEqualTo(PAQUET);
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(consigne)
                .as("SYN-6 : la consigne designe le mot cible par sa marque exacte")
                .contains("guillemets");

        for (Question item : tousLesItems(paquet())) {
            assertThat(consigne)
                    .as("la consigne ne nomme aucune bonne reponse d'aucun item, elle reste generique"
                            + " pour les seize", item.bonneReponse())
                    .doesNotContain(item.bonneReponse());
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> catalogueCharge() {
        try (var flux = PaquetCm1SynonymesTest.class.getResourceAsStream("/catalogue.yaml")) {
            assertThat(flux).as("/catalogue.yaml introuvable sur le classpath").isNotNull();
            return (Map<String, Object>) new org.yaml.snakeyaml.Yaml().load(flux);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    void leDescripteurEstFrancaisCm1VocabulaireSynonymesEtAntonymes() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere())
                .as("ADR-0049 : la meme graphie ASCII que les douze autres paquets servis - une"
                        + " graphie accentuee ici, seule, ferait echouer"
                        + " UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest")
                .isEqualTo("Francais");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Vocabulaire");
        assertThat(descripteur.competence())
                .contains("synonymes")
                .contains("antonymes");
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
