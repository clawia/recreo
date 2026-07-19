package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Géographie" — CM1. Jeu "quiz-aventure" : une question de geographie (France,
 * reliefs, continents) et plusieurs reponses a choisir. Liste VERIFIEE ; logique de
 * tirage/exclusion heritee de {@link QuizGenerator}.
 */
public final class GeographieCm1Generator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("Quelle est la capitale de la France ?", "Paris", List.of("Lyon", "Marseille")),
            new Question("Quel fleuve traverse Paris ?", "La Seine", List.of("La Loire", "Le Rhône")),
            new Question("Sur quel continent se trouve la France ?", "L'Europe", List.of("L'Afrique", "L'Asie")),
            new Question("Quel est le plus haut sommet des Alpes ?",
                    "Le mont Blanc", List.of("Le Puy de Dôme", "Le mont Ventoux")),
            new Question("Quel pays se trouve au sud de la France ?",
                    "L'Espagne", List.of("L'Allemagne", "La Belgique")),
            new Question("Quel océan borde la France à l'ouest ?",
                    "L'océan Atlantique", List.of("L'océan Pacifique", "L'océan Indien")));

    public GeographieCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
