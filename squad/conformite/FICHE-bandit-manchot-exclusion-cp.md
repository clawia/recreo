# Fiche de conformite - Une moquerie qui se repete (`bandit-manchot-exclusion-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-emc-exclusion` (11 items)
- **Statut global** : **VALIDE SOUS RESERVE** (REFUSE le 2026-09-07, **refus leve au reaudit du
  2026-09-08** - les deux blocs de validation sont lisibles ci-dessous, dans cet ordre). **Etat des
  reserves au 2026-09-10** : `EXC-R2`, `EXC-R3` **partiellement** et `EXC-R4` traitees, **`EXC-R5`
  LEVEE**, `EXC-R1` **toujours non payee**, `EXC-R6` **ouverte**, et **`EXC-R7`, `EXC-R8`, `EXC-R9`
  ouvertes le 2026-09-10** - toutes non bloquantes, blocs de constat en fin de fiche. **La ligne
  n'est pas rouverte.** **L'increment `S33-EXC-R5` est REFUSE**, et son motif est a mon debit avant
  d'etre a celui du lot : la sortie que ma propre formule prescrivait retire du paquet la seconde
  moitie du libelle de la ligne (bloc du 2026-09-10).
- **Etat au 2026-09-11** : **l'increment `S34-EXC-TACHEB` est REFUSE** sur `EXC-R10` - les cinq
  indices de la tache B n'ont pas ete touches et repondent a la question que la refonte supprime.
  **La ligne `EMC-CP-03` n'est pas rouverte** et reste `couvert-valide`. Deux reserves neuves,
  `EXC-R11` et `EXC-R12`, non bloquantes. Bloc de validation du 2026-09-11 en fin de fiche.
- **Derniere mise a jour** : 2026-09-11

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

---

## Bloc de validation pedagogique - reaudit du 2026-09-08 - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute au bloc du
> 2026-09-07 et ne le remplace pas** : un refus efface n'a aucune valeur de preuve, et l'histoire de
> cette ligne doit rester lisible.

- **Verdict** : **VALIDE SOUS RESERVE** - **le REFUSE du 2026-09-07 est leve.**
- **Date** : 2026-09-08.
- **Arbre reaudite** : `round-two` a `60bde989`, worktree dedie `s31-nadia-A2`. Correctif de
  `marc-dev`.
- **Audit joue** : **oui, deux parties completes de dix manches.** Jar reconstruit depuis mon
  worktree, port 8092. La premiere partie a **deux erreurs volontaires par manche** ; la seconde a
  **trois erreurs par manche, donc dix revelations**. **Les onze items ont ete vus a l'ecran** entre
  les deux parties, et tous ont ete lus dans le fichier, y compris celui que le second tirage n'a
  pas servi.
- **Audit degrade sur un point, et je le declare** : lecture du **HTML servi**, pas de la page
  rendue par un navigateur. **Aucun element de ce verdict ne repose sur un jugement visuel.**
- **Porte technique** : rouge sur cet arbre, pour un motif etranger au contenu (`ZZZ-9` cite en
  preuve dans un rapport de ceremonie). **J'ai verifie moi-meme, plutot que de le tenir de
  quelqu'un**, que ce paquet passe ses propres garde-fous : `PaquetCpEmcExclusionTest`, **6 tests,
  0 echec** (`-pl adapters/adapter-content -am`), et les deux garde-fous d'`ADR-0086`, **7 tests,
  0 echec** (`-pl bootstrap -am`). Les deux mesures sont partielles, portent `-am`, et ne remplacent
  pas `mvn -B clean verify`.

### Mes trois motifs de refus, reverifies un par un

**1. La premiere moitie du libelle - *reconnaitre* - est desormais travaillee. Motif paye.**
La tache A n'est plus une suite de six moqueries repetees : elle sert **trois situations de moquerie
ou d'exclusion qui se repete** (*chaque midi cette semaine*, *presque tous les jours*, *depuis
plusieurs jours a chaque recreation*) et **trois situations d'incident isole** (*une seule fois et
s'excuse aussitot*, *une seule fois par accident*, *une fois sans faire expres*). **L'enfant a
quelque chose a distinguer, et la duree est devenue le critere au lieu du decor.** La bonne reponse
est un **classement**, jamais une action - ce que la lecture de `EMC-3` que j'ai ecrite le 2026-09-07
autorise expressement.
**`FER-1`** : *moquerie repetee*, *exclusion* (empecher de s'asseoir, ecarter du jeu) et *l'adulte*
sont chacun servis en bonne reponse. **`FER-3`** : deux taches, la moins servie en porte cinq.

**2. `FAB-1a` est tenue, et je donne le compte au lieu de le declarer.**

| Tache | Items | Bonnes reponses distinctes | Comptables au titre de `FER-2` |
|---|---|---|---|
| A - classer la situation | 6 | **2** (*moquerie qui se repete* x3, *accident isole* x3) | **6** |
| B - nommer l'adulte du lieu | 5 | **3**, chacun au moins une fois | **5** |
| | | | **11, sur un plancher de dix - `FER-2` tenue** |

**Aucune tache n'a de bonne reponse invariante**, donc la regle de reduction de `FAB-1a` (*"une
tache dont **tous** les items ont la meme bonne reponse compte pour un"*) ne mord sur aucune des
deux. Le compte de 6 items comptables sur 10 qui refusait ce paquet le 2026-09-07 **est remonte a
11**.

**Et je nomme la lecture que je n'applique pas.** Prise a la lettre, la premiere phrase de `FAB-1a`
condamnerait une tache ou trois items disent *moquerie* et trois disent *accident*. **Elle ne
s'applique pas, et ce n'est pas une faveur trouvee ce soir** : le meme paragraphe, ecrit avant que
les lots existent, exempte nommement *"un classement a categories fermees (...) des lors que la
bonne reponse varie"*. **C'est moi qui ai demande que cette tache devienne un classement ; je ne
peux pas commander une forme puis la refuser au nom d'une clause qui l'exempte.**

**`FAB-1c`** : sur la tache A, chaque bonne reponse est servie **en distracteur** sur les items de
l'autre categorie ; sur la tache B, les trois memes chaines tournent en bonne reponse et en
distracteur. **Aucune amorce, aucune longueur, aucun registre ne designe la bonne reponse : zero
item sur onze.**

**3. `CAT-1` est tenue sur les deux taches, et c'est le point que j'ai verifie le plus
attentivement** - un correctif qui repare `FAB-1a` en laissant la bonne reponse reconnaissable a son
ton ne repare rien de ce que je reprochais.

*Tache A, releve a l'ecran, dans l'ordre reel des propositions* : les trois propositions de chaque
item sont **trois noms de situation** - *une moquerie qui se repete* / *un accident isole* / une
troisieme categorie (*une bagarre*, *une blague entre amis*, *un jeu de recreation*, *une dispute*,
*une vengeance*). **Il n'y a plus une seule attitude a l'ecran.** Les douze propositions negligentes
ou agressives que j'avais relevees - *"se moquer de Hugo a son tour"*, *"attendre Hugo a son tour
pour se venger"*, *"forcer un camarade a lui donner la main"* - **ont toutes disparu**. Ce qui
distingue les propositions est ce qu'elles disent, jamais comment elles sonnent.

*Tache B* : la proposition au rabais *"a personne, ..."*, ecartable au ton sur cinq items sur cinq,
**n'existe plus**. Les trois propositions de chaque item sont **les trois memes adultes reels**,
dont deux ne conviennent pas au lieu. **Aucun ton ne les separe, et il n'y a plus de bouton a
ecarter sans savoir.**

### Le reste de ce que j'ai verifie

- **Exactitude** : aucune erreur de fait. Les trois adultes sont reels et rattaches a leur lieu.
- **Lisibilite** : les onze enonces font **15 a 19 mots**, contre 22 a 31 sur la version refusee, et
  huit d'entre eux tiennent en une seule phrase de situation suivie de la question. **C'est au
  niveau des deux paquets d'EMC CP que j'ai fermes le 2026-09-05.** Les deux plus longs font l'objet
  de `EXC-R4`.
- **Charge cognitive** : basse. Une scene courte, une question stable, trois categories.
- **Traitement de l'erreur** : correct et verifie a l'ecran sur dix manches. Indice apres une
  erreur, revelation apres trois (*"Appuie sur la bonne reponse : ..."*), l'enfant avance toujours
  et la partie va jusqu'a son terme. **Les six indices de la tache A ne repetent plus la duree de
  l'enonce : ils disent le critere** (*"Une seule fois serait un hasard ; la, cela recommence chaque
  jour."*, *"Une excuse immediate apres un geste involontaire n'est pas de la moquerie."*), ce que je
  demandais. **Ceux de la tache B, non - c'est `EXC-R2`.**
- **`EMC-1`, `EMC-3`, `EMC-8`, `EMC-10`** : tenues. Le plancher de securite de `EMC-3` est intact -
  **aucune proposition, sur aucun item, en bonne reponse comme en distracteur, ne dit a l'enfant de
  regler la situation seul**, et la tache A ne propose plus aucune action du tout.
- **Biais et ton** : aucun stereotype, aucune condescendance ; les roles de temoin, d'auteur et de
  victime tournent sur les cinq personnages fictifs.

### Motif du Done

> **Les trois motifs qui refusaient ce paquet le 2026-09-07 sont payes, et je les ai recomptes
> plutot que declares tenus.** La competence est travaillee dans ses deux moities, la bonne reponse
> varie, rien ne se gagne au ton ni a la forme, les faits sont exacts et les enonces sont au niveau.
> **Done pedagogique prononce ; `EMC-CP-03` peut passer a `couvert-valide`.**

### Reserves - cinq, neuves, datees du 2026-09-08, aucune bloquante

**Elles sont neuves et je les ecris comme telles, jamais comme un durcissement de ce que j'avais
ecrit.** Le refus est leve **sur les motifs que j'avais poses** ; ces cinq points n'en faisaient pas
partie et ne rouvrent pas la ligne.

| Reserve | Ce qu'elle demande | Comment je l'ai trouvee |
|---|---|---|
| **`EXC-R1`** | **La consigne du jeu annonce la tache que le paquet sert.** L'entree de catalogue dit encore *"Lis bien, puis dis ce qu'il faut faire, ou a qui en parler."* : c'est la consigne de la version refusee. La tache A ne demande plus ce qu'il faut faire, elle demande de nommer la situation, et **aucune des trois propositions n'est une action** - l'enfant, et surtout l'adulte qui lui lit la consigne, cherchent donc a l'ecran quelque chose qui n'y est pas | **En jouant.** Elle s'affiche sur **les dix manches**, et rien dans la CI ne compare une consigne de catalogue a la tache de son paquet |
| **`EXC-R2`** | **Chaque indice de la tache B ajoute ce que l'enonce ne donne pas.** Les cinq indices ne redonnent que le lieu deja ecrit dans l'enonce, et sur deux items ils reprennent les mots memes de la bonne reponse : *"Dans la cour, ..."* -> *"C'est un adulte present dans la cour pendant la recreation."* -> *a un surveillant de la cour*. Un enfant qui n'a pas compris apprend a apparier deux mots, pas a qui s'adresser | **En jouant**, aux dix revelations |
| **`EXC-R3`** | **La troisieme proposition de la tache A est un candidat serieux sur chaque item** (`PLA-1`). Elle ne l'est pas sur trois d'entre eux : *une bagarre* la ou personne ne se bat, *une dispute* sur un pied ecrase par accident, *une vengeance* sur un geste que l'enonce dit *"sans faire expres"*. **Ces trois items redeviennent binaires de fait** | En jouant, puis recomptee item par item |
| **`EXC-R4`** | **Les deux enonces les plus longs de la tache B (17 et 19 mots) disent leur situation en une seule proposition principale**, comme les trois autres (15 et 16 mots) : *"Dans la cour, avant la classe, ..."* et *"En classe, a la fin de la journee, ..."* posent deux circonstances avant la question. **Meme forme que `EMO-R1`, ouverte hier sur le meme motif ; je ne cree aucune barre chiffree**, qui est due dans mon arbitrage prealable de s32 | Comptee sur les onze enonces |
| **`EXC-R5`** | **Aucun item ne fait d'un adulte reel une reponse fausse.** *"A sa maitresse"* est marque faux sur les trois items ou la scene n'est pas en classe, alors qu'un enfant qui parle a sa maitresse d'une moquerie de la cour fait ce qu'il faut (`EMC-5`). Le critere reel est la **presence** de l'adulte : que la question le dise (*quel adulte est la a ce moment ?*) plutot que *"a qui doit-il en parler ?"* | **En jouant, et je ne l'avais pas vue au premier passage** |

**`EXC-R5` est une reserve que j'ouvre contre mon propre constat de la veille, et je l'ecris en
toutes lettres.** Le 2026-09-07 j'ai porte au credit de ce paquet que *"les trois adultes de la
tache B sont exacts et adaptes au lieu"*, j'ai confirme la lecture par categorie d'action **sans
reserve**, et j'ai demande que cette tache **garde sa forme**. Elle est inchangee depuis. **Je
l'avais creditee trop vite ; je ne peux pas la refuser aujourd'hui pour la forme que j'ai moi-meme
prescrite**, donc elle est une reserve et non un motif. Ce qui attenue le defaut, et je le dis
aussi : sur une partie entiere, *a sa maitresse* est la **bonne** reponse deux fois, ce qui enseigne
*"cela depend de qui est la"* plutot que *"pas la maitresse"*.

**Regime** : les cinq voyagent dans **un seul rejeu borne du paquet entier** (`PAR-10`). **Ce rejeu
n'est pas payable a s31** : ma borne de 4,0 est atteinte exactement avec les deux rejeux de ce soir.

### Mentions de couverture partielle

**Aucune.** Les deux moities du libelle sont servies : *reconnaitre une situation de moquerie
repetee ou d'exclusion* par la tache A, *savoir a quel adulte en parler* par la tache B.
**`EMC-CP-03` ferme sans mention.**

### Mentions de non-couverture opposables

**Une, et elle vaut pour toutes les lignes d'EMC** (refus n°13 de mon arbitrage prealable de s31) :
**cette ligne n'atteste aucune conduite.** Elle dit qu'un enfant sait **nommer** une moquerie qui se
repete et **designer** l'adulte present ; elle ne dit pas qu'il le fera.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`CAT-1`** | **aucun**, et c'est elle qui avait produit deux des trois motifs du refus. **Tenue ce coup-ci, verifiee a l'ecran et non dans le commentaire du paquet** |
| **`PLA-1`** - qu'une proposition soit un candidat | **aucun** pour la tache A ; la tache B est desormais mordue par `laTacheDAdulteNeSertPlusDeCandidatAuRabais`, qui epingle ses propositions aux trois adultes reels. **`EXC-R3` est hors de portee de ce test** |
| **`FAB-1a`** | **mordue par paquet** depuis ce correctif (`fab1aLaTacheDeClassementSertSesDeuxCategoriesSansReponseInvariante`). Un garde-fou general reste route a `theo-archi` |
| **La coherence entre la consigne du catalogue et la tache du paquet** | **aucun**, et `EXC-R1` a traverse une reecriture complete, deux tests neufs et une CI que rien n'a alerte. **C'est le plus rentable des routages de ce reaudit** |
| **Qu'un indice ajoute quelque chose a l'enonce** | **aucun** (`EXC-R2`) |
| **`EMC-5`** - aucun item a deux reponses defendables | **aucun** (`EXC-R5`), **et je ne l'avais pas vue moi-meme au premier passage** |
| **La couverture reelle de chaque moitie du libelle** | **aucun**, et c'est structurel : rien dans le YAML ne dit quelle moitie d'un libelle un item sert |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-03` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | Le compte de la matrice lui appartient ; **je n'y touche pas** |
| **`EXC-R1` a `EXC-R5`** | `camille-po`, via `sofia-sm` | En faire des stories, **un seul rejeu borne pour les cinq**, a financer a s32. **`EXC-R1` est celle que je corrigerais la premiere** |
| **La section *Criteres pedagogiques attendus* porte encore trois cases non cochees du 2026-09-07** | `camille-po` | Les trois sont desormais tenues, avec leurs comptes ci-dessus. **Cette section est la sienne ; je ne la reecris pas** |
| **Rien ne compare la consigne d'une entree de catalogue a la tache que son paquet sert** | `theo-archi` | Symptome : `EXC-R1`. **Je dis la propriete, jamais la forme du test** |
| **Cinq identifiants alloues par moi** (`EXC-R1` a `EXC-R5`, prefixe `EXC` neuf et verifie libre) dans `squad/decisions/REGISTRE-identifiants.md`, table de la section 4 | `theo-archi` | **Allocation mecanique deja faite**, dans le meme geste que ce verdict (`PAR-5`, `ADR-0085`) ; signalee pour etre relue, pas pour etre refaite |


---

## Bloc de constat de paiement - 2026-09-09 - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute aux blocs du
> 2026-09-07 et du 2026-09-08 et n'en retouche aucun.** Il porte sur le correctif `S32-4` de
> `lea-dev` (`ba2871ea`) et sur l'instruction de `theo-archi` (`63590b33`).

- **Verdict** : **VALIDE SOUS RESERVE - maintenu.** **`EMC-CP-03` reste `couvert-valide` et n'est pas
  rouverte.**
- **`EXC-R2` : LEVEE.**
- **`EXC-R4` : LEVEE** sur les deux enonces qu'elle nommait.
- **`EXC-R3` : LEVEE SUR UN ITEM SUR TROIS, MAINTENUE SUR DEUX.** Le remplacement a change les mots
  sans changer la propriete sur les items 5 et 6, et je le montre avec les mots de ma propre reserve.
- **`EXC-R1` : NON PAYEE**, et c'est ecrit dans le paquet par `lea-dev` comme par `theo-archi`. **Le
  defaut est intact a l'ecran, je l'ai revu sur les dix manches.**
- **`EXC-R5` : NON PAYEE.** L'obstacle technique est leve, le contenu est inchange, et **le defaut
  est intact** : je l'ai rejoue.
- **`EXC-R6` : ouverte**, datee du 2026-09-09, non bloquante - le troisieme enonce trop long, que ma
  reserve n'avait pas compte.
- **Date** : 2026-09-09.
- **Arbre audite** : `round-two` a `fd19d18f`, worktree dedie `s32-nadia-b`.
- **Audit joue** : **oui, une partie complete de dix manches**, erreurs volontaires jusqu'a
  epuisement des propositions sur chaque manche dont la premiere reponse n'etait pas la bonne, donc
  **les dix consignes vues et tous les indices lus** - les deux objets de `EXC-R1` et `EXC-R2`. Jar
  reconstruit depuis mon worktree, JDK de la toolchain 21 (21.0.10 rapporte par la machine
  virtuelle), port local. Le tirage m'a servi **dix items sur onze**. **Releve integral dans
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-B.md`,
  section 8.**
- **Audit degrade sur un point, declare** : lecture du **HTML servi**, pas de la page rendue par un
  navigateur.
- **Porte technique** : **les quatre jobs de la CI lances sur mon diff et nommes un par un** -
  `prefixes`, `chemins`, `outils`, `build`. Chiffres dans mon releve, section 9.

### `EXC-R2` est levee

Ma reserve disait que les cinq indices de la tache B *"ne redonnent que le lieu deja ecrit dans
l'enonce, et sur deux items ils reprennent les mots memes de la bonne reponse"*.

| Item | Indice condamne | Indice servi aujourd'hui |
|---|---|---|
| 7 | *"C'est un adulte present dans la cour pendant la recreation."* | *"Cette personne est la pour veiller sur les eleves qui jouent."* |
| 8 | *"C'est l'adulte present avec la classe a ce moment-la."* | *"C'est elle qui reste avec les eleves toute la journee."* |
| 9 | *"C'est un adulte present au moment du repas."* | *"Cette personne veille sur les eleves qui mangent."* |
| 10 | *"C'est un adulte present dans la cour a ce moment-la."* | *"Son role est de veiller sur les eleves pendant les recreations."* |
| 11 | *"C'est l'adulte present avec la classe a ce moment-la."* | *"C'est elle qui est avec ses eleves tous les jours."* |

**Les cinq disent desormais le role de l'adulte aupres des eleves, ce que l'enonce ne donne pas, et
aucun ne recopie le lieu ni les mots de la bonne reponse.** Verifie a l'ecran sur les cinq, apres
erreur volontaire. **C'est la reserve la mieux payee des cinq**, et c'est celle qui compte le plus au
moment ou l'enfant a echoue.

### `EXC-R4` est levee

| Item | Avant | Apres | Mots |
|---|---|---|---|
| 10 | *"**Dans la cour, avant la classe**, une moquerie se repete. A qui Tom doit-il en parler ?"* | *"Une moquerie se repete dans la cour, le matin. A qui Tom doit-il en parler ?"* | **15** |
| 11 | *"**En classe, a la fin de la journee**, une moquerie se repete. A qui Lea doit-elle en parler ?"* | *"En classe, une moquerie se repete le vendredi. A qui Lea doit-elle en parler ?"* | **14** |

**La propriete que j'avais posee** - *"ces enonces disent leur situation en une seule proposition
principale"*, sans deux circonstances posees avant la question - **est tenue sur les deux.** Les onze
enonces sont a 14 et 15 mots, sauf un : voir `EXC-R6`.

### `EXC-R3` - levee sur l'item 1, maintenue sur les items 5 et 6

**Ce que ma reserve disait, mot pour mot** : *"la troisieme proposition de la tache A est un candidat
serieux sur chaque item (`PLA-1`). Elle ne l'est pas sur trois d'entre eux : **une bagarre la ou
personne ne se bat**, **une dispute sur un pied ecrase par accident**, **une vengeance sur un geste
que l'enonce dit sans faire expres**."*

| Item | Avant | Apres | Verdict |
|---|---|---|---|
| 1 - *empeche Aya de s'asseoir chaque midi* | *une bagarre* | *une bousculade* | **payee.** Empecher quelqu'un de s'asseoir passe par le corps ; un enfant peut envisager cette categorie |
| 5 - *marche sur le pied de Hugo une seule fois, par accident* | *une dispute* | *un jeu de recreation* | **non payee** |
| 6 - *fait tomber le dessin de Lea une fois, sans faire expres* | *une vengeance* | *une blague* | **non payee** |

**Le motif, et c'est le mien.** Sur l'item 6, j'ai ecrit que *une vengeance* n'etait pas un candidat
**parce que l'enonce dit *"sans faire expres"***. *Une blague* est tout aussi intentionnelle qu'une
vengeance : **les memes mots de l'enonce l'excluent, donc la propriete que j'ai ecrite n'est pas
atteinte** - seul le mot a change. Idem sur l'item 5 : *"par accident"* exclut *un jeu de
recreation*, qui est un acte voulu, comme il excluait *une dispute*. **Les deux items restent
binaires de fait.**

**Ce n'est pas un poteau deplace, et je donne le test** : je cite ma reserve, pas une exigence
nouvelle, et j'applique a la proposition d'aujourd'hui le motif exact que j'avais applique a celle
d'hier. **Sur l'item 1, la meme lecture donne payee**, et je l'ecris pour montrer que le critere n'a
pas bouge d'un item a l'autre.

**Et je dois dire ce qui rend cette reserve dure a payer, parce que je ne veux pas d'un troisieme
aller-retour sur les memes trois items.** Sur les items 4, 5 et 6, l'enonce contient lui-meme le mot
qui donne la reponse - *"une seule fois"*, *"par accident"*, *"sans faire expres"*. **Toute categorie
intentionnelle est exclue par ces mots, et toute categorie non intentionnelle est un synonyme de la
bonne reponse** (*une maladresse* rendrait l'item indecidable, ce qui serait pire). **La sortie n'est
donc probablement pas dans la troisieme proposition : elle est dans l'enonce**, qui peut decrire un
geste unique sans le qualifier d'accidentel et laisser l'enfant en tirer la conclusion. **Je dis la
propriete et l'endroit ou je crois qu'elle se paie ; la formulation appartient au developpeur, et
s'il trouve une troisieme categorie qui tient sans toucher l'enonce, elle me va.**

> **Propriete a tenir** (inchangee depuis le 2026-09-07) : sur chaque item de la tache A, la
> troisieme proposition est une categorie qu'un enfant de CP peut vraiment envisager pour la scene
> decrite, et qui reste fausse.

**J'ai verifie une seconde occurrence avant d'ecrire ceci, et elle tient.** *Une bagarre* survit sur
l'item 4 (*"En courant, Tom bouscule Malo une seule fois et s'excuse aussitot"*) : ma reserve ecrivait
*"une bagarre la ou personne ne se bat"*, et cette chaine apparaissait **deux fois** dans le paquet.
`lea-dev` l'a payee sur l'item 1. **Sur l'item 4 je la trouve envisageable** - il y a un contact
physique en course, et l'enonce donne deux discriminants explicites -, donc je ne la reclame pas.
**L'ambiguite etait dans ma reserve, pas dans le correctif**, et je la tranche ici plutot que de la
laisser se redemander.

### `EXC-R1` n'est pas payee - et le defaut est intact a l'ecran

La consigne servie par `catalogue.yaml` est toujours *"Lis bien, puis dis ce qu'il faut faire, ou a
qui en parler."*, alors que la tache A ne demande plus ce qu'il faut faire mais de **nommer la
situation**, et **aucune de ses trois propositions n'est une action**. **Vue sur les dix manches de
ma partie**, comme le 2026-09-07.

**Je constate le non-paiement, et je constate aussi que personne ne l'a cache** : `lea-dev` l'a ecrit
dans le paquet avec sa cause (quatre tests de `TexteAccentueExposeParLePortEntrantTest` rougissent au
changement) et `theo-archi` a mesure ce chiffre et tranche que **ce n'est pas un obstacle technique**
- *"ce qui manque a `EXC-R1` est le texte, pas le deblocage"*. **Il a raison, et le texte est de mon
ressort avant d'etre du sien** : une consigne est lue par l'enfant et par l'adulte qui la lui lit.

> **Propriete a tenir** (inchangee) : la consigne annonce les deux taches reellement servies -
> nommer la situation decrite, et dire a qui en parler. **Je ne prescris pas la phrase** ; je note
> qu'elle doit entrer dans l'inventaire de chaines accentuees dans le meme commit, ce que le message
> d'echec du garde-fou dit lui-meme.

### `EXC-R5` n'est pas payee - l'obstacle est leve, le defaut est entier

**Rejoue, manche 9** : *"A la cantine, une moquerie se repete chaque jour. A qui Malo doit-il en
parler ?"*. J'ai presse *"a sa maitresse"* et l'ecran a repondu **"Ce n'est pas ca. Essaie encore !"**.
**Un enfant de CP qui parle a sa maitresse d'une moquerie repetee a la cantine fait exactement ce
qu'il faut, et le jeu le lui refuse.** C'est le seul endroit des six paquets d'EMC CP ou le produit
dit non a un comportement juste, et c'est la ligne qui porte l'exclusion - la plus delicate de la
matiere.

**Ce que ce sprint a change, et c'est reel** : `theo-archi` a paye l'obstacle qui rendait la reserve
impayable. `PaquetCpEmcExclusionTest` lisait l'identite d'une tache dans `item.enonce()` contenant
*"A qui"*, **la phrase exacte que ma reserve demande de reecrire** ; la tache se lit desormais dans la
bonne reponse, et il a **mesure** que la reecriture que je proposais rend le test vert. **Ce n'est
pas un paiement de `EXC-R5`, et il l'ecrit lui-meme** : *"rien de pedagogique n'est tranche"*.

**Ce que je maintiens, et ce que je precise pour que le prochain geste n'ait rien a deviner** :

> **Propriete a tenir** (inchangee depuis le 2026-09-07) : **aucun item ne fait d'un adulte reel une
> reponse fausse** (`EMC-5`). Le critere note est la **presence** de l'adulte au moment decrit, pas
> le lieu de la scene.
>
> **Precision du 2026-09-09** : la maitresse est presente en classe **toute la journee** et le
> surveillant de la cour **pendant la recreation** ; ce sont ces presences que la question doit
> designer. **Trois des cinq items marquent aujourd'hui *"a sa maitresse"* faux** (items 7, 9, 10),
> et ce sont les trois seuls qui doivent changer.

**Je porte au credit de `theo-archi` d'avoir mesure sa proposition sur mon texte plutot que de me
renvoyer la question**, et je note que l'obstacle qu'il a trouve est un test qui epinglait une phrase
que je demandais de reecrire : **un garde-fou qui fige la formulation d'un enonce empeche la porte
pedagogique de mordre.** C'est un fait a garder au-dela de ce paquet ; il a ouvert la dette generale
lui-meme (25 fichiers de test sur 95 lisent l'enonce par une operation de chaine).

### `EXC-R6` - l'item 2 fait 16 mots, et ma reserve n'en comptait que deux

**Ouverte le 2026-09-09, non bloquante.**

*"Presque tous les jours, Hugo se moque de la facon dont parle Tom. Qu'est-ce que c'est ?"* -
**16 mots**, un au-dessus de `LON-1`.

**A mon debit** : `EXC-R4` nommait *"les deux enonces les plus longs de la tache B"*, et **le plus
long enonce du paquet est dans la tache A**. Le constat m'a ete rendu par le sprint avant que je
juge, `lea-dev` l'a confirme par son propre script reproduisant ma convention, et la dette declaree
d'`ADR-0089` porte ce reste a **1** enonce sur ce paquet. **J'ai recompte les onze moi-meme** : dix
sont a 14 ou 15 mots, un est a 16.

**Le mot en trop n'est pas le seul point** : *"la facon dont parle Tom"* emploie *dont*, le seul
relatif de ce genre des onze enonces. **Une moquerie sur la facon de parler est en outre le seul item
du paquet qui touche a une difference de la personne**, ce qui est le domaine de `EMC-CP-04` et non de
`EMC-CP-03` ; je ne le compte pas comme un defaut de couverture - l'item reste une moquerie repetee,
qui est bien la ligne - mais **si cet enonce est reecrit, c'est l'occasion de choisir un autre objet
de moquerie**, et cela ne coute rien de plus.

> **Propriete a tenir** : cet enonce dit la meme situation sous `LON-1` et sans *dont*. La
> formulation appartient au developpeur.

**Regime** : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`), avec `EXC-R1`, `EXC-R3` et
`EXC-R5`.

### Ce que j'ai verifie et qui ne fait pas reserve

- **`FER-1`, `FER-2`, `FER-3`** : tenues a l'identique, recomptees - six items de qualification,
  cinq d'adulte, trois adultes reels en position de bonne reponse.
- **`LON-2`** : les onze enonces comptent deux phrases, la seconde etant la question. Zero faute.
- **`PLA-1` sur la tache B** : les trois propositions sont trois adultes reels de l'ecole sur les
  cinq items. Tenue, et mordue par `laTacheDAdulteNeSertPlusDeCandidatAuRabais`.
- **`CAT-1`** : sans objet. Nommer une situation n'oppose aucun registre poli a un registre fautif.
- **`REG-R3`, la serie du genre de l'adulte d'ecole** : ce paquet porte **cinq** occurrences de
  *maitresse* et aucun adulte masculin nomme - *"un surveillant de la cour"* et *"l'adulte qui
  surveille la cantine"* ne portent pas de genre a l'ecran. **Je ne lui impute rien** : son contenu
  n'a pas bouge sur ce point dans ce sprint, et la reserve est ouverte sur le paquet qui a aggrave la
  serie.

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-03` reste `couvert-valide`** | `camille-po` | **Aucun mouvement de matrice.** La mention de couverture partielle du 2026-09-08 reste due et inchangee |
| **`EXC-R1`, `EXC-R3` (items 5 et 6), `EXC-R5`, `EXC-R6`** | `camille-po`, via `sofia-sm` | En faire une story. **Quatre points sur le meme paquet, un seul rejeu borne, 0,5** (`PAR-10`). **`EXC-R5` est celle que je corrigerais la premiere** : c'est la seule des quatre ou le produit dit non a un enfant qui a raison. `EXC-R1` vient ensuite, elle s'affiche sur les dix manches |
| **`EXC-R3` : la sortie est probablement dans l'enonce des items 5 et 6, pas dans leur troisieme proposition** | `camille-po`, via `sofia-sm` | **Dit pour eviter un troisieme aller-retour sur les memes items.** Ce n'est pas une prescription de texte : c'est le motif pour lequel deux substitutions de proposition ont deja echoue |
| **Qu'un test de paquet fige la formulation d'un enonce que la porte pedagogique demande de reecrire** | `theo-archi` | **Fait acquis, et il l'a deja ouvert avec son chiffre** (25 fichiers sur 95). **Je le confirme du cote pedagogique** : c'est la premiere fois qu'un garde-fou de ce depot a rendu une de mes reserves impayable, et c'est un fait a garder au-dela de ce paquet |
| **La dette de longueur d'`ADR-0089` nomme `EXC-R4` pour ce paquet** | `theo-archi` | **Constat, pas demande** : `EXC-R4` est levee et l'enonce que la dette compte est celui de `EXC-R6`. Le chiffre reste juste - **1** -, son etiquette a change de reserve |

---

## Bloc de verdict du 2026-09-10 - `nadia-prof`, instance B de s33, apres avoir joue - `EXC-R5` LEVEE, l'increment REFUSE

**Arbre audite** : `round-two` a `b45e5927`, worktree dedie. **Partie complete de dix manches, avec
erreurs volontaires.** Releve manche par manche dans
`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-B.md`, section 6.
**Audit degrade sur un point, declare** : lecture du HTML servi, pas de la page rendue par un
navigateur.

**Stories auditees** : `S33-EXC-R1` et `S33-EXC-R5`, toutes deux payees dans le meme commit
(`fbb370a7`). **La ligne `EMC-CP-03` reste `couvert-valide` et je ne la rouvre pas.**

### `EXC-R5` est LEVEE, et je ne la reformule pas apres coup

**Ce que j'avais ecrit le 2026-09-07**, dans cette fiche : *"Le critere reel est la **presence** de
l'adulte : que la question le dise (*quel adulte est la a ce moment ?*) plutot que *a qui doit-il en
parler ?*"*

**La reserve a ete executee dans ces termes exacts.** J'ai presse *a sa maitresse* sur la manche 6 et
le jeu l'a refusee - **et cette fois il a raison** : l'enonce dit que le surveillant est la, pas la
maitresse. **Aucun item ne fait plus d'un adulte reel une reponse fausse a une question a laquelle il
repondait bien.**

> **`EXC-R5` est LEVEE.** La barre que j'avais plantee est atteinte, et **je ne la deplace pas**. Le
> refus du 2026-09-07 et le constat de non-paiement du 2026-09-09 restent lisibles ou ils ont ete
> ecrits, avec leurs dates.

### L'increment est REFUSE - trois reserves neuves, et leur motif est a mon debit

**La sortie que ma propre formule prescrivait detruit trois proprietes que la reserve ne visait
pas.** *"Que la question dise quel adulte est la"* rend la question inutile : si l'enonce nomme
l'adulte present, il n'y a plus rien a savoir. **Je l'ecris ici plutot que de le faire porter au
lot** - `lea-dev` a applique ma phrase au mot pres, et `theo-archi` l'avait mesuree avant elle
(`confirmation-archunit.md` de s32, section 4 : *"la formule que `nadia-prof` propose"*).

#### `EXC-R7` - la reponse est dans l'enonce

| Item | Ce que l'enonce dit | Bonne reponse |
|---|---|---|
| 7 | *devant **le surveillant*** | *a un **surveillant** de la cour* |
| 8 | *devant **la maitresse*** | *a sa **maitresse*** |
| 10 | *pres du **surveillant*** | *a un **surveillant** de la cour* |
| 11 | *devant sa **maitresse*** | *a sa **maitresse*** |

Le cinquieme (item 9, *l'adulte du repas* -> *l'adulte qui surveille la cantine*) demande un pas de
plus : savoir que le repas se prend a la cantine.

**Un enfant de CP qui ne sait rien de la ligne gagne ces quatre items en appariant deux mots
identiques.** C'est `CON-1` a son degre maximal, et c'est **la meme famille que le repere lexical qui
a refuse le paiement de `CAT-1` a s32** - en pire : la ou *maitresse* eliminait, la recopie
**designe**.

> **`EXC-R7` - nouvelle, datee du 2026-09-10, non bloquante.** Aucune bonne reponse ne reprend un mot
> que son propre enonce vient d'ecrire.

#### `EXC-R8` - la question et ses propositions ne forment plus une phrase

**Recolle, l'item donne** : *"Quel adulte est la ? -> a sa maitresse."*

**Ce n'est pas du francais.** La question demande un sujet, les propositions repondent au datif : les
trois ont garde la forme *"a ..."* de la question supprimee, et personne ne les a reprises quand la
question a change. **Cinq items sur onze**, lus a l'ecran sur cinq manches.

C'est le defaut de `PER-R1` et de `POL-R4`, **troisieme occurrence en trois sprints sur trois paquets
de la meme matiere**, et c'est la propriete dont j'ai ecrit a s32 que j'en restais **la seule
garde**. C'est aussi la classe de defaut que la retro de s32 a nommee - *la compression qui casse la
langue* : ici ce n'est pas une subordonnee retiree pour tenir une longueur, c'est **une question
remplacee sans reprendre les reponses**, et la cause est la meme.

> **`EXC-R8` - nouvelle, datee du 2026-09-10, non bloquante.** La question de la tache B et chacune
> de ses trois propositions forment une phrase correcte une fois recollees.
>
> **Ce n'est pas du registre du `relecteur-pedagogique`** (`DEC-0002`, amendement du 2026-08-04 ;
> `DEC-0056`) : ce n'est ni une faute d'accord, ni d'accentuation, ni de ponctuation. C'est une
> question qui ne demande pas ce que ses boutons repondent.

#### `EXC-R9` - la seconde moitie du libelle n'est plus servie, et la consigne annonce ce qui a disparu

**Le libelle de `EMC-CP-03`** : *"Reconnaitre une situation de moquerie repetee ou d'exclusion **et
savoir a quel adulte en parler**."*

**Aucun item ne demande plus a qui en parler.** Les cinq items de la tache B demandent **qui est
present**, ce que l'enonce a deja dit. Sous `FER-1`, chaque element enumere par le libelle doit etre
servi par au moins un item ou il est en position de bonne reponse : **la seconde moitie ne l'est plus
par aucun.**

**Et le paquet se contredit avec lui-meme dans le meme commit** : `S33-EXC-R1` a corrige la consigne
en *"Lis bien, puis dis si c'est une moquerie qui se repete, **ou a quel adulte en parler**."* -
elle nomme donc la tache que `S33-EXC-R5` venait de retirer du paquet. **Les deux stories du meme
commit se defont l'une l'autre.** Je l'ai lu dix fois en dix manches.

> **`EXC-R9` - nouvelle, datee du 2026-09-10, non bloquante mais la plus lourde des trois.** Au moins
> un item sert *savoir a quel adulte en parler* en position de bonne reponse, et l'adulte a qui
> parler ne se lit pas dans l'enonce.
>
> **Ce que cette reserve n'est pas** : je **ne rouvre pas** `EMC-CP-03` et je ne demande aucun
> mouvement de matrice. **Je dis ce que j'ai mesure** : la ligne a ete fermee sur une tache qui
> n'existe plus dans le paquet, et tant que ce texte est servi, ce n'est plus le contenu qui l'a
> fermee qui est a l'ecran.

### `EXC-R1` reste NON PAYEE, et le defaut a change de cote

**Le texte de consigne ecrit par `S33-EXC-R1` est juste.** Il nomme correctement la tache A (*"dis si
c'est une moquerie qui se repete"*), ce que l'ancienne consigne ne faisait plus depuis la reecriture
de s31. **Il nomme aussi une tache que le meme commit a fait disparaitre du paquet.**

> **`EXC-R1` : NON PAYEE.** Sa condition - *la consigne nomme la tache reellement servie* - n'est pas
> remplie apres le commit. **Ce qui doit changer n'est pas la consigne, ce sont les items** : le
> texte de consigne est le bon des lors que `EXC-R9` est payee. **Les deux se paient ensemble ou pas
> du tout.**

### La propriete a reprendre, et elle n'est pas de moi seule a trancher

Le probleme que `EXC-R5` posait - *un enfant qui parle a sa maitresse d'une moquerie de la cour fait
ce qu'il faut* - **n'a pas de solution tant que les trois propositions sont trois adultes de l'ecole
dont deux seraient de mauvais destinataires : aucun ne l'est.** Ma formule de s32 contournait le
probleme en changeant la question ; elle a coute la tache.

> **Decision de forme due avant le premier item reecrit**, de `camille-po` et de moi ensemble, sur le
> meme regime que la decision de grain de `CONS-4`. **Je ne fournis pas les textes et je n'ecris pas
> d'items.**

### Ce que j'ai verifie et qui ne fait pas reserve

- **La tache A est intacte et bonne.** Six items, trois moqueries repetees et trois accidents
  isoles, le critere de repetition porte par l'enonce et repris par l'indice sans nommer la bonne
  reponse. **Joue sur six manches**, aucune reserve.
- **`EXC-R2` reste levee** : les cinq indices de la tache B disent le role de l'adulte, information
  que l'enonce ne donne pas.
- **`EXC-R3` reste levee sur l'item 1 et maintenue sur les items 5 et 6**, inchangee.
- **`EXC-R6` inchangee et toujours ouverte** (item 2, le plus long de la tache A). Non financee a
  s33.
- **`FER-2`** : onze items. **`FER-3`** : deux taches, la moins servie a cinq items.
- **`PaquetCpEmcExclusionTest` est vert** sur cet arbre. Il lit la tache d'un item **dans sa bonne
  reponse** (`ADR-0089`, decision E), donc il ne voit ni l'enonce ni la question posee : **il ne
  pouvait pas voir ce que j'ai vu en jouant, et il n'a jamais pretendu le voir.** Les quatre jobs de
  la CI sont verts sur le meme arbre.

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-03` reste `couvert-valide`** | `camille-po` | **Aucun mouvement de matrice**, dans les deux sens |
| **`EXC-R7`, `EXC-R8`, `EXC-R9` et `EXC-R1` toujours impayee** | `camille-po`, via `sofia-sm` | **Une seule story**, sur la tache B. **Les quatre ont une cause unique et se defont ensemble ou pas du tout.** `EXC-R6` voyage avec elles (`PAR-10`) |
| **La forme de la tache B** | `camille-po` et moi, ensemble | Decision ecrite **avant le premier item reecrit** |
| **`EXC-R7` comme propriete mordable** | `theo-archi` | Comparaison de mots entre l'enonce d'un item et sa bonne reponse, meme genre que la clause 2 d'`ADR-0086`. **Fait mesure** : 4 items sur 11. **Je dis la propriete, jamais la forme du test, et je ne prescris aucun seuil** |

---

## Bloc d'arbitrage du 2026-09-10 - `nadia-prof`, s34 - deux corrections a ma propre decision de forme, avant que `S34-EXC-TACHEB` ne s'ouvre

**Ni verdict, ni Done, ni mouvement de matrice** : `EMC-CP-03` reste `couvert-valide`. Ma decision de
forme du 2026-09-10 (arbitrage prealable de s34, section 4.4) **tient et n'est pas effacee** ; je la
corrige sur deux points en la relisant contre le paquet servi et contre son fichier de test.

- **`C-1`** - ma phrase *"ne pas se taire, ne pas rendre le coup, et ne pas s'en remettre a un
  camarade"* nomme la **competence**. Lue comme une liste de distracteurs, elle casse `CAT-1` :
  *rendre le coup* s'ecarte au ton sur cinq items sur cinq, le motif meme pour lequel j'ai fait
  retirer le bouton *"à personne, ..."*. **Les deux autres propositions doivent etre des conduites
  qu'aucun enfant ne classe comme vilaines.** Portee de mon interdit de s31, dite pour qu'on ne me la
  fasse dire ni trop large ni trop etroite : il visait un **bouton ecartable au ton**, pas toute
  conduite qui ne nomme pas d'adulte.
- **`C-2`, bloquante** - mon objet, execute a la lettre, donne **cinq items dont la bonne reponse est
  la meme conduite**. `FAB-1a` fait alors compter la tache pour **un** item : le paquet tombe a
  **sept comptables sur dix**, soit **le defaut exact qui a refuse ce paquet au premier passage**.
  **La bonne reponse doit varier d'un item a l'autre**, sans que la discrimination cesse d'etre une
  conduite contre une conduite. **Je n'ai pas verifie** que la forme qui satisfait `FAB-1a` satisfait
  en meme temps `EXC-R7` : si les deux sont conjointement insatisfaisables, cela me revient avant
  qu'un item ne soit ecrit.
- **Quatre methodes de `PaquetCpEmcExclusionTest` sont plus dures que mes clauses** et interdiraient
  la refonte - `laTacheDAdulteNeSertPlusDeCandidatAuRabais`,
  `laTacheDAdulteRepresenteLesTroisAdultesEnBonneReponse`,
  `chaqueItemAppartientAExactementUneDesDeuxTaches`,
  `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems`. **Symptome nomme, cause a
  `theo-archi`**, avis du avant l'ouverture de la branche : c'est la meme famille de defaut que sur
  `EMC-CP-07`, et elle y a coute un sprint entier.

**Le detail des symptomes, l'objet complet et le fait signale sur la lettre de `EMC-CP-03`** sont
dans `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/arbitrage-emccp07-nadia.md`,
section 4. **Perimetre et prix inchanges : tarif de lot, 1,0.**

**Correction du meme jour, apres la mesure de `theo-archi` (`909d7029`), et je ne retire pas la ligne
qu'elle corrige.** J'ai ecrit *quatre* methodes plus dures que mes clauses. **Sa sonde en mesure
trois qui bloquent** ; la quatrieme, `laTacheDAdulteNeSertPlusDeCandidatAuRabais`, **devient verte
sur une population vide** - sa boucle parcourt une tache qui ne compte plus aucun item. **J'avais
raison sur l'ecart et faux sur son effet, et l'effet reel est le plus mauvais des deux** : un test qui
bloque se voit, un test qui ne garde plus rien laisse passer sans qu'aucun rouge ne le dise. **Cette
methode-la encodait ma condition de s31** : apres la refonte, **le non-retour du bouton *"a
personne"* n'est plus garde par rien, et j'en redeviens la seule garde.** Je l'ecris pour que
personne ne le croie couvert.

**Trois questions pedagogiques tranchees dans le meme geste** (detail en section 4.6 du document
ci-dessus) : `C-2` et `EXC-R7` **sont satisfaisables ensemble**, a la condition que l'enonce installe
le moment sans employer le mot que la bonne reponse emploie, et que la bonne reponse ne soit pas la
seule proposition a nommer un adulte (`FAB-1c`, deja opposable, recomptee - **pas une barre neuve**) ;
**je ne pose aucun seuil de variation** et je dis d'avance que je ne refuserai pas trois valeurs
distinctes sur cinq items ; **une conduite du genre *attendre que cela s'arrete* est acceptable**, et
c'est meme le meilleur distracteur de cette famille, a la condition que l'indice dise pourquoi
attendre ne marche pas.

---

## Bloc de validation pedagogique - `S34-EXC-TACHEB`, 2026-09-11 - nadia-prof UNIQUEMENT

> Les blocs precedents restent lisibles tels quels. **Un refus ne s'efface pas, meme leve.**

- **Verdict sur l'increment `S34-EXC-TACHEB`** : **REFUSE.**
- **Ce que ce refus ne fait pas** : il **ne rouvre pas `EMC-CP-03`**, qui reste `couvert-valide`. La
  story etait une correction non bloquante sur une ligne deja fermee, et un refus ne defait pas une
  fermeture anterieure. **Je ne touche pas `MATRICE-couverture.md`.**
- **Date** : 2026-09-11
- **Auteur** : `nadia-prof`, instance B de s34
- **Arbre** : `round-two` a `a7852398`. Contenu du jar compare au contenu de l'arbre avant de jouer,
  identique sur ce paquet.
- **Audit joue** : **oui.** Sept manches jouees avec **une erreur volontaire par manche**, puis une
  manche poussee jusqu'a l'epuisement des essais. **Les cinq items de la tache B et quatre des six
  items de la tache A vus a l'ecran**, avec leurs indices ; les deux items non tires relus dans le
  paquet servi.

### Le motif du refus - `EXC-R10`

> **`EXC-R10` - les cinq indices de la tache B sont ceux de la tache abandonnee. Ils repondent a une
> question que les items ne posent plus, et aucun ne dit a l'enfant ce qu'il a rate.**

**Le fait, mesure sur le diff et vu a l'ecran.** `git diff` sur le commit `365de10a`, item par item :
les cinq `enonce`, les cinq `bonneReponse` et les dix `distracteurs` de la tache B sont reecrits.
**Les cinq lignes `indice` ne sont pas touchees, pas une.** Elles sont celles de la tache
*"Quel adulte est la ?"* que cette refonte supprime :

| Item | Ce que l'enfant choisit desormais | Indice servi apres son echec |
|---|---|---|
| 7 | *en parler a un surveillant* / **attendre que cela s'arrete tout seul** / *en parler a un camarade* | *"Cette personne est la pour veiller sur les eleves qui jouent."* |
| 8 | *en parler a sa maitresse* / **esperer qu'un adulte l'ait remarque** / *en parler a un camarade* | *"C'est elle qui reste avec les eleves toute la journee."* |
| 9 | *en parler a l'adulte de la cantine* / **garder cela pour lui sans rien dire** / *en parler a un camarade* | *"Cette personne veille sur les eleves qui mangent."* |
| 10 | *en parler a un surveillant* / **faire comme si de rien n'etait** / *en parler a un camarade* | *"Son role est de veiller sur les eleves pendant les recreations."* |
| 11 | *en parler a sa maitresse* / **attendre que cela s'arrete tout seul** / *esperer qu'un adulte l'ait remarque* | *"C'est elle qui est avec ses eleves tous les jours."* |

**Joue, manche 1 : j'ai presse *attendre que cela s'arrete tout seul* et le jeu m'a repondu *"Cette
personne est la pour veiller sur les eleves qui jouent."*** Quelle personne ? Je n'en ai nomme
aucune. **L'indice designe un adulte a un enfant qui vient de choisir de n'en parler a personne.**

**La condition n'est pas neuve et elle n'est pas cachee : je l'ai ecrite dans cette fiche meme, en
avance, comme la condition d'acceptation du distracteur qui faute ici.** Derniere phrase du bloc du
2026-09-10, deux paragraphes au-dessus de celui-ci : *"une conduite du genre attendre que cela
s'arrete est acceptable, et c'est meme le meilleur distracteur de cette famille, **a la condition que
l'indice dise pourquoi attendre ne marche pas**."* La condition vaut sur **zero item sur cinq**.

**Et le perimetre que j'avais ecrit la couvrait.** Arbitrage prealable de s34, section 5.5, repris en
section 4.2 de l'arbitrage de sortie : *"les cinq items de la tache B **en entier**, plus l'item 2 de
la tache A"*. **Un item, c'est son enonce, ses propositions et son indice.** Les indices sont restes
dehors.

**Ce que l'enfant perd, et c'est pour cela que je refuse plutot que de reserver.** Avant cette
refonte, les indices de la tache B repondaient exactement a la question posee - *"Quel adulte est
la ?"* - et aidaient. **Apres, ils ne repondent plus a rien.** Un enfant qui se trompe sort de la
manche sans savoir ce qu'il a rate ; c'est le traitement de l'erreur qui regresse, sur le paquet meme
dont le premier refus disait *"des la deuxieme manche, l'enfant presse le bouton sans lire"*.

**Objet, pour que `camille-po` en fasse une story sans me redemander ce que je voulais dire.**

> *L'enfant qui a choisi de ne rien dire, d'attendre ou d'en parler a un camarade apprend, en lisant
> l'indice, pourquoi ce qu'il a choisi ne fait pas cesser une moquerie qui se repete.* L'indice porte
> sur **la conduite ecartee**, jamais sur le role de l'adulte : ce role etait la reponse a l'ancienne
> question et il ne sert plus.

**Mode de defaillance nomme d'avance.** **Ecrire un indice qui renomme la bonne reponse** - *"il faut
en parler a un adulte"* - au lieu de refuter la conduite ecartee. L'enfant saurait alors quoi presser
sans savoir pourquoi, et l'indice retomberait dans le defaut qu'il repare. **Second mode** : ecrire
**un** indice par conduite plutot qu'un par item, et le recopier - les cinq items n'ecartent pas la
meme conduite, et un indice unique redeviendrait un texte sans rapport avec le bouton presse.

**Perimetre, mesure sur l'etat d'arrivee.** Les **cinq lignes `indice` de la tache B**, et elles
seules. **Enonces, bonnes reponses et distracteurs de la tache B : deja bons, ils ne bougent pas.**
Les six items de la tache A ne bougent pas. `LON-1` et `LON-2` ne portent pas sur l'indice ; `COH-1`
recomptee sur le couple indice/propositions de chaque item de la tache B.

### Ce que la refonte a reussi, et je le dis en entier parce que le refus ne porte que sur un point

**Cinq de mes six conditions de forme sont tenues, verifiees a l'ecran item par item.**

- **`C-1` tenue.** Aucun distracteur n'est une conduite vilaine ; *rendre le coup* n'apparait nulle
  part. **Aucun item ne se decide au ton** (`CAT-1`) : les trois propositions sont des conduites
  qu'un enfant de six ans a reellement, et l'item se decide sur *"est-ce que cela fait cesser la
  moquerie ?"*.
- **Mon interdit de s31 sur le bouton *"a personne, ..."* est tenu, et je tranche la lecture plutot
  que de la laisser ouverte.** Le bouton que j'avais fait retirer etait un **constat desinvolte** -
  *"ce n'est pas grave"*, *"ca arrive tous les jours"*, *"ce n'est pas son probleme"* - ecartable au
  ton sans lire. *"attendre que cela s'arrete tout seul"*, *"garder cela pour lui sans rien dire"*,
  *"faire comme si de rien n'etait"* sont des **conduites**, pas des jugements sur la situation, et
  aucune ne porte le ton desinvolte qui faisait le defaut. **Il n'est pas revenu.**
- **`C-2` tenue.** Trois bonnes reponses distinctes sur cinq items - c'est la forme que j'avais dit
  d'avance ne pas refuser sur ce motif, et je ne la refuse pas.
- **`EXC-R7` tenue, verifiee item par item.** L'enonce installe le lieu ou le moment par un mot que la
  bonne reponse n'emploie pas : *recreation* / *surveillant de la cour*, *en classe* / *sa
  maitresse*, *au moment du repas* / *l'adulte qui surveille la cantine*. **L'enfant infere, il
  n'apparie pas deux mots.**
- **`EXC-R8` tenue** : la question recollee a chacune de ses trois propositions forme une phrase
  francaise, sur les quinze propositions de la tache B.
- **Ce que la ligne couvre apres la refonte, et c'est mon travail, pas celui de `marc-dev`.** Le
  paquet sert desormais *en parler a un adulte plutot que se taire* - ce que je tiens pour la
  competence effective de `EMC-CP-03` au CP sous une mecanique a trois boutons (arbitrage de sortie,
  section 4.3). **La lettre du referentiel - *"savoir a quel adulte en parler"* - n'est plus servie**,
  et c'est une consequence voulue de ma propre decision de forme, pas une perte non vue. `camille-po`
  a le fait ; je ne modifie aucun referentiel.

### Les deux reserves, non bloquantes, ouvertes ce jour

> **`EXC-R11` - sur trois items sur cinq, la bonne reponse est la seule proposition qui nomme un
> adulte.**
>
> **Constat.** Items 7, 9 et 10 : *en parler a un camarade* nomme une personne mais pas un adulte, et
> l'autre distracteur n'en nomme aucun. Items 8 et 11 : *esperer qu'un adulte l'ait remarque* en
> nomme un. **Deux items sur cinq portent le contrepoids, trois ne l'ont pas.**
>
> **Ce que je ne fais pas, et je le dis pour qu'on ne me le fasse pas dire.** Ma condition du
> 2026-09-10 etait *"une part des items porte un distracteur qui nomme lui aussi un adulte"* - **je
> n'y avais pose aucun nombre**, et deux sur cinq est une part. **La condition telle que je l'ai
> ecrite est tenue, et je ne la durcis pas apres coup.** Ce qui suit est une reserve **neuve et
> datee**, pas une relecture de l'ancienne.
>
> **Objet.** *Un enfant ne gagne pas trois items sur cinq en cherchant le bouton qui nomme une grande
> personne.* Le raccourci mene ici a la bonne conduite, ce qui le rend benin ; il reste que sur ces
> trois items l'enfant n'a pas eu a lire la situation.
>
> **Mode de defaillance nomme d'avance.** **Mettre un adulte dans un distracteur de chacun des cinq
> items.** Si le distracteur devient *en parler a un autre adulte de l'ecole*, l'item redevient un
> choix entre deux adultes - la tache exacte que cette refonte vient de supprimer, et `C-2` avec
> elle. **Le contrepoids doit rester une conduite qui ne fait pas cesser la moquerie tout en nommant
> un adulte**, comme *esperer qu'un adulte l'ait remarque* le fait deja.
>
> **Perimetre.** Un distracteur sur chacun des items 7, 9 et 10. Rien d'autre. `FAB-1c`, `C-1`,
> `CAT-1` et `EXC-R8` recomptees sur les cinq items apres la reecriture.

> **`EXC-R12` - *"esperer qu'un adulte l'ait remarque"* est au-dessus du niveau de lecture du CP.**
>
> **Constat.** Six mots, un subjonctif passe (*ait remarque*) et un pronom elide (*l'*) dont
> l'antecedent n'est pas dans la proposition - il faut remonter a l'enonce et comprendre que *l'*
> designe la moquerie. **Un enfant de six ans ne construit pas cette phrase en la lisant.** Servi sur
> les items 8 et 11, dont l'item 11 ou il est l'un des deux seuls distracteurs.
>
> **Objet.** *L'enfant lit seul les trois boutons avant de choisir.* Un distracteur illisible n'est
> pas un distracteur : il est ecarte par sa forme, et l'item redevient binaire.
>
> **Mode de defaillance nomme d'avance.** **Le remplacer par une conduite qui ne nomme pas d'adulte**
> - ce serait defaire `EXC-R11` dans le meme geste, ces deux items etant precisement les deux qui
> portent le contrepoids. **La reecriture garde l'adulte et change la phrase.**
>
> **Perimetre.** Ce distracteur, sur les items 8 et 11. **Un point de grammaire y dort et il n'est pas
> de mon registre** : l'accord du participe avec le complement anteposé. Je le route au
> `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`) et je ne le tranche pas.

### Ce dont je reste la seule garde sur ce paquet - une ligne change, et dans le bon sens

| Propriete | Garde executable ? |
|---|---|
| **Le non-retour du bouton *"a personne"*** | **de nouveau garde, et sur une population non vide.** `PaquetCpEmcExclusionTest.laPropositionAuRabaisAPersonneNApparaitDansAucuneTache` parcourt **les onze items du paquet**, trois propositions chacune, et non plus une tache filtree qui pouvait se vider ; **`fer2AuMoinsDixItems` prouve dans la meme classe, par le meme `items()`, que cette population compte au moins dix items.** Mesure : les deux methodes sont vertes, 7 tests sur 7. **Je ne suis plus seule garde de la forme litterale du bouton** |
| **La famille de ce bouton** - une proposition ecartable au ton qui revient a n'en parler a personne, ecrite dans d'autres mots | **aucun.** La sonde compare un prefixe litteral (*"a personne"*) ; *"ne rien dire a personne"* ou *"personne ne peut l'aider"* passeraient. **Je reste seule garde de la famille, et je le dis pour que personne ne croie couvert plus que ce qui l'est** |
| **`Q2`** - qu'un enfant ayant joue deux items ne reponde pas au troisieme sans lire les propositions | **aucun**, et le minimum encodable (*la tache B ne porte pas une bonne reponse unique*) ne le couvre pas. **Verifie en jouant : tenu, et c'est `EXC-R11` qui en mesure la marge** |
| **`CAT-1`, `PLA-1`, `C-1`** | **aucun** |
| **`EXC-R10`** - qu'un indice refute la conduite ecartee | **aucun**, et c'est le defaut du jour : **quatre jobs verts sur cinq indices sans rapport avec leur item** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EXC-R10`** - les cinq indices de la tache B | `camille-po` (story), puis un developpeur | **Bloquant pour l'increment.** Objet, mode de defaillance et perimetre ci-dessus. **Je n'ecris aucun des cinq textes** (`RES-2`) |
| **`EXC-R11`, `EXC-R12`** | `camille-po` | Non bloquants, **a traiter dans le meme geste que `EXC-R10`** : les trois touchent la tache B et un paquet se relit comme un tout |
| **L'accord du participe dans *"esperer qu'un adulte l'ait remarque"*** | `relecteur-pedagogique` | Orthographe et grammaire, pas mon registre |
| **La lettre de `EMC-CP-03` face a une mecanique a trois boutons** | `camille-po` | **Deja route le 2026-09-10, rappele ici** : ce que la ligne couvre reellement apres la refonte est ecrit ci-dessus. **Aucun mouvement de matrice** |
| **Le mouvement de matrice** | `camille-po` | **Aucun.** `EMC-CP-03` reste `couvert-valide` |
