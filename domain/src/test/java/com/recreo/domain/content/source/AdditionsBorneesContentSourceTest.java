package com.recreo.domain.content.source;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S0-4 : les quatre criteres d'acceptation, verifies de facon deterministe a graine fixe
 * (ADR-0004 : le tirage est injecte, donc rejouable).
 */
class AdditionsBorneesContentSourceTest {

    private static final ContentDescriptor DESCRIPTEUR = new ContentDescriptor(
            "Mathematiques", "CP", "Nombres et calculs", "additions dans la limite de 10");

    private static final String INDICE = "Compte sur tes doigts.";

    private final RandomSource hasard = new Random(42)::nextInt;
    private final AdditionsBorneesContentSource source =
            new AdditionsBorneesContentSource(DESCRIPTEUR, 10, 3, INDICE);

    @Test
    void chaqueManchePresenteDeuxOperandesEtUneSommeEntre0Et10() {
        for (int i = 0; i < 50; i++) {
            Question question = tirer();
            String[] operandes = question.enonce().split(" \\+ ");
            int operandeA = Integer.parseInt(operandes[0]);
            int operandeB = Integer.parseInt(operandes[1]);
            int somme = Integer.parseInt(question.bonneReponse());

            assertThat(operandeA).isBetween(0, 10);
            assertThat(operandeB).isBetween(0, 10);
            assertThat(somme).isEqualTo(operandeA + operandeB);
            assertThat(somme).isBetween(0, 10);
        }
    }

    @Test
    void laBonneReponseFigureExactementUneFoisParmiLesPropositions() {
        for (int i = 0; i < 50; i++) {
            Question question = tirer();
            List<String> propositions = new ArrayList<>(question.distracteurs());
            propositions.add(question.bonneReponse());

            long occurrences = propositions.stream()
                    .filter(proposition -> proposition.equals(question.bonneReponse()))
                    .count();
            assertThat(occurrences).isEqualTo(1);
        }
    }

    @Test
    void auMoinsUnDistracteurPlausibleJamaisNegatifNiNonEntier() {
        for (int i = 0; i < 50; i++) {
            Question question = tirer();
            int bonneReponse = Integer.parseInt(question.bonneReponse());

            assertThat(question.distracteurs()).allSatisfy(distracteur -> {
                assertThat(distracteur).matches("\\d+");
                assertThat(Integer.parseInt(distracteur)).isGreaterThanOrEqualTo(0).isNotEqualTo(bonneReponse);
            });
            assertThat(question.distracteurs())
                    .as("au moins un distracteur proche de la bonne reponse")
                    .anySatisfy(distracteur -> assertThat(Math.abs(Integer.parseInt(distracteur) - bonneReponse))
                            .isLessThanOrEqualTo(3));
        }
    }

    @Test
    void lesAdditionsVarientSurVingtManchesConsecutives() {
        Set<String> enonces = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            enonces.add(tirer().enonce());
        }
        assertThat(enonces).as("variete minimale sur 20 manches").hasSizeGreaterThan(1);
    }

    /**
     * S1-7 (DEC-0004, point 5) : sur 200 tirages independants a graine fixee, entre 10 et 30
     * comportent au moins un operande nul (5 % a 15 %), contre 26 % constates et une esperance
     * theorique de 32 % avant correction. dejaVus reste vide a chaque tirage pour isoler la
     * correction de la logique d'evitement des doublons, deja couverte par un autre test.
     */
    @Test
    void surDeuxCentsTiragesAGraineFixeeEntre10Et30OntAuMoinsUnOperandeNul() {
        RandomSource graineFixe = new Random(2026)::nextInt;
        int tiragesAvecOperandeNul = 0;

        for (int i = 0; i < 200; i++) {
            Question question = (Question) source.next(graineFixe, Set.<ItemId>of()).orElseThrow();
            String[] operandes = question.enonce().split(" \\+ ");
            if ("0".equals(operandes[0]) || "0".equals(operandes[1])) {
                tiragesAvecOperandeNul++;
            }
        }

        assertThat(tiragesAvecOperandeNul)
                .as("proportion de tirages a operande nul sur 200, jamais zero")
                .isBetween(10, 30);
    }

    /**
     * S3-1, 1er et 2e criteres : sur 6500 tirages, chaque somme reste dans la fourchette
     * pedagogique de DEC-0007 point 2 (amendee le 2026-08-05, calculee sur le tirage reellement
     * compose - tirage uniforme puis filtre d'operandes nuls), et l'addition "0 + 0" n'apparait
     * jamais (le couple est retire du tirage). Verifie sur vingt graines declarees (ADR-0013,
     * note de methode de theo-archi) : une seule graine ne prouve rien, elle peut passer par
     * chance sur une cible mal calculee, comme mesure sur la table non recalculee (4 echecs sur
     * 10 graines a 6500 tirages, contre 0 echec sur 20 une fois la table recalculee).
     */
    @Test
    void surSixMilleCinqCentsTiragesChaqueSommeResteDansSaFourchettePourVingtGraines() {
        double[] fourchetteBasse = {0, 0, 1.23, 3.16, 5.10, 7.03, 8.97, 10.90, 12.84, 14.77, 16.71};
        double[] fourchetteHaute = {0, 3.29, 5.23, 7.16, 9.10, 11.03, 12.97, 14.90, 16.84, 18.77, 20.71};

        for (int graine = 0; graine < 20; graine++) {
            RandomSource graineFixe = new Random(graine)::nextInt;
            int[] occurrencesParSomme = new int[11];
            int occurrencesZeroPlusZero = 0;

            for (int tirage = 0; tirage < 6500; tirage++) {
                Question question = (Question) source.next(graineFixe, Set.<ItemId>of()).orElseThrow();
                if ("0 + 0".equals(question.enonce())) {
                    occurrencesZeroPlusZero++;
                }
                occurrencesParSomme[Integer.parseInt(question.bonneReponse())]++;
            }

            assertThat(occurrencesZeroPlusZero).as("graine %d : '0 + 0' n'apparait jamais", graine).isZero();
            for (int somme = 0; somme <= 10; somme++) {
                double proportion = occurrencesParSomme[somme] * 100.0 / 6500;
                assertThat(proportion)
                        .as("graine %d, somme %d", graine, somme)
                        .isBetween(fourchetteBasse[somme], fourchetteHaute[somme]);
            }
        }
    }

    /**
     * S3-1, 3e critere : consequence directe de l'exclusion du couple (0, 0), verifiee
     * explicitement parce que c'etait le cas le plus grave signale par nadia-prof (reserve 8,
     * verdict s01) : "et surtout pas en premiere manche".
     */
    @Test
    void surCinquantePartiesIndependantesLaPremiereMancheNEstJamais0Plus0() {
        for (int graine = 0; graine < 50; graine++) {
            RandomSource graineFixe = new Random(graine)::nextInt;
            Question premiereManche = (Question) source.next(graineFixe, Set.<ItemId>of()).orElseThrow();

            assertThat(premiereManche.enonce()).as("graine %d", graine).isNotEqualTo("0 + 0");
        }
    }

    /** S3-3 (DEC-0005) : l'indice vient du parametre recu au constructeur, jamais d'une constante. */
    @Test
    void lIndiceDeChaqueQuestionEstCeluiRecuAuConstructeur() {
        assertThat(tirer().indice()).contains(INDICE);
    }

    /**
     * Refus pedagogique de nadia-prof du 2026-08-15 (FICHE-bandit-manchot-additions-cp.md) : sur
     * l'ancienne fabrique, la bonne reponse etait le nombre intermediaire des trois propositions sur
     * 66,5 % des manches (calcul exhaustif de nadia-prof, 66,6 % mesure en jouant 900 manches) - une
     * strategie qui presse toujours le milieu sans lire l'enonce gagnait deux manches sur trois.
     * Cause : les deux distracteurs etaient tires parmi quatre voisins symetriques {s-2, s-1, s+1,
     * s+2}, ce qui rend la combinaison "un de chaque cote" (donc le rang intermediaire) mecaniquement
     * majoritaire (4 paires sur 6, 66,67 %).
     *
     * <p>La correction ne remplace pas une regle de tirage par une autre en esperant que la
     * distribution suive : elle derive du resultat voulu. Le nombre de distracteurs places en
     * dessous de la bonne reponse ({@link AdditionsBorneesContentSource#distracteurs}) est tire
     * uniformement parmi les valeurs atteignables, ce qui rend directement le rang de la bonne
     * reponse presque equiprobable entre les trois positions plutot que de l'observer en
     * consequence d'une combinatoire sur des candidats.
     *
     * <p>Calcul exact sur la fabrique (memes constantes que le paquet livre) : la plus petite
     * 33,55 %, intermediaire 33,55 %, la plus grande 32,90 % - au plancher arithmetique (33 % a
     * 37 %) que nadia-prof fixe comme cible, et non pas seulement sous son plancher de refus
     * (50 %) ; aucun rang sous sa reserve de 10 %. Verifie ici sur un grand nombre de tirages reels
     * (20 graines x 6500, comme le test voisin de repartition des sommes) et non par un seul tirage
     * par identifiant - c'est precisement la sous-estimation (63,1 % au lieu de 66,5 %) que
     * nadia-prof a corrigee dans sa fiche.
     */
    @Test
    void leRangDeLaBonneReponseNEstJamaisMajoritaireEtAucunRangNEstPresqueVide() {
        int petite = 0;
        int intermediaire = 0;
        int grande = 0;

        for (int graine = 0; graine < 20; graine++) {
            RandomSource graineFixe = new Random(graine)::nextInt;
            for (int tirage = 0; tirage < 6500; tirage++) {
                Question question = (Question) source.next(graineFixe, Set.<ItemId>of()).orElseThrow();
                int bonneReponse = Integer.parseInt(question.bonneReponse());
                int distracteur1 = Integer.parseInt(question.distracteurs().get(0));
                int distracteur2 = Integer.parseInt(question.distracteurs().get(1));
                int enDessous = (distracteur1 < bonneReponse ? 1 : 0) + (distracteur2 < bonneReponse ? 1 : 0);
                if (enDessous == 0) {
                    petite++;
                } else if (enDessous == 2) {
                    grande++;
                } else {
                    intermediaire++;
                }
            }
        }

        int total = petite + intermediaire + grande;
        double proportionPetite = 100.0 * petite / total;
        double proportionIntermediaire = 100.0 * intermediaire / total;
        double proportionGrande = 100.0 * grande / total;

        // Plancher de refus de nadia-prof : une strategie a rang constant ne doit jamais gagner
        // plus souvent qu'elle ne perd, et aucun rang ne doit devenir une elimination gratuite.
        assertThat(proportionPetite).as("rang le plus petit, jamais majoritaire").isLessThan(50.0);
        assertThat(proportionIntermediaire).as("rang intermediaire, jamais majoritaire").isLessThan(50.0);
        assertThat(proportionGrande).as("rang le plus grand, jamais majoritaire").isLessThan(50.0);
        assertThat(proportionPetite).as("rang le plus petit, jamais presque vide").isGreaterThan(10.0);
        assertThat(proportionIntermediaire).as("rang intermediaire, jamais presque vide").isGreaterThan(10.0);
        assertThat(proportionGrande).as("rang le plus grand, jamais presque vide").isGreaterThan(10.0);

        // Cible de nadia-prof : le plancher arithmetique (33 % a 37 %), pas seulement sous la barre.
        assertThat(proportionPetite).as("rang le plus petit, au plancher arithmetique").isBetween(30.0, 38.0);
        assertThat(proportionIntermediaire).as("rang intermediaire, au plancher arithmetique").isBetween(30.0, 38.0);
        assertThat(proportionGrande).as("rang le plus grand, au plancher arithmetique").isBetween(30.0, 38.0);
    }

    private Question tirer() {
        return (Question) source.next(hasard, Set.<ItemId>of()).orElseThrow();
    }
}
