package com.recreo.domain.content;

/**
 * Ou se lit le libelle d'une zone designable : dans la boite du bouton, ou hors d'elle.
 * ADR-0094. Un fait declare par le paquet, jamais une forme - ou le libelle est ECRIT a l'ecran,
 * s'il y a une legende, un numero ou un renvoi, appartient a ines-design et a nadia-prof.
 * ADR-0069 : un type, jamais un booleen - leContratDeContenuNePorteQueDuTexte le refuserait.
 */
public enum EmplacementDuLibelleDeZone {

    /** Le libelle est le texte du bouton de la zone. Defaut de tout paquet qui ne declare rien. */
    DANS_LA_BOITE,

    /** Le libelle se lit ailleurs sur l'ecran ; le bouton ne le porte pas comme texte. */
    HORS_DE_LA_BOITE
}
