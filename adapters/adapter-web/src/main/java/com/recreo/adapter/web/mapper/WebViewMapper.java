package com.recreo.adapter.web.mapper;

import com.recreo.adapter.web.view.CatalogItemView;
import com.recreo.adapter.web.view.GameView;
import com.recreo.application.port.in.CatalogEntryView;
import com.recreo.application.port.in.MoveOutcomeView;
import com.recreo.application.port.in.SessionView;
import com.recreo.domain.mechanic.ArrangementBoard;
import com.recreo.domain.mechanic.AssociationBoard;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Traduit les vues de l'application vers les view-models web. */
public class WebViewMapper {

    private static final String FINISHED = "FINISHED";

    /** Vue de la mecanique glisser-deposer (cf. {@code GlisserDeposerMechanic.VIEW}). */
    private static final String GLISSER_DEPOSER = "glisserdeposer";

    /** Vue de la mecanique puzzle (cf. {@code PuzzleMechanic.VIEW}) : meme plateau d'arrangement. */
    private static final String PUZZLE = "puzzle";

    /** Vue de la mecanique droite graduee (cf. {@code DroiteGradueeMechanic.VIEW}). */
    private static final String DROITE_GRADUEE = "droitegraduee";

    /** Vue de la mecanique associer/classer (cf. {@code AssocierMechanic.VIEW}). */
    private static final String ASSOCIER = "associer";

    /** Vue de la mecanique memory (cf. {@code MemoryMechanic.VIEW}). */
    private static final String MEMORY = "memory";

    /** Separateur canonique de l'ordre encode (cf. {@code Item.encodeArrangement}). */
    private static final String ARRANGEMENT_SEPARATOR = ", ";

    /** Separateur des listes de meta (cibles / groupes de paires) — cf. les generateurs. */
    private static final String META_SEPARATOR = "|";

    public List<CatalogItemView> toCatalogItems(List<CatalogEntryView> entries) {
        return entries.stream()
                .map(e -> new CatalogItemView(
                        e.entryId(), e.matiere(), e.niveau(), e.gameKey(), e.gameLabel(),
                        e.title(), e.description(), e.hasSettings()))
                .toList();
    }

    public GameView toGameView(SessionView view) {
        GameView game = GameView.initial(
                view.sessionId(),
                view.mechanicView(),
                view.catalogEntryId(),
                view.reelSymbols(),
                view.question(),
                view.options(),
                view.score(),
                view.round(),
                view.answered(),
                view.questionsTarget(),
                FINISHED.equals(view.status()),
                view.jokers(),
                view.triedWrong());
        // Nouveau plateau d'arrangement (glisser-deposer ET puzzle) : emplacements vides, tuiles en reserve.
        if (GLISSER_DEPOSER.equals(view.mechanicView()) || PUZZLE.equals(view.mechanicView())) {
            ArrangementBoard board = ArrangementBoard.initial(view.reelSymbols());
            GameView withBoard = game.withArrangementBoard(board.slots(), board.reserve());
            return PUZZLE.equals(view.mechanicView())
                    ? withBoard.withHint(safe(view.meta()).get("indice"))
                    : withBoard;
        }
        if (DROITE_GRADUEE.equals(view.mechanicView())) {
            return game.withLine(intMeta(view.meta(), "min", 0), intMeta(view.meta(), "max", 10));
        }
        return withAssociations(game, view.mechanicView(), view.reelSymbols(), view.meta());
    }

    /** Ancienne surcharge : aucun arrangement soumis -> plateau initial pour glisser-deposer. */
    public GameView toGameView(MoveOutcomeView view) {
        return toGameView(view, null, null);
    }

    /** Surcharge sans associations (glisser-deposer / jeux a clic). */
    public GameView toGameView(MoveOutcomeView view, List<String> submittedArrangement) {
        return toGameView(view, submittedArrangement, null);
    }

    /**
     * Surcharge glisser-deposer : re-rend le plateau en tenant compte de l'arrangement SOUMIS,
     * pour GARDER en place les tuiles deja bien positionnees sur un essai faux (UX « ranger »,
     * DEC-0008), sans reveler la bonne reponse.
     *
     * <p>Pour cette mecanique, {@code view.options()} porte UN element : l'ordre attendu encode
     * canoniquement (cf. {@code Item.encodeArrangement} ; le generateur ne produit aucun
     * distracteur). C'est la seule source de l'ordre attendu cote vue.</p>
     */
    public GameView toGameView(MoveOutcomeView view, List<String> submittedArrangement,
                               Map<String, String> submittedAssociations) {
        GameView game = GameView.withFeedback(
                view.sessionId(),
                view.mechanicView(),
                view.catalogEntryId(),
                view.correct(),
                view.feedbackMessage(),
                view.reelSymbols(),
                view.question(),
                view.options(),
                view.score(),
                view.round(),
                view.answered(),
                view.questionsTarget(),
                FINISHED.equals(view.status()),
                view.jokers(),
                view.triedWrong());

        // Session terminee : pas de plateau a rendre (ecran de fin).
        if (FINISHED.equals(view.status())) {
            return game;
        }

        // Droite graduee : bornes de la droite (ticks) lues de la meta de l'item courant.
        if (DROITE_GRADUEE.equals(view.mechanicView())) {
            return game.withLine(intMeta(view.meta(), "min", 0), intMeta(view.meta(), "max", 10));
        }

        // Memory : plateau reconstruit depuis la meta de l'item (groupes de paires).
        if (MEMORY.equals(view.mechanicView())) {
            return withAssociations(game, view.mechanicView(), view.reelSymbols(), view.meta());
        }

        // Associer/classer : sur un essai FAUX avec des associations soumises, on GARDE en place
        // les elements bien classes et on ne renvoie en reserve que les mal classes (UX « ranger »,
        // DEC-0008). Sinon (bonne reponse -> item suivant, ou pas de soumission) : plateau initial.
        if (ASSOCIER.equals(view.mechanicView())) {
            if (!view.correct() && submittedAssociations != null && !submittedAssociations.isEmpty()) {
                Map<String, String> expected = parseAssociations(firstOption(view.options()));
                AssociationBoard board = AssociationBoard.afterAttempt(
                        view.reelSymbols(), submittedAssociations, expected);
                return game
                        .withAssociationBoard(split(safe(view.meta()).get("targets")), board.reserve(), List.of())
                        .withAssociationLocked(board.lockedItems(), board.lockedTargets());
            }
            return withAssociations(game, view.mechanicView(), view.reelSymbols(), view.meta());
        }

        // Arrangement (glisser-deposer ET puzzle, meme coup SubmitArrangement) : hors de ces
        // mecaniques, rien de plus a faire.
        if (!GLISSER_DEPOSER.equals(view.mechanicView()) && !PUZZLE.equals(view.mechanicView())) {
            return game;
        }

        GameView board;
        if (view.correct() || submittedArrangement == null) {
            // Correct (ou pas d'arrangement fourni) : item suivant, tuiles fraiches en reserve.
            ArrangementBoard b = ArrangementBoard.initial(view.reelSymbols());
            board = game.withArrangementBoard(b.slots(), b.reserve());
        } else {
            // Faux : on garde en place les tuiles bien positionnees, on renvoie le reste en reserve.
            List<String> options = view.options();
            if (options == null || options.isEmpty()) {
                ArrangementBoard b = ArrangementBoard.initial(view.reelSymbols()); // repli defensif
                board = game.withArrangementBoard(b.slots(), b.reserve());
            } else {
                List<String> expected = List.of(options.get(0).split(ARRANGEMENT_SEPARATOR, -1));
                ArrangementBoard b = ArrangementBoard.afterAttempt(submittedArrangement, expected);
                board = game.withArrangementBoard(b.slots(), b.reserve());
            }
        }
        // Le puzzle affiche en plus l'indice (image/emoji/definition) porte par la meta de l'item.
        return PUZZLE.equals(view.mechanicView())
                ? board.withHint(safe(view.meta()).get("indice"))
                : board;
    }

    /**
     * Renseigne le plateau d'associations (cf. ADR-0016) pour les mecaniques associer/classer
     * et memory, a partir de la meta de l'item (cibles pour « classer », groupes de paires pour
     * memory). Les autres mecaniques sont renvoyees inchangees.
     */
    private static Map<String, String> safe(Map<String, String> meta) {
        return meta == null ? Map.of() : meta;
    }

    /** Lit un entier de la meta de l'item (bornes de la droite graduee) ; defaut si absent/illisible. */
    private static int intMeta(Map<String, String> meta, String key, int fallback) {
        String raw = safe(meta).get(key);
        if (raw == null || raw.isBlank()) {
            return fallback;
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private static String firstOption(List<String> options) {
        return options == null || options.isEmpty() ? "" : options.get(0);
    }

    /** Decode "item=cible, item2=cible2" (cf. {@code Item.encodeAssociations}) en map. */
    private static Map<String, String> parseAssociations(String encoded) {
        Map<String, String> map = new LinkedHashMap<>();
        if (encoded == null || encoded.isEmpty()) {
            return map;
        }
        for (String pair : encoded.split(ARRANGEMENT_SEPARATOR, -1)) {
            int eq = pair.indexOf('=');
            if (eq > 0) {
                map.put(pair.substring(0, eq), pair.substring(eq + 1));
            }
        }
        return map;
    }

    private GameView withAssociations(GameView game, String mechanicView,
                                      List<String> reelSymbols, Map<String, String> meta) {
        Map<String, String> safeMeta = safe(meta);
        if (ASSOCIER.equals(mechanicView)) {
            // Zones-cibles (categories/mots-cibles) ; aucun groupe (on ne revele pas la reponse).
            return game.withAssociationBoard(split(safeMeta.get("targets")), reelSymbols, List.of());
        }
        if (MEMORY.equals(mechanicView)) {
            // Cartes + identifiants de paire paralleles (le JS apparie par groupe).
            return game.withAssociationBoard(List.of(), reelSymbols, split(safeMeta.get("groups")));
        }
        return game;
    }

    private static List<String> split(String joined) {
        if (joined == null || joined.isEmpty()) {
            return List.of();
        }
        // split litteral sur "|" (regex quote), -1 pour garder d'eventuels champs vides finaux.
        return List.of(joined.split(java.util.regex.Pattern.quote(META_SEPARATOR), -1));
    }
}
