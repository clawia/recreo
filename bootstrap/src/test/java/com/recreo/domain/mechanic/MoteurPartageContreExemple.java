package com.recreo.domain.mechanic;

/**
 * ADR-0003 : viole ADR-0004 (2). Une classe concrete avec du comportement dans le paquet de
 * contrat, c'est le SessionEngine de la version precedente. Existe pour etre refusee par
 * DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme.
 */
public class MoteurPartageContreExemple {

    private int reussites;
    private int tentatives;

    public Score compter(boolean juste) {
        tentatives++;
        if (juste) {
            reussites++;
        }
        return new Score(reussites, tentatives);
    }
}
