# RAPPORT de cloture - s29, parallelisation et dettes payees

## L'increment livre

**recreo ferme neuf lignes de programme - deux paquets de questionner le monde CP, trois lignes
d'histoire-geographie CM1, quatre paquets d'enseignement moral et civique aux deux cycles - paie
quatre dettes ouvertes depuis un a trois sprints (`POS-R1`, six reserves textuelles, la dette des
paquets a voix due depuis s26, l'arbitrage prealable du palier 2), et corrige un defaut structurel
qui traversait tous les sprints de contenu du depot depuis que la mecanique de jeu existe.** La
couverture programme passe de 43/138 (31,2 %) a 52/138 (37,7 %). C'est le premier sprint ou deux
instances de `nadia-prof` ont audite des lots disjoints en parallele.

## Toutes les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0075` | Corpus de reproductions d'oeuvres et droits d'usage | Produit | Amendee : statut passe a Acceptee (option B) par `DEC-0077` |
| `DEC-0077` | Strategie de s29 : couverture maximale, marge de rejeu posee, corpus du domaine public tranche | Produit | Acceptee, amendee trois fois (perimetre et parallelisation ; cout reel et recompte de la matrice ; cout corrige a 9,50) |
| `DEC-0078` | `CONS-1` - la consigne generique cesse d'etre reprise par une entree neuve de catalogue | Produit | Acceptee |
| `ADR-0073` | Un item declare l'illustration qu'il montre par un identifiant, la preuve change de source | Architecture | Amendee (troisieme population d'images, `static/oeuvres/`) |
| `ADR-0082` | Une reproduction d'oeuvre est une troisieme population d'images, sa preuve est double | Architecture | Accepte |
| `ADR-0083` | Aucune valeur de contenu n'est enchassee dans une phrase de la mecanique | Architecture | Accepte |

## Etat des deux Done

**Technique : prononce.** `mvn -B clean verify` vert sur le reacteur complet, `round-two` a
`ea1850e`. **1286 tests, 0 echec, 0 erreur, 174 rapports XML** (methode de l'amendement s11, mesure
par moi sur l'arbre fusionne final, sans `-pl`). Confirme independamment par `yanis-qa` (1262 sur
`8ef4fb2`, avant les deux derniers lots de code), par `marc-dev` et `lea-dev` (1286 chacun, chiffre
identique au mien). Le delta entre les deux mesures est explique poste par poste (+2 pour le
correctif de `REV-2`, +22 pour les quatre fichiers de test d'EMC ajoutes en reprise) et non
seulement constate. Aucune `ArchRule` ajoutee ce sprint ; deux garde-fous de balayage neufs
(`ADR-0082`, `ADR-0083`), verifies verts par ma propre execution.

**Pedagogique : prononce.** Deux instances disjointes de `nadia-prof` (branche `round-two`, Instance
A a `ba3f603`, Instance B a `5c35f6c` puis `56f8967`, reverification finale sur `8ef4fb2`). Neuf
lignes ferment : `QLM-CP-02`, `QLM-CP-07`, `HG-CM1-06`, `HG-CM1-07`, `HG-CM1-14`, `EMC-CP-06`,
`EMC-CP-02`, `EMC-CM1-11`, `EMC-CM1-07`. Quatre paquets sur cinq evalues au premier passage ont ete
refuses, tous corriges et valides au reaudit dans le sprint. `REV-1` corrige et rejoue (`VALIDE SOUS
RESERVE`) ; `REV-2` refusee puis levee sur un texte prescrit mot pour mot ; `REV-3` routee, cause
structurelle, non tranchee.

**Prix reel du sprint : 9,50 sur une borne de 8,0** (Instance A 4,25/5,0, Instance B 5,25/5,0),
recompte independamment par `camille-po` sur les deux releves de verdicts. La marge de rejeu,
nommee a 1,0 a l'ouverture et jamais utilisee dans ce depot auparavant, tombe a **0,75** apres un
seul tirage de 0,25 (Instance B).

**Ce que la parallelisation a reellement achete, dit sans l'embellir ni le minimiser** : `nadia-
prof` avait ecrit d'avance que doubler sa borne n'acheterait aucune ligne de plus - une composition
en serie a 5,0 aurait deja plafonne aux memes neuf lignes. Verifie : le plafond de neuf est atteint
exactement, ni plus ni moins. Ce que la parallelisation a achete a la place : `POS-R1` payee apres
trois sprints en reserve, six reserves textuelles levees, la dette des paquets a voix payee apres
quatre sprints d'attente, 1,0 de marge de rejeu qui n'avait jamais existe dans ce depot (servie a
hauteur de 0,25), et une passe transverse conduite pour la premiere fois comme tache payee plutot
que comme sous-produit gratuit d'une accumulation en serie - qui a trouve deux defauts (`REV-1`,
`CONS-1`) qu'aucune autre methode de ce sprint n'aurait trouves. Le fait de methode annonce d'avance
par `nadia-prof` - la parallelisation n'aurait pas cause ces defauts mais les aurait laisses dormir
un sprint de plus - est verifie et non seulement suppose : les deux sont anterieurs a s29.

**Conditions restant sans garde executable sur les neuf lignes fermees** : aucun test du depot ne
sait qu'un enonce scolaire est vrai, `FER-1` et `FER-3` restent des jugements non automatisables, la
plausibilite d'un distracteur n'a aucun garde-fou, et `VOI-3`/`VOI-5`/`VOI-8` ne sont gardees par
personne, pas meme par `nadia-prof`.

## Obstacles resolus

- Le perimetre annonce a l'ouverture (cinq lots) valait en realite 6,0 a 7,0 sur la borne de
  `nadia-prof` - revise a quatre lots (`DEC-0077`, section 8.1).
- La question, ouverte depuis la retro de s28, de la reponse par defaut a un refus tardif dans un
  sprint deja a sa borne - fermee par une reponse structurelle : `nadia-prof` audite chaque lot le
  jour de sa livraison plutot qu'en bloc a la cloture.
- L'arbitrage de la parallelisation (borne, plafond individuel, variante retenue) - `DEC-0077`,
  section 8.2, chiffre par `nadia-prof` en clauses opposables (`PAR-0` a `PAR-8`).
- Quatre paquets d'EMC refuses au premier passage (classement binaire hors sujet, charge de lecture
  disproportionnee, mensonge confondu avec un troisieme axe de reponse, correctif de division
  insuffisant) - tous corriges et valides au reaudit dans le sprint.
- `REV-1`, defaut structurel traversant tous les sprints de contenu du depot, trouve par la passe
  transverse - corrige par `ADR-0083` le meme jour.
- `REV-2`, trouve en reverifiant le correctif de `REV-1` - leve sur un texte prescrit mot pour mot.
- `CONS-1`, la consigne generique passee de 5 a 10 jeux en un sprint - tranchee en regle produit
  (`DEC-0078`).
- L'ecart de comptage sur les lignes fermees (huit annoncees, neuf reelles, `EMC-CP-06` omise du
  total de l'Instance A) - trouve et corrige par `camille-po` en recomptant ligne a ligne.
- Le defaut trouve par `yanis-qa` (quatre paquets d'EMC sans fichier de test de matiere, le meme
  defaut qu'a s28 sur un paquet) et deux declarations inexactes dans des commentaires YAML commites
  - tous corriges dans le meme commit (`fa968ee`).
- `docs/backlog/backlog.md` presentait `REV-1` comme un travail restant pour s30 alors que le
  correctif etait deja livre - corrige dans le commit de cloture, `REV-3` ecrite comme story neuve.
- Le cout du sprint, perime a 9,25 dans trois documents apres la reverification finale de
  `nadia-prof`, signale par le relecteur - corrige a 9,50 dans le commit de cloture.
- `DEC-0075` (corpus de reproductions d'oeuvres, sourcing non instruit), hors-sprint - `ADR-0082`
  instruit la source (renonciation `CC0-1.0` enregistree), le pivot de 1900, et le registre dedie.

## Obstacles encore ouverts, a porter a s30

- **`REV-3`** : l'ecran de fin de partie nomme une reponse sans porter sa question, cause
  structurelle routee a `theo-archi`, non tranchee.
- **Le registre des 147 identifiants de conditions (`PAR-5`)** : aucune forme construite, verifie
  seulement qu'aucune collision n'a ete creee par s29 - route a `theo-archi`.
- **Un garde-fou mecanique de coherence entre un bilan de matiere et le compte reel de ses lignes** :
  pose comme question depuis `DEC-0077` section 7, non tranche.
- **Un garde-fou possible pour `POS-2`** sur la division posee, calculable mais non encode - signale,
  non requis pour ce sprint.
- **Quinze reserves non bloquantes** issues des verdicts de s29, aucune perdue : `CYV-R1`, `OBJ-R1`,
  `OBJ-R2`, `HG-R6`, `HG-R7`, `REG-R1`, `PER-R1`, `FOP-R1`, `FOP-R2`, `FOP-R3`, `DRO-R1`, `DRO-R2`,
  `DRO-R3`, `POS-R2`, `VIV-R2`.
- **La generalisation d'une discipline de worktree dedie a tous les agents mesurant en cours de
  sprint** : trois incidents d'orchestration (mesures concurrentes sur un worktree partage, une
  consigne de mesure limitee au module ayant laisse passer un rouge visible seulement sur le
  reacteur complet, du travail d'un agent embarque dans le commit d'un autre) sont de mon fait ;
  `yanis-qa` avait deja adopte le worktree dedie de lui-meme depuis s28, la methode n'a pas ete
  generalisee - a traiter au planning de s30 (voir `retro.md`).

## Tableau de consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s29`
(`squad/sprints/s29-parallelisation-et-dettes-payees/RAPPORT-tokens.md`) :

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 265 | 530 | 130 798 | 913 946 | 38 733 600 |
| `sofia-sm` | claude-sonnet-5 | 75 | 150 | 37 747 | 570 783 | 7 264 825 |
| `theo-archi` | claude-opus-5 | 181 | 362 | 122 848 | 515 452 | 27 240 633 |
| `marc-dev` | claude-sonnet-5 | 658 | 1 316 | 347 954 | 2 209 004 | 94 644 326 |
| `lea-dev` | claude-sonnet-5 | 985 | 1 970 | 389 922 | 3 215 924 | 161 953 735 |
| `yanis-qa` | claude-sonnet-5 | 179 | 358 | 58 700 | 703 132 | 24 808 389 |
| `relecteur-pedagogique` | claude-sonnet-5 | 276 | 552 | 98 331 | 944 361 | 62 184 738 |
| `nadia-prof` | claude-opus-5 | 728 | 1 456 | 456 173 | 2 860 912 | 90 660 550 |
| `ines-design` | claude-sonnet-5 | 82 | 164 | 39 171 | 383 126 | 9 247 985 |
| orchestrateur (fil principal) | claude-opus-5 | 161 | 322 | 295 137 | 2 459 725 | 84 358 691 |
| **Total** | | **3590** | **7 180** | **1 976 781** | **14 776 365** | **601 097 472** |

**Fenetre de mesure** : 2026-09-05 19:35 a 2026-09-06 13:22 UTC (`RAPPORT-tokens.md`, genere avant
l'ecriture du present rapport ; les derniers commits de cloture et la redaction de `review.md` et
`retro.md` ne sont donc pas dans cette fenetre).

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur.
Le cout d'audit de `9,50` cite plus haut est un chiffre d'arbitrage produit (borne de `nadia-prof`,
lots de matiere), sans rapport avec les compteurs de tokens de ce tableau : rien ne relie l'un a
l'autre, et aucun des deux ne se deduit de l'autre.
