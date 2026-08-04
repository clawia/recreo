package com.recreo.domain.content;

/**
 * ADR-0006 : quatre chaines, jamais un enum. Ajouter un niveau ou une matiere est une donnee.
 * ADR-0004 : aucune mecanique n'a le droit de dependre de ce type.
 */
public record ContentDescriptor(String matiere, String niveau, String domaine, String competence) {
}
