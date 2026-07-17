package com.recreo.domain.mechanic;

/** Catalogue ferme des coups possibles dans une mecanique de jeu. */
public sealed interface Move permits SpinAndPick, Skip {
}
