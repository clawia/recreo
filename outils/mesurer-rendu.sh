#!/usr/bin/env bash
# Capture le rendu de l'application a une largeur de viewport reelle, y compris sous le plancher de
# fenetre de Chrome headless (ADR-0025).
#
# Pourquoi ce script existe. Deux mesures de rendu du sprint 6 ont ete faites avec une commande que
# personne n'a ecrite dans le depot, et l'une des deux a fabrique un symptome qui n'existait pas :
# "chrome --headless --window-size=360,900 --screenshot" ne rend pas une page mise en page pour
# 360 px, il rend un decoupage a gauche d'une page mise en page pour 500 px. Le plancher a ete
# mesure a 516 px exactement par yanis-qa (rapport de qualite s06, point 1) et reverifie par
# theo-archi avant l'ecriture de ce script. Une verification de rendu dont la commande n'est ecrite
# nulle part n'est pas rejouable : c'est la cause racine, et ce script est sa fermeture.
#
# Ce script n'est PAS un garde-fou de CI et ne s'execute pas dans "mvn verify" : il a besoin d'un
# navigateur, que rien dans le depot ne peut garantir. C'est un outil de mesure pour un agent, dont
# le verdict d'invocation, lui, est teste (ADR-0009, meme forme que verifier-lignee.sh) :
#
#   - juger_invocation est une fonction pure. Elle recoit la largeur CSS demandee, la largeur de la
#     fenetre parente et la valeur du profil, et rend un verdict sans lancer aucune commande. C'est
#     elle que mesurer-rendu-test.sh met a l'epreuve, avec des entrees fabriquees, y compris aux deux
#     bords du plancher.
#   - l'enveloppe, en bas de ce fichier, est la seule partie qui touche le poste. Que ses options
#     Chrome soient les bonnes est prouve une fois a la main, sur l'application reelle, et non
#     suppose (meme limite assumee qu'ADR-0009 pour la plage de commits).
#
# Prerequis nomme, jamais son emplacement (ADR-0010) : un Chrome ou Chromium accessible, soit sur le
# PATH, soit designe par la variable d'environnement CHROME. La ligne rendue sur la sortie standard
# est ecrite pour etre citee dans un fichier commite : elle ne contient aucun chemin.
#
# Usage : outils/mesurer-rendu.sh <largeur_css_px> <url> <fichier_png>
set -uo pipefail

# Plancher mesure : pour toute largeur demandee a --window-size inferieure a cette valeur, Chrome
# headless met la page en page comme si le viewport valait 500 px et decoupe le PNG a gauche. A
# partir de cette valeur, la largeur demandee est respectee (contenu = demande - 16 px de reserve de
# barre de defilement). Une fenetre parente egale a ce plancher est donc valide ; une de moins ne
# l'est pas.
PLANCHER_FENETRE_PX=516

# juger_invocation <largeur_css> <largeur_fenetre> <profil>
#   largeur_css      : largeur exacte a verifier, portee par le CSS de l'iframe (entier positif).
#   largeur_fenetre  : largeur demandee a --window-size pour la fenetre parente.
#   profil           : valeur destinee a --user-data-dir. Sans profil isole, la commande s'attache a
#                      une session Chrome deja ouverte sur le poste et ne mesure plus un headless.
# Rend 0 et une ligne citable sur la sortie standard si l'invocation mesure ce qu'elle pretend
# mesurer, 1 et un motif explicite sur la sortie d'erreur sinon. Ne lance aucune commande, et ne
# recopie jamais la valeur du profil - c'est un chemin de poste (ADR-0010).
juger_invocation() {
  largeur_css="$1"
  largeur_fenetre="$2"
  profil="$3"

  if ! printf '%s' "${largeur_css}" | LC_ALL=C grep -Eq '^[0-9]+$' || [ "${largeur_css}" -le 0 ]; then
    echo "largeur CSS demandee invalide : une largeur de viewport est un entier positif de pixels." >&2
    return 1
  fi
  if ! printf '%s' "${largeur_fenetre}" | LC_ALL=C grep -Eq '^[0-9]+$'; then
    echo "largeur de fenetre parente invalide : un entier de pixels est attendu." >&2
    return 1
  fi
  if [ "${largeur_fenetre}" -lt "${PLANCHER_FENETRE_PX}" ]; then
    echo "fenetre parente de ${largeur_fenetre} px demandee, sous le plancher de ${PLANCHER_FENETRE_PX} px : la capture montrerait un decoupage a gauche d'une mise en page a 500 px, jamais la largeur demandee. Mesure refusee." >&2
    return 1
  fi
  if [ -z "${profil}" ]; then
    echo "aucun profil isole fourni : sans --user-data-dir dedie, la commande s'attache a une session Chrome deja ouverte et ne mesure plus un rendu headless. Mesure refusee." >&2
    return 1
  fi
  if [ "${largeur_css}" -gt "${largeur_fenetre}" ]; then
    echo "largeur CSS de ${largeur_css} px demandee dans une fenetre parente de ${largeur_fenetre} px : l'iframe serait elle-meme decoupee. Mesure refusee." >&2
    return 1
  fi

  echo "invocation valide (largeur CSS mesuree : ${largeur_css} px | fenetre parente : ${largeur_fenetre} px | profil isole : oui)"
  return 0
}

# --- enveloppe : la seule partie qui touche le poste, non testee (meme limite qu'ADR-0009) ---
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
  if [ "$#" -ne 3 ]; then
    echo "usage : outils/mesurer-rendu.sh <largeur_css_px> <url> <fichier_png>" >&2
    exit 2
  fi
  largeur_demandee="$1"
  url="$2"
  png="$3"

  navigateur="${CHROME:-}"
  if [ -z "${navigateur}" ]; then
    navigateur="$(command -v chrome || command -v google-chrome || command -v chromium || true)"
  fi
  if [ -z "${navigateur}" ]; then
    echo "aucun navigateur trouve : mets un Chrome sur le PATH ou designe-le par la variable CHROME." >&2
    exit 2
  fi

  # La fenetre parente doit depasser le plancher ET contenir l'iframe avec sa marge de cadre.
  largeur_fenetre=$((largeur_demandee + 240))
  if [ "${largeur_fenetre}" -lt 600 ]; then largeur_fenetre=600; fi

  travail="$(mktemp -d)"
  trap 'rm -rf "${travail}"' EXIT
  profil="${travail}/profil"
  conteneur="${travail}/conteneur.html"

  juger_invocation "${largeur_demandee}" "${largeur_fenetre}" "${profil}" || exit 1

  # La largeur CSS d'une iframe n'est pas soumise au plancher de fenetre : c'est elle qui sert de
  # viewport a la page chargee dedans. Le cadre rouge d'un pixel rend le bord mesurable sur l'image.
  cat > "${conteneur}" <<HTML
<!DOCTYPE html>
<html lang="fr"><head><meta charset="utf-8">
<style>html,body{margin:0;padding:0;background:#ff0000}
iframe{display:block;width:${largeur_demandee}px;height:1400px;border:0;margin:0}</style>
</head><body><iframe src="${url}"></iframe></body></html>
HTML

  # Le navigateur est un binaire natif : sur un shell POSIX pose sur Windows, il ne sait pas resoudre
  # un chemin de la forme /tmp/... Trouve en prouvant l'enveloppe a la main - sans cette conversion,
  # la capture est produite, non vide, et uniformement grise : la page n'a jamais ete chargee, et le
  # code de sortie du navigateur reste 0. C'est le genre de faux positif qu'aucune fonction pure ne
  # peut attraper, d'ou la preuve manuelle exigee par ADR-0009.
  if command -v cygpath >/dev/null 2>&1; then
    url_conteneur="file:///$(cygpath -m "${conteneur}")"
    profil_navigateur="$(cygpath -w "${profil}")"
  else
    url_conteneur="file://${conteneur}"
    profil_navigateur="${profil}"
  fi

  "${navigateur}" --headless=new --disable-gpu --no-first-run --no-default-browser-check \
    --hide-scrollbars --virtual-time-budget=4000 --user-data-dir="${profil_navigateur}" \
    --window-size="${largeur_fenetre},1400" --screenshot="${png}" "${url_conteneur}" >/dev/null 2>&1
  code=$?
  if [ "${code}" -ne 0 ] || [ ! -s "${png}" ]; then
    echo "le navigateur n'a produit aucune capture (code ${code})." >&2
    exit 1
  fi
  # Limite nommee : ce script sait qu'une capture a ete ecrite, pas qu'elle montre quelque chose. Une
  # page qui ne se charge pas rend une image unie et un code de sortie 0. C'est l'etape de mesure
  # (bbox Pillow, ou l'oeil) qui le voit, et c'est pour cela que la methode du rapport de qualite s06
  # mesure l'image plutot que de croire la commande.
  echo "capture ecrite : le bord droit du contenu mesure est a x = ${largeur_demandee} px sur l'image, le fond rouge commence apres."
  exit 0
fi
