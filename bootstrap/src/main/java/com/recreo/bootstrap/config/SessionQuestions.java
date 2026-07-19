package com.recreo.bootstrap.config;

import com.recreo.application.port.in.QuestionsSettingsView;

/**
 * Bornage du reglage commun "nombre de questions" (cf. ADR-0006). Regle par un
 * adulte, defaut 20, borne 5..50 ET jamais au-dela du vivier distinct pour un jeu a
 * liste finie ({@code poolCap} = taille du vivier, ou 50 pour un jeu combinatoire).
 * C'est le bootstrap (qui connait le vivier) qui resout et borne la cible, puis la
 * passe a la mecanique via {@code GameConfig.settings}.
 */
final class SessionQuestions {

    static final int MIN = 5;
    static final int ABSOLUTE_MAX = 50;
    static final int DEFAULT = 20;

    private SessionQuestions() {
    }

    /** Borne haute effective = min(50, vivier distinct). */
    static int maxFor(int poolCap) {
        return Math.min(ABSOLUTE_MAX, poolCap);
    }

    private static int minFor(int max) {
        // Cas defensif (vivier < 5) : le plancher ne peut depasser le plafond.
        return Math.min(MIN, max);
    }

    private static int defaultFor(int max) {
        return Math.min(DEFAULT, max);
    }

    /** Cible resolue depuis le parametre brut, bornee a [min, maxFor(poolCap)]. */
    static int resolve(String raw, int poolCap) {
        int max = maxFor(poolCap);
        int min = minFor(max);
        int def = defaultFor(max);
        if (raw == null || raw.isBlank()) {
            return def;
        }
        try {
            int requested = Integer.parseInt(raw.trim());
            return Math.max(min, Math.min(max, requested));
        } catch (NumberFormatException e) {
            return def;
        }
    }

    /** Schema du champ (bornes + valeur pre-remplie) pour l'ecran de reglages. */
    static QuestionsSettingsView view(int poolCap) {
        int max = maxFor(poolCap);
        return new QuestionsSettingsView(minFor(max), max, defaultFor(max));
    }
}
