package com.recreo.domain.mechanic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link ArrangementBoard} : aide PURE qui derive, apres un essai glisser-deposer, ce qui
 * reste VERROUILLE en place (tuiles bien positionnees) et ce qui repart en reserve (mal
 * placees). Sert l'UX « ranger » (DEC-0008) sans jamais reveler la bonne reponse.
 */
class ArrangementBoardTest {

    @Test
    void initialLeavesEverySlotEmptyAndFillsTheReserve() {
        ArrangementBoard board = ArrangementBoard.initial(List.of("3", "1", "4", "2"));

        assertThat(board.slots()).containsExactly("", "", "", "");
        assertThat(board.reserve()).containsExactly("3", "1", "4", "2");
    }

    @Test
    void afterAttemptLocksTheWellPlacedTilesAndReturnsTheRestToTheReserve() {
        ArrangementBoard board = ArrangementBoard.afterAttempt(
                List.of("1", "3", "2", "4"), List.of("1", "2", "3", "4"));

        // Positions 0 et 3 correctes (verrouillees) ; positions 1 et 2 vides.
        assertThat(board.slots()).containsExactly("1", "", "", "4");
        // Les tuiles mal placees repartent en reserve, dans l'ordre soumis.
        assertThat(board.reserve()).containsExactly("3", "2");
    }

    @Test
    void afterAttemptWithEverythingCorrectFillsAllSlotsAndEmptiesTheReserve() {
        ArrangementBoard board = ArrangementBoard.afterAttempt(
                List.of("1", "2", "3", "4"), List.of("1", "2", "3", "4"));

        assertThat(board.slots()).containsExactly("1", "2", "3", "4");
        assertThat(board.reserve()).isEmpty();
    }

    @Test
    void afterAttemptWithEverythingWrongLeavesAllSlotsEmptyAndReserveHoldsTheSubmission() {
        ArrangementBoard board = ArrangementBoard.afterAttempt(
                List.of("4", "3", "2", "1"), List.of("1", "2", "3", "4"));

        assertThat(board.slots()).containsExactly("", "", "", "");
        assertThat(board.reserve()).containsExactly("4", "3", "2", "1");
    }
}
