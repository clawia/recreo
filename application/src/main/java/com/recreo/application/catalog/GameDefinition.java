package com.recreo.application.catalog;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.mechanic.MechanicId;

/**
 * ADR-0007 : un jeu n'est pas une classe, c'est une entree de catalogue qui associe une mecanique
 * et un paquet de contenu. La consigne est portee ici, jamais par la mecanique (ADR-0004).
 * ADR-0012 : nombreDeManches est un attribut de cette association (mecanique + paquet), pas du
 * contenu ni de la mecanique en general.
 */
public record GameDefinition(
        String id,
        String titre,
        String consigne,
        MechanicId mecanique,
        ContentDescriptor descripteur,
        ContentSource contenu,
        int nombreDeManches) {
}
