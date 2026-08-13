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

import java.util.Set;

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
 *
 * <p>S10, arbitrage de mecanique de nadia-prof : bonne reponse, la manche suivante se charge dans le
 * meme echange ; mauvaise reponse, l'enfant reste sur la meme manche, message sous le meuble
 * (M1-M13). L'ecran intermediaire disparait donc de ce cycle : {@code POST /parties/{jeuId}/coups}
 * ne rend plus jamais l'ecran de retour tant que la partie continue, il rend toujours un ecran de
 * manche. L'ecran de retour ({@code fragments/retour.html}) ne sert plus que la fin de partie.</p>
 */
@Controller
public class PartieController {

    private static final String COOKIE_PARTIE_EN_COURS = "recreo-partie-en-cours";
    private static final String MESSAGE_PARTIE_PERDUE = "Ta partie s'est arrêtée. Choisis un nouveau jeu !";

    /** M12 : les trois textes exacts, voulus par nadia-prof, ecrits ici pour ne pas etre reinventes. */
    private static final String MESSAGE_BRAVO = "Bravo !";
    private static final String MESSAGE_ECHEC = "Ce n'est pas ça. Essaie encore !";

    /**
     * ADR-0027 : l'ecran de jeu declare s'il a ete demande par un geste de l'enfant. Deux constantes
     * nommees plutot que deux litteraux booleens aux points d'appel, parce que c'est le sens qui doit
     * se lire sur l'appel et non la valeur.
     */
    private static final boolean PAR_UN_GESTE = true;
    private static final boolean SANS_GESTE = false;

    /**
     * M13 : les deux points de focus qu'un ecran de manche peut declarer. "enonce" est le repli par
     * defaut - la premiere manche d'une partie, ou toute manche fraiche sans reussite immediate a
     * signaler - et le point que M13 clause 4 nomme apres une bonne reponse. L'autre, {@code
     * FOCUS_BOUTON}, designe le bouton que {@code focusCibles} porte : la proposition que l'enfant
     * vient de presser (clause 2), ou celle qui porte la reponse revelee (clause 5).
     */
    private static final String FOCUS_ENONCE = "enonce";

    /**
     * ADR-0008 amendement d'ADR-0026 : le vocabulaire du tirage ("proposition", "colonne") est
     * interdit dans le JavaScript maison, qui lit cette valeur pour choisir l'element a focaliser
     * (recreo.js). "bouton" en est le synonyme neutre qui ne heurte pas cette liste.
     */
    private static final String FOCUS_BOUTON = "bouton";

    private final PartieUseCase partie;

    public PartieController(PartieUseCase partie) {
        this.partie = partie;
    }

    @GetMapping("/")
    public String accueil(Model modele, HttpServletRequest requete, HttpServletResponse reponse) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();

        if (etat.portePartieEnCours()) {
            modele.addAttribute("reprise", true);
            mancheModele(modele, etat.jeuId(), etat.consigne(), etat.session(), SANS_GESTE);
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

        return mancheModele(modele, jeuId, jeu.consigne(), session, PAR_UN_GESTE);
    }

    @PostMapping("/parties/{jeuId}/coups")
    public String jouer(@PathVariable String jeuId, @RequestParam String propositionChoisie,
                         HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).jouer(jeuId,
                actuelle -> (BanditManchotSession) partie.jouer(
                        jeuId, actuelle, new BanditManchotMove(propositionChoisie)));
        synchroniserMarqueurDePartie(etat.session(), reponse);

        return mancheModele(modele, jeuId, etat.consigne(), etat.session(), PAR_UN_GESTE);
    }

    /**
     * ADR-0020 : une partie terminee n'a plus de manche a montrer. Le controle est ici, dans le seul
     * point de passage de l'ecran de jeu, et non dans chaque route : toute route qui demande cet
     * ecran obtient l'ecran de fin de partie a la place d'un ecran mort. Symetrique du choix
     * d'ADR-0019, qui a ferme le meme cas cote commande.
     *
     * <p>ADR-0027 : {@code arriveeParUnGeste} dit si l'enfant a presse quelque chose pour obtenir cet
     * ecran. Le serveur est le seul a le savoir : une fois le fragment insere, le document assemble est
     * le meme qu'il vienne d'un echange htmx ou du chargement complet de la page de reprise, donc
     * aucun selecteur CSS ne peut les distinguer. C'est un <b>parametre</b> et non un attribut pose a
     * cote, pour que le compilateur oblige chaque point d'appel - ceux d'aujourd'hui comme ceux de
     * demain - a se prononcer : la derivation est totale parce que ce point de passage est unique
     * (meme raison qu'ADR-0020 ci-dessus). Le mode de defaillance d'un oubli est l'immobilite, jamais
     * un mouvement que l'enfant n'a pas demande.</p>
     *
     * <p>M1, M5, M6, M9, M11, M12, M13 : le message sous le meuble, l'indice, la revelation et le
     * point de focus sont tous calcules ici, a partir du seul etat de la manche courante - jamais
     * d'un champ neuf sur la session (avis de faisabilite de theo-archi, s10, correction 2).</p>
     */
    private String mancheModele(Model modele, String jeuId, String consigne,
                                BanditManchotSession session, boolean arriveeParUnGeste) {
        if (session.status() == SessionStatus.TERMINEE) {
            return retourModele(modele, jeuId, session);
        }
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("consigne", consigne);
        modele.addAttribute("enonce", session.enonceManche().orElse(""));
        modele.addAttribute("propositions", session.propositions());
        modele.addAttribute("score", session.score());
        modele.addAttribute("indice", session.indice().orElse(null));
        modele.addAttribute("arriveeParUnGeste", arriveeParUnGeste);
        modele.addAttribute("propositionsEssayees", session.propositionsEssayees());

        boolean fraiche = session.propositionsEssayees().isEmpty();
        String message = null;
        boolean signalJuste = false;
        String explication = null;
        String focus = FOCUS_ENONCE;
        Set<String> focusCibles = Set.of();

        if (session.revelee()) {
            String reponse = session.reponseRevelee().orElseThrow();
            message = "C'est " + reponse + ". Appuie sur " + reponse + ".";
            explication = session.explicationRevelee().orElse(null);
            focus = FOCUS_BOUTON;
            focusCibles = Set.of(reponse);
        } else if (!fraiche) {
            message = MESSAGE_ECHEC;
            focus = FOCUS_BOUTON;
            focusCibles = session.derniereTentative().map(Set::of).orElse(Set.of());
        } else if (session.dernierVerdict().map(Verdict::juste).orElse(false)) {
            message = MESSAGE_BRAVO;
            signalJuste = true;
        }

        modele.addAttribute("message", message);
        modele.addAttribute("signalJuste", signalJuste);
        modele.addAttribute("explication", explication);
        modele.addAttribute("focus", focus);
        modele.addAttribute("focusCibles", focusCibles);
        return "fragments/manche :: manche";
    }

    /**
     * L'ecran de fin de partie. Depuis que le cycle de manche absorbe la reponse juste comme la
     * fausse (ci-dessus), ce gabarit ne sert plus qu'a cet unique etat : le verdict est celui de la
     * toute derniere manche resolue, juste ou par revelation (M11 clause 3 : une manche resolue par
     * revelation n'est pas une reussite, et son verdict porte {@code juste = false}).
     *
     * <p>Le verdict est obligatoire : une partie sans aucune manche jouee n'existe pas, et si elle
     * existait, c'est un defaut qui doit partir en ERROR avec sa pile (ADR-0018) plutot que de
     * rendre un ecran a moitie nourri.</p>
     *
     * <p>ADR-0024 : l'explication de l'item joue est posee au modele sous "explication", vide quand
     * le contenu n'en porte pas. Le controleur ne decide pas si elle merite d'etre affichee, il ne
     * lit pas son texte, et il n'en fabrique jamais : il transmet ce que le verdict porte.</p>
     */
    private String retourModele(Model modele, String jeuId, BanditManchotSession session) {
        Verdict verdict = session.dernierVerdict().orElseThrow();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("score", session.score());
        modele.addAttribute("juste", verdict.juste());
        modele.addAttribute("bonneReponse", verdict.bonneReponse());
        modele.addAttribute("explication", verdict.explication().orElse(null));
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
