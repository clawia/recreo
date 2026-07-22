package com.recreo.domain.mechanics.puzzle;

import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.SessionEngine;

/**
 * Puzzle / assemblage (cf. ADR-0017) : l'enfant REMET DES ELEMENTS DANS L'ORDRE pour
 * reconstituer un tout — la 1re déclinaison est « reconstituer un mot » (remettre les
 * lettres dans l'ordre à partir d'un indice). 7e mécanique de la bibliothèque.
 *
 * <p>Réutilise le coup d'ORDONNANCEMENT {@code SubmitArrangement} (comme le glisser-déposer,
 * ADR-0013) : la réponse attendue est l'arrangement encodé canoniquement
 * ({@code Item.encodeArrangement}). Elle délègue toute la logique de session au
 * {@link SessionEngine} partagé et ne porte que son identité et sa vue (fragment propre
 * {@code puzzle}, avec son îlot JS de glisser-déposer et l'indice affiché).</p>
 *
 * <p>Ne dépend d'AUCUNE autre mécanique (règle ArchUnit 5) ni d'un générateur concret
 * (règle ArchUnit 4) : elle ne consomme que l'abstraction {@link ContentGenerator}.</p>
 */
public final class PuzzleMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("puzzle");

    /** Fragment Thymeleaf qui présente cette mécanique (cf. ADR-0007). */
    public static final String VIEW = "puzzle";

    private final SessionEngine engine = new SessionEngine(ID);

    @Override
    public MechanicId id() {
        return ID;
    }

    @Override
    public String view() {
        return VIEW;
    }

    @Override
    public GameSession start(GameConfig config, RandomSource random) {
        return engine.start(config, random);
    }

    @Override
    public MoveResult apply(GameSession session, Move move) {
        return engine.apply(session, move);
    }
}
