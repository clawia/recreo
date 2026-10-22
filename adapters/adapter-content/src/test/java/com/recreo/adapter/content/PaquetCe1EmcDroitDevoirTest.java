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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-EMC-CE1-03 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-emc-droit-devoir}, ligne {@code EMC-CE1-03} - associer des droits de l'eleve a
 * l'ecole aux devoirs qui les accompagnent. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section 4.3 :
 * {@code DDE-CE1-1}, {@code DDE-CE1-2} ; section 2 : {@code TRV-CE1-1}, {@code -2}, {@code -5},
 * {@code -6}, {@code LON-CE1-1}, {@code -2}, {@code -5}, {@code FER-1} a {@code FER-3}).
 *
 * <p><b>Comment ce test recompte {@code DDE-CE1-1}.</b> La liste fermee des quatre couples est
 * ecrite dans le commentaire d'en-tete du paquet, avec, pour chaque couple et chaque cote (droit,
 * devoir), les formulations qui peuvent etre une bonne reponse. Chaque item declare son couple et
 * son sens ({@code droit->devoir} ou {@code devoir->droit}). Ce test garde que la liste est
 * exactement celle de nadia-prof, que chaque item declare un couple de la liste, que sa question
 * demande le cote que son sens annonce, que sa bonne reponse est une formulation de ce couple et de
 * ce cote et d'aucun autre, et que chaque couple est servi dans les deux sens ({@code FER-3}).
 * {@code FER-1} suit : les deux notions du libelle sont chacune en bonne reponse.
 *
 * <p><b>{@code DDE-CE1-2}</b> est gardee sur les mots : aucun champ lu par l'enfant ne dit qu'un
 * droit se gagne, se merite, se perd ou se retire. Ce test ne lit pas une intention.
 *
 * <p><b>Le point d'audit commun de nadia-prof</b> (aucune forme ne donne la reponse) est garde sous
 * la forme que voici : une propriete de forme (mot plein repris de l'enonce, negation, pronom
 * complement en tete) n'est jamais portee par la bonne reponse seule. Ce test ne juge pas qu'un
 * distracteur est croyable ni qu'un item n'a qu'une lecture defendable : les distracteurs sont
 * tires de familles fermees, jamais d'un couple, mais leur vraisemblance reste a la lecture de
 * nadia-prof ({@code EMC-5}).
 *
 * <p>Ce que ce test ne lit pas : {@code NRC-CE1-1} au-dela de l'egalite des textes avec
 * {@code cp-emc-droits-enfant} et {@code cp-emc-regles-de-vie}, qu'un item n'ait qu'une reponse
 * defendable, que chaque devoir ecrit en soit un, que les verbes soient lisibles par un enfant de
 * CE1, {@code TRV-CE1-6} au-dela de l'equilibre des prenoms declares, et qu'un indice soit une
 * methode. Cela reste a la lecture et au jeu de nadia-prof.
 */
class PaquetCe1EmcDroitDevoirTest {

    private static final String PAQUET = "ce1-emc-droit-devoir";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    private static final List<Path> PAQUETS_DU_CP = List.of(
            Path.of("src/main/resources/contenu/cp-emc-droits-enfant.yaml"),
            Path.of("src/main/resources/contenu/cp-emc-regles-de-vie.yaml"));

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1EmcDroitDevoirTest";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String DROIT_VERS_DEVOIR = "droit->devoir";

    private static final String DEVOIR_VERS_DROIT = "devoir->droit";

    /** DDE-CE1-1 : la liste fermee de nadia-prof, ecrite sans accent comme dans son arbitrage. */
    private static final Map<String, String> COUPLES_DE_NADIA = Map.of(
            "1", "apprendre / laisser les autres apprendre",
            "2", "etre respecte / respecter les autres",
            "3", "parler / ecouter quand un autre parle",
            "4", "etre en securite / ne mettre personne en danger");

    private static final Pattern LIGNE_DE_COUPLE = Pattern.compile("^#\\s+couple (\\d) : (.+)$");

    private static final Pattern LIGNE_DE_FORMULATIONS =
            Pattern.compile("^#\\s+couple (\\d) (droit|devoir) : (.+)$");

    private static final Pattern LIGNE_DE_FAMILLE = Pattern.compile("^#\\s+famille ([a-z]) : (.+)$");

    private static final Pattern DECLARATION_DE_COUPLE =
            Pattern.compile("DDE-CE1-1 couple (\\d) : (droit->devoir|devoir->droit)");

    private static final Pattern DECLARATION_DE_FAMILLES = Pattern.compile("TRV-CE1-2 : ([a-z]), ([a-z])");

    /** Les familles que chaque sens admet pour ses distracteurs. */
    private static final Map<String, Set<String>> FAMILLES_ADMISES = Map.of(
            DROIT_VERS_DEVOIR, Set.of("a", "b", "c"),
            DEVOIR_VERS_DROIT, Set.of("b", "c", "f"));

    /** Chaque famille declaree sert au moins ce nombre d'items : sans cela, c'est un decor. */
    private static final int ITEMS_PAR_FAMILLE = 3;

    /** DDE-CE1-2 : un droit ne se merite pas. Aucun de ces mots dans ce que l'enfant lit. */
    private static final Pattern DROIT_CONDITIONNEL = Pattern.compile(
            "(?<!\\p{L})(gagn|mérit|perd|retir|enlev|priv|récompens|condition|échange|retour|sinon)", FLAGS);

    /** EMC-1 : l'enonce et les propositions ne s'adressent pas a l'enfant. */
    private static final Pattern DEUXIEME_PERSONNE =
            Pattern.compile("(?<!\\p{L})(tu|te|toi|vous|votre|vos)(?!\\p{L})", FLAGS);

    /** EMC-1 : aucun mot du foyer ni possessif de deuxieme personne. */
    private static final Pattern MOTS_DU_FOYER = Pattern.compile(
            "(?<!\\p{L})(ton|ta|tes|tien|tienne|tiens|tiennes|chez|maison)(?!\\p{L})", FLAGS);

    private static final Pattern TERMINAISON_DE_PHRASE = Pattern.compile("[.?!](?:[\\s\\u00A0]+|$)");

    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!][\\s\\u00A0]+\\p{Lu}", FLAGS);

    /** TYP-1 (ADR-0067) : l'espace avant un signe double est insecable. */
    private static final Pattern ESPACE_ORDINAIRE_AVANT_SIGNE_DOUBLE = Pattern.compile("[^\\u00A0] [?:;!]");

    /** Aucune forme typographique ne designe la bonne reponse : ni chevrons ni guillemets. */
    private static final Pattern CITATION = Pattern.compile("[«»\"“”]");

    /** Pli a 744 x 551 (mesure d'ines-design, LP4) : trois lignes d'enonce, une ligne d'indice. */
    private static final int CARACTERES_D_UN_ENONCE_DE_TROIS_LIGNES = 112;

    private static final int CARACTERES_D_UN_INDICE_D_UNE_LIGNE = 46;

    /** Mots qui reviennent dans la formule de toute question du paquet : ils ne comptent pas comme repris. */
    private static final Set<String> MOTS_DE_LA_FORMULE = Set.of(
            "droit", "devoir", "quel", "élèves", "élève", "autres", "autre", "envers", "ainsi", "protégé", "quand");

    private static final Pattern NEGATION = Pattern.compile(
            "(?<!\\p{L})(ne|pas|jamais|aucun|aucune|personne|rien)(?!\\p{L})|(?<!\\p{L})n'|(?<!\\p{L})sans(?!\\p{L})",
            FLAGS);

    private static final Pattern PRONOM_COMPLEMENT_EN_TETE =
            Pattern.compile("^(l'|(le|la|lui|leur|les)(?!\\p{L}))", FLAGS);

    /** Les trois proprietes de forme qu'aucune bonne reponse ne porte seule. */
    private static final Map<String, BiPredicate<String, String>> PROPRIETES_DE_FORME = new LinkedHashMap<>();

    static {
        PROPRIETES_DE_FORME.put("reprend un mot plein de l'énoncé",
                (enonce, proposition) -> motsPleins(enonce).stream().anyMatch(motsPleins(proposition)::contains));
        PROPRIETES_DE_FORME.put("porte une négation",
                (enonce, proposition) -> NEGATION.matcher(proposition).find());
        PROPRIETES_DE_FORME.put("commence par un pronom complément",
                (enonce, proposition) -> PRONOM_COMPLEMENT_EN_TETE.matcher(proposition).find());
    }

    /**
     * TRV-CE1-6 : le genre de chaque prenom du paquet, declaration d'auteur faute de lexique de
     * prenoms dans ce depot. Ce que la clause prouve est l'equilibre de cette table, jamais qu'elle
     * dit vrai.
     */
    private static final Map<String, String> GENRE_DES_PRENOMS = Map.ofEntries(
            Map.entry("Lina", "f"), Map.entry("Zoé", "f"), Map.entry("Mila", "f"),
            Map.entry("Aya", "f"), Map.entry("Jade", "f"), Map.entry("Inès", "f"),
            Map.entry("Maya", "f"), Map.entry("Omar", "m"), Map.entry("Tom", "m"),
            Map.entry("Samir", "m"), Map.entry("Enzo", "m"), Map.entry("Hugo", "m"),
            Map.entry("Noé", "m"), Map.entry("Adam", "m"), Map.entry("Léo", "m"));

    /** Un item tel que le YAML le porte, avec les commentaires qui le precedent. */
    private record ItemLu(String enonce, String bonneReponse, List<String> distracteurs, String indice,
                          Set<String> cles, List<String> commentaires) {

        List<String> propositions() {
            List<String> propositions = new ArrayList<>(distracteurs);
            propositions.add(bonneReponse);
            return propositions;
        }

        /** Ce que l'enfant lit : enonce, propositions et indice. */
        List<String> champsLus() {
            List<String> champs = new ArrayList<>(propositions());
            champs.add(enonce);
            champs.add(indice);
            return champs;
        }

        List<Matcher> declarationsDeCouple() {
            return commentaires.stream().map(DECLARATION_DE_COUPLE::matcher).filter(Matcher::find).toList();
        }

        String couple() {
            List<Matcher> declarations = declarationsDeCouple();
            assertThat(declarations)
                    .as("DDE-CE1-1 : '%s' déclare exactement un couple et un sens", enonce).hasSize(1);
            return declarations.get(0).group(1);
        }

        String sens() {
            couple();
            return declarationsDeCouple().get(0).group(2);
        }

        List<String> familles() {
            List<Matcher> declarations = commentaires.stream().map(DECLARATION_DE_FAMILLES::matcher)
                    .filter(Matcher::find).toList();
            assertThat(declarations)
                    .as("TRV-CE1-2 : '%s' déclare exactement une paire de familles de distracteurs", enonce)
                    .hasSize(1);
            return List.of(declarations.get(0).group(1), declarations.get(0).group(2));
        }
    }

    /** L'en-tete du paquet : couples, formulations par couple et par cote, familles de distracteurs. */
    private record Entete(Map<String, String> couples, Map<String, List<String>> formulations,
                          Map<String, List<String>> familles) {

        List<String> formulations(String couple, String cote) {
            return formulations.getOrDefault(couple + " " + cote, List.of());
        }

        Set<String> toutesLesFormulations() {
            Set<String> toutes = new HashSet<>();
            formulations.values().forEach(toutes::addAll);
            return toutes;
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1EmcDroitDevoirTest.class.getResourceAsStream(chemin)) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les commentaires indentes de deux espaces qui precedent chaque ligne {@code - enonce:}. */
    private static List<List<String>> commentairesDesItems(String contenu) {
        List<List<String>> blocs = new ArrayList<>();
        List<String> courant = new ArrayList<>();
        for (String ligne : contenu.split("\\R")) {
            if (ligne.startsWith("  # ")) {
                courant.add(ligne.substring(4).strip());
            } else if (ligne.startsWith("  - enonce:")) {
                blocs.add(courant);
                courant = new ArrayList<>();
            }
        }
        return blocs;
    }

    private static List<ItemLu> lus(String contenu) {
        Map<?, ?> corps = new Yaml().load(contenu);
        List<?> items = (List<?>) corps.get("items");
        List<List<String>> commentaires = commentairesDesItems(contenu);
        assertThat(commentaires).as("un bloc de commentaires par item").hasSameSizeAs(items);
        List<ItemLu> lus = new ArrayList<>();
        for (int rang = 0; rang < items.size(); rang++) {
            Map<?, ?> champs = (Map<?, ?>) items.get(rang);
            List<String> distracteurs = new ArrayList<>();
            for (Object distracteur : (List<?>) champs.get("distracteurs")) {
                distracteurs.add(String.valueOf(distracteur));
            }
            Set<String> cles = new TreeSet<>();
            champs.keySet().forEach(cle -> cles.add(String.valueOf(cle)));
            lus.add(new ItemLu(String.valueOf(champs.get("enonce")),
                    String.valueOf(champs.get("bonneReponse")), distracteurs,
                    String.valueOf(champs.get("indice")), cles, commentaires.get(rang)));
        }
        return lus;
    }

    private static List<String> separees(String liste) {
        return Arrays.stream(liste.split("\\|")).map(String::strip).filter(texte -> !texte.isEmpty()).toList();
    }

    private static Entete entete(String contenu) {
        Map<String, String> couples = new TreeMap<>();
        Map<String, List<String>> formulations = new TreeMap<>();
        Map<String, List<String>> familles = new TreeMap<>();
        for (String ligne : contenu.split("\\R")) {
            Matcher couple = LIGNE_DE_COUPLE.matcher(ligne);
            Matcher formulation = LIGNE_DE_FORMULATIONS.matcher(ligne);
            Matcher famille = LIGNE_DE_FAMILLE.matcher(ligne);
            if (couple.matches()) {
                couples.put(couple.group(1), couple.group(2).strip());
            } else if (formulation.matches()) {
                formulations.put(formulation.group(1) + " " + formulation.group(2), separees(formulation.group(3)));
            } else if (famille.matches()) {
                familles.put(famille.group(1), separees(famille.group(2)));
            }
        }
        return new Entete(couples, formulations, familles);
    }

    private static List<ItemLu> items() {
        return lus(lire(FICHIER_CONTENU));
    }

    private static Entete enteteDuPaquet() {
        return entete(lire(FICHIER_CONTENU));
    }

    private static String contenuFautif(String nom) {
        return lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/contenu/" + nom);
    }

    private static List<ItemLu> itemsFautifs() {
        return lus(contenuFautif("ce1-emc-fautif.yaml"));
    }

    private static Entete enteteFautif() {
        return entete(contenuFautif("ce1-emc-fautif.yaml"));
    }

    /** Les items fautifs dont l'enonce porte l'un de ces marqueurs, pour mordre un verificateur a la fois. */
    private static List<ItemLu> fautifs(String... marqueurs) {
        return itemsFautifs().stream()
                .filter(item -> Arrays.stream(marqueurs).anyMatch(item.enonce()::contains)).toList();
    }

    private static int mots(String texte) {
        return (int) Arrays.stream(texte.strip().split("[\\s\\u00A0]+"))
                .filter(token -> token.chars().anyMatch(Character::isLetterOrDigit)).count();
    }

    private static Set<String> motsPleins(String texte) {
        Set<String> racines = new TreeSet<>();
        Matcher mot = Pattern.compile("\\p{L}+", Pattern.UNICODE_CHARACTER_CLASS).matcher(texte);
        while (mot.find()) {
            String minuscule = mot.group().toLowerCase(Locale.FRENCH);
            if (minuscule.length() >= 5 && !MOTS_DE_LA_FORMULE.contains(minuscule)) {
                racines.add(minuscule.substring(0, 5));
            }
        }
        return racines;
    }

    private static boolean nommeLePrenom(String texte, String prenom) {
        return Pattern.compile("(?<!\\p{L})" + Pattern.quote(prenom) + "(?!\\p{L})",
                Pattern.UNICODE_CHARACTER_CLASS).matcher(texte).find();
    }

    private static boolean nommeUnPersonnage(String texte) {
        return GENRE_DES_PRENOMS.keySet().stream().anyMatch(prenom -> nommeLePrenom(texte, prenom));
    }

    private static String derniereQuestion(ItemLu item) {
        String[] phrases = item.enonce().split("(?<=[.?!])[\\s\\u00A0]+");
        return phrases[phrases.length - 1];
    }

    /** DDE-CE1-1 : la liste des couples est exactement celle de nadia-prof. */
    private static void verifierLesCouples(Entete entete) {
        assertThat(entete.couples())
                .as("DDE-CE1-1 : la liste fermée des couples du commentaire d'en-tête n'est pas celle de "
                        + "nadia-prof (quatre couples, ses mots)")
                .isEqualTo(COUPLES_DE_NADIA);
    }

    /** DDE-CE1-1 : chaque couple a, des deux cotes, au moins trois formulations, et aucune n'est partagee. */
    private static void verifierLesFormulations(Entete entete) {
        Set<String> vues = new HashSet<>();
        for (String couple : COUPLES_DE_NADIA.keySet()) {
            for (String cote : List.of("droit", "devoir")) {
                List<String> formulations = entete.formulations(couple, cote);
                assertThat(formulations.size())
                        .as("DDE-CE1-1 : le couple %s porte %d formulations du côté %s, au moins trois sont "
                                + "écrites dans le commentaire d'en-tête (formulations)", couple,
                                formulations.size(), cote)
                        .isGreaterThanOrEqualTo(3);
                for (String formulation : formulations) {
                    assertThat(vues.add(formulation))
                            .as("DDE-CE1-1 : la formulation « %s » est écrite deux fois dans les formulations "
                                    + "des couples", formulation)
                            .isTrue();
                }
            }
        }
    }

    /**
     * DDE-CE1-1 : chaque item declare un couple de la liste et un sens, sa question demande le cote que le
     * sens annonce, sa bonne reponse est une formulation de ce couple et de ce cote et d'aucun autre.
     */
    private static void verifierLesDeclarations(List<ItemLu> items, Entete entete) {
        for (ItemLu item : items) {
            String couple = item.couple();
            String sens = item.sens();
            assertThat(entete.couples())
                    .as("DDE-CE1-1 : le couple %s de '%s' n'est pas dans la liste fermée", couple, item.enonce())
                    .containsKey(couple);
            String demande = sens.equals(DROIT_VERS_DEVOIR) ? "devoir" : "droit";
            assertThat(Pattern.compile("(?<!\\p{L})quel " + demande + "(?!\\p{L})", FLAGS)
                    .matcher(derniereQuestion(item)).find())
                    .as("DDE-CE1-1 : la question de '%s' ne demande pas le %s que le sens %s annonce",
                            item.enonce(), demande, sens)
                    .isTrue();
            assertThat(entete.formulations(couple, demande))
                    .as("DDE-CE1-1 : la bonne réponse « %s » de '%s' n'est pas une formulation du %s du couple %s",
                            item.bonneReponse(), item.enonce(), demande, couple)
                    .contains(item.bonneReponse());
        }
    }

    /** FER-3 : les deux sens sont servis, chacun d'au moins trois items, et chaque couple l'est dans les deux. */
    private static void verifierLesDeuxSens(List<ItemLu> items) {
        Map<String, Long> parSens = new TreeMap<>();
        Map<String, Long> parCoupleEtSens = new TreeMap<>();
        for (ItemLu item : items) {
            parSens.merge(item.sens(), 1L, Long::sum);
            parCoupleEtSens.merge(item.couple() + " " + item.sens(), 1L, Long::sum);
        }
        for (String sens : List.of(DROIT_VERS_DEVOIR, DEVOIR_VERS_DROIT)) {
            assertThat(parSens.getOrDefault(sens, 0L))
                    .as("FER-3 : le sens %s compte au moins trois items (%s)", sens, parSens)
                    .isGreaterThanOrEqualTo(3);
        }
        for (String couple : COUPLES_DE_NADIA.keySet()) {
            for (String sens : List.of(DROIT_VERS_DEVOIR, DEVOIR_VERS_DROIT)) {
                assertThat(parCoupleEtSens.getOrDefault(couple + " " + sens, 0L))
                        .as("DDE-CE1-1 : le couple %s n'est pas servi dans les deux sens, au moins deux items "
                                + "par sens (%s)", couple, parCoupleEtSens)
                        .isGreaterThanOrEqualTo(2);
            }
        }
    }

    /**
     * TRV-CE1-2 : chaque distracteur est lu dans la famille que l'item declare, dans l'ordre du fichier ;
     * la famille est admise par le sens ; aucun distracteur n'est une formulation d'un couple.
     */
    private static void verifierLesFamilles(List<ItemLu> items, Entete entete) {
        Set<String> formulations = entete.toutesLesFormulations();
        for (ItemLu item : items) {
            List<String> declarees = item.familles();
            for (int rang = 0; rang < item.distracteurs().size(); rang++) {
                String distracteur = item.distracteurs().get(rang);
                assertThat(formulations)
                        .as("DDE-CE1-1 : « %s » (distracteur de '%s') est une formulation d'un couple : un "
                                + "distracteur n'est jamais une réponse défendable d'un autre couple",
                                distracteur, item.enonce())
                        .doesNotContain(distracteur);
                assertThat(entete.familles().getOrDefault(declarees.get(rang), List.of()))
                        .as("TRV-CE1-2 : « %s » (distracteur de '%s') n'est pas dans la famille %s que l'item "
                                + "déclare, ses distracteurs ne sont pas des familles déclarées", distracteur,
                                item.enonce(), declarees.get(rang))
                        .contains(distracteur);
                assertThat(FAMILLES_ADMISES.get(item.sens()))
                        .as("TRV-CE1-2 : '%s' déclare la famille %s, que son sens %s n'admet pas", item.enonce(),
                                declarees.get(rang), item.sens())
                        .contains(declarees.get(rang));
            }
            assertThat(item.distracteurs().size()).isEqualTo(2);
        }
    }

    /** TRV-CE1-2 : chaque famille du commentaire sert au moins trois items, aucune n'est un decor. */
    private static void verifierQueChaqueFamilleSert(List<ItemLu> items, Entete entete) {
        Map<String, Long> servies = new TreeMap<>();
        for (ItemLu item : items) {
            item.familles().forEach(famille -> servies.merge(famille, 1L, Long::sum));
        }
        for (String famille : entete.familles().keySet()) {
            assertThat(servies.getOrDefault(famille, 0L))
                    .as("TRV-CE1-2 : la famille %s sert moins de %d items, c'est un décor (%s)", famille,
                            ITEMS_PAR_FAMILLE, servies)
                    .isGreaterThanOrEqualTo(ITEMS_PAR_FAMILLE);
        }
    }

    /** Point d'audit commun : aucune propriete de forme n'est portee par la bonne reponse seule. */
    private static void verifierQueLaBonneReponseNEstJamaisSeuleDeSonEspece(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (Map.Entry<String, BiPredicate<String, String>> propriete : PROPRIETES_DE_FORME.entrySet()) {
                boolean delaBonne = propriete.getValue().test(item.enonce(), item.bonneReponse());
                boolean seule = item.distracteurs().stream()
                        .noneMatch(d -> propriete.getValue().test(item.enonce(), d) == delaBonne);
                assertThat(seule)
                        .as("point d'audit commun : dans '%s', la bonne réponse « %s » est la seule proposition à "
                                + "%s (%s), la forme donne la réponse", item.enonce(), item.bonneReponse(),
                                propriete.getKey(), delaBonne ? "elle l'a, les deux autres non"
                                        : "elle ne l'a pas, les deux autres oui")
                        .isFalse();
            }
        }
    }

    /** Point d'audit commun : propositions en minuscule, ni chevrons ni guillemets dans ce que l'enfant lit. */
    private static void verifierLaForme(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String proposition : item.propositions()) {
                assertThat(Character.isLowerCase(proposition.codePointAt(0)))
                        .as("point d'audit commun : « %s » (proposition de '%s') commence par une majuscule, la "
                                + "typographie donne la réponse", proposition, item.enonce())
                        .isTrue();
            }
            for (String champ : item.champsLus()) {
                assertThat(CITATION.matcher(champ).find())
                        .as("point d'audit commun : « %s » porte des chevrons ou des guillemets, la typographie "
                                + "donne la réponse", champ)
                        .isFalse();
            }
        }
    }

    /** DDE-CE1-2 : aucun mot qui fasse d'un droit une recompense ou une consequence. */
    private static void verifierLesRefusDAvance(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                Matcher mot = DROIT_CONDITIONNEL.matcher(champ);
                assertThat(mot.find())
                        .as("DDE-CE1-2 : '%s' fait lire un droit gagné, mérité ou perdu : « %s »", item.enonce(),
                                champ)
                        .isFalse();
            }
        }
    }

    /** NRC-CE1-1 : aucune proposition n'est une proposition de cp-emc-droits-enfant ou de cp-emc-regles-de-vie. */
    private static void verifierQueRienNEstReprisDuCp(List<ItemLu> items, List<Path> paquetsDuCp) {
        Set<String> reprises = new HashSet<>();
        for (Path paquet : paquetsDuCp) {
            for (Object entree : (List<?>) ((Map<?, ?>) new Yaml().load(lire(paquet))).get("items")) {
                Map<?, ?> champs = (Map<?, ?>) entree;
                reprises.add(String.valueOf(champs.get("bonneReponse")).strip().toLowerCase(Locale.FRENCH));
                for (Object distracteur : (List<?>) champs.get("distracteurs")) {
                    reprises.add(String.valueOf(distracteur).strip().toLowerCase(Locale.FRENCH));
                }
            }
        }
        assertThat(reprises.size()).as("NRC-CE1-1 : les paquets du CP sont lus").isGreaterThan(20);
        for (ItemLu item : items) {
            for (String proposition : item.propositions()) {
                assertThat(reprises)
                        .as("NRC-CE1-1 : « %s » (proposition de '%s') est une proposition d'un paquet du CP",
                                proposition, item.enonce())
                        .doesNotContain(proposition.strip().toLowerCase(Locale.FRENCH));
            }
        }
    }

    /** EMC-1 et RSA : un personnage nomme, jamais l'enfant, aucun mot du foyer. */
    private static void verifierQueLeSujetEstUnPersonnageNomme(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(nommeUnPersonnage(item.enonce()))
                    .as("EMC-1 : '%s' ne nomme aucun personnage", item.enonce()).isTrue();
            for (String champ : item.champsLus()) {
                assertThat(MOTS_DU_FOYER.matcher(champ).find())
                        .as("EMC-1 : '%s' fait lire un mot du foyer ou un possessif de l'enfant (« %s »)",
                                item.enonce(), champ)
                        .isFalse();
            }
            List<String> adresses = new ArrayList<>(item.propositions());
            adresses.add(item.enonce());
            for (String champ : adresses) {
                assertThat(DEUXIEME_PERSONNE.matcher(champ).find())
                        .as("EMC-1 : '%s' s'adresse à l'enfant (« %s »)", item.enonce(), champ)
                        .isFalse();
            }
        }
    }

    /** Aucun texte support, aucune voix : seuls ces quatre champs sont servis. */
    private static void verifierLesChampsServis(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(item.cles())
                    .as("TRV-CE1-1 : '%s' ne porte ni texteSupport ni texteAVoixHaute", item.enonce())
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    /** LON-CE1-1 et -2 : un enonce de vingt mots et trois phrases au plus. */
    private static void verifierLesEnonces(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(mots(item.enonce()))
                    .as("LON-CE1-1 : l'énoncé « %s » compte plus de vingt mots", item.enonce())
                    .isLessThanOrEqualTo(20);
            long phrases = TERMINAISON_DE_PHRASE.matcher(item.enonce()).results().count();
            assertThat(phrases)
                    .as("LON-CE1-2 : l'énoncé « %s » compte plus de trois phrases", item.enonce())
                    .isLessThanOrEqualTo(3);
        }
        assertThat(items.stream().map(ItemLu::enonce).distinct().count())
                .as("deux items n'ont pas le même énoncé").isEqualTo(items.size());
    }

    /** LON-CE1-5 : au plus six mots et une seule phrase par proposition ; trois propositions distinctes. */
    private static void verifierLesPropositions(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(item.distracteurs()).as("trois propositions pour '%s' (TRV-CE1-2)", item.enonce())
                    .hasSize(2);
            assertThat(new HashSet<>(item.propositions()))
                    .as("TRV-CE1-2 : trois propositions distinctes pour '%s'", item.enonce()).hasSize(3);
            for (String proposition : item.propositions()) {
                assertThat(mots(proposition))
                        .as("LON-CE1-5 : « %s » (proposition de '%s') compte plus de six mots", proposition,
                                item.enonce())
                        .isLessThanOrEqualTo(6);
                assertThat(FIN_DE_PHRASE.matcher(proposition).find())
                        .as("LON-CE1-5 : « %s » compte plus d'une phrase", proposition).isFalse();
            }
        }
    }

    /** TRV-CE1-5 : un indice de douze mots au plus, qui ne contient aucune proposition de son item. */
    private static void verifierLesIndices(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(mots(item.indice()))
                    .as("TRV-CE1-5 : l'indice « %s » compte plus de douze mots", item.indice())
                    .isLessThanOrEqualTo(12);
            for (String proposition : item.propositions()) {
                assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(proposition) + "(?!\\p{L})", FLAGS)
                        .matcher(item.indice()).find())
                        .as("TRV-CE1-5 : l'indice « %s » contient une proposition de son item : « %s »",
                                item.indice(), proposition)
                        .isFalse();
            }
        }
    }

    /** TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet. */
    private static void verifierQueLesIndicesDifferent(List<ItemLu> items) {
        assertThat(items.stream().map(ItemLu::indice).distinct().count())
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet").isGreaterThan(1);
    }

    /** TRV-CE1-2 : la bonne reponse est strictement la plus longue, en caracteres, dans la moitie des items au plus. */
    private static void verifierLaPlusLongue(List<ItemLu> items) {
        long plusLongue = items.stream().filter(item -> item.distracteurs().stream()
                .allMatch(distracteur -> item.bonneReponse().length() > distracteur.length())).count();
        assertThat(2 * plusLongue)
                .as("TRV-CE1-2 : %d items où la bonne réponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(items.size());
    }

    /** TYP-1 : espace insecable avant un signe double, dans chaque champ lu. */
    private static void verifierLaTypographie(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                assertThat(ESPACE_ORDINAIRE_AVANT_SIGNE_DOUBLE.matcher(champ).find())
                        .as("TYP-1 : « %s » porte une espace ordinaire avant ?, :, ; ou !", champ).isFalse();
            }
        }
    }

    /** Pli a 744 x 551 : enonce de trois lignes au plus, indice d'une ligne, sous les seuils derives de la mesure. */
    private static void verifierLePli(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(item.enonce().length())
                    .as("pli : l'énoncé « %s » compte plus de %d caractères, trois lignes au plus", item.enonce(),
                            CARACTERES_D_UN_ENONCE_DE_TROIS_LIGNES)
                    .isLessThanOrEqualTo(CARACTERES_D_UN_ENONCE_DE_TROIS_LIGNES);
            assertThat(item.indice().length())
                    .as("pli : l'indice « %s » compte plus de %d caractères, une ligne au plus", item.indice(),
                            CARACTERES_D_UN_INDICE_D_UNE_LIGNE)
                    .isLessThanOrEqualTo(CARACTERES_D_UN_INDICE_D_UNE_LIGNE);
        }
    }

    /** TRV-CE1-6 : prenoms equilibres a un pres, et chaque prenom declare figure dans un item. */
    private static void verifierLEquilibreDesPrenoms(List<ItemLu> items) {
        Set<String> rencontres = new TreeSet<>();
        for (ItemLu item : items) {
            for (String prenom : GENRE_DES_PRENOMS.keySet()) {
                if (item.champsLus().stream().anyMatch(champ -> nommeLePrenom(champ, prenom))) {
                    rencontres.add(prenom);
                }
            }
        }
        long feminins = rencontres.stream().filter(p -> GENRE_DES_PRENOMS.get(p).equals("f")).count();
        long masculins = rencontres.size() - feminins;
        assertThat(Math.abs(feminins - masculins))
                .as("TRV-CE1-6 : %d prénoms féminins distincts pour %d masculins, un de plus au plus",
                        feminins, masculins)
                .isLessThanOrEqualTo(1);
    }

    /**
     * L'entree du catalogue qui sert le paquet est unique, de la mecanique bandit-manchot, a dix
     * manches (DEC-0121) ; son titre ne porte pas le niveau (ADR-0078) et l'espace avant un signe
     * double est insecable (ADR-0067).
     */
    private static void verifierLEntreeDuCatalogue(String catalogue) {
        Map<?, ?> corps = new Yaml().load(catalogue);
        List<Map<?, ?>> entrees = new ArrayList<>();
        for (Object entree : (List<?>) corps.get("jeux")) {
            Map<?, ?> jeu = (Map<?, ?>) entree;
            if (PAQUET.equals(jeu.get("paquet"))) {
                entrees.add(jeu);
            }
        }
        assertThat(entrees).as("une seule entrée du catalogue sert le paquet %s", PAQUET).hasSize(1);
        Map<?, ?> entree = entrees.get(0);
        String titre = String.valueOf(entree.get("titre"));
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(entree.get("mecanique")).as("la mécanique est bandit-manchot")
                .isEqualTo("bandit-manchot");
        assertThat(entree.get("nombreDeManches")).as("DEC-0121 : nombreDeManches vaut 10")
                .isEqualTo(10);
        assertThat(titre).as("le titre ne porte pas le niveau (ADR-0078)")
                .doesNotContainPattern("(?i)\\b(CP|CE1|CE2|CM1|CM2)\\b");
        assertThat(titre + " " + consigne)
                .as("l'espace avant ?, :, ; ou ! d'un titre ou d'une consigne est insécable (U+00A0)")
                .doesNotContainPattern("[^\\u00A0] [?:;!]");
    }

    private static List<Question> itemsDuChargeur() {
        ContentSource source = new YamlContentPackLoader().chargerPaquet(PAQUET);
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = source.next(hasard, dejaVus).orElse(null); item != null;
                item = source.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    // ----- le paquet et son entree de catalogue

    @Test
    void adr0081CePaquetDeclareEmcCe103EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("EMC-CE1-03"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Enseignement moral et civique");
    }

    @Test
    void lIdentifiantDuPaquetEstLeNomDeSonFichier() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("id")).as("le chargeur ne compare pas le champ id au nom de fichier")
                .isEqualTo(PAQUET);
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueATitreEtConsigneSansFaute() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    @Test
    void eb1LeChargeurLitTousLesItemsDuYamlEtIlsDepassentLePlancherDeVingt() {
        assertThat(itemsDuChargeur()).hasSameSizeAs(items());
        assertThat(items().size()).as("EB1 : au moins vingt items, FER-2 : plus que les dix manches")
                .isGreaterThanOrEqualTo(20);
    }

    // ----- DDE-CE1-1 et DDE-CE1-2

    @Test
    void dde1LaListeDesCouplesEstExactementCelleDeNadiaProf() {
        verifierLesCouples(enteteDuPaquet());
    }

    @Test
    void dde1ChaqueCoupleEcritSesFormulationsDesDeuxCotesSansEnPartagerAucune() {
        verifierLesFormulations(enteteDuPaquet());
    }

    @Test
    void dde1ChaqueItemDeclareUnCoupleEtSaBonneReponseEstUneFormulationDeCeCoupleEtDeCeCote() {
        verifierLesDeclarations(items(), enteteDuPaquet());
    }

    @Test
    void dde1ChaqueCoupleEstServiDansLesDeuxSensEtLesDeuxNotionsSontEnBonneReponse() {
        verifierLesDeuxSens(items());
    }

    @Test
    void dde1UnDistracteurNEstJamaisUneFormulationDUnCoupleEtVientDeSaFamilleDeclaree() {
        verifierLesFamilles(items(), enteteDuPaquet());
    }

    @Test
    void dde1ChaqueFamilleDeDistracteursServAuMoinsTroisItems() {
        verifierQueChaqueFamilleSert(items(), enteteDuPaquet());
    }

    @Test
    void dde2AucunItemNePresenteUnDroitCommeGagneOuPerdu() {
        verifierLesRefusDAvance(items());
    }

    @Test
    void nrc1AucunePropositionNEstRepriseDuCp() {
        verifierQueRienNEstReprisDuCp(items(), PAQUETS_DU_CP);
    }

    @Test
    void emc1ChaqueSujetEstUnPersonnageNommeEtJamaisLEnfant() {
        verifierQueLeSujetEstUnPersonnageNomme(items());
    }

    // ----- point d'audit commun : aucune forme ne donne la reponse

    @Test
    void auditLaBonneReponseNEstJamaisLaSeuleADeSesTroisProprietesDeForme() {
        verifierQueLaBonneReponseNEstJamaisSeuleDeSonEspece(items());
    }

    @Test
    void auditAucunePropositionNeCommenceParUneMajusculeNiNePorteDeChevrons() {
        verifierLaForme(items());
    }

    // ----- conditions transverses

    @Test
    void eb5ChaqueItemNePorteQueSesQuatreChampsSansTexteSupportNiVoix() {
        verifierLesChampsServis(items());
    }

    @Test
    void lon1LesEnoncesTiennentEnVingtMotsEtTroisPhrases() {
        verifierLesEnonces(items());
    }

    @Test
    void eb6ChaquePropositionTientEnSixMotsEtUneSeulePhraseEtChaqueItemEnPorteTrois() {
        verifierLesPropositions(items());
    }

    @Test
    void eb6ChaqueIndiceTientEnDouzeMotsEtNeContientAucuneProposition() {
        verifierLesIndices(items());
    }

    @Test
    void eb6LIndiceNEstPasIdentiqueSurToutLePaquet() {
        verifierQueLesIndicesDifferent(items());
    }

    @Test
    void eb7LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        verifierLaPlusLongue(items());
    }

    @Test
    void typ1LEspaceAvantUnSigneDoubleEstInsecable() {
        verifierLaTypographie(items());
    }

    @Test
    void pliLEnonceTientEnTroisLignesEtLIndiceEnUneSousLesSeuilsMesures() {
        verifierLePli(items());
    }

    @Test
    void trv6LesPrenomsDistinctsSontEquilibresEnGenreAUnPresEtTousLesPrenomsDeclaresServent() {
        List<ItemLu> items = items();
        verifierLEquilibreDesPrenoms(items);
        Set<String> servis = new TreeSet<>();
        for (ItemLu item : items) {
            GENRE_DES_PRENOMS.keySet().stream()
                    .filter(prenom -> item.champsLus().stream().anyMatch(champ -> nommeLePrenom(champ, prenom)))
                    .forEach(servis::add);
        }
        assertThat(servis).as("chaque prénom de la table figure dans un item : une table qui "
                + "pourrit ne prouve plus rien").containsExactlyInAnyOrderElementsOf(GENRE_DES_PRENOMS.keySet());
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurUneListeDeCouplesQuiNEnPorteQueTrois() {
        Entete entete = entete(contenuFautif("entete-trois-couples.yaml"));

        assertThatThrownBy(() -> verifierLesCouples(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DDE-CE1-1")
                .hasMessageContaining("pas celle de nadia-prof");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneListeDeCouplesQuiEnPorteCinq() {
        Entete entete = entete(contenuFautif("entete-cinq-couples.yaml"));

        assertThatThrownBy(() -> verifierLesCouples(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("pas celle de nadia-prof");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnCoupleReecrit() {
        Entete entete = entete(contenuFautif("entete-couple-reecrit.yaml"));

        assertThatThrownBy(() -> verifierLesCouples(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("pas celle de nadia-prof");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnCoteSansFormulation() {
        Entete entete = entete(contenuFautif("entete-formulations-manquantes.yaml"));

        assertThatThrownBy(() -> verifierLesFormulations(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DDE-CE1-1")
                .hasMessageContaining("formulations");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseDuMauvaisCote() {
        List<ItemLu> items = fautifs("(sonde 01)");

        assertThatThrownBy(() -> verifierLesDeclarations(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DDE-CE1-1")
                .hasMessageContaining("n'est pas une formulation du devoir du couple 1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneQuestionQuiDemandeLAutreCote() {
        List<ItemLu> items = fautifs("(sonde 02)");

        assertThatThrownBy(() -> verifierLesDeclarations(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne demande pas le devoir que le sens");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseDUnAutreCouple() {
        List<ItemLu> items = fautifs("(sonde 03)");

        assertThatThrownBy(() -> verifierLesDeclarations(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas une formulation du devoir du couple 1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansDeclarationDeCouple() {
        List<ItemLu> items = fautifs("(sonde 04)");

        assertThatThrownBy(() -> verifierLesDeclarations(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("déclare exactement un couple");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnCoupleHorsDeLaListe() {
        List<ItemLu> items = fautifs("(sonde 05)");

        assertThatThrownBy(() -> verifierLesDeclarations(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas dans la liste fermée");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnSensServiParMoinsDeTroisItems() {
        List<ItemLu> items = itemsFautifs().stream()
                .filter(item -> !item.enonce().contains("(sonde 04)") && !item.enonce().contains("(sonde 43)")
                        && !item.enonce().contains("(sonde 44)"))
                .toList();

        assertThatThrownBy(() -> verifierLesDeuxSens(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesCouplesServisDansUnSeulSens() {
        List<ItemLu> items = fautifs("(sonde 01)", "(sonde 03)", "(sonde 06)", "(sonde 07)", "(sonde 40)",
                "(sonde 43)", "(sonde 44)");

        assertThatThrownBy(() -> verifierLesDeuxSens(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas servi dans les deux sens");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurQuiEstUneFormulationDUnCouple() {
        List<ItemLu> items = fautifs("(sonde 06)");

        assertThatThrownBy(() -> verifierLesFamilles(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("formulation d'un couple");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurHorsDeLaFamilleDeclaree() {
        List<ItemLu> items = fautifs("(sonde 07)");

        assertThatThrownBy(() -> verifierLesFamilles(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ses distracteurs ne sont pas des familles déclarées");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleQueLeSensNAdmetPas() {
        List<ItemLu> items = fautifs("(sonde 08)");

        assertThatThrownBy(() -> verifierLesFamilles(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("que son sens droit->devoir n'admet pas");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleQuiNeSertQueDeuxItems() {
        List<ItemLu> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueChaqueFamilleSert(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("c'est un décor");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTroisSondesDeDroitGagneOuPerdu() {
        for (String marqueur : List.of("(sonde 09)", "(sonde 10)", "(sonde 11)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierLesRefusDAvance(items))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("DDE-CE1-2");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionRepriseDuCp() {
        for (String marqueur : List.of("(sonde 12)", "(sonde 13)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierQueRienNEstReprisDuCp(items, PAQUETS_DU_CP))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("NRC-CE1-1")
                    .hasMessageContaining("paquet du CP");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseSeuleAReprendreUnMotDeLEnonce() {
        for (String marqueur : List.of("(sonde 14)", "(sonde 17)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierQueLaBonneReponseNEstJamaisSeuleDeSonEspece(items))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("reprend un mot plein de l'énoncé")
                    .hasMessageContaining("la forme donne la réponse");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseSeuleANegation() {
        List<ItemLu> items = fautifs("(sonde 15)");

        assertThatThrownBy(() -> verifierQueLaBonneReponseNEstJamaisSeuleDeSonEspece(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("porte une négation");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseSeuleAOuvrirParUnPronom() {
        List<ItemLu> items = fautifs("(sonde 16)");

        assertThatThrownBy(() -> verifierQueLaBonneReponseNEstJamaisSeuleDeSonEspece(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("commence par un pronom complément");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneMajusculeEtDesChevrons() {
        for (String marqueur : List.of("(sonde 18)", "(sonde 19)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierLaForme(items))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("la typographie donne la réponse");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        List<ItemLu> items = fautifs("(sonde 20)");

        assertThatThrownBy(() -> verifierLesChampsServis(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeVingtEtUnMots() {
        List<ItemLu> items = fautifs("(sonde 21)");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1")
                .hasMessageContaining("vingt mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeQuatrePhrases() {
        List<ItemLu> items = fautifs("(sonde 22)");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-2")
                .hasMessageContaining("trois phrases");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionDeSeptMots() {
        List<ItemLu> items = fautifs("(sonde 23)");

        assertThatThrownBy(() -> verifierLesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5")
                .hasMessageContaining("six mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeTreizeMots() {
        List<ItemLu> items = fautifs("(sonde 24)");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("douze mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<ItemLu> items = fautifs("(sonde 25)");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("contient une proposition");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceIdentiqueSurToutLePaquet() {
        List<ItemLu> items = fautifs("(sonde 26)", "(sonde 27)");

        assertThat(items).hasSize(2);
        assertThatThrownBy(() -> verifierQueLesIndicesDifferent(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("identique");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseToujoursLaPlusLongue() {
        List<ItemLu> items = fautifs("(sonde 28)", "(sonde 29)");

        assertThat(items).hasSize(2);
        assertThatThrownBy(() -> verifierLaPlusLongue(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("strictement la plus longue");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEspaceOrdinaireAvantUnPointDInterrogation() {
        List<ItemLu> items = fautifs("(sonde 30)");

        assertThatThrownBy(() -> verifierLaTypographie(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1")
                .hasMessageContaining("espace ordinaire");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuFoyer() {
        List<ItemLu> items = fautifs("(sonde 31)");

        assertThatThrownBy(() -> verifierQueLeSujetEstUnPersonnageNomme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-1")
                .hasMessageContaining("mot du foyer");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceQuiSAdresseALEnfant() {
        List<ItemLu> items = fautifs("(sonde 32)");

        assertThatThrownBy(() -> verifierQueLeSujetEstUnPersonnageNomme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceSansPersonnageNomme() {
        List<ItemLu> items = fautifs("(sonde 33)");

        assertThatThrownBy(() -> verifierQueLeSujetEstUnPersonnageNomme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne nomme aucun personnage");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<ItemLu> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("féminins distincts");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceTropLongPourLePli() {
        List<ItemLu> items = fautifs("(sonde 41)");

        assertThatThrownBy(() -> verifierLePli(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("trois lignes au plus");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceTropLongPourLePli() {
        List<ItemLu> items = fautifs("(sonde 42)");

        assertThatThrownBy(() -> verifierLePli(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une ligne au plus");
    }

    @Test
    void leCatalogueFautifDontLeTitrePorteLeNiveauEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-titre-avec-niveau.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("niveau");
    }

    @Test
    void leCatalogueFautifDontLEspaceDevantUnSigneDoubleEstOrdinaireEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-espace-ordinaire.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("insécable");
    }

    @Test
    void leCatalogueFautifQuiSertLePaquetDeuxFoisEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-deux-entrees.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une seule entrée");
    }

    @Test
    void leCatalogueFautifQuiNePorteQueCinqManchesEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-cinq-manches.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nombreDeManches");
    }
}
