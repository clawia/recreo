package com.recreo.bootstrap.config;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.QuizGenerator.Question;

import java.util.List;

/**
 * Contenu "quiz-aventure" chargé depuis un fichier YAML éditable ({@code content/quiz/*.yml}).
 * Porte les métadonnées de catalogue (entryId, gameKey/gameLabel, titre, descripteur
 * pédagogique) et la LISTE DE QUESTIONS vérifiée. C'est l'externalisation du contenu quiz
 * (cf. DEC-0006 / ADR-0009) : le domaine ne connaît plus qu'un {@code QuizGenerator}
 * générique alimenté par cette liste ; il n'y a plus une sous-classe Java par jeu.
 */
public record QuizContentPack(
        String entryId,
        String title,
        String description,
        String gameKey,
        String gameLabel,
        ContentDescriptor descriptor,
        List<Question> questions) {

    public QuizContentPack {
        questions = List.copyOf(questions);
    }
}
