#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure l'ecran de saisie SERVI par l'application, aux dix largeurs, sur la disposition reelle.

Pourquoi ce script existe et pourquoi il ne remplace pas mesurer-ecran-de-saisie.py. Celui-la
mesurait un prototype statique, parce qu'a s24 la mecanique de saisie n'etait servie par aucune
route. Elle l'est depuis le lot 4, et marc-dev a place la liste des tentatives AU-DESSUS du champ
et non sous le verdict comme la proposition d'ines-design la placait. Il ecrit lui-meme que ce
deplacement est de lui et qu'il n'est pas mesure. Le releve de s24 decrit donc une disposition que
l'application ne rend pas.

Ce que ce script juge, et c'est la condition de nadia-prof telle qu'elle l'a ecrite le 2026-08-31
(SUITES-s24, section 6.3), mot pour mot : "que la chaine barree soit visible EN MEME TEMPS QUE LE
CHAMP, SANS DEFILEMENT, sur les largeurs que le produit vise". Trois faits mesures, jamais un avis :

  - le bas de .tentatives et le bas de .champ-reponse sont-ils tous deux au-dessus du pli ;
  - le defilement de la page est-il nul au moment de la lecture ;
  - le bas de l'ecran entier (le plus bas de tous les descendants) passe-t-il sous le pli.

Le pli est 551 px, la hauteur de reference du produit (DEC-0040, second amendement du 2026-08-22),
et la largeur de reference est 744 px. Les dix largeurs encadrent les paliers de la feuille tierce :
mesurer a une seule largeur est exactement ce qui a laisse passer le defaut de rembourrage lateral
de s05.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur et d'une application servie, que rien dans le depot ne peut garantir (meme limite
qu'outils/mesurer-rendu.sh). Les juges purs dont il depend - port, viewport lu et non suppose -
sont ceux de squad/outils/nadia-prof/balayage-largeur-rangee.py, importes et non recopies.

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable
d'environnement CHROME ou present sur le PATH, l'application servie a l'URL passee en argument,
et le module python "websocket-client". PORT_DEV deplace le port de deverminage quand celui d'un
autre outil est retenu par un navigateur qui ne s'est pas ferme.

Usage :
  CHROME=... [PORT_DEV=9223] python squad/outils/ines-design/mesurer-l-ecran-de-saisie-servi.py \
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
LARGEURS = [360, 390, 576, 640, 720, 744, 768, 992, 1200, 1440]

# Chaines refusees successivement pour atteindre les trois etats d'echec. Elles sont fausses sur
# tout item de complement a 10 - la somme depasse 10 dans les trois cas -, et elles sont de
# longueurs croissantes pour que la liste mesuree ne soit pas la plus courte possible : c'est le
# pire cas de hauteur de liste, pas un cas moyen. Ce ne sont pas des candidats de contenu.
TENTATIVES = ["99", "888", "7777"]

SELECTEURS = [
    ".entete-contenu",
    ".zone-jeu",
    ".ecran-de-jeu",
    ".score",
    ".consigne",
    ".enonce",
    ".tentatives",
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
    ".indice",
]

LIRE = r"""
(function () {
    var r = function (v) { return v === null ? null : Math.round(v * 100) / 100; };
    var ecran = document.querySelector('.ecran-de-jeu');
    function boite(selecteur) {
        var e = document.querySelector(selecteur);
        if (!e) { return null; }
        var rect = e.getBoundingClientRect();
        var style = getComputedStyle(e);
        return {
            gauche: r(rect.left), droite: r(rect.right),
            haut: r(rect.top), bas: r(rect.bottom),
            largeur: r(rect.width), hauteur: r(rect.height),
            police: style.fontSize,
            // Le texte n'est garde que sur les blocs dont le CONTENU explique la hauteur - un
            // enonce, un indice, une explication. Le garder partout mettait la totalite du texte
            // de l'ecran dans .zone-jeu et dans .ecran-de-jeu, a chacune des 440 lectures : le
            // releve pesait 2,3 Mo pour la meme information, et un releve qu'on ne peut plus
            // ouvrir n'est pas une methode consultable.
            texte: AVEC_TEXTE.indexOf(selecteur) === -1 ? null : e.textContent
        };
    }
    var boites = {};
    SELECTEURS.forEach(function (s) { boites[s] = boite(s); });

    var passees = Array.prototype.map.call(
        document.querySelectorAll('.tentative-passee'),
        function (li) {
            var rect = li.getBoundingClientRect();
            var style = getComputedStyle(li);
            return {
                texte: li.textContent,
                haut: r(rect.top), bas: r(rect.bottom),
                gauche: r(rect.left), droite: r(rect.right),
                police: style.fontSize,
                trait: style.textDecorationLine,
                epaisseurTrait: style.textDecorationThickness,
                couleurTrait: style.textDecorationColor
            };
        });

    var basReel = null;
    if (ecran) {
        basReel = ecran.getBoundingClientRect().bottom;
        Array.prototype.forEach.call(ecran.querySelectorAll('*'), function (n) {
            var b = n.getBoundingClientRect().bottom;
            if (b > basReel) { basReel = b; }
        });
    }

    return {
        boites: boites,
        tentativesPassees: passees,
        nbTentativesPassees: passees.length,
        basDeLEcran: r(basReel),
        defilement: window.scrollY,
        hauteurDocument: document.documentElement.scrollHeight,
        viewport: [window.innerWidth, window.innerHeight],
        // Ce que le serveur a declare : sans lui, une lecture peut decrire un autre etat que
        // celui qu'on croit mesurer, et rien ne le dirait.
        focusDeclare: ecran ? ecran.getAttribute('data-focus') : null,
        champPresent: !!document.querySelector('.champ-reponse'),
        valeurDuChamp: (document.querySelector('.champ-reponse') || {}).value || null
    };
})()
"""


# Les seuls selecteurs dont le releve garde le texte : ceux dont le contenu explique la hauteur.
AVEC_TEXTE = [".enonce", ".consigne", ".verdict", ".indice", ".chaine-jugee", ".forme-attendue"]


def lire(nav):
    return nav.evaluer(
        LIRE.replace("SELECTEURS", json.dumps(SELECTEURS))
        .replace("AVEC_TEXTE", json.dumps(AVEC_TEXTE)))


def compacter(valeur):
    """Arrondit au centieme de pixel et retire les cases vides du releve commite.

    Le centieme est en dessous de ce qu'un ecran affiche : rien de mesure n'est perdu. Une boite
    absente disparait au lieu d'occuper trois lignes de "null" - et son absence reste lisible,
    puisque le selecteur n'apparait plus du tout pour cet etat.
    """
    if isinstance(valeur, float):
        return round(valeur, 2)
    if isinstance(valeur, dict):
        return {c: compacter(v) for c, v in valeur.items() if v is not None}
    if isinstance(valeur, list):
        return [compacter(v) for v in valeur]
    return valeur


def naviguer(nav, url):
    nav.appeler("Page.navigate", {"url": url})
    for _ in range(120):
        if nav.evaluer("document.readyState") == "complete":
            time.sleep(0.4)
            return
        time.sleep(0.25)
    raise blr.MesureNonFiable("le document %s n'a pas fini de charger. Mesure refusee." % url)


def ouvrir_le_jeu(nav, jeu):
    clique = nav.evaluer(
        "(function(){var b=document.querySelector('[hx-post*=\"/parties/%s\"]');"
        "if(!b){return false;} b.click(); return true;})()" % jeu
    )
    if not clique:
        raise blr.MesureNonFiable(
            "aucune entree de catalogue ne pointe le jeu \"%s\" sur cette page. Mesure refusee."
            % jeu
        )
    time.sleep(1.3)


def envoyer(nav, chaine):
    """Ecrit la chaine dans le champ et presse le bouton de validation, comme l'enfant le fait.

    La valeur est posee sur l'element puis un evenement "input" est emis : sans lui, un moteur de
    gabarit ou une regle CSS qui reagirait a la saisie ne le verrait pas, et la mesure decrirait un
    champ que personne n'a rempli.
    """
    pose = nav.evaluer(
        "(function(){var c=document.querySelector('.champ-reponse');"
        "if(!c){return false;} c.value=%s;"
        "c.dispatchEvent(new Event('input', {bubbles: true})); return c.value===%s;})()"
        % (json.dumps(chaine), json.dumps(chaine))
    )
    if not pose:
        raise blr.MesureNonFiable(
            "la chaine \"%s\" n'a pas ete posee dans le champ. Mesure refusee." % chaine
        )
    nav.evaluer("document.querySelector('.valider').click(); true;")
    time.sleep(0.9)


def juger(etat):
    """Le verdict de la condition de nadia-prof, sur les seuls chiffres de cette lecture."""
    champ = etat["boites"].get(".champ-reponse")
    liste = etat["boites"].get(".tentatives")
    verdict = {
        "champPresent": champ is not None,
        "listePresente": liste is not None,
        "defilementNul": etat["defilement"] == 0,
        "basDeLEcranAuDessusDuPli": etat["basDeLEcran"] is not None
        and etat["basDeLEcran"] <= PLI_PX,
    }
    if champ is not None and liste is not None:
        verdict["champEntierAuDessusDuPli"] = champ["bas"] <= PLI_PX
        verdict["listeEntiereAuDessusDuPli"] = liste["bas"] <= PLI_PX
        # La condition litterale : les deux dans le meme regard, sans defilement.
        verdict["conditionD5Servie"] = (
            champ["bas"] <= PLI_PX and liste["bas"] <= PLI_PX and etat["defilement"] == 0
        )
    else:
        verdict["conditionD5Servie"] = None
    return verdict


def main():
    if len(sys.argv) != 5:
        print(__doc__)
        return 2
    base, jeu = sys.argv[1].rstrip("/"), sys.argv[2]
    attendus, sortie = int(sys.argv[3]), sys.argv[4]

    binaire = os.environ.get("CHROME") or shutil.which("chrome") \
        or shutil.which("chromium") or shutil.which("google-chrome")
    if not binaire:
        raise SystemExit(
            "aucun navigateur : renseigner la variable d'environnement CHROME, ou en mettre un "
            "sur le PATH."
        )
    port = int(os.environ.get("PORT_DEV", "9223"))

    profil = tempfile.mkdtemp(prefix="ines-saisie-servie-")
    nav = blr.Navigateur(binaire, profil, port)
    releve = {
        "url": base, "jeu": jeu, "pli": PLI_PX, "largeurs": LARGEURS,
        "tentativesRefusees": TENTATIVES, "itemsAttendus": attendus, "mesures": [],
    }
    try:
        for largeur in LARGEURS:
            vu = nav.regler_viewport(largeur, PLI_PX)
            # Une partie laissee en cours par la largeur precedente fait rendre l'ecran de
            # confirmation de reprise a la place de la manche fraiche (S1-4). La mesure decrirait
            # alors un autre ecran sans que rien ne le dise : la session est donc videe entre deux
            # largeurs, jamais supposee neuve.
            nav.appeler("Network.clearBrowserCookies")
            naviguer(nav, base + "/")
            nav.regler_viewport(largeur, PLI_PX)
            # Le tirage d'une manche est aleatoire et l'indice n'a pas la meme hauteur d'un item a
            # l'autre : mesurer UNE manche par largeur ferait dependre le chiffre du tirage, et
            # deux largeurs se compareraient sur deux items differents. Le releve joue donc des
            # parties jusqu'a avoir vu tous les enonces attendus, et la lecture porte l'enonce.
            vus = set()
            parties = 0
            while len(vus) < attendus and parties < 12:
                parties += 1
                ouvrir_le_jeu(nav, jeu)
                for _ in range(20):
                    fraiche = lire(nav)
                    if not fraiche["champPresent"]:
                        break
                    enonce = (fraiche["boites"].get(".enonce") or {}).get("texte")
                    deja = enonce in vus
                    if enonce:
                        vus.add(enonce)
                    lectures = [("fraiche", fraiche)]
                    for rang, nom in enumerate(["echec-1", "echec-2", "revelation"]):
                        envoyer(nav, TENTATIVES[rang])
                        lectures.append((nom, lire(nav)))
                    if not deja:
                        for nom, etat in lectures:
                            etat["etat"] = nom
                            etat["enonce"] = enonce
                            etat["largeurDemandee"] = largeur
                            etat["viewportLu"] = vu
                            etat["verdict"] = juger(etat)
                            releve["mesures"].append(etat)
                        sys.stderr.write(
                            "  %5d %-12s fraiche %6s echec2 %6s revel %6s liste %6s defil %s\n"
                            % (largeur, (enonce or "")[:12],
                               lectures[0][1]["basDeLEcran"], lectures[2][1]["basDeLEcran"],
                               lectures[3][1]["basDeLEcran"],
                               (lectures[2][1]["boites"].get(".tentatives") or {}).get("bas"),
                               max(l[1]["defilement"] for l in lectures)))
                    nav.evaluer(
                        "var b=document.querySelector('.continuer-manche');"
                        "if(b){b.click();} true;")
                    time.sleep(0.9)
                nav.evaluer(
                    "var s=document.querySelector('[data-role=sortie]');"
                    "if(s){s.click();} true;")
                time.sleep(1.0)
                naviguer(nav, base + "/")
                nav.regler_viewport(largeur, PLI_PX)
            if len(vus) < attendus:
                raise blr.MesureNonFiable(
                    "a %d px, %d enonces vus sur %d attendus apres %d parties : le releve serait "
                    "partiel sans le dire. Mesure refusee." % (largeur, len(vus), attendus, parties)
                )
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    avec_liste = [m for m in releve["mesures"] if m["verdict"]["conditionD5Servie"] is not None]
    releve["resume"] = {
        "lecturesAvecListeEtChamp": len(avec_liste),
        "lecturesQuiServentLaConditionD5": sum(
            1 for m in avec_liste if m["verdict"]["conditionD5Servie"]),
        "largeursQuiNeServentPasLaConditionD5": sorted(set(
            m["largeurDemandee"] for m in avec_liste if not m["verdict"]["conditionD5Servie"])),
        "lecturesADefilementNonNul": sum(
            1 for m in releve["mesures"] if m["defilement"] != 0),
        "lecturesDontLeBasPasseSousLePli": sorted(set(
            (m["largeurDemandee"], m["etat"]) for m in releve["mesures"]
            if m["basDeLEcran"] is not None and m["basDeLEcran"] > PLI_PX)),
    }

    with open(sortie, "w", encoding="utf-8") as fichier:
        json.dump(compacter(releve), fichier, ensure_ascii=False, indent=1,
                  sort_keys=True)
    print("%d mesures ecrites dans %s" % (len(releve["mesures"]), sortie))
    print(json.dumps(releve["resume"], ensure_ascii=False, indent=1))
    return 0


if __name__ == "__main__":
    sys.exit(main())
