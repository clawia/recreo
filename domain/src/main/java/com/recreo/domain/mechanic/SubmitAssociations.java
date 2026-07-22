package com.recreo.domain.mechanic;

import java.util.Map;

/**
 * Coup « associer / classer » (cf. ADR-0016) : l'enfant soumet un ensemble
 * d'ASSOCIATIONS {@code element -> cible} (chaque item rangé dans sa catégorie, ou
 * chaque carte appariée à sa paire), et non une réponse unique ni un ordre.
 *
 * <p>Le moteur ({@link SessionEngine}) reste AGNOSTIQUE : il compare l'encodage
 * canonique des associations soumises (cf.
 * {@link com.recreo.domain.content.Item#encodeAssociations(Map)}) à la réponse
 * attendue de l'item — il ne connaît ni catégories ni paires, il ne lit pas la meta.</p>
 *
 * <p>Coup PARTAGE par les deux mécaniques « associer/classer » (glisser vers des zones)
 * et « memory » (retourner deux cartes) : dans les deux cas la réponse est un
 * appariement {@code clé -> valeur}.</p>
 */
public record SubmitAssociations(Map<String, String> associations) implements Move {
    public SubmitAssociations {
        if (associations == null || associations.isEmpty()) {
            throw new IllegalArgumentException("associations must not be empty");
        }
        associations = Map.copyOf(associations);
    }
}
