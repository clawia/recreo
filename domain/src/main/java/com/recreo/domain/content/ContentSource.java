package com.recreo.domain.content;

import java.util.Optional;
import java.util.Set;

/**
 * ADR-0005 : c'est ce contrat, et non un moteur de regles, qui decide quel item est presente.
 */
public interface ContentSource {

    ContentDescriptor descriptor();

    Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus);
}
