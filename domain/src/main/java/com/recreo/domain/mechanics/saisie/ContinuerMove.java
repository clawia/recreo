package com.recreo.domain.mechanics.saisie;

import com.recreo.domain.mechanic.Move;

/**
 * Le second coup de cette mecanique : passer a la suite une fois la reponse revelee.
 *
 * <p>Il existe parce que la revelation de M5 n'a pas ici la meme forme que chez la mecanique
 * voisine. La-bas, l'enfant resout la manche revelee en appuyant sur la reponse que le jeu vient de
 * nommer, et le geste est deja a l'ecran. Ici il n'y a plus rien a ecrire : le champ disparait, et
 * l'enfant lit ce qu'il avait ecrit a cote de la reponse attendue (SAI-9b) avant de passer a la
 * manche suivante. Ce moment de lecture est le seul que SAI-9b ait, et il demande un geste a lui.
 *
 * <p>Un coup distinct plutot qu'un {@link SaisieMove} dont le texte serait ignore : deux gestes qui
 * ne veulent pas dire la meme chose ne se distinguent pas par une valeur convenue entre l'adaptateur
 * et la mecanique. Un record sans composante est le type le plus petit qui dise "passer a la suite".
 */
public record ContinuerMove() implements Move {
}
