# Fiche de conformite - bandit-manchot-saisons-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-saisons.yaml`
  (16 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-saisons-cp`, titre servi *"Les mois et les saisons"*,
  consigne servie *"Lis bien, puis nomme le mois ou la saison, ou dis lequel vient avant ou
  apres."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-13`.
- **Statut global** : **DONE PEDAGOGIQUE PRONONCE - VALIDE** le 2026-09-24 par `nadia-prof`
  (instance B), apres partie complete jouee. Voir le bloc de validation en fin de fiche. Fiche
  ouverte par `camille-po` a l'ouverture du bloc de construction du sprint s41 (paquet et catalogue
  livres et fusionnes dans `round-two`, commit `b5b07966`).
- **Story** : `S41-QLM-SAISONS` - Nommer et ordonner les mois de l'annee et les saisons
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Se situer dans le
  temps" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-13`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, ligne 368) : *"rentable [...] `FER-1` sur les deux moities, pas sur les douze mois.
  `FER-3` : nommer, et ordonner ('quel mois vient apres... ?')."* Precise par
  `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, ligne 377 : *"Terrain deja
  eprouve par `cp-semaine`."*
- **Recouvrement verifie, aucun double comptage** : cette ligne ne recouvre ni les jours de la
  semaine (`cp-semaine`, mathematiques CP, `MAT-CP-09`), ni la journee - aucun item du paquet ne
  demande l'ordre des jours de la semaine ni de la journee (`squad/programme/questionner-le-
  monde/competences-cp.md`, section "Recouvrements a ne pas double-compter").
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (Done pedagogique VALIDE, `nadia-prof` instance B ;
  ouverture le meme jour par `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-QLM-SAISONS`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les deux moities du
  libelle (nommer un mois ou une saison, ordonner deux mois ou deux saisons l'un par rapport a
  l'autre) sont chacune en bonne reponse sur des items distincts (`FER-1`). Aucune exigence de
  couvrir les douze mois individuellement par un item distinct.
- Etant donne un item de la moitie "nommer", quand il s'affiche, alors il demande le nom d'un mois
  ou d'une saison a partir d'une description ou d'une position (par exemple "quel mois vient apres
  juin ?").
- Etant donne un item de la moitie "ordonner", quand il s'affiche, alors il demande une relation
  d'ordre entre deux mois ou deux saisons (avant, apres), jamais uniquement leur nom.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres mois ou saisons reels et proches
  dans l'ordre du calendrier, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-13` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme que les deux moities du libelle (nommer, ordonner) sont chacune tenues.

## Ce que cette story ne fait pas

Elle ne demande jamais l'ordre des jours de la semaine ni de la journee (deja porte par
`cp-semaine` et par mathematiques CP) ; elle ne fixe aucun contenu factuel precis - reste a
l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-24
- **Auditrice** : `nadia-prof`, instance B (`DEC-0095`, section 9).
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3f6b379f`, application construite depuis les modules de cet
arbre et lancee depuis leurs classes compilees sur le JDK de la toolchain (21.0.10 rapporte par la
machine virtuelle), sur un port dedie - le fat jar etait tenu ouvert par un autre processus.
**Partie complete de dix manches par appels HTTP reels**, avec **trois erreurs volontaires sur les
manches 1, 2 et 3**, ecran de fin atteint - *"Tu as trouve, tout seul : 7 / Tu as joue toutes les
questions : 10"*. **Et j'ai lu les seize items du paquet**, plus les vingt-trois items de
`cp-semaine.yaml`.

**La manche 2 de ma partie etait l'item binaire** (*"Le mois de mai vient-il avant ou apres le mois
d'avril ?"*) : la reserve 1 ci-dessous est un fait de partie jouee, pas une lecture de fichier.

**Ce que l'audit ne couvre pas** : j'ai lu le document servi, pas la page peinte par un navigateur.

### Ce qui a ete verifie, et ce qui tient

- **Les deux moities de mon arbitrage prealable sont tenues, sur des items distincts.** *Nommer* :
  items 3, 4, 7, 8 - le premier mois, le dernier mois, la saison la plus froide, la saison la plus
  chaude ; l'enfant produit un nom a partir d'une description ou d'une position. *Ordonner* :
  items 9 a 16 - une relation d'ordre entre deux ou trois mois ou saisons. **`FER-3` est tenue.**
- **`FER-1` est tenue dans les termes ou je l'avais posee** : *"sur les deux moities, pas sur les
  douze mois"*. Le paquet ne sert pas les douze mois individuellement, et **je ne le lui demande
  pas** - c'est ma propre borne du 2026-09-01, je ne la deplace pas apres coup.
- **Exactitude du contenu, verifiee item par item.** L'ordre des mois, l'ordre des saisons, le
  premier et le dernier mois de l'annee, la saison la plus froide et la plus chaude : **aucune
  erreur**. Les deux items de temperature sont prudemment formules (*"fait-il souvent le plus
  froid"*), ce qui est exact et evite d'enseigner une regle absolue.
- **Les distracteurs sont des mois et des saisons reels et voisins dans le calendrier**, jamais des
  elements sans rapport : *mai* et *aout* contre *juillet*, *novembre* et *janvier* contre
  *decembre*. Un enfant qui se trompe se trompe de rang, pas de categorie - c'est exactement ce
  qu'on veut travailler ici.
- **Aucun item ne porte sur les jours de la semaine ni sur les moments de la journee.** Le
  perimetre que la story interdisait est respecte a la lettre.
- **Niveau CP et charge cognitive.** Consigne d'une ligne. Les dix premiers items sont courts. Une
  seule relation a tenir par item.
- **Traitement de l'erreur, mesure en jouant.** L'indice apporte un fait neuf - *"Avril est le
  quatrieme mois de l'annee, mai le cinquieme"* - et non une reformulation de l'enonce ; la
  revelation debloque a la troisieme erreur sans dispenser du geste.
- **Ton et biais.** Aucune fete, aucun pays, aucun depart en vacances, aucune configuration
  familiale : rien dans ce paquet ne suppose la vie d'un enfant plutot que celle d'un autre. C'est
  la meme prudence que `SEM-7` imposait a `cp-semaine`, et elle est tenue ici sans qu'on la lui ait
  demandee.

### Correction d'un de mes propres constats, avant de m'en servir

**Le paquet et la story affirment tous deux que cette ligne ne recouvre pas `cp-semaine`. C'est
inexact, et l'inexactitude vient de moi, pas de la developpeuse.**

Le referentiel de questionner le monde (section *"Recouvrements a ne pas double-compter"*) reserve
les mois et les saisons a `QLM-CP-13` et laisse la journee et la semaine aux mathematiques. Mais
`SEM-1`, la condition que **j'ai moi-meme ecrite** le 2026-08-31 pour ouvrir `cp-semaine`, dit mot
pour mot : *"moments de la journee, jours de la semaine et leur ordre, **mois et leur ordre,
saisons**. Rien d'autre."* J'ai donc autorise les mois et les saisons dans le paquet de
mathematiques, contre ce que mon propre referentiel reserve a cette ligne-ci. `cp-semaine` porte
aujourd'hui *"Quel mois vient juste apres mars ?"* et *"Quelle saison vient juste apres le
printemps ?"*, qui sont la meme tache que les items 1, 2, 5 et 6 d'ici.

**Ce que cela change, et ce que cela ne change pas.** Cela ne change rien au verdict : la story
n'interdisait que les jours et la journee, la developpeuse a respecte ce qu'on lui demandait, et un
enfant qui rencontre deux fois l'ordre des mois dans deux jeux differents revise, il ne desapprend
pas. Cela regarde la matrice, donc `camille-po` : **quatre items de `cp-semaine` travaillent une
competence comptee sur `MAT-CP-09` alors que le referentiel la loge sur `QLM-CP-13`.** C'est une
question de double-compte, pas de contenu, et elle est a instruire hors de ce Done.

### Reserves, datees du 2026-09-24 - elles n'empechent pas le Done et ne rouvrent rien

1. **Les items 9 et 10 sont binaires** (*"avant"* / *"apres"*), et leurs deux propositions ne sont
   pas des mois mais les deux termes de la relation. **Ce n'est pas un manquement a `BIN-1`** :
   `BIN-1` ne vaut que sur une ligne dont la tache principale est un classement a deux categories,
   et elle plafonne a la moitie des items - ici deux sur seize. **Je ne durcis pas `BIN-1`, je
   plante un poteau a cote** : sur ces deux items, l'enfant gagne une fois sur deux sans rien
   savoir, et ma partie m'en a servi un sur dix manches. La forme a trois propositions des items 11
   a 16 fait le meme travail sans ce defaut ; c'est elle qu'il faut preferer si la ligne est
   reprise.
2. **L'en-tete du paquet classe les items 1, 2, 5 et 6 dans la moitie *nommer*.** C'est inexact :
   *"quel mois vient apres juin ?"* et *"quelle saison vient apres l'hiver ?"* demandent une
   relation d'ordre. La moitie *nommer* repose en realite sur quatre items (3, 4, 7, 8), ce qui
   suffit et ne remet pas en cause `FER-3`, mais **le commentaire decrit le paquet autrement qu'il
   n'est**, et c'est ce commentaire qu'un futur auteur lira.
3. **Les items 11 a 16 font lire deux fois les memes trois mots** : les candidats figurent dans
   l'enonce (*"mars, juin, septembre ?"*) puis sur les trois boutons. Pour un lecteur de CP, c'est
   de la lecture payee deux fois. La forme courte des items 1 a 4 (*"Quel mois vient apres le mois
   de juin ?"*) obtient le meme travail mental pour moitie moins de lecture.

### Ce dont je reste la seule garde sur ce paquet

- **Le partage des mois et des saisons entre `cp-semaine` et ce paquet** : aucun test ne compare
  deux paquets de matieres differentes sur l'objet qu'ils enseignent.
- **L'appartenance d'un item a la moitie *nommer* ou a la moitie *ordonner*** : aucun automate ne
  lit une operation mentale.
- **L'exactitude de l'ordre enseigne** : aucun garde-fou ne verifie qu'octobre suit septembre.

### Consequence pour la matrice

`camille-po` peut passer la ligne `QLM-CP-13` a `couvert-valide`. **Aucune mention de couverture
partielle n'est due** : mon arbitrage prealable exigeait les deux moities et non les douze mois,
et les deux moities sont servies.
