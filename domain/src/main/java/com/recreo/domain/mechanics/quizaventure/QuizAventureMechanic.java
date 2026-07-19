package com.recreo.domain.mechanics.quizaventure;

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
 * Quiz-aventure : presente une QUESTION en grand (carte-question) et propose
 * plusieurs reponses a cliquer. La logique de session est PARTAGEE dans
 * {@link SessionEngine} (paquet parent {@code domain.mechanic}), auquel cette
 * mecanique delegue entierement (cf. ADR-0007) : elle ne porte que son identite et
 * sa vue. 3e mecanique de la bibliotheque, calquee sur {@code TexteATrouMechanic}.
 *
 * <p>Le contenu "quiz" reutilise le modele {@code Item} : {@code question} = la
 * question posee, {@code reelSymbols} = liste vide (pas de rouleaux), {@code expected}
 * = la bonne reponse, {@code distractors} = les mauvaises reponses. La mecanique et le
 * moteur restent AGNOSTIQUES du contenu.</p>
 *
 * <p>Ne depend d'AUCUNE autre mecanique ({@code domain.mechanics.banditmanchot},
 * {@code domain.mechanics.texteatrou}) — regle ArchUnit 5 verte — ni d'un generateur
 * de contenu concret — regle ArchUnit 4. Elle ne consomme que l'abstraction
 * {@link ContentGenerator}.</p>
 */
public final class QuizAventureMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("quiz-aventure");

    /** Fragment Thymeleaf qui presente cette mecanique (cf. ADR-0007). */
    public static final String VIEW = "quizaventure";

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
