#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure, PIXEL PAR PIXEL sur une capture reelle, ce qui est visible du verdict et de l'indice
apres un echec.

Reprend l'objectif de nadia-prof (squad/conformite/FICHE-bandit-manchot-reperes-cm1.md,
reaudition du 2026-08-26) : ne pas mesurer la boite (padding et bordure comprises), mesurer si un
GLYPHE est reellement visible. Une premiere version de ce script utilisait un decoupage par
Range() DOM caractere par caractere ; elle s'est revelee fausse a l'usage - pour du texte tenant
sur une seule ligne, Range().getBoundingClientRect() rend la meme boite (celle de la ligne
entiere) pour chaque caractere, quelle que soit sa position horizontale. Le test "le haut de la
ligne est-il au-dessus du pli" ne distingue donc jamais "un peu d'encre visible" de "beaucoup
d'encre visible" : il a produit un faux positif sur cette meme mesure (verifie par capture d'ecran
reelle et comparaison de pixels, la version Range() a declare un indice entierement visible alors
qu'aucun glyphe ne l'etait). Corrige ici : la seule source de verite est une capture d'ecran reelle
(Page.captureScreenshot), inspectee pixel par pixel dans la zone du bloc, au-dessus de la ligne du
pli.

Methode : la couleur du texte (--recreo-texte, #1c2733) est nettement plus sombre que les fonds et
bordures de ces deux blocs (amber #fff4e5/#b45309 pour l'indice, rose #ffebee pour le verdict) -
verifie en luminance (respectivement ~37, ~245, ~104 et ~241 ; un seuil a 60 separe le texte de
tout le reste, bordure comprise). Un glyphe est compte "vu" des lors qu'au moins un pixel de cette
couleur existe dans la zone horizontale du bloc, entre son sommet et la ligne du pli (551 px).

Ce script n'est pas un garde-fou de CI : il a besoin d'un navigateur (variable CHROME ou PATH), du
module python "websocket-client", de "Pillow", et de l'application deja servie.

Usage :
  python squad/outils/ines-design/mesurer-au-glyphe.py <url_de_base> <id_du_jeu> <nb_items> \
      [json_de_sortie]

Variables d'environnement :
  PAQUET        facultative, chemin du fichier de contenu servi (repondre juste, avancer sans user
                les trois essais de M5) ;
  INJECTER_CSS  facultative, regle CSS injectee en dernier dans le head apres chaque navigation -
                jamais une modification de recreo.css, une mesure "et si" sur le rendu servi.
"""

import base64
import importlib.util
import io
import json
import os
import shutil
import sys
import tempfile
import time

from PIL import Image

_frere_chemin = os.path.join(
    os.path.dirname(os.path.abspath(__file__)), "..", "nadia-prof", "balayage-largeur-rangee.py"
)
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _frere_chemin)
_frere = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(_frere)

PLI_PX = 551
SEUIL_LUMINANCE_TEXTE = 60  # separe --recreo-texte (~37) de tout fond/bordure croise (>=104)

LIRE_BOITE = """
(function(selecteur){
  var el = document.querySelector(selecteur);
  if (!el) { return null; }
  var r = el.getBoundingClientRect();
  return {
    gauche: Math.round(r.left * 100) / 100,
    droite: Math.round(r.right * 100) / 100,
    haut: Math.round(r.top * 100) / 100,
    bas: Math.round(r.bottom * 100) / 100
  };
})(SELECTEUR);
"""

LIRE_APRES_ECHEC = """
(function(winH){
  var enonce = document.querySelector('.enonce');
  return {enonce: enonce ? enonce.textContent : null, scrollY: window.scrollY, winH: winH};
})(%d);
""" % PLI_PX


def _luminance(rgb):
    r, g, b = rgb[0], rgb[1], rgb[2]
    return 0.299 * r + 0.587 * g + 0.114 * b


def glyphe_visible_dans(image, boite):
    """Vrai des qu'au moins un pixel de la couleur du texte existe dans la boite, au-dessus du pli."""
    if boite is None:
        return False, 0
    gauche = max(0, int(boite["gauche"]))
    droite = min(image.width, int(boite["droite"]) + 1)
    haut = max(0, int(boite["haut"]))
    bas = min(image.height, PLI_PX, int(boite["bas"]) + 1)
    if haut >= bas or gauche >= droite:
        return False, 0
    zone = image.crop((gauche, haut, droite, bas)).convert("RGB")
    pixels = zone.load()
    compte = 0
    for y in range(zone.height):
        for x in range(zone.width):
            if _luminance(pixels[x, y]) < SEUIL_LUMINANCE_TEXTE:
                compte += 1
    return compte > 0, compte


def mesurer_item(nav, selecteur_verdict, selecteur_indice):
    boite_v = nav.evaluer(LIRE_BOITE.replace("SELECTEUR", json.dumps(selecteur_verdict)))
    boite_i = nav.evaluer(LIRE_BOITE.replace("SELECTEUR", json.dumps(selecteur_indice)))
    etat = nav.evaluer(LIRE_APRES_ECHEC)
    capture = nav.appeler("Page.captureScreenshot", {"format": "png"})
    image = Image.open(io.BytesIO(base64.b64decode(capture["data"])))
    v_vu, v_px = glyphe_visible_dans(image, boite_v)
    i_vu, i_px = glyphe_visible_dans(image, boite_i)
    return {
        "enonce": etat["enonce"], "scrollY": etat["scrollY"], "winH": etat["winH"],
        "verdict": {"boite": boite_v, "glypheVisible": v_vu, "pixelsTexte": v_px,
                    "boiteEntierementVisible": boite_v["bas"] <= PLI_PX if boite_v else None},
        "indice": {"boite": boite_i, "glypheVisible": i_vu, "pixelsTexte": i_px,
                   "boiteEntierementVisible": boite_i["bas"] <= PLI_PX if boite_i else None},
    }


def main():
    if len(sys.argv) < 4:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    attendus = int(sys.argv[3])
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

    _frere.VIEWPORT_LARGEUR = 744
    _frere.VIEWPORT_HAUTEUR = PLI_PX
    bonnes = _frere.lire_bonnes_reponses(os.environ.get("PAQUET"))
    injecter_css = os.environ.get("INJECTER_CSS")

    profil = tempfile.mkdtemp(prefix="mesure-glyphe-")
    nav = _frere.Navigateur(binaire, profil, _frere.PORT_DEVERMINAGE)

    def poser_css():
        if not injecter_css:
            return
        nav.evaluer(
            "(function(css){var n=document.getElementById('mesure-glyphe-css');"
            "if(!n){n=document.createElement('style');n.id='mesure-glyphe-css';"
            "document.head.appendChild(n);} n.textContent=css; return true;})(%s);"
            % json.dumps(injecter_css)
        )

    resultats = {}
    parties = 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        poser_css()
        while len(resultats) < attendus and parties < 20:
            parties += 1
            nav.evaluer(
                "document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu
            )
            time.sleep(1.2)
            poser_css()
            for _ in range(20):
                etat = nav.evaluer(_frere.LIRE_ETAT)
                if etat.get("rangees") is None:
                    break
                enonce = etat["enonce"]
                bonne = bonnes.get(enonce)
                if enonce not in resultats:
                    fausse = [p for p in etat["propositions"] if p.strip() != (bonne or "")]
                    if not fausse:
                        break
                    nav.evaluer(
                        "Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
                        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;"
                        % json.dumps(fausse[0].strip())
                    )
                    time.sleep(0.6)
                    poser_css()
                    resultats[enonce] = mesurer_item(nav, ".message-jeu .verdict", ".message-jeu .indice")
                    sys.stderr.write("  %-55s verdict-vu=%s(%dpx) indice-vu=%s(%dpx)\n" % (
                        enonce[:55],
                        resultats[enonce]["verdict"]["glypheVisible"], resultats[enonce]["verdict"]["pixelsTexte"],
                        resultats[enonce]["indice"]["glypheVisible"], resultats[enonce]["indice"]["pixelsTexte"],
                    ))
                if bonne:
                    nav.evaluer(
                        "Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
                        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;"
                        % json.dumps(bonne)
                    )
                else:
                    nav.evaluer("document.querySelector('.proposition').click(); true;")
                time.sleep(0.6)
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;"
            )
            time.sleep(1.0)
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {"jeu": jeu, "pli": PLI_PX, "itemsVus": len(resultats), "mesurePixels": resultats}
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    if sortie:
        open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
