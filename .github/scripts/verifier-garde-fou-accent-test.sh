#!/usr/bin/env bash
# Contre-exemples du controle de proprietaire du garde-fou de l'accent (ADR-0065 A2, point 1),
# sur le modele de verifier-prefixes-test.sh (ADR-0009).
#
# Deux materiaux. Un depot jetable, construit ici, ou chaque cas est un commit juge seul : il
# prouve que chaque chemin garde mord et que les chemins voisins ne mordent pas. Puis l'historique
# reel du depot : acfecb32 (sofia-sm, s24) doit etre refuse, c2802a32 et 02ba9e80 (theo-archi, s51)
# acceptes. Le second cas exige l'historique complet (fetch-depth: 0 dans le job prefixes) ; un
# commit absent est un echec, jamais un cas saute.
set -uo pipefail

SCRIPT="$(cd "$(dirname "$0")" && pwd)/verifier-garde-fou-accent.sh"
DEPOT_REEL="$(cd "$(dirname "$0")" && git rev-parse --show-toplevel)"
ECHECS=0
CAS=0

ADR="squad/decisions/adr/ADR-0065-un-mot-lu-par-un-enfant-est-juge-sur-un-lexique-exterieur-et-la-classe-des-homographes-reste-a-la-main.md"
TEST="adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunMotServiNeSEcarteDeSonAccentTest.java"
LEXIQUE="adapters/adapter-content/src/test/java/com/recreo/adapter/content/LexiqueFrancais.java"
CONTREEXEMPLE="adapters/adapter-content/src/test/resources/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/cedille/contenu/ce1-graphie-cedille.yaml"
POM_CONTENU="adapters/adapter-content/pom.xml"
POM_PARENT="pom.xml"
CONTENU="adapters/adapter-content/src/main/resources/contenu/ce1-exemple.yaml"

TEMPORAIRE="$(mktemp -d)"
trap 'rm -rf "${TEMPORAIRE}"' EXIT

g() {
  git -C "${DEPOT}" -c core.autocrlf=false -c user.name=test -c user.email=test@example.invalid -c commit.gpgsign=false "$@"
}

depot_neuf() {
  DEPOT="${TEMPORAIRE}/$1"
  git init -q "${DEPOT}"
  for f in "${ADR}" "${TEST}" "${LEXIQUE}" "${CONTREEXEMPLE}" "${POM_CONTENU}" "${CONTENU}"; do
    mkdir -p "${DEPOT}/$(dirname "${f}")"
    echo "socle" > "${DEPOT}/${f}"
  done
  printf '%s\n' '<java.version>21</java.version>' \
    '<french-pos-dict.version>0.7</french-pos-dict.version>' > "${DEPOT}/${POM_PARENT}"
  g add -A
  g commit -q -m "[THEO] socle"
}

# modifier <sujet> <fichier> : ajoute une ligne au fichier et commite ; HEAD est le commit cree.
modifier() {
  echo "$1" >> "${DEPOT}/$2"
  g add -A
  g commit -q -m "$1"
}

# verifier <intitule> <code attendu> <depot> <plage> [motif attendu dans la sortie]
verifier() {
  intitule="$1"
  attendu="$2"
  depot="$3"
  plage="$4"
  motif="${5:-}"
  CAS=$((CAS + 1))

  sortie="$(cd "${depot}" && bash "${SCRIPT}" "${plage}" 2>&1)"
  code=$?

  if [ "${code}" != "${attendu}" ]; then
    echo "ECHEC ${intitule} : code ${code}, attendu ${attendu}"
    echo "  sortie : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  if [ -n "${motif}" ] && ! printf '%s' "${sortie}" | grep -qF -- "${motif}"; then
    echo "ECHEC ${intitule} : sortie sans le motif attendu"
    echo "  attendu : ${motif}"
    echo "  sortie  : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  echo "ok ${intitule}"
}

depot_neuf principal
P="${DEPOT}"

modifier "[THEO] amende le test" "${TEST}"
verifier "theo-archi modifie le test : accepte" 0 "${P}" "HEAD^!" "1 commit(s) le touchent dans la plage, tous [THEO]."

modifier "[SOF] borne le test" "${TEST}"
verifier "un autre agent modifie le test : refuse, et le sujet est cite" 1 "${P}" "HEAD^!" "[SOF] borne le test"
DEBUT_DES_FAUTES="$(g rev-parse HEAD^)"

modifier "[YAN] ajoute un contre-exemple" "${CONTREEXEMPLE}"
verifier "un fichier sous le repertoire des contre-exemples : refuse" 1 "${P}" "HEAD^!" "[YAN] ajoute un contre-exemple"

modifier "[NAD] ajoute un mot au lexique" "${LEXIQUE}"
verifier "le lexique : refuse" 1 "${P}" "HEAD^!" "[NAD] ajoute un mot au lexique"

modifier "[CAM] amende l'ADR" "${ADR}"
verifier "l'ADR : refuse" 1 "${P}" "HEAD^!" "[CAM] amende l'ADR"

modifier "[LEA] <version>0.8</version>" "${POM_CONTENU}"
verifier "le pom d'adapter-content, sur une ligne qui ne nomme pas le lexique : refuse" 1 "${P}" "HEAD^!" "[LEA] <version>0.8</version>"

modifier "[MARC] correction [THEO] du lexique" "${LEXIQUE}"
verifier "un prefixe THEO ailleurs qu'au debut : refuse" 1 "${P}" "HEAD^!" "[MARC] correction [THEO] du lexique"

sed -i 's/0\.7/0.8/' "${P}/${POM_PARENT}"
g add -A
g commit -q -m "[MARC] monte french-pos-dict"
verifier "la version du lexique dans le pom parent : refusee" 1 "${P}" "HEAD^!" "[MARC] monte french-pos-dict"
FIN_DES_FAUTES="$(g rev-parse HEAD)"

modifier "[MARC] monte java" "${POM_PARENT}"
verifier "une autre ligne du pom parent : acceptee" 0 "${P}" "HEAD^!" "0 commit(s)"

modifier "[LEA] ajoute un paquet" "${CONTENU}"
verifier "un paquet de contenu : accepte" 0 "${P}" "HEAD^!" "0 commit(s)"
verifier "SHA^! juge le dernier commit, pas le dernier qui touche un chemin garde" 0 "${P}" "HEAD^!"

verifier "une plage qui couvre les fautes les refuse toutes" 1 "${P}" "${DEBUT_DES_FAUTES}..HEAD" "[NAD] ajoute un mot au lexique"
verifier "une plage qui commence apres les fautes ne les juge pas" 0 "${P}" "${FIN_DES_FAUTES}..HEAD"

verifier "la plage -1 est refusee" 2 "${P}" "-1" "plage commencant par un tiret"
verifier "une plage illisible est un echec, pas un vert" 128 "${P}" "inexistant..HEAD"

g checkout -q -b instance
modifier "[THEO] amendement sur une branche" "${TEST}"
g checkout -q -
modifier "[LEA] avance la branche principale" "${CONTENU}"
AVANT_FUSION="$(g rev-parse HEAD)"
g merge -q --no-ff -m "[SOF] fusion de instance" instance
verifier "une fusion par sofia-sm d'un amendement de theo-archi : acceptee" 0 "${P}" "${AVANT_FUSION}..HEAD" "1 commit(s) le touchent dans la plage, tous [THEO]."

AVANT_CONFLIT="$(g rev-parse HEAD)"
g checkout -q -b instance-bis
modifier "[THEO] premier amendement" "${TEST}"
g checkout -q -
modifier "[THEO] second amendement" "${TEST}"
g merge -q instance-bis > /dev/null 2>&1
echo "resolu par sofia-sm" > "${P}/${TEST}"
g add -A
g commit -q -m "[SOF] fusion de instance-bis, conflit resolu"
if ! g rev-parse -q --verify "HEAD^2" > /dev/null; then
  echo "ECHEC le cas de conflit n'a pas produit de commit de fusion : il ne prouverait rien"
  ECHECS=$((ECHECS + 1))
fi
verifier "une resolution de conflit par sofia-sm sur un chemin garde : refusee" 1 "${P}" "${AVANT_CONFLIT}..HEAD" "[SOF] fusion de instance-bis, conflit resolu"

depot_neuf renomme
R="${DEPOT}"
g rm -q "${LEXIQUE}"
g commit -q -m "[THEO] deplace le lexique"
verifier "un chemin garde absent de HEAD rend le controle rouge" 1 "${R}" "HEAD^!" "chemin garde absent de HEAD : ${LEXIQUE}"

verifier "historique reel : acfecb32 (sofia-sm, s24) refuse" 1 "${DEPOT_REEL}" "acfecb32^!" "[SOF] s24: lot 5b"
verifier "historique reel : c2802a32 (theo-archi, A1) accepte" 0 "${DEPOT_REEL}" "c2802a32^!" "1 commit(s) le touchent dans la plage, tous [THEO]."
verifier "historique reel : 02ba9e80 (theo-archi, A1 recommite) accepte" 0 "${DEPOT_REEL}" "02ba9e80^!" "1 commit(s) le touchent dans la plage, tous [THEO]."

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec sur ${CAS} : le controle du garde-fou de l'accent ne se comporte pas comme annonce."
  exit 1
fi
echo "${CAS} cas passent : le controle du garde-fou de l'accent mord, et sur le bon motif."
