package com.recreo.domain.mechanics.saisie;

import com.recreo.domain.mechanic.Move;

/**
 * Le coup de l'enfant : le texte qu'il a ecrit, tel que le serveur l'a recu, sans repliage ni
 * retouche. ADR-0004 clause 6 : chaque mecanique definit ses coups dans son propre paquet.
 */
public record SaisieMove(String texteEcrit) implements Move {
}
