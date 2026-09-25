package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S43-MAT-DUREE (MAT-CE1-12, DEC-0099 section 9.5) : le paquet "ce1-problemes-durees", joue par
 * "bandit-manchot-problemes-durees-ce1". Arbitrage prealable de nadia-prof, conditions DUR-CE1-1 a
 * DUR-CE1-6 (squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md, section 5.3), et
 * discriminant DUR-CE1-7 pose le 2026-09-25 (docs/backlog/backlog.md, section "Sprint 43").
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables. L'exactitude arithmetique est
 * recalculee ici a partir des instants lus dans l'enonce, jamais recopiee. Les proprietes
 * generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme paquet, aucune
 * proposition egale a une autre dans un item, longueur d'enonce au CE1, rang de la bonne reponse)
 * sont deja derivees du repertoire de contenu par les garde-fous existants (ADR-0101, ADR-0102,
 * {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}) et ne sont pas repetees ici.</p>
 */
class PaquetCe1ProblemesDureesTest {

    private static final String PAQUET = "ce1-problemes-durees";

    /** DUR-CE1-1 : la seule forme servie pose la question sur l'ecart entre deux instants. */
    private static final Pattern QUESTION_DUREE = Pattern.compile("Combien de temps");

    /** DUR-CE1-3 : un instant s'ecrit "H h" ou "H h MM", jamais le mot "min". */
    private static final Pattern INSTANT = Pattern.compile("(\\d+)\\s*h\\b(?:\\s+(\\d+))?");

    /** DUR-CE1-3 : une duree porte toujours le mot "min" - "MM min" ou "H h MM min". */
    private static final Pattern DUREE = Pattern.compile("^(?:(\\d+) h )?(\\d+) min$");

    /**
     * DUR-CE1-7 clause 1 : les quatre indices de calcul de duree deja servis par
     * cm1-problemes-durees, a ne jamais reprendre verbatim.
     */
    private static final List<String> INDICES_CM1 = List.of(
            "Compte d'abord jusqu'à l'heure ronde, puis ajoute le reste des minutes.",
            "Une heure, c'est 60 minutes, pas 100 : compte bien.",
            "Regarde les deux heures données et calcule ce qui les sépare.",
            "Avance minute par minute jusqu'à l'heure ronde, puis compte ce qu'il reste.");

    /**
     * DUR-CE1-7 clause 3 : les vingt et un prenoms deja servis par cm1-problemes-durees, a ne
     * jamais reprendre.
     */
    private static final List<String> PRENOMS_CM1 = List.of("Malo", "Aya", "Hugo", "Salma", "Elio",
            "Manon", "Noah", "Iris", "Diego", "Fatou", "Milo", "Yasmine", "Nils", "Chloé", "Amir",
            "Kofi", "Paul", "Nour", "Zoé", "Adama", "Maya");

    private static int minutesDeInstant(Matcher m) {
        int heures = Integer.parseInt(m.group(1));
        int minutes = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
        return heures * 60 + minutes;
    }

    private static int minutesDeDuree(String texte) {
        Matcher m = DUREE.matcher(texte.strip());
        assertThat(m.matches())
                .as("'%s' : une duree porte toujours le mot 'min' (DUR-CE1-3)", texte).isTrue();
        int heures = m.group(1) == null ? 0 : Integer.parseInt(m.group(1));
        int minutes = Integer.parseInt(m.group(2));
        return heures * 60 + minutes;
    }

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems())
                .as("DUR-CE1-6 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void chaqueItemPoseLaSeuleFormeAutorisee() {
        for (Question item : tousLesItems()) {
            assertThat(QUESTION_DUREE.matcher(item.enonce()).find())
                    .as("'%s' : deux instants donnes, la duree ecoulee a trouver (DUR-CE1-1)",
                            item.enonce())
                    .isTrue();
            assertThat(item.enonce())
                    .as("'%s' : ni lecture de l'heure ni conversion d'unite seule (DUR-CE1-1)",
                            item.enonce())
                    .doesNotContainIgnoringCase("à quelle heure")
                    .doesNotContain("combien de minutes dans");
        }
    }

    @Test
    void lesInstantsSontAbregesEtLesDureesPortentToujoursLeMotMin() {
        for (Question item : tousLesItems()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(proposition)
                        .as("'%s' (item '%s') : une duree ne porte jamais l'abrege 'h' seul sans"
                                + " 'min' (DUR-CE1-3)", proposition, item.enonce())
                        .contains("min");
                minutesDeDuree(proposition);
            }
            Matcher instants = INSTANT.matcher(item.enonce());
            int nombreDInstants = 0;
            while (instants.find()) {
                nombreDInstants++;
            }
            assertThat(nombreDInstants)
                    .as("'%s' : deux instants abreges dans l'enonce (DUR-CE1-1, DUR-CE1-3)",
                            item.enonce())
                    .isEqualTo(2);
            assertThat(item.enonce())
                    .as("'%s' : un instant ne porte jamais le mot 'min' (DUR-CE1-3)", item.enonce())
                    .doesNotContain("min");
        }
    }

    @Test
    void aucunFranchissementDeMinuitAucuneSecondeAucuneAmbiguiteMatinApresMidi() {
        for (Question item : tousLesItems()) {
            assertThat(item.enonce())
                    .as("'%s' : aucune seconde, aucun minuit, aucune ambiguite matin/apres-midi"
                            + " (DUR-CE1-2)", item.enonce())
                    .doesNotContainIgnoringCase("seconde")
                    .doesNotContainIgnoringCase("minuit")
                    .doesNotContainIgnoringCase("matin")
                    .doesNotContainIgnoringCase("après-midi")
                    .doesNotContainIgnoringCase("apres-midi");
        }
    }

    @Test
    void lesMinutesDesInstantsValentZeroOuTrente() {
        for (Question item : tousLesItems()) {
            Matcher m = INSTANT.matcher(item.enonce());
            while (m.find()) {
                int minutes = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
                assertThat(minutes)
                        .as("'%s' : les minutes d'un instant valent 00 ou 30 (DUR-CE1-2)",
                                item.enonce())
                        .isIn(0, 30);
            }
        }
    }

    @Test
    void chaqueDureeRecalculeeResteEntreTrenteMinutesEtQuatreHeures() {
        for (Question item : tousLesItems()) {
            Matcher m = INSTANT.matcher(item.enonce());
            assertThat(m.find()).as("'%s' : porte un instant de depart", item.enonce()).isTrue();
            int depart = minutesDeInstant(m);
            assertThat(m.find()).as("'%s' : porte un instant d'arrivee", item.enonce()).isTrue();
            int arrivee = minutesDeInstant(m);

            assertThat(arrivee)
                    .as("'%s' : la meme journee, aucun franchissement de minuit (DUR-CE1-2)",
                            item.enonce())
                    .isGreaterThan(depart);

            int dureeMinutes = arrivee - depart;
            assertThat(minutesDeDuree(item.bonneReponse()))
                    .as("'%s' : la duree annoncee est l'ecart reel entre les deux instants",
                            item.enonce())
                    .isEqualTo(dureeMinutes);
            assertThat(dureeMinutes)
                    .as("'%s' : la duree cherchee vaut au moins 30 minutes et au plus 4 heures"
                            + " (DUR-CE1-2)", item.enonce())
                    .isBetween(30, 4 * 60);
        }
    }

    @Test
    void leFranchissementDeLaDemiHeureEstEntreUnTiersEtDeuxTiersDuPaquet() {
        List<Question> items = tousLesItems();
        int franchissent = 0;
        for (Question item : items) {
            Matcher m = INSTANT.matcher(item.enonce());
            assertThat(m.find()).isTrue();
            int minutesDepart = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
            assertThat(m.find()).isTrue();
            int minutesArrivee = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
            if (minutesDepart != minutesArrivee) {
                franchissent++;
            }
        }
        double ratio = (double) franchissent / items.size();
        assertThat(ratio)
                .as("DUR-CE1-4 : entre un tiers et deux tiers du paquet franchit la demi-heure"
                        + " (%d sur %d)", franchissent, items.size())
                .isGreaterThanOrEqualTo(1.0 / 3.0)
                .isLessThanOrEqualTo(2.0 / 3.0);
    }

    /** DUR-CE1-7 clause 2 : au moins un tiers du paquet porte une duree de 2 heures ou plus. */
    @Test
    void auMoinsUnTiersDuPaquetPorteUneDureeDeDeuxHeuresOuPlus() {
        List<Question> items = tousLesItems();
        long deuxHeuresOuPlus = items.stream()
                .filter(item -> minutesDeDuree(item.bonneReponse()) >= 2 * 60)
                .count();
        assertThat(deuxHeuresOuPlus)
                .as("DUR-CE1-7 clause 2 : au moins un tiers du paquet a une duree de 2 heures ou"
                        + " plus")
                .isGreaterThanOrEqualTo((long) Math.ceil(items.size() / 3.0));
    }

    /** DUR-CE1-7 clause 1 : aucun indice ne reprend verbatim l'un des quatre indices du CM1. */
    @Test
    void aucunIndiceNeReprendUnIndiceDeCm1() {
        for (Question item : tousLesItems()) {
            assertThat(item.indice())
                    .as("'%s' (item '%s') : ne reprend aucun des quatre indices de calcul de"
                            + " duree deja servis par cm1-problemes-durees (DUR-CE1-7 clause 1)",
                            item.indice(), item.enonce())
                    .isNotIn(INDICES_CM1);
        }
    }

    /** DUR-CE1-7 clause 3 : aucun enonce ne reprend un prenom deja servi par cm1-problemes-durees. */
    @Test
    void aucunEnonceNeReprendUnPrenomDeCm1() {
        for (Question item : tousLesItems()) {
            for (String prenom : PRENOMS_CM1) {
                assertThat(item.enonce())
                        .as("'%s' : ne reprend pas le prenom '%s' deja servi par"
                                + " cm1-problemes-durees (DUR-CE1-7 clause 3)", item.enonce(),
                                prenom)
                        .doesNotContain(prenom);
            }
        }
    }

    /**
     * DUR-CE1-7 clause 5 : au moins deux distracteurs portent des minutes superieures a 59, jamais
     * un seul.
     */
    @Test
    void auMoinsDeuxDistracteursPortentDesMinutesSuperieuresA59() {
        long comptes = 0;
        for (Question item : tousLesItems()) {
            for (String distracteur : item.distracteurs()) {
                Matcher m = DUREE.matcher(distracteur.strip());
                assertThat(m.matches()).isTrue();
                if (Integer.parseInt(m.group(2)) > 59) {
                    comptes++;
                }
            }
        }
        assertThat(comptes)
                .as("DUR-CE1-7 clause 5 : au moins deux distracteurs a minutes superieures a 59,"
                        + " jamais un seul")
                .isGreaterThanOrEqualTo(2);
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems() {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        ContentSource contenu = new YamlContentPackLoader().chargerPaquet(PAQUET);
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }
}
