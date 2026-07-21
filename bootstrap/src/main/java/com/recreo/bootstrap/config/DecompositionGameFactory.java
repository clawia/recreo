package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.in.ToggleSettingsView;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.DecompositionGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.MechanicId;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Fabrique le jeu « Décompose le nombre » (bandit manchot) avec un réglage TOGGLE
 * « inclure les centaines » (cf. DEC-0011) : décoché → nombres à 2 chiffres
 * (dizaines/unités) ; coché → 3 chiffres (+ centaines). Le défaut ({@code defaultCentaines})
 * dépend du niveau (CP : décoché ; CM1 : coché). Le réglage commun « nombre de questions »
 * est aussi exposé.
 */
final class DecompositionGameFactory implements GameFactory {

    private final String entryId;
    private final String title;
    private final String gameKey;
    private final MechanicId mechanicId;
    private final ContentDescriptor descriptor;
    private final boolean defaultCentaines;

    DecompositionGameFactory(String entryId, String title, String gameKey, MechanicId mechanicId,
                             ContentDescriptor descriptor, boolean defaultCentaines) {
        this.entryId = entryId;
        this.title = title;
        this.gameKey = gameKey;
        this.mechanicId = mechanicId;
        this.descriptor = descriptor;
        this.defaultCentaines = defaultCentaines;
    }

    @Override
    public GameConfig create(Map<String, String> parameters) {
        // Param present (via le formulaire) -> valeur explicite ; absent (jeu lancé sans
        // ouvrir les réglages) -> défaut du niveau.
        boolean inclureCentaines = parameters.containsKey("centaines")
                ? "true".equals(parameters.get("centaines"))
                : defaultCentaines;

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.put("niveau", descriptor.niveau());
        // Générateur combinatoire -> plafond de session = 50.
        settings.put("questionsTarget",
                SessionQuestions.resolve(parameters.get("questionsTarget"), SessionQuestions.ABSOLUTE_MAX));
        return new GameConfig(mechanicId, new DecompositionGenerator(descriptor, inclureCentaines), settings);
    }

    @Override
    public GameSettingsView settings() {
        return new GameSettingsView(entryId, title, gameKey, descriptor.niveau(),
                null, null, SessionQuestions.view(SessionQuestions.ABSOLUTE_MAX),
                List.of(new ToggleSettingsView("centaines", "Inclure les centaines", defaultCentaines)));
    }
}
