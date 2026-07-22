package com.recreo.domain.mechanic;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verrouillage partiel de la mecanique associer/classer (cf. DEC-0008 / s18) : sur un essai
 * FAUX, les elements bien classes restent verrouilles, seuls les mal classes repartent en
 * reserve, sans reveler la bonne reponse.
 */
class AssociationBoardTest {

    @Test
    void initialPutsEverythingInReserveAndLocksNothing() {
        AssociationBoard board = AssociationBoard.initial(List.of("chat", "voiture", "arbre"));
        assertThat(board.reserve()).containsExactly("chat", "voiture", "arbre");
        assertThat(board.lockedItems()).isEmpty();
        assertThat(board.lockedTargets()).isEmpty();
    }

    @Test
    void afterAttemptLocksWellPlacedAndReturnsMisplacedToReserve() {
        List<String> allItems = List.of("chat", "voiture", "arbre", "caillou");
        Map<String, String> expected = Map.of(
                "chat", "vivant", "voiture", "non-vivant", "arbre", "vivant", "caillou", "non-vivant");
        // L'enfant place bien chat (vivant) et voiture (non-vivant), mais se trompe sur arbre et caillou.
        Map<String, String> submitted = Map.of(
                "chat", "vivant", "voiture", "non-vivant", "arbre", "non-vivant", "caillou", "vivant");

        AssociationBoard board = AssociationBoard.afterAttempt(allItems, submitted, expected);

        // Ordre canonique (allItems) conserve.
        assertThat(board.lockedItems()).containsExactly("chat", "voiture");
        assertThat(board.lockedTargets()).containsExactly("vivant", "non-vivant");
        assertThat(board.reserve()).containsExactly("arbre", "caillou");
    }

    @Test
    void anItemNotSubmittedStaysInReserve() {
        List<String> allItems = List.of("chat", "arbre");
        Map<String, String> expected = Map.of("chat", "vivant", "arbre", "vivant");
        // "arbre" pas encore place : absent des soumissions.
        Map<String, String> submitted = Map.of("chat", "vivant");

        AssociationBoard board = AssociationBoard.afterAttempt(allItems, submitted, expected);

        assertThat(board.lockedItems()).containsExactly("chat");
        assertThat(board.reserve()).containsExactly("arbre");
    }
}
