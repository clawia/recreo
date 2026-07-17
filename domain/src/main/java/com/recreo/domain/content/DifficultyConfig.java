package com.recreo.domain.content;

import java.util.EnumSet;
import java.util.Set;

/**
 * Regles declaratives de difficulte : bornes des operandes, operations autorisees
 * et nombre de distracteurs a generer. C'est ce qui distingue CP de CM1 sans
 * toucher au code du generateur.
 */
public record DifficultyConfig(int minOperand, int maxOperand, Set<Operation> operations, int distractorCount) {
    public DifficultyConfig {
        if (maxOperand < minOperand) {
            throw new IllegalArgumentException("maxOperand must be >= minOperand");
        }
        if (operations == null || operations.isEmpty()) {
            throw new IllegalArgumentException("operations must not be empty");
        }
        if (distractorCount < 1) {
            throw new IllegalArgumentException("distractorCount must be >= 1");
        }
        // Copie defensive avec ordre deterministe (ordre de l'enum).
        operations = EnumSet.copyOf(operations);
    }
}
