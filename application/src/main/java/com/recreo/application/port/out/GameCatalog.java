package com.recreo.application.port.out;

import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;

import java.util.List;

/**
 * Port de sortie : resout une entree de catalogue vers sa {@link GameConfig}
 * (mecanique + contenu) et fournit les mecaniques par identifiant.
 */
public interface GameCatalog {
    GameConfig configFor(String entryId);

    GameMechanic mechanicFor(MechanicId mechanicId);

    List<CatalogEntry> entries();
}
