#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure le budget vertical manquant sous le pli, sur l'application servie, sans rien injecter.

Pourquoi ce script existe. Le depot a chiffre deux fois le deficit vertical - 43,19 px sur les
synonymes (theo-archi, avis de s19), puis 68,19 px sur les items a quatre propositions
(nadia-prof, DEC-0043) - toujours sur un item, un paquet et une largeur a la fois, et toujours
pendant que la piste retenue etait horizontale. DEC-0047 ayant retire le correctif de largeur, il
faut le deficit de la bande entiere et de tous les paquets servis, dans l'etat le plus charge.

Ce qu'il fait de different des scripts de nadia-prof, dont il reprend la plomberie :
  - il n'injecte aucun plafond : il mesure le produit tel qu'il est servi aujourd'hui ;
  - il pousse chaque item jusqu'a l'etat le plus charge (revelation, trois echecs), pas jusqu'au
    premier echec, parce que c'est l'etat que le critere de S19-6a nomme et que l'explication n'y
    apparait qu'a ce moment-la ;
  - il releve la pile verticale boite par boite, pour que le budget recuperable soit lu sur le
    rendu et non deduit de recreo.css.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite que les scripts de nadia-prof).

Prerequis nommes, jamais leur emplacement (ADR-0010) :
  - un Chrome ou Chromium designe par la variable d'environnement CHROME, ou sur le PATH ;
  - l'application servie, dont l'URL de base est passee en argument ;
  - le module python "websocket-client" ;
  - la variable PAQUET, facultative, qui designe le fichier de contenu servi : elle ne sert qu'a
    repondre juste pour avancer d'une manche a la suivante.

Usage :
  python squad/outils/theo-archi/mesurer-budget-vertical.py <url_de_base> <id_du_jeu> \
      <viewport_px> [json_de_sortie]
"""

import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_racine = os.path.dirname(os.path.abspath(__file__))
_frere = os.path.join(_racine, "..", "nadia-prof", "balayage-largeur-rangee.py")
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _frere)
_plomberie = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(_plomberie)

HAUTEUR_PLI_PX = 551

# Les elements de la pile verticale de l'ecran de manche, dans l'ordre du flux. Chaque nom est un
# selecteur porte par fragments/manche.html, jamais une liste devinee.
PILE = [
    ".entete",
    ".ecran-de-jeu",
    ".score",
    ".consigne",
    ".enonce",
    ".cabinet",
    ".cabinet-bandeau",
    ".propositions",
    ".cabinet-socle",
    ".message-jeu",
    ".signal",
    ".verdict",
    ".explication",
    ".indice",
]

LIRE = """
(function(pile){
  var arrondi = function(v){ return Math.round(v * 100) / 100; };
  var rangee = document.querySelector('.propositions');
  var boutons = rangee
      ? Array.prototype.slice.call(rangee.querySelectorAll('.proposition'))
      : [];
  var hauts = {};
  boutons.forEach(function(b){ hauts[arrondi(b.getBoundingClientRect().top)] = 1; });
  var boites = {};
  pile.forEach(function(sel){
    var n = document.querySelector(sel);
    if (!n) { return; }
    var r = n.getBoundingClientRect();
    var c = window.getComputedStyle(n);
    boites[sel] = {
      haut: arrondi(r.top), bas: arrondi(r.bottom), hauteur: arrondi(r.height),
      margeHaut: c.marginTop, margeBas: c.marginBottom,
      rembourrageHaut: c.paddingTop, rembourrageBas: c.paddingBottom,
      bordureHaut: c.borderTopWidth
    };
  });
  var enonce = document.querySelector('.enonce');
  var message = document.querySelector('.message-jeu');
  return {
    enonce: enonce ? enonce.textContent : null,
    rangees: rangee ? Object.keys(hauts).length : null,
    propositions: boutons.map(function(b){ return b.textContent; }),
    revelee: !!document.querySelector('.explication'),
    aUnIndice: !!document.querySelector('.indice'),
    basDuMessage: message ? arrondi(message.getBoundingClientRect().bottom) : null,
    defilement: window.scrollY,
    hauteurDocument: arrondi(document.documentElement.scrollHeight),
    boites: boites
  };
})(PILE);
"""


def presser(nav, texte):
    nav.evaluer(
        "Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;"
        % json.dumps(texte.strip())
    )
    time.sleep(0.55)


def _poser_la_regle(nav, regle):
    """Repose la regle candidate et verifie qu'elle est bien la, plutot que de la supposer.

    Reposee avant chaque lecture : une navigation complete recree le <head> et jette le noeud
    injecte, et un rapport qui melangerait des items mesures avec la regle et des items mesures
    sans serait exactement l'ecart muet que S21-1 ferme. La verification par juger_regle_injectee
    est la ceinture : si la pose echouait, le refus est bruyant.
    """
    nav.poser_regle(
        "(function(){var n=document.getElementById('mesure-nadia');"
        "if(!n){n=document.createElement('style');n.id='mesure-nadia';"
        "document.head.appendChild(n);}n.textContent=%s;return true;})();" % json.dumps(regle),
        regle,
    )
    _plomberie.refuser(
        _plomberie.juger_regle_injectee(
            regle,
            nav.evaluer(
                "(function(){var m=document.getElementById('mesure-nadia');"
                "return m ? m.textContent : null;})();"
            ),
        )
    )


def main():
    if len(sys.argv) < 4:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    viewport = int(sys.argv[3])
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

    _plomberie.VIEWPORT_LARGEUR = viewport
    _plomberie.VIEWPORT_HAUTEUR = HAUTEUR_PLI_PX
    bonnes = _plomberie.lire_bonnes_reponses(os.environ.get("PAQUET"))
    attendus = int(os.environ.get("ITEMS_ATTENDUS", "16"))
    parties_max = int(os.environ.get("PARTIES_MAX", "12"))
    # Nombre d echecs a provoquer par item. Trois est la revelation, l etat le plus charge
    # (S19-6a). Un seul suffit sur un paquet sans explication : la revelation n y ajoute
    # aucune boite, ce que le rapport dit item par item plutot que de le supposer.
    echecs_vises = int(os.environ.get("ECHECS", "3"))

    # s21 : REGLE injecte une regle candidate dans le document rendu, jamais dans recreo.css - meme
    # procede que balayage-largeur-rangee.py, et desormais meme verification : la regle est relue a
    # chaque lecture d'etat, si bien qu'une navigation qui la jetterait est un refus et non un
    # silence. Sans ce parametre, ce script ne savait mesurer que le temoin, et comparer un candidat
    # exigeait de modifier la feuille servie - donc de mesurer autre chose que ce qu'on croit.
    regle = os.environ.get("REGLE", "")

    lire = LIRE.replace("PILE", json.dumps(PILE))
    profil = tempfile.mkdtemp(prefix="mesure-theo-")
    nav = _plomberie.Navigateur(binaire, profil, _plomberie.PORT_DEVERMINAGE)
    vus, parties = {}, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        while len(vus) < attendus and parties < parties_max:
            parties += 1
            if regle:
                _poser_la_regle(nav, regle)
            nav.evaluer("document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu)
            time.sleep(1.2)
            for _ in range(12):
                if regle:
                    _poser_la_regle(nav, regle)
                etat = nav.evaluer(lire)
                if etat.get("rangees") is None:
                    break
                enonce = etat["enonce"]
                bonne = bonnes.get(enonce)
                if enonce not in vus:
                    fausses = [
                        p.strip()
                        for p in etat["propositions"]
                        if p.strip() != (bonne or "")
                    ]
                    pire, etapes = None, []
                    # Trois echecs : c'est la revelation, l'etat le plus charge (S19-6a).
                    for rang in range(echecs_vises):
                        if not fausses:
                            break
                        presser(nav, fausses[rang % len(fausses)])
                        apres = nav.evaluer(lire)
                        if apres.get("rangees") is None:
                            break
                        etapes.append(
                            {
                                "echecs": rang + 1,
                                "basDuMessage": apres["basDuMessage"],
                                "revelee": apres["revelee"],
                                "aUnIndice": apres["aUnIndice"],
                            }
                        )
                        if apres["basDuMessage"] is not None and (
                            pire is None or apres["basDuMessage"] > pire["basDuMessage"]
                        ):
                            pire = apres
                    if pire is not None:
                        vus[enonce] = {
                            "rangees": pire["rangees"],
                            "basDuMessage": pire["basDuMessage"],
                            "deficit": round(pire["basDuMessage"] - HAUTEUR_PLI_PX, 2),
                            "revelee": pire["revelee"],
                            "aUnIndice": pire["aUnIndice"],
                            "defilement": pire["defilement"],
                            "hauteurDocument": pire["hauteurDocument"],
                            "etapes": etapes,
                            "boites": pire["boites"],
                        }
                        sys.stderr.write(
                            "  %-44s %d rangee(s)  bas du message %s px  deficit %+.2f px\n"
                            % (
                                enonce[:44],
                                pire["rangees"] or 0,
                                pire["basDuMessage"],
                                vus[enonce]["deficit"],
                            )
                        )
                if bonne:
                    presser(nav, bonne)
                else:
                    nav.evaluer("document.querySelector('.proposition').click(); true;")
                    time.sleep(0.55)
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); if(s){s.click();} true;"
            )
            time.sleep(1.0)
    finally:
        viewport_lu = getattr(nav, "viewport_lu", None)
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    deficits = [v["deficit"] for v in vus.values() if v["deficit"] is not None]
    pire_enonce = max(vus, key=lambda k: vus[k]["deficit"]) if vus else None
    rapport = {
        "jeu": jeu,
        # s21 : le viewport du rapport est LU du navigateur, jamais recopie de l'argument.
        "viewport": viewport_lu,
        "viewportDemande": [viewport, HAUTEUR_PLI_PX],
        "regleInjectee": regle or None,
        "itemsVus": len(vus),
        "itemsSousLePli": sorted(k for k, v in vus.items() if v["deficit"] > 0),
        "pireDeficit": max(deficits) if deficits else None,
        "pireItem": pire_enonce,
        "pileDuPireItem": vus[pire_enonce]["boites"] if pire_enonce else None,
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
