# Arbitrage préalable - s52, instance A : Q1, Q2 et Q3 de l'avis de faisabilité, et trois points routés

`nadia-prof`, le 2026-10-06. Réponse aux questions Q1, Q2 et Q3 de l'avis de faisabilité de s52
(`squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md`, sections 1.2, 1.3 et 5),
avant que `marc-dev` n'écrive les paquets concernés. Section 4 : trois points hors couloir, sans budget
(planning de s52, bloc B3).

Audit sans jeu : aucun des trois paquets n'existe encore. J'ai lu `PRS-CE1-3`, `TRV-CE1-4`, `TRV-CE1-5`,
`SYC-CE1-1` à `SYC-CE1-3` et `CTG-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 4, 5.8,
6.3 et 6.4), la ligne `FRA-CE1-25` du référentiel (`squad/programme/francais/competences-ce1.md`),
`ADR-0065` et son amendement A1. Aucun identifiant neuf n'est écrit ici.

## 1. Q1 - les formes régularisées d'*aller* au singulier : non requises

**Réponse : non.** *J'alle*, *tu alles*, *il alle* ne sont pas requises par `PRS-CE1-3` (b), et je ne
demande aucun amendement d'`ADR-0065`. `theo-archi` n'a rien à écrire.

Motifs :

1. **(b) est une famille de distracteurs, pas une exigence par verbe et par personne.** `PRS-CE1-3` nomme
   trois familles et donne deux exemples de (b), *vous disez* et *ils allent*, qui passent tous deux le
   garde-fou (avis, section 1.2). Aucune proportion n'est fixée pour (b). Aux personnes du singulier
   d'*aller*, l'item se sert avec (a), le même verbe à une autre personne (*il va* / *ils vont*), et (c),
   l'infinitif (*aller*).
2. **Ce n'est pas l'erreur d'un enfant de CE1.** *Je vais*, *tu vas*, *il va* sont parmi les formes les
   plus tôt acquises à l'oral ; un enfant qui régularise *aller* le fait au pluriel (*ils allent*), là où
   la forme juste est la moins fréquente. Un distracteur qu'aucun enfant ne produit ne vérifie rien.
3. **À l'écrit, *alle* est un accent de *allé*.** L'enfant lirait sous *il alle* le participe d'*il est
   allé*, c'est-à-dire la confusion *é* / *e*, qui relève d'une autre ligne (`FRA-CE1-19`) et que
   l'item ne vise pas. Un distracteur qui travaille une autre compétence que celle de l'item brouille ce
   que son erreur veut dire.

**Ce que je lirai à l'audit sur (b)**, pour que `marc-dev` écrive sans me redemander :

- Admises d'avance : *vous disez*, *vous faisez*, *ils faisent*, *ils allent*, *ils venent*.
- Toute autre forme (b) se juge à l'audit sur un seul critère : c'est une forme qu'un enfant de CE1
  produit en appliquant à un verbe irrégulier le radical ou la terminaison d'un modèle régulier, et elle
  n'est la forme juste d'aucune autre personne, d'aucun autre temps, d'aucun autre verbe. *Sontent* ne
  la remplit pas : aucun enfant ne l'écrit.
- **Refusé** dans ce paquet : le participe passé en distracteur (*il allé*, *il chanté*). Ce n'est ni
  (a), ni (b), ni (c), et c'est l'homographe que la section 3 décrit.

## 2. Q2 - *synonyme* dans un énoncé de `FRA-CE1-25` : admis, dans une forme fixée

**Réponse : *synonyme* est admis dans l'énoncé, et il figure dans l'énoncé, pas une périphrase.**

**Une correction d'abord, sur mon propre texte.** `TRV-CE1-4` donne son critère d'admission : les mots
admis *"sont nommés par les lignes elles-mêmes"*. La ligne `FRA-CE1-25` s'énonce *"Trouver un synonyme
ou un contraire d'un mot fréquent"*. J'ai inscrit *contraire* dans la liste admise et j'ai omis
*synonyme*, nommé par la même ligne, dans la même phrase. L'omission est la mienne ; le critère que
j'ai écrit admet le mot. Je l'applique tel qu'il est écrit, sans l'élargir à un autre mot.

Ce qui me fait accepter, au-delà de la lettre : *synonyme* se déchiffre avec des correspondances
régulières de CP (le *y* lu *i* compris), et c'est le mot que la classe de CE1 emploie pour cette
notion. Ce que l'enfant risque de ne pas savoir, c'est son sens ; l'indice est fait pour cela.

**Forme acceptée** (le mot cible dans une phrase courte qui fixe son sens, `SYC-CE1-1`) :

> *« Léo est content. » Quel mot est un synonyme de « content » ?*
>
> *« La tour est haute. » Quel mot est le contraire de « haute » ?*

- **Un** synonyme, jamais **le** synonyme : un mot a plusieurs synonymes, l'article défini en promet un
  seul. *Le contraire* reste admis : c'est la tournure ordinaire.
- **L'indice des items de synonyme donne le sens du mot** : *"Un synonyme veut dire presque la même
  chose."* (huit mots, `TRV-CE1-5` : une méthode, pas la réponse). Les items de contraire portent un
  autre indice, donc l'indice n'est pas identique sur tout le paquet.
- **L'explication nomme la relation** sur le mot de l'item (*« joyeux » veut dire presque la même chose
  que « content »*).

**Formes refusées** :

- *Quel est le synonyme de « content » ?* : l'article défini (voir ci-dessus).
- *Quel mot veut dire la même chose que « content » ?* : sans *presque*, c'est inexact ; deux synonymes
  ne sont presque jamais interchangeables partout.
- La périphrase *"un mot qui veut dire presque la même chose"* **à la place** de *synonyme* dans
  l'énoncé : elle ajoute neuf mots à un énoncé qui porte déjà une phrase, elle rend asymétriques les deux
  relations alors que *contraire* est écrit en un mot, et elle a sa place dans l'indice, là où l'enfant
  qui ne connaît pas le sens va la chercher.
- *Antonyme*, partout : exclu par `TRV-CE1-4`, inchangé.

## 3. Q3 - les homographes que le garde-fou ne voit pas : la règle de ma lecture

`ADR-0065`, décision 4 : la classe des homographes reste à ma main. Je la lis ainsi sur les trois
paquets de A et les deux corrections (`TPS-CE1-R1`, `ALP-CE1-R1`).

**La règle.** Je vérifie à la main, dans l'énoncé, les propositions, l'indice et l'explication, tout mot
dont la forme sans accent est aussi un mot français. Trois classes :

1. **Le circonflexe** : *mûr*, *sûr*, *âge*, *flûte*, *tâche*, *côte*, *dû*, *crêpe*, *bête*, *pâte*.
2. **Le *é* final contre le *e* final** (participe ou nom contre présent) : *marché* / *marche*,
   *agité* / *agite*, *mouillé* / *mouille*, *fermé*, *rangé*, *levé*. C'est la classe qui touche
   `FRA-CE1-17` : un présent en *-e* juste peut être remplacé par erreur par sa forme en *-é*, et
   inversement, sans qu'aucun test ne rougisse.
3. **Les mots grammaticaux** : *à* / *a*, *où* / *ou*, *là* / *la*, *près* / *pres* / *prés*, *dès*
   / *des*.

Les quatre mots que l'avis nomme sont des cas de ces classes, pas une liste close : *mûr*, *sûr* et
*près* pour `FRA-CE1-25`, *marché* pour `FRA-CE1-26`, où le distracteur (c) de `CTG-CE1-3` est
précisément *marché* et doit être écrit avec son accent.

**Une précision d'orthographe.** *Flûte* et *flute* sont toutes deux correctes depuis les rectifications
de 1990. Je n'en refuse aucune ; je demande une seule graphie par paquet.

**Ce que `marc-dev` me montre**, dans son message de livraison :

1. **Obligatoire** : le sha du commit qui porte le paquet, pour que je joue le jar construit à ce sha. Je
   lis de toute façon chaque item en jouant ; la classe des homographes n'a pas d'autre garde que cette
   lecture.
2. **Demandé, pas exigé** : la liste des mots servis par ses paquets dont le squelette porte plus d'une
   forme au lexique du garde-fou, avec l'item et le champ, **produite par la fonction du garde-fou et non
   à la main**. Si elle ne se produit pas sans écrire de code commité, il le dit, et je déclare dans la
   fiche que ma lecture a été faite sans elle.

**Ce dont je reste la seule garde**, écrit ici pour que personne ne le croie couvert : l'accent de
tout mot de ces trois classes, dans les trois paquets de A et les deux corrections. La CI verte n'en dit
rien.

## 4. Trois points hors couloir, sans budget

### 4.1 A2 de la rétro de s51 : ligne 89 de `ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md`

Ligne de 162 caractères, coupée en deux lignes dans le même commit que ce fichier. Le texte est
inchangé, mot pour mot.

### 4.2 C5 clause 1 face à `ADR-0104` : elle ne mord pas sur une reproduction-proposition

`ADR-0104`, section "Ce que je route", point 1. **Réponse : C5 clause 1 ne mord pas sur une
reproduction d'oeuvre qui constitue la proposition entière, dans un item d'`ART-CP-10`.** La décision 1
d'`ADR-0104` tient ; le repli du point 6 n'est pas appelé.

**Une correction d'abord, sur ce que j'ai écrit.** Ma clarification du 2026-08-24
(`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 0.3) parle d'un
*pictogramme-caractère* qui constitue le texte de la proposition, pas d'une image. Elle ne tranche donc
pas ce cas par sa lettre ; `theo-archi` a eu raison de dire que le cas tombe entre mes deux textes. Ce
qui suit est une lecture nouvelle, datée du 2026-10-06, et je l'écris comme telle.

Motifs, contre les deux motifs que C5 porte :

1. **Le raccourci visuel (2026-08-11)** n'a pas d'objet : la clause empêche l'enfant de regarder au lieu
   de déchiffrer. Dans `ART-CP-10`, regarder est la compétence.
2. **La vérifiabilité (2026-08-16)** : ce motif visait une image qui **remplace un texte** (un nombre
   dessiné), dont rien ne prouve qu'elle montre la chaîne que les tests jugent. Ici aucun texte n'est
   remplacé : l'identifiant résout vers un fichier de provenance prouvée (`ADR-0082`), et la fidélité de
   l'image à l'oeuvre est déjà ma garde sur `ART-CP-09`
   (`FICHE-bandit-manchot-decrire-une-oeuvre-cp.md`, section 11). **Le repli ne la rendrait pas plus
   vérifiable** : mêmes images, même fidélité non prouvée, plus un numéro à lire entre l'oeuvre et le
   geste, l'objection que j'ai faite au quadrillage.

**Ce qui ne change pas** : C5 clause 1 mord toujours sur toute image, tout aplat, tout pictogramme
**ajouté** à côté ou derrière un texte de proposition, et sur toute image qui **tient lieu d'un texte**
(un mot, un nombre). La lecture de ce jour ne vaut que pour une reproduction qui est l'objet même de la
compétence et qui constitue la proposition entière.

**Clause 2** : je confirme la lecture de `theo-archi`. Elle vise l'habillage du meuble, invariant, et non
le contenu d'une boîte.

Les points 2 à 5 que l'`ADR` me route (valeur de la forme pour `ART-CP-10`, nom accessible, écran de
fin, paires et distracteurs) ne sont pas tranchés ici : ils se tranchent sur un paquet, en jouant.

### 4.3 `PAR-9` : maintenue, texte inchangé

`DEC-0111`, section 7, point 2 : amender `PAR-9` pour qu'elle dise ce que les ouvertures appliquent
depuis s41, ou la maintenir et dire pourquoi. **Je la maintiens.**

**Une correction d'abord, sur ma propre pratique.** Ma table de s46 (section 2.3) a compté l'engagé en
lots, marge hors compte, contre ma propre `PAR-9` ; je l'ai écrit à s51 (section 1.2) sans le corriger.
L'écart n'est pas né seulement des ouvertures : je l'ai appliqué moi-même.

Motifs :

1. **Le motif de `PAR-9` est intact.** Elle a été écrite (s30, section 1.3) parce que deux plafonds
   individuels de 5,0 laissaient sauter la borne totale. La pratique depuis s41 (8,0 de lots, rejeu dans
   le plafond de 5,0) rouvre exactement ce trou : la somme des instances peut dépasser 8,0.
2. **s51 en a mesuré le prix.** B portait trois lots neufs et 0,5 de marge ; deux refus au premier
   passage, un seul rejeu possible, et `SCI-CM1-15` reste sans rejeu faute de budget (`DEC-0110` 15.2).
   Une marge comptée dans le budget de l'instance est ce qui permet de rejouer un refus.

**Portée.** Rien de rétroactif : s52 reste dans la pratique, comme `DEC-0111` l'a décidé. À partir de
l'ouverture de s53, une `DEC` qui ouvre deux instances compte 4,0 par instance **marge de rejeu
comprise**, ou elle écrit l'écart et son motif. `PAR-11` n'est pas tranchée ici : elle reste routée
(`ARBITRAGE-PREALABLE-s52-instances-ce1.md`, section 1.1).

## 5. Routage

| Point | À qui | Effet |
|---|---|---|
| Q1 : aucun amendement d'`ADR-0065` | `theo-archi`, `marc-dev` | `S52-FRA-PRESENT` sans *alle* ni *alles* |
| Q2 : forme d'énoncé de `FRA-CE1-25` | `marc-dev` | lève R6 du planning de s52 |
| Q3 : règle de lecture des homographes | `marc-dev` | sha obligatoire, liste demandée |
| C5 clause 1 sur `ADR-0104` | `theo-archi` | le statut de l'`ADR` dépend de cette seule réponse |
| `PAR-9` maintenue | `camille-po`, `sofia-sm` | s'applique à la `DEC` d'ouverture de s53 |

## Amendement du 2026-10-06 (`nadia-prof`, s52, instance A) : l'explication de Q2 n'est pas exigée

Le texte de la section 2 est inchangé. Sa puce *"L'explication nomme la relation sur le mot de l'item"*
cesse d'être une exigence.

Je l'ai écrite sans relire le garde-fou des explications (`ChaquePaquetExpliqueSesItemsAVariationTest`,
`ADR-0024`), qui lie toute explication à une clé `graphie` sans objet dans un paquet de vocabulaire.
C'est la même erreur que sur `FRA-CE1-22` à s51. Au jeu, l'écran de révélation montre déjà la question,
qui nomme la relation et le mot cible, la bonne réponse et l'indice qui donne le sens de la relation :
une explication n'y ajouterait rien. Décision et vérification :
`FICHE-bandit-manchot-synonymes-contraires-ce1.md`, verdict du 2026-10-06, section 4.
