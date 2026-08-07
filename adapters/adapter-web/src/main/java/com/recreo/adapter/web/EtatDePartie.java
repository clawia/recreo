package com.recreo.adapter.web;

import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;

/**
 * Instantane des trois attributs de session qui portent une partie, lus ensemble sous le meme
 * verrou (ADR-0019). Les lire un par un exposerait a un etat mixte : le jeu de la partie qui
 * commence avec la session de celle qui vient d'etre abandonnee.
 */
record EtatDePartie(String jeuId, String consigne, BanditManchotSession session) {

    /** L'etat porte bien une partie du jeu demande par l'URL. */
    boolean porte(String jeuIdDemande) {
        return jeuIdDemande.equals(jeuId) && session != null;
    }

    boolean portePartieEnCours() {
        return jeuId != null && session != null && session.status() == SessionStatus.EN_COURS;
    }
}
