package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.PhrasePuzzleGenerator;

import java.util.List;

/**
 * Contenu "reconstitue la phrase" (mecanique puzzle, declinaison "mots" au lieu de "lettres" —
 * cf. ADR-0017) charge depuis un fichier YAML editable ({@code content/phrase/*.yml}). Porte les
 * metadonnees de catalogue, la consigne, le capital de coeurs optionnel ({@code jokers}, cf.
 * DEC-0013/s21) et la LISTE DE PHRASES verifiee (phrase + indice facultatif). Le domaine ne
 * connait qu'un {@code PhrasePuzzleGenerator} generique alimente par ces phrases.
 */
public record PhraseContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String consigne,
        Integer jokers,
        List<PhrasePuzzleGenerator.Phrase> phrases) {

    public PhraseContentPack {
        phrases = List.copyOf(phrases);
    }
}
