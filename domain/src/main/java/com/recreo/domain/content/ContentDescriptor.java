package com.recreo.domain.content;

/**
 * Decrit le contenu produit par un {@link ContentGenerator} en vocabulaire
 * Education nationale : matiere, niveau (CP, CM1, ...) et domaine.
 */
public record ContentDescriptor(String matiere, String niveau, String domaine) {
}
