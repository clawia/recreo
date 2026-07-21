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
 * Generateur « Compare les fractions » (Maths CM1). Affiche deux fractions et attend le
 * bon signe parmi {@code <}, {@code >}, {@code =}. Meme mecanique bandit manchot que
 * « Le bon symbole » ({@link BonSymboleGenerator}), mais sur des fractions. Combinatoire.
 *
 * <p>Comparaison EXACTE par produit croise (pas de flottant). Le pool inclut des fractions
 * EQUIVALENTES (1/2 = 2/4 = 3/6, ...) pour permettre des cas d'egalite ; un tirage sur
 * trois environ force une paire de meme valeur mais de label different.</p>
 */
public final class CompareFractionsGenerator implements ContentGenerator {

    private static final String QUESTION = "Quel signe ?";
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    /** Une fraction verifiee : numerateur, denominateur, libelle affiche. */
    private record Fraction(int num, int den, String label) {
    }

    /** Pool avec des fractions EQUIVALENTES (memes valeurs, labels differents). */
    private static final List<Fraction> POOL = List.of(
            new Fraction(1, 2, "1/2"), new Fraction(2, 4, "2/4"), new Fraction(3, 6, "3/6"),
            new Fraction(1, 3, "1/3"), new Fraction(2, 6, "2/6"),
            new Fraction(1, 4, "1/4"), new Fraction(2, 8, "2/8"),
            new Fraction(2, 3, "2/3"), new Fraction(3, 4, "3/4"),
            new Fraction(1, 5, "1/5"), new Fraction(4, 5, "4/5"));

    private final ContentDescriptor descriptor;

    public CompareFractionsGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        Item item = buildOne(random);
        for (int attempt = 0; attempt < MAX_EXCLUSION_ATTEMPTS && excludeKeys.contains(item.key()); attempt++) {
            item = buildOne(random);
        }
        return Optional.of(item);
    }

    private Item buildOne(RandomSource random) {
        Fraction fa = POOL.get(random.nextInt(POOL.size()));

        // ~1/3 des cas : forcer une paire de MEME valeur (label different) pour obtenir « = ».
        Fraction fb;
        List<Fraction> sameValue = new ArrayList<>();
        for (Fraction f : POOL) {
            if (!f.label().equals(fa.label()) && crossEquals(fa, f)) {
                sameValue.add(f);
            }
        }
        if (random.nextInt(3) == 0 && !sameValue.isEmpty()) {
            fb = sameValue.get(random.nextInt(sameValue.size()));
        } else {
            do {
                fb = POOL.get(random.nextInt(POOL.size()));
            } while (fb.label().equals(fa.label()));
        }

        long left = (long) fa.num() * fb.den();
        long right = (long) fb.num() * fa.den();
        String expected = left < right ? "<" : left > right ? ">" : "=";
        List<Answer> distractors = new ArrayList<>();
        for (String signe : new String[] {"<", ">", "="}) {
            if (!signe.equals(expected)) {
                distractors.add(new Answer(signe));
            }
        }

        List<String> reelSymbols = List.of(fa.label(), "?", fb.label());
        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("a", fa.label());
        meta.put("b", fb.label());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION,
                new Answer(expected), distractors, meta);
    }

    private static boolean crossEquals(Fraction x, Fraction y) {
        return (long) x.num() * y.den() == (long) y.num() * x.den();
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
