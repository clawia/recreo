#!/usr/bin/env bash
# Contre-exemples des trois juges de squad/outils/nadia-prof/balayage-largeur-rangee.py.
#
# Pourquoi ce fichier existe. Le 2026-08-27, une campagne de mesure du pli a perdu 8 jeux sur 17
# sans le dire : le script s'attachait au premier navigateur qui repondait sur un port fixe, sans
# verifier que c'etait le sien. Le mode de defaillance n'etait pas l'echec, c'etait l'ecart muet.
# Les trois refus qui le ferment sont des juges purs - une entree fabriquee, un motif de refus ou
# None - et ce sont eux qui sont testes ici. L'enveloppe, elle, touche le poste (un navigateur) et
# sa preuve est manuelle, nommee dans le lot de S21-1 : meme decoupe qu'ADR-0009 impose a
# verifier-lignee.sh et qu'ADR-0025 impose a mesurer-rendu.sh.
#
# Correction datee du 2026-08-28. Ce paragraphe decrivait la decoupe comme faite ; elle ne l'etait
# que dans l'intention. Le module portait "import websocket" en tete de fichier, si bien qu'aucun
# juge n'etait atteignable sans la bibliotheque qui pilote le navigateur : la CI a rougi sur les 23
# cas d'un coup, sans en juger un seul. Un juge pur ecrit plus bas dans le meme fichier que
# l'enveloppe n'est pas separe d'elle. L'import vit desormais dans la seule fonction qui ouvre la
# connexion, et le dernier cas de ce fichier verifie la decoupe au lieu de l'affirmer.
#
# Comme pour un contre-exemple ArchUnit, un cas qui echoue ne suffit pas : il doit echouer pour le
# bon motif. Chaque cas attendu en refus verifie donc la presence d'un fragment du message.
#
# Prerequis nomme, jamais son emplacement (ADR-0010) : un interpreteur Python 3, designe par la
# variable PYTHON ou trouve sous les noms usuels. Son absence est un ECHEC, jamais un saut : un
# test qui se met en veille quand son prerequis manque est un vert qui ne prouve rien.
set -uo pipefail

REPERTOIRE="$(cd "$(dirname "$0")" && pwd)"
MODULE="${REPERTOIRE}/balayage-largeur-rangee.py"

PY="${PYTHON:-}"
if [ -z "${PY}" ]; then
  for candidat in python3 python; do
    if command -v "${candidat}" > /dev/null 2>&1 && "${candidat}" -c 'import sys; sys.exit(0 if sys.version_info[0] == 3 else 1)' > /dev/null 2>&1; then
      PY="${candidat}"
      break
    fi
  done
fi
if [ -z "${PY}" ]; then
  echo "ECHEC : aucun interpreteur Python 3 trouve. Designe-le par la variable PYTHON."
  exit 1
fi

ECHECS=0

# Charge le module (son nom porte des tirets, donc pas d'import ordinaire) et appelle un juge.
# Rend le motif de refus sur la sortie standard, ou la chaine vide quand le juge laisse passer.
juger() {
  MODULE="${MODULE}" "${PY}" - "$@" <<'PYTHON'
import importlib.util
import json
import os
import sys

spec = importlib.util.spec_from_file_location("blr", os.environ["MODULE"])
blr = importlib.util.module_from_spec(spec)
spec.loader.exec_module(blr)

nom = sys.argv[1]
arguments = [json.loads(a) for a in sys.argv[2:]]
rendu = getattr(blr, nom)(*arguments)
# Un juge rend soit un motif seul, soit le couple (valeur, motif) quand il a aussi quelque chose
# a extraire. Dans les deux cas c'est le motif qui est juge ici.
motif = rendu[-1] if isinstance(rendu, tuple) else rendu
sys.stdout.write("" if motif is None else motif)
PYTHON
}

verifier() {
  intitule="$1"
  attendu="$2"   # "refus" ou "passe"
  fragment="$3"
  shift 3
  motif="$(juger "$@" 2>&1)"
  code=$?
  if [ "${code}" -ne 0 ]; then
    echo "ECHEC : ${intitule} - l'appel du juge a lui-meme echoue : ${motif}"
    ECHECS=$((ECHECS + 1))
    return
  fi
  if [ "${attendu}" = "passe" ]; then
    if [ -n "${motif}" ]; then
      echo "ECHEC : ${intitule} - refus inattendu : ${motif}"
      ECHECS=$((ECHECS + 1))
    else
      echo "ok ${intitule}"
    fi
    return
  fi
  if [ -z "${motif}" ]; then
    echo "ECHEC : ${intitule} - aucun refus, alors que la mesure n'est pas fiable."
    ECHECS=$((ECHECS + 1))
    return
  fi
  case "${motif}" in
    *"${fragment}"*) echo "ok ${intitule}" ;;
    *)
      echo "ECHEC : ${intitule} - refus obtenu, mais pas pour le motif attendu."
      echo "  attendu (fragment) : ${fragment}"
      echo "  obtenu             : ${motif}"
      ECHECS=$((ECHECS + 1))
      ;;
  esac
}

echo "--- juge 1a : un port FORCE repond-il AVANT qu'on lance quoi que ce soit"

verifier "un port force et muet avant le lancement est accepte" passe "" \
  juger_port_libre_avant_lancement '9319' 'null'

verifier "un port force qui repond deja est refuse, et le port est cite" refus "9319" \
  juger_port_libre_avant_lancement '9319' '"{\"Browser\":\"Chrome/140.0\"}"'

verifier "le refus cite la reponse recue, pour qu'on sache a qui est ce navigateur" refus "Chrome/140.0" \
  juger_port_libre_avant_lancement '9319' '"{\"Browser\":\"Chrome/140.0\"}"'

verifier "une reponse vide reste une reponse : le port force est tenu, donc refuse" refus "deja pris" \
  juger_port_libre_avant_lancement '9319' '""'

verifier "le port 0 n'est pas un port force : rien a sonder, accepte" passe "" \
  juger_port_libre_avant_lancement '0' 'null'

echo "--- juge 1b : quel port NOTRE propre profil declare-t-il avoir ouvert"

# Le juge rend le couple (port reellement ouvert, motif). Le harnais n'affiche que le motif ; les
# deux cas qui portent sur le port rendu sont verifies juste apres, separement.
verifier "le profil declare un port attribue : accepte" passe "" \
  juger_port_du_profil '0' '"57387\n/devtools/browser/8c97b034\n"'

verifier "aucun DevToolsActivePort ecrit : refuse" refus "n'a ecrit aucun DevToolsActivePort" \
  juger_port_du_profil '0' 'null'

verifier "le refus dit qu'un port devine reviendrait a mesurer la fenetre d'un autre" refus "la fenetre d'un autre agent" \
  juger_port_du_profil '0' 'null'

verifier "un DevToolsActivePort illisible : refuse" refus "ne commence pas par un numero de port" \
  juger_port_du_profil '0' '"ws://127.0.0.1:9319\n"'

verifier "un DevToolsActivePort vide : refuse" refus "ne commence pas par un numero de port" \
  juger_port_du_profil '0' '"   \n"'

verifier "un DevToolsActivePort qui declare le port 0 : refuse" refus "n'a ouvert aucun port de deverminage" \
  juger_port_du_profil '0' '"0\n/devtools/browser/8c97b034\n"'

verifier "un port force est rendu tel quel, sans exiger de fichier : accepte" passe "" \
  juger_port_du_profil '9319' 'null'

# Le port rendu est ce a quoi le script s'attache : un juge qui refuserait bien mais rendrait le
# mauvais port ne garderait rien. Les deux cas sont donc verifies sur la valeur, pas sur le motif.
port_rendu="$(MODULE="${MODULE}" "${PY}" - <<'PYTHON'
import importlib.util, os, sys
spec = importlib.util.spec_from_file_location("blr", os.environ["MODULE"])
blr = importlib.util.module_from_spec(spec)
spec.loader.exec_module(blr)
attribue = blr.juger_port_du_profil(0, "57387\n/devtools/browser/8c97b034\n")[0]
force = blr.juger_port_du_profil(9319, None)[0]
refuse = blr.juger_port_du_profil(0, None)[0]
sys.stdout.write("%s %s %s" % (attribue, force, refuse))
PYTHON
)"
if [ "${port_rendu}" = "57387 9319 None" ]; then
  echo "ok le juge rend le port du profil, le port force, et rien quand il refuse"
else
  echo "ECHEC : le juge ne rend pas les bons ports. Attendu '57387 9319 None', obtenu '${port_rendu}'."
  ECHECS=$((ECHECS + 1))
fi

echo "--- juge 2 : la regle injectee est-elle encore dans le document a cette lecture"

verifier "la regle posee est celle qui est lue : accepte" passe "" \
  juger_regle_injectee '".message-jeu{position:sticky;bottom:0}"' '".message-jeu{position:sticky;bottom:0}"'

verifier "la regle a disparu du document : refuse et dit pourquoi" refus "a disparu du document" \
  juger_regle_injectee '".message-jeu{position:sticky;bottom:0}"' 'null'

verifier "le refus nomme la navigation complete comme cause" refus "recree le <head>" \
  juger_regle_injectee '".message-jeu{position:sticky;bottom:0}"' 'null'

verifier "la regle lue n'est plus celle qui a ete posee : refuse et cite les deux" refus "Posee : .a{x:1} | lue : .a{x:2}" \
  juger_regle_injectee '".a{x:1}"' '".a{x:2}"'

verifier "un outil qui n'injecte rien n'est pas rendu rouge (regle attendue nulle)" passe "" \
  juger_regle_injectee 'null' 'null'

verifier "un outil qui n'injecte rien reste vert meme si le document porte un autre style" passe "" \
  juger_regle_injectee '""' '".autre{x:1}"'

echo "--- juge 3 : le viewport du rapport est-il lu du navigateur"

verifier "le viewport lu est celui demande : accepte" passe "" \
  juger_viewport '[744, 551]' '[744, 551]'

verifier "le viewport n'a pas pu etre lu : refuse" refus "n'a pas pu etre lu" \
  juger_viewport '[744, 551]' 'null'

verifier "un ecart de largeur est refuse, et les deux couples sont cites" refus "demande 744x551, viewport lu du navigateur 1280x551" \
  juger_viewport '[744, 551]' '[1280, 551]'

verifier "un ecart de hauteur seule est refuse" refus "744x551, viewport lu du navigateur 744x900" \
  juger_viewport '[744, 551]' '[744, 900]'

echo "--- les juges sont atteignables sans aucune dependance hors bibliotheque standard"

# Ce cas existe parce que le vert local ne prouvait rien. Le 2026-08-28, l'etape "Juges purs des
# outils de poste" de la CI a rougi sur les 23 cas d'un coup, sans en juger un seul : le module
# faisait "import websocket" au niveau du module, et le runner n'a pas cette bibliotheque. Sur ce
# poste, elle est installee - les memes 23 cas passaient. Une mesure juste, faite sur un
# environnement qui n'est pas celui qu'on croit mesurer : la famille du "-pl" sans "-am".
#
# Le cas ci-dessous rend la panne reproductible PARTOUT, sans dependre de ce qui est installe : il
# charge le module dans un interpreteur ou tout import hors bibliotheque standard est refuse, et
# appelle un juge. Le perimetre des noms refuses est DERIVE de sys.stdlib_module_names, jamais une
# liste ecrite ici : une dependance tierce neuve, quel que soit son nom, tombe dedans sans que
# personne y pense.
sans_tiers="$(MODULE="${MODULE}" "${PY}" - <<'PYTHON' 2>&1
import importlib.abc
import importlib.util
import os
import sys


class RefuseToutTiers(importlib.abc.MetaPathFinder):
    """Refuse tout import dont le nom de tete n'est pas dans la bibliotheque standard."""

    def find_spec(self, nom, chemin, cible=None):
        tete = nom.split(".")[0]
        if tete in sys.stdlib_module_names or tete == "blr":
            return None
        raise ImportError(
            "import tiers refuse par ce cas de test : %s. Un juge pur doit rester atteignable "
            "sans aucune dependance hors bibliotheque standard (ADR-0009)." % nom
        )


sys.meta_path.insert(0, RefuseToutTiers())
spec = importlib.util.spec_from_file_location("blr", os.environ["MODULE"])
blr = importlib.util.module_from_spec(spec)
spec.loader.exec_module(blr)

# Un juge de chaque famille, pour que le cas ne se contente pas de charger le module.
verdicts = [
    blr.juger_port_libre_avant_lancement(9319, None),
    blr.juger_port_du_profil(
        0, "57387" + chr(10) + "/devtools/browser/8c97b034" + chr(10))[1],
    blr.juger_regle_injectee(".a{x:1}", None),
    blr.juger_viewport([744, 551], [744, 900]),
]
sys.stdout.write("juges atteints : %d refus sur 4 appels" % sum(1 for v in verdicts if v))
PYTHON
)"
code_sans_tiers=$?

if [ "${code_sans_tiers}" -ne 0 ] || [ "${sans_tiers}" != "juges atteints : 2 refus sur 4 appels" ]; then
  echo "ECHEC : les juges ne sont pas atteignables sans dependance tierce."
  echo "  Un import de bibliotheque au niveau du module rend TOUS les cas de ce fichier rouges"
  echo "  avant le moindre jugement, et le vert de ce poste ne le montre pas - c'est la panne de"
  echo "  CI du 2026-08-28. Deplacer l'import dans la fonction qui en a besoin."
  echo "  Sortie obtenue : ${sans_tiers}"
  ECHECS=$((ECHECS + 1))
else
  echo "ok les quatre juges repondent dans un interpreteur qui refuse tout import tiers"
fi

echo "--- la campagne nomme les jeux perdus au lieu de se declarer terminee"

# Le defaut de s21 n'etait pas seulement dans les juges : campagne-budget-vertical.sh ignorait le
# code de sortie de chaque mesure et ecrivait "campagne terminee" quoi qu'il arrive. Le cas ci-
# dessous n'a besoin d'aucun navigateur : sans CHROME et sans PATH utilisable, chaque mesure sort
# en erreur, et c'est exactement la situation ou l'ancienne campagne perdait des jeux en silence.
CAMPAGNE="$(cd "${REPERTOIRE}/../theo-archi" && pwd)/campagne-budget-vertical.sh"
temporaire="$(mktemp -d)"
journal="$(CHROME=/introuvable/aucun-navigateur PATH=/usr/bin:/bin \
  bash "${CAMPAGNE}" http://127.0.0.1:1 744 "${temporaire}" 2>&1)"
code_campagne=$?

if [ "${code_campagne}" -eq 0 ]; then
  echo "ECHEC : la campagne sort a 0 alors qu'aucun jeu n'a pu etre mesure."
  ECHECS=$((ECHECS + 1))
else
  echo "ok une campagne dont les mesures echouent sort en erreur"
fi

case "${journal}" in
  *"CAMPAGNE INCOMPLETE"*) echo "ok la campagne se declare incomplete" ;;
  *)
    echo "ECHEC : la campagne n'a pas declare son incompletude. Journal :"
    echo "${journal}"
    ECHECS=$((ECHECS + 1))
    ;;
esac

case "${journal}" in
  *"perdu : bandit-manchot-"*) echo "ok la campagne nomme les jeux perdus un par un" ;;
  *)
    echo "ECHEC : la campagne n'a nomme aucun jeu perdu. Journal :"
    echo "${journal}"
    ECHECS=$((ECHECS + 1))
    ;;
esac

case "${journal}" in
  *"campagne terminee"*)
    echo "ECHEC : la campagne s'est declaree terminee alors qu'elle a perdu des jeux."
    ECHECS=$((ECHECS + 1))
    ;;
  *) echo "ok la campagne ne se declare pas terminee quand elle a perdu des jeux" ;;
esac

# Le cas precedent est attrape par deux conditions a la fois : la mesure sort en erreur ET elle
# n'ecrit aucun rapport. Une condition attrapee par une autre n'est prouvee par personne - c'est
# la regle decorative que ce depot sanctionne. Le cas ci-dessous n'exerce donc que le code de
# sortie : l'interpreteur fabrique ecrit un rapport parfaitement lisible, puis sort en erreur.
stub="${temporaire}/interpreteur-fabrique.sh"
cat > "${stub}" <<'STUB'
#!/usr/bin/env bash
# $1 est le script de mesure, $5 le fichier de rapport attendu.
printf '{"jeu": "fabrique", "itemsVus": 0}' > "$5"
exit 3
STUB
chmod +x "${stub}"

sortie_stub="${temporaire}/avec-rapport"
journal_stub="$(PYTHON="${stub}" bash "${CAMPAGNE}" http://127.0.0.1:1 744 "${sortie_stub}" 2>&1)"
code_stub=$?

if [ "${code_stub}" -eq 0 ]; then
  echo "ECHEC : une mesure sortie en erreur mais qui a ecrit un rapport lisible passe inapercue."
  ECHECS=$((ECHECS + 1))
else
  echo "ok un rapport lisible ne rachete pas une mesure sortie en erreur"
fi

case "${journal_stub}" in
  *"(sortie 3)"*) echo "ok le code de sortie de la mesure perdue est cite" ;;
  *)
    echo "ECHEC : le code de sortie de la mesure perdue n'est pas cite. Journal :"
    echo "${journal_stub}"
    ECHECS=$((ECHECS + 1))
    ;;
esac

rm -rf "${temporaire}"

if [ "${ECHECS}" -gt 0 ]; then
  echo "${ECHECS} cas en echec : un outil de mesure du pli peut de nouveau mesurer autre chose que ce qu'il annonce."
  exit 1
fi
echo "Tous les cas passent : les trois juges refusent, et la campagne nomme ce qu'elle perd."
