package com.recreo.domain.content;

import com.recreo.domain.content.contreexemple.OperationContreExemple;

import java.util.List;
import java.util.Set;

/**
 * ADR-0003 : viole ADR-0006 par l'argument generique, la porte que la premiere ecriture de la regle
 * laissait ouverte. Le type brut de ces deux champs est une collection de java.util, donc permis ;
 * c'est ce qu'ils contiennent qui ramene la dette. Existe pour etre refuse par
 * DifficulteTest.leContratDeContenuNePorteQueDuTexte.
 */
public record ReglageEnCollectionContreExemple(Set<OperationContreExemple> operations,
                                               List<Integer> bornes) {
}
