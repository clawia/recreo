#!/usr/bin/env bash
# Contre-exemples du juge pur de mesurer-rendu.sh (ADR-0025, doctrine ADR-0009).
#
# juger_invocation ne lance aucune commande : ce script la source (l'enveloppe qui touche le poste ne
# s'execute pas au sourcage, voir la garde "BASH_SOURCE[0] = 0" dans mesurer-rendu.sh) et lui fournit
# des entrees fabriquees. Ce que ce script couvre : tout le verdict, aux deux bords du plancher de
# largeur et aux deux bords de l'inegalite de hauteur (ADR-0028). Ce qu'il ne couvre pas : que les
# options Chrome de l'enveloppe soient les bonnes - prouve une fois a la main sur l'application reelle,
# le plancher lui-meme remesure a cette occasion, et la hauteur mesuree de la meme facon en s08.
set -uo pipefail

SCRIPT="$(dirname "$0")/mesurer-rendu.sh"
. "${SCRIPT}"
ECHECS=0
SORTIES=()

# Hauteurs valides utilisees par les cas de largeur : la hauteur par defaut de l'enveloppe, c'est-a-dire
# le couple exact que produit une invocation sans quatrieme argument. Les cas qui eprouvent la hauteur
# passent par verifier_hauteur, plus bas.
HAUTEUR_CSS_VALIDE=1400
HAUTEUR_FENETRE_VALIDE=1400

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

  sortie="$(juger_invocation "${largeur_css}" "${largeur_fenetre}" "${profil}" \
    "${HAUTEUR_CSS_VALIDE}" "${HAUTEUR_FENETRE_VALIDE}" 2>&1)"
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

# verifier_hauteur <intitule> <hauteur_css> <hauteur_fenetre> <code_attendu> [motif attendu]
# Les largeurs sont fixees a un couple valide : ces cas-la n'eprouvent que le pli (ADR-0028).
verifier_hauteur() {
  intitule="$1"
  hauteur_css="$2"
  hauteur_fenetre="$3"
  attendu="$4"
  motif="${5:-}"

  sortie="$(juger_invocation 360 600 "${PROFIL}" "${hauteur_css}" "${hauteur_fenetre}" 2>&1)"
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
  echo "ok ${intitule}"
}

# --- acceptation, dont le bord bas du plancher ---

verifier "la mesure a 360 px CSS dans une fenetre de 600 px est acceptee" \
  360 600 "${PROFIL}" 0 "largeur CSS mesuree : 360 px"

# Ce cas et le suivant sont le couple qui fixe l'inegalite. Le plancher cesse de s'appliquer A PARTIR
# de 516 px (mesure yanis-qa, remesuree par theo-archi : 515 demande rend 500x..., 516 demande rend
# 500x... puis 520 rend 504). Une fenetre parente egale au plancher est donc valide : un juge ecrit
# avec "-gt" au lieu de "-ge" refuserait ce cas, et aucun autre test ne le dirait.
verifier "une fenetre parente egale au plancher est acceptee" \
  360 516 "${PROFIL}" 0 "fenetre parente : 516 x"

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

# --- le pli (ADR-0028) : la hauteur de reference de DEC-0016, et les deux bords de son inegalite ---

verifier_hauteur "la hauteur de reference de DEC-0016 (640 px) est acceptee et le pli est nomme" \
  640 1400 0 "pli a y = 640 px"

# Ce cas et le suivant sont le couple qui fixe l'inegalite de hauteur, et il n'est pas academique :
# 1400 x 1400 est exactement ce que produit une invocation sans quatrieme argument, donc un juge ecrit
# avec "-ge" au lieu de "-gt" refuserait les deux mesures deja citees dans le depot (ADR-0025,
# DEC-0016). Un pixel de plus est en revanche un pli invente : l'image serait coupee par la fenetre.
verifier_hauteur "une hauteur CSS egale a la fenetre parente est acceptee" \
  1400 1400 0 "invocation valide"

verifier_hauteur "un pixel de plus que la fenetre parente est refuse" \
  1401 1400 1 "coupee par la fenetre et non par le pli demande"

verifier_hauteur "une hauteur CSS nulle est refusee" 0 1400 1 "hauteur CSS demandee invalide"
verifier_hauteur "une hauteur CSS negative est refusee" -640 1400 1 "hauteur CSS demandee invalide"
verifier_hauteur "une hauteur CSS non numerique est refusee" "640px" 1400 1 "hauteur CSS demandee invalide"
verifier_hauteur "une hauteur de fenetre non numerique est refusee" 640 "haut" 1 "hauteur de fenetre parente invalide"

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
