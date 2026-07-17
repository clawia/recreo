package com.recreo.bootstrap.config;

import com.recreo.application.port.out.CatalogEntry;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.domain.content.impl.CalculMentalGenerator;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Catalogue en memoire construit a partir des {@link ContentPack} charges depuis
 * les YAML. C'est LE lieu ou la mecanique (une seule) rencontre plusieurs
 * contenus (CP, CM1) : preuve du decouplage.
 */
public final class InMemoryGameCatalog implements GameCatalog {

    private final Map<String, GameConfig> configsByEntry = new LinkedHashMap<>();
    private final Map<MechanicId, GameMechanic> mechanicsById = new LinkedHashMap<>();
    private final List<CatalogEntry> entries;

    public InMemoryGameCatalog(List<GameMechanic> mechanics, List<ContentPack> packs) {
        for (GameMechanic mechanic : mechanics) {
            mechanicsById.put(mechanic.id(), mechanic);
        }
        List<CatalogEntry> built = new java.util.ArrayList<>();
        for (ContentPack pack : packs) {
            MechanicId mechanicId = new MechanicId(pack.mechanic());
            if (!mechanicsById.containsKey(mechanicId)) {
                throw new IllegalStateException("Mecanique inconnue pour l'entree " + pack.entryId()
                        + " : " + pack.mechanic());
            }
            // La mecanique consomme l'abstraction ContentGenerator ; le generateur
            // concret (calcul mental) vit ici, en bootstrap, jamais dans la mecanique.
            CalculMentalGenerator generator = new CalculMentalGenerator(pack.difficulty(), pack.descriptor());
            GameConfig config = new GameConfig(mechanicId, generator, Map.of("niveau", pack.descriptor().niveau()));
            configsByEntry.put(pack.entryId(), config);
            built.add(new CatalogEntry(pack.entryId(), pack.title(), pack.description(), mechanicId));
        }
        this.entries = List.copyOf(built);
    }

    @Override
    public GameConfig configFor(String entryId) {
        GameConfig config = configsByEntry.get(entryId);
        if (config == null) {
            throw new IllegalArgumentException("Entree de catalogue inconnue : " + entryId);
        }
        return config;
    }

    @Override
    public GameMechanic mechanicFor(MechanicId mechanicId) {
        GameMechanic mechanic = mechanicsById.get(mechanicId);
        if (mechanic == null) {
            throw new IllegalArgumentException("Mecanique inconnue : " + mechanicId);
        }
        return mechanic;
    }

    @Override
    public List<CatalogEntry> entries() {
        return entries;
    }
}
