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

    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    /** Une fraction verifiee : numerateur, denominateur, libelle affiche. */
    public record Fraction(int num, int den, String label) {
    }

    private final ContentDescriptor descriptor;
    private final List<Fraction> pool;
    private final String consigne;

    /**
     * @param pool     fractions VERIFIEES chargees depuis le YAML (contenu externalise, roadmap D),
     *                 incluant des fractions EQUIVALENTES (1/2 = 2/4 = 3/6, ...) pour permettre « = »
     * @param consigne texte affiche (ex. « Quel signe ? »)
     */
    public CompareFractionsGenerator(ContentDescriptor descriptor, List<Fraction> pool, String consigne) {
        this.descriptor = descriptor;
        this.pool = List.copyOf(pool);
        this.consigne = consigne;
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
        Fraction fa = pool.get(random.nextInt(pool.size()));

        // ~1/3 des cas : forcer une paire de MEME valeur (label different) pour obtenir « = ».
        Fraction fb;
        List<Fraction> sameValue = new ArrayList<>();
        for (Fraction f : pool) {
            if (!f.label().equals(fa.label()) && crossEquals(fa, f)) {
                sameValue.add(f);
            }
        }
        if (random.nextInt(3) == 0 && !sameValue.isEmpty()) {
            fb = sameValue.get(random.nextInt(sameValue.size()));
        } else {
            do {
                fb = pool.get(random.nextInt(pool.size()));
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

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, consigne,
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
