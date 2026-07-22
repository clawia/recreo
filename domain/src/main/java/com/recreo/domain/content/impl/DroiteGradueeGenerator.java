package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Générateur « Droite graduée » (Maths, mécanique {@code DroiteGradueeMechanic}, cf. ADR-0018).
 * Tire un NOMBRE ENTIER cible dans {@code [min, max]} que l'enfant doit placer sur une droite
 * graduée de {@code min} à {@code max}. Générateur COMBINATOIRE (exclusion best-effort, comme
 * {@link CalculMentalGenerator}).
 *
 * <p>Modèle {@code Item} : {@code expected} = le nombre à placer (cliquer la bonne graduation
 * revient, via {@code SpinAndPick}, à choisir cette valeur) ; {@code question} = « Place le
 * nombre N » ; {@code meta["min"]}/{@code meta["max"]} = bornes de la droite (lues par la vue) ;
 * {@code reelSymbols}/{@code distractors} vides (la droite fournit toutes les positions).</p>
 */
public final class DroiteGradueeGenerator implements ContentGenerator {

    /** Nombre d'essais d'exclusion "best-effort" (générateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    private final ContentDescriptor descriptor;
    private final int min;
    private final int max;

    public DroiteGradueeGenerator(ContentDescriptor descriptor, int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("max doit etre > min");
        }
        this.descriptor = descriptor;
        this.min = min;
        this.max = max;
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
        // Cible dans [min, max] ; on evite les bornes triviales quand la plage le permet.
        int span = max - min + 1;
        int cible = min + random.nextInt(span);

        String question = "Place le nombre " + cible;
        Answer expected = new Answer(String.valueOf(cible));

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("min", String.valueOf(min));
        meta.put("max", String.valueOf(max));
        meta.put("cible", String.valueOf(cible));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        // reelSymbols/distractors vides : la droite graduee fournit toutes les positions.
        return new Item(new ItemId(UUID.randomUUID().toString()), List.of(), question, expected, List.of(), meta);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
