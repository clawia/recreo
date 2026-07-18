package com.recreo.bootstrap.config;

import com.recreo.domain.mechanic.MechanicId;

/**
 * Definition complete d'une entree de catalogue en bootstrap : ses metadonnees de
 * regroupement (matiere, niveau, jeu) et sa {@link GameFactory} (config
 * parametrable + schema de reglages). C'est le lieu ou une meme mecanique rencontre
 * plusieurs contenus/niveaux (preuve du decouplage).
 */
record CatalogEntryDef(
        String entryId,
        String matiere,
        String niveau,
        String gameKey,
        String gameLabel,
        String title,
        String description,
        MechanicId mechanicId,
        GameFactory factory) {
}
