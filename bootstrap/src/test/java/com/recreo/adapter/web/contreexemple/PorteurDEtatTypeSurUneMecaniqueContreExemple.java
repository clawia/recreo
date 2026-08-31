package com.recreo.adapter.web.contreexemple;

import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;

/**
 * ADR-0003, ADR-0058 R-A : le porteur d'etat de partie type sur une mecanique, hors du point de
 * descente nomme. Ce n'est pas une faute imaginaire - c'est <b>exactement</b> la forme qu'avaient
 * {@code EtatDePartie} et {@code EtatDePartieEnSession} jusqu'a s24, et le chemin le moins cher
 * l'ecrirait ainsi : le controleur descend du contrat vers le type concret des la construction de
 * l'etat, et tout ce qui le transporte se retrouve type sur la mecanique.
 *
 * <p>Cette classe n'est jamais instanciee. Elle existe pour etre refusee par
 * {@code DecouplageMecaniqueContenuTest.adapterWebNeNommeAucuneMecaniqueHorsDeSonPointDeDescente}.</p>
 */
public final class PorteurDEtatTypeSurUneMecaniqueContreExemple {

    private final BanditManchotSession session;

    public PorteurDEtatTypeSurUneMecaniqueContreExemple(BanditManchotSession session) {
        this.session = session;
    }

    public BanditManchotSession session() {
        return session;
    }
}
