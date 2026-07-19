package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Histoire" — CM1. Jeu "quiz-aventure" : une question d'histoire (reperes, grands
 * personnages, monuments) et plusieurs reponses a choisir. Liste VERIFIEE ; logique de
 * tirage/exclusion heritee de {@link QuizGenerator}.
 */
public final class HistoireCm1Generator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("En quelle année a eu lieu la Révolution française ?",
                    "1789", List.of("1515", "1945")),
            new Question("Quel monument parisien date de 1889 ?",
                    "La tour Eiffel", List.of("Notre-Dame", "Le château de Versailles")),
            new Question("Qui a fait construire le château de Versailles ?",
                    "Louis XIV", List.of("Napoléon", "Charlemagne")),
            new Question("Les Gaulois vivaient il y a environ...",
                    "2000 ans", List.of("200 ans", "100 ans")),
            new Question("La Préhistoire, c'est l'époque...",
                    "avant l'écriture", List.of("du Moyen Âge", "des rois de France")),
            new Question("Quelle guerre s'est terminée en 1918 ?", "La Première Guerre mondiale",
                    List.of("La guerre de Cent Ans", "La Seconde Guerre mondiale")),
            new Question("Comment appelle-t-on les habitants de la Gaule ?", "Les Gaulois",
                    List.of("Les Romains", "Les Vikings")),
            new Question("Quel roi de France était surnommé le Roi-Soleil ?", "Louis XIV",
                    List.of("Henri IV", "François Ier")),
            new Question("Au Moyen Âge, les chevaliers vivaient dans des...", "châteaux forts",
                    List.of("gratte-ciels", "cavernes")),
            new Question("Qui devient empereur des Français en 1804 ?", "Napoléon Bonaparte",
                    List.of("Louis XIV", "Charlemagne")));

    public HistoireCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
