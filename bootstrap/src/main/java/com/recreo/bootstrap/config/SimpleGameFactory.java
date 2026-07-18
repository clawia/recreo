package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.MechanicId;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Fabrique d'un jeu SANS reglage : le generateur ne prend aucun parametre de
 * session. {@link #create(Map)} ignore les parametres et {@link #settings()}
 * renvoie une vue « aucun reglage » (les deux blocs nuls) — le front n'affiche donc
 * pas de bouton « Paramètres » pour ces jeux (cf. US-s04). Reutilise la meme
 * mecanique bandit manchot que les autres entrees.
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
        // Aucun reglage : les parametres eventuels sont ignores.
        return new GameConfig(mechanicId, generatorSupplier.get(), Map.of("niveau", niveau));
    }

    @Override
    public GameSettingsView settings() {
        return GameSettingsView.none(entryId, title, gameKey, niveau);
    }
}
