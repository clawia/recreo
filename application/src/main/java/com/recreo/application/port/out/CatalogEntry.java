package com.recreo.application.port.out;

import com.recreo.domain.mechanic.MechanicId;

/**
 * Une entree du catalogue : identifiant jouable + metadonnees d'affichage et de
 * regroupement.
 *
 * <p>{@code gameKey} identifie le JEU independamment du niveau (ex.
 * {@code calcul-mental}, {@code syllabes}) ; {@code gameLabel} est son libelle
 * affichable. Combines a {@code niveau} et {@code matiere}, ils permettent les
 * deux axes de navigation (par niveau / par jeu) sans coupler le front au
 * contenu (cf. ADR-0005).</p>
 */
public record CatalogEntry(
        String entryId,
        String matiere,
        String niveau,
        String gameKey,
        String gameLabel,
        String title,
        String description,
        MechanicId mechanicId) {
}
