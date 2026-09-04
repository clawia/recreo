package com.recreo.domain.mechanics.contreexemple;

import com.recreo.domain.content.CadreDeZone;

/**
 * ADR-0003 : viole la clause 10 d'ADR-0080 en resolvant un point en zone - exactement la conception
 * par coordonnees que l'alternative 1 d'ADR-0080 ecarte : le client envoie {@code (x, y)}, la
 * mecanique cherche quelle zone contient ce point, en lisant directement {@code CadreDeZone}.
 *
 * <p>Existe pour etre refusee par
 * {@code DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaGeometrie}.</p>
 */
public class MecaniqueGeometriqueContreExemple {

    public boolean contient(CadreDeZone cadre, int x, int y) {
        return x >= cadre.x() && x < cadre.x() + cadre.largeur()
                && y >= cadre.y() && y < cadre.y() + cadre.hauteur();
    }
}
