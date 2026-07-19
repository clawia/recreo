package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.DifficultyConfig;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Operation;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur concret de calcul mental. Parametre par une {@link DifficultyConfig},
 * il produit des enonces "a op b = ?" et des distracteurs plausibles.
 *
 * <p>Place dans le sous-package {@code content.impl} : les regles ArchUnit
 * interdisent aux mecaniques d'en dependre (decouplage mecanique/contenu).</p>
 */
public final class CalculMentalGenerator implements ContentGenerator {

    private final DifficultyConfig config;
    private final ContentDescriptor descriptor;
    private final List<Operation> operations;

    public CalculMentalGenerator(DifficultyConfig config, ContentDescriptor descriptor) {
        this.config = config;
        this.descriptor = descriptor;
        // Ordre deterministe (ordinal de l'enum) pour des tests reproductibles.
        this.operations = config.operations().stream()
                .sorted(Comparator.comparingInt(Enum::ordinal))
                .toList();
    }

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

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
        Operation op = operations.get(random.nextInt(operations.size()));
        int range = config.maxOperand() - config.minOperand() + 1;
        int a = config.minOperand() + random.nextInt(range);
        int b = config.minOperand() + random.nextInt(range);

        // Pas de resultat negatif pour de jeunes enfants.
        if (op == Operation.SOUSTRACTION && b > a) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int result = op.apply(a, b);
        // Les rouleaux affichent le calcul (operande, operateur, operande) ; la
        // consigne "= ?" est fournie explicitement (cf. ADR-0004).
        List<String> reelSymbols = List.of(String.valueOf(a), op.symbol(), String.valueOf(b));
        String question = "= ?";
        Answer expected = new Answer(String.valueOf(result));
        List<Answer> distractors = buildDistractors(result, config.distractorCount(), random);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("operation", op.name());
        meta.put("operandA", String.valueOf(a));
        meta.put("operandB", String.valueOf(b));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, question, expected, distractors, meta);
    }

    // Pas de distinctPoolSize : generateur combinatoire (vivier non enumere).

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    private List<Answer> buildDistractors(int expected, int count, RandomSource random) {
        Set<Integer> used = new LinkedHashSet<>();
        used.add(expected);
        List<Answer> distractors = new ArrayList<>();

        int span = 2 * count + 3;
        int attempts = 0;
        while (distractors.size() < count && attempts < 100) {
            attempts++;
            int delta = random.nextInt(span) - (count + 1);
            if (delta == 0) {
                continue;
            }
            int candidate = expected + delta;
            if (candidate < 0) {
                continue;
            }
            if (used.add(candidate)) {
                distractors.add(new Answer(String.valueOf(candidate)));
            }
        }

        // Repli deterministe si l'aleatoire n'a pas suffi a atteindre le compte.
        int k = 1;
        while (distractors.size() < count) {
            int candidate = expected + k;
            if (used.add(candidate)) {
                distractors.add(new Answer(String.valueOf(candidate)));
            }
            k++;
        }
        return distractors;
    }
}
