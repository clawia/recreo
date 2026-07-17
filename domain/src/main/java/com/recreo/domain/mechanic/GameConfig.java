package com.recreo.domain.mechanic;

import com.recreo.domain.content.ContentGenerator;

import java.util.Map;

/**
 * Point de rencontre entre une mecanique et un contenu : c'est ici que le
 * decouplage se materialise. Une meme mecanique + un meme generateur + deux
 * configs = deux niveaux jouables.
 */
public record GameConfig(MechanicId mechanic, ContentGenerator content, Map<String, Object> settings) {
    public GameConfig {
        settings = Map.copyOf(settings);
    }
}
