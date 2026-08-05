package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import com.recreo.domain.mechanics.banditmanchot.Verdict;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
 *
 * <p>S1-4 : un cookie navigateur (distinct de la session HTTP) signale qu'une partie est en cours,
 * pour distinguer, sur GET /, une reprise silencieuse (session HTTP encore valide) d'une partie
 * perdue (session HTTP disparue alors que le cookie, lui, survit). Le cookie est efface a chaque
 * fin de partie et sur le retour explicite a l'accueil.</p>
 */
@Controller
public class PartieController {

    private static final String ATTRIBUT_JEU_ID = "recreo.jeuId";
    private static final String ATTRIBUT_CONSIGNE = "recreo.consigne";
    private static final String ATTRIBUT_SESSION = "recreo.session";
    private static final String COOKIE_PARTIE_EN_COURS = "recreo-partie-en-cours";
    private static final String MESSAGE_PARTIE_PERDUE = "Ta partie s'est arrêtée. Choisis un nouveau jeu !";

    private final PartieUseCase partie;

    public PartieController(PartieUseCase partie) {
        this.partie = partie;
    }

    @GetMapping("/")
    public String accueil(Model modele, HttpSession httpSession, HttpServletRequest requete,
                           HttpServletResponse reponse) {
        String jeuId = (String) httpSession.getAttribute(ATTRIBUT_JEU_ID);
        BanditManchotSession session = (BanditManchotSession) httpSession.getAttribute(ATTRIBUT_SESSION);

        if (jeuId != null && session != null && session.status() == SessionStatus.EN_COURS) {
            String consigne = (String) httpSession.getAttribute(ATTRIBUT_CONSIGNE);
            modele.addAttribute("reprise", true);
            mancheModele(modele, jeuId, consigne, session);
            return "accueil";
        }

        if (partieSignaleeEnCours(requete)) {
            effacerMarqueurDePartie(reponse);
            modele.addAttribute("messageReprise", MESSAGE_PARTIE_PERDUE);
        }
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil";
    }

    @PostMapping("/retour")
    public String retour(HttpSession httpSession, HttpServletResponse reponse, Model modele) {
        httpSession.removeAttribute(ATTRIBUT_JEU_ID);
        httpSession.removeAttribute(ATTRIBUT_CONSIGNE);
        httpSession.removeAttribute(ATTRIBUT_SESSION);
        effacerMarqueurDePartie(reponse);
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil :: zoneAccueil";
    }

    @PostMapping("/parties/{jeuId}")
    public String demarrer(@PathVariable String jeuId, HttpSession httpSession, HttpServletResponse reponse,
                            Model modele) {
        GameDefinition jeu = jeuOuEchec(jeuId);
        BanditManchotSession session = (BanditManchotSession) partie.demarrer(jeuId);

        httpSession.setAttribute(ATTRIBUT_JEU_ID, jeuId);
        httpSession.setAttribute(ATTRIBUT_CONSIGNE, jeu.consigne());
        httpSession.setAttribute(ATTRIBUT_SESSION, session);
        synchroniserMarqueurDePartie(session, reponse);

        return mancheModele(modele, jeuId, jeu.consigne(), session);
    }

    @PostMapping("/parties/{jeuId}/coups")
    public String jouer(@PathVariable String jeuId, @RequestParam String propositionChoisie,
                         HttpSession httpSession, HttpServletResponse reponse, Model modele) {
        BanditManchotSession sessionActuelle = sessionEnCours(httpSession, jeuId);

        BanditManchotSession suivante = (BanditManchotSession) partie.jouer(
                jeuId, sessionActuelle, new BanditManchotMove(propositionChoisie));
        httpSession.setAttribute(ATTRIBUT_SESSION, suivante);
        synchroniserMarqueurDePartie(suivante, reponse);

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
        modele.addAttribute("indice", session.indice().orElse(null));
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

    private static boolean partieSignaleeEnCours(HttpServletRequest requete) {
        Cookie[] cookies = requete.getCookies();
        if (cookies == null) {
            return false;
        }
        for (Cookie cookie : cookies) {
            if (COOKIE_PARTIE_EN_COURS.equals(cookie.getName())) {
                return true;
            }
        }
        return false;
    }

    private static void synchroniserMarqueurDePartie(BanditManchotSession session, HttpServletResponse reponse) {
        if (session.status() == SessionStatus.TERMINEE) {
            effacerMarqueurDePartie(reponse);
        } else {
            marquerPartieEnCours(reponse);
        }
    }

    private static void marquerPartieEnCours(HttpServletResponse reponse) {
        Cookie cookie = new Cookie(COOKIE_PARTIE_EN_COURS, "1");
        cookie.setPath("/");
        reponse.addCookie(cookie);
    }

    private static void effacerMarqueurDePartie(HttpServletResponse reponse) {
        Cookie cookie = new Cookie(COOKIE_PARTIE_EN_COURS, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        reponse.addCookie(cookie);
    }
}
