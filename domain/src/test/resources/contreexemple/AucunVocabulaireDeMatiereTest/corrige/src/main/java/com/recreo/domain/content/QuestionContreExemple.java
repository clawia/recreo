package com.recreo.domain.content;

/**
 * Le meme type partage, corrige : le champ de francais est parti et le texte qui le remplace ne
 * privilegie aucune matiere. C'est la forme que prend la decision d'ADR-0024 - une explication
 * optionnelle que la mecanique transporte sans la lire.
 */
record QuestionContreExemple(String enonce, String bonneReponse, String explication) {
}
