package com.recreo.adapter.web.mapper;

import com.recreo.adapter.web.view.CatalogItemView;
import com.recreo.adapter.web.view.GameView;
import com.recreo.application.port.in.CatalogEntryView;
import com.recreo.application.port.in.MoveOutcomeView;
import com.recreo.application.port.in.SessionView;

import java.util.List;

/** Traduit les vues de l'application vers les view-models web. */
public class WebViewMapper {

    private static final String FINISHED = "FINISHED";

    public List<CatalogItemView> toCatalogItems(List<CatalogEntryView> entries) {
        return entries.stream()
                .map(e -> new CatalogItemView(
                        e.entryId(), e.matiere(), e.niveau(), e.gameKey(), e.gameLabel(),
                        e.title(), e.description(), e.hasSettings()))
                .toList();
    }

    public GameView toGameView(SessionView view) {
        return GameView.initial(
                view.sessionId(),
                view.catalogEntryId(),
                view.reelSymbols(),
                view.question(),
                view.options(),
                view.score(),
                view.round(),
                view.answered(),
                view.questionsTarget(),
                FINISHED.equals(view.status()));
    }

    public GameView toGameView(MoveOutcomeView view) {
        return GameView.withFeedback(
                view.sessionId(),
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
                FINISHED.equals(view.status()));
    }
}
