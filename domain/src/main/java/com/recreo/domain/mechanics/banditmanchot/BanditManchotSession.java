package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanic.SessionStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * L'etat d'une partie de bandit manchot : la manche en attente de reponse, le verdict de la
 * derniere manche jouee, le score cumule et le tirage deja vu (ADR-0004 : l'etat de session
 * appartient a la mecanique, pas a un moteur partage).
 */
public final class BanditManchotSession implements GameSession {

    private final ContentSource contenu;
    private final RandomSource hasard;
    private final Set<ItemId> dejaTirees;
    private final Question mancheCourante;
    private final List<String> propositions;
    private final Verdict dernierVerdict;
    private final Score score;
    private final SessionStatus status;

    private BanditManchotSession(ContentSource contenu, RandomSource hasard, Set<ItemId> dejaTirees,
                                  Question mancheCourante, List<String> propositions,
                                  Verdict dernierVerdict, Score score, SessionStatus status) {
        this.contenu = contenu;
        this.hasard = hasard;
        this.dejaTirees = Set.copyOf(dejaTirees);
        this.mancheCourante = mancheCourante;
        this.propositions = List.copyOf(propositions);
        this.dernierVerdict = dernierVerdict;
        this.score = score;
        this.status = status;
    }

    static BanditManchotSession demarrer(ContentSource contenu, RandomSource hasard) {
        return tirerManche(contenu, hasard, new HashSet<>(), null, new Score(0, 0));
    }

    BanditManchotSession jouer(BanditManchotMove coup) {
        if (mancheCourante == null) {
            throw new IllegalStateException("la partie est terminee, plus aucune manche a jouer");
        }
        boolean juste = mancheCourante.bonneReponse().equals(coup.propositionChoisie());
        Verdict verdict = new Verdict(juste, mancheCourante.bonneReponse());
        Score nouveauScore = avancer(score, juste);
        return tirerManche(contenu, hasard, new HashSet<>(dejaTirees), verdict, nouveauScore);
    }

    private static BanditManchotSession tirerManche(ContentSource contenu, RandomSource hasard,
                                                      HashSet<ItemId> tirees, Verdict verdict,
                                                      Score score) {
        Optional<Item> tiree = contenu.next(hasard, tirees);
        if (tiree.isEmpty()) {
            return new BanditManchotSession(contenu, hasard, tirees, null, List.of(), verdict,
                    score, SessionStatus.TERMINEE);
        }
        Question question = questionDe(tiree.orElseThrow());
        tirees.add(question.id());
        return new BanditManchotSession(contenu, hasard, tirees, question, melanger(question, hasard),
                verdict, score, SessionStatus.EN_COURS);
    }

    private static Question questionDe(Item item) {
        return switch (item) {
            case Question question -> question;
        };
    }

    private static Score avancer(Score score, boolean juste) {
        return new Score(score.reussites() + (juste ? 1 : 0), score.tentatives() + 1);
    }

    private static List<String> melanger(Question question, RandomSource hasard) {
        List<String> propositions = new ArrayList<>();
        propositions.add(question.bonneReponse());
        propositions.addAll(question.distracteurs());
        for (int i = propositions.size() - 1; i > 0; i--) {
            int j = hasard.nextInt(i + 1);
            String tampon = propositions.get(i);
            propositions.set(i, propositions.get(j));
            propositions.set(j, tampon);
        }
        return propositions;
    }

    public Optional<String> enonceManche() {
        return Optional.ofNullable(mancheCourante).map(Question::enonce);
    }

    public List<String> propositions() {
        return propositions;
    }

    public Optional<Verdict> dernierVerdict() {
        return Optional.ofNullable(dernierVerdict);
    }

    @Override
    public SessionStatus status() {
        return status;
    }

    @Override
    public Score score() {
        return score;
    }
}
