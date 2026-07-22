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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "associer / classer" a LISTE FINIE (mecanique {@code AssocierMechanic},
 * cf. ADR-0016). Chaque {@link Puzzle} devient UN item : l'enfant range chaque
 * {@code item} dans sa {@code cible} (categorie a classer, ou mot a apparier). Gere
 * l'APPARIEMENT (cibles toutes distinctes) ET la CATEGORISATION (cibles repetees) sans
 * distinction, la difference n'etant que dans le contenu.
 *
 * <p>Modele {@code Item} : {@code reelSymbols} = les elements a ranger (dans l'ordre du
 * contenu, pour une cle stable) ; {@code question} = la consigne ; {@code meta["targets"]}
 * = les zones-cibles DISTINCTES melangees, jointes par « | » ; {@code expected} =
 * l'appariement attendu {@code item -> cible} encode canoniquement (cf.
 * {@link Item#encodeAssociations(Map)}). Aucun distracteur (comme glisser-deposer).</p>
 *
 * <p>La bonne reponse (la cible de chaque item) n'est JAMAIS exposee cote item hormis via
 * {@code expected} (consomme par le moteur) : la vue ne recoit que les elements et les
 * cibles possibles, jamais l'association correcte.</p>
 */
public final class AssociationGenerator implements ContentGenerator {

    /** Une association attendue : un element et sa cible (categorie ou mot apparie). */
    public record Association(String item, String cible) {
    }

    /** Un puzzle = un ensemble d'associations a resoudre en une fois (un item de session). */
    public record Puzzle(List<Association> associations) {
        public Puzzle {
            associations = List.copyOf(associations);
        }
    }

    /** Cle de meta portant les zones-cibles possibles (cf. {@code WebViewMapper}). */
    public static final String META_TARGETS = "targets";

    /** Separateur des listes de meta (cf. {@code WebViewMapper}). */
    private static final String META_SEPARATOR = "|";

    private final ContentDescriptor descriptor;
    private final String consigne;
    private final List<Puzzle> puzzles;

    public AssociationGenerator(ContentDescriptor descriptor, String consigne, List<Puzzle> puzzles) {
        this.descriptor = descriptor;
        this.consigne = consigne;
        this.puzzles = List.copyOf(puzzles);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        List<Puzzle> available = puzzles.stream()
                .filter(p -> !excludeKeys.contains(keyOf(p)))
                .toList();
        if (available.isEmpty()) {
            return Optional.empty(); // vivier epuise
        }
        Puzzle puzzle = available.get(random.nextInt(available.size()));
        return Optional.of(buildItem(puzzle, random));
    }

    private Item buildItem(Puzzle puzzle, RandomSource random) {
        List<String> items = items(puzzle);

        // Cibles DISTINCTES (dans l'ordre d'apparition), puis melangees pour l'affichage.
        List<String> targets = new ArrayList<>(new LinkedHashSet<>(
                puzzle.associations().stream().map(Association::cible).toList()));
        shuffle(targets, random);

        // Appariement attendu item -> cible.
        Map<String, String> expectedPairs = new LinkedHashMap<>();
        for (Association a : puzzle.associations()) {
            expectedPairs.put(a.item(), a.cible());
        }
        Answer expected = new Answer(Item.encodeAssociations(expectedPairs));

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put(META_TARGETS, String.join(META_SEPARATOR, targets));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), items, consigne, expected, List.of(), meta);
    }

    /** Elements a ranger, dans l'ordre du contenu (cle stable pour l'anti-doublon). */
    private List<String> items(Puzzle puzzle) {
        return puzzle.associations().stream().map(Association::item).toList();
    }

    /** Cle deterministe d'un puzzle (elements + consigne), stable d'un tirage a l'autre. */
    private String keyOf(Puzzle puzzle) {
        return Item.keyOf(items(puzzle), consigne);
    }

    private void shuffle(List<String> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
    }

    @Override
    public OptionalInt distinctPoolSize() {
        return OptionalInt.of(puzzles.size());
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
