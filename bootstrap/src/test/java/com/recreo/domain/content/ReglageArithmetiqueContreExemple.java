package com.recreo.domain.content;

/**
 * ADR-0003 : viole ADR-0006, la regle qui tient la propriete de fond. C'est DifficultyConfig sous un
 * autre nom : deux bornes arithmetiques dans le contrat de contenu, que tout paquet de francais
 * devrait remplir de valeurs factices. Existe pour etre refuse par
 * DifficulteTest.leContratDeContenuNePorteQueDuTexte.
 *
 * <p>Le contre-exemple est un record, pas une classe : sa seule violation doit etre le type de ses
 * champs, sans quoi il ferait aussi echouer ADR-0004 (1) et le motif de l'echec deviendrait
 * ambigu.</p>
 */
public record ReglageArithmetiqueContreExemple(int sommeMaximale, int nombreDePropositions) {
}
