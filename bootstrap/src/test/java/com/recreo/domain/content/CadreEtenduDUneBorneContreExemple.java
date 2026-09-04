package com.recreo.domain.content;

/**
 * ADR-0003 : le jeu fautif du <b>plafond</b> de l'exemption geometrique ouverte dans ADR-0006 le
 * 2026-09-04. Il prend la seule route par laquelle la dette 2 de CLAUDE.md pourrait rentrer par
 * cette exemption : etendre la forme exemptee d'une composante de plus, en gardant les quatre du
 * rectangle pour se ranger sous elle.
 *
 * <p>Ce qu'il prouve, dit sans le gonfler : il serait refuse par la regle d'avant l'amendement
 * comme par celle d'apres. Ce qu'il tient est le plafond - le jour ou l'exemption serait elargie a
 * "un record dont les champs sont des entiers", ce contre-exemple deviendrait permis et sa morsure
 * echouerait. C'est ce qu'un plafond doit faire.</p>
 *
 * <p>Il vit dans com.recreo.domain.content et non dans son sous-paquet contreexemple, meme motif
 * que ReglageDeLongueurDePartieContreExemple : la regle porte sur le paquet exact du contrat.</p>
 *
 * <p>Record et non classe, et sans methode declaree : sa seule violation doit etre le type de ses
 * champs, sans quoi il ferait aussi echouer ADR-0004 (1) ou la clause 11 d'ADR-0080.</p>
 *
 * <p>Existe pour etre refuse par DifficulteTest.leContratDeContenuNePorteQueDuTexte.</p>
 */
public record CadreEtenduDUneBorneContreExemple(int x, int y, int largeur, int hauteur,
                                                 int sommeMaximale) {
}
