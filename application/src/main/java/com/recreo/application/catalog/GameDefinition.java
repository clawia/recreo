package com.recreo.application.catalog;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.mechanic.MechanicId;

/**
 * ADR-0007 : un jeu n'est pas une classe, c'est une entree de catalogue qui associe une mecanique
 * et un paquet de contenu. La consigne est portee ici, jamais par la mecanique (ADR-0004).
 */
public record GameDefinition(
        String id,
        String titre,
        String consigne,
        MechanicId mecanique,
        ContentDescriptor descripteur,
        ContentSource contenu) {
}
