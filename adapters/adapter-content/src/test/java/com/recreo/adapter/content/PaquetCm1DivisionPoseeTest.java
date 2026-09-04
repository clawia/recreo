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
 * S28-4 (squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-
 * lots.md, section 5.4) : le paquet {@code cm1-division-posee}, seconde moitie du lot de calcul
 * pose CM1, ligne {@code MAT-CM1-05}. Les proprietes generiques (indice non vide, au plus trois
 * propositions, aucune proposition egale a une autre, dix manches contre au moins onze items du
 * chargeur) sont deja gardees par les garde-fous derives du repertoire de contenu ; ce fichier
 * verifie les conditions {@code POS-1} a {@code POS-4} de l'arbitrage.
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
