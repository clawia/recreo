#!/usr/bin/env bash
# Verifie la provenance d'un lot avant que sofia-sm ne le fusionne (retro de s18, point 3 :
# "verifie la lignee d'un worktree avant de fusionner, meme si l'agent dit l'avoir fait").
#
# Different de outils/verifier-lignee.sh : ce dernier juge le worktree courant depuis l'interieur
# (HEAD detache ou non, branche courante). Sofia ne fusionne jamais depuis le worktree d'un autre
# agent : elle juge des branches distantes, par leur nom, depuis son propre checkout de round-two.
# Deux questions differentes, deux scripts.
#
# Meme decoupage que verifier-lignee.sh : juger_provenance est une fonction pure, testee dans
# verifier-provenance-lots-test.sh avec des entrees fabriquees. L'enveloppe, en bas de ce fichier,
# est la seule partie qui appelle git.
set -uo pipefail

# juger_provenance <branche> <sha_attendu> <sha_reel> <code_ancetre_commun>
#   branche            : nom de la branche du lot (pour le message uniquement).
#   sha_attendu        : sha annonce pour la tete du lot (forme courte ou longue), ou chaine vide
#                         si aucun n'est annonce.
#   sha_reel           : sortie de "git rev-parse <branche>".
#   code_ancetre_commun : code de sortie de "git merge-base round-two <branche>"
#                         (0 = un ancetre commun existe, 1 = aucun, autre = round-two introuvable).
# Rend 0 et un message de confirmation si la tete correspond (quand elle est annoncee) et si la
# lignee rejoint round-two. Rend 1 et un motif explicite sinon. Ne lance aucune commande.
juger_provenance() {
  branche="$1"
  sha_attendu="$2"
  sha_reel="$3"
  code_ancetre_commun="$4"

  if [ -z "${sha_reel}" ]; then
    echo "branche '${branche}' introuvable : verification refusee." >&2
    return 1
  fi

  # sha_attendu peut etre une forme courte (celle qu'on cite dans un brief) : comparaison en
  # prefixe, pas en egalite stricte, sinon toute forme courte saine serait rejetee a tort.
  if [ -n "${sha_attendu}" ] && [ "${sha_reel#"${sha_attendu}"}" = "${sha_reel}" ]; then
    echo "branche '${branche}' : tete reelle ${sha_reel} differente de la tete annoncee ${sha_attendu} - a re-verifier avant fusion, pas a relayer telle quelle." >&2
    return 1
  fi

  case "${code_ancetre_commun}" in
    0)
      echo "branche '${branche}' : lignee confirmee, un ancetre commun avec round-two existe."
      return 0
      ;;
    1)
      echo "branche '${branche}' ne descend pas de round-two (aucun ancetre commun) : lot a rejeter, pas a fusionner." >&2
      return 1
      ;;
    *)
      echo "branche '${branche}' : impossible de verifier, la reference round-two est introuvable dans ce checkout." >&2
      return 1
      ;;
  esac
}

# sha_est_atteignable_depuis <sha> <branche>
#   Rend 0 si <sha> fait partie de l'historique de <branche> (donc a ne jamais fusionner s'il s'agit
#   d'un sha remplace par un amendement, comme cf78a0d pour s19-nadia), 1 sinon.
sha_est_atteignable_depuis() {
  git merge-base --is-ancestor "$1" "$2" >/dev/null 2>&1
}

# --- enveloppe : la seule partie qui touche le poste, non testee (meme limite qu'ADR-0009) ---
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
  if [ "$#" -eq 0 ]; then
    echo "usage : $0 <branche[=sha_attendu]> [...]" >&2
    exit 2
  fi

  echecs=0
  for entree in "$@"; do
    branche="${entree%%=*}"
    if [ "${entree}" = "${branche}" ]; then
      sha_attendu=""
    else
      sha_attendu="${entree#*=}"
    fi

    sha_reel="$(git rev-parse "${branche}" 2>/dev/null || true)"
    git merge-base round-two "${branche}" >/dev/null 2>&1
    code_ancetre_commun=$?

    if ! juger_provenance "${branche}" "${sha_attendu}" "${sha_reel}" "${code_ancetre_commun}"; then
      echecs=$((echecs + 1))
    fi
  done

  if [ "${echecs}" -gt 0 ]; then
    echo "${echecs} lot(s) rejete(s) par la verification de provenance." >&2
    exit 1
  fi
  echo "tous les lots verifies descendent de round-two avec la tete annoncee."
  exit 0
fi
