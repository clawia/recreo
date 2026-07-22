package com.recreo.application.port.in;

/**
 * Schema du reglage « nombre de coeurs » (jokers) d'une session, reglable par un adulte
 * (cf. DEC-0013 / s21). {@code defaultValue} = capital par defaut du jeu (issu du YAML ou
 * de la mecanique) ; {@code min}/{@code max} = bornes admissibles (ex. 0..6).
 */
public record JokersSettingsView(int min, int max, int defaultValue) {
}
