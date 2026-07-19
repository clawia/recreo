package com.recreo.application.port.in;

/**
 * Schema des reglages proposes pour une entree de catalogue. Le bloc
 * {@code questions} (nombre de questions par session) est present pour TOUS les
 * jeux (cf. ADR-0006). Au plus un des blocs specifiques ({@code math},
 * {@code syllabes}) est non nul selon le jeu ; les bornes portees par ces blocs
 * empechent un reglage hors-programme.
 */
public record GameSettingsView(
        String entryId,
        String title,
        String gameKey,
        String niveau,
        MathSettingsView math,
        SyllabesSettingsView syllabes,
        QuestionsSettingsView questions) {

    /**
     * Vrai si l'entree propose des reglages. Depuis s05, TOUS les jeux ont au moins
     * le reglage "nombre de questions" ({@code questions} non nul) : le bouton
     * « Paramètres » s'affiche donc pour chaque jeu.
     */
    public boolean hasSettings() {
        return questions != null || math != null || syllabes != null;
    }
}
