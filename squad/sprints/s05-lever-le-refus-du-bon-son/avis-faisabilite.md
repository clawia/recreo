# [Sprint s05] Avis de faisabilite - theo-archi

- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Commit lu** : `301ae56`, `HEAD` de `round-two`.
- **Backlog lu** : `docs/backlog/backlog.md`, section "Sprint 5 - Lever le refus de "Le bon son" et
  completer les homophones CM1", stories S5-1 a S5-8. **Ce sont leurs criteres d'acceptation qui
  font foi ici.** Lu deux fois : a l'ouverture, puis dans l'arbre de travail au moment de conclure,
  ou `camille-po` avait ajoute S5-8, `DEC-0010`, `DEC-0011` et revise S5-1 et S5-6. Mes avis
  ci-dessous portent sur cette seconde lecture ; si elle revise encore apres moi, c'est son texte
  qui fait foi, pas ce fichier.
- **Pieces de reference** : `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`,
  `FICHE-bandit-manchot-homophones-cm1.md`, `FICHE-bandit-manchot-additions-cp.md` (bloc 2),
  `DEC-0009`, `DEC-0005`, `DEC-0008`, `ADR-0012`, `ADR-0013`, `ADR-0014`, `ADR-0015`.
- **Code relu** : `QuestionContentSourceFactory`, `AdditionsBorneesContentSourceFactory`,
  `YamlContentPackLoader`, `Question`, `ListContentSource`, `BanditManchotSession`,
  `PartieService`, `PartieController`, `GestionnaireDesErreurs`, `ContenuConfiguration`,
  `HasardConfiguration`, `CatalogueChargeableIT`, `PaquetsFrancaisS04Test`,
  `TailleDesTextesEtBoutonsTest`, `HabillageDesEcransTest`, `RessourcesTiercesTest`,
  `LesGardeFousSontMisALEpreuveTest`, les quatre gabarits, `recreo.css`, `catalogue.yaml` et les
  trois paquets de contenu.
- **Nom de ce dossier** : `s05-lever-le-refus-du-bon-son`, choisi faute de slug existant au moment
  ou j'ecris. `sofia-sm` anime le planning apres moi et **peut le renommer** ; ce fichier suit, il
  n'a aucune autorite sur le nom du sprint.

## Synthese

| Story | Avis | ADR requis | Testable par |
|---|---|---|---|
| S5-1 | **Deja livree aux trois quarts** (`fb7f4df`). Le second critere : je **confirme** le raisonnement de `nadia-prof`, la fenetre d'exposition est nulle et non petite - mais pour une raison de plus qu'elle ne dit, et le critere ne decrit pas le comportement du code | aucun | automate pour trois criteres sur quatre ; le second ne peut etre ni ferme ni teste aujourd'hui, voir plus bas |
| S5-2 | **Pas du contenu seul, et deux de ses trois criteres ne tiennent pas tels qu'ecrits.** Le premier est **deja satisfait a la lettre sans rien faire** (mesure) ; le deuxieme demande un champ qui n'existe pas ; le troisieme porte sur un texte qui n'existe plus dans le depot | aucun pour le contenu ; **un ADR le jour ou le "retour qui explique" sera decrit par une story** | automate pour le premier une fois reformule ; porte pedagogique pour le fond |
| S5-3 | Faisable, contenu seul, **plus une mise a jour de test** (`PaquetsFrancaisS04Test` epingle les six sons entre crochets) | aucun | automate (deux criteres sur trois), porte pedagogique pour la marque visuelle |
| S5-4 | Faisable en contenu, **mais son critere n'est pas verifiable par automate, et il le devient encore moins une fois S5-2 livree** - demonstration chiffree plus bas | aucun | porte pedagogique seule |
| S5-5 | **Tranchee et livree dans ce commit** : `ADR-0016`, correction de `QuestionContentSourceFactory`, garde-fou derive du repertoire de contenu. Ce qui reste est du contenu (ecrire les indices) | **ADR-0016, ecrit avec sa regle dans le meme geste** | automate (le garde-fou), porte pedagogique pour le texte des indices |
| S5-6 | Faisable, contenu seul **pour le code** - deuxieme demonstration du decouplage. Mais **une mise a jour de test est obligatoire**, et le couple `la/la/l'a` (un triplet) casse une propriete que `nadia-prof` porte au credit du paquet | aucun | automate pour trois criteres sur quatre ; le critere central (regarder ailleurs que juste avant le trou) est porte pedagogique seule |
| S5-7 | Faisable, contenu seul. Les deux criteres sont automatisables | aucun | automate |
| S5-8 | Faisable en l'etat, deux occurrences mesurees. La story exige elle-meme le test qui manquait en s03 ; il reste a dire **comment** l'ecrire pour qu'il ne soit ni decoratif ni rouge en permanence | aucun | automate, a condition de balayer les noeuds de texte rendus et non la source brute |

**Aucune story de ce sprint n'est infaisable.** Une seule demandait un ADR, elle m'etait routee, et
elle est traitee. Le reste de mon avis porte sur des **criteres qui ne verifient pas ce qu'ils
croient verifier** - c'est la meme categorie de sortie qu'en s03, et j'en trouve quatre cette fois.

---

## S5-1 - Retirer "Le bon son" du catalogue

**Avis : trois criteres sur quatre sont deja tenus par `fb7f4df`, livre avant ce planning. Le
second doit rester ouvert, et je confirme le motif technique de `nadia-prof` - en le completant.**

### Ce qui est deja fait, verifie et non suppose

`lea-dev` a retire l'entree du catalogue (`fb7f4df`). Mesure sur `HEAD` :

- **1er critere** (le jeu ne figure plus dans la liste) : `catalogue.yaml` ne contient plus que
  `bandit-manchot-additions-cp` et `bandit-manchot-homophones-cm1`. La liste de l'accueil vient de
  `partie.jeuxDisponibles()`, donc du catalogue. Tenu.
- **3e critere** (les deux autres jeux inchanges) : aucune ligne de `domain/` n'a ete touchee par ce
  commit ; `CatalogueChargeableIT` demarre une partie de chaque jeu du catalogue a chaque
  construction. Tenu.
- **4e critere** (le paquet et la fiche restent dans le depot) : `cp-le-bon-son.yaml` est en place,
  et `PaquetsFrancaisS04Test` continue de le couvrir en le chargeant directement par
  `YamlContentPackLoader.chargerPaquet`, sans passer par le catalogue. Tenu, et bien fait : la
  visibilite du chargeur a ete elargie d'un cran plutot que d'affaiblir une assertion.

### Le second critere : le raisonnement de `nadia-prof` est juste, et il l'est plus qu'elle ne le dit

Son motif est : le catalogue est charge au demarrage, les sessions vivent en memoire, donc le
redemarrage qui applique un retrait detruit toutes les sessions ; la fenetre d'exposition est nulle,
pas seulement petite. **Verifie, maillon par maillon, dans le code et pas de memoire :**

1. **Le catalogue est fige au demarrage.** `ContenuConfiguration.contentCatalogPort()` appelle
   `new YamlContentPackLoader().games()` **une fois**, a la creation du bean, et rend
   `() -> jeux` : une liste capturee. Aucun chemin de rechargement n'existe dans le depot.
2. **Les sessions vivent dans la memoire du conteneur embarque.** `PartieController` range l'etat de
   partie dans la `HttpSession` (`ADR-0008`). Aucun fichier de configuration d'application n'existe
   dans `bootstrap/src/main/resources` - il n'y a que l'index d'auto-configuration - donc
   `server.servlet.session.persistent` reste a son defaut, `false`. Aucune dependance de session
   externe n'est declaree, et `adapter-persistence` n'existe pas.
3. **Donc** appliquer le retrait suppose redemarrer le processus, et ce redemarrage detruit toutes
   les sessions. Aucune session ne peut survivre a un changement de catalogue. **La fenetre est
   vide.** Je confirme.

**Deux choses que le motif ne dit pas, et qui comptent pour la suite :**

- **Le chemin "URL directe" est deja ferme, et il l'etait avant `DEC-0009`.** `POST /parties/{id}`
  passe par `jeuOuEchec`, `POST /parties/{id}/coups` par `PartieService.trouver` : les deux levent
  `NoSuchElementException` sur un jeu absent du catalogue, et `GestionnaireDesErreurs` rend
  l'ecran d'erreur (`ADR-0011`). Un enfant ne peut donc pas **jouer** une manche d'un jeu retire,
  meme avec une session ouverte.
  La note ajoutee a la story dit qu'un enfant "pourrait terminer une manche sur le contenu refuse" :
  **il ne le peut pas**, la manche ne se termine jamais, elle tombe sur l'ecran d'erreur des la
  premiere reponse. Je le signale parce que la note sert de motif a ne pas fermer le critere, et
  qu'un motif juste vaut mieux qu'un motif approchant - la conclusion, elle, ne change pas.
- **Ce qui resterait expose n'est pas ce que le critere decrit.** Deux chemins rendent une manche
  sans consulter le catalogue : `GET /` en reprise, et `GET /parties/{id}/manche`. Ils **affichent**
  une question du jeu retire ; la premiere reponse de l'enfant tombe ensuite sur l'ecran d'erreur.
  Or le critere dit "il est renvoye vers l'accueil". **Ce n'est pas le comportement du code, et ce
  ne serait pas non plus le comportement obtenu en ajoutant une simple verification** : renvoyer
  vers l'accueil demande de vider la session, de rendre la zone d'accueil et de choisir ce que
  l'enfant lit a ce moment-la - probablement le message de partie perdue de S1-4, mais c'est un
  choix de produit. Le critere, s'il se rouvre, devra dire lequel.

**Ma recommandation : ne pas fermer ce critere, et le dire dans la story plutot que de le laisser
en suspens.** Un critere qu'on renonce a tenir doit etre ecrit comme tel, sinon le sprint suivant
croit qu'il est tenu (`ADR-0013`, fait 1).

### Ce qui rendra ce raisonnement faux, pour que le critere se rouvre au bon moment

Par ordre de cout croissant pour celui qui le declencherait - le premier est une ligne :

1. **`server.servlet.session.persistent=true`**, ou tout `ServletWebServerFactoryCustomizer` qui
   fait serialiser les sessions Tomcat au redemarrage. Une ligne, et la fenetre s'ouvre en grand :
   les sessions survivent exactement au redemarrage qui applique le retrait.
2. **Spring Session** (Redis, JDBC) ajoute pour n'importe quelle autre raison.
3. **`adapter-persistence`** (`ADR-0001`, `DEC-0003`), le jour ou une story demande de conserver
   une donnee entre deux sessions. C'est le declencheur que `nadia-prof` cite, et c'est le troisieme
   dans l'ordre de vraisemblance, pas le premier.
4. **Un catalogue rechargeable** : si `ContentCatalogPort` cesse de capturer une liste au demarrage
   pour relire le fichier, ou si une story ajoute un moyen de retirer un jeu sans redemarrer, le
   retrait s'applique pendant que des sessions vivent.
5. **Plusieurs instances derriere un repartiteur**, redemarrees en roulement, avec un magasin de
   sessions partage - ce qui suppose 1, 2 ou 3.

**Ce que je recommande pour que la reouverture n'ait pas lieu au hasard** : un **garde-fou de
premisse**, c'est-a-dire un test qui echoue le jour ou l'une de ces conditions devient vraie, avec
un message qui dit "le second critere de S5-1 se rouvre". Il derive de deux sources deja lues par
d'autres garde-fous - la liste de modules du pom agregateur, et les fichiers de configuration de
`bootstrap` - et il tient en une trentaine de lignes. Je ne l'ecris pas dans ce commit et je dis
pourquoi : il n'a de sens qu'attache a un critere de story que je ne redige pas, et son rouge est
volontairement un **faux rouge** le jour ou `adapter-persistence` naitra legitimement. C'est une
convention d'equipe a accepter avant d'etre codee, pas une decision d'architecte solitaire. Si le
planning la retient, elle tient dans S5-1 et je l'ecris.

---

## S5-2 - Le jeu demande d'entendre un son, pas de reperer les memes lettres

**Avis : la story est legitime et son fond est le bon, mais elle n'est pas du contenu seul, et deux
de ses trois criteres ne verifient pas ce qu'ils croient verifier. Aucun ADR pour le contenu.**

### 1er critere - il est deja satisfait a la lettre, sans rien changer. Mesure

Le critere dit : "quand on verifie pour chacun si les lettres de la reponse attendue figurent
litteralement dans le mot ecrit, alors une majorite des items n'est pas resoluble par cette seule
recherche visuelle - contre 1 item sur 24 avant correction".

La reponse attendue, dans le paquet, s'ecrit `[an]`, `[ou]`, `[ch]`. **Les lettres `[an]` ne
figurent litteralement dans aucun des 24 mots.** Un automate qui applique le critere au pied de la
lettre trouve donc **24 items sur 24 non resolubles**, aujourd'hui, avant toute correction. Le
critere est vert sur le paquet refuse.

En normalisant - crochets retires, minuscules, accents rabattus - je retrouve exactement le chiffre
de `nadia-prof` : **23 items sur 24 resolubles par appariement visuel, le seul qui resiste est
`DENT`**. C'est ce chiffre-la que la story vise.

**Consequence pour l'ecriture du critere** : il doit nommer la normalisation (comparaison sur la
reponse **telle qu'elle est montree a l'enfant apres S5-3**, sans crochets, insensible a la casse
et aux accents), sinon il mesure la presence des crochets et rien d'autre. Ainsi ecrit, il est
parfaitement automatisable et je recommande qu'il le soit. Il **depend de S5-3**, qui retire les
crochets : le planning doit ordonner les deux ou les confier au meme lot.

### 2e critere - il demande un champ qui n'existe pas, ce n'est pas du contenu

"Un item accompagne d'un retour qui explique la variation" : aujourd'hui, ce qu'un enfant lit apres
une reponse fausse est produit par `Verdict`, qui porte exactement deux choses, `juste` et
`bonneReponse`, rendues par `fragments/retour.html` en "Non, c'est [ou].". **Il n'existe aucun
champ de contenu pour un texte d'explication, ni aucun endroit ou l'afficher.**

C'est le meme trou que S5-5, sur un autre texte : une chose que le contenu voudrait dire et que la
chaine ne sait pas transporter. Je ne l'ouvre pas dans `ADR-0016`, et je dis pourquoi plutot que
d'ajouter un champ par anticipation (`ADR-0005`) : **le moment ou ce texte s'affiche est une
decision pedagogique**, pas une decision d'architecture. Toujours ? Seulement sur une reponse
fausse ? Seulement sur les items a variation ? Tant que cette question n'a pas de reponse, un champ
`explication` serait une abstraction ecrite pour un usage imagine.

**Ce que je recommande** : detacher ce critere en une story a part, dont le besoin s'ecrit du cote
de `nadia-prof` ("l'enfant qui se trompe sur `DENT` doit lire pourquoi") et dont la conception
technique reviendra ici. Elle coutera la meme chose que S5-5 - un champ, une lecture dans la
fabrique, un affichage, un garde-fou derive - et elle pourra reutiliser
`ChaquePaquetPorteUnIndiceTest` en changeant son critere. Laisser ce critere dans S5-2 revient a
demander a une story de contenu de faire une modification de modele sans le dire.

### 3e critere - il porte sur un texte qui n'existe plus dans le depot

"Quand la consigne s'affiche, alors elle ne contient aucune reference a un son entendu". La consigne
n'est pas un champ du paquet : c'est un champ de l'**entree de catalogue**, et S5-1 a supprime cette
entree. Mesure : la chaine `entends-tu` n'apparait plus nulle part dans le depot.

Il n'y a donc rien a corriger tant que le jeu n'est pas au catalogue, et **la consigne corrigee ne
peut etre ecrite que dans le commit qui l'y remet**. Ce n'est pas un defaut de la story, c'est une
consequence de `DEC-0009` que personne n'avait encore ecrite.

### Ce qui conditionne techniquement la remise au catalogue - reponse a la question posee

Techniquement, la remise tient a une seule chose : **reecrire l'entree dans `catalogue.yaml`**
(`id`, `titre`, `consigne`, `mecanique`, `paquet`, `nombreDeManches`). Aucune autre piece n'a ete
retiree. Trois garanties s'appliqueront automatiquement a cette entree, sans rien ajouter :

- `CatalogueChargeableIT` demarre une partie du jeu et echoue si le paquet ou la mecanique
  n'existent pas ;
- la meme integration verifie `nombreDeManches >= mecanique.longueurMinimale()` (4), c'est-a-dire
  que la partie est assez longue pour que l'aide de S5-5 soit atteignable (`ADR-0012`, S3-2) ;
- `ChaquePaquetPorteUnIndiceTest` (`ADR-0016`) exige que `cp-le-bon-son` porte ses indices des sa
  sortie de la dette.

**Mais il y a un enchainement a trancher, et il n'est pas technique.** `DEC-0009` dit que le jeu
revient "jusqu'a ce qu'un increment corrige repasse devant `nadia-prof` et obtienne un Done
pedagogique". Or `nadia-prof` audite **en jouant**, et un jeu absent du catalogue n'est pas jouable.
Lu au pied de la lettre, le retrait interdit l'audit qui doit le lever. Trois sorties possibles, et
c'est `sofia-sm` avec `camille-po` qui choisissent, pas moi :

1. le lot de correction remet l'entree au catalogue **sur sa branche**, `nadia-prof` joue le jar
   construit depuis cette branche, et la fusion sur `round-two` n'a lieu qu'apres le Done. C'est
   celle que je recommande : elle respecte `DEC-0009` a la lettre sur `round-two`, elle ne demande
   aucun mecanisme nouveau, et elle est deja la facon dont les lots circulent ;
2. la remise au catalogue se fait avant l'audit sur `round-two`, ce qui revient a amender
   `DEC-0009` ;
3. un mecanisme d'activation par profil, pour rendre un jeu jouable sans qu'il soit dans la liste
   servie. **Je le deconseille** : c'est un deuxieme etat du catalogue, donc un deuxieme chemin de
   code a garder, pour un besoin qu'une branche resout gratuitement.

---

## S5-3 - Les sons proposes sans notation d'adulte

**Avis : faisable, contenu seul, plus une mise a jour de test obligatoire. Aucun ADR.**

- **1er critere** (aucun `[` ni `]` dans une proposition affichee) : automatisable, et je recommande
  que le garde-fou **derive de tous les paquets** plutot que du seul `cp-le-bon-son` - c'est le meme
  balayage que celui d'`ADR-0016`, avec un autre critere, et il empeche la notation d'adulte de
  revenir par un paquet neuf. Cout : quelques lignes dans le meme test.
- **2e critere** (chaque son s'ecrit avec exactement les lettres qui le composent) : automatisable
  sous la forme "la reponse et les distracteurs ne contiennent que des lettres".
- **3e critere** : la story le range elle-meme du cote de la seule porte pedagogique, et elle a
  raison - la marque visuelle "ceci est un son" est un choix de `nadia-prof` et d'`ines-design`.

**Le point qu'il faut voir avant de commencer** : `PaquetsFrancaisS04Test` epingle les six sons
**avec leurs crochets** (`SONS_ATTENDUS = Set.of("[an]", "[on]", ...)`) et verifie que chaque item
n'annonce qu'un son de cette liste. Retirer les crochets rend ce test rouge. C'est une mise a jour
de test, pas un affaiblissement : la liste des six sons reste, seule son ecriture change. A dire au
lot qui prend la story, pour qu'il ne croie pas avoir casse autre chose.

---

## S5-4 - Chaque item n'admet qu'une seule reponse vraie

**Avis : faisable en contenu, mais son critere central n'est pas verifiable par automate - et
S5-2 le rend encore moins verifiable. Porte pedagogique seule. Aucun ADR.**

Le critere dit : "quand on releve lesquels [des six sons] sont **reellement presents** dans le mot
affiche, alors un seul son du jeu est present, jamais deux". Un automate ne sait relever qu'une
chose : les **lettres** presentes dans le mot. Or "reellement present" veut dire "present a
l'oreille". Mesure sur le paquet actuel, en comptant les six sons par leurs lettres :

| Mot | Sons du jeu presents **en lettres** | Ce que dit l'oreille |
|---|---|---|
| `MOUCHE` | `ou`, `ch` | deux, `nadia-prof` a raison |
| `MOUTON` | `ou`, `on` | deux, `nadia-prof` a raison |
| `BALANCOIRE` | `an`, `oi` | deux, `nadia-prof` a raison |
| `DENT` | **aucun** | **un** : `[an]`, ecrit `en` |

Les trois items que `nadia-prof` nomme sont bien les trois que la lettre trouve. Mais la derniere
ligne montre le probleme : sur `DENT`, l'automate compte zero son la ou l'enfant en entend un. **La
mesure par les lettres n'est pas le critere, c'est un substitut qui coincide tant que les graphemes
suivent les phonemes.**

Et c'est la que les deux stories se genent : **S5-2 demande explicitement que la majorite des items
cesse de se resoudre par les lettres.** Plus S5-2 reussit, moins le substitut de S5-4 mesure quelque
chose. Livrees ensemble, la premiere detruit l'automate de la seconde.

**Ma conclusion, sans detour : S5-4 est verifiable par la seule porte pedagogique.** La story le
pressent - elle range la solution retenue du cote de `nadia-prof` - mais elle presente son premier
critere comme mesurable ("un seul son du jeu est present"), ce qu'il n'est pas.

**Une option existe et je la nomme pour qu'elle soit ecartee en connaissance de cause** : faire
declarer par le paquet, item par item, les sons qu'il contient, et verifier que la liste n'en compte
qu'un. Cela s'automatise en une heure. Ce que cela garantit est **la coherence d'une declaration
avec elle-meme**, pas la verite : c'est le meme auteur qui ecrit le mot et la liste des sons, et
s'il se trompe sur l'un il se trompera sur l'autre. Le garde-fou attraperait une regression future
(un item ajoute sans mettre a jour sa declaration), pas la faute d'aujourd'hui. Je ne le recommande
pas tant que `nadia-prof` relit chaque item neuf ; je le recommanderais si le paquet grossissait au
point qu'elle ne les relise plus un par un.

---

## S5-5 - Une aide dans "Le bon son", comme dans les autres jeux

**Avis : tranchee et livree dans ce commit. `ADR-0016`, ecrit avec sa regle dans le meme geste.**
Ce qui reste de la story est du contenu : ecrire les indices, et les faire relire.

### Ou doit vivre l'indice - la question qui m'etait posee

L'indice vit **dans le contenu, attache a l'item**, et la mecanique ne decide que du moment ou il
s'affiche. Ce n'est pas nouveau : c'est `DEC-0005`, et `Question` porte un champ `indice` depuis
S1-5. Le defaut n'etait donc pas une abstraction manquante - c'est plus interessant que cela :
**l'abstraction existait, et un seul de ses deux appelants la remplissait.**
`QuestionContentSourceFactory` appelait le constructeur a quatre arguments, celui qui laisse
l'indice vide, et rien dans le depot ne confrontait ce que la mecanique **promet** a ce que le
contenu **sait donner**. Les deux cotes etaient testes separement ; le trou etait exactement entre
les deux.

Correction : la fabrique lit la cle `indice` de l'item. Trois lignes.

### Rendre la promesse verifiable - la partie qui compte

Trois lignes de correction ne repondent pas a la question que le sujet pose : rien n'empeche un
troisieme type de contenu d'arriver demain avec le meme trou. Le garde-fou d'`ADR-0016`
(`ChaquePaquetPorteUnIndiceTest`) applique la doctrine d'`ADR-0013` a une population que le depot
n'avait jamais derivee, le contenu :

- **perimetre derive du repertoire de contenu**, jamais d'une liste de paquets - et deliberement pas
  du **catalogue** : `DEC-0009` venait justement d'en sortir le paquet fautif, un garde-fou derive
  du catalogue l'aurait perdu de vue le jour meme de son refus ;
- **derivation totale** : chaque paquet est charge par le chargeur reel, ses items sont tires par la
  chaine que l'enfant traverse, et un item sans indice est rouge. Un type de contenu qui perd
  l'indice en route est rouge des son premier paquet, quoi que dise son YAML ;
- **preuve de morsure sur un objet neuf** : un paquet que personne n'a inscrit nulle part, et le
  meme paquet muni de ses indices, qui doit passer.

**La mesure qui compte, et l'objection qu'elle ferme.** Les deux seuls paquets `type: question` du
depot sont dans la dette de contenu : la regle ne prouverait donc rien sur le type qu'elle vient de
corriger si elle ne tenait qu'aux paquets de production. Le paquet fautif **corrige** est de
`type: question` et hors dette par construction : j'ai rendu a la fabrique son constructeur a quatre
arguments, et le test est redevenu rouge. La regle mord sur le code aujourd'hui, et elle mordra sur
le contenu quand la dette sera fermee.

### Ce que la story doit savoir

- La story dit "un item du paquet portant un indice" ; `ADR-0016` exige **chaque item**. La promesse
  se tient manche par manche : l'enfant qui echoue trois fois recoit la manche suivante, pas une
  manche choisie. C'est plus exigeant que le critere ecrit, et cela ne le contredit pas.
- Les deux criteres de la story sont desormais atteignables par du contenu seul : ecrire les indices
  de `cp-le-bon-son` (24) et de `cm1-homophones` (10, 20 apres S5-6). La dette plafonnee les force -
  elle ne peut pas grandir, et un paquet neuf sans indice est rouge.
- **Le texte des indices est pedagogique et n'est pas de mon ressort.** `nadia-prof` a deja donne le
  modele qu'elle attend pour le CM1 : "Remplace par *avait* pour t'aider.".

---

## S5-6 - Les cinq couples d'homophones manquants

**Avis : faisable, et c'est bien du contenu seul du point de vue du code - deuxieme demonstration
du decouplage apres s04. Mais une mise a jour de test est obligatoire, et un des cinq couples casse
une propriete que `nadia-prof` porte au credit du paquet. Aucun ADR.**

### Le decouplage tient, verifie et non suppose

Ajouter dix items a `cm1-homophones.yaml` ne demande **aucune ligne** de `domain/mechanic`,
`domain/mechanics`, `application` ou `adapter-web`. Le paquet est enumere, `ListContentSource` tire
dans son vivier, la mecanique ne connait ni les homophones ni le francais. `nombreDeManches` reste
a 10 dans le catalogue, au-dessus du minimum de 4 impose par l'aide (`ADR-0012`). C'est le second
test du decouplage et il passe.

### Ce qui n'est pas du contenu : le test qui epingle les cinq couples actuels

`PaquetsFrancaisS04Test.homophonesCm1NePresenteQueLesCinqCouplesAttendusAvecUnUniqueDistracteurApparie`
porte trois listes ecrites a la main - `COUPLES_HOMOPHONES_AUTORISES`, `PAIRE_DE`, et un
`containsExactlyInAnyOrder` sur les cinq couples. **Les dix nouveaux items le rendent rouge**, et
c'est le comportement voulu : ce test est un critere, pas un perimetre, et son mode de defaillance
est un rouge. Le lot qui prend la story met les trois listes a jour dans le meme commit.

### Le couple `la/la/l'a` est un triplet, et il casse une propriete que la fiche porte au credit du paquet

C'est un **triplet**, pas un couple. Le test actuel exige `distracteurs.hasSize(1)` et que l'unique
distracteur soit l'autre mot du couple - et `nadia-prof` en fait un point fort explicite : "les
distracteurs sont plausibles par construction [...] il n'y a aucun distracteur absurde possible, et
aucune devinette". Deux sorties, et **le choix est pedagogique, pas technique** :

1. l'item porte **deux** distracteurs, donc trois propositions. La mecanique le supporte sans
   modification (`melanger` traite n propositions, et le jeu de maths en affiche deja trois), mais
   ce seul item se joue alors avec une chance sur trois quand les autres se jouent a une sur deux ;
2. le triplet se travaille par **deux items pairwise** (`la`/`la`, puis `la`/`l'a`), ce qui conserve
   la propriete du paquet et ajoute un item.

Je les expose, je ne tranche pas : c'est `nadia-prof` qui dit ce qu'un CM1 doit distinguer. Ce que
je dis techniquement : les deux tiennent, la seconde ne coute aucun changement de test au-dela des
listes, la premiere en coute un de plus (`hasSize(1)` devient une borne).

Le critere ajoute par `camille-po` - chaque couple represente **dans les deux sens** - se heurte au
meme endroit : une famille de trois membres n'a pas deux sens, elle en a trois. Si la premiere
sortie est retenue, le critere doit dire ce qu'il attend d'un triplet ; si c'est la seconde, il
s'applique tel quel a chacune des deux paires. Ce critere-la est automatisable dans les deux cas -
il se compte sur les bonnes reponses du paquet - a condition d'etre ecrit sans ambiguite sur ce
point.

### Le critere central n'est pas verifiable par automate

"Au moins la moitie des dix nouveaux items obligent a regarder ailleurs dans la phrase
qu'immediatement avant le trou". Etablir cela demande de savoir ou est le sujet, quel mot porte
l'accord et lequel leve l'ambiguite : c'est de l'analyse grammaticale du francais, que ce depot n'a
pas et n'aura pas pour dix phrases. Un substitut du genre "le mot precedant le trou n'est pas un
pronom" serait faux la moitie du temps et donnerait un vert trompeur - le mode de defaillance que
`ADR-0013` interdit. **Porte pedagogique seule**, et la story doit l'ecrire ainsi : c'est
`nadia-prof` qui compte, en lisant.

Le troisieme critere (les dix items existants ne sont pas modifies) est, lui, automatisable en une
assertion.

---

## S5-7 - Une partie de CM1 qui ne se repete pas a l'identique

**Avis : faisable, contenu seul, les deux criteres sont automatisables. Aucun ADR.**

- Compter les items d'un paquet et les comparer a `nombreDeManches` du catalogue : direct, et
  `PaquetsFrancaisS04Test` sait deja enumerer tous les items d'un paquet.
- "Deux parties completes avec des graines distinctes ne presentent pas les memes phrases" :
  automatisable, et le harnais existe deja. `RandomSource` est une interface fonctionnelle, le bean
  par defaut est `@ConditionalOnMissingBean`, et les tests actuels ecrivent deja
  `new Random(42)::nextInt`. Le test doit **fixer ses deux graines** plutot que d'en tirer au sort :
  avec vingt items et dix manches, deux parties identiques restent possibles, tres improbables mais
  possibles, et un test qui echoue une fois sur mille est pire qu'absent.

Une remarque de conception, pas un obstacle : la variete que ce critere mesure vient entierement du
nombre d'items. Le jour ou l'on voudra garantir qu'une partie ne **repete** pas un item ou que deux
parties consecutives se recouvrent peu, ce sera une propriete de la mecanique, pas du contenu, et
cela demandera un etat qui survit a la partie - donc `adapter-persistence`. Hors sujet ce sprint, a
ne pas promettre dans cette story.

---

## S5-8 - "Récréo" redevient "Recreo" (`DEC-0010`)

**Avis : faisable en l'etat, deux occurrences mesurees, aucun ADR. Le risque de cette story n'est
pas la modification, c'est le test - et `camille-po` l'a vu avant moi : son troisieme critere exige
le test qui manquait. Mon apport porte donc sur la maniere de l'ecrire.**

Occurrences dans le depot : `templates/accueil.html` ligne 6 (`<title>`) et ligne 22 (`<h1>`).
Aucune autre, ni dans les gabarits, ni dans le catalogue, ni dans le CSS. Verifie sur `HEAD`.

Deux contraintes d'ecriture pour que ce test ne soit ni decoratif ni rouge en permanence :

1. **Balayer les noeuds de texte rendus** (jsoup, deja utilise dans ce module, jamais une expression
   reguliere - `AssertionsWebTest`), plus le `<title>`, qui n'est pas un noeud de texte du corps. Un
   balayage de la source brute serait rouge en permanence : `recreo` apparait legitimement dans
   `@{/css/recreo.css}`, `@{/js/recreo.js}` et `--recreo-accent`.
2. **Deriver le perimetre du repertoire de gabarits** (`ADR-0013`), pas d'une liste : le nom du
   produit peut arriver demain dans un gabarit qui n'existe pas encore.

Le critere "aucune autre graphie ne cohabite" devient alors une seule assertion sur l'ensemble des
noeuds de texte.

Un point de forme, deja tranche par `DEC-0010` et que je ne fais que confirmer du cote technique :
le garde-fou doit viser **le nom du produit**, jamais les accents en general. L'amendement du
2026-08-04 a `DEC-0002` exige que le texte lu par un enfant soit integralement accentue, et un test
mal cadre ici finirait par servir a justifier du texte non accentue ailleurs. Une assertion sur le
titre et sur les noeuds de texte, pas une regle sur la casse ou les diacritiques.

---

## Le besoin route par `nadia-prof` : comparer deux boutons d'un meme ecran

> "C'est la troisieme fois dans ce projet qu'un defaut visible par un enfant traverse une CI
> entierement verte. [...] aucun garde-fou de ce depot ne compare deux selecteurs entre eux sur un
> meme ecran. [...] Tant que ce garde-fou n'existe pas, c'est moi le garde-fou, et je le suis en
> jouant."

**Reponse franche : oui, le depot peut couvrir cela, et sans navigateur sans tete. Ce que je
recommande n'est pas ce que j'ai ecarte en s03, parce que la question n'est plus la meme.**

### Ce qui a change depuis mon refus de s03

En s03 j'ai ecarte Playwright pour trois criteres visuels de S3-8 (aucun element ne depasse la
largeur de la fenetre, aucun bouton n'en chevauche un autre a 375 px). Ces proprietes-la demandent
un moteur de mise en page : il faut resoudre le flux, le `flex`, les retours a la ligne et les
metriques de police. **Ce n'est pas le cas de celle-ci.** La regression que `nadia-prof` a mesuree
- "Les jeux" a 109 x 53 px contre "Encore !" a 101 x 44 px - a une cause entierement declarative, et
elle est ecrite dans le commit `6c83c97` : le rembourrage `0.75rem 1.25rem` a ete retire du bloc des
boutons pleins au motif que `.btn` de Bootstrap le fournissait, or `.btn` donne `6px 12px`, et
`.retour-accueil` gardait son `0.6rem 1.1rem` plus une bordure de `0.125rem`. **Tout cela se lit
dans le CSS.** Aucun moteur de rendu n'est necessaire pour voir que l'action est passee sous la
sortie.

### Ce que je propose, et ce qu'il aurait attrape

Un garde-fou `HierarchieDeSaillanceTest`, dans `adapter-web` :

- **population derivee** : pour chaque gabarit, les boutons rendus - la machinerie existe deja dans
  `TailleDesTextesEtBoutonsTest` (`etatsRendus`, qui gere `th:class` et `th:classappend` depuis
  `ADR-0015`) ;
- **role** : chaque bouton est une **action** ou une **sortie**. Un bouton dont le role ne se lit
  pas est **rouge**, jamais ignore (`ADR-0013`, obligation 2). C'est le seul point de conception
  reellement ouvert : soit le role se lit dans le balisage (un attribut inerte pose sur le bouton),
  soit il se lit dans une table de correspondance classe -> role dans le test, avec rouge sur
  l'inconnu. Je penche pour le balisage : la source de verite reste l'ecran ;
- **critere** : sur un meme gabarit, un bouton d'action ne declare, pour aucune des proprietes qui
  fabriquent sa boite (`font-size`, `padding`, `min-height`, `border-width`), une valeur inferieure
  a celle d'un bouton de sortie du meme ecran. Une propriete non declaree en CSS maison est rouge -
  c'est exactement ce qui s'est produit : `.encore` n'avait plus **aucune** declaration de
  rembourrage maison.

**Ce qu'il aurait attrape** : le commit `6c83c97`, immediatement, sur le message "aucune regle
maison ne declare `padding` pour ce bouton d'action". Pas apres un audit joue, pas trois semaines
plus tard.

**Ce qu'il ne couvrira pas, et il faut l'ecrire dans son nom et dans son ADR** : il ne compare pas
des **surfaces**. La largeur rendue depend du nombre de glyphes du libelle ("Encore !" contre "Les
jeux"), que rien ici ne mesure. Il garantit une hierarchie des **composantes declarees** de la
boite - ce qui suffit a garantir la hauteur, et ce qui aurait suffi dans les trois occurrences -
mais un garde-fou nomme "l'action est plus grosse que la sortie" mentirait. Il s'appellera "l'action
ne declare pas moins que la sortie".

### Le cout, dit franchement

Environ 150 lignes de test, deux jeux d'artefacts fautifs, plus l'attribut de role sur huit boutons
dans quatre gabarits. La partie delicate n'est pas le balayage, c'est la resolution : convertir les
unites (tout est en `rem` aujourd'hui, et je propose d'exiger le `rem` plutot que de convertir),
lire le raccourci `padding` a une, deux ou quatre valeurs, et decider quelle regle l'emporte entre
maison et tiers - `TailleDesTextesEtBoutonsTest` a deja tout ce qu'il faut pour le dernier point, y
compris le piege des variables `--bs-*` trouve par `yanis-qa`. **Une story, pas un sprint.**

### Pourquoi je ne l'ecris pas dans ce commit, et quand je l'ecris

Parce que je n'ecris pas un ADR sans sa regle, et pas une regle a moitie : une regle de saillance
approximative serait exactement le garde-fou decoratif que ce depot combat, et elle rendrait la
troisieme occurrence **plus** probable en donnant l'illusion d'une couverture. Elle demande aussi de
poser un attribut sur des boutons qui appartiennent aux gabarits d'`ines-design`, dans un arbre ou
`camille-po` travaille en parallele.

**Ma recommandation au planning : une story s05, affectee a moi, avec son ADR ecrit dans le meme
geste.** Elle est prete a etre planifiee, la conception ci-dessus est le cahier des charges.
D'ici la, `nadia-prof` reste le garde-fou, et je le dis sans le maquiller : ce sera la quatrieme
fois qu'elle le sera, et c'est une fois de trop.

**Une precision sur son constat, parce qu'elle merite d'etre exacte** : il existe deja trois
comparaisons entre selecteurs dans `TailleDesTextesEtBoutonsTest`
(`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`, `lIndiceEstAuMoinsAussiGrandQueLaConsigne`,
`aucunTexteNePasseSousLaTailleDeLaConsigne`). Ce qui manque n'est donc pas la comparaison en soi :
c'est qu'aucune ne porte sur des **boutons**, qu'aucune ne compare la **boite** plutot que la
police, et surtout que **leurs paires sont ecrites a la main**. Elles ne couvrent que ce a quoi
quelqu'un a pense. C'est la meme faute de perimetre qu'`ADR-0013`, sur une population de paires.

---

## La dette que j'avais laissee ouverte en la nommant : `HabillageDesEcransTest`

**Traitee dans ce commit. Le plafond de dette d'`ADR-0013` passe de 4 a 3.**

Je l'avais exemptee du plafond dans le commit meme ou je modifiais ce plafond. La traiter par la
seule obligation 3 - lui coller une fixture et un `assertThatThrownBy` - aurait ete une fausse
sortie, et c'est ce que j'ai verifie avant d'ecrire : son perimetre etait une **liste de onze
selecteurs ecrite a la main**, et `ADR-0013` dit qu'une mutation prouve le critere, pas le
perimetre. Une fixture posee sur un perimetre enumere ne prouve rien du perimetre.

J'ai aussi mesure pourquoi la solution evidente ne marche pas : deriver le perimetre en gardant le
critere ("chaque element porte une regle de couleur, de fond ou d'espacement") rend le test rouge
sur `.icone-signal`, qui ne declare ni couleur ni espacement mais une geometrie, et sur les classes
tierces `btn` et `list-unstyled`. Le critere de S2-2 ne se generalise pas ; c'etait un controle
ponctuel de ce que S2-2 livrait.

**Ce que j'ai fait, en appliquant la resolution d'`ADR-0015`** - la propriete generale demenage dans
un garde-fou derive, l'epinglage nomme reste nomme :

- la propriete generale que le nom promettait ("un element rendu est habille") est portee par
  `aucuneClassePoseeSurUnElementNEstOrpheline` : population derivee de **tous** les elements de
  **tous** les gabarits, classes statiques et branches de `th:class` comprises, critere "cette
  classe est nommee par au moins une feuille servie, maison ou tierce". Une classe orpheline -
  faute de frappe dans un gabarit, regle supprimee du CSS - laisse l'element au rendu par defaut du
  navigateur, et aujourd'hui aucun test ne le voit ;
- la liste des onze selecteurs reste, renommee pour dire ce qu'elle est :
  `lesSelecteursHabillesParS2DeuxPortentToujoursCouleurFondOuEspacement`, un epinglage de ce que
  S2-2 a livre ;
- le jeu fautif est un gabarit neuf que personne n'a inscrit nulle part, portant une classe
  qu'aucune feuille ne declare. Sa feuille de style **nomme cette classe dans un commentaire** :
  un garde-fou qui lirait les commentaires la laisserait passer, et c'est exactement le vert
  silencieux que `nadia-prof` avait mesure en s03 sur `.sortie-pleine` - les commentaires de
  `recreo.css` citent nommement `.retour-accueil` et `.btn`.

**Mesure de morsure sur le depot reel**, faite puis annulee : renommer `class="indice"` en
`class="indice-bandeau"` dans `fragments/manche.html` rend le test rouge en nommant la classe et le
fichier. C'est la faute de frappe la plus banale qui soit, et rien ne l'attrapait avant.

Reste dans la dette : `AucunVocabulaireDeMatiereTest`, `FaviconTest`,
`FrontieresDuJavaScriptTest`. Les deux premiers sont des cas ou le nom vise est legitime
(`ADR-0013` le dit deja pour `FaviconTest`) ; le troisieme merite le meme travail que celui-ci et je
ne le fais pas ce sprint, pour ne pas emporter trois garde-fous a la fois dans un arbre partage.

---

## Criteres qui ne verifient pas ce qu'ils croient verifier - a corriger avant le planning

C'est la sortie de cet avis qui demande une action de `camille-po`, pas de moi.

| Critere | Ce qu'il dit | Ce qu'il mesure reellement | Action |
|---|---|---|---|
| S5-2, 1er | "une majorite des items n'est pas resoluble par recherche visuelle" | **rien** : compare aux reponses avec crochets, il est vert des aujourd'hui, 24/24 | nommer la normalisation (reponse telle qu'affichee apres S5-3, sans crochets, casse et accents rabattus) |
| S5-2, 2e | "l'item est accompagne d'un retour qui explique la variation" | un champ et un affichage qui n'existent pas | detacher en une story a part ; ce n'est pas du contenu |
| S5-2, 3e | "la consigne ne parle plus de son entendu" | un texte que S5-1 a supprime du depot | rattacher a la remise au catalogue, sinon la story n'a rien a editer |
| S5-4, 1er | "un seul son du jeu est present dans le mot" | les **lettres** presentes, pas les sons ; le substitut se degrade a mesure que S5-2 reussit | ecrire "porte pedagogique seule" |
| S5-6, critere "regarder ailleurs" | "au moins la moitie des items obligent a regarder ailleurs" | rien qu'un automate sache lire | ecrire "porte pedagogique seule" |
| S5-1, 2e | "il est renvoye vers l'accueil" | un comportement que le code n'a pas (ecran d'erreur) | garder le critere ouvert, et dire quel ecran et quel message le jour ou il se ferme |

## Ce que ce commit contient

- `ADR-0016` et son entree d'index.
- `QuestionContentSourceFactory` : lecture de la cle `indice` de l'item.
- `ChaquePaquetPorteUnIndiceTest` et son jeu fautif (deux paquets neufs, l'un sans indice, l'autre
  corrige).
- `HabillageDesEcransTest` : perimetre derive, jeu fautif, sortie de la dette d'`ADR-0013` ;
  `LesGardeFousSontMisALEpreuveTest` : plafond de 4 a 3.
- Ce fichier.

**Verification avant planning** : `mvn -B clean verify` sur JDK 21.0.10 - **BUILD SUCCESS,
189 tests, 0 echec** (38 `domain`, 7 `application`, 71 `adapter-web`, 14 `adapter-content`,
57 + 2 `bootstrap`). Le detail des mutations mesurees est dans `ADR-0016` et ci-dessus.
