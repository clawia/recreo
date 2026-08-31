package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S24-FRA (DEC-0059 section 2) et l'arbitrage prealable de nadia-prof du 2026-08-30
 * (ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md, section
 * 8bis, FRA-1 a FRA-9), complete le meme jour par
 * ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md,
 * section 5.2, qui arrete la lecture de FRA-5 sur la forme (d) et ajoute FRA-10 : le paquet
 * "cm1-fractions", joue par "bandit-manchot-fractions-cm1".
 *
 * <p>Ne couvre que les conditions calculables a partir du fichier - FRA-1 a FRA-7, FRA-9, FRA-10 -
 * et la moitie calculable de FRA-8 (longueur de l'indice, l'indice ne nomme pas la reponse). Ce que
 * la situation de partage presuppose, et le jugement de niveau, restent la porte de nadia-prof, qui
 * les nomme comme siens.</p>
 *
 * <p><b>Rien n'est recopie du fichier : tout est recalcule.</b> Chaque item declare la fraction
 * enoncee, sa forme au sens de FRA-2 et la famille de chacun de ses deux distracteurs ; la bonne
 * reponse et les deux distracteurs sont derives de ces trois declarations et compares au texte
 * servi. C'est ce que FRA-5 demande - qu'un tiers puisse recalculer les distracteurs a partir de la
 * famille declaree, et sur la forme (d) a partir de la fraction enoncee, qui est dans l'item. Un
 * test qui recopierait le fichier ne dirait rien de plus que le fichier.</p>
 *
 * <p>Les proprietes generiques - indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, accentuation, notation des caracteres -
 * sont deja derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees
 * ici.</p>
 */
class PaquetCm1FractionsTest {

    private static final String PAQUET = "cm1-fractions";

    /** Les cles de declaration d'auteur de ce paquet, qu'aucune fabrique ne lit (ADR-0024, ADR-0070). */
    private static final String CLE_FRACTION = "fraction";

    private static final String CLE_FORME = "forme";

    private static final String CLE_FAMILLES_DISTRACTEURS = "famillesDistracteurs";

    /** FRA-7 : les deux bornes de l'enonce. */
    private static final int MOTS_MAXIMUM_DE_L_ENONCE = 25;

    private static final int LETTRES_MAXIMUM_D_UN_MOT = 11;

    /** FRA-8 : la borne de l'indice. */
    private static final int MOTS_MAXIMUM_DE_L_INDICE = 12;

    /** FRA-9 : le plancher d'items du paquet. */
    private static final int ITEMS_MINIMUM = 20;

    /** FRA-3 : le plancher d'items par forme de FRA-2. */
    private static final int ITEMS_MINIMUM_PAR_FORME = 3;

    /**
     * L'espace ordinaire, l'espace insecable (TYP-1 : le contenu neuf le porte devant un signe
     * double) et les autres blancs separent deux mots. {@code \s} de Java ne voit pas l'insecable.
     */
    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0]+");

    /** Le nom du numerateur, de un a dix - FRA-1 borne le denominateur a dix. */
    private static final List<String> NUMERATEUR_EN_LETTRES = List.of(
            "", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix");

    /** Le nom de l'unite fractionnaire au singulier, indexe par le denominateur. */
    private static final List<String> UNITE_AU_SINGULIER = List.of(
            "", "", "demi", "tiers", "quart", "cinquième", "sixième", "septième", "huitième",
            "neuvième", "dixième");

    /** Le nom de l'unite fractionnaire au pluriel : "tiers" est invariable, les autres prennent le s. */
    private static String unite(int denominateur, boolean pluriel) {
        String singulier = UNITE_AU_SINGULIER.get(denominateur);
        if (!pluriel || singulier.endsWith("s")) {
            return singulier;
        }
        return singulier + "s";
    }

    /** La denomination d'une fraction : "3/8" se lit "trois huitièmes", "1/4" se lit "un quart". */
    private static String denomination(int numerateur, int denominateur) {
        return NUMERATEUR_EN_LETTRES.get(numerateur) + " " + unite(denominateur, numerateur > 1);
    }

    private static String chiffres(int numerateur, int denominateur) {
        return numerateur + "/" + denominateur;
    }

    private record Fraction(int numerateur, int denominateur) {
    }

    /** Un item du paquet, avec les trois declarations d'auteur que son YAML porte. */
    private record ItemDeclare(Question question, Fraction fraction, String forme,
                                Map<String, String> famillesDistracteurs) {
    }

    private static Fraction fractionDe(String declaree) {
        String[] termes = declaree.split("/");
        assertThat(termes)
                .as("la fraction declaree '%s' s'ecrit numerateur/denominateur", declaree)
                .hasSize(2);
        return new Fraction(Integer.parseInt(termes[0]), Integer.parseInt(termes[1]));
    }

    /**
     * La valeur qu'une famille de FRA-5 produit sur un item, ou {@code null} quand cette famille
     * n'a pas de valeur ecrivable sur cet item - c'est le cas de la famille (a) sur une fraction de
     * numerateur 1, dont l'echange produit d/1, qui n'a pas de denomination.
     *
     * <p>La famille (b) rend <b>deux</b> valeurs, un par decalage ; FRA-10 en retire une sur la
     * forme (d) quand elle coincide avec la valeur de la famille (a). C'est pourquoi cette methode
     * rend une liste et non une valeur : le fichier choisit dans ce que la famille permet, et le
     * test verifie qu'il a choisi dedans.</p>
     */
    private static List<String> valeursDeLaFamille(String famille, Fraction fraction, String forme) {
        int numerateur = fraction.numerateur();
        int denominateur = fraction.denominateur();
        List<String> valeurs = new ArrayList<>();
        if ("a".equals(famille)) {
            ajouterValeur(valeurs, forme, denominateur, numerateur, numerateur);
            return valeurs;
        }
        if ("b".equals(famille)) {
            ajouterValeur(valeurs, forme, numerateur, denominateur - 1, denominateur - 1);
            ajouterValeur(valeurs, forme, numerateur, denominateur + 1, denominateur + 1);
            return valeurs;
        }
        throw new AssertionError("famille de distracteur inconnue de FRA-5 : '" + famille + "'");
    }

    /**
     * Ajoute a {@code valeurs} l'ecriture que la forme de FRA-2 demande, ou rien quand la fraction
     * derivee n'est pas ecrivable dans cette forme - un denominateur de 1 ou 0 n'a pas de
     * denomination, et un numerateur de 0 non plus.
     */
    private static void ajouterValeur(List<String> valeurs, String forme, int numerateur,
                                       int denominateur, int lectureDuDenominateur) {
        if ("d".equals(forme)) {
            valeurs.add(String.valueOf(lectureDuDenominateur));
            return;
        }
        if (numerateur < 1 || denominateur < 2) {
            return;
        }
        if ("a".equals(forme)) {
            if (denominateur >= UNITE_AU_SINGULIER.size() || numerateur >= NUMERATEUR_EN_LETTRES.size()) {
                return;
            }
            valeurs.add(denomination(numerateur, denominateur));
            return;
        }
        valeurs.add(chiffres(numerateur, denominateur));
    }

    /** La bonne reponse que la forme de FRA-2 impose, derivee de la seule fraction enoncee. */
    private static String bonneReponseAttendue(String forme, Fraction fraction) {
        return switch (forme) {
            case "a" -> denomination(fraction.numerateur(), fraction.denominateur());
            case "b", "c" -> chiffres(fraction.numerateur(), fraction.denominateur());
            case "d" -> String.valueOf(fraction.denominateur());
            default -> throw new AssertionError("forme inconnue de FRA-2 : '" + forme + "'");
        };
    }

    @Test
    void lePaquetPorteAuMoinsVingtItems() {
        assertThat(itemsDeclares())
                .as("FRA-9 : le paquet porte au moins %d items", ITEMS_MINIMUM)
                .hasSizeGreaterThanOrEqualTo(ITEMS_MINIMUM);
    }

    @Test
    void chaqueFractionEnonceeEstInferieureAUnAvecUnDenominateurDeDeuxADix() {
        for (ItemDeclare item : itemsDeclares()) {
            Fraction fraction = item.fraction();
            assertThat(fraction.numerateur())
                    .as("FRA-1 : '%s' - la fraction enoncee est inferieure a 1", enonce(item))
                    .isLessThan(fraction.denominateur());
            assertThat(fraction.denominateur())
                    .as("FRA-1 : '%s' - le denominateur est compris entre 2 et 10", enonce(item))
                    .isBetween(2, 10);
        }
    }

    @Test
    void chaqueItemPorteLUneDesQuatreFormesEtChacuneEnPorteAuMoinsTrois() {
        Map<String, Integer> parForme = new LinkedHashMap<>();
        for (String forme : List.of("a", "b", "c", "d")) {
            parForme.put(forme, 0);
        }
        for (ItemDeclare item : itemsDeclares()) {
            assertThat(parForme)
                    .as("FRA-2 : '%s' - la forme declaree est l'une des quatre, et d'aucune autre",
                            enonce(item))
                    .containsKey(item.forme());
            parForme.merge(item.forme(), 1, Integer::sum);
        }
        parForme.forEach((forme, effectif) -> assertThat(effectif)
                .as("FRA-3 : la forme (%s) porte au moins %d items", forme, ITEMS_MINIMUM_PAR_FORME)
                .isGreaterThanOrEqualTo(ITEMS_MINIMUM_PAR_FORME));
    }

    @Test
    void unTiersDesItemsAuMoinsPorteUneFractionDeNumerateurUn() {
        List<ItemDeclare> items = itemsDeclares();
        long unitaires = items.stream().filter(item -> item.fraction().numerateur() == 1).count();

        assertThat(unitaires)
                .as("FRA-4 : au moins arrondi-inferieur(%d/3) items ont pour numerateur 1", items.size())
                .isGreaterThanOrEqualTo(items.size() / 3);
    }

    @Test
    void laBonneReponseDeChaqueItemEstCelleQueSaFormeImposeSurLaFractionEnoncee() {
        for (ItemDeclare item : itemsDeclares()) {
            assertThat(item.question().bonneReponse())
                    .as("FRA-2 : '%s' - la bonne reponse recalculee depuis la fraction enoncee et"
                            + " la forme (%s)", enonce(item), item.forme())
                    .isEqualTo(bonneReponseAttendue(item.forme(), item.fraction()));
        }
    }

    @Test
    void chaqueDistracteurEstRecalculableDepuisSaFamilleDeclaree() {
        for (ItemDeclare item : itemsDeclares()) {
            assertThat(item.question().distracteurs())
                    .as("FRA-5 : '%s' - deux distracteurs, tous deux declares", enonce(item))
                    .hasSize(2)
                    .allMatch(distracteur -> item.famillesDistracteurs().containsKey(distracteur));

            for (String distracteur : item.question().distracteurs()) {
                String famille = item.famillesDistracteurs().get(distracteur);
                assertThat(valeursDeLaFamille(famille, item.fraction(), item.forme()))
                        .as("FRA-5 : '%s' - le distracteur '%s' est bien ce que la famille (%s)"
                                + " produit sur %s", enonce(item), distracteur, famille,
                                chiffres(item.fraction().numerateur(), item.fraction().denominateur()))
                        .contains(distracteur);
            }
        }
    }

    /**
     * FRA-5 : la famille (c) - une denomination formee sur le numerateur - est reservee aux formes
     * (a) et (b), et nadia-prof le redit le 2026-08-30 : elle reste exclue de la forme (d). Ce
     * paquet ne l'emploie nulle part, mais le controle vaut pour le jour ou un item l'emploierait.
     */
    @Test
    void laFamilleCNEstJamaisEmployeeSurLesFormesCEtD() {
        for (ItemDeclare item : itemsDeclares()) {
            if (List.of("a", "b").contains(item.forme())) {
                continue;
            }
            assertThat(item.famillesDistracteurs().values())
                    .as("FRA-5 : '%s' - la famille (c) est reservee aux formes (a) et (b)",
                            enonce(item))
                    .doesNotContain("c");
        }
    }

    /**
     * FRA-10, neuve le 2026-08-30 : sur la forme (d), la famille (b) ne rend jamais la valeur que
     * la famille (a) donne deja - c'est l'autre decalage qui est retenu. Sans cette clause, "3/4"
     * proposerait deux fois 3, ce que FRA-6 refuse.
     */
    @Test
    void surLaFormeDLaFamilleBNeRepeteJamaisLaValeurDeLaFamilleA() {
        for (ItemDeclare item : itemsDeclares()) {
            if (!"d".equals(item.forme())) {
                continue;
            }
            String valeurDeLaFamilleA = String.valueOf(item.fraction().numerateur());
            item.famillesDistracteurs().forEach((distracteur, famille) -> {
                if ("b".equals(famille)) {
                    assertThat(distracteur)
                            .as("FRA-10 : '%s' - le decalage retenu n'est pas celui qui redonne le"
                                    + " numerateur", enonce(item))
                            .isNotEqualTo(valeurDeLaFamilleA);
                }
            });
        }
    }

    @Test
    void lesTroisPropositionsDeChaqueItemSontDistinctes() {
        for (ItemDeclare item : itemsDeclares()) {
            List<String> propositions = new ArrayList<>();
            propositions.add(item.question().bonneReponse());
            propositions.addAll(item.question().distracteurs());

            assertThat(propositions)
                    .as("FRA-6 : '%s' - les trois propositions sont distinctes deux a deux",
                            enonce(item))
                    .doesNotHaveDuplicates();
        }
    }

    @Test
    void chaqueEnonceTientDansSesBornesDeMotsEtDeLettres() {
        for (ItemDeclare item : itemsDeclares()) {
            List<String> mots = mots(item.question().enonce());

            assertThat(mots)
                    .as("FRA-7 : '%s' - au plus %d mots", enonce(item), MOTS_MAXIMUM_DE_L_ENONCE)
                    .hasSizeLessThanOrEqualTo(MOTS_MAXIMUM_DE_L_ENONCE);
            for (String mot : mots) {
                assertThat(lettresDe(mot))
                        .as("FRA-7 : '%s' - le mot '%s' ne depasse pas %d lettres", enonce(item),
                                mot, LETTRES_MAXIMUM_D_UN_MOT)
                        .isLessThanOrEqualTo(LETTRES_MAXIMUM_D_UN_MOT);
            }
        }
    }

    /**
     * La moitie calculable de FRA-8 : la longueur de l'indice, et le fait qu'il ne nomme pas la
     * reponse. Ce que la situation de partage presuppose - ni gouter, ni anniversaire, ni part de
     * gateau, ni objet possede - n'est pas calculable et reste la porte de nadia-prof, qui l'ecrit.
     */
    @Test
    void chaqueIndiceTientEnDouzeMotsSansNommerLaReponse() {
        for (ItemDeclare item : itemsDeclares()) {
            String indice = item.question().indice().orElseThrow(
                    () -> new AssertionError("FRA-8 : '" + enonce(item) + "' - indice absent"));

            assertThat(mots(indice))
                    .as("FRA-8 : '%s' - l'indice tient en %d mots au plus", enonce(item),
                            MOTS_MAXIMUM_DE_L_INDICE)
                    .hasSizeLessThanOrEqualTo(MOTS_MAXIMUM_DE_L_INDICE);
            assertThat(indice)
                    .as("FRA-8 : '%s' - l'indice ne nomme jamais la reponse", enonce(item))
                    .doesNotContain(item.question().bonneReponse());
        }
    }

    private static String enonce(ItemDeclare item) {
        return item.question().enonce();
    }

    private static List<String> mots(String texte) {
        return BLANCS.splitAsStream(texte.strip()).filter(mot -> !mot.isEmpty()).toList();
    }

    private static int lettresDe(String mot) {
        int lettres = 0;
        for (int rang = 0; rang < mot.length(); rang++) {
            if (Character.isLetter(mot.charAt(rang))) {
                lettres++;
            }
        }
        return lettres;
    }

    /**
     * Les items du paquet, tires par le chargeur reel, apparies avec les declarations d'auteur du
     * YAML lues a part - aucune fabrique ne les lit, exactement comme 'graphie' sur cp-le-bon-son.
     * L'appariement se fait par le rang, celui-la meme dont la fabrique derive l'identifiant.
     */
    private static List<ItemDeclare> itemsDeclares() {
        List<Question> tires = tousLesItems(new YamlContentPackLoader().chargerPaquet(PAQUET));
        List<Map<String, Object>> bruts = itemsBruts();

        assertThat(tires)
                .as("le chargeur reel et la lecture directe du YAML doivent voir le meme nombre"
                        + " d'items")
                .hasSameSizeAs(bruts);

        List<ItemDeclare> items = new ArrayList<>();
        for (int rang = 0; rang < tires.size(); rang++) {
            Question question = tires.get(rang);
            Map<String, Object> brut = bruts.get(rang);
            assertThat(question.enonce())
                    .as("l'item de rang %d du chargeur et celui du YAML sont le meme", rang)
                    .isEqualTo(String.valueOf(brut.get("enonce")));

            Object familles = brut.get(CLE_FAMILLES_DISTRACTEURS);
            assertThat(familles)
                    .as("FRA-5 : '%s' - le fichier nomme la famille de chaque distracteur",
                            question.enonce())
                    .isInstanceOf(Map.class);
            Map<String, String> famillesParDistracteur = new LinkedHashMap<>();
            ((Map<?, ?>) familles).forEach((distracteur, famille) ->
                    famillesParDistracteur.put(String.valueOf(distracteur), String.valueOf(famille)));

            items.add(new ItemDeclare(question,
                    fractionDe(String.valueOf(brut.get(CLE_FRACTION))),
                    String.valueOf(brut.get(CLE_FORME)),
                    famillesParDistracteur));
        }
        return items;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsBruts() {
        try (InputStream flux = PaquetCm1FractionsTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            assertThat(flux).as("%s introuvable sur le classpath", PAQUET).isNotNull();
            Map<String, Object> corps = new Yaml().load(flux);
            return (List<Map<String, Object>>) corps.get("items");
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        Map<String, Question> parIdentifiant = new LinkedHashMap<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            parIdentifiant.put(question.id().value(), question);
            dejaVus.add(question.id());
        }
        List<Question> ordonnes = new ArrayList<>(parIdentifiant.values());
        ordonnes.sort((gauche, droite) -> Integer.compare(rangDe(gauche), rangDe(droite)));
        return ordonnes;
    }

    /** L'identifiant d'un item de type 'question' est "item-<rang>" : la fabrique le derive du rang. */
    private static int rangDe(Question question) {
        return Integer.parseInt(question.id().value().substring("item-".length()));
    }
}
