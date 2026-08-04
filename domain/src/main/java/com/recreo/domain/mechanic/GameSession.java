package com.recreo.domain.mechanic;

/**
 * ADR-0004 : une interface, pas un record. L'etat de session appartient a la mecanique, qui en
 * definit la forme dans son propre paquet.
 */
public interface GameSession {

    SessionStatus status();

    Score score();
}
