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
# Usage : outils/mesurer-rendu.sh <largeur_css_px> <url> <fichier_png> [hauteur_css_px]
#
# La hauteur (ADR-0028) est le quatrieme argument plutot que le deuxieme, et c'est un choix, pas une
# maladresse : les deux invocations deja ecrites dans des fichiers commites (ADR-0025 et DEC-0016,
# toutes deux "mesurer-rendu.sh 360 <url> <png>") continuent de vouloir dire ce qu'elles voulaient
# dire. Sans hauteur, la capture montre toute la page comme avant ; avec, elle ne montre que ce que
# l'enfant voit sans defiler, ce qui est la seule facon mecanique de lire DEC-0016.
set -uo pipefail

# Plancher mesure : pour toute largeur demandee a --window-size inferieure a cette valeur, Chrome
# headless met la page en page comme si le viewport valait 500 px et decoupe le PNG a gauche. A
# partir de cette valeur, la largeur demandee est respectee (contenu = demande - 16 px de reserve de
# barre de defilement). Une fenetre parente egale a ce plancher est donc valide ; une de moins ne
# l'est pas.
PLANCHER_FENETRE_PX=516

# Hauteur de la fenetre parente, et hauteur d'iframe par defaut : "toute la page", c'est-a-dire plus
# haut que n'importe quel ecran de recreo. C'est la valeur que l'enveloppe utilisait deja avant
# qu'une hauteur soit demandable (ADR-0025) ; le defaut ne change donc aucune mesure existante.
# Aucun plancher de hauteur n'est declare ici : contrairement au plancher de largeur, aucun n'a ete
# mesure sur ce poste, et ce script n'epingle pas un chiffre que personne n'a constate. La fenetre
# parente garde donc cette hauteur quelle que soit la hauteur demandee, et le juge refuse toute
# hauteur d'iframe qui la depasserait - la seule facon d'approcher un plancher inconnu serait de
# reduire la fenetre, ce que ce script ne fait jamais.
HAUTEUR_FENETRE_PX=1400

# juger_invocation <largeur_css> <largeur_fenetre> <profil> <hauteur_css> <hauteur_fenetre>
#   largeur_css      : largeur exacte a verifier, portee par le CSS de l'iframe (entier positif).
#   largeur_fenetre  : largeur demandee a --window-size pour la fenetre parente.
#   profil           : valeur destinee a --user-data-dir. Sans profil isole, la commande s'attache a
#                      une session Chrome deja ouverte sur le poste et ne mesure plus un headless.
#   hauteur_css      : hauteur exacte a verifier, portee par le CSS de l'iframe (entier positif).
#                      C'est elle qui place le pli : ce qui est au-dela ne figure pas sur l'image.
#   hauteur_fenetre  : hauteur demandee a --window-size pour la fenetre parente.
# Rend 0 et une ligne citable sur la sortie standard si l'invocation mesure ce qu'elle pretend
# mesurer, 1 et un motif explicite sur la sortie d'erreur sinon. Ne lance aucune commande, et ne
# recopie jamais la valeur du profil - c'est un chemin de poste (ADR-0010).
juger_invocation() {
  largeur_css="$1"
  largeur_fenetre="$2"
  profil="$3"
  hauteur_css="$4"
  hauteur_fenetre="$5"

  if ! printf '%s' "${largeur_css}" | LC_ALL=C grep -Eq '^[0-9]+$' || [ "${largeur_css}" -le 0 ]; then
    echo "largeur CSS demandee invalide : une largeur de viewport est un entier positif de pixels." >&2
    return 1
  fi
  if ! printf '%s' "${largeur_fenetre}" | LC_ALL=C grep -Eq '^[0-9]+$'; then
    echo "largeur de fenetre parente invalide : un entier de pixels est attendu." >&2
    return 1
  fi
  if ! printf '%s' "${hauteur_css}" | LC_ALL=C grep -Eq '^[0-9]+$' || [ "${hauteur_css}" -le 0 ]; then
    echo "hauteur CSS demandee invalide : une hauteur de viewport est un entier positif de pixels." >&2
    return 1
  fi
  if ! printf '%s' "${hauteur_fenetre}" | LC_ALL=C grep -Eq '^[0-9]+$'; then
    echo "hauteur de fenetre parente invalide : un entier de pixels est attendu." >&2
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
  # Le pli montre sur l'image doit etre celui du viewport demande, jamais celui de la fenetre qui le
  # contient : une iframe plus haute que sa fenetre est coupee par la fenetre, et l'agent lirait un
  # pli a 1400 px comme s'il etait a la hauteur qu'il a demandee. L'egalite est valide, et ce n'est
  # pas un detail : l'invocation par defaut demande exactement la hauteur de la fenetre, donc un juge
  # ecrit avec "-ge" au lieu de "-gt" refuserait toutes les mesures deja ecrites dans le depot.
  if [ "${hauteur_css}" -gt "${hauteur_fenetre}" ]; then
    echo "hauteur CSS de ${hauteur_css} px demandee dans une fenetre parente de ${hauteur_fenetre} px : l'image serait coupee par la fenetre et non par le pli demande. Mesure refusee." >&2
    return 1
  fi

  echo "invocation valide (largeur CSS mesuree : ${largeur_css} px | hauteur CSS mesuree : ${hauteur_css} px, pli a y = ${hauteur_css} px | fenetre parente : ${largeur_fenetre} x ${hauteur_fenetre} px | profil isole : oui)"
  return 0
}

# --- enveloppe : la seule partie qui touche le poste, non testee (meme limite qu'ADR-0009) ---
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
  if [ "$#" -lt 3 ] || [ "$#" -gt 4 ]; then
    echo "usage : outils/mesurer-rendu.sh <largeur_css_px> <url> <fichier_png> [hauteur_css_px]" >&2
    exit 2
  fi
  largeur_demandee="$1"
  url="$2"
  png="$3"
  hauteur_demandee="${4:-${HAUTEUR_FENETRE_PX}}"

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

  juger_invocation "${largeur_demandee}" "${largeur_fenetre}" "${profil}" \
    "${hauteur_demandee}" "${HAUTEUR_FENETRE_PX}" || exit 1

  # La largeur CSS d'une iframe n'est pas soumise au plancher de fenetre : c'est elle qui sert de
  # viewport a la page chargee dedans. Le cadre rouge d'un pixel rend le bord mesurable sur l'image.
  # Sa hauteur joue le meme role pour le pli : l'iframe est le viewport, donc ce qui depasse sa
  # hauteur n'est pas rendu et le fond rouge reprend a y = hauteur demandee. C'est exactement ce
  # qu'un enfant ne voit pas sans defiler (DEC-0016, ADR-0028).
  cat > "${conteneur}" <<HTML
<!DOCTYPE html>
<html lang="fr"><head><meta charset="utf-8">
<style>html,body{margin:0;padding:0;background:#ff0000}
iframe{display:block;width:${largeur_demandee}px;height:${hauteur_demandee}px;border:0;margin:0}</style>
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
    --window-size="${largeur_fenetre},${HAUTEUR_FENETRE_PX}" --screenshot="${png}" "${url_conteneur}" >/dev/null 2>&1
  code=$?
  if [ "${code}" -ne 0 ] || [ ! -s "${png}" ]; then
    echo "le navigateur n'a produit aucune capture (code ${code})." >&2
    exit 1
  fi
  # Limite nommee : ce script sait qu'une capture a ete ecrite, pas qu'elle montre quelque chose. Une
  # page qui ne se charge pas rend une image unie et un code de sortie 0. C'est l'etape de mesure
  # (bbox Pillow, ou l'oeil) qui le voit, et c'est pour cela que la methode du rapport de qualite s06
  # mesure l'image plutot que de croire la commande.
  echo "capture ecrite : le bord droit du contenu mesure est a x = ${largeur_demandee} px sur l'image, le pli a y = ${hauteur_demandee} px, le fond rouge commence apres l'un et l'autre. Ce qui n'est pas sur l'image est ce que l'enfant ne voit pas sans defiler."
  exit 0
fi
