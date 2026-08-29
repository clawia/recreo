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
# Ce script est une enveloppe : les transformations qu'il emploie vivent toutes dans
# verifier-jdk.sh et sont couvertes par verifier-jdk-test.sh - extraire_jdkhome_pour_version,
# extraire_version_java, et depuis le 2026-08-29 version_majeure_java et ligne_de_lancement. Sa
# preuve est manuelle et datee : sur le poste de reference, le 2026-08-17,
# "bash outils/mvn-jdk21.sh -B clean verify" passe le build complet sans JAVA_HOME, la ou
# "mvn -B clean verify" echoue a la phase validate en nommant ce qui manque.
#
# Ce que ce script n'affirme plus depuis le 2026-08-29 (obstacle 23) : que le JDK qu'il a resolu
# est un 21. Il le constate en le lui demandant, et sa premiere ligne dit ce que ce JDK rapporte.
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
majeure_jdk="$(version_majeure_java "${version_jdk}")"

# L'enveloppe verifie ce qu'elle affiche, ou elle n'affiche rien : la ligne ci-dessous est derivee
# de ce que le JDK rapporte, jamais de ce que la toolchain declare (obstacle 23, ADR-0009).
ligne_de_lancement "21" "${version_jdk}" >&2

if [ "${majeure_jdk}" != "21" ]; then
  echo "diagnostic - Maven lit le fichier de toolchains du profil utilisateur de sa machine virtuelle." >&2
  echo "Quand ce repertoire n'est pas celui de HOME, cette enveloppe et Maven ne lisent pas le meme" >&2
  echo "fichier, et c'est le JDK trouve par Maven qui compile et execute les tests." >&2
  if [ "${majeure_jdk}" = "inconnue" ] || [ "${majeure_jdk}" -lt 17 ]; then
    echo "erreur : spring-boot-maven-plugin ne peut pas etre charge par une machine virtuelle" >&2
    echo "anterieure a 17, et le JDK designe par la toolchain jdk 21 rapporte ${version_jdk}." >&2
    exit 1
  fi
fi

JAVA_HOME="${jdk_home}" exec mvn "$@"
