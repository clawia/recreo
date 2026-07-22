package com.recreo.domain.mechanics.droitegraduee;

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
 * Droite graduée (cf. ADR-0018) : l'enfant PLACE un nombre sur une droite graduée (0 → N)
 * en cliquant la bonne graduation. 8e mécanique de la bibliothèque.
 *
 * <p>Réutilise le coup {@code SpinAndPick} (aucun nouveau coup, comme le puzzle a réutilisé
 * {@code SubmitArrangement}) : cliquer le tick K revient à CHOISIR la valeur K ; le
 * {@link SessionEngine} partagé compare K au nombre attendu. Elle bénéficie donc des jokers
 * (un tick faux consomme un cœur et devient non cliquable) sans logique dédiée. La mécanique
 * ne porte que son identité et sa vue (fragment {@code droitegraduee} + îlot JS).</p>
 *
 * <p>Ne dépend d'AUCUNE autre mécanique (règle ArchUnit 5) ni d'un générateur concret
 * (règle ArchUnit 4) : elle ne consomme que l'abstraction {@link ContentGenerator}.</p>
 */
public final class DroiteGradueeMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("droite-graduee");

    /** Fragment Thymeleaf qui présente cette mécanique (cf. ADR-0007). */
    public static final String VIEW = "droitegraduee";

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
