package com.recreo.domain.content;

/**
 * ADR-0003, second contre-exemple d'ADR-0095. Le descripteur de contenu dont le niveau cesse
 * d'etre une donnee : la forme complete de la regression qu'ADR-0006 interdit et que
 * {@code DifficulteTest.leContratDeContenuNePorteQueDuTexte} <b>laisse passer</b>, son
 * {@code estPermis} admettant tout type du paquet du contrat.
 *
 * <p>Il existe pour que la morsure d'ADR-0095 puisse etre double : la regle neuve rougit dessus,
 * et la regle d'ADR-0006 reste verte. Sans lui, rien ne prouverait qu'ADR-0095 ferme autre chose
 * que ce qui etait deja ferme.
 */
public record ContratAuNiveauEnumereContreExemple(
        String matiere, NiveauEnumereContreExemple niveau, String domaine, String competence) {
}
