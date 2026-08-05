package com.recreo.domain.mechanic;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.RandomSource;

/**
 * ADR-0004 : une implementation vit dans com.recreo.domain.mechanics.&lt;mecanique&gt;, avec tout
 * son comportement. ADR-0008 : c'est ici, cote serveur, que le verdict est calcule.
 * ADR-0012 : nombreDeManches est un parametre de partie, porte par l'entree de catalogue, jamais
 * par le contenu ni par une constante de mecanique.
 */
public interface GameMechanic {

    MechanicId id();

    GameSession start(ContentSource contenu, RandomSource hasard, int nombreDeManches);

    GameSession apply(GameSession session, Move coup);
}
