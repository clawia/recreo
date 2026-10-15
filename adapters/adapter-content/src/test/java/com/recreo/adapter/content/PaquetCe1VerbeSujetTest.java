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
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S56-BR-FRA-CE1-13 ({@code squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/planning.md}), le
 * paquet {@code ce1-verbe-sujet}, ligne {@code FRA-CE1-13} - trouver le verbe d'une phrase et son
 * sujet. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.4, conditions
 * {@code VSJ-CE1-1} a {@code VSJ-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage prealable de s56, instance A
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md}, section 2,
 * conditions {@code J1} a {@code J5} et proprietes de 2.8) et avis de faisabilite de theo-archi.
 *
 * <p>Un item de sujet cite une phrase puis demande le sujet d'un verbe nomme ; un item de verbe
 * demande le verbe de la phrase. Ce que ce test ne lit pas : la justesse des 24 sujets et des 24
 * verbes, l'homonymie de {@code porte} et de {@code souffle}, le fait qu'un distracteur ne fasse
 * pas l'action du verbe (J2, J4), la lisibilite des indices pour un enfant de CE1 et la limite
 * nommee {@code H2} (la position seule du sujet). Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1VerbeSujetTest {

    private static final String PAQUET = "ce1-verbe-sujet";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1VerbeSujetTest";

    /** J5 : la consigne que l'enfant lit deja sur cinq entrees du catalogue, mot pour mot. */
    private static final String CONSIGNE_ATTENDUE = "Lis bien, puis choisis la bonne réponse.";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern PHRASE_CITEE =
            Pattern.compile("^«" + ESPACES + "(.+?)" + ESPACES + "»");

    private static final Pattern VERBE_CITE =
            Pattern.compile("du verbe «" + ESPACES + "(\\p{L}+)" + ESPACES + "»");

    private static final String QUESTION_SUJET = "Quel est le sujet du verbe";

    private static final String QUESTION_VERBE = "Quel est le verbe de la phrase";

    /** VSJ-CE1-R1 : l'indice qui dit que le sujet est avant le verbe, faux des qu'un distracteur l'est aussi. */
    private static final String INDICE_DE_LA_PLACE =
            "Le sujet est avant le verbe : regarde les mots devant.";

    /** VSJ-CE1-R1 : au moins trois items de sujet ont un distracteur avant le verbe cite. */
    private static final int MINIMUM_D_ITEMS_AVEC_DISTRACTEUR_AVANT = 3;

    private static final Set<String> PRONOMS = Set.of("il", "elle", "ils", "elles");

    /** TRV-CE1-6 : le genre de chaque prenom des phrases citees, porte ici et non deduit. */
    private static final Set<String> PRENOMS_FEMININS =
            Set.of("Mia", "Léa", "Inès", "Sofia", "Lina", "Awa");

    private static final Set<String> PRENOMS_MASCULINS =
            Set.of("Karim", "Tom", "Noé", "Sami", "Hugo");

    /** Mots qui ouvrent une phrase citee ou une question sans etre des prenoms. */
    private static final Set<String> OUVERTURES = Set.of(
            "Quel", "Elle", "Ils", "Il", "Elles", "Le", "Les", "La", "Un", "Sur");

    /** J2 et J3, copies mot pour mot (espaces insecables comprises) de l'arbitrage de s56, 2.3 et 2.4. */
    private static final List<String> ENONCES_DE_J2_ET_J3 = List.of(
            "« Tom prête son livre à Lina. » Quel est le sujet du verbe « prête » ?",
            "« Inès montre un dessin à Sami. » Quel est le sujet du verbe « montre » ?",
            "« Noé écrit une carte à Awa. » Quel est le sujet du verbe « écrit » ?",
            "« Sofia ouvre la porte à Hugo. » Quel est le sujet du verbe « ouvre » ?",
            "« Noé range ses crayons. » Quel est le verbe de la phrase ?");

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    private static final Pattern VERIFICATEUR_TRV_CE1_4 =
            Pattern.compile("\\b(" + String.join("|", LEXIQUE_TRV_CE1_4) + ")\\b", FLAGS);

    /** TRV-CE1-5 : un indice ne renvoie ni a l'oreille ni a la lecture a voix haute. */
    private static final Pattern RENVOI_A_L_OREILLE = Pattern.compile(
            "oreille|entends|écoute|dans ta tête|à voix haute|prononce", FLAGS);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1VerbeSujetTest.class.getResourceAsStream(chemin)) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<String> mots(String texte) {
        List<String> mots = new ArrayList<>();
        for (String token : texte.strip().split(ESPACES + "+")) {
            if (LETTRE_OU_CHIFFRE.matcher(token).find()) {
                mots.add(token);
            }
        }
        return mots;
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

    private static List<Question> items() {
        return tousLesItems(new YamlContentPackLoader().chargerPaquet(PAQUET));
    }

    private static List<Question> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-verbe-sujet-fautif"));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static boolean estUnItemDeSujet(Question item) {
        return item.enonce().contains(QUESTION_SUJET);
    }

    private static boolean commenceParUneMajuscule(String proposition) {
        return Character.isUpperCase(proposition.codePointAt(0));
    }

    private static String sansMajusculeInitiale(String mot) {
        return mot.substring(0, 1).toLowerCase(Locale.ROOT) + mot.substring(1);
    }

    /** Les mots de la phrase citee, ponctuation de bord retiree, casse conservee. */
    private static List<String> motsDeLaPhraseCitee(Question item) {
        Matcher phrase = PHRASE_CITEE.matcher(item.enonce());
        assertThat(phrase.find()).as("'%s' : une phrase citée entre guillemets", item.enonce()).isTrue();
        List<String> mots = new ArrayList<>();
        for (String mot : mots(phrase.group(1))) {
            mots.add(mot.replaceAll("^[^\\p{L}]+|[^\\p{L}]+$", ""));
        }
        return mots;
    }

    /**
     * Rang du premier mot de la proposition dans la phrase, casse de la premiere lettre ignoree, ou
     * -1 si la proposition n'est pas une suite de mots de la phrase.
     */
    private static int rangDansLaPhrase(List<String> phrase, String proposition) {
        List<String> voulus = mots(proposition);
        for (int debut = 0; debut + voulus.size() <= phrase.size(); debut++) {
            boolean egal = true;
            for (int i = 0; i < voulus.size() && egal; i++) {
                egal = sansMajusculeInitiale(phrase.get(debut + i))
                        .equals(sansMajusculeInitiale(voulus.get(i)));
            }
            if (egal) {
                return debut;
            }
        }
        return -1;
    }

    /**
     * J1, propriete 2 : sur aucun item la bonne reponse n'est la seule des trois propositions a
     * commencer par une majuscule.
     */
    private static void verifierQueLaMajusculeNeDesignePasLaReponse(List<Question> items) {
        for (Question item : items) {
            boolean seuleAvecMajuscule = commenceParUneMajuscule(item.bonneReponse())
                    && item.distracteurs().stream().noneMatch(PaquetCe1VerbeSujetTest::commenceParUneMajuscule);
            assertThat(seuleAvecMajuscule)
                    .as("J1 : '%s' - la bonne réponse '%s' est la seule proposition à majuscule",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** VSJ-CE1-1, propriete 3 : chaque proposition est une suite de mots de la phrase citee. */
    private static void verifierQueChaquePropositionVientDeLaPhrase(List<Question> items) {
        for (Question item : items) {
            List<String> phrase = motsDeLaPhraseCitee(item);
            for (String proposition : propositions(item)) {
                assertThat(rangDansLaPhrase(phrase, proposition))
                        .as("VSJ-CE1-1 : '%s' - la proposition '%s' n'est pas une suite de mots de la phrase",
                                item.enonce(), proposition)
                        .isGreaterThanOrEqualTo(0);
            }
        }
    }

    /**
     * TRV-CE1-6, propriete 4 : les prenoms des phrases citees, comptes en occurrences, ne vont pas
     * plus d'un de difference d'un genre a l'autre. Un prenom absent des deux listes fait echouer
     * le garde-fou en se nommant.
     */
    private static void verifierLEquilibreDesPrenoms(List<Question> items) {
        long feminins = 0;
        long masculins = 0;
        for (Question item : items) {
            for (String token : item.enonce().split("[^\\p{L}]+")) {
                if (PRENOMS_FEMININS.contains(token)) {
                    feminins++;
                } else if (PRENOMS_MASCULINS.contains(token)) {
                    masculins++;
                } else if (!token.isEmpty() && Character.isUpperCase(token.codePointAt(0))
                        && !OUVERTURES.contains(token)) {
                    throw new AssertionError("TRV-CE1-6 : '" + token + "' dans '" + item.enonce()
                            + "' est une majuscule hors des listes de prénoms et d'ouvertures");
                }
            }
        }
        assertThat(Math.abs(feminins - masculins))
                .as("TRV-CE1-6 : %d occurrences de prénoms féminins, %d de masculins, à un près",
                        feminins, masculins)
                .isLessThanOrEqualTo(1);
    }

    /** Rang du verbe cite d'un item de sujet dans sa phrase, ou -1 si le verbe n'est pas dans la phrase. */
    private static int rangDuVerbeCite(Question item, List<String> phrase) {
        Matcher verbe = VERBE_CITE.matcher(item.enonce());
        assertThat(verbe.find()).as("'%s' : un verbe cité", item.enonce()).isTrue();
        return rangDansLaPhrase(phrase, verbe.group(1));
    }

    /** Les distracteurs d'un item de sujet qui sont dans la phrase et avant le verbe cite. */
    private static List<String> distracteursAvantLeVerbe(Question item) {
        List<String> phrase = motsDeLaPhraseCitee(item);
        int rangDuVerbe = rangDuVerbeCite(item, phrase);
        return item.distracteurs().stream()
                .filter(d -> rangDuVerbe >= 0 && rangDansLaPhrase(phrase, d) >= 0
                        && rangDansLaPhrase(phrase, d) < rangDuVerbe)
                .toList();
    }

    /**
     * VSJ-CE1-2, propriete 8 amendee le 2026-10-14 : sur chaque item de sujet, la bonne reponse est
     * avant le verbe cite, au moins un distracteur est apres le verbe et au plus un est avant. Aucun
     * distracteur n'est au rang du verbe cite (VSJ-CE1-3 : un autre nom de la phrase, non le verbe),
     * y compris un groupe qui commence par lui. Une proposition hors de la phrase est l'affaire de
     * VSJ-CE1-1.
     */
    private static void verifierLaPlaceDuSujetParRapportAuVerbe(List<Question> items) {
        for (Question item : items) {
            if (!estUnItemDeSujet(item)) {
                continue;
            }
            List<String> phrase = motsDeLaPhraseCitee(item);
            int rangDuVerbe = rangDuVerbeCite(item, phrase);
            int rangDeLaReponse = rangDansLaPhrase(phrase, item.bonneReponse());
            if (rangDuVerbe >= 0 && rangDeLaReponse >= 0) {
                assertThat(rangDeLaReponse).as(
                        "VSJ-CE1-2 : '%s' - le sujet '%s' est avant le verbe", item.enonce(),
                        item.bonneReponse()).isLessThan(rangDuVerbe);
            }
            List<String> avant = distracteursAvantLeVerbe(item);
            assertThat(avant.size())
                    .as("VSJ-CE1-2 : '%s' - au plus un distracteur avant le verbe, trouvés : %s",
                            item.enonce(), avant)
                    .isLessThanOrEqualTo(1);
            List<String> auRangDuVerbe = item.distracteurs().stream()
                    .filter(d -> rangDuVerbe >= 0 && rangDansLaPhrase(phrase, d) == rangDuVerbe)
                    .toList();
            assertThat(auRangDuVerbe)
                    .as("VSJ-CE1-3 : '%s' - aucun distracteur au rang du verbe cité, trouvés : %s",
                            item.enonce(), auRangDuVerbe)
                    .isEmpty();
            long apres = item.distracteurs().stream()
                    .filter(d -> rangDuVerbe >= 0 && rangDansLaPhrase(phrase, d) > rangDuVerbe)
                    .count();
            assertThat(apres)
                    .as("VSJ-CE1-3 (a) : '%s' - au moins un distracteur après le verbe", item.enonce())
                    .isGreaterThanOrEqualTo(1);
        }
    }

    /**
     * VSJ-CE1-R1 : sur au moins trois items de sujet, un distracteur est place avant le verbe cite,
     * de sorte que la place seule ne donne pas le sujet. Le rang d'un distracteur absent de la
     * phrase est -1 et ne compte pas : la propriete 3 le refuse.
     */
    private static void verifierQueLaPlaceSeuleNeDonnePasLeSujet(List<Question> items) {
        long avecUnDistracteurAvant = items.stream()
                .filter(PaquetCe1VerbeSujetTest::estUnItemDeSujet)
                .filter(item -> !distracteursAvantLeVerbe(item).isEmpty())
                .count();
        assertThat(avecUnDistracteurAvant)
                .as("VSJ-CE1-R1 : %d items de sujet ont un distracteur avant le verbe, au moins %d attendus",
                        avecUnDistracteurAvant, MINIMUM_D_ITEMS_AVEC_DISTRACTEUR_AVANT)
                .isGreaterThanOrEqualTo(MINIMUM_D_ITEMS_AVEC_DISTRACTEUR_AVANT);
    }

    /**
     * VSJ-CE1-R1, point 1 de l'amendement : le distracteur avant le verbe ouvre la phrase et est
     * suivi d'une virgule. Que ce soit un complement de temps ou de lieu, et jamais une personne,
     * reste a la lecture de nadia-prof.
     */
    private static void verifierQueLeDistracteurAvantOuvreLaPhraseParUneVirgule(List<Question> items) {
        for (Question item : items) {
            if (!estUnItemDeSujet(item)) {
                continue;
            }
            Matcher phrase = PHRASE_CITEE.matcher(item.enonce());
            assertThat(phrase.find()).as("'%s' : une phrase citée entre guillemets", item.enonce()).isTrue();
            for (String distracteur : distracteursAvantLeVerbe(item)) {
                String texte = sansMajusculeInitiale(phrase.group(1));
                String avantLaVirgule = texte.substring(0, Math.max(texte.indexOf(','), 0));
                assertThat(avantLaVirgule)
                        .as("VSJ-CE1-R1 : '%s' - le distracteur '%s' termine le complément qui ouvre la phrase et est suivi d'une virgule",
                                item.enonce(), distracteur)
                        .endsWith(distracteur);
            }
        }
    }

    /**
     * VSJ-CE1-R1, point 2 de l'amendement : sur un item dont un distracteur est avant le verbe,
     * l'indice ne dit pas que le sujet est avant le verbe.
     */
    private static void verifierQueLIndiceNeDitPasLaPlace(List<Question> items) {
        for (Question item : items) {
            if (estUnItemDeSujet(item) && !distracteursAvantLeVerbe(item).isEmpty()) {
                assertThat(item.indice().orElse(""))
                        .as("VSJ-CE1-R1 : '%s' - l'indice ne dit pas que le sujet est avant le verbe",
                                item.enonce())
                        .isNotEqualTo(INDICE_DE_LA_PLACE);
            }
        }
    }

    /**
     * Propriete 9, lecon d'ACV-CE1-R1 : aucun indice ne contient une proposition de son item, mot
     * entier, casse ignoree.
     */
    private static void verifierQuAucunIndiceNeContientUneProposition(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            for (String proposition : propositions(item)) {
                Pattern mot = Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(proposition)
                        + "(?![\\p{L}\\p{N}])", FLAGS);
                assertThat(mot.matcher(indice).find())
                        .as("TRV-CE1-5 : '%s' - l'indice '%s' contient une proposition de son item : '%s'",
                                item.enonce(), indice, proposition)
                        .isFalse();
            }
        }
    }

    /**
     * J5, propriete 7 : la consigne du jeu, lue au catalogue, est celle que l'enfant lit deja sur
     * cinq entrees. La question est portee par chaque enonce.
     */
    private static void verifierLaConsigneDuJeu(String catalogue) {
        Map<?, ?> corps = new Yaml().load(catalogue);
        List<String> consignes = new ArrayList<>();
        for (Object entree : (List<?>) corps.get("jeux")) {
            Map<?, ?> jeu = (Map<?, ?>) entree;
            if (PAQUET.equals(jeu.get("paquet"))) {
                consignes.add(String.valueOf(jeu.get("consigne")));
            }
        }
        assertThat(consignes).as("une seule entrée du catalogue sert le paquet %s", PAQUET)
                .hasSize(1);
        assertThat(consignes.get(0))
                .as("J5 : la consigne est celle des cinq entrées existantes, mot pour mot")
                .isEqualTo(CONSIGNE_ATTENDUE);
    }

    @Test
    void adr0081CePaquetDeclareFraCe113EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-13"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1AucunItemNePorteDeTexteSupportDIllustrationNiDeVoixHaute() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : enonce, bonneReponse, distracteurs et indice")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    @Test
    void propriete10LePaquetGardeSesVingtQuatreItemsEtChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("propriété 10 : 24 items (TRV-CE1-7 : vingt au moins)").hasSize(24);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void propriete6LesCinqEnoncesDeJ2EtJ3SontDansLePaquetMotPourMot() {
        List<String> enonces = items().stream().map(Question::enonce).toList();
        for (String attendu : ENONCES_DE_J2_ET_J3) {
            assertThat(enonces.stream().filter(attendu::equals).count())
                    .as("J2, J3 : l'énoncé '%s' est dans le paquet une fois, mot pour mot", attendu)
                    .isEqualTo(1);
        }
    }

    @Test
    void propriete5LeMotCiteSortDesIndicesEtLaFormuleNeuveEstSurCinqItems() {
        List<String> indices = items().stream().map(i -> i.indice().orElseThrow()).toList();
        for (String indice : indices) {
            assertThat(indice).as("J4 : 'cité' sort des indices").doesNotContainIgnoringCase("cité");
        }
        assertThat(indices.stream().filter(i -> i.contains("qui fait l'action de ce verbe")).count())
                .as("J4 : la formule neuve est sur les cinq items qui portaient « du verbe cité »")
                .isEqualTo(5);
    }

    @Test
    void propriete2J1LaMajusculeNeDesigneJamaisLaBonneReponse() {
        verifierQueLaMajusculeNeDesignePasLaReponse(items());
    }

    @Test
    void propriete3ChaquePropositionEstUneSuiteDeMotsDeLaPhraseCitee() {
        verifierQueChaquePropositionVientDeLaPhrase(items());
    }

    @Test
    void propriete4LesPrenomsFemininsEtMasculinsSontALUnPres() {
        verifierLEquilibreDesPrenoms(items());
    }

    @Test
    void vsjCe11EtCe12QuinzeItemsDeSujetAuMoinsEtCinqPourChaqueFormeDeSujet() {
        List<Question> sujets = items().stream().filter(PaquetCe1VerbeSujetTest::estUnItemDeSujet).toList();
        long pronoms = 0;
        long nomsSeuls = 0;
        long groupesNominaux = 0;
        for (Question item : sujets) {
            String reponse = item.bonneReponse();
            int nombreDeMots = mots(reponse).size();
            if (PRONOMS.contains(sansMajusculeInitiale(reponse))) {
                pronoms++;
            } else if (nombreDeMots == 1 && (PRENOMS_FEMININS.contains(reponse)
                    || PRENOMS_MASCULINS.contains(reponse))) {
                nomsSeuls++;
            } else if (nombreDeMots >= 2 && nombreDeMots <= 3) {
                groupesNominaux++;
            } else {
                throw new AssertionError("VSJ-CE1-2 : le sujet '" + reponse + "' de '" + item.enonce()
                        + "' n'est ni un pronom, ni un prénom, ni un groupe nominal de trois mots au plus");
            }
        }
        assertThat(sujets.size()).as("VSJ-CE1-1 : au moins douze items de sujet, au moins la moitié")
                .isGreaterThanOrEqualTo(12);
        assertThat(sujets.size() * 2).isGreaterThanOrEqualTo(items().size());
        assertThat(List.of(pronoms, nomsSeuls, groupesNominaux))
                .as("VSJ-CE1-2 : pronom, nom seul, groupe nominal, chacun dans au moins cinq items")
                .allSatisfy(compte -> assertThat(compte).isGreaterThanOrEqualTo(5));
    }

    @Test
    void vsjCe12LeSujetEstAvantLeVerbeCiteAuMoinsUnDistracteurApresEtAuPlusUnAvant() {
        verifierLaPlaceDuSujetParRapportAuVerbe(items());
    }

    @Test
    void vsjCe1R1TroisItemsDeSujetAuMoinsOntUnDistracteurAvantLeVerbe() {
        verifierQueLaPlaceSeuleNeDonnePasLeSujet(items());
    }

    @Test
    void vsjCe1R1LeDistracteurAvantLeVerbeOuvreLaPhraseParUneVirgule() {
        verifierQueLeDistracteurAvantOuvreLaPhraseParUneVirgule(items());
    }

    @Test
    void vsjCe1R1SurUnItemADistracteurAvantLIndiceNeDitPasQueLeSujetEstAvantLeVerbe() {
        verifierQueLIndiceNeDitPasLaPlace(items());
    }

    @Test
    void propriete9AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void propriete11LaBonneReponseNEstStrictementLaPlusLongueQueSurDouzeItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat(plusLongue)
                .as("TRV-CE1-2 : %d items où la bonne réponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(12);
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceLesPropositionsOuLIndice() {
        for (Question item : items()) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            item.indice().ifPresent(textes::add);
            for (String texte : textes) {
                assertThat(VERIFICATEUR_TRV_CE1_4.matcher(texte).find())
                        .as("'%s' : aucun mot du lexique exclu (TRV-CE1-4)", texte).isFalse();
            }
        }
    }

    @Test
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtLesIndicesTournent() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots", indice)
                    .hasSizeLessThanOrEqualTo(12);
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("'%s' : l'indice ne renvoie pas à l'oreille (TRV-CE1-5)", indice)
                    .isFalse();
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : six indices qui tournent au moins")
                .hasSizeGreaterThanOrEqualTo(6);
    }

    @Test
    void lon5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
        }
    }

    @Test
    void propriete7J5LaConsigneDuJeuEstCelleDesCinqEntreesExistantes() {
        verifierLaConsigneDuJeu(lire(FICHIER_CATALOGUE));
    }

    /** ADR-0013 obligation 3 : le jeu fautif de J1, un item dont seule la bonne reponse a une majuscule. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaMajusculeSeule() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLaMajusculeNeDesignePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("J1")
                .hasMessageContaining("pose le livre");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de la propriete 3, une proposition hors de la phrase. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionHorsDeLaPhrase() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueChaquePropositionVientDeLaPhrase(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VSJ-CE1-1")
                .hasMessageContaining("la maison");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de la propriete 4, quatre prenoms masculins pour un feminin. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("occurrences de prénoms");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de la propriete 8, deux distracteurs avant le verbe. */
    @Test
    void leJeuFautifCommiteEstRefuseSurDeuxDistracteursAvantLeVerbe() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLaPlaceDuSujetParRapportAuVerbe(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VSJ-CE1-2")
                .hasMessageContaining("au plus un distracteur avant le verbe")
                .hasMessageContaining("Le soir, sur la table");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de VSJ-CE1-3, un distracteur egal au verbe cite. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurEgalAuVerbeCite() {
        List<Question> items = itemsFautifs().stream()
                .filter(item -> item.enonce().contains("Elles rangent"))
                .toList();

        assertThat(items).as("l'item du jeu fautif au distracteur égal au verbe cité").hasSize(1);
        assertThatThrownBy(() -> verifierLaPlaceDuSujetParRapportAuVerbe(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VSJ-CE1-3")
                .hasMessageContaining("Elles rangent les jouets dans le sac")
                .hasMessageContaining("rangent");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de VSJ-CE1-R1, un seul item avec un distracteur avant le verbe. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaPlaceSeuleQuiDonneLeSujet() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLaPlaceSeuleNeDonnePasLeSujet(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VSJ-CE1-R1")
                .hasMessageContaining("au moins 3 attendus");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du point 1 de VSJ-CE1-R1, un distracteur avant qui n'ouvre pas la phrase. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurAvantQuiNOuvrePasLaPhraseParUneVirgule() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLeDistracteurAvantOuvreLaPhraseParUneVirgule(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VSJ-CE1-R1")
                .hasMessageContaining("la table")
                .hasMessageContaining("complément qui ouvre la phrase et est suivi d'une virgule");
    }

    /** ADR-0013 obligation 3 : le jeu fautif du point 2 de VSJ-CE1-R1, l'indice de la place sur un item a distracteur avant. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLIndiceDeLaPlaceSurUnItemADistracteurAvant() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLIndiceNeDitPasLaPlace(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("VSJ-CE1-R1")
                .hasMessageContaining("l'indice ne dit pas que le sujet est avant le verbe");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de la propriete 9, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("des fraises");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif de J5. Sa consigne n'est pas celle des cinq entrees. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSurSaConsigne() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierLaConsigneDuJeu(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("J5");
    }
}
