#!/usr/bin/env bash
# Verifie qu'un worktree descend de round-two avant tout travail (S6-13, retro de s05 point 4).
#
# Ce garde-fou n'a pas la forme de .github/scripts/verifier-prefixes.sh ou verifier-chemins.sh
# (ADR-0009) : son verdict depend de l'etat du poste ou il tourne (le worktree courant), pas d'une
# entree fournie sur l'entree standard ou en argument. Un test qui l'executerait tel quel ne
# verifierait qu'une chose - que le worktree du testeur est bien provisionne - et serait vert par
# accident sur un poste correct, rouge par accident sur un poste fautif (avis de faisabilite s06,
# section S6-13). Le script est donc coupe en deux :
#
#   - juger_lignee est une fonction pure : elle recoit ce que git a repondu (nom de la branche
#     courante, code de sortie de "git merge-base round-two HEAD" ci-dessous) et rend un verdict,
#     sans lancer aucune commande. C'est elle que verifier-lignee-test.sh met a l'epreuve, avec des
#     entrees fabriquees.
#   - l'enveloppe, en bas de ce fichier, est la seule partie qui touche le poste. Elle n'appelle que
#     "git rev-parse --abbrev-ref HEAD" et "git merge-base round-two HEAD" ; ce sont les bonnes
#     commandes, verifie une fois a la main sur un worktree fautif reel plutot que suppose (meme
#     limite assumee que la plage de commits d'ADR-0009).
#
# Deux amendements du 2026-08-07 et du 2026-08-10 (s06), sur le meme defaut de fond.
#
# Le premier (2026-08-07) ne testait qu'un sens - "round-two est un ancetre de HEAD", c'est-a-dire
# que HEAD contient tout round-two. Cette propriete n'est vraie qu'a l'instant du provisionnement :
# des qu'un lot est fusionne sur round-two apres coup, un worktree sain, jamais retouche, devenait
# rouge sans avoir rien fait de mal (`theo-archi`, worktree provisionne sur cf48cae, round-two ayant
# depuis avance de trois commits). Corrige en testant aussi le sens inverse - "HEAD est un ancetre de
# round-two" - pour couvrir le cas d'un worktree simplement en retard.
#
# Le second (2026-08-10, trouve par `nadia-prof`) restait faux sur un troisieme cas : un worktree
# sain forke d'une tete ancienne de round-two, auquel un commit propre s'ajoute ensuite (la branche
# jetable de S6-6/S6-7, forkee a abe2f99 puis porteuse d'un commit de catalogue). Ni "round-two est
# ancetre de HEAD" ni "HEAD est ancetre de round-two" n'est vrai dans ce cas : les deux tests
# rendaient donc un refus, a tort. La propriete que le script doit verifier n'est ni l'une ni l'autre
# direction d'ancetralite : c'est l'existence d'un ancetre commun entre HEAD et round-two, quel que
# soit lequel des deux a avance depuis - "git merge-base round-two HEAD" rend un ancetre commun des
# qu'il en existe un, dans les trois cas sains (round-two ancetre de HEAD, HEAD ancetre de round-two,
# ou les deux ont diverge depuis un point commun anterieur), et echoue sans en trouver un seul quand
# les deux lignees sont reellement etrangeres l'une a l'autre - fait verifie par `nadia-prof` : une
# branche forkee de `main` n'a aucun ancetre commun avec `round-two` (deux branches orphelines), la
# ou une branche saine en a toujours un qui est sur `round-two`. Les deux appels a
# "git merge-base --is-ancestor" du premier amendement sont donc remplaces par ce seul appel, plus
# general et qui n'a pas besoin d'etre double.
#
# Premier critere de S6-13 (jamais de chemin absolu de poste de travail dans le message) : ce script
# n'a de toute facon aucune raison d'en lire un ("git rev-parse --abbrev-ref HEAD" rend un nom de
# branche, jamais un chemin). En defense en profondeur, juger_lignee refuse de recopier une entree
# qui ressemblerait a un chemin de poste plutot qu'a un nom de branche.
set -uo pipefail

# juger_lignee <branche_courante> <code_ancetre_commun>
#   branche_courante   : nom rendu par "git rev-parse --abbrev-ref HEAD" ("HEAD" si le worktree est
#                         en HEAD detache, chaine vide si indeterminable).
#   code_ancetre_commun : code de sortie de "git merge-base round-two HEAD"
#                         (0 = un ancetre commun existe : ce worktree est sur la lignee de round-two,
#                         qu'il soit a jour, en avance, en retard, ou qu'il ait diverge depuis un
#                         point commun anterieur avec son propre commit ;
#                         1 = aucun ancetre commun : lignee reellement etrangere a round-two ;
#                         tout autre code = la reference "round-two" est introuvable dans ce
#                         worktree).
# Rend 0 et un message de confirmation sur la sortie standard si le worktree descend de la lignee de
# round-two, 1 et un message explicite sur la sortie d'erreur sinon. Ne lance aucune commande.
juger_lignee() {
  branche="$1"
  code_ancetre_commun="$2"

  # Defense en profondeur : un nom de branche valide ne peut pas contenir la forme d'un chemin
  # absolu de poste de travail (git refuse ces caracteres dans un nom de reference), mais si une
  # entree corrompue en fournissait un, ce message ne le recopie jamais (ADR-0010).
  ctx='(^|[^0-9A-Za-z/])'
  segment='[0-9A-Za-z_]'
  chemin_poste="${ctx}([A-Za-z]:[\\/]${segment}|/[a-z]/${segment}|/home/${segment}|/Users/${segment})"
  if printf '%s' "${branche}" | LC_ALL=C grep -Eq "${chemin_poste}"; then
    echo "worktree sur une reference dont le nom ne peut pas etre affiche en toute securite : verification refusee." >&2
    return 1
  fi

  if [ -z "${branche}" ]; then
    echo "impossible de determiner la branche courante de ce worktree : verification refusee." >&2
    return 1
  fi

  if [ "${branche}" = "HEAD" ]; then
    echo "worktree en HEAD detache : aucune branche a nommer, verification refusee. Rattache-le a une branche descendant de round-two avant de continuer." >&2
    return 1
  fi

  case "${code_ancetre_commun}" in
    0)
      echo "le worktree sur la branche '${branche}' descend de la lignee de round-two (ancetre commun trouve dans son historique)."
      return 0
      ;;
    1)
      echo "le worktree est provisionne sur la branche '${branche}', qui ne descend pas de round-two (aucun ancetre commun)." >&2
      return 1
      ;;
    *)
      echo "impossible de verifier que la branche '${branche}' descend de round-two : la reference round-two est introuvable dans ce worktree." >&2
      return 1
      ;;
  esac
}

# --- enveloppe : la seule partie qui touche le poste, non testee (meme limite qu'ADR-0009) ---
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
  branche_courante="$(git rev-parse --abbrev-ref HEAD 2>/dev/null || true)"
  git merge-base round-two HEAD >/dev/null 2>&1
  code_ancetre_commun=$?
  juger_lignee "${branche_courante}" "${code_ancetre_commun}"
  exit $?
fi
