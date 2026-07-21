package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Frise chronologique" (Histoire CM1). Tire {@code COUNT} evenements DISTINCTS
 * dans une liste verifiee, les affiche MELANGES sur les tuiles et attend l'ordre du plus
 * ancien au plus recent (rang chronologique croissant). Meme mecanique glisser-deposer
 * (arrangement ordonne, cf. ADR-0013) que "Range les nombres" : la reponse attendue est
 * l'ARRANGEMENT trie, encode canoniquement via {@link Item#encodeArrangement(List)}.
 */
public final class FriseChronologiqueGenerator implements ContentGenerator {

    /** Un evenement historique et son rang chronologique (croissant = du plus ancien au plus recent). */
    private record Evenement(String label, int rang) {
    }

    /** Liste VERIFIEE d'evenements, rangs chronologiques croissants. */
    private static final List<Evenement> EVENEMENTS = List.of(
            new Evenement("La Préhistoire", 1),
            new Evenement("Les Gaulois", 2),
            new Evenement("Le Moyen Âge", 3),
            new Evenement("La Révolution française", 4),
            new Evenement("La tour Eiffel", 5),
            new Evenement("Aujourd'hui", 6));

    /** Consigne : ranger du plus ancien au plus recent. */
    private static final String QUESTION = "Range du plus ancien au plus récent";

    /** Nombre d'evenements tires par item. */
    private static final int COUNT = 3;

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    /** Nombre d'essais de re-melange pour eviter un item trivial (deja dans l'ordre chronologique). */
    private static final int MAX_SHUFFLE_ATTEMPTS = 10;

    private final ContentDescriptor descriptor;

    public FriseChronologiqueGenerator(ContentDescriptor descriptor) {
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
        // Tire COUNT evenements DISTINCTS de la liste (melange puis prend les COUNT premiers).
        List<Evenement> pool = new ArrayList<>(EVENEMENTS);
        shuffleEvents(pool, random);
        List<Evenement> target = new ArrayList<>(pool.subList(0, COUNT));

        // Cible = ces evenements tries par rang CROISSANT (= ordre chronologique).
        target.sort(Comparator.comparingInt(Evenement::rang));
        List<String> sortedLabels = new ArrayList<>();
        for (Evenement evenement : target) {
            sortedLabels.add(evenement.label());
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

    private void shuffleEvents(List<Evenement> list, RandomSource random) {
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
