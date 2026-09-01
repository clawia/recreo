package com.recreo.adapter.web;

import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.SessionStatus;

/**
 * Instantane des quatre attributs de session qui portent une partie, lus ensemble sous le meme
 * verrou (ADR-0019). Les lire un par un exposerait a un etat mixte : le jeu de la partie qui
 * commence avec la session de celle qui vient d'etre abandonnee.
 *
 * <p>ADR-0058, R-A : la session est portee par le <b>contrat</b> et non par le type concret d'une
 * mecanique. Ce record ne fait que la transporter d'un bout a l'autre du verrou ; il n'a jamais eu
 * besoin de savoir laquelle, et le typer sur une mecanique faisait de ce fichier l'un des trois
 * endroits d'{@code adapter-web} ou le nom d'une mecanique apparaissait.</p>
 *
 * <p>ADR-0075 section 7 : {@code consigneAdulte} suit {@code consigne} au meme titre - fixee au
 * demarrage de la partie depuis l'entree de catalogue, jamais recalculee en cours de partie. Type
 * brut et non {@code Optional}, sur le patron exact de {@code consigne} dans ce record : la seule
 * chose que ce fichier transporte, il ne l'interprete jamais.</p>
 */
record EtatDePartie(String jeuId, String consigne, String consigneAdulte, GameSession session) {

    /** L'etat porte bien une partie du jeu demande par l'URL. */
    boolean porte(String jeuIdDemande) {
        return jeuIdDemande.equals(jeuId) && session != null;
    }

    boolean portePartieEnCours() {
        return jeuId != null && session != null && session.status() == SessionStatus.EN_COURS;
    }
}
