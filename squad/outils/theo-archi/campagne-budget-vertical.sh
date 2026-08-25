#!/usr/bin/env bash
# Passe mesurer-budget-vertical.py sur tous les jeux du catalogue servi, a une largeur donnee.
#
#   bash squad/outils/theo-archi/campagne-budget-vertical.sh <url_de_base> <largeur_px> <repertoire_de_sortie>
#
# Le perimetre est derive de catalogue.yaml, jamais d'une liste ecrite ici : un jeu neuf entre dans
# la campagne par le seul fait d'etre servi. Le nombre d'items attendus est derive du paquet, et le
# rapport de chaque jeu dit combien il en a reellement vus - une campagne qui en voit moins ne
# ment pas sur sa couverture, elle la declare.
#
# Prerequis nommes, jamais leur emplacement : un navigateur (variable CHROME ou PATH), le module
# python "websocket-client", et l'application deja servie a l'URL passee en argument.
set -uo pipefail

base="${1:?url de base attendue}"
largeur="${2:?largeur de viewport attendue}"
sortie="${3:?repertoire de sortie attendu}"

racine="$(cd "$(dirname "${BASH_SOURCE[0]}")/../../.." && pwd)"
catalogue="${racine}/adapters/adapter-content/src/main/resources/catalogue.yaml"
contenu="${racine}/adapters/adapter-content/src/main/resources/contenu"
mkdir -p "${sortie}"

paires="$(grep -E '^  - id:|^    paquet:' "${catalogue}" | paste - - \
  | sed -E 's/^  - id: //; s/\t    paquet: /\t/')"

while IFS=$'\t' read -r jeu paquet; do
  [ -n "${jeu}" ] || continue
  fichier="${contenu}/${paquet}.yaml"
  items="$(grep -c '^  - enonce:' "${fichier}" 2>/dev/null || echo 0)"
  [ "${items}" -gt 0 ] || { echo "saute ${jeu} : paquet combinatoire, aucun item a lire" >&2; continue; }
  attendus="${items}"
  [ "${attendus}" -le "${PLAFOND_ITEMS:-24}" ] || attendus="${PLAFOND_ITEMS:-24}"
  echo "== ${jeu} (${paquet}, ${items} items, ${attendus} vises) a ${largeur} px" >&2
  PAQUET="${fichier}" ITEMS_ATTENDUS="${attendus}" \
  PARTIES_MAX="${PARTIES_MAX:-4}" ECHECS="${ECHECS:-1}" \
    python "${racine}/squad/outils/theo-archi/mesurer-budget-vertical.py" \
      "${base}" "${jeu}" "${largeur}" "${sortie}/${jeu}-${largeur}.json" \
      > /dev/null 2>> "${sortie}/campagne-${largeur}.err"
done <<< "${paires}"

echo "campagne terminee, rapports dans ${sortie}" >&2
