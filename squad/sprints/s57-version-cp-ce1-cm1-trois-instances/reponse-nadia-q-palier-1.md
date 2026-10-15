# s57, palier 1 - `nadia-prof` : `Q-R5`, `Q-T7`, `Q-F1`, et les points `N1` a `N4`, `D1` a `D5` du `relecteur-pedagogique`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-15, 12:13 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-q`, worktree dedie, partie de `round-two` a `05598be4`.
- **Lu** : `docs/backlog/backlog.md` (annotation de `camille-po` du 2026-10-15, 11:31 : `S57-RCT-CE1-R1`, amendement `Q-R4`,
  `S57-MESSAGE-PARTIE-PERDUE`, `S57-VSJ-GARDE-VERBE-CITE`) ; `tache-marc-dev-f1.md` ; `cloture-palier-1.md` ;
  `releve-relecteur-palier-1.md` ; `reponse-nadia-forme2-palier-1.md` ; `ARBITRAGE-PREALABLE-s48-francais-ce1.md` 5.4 et
  `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` (2.1, amendement de la propriete 8) ; `ce1-verbe-sujet.yaml`,
  `cm1-sciences-extraire-information.yaml`, `cp-additions-jusqu-a-10.yaml` ; `PaquetCe1VerbeSujetTest.java` (lignes 185 a 370) ;
  `PartieController.java` (lignes 61 et 146 a 162) ; les fiches de `phrases-types`, `verbe-sujet` et `sciences-extraire-information`.
- **Rien joue** : aucune de ces questions ne demande un jar, et je n'en ai lance aucun. Ce que je ne peux pas dire sans
  mesure est dit a chaque point.

## Resume

| Code | Point | Decision | Section |
|---|---|---|---|
| R1 | `Q-R5` | **Le jar de base est celui du commit d'ou part la branche de la forme**, pas le dernier commit de `round-two` sans la forme. Si la base bouge avant le rejeu, la cote se remesure | 1 |
| R2 | `Q-T7` | **Aucun rejeu, prix 0.** Je regarde le message une fois, au premier rejeu qui suit la livraison, sans prix | 2 |
| R3 | `Q-F1` | **Oui, la lecture de `sofia-sm` est la bonne**, avec deux precisions : la source est s48, 5.4 ; le rang egal couvre aussi un groupe qui commence par le verbe | 3 |
| R4 | `N1` | Ni refus ni reserve. **Recommandation non bloquante** : une question sans reprise par *il* | 4.1 |
| R5 | `N2` | Ni refus ni reserve. **Recommandation non bloquante** pour ce paquet, **point d'audit** pour les suivants : un mot cite dans un indice est entre chevrons | 4.2 |
| R6 | `N3` | **Ni refus ni reserve, rien a changer** : le determinant de *parc* et d'*élèves* n'est pas dans la phrase | 4.3 |
| R7 | `N4` | **Correction faite** : une seule majuscule manque, que deux regles demandent | 4.4 |
| R8 | `D1` | **Le relecteur a raison**, mon constat etait faux sur ma propre base. Annote | 5.1 |
| R9 | `D2` | **Le relecteur a raison** : trois paquets de Y et un de X, quatre. Annote | 5.2 |
| R10 | `D3` | **Le relecteur a raison**, et je ne peux pas corriger seule : les mots de l'utilisatrice ne sont dans aucun fichier. Demandes ; aucun verdict n'en depend | 5.3 |
| R11 | `D4` | **Le relecteur a raison** : reponse du 2026-10-14. Annote | 5.4 |
| R12 | `D5` | Ce que j'attends de `marc-dev` | 5.5 |

---

## 1. `Q-R5` : le jar de base de la forme

**Lecture** : le jar de base est celui du **commit d'ou part la branche de la forme commune**, le parent de son premier
commit. Le jar compare est celui du **commit livre**, que je rejoue. Les deux arbres ne different alors que par la forme, et
c'est ce que la clause doit isoler : un item qui passe sous le pli entre les deux l'a ete par la forme, et par rien d'autre.

**Pourquoi pas *"le dernier commit de `round-two` qui ne porte pas la forme"*.** Les deux lectures coincident tant que
`round-two` ne bouge pas entre le depart de la branche et la mesure. Ce palier fusionne pendant que d'autres travaillent : si un
lot ou la feuille du poste 8 (`L4`) entre dans `round-two` apres le depart de la branche, le dernier commit sans la forme porte
un changement que le commit livre n'a pas. La cote de base mesurerait alors un autre ecran que celui auquel je compare.

**Ce que le fichier de la cote ecrit, en plus de ce que le critere demande deja** (date, commit, largeur, hauteur, methode) :

1. `git merge-base --is-ancestor <base> <commit livre>` sort 0 ;
2. `git diff --stat <base> <commit livre>` ne nomme que les fichiers de la forme, sans aucun paquet ni `catalogue.yaml`.

**Si la branche change de base avant mon rejeu** (rebase, ou fusion de `round-two` dans la branche), **la cote se remesure
sur la nouvelle base**. Elle n'est jamais reprise de l'ancienne : c'est la meme regle que *"jamais recopiee d'une fiche
ancienne"*.

**Ce que je ne tranche pas ici** : `Q-R6` (*"tous ses items"* pour `bandit-manchot-additions-cp`, jeu tire) est posee a
`theo-archi`. Je dirai si sa reponse me suffit quand elle sera ecrite.

## 2. `Q-T7` : `S57-MESSAGE-PARTIE-PERDUE`, aucun rejeu

**Aucun rejeu, prix 0.** C'est le regime de `S57-FORME-C-VERDICT`, dont cette story etend le garde-fou : aucun Done
pedagogique propre, et un texte qui ne change pas d'un mot ni d'un signe.

**Motif.** Ce qu'un rejeu me ferait verifier est deja tenu par les criteres :

- le texte lu par l'enfant ne change pas : critere 1, un test ;
- il n'y a qu'un endroit ou la ligne peut se couper, entre les deux phrases : critere 3, un test, qui mord (critere 4).

Ce qu'aucun test ne voit, c'est le debordement d'une phrase insecable hors de sa boite. *"Choisis un nouveau jeu !"* fait 24
signes, sur un ecran d'accueil de 744 px ou plus (`DEC-0040`) : je n'attends pas de debordement, **mais je ne l'ai pas mesure**.

**Ce que je fais a la place, sans prix** : au premier rejeu qui suit la livraison, quel que soit le jeu, je provoque le message
une fois (partie en cours, jar relance, retour a l'accueil : le marqueur reste, la partie n'est plus en session,
`PartieController`, lignes 158 a 160) et je le regarde a 744 x 551. Une coupure dans une phrase ou un debordement serait une
reserve neuve, datee, non bloquante. **Prix dans le registre des miens** : 1,0 un lot neuf, 0,5 un rejeu dont les textes ou la
forme changent, 0,25 un rejeu a paquet inchange, ou dont des textes changent sans rien d'autre
a juger (`S54-OBS-SCI-CONSIGNE`) ; ici, aucun ecran de jeu n'est juge,
donc 0.

**Correction de ma section 7** (`reponse-nadia-forme2-palier-1.md`) : j'y ai ecrit le message *"lu par un enfant de CP, seul
sur l'ecran d'accueil"*. La phrase peut se lire comme un enfant seul, ce que le produit exclut au CP (`DEC-0046`, `DEC-0068`) ; et
le message n'est pas le seul texte de l'accueil. Lecture juste : *"lu par un enfant de CP sur l'ecran d'accueil"*. Mon avis sur
la story ne change pas.

## 3. `Q-F1` : un distracteur egal au verbe cite est refuse par `VSJ-CE1-3`

**Oui. La lecture de `sofia-sm` est la bonne** (`tache-marc-dev-f1.md`, section 1, troisieme point). Ce n'est pas une regle
neuve et je ne releve aucune barre.

**Le texte.** `VSJ-CE1-3` est ecrit dans `ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 5.4, lignes 528 et 529 :
*"**(a)** le groupe placé après le verbe ; **(b)** un autre nom de la phrase ; **(c)** pour les items de verbe, un nom ou un
adjectif de la phrase."* La ligne 82 de l'arbitrage de s56, que `sofia-sm` et `camille-po` citent, est ma table de lecture de
cette condition, pas sa source. Le paquet la lit comme une liste fermee : *"chaque item declare ses familles"* (en-tete de
`ce1-verbe-sujet.yaml`, ligne 9), et chaque item de sujet declare `a, b`. Le verbe cite n'est ni le groupe place apres lui, ni
un nom : **il n'appartient a aucune famille permise**. `VSJ-CE1-1` ne le refuse pas, puisque le verbe est un mot copie de la
phrase ; c'est bien `VSJ-CE1-3` qui le refuse. Le message nomme donc `VSJ-CE1-3`, comme le demande le critere 1.

**Le motif pedagogique.** L'enonce demande *"le sujet du verbe « rangent »"* : proposer *rangent* revient a demander si le verbe
est son propre sujet. Aucun enfant ne s'y trompe en raisonnant sur le sujet ; la proposition s'ecarte d'office, et l'item se
joue a deux propositions au lieu de trois.

**Deux precisions.**

1. **Le rang egal couvre plus que le verbe seul**, et c'est juste. `rangDansLaPhrase` rend le rang du premier mot d'une
   proposition (`PaquetCe1VerbeSujetTest.java`, lignes 195 a 212) : un groupe qui commence par le verbe (*"rangent les
   jouets"*) a lui aussi le rang du verbe. Il n'est pas *"place après le verbe"*, et ce n'est pas un nom : `VSJ-CE1-3` le
   refuse aussi. Le critere de `sofia-sm` (*"au meme rang que le verbe cite"*) est la bonne forme ; le titre de la story
   (*"egal au verbe cite"*) en est un cas, pas la limite.
2. **Le cote *"avant"* n'a pas ce trou.** Un groupe qui finit par le verbe (*"elles rangent"*) a un rang inferieur ; il est
   refuse par la verification de la virgule (lignes 339 a 355), puisqu'il ne termine pas un complement d'ouverture suivi d'une
   virgule. Je ne demande rien de plus sur ce cote.

**Paquet** : je n'ai pas recompte les 24 items. Le 0 de `camille-po` (critere 5) et celui de `yanis-qa` me suffisent pour dire
qu'aucun item n'est a toucher. Si `marc-dev` en trouve un, il s'arrete, comme la tache le dit.

## 4. Contenu destine a l'enfant : `N1` a `N4`

### 4.1 `N1` : *"que ce transport de pollen permet-il à de nombreuses plantes ?"*

**Ni refus ni reserve. Recommandation non bloquante.**

**Ce que le relecteur voit est juste** : la phrase commence par *"que ce transport de pollen permet"*, qu'un lecteur de CM1
peut d'abord lire comme une affirmation, avant de rencontrer *-il*. **Pourquoi ce n'est pas une reserve** : la question reprend
mot pour mot la phrase du texte (*"Ce transport de pollen permet à de nombreuses plantes de..."*), l'indice designe cette phrase
et le mot *permet* : l'enfant qui hesite sur la question retrouve la phrase qui y repond. Le referentiel de CM1 de ce depot ne
dit rien de l'interrogation avec reprise par un pronom ; je ne m'appuie pas sur lui ici. J'ai valide cet item deux fois, le
2026-09-23 et le 2026-10-14, et ma barre n'a pas change.

**Ce que je recommande, si `camille-po` le prend** : *"D'après ce texte, que permet ce transport de pollen à de nombreuses
plantes ?"*. C'est la forme sans reprise de l'item 9 du meme paquet (*"que permet la chaleur retenue par cette couche
d'air ?"*), et la bonne reponse ne change pas. **Rejeu 0,25**, le precedent de `S54-OBS-SCI-CONSIGNE` sur ce meme jeu (les 12 enonces
raccourcis, rien d'autre change) ; 0,25 en tout si `N2` entre dans le meme commit. Orthotypographie : au `relecteur-pedagogique`.

### 4.2 `N2` : les mots cites dans les indices, sans marque

**Ni refus ni reserve pour ce paquet. Recommandation non bloquante, et point d'audit pour les paquets suivants.**

**Ce que le relecteur voit est juste, et plus qu'une convention** : *"après les mots ce qui permet"* se lit comme le debut d'une
relative ; *"après le mot aussi"* se lit comme *"apres le mot, aussi"*. Un indice qu'on lit de travers aide moins. **Pourquoi ce
n'est pas une reserve** : chacun des trois indices commence par la phrase a relire (*"Relis la dernière phrase du texte"*,
*"la deuxième"*), et cette moitie suffit a l'enfant bloque : il relit la bonne phrase et y trouve la reponse. La seconde moitie
affine, elle ne porte pas l'aide.

**Ce que je recommande** : sur ces trois indices (lignes 112, 122 et 127), le mot ou les mots cites entre chevrons, comme dans les
paquets de francais de CE1 (*« Qui est-ce qui ? »*) : *"après les mots « ce qui permet »"*, *"après le mot « aussi »"*,
*"après le mot « permet »"*. Aucun des trois ne contient alors une proposition de son item. Meme commit et meme prix que `N1`.

**Pour les lots suivants** : un mot du texte cite dans un indice ou un enonce se marque entre chevrons. Je le regarde a chaque
audit d'un lot a texte support, comme l'intrus du premier mot. C'est un point d'audit date de ce jour, pas une condition
retroactive.

### 4.3 `N3` : *"parc"* et *"élèves"*, sans determinant

**Ni refus ni reserve, rien a changer.**

**Motif** : dans *"au parc"* et *"aux élèves"*, le determinant est contracte avec *à*. Donner *"le parc"* ou *"les élèves"*
ecrirait un groupe qui n'est pas dans la phrase, contre `VSJ-CE1-1` (*"des mots ou des groupes copiés de la phrase"*) ; et
*"au parc"* n'est ni le groupe place apres le verbe ni un nom (`VSJ-CE1-3`). *"parc"* et *"élèves"* sont la famille (b) a la
lettre. **Aucun signe ne designe la bonne reponse** : la forme nue marque, au plus, un distracteur, et l'enfant doit encore
choisir entre le sujet et le groupe qui suit le verbe, ce qui est la competence. *"élèves"* est en plus un distracteur plausible :
des personnes, qui pourraient faire une action.

### 4.4 `N4` : *"deux majuscules"*

**Correction faite**, annotation datee en fin de `FICHE-bandit-manchot-phrases-types-ce1.md`, renvoi a la phrase visee. Il ne
manque qu'**une** majuscule, celle de la premiere lettre, que deux regles demandent ensemble. Mon compte etait faux, dans la
fiche et dans `lecture-nadia-palier-1.md` (2.2) ; le constat et le verdict ne changent pas.

## 5. Documentation dont je suis l'auteure : `D1` a `D5`

### 5.1 `D1` : `E1`, `E2`, `E3`

**Le relecteur a raison.** `git grep` sur ma propre base de travail (`ea6d6f92`) trouve `E1` et `E2` dans
`FICHE-bandit-manchot-verbe-sujet-ce1.md` (lignes 64 et 92, `E1` et `E2` de `marc-dev`, dans une fiche que j'ai ecrite), et
`E3` dans `docs/backlog/backlog.md` (4 lignes) et `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` (1 ligne). **Mes deux
phrases etaient fausses le jour ou je les ai ecrites.** Annotation datee en fin de `rejeu-nadia-palier-1.md`, renvoi aux deux
phrases : ce sont les reperes de la demande de l'agent lanceur, lus par leur contenu, cite a chaque fois. Aucun verdict ne change.

### 5.2 `D2` : *"cinq paquets candidats de Y"*

**Le relecteur a raison.** Y a trois lots (`-10`, `-15`, `-24`) ; la section 0 nomme quatre paquets lus en entier, trois de Y et
celui de X. Annotation datee en fin d'`arbitrage-nadia-palier-1.md`, renvoi a l'en-tete. La section 3 repose sur ces quatre
lectures et sur le referentiel, et rien ne change.

### 5.3 `D3` : *"`Q-N4` = non"*, sans les mots de l'utilisatrice

**Le relecteur a raison, et je ne peux pas corriger seule.** *"`Q-N4` = non"* est ma notation ; `DEC-0062` 3.1 exige ses mots
entre guillemets. **Aucun fichier du depot ne les porte** (`DEC-0120`, ligne 1387, ecrit la meme notation), et je ne les ai pas
recus. Je ne les invente pas. **Ce que j'ai fait** : une annotation datee dans `rejeu-nadia-palier-1.md` et dans la fiche de
`phrases-types`, renvoi aux deux phrases, qui dit la forme 2 incomplete. **Ce que je demande** : a `sofia-sm`, d'obtenir de
l'agent lanceur les mots de l'utilisatrice et leur date, que j'ajouterai par une annotation datee.

**Ce qui en depend : aucun verdict.** `TPH-CE1-R1` est non bloquante par le precedent de `RDO-CP-R1`, ecrit avant la question
(`rejeu-nadia-palier-1.md`, section 3) ; `RCT-CE1-R1` suit le meme regime. `Q-N4` portait sur ma recommandation, plus stricte, de
rendre le pli bloquant pour les jeux suivants : sans les mots de l'utilisatrice, cette recommandation n'est pas acceptee, et le
regime reste le meme.

### 5.4 `D4` : la date du *"oui"*

**Le relecteur a raison.** La phrase date ma decision (2026-10-13), pas la reponse. La reponse est du **2026-10-14** (`DEC-0120`
A.53, *"Mots du 2026-10-14"*). Annotation datee en fin d'`arbitrage-nadia-palier-1.md`, renvoi a la phrase.

### 5.5 `D5` : l'en-tete *"brouillon soumis a nadia-prof"*, ce que j'attends de `marc-dev`

1. **Que l'en-tete ne porte plus d'etat de validation.** *"brouillon soumis a nadia-prof"* est faux depuis mon verdict, et un
   etat recopie dans un commentaire se perime a chaque verdict. L'en-tete garde l'histoire (*"S53-FRA-VERBE-SUJET, s53, ecrit par
   marc-dev"*) et renvoie a la fiche de conformite pour l'etat, sans le recopier.
2. **Que le diff ne touche que des commentaires** des trois paquets : `git diff -U0 <base> <commit>` sur les trois fichiers ne
   montre aucune ligne ajoutee ou retiree qui ne commence pas par `#`. A cette condition, **aucun rejeu**, prix 0.
3. **Les neuf autres paquets** (releve, section 3.4) relevent de la meme demande et de la meme condition, dans le meme commit
   s'il le veut. La forme exacte de l'en-tete est la sienne.

## 6. Ce dont je reste la seule garde, ajoute par ce fichier

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Un mot cite dans un indice ou un enonce est marque entre chevrons (lots a texte support suivants) | un test verrait les chevrons, pas qu'un mot est cite |
| Le message de reprise ne deborde pas de sa boite a 744 | le garde-fou de `S57-MESSAGE-PARTIE-PERDUE` lit le texte, pas le rendu |

## 7. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `Q-R5` (section 1) pour la clause `Q-R4` ; `Q-T7` : aucun rejeu, prix 0 ; `Q-F1` : lecture confirmee, source s48 5.4 ; `N1` et `N2`, recommandations non bloquantes, rejeu 0,25 si prises |
| `sofia-sm` | `D3` : les mots de l'utilisatrice pour `Q-N4`, et leur date, a obtenir de l'agent lanceur ; `R9` de `cloture-palier-1.md` : `N1` a `N4` et `D1` a `D4` traites ici, `D5` en 5.5 |
| `marc-dev` | `Q-F1` (section 3) pour `S57-VSJ-GARDE-VERBE-CITE` ; `D5` (5.5) |
| `theo-archi` | `Q-R5` : la base d'une branche de forme se nomme et ne bouge pas sans remesure |
| `relecteur-pedagogique` | `N4`, `D1` a `D4` : corrections faites par annotation datee ; l'orthotypographie de `N1` et `N2` si elles sont prises |

## 8. Ce que ce fichier ne fait pas

Il ne joue aucun jar et ne prononce aucun Done. Il ne touche ni la matrice, ni un catalogue, ni un paquet, ni du code. Il n'ecrit
ni n'alloue **aucun identifiant de condition ou de reserve** : les codes `R1` a `R12` sont des reperes de ce fichier, `N1` a `N4`
et `D1` a `D5` ceux du releve. Fichiers annotes dans le meme commit : `rejeu-nadia-palier-1.md`, `arbitrage-nadia-palier-1.md`,
`FICHE-bandit-manchot-phrases-types-ce1.md`.

**Tarifs** : aucun prix neuf ; reponses prises sur les arbitrages de A et de B. Marges de rejeu de A, A' et B intactes.

**Signe** : `nadia-prof`, s57, palier 1, 2026-10-15.

## 9. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les quatre fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2580 tests (120, 31, 486, 1566,
  371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` d'un rapport Surefire de `bootstrap`). Le
  build a lu ce fichier avant l'ecriture de cette section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), fichiers du commit indexes,
  passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : sort 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance :
  aucun de ses chemins n'est touche.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
