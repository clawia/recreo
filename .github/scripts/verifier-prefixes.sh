#!/usr/bin/env bash
# Refuse les sujets de commit sans prefixe d'agent connu.
#
# Lit les sujets sur l'entree standard, un par ligne. Le choix de la plage de commits appartient a
# l'appelant (.github/workflows/ci.yml), qui seul connait le contexte GitHub ; ce script ne juge que
# des sujets, ce qui le rend testable hors CI par verifier-prefixes-test.sh (ADR-0003).
set -euo pipefail

TAGS='CAM|SOF|THEO|MARC|LEA|YAN|REL|NAD|INES'

FAUTIFS=0
sujet=""
while IFS= read -r sujet || [ -n "${sujet}" ]; do
  [ -z "${sujet}" ] && continue
  if ! printf '%s' "${sujet}" | grep -Eq "^\[(${TAGS})\] "; then
    echo "::error::message sans prefixe d'agent connu : ${sujet}"
    FAUTIFS=$((FAUTIFS + 1))
  fi
done

if [ "${FAUTIFS}" -gt 0 ]; then
  echo "${FAUTIFS} commit(s) sans prefixe. Format attendu : [TAG] resume"
  echo "Tags valides : ${TAGS//|/, }"
  exit 1
fi
echo "Tous les commits portent un prefixe d'agent connu."
