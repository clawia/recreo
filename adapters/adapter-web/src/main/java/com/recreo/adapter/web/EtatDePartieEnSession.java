package com.recreo.adapter.web;

import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.util.WebUtils;

import java.util.function.UnaryOperator;

/**
 * Le seul type du depot qui touche la session HTTP (ADR-0019). Tout cycle lire-calculer-ecrire sur
 * l'etat de partie passe par une methode de cette classe, et chacune s'execute sous le verrou de la
 * session : deux requetes de la meme session ne peuvent plus calculer leur etat suivant a partir du
 * meme etat de depart, ni s'ecraser l'une l'autre.
 *
 * <p>Le verrou est celui que designe {@code WebUtils}, pas l'objet {@code HttpSession} lui-meme :
 * le conteneur ne garantit pas de rendre le meme objet a deux requetes d'une meme session, et un
 * verrou pris sur deux objets differents ne verrouille rien. {@code HttpSessionMutexListener},
 * enregistre dans bootstrap, pose l'objet stable que {@code WebUtils} lit.</p>
 *
 * <p>Le calcul du coup est recu en parametre plutot qu'appele ici : cette classe ne connait ni le
 * catalogue ni la mecanique. L'operateur recu est un appel au cas d'usage, sans entree-sortie et
 * sans verrou a lui - c'est ce qui rend acceptable de l'executer sous le verrou de la session.</p>
 */
final class EtatDePartieEnSession {

    private static final String ATTRIBUT_JEU_ID = "recreo.jeuId";
    private static final String ATTRIBUT_CONSIGNE = "recreo.consigne";
    private static final String ATTRIBUT_SESSION = "recreo.session";

    private final HttpSession httpSession;

    EtatDePartieEnSession(HttpServletRequest requete) {
        this.httpSession = requete.getSession();
    }

    EtatDePartie lire() {
        synchronized (verrou()) {
            return instantane();
        }
    }

    void demarrer(String jeuId, String consigne, BanditManchotSession session) {
        synchronized (verrou()) {
            httpSession.setAttribute(ATTRIBUT_JEU_ID, jeuId);
            httpSession.setAttribute(ATTRIBUT_CONSIGNE, consigne);
            httpSession.setAttribute(ATTRIBUT_SESSION, session);
        }
    }

    void abandonner() {
        synchronized (verrou()) {
            httpSession.removeAttribute(ATTRIBUT_JEU_ID);
            httpSession.removeAttribute(ATTRIBUT_CONSIGNE);
            httpSession.removeAttribute(ATTRIBUT_SESSION);
        }
    }

    /**
     * Lit l'etat, calcule le suivant et l'ecrit sans qu'aucune autre requete de la meme session ne
     * puisse s'intercaler. Rend l'instantane complet - session et consigne - et non la seule
     * session : {@code PartieController.mancheModele} a besoin de la consigne pour rendre l'ecran
     * de jeu, et elle n'est lisible que sous ce meme verrou (S10, avis de faisabilite de
     * theo-archi, section 1, correction 4). Un second {@code lire()} hors de ce verrou aurait
     * fonctionne mais rouvert un second acces a la session HTTP pour une donnee que ce cycle tient
     * deja.
     *
     * <p>Un coup recu sur une partie deja terminee ne rejoue rien et rend l'etat final tel quel.
     * C'est le second appui d'un double appui sur la derniere manche : la mecanique refuse a juste
     * titre de jouer une manche qui n'existe plus, et c'est a cet appelant-ci de ne pas la lui
     * demander. Consequence visible par l'enfant, a juger par nadia-prof : il revoit l'ecran de fin
     * de partie que son premier appui a produit, au lieu de l'ecran d'erreur.</p>
     */
    EtatDePartie jouer(String jeuId, UnaryOperator<BanditManchotSession> coup) {
        synchronized (verrou()) {
            EtatDePartie etat = instantane();
            if (!etat.porte(jeuId)) {
                throw new PartieIndisponible("aucune partie en cours pour le jeu " + jeuId);
            }
            BanditManchotSession actuelle = etat.session();
            if (actuelle.status() == SessionStatus.TERMINEE) {
                return etat;
            }
            BanditManchotSession suivante = coup.apply(actuelle);
            httpSession.setAttribute(ATTRIBUT_SESSION, suivante);
            return new EtatDePartie(etat.jeuId(), etat.consigne(), suivante);
        }
    }

    private EtatDePartie instantane() {
        return new EtatDePartie(
                (String) httpSession.getAttribute(ATTRIBUT_JEU_ID),
                (String) httpSession.getAttribute(ATTRIBUT_CONSIGNE),
                (BanditManchotSession) httpSession.getAttribute(ATTRIBUT_SESSION));
    }

    private Object verrou() {
        return WebUtils.getSessionMutex(httpSession);
    }
}
