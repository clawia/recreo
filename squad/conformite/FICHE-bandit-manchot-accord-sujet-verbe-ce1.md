# Fiche de conformite - bandit-manchot-accord-sujet-verbe-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-accord-sujet-verbe.yaml`
  (24 items, type `question`), ecrit en s53, corrige et branche par `marc-dev` (`c9c80937`).
- **Entree de catalogue** : `bandit-manchot-accord-sujet-verbe-ce1`, titre servi *"Un ou plusieurs ?"*,
  consigne servie *"Lis bien, puis choisis la bonne forme du verbe."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-14`. **Story** : `S55-BR-FRA-CE1-L1`. **Decision** : `DEC-0115`.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-14`,
  *"Accorder le verbe avec son sujet au présent, quand le sujet est un pronom personnel ou un nom placé
  juste avant le verbe."*
- **Conditions opposables** : `ACV-CE1-1` a `ACV-CE1-3`, `TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1`, `-2`,
  `-5` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 1, 2 et 5.5), avec la barre de s54 sur
  `TRV-CE1-5` ; `K1` a `K3` (`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, section 4).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-08 par `nadia-prof` (s55,
  instance A), apres avoir joue le jar servi. Fiche ouverte par ce verdict.
- **Etat au 2026-10-11** : `ACV-CE1-R1` et `ACV-CE1-R2` levees au rejeu du 2026-10-11 (s56) ; plus aucune reserve ouverte. Statut global inchange.
- **Derniere mise a jour** : 2026-10-11 ; avant : 2026-10-08.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-08 (`nadia-prof`, s55, instance A) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `FRA-CE1-14` a `couvert-valide`, sans mention
de couverture partielle, et faire des stories des reserves `ACV-CE1-R1` et `ACV-CE1-R2` (section 4).

#### 1. Ce que j'ai fait

**Audit joue.** Worktree `s55-nadia-audit-a` a `fa4ddc7e` (`round-two`, qui contient `c9c80937`).
`bash outils/mvn-jdk21.sh -B clean verify` sur la racine : BUILD SUCCESS, tests lances sur Java
21.0.10 (domain 120, application 18, adapter-web 402, adapter-content 1389, bootstrap 312, dernier bloc
6). **Je n'ai lance que le job `build`** ; les jobs `prefixes`, `chemins` et `outils` de `ci.yml` ne
sont pas lances par moi (`marc-dev` les declare verts sur sa branche, rapport section 2).

`catalogue.yaml` et le paquet extraits du jar, `cmp` contre l'arbre : identiques. Jar copie dans mon
scratchpad et servi par mon propre processus sur un port dedie, arrete par son PID.

**Quarante parties de dix manches** par les routes servies (`POST /parties/...`, puis `/coups`) :
une juste du premier coup, une avec une erreur par manche, une avec deux, une avec trois erreurs
jusqu'a la revelation, trente-six en tirant l'une de ces conduites au hasard a chaque manche. **Les 24
items tires**, de 12 a 24 fois chacun, chacun avec au moins 16 erreurs et 2 revelations. Sur les 400
manches : consigne servie identique au catalogue, propositions identiques au fichier, aucun distracteur
credite juste, aucune bonne reponse refusee, aucun indice avant la premiere erreur, indice servi
identique au fichier apres chaque erreur, aucun item tire deux fois dans une partie. Apres trois
erreurs, *"Ce n'est pas ça."* puis *"Appuie sur la bonne réponse :"* et la forme ; l'ecran de fin redonne
la phrase et la bonne reponse. **Rendu non vu au navigateur** : HTML servi lu.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : un sujet place juste avant le trou, nom (17 items) ou pronom
(7), et trois formes du present du meme verbe. Ce n'est pas `FRA-CE1-17` (sujet toujours pronom) ni
`FRA-CM1-06` (sujet eloigne). Une limite de la ligne telle qu'elle est servie est l'objet de
`ACV-CE1-R2`.

#### 3. `K1` a `K3` et les conditions

**Mot pour mot** : les deux indices de `K1`, les deux lignes d'en-tete, les deux enonces de `K2` et la
consigne de `K3` sont dans le paquet et le catalogue exactement comme mon arbitrage les ecrit, espaces
insecables comprises (recherche de chaque ligne de bloc dans les fichiers, toutes trouvees).

| Point | Constat |
|---|---|
| `K1` | substitution sur les items 1, 2, 4, 7, 10, 13, 16, 19, 20, 22, 23, 24 ; relecture sur 6, 9, 12, 18, 21 ; 3, 5, 8, 11, 14, 15, 17 inchanges, et sur ces sept les deux distracteurs ont l'autre nombre que la bonne reponse : le critere de nombre y departage. *"compte-les"* n'est plus servi sur un sujet seul. **Tenu, sauf l'item 6 (`ACV-CE1-R1`)** |
| `K2` | *"il ___ au marché"*, *"il ___ un gâteau"* ; bonnes reponses et distracteurs inchanges. **Tenu** |
| `K3` | consigne servie identique a celle du present CE1. **Tenu** |
| `ACV-CE1-1` a `-3` | inchanges depuis mon arbitrage (17 sujets nominaux, 12 pluriels, 12 singuliers, aucun sujet coordonne, 13 verbes de `PRS-CE1-1`). **Tenues** |
| `TRV-CE1-1` a `-4`, `-6`, `-7`, `LON-CE1-1`, `-2`, `-5` | **tenues**. `TRV-CE1-6` apres `K2` : prenoms Leo, Mia, Ines, Karim, deux et deux ; plus aucune activite de courses ou de cuisine du seul cote feminin |
| `TRV-CE1-5` | **en partie** : douze mots au plus, pas uniforme, aucun renvoi a l'ecoute, aucun critere satisfait par un distracteur ; **mais l'indice de l'item 6 contient la bonne reponse** (`ACV-CE1-R1`) |

**Le test de `K1` (`k1LeNombreSeulNEstUnCritereQueSurLesItemsOuIlDepartage`) est plus etroit que ma
barre**, et je le dis pour qu'on ne le croie pas plus large : il ne juge que les indices qui contiennent
*plusieurs*, *combien* ou *compte*. Un indice qui donnerait un autre critere satisfait par un
distracteur, ou qui contiendrait une proposition de son item, passerait.

**`W1`** non pris (item 12 garde *il* : les trois sujets pronoms singuliers sont *il*). **`W2`** non
fait, comme je le demandais : *ecoutons*, *parlons*, *travaillons*, *dessinons*, *lavons* restent.
Ni l'un ni l'autre n'etait une condition ; aucune reserve.

#### 4. Les reserves, datees du 2026-10-08

**Deux corrections de mes propres constats, avant de m'en servir.**

1. Mon arbitrage ecrivait des deux indices de `K1` : *"chacun [...] ne donne pas la reponse"*. **C'est
   faux pour l'indice de relecture sur l'item 6**, dont la bonne reponse est *va* : *"lequel va avec
   lui ?"* contient *va*. Le defaut est dans mon texte, que `marc-dev` a applique mot pour mot et
   signale (ecart `E1`). Ce n'est pas un ecart du developpeur.
2. Mon arbitrage ecrivait que la marque muette *-ent* serait tiree *"environ 4 manches sur 10"*. Ce
   chiffre est celui des **9 items du premier groupe** (singuliers et pluriels : 3,75 attendues sur 10,
   **3,5 mesurees** sur 400 manches). La forme en *-ent* qui ne s'entend pas n'est la **bonne reponse**
   que de **5 items** (2, 9, 20, 21, 23) : **2,1 attendues sur 10, 1,9 mesurees**. C'est ce second
   chiffre qui dit ce que l'enfant ecrit, et il est plus bas que celui que j'avais annonce.

**`ACV-CE1-R1` - l'indice de l'item 6 contient la bonne reponse (`TRV-CE1-5`, "jamais la reponse").**
Item *"« aller » : il ___ au marché."*, propositions *va*, *vont*, *vas*. Apres une erreur, l'enfant lit
*"Lis le sujet avec chaque verbe proposé : lequel va avec lui ?"* et voit le mot *va* ecrit a cote de
deux propositions dont l'une est *va* : il peut repondre en retrouvant le mot, sans lire le sujet. Je
ne refuse pas sur ce point : un item sur 24, l'indice n'apparait qu'apres une erreur, et rien de faux
n'est enseigne. **Je tranche `E1` : je n'accepte pas le texte sur l'item 6.** Texte de remplacement, a
poser a la place de la ligne `indice` de l'item 6 seulement (les items 9, 12, 18, 21 gardent le leur,
qui ne contient aucune de leurs propositions ; ce texte-ci ne peut pas aller sur l'item 12, dont la
bonne reponse est *est*) :

```yaml
    indice: "Lis le sujet avec chaque verbe proposé : lequel est le bon ?"
```

11 mots, espace insecable devant `:` et `?` comme le paquet (`TYP-1`). **Ce qui leve la reserve** : ce
texte sur l'item 6, rien d'autre ne change. Rejeu mot pour mot (0,25, `PAR-10`).

**`ACV-CE1-R2` - la marque muette du pluriel est trop rarement la reponse.** C'est la reserve
neuve que mon arbitrage annoncait (section 4, *"a verifier en jouant"*) ; je ne deplace pas une barre,
`ACV-CE1-2` ne fixe aucune proportion. Sur les 12 items a sujet pluriel, 7 portent un verbe dont le
pluriel s'entend (*vont*, *sont*, *ont*, *font*, *viennent*, *disent*) : l'enfant qui parle trouve la
reponse a l'oreille, sans regarder le sujet ecrit. La difficulte ecrite de la ligne au CE1, *-ent* qui
ne se prononce pas, n'est la bonne reponse que d'environ deux manches sur dix. **Propriete a tenir** :
au moins **8 des 12 items a sujet pluriel** ont pour bonne reponse une forme du premier groupe en *-ent*,
verbes de `PRS-CE1-1` seulement, distracteurs de meme famille (autre nombre, autre personne), et
`ACV-CE1-1`, `TRV-CE1-2` restant tenues. Les textes sont au developpeur ; je les juge en rejouant.
Trois items changes sur 24 se paient au tarif d'un rejeu (0,5, `PAR-10`), pas d'un lot (`PAR-14`).

#### 5. Exactitude, niveau, ton

- **Les 24 formes sont justes**, relues a l'ecran ; les 48 distracteurs sont des formes reelles du
  present du meme verbe, aucune absurde.
- **Indices** : le substitut *"Remplace le sujet par « il », « elle », « ils » ou « elles », puis
  relis."* ramene l'item a ce que `FRA-CE1-17` fait travailler ; 11 mots, lisible par un CE1.
- **A reprendre, sans reserve** : sur les items 3 et 15 (sujets *ils*, *elles*), l'indice garde
  *"combien en voit-on ?"* ; on ne voit qu'un mot. Je les ai laisses tels quels dans mon arbitrage et ce
  n'est pas un critere qu'un distracteur satisfait ; je le note pour le prochain passage du paquet.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1AccordSujetVerbeTest` tient les comptes d'`ACV-CE1-1`, la consigne, les sujets de `K2`, le
nombre seul la ou il ne departage pas, la longueur. **Il ne verifie pas** qu'un indice ne contient pas
une proposition de son item (`ACV-CE1-R1` : la propriete est mecanique, aucun test ne la porte), qu'un
indice ne donne pas un autre critere satisfait par un distracteur, la justesse des formes, la part des
pluriels muets (`ACV-CE1-R2`), ni les roles par genre. **Je les tiens en jouant.**

**Identifiants** : deux neufs, `ACV-CE1-R1` et `ACV-CE1-R2`. Forme copiee de `PCO-CE1-R1` : trois
segments, niveau au milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloues dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme diff. Aucun renomme.

**Tarif consomme** : audit 1,0 (premier passage). Aucun rejeu a s55.

**Signe** : `nadia-prof`, s55, instance A, 2026-10-08.

---

### Rejeu du 2026-10-11 (`nadia-prof`, s56) : `ACV-CE1-R1` et `ACV-CE1-R2` **LEVEES**

**`FRA-CE1-14` reste `couvert-valide`** ; aucun mouvement de matrice. Rejeu groupe, 0,5 (arbitrage A de s56, section 4).

**Ce que j'ai fait.** Worktree `s56-nadia-art` a `a6ca1751` (contient `2b66a441` et `955951f2`) ; build, jar et arret :
`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, section 1. Par les routes servies : 3 parties, erreur sur chacun des
deux distracteurs des items 3, 6, 11 et 15 puis la bonne, 20 items tires. Au navigateur a 744 x 551 : une partie de dix
manches, items 3 et 11 joues avec deux erreurs ; l'indice est au-dessus du pli (bas du texte a y = 474).

**`ACV-CE1-R1` - LEVEE.** Item 6, apres une erreur sur *vont* puis sur *vas* : *"Lis le sujet avec chaque verbe proposé :
lequel est le bon ?"*, mon texte a la lettre ; il ne contient aucune des trois propositions.

**`ACV-CE1-R2` - LEVEE.** Huit items a sujet pluriel sur douze ont pour bonne reponse une forme du premier groupe en
*-ent* qui ne s'entend pas (2, 3, 9, 11, 15, 20, 21, 23 ; lecture des 24 items du fichier). Les trois items changes
(*ils chantent*, *les oiseaux volent*, *elles marchent*) : formes justes, distracteurs de la meme famille et reels (*chante*,
*chantons* ; *vole*, *voles* ; *marche*, *marchons*), aucun role par genre. Indices joues : items 3 et 15, l'indice de
relecture, qui ne contient aucune de leurs propositions et ne departage pas par le nombre seul la ou *chantons*,
*marchons* sont pluriels ; item 11, *"combien en voit-on ?"*, et ses deux distracteurs sont au singulier : le nombre
departage (`K1`). Le choix de `marc-dev` de changer le verbe des items 3 et 15 (`E6`) est accepte : il paie la reserve et
le point *"combien en voit-on ?"* de ma section 5 dans le meme geste. `ACV-CE1-1` tenue (17 sujets nominaux, 12 pluriels,
12 singuliers).

**Ce dont je reste la seule garde** : inchange (section 6 du bloc du 2026-10-08), sauf la part des pluriels muets,
desormais portee par `acvR2HuitItemsAuMoinsASujetPluriel`.

**Signe** : `nadia-prof`, s56, 2026-10-11.
