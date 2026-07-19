package com.recreo.domain.mechanics.banditmanchot;

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
 * Bandit manchot : presente l'item sous forme de machine a sous (rouleaux
 * d'affichage + reponses a cliquer). La logique de session (score, comptage,
 * anti-doublon des reussis, fin de session) n'est PAS ici : elle est PARTAGEE dans
 * {@link SessionEngine} (paquet parent {@code domain.mechanic}), auquel cette
 * mecanique delegue entierement (cf. ADR-0007). Cette classe ne porte donc que
 * l'identite de la mecanique et sa vue.
 *
 * <p>Ne reference JAMAIS {@code CalculMentalGenerator} ni le package
 * {@code content.impl} (regle ArchUnit 4), ni une autre mecanique
 * ({@code domain.mechanics.texteatrou}) — regle ArchUnit 5. Elle ne consomme que
 * l'abstraction {@link ContentGenerator}.</p>
 */
public final class BanditManchotMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("bandit-manchot");

    /** Fragment Thymeleaf qui presente cette mecanique (cf. ADR-0007). */
    public static final String VIEW = "banditmanchot";

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
