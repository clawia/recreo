package com.recreo.application.port.in;

import java.util.List;

/**
 * Schema des reglages proposes pour une entree de catalogue. Le bloc
 * {@code questions} (nombre de questions par session) est present pour TOUS les
 * jeux (cf. ADR-0006). Au plus un des blocs specifiques ({@code math},
 * {@code syllabes}) est non nul selon le jeu ; les bornes portees par ces blocs
 * empechent un reglage hors-programme. {@code toggles} porte 0..n cases a cocher
 * (ex. « inclure les centaines » de la decomposition — cf. DEC-0011).
 */
public record GameSettingsView(
        String entryId,
        String title,
        String gameKey,
        String niveau,
        MathSettingsView math,
        SyllabesSettingsView syllabes,
        QuestionsSettingsView questions,
        List<ToggleSettingsView> toggles) {

    public GameSettingsView {
        toggles = toggles == null ? List.of() : List.copyOf(toggles);
    }

    /**
     * Vrai si l'entree propose des reglages. Depuis s05, TOUS les jeux ont au moins
     * le reglage "nombre de questions" ({@code questions} non nul) : le bouton
     * « Paramètres » s'affiche donc pour chaque jeu. (Le capital de coeurs n'est PAS
     * reglable ici : il est fixe par le YAML du jeu, cf. DEC-0013/s21.)
     */
    public boolean hasSettings() {
        return questions != null || math != null || syllabes != null || !toggles.isEmpty();
    }
}
