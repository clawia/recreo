package com.recreo.domain.mechanics;

/**
 * ADR-0013, ADR-0021 : contre-exemple, sous src/test/resources donc jamais compile ni charge. Une
 * mecanique qui nomme une matiere ne sert plus qu'a elle.
 */
final class MecaniqueQuiCompteDesSyllabesContreExemple {

    int nombreDeSyllabes(String mot) {
        return mot.length() / 2;
    }
}
