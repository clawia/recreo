package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Le bon mot en anglais" (Anglais CM1, lexique LV). Affiche un mot
 * francais et propose trois traductions anglaises dont la bonne. Les distracteurs
 * sont d'autres traductions de la liste. Meme mecanique bandit manchot. Jeu sans
 * reglage.
 */
public final class AnglaisLexiqueGenerator implements ContentGenerator {

    private static final String QUESTION = "Comment dit-on en anglais ?";
    private static final int DISTRACTOR_COUNT = 2;

    /** Liste verifiee francais -> anglais, ordre d'insertion deterministe. */
    private static final Map<String, String> LEXIQUE = buildLexique();
    private static final List<String> ALL_EN = List.copyOf(new LinkedHashSet<>(LEXIQUE.values()));

    private final ContentDescriptor descriptor;
    private final List<Map.Entry<String, String>> entries;

    public AnglaisLexiqueGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
        this.entries = List.copyOf(LEXIQUE.entrySet());
    }

    private static Map<String, String> buildLexique() {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("chien", "dog");
        m.put("chat", "cat");
        m.put("maison", "house");
        m.put("rouge", "red");
        m.put("bleu", "blue");
        m.put("pomme", "apple");
        m.put("eau", "water");
        m.put("livre", "book");
        m.put("école", "school");
        m.put("ami", "friend");
        m.put("soleil", "sun");
        m.put("main", "hand");
        return m;
    }

    @Override
    public Item next(RandomSource random) {
        Map.Entry<String, String> entry = entries.get(random.nextInt(entries.size()));
        String fr = entry.getKey();
        String en = entry.getValue();

        List<String> reelSymbols = List.of(fr);
        Answer expected = new Answer(en);
        List<Answer> distractors = buildDistractors(en, random);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("fr", fr);
        meta.put("en", en);
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /** Deux traductions anglaises distinctes de la bonne, tirees de la liste. */
    private List<Answer> buildDistractors(String en, RandomSource random) {
        List<String> others = new ArrayList<>(ALL_EN);
        others.remove(en);

        Set<String> used = new LinkedHashSet<>();
        List<Answer> distractors = new ArrayList<>();
        int attempts = 0;
        while (distractors.size() < DISTRACTOR_COUNT && attempts < 100) {
            attempts++;
            String candidate = others.get(random.nextInt(others.size()));
            if (used.add(candidate)) {
                distractors.add(new Answer(candidate));
            }
        }
        // Repli deterministe.
        for (String candidate : others) {
            if (distractors.size() == DISTRACTOR_COUNT) {
                break;
            }
            if (used.add(candidate)) {
                distractors.add(new Answer(candidate));
            }
        }
        return distractors;
    }
}
