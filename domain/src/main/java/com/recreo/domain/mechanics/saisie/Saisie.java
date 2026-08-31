package com.recreo.domain.mechanics.saisie;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;

/**
 * Une manche presente un enonce et un endroit ou ecrire ; l'enfant ecrit sa reponse, le verdict et
 * le score cumule sont rendus immediatement, et la manche suivante est deja tiree (ADR-0008 : tout
 * le comportement vit ici, en Java pur, cote serveur).
 *
 * <p>Cette mecanique ne connait aucune matiere et aucune regle d'orthographe : ce qu'un item
 * accepte vient entierement du paquet (SAI-6, SAI-7), et ce qu'elle possede en propre est un
 * repliage qui ne consulte aucune liste de mots (ADR-0058 section 3).</p>
 */
public final class Saisie implements GameMechanic {

    public static final MechanicId ID = new MechanicId("saisie");

    @Override
    public MechanicId id() {
        return ID;
    }

    @Override
    public GameSession start(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
        return SaisieSession.demarrer(contenu, hasard, nombreDeManches);
    }

    @Override
    public GameSession apply(GameSession session, Move coup) {
        if (!(session instanceof SaisieSession saisieSession)) {
            throw new IllegalArgumentException("session inconnue de la mecanique de saisie");
        }
        if (coup instanceof SaisieMove saisieMove) {
            return saisieSession.jouer(saisieMove);
        }
        if (coup instanceof ContinuerMove) {
            return saisieSession.poursuivre();
        }
        throw new IllegalArgumentException("coup inconnu de la mecanique de saisie");
    }

    @Override
    public int longueurMinimale() {
        return SaisieSession.LONGUEUR_MINIMALE_POUR_AIDE;
    }
}
