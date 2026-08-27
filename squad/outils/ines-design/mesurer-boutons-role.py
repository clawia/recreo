#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure la boite rendue de chaque bouton portant [data-role], sur un ecran donne de
l'application servie - hauteur, largeur, surface, et les composantes de padding/font-size/
min-height qu'ADR-0017 compare de facon declarative.

Pourquoi ce script existe. FICHE-bandit-manchot-reperes-cm1.md (nadia-prof, arbitrage du
2026-08-27, "ADR-0017 inverse sur un axe apres S21-2") mesure a l'ecran que le rembourrage
vertical de .lien-accueil (sortie) reste au-dessus de celui de .proposition (action) apres
S21-2, et route a ines-design la valeur exacte qui referme l'ecart (SOR-1 a SOR-4). Aucun outil
du depot ne mesurait deja les boites reelles action/sortie ensemble : LActionNeDeclarePasMoins
QueLaSortieTest lit des declarations, jamais un rendu.

Methode, memes protocole et prerequis que balayage-largeur-rangee.py (squad/outils/nadia-prof/),
dont ce script reprend le Navigateur par import de module plutot que par recopie : navigateur
reel pilote par son protocole de deverminage, viewport DEC-0040 par defaut, deviceScaleFactor a
1, mode non mobile, application servie depuis le fat jar de l'arbre mesure. Rien n'est injecte
dans le document : la mesure porte sur la feuille servie telle quelle.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Usage :
  python squad/outils/ines-design/mesurer-boutons-role.py <url_de_base> <id_du_jeu_ou_vide> \
      <ecran> <json_de_sortie>

<ecran> vaut "accueil" (aucune manche ouverte), "manche" (une manche du jeu nomme, au repos) ou
"confirmation" (l'ecran de confirmation avant abandon, ouvert depuis une manche du jeu nomme).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME
ou sur le PATH, l'application deja servie a l'URL passee en argument, le module python
"websocket-client".
"""
import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_chemin_balayage = os.path.join(_dossier, "..", "nadia-prof", "balayage-largeur-rangee.py")
_spec_balayage = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_balayage)
blr = importlib.util.module_from_spec(_spec_balayage)
_spec_balayage.loader.exec_module(blr)

LIRE_BOUTONS = """
(function(){
  var r = function(n){ return Math.round(n * 100) / 100; };
  var noeuds = Array.prototype.slice.call(document.querySelectorAll('[data-role]'));
  var resultat = {};
  noeuds.forEach(function(n, i){
    var rect = n.getBoundingClientRect();
    var cs = getComputedStyle(n);
    var visible = rect.width > 0 && rect.height > 0 &&
      cs.display !== 'none' && cs.visibility !== 'hidden';
    var cle = n.getAttribute('data-role') + ':' + n.className + '#' + i;
    resultat[cle] = {
      role: n.getAttribute('data-role'),
      texte: n.textContent.trim(),
      visible: visible,
      hauteurRendue: r(rect.height),
      largeurRendue: r(rect.width),
      surfaceRendue: r(rect.width * rect.height),
      fontSize: cs.fontSize,
      minHeight: cs.minHeight,
      paddingHaut: cs.paddingTop,
      paddingBas: cs.paddingBottom,
      paddingGauche: cs.paddingLeft,
      paddingDroite: cs.paddingRight,
      borderTopWidth: cs.borderTopWidth
    };
  });
  return resultat;
})();
"""


def main():
    if len(sys.argv) != 5:
        sys.stderr.write(__doc__)
        return 2
    base, jeu, ecran, sortie = sys.argv[1].rstrip("/"), sys.argv[2], sys.argv[3], sys.argv[4]

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    profil = tempfile.mkdtemp(prefix="mesure-ines-boutons-")
    nav = blr.Navigateur(binaire, profil, blr.PORT_DEVERMINAGE)
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        if ecran in ("manche", "confirmation"):
            nav.evaluer(
                "document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu
            )
            time.sleep(1.2)
        if ecran == "confirmation":
            nav.evaluer(
                "var s=document.querySelector('[data-role=sortie]'); "
                "if(s){s.click();} true;"
            )
            time.sleep(1.0)
        boutons = nav.evaluer(LIRE_BOUTONS)
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    rapport = {
        "viewport": [blr.VIEWPORT_LARGEUR, blr.VIEWPORT_HAUTEUR],
        "ecran": ecran,
        "jeu": jeu,
        "boutons": boutons,
    }
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
