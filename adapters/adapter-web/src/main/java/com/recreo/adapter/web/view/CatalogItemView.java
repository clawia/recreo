package com.recreo.adapter.web.view;

/**
 * View-model d'une entree de catalogue (feuille) affichee comme carte, enrichie
 * des metadonnees de regroupement (niveau, jeu) pour la navigation a deux axes.
 */
public record CatalogItemView(
        String entryId,
        String matiere,
        String niveau,
        String gameKey,
        String gameLabel,
        String title,
        String description) {
}
