package com.recreo.domain.content;

/**
 * ADR-0003 : viole ADR-0004 (1). Pendant exact de MoteurPartageContreExemple pour le contrat de
 * contenu : une classe porteuse de comportement hissee dans le contrat, ou toutes les matieres la
 * partageraient. Existe pour etre refuse par
 * DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme.
 *
 * <p>Sans champ, pour ne violer que cette regle : un champ non textuel ferait aussi echouer
 * ADR-0006.</p>
 */
public class MoteurDeContenuPartageContreExemple {

    public String enonceNormalise(String enonce) {
        return enonce.strip();
    }
}
