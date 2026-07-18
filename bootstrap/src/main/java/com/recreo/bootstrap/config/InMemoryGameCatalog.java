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
        this(mechanics, packs, List.of());
    }

    /**
     * @param coded entrees cablees en code (contenus dont la difficulte n'est pas
     *              pilotable par le YAML maths, ex. francais-cp) : leur
     *              {@link GameConfig} est deja construit par l'appelant (bootstrap).
     */
    public InMemoryGameCatalog(List<GameMechanic> mechanics, List<ContentPack> packs, List<CodedEntry> coded) {
        for (GameMechanic mechanic : mechanics) {
            mechanicsById.put(mechanic.id(), mechanic);
        }
        List<CatalogEntry> built = new java.util.ArrayList<>();
        for (ContentPack pack : packs) {
            MechanicId mechanicId = new MechanicId(pack.mechanic());
            requireMechanic(mechanicId, pack.entryId());
            // La mecanique consomme l'abstraction ContentGenerator ; le generateur
            // concret (calcul mental) vit ici, en bootstrap, jamais dans la mecanique.
            CalculMentalGenerator generator = new CalculMentalGenerator(pack.difficulty(), pack.descriptor());
            GameConfig config = new GameConfig(mechanicId, generator, Map.of("niveau", pack.descriptor().niveau()));
            configsByEntry.put(pack.entryId(), config);
            built.add(new CatalogEntry(pack.entryId(), pack.title(), pack.description(), mechanicId));
        }
        for (CodedEntry entry : coded) {
            MechanicId mechanicId = entry.config().mechanic();
            requireMechanic(mechanicId, entry.entryId());
            configsByEntry.put(entry.entryId(), entry.config());
            built.add(new CatalogEntry(entry.entryId(), entry.title(), entry.description(), mechanicId));
        }
        this.entries = List.copyOf(built);
    }

    private void requireMechanic(MechanicId mechanicId, String entryId) {
        if (!mechanicsById.containsKey(mechanicId)) {
            throw new IllegalStateException("Mecanique inconnue pour l'entree " + entryId + " : " + mechanicId);
        }
    }

    /** Entree de catalogue cablee en code, avec sa {@link GameConfig} deja assemblee. */
    public record CodedEntry(String entryId, String title, String description, GameConfig config) {
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
