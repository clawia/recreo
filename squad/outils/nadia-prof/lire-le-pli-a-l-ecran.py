#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Lit ce que l'enfant voit sous le pli apres un echec, et en garde l'image.

Pourquoi cet outil existe, alors qu'auditer-paquet.py mesure deja le pli. auditer-paquet.py rend
le bas de la **boite** d'indice. Or la boite est plus basse que le texte qu'elle porte : elle a un
rembourrage et une bordure. Un item dont la boite depasse le pli de 16 px n'a pas forcement perdu
un seul mot - c'est le cas de figure que j'ai trouve le 2026-08-27 sur douze items du catalogue, et
il ne se distingue d'une perte reelle de texte par aucun chiffre d'auditer-paquet.py. Cet outil
rend donc, a cote du bas de la boite, **le bas de la derniere ligne de texte** et le nombre de
lignes, et il ecrit la capture d'ecran pour que la lecture ne soit pas seulement arithmetique.

Il porte aussi les deux etats de mesure qu'aucun outil de ce depot ne savait produire :

  - `--barre` lance le navigateur **sans** `--hide-scrollbars`, donc avec une barre de defilement
    classique peinte. C'est l'etat que mes campagnes precedentes n'avaient jamais essaye et que je
    declarais non couvert ;
  - `LARGEUR` fixe la largeur de mise en page, pour separer ce qu'une barre peinte coute en
    **geometrie** de ce qu'elle apporte en **signal** ;
  - `REGLE` injecte une regle CSS candidate dans le document rendu, jamais dans `recreo.css` -
    meme procede que mesurer-candidats-rep-8.py d'ines-design, dont cet outil reprend le protocole.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client", et la variable PAQUET qui
designe le fichier de contenu servi (pour repondre juste et avancer). PORT_DEV deplace le port de
deverminage quand celui d'auditer-paquet.py est retenu par un navigateur qui ne s'est pas ferme -
panne rencontree le 2026-08-27, bruyante et non silencieuse, mais qui arrete toute mesure.

Usage :
  CHROME=... PAQUET=<paquet.yaml> [REGLE="<css>"] [LARGEUR=744] [PORT_DEV=9222] [DEFILER=1] \
      python squad/outils/nadia-prof/lire-le-pli-a-l-ecran.py <url_de_base> <id_du_jeu> <png> \
      [--barre]
"""

import base64
import importlib.util
import json
import os
import shutil
import subprocess
import sys
import tempfile
import time
import urllib.request

# Pas d'import de "websocket" ici : la connexion passe par blr.ouvrir_connexion, qui
# l'importe au point ou elle est reellement necessaire (meme motif que le module frere).

_dossier = os.path.dirname(os.path.abspath(__file__))


def _module(nom, fichier):
    chemin = os.path.join(_dossier, fichier)
    spec = importlib.util.spec_from_file_location(nom, chemin)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


blr = _module("balayage_largeur_rangee", "balayage-largeur-rangee.py")
auditer = _module("auditer_paquet", "auditer-paquet.py")

LIRE_FIN = """
(function(){
  var r = function(v){ return v === null ? null : Math.round(v * 100) / 100; };
  var indice = document.querySelector('.message-jeu .indice');
  var proposition = document.querySelector('.proposition');
  var enonce = document.querySelector('.enonce');
  var rangee = document.querySelector('.propositions');
  var lignes = null, basDuTexte = null;
  if (indice) {
    var plage = document.createRange();
    plage.selectNodeContents(indice);
    var boites = Array.prototype.slice.call(plage.getClientRects());
    lignes = boites.length;
    if (boites.length) {
      basDuTexte = r(Math.max.apply(null, boites.map(function(b){ return b.bottom; })));
    }
  }
  var hauts = {};
  Array.prototype.slice.call(document.querySelectorAll('.proposition')).forEach(function(b){
    hauts[Math.round(b.getBoundingClientRect().top)] = 1;
  });
  return {
    regleInjectee: (function(){var m = document.getElementById('mesure-nadia');
                              return m ? m.textContent : null;})(),
    enonce: enonce ? enonce.textContent : null,
    texteIndice: indice ? indice.textContent.trim() : null,
    basDeLaBoiteDIndice: indice ? r(indice.getBoundingClientRect().bottom) : null,
    basDeLaDerniereLigneDeTexte: basDuTexte,
    lignesDIndice: lignes,
    hauteurDUnBouton: proposition ? r(proposition.getBoundingClientRect().height) : null,
    policeDeLaProposition: proposition ? getComputedStyle(proposition).fontSize : null,
    policeDeLEnonce: enonce ? getComputedStyle(enonce).fontSize : null,
    largeurDeLaRangee: rangee ? r(rangee.getBoundingClientRect().width) : null,
    rangees: Object.keys(hauts).length,
    largeurDeMiseEnPage: document.documentElement.clientWidth,
    largeurDeLaFenetre: window.innerWidth,
    hauteurDuDocument: document.documentElement.scrollHeight,
    hauteurVisible: document.documentElement.clientHeight
  };
})();
"""


class Navigateur(blr.Navigateur):
    """Meme navigateur que balayage-largeur-rangee.py, deux options en plus.

    La barre de defilement et la largeur de mise en page sont exactement ce que la classe mere
    fige ; les redeclarer ici plutot que de modifier la classe mere laisse intactes toutes les
    mesures deja commitees qui s'appuient sur elle.
    """

    def __init__(self, binaire, profil, port, largeur, barre_peinte):
        options = [binaire, "--headless=new", "--disable-gpu"]
        if not barre_peinte:
            options.append("--hide-scrollbars")
        options += [
            "--no-first-run",
            "--no-default-browser-check",
            "--user-data-dir=" + profil,
            "--remote-debugging-port=" + str(port),
            "--remote-allow-origins=*",
            "--window-size=%d,%d" % (largeur + 200, blr.VIEWPORT_HAUTEUR + 400),
            "about:blank",
        ]
        # s21 : l'attachement passe par la classe mere plutot que par une boucle recopiee ici.
        # Cette copie etait le trou : le refus de port ecrit dans la mere n'aurait pas existe dans
        # la fille, et c'est cette fille qui a rendu les captures de REP-8.
        self.processus, cible = blr.lancer_et_attacher(options, profil, port)
        self.ws = blr.ouvrir_connexion(cible)
        self.compteur = 0
        self.regle_posee = None
        self.appeler("Page.enable")
        self.appeler("Runtime.enable")
        self.regler_viewport(largeur, blr.VIEWPORT_HAUTEUR)

    def attendre(self, expression, secondes=20):
        for _ in range(int(secondes * 2)):
            if self.evaluer("!!(%s);" % expression):
                return True
            time.sleep(0.5)
        return False

    def photographier(self, chemin):
        image = self.appeler(
            "Page.captureScreenshot", {"format": "png", "captureBeyondViewport": False}
        )
        open(chemin, "wb").write(base64.b64decode(image["data"]))


def main():
    if len(sys.argv) < 4:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    png = sys.argv[3]
    barre_peinte = "--barre" in sys.argv

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    largeur = int(os.environ.get("LARGEUR", blr.VIEWPORT_LARGEUR))
    port = int(os.environ.get("PORT_DEV", blr.PORT_DEVERMINAGE))
    regle = os.environ.get("REGLE", "")
    bonnes = blr.lire_bonnes_reponses(os.environ.get("PAQUET"))

    profil = tempfile.mkdtemp(prefix="pli-nadia-")
    nav = Navigateur(binaire, profil, port, largeur, barre_peinte)
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        if not nav.attendre("document.querySelector('[hx-post*=\"%s\"]')" % jeu):
            raise RuntimeError("le jeu %s n'apparait pas sur l'accueil servi" % jeu)
        if regle:
            # Le noeud porte l'identifiant que LIRE_FIN relit : sans identifiant, une regle jetee
            # par une navigation etait indiscernable d'une regle en place (defaut de s21).
            nav.poser_regle(
                "(function(){var n=document.getElementById('mesure-nadia');"
                "if(!n){n=document.createElement('style');n.id='mesure-nadia';"
                "document.head.appendChild(n);}n.textContent=%s;return true;})();"
                % json.dumps(regle),
                regle,
            )
        nav.evaluer("document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu)
        if not nav.attendre("document.querySelector('.proposition')"):
            raise RuntimeError("aucune proposition rendue apres le demarrage de la partie")
        time.sleep(0.5)

        etat = nav.evaluer(auditer.LIRE)
        bonne = bonnes.get(etat["enonce"])
        fausses = [p for p in etat["propositions"] if p != (bonne or "")]
        if not fausses:
            raise RuntimeError("aucune reponse fausse identifiable : verifier la variable PAQUET")
        nav.evaluer(auditer.CLIC % json.dumps(fausses[0]))
        nav.attendre("document.querySelector('.message-jeu')")
        time.sleep(0.5)

        mesure = nav.verifier_regle(nav.evaluer(LIRE_FIN))
        mesure["viewport"] = nav.viewport_lu
        mesure["viewportDemande"] = nav.viewport_demande
        mesure["pli"] = auditer.PLI_PX
        mesure["barreDeDefilementPeinte"] = barre_peinte
        mesure["regleCssCandidate"] = regle
        nav.photographier(png)

        if os.environ.get("DEFILER"):
            nav.evaluer("window.scrollTo(0, document.documentElement.scrollHeight); true;")
            time.sleep(0.6)
            mesure["apresDefilementJusquEnBas"] = nav.evaluer(
                "(function(){var i=document.querySelector('.message-jeu .indice');"
                "return {defilement: window.scrollY, basDeLaBoiteDIndice: i ? "
                "Math.round(i.getBoundingClientRect().bottom * 100) / 100 : null};})();"
            )
            nav.photographier(png.replace(".png", "-defile.png"))

        # Ecriture en octets UTF-8 explicites : la sortie de ce script est destinee a etre
        # redirigee dans un releve commite, et un "print" prend l'encodage de la console, qui
        # n'est pas UTF-8 partout. Un releve illisible est un releve perdu.
        sys.stdout.buffer.write(
            json.dumps(mesure, ensure_ascii=False, indent=1, sort_keys=True).encode("utf-8")
        )
        sys.stdout.buffer.write(b"\n")
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)
    return 0


if __name__ == "__main__":
    sys.exit(main())
