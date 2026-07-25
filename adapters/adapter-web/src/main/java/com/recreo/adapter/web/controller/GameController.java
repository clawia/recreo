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
    public String jeuxDuNiveau(@PathVariable("niveau") String niveau,
                               @RequestParam(value = "matiere", required = false) String matiere,
                               Model model) {
        // Axe « par niveau », FILTRABLE par matiere (chips bascule + « Toutes », symetrique aux
        // filtres de « Par jeu », cf. DEC-0019). Les chips proposees = seulement les matieres
        // PRESENTES a ce niveau, dans l'ordre canonique du catalogue.
        boolean filtreMatiere = matiere != null && !matiere.isBlank();
        var all = mapper.toCatalogItems(catalogueQuery.byNiveau(niveau));
        var presentes = all.stream().map(i -> i.matiere()).distinct().toList();
        var matieres = catalogueQuery.matieres().stream().filter(presentes::contains).toList();
        var entries = all.stream()
                .filter(i -> !filtreMatiere || i.matiere().equals(matiere))
                .toList();
        model.addAttribute("niveau", niveau);
        model.addAttribute("entries", entries);
        model.addAttribute("matieres", matieres);
        model.addAttribute("selectedMatiere", filtreMatiere ? matiere : null);
        model.addAttribute("from", "/niveau/" + niveau);
        model.addAttribute("back", "/niveaux");
        return "niveau";
    }

    // ---- Axe "par matiere" ----

    @GetMapping("/matieres")
    public String matieres(Model model) {
        model.addAttribute("matieres", catalogueQuery.matieres());
        return "matieres";
    }

    @GetMapping("/matiere/{matiere}")
    public String jeuxDeLaMatiere(@PathVariable("matiere") String matiere,
                                  @RequestParam(value = "niveau", required = false) String niveau,
                                  Model model) {
        // Axe « par matiere », FILTRABLE par niveau (chips bascule + « Tous », symetrique aux
        // filtres de « Par jeu », cf. DEC-0019). Les chips proposees = seulement les niveaux
        // PRESENTS dans cette matiere, dans l'ordre canonique du catalogue.
        boolean filtreNiveau = niveau != null && !niveau.isBlank();
        var all = mapper.toCatalogItems(catalogueQuery.byMatiere(matiere));
        var presents = all.stream().map(i -> i.niveau()).distinct().toList();
        var niveaux = catalogueQuery.niveaux().stream().filter(presents::contains).toList();
        var entries = all.stream()
                .filter(i -> !filtreNiveau || i.niveau().equals(niveau))
                .toList();
        model.addAttribute("matiere", matiere);
        model.addAttribute("entries", entries);
        model.addAttribute("niveaux", niveaux);
        model.addAttribute("selectedNiveau", filtreNiveau ? niveau : null);
        model.addAttribute("from", "/matiere/" + matiere);
        model.addAttribute("back", "/matieres");
        return "matiere";
    }

    // ---- Axe "par jeu" ----

    @GetMapping("/jeux")
    public String jeux(@RequestParam(value = "niveau", required = false) String niveau,
                       @RequestParam(value = "matiere", required = false) String matiere,
                       Model model) {
        // Axe « par jeu » : liste PLATE de tous les jeux, FILTRABLE par niveau et/ou matiere
        // (boutons de filtre combinables, cf. IDEE-3). Triee par nom (regroupe les memes jeux) puis
        // niveau, affichee au format « Matiere Niveau — Nom » (contexte 'jeu').
        boolean filtreNiveau = niveau != null && !niveau.isBlank();
        boolean filtreMatiere = matiere != null && !matiere.isBlank();
        var entries = mapper.toCatalogItems(catalogueQuery.entries()).stream()
                .filter(i -> !filtreNiveau || i.niveau().equals(niveau))
                .filter(i -> !filtreMatiere || i.matiere().equals(matiere))
                .sorted(java.util.Comparator
                        .comparing(com.recreo.adapter.web.view.CatalogItemView::gameLabel)
                        .thenComparing(com.recreo.adapter.web.view.CatalogItemView::niveau))
                .toList();
        model.addAttribute("entries", entries);
        model.addAttribute("niveaux", catalogueQuery.niveaux());
        model.addAttribute("matieres", catalogueQuery.matieres());
        model.addAttribute("selectedNiveau", filtreNiveau ? niveau : null);
        model.addAttribute("selectedMatiere", filtreMatiere ? matiere : null);
        model.addAttribute("from", "/jeux");
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
                               @RequestParam(value = "centaines", required = false) List<String> centaines,
                               @RequestParam(value = "questionsTarget", required = false) String questionsTarget,
                               Model model) {
        Map<String, String> parameters = new LinkedHashMap<>();
        putIfPresent(parameters, "minOperand", minOperand);
        putIfPresent(parameters, "maxOperand", maxOperand);
        if (operations != null && !operations.isEmpty()) {
            parameters.put("operations", String.join(",", operations));
        }
        putIfPresent(parameters, "maxSyllabes", maxSyllabes);
        // Toggle (case a cocher) : hidden "false" + checkbox "true" -> present si le
        // formulaire est utilise ; coche => contient "true". Absent (jeu lance sans
        // ouvrir les reglages) => la fabrique applique son defaut.
        if (centaines != null && !centaines.isEmpty()) {
            parameters.put("centaines", String.valueOf(centaines.contains("true")));
        }
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
                             @RequestParam(value = "chosenAnswer", required = false) String chosenAnswer,
                             @RequestParam(value = "arrangement", required = false) java.util.List<String> arrangement,
                             @RequestParam(value = "assocItem", required = false) List<String> assocItem,
                             @RequestParam(value = "assocTarget", required = false) List<String> assocTarget,
                             @RequestParam(value = "from", required = false, defaultValue = HOME) String from,
                             Model model) {
        SubmitMoveCommand command = buildCommand(sessionId, chosenAnswer, arrangement, assocItem, assocTarget);
        MoveOutcomeView view = submitMoveUseCase.submit(command);
        // On passe au mapper l'arrangement ET les associations soumis : sur un essai faux, le
        // glisser-deposer garde les tuiles bien placees et l'associer garde les elements bien
        // classes (UX « ranger », DEC-0008). Pour les jeux a clic, les deux sont null.
        var game = mapper.toGameView(view, arrangement, command.associations());
        model.addAttribute("game", game);
        // `from` propage l'origine du parcours pour l'ecran de fin (Rejouer / retour).
        model.addAttribute("from", from);
        // Le fragment renvoye a htmx est choisi selon la mecanique (cf. ADR-0007) :
        // il vient de la session/mecanique (mechanicView), pas devine ici.
        return "fragments/" + game.mechanicView() + " :: game";
    }

    /**
     * Choisit le coup a soumettre selon la charge utile du formulaire : associations
     * (associer/classer & memory : deux listes PARALLELES {@code assocItem}/{@code assocTarget}
     * zippees en une map), sinon arrangement (glisser-deposer), sinon reponse cliquee (pick).
     */
    private static SubmitMoveCommand buildCommand(String sessionId, String chosenAnswer,
                                                  List<String> arrangement,
                                                  List<String> assocItem, List<String> assocTarget) {
        if (assocItem != null && !assocItem.isEmpty() && assocTarget != null) {
            Map<String, String> associations = new LinkedHashMap<>();
            int n = Math.min(assocItem.size(), assocTarget.size());
            for (int i = 0; i < n; i++) {
                associations.put(assocItem.get(i), assocTarget.get(i));
            }
            if (!associations.isEmpty()) {
                return SubmitMoveCommand.associations(sessionId, associations);
            }
        }
        if (arrangement != null && !arrangement.isEmpty()) {
            return SubmitMoveCommand.arrangement(sessionId, arrangement);
        }
        return SubmitMoveCommand.pick(sessionId, chosenAnswer);
    }

    private static void putIfPresent(Map<String, String> map, String key, String value) {
        if (value != null && !value.isBlank()) {
            map.put(key, value);
        }
    }
}
