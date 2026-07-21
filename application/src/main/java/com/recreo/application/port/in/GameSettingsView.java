package com.recreo.application.port.in;

import java.util.List;

/**
 * Schema des reglages proposes pour une entree de catalogue. Le bloc
 * {@code questions} (nombre de questions par session) est present pour TOUS les
 * jeux (cf. ADR-0006). Au plus un des blocs specifiques ({@code math},
 * {@code syllabes}) est non nul selon le jeu ; les bornes portees par ces blocs
 * empechent un reglage hors-programme. {@code choix} porte 0..n reglages « choix »
 * generiques (sens du tri, consigne plus grand/plus petit, ... — cf. ADR-0014).
 */
public record GameSettingsView(
        String entryId,
        String title,
        String gameKey,
        String niveau,
        MathSettingsView math,
        SyllabesSettingsView syllabes,
        QuestionsSettingsView questions,
        List<ChoixSettingsView> choix) {

    public GameSettingsView {
        choix = choix == null ? List.of() : List.copyOf(choix);
    }

    /**
     * Vrai si l'entree propose des reglages. Depuis s05, TOUS les jeux ont au moins
     * le reglage "nombre de questions" ({@code questions} non nul) : le bouton
     * « Paramètres » s'affiche donc pour chaque jeu.
     */
    public boolean hasSettings() {
        return questions != null || math != null || syllabes != null || !choix.isEmpty();
    }
}
