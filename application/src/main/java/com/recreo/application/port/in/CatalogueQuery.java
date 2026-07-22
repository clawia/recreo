package com.recreo.application.port.in;

import java.util.List;

/**
 * Use case (lecture) : interroger le catalogue de jeux selon les deux axes de
 * navigation (par niveau / par jeu) et exposer le schema de reglages d'une entree.
 */
public interface CatalogueQuery {

    /** Toutes les entrees du catalogue. */
    List<CatalogEntryView> entries();

    /** Les niveaux distincts (ex. CP, CM1), dans l'ordre du catalogue. */
    List<String> niveaux();

    /** Les jeux distincts (par {@code gameKey}) avec leur libelle. */
    List<GameSummaryView> games();

    /** Les matieres distinctes (ex. Mathematiques, Francais), dans l'ordre du catalogue. */
    List<String> matieres();

    /** Les entrees d'un niveau donne. */
    List<CatalogEntryView> byNiveau(String niveau);

    /** Les entrees d'une matiere donnee (3e axe de navigation). */
    List<CatalogEntryView> byMatiere(String matiere);

    /** Les entrees d'un jeu donne (une par niveau ou ce jeu existe). */
    List<CatalogEntryView> byGameKey(String gameKey);

    /** Le schema de reglages (propre au type de jeu) d'une entree. */
    GameSettingsView settingsFor(String entryId);
}
