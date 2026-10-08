#!/usr/bin/env bash
# Contre-exemples de l'enveloppe outils/mvn-jdk21.sh (s25, lot F, 2026-08-31).
#
# Pourquoi une enveloppe est testee ici, alors qu'ADR-0009 les prouve d'habitude a la main.
# La dette payee par ce lot n'etait pas dans un juge : le juge "cette version convient-elle" a
# toujours pu s'ecrire, et verifier-jdk-test.sh le met a l'epreuve juste a cote. Ce qui manquait
# etait le cablage - l'enveloppe avait la version en main, l'imprimait, et lancait Maven quand
# meme. Un harnais qui ne testerait que le juge resterait vert le jour ou quelqu'un retirerait le
# "exit 1" : ce serait exactement le garde-fou decoratif que ce depot sanctionne depuis s05, et la
# forme meme du defaut qu'on corrige. Le harnais teste donc ce qui a manque quatre fois : que
# Maven ne soit pas lance.
#
# Le poste n'est jamais touche. Chaque cas fabrique un HOME jetable, un fichier de toolchains, une
# racine de JDK dont le bin/java rend la version qu'on veut, et un mvn temoin place en tete de
# PATH. Rien de tout cela ne depend du JDK, de Maven ni du fichier de toolchains de la machine qui
# execute ce test : il passe donc en CI comme sur un poste.
set -uo pipefail

ENVELOPPE="$(cd "$(dirname "$0")" && pwd)/mvn-jdk21.sh"
ECHECS=0
SORTIES=()

BAC="$(mktemp -d)"
trap 'rm -rf "${BAC}"' EXIT

# Un mvn temoin : il n'execute rien, il laisse une trace de son lancement. C'est cette trace, et
# elle seule, qui distingue "l'enveloppe a refuse" de "l'enveloppe a imprime puis lance".
mkdir -p "${BAC}/bin"
cat > "${BAC}/bin/mvn" <<'MVN'
#!/usr/bin/env bash
echo "lance" > "${TEMOIN_MVN}"
exit 0
MVN
chmod +x "${BAC}/bin/mvn"

# fabriquer_jdk <nom> <version_rapportee>
# Rend la racine d'un JDK jetable dont bin/java rend la version demandee. bin/javac existe, parce
# que l'enveloppe verifie sa presence avant de demander sa version.
fabriquer_jdk() {
  racine="${BAC}/$1"
  mkdir -p "${racine}/bin"
  printf '#!/usr/bin/env bash\nprintf %s"openjdk version \\"%s\\" 2026-01-01"%s >&2\n' "'" "$2" "'" \
    > "${racine}/bin/java"
  chmod +x "${racine}/bin/java"
  : > "${racine}/bin/javac"
  chmod +x "${racine}/bin/javac"
  printf '%s' "${racine}"
}

# fabriquer_java_du_path <repertoire_utilisateur_rapporte>
# Le java du PATH est ce a quoi l'enveloppe demande le repertoire utilisateur de la machine
# virtuelle, depuis la correction du 2026-08-31 : c'est lui qui decide quel fichier de toolchains
# gouverne. Sans ce temoin, chaque cas de ce fichier lirait le fichier de toolchains reel du poste
# et prouverait ce que le poste a, pas ce que la fixture declare. Mesure faite avant de l'ecrire :
# sans lui, cinq des sept cas historiques ci-dessous passent au rouge et les deux autres restent
# verts pour une raison etrangere a leur objet.
fabriquer_java_du_path() {
  {
    echo '#!/usr/bin/env bash'
    echo 'for argument in "$@"; do'
    echo '  case "${argument}" in'
    printf "    -XshowSettings*) printf 'Property settings:\\n    user.home = %%s\\n' '%s' >&2; exit 0 ;;\n" "$1"
    echo '  esac'
    echo 'done'
    echo 'printf '"'"'openjdk version "1.8.0_202" 2019-01-01\n'"'"' >&2'
  } > "${BAC}/bin/java"
  chmod +x "${BAC}/bin/java"
}

# fabriquer_home <nom> <version_declaree_ou_vide> <jdk_home_ou_vide>
# Rend un HOME jetable. Avec une version vide, le fichier de toolchains ne declare aucun jdk 21.
fabriquer_home() {
  maison="${BAC}/home-$1"
  mkdir -p "${maison}/.m2"
  {
    echo '<?xml version="1.0" encoding="UTF-8"?>'
    echo '<toolchains>'
    if [ -n "$2" ]; then
      echo '  <toolchain>'
      echo '    <type>jdk</type>'
      echo "    <provides><version>$2</version></provides>"
      echo "    <configuration><jdkHome>$3</jdkHome></configuration>"
      echo '  </toolchain>'
    fi
    echo '</toolchains>'
  } > "${maison}/.m2/toolchains.xml"
  printf '%s' "${maison}"
}

# jouer <maison> [repertoire_utilisateur_de_la_machine_virtuelle]
# Lance l'enveloppe dans un environnement jetable, rend "<code>|<mvn lance ?>". Sans second
# argument, la machine virtuelle rapporte la meme maison que le shell : c'est le cas ordinaire, et
# c'est celui que les sept cas historiques decrivent.
jouer() {
  fabriquer_java_du_path "${2:-$1}"
  temoin="${BAC}/temoin-$$-${RANDOM}"
  rm -f "${temoin}"
  HOME="$1" PATH="${BAC}/bin:${PATH}" TEMOIN_MVN="${temoin}" \
    bash "${ENVELOPPE}" -v > "${BAC}/sortie" 2>&1
  code=$?
  if [ -f "${temoin}" ]; then
    printf '%s|mvn lance' "${code}"
  else
    printf '%s|mvn non lance' "${code}"
  fi
}

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

# --- la morsure du lot : une toolchain jdk 21 qui designe autre chose ---

jdk_25="$(fabriquer_jdk jdk-25 "25.0.2")"
maison_menteuse="$(fabriquer_home menteuse "21" "${jdk_25}")"

verifier_egal "une toolchain jdk 21 qui designe un JDK 25 fait refuser, et Maven n'est pas lance" \
  "1|mvn non lance" \
  "$(jouer "${maison_menteuse}")"

verifier_egal "le refus nomme les deux versions plutot que de se contenter d'un code" \
  "trouve" \
  "$(grep -q "designe un JDK qui rapporte 25.0.2" "${BAC}/sortie" && echo trouve || echo absent)"

jre_8="$(fabriquer_jdk jre-8 "1.8.0_202")"
maison_jre="$(fabriquer_home jre "21" "${jre_8}")"

verifier_egal "une toolchain jdk 21 qui designe un JRE 8 fait refuser aussi" \
  "1|mvn non lance" \
  "$(jouer "${maison_jre}")"

# --- le cas nominal : l'enveloppe ne refuse pas ce qui est conforme ---

jdk_21="$(fabriquer_jdk jdk-21 "21.0.10")"
maison_juste="$(fabriquer_home juste "21" "${jdk_21}")"

verifier_egal "une toolchain jdk 21 qui designe un JDK 21 laisse passer, et Maven est lance" \
  "0|mvn lance" \
  "$(jouer "${maison_juste}")"

verifier_egal "le cas nominal annonce la version confirmee" \
  "trouve" \
  "$(grep -q "Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10" "${BAC}/sortie" && echo trouve || echo absent)"

# --- JAVA_TOOL_OPTIONS : la JVM imprime une ligne avant la version (s55, A7) ---
#
# Quand JAVA_TOOL_OPTIONS est positionnee, java -version commence par "Picked up
# JAVA_TOOL_OPTIONS: ..." sur stderr. L'enveloppe lisait la premiere ligne et rendait "inconnue" :
# refus d'un JDK 21 conforme. Le JDK jetable imprime cette ligne avant la version.

jdk_21_bavard="${BAC}/jdk-21-bavard"
mkdir -p "${jdk_21_bavard}/bin"
{
  echo '#!/usr/bin/env bash'
  echo 'echo "Picked up JAVA_TOOL_OPTIONS: -Dfile.encoding=UTF-8" >&2'
  echo 'echo "openjdk version \"21.0.10\" 2026-01-01" >&2'
} > "${jdk_21_bavard}/bin/java"
chmod +x "${jdk_21_bavard}/bin/java"
: > "${jdk_21_bavard}/bin/javac"
chmod +x "${jdk_21_bavard}/bin/javac"
maison_bavarde="$(fabriquer_home bavarde "21" "${jdk_21_bavard}")"

verifier_egal "un JDK 21 conforme qui imprime Picked up JAVA_TOOL_OPTIONS avant sa version laisse passer"   "0|mvn lance"   "$(jouer "${maison_bavarde}")"

verifier_egal "la version annoncee est celle du JDK et non la ligne Picked up"   "trouve"   "$(grep -q "Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10" "${BAC}/sortie" && echo trouve || echo absent)"

# --- les deux refus qui existaient deja, verifies pour qu'ils ne partent pas avec le changement ---

maison_sans_21="$(fabriquer_home sans-21 "17" "${jdk_21}")"

verifier_egal "aucune toolchain jdk 21 declaree fait refuser sans lancer Maven" \
  "1|mvn non lance" \
  "$(jouer "${maison_sans_21}")"

racine_vide="${BAC}/racine-sans-javac"
mkdir -p "${racine_vide}/bin"
maison_creuse="$(fabriquer_home creuse "21" "${racine_vide}")"

verifier_egal "une racine de JDK sans bin/javac fait refuser sans lancer Maven" \
  "1|mvn non lance" \
  "$(jouer "${maison_creuse}")"

# --- HOME et le repertoire utilisateur de la machine virtuelle divergent (2026-08-31, theo-archi) ---
#
# Le lot F a appris au diagnostic que Maven resout son fichier de toolchains sur le user.home de sa
# machine virtuelle et non sur HOME. Le verdict, lui, etait reste sur HOME : le diagnostic savait ce
# que le verdict ignorait. Les deux cas ci-dessous sont ce que cela coutait, et ils sont mesures dans
# les deux sens - ce n'est pas seulement un faux rouge genant, c'est aussi un faux vert.

maison_de_maven="$(fabriquer_home de-maven "21" "${jdk_21}")"
maison_du_shell_menteuse="$(fabriquer_home du-shell-menteuse "21" "${jdk_25}")"

verifier_egal "le fichier du shell ment, celui que Maven lit est bon : Maven est lance" \
  "0|mvn lance" \
  "$(jouer "${maison_du_shell_menteuse}" "${maison_de_maven}")"

verifier_egal "et le verdict dit sur quel fichier il porte" \
  "trouve" \
  "$(grep -q "sur le fichier de toolchains que Maven lit, qui n'est pas celui de ce shell" "${BAC}/sortie" && echo trouve || echo absent)"

maison_de_maven_menteuse="$(fabriquer_home de-maven-menteuse "21" "${jdk_25}")"
maison_du_shell_bonne="$(fabriquer_home du-shell-bonne "21" "${jdk_21}")"

verifier_egal "le fichier du shell est bon, celui que Maven lit ment : refus, et Maven n'est pas lance" \
  "1|mvn non lance" \
  "$(jouer "${maison_du_shell_bonne}" "${maison_de_maven_menteuse}")"

# La machine virtuelle ne rapporte rien : repli sur le fichier du shell, et l'enveloppe le dit
# plutot que de laisser croire qu'elle a juge le fichier de Maven. C'est un etat degrade, pas un
# etat normal, et il est nomme comme tel.
maison_sans_vm="$(fabriquer_home sans-vm "21" "${jdk_21}")"

verifier_egal "sans repertoire utilisateur rapporte, le repli sur le fichier du shell est annonce" \
  "trouve" \
  "$(jouer "${maison_sans_vm}" muet > /dev/null 2>&1; grep -q "ce fichier n'est peut-etre pas celui que Maven lit" "${BAC}/sortie" && echo trouve || echo absent)"

# --- test negatif global, meme motif que verifier-jdk-test.sh ---

ctx='(^|[^0-9A-Za-z/])'
segment='[0-9A-Za-z_]'
chemin_poste="${ctx}([A-Za-z]:[\/]${segment}|/[a-z]/${segment}|/home/${segment}|/Users/${segment})"
for sortie in "${SORTIES[@]}"; do
  if printf '%s' "${sortie}" | LC_ALL=C grep -Eq "${chemin_poste}"; then
    echo "ECHEC test negatif global : une sortie collectee contient un chemin de poste"
    ECHECS=$((ECHECS + 1))
  fi
done
echo "ok test negatif global : aucune sortie collectee ne contient de chemin de poste"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : l'enveloppe ne refuse pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : l'enveloppe refuse au lieu d'imprimer, et le cas conforme passe."
