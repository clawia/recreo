#!/usr/bin/env bash
# Contre-exemples du juge pur de verifier-provenance-lots.sh.
#
# Meme methode que outils/verifier-lignee-test.sh : juger_provenance ne lance aucune commande, ce
# script la source et lui fournit des entrees fabriquees. Cas reel qui a motive ce garde-fou : s19,
# lot s19-ines, dont la tete annoncee (680c1e9) avait ete remplacee par un amendement de message
# (a831871) sans que l'arbre change - la verification doit accepter la nouvelle tete quand on la
# lui donne, et refuser un cas ou tete annoncee et tete reelle divergent reellement.
set -uo pipefail

SCRIPT="$(dirname "$0")/verifier-provenance-lots.sh"
. "${SCRIPT}"
ECHECS=0

# verifier <intitule> <branche> <sha_attendu> <sha_reel> <code_ancetre_commun> <code_attendu> [motif]
verifier() {
  intitule="$1"; branche="$2"; sha_attendu="$3"; sha_reel="$4"
  code_ancetre_commun="$5"; attendu="$6"; motif="${7:-}"

  sortie="$(juger_provenance "${branche}" "${sha_attendu}" "${sha_reel}" "${code_ancetre_commun}" 2>&1)"
  code=$?

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
  echo "ok ${intitule}"
}

verifier "aucune tete annoncee, lignee saine : accepte" \
  "s19-nadia" "" "74ad820deadbeef0000000000000000000000000" 0 0 "lignee confirmee"

verifier "tete annoncee et tete reelle identiques, lignee saine : accepte" \
  "s19-ines" "a831871" "a831871" 0 0 "lignee confirmee"

verifier "tete annoncee en forme courte, tete reelle en forme longue : accepte (prefixe)" \
  "s19-ines" "a831871" "a831871032ed333e9144b88735a342c20f4c7c8d" 0 0 "lignee confirmee"

# --- cas reel de s19-ines : un amendement remplace la tete annoncee sans toucher l'arbre. Une
#     verification qui recopierait l'ancienne tete sans la reverifier resterait bloquee dessus. ---
verifier "une tete annoncee perimee par un amendement est signalee, pas relayee" \
  "s19-ines" "680c1e9" "a831871" 0 1 "tete reelle"

verifier "branche introuvable : refusee et nommee" \
  "s19-fantome" "" "" 0 1 "introuvable"

verifier "lignee etrangere (aucun ancetre commun avec round-two) : refusee" \
  "main" "" "aa2b356" 1 1 "ne descend pas de round-two"

verifier "reference round-two introuvable dans ce checkout : refusee distinctement" \
  "s19-nadia" "" "74ad820" 128 1 "impossible de verifier"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : le garde-fou de provenance des lots ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent."
