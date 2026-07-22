package com.recreo.domain.mechanic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Etat PUR d'un plateau de la mecanique associer/classer apres un essai : ce qui est
 * VERROUILLE (bien associe, reste en place) et ce qui repart en RESERVE (mal associe).
 * Aide de calcul SANS IO ni framework (domaine pur), calquee sur {@link ArrangementBoard}
 * pour l'UX « ranger » (DEC-0008) : sur un essai FAUX, on GARDE en place les elements que
 * l'enfant a bien classes et on ne renvoie en reserve que les mal classes, SANS reveler la
 * bonne reponse (seuls les placements reussis par l'enfant sont montres).
 *
 * @param reserve      les elements a (re)placer (mal associes), dans l'ordre du contenu
 * @param lockedItems  les elements bien associes (verrouilles), dans l'ordre du contenu
 * @param lockedTargets la cible de chaque element verrouille, PARALLELE a {@code lockedItems}
 */
public record AssociationBoard(List<String> reserve, List<String> lockedItems, List<String> lockedTargets) {

    public AssociationBoard {
        reserve = List.copyOf(reserve);
        lockedItems = List.copyOf(lockedItems);
        lockedTargets = List.copyOf(lockedTargets);
    }

    /** Plateau initial : tous les elements en reserve, rien de verrouille. */
    public static AssociationBoard initial(List<String> items) {
        return new AssociationBoard(List.copyOf(items), List.of(), List.of());
    }

    /**
     * Plateau apres un essai : un element est VERROUILLE si l'enfant l'a place dans la cible
     * ATTENDUE ({@code submitted[item].equals(expected[item])}) ; sinon il repart en reserve.
     * L'ordre suit {@code allItems} (ordre canonique du contenu).
     */
    public static AssociationBoard afterAttempt(List<String> allItems,
                                                Map<String, String> submitted,
                                                Map<String, String> expected) {
        List<String> reserve = new ArrayList<>();
        List<String> lockedItems = new ArrayList<>();
        List<String> lockedTargets = new ArrayList<>();
        for (String item : allItems) {
            String want = expected.get(item);
            String got = submitted.get(item);
            if (want != null && want.equals(got)) {
                lockedItems.add(item);
                lockedTargets.add(want);
            } else {
                reserve.add(item);
            }
        }
        return new AssociationBoard(reserve, lockedItems, lockedTargets);
    }
}
