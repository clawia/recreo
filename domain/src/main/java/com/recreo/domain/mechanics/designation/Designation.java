package com.recreo.domain.mechanics.designation;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;

/**
 * ADR-0080 : une manche montre une image et les zones designables que le contenu declare ; l'enfant
 * en designe une, le verdict et le score cumule sont rendus immediatement, et la manche suivante est
 * deja tiree (ADR-0008 : tout le comportement vit ici, en Java pur, cote serveur).
 *
 * <p>Cette mecanique ne connait aucune matiere et aucune geometrie : ce que represente une zone, son
 * dessin, son decoupage viennent entierement du paquet. Elle ne melange jamais l'ordre des zones -
 * la place d'une zone est une donnee du contenu, pas un tirage (ADR-0080, decision 3).</p>
 */
public final class Designation implements GameMechanic {

    public static final MechanicId ID = new MechanicId("designation");

    @Override
    public MechanicId id() {
        return ID;
    }

    @Override
    public GameSession start(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
        return DesignationSession.demarrer(contenu, hasard, nombreDeManches);
    }

    @Override
    public GameSession apply(GameSession session, Move coup) {
        if (!(session instanceof DesignationSession designationSession)) {
            throw new IllegalArgumentException("session inconnue de la mecanique de designation");
        }
        if (!(coup instanceof DesignationMove designationMove)) {
            throw new IllegalArgumentException("coup inconnu de la mecanique de designation");
        }
        return designationSession.jouer(designationMove);
    }

    @Override
    public int longueurMinimale() {
        return DesignationSession.LONGUEUR_MINIMALE_POUR_AIDE;
    }
}
