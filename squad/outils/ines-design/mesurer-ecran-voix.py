#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure, dans un navigateur reel, le prototype de l'ecran de la voix adulte de s26.

Pourquoi ce script existe. La mecanique a voix n'existe pas encore : aucune route ne la sert, donc
aucun outil de cette squad ne peut la jouer. Une proposition de rendu qui n'aurait ete verifiee que
sur du CSS lu est exactement ce que le mandat d'ines-design refuse : un CSS qui compile n'est pas un
CSS qui se voit. Le vehicule est donc un document statique commite,
squad/design/prototype-ines-s26-ecran-voix.html, qui charge les deux feuilles reelles de
l'application dans l'ordre reel et recopie la coquille de templates/accueil.html. Meme forme que
squad/outils/ines-design/mesurer-ecran-de-saisie.py (s24), adaptee aux six etats de ce prototype.

Ce que ce script ne prouve pas : que l'application servie rendra ces boites une fois la mecanique
ecrite. Il prouve que ces regles, sur ces feuilles, donnent ces pixels sur ce prototype.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir. Les juges purs dont il depend - port, viewport,
regle injectee - sont ceux de squad/outils/nadia-prof/balayage-largeur-rangee.py, importes et non
recopies.

Usage :
  python squad/outils/ines-design/mesurer-ecran-voix.py <chemin_du_prototype> <json_de_sortie>

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

# Les six etats du prototype.
ETATS = [
    "localiser",
    "identifier",
    "associer",
    "ecrire-fraiche",
    "ecrire-echec",
    "identifier-reussite",
]

# Meme jeu de largeurs que mesurer-ecran-de-saisie.py (s24) : 744 x 551 est la reference du produit
# (DEC-0040), les autres encadrent les paliers de la feuille tierce et la bande maison.
LARGEURS = [360, 390, 576, 640, 720, 744, 768, 992, 1200, 1440]
HAUTEUR_DE_REFERENCE = 551

# Les boites que ce releve compare. Un selecteur absent d'un etat rend None, jamais une erreur :
# c'est le fait qu'il soit absent qui est mesure.
SELECTEURS = [
    ".entete-contenu",
    ".zone-jeu",
    ".bloc-adulte",
    ".etiquette-adulte",
    ".stimulus-a-lire",
    ".consigne-adulte",
    ".consigne",
    ".propositions",
    ".proposition",
    ".saisie",
    ".champ-reponse",
    ".valider",
    ".continuer-manche",
    ".lien-accueil",
    ".message-jeu",
    ".signal",
    ".icone-signal",
    ".verdict",
]

# Les seuls selecteurs dont le releve garde aussi les composantes de boite et les couleurs : ce
# sont les pressables et le bloc adulte, dont le contraste est la piece a verifier sur le rendu reel.
DETAILLES = [
    ".bloc-adulte",
    ".consigne-adulte",
    ".proposition",
    ".champ-reponse",
    ".valider",
    ".continuer-manche",
    ".lien-accueil",
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
        if (DETAILLES.indexOf(selecteur) !== -1) {
            lue.graisse = style.fontWeight;
            lue.couleur = style.color;
            lue.fond = style.backgroundColor;
            lue.bordure = style.borderTopWidth + ' ' + style.borderTopColor;
            lue.rembourrage = style.paddingTop + ' ' + style.paddingRight + ' '
                + style.paddingBottom + ' ' + style.paddingLeft;
        }
        return lue;
    }

    var boites = {};
    SELECTEURS.forEach(function (selecteur) { boites[selecteur] = boite(selecteur); });

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

    profil = tempfile.mkdtemp(prefix="ines-voix-")
    nav = blr.Navigateur(binaire, profil, 0)
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
