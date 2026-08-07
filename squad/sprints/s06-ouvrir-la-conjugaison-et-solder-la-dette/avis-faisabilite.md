# [Sprint s06] Avis de faisabilite - theo-archi

- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Commit lu** : `095547c`, `HEAD` de `round-two` (`[CAM] s06: perimetre du sprint 6, amendement
  DEC-0012, stories S6-3 a S6-14`). Lignee verifiee avant toute lecture :
  `git rev-parse --abbrev-ref HEAD` rend `round-two`.
- **Backlog lu** : `docs/backlog/backlog.md`, stories **S6-3 a S6-14**. **Ce sont leurs criteres
  d'acceptation qui font foi ici**, pas ma reformulation. S6-1 et S6-2 ne sont pas dans mon
  perimetre : elles ont ete livrees et auditees en s05 (`DEC-0012`, amendement du 2026-08-07).
- **Pieces de reference** : `DEC-0013-perimetre-sprint-6.md`,
  `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md` (amendement),
  `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md` (avis de conception du 2026-08-06),
  `FICHE-bandit-manchot-homophones-cm1.md` et `FICHE-bandit-manchot-additions-cp.md` (verdicts du
  2026-08-07), `squad/sprints/s05-lever-le-refus-du-bon-son/RAPPORT.md`, `review.md`, `retro.md`,
  `ADR-0004`, `ADR-0006`, `ADR-0007`, `ADR-0009`, `ADR-0012`, `ADR-0013`, `ADR-0016`, `ADR-0017`,
  `ADR-0019`.
- **Code relu** : `Question`, `Verdict`, `BanditManchotSession`, `YamlContentPackLoader`,
  `QuestionContentSourceFactory`, `PartieController`, `EtatDePartie`, `EtatDePartieEnSession`,
  `GestionnaireDesErreurs`, `RecreoApplication`, les quatre gabarits, `catalogue.yaml`, les trois
  paquets de contenu, `contenu-repris/textes-trous/cm1-conjugaison.yml`, et les garde-fous
  `ChaquePaquetPorteUnIndiceTest`, `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest`,
  `PaquetsFrancaisS04Test`, `LActionNeDeclarePasMoinsQueLaSortieTest`,
  `TailleDesTextesEtBoutonsTest`, `LesGardeFousSontMisALEpreuveTest`, `LesReglesMordentTest`,
  `GestionDesErreursTest`, `AccesALEtatDeSessionTest`, `PureteDesCouchesTest`.
- **Nom de ce dossier** : `s06-ouvrir-la-conjugaison-et-solder-la-dette`, choisi faute de slug
  existant au moment ou j'ecris. `sofia-sm` anime le planning apres moi et **peut le renommer** ;
  ce fichier suit, il n'a aucune autorite sur le nom du sprint.
- **Perimetre gele** : les trois chantiers de `DEC-0013` ont ete tranches par l'utilisatrice. Je ne
  les rediscute pas. Cet avis ne dit que ce qui est faisable, a quel prix, et quel garde-fou chaque
  story fait bouger.

## Synthese

| Story | Avis | ADR requis | Garde-fou concerne |
|---|---|---|---|
| S6-3 | **Faisable sous condition** : le domaine accepte la forme telle quelle (reponse a la note de frontiere de mandat), mais le paquet repris est **inexploitable en l'etat sur trois points mesures** - 6 items pour 10 manches, aucun `indice`, et le trou `___` sur chaque item | aucun | `ChaquePaquetPorteUnIndiceTest` (rouge si un item n'a pas d'indice, plafond a 0), `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` (entre automatiquement dans son perimetre) |
| S6-4 | **Faisable en l'etat**, contenu seul | aucun | `PaquetsFrancaisS04Test` (trois listes ecrites a la main a mettre a jour), `ChaquePaquetPorteUnIndiceTest`, `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` |
| S6-5 | **Faisable en l'etat**, une ligne de contenu | aucun | aucun ne bouge ; le troisieme critere ("les vingt et un autres indices inchanges") est automatisable |
| S6-6 | **Faisable sous condition** : le troisieme critere demande un **champ neuf** (`graphie`), et le chargeur **ignore silencieusement** toute cle qu'aucune fabrique ne lit | **oui, un ADR** - le meme geste que S6-7, voir plus bas | `PaquetsFrancaisS04Test` (`SONS_ATTENDUS` epingle six sons, la recomposition en garde trois), `ChaquePaquetPorteUnIndiceTest` (21 indices a reecrire pour 15 items) |
| S6-7 | **Non faisable sans decision d'architecture prealable** : le texte doit traverser `Question`, la mecanique et l'ecran de retour, et `Verdict` ne porte rien de tel | **oui, ADR conjoint avec S6-6** | `LesGardeFousSontMisALEpreuveTest` : `DETTE_DE_BALAYAGE` est **pleine (3/3)**, le garde-fou neuf ne peut pas y etre exempte. Cout chiffre plus bas |
| S6-8 | **Faisable en l'etat** | aucun | `ViewportEtLargeurTelephoneTest`, `TailleDesTextesEtBoutonsTest`, `HabillageDesEcransTest` (une classe orpheline y est rouge) |
| S6-9 | **Condition levee le 2026-08-07 : `nadia-prof` a tranche.** Faisable ; les deux questions techniques qu'elle m'a rendues sont tranchees et livrees dans ce commit (`ADR-0022`). Ce qui reste est son verdict en jouant | **ADR-0022**, plus le second amendement d'`ADR-0017` | `LActionNeDeclarePasMoinsQueLaSortieTest` amende (un epinglage supprime avec motif, un ajoute), `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` (neuf) |
| S6-10 | **Faisable en l'etat** | aucun | `LActionNeDeclarePasMoinsQueLaSortieTest` reste vert par construction (il ne lit aucune couleur) ; c'est justement sa limite nommee |
| S6-11 | **Tranchee et livree dans ce commit** : `ADR-0020`. Le defaut est reel, sa cause est trouvee | **ADR-0020, ecrit avec son garde-fou dans le meme geste** | `ToutEcranEchangeEstUnDocumentJouableTest` (neuf) |
| S6-12 | **Tranchee et livree dans ce commit** : `ADR-0021` | **ADR-0021, ecrit avec ses deux garde-fous dans le meme geste** | `ScanDeComposantsTest` et `AucunContreExempleNEstUnBeanDeLApplicationTest` (neufs) |
| S6-13 | **Faisable en l'etat**, et le mot "teste" a un sens precis ici : le script doit etre coupe en deux, un juge pur et une collecte | aucun (`ADR-0009` couvre deja la doctrine) | `ConcordanceDesTagsDAgentTest` inchange ; le script neuf a ses propres contre-exemples |
| S6-14 | **Faisable sous condition** : tel qu'ecrit, son premier critere **produit exactement ce qu'`ADR-0010` interdit** dans le fichier ou son second critere demande de le coller | aucun, mais **le critere doit etre corrige avant developpement** | `verifier-chemins-de-poste.sh` (`ADR-0010`) rougirait sur la sortie collee dans `rapport-qualite.md` |

**Deux stories ne sont pas faisables telles qu'ecrites** : S6-7 sans decision d'architecture
prealable (que j'ecris ci-dessous avec S6-6), et S6-14 dont un critere se contredit avec `ADR-0010`.
**Deux sont faisables sous condition nommee** (S6-3, S6-6). **Deux sont livrees dans ce
commit** (S6-11, S6-12), parce qu'elles m'etaient routees comme prealables techniques et non comme
stories a coder par un autre.

**Mise a jour du 2026-08-07, apres l'arbitrage de `nadia-prof` sur S6-9** (`FICHE-bandit-manchot-additions-cp.md`,
section "Arbitrage prealable au developpement - S6-9") : la condition que j'avais posee sur S6-9 est
**levee**, elle a tranche. Les deux questions techniques qu'elle m'a rendues sont tranchees par
`ADR-0022` et livrees dans le meme commit que cette mise a jour. S6-9 passe donc de "faisable sous
condition" a **faisable**, sa part technique etant close.

**Seconde mise a jour du 2026-08-07, a l'ouverture des lots (commit lu : `cf48cae`).** Deux
engagements de cet avis sont tenus, et une de ses mesures est corrigee.

1. **Le trou de garde-fou que j'avais nomme sans le refermer** (section S6-3, "un trou de garde-fou que
   cette story met en lumiere") est ferme par **`ADR-0023`** et
   `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`. Le perimetre est le catalogue, pas une liste
   de paquets : l'entree neuve `bandit-manchot-conjugaison-present-cm1` y est jugee sans y avoir ete
   inscrite, et les deux methodes enumerees de `PaquetsFrancaisS04Test` sont supprimees. La condition
   que j'avais posee ("il n'a de sens qu'attache au critere qui tranche entre 6 manches et plus
   d'items") est levee : le produit a tranche, douze items pour dix manches.
2. **La decision d'architecture que S6-7 attendait** est prise : **`ADR-0024`**. `Verdict` porte
   l'explication de l'item joue, la graphie reste une declaration du paquet. **Je corrige ici mon
   propre raisonnement** : je penchais pour `Verdict` par un argument de semantique (deux moments
   d'affichage distincts), qui tient mais n'est pas le bon. La raison est structurelle - apres
   `jouer`, la session ne tient plus l'item joue, donc un accesseur separe aurait exige un champ
   parallele au verdict, la forme exacte du defaut d'`ADR-0016`. S6-6 et S6-7 passent de "non faisable
   / faisable sous condition" a **faisables**, la part architecture etant close ; ce qui reste a
   `lea-dev` est enumere dans la section "Ce qui reste au lot de `lea-dev`" d'`ADR-0024`.
3. **Correction de chiffre.** Cet avis annonce **42 sites `new Question(`**, mesures a `095547c`. A
   `cf48cae` ils sont **56** (meme methode : `git grep -o "new Question(" -- "*/src/main/*"
   "*/src/test/*"` sur les cinq modules du pom agregateur). Les quatorze d'ecart viennent
   entierement du lot de `marc-dev` fusionne depuis - douze items de conjugaison et deux items
   d'homophones epingles dans `PaquetsFrancaisS04Test`. La conclusion que j'en tirais ne change pas,
   elle se renforce : le constructeur de commodite a cinq arguments est indispensable, et il est
   livre.

---

## Chantier 1 - ouverture conjugaison CM1 et solde des homophones

### S6-3 - Choisir la bonne forme d'un verbe frequent au present (CM1)

**Avis : faisable sous condition. Le domaine accepte cette forme de contenu sans modification - je
reponds ainsi a la note de frontiere de mandat de la story. Mais le paquet repris est inexploitable
en l'etat sur trois points, tous mesures, dont deux qu'aucun critere de la story ne nomme.**

#### La note de frontiere de mandat : reponse, mesuree

La story demande si "plusieurs distracteurs par item, comme dans la matiere premiere" rentre dans
`Question` sans changement du domaine. **Oui, sans une ligne.** `Question` porte
`List<String> distracteurs` depuis S0-1, `BanditManchotSession.melanger` traite n propositions par
un melange de Fisher-Yates sur la liste entiere, et le jeu de mathematiques en affiche deja trois.
`contenu-repris/textes-trous/cm1-conjugaison.yml` porte trois distracteurs par item, donc quatre
propositions. **Aucun ADR, aucune reformulation de story.** La story peut etre developpee telle
qu'ecrite sur ce point.

Un seul garde-fou impose `hasSize(1)` sur les distracteurs, et il ne concerne pas cette story :
`PaquetsFrancaisS04Test.homophonesCm1NePresenteQueLesCouplesAttendusAvecUnUniqueDistracteurApparie`
ne porte que sur `cm1-homophones`. Verifie a la lecture, pas suppose.

#### Trois points qui bloquent, mesures sur le paquet repris

1. **Six items pour dix manches.** `grep -c "  - question:"` sur
   `contenu-repris/textes-trous/cm1-conjugaison.yml` rend **6**. C'est aussi exactement le nombre
   qu'exige le troisieme critere de la story (les six personnes grammaticales, un item chacune) :
   le paquet repris est donc au minimum strict. Si l'entree de catalogue reprend
   `nombreDeManches: 10` comme les deux autres jeux, `ListContentSource` s'epuise au sixieme
   tirage, `BanditManchotSession.tirerManche` rend une session `TERMINEE` et la partie s'arrete
   avant l'heure - sans erreur, sans message, **et sans qu'aucun garde-fou ne le voie**. Deux
   sorties : porter `nombreDeManches` a 6 au plus, ou ecrire plus d'items. C'est un choix de
   produit, je ne le tranche pas.
2. **Aucun `indice`.** Le paquet repris n'en porte aucun, et
   `ChaquePaquetPorteUnIndiceTest.chaquePaquetDeProductionPorteUnIndiceSurChacunDeSesItems` derive
   son perimetre du **repertoire de contenu** : le paquet est rouge des qu'il est deposé dans
   `adapters/adapter-content/src/main/resources/contenu/`, avant meme d'entrer au catalogue. Le
   `PLAFOND_DE_DETTE` d'`ADR-0016` vaut **0** depuis le 2026-08-07 : ce paquet ne peut pas etre
   exempte sans modifier cette ligne, ce qui se voit dans un diff. **Un indice par item, six
   indices, texte pedagogique donc a relire par `nadia-prof`** - la story ne le mentionne pas et
   c'est du travail reel.
3. **Le trou `___` sur chaque item.** `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` derive du
   repertoire quel paquet utilise la convention du trou (marqueur present sur au moins un item) et
   l'exige alors sur **tous** ses items. Le paquet repris utilise `___` partout : il entre donc
   automatiquement dans ce perimetre, et un seul item ecrit sans trou le rend rouge. C'est une
   bonne nouvelle, pas un obstacle - c'est exactement le defaut que l'utilisatrice avait trouve en
   jouant sur les douze items de S5-6, desormais impossible a reproduire.

#### Un trou de garde-fou que cette story met en lumiere, et que je nomme sans le refermer ici

La propriete "un paquet porte plus d'items que la partie n'a de manches" est verifiee par
`PaquetsFrancaisS04Test.homophonesCm1CompteDavantageDItemsQueDeManchesParPartie` - **pour le seul
paquet `cm1-homophones`, nomme dans le titre de la methode**. C'est un perimetre enumere, donc la
faute exacte qu'`ADR-0013` interdit : le paquet de conjugaison arrive et sort du controle en
silence. Le point 1 ci-dessus n'est pas une hypothese, c'est ce trou-la qui le rend possible.

Ce garde-fou se derive sans difficulte : pour **chaque entree du catalogue**, compter les items que
le paquet rend par le chargeur reel et les comparer a `nombreDeManches`. Le harnais existe deja
dans les deux garde-fous de contenu. Une trentaine de lignes, un jeu fautif (une entree de
catalogue plus longue que son paquet). **Je ne l'ecris pas dans ce commit** et je dis pourquoi :
il n'a de sens qu'attache au critere de S6-3 qui tranchera entre "6 manches" et "plus d'items", et
ecrire le garde-fou avant la decision me ferait choisir a la place du produit. Si le planning
retient S6-3, il tient dans son lot et je l'ecris.

### S6-4 - Deux phrases de plus au-dela du mot voisin du trou

**Avis : faisable en l'etat, contenu seul. Aucun ADR.**

Ajouter deux items a `cm1-homophones.yaml` ne demande aucune ligne de `domain/`, `application` ni
`adapter-web` - troisieme demonstration du decouplage apres s04 et s05. Trois garde-fous s'y
appliquent automatiquement et sans rien ajouter : l'indice obligatoire (`ADR-0016`), le trou sur
chaque item, et la variete entre deux parties.

**Ce que le lot doit savoir avant de commencer** : `PaquetsFrancaisS04Test` porte trois listes
ecrites a la main (`COUPLES_HOMOPHONES_AUTORISES`, `PARTENAIRES_DE`, et un comptage par couple).
Deux items neufs les rendent rouges si le couple retenu n'y figure pas. C'est une mise a jour de
test, pas un affaiblissement - meme situation qu'en s05, et le mode de defaillance est un rouge,
ce qui est le bon.

Le critere central (le mot decisif n'est pas voisin du trou) reste ce qu'il etait en s05 : **porte
pedagogique seule**. La story l'ecrit ainsi, elle a raison, et je le confirme sans y revenir - un
automate ne sait pas ou est le sujet d'une phrase francaise.

### S6-5 - Un indice qui reste du francais

**Avis : faisable en l'etat. Une ligne de contenu. Aucun ADR.**

Le seul point technique tient au troisieme critere ("les vingt et un autres indices restent
inchanges") : il est automatisable en une assertion, et je recommande qu'il le soit dans le lot
plutot qu'a la relecture. Le reste est du texte, donc du ressort de `nadia-prof` et du relecteur.

---

## Chantier 2 - "Le bon son", lot indivisible

`nadia-prof` a tranche que S6-6 et S6-7 se livrent ensemble ou pas du tout. **Ce lot est aussi
indivisible techniquement, et pour une raison qui n'est pas la sienne** : le troisieme critere de
S6-6 (chaque item declare la graphie qui porte le son) est precisement ce qui permet a S6-7 de
savoir sur quels items afficher son texte. Les deux stories partagent un seul champ de contenu et
un seul ADR. Je les traite donc d'un geste.

### S6-6 - Recomposition en quinze items sur trois nasales

**Avis : faisable sous condition. Quatre criteres sur six sont du contenu pur. Le troisieme
demande un champ qui n'existe pas, et le chargeur le laisserait tomber en silence.**

#### Le piege exact, mesure dans le chargeur

`YamlContentPackLoader.chargerPaquet` valide l'enveloppe (`schema`, `type`, `descripteur`) puis
delegue le corps a la fabrique. `QuestionContentSourceFactory` lit `enonce`, `bonneReponse`,
`distracteurs`, `indice`. **Aucune cle inconnue n'est refusee.** Ecrire `graphie: "en"` sur un item
charge sans erreur, sans avertissement, et la valeur n'atteint jamais la mecanique.

C'est **exactement la forme du defaut d'`ADR-0016`**, dans l'autre sens : la, l'abstraction
existait et un appelant sur deux la remplissait ; ici, le contenu remplirait un champ que personne
ne lit. Un auteur de contenu ecrirait quinze declarations de graphie, verrait sa CI verte, et
`nadia-prof` decouvrirait a l'audit que rien n'en sort. **Ce n'est pas une hypothese, c'est le
comportement mesure du chargeur d'aujourd'hui.**

#### Ce qui bouge dans les garde-fous existants

- `PaquetsFrancaisS04Test.leBonSonCpNePresenteQueDesSonsConnusEtCouvreLesTroisDigraphesCitesParLeReferentiel`
  porte `SONS_ATTENDUS` avec **six** sons. La recomposition n'en garde que trois : le test devient
  rouge. Mise a jour obligatoire, du meme genre que le retrait des crochets en s05 - la liste reste,
  son contenu se reduit. **Le lot doit resserrer cette liste a trois, pas la supprimer** : la
  supprimer rendrait le garde-fou incapable de refuser un quatrieme son introduit par erreur.
- `ChaquePaquetPorteUnIndiceTest` : les 21 indices ecrits en s05 correspondent aux 21 items actuels.
  Quinze items neufs veulent quinze indices neufs, et le plafond a 0 interdit toute exemption
  transitoire. **Le paquet ne peut pas etre recompose en deux commits** dont le premier laisserait
  des items sans indice : la CI serait rouge entre les deux.
- `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` : `cp-le-bon-son` n'utilise pas `___` et reste
  hors de son perimetre, comme sa Javadoc le dit deja. Rien ne bouge, verifie.

### S6-7 - Un retour qui nomme les lettres et le son

**Avis : non faisable sans une decision d'architecture prealable, et cette decision n'est pas dans
ce commit. Voici son cout exact, chiffre, puisque c'est ce qui m'est demande.**

Je n'ecris pas cet ADR aujourd'hui, et je dis franchement pourquoi plutot que de le bacler : la
question "ou vit ce texte" a une bonne reponse (le contenu, `DEC-0005`, `ADR-0016`), mais la
question "par quoi il transite" en a deux, et choisir entre elles demande de savoir ce que
`nadia-prof` attend sur les items **reguliers** - une phrase differente, ou rien du tout. Son avis
de conception dit "sur un item regulier il n'y a rien a expliquer", ce qui tranche l'affichage mais
pas la modelisation. Je le tranche au moment ou le lot demarre, avec la personne qui ecrit les
textes, pas trois jours avant dans un fichier.

#### Le cout, piece par piece, sur le code que j'ai relu

1. **`Question` gagne une composante.** Le record en porte cinq
   (`id`, `enonce`, `bonneReponse`, `distracteurs`, `indice`). Une sixieme, `Optional<String>`. Le
   depot compte **42 sites `new Question(`**, comptes par
   `git grep -c "new Question(" -- "*/src/main/*" "*/src/test/*"` sur les modules du pom
   agregateur, dont 41 dans des tests. Le constructeur de commodite a quatre arguments les absorbe
   tous **a condition d'en ajouter un a cinq arguments dans le meme geste** - sans quoi c'est la
   dette 6 de la version precedente qui revient (un record large ou ajouter un champ oblige a
   editer quinze appels).
2. **`QuestionContentSourceFactory` lit la cle.** Trois lignes, exactement comme `indice` en s05.
3. **Le texte doit atteindre l'ecran de retour, et c'est la le vrai choix.** `fragments/retour.html`
   ne lit que ce que `PartieController.jouer` met au modele, c'est-a-dire
   `Verdict(boolean juste, String bonneReponse)`. Deux sorties, et elles ne coutent pas la meme
   chose :
   - `Verdict` gagne une troisieme composante. Le verdict est le resultat de la manche jouee, et le
     texte porte sur l'item que l'enfant vient de lire : c'est semantiquement juste. Cout : un
     record de domaine elargi, `BanditManchotSession.jouer` qui le construit, et les tests de
     `BanditManchotTest` qui l'instancient.
   - `BanditManchotSession` expose un accesseur separe, sur le modele exact d'`indice()`. Cout
     identique, mais **le precedent existe deja** et il a ete accepte : c'est ainsi que l'aide de
     S3-2 circule.

   Je penche pour `Verdict`, parce que le moment d'affichage differe : `indice()` s'affiche sur
   l'ecran de **manche** et ne vaut qu'apres trois echecs, ce texte-ci s'affiche sur l'ecran de
   **retour** et vaut toujours. Deux moments distincts, deux porteurs distincts. Mais je ne
   l'inscris pas comme tranche avant d'avoir ecrit l'ADR.
4. **La mecanique ne doit rien savoir des nasales.** `ADR-0004` tient si et seulement si la
   mecanique **transporte** ce texte sans le comprendre : elle ne decide jamais qu'un item est "a
   variation", c'est le contenu qui porte le texte ou ne le porte pas. Ecrit ainsi, S6-7 ne touche
   pas une ligne de `domain/mechanics` au-dela du transport, et le critere "aucun texte sur les neuf
   items reguliers" est tenu par le contenu, pas par une condition dans la mecanique. **C'est la
   seule ecriture qui preserve le principe directeur du produit**, et c'est ce que l'ADR fixera.

#### Le cout du garde-fou, et pourquoi il ne peut pas etre reporte

C'est le point sur lequel la story est la plus chere, et il n'apparait nulle part dans ses criteres.

- Le critere a garder est **conditionnel** : le texte est obligatoire sur les items a variation,
  interdit sur les reguliers. Un garde-fou "chaque item porte ce texte" serait faux ; un garde-fou
  "au moins un item le porte" serait decoratif. Le seul critere qui interdit reellement quelque
  chose compare le champ de graphie a la notation du son - ce qui n'existe **que si S6-6 livre son
  troisieme critere**. Sans le champ `graphie`, S6-7 n'a pas de garde-fou possible autre que
  decoratif. C'est la troisieme raison, technique celle-ci, de l'indivisibilite du lot.
- Ce garde-fou lira `src/main/resources`, donc il entre dans le perimetre de
  `LesGardeFousSontMisALEpreuveTest`, qui exige un jeu fautif sous
  `src/test/resources/contreexemple/<NomDeLaClasse>` **et** un `assertThatThrownBy`.
  **`DETTE_DE_BALAYAGE` compte exactement trois noms et `PLAFOND_DE_DETTE` vaut 3 : elle est
  pleine.** Le garde-fou neuf **ne peut pas** y etre exempte, meme provisoirement, sans remonter un
  plafond dont l'ADR dit qu'il ne remonte jamais. Le jeu fautif n'est pas optionnel, il est dans le
  lot. Compter : un paquet neuf a deux items, l'un a variation sans son texte, l'autre regulier
  avec un texte qu'il ne devrait pas avoir, plus la variante corrigee qui doit passer.

**Total pour le lot S6-6 + S6-7** : un ADR, un champ dans `Question`, un champ dans le schema lu
par la fabrique, un porteur jusqu'a l'ecran de retour, un affichage conditionnel dans
`fragments/retour.html`, un garde-fou derive avec son jeu fautif et son corrige, la reduction de
`SONS_ATTENDUS` a trois sons, quinze items et quinze indices reecrits. **Ce n'est pas une story de
contenu, et le backlog la presente comme telle sur ses deux tiers.**

---

## Chantier 3 - dette de forme et d'outillage

### S6-8 - Une marge laterale qui ne disparait plus sous 1200 px

**Avis : faisable en l'etat. Aucun ADR.**

La regression est declarative et se lit entierement dans `recreo.css` : `.zone-jeu { padding: 2rem 0 }`
ecrase le rembourrage lateral de `.container`. Les deux premiers criteres sont automatisables sur la
declaration, sans moteur de rendu - c'est le meme raisonnement qui a rendu `ADR-0017` possible en
s05, et la meme limite s'applique (une declaration n'est pas un pixel rendu ; `nadia-prof`
remesurera, et elle l'annonce elle-meme).

**Un garde-fou existant a connaitre** : `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline`
rougit si une classe posee sur un element n'est nommee par aucune feuille servie. Si le correctif
passe par une classe neuve dans un gabarit, la regle CSS doit arriver dans le meme commit. C'est un
rouge utile, pas un obstacle - je le signale pour que le lot ne croie pas avoir casse autre chose.

### S6-9 - Une seule sortie par ecran de jeu

> **Reecrit le 2026-08-07, apres l'arbitrage de `nadia-prof`.** La condition que j'avais posee est
> **levee** : elle a tranche. Le developpeur affecte doit lire cette section-ci et non la version
> d'avant, conservee plus bas sous "Etat de mon avis avant l'arbitrage" pour que la trace du
> raisonnement reste lisible.

**Avis apres arbitrage : faisable, et la part technique est deja tranchee et livree dans ce commit
(`ADR-0022`). Ce qui reste a S6-9 est du contenu de gabarit et de feuille, plus le verdict
pedagogique.**

`nadia-prof` confirme le principe (une seule sortie par ecran de jeu, mot "Les jeux") et **refuse la
forme** : la sortie qui survit sur un ecran de jeu est le bouton de la zone de contenu, pas le lien de
l'en-tete, et `sortie-pleine` est conserve tel quel. Trois gestes, pas un de plus - libelle du lien de
menu a "Les jeux", lien de menu non visible sur les cinq etats de l'ecran de jeu, fragments non
touches.

**Les deux questions techniques qu'elle m'a rendues sont tranchees par `ADR-0022`** :

1. **L'invisibilite du lien de menu sur un en-tete permanent** : un couple declaratif. Le serveur pose
   le marqueur `ecran-de-jeu` sur la racine de chaque fragment insere dans `#jeu` ; la feuille maison
   porte `body:has(.ecran-de-jeu) .lien-accueil { display: none }`. L'element **reste present** dans
   le document, seule sa visibilite depend de l'etat : `DEC-0012` et le critere de S6-1 sont tenus a
   la lettre, l'en-tete n'est ni efface ni recree. Aucun JavaScript, aucun `hx-swap-oob` - ce dernier
   aurait declenche la dette explicite d'`ADR-0020` sur l'assemblage du document. Ce choix ne rouvre
   pas le defaut d'identifiant : il n'ajoute aucun identifiant, et `ToutEcranEchangeEstUnDocumentJouableTest`
   reste vert.
2. **Le sort de mon extension du 2026-08-07** : amendee, pas supprimee ; l'epinglage qui en dependait
   est supprime avec son motif ecrit et remplace. Detail dans `ADR-0022` et dans le second amendement
   d'`ADR-0017`.

**Ce que le lot doit encore livrer** : rien sur les garde-fous - ils sont livres et prouves mordants -
et rien sur les fragments, que l'arbitrage interdit de toucher. Le lot est donc reduit a ce qui est
deja dans ce commit, plus la remesure de `nadia-prof` en jouant. Deux proprietes restent d'elle seule
et aucun automate ne les prend : que le lien soit reellement invisible sur les cinq etats, et que
"Les jeux" soit dechiffrable au CP.

**Le seul point de vigilance qui reste, et il est reel** : le masquage repose sur le selecteur
relationnel `:has()`. Un navigateur qui ne le connait pas ignore la regle entiere et l'entree
redevient visible sur les ecrans de jeu, **sans aucun signal**. La degradation ramene exactement
l'etat que `nadia-prof` vient d'auditer, pas un ecran casse, et la ligne de base de Bootstrap 5.3.8
deja vendorise est de la meme generation. Nomme en consequence dans `ADR-0022` plutot que decouvert
plus tard.

#### Etat de mon avis avant l'arbitrage, conserve

Ce qui suit est ma lecture du 2026-08-07 **avant** que `nadia-prof` tranche. Deux des trois
consequences que j'y chiffrais sont annulees par l'arbitrage - `.retour-accueil` et `sortie-pleine`
restent, donc les points 2 et 3 ne se produisent pas. Le point 1 s'est produit, et une quatrieme
consequence que je n'avais pas vue est apparue, celle de mon extension.

#### Le garde-fou reste-t-il mordant ? Oui, et voici par quoi

Le mecanisme d'appariement de `LActionNeDeclarePasMoinsQueLaSortieTest` a ete etendu le 2026-08-07 :
un bouton ecrit **hors de la zone `hx-target`** est confronte aux boutons de **tous** les ecrans.
Le lien de l'en-tete (`.btn.lien-accueil`, dans `accueil.html`, hors du `<main id="jeu">`) est donc
apparie a `.btn.proposition` de l'ecran de manche et a `.btn.encore` de l'ecran de retour.

**Consequence : retirer le bouton de sortie des fragments ne prive le garde-fou d'aucun couple.** Il
en garde deux, et l'un des deux est exactement celui que le lot Bootstrap avait inverse. De plus,
`verifierQueLActionNeDeclarePasMoinsQueLaSortie` se termine par
`if (couples.isEmpty()) defauts.add("aucun couple action/sortie n'a ete confronte ...")` : un
appariement vide serait **rouge**, jamais un vert silencieux. Le garde-fou ne peut donc pas devenir
vide de sens par accident, quelle que soit la disposition retenue. C'est une propriete que je
verifie a la lecture du code, pas une esperance.

#### Ce qui devient rouge, et ce qui devient vide - a traiter dans le lot, pas apres

1. `leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu` epingle **deux chaines exactes** finissant
   par `.btn.lien-accueil "Accueil"`. La story change ce libelle en "Les jeux" : **les deux
   assertions deviennent rouges**. Mise a jour obligatoire, et legitime - c'est un epinglage de
   l'etat livre, il suit l'etat.
2. `surLEcranDeRetourLaSortieEstConfronteeAEncoreMaisPasDansSonEtatDeFinDePartie` exige un couple
   contenant `.btn.encore` **et** `.btn.retour-accueil`. Si `.retour-accueil` disparait des
   fragments, **cette assertion devient rouge** et ne peut pas etre simplement supprimee : elle
   epingle le couple que le lot Bootstrap avait inverse. Elle doit etre **reecrite sur le nouveau
   couple** (`.btn.encore` contre `.btn.lien-accueil`), sinon la regression de s03 redevient
   possible sans epinglage.
3. La meme methode assure `noneMatch(couple -> couple.contains("sortie-pleine"))`. Si le bouton qui
   porte `sortie-pleine` disparait, **cette assertion reste verte en ne verifiant plus rien** :
   c'est le garde-fou decoratif exact que la retro de s05 a sanctionne deux fois. Elle doit etre
   supprimee explicitement avec un motif ecrit, ou remplacee. **Un vert qui survit a la
   disparition de son objet est le pire mode de defaillance de ce depot.**

#### Le point que je remonte a `sofia-sm` et `nadia-prof`, parce qu'il n'est pas de mon ressort

`fragments/retour.html` porte
`th:class="${enSuite} ? 'btn retour-accueil' : 'btn sortie-pleine'"`. Cette seconde branche est
l'etat de **fin de partie** : la sortie devient l'aplat le plus saillant quand elle est la seule
action possible. Ce n'est pas un detail de style, c'est **S3-6, demande par `nadia-prof` et validee
par elle en jouant** (`FICHE-bandit-manchot-additions-cp.md`, reserve 4).

Retirer le bouton de sortie des fragments **supprime cet etat**. L'enfant qui termine une partie
n'aurait plus qu'un lien d'en-tete de taille ordinaire la ou il avait un bouton plein. La story ne
dit pas ce qui remplace `sortie-pleine`, et ce n'est ni a moi ni au developpeur de l'inventer. Deux
sorties existent techniquement - garder le bouton des fragments et masquer le lien d'en-tete sur
les ecrans de jeu, ou retirer le bouton et faire grossir le lien en fin de partie - et les deux
tiennent le critere "une seule sortie visible". **Elles ne sont pas equivalentes pour l'enfant, donc
c'est `nadia-prof` qui tranche, avant developpement.** Je le signale maintenant plutot qu'a l'audit :
c'est la quatrieme fois qu'un etat qu'elle a valide serait defait par une story qui ne le nomme pas.

### S6-10 - Le bouton d'action reste seul de sa couleur

**Avis : faisable en l'etat. Aucun ADR.**

Un point qu'il faut dire sans le maquiller : **`LActionNeDeclarePasMoinsQueLaSortieTest` restera
vert quoi que fasse cette story, parce qu'il ne lit aucune couleur.** C'est ecrit noir sur blanc
dans `ADR-0017`, section "Ce que cette regle ne couvre pas", et c'est pourquoi la reserve 4 a ete
trouvee par `nadia-prof` et non par la CI. Le troisieme critere de la story (les mesures
117 >= 109 >= 102 restent verifiees) est effectivement garde par ce test ; le premier critere - deux
couleurs de fond distinctes - ne l'est par rien aujourd'hui.

Est-il automatisable ? Oui, et honnetement : comparer deux valeurs de fond declarees par des regles
maison sur le bandeau et sur le bouton d'action est du meme ordre de difficulte que ce qu'`ADR-0017`
fait deja, et la machinerie de lecture des blocs maison existe. **Je ne le recommande pas dans cette
story**, et je dis pourquoi : "la couleur d'action est exclusive" est une propriete que je ne sais
pas encore ecrire sans arbitraire (deux nuances proches sont-elles "identiques" ? a quel ecart ?),
et une regle approximative sur ce sujet donnerait l'illusion d'une couverture sur precisement le
critere que `nadia-prof` juge en jouant. Egalite stricte de deux valeurs declarees, en revanche, se
verifie sans arbitraire : si le lot retient la solution "le bandeau porte une nuance distincte", ce
garde-fou-la tient en dix lignes et je le soutiens. Si le lot retient "le bouton grandit", la
propriete redevient une taille et le garde-fou existant la couvre deja.

### S6-11 - Un identifiant unique apres chaque echange htmx

**Avis : tranchee et livree dans ce commit. `ADR-0020`, avec son garde-fou dans le meme geste.**

Le defaut est reel, sa cause est trouvee, elle est ecrite dans l'ADR avec le chemin exact. Ce qui
reste de la story est zero : elle est close par ce commit. Le second critere de la story - le
garde-fou de `hx-target` continue de reperer la zone sans modification de sa logique - est verifie :
`LActionNeDeclarePasMoinsQueLaSortieTest` lit la cible dans les gabarits et ne suppose rien de
l'identifiant des fragments.

### S6-12 - Un contre-exemple qui ne peut jamais s'executer dans l'application servie

**Avis : tranchee et livree dans ce commit. `ADR-0021`, avec ses deux garde-fous dans le meme
geste.** La note de la story me laissait le choix technique entre deplacer les contre-exemples hors
du classpath scanne et resserrer le scan : j'ai retenu une troisieme voie, moins couteuse et plus
sure, motivee dans l'ADR.

### S6-13 - Un script qui verifie la lignee d'un worktree

**Avis : faisable en l'etat, aucun ADR - `ADR-0009` couvre deja la doctrine et ce script en est une
application directe, pas une extension. Ce qui m'est demande, c'est ce que "teste" veut dire pour un
script qui inspecte l'environnement plutot que le depot. Reponse precise ci-dessous.**

#### Le probleme, franchement

Un script de garde-fou classique (`verifier-prefixes.sh`, `ADR-0009`) est testable parce qu'il est
une **fonction** : des sujets de commit sur l'entree standard, un code de sortie en sortie. Le test
lui fournit quatorze entrees fabriquees et verifie quatorze verdicts. Rien ne depend du poste.

`verifier-lignee.sh` n'a pas cette forme telle que la story l'ecrit : il interroge `git` dans le
worktree courant. Son resultat depend de l'etat du depot ou il tourne. Un test qui l'executerait
tel quel ne verifierait qu'une chose - que le worktree du testeur est bien provisionne - et serait
**vert par accident sur un poste correct et rouge par accident sur un poste fautif**. C'est un
garde-fou decoratif : il passerait sa propre epreuve en echouant dans le monde, exactement la faute
mesuree en s05.

#### Ce que "teste" doit vouloir dire ici : couper le script en deux

**Le juge est une fonction pure ; la collecte est la seule partie qui touche le poste.**

1. Une fonction de jugement qui recoit ce que `git` a repondu - le nom de la branche courante et le
   verdict d'ancetralite - et rend un code de sortie plus un message. Elle ne lance aucune commande.
   Elle est testable exactement comme `verifier-prefixes.sh` : des entrees fabriquees, des verdicts
   attendus. **C'est la que vivent les deux cas exiges par le troisieme critere de la story**, plus
   ceux que la story ne nomme pas et qui sont les vrais pieges : un depot en `HEAD` detache, une
   branche dont le nom contient `round-two` sans en descendre (`round-two-bis`, qui doit etre
   **refusee** si elle n'est pas un descendant), une reference `round-two` absente du worktree
   (dixieme occurrence typique : le worktree n'a pas la branche), et un nom de branche vide.
2. Une enveloppe de trois lignes qui appelle `git rev-parse --abbrev-ref HEAD` et
   `git merge-base --is-ancestor round-two HEAD`, puis passe les resultats au juge.

Ce que le test couvre alors : **tout le verdict**. Ce qu'il ne couvre pas, et qui doit etre ecrit
dans le script comme `ADR-0009` l'exige : que les deux commandes `git` soient les bonnes. Cette
partie-la se prouve une fois, a la main, sur un worktree fautif reel - et c'est exactement ce que le
sprint a sous la main, puisque dix occurrences ont eu lieu.

**Sur l'interdiction de chemin absolu** (premier critere) : elle est plus facile a tenir ici qu'il
n'y parait, parce que le script n'a aucune raison d'imprimer un chemin. `git rev-parse
--abbrev-ref HEAD` rend un nom de branche, jamais un chemin. Le piege est ailleurs : un message
d'erreur qui citerait `git rev-parse --show-toplevel` pour "aider" l'agent. Il ne faut pas, et le
test de la fonction pure peut le verifier - un cas qui donne au juge une entree contenant un chemin
de poste et exige que le message rendu ne le recopie pas.

### S6-14 - Un script qui verifie la presence d'un JDK 21

**Avis : faisable sous condition, et la condition est que son premier critere soit corrige avant
developpement. Tel qu'il est ecrit, il demande de produire exactement ce qu'`ADR-0010` interdit, a
l'endroit meme ou son second critere demande de le coller.**

#### La contradiction, mot pour mot

- Premier critere : le script "imprime la sortie de `java -version`, la valeur de `JAVA_HOME`, et le
  nom exact du fichier de toolchains trouve sous le depot Maven local - sans jamais imprimer de
  chemin absolu **destine a etre commite**".
- Second critere : cette sortie "est collee telle quelle" dans `rapport-qualite.md` ou
  `confirmation-archunit.md`, deux fichiers **commites**.

`JAVA_HOME`, quand elle est renseignee, **est** un chemin absolu de poste. Le "nom exact du fichier
de toolchains trouve sous le depot Maven local" suppose de nommer ce depot, qui est sous le
repertoire personnel de l'utilisatrice. Coller cette sortie telle quelle dans un fichier commite
fait rougir `verifier-chemins-de-poste.sh` (`ADR-0010`) - et si par malheur elle passait, elle
publierait sur GitHub un nom de compte et une arborescence de poste. Les deux criteres ne peuvent
pas etre tenus ensemble.

#### La sortie que je recommande : deux sorties, pas une

Le script produit **deux** blocs, et la story doit dire lequel se colle :

1. Un **diagnostic**, destine a l'agent qui debloque son poste, affiche sur la sortie d'erreur. Il
   peut tout dire, y compris les chemins : il ne va nulle part.
2. Un **constat citable**, sur la sortie standard, ecrit pour etre commite : la version rendue par
   `java -version`, celle du JDK effectivement retenu pour la compilation, si `JAVA_HOME` est
   **renseignee ou non** (le fait, jamais la valeur), et si un fichier de toolchains est **lu ou
   non** par Maven - le fait, jamais l'emplacement. Exemple de forme, et c'est un exemple, pas une
   maquette a copier : `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME :
   renseignee | toolchains lu par Maven : non (fichier present sous un nom non lu)`.

C'est ce second bloc qui repond au besoin reel de la story. Le besoin n'est pas de connaitre le
chemin du JDK - c'est de **cesser de conclure a tort qu'il n'y en a pas**. La derniere ligne de
l'exemple ci-dessus est celle qui aurait epargne quatre agents en s05 : elle dit que le fichier
existe et que Maven ne le lit pas, ce qui est le fait exact, et elle le dit sans nommer un seul
repertoire.

#### Ce que "teste" veut dire ici, et c'est une reponse differente de S6-13

Le verdict de S6-13 est booleen, donc entierement testable par une fonction pure. Celui-ci ne l'est
pas : "quel JDK est present sur ce poste" est une question dont la reponse **est** l'environnement.
La partie testable n'est donc pas le constat, c'est sa **forme** :

1. **La fonction de formatage est pure et testee** : elle recoit une sortie de `java -version`
   simulee, une valeur de `JAVA_HOME` simulee (renseignee, vide, absente), un resultat de recherche
   de toolchains simule, et rend la ligne citable. Les cas qui comptent : `JAVA_HOME` valant un
   chemin de poste doit produire `renseignee` et **jamais le chemin** ; un fichier de toolchains
   trouve sous un nom que Maven ne lit pas doit produire `non`, avec la mention du fait ; un
   `java -version` de JRE 8 doit etre rapporte comme tel sans faire echouer le script, puisque
   c'est l'etat normal de ce poste.
2. **Le test le plus important est un test negatif, et il est facile a ecrire** : la ligne citable
   rendue par la fonction, pour n'importe quelle entree, ne contient **ni `/`, ni `\`, ni `:` suivi
   d'une lettre de lecteur**. Une assertion, et elle interdit reellement quelque chose - c'est
   `ADR-0010` applique a la sortie d'un outil au lieu d'un fichier.
3. Ce que le test ne couvre pas, a ecrire dans le script : que la recherche de toolchains regarde au
   bon endroit. Comme pour S6-13, cela se prouve une fois a la main sur le poste, et le poste
   d'aujourd'hui est le cas fautif - le fichier existe sous un nom que Maven ne lit pas.

**Un mot sur la mesure qui motive cette story, puisque je viens de la refaire.** J'ai verifie avant
d'ecrire une ligne de cet avis : `JAVA_HOME` n'est renseignee ni dans l'un ni dans l'autre des deux
interpreteurs disponibles, le `java` du `PATH` est un JRE 8 qui ne compile pas, le fichier de
toolchains du depot Maven local porte bien un nom que Maven ne lit pas, et il declare trois JDK dont
un 21. Un JDK 21.0.10 est installe et fonctionne. **Les quatre agents de s05 avaient tort de
conclure a l'absence d'un JDK 21, et le script de cette story est le bon remede** - a condition que
sa sortie citable ne dise pas ou il est.

---

## Criteres qui ne verifient pas ce qu'ils croient verifier - action de `camille-po`

C'est la sortie de cet avis qui demande une action d'elle, pas de moi.

| Critere | Ce qu'il dit | Ce qu'il mesure reellement | Action |
|---|---|---|---|
| S6-3, 4e | "une partie demarre sans erreur sur ce paquet" | vrai des aujourd'hui, et **muet** sur le fait que 6 items pour 10 manches terminent la partie avant l'heure | ajouter un critere qui compare le nombre d'items a `nombreDeManches`, ou fixer `nombreDeManches` a 6 |
| S6-3 (absent) | rien sur l'indice | `ADR-0016` rend le paquet **rouge** sans six indices ecrits ; la story ne les commande pas | ajouter le critere, et prevoir la relecture pedagogique des six textes |
| S6-6, 3e | "ce champ existe et permet de determiner automatiquement si l'item est regulier" | rien : le chargeur ignore silencieusement toute cle qu'aucune fabrique ne lit | garder le critere, mais le lot doit livrer la lecture du champ **et** son garde-fou, pas seulement le YAML |
| S6-7 (absent) | rien sur le garde-fou | `DETTE_DE_BALAYAGE` est pleine : le jeu fautif est obligatoire, pas optionnel | nommer le garde-fou et son jeu fautif dans la story, comme S5-5 l'avait fait |
| S6-9, 4e et absents | ~~"`LActionNeDeclarePasMoinsQueLaSortieTest` reste vert", rien sur `sortie-pleine`~~ | **sans objet depuis l'arbitrage du 2026-08-07** : `sortie-pleine` et `.retour-accueil` sont conserves, `camille-po` reecrit les criteres | aucune de mon fait ; les garde-fous sont livres (`ADR-0022`) |
| S6-14, 1er et 2e | une sortie sans chemin absolu, collee dans un fichier commite | `JAVA_HOME` **est** un chemin de poste ; les deux criteres se contredisent | separer diagnostic (sortie d'erreur, libre) et constat citable (sortie standard, sans chemin) |

## Ce que ce commit contient

- `ADR-0020` (identifiant duplique apres un echange htmx, et lecture d'une manche sur une partie
  terminee) et son garde-fou `ToutEcranEchangeEstUnDocumentJouableTest`.
- `ADR-0021` (un contre-exemple de garde-fou n'est jamais un bean de l'application) et ses deux
  garde-fous, `ScanDeComposantsTest` et `AucunContreExempleNEstUnBeanDeLApplicationTest`.
- Les entrees correspondantes dans `ADR-index.md`.
- Ce fichier.

## Verification avant planning

`mvn -B clean verify`, sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande (le `java`
du `PATH` de ce poste est un JRE 8 qui ne compile pas) : **BUILD SUCCESS**, les cinq modules du pom
agregateur en SUCCESS, 56,7 s.

**254 tests, 0 echec, 0 erreur, 0 ignore.** Methode de ce comptage, parce que celui de s05 a du etre
corrige deux fois faute de l'avoir ecrite : somme de l'attribut `tests` de la balise racine
`<testsuite>` des **47 rapports XML** produits par la commande - 44 sous `target/surefire-reports/`
et 3 sous `target/failsafe-reports/`, soit 251 tests unitaires et 3 tests d'integration. Je ne compte
pas les rapports texte : c'est la source qui avait fait divergier 29 et 25 en s05, parce que deux
moteurs JUnit tournent et que le rapport texte n'en garde qu'un. Le comptage est fait apres un
`clean` : des rapports laisses par une execution mutee anterieure feraient un total juste avec des
echecs faux, et je m'y suis pris une fois pendant ce sprint. Repartition : `adapter-web` 114,
`bootstrap` 71 + 3, `domain` 38, `adapter-content` 21, `application` 7.

Progression sur le sprint : **238 a la cloture de s05 -> 250 apres ADR-0020 et ADR-0021 -> 254 apres
ADR-0022**, les quatre derniers etant `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`.

**28 `ArchRule` declarees, inchange depuis `ADR-0021`.** Methode : expression
`ArchRule <identifiant> =` cherchee sous `src/test/java` des **cinq** modules declares dans
`<modules>` du pom agregateur - la meme derivation que
`LesGardeFousSontMisALEpreuveTest.toutesLesRegles` applique, et non une liste de fichiers ecrite a la
main. Exclu explicitement : le faux depot de
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/`, qui contient des
`ArchRule` jamais compilees ni executees - c'est lui qui gonflait le compte de s05. Repartition : 27
dans `bootstrap`, 1 dans `adapter-web`. Progression depuis la cloture de s05 : **27 -> 28**, la
nouvelle etant `ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`
(`ADR-0021`). Ni `ADR-0020` ni `ADR-0022` n'ajoutent d'`ArchRule`, et les deux ADR disent pourquoi :
ArchUnit ne rend pas une vue et ne lit pas une feuille de style.

Les preuves de morsure des quatre garde-fous neufs et du garde-fou amende, mutations et resultats
mesures, sont dans `ADR-0020`, `ADR-0021` et `ADR-0022`. Les trois mutations d'`ADR-0020` et celle
d'`ADR-0021` ont chacune ete relancee **avec le garde-fou neuf retire de l'arbre** : `BUILD SUCCESS` a
chaque fois, ce qui mesure l'angle mort que ces garde-fous ferment. Les trois mutations d'`ADR-0022`
n'admettent pas cette mesure et l'ADR le dit : deux d'entre elles portent sur un mecanisme qui
n'existait pas avant ce commit, il n'y a donc pas d'etat anterieur auquel les comparer.
