package com.recreo.domain.content.contreexemple;

/**
 * ADR-0003 : viole ADR-0005. Le contre-exemple d'une regle qui brule un nom doit porter ce nom
 * exact, il ne peut donc pas prendre le suffixe ContreExemple ; le paquet le dit a sa place.
 * Existe pour etre refuse par AbstractionsCiteesTest.aucunTypeNommeRuleSet.
 */
public interface RuleSet {
}
