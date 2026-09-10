# Fiche de conformite - Les regles de securite (`bandit-manchot-regles-securite-eps-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-eps-regles-de-securite` (11 items)
- **Statut global** : **REFUSE** (second refus, 2026-09-10)
- **Derniere mise a jour** : 2026-09-10

> **Fiche ouverte par `nadia-prof`, instance C, et non par `camille-po`.** Le paquet est neuf, la
> matiere n'avait aucune fiche, et celle-ci est la premiere de l'education physique et sportive dans
> ce depot. J'ai rempli les sections descriptives depuis la story `S32-5` du backlog et depuis le
> referentiel, sans rien y ajouter de mon cru ; **le bloc de validation est le mien, comme
> toujours**. Si `camille-po` veut reprendre la main sur les sections qui lui reviennent, elle le
> peut : elles ne portent aucun jugement.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Education physique et sportive | CP (cycle 2) | Adapter ses deplacements a des environnements varies | Connaitre et respecter les regles de securite propres a l'activite et au lieu de pratique | `EPS-CP-04`, `squad/programme/education-physique-et-sportive/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S32-5`.

- [x] Onze items, `nombreDeManches: 10` (`DEC-0064`), plancher de dix (`FER-2`)
- [x] Deux taches distinctes, la moins servie a trois items au moins (`FER-3`)
- [x] Aucun item ne compare des corps, des performances ni des capacites (`EPS-2`)
- [x] Aucune attitude, aucune emotion, aucune conduite morale portee par une bonne reponse (`EPS-3`)
- [x] Aucun item n'interroge l'enfant sur sa pratique, son corps ni sa situation (`BIA-4`)
- [x] Aucun item ne demande a l'enfant de faire expliquer ou montrer quoi que ce soit par un adulte
- [x] Chaque proposition est un candidat qu'un enfant de CP peut serieusement envisager (`PLA-1`)
- [x] Au plus 15 mots et deux phrases par enonce (`LON-1`, `LON-2`)
- [x] Au plus la moitie des items portent deux propositions (`BIN-1`)
- [x] La ligne est declaree sous `lignesDeReferentiel` (`ADR-0081`)
- [x] La consigne de catalogue nomme la tache reelle et ne reprend pas la formule generique
      (`DEC-0078`)
- [x] Bonne reponse non repetee dans une tache, distracteurs hors du vivier des bonnes reponses
      (`FAB-1a`, `FAB-1b`)
- [ ] **La bonne reponse ne se reconnait pas a la forme de son texte sur plus d'un tiers des items
      d'une tache (`FAB-1c`)** - **non tenue sur la tache B**
- [ ] **La bonne reponse ne se reconnait pas a son registre (`CAT-1`)** - **non tenue sur la
      tache B**

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-09
- **Auteur** : `nadia-prof`, instance C, branche `s32-nadia-c`
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree, application lancee sur le port
  8099. **Trois parties completes de dix manches** : une toute juste, une avec **trois erreurs
  volontaires par manche** (ecran de revelation atteint dix fois sur dix), une a l'aveugle en
  pressant toujours la premiere proposition affichee. **Les onze items ont ete vus a l'ecran**, dix
  par partie, l'item non tire differant d'une partie a l'autre.
- **Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai
  lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des
  ecrans, l'enchainement apres une erreur et apres trois, les indices, l'ecran de fin et le tirage.
  **Cela ne couvre ni la couleur, ni le contraste, ni la taille, ni la position a l'ecran**, et
  aucune conclusion de cette fiche n'en depend.
- **Premier terrain sans image (`PAR-13`)** : aucune de mes conditions d'EPS - `EPS-1`, `EPS-2`,
  `EPS-3` - n'avait rencontre un item avant ce jour.

### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est bien celle travaillee, **sur sa seule moitie
  connaissance**, ce que j'ai declare jouable d'avance. Le libelle demande *"connaitre et respecter
  les regles de securite propres a l'activite et au lieu de pratique"* ; les six premiers items font
  reconnaitre la regle qui s'applique a une activite ou a un lieu decrit, les cinq suivants font dire
  pourquoi la regle existe. **Ce n'est pas une competence voisine plus facile** : dire pourquoi une
  regle existe est plus exigeant que la reciter, et c'est bien cette ligne-la et pas une autre.
  **Deux items sur onze derivent cependant vers la securite routiere du pieton** : c'est `SEC-R1`,
  et je ne les retire pas du compte, pour le motif de gouvernance qui y est ecrit.
  **`FER-1` tenue, vacuement** : le libelle ne porte aucune parenthese enumerative, il n'y a rien a
  couvrir element par element. Je le confirme au lieu de laisser croire que la condition a mordu.
  **`FER-2` tenue** : onze items enumeres, plancher de dix, aucune tache deflatee par `FAB-1a`, la
  ligne servie par ce seul paquet.
  **`FER-3` tenue** : deux operations mentales distinctes, et non une phrase a mot variable -
  reconnaitre la regle (6 items) et dire pourquoi elle existe (5 items). La moins servie en porte
  cinq, au-dessus du plancher de **trois**, plancher que le depot ecrit dans son code
  (`MINIMUM_PAR_TACHE = 3`) et que je confirme.
- **Lisibilite au niveau vise** : **la meilleure que j'aie mesuree sur un paquet de CP.** Les onze
  enonces font **6 a 10 mots** et **une seule phrase** chacun ; la barre `LON-1` que j'ai posee la
  veille est tenue avec cinq mots de marge, et `LON-2` sans discussion. Aucun mot hors du vocabulaire
  de CP dans les enonces, les propositions et les indices. **Un enfant de six ans lit ces consignes
  seul.**
- **Charge cognitive** : basse et constante. L'enfant tient une situation d'une ligne et choisit
  parmi trois propositions courtes. Aucun item ne demande de tenir deux choses en tete a la fois.
- **Progression** : la difficulte ne monte pas a l'interieur d'une tache, et le tirage etant
  aleatoire elle ne serait pas garantie a l'ecran de toute facon. La tache B (le *pourquoi*) est plus
  exigeante que la tache A (le *quoi*), et l'ordre du paquet les place dans cet ordre. **Acceptable,
  et ce n'est pas propre a ce paquet.**
- **Traitement de l'erreur** : **verifie a l'ecran trente fois.** L'indice s'affiche des la premiere
  erreur, la bonne reponse est nommee a la troisieme, l'enfant avance toujours. **Aucun enfant ne
  reste bloque.** Les indices expliquent le danger plutot que de repeter la reponse (*"Deux enfants
  en meme temps sur le toboggan peuvent se cogner."*, *"Une voiture peut arriver de la gauche comme
  de la droite."*). **C'est du bon travail et je le dis, meme en refusant.**
- **Exactitude du contenu** : verifiee un par un sur les onze enonces, les onze bonnes reponses, les
  vingt-deux distracteurs et les onze indices. **Je n'ai trouve aucune erreur de fait.** Le casque a
  velo, l'autorisation de l'adulte avant d'entrer dans un bassin, la profondeur avant de plonger, un
  enfant a la fois sur le toboggan et sur le tapis : ce sont les regles reelles, dites juste. **Rien
  de faux n'est enseigne par ce paquet, et c'est pourquoi la premiere branche de mon critere de
  blocage ne mord pas.**
- **`EPS-1` - sans objet** : aucun item ne porte sur la regle d'un jeu.
- **`EPS-2` - tenue** : aucun item ne compare des corps, des performances ni des capacites entre
  enfants ; aucune performance chiffree n'apparait nulle part, et donc aucune norme a atteindre.
- **`EPS-3` - tenue** : chaque item porte sur une regle de securite factuelle ou sur son motif.
  Aucune attitude, aucune emotion, aucune conduite morale n'est portee par une bonne reponse. **La
  contrefacon que j'avais refusee d'avance a la ligne 9 du referentiel n'est pas reproduite.**
- **`BIA-4` - tenue** : les onze enonces sont impersonnels (*on*). Aucun n'interroge l'enfant sur sa
  pratique, son corps ni sa situation.
- **`PLA-1` - tenue** : les vingt-deux distracteurs sont des reponses qu'un enfant de CP peut
  serieusement envisager - des chaussures de sport, des lunettes de soleil, la temperature de l'eau,
  la file vide. **Aucun bouton de remplissage, et aucun item ne redevient binaire de fait.**
- **`BIN-1` - tenue** : **zero item binaire sur onze.**
- **`FAB-1a` - tenue** : six bonnes reponses distinctes sur la tache A, cinq sur la tache B. Aucune
  repetition, donc aucune tache deflatee.
- **`FAB-1b` - tenue** : aucun des vingt-deux distracteurs n'est la bonne reponse d'un autre item de
  la meme tache. Verifie distracteur par distracteur.
- **`FAB-1c` - NON TENUE sur la tache B**, branche **registre**. C'est le motif du refus, mesure
  ci-dessous.
- **`CAT-1` - tenue sur la tache A, non tenue sur la tache B.** Sur la tache A, seul l'item 6
  presente ses deux distracteurs comme imprudents ; un item sur six ne fait pas une tache qui se
  gagne au ton. Sur la tache B, quatre items sur cinq se gagnent au registre.
- **Biais et ton** : aucun stereotype de genre, d'origine, de milieu social ni de configuration
  familiale - les enonces ne nomment aucune personne et ne decrivent aucun foyer. Le ton n'est ni
  condescendant ni infantilisant. **Une seule formulation presuppose un contexte**, et c'est le
  distracteur *mettre de la creme solaire* de l'item 1, traite en `SEC-R2` : il suppose un bassin
  decouvert, ce que la plupart des piscines scolaires ne sont pas. **Le mot *maitresse* apparait dans
  un distracteur de l'item 4** ; je l'ai examine et je ne le retiens pas - c'est le terme d'usage au
  CP, il est en position de distracteur, et rien n'y attache un genre a une fonction dans le reste du
  paquet.

### Motif

**Le paquet est exact, lisible, bien construit sur sa premiere tache, et il ne se ferme pas.**

**Le motif est unique et il est mesure : `FAB-1c`, branche registre, sur la tache B - `SEC-R3`.**

Sur quatre des cinq items de la tache B (*"pourquoi cette regle existe"*), **la bonne reponse est la
seule proposition qui nomme un danger, une blessure, un secours ou un choc** :

| Item | Bonne reponse | Distracteurs | Propositions du champ du danger |
|---|---|---|---|
| 7 - casque | *pour proteger la tete en cas de chute* | *pour aller plus vite en pedalant* ; *pour etre vu de loin par les voitures* | **2 sur 3** - l'item ne tombe pas |
| 8 - regarder des deux cotes | *pour verifier qu'aucune voiture n'arrive* | *pour voir si un ami arrive* ; *pour observer les vitrines des magasins* | **1 sur 3** |
| 9 - adulte a la piscine | *pour porter secours en cas de danger* | *pour choisir la musique diffusee* ; *pour compter les longueurs nagees* | **1 sur 3** |
| 10 - un par un sur le toboggan | *pour eviter que deux enfants se heurtent* | *pour faire moins de bruit en glissant* ; *pour user moins vite le toboggan* | **1 sur 3** |
| 11 - profondeur avant de plonger | *pour eviter de se blesser en touchant le fond* | *pour savoir combien de temps on peut nager* ; *pour choisir la couleur de son maillot* | **1 sur 3** |

Les distracteurs parlent de musique, de vitrines, de bruit, d'usure, de couleur de maillot et de
duree de baignade. **Un enfant de six ans qui n'a jamais entendu parler d'aucune de ces regles gagne
quatre manches sur cinq par une seule strategie : prendre la reponse qui parle de se faire mal.**

**Le seuil de `FAB-1c` est *plus d'un tiers des items de la tache*** : un tiers de cinq est 1,67, le
seuil est franchi a deux items, **il y en a quatre**. L'ecart n'est pas marginal.

**C'est `CAT-1` transposee du registre moral au registre du danger** - le meme defaut que celui que
j'ai trouve dans l'EMC le 2026-09-07 en jouant. **Je l'avais annonce sur la mauvaise branche** : ma
vigilance ecrite dans la story `S32-5` portait sur les **amorces** (*"il faut boire de l'eau"*), et
l'equipe a bien evite les amorces signantes. Le defaut est arrive par le registre.

**Pourquoi c'est un refus et non un `VALIDE SOUS RESERVE`.** Mon critere du 2026-09-06, reconduit au
2026-09-07 et que je n'amende pas : un defaut est bloquant si *quelque chose de faux est enseigne*
**ou** si *un item se gagne sans la competence*. Rien de faux n'est enseigne ici, je viens de
l'ecrire. **La seconde branche mord, et sur une tache entiere** - or cette tache B est precisement la
seconde tache dont `FER-3` a besoin pour que la ligne ferme. Ouvrir le Done ferait passer
`EPS-CP-04` a `couvert-valide` avec une moitie de competence que le jeu ne verifie pas.

**Le refus est proportionne et actionnable : huit distracteurs a reecrire.** Aucun item a ajouter,
aucune mecanique a toucher, aucun enonce a changer, toute la tache A conservee. Forme qui tient, sur
l'item 9 : *pour porter secours en cas de danger* / *pour verifier que l'eau n'est pas trop froide* /
*pour compter les nageurs qui entrent dans le bassin* - trois raisons serieuses, deux fausses, et
l'enfant doit alors savoir a quoi sert un surveillant de bassin. **C'est la *"forme qui tient"* que
j'ai ecrite dans `CAT-1` elle-meme.**

**La ligne `EPS-CP-04` ne ferme pas. Elle reste `non couvert`.**

### Motifs de refus - la liste nommee

#### `SEC-R3` - la tache B se gagne au champ lexical du danger (`FAB-1c` branche registre, `CAT-1`)

Mesure ci-dessus, quatre items sur cinq. **Bloquant.**

**Ce que j'attends** : sur les items 8, 9, 10 et 11, les **trois** propositions nomment un danger,
une blessure ou une consequence, dont **deux fausses**. **Puis rejeu par moi.**

### Reserves non bloquantes

#### `SEC-R1` - les items 3 et 8 servent la securite routiere du pieton, pas `EPS-CP-04`

**Constat.** L'item 3 (*"Avant de traverser la rue, que doit-on faire ?"*) et l'item 8 (*"Pourquoi
regarde-t-on des deux cotes avant de traverser ?"*) portent sur la traversee de rue **a pied**. Le
libelle dit *"propres a l'activite et au lieu de pratique"*, et le domaine ou j'ai range cette ligne
borne ses milieux par une parenthese que j'ai moi-meme ecrite : *"(dans l'eau, sur un engin roulant,
sur un parcours d'equilibre)"*. **Un pieton qui traverse une rue n'est ni dans une activite physique
ni dans un lieu de pratique d'EPS** : il est dans le champ de la premiere education a la route.

**Ce que je ne fais pas, et le motif est de gouvernance.** Je **ne retire pas** ces deux items du
compte de `FER-2`, ce qui ferait tomber la ligne a neuf items et rendrait la condition non tenue.
**La barre que j'avais ecrite est *au moins dix items servent la ligne*, et le lot l'atteint sur la
lecture qui avait cours quand il a ete ecrit.** L'exigence d'ancrage que je formule est une lecture
de **premier contact** ; l'appliquer aujourd'hui pour refuser serait deplacer mon poteau apres le
tir. Elle devient la condition **`EPS-5`**, datee du 2026-09-09, **opposable au second lot d'EPS et
pas a celui-ci**.

**Mon propre exemple est en cause, et je le dis** : j'ai ecrit le 2026-09-03 *"Ou regarde-t-on avant
de traverser en trottinette ?"*. L'engin roulant y etait et c'est lui qui rattachait l'exemple a la
ligne ; les items 3 et 8 l'ont laisse tomber. **Une equipe qui suit mon exemple mot pour mot n'aurait
pas produit ce defaut.**

**Ce que j'attends** : que les deux items portent un engin ou un lieu de pratique, ou qu'ils cedent
la place a deux items d'une activite d'EPS. **Le contenu est juste et utile : c'est son ancrage qui
est ailleurs, pas sa verite.**

#### `SEC-R2` - item 1, *mettre de la creme solaire* est une reponse defendable

**Constat.** *"Avant d'entrer dans une piscine, que doit-on faire ?"*, bonne reponse *attendre
l'autorisation de l'adulte*, distracteur *mettre de la creme solaire*, sous une consigne qui demande
*"quelle regle de securite s'applique"*. **Mettre de la creme solaire avant un bassin decouvert est
une regle de securite reelle**, et beaucoup d'enfants l'ont entendue de leurs parents. L'enfant qui
la choisit est marque faux, et l'indice qu'il recoit (*"Un adulte doit d'abord donner le signal
d'entrer dans l'eau."*) **affirme l'autre regle sans ecarter la sienne**.

**Non bloquant** : la bonne reponse reste la meilleure des trois et la regle enseignee est juste. Ce
distracteur fait par ailleurs exactement ce que `CAT-1` demande - il sonne prudent, donc il empeche
de gagner l'item au ton. **C'est son effet de bord qui pose probleme, pas son intention.**

**Ce que j'attends** : un distracteur prudent qui ne soit pas une regle de securite concurrente,
**ou** un indice qui ecarte explicitement la creme (*"La creme solaire protege du soleil, pas de
l'eau."*).

### Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **La bonne reponse est la plus courte des trois sur les items 1, 2 et 6** (trois sur six de la
  tache A). J'ai regarde si `FAB-1c` mordait par la **longueur** : **elle ne mord pas**, la direction
  n'etant pas constante - la bonne reponse est la plus longue sur l'item 4 et a egalite sur l'item 5.
  Un enfant qui prendrait toujours la plus courte gagnerait trois items sur six, a peine au-dessus du
  hasard a trois propositions. **Je le mesure et je l'ecarte.**
- **L'item 6 est le seul de la tache A dont les deux distracteurs sont imprudents** (*sauter dessus
  des qu'on arrive*, *monter a plusieurs en meme temps*). C'est le motif de `CAT-1` sur **un** item de
  six ; je ne fais pas une reserve d'un item isole quand j'en fais une d'une tache entiere a cote.
- **La consigne du catalogue** (*"Lis bien, puis dis quelle regle de securite s'applique, ou pourquoi
  elle existe."*) nomme les deux taches, tient sur une ligne et ne reprend pas la formule generique.
  **`DEC-0078` est tenue. Je ne la conteste pas.**
- **L'indice de l'item 7** (*"En cas de chute, la tete est la partie la plus fragile."*). Le
  superlatif est discutable en anatomie ; il est juste sur ce qui compte - la gravite de la
  consequence - et c'est la formulation d'usage au CP. **Je l'accepte et je ne le route pas.**
- **Le mot *maitresse*** dans un distracteur de l'item 4. Terme d'usage au CP, en position de
  distracteur, aucune fonction genree ailleurs dans le paquet. **Ecarte.**

### Mentions de couverture partielle

**Elle est due, elle est exigee d'avance, et elle est reprise mot pour mot depuis
`squad/programme/education-physique-et-sportive/competences-cp.md`. Elle vaut le jour ou cette ligne
bougera, et pas avant.**

> *"connaissance de la regle de securite seule ; son respect en situation n'est pas servi"*
> (referentiel, ligne 4)

> *"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste
> et aucune performance ne sont servis par ce produit"* (referentiel, section *Mention exigee
> d'avance si une ligne bouge*)

**Je ne les reformule pas et je n'en retire pas un mot.** La seconde est le refus d'avance n5 que
j'ai pose le 2026-09-03 : sans elle, **je refuse la fermeture de cette ligne meme quand son contenu
sera bon**. Aujourd'hui la question ne se pose pas, la ligne ne fermant pas ; **je l'ecris quand
meme, pour qu'elle soit lisible avant la prochaine tentative et non redemandee apres.**

**Et je le dis sans detour : quatre lignes vertes sur onze en EPS ne devront en aucun cas se lire
comme une couverture partielle de l'education physique et sportive. Ce qui serait couvert, c'est ce
qu'on sait a propos de l'EPS, pas l'EPS.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce verdict ne dit rien de ce que l'enfant voit sans faire defiler la page, y compris
> le retour qui suit sa reponse - verdict, indice, proposition. Cette propriete est sortie de la
> porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28 (`DEC-0053`) ; sa dette est
> ouverte, croissante et assumee. **Aucune mesure ne l'a instruite sur ce paquet.**

### Ce dont je reste la seule garde sur ce paquet

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Garde executable ? |
|---|---|
| **`FAB-1c` branche registre** | **aucun**, et c'est un jugement de registre qu'aucun automate ne rend. **C'est le motif de ce refus : sans moi, cette ligne fermait** |
| **`CAT-1`, `EPS-4`** | **aucun** |
| **`EPS-2`, `EPS-3`** | **aucun** |
| **`PLA-1`** | **aucun** |
| **L'exactitude de chaque regle de securite** | **aucun** |
| **`EPS-5`** - l'ancrage a une activite ou a un lieu de pratique d'EPS | **aucun**, et la condition est neuve de ce jour |
| **`LON-1`, `LON-2`** | **`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, depuis `ADR-0089`** - le seul garde-fou de cette fiche que je n'aie pas eu a jouer moi-meme. **Ma barre est necessaire, jamais suffisante** |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`** | **aucun sur ce paquet** : aucun test de paquet n'existe pour l'EPS. **Je les ai comptes a la main** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EPS-CP-04` reste `non couvert`** | `camille-po` | La ligne ne passe pas a `couvert-valide`. Le compte de la matrice lui appartient ; je ne la touche pas |
| **`SEC-R3`** (motif bloquant) | `camille-po`, via `sofia-sm` | Reecrire les huit distracteurs des items 8, 9, 10, 11. **Puis rejeu par moi** |
| **`SEC-R1`, `SEC-R2`** (reserves) | `camille-po`, via `sofia-sm` | A traiter dans la meme reprise, le paquet etant de toute facon rouvert |
| **`EPS-4`, `EPS-5`** | toute l'equipe, pour le second lot d'EPS | Deux conditions opposables, allouees au registre dans ce meme diff |
| **Un test de paquet pour ce paquet** | `theo-archi`, via `sofia-sm` | `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b` sont mordables - `PaquetCm1EmcDroitDevoirTest` le fait deja pour l'EMC |

### Historique des verdicts sur ce paquet

**Un refus garde sa trace. Rien n'est efface ici, on ajoute.**

| Date | Verdict | Instance | Motif en une ligne |
|---|---|---|---|
| 2026-09-09 | **REFUSE** | C | `SEC-R3` - la tache B se gagne au champ lexical du danger sur quatre items sur cinq (`FAB-1c` branche registre) |

---

## Bloc de validation pedagogique - second passage, 2026-09-10 - nadia-prof UNIQUEMENT

> **Rien n'est efface au-dessus. On ajoute.** Le bloc du 2026-09-09 reste verbatim : un refus efface
> n'a aucune valeur de preuve, et le premier motif de ce paquet a ete paye - il doit rester lisible.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-10
- **Auteur** : `nadia-prof`, **instance C de s33**
- **Increment audite** : `S33-EPS-SEC`, commit `556cad9f`, fusionne en `e5aebf9c`, arbre `b45e5927`
- **Audit joue** : **oui.** Jar reconstruit depuis un worktree dedie, application lancee sur un port
  local, arretee **par l'identifiant de son processus**. **Huit parties, quatre-vingts manches, les onze
  items vus a l'ecran**, avec erreur volontaire sur chaque item non encore rencontre pour lire
  l'indice servi.
- **Audit degrade sur un point, declare** : joue par requetes HTTP reelles, donc **HTML servi** et
  non page rendue par un navigateur. **Ni couleur, ni contraste, ni taille, ni position** - aucune
  conclusion de ce bloc n'en depend.
- **`PAR-14` s'applique a cette reprise, et c'est une correction contre l'equipe que je fais
  moi-meme** : **six items sur onze touches** (item 1 indice, item 3 enonce, item 8 enonce et
  propositions et indice, items 9, 10, 11 propositions et indice), donc plus de la moitie du paquet.
  Ma phrase du planning et de `DEC-0085` - *"aucune autre reprise de ce sprint n'atteint la moitie de
  son paquet"* - **est fausse, et elle l'etait deja sur le perimetre planifie**. La reprise vaut
  **1,0 et non 0,5**, et **j'ai rebalaye toutes les conditions du paquet**, ce que ce tarif achete.

### Les quatre motifs de s32 sont leves, un par un

| Motif | Etat | Mesure |
|---|---|---|
| **`SEC-R3`** (motif bloquant du premier refus) | **LEVEE** | Sur les items 8, 9, 10 et 11, **les trois propositions nomment un danger, une blessure ou une consequence, dont deux fausses**. Verifie une par une. **La strategie *prendre la reponse qui parle de se faire mal* ne gagne plus rien**, et `FAB-1c` branche registre est **tenue** sur la tache B - les deux cases non cochees de la section de `camille-po` ci-dessus sont a cocher ; **c'est sa section, je ne la reecris pas** |
| **`EPS-7`** | **LEVEE a la lettre** | Aucun des quatre indices ne reprend le mot qui distinguait sa bonne reponse : *voiture*, *danger*, le choc, *blesser* ont disparu. Ce qui reste est `SEC-R5`, **non bloquant, et sa cause est chez moi** |
| **`SEC-R1`** | **LEVEE** | Les items 3 et 8 portent un engin roulant. `EPS-5` demandait *"un engin roulant, l'eau, un agres, un parcours, un gymnase, une cour"* ; la trottinette en est un. **Je ne rediscute pas ma propre condition apres coup** |
| **`SEC-R2`** | **LEVEE** | L'indice de l'item 1 ecarte la creme solaire explicitement, dans la formulation que j'avais donnee |

**Le reste du paquet est intact et reste bon** : toute la tache A, la lisibilite - six a dix mots par
enonce, une phrase, aucun mot hors du vocabulaire de CP -, le traitement de l'erreur, l'absence de
tout stereotype. **`FER-1` vacuement tenue, `FER-2` onze items, `FER-3` deux taches a six et cinq,
`BIN-1` zero item binaire, `FAB-1a`, `FAB-1b`, `PLA-1`, `EPS-2`, `EPS-3`, `BIA-4` tenues** -
reverifiees, non rediscutees. **L'equipe n'a pas travaille pour rien, et le refus d'aujourd'hui porte
sur un defaut que la reprise a introduit.**

### Motif du refus - `SEC-R4`

#### `SEC-R4` - items 3 et 8 : l'enonce met l'enfant sur sa trottinette, les propositions le decrivent a pied, et la reponse creditee n'est pas la regle qui s'applique

**Element 1 - le mot ajoute a l'enonce n'a pas ete porte dans les propositions.** Verifiable sans
aucune expertise.

| Item | Ce que dit l'enonce | Ce que disent les propositions |
|---|---|---|
| 3 | *"Avant de traverser une rue **en trottinette**, que doit-on faire ?"* | *"courir sans s'arrêter"* - **on ne court pas sur une trottinette** ; *"attendre qu'un camarade traverse d'abord"* - deux pietons |
| 8 | *"Pourquoi regarde-t-on des deux côtés avant de traverser **en trottinette** ?"* | *"pour éviter de trébucher sur le trottoir"*, *"pour ne pas se cogner à un passant"* - **un enfant a pied sur un trottoir** |

**Element 2, et c'est celui qui bloque - la reponse creditee n'est pas la regle qui s'applique, sur
une ligne dont l'objet est la securite du corps.** A la question *"Avant de traverser une rue en
trottinette, que doit-on faire ?"*, le jeu credite **"regarder des deux côtés"** et ne propose rien
d'autre. Ce qu'on apprend a un enfant de cet age, c'est qu'**on descend de sa trottinette et qu'on la
pousse a la main pour traverser**. Regarder vient ensuite, et ne vient jamais seul.

**Un enfant de six ans qui joue cet item retient qu'on traverse la rue sur sa trottinette apres avoir
regarde.** Le premier geste de la regle manque, et c'est celui qui sort le corps de la chaussee.

**Pourquoi c'est un refus et non une reserve.** Mon critere du 2026-09-06, reconduit et non amende :
un defaut est bloquant si *quelque chose de faux est enseigne* **ou** si *un item se gagne sans la
competence*. **La premiere branche mord.** Aucune phrase du paquet n'est fausse prise isolement -
*"regarder des deux côtés"* est vrai. Mais **un jeu de choix n'enseigne pas des phrases : il enseigne
la reponse qu'il credite**, et celle-ci est la mauvaise reponse a la question posee. Devant un eleve
qui me repondrait cela dans ma classe, je corrigerais ; le jeu, lui, affiche *"Bravo !"*.

**Ma part dans ce defaut, et je l'ecris avant de demander quoi que ce soit.** L'exemple
*"Ou regarde-t-on avant de traverser en trottinette ?"* est **de moi**, ecrit le 2026-09-03 dans le
referentiel, et **repris mot pour mot dans `SEC-R1` a s32 pour dire ce que j'attendais**. L'equipe l'a
suivi a la lettre. **Mon exemple posait la question au mauvais endroit de la regle**, et rien ne
permettait de s'en mefier. `SEC-R1` est donc **levee** et **je ne refuse pas sur elle** : le refus
porte un identifiant neuf et date, sur un fait que la reprise a cree. **La correction de l'exemple du
referentiel m'appartient et je me la route.**

**Ce que j'attends - deux voies, et je dis laquelle je prendrais.**

1. **Remplacer les deux items par deux items d'une activite d'EPS** - la cour, le gymnase, un agres,
   un parcours d'equilibre, l'eau. **C'est la voie que je prendrais** : elle regle la regle de
   securite *et* l'ancrage.
2. **Ou corriger la regle** : *"En trottinette, comment traverse-t-on une rue ?"* avec *"on descend
   et on la pousse"* en bonne reponse, et deux propositions qui decrivent aussi un enfant sur une
   trottinette. L'item 8 suit : *"Pourquoi descend-on de sa trottinette pour traverser ?"*

**Dans les deux cas : `FER-2` reste a onze items, `FER-3` ne bouge pas, la tache A n'est pas touchee,
rien de technique n'est en cause. Deux items sur onze. Puis rejeu par moi.**

### Reserve non bloquante

#### `SEC-R5` - trois des quatre indices reecrits n'apportent plus de fait a l'enfant qui vient d'echouer

| Item | Indice avant s33 | Indice servi aujourd'hui | Ce qu'il apprend |
|---|---|---|---|
| 8 | *"Une voiture peut arriver de la gauche comme de la droite."* | *"Regarder des deux côtés permet de voir ce qui arrive avant de traverser."* | **Rien** : il repete l'enonce en le retournant |
| 9 | *"Un danger dans l'eau peut survenir très vite."* | *"Un adulte présent peut réagir tout de suite si besoin."* | **Le fait qui tranche.** Celui-ci est bon, et il montre que c'etait faisable |
| 10 | *"Une glissade à deux peut faire tomber l'un des deux enfants."* | *"Descendre seul laisse de la place en bas du toboggan."* | Un fait vrai, mais **pas la raison de la regle** |
| 11 | *"Plonger dans une eau trop basse peut blesser la tête ou le cou."* | *"Vérifier avant de plonger évite les mauvaises surprises."* | **Rien**, et *"les mauvaises surprises"* est lu au premier degre au CP - une surprise, a six ans, est une bonne chose |

**Les trois propositions d'un item nommant desormais toutes un danger, l'indice est le seul texte qui
peut dire lequel est la vraie raison. Trois fois sur quatre, il ne le dit plus.**

**Non bloquant, et le motif est de gouvernance** : `EPS-7`, telle que je l'ai ecrite, ne demandait que
le **retrait** d'un mot, jamais le fait a mettre a la place. **L'equipe a tenu ma condition ; je ne
refuse pas sur ce que je n'avais pas ecrit.** La condition qui l'ecrit est **`EPS-8`, datee du
2026-09-10**, opposable au prochain lot et pas a celui-ci.

**Ce que j'attends** : que chaque indice de la tache B donne **un fait sur la situation** que l'enfant
ne peut pas deviner, comme le fait celui de l'item 9. Exemple sur l'item 11 : *"Sous l'eau, le fond
est plus près qu'il n'en a l'air."*

### Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **L'indice de l'item 3 (tache A) dit *"aucune voiture n'arrive"*, qui est la bonne reponse de
  l'item 8 (tache B).** J'ai regarde si cela designait l'item 8 : **je l'ecarte.** Un indice de tache
  A qui enseigne la raison, puis un item de tache B qui la demande, est un ordre d'apprentissage
  correct - le contraire du defaut de `JEU-R7` sur le paquet voisin. Meme constat entre l'item 4 et
  l'item 10 sur le toboggan. **C'est du bon travail et je ne le penalise pas.**
- **L'indice de l'item 1 fait deux phrases et dix-sept mots**, le plus long du paquet. `LON-1` et
  `LON-2` portent sur les **enonces** et sont executees par `ADR-0089`. **Je n'ai aucune barre sur la
  longueur des indices et je n'en cree pas une en cours de sprint** : je note que la question existe.
- **Le mot *maîtresse*** reste dans un distracteur de l'item 4, seul adulte d'ecole genre du paquet.
  **Un sur un ne fait pas une population.** Ecarte a s32, ecarte encore.
- **La consigne de catalogue** nomme les deux taches. **`DEC-0078` tenue.**

### Ce dont je reste la seule garde sur ce paquet - mise a jour du 2026-09-10

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque regle de securite** | **aucun.** **C'est le motif du refus d'aujourd'hui, et les quatre jobs sont verts sur l'arbre qui le porte.** Aucun automate ne sait ce qu'on fait d'une trottinette au bord d'une chaussee |
| **La coherence entre l'enonce d'un item et ses propositions** (`SEC-R4`, element 1) | **aucun**, et c'est un defaut a dix mots qu'`ADR-0089` ne peut pas voir |
| **`EPS-4`, `EPS-7`, `EPS-8`** | **aucun** : jugement de registre, puis jugement de substance |
| **`FAB-1c` branche registre, `CAT-1`** | **aucun**, et elles sont **tenues aujourd'hui** - premiere fois sur ce paquet |
| **`PLA-1`, `EPS-2`, `EPS-3`, `EPS-5`** | **aucun**, toutes tenues |
| **`LON-1`, `LON-2`** | **`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, depuis `ADR-0089`** - **et il n'a rien vu de `SEC-R4`, qui est un defaut a dix mots.** Ma barre est necessaire, jamais suffisante |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`** | **aucun** : aucun test de paquet n'existe pour l'EPS. **Comptes a la main pour la deuxieme fois** - route a `theo-archi` a s32, non paye |

### Ce que je route - 2026-09-10

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EPS-CP-04` reste `non couvert`** | `camille-po` | La ligne ne passe pas a `couvert-valide`. **Je ne touche pas la matrice** |
| **`SEC-R4`** (motif bloquant) | `camille-po`, via `sofia-sm` | Deux items sur onze, par l'une des deux voies ci-dessus. **Puis rejeu par moi** |
| **`SEC-R5`** (reserve) | `camille-po`, via `sofia-sm` | Dans la meme reprise, le paquet etant de toute facon rouvert |
| **Les deux cases `FAB-1c` et `CAT-1`** de la section de criteres | `camille-po` | Elles sont tenues ; **c'est sa section et je ne la reecris pas** |
| **L'exemple *"traverser en trottinette"* du referentiel d'EPS CP** | moi-meme | Correction dans le prochain diff de ce fichier. **C'est ma faute** |
| **La frontiere entre `EPS-CP-04` et la premiere education a la route** | `camille-po` et moi | A ecrire dans le referentiel. **Non opposable retroactivement a ce lot** |
| **`EPS-8`** | toute l'equipe, pour le prochain lot d'EPS | Condition opposable, allouee au registre dans le meme diff que ce verdict |
| **`PAR-14` s'applique aussi a cette reprise** | `camille-po` et `sofia-sm` | Correction de chiffrage, 0,5 vers 1,0. **Elle est de moi et elle est contre l'equipe** |

### Historique des verdicts sur ce paquet - suite

| Date | Verdict | Instance | Motif en une ligne |
|---|---|---|---|
| 2026-09-10 | **REFUSE** | C (s33) | `SEC-R4` - les items 3 et 8 mettent l'enfant sur sa trottinette, decrivent un pieton dans leurs propositions, et creditent une regle de traversee dont le premier geste manque. **Les quatre motifs de s32 sont leves** |
