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
#
# Depuis le 2026-08-31, ce juge porte le nom de ce qu'il a toujours dit : le fichier que ce
# script lit, jamais celui que Maven lit. Le second est etat_fichier_de_toolchains_de_maven.

verifier_egal "un fichier de toolchains lu par ce script rend oui" \
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

verifier_egal "le constat assemble les cinq faits dans l'ordre attendu" \
  "java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : renseignee | toolchains lu par ce script : non (fichier present sous un nom non lu) | toolchains lu par Maven : oui (un autre que celui lu par ce script)" \
  "$(formater_constat "1.8.0_202" "21.0.10" "renseignee" "non (fichier present sous un nom non lu)" "oui (un autre que celui lu par ce script)")"

prefixe_home="/home"
chemin_unix_simule="${prefixe_home}/quelquun/jdk-21"
verifier_egal "une version de compilation corrompue en chemin de poste est refusee dans le constat, jamais recopiee" \
  "java du PATH : 1.8.0_202 | JDK de compilation : valeur refusee (ressemble a un chemin de poste de travail) | JAVA_HOME : vide | toolchains lu par ce script : non (aucun fichier trouve) | toolchains lu par Maven : non (aucun sous le repertoire utilisateur de la machine virtuelle)" \
  "$(formater_constat "1.8.0_202" "${chemin_unix_simule}" "vide" "non (aucun fichier trouve)" "non (aucun sous le repertoire utilisateur de la machine virtuelle)")"

# --- version_majeure_java et ligne_de_lancement (obstacle 23, 2026-08-29) ---
#
# Le cas qui compte : la ligne affichee avant de lancer Maven ne repete jamais la version demandee
# quand le JDK resolu en rapporte une autre. C'est le defaut mesure le 2026-08-28, ou l'enveloppe
# annoncait "toolchain jdk 21" en affichant 25.0.2 dans la meme phrase.

verifier_egal "une version 21 rend sa majeure" \
  "21" \
  "$(version_majeure_java "21.0.10")"

verifier_egal "une version sans micro rend sa majeure" \
  "25" \
  "$(version_majeure_java "25")"

verifier_egal "la forme historique 1.x rend la majeure historique, pas 1" \
  "8" \
  "$(version_majeure_java "1.8.0_202")"

verifier_egal "une version inconnue reste inconnue et ne rend jamais un nombre" \
  "inconnue" \
  "$(version_majeure_java "inconnue")"

verifier_egal "une version vide reste inconnue" \
  "inconnue" \
  "$(version_majeure_java "")"

verifier_egal "une version confirmee est annoncee comme telle" \
  "Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10" \
  "$(ligne_de_lancement "21" "21.0.10")"

verifier_egal "une toolchain jdk 21 qui designe un JDK 25 ne fait plus annoncer 21" \
  "Maven lance sur le JDK que designe la toolchain jdk 21, et ce JDK rapporte 25.0.2 : la declaration de version n'a pas ete confirmee" \
  "$(ligne_de_lancement "21" "25.0.2")"

verifier_egal "une toolchain jdk 21 qui designe un JRE 8 ne fait plus annoncer 21 non plus" \
  "Maven lance sur le JDK que designe la toolchain jdk 21, et ce JDK rapporte 1.8.0_202 : la declaration de version n'a pas ete confirmee" \
  "$(ligne_de_lancement "21" "1.8.0_202")"

verifier_egal "un JDK muet sur sa version ne fait rien confirmer" \
  "Maven lance sur le JDK que designe la toolchain jdk 21, et ce JDK rapporte inconnue : la declaration de version n'a pas ete confirmee" \
  "$(ligne_de_lancement "21" "inconnue")"


# --- verdict_de_version et message_de_refus_de_toolchain (s25, lot F, 2026-08-31) ---
#
# Le cas qui compte : l'enveloppe ne lance plus Maven quand le JDK designe dement la declaration.
# Ce sont ces deux fonctions qui portent le verdict et son message ; l'enveloppe ne fait que les
# appeler et sortir en erreur. Sans elles, quatre occurrences documentees d'un avertissement
# imprime puis ignore, dont une filtree par son propre auteur le jour de son ecriture.

verifier_egal "une toolchain jdk 21 qui designe un JDK 21 est conforme" \
  "conforme" \
  "$(verdict_de_version "21" "21.0.10")"

verifier_egal "une toolchain jdk 21 qui designe un JDK 25 n'est pas conforme - le cas mesure du poste" \
  "non conforme" \
  "$(verdict_de_version "21" "25.0.2")"

verifier_egal "une toolchain jdk 21 qui designe un JRE 8 n'est pas conforme" \
  "non conforme" \
  "$(verdict_de_version "21" "1.8.0_202")"

verifier_egal "une version illisible n'est jamais conforme : on ne verifie pas ce qu'on ne lit pas" \
  "non conforme" \
  "$(verdict_de_version "21" "inconnue")"

verifier_egal "une version vide n'est jamais conforme non plus" \
  "non conforme" \
  "$(verdict_de_version "21" "")"

verifier_egal "un 21.0.10 reste conforme a une demande de 21 malgre son micro" \
  "conforme" \
  "$(verdict_de_version "21" "21")"

verifier_egal "le refus nomme la version demandee et la version constatee des sa premiere ligne" \
  "erreur : refus de lancer Maven. La toolchain jdk 21 designe un JDK qui rapporte 25.0.2." \
  "$(message_de_refus_de_toolchain "21" "25.0.2" | head -n1)"

verifier_egal "le refus nomme la seconde cause, celle des douze rapports inexacts" \
  "  2. HOME et le repertoire utilisateur de la machine virtuelle ne designent pas le meme" \
  "$(message_de_refus_de_toolchain "21" "25.0.2" | sed -n '5p')"

# --- repertoire_utilisateur_de_la_machine_virtuelle ---
#
# La sortie de java -XshowSettings:properties est fabriquee ici : la fonction est pure, elle ne
# lance aucun java. Les valeurs de user.home evitent volontairement les quatre formes qu'ADR-0010
# refuse, pour que ce fichier de test reste lisible par verifier-chemins.sh.

proprietes_fabriquees='Property settings:
    java.version = 21.0.10
    user.home = /opt/comptes/quelquun
    user.language = fr'

verifier_egal "user.home est isole de la sortie de la machine virtuelle" \
  "/opt/comptes/quelquun" \
  "$(repertoire_utilisateur_de_la_machine_virtuelle "${proprietes_fabriquees}")"

verifier_egal "une sortie sans user.home rend une chaine vide, jamais une valeur devinee" \
  "" \
  "$(repertoire_utilisateur_de_la_machine_virtuelle 'Property settings:
    java.version = 21.0.10')"

verifier_egal "une entree vide rend une chaine vide" \
  "" \
  "$(repertoire_utilisateur_de_la_machine_virtuelle '')"

verifier_egal "une propriete dont le nom contient user.home sans l'etre n'est pas prise pour elle" \
  "/opt/comptes/quelquun" \
  "$(repertoire_utilisateur_de_la_machine_virtuelle 'Property settings:
    sun.java.command = user.home = /piege
    user.home = /opt/comptes/quelquun')"

# --- etat_fichier_de_toolchains_de_maven : le champ qui repondait sur un autre fichier ---

verifier_egal "le fichier de Maven trouve et identique a celui du script rend le fait complet" \
  "oui (le meme que celui lu par ce script)" \
  "$(etat_fichier_de_toolchains_de_maven 1 1)"

verifier_egal "le fichier de Maven trouve mais distinct est dit, au lieu d'etre confondu avec le premier" \
  "oui (un autre que celui lu par ce script)" \
  "$(etat_fichier_de_toolchains_de_maven 1 0)"

verifier_egal "aucun fichier sous le user.home de la machine virtuelle rend un motif distinct" \
  "non (aucun sous le repertoire utilisateur de la machine virtuelle)" \
  "$(etat_fichier_de_toolchains_de_maven 0 0)"

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
