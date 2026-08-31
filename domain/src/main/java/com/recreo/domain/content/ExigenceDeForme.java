package com.recreo.domain.content;

/**
 * Ce que l'exercice exige de la forme de la reponse ecrite par l'enfant (SAI-2a, arbitrage de
 * nadia-prof du 2026-08-25).
 *
 * <p>ADR-0069 : c'est un type du contrat de contenu et jamais un booleen. La forme decidee par
 * ADR-0058 section 1 - {@code boolean formeExacteExigee} - est refusee par
 * {@code DifficulteTest.leContratDeContenuNePorteQueDuTexte}, qui n'admet dans ce paquet que du
 * texte, les collections de {@code java.util} et les types du contrat.</p>
 *
 * <p>Le nom dit ce que l'exercice <b>exige</b>, jamais comment une mecanique s'y prend pour
 * comparer : un nom qui parlerait de comparaison ferait dire au contenu ce que la mecanique fait,
 * la ou ADR-0004 tient les deux separes.</p>
 *
 * <p>{@link #FORME_LIBRE} est le defaut de tout item qui ne declare rien, et ce defaut protege
 * l'enfant plutot que l'exercice : les tolerances de SAI-1, SAI-3 et SAI-4 jouent tant qu'un
 * paquet n'a pas dit que la forme elle-meme etait la competence.</p>
 */
public enum ExigenceDeForme {

    /** La reponse est jugee aux tolerances pres que la mecanique possede (SAI-1, SAI-3, SAI-4). */
    FORME_LIBRE,

    /** La forme est la competence : la reponse est jugee sur son ecriture exacte (SAI-2a). */
    FORME_EXACTE
}
