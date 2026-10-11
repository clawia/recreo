package com.recreo.application.usecase;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.application.catalog.Matiere;
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
import java.util.Optional;
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
     * <p>ADR-0106 : l'ordre des groupes est le rang de leur ligne dans la donnee de matiere (APM-4),
     * jamais un ordre ecrit en code (ADR-0006) ; les jeux d'un groupe gardent l'ordre du catalogue.
     * Une matiere servie sans ligne n'a pas de repli : elle fait echouer le regroupement en se
     * nommant, parce qu'un libelle invente n'a pas le droit d'etre servi (APM-1).
     */
    @Override
    public List<GroupeDeJeux> matieresDuNiveau(String niveau) {
        Map<String, List<GameDefinition>> parMatiere = new LinkedHashMap<>();
        for (GameDefinition jeu : jeuxDuNiveau(niveau)) {
            parMatiere.computeIfAbsent(jeu.descripteur().matiere(), matiere -> new ArrayList<>()).add(jeu);
        }
        List<Matiere> lignes = catalogue.matieres();
        List<String> ordre = lignes.stream().map(Matiere::nom).toList();
        for (String matiere : parMatiere.keySet()) {
            if (!ordre.contains(matiere)) {
                throw new IllegalStateException("ADR-0106 : la matiere \"" + matiere
                        + "\" est servie sans ligne dans la donnee de matiere");
            }
        }
        return lignes.stream()
                .filter(ligne -> parMatiere.containsKey(ligne.nom()))
                .map(ligne -> new GroupeDeJeux(ligne, parMatiere.get(ligne.nom())))
                .toList();
    }

    /**
     * ADR-0106 decision 5 : le segment d'adresse est resolu ici, sur la ligne de matiere qui le porte,
     * jamais compare a la matiere du paquet ni traduit par l'adaptateur. Un segment sans ligne, ou
     * une matiere sans jeu a ce niveau, rend un {@code Optional} vide : l'adresse vient d'un enfant ou
     * d'un favori, pas d'un defaut de la donnee.
     */
    @Override
    public Optional<GroupeDeJeux> groupeDeLaMatiere(String niveau, String segment) {
        return catalogue.matieres().stream()
                .filter(ligne -> ligne.segment().equals(segment))
                .findFirst()
                .flatMap(ligne -> {
                    List<GameDefinition> jeux = jeuxDuNiveau(niveau).stream()
                            .filter(jeu -> jeu.descripteur().matiere().equals(ligne.nom()))
                            .toList();
                    return jeux.isEmpty() ? Optional.empty() : Optional.of(new GroupeDeJeux(ligne, jeux));
                });
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
