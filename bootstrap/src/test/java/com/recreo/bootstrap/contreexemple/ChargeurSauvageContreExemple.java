package com.recreo.bootstrap.contreexemple;

import java.nio.file.Path;

/**
 * ADR-0003 : viole ADR-0007. Le dixieme chargeur de la version precedente, celui qui relit un
 * fichier depuis bootstrap au lieu de passer par l'adaptateur de contenu. Existe pour etre refuse
 * par ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers.
 */
public final class ChargeurSauvageContreExemple {

    public Path fichierDeContenu(String nom) {
        return Path.of("contenu", nom);
    }
}
