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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-EMC-CE1-04 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-emc-interet-commun}, ligne {@code EMC-CE1-04} - distinguer, dans une situation
 * simple, ce qui sert l'interet d'une seule personne de ce qui sert l'interet de tous. Conditions de
 * nadia-prof ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section
 * 4.4 : {@code INT-CE1-1}, {@code INT-CE1-2} ; section 2 : {@code TRV-CE1-1}, {@code -2}, {@code -5},
 * {@code -6}, {@code LON-CE1-1}, {@code -2}, {@code -5}, {@code FER-1}, {@code EMC-1}, {@code EMC-6}).
 *
 * <p><b>Comment ce test recompte {@code INT-CE1-2}.</b> Les deux formules de nadia-prof sont ecrites
 * ici : <i>pour une seule personne</i> et <i>pour toute la classe</i>. Chaque item les porte toutes
 * les deux, sa bonne reponse est l'une des deux, et sa troisieme proposition est un nombre d'eleves
 * lu dans la famille {@code b} du commentaire d'en-tete. Les deux expressions que l'arbitrage interdit
 * a l'enfant de lire sont cherchees dans chaque champ lu <b>et dans le fichier entier</b>, commentaires
 * compris : la commande de la story porte sur le fichier.
 *
 * <p><b>Ce que ce test garde de {@code INT-CE1-1}</b> : la portee declaree de chaque scene (une seule
 * personne, tous) est celle de sa bonne reponse ; l'appartenance de l'objet est declaree a part
 * (personnel ou partage) et la scene la dit par un mot d'une liste fermee ecrite ici (un possessif
 * pour un objet personnel, un lieu ou un objet partage pour un objet partage, jamais un possessif).
 * <b>L'appartenance ne dit pas la portee</b> (fiche de conformite, motif M1) : dans chaque portee, au
 * moins un tiers des scenes croise les deux, et le test le recompte sur le texte des scenes, en
 * appliquant la regle de surface qui repondrait <i>une seule personne</i> sur un possessif et
 * <i>toute la classe</i> sinon. Aucune scene ne met un personnage en balance avec le groupe
 * (conjonction d'opposition) ni ne juge celui qui veut quelque chose pour lui (mots de jugement).
 * <b>Ce qu'il ne garde pas</b> : qu'une scene n'ait qu'une lecture defendable, qu'une scene ne
 * fasse honte a personne par son sens, et que les mots de la liste fermee disent vrai. C'est la
 * lecture de nadia-prof, seule garde de {@code INT-CE1-1} (aucune mesure).
 *
 * <p><b>Le point d'audit commun</b> (aucune forme ne donne la reponse) est garde plus fort que dans les
 * paquets voisins : aucune proposition ne porte de negation, aucune ne commence par un pronom
 * complement, et aucune ne reprend un mot plein de la scene ni de l'indice. Les trois propositions
 * etant des formules fermees, la propriete utile est que la scene ne les echo jamais. Un nombre
 * d'eleves est servi avec les deux bonnes reponses (ADR-0086, clause 1).
 *
 * <p>Ce que ce test ne lit pas : {@code NRC-CE1-1} au-dela de l'egalite des textes avec six paquets
 * (trois de CP, trois de CM1), le sujet d'une scene, que les verbes soient lisibles par un enfant de
 * CE1 et qu'un indice soit une methode. Cela reste a la lecture et au jeu de nadia-prof.
 */
class PaquetCe1EmcInteretCommunTest {

    private static final String PAQUET = "ce1-emc-interet-commun";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    private static final List<Path> PAQUETS_VOISINS = List.of(
            Path.of("src/main/resources/contenu/cp-emc-droits-enfant.yaml"),
            Path.of("src/main/resources/contenu/cp-emc-regles-de-vie.yaml"),
            Path.of("src/main/resources/contenu/cp-emc-regle-souhait-loi.yaml"),
            Path.of("src/main/resources/contenu/cm1-emc-regle-loi.yaml"),
            Path.of("src/main/resources/contenu/cm1-emc-droit-devoir.yaml"),
            Path.of("src/main/resources/contenu/cm1-emc-regles-vie-collective.yaml"));

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1EmcInteretCommunTest";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    /** INT-CE1-2 : les deux formules de nadia-prof, ses mots. */
    private static final String UNE_SEULE_PERSONNE = "pour une seule personne";

    private static final String TOUTE_LA_CLASSE = "pour toute la classe";

    private static final Set<String> FORMULES = Set.of(UNE_SEULE_PERSONNE, TOUTE_LA_CLASSE);

    private static final String PORTEE_UN = "un";

    private static final String PORTEE_TOUS = "tous";

    /** La question de toute scene du paquet, avec l'espace insecable de TYP-1. */
    private static final String QUESTION = "Pour qui est-ce utile ?";

    /**
     * INT-CE1-2 : ce que l'enfant ne lit pas, dans aucun champ et dans aucune ligne du fichier. Le pluriel
     * et le trait d'union sont le meme terme : biens communs, bien-commun, intérêts généraux. La forme
     * sans accent n'y est pas : un texte d'enfant sans accents viole deja DEC-0002.
     */
    private static final Pattern MOTS_INTERDITS = Pattern.compile(
            "(?<!\\p{L})(intérêts?[\\s\\u2010\\u2011-]+(général|généraux)"
                    + "|biens?[\\s\\u2010\\u2011-]+communs?)(?!\\p{L})", FLAGS);

    /**
     * L'identifiant du paquet voisin, cite par l'en-tete : un nom de fichier, pas un mot lu par l'enfant.
     * Il est retire de la lecture du fichier entier, et de rien d'autre.
     */
    private static final String IDENTIFIANT_DU_PAQUET_DES_BIENS = "ce1-emc-biens-communs";

    /** INT-CE1-1 : une scene ne met pas un personnage en balance avec le groupe. */
    private static final Pattern MISE_EN_BALANCE = Pattern.compile(
            "(?<!\\p{L})(mais|alors que|tandis que|plutôt que|au lieu de|pourtant|contre)(?!\\p{L})", FLAGS);

    /** INT-CE1-1 : vouloir quelque chose pour soi n'est jamais dit mal, personne n'est juge. */
    private static final Pattern JUGEMENT = Pattern.compile(
            "(?<!\\p{L})(égoïst|hont|méchan|gentil|vilain|faute|tort|gêne|dérang|jalou)", FLAGS);

    /** INT-CE1-1 : possessifs qui disent qu'une chose ne sert qu'a celui qui la fait. */
    private static final Set<String> POSSESSIFS = Set.of("son", "sa", "ses");

    /** INT-CE1-1 : lieux et objets que tous utilisent, liste fermee, sans mot qui dise la portee. */
    private static final Set<String> PARTAGES = Set.of(
            "tableau", "cour", "ballons", "étagère", "coin", "craies", "jeu", "jeux", "salle", "porte",
            "bibliothèque", "robinet");

    private static final String OBJET_PERSONNEL = "personnel";

    /** INT-CE1-1 : dans chaque portee, une scene sur ce nombre au moins croise appartenance et portee. */
    private static final int UN_TIERS = 3;

    private static final Pattern DECLARATION_DE_PORTEE = Pattern.compile("INT-CE1-1 portee : (un|tous)");

    private static final Pattern DECLARATION_D_OBJET = Pattern.compile("INT-CE1-1 objet : (personnel|partage)");

    private static final Pattern DECLARATION_DE_FAMILLES = Pattern.compile("TRV-CE1-2 : ([a-z]), ([a-z])");

    private static final Pattern LIGNE_DE_FAMILLE = Pattern.compile("^#\\s+famille ([a-z]) : (.+)$");

    /** EMC-1 : l'enonce et les propositions ne s'adressent pas a l'enfant. */
    private static final Pattern DEUXIEME_PERSONNE =
            Pattern.compile("(?<!\\p{L})(tu|te|toi|vous|votre|vos)(?!\\p{L})", FLAGS);

    /** EMC-1 : aucun mot du foyer ni possessif de deuxieme personne. */
    private static final Pattern MOTS_DU_FOYER = Pattern.compile(
            "(?<!\\p{L})(ton|ta|tes|tien|tienne|tiens|tiennes|chez|maison)(?!\\p{L})", FLAGS);

    private static final Pattern NEGATION = Pattern.compile(
            "(?<!\\p{L})(ne|pas|jamais|aucun|aucune|rien)(?!\\p{L})|(?<!\\p{L})n'|(?<!\\p{L})sans(?!\\p{L})",
            FLAGS);

    private static final Pattern PRONOM_COMPLEMENT_EN_TETE =
            Pattern.compile("^(l'|(le|la|lui|leur|les)(?!\\p{L}))", FLAGS);

    private static final Pattern TERMINAISON_DE_PHRASE = Pattern.compile("[.?!](?:[\\s\\u00A0]+|$)");

    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!][\\s\\u00A0]+\\p{Lu}", FLAGS);

    /** TYP-1 (ADR-0067) : l'espace avant un signe double est insecable. */
    private static final Pattern ESPACE_ORDINAIRE_AVANT_SIGNE_DOUBLE = Pattern.compile("[^\\u00A0] [?:;!]");

    /** Aucune forme typographique ne designe la bonne reponse : ni chevrons ni guillemets. */
    private static final Pattern CITATION = Pattern.compile("[«»\"“”]");

    /** Pli a 744 x 551 (mesure d'ines-design, LP4) : trois lignes d'enonce, une ligne d'indice. */
    private static final int CARACTERES_D_UN_ENONCE_DE_TROIS_LIGNES = 112;

    private static final int CARACTERES_D_UN_INDICE_D_UNE_LIGNE = 46;

    /** FER-1 : chaque formule est la bonne reponse d'au moins ce nombre d'items. */
    private static final int ITEMS_PAR_FORMULE = 5;

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

        String portee() {
            List<Matcher> declarations = commentaires.stream().map(DECLARATION_DE_PORTEE::matcher)
                    .filter(Matcher::find).toList();
            assertThat(declarations)
                    .as("INT-CE1-1 : '%s' déclare exactement une portée", enonce).hasSize(1);
            return declarations.get(0).group(1);
        }

        String objet() {
            List<Matcher> declarations = commentaires.stream().map(DECLARATION_D_OBJET::matcher)
                    .filter(Matcher::find).toList();
            assertThat(declarations)
                    .as("INT-CE1-1 : '%s' déclare exactement une appartenance d'objet", enonce).hasSize(1);
            return declarations.get(0).group(1);
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

    /** L'en-tete du paquet : les familles de distracteurs. */
    private record Entete(Map<String, List<String>> familles) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1EmcInteretCommunTest.class.getResourceAsStream(chemin)) {
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
        Map<String, List<String>> familles = new TreeMap<>();
        for (String ligne : contenu.split("\\R")) {
            Matcher famille = LIGNE_DE_FAMILLE.matcher(ligne);
            if (famille.matches()) {
                familles.put(famille.group(1), separees(famille.group(2)));
            }
        }
        return new Entete(familles);
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
            if (minuscule.length() >= 5) {
                racines.add(minuscule.substring(0, 5));
            }
        }
        return racines;
    }

    private static Set<String> motsEntiers(String texte) {
        Set<String> mots = new TreeSet<>();
        Matcher mot = Pattern.compile("\\p{L}+", Pattern.UNICODE_CHARACTER_CLASS).matcher(texte);
        while (mot.find()) {
            mots.add(mot.group().toLowerCase(Locale.FRENCH));
        }
        return mots;
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

    /** La scene : l'enonce sans sa derniere phrase, la question. */
    private static String scene(ItemLu item) {
        return item.enonce().substring(0, item.enonce().length() - derniereQuestion(item).length());
    }

    private static String normalise(String texte) {
        return texte.replaceAll("[\\s\\u00A0]+", " ").strip().toLowerCase(Locale.FRENCH);
    }

    /** INT-CE1-2 : la famille a est exactement les deux formules, la famille b porte des nombres d'eleves. */
    private static void verifierLesFamillesDuCommentaire(Entete entete) {
        assertThat(new HashSet<>(entete.familles().getOrDefault("a", List.of())))
                .as("INT-CE1-2 : la famille a du commentaire d'en-tête n'est pas exactement les deux formules "
                        + "de nadia-prof (pour une seule personne, pour toute la classe)")
                .isEqualTo(FORMULES);
        List<String> nombres = entete.familles().getOrDefault("b", List.of());
        assertThat(nombres.size())
                .as("TRV-CE1-2 : la famille b du commentaire d'en-tête porte moins de trois nombres d'élèves : "
                        + "il n'y a pas de famille b écrite")
                .isGreaterThanOrEqualTo(3);
        assertThat(nombres).as("INT-CE1-2 : la famille b ne porte aucune des deux formules")
                .doesNotContainAnyElementsOf(FORMULES);
    }

    /**
     * INT-CE1-2 : la bonne reponse est l'une des deux formules, l'item porte les deux, et chaque
     * distracteur vient de sa famille declaree : le premier l'autre formule, le second un nombre.
     */
    private static void verifierLesFormules(List<ItemLu> items, Entete entete) {
        for (ItemLu item : items) {
            assertThat(FORMULES)
                    .as("INT-CE1-2 : la bonne réponse « %s » de '%s' n'est pas l'une des deux formules",
                            item.bonneReponse(), item.enonce())
                    .contains(item.bonneReponse());
            assertThat(new HashSet<>(item.propositions()))
                    .as("INT-CE1-2 : les propositions de '%s' ne portent pas les deux formules", item.enonce())
                    .containsAll(FORMULES);
            assertThat(item.familles())
                    .as("TRV-CE1-2 : '%s' déclare des familles autres que a puis b", item.enonce())
                    .isEqualTo(List.of("a", "b"));
            assertThat(entete.familles().getOrDefault("a", List.of()))
                    .as("TRV-CE1-2 : « %s » (distracteur de '%s') n'est pas dans la famille a que l'item "
                            + "déclare : l'autre formule", item.distracteurs().get(0), item.enonce())
                    .contains(item.distracteurs().get(0));
            assertThat(item.distracteurs().get(0))
                    .as("INT-CE1-2 : le distracteur de la famille a de '%s' est la bonne réponse", item.enonce())
                    .isNotEqualTo(item.bonneReponse());
            assertThat(entete.familles().getOrDefault("b", List.of()))
                    .as("TRV-CE1-2 : « %s » (distracteur de '%s') n'est pas dans la famille b que l'item "
                            + "déclare : un nombre d'élèves", item.distracteurs().get(1), item.enonce())
                    .contains(item.distracteurs().get(1));
        }
    }

    /** FER-1 : chaque formule est la bonne reponse d'au moins cinq items. */
    private static void verifierQueLesDeuxFormulesSontEnBonneReponse(List<ItemLu> items) {
        Map<String, Long> parReponse = new TreeMap<>();
        items.forEach(item -> parReponse.merge(item.bonneReponse(), 1L, Long::sum));
        for (String formule : FORMULES) {
            assertThat(parReponse.getOrDefault(formule, 0L))
                    .as("FER-1 : la formule « %s » est la bonne réponse de moins de %d items (%s)", formule,
                            ITEMS_PAR_FORMULE, parReponse)
                    .isGreaterThanOrEqualTo(ITEMS_PAR_FORMULE);
        }
    }

    /**
     * ADR-0086, clause 1 : un nombre d'eleves est toujours servi avec les deux bonnes reponses. Sinon
     * les items qui le portent sont un moule de propositions et une seule bonne reponse.
     */
    private static void verifierQueChaqueNombreSertLesDeuxReponses(List<ItemLu> items) {
        Map<String, Set<String>> reponsesParNombre = new TreeMap<>();
        for (ItemLu item : items) {
            reponsesParNombre.computeIfAbsent(item.distracteurs().get(1), nombre -> new TreeSet<>())
                    .add(item.bonneReponse());
        }
        reponsesParNombre.forEach((nombre, reponses) -> assertThat(reponses)
                .as("ADR-0086 clause 1 : le nombre « %s » n'est servi qu'avec la bonne réponse %s : ces items "
                        + "sont un moule de propositions et une seule bonne réponse", nombre, reponses)
                .hasSize(2));
    }

    /** INT-CE1-1 : la regle de surface, qui lit un possessif et ignore le reste de la scene. */
    private static String porteeQueDonneLePossessif(ItemLu item) {
        return motsEntiers(scene(item)).stream().anyMatch(POSSESSIFS::contains) ? PORTEE_UN : PORTEE_TOUS;
    }

    /** INT-CE1-1 : la regle de surface symetrique, qui lit un lieu ou un objet partage et ignore le reste. */
    private static String porteeQueDonneUnMotPartage(ItemLu item) {
        return motsEntiers(scene(item)).stream().anyMatch(PARTAGES::contains) ? PORTEE_TOUS : PORTEE_UN;
    }

    /**
     * INT-CE1-1 : la portee declaree est celle de la bonne reponse ; l'appartenance declaree de l'objet est
     * celle que la scene dit par un mot de la liste fermee : un possessif pour un objet personnel, un lieu
     * ou un objet partage et aucun possessif pour un objet partage. Une scene de portee un dont l'objet est
     * personnel ne nomme aucun lieu partage.
     */
    private static void verifierLaPortee(List<ItemLu> items) {
        for (ItemLu item : items) {
            String portee = item.portee();
            String attendue = portee.equals(PORTEE_UN) ? UNE_SEULE_PERSONNE : TOUTE_LA_CLASSE;
            assertThat(item.bonneReponse())
                    .as("INT-CE1-1 : '%s' déclare la portée %s, sa bonne réponse n'est pas « %s »",
                            item.enonce(), portee, attendue)
                    .isEqualTo(attendue);
            String objet = item.objet();
            Set<String> mots = motsEntiers(scene(item));
            boolean possessif = mots.stream().anyMatch(POSSESSIFS::contains);
            boolean partage = mots.stream().anyMatch(PARTAGES::contains);
            if (objet.equals(OBJET_PERSONNEL)) {
                assertThat(possessif)
                        .as("INT-CE1-1 : la scène de '%s' déclare l'objet personnel et ne porte aucun possessif",
                                item.enonce())
                        .isTrue();
                if (portee.equals(PORTEE_UN)) {
                    assertThat(partage)
                            .as("INT-CE1-1 : la scène de '%s' déclare la portée un et l'objet personnel et nomme "
                                    + "un lieu ou un objet partagé", item.enonce())
                            .isFalse();
                }
            } else {
                assertThat(possessif)
                        .as("INT-CE1-1 : la scène de '%s' déclare l'objet partagé et porte un possessif",
                                item.enonce())
                        .isFalse();
                assertThat(partage)
                        .as("INT-CE1-1 : la scène de '%s' déclare l'objet partagé et ne nomme aucun lieu ni objet "
                                + "partagé de la liste", item.enonce())
                        .isTrue();
            }
        }
    }

    /**
     * INT-CE1-1, motif M1 de la fiche de conformite : l'appartenance de l'objet ne decide pas de la portee.
     * Dans chaque portee, un tiers des scenes au moins (calcul sur le texte, non sur les declarations) est
     * une scene ou la regle de surface du possessif se trompe.
     */
    private static void verifierQueLAppartenanceNeDecidePasDeLaPortee(List<ItemLu> items) {
        for (String portee : List.of(PORTEE_UN, PORTEE_TOUS)) {
            List<ItemLu> deLaPortee = items.stream().filter(item -> item.portee().equals(portee)).toList();
            long croisees = deLaPortee.stream()
                    .filter(item -> !porteeQueDonneLePossessif(item).equals(portee)).count();
            assertThat(UN_TIERS * croisees)
                    .as("INT-CE1-1 : dans la portée %s, %d scènes sur %d où l'appartenance de l'objet ne décide pas "
                            + "de la portée, un tiers au moins est exigé : la forme donne la réponse", portee,
                            croisees, deLaPortee.size())
                    .isGreaterThanOrEqualTo(deLaPortee.size());
        }
    }

    /**
     * INT-CE1-1, reserve 4 du rejeu de nadia-prof : le lieu ne decide pas non plus de la portee. Meme seuil
     * que pour le possessif, applique a la regle "un mot de la liste fermee donne toute la classe" : dans
     * chaque portee, un tiers des scenes au moins est une scene ou cette regle se trompe (calcul sur le
     * texte, non sur les declarations).
     */
    private static void verifierQueLeLieuNeDecidePasDeLaPortee(List<ItemLu> items) {
        for (String portee : List.of(PORTEE_UN, PORTEE_TOUS)) {
            List<ItemLu> deLaPortee = items.stream().filter(item -> item.portee().equals(portee)).toList();
            long croisees = deLaPortee.stream()
                    .filter(item -> !porteeQueDonneUnMotPartage(item).equals(portee)).count();
            assertThat(UN_TIERS * croisees)
                    .as("INT-CE1-1 : dans la portée %s, %d scènes sur %d où un mot de la liste fermée des lieux et "
                            + "objets partagés ne décide pas de la portée, un tiers au moins est exigé : le lieu "
                            + "donne la réponse", portee, croisees, deLaPortee.size())
                    .isGreaterThanOrEqualTo(deLaPortee.size());
        }
    }

    /** INT-CE1-2 : les deux expressions interdites, dans chaque champ lu et dans le fichier entier. */
    private static void verifierLesMotsInterdits(List<ItemLu> items, String fichier) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                assertThat(MOTS_INTERDITS.matcher(champ).find())
                        .as("INT-CE1-2 : « %s » fait lire à l'enfant une expression que nadia-prof interdit",
                                champ)
                        .isFalse();
            }
        }
        assertThat(MOTS_INTERDITS.matcher(fichier.replace(IDENTIFIANT_DU_PAQUET_DES_BIENS, "")).find())
                .as("INT-CE1-2 : le fichier porte, même en commentaire, une expression que nadia-prof interdit")
                .isFalse();
    }

    /** INT-CE1-1 : aucune mise en balance du personnage et du groupe, aucun jugement. */
    private static void verifierQueLaSceneNeBlameJamais(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                assertThat(MISE_EN_BALANCE.matcher(champ).find())
                        .as("INT-CE1-1 : « %s » oppose un personnage et le groupe", champ)
                        .isFalse();
                assertThat(JUGEMENT.matcher(champ).find())
                        .as("INT-CE1-1 : « %s » juge celui qui veut quelque chose pour lui", champ)
                        .isFalse();
            }
        }
    }

    /** Point d'audit commun : aucune proposition ne reprend un mot plein de la scene ni de l'indice. */
    private static void verifierQuAucunePropositionNeReprendLaScene(List<ItemLu> items) {
        for (ItemLu item : items) {
            Set<String> lus = motsPleins(scene(item) + " " + item.indice());
            for (String proposition : item.propositions()) {
                assertThat(motsPleins(proposition).stream().filter(lus::contains).toList())
                        .as("point d'audit commun : « %s » (proposition de '%s') reprend un mot plein de la "
                                + "scène ou de l'indice : la forme donne la réponse", proposition, item.enonce())
                        .isEmpty();
            }
        }
    }

    /** Point d'audit commun : propositions en minuscule, ni chevrons ni guillemets, ni negation ni pronom en tete. */
    private static void verifierLaForme(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String proposition : item.propositions()) {
                assertThat(Character.isLowerCase(proposition.codePointAt(0)))
                        .as("point d'audit commun : « %s » (proposition de '%s') commence par une majuscule, la "
                                + "typographie donne la réponse", proposition, item.enonce())
                        .isTrue();
                assertThat(NEGATION.matcher(proposition).find())
                        .as("point d'audit commun : « %s » (proposition de '%s') porte une négation, la forme "
                                + "donne la réponse", proposition, item.enonce())
                        .isFalse();
                assertThat(PRONOM_COMPLEMENT_EN_TETE.matcher(proposition).find())
                        .as("point d'audit commun : « %s » (proposition de '%s') commence par un pronom "
                                + "complément, la forme donne la réponse", proposition, item.enonce())
                        .isFalse();
            }
            for (String champ : item.champsLus()) {
                assertThat(CITATION.matcher(champ).find())
                        .as("point d'audit commun : « %s » porte des chevrons ou des guillemets, la typographie "
                                + "donne la réponse", champ)
                        .isFalse();
            }
        }
    }

    /** NRC-CE1-1 : aucune proposition ni aucun enonce n'est celui d'un paquet voisin de CP ou de CM1. */
    private static void verifierQueRienNEstReprisDUnPaquetVoisin(List<ItemLu> items, List<Path> paquets) {
        Set<String> propositions = new HashSet<>();
        Set<String> enonces = new HashSet<>();
        for (Path paquet : paquets) {
            for (Object entree : (List<?>) ((Map<?, ?>) new Yaml().load(lire(paquet))).get("items")) {
                Map<?, ?> champs = (Map<?, ?>) entree;
                enonces.add(normalise(String.valueOf(champs.get("enonce"))));
                propositions.add(normalise(String.valueOf(champs.get("bonneReponse"))));
                for (Object distracteur : (List<?>) champs.get("distracteurs")) {
                    propositions.add(normalise(String.valueOf(distracteur)));
                }
            }
        }
        assertThat(propositions.size()).as("NRC-CE1-1 : les paquets voisins sont lus").isGreaterThan(50);
        for (ItemLu item : items) {
            assertThat(enonces)
                    .as("NRC-CE1-1 : l'énoncé de '%s' est celui d'un paquet de CP ou de CM1", item.enonce())
                    .doesNotContain(normalise(item.enonce()));
            for (String proposition : item.propositions()) {
                assertThat(propositions)
                        .as("NRC-CE1-1 : « %s » (proposition de '%s') est une proposition d'un paquet de CP ou "
                                + "de CM1", proposition, item.enonce())
                        .doesNotContain(normalise(proposition));
            }
        }
    }

    /** EMC-1 : un personnage nomme, jamais l'enfant, aucun mot du foyer. */
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
            adresses.add(item.indice());
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

    /** LON-CE1-1 et -2 : un enonce de vingt mots et trois phrases au plus ; la question est toujours la meme. */
    private static void verifierLesEnonces(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(mots(item.enonce()))
                    .as("LON-CE1-1 : l'énoncé « %s » compte plus de vingt mots", item.enonce())
                    .isLessThanOrEqualTo(20);
            long phrases = TERMINAISON_DE_PHRASE.matcher(item.enonce()).results().count();
            assertThat(phrases)
                    .as("LON-CE1-2 : l'énoncé « %s » compte plus de trois phrases", item.enonce())
                    .isLessThanOrEqualTo(3);
            assertThat(derniereQuestion(item))
                    .as("INT-CE1-2 : la question de '%s' n'est pas « %s »", item.enonce(), QUESTION)
                    .isEqualTo(QUESTION);
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

    /** TRV-CE1-5 : aucun indice n'est repete sur le paquet. */
    private static void verifierQueLesIndicesSontDistincts(List<ItemLu> items) {
        assertThat(items.stream().map(ItemLu::indice).distinct().count())
                .as("TRV-CE1-5 : un indice est identique sur deux items du paquet").isEqualTo(items.size());
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

    /** TRV-CE1-6 : prenoms equilibres a un pres. */
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
        assertThat(MOTS_INTERDITS.matcher(titre + " " + consigne).find())
                .as("INT-CE1-2 : le titre ou la consigne fait lire une expression que nadia-prof interdit")
                .isFalse();
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
    void adr0081CePaquetDeclareEmcCe104EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("EMC-CE1-04"));
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

    // ----- INT-CE1-1 et INT-CE1-2

    @Test
    void int2LesFamillesDuCommentaireSontLesDeuxFormulesEtDesNombresDEleves() {
        verifierLesFamillesDuCommentaire(enteteDuPaquet());
    }

    @Test
    void int2ChaqueItemPorteLesDeuxFormulesEtSaBonneReponseEnEstUne() {
        verifierLesFormules(items(), enteteDuPaquet());
    }

    @Test
    void fer1LesDeuxFormulesSontChacuneEnBonneReponse() {
        verifierQueLesDeuxFormulesSontEnBonneReponse(items());
    }

    @Test
    void adr0086ChaqueNombreDEleveSertLesDeuxBonnesReponses() {
        verifierQueChaqueNombreSertLesDeuxReponses(items());
    }

    @Test
    void int1LaPorteeDeclareeEstCelleDeLaBonneReponseEtDeLaScene() {
        verifierLaPortee(items());
    }

    @Test
    void int1LAppartenanceDeLObjetNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees() {
        verifierQueLAppartenanceNeDecidePasDeLaPortee(items());
    }

    @Test
    void int1LeLieuNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees() {
        verifierQueLeLieuNeDecidePasDeLaPortee(items());
    }

    @Test
    void int2AucuneExpressionInterditeNEstLueParLEnfantNiEcriteDansLeFichier() {
        verifierLesMotsInterdits(items(), lire(FICHIER_CONTENU));
    }

    @Test
    void int1AucuneSceneNOpposeUnPersonnageAuGroupeNiNeJugeCeluiQuiVeutPourLui() {
        verifierQueLaSceneNeBlameJamais(items());
    }

    @Test
    void nrc1AucuneReprisePourUnPaquetDeCpOuDeCm1() {
        verifierQueRienNEstReprisDUnPaquetVoisin(items(), PAQUETS_VOISINS);
    }

    @Test
    void emc1ChaqueSujetEstUnPersonnageNommeEtJamaisLEnfant() {
        verifierQueLeSujetEstUnPersonnageNomme(items());
    }

    // ----- point d'audit commun : aucune forme ne donne la reponse

    @Test
    void auditAucunePropositionNeReprendLaSceneNiNePorteDeNegation() {
        verifierQuAucunePropositionNeReprendLaScene(items());
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
    void eb6AucunIndiceNEstRepeteSurLePaquet() {
        verifierQueLesIndicesSontDistincts(items());
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
    void leJeuFautifCommiteEstRefuseSurUnEnteteDontLaFamilleAReecritUneFormule() {
        Entete entete = entete(contenuFautif("entete-formule-reecrite.yaml"));

        assertThatThrownBy(() -> verifierLesFamillesDuCommentaire(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("INT-CE1-2")
                .hasMessageContaining("pas exactement les deux formules");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnteteSansNombresDEleves() {
        Entete entete = entete(contenuFautif("entete-sans-nombres.yaml"));

        assertThatThrownBy(() -> verifierLesFamillesDuCommentaire(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("famille b");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasUneFormule() {
        List<ItemLu> items = fautifs("(sonde 01)");

        assertThat(items).hasSize(1);
        assertThatThrownBy(() -> verifierLesFormules(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("INT-CE1-2")
                .hasMessageContaining("n'est pas l'une des deux formules");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemQuiNePorteQuUneFormule() {
        List<ItemLu> items = fautifs("(sonde 02)");

        assertThatThrownBy(() -> verifierLesFormules(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne portent pas les deux formules");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurHorsDeLaFamilleDeclaree() {
        List<ItemLu> items = fautifs("(sonde 03)");

        assertThatThrownBy(() -> verifierLesFormules(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas dans la famille a");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnNombreHorsDeLaFamilleB() {
        List<ItemLu> items = fautifs("(sonde 40)");

        assertThatThrownBy(() -> verifierLesFormules(items, enteteFautif()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas dans la famille b");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFormuleQuiNEstJamaisEnBonneReponse() {
        List<ItemLu> items = fautifs("(sonde 36)");

        assertThat(items).hasSize(3);
        assertThatThrownBy(() -> verifierQueLesDeuxFormulesSontEnBonneReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnNombreServiAvecUneSeuleBonneReponse() {
        List<ItemLu> items = fautifs("(sonde 36)");

        assertThatThrownBy(() -> verifierQueChaqueNombreSertLesDeuxReponses(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0086 clause 1")
                .hasMessageContaining("moule de propositions");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePorteeDeclareeQuiNEstPasCelleDeLaBonneReponse() {
        List<ItemLu> items = fautifs("(sonde 04)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("INT-CE1-1")
                .hasMessageContaining("n'est pas « pour une seule personne »");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSceneDePorteeUnSansPossessif() {
        List<ItemLu> items = fautifs("(sonde 05)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte aucun possessif");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSceneDePorteeTousAvecUnPossessif() {
        List<ItemLu> items = fautifs("(sonde 06)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("déclare l'objet partagé et porte un possessif");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSceneDePorteeUnQuiNommeUnLieuPartage() {
        List<ItemLu> items = fautifs("(sonde 07)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nomme un lieu ou un objet partagé");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansDeclarationDAppartenanceDeLObjet() {
        List<ItemLu> items = fautifs("(sonde 41)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("déclare exactement une appartenance d'objet");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePorteeUnOuLePossessifDecideDeToutes() {
        List<ItemLu> items = fautifs("(sonde 42)");

        assertThat(items).hasSize(3);
        assertThatThrownBy(() -> verifierQueLAppartenanceNeDecidePasDeLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dans la portée un")
                .hasMessageContaining("la forme donne la réponse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePorteeTousOuLObjetPartageDecideDeToutes() {
        List<ItemLu> items = fautifs("(sonde 43)");

        assertThat(items).hasSize(3);
        assertThatThrownBy(() -> verifierQueLAppartenanceNeDecidePasDeLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dans la portée tous")
                .hasMessageContaining("la forme donne la réponse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePorteeUnOuLeLieuDecideDeToutes() {
        List<ItemLu> items = fautifs("(sonde 44)");

        assertThat(items).hasSize(3);
        assertThatThrownBy(() -> verifierQueLeLieuNeDecidePasDeLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dans la portée un")
                .hasMessageContaining("le lieu donne la réponse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePorteeTousOuLeLieuDecideDeToutes() {
        List<ItemLu> items = fautifs("(sonde 45)");

        assertThat(items).hasSize(3);
        assertThatThrownBy(() -> verifierQueLeLieuNeDecidePasDeLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dans la portée tous")
                .hasMessageContaining("le lieu donne la réponse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansDeclarationDePortee() {
        List<ItemLu> items = fautifs("(sonde 08)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("déclare exactement une portée");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSceneSansAucunMotPartageDeLaListe() {
        List<ItemLu> items = fautifs("(sonde 31)");

        assertThatThrownBy(() -> verifierLaPortee(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne nomme aucun lieu ni objet partagé");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLesDeuxExpressionsInterdites() {
        for (String marqueur : List.of("(sonde 09)", "(sonde 10)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThat(items).hasSize(1);
            assertThatThrownBy(() -> verifierLesMotsInterdits(items, ""))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("INT-CE1-2")
                    .hasMessageContaining("nadia-prof interdit");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLePlurielLeTraitDUnionEtLesFormesFlechiesDesExpressionsInterdites() {
        for (String marqueur : List.of("(sonde 46)", "(sonde 47)", "(sonde 48)", "(sonde 49)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThat(items).as(marqueur).hasSize(1);
            assertThatThrownBy(() -> verifierLesMotsInterdits(items, ""))
                    .as(marqueur)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("INT-CE1-2")
                    .hasMessageContaining("nadia-prof interdit");
        }
    }

    @Test
    void leFichierQuiCiteLIdentifiantDuPaquetDesBiensNEstPasRefuseEtSonPlurielEcritEnCommentaireLEst() {
        verifierLesMotsInterdits(List.of(), "# voir " + IDENTIFIANT_DU_PAQUET_DES_BIENS + " pour la ligne voisine.");

        assertThatThrownBy(() -> verifierLesMotsInterdits(List.of(),
                "# voir " + IDENTIFIANT_DU_PAQUET_DES_BIENS + " et les biens communs."))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le fichier porte, même en commentaire");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnFichierQuiEcritUneExpressionInterditeEnCommentaire() {
        assertThatThrownBy(() -> verifierLesMotsInterdits(List.of(), "# Cela sert l'intérêt général."))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le fichier porte, même en commentaire");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePersonneMiseEnBalanceAvecLeGroupe() {
        List<ItemLu> items = fautifs("(sonde 11)");

        assertThatThrownBy(() -> verifierQueLaSceneNeBlameJamais(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("INT-CE1-1")
                .hasMessageContaining("oppose un personnage et le groupe");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnJugementDeCeluiQuiVeutPourLui() {
        for (String marqueur : List.of("(sonde 12)", "(sonde 13)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierQueLaSceneNeBlameJamais(items))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("juge celui qui veut quelque chose pour lui");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionQuiReprendUnMotDeLaScene() {
        List<ItemLu> items = fautifs("(sonde 14)");

        assertThatThrownBy(() -> verifierQuAucunePropositionNeReprendLaScene(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("reprend un mot plein")
                .hasMessageContaining("la forme donne la réponse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneMajusculeEtDesChevrons() {
        for (String marqueur : List.of("(sonde 15)", "(sonde 16)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierLaForme(items))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("la typographie donne la réponse");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionANegation() {
        List<ItemLu> items = fautifs("(sonde 17)");

        assertThatThrownBy(() -> verifierLaForme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("porte une négation");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        List<ItemLu> items = fautifs("(sonde 18)");

        assertThatThrownBy(() -> verifierLesChampsServis(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeVingtEtUnMots() {
        List<ItemLu> items = fautifs("(sonde 19)");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1")
                .hasMessageContaining("vingt mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeQuatrePhrases() {
        List<ItemLu> items = fautifs("(sonde 20)");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-2")
                .hasMessageContaining("trois phrases");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneQuestionFinaleDifferente() {
        List<ItemLu> items = fautifs("(sonde 35)");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la question de");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionDeSeptMots() {
        List<ItemLu> items = fautifs("(sonde 21)");

        assertThatThrownBy(() -> verifierLesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5")
                .hasMessageContaining("six mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeTreizeMots() {
        List<ItemLu> items = fautifs("(sonde 22)");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("douze mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<ItemLu> items = fautifs("(sonde 23)");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceRepete() {
        List<ItemLu> items = fautifs("(sonde 24)", "(sonde 25)");

        assertThat(items).hasSize(2);
        assertThatThrownBy(() -> verifierQueLesIndicesSontDistincts(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("identique");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseToujoursLaPlusLongue() {
        List<ItemLu> items = fautifs("(sonde 26)", "(sonde 27)");

        assertThat(items).hasSize(2);
        assertThatThrownBy(() -> verifierLaPlusLongue(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("strictement la plus longue");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEspaceOrdinaireAvantUnPointDInterrogation() {
        List<ItemLu> items = fautifs("(sonde 28)");

        assertThatThrownBy(() -> verifierLaTypographie(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1")
                .hasMessageContaining("espace ordinaire");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuFoyerEtUneAdresseALEnfant() {
        for (String marqueur : List.of("(sonde 29)", "(sonde 30)", "(sonde 39)")) {
            List<ItemLu> items = fautifs(marqueur);

            assertThatThrownBy(() -> verifierQueLeSujetEstUnPersonnageNomme(items))
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("EMC-1");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceSansPersonnageNomme() {
        List<ItemLu> items = fautifs("(sonde 31)");

        assertThatThrownBy(() -> verifierQueLeSujetEstUnPersonnageNomme(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne nomme aucun personnage");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<ItemLu> items = fautifs("(sonde 32)");

        assertThat(items).hasSize(3);
        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("féminins distincts");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceTropLongPourLePli() {
        List<ItemLu> items = fautifs("(sonde 33)");

        assertThatThrownBy(() -> verifierLePli(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("trois lignes au plus");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceTropLongPourLePli() {
        List<ItemLu> items = fautifs("(sonde 34)");

        assertThatThrownBy(() -> verifierLePli(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une ligne au plus");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceOuUnePropositionRepriseDUnPaquetVoisin() {
        List<ItemLu> repriseDEnonce = itemsFautifs().stream()
                .filter(item -> item.enonce().startsWith("Dans la cour, il est interdit")).toList();
        List<ItemLu> repriseDeProposition = fautifs("(sonde 38)");

        assertThat(repriseDEnonce).hasSize(1);
        assertThatThrownBy(() -> verifierQueRienNEstReprisDUnPaquetVoisin(repriseDEnonce, PAQUETS_VOISINS))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NRC-CE1-1")
                .hasMessageContaining("énoncé");
        assertThatThrownBy(() -> verifierQueRienNEstReprisDUnPaquetVoisin(repriseDeProposition, PAQUETS_VOISINS))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NRC-CE1-1")
                .hasMessageContaining("proposition");
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
