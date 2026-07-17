package com.recreo.adapter.web.controller;

import com.recreo.adapter.web.mapper.WebViewMapper;
import com.recreo.application.port.in.CatalogueQuery;
import com.recreo.application.port.in.MoveOutcomeView;
import com.recreo.application.port.in.SessionView;
import com.recreo.application.port.in.StartSessionCommand;
import com.recreo.application.port.in.StartSessionUseCase;
import com.recreo.application.port.in.SubmitMoveCommand;
import com.recreo.application.port.in.SubmitMoveUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controllers Spring MVC renvoyant des FRAGMENTS HTML Thymeleaf (pas de JSON).
 * Le catalogue est une page complete ; les coups renvoient le fragment de jeu
 * que htmx vient echanger dans la page.
 */
@Controller
public class GameController {

    private final StartSessionUseCase startSessionUseCase;
    private final SubmitMoveUseCase submitMoveUseCase;
    private final CatalogueQuery catalogueQuery;
    private final WebViewMapper mapper;

    private static final String GAME_FRAGMENT = "fragments/banditmanchot :: game";

    public GameController(StartSessionUseCase startSessionUseCase,
                          SubmitMoveUseCase submitMoveUseCase,
                          CatalogueQuery catalogueQuery,
                          WebViewMapper mapper) {
        this.startSessionUseCase = startSessionUseCase;
        this.submitMoveUseCase = submitMoveUseCase;
        this.catalogueQuery = catalogueQuery;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String catalogue(Model model) {
        model.addAttribute("catalogue", mapper.toCatalogItems(catalogueQuery.entries()));
        return "catalogue";
    }

    @PostMapping("/sessions")
    public String startSession(@RequestParam("catalogEntryId") String catalogEntryId, Model model) {
        SessionView view = startSessionUseCase.start(new StartSessionCommand(catalogEntryId));
        model.addAttribute("game", mapper.toGameView(view));
        return GAME_FRAGMENT;
    }

    @PostMapping("/sessions/{id}/moves")
    public String submitMove(@PathVariable("id") String sessionId,
                             @RequestParam("chosenAnswer") String chosenAnswer,
                             Model model) {
        MoveOutcomeView view = submitMoveUseCase.submit(new SubmitMoveCommand(sessionId, chosenAnswer));
        model.addAttribute("game", mapper.toGameView(view));
        return GAME_FRAGMENT;
    }
}
