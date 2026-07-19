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
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Complète l'égalité" (Maths CP), pour la mecanique "texte a trous".
 * Tire {@code a} dans [1, 9] et une cible {@code c} dans [a+1, 10] ; la consigne est
 * {@code "a + ___ = c"} (avec le marqueur {@code ___}), la bonne reponse est
 * {@code c - a} (toujours >= 1), et deux distracteurs sont des nombres voisins,
 * differents de la reponse et jamais negatifs.
 *
 * <p>{@code reelSymbols} = liste VIDE (mecanique "texte a trous" : la phrase/egalite
 * a completer est portee par la consigne). Generateur COMBINATOIRE : exclusion des
 * items reussis en best-effort (cf. ADR-0006), renvoie toujours un item. Place dans
 * {@code content.impl}, hors de portee des mecaniques (ArchUnit 4).</p>
 */
public final class EgaliteGenerator implements ContentGenerator {

    private static final int MAX = 10;
    private static final int DISTRACTOR_COUNT = 2;

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    private final ContentDescriptor descriptor;

    public EgaliteGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Generateur combinatoire : exclusion best-effort. On tente quelques tirages
        // puis on accepte le dernier item, meme si sa cle est deja maitrisee.
        Item item = buildOne(random);
        for (int attempt = 0; attempt < MAX_EXCLUSION_ATTEMPTS && excludeKeys.contains(item.key()); attempt++) {
            item = buildOne(random);
        }
        return Optional.of(item);
    }

    private Item buildOne(RandomSource random) {
        // a dans [1, 9] ; c dans [a+1, 10] -> le complement c - a est dans [1, 10 - a].
        int a = 1 + random.nextInt(MAX - 1); // 1..9
        int c = (a + 1) + random.nextInt(MAX - a); // a+1..10
        int answer = c - a;

        String question = a + " + ___ = " + c;
        Answer expected = new Answer(String.valueOf(answer));
        List<Answer> distractors = buildDistractors(answer);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("a", String.valueOf(a));
        meta.put("c", String.valueOf(c));
        meta.put("complement", String.valueOf(answer));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        // reelSymbols VIDE : mecanique "texte a trous".
        return new Item(new ItemId(UUID.randomUUID().toString()), List.of(), question, expected, distractors, meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /**
     * Deux distracteurs = nombres voisins de la reponse, distincts d'elle et jamais
     * negatifs (elargissement deterministe vers l'exterieur).
     */
    private List<Answer> buildDistractors(int answer) {
        Set<Integer> used = new LinkedHashSet<>();
        used.add(answer);
        List<Answer> distractors = new ArrayList<>();

        int delta = 1;
        while (distractors.size() < DISTRACTOR_COUNT && delta <= MAX) {
            for (int candidate : new int[] {answer - delta, answer + delta}) {
                if (candidate < 0) {
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
