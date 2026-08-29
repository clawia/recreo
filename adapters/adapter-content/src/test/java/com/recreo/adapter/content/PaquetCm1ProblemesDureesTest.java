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
 * S13-3 (DEC-0053, docs/backlog/backlog.md, section "Sprint 13") et l'arbitrage prealable de
 * nadia-prof (squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md,
 * section 3, D-1 a D-11, D-9 reformulee le 2026-08-28 par
 * squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md) : le paquet
 * "cm1-problemes-durees", joue par "bandit-manchot-problemes-durees-cm1".
 *
 * <p>Ne couvre que les criteres d'acceptation automatisables. L'exactitude arithmetique est
 * recalculee ici a partir des instants et des durees lus dans l'enonce, jamais recopiee. La
 * plausibilite pedagogique des distracteurs, la clarte des enonces et des indices, et le niveau
 * tel qu'il se joue restent le jugement de nadia-prof (D-6, D-9, D-10, section 4 de l'arbitrage).
 * Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, rang de la bonne reponse) sont deja
 * derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCm1ProblemesDureesTest {

    private static final String PAQUET = "cm1-problemes-durees";

    /** D-1 : un item de calcul de duree pose la question sur l'ecart entre deux instants. */
    private static final Pattern QUESTION_DUREE = Pattern.compile("Combien de temps");

    /** D-1 : un item d'horaire d'arrivee pose la question sur l'instant final. */
    private static final Pattern QUESTION_ARRIVEE = Pattern.compile("[AÀ] quelle heure");

    /**
     * D-2 : un instant s'ecrit en abrege ("10 h 15", "9 h 05"). Le mot entier "heure(s)" est
     * exclu par la frontiere de mot qui suit "h" : "heure" ne matche jamais ce motif.
     */
    private static final Pattern INSTANT = Pattern.compile("(\\d+)\\s*h\\b(?:\\s*(\\d+))?");

    /** D-2 : une duree s'ecrit en toutes lettres ("25 minutes", "1 heure 15 minutes", "1 heure"). */
    private static final Pattern DUREE_LETTRES =
            Pattern.compile("^(?:(\\d+) heures?)?\\s*(?:(\\d+) minutes?)?$");

    /** Extrait la duree annoncee dans l'enonce d'un item d'horaire d'arrivee ("dure ... ."). */
    private static final Pattern DUREE_DANS_ENONCE = Pattern.compile("dure ([^.]+)\\.");

    private static int minutesDeInstant(Matcher m) {
        int heures = Integer.parseInt(m.group(1));
        int minutes = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
        return heures * 60 + minutes;
    }

    private static int minutesDeDuree(String texte) {
        Matcher m = DUREE_LETTRES.matcher(texte.strip());
        assertThat(m.matches()).as("'%s' : une duree s'ecrit en toutes lettres (D-2)", texte).isTrue();
        int heures = m.group(1) == null ? 0 : Integer.parseInt(m.group(1));
        int minutes = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
        assertThat(m.group(1) != null || m.group(2) != null)
                .as("'%s' : une duree porte au moins une heure ou des minutes", texte).isTrue();
        return heures * 60 + minutes;
    }

    private static boolean estCalculDeDuree(Question item) {
        boolean duree = QUESTION_DUREE.matcher(item.enonce()).find();
        boolean arrivee = QUESTION_ARRIVEE.matcher(item.enonce()).find();
        assertThat(duree ^ arrivee)
                .as("'%s' : releve exactement d'une des deux formes du referentiel (D-1)", item.enonce())
                .isTrue();
        return duree;
    }

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems()).as("D-11 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void chaqueFormeEstRepresenteeParAuMoinsUnTiersDuPaquet() {
        List<Question> items = tousLesItems();
        long calculDeDuree = items.stream().filter(PaquetCm1ProblemesDureesTest::estCalculDeDuree).count();
        long horaireDArrivee = items.size() - calculDeDuree;

        assertThat(calculDeDuree)
                .as("D-1 : le calcul de duree porte au moins un tiers du paquet")
                .isGreaterThanOrEqualTo((long) Math.ceil(items.size() / 3.0));
        assertThat(horaireDArrivee)
                .as("D-1 : l'horaire d'arrivee porte au moins un tiers du paquet")
                .isGreaterThanOrEqualTo((long) Math.ceil(items.size() / 3.0));
    }

    @Test
    void aucunItemNeReleveDeLaLectureDeLHeureOuDUneConversionSeule() {
        for (Question item : tousLesItems()) {
            assertThat(item.enonce())
                    .as("'%s' : ni lecture de l'heure ni conversion d'unite seule (D-1)", item.enonce())
                    .doesNotContainIgnoringCase("c'est...")
                    .doesNotContain("combien de minutes dans");
        }
    }

    @Test
    void lesInstantsSontAbregesEtLesDureesSontEnToutesLettres() {
        for (Question item : tousLesItems()) {
            boolean calculDeDuree = estCalculDeDuree(item);
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                if (calculDeDuree) {
                    assertThat(proposition)
                            .as("'%s' (item '%s') : une duree ne porte jamais l'abrege 'h' (D-2)",
                                    proposition, item.enonce())
                            .doesNotMatch(".*\\d+\\s*h\\b.*");
                    minutesDeDuree(proposition);
                } else {
                    assertThat(proposition)
                            .as("'%s' (item '%s') : un instant ne porte jamais 'heure' ou 'minute' en"
                                    + " toutes lettres (D-2)", proposition, item.enonce())
                            .doesNotContain("heure")
                            .doesNotContain("minute");
                    assertThat(INSTANT.matcher(proposition).matches())
                            .as("'%s' (item '%s') : un instant s'ecrit 'H h MM' (D-2)",
                                    proposition, item.enonce())
                            .isTrue();
                }
            }
        }
    }

    @Test
    void aucunFranchissementDeMinuitAucuneSecondeAucuneAmbiguiteMatinApresMidi() {
        for (Question item : tousLesItems()) {
            assertThat(item.enonce())
                    .as("'%s' : aucune seconde, aucun minuit, aucune ambiguite matin/apres-midi (D-3)",
                            item.enonce())
                    .doesNotContainIgnoringCase("seconde")
                    .doesNotContainIgnoringCase("minuit")
                    .doesNotContainIgnoringCase("matin")
                    .doesNotContainIgnoringCase("après-midi")
                    .doesNotContainIgnoringCase("apres-midi");
        }
    }

    @Test
    void lesMinutesDesInstantsSontDesMultiplesDeCinq() {
        for (Question item : tousLesItems()) {
            Matcher m = INSTANT.matcher(item.enonce());
            while (m.find()) {
                int minutes = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
                assertThat(minutes % 5)
                        .as("'%s' : les minutes d'un instant sont des multiples de 5 (D-3, D-4)",
                                item.enonce())
                        .isZero();
            }
        }
    }

    @Test
    void auPlusLaMoitieDuPaquetNUtiliseQueDesQuartsDHeure() {
        List<Question> items = tousLesItems();
        long itemsAQuartsDHeure = 0;
        for (Question item : items) {
            Matcher m = INSTANT.matcher(item.enonce());
            boolean toutesQuarts = true;
            boolean auMoinsUnInstant = false;
            while (m.find()) {
                auMoinsUnInstant = true;
                int minutes = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
                if (minutes != 0 && minutes != 15 && minutes != 30 && minutes != 45) {
                    toutesQuarts = false;
                }
            }
            assertThat(auMoinsUnInstant).as("'%s' : porte au moins un instant", item.enonce()).isTrue();
            if (toutesQuarts) {
                itemsAQuartsDHeure++;
            }
        }
        assertThat(itemsAQuartsDHeure)
                .as("D-4 : au plus la moitie du paquet n'utilise que des quarts d'heure")
                .isLessThanOrEqualTo(items.size() / 2);
    }

    @Test
    void chaqueDureeRecalculeeResteSousCinqHeuresEtLeFranchissementDUneHeureRondeEstBienReparti() {
        List<Question> items = tousLesItems();
        int franchissent = 0;
        for (Question item : items) {
            boolean calculDeDuree = estCalculDeDuree(item);
            int dureeMinutes;
            boolean franchit;
            if (calculDeDuree) {
                Matcher m = INSTANT.matcher(item.enonce());
                assertThat(m.find()).as("'%s' : porte un instant de depart", item.enonce()).isTrue();
                int depart = minutesDeInstant(m);
                assertThat(m.find()).as("'%s' : porte un instant d'arrivee", item.enonce()).isTrue();
                int arrivee = minutesDeInstant(m);
                dureeMinutes = arrivee - depart;
                franchit = arrivee / 60 > depart / 60;

                assertThat(minutesDeDuree(item.bonneReponse()))
                        .as("'%s' : la duree annoncee est l'ecart reel entre les deux instants",
                                item.enonce())
                        .isEqualTo(dureeMinutes);
            } else {
                Matcher instant = INSTANT.matcher(item.enonce());
                assertThat(instant.find()).as("'%s' : porte un instant de depart", item.enonce()).isTrue();
                int depart = minutesDeInstant(instant);
                Matcher duree = DUREE_DANS_ENONCE.matcher(item.enonce());
                assertThat(duree.find()).as("'%s' : nomme la duree ('dure ...')", item.enonce()).isTrue();
                dureeMinutes = minutesDeDuree(duree.group(1));
                int arrivee = depart + dureeMinutes;
                franchit = arrivee / 60 > depart / 60;

                Matcher bonneReponse = INSTANT.matcher(item.bonneReponse());
                assertThat(bonneReponse.matches())
                        .as("'%s' : la bonne reponse est un instant", item.bonneReponse()).isTrue();
                assertThat(minutesDeInstant(bonneReponse))
                        .as("'%s' : l'horaire d'arrivee est le depart plus la duree", item.enonce())
                        .isEqualTo(arrivee);
            }
            assertThat(dureeMinutes)
                    .as("'%s' : la duree reste sous cinq heures (D-5)", item.enonce())
                    .isLessThanOrEqualTo(5 * 60);
            if (franchit) {
                franchissent++;
            }
        }
        double ratio = (double) franchissent / items.size();
        assertThat(ratio)
                .as("D-5 : entre un tiers et deux tiers du paquet franchit une heure ronde (%d sur %d)",
                        franchissent, items.size())
                .isGreaterThanOrEqualTo(1.0 / 3.0)
                .isLessThanOrEqualTo(2.0 / 3.0);
    }

    @Test
    void chaqueEnonceTientEnAuPlusDeuxPhrasesEtVingtCinqMots() {
        for (Question item : tousLesItems()) {
            String enonce = item.enonce();
            long phrases = enonce.chars().filter(c -> c == '.' || c == '?').count();
            assertThat(phrases).as("'%s' : au plus deux phrases (D-8)", enonce).isLessThanOrEqualTo(2);

            String sansPonctuation = enonce.replace("?", "").replace(".", "").strip();
            int mots = sansPonctuation.isEmpty() ? 0 : sansPonctuation.split("\\s+").length;
            assertThat(mots).as("'%s' : au plus vingt-cinq mots (D-8)", enonce).isLessThanOrEqualTo(25);
        }
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
