package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.mechanic.Move;

/**
 * Le coup de l'enfant : le texte de la proposition choisie parmi celles affichees.
 */
public record BanditManchotMove(String propositionChoisie) implements Move {
}
