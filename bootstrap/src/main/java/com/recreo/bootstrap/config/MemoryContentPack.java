package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.MemoryGenerator;

import java.util.List;

/**
 * Contenu "memory" charge depuis un fichier YAML editable
 * ({@code content/memory/*.yml}). Porte les metadonnees de catalogue (entryId,
 * gameKey/gameLabel, titre, descripteur pedagogique), la consigne et la LISTE DE PAIRES
 * verifiee (cf. ADR-0016). C'est l'externalisation du contenu memory : le domaine ne
 * connait qu'un {@code MemoryGenerator} generique alimente par ces paires.
 */
public record MemoryContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String consigne,
        Integer jokers,
        List<MemoryGenerator.Paire> paires) {

    public MemoryContentPack {
        paires = List.copyOf(paires);
    }
}
