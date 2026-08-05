#!/usr/bin/env bash
# Contre-exemples de commit-atteignable.sh (ADR-0009 applique a la robustesse de la plage de
# prefixes, apres qu'un push force a rendu github.event.before inatteignable sans etre vide ni a
# zero : la CI a alors echoue sur "bad object" pour un motif etranger a un prefixe manquant).
#
# Le depot temoin est un vrai depot git jetable, cree a l'execution, jamais commite : il donne au
# script un vrai commit et un vrai objet inconnu a juger, sans dependre du contexte GitHub.
set -uo pipefail

SCRIPT="$(cd "$(dirname "$0")" && pwd)/commit-atteignable.sh"
DEPOT_TEMOIN="$(mktemp -d)"
trap 'rm -rf "${DEPOT_TEMOIN}"' EXIT
ECHECS=0

cd "${DEPOT_TEMOIN}"
git init --quiet
git config user.email "temoin@example.com"
git config user.name "Temoin"
git config core.autocrlf false
echo "premier" > fichier.txt
git add fichier.txt
git commit --quiet -m "premier commit"
PREMIER_SHA="$(git rev-parse HEAD)"
echo "second" >> fichier.txt
git add fichier.txt
git commit --quiet -m "second commit"
SECOND_SHA="$(git rev-parse HEAD)"
ARBRE_SHA="$(git rev-parse HEAD^{tree})"

# verifier <intitule> <code attendu> <argument>
verifier() {
  intitule="$1"
  attendu="$2"
  argument="$3"

  bash "${SCRIPT}" "${argument}" >/dev/null 2>&1
  code=$?

  if [ "${code}" != "${attendu}" ]; then
    echo "ECHEC ${intitule} : code ${code}, attendu ${attendu}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  echo "ok ${intitule}"
}

verifier "un commit ancetre atteignable est accepte" 0 "${PREMIER_SHA}"

verifier "le commit courant est accepte" 0 "${SECOND_SHA}"

verifier "un sha inconnu du depot est refuse" 1 "0123456789abcdef0123456789abcdef01234567"

verifier "une chaine vide est refusee" 1 ""

verifier "un sha qui existe mais n'est pas un commit (un arbre) est refuse" 1 "${ARBRE_SHA}"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : commit-atteignable.sh ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu."
