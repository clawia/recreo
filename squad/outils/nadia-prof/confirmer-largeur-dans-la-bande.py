#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Confirme, a un viewport de la bande 576-719 px, ce que le balayage a trouve a la reference.

Le balayage (balayage-largeur-rangee.py) rend la largeur de rangee necessaire item par item, mesuree
au viewport de reference 744 x 551. Ce script verifie la meme chose la ou le defaut vit : a un
viewport de la bande, avec une declaration injectee portant la meme borne de media que celle qui est
servie, en jouant chaque item jusqu'a l'echec pour lire la position reelle du bloc de verdict contre
le pli.

Il rend, par item : le nombre de rangees de propositions, le bas du texte du verdict apres un echec,
et si ce bas est au-dessus du pli (SYN-11).

Prerequis et limites : identiques a balayage-largeur-rangee.py.

Usage :
  python squad/outils/nadia-prof/confirmer-largeur-dans-la-bande.py <url_de_base> <id_du_jeu> \
      <viewport_px> <plafond_zone_px> [json_de_sortie]
"""

import json
import os
import shutil
import sys
import tempfile
import time

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

# Le module frere porte un tiret dans son nom : on le charge par son chemin plutot que par un import
# qui n'est pas un identifiant python valide.
import importlib.util

_chemin_frere = os.path.join(
    os.path.dirname(os.path.abspath(__file__)), "balayage-largeur-rangee.py"
)
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_frere)
_frere = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(_frere)

HAUTEUR_PLI_PX = 551

POSER_MEDIA = """
(function(plafond){
  var noeud = document.getElementById('mesure-nadia');
  if (!noeud) {
    noeud = document.createElement('style');
    noeud.id = 'mesure-nadia';
    document.head.appendChild(noeud);
  }
  noeud.textContent =
    '@media (min-width:576px) and (max-width:767.98px){.entete-contenu,.zone-jeu{max-width:'
    + plafond + 'px}}';
  return true;
})(PLAFOND);
"""

LIRE_MANCHE = """
(function(){
  var enonce = document.querySelector('.enonce');
  var rangee = document.querySelector('.propositions');
  var verdict = document.querySelector('.message-jeu .verdict');
  var signal = document.querySelector('.message-jeu .signal');
  if (!rangee) { return {rangees: null}; }
  var boutons = Array.prototype.slice.call(rangee.querySelectorAll('.proposition'));
  var hauts = {};
  boutons.forEach(function(b){ hauts[Math.round(b.getBoundingClientRect().top * 100) / 100] = 1; });
  var arrondi = function(v){ return Math.round(v * 100) / 100; };
  return {
    enonce: enonce ? enonce.textContent : null,
    largeurRangee: arrondi(rangee.getBoundingClientRect().width),
    rangees: Object.keys(hauts).length,
    basDuVerdict: verdict ? arrondi(verdict.getBoundingClientRect().bottom) : null,
    basDuSignal: signal ? arrondi(signal.getBoundingClientRect().bottom) : null,
    defilement: window.scrollY,
    propositions: boutons.map(function(b){ return b.textContent; })
  };
})();
"""


def main():
    if len(sys.argv) < 5:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    viewport = int(sys.argv[3])
    plafond = int(sys.argv[4])
    sortie = sys.argv[5] if len(sys.argv) > 5 else None

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    _frere.VIEWPORT_LARGEUR = viewport
    _frere.VIEWPORT_HAUTEUR = HAUTEUR_PLI_PX
    bonnes = _frere.lire_bonnes_reponses(os.environ.get("PAQUET"))
    attendus = int(os.environ.get("ITEMS_ATTENDUS", "16"))

    profil = tempfile.mkdtemp(prefix="mesure-nadia-")
    nav = _frere.Navigateur(binaire, profil, _frere.PORT_DEVERMINAGE)
    vus, parties = {}, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        while len(vus) < attendus and parties < 15:
            parties += 1
            nav.evaluer(
                "document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu
            )
            time.sleep(1.2)
            for _ in range(12):
                nav.evaluer(POSER_MEDIA.replace("PLAFOND", str(plafond)))
                etat = nav.evaluer(LIRE_MANCHE)
                if etat.get("rangees") is None:
                    break
                enonce = etat["enonce"]
                bonne = bonnes.get(enonce)
                if enonce not in vus:
                    # Un seul echec suffit a faire naitre le bloc de verdict ; on presse une
                    # proposition qui n'est pas la bonne, jamais la bonne.
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
                    nav.evaluer(POSER_MEDIA.replace("PLAFOND", str(plafond)))
                    apres = nav.evaluer(LIRE_MANCHE)
                    vus[enonce] = {
                        "rangees": apres["rangees"],
                        "largeurRangee": apres["largeurRangee"],
                        "basDuVerdict": apres["basDuVerdict"],
                        "basDuSignal": apres["basDuSignal"],
                        "defilement": apres["defilement"],
                        "auDessusDuPli": (apres["basDuVerdict"] or 0) <= HAUTEUR_PLI_PX,
                    }
                    sys.stderr.write(
                        "  %-46s %d rangee(s)  verdict a %s px  %s\n"
                        % (
                            enonce[:46],
                            apres["rangees"],
                            apres["basDuVerdict"],
                            "au-dessus" if vus[enonce]["auDessusDuPli"] else "SOUS LE PLI",
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
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;"
            )
            time.sleep(1.0)
    finally:
        viewport_lu = getattr(nav, "viewport_lu", None)
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {
        # s21 : le viewport du rapport est LU du navigateur, jamais recopie de l'argument.
        "viewport": viewport_lu,
        "viewportDemande": [viewport, HAUTEUR_PLI_PX],
        "plafondZoneInjecte": plafond,
        "rangeeAttendue": plafond - _frere.RETRAIT_RANGEE_PX,
        "itemsVus": len(vus),
        "itemsADeuxRangees": sorted(
            k for k, v in vus.items() if v["rangees"] and v["rangees"] > 1
        ),
        "itemsSousLePli": sorted(k for k, v in vus.items() if not v["auDessusDuPli"]),
        "pireBasDuVerdict": max(
            (v["basDuVerdict"] for v in vus.values() if v["basDuVerdict"]), default=None
        ),
        "lecturesADefilementNonNul": sum(1 for v in vus.values() if v["defilement"]),
        "items": vus,
    }
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    if sortie:
        open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
