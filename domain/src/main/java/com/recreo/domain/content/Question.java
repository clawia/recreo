package com.recreo.domain.content;

import java.util.List;
import java.util.Optional;

/**
 * ADR-0006 : que du texte. Aucune borne, aucun reglage de matiere.
 * ADR-0012 / S1-5 : l'indice est optionnel et vient du contenu, jamais de la mecanique, qui se
 * contente de decider quand l'afficher.
 */
public record Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs,
                        Optional<String> indice) implements Item {

    public Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs) {
        this(id, enonce, bonneReponse, distracteurs, Optional.empty());
    }
}
