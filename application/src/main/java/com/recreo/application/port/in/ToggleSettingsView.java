package com.recreo.application.port.in;

/**
 * Schéma d'un réglage « toggle » (case à cocher) réglé par l'adulte : activer/désactiver
 * une option d'un jeu (ex. « inclure les centaines » dans la décomposition). {@code paramName}
 * est le nom du champ POST (doit correspondre à un {@code @RequestParam} du controller) ;
 * {@code on} est l'état par défaut (pré-coché ou non).
 */
public record ToggleSettingsView(String paramName, String label, boolean on) {
}
