package com.recreo.adapter.web.contreexemple;

import com.recreo.adapter.content.YamlContentPackLoader;

/**
 * ADR-0003 : viole ADR-0001. Cette classe n'est jamais instanciee, elle existe pour etre refusee
 * par ArchitectureEnCouchesTest.lesAdaptateursNeSeConnaissentPas : un adaptateur qui depend d'un
 * autre adaptateur, alors que le cablage entre eux doit rester dans bootstrap.
 */
public final class AdaptateurWebDependantDuContenuContreExemple {

    public YamlContentPackLoader chargeur() {
        return null;
    }
}
