# Fiche de conformite - Les regles du jeu collectif (`bandit-manchot-regles-jeu-collectif-eps-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-eps-regles-jeu-collectif` (11 items)
- **Statut global** : **REFUSE**
- **Derniere mise a jour** : 2026-09-09

> **Fiche ouverte par `nadia-prof`, instance C, et non par `camille-po`.** Le paquet est neuf et la
> matiere n'avait aucune fiche. J'ai rempli les sections descriptives depuis la story `S32-5` du
> backlog et depuis le referentiel, sans rien y ajouter de mon cru ; **le bloc de validation est le
> mien, comme toujours**. Si `camille-po` veut reprendre la main sur les sections qui lui reviennent,
> elle le peut : elles ne portent aucun jugement.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Education physique et sportive | CP (cycle 2) | Conduire et maitriser un affrontement individuel ou collectif | Connaitre et respecter les regles d'un jeu collectif simple | `EPS-CP-08`, `squad/programme/education-physique-et-sportive/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S32-5`.

- [x] Onze items, `nombreDeManches: 10` (`DEC-0064`), plancher de dix (`FER-2`)
- [x] Deux taches distinctes, la moins servie a trois items au moins (`FER-3`)
- [ ] **Chaque regle de jeu est rappelee dans l'enonce ou universellement scolaire (`EPS-1`)** -
      **non tenue sur l'item 5**
- [x] Aucun item ne compare des corps, des performances ni des capacites (`EPS-2`)
- [x] Aucune attitude, aucune emotion, aucune conduite morale portee par une bonne reponse (`EPS-3`)
- [x] Aucun item n'interroge l'enfant sur sa pratique ni sur sa situation (`BIA-4`)
- [x] Aucun item ne demande a l'enfant de faire expliquer ou montrer quoi que ce soit par un adulte
- [ ] **Chaque proposition est un candidat qu'un enfant de CP peut serieusement envisager
      (`PLA-1`)** - **non tenue sur l'item 8**
- [x] Au plus 15 mots et deux phrases par enonce, la seconde etant la question (`LON-1`, `LON-2`)
- [x] Au plus la moitie des items portent deux propositions (`BIN-1`) - deux sur onze
- [x] La ligne est declaree sous `lignesDeReferentiel` (`ADR-0081`)
- [x] La consigne de catalogue nomme la tache reelle et ne reprend pas la formule generique
      (`DEC-0078`)
- [x] Bonne reponse non repetee dans une tache, distracteurs hors du vivier des bonnes reponses
      (`FAB-1a`, `FAB-1b`)
- [ ] **La bonne reponse ne se reconnait pas a la forme de son texte sur plus d'un tiers des items
      d'une tache (`FAB-1c`)** - **non tenue sur la tache B**
- [ ] **Exactitude du contenu** - **trois defauts, dont une erreur de regle servie deux fois**

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-09
- **Auteur** : `nadia-prof`, instance C, branche `s32-nadia-c`
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree, application lancee sur le port
  8099. **Une partie complete de dix manches, avec une erreur volontaire par manche puis la bonne
  reponse**, donc l'indice atteint dix fois sur dix. Les onze items ont ete vus a l'ecran, la partie
  en servant dix, et l'item non tire etant l'item 3 (l'epervier, tache A).
- **Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai
  lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des
  ecrans, l'enchainement apres une erreur, les indices, l'ecran de fin et le tirage. **Cela ne couvre
  ni la couleur, ni le contraste, ni la taille, ni la position a l'ecran**, et aucune conclusion de
  cette fiche n'en depend.
- **Audit degrade sur un second point, et je le declare aussi** : je n'ai pas pousse cette partie-ci
  jusqu'a l'ecran de revelation a trois erreurs, l'ayant fait dix fois sur dix sur le paquet frere de
  la meme mecanique et du meme lot. **Aucune conclusion de cette fiche ne porte sur la revelation.**
- **Premier terrain sans image (`PAR-13`)** : c'est le premier contact reel de `EPS-1` avec un item,
  et elle tombe.

### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est bien celle travaillee, **sur sa seule moitie
  connaissance**, ce que j'ai declare jouable d'avance. Le libelle demande *"connaitre et respecter
  les regles d'un jeu collectif simple"* ; les six premiers items font reconnaitre la regle d'un jeu
  nomme, les cinq suivants font juger si une situation decrite respecte ou viole une regle. **Les six
  jeux choisis sont a la bonne echelle** - 1, 2, 3 soleil, le chat perche, l'epervier, la balle au
  prisonnier, le loup et les moutons, les demenageurs sont des jeux de cour de cycle 2, pas des
  sports a reglement. C'est un bon choix et je le dis.
  **`FER-1` tenue, vacuement** : le libelle ne porte aucune parenthese enumerative.
  **`FER-2` tenue sur le compte** : onze items enumeres, plancher de dix. **Mais quatre d'entre eux
  sont en cause ci-dessous** : le compte tient, la substance non.
  **`FER-3` tenue sur le compte, et fragile** : deux operations mentales distinctes - reconnaitre la
  regle d'un jeu nomme (6 items) et juger une situation (5 items) - la moins servie en portant cinq,
  au-dessus du plancher de **trois** que le depot ecrit dans son code (`MINIMUM_PAR_TACHE = 3`) et
  que je confirme. **Si les items 7 et 8 tombent, la tache B en porte trois : exactement le plancher,
  sans marge.** Je le dis d'avance pour que la reprise ne se contente pas de retirer deux items.
- **Lisibilite au niveau vise** : les onze enonces font **8 a 14 mots**, `LON-1` est tenue ; les cinq
  items de la tache B comptent **deux phrases dont la seconde est la question**, `LON-2` est tenue.
  **Deux obstacles de lecture subsistent que le comptage ne voit pas** - la reprise pronominale de
  l'item 4 (`JEU-R5`) et le nom du jeu de l'item 5 (`JEU-R8`). **C'est exactement pourquoi ma barre
  est necessaire et jamais suffisante.**
- **Charge cognitive** : plus haute que sur le paquet frere, et c'est normal - les items de la tache
  B demandent de tenir une situation **et** une regle en meme temps. Cela reste dans ce qu'un enfant
  de CP accompagne peut faire, **sauf sur l'item 4**, ou il doit en plus resoudre un pronom sans
  antecedent.
- **Progression** : la tache B est plus exigeante que la tache A et l'ordre du paquet les place dans
  cet ordre. Le tirage etant aleatoire, la progression n'est de toute facon pas garantie a l'ecran.
- **Traitement de l'erreur** : verifie a l'ecran dix fois. L'indice s'affiche des la premiere erreur
  et le reessai reste ouvert. **Aucun enfant ne reste bloque.** Mais **deux indices sont en cause** :
  celui de l'item 5, faux au premier degre (`JEU-R3`), et celui de l'item 7, qui redouble la confusion
  de son propre item (`JEU-R1`). **L'indice est le texte que lit l'enfant qui vient d'echouer ; c'est
  le pire endroit du produit pour se tromper.**
- **Exactitude du contenu** : verifiee un par un. **Cinq des six items de la tache A sont exacts** -
  1, 2, 3 soleil, le chat perche, l'epervier, la balle au prisonnier, les demenageurs sont dits
  juste. **Trois defauts d'exactitude subsistent** : l'item 7 (une erreur de regle, servie dans la
  bonne reponse et dans l'indice), l'item 5 (une variante presentee comme la regle) et l'indice de
  l'item 5 (faux tel qu'il est lu). **Ce sont les motifs du refus.**
- **`EPS-1` - NON TENUE sur l'item 5.** Voir `JEU-R2`. Premier contact de cette condition avec un
  item, et elle mord.
- **`EPS-2` - tenue** : aucun item ne compare des corps, des performances ni des capacites entre
  enfants ; aucune performance chiffree, donc aucune norme.
- **`EPS-3` - tenue.** J'ai regarde de pres, parce que trois items declarent qu'un enfant nomme n'a
  pas respecte la regle. **La condition ne mord pas** : elle interdit qu'une **attitude, une emotion
  ou une conduite morale** soit portee par une bonne reponse, et juger le respect **factuel** d'une
  regle de jeu n'est pas de cette famille - c'est meme exactement la seconde tache que la story
  demande. **Ce qui cloche sur l'item 7 est que le jugement soit faux, pas qu'il existe.** Et la
  contrefacon que j'avais refusee d'avance a la ligne 9 du referentiel (*"que fais-tu si un joueur
  triche ?"*) n'est pas reproduite.
- **`BIA-4` - tenue** : les situations mettent en scene des personnages nommes, exterieurs a
  l'enfant. Aucun item n'interroge l'enfant sur sa pratique ni sur sa situation.
- **`PLA-1` - NON TENUE sur l'item 8** : *non* n'est pas une reponse qu'un enfant peut serieusement
  envisager apres avoir lu un enonce qui dit que Malo a traverse sans se faire toucher. **C'est un
  bouton de remplissage, ce que `PLA-1` nomme en toutes lettres**, et l'item redevient binaire de
  fait. Sur les dix autres items, la condition est tenue.
- **`BIN-1` - tenue, et je le dis clairement** : **deux items binaires sur onze**, les items 7 et 8.
  Le seuil est *au plus la moitie des items d'un paquet* ; deux sur onze est loin dessous. **La
  condition que j'ai posee ne mord pas ici** - ce sont ces deux items eux-memes qui posent probleme,
  pour d'autres motifs, et je ne fais pas dire a `BIN-1` ce qu'elle ne dit pas.
- **`FAB-1a` - tenue** : six bonnes reponses distinctes sur la tache A, cinq sur la tache B. Aucune
  tache deflatee.
- **`FAB-1b` - tenue a la lettre, contournee en esprit** : aucun distracteur n'est la bonne reponse
  d'un autre item de la meme tache. **Mais le distracteur *en portant deux objets a la fois* de
  l'item 6 est la premisse de l'item 10** - ce n'est pas le vivier des bonnes reponses que `FAB-1b`
  nomme, donc la lettre est tenue ; l'effet d'appariement est le meme, et c'est `JEU-R7`.
- **`FAB-1c` - non tenue sur la tache B**, branche registre, sur les items 10 et 11. Voir `JEU-R6`.
- **`CAT-1` - non tenue sur les items 10 et 11**, par le meme fait.
- **Biais et ton** : aucun stereotype de genre, d'origine, de milieu social ni de configuration
  familiale. **Les quatre personnages se repartissent les roles sans genre** : Lea (fille) et Hugo
  (garcon) enfreignent, Malo (garcon) respecte, Aya (fille) est attrapee sans faute de sa part.
  **J'ai verifie cette repartition explicitement**, parce qu'une matiere ou l'on juge des conduites
  est exactement celle ou une repartition genree passerait inapercue. Le ton n'est ni condescendant
  ni infantilisant. **Un point cependant** : l'item 7 declare qu'une enfant nommee n'a pas respecte
  la regle **alors qu'elle l'a respectee** ; le probleme est l'exactitude, mais le fait que le
  reproche injuste porte un prenom n'ameliore rien.

### Motif

**Quatre items sur onze enseignent ou verifient quelque chose de faux, et deux indices sur onze
brouillent ce qu'ils expliquent. Le paquet ne se ferme pas.**

**Six motifs, dont le premier suffirait a lui seul.**

1. **`JEU-R1` - item 7 : toucher un joueur perche n'est pas une infraction. C'est faux.** *"Au chat
   perche, Lea touche Hugo sur un banc. A-t-elle respecte la regle ?"*, bonne reponse **non**,
   indice *"Un joueur perche en hauteur ne peut pas etre touche."* **La regle du chat perche est que
   le joueur perche ne peut pas etre *pris*** : le toucher n'a **aucun effet**, Hugo n'est pas
   attrape, et Lea n'a enfreint aucune regle. **L'item transforme un geste sans effet en faute**, et
   l'enfant en ressort en croyant que toucher un camarade perche est de la triche - ce qui est faux
   du jeu et lui fera reprocher a tort quelque chose dans la cour. **L'indice redouble la confusion
   au lieu de la lever** : un joueur perche *peut* etre touche, il ne peut pas etre *pris*, et c'est
   precisement la distinction que l'item aurait du enseigner. **Une erreur factuelle dans un contenu
   scolaire est un motif de refus a elle seule, sans discussion** ; celle-ci est servie deux fois.
2. **`JEU-R2` - item 5 : la regle du loup et des moutons est une variante, et `EPS-1` l'interdit.**
   *"Au loup et les moutons, que devient un mouton touche ?"*, bonne reponse *il devient loup a son
   tour*, distracteur *il sort du jeu*. **Les deux formes scolaires coexistent, et le paquet met la
   seconde en distracteur - ce qui est la preuve la plus directe qu'elle existe** : on ne fabrique
   pas un distracteur plausible avec une regle qui n'a cours nulle part. L'enonce ne rappelle pas
   laquelle des deux il juge. **L'enfant qui joue la seconde variante dans sa cour est declare faux
   sur son propre jeu** - le motif meme de `EPS-1` dans sa forme la plus dure, puisqu'ici le produit
   ne se contente pas de supposer une pratique, il la **contredit**.
3. **`JEU-R3` - item 5 : l'indice *"Le loup grandit au fil de la partie"* est faux au premier
   degre.** Ce que la phrase veut dire est *"les loups deviennent de plus en plus nombreux"* ; ce
   qu'elle dit est que le loup devient plus grand. **Un enfant de six ans lit *grandir* au premier
   degre**, et c'est le sens qu'on lui enseigne. La phrase est fausse telle qu'elle est lue, et elle
   est servie a l'enfant qui vient d'echouer. **Meme famille que `MEL-R1` et `OBT-R1` a s31** : un
   indice qui brouille ce qu'il est cense expliquer.
4. **`JEU-R4` - item 8 : aucune regle n'est en jeu, et l'enonce porte deja son verdict.** *"A
   l'epervier, Malo traverse sans se faire toucher. A-t-il respecte la regle ?"*, indice *"Traverser
   sans etre touche est justement le but du jeu."* **Ne pas se faire toucher n'est pas respecter une
   regle : c'est reussir.** L'enonce dit que tout s'est bien passe et demande si tout s'est bien
   passe ; **l'indice l'admet lui-meme**. L'item ne verifie aucune connaissance, il est binaire, et
   `PLA-1` n'est pas tenue.
5. **`JEU-R5` - item 4 : *"quand on est touche"* puis *"il va dans le camp adverse"*.** L'enonce dit
   *on*, les trois propositions disent *il*, **et le pronom n'a aucun antecedent dans la phrase que
   l'enfant vient de lire**. Un adulte comble le trou sans le voir ; un enfant de CP, qui lit encore
   mot a mot, doit d'abord decider qui est *il*. **C'est de la charge cognitive ajoutee sur la
   reprise pronominale, qui est justement une difficulte de lecture du CP.** Ce n'est ni de
   l'orthographe, ni de l'accentuation, ni de la ponctuation : c'est le niveau scolaire de ce que
   l'enfant lit, donc mon domaine.
6. **`JEU-R6` - la tache B se gagne a la forme de l'enonce sur les items 10 et 11.** Sur les deux,
   **l'enonce signale lui-meme la transgression** (*porte deux objets a la fois*, *avance quand le
   meneur regarde*) et **la bonne reponse est la seule proposition de forme *sanction*** en face de
   deux recompenses. Deux items sur cinq, sur une tache de cinq dont le seuil de `FAB-1c` est franchi
   a deux : la condition n'est pas tenue. **Meme mecanisme que sur le paquet frere, en plus discret.**

**Pourquoi c'est un refus.** Mon critere du 2026-09-06 : un defaut est bloquant si *quelque chose de
faux est enseigne* **ou** si *un item se gagne sans la competence*. **Les deux branches mordent
ici**, la premiere sur trois points et la seconde sur trois. **La ligne `EPS-CP-08` ne ferme pas.
Elle reste `non couvert`.**

**Ce qui est actionnable, et ce n'est pas une reecriture du paquet** : l'item 7 refait sur l'effet et
non sur la faute (*"Au chat perche, Malo touche Aya montee sur un banc. Que se passe-t-il ?"* -
*rien, Aya est perchee* / *Aya est attrapee* / *Aya devient chat*, ce qui le desbinarise au passage) ;
l'item 5 rappelle sa variante dans son enonce, ou change de jeu ; son indice devient *"Chaque mouton
touche devient un loup de plus."* ; l'item 8 met une vraie regle en jeu (*"Malo traverse en sortant
du couloir"*) avec trois propositions ; l'item 4 dit *"que devient un joueur touche ?"* sans changer
un mot de ses propositions ; les distracteurs des items 10 et 11 deviennent des sanctions plausibles
(*Hugo passe son tour*, *Hugo laisse un objet a l'arrivee*). **Cinq items retouches sur onze, la
tache A conservee a cinq items sur six, aucune mecanique touchee.**

### Motifs de refus - la liste nommee

| Code | Item | Ce qui bloque |
|---|---|---|
| **`JEU-R1`** | 7 | Toucher un joueur perche n'est pas une infraction. Erreur de regle, servie dans la bonne reponse **et** dans l'indice |
| **`JEU-R2`** | 5 | La regle jugee est une variante, non rappelee dans l'enonce ; l'autre variante est en distracteur (`EPS-1`) |
| **`JEU-R3`** | 5 | Indice *"Le loup grandit au fil de la partie"* : faux au premier degre, et servi apres l'echec |
| **`JEU-R4`** | 8 | Aucune regle en jeu, l'enonce porte son propre verdict, item binaire, `PLA-1` non tenue |
| **`JEU-R5`** | 4 | *"quand on est touche"* puis *"il va..."* : pronom sans antecedent, au CP |
| **`JEU-R6`** | 10, 11 | La bonne reponse est la seule de forme *sanction*, et l'enonce signale la transgression (`FAB-1c` branche registre, `CAT-1`) |

**Tous les six sont bloquants. Rejeu par moi apres reprise.**

### Reserves non bloquantes

#### `JEU-R7` - la regle *un objet a la fois* n'est jamais posee, elle n'apparait qu'en distracteur

**Constat.** L'item 10 juge Hugo sur la regle *un seul objet a la fois* aux demenageurs. Cette regle
n'est **rappelee dans aucun enonce** : elle apparait en **distracteur** de l'item 6 et dans
l'**indice** de l'item 10, nulle part ailleurs.

**Je ne le retiens pas comme motif.** La regle *un objet par passage* est bien la forme courante des
demenageurs a l'ecole, donc `EPS-1` est plausiblement satisfaite par sa branche *universellement
scolaire*. **Je ne fabrique pas un manquement sur une variante dont je ne suis pas sure**, et je
prefere l'ecrire que de laisser croire que j'ai tranche. **Ce point distingue cet item de l'item 5**,
ou la variante concurrente est dans le paquet lui-meme.

**Ce qui reste** : un enfant rencontre cette regle pour la premiere fois **en position de mauvaise
reponse**. C'est un ordre d'apprentissage a l'envers.

**Ce que j'attends** : que l'enonce de l'item 10 pose la regle avant de la faire juger, **ou** qu'un
item de la tache A la porte en **bonne** reponse. Si la premiere voie est retenue, le compte de mots
et de phrases est a recontroler contre `LON-1` et `LON-2`.

#### `JEU-R8` - *"Au loup et les moutons"* ne se lit pas au CP

**Constat.** Cinq enonces sur onze ouvrent par le nom du jeu. Quatre sont lisibles (*Au chat perche*,
*A l'epervier*, *A la balle au prisonnier*, *Aux demenageurs*). Le cinquieme ne l'est pas : la
contraction *au* ne peut pas gouverner les deux membres du nom, et un enfant qui lit mot a mot bute
sur la phrase avant d'arriver a la question.

**La forme grammaticale de cette tournure appartient au `relecteur-pedagogique`** (`DEC-0002`,
amendement du 2026-08-04 ; `DEC-0056`), et **je la lui route sans la corriger**. Ce qui m'appartient
et que je retiens, c'est l'obstacle de lecture au niveau vise.

**Ce que j'attends** : *"Au jeu du loup et des moutons"*, ou la forme que le `relecteur-pedagogique`
retiendra - avec le compte de mots recontrole, l'enonce etant a dix mots aujourd'hui. **Si l'item 5
disparait au titre de `JEU-R2`, cette reserve disparait avec lui.**

### Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **Les items 7, 10 et 11 declarent qu'un enfant nomme n'a pas respecte la regle.** `EPS-3` ne mord
  pas : juger le respect factuel d'une regle de jeu n'est ni une attitude, ni une emotion, ni une
  conduite morale, et c'est la seconde tache que la story demande. **Traite ci-dessus, et je ne le
  reouvre pas.**
- **La repartition des jeux entre les taches** : le chat perche sert trois items, 1, 2, 3 soleil
  deux, les demenageurs deux, et l'epervier, la balle au prisonnier et le loup un chacun. Ce n'est pas
  equilibre et cela ne me gene pas - `FER-1` n'enumere rien a couvrir sur cette ligne.
- **Les items 7 et 8 sont binaires.** `BIN-1` est tenue (deux sur onze), et je ne fais pas dire a ma
  propre condition ce qu'elle ne dit pas. **Ces deux items tombent pour d'autres motifs**, chacun
  nomme.
- **La consigne du catalogue** (*"Lis bien, puis dis quelle est la regle du jeu, ou ce qui se passe
  dans la situation."*) nomme les deux taches et ne reprend pas la formule generique. **`DEC-0078`
  est tenue. Je ne la conteste pas.**

### Mentions de couverture partielle

**Elle est due, elle est exigee d'avance, et elle est reprise mot pour mot depuis
`squad/programme/education-physique-et-sportive/competences-cp.md`. Elle vaut le jour ou cette ligne
bougera, et pas avant.**

> *"connaissance de la regle seule ; la tenue du role en situation reste non couverte"*
> (referentiel, ligne 8)

> *"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste
> et aucune performance ne sont servis par ce produit"* (referentiel, section *Mention exigee
> d'avance si une ligne bouge*)

**Je ne les reformule pas et je n'en retire pas un mot.** La seconde est le refus d'avance n5 que
j'ai pose le 2026-09-03 : sans elle, **je refuse la fermeture de cette ligne meme quand son contenu
sera bon**. Aujourd'hui la question ne se pose pas ; je l'ecris quand meme, pour qu'elle soit lisible
avant la prochaine tentative et non redemandee apres.

**Et je le dis sans detour : aucune ligne de ce paquet n'atteste une conduite.** Un enfant qui repond
juste sait ce que dit la regle d'un jeu ; **il n'a pas montre qu'il la respecte**, et rien dans ce
produit ne le montrera jamais.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce verdict ne dit rien de ce que l'enfant voit sans faire defiler la page, y compris
> le retour qui suit sa reponse. Propriete sortie de la porte pedagogique par arbitrage de
> l'utilisatrice du 2026-08-28 (`DEC-0053`) ; dette ouverte, croissante et assumee. **Aucune mesure
> ne l'a instruite sur ce paquet.**

### Ce dont je reste la seule garde sur ce paquet

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque regle de jeu** | **aucun**, et c'est ce qui a produit `JEU-R1`. **Un automate ne sait pas ce que dit le chat perche**, et il ne le saura pas |
| **`EPS-1`** - la regle est rappelee ou universelle | **aucun**, et il en faudrait un catalogue de variantes scolaires que personne n'a. **C'est la condition la plus chere a tenir de cette matiere** |
| **`EPS-6`** - un indice n'est pas une image | **aucun**, et la condition est neuve de ce jour |
| **`FAB-1c` branche registre, `CAT-1`, `EPS-4`** | **aucun** |
| **`PLA-1`** | **aucun**, et c'est elle qui tombe sur l'item 8 |
| **`EPS-2`, `EPS-3`** | **aucun** |
| **La reprise pronominale entre un enonce et ses propositions** (`JEU-R5`) | **aucun**. Mordable en principe - un pronom de proposition sans antecedent dans l'enonce est une propriete de texte - et je ne cree pas cette barre en cours de sprint |
| **`LON-1`, `LON-2`** | **`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, depuis `ADR-0089`.** Le seul garde-fou de cette fiche que je n'aie pas eu a jouer moi-meme - **et il n'a rien vu de `JEU-R5`, qui est un defaut de lecture a douze mots.** Ma barre est necessaire, jamais suffisante |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`** | **aucun sur ce paquet** : aucun test de paquet n'existe pour l'EPS. **Je les ai comptes a la main** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EPS-CP-08` reste `non couvert`** | `camille-po` | La ligne ne passe pas a `couvert-valide`. Le compte de la matrice lui appartient ; je ne la touche pas |
| **`JEU-R1` a `JEU-R6`** (motifs bloquants) | `camille-po`, via `sofia-sm` | La reprise item par item decrite dans le motif. **Puis rejeu par moi** |
| **`JEU-R7`** (reserve) | `camille-po`, via `sofia-sm` | A traiter dans la meme reprise, le paquet etant de toute facon rouvert |
| **`JEU-R8`** - *"Au loup et les moutons"* | `relecteur-pedagogique` | La forme grammaticale est la sienne, pas la mienne. Je route l'obstacle de lecture, je ne corrige pas la tournure |
| **`EPS-4`, `EPS-6`** | toute l'equipe, pour le second lot d'EPS | Deux conditions opposables, allouees au registre dans ce meme diff |
| **Un test de paquet pour ce paquet** | `theo-archi`, via `sofia-sm` | `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b` sont mordables - `PaquetCm1EmcDroitDevoirTest` le fait deja pour l'EMC |

### Historique des verdicts sur ce paquet

**Un refus garde sa trace. Rien n'est efface ici, on ajoute.**

| Date | Verdict | Instance | Motif en une ligne |
|---|---|---|---|
| 2026-09-09 | **REFUSE** | C | `JEU-R1` a `JEU-R6` - quatre items sur onze enseignent ou verifient quelque chose de faux, dont une erreur de regle du chat perche servie deux fois |
