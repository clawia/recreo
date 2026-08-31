#!/usr/bin/env bash
# Constate la presence d'un JDK 21 sans se fier au seul nom d'un fichier de toolchains (S6-14).
#
# Changement de role au sprint 14 : la moitie "diagnostiquer pourquoi rien ne compile" de ce script
# n'est plus le seul recours. Le pom parent declare maintenant maven-toolchains-plugin (toolchain
# jdk 21 exigee) et maven-enforcer-plugin (machine virtuelle de Maven en 17 ou plus) : le build
# lui-meme nomme desormais ce qui manque, en une seconde et a la phase validate, la ou il echouait
# sur "No compiler is provided in this environment". Ce qui reste a la charge de ce fichier :
#   - le constat citable, sans chemin, colle tel quel dans un rapport de sprint ;
#   - extraire_jdkhome_pour_version, dont outils/mvn-jdk21.sh se sert pour lancer Maven sur le JDK
#     de la toolchain sans que personne ait a positionner JAVA_HOME a la main.
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

# version_majeure_java <version>
# Pure. Rend le numero de version majeure d'une version rendue par extraire_version_java :
# "21.0.10" -> 21, "1.8.0_202" -> 8 (forme historique), "25" -> 25. Rend "inconnue" si la forme
# n'est pas reconnue - un java -version inhabituel ne doit jamais faire echouer le constat.
version_majeure_java() {
  version="$1"
  case "${version}" in
    1.*) majeure="$(printf '%s' "${version}" | sed -n 's/^1\.\([0-9][0-9]*\).*/\1/p')" ;;
    *) majeure="$(printf '%s' "${version}" | sed -n 's/^\([0-9][0-9]*\).*/\1/p')" ;;
  esac
  if [ -z "${majeure}" ]; then
    echo "inconnue"
  else
    echo "${majeure}"
  fi
}

# ligne_de_lancement <version_demandee> <version_constatee>
# Pure. La ligne que mvn-jdk21.sh affiche avant de lancer Maven.
#
# Correction du 2026-08-29 (obstacle 23, ouvert en s21). Cette ligne recopiait la version
# *declaree* par la toolchain, jamais celle que rapporte le JDK designe. Elle annoncait donc
# "toolchain jdk 21" tout en affichant une autre version dans la meme phrase, sur un poste ou la
# declaration ne dit pas la verite - ou simplement ou HOME et le profil utilisateur de la machine
# virtuelle ne designent pas le meme repertoire, auquel cas l'enveloppe et Maven ne lisent pas le
# meme fichier de toolchains. Un chiffre faux dans l'outil qui sert a mesurer est le pire endroit
# ou en laisser un : la version affichee est desormais derivee de ce que le JDK rapporte, et la
# declaration n'est repetee que lorsqu'elle est confirmee.
ligne_de_lancement() {
  demandee="$1"
  constatee="$2"
  if [ "$(version_majeure_java "${constatee}")" = "${demandee}" ]; then
    printf 'Maven lance sur le JDK de la toolchain jdk %s : %s\n' "${demandee}" "${constatee}"
  else
    printf "Maven lance sur le JDK que designe la toolchain jdk %s, et ce JDK rapporte %s : la declaration de version n'a pas ete confirmee
" \
      "${demandee}" "${constatee}"
  fi
}

# verdict_de_version <version_demandee> <version_constatee>
# Pure. Rend "conforme" quand le JDK designe rapporte bien la version majeure demandee, "non
# conforme" dans tous les autres cas - y compris quand sa version est illisible, parce qu'une
# version qu'on ne sait pas lire n'est pas une version qu'on a verifiee.
#
# Ecrite le 2026-08-31 (s25, lot F). C'est le juge que S21-1 nommait manquant le 2026-08-27 :
# "il a la version en main, il l'affiche, et il ne la compare a rien". Tant qu'il n'existait pas,
# l'enveloppe imprimait un avertissement puis lancait Maven quand meme. Quatre occurrences
# documentees, dont une ou l'avertissement a ete filtre par son propre auteur le jour ou il
# l'ecrivait : un avertissement qu'un filtre de lecture ordinaire fait disparaitre n'est pas un
# garde-fou.
verdict_de_version() {
  if [ "$(version_majeure_java "$2")" = "$1" ]; then
    echo "conforme"
  else
    echo "non conforme"
  fi
}

# message_de_refus_de_toolchain <version_demandee> <version_constatee>
# Pure. Le refus que l'enveloppe imprime avant de sortir en erreur, et la marche a suivre.
# Ne porte ni chemin de poste, ni nom de compte : il nomme le fichier par sa forme portable
# (~/.m2/toolchains.xml), qu'ADR-0010 et les Conventions de redaction admettent l'un comme les
# autres - c'est la variable qui est bonne et sa valeur figee qui serait mauvaise.
message_de_refus_de_toolchain() {
  demandee="$1"
  constatee="$2"
  printf 'erreur : refus de lancer Maven. La toolchain jdk %s designe un JDK qui rapporte %s.\n' \
    "${demandee}" "${constatee}"
  printf 'Une mesure prise par cette enveloppe serait annoncee sur %s et executee sur %s.\n' \
    "${demandee}" "${constatee}"
  printf 'Deux causes, et le constat citable dit laquelle :\n'
  printf "  1. l'entree <version>%s</version> de ~/.m2/toolchains.xml designe la racine d'un autre JDK ;\n" \
    "${demandee}"
  printf '  2. HOME et le repertoire utilisateur de la machine virtuelle ne designent pas le meme\n'
  printf '     repertoire, auquel cas cette enveloppe et Maven ne lisent pas le meme fichier.\n'
  printf 'Lancer : bash outils/verifier-jdk.sh\n'
}

# repertoire_utilisateur_de_la_machine_virtuelle <sortie_de_java_-XshowSettings:properties>
# Pure. Isole la valeur de user.home. C'est le repertoire sur lequel Maven resout son fichier de
# toolchains, et rien ne le garantit egal a HOME - c'est tout l'objet de la correction du
# 2026-08-31. Rend une chaine vide si la propriete n'est pas dans la sortie, plutot que de deviner.
repertoire_utilisateur_de_la_machine_virtuelle() {
  printf '%s\n' "$1" | sed -n 's/^[[:space:]]*user\.home[[:space:]]*=[[:space:]]*//p' | head -n1
}

# etat_fichier_de_toolchains_de_maven <trouve:0|1> <meme_fichier_que_celui_lu_par_ce_script:0|1>
# Pure. Ne recoit et ne rend jamais un nom ni un emplacement : seulement les deux faits etablis
# par la collecte.
#
# Correction du 2026-08-31 (s25, lot F). Le defaut est nomme par S21-1 section 6.3 le 2026-08-27 et
# n'avait jamais ete corrige : le champ "toolchains lu par Maven" du constat citable etait
# renseigne en regardant le fichier du shell POSIX, alors que Maven resout le sien sur le user.home
# de sa machine virtuelle. Le champ repondait donc sur un autre fichier que celui dont il parlait,
# et quatre confirmation-archunit.md le citent comme preuve. Le troisieme etat ci-dessous est le
# cas exact qui a rendu ces rapports inexacts ; il est desormais dit au lieu d'etre confondu avec
# le premier.
etat_fichier_de_toolchains_de_maven() {
  if [ "$1" != "1" ]; then
    echo "non (aucun sous le repertoire utilisateur de la machine virtuelle)"
  elif [ "$2" = "1" ]; then
    echo "oui (le meme que celui lu par ce script)"
  else
    echo "oui (un autre que celui lu par ce script)"
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

# formater_constat <version_java_path> <version_jdk_compilation> <etat_java_home>
#                  <etat_toolchains_de_ce_script> <etat_toolchains_de_maven>
# Pure. Assemble le constat citable, la seule sortie destinee a la sortie standard.
#
# Le quatrieme champ s'appelait "toolchains lu par Maven" et etait renseigne par etat_toolchains,
# qui regarde le fichier du shell. Il reprend le 2026-08-31 le nom de ce qu'il a toujours dit, et
# un cinquieme champ porte le fait sur lequel il se trompait.
formater_constat() {
  printf 'java du PATH : %s | JDK de compilation : %s | JAVA_HOME : %s | toolchains lu par ce script : %s | toolchains lu par Maven : %s\n' \
    "$(nettoyer_valeur_libre "$1")" "$(nettoyer_valeur_libre "$2")" "$3" "$4" "$5"
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
    echo "diagnostic - fichier de toolchains lu par ce script : ${fichier_defaut}" >&2
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

  # Correction du 2026-08-31 (s25, lot F). Le champ "toolchains lu par Maven" repondait jusqu'ici
  # sur ${HOME}/.m2/toolchains.xml, c'est-a-dire sur le fichier du shell POSIX. Maven resout le
  # sien sur le user.home de sa machine virtuelle, et rien ne garantit que les deux designent le
  # meme repertoire - c'est ce qui a rendu douze rapports inexacts. On le demande donc a la machine
  # virtuelle plutot que de le deduire du shell.
  #
  # La comparaison des deux fichiers se fait par -ef, jamais par egalite de chaine : sur un poste
  # ou le shell ecrit un chemin POSIX et la machine virtuelle un chemin natif, deux ecritures
  # differentes designent le meme fichier, et une comparaison textuelle rendrait "distincts" a tort
  # - un chiffre faux dans l'outil qui sert a mesurer, exactement le defaut qu'on corrige ici.
  proprietes_vm="$(java -XshowSettings:properties -version 2>&1)"
  user_home_vm="$(repertoire_utilisateur_de_la_machine_virtuelle "${proprietes_vm}")"
  toolchains_de_maven_trouve=0
  toolchains_de_maven_est_le_meme=0
  if [ -n "${user_home_vm}" ]; then
    echo "diagnostic - user.home rapporte par la machine virtuelle : ${user_home_vm}" >&2
    fichier_de_maven="${user_home_vm}/.m2/toolchains.xml"
    if [ -f "${fichier_de_maven}" ]; then
      toolchains_de_maven_trouve=1
      echo "diagnostic - fichier de toolchains que Maven lit : ${fichier_de_maven}" >&2
      if [ -n "${fichier_trouve}" ] && [ "${fichier_de_maven}" -ef "${fichier_trouve}" ]; then
        toolchains_de_maven_est_le_meme=1
      fi
    else
      echo "diagnostic - aucun toolchains.xml sous le user.home de la machine virtuelle" >&2
    fi
  else
    echo "diagnostic - la machine virtuelle n'a pas rapporte de user.home" >&2
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
    "$(etat_toolchains "${toolchains_lu}" "${fichier_trouve_ailleurs}")" \
    "$(etat_fichier_de_toolchains_de_maven "${toolchains_de_maven_trouve}" "${toolchains_de_maven_est_le_meme}")"
  exit 0
fi
