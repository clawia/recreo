package com.recreo.application.port.in;

/** Vue d'une entree du catalogue de jeux (pour l'ecran d'accueil). */
public record CatalogEntryView(String entryId, String title, String description) {
}
