#!/usr/bin/env python
"""Juge pur du releve de l'ecran a deux oeuvres (ART-CP-10) : les regles J1 a J5 de
squad/sprints/s57-version-cp-ce1-cm1-trois-instances/avis-theo-poste-8-art-cp-10.md, section 4.2.

Ce que ce script juge : des cotes, lues dans le JSON que squad/conformite/releves-s57/pilote-poste-8.mjs
ecrit (largeur -> item -> etat -> mesure). Il ne juge ni la coupure du verdict, ni la taille rendue des
reproductions, ni le decalage lateral des cases (avis, 4.2, dernier paragraphe), ni la pedagogie : le verdict
pedagogique reste a nadia-prof.

Regles, telles que l'avis les definit :
  J1  completude : hauteur 551 sur toute mesure ; largeurs 744 et 992 presentes ; a chaque largeur autant
      d'items que le paquet en porte (compte lu sur le YAML du paquet, jamais ecrit ici), chacun avec
      fraiche, echec1 et revelation. Un releve incomplet sort 1.
  J2  barre du refus : a 744, revelation, bas de m4 au plus 551 sur chaque item.
  J3  RDO-CP-R1, imprime a part de J2 (un echec de J3 seul est une reserve, pas un refus) : a 744, bas de
      indice au plus 551 a chaque echec ; a 992, bas de chaque marque au plus 551 a chaque echec.
  J4  veto de nadia-prof : a chaque largeur de 720 et plus, la boite de messageJeu commence sous le bas de
      cabinet et tient entre son bord gauche et son bord droit, a 1 px pres.
  J5  C1' et N6 : par item, haut, largeur et hauteur des cases identiques dans tous les etats ; haut, gauche et
      largeur de cabinet identiques dans tous les etats ; hauteur de cabinet identique entre deux etats qui ont
      tous deux au moins une case essayee, ou tous deux aucune.

Conventions du juge, la ou l'avis ne dit rien : une cote absente d'un etat qu'une regle doit lire est un
echec (un releve a trous ne passe pas vert, comme J1 le pose) ; "au plus 551" est une comparaison large ;
"a 1 px pres" vaut pour les trois bords de J4 ; "identiques" (J5) est une egalite a 0,01 px, les cotes etant
relevees a 0,1 px.

Sortie 0 : toutes les regles passent. Sortie 1 : une regle echoue ou le releve est incomplet. Sortie 2 :
invocation invalide (fichier illisible, JSON invalide, paquet illisible).

Ce script n'est pas un garde-fou de CI sur un releve : il ne lit que le fichier qu'on lui donne, pas le jar.
Ce que la CI execute, c'est son test (outils/juger-pli-deux-oeuvres-test.py), eprouve par des entrees
fabriquees (ADR-0009).

Usage :
    python outils/juger-pli-deux-oeuvres.py <releve.json> [--paquet <cp-rapprocher-deux-oeuvres.yaml>]
"""

import argparse
import json
import re
import sys
from pathlib import Path

HAUTEUR_FENETRE = 551
LARGEURS_REQUISES = (744, 992)
LARGEUR_GRILLE = 720
ETATS_REQUIS = ("fraiche", "echec1", "revelation")
ETATS_ECHEC = ("echec1", "echec2")
TOLERANCE_J4 = 1.0
TOLERANCE_J5 = 0.01

PAQUET_PAR_DEFAUT = (
    Path(__file__).resolve().parent.parent
    / "adapters/adapter-content/src/main/resources/contenu/cp-rapprocher-deux-oeuvres.yaml"
)


class Verdict:
    def __init__(self, code, titre, ok, resume, lignes=None, reserve=False):
        self.code = code
        self.titre = titre
        self.ok = ok
        self.resume = resume
        self.lignes = lignes or []
        self.reserve = reserve


def compter_items_paquet(texte):
    """Nombre d'entrees de la liste items: d'un paquet YAML (lecture par lignes, sans dependance)."""
    dans_items = False
    n = 0
    for ligne in texte.splitlines():
        if re.match(r"^items:\s*$", ligne):
            dans_items = True
            continue
        if dans_items:
            if re.match(r"^\S", ligne) and not ligne.startswith("#"):
                break
            if re.match(r"^  - ", ligne):
                n += 1
    if n == 0:
        raise ValueError("aucun item lu dans la liste items: du paquet")
    return n


def _est_etat(valeur):
    return isinstance(valeur, dict) and isinstance(valeur.get("cases"), list)


def _etats(item):
    return {nom: m for nom, m in item.items() if _est_etat(m)}


def _largeurs(releve):
    """Largeurs presentes, dans l'ordre croissant : liste de (largeur, items)."""
    return sorted(((int(w), items) for w, items in releve.items()), key=lambda t: t[0])


def _forme_valide(releve):
    if not isinstance(releve, dict) or not releve:
        return False
    try:
        for w, items in releve.items():
            int(w)
            if not isinstance(items, dict):
                return False
            if not all(isinstance(it, dict) for it in items.values()):
                return False
    except (TypeError, ValueError):
        return False
    return True


def _cote(mesure, boite, cle):
    if mesure is None:
        return None
    b = mesure.get(boite)
    if not isinstance(b, dict):
        return None
    v = b.get(cle)
    return v if isinstance(v, (int, float)) else None


def _num(v):
    return ("%g" % v) if v is not None else "absente"


def _non_juge(code, titre, motif):
    return Verdict(code, titre, False, "non juge : " + motif)


def juger_j1(releve, nb_items):
    titre = "completude"
    if not _forme_valide(releve):
        return Verdict(
            "J1", titre, False,
            "le releve n'a pas la forme de la sortie de pilote-poste-8.mjs (objet largeur -> item -> etat)",
        )
    lignes = []
    largeurs = _largeurs(releve)
    presentes = [w for w, _ in largeurs]
    for requise in LARGEURS_REQUISES:
        if requise not in presentes:
            lignes.append("largeur %d absente (largeurs presentes : %s)" % (requise, ", ".join(map(str, presentes))))
    mesures = 0
    for w, items in largeurs:
        if len(items) != nb_items:
            lignes.append("%d : %d items releves, le paquet en porte %d" % (w, len(items), nb_items))
        for src, item in items.items():
            etats = _etats(item)
            for requis in ETATS_REQUIS:
                if requis not in etats:
                    lignes.append("%d %s : etat %s absent" % (w, src, requis))
            for nom, m in etats.items():
                mesures += 1
                if m.get("innerH") != HAUTEUR_FENETRE:
                    lignes.append(
                        "%d %s %s : hauteur de fenetre %s, %d attendue" % (w, src, nom, _num(m.get("innerH")), HAUTEUR_FENETRE)
                    )
    resume = "largeurs %s, %d items par largeur (paquet : %d), hauteur %d sur %d mesures" % (
        ", ".join(map(str, presentes)), nb_items, nb_items, HAUTEUR_FENETRE, mesures,
    )
    return Verdict("J1", titre, not lignes, resume, lignes)


def _items_a(releve, largeur):
    if not _forme_valide(releve):
        return None
    for w, items in releve.items():
        if int(w) == largeur:
            return items
    return None


def juger_j2(releve):
    titre = "barre du refus (744, revelation, bas de m4 <= %d)" % HAUTEUR_FENETRE
    if not _forme_valide(releve):
        return _non_juge("J2", titre, "releve sans la forme attendue")
    items = _items_a(releve, 744)
    if items is None:
        return _non_juge("J2", titre, "largeur 744 absente")
    lignes, bas = [], []
    for src, item in items.items():
        b = _cote(item.get("revelation"), "m4", "bottom")
        if b is None:
            lignes.append("744 %s revelation : bas de m4 absent" % src)
        else:
            bas.append(b)
            if b > HAUTEUR_FENETRE:
                lignes.append("744 %s revelation : bas de m4 = %s > %d" % (src, _num(b), HAUTEUR_FENETRE))
    resume = "%d items, bas de m4 max %s" % (len(items), _num(max(bas)) if bas else "absent")
    return Verdict("J2", titre, not lignes, resume, lignes)


def juger_j3(releve):
    titre = "RDO-CP-R1 (a part de J2 : reserve, pas refus) (744 indice, 992 marques, bas <= %d)" % HAUTEUR_FENETRE
    if not _forme_valide(releve):
        return _non_juge("J3", titre, "releve sans la forme attendue")
    lignes, indices, marques = [], [], []
    i744, i992 = _items_a(releve, 744), _items_a(releve, 992)
    if i744 is None or i992 is None:
        manque = " et ".join(str(w) for w, i in ((744, i744), (992, i992)) if i is None)
        return _non_juge("J3", titre, "largeur %s absente" % manque)
    for src, item in i744.items():
        for nom in ETATS_ECHEC:
            if nom not in item:
                continue
            b = _cote(item[nom], "indice", "bottom")
            if b is None:
                lignes.append("744 %s %s : bas de indice absent" % (src, nom))
                continue
            indices.append(b)
            if b > HAUTEUR_FENETRE:
                lignes.append("744 %s %s : bas de indice = %s > %d" % (src, nom, _num(b), HAUTEUR_FENETRE))
    for src, item in i992.items():
        for nom in ETATS_ECHEC:
            if nom not in item:
                continue
            liste = item[nom].get("marques") if isinstance(item[nom], dict) else None
            bas = [m.get("bottom") for m in liste or [] if isinstance(m, dict) and isinstance(m.get("bottom"), (int, float))]
            if not bas:
                lignes.append("992 %s %s : aucune marque relevee" % (src, nom))
                continue
            marques.extend(bas)
            for b in bas:
                if b > HAUTEUR_FENETRE:
                    lignes.append("992 %s %s : bas de marque = %s > %d" % (src, nom, _num(b), HAUTEUR_FENETRE))
    resume = "744 bas de indice max %s (%d mesures), 992 bas de marque max %s (%d mesures)" % (
        _num(max(indices)) if indices else "absent", len(indices),
        _num(max(marques)) if marques else "absent", len(marques),
    )
    return Verdict("J3", titre, not lignes, resume, lignes, reserve=True)


def juger_j4(releve):
    titre = "veto de nadia-prof (messageJeu sous cabinet et dans ses bords, a %g px pres, des %d)" % (
        TOLERANCE_J4, LARGEUR_GRILLE)
    if not _forme_valide(releve):
        return _non_juge("J4", titre, "releve sans la forme attendue")
    lignes = []
    mesures = 0
    marge_haut = marge_gauche = marge_droite = None
    for w, items in _largeurs(releve):
        if w < LARGEUR_GRILLE:
            continue
        for src, item in items.items():
            for nom, m in _etats(item).items():
                if nom == "fraiche":
                    continue
                msg, cab = m.get("messageJeu"), m.get("cabinet")
                if not isinstance(msg, dict) or not isinstance(cab, dict):
                    lignes.append("%d %s %s : messageJeu ou cabinet absent" % (w, src, nom))
                    continue
                mesures += 1
                haut = msg["top"] - cab["bottom"]
                gauche = msg["left"] - cab["left"]
                droite = cab["right"] - msg["right"]
                marge_haut = haut if marge_haut is None else min(marge_haut, haut)
                marge_gauche = gauche if marge_gauche is None else min(marge_gauche, gauche)
                marge_droite = droite if marge_droite is None else min(marge_droite, droite)
                if haut < -TOLERANCE_J4:
                    lignes.append(
                        "%d %s %s : messageJeu commence a %s, cabinet finit a %s" % (w, src, nom, _num(msg["top"]), _num(cab["bottom"]))
                    )
                if gauche < -TOLERANCE_J4:
                    lignes.append(
                        "%d %s %s : bord gauche de messageJeu %s, de cabinet %s" % (w, src, nom, _num(msg["left"]), _num(cab["left"]))
                    )
                if droite < -TOLERANCE_J4:
                    lignes.append(
                        "%d %s %s : bord droit de messageJeu %s, de cabinet %s" % (w, src, nom, _num(msg["right"]), _num(cab["right"]))
                    )
    if mesures == 0 and not lignes:
        return _non_juge("J4", titre, "aucune mesure avec messageJeu a %d ou plus" % LARGEUR_GRILLE)
    resume = "%d mesures, marge min : sous cabinet %s, a gauche %s, a droite %s" % (
        mesures, _num(marge_haut), _num(marge_gauche), _num(marge_droite),
    )
    return Verdict("J4", titre, not lignes, resume, lignes)


def _essayees(mesure):
    return sum(1 for c in mesure["cases"] if "deja-essayee" in str(c.get("cls", "")).split())


def juger_j5(releve):
    titre = "C1' et N6 (cases : haut, largeur, hauteur ; cabinet : haut, gauche, largeur, hauteur par groupe)"
    if not _forme_valide(releve):
        return _non_juge("J5", titre, "releve sans la forme attendue")
    ecarts = {}  # (largeur, critere, valeur de reference, valeur divergente) -> (mesures, items)
    comparees = 0

    def noter(w, src, critere, ref, val):
        cle = (w, critere, ref, val)
        mesures, items = ecarts.get(cle, (0, set()))
        items.add(src)
        ecarts[cle] = (mesures + 1, items)

    for w, items in _largeurs(releve):
        for src, item in items.items():
            etats = _etats(item)
            if not etats:
                continue
            noms = list(etats)
            nom_ref = "fraiche" if "fraiche" in etats else noms[0]
            ref = etats[nom_ref]
            groupes = {}
            for nom in noms:
                m = etats[nom]
                comparees += 1
                if len(m["cases"]) != len(ref["cases"]):
                    noter(w, src, "cases.nombre", len(ref["cases"]), len(m["cases"]))
                else:
                    for cr, cm in zip(ref["cases"], m["cases"]):
                        for crit, cle in (("cases.haut", "top"), ("cases.largeur", "w"), ("cases.hauteur", "h")):
                            a, b = (cr.get("box") or {}).get(cle), (cm.get("box") or {}).get(cle)
                            if a is None or b is None or abs(a - b) > TOLERANCE_J5:
                                noter(w, src, crit, _num(a), _num(b))
                for crit, cle in (("cabinet.haut", "top"), ("cabinet.gauche", "left"), ("cabinet.largeur", "w")):
                    a, b = _cote(ref, "cabinet", cle), _cote(m, "cabinet", cle)
                    if a is None or b is None or abs(a - b) > TOLERANCE_J5:
                        noter(w, src, crit, _num(a), _num(b))
                groupes.setdefault(_essayees(m) > 0, []).append((nom, m))
            for membres in groupes.values():
                ref_g = _cote(membres[0][1], "cabinet", "h")
                for nom, m in membres[1:]:
                    h = _cote(m, "cabinet", "h")
                    if ref_g is None or h is None or abs(ref_g - h) > TOLERANCE_J5:
                        noter(w, src, "cabinet.hauteur", _num(ref_g), _num(h))
    lignes = []
    for (w, critere, ref, val), (mesures, items) in sorted(ecarts.items()):
        lignes.append("%d %s : %s puis %s (%d mesures, %d items)" % (w, critere, ref, val, mesures, len(items)))
    resume = "%d mesures comparees a leur etat de reference" % comparees
    return Verdict("J5", titre, not lignes, resume, lignes)


def juger(releve, nb_items):
    return [
        juger_j1(releve, nb_items),
        juger_j2(releve),
        juger_j3(releve),
        juger_j4(releve),
        juger_j5(releve),
    ]


def formater(verdicts):
    sortie = []
    for v in verdicts:
        etat = "OK" if v.ok else ("ROUGE (reserve)" if v.reserve else "ROUGE")
        sortie.append("%s %s - %s : %s" % (v.code, etat, v.titre, v.resume))
        for ligne in v.lignes:
            sortie.append("    - " + ligne)
    echecs = [v.code for v in verdicts if not v.ok]
    sortie.append("VERDICT DES COTES : " + ("tout passe" if not echecs else "rouge sur " + ", ".join(echecs)))
    return "\n".join(sortie)


def main(argv=None):
    parseur = argparse.ArgumentParser(description="Juge pur du releve de l'ecran a deux oeuvres (J1 a J5).")
    parseur.add_argument("releve")
    parseur.add_argument("--paquet", default=str(PAQUET_PAR_DEFAUT))
    args = parseur.parse_args(argv)
    try:
        releve = json.loads(Path(args.releve).read_text(encoding="utf-8"))
    except (OSError, ValueError) as e:
        print("releve illisible : %s" % e, file=sys.stderr)
        return 2
    try:
        nb_items = compter_items_paquet(Path(args.paquet).read_text(encoding="utf-8"))
    except (OSError, ValueError) as e:
        print("paquet illisible : %s" % e, file=sys.stderr)
        return 2
    verdicts = juger(releve, nb_items)
    print(formater(verdicts))
    return 0 if all(v.ok for v in verdicts) else 1


if __name__ == "__main__":
    sys.exit(main())
