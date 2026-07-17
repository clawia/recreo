package com.recreo.application.port.in;

/** Use case : demarrer une nouvelle session de jeu. */
public interface StartSessionUseCase {
    SessionView start(StartSessionCommand command);
}
