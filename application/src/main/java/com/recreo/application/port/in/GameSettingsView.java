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
        JokersSettingsView jokers,
        List<ToggleSettingsView> toggles) {

    public GameSettingsView {
        toggles = toggles == null ? List.of() : List.copyOf(toggles);
    }

    /**
     * Constructeur historique (avant le reglage « nombre de coeurs », cf. DEC-0013/s21) :
     * pas de bloc {@code jokers}. Conserve la compatibilite des fabriques existantes.
     */
    public GameSettingsView(String entryId, String title, String gameKey, String niveau,
                            MathSettingsView math, SyllabesSettingsView syllabes,
                            QuestionsSettingsView questions, List<ToggleSettingsView> toggles) {
        this(entryId, title, gameKey, niveau, math, syllabes, questions, null, toggles);
    }

    /**
     * Vrai si l'entree propose des reglages. Depuis s05, TOUS les jeux ont au moins
     * le reglage "nombre de questions" ({@code questions} non nul) : le bouton
     * « Paramètres » s'affiche donc pour chaque jeu.
     */
    public boolean hasSettings() {
        return questions != null || math != null || syllabes != null || jokers != null || !toggles.isEmpty();
    }
}
