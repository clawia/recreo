package com.recreo.domain.content;

/**
 * ADR-0003 : viole ADR-0058, pendant exact de {@code NormalisationPartageeContreExemple} pour le
 * contrat de contenu et sous l'<b>autre</b> forme de comportement qu'une interface autorise - une
 * methode <b>par defaut</b>, heritee par tout type de contenu qui implementerait ce contrat, donc
 * partagee par toutes les matieres sans qu'aucune ne l'ait demandee.
 *
 * <p>Sans champ, pour ne violer que cette regle : un champ non textuel ferait aussi echouer
 * ADR-0006, et une classe ferait echouer ADR-0004 (1) - or c'est precisement ce qu'ADR-0004 (1) ne
 * voit pas qui est en cause ici.</p>
 *
 * <p>Existe pour etre refuse par
 * {@code DecouplageMecaniqueContenuTest.unContratNeDeclareAucunComportement}.</p>
 */
public interface EnonceNormaliseContreExemple {

    default String enonceNormalise(String enonce) {
        return enonce.strip();
    }
}
