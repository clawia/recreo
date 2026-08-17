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
 * S12-1 (DEC-0029, docs/backlog/backlog.md section "Sprint 12") et l'arbitrage prealable de
 * nadia-prof (squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md, section 2, P-1 a
 * P-10) : le paquet "cm1-perimetre", joue par "bandit-manchot-perimetre-cm1". Ne couvre que les
 * criteres d'acceptation automatisables ; l'exactitude arithmetique est verifiee ici (recalculee,
 * jamais recopiee), mais la plausibilite pedagogique des distracteurs, la clarte des enonces et des
 * indices restent le jugement de nadia-prof.
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item) sont deja derivees du repertoire de
 * contenu par les garde-fous existants et ne sont pas repetees ici.</p>
 */
class PaquetCm1PerimetreTest {

    private static final String PAQUET = "cm1-perimetre";

    /** P-5 : les deux items definitionnels de la matiere premiere, admis et hors quota. */
    private static final Set<String> ENONCES_DEFINITIONNELS = Set.of(
            "Le périmètre d'une figure, c'est...",
            "Comment calcule-t-on le périmètre d'un carré ?");

    private static final Pattern CARRE = Pattern.compile(
            "^Quel est le périmètre d'un carré de côté (\\d+) (cm|m) \\?$");

    private static final Pattern RECTANGLE = Pattern.compile(
            "^Quel est le périmètre d'un rectangle de longueur (\\d+) (cm|m) et de largeur (\\d+) (cm|m) \\?$");

    @Test
    void exactementDeuxItemsDefinitionnelsHorsQuota() {
        List<Question> items = tousLesItems(paquet());
        List<Question> definitionnels = items.stream()
                .filter(item -> ENONCES_DEFINITIONNELS.contains(item.enonce()))
                .toList();

        assertThat(definitionnels)
                .as("P-5 : deux items definitionnels au maximum, ni plus ni moins")
                .hasSize(2);
    }

    @Test
    void lePaquetCompteAuMoinsVingtItemsDeCalculHorsItemsDefinitionnels() {
        List<Question> items = tousLesItems(paquet());
        long itemsDeCalcul = items.stream()
                .filter(item -> !ENONCES_DEFINITIONNELS.contains(item.enonce()))
                .count();

        assertThat(itemsDeCalcul)
                .as("les items definitionnels sont hors quota (P-5) : au moins 20 items de calcul")
                .isGreaterThanOrEqualTo(20);
    }

    @Test
    void chaqueItemDeCalculEstUnCarreOuUnRectangleReconnu() {
        for (Question item : itemsDeCalcul()) {
            boolean carre = CARRE.matcher(item.enonce()).matches();
            boolean rectangle = RECTANGLE.matcher(item.enonce()).matches();
            assertThat(carre || rectangle)
                    .as("'%s' : chaque item de calcul nomme un carre (cote) ou un rectangle"
                            + " (longueur et largeur), P-4", item.enonce())
                    .isTrue();
        }
    }

    @Test
    void chaqueItemDeCarreDonneLeQuadrupleDuCoteEtLesDistracteursAireEtDemiPerimetre() {
        for (Question item : itemsDeCalcul()) {
            Matcher forme = CARRE.matcher(item.enonce());
            if (!forme.matches()) {
                continue;
            }
            int cote = Integer.parseInt(forme.group(1));
            String unite = forme.group(2);

            assertThat(cote).as("'%s' : le cote reste un entier de 1 a 50 (P-1)", item.enonce())
                    .isBetween(1, 50);
            assertThat(item.bonneReponse())
                    .as("'%s' : la bonne reponse vaut quatre fois le cote", item.enonce())
                    .isEqualTo((4 * cote) + " " + unite);
            assertThat(item.distracteurs())
                    .as("'%s' : les deux distracteurs sont l'aire et la moitie du perimetre (P-6)",
                            item.enonce())
                    .containsExactlyInAnyOrder((cote * cote) + " " + unite, (2 * cote) + " " + unite);
        }
    }

    @Test
    void chaqueItemDeRectangleDonneLeDoubleDeLaSommeEtLesDistracteursAireEtDemiPerimetre() {
        for (Question item : itemsDeCalcul()) {
            Matcher forme = RECTANGLE.matcher(item.enonce());
            if (!forme.matches()) {
                continue;
            }
            int longueur = Integer.parseInt(forme.group(1));
            String uniteLongueur = forme.group(2);
            int largeur = Integer.parseInt(forme.group(3));
            String uniteLargeur = forme.group(4);

            assertThat(uniteLongueur).as("'%s' : la meme unite pour la longueur et la largeur (P-2)",
                    item.enonce()).isEqualTo(uniteLargeur);
            assertThat(longueur).as("'%s' : la longueur reste un entier de 1 a 50 (P-1)", item.enonce())
                    .isBetween(1, 50);
            assertThat(largeur).as("'%s' : la largeur reste un entier de 1 a 50 (P-1)", item.enonce())
                    .isBetween(1, 50);
            assertThat(longueur).as("'%s' : longueur et largeur sont distinctes", item.enonce())
                    .isNotEqualTo(largeur);

            int perimetre = 2 * (longueur + largeur);
            int aire = longueur * largeur;
            int demiPerimetre = longueur + largeur;
            assertThat(item.bonneReponse())
                    .as("'%s' : la bonne reponse vaut le double de la somme des deux cotes", item.enonce())
                    .isEqualTo(perimetre + " " + uniteLongueur);
            assertThat(item.distracteurs())
                    .as("'%s' : les deux distracteurs sont l'aire et la moitie du perimetre (P-6)",
                            item.enonce())
                    .containsExactlyInAnyOrder(aire + " " + uniteLongueur, demiPerimetre + " " + uniteLongueur);
        }
    }

    @Test
    void leNombreDeCarresEtDeRectanglesEstEquilibreAUnPres() {
        List<Question> items = itemsDeCalcul();
        long carres = items.stream().filter(item -> CARRE.matcher(item.enonce()).matches()).count();
        long rectangles = items.stream().filter(item -> RECTANGLE.matcher(item.enonce()).matches()).count();

        assertThat(Math.abs(carres - rectangles))
                .as("carres=%d, rectangles=%d : ni l'une ni l'autre figure n'est reduite a un ou deux"
                        + " exemples isoles", carres, rectangles)
                .isLessThanOrEqualTo(1);
    }

    @Test
    void aucuneUniteAutreQueCentimetreOuMetre() {
        for (Question item : itemsDeCalcul()) {
            Matcher carre = CARRE.matcher(item.enonce());
            Matcher rectangle = RECTANGLE.matcher(item.enonce());
            String unite = carre.matches() ? carre.group(2) : rectangle.matches() ? rectangle.group(2) : null;
            assertThat(unite).as("'%s' : seuls le centimetre et le metre sont admis (P-2)", item.enonce())
                    .isIn("cm", "m");
        }
    }

    /*
     * s14, dette technique 1 (avis-faisabilite.md section 8.1) : la verification "l'entree de
     * catalogue charge bien ce paquet" est desormais generique, derivee de catalogue.yaml, dans
     * ChaqueEntreeDuCatalogueChargeBienSonPaquetTest. Elle n'est plus repetee ici.
     */

    /** Les items de calcul, hors les deux items definitionnels admis par P-5. */
    private static List<Question> itemsDeCalcul() {
        return tousLesItems(paquet()).stream()
                .filter(item -> !ENONCES_DEFINITIONNELS.contains(item.enonce()))
                .toList();
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

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
