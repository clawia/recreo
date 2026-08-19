# Fiche de conformite - bandit-manchot-synonymes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-synonymes.yaml`
  (a creer, `type: question`, au moins 20 items), story `S17-3` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/associer/cm1-synonymes.yml` (12 paires mot/synonyme, forme
  d'association par paires - `gameKey: associer`). **Reformulation de forme requise avant reprise** :
  aucune mecanique d'association par paires n'existe dans `domain/mechanics` (seule
  `banditmanchot` y est implementee) ; en ouvrir une serait une question d'architecture hors de mon
  mandat. Chaque paire est donc reprise comme matiere (le mot et son synonyme), reformulee en
  question a choix multiple ("Quel mot veut dire la meme chose que `<mot>` ?") pour rester dans le
  schema `type: question` existant. Reprise = reprise de la matiere, pas de la structure
  (`contenu-repris/README.md`) : chaque item repasse devant `nadia-prof` comme un contenu neuf.
- **Statut global** : **en construction** - aucun code, aucun contenu neuf ecrit a l'ouverture de
  cette fiche. Arbitrage prealable bloquant pose ci-dessous, avant tout premier commit.
- **Derniere mise a jour** : 2026-08-19 (ouverture, `camille-po`, s17).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | `squad/programme/francais/competences-cm1.md` |

**Couverture partielle assumee a l'ouverture, nommee pour ne pas etre decouverte a l'audit** - sur
le meme modele que toutes les couvertures partielles deja nommees sur ce produit (additions
"jusqu'a 10 (puis 20)", homophones "les principaux", conjugaison "un temps sur trois", comparer
"sans ranger", tables de multiplication "sans mesure d'automatisation") : **ce paquet ne porte que
des synonymes**. Aucun antonyme n'y figure, faute de matiere premiere identifiee dans
`contenu-repris/` (verifie par recherche du mot "antonyme" et de ses derives sur l'ensemble du
dossier : aucun fichier n'en porte). Un Done pedagogique accorde sur ce paquet ne fera donc jamais
passer cette ligne a `couvert-valide` sans une extension ulterieure aux antonymes, meme couverte a
son maximum sur les synonymes seuls.

## Criteres pedagogiques attendus - camille-po

- [ ] Le paquet compte au moins 20 items (le double de la matiere premiere reprise), pour que deux
  parties de dix manches ne se ressemblent pas.
- [ ] Pour chaque item, la bonne reponse est un synonyme reellement interchangeable avec le mot de
  l'enonce dans au moins une phrase usuelle d'un enfant de CM1, jamais un synonyme rare, technique
  ou hors du vocabulaire courant de cet age.
- [ ] Pour chaque item, aucun des deux ou trois distracteurs n'est lui-meme un synonyme acceptable
  du mot de l'enonce (pas de double bonne reponse).
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1, qui aide a
  retrouver le sens du mot sans jamais nommer la reponse (condition **M2** de l'arbitrage de
  mecanique en vigueur pour tout le catalogue, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`).
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve, et sans mecanique d'association par paires.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : <a rendre>
- **Date** : <a rendre>
- **Audit joue** : <a rendre>

### Ce qui a ete verifie

- **Ancrage au programme** :
- **Lisibilite au niveau vise** :
- **Charge cognitive** :
- **Progression** :
- **Traitement de l'erreur** :
- **Exactitude du contenu** :
- **Biais et ton** :

### Motif

### Reserves

## Arbitrage prealable bloquant, avant tout premier commit de code

Pose par `camille-po` a l'ouverture de cette fiche, sur le modele impose depuis l'incident du "Bon
verbe" en s07 (`squad/README.md`, section 10). Deux questions, toutes deux avant tout code :

1. **La forme retenue (question a choix multiple sur un mot isole) est-elle un exercice valide
   d'"utiliser des synonymes... pour preciser ou nuancer un propos" ?** Le referentiel dit
   "utiliser", un verbe de production dans une phrase ; la forme MCQ ne fait que reconnaitre un mot
   parmi trois ou quatre, jamais l'employer dans un propos. C'est un proxy, au meme sens que le
   proxy de transcription chiffres/lettres accepte par `nadia-prof` pour "lire"/"ecrire" les grands
   nombres CM1 (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1) : je ne le declare pas
   valide moi-meme, je le nomme et je demande son jugement avant d'ecrire le premier item.
2. **Le perimetre "synonymes seuls, antonymes non couverts" est-il acceptable comme premiere
   ouverture en couverture partielle nommee ?** Sur le meme modele que "notion de fraction simple"
   ouverte seule en mathematiques CM1 (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1,
   couverture partielle nommee).

Si l'une des deux reponses est un refus du principe, la story `S17-3` est reportee sans qu'aucun
code n'ait ete ecrit sur sa base.
