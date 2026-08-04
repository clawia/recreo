package com.recreo.domain.mechanics;

import com.recreo.domain.mechanic.GameMechanic;

/**
 * ADR-0003 : viole ADR-0004 (6). Une implementation de GameMechanic posee directement dans
 * com.recreo.domain.mechanics, donc dans aucun paquet de mecanique : la regle d'isolation entre
 * mecaniques (7) ne comparerait plus rien, puisqu'elle decoupe par sous-paquet. Existe pour etre
 * refuse par DecouplageMecaniqueContenuTest.uneMecaniqueVitDansSonProprePaquet.
 */
public abstract class MecaniqueHorsPaquetContreExemple implements GameMechanic {
}
