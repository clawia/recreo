package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import com.recreo.domain.mechanics.banditmanchot.Verdict;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

/**
 * Adaptateur entrant htmx : rendu serveur, fragments HTML. Le coup de l'enfant traverse
 * PartieUseCase, jamais un appel direct a une mecanique. La session HTTP porte l'etat de partie
 * (ADR-0008), pas la base et pas le navigateur.
 */
@Controller
public class PartieController {

    private static final String ATTRIBUT_JEU_ID = "recreo.jeuId";
    private static final String ATTRIBUT_CONSIGNE = "recreo.consigne";
    private static final String ATTRIBUT_SESSION = "recreo.session";

    private final PartieUseCase partie;

    public PartieController(PartieUseCase partie) {
        this.partie = partie;
    }

    @GetMapping("/")
    public String accueil(Model modele) {
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil";
    }

    @PostMapping("/parties/{jeuId}")
    public String demarrer(@PathVariable String jeuId, HttpSession httpSession, Model modele) {
        GameDefinition jeu = jeuOuEchec(jeuId);
        BanditManchotSession session = (BanditManchotSession) partie.demarrer(jeuId);

        httpSession.setAttribute(ATTRIBUT_JEU_ID, jeuId);
        httpSession.setAttribute(ATTRIBUT_CONSIGNE, jeu.consigne());
        httpSession.setAttribute(ATTRIBUT_SESSION, session);

        return mancheModele(modele, jeuId, jeu.consigne(), session);
    }

    @PostMapping("/parties/{jeuId}/coups")
    public String jouer(@PathVariable String jeuId, @RequestParam String propositionChoisie,
                         HttpSession httpSession, Model modele) {
        BanditManchotSession sessionActuelle = sessionEnCours(httpSession, jeuId);

        BanditManchotSession suivante = (BanditManchotSession) partie.jouer(
                jeuId, sessionActuelle, new BanditManchotMove(propositionChoisie));
        httpSession.setAttribute(ATTRIBUT_SESSION, suivante);

        Verdict verdict = suivante.dernierVerdict().orElseThrow();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("score", suivante.score());
        modele.addAttribute("juste", verdict.juste());
        modele.addAttribute("bonneReponse", verdict.bonneReponse());
        modele.addAttribute("enSuite", suivante.enonceManche().isPresent());
        return "fragments/retour :: retour";
    }

    @GetMapping("/parties/{jeuId}/manche")
    public String continuer(@PathVariable String jeuId, HttpSession httpSession, Model modele) {
        BanditManchotSession session = sessionEnCours(httpSession, jeuId);
        String consigne = (String) httpSession.getAttribute(ATTRIBUT_CONSIGNE);
        return mancheModele(modele, jeuId, consigne, session);
    }

    private String mancheModele(Model modele, String jeuId, String consigne, BanditManchotSession session) {
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("consigne", consigne);
        modele.addAttribute("enonce", session.enonceManche().orElse(""));
        modele.addAttribute("propositions", session.propositions());
        modele.addAttribute("score", session.score());
        return "fragments/manche :: manche";
    }

    private GameDefinition jeuOuEchec(String jeuId) {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().equals(jeuId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("jeu inconnu : " + jeuId));
    }

    private BanditManchotSession sessionEnCours(HttpSession httpSession, String jeuId) {
        if (!jeuId.equals(httpSession.getAttribute(ATTRIBUT_JEU_ID))) {
            throw new IllegalStateException("aucune partie en cours pour ce jeu");
        }
        return (BanditManchotSession) httpSession.getAttribute(ATTRIBUT_SESSION);
    }
}
