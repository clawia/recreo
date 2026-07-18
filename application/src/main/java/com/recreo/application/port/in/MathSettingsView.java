package com.recreo.application.port.in;

import java.util.List;

/**
 * Reglages du jeu de calcul mental : fenetre de nombres (valeurs courantes +
 * bornes autorisees) et operations proposees (avec leur etat coche par defaut).
 */
public record MathSettingsView(
        int minOperand,
        int maxOperand,
        int operandFloor,
        int operandCeiling,
        List<OperationOptionView> operations) {

    public MathSettingsView {
        operations = List.copyOf(operations);
    }
}
