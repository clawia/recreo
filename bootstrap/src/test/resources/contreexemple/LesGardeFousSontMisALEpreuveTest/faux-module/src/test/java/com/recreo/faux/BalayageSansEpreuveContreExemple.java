package com.recreo.faux;

/**
 * Ce fichier n'est jamais compile : il est lu comme donnee par LesGardeFousSontMisALEpreuveTest.
 *
 * <p>Il balaie un artefact de production et ne se met jamais a l'epreuve : aucun jeu fautif, aucune
 * execution qui exige le rouge. C'est la forme exacte du defaut mesure deux fois en s01 et s02.
 * Le recensement doit le nommer.
 */
class BalayageSansEpreuveContreExemple {

    private static final String GABARITS = "src/main/resources/templates";

    void toutVaBien() {
        System.out.println(GABARITS);
    }
}
