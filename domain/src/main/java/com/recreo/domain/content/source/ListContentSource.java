package com.recreo.domain.content.source;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.RandomSource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Tirage dans un vivier fini. Aucune entree/sortie : les items sont deja lus (ADR-0007).
 */
public final class ListContentSource implements ContentSource {

    private final ContentDescriptor descripteur;
    private final List<Item> items;

    public ListContentSource(ContentDescriptor descripteur, List<? extends Item> items) {
        this.descripteur = descripteur;
        this.items = List.copyOf(items);
    }

    @Override
    public ContentDescriptor descriptor() {
        return descripteur;
    }

    @Override
    public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
        List<Item> restants = items.stream().filter(item -> !dejaVus.contains(item.id())).toList();
        if (restants.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(restants.get(hasard.nextInt(restants.size())));
    }
}
