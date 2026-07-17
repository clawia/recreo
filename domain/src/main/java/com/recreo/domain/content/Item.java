package com.recreo.domain.content;

import java.util.List;
import java.util.Map;

/**
 * Une unite de contenu jouable : un enonce, la reponse attendue, des distracteurs
 * et des meta-donnees libres (operation, operandes, ...).
 */
public record Item(ItemId id, String prompt, Answer expected, List<Answer> distractors, Map<String, String> meta) {
    public Item {
        distractors = List.copyOf(distractors);
        meta = Map.copyOf(meta);
    }
}
