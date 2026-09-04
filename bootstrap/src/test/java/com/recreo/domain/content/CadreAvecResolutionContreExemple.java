package com.recreo.domain.content;

/**
 * ADR-0003 : viole la clause 11 d'ADR-0080 en declarant, sur un record du contrat de contenu, une
 * methode ecrite a la main qui ne porte le nom d'aucun de ses champs - exactement la forme que
 * prendrait {@code CadreDeZone.contient(int, int)}, le predicat de resolution d'un point en zone
 * qu'ADR-0080 refuse d'ecrire.
 *
 * <p>Il vit dans {@code com.recreo.domain.content} et non dans son sous-paquet
 * {@code contreexemple}, meme motif que {@code ModeDeSaisieEnBooleenContreExemple} : la regle porte
 * sur le paquet exact du contrat.</p>
 *
 * <p>Existe pour etre refuse par
 * {@code DecouplageMecaniqueContenuTest.aucunRecordDuContratDeContenuNeDeclareDeMethode}.</p>
 */
public record CadreAvecResolutionContreExemple(int x, int y, int largeur, int hauteur) {

    public boolean contient(int px, int py) {
        return px >= x && px < x + largeur && py >= y && py < y + hauteur;
    }
}
