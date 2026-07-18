package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.DifficultyConfig;

/**
 * Contenu declaratif charge depuis un fichier YAML : une entree de catalogue
 * (calcul mental CP, CM1, ...) avec ses metadonnees de regroupement
 * ({@code gameKey}/{@code gameLabel}), sa difficulte de base, son descripteur
 * pedagogique et les bornes de reglage coherentes avec le niveau.
 */
public record ContentPack(
        String entryId,
        String title,
        String description,
        String mechanic,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        DifficultyConfig difficulty,
        MathBounds bounds) {
}
