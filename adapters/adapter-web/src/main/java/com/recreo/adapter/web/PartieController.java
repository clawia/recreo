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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Adaptateur entrant htmx : rendu serveur, fragments HTML. Le coup de l'enfant traverse
 * PartieUseCase, jamais un appel direct a une mecanique. La session HTTP porte l'etat de partie
 * (ADR-0008), pas la base et pas le navigateur.
 *
 * <p>S1-4 : un cookie navigateur (distinct de la session HTTP) signale qu'une partie est en cours,
 * pour distinguer, sur GET /, une reprise silencieuse (session HTTP encore valide) d'une partie
 * perdue (session HTTP disparue alors que le cookie, lui, survit). Le cookie est efface a chaque
 * fin de partie et sur le retour explicite a l'accueil.</p>
 *
 * <p>ADR-0019 : ce controleur ne touche jamais la session HTTP directement. Il passe par
 * EtatDePartieEnSession, qui rend chaque lecture-calcul-ecriture indivisible pour une meme session.
 * Un double appui envoie deux requetes qui se recouvrent, et le cycle non protege perdait un
 * increment de score une fois sur trois.</p>
 */
@Controller
public class PartieController {

    private static final String COOKIE_PARTIE_EN_COURS = "recreo-partie-en-cours";
    private static final String MESSAGE_PARTIE_PERDUE = "Ta partie s'est arrêtée. Choisis un nouveau jeu !";

    private final PartieUseCase partie;

    public PartieController(PartieUseCase partie) {
        this.partie = partie;
    }

    @GetMapping("/")
    public String accueil(Model modele, HttpServletRequest requete, HttpServletResponse reponse) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();

        if (etat.portePartieEnCours()) {
            modele.addAttribute("reprise", true);
            mancheModele(modele, etat.jeuId(), etat.consigne(), etat.session());
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
    public String retour(HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        new EtatDePartieEnSession(requete).abandonner();
        effacerMarqueurDePartie(reponse);
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil :: zoneAccueil";
    }

    @PostMapping("/parties/{jeuId}")
    public String demarrer(@PathVariable String jeuId, HttpServletRequest requete,
                            HttpServletResponse reponse, Model modele) {
        GameDefinition jeu = jeuOuEchec(jeuId);
        BanditManchotSession session = (BanditManchotSession) partie.demarrer(jeuId);

        new EtatDePartieEnSession(requete).demarrer(jeuId, jeu.consigne(), session);
        synchroniserMarqueurDePartie(session, reponse);

        return mancheModele(modele, jeuId, jeu.consigne(), session);
    }

    @PostMapping("/parties/{jeuId}/coups")
    public String jouer(@PathVariable String jeuId, @RequestParam String propositionChoisie,
                         HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        BanditManchotSession suivante = new EtatDePartieEnSession(requete).jouer(jeuId,
                actuelle -> (BanditManchotSession) partie.jouer(
                        jeuId, actuelle, new BanditManchotMove(propositionChoisie)));
        synchroniserMarqueurDePartie(suivante, reponse);

        return retourModele(modele, jeuId, suivante);
    }

    @GetMapping("/parties/{jeuId}/manche")
    public String continuer(@PathVariable String jeuId, HttpServletRequest requete, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();
        if (!etat.porte(jeuId)) {
            throw new PartieIndisponible("aucune partie en cours pour le jeu " + jeuId);
        }
        return mancheModele(modele, jeuId, etat.consigne(), etat.session());
    }

    /**
     * ADR-0020 : une partie terminee n'a plus de manche a montrer. Le controle est ici, dans le seul
     * point de passage de l'ecran de jeu, et non dans chaque route : toute route qui demande cet
     * ecran - celles d'aujourd'hui comme celles de demain - obtient l'ecran de fin de partie a la
     * place de l'ecran mort (consigne rendue, enonce vide, aucune proposition) que rendait
     * {@code GET /parties/{jeuId}/manche} sur une partie terminee. Symetrique du choix d'ADR-0019,
     * qui a ferme le meme cas cote commande.
     */
    private String mancheModele(Model modele, String jeuId, String consigne, BanditManchotSession session) {
        if (session.status() == SessionStatus.TERMINEE) {
            return retourModele(modele, jeuId, session);
        }
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("consigne", consigne);
        modele.addAttribute("enonce", session.enonceManche().orElse(""));
        modele.addAttribute("propositions", session.propositions());
        modele.addAttribute("score", session.score());
        modele.addAttribute("indice", session.indice().orElse(null));
        return "fragments/manche :: manche";
    }

    /**
     * L'ecran de retour juste/faux, qui est aussi l'ecran de fin de partie quand plus aucune manche
     * ne suit. Le verdict est obligatoire : une partie sans aucune manche jouee n'existe pas, et si
     * elle existait, c'est un defaut qui doit partir en ERROR avec sa pile (ADR-0018) plutot que de
     * rendre un ecran a moitie nourri.
     */
    private String retourModele(Model modele, String jeuId, BanditManchotSession session) {
        Verdict verdict = session.dernierVerdict().orElseThrow();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("score", session.score());
        modele.addAttribute("juste", verdict.juste());
        modele.addAttribute("bonneReponse", verdict.bonneReponse());
        modele.addAttribute("enSuite", session.enonceManche().isPresent());
        return "fragments/retour :: retour";
    }

    private GameDefinition jeuOuEchec(String jeuId) {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> jeu.id().equals(jeuId))
                .findFirst()
                .orElseThrow(() -> new PartieIndisponible("jeu inconnu : " + jeuId));
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
