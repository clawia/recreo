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

    /** ADR-0044 : les valeurs de niveau distinctes du catalogue, dans l'ordre du catalogue. */
    List<String> niveauxDuCatalogue();

    /** ADR-0044 : le sous-ensemble du catalogue dont le niveau correspond. */
    List<GameDefinition> jeuxDuNiveau(String niveau);

    GameSession demarrer(String jeuId);

    GameSession jouer(String jeuId, GameSession session, Move coup);
}
