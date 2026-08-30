#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure, dans un navigateur reel, le prototype de l'ecran de saisie de s24.

Pourquoi ce script existe. La mecanique de saisie n'existe pas encore : aucune route ne la sert,
donc aucun outil de cette squad ne peut la jouer (auditer-paquet.py, lire-le-pli-a-l-ecran.py et
mesurer-candidats-rep-8.py pilotent tous l'application servie). Une proposition de rendu qui
n'aurait ete verifiee que sur du CSS lu est exactement ce que le mandat d'ines-design refuse : un
CSS qui compile n'est pas un CSS qui se voit. Le vehicule est donc un document statique commite,
squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/prototype-ines-ecran-de-saisie.html, qui
charge les deux feuilles reelles de l'application dans l'ordre reel et recopie la coquille de
templates/accueil.html.

Ce que ce script ne prouve pas, ecrit avant qu'on me le reproche : que l'application servie rendra
ces boites. Il prouve que ces regles, sur ces feuilles, donnent ces pixels. Le releve refait sur le
jar, une fois le lot 4 livre, reste du.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh). Les
juges purs dont il depend - port, viewport, regle injectee - sont ceux de
squad/outils/nadia-prof/balayage-largeur-rangee.py, importes et non recopies, et deja mis a
l'epreuve par balayage-largeur-rangee-test.sh.

Usage :
  python squad/outils/ines-design/mesurer-ecran-de-saisie.py <chemin_du_prototype> <json_de_sortie>

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable
d'environnement CHROME ou present sur le PATH, et le module python "websocket-client".
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
_spec = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_balayage)
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

# Les etats du prototype, dans l'ordre de la partie que l'enfant joue.
ETATS = [
    "fraiche",
    "echec-1",
    "echec-2",
    "revelation",
    "reussite",
    "reussite-stricte",
    "numerique",
    "accents",
]

# 744 x 551 est la reference du produit (DEC-0040, second amendement du 2026-08-22). Les autres
# largeurs encadrent les paliers de la feuille tierce (540 sous 768, 720 dans la bande maison, 720
# a partir de 768, 960 a partir de 992, 1140 a partir de 1200) : mesurer a une seule largeur est
# exactement ce qui a laisse passer le defaut de rembourrage lateral de s05.
LARGEURS = [360, 390, 576, 640, 720, 744, 768, 992, 1200, 1440]
HAUTEUR_DE_REFERENCE = 551

# Les boites que ce releve compare. Un selecteur absent d'un etat rend None, jamais une erreur :
# c'est le fait qu'il soit absent qui est mesure.
SELECTEURS = [
    ".entete-contenu",
    ".zone-jeu",
    ".consigne",
    ".enonce",
    ".saisie",
    ".champ-reponse",
    ".valider",
    ".continuer-manche",
    ".lien-accueil",
    ".message-jeu",
    ".signal",
    ".icone-signal",
    ".verdict",
    ".chaine-jugee",
    ".forme-de-reference",
    ".forme-attendue",
    ".tentatives",
    ".tentative-passee",
    ".touches-accentuees",
    ".touche-accentuee",
]

# Les seuls selecteurs dont le releve garde aussi les composantes de boite et les couleurs : ce
# sont les pressables, ceux qu'ADR-0017 compare.
DETAILLES = [
    ".champ-reponse",
    ".valider",
    ".continuer-manche",
    ".lien-accueil",
    ".touche-accentuee",
]

# Chaines candidates dont on mesure la largeur rendue DANS le champ, pour dire combien de
# caracteres son plafond de 24rem laisse voir. Elles ne fixent aucun contenu : ce sont des
# gabarits de longueur, dont deux viennent de l'arbitrage prealable lui-meme (elleve, deuxcentcinq).
CHAINES_TEMOIN = [
    "7",
    "12",
    "205",
    "à",
    "élève",
    "elleve",
    "quatre-vingt-douze",
    "deux cent cinq",
    "deuxcentcinq",
    "trois cent soixante-quinze",
]

LIRE = r"""
(function () {
    function boite(selecteur) {
        // L'etat mesure d'abord ; a defaut, un element de la coquille permanente seulement - jamais
        // un element d'un AUTRE etat, qui est masque et rendrait une boite de zero pixel au lieu
        // d'une absence. Une boite fausse est pire qu'une case vide.
        var element = document.querySelector('[data-etat="ETAT"] ' + selecteur);
        if (!element) {
            element = Array.prototype.find.call(
                document.querySelectorAll(selecteur),
                function (candidat) { return !candidat.closest('[data-etat]'); });
        }
        if (!element) { return null; }
        var rect = element.getBoundingClientRect();
        var style = getComputedStyle(element);
        var lue = {
            gauche: rect.left, droite: rect.right, haut: rect.top, bas: rect.bottom,
            largeur: rect.width, hauteur: rect.height,
            police: style.fontSize
        };
        // Les composantes que compare ADR-0017, plus les couleurs, seulement sur les pressables :
        // elles ne varient pas avec la largeur du viewport, les repeter vingt fois par etat
        // gonflerait le releve commite sans rien ajouter a ce qu'il prouve.
        if (DETAILLES.indexOf(selecteur) !== -1) {
            lue.graisse = style.fontWeight;
            lue.couleur = style.color;
            lue.fond = style.backgroundColor;
            lue.hauteurMinimale = style.minHeight;
            lue.bordure = style.borderTopWidth + ' ' + style.borderTopColor;
            lue.rembourrage = style.paddingTop + ' ' + style.paddingRight + ' '
                + style.paddingBottom + ' ' + style.paddingLeft;
        }
        return lue;
    }

    var boites = {};
    SELECTEURS.forEach(function (selecteur) { boites[selecteur] = boite(selecteur); });

    var champ = document.querySelector('[data-etat="ETAT"] .champ-reponse');
    var capacite = null;
    var focus = null;
    if (champ) {
        var style = getComputedStyle(champ);
        var regle = document.createElement('span');
        regle.style.position = 'absolute';
        regle.style.visibility = 'hidden';
        regle.style.whiteSpace = 'pre';
        regle.style.font = style.font;
        regle.style.fontWeight = style.fontWeight;
        regle.style.fontSize = style.fontSize;
        regle.style.fontFamily = style.fontFamily;
        document.body.appendChild(regle);
        capacite = { interieur: champ.clientWidth
            - parseFloat(style.paddingLeft) - parseFloat(style.paddingRight), chaines: {} };
        CHAINES.forEach(function (chaine) {
            regle.textContent = chaine;
            capacite.chaines[chaine] = regle.getBoundingClientRect().width;
        });
        regle.textContent = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa';
        capacite.trenteA = regle.getBoundingClientRect().width;
        document.body.removeChild(regle);

        champ.focus();
        var apres = getComputedStyle(champ);
        focus = {
            contourStyle: apres.outlineStyle,
            contourEpaisseur: apres.outlineWidth,
            contourCouleur: apres.outlineColor,
            ombre: apres.boxShadow,
            focusVisible: champ.matches(':focus-visible'),
            // Sans ces deux temoins, un releve de focus peut decrire l'etat de repos sans que rien
            // ne le dise : c'est le cas qu'une premiere execution de ce script a produit.
            actif: document.activeElement === champ,
            documentFocalise: document.hasFocus()
        };
        champ.blur();
    }

    var etat = document.querySelector('[data-etat="ETAT"]');
    var basReel = null;
    if (etat) {
        var enfants = etat.querySelectorAll('*');
        basReel = etat.getBoundingClientRect().bottom;
        enfants.forEach(function (enfant) {
            var b = enfant.getBoundingClientRect().bottom;
            if (b > basReel) { basReel = b; }
        });
    }

    return {
        boites: boites,
        capacite: capacite,
        focus: focus,
        basDeLEcran: basReel,
        hauteurDocument: document.documentElement.scrollHeight,
        viewport: [window.innerWidth, window.innerHeight]
    };
})()
"""


def lire_etat(nav, etat):
    expression = (
        LIRE.replace("ETAT", etat)
        .replace("SELECTEURS", json.dumps(SELECTEURS))
        .replace("DETAILLES", json.dumps(DETAILLES))
        .replace("CHAINES", json.dumps(CHAINES_TEMOIN))
    )
    return nav.evaluer(expression)


def afficher_un_seul_etat(nav, etat):
    """Rend visible le seul etat mesure, et refuse si le document n'en montre pas exactement un."""
    nav.evaluer(
        "(function () {"
        "  var precedent = document.getElementById('commutateur-ines');"
        "  if (precedent) { precedent.remove(); }"
        "  var style = document.createElement('style');"
        "  style.id = 'commutateur-ines';"
        "  style.textContent = '[data-etat=\"%s\"] { display: block; }';"
        "  document.head.appendChild(style);"
        "})()" % etat
    )
    visibles = nav.evaluer(
        "Array.prototype.filter.call(document.querySelectorAll('[data-etat]'),"
        " function (e) { return getComputedStyle(e).display !== 'none'; })"
        ".map(function (e) { return e.getAttribute('data-etat'); })"
    )
    if visibles != [etat]:
        raise blr.MesureNonFiable(
            "le document montre %s alors que la mesure porte sur \"%s\" : un etat de trop "
            "fausserait la hauteur et le pli. Mesure refusee." % (visibles, etat)
        )


def compacter(valeur):
    """Arrondit au centieme de pixel et retire les boites absentes, pour que le releve commite reste
    lisible. Le centieme est en dessous de ce qu'un ecran affiche ; rien de mesure n'est perdu."""
    if isinstance(valeur, float):
        return round(valeur, 2)
    if isinstance(valeur, dict):
        return {cle: compacter(sous) for cle, sous in valeur.items() if sous is not None}
    if isinstance(valeur, list):
        return [compacter(sous) for sous in valeur]
    return valeur


def naviguer(nav, url):
    nav.appeler("Page.navigate", {"url": url})
    for _ in range(120):
        if nav.evaluer("document.readyState") == "complete":
            return
        time.sleep(0.25)
    raise blr.MesureNonFiable("le document %s n'a pas fini de charger. Mesure refusee." % url)


def main():
    if len(sys.argv) != 3:
        print(__doc__)
        return 2
    chemin, sortie = sys.argv[1], sys.argv[2]
    if not os.path.isfile(chemin):
        raise SystemExit("prototype introuvable : %s" % chemin)
    url = "file:///" + os.path.abspath(chemin).replace("\\", "/").lstrip("/")

    binaire = os.environ.get("CHROME") or shutil.which("chrome") or shutil.which("chromium") \
        or shutil.which("google-chrome")
    if not binaire:
        raise SystemExit(
            "aucun navigateur : renseigner la variable d'environnement CHROME, ou en mettre un "
            "sur le PATH."
        )

    profil = tempfile.mkdtemp(prefix="ines-saisie-")
    nav = blr.Navigateur(binaire, profil, 0)
    # Une page sans tete n'a pas le focus systeme : sans cette emulation, ":focus-visible" ne
    # s'applique jamais et le releve de l'anneau de focus decrit le repos en silence.
    nav.appeler("Emulation.setFocusEmulationEnabled", {"enabled": True})
    releve = {"prototype": os.path.basename(chemin), "largeurs": LARGEURS, "etats": ETATS,
              "hauteurDeReference": HAUTEUR_DE_REFERENCE, "mesures": []}
    try:
        for largeur in LARGEURS:
            vu = nav.regler_viewport(largeur, HAUTEUR_DE_REFERENCE)
            naviguer(nav, url)
            nav.regler_viewport(largeur, HAUTEUR_DE_REFERENCE)
            for etat in ETATS:
                afficher_un_seul_etat(nav, etat)
                mesure = lire_etat(nav, etat)
                mesure["largeurDemandee"] = largeur
                mesure["viewportLu"] = vu
                mesure["etat"] = etat
                releve["mesures"].append(mesure)
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    with open(sortie, "w", encoding="utf-8") as fichier:
        json.dump(compacter(releve), fichier, ensure_ascii=False, indent=1, sort_keys=True)
    print("%d mesures ecrites dans %s" % (len(releve["mesures"]), sortie))
    return 0


if __name__ == "__main__":
    sys.exit(main())
