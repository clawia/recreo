package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "L'accord de l'adjectif" — Français CM1. Jeu "texte a trous" : completer une phrase
 * par l'adjectif correctement accorde (genre/nombre). Liste VERIFIEE ; logique de
 * tirage/exclusion heritee de {@link PhraseATrouGenerator}.
 */
public final class AccordsCm1Generator extends PhraseATrouGenerator {

    private static final List<Trou> POOL = List.of(
            new Trou("Les fleurs sont ___.", "jolies", List.of("joli", "jolis", "jolie")),
            new Trou("Des chats ___.", "noirs", List.of("noir", "noire", "noires")),
            new Trou("La maison est ___.", "grande", List.of("grand", "grands", "grandes")),
            new Trou("Les garçons sont ___.", "petits", List.of("petit", "petite", "petites")),
            new Trou("Une pomme ___.", "verte", List.of("vert", "verts", "vertes")));

    public AccordsCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
