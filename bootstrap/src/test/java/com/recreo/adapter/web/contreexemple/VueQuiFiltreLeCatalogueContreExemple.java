package com.recreo.adapter.web.contreexemple;

import com.recreo.application.catalog.GameDefinition;

import java.util.List;

/**
 * ADR-0003 : viole ADR-0044. Cette classe n'est jamais instanciee ; elle existe pour etre refusee
 * par {@code LeChoixDesJeuxResteDerriereLePortTest.aucunAdaptateurDeRenduNeLitLeDescripteurDeContenu}.
 *
 * <p>Elle est ecrite comme le chemin le moins cher l'ecrirait vraiment : l'adaptateur web recoit le
 * catalogue entier par le port, puis decide lui-meme quels jeux montrer en lisant le descripteur.
 * C'est la selection par niveau que S14-1 demande, mise du mauvais cote de la frontiere.
 */
public final class VueQuiFiltreLeCatalogueContreExemple {

    public List<GameDefinition> jeuxDuNiveau(List<GameDefinition> catalogue, String niveau) {
        return catalogue.stream()
                .filter(jeu -> jeu.descripteur().niveau().equals(niveau))
                .toList();
    }
}
