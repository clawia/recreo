package com.recreo.domain.content;

/**
 * Abstraction du hasard, injectee partout ou de l'aleatoire est requis. Permet
 * des tests deterministes (implementation scriptee/seedee) sans coupler le
 * domaine a {@link java.util.Random}.
 */
public interface RandomSource {
    /** Renvoie un entier dans [0, bound). */
    int nextInt(int bound);
}
