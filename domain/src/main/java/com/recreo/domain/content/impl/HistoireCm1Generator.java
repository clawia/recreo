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
                    "avant l'écriture", List.of("du Moyen Âge", "des rois de France")));

    public HistoireCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
