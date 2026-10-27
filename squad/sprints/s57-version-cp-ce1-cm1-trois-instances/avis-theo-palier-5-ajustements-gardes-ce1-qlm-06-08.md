# s57 - palier 5 : avis de theo-archi sur les ajustements des gardes de S57-BR-QLM-CE1-06 et -08 (T1 a T5)

- **Auteur** : `theo-archi`. Date : 2026-10-27 08:56 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-a0608`, base `round-two` a `db9c8aaa` (`git rev-parse`). Ni `ce1-qlm-aimant` ni `ce1-qlm-dangers-electricite` n'existent a cette base.
- **Repond a** : `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 5 (`T1` a `T5`), section 4 (regles corrigees : `-06` critere 10, `-08` critere 9), `P1`, `P3`, `P9`, `P11`.
- **Complete, sans le reecrire** : `avis-theo-palier-5-gardes-ce1-qlm-06-08.md` (dit ci-dessous "l'avis du 2026-10-26"). Ce qui n'est pas nomme ici y reste valable. Chaque passage que j'abandonne est cite en section 4 avec son marqueur.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition, ne prononce aucun Done et ne tranche rien de pedagogique. Les reperes `g1`... reprennent ceux de l'avis du 2026-10-26 ; `g11` (`-06`) et `g9` (`-08`) sont nouveaux ; `Q8`... sont locaux a ce fichier et suivent les `Q1` a `Q6` deja tranches.
- **Pas d'`ADR`, pas de regle ArchUnit** : l'objet garde est du texte YAML lu par le test de chaque paquet ; aucune dependance entre classes n'est touchee. Meme motif que l'avis du 2026-10-26.

## 0. Verdict

| Code | Ajustement | Forme retenue | Sondes |
|---|---|---|---|
| `T1` | `-06`, premisse vraie | **garde neuve `g11`** : un enonce qui commence par `PREMISSE` est refuse si un objet d'une proposition (bonne reponse ou distracteur) est `SANS_METAL` dans la liste fermee de seize objets du test | 3 refusees, 1 passe ; 3 mutants tues |
| `T2` | `-08`, table des formes, alerte | table : *"garder l'appareil loin de l'eau"* et la ligne d'alerte retirees, *"ne pas toucher l'appareil"* ajoutee (situation `eau`) ; **garde neuve `g9`** : toute proposition dont `plat` commence par `(prevenir\|appeler\|le dire a) un adulte` est refusee | 5 refusees, 3 passent ; 4 mutants tues |
| `T3` | `-08`, `g3` | sonde remplacee : bonne reponse *"laisser le fil sans le toucher"*, distracteur *"ne pas toucher le fil"* ; seule `g3` la mord | 1 refusee |
| `T4` | `-08`, `g1` et `g2` | les trois formes des mains n'ont plus que la situation `eau` ; *"ne pas brancher la lampe"* exige `lampes?` dans l'enonce ; `g1` refuse un paquet sans item `geste` de situation `eau` dont l'enonce ne porte pas `mouill` | 3 refusees, 2 passent, 1 sonde de paquet |
| `T5` | `-08`, `g7` | motif ajoute : `se (bless...\|fait/font... mal)` conjugue, sauf apres `on` ou `on ne` | 19 sondes conformes ; 2 mutants tues |
| `V1` | Mesure | sondes et motifs executes hors depot, `java.version=21.0.10`, **sur 190 fichiers, 0 refus dans le depot** ; ce que les motifs trouvent dans le contenu des autres lots en section 3 |
| `V2` | `AIM-CE1-2` | **`g10` inchangee, 17 sondes conformes sur 17** |
| `V3` | Desaccord | **aucun refus contraire a ses regles.** Un ecart entre sa table `T5` (*"present conjugue"*) et sa regle 5 (*"a aucun temps"*) : je suis la regle (`Q8`) ; et un mot reel que la lettre de `T5` refuse (`Q9`) |

## 1. `-06` (`ce1-qlm-aimant`)

### 1.1 `T1` : `g11`, premisse vraie

**Forme.** Si `plat(enonce)` commence par `PREMISSE` (`(ces trois objets sont en metal|tous ces objets sont en metal)\.`, l'avis du 2026-10-26, 2.0), chaque objet de chaque proposition, bonne reponse et distracteurs, lu comme en `g1`, doit etre `FER` ou `METAL`. Un objet `SANS_METAL` refuse l'item, son nom dans le message. Les deux classes viennent de la liste fermee du test (2.0 point 2 de l'avis du 2026-10-26), inchangee : `FER` (clou, trombone, epingle, aiguille, agrafe, fil de fer), `METAL` (papier d'aluminium, barquette en aluminium, fil de cuivre), `SANS_METAL` (gomme, bouchon en liege, regle en plastique, bille en verre, feuille de papier, elastique, pate a modeler).

**Garde neuve plutot que dans `g4`** : `g4` compte, `g11` refuse. Les mettre ensemble ferait d'un refus un effet de bord d'un compte. `g4` reste ecrite telle quelle ; sa clause *"tous les objets sont `FER` ou `METAL`"* devient redondante pour tout item qui passe `g11`, et je la laisse pour que `g4` ne depende pas de l'ordre des gardes.

En tache B, `FORME_B` porte toujours la premisse et `g7` impose deja trois `FER` au bon groupe et deux `FER` plus un `METAL` a chaque distracteur : `g11` n'y refuse rien que `g7` ne refuse deja. Une seule fonction pour A et B, comme `g4`.

| Sonde | Entree | Attendu | Obtenu |
|---|---|---|---|
| `T1-r1` (`nadia-prof`) | *"Ces trois objets sont en métal. Lequel l'aimant attire-t-il ?"*, *le clou* ; *la gomme*, *le fil de cuivre* | refusee | `g11` seule |
| `T1-r2` (de moi) | *"Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"*, *la gomme* ; *le clou*, *l'agrafe* (`U+2019`) | refusee | `g11` seule |
| `T1-r3` (de moi) | *"Tous ces objets sont en métal. Lequel de ces objets l'aimant attire-t-il ?"*, *le trombone* ; *la bille en verre*, *le papier d'aluminium* | refusee | `g11` seule |
| `T1-p` (`nadia-prof`) | *"Ces trois objets sont en métal. Lequel l'aimant attire-t-il ?"*, *le clou* ; *le papier d'aluminium*, *le fil de cuivre* | passe | passe ; compte au quart (`g4`) |

| Mutant de `g11` | Tue par |
|---|---|
| lit la bonne reponse seule | `T1-r1`, `T1-r3` |
| lit les distracteurs seuls | `T1-r2` seule : la sonde de `nadia-prof` ne le tue pas |
| ne reconnait que *"Ces trois objets sont en métal."* | `T1-r3` seule |

`g3` ne mord aucune des trois refusees (une seule proposition `FER` en *attire*, une seule hors `FER` en *n'attire pas*) : `g11` est la seule garde qui les voit, ce que la mesure confirme.

### 1.2 `P1` : ce que `g9` devient

**`g9` est inchangee, et sa forme est desormais la lettre de la regle 3.** La regle reformulee dit *"aucun mot de leur nom que la bonne reponse n'a pas"* : c'est exactement l'intersection des mots des deux noms de distracteurs, moins les mots de la bonne reponse, que `g9` calcule. Le cas *n'attire pas* admis ne demande rien de plus : sur les 150 items possibles de cette polarite (15 paires d'objets `FER` en distracteurs, fois 10 bonnes reponses hors `FER`), `g9` en refuse **0**, mesure ; aucun nom `FER` ne partage un mot avec un autre (*fil de fer* est le seul a porter *fil* ou *fer*). La sonde de `nadia-prof` *la gomme* parmi *l'aiguille* et *l'épingle* passe `g9`, comme attendu : forme et usage restent a sa lecture.

### 1.3 Autres reponses de `nadia-prof` sur `-06`

- **Regle 2, tache B (`P11`)** : rien de neuf. `g1` refuse deja toute autre forme que la liste a virgules de trois noms sans article (*"le clou, le trombone et l'aiguille"* : deux segments, aucun n'est une cle). Six mots au plus : test transverse, `PaquetLu.mots`. Mesure de composition pour `marc-dev` (`A3` de l'avis du 2026-10-26), sur les ensembles possibles : les 20 bons groupes (trois `FER`) font tous 6 mots au plus ; 10 des 45 distracteurs (deux `FER` et un `METAL`) en font 7 et sont refuses, exactement ceux qui joignent *fil de fer* a *barquette en aluminium* ou a *fil de cuivre*.
- ***Doré*** : non garde, `g2` inchangee, a sa lecture.
- ***Pièce*, *couvert*, *or* dans tout sens (`P9`)** : `g2` inchangee ; ses quatorze mots de la regle 5 corrigee sont tous dans le motif de `g2` (plus *acier* et *clef*, deja ecrits).

## 2. `-08` (`ce1-qlm-dangers-electricite`)

### 2.1 La table des formes, apres `T2` et `T4`

Remplace le tableau de 3.0 point 3 de l'avis du 2026-10-26.

| Forme (`plat`, point final retire) | Situation | Exige dans l'enonce |
|---|---|---|
| `ne pas toucher le fil`, `laisser le fil sans le toucher` | fil | - |
| `ne pas toucher la prise`, `ne rien mettre dans la prise` | prise | - |
| `ne pas toucher l'appareil`, `ne pas l'approcher de l'eau` | eau | - |
| `attendre d'avoir les mains seches`, `se secher d'abord les mains` | eau | `(?<!\p{L})mouill\p{L}*` |
| `ne pas brancher la lampe` | eau | `mouill\p{L}*` **et** `(?<!\p{L})lampes?(?!\p{L})` |

Neuf formes. Le mot repere de la situation dans l'enonce (`g1`, 3.0 point 2 de l'avis du 2026-10-26) est inchange : `mouille\p{L}*` y est deja un repere de `eau`.

### 2.2 `T2` : `g9`, aucune alerte a un adulte

**Les trois formes, exactement** : *"prévenir un adulte"*, *"appeler un adulte"*, *"le dire à un adulte"*. **Forme** : sur chaque proposition, bonne reponse et distracteurs, refus si `^(prevenir|appeler|le dire a) un adulte(?!\p{L})` trouve dans `plat(proposition)`. Ancree au debut et non par egalite : elle refuse aussi ces trois formes suivies d'un complement (*"prévenir un adulte tout de suite"*, bonne reponse de `cp-emc-alerte`). Ancree au debut, elle laisse passer l'alerte niee, qui commence par *ne* : *"ne rien dire à un adulte"*, *"ne pas prévenir un adulte"*, *"ne pas déranger un adulte"*. Elle ne lit ni l'enonce ni l'indice : l'adulte reste admis dans une explication, et l'indice qui le nomme reste a la lecture de `nadia-prof` (regle 4, non demandee).

**Ce que `g9` ne couvre pas**, et qui reste a `nadia-prof` : un autre verbe (*"aller voir un adulte"*, *"avertir un adulte"*, *"demander de l'aide à un adulte"*, *"en parler à un adulte"*) ; un autre nom (*un grand*, *la maîtresse*, *ses parents*) ; l'alerte qui ne commence pas la proposition (*"refuser et le dire à un adulte"*, *"arrêter et prévenir un adulte"*) ; la forme conjuguee (*"il prévient un adulte"*). Mesure sur `cp-emc-alerte` : `g9` mord **4** de ses 8 bonnes reponses d'alerte (*"prévenir un adulte"* trois fois, *"prévenir un adulte tout de suite"*) ; les 4 autres sont de cette liste.

**Encodable, non propose** : refuser toute proposition qui porte `adulte` sans commencer par *ne*. Mesure : 40 propositions du corpus, dont des distracteurs qui ne sont pas des alertes (*"croire qu'un adulte a déjà vu cela"*, `cp-emc-exclusion`, refuse une precaution : c'est le distracteur negatif qu'elle admet, ecrit sans *ne*). Elle mordrait par inclusion.

| Sonde | Item (tache, situation, enonce ; bonne reponse ; distracteurs) | Attendu | Obtenu |
|---|---|---|---|
| `T2-r1` | geste, eau, *"Le sèche-cheveux est branché au bord du lavabo. Que doit faire Lina ?"* ; *"garder l'appareil loin de l'eau"* ; *"le débrancher en tirant sur le fil"*, *"ne rien dire à un adulte"* | refusee | `g2` |
| `T2-r2` | geste, fil, *"Tom voit un fil électrique abîmé. Que doit-il faire ?"* ; *"Prévenir un adulte."* ; *"réparer le fil avec du scotch"*, *"toucher le fil pour voir"* | refusee | `g2`, `g9` (une alerte n'est plus une forme) |
| `T2-r3` | geste, fil, meme enonce ; *"ne pas toucher le fil"* ; *"appeler un adulte"*, *"ne rien dire à un adulte"* | refusee | `g9` seule |
| `T2-r4` | geste, prise, *"La prise du salon est cassée. Que doit faire Sami ?"* ; *"ne pas toucher la prise"* ; *"Le dire à un adulte."*, *"ne pas déranger un adulte"* | refusee | `g9` seule |
| `T2-r5` (de moi) | geste, fil ; *"laisser le fil sans le toucher"* ; *"prévenir un adulte tout de suite"*, *"réparer le fil avec du scotch"* | refusee | `g9` seule |
| `T2-p1` | geste, eau, enonce du lavabo ; *"ne pas toucher l'appareil"* (`U+2019`) ; *"le débrancher en tirant sur le fil"*, *"ne rien dire à un adulte"* | passe | passe |
| `T2-p2` | geste, prise ; *"ne pas toucher la prise"* ; *"ne rien dire à un adulte"*, *"toucher la prise pour voir"* | passe | passe |
| `T2-p3` (de moi) | geste, prise ; *"ne pas toucher la prise"* ; *"ne pas prévenir un adulte"*, *"toucher la prise pour voir"* | passe | passe |

Mutants de `g9` executes sur ces sondes : *bonne reponse seule* tue par `T2-r3`, `T2-r4`, `T2-r5` ; *distracteurs seuls* par `T2-r2` seule, a condition que le test asserte le message de `g9` et non le seul refus, `g2` mordant aussi ; *egalite* par `T2-r5` seule ; *non ancre* (l'alerte cherchee partout dans la proposition) par `T2-p3` seule.

### 2.3 `T3` : la sonde de `g3`

Item : geste, fil, *"Tom voit un fil électrique abîmé. Que doit-il faire ?"* ; bonne reponse *"laisser le fil sans le toucher"* ; distracteurs *"ne pas toucher le fil"*, *"réparer le fil avec du scotch"*. **Obtenu : `g3` seule.** La bonne reponse ne commence pas par *ne* : `g4` ne mord pas, ce qui ne serait pas vrai si la bonne reponse etait *"ne pas toucher le fil"* et qu'aucun distracteur ne commencait par *ne*. L'ancienne sonde (*"ne pas toucher le fil"* ; *"prévenir un adulte"*, *"réparer le fil avec du scotch"*) est mordue par `g9` et `g4`, plus par `g3`.

### 2.4 `T4` : `g1` et `g2`

- **`g2`**, par la table de 2.1 : une forme des mains sous une situation declaree `prise` ou `fil` n'est pas dans ses situations ; *"ne pas brancher la lampe"* exige `lampes?` dans l'enonce.
- **`g1`, partie paquet** : en plus de `FER-1` et `FER-3` (inchanges), au moins un item de tache `geste`, situation `eau`, dont `plat(enonce)` ne porte pas `(?<!\p{L})mouill\p{L}*`. C'est la troisieme phrase ajoutee a la regle 1, tache B.

| Sonde | Item | Attendu | Obtenu |
|---|---|---|---|
| `T4-r1` | geste, prise, *"Inès a les mains mouillées et veut brancher le chargeur dans la prise. Que doit-elle faire ?"* ; *"se sécher d'abord les mains"* ; *"brancher le chargeur tout de suite"*, *"ne rien dire à un adulte"* | refusee | `g2` |
| `T4-r2` | geste, fil, *"Lina a les mains mouillées et voit un fil abîmé. Que doit-elle faire ?"* ; *"attendre d'avoir les mains sèches"* ; *"réparer le fil avec du scotch"*, *"ne rien dire à un adulte"* | refusee | `g2` |
| `T4-r3` | geste, eau, *"Inès a les mains mouillées et veut brancher le grille-pain. Que doit-elle faire ?"* ; *"ne pas brancher la lampe"* ; *"brancher le grille-pain tout de suite"*, *"ne rien dire à un adulte"* | refusee | `g2` |
| `T4-p` (`nadia-prof`) | geste, eau, *"Inès a les mains mouillées et veut brancher la lampe. Que doit-elle faire ?"* ; *"se sécher d'abord les mains"* ; *"brancher la lampe tout de suite"*, *"ne rien dire à un adulte"* | passe | passe |
| `T4-p2` | meme enonce ; *"ne pas brancher la lampe"* | passe | passe |
| `T4-paquet` | le paquet reel prive de ses items `geste` `eau` dont l'enonce ne porte pas `mouill` | refuse | sur un paquet synthetique de sept items (le reel n'existe pas) : refuse par cette clause seule, `FER-1` tenu par l'item des mains |

`T4-r1` et `T4-r2` portent deux situations dans un meme enonce (mains mouillees et prise, ou fil) : la regle 1 les refuse aussi a ce titre, mais aucune garde ne le lit (`Q10`).

La sonde de `g1` du 2026-10-26 *"un item `danger` de bonne reponse *prévenir un adulte*"* ne mord plus `g1` (une alerte n'est plus une forme) ; elle est remplacee par un item `danger`, situation `prise`, de bonne reponse *"ne pas toucher la prise"* : **obtenu, `g1` seule**.

### 2.5 `T5` : `g7`, se blesser et se faire mal

**Motif ajoute**, sur `plat` :

```
(?<!(?<!\p{L})on )(?<!(?<!\p{L})on ne )(?<!\p{L})se (bless(e|es|ent|ait|aient|a|erent|era|erait|eront|eraient)|(fait|font|faisait|faisaient|fit|firent|fera|ferait|feront|feraient) mal)(?!\p{L})
```

L'infinitif (*se blesser*, *se faire mal*) n'est jamais lu. *On ne* est exempte comme *on* : *"On ne se blesse pas si on ne touche pas le fil."* est le present generique nie (de moi, `Q8`). `l'on` passe, l'apostrophe n'etant pas une lettre. Le passe compose reste aux deux motifs de 3.1 de l'avis du 2026-10-26 (*blessé* accentue, *s'est fait mal*), inchanges.

| Sondes | Attendu | Obtenu |
|---|---|---|
| *"Lina se blesse avec le fil."*, *"Sami se fait mal."* (`nadia-prof`) ; *"Lina se blessera."*, *"Sami se faisait mal."*, *"Tom et Lina se blessent."* (de moi) | refusees | refusees |
| *"On se blesse avec un fil abîmé."*, *"On peut se faire mal."*, *"pour ne pas se blesser"*, *"pour ne pas se faire mal"* (`nadia-prof`) ; *"On ne se blesse pas si on ne touche pas le fil."*, *"Si l'on se blesse, on le dit."* (de moi) | passent | passent |
| Les dix sondes de `g7` du 2026-10-26 | inchangees | conformes |

Mutants : *present seul* est tue par *"Lina se blessera."* et *"Sami se faisait mal."* seules ; *sans exception de `on`* est tue par les trois sondes en *on*.

### 2.6 `P3` : comment `g4` distingue abstention et distracteur negatif

**Elle ne les distingue pas.** `g4` est une garde de grammaire : elle refuse une bonne reponse en *ne pas*, *ne rien*, *ne jamais* quand aucun distracteur ne commence ainsi. Un distracteur negatif admis (*"ne rien dire à un adulte"*) et une abstention negative (*"ne rien toucher"*, *"ne pas s'approcher"*) la satisfont tous les deux ; l'abstention affirmative (*"s'éloigner"*, *"attendre"*) lui est indifferente. Le critere de `nadia-prof` (*"un enfant qui ferait cela serait-il hors de danger ?"*) porte sur le sens, que `g4` ne lit pas.

**Ce que `g4` pousse a ecrire, dit** : pour la satisfaire, un auteur ajoute un distracteur en *ne* ; l'abstention en *ne* est le plus court, et c'est une seconde reponse. Deux gardes lexicales rendraient ce raccourci visible, encodables, non ecrites sans son accord : `Q11` et `Q12`.

## 3. Mesure

**Methode.** Deux fichiers Java hors du depot et non commites (le programme de mesure, et un second pour les mutants de `g9`), lances sur le JDK que le programme rapporte : `java.version=21.0.10`, `java.vm.version=21.0.10+8-LTS-217`. Classpath : SnakeYAML 2.6 du depot local Maven, version que je n'ai pas relue par `dependency:build-classpath`. Il lit chaque fichier par `new Yaml().load`, recopie `PaquetLu.sansAccent` et `PaquetLu.mots`, et applique les formes de ce fichier telles qu'ecrites, les gardes inchangees des deux lots qui decident du verdict d'une sonde (`-06` `g1`, `g2`, `g3`, `g6`, `g7`, `g9`, `g10`, `g11` et le compte de `g4` ; `-08` `g1` a `g4`, `g6` a `g9`) et les trois motifs de l'exemption d'`AIM-CE1-2`. **Ce n'est pas le test** : la preuve dans le depot sera l'execution des deux classes sur leur jeu fautif commite.

**Sondes.** Toutes celles des sections 1 et 2 rendent le verdict attendu, chacune avec l'ensemble de gardes qui la mord tel que les tableaux le donnent. **`g10` (`AIM-CE1-2`) : 17 sondes sur 17 conformes** (refusees 1, 2, 3, 10, 11, `T-a`, `T-b`, `T-c` ; passent 4 a 9, 12 et les deux de `nadia-prof`). Ses trois motifs, ses champs et ses sondes sont inchanges : aucun ajustement ne touche un enonce ou un indice de `-06` que `g10` lit.

**Perimetre du corpus.** Tout `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `db9c8aaa` : 190 fichiers, 3 182 items portant `enonce` et `bonneReponse`, 15 751 champs (`enonce`, `indice`, `explication`, `bonneReponse`, chaque distracteur), 9 375 propositions. Exclus : les jeux fautifs de `src/test/resources`. Les motifs y sont appliques quel que soit le lot, pour leur precision sur une langue ecrite par d'autres. Le compte de champs differe de celui de l'avis du 2026-10-26 (15 901) a meme nombre d'items ; je n'ai pas cherche la cause et je ne compare pas les deux.

**Aucun refus dans le depot** : chaque garde ne lit que son paquet, aucun des deux n'existe, aucun test existant ne change d'etat. Ce que les motifs ajustes trouvent ailleurs :

| Motif | Trouve | Lecture |
|---|---|---|
| `g9` alerte (propositions) | 4, toutes dans `cp-emc-alerte` | 4 alertes, 0 a tort |
| table de 2.1, egalite (propositions) | 0 | les 3 egalites du 2026-10-26 etaient *"prévenir un adulte"*, sortie de la table |
| `T5` (champs) | 3 | 0 personnage nomme : *"Un élève se blesse pendant la récréation..."* (`cm1-emc-institutions`), *"pour que personne ne se blesse"* (`cp-emc-regles-de-vie`), *"Des muscles froids se blessent..."* (`cp-eps-corps-effort`) |
| `T1`, `FORME_A` / `FORME_B` (enonces) | 0 | `g11` ne lit que les enonces de ces formes |
| `mouill` (champs) | 9, aucun d'electricite | `T4` ne lit que `-08` |

**Ce que la mesure dit de `T5`** : la lettre de `nadia-prof` (*"sauf apres on"*) est plus large que sa regle (*"un personnage nomme"*). Sur le materiau reel, le motif ne trouve aucun personnage nomme et trois autres sujets. *Un élève se blesse* met en scene un accident et je le crois refuse a raison. *Pour que personne ne se blesse* est une consequence generique, du meme ordre que *pour ne pas se blesser* qu'elle admet : la lettre la refuse (`Q9`).

## 4. Ce que j'abandonne de l'avis du 2026-10-26

1. 0, `V2` : *"huit sur huit pour `-08`"*, et *"Dix gardes sur dix pour `-06`"*. **[corrige le 2026-10-27, `theo-archi`, ce fichier, section 0]** : neuf gardes pour `-08` (`g9`), onze pour `-06` (`g11`).
2. 3.0 point 3, lignes *"`garder l'appareil loin de l'eau`, `ne pas l'approcher de l'eau` - eau"*, *"`attendre d'avoir les mains seches`, `se secher d'abord les mains`, `ne pas brancher la lampe` - toute situation, si l'enonce porte `mains mouillees`"* et *"`prevenir un adulte`, `appeler un adulte`, `le dire a un adulte` - prise, eau, fil"*. **[corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.1]** : table remplacee ; le repere des mains devient `mouill\p{L}*` au lieu de la suite `mains mouillees`, pour lire *"mains toutes mouillées"* comme le repere de situation le lit deja.
3. 3.1, `g1`, contre-exemple *"un item `danger` de bonne reponse *prévenir un adulte*"*. **[corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.4]** : remplace par la bonne reponse *"ne pas toucher la prise"*.
4. 3.1, `g2`, *"chacune des douze formes ; *Prévenir un adulte.* (casse, point)"* en doit passer. **[corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.1]** : chacune des neuf formes ; la sonde de casse devient *"Ne pas toucher la prise."* (mesure : passe).
5. 3.1, `g3`, contre-exemple *"ne pas toucher le fil ; prévenir un adulte, réparer le fil avec du scotch"*. **[corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.3]**.
6. 5.2, `Q4`, *"toute situation acceptee si son mot repere est dans l'enonce"*. **[corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.1]** : situation `eau` seule, par `P8`.
7. 4, *"3 une forme de geste (*prévenir un adulte*, trois bonnes reponses de `cp-emc-alerte`)"* : mesure exacte pour l'ancienne table, sans objet pour la nouvelle (0, section 3). Non abandonnee, rendue caduque par `T2`.

## 5. Ce qui reste a `nadia-prof`, et ce que je lui soumets

**Reste a sa lecture, aucune garde** (en plus de 5.3 de l'avis du 2026-10-26, inchange) : forme, taille et usage partages par deux distracteurs de A a `-06`, dans les deux polarites ; *doré* ; une alerte a un adulte hors des trois formes de `g9` (liste en 2.2) ; qu'un distracteur de `-08` laisse l'enfant hors du danger, au-dela de `g9` ; qu'un enonce `eau` dise *deja branche* sous *"ne pas toucher l'appareil"* et *veut poser* sous *"ne pas l'approcher de l'eau"* ; que l'enonce nomme l'appareil que *"ne pas toucher l'appareil"* designe ; un personnage nomme blesse par une periphrase ; une seule situation par enonce (`Q10` si elle n'en veut pas).

| Code | Point | Ce que la forme fait aujourd'hui |
|---|---|---|
| `Q8` | Sa table `T5` dit *"present conjugue"*, sa regle 5 corrigee *"a aucun temps"*. J'encode les temps simples de la troisieme personne (imparfait, passe simple, futur, conditionnel en plus du present) et j'exempte *on ne* comme *on*. Deux sondes de futur et d'imparfait et deux sondes en *on* sont de moi | refuse *"Lina se blessera."* ; laisse passer *"On ne se blesse pas..."* |
| `Q9` | La lettre *"sauf apres on"* refuse *"pour que personne ne se blesse"*, trouve tel quel dans `cp-emc-regles-de-vie`. L'exempter (`personne ne` comme `on ne`) ou le laisser refuse, l'auteur ecrivant *"pour ne pas se blesser"* | refuse |
| `Q10` | La regle 1 corrigee dit *"Un enonce porte une seule situation"*. Encodable : refuser un enonce `geste` qui porte les reperes de deux situations (`T4-r1`, `T4-r2` en portent deux). Le veut-elle en garde ? | non garde |
| `Q11` | Ses cinq abstentions nommees (*s'éloigner*, *attendre*, *ne rien toucher*, *ne pas s'approcher*, *continuer à jouer ailleurs*) refusees en distracteur, ancrees au debut de la proposition. Une abstention ecrite autrement resterait a elle | non garde |
| `Q12` | *"Il ne nie jamais la bonne reponse du meme item ni une autre forme de la meme precaution"* : refuser un distracteur egal a *ne pas* suivi d'une forme de la table (*"ne pas attendre d'avoir les mains sèches"*) | non garde |

Aucun desaccord avec ses regles : `Q8` et `Q9` sont deux lectures de son texte que la mesure separe, et je suis sa regle la ou sa table et sa regle different.

## 6. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A6` | `g11` (1.1) et ses quatre sondes dans `PaquetCe1QlmAimantTest`, dans le commit du premier jet, avec `A1` de l'avis du 2026-10-26 | `marc-dev` |
| `A7` | Table de 2.1, `g9` (2.2), sondes de 2.2 a 2.5, clause `mouill` de `g1`, motif `T5` de `g7`, dans `PaquetCe1QlmDangersElectriciteTest`, avec `A2` de l'avis du 2026-10-26 | `marc-dev` |
| `A8` | `Q8` a `Q12` | `nadia-prof`, par `sofia-sm` |
| `A9` | Dans `S57-BR-QLM-CE1-06` et `-08`, renvoyer `T1` a `T5` a ce fichier | `camille-po` |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-27.
