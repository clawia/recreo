package com.recreo.application.port.in;

import java.util.List;

/**
 * Schéma d'un réglage « choix » générique réglé par l'adulte : un champ à choisir parmi
 * quelques options (ex. sens du rangement, consigne plus grand/plus petit, inclure les
 * centaines…). Réutilisable par n'importe quel jeu (cf. ChoixGameFactory). {@code paramName}
 * est le nom du champ POST (doit correspondre à un {@code @RequestParam} du controller) ;
 * {@code selected} est la valeur pré-sélectionnée (défaut).
 */
public record ChoixSettingsView(String paramName, String legend,
                                List<ChoixOptionView> options, String selected) {
    public ChoixSettingsView {
        options = List.copyOf(options);
    }
}
