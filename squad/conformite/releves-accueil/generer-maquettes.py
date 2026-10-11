"""Genere les maquettes statiques de proposition-ines-accueil-vues.md depuis le catalogue reel.

Usage, depuis la racine du depot : python -I squad/conformite/releves-accueil/generer-maquettes.py

Lit adapters/adapter-content/src/main/resources/catalogue.yaml et le descripteur de chaque paquet
(matiere, niveau), ecrit les fichiers maquette-*.html a cote de ce script. Aucune donnee n'est
inventee : les 147 titres, leur niveau et leur matiere viennent du catalogue. Les trois tables
ci-dessous (libelle court, emplacement de teinte, ordre) sont des PROPOSITIONS, pas du contenu servi.
"""
import html
import re
from pathlib import Path

ICI = Path(__file__).resolve().parent
RACINE = ICI.parents[2]
RES = RACINE / "adapters" / "adapter-content" / "src" / "main" / "resources"
STATIC = "../../../adapters/adapter-web/src/main/resources/static"

NIVEAUX = ["CP", "CE1", "CM1"]

# Ordre propose des matieres (a arbitrer par camille-po et nadia-prof), libelle court propose (a
# arbitrer par nadia-prof), nom du pictogramme, emplacement de teinte.
MATIERES = [
    ("Français", "Français", "francais", 1),
    ("Mathématiques", "Maths", "mathematiques", 2),
    ("Langues vivantes (anglais)", "Anglais", "langues-vivantes", 3),
    ("Sciences et technologie", "Sciences", "sciences", 4),
    ("Questionner le monde", "Questionner le monde", "questionner-le-monde", 5),
    ("Histoire-géographie", "Histoire-géographie", "histoire-geographie", 6),
    ("Enseignement moral et civique", "Vivre ensemble", "emc", 7),
    ("Arts plastiques", "Arts plastiques", "arts-plastiques", 8),
    ("Éducation musicale", "Musique", "education-musicale", 9),
    ("Histoire des arts", "Histoire des arts", "histoire-des-arts", 10),
    ("Éducation physique et sportive", "Sport", "eps", 11),
]
PAR_NOM = {m[0]: m for m in MATIERES}

ICONE_VUE_MATIERES = (
    '<svg viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"><rect x="2" y="2" width="9" height="9" rx="2"/>'
    '<rect x="13" y="2" width="9" height="9" rx="2"/><rect x="2" y="13" width="9" height="9" rx="2"/>'
    '<rect x="13" y="13" width="9" height="9" rx="2"/></svg>'
)
ICONE_VUE_TOUT = (
    '<svg viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"><rect x="2" y="3" width="20" height="4" rx="2"/>'
    '<rect x="2" y="10" width="20" height="4" rx="2"/><rect x="2" y="17" width="20" height="4" rx="2"/></svg>'
)
ICONE_RETOUR = (
    '<svg viewBox="0 0 24 24" aria-hidden="true" fill="none" stroke="currentColor" stroke-width="3" '
    'stroke-linecap="round" stroke-linejoin="round"><path d="M15 4L7 12L15 20"/></svg>'
)


def lire_catalogue():
    texte = (RES / "catalogue.yaml").read_text(encoding="utf-8")
    jeux, cur = [], None
    for ligne in texte.splitlines():
        m = re.match(r"\s*- id:\s*(\S+)", ligne)
        if m:
            cur = {"id": m.group(1)}
            jeux.append(cur)
            continue
        m = re.match(r"\s+(\w+):\s*(.*)$", ligne)
        if m and cur is not None:
            v = m.group(2).strip()
            if v.startswith('"') and v.endswith('"'):
                v = v[1:-1]
            cur[m.group(1)] = v
    for j in jeux:
        p = (RES / "contenu" / (j["paquet"] + ".yaml")).read_text(encoding="utf-8")
        d = re.search(r"descripteur:\s*\n((?:\s+.*\n)+)", p).group(1)
        j["matiere"] = re.search(r'matiere:\s*"?([^"\n]+)"?', d).group(1).strip()
        j["niveau"] = re.search(r'niveau:\s*"?([^"\n]+)"?', d).group(1).strip()
    return jeux


def e(s):
    return html.escape(s, quote=True)


def tete(titre, classe_corps="", gris=False):
    style = ' style="filter: grayscale(1)"' if gris else ""
    return f"""<!DOCTYPE html>
<!--
  VEHICULE DE MESURE, JAMAIS DU CODE SERVI. Genere par generer-maquettes.py depuis le catalogue reel.
  Charge les deux feuilles reelles dans l'ordre d'ADR-0014, puis accueil-vues.css (regles candidates).
  Les boutons de jeu ne font rien ; les puces, les tuiles et les boutons de vue naviguent entre maquettes.
-->
<html lang="fr"{style}><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>{e(titre)}</title>
<link rel="stylesheet" href="{STATIC}/vendor/bootstrap.min.css">
<link rel="stylesheet" href="{STATIC}/css/recreo.css">
<link rel="stylesheet" href="accueil-vues.css">
</head><body class="{classe_corps}">
"""


def entete(niveau_actif, vue):
    """vue : 'actuelle', 'v1' ou 'v2' ; les puces gardent la vue en cours."""
    puces = ""
    for n in NIVEAUX:
        cible = {"actuelle": f"maquette-actuelle-{n}.html", "v1": f"maquette-v1-etage1-{n}.html",
                 "v2": f"maquette-v2-{n}.html"}[vue]
        actif = " est-actif" if n == niveau_actif else ""
        puces += f'<a class="btn chip-niveau{actif}" data-role="entree" href="{cible}">{n}</a>'
    sortie = {"actuelle": "maquette-actuelle-tous.html", "v1": "maquette-v1-etage1-tous.html",
              "v2": "maquette-v2-tous.html"}[vue]
    return (
        '<header class="entete"><nav class="container entete-contenu">'
        f'<img src="{STATIC}/favicon.ico" alt="" class="icone-entete"><h1 class="nom-site">Recreo</h1>'
        f'{puces}<a class="btn lien-accueil" data-role="sortie" href="{sortie}">Les jeux</a></nav></header>\n'
    )


def pied():
    return "</div></main>\n</body></html>\n"


def debut_zone(niveau, bouton):
    phrase = f'<p class="filtre-niveau">Pour le {niveau}</p>' if niveau else ""
    barre = f'<div class="barre-vue">{phrase}{bouton}</div>\n' if phrase or bouton else ""
    return '<main id="jeu" class="container zone-jeu">\n<div>\n' + barre


def bouton_vers(href, icone, libelle):
    return f'<a class="btn bascule" data-role="entree" href="{href}">{icone}<span>{libelle}</span></a>'


def picto(slug):
    return f'<img src="pictos/{slug}.svg" alt="">'


def carte(jeu):
    return (
        f'<li><button type="button" class="btn demarrer" data-role="action" data-jeu="{e(jeu["id"])}">'
        f'{e(jeu["titre"])}</button></li>'
    )


def matieres_du(jeux):
    vues = []
    for nom, court, slug, teinte in MATIERES:
        liste = [j for j in jeux if j["matiere"] == nom]
        if liste:
            vues.append((nom, court, slug, teinte, liste))
    inconnues = {j["matiere"] for j in jeux} - set(PAR_NOM)
    assert not inconnues, f"matiere hors table : {inconnues}"
    return vues


def bloc_matiere(court, slug, teinte, liste):
    cartes = "".join(carte(j) for j in liste)
    return (
        f'<section class="bloc-matiere" data-teinte="{teinte}" data-matiere="{slug}">'
        f'<h2 class="entete-matiere">{picto(slug)}<span>{e(court)}</span></h2>'
        f'<ul class="cartes list-unstyled">{cartes}</ul></section>\n'
    )


def ecrire(nom, contenu):
    (ICI / nom).write_text(contenu, encoding="utf-8", newline="\n")


def main():
    catalogue = lire_catalogue()
    assert len(catalogue) == 147, len(catalogue)
    for niveau in NIVEAUX + [None]:
        suffixe = niveau or "tous"
        jeux = [j for j in catalogue if niveau is None or j["niveau"] == niveau]
        mats = matieres_du(jeux)

        # Baseline : la liste actuelle, balisage du gabarit servi.
        liste = "".join(
            f'<li><button type="button" class="btn demarrer" data-role="action">{e(j["titre"])}</button></li>'
            for j in jeux
        )
        ecrire(f"maquette-actuelle-{suffixe}.html",
               tete(f"actuelle {suffixe}") + entete(niveau, "actuelle") + debut_zone(niveau, "")
               + f'<ul class="jeux list-unstyled">{liste}</ul>\n' + pied())

        # V1 etage 1 : tuiles de matiere.
        tuiles = "".join(
            f'<li><a class="btn tuile" data-role="action" data-teinte="{t}" '
            f'href="maquette-v1-etage2-{suffixe}-{slug}.html">{picto(slug)}<span>{e(court)}</span></a></li>'
            for _, court, slug, t, _ in mats
        )
        ecrire(f"maquette-v1-etage1-{suffixe}.html",
               tete(f"V1 etage 1 {suffixe}") + entete(niveau, "v1")
               + debut_zone(niveau, bouton_vers(f"maquette-v2-{suffixe}.html", ICONE_VUE_TOUT, "Tout voir"))
               + f'<ul class="matieres list-unstyled">{tuiles}</ul>\n' + pied())

        # V1 etage 2 : les jeux d'une matiere.
        for nom, court, slug, t, liste_m in mats:
            ecrire(f"maquette-v1-etage2-{suffixe}-{slug}.html",
                   tete(f"V1 etage 2 {suffixe} {slug}") + entete(niveau, "v1")
                   + debut_zone(niveau, bouton_vers(f"maquette-v1-etage1-{suffixe}.html", ICONE_RETOUR,
                                                    "Par matière"))
                   + bloc_matiere(court, slug, t, liste_m) + pied())

        # V2 : une page, les matieres en bandeaux collants.
        corps = "".join(bloc_matiere(court, slug, t, lm) for _, court, slug, t, lm in mats)
        bouton = bouton_vers(f"maquette-v1-etage1-{suffixe}.html", ICONE_VUE_MATIERES, "Par matière")
        ecrire(f"maquette-v2-{suffixe}.html",
               tete(f"V2 {suffixe}") + entete(niveau, "v2") + debut_zone(niveau, bouton) + corps + pied())

        if niveau == "CM1":
            tuiles_longues = "".join(
                f'<li><a class="btn tuile" data-role="action" data-teinte="{t}" href="#">{picto(slug)}<span>{e(nom)}</span></a></li>'
                for nom, _, slug, t, _ in mats
            )
            ecrire("maquette-v1-etage1-CM1-libelles-longs.html",
                   tete("V1 etage 1 CM1, libelles complets") + entete(niveau, "v1")
                   + debut_zone(niveau, bouton_vers("maquette-v2-CM1.html", ICONE_VUE_TOUT, "Tout voir"))
                   + f'<ul class="matieres list-unstyled">{tuiles_longues}</ul>\n' + pied())

        if niveau == "CP":
            ecrire("maquette-v2-CP-neutre.html",
                   tete("V2 CP sans teinte", "teinte-neutre") + entete(niveau, "v2")
                   + debut_zone(niveau, bouton) + corps + pied())
            ecrire("maquette-v2-CP-gris.html",
                   tete("V2 CP en niveaux de gris", gris=True) + entete(niveau, "v2")
                   + debut_zone(niveau, bouton) + corps + pied())
            ecrire("maquette-v1-etage1-CP-gris.html",
                   tete("V1 etage 1 CP en niveaux de gris", gris=True) + entete(niveau, "v1")
                   + debut_zone(niveau, bouton_vers("maquette-v2-CP.html", ICONE_VUE_TOUT, "Tout voir"))
                   + f'<ul class="matieres list-unstyled">{tuiles}</ul>\n' + pied())

    # Planche des pictogrammes, avec libelle court et teinte.
    cellules = "".join(
        f'<li><span class="tuile" data-teinte="{t}">{picto(slug)}<span>{e(court)}</span></span>'
        f'<small>{e(nom)}</small></li>'
        for nom, court, slug, t in MATIERES
    )
    ecrire("maquette-pictos.html",
           tete("pictogrammes de matiere")
           + '<main class="container zone-jeu"><div><ul class="matieres list-unstyled" style="grid-template-columns:'
             f'repeat(auto-fill,minmax(11rem,1fr));row-gap:1.5rem">{cellules}</ul>\n' + pied())

    # Bouton de bascule : deux formes, deux etats chacune.
    def segment(courant, libelle, icone, href):
        if courant:
            return (f'<span class="segment est-courant" aria-current="true">'
                    f'<svg viewBox="0 0 24 24" aria-hidden="true" fill="none" stroke="currentColor" '
                    f'stroke-width="3" stroke-linecap="round" stroke-linejoin="round"><path d="M4 12L10 18L20 6"/></svg>'
                    f'<span>{libelle}</span></span>')
        return f'<a class="segment" href="{href}">{icone}<span>{libelle}</span></a>'

    seg_css = """
.segments{display:inline-flex;border:.125rem solid var(--recreo-accent);border-radius:.5rem;overflow:hidden}
.segment{display:inline-flex;align-items:center;gap:.5rem;min-height:2.75rem;padding:.5rem 1.1rem;font-size:1.25rem;color:var(--recreo-accent);text-decoration:none;background:transparent}
.segment svg{width:1.5rem;height:1.5rem}
.segment+.segment{border-left:.125rem solid var(--recreo-accent)}
.segment.est-courant{font-weight:bold;box-shadow:inset 0 -.375rem 0 var(--recreo-accent)}
.cas{margin:1.5rem 0}.cas small{display:block;margin-bottom:.5rem}
"""
    cas = (
        '<div class="cas"><small>O1, vue par defaut (toute la liste) : le bouton nomme la vue ou il mene</small>'
        + bouton_vers("#", ICONE_VUE_MATIERES, "Par matière") + "</div>"
        '<div class="cas"><small>O1, vue par matiere (etage 1) : meme bouton, autre destination</small>'
        + bouton_vers("#", ICONE_VUE_TOUT, "Tout voir") + "</div>"
        '<div class="cas"><small>O2, vue par defaut : deux segments, la vue courante porte une coche et un trait plein</small>'
        '<span class="segments">'
        + segment(True, "Tout voir", "", "#") + segment(False, "Par matière", ICONE_VUE_MATIERES, "#")
        + "</span></div>"
        '<div class="cas"><small>O2, vue par matiere</small><span class="segments">'
        + segment(False, "Tout voir", ICONE_VUE_TOUT, "#") + segment(True, "Par matière", "", "#")
        + "</span></div>"
    )
    for suffixe, gris in (("", False), ("-gris", True)):
        ecrire(f"maquette-bascule{suffixe}.html",
               tete("bouton de bascule", gris=gris).replace("</head>", f"<style>{seg_css}</style></head>")
               + '<main class="container zone-jeu"><div>' + cas + pied())


if __name__ == "__main__":
    main()
