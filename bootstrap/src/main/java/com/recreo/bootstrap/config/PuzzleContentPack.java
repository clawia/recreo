package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.MotPuzzleGenerator;

import java.util.List;

/**
 * Contenu "reconstitue le mot" (mecanique puzzle, cf. ADR-0017) charge depuis un fichier YAML
 * editable ({@code content/puzzle/*.yml}). Porte les metadonnees de catalogue, la consigne, le
 * capital de coeurs optionnel ({@code jokers}, cf. DEC-0013/s21) et la LISTE DE MOTS verifiee
 * (mot + indice). Le domaine ne connait qu'un {@code MotPuzzleGenerator} generique alimente par
 * ces mots.
 */
public record PuzzleContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String consigne,
        Integer jokers,
        List<MotPuzzleGenerator.Mot> mots) {

    public PuzzleContentPack {
        mots = List.copyOf(mots);
    }
}
