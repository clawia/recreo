package com.recreo.application.usecase;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.application.catalog.OrdreDesNiveaux;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * ADR-0044 : la selection vit ici, derriere le port entrant, jamais dans l'adaptateur de
     * rendu - c'est cette methode, et rien d'autre, qui dit combien de points d'entree l'en-tete
     * porte et dans quel ordre.
     *
     * <p>ADR-0095, DEC-0090 : les valeurs distinctes ne sont plus rendues dans l'ordre du
     * catalogue mais dans celui de {@link OrdreDesNiveaux#SEQUENCE}. Le tri de {@link List} est
     * stable, donc deux niveaux de meme rang - le cas de repli, qu'aucun catalogue servi ne peut
     * atteindre - conservent entre eux l'ordre du catalogue.
     */
    @Override
    public List<String> niveauxDuCatalogue() {
        List<String> niveaux = new ArrayList<>();
        for (GameDefinition jeu : catalogue.games()) {
            String niveau = jeu.descripteur().niveau();
            if (!niveaux.contains(niveau)) {
                niveaux.add(niveau);
            }
        }
        niveaux.sort(Comparator.comparingInt(OrdreDesNiveaux::rang));
        return niveaux;
    }

    @Override
    public List<GameDefinition> jeuxDuNiveau(String niveau) {
        return catalogue.games().stream()
                .filter(jeu -> jeu.descripteur().niveau().equals(niveau))
                .toList();
    }

    /**
     * ADR-0105 : le regroupement vit ici, derriere le port entrant, parce que la matiere se lit sur
     * le descripteur et que l'adaptateur de rendu n'y a pas acces (ADR-0044). La matiere est la
     * chaine du paquet, comparee telle quelle : une graphie differente fait un groupe de plus, visible,
     * plutot qu'une normalisation silencieuse.
     *
     * <p>L'ordre des groupes est celui de la premiere apparition de la matiere au catalogue, parmi les
     * jeux du niveau, et l'ordre des jeux d'un groupe est celui du catalogue : deterministe, sans
     * ordre de matiere ecrit en code (ADR-0006). C'est un ordre de repli : la regle d'affichage est
     * a arbitrer par nadia-prof.
     */
    @Override
    public List<GroupeDeJeux> matieresDuNiveau(String niveau) {
        Map<String, List<GameDefinition>> parMatiere = new LinkedHashMap<>();
        for (GameDefinition jeu : jeuxDuNiveau(niveau)) {
            parMatiere.computeIfAbsent(jeu.descripteur().matiere(), matiere -> new ArrayList<>()).add(jeu);
        }
        return parMatiere.entrySet().stream()
                .map(entree -> new GroupeDeJeux(entree.getKey(), entree.getValue()))
                .toList();
    }

    @Override
    public List<GameDefinition> jeuxDeLaMatiere(String niveau, String matiere) {
        return jeuxDuNiveau(niveau).stream()
                .filter(jeu -> jeu.descripteur().matiere().equals(matiere))
                .toList();
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
