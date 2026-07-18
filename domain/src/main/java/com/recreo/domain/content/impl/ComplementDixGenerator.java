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
 * Generateur "Complements a 10" (Maths CP). Tire un nombre {@code n} dans [0, 10],
 * affiche UN rouleau ({@code n}) et attend le complement {@code 10 - n}. Meme
 * mecanique bandit manchot : ce n'est qu'un {@link ContentGenerator} de plus (cf.
 * ADR-0004/0005). Jeu sans reglage.
 */
public final class ComplementDixGenerator implements ContentGenerator {

    private static final String QUESTION = "Combien pour aller à 10 ?";
    private static final int TARGET = 10;
    private static final int DISTRACTOR_COUNT = 2;

    private final ContentDescriptor descriptor;

    public ComplementDixGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Item next(RandomSource random) {
        int n = random.nextInt(TARGET + 1); // 0..10
        int answer = TARGET - n;

        List<String> reelSymbols = List.of(String.valueOf(n));
        Answer expected = new Answer(String.valueOf(answer));
        List<Answer> distractors = buildDistractors(answer);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("nombre", String.valueOf(n));
        meta.put("complement", String.valueOf(answer));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /**
     * Deux distracteurs = nombres voisins de la reponse, dans [0, 10], distincts de
     * la reponse (elargissement deterministe vers l'exterieur).
     */
    private List<Answer> buildDistractors(int answer) {
        Set<Integer> used = new LinkedHashSet<>();
        used.add(answer);
        List<Answer> distractors = new ArrayList<>();

        int delta = 1;
        while (distractors.size() < DISTRACTOR_COUNT && delta <= TARGET) {
            for (int candidate : new int[] {answer - delta, answer + delta}) {
                if (candidate < 0 || candidate > TARGET) {
                    continue;
                }
                if (used.add(candidate)) {
                    distractors.add(new Answer(String.valueOf(candidate)));
                    if (distractors.size() == DISTRACTOR_COUNT) {
                        break;
                    }
                }
            }
            delta++;
        }
        return distractors;
    }
}
