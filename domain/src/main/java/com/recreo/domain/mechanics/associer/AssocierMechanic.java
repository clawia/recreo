package com.recreo.domain.mechanics.associer;

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
 * Associer / classer (cf. ADR-0016) : l'enfant fait glisser des elements vers des
 * ZONES-CIBLES (categories a classer, ou mots a apparier). 5e mecanique de la
 * bibliotheque, calquee sur {@code GlisserDeposerMechanic} : elle delegue toute la
 * logique de session au {@link SessionEngine} partage (paquet parent
 * {@code domain.mechanic}) et ne porte que son identite et sa vue.
 *
 * <p>Le contenu reutilise le modele {@code Item} : {@code reelSymbols} = les elements a
 * associer ; {@code meta["targets"]} = les zones-cibles ; {@code expected} =
 * l'appariement attendu encode canoniquement (cf. {@code Item.encodeAssociations}). Le
 * coup {@code SubmitAssociations} soumet l'appariement choisi.</p>
 *
 * <p>Ne depend d'AUCUNE autre mecanique (regle ArchUnit 5) ni d'un generateur concret
 * (regle ArchUnit 4) : elle ne consomme que l'abstraction {@link ContentGenerator}.</p>
 */
public final class AssocierMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("associer");

    /** Fragment Thymeleaf qui presente cette mecanique (cf. ADR-0007). */
    public static final String VIEW = "associer";

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
