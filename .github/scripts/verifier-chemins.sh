#!/usr/bin/env bash
# Refuse un chemin de poste de travail ou un nom de compte dans un fichier suivi.
#
# Lit une liste de chemins de fichiers sur l'entree standard, un par ligne. Le choix des fichiers
# concernes (liste des fichiers suivis, exclusion nommee de verifier-chemins-test.sh) appartient a
# l'appelant (.github/workflows/ci.yml) ; ce script ne juge que le contenu qu'on lui donne, ce qui
# le rend testable hors CI par verifier-chemins-test.sh (ADR-0009, ADR-0010).
#
# Formes refusees, une occurrence par ligne suffit :
#   - chemin Windows a lettre de lecteur   : C:\<compte>\..., C:/<compte>/...
#   - forme Git Bash de la meme chose      : /c/<compte>/...
#   - repertoire personnel Unix nomme      : /home/<compte>/...
#   - repertoire personnel macOS nomme     : /Users/<compte>/...
# Une occurrence n'est retenue que si la lettre de lecteur ou le repertoire est suivi d'un vrai
# segment de chemin (un caractere alphanumerique ou underscore). Nommer le motif interdit sans le
# faire suivre d'un segment reel (C:\..., /c/...) n'est pas une occurrence : c'est la citer pour la
# documenter, pas la commettre. C'est ce que fait CLAUDE.md lui-meme dans ses conventions de
# redaction, et c'est pour cette raison, pas par exception codee en dur, qu'il passe ce garde-fou.
#
# Formes acceptees car portables, resolues localement plutot qu'ecrites en dur : $HOME,
# $JAVA_HOME, ~/.m2/settings.xml, %USERPROFILE%.
set -uo pipefail

# Verifie a la main : sous une locale UTF-8 (en_US.UTF-8, courante sur un poste de developpeur),
# GNU grep 3.0 rate silencieusement des occurrences pourtant reelles d'une classe negee suivie
# d'une classe [\/] dans une expression etendue. LC_ALL=C rend la lecture octet par octet et le
# resultat stable, ici comme en CI.
export LC_ALL=C

CTX='(^|[^0-9A-Za-z/])'
SEGMENT='[0-9A-Za-z_]'
DRIVE_WIN="${CTX}[A-Za-z]:[\\/]${SEGMENT}"
DRIVE_GITBASH="${CTX}/[a-z]/${SEGMENT}"
HOME_UNIX="${CTX}/home/${SEGMENT}"
HOME_MAC="${CTX}/Users/${SEGMENT}"
REGEX="${DRIVE_WIN}|${DRIVE_GITBASH}|${HOME_UNIX}|${HOME_MAC}"

# ADR-0036 : -I retire les fichiers binaires du perimetre, et le retire par leur contenu (grep les
# reconnait a leurs octets nuls) et non par une liste d'extensions qui echouerait par omission au
# premier .woff ou .pdf. Motif mesure : sans -I, une image quelconque fait rougir ce garde-fou par
# coincidence, parce que ses octets compresses contiennent tot ou tard une sequence de quatre octets
# que la regex lit comme un chemin a lettre de lecteur - lettre, deux-points, antislash, caractere
# alphanumerique, sans qu'aucun de ces octets ait le moindre rapport avec un chemin. Sur un lot de 21
# PNG de 72 ko a 500 ko fabriques pour la mesure, 4 sont refuses ; le premier en portait deux, aux
# offsets 102745 et 105396. Un binaire n'a pas de contenu redactionnel : ce que ce garde-fou protege -
# un depot lisible et portable - n'existe pas dans ses octets.
#
# Tous les fichiers dans un seul appel a grep, plutot qu'un appel par fichier : sur un poste
# Windows, le cout de lancement d'un processus se compte en dizaines de millisecondes, et se
# voit dans le temps total sur plusieurs centaines de fichiers.
FICHIERS=()
fichier=""
while IFS= read -r fichier || [ -n "${fichier}" ]; do
  [ -z "${fichier}" ] && continue
  FICHIERS+=("${fichier}")
done

FAUTIFS=0
if [ "${#FICHIERS[@]}" -gt 0 ]; then
  # Un fichier de la liste qui n'existe plus (suppression en cours, chemin invalide) n'est ni une
  # occurrence ni une erreur : grep signale l'absence sur stderr (coupe) pour ce seul fichier et
  # continue les autres ; "|| true" absorbe le code de sortie global.
  trouvailles="$(grep -I -nEH "${REGEX}" "${FICHIERS[@]}" 2>/dev/null || true)"

  while IFS= read -r trouvaille || [ -n "${trouvaille}" ]; do
    [ -z "${trouvaille}" ] && continue
    echo "::error::chemin de poste de travail ou compte utilisateur dans ${trouvaille}"
    FAUTIFS=$((FAUTIFS + 1))
  done <<EOF
${trouvailles}
EOF
fi

if [ "${FAUTIFS}" -gt 0 ]; then
  echo "${FAUTIFS} occurrence(s) de chemin de poste de travail ou de compte utilisateur."
  echo "Formes acceptees : variables resolues localement (\$HOME, \$JAVA_HOME, ~/..., %USERPROFILE%)."
  exit 1
fi
echo "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."
