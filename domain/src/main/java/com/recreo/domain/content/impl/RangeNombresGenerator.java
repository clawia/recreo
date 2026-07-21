package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Range les nombres" (Maths CP). Tire {@code count} nombres DISTINCTS dans
 * [0, max], les affiche MELANGES sur les tuiles et attend l'ordre croissant ou decroissant
 * selon le SENS ({@code croissant}). Mecanique glisser-deposer (cf. ADR-0013) : la reponse
 * attendue est l'ARRANGEMENT trie, encode canoniquement via {@link Item#encodeArrangement(List)}.
 */
public final class RangeNombresGenerator implements ContentGenerator {

    private final ContentDescriptor descriptor;
    private final int count;
    private final int max;
    private final boolean croissant;
    private final String question;

    public RangeNombresGenerator(ContentDescriptor descriptor) {
        this(descriptor, 4, 20, true);
    }

    public RangeNombresGenerator(ContentDescriptor descriptor, int count, int max, boolean croissant) {
        this.descriptor = descriptor;
        this.count = count;
        this.max = max;
        this.croissant = croissant;
        this.question = croissant
                ? "Range les nombres du plus petit au plus grand"
                : "Range les nombres du plus grand au plus petit";
    }

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    /** Nombre d'essais de re-melange pour eviter un item trivial (deja trie). */
    private static final int MAX_SHUFFLE_ATTEMPTS = 10;

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
        // Tire count entiers DISTINCTS dans [0, max].
        Set<Integer> values = new LinkedHashSet<>();
        while (values.size() < count) {
            values.add(random.nextInt(max + 1));
        }

        // Liste TRIEE selon le sens (croissante ou decroissante) : c'est la reponse attendue.
        List<Integer> sortedInts = new ArrayList<>(values);
        Collections.sort(sortedInts);
        if (!croissant) {
            Collections.reverse(sortedInts);
        }
        List<String> sorted = new ArrayList<>();
        for (int value : sortedInts) {
            sorted.add(String.valueOf(value));
        }

        Answer expected = new Answer(Item.encodeArrangement(sorted));

        // Symboles affiches : une copie MELANGEE de la liste triee (item non trivial).
        List<String> reelSymbols = new ArrayList<>(sorted);
        shuffle(reelSymbols, random);
        for (int attempt = 0; attempt < MAX_SHUFFLE_ATTEMPTS && reelSymbols.equals(sorted); attempt++) {
            shuffle(reelSymbols, random);
        }

        List<Answer> distractors = List.of();

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, question, expected, distractors, meta);
    }

    private void shuffle(List<String> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
