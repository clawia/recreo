# Fiche de conformite - Nommer, en histoire (`bandit-manchot-histoire-geographie-nommer-en-histoire-cm1`)

> **Fiche ouverte par `nadia-prof` a s35** (`S35-CONS-4`), et non par `camille-po`. Cette entree de
> catalogue est neuve : elle est l'une des **quatre** issues de la scission de
> `bandit-manchot-histoire-geographie-cm1` au commit `b16bbbcb`. **Aucun de ses items n'est neuf** -
> les 45 items de ce paquet viennent tous du paquet d'origine, mot pour mot.
>
> **Toute l'histoire pedagogique de ces items, jusqu'a s35, vit dans
> `FICHE-bandit-manchot-histoire-geographie-cm1.md`** : cinq verdicts de `nadia-prof` de s27 a s33,
> et les reserves `HG-R*` ouvertes, qui **restent opposables** aux items de ce paquet. Cette fiche
> ne les recopie pas - un verdict recopie n'est plus une preuve - elle y renvoie. Les sections
> descriptives sont reprises de `catalogue.yaml`, du paquet et du referentiel, sans rien y ajouter ;
> **le bloc de validation est le mien, comme toujours.**

- **Mecanique** : `bandit-manchot`
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-nommer-en-histoire.yaml` (45 items)
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-nommer-en-histoire-cm1`, titre servi *"Nommer, en histoire"*, `nombreDeManches: 10`
- **Fiche de l'ancetre** : `FICHE-bandit-manchot-histoire-geographie-cm1.md`
- **Statut global** : **VALIDE** (premier passage sur cette entree, 2026-09-11, s35). **`CONS-4` levee sur cette entree**, et **`CONS-5` ouverte** (non
  bloquante) : trois items de ce paquet ne font pas ce que leur consigne annonce.
  **Sur les trois items neufs de `S42-HG-ROIS` : VALIDE SOUS RESERVE** (2026-09-24, s42,
  `nadia-prof` instance A), reserves neuves `HG-R23`, `HG-R24`, `HG-R25`, aucune bloquante
- **Derniere mise a jour** : 2026-09-24 (verdict pedagogique sur les trois items neufs,
  `nadia-prof` instance A)
- **Amendement du 2026-09-24 (s42, `camille-po`)** : `S42-HG-ROIS` ajoute trois items neufs
  portant `HG-CM1-05`, non couverts par le verdict de 2026-09-11 ci-dessous. Voir la section
  "Ajout du 2026-09-24" plus bas.

## Competences visees

| Matiere | Niveau | Domaine | Lignes de referentiel servies |
|---|---|---|---|
| Histoire-geographie | CM1 (cycle 3) | Reperes historiques et geographiques | `HG-CM1-03`, `HG-CM1-04`, `HG-CM1-06`, `HG-CM1-07`, `HG-CM1-08` |

`squad/programme/histoire-geographie/competences-cm1.md`. **Aucun statut de matrice ne bouge par
cette scission** : ces lignes gardent exactement l'etat que mes verdicts de s27 a s33 leur ont
donne, et les items qui les servent n'ont pas change.

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, stories `S34-CONS-4` (criteres qui font foi) et `S35-CONS-4`,
plus les trois criteres ajoutes par `DEC-0088`.

- [x] Les items viennent du paquet d'origine, sans ajout, sans perte, sans texte change
- [x] La consigne de l'entree nomme la tache reellement servie par ce paquet (`CONS-4`)
- [x] Le descripteur `matiere` / `niveau` / `domaine` est identique a celui des trois autres paquets
      issus de la scission (`DEC-0088`), pour que `HIS-1` continue de se compter sur le lot de
      matiere entier
- [x] Plus d'items que de manches (`ADR-0023`) - 45 pour 10

## Ajout du 2026-09-24 (s42, `camille-po`) - `S42-HG-ROIS`

Trois items neufs ajoutes en fin de paquet (46e a 48e position) par `S42-HG-ROIS`
(`docs/backlog/backlog.md`), portant tous `HG-CM1-05` : nommer, parmi les quatre rois du libelle
(Louis IX, Francois Ier, Henri IV, Louis XIV), celui que decrit l'enonce a partir d'une
realisation ou d'une situation dans le temps par rapport a un autre roi du libelle - jamais un
jugement de valeur sur le personnage (`BIA-5`). Cette story etend le paquet existant, elle n'ouvre
pas une forme neuve (`DEC-0077`, section 9). Livres et fusionnes dans `round-two` au commit
`cdf0229f` (contenu au commit `cbe35d0b`). Lien decision : `DEC-0098`.

`HG-CM1-05` est conjonctive ("nommer et situer dans le temps") : les criteres qui portent sur le
paquet livre dans son entier (`HIS-1`, les quatre rois chacun en bonne reponse au moins une fois)
se verifient sur les **cinq** items neufs de `S42-HG-ROIS` pris ensemble - les trois d'ici et les
deux de `cm1-histoire-geographie-situer-dans-le-temps.yaml` (voir
`FICHE-bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1.md`), pas sur les trois d'ici
seuls.

**Le bloc de validation pedagogique ci-dessous porte sur les 45 items anterieurs a cet ajout
(verdict du 2026-09-11, s35) : il ne couvre pas ces trois items neufs.** Ils restent en attente
d'un Done pedagogique propre. `HG-CM1-05` reste `non couvert` dans `MATRICE-couverture.md` au
moment de cet ajout.

> **Note de `nadia-prof` du 2026-09-24 (s42, instance A)** : ce Done pedagogique propre a ete
> prononce le jour meme. Il est **plus bas dans cette fiche**, dans le bloc de validation date du
> 2026-09-24, et non dans le bloc du 2026-09-11 auquel le paragraphe ci-dessus renvoie. Verdict :
> **VALIDE SOUS RESERVE**, trois reserves neuves `HG-R23`, `HG-R24`, `HG-R25`, aucune bloquante.

Criteres d'acceptation de `S42-HG-ROIS` portant sur ces items, repris de la story :

- [x] Chaque item porte sur l'un des quatre rois nommes par le libelle, sa datation ou sa
      situation dans le temps par rapport a un autre roi du libelle - jamais un jugement de valeur
      sur le personnage (`BIA-5`). **Coche le 2026-09-24 par `nadia-prof`, item par item sur les
      cinq.**
- [x] Sur les cinq items neufs pris ensemble (les trois d'ici et les deux du paquet "situer dans
      le temps"), au plus un tiers demande une date, un nom ou un chiffre a restituer seul ; les
      deux autres tiers demandent d'ordonner deux rois dans le temps, d'associer un roi a un fait,
      ou d'extraire une information d'un document (`HIS-1`). **Zero restitution pure sur les cinq.**
- [x] Une seule des trois propositions est defendable par item, aucune reponse discutable
      (`HIS-2`).
- [x] Chaque fait historique (date, nom, filiation) porte sa source dans le commentaire du
      paquet, et aucun fait n'est errone (`HIS-3`).
- [x] Sur les cinq items neufs pris ensemble, les quatre rois du libelle sont chacun en bonne
      reponse au moins une fois.
- [x] Chaque item declare `HG-CM1-05` et uniquement elle.

**Les six criteres sont tenus, et trois defauts sont passes entre eux.** Ils sont ecrits en
`HG-R23`, `HG-R24` et `HG-R25` dans mon verdict du 2026-09-24, en bas de fiche. Un critere d'histoire
coche n'est pas une garantie de qualite d'item : `HIS-2` interdit deux reponses defendables, il
n'interdit pas qu'il n'en reste qu'une par elimination logique, et c'est exactement ce qui est
arrive.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`
- **Audit joue** : **oui.** Jar reconstruit depuis mon propre worktree, lance sur le JDK `21.0.10`
  de la toolchain. **Une partie complete de 10 manches sur cette entree**, chaque manche
  jouee en pressant les propositions jusqu'a la bonne, pour lire les indices servis. **La consigne a
  ete lue a l'ecran, au-dessus de ses items, et non dans le catalogue.**
- **Mouvement de matrice** : **aucun.**
- **Portee de ce verdict** : il porte sur **ce que la scission fait a ces items** - leur fidelite au
  contenu d'origine et l'ajustement de la consigne neuve a ce paquet. **Il ne rejuge pas les items
  eux-memes** : ils ont deja recu cinq verdicts, et les reserves `HG-R*` encore ouvertes dans la
  fiche de l'ancetre **restent ouvertes sur ce paquet** et voyagent avec les items qui les portent.

### 1. Fidelite - verifiee, pas supposee

Mesure faite sur les quatre paquets a la fois, methode et chiffres complets dans le bloc de s35 de
`FICHE-bandit-manchot-histoire-geographie-cm1.md`, section 1 : **110 items a l'origine, 110 dans les
quatre paquets, zero enonce perdu, zero enonce neuf, zero ecart** sur un `enonce`, une
`bonneReponse`, un `distracteurs`, un `indice`, un `texteSupport` ou une `explication`.

**Ce paquet en porte 45, tous identiques a ce qu'ils etaient.**

### 2. La consigne devant les items de ce paquet

**Consigne servie, lue a l'ecran** : *"Nomme ce que l'enonce decrit."* - 5 mots, contre les 28 de la consigne
fourre-tout qu'elle remplace.

**Exacte sur quarante-deux items sur quarante-cinq, et c'est un tres net progres sur la consigne
qu'elle remplace.** L'enonce decrit une realisation, un peuple, un evenement, et l'enfant le nomme :
*"Des arenes et des aqueducs en pierre ont ete construits en Gaule durant l'Antiquite. Quel peuple
les a construits ?"*, *"Comment appelle-t-on la grande salle du chateau de Versailles decoree de
miroirs ?"*, *"Napoleon Bonaparte fait rediger un meme ensemble de lois pour toute la France. Comment
appelle-t-on ce texte ?"*. **La consigne decrit exactement ce geste.**

**Trois items ne le font pas, et ils fondent `CONS-5`** (reserve neuve, datee du 2026-09-11, non
bloquante, ecrite en entier dans la fiche de l'ancetre) :

| Enonce | Bonne reponse | Ce que l'enfant fait |
|---|---|---|
| *"Que se passe-t-il finalement en 476 ?"* | *l'Empire romain d'Occident disparait* | il produit **un evenement**, pas un nom |
| *"Que devient son expansion en Europe ?"* | *elle s'arrete* | il produit **une issue** |
| *"Que devient ce territoire ?"* | *il se divise en plusieurs royaumes* | il produit **une issue** |

**Ces trois items sont inchanges depuis leur ecriture, et le defaut est du cote de la consigne, pas
d'eux.** Un mot de plus dans la consigne (*"Nomme ou dis ce que l'enonce decrit"*) les couvrirait.
**Je ne l'ecris pas a la place de qui decide** (`RES-2`), et je ne rouvre pas le grain du catalogue
(`ADR-0081`).

**Un quatrieme cas, plus lache, que je nomme sans le compter** : les quatre items *"Quel fait
caracterise le regne de X ?"* partent d'un roi nomme pour demander un fait. L'enonce **ne decrit
rien** - mais la reponse **est** bien un nom (*la croisade*, *la Renaissance*, *l'edit de Nantes*,
*Versailles*). La consigne tient a moitie ; cela ne genera aucun enfant, et je n'en fais pas une
demande.

### 3. Ce que j'ai verifie a l'ecran sur cette partie

- **Les dix manches se sont jouees de bout en bout**, aucun ecran casse, aucune repetition.
- **Les indices** : exacts, a leur item, et ils distinguent bien les trois propositions. Celui de
  l'edit de Nantes (*"Ce texte accorde la liberte de culte aux protestants."*) est exact et
  discriminant.
- **`ADR-0023`** : 45 items pour 10 manches, le plus grand des quatre paquets.
- **L'ecran de fin de partie** : les deux nombres, justes. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
- **Les reserves `HG-R*` ouvertes** sur ces items - dont `HG-R19` (un mot de l'enonce qui appelle la
  bonne reponse) et `HG-R21` (un indice qui doit separer les trois propositions) - **restent
  ouvertes ici** et ne sont ni soldees ni rouvertes par la scission.

### 4. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun.** C'est moi, en jouant, texte devant items |
| **La fidelite d'une scission** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main ce jour ; une scission prochaine repasserait sans rouge si un item etait perdu en route. Route a `theo-archi` comme symptome |
| **Les reserves `HG-R*` ouvertes** portees par les items de ce paquet | voir la fiche de l'ancetre - inchangees |

---

## Bloc de validation pedagogique du 2026-09-24 (s42) - les trois items neufs de `S42-HG-ROIS`

> Ce bloc n'annule ni ne modifie le verdict du 2026-09-11 ci-dessus, qui porte sur les 45 items
> anterieurs et qui reste lisible tel qu'il a ete prononce. Il porte **uniquement sur les trois
> items neufs** ajoutes par `S42-HG-ROIS` (46e, 47e et 48e position, ligne `HG-CM1-05`).

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-24 (s42)
- **Auteur** : `nadia-prof`, **instance A** (`DEC-0098`, terrain 1)
- **Arbre** : `round-two` a `9817b4f0`
- **Audit joue** : **oui.** Jar reconstruit depuis cet arbre, lance sur le JDK `21.0.10` de la
  toolchain. Une partie complete de 10 manches d'abord, puis **des parties repetees jusqu'a ce que
  les trois items neufs soient chacun sortis a l'ecran** - le tirage est aleatoire sur 48 items, dix
  manches ne les donnent pas. Chaque item joue en pressant les propositions jusqu'a la bonne, pour
  lire l'indice servi. **Enonces, propositions et indices lus a l'ecran**, le YAML n'ayant servi
  qu'a verifier ensuite ce que l'ecran m'avait montre.
- **Mouvement de matrice** : `HG-CM1-05` peut passer a `couvert-valide`, **en tenant compte de ce
  que dit `HG-R24` ci-dessous sur ce que cette ligne apporte reellement**. `camille-po` decide, pas
  moi.

### 1. `BIA-5` - ma garde, aucun automate ne la tient, verifiee item par item

`theo-archi` a signale que le test qui cite `BIA-5` dans sa Javadoc **ne verifie en realite que
l'appartenance de la bonne reponse a un ensemble de quatre noms de rois**. **Il ne me couvre pas, et
je n'ai pas fait semblant qu'il me couvrait.** J'ai lu les cinq items neufs de `S42-HG-ROIS` un par
un - les trois d'ici et les deux du paquet "situer dans le temps" -, enonce, bonne reponse,
distracteurs et indice.

| Item | Ce que l'enonce demande | Jugement de valeur ? |
|---|---|---|
| 46 - *"lequel est parti combattre en croisade et est reste connu sous le nom de Saint Louis ?"* | un depart en croisade, un surnom d'usage | **non** |
| 47 - *"lequel a regne en pleine Renaissance, entre Louis IX et Henri IV ?"* | une periode de regne, une position chronologique | **non** |
| 48 - *"lequel a mis fin aux guerres de religion en signant l'edit de Nantes ?"* | la signature d'un texte et son effet | **non** |
| 26 (autre paquet) - *"lequel a regne le premier ?"* | un ordre de regne | **non** |
| 27 (autre paquet) - *"lequel a regne le dernier ?"* | un ordre de regne | **non** |

**Aucun *bon roi*, aucun *grand roi*, aucun *mauvais roi*, aucune question du type *"etait-il un bon
roi ?"*.** Les cinq indices non plus : *"Il est parti combattre loin de France, en Terre sainte."*,
*"Ce roi invite en France le peintre Leonard de Vinci."*, *"Il regne apres Francois Ier et avant
Louis XIV, parmi ces quatre rois."*, *"C'est le seul des quatre a avoir regne au Moyen Age."*,
*"C'est le roi qui fait construire le chateau de Versailles."* - cinq faits, zero appreciation.

**Un point que je verifie plutot que de le supposer** : la formule *"ces quatre rois qui ont marque
la construction du royaume"*, qui ouvre les cinq enonces, **est le libelle du referentiel lui-meme**
(`squad/programme/histoire-geographie/competences-cm1.md`, `HG-CM1-05`). *Marquer la construction du
royaume* est un constat historique, pas une appreciation morale sur un personnage. **`BIA-5` tient.**

### 2. `HIS-1`, `HIS-2`, `HIS-3` sur les cinq items neufs

- **`HIS-1`** : **zero restitution pure sur les cinq.** Trois associations (un roi a partir d'un
  fait), deux mises en ordre. Le plafond d'un tiers n'est pas approche. Le marqueur et son plafond
  au niveau du paquet entier relevent de `theo-archi`, qui les a verifies ; ce comptage-ci est le
  mien, sur les cinq items neufs.
- **`HIS-2`** : aucune des trois propositions d'aucun des cinq items n'est defendable a cote de la
  bonne. **Le probleme de l'item 47 est l'inverse, et il est plus genant : voir `HG-R23`.**
- **`HIS-3`** : **exactitude verifiee fait par fait.** Louis IX (regne 1226-1270, croisades, appele
  Saint Louis), Francois Ier (1515-1547, Renaissance, invite Leonard de Vinci en 1516), Henri IV
  (edit de Nantes, 1598), Louis XIV (dernier des quatre a regner). La chronologie des quatre rois,
  sur laquelle reposent les deux items d'ordre, est juste. **Aucune date fausse, aucune filiation
  fausse, aucun nom faux.** Les commentaires du paquet portent leur source sur chaque item neuf.
- **Les quatre rois en bonne reponse** : Louis IX (46 et 26), Francois Ier (47), Henri IV (48),
  Louis XIV (27). Tenu **sur les cinq items pris ensemble**, comme la story le demandait.

### 3. Les trois reserves, neuves et datees du 2026-09-24, **aucune bloquante**

#### `HG-R23` - l'enonce de l'item 47 nomme ses deux distracteurs et les exclut

**Item 47, lu a l'ecran** : *"Parmi ces quatre rois qui ont marque la construction du royaume, lequel
a regne en pleine Renaissance, **entre Louis IX et Henri IV** ?"*
**Les trois propositions servies, relevees a l'ecran** : *Louis IX*, *Francois Ier*, *Henri IV*.

**L'enonce nomme ses deux distracteurs comme les bornes de l'intervalle ou se trouve la reponse.**
Un enfant qui ne sait rien de la Renaissance, rien de Francois Ier, rien de Leonard de Vinci, raisonne
ainsi : *la reponse est entre Louis IX et Henri IV, donc ce n'est ni Louis IX ni Henri IV, donc c'est
le troisieme.* **Il a juste a tous les coups, et il n'a rien appris.** L'item ne peut pas etre rate.

C'est la famille de `HG-R19` - *un mot de l'enonce qui appelle la bonne reponse* -, que j'ai datee
du 2026-09-11 et gradee non bloquante. **Je la grade pareil ici, meme si la forme est plus forte** :
sur `HG-R19` l'enonce facilitait la reponse, ici il l'impose. **Je ne releve pas ma barre treize
jours apres l'avoir posee** ; j'ecris que la forme est plus forte, et c'est la premiere des trois
reserves a payer.

> **`HG-R23`.**
> - **Objet** : que l'enonce de l'item 47 cesse de nommer ses propres distracteurs. Un enfant qui
>   ne sait pas quand a regne Francois Ier doit pouvoir se tromper sur cet item.
> - **Mode de defaillance a eviter** : retirer *"entre Louis IX et Henri IV"* en laissant l'enonce
>   ne plus situer le roi dans le temps. `HG-CM1-05` demande de **situer** autant que de nommer :
>   l'enonce doit continuer a poser une position chronologique, sans employer les noms qui sont dans
>   les propositions.
> - **Perimetre** : l'enonce de l'item 47. La bonne reponse, les deux distracteurs et l'indice ne
>   bougent pas.
> - **Non bloquante.**

#### `HG-R24` - les trois items neufs reprennent chacun le couple bonne reponse / distracteurs d'un item deja dans le paquet

**Mesure faite sur les 48 items du paquet, comparaison des couples (bonne reponse, paire de
distracteurs)** :

| Item deja present (`HG-CM1-06`) | Item neuf (`HG-CM1-05`) | Couple commun |
|---|---|---|
| 23 - *"Quel roi de France a participe a une croisade ?"* | 46 - *"...lequel est parti combattre en croisade et est reste connu sous le nom de Saint Louis ?"* | Louis IX contre Francois Ier et Henri IV |
| 24 - *"Sous quel roi de France le chateau de Chambord est-il construit, temoin de la Renaissance ?"* | 47 - *"...lequel a regne en pleine Renaissance, entre Louis IX et Henri IV ?"* | Francois Ier contre Louis IX et Henri IV |
| 22 - *"Quel roi de France a signe l'edit de Nantes ?"* | 48 - *"...lequel a mis fin aux guerres de religion en signant l'edit de Nantes ?"* | Henri IV contre Louis IX et Louis XIV |

**Trois sur trois.** Meme roi en bonne reponse, memes deux distracteurs, meme fait historique. Ce
sont les **seuls** couples dupliques que les trois items neufs introduisent dans ce paquet : je l'ai
verifie sur les 48 items, pas suppose.

**Ce que cela fait a l'ecran, mesure et non deduit** : j'ai joue **40 parties de 10 manches**.
**Dans une d'entre elles, l'enfant a recu deux fois la meme question** - *"Quel roi de France a signe
l'edit de Nantes ?"* puis *"...lequel a mis fin aux guerres de religion en signant l'edit de
Nantes ?"* -, avec les trois memes noms devant lui et la meme reponse. Une partie sur quarante ; ce
n'est pas frequent, ce n'est pas nul, et un enfant le remarque immediatement.

**Ce que cela fait a la couverture, et c'est le point que `camille-po` doit lire avant de bouger la
matrice.** `HG-CM1-05` est *"nommer **et** situer dans le temps"*. Sa moitie *situer* est ouverte
par deux items neufs, corrects et reellement neufs (items 26 et 27 du paquet "situer dans le
temps"). **Sa moitie *nommer* est servie par trois reformulations d'un travail que le paquet faisait
deja sous `HG-CM1-06`.** Je ne dis pas que c'est hors competence : nommer un roi a partir de sa
realisation sert de bonne foi les deux lignes, qui se recouvrent par construction dans le
referentiel. **Je dis que la ligne se ferme sur deux items neufs et trois relibellages, et que cela
doit etre ecrit ici plutot que decouvert dans six mois.**

> **`HG-R24`.**
> - **Objet** : que la moitie *nommer* de `HG-CM1-05` porte un travail que le paquet ne faisait pas
>   deja, plutot que trois couples bonne reponse / distracteurs deja servis. Deux suffisent.
> - **Mode de defaillance a eviter** : fabriquer de la variete en changeant les distracteurs pour
>   des rois hors des quatre du libelle. Le critere de la story les borne aux *"autres rois ou
>   personnages reels de la meme periode"*, et le libelle en nomme quatre.
> - **Perimetre** : les items 46, 47 et 48 de ce paquet. Les 45 items anterieurs ne bougent pas.
> - **Non bloquante** : ce que l'enfant recoit est redondant, jamais faux.

#### `HG-R25` - *"ces quatre rois"* devant trois propositions

**Les cinq items neufs ouvrent tous par *"Parmi ces quatre rois qui ont marque la construction du
royaume..."*. La mecanique en affiche trois.** Rien a l'ecran ne nomme les quatre rois : ni le titre
*"Nommer, en histoire"*, ni la consigne *"Nomme ce que l'enonce decrit."*, ni l'enonce. **Le *ces*
ne pointe vers rien que l'enfant puisse voir, et le nombre qu'il annonce n'est pas celui des boutons
qu'il a devant lui.** Je l'ai constate a l'ecran sur les cinq items, pas dans le YAML.

**Le paquet porte deja la bonne forme, et c'est elle qui rend l'ecart visible** : *"Parmi ces trois
faits de l'histoire de France, lequel a eu lieu le plus tot ?"*, *"Parmi ces trois evenements de la
Revolution francaise..."* - **trois annonces, trois boutons**. Le paquet "questionner le monde" que
j'ai audite le meme jour fait pareil (*"Parmi ces trois matieres..."*). **Ce n'est donc pas une barre
neuve : c'est la forme etablie du depot, que les cinq items neufs sont les seuls a ne pas suivre.**

**Ce que j'ai verifie avant de ne pas en faire une reserve bloquante** : sur les deux items d'ordre
(26 et 27 du paquet "situer dans le temps"), la bonne reponse est la meme qu'on raisonne sur les
trois rois affiches ou sur les quatre du libelle. **Aucun enfant ne sera induit vers une mauvaise
reponse par cet ecart.** Il sera seulement mis devant un texte qui ne decrit pas son ecran.

> **`HG-R25`.**
> - **Objet** : que les cinq enonces de `S42-HG-ROIS` cessent d'annoncer un nombre que l'ecran ne
>   montre pas - soit en disant *trois*, soit en ne comptant pas.
> - **Mode de defaillance a eviter** : ecrire *"Parmi ces trois rois"* sur les items d'ordre sans
>   verifier que la bonne reponse reste la meme parmi les trois affiches. Sur les items 26 et 27
>   elle l'est aujourd'hui ; sur un item futur, ce n'est pas automatique.
> - **Perimetre** : la clause d'ouverture des cinq enonces neufs. Rien d'autre.
> - **Non bloquante.**

### 4. Ce dont je reste la seule garde sur ces items

| Propriete | Garde executable ? |
|---|---|
| **`BIA-5` - qu'aucun enonce ne porte un jugement de valeur sur un roi** | **aucun.** Le test qui cite `BIA-5` ne verifie que l'appartenance de la bonne reponse a quatre noms (`theo-archi`, s42). **Un enonce disant *"lequel fut le plus grand roi ?"* passerait vert.** C'est moi, item par item |
| **Qu'un enonce ne nomme pas ses propres distracteurs** (`HG-R23`) | **aucun.** C'est moi, en lisant l'enonce a cote de ses propositions |
| **Qu'un item neuf n'en reproduise pas un deja present** (`HG-R24`) | **aucun.** Rien ne compare deux items d'un meme paquet. Je l'ai mesure a la main sur les 48. **Route a `theo-archi` comme symptome** : le meme couple bonne reponse / distracteurs deux fois dans un paquet est mecaniquement detectable, la ou le juger ne l'est pas |
| **Qu'un enonce decrive l'ecran qu'il accompagne** (`HG-R25`) | **aucun.** Aucun test ne rapproche le mot *quatre* d'un enonce du nombre de boutons rendus |
| **Les reserves `HG-R*` anterieures** | inchangees, voir la fiche de l'ancetre et le bloc du 2026-09-11 |
