package com.recreo.application.port.in;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
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

    /**
     * ADR-0105 : les jeux d'un niveau regroupes par matiere, dans l'ordre de premiere apparition de
     * la matiere au catalogue (rien n'est ecrit en code, l'ordre d'affichage reste a arbitrer par
     * nadia-prof). Un niveau inconnu rend une liste vide, jamais une valeur sentinelle (ADR-0044).
     */
    List<GroupeDeJeux> matieresDuNiveau(String niveau);

    /**
     * ADR-0105 : le sous-ensemble du catalogue dont le niveau et la matiere correspondent, dans
     * l'ordre du catalogue ; vide si aucun jeu n'y repond.
     */
    List<GameDefinition> jeuxDeLaMatiere(String niveau, String matiere);

    GameSession demarrer(String jeuId);

    GameSession jouer(String jeuId, GameSession session, Move coup);
}
