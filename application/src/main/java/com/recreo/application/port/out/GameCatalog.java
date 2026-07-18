package com.recreo.application.port.out;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;

import java.util.List;
import java.util.Map;

/**
 * Port de sortie : resout une entree de catalogue vers sa {@link GameConfig}
 * (mecanique + contenu), en appliquant d'eventuels reglages de session sur la
 * config de base, et fournit les mecaniques par identifiant.
 */
public interface GameCatalog {

    /** Config par defaut (aucun reglage) : comportement historique. */
    default GameConfig configFor(String entryId) {
        return configFor(entryId, Map.of());
    }

    /**
     * Config construite en appliquant {@code parameters} sur la config de base de
     * l'entree. Parametres vides = comportement par defaut.
     */
    GameConfig configFor(String entryId, Map<String, String> parameters);

    GameMechanic mechanicFor(MechanicId mechanicId);

    List<CatalogEntry> entries();

    /** Schema de reglages (bornes + valeurs par defaut) propre a l'entree. */
    GameSettingsView settingsFor(String entryId);
}
