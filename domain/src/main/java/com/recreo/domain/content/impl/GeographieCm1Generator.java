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
                    "L'océan Atlantique", List.of("L'océan Pacifique", "L'océan Indien")),
            new Question("Quelle est la monnaie utilisée en France ?", "L'euro",
                    List.of("Le dollar", "La livre")),
            new Question("Quelle chaîne de montagnes sépare la France de l'Espagne ?", "Les Pyrénées",
                    List.of("Les Alpes", "Le Jura")),
            new Question("Combien de continents y a-t-il sur Terre ?", "6", List.of("4", "8")),
            new Question("Quelle mer borde le sud de la France ?", "La mer Méditerranée",
                    List.of("La mer du Nord", "La mer Rouge")),
            new Question("Quel est le plus long fleuve de France ?", "La Loire",
                    List.of("La Seine", "La Garonne")),
            new Question("Quel pays voisin se trouve à l'est de la France ?", "L'Allemagne",
                    List.of("L'Espagne", "Le Portugal")));

    public GeographieCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
