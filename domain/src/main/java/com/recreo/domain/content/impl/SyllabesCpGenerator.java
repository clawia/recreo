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
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur de francais CP : "denombrer les syllabes d'un mot". Chaque item affiche
 * UN rouleau (le mot en majuscules), pose la question "Combien de syllabes ?" et attend
 * le nombre de syllabes orales. Les distracteurs sont des nombres voisins.
 *
 * <p>Le contenu (liste mot -> nombre de syllabes) est fourni de l'exterieur — il est
 * externalise dans {@code content/syllabes/*.yml} (cf. ADR-0011) : ce generateur ne
 * porte plus de liste en dur. Meme mecanique que le calcul mental (bandit manchot).
 * Place dans {@code content.impl}, hors de portee des mecaniques (ArchUnit 4).</p>
 */
public final class SyllabesCpGenerator implements ContentGenerator {

    /** Consigne affichee a l'enfant. */
    private static final String QUESTION = "Combien de syllabes ?";

    /** Nombre de distracteurs (CP). */
    private static final int DISTRACTOR_COUNT = 2;

    /** Un mot verifie et son nombre de syllabes orales. */
    public record Mot(String mot, int syllabes) {
    }

    private final ContentDescriptor descriptor;
    private final List<Mot> entries;

    /** Comportement historique : aucun filtre, toute la liste de mots est jouable. */
    public SyllabesCpGenerator(ContentDescriptor descriptor, List<Mot> mots) {
        this(descriptor, mots, null);
    }

    /**
     * Generateur configurable : si {@code maxSyllabes} est non nul, seuls les mots
     * comptant au plus {@code maxSyllabes} syllabes sont tires. {@code null} =
     * aucun filtre.
     *
     * @throws IllegalArgumentException si le filtre ne laisse aucun mot jouable.
     */
    public SyllabesCpGenerator(ContentDescriptor descriptor, List<Mot> mots, Integer maxSyllabes) {
        this.descriptor = descriptor;
        List<Mot> filtered = mots.stream()
                .filter(m -> maxSyllabes == null || m.syllabes() <= maxSyllabes)
                .toList();
        if (filtered.isEmpty()) {
            throw new IllegalArgumentException(
                    "Aucun mot ne respecte maxSyllabes=" + maxSyllabes);
        }
        this.entries = List.copyOf(filtered);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Cle d'un item syllabes = celle derivee du contenu (mot en MAJUSCULES + consigne).
        List<Mot> available = entries.stream()
                .filter(m -> !excludeKeys.contains(
                        Item.keyOf(List.of(m.mot().toUpperCase(Locale.FRENCH)), QUESTION)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier (filtre) epuise
        }

        Mot entry = available.get(random.nextInt(available.size()));
        String mot = entry.mot();
        int syllabes = entry.syllabes();

        List<String> reelSymbols = List.of(mot.toUpperCase(Locale.FRENCH));
        Answer expected = new Answer(String.valueOf(syllabes));
        List<Answer> distractors = buildDistractors(syllabes);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("mot", mot);
        meta.put("syllabes", String.valueOf(syllabes));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return Optional.of(new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta));
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(entries.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /**
     * Distracteurs plausibles : nombres voisins (expected-1, expected+1, ...),
     * jamais inferieurs a 1, sans doublon et differents de la reponse.
     */
    private List<Answer> buildDistractors(int expected) {
        Set<Integer> used = new LinkedHashSet<>();
        used.add(expected);
        List<Answer> distractors = new ArrayList<>();

        int delta = 1;
        while (distractors.size() < DISTRACTOR_COUNT) {
            for (int candidate : new int[] {expected - delta, expected + delta}) {
                if (candidate < 1) {
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
