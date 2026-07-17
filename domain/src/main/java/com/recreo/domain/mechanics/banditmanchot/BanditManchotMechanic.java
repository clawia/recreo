package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.Feedback;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.Reel;
import com.recreo.domain.mechanic.SessionId;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanic.SpinAndPick;
import com.recreo.domain.mechanic.Skip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Bandit manchot : tire un {@link Item}, construit les rouleaux (bonne reponse +
 * distracteurs melanges), valide le choix et met a jour le score.
 *
 * <p>Ne reference JAMAIS {@code CalculMentalGenerator} ni le package
 * {@code content.impl} : elle consomme uniquement l'abstraction
 * {@link ContentGenerator} (garanti par ArchUnit).</p>
 */
public final class BanditManchotMechanic implements GameMechanic {

    public static final MechanicId ID = new MechanicId("bandit-manchot");

    private static final int POINTS_PER_CORRECT = 10;

    @Override
    public MechanicId id() {
        return ID;
    }

    @Override
    public GameSession start(GameConfig config, RandomSource random) {
        ContentGenerator generator = config.content();
        Item item = generator.next(random);
        List<Reel> reels = buildReels(item, random);
        SessionId sessionId = new SessionId(UUID.randomUUID().toString());
        return new GameSession(
                sessionId,
                ID,
                null,
                item,
                reels,
                0,
                1,
                SessionStatus.ACTIVE,
                generator,
                random);
    }

    @Override
    public MoveResult apply(GameSession session, Move move) {
        ContentGenerator generator = session.content();
        RandomSource random = session.random();

        return switch (move) {
            case SpinAndPick pick -> {
                boolean correct = pick.chosen().equals(session.currentItem().expected());
                int newScore = session.score() + (correct ? POINTS_PER_CORRECT : 0);
                Feedback feedback = correct
                        ? new Feedback("Bravo ! +" + POINTS_PER_CORRECT + " points")
                        : new Feedback("Presque ! La bonne reponse etait "
                                + session.currentItem().expected().value());
                GameSession updated = advance(session, newScore, generator, random);
                yield new MoveResult(updated, correct, feedback);
            }
            case Skip ignored -> {
                GameSession updated = advance(session, session.score(), generator, random);
                yield new MoveResult(updated, false, new Feedback("Item passe."));
            }
        };
    }

    private GameSession advance(GameSession session, int newScore, ContentGenerator generator, RandomSource random) {
        Item next = generator.next(random);
        List<Reel> reels = buildReels(next, random);
        return session
                .withScore(newScore)
                .withRound(session.round() + 1)
                .withCurrentItem(next)
                .withReels(reels);
    }

    private List<Reel> buildReels(Item item, RandomSource random) {
        List<Answer> options = new ArrayList<>();
        options.add(item.expected());
        options.addAll(item.distractors());
        shuffle(options, random);
        return List.of(new Reel(List.copyOf(options)));
    }

    private void shuffle(List<Answer> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
    }
}
