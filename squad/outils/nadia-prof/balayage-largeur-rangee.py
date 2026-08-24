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
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

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

import websocket

VIEWPORT_LARGEUR = 744
VIEWPORT_HAUTEUR = 551
# Rembourrage de .container (24 px) + bordure de .cabinet (16 px). Arithmetique d'ADR-0054,
# confirmee sur le rendu a s18 : la rangee vaut le plafond de .zone-jeu moins cette somme.
RETRAIT_RANGEE_PX = 40
# Bornes du balayage. La borne basse est nettement sous la rangee servie aujourd'hui dans la bande
# 576-719 px (500 px) ; la borne haute est sous la seule largeur connue suffisante (680 px).
RANGEE_MIN_PX = 400
RANGEE_MAX_PX = 700
PORT_DEVERMINAGE = 9319

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

LIRE_ETAT = """
(function(){
  var enonce = document.querySelector('.enonce');
  var rangee = document.querySelector('.propositions');
  var fin = document.querySelector('.fin-de-partie');
  if (!rangee) {
    return {fin: !!fin, enonce: enonce ? enonce.textContent : null, rangees: null};
  }
  var boutons = Array.prototype.slice.call(rangee.querySelectorAll('.proposition'));
  var hauts = {};
  boutons.forEach(function(b){ hauts[Math.round(b.getBoundingClientRect().top * 100) / 100] = 1; });
  return {
    fin: !!fin,
    enonce: enonce ? enonce.textContent : null,
    largeurRangee: Math.round(rangee.getBoundingClientRect().width * 100) / 100,
    rangees: Object.keys(hauts).length,
    propositions: boutons.map(function(b){ return b.textContent; })
  };
})();
"""


class Navigateur:
    def __init__(self, binaire, profil, port):
        self.processus = subprocess.Popen(
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
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
        )
        cible = None
        for _ in range(120):
            time.sleep(0.5)
            try:
                brut = urllib.request.urlopen(
                    "http://127.0.0.1:%d/json/list" % port, timeout=2
                ).read()
                for entree in json.loads(brut):
                    if entree.get("type") == "page":
                        cible = entree["webSocketDebuggerUrl"]
                        break
                if cible:
                    break
            except Exception:
                continue
        if not cible:
            raise RuntimeError("le navigateur n'a pas ouvert de page de deverminage")
        self.ws = websocket.create_connection(cible, timeout=30)
        self.compteur = 0
        self.appeler("Page.enable")
        self.appeler("Runtime.enable")
        self.appeler(
            "Emulation.setDeviceMetricsOverride",
            {
                "width": VIEWPORT_LARGEUR,
                "height": VIEWPORT_HAUTEUR,
                "deviceScaleFactor": 1,
                "mobile": False,
            },
        )

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


def compter_rangees(nav, largeur):
    nav.evaluer(
        POSER_STYLE.replace("RETRAIT", str(RETRAIT_RANGEE_PX)).replace(
            "LARGEUR", str(largeur)
        )
    )
    etat = nav.evaluer(LIRE_ETAT)
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
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {
        "viewport": [VIEWPORT_LARGEUR, VIEWPORT_HAUTEUR],
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
