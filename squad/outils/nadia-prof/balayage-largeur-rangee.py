#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Balayage de la largeur offerte a la rangee de propositions, sur l'application servie.

Pourquoi ce script existe. Le depot connait une largeur de rangee **suffisante** pour que les items
de "Les synonymes" cessent de se replier sur deux rangees (680 px, mesuree a s18) ; il n'a jamais
connu la largeur **necessaire**. C'est ce chiffre qui separe deux verdicts opposes sur SYN-16 /
BPM-8 (theo-archi, avis de faisabilite de s19, section 6). Le mesurer demande un navigateur : aucun
test de ce depot ne lit une position rendue.

Methode, identique a celle des campagnes de s18 (FICHE-bandit-manchot-synonymes-cm1.md, "Methode -
regle injectee") : navigateur reel pilote par son protocole de deverminage, viewport de reference
DEC-0040, deviceScaleFactor a 1, mode non mobile, application servie depuis le fat jar de l'arbre
mesure. La declaration de largeur est **injectee** en dernier dans le head, la ou recreo.css est
chargee (ADR-0014 point 5) - la feuille n'est jamais modifiee.

Ce que le script fait de plus qu'un balayage naif, et c'est ce qui le rend abordable : la largeur
est balayee **sur la manche affichee**, sans recharger ni retirer l'item. Chaque item visite rend
donc son propre seuil par recherche dichotomique, au pixel.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh). Ses
**juges** en revanche sont purs et sont, eux, executes par la CI : voir balayage-largeur-rangee-test.sh.

Trois refus, ajoutes a s21 (theo-archi, 2026-08-27) apres qu'une campagne de ce jour a perdu 8 jeux
sur 17 sans le dire. Le mode de defaillance corrige n'est pas l'echec, c'est l'ecart muet : ce module
decrivait ses conditions de mesure par ses parametres au lieu de les lire de ce qui s'est passe.

  1. **Le port de deverminage.** Le script s'attachait au premier navigateur qui repondait sur un
     port fixe, sans verifier que c'etait le sien. **Le port n'est plus fixe** : il vaut 0 par
     defaut, c'est-a-dire que le systeme en attribue un libre, et le script lit lequel dans le
     fichier DevToolsActivePort de SON propre profil - un repertoire temporaire cree par cette
     execution et par elle seule. La collision devient impossible par construction plutot que
     detectee apres coup. Quand un port est **force** (PORT_DEV non nul), le script sonde ce port
     AVANT de lancer quoi que ce soit et refuse s'il repond deja : c'est alors la seule garde
     disponible, Chrome n'ecrivant pas DevToolsActivePort lorsqu'un port lui est impose (verifie le
     2026-08-27 sur Chrome 151.0.7922.174, les deux modes essayes sur le poste de mesure).
  2. **La regle injectee.** Elle etait posee puis supposee encore la. Elle est desormais relue dans
     le document a chaque lecture d'etat, et une disparition (navigation complete) ou une alteration
     est un refus, pas un silence.
  3. **Le viewport du rapport.** Il etait recopie de l'argument. Il est desormais lu du navigateur
     (window.innerWidth / innerHeight) et compare a ce qui a ete demande ; un ecart est un refus.

Ces trois refus levent MesureNonFiable et ne rendent aucun rapport : un rapport partiel serait
exactement l'ecart muet qu'ils ferment.

Prerequis nommes, jamais leur emplacement (ADR-0010) :
  - un Chrome ou Chromium designe par la variable d'environnement CHROME, ou sur le PATH ;
  - l'application servie, dont l'URL de base est passee en argument ;
  - le module python "websocket-client" ;
  - la variable PAQUET, facultative, qui designe le fichier de contenu servi : elle ne sert qu'a
    repondre juste pour avancer d'une manche a la suivante sans user les trois essais de M5.

Usage : python squad/outils/nadia-prof/balayage-largeur-rangee.py <url_de_base> <id_du_jeu> [json_de_sortie]
"""

import json
import os
import shutil
import subprocess
import sys
import tempfile
import time
import urllib.request

# "websocket" (le module websocket-client) n'est PAS importe ici, et c'est une decision, pas un
# oubli - voir ouvrir_connexion. Un import de bibliotheque tierce au niveau du module rendrait les
# juges purs de ce fichier inatteignables sans elle : charger le module pour appeler
# juger_viewport ferait echouer l'import bien avant le moindre jugement. C'est exactement ce qui
# est arrive le 2026-08-28 - l'etape "Juges purs des outils de poste" de la CI a rougi sur les 23
# cas d'un coup, sans en juger un seul, sur un runner qui n'a pas cette bibliotheque.

VIEWPORT_LARGEUR = 744
VIEWPORT_HAUTEUR = 551
# Rembourrage de .container (24 px) + bordure de .cabinet (16 px). Arithmetique d'ADR-0054,
# confirmee sur le rendu a s18 : la rangee vaut le plafond de .zone-jeu moins cette somme.
RETRAIT_RANGEE_PX = 40
# Bornes du balayage. La borne basse est nettement sous la rangee servie aujourd'hui dans la bande
# 576-719 px (500 px) ; la borne haute est sous la seule largeur connue suffisante (680 px).
RANGEE_MIN_PX = 400
RANGEE_MAX_PX = 700
# 0, et non un numero : le systeme attribue un port libre et Chrome ecrit lequel dans le
# DevToolsActivePort de notre propre profil. Un port fixe etait la cause du defaut de s21 - deux
# executions simultanees s'attachaient au meme navigateur, et c'est celle qui n'avait pas lance ce
# navigateur qui rendait un chiffre faux sans le dire.
PORT_DEVERMINAGE = 0

# La declaration injectee porte le meme selecteur que celle qui est servie ; seule la valeur du
# plafond varie, et elle est posee sans borne de media pour que le balayage ne depende pas du
# viewport auquel on mesure. Elle est posee en dernier dans le head, apres recreo.css.
POSER_STYLE = """
(function(largeurRangee){
  var noeud = document.getElementById('mesure-nadia');
  if (!noeud) {
    noeud = document.createElement('style');
    noeud.id = 'mesure-nadia';
    document.head.appendChild(noeud);
  }
  noeud.textContent = '.entete-contenu,.zone-jeu{max-width:' + (largeurRangee + RETRAIT) + 'px}';
  return true;
})(LARGEUR);
"""

# Toute lecture d'etat rend, en plus de la mesure, le texte de la regle injectee tel qu'il se trouve
# dans le document a cet instant - null si le noeud a disparu. C'est ce champ qui rend l'omission
# bruyante : une navigation complete recree le <head> et jette la regle, et le script le voit.
LIRE_ETAT = """
(function(){
  var mesure = document.getElementById('mesure-nadia');
  var enonce = document.querySelector('.enonce');
  var rangee = document.querySelector('.propositions');
  var fin = document.querySelector('.fin-de-partie');
  if (!rangee) {
    return {fin: !!fin, enonce: enonce ? enonce.textContent : null, rangees: null,
            regleInjectee: mesure ? mesure.textContent : null};
  }
  var boutons = Array.prototype.slice.call(rangee.querySelectorAll('.proposition'));
  var hauts = {};
  boutons.forEach(function(b){ hauts[Math.round(b.getBoundingClientRect().top * 100) / 100] = 1; });
  return {
    fin: !!fin,
    enonce: enonce ? enonce.textContent : null,
    largeurRangee: Math.round(rangee.getBoundingClientRect().width * 100) / 100,
    rangees: Object.keys(hauts).length,
    propositions: boutons.map(function(b){ return b.textContent; }),
    regleInjectee: mesure ? mesure.textContent : null
  };
})();
"""

LIRE_VIEWPORT = "[window.innerWidth, window.innerHeight];"


class MesureNonFiable(RuntimeError):
    """La mesure ne porte pas sur ce qu'on croit. On refuse plutot que de rendre un chiffre."""


def juger_port_libre_avant_lancement(port, sonde):
    """Juge pur. `sonde` est ce que le port a repondu AVANT tout lancement, ou None s'il s'est tu.

    Un port de deverminage qui repond avant qu'on ait lance quoi que ce soit est tenu par un
    navigateur etranger. S'y attacher rend les pixels de la fenetre de quelqu'un d'autre - c'est
    arrive le 2026-08-27, trois fois, et ce jour-la la campagne a perdu 8 jeux sur 17 sans le dire.

    Un port nul n'est pas un port force : le systeme en choisira un libre, il n'y a rien a sonder.
    """
    if not port:
        return None
    if sonde is None:
        return None
    return (
        "port de deverminage %d deja pris : il repond avant que ce script ait lance un navigateur. "
        "Mesure refusee - elle porterait sur la fenetre d'un autre processus. Arrete ce navigateur "
        "par son identifiant de processus (jamais par nom d'image), ou relance avec PORT_DEV sur un "
        "port libre. Reponse recue : %s" % (port, sonde.strip()[:120])
    )


def juger_port_du_profil(port_demande, contenu_devtools_active_port):
    """Juge pur. Rend le couple (port reellement ouvert, motif de refus).

    `contenu_devtools_active_port` est le fichier que NOTRE profil a ecrit, ou None. Chrome y ecrit
    le port qu'il a reellement ouvert quand on lui demande le port 0. C'est la seule preuve, du cote
    du script, que le navigateur joignable sur ce port est bien le sien : le profil est un
    repertoire temporaire cree par cette execution et par elle seule.

    Quand un port est **force**, ce fichier n'existe pas - Chrome ne l'ecrit que pour un port
    attribue (verifie le 2026-08-27 sur Chrome 151.0.7922.174). Le port force est alors rendu tel
    quel, et la seule garde est la sonde d'avant lancement. Ce n'est pas une preuve de provenance,
    et c'est la raison pour laquelle le mode par defaut est le port attribue.
    """
    if port_demande:
        return port_demande, None
    if contenu_devtools_active_port is None:
        return None, (
            "le navigateur lance par ce script n'a ecrit aucun DevToolsActivePort dans son profil : "
            "on ne sait pas quel port il a ouvert, et s'attacher a un port devine reviendrait a "
            "mesurer la fenetre d'un autre agent. Mesure refusee."
        )
    lignes = contenu_devtools_active_port.strip().splitlines()
    premiere = lignes[0].strip() if lignes else ""
    if not premiere.isdigit():
        return None, (
            "DevToolsActivePort du profil de ce script ne commence pas par un numero de port "
            "(lu : %r). Mesure refusee : la provenance du navigateur n'est pas etablie." % premiere
        )
    if int(premiere) == 0:
        return None, (
            "DevToolsActivePort du profil de ce script declare le port 0 : le navigateur n'a ouvert "
            "aucun port de deverminage. Mesure refusee."
        )
    return int(premiere), None


def juger_regle_injectee(regle_attendue, regle_lue):
    """Juge pur. Compare la regle posee par ce script a celle que le document porte a la lecture.

    `regle_attendue` vaut None ou "" quand le script n'injecte rien : il n'y a alors rien a juger,
    et un outil sans regle injectee (mesurer-budget-vertical.py) n'est pas rendu rouge pour autant.
    """
    if not regle_attendue:
        return None
    if regle_lue is None:
        return (
            "la regle injectee a disparu du document entre sa pose et cette lecture : une "
            "navigation complete recree le <head> et la jette. Mesure refusee - sans ce refus, le "
            "rapport melangerait des items mesures avec la regle et des items mesures sans, sans le "
            "dire. Regle posee : %s" % regle_attendue
        )
    if regle_lue != regle_attendue:
        return (
            "la regle presente dans le document n'est pas celle que ce script a posee. "
            "Posee : %s | lue : %s. Mesure refusee." % (regle_attendue, regle_lue)
        )
    return None


def juger_viewport(demande, lu):
    """Juge pur. `lu` est ce que le navigateur rend, jamais ce que l'argument disait."""
    if lu is None:
        return (
            "le viewport n'a pas pu etre lu du navigateur. Mesure refusee : un rapport qui recopie "
            "son argument affirme un viewport qu'il n'a pas constate."
        )
    if [int(v) for v in lu] != [int(v) for v in demande]:
        return (
            "viewport demande %dx%d, viewport lu du navigateur %dx%d. Mesure refusee : les pixels "
            "mesures ne sont pas ceux du viewport annonce."
            % (int(demande[0]), int(demande[1]), int(lu[0]), int(lu[1]))
        )
    return None


def refuser(motif):
    """Leve le refus quand un juge en rend un. Un juge qui rend None laisse passer."""
    if motif:
        raise MesureNonFiable(motif)


def ouvrir_connexion(url_ws, secondes=30):
    """Ouvre la connexion de deverminage, et c'est le SEUL endroit du depot qui touche websocket.

    L'import est fait ici plutot qu'en tete de fichier pour que la decoupe d'ADR-0009 soit reelle et
    pas seulement declaree : les juges purs de ce module - juger_port_libre_avant_lancement,
    juger_port_du_profil, juger_regle_injectee, juger_viewport - doivent etre atteignables sans
    aucune bibliotheque tierce, puisque c'est a ce titre que la CI les execute. Ecrire "les juges
    sont purs" en commentaire et importer la bibliotheque du navigateur trois lignes plus haut,
    c'est une decoupe d'intention ; ceci en est une de fait.

    Le prerequis n'est pas supprime, il est deplace au point ou il est reellement necessaire : sans
    websocket-client, ce sont les mesures qui echouent - bruyamment, avec la marche a suivre - et
    non les juges.
    """
    try:
        import websocket
    except ImportError as absent:
        raise MesureNonFiable(
            "le module python \"websocket-client\" est absent : aucune mesure au navigateur n'est "
            "possible. Les juges de ce module, eux, n'en ont pas besoin et restent executables. "
            "Installer websocket-client pour mesurer. Detail : %s" % absent
        ) from absent
    return websocket.create_connection(url_ws, timeout=secondes)


def sonder_port(port):
    """Ce que le port de deverminage repond, ou None s'il ne repond pas. Impur, non teste en CI."""
    try:
        return (
            urllib.request.urlopen("http://127.0.0.1:%d/json/version" % port, timeout=2)
            .read()
            .decode("utf-8", "replace")
        )
    except Exception:
        return None


def lire_devtools_active_port(profil, secondes=60):
    """Le DevToolsActivePort ecrit par NOTRE profil, ou None au bout du delai. Impur."""
    chemin = os.path.join(profil, "DevToolsActivePort")
    for _ in range(int(secondes * 2)):
        try:
            contenu = open(chemin, encoding="utf-8").read()
            if contenu.strip():
                return contenu
        except OSError:
            pass
        time.sleep(0.5)
    return None


def lancer_et_attacher(options, profil, port):
    """Lance le navigateur et rend (processus, url_ws) d'une page qui est bien la notre.

    Point unique de raccordement des deux classes Navigateur du depot : le refus de port ne peut
    donc pas etre vrai dans l'une et absent dans l'autre, comme l'etait la boucle d'attachement
    recopiee dans lire-le-pli-a-l-ecran.py jusqu'a s21.

    `port` vaut 0 dans le cas courant : le systeme attribue, le profil declare, on lit. Un port non
    nul est un port force par l'appelant, garde par la seule sonde d'avant lancement.
    """
    refuser(juger_port_libre_avant_lancement(port, sonder_port(port) if port else None))
    processus = subprocess.Popen(
        options, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL
    )
    try:
        port_reel, motif = juger_port_du_profil(
            port, None if port else lire_devtools_active_port(profil)
        )
        refuser(motif)
        cible = None
        for _ in range(120):
            try:
                brut = urllib.request.urlopen(
                    "http://127.0.0.1:%d/json/list" % port_reel, timeout=2
                ).read()
                for entree in json.loads(brut):
                    if entree.get("type") == "page":
                        cible = entree["webSocketDebuggerUrl"]
                        break
                if cible:
                    break
            except Exception:
                pass
            time.sleep(0.5)
        if not cible:
            raise MesureNonFiable(
                "le navigateur lance par ce script n'a ouvert aucune page de deverminage sur le "
                "port %d. Mesure refusee." % port_reel
            )
    except BaseException:
        processus.kill()
        raise
    return processus, cible


class Navigateur:
    def __init__(self, binaire, profil, port):
        self.processus, cible = lancer_et_attacher(
            [
                binaire,
                "--headless=new",
                "--disable-gpu",
                "--hide-scrollbars",
                "--no-first-run",
                "--no-default-browser-check",
                "--user-data-dir=" + profil,
                "--remote-debugging-port=" + str(port),
                "--remote-allow-origins=*",
                "--window-size=%d,%d" % (VIEWPORT_LARGEUR + 200, VIEWPORT_HAUTEUR + 400),
                "about:blank",
            ],
            profil,
            port,
        )
        self.ws = ouvrir_connexion(cible)
        self.compteur = 0
        self.regle_posee = None
        self.appeler("Page.enable")
        self.appeler("Runtime.enable")
        self.regler_viewport(VIEWPORT_LARGEUR, VIEWPORT_HAUTEUR)

    def regler_viewport(self, largeur, hauteur):
        """Pose le viewport, puis le LIT du navigateur. Le rapport cite la lecture, pas l'argument.

        Jusqu'a s21, le champ "viewport" des rapports de cette famille recopiait l'argument : un
        releve pouvait donc porter 744 x 551 alors que les pixels venaient d'une autre fenetre.
        """
        self.appeler(
            "Emulation.setDeviceMetricsOverride",
            {
                "width": largeur,
                "height": hauteur,
                "deviceScaleFactor": 1,
                "mobile": False,
            },
        )
        self.viewport_demande = [largeur, hauteur]
        self.viewport_lu = self.evaluer(LIRE_VIEWPORT)
        refuser(juger_viewport(self.viewport_demande, self.viewport_lu))
        return self.viewport_lu

    def poser_regle(self, expression, texte_pose):
        """Injecte une regle et retient son texte, pour que chaque lecture puisse le comparer."""
        self.evaluer(expression)
        self.regle_posee = texte_pose

    def verifier_regle(self, etat):
        """Refuse si la regle posee n'est plus dans le document au moment de cette lecture."""
        refuser(juger_regle_injectee(self.regle_posee, etat.get("regleInjectee")))
        return etat

    def appeler(self, methode, parametres=None):
        self.compteur += 1
        identifiant = self.compteur
        self.ws.send(
            json.dumps({"id": identifiant, "method": methode, "params": parametres or {}})
        )
        while True:
            message = json.loads(self.ws.recv())
            if message.get("id") == identifiant:
                if "error" in message:
                    raise RuntimeError(methode + " : " + json.dumps(message["error"]))
                return message.get("result", {})

    def evaluer(self, expression):
        resultat = self.appeler(
            "Runtime.evaluate",
            {"expression": expression, "returnByValue": True, "awaitPromise": True},
        )
        if "exceptionDetails" in resultat:
            raise RuntimeError(json.dumps(resultat["exceptionDetails"]))
        return resultat["result"].get("value")

    def fermer(self):
        try:
            self.ws.close()
        except Exception:
            pass
        self.processus.terminate()
        try:
            self.processus.wait(timeout=10)
        except Exception:
            self.processus.kill()


def texte_de_la_regle(largeur):
    """Le texte exact que POSER_STYLE ecrit dans le noeud de style, pour pouvoir le relire."""
    return ".entete-contenu,.zone-jeu{max-width:%dpx}" % (largeur + RETRAIT_RANGEE_PX)


def compter_rangees(nav, largeur):
    nav.poser_regle(
        POSER_STYLE.replace("RETRAIT", str(RETRAIT_RANGEE_PX)).replace(
            "LARGEUR", str(largeur)
        ),
        texte_de_la_regle(largeur),
    )
    etat = nav.verifier_regle(nav.evaluer(LIRE_ETAT))
    return etat["rangees"], etat["largeurRangee"]


def seuil_de_l_item(nav):
    """Plus petite largeur de rangee, au pixel, pour laquelle l'item tient sur une seule rangee."""
    basse, haute = RANGEE_MIN_PX, RANGEE_MAX_PX
    rangees_haute, offerte_haute = compter_rangees(nav, haute)
    if rangees_haute != 1:
        return None, offerte_haute
    rangees_basse, _ = compter_rangees(nav, basse)
    if rangees_basse == 1:
        return basse, offerte_haute
    while haute - basse > 1:
        milieu = (basse + haute) // 2
        rangees, _ = compter_rangees(nav, milieu)
        if rangees == 1:
            haute = milieu
        else:
            basse = milieu
    return haute, offerte_haute


def lire_bonnes_reponses(chemin):
    """Lit les couples enonce / bonne reponse du paquet servi, sans dependance YAML."""
    reponses, enonce = {}, None
    if not chemin:
        return reponses
    for ligne in open(chemin, encoding="utf-8"):
        depouillee = ligne.strip()
        if depouillee.startswith("- enonce:"):
            enonce = depouillee.split(":", 1)[1].strip().strip('"')
        elif depouillee.startswith("bonneReponse:") and enonce:
            reponses[enonce] = depouillee.split(":", 1)[1].strip().strip('"')
    return reponses


def main():
    if len(sys.argv) < 3:
        sys.stderr.write(__doc__)
        return 2
    base, jeu = sys.argv[1].rstrip("/"), sys.argv[2]
    sortie = sys.argv[3] if len(sys.argv) > 3 else None

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write(
            "aucun navigateur trouve : mets un Chrome sur le PATH ou designe-le par CHROME.\n"
        )
        return 1

    bonnes_reponses = lire_bonnes_reponses(os.environ.get("PAQUET"))
    attendus = int(os.environ.get("ITEMS_ATTENDUS", "16"))

    profil = tempfile.mkdtemp(prefix="mesure-nadia-")
    nav = Navigateur(binaire, profil, PORT_DEVERMINAGE)
    seuils = {}
    parties = 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        while len(seuils) < attendus and parties < 15:
            parties += 1
            nav.evaluer(
                "document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu
            )
            time.sleep(1.2)
            for _ in range(12):
                etat = nav.evaluer(LIRE_ETAT)
                if etat.get("rangees") is None:
                    break
                enonce = etat["enonce"]
                if enonce not in seuils:
                    seuil, offerte = seuil_de_l_item(nav)
                    seuils[enonce] = {
                        "seuil": seuil,
                        "propositions": etat["propositions"],
                        "rangeeOfferteAuPlafondHaut": offerte,
                    }
                    sys.stderr.write("  %-60s seuil %s px\n" % (enonce[:60], seuil))
                bonne = bonnes_reponses.get(enonce)
                if bonne:
                    clic = (
                        "Array.prototype.slice.call("
                        "document.querySelectorAll('.proposition'))"
                        ".filter(function(b){return b.textContent.trim()===%s;})[0]"
                        ".click(); true;" % json.dumps(bonne)
                    )
                else:
                    clic = "document.querySelector('.proposition').click(); true;"
                nav.evaluer(clic)
                time.sleep(0.7)
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;"
            )
            time.sleep(1.0)
    finally:
        viewport_lu = getattr(nav, "viewport_lu", None)
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {
        "viewport": viewport_lu,
        "viewportDemande": [VIEWPORT_LARGEUR, VIEWPORT_HAUTEUR],
        "retraitRangee": RETRAIT_RANGEE_PX,
        "bornesDuBalayage": [RANGEE_MIN_PX, RANGEE_MAX_PX],
        "parties": parties,
        "itemsVus": len(seuils),
        "seuilMaximum": max(
            (v["seuil"] for v in seuils.values() if v["seuil"]), default=None
        ),
        "items": seuils,
    }
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    if sortie:
        open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
