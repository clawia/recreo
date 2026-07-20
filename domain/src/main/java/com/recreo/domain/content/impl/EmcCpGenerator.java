package com.recreo.domain.content.impl;

import com.recreo.domain.content.ContentDescriptor;

import java.util.List;

/**
 * "Vivre ensemble" — EMC CP. Jeu "quiz-aventure" : une question sur les règles de vie
 * de la classe et de la cour (politesse, respect, sécurité) et plusieurs réponses à
 * choisir. Liste VERIFIEE ; logique de tirage/exclusion héritée de {@link QuizGenerator}.
 * Ouvre la matière EMC (Enseignement moral et civique) au niveau CP.
 */
public final class EmcCpGenerator extends QuizGenerator {

    private static final List<Question> POOL = List.of(
            new Question("Que dit-on quand on demande quelque chose gentiment ?",
                    "S'il te plaît", List.of("Donne", "Tais-toi")),
            new Question("Avant de parler en classe, il faut...",
                    "lever la main", List.of("crier", "se lever d'un bond")),
            new Question("Quand quelqu'un t'aide, tu dis...",
                    "Merci", List.of("Rien", "C'est normal")),
            new Question("Comment doit-on traiter ses camarades ?",
                    "Avec respect", List.of("En se moquant", "En bousculant")),
            new Question("Où jette-t-on un papier ?",
                    "À la poubelle", List.of("Par terre", "Dans le cartable du voisin")),
            new Question("Si un camarade est triste, tu peux...",
                    "le consoler", List.of("te moquer de lui", "l'ignorer")),
            new Question("Dans la cour, on joue...",
                    "sans se faire mal", List.of("en tapant", "en poussant")),
            new Question("Quand la maîtresse parle, on doit...",
                    "écouter", List.of("parler plus fort", "se lever")),
            new Question("Prêter un jouet à un ami, c'est...",
                    "partager", List.of("voler", "se disputer")),
            new Question("Pour traverser la rue, on regarde...",
                    "des deux côtés", List.of("ses chaussures", "le ciel")));

    public EmcCpGenerator(ContentDescriptor descriptor) {
        super(descriptor, POOL);
    }
}
