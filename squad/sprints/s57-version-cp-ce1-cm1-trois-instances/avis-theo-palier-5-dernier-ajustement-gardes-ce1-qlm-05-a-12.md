# s57 - palier 5 : avis de theo-archi sur le dernier ajustement des gardes de `-04`, `-05`, `-06`, `-08`, `-10` (G1 a G13)

- **Auteur** : `theo-archi`. Date : 2026-10-27 ; debut non lu par `date`, je ne l'ecris pas ; fin en section 9.
- **Arbre** : worktree `s57-p5-theo-dernier`, base `round-two` a `97c67bb2` (`git rev-parse`). Aucun des paquets `-04`, `-05`, `-06`, `-08`, `-10`, `-12` n'existe a cette base ; trois paquets `ce1-qlm-*` existent (`-01`, `-02`, `-03`).
- **Repond a** : `arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md` (dit *"son arbitrage"*), section 7 (`G1` a `G13`), avec sa table de reponses (section 0) et les textes de regles de sa section 6.
- **Lu** : son arbitrage en entier ; mes trois avis d'ajustement du 2026-10-27 (`avis-theo-palier-5-ajustements-gardes-ce1-qlm-04-05.md`, `-06-08.md`, `-10-12.md`, dits *"mon avis 0405"*, *"0608"*, *"1012"*) en entier ; dans mes avis du 2026-10-26, les formes que ces ajustements touchent (`avis-theo-palier-5-gardes-ce1-qlm-06-08.md` sections 1 a 4, `avis-theo-palier-5-gardes-ce1-qlm-10-12.md` section 1, `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md` 2.2 et 3.1) ; `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md` 2.2 et 5.1 ; `PaquetLu.mots` et `PaquetLu.sansAccent` ; l'annotation neuvieme de `docs/backlog/backlog.md` (`Q9-5`) ; `squad/lessons.md`.
- **Ce que ce fichier ne fait pas** : il ne reecrit aucun de mes avis, il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition (`G1`... sont les reperes de `nadia-prof`, `M1`..., `P1`... ceux de ce fichier) ; il ne tranche rien de pedagogique et ne prononce aucun Done. **Aucune regle ArchUnit, aucun `ADR`** : ce sont des gardes de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite), comme mes six avis precedents ; aucune ne porte sur une dependance entre classes.
- **Notations** : `plat`, egalite (point final retire), bornes `(?<!\p{L})` et `(?!\p{L})` sont ceux de 1.1 de mon avis `-06`/`-08` du 2026-10-26. La table des neuf formes de `-08` est celle de 2.1 de mon avis 0608 ; ses trois gestes au sens de 6.4 de son arbitrage sont notes `TOUCHER` (cinq formes), `APPROCHER` (*ne pas l'approcher de l'eau*) et `MAINS` (trois formes).

## 0. Verdict, une ligne par ajustement

| Repere | Forme retenue | Sonde refusee | Doit passer |
|---|---|---|---|
| `G1` | `-08` `g1`, partie paquet : chaque geste (`TOUCHER`, `APPROCHER`, `MAINS`) est le geste de la bonne reponse d'au moins un item `geste` ; *B au moins 4* est tenu par cette clause et celle des situations, sans assertion propre | paquet sans item `APPROCHER` | quatre items `geste` : `fil`, `prise`, `APPROCHER`, `MAINS` |
| `G2` | `-08`, garde neuve : refus d'un item `geste` dont `plat(enonce)` porte les reperes d'au moins deux situations | *"Le sèche-cheveux est branché à la prise près du lavabo. Que doit faire Lina ?"* | *"... branché au bord du lavabo. ..."* |
| `G3` | `-08`, garde neuve : refus d'un distracteur dont `plat` verifie `^(s'eloigner\|attendre\|ne rien toucher\|ne pas s'approcher\|continuer a jouer ailleurs)(?!\p{L})` | distracteur *"ne rien toucher"* | *"ne rien dire à personne"*, *"ne pas en parler"* |
| `G4` | `-08`, garde neuve : refus d'un distracteur dont l'egalite verifie `^ne (pas\|jamais) (.+)$` avec le groupe 2 egal a une des neuf formes, dans tout item | *"ne pas attendre d'avoir les mains sèches"* | *"ne pas en parler"* |
| `G5` | `-08` `g7` : `(?<!(?<!\p{L})personne ne )` ajoute aux deux exceptions de `on` | *"Un élève se blesse pendant la récréation."* | *"pour que personne ne se blesse"* |
| `G6` | `-05` `F3` : `SOUS_ZERO` = `(?<!\p{L})(?:sous\|en dessous de\|au-dessous de) (?:0\|zero) degres?(?!\p{L})` ; `EAU` inchange | *"... il fait au-dessus de 0 degré ..."* | *"... il fait au-dessous de 0 degré ..."* |
| `G7` | `-05`, regle 2 de B, **en partie** : dans un item de temperature, une proposition qui porte `GLACE` exige `GLACE` ou `GEL` dans l'enonce. Le cote *eau* : **non garde : `ETE-CE1-2`**, la forme demandee refusant son exemple de la mare (2.2) | *"Un verre d'eau est posé au congélateur. ..."* ; *l'eau gèle* ; *le glaçon fond*, *rien ne change* | ses quatre exemples (2.2 de `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, et celui de la regle 2) |
| `G8` | `F2` inchange. `T04-3e` : **deux alternatives ajoutees**, `feculentes?` et `produits?`, pour que le motif corresponde a sa phrase (3) | *"Dans quelle famille range-t-on la purée féculente ?"* | *"... la pomme ?"*, *"... la pomme de terre ?"* |
| `G9` | `-06` `g4` : clause ajoutee, au moins un item de A (`FORME_A`) verifie `compte` | 9 items A qui ne comptent pas, 3 items B qui comptent | le meme paquet, un item A qui compte |
| `G10` | `-06`, garde neuve : `S` = items de B ou `mots(bonne) < mots(d)` pour les deux distracteurs ; refus si `2 * S > nB` | 2 items sur 3 ou le bon groupe est le seul plus court | 1 sur 3, dont son exemple de 6.3 ; 2 sur 4 |
| `G11` | `-10` `G10-4` A : aucune forme d'un element propre de l'un des trois paysages proposes (au lieu de celui de la bonne reponse seul) | A, *la montagne* ; *le littoral*, *la ville* ; indice *"Ce n'est pas au bord de la mer."* | meme item, indice *"Pense à la forêt."* (element partage) |
| `G12` | `-10` `G10-4` B : inchange, tel qu'en 6.1 de mon avis 1012 | B *à la montagne*, indice *"Pense au littoral."* | B *sur le littoral*, *"Sur le littoral, il y a par exemple la mer."* |
| `G13` | `-10` : rien de plus que la regle (iii) ; l'adjectif hors de l'exemple, **non garde : `PAY-CE1-2`** | *"... par exemple la grande mer."* | *"Sur le grand littoral, il y a par exemple la mer."* passe : c'est le reste non garde |

**Mesure** (section 6) : 104 verdicts de sonde ou de mutant, tous conformes, JDK rapporte `java.version=21.0.10`. Sur le corpus servi (190 fichiers, 3 182 items, 15 751 champs), **aucun motif ajuste ne mord sur un paquet `ce1-qlm-*` existant** ; ailleurs, trois mesures portent un constat (`G3`, `G7`, `G8`).

**Desaccord avec ses regles : aucun.** Trois constats de mesure lui sont soumis en section 8 (`P1` a `P3`) ; aucun ne bloque un premier jet.

## 1. `-08` (`ce1-qlm-dangers-electricite`)

### 1.1 `G1` : chaque geste, et B au moins 4

**Forme**, dans `g1` partie paquet, a cote des clauses de 3.1 de mon avis du 2026-10-26 (situations) et de 2.4 de mon avis 0608 (item `eau` sans `mouill`), toutes inchangees : pour chacun des trois gestes, au moins un item de tache `geste` dont la bonne reponse est, par egalite, une forme de ce geste. Message : *"6.4 : aucun item de B n'a pour bonne reponse le geste %s"*. Le geste d'une forme est une troisieme colonne de la table de 2.1 de mon avis 0608 ; les neuf formes et leurs situations ne changent pas.

**B au moins 4, sans assertion propre.** Les formes de `fil` et de `prise` sont toutes `TOUCHER`, `APPROCHER` et `MAINS` sont toutes deux `eau`. Un B qui tient les trois situations et les trois gestes a donc deux items `TOUCHER` de situations differentes, un `APPROCHER` et un `MAINS` : 4 au moins. Mesure exhaustive : **0 multiensemble de trois formes sur 165** tient a la fois les trois situations et les trois gestes. Une assertion `>= 4` ne pourrait etre la seule a mordre sur aucune sonde : elle serait decorative, je ne l'ecris pas. Le message de la clause cite *"B au moins 4"* pour que le developpeur lise la consequence.

Le programme de la section 6 n'execute que les clauses de B de `g1` ; dans le jeu fautif, chaque sonde porte en plus trois items `danger`, un par situation, pour que `FER-3` et les situations de A ne levent rien.

| Sonde (items `geste` du paquet de sonde) | Attendu | Obtenu |
|---|---|---|
| `geste` : *"ne pas toucher le fil"*, *"ne pas toucher la prise"*, *"ne pas toucher l'appareil"* (enonce du lavabo), *"se sécher d'abord les mains"* | refus, `APPROCHER` seul | refus, ce seul message |
| `geste` : les trois premiers, plus *"ne pas l'approcher de l'eau"* (*"Lina veut poser la radio au bord de l'évier. Que doit-elle faire ?"*) | refus, `MAINS` seul | refus, ce seul message |
| `geste` : *"ne pas toucher le fil"*, *"ne pas toucher la prise"*, *"ne pas l'approcher de l'eau"*, *"se sécher d'abord les mains"* | passe | passe |

Les trois situations sont tenues par chacune des deux sondes refusees : un mutant qui ne lirait que les situations les laisse passer et meurt. **Sonde sur le materiau reel, quand le paquet existera** : le paquet reel prive de ses items `APPROCHER`, puis de ses items `MAINS`, posee seulement si le refus y est unique (le retrait des `APPROCHER` peut aussi lever la clause *eau sans `mouill`*).

### 1.2 `G2` : deux situations dans un enonce

**Forme** : sur un item de tache `geste`, l'ensemble des situations dont le repere de 3.0 point 2 de mon avis du 2026-10-26 est trouve dans `plat(enonce)` (`prises?` ; `fils?` ; `eau|lavabos?|eviers?|mouille\p{L}*`, bornes) a au moins deux elements : refus, les deux situations dans le message. Le compte porte sur les **situations**, pas sur les reperes : *lavabo* et *eau* ensemble sont une seule situation. Les items `danger` ne sont pas lus : sa regle (`Q10`) parle de l'enonce `geste`.

| Sonde | Attendu | Obtenu |
|---|---|---|
| *"Le sèche-cheveux est branché à la prise près du lavabo. Que doit faire Lina ?"* (`nadia-prof`) | refus (`prise`, `eau`) | refus |
| *"Inès a les mains mouillées et veut brancher le chargeur dans la prise. Que doit-elle faire ?"* (`T4-r1` de mon avis 0608) | refus | refus |
| *"Le sèche-cheveux est branché au bord du lavabo. Que doit faire Lina ?"* (sa reecriture) | passe | passe |
| *"Le sèche-cheveux est branché au bord du lavabo, à côté de l'eau. Que doit faire Lina ?"* (de moi) | passe | passe ; tue le mutant qui compte les reperes |
| *"Inès a les mains mouillées et veut brancher la lampe. Que doit-elle faire ?"* (`T4-p`) | passe | passe |

`G2` lit la meme chose que la clause *situation declaree* de `g1` et ne la remplace pas : `g1` exige le repere de la situation declaree, `G2` refuse le repere d'une autre. Les enonces des sondes de 1.1 ne portent chacun qu'une situation (verifie par le programme).

### 1.3 `G3` : les cinq abstentions en distracteur

**Forme** : sur `plat` de chaque distracteur, `^(?:s'eloigner|attendre|ne rien toucher|ne pas s'approcher|continuer a jouer ailleurs)(?!\p{L})`. Les cinq sont celles de 6.4 de son arbitrage, regle 3 remplacee. Ancree au debut : *"s'éloigner du fil"* est refuse, *"réparer le fil et attendre"* ne l'est pas, *"une abstention ecrite autrement reste a ma lecture"*. La bonne reponse n'est pas lue ; `g2` la ferme deja sur la table.

| Sonde | Attendu | Obtenu |
|---|---|---|
| `fil` ; *"ne pas toucher le fil"* ; *"ne rien toucher"*, *"réparer le fil avec du scotch"* | refus | `G3` seule (`g3` et `g4` existantes : passent) |
| `fil` ; *"laisser le fil sans le toucher"* ; *"s'éloigner du fil"* (`U+2019`), *"réparer le fil avec du scotch"* | refus | `G3` seule |
| meme item, le distracteur remplace par *"S'éloigner."*, *"attendre"*, *"ne pas s'approcher"*, *"continuer à jouer ailleurs"* | refus | refus, chacune |
| `fil` ; *"ne pas toucher le fil"* ; *"ne rien dire à personne"*, *"ne pas en parler"* | passe | passe |
| `fil` ; *"laisser le fil sans le toucher"* ; *"réparer le fil et attendre"*, *"toucher le fil pour voir"* | passe | passe ; tue le mutant non ancre |
| meme item, *"attendrir le fil"* | passe | passe ; tue le mutant sans borne |

**Ce que l'ancre refuse en plus, mesure sur le materiau reel** : 7 distracteurs du corpus commencent par *attendre*, aucun dans `ce1-qlm-*` : *"attendre sans rien dire"*, *"attendre assise sans parler à personne"* (`cp-emc-alerte`), *"attendre que cela s'arrête tout seul"* (deux fois, `cp-emc-exclusion`), *"attendre en silence"*, *"attendre que les autres soient partis"* (`cp-emc-regles-de-vie`), *"attendre qu'il remarche pour sortir"* (`cm1-sciences-phenomenes`). Les deux premiers sont, a la lecture de sa regle, des refus de signaler, qu'elle admet en distracteur ; ecrits a `-08`, `G3` les refuserait. Le faux rouge est visible et se reecrit par une forme qu'elle admet (*"ne rien dire à personne"*). Je ne retire pas l'ancre, qu'elle a demandee : `P1`.

### 1.4 `G4` : la precaution niee

**Forme** : sur l'egalite de chaque distracteur, `^ne (pas|jamais) (.+)$` ; refus si le groupe 2 est une cle de la table des neuf formes, **quelle que soit la situation de l'item**. Egalite, pas prefixe : *"ne pas laisser le fil"* n'est pas *ne pas* suivi d'une forme et passe. Les six formes qui commencent deja par *ne* ne donnent rien de lisible une fois niees ; la garde mord en pratique sur les trois autres (*laisser le fil sans le toucher*, *attendre d'avoir les mains seches*, *se secher d'abord les mains*).

| Sonde | Attendu | Obtenu |
|---|---|---|
| `eau`, enonce des mains mouillees ; *"se sécher d'abord les mains"* ; *"ne pas attendre d'avoir les mains sèches"*, *"brancher la lampe tout de suite"* | refus | `G4` seule (`g3`, `g4` existantes : passent) |
| `fil` ; *"ne pas toucher le fil"* ; *"ne pas attendre d'avoir les mains sèches"*, *"réparer le fil avec du scotch"* | refus | refus ; tue le mutant qui ne lit que les formes de la situation de l'item |
| `eau`, mains ; *"attendre d'avoir les mains sèches"* ; *"ne jamais se sécher d'abord les mains."*, *"brancher la lampe tout de suite"* | refus | refus ; tue le mutant sans *jamais* |
| `fil` ; *"ne pas toucher le fil"* ; *"ne pas en parler"*, *"toucher le fil pour voir"* | passe | passe |
| `fil` ; *"laisser le fil sans le toucher"* ; *"ne pas laisser le fil"*, *"toucher le fil pour voir"* | passe | passe ; tue le mutant par prefixe |

### 1.5 `G5` : *personne ne*

**Motif**, sur `plat`, remplace celui de 2.5 de mon avis 0608 :

```
(?<!(?<!\p{L})on )(?<!(?<!\p{L})on ne )(?<!(?<!\p{L})personne ne )(?<!\p{L})se (bless(e|es|ent|ait|aient|a|erent|era|erait|eront|eraient)|(fait|font|faisait|faisaient|fit|firent|fera|ferait|feront|feraient) mal)(?!\p{L})
```

L'exception porte sur *personne ne*, pas sur *personne* : *"Une personne se blesse."* designe quelqu'un de la scene et reste refuse.

| Sondes | Attendu | Obtenu |
|---|---|---|
| *"Un élève se blesse pendant la récréation."* (`nadia-prof`), *"Lina se blesse avec le fil."*, *"Lina se blessera."*, *"Une personne se blesse."* (de moi) | refus | refus ; la derniere tue le mutant qui exempte *personne* seul |
| *"pour que personne ne se blesse"* (`nadia-prof`), *"Personne ne se fait mal."*, *"Personne ne se blessera."* (de moi), *"On ne se blesse pas si on ne touche pas le fil."*, *"On se blesse avec un fil abîmé."*, *"pour ne pas se blesser"* | passe | passe |

**Materiau reel** : l'ancien motif trouvait 3 champs du corpus, le nouveau en trouve **2** ; celui qui sort est *"pour que personne ne se blesse"* (`cp-emc-regles-de-vie`), la phrase meme qu'elle exempte. Restent *"Un élève se blesse pendant la récréation ..."* (`cm1-emc-institutions`), refuse a raison par sa regle, et *"Des muscles froids se blessent ..."* (`cp-eps-corps-effort`), hors de `-08`.

## 2. `-05` (`ce1-qlm-eau-trois-etats`)

### 2.1 `G6` : *au-dessous de*

`SOUS_ZERO` = `(?<!\p{L})(?:sous|en dessous de|au-dessous de) (?:0|zero) degres?(?!\p{L})`, doit etre present dans l'enonce d'un item a bonne reponse *rien ne change* ; `EAU` inchange. Le trait d'union est garde par `plat`.

| Cas | Attendu | Obtenu |
|---|---|---|
| *"Un glaçon est posé dehors. Toute la journée, il fait au-dessous de 0 degré. Que se passe-t-il ?"* | passe | passe ; l'ancien motif le refusait |
| *"... il fait au-dessus de 0 degré."* | refus | refus |
| *"sous zéro degré"*, *"en dessous de 0 degré"* | passe | passe |

Materiau reel : `au-dessous de` est ecrit une fois dans le corpus (*"au-dessous de l'oiseau"*, `cp-qlm-vocabulaire-de-position`), que le motif ne lit pas (pas de degre). `SOUS_ZERO` ancien et nouveau : 0 champ.

### 2.2 `G7` : le changement sans objet, garde du cote de la glace seulement

**Ce qui est garde.** Dans un item de tache temperature (`T05-1`), si une proposition, bonne reponse ou distracteur, porte `GLACE` (`glaces?|glacons?`, bornes), l'enonce porte `GLACE` ou `GEL` (les formes de *geler* de 3.1 de mon avis du 2026-10-26, dont *gelee*). Sinon refus, *"regle 2 : '%s' nomme une glace que l'enonce ne decrit pas"*. La garde lit la **matiere nommee** par la proposition et non le verbe : *"il reste de la glace"* sans glace decrite est un changement sans objet comme *"le glaçon fond"*, et sa regle (*"le changement d'une matiere que l'enonce ne decrit pas"*) les vise tous deux.

**Ce qui ne l'est pas : le cote *eau***. La forme qu'elle propose, *l'eau gèle* refusee dans un item dont l'enonce n'a pas *eau*, **refuse son propre exemple** de 2.2 de `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md` : *"La mare est gelée en surface. Le soleil la réchauffe au-dessus de 0 degré. Que se passe-t-il ?"* ; *la glace fond* ; *l'eau gèle*, *rien ne change*. La mare sous sa glace est de l'eau liquide, *l'eau gèle* y a un objet, et l'enonce ne porte pas le mot *eau*. Elargir le repere (*mare*, *flaque*, *lac*, *pluie*...) donne une liste qui ne se ferme pas, le motif que j'ai deja donne en 1.3 de mon avis 0405 pour *sans eau liquide*. **Non garde : `ETE-CE1-2`** : un changement de l'eau liquide propose sans eau liquide decrite. Lu par `nadia-prof` ; voir `P2`.

| Sonde | Attendu | Obtenu |
|---|---|---|
| ses deux exemples de 2.2 (verre d'eau et glacon au congelateur ; mare gelee), l'item du lendemain (*seulement de la glace*...), l'item de la regle 2 (*rien ne change* ; *le glaçon fond*, *le glaçon disparaît*) | passe | passe, les quatre |
| *"Un verre d'eau est posé au congélateur. Que se passe-t-il ?"* ; *l'eau gèle* ; *le glaçon fond*, *rien ne change* | refus | refus |
| meme enonce ; *l'eau gèle* ; *la glace fond*, *rien ne change* | refus | refus |
| meme enonce ; *l'eau gèle* ; *il reste de la glace*, *rien ne change* | refus | refus ; tue le mutant qui lit `FONTE` au lieu de la matiere |
| la mare gelee, sous un mutant qui n'accepte que `GLACE` dans l'enonce | - | refus : le mutant meurt, `GEL` dans l'enonce est necessaire |
| la mare gelee, sous la forme *eau* demandee | - | **refus de son exemple** |

Une premiere sonde de ma main (*"Un verre d'eau est posé au soleil."* ; *"l'eau reste liquide"*...) n'atteignait pas `G7` : sa bonne reponse n'est classee par aucune etape de `T05-1`, qui la refuse avant. Elle est remplacee par la deuxieme ligne refusee.

**Materiau reel** : sur tout le corpus, le cote glace ne trouve que 2 items, hors de `-05` et classes temperature par la seule presence de `GLACE` dans la bonne reponse (*"un glaçon"*, `ce1-graphie` ; *"la galerie des Glaces"*, `cm1-histoire-geographie-nommer-en-histoire`). `G7` ne lit que le paquet de `-05` : ce compte dit pourquoi elle n'entre pas dans le test transverse. Cote *eau*, 0.

**Faux rouge du cote glace, dit** : un enonce qui decrit de la glace sans ces mots (*la neige*, *le givre*, *le bonhomme de neige*) refuse *"la glace fond"*. Visible, il se corrige par l'enonce.

## 3. `-04` : `G8`, `T04-3e` et sa phrase

`F2` ne change pas (sa reponse a `P1-0405` le confirme). **`T04-3e` ne correspond pas tout a fait a sa phrase** de 6.1 : *"les mots des cinq noms de famille, avec leurs formes de genre et de nombre"*. Les cinq noms (critere 1 de `-04`) sont *fruits et legumes*, *feculents*, *produits laitiers*, *viande, poisson et oeufs*, *produits sucres*. Mot par mot contre le motif de 2.2 de mon avis du 2026-10-26 :

| Mot du nom | Formes de genre et de nombre | Dans `T04-3e` |
|---|---|---|
| fruit, legume, viande, poisson, oeuf | `fruits?`, `legumes?`, `viandes?`, `poissons?`, `oeufs?` | oui |
| laitier | `laitiers?`, `laitieres?` | oui |
| sucre (adjectif) | `sucres?`, `sucrees?` | oui |
| feculent | `feculents?`, **`feculentes?`** | **le feminin manque** |
| **produit** | **`produits?`** | **absent** |
| *lait* (exemple de sa parenthese, pas un mot d'un nom) | `laits?` | oui, garde : sa parenthese le nomme |

*Et* n'est pas lu : un mot grammatical, que sa phrase ne peut viser sans refuser tout enonce a deux noms.

**Forme retenue** : `(?<!\p{L})(?:poissons?|oeufs?|viandes?|laits?|laitiers?|laitieres?|fruits?|legumes?|sucres?|sucrees?|feculents?|feculentes?|produits?)(?!\p{L})`, sur l'enonce d'un item de classement de `-04`, comme avant. C'est sa regle, pas une regle neuve : je suis sa phrase la ou elle ecrit *"ce que `T04-3e` encode est la regle"*, comme j'ai suivi sa regle contre sa table en `Q8`.

| Cas | Attendu | Obtenu |
|---|---|---|
| *"Dans quelle famille range-t-on la pomme ?"*, *"... la carotte ?"*, *"... la pomme de terre ?"*, *"... le riz ?"* | passe | passe |
| *"... le poisson ?"*, *"... l'œuf ?"*, *"... le lait ?"*, *"... la viande ?"*, *"... la compote sucrée ?"*, *"... le fromage laitier ?"*, *"... la boisson laitière ?"* | refus | refus |
| *"... la purée féculente ?"*, *"... ce produit ?"* | refus | refus ; **l'ancien motif les laissait passer** |

**Materiau reel** : les deux alternatives ajoutees trouvent 28 champs du corpus, 0 dans `ce1-qlm-*` (*"des produits sucrés"*, `cm1-comprehension` ; *"calcule chaque produit"*, cinq fois, `cm1-multiplication-posee`). Le motif complet passe de 138 a 165 champs sur le corpus et reste a 28 dans `ce1-qlm-*`, inchange. Il ne lit que l'enonce de classement de `-04` : *produit* au sens du calcul ne le croise jamais.

## 4. `-06` (`ce1-qlm-aimant`)

### 4.1 `G9` : au moins un item de A compte

**Forme** : dans `g4`, a cote du quart (`4 * comptes < items`, inchange), une clause : parmi les items dont l'enonce a `FORME_A`, au moins un verifie `compte` (2.1 de mon avis du 2026-10-26, inchangee). Message propre, *"regle 1 : aucun item de A ne compte pour le quart"*. La sonde se juge sur ce message.

| Sonde (paquet de sonde) | Attendu | Obtenu |
|---|---|---|
| 9 items A sans premisse (*le clou* ; *la gomme*, *la bille en verre*), 3 items B qui comptent | refus `G9` ; le quart passe (3 sur 12) | refus `G9`, quart passe ; tue le mutant *"aucun item ne compte"* |
| 8 de ces items A, 1 item A qui compte (*"Ces trois objets sont en métal. Lequel l'aimant attire-t-il ?"*, *le clou* ; *le papier d'aluminium*, *le fil de cuivre*), les memes 3 B | passe | passe ; tue le mutant qui calcule le quart sur A seul |

**Sonde sur le materiau reel, quand le paquet existera** : le paquet reel prive de ses items A qui comptent, en-tete inchange (sa demande). Elle peut lever aussi `FER-1` ou le plancher de A ; elle se juge alors sur le seul message de `G9`, comme `S1` de mon avis 0405.

### 4.2 `G10` : le bon groupe, seul plus court

**Forme**, methode a part sur la liste des items de B (`FORME_B`), sur le modele de `F2` : `S` = nombre d'items ou `PaquetLu.mots(bonne) < PaquetLu.mots(d)` pour **chacun** des deux distracteurs (strict : une egalite n'est pas *seul plus court*) ; refus si `2 * S > nB`, message *"regle 2 B : le bon groupe est le seul plus court dans %d item(s) sur %d"*. `PaquetLu.mots` est la mesure de `LON-CE1-5` : la meme que le developpeur lit quand il compose.

Sondes, quatre items B de ma main sauf son exemple, chacun verifie par le programme pour `g7` (trois `FER` ; deux `FER` et un `METAL`), `LON-CE1-5` (6 mots au plus), `compte`, et le partage d'un objet entre chaque paire de groupes (sa regle 3 de B) :

| Item | Groupes (bonne ; distracteurs) | Mots | Seul plus court |
|---|---|---|---|
| `E` (`nadia-prof`, 6.3) | *fil de fer, clou, agrafe* ; *fil de fer, épingle, papier d'aluminium* ; *clou, épingle, fil de cuivre* | 5 ; 6 ; 5 | non |
| `X` | *clou, trombone, aiguille* ; *clou, épingle, papier d'aluminium* ; *trombone, épingle, fil de cuivre* | 3 ; 4 ; 5 | oui |
| `X2` | *épingle, agrafe, trombone* ; *épingle, clou, barquette en aluminium* ; *agrafe, clou, papier d'aluminium* | 3 ; 5 ; 4 | oui |
| `Y` | *fil de fer, trombone, aiguille* ; *trombone, épingle, papier d'aluminium* ; *aiguille, épingle, fil de cuivre* | 5 ; 4 ; 5 | non |

| Paquet B | Attendu | Obtenu | Mutant tue |
|---|---|---|---|
| `X`, `X2`, `E` (2 sur 3) | refus | refus | - |
| `E`, `X`, `Y` (1 sur 3) | passe | passe | egalite comptee comme *plus court* ; *plus court qu'un des deux* ; longueur en caracteres (`E` y est le seul plus court : 24 ; 39 ; 28) |
| `X`, `X2`, `E`, `Y` (2 sur 4) | passe | passe | refus a la moitie exacte |

Son exemple passe, et c'est lui qui tue trois des quatre mutants : materiau d'elle plutot que de moi.

**Ce que la garde ne lit pas** : que *fil de fer* ne devienne pas le signe du bon groupe (son rapport de premier jet le compte) ; la composition au-dela du compte. Par construction des listes (2.0 de mon avis du 2026-10-26), un bon groupe sans *fil de fer* fait 3 mots et tout distracteur 4 au moins : *"dans les autres, il porte fil de fer"* (6.3) suit du compte et n'a pas d'assertion propre.

### 4.3 *Fil de fer* en distracteur : aucun de mes avis ne l'interdit

La demande me dit de corriger mon avis precedent. **Je n'y trouve pas le passage a corriger.** 1.3 de mon avis 0608 ecrit : *"10 des 45 distracteurs (deux `FER` et un `METAL`) en font 7 et sont refuses, exactement ceux qui joignent fil de fer a barquette en aluminium ou a fil de cuivre"* : *fil de fer* avec *papier d'aluminium* y reste admis, et c'est cette mesure que son arbitrage cite (4.2, *"mesure de theo-archi, ajustements -06/-08, 1.3"*). `A3` de mon avis du 2026-10-26 nomme *"fil de fer, fil de cuivre, clou"*, 7 mots, ce qui est exact. Le constat qu'elle corrige est celui de `Q9-5` dans l'annotation neuvieme de `docs/backlog/backlog.md`, qui dit aussi *papier d'aluminium* *"objet de trois mots"* : `PaquetLu.mots` en compte 2. Mesure de ce jour : *"fil de fer, papier d'aluminium, clou"* 6, *"fil de fer, barquette en aluminium, clou"* 7, *"fil de fer, fil de cuivre, clou"* 7. Aucun marqueur dans mes avis ; la correction de l'annotation est a `camille-po`.

## 5. `-10` (`ce1-qlm-paysages`)

### 5.1 `G11` : en A, aucun propre d'un paysage propose

**Forme** de `G10-4` A, remplace le premier point de 6.1 de mon avis 1012 : sur l'indice mis sous la forme de 1.2 de mon avis du 2026-10-26, refus si une forme de `FORME_PAYSAGE` y est (inchange) **ou** si une forme d'un element `PROPRE` de l'un des trois paysages proposes (bonne reponse et deux distracteurs) y est. Les elements partages et les propres du quatrieme paysage, non propose, restent admis.

| Sonde (A, *"On voit un sommet et une forêt. Quel est ce paysage ?"*, *la montagne* ; *le littoral*, *la ville*) | Attendu | Obtenu |
|---|---|---|
| indice *"Ce n'est pas au bord de la mer."* (son cas, 6.5) | refus | refus ; **l'ancienne forme le laissait passer** |
| indice *"Il n'y a pas d'immeubles."* | refus | refus (pluriel enumere) |
| indice *"Pense au sommet."* | refus | refus ; tue le mutant qui ne lit que les distracteurs |
| indice *"Regarde s'il y a des champs."* | passe | passe ; tue le mutant qui lit les quatre paysages |
| indice *"Pense à la forêt."* ; *"Cherche ce qui ne va qu'avec un seul paysage."* | passe | passe |

### 5.2 `G12` : *littoral* hors du littoral

Inchangee, quatrieme point de 6.1 de mon avis 1012 : un item B dont le lieu n'est pas *sur le littoral* n'a pas `littoral` dans son indice. Sonde refusee *"Lequel ne voit-on pas à la montagne ?"*, indice *"Pense au littoral."* ; passe, l'item B *sur le littoral* et son indice *"Sur le littoral, il y a par exemple la mer."*, qui tue le mutant appliquant la clause a tout B.

### 5.3 `G13` et ce que *"aucun adjectif, indice compris"* change

**La regle (iii) suffit pour l'exemple** : chaque segment apres *par exemple*, determinant retire, est **egal** a une forme de *plage*, *mer* ou *phare*. *"... par exemple la grande mer."* est refuse ; *"... par exemple la mer et le phare."* passe.

**Le reste de l'indice : non garde : `PAY-CE1-2`.** *"Sur le grand littoral, il y a par exemple la mer."* passe, mesure. Un adjectif qui n'est pas un jugement ailleurs dans un indice de A ou de B (*"une grande forêt"*) n'est lu par rien ; les dix-sept mots de `G10-5` ne lisent que le jugement.

**Pour `G10-1` et `G10-5`, `P1` ne change aucune forme.** `G10-1` tient l'interdit dans l'enonce et les propositions par egalite, comme 4 de mon avis 1012 l'ecrit ; il ne lit pas l'indice et n'a pas a le lire, la regle (iii) etant l'egalite de `G10-1` appliquee aux segments de l'exemple. `G10-5` reste la seule garde de l'indice pour le jugement, liste inchangee. Ce que `P1` change est la classe du reste : un adjectif dans l'indice etait hors de sa regle, il est desormais dans sa regle et non garde, donc a sa lecture.

## 6. Mesure

- **Methode** : programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources du JDK que designe la toolchain `jdk` 21 de `~/.m2/toolchains.xml` ; **la machine virtuelle a rapporte `java.version=21.0.10`, `java.vm.version=21.0.10+8-LTS-217`**. Classpath : SnakeYAML 2.6 du depot Maven local, version non relue contre `dependency:build-classpath`. Lecture par `new Yaml().load`, collecte recursive de toute table portant `bonneReponse` ; `PaquetLu.sansAccent` et `PaquetLu.mots` recopies. Les formes sont celles de ce fichier ; les gardes existantes qui decident d'un verdict (`-08` `g3`, `g4` ; `-06` `compte`, quart, composition `g7`) sont recopiees de mes avis ; `T05-1` est recopiee en version reduite (etapes 1, 4, 5 et *eau liquide* en sous-chaine), suffisante pour les sondes de 2.2. **Ce n'est pas le test** : la preuve dans le depot sera l'execution des classes de test sur leurs jeux fautifs commites.
- **Perimetre du corpus** : tout `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `97c67bb2` ; 190 fichiers, **3 182 items, 15 751 champs** (enonce, indice, explication, bonne reponse, chaque distracteur), 9 375 propositions, les memes comptes que mon avis 0608. Dont `ce1-qlm-*` : 3 fichiers, 41 items, 205 champs. Exclus : les jeux fautifs de `src/test/resources`, les items sans `bonneReponse`.
- **Sur les trois paquets `ce1-qlm-*` existants** : 0 pour `G2`, `G3`, `G4`, `G5`, `G6`, `G7` (les deux cotes), les alternatives ajoutees a `T04-3e`, `G11` (propres dans un indice), `G12` ; `T04-3e` complet 28 champs, le compte de mon avis 0405, sans objet hors de `-04`. Chaque garde ne lit que son paquet, aucun des six n'existe : **aucun test existant ne change d'etat**.
- **Sur le corpus entier** : `G2` 0 enonce ; `G3` 7 distracteurs (1.3) ; `G4` 0 ; `G5` 2 champs au lieu de 3 (1.5) ; `G6` 0 ; `G7` glace 2 items hors de `-05`, eau 0 (2.2) ; `T04-3e` 165 champs au lieu de 138, dont 28 par les ajouts (3) ; `G11` 7 indices portent un propre de `-10`, aucun `ce1-qlm-*` ; `littoral` 0 champ ; *fil de fer* 0 proposition. `G10` n'a aucun materiau reel ou mordre : sa preuve repose sur l'exemple de `nadia-prof` jusqu'au paquet de `-06`.
- **Sondes et mutants** : **104 verdicts, 104 conformes**, apres remplacement d'une sonde de `G7` qui n'atteignait pas la garde (2.2). Les phrases de `nadia-prof` sont recopiees a l'identique avec leurs accents ; les autres sondes sont de ma main et valent moins que ses phrases et que le corpus.

## 7. Passages de mes avis que j'abandonne

Mes avis ne sont pas reecrits. Chaque passage ci-dessous y reste, et cesse de valoir, selon le precedent de 4 de mon avis 0608 et de 8 de mon avis 1012.

| Code | Passage | Marqueur |
|---|---|---|
| `M1` | Avis 0405, 1.3, bloc de code, ligne `SOUS_ZERO`, et paragraphe *"Formes admises fermees. ... Au-dessous de 0 degre, francais correct, est refuse : un faux rouge, qui s'ajoute par une alternative apres son accord."* | [corrige le 2026-10-27, `theo-archi`, `avis-theo-palier-5-dernier-ajustement-gardes-ce1-qlm-05-a-12.md`, section 2.1 : *au-dessous de* est admis] |
| `M2` | Avis 0405, 2.2, *"`T04-3e` ne change pas"* et le paragraphe *"Ce que le motif porte au-dela des sept mots ... ces formes se retirent du motif, une ligne."* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 3 : les formes restent, `feculentes?` et `produits?` s'ajoutent] |
| `M3` | Avis 0608, 0, ligne `T5`, *"sauf apres `on` ou `on ne`"*, et 2.5, bloc de code et *"On ne est exempte comme on"* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 1.5 : *personne ne* est exempte aussi] |
| `M4` | Avis 0608, 2.6, *"Deux gardes lexicales rendraient ce raccourci visible, encodables, non ecrites sans son accord : `Q11` et `Q12`."* | [corrige le 2026-10-27, `theo-archi`, ce fichier, sections 1.3 et 1.4 : ecrites, `G3` et `G4`] |
| `M5` | Avis 0608, 5, *"Reste a sa lecture"* : *"qu'un distracteur de `-08` laisse l'enfant hors du danger, au-dela de `g9`"* et *"une seule situation par enonce (`Q10` si elle n'en veut pas)"* ; table, colonne *"Ce que la forme fait aujourd'hui"*, lignes `Q9` a `Q12` | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 1 : son critere devient *"un adulte attendrait-il cela"*, garde par `G3` pour ses cinq formes ; une situation par enonce `geste`, garde par `G2` ; `Q9` a `Q12` encodes] |
| `M6` | Avis 1012, 6.1, premier point, *"aucune forme d'un element propre du paysage de la bonne reponse"* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 5.1 : des trois paysages proposes] |
| `M7` | Avis 1012, 4, derniere phrase, *"Un adjectif qui n'est pas un jugement, dans un indice (*"par exemple la grande mer"*), n'est garde par rien : voir `P1`."* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 5.3 : *la grande mer* apres *par exemple* est refusee par la regle (iii), mesure ; l'adjectif ailleurs dans l'indice n'est garde par rien] |

Aucun marqueur pour *fil de fer* : section 4.3.

## 8. Ce qui reste lu par `nadia-prof`, et ce que je lui soumets

**Non garde, a sa lecture**, en plus de la section 7 de son arbitrage et de mes avis precedents :

- *"non garde : `ETE-CE1-2`"* : en B de `-05`, un changement de l'eau liquide propose quand l'enonce ne decrit pas d'eau liquide (*l'eau gèle* sous un glacon seul) (2.2) ;
- *"non garde : `PAY-CE1-2`"* : un adjectif dans un indice de `-10`, hors des segments de l'exemple de *littoral* (5.3) ;
- a `-08`, une abstention ecrite hors de ses cinq formes ou apres le debut de la proposition (1.3) ;
- a `-06`, que *fil de fer* ne devienne pas le signe du bon groupe de B (4.2).

| Code | Constat mesure | Ce que la forme fait |
|---|---|---|
| `P1` | `G3` ancree refuse, a `-08`, tout distracteur qui commence par *attendre*. Le corpus en ecrit 7 ailleurs, dont *"attendre sans rien dire"* et *"attendre assise sans parler à personne"*, que sa regle range parmi les refus de signaler qu'elle admet | refuse ; le developpeur ecrit *"ne rien dire à personne"*. Si elle veut admettre *attendre* suivi d'un refus de signaler, l'alternative `attendre` devient une egalite (*attendre* seul), une ligne |
| `P2` | La forme qu'elle propose pour *l'eau gèle* refuse son exemple de la mare gelee (2.2) | cote *eau* non garde ; cote glace garde |
| `P3` | `T04-3e` n'avait ni `feculentes?` ni `produits?`, que sa phrase de 6.1 vise ; ajoutes | refuse *"la purée féculente"*, *"ce produit"* ; retrait d'une alternative si elle la refuse |

## 9. Actions, cout, duree, verifications

| Code | Quoi | Qui |
|---|---|---|
| `A1` | `G1` a `G5` dans `PaquetCe1QlmDangersElectriciteTest`, `G6` et `G7` dans le test du paquet de `-05`, `G8` dans celui de `-04`, `G9` et `G10` dans `PaquetCe1QlmAimantTest`, `G11` dans `PaquetCe1QlmPaysagesTest`, avec leurs sondes, chacune jugee sur le message de sa garde, dans le commit du premier jet ; remplacent les passages `M1` a `M7` | `marc-dev` |
| `A2` | `P1` a `P3` | `nadia-prof`, par `sofia-sm` |
| `A3` | `Q9-5` de l'annotation neuvieme : *papier d'aluminium* fait 2 mots, et *fil de fer* reste admis en distracteur avec lui (4.3) | `camille-po` |

**Cout** : 0,00, par la decision de l'utilisatrice du 2026-10-27 (arbitrages avant premier jet non chiffres), sous laquelle ce geste de forme tombe ; le classer est a `camille-po`.

**Duree** : debut non lu par `date`, je ne l'ecris pas ; fin **10:22:12**, lue par `date` apres les verifications ci-dessous, avant le commit.

**Verifications avant commit**, ce fichier present dans l'arbre, base `97c67bb2`. Il ne cree ni ne renomme aucun identifiant de condition.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `bash .github/scripts/verifier-chemins.sh < /dev/null` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- Aucun `mvn verify`, aucun autre job de `.github/workflows/ci.yml` lance.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-27.
