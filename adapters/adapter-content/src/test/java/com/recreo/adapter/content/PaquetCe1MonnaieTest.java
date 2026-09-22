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
 * <b>S39-MONNAIE, le paquet {@code ce1-monnaie}, ligne {@code MAT-CE1-13} de "Mathématiques"
 * (CE1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-
 * solides.md}, section 3 (MON-CE1-1 a MON-CE1-4). Sous-partie allouee par ADR-0098
 * ({@code SousPartieDeMathematiquesCe1.MONNAIE}), tenue par
 * {@link ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest}.
 *
 * <p><b>Ecriture des montants</b> (avis de faisabilite de s39, section 21) : chaque proposition
 * ecrit un nombre entier de centimes, jamais une virgule decimale ni deux nombres - ce qui garde le
 * paquet entierement dans le domaine numerique de {@code lireValeur} (ADR-0068), sans qu'aucune
 * declaration dans {@code PAQUETS_A_ITEMS_ECARTES} ni {@code PAQUETS_HORS_DU_DOMAINE_NUMERIQUE} ne
 * soit due. Ce test verifie cette propriete plutot que de la supposer.
 *
 * <p><b>Ce que ce test ne juge pas</b> : que les scenarios (pieces, billets, prix) soient
 * pedagogiquement clairs pour un enfant de CE1, ni que le vocabulaire soit exact - relecture de
 * {@code nadia-prof} au Done pedagogique.
 */
class PaquetCe1MonnaieTest {

    private static final String PAQUET = "ce1-monnaie";

    /** ADR-0023 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** MON-CE1-3 : au moins un tiers des items portent explicitement sur le rendu de monnaie. */
    private static final double SEUIL_MON_CE1_3 = 1.0 / 3.0;

    /** MON-CE1-1 : montants entre 0,05 et 50 euros, soit 5 et 5000 centimes. */
    private static final int MONTANT_MINIMUM_EN_CENTIMES = 5;

    private static final int MONTANT_MAXIMUM_EN_CENTIMES = 5000;

    /** Ecriture des montants (section 21 de l'avis de faisabilite) : un entier suivi de "centimes". */
    private static final Pattern PROPOSITION_EN_CENTIMES = Pattern.compile("^(\\d+) centimes$");

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    private static int centimes(String proposition) {
        Matcher lecture = PROPOSITION_EN_CENTIMES.matcher(proposition);
        assertThat(lecture.matches())
                .as("la proposition \"%s\" n'ecrit pas un entier de centimes - S39-MONNAIE tient"
                        + " le paquet dans le domaine numerique par cette seule forme", proposition)
                .isTrue();
        return Integer.parseInt(lecture.group(1));
    }

    private static boolean estUnItemDeRendu(Question item) {
        return item.enonce().contains("rend-on");
    }

    /** ADR-0023 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("ADR-0023 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /**
     * L'ecriture des montants (section 21 de l'avis de faisabilite) : chaque proposition de chaque
     * item, bonne reponse et distracteurs, est un entier de centimes. C'est ce qui garde le paquet
     * hors de toute declaration d'exemption du domaine numerique.
     */
    @Test
    void chaquePropositionEcritUnEntierDeCentimes() {
        for (Question item : items()) {
            centimes(item.bonneReponse());
            for (String distracteur : item.distracteurs()) {
                centimes(distracteur);
            }
        }
    }

    /** MON-CE1-1 : les montants tires (bonne reponse) restent entre 5 et 5000 centimes. */
    @Test
    void mon1LesMontantsTiresRestentDansLesBornes() {
        for (Question item : items()) {
            int valeur = centimes(item.bonneReponse());
            assertThat(valeur)
                    .as("item %s : MON-CE1-1, montant entre 5 et 5000 centimes", item.id().value())
                    .isBetween(MONTANT_MINIMUM_EN_CENTIMES, MONTANT_MAXIMUM_EN_CENTIMES);
            assertThat(valeur % 5)
                    .as("item %s : MON-CE1-1, montant multiple de 5 centimes", item.id().value())
                    .isZero();
        }
    }

    /** MON-CE1-3 : au moins un tiers des items portent explicitement sur le rendu de monnaie. */
    @Test
    void mon3AuMoinsUnTiersDesItemsPortentSurLeRendu() {
        List<Question> items = items();
        long itemsDeRendu = items.stream().filter(PaquetCe1MonnaieTest::estUnItemDeRendu).count();

        assertThat(itemsDeRendu).as("au moins un item de rendu existe").isGreaterThan(0);
        assertThat((double) itemsDeRendu / items.size())
                .as("MON-CE1-3 : au moins un tiers des items sur le rendu de monnaie")
                .isGreaterThanOrEqualTo(SEUIL_MON_CE1_3);
    }

    /** MON-CE1-4 : aucun distracteur n'est negatif, et aucun ne vaut la bonne reponse. */
    @Test
    void mon4AucunDistracteurNEstNegatifNiEgalALaBonneReponse() {
        for (Question item : items()) {
            int bonneReponse = centimes(item.bonneReponse());
            for (String distracteur : item.distracteurs()) {
                int valeur = centimes(distracteur);
                assertThat(valeur)
                        .as("item %s : MON-CE1-4, aucun distracteur negatif", item.id().value())
                        .isPositive();
                assertThat(valeur)
                        .as("item %s : MON-CE1-4, un distracteur ne vaut pas la bonne reponse",
                                item.id().value())
                        .isNotEqualTo(bonneReponse);
            }
        }
    }

    /** Chaque item propose exactement deux distracteurs (ADR-0063 : trois propositions au plus). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /** Chaque item porte un indice (ChaquePaquetPorteUnIndiceTest le tient, verifie ici aussi). */
    @Test
    void chaqueItemPorteUnIndice() {
        for (Question item : items()) {
            assertThat(item.indice())
                    .as("item %s : indice present et non vide", item.id().value())
                    .isPresent();
            assertThat(item.indice().orElseThrow()).isNotBlank();
        }
    }
}
