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
     * paquet de nombres de CP.
     *
     * <p>ADR-0058 : cette methode etait declaree {@code default} et renvoyait {@code false}. Elle est
     * abstraite depuis le 2026-08-24, et ce n'est pas un changement de la convention de M21 - "faux
     * tant que le paquet ne declare pas le contraire" est implemente par
     * {@code QuestionContentSourceFactory}, qui lit la cle facultative du YAML, jamais par ce defaut.
     * Le defaut ne servait qu'a dispenser une implementation de decider, ce qui est la forme exacte
     * du constructeur de commodite par lequel le defaut d'ADR-0016 etait entre. Chaque source declare
     * desormais sa reponse.</p>
     */
    boolean indicesNommentLaReponse();
}
