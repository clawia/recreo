#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Audit d'un paquet servi : rangees, pli, largeur necessaire, indice, propositions.

Ce script joue tous les items d'un jeu sur l'application servie, au viewport de reference de
DEC-0040 (744 x 551), et rend pour chaque item :

  - le nombre de rangees occupees par les propositions et la largeur de rangee offerte ;
  - la largeur de rangee **necessaire** a cet item, par dichotomie au pixel (meme procede que
    balayage-largeur-rangee.py) ;
  - apres un echec : le bas du bloc de verdict, le bas de la boite d'indice, et si l'un ou l'autre
    passe sous le pli - c'est le mecanisme de SYN-12, mesure et non suppose ;
  - le nombre de propositions rendues, leur texte, et le texte de l'indice ;
  - le defilement, verifie nul a chaque lecture.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client", et la variable PAQUET qui
designe le fichier de contenu servi (pour repondre juste et avancer sans user les essais de M5).

Usage :
  python squad/outils/nadia-prof/auditer-paquet.py <url_de_base> <id_du_jeu> <nb_items> [json]
"""

import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_frere_chemin = os.path.join(
    os.path.dirname(os.path.abspath(__file__)), "balayage-largeur-rangee.py"
)
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _frere_chemin)
_frere = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(_frere)

PLI_PX = 551

LIRE = """
(function(){
  var r = function(v){ return v === null ? null : Math.round(v * 100) / 100; };
  var q = function(s){ return document.querySelector(s); };
  var rangee = q('.propositions');
  var enonce = q('.enonce');
  var consigne = q('.consigne');
  var verdict = q('.message-jeu .verdict');
  var indice = q('.message-jeu .indice');
  var explication = q('.message-jeu .explication');
  if (!rangee) { return {rangees: null, fin: !!q('.fin-de-partie')}; }
  var boutons = Array.prototype.slice.call(rangee.querySelectorAll('.proposition'));
  var hauts = {};
  boutons.forEach(function(b){ hauts[r(b.getBoundingClientRect().top)] = 1; });
  var bas = function(n){ return n ? r(n.getBoundingClientRect().bottom) : null; };
  return {
    consigne: consigne ? consigne.textContent : null,
    enonce: enonce ? enonce.textContent : null,
    largeurRangee: r(rangee.getBoundingClientRect().width),
    rangees: Object.keys(hauts).length,
    nbPropositions: boutons.length,
    propositions: boutons.map(function(b){ return b.textContent.trim(); }),
    basDesPropositions: r(Math.max.apply(null, boutons.map(function(b){
      return b.getBoundingClientRect().bottom; }))),
    basDuVerdict: bas(verdict),
    basDeLIndice: bas(indice),
    basDeLExplication: bas(explication),
    texteIndice: indice ? indice.textContent : null,
    defilement: window.scrollY
  };
})();
"""

CLIC = ("Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;")


def auditer_un_item(nav, repos_enonce, bonne):
    """Mesure un item : seuil de largeur, etat au repos, etat apres un echec."""
    seuil, _ = _frere.seuil_de_l_item(nav)
    nav.evaluer(
        "var n=document.getElementById('mesure-nadia'); if(n){n.textContent='';} true;"
    )
    repos = nav.evaluer(LIRE)
    fausses = [p for p in repos["propositions"] if p != (bonne or "")]
    if fausses:
        nav.evaluer(CLIC % json.dumps(fausses[0]))
        time.sleep(0.7)
    apres = nav.evaluer(LIRE)
    return {
        "consigne": repos["consigne"],
        "seuilLargeurNecessaire": seuil,
        "rangeesAuRepos": repos["rangees"],
        "largeurRangeeOfferte": repos["largeurRangee"],
        "nbPropositions": repos["nbPropositions"],
        "propositions": repos["propositions"],
        "basDesPropositions": repos["basDesPropositions"],
        "defilementAuRepos": repos["defilement"],
        "apresUnEchec": {
            "rangees": apres["rangees"],
            "basDuVerdict": apres["basDuVerdict"],
            "basDeLIndice": apres["basDeLIndice"],
            "basDeLExplication": apres["basDeLExplication"],
            "texteIndice": apres["texteIndice"],
            "defilement": apres["defilement"],
            "verdictAuDessusDuPli": (apres["basDuVerdict"] or 0) <= PLI_PX,
            "indiceEntierAuDessusDuPli": (
                apres["basDeLIndice"] is None or apres["basDeLIndice"] <= PLI_PX
            ),
        },
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

    bonnes = _frere.lire_bonnes_reponses(os.environ.get("PAQUET"))
    profil = tempfile.mkdtemp(prefix="mesure-nadia-")
    nav = _frere.Navigateur(binaire, profil, _frere.PORT_DEVERMINAGE)
    vus, parties, lectures, defilements = {}, 0, 0, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        while len(vus) < attendus and parties < 25:
            parties += 1
            nav.evaluer("document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu)
            time.sleep(1.2)
            for _ in range(12):
                etat = nav.evaluer(LIRE)
                lectures += 1
                if etat.get("rangees") is None:
                    break
                if etat["defilement"]:
                    defilements += 1
                enonce = etat["enonce"]
                bonne = bonnes.get(enonce)
                if enonce not in vus:
                    vus[enonce] = auditer_un_item(nav, enonce, bonne)
                    lectures += 2
                    if vus[enonce]["apresUnEchec"]["defilement"]:
                        defilements += 1
                    e = vus[enonce]
                    sys.stderr.write(
                        "  %-44s %sprop %srang seuil %s verdict %s indice %s\n"
                        % (enonce[:44], e["nbPropositions"], e["rangeesAuRepos"],
                           e["seuilLargeurNecessaire"],
                           e["apresUnEchec"]["basDuVerdict"],
                           e["apresUnEchec"]["basDeLIndice"]))
                if bonne:
                    nav.evaluer(CLIC % json.dumps(bonne))
                else:
                    nav.evaluer("document.querySelector('.proposition').click(); true;")
                time.sleep(0.7)
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;"
            )
            time.sleep(1.0)
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {
        "jeu": jeu,
        "viewport": [_frere.VIEWPORT_LARGEUR, PLI_PX],
        "parties": parties,
        "lectures": lectures,
        "lecturesADefilementNonNul": defilements,
        "itemsVus": len(vus),
        "seuilLargeurMaximum": max(
            (v["seuilLargeurNecessaire"] for v in vus.values()
             if v["seuilLargeurNecessaire"]), default=None),
        "itemsADeuxRangees": sorted(
            k for k, v in vus.items() if v["rangeesAuRepos"] and v["rangeesAuRepos"] > 1),
        "itemsVerdictSousLePli": sorted(
            k for k, v in vus.items() if not v["apresUnEchec"]["verdictAuDessusDuPli"]),
        "itemsIndiceSousLePli": sorted(
            k for k, v in vus.items() if not v["apresUnEchec"]["indiceEntierAuDessusDuPli"]),
        "nombresDePropositions": sorted(set(v["nbPropositions"] for v in vus.values())),
        "items": vus,
    }
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    if sortie:
        open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
