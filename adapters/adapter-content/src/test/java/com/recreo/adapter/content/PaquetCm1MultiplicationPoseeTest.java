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
 * lots.md, section 5.4) : le paquet {@code cm1-multiplication-posee}, premiere moitie du lot de
 * calcul pose CM1, ligne {@code MAT-CM1-04}. Les proprietes generiques (indice non vide, au plus
 * trois propositions, aucune proposition egale a une autre, rang non predictible au seuil de la
 * CI, dix manches contre au moins onze items du chargeur) sont deja gardees par les garde-fous
 * derives du repertoire de contenu ; ce fichier verifie les conditions {@code POS-1} a
 * {@code POS-4} de l'arbitrage, qu'aucun garde-fou generique ne connait.
 *
 * <p>{@code POS-1} (la mention de couverture partielle) et {@code POS-3} (chaque distracteur est
 * une erreur classique nommee) sont ecrites dans le commentaire du paquet servi, pour reprise mot
 * pour mot par camille-po dans la matrice ; elles ne sont pas des proprietes calculables sur les
 * items charges et ne sont donc pas mordues ici, meme limite que {@code HIS-2} sur
 * {@code PaquetCm1HistoireGeographieTest}.</p>
 */
class PaquetCm1MultiplicationPoseeTest {

    private static final String PAQUET = "cm1-multiplication-posee";

    private static final Pattern ENONCE = Pattern.compile("^(\\d+) × (\\d+) =[  ]?\\?$");

    private record ItemAnalyse(Question question, int multiplicande, int multiplicateur) {
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(paquet(PAQUET))) {
            Matcher matcher = ENONCE.matcher(item.enonce());
            assertThat(matcher.matches()).as("forme de l'enonce '%s'", item.enonce()).isTrue();
            analyses.add(new ItemAnalyse(item, Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))));
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

    /** La bonne reponse est le produit exact du multiplicande par le multiplicateur. */
    @Test
    void laBonneReponseEstLeProduitExact() {
        for (ItemAnalyse analyse : itemsAnalyses()) {
            long attendu = (long) analyse.multiplicande() * analyse.multiplicateur();
            assertThat(numeriqueDe(analyse.question().bonneReponse()))
                    .as("bonne reponse de '%s'", analyse.question().enonce())
                    .isEqualTo(attendu);
        }
    }

    /**
     * POS-4 : les quatre combinaisons du libelle (2-3 chiffres par 1-2 chiffres) sont servies -
     * 2 par 1, 2 par 2, 3 par 1, 3 par 2 - chacune par au moins un item.
     */
    @Test
    void lesQuatreCombinaisonsDeChiffresSontServies() {
        Set<String> combinaisons = new HashSet<>();
        for (ItemAnalyse analyse : itemsAnalyses()) {
            combinaisons.add(chiffresDe(analyse.multiplicande()) + "x" + chiffresDe(analyse.multiplicateur()));
        }
        assertThat(combinaisons).as("POS-4 : les quatre combinaisons attendues")
                .containsExactlyInAnyOrder("2x1", "2x2", "3x1", "3x2");
    }

    /**
     * POS-2 : sur chaque item, les trois propositions ont le meme nombre de chiffres, et au moins
     * un distracteur partage le chiffre des unites avec la bonne reponse - sinon l'enfant elimine
     * par la longueur ou par le dernier chiffre sans poser l'operation.
     */
    @Test
    void lesTroisPropositionsOntLeMemeNombreDeChiffresEtUnDistracteurPartageLeChiffreDesUnites() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            String bonne = sansSeparateur(item.bonneReponse());
            List<String> distracteurs = item.distracteurs().stream().map(PaquetCm1MultiplicationPoseeTest::sansSeparateur).toList();

            for (String distracteur : distracteurs) {
                assertThat(distracteur.length()).as("POS-2 : '%s' (item '%s') n'a pas le meme"
                        + " nombre de chiffres que la bonne reponse '%s'", distracteur,
                        item.enonce(), bonne).isEqualTo(bonne.length());
            }
            boolean chiffreDesUnitesPartage = distracteurs.stream()
                    .anyMatch(distracteur -> distracteur.charAt(distracteur.length() - 1)
                            == bonne.charAt(bonne.length() - 1));
            assertThat(chiffreDesUnitesPartage).as("POS-2 : item '%s', aucun distracteur ne partage"
                    + " le chiffre des unites de la bonne reponse '%s'", item.enonce(), bonne)
                    .isTrue();
        }
    }

    private static int chiffresDe(int valeur) {
        return Integer.toString(valeur).length();
    }

    private static String sansSeparateur(String valeur) {
        return valeur.replace(" ", "");
    }

    private static long numeriqueDe(String texte) {
        return Long.parseLong(sansSeparateur(texte));
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
