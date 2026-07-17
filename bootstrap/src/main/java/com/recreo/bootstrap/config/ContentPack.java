package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.DifficultyConfig;

/**
 * Contenu declaratif charge depuis un fichier YAML : une entree de catalogue
 * (calcul mental CP, CM1, ...) avec sa difficulte et son descripteur pedagogique.
 */
public record ContentPack(
        String entryId,
        String title,
        String description,
        String mechanic,
        ContentDescriptor descriptor,
        DifficultyConfig difficulty) {
}
