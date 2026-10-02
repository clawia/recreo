# Arbitrage préalable du 2026-10-02 - s47, "œufs" sans ligature dans `LVE-CP-06`

- **Auteur** : `nadia-prof`. **Date** : 2026-10-02. **Sprint** : s47.
- **Ce qui m'est posé** : la question Q1 de `theo-archi`
  (`squad/sprints/s47-tables-ce1-decouverte-cp-et-emc/confirmation-archunit.md`, section 6.2) :
  le distracteur *"on cherche des oeufs en chocolat dans le jardin"* de
  `cp-anglais-culture.yaml` (ligne 81), écrit sans ligature par `5015d96c`, tient-il l'exigence de
  `DEC-0002` (amendement du 2026-08-04) ?
- **Ce document ne prononce aucun Done**, ne crée aucun identifiant de condition ni de réserve, ne
  modifie ni contenu ni test. Je n'ai rien joué : la question porte sur une graphie, lue dans le
  fichier et dans l'historique.

---

## 1. Décision

**La substitution dégrade le texte lu par l'enfant. La ligature revient : "œufs".** Elle revient
dans le même geste que la correction du harnais de `PropositionsAccentueesJouablesIT`, jamais
avant, pour ne pas rendre la CI rouge.

**Le contenu destiné à l'enfant ne se plie pas à la limite d'un test.** Quand un test refuse un
texte juste, c'est le test qu'on corrige. `theo-archi` a établi (section 6.2) que le refus venait
du harnais, qui lève avant d'envoyer la requête, et non du produit.

## 2. Ce qui est à moi et ce qui ne l'est pas

- **L'orthographe du texte lu par l'enfant n'est pas ma porte** : elle appartient au
  `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). C'est la position
  que j'ai déjà tenue sur *coeur* le 2026-09-11 (`FICHE-bandit-manchot-corps-effort-eps-cp.md`,
  *"Ce n'est pas ma porte. Route, pas corrigé."*). Je ne la déplace pas.
- **Je ne tranche donc pas l'orthographe à la place du `relecteur-pedagogique` : il l'a déjà
  tranchée.** En s34, il a corrigé *coeur* en *cœur* sur un paquet de CP
  (`squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/relecture.md`, section 1.1). Les
  deux mots relèvent de la même règle. Le message de `5015d96c` dit que la forme sans ligature est
  *"déjà admise en français courant"*. C'est une tolérance typographique, quand le caractère
  manque au clavier. Ce n'est pas l'orthographe que le relecteur a appliquée sur ce dépôt.
- **Ce qui est à moi, c'est le fond** : l'amendement que j'ai demandé le 2026-08-04 fonde
  l'exigence sur un enfant qui construit *"ses correspondances graphèmes-phonèmes et son
  orthographe lexicale"* sur ce qu'on lui montre. Un distracteur se lit comme une bonne réponse :
  l'enfant de CP voit la forme du mot, quel que soit le statut de la proposition. Montrer "oeufs"
  à cet enfant pour faire passer un test n'a aucun motif pédagogique.

**"oeufs" n'est pas un motif de refus de ma porte sur `LVE-CP-06`.** Le Done pédagogique de ce
paquet reste à prononcer à son audit, sur mes critères. Le retour de la ligature est routé
(section 4), pas conditionné à mon verdict.

## 3. Correction d'un fait enregistré

Le message de `5015d96c` écrit que "œufs" est *"le premier exemple du dépôt"* d'une ligature dans
une proposition. **C'est inexact. C'est le second.** Le premier date de s34 :
`cp-eps-corps-effort.yaml`, ligne 194, `bonneReponse` *"pour que les muscles et le coeur
récupèrent"*. Il a été remis sans ligature par un *"stopgap minimal et réversible"*
(`squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/review.md`, section 10). La question
de fond était routée à `theo-archi` (`retro.md`, ligne 161) puis reprise comme contrainte de
planning en s35 (`avis-faisabilite-theo.md`, section 2.3, *"oeuf s'écrivent en deux lettres tant
que la décision de fond n'est pas prise"*). **Elle n'a jamais été tranchée.** Un enfant de CP lit
"coeur" sans ligature depuis le 2026-09-11.

Le compte de `theo-archi` (*"0 sur 5735"* propositions au-delà de U+00FF) est exact. Il est nul
parce que le stopgap de s34 a retiré la seule occurrence antérieure.

J'ai aussi trouvé des formes sans ligature que le harnais ne lit pas (`enonce`, `indice`) :

| Fichier | Lignes | Champ |
|---|---|---|
| `cp-qlm-cycle-de-vie.yaml` (CP) | 45, 88 | `enonce`, `indice` |
| `cm1-sciences-etapes-developpement.yaml` | 78, 81, 82, 85, 99 | `enonce`, `indice`, `bonneReponse` |

J'ai audité ces deux paquets sans relever ces formes. Je ne rouvre pas ces verdicts : ce n'était
pas ma porte. Je le signale au juge de l'orthographe.

## 4. Ce que je route, via `sofia-sm`

| Code | Point | À qui |
|---|---|---|
| A1 | Corriger le harnais de `PropositionsAccentueesJouablesIT` pour qu'il envoie `œ` au lieu de le refuser. La proposition de `theo-archi` (0x9C en Windows-1252) est un choix technique que je ne tranche pas | `theo-archi`, `yanis-qa` |
| A2 | Dans le même commit que A1, rétablir "œufs" (`cp-anglais-culture.yaml`, ligne 81) et "cœur" (`cp-eps-corps-effort.yaml`, ligne 194) | le développeur de A1, relu par `relecteur-pedagogique` |
| A3 | Statuer sur les graphies sans ligature de la section 3. Elles ne dépendent d'aucun harnais | `relecteur-pedagogique` |

## 5. Ce dont je reste la seule garde

Personne, moi comprise. Aucun garde-fou ne détecte "oe" écrit pour "œ" dans le texte servi, et
cette propriété n'est pas ma porte. Le seul contrôle est la relecture du
`relecteur-pedagogique`. A1 empêche seulement qu'un test oblige à retirer une ligature juste. Il
ne vérifie pas que la ligature est présente.
