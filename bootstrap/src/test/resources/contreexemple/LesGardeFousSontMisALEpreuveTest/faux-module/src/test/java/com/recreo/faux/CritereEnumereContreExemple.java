package com.recreo.faux;

/**
 * Ce fichier n'est jamais compile : il est lu comme donnee par LesGardeFousSontMisALEpreuveTest.
 *
 * <p>ADR-0039, recensement 3 : il avoue garder un critere enumere assume, et aucun recensement ne le
 * nomme. Il ne lit aucun artefact de production - il ne doit donc pas entrer dans le recensement 1 -
 * et ne declare aucune ArchRule.
 */
class CritereEnumereContreExemple {

    /**
     * ADR-0039, critere enumere assume : une liste de refus, verte par defaut sur tout nom qu'elle ne
     * porte pas. C'est exactement ce que le recensement doit rendre visible et plafonner.
     */
    private static final String BIBLIOTHEQUE_REFUSEE = "bibliotheque-nommee";

    boolean refuse(String nom) {
        return nom.contains(BIBLIOTHEQUE_REFUSEE);
    }
}
