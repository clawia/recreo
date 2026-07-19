package com.recreo.domain.mechanics.texteatrou;

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
 * Texte a trous : presente une phrase a completer (une consigne contenant le
 * marqueur {@code ___}) et propose des mots a choisir. La logique de session est
 * PARTAGEE dans {@link SessionEngine} (paquet parent {@code domain.mechanic}),
 * auquel cette mecanique delegue entierement (cf. ADR-0007) : elle ne porte que son
 * identite et sa vue.
 *
 * <p>Le contenu "texte a trous" reutilise le modele {@code Item} : {@code question}
 * = la phrase avec le trou, {@code reelSymbols} = liste vide, {@code expected} = le
 * bon mot, {@code distractors} = les autres mots proposes. La mecanique et le moteur
 * restent AGNOSTIQUES du contenu.</p>
 *
 * <p>Ne depend d'AUCUNE autre mecanique ({@code domain.mechanics.banditmanchot}) —
 * regle ArchUnit 5 verte — ni d'un generateur de contenu concret — regle ArchUnit 4.
 * Elle ne consomme que l'abstraction {@link ContentGenerator}.</p>
 */
public final class TexteATrouMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("texte-a-trou");

    /** Fragment Thymeleaf qui presente cette mecanique (cf. ADR-0007). */
    public static final String VIEW = "texteatrou";

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
