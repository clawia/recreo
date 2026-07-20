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
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * "Le bon son" (Français CP, décodage / conscience phonologique). Un mot s'affiche
 * sur le rouleau du bandit manchot ; l'enfant choisit le SON qu'il entend dans ce mot
 * parmi trois propositions. Même mécanique bandit manchot que les autres jeux à mot
 * unique (cf. {@link AnglaisLexiqueGenerator}).
 *
 * <p>Liste FINIE de mots vérifiés. Choix pédagogique clé : les distracteurs sont
 * définis <b>mot par mot</b> (sons volontairement ABSENTS du mot), et non tirés d'un
 * vivier global — ainsi un distracteur ne peut jamais être un son réellement présent
 * dans le mot. En mode session, un mot déjà réussi est exclu du tirage suivant via
 * {@link #next(RandomSource, Set)} (cf. ADR-0006).</p>
 */
public final class LeBonSonGenerator implements ContentGenerator {

    private static final String QUESTION = "Quel son entends-tu ?";

    /** Un mot vérifié : le mot affiché, le bon son, deux sons absents du mot. */
    private record Mot(String mot, String son, List<String> distracteurs) {
        private Mot {
            distracteurs = List.copyOf(distracteurs);
        }
    }

    /**
     * Liste vérifiée (ordre d'insertion déterministe). Chaque mot contient EXACTEMENT
     * le son attendu ; les deux distracteurs sont des sons absents du mot.
     * Sons ciblés : nasales [an]/[on]/[in], + [ou]/[oi]/[ch].
     */
    private static final List<Mot> MOTS = List.of(
            new Mot("MAMAN", "[an]", List.of("[on]", "[in]")),
            new Mot("DENT", "[an]", List.of("[oi]", "[ou]")),
            new Mot("PONT", "[on]", List.of("[an]", "[ou]")),
            new Mot("BALLON", "[on]", List.of("[in]", "[oi]")),
            new Mot("LAPIN", "[in]", List.of("[an]", "[on]")),
            new Mot("JARDIN", "[in]", List.of("[ou]", "[oi]")),
            new Mot("LOUP", "[ou]", List.of("[on]", "[oi]")),
            new Mot("GENOU", "[ou]", List.of("[oi]", "[an]")),
            new Mot("ROI", "[oi]", List.of("[ou]", "[on]")),
            new Mot("BOIS", "[oi]", List.of("[ou]", "[in]")),
            new Mot("CHAT", "[ch]", List.of("[oi]", "[ou]")),
            new Mot("VACHE", "[ch]", List.of("[an]", "[on]")));

    private final ContentDescriptor descriptor;

    public LeBonSonGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        List<Mot> available = MOTS.stream()
                .filter(m -> !excludeKeys.contains(Item.keyOf(List.of(m.mot()), QUESTION)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier épuisé
        }

        Mot chosen = available.get(random.nextInt(available.size()));
        List<String> reelSymbols = List.of(chosen.mot());
        Answer expected = new Answer(chosen.son());
        List<Answer> distractors = chosen.distracteurs().stream().map(Answer::new).toList();

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("mot", chosen.mot());
        meta.put("son", chosen.son());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return Optional.of(new Item(
                new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta));
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(MOTS.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
