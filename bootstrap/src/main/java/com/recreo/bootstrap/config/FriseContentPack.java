package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.FriseChronologiqueGenerator;

import java.util.List;

/**
 * Contenu "frise chronologique" (glisser-deposer, Histoire CM1) charge depuis un fichier
 * YAML editable ({@code content/frise/*.yml}). Porte les metadonnees de catalogue, la
 * consigne, le nombre d'evenements tires par item ({@code count}), le capital de coeurs
 * optionnel ({@code jokers}, cf. DEC-0013/s21) et la LISTE D'EVENEMENTS verifiee. Le domaine
 * ne connait qu'un {@code FriseChronologiqueGenerator} generique alimente par ces evenements.
 */
public record FriseContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String consigne,
        int count,
        Integer jokers,
        List<FriseChronologiqueGenerator.Evenement> evenements) {

    public FriseContentPack {
        evenements = List.copyOf(evenements);
    }
}
