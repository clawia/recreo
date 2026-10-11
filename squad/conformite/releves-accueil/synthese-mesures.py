"""Lit mesures-accueil.json (produit par pilote-accueil.mjs) et imprime les tableaux de la proposition.

Usage : python -I synthese-mesures.py mesures-accueil.json
"""
import json
import sys

d = json.load(open(sys.argv[1], encoding="utf-8"))
P = d["pages"]
W = [str(w) for w in d["largeurs"]]
NIV = ["CP", "CE1", "CM1"]


def g(cle, w):
    return P[cle][w]


print("== A. Cartes ou tuiles visibles sans defiler (pli = %d) ==" % d["pli"])
print("vue | niveau | largeur | total | entieres | au moins en partie | hauteur du document | ecrans de defilement")
for w in W:
    for n in NIV + ["tous"]:
        for cle, lib, champ in (("maquette:actuelle-" + n, "actuelle", "Cartes"), ("maquette:v1-etage1-" + n, "V1 etage 1", "Tuiles"),
                                ("maquette:v2-" + n, "V2", "Cartes")):
            m = g(cle, w)
            tot, ent, par = m["n" + champ], m[champ.lower() + "Entieres"], m[champ.lower() + "AuMoinsEnPartie"]
            print(f"{lib} | {n} | {w} | {tot} | {ent} | {par} | {m['docH']} | {m['docH'] / d['pli']:.1f}")

print("\n== B. V1 etage 2 : cartes d'une matiere, 744 ==")
for n in NIV:
    for cle in sorted(k for k in P if k.startswith("maquette:v1-etage2-" + n + "-")):
        for w in W:
            m = g(cle, w)
            print(f"{cle.split('-',2)[2]} | {w} | total {m['nCartes']} | entieres {m['cartesEntieres']} | en partie {m['cartesAuMoinsEnPartie']} | doc {m['docH']}")

print("\n== C. Jar : liste servie ==")
for cle in ("jar:/", "jar:/niveaux/CP", "jar:/niveaux/CE1", "jar:/niveaux/CM1"):
    for w in W:
        m = g(cle, w)
        print(f"{cle} | {w} | total {m['nCartes']} | entieres {m['cartesEntieres']} | en partie {m['cartesAuMoinsEnPartie']} | doc {m['docH']} | 1re carte {m['premiereCarteHaut']} | hauteurs {m['hauteursCartes']}")

print("\n== D. Parite maquette / jar (liste actuelle) ==")
for n, cle in (("tous", "jar:/"), ("CP", "jar:/niveaux/CP"), ("CE1", "jar:/niveaux/CE1"), ("CM1", "jar:/niveaux/CM1")):
    for w in W:
        a, b = g("maquette:actuelle-" + n, w), g(cle, w)
        print(n, w, "maquette doc", a["docH"], "jar doc", b["docH"], "| 1re carte", a["premiereCarteHaut"], b["premiereCarteHaut"],
              "| entieres", a["cartesEntieres"], b["cartesEntieres"])

print("\n== E. Cibles ==")
for n in NIV:
    for cle in ("maquette:v1-etage1-" + n, "maquette:v2-" + n, "maquette:v1-etage2-" + n + "-francais"):
        if cle not in P:
            continue
        for w in W:
            m = g(cle, w)
            print(cle, w, {k: (v["n"], v["minW"], v["minH"]) for k, v in m["parGroupe"].items()},
                  "ecart min", m["ecartMinEntreCibles"], "chevauchees", m["cibleschevauchees"], "coupes", m["coupes"], "texte dans le rembourrage", m["intrusions"],
                  "debordement", m["scrollW"] > m["clientW"])

print("\n== F. Bandeau colle ==")
for n in NIV:
    for w in W:
        m = g("maquette:v2-" + n, w)
        print(n, w, "hauteur bandeau", m["bandeauHaut"], "bandeaux visibles sans defiler", m["bandeauxVisibles"], "colle", m.get("colle"))

print("\n== G. Hauteur d'en-tete et position de la 1re ligne de contenu ==")
for w in W:
    for cle in ("maquette:v2-CP", "maquette:v1-etage1-CP", "jar:/niveaux/CP"):
        m = g(cle, w)
        print(w, cle, "entete bas", m["entete"]["b"], "barre", m["barre"] and (m["barre"]["t"], m["barre"]["b"]), "filtre", m["filtre"] and (m["filtre"]["t"], m["filtre"]["b"]))

print("\n== H. Contrastes (maquette V2 et V1 etage 1, CP, 744) ==")
for cle in ("maquette:v2-CP", "maquette:v1-etage1-CP"):
    print(cle, json.dumps(g(cle, "744")["couleurs"], ensure_ascii=False))

print("\n== I. Libelles complets de matiere dans les tuiles (CM1) ==")
for w in W:
    for cle in ("maquette:v1-etage1-CM1", "maquette:v1-etage1-CM1-libelles-longs"):
        m = g(cle, w)
        print(cle, w, "tuiles coupees", m["coupes"], "texte dans le rembourrage", m["intrusions"], "hauteur du document", m["docH"], "entieres", m["tuilesEntieres"])
