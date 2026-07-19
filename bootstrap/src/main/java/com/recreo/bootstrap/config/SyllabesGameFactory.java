package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.in.SyllabesSettingsView;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.impl.SyllabesCpGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.MechanicId;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Fabrique le generateur de syllabes : applique le reglage {@code maxSyllabes}
 * (borne dans [{@code minSyllabes}, {@code maxSyllabes}]) qui filtre la liste de
 * mots. Aucun parametre = pas de filtre (comportement s02).
 */
final class SyllabesGameFactory implements GameFactory {

    private final String entryId;
    private final String title;
    private final String gameKey;
    private final MechanicId mechanicId;
    private final ContentDescriptor descriptor;
    private final List<Integer> options;
    private final int minSyllabes;
    private final int maxSyllabes;

    SyllabesGameFactory(String entryId, String title, String gameKey, MechanicId mechanicId,
                        ContentDescriptor descriptor, List<Integer> options,
                        int minSyllabes, int maxSyllabes) {
        this.entryId = entryId;
        this.title = title;
        this.gameKey = gameKey;
        this.mechanicId = mechanicId;
        this.descriptor = descriptor;
        this.options = List.copyOf(options);
        this.minSyllabes = minSyllabes;
        this.maxSyllabes = maxSyllabes;
    }

    @Override
    public GameConfig create(Map<String, String> parameters) {
        String raw = parameters.get("maxSyllabes");
        Integer max = null;
        if (raw != null && !raw.isBlank()) {
            try {
                int requested = Integer.parseInt(raw.trim());
                max = Math.max(minSyllabes, Math.min(maxSyllabes, requested));
            } catch (NumberFormatException ignored) {
                max = null; // reglage illisible : aucun filtre.
            }
        }
        Map<String, Object> settings = new LinkedHashMap<>();
        settings.put("niveau", descriptor.niveau());
        settings.put("questionsTarget", SessionQuestions.resolve(parameters.get("questionsTarget"), fullPoolCap()));
        return new GameConfig(mechanicId, new SyllabesCpGenerator(descriptor, max), settings);
    }

    @Override
    public GameSettingsView settings() {
        // Defaut = plus grande valeur proposee (pas de filtre effectif).
        int selected = options.get(options.size() - 1);
        return new GameSettingsView(
                entryId, title, gameKey, descriptor.niveau(),
                null,
                new SyllabesSettingsView(options, selected),
                SessionQuestions.view(fullPoolCap()));
    }

    /** Vivier distinct plein (sans filtre maxSyllabes) : plafonne "nombre de questions". */
    private int fullPoolCap() {
        return new SyllabesCpGenerator(descriptor, null)
                .distinctPoolSize().orElse(SessionQuestions.ABSOLUTE_MAX);
    }
}
