#!/usr/bin/env python
"""Mesure l'accueil de recreo sur un serveur qui tourne, dans un Chrome headless pilote par le protocole
de debogage (HS-ACCUEIL-MESURE, DEC-0117 section 4).

Ce que cette commande fait, pour chaque niveau du catalogue servi :
  - charge /niveaux/<niveau> dans une session neuve (la vue par defaut) et releve, par
    getBoundingClientRect(), les cartes (button.demarrer), les bandeaux de matiere, les tuiles ;
  - si l'accueil porte un bouton de bascule, appuie dessus et releve l'etage 1 de V1, appuie sur chaque
    tuile, releve l'etage 2, revient a l'etage 1 par le bouton, puis rend la vue par defaut ;
  - compare les identifiants de jeu atteints (V2 ; V1 = union des etages 2) a ceux du catalogue lu SUR
    DISQUE (catalogue.yaml et descripteurs de contenu/), jamais a un nombre ecrit ici.

Une carte est "entierement visible" si top >= 0, bottom <= innerHeight, left >= 0 et right <= innerWidth :
une carte coupee par le pli ne compte pas.

Ce script n'est PAS un garde-fou de CI : il lui faut un Chrome et un serveur. Ce qui est execute par la CI,
c'est son juge pur (outils/mesurer-accueil-test.py), eprouve par des entrees fabriquees (ADR-0009). Le
verdict de completude rend un code de sortie 1 ; les nombres de cartes visibles sont rapportes, jamais
juges, sauf si un seuil est demande (--seuil-v2, --seuil-etage2) : le seuil est une decision de produit.

Prerequis nommes, jamais leur emplacement (ADR-0010) : Python 3, un Chrome ou Chromium (sur le PATH sous
chrome, google-chrome ou chromium, ou designe par la variable CHROME), un serveur deja lance. Le serveur
n'est pas lance ici : qui le lance lit son PID dans son propre journal et ne tue que ce PID. Le Chrome
lance ici est arrete par son seul PID, jamais par nom d'image.

Usage :
    python outils/mesurer-accueil.py --url http://localhost:<port> [--largeur 744] [--hauteur 551]
        [--ressources adapters/adapter-content/src/main/resources] [--niveaux CP CE1 CM1] [--json]
"""

import argparse
import base64
import hashlib
import json
import os
import re
import shutil
import socket
import struct
import subprocess
import sys
import tempfile
import time
import urllib.request
from pathlib import Path

# ---------------------------------------------------------------------------------------------------
# Juges purs : aucune commande, aucun navigateur. Ce sont eux que mesurer-accueil-test.py eprouve.
# ---------------------------------------------------------------------------------------------------


def juger_invocation(largeur, hauteur, url):
    """Rend la liste des motifs de refus (vide si l'invocation mesure ce qu'elle pretend mesurer)."""
    motifs = []
    if not isinstance(largeur, int) or largeur <= 0:
        motifs.append("largeur invalide : un entier positif de pixels est attendu.")
    if not isinstance(hauteur, int) or hauteur <= 0:
        motifs.append("hauteur invalide : un entier positif de pixels est attendu.")
    if not re.match(r"^https?://[^/\s]+$", url or ""):
        motifs.append("url invalide : http(s)://hote[:port], sans chemin final, est attendu.")
    return motifs


def carte_entierement_visible(carte, largeur, hauteur):
    return (
        carte["top"] >= 0
        and carte["bottom"] <= hauteur
        and carte["left"] >= 0
        and carte["right"] <= largeur
    )


def compter_visibles(cartes, largeur, hauteur):
    return sum(1 for c in cartes if carte_entierement_visible(c, largeur, hauteur))


def colonnes(cartes):
    """Nombre de valeurs distinctes de `left`, arrondies au pixel."""
    return len({round(c["left"]) for c in cartes})


def nommer(ids, plafond=20):
    ids = list(ids)
    if len(ids) <= plafond:
        return ", ".join(ids)
    return "%s ... (+%d autres)" % (", ".join(ids[:plafond]), len(ids) - plafond)


def comparer_ids(attendus, atteints):
    """Rend (manquants, en_trop, doublons), listes triees. `atteints` est une liste : un doublon compte."""
    vus = set()
    doublons = set()
    for i in atteints:
        if i in vus:
            doublons.add(i)
        vus.add(i)
    attendus = set(attendus)
    return sorted(attendus - vus), sorted(vus - attendus), sorted(doublons)


def juger_completude(attendu, mesure):
    """Rend la liste des ecarts de completude d'un niveau (vide = conforme).

    `attendu` : {"ids": [...], "matieres": [...]} lu sur disque.
    `mesure`  : {"v2": ecran, "etages2": {segment: ecran}, "etage1": ecran | None, ...}.
    Un ecran : {"cartes": [{"id", ...}], "bandeaux": [...], "tuiles": [...]}.
    """
    ecarts = []

    def verifier(nom, ids):
        manquants, en_trop, doublons = comparer_ids(attendu["ids"], ids)
        if manquants:
            ecarts.append("%s : %d jeu(x) inatteignable(s) : %s" % (nom, len(manquants), nommer(manquants)))
        if en_trop:
            ecarts.append("%s : %d jeu(x) hors du niveau : %s" % (nom, len(en_trop), nommer(en_trop)))
        if doublons:
            ecarts.append("%s : identifiant(s) en double : %s" % (nom, nommer(doublons)))

    v2 = mesure["v2"]
    verifier("vue par defaut", [c["id"] for c in v2["cartes"]])
    if v2["bandeaux"]:
        if len(v2["bandeaux"]) != len(attendu["matieres"]):
            ecarts.append(
                "vue par defaut : %d bandeau(x) pour %d matiere(s) du niveau"
                % (len(v2["bandeaux"]), len(attendu["matieres"]))
            )
        vides = [b["texte"] for b in v2["bandeaux"] if b.get("cartes", 1) == 0]
        if vides:
            ecarts.append("vue par defaut : bandeau(x) sans carte : %s" % ", ".join(vides))
    if mesure.get("etage1") is not None:
        e1 = mesure["etage1"]
        if len(e1["tuiles"]) != len(attendu["matieres"]):
            ecarts.append(
                "etage 1 : %d tuile(s) pour %d matiere(s) du niveau" % (len(e1["tuiles"]), len(attendu["matieres"]))
            )
        ids = []
        for segment, e2 in mesure["etages2"].items():
            if not e2["cartes"]:
                ecarts.append("etage 2 %s : aucune carte" % segment)
            ids.extend(c["id"] for c in e2["cartes"])
        verifier("V1 (somme des etages 2)", ids)
        if len(mesure["etages2"]) != len(e1["tuiles"]):
            ecarts.append(
                "etage 1 : %d tuile(s) mais %d etage(s) 2 releve(s)" % (len(e1["tuiles"]), len(mesure["etages2"]))
            )
    return ecarts


def juger_seuils(mesure, largeur, hauteur, seuil_v2=None, seuil_etage2=None):
    """Seuils de produit, juges seulement s'ils sont demandes. Rend la liste des ecarts."""
    ecarts = []
    if seuil_v2 is not None:
        n = compter_visibles(mesure["v2"]["cartes"], largeur, hauteur)
        if n < seuil_v2:
            ecarts.append("vue par defaut : %d carte(s) entierement visible(s), seuil %d" % (n, seuil_v2))
    if seuil_etage2 is not None:
        for segment, e2 in mesure.get("etages2", {}).items():
            n = compter_visibles(e2["cartes"], largeur, hauteur)
            attendu = min(len(e2["cartes"]), seuil_etage2)
            if n < attendu:
                ecarts.append(
                    "etage 2 %s : %d carte(s) entierement visible(s) sur %d, seuil min(n, %d) = %d"
                    % (segment, n, len(e2["cartes"]), seuil_etage2, attendu)
                )
    return ecarts


# --- lecture du catalogue sur disque : lignes seulement, aucune dependance YAML -------------------------


def lire_attendu(ressources):
    """Rend ({niveau: {"ids": [...], "matieres": [...]}}, {matiere: libelle}) lus sous `ressources`.

    Joint chaque `paquet:` du catalogue au fichier de contenu/ dont l'`id:` de tete est ce paquet, puis lit
    `niveau:` et `matiere:` de son descripteur. Les matieres sont rendues dans l'ordre de matieres.yaml
    quand il existe, sinon dans l'ordre de premiere apparition.
    """
    ressources = Path(ressources)
    catalogue = (ressources / "catalogue.yaml").read_text(encoding="utf-8")
    jeux = []
    courant = None
    for ligne in catalogue.splitlines():
        m = re.match(r"^\s*-\s+id:\s*(\S+)\s*$", ligne)
        if m:
            courant = {"id": m.group(1)}
            jeux.append(courant)
            continue
        m = re.match(r"^\s+paquet:\s*(\S+)\s*$", ligne)
        if m and courant is not None:
            courant["paquet"] = m.group(1)

    paquets = {}
    for f in sorted((ressources / "contenu").glob("*.yaml")):
        identifiant = niveau = matiere = None
        for ligne in f.read_text(encoding="utf-8").splitlines():
            m = re.match(r"^id:\s*(\S+)\s*$", ligne)
            if m and identifiant is None:
                identifiant = m.group(1)
            m = re.match(r'^\s+niveau:\s*"([^"]+)"\s*$', ligne)
            if m and niveau is None:
                niveau = m.group(1)
            m = re.match(r'^\s+matiere:\s*"([^"]+)"\s*$', ligne)
            if m and matiere is None:
                matiere = m.group(1)
        if identifiant:
            paquets[identifiant] = (niveau, matiere)

    libelles = {}
    ordre = []
    fichier_matieres = ressources / "matieres.yaml"
    if fichier_matieres.exists():
        matiere = None
        for ligne in fichier_matieres.read_text(encoding="utf-8").splitlines():
            m = re.match(r'^\s*-\s+matiere:\s*"([^"]+)"\s*$', ligne)
            if m:
                matiere = m.group(1)
                ordre.append(matiere)
            m = re.match(r'^\s+libelle:\s*"([^"]+)"\s*$', ligne)
            if m and matiere:
                libelles[matiere] = m.group(1)

    attendu = {}
    for jeu in jeux:
        if jeu.get("paquet") not in paquets:
            raise ValueError("jeu %s : paquet %s introuvable sous contenu/" % (jeu["id"], jeu.get("paquet")))
        niveau, matiere = paquets[jeu["paquet"]]
        n = attendu.setdefault(niveau, {"ids": [], "matieres": []})
        n["ids"].append(jeu["id"])
        if matiere not in n["matieres"]:
            n["matieres"].append(matiere)
    for n in attendu.values():
        rang = {m: i for i, m in enumerate(ordre)}
        n["matieres"].sort(key=lambda m: rang.get(m, len(rang)))
    return attendu, libelles


def matieres_nommees(textes, matieres, libelles):
    """Nombre de matieres du niveau dont le nom (valeur du descripteur ou libelle court) est le texte
    entier d'un element visible. Un titre de jeu qui contient le mot ne compte pas."""
    textes = set(textes)
    return sum(1 for m in matieres if m in textes or libelles.get(m) in textes)


# ---------------------------------------------------------------------------------------------------
# Enveloppe : la seule partie qui touche le poste (navigateur, reseau). Non couverte par la CI.
# ---------------------------------------------------------------------------------------------------


class Ws:
    """Client WebSocket minimal (RFC 6455), texte seulement, sans dependance."""

    def __init__(self, hote, port, chemin):
        self.s = socket.create_connection((hote, port), timeout=30)
        cle = base64.b64encode(os.urandom(16)).decode()
        self.s.sendall(
            (
                "GET %s HTTP/1.1\r\nHost: %s:%d\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n"
                "Sec-WebSocket-Key: %s\r\nSec-WebSocket-Version: 13\r\n\r\n" % (chemin, hote, port, cle)
            ).encode()
        )
        reponse = b""
        while b"\r\n\r\n" not in reponse:
            morceau = self.s.recv(4096)
            if not morceau:
                raise RuntimeError("poignee de main WebSocket interrompue")
            reponse += morceau
        entete, _, reste = reponse.partition(b"\r\n\r\n")
        if b" 101 " not in entete.split(b"\r\n")[0]:
            raise RuntimeError("poignee de main refusee : %r" % entete.split(b"\r\n")[0])
        attendu = base64.b64encode(
            hashlib.sha1((cle + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").encode()).digest()
        )
        if attendu not in entete:
            raise RuntimeError("Sec-WebSocket-Accept inattendu")
        self.tampon = reste

    def _lire(self, n):
        while len(self.tampon) < n:
            morceau = self.s.recv(65536)
            if not morceau:
                raise RuntimeError("connexion fermee")
            self.tampon += morceau
        donnees, self.tampon = self.tampon[:n], self.tampon[n:]
        return donnees

    def envoyer(self, texte):
        charge = texte.encode()
        entete = bytearray([0x81])
        n = len(charge)
        if n < 126:
            entete.append(0x80 | n)
        elif n < 65536:
            entete.append(0x80 | 126)
            entete += struct.pack(">H", n)
        else:
            entete.append(0x80 | 127)
            entete += struct.pack(">Q", n)
        masque = os.urandom(4)
        entete += masque
        corps = bytes(b ^ masque[i % 4] for i, b in enumerate(charge))
        self.s.sendall(bytes(entete) + corps)

    def recevoir(self):
        message = b""
        while True:
            b0, b1 = self._lire(2)
            n = b1 & 0x7F
            if n == 126:
                n = struct.unpack(">H", self._lire(2))[0]
            elif n == 127:
                n = struct.unpack(">Q", self._lire(8))[0]
            donnees = self._lire(n)
            opcode = b0 & 0x0F
            if opcode == 0x8:
                raise RuntimeError("connexion fermee par le navigateur")
            if opcode in (0x9, 0xA):
                continue
            message += donnees
            if b0 & 0x80:
                return message.decode()


class Cdp:
    def __init__(self, ws):
        self.ws = ws
        self.n = 0

    def appeler(self, methode, **params):
        self.n += 1
        mon_id = self.n
        self.ws.envoyer(json.dumps({"id": mon_id, "method": methode, "params": params}))
        while True:
            reponse = json.loads(self.ws.recevoir())
            if reponse.get("id") == mon_id:
                if "error" in reponse:
                    raise RuntimeError("%s : %s" % (methode, reponse["error"]))
                return reponse.get("result", {})

    def evaluer(self, expression):
        r = self.appeler("Runtime.evaluate", expression=expression, returnByValue=True, awaitPromise=True)
        if "exceptionDetails" in r:
            raise RuntimeError("exception JS : %s" % r["exceptionDetails"])
        return r["result"].get("value")

    def attendre(self, predicat_js, delai=15.0):
        fin = time.time() + delai
        while time.time() < fin:
            try:
                if self.evaluer(predicat_js):
                    return
            except RuntimeError:
                pass  # contexte en cours de remplacement pendant une navigation
            time.sleep(0.1)
        raise RuntimeError("delai depasse en attendant : %s" % predicat_js)


JS_RELEVE = """(() => {
  const r = e => { const b = e.getBoundingClientRect();
    return {top: b.top, bottom: b.bottom, left: b.left, right: b.right, height: b.height, width: b.width}; };
  const visible = e => { const b = e.getBoundingClientRect(), s = getComputedStyle(e);
    return b.width > 0 && b.height > 0 && s.visibility !== 'hidden' && s.display !== 'none'; };
  const jeu = document.querySelector('#jeu');
  const cartes = [...jeu.querySelectorAll('button.demarrer')].map(e =>
    Object.assign({id: (e.getAttribute('hx-post') || '').split('/').pop().split(';')[0],
      urlAvecJsessionid: /jsessionid/i.test(e.getAttribute('hx-post') || '')}, r(e)));
  const bandeaux = [...jeu.querySelectorAll('.entete-matiere')].map(e => {
    const img = e.querySelector('img');
    const groupe = e.closest('.bloc-matiere');
    return Object.assign({texte: e.textContent.trim(), image: !!img,
      imageChargee: !!img && img.complete && img.naturalWidth > 0,
      position: getComputedStyle(e).position,
      cartes: groupe ? groupe.querySelectorAll('button.demarrer').length : 0}, r(e)); });
  const tuiles = [...jeu.querySelectorAll('a.tuile')].map(e => {
    const img = e.querySelector('img');
    return Object.assign({texte: e.textContent.trim(), href: e.getAttribute('href'), image: !!img,
      imageChargee: !!img && img.complete && img.naturalWidth > 0}, r(e)); });
  const bascule = [...jeu.querySelectorAll('button.bascule')].map(e => e.textContent.trim());
  const lesJeux = [...document.querySelectorAll('a, button')]
    .filter(e => e.textContent.trim() === 'Les jeux' && visible(e)).length;
  const textes = [...jeu.querySelectorAll('*')]
    .filter(e => e.children.length === 0 && visible(e) && !e.closest('button.demarrer'))
    .map(e => e.textContent.trim()).filter(t => t);
  return JSON.stringify({W: innerWidth, H: innerHeight, docH: document.documentElement.scrollHeight,
    docW: document.documentElement.scrollWidth, scrollY: scrollY, path: location.pathname,
    cartes, bandeaux, tuiles, bascule, lesJeux, textes});
})()"""


def trouver_chrome():
    designe = os.environ.get("CHROME")
    if designe:
        return designe
    for nom in ("chrome", "google-chrome", "chromium"):
        trouve = shutil.which(nom)
        if trouve:
            return trouve
    return None


def lancer_chrome(chrome, profil):
    proc = subprocess.Popen(
        [
            chrome, "--headless=new", "--disable-gpu", "--no-first-run", "--no-default-browser-check",
            "--hide-scrollbars", "--remote-debugging-port=0", "--user-data-dir=" + str(profil), "about:blank",
        ],
        stdout=subprocess.DEVNULL,
        stderr=subprocess.DEVNULL,
    )
    fichier = Path(profil) / "DevToolsActivePort"
    fin = time.time() + 30
    while time.time() < fin:
        if fichier.exists() and fichier.read_text().strip():
            return proc, int(fichier.read_text().splitlines()[0])
        if proc.poll() is not None:
            raise RuntimeError("le navigateur s'est arrete au demarrage (code %s)" % proc.returncode)
        time.sleep(0.1)
    proc.kill()
    raise RuntimeError("le navigateur n'a pas ouvert son port de debogage")


def ouvrir_page(port):
    requete = urllib.request.Request("http://127.0.0.1:%d/json/new?about:blank" % port, method="PUT")
    cible = json.load(urllib.request.urlopen(requete, timeout=30))
    m = re.match(r"ws://([^:/]+):(\d+)(/.*)$", cible["webSocketDebuggerUrl"])
    return Cdp(Ws(m.group(1), int(m.group(2)), m.group(3)))


def releve(cdp):
    return json.loads(cdp.evaluer(JS_RELEVE))


def ecran(cdp):
    """Releve + indicateurs derives, prets pour les juges purs."""
    # Bootstrap pose scroll-behavior: smooth : sans retour instantane en haut, la lecture peut tomber
    # pendant un defilement et decaler toutes les cotes.
    cdp.evaluer("window.scrollTo({top: 0, behavior: 'instant'}); true")
    cdp.attendre("scrollY === 0")
    r = releve(cdp)
    r["visibles"] = compter_visibles(r["cartes"], r["W"], r["H"])
    r["colonnes"] = colonnes(r["cartes"])
    r["hauteurMinCarte"] = min((c["height"] for c in r["cartes"]), default=None)
    r["defilement"] = r["docH"] > r["H"]
    r["debordeEnLargeur"] = r["docW"] > r["W"]
    r["urlsAvecJsessionid"] = sum(1 for c in r["cartes"] if c["urlAvecJsessionid"]) + sum(
        1 for t in r["tuiles"] if "jsessionid" in (t["href"] or "").lower()
    )
    return r


def mesurer_niveau(cdp, url, niveau):
    cdp.appeler("Network.clearBrowserCookies")
    cdp.appeler("Page.navigate", url="about:blank")
    cdp.appeler("Page.navigate", url="%s/niveaux/%s" % (url, niveau))
    cdp.attendre("document.readyState === 'complete' && location.pathname === '/niveaux/%s' && !!document.querySelector('#jeu')" % niveau)
    cdp.evaluer("document.fonts.ready.then(() => true)")
    sortie = {"niveau": niveau, "v2": ecran(cdp), "etage1": None, "etages2": {}}

    v2 = sortie["v2"]
    if len(v2["bandeaux"]) >= 3:
        # Bandeau colle : la premiere carte du troisieme groupe au milieu de la fenetre.
        cdp.evaluer(
            """(() => { const g = document.querySelectorAll('.bloc-matiere')[2];
            const c = g.querySelector('button.demarrer').getBoundingClientRect();
            window.scrollTo({top: scrollY + c.top + c.height / 2 - innerHeight / 2, behavior: 'instant'}); return true; })()"""
        )
        time.sleep(0.3)
        r = releve(cdp)
        sortie["collant"] = {
            "bandeauDuTroisieme": r["bandeaux"][2],
            "bandeauxAuBordHaut": sum(1 for b in r["bandeaux"] if abs(b["top"]) < 1),
            "hauteurFenetre": r["H"],
        }
        cdp.evaluer("window.scrollTo({top: 0, behavior: 'instant'}); true")

    if not v2["bascule"]:
        return sortie

    cdp.evaluer("document.querySelector('#jeu button.bascule').click(); true")
    cdp.attendre("!!document.querySelector('#jeu .matieres')")
    sortie["etage1"] = ecran(cdp)
    segments = [t["href"] for t in sortie["etage1"]["tuiles"]]
    sortie["hrefsAvecJsessionid"] = sum(1 for h in segments if "jsessionid" in h.lower())
    for href in segments:
        cdp.evaluer(
            "document.querySelector('#jeu a.tuile[href=\"%s\"]').click(); true" % href
        )
        cdp.attendre(
            "location.pathname === '%s' && document.readyState === 'complete' && !!document.querySelector('#jeu .bloc-matiere')"
            % href.split(";")[0]
        )
        sortie["etages2"][href.split(";")[0]] = ecran(cdp)
        cdp.evaluer("document.querySelector('#jeu button.bascule').click(); true")
        cdp.attendre("!!document.querySelector('#jeu .matieres')")
    cdp.evaluer("document.querySelector('#jeu button.bascule').click(); true")
    cdp.attendre("!!document.querySelector('#jeu .bloc-matiere') && !document.querySelector('#jeu .matieres')")
    return sortie


def resume(m, attendu, libelles):
    lignes = []
    v2 = m["v2"]
    nommees = matieres_nommees(v2["textes"], attendu["matieres"], libelles)
    lignes.append(
        "%s vue par defaut : %d carte(s) au total, %d entierement visible(s), %d bandeau(x), %d matiere(s) nommee(s) "
        "sur %d, document %d px pour %d, defilement %s, debordement horizontal %s, 'Les jeux' visible %d fois, %d URL(s) portant jsessionid"
        % (m["niveau"], len(v2["cartes"]), v2["visibles"], len(v2["bandeaux"]), nommees, len(attendu["matieres"]),
           round(v2["docH"]), v2["H"], "oui" if v2["defilement"] else "non",
           "oui" if v2["debordeEnLargeur"] else "non", v2["lesJeux"], v2["urlsAvecJsessionid"])
    )
    if "collant" in m:
        c = m["collant"]["bandeauDuTroisieme"]
        lignes.append(
            "%s bandeau colle (3e groupe, sa 1re carte au milieu) : top %.0f bottom %.0f position %s, %d bandeau(x) a top 0"
            % (m["niveau"], c["top"], c["bottom"], c["position"], m["collant"]["bandeauxAuBordHaut"])
        )
    if m["etage1"] is not None:
        e1 = m["etage1"]
        entieres = sum(1 for t in e1["tuiles"] if carte_entierement_visible(t, e1["W"], e1["H"]))
        lignes.append(
            "%s V1 etage 1 : %d tuile(s), %d entierement visible(s), document %d px, defilement %s, bouton %s, 'Les jeux' visible %d fois"
            % (m["niveau"], len(e1["tuiles"]), entieres, round(e1["docH"]), "oui" if e1["defilement"] else "non",
               e1["bascule"], e1["lesJeux"])
        )
        total = 0
        for href, e2 in m["etages2"].items():
            total += len(e2["cartes"])
            lignes.append(
                "%s V1 etage 2 %s : %d carte(s), %d entierement visible(s), %d colonne(s), document %d px, defilement %s, "
                "'Les jeux' visible %d fois"
                % (m["niveau"], href, len(e2["cartes"]), e2["visibles"], e2["colonnes"], round(e2["docH"]),
                   "oui" if e2["defilement"] else "non", e2["lesJeux"])
            )
        lignes.append("%s V1 somme des etages 2 : %d carte(s) pour %d jeu(x) du catalogue" % (m["niveau"], total, len(attendu["ids"])))
    return lignes


def principal(argv):
    p = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    p.add_argument("--url", required=True)
    p.add_argument("--largeur", type=int, default=744)
    p.add_argument("--hauteur", type=int, default=551)
    p.add_argument("--ressources", default="adapters/adapter-content/src/main/resources")
    p.add_argument("--niveaux", nargs="*")
    p.add_argument("--seuil-v2", type=int)
    p.add_argument("--seuil-etage2", type=int)
    p.add_argument("--json", action="store_true", help="ecrit les releves bruts sur la sortie standard")
    a = p.parse_args(argv)

    motifs = juger_invocation(a.largeur, a.hauteur, a.url)
    if motifs:
        for m in motifs:
            print(m, file=sys.stderr)
        return 2
    chrome = trouver_chrome()
    if not chrome:
        print("aucun navigateur trouve : mets un Chrome sur le PATH ou designe-le par la variable CHROME.", file=sys.stderr)
        return 2
    try:
        urllib.request.urlopen(a.url + "/", timeout=10).read(1)
    except Exception as e:  # noqa: BLE001 - tout echec de connexion est un refus de mesurer
        print("le serveur ne repond pas sur l'url donnee : %s" % e, file=sys.stderr)
        return 2

    attendu_tous, libelles = lire_attendu(a.ressources)
    niveaux = a.niveaux or sorted(attendu_tous)

    profil = tempfile.mkdtemp(prefix="mesurer-accueil-")
    proc = None
    ecarts = []
    releves = []
    try:
        proc, port = lancer_chrome(chrome, profil)
        cdp = ouvrir_page(port)
        cdp.appeler("Page.enable")
        cdp.appeler("Network.enable")
        cdp.appeler(
            "Emulation.setDeviceMetricsOverride", width=a.largeur, height=a.hauteur, deviceScaleFactor=1, mobile=False
        )
        print(
            "mesure : fenetre emulee %d x %d, facteur 1, barres de defilement masquees, serveur %s, "
            "%d jeu(x) au catalogue lu sur disque (%s)"
            % (a.largeur, a.hauteur, a.url, sum(len(v["ids"]) for v in attendu_tous.values()),
               ", ".join("%s %d" % (n, len(attendu_tous[n]["ids"])) for n in sorted(attendu_tous)))
        )
        for niveau in niveaux:
            attendu = attendu_tous[niveau]
            m = mesurer_niveau(cdp, a.url, niveau)
            releves.append(m)
            for ligne in resume(m, attendu, libelles):
                print(ligne)
            ecarts += ["%s : %s" % (niveau, e) for e in juger_completude(attendu, m)]
            ecarts += ["%s : %s" % (niveau, e) for e in juger_seuils(m, a.largeur, a.hauteur, a.seuil_v2, a.seuil_etage2)]
        try:
            cdp.appeler("Browser.close")
        except Exception:  # noqa: BLE001 - la fermeture coupe la connexion
            pass
    finally:
        if proc is not None:
            try:
                proc.wait(timeout=10)
            except subprocess.TimeoutExpired:
                proc.kill()  # par le PID de ce Popen, jamais par nom d'image
        shutil.rmtree(profil, ignore_errors=True)

    if a.json:
        print(json.dumps(releves))
    for e in ecarts:
        print("ECART " + e)
    print("%d ecart(s)." % len(ecarts))
    return 1 if ecarts else 0


if __name__ == "__main__":
    sys.exit(principal(sys.argv[1:]))
