package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.CompareFractionsGenerator;

import java.util.List;

/**
 * Contenu "compare les fractions" (bandit manchot, Maths CM1) charge depuis un fichier YAML
 * editable ({@code content/fractions/*.yml}). Porte les metadonnees de catalogue, la consigne,
 * le capital de coeurs optionnel ({@code jokers}, cf. DEC-0013/s21) et le POOL DE FRACTIONS
 * verifie. Le domaine ne connait qu'un {@code CompareFractionsGenerator} generique alimente
 * par ce pool.
 */
public record FractionsContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        String consigne,
        Integer jokers,
        List<CompareFractionsGenerator.Fraction> fractions) {

    public FractionsContentPack {
        fractions = List.copyOf(fractions);
    }
}
