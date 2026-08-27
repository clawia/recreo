#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Lit ce qu'un bloc de message epingle recouvre, et si l'enfant peut encore presser dessous.

Pourquoi cet outil existe. Les outils de pli du depot (auditer-paquet.py,
mesurer-budget-vertical.py, mesurer-candidats-rep-8.py) rendent tous la meme chose : le bas d'une
boite contre un pli. Aucun ne dit ce qui se trouve **sous** cette boite quand une declaration de
position la fait flotter par-dessus le reste de l'ecran. Or c'est exactement la question que pose
le levier de position mesure par theo-archi le 2026-08-27 (avis-faisabilite-s21, section 1.3) :
epingler .message-jeu remonte l'indice au-dessus du pli, et le pose sur le bas du meuble. Un
chiffre de pli ne peut pas dire si l'enfant y perd un bouton.

Ce que cet outil rend, et qu'aucun autre ne rendait :

  - le rectangle du bloc de message, et pour **chaque** proposition la hauteur et la part de
    surface que le message recouvre ;
  - un test de touche reel : document.elementFromPoint au centre du bouton et en neuf points de
    sa boite. C'est le navigateur qui repond qui recoit le clic, pas une soustraction de
    rectangles. Un bouton dont le centre ne repond plus n'est plus pressable, meme s'il est a
    l'ecran ;
  - ce que le message recouvre d'autre : l'enonce, la consigne, le socle et le levier du cabinet ;
  - la meme lecture apres defilement jusqu'en bas, parce qu'une position collante se decolle et
    qu'il faut savoir ou elle retombe ;
  - une capture d'ecran par etat, parce que la lecture ne doit pas etre seulement arithmetique.

Verification des conditions de mesure, a chaque lecture et non au lancement (defaut d'outil que
theo-archi nomme en section 2.4 de son avis, et que j'ai constate le meme jour sur mon propre
protocole) : la regle candidate est relue **dans le document** a chaque etat, par sa position
calculee sur .message-jeu, jamais supposee encore posee. Un etat dont la position calculee ne vaut
pas ce que la regle demande est ecrit tel quel dans le releve.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client", et la variable PAQUET qui
designe le fichier de contenu servi. RANGEES_MIN fait avancer la partie, en repondant juste,
jusqu'a un item dont les propositions occupent au moins ce nombre de rangees - c'est ainsi
qu'on va chercher le pire cas d'un paquet au lieu d'attendre que le tirage le donne.
ENONCE_CIBLE fait de meme jusqu'a un item dont l'enonce contient ce texte, pour aller
chercher un item nomme - par exemple le seul du paquet qui porte une explication.

Usage :
  CHROME=... PAQUET=<paquet.yaml> [REGLE="<css>"] [PORT_DEV=9319] [RANGEES_MIN=3] \
      python squad/outils/nadia-prof/lire-le-recouvrement-a-l-ecran.py \
      <url_de_base> <id_du_jeu> <prefixe_des_png>
"""

import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))


def _module(nom, fichier):
    chemin = os.path.join(_dossier, fichier)
    spec = importlib.util.spec_from_file_location(nom, chemin)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


blr = _module("balayage_largeur_rangee", "balayage-largeur-rangee.py")
pli = _module("lire_le_pli_a_l_ecran", "lire-le-pli-a-l-ecran.py")
auditer = _module("auditer_paquet", "auditer-paquet.py")

LIRE_RECOUVREMENT = """
(function(){
  var r = function(v){ return v === null || v === undefined ? null : Math.round(v * 100) / 100; };
  var q = function(s){ return document.querySelector(s); };
  var message = q('.message-jeu');
  var boite = function(n){
    if (!n) { return null; }
    var b = n.getBoundingClientRect();
    return {haut: r(b.top), bas: r(b.bottom), gauche: r(b.left), droite: r(b.right),
            hauteur: r(b.height), largeur: r(b.width)};
  };
  var chevauche = function(a, b){
    if (!a || !b) { return 0; }
    return r(Math.max(0, Math.min(a.bas, b.bas) - Math.max(a.haut, b.haut)));
  };
  var m = boite(message);
  var boutons = Array.prototype.slice.call(document.querySelectorAll('.proposition'));
  var lus = boutons.map(function(bouton){
    var bb = boite(bouton);
    var recouvrement = chevauche(bb, m);
    var points = [];
    var repondPartout = true, repondAuCentre = null;
    [0.15, 0.5, 0.85].forEach(function(fx){
      [0.15, 0.5, 0.85].forEach(function(fy){
        var x = bb.gauche + bb.largeur * fx;
        var y = bb.haut + bb.hauteur * fy;
        var cible = document.elementFromPoint(x, y);
        var atteint = !!(cible && (cible === bouton || bouton.contains(cible)));
        var nomme = cible ? (cible.className || cible.tagName) : null;
        points.push({x: r(x), y: r(y), atteint: atteint, elementDessus: nomme});
        if (!atteint) { repondPartout = false; }
        if (fx === 0.5 && fy === 0.5) { repondAuCentre = atteint; }
      });
    });
    return {
      texte: bouton.textContent.trim(),
      boite: bb,
      hauteurRecouverte: recouvrement,
      partDeSurfaceRecouverte: bb.hauteur ? r(100 * recouvrement / bb.hauteur) : null,
      pressableAuCentre: repondAuCentre,
      pressableEnNeufPoints: repondPartout,
      pointsQuiNeRepondentPlus: points.filter(function(p){ return !p.atteint; })
    };
  });
  var autres = {};
  [['enonce', '.enonce'], ['consigne', '.consigne'], ['score', '.score'],
   ['socleDuCabinet', '.cabinet-socle'], ['levierDuCabinet', '.cabinet-levier'],
   ['bandeauDuCabinet', '.cabinet-bandeau'], ['cabinet', '.cabinet'],
   ['rangeeDePropositions', '.propositions']].forEach(function(paire){
    var n = q(paire[1]);
    var bb = boite(n);
    autres[paire[0]] = bb ? {boite: bb, hauteurRecouverte: chevauche(bb, m)} : null;
  });
  var signal = q('.message-jeu .signal');
  var indice = q('.message-jeu .indice');
  var explication = q('.message-jeu .explication');
  return {
    positionCalculeeDuMessage: message ? getComputedStyle(message).position : null,
    fondCalculeDuMessage: message ? getComputedStyle(message).backgroundColor : null,
    regleCandidateEncorePresente: !!document.getElementById('regle-nadia'),
    boiteDuMessage: m,
    texteDuVerdict: q('.message-jeu .verdict') ? q('.message-jeu .verdict').textContent.trim() : null,
    texteDeLIndice: indice ? indice.textContent.trim() : null,
    texteDeLExplication: explication ? explication.textContent.trim() : null,
    basDuSignal: signal ? r(signal.getBoundingClientRect().bottom) : null,
    basDeLIndice: indice ? r(indice.getBoundingClientRect().bottom) : null,
    basDeLExplication: explication ? r(explication.getBoundingClientRect().bottom) : null,
    basDeLaDerniereLigneDeTexteDeLIndice: (function(){
      if (!indice) { return null; }
      var plage = document.createRange();
      plage.selectNodeContents(indice);
      var boites = Array.prototype.slice.call(plage.getClientRects());
      if (!boites.length) { return null; }
      return r(Math.max.apply(null, boites.map(function(b){ return b.bottom; })));
    })(),
    trousTransparentsDansLeBloc: (function(){
      if (!message) { return null; }
      var enfants = Array.prototype.slice.call(message.children);
      var somme = 0;
      enfants.forEach(function(e){ somme += e.getBoundingClientRect().height; });
      return r(message.getBoundingClientRect().height - somme);
    })(),
    fondsDesEnfantsDuMessage: message ? Array.prototype.slice.call(message.children).map(
      function(e){ return {classe: e.className, fond: getComputedStyle(e).backgroundColor}; }) : null,
    enonce: q('.enonce') ? q('.enonce').textContent : null,
    propositions: lus,
    autresElements: autres,
    defilement: r(window.scrollY),
    hauteurDuDocument: r(document.documentElement.scrollHeight),
    hauteurVisible: r(document.documentElement.clientHeight)
  };
})();
"""


def _lire(nav, png, etiquette):
    time.sleep(0.5)
    # s21 : la regle candidate est relue a chaque lecture, jamais supposee encore la.
    blr.refuser(
        blr.juger_regle_injectee(
            nav.regle_posee,
            nav.evaluer(
                "(function(){var m=document.getElementById('mesure-nadia');"
                "return m ? m.textContent : null;})();"
            ),
        )
    )
    mesure = nav.evaluer(LIRE_RECOUVREMENT)
    mesure["etat"] = etiquette
    mesure["pli"] = auditer.PLI_PX
    nav.photographier(png)
    mesure["capture"] = os.path.basename(png)
    return mesure


def presser_reellement(nav, x, y):
    """Presse a un point de l'ecran comme un doigt le ferait, sans passer par un selecteur.

    C'est la difference qui compte pour cet outil : ".click()" sur un noeud atteint le noeud meme
    s'il est recouvert, alors qu'un appui reel est distribue par le navigateur a ce qui est
    au-dessus a cet endroit. Un bouton recouvert ne se distingue d'un bouton pressable par aucune
    autre mesure.
    """
    for phase in ("mousePressed", "mouseReleased"):
        nav.appeler(
            "Input.dispatchMouseEvent",
            {"type": phase, "x": x, "y": y, "button": "left", "clickCount": 1},
        )
    time.sleep(0.9)


def main():
    if len(sys.argv) < 4:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    prefixe = sys.argv[3]

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    port = int(os.environ.get("PORT_DEV", blr.PORT_DEVERMINAGE))
    regle = os.environ.get("REGLE", "")
    bonnes = blr.lire_bonnes_reponses(os.environ.get("PAQUET"))

    profil = tempfile.mkdtemp(prefix="recouvrement-nadia-")
    nav = pli.Navigateur(binaire, profil, port, blr.VIEWPORT_LARGEUR, False)
    etats = []
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        if not nav.attendre("document.querySelector('[hx-post*=\"%s\"]')" % jeu):
            raise RuntimeError("le jeu %s n'apparait pas sur l'accueil servi" % jeu)
        if regle:
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

        rangees_min = int(os.environ.get("RANGEES_MIN", "0"))
        enonce_cible = os.environ.get("ENONCE_CIBLE", "")
        avances = 0
        while rangees_min or enonce_cible:
            etat = nav.evaluer(auditer.LIRE)
            assez_de_rangees = (etat.get("rangees") or 0) >= rangees_min
            bon_enonce = (not enonce_cible) or enonce_cible in (etat.get("enonce") or "")
            if (assez_de_rangees and bon_enonce) or avances >= 24:
                break
            bonne = bonnes.get(etat["enonce"])
            if not bonne:
                raise RuntimeError(
                    "bonne reponse introuvable pour avancer : verifier la variable PAQUET"
                )
            nav.evaluer(auditer.CLIC % json.dumps(bonne))
            time.sleep(0.8)
            avances += 1
        etats.append(_lire(nav, prefixe + "-0-repos.png", "repos, aucun message"))

        for essai in (1, 2, 3):
            etat = nav.evaluer(auditer.LIRE)
            bonne = bonnes.get(etat["enonce"])
            fausses = [p for p in etat["propositions"] if p != (bonne or "")]
            if not fausses:
                raise RuntimeError(
                    "aucune reponse fausse identifiable : verifier la variable PAQUET"
                )
            choix = fausses[min(essai - 1, len(fausses) - 1)]
            nav.evaluer(auditer.CLIC % json.dumps(choix))
            time.sleep(0.8)
            etats.append(
                _lire(nav, "%s-%d-echec.png" % (prefixe, essai), "echec %d" % essai)
            )

        recouverts = [
            p for p in etats[-1]["propositions"] if p["pressableAuCentre"] is False
        ]
        if recouverts:
            cible = recouverts[0]
            avant = nav.evaluer(auditer.LIRE)
            presser_reellement(
                nav,
                cible["boite"]["gauche"] + cible["boite"]["largeur"] / 2.0,
                cible["boite"]["haut"] + cible["boite"]["hauteur"] / 2.0,
            )
            apres = nav.evaluer(auditer.LIRE)
            etat_appui = _lire(
                nav,
                prefixe + "-3bis-appui-sous-le-message.png",
                "appui reel au centre d'un bouton recouvert",
            )
            etat_appui["appuiReel"] = {
                "boutonVise": cible["texte"],
                "centre": [
                    cible["boite"]["gauche"] + cible["boite"]["largeur"] / 2.0,
                    cible["boite"]["haut"] + cible["boite"]["hauteur"] / 2.0,
                ],
                "enonceAvant": avant["enonce"],
                "enonceApres": apres["enonce"],
                "verdictAvant": avant["basDuVerdict"],
                "lAppuiAEuUnEffet": avant["enonce"] != apres["enonce"],
            }
            etats.append(etat_appui)
        nav.evaluer(
            "window.scrollTo(0, document.documentElement.scrollHeight); true;"
        )
        time.sleep(0.7)
        etats.append(
            _lire(nav, prefixe + "-4-defile.png", "apres defilement jusqu'en bas, en revelation")
        )
        nav.evaluer("window.scrollTo(0, 0); true;")
        time.sleep(0.5)

        etat = nav.evaluer(auditer.LIRE)
        bonne = bonnes.get(etat["enonce"])
        if bonne:
            nav.evaluer(auditer.CLIC % json.dumps(bonne))
            time.sleep(0.9)
            etats.append(
                _lire(nav, prefixe + "-5-apres-revelation.png", "manche suivante, apres revelation")
            )
            etat = nav.evaluer(auditer.LIRE)
            bonne = bonnes.get(etat["enonce"])
            if bonne:
                nav.evaluer(auditer.CLIC % json.dumps(bonne))
                time.sleep(0.9)
                etats.append(
                    _lire(nav, prefixe + "-6-reussite.png", "manche suivante, apres une reussite")
                )

        sortie = {
            "jeu": jeu,
            "regleCssCandidate": regle,
            "viewportDemande": nav.viewport_demande,
            "viewportLuDuNavigateur": nav.viewport_lu,
            "largeurDeMiseEnPageLue": nav.evaluer(
                "[document.documentElement.clientWidth, document.documentElement.clientHeight]"
            ),
            "etats": etats,
        }
        sys.stdout.buffer.write(
            json.dumps(sortie, ensure_ascii=False, indent=1, sort_keys=True).encode("utf-8")
        )
        sys.stdout.buffer.write(b"\n")
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)
    return 0


if __name__ == "__main__":
    sys.exit(main())
