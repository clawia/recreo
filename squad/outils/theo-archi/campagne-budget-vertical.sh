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
# s21 (theo-archi, 2026-08-27), et c'est le correctif qui rend l'omission bruyante. Ce script
# ignorait le code de sortie de chaque mesure et ecrivait "campagne terminee" quoi qu'il arrive :
# le 2026-08-27, 8 jeux sur 17 ont echoue - le navigateur repondant sur le port fixe etait celui
# d'un autre agent - et la campagne s'est declaree terminee sans nommer un seul des 8. Un jeu est
# desormais **perdu** si sa mesure sort en erreur ou si elle n'ecrit pas de rapport lisible ; les
# jeux perdus sont nommes un par un et la campagne sort en erreur. Le nombre d'items vus, lui,
# reste declare et non juge : un tirage voit legitimement moins d'items que le paquet n'en porte
# (23 sur 24 sur cp-soustractions le 2026-08-27), et en faire un echec serait un rouge sur une
# mesure saine.
#
# Prerequis nommes, jamais leur emplacement : un navigateur (variable CHROME ou PATH), le module
# python "websocket-client", l'application deja servie a l'URL passee en argument, et un
# interpreteur Python 3 - designe par la variable PYTHON quand le nom "python" ne convient pas au
# poste. Cette variable est aussi ce qui rend la comptabilite des jeux perdus testable par une
# entree fabriquee, sans navigateur.
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

mesures=0
perdus=""
while IFS=$'\t' read -r jeu paquet; do
  [ -n "${jeu}" ] || continue
  fichier="${contenu}/${paquet}.yaml"
  items="$(grep -c '^  - enonce:' "${fichier}" 2>/dev/null || echo 0)"
  [ "${items}" -gt 0 ] || { echo "saute ${jeu} : paquet combinatoire, aucun item a lire" >&2; continue; }
  attendus="${items}"
  [ "${attendus}" -le "${PLAFOND_ITEMS:-24}" ] || attendus="${PLAFOND_ITEMS:-24}"
  mesures=$((mesures + 1))
  rapport="${sortie}/${jeu}-${largeur}.json"
  rm -f "${rapport}"
  echo "== ${jeu} (${paquet}, ${items} items, ${attendus} vises) a ${largeur} px" >&2
  PAQUET="${fichier}" ITEMS_ATTENDUS="${attendus}" \
  PARTIES_MAX="${PARTIES_MAX:-4}" ECHECS="${ECHECS:-1}" \
    "${PYTHON:-python}" "${racine}/squad/outils/theo-archi/mesurer-budget-vertical.py" \
      "${base}" "${jeu}" "${largeur}" "${rapport}" \
      > /dev/null 2>> "${sortie}/campagne-${largeur}.err"
  code=$?
  if [ "${code}" -ne 0 ]; then
    perdus="${perdus}${jeu} (sortie ${code})"$'\n'
  elif [ ! -s "${rapport}" ]; then
    perdus="${perdus}${jeu} (aucun rapport ecrit)"$'\n'
  fi
done <<< "${paires}"

if [ -n "${perdus}" ]; then
  nombre="$(printf '%s' "${perdus}" | grep -c .)"
  echo "CAMPAGNE INCOMPLETE : ${nombre} jeu(x) perdu(s) sur ${mesures} mesure(s) tentee(s)." >&2
  printf '%s' "${perdus}" | sed 's/^/  perdu : /' >&2
  echo "Motifs dans ${sortie}/campagne-${largeur}.err. Aucun chiffre de cette campagne n'est citable :" >&2
  echo "une couverture partielle non declaree est l'ecart muet que ce script fermait mal." >&2
  exit 1
fi

echo "campagne terminee, ${mesures} jeu(x) mesure(s), rapports dans ${sortie}" >&2
