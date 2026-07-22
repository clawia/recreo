package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.MotChoixGenerator.Entree;

import java.util.List;

/**
 * Contenu « mot → réponse » (bandit manchot) chargé depuis un fichier YAML éditable
 * ({@code content/mots/*.yml}). Porte les métadonnées de catalogue, la consigne fixe,
 * la stratégie de distracteurs ({@code poolMode} + {@code distractorCount}) et la liste
 * d'entrées vérifiées. Externalisation du contenu (DEC-0006 / ADR-0010).
 */
public record MotsContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String question,
        boolean poolMode,
        int distractorCount,
        Integer jokers,
        List<Entree> entrees) {

    public MotsContentPack {
        entrees = List.copyOf(entrees);
    }
}
