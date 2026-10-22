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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-EMC-CE1-02 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-emc-regle-sanction}, ligne {@code EMC-CE1-02} - distinguer une regle d'une
 * sanction, et dire a quoi sert une sanction. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section 4.2 :
 * {@code RSA-CE1-1}, {@code RSA-CE1-2} ; section 2 : {@code TRV-CE1-1}, {@code -2}, {@code -5},
 * {@code -6}, {@code LON-CE1-1}, {@code -2}, {@code -5}, {@code FER-1} a {@code FER-3}).
 *
 * <p><b>Comment ce test recompte les deux taches de {@code RSA-CE1-1}.</b> La cible de chaque item
 * (regle, sanction, reparation, rappel) est une declaration d'auteur, lue dans le commentaire qui
 * precede l'item ({@code RSA-CE1-1 tache N : cible}). Ce test garde qu'elle existe, que la tache
 * qu'elle annonce est la bonne, que la bonne reponse de l'item est celle que la cible commande
 * (<i>la regle</i>, <i>la sanction</i>, <i>a rappeler</i>, <i>a reparer</i>), que les deux buts de la
 * parenthese du referentiel sont chacun en bonne reponse ({@code FER-1}), que chaque tache compte au
 * moins trois items ({@code FER-3}), qu'une situation de cible <i>reparation</i> decrit ce qui est
 * abime et qu'une situation de cible <i>rappel</i> ne decrit rien d'abime. Les familles de
 * distracteurs ({@code TRV-CE1-2}) sont declarees dans le second commentaire et recomptees sur le
 * texte des distracteurs, dans l'ordre du fichier.
 *
 * <p><b>Les refus d'avance de nadia-prof</b> sont gardes sur les mots : aucune humiliation, punition
 * physique, sanction collective ni privation de repas, aucun qualificatif du personnage, dans aucun
 * champ que l'enfant lit, distracteurs compris. Ce test ne lit pas une intention.
 *
 * <p>Ce que ce test ne lit pas : {@code NRC-CE1-1} (aucune reprise d'un item du CP), qu'un item n'ait
 * qu'une reponse defendable ({@code EMC-5}), que chaque situation soit exacte, qu'une sanction
 * proposee soit proportionnee a l'acte, qu'aucun role ne soit distribue par genre
 * ({@code TRV-CE1-6}, dont il ne garde que l'equilibre des prenoms declares), qu'un indice soit une
 * methode et non la reponse, et que le registre soit celui d'un enfant de CE1. Cela reste a la
 * lecture et au jeu de nadia-prof.
 */
class PaquetCe1EmcRegleSanctionTest {

    private static final String PAQUET = "ce1-emc-regle-sanction";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1EmcRegleSanctionTest";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final Pattern CIBLE =
            Pattern.compile("RSA-CE1-1 tache ([12]) : (regle|sanction|reparation|rappel)");

    private static final Pattern FAMILLES = Pattern.compile("TRV-CE1-2 : ([a-f]), ([a-f])");

    private static final String LA_REGLE = "la règle";

    private static final String LA_SANCTION = "la sanction";

    private static final String LA_FAUTE = "la faute";

    /** RSA-CE1-1, famille (d) : un but qui change ou supprime la regle. */
    private static final Pattern BUT_QUI_CHANGE_LA_REGLE =
            Pattern.compile("^à (changer|supprimer) la règle$", FLAGS);

    /** RSA-CE1-1, famille (e) : un but qui cache l'acte ou le fait oublier. */
    private static final Pattern BUT_QUI_CACHE = Pattern.compile(
            "^à (oublier |cacher |garder le secret$|ne rien dire à personne$)", FLAGS);

    /** RSA-CE1-1, famille (f) : l'autre but de la sanction. */
    private static final Pattern BUT_REPARER = Pattern.compile("^à réparer ", FLAGS);

    private static final Pattern BUT_RAPPELER = Pattern.compile("^à rappeler ", FLAGS);

    /** RSA-CE1-1 : une sanction proposee est une reparation, ou un geste refait comme il faut. */
    private static final Pattern GESTE_REPARATEUR = Pattern.compile(
            "recoll|nettoy|laver|essuy|ramass|rendr|refai|refer|replant|réécri|excus|rejou|reprend|repos"
                    + "|ferme|écrire|répét|taill|séch|effac", FLAGS);

    /** EMC-5 : une situation de cible reparation decrit ce qui est abime, une situation de rappel non. */
    private static final Pattern CE_QUI_EST_ABIME =
            Pattern.compile("(?<!\\p{L})(cass|déchir|tach|mouill|abîm|écrit sur)", FLAGS);

    /**
     * RSA-CE1-1 et RSA-CE1-2, refus d'avance de nadia-prof : une humiliation, une punition physique, une
     * sanction collective, une privation de repas, un personnage qualifie. Aucun de ces mots dans ce
     * que l'enfant lit, meme en distracteur.
     */
    private static final Pattern REFUS_D_AVANCE = Pattern.compile(
            "(?<!\\p{L})(méchan|vilain|honte|humili|punit|puni|punition|frapp|gifl|fess|enferm|privé|privation"
                    + "|repas|cantine|goûter|déjeuner|idiot|bête|stupide|paresseu|menteu"
                    + "|toute la classe|toute l'école|devant la classe|devant tout le monde)"
                    + "|(?<!\\p{L})coups?(?!\\p{L})", FLAGS);

    /** EMC-1 : l'enfant n'est jamais interroge, aucun mot du foyer ni possessif de deuxieme personne. */
    private static final Pattern MOTS_DU_FOYER = Pattern.compile(
            "(?<!\\p{L})(ton|ta|tes|tien|tienne|tiens|tiennes|chez|maison)(?!\\p{L})", FLAGS);

    /** EMC-1 : l'enonce et les propositions ne s'adressent pas a l'enfant. */
    private static final Pattern DEUXIEME_PERSONNE =
            Pattern.compile("(?<!\\p{L})(tu|te|toi|vous|votre|vos)(?!\\p{L})", FLAGS);

    private static final Pattern TERMINAISON_DE_PHRASE = Pattern.compile("[.?!](?:[\\s\\u00A0]+|$)");

    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!][\\s\\u00A0]+\\p{Lu}", FLAGS);

    /** TYP-1 (ADR-0067) : l'espace avant un signe double, et dans les chevrons, est insecable. */
    private static final Pattern ESPACE_ORDINAIRE_AVANT_SIGNE_DOUBLE = Pattern.compile("[^\\u00A0] [?:;!]");

    private static final Pattern CHEVRON_SANS_ESPACE_INSECABLE = Pattern.compile("«[^\\u00A0]|[^\\u00A0]»");

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

        private Matcher mentionDeCible() {
            List<Matcher> mentions = commentaires.stream().map(CIBLE::matcher).filter(Matcher::find).toList();
            assertThat(mentions).as("RSA-CE1-1 : '%s' porte exactement une mention de tâche et de cible",
                    enonce).hasSize(1);
            return mentions.get(0);
        }

        String tache() {
            return mentionDeCible().group(1);
        }

        String cible() {
            return mentionDeCible().group(2);
        }

        String familles() {
            List<String> familles = commentaires.stream().map(FAMILLES::matcher).filter(Matcher::find)
                    .map(m -> m.group(1) + m.group(2)).toList();
            assertThat(familles)
                    .as("TRV-CE1-2 : '%s' déclare exactement une paire de familles de distracteurs "
                            + "(a à f)", enonce)
                    .hasSize(1);
            return familles.get(0);
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
        try (InputStream flux = PaquetCe1EmcRegleSanctionTest.class.getResourceAsStream(chemin)) {
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

    private static List<ItemLu> items() {
        return lus(lire(FICHIER_CONTENU));
    }

    private static List<ItemLu> itemsFautifs() {
        return lus(lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-emc-fautif.yaml"));
    }

    /** L'item fautif dont l'enonce commence par ce debut, seul, pour mordre un verificateur a la fois. */
    private static List<ItemLu> fautif(String debutDEnonce) {
        return itemsFautifs().stream().filter(item -> item.enonce().startsWith(debutDEnonce)).toList();
    }

    private static int mots(String texte) {
        return (int) java.util.Arrays.stream(texte.strip().split("[\\s\\u00A0]+"))
                .filter(token -> token.chars().anyMatch(Character::isLetterOrDigit)).count();
    }

    private static boolean nommeLePrenom(String texte, String prenom) {
        return Pattern.compile("(?<!\\p{L})" + Pattern.quote(prenom) + "(?!\\p{L})",
                Pattern.UNICODE_CHARACTER_CLASS).matcher(texte).find();
    }

    private static boolean nommeUnPersonnage(String texte) {
        return GENRE_DES_PRENOMS.keySet().stream().anyMatch(prenom -> nommeLePrenom(texte, prenom));
    }

    /** FER-3 : deux taches, chacune d'au moins trois items, et la cible annoncee est celle de la tache. */
    private static void verifierLesTaches(List<ItemLu> items) {
        for (ItemLu item : items) {
            String attendue = item.cible().equals("regle") || item.cible().equals("sanction") ? "1" : "2";
            assertThat(item.tache())
                    .as("RSA-CE1-1 : '%s' annonce la tâche %s pour la cible %s, qui relève de la tâche %s",
                            item.enonce(), item.tache(), item.cible(), attendue)
                    .isEqualTo(attendue);
        }
        long premiere = items.stream().filter(item -> item.tache().equals("1")).count();
        long seconde = items.stream().filter(item -> item.tache().equals("2")).count();
        assertThat(Math.min(premiere, seconde))
                .as("FER-3 : la tâche la moins servie compte au moins trois items (%d et %d)", premiere,
                        seconde)
                .isGreaterThanOrEqualTo(3);
    }

    /**
     * RSA-CE1-1 et FER-1 : la tache 1 sert la regle et la sanction, la tache 2 sert les deux buts de la
     * parenthese du referentiel, chacun en bonne reponse ; la bonne reponse de chaque item est celle
     * que sa cible commande.
     */
    private static void verifierLesBonnesReponsesDeChaqueCible(List<ItemLu> items) {
        Map<String, String> attendue = Map.of("regle", "^" + LA_REGLE + "$", "sanction", "^" + LA_SANCTION + "$",
                "reparation", BUT_REPARER.pattern(), "rappel", BUT_RAPPELER.pattern());
        for (ItemLu item : items) {
            assertThat(Pattern.compile(attendue.get(item.cible()), FLAGS).matcher(item.bonneReponse()).find())
                    .as("RSA-CE1-1 : '%s' est de cible %s, sa bonne réponse « %s » n'est pas celle que la cible "
                            + "commande (%s)", item.enonce(), item.cible(), item.bonneReponse(),
                            item.cible().equals("reparation") ? "à réparer"
                                    : item.cible().equals("rappel") ? "à rappeler" : "la " + item.cible())
                    .isTrue();
        }
        for (String cible : List.of("regle", "sanction", "reparation", "rappel")) {
            assertThat(items.stream().filter(item -> item.cible().equals(cible)).count())
                    .as("FER-1 : au moins trois items de cible %s", cible).isGreaterThanOrEqualTo(3);
        }
    }

    /** RSA-CE1-1 : la regle est citee entre chevrons quand elle est la cible, et seulement alors. */
    private static void verifierQueLaReglePorteLesChevrons(List<ItemLu> items) {
        for (ItemLu item : items) {
            boolean cite = item.enonce().contains("«") && item.enonce().contains("»");
            assertThat(cite == item.cible().equals("regle"))
                    .as("RSA-CE1-1 : '%s' (cible %s) %s", item.enonce(), item.cible(),
                            item.cible().equals("regle") ? "cite la règle entre chevrons"
                                    : "ne cite aucune règle entre chevrons")
                    .isTrue();
        }
    }

    /** RSA-CE1-1 : les sanctions des situations sont des reparations. */
    private static void verifierQueLesSanctionsSontDesReparations(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(GESTE_REPARATEUR.matcher(item.enonce()).find())
                    .as("RSA-CE1-1 : la sanction de '%s' n'est pas une réparation (recoller, nettoyer, "
                            + "s'excuser, refaire)", item.enonce())
                    .isTrue();
        }
    }

    /** EMC-5 : une situation de cible reparation decrit ce qui est abime, une situation de rappel non. */
    private static void verifierCeQuiEstAbime(List<ItemLu> items) {
        for (ItemLu item : items) {
            if (item.cible().equals("reparation") || item.cible().equals("rappel")) {
                assertThat(CE_QUI_EST_ABIME.matcher(item.enonce()).find() == item.cible().equals("reparation"))
                        .as("EMC-5 : '%s' (cible %s) %s", item.enonce(), item.cible(),
                                item.cible().equals("reparation") ? "ne décrit rien d'abîmé à réparer"
                                        : "décrit ce qui est abîmé, la sanction y répare un tort")
                        .isTrue();
            }
        }
    }

    /** RSA-CE1-1 et RSA-CE1-2, refus d'avance : aucun de ces mots, meme en distracteur. */
    private static void verifierLesRefusDAvance(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                assertThat(REFUS_D_AVANCE.matcher(champ).find())
                        .as("RSA-CE1-2 : '%s' fait lire un mot refusé d'avance (humiliation, punition physique, "
                                + "sanction collective, privation de repas, personnage qualifié) : « %s »",
                                item.enonce(), champ)
                        .isFalse();
            }
        }
    }

    /** RSA-CE1-2 : un acte, un personnage nomme, jamais l'enfant. */
    private static void verifierQueLActeEstCeluiDUnPersonnage(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(nommeUnPersonnage(item.enonce()))
                    .as("RSA-CE1-2 : '%s' ne nomme aucun personnage", item.enonce()).isTrue();
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

    /**
     * TRV-CE1-2 : les familles declarees sont celles des distracteurs, dans l'ordre du fichier, et elles
     * sont les seules que la cible admet ; les propositions de la tache 1 sont toujours la regle, la
     * sanction et la faute.
     */
    private static void verifierLesFamilles(List<ItemLu> items) {
        for (ItemLu item : items) {
            String declarees = item.familles();
            StringBuilder lues = new StringBuilder();
            for (String distracteur : item.distracteurs()) {
                lues.append(familleDe(item, distracteur));
            }
            assertThat(declarees)
                    .as("TRV-CE1-2 : '%s' déclare les familles « %s », ses distracteurs sont de familles « %s »",
                            item.enonce(), declarees, lues)
                    .isEqualTo(lues.toString());
            String triees = declarees.chars().sorted()
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            Set<String> admises = switch (item.cible()) {
                case "regle" -> Set.of("bc");
                case "sanction" -> Set.of("ac");
                case "reparation" -> Set.of("de");
                default -> Set.of("df", "ef");
            };
            assertThat(admises)
                    .as("TRV-CE1-2 : '%s' (cible %s) déclare les familles « %s », que sa cible n'admet pas",
                            item.enonce(), item.cible(), declarees)
                    .contains(triees);
            if (item.tache().equals("1")) {
                assertThat(new HashSet<>(item.propositions()))
                        .as("RSA-CE1-1 : les trois propositions de '%s' sont la règle, la sanction et la faute",
                                item.enonce())
                        .isEqualTo(Set.of(LA_REGLE, LA_SANCTION, LA_FAUTE));
            }
        }
    }

    private static char familleDe(ItemLu item, String distracteur) {
        if (item.tache().equals("1")) {
            return switch (distracteur) {
                case LA_REGLE -> 'a';
                case LA_SANCTION -> 'b';
                case LA_FAUTE -> 'c';
                default -> '?';
            };
        }
        if (BUT_REPARER.matcher(distracteur).find()) {
            return 'f';
        }
        if (BUT_QUI_CHANGE_LA_REGLE.matcher(distracteur).find()) {
            return 'd';
        }
        return BUT_QUI_CACHE.matcher(distracteur).find() ? 'e' : '?';
    }

    /** BCO-CE1-1 de nadia-prof (lecture du palier 3) : aucune proposition n'est l'intrus du premier mot. */
    private static void verifierQueLePremierMotNeDonnePasLaReponse(List<ItemLu> items) {
        for (ItemLu item : items) {
            String premierMot = item.bonneReponse().split(" ")[0];
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.split(" ")[0])
                        .as("TRV-CE1-2 : « %s » (distracteur de '%s') n'a pas le même premier mot que la bonne "
                                + "réponse « %s » : le premier mot donne la réponse",
                                distracteur, item.enonce(), item.bonneReponse())
                        .isEqualTo(premierMot);
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

    /** LON-CE1-5 : au plus six mots et une seule phrase par proposition. */
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
    private static void verifierQueLesIndicesDiffèrent(List<ItemLu> items) {
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

    /** TYP-1 : espace insecable avant un signe double et dans les chevrons, dans chaque champ lu. */
    private static void verifierLaTypographie(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                assertThat(ESPACE_ORDINAIRE_AVANT_SIGNE_DOUBLE.matcher(champ).find())
                        .as("TYP-1 : « %s » porte une espace ordinaire avant ?, :, ; ou !", champ).isFalse();
                assertThat(CHEVRON_SANS_ESPACE_INSECABLE.matcher(champ).find())
                        .as("TYP-1 : « %s » porte un chevron sans espace insécable", champ).isFalse();
            }
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
    void adr0081CePaquetDeclareEmcCe102EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("EMC-CE1-02"));
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

    // ----- RSA-CE1-1 et RSA-CE1-2

    @Test
    void rsa1LesDeuxTachesSontServiesEtChaqueItemEnPorteUneSeuleAvecSaCible() {
        verifierLesTaches(items());
    }

    @Test
    void rsa1LaBonneReponseDeChaqueItemEstCelleQueSaCibleCommandeEtLesDeuxButsSontServis() {
        verifierLesBonnesReponsesDeChaqueCible(items());
    }

    @Test
    void rsa1LaRegleEstCiteeEntreChevronsQuandElleEstLaCibleEtSeulementAlors() {
        verifierQueLaReglePorteLesChevrons(items());
    }

    @Test
    void rsa1LesSanctionsDesSituationsSontDesReparations() {
        verifierQueLesSanctionsSontDesReparations(items());
    }

    @Test
    void emc5UneSituationDeReparationDecritCeQuiEstAbimeEtUneSituationDeRappelNon() {
        verifierCeQuiEstAbime(items());
    }

    @Test
    void rsa2AucunMotRefuseDAvanceNApparaitMemeEnDistracteur() {
        verifierLesRefusDAvance(items());
    }

    @Test
    void rsa2ChaqueActeEstCeluiDUnPersonnageNommeEtJamaisDeLEnfant() {
        verifierQueLActeEstCeluiDUnPersonnage(items());
    }

    @Test
    void trv2LesFamillesDeclareesSontCoherentesAvecLaCibleEtAvecLesDistracteurs() {
        verifierLesFamilles(items());
    }

    @Test
    void trv2AucunPremierMotNeDesigneLaBonneReponse() {
        verifierQueLePremierMotNeDonnePasLaReponse(items());
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
        verifierQueLesIndicesDiffèrent(items());
    }

    @Test
    void eb7LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        verifierLaPlusLongue(items());
    }

    @Test
    void typ1LEspaceAvantUnSigneDoubleEtDansLesChevronsEstInsecable() {
        verifierLaTypographie(items());
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
    void leJeuFautifCommiteEstRefuseSurUneTacheTropPeuServie() {
        List<ItemLu> items = fautif("Noé a sali sans chevrons");

        assertThatThrownBy(() -> verifierLesTaches(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansMentionDeCible() {
        List<ItemLu> items = fautif("Lina a cassé sans cible");

        assertThatThrownBy(() -> verifierLesTaches(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-1")
                .hasMessageContaining("mention de tâche");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneCibleAnnonceeDansLaMauvaiseTache() {
        List<ItemLu> items = fautif("Tom a cassé dans la mauvaise tâche");

        assertThatThrownBy(() -> verifierLesTaches(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-1")
                .hasMessageContaining("relève de la tâche");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasCelleDeSaCible() {
        List<ItemLu> items = fautif("Mila a cassé avec une mauvaise bonne réponse");

        assertThatThrownBy(() -> verifierLesBonnesReponsesDeChaqueCible(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-1")
                .hasMessageContaining("à réparer");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnButDeLaParentheseJamaisEnBonneReponse() {
        List<ItemLu> items = fautif("Léo a cassé pour FER-1");

        assertThatThrownBy(() -> verifierLesBonnesReponsesDeChaqueCible(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneReglePorteeSansChevrons() {
        List<ItemLu> items = fautif("Noé a sali sans chevrons");

        assertThatThrownBy(() -> verifierQueLaReglePorteLesChevrons(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-1")
                .hasMessageContaining("chevrons");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSanctionQuiNEstPasUneReparation() {
        List<ItemLu> items = fautif("Adam a triché sans réparation");

        assertThatThrownBy(() -> verifierQueLesSanctionsSontDesReparations(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-1")
                .hasMessageContaining("pas une réparation");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSituationDeRappelQuiDecritUnDegat() {
        List<ItemLu> items = fautif("Hugo a cassé un rappel");

        assertThatThrownBy(() -> verifierCeQuiEstAbime(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-5")
                .hasMessageContaining("répare un tort");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePersonneQualifiee() {
        List<ItemLu> items = fautif("Enzo méchant");

        assertThatThrownBy(() -> verifierLesRefusDAvance(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-2")
                .hasMessageContaining("méchant");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneHumiliationEnDistracteur() {
        List<ItemLu> items = fautif("Jade a taché une humiliation");

        assertThatThrownBy(() -> verifierLesRefusDAvance(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-2")
                .hasMessageContaining("devant toute la classe");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePrivationDeRepas() {
        List<ItemLu> items = fautif("Samir a cassé une privation");

        assertThatThrownBy(() -> verifierLesRefusDAvance(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-2")
                .hasMessageContaining("sans goûter");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnActeSansPersonnageNomme() {
        List<ItemLu> items = fautif("Quelqu'un a cassé");

        assertThatThrownBy(() -> verifierQueLActeEstCeluiDUnPersonnage(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RSA-CE1-2")
                .hasMessageContaining("ne nomme aucun personnage");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceQuiSAdresseALEnfant() {
        List<ItemLu> items = fautif("Tu as cassé");

        assertThatThrownBy(() -> verifierQueLActeEstCeluiDUnPersonnage(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-1")
                .hasMessageContaining("s'adresse à l'enfant");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuFoyer() {
        List<ItemLu> items = fautif("Lina a cassé chez elle");

        assertThatThrownBy(() -> verifierQueLActeEstCeluiDUnPersonnage(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("EMC-1")
                .hasMessageContaining("mot du foyer");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesFamillesDeclareesQuiNeSontPasCellesDesDistracteurs() {
        List<ItemLu> items = fautif("Tom a salé les familles");

        assertThatThrownBy(() -> verifierLesFamilles(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("ses distracteurs sont de familles");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleQueLaCibleNAdmetPas() {
        List<ItemLu> items = fautif("Mila a cassé une famille hors cible");

        assertThatThrownBy(() -> verifierLesFamilles(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("que sa cible n'admet pas");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPremierMotQuiDesigneLaBonneReponse() {
        List<ItemLu> items = fautif("Inès a jeté un intrus");

        assertThatThrownBy(() -> verifierQueLePremierMotNeDonnePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("premier mot");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        List<ItemLu> items = fautif("Léo a cassé avec texte");

        assertThatThrownBy(() -> verifierLesChampsServis(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeVingtEtUnMots() {
        List<ItemLu> items = fautif("Aya a cassé un énoncé");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1")
                .hasMessageContaining("vingt mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeQuatrePhrases() {
        List<ItemLu> items = fautif("Zoé a cassé. Quatre");

        assertThatThrownBy(() -> verifierLesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-2")
                .hasMessageContaining("trois phrases");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionDeSeptMots() {
        List<ItemLu> items = fautif("Omar a cassé en sept mots");

        assertThatThrownBy(() -> verifierLesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5")
                .hasMessageContaining("six mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeTreizeMots() {
        List<ItemLu> items = fautif("Lina a cassé avec un long indice");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("douze mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<ItemLu> items = fautif("Tom a cassé avec la réponse");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("contient une proposition");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceIdentiqueSurToutLePaquet() {
        List<ItemLu> items = fautif("Mila a cassé identique");

        assertThatThrownBy(() -> verifierQueLesIndicesDiffèrent(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("identique");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseToujoursLaPlusLongue() {
        List<ItemLu> items = fautif("Hugo a cassé toujours le plus long");

        assertThatThrownBy(() -> verifierLaPlusLongue(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("strictement la plus longue");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEspaceOrdinaireAvantUnPointDInterrogation() {
        List<ItemLu> items = fautif("Enzo a cassé avec une espace ordinaire");

        assertThatThrownBy(() -> verifierLaTypographie(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1")
                .hasMessageContaining("espace ordinaire");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnChevronSansEspaceInsecable() {
        List<ItemLu> items = fautif("Jade a cassé avec un chevron collé");

        assertThatThrownBy(() -> verifierLaTypographie(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1")
                .hasMessageContaining("chevron");
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
