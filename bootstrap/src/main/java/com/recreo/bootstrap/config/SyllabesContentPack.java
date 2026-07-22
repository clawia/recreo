package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.SyllabesCpGenerator.Mot;

import java.util.List;

/**
 * Contenu « syllabes » (bandit manchot, Français CP) chargé depuis un fichier YAML
 * éditable ({@code content/syllabes/*.yml}). Porte les métadonnées de catalogue, les
 * options du réglage « nombre de syllabes max » ({@code syllabesOptions} + bornes) et la
 * liste vérifiée mot → nombre de syllabes. Externalisation du contenu (DEC-0006 / ADR-0011).
 */
public record SyllabesContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        List<Integer> syllabesOptions,
        int minSyllabes,
        int maxSyllabes,
        Integer jokers,
        List<Mot> mots) {

    public SyllabesContentPack {
        syllabesOptions = List.copyOf(syllabesOptions);
        mots = List.copyOf(mots);
    }
}
