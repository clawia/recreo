package com.recreo.adapter.web;

/**
 * Les deux seules situations que la frontiere HTTP sait prevoir : la session ne porte plus la
 * partie demandee, et l'identifiant de jeu ne designe aucune entree de catalogue.
 *
 * <p>ADR-0018 : ce type est declare ici et nulle part ailleurs, et rien hors de recreo ne peut le
 * lever. C'est la seule facon qu'un canal reserve a l'attendu ne soit pas emprunte par autre chose.
 * Les deux types du JDK qui tenaient ce role auparavant, {@code IllegalStateException} et
 * {@code NoSuchElementException}, laissaient passer tout leur sous-arbre : c'est par la qu'un
 * defaut de decodage leve par le conteneur s'est fait passer pour une session perdue, sans une
 * ligne de journal.</p>
 */
public final class PartieIndisponible extends RuntimeException {

    public PartieIndisponible(String message) {
        super(message);
    }
}
