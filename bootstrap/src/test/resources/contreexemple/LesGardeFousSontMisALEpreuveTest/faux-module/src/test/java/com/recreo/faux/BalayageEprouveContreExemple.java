package com.recreo.faux;

/**
 * Ce fichier n'est jamais compile : il est lu comme donnee par LesGardeFousSontMisALEpreuveTest.
 *
 * <p>Il balaie un artefact de production et il est mis a l'epreuve : un jeu fautif existe a cote
 * de lui, et il s'y execute en exigeant le rouge. Le recensement ne doit pas le nommer - sans lui,
 * un recensement qui refuserait tout aurait l'air de mordre sans rien distinguer.
 */
class BalayageEprouveContreExemple {

    private static final String GABARITS = "src/main/resources/templates";

    void leGardeFouEchoueSurLeJeuFautif() {
        assertThatThrownBy(() -> verifier("src/test/resources/contreexemple/BalayageEprouveContreExemple"));
    }

    void verifier(String racine) {
        System.out.println(GABARITS + racine);
    }

    static void assertThatThrownBy(Runnable appel) {
        appel.run();
    }
}
