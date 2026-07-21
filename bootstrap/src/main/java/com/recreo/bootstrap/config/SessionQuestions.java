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

    private static int minFor(int floor, int max) {
        // Cas defensif (vivier < plancher) : le plancher ne peut depasser le plafond.
        return Math.min(floor, max);
    }

    private static int defaultFor(int def, int max) {
        return Math.min(def, max);
    }

    /** Cible resolue avec le plancher/defaut GLOBAUX (5 / 20). */
    static int resolve(String raw, int poolCap) {
        return resolve(raw, poolCap, MIN, DEFAULT);
    }

    /**
     * Cible resolue depuis le parametre brut, bornee a [min(floor,max), maxFor(poolCap)],
     * defaut = min(def,max). Le plancher et le defaut peuvent etre propres a un jeu (ex.
     * « Range les nombres » : defaut 3, plancher plus bas que le 5 global — cf. ADR-0014).
     */
    static int resolve(String raw, int poolCap, int floor, int def) {
        int max = maxFor(poolCap);
        int min = minFor(floor, max);
        int fallback = defaultFor(def, max);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            int requested = Integer.parseInt(raw.trim());
            return Math.max(min, Math.min(max, requested));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    /** Schema du champ avec plancher/defaut GLOBAUX. */
    static QuestionsSettingsView view(int poolCap) {
        return view(poolCap, MIN, DEFAULT);
    }

    /** Schema du champ (bornes + valeur pre-remplie), plancher/defaut propres au jeu. */
    static QuestionsSettingsView view(int poolCap, int floor, int def) {
        int max = maxFor(poolCap);
        return new QuestionsSettingsView(minFor(floor, max), max, defaultFor(def, max));
    }
}
