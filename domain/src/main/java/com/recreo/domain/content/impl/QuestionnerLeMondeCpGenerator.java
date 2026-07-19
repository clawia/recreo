package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Questionner le monde" — CP. Jeu "quiz-aventure" : une question du quotidien
 * (animaux, nature, corps) et plusieurs reponses a choisir. Liste VERIFIEE ; logique
 * de tirage/exclusion heritee de {@link QuizGenerator}.
 */
public final class QuestionnerLeMondeCpGenerator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("Quel animal fait « miaou » ?", "Le chat", List.of("Le chien", "La vache")),
            new Question("Où vivent les poissons ?", "Dans l'eau", List.of("Dans les arbres", "Sous la terre")),
            new Question("Combien de pattes a une araignée ?", "8", List.of("6", "4")),
            new Question("De quoi une plante a-t-elle besoin pour pousser ?",
                    "De l'eau et de la lumière", List.of("Du chocolat", "Du sable")),
            new Question("Quel est le petit de la vache ?", "Le veau", List.of("Le poulain", "L'agneau")),
            new Question("Que mange surtout le lapin ?", "Des carottes", List.of("De la viande", "Des cailloux")),
            new Question("Quand il neige, il fait...", "froid", List.of("chaud", "lourd")));

    public QuestionnerLeMondeCpGenerator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
