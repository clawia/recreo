package com.recreo.domain.content;

/**
 * ADR-0024 : contre-exemple, jamais compile. La tentation exacte du lot "Le bon son" - poser la
 * graphie sur le type que toutes les matieres traversent, ce qui obligerait chaque paquet de
 * mathematiques a porter un champ de francais.
 */
record QuestionAvecGraphieContreExemple(String enonce, String bonneReponse, String graphie) {
}
