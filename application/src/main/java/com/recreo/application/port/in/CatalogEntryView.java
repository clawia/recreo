package com.recreo.application.port.in;

/**
 * Vue d'une entree du catalogue de jeux, enrichie des metadonnees de regroupement
 * (matiere, niveau, jeu) pour la navigation a deux axes (cf. ADR-0005).
 */
public record CatalogEntryView(
        String entryId,
        String matiere,
        String niveau,
        String gameKey,
        String gameLabel,
        String title,
        String description,
        boolean hasSettings) {
}
