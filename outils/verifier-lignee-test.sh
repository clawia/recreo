#!/usr/bin/env bash
# Contre-exemples du juge pur de verifier-lignee.sh (S6-13, doctrine ADR-0009).
#
# juger_lignee ne lance aucune commande : ce script la source (l'enveloppe qui touche le poste ne
# s'execute pas au sourcage, voir la garde "BASH_SOURCE[0] = 0" dans verifier-lignee.sh) et lui
# fournit des entrees fabriquees. Ce que ce script couvre : tout le verdict. Ce qu'il ne couvre
# pas : que "git rev-parse --abbrev-ref HEAD" et "git merge-base --is-ancestor round-two HEAD"
# soient les deux bonnes commandes - prouve une fois a la main sur un worktree fautif reel.
set -uo pipefail

SCRIPT="$(dirname "$0")/verifier-lignee.sh"
. "${SCRIPT}"
ECHECS=0
SORTIES=()

# verifier <intitule> <branche> <code_ancetralite> <code_attendu> [motif attendu] [motif interdit]
verifier() {
  intitule="$1"
  branche="$2"
  code_ancetralite="$3"
  attendu="$4"
  motif="${5:-}"
  interdit="${6:-}"

  sortie="$(juger_lignee "${branche}" "${code_ancetralite}" 2>&1)"
  code=$?
  SORTIES+=("${sortie}")

  if [ "${code}" != "${attendu}" ]; then
    echo "ECHEC ${intitule} : code ${code}, attendu ${attendu}"
    echo "  sortie : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  if [ -n "${motif}" ] && ! printf '%s' "${sortie}" | grep -qF "${motif}"; then
    echo "ECHEC ${intitule} : sortie sans le motif attendu"
    echo "  attendu : ${motif}"
    echo "  sortie  : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  if [ -n "${interdit}" ] && printf '%s' "${sortie}" | grep -qF "${interdit}"; then
    echo "ECHEC ${intitule} : sortie contenant un motif interdit"
    echo "  interdit : ${interdit}"
    echo "  sortie   : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  echo "ok ${intitule}"
}

# --- acceptation ---

verifier "un worktree sur round-two lui-meme est accepte" "round-two" 0 0 \
  "descend de round-two"

verifier "un worktree sur un descendant de round-two est accepte" "s06-sofia" 0 0 \
  "descend de round-two"

# --- refus, les quatre pieges nommes par l'avis de faisabilite ---

verifier "une branche qui ne descend pas de round-two est refusee et nommee" "main" 1 1 \
  "main"

verifier "une branche dont le nom contient round-two sans en descendre est refusee" \
  "round-two-bis" 1 1 "round-two-bis"

verifier "la reference round-two introuvable dans le worktree est refusee distinctement" \
  "s06-sofia" 128 1 "introuvable"

verifier "un depot en HEAD detache est refuse sans pretendre nommer une branche" \
  "HEAD" 0 1 "detache"

verifier "un nom de branche vide est refuse" "" 0 1 \
  "impossible de determiner"

# --- le test qui compte : jamais de chemin dans le message, meme sur une entree corrompue ---
#
# Les deux valeurs ci-dessous sont assemblees a l'execution plutot qu'ecrites en clair : un chemin
# de poste authentique fait rougir verifier-chemins.sh (ADR-0010) des qu'il est ecrit tel quel dans
# un fichier suivi, y compris dans une fixture de test - le meme piege qu'ADR-0010 nomme pour son
# propre script de test. Les valeurs reconstruites ici sont de vrais chemins de poste au moment ou
# juger_lignee les recoit, sans jamais l'etre dans le texte source de ce fichier.
lettre_disque="C"
chemin_windows_simule="${lettre_disque}:\\Users\\quelquun\\recreo"
verifier "une entree Windows contenant un chemin de poste n'est jamais recopiee" \
  "${chemin_windows_simule}" 1 1 "" "quelquun"

prefixe_home="/home"
chemin_unix_simule="${prefixe_home}/quelquun/recreo"
verifier "une entree Git Bash contenant un chemin de poste n'est jamais recopiee" \
  "${chemin_unix_simule}" 1 1 "" "quelquun"

# --- test negatif global : aucune sortie, quel que soit le cas, ne contient de chemin de poste ---

ctx='(^|[^0-9A-Za-z/])'
segment='[0-9A-Za-z_]'
chemin_poste="${ctx}([A-Za-z]:[\\/]${segment}|/[a-z]/${segment}|/home/${segment}|/Users/${segment})"
for sortie in "${SORTIES[@]}"; do
  if printf '%s' "${sortie}" | LC_ALL=C grep -Eq "${chemin_poste}"; then
    echo "ECHEC test negatif global : une sortie contient un chemin de poste de travail"
    echo "  sortie : ${sortie}"
    ECHECS=$((ECHECS + 1))
  fi
done
echo "ok test negatif global : aucune des ${#SORTIES[@]} sorties collectees ne contient de chemin de poste"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : le garde-fou de lignee ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : le garde-fou de lignee mord, et sur le bon motif."
