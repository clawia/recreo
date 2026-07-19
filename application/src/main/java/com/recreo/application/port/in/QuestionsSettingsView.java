package com.recreo.application.port.in;

/**
 * Reglage commun a TOUS les jeux (cf. ADR-0006) : le nombre de questions d'une
 * session, regle par un adulte. {@code defaultValue} est la valeur pre-remplie
 * (defaut 20, plafonnee au vivier) ; {@code min}/{@code max} sont les bornes du
 * champ (5..50, {@code max} jamais au-dela du vivier distinct pour un jeu a liste
 * finie).
 */
public record QuestionsSettingsView(int min, int max, int defaultValue) {
}
