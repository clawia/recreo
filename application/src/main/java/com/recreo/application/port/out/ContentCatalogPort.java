package com.recreo.application.port.out;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.Matiere;

import java.util.List;

/**
 * Port sortant du catalogue. ADR-0007 : sa seule implementation est
 * com.recreo.adapter.content.YamlContentPackLoader.
 */
public interface ContentCatalogPort {

    List<GameDefinition> games();

    /**
     * ADR-0106 : une ligne par matiere servie, dans l'ordre d'affichage. La seule implementation la
     * redefinit ; la valeur par defaut ne sert qu'aux doubles de test ecrits en lambda, et un
     * regroupement par matiere sur un tel double echoue en nommant la matiere sans ligne.
     */
    default List<Matiere> matieres() {
        return List.of();
    }
}
