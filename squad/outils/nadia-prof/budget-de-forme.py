#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure ce qu'un contrat de forme sur le contenu coute au catalogue, en items reels.

Pourquoi cet outil existe. Un contrat de forme pose sur le contenu ("l'enonce tient sur une
ligne", "un libelle de proposition tient en dix caracteres") est une contrainte pedagogique
deguisee en contrainte de mise en page : il decide, sans le dire, quelles competences peuvent
encore etre enseignees. Le seul moyen de le juger est de le confronter aux items deja ecrits,
mesures **avec la police reellement rendue** et non au comptage de caracteres - un chiffre romain
et un "m" ne tiennent pas la meme place.

Methode. Le script demarre une partie sur l'application servie pour lire les polices calculees de
`.enonce` et de `.proposition`, puis mesure chaque enonce et chaque libelle de chaque paquet avec
ces polices-la, par `measureText` sur un canevas. Il ne rend jamais un jugement : il rend le
nombre d'items que chaque regle du contrat ecarterait, paquet par paquet. Le jugement pedagogique
appartient au verdict qui cite ces chiffres, pas au script.

Les deux budgets sont des mesures, pas des choix : la largeur offerte a l'enonce est celle de
`.enonce` sur l'ecran servi ; la largeur offerte au texte d'un libelle est la colonne d'une rangee
a trois propositions, rembourrage lateral declare retire.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client".

Usage :
  CHROME=... [PORT_DEV=9222] python squad/outils/nadia-prof/budget-de-forme.py \
      <url_de_base> <id_du_jeu_temoin> <dossier_des_paquets> [json_de_sortie]
"""

import glob
import importlib.util
import json
import os
import re
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_spec = importlib.util.spec_from_file_location(
    "balayage_largeur_rangee", os.path.join(_dossier, "balayage-largeur-rangee.py")
)
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

MESURE = r"""
window.__mesurer = (function(){
  var enonce = document.querySelector('.enonce');
  var proposition = document.querySelector('.proposition');
  var se = getComputedStyle(enonce), sp = getComputedStyle(proposition);
  var c = document.createElement('canvas').getContext('2d');
  var police = function(s){ return s.fontWeight + ' ' + s.fontSize + ' ' + s.fontFamily; };
  return {
    largeurOfferteALEnonce: Math.round(enonce.getBoundingClientRect().width * 100) / 100,
    largeurOfferteAUnLibelleATroisColonnes: Math.round(
      ((document.querySelector('.propositions').getBoundingClientRect().width
        - 2 * parseFloat(getComputedStyle(document.querySelector('.propositions')).columnGap)) / 3
       - parseFloat(sp.paddingLeft) - parseFloat(sp.paddingRight)) * 100) / 100,
    policeDeLEnonce: police(se),
    policeDeLaProposition: police(sp),
    mesurer: function(texte, quoi){
      c.font = police(quoi === 'enonce' ? se : sp);
      return Math.round(c.measureText(texte).width * 100) / 100;
    }
  };
})(); true;
"""


def lire_items(chemin):
    """Lit enonces, bonne reponse et distracteurs d'un paquet, sans dependance YAML.

    Meme parti pris que lire_bonnes_reponses de balayage-largeur-rangee.py : les paquets de ce
    depot sont ecrits a plat, et une dependance de plus rendrait cet outil moins lancable que la
    mesure qu'il produit.
    """
    items, courant = [], None
    for ligne in open(chemin, encoding="utf-8"):
        depouillee = ligne.strip()
        if depouillee.startswith("- enonce:"):
            courant = {
                "enonce": depouillee.split(":", 1)[1].strip().strip('"'),
                "propositions": [],
            }
            items.append(courant)
        elif courant is not None and depouillee.startswith("bonneReponse:"):
            courant["propositions"].append(depouillee.split(":", 1)[1].strip().strip('"'))
        elif courant is not None and depouillee.startswith("distracteurs:"):
            courant["propositions"] += re.findall(
                r'"([^"]*)"', depouillee.split(":", 1)[1]
            )
    return items


def main():
    if len(sys.argv) < 4:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    temoin = sys.argv[2]
    paquets = sys.argv[3]
    sortie = sys.argv[4] if len(sys.argv) > 4 else None

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    profil = tempfile.mkdtemp(prefix="budget-nadia-")
    nav = blr.Navigateur(
        binaire, profil, int(os.environ.get("PORT_DEV", blr.PORT_DEVERMINAGE))
    )
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        for _ in range(40):
            if nav.evaluer("!!document.querySelector('[hx-post*=\"%s\"]');" % temoin):
                break
            time.sleep(0.5)
        nav.evaluer("document.querySelector('[hx-post*=\"%s\"]').click(); true;" % temoin)
        for _ in range(40):
            if nav.evaluer("!!document.querySelector('.proposition');"):
                break
            time.sleep(0.5)
        nav.evaluer(MESURE)
        cadre = nav.evaluer(
            "({largeurOfferteALEnonce: window.__mesurer.largeurOfferteALEnonce,"
            " largeurOfferteAUnLibelleATroisColonnes:"
            " window.__mesurer.largeurOfferteAUnLibelleATroisColonnes,"
            " policeDeLEnonce: window.__mesurer.policeDeLEnonce,"
            " policeDeLaProposition: window.__mesurer.policeDeLaProposition});"
        )
        budget_enonce = cadre["largeurOfferteALEnonce"]
        budget_libelle = cadre["largeurOfferteAUnLibelleATroisColonnes"]

        detail, total = {}, {
            "items": 0,
            "enonceQuiNeTientPasSurUneLigne": 0,
            "itemsAPlusDeTroisPropositions": 0,
            "itemsPortantUnLibelleTropLarge": 0,
            "libellesTropLarges": 0,
        }
        for chemin in sorted(glob.glob(os.path.join(paquets, "*.yaml"))):
            items = lire_items(chemin)
            if not items:
                continue
            nom = os.path.basename(chemin)[:-5]
            compte = {
                "items": len(items),
                "enonceQuiNeTientPasSurUneLigne": 0,
                "itemsAPlusDeTroisPropositions": 0,
                "itemsPortantUnLibelleTropLarge": 0,
                "libellesTropLarges": 0,
                "libelleLePlusLarge": [None, 0.0],
            }
            for item in items:
                total["items"] += 1
                largeur = nav.evaluer(
                    "window.__mesurer.mesurer(%s,'enonce');" % json.dumps(item["enonce"])
                )
                if largeur > budget_enonce:
                    compte["enonceQuiNeTientPasSurUneLigne"] += 1
                if len(item["propositions"]) > 3:
                    compte["itemsAPlusDeTroisPropositions"] += 1
                trop_large = False
                for libelle in item["propositions"]:
                    largeur = nav.evaluer(
                        "window.__mesurer.mesurer(%s,'proposition');" % json.dumps(libelle)
                    )
                    if largeur > budget_libelle:
                        compte["libellesTropLarges"] += 1
                        trop_large = True
                    if largeur > compte["libelleLePlusLarge"][1]:
                        compte["libelleLePlusLarge"] = [libelle, largeur]
                if trop_large:
                    compte["itemsPortantUnLibelleTropLarge"] += 1
            for cle in total:
                if cle != "items":
                    total[cle] += compte[cle]
            detail[nom] = compte

        rapport = {
            "viewport": [blr.VIEWPORT_LARGEUR, blr.VIEWPORT_HAUTEUR],
            "cadreMesure": cadre,
            "total": total,
            "paquets": detail,
        }
        texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
        if sortie:
            open(sortie, "w", encoding="utf-8").write(texte)
        sys.stdout.buffer.write(texte.encode("utf-8"))
        sys.stdout.buffer.write(b"\n")
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)
    return 0


if __name__ == "__main__":
    sys.exit(main())
