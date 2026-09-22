# Routage - s38 - distracteur de S38-COMPLEMENTS-DIZAINE et ADR de mecanisme

- **Auteure** : `sofia-sm`, casquette Scrum Master.
- **Date** : 2026-09-22.
- **Ce que ce fichier est** : un routage en cours de sprint, pas une ceremonie. Le daily n'est pas
  journalise (`squad/README.md`, section 4), mais le tableau des obstacles qu'il produit ne
  disparait pas pour autant - il est ecrit ici, sera repris tel quel dans `review.md` section 6 a
  la cloture, et n'est complet que servi avec les deux routages effectifs qui suivent.
- **Entree** : `squad/sprints/s38-fermeture-mat-ce1-03/avis-faisabilite.md`, sections 8 a 10
  (commit `71c79ba3`), sur la base de `ARBITRAGE-s38-soustractions-complements-dizaine-et-
  moities-ce1.md` (commit `11da48d6`) et de `DEC-0091` (commit `b480cfae`).
- **Ce que ce fichier ne fait pas** : il ne choisit pas de distracteur, il ne redige pas d'`ADR`.
  Les deux obstacles restent ouverts tant que `nadia-prof` et `theo-archi` n'ont pas ecrit leur
  reponse dans un fichier physique du depot.

## Tableau des obstacles

| Obstacle | Categorie | Route a | Statut |
|---|---|---|---|
| `S38-COMPLEMENTS-DIZAINE` infaisable en l'etat : la regle de distracteur de l'arbitrage (2.3) rend `d` systematiquement la plus grande des trois propositions, alors que la bonne reponse `d - a` vaut au plus 9 - `DETTE_DE_SEUIL_MINIMUM` (cliquet a 3 noms, plafond atteint) refuse tout paquet conforme a 0 % | Pedagogie (forme d'un distracteur) | `nadia-prof` | **Ouvert, route ci-dessous** |
| Aucun `ADR` en vigueur ne couvre le choix du mecanisme statique pour `S38-SOUSTRACTIONS` et `S38-MOITIES` - `ADR-0096` n'a tranche que le premier paquet CE1 (les additions) | Architecture | `theo-archi` | **Ouvert, route ci-dessous** |

`DEC-0091` (section 2.2, rappelee par `theo-archi` section 12) : aucune affectation nominative de
developpement tant que ces deux points ne sont pas resolus par un fichier physique. `MAT-CE1-18`
et toute matiere hors mathematiques CE1 restent hors perimetre de ce sprint.

## Routage 1 - vers `nadia-prof` : quel second distracteur pour `S38-COMPLEMENTS-DIZAINE` ?

**Le fait etabli par `theo-archi`** (avis de faisabilite, section 10, demontre pas estime) :
`CDZ-CE1-1` borne la bonne reponse `d - a` a un entier de 1 a 9. La section 2.3 de ton arbitrage
prescrit deux distracteurs sans laisser de choix sur le second : `unite(a)` (1 a 9) et `d`, un
multiple de dix de 10 a 100. Sur tout item conforme, `d` est donc strictement superieur a 9,
c'est-a-dire toujours superieur a la bonne reponse et a `unite(a)` : **`d` est systematiquement la
plus grande des trois propositions, et la bonne reponse ne l'est jamais - sur 100 % des items, pas
seulement en moyenne.**

**Le garde-fou que cela ferait rougir** : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
exige qu'aucun des trois rangs (plus petite, intermediaire, plus grande) ne tombe sous
`SEUIL_MINIMUM = 0,10`. Le rang "la plus grande" vaudrait 0,0 % sur tout paquet que `CDZ-CE1-1` et
2.3 autorisent a exister. Ce garde-fou porte `DETTE_DE_SEUIL_MINIMUM`, un cliquet en egalite a
`PLAFOND_DE_DETTE_SEUIL_MINIMUM = 3` : il porte deja trois noms et ne peut plus en recevoir un
quatrieme. Aucune exemption n'est possible - le paquet serait rouge a la livraison, sans recours
technique.

**Ce que `theo-archi` ne fait pas a ta place** : il ne choisit pas le distracteur de remplacement,
la plausibilite d'une erreur d'enfant restant ta main. Ce qu'il chiffre, pour cadrer la reponse :
sur au moins un item sur dix, les deux distracteurs doivent etre inferieurs a la bonne reponse.
Comme la bonne reponse vaut au plus 9, cela demande de remplacer `d` par une valeur d'un seul
chiffre sur ces items-la.

**La question qui te revient, mot pour mot celle que `theo-archi` route** : *quelle erreur d'enfant,
sur un complement a la dizaine, produit un nombre plus petit que le complement lui-meme ?* Une
reponse qui fixe ce second distracteur (motif inclus, comme pour chacune des clauses de ton
arbitrage du 2026-09-22) leve l'obstacle. Sans elle, `S38-COMPLEMENTS-DIZAINE` n'est pas
engageable et la fermeture de `MAT-CE1-03` a `couvert-valide` dans ce sprint ne l'est pas non plus.

## Routage 2 - vers `theo-archi` : l'`ADR` de mecanisme pour `S38-SOUSTRACTIONS` et `S38-MOITIES`

Ton propre avis de faisabilite (section 8) etablit que le statique reste faisable et justifie pour
les deux lots - meme patron que `S37-1` (`ADR-0096`), condition de reversibilite non atteinte
(tables differentes, rien a mutualiser), cout constate a cinq fichiers et zero ligne de Java de
production par lot. Tu notes toi-meme que ce choix n'est pas deja couvert : `ADR-0096` n'a tranche
le statique que "pour le tout premier paquet CE1", pas pour les paquets suivants.

Tu notes egalement le vrai point de conception, section 9 : le perimetre d'un garde-fou derive doit
se deduire de la forme des items (`ADR-0013`), ce qui est mecanique pour les soustractions et les
complements a la dizaine mais pas pour les moities, dont l'enonce est du francais libre - un motif
sur le mot "moitie" serait un perimetre derive en apparence seulement.

**Ce qui te revient, dans ton role habituel d'architecte** : rediger l'`ADR` qui tranche le
mecanisme (statique) pour `S38-SOUSTRACTIONS` et `S38-MOITIES`, avec sa regle ArchUnit ecrite dans
le meme geste (`CLAUDE.md` : "un `ADR` sans sa regle ArchUnit ecrite dans le meme geste n'est pas
complet"), et la question de conception de ta section 9 tranchee - ta preference y est deja ecrite
(declarer la forme au descripteur du paquet, absence de declaration = rouge), avec ce qui te ferait
changer d'avis. Tant que cet `ADR` n'existe pas, aucune affectation nominative de developpement sur
ces deux lots (`DEC-0091`, section 2.2).

## Ce que ce routage ne change pas

- Aucune affectation nominative n'est faite par ce fichier : `S38-CORR` reste le seul lot sans
  obstacle bloquant a ce point du sprint (avis de faisabilite, section 1 a 6), et son affectation,
  si elle est faite, sera tracee au prochain routage, pas ici.
- Aucun Done, technique ou pedagogique, n'est prononce.
- `MAT-CE1-03` ne change pas de statut dans `squad/programme/MATRICE-couverture.md`.

## Decisions prises

Aucune decision n'est prise dans ce fichier. Il route deux questions vers leurs autorites
respectives ; les reponses restent a ecrire par `nadia-prof` et `theo-archi`.
