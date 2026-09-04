package com.recreo.domain.content;

/**
 * ADR-0080, decision 1 : une zone designable d'un item de {@link ZoneADesigner}.
 *
 * <p>{@code id} est l'identifiant que le client renvoie au serveur - jamais une coordonnee, jamais
 * un libelle. {@code libelle} est le nom accessible du bouton qui la represente a l'ecran, celui
 * qu'une lecture d'ecran annonce. {@code cadre} porte sa geometrie sur l'image (voir
 * {@link CadreDeZone}).</p>
 */
public record Zone(String id, String libelle, CadreDeZone cadre) {
}
