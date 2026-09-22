package com.recreo.domain.content;

/**
 * ADR-0003, contre-exemple d'ADR-0095. Le type enumere du niveau qu'ADR-0006 refuse et que
 * DEC-0090 nomme comme la seule contrainte de fond posee au mecanisme d'ordre.
 *
 * <p>Il reside dans {@code com.recreo.domain.content}, et pas dans le paquet
 * {@code contreexemple} voisin, parce que c'est exactement la ou le chemin le moins cher le
 * poserait : dans le contrat de contenu, a cote du descripteur qui porte le niveau. C'est aussi
 * la position qui rend visible le trou que cette regle ferme -
 * {@code DifficulteTest.leContratDeContenuNePorteQueDuTexte} admet tout type du paquet
 * {@code com.recreo.domain.content}, donc celui-ci.
 */
public enum NiveauEnumereContreExemple {
    CP,
    CE1,
    CM1,
    CM2
}
