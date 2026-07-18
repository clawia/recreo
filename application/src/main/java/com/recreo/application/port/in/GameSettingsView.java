package com.recreo.application.port.in;

/**
 * Schema des reglages proposes pour une entree de catalogue, propre au type de
 * jeu (cf. US-s03-2). Exactement UN des deux blocs ({@code math}, {@code syllabes})
 * est non nul selon le jeu ; l'autre est {@code null}. Les bornes portees par ces
 * blocs empechent un reglage hors-programme.
 */
public record GameSettingsView(
        String entryId,
        String title,
        String gameKey,
        String niveau,
        MathSettingsView math,
        SyllabesSettingsView syllabes) {

    /**
     * Vrai si l'entree propose des reglages (au moins un bloc non nul). Les jeux
     * sans parametre reglable renvoient une vue « aucun reglage » (les deux blocs
     * nuls) : le front n'affiche alors pas de bouton « Paramètres » (cf. US-s04).
     */
    public boolean hasSettings() {
        return math != null || syllabes != null;
    }

    /** Vue « aucun reglage » pour un jeu sans parametre reglable. */
    public static GameSettingsView none(String entryId, String title, String gameKey, String niveau) {
        return new GameSettingsView(entryId, title, gameKey, niveau, null, null);
    }
}
