package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.domain.mechanic.GameConfig;

import java.util.Map;

/**
 * Fabrique la {@link GameConfig} d'une entree de catalogue en appliquant des
 * reglages de session sur sa config de base, et expose le schema de reglages
 * (bornes + valeurs par defaut) propre au type de jeu. C'est le lieu du wiring
 * "reglages -> generateur configurable" : ni Spring ni JPA, seulement du domaine.
 */
interface GameFactory {

    /** Construit la config en appliquant {@code parameters} (vide = defaut). */
    GameConfig create(Map<String, String> parameters);

    /** Schema de reglages (propre au jeu) pour l'ecran de parametres. */
    GameSettingsView settings();
}
