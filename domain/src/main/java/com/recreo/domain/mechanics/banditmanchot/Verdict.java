package com.recreo.domain.mechanics.banditmanchot;

import java.util.Optional;

/**
 * Le resultat d'une manche jouee : juste ou faux, avec la bonne reponse pour que l'ecran de
 * retour puisse la designer quand l'enfant s'est trompe.
 *
 * <p>ADR-0024 : le verdict porte aussi l'explication de l'item qui vient d'etre joue, parce qu'il
 * est la seule trace de cet item dans la session. Des que {@code jouer} a rendu la main, la manche
 * courante est la <b>suivante</b> : l'item que l'enfant vient de lire n'est plus atteignable
 * autrement. Un accesseur separe sur la session, sur le modele de {@code indice()}, aurait exige un
 * champ parallele a tenir en phase avec ce verdict a chacune des constructions de la session - la
 * forme exacte du defaut d'ADR-0016. Ici, la composante est obligatoire : aucun constructeur de
 * commodite ne la laisse tomber, donc aucun site ne peut construire un verdict sans decider de
 * l'explication.</p>
 *
 * <p>{@code Optional.empty()} n'est pas un manque : c'est la reponse normale pour un item que le
 * contenu n'a pas juge digne d'explication. La mecanique ne fait aucune difference entre les deux
 * cas, elle transporte ce qu'elle recoit.</p>
 */
public record Verdict(boolean juste, String bonneReponse, Optional<String> explication) {
}
