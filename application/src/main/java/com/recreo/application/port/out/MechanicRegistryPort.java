package com.recreo.application.port.out;

import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;

/**
 * Port sortant : resout une mecanique concrete a partir de son identifiant. C'est bootstrap qui
 * connait toutes les mecaniques (ADR-0004 : l'application ne les nomme pas elle-meme).
 */
public interface MechanicRegistryPort {

    GameMechanic mecaniquePour(MechanicId id);
}
