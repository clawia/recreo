package com.recreo.domain.mechanics.premiere;

/**
 * ADR-0003 : moitie du contre-exemple d'ADR-0004 (7). Porte un comportement reel, pour que le
 * couplage que sa voisine cree soit celui d'une mecanique qui fait quelque chose, et non celui de
 * deux coquilles vides - la vacuite de type 2 qui a laisse cette regle mentir 25 sprints.
 */
public final class MecaniquePremiereContreExemple {

    public int mancheSuivante(int manche) {
        return manche + 1;
    }
}
