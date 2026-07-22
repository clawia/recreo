package com.recreo.domain.mechanics.memory;

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
 * Memory (cf. ADR-0016) : une grille de cartes face cachee ; l'enfant en retourne deux
 * et garde les PAIRES trouvees. Un plateau complet = un item reussi. 6e mecanique de la
 * bibliotheque, calquee sur {@code GlisserDeposerMechanic} : elle delegue toute la
 * logique de session au {@link SessionEngine} partage et ne porte que son identite et
 * sa vue.
 *
 * <p>Le contenu reutilise le modele {@code Item} : {@code reelSymbols} = les faces des
 * cartes (les deux cotes de chaque paire) ; {@code meta["groups"]} = l'identifiant de
 * paire et le cote de chaque carte, PARALLELE a {@code reelSymbols} ; {@code expected} =
 * l'appariement attendu encode canoniquement. Le coup {@code SubmitAssociations} soumet
 * les paires appariees a la complétion du plateau.</p>
 *
 * <p>Ne depend d'AUCUNE autre mecanique (regle ArchUnit 5) ni d'un generateur concret
 * (regle ArchUnit 4) : elle ne consomme que l'abstraction {@link ContentGenerator}.</p>
 */
public final class MemoryMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("memory");

    /** Fragment Thymeleaf qui presente cette mecanique (cf. ADR-0007). */
    public static final String VIEW = "memory";

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
