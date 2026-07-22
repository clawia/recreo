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
 * Générateur « Reconstitue le mot » à LISTE FINIE (mécanique {@code PuzzleMechanic}, cf.
 * ADR-0017). Chaque {@link Mot} devient un item : l'enfant remet les LETTRES dans l'ordre
 * pour écrire le mot, guidé par un INDICE (image/emoji ou courte définition). Même coup
 * d'ordonnancement que le glisser-déposer : la réponse attendue est l'arrangement des
 * lettres encodé canoniquement (cf. {@link Item#encodeArrangement(List)}).
 *
 * <p>Modèle {@code Item} : {@code reelSymbols} = les lettres TRIÉES (ordre stable, non
 * révélateur — ce n'est pas l'ordre du mot) → clé d'anti-doublon stable ; {@code question}
 * = la consigne ; {@code meta["indice"]} = l'indice affiché ; {@code expected} =
 * l'arrangement des lettres DANS L'ORDRE DU MOT. Aucun distracteur.</p>
 *
 * <p>Contenu externalisé (YAML) chargé au démarrage. Éviter les paires d'anagrammes dans un
 * même jeu (mêmes lettres triées → même clé).</p>
 */
public final class MotPuzzleGenerator implements ContentGenerator {

    /** Un mot à reconstituer et son indice (image/emoji ou courte définition). */
    public record Mot(String mot, String indice) {
    }

    private final ContentDescriptor descriptor;
    private final String consigne;
    private final List<Mot> mots;

    public MotPuzzleGenerator(ContentDescriptor descriptor, String consigne, List<Mot> mots) {
        this.descriptor = descriptor;
        this.consigne = consigne;
        this.mots = List.copyOf(mots);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        List<Mot> available = mots.stream()
                .filter(m -> !excludeKeys.contains(keyOf(m)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier épuisé
        }
        return Optional.of(buildItem(available.get(random.nextInt(available.size()))));
    }

    private Item buildItem(Mot mot) {
        List<String> lettres = lettres(mot.mot());

        // Réponse attendue : les lettres DANS L'ORDRE du mot.
        Answer expected = new Answer(Item.encodeArrangement(lettres));

        // Tuiles affichées : lettres TRIÉES (ordre stable, ne révèle pas le mot).
        List<String> reelSymbols = new ArrayList<>(lettres);
        Collections.sort(reelSymbols);

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put("indice", mot.indice());
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), reelSymbols, consigne, expected, List.of(), meta);
    }

    /** Découpe un mot en lettres (une par tuile). */
    private static List<String> lettres(String mot) {
        List<String> lettres = new ArrayList<>();
        for (int i = 0; i < mot.length(); i++) {
            lettres.add(String.valueOf(mot.charAt(i)));
        }
        return lettres;
    }

    /** Clé stable d'un mot : ses lettres triées + la consigne (indépendante de l'affichage). */
    private String keyOf(Mot mot) {
        List<String> sorted = lettres(mot.mot());
        Collections.sort(sorted);
        return Item.keyOf(sorted, consigne);
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(mots.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
