package com.recreo.application.port.in;

/**
 * Une option d'un réglage « choix » (cf. {@link ChoixSettingsView}) : la valeur envoyée
 * au serveur ({@code value}) et le libellé affiché à l'adulte ({@code label}).
 */
public record ChoixOptionView(String value, String label) {
}
