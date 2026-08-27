#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure, sur l'application servie, le cout reel d'un renfort visuel du mot cible (NAT-5).

Pourquoi ce script existe. La proposition d'ines-design pour NAT-5 (squad/conformite/
FICHE-bandit-manchot-nature-mot-cp.md) enveloppe le mot designe entre guillemets francais dans un
<span class="mot-designe"> style (fond, rembourrage, police agrandie), sans toucher au controleur
ni au domaine : elle ne peut donc etre injectee que sur un jeu deja servi qui emploie la meme
convention de marquage (guillemets francais autour du mot cible). "cp-nature-mot" est hors du
catalogue (DEC-0045) ; "Les synonymes" et "Les antonymes" (CM1) partagent le meme gabarit
(fragments/manche.html) et la meme regle .enonce (recreo.css) et servent donc de vehicule de
mesure sans qu'aucune ligne de production ne bouge.

Methode, identique dans son protocole a celle des campagnes de nadia-prof (squad/outils/
nadia-prof/) : navigateur reel pilote par le protocole de deverminage, deviceScaleFactor 1, mode
non mobile, application servie depuis le fat jar de l'arbre mesure. Pour chaque item :

  1. l'item est affiche frais (aucun essai encore joue) - releve BASELINE de .enonce (largeur,
     hauteur, bas) ;
  2. un essai faux est joue (M1) pour faire apparaitre le bloc de verdict et l'indice - releve
     BASELINE du bas du verdict et du bas de l'indice, exactement comme
     confirmer-largeur-dans-la-bande.py ;
  3. le contenu de .enonce est remplace, par mutation DOM seule (jamais par edition de recreo.css
     ni des gabarits), par trois <span> : le texte avant le guillemet ouvrant, le mot cible seul
     dans un <span class="mot-designe">, le texte a partir du guillemet fermant. La regle
     ".mot-designe" est injectee en dernier dans le head, comme le fait deja balayage-largeur-
     rangee.py pour son propre selecteur ;
  4. nouveau releve de .enonce et du bloc de verdict/indice, sur le MEME DOM qui vient de se
     reagencer - c'est le navigateur qui recalcule la position reelle, jamais un calcul a la main.

Le delta entre les deux releves est le cout reel du renfort, au pixel, sur ce contenu et cette
largeur - jamais suppose.

Usage :
  python squad/outils/ines-design/mesurer-cout-mot-designe.py <url_de_base> <id_du_jeu> \
      <largeur_px> [json_de_sortie]

Prerequis et limites : identiques a balayage-largeur-rangee.py (Chrome/Chromium sur le PATH ou
CHROME, module python websocket-client, variable PAQUET facultative).
"""

import importlib.util
import json
import os
import re
import shutil
import sys
import tempfile
import time

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

_chemin_frere = os.path.join(
    os.path.dirname(os.path.abspath(__file__)), "..", "nadia-prof", "balayage-largeur-rangee.py"
)
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_frere)
_frere = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(_frere)

HAUTEUR_PLI_PX = 551

# Regle proposee pour NAT-5 (a discuter avec nadia-prof) : jamais la seule couleur - la boite de
# fond (forme), le rembourrage et la police agrandie sont trois canaux non colores, en plus des
# guillemets deja en place, conserves tels quels de part et d'autre du mot.
REGLE_MOT_DESIGNE_CSS = (
    ".mot-designe{background-color:#fff59d;border-radius:0.25rem;"
    "padding:0 0.25rem;font-size:1.15em}"
)

POSER_REGLE = """
(function(){
  var noeud = document.getElementById('mesure-ines');
  if (!noeud) {
    noeud = document.createElement('style');
    noeud.id = 'mesure-ines';
    document.head.appendChild(noeud);
  }
  noeud.textContent = %s;
  return true;
})();
""" % json.dumps(REGLE_MOT_DESIGNE_CSS)

# Coupe le texte de .enonce en trois : avant le guillemet ouvrant (inclus), le mot seul (sans les
# espaces, insecables ou non, immediatement a l'interieur des guillemets - SYN-9), et le reste a
# partir du guillemet fermant (inclus). Jamais de logique de contenu ici : la coupe se fait sur les
# deux caracteres de ponctuation deja presents dans la chaine servie, pas sur une regle de grammaire.
MARQUER_MOT_DESIGNE = r"""
(function(){
  var enonceEl = document.querySelector('.enonce');
  if (!enonceEl) { return {ok:false}; }
  var texte = enonceEl.textContent;
  var m = texte.match(/«(\s*)([^»]*?)(\s*)»/);
  if (!m) { return {ok:false, texte: texte}; }
  var avant = texte.slice(0, m.index) + '«' + m[1];
  var mot = m[2];
  var apres = m[3] + '»' + texte.slice(m.index + m[0].length);
  var echapper = function(s){
    var d = document.createElement('div');
    d.textContent = s;
    return d.innerHTML;
  };
  enonceEl.innerHTML = echapper(avant) + '<span class="mot-designe">' + echapper(mot) + '</span>' + echapper(apres);
  return {ok:true, mot: mot};
})();
"""

LIRE_MESURE = """
(function(){
  var enonce = document.querySelector('.enonce');
  var verdict = document.querySelector('.message-jeu .verdict');
  var indice = document.querySelector('.message-jeu .indice');
  var signal = document.querySelector('.message-jeu .signal');
  var arrondi = function(v){ return v === null || v === undefined ? null : Math.round(v * 100) / 100; };
  var rectEnonce = enonce ? enonce.getBoundingClientRect() : null;
  return {
    enonceTexte: enonce ? enonce.textContent : null,
    enonceLargeur: rectEnonce ? arrondi(rectEnonce.width) : null,
    enonceHauteur: rectEnonce ? arrondi(rectEnonce.height) : null,
    enonceBas: rectEnonce ? arrondi(rectEnonce.bottom) : null,
    basDuVerdict: verdict ? arrondi(verdict.getBoundingClientRect().bottom) : null,
    basDuSignal: signal ? arrondi(signal.getBoundingClientRect().bottom) : null,
    basDeLIndice: indice ? arrondi(indice.getBoundingClientRect().bottom) : null,
    defilement: window.scrollY
  };
})();
"""

LIRE_ETAT = _frere.LIRE_ETAT


def main():
    if len(sys.argv) < 4:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    largeur = int(sys.argv[3])
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

    _frere.VIEWPORT_LARGEUR = largeur
    _frere.VIEWPORT_HAUTEUR = HAUTEUR_PLI_PX
    bonnes = _frere.lire_bonnes_reponses(os.environ.get("PAQUET"))
    attendus = int(os.environ.get("ITEMS_ATTENDUS", "16"))

    profil = tempfile.mkdtemp(prefix="mesure-ines-")
    nav = _frere.Navigateur(binaire, profil, _frere.PORT_DEVERMINAGE)
    vus, parties = {}, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        nav.evaluer(POSER_REGLE)
        while len(vus) < attendus and parties < 15:
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
                bonne = bonnes.get(enonce)
                if enonce not in vus:
                    avant = nav.evaluer(LIRE_MESURE)
                    fausse = [
                        p for p in etat["propositions"] if p.strip() != (bonne or "")
                    ][0]
                    nav.evaluer(
                        "Array.prototype.slice.call("
                        "document.querySelectorAll('.proposition'))"
                        ".filter(function(b){return b.textContent.trim()===%s;})[0]"
                        ".click(); true;" % json.dumps(fausse.strip())
                    )
                    time.sleep(0.7)
                    baseline = nav.evaluer(LIRE_MESURE)
                    marquage = nav.evaluer(MARQUER_MOT_DESIGNE)
                    avec_renfort = nav.evaluer(LIRE_MESURE)
                    vus[enonce] = {
                        "motDesigne": marquage.get("mot"),
                        "avantEssai": avant,
                        "baseline": baseline,
                        "avecRenfort": avec_renfort,
                        "deltaHauteurEnonce": (
                            (avec_renfort["enonceHauteur"] or 0)
                            - (baseline["enonceHauteur"] or 0)
                        ),
                        "deltaBasDuVerdict": (
                            (avec_renfort["basDuVerdict"] or 0)
                            - (baseline["basDuVerdict"] or 0)
                        ) if baseline["basDuVerdict"] and avec_renfort["basDuVerdict"] else None,
                        "auDessusDuPliBaseline": (baseline["basDuVerdict"] or 0) <= HAUTEUR_PLI_PX,
                        "auDessusDuPliAvecRenfort": (avec_renfort["basDuVerdict"] or 0) <= HAUTEUR_PLI_PX,
                    }
                    sys.stderr.write(
                        "  %-46s haut.enonce %s->%s  verdict %s->%s  %s\n"
                        % (
                            enonce[:46],
                            baseline["enonceHauteur"], avec_renfort["enonceHauteur"],
                            baseline["basDuVerdict"], avec_renfort["basDuVerdict"],
                            "" if vus[enonce]["auDessusDuPliAvecRenfort"] else "SOUS LE PLI AVEC RENFORT",
                        )
                    )
                if bonne:
                    nav.evaluer(
                        "Array.prototype.slice.call("
                        "document.querySelectorAll('.proposition'))"
                        ".filter(function(b){return b.textContent.trim()===%s;})[0]"
                        ".click(); true;" % json.dumps(bonne)
                    )
                else:
                    nav.evaluer("document.querySelector('.proposition').click(); true;")
                time.sleep(0.7)
                nav.evaluer(POSER_REGLE)
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;"
            )
            time.sleep(1.0)
    finally:
        viewport_lu = getattr(nav, "viewport_lu", None)
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {
        "jeu": jeu,
        # s21 : le viewport du rapport est LU du navigateur, jamais recopie de l'argument.
        "viewport": viewport_lu,
        "viewportDemande": [largeur, HAUTEUR_PLI_PX],
        "regleInjectee": REGLE_MOT_DESIGNE_CSS,
        "itemsVus": len(vus),
        "pireDeltaHauteurEnonce": max(
            (v["deltaHauteurEnonce"] for v in vus.values()), default=None
        ),
        "pireBasDuVerdictAvecRenfort": max(
            (v["avecRenfort"]["basDuVerdict"] for v in vus.values() if v["avecRenfort"]["basDuVerdict"]),
            default=None,
        ),
        "itemsSousLePliBaseline": sorted(k for k, v in vus.items() if v["auDessusDuPliBaseline"] is False),
        "itemsSousLePliAvecRenfort": sorted(k for k, v in vus.items() if v["auDessusDuPliAvecRenfort"] is False),
        "items": vus,
    }
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    if sortie:
        open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
