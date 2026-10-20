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
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-EMC-CE1-01 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-emc-biens-communs}, ligne {@code EMC-CE1-01} - reconnaitre ce qui appartient a
 * tous et le geste qui le protege. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, sections 2 et
 * 4.1 : {@code BCO-CE1-1}, {@code BCO-CE1-2}, {@code TRV-CE1-1}, {@code -2}, {@code -5}, {@code -6},
 * {@code LON-CE1-5}, {@code FER-1} a {@code FER-3}) et sa mesure de {@code BCO-CE1-2}
 * ({@code reponses-nadia-questions-stories-palier-3.md}, section 4).
 *
 * <p>Les champs sont lus dans le YAML par SnakeYAML ; la tache et la famille des distracteurs de
 * chaque item, que le format n'a pas de champ pour porter, sont lues dans les deux commentaires
 * qui precedent l'item ({@code BCO-CE1-1 tache N}, {@code TRV-CE1-2 : x, y}). Un commentaire est une
 * declaration d'auteur : ce test garde qu'elle existe, qu'elle est coherente avec ce que l'item
 * contient (un bien d'une seule personne porte le prenom d'un personnage, un bien de tous un des
 * trois lieux) et que les comptes tiennent, jamais qu'elle dit vrai.
 *
 * <p>Ce que ce test ne lit pas : {@code NRC-CE1-1} (aucune reprise d'un item du CP), qu'un item n'ait
 * qu'une reponse defendable ({@code EMC-5}), qu'un item sans possessif ne vise pas l'enfant, qu'un
 * geste soit bien celui qui protege le bien, que preter ne soit jamais un devoir, qu'aucun role ne
 * soit distribue par genre ({@code TRV-CE1-6}, dont il ne garde que l'equilibre des prenoms
 * declares), qu'un indice soit une methode et non la reponse, et que le registre soit celui d'un
 * enfant de CE1. Cela reste a la lecture et au jeu de nadia-prof.
 */
class PaquetCe1EmcBiensCommunsTest {

    private static final String PAQUET = "ce1-emc-biens-communs";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1EmcBiensCommunsTest";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final Pattern TACHE = Pattern.compile("BCO-CE1-1 tache ([12])");

    private static final Pattern FAMILLES = Pattern.compile("TRV-CE1-2 : ([a-d]), ([a-d])");

    /** BCO-CE1-1 et FER-1 : les trois exemples du libelle de la competence. */
    private static final Pattern CLASSE = Pattern.compile("(?<!\\p{L})classe(?!\\p{L})", FLAGS);

    private static final Pattern BIBLIOTHEQUE = Pattern.compile("(?<!\\p{L})bibliothèque(?!\\p{L})", FLAGS);

    private static final Pattern COUR = Pattern.compile("(?<!\\p{L})cour(?!\\p{L})", FLAGS);

    /** BCO-CE1-1 : ranger et remettre a sa place ne sont jamais la bonne reponse. */
    private static final Pattern RANGER = Pattern.compile("rang|remett|à sa place", FLAGS);

    /** BCO-CE1-2, mesure de nadia-prof : aucun de ces mots entiers dans ce que l'enfant lit. */
    private static final Pattern MOTS_DU_FOYER = Pattern.compile(
            "(?<!\\p{L})(ton|ta|tes|tien|tienne|tiens|tiennes|chez|maison)(?!\\p{L})", FLAGS);

    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!][\\s\\u00A0]+\\p{Lu}", FLAGS);

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

        String tache() {
            List<String> taches = commentaires.stream().map(TACHE::matcher).filter(m -> m.find())
                    .map(m -> m.group(1)).toList();
            assertThat(taches).as("BCO-CE1-1 : '%s' porte exactement une mention de tâche", enonce)
                    .hasSize(1);
            return taches.get(0);
        }

        String familles() {
            List<String> familles = commentaires.stream().map(FAMILLES::matcher).filter(m -> m.find())
                    .map(m -> m.group(1) + m.group(2)).toList();
            assertThat(familles)
                    .as("TRV-CE1-2 : '%s' déclare exactement une paire de familles de distracteurs "
                            + "(a à d)", enonce)
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
        try (InputStream flux = PaquetCe1EmcBiensCommunsTest.class.getResourceAsStream(chemin)) {
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

    private static boolean nommeUnPersonnage(String texte) {
        return GENRE_DES_PRENOMS.keySet().stream().anyMatch(prenom -> Pattern
                .compile("(?<!\\p{L})" + Pattern.quote(prenom) + "(?!\\p{L})", Pattern.UNICODE_CHARACTER_CLASS)
                .matcher(texte).find());
    }

    private static boolean nommeUnLieu(String texte) {
        return CLASSE.matcher(texte).find() || BIBLIOTHEQUE.matcher(texte).find()
                || COUR.matcher(texte).find();
    }

    /** FER-3 : deux taches, chacune d'au moins trois items. */
    private static void verifierLesTaches(List<ItemLu> items) {
        long premiere = items.stream().filter(item -> item.tache().equals("1")).count();
        long seconde = items.stream().filter(item -> item.tache().equals("2")).count();
        assertThat(Math.min(premiere, seconde))
                .as("FER-3 : la tâche la moins servie compte au moins trois items (%d et %d)", premiere,
                        seconde)
                .isGreaterThanOrEqualTo(3);
    }

    /** BCO-CE1-1 et FER-1 : les trois exemples du libelle sont chacun en bonne reponse. */
    private static void verifierLesTroisExemples(List<ItemLu> items) {
        for (Map.Entry<String, Pattern> exemple : Map.of("classe", CLASSE, "bibliothèque", BIBLIOTHEQUE,
                "cour", COUR).entrySet()) {
            assertThat(items.stream().filter(item -> exemple.getValue().matcher(item.bonneReponse()).find())
                    .count())
                    .as("BCO-CE1-1 : au moins une bonne réponse nomme « %s »", exemple.getKey())
                    .isGreaterThanOrEqualTo(1);
        }
    }

    /** BCO-CE1-1 : ni ranger ni remettre a sa place en bonne reponse. */
    private static void verifierQueRangerNEstJamaisLaBonneReponse(List<ItemLu> items) {
        for (ItemLu item : items) {
            assertThat(RANGER.matcher(item.bonneReponse()).find())
                    .as("BCO-CE1-1 : '%s' a pour bonne réponse « %s », qui range ou remet à sa place",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** BCO-CE1-2, mesure de nadia-prof : aucun mot du foyer ni possessif de deuxieme personne. */
    private static void verifierLesMotsDuFoyer(List<ItemLu> items) {
        for (ItemLu item : items) {
            for (String champ : item.champsLus()) {
                assertThat(MOTS_DU_FOYER.matcher(champ).find())
                        .as("BCO-CE1-2 : '%s' fait lire un mot du foyer ou un possessif de l'enfant (« %s »)",
                                item.enonce(), champ)
                        .isFalse();
            }
        }
    }

    /**
     * BCO-CE1-2 et TRV-CE1-2 : les familles declarees sont coherentes avec la tache et avec ce que
     * l'item contient. Un bien d'une seule personne (familles a ou b) est celui d'un personnage nomme ;
     * un bien de tous porte l'un des trois lieux.
     */
    private static void verifierLesFamilles(List<ItemLu> items) {
        for (ItemLu item : items) {
            String familles = item.familles();
            String permises = item.tache().equals("1") ? "ab" : "cd";
            assertThat(familles.chars().allMatch(famille -> permises.indexOf(famille) >= 0))
                    .as("TRV-CE1-2 : '%s' déclare les familles « %s », la tâche %s n'admet que « %s »",
                            item.enonce(), familles, item.tache(), permises)
                    .isTrue();
            if (item.tache().equals("1")) {
                assertThat(familles.charAt(0) == familles.charAt(1))
                        .as("TRV-CE1-2 : '%s' mélange deux familles de distracteurs dans une question "
                                + "d'appartenance", item.enonce())
                        .isTrue();
                boolean distracteursPersonnels = familles.charAt(0) == 'a';
                for (String distracteur : item.distracteurs()) {
                    assertThat(nommeUnPersonnage(distracteur))
                            .as("BCO-CE1-2 : « %s » (distracteur de '%s') %s", distracteur, item.enonce(),
                                    distracteursPersonnels
                                            ? "est un bien personnel, il porte le prénom d'un personnage"
                                            : "est un bien de tous, il ne porte aucun prénom")
                            .isEqualTo(distracteursPersonnels);
                    assertThat(nommeUnLieu(distracteur))
                            .as("BCO-CE1-1 : « %s » (distracteur de '%s') %s", distracteur, item.enonce(),
                                    distracteursPersonnels
                                            ? "est un bien personnel, il ne nomme aucun des trois lieux"
                                            : "est un bien de tous, il nomme la classe, la bibliothèque "
                                                    + "ou la cour")
                            .isEqualTo(!distracteursPersonnels);
                }
                assertThat(nommeUnPersonnage(item.bonneReponse()))
                        .as("BCO-CE1-2 : « %s » (bonne réponse de '%s') %s", item.bonneReponse(),
                                item.enonce(), distracteursPersonnels
                                        ? "est un bien de tous, il ne porte aucun prénom"
                                        : "est un bien personnel, il porte le prénom d'un personnage")
                        .isEqualTo(!distracteursPersonnels);
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

    private static boolean nommeLePrenom(String texte, String prenom) {
        return Pattern.compile("(?<!\\p{L})" + Pattern.quote(prenom) + "(?!\\p{L})",
                Pattern.UNICODE_CHARACTER_CLASS).matcher(texte).find();
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
    void adr0081CePaquetDeclareEmcCe101EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("EMC-CE1-01"));
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

    // ----- BCO-CE1-1 et BCO-CE1-2

    @Test
    void eb4LesDeuxTachesSontServiesEtChaqueItemEnPorteUneSeule() {
        verifierLesTaches(items());
    }

    @Test
    void eb3LesTroisExemplesDuLibelleSontChacunEnBonneReponse() {
        verifierLesTroisExemples(items());
    }

    @Test
    void eb2RangerEtRemettreASaPlaceNeSontJamaisLaBonneReponse() {
        verifierQueRangerNEstJamaisLaBonneReponse(items());
    }

    @Test
    void eb9AucunMotDuFoyerNiPossessifDeDeuxiemePersonneDansCeQueLEnfantLit() {
        verifierLesMotsDuFoyer(items());
    }

    @Test
    void bco2LesFamillesDeclareesSontCoherentesAvecLaTacheEtAvecLeContenuDeLItem() {
        verifierLesFamilles(items());
    }

    // ----- conditions transverses

    @Test
    void eb5ChaqueItemNePorteQueSesQuatreChampsSansTexteSupportNiVoix() {
        verifierLesChampsServis(items());
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
        List<ItemLu> items = itemsFautifs().stream()
                .filter(item -> !item.enonce().startsWith("Quel geste sans tâche")).toList();

        assertThatThrownBy(() -> verifierLesTaches(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansMentionDeTache() {
        List<ItemLu> items = fautif("Quel geste sans tâche");

        assertThatThrownBy(() -> verifierLesTaches(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BCO-CE1-1")
                .hasMessageContaining("mention de tâche");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesBonnesReponsesSansLesTroisExemples() {
        List<ItemLu> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesTroisExemples(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BCO-CE1-1")
                .hasMessageContaining("nomme");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiRange() {
        List<ItemLu> items = fautif("Léo a fini de lire");

        assertThatThrownBy(() -> verifierQueRangerNEstJamaisLaBonneReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BCO-CE1-1")
                .hasMessageContaining("le ranger sur l'étagère");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPossessifDeDeuxiemePersonne() {
        List<ItemLu> items = fautif("Quel objet est à ton");

        assertThatThrownBy(() -> verifierLesMotsDuFoyer(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BCO-CE1-2")
                .hasMessageContaining("ton");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuFoyerDansUneProposition() {
        List<ItemLu> items = fautif("Quel jeu est à tous");

        assertThatThrownBy(() -> verifierLesMotsDuFoyer(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("chez");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnBienPersonnelSansPersonnage() {
        List<ItemLu> items = fautif("Quel objet est à tout le monde sans prénom");

        assertThatThrownBy(() -> verifierLesFamilles(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BCO-CE1-2")
                .hasMessageContaining("prénom");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleHorsDeLaTache() {
        List<ItemLu> items = fautif("Quel geste hors famille");

        assertThatThrownBy(() -> verifierLesFamilles(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        List<ItemLu> items = fautif("Quel objet est à tous avec texte");

        assertThatThrownBy(() -> verifierLesChampsServis(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionDeSeptMots() {
        List<ItemLu> items = fautif("Quel objet est à tous en sept mots");

        assertThatThrownBy(() -> verifierLesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5")
                .hasMessageContaining("six mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeTreizeMots() {
        List<ItemLu> items = fautif("Quel objet est à tous avec un long indice");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("douze mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<ItemLu> items = fautif("Quel objet est à tous avec la réponse");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("contient une proposition");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceIdentiqueSurToutLePaquet() {
        List<ItemLu> items = fautif("Quel objet identique");

        assertThatThrownBy(() -> verifierQueLesIndicesDiffèrent(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("identique");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseToujoursLaPlusLongue() {
        List<ItemLu> items = fautif("Quel geste toujours le plus long");

        assertThatThrownBy(() -> verifierLaPlusLongue(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("strictement la plus longue");
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
