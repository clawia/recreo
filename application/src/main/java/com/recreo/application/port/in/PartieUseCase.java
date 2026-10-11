package com.recreo.application.port.in;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;

import java.util.List;
import java.util.Optional;

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
     * ADR-0105 : les jeux d'un niveau regroupes par matiere, dans l'ordre des lignes de la donnee de
     * matiere (ADR-0106, APM-4). Un niveau inconnu rend une liste vide, jamais une valeur sentinelle
     * (ADR-0044).
     */
    List<GroupeDeJeux> matieresDuNiveau(String niveau);

    /**
     * ADR-0106 : le groupe de la matiere que designe ce segment d'adresse, parmi les jeux de ce
     * niveau. Vide, jamais une exception, quand aucune ligne de matiere ne porte ce segment ou que la
     * matiere n'a aucun jeu a ce niveau : l'adaptateur ne traduit jamais un segment en matiere
     * (ADR-0044), c'est ce port qui le resout.
     */
    Optional<GroupeDeJeux> groupeDeLaMatiere(String niveau, String segment);

    GameSession demarrer(String jeuId);

    GameSession jouer(String jeuId, GameSession session, Move coup);
}
