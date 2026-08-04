package com.recreo.domain.mechanics.banditmanchot;

/**
 * Le resultat d'une manche jouee : juste ou faux, avec la bonne reponse pour que l'ecran de
 * retour puisse la designer quand l'enfant s'est trompe.
 */
public record Verdict(boolean juste, String bonneReponse) {
}
