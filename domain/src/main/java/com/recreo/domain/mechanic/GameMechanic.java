package com.recreo.domain.mechanic;

import com.recreo.domain.content.RandomSource;

/**
 * Contrat d'une mecanique de jeu reutilisable. Ne connait rien du contenu
 * concret : elle consomme des {@link com.recreo.domain.content.ContentGenerator}.
 */
public interface GameMechanic {
    MechanicId id();

    /**
     * Identifiant de la VUE (fragment) qui presente cette mecanique — ex.
     * {@code "banditmanchot"} ou {@code "texteatrou"} (cf. ADR-0007). Il vient de la
     * mecanique elle-meme (pas devine cote front) : la page de jeu insere
     * dynamiquement {@code fragments/<view> :: game} et les coups renvoient ce meme
     * fragment. C'est ainsi que deux mecaniques partagent le meme moteur de session
     * mais s'affichent differemment.
     */
    String view();

    GameSession start(GameConfig config, RandomSource random);

    MoveResult apply(GameSession session, Move move);
}
