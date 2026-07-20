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
 * Générateur GÉNÉRIQUE « mot → réponse » pour le bandit manchot : un prompt (mot ou
 * phrase) s'affiche sur le rouleau, une consigne fixe est posée, l'enfant choisit la
 * bonne réponse parmi des options. Le contenu est fourni de l'extérieur (liste
 * d'{@link Entree}), ce qui permet de l'externaliser dans des fichiers éditables
 * (cf. ADR-0010) : il n'y a plus une sous-classe Java par jeu (le bon son, homophones,
 * première lettre, nature du mot, anglais…).
 *
 * <p>Deux stratégies de distracteurs :</p>
 * <ul>
 *   <li><b>explicite</b> ({@link #explicit}) : chaque entrée porte ses propres
 *       distracteurs (le bon son, homophones) ;</li>
 *   <li><b>vivier</b> ({@link #fromPool}) : les distracteurs sont tirés aléatoirement
 *       parmi l'ensemble des autres bonnes réponses du jeu (anglais, nature du mot,
 *       première lettre) — comportement dynamique, comme les générateurs historiques.</li>
 * </ul>
 */
public final class MotChoixGenerator implements ContentGenerator {

    /** Une entrée vérifiée : le prompt affiché, la bonne réponse, ses distracteurs (mode explicite). */
    public record Entree(String prompt, String bonne, List<String> distracteurs) {
        public Entree {
            distracteurs = List.copyOf(distracteurs);
        }
    }

    private final ContentDescriptor descriptor;
    private final String question;
    private final List<Entree> entrees;
    /** > 0 = mode vivier (nombre de distracteurs à tirer) ; 0 = mode explicite. */
    private final int poolDistractorCount;
    private final List<String> answerPool;

    private MotChoixGenerator(ContentDescriptor descriptor, String question,
                              List<Entree> entrees, int poolDistractorCount) {
        this.descriptor = descriptor;
        this.question = question;
        this.entrees = List.copyOf(entrees);
        this.poolDistractorCount = poolDistractorCount;
        // Vivier des réponses distinctes (utilisé en mode vivier pour les distracteurs).
        this.answerPool = List.copyOf(new LinkedHashSet<>(this.entrees.stream().map(Entree::bonne).toList()));
    }

    /** Mode explicite : chaque entrée fournit ses distracteurs. */
    public static MotChoixGenerator explicit(ContentDescriptor descriptor, String question, List<Entree> entrees) {
        return new MotChoixGenerator(descriptor, question, entrees, 0);
    }

    /** Mode vivier : les distracteurs sont tirés parmi les autres bonnes réponses. */
    public static MotChoixGenerator fromPool(ContentDescriptor descriptor, String question,
                                             List<Entree> entrees, int distractorCount) {
        return new MotChoixGenerator(descriptor, question, entrees, distractorCount);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        List<Entree> available = entrees.stream()
                .filter(e -> !excludeKeys.contains(Item.keyOf(List.of(e.prompt()), question)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier épuisé
        }

        Entree entree = available.get(random.nextInt(available.size()));
        Answer expected = new Answer(entree.bonne());
        List<Answer> distractors = poolDistractorCount > 0
                ? buildFromPool(entree.bonne(), random)
                : entree.distracteurs().stream().map(Answer::new).toList();

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("prompt", entree.prompt());
        meta.put("bonne", entree.bonne());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return Optional.of(new Item(
                new ItemId(UUID.randomUUID().toString()), List.of(entree.prompt()), question, expected, distractors, meta));
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(entrees.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }

    /** Distracteurs tirés parmi les autres bonnes réponses du jeu (distincts, ≠ bonne). */
    private List<Answer> buildFromPool(String bonne, RandomSource random) {
        List<String> others = new ArrayList<>(answerPool);
        others.remove(bonne);

        Set<String> used = new LinkedHashSet<>();
        List<Answer> distractors = new ArrayList<>();
        int attempts = 0;
        while (distractors.size() < poolDistractorCount && attempts < 200 && !others.isEmpty()) {
            attempts++;
            String candidate = others.get(random.nextInt(others.size()));
            if (used.add(candidate)) {
                distractors.add(new Answer(candidate));
            }
        }
        // Repli déterministe si l'aléatoire n'a pas suffi.
        for (String candidate : others) {
            if (distractors.size() == poolDistractorCount) {
                break;
            }
            if (used.add(candidate)) {
                distractors.add(new Answer(candidate));
            }
        }
        return distractors;
    }
}
