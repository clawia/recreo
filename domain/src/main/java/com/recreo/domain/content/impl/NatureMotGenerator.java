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
 * Generateur "La nature du mot" (Francais CM1, classes de mots). Affiche un mot et
 * propose trois natures dont la bonne (parmi nom / verbe / adjectif / determinant).
 * Meme mecanique bandit manchot. Jeu sans reglage.
 */
public final class NatureMotGenerator implements ContentGenerator {

    private static final String QUESTION = "Quelle est la nature de ce mot ?";
    private static final int DISTRACTOR_COUNT = 2;

    private static final String NOM = "nom";
    private static final String VERBE = "verbe";
    private static final String ADJECTIF = "adjectif";
    private static final String DETERMINANT = "déterminant";

    /** Les quatre natures possibles (source des distracteurs). */
    private static final List<String> NATURES = List.of(NOM, VERBE, ADJECTIF, DETERMINANT);

    /** Liste verifiee mot -> nature, ordre d'insertion deterministe. */
    private static final Map<String, String> MOTS = buildMots();

    private final ContentDescriptor descriptor;
    private final List<Map.Entry<String, String>> entries;

    public NatureMotGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
        this.entries = List.copyOf(MOTS.entrySet());
    }

    private static Map<String, String> buildMots() {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("chat", NOM);
        m.put("manger", VERBE);
        m.put("rouge", ADJECTIF);
        m.put("le", DETERMINANT);
        m.put("courir", VERBE);
        m.put("maison", NOM);
        m.put("grand", ADJECTIF);
        m.put("une", DETERMINANT);
        m.put("chanter", VERBE);
        m.put("joli", ADJECTIF);
        m.put("table", NOM);
        m.put("mon", DETERMINANT);
        m.put("sauter", VERBE);
        m.put("petit", ADJECTIF);
        m.put("la", DETERMINANT);
        m.put("fleur", NOM);
        return m;
    }

    @Override
    public Item next(RandomSource random) {
        Map.Entry<String, String> entry = entries.get(random.nextInt(entries.size()));
        String mot = entry.getKey();
        String nature = entry.getValue();

        List<String> reelSymbols = List.of(mot);
        Answer expected = new Answer(nature);
        List<Answer> distractors = buildDistractors(nature, random);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("mot", mot);
        meta.put("nature", nature);
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /** Deux natures distinctes de la bonne, tirees parmi les trois restantes. */
    private List<Answer> buildDistractors(String nature, RandomSource random) {
        List<String> others = new ArrayList<>(NATURES);
        others.remove(nature);

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
