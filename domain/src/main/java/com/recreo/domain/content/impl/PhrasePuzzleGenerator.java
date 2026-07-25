package com.recreo.domain.content.impl;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Générateur « Reconstitue la phrase » à LISTE FINIE (mécanique {@code PuzzleMechanic}, cf.
 * ADR-0017). Déclinaison de {@link MotPuzzleGenerator} : l'unité à remettre dans l'ordre n'est
 * plus la LETTRE mais le MOT. Chaque {@link Phrase} devient un item : l'enfant remet les MOTS
 * dans l'ordre pour reconstituer la phrase, éventuellement guidé par un INDICE (image/emoji ou
 * thème). Même coup d'ordonnancement que le glisser-déposer / le puzzle : la réponse attendue
 * est l'arrangement des mots encodé canoniquement (cf. {@link Item#encodeArrangement(List)}).
 *
 * <p>Modèle {@code Item} : {@code reelSymbols} = les mots TRIÉS (ordre stable, non révélateur —
 * ce n'est pas l'ordre de la phrase) → clé d'anti-doublon stable ; {@code question} = la
 * consigne ; {@code meta["indice"]} = l'indice affiché (facultatif) ; {@code expected} =
 * l'arrangement des mots DANS L'ORDRE de la phrase. Aucun distracteur.</p>
 *
 * <p>Les mots en double (« le chat et le chien » → deux tuiles « le ») sont gérés naturellement :
 * {@code ArrangementBoard} compare position par position. Éviter la ponctuation contenant le
 * séparateur « , » dans une phrase (cf. {@link Item#encodeArrangement(List)}).</p>
 */
public final class PhrasePuzzleGenerator implements ContentGenerator {

    /** Une phrase à reconstituer et son indice facultatif (image/emoji ou thème). */
    public record Phrase(String phrase, String indice) {
    }

    private final ContentDescriptor descriptor;
    private final String consigne;
    private final List<Phrase> phrases;

    public PhrasePuzzleGenerator(ContentDescriptor descriptor, String consigne, List<Phrase> phrases) {
        this.descriptor = descriptor;
        this.consigne = consigne;
        this.phrases = List.copyOf(phrases);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        List<Phrase> available = phrases.stream()
                .filter(p -> !excludeKeys.contains(keyOf(p)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier épuisé
        }
        return Optional.of(buildItem(available.get(random.nextInt(available.size()))));
    }

    private Item buildItem(Phrase phrase) {
        List<String> mots = mots(phrase.phrase());

        // Réponse attendue : les mots DANS L'ORDRE de la phrase.
        Answer expected = new Answer(Item.encodeArrangement(mots));

        // Tuiles affichées : mots TRIÉS (ordre stable, ne révèle pas la phrase).
        List<String> reelSymbols = new ArrayList<>(mots);
        Collections.sort(reelSymbols);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("indice", phrase.indice() == null ? "" : phrase.indice());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, consigne, expected, List.of(), meta);
    }

    /** Découpe une phrase en mots (un par tuile), sur les espaces. */
    private static List<String> mots(String phrase) {
        return new ArrayList<>(List.of(phrase.trim().split("\\s+")));
    }

    /** Clé stable d'une phrase : ses mots triés + la consigne (indépendante de l'affichage). */
    private String keyOf(Phrase phrase) {
        List<String> sorted = mots(phrase.phrase());
        Collections.sort(sorted);
        return Item.keyOf(sorted, consigne);
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(phrases.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
