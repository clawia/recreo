package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.MechanicId;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Fabrique d'un jeu sans reglage SPECIFIQUE (ni math ni syllabes) : le generateur
 * n'attend aucun parametre de contenu. Depuis s05, ces jeux ont neanmoins le
 * reglage COMMUN « nombre de questions » (cf. ADR-0006) : {@link #settings()}
 * expose donc un bloc {@code questions} (borne par le vivier du generateur) et le
 * bouton « Paramètres » s'affiche. Reutilise la meme mecanique bandit manchot.
 */
final class SimpleGameFactory implements GameFactory {

    private final String entryId;
    private final String title;
    private final String gameKey;
    private final String niveau;
    private final MechanicId mechanicId;
    private final Supplier<ContentGenerator> generatorSupplier;

    SimpleGameFactory(String entryId, String title, String gameKey, String niveau,
                      MechanicId mechanicId, Supplier<ContentGenerator> generatorSupplier) {
        this.entryId = entryId;
        this.title = title;
        this.gameKey = gameKey;
        this.niveau = niveau;
        this.mechanicId = mechanicId;
        this.generatorSupplier = generatorSupplier;
    }

    @Override
    public GameConfig create(Map<String, String> parameters) {
        ContentGenerator generator = generatorSupplier.get();
        int poolCap = generator.distinctPoolSize().orElse(SessionQuestions.ABSOLUTE_MAX);

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.put("niveau", niveau);
        // Seul reglage applicable : le nombre de questions (les parametres de contenu
        // eventuels sont ignores).
        settings.put("questionsTarget", SessionQuestions.resolve(parameters.get("questionsTarget"), poolCap));
        return new GameConfig(mechanicId, generator, settings);
    }

    @Override
    public GameSettingsView settings() {
        int poolCap = generatorSupplier.get().distinctPoolSize().orElse(SessionQuestions.ABSOLUTE_MAX);
        return new GameSettingsView(entryId, title, gameKey, niveau,
                null, null, SessionQuestions.view(poolCap), java.util.List.of());
    }
}
