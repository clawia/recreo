package com.recreo.application.usecase;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Cas d'usage du sprint 0 : demarrer une partie et jouer un coup. ADR-0002 : aucune annotation
 * de framework ici, aucune journalisation ; le cablage Spring vit dans bootstrap.
 */
public final class PartieService implements PartieUseCase {

    private final ContentCatalogPort catalogue;
    private final MechanicRegistryPort mecaniques;
    private final RandomSource hasard;

    public PartieService(ContentCatalogPort catalogue, MechanicRegistryPort mecaniques, RandomSource hasard) {
        this.catalogue = catalogue;
        this.mecaniques = mecaniques;
        this.hasard = hasard;
    }

    @Override
    public List<GameDefinition> jeuxDisponibles() {
        return catalogue.games();
    }

    @Override
    public GameSession demarrer(String jeuId) {
        GameDefinition jeu = trouver(jeuId);
        GameMechanic mecanique = mecaniques.mecaniquePour(jeu.mecanique());
        return mecanique.start(jeu.contenu(), hasard, jeu.nombreDeManches());
    }

    @Override
    public GameSession jouer(String jeuId, GameSession session, Move coup) {
        GameDefinition jeu = trouver(jeuId);
        GameMechanic mecanique = mecaniques.mecaniquePour(jeu.mecanique());
        return mecanique.apply(session, coup);
    }

    private GameDefinition trouver(String jeuId) {
        return catalogue.games().stream()
                .filter(jeu -> jeu.id().equals(jeuId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("jeu inconnu : " + jeuId));
    }
}
