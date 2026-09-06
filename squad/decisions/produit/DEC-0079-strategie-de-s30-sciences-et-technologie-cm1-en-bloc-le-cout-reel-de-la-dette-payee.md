# DEC-0079 - Strategie de s30 : sciences et technologie CM1 entre en bloc comme perimetre principal ; le cout reel de la dette payee est chiffre sans l'embellir

- **Type** : quatre arbitrages de l'utilisatrice, enregistres et non rouverts.
- **Date** : 2026-09-06
- **Auteur** : `camille-po`
- **Sprint** : s30 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision arbitre
  un perimetre et une repartition de sprint ; les competences ligne a ligne visees par les deux
  lots de sciences sont nommees dans `docs/backlog/backlog.md`, stories `S30-1` et `S30-2`, et
  chiffrees par `nadia-prof` dans son arbitrage prealable.
- **Statut** : Acceptee.
- **Lien** : `DEC-0074` (`nadia-prof` ecrit les dix referentiels absents, dont sciences et
  technologie CM1), `DEC-0077` (strategie de s29, section 8.7 - trace la correction des deux
  libelles de sciences), `DEC-0078` (`CONS-1`, regle produit et correctif non bloquant),
  `ADR-0081` (codes de ligne, clause 5), `ADR-0084` (`REV-3`, tranchee et livree par `theo-archi`
  avant l'ouverture de s30),
  `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-
  quinze-reserves.md` (`nadia-prof`, arbitrage prealable complet - chiffres et conditions repris
  sans etre recalcules),
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/avis-faisabilite-theo.md` (`theo-archi`, avis de faisabilite technique),
  `squad/programme/MATRICE-couverture.md` (section "Perimetre de s30"),
  `squad/programme/sciences-et-technologie/competences-cm1.md`,
  `docs/backlog/backlog.md` (section "Sprint 30").

---

## 1. Les quatre arbitrages de l'utilisatrice, rendus le 2026-09-06

Objectif nomme par elle : **la meilleure couverture programme possible.** Quatre points arbitres,
enregistres ici et non rouverts :

1. **Perimetre principal : sciences et technologie CM1 en bloc** - 19 lignes `non couvert` sur 20
   (une seule `non couvrable en l'etat`, la demarche d'investigation), declarees par `nadia-prof`
   comme la matiere la plus immediatement servable des dix.
2. **Deux instances de `nadia-prof` en parallele**, comme a s29.
3. **Les quinze reserves ouvertes de s29 entrent dans s30.**
4. **`CONS-1` et `REV-3` entrent tous les deux dans s30.**

Ce que cette decision en tire n'est pas une reouverture de ces quatre points : c'est leur
consequence chiffree, et ce qu'ils coutent en couverture nommee sans adoucissement.

## 2. Etat de depart, recompte avant de m'en servir

Recompte ligne a ligne des dix-huit tableaux de `squad/programme/MATRICE-couverture.md`, colonne
`Statut`, sur l'etat du fichier apres la cloture de s29 : **52 `couvert-valide`, 7 `en cours`, 79
`non couvert`, 71 `non couvrable en l'etat`, 209 lignes au total, 138 au denominateur atteignable.
52 / 138 = 0,3768... = 37,7 %.** Ce compte converge avec celui deja ecrit dans la matrice
("Synthese apres les verdicts du sprint 29") : aucun ecart trouve.

## 3. Deux corrections faites avant tout le reste, sur signalement de `nadia-prof`

`nadia-prof` a corrige, le 2026-09-05, deux erreurs de fond dans
`squad/programme/sciences-et-technologie/competences-cm1.md` (`DEC-0077`, section 8.7) : la ligne
des regimes alimentaires portait *"vegetarien"* a la place d'*"herbivore"* (*vegetarien* est un
choix alimentaire humain, pas le regime d'un animal qui mange des vegetaux) ; la ligne des grandes
fonctions du corps rangeait *"nutrition"* parmi ses propres composantes, alors que la nutrition est
la famille qui contient la digestion, la respiration et la circulation, pas une quatrieme fonction a
leur cote. **`squad/programme/MATRICE-couverture.md` portait encore les deux anciens libelles**,
aux lignes alors numerotees 1744 et 1746 - `nadia-prof` l'a rejoue le 2026-09-06 et me l'a route
(son arbitrage prealable de s30, section 1.6), en refusant de toucher elle-meme la matrice.

**Pourquoi cela ne pouvait pas attendre l'audit.** `FER-1` fait de chaque parenthese une liste
contractuelle : un developpeur composant son lot depuis la matrice - le document de pilotage -
aurait ecrit un item dont la bonne reponse est *"vegetarien"* pour designer le regime d'un animal,
et un item demandant *"l'organe principal de la nutrition"*, question a plusieurs reponses
defendables que `HIS-2` refuse. Le lot aurait ete faux **en obeissant au document de pilotage**.

**Corrige avant l'ouverture, dans le meme geste que la presente decision** : les deux libelles de
la matrice reprennent desormais, au mot pres, le referentiel corrige. Trace dans
`squad/programme/MATRICE-couverture.md`, section "Sciences et technologie - CM1".

## 4. Les codes `SCI-CM1-01` a `SCI-CM1-20`, alloues avant tout paquet

`ADR-0081` exclut explicitement les referentiels qu'aucun paquet ne touche encore de son allocation
de codes, sciences et technologie CM1 en faisant partie. Ce jour est celui ou un paquet de sciences
va exister : `nadia-prof` ecrit qu'elle **ne prononcera aucun Done pedagogique sur une ligne de
sciences dont le code n'est pas alloue et declare par le paquet qui la sert** (section 2.7 de son
arbitrage), et `theo-archi` confirme que le garde-fou `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`
refuse tout paquet servi tant que son referentiel ne porte aucun code (`squad/sprints/
s30-sciences-cm1-et-le-prix-des-reserves/avis-faisabilite-theo.md`, section 2.2).

**Alloue ici, dans le meme geste que la presente decision**, sur le precedent exact de `lea-dev`
pour l'EMC CP (`ADR-0081`, commit `5526932`) : vingt codes `SCI-CM1-01` a `SCI-CM1-20`, ecrits en
tete de chaque puce de `squad/programme/sciences-et-technologie/competences-cm1.md`, dans l'ordre
de lecture des vingt lignes - memes rangs qu'au tableau de faisabilite de `nadia-prof`. Aucun
intitule n'est modifie par cette allocation.

## 5. Le perimetre retenu, ligne par ligne

### 5.1 Sciences et technologie CM1 - deux lots, quatre lignes

`nadia-prof` classe quatre lignes comme les plus sures du lot - aucune condition de biais, aucun
fait qui demande une source exterieure, distracteurs naturels (section 2.8 de son arbitrage) : les
lignes 2, 5, 12 et 17. Elle nomme elle-meme une composition tenable si elle est demandee : lot 1 =
lignes 5 et 17, lot 2 = lignes 2 et 12. **Je retiens cette composition.**

| Lot | Lignes | Codes | Elements de `FER-1` |
|---|---|---|---|
| 1 | Signal et information dans les objets du quotidien ; milieu proche et chaine alimentaire | `SCI-CM1-05`, `SCI-CM1-17` | 2 et 2 |
| 2 | Melange homogene / heterogene ; objet technique (fonction, composants, fonctionnement) | `SCI-CM1-02`, `SCI-CM1-12` | 3 et 3 |

Motif du choix, contre les seize autres lignes : les quatre retenues portent le moins de conditions
particulieres (`SCI-1` s'y applique comme partout, mais aucune n'est signalee sous `SCI-2`, `BIA-2`
ou `BIA-3`), et deux d'entre elles (2 et 12) ne recouvrent aucun autre poste de la matrice, quand la
ligne 3 (mouvement) recouperait mathematiques CM1 / Grandeurs et mesures (`ADR-0081` clause 5) et la
ligne 17 (chaine alimentaire) mord sur `CYC-1` contre `cp-qlm-vivant-non-vivant.yaml` - lecture due,
cout nul, deja nommee dans la story qui la porte.

**Non retenue : la ligne "extraire une information d'un document scientifique".** `theo-archi`
mesure que `texteSupport` est rendu sans regle `white-space: pre-line` et collabe un tableau en une
seule ligne (`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/avis-faisabilite-theo.md`, section 2.1) : servir cette ligne, meme
sur sa seule moitie textuelle, est un arbitrage distinct que je ne tranche pas dans cette decision.

**Non retenues, a ne pas prendre au premier lot** (`nadia-prof`, section 2.8) : les lignes 9, 15,
16 et 18, qui portent chacune une condition (`SCI-1` a son point le plus exigeant, `BIA-2`, `SCI-2`)
qui n'a encore rencontre aucun contenu reel.

### 5.2 Les treize reprises de reserves retenues, en six blocs

| Bloc | Reserves | Paquet | Regime |
|---|---|---|---|
| A | `CYV-R1`, `VIV-R2` | `bandit-manchot-cycle-de-vie-cp`, `bandit-manchot-vivant-non-vivant-cp` | mot pour mot, 0,25 |
| B | `OBJ-R1`, `OBJ-R2` | `bandit-manchot-objet-technique-cp` | rejeu borne, 0,5 |
| C | `HG-R6`, `HG-R7` | `bandit-manchot-histoire-geographie-cm1` | rejeu borne, 0,5 |
| D | `POS-R2` | `bandit-manchot-division-posee-cm1` | rejeu borne, 0,5 |
| E | `FOP-R1`, `FOP-R2`, `FOP-R3` | `bandit-manchot-fait-opinion-cm1` | rejeu borne, 0,5 |
| F | `DRO-R1`, `DRO-R2`, `DRO-R3` | `bandit-manchot-droit-devoir-cm1` | rejeu borne, 0,5 |

**Total : 2,75.** Stories `S30-3` a `S30-8` de `docs/backlog/backlog.md`.

### 5.3 `PER-R1` et `REG-R1`, differees a s31 - motif de `nadia-prof`, repris sans le reformuler

L'arbitrage de l'utilisatrice fait entrer les quinze reserves de s29 dans s30. `nadia-prof` en
differe deux, par ecrit, sur une question qui est de son ressort et non du mien - *"si deux blocs
doivent sortir, lesquels font le moins de mal a un enfant ?"* (section 5.3 de son arbitrage) :

- **`PER-R1`** (`bandit-manchot-permis-interdit-cp`, ligne `EMC-CP-02`) : *"la question et la bonne
  reponse ne s'enchainent pas toujours en francais correct (elle fait un service). Aucun
  apprentissage faux : l'enfant apprend la bonne qualification, il lit une phrase maladroite. C'est
  le seul de la liste ou rien de faux n'est enseigne et ou aucun item ne se gagne sans la
  competence."*
- **`REG-R1`** (`bandit-manchot-regles-de-vie-cp`, ligne `EMC-CP-06`) : *"trois distracteurs hors
  registre reduisent trois items a un choix entre deux. Aucun apprentissage faux : l'item est plus
  facile qu'il ne devrait, il n'est pas trompeur."*

Ce que je fais de ce differe : je l'acte, je ne le recalcule pas. Les lignes `EMC-CP-02` et
`EMC-CP-06` restent `couvert-valide`, inchangees ; les deux reserves restent ouvertes, portees a
s31. Aucune des deux n'est un premier report - elles n'ont jamais ete differees avant ce jour.

### 5.4 `CONS-1`, `REV-3`, la passe transverse

- **`CONS-1`** : dix consignes generiques reecrites, chacune rejouee contre le paquet qu'elle
  introduit. **0,5.** Story `S30-9`.
- **`REV-3`** : tranchee et livree par `theo-archi` avant l'ouverture du sprint (`ADR-0084`, commit
  `c2c82ac`) - fait inhabituel, note ici pour qu'il ne se lise pas comme une histoire qui restait a
  ecrire techniquement. Ce qui reste du au sprint est le rejeu de `nadia-prof` sur la forme livree :
  **0,25 attendu**, sujet a doubler si elle constate qu'un ecran neuf est ne du correctif (sa propre
  clause du 2026-09-06). Story `S30-10`.
- **Passe transverse** (`PAR-4`) : proprietes de portee depot, objets nommes par `nadia-prof`
  (section 3.3 de son arbitrage) - la famille de `REV-1` ailleurs dans le depot, l'homogeneite de
  registre au-dela de `CONS-1`, les collisions d'identifiants mintes par s30. **0,5.** Story
  `S30-11`.

**Total : 1,25.**

## 6. La borne, sa repartition, et ce qu'elle coute en couverture

### 6.1 La borne et le budget opposable

**Borne du sprint : 8,0.** La decote de 1,0 reste, contre elle-meme : `nadia-prof` avait ecrit que
sa levee exigeait *"un second sprint parallele sans defaut de couture **et** le registre de `PAR-5`
ecrit"*. Aucune des deux n'est acquise - le premier sprint parallele (s29) a eu un defaut de couture
(un ecart de comptage trouve par `camille-po` et par personne d'autre), et le registre de `PAR-5`
n'existe toujours pas.

**`PAR-9`, clause neuve de `nadia-prof` datee du 2026-09-06** : le budget opposable est **4,0 par
instance**, marge de rejeu comprise, la somme des deux valant exactement la borne. Le plafond
individuel de `PAR-8` (5,0) reste un plafond dur qu'aucune instance ne franchit, mais il cesse
d'etre le chiffre de planification : s29 a coute 9,50 sur 8,0 en respectant les deux plafonds
individuels, parce que rien ne tenait leur somme.

### 6.2 La composition retenue

| | Une instance | L'autre instance |
|---|---|---|
| Lots de contenu | - | sciences et technologie CM1, lot 1 (1,5) et lot 2 (1,5) |
| Reprises | blocs A, B, C, E, F (2,25) | bloc D (0,5) |
| Portee depot | passe transverse (0,5), `CONS-1` (0,5), `REV-3` (0,25) | - |
| Planifie | 3,5 | 3,5 |
| Marge de rejeu | 0,5 | 0,5 |
| **Total** | **4,0 / 4,0** | **4,0 / 4,0** |

**Total planifie du sprint : 7,00. Marge : 1,00. Borne : 8,00.** L'affectation nominative des deux
instances a un agent est le travail de `sofia-sm`, pas de la presente decision : ce tableau
reprend la repartition par objet de travail que `PAR-1`, `PAR-2` et `PAR-7` imposent (un lot de
contenu n'est jamais partage ; une matiere et un niveau ne le sont jamais entre deux instances ;
l'instance qui porte l'accumulation prend les dettes et la portee depot, l'autre prend le terrain
ou personne n'a d'accumulation - sciences et technologie CM1, jamais servie).

### 6.3 Ce que ce choix coute en couverture, sans l'embellir

**Le chiffre honnete** : sur 8,0 de borne, **5,00 - 62,5 % - sont engages sur du travail qui ne
ferme aucune ligne neuve.** Les treize reprises (2,75), `CONS-1` (0,5), `REV-3` (0,25) et la passe
transverse (0,5) portent tous sur des paquets ou des ecrans deja livres ; aucun ne fait bouger un
statut de `squad/programme/MATRICE-couverture.md`.

**Ce que les 3,00 restants achetent** : deux lots de sciences a 1,5, chacun fermant deux lignes au
plus (`FER-2` : dix items au moins par ligne ; `ADR-0081` clause 5 : un item ne sert qu'une ligne ;
`DEC-0064` : onze items au moins dans un paquet de fermeture a dix manches - un lot de vingt-deux
items ferme donc deux lignes au plus). **Plafond arithmetique : 4 lignes. 52 + 4 = 56 / 138 =
40,6 %.**

**Ce plafond suppose zero marge de rejeu** sur les lots de sciences eux-memes ; avec la marge que
`nadia-prof` tient pour necessaire (1,0 nommee, non allouee), **l'attente raisonnable qu'elle ecrit
est 3 a 4 lignes, soit 55 a 56 / 138, 39,9 % a 40,6 %.**

**Ce que ce choix n'est pas : une contestation de l'arbitrage de l'utilisatrice.** Les quatre points
de la section 1 sont rendus, pas discutes. Ce que cette section porte est le fait que solder la
dette de s29 (les reserves, `CONS-1`) et payer la parallelisation (la passe transverse) au meme
sprint que l'ouverture d'une matiere neuve absorbe les deux tiers de la borne d'audit sans faire
bouger la matrice - pour que la prochaine decision de perimetre se prenne en connaissance de ce
prix, et non en le decouvrant a la cloture.

## 7. Ce que je route, sans le trancher

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| L'intitule de la question rappelee en fin de partie (*"La question :"* ou rien) | `nadia-prof` (`M12`) | Arbitrage pedagogique sur un texte lu par l'enfant, pas une decision produit que je prends ici |
| Le poids visuel de la question rappelee | `ines-design` | Propose sans trancher |
| Le cliquet de comptage possible sur `CONS-1` | `sofia-sm`, `theo-archi` | A ecrire, si voulu, **apres** le correctif, avec le compte mesure a ce moment - jamais avec 10 (`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/avis-faisabilite-theo.md`, section 2.3) |
| Le garde-fou de coherence entre un bilan de matiere et le compte reel de ses lignes (`DEC-0077`, section 7) | `camille-po` (normalisation prealable des quatorze bilans), puis `theo-archi` (garde-fou, ~170 lignes) | `theo-archi` le juge encodable mais pas avant que les bilans partagent une forme fixe, sans quoi le garde-fou devinerait une phrase (meme source, section 3). Non engage a s30, note pour ne pas rester une quatrieme fois sans reponse |
| Le rang de declaration comme troisieme forme de contrefacon du garde-fou 4 du palier 2 (`ADR-0080`) | `theo-archi` | Gagne a etre tranche avant `T6`, hors perimetre du contenu de s30 - le palier 2 n'est pas ouvert |

## 8. Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique ou d'architecture : la forme de `REV-3` est celle
  d'`ADR-0084`, deja ecrite par `theo-archi`.
- Elle ne prononce aucun Done pedagogique et ne fait passer aucune ligne a `couvert-valide` : ce
  verdict appartient a `nadia-prof` seule, sur les increments qu'elle joue.
- Elle n'affecte personne nominativement aux deux instances : c'est `sofia-sm`, dans `planning.md`.
- Elle ne rouvre aucun des quatre arbitrages de l'utilisatrice de la section 1.
- Elle ne recalcule aucun chiffre de `nadia-prof` : la borne, le tarif, la repartition et le
  plafond sont repris de son arbitrage prealable, pas reevalues.

## 9. Impact

- `squad/programme/MATRICE-couverture.md` : deux libelles corriges (section "Sciences et
  technologie - CM1"), section "Perimetre de s30" ajoutee. Aucun statut ne bouge.
- `squad/programme/sciences-et-technologie/competences-cm1.md` : vingt codes `SCI-CM1-01` a
  `SCI-CM1-20` alloues.
- `docs/backlog/backlog.md` : section "Sprint 30" ajoutee, onze stories (`S30-1` a `S30-11`).
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0079` ajoutee.
