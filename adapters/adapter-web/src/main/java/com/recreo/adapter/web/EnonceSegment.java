package com.recreo.adapter.web;

/**
 * ADR-0062 : un fragment de l'enonce d'une manche, tel que le gabarit doit le rendre - son texte, et
 * s'il porte la part que le contenu a designee. Ce type vit ici, jamais dans le domaine : c'est une
 * forme de rendu, et aucun autre appelant que {@code fragments/manche.html} n'en a l'usage.
 *
 * <p>Public pour que Thymeleaf/SpringEL puisse lire {@link #texte()} et {@link #designe()} par
 * reflexion depuis le gabarit, sur le meme principe que {@code GameDefinition}.</p>
 */
public record EnonceSegment(String texte, boolean designe) {
}
