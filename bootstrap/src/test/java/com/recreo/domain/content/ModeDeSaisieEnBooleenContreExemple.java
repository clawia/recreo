package com.recreo.domain.content;

/**
 * ADR-0003, ADR-0069 : viole ADR-0006. Ce n'est pas une forme imaginee - c'est, mot pour mot, la
 * forme que la section 1 d'ADR-0058 decidait pour l'item de saisie : "un booleen
 * formeExacteExigee". La sonde qui l'a mise a l'epreuve le 2026-08-30, sur l'arbre reel et avec le
 * reste des composantes de l'item, a fait rougir
 * DifficulteTest.leContratDeContenuNePorteQueDuTexte en une violation, en nommant le type boolean.
 *
 * <p>Il vit dans com.recreo.domain.content et non dans son sous-paquet contreexemple, pour la meme
 * raison que ReglageDeLongueurDePartieContreExemple : la regle porte sur le paquet exact du
 * contrat.</p>
 *
 * <p>Record et non classe, et une seule composante fautive : sa seule violation doit etre le type
 * de ce champ, sans quoi il ferait aussi echouer ADR-0004 (1).</p>
 */
public record ModeDeSaisieEnBooleenContreExemple(String enonce, boolean formeExacteExigee) {
}
