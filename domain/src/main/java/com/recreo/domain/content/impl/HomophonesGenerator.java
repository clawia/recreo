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
 * Generateur "Le bon petit mot" (Francais CM1, homophones grammaticaux). Affiche
 * une phrase a trou ({@code ___}) et propose deux options (le bon homophone et son
 * piege). Meme mecanique bandit manchot. Jeu sans reglage.
 */
public final class HomophonesGenerator implements ContentGenerator {

    private static final String QUESTION = "Quel mot complète la phrase ?";

    /** Phrase a trou, bon mot, mauvais mot (homophone piege). Listes verifiees. */
    private record Phrase(String texte, String bon, String mauvais) {
    }

    private static final List<Phrase> PHRASES = List.of(
            new Phrase("Il ___ mangé une pomme.", "a", "à"),
            new Phrase("Je vais ___ l'école.", "à", "a"),
            new Phrase("Papa ___ maman font la cuisine.", "et", "est"),
            new Phrase("Le chat ___ noir.", "est", "et"),
            new Phrase("Les enfants ___ partis.", "sont", "son"),
            new Phrase("Il prend ___ cartable.", "son", "sont"),
            new Phrase("Ils ___ un chien.", "ont", "on"),
            new Phrase("___ joue dans la cour.", "On", "Ont"),
            new Phrase("___ vas-tu ?", "Où", "Ou"),
            new Phrase("Tu veux du thé ___ du café ?", "ou", "où"));

    private final ContentDescriptor descriptor;

    public HomophonesGenerator(ContentDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        // Cle d'un item = celle derivee du contenu (phrase affichee + consigne).
        List<Phrase> available = PHRASES.stream()
                .filter(p -> !excludeKeys.contains(Item.keyOf(List.of(p.texte()), QUESTION)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier epuise
        }

        Phrase phrase = available.get(random.nextInt(available.size()));

        List<String> reelSymbols = List.of(phrase.texte());
        Answer expected = new Answer(phrase.bon());
        List<Answer> distractors = List.of(new Answer(phrase.mauvais()));

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("phrase", phrase.texte());
        meta.put("bon", phrase.bon());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return Optional.of(new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, QUESTION, expected, distractors, meta));
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(PHRASES.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
