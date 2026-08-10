#!/usr/bin/env bash
# Contre-exemples du juge pur de mesurer-rendu.sh (ADR-0025, doctrine ADR-0009).
#
# juger_invocation ne lance aucune commande : ce script la source (l'enveloppe qui touche le poste ne
# s'execute pas au sourcage, voir la garde "BASH_SOURCE[0] = 0" dans mesurer-rendu.sh) et lui fournit
# des entrees fabriquees. Ce que ce script couvre : tout le verdict, aux deux bords du plancher. Ce
# qu'il ne couvre pas : que les options Chrome de l'enveloppe soient les bonnes - prouve une fois a la
# main sur l'application reelle, et le plancher lui-meme remesure a cette occasion.
set -uo pipefail

SCRIPT="$(dirname "$0")/mesurer-rendu.sh"
. "${SCRIPT}"
ECHECS=0
SORTIES=()

# verifier <intitule> <largeur_css> <largeur_fenetre> <profil> <code_attendu>
#          [motif attendu] [motif interdit]
verifier() {
  intitule="$1"
  largeur_css="$2"
  largeur_fenetre="$3"
  profil="$4"
  attendu="$5"
  motif="${6:-}"
  interdit="${7:-}"

  sortie="$(juger_invocation "${largeur_css}" "${largeur_fenetre}" "${profil}" 2>&1)"
  code=$?
  SORTIES+=("${sortie}")

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
  if [ -n "${interdit}" ] && printf '%s' "${sortie}" | grep -qF "${interdit}"; then
    echo "ECHEC ${intitule} : sortie contenant un motif interdit"
    echo "  interdit : ${interdit}"
    echo "  sortie   : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  echo "ok ${intitule}"
}

PROFIL="un-profil-fourni"

# --- acceptation, dont le bord bas du plancher ---

verifier "la mesure a 360 px CSS dans une fenetre de 600 px est acceptee" \
  360 600 "${PROFIL}" 0 "largeur CSS mesuree : 360 px"

# Ce cas et le suivant sont le couple qui fixe l'inegalite. Le plancher cesse de s'appliquer A PARTIR
# de 516 px (mesure yanis-qa, remesuree par theo-archi : 515 demande rend 500x..., 516 demande rend
# 500x... puis 520 rend 504). Une fenetre parente egale au plancher est donc valide : un juge ecrit
# avec "-gt" au lieu de "-ge" refuserait ce cas, et aucun autre test ne le dirait.
verifier "une fenetre parente egale au plancher est acceptee" \
  360 516 "${PROFIL}" 0 "fenetre parente : 516 px"

verifier "une largeur CSS egale a la fenetre parente est acceptee" \
  516 516 "${PROFIL}" 0 "invocation valide"

# --- refus : le piege qui a fabrique un symptome inexistant pendant le sprint 6 ---

verifier "un pixel sous le plancher est refuse" \
  360 515 "${PROFIL}" 1 "sous le plancher de 516 px"

verifier "la commande fautive du sprint (fenetre = largeur cherchee, 360 px) est refusee" \
  360 360 "${PROFIL}" 1 "decoupage a gauche d'une mise en page a 500 px"

verifier "une fenetre de 200 px est refusee" \
  200 200 "${PROFIL}" 1 "Mesure refusee"

# --- refus : le second piege etabli, la session Chrome deja ouverte ---

verifier "l'absence de profil isole est refusee" \
  360 600 "" 1 "s'attache a une session Chrome deja ouverte"

# --- refus : entrees qui ne sont pas des largeurs ---

verifier "une largeur CSS nulle est refusee" 0 600 "${PROFIL}" 1 "entier positif"
verifier "une largeur CSS negative est refusee" -360 600 "${PROFIL}" 1 "entier positif"
verifier "une largeur CSS non numerique est refusee" "360px" 600 "${PROFIL}" 1 "entier positif"
verifier "une largeur de fenetre non numerique est refusee" 360 "grand" "${PROFIL}" 1 "entier de pixels"

# --- refus : une iframe plus large que la fenetre qui la contient serait elle-meme decoupee ---

verifier "une largeur CSS superieure a la fenetre parente est refusee" \
  700 600 "${PROFIL}" 1 "l'iframe serait elle-meme decoupee"

# --- le test qui compte : jamais de chemin dans le message, meme quand le profil en est un ---
#
# Les valeurs sont assemblees a l'execution plutot qu'ecrites en clair : un chemin de poste
# authentique fait rougir verifier-chemins.sh (ADR-0010) des qu'il est ecrit tel quel dans un fichier
# suivi, y compris dans une fixture. Meme protection que dans verifier-lignee-test.sh.
lettre_disque="C"
profil_windows_simule="${lettre_disque}:\\Users\\quelquun\\profil-chrome"
verifier "un profil Windows n'est jamais recopie dans un refus" \
  360 360 "${profil_windows_simule}" 1 "" "quelquun"
verifier "un profil Windows n'est jamais recopie dans une acceptation" \
  360 600 "${profil_windows_simule}" 0 "profil isole : oui" "quelquun"

prefixe_home="/home"
profil_unix_simule="${prefixe_home}/quelquun/profil-chrome"
verifier "un profil Git Bash n'est jamais recopie" \
  360 600 "${profil_unix_simule}" 0 "profil isole : oui" "quelquun"

# --- test negatif global : aucune sortie, quel que soit le cas, ne contient de chemin de poste ---

ctx='(^|[^0-9A-Za-z/])'
segment='[0-9A-Za-z_]'
chemin_poste="${ctx}([A-Za-z]:[\\/]${segment}|/[a-z]/${segment}|/home/${segment}|/Users/${segment})"
for sortie in "${SORTIES[@]}"; do
  if printf '%s' "${sortie}" | LC_ALL=C grep -Eq "${chemin_poste}"; then
    echo "ECHEC test negatif global : une sortie contient un chemin de poste de travail"
    echo "  sortie : ${sortie}"
    ECHECS=$((ECHECS + 1))
  fi
done
echo "ok test negatif global : aucune des ${#SORTIES[@]} sorties collectees ne contient de chemin de poste"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : le juge d'invocation de mesure ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : une invocation qui ne mesure pas ce qu'elle pretend mesurer est refusee."
