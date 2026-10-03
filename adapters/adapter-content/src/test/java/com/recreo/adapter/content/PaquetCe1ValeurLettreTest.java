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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S50-FRA-VALEUR-LETTRE ({@code docs/backlog/backlog.md}, sprint 50 - entree non encore ecrite au
 * moment de ce commit, voir le message), le paquet {@code ce1-valeur-lettre}, ligne
 * {@code FRA-CE1-02} - determiner la valeur d'une lettre selon les lettres qui l'entourent.
 * Arbitrage pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 3.2, conditions
 * {@code VLE-CE1-1} a {@code VLE-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), et avis de faisabilite de theo-archi
 * ({@code squad/rapports/avis-faisabilite-s50-cinq-stories.md}, section 3, V1).
 *
 * <p><b>Ce que ce test verifie, a la difference de {@code PaquetCe1GraphemesComplexesTest}.</b> La
 * regle de lecture de {@code c}, {@code g} et {@code s} est totale sur les mots admis (piege 1 de
 * l'avis) : ce test la recalcule depuis le mot cite entre guillemets de chaque item et verifie
 * qu'elle rejoint a la fois la valeur declaree par le marqueur et la bonne reponse servie - c'est
 * l'exactitude de chaque item, verifiee item par item, jamais laissee a {@code nadia-prof}.
 *
 * <p><b>Chaque item declare sa derivation</b> a cote de l'enonce, dans un commentaire YAML de la
 * forme {@code # VLE-CE1: lettre=x;valeur=dure|douce;graphie=y[;insertion=oui]}, relu par ce test
 * plutot que recopie du YAML.
 */
class PaquetCe1ValeurLettreTest {

    private static final String PAQUET = "ce1-valeur-lettre";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1ValeurLettreTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1ValeurLettreTest";

    /** Une ligne "- enonce:" du fichier source, avec son marqueur VLE-CE1 en commentaire. */
    private static final Pattern LIGNE_ITEM =
            Pattern.compile("- enonce: \"([^\"]*)\"\\s*#\\s*VLE-CE1:\\s*lettre=([cgs]);"
                    + "valeur=(dure|douce);graphie=([^;\\s]+)(?:;insertion=(oui))?\\s*$");

    /** L'en-tete "# VLE-CE1-2: c=car,cerise; g=gare,girafe; s=sel,rose" (VLE-CE1-2). */
    private static final Pattern LIGNE_REPERES =
            Pattern.compile("#\\s*VLE-CE1-2:\\s*(.+)$");
    private static final Pattern MOT_ENTRE_GUILLEMETS = Pattern.compile("« (\\S+) »");

    /** VLE-CE1-1 : les trois lettres de la ligne. */
    private static final Set<String> LETTRES_VALIDES = Set.of("c", "g", "s");

    private static final double SEUIL_LETTRE_MINIMUM = 0.25;

    private static final double SEUIL_VALEUR_MINIMUM = 1.0 / 3;

    private static final double SEUIL_INSERTION_G_MINIMUM = 1.0 / 3;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    /** Voyelles qui rendent c/g doux quand elles les suivent directement. */
    private static final Set<Character> VOYELLES_DOUCES = Set.of(
            'e', 'é', 'è', 'ê', 'ë', 'i', 'î', 'ï', 'y');

    /** Voyelles generiques, pour la regle du s seul entre deux voyelles. */
    private static final Set<Character> VOYELLES = Set.of(
            'a', 'e', 'i', 'o', 'u', 'y',
            'à', 'â', 'ä', 'é', 'è', 'ê', 'ë', 'î', 'ï', 'ô', 'ö', 'ù', 'û', 'ü', 'ÿ');

    /**
     * TRV-CE1-4 : les mots de grammaire qu'un enfant de CE1 ne lit pas seul, exclus de l'enonce et
     * de l'indice. Ecrits accentues (piege 1 de l'avis de s49, repris sans changement).
     */
    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    /** VLE-CE1-3 : les exceptions a la regle, jamais servies comme mot cible. */
    private static final List<String> LEXIQUE_VLE_CE1_3_EXCEPTIONS = List.of(
            "second", "seconde", "secondes", "seconds", "gageure", "gageures");

    private static Pattern verificateurLexique(List<String> mots) {
        return Pattern.compile("\\b(" + String.join("|", mots) + ")\\b",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
    }

    private static final Pattern VERIFICATEUR_TRV_CE1_4 = verificateurLexique(LEXIQUE_TRV_CE1_4);

    private static final Pattern VERIFICATEUR_VLE_CE1_3_EXCEPTIONS =
            verificateurLexique(LEXIQUE_VLE_CE1_3_EXCEPTIONS);

    private record Marqueur(String lettre, String valeur, String graphie, boolean insertion) {
    }

    private record Reperes(String dure, String douce) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Un (enonce -> marqueur) par ligne "- enonce:" du fichier source. */
    private static Map<String, Marqueur> marqueursParEnonce(Path fichier) {
        Map<String, Marqueur> marqueurs = new LinkedHashMap<>();
        for (String ligne : lire(fichier).split("\n", -1)) {
            Matcher matcher = LIGNE_ITEM.matcher(ligne.strip());
            if (matcher.matches()) {
                marqueurs.put(matcher.group(1), new Marqueur(matcher.group(2), matcher.group(3),
                        matcher.group(4), "oui".equals(matcher.group(5))));
            }
        }
        return marqueurs;
    }

    /** VLE-CE1-2 : les mots-reperes dure/douce par lettre, lus depuis l'en-tete du fichier. */
    private static Map<String, Reperes> reperesParLettre(Path fichier) {
        Map<String, Reperes> reperes = new LinkedHashMap<>();
        for (String ligne : lire(fichier).split("\n", -1)) {
            Matcher entete = LIGNE_REPERES.matcher(ligne.strip());
            if (entete.matches()) {
                for (String segment : entete.group(1).split(";")) {
                    String[] cleEtValeurs = segment.strip().split("=", 2);
                    assertThat(cleEtValeurs).as("en-tete VLE-CE1-2 mal forme : '%s'", segment)
                            .hasSize(2);
                    String[] valeurs = cleEtValeurs[1].split(",");
                    assertThat(valeurs).as("en-tete VLE-CE1-2, lettre '%s' : deux reperes attendus",
                            cleEtValeurs[0]).hasSize(2);
                    reperes.put(cleEtValeurs[0].strip(),
                            new Reperes(valeurs[0].strip(), valeurs[1].strip()));
                }
                return reperes;
            }
        }
        throw new AssertionError("aucun en-tete VLE-CE1-2 trouve dans " + fichier);
    }

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }

    private static List<Question> tousLesItems(ContentSource source) {
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

    private record ItemAnalyse(Question item, Marqueur marqueur, String mot) {
    }

    private static boolean estVoyelleDouce(char c) {
        return VOYELLES_DOUCES.contains(Character.toLowerCase(c));
    }

    private static boolean estVoyelle(char c) {
        return VOYELLES.contains(Character.toLowerCase(c));
    }

    /**
     * Piege 1 : la valeur de "c", calculee depuis le mot. VLE-CE1-3 : exclusion de "sc", "cc".
     *
     * <p>Ce paquet ne cible jamais "ç" (en-tete du YAML servi, question non bloquante routee a
     * camille-po/nadia-prof) : une lettre isolee sans forme ASCII sœur accentuee fait rougir
     * {@code AucunMotServiNeSEcarteDeSonAccentTest}, qui reconnait "c" (lettre ASCII) mais pas "ç"
     * seul comme forme du francais.
     */
    private static String valeurCalculeePourC(String mot) {
        String minu = mot.toLowerCase(Locale.FRENCH);
        assertThat(minu).as("'%s' : 'sc' ou 'cc' exclus pour la lettre c (VLE-CE1-3)", mot)
                .doesNotContain("sc").doesNotContain("cc");
        int idx = minu.indexOf('c');
        assertThat(idx).as("'%s' : aucun c trouve", mot).isGreaterThanOrEqualTo(0);
        assertThat(minu.indexOf('c', idx + 1))
                .as("'%s' : plus d'un c trouve (VLE-CE1-3, piege 2)", mot).isEqualTo(-1);
        char suivant = idx + 1 < minu.length() ? minu.charAt(idx + 1) : ' ';
        return estVoyelleDouce(suivant) ? "douce" : "dure";
    }

    /**
     * Piege 1 : la valeur de "g", calculee depuis le mot - "gu" devant une voyelle douce et "ge"
     * devant "a"/"o"/"u" sont les deux insertions de VLE-CE1-1. VLE-CE1-3 : exclusion de "gn".
     */
    private static String valeurCalculeePourG(String mot) {
        String minu = mot.toLowerCase(Locale.FRENCH);
        assertThat(minu).as("'%s' : 'gn' exclu pour la lettre g (VLE-CE1-3)", mot)
                .doesNotContain("gn");
        int idx = minu.indexOf('g');
        assertThat(idx).as("'%s' : aucun g trouve", mot).isGreaterThanOrEqualTo(0);
        assertThat(minu.indexOf('g', idx + 1))
                .as("'%s' : plus d'un g trouve (VLE-CE1-3, piege 2)", mot).isEqualTo(-1);
        char suivant = idx + 1 < minu.length() ? minu.charAt(idx + 1) : ' ';
        if (suivant == 'u') {
            return "dure";
        }
        if (suivant == 'e') {
            return "douce";
        }
        return estVoyelleDouce(suivant) ? "douce" : "dure";
    }

    /** VLE-CE1-1 : "gu" devant une voyelle, ou "ge" devant a/o/u - une lettre inseree. */
    private static boolean estInsertionG(String mot) {
        String minu = mot.toLowerCase(Locale.FRENCH);
        int idx = minu.indexOf('g');
        char suivant = idx + 1 < minu.length() ? minu.charAt(idx + 1) : ' ';
        if (suivant == 'u') {
            return true;
        }
        if (suivant == 'e') {
            char apres = idx + 2 < minu.length() ? minu.charAt(idx + 2) : ' ';
            return apres == 'a' || apres == 'o' || apres == 'u';
        }
        return false;
    }

    /**
     * Piege 1 : la valeur de "s", calculee depuis le mot - "ss" se lit comme une seule unite, dure
     * ; un "s" seul entre deux voyelles est doux, dur sinon. VLE-CE1-3 : exclusion du s final muet
     * et du s apres une consonne.
     */
    private static String valeurCalculeePourS(String mot) {
        String minu = mot.toLowerCase(Locale.FRENCH);
        if (minu.contains("ss")) {
            return "dure";
        }
        int idx = minu.indexOf('s');
        assertThat(idx).as("'%s' : aucun s trouve", mot).isGreaterThanOrEqualTo(0);
        assertThat(minu.indexOf('s', idx + 1))
                .as("'%s' : plus d'un s trouve (VLE-CE1-3, piege 2)", mot).isEqualTo(-1);
        assertThat(idx == minu.length() - 1)
                .as("'%s' : s final muet exclu (VLE-CE1-3)", mot).isFalse();
        char suivant = minu.charAt(idx + 1);
        if (idx == 0) {
            // Pas de lettre avant le s : il ne peut pas etre entre deux voyelles, donc dur,
            // quelle que soit la lettre qui le suit (VLE-CE1-1, "sinon").
            return "dure";
        }
        char precedent = minu.charAt(idx - 1);
        assertThat(estVoyelle(precedent))
                .as("'%s' : s precede d'une consonne '%s', exclu (VLE-CE1-3)", mot, precedent)
                .isTrue();
        return estVoyelle(suivant) ? "douce" : "dure";
    }

    private static String valeurCalculee(String lettre, String mot) {
        return switch (lettre) {
            case "c" -> valeurCalculeePourC(mot);
            case "g" -> valeurCalculeePourG(mot);
            case "s" -> valeurCalculeePourS(mot);
            default -> throw new IllegalArgumentException("lettre inconnue : " + lettre);
        };
    }

    private static List<ItemAnalyse> itemsAnalyses(ContentSource source, Path fichierMarqueurs) {
        Map<String, Marqueur> marqueurs = marqueursParEnonce(fichierMarqueurs);
        Map<String, Reperes> reperes = reperesParLettre(fichierMarqueurs);
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(source)) {
            Marqueur marqueur = marqueurs.get(item.enonce());
            assertThat(marqueur).as("aucun marqueur trouve pour '%s'", item.enonce()).isNotNull();

            assertThat(LETTRES_VALIDES)
                    .as("'%s' : lettre '%s' hors des trois lettres de la ligne (VLE-CE1-1)",
                            item.enonce(), marqueur.lettre())
                    .contains(marqueur.lettre());

            assertThat(item.distracteurs())
                    .as("'%s' : un seul distracteur (TRV-CE1-2)", item.enonce()).hasSize(1);
            assertThat(item.bonneReponse())
                    .as("'%s' : deux propositions distinctes (TRV-CE1-2)", item.enonce())
                    .isNotEqualTo(item.distracteurs().get(0));

            Matcher mot = MOT_ENTRE_GUILLEMETS.matcher(item.enonce());
            assertThat(mot.find())
                    .as("'%s' : aucun mot entre guillemets trouve (TRV-CE1-3)", item.enonce())
                    .isTrue();
            String motCible = mot.group(1);
            assertThat(mot.find())
                    .as("'%s' : un seul mot entre guillemets attendu, un second a ete trouve"
                            + " (TRV-CE1-3)", item.enonce()).isFalse();

            assertThat(item.enonce())
                    .as("'%s' : la lettre '%s' du marqueur doit etre nommee en toutes lettres"
                            + " (TRV-CE1-3, piege 4)", item.enonce(), marqueur.lettre())
                    .contains(", le " + marqueur.lettre() + " se lit comme dans quel mot");

            String valeurCalculee = valeurCalculee(marqueur.lettre(), motCible);
            assertThat(valeurCalculee)
                    .as("'%s' : valeur calculee '%s' sur le mot '%s', valeur declaree '%s'"
                            + " (piege 1, exactitude)", item.enonce(), valeurCalculee, motCible,
                            marqueur.valeur())
                    .isEqualTo(marqueur.valeur());

            if ("g".equals(marqueur.lettre())) {
                assertThat(estInsertionG(motCible))
                        .as("'%s' : insertion declaree '%s' ne correspond pas au mot '%s'"
                                        + " (VLE-CE1-1)", item.enonce(), marqueur.insertion(),
                                motCible)
                        .isEqualTo(marqueur.insertion());
            }

            Reperes reperesDeLaLettre = reperes.get(marqueur.lettre());
            assertThat(reperesDeLaLettre)
                    .as("'%s' : aucun repere declare pour la lettre '%s' (VLE-CE1-2)",
                            item.enonce(), marqueur.lettre()).isNotNull();
            String attendu = "dure".equals(marqueur.valeur())
                    ? reperesDeLaLettre.dure() : reperesDeLaLettre.douce();
            String autre = "dure".equals(marqueur.valeur())
                    ? reperesDeLaLettre.douce() : reperesDeLaLettre.dure();
            assertThat(item.bonneReponse())
                    .as("'%s' : la bonne reponse doit etre le repere de la valeur calculee"
                            + " (VLE-CE1-2, exactitude)", item.enonce()).isEqualTo(attendu);
            assertThat(item.distracteurs().get(0))
                    .as("'%s' : le distracteur doit etre l'autre repere de la lettre (VLE-CE1-2)",
                            item.enonce()).isEqualTo(autre);
            assertThat(motCible.toLowerCase(Locale.FRENCH))
                    .as("'%s' : le mot de l'enonce ne doit pas etre lui-meme un repere (VLE-CE1-2)",
                            item.enonce())
                    .isNotEqualTo(reperesDeLaLettre.dure().toLowerCase(Locale.FRENCH))
                    .isNotEqualTo(reperesDeLaLettre.douce().toLowerCase(Locale.FRENCH));

            analyses.add(new ItemAnalyse(item, marqueur, motCible));
        }
        return analyses;
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        return itemsAnalyses(paquet(PAQUET), FICHIER_CONTENU);
    }

    @Test
    void adr0081CePaquetDeclareFraCe102EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-02"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet(PAQUET)))
                .as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void trv2VlE2LaPropositionLeMotEtLExactitudeSontTenues() {
        // Les verifications elles-memes sont faites dans itemsAnalyses() : cet appel suffit a les
        // mordre (un seul distracteur, propositions distinctes, mot entre guillemets, lettre
        // nommee, exactitude, propositions = les deux reperes, mot pas lui-meme un repere).
        assertThat(itemsAnalyses()).isNotEmpty();
    }

    @Test
    void vle1ChaqueLettreEstCibleeDauMoinsUnQuartEtChaqueValeurDauMoinsUnTiersDeSaLettre() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        Map<String, Map<String, Integer>> comptes = new TreeMap<>();
        for (ItemAnalyse analyse : analyses) {
            comptes.computeIfAbsent(analyse.marqueur().lettre(), l -> new TreeMap<>())
                    .merge(analyse.marqueur().valeur(), 1, Integer::sum);
        }
        assertThat(comptes.keySet())
                .as("VLE-CE1-1 : les trois lettres de la ligne doivent toutes etre servies")
                .containsExactlyInAnyOrder("c", "g", "s");
        comptes.forEach((lettre, parValeur) -> {
            int totalLettre = parValeur.values().stream().mapToInt(Integer::intValue).sum();
            assertThat((double) totalLettre / total)
                    .as("VLE-CE1-1 : lettre '%s', %d items sur %d", lettre, totalLettre, total)
                    .isGreaterThanOrEqualTo(SEUIL_LETTRE_MINIMUM);
            assertThat(parValeur.keySet())
                    .as("VLE-CE1-1 : lettre '%s' doit servir ses deux valeurs", lettre)
                    .containsExactlyInAnyOrder("dure", "douce");
            parValeur.forEach((valeur, compte) -> assertThat((double) compte / totalLettre)
                    .as("VLE-CE1-1 : lettre '%s', valeur '%s', %d items sur %d", lettre, valeur,
                            compte, totalLettre)
                    .isGreaterThanOrEqualTo(SEUIL_VALEUR_MINIMUM));
        });
    }

    @Test
    void vle1LinsertionEstPresenteSurAuMoinsUnTiersDesItemsDeG() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        List<ItemAnalyse> itemsG = analyses.stream()
                .filter(a -> "g".equals(a.marqueur().lettre())).toList();
        assertThat(itemsG).isNotEmpty();
        long avecInsertion = itemsG.stream().filter(a -> a.marqueur().insertion()).count();
        assertThat((double) avecInsertion / itemsG.size())
                .as("VLE-CE1-1 : l'insertion d'un e ou d'un u doit porter au moins un tiers des"
                        + " items de g")
                .isGreaterThanOrEqualTo(SEUIL_INSERTION_G_MINIMUM);
    }

    @Test
    void trv2LaBonneReponseNestStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long strictementLaPlusLongue = analyses.stream()
                .filter(a -> a.item().bonneReponse().length() > a.item().distracteurs().get(0).length())
                .count();
        assertThat((double) strictementLaPlusLongue / total)
                .as("TRV-CE1-2 : la bonne reponse n'est strictement la plus longue que sur la"
                        + " moitie des items au plus")
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceOuLIndice() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_TRV_CE1_4.matcher(item.enonce()).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'enonce", item.enonce())
                    .isFalse();
            item.indice().ifPresent(indice -> assertThat(VERIFICATEUR_TRV_CE1_4.matcher(indice).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'indice", item.enonce())
                    .isFalse());
        }
    }

    @Test
    void vle3AucunMotNAppartientAuLexiqueDesExceptions() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_VLE_CE1_3_EXCEPTIONS.matcher(item.enonce()).find())
                    .as("'%s' : aucune exception de VLE-CE1-3 (second, gageure) dans l'enonce",
                            item.enonce())
                    .isFalse();
        }
    }

    @Test
    void trv5ChaqueIndiceEstPresentEtAuPlusDouzeMotsEtNeRevelePasLaBonneReponse() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : tousLesItems(paquet(PAQUET))) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            int mots = indice.strip().split("\\s+").length;
            assertThat(mots).as("'%s' : indice d'au plus douze mots (TRV-CE1-5)", indice)
                    .isLessThanOrEqualTo(12);
            indiceNeRevelePasLaBonneReponse(indice, item);
        }
        assertThat(indicesDistincts)
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    private static void indiceNeRevelePasLaBonneReponse(String indice, Question item) {
        Pattern motEntier = Pattern.compile("\\b" + Pattern.quote(item.bonneReponse()) + "\\b",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
        assertThat(motEntier.matcher(indice).find())
                .as("'%s' : l'indice revele la bonne reponse '%s' (TRV-CE1-5)", indice,
                        item.bonneReponse())
                .isFalse();
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMotsEtUneSeulePhrase() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(proposition.strip().split("\\s+").length)
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .isLessThanOrEqualTo(6);
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item declare valeur=douce
     * pour "carotte" alors que "c" devant "a" se calcule dure (piege 1).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaValeurDeclareeSansRapportAvecLeMot() {
        Path fichierFautif = CONTRE_EXEMPLE.resolve("contenu/ce1-valeur-lettre-fautif.yaml");
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-valeur-lettre-fautif");

        assertThatThrownBy(() -> itemsAnalyses(source, fichierFautif))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("piege 1")
                .hasMessageContaining("carotte");
    }
}
