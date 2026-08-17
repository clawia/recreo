#!/usr/bin/env bash
# Lance Maven sur le JDK 21 declare comme toolchain, sans que l'appelant ait a positionner
# JAVA_HOME a la main a chaque commande.
#
#   bash outils/mvn-jdk21.sh -B clean verify
#
# Motif, mesure sur trois sprints : le java du PATH de certains postes est un JRE, et la machine
# virtuelle sur laquelle Maven tourne n'est pas gouvernable depuis le pom - une toolchain choisit
# le JDK qui compile et qui execute les tests, pas celui qui execute Maven. La regle
# maven-enforcer-plugin du pom parent dit desormais ce qu'il manque en une seconde ; ce script est
# ce qu'on lance ensuite pour ne plus avoir a le dire.
#
# La racine du JDK n'est jamais ecrite ici : elle est lue dans le fichier de toolchains de
# l'utilisateur, la ou Maven la lit deja (ADR-0010, conventions de redaction). Le seul prerequis
# est donc une toolchain de type jdk et de version 21 declaree dans ce fichier.
#
# Ce script est une enveloppe : il ne contient aucune logique pure a tester (la seule
# transformation, extraire_jdkhome_pour_version, est celle de verifier-jdk.sh, deja couverte par
# verifier-jdk-test.sh). Sa preuve est manuelle et datee : sur le poste de reference, le
# 2026-08-17, "bash outils/mvn-jdk21.sh -B clean verify" passe le build complet sans JAVA_HOME,
# la ou "mvn -B clean verify" echoue a la phase validate en nommant ce qui manque.
set -uo pipefail

repertoire_outils="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck source=outils/verifier-jdk.sh
. "${repertoire_outils}/verifier-jdk.sh"

fichier_toolchains="${HOME}/.m2/toolchains.xml"
jdk_home="$(extraire_jdkhome_pour_version "${fichier_toolchains}" "21")"

if [ -z "${jdk_home}" ]; then
  echo "erreur : aucune toolchain de type jdk et de version 21 dans le fichier de toolchains de l'utilisateur." >&2
  echo "Declarer dans ~/.m2/toolchains.xml un <toolchain> avec <type>jdk</type>," >&2
  echo "<provides><version>21</version></provides> et <configuration><jdkHome>...</jdkHome></configuration>" >&2
  echo "pointant la racine d'un JDK 21 installe sur ce poste." >&2
  exit 1
fi

if [ ! -e "${jdk_home}/bin/javac" ] && [ ! -e "${jdk_home}/bin/javac.exe" ]; then
  echo "erreur : la toolchain jdk 21 declaree ne pointe pas une racine de JDK exploitable (bin/javac introuvable)." >&2
  echo "diagnostic - jdkHome declare pour la version 21 : ${jdk_home}" >&2
  exit 1
fi

version_jdk="$(extraire_version_java "$("${jdk_home}/bin/java" -version 2>&1 | head -n1)")"
echo "Maven lance sur le JDK de la toolchain jdk 21 : ${version_jdk}" >&2

JAVA_HOME="${jdk_home}" exec mvn "$@"
