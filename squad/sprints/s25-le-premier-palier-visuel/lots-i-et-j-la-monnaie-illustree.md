# Lots I et J - la monnaie illustree, avec ses comptes et leur methode

- **Auteure** : `sofia-sm`, **casquette developpeuse**. Ce document ne prononce aucun Done, n'arbitre
  aucune condition pedagogique et ne clot aucun obstacle.
- **Date** : 2026-08-31. **Sprint** : s25. **Branche** : `s25-sofia-dev`, worktree dedie ouvert sur
  `origin/s25-sofia` a `eec6095`, lignee verifiee avant le premier commit.
- **Story** : `S25-MON-1`, rouverte sur sa forme illustree (`DEC-0065`, second amendement).
- **Conditions appliquees, jamais rediscutees** : `MON-1` a `MON-5`, `CON-1` a `CON-5`, `ADR-0073`
  clauses 1 a 6, `ADR-0036` clause 5, `DES-1` a `DES-7` d'`ines-design`.

---

## 1. Ce qui est livre

| Lot | Contenu | Fichiers |
|---|---|---|
| **I** | Les douze dessins de la monnaie | `adapters/adapter-web/src/main/resources/static/illustrations/monnaie-*.svg` |
| **J** | Le paquet et son entree de catalogue | `contenu/cp-monnaie.yaml`, `catalogue.yaml`, `PaquetCpMonnaieTest.java` |
| **I + J** | Le cliquet de zero leve, le cliquet de poids deplace | `LIllustrationDeContenuResoutEtAucuneNeDortTest.java` |

**Les deux lots sont dans le meme commit**, et ce n'est pas un choix de confort : le garde-fou
bidirectionnel d'`ADR-0073` refuse une illustration deposee sans paquet qui la declare **et** un
identifiant declare qui ne resout pas. Chacun des deux lots, seul, rend la porte technique rouge.

**Le lot A est un ancetre de cette branche.** `origin/s25-marc` a `6080551` est fusionne ici : les
lots I et J ne compilent pas sans le champ d'illustration qu'il apporte. **Les lots E et H que
`marc-dev` a pousses depuis ne sont pas dans cette branche**, et le lot de `lea-dev` non plus : ce
sont leurs paires, elles se fusionnent separement.

---

## 2. Les comptes, et la methode avec chacun

### 2.1 Porte technique - mesuree par moi sur l'arbre fusionne

```
mvn -B clean verify
```

**BUILD SUCCESS, 963 tests, 0 echec.** Perimetre : **le reacteur complet a la racine**, aucun `-pl`,
donc aucun `-am` a citer. Detail par module, lu dans le journal du build et non reconstitue :
80 (`domain`) + 13 (`application`) + 351 (`adapter-content`) + 366 (`adapter-web`) + 147
(`bootstrap`) + 6 = **963**.

### 2.2 L'ecart de +26 avec l'ouverture, attribue par la mesure et non par une repartition

**J'ai mesure chaque palier au lieu de deduire ma part.** Chaque ligne est une execution complete du
reacteur, sur un arbre nomme.

| Arbre | Total | Ce que la ligne ajoute |
|---|---|---|
| Ouverture, `eec6095` | **937** | reference du planning |
| Apres le lot F | **937** | **+0.** Le lot F ne touche aucun module de production ; ses neuf juges purs sont des scripts, pas des tests Maven |
| Apres la fusion du lot A, `c06d6a6` | **952** | **+15**, et ils sont a `marc-dev` |
| Apres les lots I et J | **963** | **+11**, et ils sont a moi |

**Les 11 se recomptent un par un** : 9 dans `PaquetCpMonnaieTest`, 2 dans le garde-fou d'illustration
- le cliquet de poids et sa morsure. Le cliquet de zero, lui, n'ajoute aucun test : il change ce que
le test existant epingle.

*La mesure du palier `c06d6a6` a ete faite sur un worktree jetable ouvert sur ce commit, et non
deduite d'une soustraction.*

*Note de methode sur la commande* : l'enveloppe `outils/mvn-jdk21.sh` **refuse** desormais sur ce
poste, et c'est le lot F qui le lui a appris - la toolchain jdk 21 du fichier de l'utilisateur y
designe un JDK qui rapporte 25.0.2. `mvn -B clean verify` est la commande de la porte technique et
elle n'a jamais eu ce probleme : le reacteur y est complet par construction. La machine virtuelle
qui a execute ces 963 tests **rapporte d'elle-meme** la version majeure que le pom exige - verifie a
cette execution par `LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest` (`ADR-0071`),
jamais lu dans une declaration de toolchain.

### 2.3 Le paquet - douze items, et chaque condition avec son compte

| Condition | Compte | Methode |
|---|---|---|
| Volume | **12 items**, plus que les 10 manches du catalogue | Comptes sur le paquet charge par le chargeur reel |
| **`MON-1`** | **0 enonce sur 12** porte un chiffre ; 0 nomme une valeur en toutes lettres | Deux tests exhaustifs de `PaquetCpMonnaieTest` |
| **`MON-2`** | **5 valeurs servies** : pieces de 1 et 2 euros, billets de 5, 10 et 20 euros. Aucune autre | Termes des propositions des items de composition, verifies un par un |
| **`MON-3`** | **5 items sur 12, soit 41,7 %**, plancher a 33,3 % | L'appartenance a la famille "composer" est **declaree** dans le test par l'enonce, jamais devinee sur la forme de la reponse |
| **`MON-4`** | **somme maximale servie : 20 euros**, exactement a la borne. Aucun centime, ni en mot ni en virgule decimale | Sommes calculees sur les **trois** propositions de chaque item, pas sur la seule bonne reponse |
| **`MON-5`** | *non automatisable* | Aucune situation n'est ecrite : les enonces posent une question sur ce qui est montre, sans acheteur, sans magasin et sans argent de poche |
| **`CON-2`** | **0 enonce sur 12** ne nomme une quantite, une couleur ou une forme | Jeu de mots temoins declare dans le test |
| **`CON-3`** | **12 illustrations distinctes pour 12 items** | Aucune donnee n'est a la fois dans l'image et dans le texte : les enonces ne portent aucun nombre |
| `ADR-0073` clause 6 | **`enonce` en premiere cle sur 12 items sur 12** | Verifie deux fois : par le garde-fou de balayage, et par l'ordre reel des cles rendu par SnakeYAML |
| `ADR-0073` clause 5 | **0 item sans texte alternatif** | |
| **`TYP-1`** | **0 occurrence neuve** dans le texte servi par ce paquet | Espace insecable devant chaque point d'interrogation, comme `cm1-problemes-durees` depuis s23. **Ce paquet ne tranche pas le choix de caractere**, qui reste ouvert chez `nadia-prof` : il suit un precedent, il n'en cree pas |
| Rang de la bonne reponse | **3 fois la plus petite, 4 fois intermediaire, 5 fois la plus grande** | Aucun rang n'est majoritaire (plafond : 6 sur 12) et aucun ne descend sous le plancher de 10 % |

### 2.4 Les dessins - douze, mesures au navigateur reel

**Outil** : `squad/outils/ines-design/mesurer-un-chiffre-trace.py`, celui d'`ines-design`, non
modifie. **Releve** : `squad/conformite/releves-s25/s25-sofia-monnaie-chiffres-graves.json`.

| `DES-` | Ce qui est exige | Resultat sur les douze |
|---|---|---|
| **`DES-1`** | Cadre de vue et dimensions en unites utilisateur | **tenue** : `viewBox`, `width` et `height` sur les douze, entiers, sans unite ni pourcentage |
| **`DES-2`** | Dimensions au meme rapport que le cadre de vue | **tenue** : le facteur est applique aux deux, jamais a une seule |
| **`DES-3`** | Hauteur declaree entre le plancher mesure et 176 px | **tenue** : **176 px declares sur les douze**, contre un plancher mesure de **34,9 px au mieux et 88,0 px au pire** |
| **`DES-4`** | Trait en unites utilisateur, aucun `vector-effect` | **tenue** : trait de 8 unites, aucun decouplage d'echelle |
| **`DES-5`** | Teinte ecrite en clair, `#1c2733` | **tenue** - et **c'etait un defaut de mes dessins**, corrige : ils declaraient `currentColor` |
| **`DES-6`** | Un chiffre n'est jamais redessine deux fois | **tenue** : **1 a 4 glyphes definis** par fichier, **2 a 8 instances** |
| **`DES-7`** | Aucun element de texte | **tenue** sur les douze, mesuree et non affirmee |

Autres mesures du meme releve :

| Ce qui est mesure | Resultat |
|---|---|
| Police appelee | **aucune** - les chiffres sont des squelettes tracees, epaissis par le trait |
| Boites de glyphe qui se recouvrent | **0, sur les douze** |
| Rapport largeur / hauteur | **1,58 au mieux, 3,85 au pire** |
| Poids, fins de ligne normalisees | **1 445** octets au plus leger, **2 685** au plus lourd, **22 912** pour les douze |

**Trois defauts ont ete trouves par la mesure et corriges avant le commit.** Je les ecris plutot que
de ne montrer que le resultat :

1. **Un recouvrement de glyphes sur `monnaie-meme-somme-e.svg`.** Les boites du `2` et du `0` de
   *20* se touchaient, trait compris. Le pas entre deux chiffres passe de 38 a 48 unites ; le
   recouvrement retombe a zero sur les douze. *Deux chiffres colles ne se lisent pas, quelle que
   soit la hauteur de l'image* - c'est le critere d'`ines-design`, pas le mien.
2. **Cinq dessins etiraient leur bourse sur une seule rangee**, jusqu'a un rapport de 6,49. A cette
   forme, un ecran etroit ne laisse plus que 46 px de hauteur rendue contre un plancher mesure a
   43 : la marge etait d'un cheveu. Les groupes de plus de trois objets se replient desormais sur
   deux rangees, et le pire rapport tombe a 3,85.
3. **Mes douze dessins declaraient `currentColor`, donc aucune teinte gouvernee.** `ines-design` a
   mesure au pixel qu'un SVG charge comme image est un **document isole**, ou `currentColor` rend du
   noir et ou une variable maison n'existe pas. Elle a corrige ses deux patrons contre elle-meme et
   pose `DES-5` ; **j'ai pris la version corrigee**, et les douze dessins declarent `#1c2733`. Sans
   sa mesure, ce lot aurait servi une teinte que personne ne gouverne, et l'aurait annoncee comme
   "aucune couleur".

**Sa mesure et la mienne convergent** : elle chiffre une piece a **43 px** de hauteur d'image
minimale ; mes trois dessins de pieces seules mesurent **43,1 px**. Deux mesures independantes sur
deux dessins differents, au dixieme de pixel.

**Les glyphes sont ceux d'`ines-design`**, recopies a l'identique. Elle avait etabli le fait qui
compte pour ce lot : *"le jeu de glyphes s'ecrit une fois pour la famille visuelle entiere, pas une
fois par paquet"*. Chaque dessin ne definit que les chiffres qu'il montre.

---

## 3. Le cliquet de zero, leve par le lot qui l'a fait rougir

`ADR-0073` avait epingle le zero et ecrit ce qui devait arriver : *"le jour ou un paquet declare une
illustration, ce test-la rougit, et celui qui pose ce paquet passe par ici plutot que de decouvrir
six mois plus tard qu'il n'etait garde par rien."* **C'est exactement ce qui s'est passe.**

Le test ne dit plus *"aucune"* mais *"exactement celles que le depot sert"* : **12 items qui
declarent une illustration, 12 fichiers sous le repertoire d'illustrations.** Le plafond vaut
toujours exactement la population, jamais un chiffre rond ni une marge.

**`lea-dev` a fait le meme geste sur le meme test, quelques heures plus tot et de la meme facon** -
mecanisme garde, sens de ce qui est epingle retourne, test non retire. **A la fusion, les deux
nombres valent 24 et 24**, et non 12 : c'est la somme des deux paires, et c'est le seul endroit du
depot ou les deux chaines visuelles se croisent.

---

## 4. Le cliquet de poids - critere 11 execute : je le deplace, avec motif et valeur mesuree

**`lea-dev` l'a pose la premiere**, dans
`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (adapter-web), qu'elle
a nomme foyer commun des deux chaines : **1 112 octets pour un dessin, 6 206 pour le repertoire**,
fins de ligne normalisees. **Mes douze ne les tiennent pas**, et le critere 11 - que j'ai ecrit
moi-meme - dit ce qu'il faut faire alors : *respecter, ou deplacer dans le meme commit, avec motif
ecrit et valeur mesuree.*

**Le motif, et il n'est pas une negligence de trace** : une figure plane est **un** trace - un
rectangle, un cercle, un triangle. Une scene de monnaie porte **jusqu'a six objets**, dont chacun
demande un contour, un contour interieur et une a deux instances de glyphe, plus un trait de
separation. Le trace est plus gros par construction. J'ai neanmoins ramene mes en-tetes de
commentaire au format des siens - de neuf lignes a six - ce qui fait tomber le plus lourd fichier de
3 824 a **2 685 octets**.

**Les valeurs mesurees, fins de ligne normalisees, a reprendre telles quelles** :

| Corpus | Fichiers | Total | Le plus lourd |
|---|---|---|---|
| `FIG`, `lea-dev` | 12 | 6 206 | 1 112 |
| `MON`, moi | 12 | **22 912** | **2 685** (`monnaie-meme-somme-e.svg`) |
| **Arbre fusionne des deux paires** | **24** | **29 118** | **2 685** |

> **Ce sont ces deux nombres - 2 685 et 29 118 - que son garde-fou doit porter apres la fusion.**

**Ce que le cliquet mesure, et `ines-design` demande que ce soit dit** : les octets **commites**,
en-tete de commentaire compris, pas les octets de trace. Ses fichiers pesent 275 octets de trace
pour 1 084 commites, et *"un seuil pose sur l'un ne veut rien dire sur l'autre, et le cliquet doit
dire lequel il mesure"*. Les octets commites sont ce que l'enfant telecharge ; c'est ce que les deux
cliquets comptent, et c'est ecrit dans les deux.

**Mon cliquet est un doublon assume et il est nomme comme tel dans sa Javadoc.** Il existe pour que
la dette d'`ADR-0036` clause 5 soit payee sur cette branche prise seule, quel que soit l'ordre de
fusion des deux paires. **Celui de `lea-dev` est celui qui survit** - il cliquete en plus le poids
total du repertoire - et le mien part avec la fusion.

---

## 5. Ce que la porte technique a trouve, et que je n'avais pas vu

Quatre rouges, tous rattrapes avant le commit. Chacun est un garde-fou qui a fait son travail.

1. **Mon propre test a rougi sur zero item.** `MON-3` comptait 0 item sur 12 : le YAML pose une
   espace **insecable** devant son point d'interrogation, ma constante Java une espace ordinaire.
   Le caractere invisible est desormais ecrit en echappement dans la source Java, avec son motif -
   *un caractere invisible dans une source se compare mal a l'oeil*.
2. **La clause 4 a rougi sur les douze dessins.** L'en-tete de commentaire de chaque fichier ecrivait
   les noms des deux balises interdites sous leur forme litterale, pour dire qu'elles etaient
   absentes. **Le garde-fou lit le fichier entier, commentaires compris, et il a raison de ne pas
   savoir lire un commentaire** : un garde-fou qui exempterait les commentaires exempterait aussi ce
   que quelqu'un y cacherait. Ce sont mes en-tetes qui ont change, pas le garde-fou. *`lea-dev` a
   rencontre exactement le meme rouge sur la meme ligne de commentaire.*
3. **Quatre chaines neuves exposees par le port entrant** - l'identifiant du jeu, son titre, sa
   consigne et le libelle de competence du descripteur - n'etaient pas dans l'inventaire de
   `TexteAccentueExposeParLePortEntrantTest`. Il est etendu dans le meme commit, avec le motif de
   chaque entree, comme sa Javadoc l'exige.
4. **La teinte non gouvernee**, section 2.4 point 3. Celui-la, aucun garde-fou du depot ne l'a
   trouve : c'est la mesure d'`ines-design` qui l'a trouve, et son garde-fou de geometrie
   l'attrapera desormais.

---

## 6. Ce que je ne fais pas

- **Je ne prononce aucun Done**, ni technique ni pedagogique.
- **Je ne fais bouger aucune ligne de la matrice.**
- **Je n'ecris aucune fiche de conformite** : elle est de `nadia-prof`.
- **Je ne rouvre aucune de ses conditions**, y compris celle dont j'ai du choisir la lecture stricte.
- **Je n'ai touche aucun document ni aucun garde-fou d'un autre agent**, hors les deux que ce lot
  fait necessairement bouger et dont le motif est ecrit dans leur Javadoc.
- **Je n'ai pas fusionne la branche de `lea-dev`** dans la mienne : les deux paires sont
  indissociables chacune de son cote, pas l'une de l'autre.

---

## 7. Ce que je remonte, et a qui - rien n'est tranche ici

| # | Obstacle | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|---|
| **a** | **`MON-1` interdit-elle un prix dans l'enonce, ou seulement la valeur d'une piece montree ?** Sa lettre dit *"aucune valeur n'y est ecrite"* ; son motif vise *"un enonce qui ecrit 'une piece de 2 euros'"*. **J'ai applique la lettre**, la plus stricte : aucun enonce ne porte de chiffre. Cela m'a interdit *"il faut payer 7 euros"* et m'a conduite a la forme *"comment faire la meme somme que le billet"*, ou la somme a atteindre est **lue sur l'image** | `nadia-prof` | C'est la lecture d'une de ses conditions. Si sa lettre est plus large que son intention, cinq items peuvent etre reecrits sous une forme plus proche de son exemple - *"on paie 7 euros avec 5 + 2"* |
| **b** | **Un garde-fou existant interdit la forme la plus forte de `MON-3`.** `ADR-0041` refuse deux propositions qui se lisent a la meme valeur. Or le distracteur le plus parlant d'un item de composition est celui qui est **juste arithmetiquement mais impossible avec les pieces montrees** - et il a, par construction, la meme valeur que la bonne reponse. **Le garde-fou est bon et je ne l'ai pas affaibli** : mes distracteurs ont des totaux distincts, et c'est l'image qui porte la somme a atteindre | `nadia-prof` pour la condition, `theo-archi` pour le fait technique | Savoir si la forme affaiblie satisfait encore *"et non de faire une addition qui serait juste avec n'importe quels nombres"* est un jugement pedagogique. Une collision entre deux garde-fous merite d'etre nommee avant qu'un autre lot la redecouvre |
| **c** | **Le trait vertical qui separe la reference de la bourse n'est explique nulle part.** Il est de la geometrie, pas du texte ; aucune condition de palier 1 n'exige d'explication, et l'indice de chaque item dit *"a gauche du trait"*. **Un enfant de CP comprend-il cette disposition sans qu'on la lui apprenne ?** | `nadia-prof` | C'est ce que l'enfant voit et comprend, pas une propriete de fichier |
| **d** | **`CON-4` et `CON-5` restent entieres dans sa main** : le controle exhaustif par masquage, et la verification que les douze textes alternatifs ne livrent pas la reponse | `nadia-prof` | Aucun automate ne le fera, et ce lot ne fait pas semblant de le couvrir |
| **e** | **Contradiction reelle entre deux contraintes de forme en vigueur, et je ne la tranche pas.** Le garde-fou de geometrie de `lea-dev` exige une dimension declaree **au moins** egale a 176 px ; `DES-3` d'`ines-design` exige une hauteur declaree **au plus** egale a 176 px, en chiffrant le cout d'aller au-dela - *"une rangee de pieces, dont la lisibilite n'exige que 43 px, occuperait 133 px de plus que necessaire"*. **La seule valeur qui satisfait les deux est 176 exactement**, et c'est celle que mes douze dessins declarent. *Les douze de `lea-dev` declarent 480, ce que `DES-3` refuse ; ecrites avant sa contrainte, elles n'ont pas pu la lire.* | `ines-design` et `theo-archi` | Une contrainte ecrite et un garde-fou executable qui disent l'inverse l'un de l'autre est un arbitrage de forme et de faisabilite, pas un choix de developpeur. **Prendre l'intersection est le seul geste qui ne tranche rien** |
| **f** | **`DV-3`, le contraste du trait, reste ouvert et porte sur deux paquets illustres.** Il n'est plus sans reponse pour autant : `DES-5` fixe la teinte a `#1c2733`, et `ines-design` mesure son contraste a **14,09:1** contre un seuil de 3:1 | `nadia-prof`, puis `theo-archi` et `yanis-qa` | La condition d'ecriture reste la sienne |
| **g** | **Les deux cliquets et le cliquet de zero sont le seul point de croisement des deux chaines.** Valeurs a porter apres fusion : population **24 et 24**, poids **2 685** et **29 118** | `sofia-sm`, casquette Scrum Master | C'est du sequencement de fusion |
| **h** | **L'horodatage de debut du planning de s25 n'est pas lu par l'outil de rapport de tokens.** Il s'ecrit `2026-08-31, 18:24 (UTC+2)` ; les vingt-quatre plannings precedents ecrivent `2026-08-30 22:32`. **La fenetre de s25 est donc absente**, et les tokens du sprint tomberaient en *hors sprint*. L'outil refuse bruyamment si on lui demande `--sprint s25` - verifie, code de sortie 1 - mais il se tait sans cette option | `sofia-sm`, casquette Scrum Master | C'est un fichier de ceremonie, et je ne reecris pas une ceremonie sous ma casquette de developpeuse. **Je n'ai pas elargi le motif de l'outil non plus** : cela masquerait l'ecart au lieu de le montrer |

**Aucun de ces huit points n'est clos par ce document.**

---

## 8. Ce qui reste a faire sur ce lot, et par qui

- **`nadia-prof`** : l'audit de contenu du paquet - passe d'exactitude image par image, `CON-4` par
  masquage, `CON-5` sur les douze textes alternatifs - plus les questions a, b et c. Et le jugement
  qu'`ines-design` lui route sur la forme des chiffres traces : *"le 1 a-t-il une base, le 7 une
  barre, le 4 est-il ferme"*.
- **`relecteur-pedagogique`** : le francais des douze items et des douze textes alternatifs, et le
  diff typographique de ce lot. **Compte a verifier : zero occurrence neuve.**
- **`yanis-qa`** : la bidirectionnalite du garde-fou d'`ADR-0073` sur du materiau desormais non vide,
  et les deux cliquets.
- **`sofia-sm`, casquette Scrum Master** : les obstacles g et h, et l'ordre de fusion des deux
  paires.

---

## 9. Essai de fusion des deux paires visuelles - fait, mesure, et rendu a la Scrum Master

**Fait sur un worktree jetable, jamais sur ma branche** : `s25-sofia-dev` ne contient pas la paire
de `lea-dev`, et la resolution des conflits appartient a la Scrum Master (planning, section F.6).
**Ce qui est rendu ici est une mesure et une proposition, pas une fusion.**

> **`115ac28` fusionne avec `origin/s25-lea` : BUILD SUCCESS, 986 tests, 0 echec.**
> Detail : 80 + 13 + 359 + 383 + 145 + 6.

**Trois conflits, tous des ajouts au meme endroit, aucun sur le fond** :

| Fichier | Nature | Resolution proposee |
|---|---|---|
| `catalogue.yaml` | Deux entrees ajoutees en fin de liste par chaque paire | Union, dans l'ordre de fusion |
| `TexteAccentueExposeParLePortEntrantTest` | Chacune etend le meme inventaire | Union. **Le piege est la parenthese fermante** : la derniere chaine de chaque liste la porte, et une union naive en laisse une au milieu |
| `LIllustrationDeContenuResoutEtAucuneNeDortTest` | **Les deux paires retournent le cliquet de zero, avec deux redactions differentes** | Voir ci-dessous |

**Le seul conflit qui demande un jugement, et je propose la reponse plutot que le milieu** :
`lea-dev` exige une population **non vide** ; moi une population de **taille exacte**. Les deux
disent vrai, et son motif de conservation du test est meilleur que le mien - je l'ai repris mot pour
mot. **Mais seule la taille exacte attrape un paquet qui perdrait silencieusement la moitie de ses
illustrations.** *Un conflit ne se resout jamais en affaiblissant une assertion* : la retenue est la
sienne pour le motif, la mienne pour l'assertion, et les nombres deviennent **24 et 24**.

**Ce que la resolution mesuree etablit en plus** :

- **Mes douze dessins passent le garde-fou de geometrie de `lea-dev` sans une retouche** - `viewBox`,
  dimensions declarees a 176, teinte unique `#1c2733`, aucun remplissage. C'est le benefice direct
  d'avoir pris `DES-1` a `DES-7` avant de commiter plutot qu'apres la fusion.
- **Le cliquet de poids se deplace a 2 685 et 29 118**, dans son fichier a elle, et **mon cliquet de
  poids disparait** - il etait ecrit comme un doublon temporaire et il part comme prevu. Le compte
  de `bootstrap` passe donc de 147 a 145 dans l'arbre fusionne.
- **Aucune assertion n'a ete affaiblie pour faire tenir la fusion**, et aucun chiffre n'a ete
  recopie : les 986 sont lus dans le journal du build de l'arbre fusionne.

---

## 10. Les reserves de `nadia-prof`, traitees - et un chiffre qui n'est pas un merite

**Arbre de mesure** : `1a47644`, `s25-sofia-review`, plus les deux fichiers de ce lot. **Mesure
lancee apres `clean`**, donc sur un jar reconstruit : `nadia-prof` a audite un jar de la veille sans
s'en apercevoir jusqu'a en regarder l'horodatage, et ce mode de defaillance est le meme que celui
qu'elle raconte deux fois dans sa fiche.

### 10.1 `CON-6` - mesure faite avant d'ecrire, et le resultat est contre-intuitif

`CON-6` est **neuve et non retroactive** : elle ne conditionnait pas le Done de ce paquet. Je l'ai
quand meme mesuree sur mes douze items, avant toute reecriture.

> **Avant reecriture : 12 / 12, soit 100 %**, pour un plancher de 50 %.

**Ce chiffre n'est pas un merite, et c'est le fait que je remonte.** Il vient de l'uniformite : deux
indices pour douze items. Un indice partage par tout le paquet est **maximalement conforme** a
`CON-6`, puisqu'il est forcement porte par un item de reponse differente. **La parade que `lea-dev`
avait trouvee pour les textes alternatifs - les rendre identiques - passe donc `CON-6` avec un score
parfait**, alors que c'est exactement ce que l'avertissement vise : *un indice qui ne varie pas ne
sert plus l'enfant qui vient d'echouer.*

`CON-6` attrape l'indice **taille pour une reponse**. Elle ne distingue pas l'indice **partage** de
l'indice **si general qu'il n'aide plus**. Les deux bornes de la condition sont donc a un seul bout.
**Ce n'est pas un reproche a une condition ecrite en trois heures sur un trou reel** : c'est le point
qui doit revenir a son autrice avant que `theo-archi` et `yanis-qa` decident si elle devient un
garde-fou de depot, parce qu'un garde-fou qui rend 100 % sur le cas degenere ne garde pas ce que sa
phrase promet.

### 10.2 Reserve 1 - le vocabulaire de l'indice : corrigee

*"**grave** n'est pas un mot du CP [...] **objet** est abstrait pour designer une piece ou un
billet."* **Les deux mots ont disparu des douze indices** - verifie, zero occurrence.

**Je n'ai pas remplace deux mots : j'ai redistribue les indices**, parce que la reserve de
vocabulaire et l'avertissement sur l'uniformite portent sur le meme champ et se corrigent d'un seul
geste. **Trois indices au lieu de deux, distribues par scene et non par reponse** :

| Indice | Items | Ce qu'il ajoute a l'enfant qui vient d'echouer |
|---|---|---|
| *"Commence par le plus grand nombre, puis ajoute les autres."* | 5 | Une **methode**, vraie et reutilisable, la ou l'ancien indice ne faisait que repeter la consigne |
| *"Tout est pareil ici. Ajoute plusieurs fois le meme nombre."* | 2 | Les deux seules scenes ou tout est identique, et ou "commencer par le plus grand" n'aide en rien |
| *"Ajoute ce qui est a droite du trait pour retrouver le nombre de gauche."* | 5 | Nomme le trait comme repere, ce que l'ancien indice faisait deja, sans le mot *gauche* avant le mot *trait* |

**Mesure apres reecriture** : `CON-6` **12 / 12**, inchange - ce qui confirme 10.1 plutot que de le
contredire. Aucun mot de plus de neuf lettres. **Zero occurrence typographique neuve.**

### 10.3 Reserve 2 et `MON-6` - mesuree, et rien n'est change dessus

`MON-6` est **neuve et non retroactive** ; `nadia-prof` ecrit qu'elle *"ne s'applique pas aux douze
items de ce paquet, deja valides"*. **Je l'ai mesuree quand meme, pour que le chiffre existe avant le
paquet suivant** :

| Item de composition | Termes | Somme des trois premiers | `MON-6` |
|---|---|---|---|
| `2 + 2 + 1` | 3 | 5 | tient |
| `2 + 1 + 1 + 1` | 4 | 4 | tient |
| `1 + 1` | 2 | 2 | tient |
| `5 + 2 + 2 + 1` | 4 | 9 | tient |
| `10 + 5 + 2 + 2 + 1` | **5** | **17** | **ne tient pas** |

**Quatre items sur cinq tiennent deja la condition qui n'existait pas quand ils ont ete ecrits.**
Le cinquieme est exactement celui qu'elle nomme. **Je ne le change pas** : le modifier rouvrirait sa
passe d'exactitude sur un increment qu'elle a valide, et deplacer son poteau dans l'autre sens ne
vaut pas mieux que le deplacer dans le sien.

### 10.4 Ses deux autres reponses - ce qu'elles changent, c'est-a-dire rien

- **`MON-1`, le motif et non la lettre.** Un nombre qui nomme **la valeur d'un objet montre** est
  interdit ; un nombre qui nomme **une cible a atteindre** est permis. **Ma lecture etait plus severe
  que necessaire et elle n'a rien casse** : elle ecrit qu'aucune reecriture n'en decoule. Les douze
  enonces restent a **zero chiffre**, ce qui tient la lettre comme le motif.
- **La forme forte de `MON-3`, et mon motif etait faux.** Je l'avais bloquee sur `ADR-0041`. Le vrai
  motif est ailleurs et il est meilleur : l'enonce ne liant pas l'enfant aux pieces montrees, **un
  distracteur de meme valeur donnerait deux reponses justes**. *"Limite de mecanique, pas manquement
  de contenu."* **Le garde-fou n'avait pas a etre affaibli, et il ne l'a pas ete** - mais je notais
  la bonne conclusion pour la mauvaise raison, et c'est elle qui a trouve la bonne.

### 10.5 Ce que j'ai verifie et qui ne bouge pas

| Ce qui est verifie | Resultat |
|---|---|
| Les vingt-quatre dessins | **zero octet change** par ce lot - `git diff` vide sur le repertoire |
| Cliquet de poids, **remesure par moi** | **24 fichiers, 29 436 octets, maximum 2 685** - identique a ce que porte l'arbre final |
| Part de `MON` dans ce total | 12 fichiers, **22 912 octets**, maximum 2 685 - inchangee depuis mon premier commit |
| Population epinglee | **24 et 24** |
| `MON-1`, `MON-3`, `MON-4`, `CON-3`, clause 6 | 0 chiffre sur 12 enonces ; 5 items sur 12 (41,7 %) ; 20 euros ; 12 dessins distincts ; 12 fois `enonce` en premiere cle |
| `CON-5` | **7 textes alternatifs distincts**, inchanges |
| Porte technique | `mvn -B clean verify`, racine du reacteur complet, **BUILD SUCCESS, 1 007 tests** |

**Les 1 007 se recomptent** : 1 006 sur l'arbre final, plus **un** - le test de `CON-6` ajoute ici.
Detail par module : 80 + 13 + 364 + 399 + 145 + 6.

### 10.6 Ce que je remonte, et qui touche mon propre lot F

**Le refus de l'enveloppe de build est un faux rouge, et la cause est dans ma moitie du lot F.**
`outils/mvn-jdk21.sh` resout sa toolchain dans le fichier du **shell**, et refuse sur ce qu'elle y
lit ; Maven, lui, lit le fichier du **repertoire utilisateur de sa machine virtuelle**, ou la
declaration est juste. **Le refus est donc exact sur le fichier qu'il regarde et sans objet pour le
build qu'il empeche** - c'est le defaut de `S21-1` section 6.3, deplace de `verifier-jdk.sh` vers
l'enveloppe.

**L'ingredient du correctif existe deja, ecrit dans le meme lot F** :
`repertoire_utilisateur_de_la_machine_virtuelle`, juge pur, teste, et deja employe par
`verifier-jdk.sh` - c'est lui qui fait dire au constat *"toolchains lu par Maven : oui (un autre que
celui lu par ce script)"*. **Je ne l'applique pas a l'enveloppe** : `theo-archi` instruit ce
correctif, et deux mains sur le meme fichier est ce qui produit les conflits que ce sprint a passe sa
journee a resoudre. **Je nomme l'ingredient pour que son correctif soit un appel, pas une enquete.**
