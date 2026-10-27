# s57 - palier 5 : avis de theo-archi sur les ajustements T1 a T9 des gardes de S57-BR-QLM-CE1-10 et -12

- **Auteur** : `theo-archi`. Date : 2026-10-27, debut 08:53 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-a1012`, base `round-two` a `db9c8aaa` (`git rev-parse`). Aucun paquet `ce1-qlm-paysages` ni `ce1-qlm-generations` n'existe a cette base ; trois paquets `ce1-qlm-*` existent (`-01`, `-02`, `-03`).
- **Demande** : `arbitrage-nadia-contradictions-ce1-qlm-10-12.md` (cite *"son arbitrage du 27"*), section 3 (`T1` a `T9`), regles corrigees des sections 1.1 (`R10-2`, `R10-4`, `R10-5`) et 2.1 (`R12-1`, `R12-2` B, `R12-3`, `R12-5`).
- **Lu** : son arbitrage du 27 en entier ; `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md`, sections 1.1 a 2.2 (`R12-2`, `R12-6`, `R12-7`, `N12-9`) ; mon avis `avis-theo-palier-5-gardes-ce1-qlm-10-12.md` (cite *"mon avis du 26"*), que ce fichier ne reecrit pas ; `verifierLesIndices` de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java` ; `PaquetLu.sansAccent` ; les champs de `cp-frise-de-la-journee.yaml` ; les suites de `ce1-qlm-etapes-de-developpement.yaml`.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition, aucune regle ArchUnit, aucun `ADR` (gardes de contenu sur du YAML, forme d'`ADR-0013`, comme mon avis du 26, `V8`) ; il ne prononce aucun Done ; il ne touche aucun fichier de `-04`, `-05`, `-02`, `-06`, `-08`. Les codes `X1`..., `F1`..., `P1`... sont des reperes de ce fichier.

## 0. Verdict, une ligne par ajustement

| Ajustement | Garde | Forme retenue |
|---|---|---|
| `T1` | `G12-3` | permutation de chaque distracteur lue sur les rangs de la bonne suite : admis `(1,0,2)`, `(0,2,1)`, `(1,2,0)`, `(2,0,1)` ; refus de `(2,1,0)` (l'inverse) et de la paire `{(1,0,2), (0,2,1)}` (les deux echanges de voisins) |
| `T2` | `G12-2` | groupe `pre` admis dans un item `ordre` seulement, ses trois parties toutes `pre` ; une proposition `pre` dans un item `voisin` est refusee |
| `T3` | `G12-2` | confirme : *la grand-mère* n'a aucun groupe, refus par egalite |
| `T4` | `G10-1`, `G10-5` | confirme : *un grand champ* refuse par l'egalite de `G10-1` ; `G10-1` tient a lui seul *"aucun adjectif"* dans l'enonce et les propositions ; la liste des dix-sept mots reste, seule garde de l'indice |
| `T5` | `G12-1` | confirme, motif inchange ; deux des dix formes ne sont attestees dans aucun champ du corpus (section 5) |
| `T6` | `G12-2` | confirme : *la naissance d'un enfant* n'a aucun groupe, refus par egalite (`G12-4` la refuse aussi, appelee seule) |
| `T7` | `G12-4` | `VOISINS_ADMIS` = la table de `R12-5` en (reference, sens) vers (bonne reponse, distracteur d'enfance), second distracteur dans `ADULTE` ; tout autre jeu refuse |
| `T8` | `G10-4`, `G10-7` | A : aucune forme des quatre paysages ; B *sur le littoral* : *littoral* present, *par exemple* suivi d'un ou deux elements propres du littoral ; `G10-7` : au moins un tel item. *"Pas une definition"* : **non garde**, lu par `nadia-prof` |
| `T9` | `A6`, `A8` | `A6` tient tel quel, verifie par execution ; `A8` encode dans `G12-6`, recherche par jetons sur les formes fermees, la plus longue d'abord, bornee par lettre ou tiret |

**Desaccord avec ses regles : aucun.** Le calcul de la section 1 confirme sa regle neuve sur la paire. Trois points restent a trancher par elle, en section 10 (`P1` a `P3`), et un constat sur `-01` lui est remonte (`F1`).

## 1. `T1` - `G12-3`, echanges et rotations

### 1.1 Forme

Item `ordre` (bonne reponse coupee sur `,` en trois parties, mon avis du 26, 2.1) :

1. les trois parties de la bonne suite, normalisees (mon avis du 26, 2.2), sont **distinctes** ; sinon refus. C'est ce qui rend la permutation lisible, et c'est ce que *"chacun d'une generation differente"* (`R12-2` B) exige d'une suite de prenoms, a la part pres que la garde ne lit pas les generations des prenoms ;
2. chaque distracteur est fait des trois memes elements ; sa permutation `p` est la suite des rangs, dans la bonne suite, de ses trois parties ;
3. `p = (2,1,0)` : refus *"ordre inverse"* ;
4. l'ensemble des deux permutations egal a `{(1,0,2), (0,2,1)}` : refus *"les deux echanges de voisins ensemble"* ;
5. `p = (0,1,2)` n'arrive pas : `TRV-CE1-2` (trois propositions distinctes, test transverse) le refuse avant.

Restent admis, un par distracteur : `(1,0,2)` et `(0,2,1)` (echanges de voisins), `(1,2,0)` et `(2,0,1)` (rotations). La regle 4 ne demande pas *"deux distracteurs de meme nature"* : deux rotations restent admises, et une sonde le tient (1.4).

### 1.2 Le calcul sur une suite de trois

Bonne suite *a, b, c*. Pour chaque proposition, le score est le nombre de positions ou elle porte le meme element qu'une autre proposition, somme sur les deux autres. *"La proposition la plus ressemblante aux deux autres"* est celle de score maximal, quand il est unique. Calcul fait par le programme de la section 9 sur les dix paires possibles :

| Paire de distracteurs | Scores (bonne ; d1 ; d2) | Proposition designee | Statut |
|---|---|---|---|
| echange 1-2 + echange 2-3 | 2 ; 1 ; 1 | **la bonne reponse** | refusee (regle neuve) |
| echange 1-2 + rotation `(1,2,0)` | 1 ; 2 ; 1 | l'echange | admise |
| echange 1-2 + rotation `(2,0,1)` | 1 ; 2 ; 1 | l'echange | admise |
| echange 2-3 + rotation `(1,2,0)` | 1 ; 2 ; 1 | l'echange | admise |
| echange 2-3 + rotation `(2,0,1)` | 1 ; 2 ; 1 | l'echange | admise |
| deux rotations | 0 ; 0 ; 0 | aucune | admise |
| echange + inverse (deux paires) | 2 ; 1 ; 1 | **la bonne reponse** | refusee (inverse) |
| rotation + inverse (deux paires) | 1 ; 1 ; 2 | l'inverse | refusee (inverse) |

**Ce que le calcul etablit** : parmi les six paires que l'ancienne forme admettait, une seule designe la bonne reponse, et c'est celle que `nadia-prof` refuse. Sur les cinq qui restent, quatre designent un distracteur et une ne designe rien. Sa phrase *"les autres paires ne designent pas la bonne reponse ainsi"* est exacte sous cette mesure.

### 1.3 Les autres tailles

`R12-2` fixe la suite a trois elements, et la garde de tache refuse toute bonne reponse qui n'a ni une ni trois parties (mon avis du 26, 2.1) : le paquet ne peut porter aucune autre taille. **Ce qui me ferait reecrire cette garde** : une suite de quatre (les quatre generations y tiennent en 4 mots, sous `LON-CE1-5`). L'echange des extremes n'y est plus l'inverse, et la table d'admission est a refaire ; elle ne se generalise pas en renommant les classes.

### 1.4 Materiau reel

**Perimetre** : tout item du corpus servi (section 9) dont la bonne reponse se coupe sur `,` en trois parties distinctes et dont les deux distracteurs sont des permutations de ces parties. **58 items** dans quatre fichiers : `cp-ranger-nombres` 24, `ce1-comparer-ranger` 20, `ce1-ordre-alphabetique` 10, `ce1-qlm-etapes-de-developpement` 4.

| Paire | Items |
|---|---|
| inverse + echange 2-3 | 27 |
| inverse + echange 1-2 | 14 |
| inverse + rotation | 16 |
| echange 1-2 + echange 2-3 | 1 (*"graine, plante, fleur"*, `-01`) |

La proposition la plus ressemblante designe la bonne reponse dans **42** de ces 58 items, un distracteur dans 16. Aucun de ces fichiers n'est lu par le test de `-12`, et `G12-3` y refuserait les 58 (57 pour l'inverse, 1 pour la paire). **La regle neuve mord donc sur une ecriture reelle**, et pas seulement sur ma sonde : la paire qu'elle interdit a deja ete ecrite dans ce depot, sur `-01`.

### 1.5 Sondes et mutants

Sondes, dans `contreexemple/PaquetCe1QlmGenerationsTest/contenu/`, enonce *"Range ces générations du plus âgé au plus jeune."*, bonne reponse *"grands-parents, parents, enfants"* :

| Sonde | Distracteurs | Attendu |
|---|---|---|
| echange + rotation (exemple de `R12-2` B) | *parents, grands-parents, enfants* ; *parents, enfants, grands-parents* | passe |
| deux rotations | *parents, enfants, grands-parents* ; *enfants, grands-parents, parents* | passe |
| echange 2-3 + rotation | *grands-parents, enfants, parents* ; *enfants, grands-parents, parents* | passe |
| l'inverse | *enfants, parents, grands-parents* ; *parents, grands-parents, enfants* | refus, *"ordre inverse"* |
| les deux echanges de voisins | *parents, grands-parents, enfants* ; *grands-parents, enfants, parents* | refus, *"les deux echanges de voisins"* |
| idem, prenoms (*Ali, Sara, Lina*) | *Sara, Ali, Lina* ; *Ali, Lina, Sara* | refus, meme message |
| idem, evenements (`SUITE_ADMISE`) | *entrée à l'école, naissance, premier emploi* ; *naissance, premier emploi, entrée à l'école* | refus, meme message |

Quatre mutants, chacun tue par au moins une sonde (programme de la section 9) : sans la regle de paire (3 sondes au verdict change) ; refus de deux distracteurs de meme nature (1, la sonde des deux rotations) ; refus de tout echange de voisins (3) ; refus de toute rotation, la garde de `-01` (4).

## 2. `T2`, `T3`, `T6` - `G12-2`

**Forme retenue (`T2`).** Item `voisin` : chaque proposition a un groupe dans `{gen, evt}` ; une proposition egale a un prenom de l'enonce est refusee, message *"prenom en proposition d'un item voisin"*, avant le controle *"meme groupe"*. Item `ordre` : chaque partie a un groupe, les trois parties le meme, dans `{gen, evt, pre}`. Le reste de `G12-2` (formes d'enonce, reference) est inchange.

La sonde qui mord : *"Sara est la maman de Lina. Ali est le papa de Sara. Pour Lina, de quelle génération est Ali ?"* avec *Ali*, *Sara*, *Lina*. Forme d'enonce valide, trois propositions du meme groupe : **l'ancienne forme la laisse passer, la nouvelle la refuse** (mesure, section 9). C'est une sonde structurelle de ma main ; elle n'a pas a etre un item defendable.

**`T3` confirme.** *La grand-mère*, normalisee, n'est ni une generation ni un evenement ni un prenom : refus *"proposition hors des listes"*. *Les parents*, *les enfants* sont des generations de `R12-1` et passent par construction ; la liste `PARENTE` de `G12-6` ne sert qu'aux possessifs. Materiau reel : **aucune** proposition du corpus n'est egale a un mot de parente (section 9) ; la sonde est donc la mienne.

**`T6` confirme.** `EVENEMENTS` porte *naissance de son enfant* et pas *naissance d'un enfant* : refus par `G12-2`, *"proposition hors des listes"* ; dans une suite, refus de la partie ; en reference d'enonce, refus *"X a un groupe"*. `nadia-prof` range `T6` sous `G12-4` : `G12-4`, appelee seule sur la meme sonde, la refuse aussi (second distracteur hors `ADULTE`), mais la sonde attend le message de `G12-2`, qui la voit en premier. Materiau reel : *naissance d'un enfant* n'est ecrit dans aucun champ du corpus.

## 3. `T7` - `G12-4`, la table de `R12-5`

### 3.1 La table, telle que `nadia-prof` la fixe

Copiee de son arbitrage du 27, section 2.1, `R12-5` corrige :

| Enonce | Bonne reponse | Distracteurs |
|---|---|---|
| *juste après sa naissance* | *l'entrée à l'école* | *l'entrée au collège* ; *le premier emploi* ou *la naissance de son enfant* |
| *juste avant l'entrée à l'école* | *sa naissance* | *l'entrée au collège* ; *le premier emploi* ou *la naissance de son enfant* |
| *juste après l'entrée à l'école* | *l'entrée au collège* | *sa naissance* ; *le premier emploi* ou *la naissance de son enfant* |
| *juste avant l'entrée au collège* | *l'entrée à l'école* | *sa naissance* ; *le premier emploi* ou *la naissance de son enfant* |

### 3.2 Forme

- `VOISINS_ADMIS`, constante du test : (*naissance*, apres) vers (*entree a l'ecole*, *entree au college*) ; (*entree a l'ecole*, avant) vers (*naissance*, *entree au college*) ; (*entree a l'ecole*, apres) vers (*entree au college*, *naissance*) ; (*entree au college*, avant) vers (*entree a l'ecole*, *naissance*). Source : son arbitrage du 27, section 2.1, comme `R12-9` le veut pour les listes.
- `ADULTE` : {*premier emploi*, *naissance de son enfant*}.
- Item `voisin` de groupe `evt` : la reference et le sens sont lus par `juste (avant|apres) (.+?) ?\?$` sur le texte sans accent (mon avis du 26, 2.2), la reference normalisee ; la cle (reference, sens) est dans `VOISINS_ADMIS`, sinon refus *"question hors de R12-6"* ; la bonne reponse est celle de la table ; l'ensemble des deux distracteurs contient le distracteur d'enfance de la table, et l'autre est dans `ADULTE`. Tout autre jeu est refuse.

Sous `G12-2` (aucune proposition n'est la reference, trois propositions distinctes), chaque ligne laisse trois distracteurs possibles, donc trois paires : deux admises, une refusee, toujours {*le premier emploi*, *la naissance de son enfant*}. Les douze cas ont ete executes, chacun au verdict attendu (section 9) ; la sonde que nadia nomme (*"juste apres sa naissance"* avec ces deux moments d'adulte) en est un.

**Le college** : la table le place dans chaque item A d'evenements, en distracteur (lignes 1 et 2), en bonne reponse (ligne 3) ou en reference de l'enonce (ligne 4). Aucune verification separee : `G12-4` l'impose. Son absence de toute suite est tenue par `SUITE_ADMISE` et par la regle de permutation de `G12-3`, comme dans mon avis du 26.

## 4. `T4` - `G10-1`, `G10-5`, et la liste des dix-sept mots

**Refus confirme.** *Un grand champ* : determinant *un* retire, *grand champ* n'est pas dans `FORME_ELEMENT`, refus de `G10-1`. La sonde *une grande ville*, *un grand champ* est appelee deux fois : sur `G10-5` seule, elle passe (`T4`) ; sur `G10-1` seule, elle est refusee.

**Ce que *"aucun adjectif"* change pour `G10-5`.** `R10-5` corrige porte l'interdit sur l'enonce et les propositions. Dans ces deux champs, `G10-1` le tient deja sans liste d'adjectifs : chaque proposition et chaque segment de la liste de A doivent etre **egaux** a un element ou a un paysage apres retrait d'un determinant, le lieu de B est une table de quatre lignes, et le reste de l'enonce est constant. Un adjectif, quel qu'il soit, rompt l'egalite. Materiau reel : `cm1-histoire-geographie-nommer-en-geographie`, voisin que `nadia-prof` relit, ecrit *"des fermes isolées"* et *"des immeubles collectifs"* ; l'un et l'autre seraient refuses par `G10-1`, comme les 29 propositions du corpus qui contiennent un element sans lui etre egales (section 9).

**La liste des dix-sept mots reste utile, et ne change pas**, pour trois raisons :

1. **l'indice** : `G10-1` ne le lit pas, et la premiere phrase de `R10-5` (aucun jugement) le vise. La liste y est la seule garde. Materiau reel : 28 indices du corpus portent une de ses formes (aucun `ce1-qlm-*`) ; les auteurs de ce depot ecrivent ces mots dans des indices ;
2. **le sujet de la forme A**, `(?:on|\p{L}+) voit`, un mot libre que `G10-1` ne lit pas ;
3. **une forme d'enonce neuve**, que mon avis du 26 (1.1) laisse entrer comme une ligne : sa partie libre ne serait lue que par `G10-5`.

Dans l'enonce et les propositions, `G10-5` devient un second verrou derriere `G10-1`. *"Aucun adjectif"* ne remplace pas la liste : il la complete la ou `G10-1` lit. Un adjectif qui n'est pas un jugement, dans un indice (*"par exemple la grande mer"*), n'est garde par rien : voir `P1`.

## 5. `T5` - `G12-1`

**Confirme**, motif de mon avis du 26 inchange : les dix moments que sert `cp-frise-de-la-journee.yaml`, plus *reveil* et *autrefois*.

Mesure sur le materiau reel (section 9) : les champs lus de `cp-frise-de-la-journee.yaml` (enonce, indice) ecrivent **huit** des dix moments, tous reconnus (*lever*, *petit-dejeuner*, *ecole du matin*, *dejeuner*, *diner*, *brosser les dents*, *pyjama*, *coucher*). *Goûter* et *école de l'après-midi* n'y existent que comme identifiants de zone (`gouter`, `ecole-apres-midi`), jamais dans un champ lu. Sur tout le corpus, *goûter* est ecrit 7 fois et reconnu ; *école de l'après-midi* ne l'est **nulle part**. Cette forme est donc la mienne, eprouvee seulement par une sonde de ma main ; une ecriture voisine (*l'après-midi* seul) ne serait pas refusee. Je ne l'elargis pas sans demande : `nadia-prof` confirme la liste telle que je l'ai ecrite.

## 6. `T8` - `G10-4`, `G10-7` et *littoral*

### 6.1 Forme

`G10-4`, indice sous la forme de mon avis du 26 (1.2), recherche bornee par `\p{L}` :

- **A** : aucune forme de `FORME_PAYSAGE` (les quatre paysages, et non plus celui de la bonne reponse seul) ; aucune forme d'un element propre du paysage de la bonne reponse. Le mot *littoral* etant une forme de paysage, *"il n'explique pas littoral"* est tenu pour ce qui le nomme.
- **B** : aucune forme d'un element des trois propositions (inchange).
- **B dont le lieu est *sur le littoral*** : (i) l'indice contient *littoral* ; (ii) il contient *par exemple*, suivi, jusqu'au premier `.`, `!`, `?` ou jusqu'a la fin, d'un ou de deux segments coupes sur `, ` et ` et ` ; (iii) chaque segment, determinant retire, est egal a une forme de *plage*, *mer* ou *phare*. Qu'aucun exemple ne soit une proposition est deja tenu par la regle B.
- **B dont le lieu n'est pas le littoral** : l'indice ne contient pas *littoral*. **Ajout tire de son texte** (*"dans eux seuls"*, `R10-4`), non demande dans `T8` : a confirmer ou retirer, `P3`.

`G10-7`, calcul sur les items avant toute lecture de l'en-tete, dans cet ordre : deux taches ; **au moins un item B dont le lieu est *sur le littoral*** ; `element` au moins 3 ; chacun des quatre paysages bonne reponse d'un item `paysage` ; comptes de l'en-tete. Le controle du littoral passe avant le plancher de 3 : le refus construit sur le paquet reel prive de ses items B *sur le littoral*, en-tete inchange, attend ainsi son propre message, et non celui du plancher ou des comptes.

**Ce que la table de `R10-3` impose a ces items** (calcule sur `TABLE_B`, mon avis du 26, 1.1) : la seule bonne reponse admise sous *sur le littoral* est *les pistes de ski* ; ses distracteurs sont un propre du littoral (*la plage*, *la mer* ou *le phare*) et un partage du littoral et de la montagne (*la forêt*, *le village*, *la falaise* ou *la rue*). L'exemple de l'indice est donc pris parmi les deux propres du littoral que l'item ne propose pas.

### 6.2 Ce qui est garde et ce qui ne l'est pas

| Sonde | Verdict mesure |
|---|---|
| A, *"Ce n'est pas le littoral."*, propositions *la montagne*, *la campagne*, *la ville* | refus ; **l'ancienne forme le laissait passer**, et `TRV-CE1-5` aussi (*le littoral* n'y est pas une proposition) |
| A, *"Sur le littoral, il y a par exemple la mer."*, *le littoral* en distracteur | refus |
| B littoral, *"Sur le littoral, il y a par exemple la mer."*, distracteurs *la plage*, *la forêt* (exemple de `R10-4`) | passe |
| idem, *"... par exemple la mer et le phare."* | passe |
| B littoral, *"Cherche ce qui va avec la neige."* | refus, pas de *littoral* |
| B littoral, *"Le littoral, c'est le bord de la mer."* | refus, pas de *par exemple* |
| B littoral, *"... par exemple le port."* | refus, *le port* n'est pas un propre du littoral |
| B littoral, *"... par exemple la plage."*, *la plage* proposee | refus, regle B |
| B montagne, *"Pense au littoral."* | refus (`P3`) |
| B littoral, *"Le littoral, c'est le bord de l'eau, par exemple la mer."* | **passe** |

**Non garde : que l'explication ne soit pas une definition.** La derniere sonde porte une definition faite de mots hors des listes, avant un *par exemple* bien forme, et passe. La garde refuse une definition sans *par exemple* et un exemple hors des propres du littoral ; elle ne lit pas le reste de la phrase. Lu par `nadia-prof`, comme son arbitrage du 27 le dit.

## 7. `T9` - `A6` et `A8`

### 7.1 `A6`, verifie

Les trois ajouts de mon avis du 26 tiennent sous les corrections du 27, mesure par les sondes de la section 9 :

- **forme d'enonce A de `R12-2` dans `G12-2`** (*juste avant*, *juste apres*, *de quelle generation*) : l'exemple de `R12-2` A passe ; aucune correction n'ajoute de forme ;
- **sens lie au groupe dans `G12-3`** (`gen`, `pre` : *du plus âgé au plus jeune* ; `evt` : *du premier au dernier*) : `pre` ne vit plus qu'en tache `ordre` (`T2`), la ou ce controle s'applique ; rien a changer ;
- **rang croissant d'une suite `gen`, `SUITE_ADMISE` pour `evt`** : independants de `T1`, qui porte sur les distracteurs ; la sonde d'evenements de 1.5 est refusee pour la paire et non pour la suite.

### 7.2 `A8` encode : premiere moitie de `R12-7`, dans `G12-6`

**Ou** : dans `G12-6`, qui porte deja `R12-7` (criteres 3, 4, `R12-7`) ; un verificateur de plus, appele seul sur ses sondes, message *"G12-6 (R12-7)"*. Aucun repere de garde neuf, aucun identifiant de condition.

**Forme, celle de `G10-4`** :

1. l'indice est mis sous la forme sans accent de mon avis du 26 (2.2) ;
2. il est decoupe en jetons par une seule alternation des formes fermees, **la plus longue d'abord**, bornee par `(?<![\p{L}-])` et `(?![\p{L}-])` : les generations au singulier et au pluriel (*enfant(s)*, *parent(s)*, *grand-parent(s)*, *arriere-grand-parent(s)*), vers leur forme de `GENERATIONS`, et les cinq evenements de `EVENEMENTS` ;
3. **A** : refus si les jetons contiennent la bonne reponse normalisee ;
4. **B** : refus si les jetons contiennent le premier element de la bonne suite ; pour une suite de prenoms, refus si l'indice brut contient le premier prenom, borne par `\p{L}`.

Les deux choix de 2 ont chacun leur sonde et leur mutant (section 9) :

- **la plus longue d'abord** : *grands-parents* contient *parents*, dans la table fermee elle-meme. Sonde *"Compte depuis Lina, sans aller jusqu'aux grands-parents."*, bonne reponse *celle des parents* : passe ; le mutant qui cherche la seule bonne reponse la refuse. Meme effet pour *naissance de son enfant* contre *naissance* (*"Commence par la naissance."* refuse, *"La naissance de son enfant n'est pas dans la suite."* passe) ;
- **le tiret dans la borne** : sonde *"Lina n'a pas encore de petits-enfants."*, bonne reponse *celle des enfants* : passe ; le mutant borne par `\p{L}` seul la refuse. **Ce choix ne repose que sur ma sonde** : sur le corpus, la borne par lettre seule et la borne par lettre ou tiret rendent le meme resultat sur tous les champs (section 9). Il coute une classe de caracteres ; ce qui me le ferait retirer est un faux rouge qu'il causerait.

**Ce qu'`A8` ne garde pas** : la periphrase par la parente (*"Le papa de la maman, c'est le grand-père"*, premier exemple de `N12-9` : *grand-pere* n'est pas une forme de generation) ; un evenement nomme sans son nom de liste (*l'école* pour *l'entrée à l'école*). Lus par `nadia-prof`.

## 8. Passages de mon avis du 26 que j'abandonne

Mon avis du 26 n'est pas reecrit. Chaque passage ci-dessous y reste, et cesse de valoir.

| Code | Passage | Marqueur |
|---|---|---|
| `X1` | 2.3, `G12-3`, colonne *Passe* : *"parents, grands-parents, enfants" et "grands-parents, enfants, parents" (echanges de deux voisins)* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 1 : ensemble, ces deux distracteurs sont refuses ; chacun passe avec une rotation] |
| `X2` | 4, `C2`, colonne *Ce que je fais en attendant* : *"les prenoms forment un troisieme groupe, admis seul ; en pratique ils ne servent que les suites de B"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 2 : le groupe des prenoms n'est admis qu'en tache `ordre`] |
| `X3` | 4, `C4` : *"si elle admet une taille, une liste fermee d'adjectifs (grand, petit, haut) entre devant le nom"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 4 : `nadia-prof` refuse la taille, aucune liste d'adjectifs n'entre] |
| `X4` | 4, `C6` : *"une ligne de EVENEMENTS si elle l'admet"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 2 : refusee, aucune ligne n'entre] |
| `X5` | 2.1, `VOISINS_ADMIS` *"en triplets (reference, sens, bonne reponse)"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 3 : la table porte aussi le distracteur d'enfance, et `ADULTE` le second] |
| `X6` | 1.3, `G10-4`, colonne *Forme* : *"A : aucune forme du paysage de la bonne reponse"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 6 : aucune forme des quatre paysages] |
| `X7` | 1.4 : *"L'explication vit donc dans un item ou le littoral n'est pas une proposition (A sans lui, ou B sur le littoral)"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 6 : dans les items B *sur le littoral* seulement] |
| `X8` | 2.6, premier point : *"Non demandee dans G12-* ; je ne l'ajoute pas sans elle (A8)"* | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 7.2 : demandee, encodee dans `G12-6`] |
| `X9` | 2.6, second point : *"R12-5, distracteur du meme cote : lisible sur les rangs. Non demande."*, pour les evenements | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 3 : pour les evenements, la table de `G12-4` remplace la regle du meme cote ; pour les generations, le passage vaut toujours] |
| `X10` | 5, `-12` : *"un indice qui donne la reponse (A8)"* parmi ce que `nadia-prof` lit | [corrige le 2026-10-27 par `theo-archi`, ce fichier, section 7.2 : la forme litterale est gardee ; la periphrase reste lue] |

## 9. Mesure sur le materiau reel

- **Perimetre** : tout fichier `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `db9c8aaa`. 190 fichiers lus, 189 avec une cle `items`, 3 257 items, 15 889 champs lus (enonce, indice, bonne reponse, distracteurs, quand ils existent), 3 257 indices. Exclus : les jeux fautifs de `src/test/resources`. Les comptes du tableau sont des **items**, un item comptant une fois quel que soit le nombre de ses champs touches.
- **Methode** : programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources ; **JDK 21.0.10, propriete `java.version` imprimee par le programme** ; classpath `snakeyaml` 2.6 du depot local Maven, version lue par les avis de `-01` et `-02`, non relue ici. Chaque fichier est lu par `new Yaml().load`, comme `PaquetLu`. Les motifs et les tables sont ceux de ce fichier et de mon avis du 26. **Ce n'est pas le test** : la preuve dans le depot sera l'execution de chaque classe sur son jeu fautif commite.

| Motif ajuste | Items touches, tout le corpus | Dont `ce1-qlm-*` | Lecture |
|---|---|---|---|
| `G12-3`, suites de trois (`T1`) | 58 suites ; 57 inverses, 1 paire de voisins | 4 (`-01`) | section 1.4 ; aucun fichier lu par `-12` |
| `G10-4` A, une forme des quatre paysages dans l'indice (`T8`) | 29 | 0 | hors de la portee du test de `-10` |
| *littoral*, tout champ | 0 | 0 | aucun paquet ne l'ecrit encore |
| *par exemple*, indice | 1 | 0 | - |
| `G12-1`, tout champ (`T5`) | 57 | 0 | formes : *autrefois* 40, *goûter* 7, *réveil* 4, *lever* 4, sept autres 2 a 3, *école de l'après-midi* 0 |
| `A8`, une forme de generation ou d'evenement dans un indice | 38 | 0 | - |
| `A8`, ecart entre borne par lettre et borne par lettre ou tiret, tout champ | 0 | 0 | le tiret n'est justifie que par ma sonde (7.2) |
| Proposition egale a une generation ou a un evenement | 7 | 0 | hors de la portee du test de `-12` |
| Proposition egale a un mot de parente (`T3`) | 0 | 0 | - |
| *naissance d'un enfant*, tout champ (`T6`) | 0 | 0 | - |
| `G10-5`, dans un indice | 28 | 0 | section 4, raison 1 |
| `G10-5`, dans un enonce ou une proposition | 91 | 0 | - |
| Proposition qui contient un element de `-10` sans lui etre egale, trois mots au plus | 29 propositions | 0 | *à la plage*, *dans le champ*, *des fermes isolées*, *des immeubles collectifs*... : refusees par l'egalite de `G10-1` |

**Ce que ces chiffres prouvent** : aucun motif ajuste ne mord sur un paquet `ce1-qlm-*` deja ecrit, sauf la forme de `G12-3` sur les quatre suites de `-01`, qu'aucune garde de `-12` ne lit. **Ce qu'ils ne prouvent pas** : que les gardes mordent sur `-10` et `-12`, qui n'existent pas.

**Sondes** : 50 verdicts, chacun celui attendu, sur des sondes de ma main ou de `nadia-prof` appelees sur la forme retenue, sur l'ancienne forme ou sur un mutant (`T1` 8, `T2`/`T3`/`T6` 8, `T7` 13, `T8` 11, `A8` 10) ; les quatre mutants de 1.5 sont comptes a part, chacun tue par au moins une sonde. Ces sondes valent moins que le materiau reel ; les deux preuves reelles de ce fichier sont la paire *"graine, plante, fleur"* de `-01` (`T1`) et les propositions adjectivees du voisin de geographie (`T4`).

## 10. Ce qui reste lu par `nadia-prof`, et ce qui est a trancher

**Lu par elle, ajoute a son arbitrage du 27 (section 3) et a mon avis du 26 (section 5)** : qu'une explication de *littoral* bien formee ne porte pas aussi une definition (6.2) ; la periphrase par la parente ou par un mot hors liste dans un indice de `-12` (7.2) ; qu'une ecriture d'un moment de la journee hors des formes de `G12-1` (*l'après-midi*) n'entre pas (5) ; que les trois prenoms d'une suite soient de trois generations differentes, la garde ne verifiant que leur distinction (1.1).

**Ce qu'elle ne lit plus seule** : un indice de `-12` qui ecrit la bonne reponse ou le premier element de la suite ; un indice de A de `-10` qui nomme un paysage ; la presence et la forme *par exemple* de l'explication de *littoral*.

| Code | Point a trancher par `nadia-prof` | Ce que je fais en attendant |
|---|---|---|
| `P1` | *"Aucun adjectif"* (`R10-5` corrige) vise l'enonce et les propositions. L'indice B *sur le littoral* nomme desormais un element : un adjectif qui n'est pas un jugement (*"par exemple la grande mer"*) y passe. L'interdit s'etend-il a l'indice ? | non garde ; si oui, la regle (iii) de 6.1 le tient deja pour l'exemple, et le reste de l'indice demanderait une liste |
| `P2` | Dans un item A ou *le littoral* est un distracteur, un indice qui nomme *la mer* ou *la plage* l'explique sans le nommer, ce que `R10-4` interdit. Encodable : en A, aucun propre du littoral dans l'indice quand *le littoral* est propose | non garde, je ne l'ajoute pas sans elle |
| `P3` | *Littoral* refuse dans l'indice d'un item B dont le lieu n'est pas le littoral, tire de *"dans eux seuls"* (6.1) | garde ; retiree si elle la refuse |

**`F1`, constat sur `-01`, pour elle seule** : les quatre suites de `ce1-qlm-etapes-de-developpement` sont designees par *"la proposition la plus ressemblante aux deux autres"* (score 2 ; 1 ; 1), trois par la paire *echange + inverse*, une par les deux echanges de voisins (1.4). `-01` a ses propres regles, et je ne juge pas si la sienne doit y changer.

## 11. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A9` | Formes des sections 1 a 7 dans `PaquetCe1QlmPaysagesTest` et `PaquetCe1QlmGenerationsTest`, avec leurs sondes et le refus `G10-7` construit sur le paquet reel, dans le commit du premier jet ; remplacent les passages `X1` a `X10` | `marc-dev` |
| `A10` | `P1` a `P3`, et `F1` | `nadia-prof`, par `sofia-sm` |

**Verifications avant commit**, ce fichier present dans l'arbre : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS` ; `bash .github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* Aucun `mvn verify` lance, aucun autre job de la CI.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-27.
