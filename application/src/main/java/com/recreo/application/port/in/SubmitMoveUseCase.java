package com.recreo.application.port.in;

/** Use case : appliquer un coup sur une session existante. */
public interface SubmitMoveUseCase {
    MoveOutcomeView submit(SubmitMoveCommand command);
}
