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
                    "La photosynthèse", List.of("La digestion", "L'évaporation")),
            new Question("Combien de planètes compte le système solaire ?", "8", List.of("9", "7")),
            new Question("Quel gaz de l'air nous est indispensable pour respirer ?", "L'oxygène",
                    List.of("Le dioxyde de carbone", "L'hélium")),
            new Question("Quel organe pompe le sang dans tout le corps ?", "Le cœur",
                    List.of("Le cerveau", "Les reins")),
            new Question("En dessous de 0 °C, l'eau devient...", "solide", List.of("liquide", "gazeuse")),
            new Question("Sur quelle planète vivons-nous ?", "La Terre", List.of("Mars", "Jupiter")),
            new Question("Qu'est-ce qui attire les objets vers le sol ?", "La gravité",
                    List.of("Le vent", "La lumière")));

    public SciencesCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
