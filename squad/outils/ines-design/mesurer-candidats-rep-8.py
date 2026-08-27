#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure, sur l'application servie, le cout reel d'une feuille candidate pour REP-8 (l'indice
hors de vue apres un echec, catalogue entier, squad/conformite/FICHE-bandit-manchot-reperes-cm1.md).

Pourquoi ce script existe. La piste de correction la plus citee pour REP-8 est deux termes de
forme du gabarit du jeu, jamais un contenu : le rembourrage vertical de ".proposition" et la marge
de ".score" (squad/conformite/proposition-ines-rep-8.md, section 2). Aucun des deux n'est protege
par un plancher de garde-fou (TailleDesTextesEtBoutonsTest ne garde que la police, pas le
rembourrage ni la marge - voir la section citee ci-dessus pour la lecture faite du garde-fou), donc
aucune mesure de ce depot ne dit ce qu'un candidat vaut avant ce script. Il n'ecrit jamais dans
recreo.css : la regle candidate est injectee dans le document rendu, exactement comme
balayage-largeur-rangee.py le fait deja pour sa propre declaration de largeur.

Methode, identique dans son protocole a auditer-paquet.py (squad/outils/nadia-prof/) : une regle
candidate est injectee en dernier dans le head avant que la partie ne commence, puis le jeu est
joue comme le fait auditer-paquet.py (etat au repos, un echec volontaire par item, lecture du bas
du verdict et du bas de l'indice). La regle proposee determine seule ce qu'un delta signifie ; ce
script ne choisit aucune valeur, il la mesure.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Correction du 2026-08-27 (S21-7, trouvee par nadia-prof) : "[data-role=sortie]" est un
"<a href=\"/\">" (templates/accueil.html) - quitter une partie declenche donc une navigation
complete, qui recree le "<head>" et jette la regle candidate injectee. La premiere version de ce
script l'injectait une seule fois, avant la premiere partie, et ne le remarquait jamais : une
execution a plusieurs parties melangeait silencieusement des items mesures avec la regle et des
items mesures sans (squad/conformite/FICHE-bandit-manchot-reperes-cm1.md, section 5). Ce script
repose donc la regle a chaque debut de partie et **verifie sa presence exacte dans le DOM avant
d'enregistrer la mesure de tout item nouveau** ; si la verification echoue, il s'arrete en erreur
plutot que de rendre un chiffre qu'il ne peut pas prouver. Les trois releves commites avant ce
correctif portent tous "parties: 1" et ne sont donc pas touches par ce defaut (verifie par
nadia-prof et par theo-archi independamment, meme fiche).

Usage :
  python squad/outils/ines-design/mesurer-candidats-rep-8.py <url_de_base> <id_du_jeu> <nb_items> \
      <regle_css_candidate_ou_vide> <json_de_sortie>

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client", et la variable PAQUET qui
designe le fichier de contenu servi (pour repondre juste et avancer sans user les trois essais de
M5) - toutes deja requises par auditer-paquet.py, dont ce script reprend le protocole par import de
module, jamais par recopie.
"""
import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_chemin_auditer = os.path.join(_dossier, "..", "nadia-prof", "auditer-paquet.py")
_spec_auditer = importlib.util.spec_from_file_location("auditer_paquet", _chemin_auditer)
auditer = importlib.util.module_from_spec(_spec_auditer)
_spec_auditer.loader.exec_module(auditer)

_chemin_balayage = os.path.join(_dossier, "..", "nadia-prof", "balayage-largeur-rangee.py")
_spec_balayage = importlib.util.spec_from_file_location("balayage_largeur_rangee", _chemin_balayage)
blr = importlib.util.module_from_spec(_spec_balayage)
_spec_balayage.loader.exec_module(blr)

_ID_NOEUD = "mesure-ines-candidat"


def _injecter_regle(nav, regle_css):
    """Pose (ou repose) la regle candidate en dernier dans le head, comme au premier chargement."""
    nav.evaluer(
        "(function(){var n=document.getElementById(%s);"
        "if(!n){n=document.createElement('style');n.id=%s;document.head.appendChild(n);}"
        "n.textContent=%s;return true;})();"
        % (json.dumps(_ID_NOEUD), json.dumps(_ID_NOEUD), json.dumps(regle_css))
    )


def _verifier_regle_presente(nav, regle_css, contexte):
    """Leve une erreur plutot que de laisser enregistrer une mesure dont la regle candidate ne
    peut pas etre prouvee presente au moment de la lecture - le defaut du 2026-08-27."""
    trouvee = nav.evaluer(
        "(function(){var n=document.getElementById(%s);return n ? n.textContent : null;})();"
        % json.dumps(_ID_NOEUD)
    )
    if trouvee != regle_css:
        raise RuntimeError(
            "regle candidate absente ou alteree (%s) : attendue %r, trouvee %r - "
            "aucune mesure ne peut etre rendue pour cet etat." % (contexte, regle_css, trouvee)
        )


def main():
    if len(sys.argv) != 6:
        sys.stderr.write(__doc__)
        return 2
    base = sys.argv[1].rstrip("/")
    jeu = sys.argv[2]
    attendus = int(sys.argv[3])
    regle_css = sys.argv[4]
    sortie = sys.argv[5]

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    bonnes = auditer._frere.lire_bonnes_reponses(os.environ.get("PAQUET"))
    profil = tempfile.mkdtemp(prefix="mesure-ines-rep8-")
    nav = blr.Navigateur(binaire, profil, blr.PORT_DEVERMINAGE)
    vus, parties, lectures, reinjections = {}, 0, 0, 0
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.5)
        if regle_css:
            _injecter_regle(nav, regle_css)
            _verifier_regle_presente(nav, regle_css, "avant la premiere partie")
        while len(vus) < attendus and parties < 25:
            parties += 1
            if regle_css and parties > 1:
                # [data-role=sortie] est une navigation complete (<a href="/">) qui a vide le
                # head a la fin de la partie precedente : la regle candidate est reposee et
                # reverifiee avant de rejouer, jamais supposee encore presente.
                _injecter_regle(nav, regle_css)
                reinjections += 1
                sys.stderr.write(
                    "  regle candidate reposee apres navigation (partie %d)\n" % parties
                )
            if regle_css:
                _verifier_regle_presente(nav, regle_css, "debut de partie %d" % parties)
            nav.evaluer(
                "document.querySelector('[hx-post*=\"%s\"]').click(); true;" % jeu
            )
            time.sleep(1.2)
            for _ in range(12):
                etat = nav.evaluer(auditer.LIRE)
                lectures += 1
                if etat.get("rangees") is None:
                    break
                enonce = etat["enonce"]
                bonne = bonnes.get(enonce)
                if enonce not in vus:
                    if regle_css:
                        _verifier_regle_presente(
                            nav, regle_css, "lecture de l'item %r" % enonce[:60]
                        )
                    vus[enonce] = auditer.auditer_un_item(nav, enonce, bonne)
                    lectures += 2
                if bonne:
                    nav.evaluer(auditer.CLIC % json.dumps(bonne))
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
        "regleCssCandidate": regle_css,
        "parties": parties,
        "reinjectionsDeLaRegle": reinjections,
        "lectures": lectures,
        "itemsVus": len(vus),
        "items": vus,
    }
    texte = json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True)
    open(sortie, "w", encoding="utf-8").write(texte)
    print(texte)
    return 0


if __name__ == "__main__":
    sys.exit(main())
