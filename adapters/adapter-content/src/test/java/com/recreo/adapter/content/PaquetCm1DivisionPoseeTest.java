package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S28-4 (squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-
 * lots.md, section 5.4) : le paquet {@code cm1-division-posee}, seconde moitie du lot de calcul
 * pose CM1, ligne {@code MAT-CM1-05}. Les proprietes generiques (indice non vide, au plus trois
 * propositions, aucune proposition egale a une autre, dix manches contre au moins onze items du
 * chargeur) sont deja gardees par les garde-fous derives du repertoire de contenu ; ce fichier
 * verifie les conditions {@code POS-1} a {@code POS-4} de l'arbitrage, et depuis S29-6 la reserve
 * {@code POS-R1} (squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-
 * le-plafond-a-huit-lignes.md, sections 1.4 et 7.1).
 *
 * <p>{@code cm1-division-posee} est exempte du garde-fou de rang ({@code
 * LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}, table {@code
 * PAQUETS_HORS_DU_DOMAINE_NUMERIQUE}) : ses propositions ecrivent un quotient suivi du mot
 * "reste" et d'un second nombre, que la lecture de valeur generique ne consomme pas (ADR-0068).
 * {@code POS-2} est donc verifiee ici, sur le seul quotient.</p>
 *
 * <p>{@code POS-1} et {@code POS-3} sont ecrites dans le commentaire du paquet servi, pour reprise
 * mot pour mot par camille-po dans la matrice ; elles ne sont pas des proprietes calculables sur
 * les items charges et ne sont donc pas mordues ici, meme limite que {@code HIS-2} sur
 * {@code PaquetCm1HistoireGeographieTest}.</p>
 *
 * <p><b>Correction du 2026-09-06, second refus de {@code POS-R1}</b>
 * ({@code squad/conformite/FICHE-bandit-manchot-division-posee-cm1.md}, second audit s29,
 * section 3) : la propriete refusee portait sur la <b>serie</b>, pas sur un item pris seul - le
 * couple de distracteurs etait toujours le meme (quotient + 10, quotient - 2), si bien que la
 * bonne reponse etait toujours la valeur intermediaire parmi les trois quotients tries, sur les
 * onze items. {@link #leRangDeLaBonneReponseParmiLesQuotientsVarieEtAucunRangNEstMajoritaireNiAbsent}
 * mord cette regularite-la, que {@code leQuotientDesTroisPropositionsALeMemeNombreDeChiffres} ne
 * pouvait pas voir : un nombre de chiffres identique n'empeche aucunement un rang constant.</p>
 *
 * <p><b>Correction du 2026-09-06 (s30), troisieme audit, {@code POS-R2}</b> ({@code
 * squad/conformite/FICHE-bandit-manchot-division-posee-cm1.md}, troisieme audit s29, section 2 ;
 * story {@code S30-6}) : la forme qui a leve {@code POS-R1} laissait une regularite plus fine -
 * sur les items a restes distincts, la bonne reponse partageait toujours son reste avec un
 * distracteur, si bien que le distracteur au reste unique etait toujours faux ; sur les items a
 * restes egaux, le triplet de quotients etait toujours symetrique, la bonne reponse toujours au
 * milieu. {@link #posR2LaBonneReponseNEstPasToujoursDuCoteDuResteQuiSePartage} et
 * {@link #posR2LesItemsARestesEgauxNeSontPasTousSymetriques} mordent ces deux regularites.</p>
 */
class PaquetCm1DivisionPoseeTest {

    private static final String PAQUET = "cm1-division-posee";

    private static final Pattern ENONCE = Pattern.compile("^(\\d+) ÷ (\\d+) =[  ]?\\?$");

    private static final Pattern PROPOSITION = Pattern.compile("^(\\d+) reste (\\d+)$");

    private record Reponse(int quotient, int reste) {
    }

    private record ItemAnalyse(Question question, int dividende, int diviseur, Reponse bonne) {
    }

    private static Reponse reponseDe(String texte, String contexte) {
        Matcher matcher = PROPOSITION.matcher(texte);
        assertThat(matcher.matches()).as("forme de la proposition '%s' (%s)", texte, contexte).isTrue();
        return new Reponse(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(paquet(PAQUET))) {
            Matcher matcher = ENONCE.matcher(item.enonce());
            assertThat(matcher.matches()).as("forme de l'enonce '%s'", item.enonce()).isTrue();
            int dividende = Integer.parseInt(matcher.group(1));
            int diviseur = Integer.parseInt(matcher.group(2));
            Reponse bonne = reponseDe(item.bonneReponse(), item.enonce());
            analyses.add(new ItemAnalyse(item, dividende, diviseur, bonne));
        }
        return analyses;
    }

    /** FER-2 : au moins dix items servent la ligne. */
    @Test
    void lePaquetCompteAuMoinsDixItems() {
        assertThat(tousLesItems(paquet(PAQUET))).hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsToutesDistinctes() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.distracteurs()).as("distracteurs de '%s'", item.enonce()).hasSize(2);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s'", item.enonce()).hasSize(3);
        }
    }

    /**
     * POS-4 : le diviseur a un seul chiffre, sans exception. La bonne reponse est le quotient et
     * le reste exacts de la division euclidienne, et le reste est bien inferieur au diviseur.
     */
    @Test
    void leDiviseurAUnSeulChiffreEtLaBonneReponseEstLeQuotientEtLeResteExacts() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            assertThat(analyse.diviseur()).as("diviseur de '%s' : POS-4, un seul chiffre",
                    analyse.question().enonce()).isBetween(2, 9);
            assertThat(analyse.bonne().reste()).as("reste de '%s' : toujours inferieur au diviseur",
                    analyse.question().enonce()).isLessThan(analyse.diviseur());
            int attendu = analyse.bonne().quotient() * analyse.diviseur() + analyse.bonne().reste();
            assertThat(attendu).as("dividende reconstitue de '%s'", analyse.question().enonce())
                    .isEqualTo(analyse.dividende());
        }
    }

    /** POS-4 : au moins un item a reste nul, et plusieurs a reste non nul. */
    @Test
    void auMoinsUnItemAResteNulEtPlusieursAResteNonNul() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        long resteNul = analyses.stream().filter(analyse -> analyse.bonne().reste() == 0).count();
        long resteNonNul = analyses.stream().filter(analyse -> analyse.bonne().reste() != 0).count();
        assertThat(resteNul).as("POS-4 : au moins un item a reste nul").isGreaterThanOrEqualTo(1);
        assertThat(resteNonNul).as("POS-4 : plusieurs items a reste non nul").isGreaterThanOrEqualTo(2);
    }

    /**
     * POS-R1 (squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-
     * plafond-a-huit-lignes.md, sections 1.4 et 7.1) : le reste d'un distracteur est toujours
     * strictement inferieur au diviseur, exactement comme celui de la bonne reponse. Un
     * distracteur dont le reste depasse ou egale le diviseur s'elimine sans poser l'operation, par
     * la seule regle que l'indice enonce lui-meme ; c'est le defaut que la forme "quotient - 1,
     * reste + diviseur" produisait sur les onze items avant cette correction.
     */
    @Test
    void leResteDeChaqueDistracteurEstInferieurAuDiviseur() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            for (String distracteur : analyse.question().distracteurs()) {
                Reponse reponse = reponseDe(distracteur, analyse.question().enonce());
                assertThat(reponse.reste()).as("POS-R1 : reste du distracteur '%s' (item '%s')"
                                + " doit etre strictement inferieur au diviseur %d", distracteur,
                        analyse.question().enonce(), analyse.diviseur())
                        .isLessThan(analyse.diviseur());
            }
        }
    }

    /**
     * POS-2, transposee au quotient : sur chaque item, le quotient des trois propositions a le
     * meme nombre de chiffres - sinon l'enfant elimine par la longueur sans poser l'operation.
     */
    @Test
    void leQuotientDesTroisPropositionsALeMemeNombreDeChiffres() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            Reponse bonne = reponseDe(item.bonneReponse(), item.enonce());
            int chiffresBonneReponse = Integer.toString(bonne.quotient()).length();
            for (String distracteur : item.distracteurs()) {
                Reponse reponse = reponseDe(distracteur, item.enonce());
                assertThat(Integer.toString(reponse.quotient()).length())
                        .as("POS-2 : '%s' (item '%s') n'a pas le meme nombre de chiffres de"
                                + " quotient que la bonne reponse '%s'", distracteur,
                                item.enonce(), item.bonneReponse())
                        .isEqualTo(chiffresBonneReponse);
            }
        }
    }

    private enum Rang {
        LA_PLUS_PETITE, INTERMEDIAIRE, LA_PLUS_GRANDE
    }

    /**
     * POS-R1, second etat (fiche de conformite, second audit s29, section 3) : la premiere
     * correction (S29-6) garantissait un reste valide par item, mais laissait le couple de
     * distracteurs identique sur toute la serie (quotient + 10, quotient - 2), ce qui rend le rang
     * de la bonne reponse parmi les trois quotients tries constant - toujours intermediaire, sur
     * les onze items. Une strategie qui presse toujours le meme rang, sans lire l'enonce, ne doit
     * ni etre majoritaire sur le paquet, ni laisser un rang absent - le meme seuil de majorite que
     * {@link LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}, adapte a un paquet dont la
     * lecture de valeur generique ne sait pas lire les propositions (voir la Javadoc de la
     * classe).
     */
    @Test
    void leRangDeLaBonneReponseParmiLesQuotientsVarieEtAucunRangNEstMajoritaireNiAbsent() {
        Map<Rang, Integer> effectifs = new EnumMap<>(Rang.class);
        for (Rang rang : Rang.values()) {
            effectifs.put(rang, 0);
        }
        List<ItemAnalyse> analyses = itemsAnalyses();
        for (ItemAnalyse analyse : analyses) {
            List<String> distracteurs = analyse.question().distracteurs();
            int bonne = analyse.bonne().quotient();
            int d1 = reponseDe(distracteurs.get(0), analyse.question().enonce()).quotient();
            int d2 = reponseDe(distracteurs.get(1), analyse.question().enonce()).quotient();
            int rangNumerique = (d1 < bonne ? 1 : 0) + (d2 < bonne ? 1 : 0);
            Rang rang = switch (rangNumerique) {
                case 0 -> Rang.LA_PLUS_PETITE;
                case 1 -> Rang.INTERMEDIAIRE;
                default -> Rang.LA_PLUS_GRANDE;
            };
            effectifs.merge(rang, 1, Integer::sum);
        }
        int total = analyses.size();
        for (Rang rang : Rang.values()) {
            int effectif = effectifs.get(rang);
            assertThat(effectif).as("POS-R1 : le rang %s ne doit pas etre absent de la serie", rang)
                    .isGreaterThanOrEqualTo(1);
            assertThat(effectif)
                    .as("POS-R1 : le rang %s ne doit pas etre majoritaire sur la serie (%d/%d items)",
                            rang, effectif, total)
                    .isLessThanOrEqualTo(total / 2);
        }
    }

    /**
     * POS-R2 (squad/conformite/FICHE-bandit-manchot-division-posee-cm1.md, troisieme audit s29,
     * section 2 ; docs/backlog/backlog.md, story S30-6). Sur un item a restes distincts, un des
     * trois restes peut etre partage par deux propositions et le troisieme rester seul ({@code
     * BONNE_PARTAGE} si la bonne reponse est du cote du reste partage, {@code BONNE_SEULE} si elle
     * porte le reste seul). Avant cette correction, les huit items a restes distincts etaient tous
     * {@code BONNE_PARTAGE} : la bonne reponse partageait toujours son reste avec un distracteur, et
     * le distracteur au reste unique etait toujours faux - un enfant pouvait l'ecarter sans diviser.
     */
    private enum PartageDuReste {
        BONNE_PARTAGE, BONNE_SEULE, TOUS_EGAUX, TOUS_DIFFERENTS
    }

    private static PartageDuReste partageDuReste(ItemAnalyse analyse) {
        List<String> distracteurs = analyse.question().distracteurs();
        int resteBonne = analyse.bonne().reste();
        int resteD1 = reponseDe(distracteurs.get(0), analyse.question().enonce()).reste();
        int resteD2 = reponseDe(distracteurs.get(1), analyse.question().enonce()).reste();

        if (resteBonne == resteD1 && resteBonne == resteD2) {
            return PartageDuReste.TOUS_EGAUX;
        }
        if (resteD1 == resteD2) {
            return PartageDuReste.BONNE_SEULE;
        }
        if (resteBonne == resteD1 || resteBonne == resteD2) {
            return PartageDuReste.BONNE_PARTAGE;
        }
        return PartageDuReste.TOUS_DIFFERENTS;
    }

    /**
     * POS-R2, premiere moitie : sur les items ou un reste est partage par deux des trois
     * propositions et le troisieme reste seul, la bonne reponse n'est pas toujours du cote du reste
     * partage - ni, symetriquement, toujours seule a porter le reste unique. Ni l'un ni l'autre cas
     * ne doit etre majoritaire sur ces items.
     */
    @Test
    void posR2LaBonneReponseNEstPasToujoursDuCoteDuResteQuiSePartage() {
        Map<PartageDuReste, Long> comptes = itemsAnalyses().stream()
                .map(PaquetCm1DivisionPoseeTest::partageDuReste)
                .filter(partage -> partage == PartageDuReste.BONNE_PARTAGE
                        || partage == PartageDuReste.BONNE_SEULE)
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        long partagee = comptes.getOrDefault(PartageDuReste.BONNE_PARTAGE, 0L);
        long seule = comptes.getOrDefault(PartageDuReste.BONNE_SEULE, 0L);
        long total = partagee + seule;

        assertThat(partagee).as("POS-R2 : la bonne reponse n'est pas toujours du cote du reste"
                        + " partage (%d/%d items)", partagee, total)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(total / 2);
        assertThat(seule).as("POS-R2 : la bonne reponse ne porte pas toujours seule le reste"
                        + " unique non plus (%d/%d items)", seule, total)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(total / 2);
    }

    /**
     * POS-R2, seconde moitie : sur les items ou les trois propositions partagent le meme reste, le
     * triplet de quotients n'est pas toujours symetrique (bonne reponse au milieu) - sinon la bonne
     * reponse s'y devine sans diviser des que le reste ne distingue rien.
     */
    @Test
    void posR2LesItemsARestesEgauxNeSontPasTousSymetriques() {
        List<ItemAnalyse> itemsARestesEgaux = itemsAnalyses().stream()
                .filter(analyse -> partageDuReste(analyse) == PartageDuReste.TOUS_EGAUX)
                .toList();

        assertThat(itemsARestesEgaux).as("au moins un item a ses trois restes egaux")
                .isNotEmpty();

        long symetriques = itemsARestesEgaux.stream().filter(analyse -> {
            List<String> distracteurs = analyse.question().distracteurs();
            int bonne = analyse.bonne().quotient();
            int d1 = reponseDe(distracteurs.get(0), analyse.question().enonce()).quotient();
            int d2 = reponseDe(distracteurs.get(1), analyse.question().enonce()).quotient();
            boolean unDeChaqueCote = (d1 < bonne) != (d2 < bonne);
            return unDeChaqueCote;
        }).count();

        assertThat(symetriques)
                .as("POS-R2 : les items a restes egaux ne sont pas tous symetriques (%d/%d le sont)",
                        symetriques, itemsARestesEgaux.size())
                .isLessThan(itemsARestesEgaux.size());
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }
}
