#!/usr/bin/env bash
# Sort 0 si l'argument est un commit qui existe dans le depot courant, 1 sinon (chaine vide
# comprise).
#
# Utilise par .github/workflows/ci.yml pour decider si la base de comparaison annoncee par
# github.event.before ou github.event.pull_request.base.sha est encore un point de depart valide
# avant de construire une plage "${BASE}..${GITHUB_SHA}". Une reecriture d'historique suivie d'un
# push force rend cette base inatteignable sans qu'elle soit vide ni a zero (le cas de la branche
# neuve, deja gere ailleurs) : le seul moyen fiable de le savoir est de demander a git, pas de
# deviner sur la forme de la valeur.
set -uo pipefail

COMMIT="${1:-}"
[ -z "${COMMIT}" ] && exit 1

# "git cat-file -e" sort avec des codes fatals variables (128) suivant la raison de l'echec
# (objet absent, objet present mais d'un autre type) : le code de sortie de ce script est
# normalise a 0 ou 1, pas transmis tel quel.
if git cat-file -e "${COMMIT}^{commit}" 2>/dev/null; then
  exit 0
fi
exit 1
