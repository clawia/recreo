#!/usr/bin/env bash
# Contre-exemples du juge pur de mesurer-rendu.sh (ADR-0025, doctrine ADR-0009).
#
# Les deux fonctions pures de mesurer-rendu.sh ne lancent aucune commande : ce script les source
# (l'enveloppe qui touche le poste ne s'execute pas au sourcage, voir la garde "BASH_SOURCE[0] = 0"
# dans mesurer-rendu.sh) et leur fournit des entrees fabriquees. Ce qui est couvert de cette facon :
# tout le verdict d'invocation, aux deux bords du plancher de largeur et aux deux bords de l'inegalite
# de hauteur (ADR-0028), et la resolution du chemin du PNG de sortie sur ses trois formes.
#
# Une derniere section execute l'enveloppe contre un faux navigateur, parce qu'une fonction pure juste
# que l'enveloppe n'appellerait pas est exactement le defaut corrige en s10 (amendement d'ADR-0009).
#
# Ce qui n'est couvert par rien ici : que les options Chrome de l'enveloppe soient les bonnes, et qu'un
# vrai navigateur produise une image qui montre quelque chose - prouve une fois a la main sur
# l'application reelle, le plancher lui-meme remesure a cette occasion, la hauteur mesuree de la meme
# facon en s08, et le chemin de sortie en s10.
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

# --- le chemin du PNG de sortie, fonction pure (s10, symptome remonte par nadia-prof) ---
#
# Ces cas n'alimentent pas SORTIES, et c'est voulu : resoudre_png rend un chemin, c'est tout son
# travail, donc le test negatif global ci-dessus ne peut pas s'y appliquer. Les fixtures sont pour
# cette raison choisies pour ne ressembler a aucun chemin de poste reel, et la forme Windows est
# assemblee a l'execution (meme protection qu'au-dessus, ADR-0010).
ATELIER_FICTIF="/atelier/mesures"

# verifier_png <intitule> <chemin_png> <repertoire_courant> <code_attendu> <sortie_attendue>
# La sortie attendue est comparee en entier : une fonction qui rend un chemin se juge au chemin exact,
# pas a un motif contenu dedans.
verifier_png() {
  intitule="$1"
  chemin_png="$2"
  repertoire_courant="$3"
  attendu_code="$4"
  attendu_sortie="$5"

  sortie="$(resoudre_png "${chemin_png}" "${repertoire_courant}" 2>&1)"
  code=$?

  if [ "${code}" != "${attendu_code}" ]; then
    echo "ECHEC ${intitule} : code ${code}, attendu ${attendu_code}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  if [ "${sortie}" != "${attendu_sortie}" ]; then
    echo "ECHEC ${intitule} : la sortie n'est pas celle attendue"
    ECHECS=$((ECHECS + 1))
    return
  fi
  echo "ok ${intitule}"
}

verifier_png "un nom de fichier nu est rendu absolu contre le repertoire courant" \
  "capture.png" "${ATELIER_FICTIF}" 0 "${ATELIER_FICTIF}/capture.png"

verifier_png "un prefixe ./ est absorbe et non recopie dans le chemin rendu" \
  "./capture.png" "${ATELIER_FICTIF}" 0 "${ATELIER_FICTIF}/capture.png"

verifier_png "un relatif a plusieurs segments est rendu absolu en entier" \
  "captures/360/capture.png" "${ATELIER_FICTIF}" 0 "${ATELIER_FICTIF}/captures/360/capture.png"

verifier_png "un repertoire courant termine par une barre ne produit pas de barre doublee" \
  "capture.png" "${ATELIER_FICTIF}/" 0 "${ATELIER_FICTIF}/capture.png"

# Les deux formes deja absolues ne doivent surtout pas changer : elles arrivaient a destination avant
# la correction, et un prefixage aveugle du repertoire courant les casserait toutes les deux.
verifier_png "un absolu POSIX est rendu inchange" \
  "${ATELIER_FICTIF}/capture.png" "/ailleurs" 0 "${ATELIER_FICTIF}/capture.png"

lettre_disque_png="C"
png_windows_simule="${lettre_disque_png}:\\atelier\\capture.png"
png_windows_barres_simule="${lettre_disque_png}:/atelier/capture.png"
verifier_png "un absolu Windows a barres inverses est rendu inchange" \
  "${png_windows_simule}" "/ailleurs" 0 "${png_windows_simule}"
verifier_png "un absolu Windows a barres droites est rendu inchange" \
  "${png_windows_barres_simule}" "/ailleurs" 0 "${png_windows_barres_simule}"

verifier_png "un chemin de PNG vide est refuse" \
  "" "${ATELIER_FICTIF}" 1 "aucun fichier de sortie fourni : le troisieme argument est le PNG a ecrire."

verifier_png "un relatif sans repertoire courant connu est refuse" \
  "capture.png" "" 1 "repertoire courant inconnu : un chemin de PNG relatif ne peut pas etre rendu absolu."

# --- l'enveloppe, eprouvee par un faux navigateur (s10) ---
#
# Ce que ces cas ajoutent, et pourquoi ils ne sont pas redondants avec ceux du dessus : resoudre_png
# peut etre parfaitement juste et n'etre appelee par personne. C'etait exactement l'etat du script
# avant la correction, ou le PNG partait au navigateur tel que l'appelant l'avait ecrit. Aucun cas de
# fonction pure ne peut voir ce cablage-la ; il faut executer l'enveloppe.
#
# Le faux navigateur reproduit le comportement constate du vrai, et cette fidelite est le seul endroit
# ou ce test peut mentir a son auteur : un stub ecrit en shell resoudrait un chemin relatif comme le
# shell le fait, donc il serait vert sur le script fautif et ne prouverait rien. Il refuse donc
# explicitement tout chemin non absolu, en se taisant comme le vrai se taisait - rien d'ecrit, code de
# sortie 0. Verifie a la main : sur le script d'avant la correction, ces cas rougissent.
#
# Ce que ces cas ne prouvent toujours pas : que les options Chrome soient les bonnes, ni qu'un vrai
# navigateur ecrive une image qui montre quelque chose. Cela reste la preuve manuelle d'ADR-0009.
SCRIPT_ABSOLU="$(cd "$(dirname "$0")" && pwd)/mesurer-rendu.sh"
ATELIER="$(mktemp -d)"
trap 'rm -rf "${ATELIER}"' EXIT
FAUX_NAVIGATEUR="${ATELIER}/faux-navigateur.sh"
cat > "${FAUX_NAVIGATEUR}" <<'STUB'
#!/usr/bin/env bash
set -uo pipefail
cible=""
for arg in "$@"; do
  case "${arg}" in --screenshot=*) cible="${arg#--screenshot=}" ;; esac
done
case "${cible}" in
  /* | [A-Za-z]:[\\/]*) ;;
  *) exit 0 ;;
esac
if command -v cygpath >/dev/null 2>&1; then cible="$(cygpath -u "${cible}")"; fi
printf 'PNG' > "${cible}" || exit 0
exit 0
STUB
chmod +x "${FAUX_NAVIGATEUR}"

# verifier_enveloppe <intitule> <chemin_png_demande> <fichier_attendu> <code_attendu> [motif attendu]
#   fichier_attendu : chemin, cote shell, ou l'appelant s'attend a trouver sa capture ; vide si aucun
#                     fichier ne doit etre ecrit. Jamais affiche.
#   motif attendu   : exige pour tout refus. Le code 2 est aussi celui d'une erreur d'usage et d'un
#                     navigateur introuvable : sans motif, un cas de refus passerait pour une raison
#                     etrangere a ce qu'il pretend eprouver.
verifier_enveloppe() {
  intitule="$1"
  png_demande="$2"
  fichier_attendu="$3"
  attendu_code="$4"
  motif="${5:-}"

  [ -n "${fichier_attendu}" ] && rm -f "${fichier_attendu}"
  sortie="$(cd "${ATELIER}" && CHROME="${FAUX_NAVIGATEUR}" \
    bash "${SCRIPT_ABSOLU}" 360 "about:blank" "${png_demande}" 640 2>&1)"
  code=$?

  if [ "${code}" != "${attendu_code}" ]; then
    echo "ECHEC ${intitule} : code ${code}, attendu ${attendu_code}"
    echo "  sortie : ${sortie}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  if [ -n "${fichier_attendu}" ] && [ ! -s "${fichier_attendu}" ]; then
    echo "ECHEC ${intitule} : aucune capture a l'endroit demande par l'appelant"
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

verifier_enveloppe "un PNG relatif est ecrit dans le repertoire d'ou la commande est lancee" \
  "capture-relative.png" "${ATELIER}/capture-relative.png" 0

verifier_enveloppe "un PNG absolu POSIX continue d'etre ecrit" \
  "${ATELIER}/capture-absolue.png" "${ATELIER}/capture-absolue.png" 0

if command -v cygpath >/dev/null 2>&1; then
  verifier_enveloppe "un PNG donne sous forme Windows continue d'etre ecrit" \
    "$(cygpath -w "${ATELIER}/capture-windows.png")" "${ATELIER}/capture-windows.png" 0
else
  echo "ok (sans objet hors shell POSIX sur Windows) un PNG donne sous forme Windows"
fi

# Meme classe de defaillance silencieuse que celle corrigee, autre cause : le refus doit etre explicite
# et arriver avant que le navigateur ne soit lance.
verifier_enveloppe "un dossier de sortie inexistant est refuse explicitement" \
  "dossier-absent/capture.png" "" 2 "le dossier de sortie du PNG demande n'existe pas"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : l'outil de mesure de rendu ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : une invocation qui ne mesure pas ce qu'elle pretend mesurer est refusee, et la capture est ecrite la ou l'appelant l'a demandee."
