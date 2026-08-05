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

    private final RandomSource hasard = new Random(42)::nextInt;
    private final AdditionsBorneesContentSource source =
            new AdditionsBorneesContentSource(DESCRIPTEUR, 10, 3);

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

    private Question tirer() {
        return (Question) source.next(hasard, Set.<ItemId>of()).orElseThrow();
    }
}
