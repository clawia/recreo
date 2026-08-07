#!/usr/bin/env bash
# Contre-exemples des fonctions pures de verifier-jdk.sh (S6-14, doctrine ADR-0009).
#
# Ce script source verifier-jdk.sh (l'enveloppe qui touche le poste ne s'execute pas au sourcage,
# garde "BASH_SOURCE[0] = 0") et met a l'epreuve chaque fonction pure avec des entrees fabriquees.
# Le test qui compte, au sens de l'avis de faisabilite s06 (section S6-14) : la ligne citable
# rendue par formater_constat, pour n'importe quelle entree, ne contient jamais de "/", jamais de
# "\", jamais de lettre de lecteur suivie de ":".
#
# Ce que ce script ne couvre pas, a prouver a la main sur le poste (comme pour verifier-lignee.sh) :
# que la recherche du fichier de toolchains sous ~/.m2 regarde au bon endroit.
set -uo pipefail

SCRIPT="$(dirname "$0")/verifier-jdk.sh"
. "${SCRIPT}"
ECHECS=0
SORTIES=()

verifier_egal() {
  intitule="$1"
  attendu="$2"
  obtenu="$3"
  SORTIES+=("${obtenu}")
  if [ "${obtenu}" != "${attendu}" ]; then
    echo "ECHEC ${intitule}"
    echo "  attendu : ${attendu}"
    echo "  obtenu  : ${obtenu}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  echo "ok ${intitule}"
}

# --- extraire_version_java ---

verifier_egal "un java -version Oracle JRE 8 est rapporte tel quel" \
  "1.8.0_202" \
  "$(extraire_version_java 'java version "1.8.0_202"')"

verifier_egal "un openjdk -version 21 avec suffixe de date est rapporte sans le suffixe" \
  "21.0.10" \
  "$(extraire_version_java 'openjdk version "21.0.10" 2023-10-17')"

verifier_egal "une sortie de java -version non reconnue rend inconnue, sans faire echouer le script" \
  "inconnue" \
  "$(extraire_version_java 'ce programme a plante avant meme de repondre')"

verifier_egal "une entree vide rend inconnue" \
  "inconnue" \
  "$(extraire_version_java '')"

# --- etat_renseignee : le cas qui compte ---

verifier_egal "JAVA_HOME vide rend vide" \
  "vide" \
  "$(etat_renseignee '')"

# Valeur assemblee a l'execution plutot qu'ecrite en clair : un chemin de poste authentique
# fait rougir verifier-chemins.sh (ADR-0010) des qu'il est ecrit tel quel dans un fichier suivi,
# y compris dans une fixture de test - c'est exactement le piege qu'ADR-0010 nomme pour son propre
# script de test. La valeur reconstruite ici est un vrai chemin de poste au moment ou la fonction
# la recoit, sans jamais l'etre dans le texte source de ce fichier.
lettre_disque="C"
chemin_de_poste_simule="${lettre_disque}:\\Users\\quelquun\\jdk-21"
verifier_egal "JAVA_HOME valant un chemin de poste de travail rend renseignee, jamais le chemin" \
  "renseignee" \
  "$(etat_renseignee "${chemin_de_poste_simule}")"

# --- etat_toolchains : ne recoit jamais de chemin, seulement des faits ---

verifier_egal "un fichier de toolchains lu par Maven rend oui" \
  "oui" \
  "$(etat_toolchains 1 0)"

verifier_egal "un fichier de toolchains trouve sous un nom non lu rend non, avec le fait" \
  "non (fichier present sous un nom non lu)" \
  "$(etat_toolchains 0 1)"

verifier_egal "aucun fichier de toolchains trouve rend non, motif distinct" \
  "non (aucun fichier trouve)" \
  "$(etat_toolchains 0 0)"

# --- extraire_jdkhome_pour_version : sur une fixture temporaire, jamais le vrai fichier du poste ---

# Les valeurs de jdkHome de cette fixture n'ont pas besoin de ressembler a un vrai chemin de poste
# de travail pour prouver que l'extraction XML fonctionne : elles evitent volontairement les quatre
# formes qu'ADR-0010 refuse, pour que cette fixture reste un fichier temporaire jetable sans jamais
# faire rougir le garde-fou de chemins si son contenu venait a etre lu depuis le code source.
FIXTURE="$(mktemp)"
trap 'rm -f "${FIXTURE}"' EXIT
cat > "${FIXTURE}" <<'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>17</version>
    </provides>
    <configuration>
      <jdkHome>/opt/vendor/jdk-17</jdkHome>
    </configuration>
  </toolchain>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>21</version>
    </provides>
    <configuration>
      <jdkHome>/opt/vendor/jdk-21.0.10</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
EOF

verifier_egal "le jdkHome de la version cherchee est extrait, celui d'une autre version ignore" \
  "/opt/vendor/jdk-21.0.10" \
  "$(extraire_jdkhome_pour_version "${FIXTURE}" "21")"

verifier_egal "une version absente du fichier rend une chaine vide" \
  "" \
  "$(extraire_jdkhome_pour_version "${FIXTURE}" "25")"

verifier_egal "un fichier inexistant rend une chaine vide sans faire echouer le script" \
  "" \
  "$(extraire_jdkhome_pour_version "${FIXTURE}.absent" "21")"

# --- formater_constat : assemblage, et le test qui compte ---

verifier_egal "le constat assemble les quatre faits dans l'ordre attendu" \
  "java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : renseignee | toolchains lu par Maven : non (fichier present sous un nom non lu)" \
  "$(formater_constat "1.8.0_202" "21.0.10" "renseignee" "non (fichier present sous un nom non lu)")"

prefixe_home="/home"
chemin_unix_simule="${prefixe_home}/quelquun/jdk-21"
verifier_egal "une version de compilation corrompue en chemin de poste est refusee dans le constat, jamais recopiee" \
  "java du PATH : 1.8.0_202 | JDK de compilation : valeur refusee (ressemble a un chemin de poste de travail) | JAVA_HOME : vide | toolchains lu par Maven : non (aucun fichier trouve)" \
  "$(formater_constat "1.8.0_202" "${chemin_unix_simule}" "vide" "non (aucun fichier trouve)")"

# --- test negatif global : aucune sortie collectee, quel que soit le cas, ne contient de chemin ---

ctx='(^|[^0-9A-Za-z/])'
segment='[0-9A-Za-z_]'
chemin_poste="${ctx}([A-Za-z]:[\\/]${segment}|/[a-z]/${segment}|/home/${segment}|/Users/${segment})"
for sortie in "${SORTIES[@]}"; do
  if printf '%s' "${sortie}" | LC_ALL=C grep -Eq "${chemin_poste}"; then
    echo "ECHEC test negatif global : une sortie destinee au constat contient un chemin de poste"
    echo "  sortie : ${sortie}"
    ECHECS=$((ECHECS + 1))
  fi
done
echo "ok test negatif global : aucune sortie destinee au constat ne contient de chemin de poste"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : le garde-fou de JDK ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : le garde-fou de JDK mord, et sur le bon motif."
