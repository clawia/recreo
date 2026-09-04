package com.recreo.domain.mechanics.designation;

import com.recreo.domain.mechanic.Move;

/**
 * ADR-0080, decision 4 : le coup de l'enfant. {@code zoneDesignee} est l'identifiant de la zone
 * qu'il a designee, tel qu'un vrai bouton de formulaire l'a transmis - jamais une coordonnee, jamais
 * un libelle. ADR-0004 clause 6 : chaque mecanique definit ses coups dans son propre paquet.
 */
public record DesignationMove(String zoneDesignee) implements Move {
}
