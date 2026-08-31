#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Ou se situe, par rapport au pli, le seul jeu du catalogue qui sert deja un texte-support.

Pourquoi cet ancrage existe. La mesure de pli demandee par nadia-prof pour LIS porte sur un texte de
45 a 70 mots qui n'existe nulle part : elle se fait donc sur un prototype. Un prototype dit ce que
des regles donnent en pixels, jamais ou en est le produit servi. Sans un point d'ancrage pris sur
l'application reelle, le chiffre du prototype flotte - et c'est exactement le reproche que ce depot
a deja fait a une mesure.

bandit-manchot-comprehension-cm1 est ce point : c'est le seul jeu servi qui porte un texteSupport,
ses vingt textes comptent de 20 a 38 mots (mesure de nadia-prof, arbitrage prealable de s25, section
9.1), et la ligne est verte. Ce script mesure, item par item, ou tombe le bas du bloc de retour
apres un echec. Le chiffre du prototype se lit alors CONTRE celui-ci, pas dans le vide.

PERIMETRE, ecrit a cote du chiffre et non ailleurs : une seule largeur, 744 px, la reference du
produit (DEC-0040), et une seule hauteur, 551 px. Ce script ne balaie pas les dix largeurs et ne
pretend pas le faire. Il ne juge aucun jeu : un item sous le pli est un fait, et ce que ce fait
vaut appartient a nadia-prof.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur et d'une application servie (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
present sur le PATH, l'application servie, et le module python "websocket-client".

Usage :
  CHROME=... [PORT_DEV=9225] python squad/outils/ines-design/mesurer-l-ancrage-du-texte-support.py \
      <url_de_base> <id_du_jeu> <nb_items_attendus> <json_de_sortie>
"""
import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_chemin_blr = os.path.join(_dossier, "..", "nadia-prof", "balayage-largeur-rangee.py")
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_blr)
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

PLI_PX = 551
LARGEUR_DE_REFERENCE = 744

LIRE = r"""
(function () {
    var r = function (v) { return v === null ? null : Math.round(v * 100) / 100; };
    var q = function (s) { return document.querySelector(s); };
    var bas = function (n) { return n ? r(n.getBoundingClientRect().bottom) : null; };
    var support = q('.texte-support');
    var props = Array.prototype.map.call(document.querySelectorAll('.proposition'), function (b) {
        var rect = b.getBoundingClientRect();
        return {texte: b.textContent.trim(), caracteres: b.textContent.trim().length,
                haut: r(rect.top), hauteur: r(rect.height)};
    });
    var ecran = q('.ecran-de-jeu');
    var basReel = null;
    if (ecran) {
        basReel = ecran.getBoundingClientRect().bottom;
        Array.prototype.forEach.call(ecran.querySelectorAll('*'), function (n) {
            var b = n.getBoundingClientRect().bottom;
            if (b > basReel) { basReel = b; }
        });
    }
    return {
        enonce: q('.enonce') ? q('.enonce').textContent : null,
        texteSupport: support ? support.textContent : null,
        motsDuTexteSupport: support ? support.textContent.trim().split(/\s+/).length : null,
        hauteurDuTexteSupport: support ? r(support.getBoundingClientRect().height) : null,
        propositions: props,
        rangeesDePropositions: Object.keys(props.reduce(function (a, p) {
            a[p.haut] = 1; return a; }, {})).length,
        basDuVerdict: bas(q('.message-jeu .verdict')),
        basDeLIndice: bas(q('.message-jeu .indice')),
        basDeLEcran: r(basReel),
        defilement: window.scrollY,
        aDesPropositions: props.length > 0
    };
})()
"""


def naviguer(nav, url):
    nav.appeler("Page.navigate", {"url": url})
    for _ in range(120):
        if nav.evaluer("document.readyState") == "complete":
            time.sleep(0.4)
            return
        time.sleep(0.25)
    raise blr.MesureNonFiable("le document %s n'a pas fini de charger. Mesure refusee." % url)


def main():
    if len(sys.argv) != 5:
        print(__doc__)
        return 2
    base, jeu = sys.argv[1].rstrip("/"), sys.argv[2]
    attendus, sortie = int(sys.argv[3]), sys.argv[4]

    binaire = os.environ.get("CHROME") or shutil.which("chrome") \
        or shutil.which("chromium") or shutil.which("google-chrome")
    if not binaire:
        raise SystemExit("aucun navigateur : variable d'environnement CHROME, ou sur le PATH.")
    port = int(os.environ.get("PORT_DEV", "9225"))

    profil = tempfile.mkdtemp(prefix="ines-ancrage-")
    nav = blr.Navigateur(binaire, profil, port)
    vus = {}
    parties = 0
    try:
        vu = nav.regler_viewport(LARGEUR_DE_REFERENCE, PLI_PX)
        while len(vus) < attendus and parties < 20:
            parties += 1
            # Une partie laissee en cours fait rendre l'ecran de reprise a la place du catalogue.
            nav.appeler("Network.clearBrowserCookies")
            naviguer(nav, base + "/")
            nav.regler_viewport(LARGEUR_DE_REFERENCE, PLI_PX)
            ouvert = nav.evaluer(
                "(function(){var b=document.querySelector('[hx-post*=\"/parties/%s\"]');"
                "if(!b){return false;} b.click(); return true;})()" % jeu)
            if not ouvert:
                raise blr.MesureNonFiable(
                    "aucune entree de catalogue ne pointe \"%s\". Mesure refusee." % jeu)
            time.sleep(1.3)
            for _ in range(20):
                repos = nav.evaluer(LIRE)
                if not repos["aDesPropositions"]:
                    break
                nav.evaluer("document.querySelector('.proposition').click(); true;")
                time.sleep(0.9)
                apres = nav.evaluer(LIRE)
                enonce = repos["enonce"]
                if enonce and enonce not in vus:
                    apres["auRepos"] = {
                        "basDeLEcran": repos["basDeLEcran"],
                        "rangeesDePropositions": repos["rangeesDePropositions"],
                    }
                    apres["sousLePli"] = apres["basDeLEcran"] > PLI_PX
                    vus[enonce] = apres
                    sys.stderr.write(
                        "  %-46s %2s mots, support %5s px, %s rangees, bas %6s %s\n"
                        % (enonce[:46], apres["motsDuTexteSupport"],
                           apres["hauteurDuTexteSupport"], apres["rangeesDePropositions"],
                           apres["basDeLEcran"], "SOUS LE PLI" if apres["sousLePli"] else ""))
                    sys.stderr.flush()
                # Un second appui fait avancer, que la premiere reponse ait ete juste ou fausse.
                nav.evaluer(
                    "var b=document.querySelector('.proposition');"
                    "var c=document.querySelector('.continuer-manche');"
                    "if(c){c.click();} else if(b){b.click();} true;")
                time.sleep(0.9)
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    sous = sorted(k for k, v in vus.items() if v["sousLePli"])
    rapport = {
        "jeu": jeu, "pli": PLI_PX, "largeur": LARGEUR_DE_REFERENCE, "viewportLu": vu,
        "parties": parties, "itemsVus": len(vus), "itemsAttendus": attendus,
        "itemsSousLePliApresUnEchec": sous,
        "nombreSousLePli": len(sous),
        "motsMin": min((v["motsDuTexteSupport"] for v in vus.values()
                        if v["motsDuTexteSupport"]), default=None),
        "motsMax": max((v["motsDuTexteSupport"] for v in vus.values()
                        if v["motsDuTexteSupport"]), default=None),
        "hauteurDeSupportMax": max((v["hauteurDuTexteSupport"] for v in vus.values()
                                    if v["hauteurDuTexteSupport"]), default=None),
        "basDeLEcranMax": max((v["basDeLEcran"] for v in vus.values()
                               if v["basDeLEcran"]), default=None),
        "items": vus,
    }
    with open(sortie, "w", encoding="utf-8") as fichier:
        json.dump(rapport, fichier, ensure_ascii=False, indent=1, sort_keys=True)
    print("%d items vus sur %d, %d sous le pli. Ecrit dans %s"
          % (len(vus), attendus, len(sous), sortie))
    return 0


if __name__ == "__main__":
    sys.exit(main())
