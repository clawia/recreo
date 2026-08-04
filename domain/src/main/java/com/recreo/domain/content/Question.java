package com.recreo.domain.content;

import java.util.List;

/**
 * ADR-0006 : que du texte. Aucune borne, aucun reglage de matiere.
 */
public record Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs)
        implements Item {
}
