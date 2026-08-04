package com.recreo.domain.contreexemple;

import com.recreo.application.catalog.GameDefinition;

/**
 * ADR-0003 : viole ADR-0001. Cette classe n'est jamais instanciee, elle existe pour etre refusee
 * par ArchitectureEnCouchesTest.lesDependancesVontVersLInterieur : la couche "Application" ne peut
 * etre accedee que par "Web", "Contenu" et "Bootstrap" ; le domaine, qui n'y figure pas, ne peut
 * pas dependre de l'application.
 */
public final class DomaineDependantDeLApplicationContreExemple {

    public GameDefinition definition() {
        return null;
    }
}
