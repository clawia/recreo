package com.recreo.bootstrap.config;

import com.recreo.domain.content.Operation;

import java.util.EnumSet;
import java.util.Set;

/**
 * Bornes de reglage d'un jeu de calcul mental, coherentes avec le niveau : elles
 * empechent un reglage hors-programme (ex. pas de multiplication ni d'operande
 * demesure en CP). Appliquees par {@link MathGameFactory} quelle que soit
 * l'origine des parametres (le formulaire ne fait pas foi).
 */
record MathBounds(int operandFloor, int operandCeiling, Set<Operation> allowedOperations) {

    MathBounds {
        if (operandCeiling < operandFloor) {
            throw new IllegalArgumentException("operandCeiling must be >= operandFloor");
        }
        if (allowedOperations == null || allowedOperations.isEmpty()) {
            throw new IllegalArgumentException("allowedOperations must not be empty");
        }
        allowedOperations = EnumSet.copyOf(allowedOperations);
    }
}
