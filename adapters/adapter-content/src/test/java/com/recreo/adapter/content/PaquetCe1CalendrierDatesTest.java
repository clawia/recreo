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
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-MAT-CE1-11 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-calendrier-dates}, ligne {@code MAT-CE1-11} - se reperer dans le calendrier par
 * la date seule, plafond {@code en cours}. Arbitrage prealable de s43
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md}, section 4,
 * conditions {@code CAL-CE1-1} a {@code CAL-CE1-5}) ; en mathematiques CE1 ne valent ni
 * {@code LON-CE1-3} a {@code -5} ni {@code TRV-CE1-1}, {@code -3}, {@code -4}, {@code -6} (francais).
 * {@code TRV-CE1-2} (longueur des propositions) et {@code TRV-CE1-5} (douze mots d'indice) sont
 * gardes ici ; {@code TRV-CE1-7} l'est par le plancher de vingt items de NB1.
 *
 * <p>Un item est range par ce que son enonce dit : "Combien de jours a le mois" est le nombre de
 * jours d'un mois, "le lendemain" ou "la veille" un decalage d'un jour, "Entre le" l'ordre de trois
 * dates, "Ecris en chiffres" ou "Lis cette date" l'ecriture d'une date. Les dates, les rangs et les
 * bonnes reponses sont recalcules sur les champs lus par le chargeur, jamais sur les commentaires du
 * fichier, avec un calendrier de 365 jours (fevrier : 28).
 *
 * <p>Deux dettes sont nommees dans ce test, chacune avec le motif qui la porte : le paquet est de
 * s53, la story limite son diff aux fichiers de test tant que {@code nadia-prof} ne decide pas une
 * correction (K2, levee le 2026-10-22 pour les items qu'elle a nommes), et ces deux ecarts ont ete
 * mesures ici et juges recevables. {@link #DETTE_DE_FAMILLE} et
 * {@link #DETTE_DE_FETES} listent les occurrences connues ; une occurrence nouvelle fait echouer le
 * test, et une entree qui ne designe plus rien aussi : la correction du paquet vide la liste dans le
 * meme commit.
 *
 * <p>Ce que ce test ne lit pas : que l'ordre ou le nom des mois ne soit pas servi pour lui-meme au-dela
 * de la forme (un test voit le debut d'un enonce, pas ce qu'il demande), la plausibilite d'une date
 * pour un enfant, qu'un indice n'enseigne rien de faux ou ne soit pas ambigu, que les distracteurs de
 * la forme "ecriture d'une date" soient les confusions que fait un enfant, et le rang de la bonne
 * reponse sur les 25 items (il n'est compte que sur les sept items de la forme "nombre de jours",
 * les seuls dont la valeur est un nombre, comme le fait la verification de {@code nadia-prof} en
 * s43). Cela reste a la lecture et au jeu de nadia-prof.
 */
class PaquetCe1CalendrierDatesTest {

    private static final String PAQUET = "ce1-calendrier-dates";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1CalendrierDatesTest";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 25;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;

    private static final List<String> MOIS = List.of("janvier", "février", "mars", "avril", "mai", "juin",
            "juillet", "août", "septembre", "octobre", "novembre", "décembre");

    /** CAL-CE1-3 : le calendrier reel, fevrier a 28 jours, aucune annee bissextile. */
    private static final int[] JOURS_PAR_MOIS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final String[] UNITES_EN_LETTRES = {"", "premier", "deux", "trois", "quatre", "cinq", "six",
            "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize",
            "dix-sept", "dix-huit", "dix-neuf"};

    private static final Map<String, Integer> JOURS_EN_LETTRES = joursEnLettres();

    /** Un jour ("1er", en chiffres ou en lettres) suivi d'un mois : "le 14 mars", "le vingt et un mai". */
    private static final Pattern DATE_ECRITE = Pattern.compile(
            "(?<![\\p{L}\\d-])(1er|\\d{1,2}|" + JOURS_EN_LETTRES.keySet().stream()
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .map(Pattern::quote).collect(Collectors.joining("|"))
                    + ") (" + String.join("|", MOIS) + ")(?![\\p{L}])");

    private static final Pattern NOMBRE_DE_JOURS = Pattern.compile("^(\\d+) jours$");

    /** CAL-CE1-1 : ni heure, ni horloge, ni saison, ni jour de la semaine. */
    private static final Pattern HORS_DE_LA_LIGNE = Pattern.compile(
            "(?<![\\p{L}])(heures?|horloge|cadran|saisons?|printemps|automne|hiver|lundi|mardi|mercredi"
                    + "|jeudi|vendredi|samedi|dimanche)(?![\\p{L}])", FLAGS);

    /** CAL-CE1-3 : aucun item ne porte sur l'annee bissextile ni sur un 29 fevrier. */
    private static final Pattern BISSEXTILE = Pattern.compile("bissextile|29 f", FLAGS);

    /** CAL-CE1-4 : aucune rentree, vacances, fete, anniversaire ni annee (backlog, critere 1). */
    private static final Pattern ANCRAGE = Pattern.compile(
            "vacances|noël|noel|rentrée|rentree|anniversaire|20[0-9][0-9]", FLAGS);

    /**
     * Occurrence admise 1, CAL-CE1-5 : le distracteur est d'aucune des trois familles que la condition
     * nomme (le nombre de jours d'un mois voisin, la date decalee d'un jour, le mois decale d'un rang).
     * Mesure le 2026-10-22 : une occurrence, dans l'item de la veille du 1er juin. nadia-prof l'a jugee
     * recevable le meme jour (famille "mois decale" ramenee a une date reelle : le 31 juin n'existe
     * pas) ; la liste reste pour qu'une occurrence nouvelle echoue.
     */
    private static final Set<String> DETTE_DE_FAMILLE = Set.of(
            "Quelle est la veille du 1er juin ? / le 30 juin");

    /**
     * Occurrences admises 2, CAL-CE1-4 : la liste est 1er janvier, 1er avril, 1er mai, 14 juillet,
     * 31 octobre, 25 decembre et la fin d'aout. Deux occurrences, toutes deux en distracteur de
     * lendemain ou de veille. nadia-prof les a jugees recevables le 2026-10-22 : CAL-CE1-4 exclut une
     * date que l'item suppose, un distracteur n'en suppose aucune ; l'en-tete du paquet le dit
     * maintenant. Le 1er septembre, recevable aussi, n'est pas dans la liste.
     */
    private static final Set<String> DETTE_DE_FETES = Set.of(
            "Quel est le lendemain du 31 mai ? / le 1er mai",
            "Quelle est la veille du 1er août ? / le 31 août");

    private static final String[] TRIO_DE_RANG = {"Combien de jours a le mois de janvier",
            "Combien de jours a le mois de mars", "Combien de jours a le mois de mai"};

    private enum Forme { NOMBRE_DE_JOURS, LENDEMAIN_OU_VEILLE, ORDRE_DE_DATES, ECRITURE_DE_LA_DATE }

    private enum Rang { BAS, MILIEU, HAUT }

    /** Un jour et un mois du calendrier de 365 jours ; le mois va de 1 a 12. */
    private record DateDuCalendrier(int jour, int mois) {

        boolean estReelle() {
            return mois >= 1 && mois <= 12 && jour >= 1 && jour <= JOURS_PAR_MOIS[mois - 1];
        }

        int rang() {
            int total = jour;
            for (int m = 0; m < mois - 1; m++) {
                total += JOURS_PAR_MOIS[m];
            }
            return total;
        }

        DateDuCalendrier decalee(int ecart) {
            int reste = rang() + ecart;
            assertThat(reste).as("décalage de %d jour(s) de %s hors de l'année", ecart, this)
                    .isBetween(1, 365);
            for (int m = 0; m < 12; m++) {
                if (reste <= JOURS_PAR_MOIS[m]) {
                    return new DateDuCalendrier(reste, m + 1);
                }
                reste -= JOURS_PAR_MOIS[m];
            }
            throw new IllegalStateException("inatteignable");
        }

        String enChiffres() {
            return "le " + (jour == 1 ? "1er" : String.valueOf(jour)) + " " + MOIS.get(mois - 1);
        }

        String enLettres() {
            return "le " + JOURS_EN_LETTRES.entrySet().stream()
                    .filter(entree -> entree.getValue() == jour).findFirst().orElseThrow().getKey()
                    + " " + MOIS.get(mois - 1);
        }
    }

    private static Map<String, Integer> joursEnLettres() {
        Map<String, Integer> jours = new LinkedHashMap<>();
        for (int jour = 1; jour <= 31; jour++) {
            String ecrit;
            if (jour < 20) {
                ecrit = UNITES_EN_LETTRES[jour];
            } else if (jour == 20) {
                ecrit = "vingt";
            } else if (jour == 30) {
                ecrit = "trente";
            } else if (jour == 21) {
                ecrit = "vingt et un";
            } else if (jour == 31) {
                ecrit = "trente et un";
            } else {
                ecrit = (jour < 30 ? "vingt-" : "trente-") + UNITES_EN_LETTRES[jour % 10];
            }
            jours.put(ecrit, jour);
        }
        return jours;
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1CalendrierDatesTest.class.getResourceAsStream(chemin)) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
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
                .chargerPaquet("ce1-calendrier-fautif"));
    }

    /** Les items fautifs dont l'enonce commence par l'un de ces debuts, pour mordre un verificateur a la fois. */
    private static List<Question> fautif(String... debutsDEnonce) {
        return itemsFautifs().stream()
                .filter(item -> List.of(debutsDEnonce).stream().anyMatch(item.enonce()::startsWith))
                .toList();
    }

    private static List<Question> fautifAvecLeTrio(String... debutsDEnonce) {
        List<String> debuts = new ArrayList<>(List.of(TRIO_DE_RANG));
        debuts.addAll(List.of(debutsDEnonce));
        return fautif(debuts.toArray(new String[0]));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static List<String> champsLus(Question item) {
        List<String> champs = new ArrayList<>(propositions(item));
        champs.add(item.enonce());
        item.indice().ifPresent(champs::add);
        return champs;
    }

    /** L'enonce et les propositions : les champs ou une date est ecrite pour etre lue comme une date. */
    private static List<String> champsDeDate(Question item) {
        List<String> champs = new ArrayList<>(propositions(item));
        champs.add(item.enonce());
        return champs;
    }

    private static String cle(Question item, String detail) {
        return item.enonce().replace('\u00A0', ' ') + " / " + detail;
    }

    private static Forme formeDe(Question item) {
        String enonce = item.enonce();
        if (enonce.startsWith("Combien de jours a le mois")) {
            return Forme.NOMBRE_DE_JOURS;
        }
        if (enonce.startsWith("Quel est le lendemain") || enonce.startsWith("Quelle est la veille")) {
            return Forme.LENDEMAIN_OU_VEILLE;
        }
        if (enonce.startsWith("Entre le")) {
            return Forme.ORDRE_DE_DATES;
        }
        if (enonce.startsWith("Écris en chiffres") || enonce.startsWith("Lis cette date")) {
            return Forme.ECRITURE_DE_LA_DATE;
        }
        return null;
    }

    private static List<Question> deLaForme(List<Question> items, Forme forme) {
        return items.stream().filter(item -> formeDe(item) == forme).toList();
    }

    private static List<DateDuCalendrier> datesEcrites(String texte) {
        List<DateDuCalendrier> dates = new ArrayList<>();
        Matcher trouve = DATE_ECRITE.matcher(texte);
        while (trouve.find()) {
            String jour = trouve.group(1);
            int numero = jour.equals("1er") ? 1
                    : jour.chars().allMatch(Character::isDigit) ? Integer.parseInt(jour)
                    : JOURS_EN_LETTRES.get(jour);
            dates.add(new DateDuCalendrier(numero, MOIS.indexOf(trouve.group(2)) + 1));
        }
        return dates;
    }

    private static DateDuCalendrier uneDate(String champ, String item) {
        List<DateDuCalendrier> dates = datesEcrites(champ);
        assertThat(dates).as("« %s » (de '%s') écrit exactement une date", champ, item).hasSize(1);
        return dates.get(0);
    }

    private static boolean estEnChiffres(String proposition) {
        Matcher trouve = DATE_ECRITE.matcher(proposition);
        assertThat(trouve.find()).as("« %s » écrit une date", proposition).isTrue();
        String jour = trouve.group(1);
        return jour.equals("1er") || jour.chars().allMatch(Character::isDigit);
    }

    /** CAL-CE1-1 : chaque item est d'une des quatre formes ; CAL-CE1-2 : trois formes au moins, un cinquieme chacune. */
    private static void verifierLesFormes(List<Question> items) {
        Map<Forme, Long> comptes = new EnumMap<>(Forme.class);
        for (Question item : items) {
            Forme forme = formeDe(item);
            assertThat(forme)
                    .as("CAL-CE1-1 : '%s' n'est d'aucune des quatre formes servies (nombre de jours d'un "
                            + "mois, lendemain ou veille, ordre de trois dates, écriture d'une date)",
                            item.enonce())
                    .isNotNull();
            comptes.merge(forme, 1L, Long::sum);
        }
        assertThat(comptes.size())
                .as("CAL-CE1-2 : au moins trois formes sur quatre sont servies (%s)", comptes)
                .isGreaterThanOrEqualTo(3);
        comptes.forEach((forme, servis) -> assertThat(5 * servis)
                .as("CAL-CE1-2 : la forme %s porte au moins un cinquième des %d items (%d portés)",
                        forme, items.size(), servis)
                .isGreaterThanOrEqualTo(items.size()));
    }

    /** CAL-CE1-1 : aucun item sur les heures, les saisons ou les jours de la semaine. */
    private static void verifierLesExclusions(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                Matcher trouve = HORS_DE_LA_LIGNE.matcher(champ);
                boolean exclu = trouve.find();
                assertThat(exclu)
                        .as("CAL-CE1-1 : '%s' parle d'une chose que la ligne exclut (« %s » dans « %s »)",
                                item.enonce(), exclu ? trouve.group() : "", champ)
                        .isFalse();
            }
        }
    }

    /** CAL-CE1-3 : un calendrier reel, fevrier a 28 jours, aucune annee bissextile, dates reelles partout. */
    private static void verifierLeCalendrierReel(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                assertThat(BISSEXTILE.matcher(champ).find())
                        .as("CAL-CE1-3 : '%s' porte sur l'année bissextile (« %s »)", item.enonce(), champ)
                        .isFalse();
            }
            for (String champ : champsDeDate(item)) {
                for (DateDuCalendrier date : datesEcrites(champ)) {
                    assertThat(date.estReelle())
                            .as("CAL-CE1-3 : « %s » (dans '%s') n'est pas une date réelle", date.enChiffres(),
                                    item.enonce())
                            .isTrue();
                }
            }
            Forme forme = formeDe(item);
            if (forme == Forme.NOMBRE_DE_JOURS) {
                verifierLeNombreDeJours(item);
            } else if (forme == Forme.LENDEMAIN_OU_VEILLE) {
                verifierLeLendemainOuLaVeille(item);
            } else if (forme == Forme.ORDRE_DE_DATES) {
                verifierLOrdreDeTroisDates(item);
            } else if (forme == Forme.ECRITURE_DE_LA_DATE) {
                verifierLEcritureDeLaDate(item);
            }
        }
    }

    private static void verifierLeNombreDeJours(Question item) {
        List<Integer> mois = new ArrayList<>();
        for (int i = 0; i < MOIS.size(); i++) {
            if (item.enonce().contains(MOIS.get(i))) {
                mois.add(i);
            }
        }
        assertThat(mois).as("CAL-CE1-3 : '%s' nomme exactement un mois", item.enonce()).hasSize(1);
        String attendue = JOURS_PAR_MOIS[mois.get(0)] + " jours";
        assertThat(item.bonneReponse())
                .as("CAL-CE1-3 : '%s' attend « %s »", item.enonce(), attendue)
                .isEqualTo(attendue);
    }

    private static void verifierLeLendemainOuLaVeille(Question item) {
        DateDuCalendrier donnee = uneDate(item.enonce(), item.enonce());
        int ecart = item.enonce().startsWith("Quel est le lendemain") ? 1 : -1;
        String attendue = donnee.decalee(ecart).enChiffres();
        assertThat(item.bonneReponse())
                .as("CAL-CE1-3 : '%s' attend « %s »", item.enonce(), attendue)
                .isEqualTo(attendue);
    }

    private static void verifierLOrdreDeTroisDates(Question item) {
        List<DateDuCalendrier> dates = datesEcrites(item.enonce());
        assertThat(dates).as("CAL-CE1-3 : '%s' écrit trois dates", item.enonce()).hasSize(3);
        Set<String> ecrites = dates.stream().map(DateDuCalendrier::enChiffres)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        assertThat(new HashSet<>(propositions(item)))
                .as("CAL-CE1-3 : les trois propositions de '%s' sont les trois dates de l'énoncé",
                        item.enonce())
                .isEqualTo(ecrites);
        List<DateDuCalendrier> rangees = dates.stream()
                .sorted(Comparator.comparingInt(DateDuCalendrier::rang)).toList();
        DateDuCalendrier attendue;
        if (item.enonce().contains("la première")) {
            attendue = rangees.get(0);
        } else if (item.enonce().contains("la dernière")) {
            attendue = rangees.get(2);
        } else {
            assertThat(item.enonce())
                    .as("CAL-CE1-3 : '%s' ne dit ni la première, ni la dernière, ni le milieu", item.enonce())
                    .contains("au milieu");
            attendue = rangees.get(1);
        }
        assertThat(item.bonneReponse())
                .as("CAL-CE1-3 : '%s' attend « %s »", item.enonce(), attendue.enChiffres())
                .isEqualTo(attendue.enChiffres());
    }

    private static void verifierLEcritureDeLaDate(Question item) {
        DateDuCalendrier donnee = uneDate(item.enonce(), item.enonce());
        boolean versLesChiffres = item.enonce().startsWith("Écris en chiffres");
        String attendue = versLesChiffres ? donnee.enChiffres() : donnee.enLettres();
        assertThat(item.bonneReponse())
                .as("CAL-CE1-3 : '%s' attend « %s » (%s)", item.enonce(), attendue,
                        versLesChiffres ? "en chiffres" : "en lettres")
                .isEqualTo(attendue);
        for (String proposition : propositions(item)) {
            assertThat(estEnChiffres(proposition))
                    .as("CAL-CE1-3 : « %s » (proposition de '%s') n'est pas écrite comme la bonne réponse",
                            proposition, item.enonce())
                    .isEqualTo(versLesChiffres);
        }
    }

    private static boolean estUneDateDeFete(DateDuCalendrier date) {
        return (date.jour() == 1 && (date.mois() == 1 || date.mois() == 4 || date.mois() == 5))
                || (date.jour() == 14 && date.mois() == 7)
                || (date.jour() == 31 && date.mois() == 10)
                || (date.jour() == 25 && date.mois() == 12)
                || (date.mois() == 8 && date.jour() >= 25);
    }

    /**
     * CAL-CE1-4 : aucun mot d'ancrage ni annee ; aucune date de la liste de l'en-tete du paquet, a la
     * dette nommee pres, qui ne doit ni grandir ni pourrir.
     */
    private static void verifierLAbsenceDAncrage(List<Question> items, Set<String> dette) {
        Set<String> trouvees = new LinkedHashSet<>();
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                assertThat(ANCRAGE.matcher(champ).find())
                        .as("CAL-CE1-4 : '%s' porte un ancrage (rentrée, vacances, anniversaire, année : « %s »)",
                                item.enonce(), champ)
                        .isFalse();
            }
            for (String champ : champsDeDate(item)) {
                for (DateDuCalendrier date : datesEcrites(champ)) {
                    if (estUneDateDeFete(date)) {
                        trouvees.add(cle(item, date.enChiffres()));
                    }
                }
            }
        }
        verifierLaDette("CAL-CE1-4", "une date de fête ou de fin d'août", trouvees, dette);
    }

    private static void verifierLaDette(String condition, String quoi, Set<String> trouvees, Set<String> dette) {
        Set<String> nouvelles = new LinkedHashSet<>(trouvees);
        nouvelles.removeAll(dette);
        assertThat(nouvelles)
                .as("%s : %s hors de la dette nommée", condition, quoi)
                .isEmpty();
        Set<String> perimees = new LinkedHashSet<>(dette);
        perimees.removeAll(trouvees);
        assertThat(perimees)
                .as("%s : dette périmée, aucune occurrence ne la porte plus (la retirer de la liste)", condition)
                .isEmpty();
    }

    /**
     * CAL-CE1-5 : les distracteurs. Forme "nombre de jours" : le nombre de jours d'un autre mois ;
     * "lendemain ou veille" : la date decalee d'un jour (de la bonne reponse, ou de la date donnee), ou le
     * meme jour un mois plus tot ou plus tard, a la dette nommee pres ; "ecriture d'une date" : le jour ou
     * le mois change, jamais les deux.
     */
    private static void verifierLesDistracteurs(List<Question> items, Set<String> dette) {
        Set<String> horsFamille = new LinkedHashSet<>();
        for (Question item : items) {
            Forme forme = formeDe(item);
            if (forme == Forme.NOMBRE_DE_JOURS) {
                for (String distracteur : item.distracteurs()) {
                    Matcher nombre = NOMBRE_DE_JOURS.matcher(distracteur);
                    assertThat(nombre.matches()).as("« %s » écrit un nombre de jours", distracteur).isTrue();
                    int jours = Integer.parseInt(nombre.group(1));
                    assertThat(Arrays.stream(JOURS_PAR_MOIS).boxed().toList())
                            .as("CAL-CE1-5 : « %s » (distracteur de '%s') n'est le nombre de jours d'aucun mois",
                                    distracteur, item.enonce())
                            .contains(jours);
                }
            } else if (forme == Forme.LENDEMAIN_OU_VEILLE) {
                DateDuCalendrier donnee = uneDate(item.enonce(), item.enonce());
                DateDuCalendrier bonne = uneDate(item.bonneReponse(), item.enonce());
                for (String distracteur : item.distracteurs()) {
                    DateDuCalendrier date = uneDate(distracteur, item.enonce());
                    boolean famille = Math.abs(date.rang() - bonne.rang()) == 1
                            || Math.abs(date.rang() - donnee.rang()) == 1
                            || (date.jour() == bonne.jour() && Math.abs(date.mois() - bonne.mois()) == 1);
                    if (!famille) {
                        horsFamille.add(cle(item, distracteur));
                    }
                }
            } else if (forme == Forme.ECRITURE_DE_LA_DATE) {
                DateDuCalendrier bonne = uneDate(item.bonneReponse(), item.enonce());
                for (String distracteur : item.distracteurs()) {
                    DateDuCalendrier date = uneDate(distracteur, item.enonce());
                    assertThat((date.jour() != bonne.jour()) != (date.mois() != bonne.mois()))
                            .as("CAL-CE1-5 : « %s » (distracteur de '%s') diffère de la bonne réponse par le "
                                    + "jour et par le mois, ou ne diffère pas", distracteur, item.enonce())
                            .isTrue();
                }
            }
        }
        verifierLaDette("CAL-CE1-5", "un distracteur d'aucune des trois familles", horsFamille, dette);
    }

    /**
     * CAL-CE1-5 : les indices ne sont pas identiques sur tout le paquet et ne donnent pas la date
     * attendue ; TRV-CE1-5 : douze mots au plus.
     */
    private static void verifierLesIndices(List<Question> items) {
        for (Question item : items) {
            long mots = List.of(item.indice().orElse("").split("[\\s\\u00A0]+")).stream()
                    .filter(mot -> mot.matches(".*[\\p{L}\\d].*")).count();
            assertThat(mots)
                    .as("TRV-CE1-5 : l'indice de '%s' porte %d mots, plus de douze", item.enonce(), mots)
                    .isLessThanOrEqualTo(12L);
            if (item.indice().orElse("").contains("creux")) {
                assertThat(item.indice().orElse(""))
                        .as("CAL-CE1-5 : l'indice de '%s' donne la règle des creux sans février, qui a 28 jours",
                                item.enonce())
                        .contains("février");
            }
            Forme forme = formeDe(item);
            if (forme != null && forme != Forme.NOMBRE_DE_JOURS) {
                assertThat(item.indice().orElse(""))
                        .as("CAL-CE1-5 : l'indice de '%s' donne la réponse", item.enonce())
                        .doesNotContain(item.bonneReponse());
            }
        }
        assertThat(items.stream().map(item -> item.indice().orElse("")).distinct().count())
                .as("CAL-CE1-5 : les indices ne sont pas identiques sur tout le paquet")
                .isGreaterThan(1L);
    }

    /** TRV-CE1-2 : la bonne reponse n'est la proposition strictement la plus longue (en caracteres) que dans la moitie des items au plus. */
    private static void verifierLaLongueurDesPropositions(List<Question> items) {
        long plusLongues = items.stream().filter(item -> item.distracteurs().stream()
                .allMatch(distracteur -> item.bonneReponse().length() > distracteur.length())).count();
        assertThat(2 * plusLongues)
                .as("TRV-CE1-2 : la bonne réponse est la plus longue sur %d des %d items, plus de la moitié",
                        plusLongues, items.size())
                .isLessThanOrEqualTo((long) items.size());
    }

    private static int nombreEnTete(String proposition) {
        Matcher nombre = NOMBRE_DE_JOURS.matcher(proposition);
        assertThat(nombre.matches()).as("« %s » écrit un nombre de jours", proposition).isTrue();
        return Integer.parseInt(nombre.group(1));
    }

    /** La valeur d'une proposition : son nombre de jours, ou le rang de sa date dans l'annee. */
    private static int valeurDe(String proposition) {
        return NOMBRE_DE_JOURS.matcher(proposition).matches() ? nombreEnTete(proposition)
                : uneDate(proposition, proposition).rang();
    }

    private static Rang rangDe(Question item) {
        List<Integer> valeurs = propositions(item).stream().map(PaquetCe1CalendrierDatesTest::valeurDe)
                .sorted().toList();
        int position = valeurs.indexOf(valeurDe(item.bonneReponse()));
        return position == 0 ? Rang.BAS : position == 2 ? Rang.HAUT : Rang.MILIEU;
    }

    /** CAL-CE1-5 : chacun des trois rangs de la bonne reponse porte de 10 % a 50 % des items de la forme "nombre de jours". */
    private static void verifierLesTroisRangs(List<Question> items) {
        verifierLesRangs(deLaForme(items, Forme.NOMBRE_DE_JOURS));
    }

    /**
     * CAL-CE1-5, lecture de nadia-prof du 2026-10-22 : le rang compte sur les 25 items, les dates rangees
     * dans l'ordre du calendrier, chacun des trois rangs de 10 % a 50 %.
     */
    private static void verifierLesTroisRangsSurTousLesItems(List<Question> items) {
        verifierLesRangs(items);
    }

    /**
     * CAL-CE1-1 : un item d'ordre compare des nombres, pas seulement des mois (refus de nadia-prof du
     * 2026-10-22) : jamais trois dates du meme jour ; au moins un item dont deux dates sont du meme mois.
     */
    private static void verifierQueLeJourDecideDansLOrdre(List<Question> items) {
        boolean unJourDecide = false;
        for (Question item : deLaForme(items, Forme.ORDRE_DE_DATES)) {
            List<DateDuCalendrier> dates = datesEcrites(item.enonce());
            assertThat(dates.stream().map(DateDuCalendrier::jour).distinct().count())
                    .as("CAL-CE1-1 : les trois dates de '%s' ont le même jour, seul l'ordre des mois décide",
                            item.enonce())
                    .isGreaterThan(1L);
            unJourDecide |= dates.stream().map(DateDuCalendrier::mois).distinct().count() < dates.size();
        }
        assertThat(unJourDecide)
                .as("CAL-CE1-1 : aucun item d'ordre ne compare des jours (deux dates du même mois)")
                .isTrue();
    }

    private static void verifierLesRangs(List<Question> mesures) {
        for (Rang rang : Rang.values()) {
            long portes = mesures.stream().filter(item -> rangDe(item) == rang).count();
            assertThat(10 * portes)
                    .as("CAL-CE1-5 : le rang %s porte au moins 10 %% des %d items (%d portés)",
                            rang, mesures.size(), portes)
                    .isGreaterThanOrEqualTo(mesures.size());
            assertThat(2 * portes)
                    .as("CAL-CE1-5 : le rang %s porte au plus 50 %% des %d items (%d portés)",
                            rang, mesures.size(), portes)
                    .isLessThanOrEqualTo(mesures.size());
        }
    }

    /**
     * LON-CE1-1 et LON-CE1-2 : vingt mots au plus, trois phrases au plus, la derniere est la question
     * quand l'enonce compte plusieurs phrases (regle de AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest :
     * une consigne en une phrase, "Ecris en chiffres : ...", n'est pas une question).
     */
    private static void verifierLaLongueurDesEnonces(List<Question> items) {
        for (Question item : items) {
            long mots = List.of(item.enonce().split("[\\s\\u00A0]+")).stream()
                    .filter(mot -> mot.matches(".*[\\p{L}\\d].*")).count();
            assertThat(mots)
                    .as("LON-CE1-1 : '%s' porte %d mots, plus de vingt", item.enonce(), mots)
                    .isLessThanOrEqualTo(20L);
            long phrases = item.enonce().chars().filter(c -> c == '.' || c == '?' || c == '!').count();
            assertThat(phrases)
                    .as("LON-CE1-2 : '%s' porte %d phrases, plus de trois", item.enonce(), phrases)
                    .isLessThanOrEqualTo(3L);
            if (phrases > 1) {
                assertThat(item.enonce())
                        .as("LON-CE1-2 : la derniere phrase de '%s' est la question", item.enonce())
                        .endsWith("?");
            }
        }
    }

    /** TYP-1 (ADR-0067) : l'espace avant ?, :, ; ou ! est insecable, dans tout ce que l'enfant lit. */
    private static void verifierLEspaceInsecable(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                assertThat(champ)
                        .as("TYP-1 : '%s' porte une espace ordinaire devant un signe double (« %s »)",
                                item.enonce(), champ)
                        .doesNotContainPattern("[^\\u00A0] [?:;!]");
            }
        }
    }

    /** Aucun texte support, aucune voix : seuls ces quatre champs sont servis. */
    private static void verifierLesChampsServis(String contenu) {
        Map<?, ?> corps = new Yaml().load(contenu);
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("NB8 : aucun texteSupport ni texteAVoixHaute, seuls enonce, bonneReponse, "
                            + "distracteurs et indice")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
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

    // ----- le paquet et son entree de catalogue

    @Test
    void adr0081CePaquetDeclareMatCe111EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-11"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Mathématiques");
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
    void nb1LePaquetGardeSesVingtCinqItemsChacunATroisPropositionsEtPlusDeDixManches() {
        List<Question> items = items();
        assertThat(items).hasSize(NOMBRE_D_ITEMS_ATTENDU);
        assertThat(items.size()).as("FER-2 : strictement plus d'items que les dix manches")
                .isGreaterThan(10);
        for (Question item : items) {
            assertThat(item.distracteurs()).as("deux distracteurs pour '%s'", item.enonce()).hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("trois propositions distinctes pour '%s'", item.enonce()).hasSize(3);
        }
    }

    @Test
    void nb8AucunItemNePorteDeTexteSupportNiDeVoix() {
        verifierLesChampsServis(lire(FICHIER_CONTENU));
    }

    // ----- les conditions CAL-CE1-1 a CAL-CE1-5

    @Test
    void cal1ChaqueItemEstDUneDesQuatreFormesEtAucunNeSertHeureSaisonOuJourDeLaSemaine() {
        verifierLesFormes(items());
        verifierLesExclusions(items());
    }

    @Test
    void cal2AuMoinsTroisFormesSontServiesUnCinquiemeDesItemsChacune() {
        List<Question> items = items();
        verifierLesFormes(items);
        for (Forme forme : Forme.values()) {
            assertThat(5 * deLaForme(items, forme).size())
                    .as("CAL-CE1-2 : les quatre formes sont servies, la forme %s au moins 5 items sur 25",
                            forme)
                    .isGreaterThanOrEqualTo(items.size());
        }
    }

    @Test
    void cal3LeCalendrierEstReelFevrierCompteVingtHuitJoursEtChaqueBonneReponseEstRecalculee() {
        verifierLeCalendrierReel(items());
    }

    @Test
    void cal4AucunAncrageEtAucuneDateDeFeteHorsDeLaDetteNommee() {
        verifierLAbsenceDAncrage(items(), DETTE_DE_FETES);
    }

    @Test
    void cal5LesDistracteursSontDeLaFamilleDeLaBonneReponseALaDetteNommeePres() {
        verifierLesDistracteurs(items(), DETTE_DE_FAMILLE);
    }

    @Test
    void cal5LesIndicesNeSontPasTousIdentiquesNeDonnentPasLaDateEtTiennentDouzeMots() {
        verifierLesIndices(items());
    }

    @Test
    void trv2LaBonneReponseNEstLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        verifierLaLongueurDesPropositions(items());
    }

    @Test
    void cal5ChacunDesTroisRangsDeLaBonneReponsePorteDixACinquantePourCentDesItemsDeNombreDeJours() {
        verifierLesTroisRangs(items());
    }

    @Test
    void cal5ChacunDesTroisRangsPorteDixACinquantePourCentDesVingtCinqItemsDatesRangeesDansLAnnee() {
        verifierLesTroisRangsSurTousLesItems(items());
    }

    @Test
    void cal1LeJourDecideDansAuMoinsUnItemDOrdreEtAucunNeAQueLesMoisPourDifference() {
        verifierQueLeJourDecideDansLOrdre(items());
    }

    @Test
    void lon1LonEtLon2LesEnoncesTiennentVingtMotsEtTroisPhrases() {
        verifierLaLongueurDesEnonces(items());
    }

    @Test
    void typ1LEspaceAvantUnSigneDoubleEstInsecableDansTousLesChampsLus() {
        verifierLEspaceInsecable(items());
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemQuiParleDUnJourDeLaSemaine() {
        List<Question> items = fautif("Quel jour vient après le lundi");

        assertThatThrownBy(() -> verifierLesExclusions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-1")
                .hasMessageContaining("exclut");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemQuiParleDUneHorloge() {
        List<Question> items = fautif("Que marque l'horloge");

        assertThatThrownBy(() -> verifierLesExclusions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-1")
                .hasMessageContaining("horloge");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemQuiEstDAucuneDesQuatreFormes() {
        List<Question> items = fautif("Quel mois vient après mars");

        assertThatThrownBy(() -> verifierLesFormes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-1")
                .hasMessageContaining("d'aucune des quatre formes");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPaquetQuiNeSertQueDeuxFormes() {
        List<Question> items = fautifAvecLeTrio("Entre le 5 mars");

        assertThatThrownBy(() -> verifierLesFormes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-2")
                .hasMessageContaining("au moins trois formes");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFormeQuiNePortePasUnCinquiemeDesItems() {
        List<Question> items = fautifAvecLeTrio("Quel est le lendemain du 14 mars",
                "Quel est le lendemain du 20 mars", "Quel est le lendemain du 24 décembre",
                "Entre le 5 mars");

        assertThatThrownBy(() -> verifierLesFormes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-2")
                .hasMessageContaining("un cinquième")
                .hasMessageContaining("ORDRE_DE_DATES");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurFevrierAVingtNeufJours() {
        List<Question> items = fautif("Combien de jours a le mois de février");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("attend « 28 jours »");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnNombreDeJoursFauxPourSeptembre() {
        List<Question> items = fautif("Combien de jours a le mois de septembre");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("attend « 30 jours »");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLAnneeBissextile() {
        List<Question> items = fautif("Quel est le lendemain du 28 février");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("bissextile");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneDateIrreelleEnDistracteur() {
        List<Question> items = fautif("Quel est le lendemain du 30 novembre");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("le 31 novembre")
                .hasMessageContaining("pas une date réelle");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnLendemainFaux() {
        List<Question> items = fautif("Quel est le lendemain du 14 mars");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("attend « le 15 mars »");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasLaPremiereDate() {
        List<Question> items = fautif("Entre le 5 mars, le 2 mai et le 9 avril, quelle date vient la première");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("attend « le 5 mars »");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionQuiNEstPasUneDeLEnonce() {
        List<Question> items = fautif("Entre le 7 juin");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("les trois dates de l'énoncé");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEcritureEnChiffresFausse() {
        List<Question> items = fautif("Écris en chiffres");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("attend « le 15 mai » (en chiffres)");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseEnChiffresQuandLEnonceDemandeLesMots() {
        List<Question> items = fautif("Lis cette date\u00A0: le 8 mars");

        assertThatThrownBy(() -> verifierLeCalendrierReel(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-3")
                .hasMessageContaining("attend « le huit mars » (en lettres)");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneDateDeFete() {
        List<Question> items = fautif("Quel est le lendemain du 24 décembre");

        assertThatThrownBy(() -> verifierLAbsenceDAncrage(items, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-4")
                .hasMessageContaining("le 25 décembre");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLeMotVacances() {
        List<Question> items = fautif("Quel est le lendemain du 3 juillet");

        assertThatThrownBy(() -> verifierLAbsenceDAncrage(items, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-4")
                .hasMessageContaining("vacances");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneAnneeDansLEnonce() {
        List<Question> items = fautif("Quel est le lendemain du 3 mars 2025");

        assertThatThrownBy(() -> verifierLAbsenceDAncrage(items, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-4")
                .hasMessageContaining("2025");
    }

    @Test
    void uneDetteDeFetesQuiNeDesigneePlusRienEstRefusee() {
        List<Question> items = fautif("Quel est le lendemain du 14 mars");

        assertThatThrownBy(() -> verifierLAbsenceDAncrage(items, DETTE_DE_FETES))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-4")
                .hasMessageContaining("dette périmée");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnNombreDeJoursQueAucunMoisNePorte() {
        List<Question> items = fautif("Combien de jours a le mois de juin");

        assertThatThrownBy(() -> verifierLesDistracteurs(items, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("32 jours");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurDAucuneDesTroisFamilles() {
        List<Question> items = fautif("Quel est le lendemain du 20 mars");

        assertThatThrownBy(() -> verifierLesDistracteurs(items, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("le 25 mars");
    }

    @Test
    void uneDetteDeFamilleQuiNeDesigneePlusRienEstRefusee() {
        List<Question> items = fautif("Quel est le lendemain du 14 mars");

        assertThatThrownBy(() -> verifierLesDistracteurs(items, DETTE_DE_FAMILLE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("dette périmée");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurQuiChangeLeJourEtLeMois() {
        List<Question> items = fautif("Lis cette date\u00A0: le 5 mars");

        assertThatThrownBy(() -> verifierLesDistracteurs(items, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("le sept avril");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiDonneLaReponse() {
        List<Question> items = fautif("Quel est le lendemain du 8 mars");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("donne la réponse");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeTropDeMots() {
        List<Question> items = fautif("Combien de jours a le mois d'octobre");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("plus de douze");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseToujoursLaPlusLongue() {
        List<Question> items = fautif("Quel est le lendemain du 10 avril", "Quel est le lendemain du 12 juin");

        assertThatThrownBy(() -> verifierLaLongueurDesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("plus de la moitié");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTroisDatesDuMemeJour() {
        List<Question> items = fautif("Entre le 11 avril");

        assertThatThrownBy(() -> verifierQueLeJourDecideDansLOrdre(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-1")
                .hasMessageContaining("même jour");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesItemsDOrdreDontAucunNeCompareDesJours() {
        List<Question> items = fautif("Entre le 5 mars");

        assertThatThrownBy(() -> verifierQueLeJourDecideDansLOrdre(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-1")
                .hasMessageContaining("compare des jours");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTroisBonnesReponsesAuMemeRangSurTousLesItems() {
        List<Question> items = fautif(TRIO_DE_RANG);

        assertThatThrownBy(() -> verifierLesTroisRangsSurTousLesItems(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("rang BAS");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseAuMilieuSurPlusDeLaMoitieDesItems() {
        List<Question> items = fautif("Quel est le lendemain du 14 mars", "Quel est le lendemain du 3 juillet",
                "Quel est le lendemain du 20 mars", "Entre le 7 juin", "Quelle est la veille du 9 octobre");

        assertThatThrownBy(() -> verifierLesTroisRangsSurTousLesItems(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("rang MILIEU porte au plus 50");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLaRegleDesCreuxSansFevrier() {
        List<Question> items = fautif("Combien de jours a le mois de novembre");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("sans février");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesIndicesTousIdentiques() {
        List<Question> items = fautif(TRIO_DE_RANG);

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("identiques");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTroisBonnesReponsesAuMemeRang() {
        List<Question> items = fautif(TRIO_DE_RANG);

        assertThatThrownBy(() -> verifierLesTroisRangs(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CAL-CE1-5")
                .hasMessageContaining("rang BAS");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeTropDeMots() {
        List<Question> items = fautif("Marie regarde le calendrier");

        assertThatThrownBy(() -> verifierLaLongueurDesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceQuiNeFinitPasParLaQuestion() {
        List<Question> items = fautif("Regarde le calendrier. Combien");

        assertThatThrownBy(() -> verifierLaLongueurDesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-2");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEspaceOrdinaireDevantLePointDInterrogation() {
        List<Question> items = fautif("Combien de jours a le mois de décembre");

        assertThatThrownBy(() -> verifierLEspaceInsecable(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-calendrier-fautif.yaml");

        assertThatThrownBy(() -> verifierLesChampsServis(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NB8");
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
