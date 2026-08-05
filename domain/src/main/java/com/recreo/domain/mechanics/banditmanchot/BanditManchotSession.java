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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * L'etat d'une partie de bandit manchot : la manche en attente de reponse, le verdict de la
 * derniere manche jouee, le score cumule et le tirage deja vu (ADR-0004 : l'etat de session
 * appartient a la mecanique, pas a un moteur partage).
 *
 * <p>ADR-0012 : la partie se termine des que {@code nombreDeManches} reponses ont ete donnees,
 * independamment de ce que rend le contenu. Deux aides face a l'echec repete (DEC-0004, point 4) :
 * apres trois echecs consecutifs, quel que soit l'item, la manche suivante represente un item deja
 * reussi dans la session plutot qu'un nouveau tirage ; un item ayant deja ete rate trois fois, s'il
 * est represente a nouveau, porte l'indice que son contenu lui associe.</p>
 */
public final class BanditManchotSession implements GameSession {

    private static final int SEUIL_ECHECS_CONSECUTIFS = 3;
    private static final int SEUIL_ECHECS_SUR_LE_MEME_ITEM = 3;

    private final ContentSource contenu;
    private final RandomSource hasard;
    private final int nombreDeManches;
    private final Set<ItemId> dejaTirees;
    private final List<Question> reussies;
    private final Map<ItemId, Integer> echecsParItem;
    private final int echecsConsecutifs;
    private final Question mancheCourante;
    private final List<String> propositions;
    private final Optional<String> indiceAffiche;
    private final Verdict dernierVerdict;
    private final Score score;
    private final SessionStatus status;

    private BanditManchotSession(ContentSource contenu, RandomSource hasard, int nombreDeManches,
                                  Set<ItemId> dejaTirees, List<Question> reussies,
                                  Map<ItemId, Integer> echecsParItem, int echecsConsecutifs,
                                  Question mancheCourante, List<String> propositions,
                                  Optional<String> indiceAffiche, Verdict dernierVerdict, Score score,
                                  SessionStatus status) {
        this.contenu = contenu;
        this.hasard = hasard;
        this.nombreDeManches = nombreDeManches;
        this.dejaTirees = Set.copyOf(dejaTirees);
        this.reussies = List.copyOf(reussies);
        this.echecsParItem = Map.copyOf(echecsParItem);
        this.echecsConsecutifs = echecsConsecutifs;
        this.mancheCourante = mancheCourante;
        this.propositions = List.copyOf(propositions);
        this.indiceAffiche = indiceAffiche;
        this.dernierVerdict = dernierVerdict;
        this.score = score;
        this.status = status;
    }

    static BanditManchotSession demarrer(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(), List.of(), Map.of(), 0,
                null, new Score(0, 0));
    }

    BanditManchotSession jouer(BanditManchotMove coup) {
        if (mancheCourante == null) {
            throw new IllegalStateException("la partie est terminee, plus aucune manche a jouer");
        }
        boolean juste = mancheCourante.bonneReponse().equals(coup.propositionChoisie());
        Verdict verdict = new Verdict(juste, mancheCourante.bonneReponse());
        Score nouveauScore = avancer(score, juste);

        List<Question> reussiesSuivantes = new ArrayList<>(reussies);
        Map<ItemId, Integer> echecsSuivants = new HashMap<>(echecsParItem);
        int echecsConsecutifsSuivants;
        if (juste) {
            if (reussiesSuivantes.stream().noneMatch(question -> question.id().equals(mancheCourante.id()))) {
                reussiesSuivantes.add(mancheCourante);
            }
            echecsConsecutifsSuivants = 0;
        } else {
            echecsSuivants.merge(mancheCourante.id(), 1, Integer::sum);
            echecsConsecutifsSuivants = echecsConsecutifs + 1;
        }

        if (nouveauScore.tentatives() >= nombreDeManches) {
            return new BanditManchotSession(contenu, hasard, nombreDeManches, dejaTirees, reussiesSuivantes,
                    echecsSuivants, echecsConsecutifsSuivants, null, List.of(), Optional.empty(), verdict,
                    nouveauScore, SessionStatus.TERMINEE);
        }
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(dejaTirees), reussiesSuivantes,
                echecsSuivants, echecsConsecutifsSuivants, verdict, nouveauScore);
    }

    /**
     * Choisit la manche suivante : apres le seuil d'echecs consecutifs, un item deja reussi dans la
     * session plutot qu'un nouveau tirage (DEC-0004, point 4), sauf si aucun item n'a encore ete
     * reussi, auquel cas le tirage normal reprend sans erreur (repli explicite).
     */
    private static BanditManchotSession tirerManche(ContentSource contenu, RandomSource hasard,
                                                      int nombreDeManches, HashSet<ItemId> tirees,
                                                      List<Question> reussies,
                                                      Map<ItemId, Integer> echecsParItem,
                                                      int echecsConsecutifs, Verdict verdict, Score score) {
        Question manche;
        int echecsConsecutifsApres = echecsConsecutifs;
        if (echecsConsecutifs >= SEUIL_ECHECS_CONSECUTIFS && !reussies.isEmpty()) {
            manche = reussies.get(hasard.nextInt(reussies.size()));
            echecsConsecutifsApres = 0;
        } else {
            Optional<Item> tiree = contenu.next(hasard, tirees);
            if (tiree.isEmpty()) {
                return new BanditManchotSession(contenu, hasard, nombreDeManches, tirees, reussies,
                        echecsParItem, echecsConsecutifsApres, null, List.of(), Optional.empty(), verdict,
                        score, SessionStatus.TERMINEE);
            }
            manche = questionDe(tiree.orElseThrow());
        }
        tirees.add(manche.id());
        Optional<String> indiceAffiche = echecsParItem.getOrDefault(manche.id(), 0) >= SEUIL_ECHECS_SUR_LE_MEME_ITEM
                ? manche.indice() : Optional.empty();
        return new BanditManchotSession(contenu, hasard, nombreDeManches, tirees, reussies, echecsParItem,
                echecsConsecutifsApres, manche, melanger(manche, hasard), indiceAffiche, verdict, score,
                SessionStatus.EN_COURS);
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

    /** L'indice n'est expose qu'apres le seuil d'echecs sur ce meme item (DEC-0004, point 4). */
    public Optional<String> indice() {
        return indiceAffiche;
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
