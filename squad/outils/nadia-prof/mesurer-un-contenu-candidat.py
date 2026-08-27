#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Mesure un contenu qui n'existe pas encore, sur l'application deja servie.

Pourquoi cet outil existe. Un arbitrage prealable se prononce avant qu'une ligne de code soit
ecrite : le paquet n'est donc dans aucun classpath, aucun chargeur ne le lit, et rien ne peut le
jouer. Les autres outils de ce repertoire mesurent ce qui est servi ; celui-ci mesure ce qui est
seulement propose, en substituant les textes candidats dans le document deja rendu d'un jeu voisin,
puis en lisant la geometrie reelle. C'est le seul moyen que j'aie trouve de dire "ce paquet naitra
au-dessus du pli" avec un chiffre plutot qu'avec une intuition - le manque exact qui a coute un
refus a s20, ou ma propre specification demandait a quatre libelles de porter cinq cas.

Ce qu'il mesure, et rien d'autre :

  - la boite offerte a l'enonce, a la consigne, a l'indice et a un libelle de proposition ;
  - la largeur rendue de chaque texte candidat, avec la police reellement calculee de l'element
    qui le portera, jamais par comptage de caracteres (un "1" et un "m" ne tiennent pas la meme
    place) ;
  - pour chaque cas candidat, apres un echec reel sur le jeu temoin : le nombre de rangees, le bas
    du bloc de verdict, le bas de la boite d'indice, et le defilement.

Ce qu'il ne mesure pas, et ne doit pas etre lu comme mesurant : la justesse pedagogique du contenu,
et le comportement du paquet une fois charge par le chargeur reel. Un cas mesure ici au-dessus du
pli reste a remesurer sur l'increment servi - c'est la porte de rendu, pas ce script, qui tranche.

Le calibrage du canevas est verifie a chaque execution, et c'est delibere. Une mesure au canevas
retombe **silencieusement** sur "10px sans-serif" quand la police lui est passee depuis un element
detache du document - ce qui arrive des qu'un echange htmx a remplace le fragment entre la lecture
de la police et la mesure. Le script mesure donc d'abord une chaine de reference dont la largeur
est connue et commitee (590,05 px, FICHE-bandit-manchot-reperes-cm1.md, section 6, 2026-08-27) et
s'arrete si l'ecart depasse le pixel. Sans ce controle, l'execution rend des largeurs 3,2 fois trop
petites sans un mot : c'est un faux vert, la famille d'ecart que ce depot sanctionne.

Ce script n'est pas un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
navigateur, que rien dans le depot ne peut garantir (meme limite qu'outils/mesurer-rendu.sh).

Prerequis nommes, jamais leur emplacement (ADR-0010) : un Chrome designe par la variable CHROME ou
sur le PATH, l'application servie, le module python "websocket-client".

PORT_DEV designe le port de deverminage. Tant que la story S21-1 n'a pas ete confirmee, l'outillage
de ce repertoire s'attache au premier navigateur qui repond sur ce port sans verifier que c'est le
sien : donner ici un port distinct de 9319 est la seule protection disponible quand une autre
mesure tourne sur le meme poste.

Usage :
  CHROME=... [PORT_DEV=9337] python squad/outils/nadia-prof/mesurer-un-contenu-candidat.py \
      <url_de_base> <id_du_jeu_temoin> <corpus.json> <sortie.json>

Le corpus est un objet JSON a trois cles :
  "textes"  : {nom: {"quoi": "enonce"|"consigne"|"proposition", "valeurs": [...]}}
  "indices" : [textes mesures avec la police de la boite d'indice]
  "cas"     : [{"nom", "consigne", "enonce", "libelles": [...], "indice"}]
"""

import importlib.util
import json
import os
import shutil
import sys
import tempfile
import time

_dossier = os.path.dirname(os.path.abspath(__file__))
_spec = importlib.util.spec_from_file_location(
    "balayage_largeur_rangee", os.path.join(_dossier, "balayage-largeur-rangee.py")
)
blr = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(blr)

PLI_PX = 551
CALIBRAGE_TEXTE = "quatre-vingt-seize mille deux cent trois"
CALIBRAGE_ATTENDU_PX = 590.05
CALIBRAGE_TOLERANCE_PX = 1.0

MESURE = r"""
window.__m = (function(){
  var enonce = document.querySelector('.enonce');
  var prop = document.querySelector('.proposition');
  var rangee = document.querySelector('.propositions');
  var consigne = document.querySelector('.consigne');
  var se = getComputedStyle(enonce), sp = getComputedStyle(prop), sc = getComputedStyle(consigne);
  var sr = getComputedStyle(rangee);
  var c = document.createElement('canvas').getContext('2d');
  var f = function(s){ return s.fontWeight + ' ' + s.fontSize + ' ' + s.fontFamily; };
  var r = function(v){ return Math.round(v * 100) / 100; };
  var lr = rangee.getBoundingClientRect().width;
  var gap = parseFloat(sr.columnGap);
  var horsTexte = parseFloat(sp.paddingLeft) + parseFloat(sp.paddingRight)
                + parseFloat(sp.borderLeftWidth) + parseFloat(sp.borderRightWidth);
  return {
    largeurOfferteALEnonce: r(enonce.getBoundingClientRect().width),
    largeurOfferteALaConsigne: r(consigne.getBoundingClientRect().width),
    largeurRangee: r(lr),
    ecartEntreBoutons: gap,
    largeurHorsTexteDUnBouton: horsTexte,
    budgetTexteATroisLibelles: r(lr - 3 * horsTexte - 2 * gap),
    budgetTexteADeuxLibelles: r(lr - 2 * horsTexte - gap),
    policeDeLEnonce: f(se), policeDeLaProposition: f(sp), policeDeLaConsigne: f(sc),
    mesurer: function(t, quoi){
      c.font = f(quoi === 'enonce' ? se : (quoi === 'consigne' ? sc : sp));
      return r(c.measureText(t).width);
    }
  };
})();
"""

MESURE_INDICE = r"""
(function(){
  var i = document.querySelector('.message-jeu .indice');
  if (!i) { return null; }
  var si = getComputedStyle(i);
  var c = document.createElement('canvas').getContext('2d');
  var r = function(v){ return Math.round(v * 100) / 100; };
  window.__mi = {
    largeur: r(i.getBoundingClientRect().width),
    police: si.fontWeight + ' ' + si.fontSize + ' ' + si.fontFamily,
    interligne: si.lineHeight,
    mesurer: function(t){ c.font = window.__mi.police; return r(c.measureText(t).width); }
  };
  return {largeurOfferteALIndice: window.__mi.largeur, policeDeLIndice: window.__mi.police,
          interligneDeLIndice: window.__mi.interligne};
})();
"""

LIRE = r"""
(function(){
  var r = function(v){ return (v === null || v === undefined) ? null : Math.round(v * 100) / 100; };
  var q = function(s){ return document.querySelector(s); };
  var rangee = q('.propositions');
  if (!rangee) { return {rangees: null, fin: !!q('.fin-de-partie')}; }
  var boutons = Array.prototype.slice.call(rangee.querySelectorAll('.proposition'));
  var hauts = {};
  boutons.forEach(function(b){ hauts[r(b.getBoundingClientRect().top)] = 1; });
  var bas = function(n){ return n ? r(n.getBoundingClientRect().bottom) : null; };
  return {
    consigne: q('.consigne') ? q('.consigne').textContent.trim() : null,
    enonce: q('.enonce') ? q('.enonce').textContent.trim() : null,
    hauteurConsigne: q('.consigne') ? r(q('.consigne').getBoundingClientRect().height) : null,
    hauteurEnonce: q('.enonce') ? r(q('.enonce').getBoundingClientRect().height) : null,
    rangees: Object.keys(hauts).length,
    nbPropositions: boutons.length,
    propositions: boutons.map(function(b){ return b.textContent.trim(); }),
    hauteurBouton: boutons.length ? r(boutons[0].getBoundingClientRect().height) : null,
    basDesPropositions: boutons.length
      ? r(Math.max.apply(null, boutons.map(function(b){ return b.getBoundingClientRect().bottom; })))
      : null,
    basDuVerdict: bas(q('.message-jeu .verdict')),
    basDeLIndice: bas(q('.message-jeu .indice')),
    hauteurIndice: q('.message-jeu .indice')
      ? r(q('.message-jeu .indice').getBoundingClientRect().height) : null,
    defilement: window.scrollY,
    hauteurDocument: r(document.documentElement.scrollHeight)
  };
})();
"""

SUBSTITUER = r"""
(function(o){
  var q = function(s){ return document.querySelector(s); };
  if (o.consigne && q('.consigne')) { q('.consigne').textContent = o.consigne; }
  if (o.enonce && q('.enonce')) { q('.enonce').textContent = o.enonce; }
  var rangee = q('.propositions');
  var boutons = Array.prototype.slice.call(rangee.querySelectorAll('.proposition'));
  while (boutons.length > o.libelles.length) {
    var trop = boutons.pop();
    trop.parentNode.removeChild(trop);
  }
  while (boutons.length < o.libelles.length) {
    var copie = boutons[0].cloneNode(true);
    rangee.appendChild(copie);
    boutons.push(copie);
  }
  boutons.forEach(function(b, i){ b.textContent = o.libelles[i]; });
  var indice = q('.message-jeu .indice');
  if (o.indice && indice) { indice.textContent = o.indice; }
  return true;
})(OBJET);
"""

CLIC = ("Array.prototype.slice.call(document.querySelectorAll('.proposition'))"
        ".filter(function(b){return b.textContent.trim()===%s;})[0].click(); true;")


def echouer_une_fois(nav):
    """Fait echouer la manche courante pour faire apparaitre le verdict et la boite d'indice."""
    etat = nav.evaluer(LIRE)
    for proposition in etat["propositions"]:
        nav.evaluer(CLIC % json.dumps(proposition))
        time.sleep(0.8)
        etat = nav.evaluer(LIRE)
        if etat.get("basDeLIndice") is not None:
            return etat
    return etat


def main():
    if len(sys.argv) < 5:
        sys.stderr.write(__doc__)
        return 2
    base, temoin, corpus_chemin, sortie = (
        sys.argv[1].rstrip("/"), sys.argv[2], sys.argv[3], sys.argv[4])
    corpus = json.load(open(corpus_chemin, encoding="utf-8"))

    binaire = (
        os.environ.get("CHROME")
        or shutil.which("chrome")
        or shutil.which("google-chrome")
        or shutil.which("chromium")
    )
    if not binaire:
        sys.stderr.write("aucun navigateur trouve : PATH ou variable CHROME.\n")
        return 1

    profil = tempfile.mkdtemp(prefix="candidat-nadia-")
    nav = blr.Navigateur(binaire, profil,
                         int(os.environ.get("PORT_DEV", blr.PORT_DEVERMINAGE)))
    rapport = {"viewport": [blr.VIEWPORT_LARGEUR, PLI_PX], "jeuTemoin": temoin, "pli": PLI_PX}
    try:
        nav.appeler("Page.navigate", {"url": base + "/"})
        time.sleep(2.0)
        nav.evaluer("document.querySelector('[hx-post*=\"%s\"]').click(); true;" % temoin)
        time.sleep(1.5)
        rapport["temoinAuRepos"] = nav.evaluer(LIRE)
        rapport["temoinApresUnEchec"] = echouer_une_fois(nav)
        rapport["cadreDeLIndice"] = nav.evaluer(MESURE_INDICE)
        rapport["cadre"] = nav.evaluer(MESURE)

        calibrage = nav.evaluer(
            "window.__m.mesurer(%s, 'proposition');" % json.dumps(CALIBRAGE_TEXTE))
        rapport["calibrage"] = {"texte": CALIBRAGE_TEXTE, "mesure": calibrage,
                                "attendu": CALIBRAGE_ATTENDU_PX}
        if calibrage is None or abs(calibrage - CALIBRAGE_ATTENDU_PX) > CALIBRAGE_TOLERANCE_PX:
            raise RuntimeError(
                "calibrage du canevas faux : %s px au lieu de %s px. La police n'a pas ete"
                " appliquee (element detache) et toutes les largeurs seraient fausses."
                % (calibrage, CALIBRAGE_ATTENDU_PX))

        largeurs = {}
        for nom, entree in corpus.get("textes", {}).items():
            largeurs[nom] = {}
            for texte in entree["valeurs"]:
                largeurs[nom][texte] = nav.evaluer(
                    "window.__m.mesurer(%s, %s);"
                    % (json.dumps(texte), json.dumps(entree["quoi"])))
        rapport["largeurs"] = largeurs

        rapport["largeursDIndice"] = {
            texte: nav.evaluer("window.__mi.mesurer(%s);" % json.dumps(texte))
            for texte in corpus.get("indices", [])
        }

        cas_mesures = []
        for cas in corpus.get("cas", []):
            nav.evaluer(SUBSTITUER.replace("OBJET", json.dumps(
                {"consigne": cas.get("consigne"), "enonce": cas.get("enonce"),
                 "libelles": cas["libelles"], "indice": cas.get("indice")}, ensure_ascii=False)))
            time.sleep(0.35)
            lu = nav.evaluer(LIRE)
            lu["nom"] = cas["nom"]
            lu["verdictAuDessusDuPli"] = (
                lu["basDuVerdict"] is not None and lu["basDuVerdict"] <= PLI_PX)
            lu["indiceEntierAuDessusDuPli"] = (
                lu["basDeLIndice"] is not None and lu["basDeLIndice"] <= PLI_PX)
            cas_mesures.append(lu)
            sys.stderr.write(
                "  %-44s rangees %s  bouton %s  verdict %s  indice %s\n"
                % (cas["nom"][:44], lu["rangees"], lu["hauteurBouton"],
                   lu["basDuVerdict"], lu["basDeLIndice"]))
        rapport["cas"] = cas_mesures
    finally:
        nav.fermer()
        shutil.rmtree(profil, ignore_errors=True)

    open(sortie, "w", encoding="utf-8").write(
        json.dumps(rapport, ensure_ascii=False, indent=2, sort_keys=True))
    return 0


if __name__ == "__main__":
    sys.exit(main())
