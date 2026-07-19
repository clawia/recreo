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
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "La premiere lettre" (Francais CP, principe alphabetique). Affiche un
 * mot en MAJUSCULES sur un rouleau et attend sa premiere lettre. Deux distracteurs
 * = autres lettres majuscules aleatoires distinctes. Meme mecanique bandit manchot.
 * Jeu sans reglage.
 */
public final class PremiereLettreGenerator implements ContentGenerator {

    private static final String QUESTION = "Par quelle lettre commence ce mot ?";
    private static final int DISTRACTOR_COUNT = 2;

    /** Mots verifies (en MAJUSCULES), figes pour eviter toute erreur. */
    private static final List<String> MOTS = List.of(
            "BATEAU", "MAISON", "CHAT", "POMME", "LAPIN", "SOLEIL",
            "TABLE", "RENARD", "VELO", "FLEUR", "ROBOT", "CANARD");

    private final ContentDescriptor descriptor;

    public PremiereLettreGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Cle d'un item = celle derivee du contenu (mot affiche + consigne).
        List<String> available = MOTS.stream()
                .filter(m -> !excludeKeys.contains(Item.keyOf(List.of(m), QUESTION)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier epuise
        }

        String mot = available.get(random.nextInt(available.size()));
        String lettre = mot.substring(0, 1);

        List<String> reelSymbols = List.of(mot);
        Answer expected = new Answer(lettre);
        List<Answer> distractors = buildDistractors(lettre, random);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("mot", mot);
        meta.put("lettre", lettre);
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return Optional.of(new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta));
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(MOTS.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /** Deux lettres majuscules (A..Z) distinctes de la reponse et entre elles. */
    private List<Answer> buildDistractors(String lettre, RandomSource random) {
        Set<String> used = new LinkedHashSet<>();
        used.add(lettre);
        List<Answer> distractors = new ArrayList<>();

        int attempts = 0;
        while (distractors.size() < DISTRACTOR_COUNT && attempts < 200) {
            attempts++;
            String candidate = String.valueOf((char) ('A' + random.nextInt(26)));
            if (used.add(candidate)) {
                distractors.add(new Answer(candidate));
            }
        }
        // Repli deterministe si l'aleatoire n'a pas suffi.
        char c = 'A';
        while (distractors.size() < DISTRACTOR_COUNT) {
            String candidate = String.valueOf(c);
            if (used.add(candidate)) {
                distractors.add(new Answer(candidate));
            }
            c++;
        }
        return distractors;
    }
}
