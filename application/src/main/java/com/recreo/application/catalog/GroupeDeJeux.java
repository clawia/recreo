package com.recreo.application.catalog;

import java.util.List;

/**
 * ADR-0105 : les jeux d'un niveau qui partagent une matiere, tels que l'accueil les montre. La
 * matiere est la chaine que les paquets declarent dans leur descripteur (ADR-0006, ADR-0049), rendue
 * sans transformation : un libelle court, un pictogramme ou un segment d'URL s'ajouteront ici par la
 * correspondance de matiere, jamais par un calcul sur cette chaine.
 *
 * <p>Les jeux sont des {@link GameDefinition} entiers : l'adaptateur de rendu n'en lit jamais le
 * descripteur (ADR-0044), il rend ce que ce groupe lui donne.
 */
public record GroupeDeJeux(String matiere, List<GameDefinition> jeux) {

    public GroupeDeJeux {
        jeux = List.copyOf(jeux);
    }
}
