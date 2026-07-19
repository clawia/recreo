package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Conjugaison (présent)" — Français CM1. Jeu "texte a trous" : completer une phrase
 * par la forme conjuguee correcte au present de l'indicatif. Liste VERIFIEE ; la
 * logique de tirage/exclusion est heritee de {@link PhraseATrouGenerator}.
 */
public final class ConjugaisonCm1Generator extends PhraseATrouGenerator {

    private static final List<Trou> POOL = List.of(
            new Trou("Tous les jours, il ___ à l'école.", "va", List.of("vais", "vont", "allez")),
            new Trou("Nous ___ une histoire.", "lisons", List.of("lit", "lisent", "lis")),
            new Trou("Vous ___ du chocolat.", "mangez", List.of("mange", "mangeons", "mangent")),
            new Trou("Je ___ mes devoirs.", "fais", List.of("fait", "faisons", "font")),
            new Trou("Elles ___ dans le jardin.", "jouent", List.of("joue", "jouons", "jouez")),
            new Trou("Tu ___ un dessin.", "dessines", List.of("dessine", "dessinent", "dessinez")));

    public ConjugaisonCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
