package com.recreo.domain.content;

/**
 * Value object : une reponse possible (bonne reponse ou distracteur), representee
 * par sa valeur textuelle affichable.
 */
public record Answer(String value) {
    public Answer {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
    }
}
