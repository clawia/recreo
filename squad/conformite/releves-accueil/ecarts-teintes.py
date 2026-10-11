"""Ecarts de couleur (CIEDE2000) entre les onze emplacements de teinte de accueil-vues.css, et entre
chacun et les couleurs de sens de recreo.css (juste, faux, indice, bloc adulte, accent, en-tete, explication).

Usage : python -I ecarts-teintes.py
Pour une paire, l'ecart est le plus petit des deux : fond contre fond, contour contre contour. Colonnes :
vue normale, puis simulation de protanopie, deuteranopie, tritanopie (matrices de Machado et al. 2009,
severite 1.0, recopiees dans ce fichier : une simulation approximative, pas une mesure sur des yeux).
Repere de lecture : sous 5, deux teintes se confondent facilement ; la paire la plus proche de l'ensemble
d'origine (emplacements 6 et 11) est a 5,9 en vue normale.
"""
import itertools
import math
import re
from pathlib import Path

ICI = Path(__file__).resolve().parent
CSS_MAISON = (ICI.parents[2] / "adapters/adapter-web/src/main/resources/static/css/recreo.css").read_text(encoding="utf-8")
CSS_VUES = (ICI / "accueil-vues.css").read_text(encoding="utf-8")

SIM = {
    "normale": None,
    "protanopie": [[0.152286, 1.052583, -0.204868], [0.114503, 0.786281, 0.099216], [-0.003882, -0.048116, 1.051998]],
    "deuteranopie": [[0.367322, 0.860646, -0.227968], [0.280085, 0.672501, 0.047413], [-0.011820, 0.042940, 0.968881]],
    "tritanopie": [[1.255528, -0.076749, -0.178779], [-0.078411, 0.930809, 0.147602], [0.004733, 0.691367, 0.303900]],
}


def variable(nom):
    return re.search(r"--" + nom + r":\s*(#[0-9a-fA-F]{6})", CSS_MAISON).group(1)


def lin(c):
    return c / 12.92 if c <= 0.04045 else ((c + 0.055) / 1.055) ** 2.4


def lab(h, m=None):
    r, g, b = (lin(int(h[i:i + 2], 16) / 255) for i in (1, 3, 5))
    if m:
        r, g, b = (max(0.0, min(1.0, sum(m[i][j] * v for j, v in enumerate((r, g, b))))) for i in range(3))
    x = 0.4124 * r + 0.3576 * g + 0.1805 * b
    y = 0.2126 * r + 0.7152 * g + 0.0722 * b
    z = 0.0193 * r + 0.1192 * g + 0.9505 * b
    f = lambda t: t ** (1 / 3) if t > 0.008856 else 7.787 * t + 16 / 116
    fx, fy, fz = f(x / 0.95047), f(y), f(z / 1.08883)
    return 116 * fy - 16, 500 * (fx - fy), 200 * (fy - fz)


def de2000(c1, c2):
    l1, a1, b1 = c1
    l2, a2, b2 = c2
    cb = (math.hypot(a1, b1) + math.hypot(a2, b2)) / 2
    g = 0.5 * (1 - math.sqrt(cb ** 7 / (cb ** 7 + 25 ** 7)))
    a1p, a2p = (1 + g) * a1, (1 + g) * a2
    c1p, c2p = math.hypot(a1p, b1), math.hypot(a2p, b2)
    h1, h2 = math.degrees(math.atan2(b1, a1p)) % 360, math.degrees(math.atan2(b2, a2p)) % 360
    dl, dc = l2 - l1, c2p - c1p
    if c1p * c2p == 0:
        dh = 0
    else:
        dh = h2 - h1
        dh = dh - 360 if dh > 180 else dh + 360 if dh < -180 else dh
    dhh = 2 * math.sqrt(c1p * c2p) * math.sin(math.radians(dh / 2))
    lb, cbp = (l1 + l2) / 2, (c1p + c2p) / 2
    if c1p * c2p == 0:
        hb = h1 + h2
    elif abs(h1 - h2) <= 180:
        hb = (h1 + h2) / 2
    else:
        hb = (h1 + h2 + 360) / 2 if h1 + h2 < 360 else (h1 + h2 - 360) / 2
    t = (1 - 0.17 * math.cos(math.radians(hb - 30)) + 0.24 * math.cos(math.radians(2 * hb))
         + 0.32 * math.cos(math.radians(3 * hb + 6)) - 0.2 * math.cos(math.radians(4 * hb - 63)))
    sl = 1 + 0.015 * (lb - 50) ** 2 / math.sqrt(20 + (lb - 50) ** 2)
    sc, sh = 1 + 0.045 * cbp, 1 + 0.015 * cbp * t
    rt = -2 * math.sqrt(cbp ** 7 / (cbp ** 7 + 25 ** 7)) * math.sin(math.radians(60 * math.exp(-((hb - 275) / 25) ** 2)))
    return math.sqrt((dl / sl) ** 2 + (dc / sc) ** 2 + (dhh / sh) ** 2 + rt * (dc / sc) * (dhh / sh))


def ecart(p, q):
    return [min(de2000(lab(p[0], m), lab(q[0], m)), de2000(lab(p[1], m), lab(q[1], m))) for m in SIM.values()]


TEINTES = {int(n): (f, b) for n, f, b in re.findall(
    r'\[data-teinte="(\d+)"\]\s*\{\s*--teinte-fond:\s*(#\w+);\s*--teinte-bord:\s*(#\w+);', CSS_VUES)}
SENS = {
    "juste (pastille)": ("#2e7d32", "#2e7d32"),
    "faux (pastille)": ("#c62828", "#c62828"),
    "indice": (variable("recreo-attention-fond"), variable("recreo-attention-bordure")),
    "bloc adulte": (variable("recreo-adulte-fond"), variable("recreo-adulte-bordure")),
    "accent": (variable("recreo-accent"), variable("recreo-accent")),
    "en-tete": (variable("recreo-entete-fond"), variable("recreo-entete-fond")),
    "explication": (variable("recreo-explication-fond"), variable("recreo-explication-bordure")),
}
ENTETE = "normale | protanopie | deuteranopie | tritanopie"
fmt = lambda e: " | ".join(f"{v:5.1f}" for v in e)

print("Teinte contre teinte, les 12 paires les plus proches en vue normale :", ENTETE, sep="\n")
paires = sorted(((ecart(TEINTES[a], TEINTES[b]), a, b) for a, b in itertools.combinations(sorted(TEINTES), 2)))
for e, a, b in paires[:12]:
    print(f"  {a:2d} - {b:2d} : {fmt(e)}")
print("Emplacement contre couleurs de sens, le plus proche de chaque emplacement :", ENTETE, sep="\n")
for n in sorted(TEINTES):
    nom, e = min(((k, ecart(TEINTES[n], v)) for k, v in SENS.items()), key=lambda x: x[1][0])
    print(f"  {n:2d} / {nom:16s}: {fmt(e)}")
