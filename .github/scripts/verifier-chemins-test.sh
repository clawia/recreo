#!/usr/bin/env bash
# Contre-exemples du garde-fou de chemins de poste de travail (ADR-0009 applique a ADR-0010).
#
# Chaque cas attendu en echec prouve que le garde-fou mord, et sur le bon motif. Les fixtures sont
# ecrites dans des fichiers temporaires a l'execution : elles ne vivent jamais comme contenu
# commite, precisement pour ne pas se faire refuser elles-memes par ce garde-fou le jour ou il
# tourne sur le depot entier (ADR-0010, piege des fixtures auto-referentes).
#
# Ce script est execute en CI avant le garde-fou lui-meme, pour qu'une regex cassee soit rouge ici
# et non au prochain chemin de poste de travail qui passe.
set -uo pipefail

SCRIPT="$(dirname "$0")/verifier-chemins.sh"
TMPDIR_CAS="$(mktemp -d)"
trap 'rm -rf "${TMPDIR_CAS}"' EXIT
ECHECS=0

# verifier <intitule> <code attendu> <contenu du fichier temoin> [motif attendu dans la sortie]
verifier() {
  intitule="$1"
  attendu="$2"
  contenu="$3"
  motif="${4:-}"

  fichier="${TMPDIR_CAS}/temoin"
  printf '%s' "${contenu}" > "${fichier}"

  sortie="$(printf '%s\n' "${fichier}" | bash "${SCRIPT}" 2>&1)"
  code=$?

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

# --- refus : les quatre formes non portables ---

verifier "chemin Windows a lettre de lecteur, antislash, est refuse" 1 \
  'voir C:\Users\alice\projet\pom.xml
' "chemin de poste de travail ou compte utilisateur dans"

verifier "chemin Windows a lettre de lecteur, slash, est refuse" 1 \
  'voir C:/Users/alice/projet/pom.xml
' "chemin de poste de travail ou compte utilisateur dans"

verifier "une autre lettre de lecteur que C est refusee" 1 \
  'JAVA_HOME=D:\jdk-21
'

verifier "forme Git Bash de la meme chose est refusee" 1 \
  'export PATH=/c/Users/alice/jdk-21/bin:$PATH
'

verifier "repertoire personnel Unix nomme est refuse" 1 \
  'voir /home/alice/recreo/pom.xml
'

verifier "repertoire personnel macOS nomme est refuse" 1 \
  'voir /Users/alice/recreo/pom.xml
'

verifier "deux occurrences dans le meme fichier sont comptees toutes les deux" 1 \
  'un C:\Users\alice
puis /home/bob/depot
' "2 occurrence(s)"

# --- acceptation : citer le motif interdit sans le commettre ---

verifier "citer C:\\... sans segment reel n'est pas une occurrence" 0 \
  'depot est pousse sur GitHub : `C:\...`, `/c/...` n'"'"'ont rien a y faire
'

verifier "citer /c/... sans segment reel n'est pas une occurrence" 0 \
  'motif interdit : /c/...
'

# --- acceptation : formes portables, resolues localement ---

verifier "\$HOME est accepte" 0 \
  'export JAVA_HOME=$HOME/programs/jdk-21
'

verifier "\$JAVA_HOME seul, sans valeur en dur, est accepte" 0 \
  'JAVA_HOME=$JAVA_HOME mvn -B verify
'

verifier "~/.m2/settings.xml est accepte" 0 \
  'voir ~/.m2/settings.xml
'

verifier "%USERPROFILE% est accepte" 0 \
  'set JDK=%USERPROFILE%\jdk-21
'

# --- non-regression : les schemas d'URL ne sont pas pris pour des chemins de poste de travail ---

verifier "une URL avec un segment d'une lettre n'est pas une fausse alerte" 0 \
  'voir http://example.com/a/b pour le detail
'

verifier "le double slash d'un schema d'URL n'est pas pris pour une lettre de lecteur" 0 \
  'voir https://example.com/doc pour le detail
'

verifier "citer /home/... sans segment reel n'est pas une occurrence" 0 \
  'motif interdit : /home/...
'

# --- ADR-0036 : un binaire n'est pas lu comme du texte ---
#
# Les deux cas portent la meme chaine de caracteres. Seul le premier est un fichier binaire, et
# c'est bien cela, et non un affaiblissement de la regex, qui fait la difference de verdict.

fichier="${TMPDIR_CAS}/ressource-binaire"
printf 'PNG\x00\x00\x00 C:\\Users\\alice\\projet \x00\x00 fin' > "${fichier}"
sortie="$(printf '%s\n' "${fichier}" | bash "${SCRIPT}" 2>&1)"
code=$?
if [ "${code}" != 0 ]; then
  echo "ECHEC un fichier binaire n'est pas juge comme du texte : code ${code}"
  echo "  sortie : ${sortie}"
  ECHECS=$((ECHECS + 1))
else
  echo "ok un fichier binaire n'est pas juge comme du texte"
fi

verifier "les memes octets sans octet nul, donc en texte, restent refuses" 1 \
  'PNG    C:\Users\alice\projet    fin
' "chemin de poste de travail ou compte utilisateur dans"

# --- cas limites deja vus sur le garde-fou de prefixes, reproduits ici ---

verifier "un fichier sans chemin de poste de travail est accepte" 0 \
  'rien a signaler ici
'

verifier "un dernier motif sans retour a la ligne final est bien juge" 1 \
  'C:\Users\alice\pom.xml'

sortie="$(printf '' | bash "${SCRIPT}" 2>&1)"
code=$?
if [ "${code}" != 0 ]; then
  echo "ECHEC aucun fichier a juger n'est pas un echec : code ${code}"
  echo "  sortie : ${sortie}"
  ECHECS=$((ECHECS + 1))
else
  echo "ok aucun fichier a juger n'est pas un echec"
fi

sortie="$(printf '%s\n' "${TMPDIR_CAS}/n-existe-pas" | bash "${SCRIPT}" 2>&1)"
code=$?
if [ "${code}" != 0 ]; then
  echo "ECHEC un fichier disparu de la liste n'est pas un echec : code ${code}"
  echo "  sortie : ${sortie}"
  ECHECS=$((ECHECS + 1))
else
  echo "ok un fichier disparu de la liste n'est pas un echec"
fi

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : le garde-fou de chemins ne se comporte pas comme annonce."
  exit 1
fi
echo "Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."
