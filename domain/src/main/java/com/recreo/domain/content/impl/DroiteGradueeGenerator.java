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
 * Générateur « Droite graduée » (Maths, mécanique {@code DroiteGradueeMechanic}, cf. ADR-0018).
 * Présente une droite dont les GRADUATIONS sont une liste d'étiquettes (entiers pour situer un
 * NOMBRE, ou fractions pour situer une FRACTION — s23) et demande à l'enfant de placer la
 * bonne valeur en cliquant la bonne graduation. Générateur COMBINATOIRE (exclusion best-effort).
 *
 * <p>Modèle {@code Item} : {@code expected} = l'étiquette à placer (cliquer la graduation revient,
 * via {@code SpinAndPick}, à choisir cette étiquette) ; {@code question} = « Place &lt;nom&gt; X »
 * (ex. « Place le nombre 7 », « Place la fraction 3/4 ») ; {@code meta["ticks"]} = les étiquettes
 * de la droite jointes par « | » (lues par la vue) ; {@code reelSymbols}/{@code distractors}
 * vides (la droite fournit toutes les positions).</p>
 */
public final class DroiteGradueeGenerator implements ContentGenerator {

    /** Séparateur des étiquettes de graduation dans la meta (cf. {@code WebViewMapper}). */
    public static final String TICK_SEPARATOR = "|";

    /** Nombre d'essais d'exclusion "best-effort" (générateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    private final ContentDescriptor descriptor;
    private final List<String> ticks;
    private final String consigneNoun;
    private final Integer min;
    private final Integer max;

    /**
     * Droite d'ÉTIQUETTES quelconques (ex. fractions {@code ["0","1/4","1/2","3/4","1"]}).
     * @param consigneNoun ce qu'on place, ex. « la fraction » → « Place la fraction 3/4 »
     */
    public DroiteGradueeGenerator(ContentDescriptor descriptor, List<String> ticks, String consigneNoun) {
        this(descriptor, List.copyOf(ticks), consigneNoun, null, null);
    }

    /** Droite d'ENTIERS de {@code min} à {@code max} (« Place le nombre N »). */
    public DroiteGradueeGenerator(ContentDescriptor descriptor, int min, int max) {
        this(descriptor, intTicks(min, max), "le nombre", min, max);
        if (max <= min) {
            throw new IllegalArgumentException("max doit etre > min");
        }
    }

    private DroiteGradueeGenerator(ContentDescriptor descriptor, List<String> ticks, String consigneNoun,
                                   Integer min, Integer max) {
        if (ticks.size() < 3) {
            throw new IllegalArgumentException("une droite graduee a besoin d'au moins 3 graduations");
        }
        this.descriptor = descriptor;
        this.ticks = ticks;
        this.consigneNoun = consigneNoun;
        this.min = min;
        this.max = max;
    }

    private static List<String> intTicks(int min, int max) {
        List<String> t = new ArrayList<>();
        for (int v = min; v <= max; v++) {
            t.add(String.valueOf(v));
        }
        return t;
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
        // Cible parmi les graduations INTERNES (on exclut les deux extrémités, triviales à placer).
        int idx = 1 + random.nextInt(ticks.size() - 2);
        String cible = ticks.get(idx);

        String question = "Place " + consigneNoun + " " + cible;
        Answer expected = new Answer(cible);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("ticks", String.join(TICK_SEPARATOR, ticks));
        meta.put("cible", cible);
        if (min != null && max != null) {
            meta.put("min", String.valueOf(min));
            meta.put("max", String.valueOf(max));
        }
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
