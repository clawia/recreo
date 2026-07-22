package com.recreo.adapter.web.mapper;

import com.recreo.adapter.web.view.CatalogItemView;
import com.recreo.adapter.web.view.GameView;
import com.recreo.application.port.in.CatalogEntryView;
import com.recreo.application.port.in.MoveOutcomeView;
import com.recreo.application.port.in.SessionView;
import com.recreo.domain.mechanic.ArrangementBoard;

import java.util.List;
import java.util.Map;

/** Traduit les vues de l'application vers les view-models web. */
public class WebViewMapper {

    private static final String FINISHED = "FINISHED";

    /** Vue de la mecanique glisser-deposer (cf. {@code GlisserDeposerMechanic.VIEW}). */
    private static final String GLISSER_DEPOSER = "glisserdeposer";

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
        // Nouveau puzzle glisser-deposer : plateau initial (emplacements vides, tuiles en reserve).
        if (GLISSER_DEPOSER.equals(view.mechanicView())) {
            ArrangementBoard board = ArrangementBoard.initial(view.reelSymbols());
            return game.withArrangementBoard(board.slots(), board.reserve());
        }
        return withAssociations(game, view.mechanicView(), view.reelSymbols(), view.meta());
    }

    /** Ancienne surcharge : aucun arrangement soumis -> plateau initial pour glisser-deposer. */
    public GameView toGameView(MoveOutcomeView view) {
        return toGameView(view, null);
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
    public GameView toGameView(MoveOutcomeView view, List<String> submittedArrangement) {
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

        // Mecaniques associer/classer & memory : plateau reconstruit depuis la meta de l'item.
        if (ASSOCIER.equals(view.mechanicView()) || MEMORY.equals(view.mechanicView())) {
            return withAssociations(game, view.mechanicView(), view.reelSymbols(), view.meta());
        }

        // Autres mecaniques (hors glisser-deposer) : rien de plus a faire.
        if (!GLISSER_DEPOSER.equals(view.mechanicView())) {
            return game;
        }

        // Correct (ou pas d'arrangement fourni) : puzzle suivant, tuiles fraiches en reserve.
        if (view.correct() || submittedArrangement == null) {
            ArrangementBoard board = ArrangementBoard.initial(view.reelSymbols());
            return game.withArrangementBoard(board.slots(), board.reserve());
        }

        // Faux : on garde en place les tuiles bien positionnees, on renvoie le reste en reserve.
        List<String> options = view.options();
        if (options == null || options.isEmpty()) {
            // Repli defensif (ne devrait pas arriver) : plateau initial.
            ArrangementBoard board = ArrangementBoard.initial(view.reelSymbols());
            return game.withArrangementBoard(board.slots(), board.reserve());
        }
        List<String> expected = List.of(options.get(0).split(ARRANGEMENT_SEPARATOR, -1));
        ArrangementBoard board = ArrangementBoard.afterAttempt(submittedArrangement, expected);
        return game.withArrangementBoard(board.slots(), board.reserve());
    }

    /**
     * Renseigne le plateau d'associations (cf. ADR-0016) pour les mecaniques associer/classer
     * et memory, a partir de la meta de l'item (cibles pour « classer », groupes de paires pour
     * memory). Les autres mecaniques sont renvoyees inchangees.
     */
    private GameView withAssociations(GameView game, String mechanicView,
                                      List<String> reelSymbols, Map<String, String> meta) {
        Map<String, String> safeMeta = meta == null ? Map.of() : meta;
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
