# s57 - arbitrage avant les premiers jets de `ce1-qlm-aimant` (`-06`) et `ce1-qlm-dangers-electricite` (`-08`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26, debut 16:28:12 (UTC+01:00), lu par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-arb0608`, base `round-two` a `649f517b`, lue par `git rev-parse`.
- **Repond a** : `docs/backlog/backlog.md`, annotation du 2026-10-26, septieme, sections 5 et 6 (`N06-1`, `N06-2`, `N08-1` a
  `N08-4`), et section 9 (structure de l'intrus).
- **Type** : arbitrage avant premier jet, sur le precedent de `avis-nadia-seconde-tache-ce1-qlm-02.md`. **Aucun Done, aucun
  verdict de lot, aucun identifiant neuf, aucune condition neuve** : les regles ci-dessous sont des lectures de `AIM-CE1-1`,
  `AIM-CE1-2`, `DEL-CE1-1`, `DEL-CE1-2`, `TRV-CE1-2`, `TRV-CE1-5`, `TRV-CE1-6`, `QMT-CE1-2`, `FER-1` et `FER-3`, et des trois
  defauts que `DEC-0120` A.273 releve dans mes lectures (a, b, c). Les reperes (regle 1, garde 1...) sont locaux a ce fichier.
  Je ne modifie ni les stories, ni un paquet, ni un test : `camille-po` reporte les textes de 1.6 et 2.6.
- **Audit degrade, dit** : rien a jouer, aucun des deux paquets n'existe (`ls` du repertoire de contenu : trois fichiers
  `ce1-qlm-*`, ceux de `-01`, `-02`, `-03`). Je juge des formes de taches et de contenu, pas des items.
- **Lu** : stories `S57-BR-QLM-CE1-06` et `-08` et les annotations du 2026-10-25 (cinquieme, sixieme) et du 2026-10-26
  (septieme) ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` (2, 3.6, 3.8) ;
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (`LON-CE1-5`) ; `arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md` ;
  `reponse-nadia-aim-ce1-2-q1-q2.md`, `reponse-nadia-aim-ce1-2-q7.md`, `reponse-nadia-palier-5-qlm-items-et-a229.md` (3) ;
  `avis-theo-palier-5-exemption-aim-ce1-2.md` ; mes lectures des premiers jets de `-01`, `-02`, `-03` et mon avis sur la seconde
  tache de `-02` ; `DEC-0120` A.273 ; `PaquetLu.mots` ; voisins `cp-qlm-matiere`, `cm1-sciences-melanges`,
  `cp-eps-regles-de-securite`, et un `grep` de *aimant* et d'*electri* sur les paquets `cp-*`.

## Resume

| Question | Reponse | En une ligne |
|---|---|---|
| `N06-1` seconde tache de `-06` | **reformulee** | *attire* et *n'attire pas* sont une seule tache ; la seconde est *le groupe* : trois groupes de trois objets, le bon est celui ou l'aimant attire les trois (1.1) |
| `N06-2` *cible* | **reformulee** | ni l'objet de l'enonce seul, ni la bonne reponse seule, ni toute proposition : un item compte pour le quart quand ses objets sont tous en metal, que l'enonce le dit, et qu'au moins un est en aluminium ou en cuivre. L'exemple de `camille-po` ne compte pas (1.2) |
| `-06`, critere 3 | **corrige** | les six objets exclus le sont partout, pas seulement en cible (1.2) |
| Intrus sur `-06` | **forcee, je ne l'interdis pas** | elle est forcee des qu'un item juge trois objets sur un attribut binaire ; je demande seulement qu'elle ne se voie pas sans la competence (1.3) |
| `N08-1` | **oui, avant le premier jet** | deux taches : *le danger* (bonne reponse : une des trois situations) et *le geste* (bonne reponse : un des quatre gestes) (2.1) |
| `N08-2` | **oui, fermee ; formulation libre sous trois conditions** | *"ne pas mettre les doigts dans la prise"* est *ne pas toucher*, mais fait 8 mots (2.2) |
| `N08-3` | **reformulee** | les mains mouillees vont dans l'enonce ; la proposition porte le geste, en 6 mots au plus (2.3) |
| `N08-4` | **reformulee** | un preventif ne decrit pas l'accident, mais les mots *electrocution*, *brulure*, *mort* et leur famille sont refuses meme au preventif ; *se faire mal*, *se blesser*, *c'est dangereux* passent (2.4) |
| Questions de mon audit (a, b, c) | **ajoutees** | indice qui nomme la reponse, distracteurs reconnaissables sans la competence (forme, matiere, grammaire), source de chaque objet et de chaque geste (1.4, 2.5) |
| Cout | **aucun texte n'en fixe le prix** | duree mesuree en section 6 ; je ne la convertis pas |

---

## 1. Lot `-06` (`QLM-CE1-06`, `ce1-qlm-aimant`)

### 1.1 `N06-1` : la seconde tache

**Deux items opposes sur le meme objet ne sont pas deux taches.** `FER-3` porte sur l'operation demandee, pas sur la forme de la
phrase (s28, section 2, cite dans mon avis de `-02`, section 2). *"Lequel l'aimant attire-t-il ?"* et *"Lequel l'aimant
n'attire-t-il pas ?"* demandent la meme chose : juger chacun des trois objets, attire ou non, et prendre celui qui est seul de son
cote. Ce sont les deux moities de `FER-1` (critere 4), servies par une seule tache, que j'appelle **A, l'objet**.

**Les secondes taches que j'ai ecartees, et pourquoi** :

- *De l'observation a la matiere* (*"L'aimant attire cet objet. En quoi est-il fait ?"*) : la seule bonne reponse possible est *en
  fer* (le seul metal attire que je laisse lire a un enfant, voir regle 6) ; une tache dont la bonne reponse est toujours la meme
  est une devinette des le deuxieme item.
- *Une question oui ou non sur un objet nomme* : il faut trois propositions (`TRV-CE1-2`), et la troisieme d'une question a deux
  reponses est absurde (defaut b).
- *L'usage de l'aimant* (*"Que prend-on pour ramasser des epingles ?"*, bonne reponse *un aimant*) : la bonne reponse n'est pas un
  objet attire ou non, c'est une competence voisine, et c'est l'item de `cm1-sciences-melanges` (*"utiliser un aimant"*).
- *"Pourquoi l'aimant attire-t-il le trombone et pas le papier d'aluminium ?"* : pour un enfant qui ne sait pas que l'aluminium
  est un metal, *"le trombone est en metal"* est une seconde reponse defendable.

**La seconde tache : B, le groupe.** L'enonce dit que tous les objets sont en metal et demande dans quel groupe l'aimant attire
les trois objets ; chaque proposition est un groupe de trois objets de la liste, sans article, separes par des virgules
(*"clou, trombone, aiguille"*), comme les suites de trois mots de `-01`. La bonne reponse est le groupe de trois objets en fer ;
chaque groupe distracteur porte deux objets en fer et un objet en aluminium ou en cuivre.

**Pourquoi c'est une autre operation.** En A, l'enfant peut repondre en reconnaissant un seul objet qu'il sait attire, sans juger
les deux autres. En B, il doit juger les neuf objets et rejeter chaque groupe des qu'un de ses objets ne l'est pas : c'est une
verification de tout un ensemble, pas un choix d'un objet. C'est la paire que j'ai retenue a `-01` (l'etape voisine, puis ranger
trois etapes). Et B sert la ligne au plus pres : un enfant qui croit que l'aimant attire les metaux voit trois groupes de metal et
ne peut pas choisir.

**Ce que je n'ai pas ecarte et que je ne retiens pas pour autant** : la variante *"dans quel groupe l'aimant n'attire aucun
objet"*. Elle demande un groupe de trois objets non attires en metal, et la liste n'en a que trois (regle 6) : ce serait le meme
groupe a chaque item.

### 1.2 `N06-2` : ce que *cible* veut dire, et le critere 3

**Correction de mon propre ecrit.** J'ai ecrit dans `AIM-CE1-2` *"au moins un quart des items ont pour cible un objet metallique
que l'aimant n'attire pas"* sans dire *cible*. Ce que la phrase veut, son titre le dit : *"le piege est servi"*. Le piege est servi
quand un enfant qui croit que l'aimant attire les metaux ne peut pas trouver la bonne reponse par cette croyance. Aucune des trois
lectures que `camille-po` propose ne le garantit :

- *l'objet de l'enonce seul* : les items a un seul objet nomme n'ont pas trois propositions plausibles (1.1) ;
- *la bonne reponse seule* : *"Ces trois objets sont en metal. Lequel l'aimant attire-t-il ?"*, bonne reponse *le fil de fer*,
  distracteurs *le fil de cuivre*, *le papier d'aluminium*, sert le piege autant qu'un item dont la bonne reponse est le cuivre,
  et ne compterait pas ;
- *toute proposition* : l'exemple de `camille-po` (*le trombone* ; *le papier d'aluminium*, *la gomme*) compterait, alors qu'un
  enfant de CE1 ne sait pas que le papier d'aluminium est un metal : pour lui, c'est du papier, et sa croyance fausse lui donne
  *le trombone*, la bonne reponse.

**La regle (regle 1, texte en 1.6)** : un item compte pour le quart quand **tous les objets de ses propositions sont en metal**,
que **l'enonce le dit**, et qu'**au moins un est en aluminium ou en cuivre**. Avec une seule proposition acceptable, la croyance
fausse ne designe alors jamais la bonne reponse. **L'exemple de `camille-po` ne compte pas** ; il reste un item admis, qui sert
`FER-1`. Tout item de B compte, par construction.

**Le critere 3 se corrige dans l'autre sens.** `AIM-CE1-1` dit *"Exclus, parce que leur metal varie"*, sans restriction. Le
critere 3 refuse seulement l'item *"dont la cible est celui-la"*. En distracteur, une piece de monnaie est une seconde reponse
defendable (les pieces de 1, 2 et 5 centimes sont en acier recouvert de cuivre, attirees ; celles de 10, 20 et 50 centimes ne le sont pas), et une cle dans un indice
mene vers un objet dont l'enfant ne peut pas savoir le metal. **Les objets exclus le sont dans l'enonce, l'indice, l'explication et
chaque proposition.** Ce n'est pas une barre neuve : c'est ma condition, que le critere ecrivait plus etroite.

**Correction de mon constat du 2026-10-25.** Dans mon arbitrage avant ouverture, j'ai ecrit des entrees non marquees *proposee*
(dont les listes d'`AIM-CE1-1`) : *"chacune reprend ma condition ou l'illustre sans erreur"*. Le critere 3 de `-06` ne la
reprenait pas : il la restreignait a la cible. Je l'avais lu sans le voir.

### 1.3 La bonne reponse est l'intrus : forcee, et ce que j'en fais

**Oui, elle est forcee en A.** Trois propositions jugees sur un attribut a deux valeurs (attire, n'attire pas), une seule
acceptable : la bonne reponse est seule de son cote, et les deux distracteurs sont ensemble de l'autre. C'est vrai pour les deux
polarites et quels que soient les objets. `camille-po` a raison. En B, elle ne l'est pas : chaque groupe distracteur differe du bon
par un seul objet, et les trois groupes sont en metal.

**Je ne l'interdis pas.** L'interdire supprimerait la tache A, et donc `FER-1`. Je ne durcis pas non plus ma regle de `-02`, qui
reste celle de `-02`.

**Ce qui la rendait nuisible a `-02`, et ce que j'en tire ici.** A `-02`, l'enfant pouvait trouver l'intrus parce que les deux
distracteurs partageaient un trait visible sans la competence (deux animaux contre un vegetal). Sur `-06`, l'intrus n'est nuisible
que si les deux distracteurs partagent un trait que la bonne reponse n'a pas et qui se voit sans savoir ce qu'un aimant attire :
le meme nom de matiere (*le papier d'aluminium*, *la barquette en aluminium*, contre *le trombone*), la meme forme ou le meme usage
(deux feuilles contre un objet pointu). **Regle 3, texte en 1.6.** C'est le defaut b de mes lectures, ecrit avant le premier jet.
Les items ou la croyance fausse et la bonne connaissance donnent la meme reponse (*le clou* parmi *la gomme* et *la bille en
verre*) restent admis : ils servent `FER-1` et la reconnaissance des objets en fer ; ils ne comptent pas pour le quart.

**Ce que je regarderai a l'audit**, comme a `-02` : si, en jouant plusieurs manches, l'enfant trouve la bonne reponse de A avant
d'avoir lu les objets, ce sera une reserve neuve et datee.

### 1.4 Les questions que mon audit des trois premiers lots m'apprend a poser

Je ne pose que celles des trois defauts d'A.273, que mes lectures de `-01`, `-02`, `-03` ont trouves.

| Defaut | Question pour `-06` | Reponse |
|---|---|---|
| **a** indice qui donne la reponse | Un indice qui nomme la matiere de la bonne reponse (*"Cherche l'objet en fer."* quand la bonne reponse est *le fil de fer*) est-il une methode ? | **Non** : c'est la periphrase de `-03` (2.1), l'enfant traduit le mot. De meme un indice qui dit de chercher l'objet different des deux autres : il donne la structure de 1.3 au lieu de la competence. Regle 4 |
| **b** distracteur ecarte sans la competence | Un distracteur qui partage avec l'autre distracteur une matiere nommee, une forme ou un usage que la bonne reponse n'a pas ? Un objet dont l'enfant ne sait pas qu'il est en metal ? | **Refuse** dans le premier cas (regle 3) ; dans le second, l'item ne compte pas pour le quart (regle 1) |
| **b**, suite | Un objet dont la matiere varie, sans que son nom la dise (*le bouton*, *la regle*, *la bille*, *le bouchon*) ? | **Son nom porte sa matiere** : *la regle en plastique*, *la bille en verre*. C'est le motif d'`AIM-CE1-1` (*"leur metal varie"*) applique aux objets non exclus. Regle 6 |
| **c** source circulaire | Quelle source le commentaire cite-t-il pour ce qu'un aimant attire ? | **`AIM-CE1-1`** (instruction, 3.6) pour les trois classes et les objets qu'elle nomme, **et ce fichier, section 1.6, regle 6**, pour la liste fermee : un texte qui affirme les faits, pas un tableau qui demande une source ni le test du paquet. Si `marc-dev` veut un objet hors de la liste, il me le soumet avant le premier jet |

**Voisins lus, en une ligne** : aucun paquet de CP ne parle d'aimant (`grep`) ; `cp-qlm-matiere` dit le clou et la casserole *en
metal*, que `-06` ne contredit pas (`CYC-1`) ; `cm1-sciences-melanges` fait attirer des *morceaux de fer* par l'aimant, coherent
avec la regle 6.

### 1.5 Ce que je laisse a `theo-archi`

`T06-1` (les deux verifications de presence de `Q7`) : ma reponse de `Q7` exige que **la presence** de `texteSupport` et de
`texteAVoixHaute` soit refusee sur ce paquet ; *"Sans elles, la reponse de cette page ne tient pas"* vise ce refus, pas le fichier
ou il s'ecrit. Si le test transverse le fait deja sur `ce1-qlm-aimant`, ma reponse tient. Le lieu de l'assertion est a
`theo-archi`. Les gardes que je demande sont en 1.7.

### 1.6 Texte pour la story `-06`, a reporter par `camille-po`

> **Regles de contenu de `nadia-prof` avant le premier jet** [arbitre le 2026-10-26,
> `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, sections 1.1 a 1.4 ; lectures d'`AIM-CE1-1`, `AIM-CE1-2`, `FER-1`, `FER-3`,
> `TRV-CE1-2`, `TRV-CE1-5`, `QMT-CE1-2` ; aucune condition, aucun identifiant nouveau].
>
> 1. **Le quart d'`AIM-CE1-2`** (criteres 1 et 7, remplace le mot *cible*). Un item compte pour le quart quand tous les objets de
>    ses propositions sont en metal, que l'enonce le dit dans une phrase sans le verbe *attirer* (*"Ces trois objets sont en
>    métal."*, *"Tous ces objets sont en métal."*), et qu'au moins un objet est en aluminium ou en cuivre. Un objet en aluminium
>    ou en cuivre present seulement a cote d'un objet sans metal ne fait pas compter l'item.
> 2. **Deux taches (`FER-3`, critere 8).**
>    - **A, l'objet** : *"Lequel de ces objets l'aimant attire-t-il ?"* ou *"... n'attire-t-il pas ?"* ; trois objets de la liste,
>      avec leur article ; une seule proposition attiree (ou non attiree, selon la question). Les deux polarites sont la meme
>      tache. A porte `FER-1` : au moins un item dont la bonne reponse est un objet attire, au moins un dont c'est un objet non
>      attire (critere 4).
>    - **B, le groupe** : *"Tous ces objets sont en métal. Dans quel groupe l'aimant attire-t-il les trois objets ?"* ; trois
>      propositions, chacune trois objets de la liste en metal, sans article, separes par des virgules, six mots au plus
>      (`LON-CE1-5`) ; la bonne reponse est trois objets en fer ; chaque groupe distracteur porte exactement deux objets en fer et
>      un objet en aluminium ou en cuivre. Aucun item de B ne demande le groupe *"qui n'est pas attire"*.
>    - Le commentaire nomme les deux taches et leur compte ; la moins servie porte au moins 3 items, recomptes sur les items.
> 3. **Les distracteurs ne se reconnaissent pas sans la competence.** Dans un item de A, les deux distracteurs n'ont pas en
>    commun un nom de matiere, une forme, une taille ou un usage que la bonne reponse n'a pas (refuse : *le trombone* parmi *le
>    papier d'aluminium* et *la barquette en aluminium*). Dans un item de B, le bon groupe ne se reconnait pas a ce qu'il partage
>    le plus d'objets avec les deux autres.
> 4. **Indices (`TRV-CE1-5`).** Un indice ne nomme pas la matiere (*fer*, *aluminium*, *cuivre*) d'un objet de la bonne reponse,
>    et ne dit pas de chercher l'objet ou le groupe *different des autres* (*intrus*). Il donne une methode que l'enfant applique a
>    chaque objet (*"Imagine l'aimant qu'on approche de chaque objet, un par un."*).
> 5. **Objets exclus (`AIM-CE1-1`, critere 3, corrige).** Les pieces de monnaie, canettes, cles, couverts (cuillere, fourchette,
>    couteau), casseroles et bijoux (bague, collier, bracelet) ne sont nommes nulle part : ni dans l'enonce, ni dans l'indice, ni
>    dans l'explication, ni dans aucune proposition. Aucun objet en or : les objets en or qu'un enfant connait sont des bijoux ou
>    des pieces, et un objet dore n'est pas en or. Le mot *acier* n'est pas employe dans ce que l'enfant lit : un acier
>    inoxydable peut ne pas etre attire, et l'enfant ne les distingue pas ; il lit *fer*.
> 6. **Liste fermee des objets, ecrite dans le commentaire avec sa classe et sa source** (`QMT-CE1-2`). Aucun autre objet sans
>    l'accord ecrit de `nadia-prof` avant le premier jet.
>    - **Attires, en fer** : le clou, le trombone, l'épingle, l'aiguille, l'agrafe, le fil de fer.
>    - **En metal, non attires** : le papier d'aluminium, la barquette en aluminium, le fil de cuivre.
>    - **Sans metal, non attires** : la gomme, le bouchon en liège, la règle en plastique, la bille en verre, la feuille de
>      papier, l'élastique, la pâte à modeler.
>    - **Source** : `AIM-CE1-1` (`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`,
>      3.6) pour les trois classes et pour le clou, le trombone, l'epingle, l'aiguille et le papier d'aluminium ;
>      `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, section 1.6, regle 6, pour les autres objets, que `nadia-prof` tient pour
>      exacts au CE1. Jamais le test du paquet.
>
> **Lu par `nadia-prof`, aucun test** (`QC9`) : l'exactitude de chaque objet contre sa classe ; la forme, la taille et l'usage de
> la regle 3 ; le partage d'objets des groupes de B ; qu'un indice soit une methode ; les paraphrases d'`AIM-CE1-2` (1.7).

### 1.7 Gardes demandees a `theo-archi` pour `-06`, et ce que chacune doit refuser

La forme est a `theo-archi` ; une garde qu'il ne juge pas possible s'ecrit *"non garde : <identifiant>"* et je la lis.

| Garde | Doit refuser | Doit passer |
|---|---|---|
| 1. liste fermee (regle 6) | un objet hors liste en proposition (*la vis*), y compris dans un groupe de B ; un objet de la liste sans sa ligne de classe et de source dans le commentaire | *le clou* ; *"clou, trombone, aiguille"* |
| 2. exclus (regle 5, critere 3) | *"La cuillère est en métal. L'aimant l'attire-t-il ?"* ; *la pièce de monnaie* en distracteur ; l'indice *"Pense aux clés."* ; *en or* ; *acier* dans un champ lu par l'enfant | les mots qui portent ces lettres : *couvercle*, *baguette*, *orange*, *encore* |
| 3. une seule acceptable (A) | *"Lequel de ces objets l'aimant attire-t-il ?"*, *le clou* ; *le trombone*, *la gomme* (deux attires) ; la meme question au negatif avec deux objets non attires | *le trombone* ; *le papier d'aluminium*, *la gomme* (sonde 12 de l'avis de `theo-archi`) |
| 4. le quart (regle 1) | un paquet de 12 items dont 2 comptent ; un paquet de 13 items dont 3 comptent | 12 items dont 3 comptent ; l'item de `camille-po` ne compte pas ; *"Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"*, *le fil de cuivre* ; *le fil de fer*, *l'agrafe* compte |
| 5. `FER-1` (critere 4) | un paquet dont toutes les bonnes reponses de A sont attirees, ou toutes non attirees | - |
| 6. `FER-3` (regle 2) | un paquet dont B porte 2 items ; un item qui n'est ni A ni B | - |
| 7. composition de B | un groupe distracteur qui porte *la gomme* ; un bon groupe qui porte *le fil de cuivre* ; un groupe distracteur a deux objets non attires | *"clou, trombone, aiguille"* ; *"agrafe, fil de cuivre, épingle"* ; *"clou, papier d'aluminium, aiguille"* |
| 8. indice (regle 4) | *"Cherche l'objet en fer."* quand la bonne reponse est *le fil de fer* ; *"Cherche l'intrus."* ; *"Trouve l'objet différent des deux autres."* | *"Imagine l'aimant qu'on approche de chaque objet, un par un."* |
| 9. matiere partagee (regle 3, partie lexicale) | *le trombone* ; *le papier d'aluminium*, *la barquette en aluminium* | *le fil de fer* ; *le fil de cuivre*, *le papier d'aluminium* |
| 10. garde d'`AIM-CE1-2` (avis de `theo-archi`) | inchangee, quinze sondes | **deux sondes de plus a faire passer** : *"Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"* ; *"Tous ces objets sont en métal. Dans quel groupe l'aimant attire-t-il les trois objets ?"* (aucune ne dit que l'aimant attire les metaux ; executees avec les trois motifs de la section 1 de l'avis recopies, dans un programme hors depot, JDK 21.0.10 : les deux passent, et les sondes 1, 3 et 7 de l'avis rendent refusee, refusee, passe, comme l'avis ; elles mordent le jour ou une garde trop large est ecrite) |

**Ce dont je reste la seule garde sur `-06`, quelle que soit la forme** : l'exactitude de chaque objet ; la forme, la taille et
l'usage (regle 3) ; le partage d'objets en B ; qu'un indice soit une methode au-dela des mots de la garde 8 ; et les quatre cas
d'`AIM-CE1-2` que la garde ne voit pas (paraphrases sans *attirer*, metaux nommes presentes comme attires, designation hors des
quatre formes, negation fausse sans designation), plus un indice qui dit seulement d'ecarter ce qui n'est pas en metal sans dire
que tous les metaux ne sont pas attires : il pousse vers la regle fausse sans l'ecrire. **Aucune n'est couverte par un test.**

---

## 2. Lot `-08` (`QLM-CE1-08`, `ce1-qlm-dangers-electricite`)

### 2.1 `N08-1` : j'arbitre avant le premier jet, et les deux taches

**Oui, avant le premier jet.** A `-02`, l'arbitrage prealable a evite le tour de correction ; a `-08`, le critere 5 laisse le
choix au developpeur et je vois deja ce qui coincerait.

**Correction de mon propre ecrit.** `DEL-CE1-1` dit d'abord *"La bonne reponse est ne pas toucher, ne pas approcher de l'eau, ne
pas brancher avec les mains mouillees, ou prevenir un adulte"*, puis *"Les trois situations de la parenthese en bonne reponse
(`FER-1`)"*. Lues dans une seule tache, les deux phrases se contredisent : une bonne reponse ne peut pas etre a la fois un geste et
une situation. Le critere 1 de la story les a conciliees en mettant la situation dans l'enonce et le geste en bonne reponse ; cette
lecture laisse une seule tache possible, puisque toute bonne reponse serait un geste. **Ma lecture de mes mots** : la premiere
phrase dit ce qu'est la bonne reponse quand l'item demande un geste ; la seconde demande des items dont la bonne reponse est la
situation. Ce sont les deux moities du libelle, *"les dangers ... et les gestes qui les evitent"*, et ce sont les deux taches.

- **A, le danger** : l'enonce demande quelle situation de la maison est dangereuse ; la bonne reponse est une des trois situations
  de la parenthese (une prise abimee ou cassee, un appareil branche pres de l'eau, un fil electrique abime) ; les deux
  distracteurs sont des choses electriques de la maison dans un etat sans danger. Chaque situation est la bonne reponse d'au moins
  un item de A (`FER-1`, a la lettre).
- **B, le geste** : l'enonce nomme un personnage et une des trois situations, et demande ce qu'il doit faire ; la bonne reponse
  est un des quatre gestes ; les distracteurs sont des gestes de reparation, de manipulation ou d'imprudence. Le critere 1 reste :
  chaque situation est aussi celle d'au moins un item de B.

**Deux operations** : en A, l'enfant juge un etat (y a-t-il un danger ?) ; en B, il choisit une conduite devant un danger qu'on
lui donne. Savoir qu'un fil abime est dangereux ne dit pas qu'il faut prevenir un adulte plutot que le reparer.

**Intrus** (`camille-po`, section 9 : *"possible, pas forcee"*) : en A elle est forcee (dangereux ou non, une seule acceptable),
comme en `-06` ; en B elle l'est aussi des que les deux distracteurs sont des gestes faux et la bonne reponse le seul juste. Meme
traitement qu'en 1.3 : je ne l'interdis pas, je demande qu'elle ne se voie pas sans la competence (regle 3 de 2.6), et la forme la
plus visible a `-08` est la grammaire (2.5).

### 2.2 `N08-2` : la liste des quatre gestes, et leur formulation

**La liste est fermee** : en B, la bonne reponse est un des quatre gestes, et rien d'autre. *S'eloigner*, *attendre*, *ne rien
faire* ne sont pas des bonnes reponses. Le motif est l'exactitude et `TRV-CE1-2` : un geste juste hors de la liste ne peut pas
non plus etre un distracteur, sinon l'item a deux reponses ; la liste fermee rend ce partage lisible.

**La formulation est libre a trois conditions** : elle nomme un seul des quatre gestes, applique a l'objet de la situation ; elle
reste une abstention ou une alerte, jamais un geste sur l'appareil ; elle tient en six mots (`LON-CE1-5`, `PaquetLu.mots`).

- *"ne pas mettre les doigts dans la prise"* **est** *ne pas toucher* (toucher l'interieur de la prise, du doigt ou d'un objet),
  mais fait **8 mots**. Formes admises : *"ne rien mettre dans la prise"* (6), *"ne pas toucher la prise"* (5).
- *Ne pas toucher* : *"ne pas toucher le fil"* (5), *"laisser le fil sans le toucher"* (6).
- *Ne pas approcher de l'eau* : *"garder l'appareil loin de l'eau"* (5), *"ne pas l'approcher de l'eau"* (5).
- *Prevenir un adulte* : *"prévenir un adulte"* (3), *"appeler un adulte"* (3), *"le dire à un adulte"* (5).

**Un seul des quatre gestes par item** : devant un fil abime, *ne pas toucher* et *prevenir un adulte* sont tous deux justes ; ils
ne sont jamais ensemble parmi les propositions.

### 2.3 `N08-3` : le geste de sept mots

**Ma phrase ne va pas telle quelle en proposition.** Les mains mouillees sont une donnee de la situation : elles vont dans
l'enonce (*"Inès a les mains mouillées et veut brancher la lampe. Que doit-elle faire ?"*). La proposition porte le geste :
*"attendre d'avoir les mains sèches"* (5), *"se sécher d'abord les mains"* (5), ou *"ne pas brancher la lampe"* (5).

**Deux formes refusees** : *"ne pas brancher les mains mouillées"*, ou *les mains mouillees* se lit comme le complement de
*brancher* ; et *"ne pas brancher"* seul quand l'enonce ne dit pas les mains mouillees, qui deviendrait une regle fausse (on branche
une lampe avec les mains seches).

### 2.4 `N08-4` : une mention preventive decrit-elle l'accident ?

**Elle ne le decrit pas, mais elle le nomme, et c'est le mot que je refuse.** *"Pour ne pas se bruler"* ne raconte rien et ne
blesse personne ; il met pourtant dans la tete de l'enfant l'image que `DEL-CE1-2` ecarte (*"l'item enseigne le geste sans mettre
en scene l'accident"*). **Ma lecture, ecrite avant le premier jet pour qu'on ne la deduise pas** : les trois familles de mots que
ma condition nomme, *electrocution* (*electrocuter*), *brulure* (*bruler*), *mort* (*mourir*, *tuer*), ne sont employees dans aucun
champ, meme au preventif ou au conditionnel. Le danger se dit en termes generaux : *"c'est dangereux"*, *"l'électricité peut faire
très mal"*, *"pour ne pas se faire mal"*, *"pour ne pas se blesser"*. C'est la forme que j'ai validee sur
`cp-eps-regles-de-securite` (*"pour éviter de se blesser en touchant le fond"*). Un personnage nomme n'est jamais blesse, meme
sans ces mots (*"Lina s'est blessée"*).

C'est la lecture la plus stricte des deux possibles. Je la choisis parce qu'aucun item n'existe encore, et qu'un enfant de CE1 joue
ce paquet sans adulte : la premisse de l'adulte ne vaut pas pour questionner le monde CE1 (`reponse-nadia-aim-ce1-2-q7.md`,
section 1).

### 2.5 Les questions que mon audit des trois premiers lots m'apprend a poser

| Defaut | Question pour `-08` | Reponse |
|---|---|---|
| **a** indice qui donne la reponse | Un indice de B qui nomme le geste ou son verbe (*"Ne touche à rien."*, *"Pense à un adulte."*) ? Un indice de A qui nomme l'etat dangereux (*"Regarde ce qui est près de l'eau."* quand la bonne reponse est l'appareil pres de l'eau) ? | **Periphrases**, comme a `-03` (2.1). L'indice de B montre ou est le danger dans la situation, sans nommer le geste ; l'indice de A donne une maniere de comparer les trois situations, sans nommer l'etat de la bonne reponse. Regle 4 |
| **b** distracteur ecarte sans la competence | Les quatre gestes sont trois negations et une alerte ; les distracteurs naturels sont des actions affirmatives. L'enfant trouve-t-il *"ne pas toucher le fil"* parce que c'est la seule proposition en *ne pas* ? | **Oui, c'est le piege de grammaire** de mon avis de `-02` (*"sinon la grammaire designe la reponse"*). La bonne reponse n'est jamais la seule des trois a commencer par *ne pas*, *ne rien* ou *ne jamais* : forme affirmative (2.2) ou distracteur negatif. Regle 3 |
| **b**, suite | Un distracteur qui est une abstention (*"s'éloigner du fil"*, *"continuer à jouer ailleurs"*) ? Un distracteur absurde (*"chanter devant la prise"*) ? | **Refuses tous les deux** : le premier est une seconde reponse defendable, le second s'ecarte sans rien savoir. Les distracteurs de B sont des gestes qu'un enfant pourrait vraiment faire (*"réparer le fil avec du scotch"*, *"toucher le fil pour voir"*, *"débrancher en tirant sur le fil"*). En A, les deux distracteurs ne partagent pas un trait que la bonne reponse n'a pas (deux fois *en bon état* contre *abîmé*) |
| **c** source circulaire | Quelle source pour les situations et les gestes ? | **`DEL-CE1-1`** (instruction, 3.8), qui les affirme. Tout autre fait ecrit dans un indice ou une explication porte sa source hors du paquet, ou le commentaire ecrit en toutes lettres qu'il n'en a pas (A.273, ligne c) ; jamais le test du paquet |

**Voisins lus, en une ligne** : aucun paquet de CP ne sert l'electricite de la maison (`grep` d'*electri* : seul
`cp-qlm-autrefois-aujourdhui` nomme des appareils electriques, comme objets d'hier et d'aujourd'hui) ; `cp-eps-regles-de-securite`
n'a aucun item electrique, et sa forme (*se blesser*, *porter secours*) fonde 2.4.

### 2.6 Texte pour la story `-08`, a reporter par `camille-po`

> **Regles de contenu de `nadia-prof` avant le premier jet** [arbitre le 2026-10-26,
> `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, sections 2.1 a 2.5 ; lectures de `DEL-CE1-1`, `DEL-CE1-2`, `FER-1`, `FER-3`,
> `TRV-CE1-2`, `TRV-CE1-5`, `TRV-CE1-6`, `LON-CE1-5`, `QMT-CE1-2` ; aucune condition, aucun identifiant nouveau ; remplace le
> choix laisse au developpeur par le critere 5].
>
> 1. **Deux taches (`FER-3`).**
>    - **A, le danger** : l'enonce demande quelle situation de la maison est dangereuse ; la bonne reponse est une prise abimee ou
>      cassee, un appareil branche pres de l'eau, ou un fil electrique abime ; les deux distracteurs sont des choses electriques de
>      la maison dans un etat sans danger. Chaque situation est la bonne reponse d'au moins un item de A (`FER-1`).
>    - **B, le geste** : l'enonce nomme un personnage et une des trois situations et demande ce qu'il doit faire ; la bonne
>      reponse est un des quatre gestes de `DEL-CE1-1` ; chaque situation est celle d'au moins un item de B (critere 1).
>    - Chaque item declare sa tache et sa situation a cote de lui ; le commentaire nomme les deux taches et leur compte ; la moins
>      servie porte au moins 3 items, recomptes sur les items.
> 2. **Les quatre gestes, liste fermee ; formulation libre a trois conditions** : un seul geste, applique a l'objet de la
>    situation ; une abstention ou une alerte, jamais un geste sur l'appareil ; six mots au plus. Formes admises par `nadia-prof` :
>    *"ne pas toucher le fil"*, *"ne pas toucher la prise"*, *"ne rien mettre dans la prise"*, *"laisser le fil sans le toucher"* ;
>    *"garder l'appareil loin de l'eau"*, *"ne pas l'approcher de l'eau"* ; *"attendre d'avoir les mains sèches"*, *"se sécher
>    d'abord les mains"*, *"ne pas brancher la lampe"* (quand l'enonce dit les mains mouillees) ; *"prévenir un adulte"*,
>    *"appeler un adulte"*, *"le dire à un adulte"*. Une autre forme se soumet a `nadia-prof` avant le premier jet. Les mains
>    mouillees vont dans l'enonce ; *"ne pas brancher les mains mouillées"* et *"ne pas brancher"* sans mains mouillees dans
>    l'enonce sont refuses.
> 3. **Distracteurs (`TRV-CE1-2`).** Jamais deux des quatre gestes dans un meme item. Jamais une abstention en distracteur
>    (*s'eloigner*, *attendre*, *continuer a jouer ailleurs*) : elle serait une seconde reponse. Les distracteurs de B sont des
>    gestes de reparation, de manipulation ou d'imprudence qu'un enfant pourrait faire. La bonne reponse n'est jamais la seule des
>    trois propositions a commencer par *ne pas*, *ne rien* ou *ne jamais*. En A, les deux distracteurs n'ont pas en commun un mot
>    ou un trait que la bonne reponse n'a pas.
> 4. **Indices (`TRV-CE1-5`).** L'indice de B ne nomme ni le geste ni son verbe (*toucher*, *approcher*, *brancher*, *sécher*,
>    *prévenir*, *adulte*) ; il montre ou est le danger dans la situation. L'indice de A ne nomme pas l'etat de la bonne reponse
>    (*abîmé*, *cassé*, *eau*).
> 5. **`DEL-CE1-2`, les mots.** Les familles *électrocution* (*électrocuter*), *brûlure* (*brûler*) et *mort* (*mourir*, *tuer*)
>    ne sont employees dans aucun champ, meme au preventif (*"pour ne pas se brûler"* est refuse). Le danger se dit en termes
>    generaux (*"c'est dangereux"*, *"pour ne pas se faire mal"*, *"pour ne pas se blesser"*). Aucun personnage nomme n'est
>    blesse.
> 6. **`DEL-CE1-1`, aucune pile.** Le mot *pile* n'est employe dans aucun sens, *pile ou face* compris : rien dans cette ligne n'en
>    a besoin.
> 7. **`TRV-CE1-6`.** La situation est vecue par un personnage nomme, dans *une maison* ou *la cuisine*, jamais *chez toi*, *ta
>    maison*, *ta chambre* ; aucun objet n'appartient a l'enfant ; *le lavabo* ou *l'évier*, pas *la baignoire*, que tous les
>    logements n'ont pas.
> 8. **Source (`QMT-CE1-2`).** Les situations et les gestes ont pour source `DEL-CE1-1`
>    (`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 3.8). Tout autre fait porte sa
>    source hors du paquet, ou le commentaire ecrit qu'il n'en a pas ; jamais le test du paquet.
>
> **Lu par `nadia-prof`, aucun test** (`QC9`) : qu'une situation de A soit vraiment dangereuse et ses distracteurs vraiment sans
> danger ; qu'un distracteur de B soit plausible et faux ; qu'un indice soit une methode ; le sens de `DEL-CE1-2` au-dela des mots
> de la regle 5 ; les traits partages de la regle 3 au-dela de la grammaire.

### 2.7 Gardes demandees a `theo-archi` pour `-08`, et ce que chacune doit refuser

| Garde | Doit refuser | Doit passer |
|---|---|---|
| 1. `FER-3` et `FER-1` (regle 1, `T08-1`) | un paquet dont A porte 2 items ; un item de A dont la bonne reponse est un geste ; une situation qui n'est la bonne reponse d'aucun item de A, ou la situation d'aucun item de B | - |
| 2. gestes (regle 2, `T08-2`, critere 2) | la bonne reponse *"réparer le fil"* ; *"débrancher l'appareil qui fume"* ; une bonne reponse de B hors des formes admises | les formes de la regle 2 |
| 3. un seul geste (regle 3) | un item dont deux propositions sont des formes admises (*"ne pas toucher le fil"* et *"prévenir un adulte"*) | - |
| 4. grammaire (regle 3) | bonne reponse *"ne pas toucher le fil"*, distracteurs *"réparer le fil avec du scotch"*, *"toucher le fil pour voir"* | bonne reponse *"laisser le fil sans le toucher"*, memes distracteurs |
| 5. longueur (regle 2) | deja tenue par la garde transverse de `LON-CE1-5` : *"ne pas brancher avec les mains mouillées"* (7) | *"attendre d'avoir les mains sèches"* (5) |
| 6. pile (regle 6, `T08-3`) | *pile*, *piles*, *pile ou face*, dans tout champ | *empiler*, *pilote* |
| 7. `DEL-CE1-2` (regle 5, `T08-4`, critere 4) | *"Lina s'est électrocutée en touchant la prise."* ; *"pour ne pas se brûler"* ; *"Un fil abîmé peut tuer."* ; *"On peut mourir en touchant un fil abîmé."* ; *"Sami s'est blessé à la main."* | *"C'est dangereux."* ; *"pour ne pas se faire mal"* ; *"pour ne pas se blesser"* ; *"un fil électrique abîmé"* |
| 8. `TRV-CE1-6` (regle 7) | *"Dans ta chambre, ..."* ; *"chez toi"* ; *"près de la baignoire"* | *"Dans la cuisine, ..."* |

**Ce dont je reste la seule garde sur `-08`** : la realite du danger en A et son absence dans les distracteurs ; la plausibilite
des distracteurs de B et leur faussete ; les abstentions deguisees en distracteur ; la methode de chaque indice ; `DEL-CE1-2` au
sens (une scene d'accident sans aucun des mots de la garde 7). **Aucune n'est couverte par un test.**

---

## 3. Ce que je corrige de mes propres ecrits, rassemble

1. `AIM-CE1-2` employait *cible* sans le definir : regle 1 de 1.6.
2. Le 2026-10-25, j'ai lu le critere 3 de `-06` comme reprenant `AIM-CE1-1` ; il la restreignait a la cible (1.2).
3. `DEL-CE1-1` porte deux phrases qui, lues dans une seule tache, se contredisent ; elles decrivent deux taches (2.1).
4. Le geste *"ne pas brancher avec les mains mouillees"*, que j'ai ecrit, depasse `LON-CE1-5` en proposition (2.3).

## 4. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni les
stories, ni un paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre. Il ne decide la forme d'aucune garde.

## 5. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | reporter 1.6 dans `S57-BR-QLM-CE1-06` (criteres 1, 3, 7, 8) et 2.6 dans `S57-BR-QLM-CE1-08` (critere 5) par annotation datee |
| `theo-archi` | 1.7 et 2.7, dont les deux sondes *doit passer* de la ligne 10 de 1.7 pour la garde d'`AIM-CE1-2` ; `T06-1` (1.5) |
| `marc-dev`, par `sofia-sm` | 1.6 et 2.6 avant les premiers jets ; l'autocontrole d'A.273 (a, b, c) porte aussi sur les regles 3 et 4 de chaque lot |
| `sofia-sm` | la duree de la section 6 |

## 6. Cout et duree

**Duree**, mesuree par `date` : debut **2026-10-26 16:28:12**, ecriture de ce fichier a partir de 16:46:51, fin **16:56:42**,
avant le commit. **Duree : 28 min 30 s**, dont deux mesures : les sondes de la ligne 10 de 1.7 (programme hors depot) et le
garde-fou des identifiants (`bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` :
*"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; il n'ecrit aucun
identifiant neuf), et `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les
fichiers verifies."*

**Au bareme** : mon bareme (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"* ; fixe 1,0 dont
l'arbitrage prealable de l'instance a 0,5) ne nomme pas un arbitrage avant premier jet, et je n'ai trouve aucune ligne de
`planning.md` qui en fixe le prix, pas meme pour celui de `-02`. Je ne lui fixe pas de prix et je ne convertis pas une duree en bareme :
aucune regle ne le fait. Ce qu'il peut eviter est connu par les textes, sans promesse (A.273 : *"ne promet aucun chiffre"*) : une
seconde lecture avant `verify` vaut 0,50 hors marge (A.270, point 4) sous un plafond de 22,20 (A.273). Le classement est a
`camille-po`.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
