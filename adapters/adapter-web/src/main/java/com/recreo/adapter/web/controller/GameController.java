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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controllers Spring MVC renvoyant du HTML Thymeleaf (pas de JSON). Navigation
 * pleine page a DEUX AXES (US-s03-1) : accueil ({@code GET /}) -> par niveau
 * ({@code /niveaux}, {@code /niveau/{niveau}}) ou par jeu ({@code /jeux},
 * {@code /jeu/{gameKey}}). Chaque feuille propose Jouer et Parametres
 * ({@code /parametres}). Le demarrage ({@code POST /sessions}) renvoie la page de
 * jeu {@code jeu.html} ; les coups ({@code POST /sessions/{id}/moves}) renvoient le
 * FRAGMENT echange par htmx. La fleche retour est contextuelle via le parametre
 * {@code from}.
 */
@Controller
public class GameController {

    private final StartSessionUseCase startSessionUseCase;
    private final SubmitMoveUseCase submitMoveUseCase;
    private final CatalogueQuery catalogueQuery;
    private final WebViewMapper mapper;

    private static final String HOME = "/";

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
    public String accueil() {
        return "accueil";
    }

    // ---- Axe "par niveau" ----

    @GetMapping("/niveaux")
    public String niveaux(Model model) {
        model.addAttribute("niveaux", catalogueQuery.niveaux());
        return "niveaux";
    }

    @GetMapping("/niveau/{niveau}")
    public String jeuxDuNiveau(@PathVariable("niveau") String niveau, Model model) {
        model.addAttribute("niveau", niveau);
        model.addAttribute("entries", mapper.toCatalogItems(catalogueQuery.byNiveau(niveau)));
        model.addAttribute("from", "/niveau/" + niveau);
        model.addAttribute("back", "/niveaux");
        return "niveau";
    }

    // ---- Axe "par jeu" ----

    @GetMapping("/jeux")
    public String jeux(Model model) {
        model.addAttribute("jeux", catalogueQuery.games());
        return "jeux";
    }

    @GetMapping("/jeu/{gameKey}")
    public String niveauxDuJeu(@PathVariable("gameKey") String gameKey, Model model) {
        model.addAttribute("gameKey", gameKey);
        model.addAttribute("entries", mapper.toCatalogItems(catalogueQuery.byGameKey(gameKey)));
        model.addAttribute("from", "/jeu/" + gameKey);
        model.addAttribute("back", "/jeux");
        return "jeu-niveaux";
    }

    // ---- Ecran de parametres (adulte) ----

    @GetMapping("/parametres")
    public String parametres(@RequestParam("entryId") String entryId,
                             @RequestParam(value = "from", required = false, defaultValue = HOME) String from,
                             Model model) {
        model.addAttribute("settings", catalogueQuery.settingsFor(entryId));
        model.addAttribute("from", from);
        return "parametres";
    }

    // ---- Session ----

    @PostMapping("/sessions")
    public String startSession(@RequestParam("catalogEntryId") String catalogEntryId,
                               @RequestParam(value = "from", required = false, defaultValue = HOME) String from,
                               @RequestParam(value = "minOperand", required = false) String minOperand,
                               @RequestParam(value = "maxOperand", required = false) String maxOperand,
                               @RequestParam(value = "operations", required = false) List<String> operations,
                               @RequestParam(value = "maxSyllabes", required = false) String maxSyllabes,
                               @RequestParam(value = "questionsTarget", required = false) String questionsTarget,
                               Model model) {
        Map<String, String> parameters = new LinkedHashMap<>();
        putIfPresent(parameters, "minOperand", minOperand);
        putIfPresent(parameters, "maxOperand", maxOperand);
        if (operations != null && !operations.isEmpty()) {
            parameters.put("operations", String.join(",", operations));
        }
        putIfPresent(parameters, "maxSyllabes", maxSyllabes);
        putIfPresent(parameters, "questionsTarget", questionsTarget);

        SessionView view = startSessionUseCase.start(new StartSessionCommand(catalogEntryId, parameters));
        model.addAttribute("game", mapper.toGameView(view));
        // Navigation pleine page : on renvoie la PAGE complete jeu.html (qui heberge
        // le fragment dans #game-area et porte la fleche retour contextuelle).
        model.addAttribute("from", from);
        return "jeu";
    }

    @PostMapping("/sessions/{id}/moves")
    public String submitMove(@PathVariable("id") String sessionId,
                             @RequestParam("chosenAnswer") String chosenAnswer,
                             @RequestParam(value = "from", required = false, defaultValue = HOME) String from,
                             Model model) {
        MoveOutcomeView view = submitMoveUseCase.submit(new SubmitMoveCommand(sessionId, chosenAnswer));
        var game = mapper.toGameView(view);
        model.addAttribute("game", game);
        // `from` propage l'origine du parcours pour l'ecran de fin (Rejouer / retour).
        model.addAttribute("from", from);
        // Le fragment renvoye a htmx est choisi selon la mecanique (cf. ADR-0007) :
        // il vient de la session/mecanique (mechanicView), pas devine ici.
        return "fragments/" + game.mechanicView() + " :: game";
    }

    private static void putIfPresent(Map<String, String> map, String key, String value) {
        if (value != null && !value.isBlank()) {
            map.put(key, value);
        }
    }
}
