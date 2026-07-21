package com.recreo.bootstrap.config;

import com.recreo.application.port.in.ChoixOptionView;
import com.recreo.application.port.in.ChoixSettingsView;
import com.recreo.application.port.in.GameSettingsView;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.MechanicId;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Fabrique GÉNÉRIQUE d'un jeu avec un réglage « choix » (cf. ADR-0014) : l'adulte
 * sélectionne une valeur (ex. sens du rangement, plus grand/plus petit) qui pilote la
 * construction du générateur. Le réglage commun « nombre de questions » est aussi exposé,
 * avec un plancher/défaut éventuellement propres au jeu (ex. défaut 3 pour « Range les
 * nombres »). Réutilisable par n'importe quelle mécanique (portée par {@code mechanicId}).
 */
final class ChoixGameFactory implements GameFactory {

    private final String entryId;
    private final String title;
    private final String gameKey;
    private final String niveau;
    private final MechanicId mechanicId;
    private final ChoixSettingsView schema;
    private final Function<String, ContentGenerator> generatorForChoice;
    private final int poolCap;
    private final int defaultQuestions;
    private final int questionsFloor;

    ChoixGameFactory(String entryId, String title, String gameKey, String niveau,
                     MechanicId mechanicId, ChoixSettingsView schema,
                     Function<String, ContentGenerator> generatorForChoice,
                     int poolCap, int defaultQuestions, int questionsFloor) {
        this.entryId = entryId;
        this.title = title;
        this.gameKey = gameKey;
        this.niveau = niveau;
        this.mechanicId = mechanicId;
        this.schema = schema;
        this.generatorForChoice = generatorForChoice;
        this.poolCap = poolCap;
        this.defaultQuestions = defaultQuestions;
        this.questionsFloor = questionsFloor;
    }

    @Override
    public GameConfig create(Map<String, String> parameters) {
        String choice = sanitize(parameters.get(schema.paramName()));
        ContentGenerator generator = generatorForChoice.apply(choice);

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.put("niveau", niveau);
        settings.put("questionsTarget", SessionQuestions.resolve(
                parameters.get("questionsTarget"), poolCap, questionsFloor, defaultQuestions));
        return new GameConfig(mechanicId, generator, settings);
    }

    @Override
    public GameSettingsView settings() {
        return new GameSettingsView(entryId, title, gameKey, niveau,
                null, null, SessionQuestions.view(poolCap, questionsFloor, defaultQuestions),
                List.of(schema));
    }

    /** Valeur reçue si c'est une option connue, sinon la valeur par défaut (repli sûr). */
    private String sanitize(String raw) {
        if (raw != null) {
            List<ChoixOptionView> options = schema.options();
            for (ChoixOptionView option : options) {
                if (option.value().equals(raw)) {
                    return raw;
                }
            }
        }
        return schema.selected();
    }
}
