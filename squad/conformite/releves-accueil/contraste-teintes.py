"""Rapports de contraste WCAG 2.x des onze emplacements de teinte de accueil-vues.css.

Usage : python -I contraste-teintes.py
Lit les emplacements [data-teinte="N"] dans accueil-vues.css et la page, l'encre et l'accent dans recreo.css.
Planchers du depot : 4,5:1 pour un texte, 3:1 pour un composant non textuel.
"""
import re
from pathlib import Path

ICI = Path(__file__).resolve().parent
CSS_MAISON = (ICI.parents[2] / "adapters/adapter-web/src/main/resources/static/css/recreo.css").read_text(encoding="utf-8")
CSS_VUES = (ICI / "accueil-vues.css").read_text(encoding="utf-8")


def variable(nom):
    return re.search(r"--" + nom + r":\s*(#[0-9a-fA-F]{6})", CSS_MAISON).group(1)


def lum(h):
    r, g, b = (int(h.lstrip("#")[i:i + 2], 16) / 255 for i in (0, 2, 4))
    f = lambda c: c / 12.92 if c <= 0.03928 else ((c + 0.055) / 1.055) ** 2.4
    return 0.2126 * f(r) + 0.7152 * f(g) + 0.0722 * f(b)


def rapport(a, b):
    la, lb = sorted((lum(a), lum(b)), reverse=True)
    return (la + 0.05) / (lb + 0.05)


PAGE, ENCRE, ACCENT = variable("recreo-fond"), variable("recreo-texte"), variable("recreo-accent")
EXPL_FOND, EXPL_BORD = variable("recreo-explication-fond"), variable("recreo-explication-bordure")
print(f"page {PAGE}, encre {ENCRE}, accent {ACCENT}")
print("emplacement | fond | bord | encre/fond | bord/page | bord/fond | fond/page")
for n, fond, bord in re.findall(r'\[data-teinte="(\d+)"\]\s*\{\s*--teinte-fond:\s*(#\w+);\s*--teinte-bord:\s*(#\w+);', CSS_VUES):
    print(f"{n} | {fond} | {bord} | {rapport(ENCRE, fond):.2f} | {rapport(bord, PAGE):.2f} | {rapport(bord, fond):.2f} | {rapport(fond, PAGE):.2f}")
print(f"neutre (explication) | {EXPL_FOND} | {EXPL_BORD} | {rapport(ENCRE, EXPL_FOND):.2f} | {rapport(EXPL_BORD, PAGE):.2f} | {rapport(EXPL_BORD, EXPL_FOND):.2f} | {rapport(EXPL_FOND, PAGE):.2f}")
print(f"bascule : accent/page {rapport(ACCENT, PAGE):.2f} ; carte : blanc/accent {rapport('#ffffff', ACCENT):.2f} ; pictogramme : encre/blanc {rapport(ENCRE, '#ffffff'):.2f}")
