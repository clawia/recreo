#!/usr/bin/env bash
# ADR-0065, amendement A2, point 1 : seul theo-archi modifie le garde-fou de l'accent.
#
# Refuse tout commit de la plage qui touche un chemin garde sans porter le prefixe [THEO]. La plage
# est choisie par l'appelant (.github/workflows/ci.yml) : "BASE..SHA", ou "SHA^!" pour juger un seul
# commit. Jamais "-1" : "git log -1 -- <chemins>" rend le dernier commit qui a touche ces chemins,
# pas le dernier commit.
#
# Les fusions ne sont pas exclues : la simplification d'historique de git log ecarte une fusion
# identique a l'un de ses parents sur les chemins gardes, et ne garde que celle qui y ecrit autre
# chose, une resolution de conflit. Celle-ci est jugee comme un commit.
#
# Ce que ce controle ne voit pas (ADR-0065 A2) : un prefixe [THEO] ecrit par un autre agent, un
# commit de theo-archi sur la mauvaise branche (02ba9e80), une resolution de conflit sur la ligne
# du lexique dans le pom parent (-G ne lit pas le diff d'une fusion).
set -euo pipefail

PLAGE="${1:?usage : verifier-garde-fou-accent.sh <plage de revisions>}"
case "${PLAGE}" in
  -*)
    echo "::error::plage commencant par un tiret : ${PLAGE}. Juger un seul commit s'ecrit SHA^!."
    exit 2
    ;;
esac

# Le pom d'adapter-content est garde entier : une ligne <version> ajoutee dans le bloc de la
# dependance ne nommerait pas french-pos-dict. Le pom parent ne l'est que par -G, plus bas.
CHEMINS=(
  "squad/decisions/adr/ADR-0065-un-mot-lu-par-un-enfant-est-juge-sur-un-lexique-exterieur-et-la-classe-des-homographes-reste-a-la-main.md"
  "adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunMotServiNeSEcarteDeSonAccentTest.java"
  "adapters/adapter-content/src/test/java/com/recreo/adapter/content/LexiqueFrancais.java"
  "adapters/adapter-content/src/test/resources/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest"
  "adapters/adapter-content/pom.xml"
)
POM_PARENT="pom.xml"
MOTIF_POM_PARENT="french-pos-dict"

# Un chemin renomme ou supprime rendrait le controle muet sans le rendre rouge.
for chemin in "${CHEMINS[@]}" "${POM_PARENT}"; do
  if [ -z "$(git ls-tree -r --name-only HEAD -- "${chemin}")" ]; then
    echo "::error::chemin garde absent de HEAD : ${chemin}. Le controle ne garderait plus rien ; mettre a jour sa liste dans le meme commit."
    exit 1
  fi
done

touches="$(git log --format='%h %s' "${PLAGE}" -- "${CHEMINS[@]}")"
touches_pom="$(git log -G "${MOTIF_POM_PARENT}" --format='%h %s' "${PLAGE}" -- "${POM_PARENT}")"

commits="$(printf '%s\n%s\n' "${touches}" "${touches_pom}" | sed '/^$/d' | sort -u)"
fautifs="$(printf '%s\n' "${commits}" | sed '/^$/d' | grep -Ev '^[0-9a-f]+ \[THEO\] ' || true)"

if [ -n "${fautifs}" ]; then
  while IFS= read -r ligne; do
    echo "::error::commit hors theo-archi sur le garde-fou de l'accent : ${ligne}"
  done <<< "${fautifs}"
  echo "ADR-0065 A2, point 1 : seul theo-archi modifie ce garde-fou. Un lot rouge route son besoin a theo-archi (point 2)."
  exit 1
fi

nombre="$(printf '%s\n' "${commits}" | sed '/^$/d' | wc -l | tr -d ' ')"
echo "Garde-fou de l'accent : ${nombre} commit(s) le touchent dans la plage, tous [THEO]."
