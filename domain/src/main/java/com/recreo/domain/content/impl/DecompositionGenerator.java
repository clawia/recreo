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
 * Generateur "Decompose le nombre" (valeur POSITIONNELLE des chiffres). Affiche un
 * nombre sur un unique rouleau et demande le chiffre d'une position tiree ALEATOIREMENT
 * (unites, dizaines, et centaines selon le mode). L'enfant clique le bon chiffre :
 * meme mecanique bandit manchot. Deux distracteurs distincts, tires en priorite parmi
 * les autres chiffres du nombre, completes par des chiffres voisins.
 *
 * <p>Mode CP : {@code inclureCentaines=false}, nombre dans [10, 99], positions
 * {unites, dizaines}. Mode CM1 : {@code inclureCentaines=true}, nombre dans [100, 999],
 * positions {unites, dizaines, centaines}.</p>
 */
public final class DecompositionGenerator implements ContentGenerator {

    private static final String UNITES = "unités";
    private static final String DIZAINES = "dizaines";
    private static final String CENTAINES = "centaines";

    private final ContentDescriptor descriptor;
    private final boolean inclureCentaines;

    public DecompositionGenerator(ContentDescriptor descriptor, boolean inclureCentaines) {
        this.descriptor = descriptor;
        this.inclureCentaines = inclureCentaines;
    }

    /** Nombre d'essais d'exclusion "best-effort" (generateur combinatoire, cf. ADR-0006). */
    private static final int MAX_EXCLUSION_ATTEMPTS = 12;

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Generateur combinatoire : exclusion best-effort.
        Item item = buildOne(random);
        for (int attempt = 0; attempt < MAX_EXCLUSION_ATTEMPTS && excludeKeys.contains(item.key()); attempt++) {
            item = buildOne(random);
        }
        return Optional.of(item);
    }

    private Item buildOne(RandomSource random) {
        int n;
        List<String> positions;
        if (inclureCentaines) {
            n = 100 + random.nextInt(900); // 100..999
            positions = List.of(UNITES, DIZAINES, CENTAINES);
        } else {
            n = 10 + random.nextInt(90); // 10..99
            positions = List.of(UNITES, DIZAINES);
        }

        int unites = n % 10;
        int dizaines = (n / 10) % 10;
        int centaines = (n / 100) % 10;

        // Position tiree AU HASARD parmi les positions possibles.
        String position = positions.get(random.nextInt(positions.size()));

        int chiffre;
        String question;
        switch (position) {
            case DIZAINES -> {
                chiffre = dizaines;
                question = "Quel est le chiffre des dizaines ?";
            }
            case CENTAINES -> {
                chiffre = centaines;
                question = "Quel est le chiffre des centaines ?";
            }
            default -> {
                chiffre = unites;
                question = "Quel est le chiffre des unités ?";
            }
        }

        Answer expected = new Answer(String.valueOf(chiffre));

        // Un seul rouleau : le nombre affiche.
        List<String> reelSymbols = List.of(String.valueOf(n));

        List<Answer> distractors = buildDistractors(chiffre, unites, dizaines, centaines);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("nombre", String.valueOf(n));
        meta.put("position", position);
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, question, expected, distractors, meta);
    }

    /**
     * Deux distracteurs : chiffres distincts dans [0,9] differents de {@code expected}.
     * Priorite aux AUTRES chiffres du nombre (distincts de expected), puis completion
     * par des chiffres voisins (expected±1 bornes 0..9, puis elargissement deterministe).
     */
    private List<Answer> buildDistractors(int chiffre, int unites, int dizaines, int centaines) {
        Set<Integer> digits = new LinkedHashSet<>();

        // 1) Autres chiffres du nombre, distincts de expected.
        List<Integer> chiffresDuNombre = new ArrayList<>();
        chiffresDuNombre.add(unites);
        chiffresDuNombre.add(dizaines);
        if (inclureCentaines) {
            chiffresDuNombre.add(centaines);
        }
        for (int d : chiffresDuNombre) {
            if (d != chiffre) {
                digits.add(d);
            }
        }

        // 2) Completion par chiffres voisins : expected±1, puis elargissement deterministe.
        for (int offset = 1; digits.size() < 2 && offset <= 9; offset++) {
            int bas = chiffre - offset;
            int haut = chiffre + offset;
            if (bas >= 0) {
                digits.add(bas);
            }
            if (digits.size() < 2 && haut <= 9) {
                digits.add(haut);
            }
        }

        List<Answer> distractors = new ArrayList<>();
        for (int d : digits) {
            if (distractors.size() >= 2) {
                break;
            }
            distractors.add(new Answer(String.valueOf(d)));
        }
        return distractors;
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
