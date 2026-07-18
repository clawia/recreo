package com.recreo.application.port.in;

/**
 * Un jeu distinct (regroupement par {@code gameKey}) pour l'axe de navigation
 * « par jeu » : l'identifiant du jeu et son libelle affichable.
 */
public record GameSummaryView(String gameKey, String label) {
}
