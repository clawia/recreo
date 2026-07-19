package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Le mot qui manque" — Français CP. Jeu "texte a trous" : completer une phrase par
 * le mot qui a du sens. Liste VERIFIEE ; logique de tirage/exclusion heritee de
 * {@link PhraseATrouGenerator}.
 */
public final class MotManquantCpGenerator extends PhraseATrouGenerator {

    private static final List<Trou> POOL = List.of(
            new Trou("Le chat boit du ___.", "lait", List.of("pain", "vélo", "chien")),
            new Trou("Je dors dans mon ___.", "lit", List.of("bol", "chat", "arbre")),
            new Trou("Le soleil est dans le ___.", "ciel", List.of("sac", "pied", "livre")),
            new Trou("On écrit avec un ___.", "crayon", List.of("ballon", "gâteau", "nuage")),
            new Trou("Le poisson nage dans l'___.", "eau", List.of("air", "herbe", "feu")));

    public MotManquantCpGenerator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
