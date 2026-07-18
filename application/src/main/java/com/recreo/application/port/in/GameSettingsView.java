package com.recreo.application.port.in;

/**
 * Schema des reglages proposes pour une entree de catalogue, propre au type de
 * jeu (cf. US-s03-2). Exactement UN des deux blocs ({@code math}, {@code syllabes})
 * est non nul selon le jeu ; l'autre est {@code null}. Les bornes portees par ces
 * blocs empechent un reglage hors-programme.
 */
public record GameSettingsView(
        String entryId,
        String title,
        String gameKey,
        String niveau,
        MathSettingsView math,
        SyllabesSettingsView syllabes) {
}
