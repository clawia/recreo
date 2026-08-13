package com.recreo.domain.content;

import java.util.Optional;
import java.util.Set;

/**
 * ADR-0005 : c'est ce contrat, et non un moteur de regles, qui decide quel item est presente.
 */
public interface ContentSource {

    ContentDescriptor descriptor();

    Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus);

    /**
     * M21 (arbitrage de nadia-prof du 2026-08-13) : vrai quand les indices de ce paquet nomment la
     * bonne reponse - la mecanique ne les affiche alors jamais, quel que soit le nombre d'echecs.
     * Propriete du paquet entier, jamais d'un item isole ni d'un identifiant de jeu compare en dur :
     * une comparaison litterale texte-a-texte disqualifierait a tort un indice sain, mesure sur un
     * paquet de nombres de CP. Par defaut, faux : un paquet neuf porte des indices utilisables tant
     * qu'il ne declare pas le contraire.
     */
    default boolean indicesNommentLaReponse() {
        return false;
    }
}
