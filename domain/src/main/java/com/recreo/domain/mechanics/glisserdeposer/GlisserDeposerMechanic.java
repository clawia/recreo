package com.recreo.domain.mechanics.glisserdeposer;

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
 * Glisser-deposer : presente des tuiles a RANGER dans le bon ordre (l'enfant fait
 * glisser les elements). La logique de session est PARTAGEE dans {@link SessionEngine}
 * (paquet parent {@code domain.mechanic}), auquel cette mecanique delegue entierement
 * (cf. ADR-0007) : elle ne porte que son identite et sa vue. 4e mecanique de la
 * bibliotheque, calquee sur {@code QuizAventureMechanic}.
 *
 * <p>Le contenu "arrangement" reutilise le modele {@code Item} : {@code reelSymbols} =
 * les elements a ranger (melanges), {@code question} = la consigne, {@code expected} =
 * l'arrangement attendu encode canoniquement (cf. {@code Item.encodeArrangement}). Le
 * coup {@code SubmitArrangement} soumet l'ordre choisi ; la mecanique et le moteur
 * restent AGNOSTIQUES du contenu.</p>
 *
 * <p>Ne depend d'AUCUNE autre mecanique ({@code domain.mechanics.banditmanchot},
 * {@code domain.mechanics.texteatrou}, {@code domain.mechanics.quizaventure}) — regle
 * ArchUnit 5 verte — ni d'un generateur de contenu concret — regle ArchUnit 4. Elle ne
 * consomme que l'abstraction {@link ContentGenerator}.</p>
 */
public final class GlisserDeposerMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("glisser-deposer");

    /** Fragment Thymeleaf qui presente cette mecanique (cf. ADR-0007). */
    public static final String VIEW = "glisserdeposer";

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
