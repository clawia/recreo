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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Range les fractions" (Maths CM1). Tire {@code COUNT} fractions DISTINCTES
 * dans une liste verifiee (valeurs toutes differentes), les affiche MELANGEES sur les
 * tuiles et attend l'ordre de la plus petite a la plus grande (valeur croissante). Meme
 * mecanique glisser-deposer (arrangement ordonne, cf. ADR-0013) que "Range les nombres" et
 * "Frise chronologique" : la reponse attendue est l'ARRANGEMENT trie, encode canoniquement
 * via {@link Item#encodeArrangement(List)}.
 */
public final class FractionsGenerator implements ContentGenerator {

    /** Une fraction {@code num/den} et son etiquette d'affichage. */
    private record Fraction(int num, int den, String label) {
    }

    /**
     * Liste VERIFIEE de fractions a valeurs DISTINCTES (aucune valeur egale), ce qui garantit
     * un tri strict et donc une reponse attendue unique.
     */
    private static final List<Fraction> FRACTIONS = List.of(
            new Fraction(1, 5, "1/5"),
            new Fraction(1, 4, "1/4"),
            new Fraction(1, 3, "1/3"),
            new Fraction(1, 2, "1/2"),
            new Fraction(2, 3, "2/3"),
            new Fraction(3, 4, "3/4"),
            new Fraction(4, 5, "4/5"));

    /** Consigne : ranger de la plus petite a la plus grande. */
    private static final String QUESTION = "Range les fractions de la plus petite à la plus grande";

    /** Nombre de fractions tirees par item. */
    private static final int COUNT = 3;

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    /** Nombre d'essais de re-melange pour eviter un item trivial (deja dans l'ordre croissant). */
    private static final int MAX_SHUFFLE_ATTEMPTS = 10;

    private final ContentDescriptor descriptor;

    public FractionsGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Generateur combinatoire (sous-ensembles) : exclusion best-effort.
        Item item = buildOne(random);
        for (int attempt = 0; attempt < MAX_EXCLUSION_ATTEMPTS && excludeKeys.contains(item.key()); attempt++) {
            item = buildOne(random);
        }
        return Optional.of(item);
    }

    private Item buildOne(RandomSource random) {
        // Tire COUNT fractions DISTINCTES de la liste (melange puis prend les COUNT premieres).
        List<Fraction> pool = new ArrayList<>(FRACTIONS);
        shuffleFractions(pool, random);
        List<Fraction> target = new ArrayList<>(pool.subList(0, COUNT));

        // Cible = ces fractions triees par VALEUR croissante. Comparaison par produit croise
        // (a/b < c/d <=> a*d < c*b) pour eviter toute imprecision de flottant.
        target.sort((left, right) -> Integer.compare(left.num() * right.den(), right.num() * left.den()));
        List<String> sortedLabels = new ArrayList<>();
        for (Fraction fraction : target) {
            sortedLabels.add(fraction.label());
        }

        Answer expected = new Answer(Item.encodeArrangement(sortedLabels));

        // Symboles affiches : une copie MELANGEE des labels (item non trivial).
        List<String> reelSymbols = new ArrayList<>(sortedLabels);
        shuffle(reelSymbols, random);
        for (int attempt = 0; attempt < MAX_SHUFFLE_ATTEMPTS && reelSymbols.equals(sortedLabels); attempt++) {
            shuffle(reelSymbols, random);
        }

        List<Answer> distractors = List.of();

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta);
    }

    private void shuffleFractions(List<Fraction> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
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
