package com.recreo.application.port.out;

import com.recreo.domain.mechanic.MechanicId;

/** Une entree du catalogue : identifiant jouable + metadonnees d'affichage. */
public record CatalogEntry(String entryId, String title, String description, MechanicId mechanicId) {
}
