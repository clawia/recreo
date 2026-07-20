package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Vrai ou Faux — sciences" — CM1. Variante du jeu "quiz-aventure" où chaque question
 * est une AFFIRMATION avec seulement DEUX options : « Vrai » ou « Faux ». Le moteur de
 * session ({@code SessionEngine.buildReels}) et la vue quiz gèrent un nombre d'options
 * quelconque : ici 1 seul distracteur → 2 boutons. Liste VERIFIEE ; logique de
 * tirage/exclusion héritée de {@link QuizGenerator}.
 */
public final class VraiFauxSciencesCm1Generator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("La Terre tourne autour du Soleil.", "Vrai", List.of("Faux")),
            new Question("Le Soleil est une planète.", "Faux", List.of("Vrai")),
            new Question("L'eau bout à 100 °C.", "Vrai", List.of("Faux")),
            new Question("Les araignées sont des insectes.", "Faux", List.of("Vrai")),
            new Question("Le cœur fait circuler le sang dans le corps.", "Vrai", List.of("Faux")),
            new Question("La Lune produit sa propre lumière.", "Faux", List.of("Vrai")),
            new Question("Les plantes ont besoin de lumière pour grandir.", "Vrai", List.of("Faux")),
            new Question("Un insecte a six pattes.", "Vrai", List.of("Faux")),
            new Question("Les poumons servent à digérer les aliments.", "Faux", List.of("Vrai")),
            new Question("La glace est de l'eau à l'état solide.", "Vrai", List.of("Faux")));

    public VraiFauxSciencesCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
