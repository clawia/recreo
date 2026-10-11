package com.recreo.application.catalog;

import java.util.List;

/**
 * ADR-0105 : les jeux d'un niveau qui partagent une matiere, tels que l'accueil les montre.
 * ADR-0106 : la matiere est la ligne entiere de la donnee de matiere (libelle court, pictogramme,
 * teintes, segment d'adresse), jamais sa seule chaine : l'adaptateur de rendu lit ce que ce groupe lui
 * donne et ne calcule ni libelle ni segment depuis le nom de la matiere.
 *
 * <p>Les jeux sont des {@link GameDefinition} entiers : l'adaptateur de rendu n'en lit jamais le
 * descripteur (ADR-0044), il rend ce que ce groupe lui donne.
 */
public record GroupeDeJeux(Matiere matiere, List<GameDefinition> jeux) {

    public GroupeDeJeux {
        jeux = List.copyOf(jeux);
    }
}
