#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Un chiffre trace en geometrie est-il lisible, et a partir de quelle hauteur d'image ?

Pourquoi cet outil existe. `ADR-0073`, clause 4, interdit tout `<text>` et tout `<tspan>` dans une
illustration de contenu. `nadia-prof` a approuve la clause et juge qu'un chiffre grave sur une piece
n'est pas du texte mais une partie de l'objet dessine ; `theo-archi` lui a donne raison, l'a mesure
sur une piece, et a route a `ines-design` la seule question qui restait : **douze chiffres traces
sont-ils producibles ?** Une ligne de couverture en depend.

"Producible" a deux moities, et cet outil ne mesure que la seconde :

  - **ecrire les glyphes** est etabli par les fichiers de demonstration commites a cote, qui ne
    contiennent ni `<text>` ni `<tspan>` et n'appellent aucune police - le depot n'en vendorise
    aucune, donc un contour extrait d'une police ne serait reproductible sur aucun appareil ;
  - **les rendre lisibles** est une affaire de pixels, et c'est ce que ce script rend.

**Le critere de lisibilite n'est pas mon oeil, et il n'est pas invente pour l'occasion** : c'est
celui que le produit s'applique deja a lui-meme. Le plancher typographique de `recreo` est
1,25 rem (`TailleDesTextesEtBoutonsTest`), la taille de `.consigne`, de `.indice` et du verdict. Le
script mesure **la hauteur d'encre d'un chiffre a cette taille, dans la pile de polices reellement
servie**, puis la hauteur d'encre d'un chiffre trace en proportion de l'image, et rend la hauteur
d'image minimale qui egale les deux. Un chiffre dessine n'a alors pas a etre juge "assez gros" : il
est aussi gros que le plus petit chiffre que le produit sert deja.

Le second repere rendu est la taille de `.enonce` (2 rem), pour que le lecteur voie les deux bornes.

**Ce que ce script ne dit pas** : qu'un enfant de CP reconnaisse la forme tracee comme le chiffre
qu'on lui apprend a ecrire. Le 1 a-t-il une base, le 7 une barre, le 4 est-il ferme - c'est un
jugement pedagogique et il est a `nadia-prof`. Le script mesure des hauteurs, pas des formes.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
present sur le PATH, et le module python "websocket-client".

Usage :
  CHROME=... [PORT_DEV=9232] python squad/outils/ines-design/mesurer-un-chiffre-trace.py \
      <json_de_sortie> <dessin.svg> [<dessin.svg> ...]
"""
import importlib.util
import io
import json
import os
import re
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_chemin_blr = os.path.join(_dossier, "..", "nadia-prof", "balayage-largeur-rangee.py")
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_blr)
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

# La feuille reelle, chargee dans l'ordre d'ADR-0014 : c'est elle qui porte le plancher
# typographique dont ce script tire son critere. Chemins relatifs a la racine du depot.
FEUILLE_TIERCE = "adapters/adapter-web/src/main/resources/static/vendor/bootstrap.min.css"
FEUILLE_MAISON = "adapters/adapter-web/src/main/resources/static/css/recreo.css"

# Les deux reperes du produit : son plancher de lisibilite et la taille de l'enonce.
REPERES = {"plancherDeLisibilite": "1.25rem", "enonce": "2rem"}

# Le plancher de trait, tire du produit lui-meme et non d'une regle exterieure : 0,125 rem, la
# bordure de .champ-reponse et des deux boutons de sortie, soit 2 px a la racine par defaut. C'est
# la plus fine ligne que recreo dessine en lui faisant porter un sens.
PLANCHER_DE_TRAIT_PX = 2

MESURER_LE_TEXTE = r"""
(function () {
    var r = function (v) { return Math.round(v * 100) / 100; };
    var sonde = document.createElement('p');
    sonde.className = 'consigne';
    sonde.textContent = '0123456789';
    document.body.appendChild(sonde);
    var famille = getComputedStyle(sonde).fontFamily;
    var graisse = getComputedStyle(sonde).fontWeight;
    document.body.removeChild(sonde);

    var cv = document.createElement('canvas').getContext('2d');
    var lu = {};
    Object.keys(REPERES).forEach(function (nom) {
        cv.font = graisse + ' ' + REPERES[nom] + ' ' + famille;
        var m = cv.measureText('0123456789');
        lu[nom] = {
            taille: REPERES[nom],
            hauteurDEncreDUnChiffre: r(m.actualBoundingBoxAscent + m.actualBoundingBoxDescent),
            largeurMoyenneDUnChiffre: r(cv.measureText('8').width)
        };
    });
    return {famille: famille.split(',')[0], graisse: graisse, reperes: lu};
})()
"""

MESURER_LE_DESSIN = r"""
(function () {
    var r = function (v) { return Math.round(v * 100) / 100; };
    var svg = document.getElementById('dessin-mesure');
    var boite = svg.getBoundingClientRect();
    var instances = Array.prototype.slice.call(svg.querySelectorAll('use'));
    var hauteurs = instances.map(function (u) { return u.getBoundingClientRect().height; });
    var largeurs = instances.map(function (u) { return u.getBoundingClientRect().width; });
    // Deux boites de glyphe qui se recouvrent sont un defaut de mise en page, pas un defaut de
    // taille : deux chiffres colles ne se lisent pas, quelle que soit la hauteur de l'image. Les
    // compter ici evite de rendre une hauteur minimale obtenue par un dessin illisible.
    var boites = instances.map(function (u) { return u.getBoundingClientRect(); });
    var recouvrements = 0;
    for (var i = 0; i < boites.length; i++) {
        for (var j = i + 1; j < boites.length; j++) {
            if (boites[i].left < boites[j].right && boites[j].left < boites[i].right
                && boites[i].top < boites[j].bottom && boites[j].top < boites[i].bottom) {
                recouvrements++;
            }
        }
    }
    // L'epaisseur REELLEMENT rendue du trait d'un glyphe : la valeur declaree dans le dessin,
    // multipliee par l'echelle que le viewBox et la mise en page appliquent. Elle se mesure, elle
    // ne se deduit pas : un vector-effect ou une transformation non uniforme la changerait sans que
    // la declaration bouge.
    var trait = null;
    var premier = svg.querySelector('defs path');
    var porteur = instances.length ? instances[0] : null;
    if (premier && porteur) {
        var ctm = porteur.getScreenCTM();
        var echelle = ctm ? Math.sqrt(Math.abs(ctm.a * ctm.d - ctm.b * ctm.c)) : null;
        var declaree = parseFloat(getComputedStyle(premier).strokeWidth);
        trait = echelle ? r(declaree * echelle) : null;
    }

    return {
        hauteurRendue: r(boite.height),
        epaisseurDeTraitRendue: trait,
        largeurRendue: r(boite.width),
        instancesDeGlyphe: instances.length,
        glyphesDefinis: svg.querySelectorAll('defs path').length,
        hauteurDEncreMax: hauteurs.length ? r(Math.max.apply(null, hauteurs)) : null,
        hauteurDEncreMin: hauteurs.length ? r(Math.min.apply(null, hauteurs)) : null,
        largeurDEncreMax: largeurs.length ? r(Math.max.apply(null, largeurs)) : null,
        boitesDeGlypheQuiSeRecouvrent: recouvrements,
        // La clause 4 d'ADR-0073, verifiee sur le document rendu et non sur la source.
        aucunTextNiTspan: svg.querySelectorAll('text, tspan').length === 0
    };
})()
"""


def page(racine, svg_source):
    """Inline le dessin dans une page qui charge les deux feuilles reelles.

    Le dessin est inline et non charge par <img> pour une raison de mesure et non de gout : un SVG
    charge par <img> vit dans un document separe, dont le script hote ne peut lire aucune boite.
    Ce que le navigateur rend est identique ; ce qu'on peut en lire ne l'est pas.
    """
    svg = svg_source[svg_source.index("<svg"):]
    svg = re.sub(r"<svg ", '<svg id="dessin-mesure" ', svg, count=1)
    return (
        '<!DOCTYPE html><html lang="fr"><head><meta charset="UTF-8">'
        '<link rel="stylesheet" href="file:///%s">'
        '<link rel="stylesheet" href="file:///%s">'
        '</head><body style="margin:0">%s</body></html>'
        % (
            os.path.join(racine, FEUILLE_TIERCE).replace("\\", "/").lstrip("/"),
            os.path.join(racine, FEUILLE_MAISON).replace("\\", "/").lstrip("/"),
            svg,
        )
    )


def naviguer(nav, url):
    nav.appeler("Page.navigate", {"url": url})
    for _ in range(120):
        if nav.evaluer("document.readyState") == "complete":
            time.sleep(0.4)
            return
        time.sleep(0.25)
    raise blr.MesureNonFiable("le document %s n'a pas fini de charger. Mesure refusee." % url)


def main():
    if len(sys.argv) < 3:
        print(__doc__)
        return 2
    sortie, dessins = sys.argv[1], sys.argv[2:]
    racine = os.path.abspath(os.path.join(_dossier, "..", "..", ".."))
    for chemin in dessins:
        if not os.path.isfile(chemin):
            raise SystemExit("dessin introuvable : %s" % chemin)

    binaire = os.environ.get("CHROME") or shutil.which("chrome") \
        or shutil.which("chromium") or shutil.which("google-chrome")
    if not binaire:
        raise SystemExit("aucun navigateur : variable d'environnement CHROME, ou sur le PATH.")
    port = int(os.environ.get("PORT_DEV", "9232"))

    profil = tempfile.mkdtemp(prefix="ines-chiffre-")
    travail = tempfile.mkdtemp(prefix="ines-chiffre-page-")
    nav = blr.Navigateur(binaire, profil, port)
    rapport = {"reperesDuProduit": None, "dessins": []}
    try:
        nav.regler_viewport(900, 700)
        for rang, chemin in enumerate(dessins):
            fichier = os.path.join(travail, "dessin-%d.html" % rang)
            io.open(fichier, "w", encoding="utf-8").write(
                page(racine, io.open(chemin, encoding="utf-8").read()))
            naviguer(nav, "file:///" + os.path.abspath(fichier).replace("\\", "/").lstrip("/"))
            if rapport["reperesDuProduit"] is None:
                rapport["reperesDuProduit"] = nav.evaluer(
                    MESURER_LE_TEXTE.replace("REPERES", json.dumps(REPERES)))
            mesure = nav.evaluer(MESURER_LE_DESSIN)
            mesure["dessin"] = os.path.basename(chemin)
            part = (mesure["hauteurDEncreMax"] / mesure["hauteurRendue"]
                    if mesure["hauteurDEncreMax"] else None)
            mesure["partDeLaHauteurDeLImage"] = round(part, 4) if part else None
            mesure["hauteurDImageMinimale"] = {
                nom: round(repere["hauteurDEncreDUnChiffre"] / part, 1) if part else None
                for nom, repere in rapport["reperesDuProduit"]["reperes"].items()
            }
            # Le second plancher : le trait le plus fin que le produit dessine deja de facon
            # porteuse est la bordure de 0,125 rem (2 px) de .champ-reponse et des deux sorties. Un
            # trait de chiffre plus fin que cela n'est plus une ligne du produit, c'est un cheveu.
            if mesure["epaisseurDeTraitRendue"]:
                partDuTrait = mesure["epaisseurDeTraitRendue"] / mesure["hauteurRendue"]
                mesure["partDuTraitDansLaHauteurDeLImage"] = round(partDuTrait, 4)
                mesure["hauteurDImageMinimale"]["traitAuPlancherDuProduit"] = round(
                    PLANCHER_DE_TRAIT_PX / partDuTrait, 1)
            rapport["dessins"].append(mesure)
            sys.stderr.write(
                "  %-36s %2d glyphes, %2d instances, encre %.1f%% de l'image, "
                "recouvrements %d, sans <text> %s\n"
                % (mesure["dessin"], mesure["glyphesDefinis"], mesure["instancesDeGlyphe"],
                   (part or 0) * 100, mesure["boitesDeGlypheQuiSeRecouvrent"],
                   mesure["aucunTextNiTspan"]))
            for nom, valeur in mesure["hauteurDImageMinimale"].items():
                sys.stderr.write("      %-22s -> image d'au moins %s px de haut\n" % (nom, valeur))
            sys.stderr.flush()
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)
        shutil.rmtree(travail, ignore_errors=True)

    with open(sortie, "w", encoding="utf-8") as fichier:
        json.dump(rapport, fichier, ensure_ascii=False, indent=1, sort_keys=True)
    print("%d dessins mesures, ecrits dans %s" % (len(rapport["dessins"]), sortie))
    return 0


if __name__ == "__main__":
    sys.exit(main())
