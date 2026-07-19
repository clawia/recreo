package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Le plus grand nombre" (Maths CP). Tire deux nombres distincts dans
 * [0, 20], les affiche sur deux rouleaux et attend le plus grand. Deux options
 * seulement (les deux nombres) : un unique distracteur (le plus petit). Meme
 * mecanique bandit manchot. Jeu sans reglage.
 */
public final class ComparaisonGenerator implements ContentGenerator {

    private static final String QUESTION = "Quel est le plus grand ?";
    private static final int MAX = 20;

    private final ContentDescriptor descriptor;

    public ComparaisonGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Generateur combinatoire : exclusion best-effort.
        Item item = buildOne(random);
        for (int attempt = 0; attempt < MAX_EXCLUSION_ATTEMPTS && excludeKeys.contains(item.key()); attempt++) {
            item = buildOne(random);
        }
        return Optional.of(item);
    }

    private Item buildOne(RandomSource random) {
        int a = random.nextInt(MAX + 1); // 0..20
        int b;
        do {
            b = random.nextInt(MAX + 1);
        } while (b == a);

        int max = Math.max(a, b);
        int min = Math.min(a, b);

        List<String> reelSymbols = List.of(String.valueOf(a), String.valueOf(b));
        Answer expected = new Answer(String.valueOf(max));
        List<Answer> distractors = List.of(new Answer(String.valueOf(min)));

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("a", String.valueOf(a));
        meta.put("b", String.valueOf(b));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
