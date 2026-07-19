package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Sciences et technologie" — CM1. Jeu "quiz-aventure" : une question de sciences
 * (matiere, vivant, univers) et plusieurs reponses a choisir. Liste VERIFIEE ; logique
 * de tirage/exclusion heritee de {@link QuizGenerator}.
 */
public final class SciencesCm1Generator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("À quelle température l'eau bout-elle ?", "100 °C", List.of("50 °C", "0 °C")),
            new Question("Quand l'eau gèle, elle devient...", "solide", List.of("gazeuse", "invisible")),
            new Question("Quel organe sert à respirer ?", "Les poumons", List.of("L'estomac", "Le foie")),
            new Question("Le Soleil est une...", "étoile", List.of("planète", "lune")),
            new Question("Combien de pattes a un insecte ?", "6", List.of("8", "4")),
            new Question("Grâce à quoi les plantes fabriquent-elles leur nourriture avec la lumière ?",
                    "La photosynthèse", List.of("La digestion", "L'évaporation")));

    public SciencesCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
