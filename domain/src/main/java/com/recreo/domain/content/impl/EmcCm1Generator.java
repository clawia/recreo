package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Citoyenneté & respect" — EMC CM1. Jeu "quiz-aventure" : une question sur les
 * symboles de la République, les valeurs, les droits et devoirs, et plusieurs réponses
 * à choisir. Liste VERIFIEE ; logique de tirage/exclusion héritée de {@link QuizGenerator}.
 * Ouvre la matière EMC (Enseignement moral et civique) au niveau CM1.
 */
public final class EmcCm1Generator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("Quelle est la devise de la République française ?",
                    "Liberté, Égalité, Fraternité", List.of("Paix, Amour, Joie", "Travail, Famille, Patrie")),
            new Question("Quelles sont les couleurs du drapeau français ?",
                    "Bleu, blanc, rouge", List.of("Rouge et jaune", "Vert, blanc, rouge")),
            new Question("Comment s'appelle l'hymne national français ?",
                    "La Marseillaise", List.of("La Parisienne", "Le Chant du départ")),
            new Question("Respecter les autres, c'est accepter...",
                    "leurs différences", List.of("seulement ses amis", "les plus forts")),
            new Question("Dans une démocratie, les lois sont votées par...",
                    "les représentants élus", List.of("le roi", "une seule personne")),
            new Question("Le 14 juillet, en France, on célèbre...",
                    "la fête nationale", List.of("Noël", "le nouvel an")),
            new Question("Qui peut voter aux élections en France ?",
                    "Les citoyens majeurs", List.of("Les enfants", "Tout le monde dès la naissance")),
            new Question("Harceler un camarade, c'est...",
                    "interdit et puni", List.of("autorisé", "un simple jeu")),
            new Question("En France, l'école est...",
                    "obligatoire et gratuite", List.of("payante", "réservée aux garçons")),
            new Question("Qui Marianne représente-t-elle ?",
                    "La République française", List.of("Une reine", "Une chanteuse")));

    public EmcCm1Generator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
