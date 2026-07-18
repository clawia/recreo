package com.recreo.bootstrap.config;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.out.CatalogEntry;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Catalogue en memoire construit a partir de {@link CatalogEntryDef}. C'est LE
 * lieu ou la mecanique (une seule) rencontre plusieurs contenus (CP, CM1, francais)
 * : preuve du decouplage. La config d'une entree est fabriquee A LA DEMANDE par sa
 * {@link GameFactory} en appliquant d'eventuels reglages de session.
 */
public final class InMemoryGameCatalog implements GameCatalog {

    private final Map<String, CatalogEntryDef> defsByEntry = new LinkedHashMap<>();
    private final Map<MechanicId, GameMechanic> mechanicsById = new LinkedHashMap<>();
    private final List<CatalogEntry> entries;

    InMemoryGameCatalog(List<GameMechanic> mechanics, List<CatalogEntryDef> defs) {
        for (GameMechanic mechanic : mechanics) {
            mechanicsById.put(mechanic.id(), mechanic);
        }
        List<CatalogEntry> built = new ArrayList<>();
        for (CatalogEntryDef def : defs) {
            requireMechanic(def.mechanicId(), def.entryId());
            defsByEntry.put(def.entryId(), def);
            built.add(new CatalogEntry(
                    def.entryId(), def.matiere(), def.niveau(), def.gameKey(),
                    def.gameLabel(), def.title(), def.description(), def.mechanicId()));
        }
        this.entries = List.copyOf(built);
    }

    private void requireMechanic(MechanicId mechanicId, String entryId) {
        if (!mechanicsById.containsKey(mechanicId)) {
            throw new IllegalStateException("Mecanique inconnue pour l'entree " + entryId + " : " + mechanicId);
        }
    }

    @Override
    public GameConfig configFor(String entryId, Map<String, String> parameters) {
        return def(entryId).factory().create(parameters);
    }

    @Override
    public GameSettingsView settingsFor(String entryId) {
        return def(entryId).factory().settings();
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

    private CatalogEntryDef def(String entryId) {
        CatalogEntryDef def = defsByEntry.get(entryId);
        if (def == null) {
            throw new IllegalArgumentException("Entree de catalogue inconnue : " + entryId);
        }
        return def;
    }
}
