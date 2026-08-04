package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;

/**
 * Une manche presente une question et des propositions melangees ; l'enfant en choisit une, le
 * verdict et le score cumule sont rendus immediatement, et la manche suivante est deja tiree
 * (ADR-0008 : tout le comportement vit ici, en Java pur, cote serveur).
 */
public final class BanditManchot implements GameMechanic {

    public static final MechanicId ID = new MechanicId("bandit-manchot");

    @Override
    public MechanicId id() {
        return ID;
    }

    @Override
    public GameSession start(ContentSource contenu, RandomSource hasard) {
        return BanditManchotSession.demarrer(contenu, hasard);
    }

    @Override
    public GameSession apply(GameSession session, Move coup) {
        if (!(session instanceof BanditManchotSession banditSession)) {
            throw new IllegalArgumentException("session inconnue de la mecanique bandit manchot");
        }
        if (!(coup instanceof BanditManchotMove banditMove)) {
            throw new IllegalArgumentException("coup inconnu de la mecanique bandit manchot");
        }
        return banditSession.jouer(banditMove);
    }
}
