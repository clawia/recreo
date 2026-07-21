package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Le bon symbole" (Maths CP). Tire deux nombres {@code a} et {@code b}
 * dans [0, 20], les affiche encadrant un "?" ({@code a ? b}) et attend le signe de
 * comparaison correct parmi {@code <}, {@code >}, {@code =}. Pour garantir des cas
 * d'EGALITE, {@code b} vaut {@code a} avec ~1/3 de chance. Meme mecanique bandit
 * manchot. Jeu sans reglage.
 */
public final class BonSymboleGenerator implements ContentGenerator {

    private static final String QUESTION = "Quel signe ?";
    private static final int MAX = 20;

    /** Signes, dans l'ordre canonique (source des distracteurs deterministes). */
    private static final List<String> SIGNS = List.of("<", ">", "=");

    private final ContentDescriptor descriptor;

    public BonSymboleGenerator(ContentDescriptor descriptor) {
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
        // ~1/3 de chance d'egalite forcee ; sinon tirage libre (peut aussi egaler a).
        int b = random.nextInt(3) == 0 ? a : random.nextInt(MAX + 1);

        String sign = a < b ? "<" : a > b ? ">" : "=";

        List<String> reelSymbols = List.of(String.valueOf(a), "?", String.valueOf(b));
        Answer expected = new Answer(sign);

        // Distracteurs = les deux autres signes, dans l'ordre canonique.
        List<Answer> distractors = new ArrayList<>();
        for (String candidate : SIGNS) {
            if (!candidate.equals(sign)) {
                distractors.add(new Answer(candidate));
            }
        }

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
