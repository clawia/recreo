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
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

/**
 * Generateur "Memory" a LISTE FINIE (mecanique {@code MemoryMechanic}, cf. ADR-0016).
 * Le fichier de contenu decrit un ensemble de {@link Paire} {@code gauche <-> droite} ;
 * le generateur produit UN item = UN plateau (toutes les paires). Un plateau complete =
 * un item reussi ({@code distinctPoolSize = 1} ; « Rejouer » redistribue).
 *
 * <p>Modele {@code Item} : {@code reelSymbols} = les faces des cartes, dans l'ordre
 * {@code g0, d0, g1, d1, ...} (cle stable) ; {@code meta["groups"]} = l'identifiant de
 * paire + le cote de CHAQUE carte, PARALLELE a {@code reelSymbols} : {@code "0g","0d",
 * "1g","1d", ...}, joint par « | » — c'est ce qui permet a l'ilot JS d'apparier deux
 * cartes (meme index) tout en preservant l'orientation gauche->droite du controle.
 * {@code expected} = l'appariement attendu {@code gauche -> droite} encode canoniquement
 * (cf. {@link Item#encodeAssociations(Map)}).</p>
 *
 * <p>Les positions des cartes sont melangees cote CLIENT (ilot JS) : le generateur les
 * emet dans un ordre fixe pour garder une cle deterministe.</p>
 */
public final class MemoryGenerator implements ContentGenerator {

    /** Une paire du memory : les deux faces a apparier. */
    public record Paire(String gauche, String droite) {
    }

    /** Cle de meta portant les groupes de paires (cf. {@code WebViewMapper}). */
    public static final String META_GROUPS = "groups";

    /** Separateur des listes de meta (cf. {@code WebViewMapper}). */
    private static final String META_SEPARATOR = "|";

    private final ContentDescriptor descriptor;
    private final String consigne;
    private final List<Paire> paires;

    public MemoryGenerator(ContentDescriptor descriptor, String consigne, List<Paire> paires) {
        this.descriptor = descriptor;
        this.consigne = consigne;
        this.paires = List.copyOf(paires);
    }

    @Override
    public Optional<Item> next(RandomSource random, Set<String> excludeKeys) {
        Item board = buildBoard();
        if (excludeKeys.contains(board.key())) {
            return Optional.empty(); // plateau deja reussi : vivier epuise
        }
        return Optional.of(board);
    }

    private Item buildBoard() {
        List<String> cards = new ArrayList<>();
        List<String> groups = new ArrayList<>();
        Map<String, String> expectedPairs = new LinkedHashMap<>();
        for (int i = 0; i < paires.size(); i++) {
            Paire p = paires.get(i);
            cards.add(p.gauche());
            groups.add(i + "g");
            cards.add(p.droite());
            groups.add(i + "d");
            expectedPairs.put(p.gauche(), p.droite());
        }
        Answer expected = new Answer(Item.encodeAssociations(expectedPairs));

        Map<String, String> meta = new LinkedHashMap<>();
        meta.put(META_GROUPS, String.join(META_SEPARATOR, groups));
        meta.put("matiere", descriptor.matiere());
        meta.put("niveau", descriptor.niveau());

        return new Item(new ItemId(UUID.randomUUID().toString()), cards, consigne, expected, List.of(), meta);
    }

    @Override
    public OptionalInt distinctPoolSize() {
        // Un seul plateau par jeu (toutes les paires) : borne la cible de session a 1.
        return OptionalInt.of(1);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descriptor;
    }
}
