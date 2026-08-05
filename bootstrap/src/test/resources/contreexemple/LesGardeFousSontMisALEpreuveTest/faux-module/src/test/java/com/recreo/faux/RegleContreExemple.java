package com.recreo.faux;

/**
 * Ce fichier n'est jamais compile : il est lu comme donnee par LesGardeFousSontMisALEpreuveTest.
 *
 * <p>Deux regles y sont declarees. L'une est passee a un check, donc mise a l'epreuve ; l'autre ne
 * l'est jamais, et c'est le trou qu'ADR-0003 laissait ouvert dans son propre mecanisme : rien
 * n'obligeait une regle neuve a recevoir son contre-exemple. Le recensement doit nommer la seconde
 * et seulement elle.
 */
class RegleContreExemple {

    static final ArchRule uneRegleEprouvee = new ArchRule();

    static final ArchRule uneRegleJamaisEprouvee = new ArchRule();

    void miseALEpreuveDeLaPremiere() {
        uneRegleEprouvee
                .check(null);
    }

    static final class ArchRule {

        void check(Object classes) {
            System.out.println(classes);
        }
    }
}
