#!/usr/bin/env bash
# Verifie que AGENTS_SQUAD (outils/rapport-tokens.py) reste aligne avec le roster reel de la
# squad, source de verite : un fichier par agent sous .claude/agents/ (squad/EQUIPE.md en est le
# miroir humain).
#
# Test purement textuel, sans invoquer aucun interpreteur Python : AGENTS_SQUAD est une simple
# liste de chaines, une extraction par expression reguliere suffit et evite toute dependance a
# l'interpreteur Python installe sur le poste qui execute ce test (doctrine ADR-0009 : un juge pur,
# sans dependance au host).
set -uo pipefail

REPERTOIRE_OUTILS="$(cd "$(dirname "$0")" && pwd)"
SCRIPT_PY="${REPERTOIRE_OUTILS}/rapport-tokens.py"
REPO_RACINE="$(cd "${REPERTOIRE_OUTILS}/.." && pwd)"

ECHECS=0

# --- extrait AGENTS_SQUAD de outils/rapport-tokens.py ---
bloc="$(sed -n '/^AGENTS_SQUAD = \[/,/^\]/p' "${SCRIPT_PY}")"
agents_declares="$(printf '%s\n' "${bloc}" | grep -oE '"[a-z-]+"' | tr -d '"' | sort)"

# --- roster reel : un fichier par agent sous .claude/agents/ ---
agents_reels="$(cd "${REPO_RACINE}/.claude/agents" && ls -1 -- *.md | sed 's/\.md$//' | sort)"

if [ -z "${agents_declares}" ]; then
  echo "ECHEC : aucun agent extrait de AGENTS_SQUAD dans ${SCRIPT_PY}."
  ECHECS=$((ECHECS + 1))
fi

if printf '%s\n' "${agents_declares}" | grep -qx "ines-design"; then
  echo "ok ines-design est present dans AGENTS_SQUAD"
else
  echo "ECHEC : ines-design est absent de AGENTS_SQUAD."
  ECHECS=$((ECHECS + 1))
fi

if [ "${agents_declares}" = "${agents_reels}" ]; then
  nombre="$(printf '%s\n' "${agents_reels}" | wc -l)"
  echo "ok AGENTS_SQUAD correspond exactement aux ${nombre} agents de .claude/agents/"
else
  echo "ECHEC : AGENTS_SQUAD ne correspond pas au roster reel de .claude/agents/."
  echo "  declares : $(printf '%s' "${agents_declares}" | tr '\n' ' ')"
  echo "  reels    : $(printf '%s' "${agents_reels}" | tr '\n' ' ')"
  ECHECS=$((ECHECS + 1))
fi

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : AGENTS_SQUAD a derive du roster reel de la squad."
  exit 1
fi
echo "Tous les cas passent : AGENTS_SQUAD est aligne avec le roster reel de la squad."
