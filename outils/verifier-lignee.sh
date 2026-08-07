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
#     courante, code de sortie de "git merge-base --is-ancestor") et rend un verdict, sans lancer
#     aucune commande. C'est elle que verifier-lignee-test.sh met a l'epreuve, avec des entrees
#     fabriquees.
#   - l'enveloppe, en bas de ce fichier, est la seule partie qui touche le poste. Elle n'appelle
#     que "git rev-parse --abbrev-ref HEAD" et "git merge-base --is-ancestor round-two HEAD" ; ce
#     sont les deux bonnes commandes, verifie une fois a la main sur un worktree fautif reel plutot
#     que suppose (meme limite assumee que la plage de commits d'ADR-0009).
#
# Premier critere de S6-13 (jamais de chemin absolu de poste de travail dans le message) : ce
# script n'a de toute facon aucune raison d'en lire un ("git rev-parse --abbrev-ref HEAD" rend un
# nom de branche, jamais un chemin). En defense en profondeur, juger_lignee refuse de recopier une
# entree qui ressemblerait a un chemin de poste plutot qu'a un nom de branche.
set -uo pipefail

# juger_lignee <branche_courante> <code_ancetralite>
#   branche_courante : nom rendu par "git rev-parse --abbrev-ref HEAD" ("HEAD" si le worktree est
#                       en HEAD detache, chaine vide si indeterminable).
#   code_ancetralite  : code de sortie de "git merge-base --is-ancestor round-two HEAD"
#                       (0 = descend de round-two, 1 = n'en descend pas, tout autre code = la
#                       reference "round-two" est introuvable dans ce worktree).
# Rend 0 et un message de confirmation sur la sortie standard si le worktree descend de round-two,
# 1 et un message explicite sur la sortie d'erreur sinon. Ne lance aucune commande.
juger_lignee() {
  branche="$1"
  code_ancetralite="$2"

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

  case "${code_ancetralite}" in
    0)
      echo "le worktree sur la branche '${branche}' descend de round-two."
      return 0
      ;;
    1)
      echo "le worktree est provisionne sur la branche '${branche}', qui ne descend pas de round-two." >&2
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
  git merge-base --is-ancestor round-two HEAD 2>/dev/null
  code_ancetralite=$?
  juger_lignee "${branche_courante}" "${code_ancetralite}"
  exit $?
fi
