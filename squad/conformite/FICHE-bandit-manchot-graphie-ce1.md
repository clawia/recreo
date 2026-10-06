# Fiche de conformité - bandit-manchot-graphie-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-graphie.yaml`
  (24 items, type `question`), écrit par `lea-dev` (`271058ec`), corrigé par `lea-dev` (`df608669`).
- **Entrée de catalogue** : `bandit-manchot-graphie-ce1`, titre servi *"Comment ça s'écrit ?"*,
  consigne servie *"Lis bien, puis choisis le mot bien écrit."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-22`. **Story** : `S51-FRA-GRAPHIE`. **Décision** : `DEC-0110`.
- **Compétence programme visée** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-22`,
  *"Choisir la graphie d'un son selon la lettre qui suit (`m` devant `m`, `b`, `p` ; `c`/`ç` ;
  `g`/`ge`/`gu`)."*
- **Conditions opposables** : `GRL-CE1-1` à `GRL-CE1-3` et `TRV-CE1-1` à `TRV-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 5.13), `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5` (même fichier, section 1), points 1 à 6 de la section 2 de
  `squad/conformite/ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md`.
- **Statut global** : **VALIDÉ** le 2026-10-06 par `nadia-prof` (s51, second passage), après le
  correctif `df608669` de `lea-dev`. Premier passage **REFUSÉ** le même jour (s51, instance A), un
  motif, conservé plus bas. Fiche ouverte par ce refus : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-06.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-06 (`nadia-prof`, s51, second passage) : **VALIDÉ**

**Done pédagogique prononcé pour `FRA-CE1-22`.** Le motif 1 du refus ci-dessous est levé, contre la
barre « Ce qui lève le motif » que j'avais écrite, sans qu'elle soit relevée. `camille-po` peut
passer la ligne à `couvert-valide`, sans mention de couverture partielle (`DEC-0110`, section 10).

#### 1. Ce que j'ai fait

**Lecture masquée d'abord**, sur les deux items touchés : *une trompette* / *une tronpette*, *un
champion* / *un chanpion*, propositions triées, sans la bonne réponse.

**Audit joué.** Worktree `s51-nadia-instance-a` à `df608669`. Jar reconstruit depuis ce worktree par
`bash outils/mvn-jdk21.sh -B -q -DskipTests package` (Maven sur JDK 21.0.10). `contenu/ce1-graphie.yaml`
et `catalogue.yaml` contenus dans le jar sont identiques octet pour octet à ceux de l'arbre (`cmp`).
Jar lancé sur un port dédié par mon propre processus, arrêté par son PID. **Rendu non vu au
navigateur** : appels HTTP réels, HTML servi lu.

**Soixante parties complètes de dix manches**, mêmes conduites qu'au premier passage. **Les 24 items
tirés et joués**, de 15 à 33 fois chacun, chacun avec une erreur et une révélation au moins. Sur les
600 manches : aucune anomalie (propositions, verdicts, indices et révélations identiques au
fichier, aucune explication servie) ; bonne réponse 300 fois à gauche, 300 fois à droite. *une
trompette* : 29 tirages, 47 erreurs, 12 révélations ; *un champion* : 22 tirages, 26 erreurs, 7
révélations ; chacun servi des deux côtés. *pomme* et *flamme* ne sont plus servis.

#### 2. Motif 1 : levé

| Point de la barre | Constat |
|---|---|
| Aucun item n'oppose deux formes qui se lisent différemment | *tronpette* se lit « tron-pette » comme *trompette* ; *chanpion* se lit « chan-pion » comme *champion*. L'enfant ne peut plus trouver en lisant : seule la règle décide |
| Chaque item du `m` porte un son « on », « an » ou « in » écrit `m` devant `b`, `p` ou `m` | les 8 items, relus un par un : *jambe*, *lampe*, *champion*, *chambre* (« an ») ; *tombe*, *trompette*, *nombre* (« on ») ; *timbre* (« in »), chacun `m` devant `b` ou `p` après une voyelle |
| `GRL-CE1-1` et `TRV-CE1-2` tenues | 8 items par règle sur 24 ; deux remplacements de même longueur, la bonne réponse reste strictement la plus longue sur les 8 items de `g`, un tiers |

**Vérifié aussi** : *tronpette* et *chanpion* ne sont pas des mots (section 2, point 2) ; *trompette*
et *champion* sont des mots que l'enfant de CE1 rencontre (point 3) ; aucun n'est une exception de
`GRL-CE1-3` ; aucun n'était déjà servi par un autre item ; l'énoncé reste *"Quel mot est bien écrit
?"* et les deux indices (*"Compare la lettre après le m."*, *"Pense à la lettre qui vient après le
m."*) n'écrivent pas le mot cible (point 4). Ces deux indices, que j'avais rangés dans le motif sur
*pomme*, sont justes sur *trompette* et *champion* : la lettre qui suit le `m` y est un `p`.

**Le cas *devant `m`*** n'est pas servi par le paquet. Je l'avais dit non exigé au premier passage ;
je ne le réclame pas maintenant.

**Le reste du premier passage tient** : le correctif ne touche que ces deux items et un commentaire
d'en-tête. Le point 6 reste réglé par la décision (a) du premier passage (section 4 plus bas).

#### 3. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1GraphieTest` exige désormais, pour la règle du `m`, une voyelle avant le `m` et un `b` ou
un `p` après (diff de `df608669` lu), et refuse un contre-exemple neuf en consonne double (*pomme* /
*ponme*). **Il juge des lettres, pas un son** : que ce qu'on entend soit « on », « an » ou « in »,
c'est moi qui le vérifie, avec les points 2 et 3 et la plausibilité de chaque erreur, en jouant.

**Un écart sans effet aujourd'hui, nommé pour qu'on ne le découvre pas plus tard.** Le test refuse
aussi tout `m` devant `m`, donc *emmener* / *enmener*, que mon premier passage déclarait recevable
(motif 1, correction attendue). Le paquet ne sert pas ce cas et je ne l'exige pas. Un item de ce
genre demanderait d'adapter le test d'abord.

**Porte technique** : `bash outils/mvn-jdk21.sh -B verify` lancé par moi sur la racine du worktree à
`df608669` plus cette fiche, `BUILD SUCCESS`, aucun échec ; job `chemins` lancé sur l'arbre : vert.
Je n'ai lancé ni les juges purs des outils, ni le job des préfixes sur d'autres commits que le mien.

**Identifiants** : aucun écrit, aucun renommé.

**Signé** : `nadia-prof`, s51, second passage, 2026-10-06.

---

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance A) : **REFUSÉ**

**Done pédagogique non prononcé.** `FRA-CE1-22` reste `non couvert`. Le refus porte sur deux items
sur vingt-quatre ; tout le reste du paquet est tenu (section 3), et la correction est décrite item
par item (section 2). Le rejeu se paie sur la marge de rejeu de l'instance A (`DEC-0110`).

#### 1. Ce que j'ai fait

**Audit joué.** Worktree `s51-nadia-instance-a` à `271058ec`, arbre propre. Jar construit depuis ce
worktree par `bash outils/mvn-jdk21.sh -B -DskipTests package` (Maven sur JDK 21.0.10), lancé sur un
port dédié par mon propre processus, arrêté par son PID. **Rendu non vu au navigateur** : appels
HTTP réels, HTML servi lu.

**Lecture masquée d'abord** : les 24 items affichés avec leurs deux propositions triées, sans la
bonne réponse ni l'indice, avant de lire le fichier. C'est à cette lecture que les deux items du
motif se sont détachés.

**Soixante-dix parties complètes de dix manches** : une juste du premier coup, une avec une erreur
par manche, une en pressant trois fois le distracteur jusqu'à la révélation, les autres en tirant
l'une de ces trois conduites au hasard à chaque manche. **Les 24 items tirés et joués**, chacun avec
une erreur et une révélation au moins. Sur les 700 manches : propositions servies identiques au
fichier, aucun distracteur crédité juste, indice servi identique au fichier après chaque erreur,
réponse révélée identique à la bonne réponse, **aucune explication servie**. Position de la bonne
réponse : 198 fois à gauche, 202 fois à droite sur les 400 manches de la seconde série ; chaque
item a été servi des deux côtés. Écran de fin atteint sur les soixante-dix parties ; après une
révélation, il redonne *"Quel mot est bien écrit ?"* et *"La bonne réponse : un guide"*.

#### 2. Motif du refus

**Motif 1 - deux items ne travaillent pas la règle qu'ils déclarent, et leur distracteur n'est pas
une erreur d'élève (`GRL-CE1-2` ; section 9, points 4, 5 et 7 de l'arbitrage de s48).**

| Item | Propositions | Indice servi |
|---|---|---|
| 13 | *une pomme* / *une ponme* | *"Compare la lettre après le m."* |
| 16 | *une flamme* / *une flanme* | *"Pense à la lettre qui vient après le m."* |

La règle du `m` devant `m`, `b`, `p` décide **comment s'écrit un son « on », « an » ou « in »** (comme
dans « jambe ») : devant `b`, `p` ou `m`, ce son s'écrit avec `m`. Dans *pomme* et *flamme*, il n'y a
aucun de ces sons : on entend « po-me » et « fla-me ». Le double `m` n'est pas décidé par la règle,
il se mémorise. Trois conséquences, chacune visible par un enfant :

1. **Les deux propositions ne se lisent pas pareil.** *ponme* se lit « pon-me », *pomme* se lit
   « po-me ». L'enfant trouve la réponse en lisant, sans rien savoir de la règle : c'est un exercice
   de lecture, la frontière que `GRL-CE1-3` trace avec `FRA-CE1-02`.
2. **Le distracteur n'est pas une erreur réelle.** Un enfant de CE1 qui se trompe sur *pomme* écrit
   *pome*, jamais *ponme* : il n'entend aucun « on ».
3. **L'item enseigne une analyse fausse.** Avec l'indice *"Compare la lettre après le m"*, il présente
   *pomme* comme un cas de la règle : l'enfant peut en conclure qu'il y a un « on » dans *pomme*.

Fréquence au jeu : 37 manches sur 400 dans la seconde série (*une pomme* 21, *une flamme* 16).

**Correction attendue de `lea-dev`, sur ces deux items et eux seuls :**

- Remplacer chacun par un item où le `m` écrit **note un son « on », « an » ou « in » placé devant
  `b` ou `p`**, distracteur en `n` à la même place, rien d'autre ne change : par exemple *une
  ampoule* / *une anpoule*, *un pompier* / *un ponpier*, *une trompette* / *une tronpette*, *un
  tambour* / *un tanbour*, *un champion* / *un chanpion*. Liste indicative. Le mot n'est ni une
  exception de `GRL-CE1-3` (*bonbon*, *néanmoins*, *embonpoint*), ni un mot déjà servi par un autre
  item du paquet.
- Le cas *devant `m`* n'est pas exigé. S'il est servi, c'est par un mot où le `m` placé devant un
  autre `m` note ce son (*emmener* / *enmener*), jamais par une consonne double (*pomme*, *flamme*,
  *homme*, *femme*).
- Supprimer les deux items sans les remplacer est aussi recevable : la règle du `m` garde 6 items
  sur 22, au-dessus du quart de `GRL-CE1-1`.

**Ce qui lève le motif** : aucun item du paquet n'oppose deux formes qui se lisent différemment ;
chaque item de la règle du `m` porte un son « on », « an » ou « in » écrit `m` devant `b`, `p` ou
`m` ; `GRL-CE1-1` et `TRV-CE1-2` restent tenues. Je rejoue le paquet et je vérifie ces trois points.

**Une précision sur ma propre condition, datée du 2026-10-06.** `GRL-CE1-1` reprend la parenthèse du
référentiel (*"`m` devant `m`, `b`, `p`"*) sans dire que la règle porte sur un son « on », « an » ou
« in ». Ce n'est pas une barre neuve : le libellé de la ligne parle de *la graphie d'un son*, et
`GRL-CE1-2` exige que les deux formes ne diffèrent que *par la graphie que la règle décide*. Mais la
phrase qui l'aurait évité n'était pas écrite. Elle l'est ici.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `GRL-CE1-1` | **oui** | `c`/`ç` 8 items, `g`/`ge`/`gu` 8 items (4 en `gu`, 4 en `ge`), `m` 8 items dont 6 valides ; 6 sur 24 reste un quart |
| `GRL-CE1-2` | **non, items 13 et 16** (motif 1) | les 22 autres : deux propositions, même mot, même déterminant, une seule différence, celle que la règle décide |
| `GRL-CE1-3` | **oui** hors motif 1 | aucune exception du `m` ; l'enfant choisit comment écrire, il ne lit pas la valeur d'une lettre |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support |
| `TRV-CE1-2` | **oui** | deux propositions distinctes ; bonne réponse strictement la plus longue sur les 8 items de `g`, un tiers |
| `TRV-CE1-3` | **oui** | aucun son désigné par un graphème isolé ; aucune prononciation régionale en jeu |
| `TRV-CE1-4` | **oui** | ni *graphème* ni *phonème* ; *cédille* n'est écrit nulle part |
| `TRV-CE1-5` | **oui** hors motif 1 | 6 à 9 mots, trois formes par règle ; méthode, jamais le mot ; aucune règle fausse sur les 22 autres |
| `TRV-CE1-6` | **sans objet** | aucun personnage, aucun *"tu"* |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | énoncé de cinq mots, une phrase ; propositions d'un ou deux mots |
| Section 2, point 1 | **oui** | 8 items : `ç` devant `a`, `o` ou `u` dans la bonne réponse, `c` dans le distracteur, rien d'autre ; aucun `ç` devant `e` ou `i` |
| Section 2, point 2 | **oui** | aucun distracteur n'est un mot juste : *garcon*, *lecon*, *glacon*, *macon*, *balancoire*, *francais*, *recu*, *decu* n'existent pas en minuscules ; *Mâcon* est un nom propre et porte un accent circonflexe. Idem pour les 16 distracteurs de `m` et de `g` |
| Section 2, point 3 | **oui** | les huit mots `ç` sont ceux de ma liste indicative ; *un reçu* est le moins fréquent, je l'avais moi-même déclaré recevable et je ne reviens pas dessus |
| Section 2, point 4 | **oui** | l'énoncé est le même sur les 24 items ; aucun indice n'écrit le mot cible |
| Section 2, point 5 | **oui** | *"Regarde la lettre qui suit le c."* et ses deux variantes : la forme que j'avais donnée en exemple |
| Section 2, point 6 | **tenu par son objet** | décision en section 4 |

**Plausibilité des erreurs de `g`** : *une gitare*, *un gide*, *une bage*, *une vage* sont des
erreurs réelles d'un enfant qui écrit ce qu'il entend sans connaître `gu` ; *un pigon*, *un
plongon*, *une nagoire*, *une mangoire* sont celles d'un enfant qui oublie le `e` de `ge`. Aucun de
ces distracteurs n'est un mot.

**Libellé de la compétence** : *"c avec ou sans cédille"* à la place de *"c/ç"*. Le garde-fou d'accent
refuse un `ç` isolé. Le libellé dit la même chose que le référentiel, avec des mots : il reste fidèle.

#### 4. Le point 6 de mon arbitrage : l'absence d'explication ne ferme pas la ligne

**Décision : (a). L'absence d'explication est recevable, sans réserve.** Le point 6 est tenu par ce
qu'il protège, pas par sa lettre, et je corrige ma lettre (amendement daté dans
`ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md`).

**Ce que le point 6 protège** : *"L'enfant ne quitte pas la manche avec garcon comme dernière forme
écrite sous les yeux."* **Vérifié au jeu, sur les trois chemins de sortie d'une manche :**

1. Juste du premier coup : l'enfant presse la forme juste ; l'écran suivant est un autre item.
2. Après une ou deux erreurs : *"Ce n'est pas ça. Essaie encore !"* et l'indice ; l'enfant presse
   ensuite la forme juste lui-même pour sortir.
3. Après trois erreurs : *"Appuie sur la bonne réponse :"*, puis la forme juste seule dans son
   élément (*une balançoire*) ; l'enfant sort en la pressant. En fin de partie, l'écran redonne
   *"La bonne réponse :"* et la forme juste.

**Une explication n'ajouterait rien** : le gabarit ne la rend qu'avec la révélation
(`fragments/manche.html`, bloc `.message-jeu`), c'est-à-dire dans le seul état où la forme juste
est déjà affichée seule. Sur les chemins 1 et 2, elle ne serait jamais servie.

**Ma correction.** J'ai écrit le point 6 sans lire le garde-fou des explications
(`ChaquePaquetExpliqueSesItemsAVariationTest`, `ADR-0024`), qui lie toute explication à une clé
`graphie` et, quand celle-ci diffère de la bonne réponse, à un énoncé contenant le mot. Ce lien
contredit mon point 4. J'ai vérifié ce comportement dans le test. Le point 6 n'était donc pas
servable avec le point 4, comme `GRL-CE1-2` ne l'était pas avec `ADR-0065` le 2026-10-03. J'ai aussi
écrit qu'`ADR-0065` vérifiait le point 6 *"pour l'explication"* : ce contrôle n'a rien à vérifier
dans un paquet sans explication. **Rien n'est routé à `theo-archi`** : je n'exige pas d'explication.

#### 5. Exactitude, niveau, ton

Les 24 bonnes réponses sont orthographiées juste. *déçu* garde son `é` dans les deux propositions.
Énoncé de cinq mots, identique sur tout le paquet : un enfant de CE1 le lit seul. Indices de six à
neuf mots. Aucun stéréotype possible : aucun personnage. *un maçon* est un métier : il n'est attribué
à aucun genre.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1GraphieTest` tient la forme des deux propositions, les quarts, les exceptions, la longueur,
le mot cible absent de l'énoncé et de l'indice, et l'absence d'explication. **Il ne sait pas si un
`m` note un son « on », « an » ou « in »** : il a laissé passer *pomme* / *ponme*, comme il laissera
passer *homme* / *honme*. Restent aussi dans ma seule main : qu'un distracteur ne soit pas un mot
(point 2), la fréquence (point 3), la plausibilité de chaque erreur et la justesse de chaque
indice. J'en reste la seule garde, en jouant.

**Porte technique, pour les quatre livraisons de ce jour** : `bash outils/mvn-jdk21.sh -B verify`
lancé par moi sur la racine du worktree, à `271058ec` plus mes fiches, `BUILD SUCCESS`, aucun échec.
Un premier lancement, pendant que j'écrivais `TPS-CE1-R1` sans l'avoir encore alloué, a rougi sur
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` et sur ce seul défaut : le mien, réparé
avant de commiter. Job `chemins` lancé sur l'arbre, fiches comprises : vert. Je n'ai lancé ni le job
des préfixes de commit ni les juges purs des outils.

**Identifiants** : aucun écrit, aucun renommé. Le motif est numéroté dans cette fiche, comme le
refus de `FRA-CE1-02` à s50.

**Signé** : `nadia-prof`, s51, instance A, 2026-10-06.
