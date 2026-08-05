package com.recreo.domain.content;

/**
 * ADR-0003 : viole ADR-0006, et par elle ADR-0012. C'est le cas reel, et non plus seulement
 * synthetique, que le sprint 1 a fait surgir : la longueur d'une partie glissee dans le contrat de
 * contenu, la ou ADR-0012 la refuse au profit de l'entree de catalogue. Existe pour etre refuse par
 * DifficulteTest.leContratDeContenuNePorteQueDuTexte.
 *
 * <p>Il vit dans com.recreo.domain.content et non dans com.recreo.domain.content.contreexemple :
 * la regle est portee sur le paquet exact du contrat, pas sur ses sous-paquets. Un contre-exemple
 * range dans le sous-paquet serait invisible pour elle, et son test de non-vacuite echouerait sur
 * "failed to check any classes" au lieu de la violation attendue. ADR-0012 est amende sur ce
 * point.</p>
 *
 * <p>Record et non classe, comme ReglageArithmetiqueContreExemple : sa seule violation doit etre le
 * type de son champ, sans quoi il ferait aussi echouer ADR-0004 (1).</p>
 */
public record ReglageDeLongueurDePartieContreExemple(int nombreDeManches) {
}
