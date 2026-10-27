package com.recreo.adapter.content;

import com.recreo.adapter.content.PaquetLu.Item;
import com.recreo.adapter.content.PaquetLu.Paquet;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

/**
 * S57-BR-QLM-CE1-12 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-qlm-generations}, ligne {@code QLM-CE1-12} - situer les generations d'une famille
 * et les evenements d'une vie. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section 3.12 :
 * {@code GEN-CE1-1}, {@code GEN-CE1-2}, {@code BIA-4} ; section 2 : {@code FER-1}, {@code FER-3}) et
 * regles {@code R12-1} a {@code R12-9} ({@code arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md},
 * section 2.1, et {@code arbitrage-nadia-contradictions-ce1-qlm-10-12.md}, section 2.1). Formes des
 * gardes : {@code avis-theo-palier-5-gardes-ce1-qlm-10-12.md}, section 2, et
 * {@code avis-theo-palier-5-ajustements-gardes-ce1-qlm-10-12.md} ({@code T1}, {@code T2}, {@code T7},
 * {@code T9}). Les conditions transverses ({@code TRV-CE1-1}, {@code -2}, {@code -5},
 * {@code LON-CE1-1}, {@code -2}, {@code -5}, {@code QMT-CE1-1}, {@code NRC-CE1-1} sur le texte) sont
 * gardees une fois pour les neuf lots par
 * {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Ce que ce test tient.</b> Les listes fermees ({@link #GENERATIONS}, {@link #EVENEMENTS},
 * {@link #ADULTE}, {@link #SUITE_ADMISE}) et la table de {@code R12-5} ({@link #VOISINS_ADMIS}) sont des
 * constantes d'ici, source {@code arbitrage-nadia-contradictions-ce1-qlm-10-12.md} section 2.1 : les
 * lire dans l'en-tete du paquet laisserait etendre une liste en editant un commentaire. Une
 * proposition est reconnue par <b>egalite</b> apres normalisation, jamais par sous-chaine. La tache
 * d'un item est une fonction de sa seule bonne reponse (une partie : {@code voisin} ; trois parties :
 * {@code ordre}), et les deux lignes de compte de l'en-tete sont relues contre les items.
 *
 * <p><b>Ce que ce test ne garde pas</b> ({@code T12-2}, lu par nadia-prof) : que la suite de prenoms
 * d'un item soit la seule dans le sens ecrit et de trois generations differentes ; que
 * <i>generation</i> soit vu depuis le personnage nomme ; que la bonne reponse d'un item <i>de quelle
 * generation</i> soit exacte ; qu'aucune configuration familiale ne soit la norme ({@code GEN-CE1-2}) ;
 * que les prenoms et les roles soient varies ({@code TRV-CE1-6}) ; qu'un indice ne donne pas la reponse
 * par une periphrase ou par un mot hors liste ({@code l'ecole}) ; que la rotation qu'un distracteur
 * propose soit plausible. Cela reste a la lecture et au jeu de nadia-prof ({@code QC9}). La rotation de
 * {@code -01} ne s'applique pas ici : un ordre de generations n'est pas un cycle.
 */
class PaquetCe1QlmGenerationsTest {

    private static final String PAQUET = "ce1-qlm-generations";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmGenerationsTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String VOISIN = "voisin";

    private static final String ORDRE = "ordre";

    private static final String GEN = "gen";

    private static final String EVT = "evt";

    private static final String PRE = "pre";

    /** R12-1 : les generations, rang du plus age au plus jeune, ecrites sans accent et sans determinant. */
    private static final Map<String, Integer> GENERATIONS = Map.of(
            "arriere-grands-parents", 0, "grands-parents", 1, "parents", 2, "enfants", 3);

    /** R12-1 : les evenements d'une vie ; aucun ordre n'est lu entre le premier emploi et la naissance de son enfant. */
    private static final Set<String> EVENEMENTS = Set.of(
            "naissance", "entree a l'ecole", "entree au college", "premier emploi", "naissance de son enfant");

    /** R12-5 : les deux moments d'adulte entre lesquels la table laisse choisir. */
    private static final Set<String> ADULTE = Set.of("premier emploi", "naissance de son enfant");

    /** R12-2 : la seule suite d'evenements admise. */
    private static final List<String> SUITE_ADMISE = List.of("naissance", "entree a l'ecole", "premier emploi");

    /** La table de R12-5 pour une ligne : la bonne reponse et le moment d'enfance impose en distracteur. */
    private record LigneDeLaTable(String bonneReponse, String distracteurDEnfance) {
    }

    /** R12-5, R12-6 : les quatre seules questions voisin d'evenements, cle (reference, sens). */
    private static final Map<String, LigneDeLaTable> VOISINS_ADMIS = Map.of(
            "naissance|apres", new LigneDeLaTable("entree a l'ecole", "entree au college"),
            "entree a l'ecole|avant", new LigneDeLaTable("naissance", "entree au college"),
            "entree a l'ecole|apres", new LigneDeLaTable("entree au college", "naissance"),
            "entree au college|avant", new LigneDeLaTable("entree a l'ecole", "naissance"));

    /** G12-1 : les dix moments de cp-frise-de-la-journee, plus reveil et autrefois, ecrits sans accent. */
    private static final List<String> MOMENTS_DE_LA_JOURNEE = List.of(
            "reveils?", "lever", "petit-dejeuners?", "dejeuners?", "ecole du matin", "ecole de l'apres-midi",
            "gouters?", "diners?", "coucher", "pyjamas?", "brosser les dents", "autrefois");

    private static final Pattern MOMENT_DE_LA_JOURNEE = Pattern.compile(
            "(?<!\\p{L})(" + String.join("|", MOMENTS_DE_LA_JOURNEE) + ")(?!\\p{L})", FLAGS);

    /** R12-5 : le mot de tete d'une proposition, liste fermee, du plus long au plus court. */
    private static final Pattern DETERMINANT = Pattern.compile(
            "^(celle des |la generation des |les |la |le |l'|sa |son )", FLAGS);

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    private static final Pattern PONCTUATION_FINALE = Pattern.compile("[.!?]+$");

    /** Les prenoms de l'enonce : un mot qui commence par une majuscule. */
    private static final Pattern PRENOM = Pattern.compile("(?<!\\p{L})\\p{Lu}\\p{Ll}+(?!\\p{L})");

    /** R12-2 : les trois formes d'un enonce de la tache voisin, sur le texte sans accent. */
    private static final Pattern FORME_VOISIN = Pattern.compile("juste (avant|apres)|de quelle generation");

    private static final Pattern JUSTE_AVANT_OU_APRES = Pattern.compile("juste (avant|apres) (.+?) ?\\?$");

    private static final String SENS_DU_PLUS_AGE = "du plus age au plus jeune";

    private static final String SENS_DU_PREMIER = "du premier au dernier";

    /** T1 : les permutations lues sur les rangs de la bonne suite. */
    private static final List<Integer> ORDRE_INVERSE = List.of(2, 1, 0);

    private static final Set<List<Integer>> LES_DEUX_ECHANGES_DE_VOISINS =
            Set.of(List.of(1, 0, 2), List.of(0, 2, 1));

    private static final Pattern CHIFFRE = Pattern.compile("\\p{Nd}");

    private static final Pattern ANS = Pattern.compile("(?<!\\p{L})ans(?!\\p{L})", FLAGS);

    /** G12-6 : le mot de parente que ton, ta ou tes ne doit pas precéder. */
    private static final String PARENTE = "(?:mamans?|papas?|meres?|peres?|parents?|grands?-(?:parents?|meres?|peres?)"
            + "|arriere-grands?-(?:parents?|meres?|peres?)|freres?|soeurs?|familles?|enfants?|oncles?|tantes?"
            + "|cousins?|cousines?|fils|filles?|bebes?)";

    private static final Pattern VIT_AVEC = Pattern.compile("(?<!\\p{L})(vit|vivent) avec(?!\\p{L})", FLAGS);

    private static final Pattern TU_OU_TOI = Pattern.compile("(?<!\\p{L})(tu|toi)(?!\\p{L})", FLAGS);

    private static final Pattern POSSESSIF_ET_PARENTE = Pattern.compile(
            "(?<!\\p{L})(ton|ta|tes) (?:\\p{L}+ )?" + PARENTE + "(?!\\p{L})", FLAGS);

    /** R12-7, T9 : les formes fermees qu'un indice ne doit pas ecrire, la plus longue d'abord, bornees par lettre ou tiret. */
    private static final Pattern FORME_FERMEE = Pattern.compile(
            "(?<![\\p{L}-])(arriere-grands?-parents?|naissance de son enfant|entree a l'ecole|entree au college"
                    + "|premier emploi|grands?-parents?|naissance|parents?|enfants?)(?![\\p{L}-])", FLAGS);

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(voisin|ordre) : .*\\((\\d+) items?");

    /** FER-3 : la tache la moins servie porte au moins ce nombre d'items. */
    private static final int ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE = 3;

    private static Paquet paquet() {
        return PaquetLu.lirePaquet(FICHIER_CONTENU);
    }

    private static Paquet fautif(String nom) {
        return PaquetLu.lirePaquet(CONTRE_EXEMPLE.resolve(nom));
    }

    /** Les items du jeu fautif qui portent ce marqueur en commentaire, pour mordre un verificateur a la fois. */
    private static List<Item> sonde(Paquet fautif, String marqueur) {
        return fautif.items().stream().filter(item -> item.commentaires().contains(marqueur)).toList();
    }

    /** Une sonde, seule dans le jeu fautif : ce verificateur la refuse, avec ces fragments de message. */
    private static void refuse(Consumer<List<Item>> verificateur, Paquet fautif, String marqueur,
                               String... fragments) {
        List<Item> items = sonde(fautif, marqueur);
        assertThat(items).as(marqueur).hasSize(1);
        var refus = assertThatThrownBy(() -> verificateur.accept(items)).as(marqueur)
                .isInstanceOf(AssertionError.class);
        for (String fragment : fragments) {
            refus.hasMessageContaining(fragment);
        }
    }

    /** Une sonde, seule dans le jeu fautif : ce verificateur la laisse passer. */
    private static void laisse(Consumer<List<Item>> verificateur, Paquet fautif, String marqueur) {
        List<Item> items = sonde(fautif, marqueur);
        assertThat(items).as(marqueur).hasSize(1);
        verificateur.accept(items);
    }

    // ----- normalisation et lecture d'un item

    /** Minuscule, sans accent, apostrophe droite, blancs (insecables compris) ramenes a une espace. */
    static String plat(String texte) {
        String sansAccent = PaquetLu.sansAccent(texte).replace('’', '\'');
        return BLANCS.matcher(sansAccent).replaceAll(" ").strip();
    }

    /** Une proposition ou une partie de suite : sans ponctuation finale, sans determinant de tete. */
    static String normalise(String proposition) {
        String sansPonctuation = PONCTUATION_FINALE.matcher(plat(proposition)).replaceFirst("").strip();
        return DETERMINANT.matcher(sansPonctuation).replaceFirst("").strip();
    }

    private static Set<String> prenomsDe(String enonce) {
        Set<String> prenoms = new HashSet<>();
        Matcher prenom = PRENOM.matcher(enonce);
        while (prenom.find()) {
            prenoms.add(prenom.group());
        }
        return prenoms;
    }

    /** gen, evt, pre (un prenom de l'enonce, par egalite brute) ou null. */
    private static String groupe(String proposition, Set<String> prenoms) {
        if (prenoms.contains(proposition.strip())) {
            return PRE;
        }
        String normalisee = normalise(proposition);
        if (GENERATIONS.containsKey(normalisee)) {
            return GEN;
        }
        return EVENEMENTS.contains(normalisee) ? EVT : null;
    }

    private static List<String> parties(String proposition) {
        return Arrays.stream(proposition.split(",")).map(String::strip).toList();
    }

    private static List<String> partiesNormalisees(String proposition) {
        return parties(proposition).stream().map(PaquetCe1QlmGenerationsTest::normalise).toList();
    }

    /** Le groupe commun des parties d'une suite, ou null si l'une n'a pas de groupe ou si elles different. */
    private static String groupeDeLaSuite(List<String> parties, Set<String> prenoms) {
        String commun = null;
        for (String partie : parties) {
            String groupe = groupe(partie, prenoms);
            if (groupe == null || (commun != null && !commun.equals(groupe))) {
                return null;
            }
            commun = groupe;
        }
        return commun;
    }

    /** R12-2 : une partie est la tache voisin, trois parties la tache ordre, toute autre forme est refusee. */
    private static String tacheDe(Item item) {
        int nombreDeParties = item.bonneReponse().split(",").length;
        return switch (nombreDeParties) {
            case 1 -> VOISIN;
            case 3 -> ORDRE;
            default -> fail("R12-2 : la bonne réponse « %s » de '%s' n'est ni une seule génération ou un seul "
                    + "événement ni une suite de trois (ni voisin, ni ordre)", item.bonneReponse(), item.enonce());
        };
    }

    // ----- G12-1, R12-8, GEN-CE1-1

    /** G12-1 : aucun moment d'une journee et aucun autrefois, dans aucun champ lu. */
    static void verifierQueRienNEstUneJourneeNiUnAutrefois(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher moment = MOMENT_DE_LA_JOURNEE.matcher(plat(champ));
                boolean trouve = moment.find();
                assertThat(trouve)
                        .as("G12-1 : « %s » (champ de '%s') porte un moment de la journée ou un autrefois : %s",
                                champ, item.enonce(), trouve ? moment.group(1) : "")
                        .isFalse();
            }
        }
    }

    // ----- G12-2, R12-1, R12-2, R12-3, R12-5

    /** G12-2 : chaque proposition a un groupe, les trois le meme ; un prenom seulement dans une suite ; jamais la reference. */
    static void verifierLesGroupes(List<Item> items) {
        for (Item item : items) {
            String tache = tacheDe(item);
            Set<String> prenoms = prenomsDe(item.enonce());
            if (tache.equals(VOISIN)) {
                verifierUnItemVoisin(item, prenoms);
            } else {
                verifierUnItemOrdre(item, prenoms);
            }
        }
    }

    private static void verifierUnItemVoisin(Item item, Set<String> prenoms) {
        String plat = plat(item.enonce());
        assertThat(FORME_VOISIN.matcher(plat).find())
                .as("G12-2 : l'énoncé « %s » n'a aucune des trois formes de R12-2 (juste avant, juste après, de "
                        + "quelle génération)", item.enonce())
                .isTrue();
        Set<String> groupes = new TreeSet<>();
        for (String proposition : item.propositions()) {
            String groupe = groupe(proposition, prenoms);
            assertThat(groupe)
                    .as("G12-2 : « %s » (proposition de '%s') est hors des listes fermées", proposition,
                            item.enonce())
                    .isNotNull();
            assertThat(groupe)
                    .as("G12-2 : « %s » (proposition de '%s') est un prénom, refusé dans un item voisin",
                            proposition, item.enonce())
                    .isNotEqualTo(PRE);
            groupes.add(groupe);
        }
        assertThat(groupes)
                .as("G12-2 : les propositions de '%s' mêlent les deux moitiés (%s)", item.enonce(), groupes)
                .hasSize(1);
        Matcher juste = JUSTE_AVANT_OU_APRES.matcher(plat);
        if (juste.find()) {
            String reference = juste.group(2);
            assertThat(groupe(reference, prenoms))
                    .as("G12-2 : la référence « %s » de '%s' est hors des listes fermées", reference,
                            item.enonce())
                    .isIn(GEN, EVT);
            for (String proposition : item.propositions()) {
                assertThat(normalise(proposition))
                        .as("G12-2 : « %s » (proposition de '%s') est l'élément de référence de l'énoncé",
                                proposition, item.enonce())
                        .isNotEqualTo(normalise(reference));
            }
        }
    }

    private static void verifierUnItemOrdre(Item item, Set<String> prenoms) {
        Set<String> groupes = new TreeSet<>();
        for (String proposition : item.propositions()) {
            String groupe = groupeDeLaSuite(parties(proposition), prenoms);
            assertThat(groupe)
                    .as("G12-2 : « %s » (proposition de '%s') n'est pas une suite de trois éléments d'un même "
                            + "groupe fermé", proposition, item.enonce())
                    .isNotNull();
            groupes.add(groupe);
        }
        assertThat(groupes)
                .as("G12-2 : les suites de '%s' mêlent plusieurs groupes (%s)", item.enonce(), groupes)
                .hasSize(1);
    }

    // ----- G12-3, R12-2 (B), T1

    /** G12-3 : le sens est ecrit, la suite est dans l'ordre, un distracteur est un echange de voisins ou une rotation. */
    static void verifierLesSuites(List<Item> items) {
        for (Item item : items) {
            if (!tacheDe(item).equals(ORDRE)) {
                continue;
            }
            String plat = plat(item.enonce());
            boolean sensDeLAge = plat.contains(SENS_DU_PLUS_AGE);
            boolean sensDuPremier = plat.contains(SENS_DU_PREMIER);
            assertThat(sensDeLAge || sensDuPremier)
                    .as("G12-3 : l'énoncé « %s » n'écrit ni « du plus âgé au plus jeune » ni « du premier au "
                            + "dernier »", item.enonce())
                    .isTrue();
            String groupe = groupeDeLaSuite(parties(item.bonneReponse()), prenomsDe(item.enonce()));
            assertThat(groupe)
                    .as("G12-3 : la suite « %s » de '%s' n'est pas faite d'éléments d'un même groupe fermé",
                            item.bonneReponse(), item.enonce())
                    .isNotNull();
            assertThat(groupe.equals(EVT) ? sensDuPremier : sensDeLAge)
                    .as("G12-3 : le sens que '%s' écrit n'est pas celui de son groupe (%s)", item.enonce(), groupe)
                    .isTrue();
            List<String> bonne = partiesNormalisees(item.bonneReponse());
            assertThat(new HashSet<>(bonne))
                    .as("G12-3 : la suite « %s » de '%s' répète un élément", item.bonneReponse(), item.enonce())
                    .hasSize(3);
            if (groupe.equals(GEN)) {
                assertThat(bonne.stream().map(GENERATIONS::get).toList())
                        .as("G12-3 : la suite « %s » de '%s' n'est pas du plus âgé au plus jeune",
                                item.bonneReponse(), item.enonce())
                        .isSorted();
            }
            if (groupe.equals(EVT)) {
                assertThat(bonne)
                        .as("G12-3 : la suite « %s » de '%s' n'est pas la suite d'événements admise",
                                item.bonneReponse(), item.enonce())
                        .isEqualTo(SUITE_ADMISE);
            }
            Set<List<Integer>> permutations = new HashSet<>();
            for (String distracteur : item.distracteurs()) {
                List<String> autre = partiesNormalisees(distracteur);
                assertThat(autre)
                        .as("G12-3 : « %s » (distracteur de '%s') n'est pas les trois mêmes éléments dans un "
                                + "autre ordre", distracteur, item.enonce())
                        .containsExactlyInAnyOrderElementsOf(bonne)
                        .isNotEqualTo(bonne);
                List<Integer> permutation = autre.stream().map(bonne::indexOf).toList();
                assertThat(permutation)
                        .as("G12-3 : « %s » (distracteur de '%s') est l'ordre inverse de la bonne suite",
                                distracteur, item.enonce())
                        .isNotEqualTo(ORDRE_INVERSE);
                permutations.add(permutation);
            }
            assertThat(permutations)
                    .as("G12-3 : les deux distracteurs de '%s' sont les deux échanges de voisins : la proposition "
                            + "la plus ressemblante aux deux autres est la bonne réponse", item.enonce())
                    .isNotEqualTo(LES_DEUX_ECHANGES_DE_VOISINS);
        }
    }

    // ----- G12-4, R12-5, R12-6, T7

    /** G12-4 : un item d'evenements est une des quatre questions de la table, avec ses propositions, ou la suite admise. */
    static void verifierLesEvenements(List<Item> items) {
        for (Item item : items) {
            boolean evenements = item.propositions().stream().flatMap(proposition -> parties(proposition).stream())
                    .anyMatch(partie -> EVENEMENTS.contains(normalise(partie)));
            if (!evenements) {
                continue;
            }
            if (tacheDe(item).equals(ORDRE)) {
                assertThat(partiesNormalisees(item.bonneReponse()))
                        .as("G12-4 : la suite « %s » de '%s' n'est pas la suite d'événements admise",
                                item.bonneReponse(), item.enonce())
                        .isEqualTo(SUITE_ADMISE);
                continue;
            }
            Matcher juste = JUSTE_AVANT_OU_APRES.matcher(plat(item.enonce()));
            assertThat(juste.find())
                    .as("G12-4 : '%s' est une question d'événements qui n'est ni juste avant ni juste après",
                            item.enonce())
                    .isTrue();
            String cle = normalise(juste.group(2)) + "|" + juste.group(1);
            LigneDeLaTable ligne = VOISINS_ADMIS.get(cle);
            assertThat(ligne)
                    .as("G12-4 : la question de '%s' (%s) est hors de R12-6, qui n'en admet que quatre",
                            item.enonce(), cle)
                    .isNotNull();
            assertThat(normalise(item.bonneReponse()))
                    .as("G12-4 : la bonne réponse « %s » de '%s' n'est pas celle de la table de R12-5",
                            item.bonneReponse(), item.enonce())
                    .isEqualTo(ligne.bonneReponse());
            List<String> distracteurs = item.distracteurs().stream().map(PaquetCe1QlmGenerationsTest::normalise).toList();
            assertThat(distracteurs)
                    .as("G12-4 : les distracteurs de '%s' ne portent pas le moment d'enfance de la table (%s)",
                            item.enonce(), ligne.distracteurDEnfance())
                    .contains(ligne.distracteurDEnfance());
            List<String> autres = new ArrayList<>(distracteurs);
            autres.remove(ligne.distracteurDEnfance());
            assertThat(autres)
                    .as("G12-4 : le second distracteur de '%s' n'est pas un moment d'adulte de la table",
                            item.enonce())
                    .hasSize(1).allMatch(ADULTE::contains);
        }
    }

    // ----- G12-5, R12-4, A.259

    /** G12-5 : aucun chiffre et aucun ans, dans aucun champ lu. */
    static void verifierQuAucunNombreNEstEcrit(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                assertThat(CHIFFRE.matcher(champ).find())
                        .as("G12-5 : « %s » (champ de '%s') porte un chiffre", champ, item.enonce()).isFalse();
                assertThat(ANS.matcher(plat(champ)).find())
                        .as("G12-5 : « %s » (champ de '%s') porte un âge en ans", champ, item.enonce()).isFalse();
            }
        }
    }

    // ----- G12-6, R12-7, BIA-4, GEN-CE1-2

    /** G12-6 : jamais qui vit avec qui, jamais tu ou toi, jamais ton, ta, tes suivi d'un mot de parente. */
    static void verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                String plat = plat(champ);
                assertThat(VIT_AVEC.matcher(plat).find())
                        .as("G12-6 : « %s » (champ de '%s') dit qui vit avec qui (GEN-CE1-2)", champ,
                                item.enonce())
                        .isFalse();
                assertThat(TU_OU_TOI.matcher(plat).find())
                        .as("G12-6 : « %s » (champ de '%s') s'adresse à l'enfant par tu ou toi (BIA-4)", champ,
                                item.enonce())
                        .isFalse();
                assertThat(POSSESSIF_ET_PARENTE.matcher(plat).find())
                        .as("G12-6 : « %s » (champ de '%s') nomme la famille de l'enfant (BIA-4)", champ,
                                item.enonce())
                        .isFalse();
            }
        }
    }

    /**
     * G12-6, premiere moitie de R12-7 (T9, forme de G10-4) : l'indice ne contient pas la bonne reponse
     * d'une tache voisin ni le premier element d'une suite. Les formes fermees sont lues du plus long au
     * plus court, bornees par une lettre ou un tiret : <i>grands-parents</i> ne contient pas <i>parents</i>.
     */
    static void verifierQueLIndiceNeDonnePasLaReponse(List<Item> items) {
        for (Item item : items) {
            Set<String> formes = new HashSet<>();
            Matcher forme = FORME_FERMEE.matcher(plat(item.indice()));
            while (forme.find()) {
                formes.add(formeNormalisee(forme.group(1)));
            }
            if (tacheDe(item).equals(VOISIN)) {
                assertThat(formes)
                        .as("G12-6 (R12-7) : l'indice « %s » de '%s' contient la bonne réponse « %s »",
                                item.indice(), item.enonce(), item.bonneReponse())
                        .doesNotContain(normalise(item.bonneReponse()));
                continue;
            }
            List<String> parties = parties(item.bonneReponse());
            if (PRE.equals(groupeDeLaSuite(parties, prenomsDe(item.enonce())))) {
                assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(parties.get(0)) + "(?!\\p{L})")
                        .matcher(item.indice()).find())
                        .as("G12-6 (R12-7) : l'indice « %s » de '%s' contient le premier prénom de la suite",
                                item.indice(), item.enonce())
                        .isFalse();
            } else {
                assertThat(formes)
                        .as("G12-6 (R12-7) : l'indice « %s » de '%s' contient le premier élément de la suite",
                                item.indice(), item.enonce())
                        .doesNotContain(normalise(parties.get(0)));
            }
        }
    }

    private static String formeNormalisee(String forme) {
        if (forme.startsWith("arriere-grand")) {
            return "arriere-grands-parents";
        }
        if (forme.startsWith("grand")) {
            return "grands-parents";
        }
        if (forme.startsWith("parent")) {
            return "parents";
        }
        return forme.startsWith("enfant") ? "enfants" : forme;
    }

    // ----- G12-7, FER-1, FER-3

    /** G12-7, FER-3 : deux taches, la moins servie au moins trois items, et l'en-tete dit les comptes. */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(tacheDe(item), 1, Integer::sum));
        assertThat(comptes.keySet()).as("FER-3 (G12-7) : le paquet ne sert pas deux tâches distinctes (%s)", comptes)
                .containsExactlyInAnyOrder(VOISIN, ORDRE);
        comptes.forEach((tache, compte) -> assertThat(compte)
                .as("FER-3 (G12-7) : la tâche %s porte %d items, la moins servie en porte au moins %d", tache,
                        compte, ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE)
                .isGreaterThanOrEqualTo(ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE));
        Map<String, Integer> ecrits = new TreeMap<>();
        for (String ligne : entete.split("\\R")) {
            Matcher tache = TACHE_DE_L_ENTETE.matcher(ligne);
            if (tache.find()) {
                ecrits.put(tache.group(1), Integer.parseInt(tache.group(2)));
            }
        }
        assertThat(ecrits)
                .as("FER-3 (G12-7) : les comptes que le commentaire du paquet écrit ne sont pas ceux du paquet")
                .isEqualTo(comptes);
    }

    /**
     * G12-7, FER-1 : enfants, parents et grands-parents sont chacun la bonne reponse d'un item voisin, et
     * un evenement l'est d'un item voisin. Une suite, un prenom et un mot de parente ne comptent pas.
     */
    static void verifierLesDeuxMoitiesEnBonneReponse(List<Item> items) {
        Set<String> enBonneReponse = new HashSet<>();
        for (Item item : items) {
            if (tacheDe(item).equals(VOISIN)) {
                enBonneReponse.add(normalise(item.bonneReponse()));
            }
        }
        for (String generation : List.of("enfants", "parents", "grands-parents")) {
            assertThat(enBonneReponse)
                    .as("FER-1 (G12-7) : « %s » n'est la bonne réponse d'aucun item voisin (%s)", generation,
                            enBonneReponse)
                    .contains(generation);
        }
        assertThat(enBonneReponse.stream().anyMatch(EVENEMENTS::contains))
                .as("FER-1 (G12-7) : aucun événement n'est la bonne réponse d'un item voisin (%s)", enBonneReponse)
                .isTrue();
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe112EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-12"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-12");
    }

    @Test
    void lIdentifiantDuPaquetEstLeNomDeSonFichier() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("id")).as("le chargeur ne compare pas le champ id au nom de fichier")
                .isEqualTo(PAQUET);
    }

    @Test
    void qc5LeChargeurLitTousLesItemsEtIlsSontEntreOnzeEtVingt() {
        ContentSource source = new YamlContentPackLoader().chargerPaquet(PAQUET);
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> charges = new ArrayList<>();
        for (var item = source.next(hasard, dejaVus).orElse(null); item != null;
             item = source.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            charges.add(question);
            dejaVus.add(question.id());
        }

        assertThat(charges).hasSameSizeAs(paquet().items());
        assertThat(paquet().items().size())
                .as("QC5 : entre 11 (plus que les dix manches, DEC-0120 A.258) et 20 items")
                .isBetween(11, 20);
    }

    @Test
    void g121AucunMomentDUneJourneeNiAutrefoisDansLePaquet() {
        verifierQueRienNEstUneJourneeNiUnAutrefois(paquet().items());
    }

    @Test
    void g122ChaquePropositionEstDansUneListeFermeeEtLesTroisSontDuMemeGroupe() {
        verifierLesGroupes(paquet().items());
    }

    @Test
    void g123ChaqueSuiteEstDansLeSensEcritEtSesDistracteursSontDesEchangesOuDesRotations() {
        verifierLesSuites(paquet().items());
    }

    @Test
    void g124ChaqueItemDEvenementsEstUneDesQuatreQuestionsDeLaTableOuLaSuiteAdmise() {
        verifierLesEvenements(paquet().items());
    }

    @Test
    void g125AucunNombreDansLePaquet() {
        verifierQuAucunNombreNEstEcrit(paquet().items());
    }

    @Test
    void g126LaFamilleDeLEnfantNEstJamaisEvoqueeEtAucunIndiceNeDonneLaReponse() {
        verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee(paquet().items());
        verifierQueLIndiceNeDonnePasLaReponse(paquet().items());
    }

    @Test
    void g127DeuxTachesLaMoinsServiePorteTroisItemsEtLEnTeteDitLesComptes() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    @Test
    void fer1ChaqueGenerationDeLaParentheseEtUnEvenementSontEnBonneReponseDUnItemVoisin() {
        verifierLesDeuxMoitiesEnBonneReponse(paquet().items());
    }

    @Test
    void typ1LEspaceAvantLePointDInterrogationEstInsecable() {
        for (Item item : paquet().items()) {
            for (String champ : item.champsLus()) {
                assertThat(champ).as("TYP-1 : « %s » porte une espace ordinaire avant ?, :, ; ou !", champ)
                        .doesNotContainPattern("[^\\u00A0] [?:;!]");
            }
        }
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurChacunDesDouzeMomentsDeLaJourneeDansUnChampDifferent() {
        Paquet paquet = fautif("journee.yaml");

        assertThat(paquet.items()).as("un item fautif par moment de la journée").hasSameSizeAs(MOMENTS_DE_LA_JOURNEE);
        for (int rang = 0; rang < paquet.items().size(); rang++) {
            Item item = paquet.items().get(rang);
            assertThatThrownBy(() -> verifierQueRienNEstUneJourneeNiUnAutrefois(List.of(item)))
                    .as("le moment de l'item %d (%s) doit être refusé", rang + 1, item.enonce())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("G12-1");
        }
    }

    @Test
    void leJeuFautifCommiteRefuseUnReveilUnDejeunerUnCoucherEtAutrefoisEtLaisseLEcoleEtLaSuiteDeLaVie() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierQueRienNEstUneJourneeNiUnAutrefois, fautif, "sonde 01", "G12-1");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueRienNEstUneJourneeNiUnAutrefois, fautif, "sonde 02", "G12-1",
                "autrefois");
        laisse(PaquetCe1QlmGenerationsTest::verifierQueRienNEstUneJourneeNiUnAutrefois, fautif, "sonde 03");
        laisse(PaquetCe1QlmGenerationsTest::verifierQueRienNEstUneJourneeNiUnAutrefois, fautif, "sonde 04");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionUnMotDeParenteEtUnNaissanceDUnEnfant() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 05", "G12-2", "hors des listes");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 08", "G12-2", "grand-mère",
                "hors des listes");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 10", "G12-2", "naissance d'un enfant",
                "hors des listes");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, "sonde 10", "G12-4", "moment d'adulte");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemQuiMeleLesDeuxMoitiesOuQuiProposeLaReference() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 06", "G12-2", "mêlent les deux moitiés");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 07", "G12-2",
                "élément de référence");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 12", "G12-2", "même groupe fermé");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPrenomEnPropositionDUnItemVoisinEtSurUnEnonceSansLesTroisFormes() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 09", "G12-2", "est un prénom");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 11", "G12-2",
                "aucune des trois formes");
    }

    @Test
    void leJeuFautifCommiteLaisseUnItemDeQuelleGenerationEtUneSuiteDePrenomsPasser() {
        Paquet fautif = fautif("generations-fautives.yaml");

        laisse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 13");
        laisse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 14");
        laisse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 14");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstNiUneNiTroisParties() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, "sonde 15", "ni voisin, ni ordre");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 15", "ni voisin, ni ordre");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLOrdreInverseDeLaBonneSuiteEtSurUnEnonceSansSens() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 16", "G12-3", "ordre inverse");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 17", "G12-3",
                "n'écrit ni « du plus âgé au plus jeune »");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 18", "G12-3", "ordre inverse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLesDeuxEchangesDeVoisinsEnsembleSurTroisGroupes() {
        Paquet fautif = fautif("generations-fautives.yaml");

        for (String sonde : List.of("sonde 19", "sonde 20", "sonde 21")) {
            refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, sonde, "G12-3",
                    "les deux échanges de voisins");
        }
    }

    @Test
    void leJeuFautifCommiteLaisseUnEchangeEtUneRotationDeuxRotationsEtUnEchangeEtUneRotationPasser() {
        Paquet fautif = fautif("generations-fautives.yaml");

        for (String sonde : List.of("sonde 22", "sonde 23", "sonde 24")) {
            laisse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, sonde);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSuiteHorsDeSonOrdreDeSonSensOuDeSesElements() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 25", "G12-3",
                "n'est pas du plus âgé au plus jeune");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 26", "G12-3",
                "pas celui de son groupe");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 27", "G12-3",
                "suite d'événements admise");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 28", "G12-3",
                "trois mêmes éléments");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 29", "G12-3", "répète un élément");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneQuestionDEvenementsHorsDeR126() {
        Paquet fautif = fautif("generations-fautives.yaml");

        for (String sonde : List.of("sonde 30", "sonde 31", "sonde 32")) {
            refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, sonde, "G12-4", "hors de R12-6");
        }
        refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, "sonde 33", "G12-4",
                "pas celle de la table");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSuiteQuiPorteLePremierEmploiEtLaNaissanceDeSonEnfant() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, "sonde 34", "G12-4",
                "suite d'événements admise");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesSuites, fautif, "sonde 34", "G12-3",
                "suite d'événements admise");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLesDeuxMomentsDAdulteSansMomentDEnfanceSurChaqueLigneDeLaTable() {
        Paquet fautif = fautif("generations-fautives.yaml");

        for (String sonde : List.of("sonde 35", "sonde 42", "sonde 43", "sonde 44")) {
            refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, sonde, "G12-4",
                    "moment d'enfance de la table");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseHorsDeLaTableEtUnSecondDistracteurHorsDeLAdulte() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, "sonde 36", "G12-4",
                "pas celle de la table");
        refuse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, "sonde 37", "G12-4",
                "moment d'adulte");
    }

    @Test
    void leJeuFautifCommiteLaisseLesQuatreQuestionsDeLaTablePasserAvecChaqueMomentDAdulte() {
        Paquet fautif = fautif("generations-fautives.yaml");

        for (String sonde : List.of("sonde 38", "sonde 39", "sonde 40", "sonde 41")) {
            laisse(PaquetCe1QlmGenerationsTest::verifierLesEvenements, fautif, sonde);
            laisse(PaquetCe1QlmGenerationsTest::verifierLesGroupes, fautif, sonde);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnChiffreEtSurUnAgeEnAnsEtLaisseDansPasser() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierQuAucunNombreNEstEcrit, fautif, "sonde 45", "G12-5", "chiffre");
        refuse(PaquetCe1QlmGenerationsTest::verifierQuAucunNombreNEstEcrit, fautif, "sonde 46", "G12-5", "âge en ans");
        laisse(PaquetCe1QlmGenerationsTest::verifierQuAucunNombreNEstEcrit, fautif, "sonde 47");
        refuse(PaquetCe1QlmGenerationsTest::verifierQuAucunNombreNEstEcrit, fautif, "sonde 48", "G12-5", "chiffre");
        refuse(PaquetCe1QlmGenerationsTest::verifierQuAucunNombreNEstEcrit, fautif, "sonde 49", "G12-5", "chiffre");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurQuiVitAvecQuiEtSurTuToiEtLesPossessifsDeParente() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 50",
                "G12-6", "qui vit avec qui");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 51",
                "G12-6", "qui vit avec qui");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 52",
                "G12-6");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 53",
                "G12-6", "famille de l'enfant");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 54",
                "G12-6", "famille de l'enfant");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 57",
                "G12-6", "tu ou toi");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 58",
                "G12-6", "tu ou toi");
    }

    @Test
    void leJeuFautifCommiteLaisseDeQuelleGenerationEstAliEtLaSuiteDeLaVieDeSaraPasser() {
        Paquet fautif = fautif("generations-fautives.yaml");

        laisse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 55");
        laisse(PaquetCe1QlmGenerationsTest::verifierQueLaFamilleDeLEnfantNEstJamaisEvoquee, fautif, "sonde 56");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiEcritLaBonneReponseOuLePremierElementDeLaSuite() {
        Paquet fautif = fautif("generations-fautives.yaml");

        refuse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 59", "G12-6",
                "R12-7", "contient la bonne réponse");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 61", "G12-6",
                "R12-7", "premier élément de la suite");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 64", "G12-6",
                "R12-7", "premier prénom");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 65", "G12-6",
                "R12-7", "contient la bonne réponse");
        refuse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 66", "G12-6",
                "R12-7", "premier élément de la suite");
    }

    @Test
    void leJeuFautifCommiteLaisseUnIndiceQuiNommeUnVoisinDeLaReponsePasser() {
        Paquet fautif = fautif("generations-fautives.yaml");

        laisse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 60");
        laisse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 62");
        laisse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 63");
        laisse(PaquetCe1QlmGenerationsTest::verifierQueLIndiceNeDonnePasLaReponse, fautif, "sonde 67");
    }

    @Test
    void unPaquetPrivePourLesGrandsParentsEnBonneReponseEstRefuse() {
        List<Item> sansLesGrandsParents = paquet().items().stream()
                .filter(item -> !normalise(item.bonneReponse()).equals("grands-parents")).toList();

        assertThat(sansLesGrandsParents).hasSizeLessThan(paquet().items().size());
        assertThatThrownBy(() -> verifierLesDeuxMoitiesEnBonneReponse(sansLesGrandsParents))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1")
                .hasMessageContaining("grands-parents");
    }

    @Test
    void unPaquetPrivePourLesParentsEnBonneReponseEstRefuseMemeSiUneSuiteLesPorte() {
        List<Item> sansLesParents = paquet().items().stream()
                .filter(item -> !normalise(item.bonneReponse()).equals("parents")).toList();

        assertThat(sansLesParents).hasSizeLessThan(paquet().items().size());
        assertThat(sansLesParents.stream().anyMatch(item -> item.bonneReponse().contains("parents, enfants")))
                .as("une suite porte encore les parents").isTrue();
        assertThatThrownBy(() -> verifierLesDeuxMoitiesEnBonneReponse(sansLesParents))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1")
                .hasMessageContaining("« parents »");
    }

    @Test
    void unPaquetPriveDesItemsVoisinsDEvenementsEstRefuse() {
        List<Item> sansEvenement = paquet().items().stream()
                .filter(item -> !tacheDe(item).equals(VOISIN) || !EVENEMENTS.contains(normalise(item.bonneReponse())))
                .toList();

        assertThat(sansEvenement).hasSizeLessThan(paquet().items().size());
        assertThatThrownBy(() -> verifierLesDeuxMoitiesEnBonneReponse(sansEvenement))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1")
                .hasMessageContaining("aucun événement");
    }

    @Test
    void unPaquetQuiNePorteQueDeuxItemsDeLaTacheOrdreEstRefuse() {
        Paquet paquet = paquet();
        List<Item> ordres = paquet.items().stream().filter(item -> tacheDe(item).equals(ORDRE)).toList();
        List<Item> avecDeuxOrdres = new ArrayList<>(paquet.items().stream()
                .filter(item -> tacheDe(item).equals(VOISIN)).toList());
        avecDeuxOrdres.addAll(ordres.subList(0, 2));

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxOrdres, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("la moins servie");
    }

    @Test
    void unCommentaireQuiEcritUnMauvaisCompteDeTachesEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replace("(8 items)", "(7 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("comptes");
    }

    @Test
    void unPaquetVideNeSertAucuneTacheEtAucuneMoitie() {
        Paquet paquet = paquet();

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(List.of(), paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne sert pas deux tâches");
        assertThatThrownBy(() -> verifierLesDeuxMoitiesEnBonneReponse(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1");
    }
}
