package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.content.EmplacementDuLibelleDeZone;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import com.recreo.domain.mechanics.banditmanchot.Verdict;
import com.recreo.domain.mechanics.designation.DesignationMove;
import com.recreo.domain.mechanics.designation.DesignationSession;
import com.recreo.domain.mechanics.designation.DesignationVerdict;
import com.recreo.domain.mechanics.saisie.ContinuerMove;
import com.recreo.domain.mechanics.saisie.SaisieMove;
import com.recreo.domain.mechanics.saisie.SaisieSession;
import com.recreo.domain.mechanics.saisie.SaisieVerdict;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
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

    /**
     * ADR-0048 : en-tete htmx qui pousse une adresse dans l'historique du navigateur sans toucher
     * au corps de la reponse (aucun echange hors bande, ADR-0020 intact). Le seul en-tete de ce
     * type que ce depot pose a ce jour.
     */
    private static final String ENTETE_ADRESSE_AFFICHEE = "HX-Push-Url";
    private static final String ADRESSE_CATALOGUE_COMPLET = "/";

    /** M12 : les trois textes exacts, voulus par nadia-prof, ecrits ici pour ne pas etre reinventes. */
    private static final String MESSAGE_BRAVO = "Bravo !";
    private static final String MESSAGE_ECHEC = "Ce n'est pas ça. Essaie encore !";

    /**
     * Le texte que l'enfant lit quand ses trois essais sont depenses, sur <b>les deux</b> mecaniques
     * depuis ADR-0083. Il ne nomme pas la reponse : c'est un bloc voisin qui la porte, seul dans son
     * element - {@code .forme-de-reference} sur l'ecran de saisie (SAI-9b),
     * {@code .reponse-attendue} sur l'ecran a boutons (REV-1). <b>Candidat</b>, au sens ou
     * ines-design l'emploie : la formulation de tout ce que l'enfant lit apres avoir repondu est une
     * propriete dont nadia-prof reste la seule garde, et elle la jugera sur l'increment joue.
     */
    private static final String MESSAGE_REVELATION = "Ce n'est pas ça.";

    /**
     * ADR-0027 : chaque point d'appel de l'ecran de jeu declare si un geste de l'enfant a demande
     * cette reponse. Deux constantes nommees plutot que deux litteraux booleens aux points d'appel,
     * parce que c'est le sens qui doit se lire sur l'appel et non la valeur.
     *
     * <p>ADR-0027, amendement du 2026-08-13 (M10) : ce que le point d'appel declare n'est plus la
     * valeur du marqueur, c'est <b>l'un de ses deux facteurs</b> - celui que lui seul connait.
     * L'autre, "ce geste a-t-il renouvele le tirage", est calcule dans {@code mancheModele} pour
     * tous les points d'appel a la fois.</p>
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

    /**
     * ADR-0082 decision 2, etendue aux propositions par ADR-0104 decision 3 : le prefixe d'un
     * identifiant de reproduction. Le lire est un fait de rendu, donc d'ici : une mecanique ne lit
     * jamais ce qu'une proposition ecrit (ADR-0104, regle 13).
     */
    private static final String PREFIXE_DE_REPRODUCTION = "oeuvre-";

    /** ADR-0104 decision 3 : vrai si et seulement si toutes les propositions de la manche sont des reproductions. */
    private static boolean propositionsEnImage(List<String> propositions) {
        return !propositions.isEmpty()
                && propositions.stream().allMatch(proposition -> proposition.startsWith(PREFIXE_DE_REPRODUCTION));
    }

    private final PartieUseCase partie;

    public PartieController(PartieUseCase partie) {
        this.partie = partie;
    }

    /**
     * S15-5 (nadia-prof, FICHE-barre-entree-niveau.md) : aucun filtre ne survit a un chargement de
     * page - {@code niveauActif} n'est jamais pose ici, sur aucune des deux branches, donc jamais
     * lu comme non nul par le gabarit (DEC-0028, ADR-0044 point 4 : le niveau ne vit que dans l'URL
     * de {@code /niveaux/{niveau}}, jamais en session).
     */
    @GetMapping("/")
    public String accueil(Model modele, HttpServletRequest requete, HttpServletResponse reponse) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();
        modele.addAttribute("niveaux", partie.niveauxDuCatalogue());

        if (etat.portePartieEnCours()) {
            modele.addAttribute("reprise", true);
            mancheModele(modele, etat.jeuId(), etat.consigne(), etat.consigneAdulte(), etat.session(),
                    SANS_GESTE);
            return "accueil";
        }

        if (partieSignaleeEnCours(requete)) {
            effacerMarqueurDePartie(reponse);
            modele.addAttribute("messageReprise", MESSAGE_PARTIE_PERDUE);
        }
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil";
    }

    /**
     * ADR-0038, DEC-0026 (story 2, s11) : quitter une partie en cours n'est plus un geste unique.
     * Ce point d'appel ne detruit plus rien lui-meme ; s'il existe une partie en cours, il rend un
     * ecran de confirmation qui laisse la session intacte (aucun appel a {@code abandonner()}). Sans
     * partie en cours, il n'y a rien a confirmer : le comportement d'avant ce sprint est inchange,
     * celui de l'ecran de fin de partie qui pose lui aussi {@code hx-post="/retour"}.
     *
     * <p>ADR-0048 (S16-2) : {@code hx-post="/retour"} reste un echange htmx, pour garder sa double
     * responsabilite (ci-dessus). C'est la meme branche qui choisit le fragment qui declare, par
     * une en-tete de reponse et non par un attribut du declencheur, l'adresse a afficher - jamais
     * sur l'ecran de confirmation, ou elle mentirait sur ce que l'enfant vient de quitter.</p>
     */
    @PostMapping("/retour")
    public String retour(HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();
        if (etat.portePartieEnCours()) {
            modele.addAttribute("urlConfirmerAbandon", "/retour/confirmer");
            return "fragments/confirmation :: confirmation";
        }
        new EtatDePartieEnSession(requete).abandonner();
        effacerMarqueurDePartie(reponse);
        reponse.setHeader(ENTETE_ADRESSE_AFFICHEE, ADRESSE_CATALOGUE_COMPLET);
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil :: zoneAccueil";
    }

    /** Le geste que l'ecran de confirmation appelle pour quitter reellement la partie en cours. */
    @PostMapping("/retour/confirmer")
    public String confirmerAbandon(HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        new EtatDePartieEnSession(requete).abandonner();
        effacerMarqueurDePartie(reponse);
        modele.addAttribute("jeux", partie.jeuxDisponibles());
        return "accueil :: zoneAccueil";
    }

    /**
     * S14-1 (ADR-0043, ADR-0044) : le point d'entree par niveau, une route et non un parametre -
     * c'est ce qui le fait entrer dans le perimetre derive du garde-fou de tabulation
     * (chaqueRouteDuControleurEstExerceeParCeGardeFou). Sans partie en cours, la liste se filtre
     * dans la meme reponse (critere 3). Avec une partie en cours, la meme confirmation qu'une
     * sortie ordinaire s'affiche d'abord, sans toucher la session (critere 4) ; son bouton de
     * confirmation porte le niveau demande pour que l'accueil qui en resulte reste filtre
     * (critere 6), puisque DEC-0028/ADR-0044 interdisent de le loger en session.
     *
     * <p>Amendement d'ADR-0044 (s15, "un seul rang, une couleur sur la puce choisie") : la puce de
     * niveau perd {@code hx-get}/{@code hx-target} et ne garde que son {@code href} (exigences de
     * theo-archi, squad/sprints/s15-cinq-arbitrages-de-l-en-tete/exigences-theo-etat-actif.md,
     * section 1.1). Un appui n'echange donc plus un fragment dans {@code #jeu} : c'est une
     * navigation complete, et cette route doit desormais rendre le document entier - en-tete
     * comprise, avec la puce du niveau demande marquee active par la donnee ({@code niveauActif}),
     * jamais par une regle CSS par niveau (ADR-0006). C'est aussi ce qui rend possible, ici et nulle
     * part ailleurs, de marquer la classe active : c'est la puce elle-meme qui declenche ce rendu
     * complet (section 1 de l'exigence).</p>
     */
    @GetMapping("/niveaux/{niveau}")
    public String niveaux(@PathVariable String niveau, HttpServletRequest requete, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();
        modele.addAttribute("niveaux", partie.niveauxDuCatalogue());
        modele.addAttribute("niveauActif", niveau);
        if (etat.portePartieEnCours()) {
            modele.addAttribute("urlConfirmerAbandon", "/niveaux/" + niveau + "/confirmer");
            modele.addAttribute("confirmationDirecte", true);
            return "accueil";
        }
        modele.addAttribute("jeux", partie.jeuxDuNiveau(niveau));
        return "accueil";
    }

    /**
     * Le geste que l'ecran de confirmation appelle pour quitter reellement la partie en cours
     * quand la confirmation a ete ouverte par une puce de niveau : meme abandon que
     * {@code confirmerAbandon}, mais l'accueil qui en resulte reste filtre sur ce niveau
     * (critere 6).
     *
     * <p>S16-6 (ferme S15-D, nadia-prof) : {@code niveauActif} manquait ici, alors que la phrase
     * de filtre d'{@code accueil.html} (".filtre-niveau") comme la puce de niveau active en
     * dependent toutes deux. Sans lui, cette route rendait la liste filtree sans aucune phrase
     * pour le dire - et, par la condition posee sur {@code body:has(.filtre-niveau)} (amendement
     * d'ADR-0046, s15), la puce deja marquee active dans la coquille perdait aussi sa couleur des
     * que ce fragment remplaçait #jeu. Poser {@code niveauActif} ici repare les deux symptomes
     * d'un seul geste, sans toucher la coquille : c'est la reapparition de ".filtre-niveau" dans
     * le document qui rend la condition vraie de nouveau.</p>
     */
    @PostMapping("/niveaux/{niveau}/confirmer")
    public String confirmerAbandonNiveau(@PathVariable String niveau, HttpServletRequest requete,
                                          HttpServletResponse reponse, Model modele) {
        new EtatDePartieEnSession(requete).abandonner();
        effacerMarqueurDePartie(reponse);
        modele.addAttribute("niveauActif", niveau);
        modele.addAttribute("jeux", partie.jeuxDuNiveau(niveau));
        return "accueil :: zoneAccueil";
    }

    /**
     * Le geste que l'ecran de confirmation appelle pour rester sur la partie en cours. La session
     * n'est ni lue ni ecrite pour la faire progresser : aucun essai n'est compte et aucun tirage
     * n'est renouvele. {@code PAR_UN_GESTE} et non {@code SANS_GESTE} : ADR-0027 amendement M10, le
     * premier facteur dit seulement si un geste de l'enfant a produit cet echange, ce qui est vrai
     * ici (l'enfant vient de presser "Continuer la partie") - le second facteur, calcule dans {@code
     * mancheModele} a partir du seul etat de la manche, suffit deja a ne pas rejouer le defilement
     * si cette manche a deja essuye un echec.
     */
    @PostMapping("/retour/annuler")
    public String annulerAbandon(HttpServletRequest requete, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).lire();
        if (!etat.portePartieEnCours()) {
            throw new PartieIndisponible("aucune partie en cours a reprendre");
        }
        return mancheModele(modele, etat.jeuId(), etat.consigne(), etat.consigneAdulte(), etat.session(),
                PAR_UN_GESTE);
    }

    @PostMapping("/parties/{jeuId}")
    public String demarrer(@PathVariable String jeuId, HttpServletRequest requete,
                            HttpServletResponse reponse, Model modele) {
        GameDefinition jeu = jeuOuEchec(jeuId);
        GameSession session = partie.demarrer(jeuId);
        String consigneAdulte = jeu.consigneAdulte().orElse(null);

        new EtatDePartieEnSession(requete).demarrer(jeuId, jeu.consigne(), consigneAdulte, session);
        synchroniserMarqueurDePartie(session, requete, reponse);

        return mancheModele(modele, jeuId, jeu.consigne(), consigneAdulte, session, PAR_UN_GESTE);
    }

    @PostMapping("/parties/{jeuId}/coups")
    public String jouer(@PathVariable String jeuId, @RequestParam String propositionChoisie,
                         HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).jouer(jeuId,
                actuelle -> partie.jouer(jeuId, actuelle, new BanditManchotMove(propositionChoisie)));
        synchroniserMarqueurDePartie(etat.session(), requete, reponse);

        return mancheModele(modele, jeuId, etat.consigne(), etat.consigneAdulte(), etat.session(),
                PAR_UN_GESTE, propositionChoisie);
    }

    /**
     * Le coup de la mecanique de saisie : une route et un parametre distincts de ceux du choix
     * parmi des propositions, parce que ce n'est pas le meme geste. Une route neuve entre d'elle-meme
     * dans le perimetre du garde-fou de tabulation, qui derive ses routes du registre de Spring.
     */
    @PostMapping("/parties/{jeuId}/reponses")
    public String repondre(@PathVariable String jeuId, @RequestParam String reponseEcrite,
                            HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).jouer(jeuId,
                actuelle -> partie.jouer(jeuId, actuelle, new SaisieMove(reponseEcrite)));
        synchroniserMarqueurDePartie(etat.session(), requete, reponse);

        return mancheModele(modele, jeuId, etat.consigne(), etat.consigneAdulte(), etat.session(),
                PAR_UN_GESTE);
    }

    /**
     * ADR-0080, decision 4 : le coup de la mecanique de designation - une route et un parametre
     * distincts de ceux des deux autres mecaniques, parce que ce n'est pas le meme geste. Le client
     * n'envoie jamais une coordonnee : {@code zoneDesignee} est l'identifiant que le serveur a
     * lui-meme ecrit sur le bouton presse. Une route neuve entre d'elle-meme dans le perimetre du
     * garde-fou de tabulation, qui derive ses routes du registre de Spring.
     */
    @PostMapping("/parties/{jeuId}/designations")
    public String designer(@PathVariable String jeuId, @RequestParam String zoneDesignee,
                            HttpServletRequest requete, HttpServletResponse reponse, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).jouer(jeuId,
                actuelle -> partie.jouer(jeuId, actuelle, new DesignationMove(zoneDesignee)));
        synchroniserMarqueurDePartie(etat.session(), requete, reponse);

        return mancheModele(modele, jeuId, etat.consigne(), etat.consigneAdulte(), etat.session(),
                PAR_UN_GESTE);
    }

    /**
     * Le geste qui suit la revelation sur un ecran de saisie. Il resout la manche sans la compter
     * comme une reussite, exactement comme l'appui sur la reponse nommee chez l'autre mecanique -
     * la difference est qu'il n'y a plus rien a ecrire a ce moment-la, donc plus de champ a l'ecran.
     */
    @PostMapping("/parties/{jeuId}/manche")
    public String mancheSuivante(@PathVariable String jeuId, HttpServletRequest requete,
                                  HttpServletResponse reponse, Model modele) {
        EtatDePartie etat = new EtatDePartieEnSession(requete).jouer(jeuId,
                actuelle -> partie.jouer(jeuId, actuelle, new ContinuerMove()));
        synchroniserMarqueurDePartie(etat.session(), requete, reponse);

        return mancheModele(modele, jeuId, etat.consigne(), etat.consigneAdulte(), etat.session(),
                PAR_UN_GESTE);
    }

    /**
     * ADR-0020 : une partie terminee n'a plus de manche a montrer. Le controle est ici, dans le seul
     * point de passage de l'ecran de jeu, et non dans chaque route : toute route qui demande cet
     * ecran obtient l'ecran de fin de partie a la place d'un ecran mort. Symetrique du choix
     * d'ADR-0019, qui a ferme le meme cas cote commande.
     *
     * <p>ADR-0027 : {@code gesteDeLEnfant} dit si l'enfant a presse quelque chose pour obtenir cette
     * reponse. Le serveur est le seul a le savoir : une fois le fragment insere, le document assemble
     * est le meme qu'il vienne d'un echange htmx ou du chargement complet de la page de reprise, donc
     * aucun selecteur CSS ne peut les distinguer. C'est un <b>parametre</b> et non un attribut pose a
     * cote, pour que le compilateur oblige chaque point d'appel - ceux d'aujourd'hui comme ceux de
     * demain - a se prononcer : la derivation est totale parce que ce point de passage est unique
     * (meme raison qu'ADR-0020 ci-dessus). Le mode de defaillance d'un oubli est l'immobilite, jamais
     * un mouvement que l'enfant n'a pas demande.</p>
     *
     * <p><b>ADR-0027, amendement du 2026-08-13 (condition M10 de nadia-prof).</b> Le sujet de
     * {@code data-arrivee} est <b>le tirage que les colonnes montrent</b>, pas la reponse HTTP. Un
     * geste suffisait a poser le marqueur ; il ne suffit plus, parce qu'un geste ne renouvelle pas
     * toujours le tirage : un echec avec des essais restants, l'entree en revelation et un appui a
     * cote apres la revelation rendent le meme tirage, sur lequel un mouvement dirait que la machine
     * vient de distribuer alors que rien n'a ete distribue. La condition est donc le produit des deux
     * facteurs, et le second - "ce tirage est neuf pour l'enfant" - est calcule <b>ici</b> et non aux
     * points d'appel : c'est le seul placement qui n'exempte aucune route et qui couvre les points
     * d'appel de demain comme ceux d'aujourd'hui.</p>
     *
     * <p>M1, M5, M6, M9, M11, M12, M13 : le message sous le meuble, l'indice, la revelation et le
     * point de focus sont tous calcules ici, a partir du seul etat de la manche courante - jamais
     * d'un champ neuf sur la session (avis de faisabilite de theo-archi, s10, correction 2).</p>
     */
    private String mancheModele(Model modele, String jeuId, String consigne, String consigneAdulte,
                                GameSession session, boolean gesteDeLEnfant) {
        return mancheModele(modele, jeuId, consigne, consigneAdulte, session, gesteDeLEnfant, null);
    }

    /**
     * M14 (nadia-prof, arbitrage du 2026-08-13) : sur un appui a cote apres la revelation (M5), la
     * proposition pressee recoit la marque barree de M9 - et seulement elle, aucun essai compte,
     * aucun message different, aucun mouvement. {@code BanditManchotSession.jouer} ne l'ajoute pas
     * a {@code propositionsEssayees} dans cette branche precise, puisque rien d'autre n'y bouge
     * (voir son commentaire sur la branche {@code revelee}) ; la meme condition -
     * {@code revelee() && !propositionFraichementPressee.equals(reponseRevelee())} - est donc
     * dupliquee ici, pour l'affichage seul, sans ajouter le moindre champ a la session
     * (avis de faisabilite de theo-archi, s10, correction 2). {@code propositionFraichementPressee}
     * vaut {@code null} pour tout point d'appel qui ne vient pas d'un coup ({@code
     * accueil}, {@code demarrer}) : theo-archi a refuse une comparaison par identite de reference
     * comme protocole non documente entre les deux surcharges, d'ou ce parametre explicite.
     */
    private String mancheModele(Model modele, String jeuId, String consigne, String consigneAdulte,
                                GameSession session, boolean gesteDeLEnfant,
                                String propositionFraichementPressee) {
        if (session instanceof BanditManchotSession banditSession) {
            return mancheDeBanditManchot(modele, jeuId, consigne, consigneAdulte, banditSession,
                    gesteDeLEnfant, propositionFraichementPressee);
        }
        if (session instanceof SaisieSession saisieSession) {
            return mancheDeSaisie(modele, jeuId, consigne, consigneAdulte, saisieSession);
        }
        if (session instanceof DesignationSession designationSession) {
            return mancheDeDesignation(modele, jeuId, consigne, consigneAdulte, designationSession);
        }
        throw new PartieIndisponible(
                "aucun ecran de jeu pour ce type de session : " + session.getClass().getSimpleName());
    }

    /**
     * ADR-0058, R-A : <b>le point de descente nomme</b> de la mecanique existante. C'est ici, et
     * dans la seule autre branche de {@code mancheModele}, que ce module descend du contrat vers un
     * type concret de mecanique ; partout ailleurs dans {@code adapter-web}, la session voyage par
     * {@code GameSession}. Deux branches nommees plutot qu'une abstraction de dispatch : une
     * abstraction ecrite pour deux cas est une abstraction ecrite pour un usage imagine, et
     * {@code theo-archi} recommande cette forme-la tant que deux mecaniques existent.
     */
    private String mancheDeBanditManchot(Model modele, String jeuId, String consigne,
                                          String consigneAdulte, BanditManchotSession session,
                                          boolean gesteDeLEnfant,
                                          String propositionFraichementPressee) {
        if (session.status() == SessionStatus.TERMINEE) {
            return retourModele(modele, jeuId, session);
        }
        boolean fraiche = session.propositionsEssayees().isEmpty();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("consigne", consigne);
        // ADR-0075 section 7, VOI-12 : le stimulus (propre a l'item joue) et la mise en garde
        // (invariante sur tout le paquet, fixee au demarrage de la partie comme la consigne) sont
        // combines en un seul objet - BlocAdulte.creerOuNull leve plutot que de poser l'un sans
        // l'autre (yanis-qa a mesure que deux attributs de modele independants laissaient passer un
        // bloc-adulte incomplet des que le second manquait, sans passer par le garde-fou de contenu
        // qui ne garde que le fichier YAML, jamais le document rendu). VOI-8 :
        // session.texteAVoixHauteManche() ne depend d'aucun etat d'essai ni de revelation, donc ce
        // bloc reste identique sur un item frais, un echec et la revelation - jamais vide au
        // changement d'etat.
        modele.addAttribute("blocAdulte",
                BlocAdulte.creerOuNull(session.texteAVoixHauteManche().orElse(null), consigneAdulte));
        // ADR-0050 : le texte-support d'un item de comprehension, vide sur tout item qui n'en porte
        // pas - le gabarit ne rend alors aucun paragraphe (th:if), jamais une balise vide.
        modele.addAttribute("texteSupport", session.texteSupportManche().orElse(null));
        // ADR-0099 : la grille d'un item d'extraction, vide sur tout item qui n'en porte pas - le
        // gabarit ne rend alors aucune table (th:if). L'objet voyage structure jusqu'au gabarit :
        // aucune mise en colonnes n'est calculee ici, c'est le moteur de rendu qui la fait et non
        // la police servie (ADR-0092).
        modele.addAttribute("tableauSupport", session.tableauSupportManche().orElse(null));
        // ADR-0073 : l'item declare un identifiant, cet adapter est le seul endroit du produit qui
        // sache ou le fichier est servi et sous quelle extension - le gabarit compose les trois
        // termes, le contenu n'en connait qu'un. Vide sur tout item qui ne montre rien : le gabarit
        // ne rend alors aucun bloc (th:if), jamais une image cassee.
        modele.addAttribute("illustration", session.illustrationManche().orElse(null));
        modele.addAttribute("illustrationTexteAlternatif",
                session.illustrationTexteAlternatifManche().orElse(null));
        modele.addAttribute("segmentsEnonce", segmentsEnonce(session));
        modele.addAttribute("propositions", session.propositions());
        boolean propositionsEnImage = propositionsEnImage(session.propositions());
        modele.addAttribute("propositionsEnImage", propositionsEnImage);
        // ADR-0104 decision 4, point 4 : le gabarit ne peut pas distinguer une revelation d'un echec
        // par focusCibles (la derniere proposition pressee, ou la bonne) ; il lui faut ce booleen
        // pour poser la marque de la bonne oeuvre (proposition d'ines-design, section 6).
        modele.addAttribute("mancheRevelee", session.revelee());
        modele.addAttribute("score", session.score());
        modele.addAttribute("indice", session.indice().orElse(null));
        // M10 : le produit des deux facteurs, jamais le seul geste. Retirer "&& fraiche" rend a
        // l'enfant qui se trompe un rebalayage de colonnes par essai (nadia-prof, condition M10).
        modele.addAttribute("arriveeParUnGeste", gesteDeLEnfant && fraiche);
        modele.addAttribute("propositionsEssayees", propositionsEssayeesAffichees(
                session, propositionFraichementPressee));

        String message = null;
        boolean signalJuste = false;
        String reponseRevelee = null;
        String explication = null;
        String focus = FOCUS_ENONCE;
        Set<String> focusCibles = Set.of();

        // ADR-0083, REV-1 : la reponse voyage seule vers le gabarit, jamais enchassee dans une
        // phrase composee ici. L'expression que cette branche portait jusqu'a s29 -
        // "C'est " + reponse + ". Appuie sur " + reponse + "." - supposait que toute bonne reponse
        // fut un groupe nominal ; sur douze items de quatre paquets servis, elle donnait "C'est ce
        // n'est pas un etre vivant." (nadia-prof, passe transverse de s29, verifie a l'ecran). Les
        // mots invariants vivent desormais dans manche.html, la valeur de contenu dans le modele, et
        // rien ne les concatene.
        if (session.revelee()) {
            String reponse = session.reponseRevelee().orElseThrow();
            message = MESSAGE_REVELATION;
            // ADR-0104 decision 4, point 4 : jamais un identifiant en texte ; la bonne oeuvre est
            // deja designee par focusCibles et par mancheRevelee.
            reponseRevelee = propositionsEnImage ? null : reponse;
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
        modele.addAttribute("reponseRevelee", reponseRevelee);
        modele.addAttribute("explication", explication);
        modele.addAttribute("focus", focus);
        modele.addAttribute("focusCibles", focusCibles);
        return "fragments/manche :: manche";
    }

    /**
     * ADR-0058, R-A : <b>le point de descente nomme</b> de la mecanique de saisie, seconde branche
     * de {@code mancheModele}. Ce qui vaut pour la premiere vaut ici : le modele est calcule a
     * partir du seul etat de la manche, jamais d'un champ neuf sur la session.
     *
     * <p><b>Trois etats, et un seul texte a l'ecran a la fois.</b> Manche fraiche : rien, ou le
     * retour de la manche precedente si elle a ete reussie. Refus avec des essais restants : la
     * chaine reste dans le champ, corrigeable, et la reponse attendue n'est <b>jamais</b> nommee -
     * ni par un mot, ni par une marque (SAI-9a). Revelation : le champ disparait, il n'y a plus
     * rien a ecrire, et les deux chaines sont montrees ensemble (SAI-9b).</p>
     *
     * <p><b>D5, arbitrage de nadia-prof du 2026-08-31 - variante A sous condition.</b> Le champ
     * garde la tentative refusee et elle y est corrigeable, parce que le mot qui fonde SAI-9a est
     * <i>corriger</i> et qu'un champ vide ne se corrige pas. Sa condition : au moment ou l'enfant
     * peut appuyer pour renvoyer, la chaine du champ doit lui etre montree <b>comme deja
     * refusee</b> - sans quoi elle porte deux sens a la fois, "ce que tu as ecrit, refuse" et "ce
     * que tu vas envoyer", et un enfant de six ans n'en tient qu'un. La forme qui sert cette
     * condition ici est celle que nadia-prof decrit elle-meme : la meme chaine barree dans la liste
     * au-dessus du champ. Ce n'est pas une repetition, elle l'ecrit - "l'une dit refusee, l'autre
     * dit modifiable".</p>
     *
     * <p><b>SAI-12 : la liste porte toutes les tentatives de la manche, la derniere comprise.</b>
     * C'est la reponse de nadia-prof du 2026-08-30 - "toutes celles de la manche, cumulativement" -
     * et elle la reconfirme le 2026-08-31 contre la description de la variante A, qui laissait la
     * liste vide au deuxieme essai. Les chaines sont brutes, jamais repliees, et une chaine retapee
     * a l'identique y figure deux fois : c'est exactement ce que SAI-12 existe pour montrer.</p>
     */
    private String mancheDeSaisie(Model modele, String jeuId, String consigne,
                                   String consigneAdulte, SaisieSession session) {
        if (session.status() == SessionStatus.TERMINEE) {
            return retourDeSaisie(modele, jeuId, session);
        }
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("consigne", consigne);
        // ADR-0075 section 7, VOI-12 : meme geste que sur mancheDeBanditManchot - voir son
        // commentaire. La ligne "ecrire un mot en autonomie" est la seule des cinq lignes ouvertes
        // par nadia-prof a passer par cette mecanique (ADR-0075 section 5).
        // ADR-0075 section 7, VOI-12 : meme geste que sur mancheDeBanditManchot - voir son
        // commentaire.
        modele.addAttribute("blocAdulte",
                BlocAdulte.creerOuNull(session.texteAVoixHauteManche().orElse(null), consigneAdulte));
        modele.addAttribute("enonce", session.enonceManche().orElseThrow());
        modele.addAttribute("score", session.score());
        modele.addAttribute("tentatives", session.tentatives());
        modele.addAttribute("mancheResolue", session.revelee());
        modele.addAttribute("brouillon", session.revelee() ? null
                : session.derniereTentative().orElse(null));

        String message = null;
        boolean signalJuste = false;
        String chaineJugee = null;
        String formeDeReference = null;
        String explication = null;
        String indice = session.indice().orElse(null);
        String focus = FOCUS_ENONCE;
        boolean focusSurLeChamp = false;
        boolean focusSurLaSuite = false;

        if (session.revelee()) {
            message = MESSAGE_REVELATION;
            chaineJugee = session.derniereTentative().orElseThrow();
            formeDeReference = session.formeDeReferenceRevelee().orElseThrow();
            explication = session.explicationRevelee().orElse(null);
            focus = FOCUS_BOUTON;
            focusSurLaSuite = true;
        } else if (!session.tentatives().isEmpty()) {
            message = MESSAGE_ECHEC;
            focus = FOCUS_BOUTON;
            focusSurLeChamp = true;
        } else if (session.dernierVerdict().map(SaisieVerdict::juste).orElse(false)) {
            SaisieVerdict verdict = session.dernierVerdict().orElseThrow();
            message = MESSAGE_BRAVO;
            signalJuste = true;
            chaineJugee = chaineJugeeMontreeALaReussite(verdict);
            formeDeReference = formeDeReferenceMontreeALaReussite(verdict);
        }

        modele.addAttribute("message", message);
        modele.addAttribute("signalJuste", signalJuste);
        modele.addAttribute("chaineJugee", chaineJugee);
        modele.addAttribute("formeDeReference", formeDeReference);
        modele.addAttribute("explication", explication);
        modele.addAttribute("indice", indice);
        modele.addAttribute("focus", focus);
        modele.addAttribute("focusSurLeChamp", focusSurLeChamp);
        modele.addAttribute("focusSurLaSuite", focusSurLaSuite);
        return "fragments/saisie :: saisie";
    }

    /**
     * ADR-0080, ADR-0058 R-A : <b>le point de descente nomme</b> de la mecanique de designation,
     * troisieme branche de {@code mancheModele}. Meme boucle que {@code mancheDeBanditManchot} -
     * trois essais, indice des le premier echec, revelation au troisieme -, sans melange d'ordre :
     * les zones sont rendues dans l'ordre exact ou le contenu les declare (ADR-0080, decision 3 et
     * 4), qui est aussi l'ordre de tabulation.
     *
     * <p>Aucun segment d'enonce ici (ADR-0062) : {@code ZoneADesigner} ne declare pas de part
     * designee, la designation portant deja sur un endroit de l'image et non sur un mot de l'enonce.
     * Aucun texte-support non plus (ADR-0050) : le contrat de l'item ne le declare pas
     * (ADR-0080, decision 1).</p>
     */
    private String mancheDeDesignation(Model modele, String jeuId, String consigne,
                                        String consigneAdulte, DesignationSession session) {
        if (session.status() == SessionStatus.TERMINEE) {
            return retourDeDesignation(modele, jeuId, session);
        }
        boolean fraiche = session.zonesEssayees().isEmpty();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("consigne", consigne);
        modele.addAttribute("blocAdulte",
                BlocAdulte.creerOuNull(session.texteAVoixHauteManche().orElse(null), consigneAdulte));
        modele.addAttribute("enonce", session.enonceManche().orElseThrow());
        // ADR-0080, decision 1 : illustration et son texte alternatif sont obligatoires sur cette
        // forme d'item - jamais null ici, a la difference de mancheDeBanditManchot.
        modele.addAttribute("illustration", session.illustrationManche().orElseThrow());
        modele.addAttribute("illustrationTexteAlternatif",
                session.illustrationTexteAlternatifManche().orElseThrow());
        modele.addAttribute("zones", session.zonesManche());
        modele.addAttribute("zonesEssayees", session.zonesEssayees());
        modele.addAttribute("score", session.score());
        modele.addAttribute("indice", session.indice().orElse(null));
        // ADR-0094, decision 4.4 : l'enum de contenu est converti en un seul booleen de vue ici,
        // la seule couche qui a le droit de connaitre les deux cotes - il n'entre jamais dans le
        // modele et donc jamais dans le gabarit.
        modele.addAttribute("libelleDeZoneHorsDeLaBoite",
                session.emplacementDuLibelleDeZoneManche()
                        .orElse(EmplacementDuLibelleDeZone.DANS_LA_BOITE)
                        == EmplacementDuLibelleDeZone.HORS_DE_LA_BOITE);

        String message = null;
        boolean signalJuste = false;
        String explication = null;
        String focus = FOCUS_ENONCE;
        Set<String> focusCibles = Set.of();

        if (session.revelee()) {
            message = MESSAGE_REVELATION;
            explication = session.explicationRevelee().orElse(null);
            focus = FOCUS_BOUTON;
            focusCibles = session.bonneZoneRevelee().map(Set::of).orElse(Set.of());
        } else if (!fraiche) {
            message = MESSAGE_ECHEC;
            focus = FOCUS_BOUTON;
            focusCibles = session.derniereTentative().map(Set::of).orElse(Set.of());
        } else if (session.dernierVerdict().map(DesignationVerdict::juste).orElse(false)) {
            message = MESSAGE_BRAVO;
            signalJuste = true;
        }

        modele.addAttribute("message", message);
        modele.addAttribute("signalJuste", signalJuste);
        modele.addAttribute("explication", explication);
        modele.addAttribute("focus", focus);
        modele.addAttribute("focusCibles", focusCibles);
        return "fragments/designation :: designation";
    }

    /**
     * SAI-2c : sur un item en forme exacte, la chaine jugee est montree <b>aussi</b> a la reussite.
     * Sur un item en forme libre, elle ne l'est pas : ce que l'enfant a ecrit y a ete accepte tel
     * quel ou par une tolerance, et c'est la forme de reference qui l'interesse alors, pas sa
     * propre chaine.
     */
    private static String chaineJugeeMontreeALaReussite(SaisieVerdict verdict) {
        return verdict.exigenceDeForme() == ExigenceDeForme.FORME_EXACTE
                ? verdict.chaineJugee() : null;
    }

    /**
     * SAI-8 : quand une reponse est acceptee par une tolerance, le retour montre la forme de
     * reference, integralement accentuee et sans faute. La condition est derivee de ce que le
     * verdict porte - une tolerance a joue si la chaine recue n'est pas la forme de reference elle-
     * meme - et jamais d'un drapeau que le domaine aurait pose : le domaine transporte, il ne
     * decide pas d'afficher.
     */
    private static String formeDeReferenceMontreeALaReussite(SaisieVerdict verdict) {
        return verdict.chaineJugee().equals(verdict.formeDeReference())
                ? null : verdict.formeDeReference();
    }

    /**
     * L'ecran de fin de partie d'une partie de saisie. Le meme gabarit que celui de l'autre
     * mecanique : ce que cet ecran montre - le score, le verdict de la derniere manche resolue, la
     * reponse attendue et l'explication - ne depend pas de la facon dont l'enfant a repondu.
     */
    private String retourDeSaisie(Model modele, String jeuId, SaisieSession session) {
        SaisieVerdict verdict = session.dernierVerdict().orElseThrow();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("score", session.score());
        modele.addAttribute("juste", verdict.juste());
        modele.addAttribute("bonneReponse", verdict.formeDeReference());
        // ADR-0084, REV-3 : meme geste que sur l'autre mecanique, meme motif.
        modele.addAttribute("enonceRevele", verdict.enonce());
        modele.addAttribute("explication", verdict.explication().orElse(null));
        // S33-PAL2-TROU-A, S33-PAL2-TROU-B : sur cette mecanique, la question rappelee et le bloc
        // de reponse restent lies au meme etat qu'avant ce lot - voir le commentaire de
        // retourDeDesignation pour le motif qui les decouple sur la troisieme mecanique.
        modele.addAttribute("questionRappeleeVisible", !verdict.juste());
        modele.addAttribute("reponseAttendueVisible", !verdict.juste());
        return "fragments/retour :: retour";
    }

    /**
     * <b>S33-PAL2-TROU-A, S33-PAL2-TROU-B (ADR-0088) : l'ecran de fin de partie d'une designation.</b>
     * Meme gabarit que les deux autres mecaniques, avec deux differences que {@code nadia-prof} a
     * tranchees (arbitrage prealable de s32, section 17.6) :
     *
     * <ul>
     *   <li><b>Trou A</b> - la question rappelee est portee <b>quel que soit le verdict</b> - juste,
     *   fausse ou par revelation -, jamais seulement sur un echec comme sur les deux autres
     *   mecaniques : a cet instant le dessin n'est plus a l'ecran, et c'est la seule trace du terrain
     *   sur lequel l'enfant vient de jouer, y compris quand il a reussi.</li>
     *   <li><b>Trou B</b> - le bloc de reponse n'est <b>jamais</b> rendu : {@code DesignationVerdict}
     *   ne porte plus de zone du tout depuis ADR-0088 clause B - ni un identifiant technique, ni un
     *   libelle positionnel qui n'apprendrait rien. La revelation reste sur l'ecran de manche, ou le
     *   dessin est encore la, par {@code DesignationSession.bonneZoneRevelee()}.</li>
     * </ul>
     */
    private String retourDeDesignation(Model modele, String jeuId, DesignationSession session) {
        DesignationVerdict verdict = session.dernierVerdict().orElseThrow();
        modele.addAttribute("jeuId", jeuId);
        modele.addAttribute("score", session.score());
        modele.addAttribute("juste", verdict.juste());
        modele.addAttribute("enonceRevele", verdict.enonce());
        modele.addAttribute("explication", verdict.explication().orElse(null));
        modele.addAttribute("questionRappeleeVisible", true);
        modele.addAttribute("reponseAttendueVisible", false);
        return "fragments/retour :: retour";
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
        // ADR-0104 decision 3 et 4, point 5 : quand la bonne reponse est une reproduction, ni
        // l'identifiant ni un libelle n'apprend rien a cet ecran (precedent d'ADR-0088, trou B).
        boolean reponseEnImage = verdict.bonneReponse().startsWith(PREFIXE_DE_REPRODUCTION);
        modele.addAttribute("bonneReponse", reponseEnImage ? null : verdict.bonneReponse());
        // ADR-0084, REV-3 : l'enonce de la manche que ce verdict commente, appose au modele a cote
        // de sa reponse et jamais dedans. Il vient du verdict et de nulle part ailleurs, parce qu'a
        // cet instant la session n'a plus de manche courante - c'est exactement pourquoi la reponse
        // etait orpheline.
        modele.addAttribute("enonceRevele", verdict.enonce());
        modele.addAttribute("explication", verdict.explication().orElse(null));
        // S33-PAL2-TROU-A, S33-PAL2-TROU-B : voir le commentaire de retourDeDesignation.
        modele.addAttribute("questionRappeleeVisible", !verdict.juste());
        modele.addAttribute("reponseAttendueVisible", !verdict.juste() && !reponseEnImage);
        return "fragments/retour :: retour";
    }

    /**
     * ADR-0062 : derive de l'enonce et de la part designee la suite de segments que le gabarit rend
     * en une boucle unique. Une manche qui ne designe rien rend un seul segment non designe - la
     * phrase telle quelle, sans branche ni valeur factice. Cette derivation cherche une valeur
     * declaree, jamais une ponctuation : son unique mode de defaillance (une part declaree absente de
     * l'enonce, ou qui y figure plus d'une fois) est ferme par un garde-fou de contenu, pas ici.
     */
    private static List<EnonceSegment> segmentsEnonce(BanditManchotSession session) {
        String enonce = session.enonceManche().orElse("");
        Optional<String> partDesignee = session.partDesigneeManche();
        if (partDesignee.isEmpty()) {
            return List.of(new EnonceSegment(enonce, false));
        }
        String part = partDesignee.orElseThrow();
        int debut = enonce.indexOf(part);
        int fin = debut + part.length();
        List<EnonceSegment> segments = new ArrayList<>();
        segments.add(new EnonceSegment(enonce.substring(0, debut), false));
        segments.add(new EnonceSegment(part, true));
        segments.add(new EnonceSegment(enonce.substring(fin), false));
        return segments;
    }

    /** M14 : voir le commentaire de {@code mancheModele} sur cette meme condition. */
    private static Set<String> propositionsEssayeesAffichees(BanditManchotSession session,
                                                              String propositionFraichementPressee) {
        Set<String> propositionsEssayees = session.propositionsEssayees();
        if (session.revelee() && propositionFraichementPressee != null
                && !propositionFraichementPressee.equals(session.reponseRevelee().orElseThrow())) {
            Set<String> avecLaPressee = new LinkedHashSet<>(propositionsEssayees);
            avecLaPressee.add(propositionFraichementPressee);
            return avecLaPressee;
        }
        return propositionsEssayees;
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

    private static void synchroniserMarqueurDePartie(GameSession session,
                                                      HttpServletRequest requete, HttpServletResponse reponse) {
        if (session.status() == SessionStatus.TERMINEE) {
            effacerMarqueurDePartie(reponse);
        } else {
            marquerPartieEnCours(requete, reponse);
        }
    }

    /**
     * ADR-0047, DEC-0036 : le marqueur ne doit jamais rester valide au-dela du moment ou la
     * session aurait de toute facon expire par inactivite. Sa duree de validite est donc lue sur
     * cette meme session, jamais chiffree ici ni dans une configuration - EtatDePartieEnSession
     * est le seul type autorise a la lire (ADR-0019).
     */
    private static void marquerPartieEnCours(HttpServletRequest requete, HttpServletResponse reponse) {
        Cookie cookie = new Cookie(COOKIE_PARTIE_EN_COURS, "1");
        cookie.setPath("/");
        cookie.setMaxAge(new EtatDePartieEnSession(requete).dureeInactiviteMaximale());
        reponse.addCookie(cookie);
    }

    private static void effacerMarqueurDePartie(HttpServletResponse reponse) {
        Cookie cookie = new Cookie(COOKIE_PARTIE_EN_COURS, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        reponse.addCookie(cookie);
    }
}
