#!/usr/bin/env python
"""Agrege la consommation de tokens de la squad, par agent et par sprint.

Claude Code ecrit une transcription par session sous
`~/.claude/projects/<projet-encode>/` :

    <session-id>.jsonl                          le fil principal (orchestrateur)
    <session-id>/subagents/agent-<id>.jsonl     un fichier par sous-agent lance
    <session-id>/subagents/agent-<id>.meta.json contient `agentType`, le nom de l'agent

Chaque tour assistant porte `message.usage` (input_tokens, output_tokens,
cache_read_input_tokens, cache_creation_input_tokens) et `message.model`. Chaque
entree porte un `timestamp` ISO en UTC.

Un message est attribue a un sprint quand son horodatage tombe dans la fenetre
[debut du planning, fin de la review] lue dans `squad/sprints/sNN-<slug>/`. Ce qui
tombe hors de toute fenetre est reporte sur une ligne `hors sprint`, jamais
reparti arbitrairement.

Les quatre compteurs sont toujours affiches separement : les additionner donnerait
un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur. Aucune conversion en euros n'est faite ici, ce sont des compteurs de
tokens.

Usage :
    python outils/rapport-tokens.py --sprint s03             un sprint, plus le journal
    python outils/rapport-tokens.py --sprint s03 --dry-run   affiche sans rien ecrire
    Sans --sprint l'outil refuse (code 2) et n'ecrit rien.
"""

import argparse
import json
import re
import sys
from datetime import datetime, timezone
from pathlib import Path

# Les 9 agents de la squad, alignes sur .claude/agents/ et squad/EQUIPE.md. Un agent absent
# des transcriptions apparait quand meme dans le rapport, avec la mention "aucune invocation
# mesuree" plutot qu'un zero qui laisserait croire a une mesure.
AGENTS_SQUAD = [
    "camille-po",
    "sofia-sm",
    "theo-archi",
    "marc-dev",
    "lea-dev",
    "yanis-qa",
    "relecteur-pedagogique",
    "nadia-prof",
    "ines-design",
]

FIL_PRINCIPAL = "orchestrateur (fil principal)"
HORS_SPRINT = "hors sprint"

RE_HORODATAGE = re.compile(r"(\d{4}-\d{2}-\d{2})[ T](\d{2}:\d{2})")
RE_SPRINT_DIR = re.compile(r"^(s\d+)(?:-(.*))?$")


def encoder_chemin_projet(repo: Path) -> str:
    """Reproduit l'encodage du chemin projet utilise par Claude Code.

    Un chemin de depot devient un identifiant plat : chaque `:` et chaque separateur
    de chemin devient un tiret.
    """
    brut = str(repo.resolve())
    return brut.replace(":", "-").replace("\\", "-").replace("/", "-")


def lire_horodatage(fichier: Path, etiquette: str):
    """Extrait un horodatage local d'un fichier de ceremonie et le rend en UTC.

    `etiquette` est cherchee sans accent ni casse ("debut", "fin"), le dernier
    horodatage de la ligne correspondante est retenu, pas le premier : depuis
    l'amendement de squad/README.md section 2 (retro de s22), un `review.md` non
    encore clos peut recevoir plusieurs ajouts dates, chacun pouvant porter sa
    propre ligne "Horodatage de fin" ; seule la derniere reflete la cloture reelle.
    """
    if not fichier.exists():
        return None
    resultat = None
    for ligne in fichier.read_text(encoding="utf-8", errors="replace").splitlines():
        normalisee = ligne.lower().replace("é", "e").replace("è", "e")
        if "horodatage" not in normalisee or etiquette not in normalisee:
            continue
        trouve = RE_HORODATAGE.search(ligne)
        if not trouve:
            continue
        naif = datetime.strptime(f"{trouve.group(1)} {trouve.group(2)}", "%Y-%m-%d %H:%M")
        # Les fichiers de ceremonie sont ecrits en heure locale, les transcriptions
        # en UTC : on convertit pour pouvoir comparer.
        resultat = naif.astimezone().astimezone(timezone.utc)
    return resultat


def racine_principale_du_depot(repo: Path) -> Path:
    """Rend la racine du depot principal, meme si `repo` est un worktree.

    Correction du 2026-08-31 (s25, lot F ; defaut constate a la review de s24, section 2.4 de la
    retro). Le dossier des transcriptions est deduit du chemin du depot ; un worktree a un autre
    chemin, donc un autre dossier, et ce dossier-la est vide. Lance depuis le worktree ou la
    ceremonie se conduit, l'outil rendait "aucune invocation mesuree" pour les neuf agents : la
    commande reussissait, le rapport s'ecrivait, et il decrivait un autre arbre que celui du
    sprint. C'est l'ecart muet que CLAUDE.md sanctionne, applique a l'outil qui mesure.

    Dans un worktree, `.git` est un fichier et non un repertoire ; il porte une ligne
    `gitdir: <racine principale>/.git/worktrees/<nom>`. La racine principale est donc le
    grand-parent du repertoire `.git` ainsi designe. Hors worktree, `.git` est un repertoire et
    `repo` est deja la racine principale : la fonction rend alors son argument inchange.

    Rend `repo` sans rien deviner quand la forme n'est pas reconnue - la refuser ici ferait
    echouer l'outil sur un depot exporte sans `.git`, ou il n'y a pourtant rien d'ambigu.
    """
    marque = repo / ".git"
    if not marque.is_file():
        return repo
    try:
        contenu = marque.read_text(encoding="utf-8")
    except OSError:
        return repo
    for ligne in contenu.splitlines():
        if not ligne.startswith("gitdir:"):
            continue
        designe = Path(ligne.split(":", 1)[1].strip())
        if designe.name and designe.parent.name == "worktrees":
            # <racine>/.git/worktrees/<nom> : deux parents pour remonter a <racine>/.git
            return designe.parent.parent.parent
        return designe.parent
    return repo


def verdict_de_collecte(dossier_projet: Path):
    """Rend "utilisable" ou le motif du refus, sans jamais rendre un rapport vide pour vrai.

    Le mode de defaillance corrige ici n'est pas un plantage, c'est un rapport d'apparence
    normale : le script trouvait un dossier vide, ecrivait "aucune invocation mesuree" pour les
    neuf agents - la bonne formulation du script, et une description fausse du sprint. Un outil de
    mesure qui ne trouve pas sa matiere refuse ; il ne rend pas un zero deguise en constat.
    """
    if not dossier_projet.is_dir():
        return "dossier absent"
    if not any(dossier_projet.glob("*.jsonl")) and not any(dossier_projet.glob("*/subagents")):
        return "dossier vide"
    return "utilisable"


def charger_fenetres_sprints(repo: Path):
    """Construit la liste des fenetres temporelles, une par sprint."""
    racine = repo / "squad" / "sprints"
    if not racine.is_dir():
        return []

    fenetres = []
    for dossier in sorted(p for p in racine.iterdir() if p.is_dir()):
        trouve = RE_SPRINT_DIR.match(dossier.name)
        if not trouve:
            continue
        debut = lire_horodatage(dossier / "planning.md", "debut")
        if debut is None:
            print(
                f"  ignore {dossier.name} : pas d'horodatage de debut dans planning.md",
                file=sys.stderr,
            )
            continue
        fin = lire_horodatage(dossier / "review.md", "fin")
        fenetres.append(
            {
                "id": trouve.group(1),
                "dossier": dossier,
                "debut": debut,
                # Sprint encore ouvert : la fenetre court jusqu'a maintenant.
                "fin": fin or datetime.now(timezone.utc),
                "en_cours": fin is None,
            }
        )
    return fenetres


def sprint_de(horodatage, fenetres):
    for fenetre in fenetres:
        if fenetre["debut"] <= horodatage <= fenetre["fin"]:
            return fenetre["id"]
    return HORS_SPRINT


def compteurs_vides():
    return {
        "tours": 0,
        "input": 0,
        "output": 0,
        "cache_cree": 0,
        "cache_lu": 0,
        "modeles": set(),
    }


def cumuler(cible, usage, modele):
    cible["tours"] += 1
    cible["input"] += usage.get("input_tokens", 0) or 0
    cible["output"] += usage.get("output_tokens", 0) or 0
    cible["cache_cree"] += usage.get("cache_creation_input_tokens", 0) or 0
    cible["cache_lu"] += usage.get("cache_read_input_tokens", 0) or 0
    if modele:
        cible["modeles"].add(modele)


def parcourir_transcription(fichier: Path, nom_agent: str, fenetres, resultats):
    """Cumule les tours assistants d'une transcription dans `resultats`.

    `resultats` est indexe par (sprint, agent). Chaque message est attribue
    individuellement, pour qu'un agent a cheval sur deux sprints soit reparti
    correctement.
    """
    try:
        contenu = fichier.read_text(encoding="utf-8", errors="replace")
    except OSError as erreur:
        print(f"  illisible : {fichier} ({erreur})", file=sys.stderr)
        return

    for ligne in contenu.splitlines():
        ligne = ligne.strip()
        if not ligne:
            continue
        try:
            entree = json.loads(ligne)
        except json.JSONDecodeError:
            continue

        message = entree.get("message")
        if not isinstance(message, dict):
            continue
        usage = message.get("usage")
        if not isinstance(usage, dict):
            continue
        # Les messages synthetiques (interruptions, erreurs d'API) ne correspondent
        # a aucun appel facture : les compter fausserait le nombre de tours.
        if message.get("model") == "<synthetic>":
            continue

        brut = entree.get("timestamp")
        if not brut:
            continue
        try:
            horodatage = datetime.fromisoformat(brut.replace("Z", "+00:00"))
        except ValueError:
            continue

        cle = (sprint_de(horodatage, fenetres), nom_agent)
        resultats.setdefault(cle, compteurs_vides())
        cumuler(resultats[cle], usage, message.get("model"))


def collecter(dossier_projet: Path, fenetres):
    """Balaie toutes les sessions du projet, pas seulement la courante."""
    resultats = {}
    if not dossier_projet.is_dir():
        print(f"Aucune transcription sous {dossier_projet}", file=sys.stderr)
        return resultats

    for transcription in sorted(dossier_projet.glob("*.jsonl")):
        parcourir_transcription(transcription, FIL_PRINCIPAL, fenetres, resultats)

    for sous_agents in sorted(dossier_projet.glob("*/subagents")):
        for fichier in sorted(sous_agents.glob("agent-*.jsonl")):
            meta = fichier.with_suffix(".meta.json")
            nom = "sous-agent inconnu"
            if meta.exists():
                try:
                    nom = json.loads(meta.read_text(encoding="utf-8")).get(
                        "agentType", nom
                    )
                except (OSError, json.JSONDecodeError):
                    pass
            parcourir_transcription(fichier, nom, fenetres, resultats)

    return resultats


def agents_du_sprint(resultats, sprint):
    """Agents de la squad d'abord, puis les autres, puis le fil principal."""
    presents = {agent for (identifiant, agent) in resultats if identifiant == sprint}
    autres = sorted(presents - set(AGENTS_SQUAD) - {FIL_PRINCIPAL})
    return AGENTS_SQUAD + autres + [FIL_PRINCIPAL]


def tableau(resultats, sprint):
    lignes = [
        "| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |",
        "|---|---|---|---:|---:|---:|---:|",
    ]
    totaux = compteurs_vides()
    mesure = False
    absents = []

    for agent in agents_du_sprint(resultats, sprint):
        compteurs = resultats.get((sprint, agent))
        if compteurs is None:
            if agent in AGENTS_SQUAD:
                absents.append(agent)
            continue

        mesure = True
        for cle in ("tours", "input", "output", "cache_cree", "cache_lu"):
            totaux[cle] += compteurs[cle]
        totaux["modeles"] |= compteurs["modeles"]

        modeles = ", ".join(sorted(compteurs["modeles"])) or "-"
        etiquette = f"`{agent}`" if agent != FIL_PRINCIPAL else agent
        lignes.append(
            f"| {etiquette} | {modeles} | {compteurs['tours']} "
            f"| {compteurs['input']:,} | {compteurs['output']:,} "
            f"| {compteurs['cache_cree']:,} | {compteurs['cache_lu']:,} |".replace(
                ",", " "
            )
        )

    if mesure:
        lignes.append(
            f"| **Total** | | **{totaux['tours']}** | **{totaux['input']:,}** "
            f"| **{totaux['output']:,}** | **{totaux['cache_cree']:,}** "
            f"| **{totaux['cache_lu']:,}** |".replace(",", " ")
        )
    if absents:
        # Un agent sans ligne n'a pas consomme zero : il n'a pas ete invoque.
        # La distinction compte pour lire le rapport.
        lignes.append("")
        lignes.append(
            "Aucune invocation mesuree sur cette fenetre pour : "
            + ", ".join(f"`{agent}`" for agent in absents)
            + "."
        )
    return "\n".join(lignes), mesure


AVERTISSEMENT = (
    "> Les quatre compteurs sont volontairement separes. Les additionner donnerait un\n"
    "> classement faux : le cache lu depasse couramment l'output de deux ordres de\n"
    "> grandeur. Ce sont des compteurs de tokens, pas des euros.\n"
)


def agents_perdus(texte, resultats, sprint):
    """Agents qui ont une ligne chiffree dans `texte` et aucune mesure dans `resultats`.

    Des transcriptions effacees en partie donnent une mesure non vide mais plus basse : la
    regenerer ecraserait des chiffres plus complets. Un agent chiffre hier et introuvable
    aujourd'hui est le signe que la matiere a disparu, pas que la consommation a baisse.
    """
    chiffres = set()
    for ligne in texte.splitlines():
        cellule = ligne.split("|")[1].strip().strip("`") if ligne.startswith("| ") else ""
        if cellule and cellule not in ("Agent", "**Total**"):
            chiffres.add(cellule)
    return chiffres - {agent for (identifiant, agent) in resultats if identifiant == sprint}


def sections_du_journal(texte):
    """Decoupe un journal en {titre de section: corps}, sur les lignes `## `."""
    sections, titre = {}, None
    for ligne in texte.splitlines():
        if ligne.startswith("## "):
            titre = ligne[3:].strip()
            sections[titre] = []
        elif titre is not None:
            sections[titre].append(ligne)
    return {cle: chr(10).join(lignes).strip(chr(10)) for cle, lignes in sections.items()}


def ecrire_rapport_sprint(fenetre, resultats, dry_run):
    corps, mesure = tableau(resultats, fenetre["id"])
    cible = fenetre["dossier"] / "RAPPORT-tokens.md"
    ancien = cible.read_text(encoding="utf-8") if cible.exists() else ""
    if agents_perdus(ancien, resultats, fenetre["id"]):
        # Transcriptions absentes ou partielles : un rapport chiffre n'est pas remplace par moins.
        print(f"conserve (transcriptions absentes ou partielles, chiffres ecrits) : {cible}")
        return
    etat = " (sprint en cours, fenetre ouverte jusqu'a maintenant)" if fenetre["en_cours"] else ""
    texte = (
        f"# [Sprint {fenetre['id']}] Consommation de tokens\n\n"
        f"- **Fenetre** : {fenetre['debut']:%Y-%m-%d %H:%M} a "
        f"{fenetre['fin']:%Y-%m-%d %H:%M} UTC{etat}\n"
        f"- **Genere par** : `outils/rapport-tokens.py`\n\n"
        f"{AVERTISSEMENT}\n{corps}\n"
    )
    if not mesure:
        texte += "\nAucune consommation mesuree sur cette fenetre.\n"

    if dry_run:
        print(f"--- {cible} ---\n{texte}")
    else:
        cible.write_text(texte, encoding="utf-8")
        print(f"ecrit : {cible}")


def ecrire_journal(repo, fenetres, resultats, dry_run):
    sections = [
        "# Journal de consommation de tokens",
        "",
        "Cumul de tous les sprints. Genere par `outils/rapport-tokens.py`, jamais edite",
        "a la main.",
        "",
        AVERTISSEMENT.rstrip("\n"),
        "",
    ]

    identifiants = [fenetre["id"] for fenetre in fenetres]
    if any(identifiant == HORS_SPRINT for (identifiant, _) in resultats):
        identifiants.append(HORS_SPRINT)

    cible = repo / "squad" / "rapports" / "JOURNAL-tokens.md"
    anciennes = sections_du_journal(cible.read_text(encoding="utf-8")) if cible.exists() else {}
    conservees = []

    for identifiant in identifiants:
        corps, mesure = tableau(resultats, identifiant)
        titre = identifiant if identifiant == HORS_SPRINT else f"Sprint {identifiant}"
        if agents_perdus(anciennes.get(titre, ""), resultats, identifiant):
            corps, mesure = anciennes[titre], True
            conservees.append(identifiant)
        sections.append(f"## {titre}")
        sections.append("")
        sections.append(corps if mesure else "Aucune consommation mesuree.")
        sections.append("")

    if not identifiants:
        sections.append("Aucun sprint ouvert : aucune fenetre a rapporter.")
        sections.append("")

    texte = "\n".join(sections)
    if conservees:
        print("sections conservees (transcriptions absentes ou partielles, chiffres ecrits) : " + ", ".join(conservees))
    if dry_run:
        print(f"--- {cible} ---\n{texte}")
    else:
        cible.parent.mkdir(parents=True, exist_ok=True)
        cible.write_text(texte, encoding="utf-8")
        print(f"ecrit : {cible}")


def main():
    analyseur = argparse.ArgumentParser(
        description="Consommation de tokens de la squad, par agent et par sprint."
    )
    analyseur.add_argument("--sprint", help="identifiant du sprint, obligatoire, par exemple s03")
    analyseur.add_argument(
        "--repo",
        type=Path,
        default=Path(__file__).resolve().parent.parent,
        help="racine du depot (par defaut : le parent de outils/)",
    )
    analyseur.add_argument(
        "--projects-dir",
        type=Path,
        help="dossier des transcriptions Claude Code (deduit du depot par defaut)",
    )
    analyseur.add_argument(
        "--dry-run",
        action="store_true",
        help="affiche les rapports sans ecrire de fichier",
    )
    options = analyseur.parse_args()

    if not options.sprint:
        print(
            "Refus : --sprint est obligatoire (par exemple --sprint s03). Sans lui, l'outil "
            "recalculerait la fenetre de chaque sprint et modifierait des rapports deja livres. "
            "Aucun fichier n'est ecrit.",
            file=sys.stderr,
        )
        return 2

    repo = options.repo.resolve()
    # Le depot principal, jamais le worktree : les transcriptions vivent sous le chemin du depot
    # d'origine. Les fenetres de sprint, elles, restent lues dans `repo` - ce sont les fichiers de
    # ceremonie de l'arbre qu'on mesure.
    depot_des_transcriptions = racine_principale_du_depot(repo)
    dossier_projet = options.projects_dir or (
        Path.home() / ".claude" / "projects" / encoder_chemin_projet(depot_des_transcriptions)
    )

    verdict = verdict_de_collecte(dossier_projet)
    if verdict != "utilisable":
        print(
            f"Refus : le dossier des transcriptions deduit est inutilisable ({verdict}).\n"
            "Aucun rapport n'est ecrit. Un rapport rendu sur un dossier vide dirait "
            '"aucune invocation mesuree" pour chaque agent : la bonne formulation de cet outil, '
            "et une description fausse du sprint.\n"
            "Verifier que --repo designe le depot dont les transcriptions sont cherchees, ou "
            "passer --projects-dir explicitement.",
            file=sys.stderr,
        )
        return 1

    fenetres = charger_fenetres_sprints(repo)
    if not fenetres:
        print(
            "Aucune fenetre de sprint trouvee : il faut au moins un "
            "squad/sprints/sNN-<slug>/planning.md avec son horodatage de debut.",
            file=sys.stderr,
        )

    resultats = collecter(dossier_projet, fenetres)

    choisies = [f for f in fenetres if f["id"] == options.sprint]
    if not choisies:
        print(f"Sprint inconnu : {options.sprint}", file=sys.stderr)
        return 1
    for fenetre in choisies:
        ecrire_rapport_sprint(fenetre, resultats, options.dry_run)

    ecrire_journal(repo, fenetres, resultats, options.dry_run)
    return 0


if __name__ == "__main__":
    sys.exit(main())
