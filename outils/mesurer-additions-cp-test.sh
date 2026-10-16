#!/usr/bin/env bash
# Contre-exemples de squad/conformite/releves-s57/mesurer-additions-cp.mjs (S57-SCRIPT-ADDITIONS-CP,
# criteres 2, 4 et 5, doctrine ADR-0009).
#
# Ce que ce script couvre, sans navigateur ni jar : l'ensemble derive (65 enonces distincts, egaux
# a l'enumeration independante de sommeMaximale, et qui suit la valeur du paquet), le refus sur un
# port de jeu muet (sortie non nulle, aucun fichier), le refus sur un port de debogage deja occupe
# (le script ne se connecte pas au navigateur d'un autre), et les deux balayages du critere 5
# (aucun port en dur, aucune commande d'arret par nom).
# Ce qu'il ne couvre pas : la mesure elle-meme (Chrome, le jar, les 65 cotes). Sa preuve est
# manuelle et nommee dans rapport-marc-dev-palier-2.md.
set -uo pipefail

RACINE="$(cd "$(dirname "$0")/.." && pwd)"
SCRIPT="${RACINE}/squad/conformite/releves-s57/mesurer-additions-cp.mjs"
PAQUET="${RACINE}/adapters/adapter-content/src/main/resources/contenu/cp-additions-jusqu-a-10.yaml"
ATELIER="$(mktemp -d)"
trap 'rm -rf "${ATELIER}"' EXIT
ECHECS=0

echec() {
  echo "ECHEC $1"
  shift
  for ligne in "$@"; do echo "  ${ligne}"; done
  ECHECS=$((ECHECS + 1))
}

# enumeration <somme> : couples (a, b) avec a + b <= somme, (0, 0) excepte, ecrite en awk pour ne
# rien partager avec le script juge.
enumeration() {
  awk -v n="$1" 'BEGIN { for (a = 0; a <= n; a++) for (b = 0; a + b <= n; b++) if (a + b > 0) print a " + " b }' | sort
}

if ! command -v node > /dev/null; then
  echo "ECHEC node absent : ce juge ne peut pas tourner, et un juge qui ne tourne pas ne juge rien"
  exit 1
fi

SOMME="$(sed -n 's/^ *sommeMaximale: *\([0-9][0-9]*\) *$/\1/p' "${PAQUET}")"
if [ -z "${SOMME}" ]; then
  echec "le paquet ne porte pas sommeMaximale : la reference du test est perdue"
fi

# --- critere 2 : l'ensemble est derive, distinct, egal a l'enumeration independante ---

derive="$(node "${SCRIPT}" --ensemble | sort)"
compte="$(printf '%s\n' "${derive}" | grep -c .)"
distincts="$(printf '%s\n' "${derive}" | sort -u | grep -c .)"
if [ "${compte}" != "65" ] || [ "${distincts}" != "65" ]; then
  echec "l'ensemble derive du paquet compte ${compte} enonces dont ${distincts} distincts, attendu 65 et 65"
elif [ "${derive}" != "$(enumeration "${SOMME}")" ]; then
  echec "l'ensemble derive differe de l'enumeration independante de sommeMaximale ${SOMME}"
else
  echo "ok l'ensemble derive compte 65 enonces distincts, egaux a l'enumeration de sommeMaximale ${SOMME}"
fi

sed 's/^\( *sommeMaximale: *\)[0-9][0-9]*/\15/' "${PAQUET}" > "${ATELIER}/somme-5.yaml"
derive5="$(node "${SCRIPT}" --ensemble "${ATELIER}/somme-5.yaml" | sort)"
if [ "${derive5}" != "$(enumeration 5)" ] || [ "$(printf '%s\n' "${derive5}" | grep -c .)" != "20" ]; then
  echec "avec sommeMaximale 5 l'ensemble doit compter 20 enonces egaux a l'enumeration : le script ne lit pas la valeur du paquet"
else
  echo "ok l'ensemble suit sommeMaximale (5 donne 20 enonces)"
fi

grep -v 'sommeMaximale' "${PAQUET}" > "${ATELIER}/sans-somme.yaml"
sortie="$(node "${SCRIPT}" --ensemble "${ATELIER}/sans-somme.yaml" 2>&1)"
code=$?
if [ "${code}" = "0" ] || ! printf '%s' "${sortie}" | grep -qF 'sommeMaximale absente'; then
  echec "un paquet sans sommeMaximale doit etre refuse en le disant" "code ${code}" "sortie : ${sortie}"
else
  echo "ok un paquet sans sommeMaximale est refuse"
fi

# --- critere 5 : balayages ---

if [ "$(grep -cE '1[89][0-9]{3}' "${SCRIPT}")" != "0" ]; then
  echec "le script porte un port ecrit en dur (1[89][0-9]{3})"
else
  echo "ok aucun port en dur"
fi
if [ "$(grep -cE 'taskkill|pkill|killall' "${SCRIPT}")" != "0" ]; then
  echec "le script porte une commande d'arret par nom d'image"
else
  echo "ok aucun arret par nom d'image"
fi
if [ "$(grep -c 'sommeMaximale' "${SCRIPT}")" -lt 1 ]; then
  echec "le script ne lit pas sommeMaximale"
else
  echo "ok le script lit sommeMaximale"
fi

# --- critere 4 : port de jeu muet ---

PORT_MUET="$(node -e "const s=require('net').createServer();s.listen(0,'127.0.0.1',()=>{console.log(s.address().port);s.close();});")"
PORT_DEBOGAGE_MUET="$(node -e "const s=require('net').createServer();s.listen(0,'127.0.0.1',()=>{console.log(s.address().port);s.close();});")"
sortie="$(node "${SCRIPT}" navigateur-inexistant "${PORT_MUET}" "${PORT_DEBOGAGE_MUET}" "${ATELIER}/sortie-muet" 2>&1)"
code=$?
if [ "${code}" = "0" ] || ! printf '%s' "${sortie}" | grep -qF "rien n'ecoute sur le port de jeu"; then
  echec "un port de jeu muet doit sortir non nul en disant que rien n'ecoute" "code ${code}" "sortie : ${sortie}"
elif [ -e "${ATELIER}/sortie-muet" ]; then
  echec "un port de jeu muet ne doit ecrire aucun fichier" "$(find "${ATELIER}/sortie-muet" | head -5)"
else
  echo "ok un port de jeu muet sort non nul sans rien ecrire (code ${code})"
fi

# --- un port de debogage occupe : le script ne se connecte pas au navigateur d'un autre ---

resultat="$(node --input-type=module -e "
import http from 'node:http';
import net from 'node:net';
import { spawn } from 'node:child_process';
const [script, sortie] = process.argv.slice(1);
const ecoute = (serveur) => new Promise((r) => serveur.listen(0, '127.0.0.1', () => r(serveur.address().port)));
const jeu = http.createServer((q, s) => { s.statusCode = 200; s.end('ok'); });
const occupe = net.createServer();
const portJeu = await ecoute(jeu);
const portOccupe = await ecoute(occupe);
const fils = spawn('node', [script, 'navigateur-inexistant', String(portJeu), String(portOccupe), sortie], { stdio: ['ignore', 'ignore', 'pipe'] });
let erreur = '';
fils.stderr.on('data', (d) => { erreur += d; });
fils.on('close', (code) => { console.log('code:' + code); console.log(erreur); jeu.close(); occupe.close(); });
" "${SCRIPT}" "${ATELIER}/sortie-occupe" 2>&1)"
code="$(printf '%s\n' "${resultat}" | sed -n 's/^code://p' | head -1)"
if [ -z "${code}" ] || [ "${code}" = "0" ] || ! printf '%s' "${resultat}" | grep -qF "deja occupe"; then
  echec "un port de debogage occupe doit etre refuse en le disant" "sortie : ${resultat}"
elif [ -e "${ATELIER}/sortie-occupe" ]; then
  echec "un port de debogage occupe ne doit ecrire aucun fichier"
else
  echo "ok un port de debogage occupe est refuse sans rien ecrire (code ${code})"
fi

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} echec(s)"
  exit 1
fi
echo "Tous les cas passent : le script derive l'ensemble, refuse un port muet ou occupe, et ne porte ni port ni arret par nom."
