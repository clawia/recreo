# Fiche de conformite - Une moquerie qui se repete (`bandit-manchot-exclusion-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-emc-exclusion` (11 items)
- **Statut global** : **REFUSE**
- **Derniere mise a jour** : 2026-09-07

> **Fiche ouverte par `nadia-prof` et non par `camille-po`** : le paquet est neuf et sa fiche
> n'existait pas. Sections descriptives reprises de la story `S31-2` et du referentiel ; **le bloc
> de validation est le mien**.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Enseignement moral et civique | CP (cycle 2) | Respecter autrui | Reconnaitre une situation de moquerie repetee ou d'exclusion et savoir a quel adulte en parler | `EMC-CP-03`, `squad/programme/enseignement-moral-et-civique/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S31-2`.

- [x] Onze items, `nombreDeManches: 10` (`DEC-0064`)
- [ ] **Deux taches distinctes (`FER-3`) : reconnaitre la situation decrite comme une moquerie
      repetee **ou un accident isole**, et identifier l'adulte selon le lieu** - *non tenu, voir le
      motif*
- [x] La bonne reponse n'est jamais une solution que l'enfant appliquerait seul (`EMC-3`)
- [x] Aucun item ne met l'enfant en position de victime ou d'auteur ; aucune violence detaillee
- [ ] **`CAT-1` : les trois propositions sont du meme registre** - *non tenu sur les deux taches*
- [ ] **`FAB-1` : deux items d'une meme tache ne portent jamais la meme bonne reponse mot pour mot**
      - *non tenu sur la tache A*
- [x] `EMC-1`, `EMC-8`, `EMC-10` tenues

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-07
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree `s31-nadia-A` a `3181b5e6`, port
  8082. **Une partie complete de dix manches, deux erreurs volontaires par manche**, plus la lecture
  du paquet entier. Le tirage m'a servi **cinq items de la tache A et cinq de la tache B**.
- **Audit degrade sur un point, et je le declare** : lecture du **HTML servi**, pas de la page
  rendue par un navigateur. **Le motif de ce refus ne repose sur aucun jugement visuel** : il est
  entierement textuel et se recompte sur le fichier.

### Ce qui a ete verifie

- **Ancrage au programme** : **c'est ici que le paquet decroche.** Le libelle demande deux choses :
  *"**reconnaitre** une situation de moquerie repetee ou d'exclusion"* **et** *"savoir a quel adulte
  en parler"*. **La seconde moitie est servie. La premiere ne l'est pas.**
  Les six items de la tache A decrivent tous, sans exception, une moquerie ou une exclusion
  **repetee** - *"chaque midi depuis une semaine"*, *"presque tous les jours"*, *"depuis plusieurs
  jours"*, *"toutes les semaines"*, *"depuis le debut du mois"*, *"chaque jour cette semaine"*.
  **Aucun item ne decrit un accident isole, un jeu ordinaire ou un desaccord banal.** L'enfant n'a
  donc jamais a *reconnaitre* quoi que ce soit : il n'y a rien a distinguer. La repetition, qui est
  le critere meme de la competence, est presente partout et ne discrimine rien.
  **Et le critere du backlog disait exactement le contraire** : *"reconnaitre la situation decrite
  comme une moquerie repetee **ou un accident isole**"*. La tache livree n'est pas celle-la.
- **`FAB-1a` - non tenue, et c'est le defaut qui a produit mes deux refus de s30.** Les six items de
  la tache A portent **la meme bonne reponse, mot pour mot** : *"en parler a un adulte"*. Ce que
  `FAB-1a` dit, ecrit le matin meme et **avant que ce paquet existe** : *"deux items ne portent pas
  la meme bonne reponse ; une tache dont tous les items ont la meme bonne reponse compte pour **un**
  item au titre de `FER-2` et de `FER-3`, quel que soit le nom, le lieu ou le nombre qui change dans
  l'enonce."*
  **Et `FER-3`, ecrite le 2026-09-03, disait deja la meme chose** : *"changer de nom dans la meme
  phrase n'en est qu'une [tache]"*. Le defaut ne depend donc pas d'une clause de ce matin.
- **`FER-2` - non tenue, une fois `FAB-1a` appliquee.** Tache A : six items, une bonne reponse,
  **compte pour 1**. Tache B : cinq items, trois bonnes reponses distinctes qui varient selon le
  lieu, **compte pour 5**. **Total : 6 items comptables, contre un plancher de dix.** La ligne ne
  peut pas fermer.
- **`CAT-1` - non tenue, sur les deux taches, et je l'ai constate en jouant.**
  *Tache A* : la bonne reponse est la seule proposition prudente ; les douze distracteurs sont **les
  seules propositions negligentes ou agressives** - *"penser que c'est un hasard et ne rien dire"*,
  *"regler ca toute seule avec Malo"*, *"se moquer de Hugo a son tour"*, *"imiter le groupe a son
  tour pour voir ce que ca fait"*, *"attendre Hugo a son tour pour se venger"*, *"forcer un camarade
  a lui donner la main"*. **C'est mot pour mot la definition de `CAT-1`** : *"un item dont la bonne
  reponse est la seule proposition polie, prudente ou bienveillante, et dont les distracteurs sont
  les seules propositions grossieres, dangereuses ou egoistes, se gagne sans la competence."*
  *Tache B* : les cinq items portent deux adultes et **une proposition *"a personne, ..."*** -
  *"ce n'est pas grave"*, *"elle reglera ca seule"*, *"ca arrive tous les jours"*, *"il oubliera
  vite"*, *"ce n'est pas son probleme"*. Un enfant ecarte cette troisieme par le ton sur **5 items
  sur 5**, sans savoir quel adulte est le bon. **La tache B se joue a pile ou face** : il reste deux
  adultes a chaque item.
- **`EMC-3` - tenue, et je le dis clairement parce que c'est la condition delicate.** Aucun item ne
  met l'enfant qui joue en position de victime ou d'auteur : chaque scene est vue par un personnage
  temoin, entre deux autres personnages. Aucune violence n'est detaillee. **Aucune proposition, sur
  aucun item, ne suggere a l'enfant de regler la situation seul en position de bonne reponse.**
  Le plancher de securite que `EMC-3` protege est **intact**, et rien dans ce refus ne le remet en
  cause.
- **Lisibilite au niveau vise** : les onze enonces vont de **22 a 31 mots**, six d'entre eux en
  **trois phrases** (une situation, un temoin, une question). Les deux paquets d'EMC CP que j'ai
  fermes le 2026-09-05 plafonnaient a 15 et 14 mots. **31 mots en trois phrases est le plus lourd
  enonce de CP de ce depot.** Ce n'est pas le motif du refus, mais il s'y ajoute et il devra etre
  traite dans la meme reecriture.
- **Charge cognitive** : elevee et pour rien. L'enfant tient une scene a deux personnages, une duree
  (*"depuis le debut du mois"*), un temoin et un lieu - puis choisit une reponse qui est la meme a
  tous les items.
- **Traitement de l'erreur** : correct sur le plan mecanique - indice apres une erreur, revelation
  apres trois, l'enfant avance toujours. **Mais les six indices de la tache A ne font que repeter la
  duree que l'enonce vient de donner** (*"Cela revient chaque semaine, pas juste une fois."*,
  *"Cela dure depuis le debut du mois, a chaque recreation."*). Ils n'ajoutent rien, parce qu'il n'y
  a rien a ajouter : la reponse ne depend pas de la duree.
- **Exactitude du contenu** : **aucune erreur de fait.** Les trois adultes de la tache B sont
  exacts et adaptes au lieu, et le paquet ne prescrit rien de dangereux. **Ce refus ne porte pas sur
  une erreur factuelle.**
- **Biais et ton** : aucun stereotype de genre, d'origine ou de milieu ; les roles de temoin,
  d'auteur et de victime tournent sur les cinq personnages. **`EMC-1`, `EMC-8` et `EMC-10`
  tenues.**

### Motif

**Trois motifs, tous actionnables, aucun ne demandant a etre reinterprete.**

**1. La premiere moitie de la competence n'est pas travaillee.** Le libelle demande de
*reconnaitre* une moquerie repetee ou une exclusion. Les six items de la tache A decrivent tous une
moquerie repetee ; **aucun ne decrit un accident isole, une maladresse ou un jeu ordinaire**. Il n'y
a donc aucune reconnaissance a faire. Le critere du backlog demandait explicitement *"une moquerie
repetee **ou un accident isole**"* ; il n'a pas ete servi.

**2. La tache A porte six fois la meme bonne reponse, mot pour mot.** *"En parler a un adulte"* est
la reponse des items 1 a 6. C'est `FAB-1a`, ecrite avant l'ouverture du sprint, et c'est deja
`FER-3` du 2026-09-03 (*"changer de nom dans la meme phrase n'en est qu'une"*). **La tache compte
pour un item ; le paquet en compte six comptables sur dix requis ; `FER-2` echoue.** En jouant, j'ai
vu ce que cela donne a l'ecran : des la deuxieme manche de tache A, l'enfant presse *"en parler a un
adulte"* sans lire.

**3. `CAT-1` n'est pas tenue, sur les deux taches.** Sur la tache A, la bonne reponse est la seule
proposition prudente contre douze distracteurs negligents ou agressifs. Sur la tache B, une
proposition *"a personne, ..."* est ecartable au ton sur cinq items sur cinq, ce qui reduit chaque
item a un choix entre deux adultes. **Le commentaire du paquet declare `CAT-1` tenue et affirme que
les trois reactions sont serieusement envisageables ; ce n'est pas ce que le jeu sert.** C'est
precisement pour cela que je joue plutot que je lis.

**Ce qui doit changer, et il n'y a pas de piege** :

> **La tache A devient une tache de discrimination.** Elle sert des situations de deux sortes - une
> moquerie ou une exclusion qui se repete, et un incident isole ou un jeu ordinaire - et l'enfant
> dit laquelle il a devant lui. **Sa bonne reponse varie alors d'un item a l'autre**, et la duree
> cesse d'etre un decor pour devenir le critere.
>
> **La tache B garde sa forme et perd sa troisieme proposition au rabais.** Les trois propositions
> sont trois adultes, dont deux ne conviennent pas au lieu decrit. *"A personne"* n'est pas un
> candidat : c'est un bouton que l'enfant ecarte au ton (`PLA-1`, `CAT-1`).

### Ce que je corrige dans ma propre clause, avant que quiconque me l'oppose

**`EMC-3`, telle que je l'ai ecrite le 2026-09-03, est mal operationnalisee, et le defaut est le
mien.** Elle dit : *"la bonne reponse est **toujours** en parler a un adulte, jamais une solution que
l'enfant appliquerait seul"*. **Le motif que j'ai ecrit dessous** est un plancher de securite :
*"un exercice ne doit ni les mettre en scene, ni leur faire porter la responsabilite de s'en
sortir"*.

**J'ai fige une chaine de caracteres la ou je voulais interdire une categorie de reponses.** C'est
la meme faute que sur `SCI-4`, ou j'avais compte des propositions au lieu d'exiger des candidats.
Prise a la lettre, `EMC-3` rend `FAB-1a` et `FER-3` impossibles a tenir sur cette ligne, et c'est
exactement le mur contre lequel `marc-dev` est arrive.

> **Lecture qui fait foi, ecrite ici et datee du 2026-09-07** : `EMC-3` est une **interdiction
> portant sur les propositions**, pas une prescription portant sur la bonne reponse. **Aucune
> proposition d'aucun item ne dit a l'enfant de regler la situation seul**, en bonne reponse comme
> en distracteur - c'est le plancher, et il ne bouge pas. **En revanche, la bonne reponse d'un item
> de reconnaissance peut etre un classement** (*"c'est une moquerie qui se repete"*, *"c'est un
> accident"*), qui n'est pas une solution que l'enfant applique et ne l'expose donc a rien.

**Ceci elargit ma clause, il ne la durcit pas** : je ne peux pas m'en servir pour refuser quelque
chose que `EMC-3` autorisait hier. **Ce n'est pas un deplacement de poteau, c'est l'ouverture d'une
porte que j'avais fermee par erreur** - et je l'ecris dans le verdict qui refuse, pour qu'on ne
puisse pas dire que je l'ai trouvee apres coup.

### La question que `marc-dev` a remontee plutot que tranchee - ma reponse

**Il a bien fait de la remonter, et sa lecture est bonne - sur la tache B.**

Sa question : le critere *"la bonne reponse est toujours en parler a un adulte"* se lit-il au pied
de la lettre, alors que la tache B doit nommer l'adulte precis selon le lieu ?

**Reponse : la lecture par categorie d'action est la bonne, et je la confirme.** Nommer *"a sa
maitresse"*, *"a un surveillant de la cour"*, *"a l'adulte qui surveille la cantine"* **est** *"en
parler a un adulte"* au sens de `EMC-3`. Mon propre arbitrage prealable nommait cette tache mot pour
mot (*"identifier l'adulte selon le lieu"*, section 3.1). **La tache B n'est pas ce qui refuse ce
paquet.**

**Mais la lecture par categorie n'a pas ete appliquee a la tache A, et c'est la que le paquet
tombe** : la tache A a garde la lecture litterale, donc la reponse invariante, donc `FAB-1a`. **La
question etait la bonne ; elle a ete posee sur la moitie du paquet qui n'en avait pas besoin.**

### Reserves

**Aucune.** Un paquet refuse ne porte pas de reserves : ses defauts sont dans le motif, et ils sont
tous a corriger dans le meme geste. **La longueur des enonces (22 a 31 mots) et les indices
redondants de la tache A ne sont pas des reserves separees** : ils entrent dans la reecriture, et je
les reverifierai au rejeu.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. `EMC-CP-03` reste **`non couvert`**.

### Mentions de non-couverture opposables

**Sans objet** : `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`CAT-1`** | **aucun**, et c'est elle qui a produit deux des trois motifs de ce refus |
| **`EMC-3`** - la posture des items sur l'exclusion | **aucun** |
| **`PLA-1`** - *"a personne"* n'est pas un candidat | **aucun** |
| **`FAB-1a`** - pas de bonne reponse invariante dans une tache | **aucun a ce jour**, et c'est le plus rentable de mes routages a `theo-archi` : ce defaut se compte. **Un garde-fou sur `FAB-1a` aurait rougi sur ce paquet avant moi** |
| **La couverture reelle de la premiere moitie du libelle** (*reconnaitre*) | **aucun**, et c'est structurel : rien dans le YAML ne dit quelle moitie d'un libelle un item sert |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-03` reste `non couvert`** | `camille-po` | La ligne ne ferme pas ; le compte de la matrice lui appartient |
| **Les trois motifs de ce refus** | `camille-po`, via `sofia-sm` | En faire du backlog. **Un refus ne bloque pas le sprint suivant** ; il ne se paie pas non plus tout seul |
| **La lecture de `EMC-3` du 2026-09-07** (interdiction sur les propositions, non prescription sur la bonne reponse) | `camille-po` | A reprendre dans le critere de la story qui reecrit ce paquet, sans quoi le meme mur se represente |
| **`FAB-1a` mordable** | `theo-archi` | Deja routee le 2026-09-07. Ce paquet est la preuve que la CI verte ne la voit pas. Je dis la propriete, jamais la forme du test |
