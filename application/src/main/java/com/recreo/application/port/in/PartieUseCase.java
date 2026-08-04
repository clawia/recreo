package com.recreo.application.port.in;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;

import java.util.List;

/**
 * Port entrant : ce qu'un adaptateur peut demander pour faire jouer une partie, quelle que soit
 * la mecanique ou le contenu du jeu choisi.
 */
public interface PartieUseCase {

    List<GameDefinition> jeuxDisponibles();

    GameSession demarrer(String jeuId);

    GameSession jouer(String jeuId, GameSession session, Move coup);
}
