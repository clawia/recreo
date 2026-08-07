#!/usr/bin/env bash
# Constate la presence d'un JDK 21 sans se fier au seul nom d'un fichier de toolchains (S6-14).
#
# Deux canaux distincts, jamais melanges (critere corrige par theo-archi, avis de faisabilite s06,
# section S6-14, apres que la premiere ecriture de la story se contredisait avec ADR-0010) :
#
#   - un diagnostic libre, sur la sortie d'erreur, destine a l'agent qui debloque son poste. Il
#     peut nommer un chemin, une valeur de JAVA_HOME, un nom de fichier : il n'est jamais destine a
#     etre commite.
#   - un constat citable, sur la sortie standard, ecrit pour etre colle tel quel dans
#     rapport-qualite.md ou confirmation-archunit.md. Il ne porte que des faits (versions, et si
#     JAVA_HOME/le fichier de toolchains sont renseignes/lus ou non), jamais un chemin, un nom de
#     compte ni un emplacement.
#
# Comme pour verifier-lignee.sh (S6-13), le script est coupe en deux, mais le partage est different
# : "quel JDK est present sur ce poste" est une question dont la reponse est l'environnement, donc
# non testable en soi. Ce qui est teste, c'est la mise en forme :
#
#   - etat_renseignee, etat_toolchains et extraire_version_java sont des fonctions pures qui
#     transforment une donnee collectee en fait categoriel, sans jamais recevoir ni recopier un
#     chemin.
#   - formater_constat assemble le constat citable, et refuse en defense en profondeur (via
#     nettoyer_valeur_libre) toute valeur libre qui ressemblerait a un chemin de poste de travail
#     (ADR-0010) plutot que a une version.
#   - extraire_jdkhome_pour_version lit un fichier de toolchains donne en argument : c'est une
#     transformation deterministe testable sur des fixtures temporaires (meme principe que les
#     fichiers temoins de verifier-chemins-test.sh), pas sur le vrai fichier du poste.
#   - l'enveloppe, en bas de ce fichier, est la seule partie qui touche reellement le poste (java,
#     JAVA_HOME, le repertoire ~/.m2). Elle n'est pas testee ; elle a ete prouvee une fois a la main
#     sur ce poste, qui est precisement le cas fautif motivant cette story (fichier de toolchains
#     nomme toochains.xml, jamais lu par Maven, JAVA_HOME vide, java du PATH en JRE 8, alors qu'un
#     JDK 21.0.10 y est installe et fonctionne).
set -uo pipefail

# extraire_version_java <ligne_de_sortie_de_java_-version>
# Pure. Isole la valeur entre guillemets qui suit "version" ("java version \"1.8.0_202\"",
# "openjdk version \"21.0.10\" 2023-10-17"). Rend "inconnue" si la forme n'est pas reconnue - un
# java -version inhabituel ne doit jamais faire echouer le constat.
extraire_version_java() {
  ligne="$1"
  version="$(printf '%s' "${ligne}" | sed -n 's/.*version "\([^"]*\)".*/\1/p')"
  if [ -z "${version}" ]; then
    echo "inconnue"
  else
    echo "${version}"
  fi
}

# etat_renseignee <valeur>
# Pure. Rend "renseignee" si la valeur est non vide, "vide" sinon. Ne recopie jamais la valeur :
# c'est le cas exact nomme par l'avis de faisabilite (JAVA_HOME valant un chemin de poste doit
# produire "renseignee", jamais le chemin).
etat_renseignee() {
  if [ -n "$1" ]; then
    echo "renseignee"
  else
    echo "vide"
  fi
}

# etat_toolchains <lu_par_maven:0|1> <fichier_trouve_sous_un_autre_nom:0|1>
# Pure. Ne recoit et ne rend jamais un nom ni un emplacement de fichier, seulement les deux faits
# etablis par la collecte.
etat_toolchains() {
  lu="$1"
  trouve_ailleurs="$2"
  if [ "${lu}" = "1" ]; then
    echo "oui"
  elif [ "${trouve_ailleurs}" = "1" ]; then
    echo "non (fichier present sous un nom non lu)"
  else
    echo "non (aucun fichier trouve)"
  fi
}

# nettoyer_valeur_libre <valeur>
# Pure. Defense en profondeur pour formater_constat : une valeur libre (version rendue par
# extraire_version_java) qui ressemblerait malgre tout a un chemin de poste de travail n'est jamais
# recopiee dans le constat citable.
nettoyer_valeur_libre() {
  valeur="$1"
  ctx='(^|[^0-9A-Za-z/])'
  segment='[0-9A-Za-z_]'
  chemin_poste="${ctx}([A-Za-z]:[\\/]${segment}|/[a-z]/${segment}|/home/${segment}|/Users/${segment})"
  if printf '%s' "${valeur}" | LC_ALL=C grep -Eq "${chemin_poste}"; then
    echo "valeur refusee (ressemble a un chemin de poste de travail)"
  else
    printf '%s' "${valeur}"
  fi
}

# formater_constat <version_java_path> <version_jdk_compilation> <etat_java_home> <etat_toolchains>
# Pure. Assemble le constat citable, la seule sortie destinee a la sortie standard.
formater_constat() {
  printf 'java du PATH : %s | JDK de compilation : %s | JAVA_HOME : %s | toolchains lu par Maven : %s\n' \
    "$(nettoyer_valeur_libre "$1")" "$(nettoyer_valeur_libre "$2")" "$3" "$4"
}

# extraire_jdkhome_pour_version <fichier_toolchains> <version_cherchee>
# Deterministe mais lit un fichier passe en argument : testable sur une fixture temporaire, jamais
# sur le vrai fichier du poste. Rend le contenu de <jdkHome> du premier <toolchain> dont
# <version> vaut exactement <version_cherchee>, chaine vide si aucun ne correspond.
extraire_jdkhome_pour_version() {
  fichier="$1"
  cherchee="$2"
  [ -f "${fichier}" ] || return 0
  awk -v cherchee="${cherchee}" '
    /<version>/ {
      v = $0
      gsub(/.*<version>/, "", v); gsub(/<\/version>.*/, "", v)
      gsub(/^[ \t]+|[ \t]+$/, "", v)
      version_courante = v
    }
    /<jdkHome>/ {
      if (version_courante == cherchee) {
        h = $0
        gsub(/.*<jdkHome>/, "", h); gsub(/<\/jdkHome>.*/, "", h)
        gsub(/^[ \t]+|[ \t]+$/, "", h)
        print h
        exit
      }
    }
  ' "${fichier}"
}

# --- enveloppe : la seule partie qui touche le poste, non testee ---
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
  version_java_brute="$(java -version 2>&1 | head -n1)"
  echo "diagnostic - sortie de java -version (PATH) : ${version_java_brute}" >&2
  echo "diagnostic - JAVA_HOME : ${JAVA_HOME:-<vide>}" >&2

  fichier_defaut="${HOME}/.m2/toolchains.xml"
  toolchains_lu=0
  fichier_trouve=""
  if [ -f "${fichier_defaut}" ]; then
    echo "diagnostic - fichier de toolchains lu par Maven : ${fichier_defaut}" >&2
    toolchains_lu=1
    fichier_trouve="${fichier_defaut}"
  else
    candidat="$(find "${HOME}/.m2" -maxdepth 1 -type f -iname '*chain*.xml' 2>/dev/null | head -n1)"
    if [ -n "${candidat}" ]; then
      echo "diagnostic - fichier de toolchains present sous un nom non lu par Maven : ${candidat}" >&2
      fichier_trouve="${candidat}"
    else
      echo "diagnostic - aucun fichier de toolchains trouve sous ${HOME}/.m2" >&2
    fi
  fi

  jdk_home_21=""
  if [ -n "${fichier_trouve}" ]; then
    jdk_home_21="$(extraire_jdkhome_pour_version "${fichier_trouve}" "21")"
  fi

  if [ -n "${jdk_home_21}" ]; then
    echo "diagnostic - jdkHome declare pour la version 21 dans ${fichier_trouve} : ${jdk_home_21}" >&2
    version_compilation="$(extraire_version_java "$("${jdk_home_21}/bin/java" -version 2>&1 | head -n1)")"
  elif [ -n "${JAVA_HOME:-}" ]; then
    echo "diagnostic - pas de version 21 dans un fichier de toolchains, repli sur JAVA_HOME" >&2
    version_compilation="$(extraire_version_java "$("${JAVA_HOME}/bin/java" -version 2>&1 | head -n1)")"
  else
    echo "diagnostic - pas de version 21 dans un fichier de toolchains, ni de JAVA_HOME, repli sur le java du PATH" >&2
    version_compilation="$(extraire_version_java "${version_java_brute}")"
  fi

  fichier_trouve_ailleurs=0
  if [ -n "${fichier_trouve}" ] && [ "${toolchains_lu}" -eq 0 ]; then
    fichier_trouve_ailleurs=1
  fi

  formater_constat \
    "$(extraire_version_java "${version_java_brute}")" \
    "${version_compilation}" \
    "$(etat_renseignee "${JAVA_HOME:-}")" \
    "$(etat_toolchains "${toolchains_lu}" "${fichier_trouve_ailleurs}")"
  exit 0
fi
