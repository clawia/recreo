package com.recreo.application.port.in;

import java.util.List;

/**
 * Reglages du jeu de syllabes : le nombre de syllabes max propose (options
 * possibles + valeur selectionnee par defaut).
 */
public record SyllabesSettingsView(List<Integer> options, int selected) {

    public SyllabesSettingsView {
        options = List.copyOf(options);
    }
}
