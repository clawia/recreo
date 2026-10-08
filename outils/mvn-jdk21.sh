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
# extraire_version_java, version_majeure_java, ligne_de_lancement, et depuis le 2026-08-31
# verdict_de_version et message_de_refus_de_toolchain. Sa preuve est manuelle et datee : sur le
# poste de reference, le 2026-08-17, "bash outils/mvn-jdk21.sh -B clean verify" passe le build
# complet sans JAVA_HOME, la ou "mvn -B clean verify" echoue a la phase validate en nommant ce qui
# manque.
#
# Ce que ce script n'affirme plus depuis le 2026-08-29 (obstacle 23) : que le JDK qu'il a resolu
# est un 21. Il le constate en le lui demandant, et la premiere ligne de sa sortie qui contient
# `version "` dit ce que ce JDK rapporte (depuis s55 : la JVM peut imprimer avant elle une ligne
# "Picked up JAVA_TOOL_OPTIONS").
#
# Ce qu'il ne fait plus depuis le 2026-08-31 (s25, lot F) : lancer Maven quand ce constat le
# dement. La dette est ouverte par S21-1 le 2026-08-27 - "il doit refuser au lieu d'imprimer" - et
# elle a ete documentee quatre fois sans etre payee. Entre-temps l'avertissement a ete filtre par
# son propre auteur le jour ou il l'ecrivait, et il a induit en erreur une lectrice competente le
# 2026-08-30 : un avertissement qu'un filtre de lecture ordinaire fait disparaitre n'est pas un
# garde-fou. Le refus est bruyant, il porte un code de sortie non nul, et il nomme les deux causes
# possibles plutot que de laisser deviner.
set -uo pipefail

repertoire_outils="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck source=outils/verifier-jdk.sh
. "${repertoire_outils}/verifier-jdk.sh"

# Le fichier que Maven lit, et non celui que ce shell lit. Maven resout son fichier de toolchains
# sur le user.home de sa machine virtuelle ; HOME est une variable du shell, et rien ne garantit
# que les deux designent le meme repertoire. Le lot F du 2026-08-31 l'a appris au diagnostic et
# l'a laisse ignorer du verdict ; la mesure du meme jour montre que cela coute dans les deux sens,
# un faux rouge et un faux vert. Le detail est dans fichier_de_toolchains_gouvernant.
fichier_du_shell="${HOME}/.m2/toolchains.xml"
proprietes_vm="$(java -XshowSettings:properties -version 2>&1)"
user_home_vm="$(repertoire_utilisateur_de_la_machine_virtuelle "${proprietes_vm}")"

fichier_de_maven=""
maven_lisible=0
meme_fichier=0
if [ -n "${user_home_vm}" ] && [ -f "${user_home_vm}/.m2/toolchains.xml" ]; then
  fichier_de_maven="${user_home_vm}/.m2/toolchains.xml"
  maven_lisible=1
  if [ "${fichier_de_maven}" -ef "${fichier_du_shell}" ]; then
    meme_fichier=1
  fi
fi

fichier_toolchains="$(fichier_de_toolchains_gouvernant   "${fichier_du_shell}" "${fichier_de_maven}" "${maven_lisible}")"
echo "Verdict rendu $(mention_du_fichier_gouvernant "${maven_lisible}" "${meme_fichier}")." >&2

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

version_jdk="$(extraire_version_java "$("${jdk_home}/bin/java" -version 2>&1 | grep -m1 'version "')")"

# L'enveloppe verifie ce qu'elle affiche, ou elle n'affiche rien : la ligne ci-dessous est derivee
# de ce que le JDK rapporte, jamais de ce que la toolchain declare (obstacle 23, ADR-0009).
ligne_de_lancement "21" "${version_jdk}" >&2

if [ "$(verdict_de_version "21" "${version_jdk}")" != "conforme" ]; then
  message_de_refus_de_toolchain "21" "${version_jdk}" >&2
  exit 1
fi

JAVA_HOME="${jdk_home}" exec mvn "$@"
