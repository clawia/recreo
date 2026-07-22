package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.AssociationGenerator;

import java.util.List;

/**
 * Contenu "associer / classer" charge depuis un fichier YAML editable
 * ({@code content/associer/*.yml}). Porte les metadonnees de catalogue (entryId,
 * gameKey/gameLabel, titre, descripteur pedagogique), la consigne et la LISTE DE PUZZLES
 * verifiee (cf. ADR-0016). C'est l'externalisation du contenu associer : le domaine ne
 * connait qu'un {@code AssociationGenerator} generique alimente par ces puzzles.
 */
public record AssocContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String consigne,
        Integer jokers,
        List<AssociationGenerator.Puzzle> puzzles) {

    public AssocContentPack {
        puzzles = List.copyOf(puzzles);
    }
}
