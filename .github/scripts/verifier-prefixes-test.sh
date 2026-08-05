#!/usr/bin/env bash
# Contre-exemples du garde-fou de prefixes de commit (ADR-0003 applique a un garde-fou en shell).
#
# Chaque cas attendu en echec est le pendant d'un contre-exemple ArchUnit : il prouve que le garde-fou
# mord, et sur le bon motif. Ce script est execute en CI avant le garde-fou lui-meme, pour qu'une
# regex cassee soit rouge ici et non au prochain commit fautif.
set -uo pipefail

SCRIPT="$(dirname "$0")/verifier-prefixes.sh"
ECHECS=0

# verifier <intitule> <code attendu> <sujets sur l'entree standard> [motif attendu dans la sortie]
verifier() {
  intitule="$1"
  attendu="$2"
  entree="$3"
  motif="${4:-}"

  sortie="$(printf '%s' "${entree}" | bash "${SCRIPT}" 2>&1)"
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

verifier "un prefixe connu est accepte" 0 '[THEO] socle
' "Tous les commits portent un prefixe d'agent connu."

verifier "les neuf prefixes de la squad sont acceptes" 0 '[CAM] a
[SOF] b
[THEO] c
[MARC] d
[LEA] e
[YAN] f
[REL] g
[NAD] h
[INES] i
'

verifier "le prefixe INES est accepte" 0 '[INES] socle du design
' "Tous les commits portent un prefixe d'agent connu."

verifier "un prefixe inconnu est refuse et le sujet est cite" 1 '[BOB] socle
' "::error::message sans prefixe d'agent connu : [BOB] socle"

verifier "un sujet sans prefixe est refuse" 1 'socle initial
' "::error::message sans prefixe d'agent connu : socle initial"

verifier "un prefixe en minuscules est refuse" 1 '[theo] socle
' "::error::message sans prefixe d'agent connu : [theo] socle"

verifier "un prefixe sans espace est refuse" 1 '[THEO]socle
' "::error::message sans prefixe d'agent connu : [THEO]socle"

verifier "un prefixe sans crochets est refuse" 1 'THEO socle
'

verifier "un prefixe connu ailleurs qu'au debut est refuse" 1 'correction [THEO] socle
'

verifier "un seul sujet fautif parmi plusieurs suffit, et il est compte" 1 '[THEO] a
[BOB] b
[LEA] c
' "1 commit(s) sans prefixe."

verifier "deux sujets fautifs sont comptes tous les deux" 1 '[BOB] a
b
' "2 commit(s) sans prefixe."

verifier "la liste des tags valides est affichee en cas d'echec" 1 'b
' "Tags valides : CAM, SOF, THEO, MARC, LEA, YAN, REL, NAD, INES"

verifier "une ligne vide n'est pas un commit fautif" 0 '
[THEO] socle

'

verifier "un dernier sujet sans retour a la ligne final est bien juge" 1 '[BOB] socle'

verifier "aucun commit a juger n'est pas un echec" 0 ''

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : le garde-fou de prefixes ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif."
