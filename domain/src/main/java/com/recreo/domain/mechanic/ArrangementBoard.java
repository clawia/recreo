package com.recreo.domain.mechanic;

import java.util.ArrayList;
import java.util.List;

/**
 * Etat PUR d'un plateau de la mecanique glisser-deposer : ce qui est VERROUILLE en
 * place (bien positionne) et ce qui reste a (re)placer en reserve. Aide de calcul
 * SANS IO ni framework (domaine pur, cf. Clean Architecture) : elle ne connait ni la
 * vue ni le moteur, elle se contente de derouler la regle « une tuile en position i
 * est correcte si sa valeur == la i-eme valeur de l'ordre attendu ».
 *
 * <p>Elle sert l'UX « ranger » (DEC-0008) : sur un arrangement FAUX, on GARDE en place
 * les tuiles deja bien positionnees et on ne renvoie en reserve que les tuiles mal
 * placees, SANS jamais reveler la bonne reponse (seuls les placements que l'enfant a
 * lui-meme reussis sont montres).</p>
 *
 * @param slots   par position : la valeur VERROUILLEE (bien placee) ou {@code ""} si l'emplacement est vide
 * @param reserve les tuiles a (re)placer, dans l'ordre
 */
public record ArrangementBoard(List<String> slots, List<String> reserve) {

    public ArrangementBoard {
        slots = List.copyOf(slots);
        reserve = List.copyOf(reserve);
    }

    /** Plateau initial : tous les emplacements vides, toutes les tuiles en reserve (dans l'ordre donne). */
    public static ArrangementBoard initial(List<String> tiles) {
        List<String> slots = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            slots.add("");
        }
        return new ArrangementBoard(slots, List.copyOf(tiles));
    }

    /**
     * Plateau apres un essai : l'emplacement {@code i} est VERROUILLE (= {@code expected[i]}) si
     * {@code submitted[i] == expected[i]}, sinon il reste vide ; la reserve regroupe les tuiles
     * SOUMISES aux positions non verrouillees (les mal placees), dans l'ordre. La comparaison se
     * fait position par position sur {@code min(submitted.size, expected.size)} ; robuste si les
     * tailles different (les positions au-dela restent vides / partent en reserve).
     */
    public static ArrangementBoard afterAttempt(List<String> submitted, List<String> expected) {
        int n = Math.max(submitted.size(), expected.size());
        int common = Math.min(submitted.size(), expected.size());
        List<String> slots = new ArrayList<>();
        List<String> reserve = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean locked = i < common && submitted.get(i).equals(expected.get(i));
            if (locked) {
                slots.add(expected.get(i));
            } else {
                // Emplacement vide (position pas encore reussie). Il n'existe un slot que
                // pour les positions attendues.
                if (i < expected.size()) {
                    slots.add("");
                }
                // La tuile soumise a cette position (si elle existe) repart en reserve.
                if (i < submitted.size()) {
                    reserve.add(submitted.get(i));
                }
            }
        }
        return new ArrangementBoard(slots, reserve);
    }
}
