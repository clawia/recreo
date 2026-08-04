package com.recreo.domain.mechanics.seconde;

import com.recreo.domain.mechanics.premiere.MecaniquePremiereContreExemple;

/**
 * ADR-0003 : autre moitie du contre-exemple d'ADR-0004 (7). Une mecanique qui reutilise le
 * comportement de sa voisine. Existe pour etre refusee par
 * DecouplageMecaniqueContenuTest.lesMecaniquesNeSeConnaissentPas.
 */
public final class MecaniqueSecondeContreExemple {

    private final MecaniquePremiereContreExemple voisine;

    public MecaniqueSecondeContreExemple(MecaniquePremiereContreExemple voisine) {
        this.voisine = voisine;
    }

    public int mancheSuivante(int manche) {
        return voisine.mancheSuivante(manche);
    }
}
