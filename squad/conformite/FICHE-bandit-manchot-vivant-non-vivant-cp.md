# Fiche de conformite - bandit-manchot-vivant-non-vivant-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-vivant-non-vivant.yaml`.
- **Entree de catalogue** : `bandit-manchot-vivant-non-vivant-cp`, titre servi *"Vivant ou pas vivant"* depuis la correction du 2026-09-04 (*"Vivant ou objet"* avant), `nombreDeManches: 10` depuis s28 (`7` a s27).
- **Statut global** : **VALIDE SOUS RESERVE** (2026-09-04, troisieme passage, bloc en bas de fiche). **La ligne `QLM-CP-01` ferme** : elle passe a `couvert-valide`. Une reserve nommee et datee, `VIV-R2`, non bloquante, a corriger dans un sprint suivant.
- **Histoire des verdicts, aucun efface** : s27, premier paquet (huit items) **VALIDE**. s28, second passage (six items neufs) **REFUSE** le 2026-09-04 - *"De la neige, qu'est-ce que c'est ? - un objet"* est faux, et le jeu felicitait l'enfant qui le repondait. s28, troisieme passage apres correction (`0c383b9`) **VALIDE SOUS RESERVE** le meme jour. **Les trois blocs restent lisibles, dans l'ordre ; un refus efface ne prouve rien.**
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 1) et 7.1.
- **Derniere mise a jour** : 2026-09-04 (**troisieme verdict de `nadia-prof` apres avoir rejoue et relu les quatorze items**, s28).

## Competence visee

`Distinguer les êtres vivants des objets et de la matière inerte sur des exemples du quotidien.`
(Questionner le monde, CP, Le vivant, la matière et les objets).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude - huit items sur huit

chat, arbre, poisson, oiseau -> **etre vivant** ; chaise, ballon, voiture, stylo -> **objet**.
**Quatre et quatre, aucune erreur.** L'arbre est bien du cote du vivant, ce qui est l'erreur
classique a cet age et le seul piege reel de la ligne ; l'indice le justifie correctement
(*"un arbre nait, grandit et a des petits (ses graines)"*).

### 2. Deux propositions seulement - **accepte, et j'ai mesure le precedent avant de l'accepter**

Un item binaire se repond a 50 % au hasard, et c'est une objection serieuse. **Je l'ai instruite
sur le catalogue plutot que sur mon impression.**

| Paquet servi | Items | Propositions | Statut |
|---|---|---|---|
| `cp-nature-mot` | 16 | **2** | `couvert-valide`, valide par moi |
| `cm1-homophones` | 24 | **2** | `couvert-valide`, valide par moi |
| `cp-son-dans-mot` | 12 | **2** | `couvert-valide`, valide par moi **le 2026-09-01** |

**Trois paquets binaires, tous valides par moi, dont un il y a deux jours. Je ne deplace pas ce
poteau.** Et le motif de fond tient : *vivant* contre *objet* est une opposition **reellement
binaire**, et une troisieme proposition devrait etre inventee - c'est-a-dire fausse ou absurde,
ce que je refuse par ailleurs.

### 3. Lisibilite - mesuree, pas estimee

**Les huit enonces font 5 mots chacun**, forme identique (*"Un chat, qu'est-ce que c'est ?"*).
Pour un CP, c'est court, regulier, et la regularite aide : l'enfant lit la forme une fois et ne
lit plus ensuite que le mot qui change. **C'est le meilleur profil de lisibilite des trois paquets
de cette matiere.**

### 4. Biais

**`BIA-4` respectee** : aucun item n'interroge l'enfant sur sa propre vie. Les huit objets et
etres cites sont accessibles a tous les enfants sans supposer un foyer, un lieu ni un moyen.
**`lea-dev` a par ailleurs ecarte deliberement ma ligne 16** (*ici et ailleurs*), que j'avais
signalee comme la plus dangereuse du lot. **Le choix des lignes a evite les clauses de biais
plutot que de les affronter mal** : c'est la bonne conduite pour un premier paquet de matiere
neuve.

### 5. Joue jusqu'au bout

Partie complete, **7 manches**, ecran de fin atteint (*"C'est fini !"*), retour au catalogue
disponible. Erreurs volontaires : l'enfant reste sur l'item, recoit *"Ce n'est pas ca. Essaie
encore !"* et son indice. **L'indice ne donne jamais la reponse : il donne le critere** (*"un
stylo ne nait pas, ne grandit pas et ne se nourrit pas"*). C'est exactement ce que je demande d'un
indice, et c'est rare.

### 6. Reserve non bloquante

- **`VIV-R1` - `nombreDeManches: 7` n'est couvert par aucune decision produit ecrite.**
  `DEC-0064` exige que toute valeur autre que `10` soit portee par une decision qui nomme son
  motif ; `DEC-0020` ne couvre que les complements a 10. **Ce n'est pas un defaut pedagogique** -
  interroge, je reponds que **7 manches pour 8 items est la bonne valeur**, une partie de 10 en
  epuiserait le paquet et le ferait tourner en rond. **La ratification est donc facile ; elle
  manque.** Route a `camille-po`. Vaut identiquement pour les deux autres paquets de cette matiere.


---

## Second passage - increment de s28 (S28-1, `ad522a2`) - **REFUSE**

- **Verdict** : **REFUSE**, prononce le **2026-09-04** par `nadia-prof`, apres avoir joue.
- **Ce que ce refus ne fait pas** : il **ne revoque pas** le Done du 2026-09-03. Les huit items du
  premier paquet restent valides, mot pour mot, et le bloc ci-dessus n'est pas touche. Le refus
  porte sur les **six items ajoutes** et sur la **fermeture de la ligne**, pas sur le jeu entier.
- **Consequence de matrice** : `QLM-CP-01` **reste `en cours`**. Elle ne devient pas
  `couvert-valide`. Le motif est corrigeable en une reecriture de texte ; il part a s29.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit (`mvn -B clean package`, machine
virtuelle rapportant 21.0.10) et lance, **partie complete de dix manches par appels HTTP reels**,
y compris en me trompant volontairement. **Et j'ai lu les quatorze items un par un** : une partie
de dix manches sur quatorze items n'en montre jamais quatre, ce que j'ai ecrit d'avance (arbitrage
prealable de s28, section 5.5). **Ce n'est pas un audit degrade.**

### 1. Ce que le lot a tenu, et je le dis avant de dire ce qui casse

- **`FER-1`** telle que **je** l'ai ecrite pour cette ligne : *"+6 items minimum, dont au moins
  trois de matiere inerte"*. **Trois items de matiere inerte servis** (caillou, sable, neige),
  **six items ajoutes**. Tenue au chiffre pres.
- **`FER-2`** : **14 items**, contre un plancher de dix. Tenue.
- **`FER-3`** : deux taches reelles, **onze categorisations et trois selections**
  (*"Parmi ces trois choses, laquelle est un etre vivant ?"*), la moins servie a trois, exactement
  mon plancher. **C'est bien deux operations mentales differentes** - classer un element isole,
  reperer l'element different parmi trois - et non deux formulations de la meme. Tenue.
- **Les trois items de selection sont justes** (le chien, la fleur, le poisson) et leurs
  distracteurs sont pris dans le materiau deja introduit. Rien a leur reprocher.
- **`nombreDeManches`** repasse a `10` : `VIV-R1`, ma reserve de s27, **est levee** - le motif de
  `DEC-0073` (huit items) n'existe plus a quatorze.

**Autrement dit : ce lot echoue sur l'exactitude, pas sur l'extension.** Mes trois conditions de
fermeture sont tenues et cela ne suffit pas - c'est exactement ce que j'avais ecrit d'elles
(*"elles disent quand une ligne peut fermer, jamais qu'elle doit"*).

### 2. **Le motif du refus - trois items enseignent une classification fausse**

Le paquet oppose deux propositions, **`un etre vivant`** et **`un objet`**. Les items du premier
paquet le pouvaient : chaise, ballon, voiture, stylo **sont** des objets fabriques. Les trois items
neufs de matiere inerte gardent le meme couple, et cela donne, **lu a l'ecran par moi pendant la
partie** :

> *"De la neige, qu'est-ce que c'est ?"* -> **un objet** -> *"Bravo !"*
> *"Du sable, qu'est-ce que c'est ?"* -> **un objet** -> *"Bravo !"*

**Ce n'est pas vrai, et ce n'est pas une nuance de vocabulaire.** Le libelle de la ligne, que j'ai
ecrit et qui est recopie en tete du fichier, enumere **trois** choses et en distingue explicitement
deux : *"les etres vivants"*, *"les objets"* **et** *"la matiere inerte"*. L'item repond a la
question *"qu'est-ce que c'est ?"* - une question d'identification - par le nom d'une **autre**
categorie du meme libelle. Le programme de cycle 2 distingue de la meme facon ce qui est *mineral*
de ce qui est *elabore par des etres humains* : un enfant entraine ici repondra faux a l'exercice
standard de sa classe. **Une erreur factuelle dans un contenu scolaire est un motif de refus a elle
seule ; celle-ci porte sur la distinction meme que la ligne enseigne.**

**Le troisieme item, *"Un caillou"*, est de la meme famille mais plus defendable** : un caillou est
une chose solide et isolable, que le langage courant appelle volontiers un objet. Je le signale
sans en faire le motif. **Le sable et la neige, eux, ne sont des objets dans aucun usage.**

**Et le titre du jeu porte la meme erreur** : *"Vivant ou objet"* etait exact tant que le non-vivant
etait fabrique ; il ne l'est plus.

### 3. **Ce que je corrige de moi-meme avant d'arbitrer, parce que la cause est chez moi**

**C'est moi qui ai ouvert cette porte, et je l'ecris plutot que de la laisser trouver.** Mon
arbitrage prealable de s28, section 3.1, dit : *"l'opposition reste binaire - on distingue le vivant
du reste, et la matiere inerte est une famille d'exemples du non-vivant, pas une troisieme
etiquette. Aucun changement de mecanique n'est requis."* J'ai autorise le binaire **sans verifier le
libelle du pole non-vivant**, que je connaissais pourtant : je l'avais moi-meme decrit a s27 comme
*"4 objets fabriques"*. `lea-dev` a applique ma consigne a la lettre.

**Ce que je maintiens** : l'opposition binaire est bonne, la mecanique n'a pas a changer, et je ne
demande **pas** de troisieme etiquette. **Ce que je corrige** : le binaire ne peut pas etre
*vivant / objet* sur un item de matiere inerte. **Ce refus n'est donc pas un poteau deplace** - ma
barre d'exactitude est ancienne et n'a pas bouge ; c'est ma prescription du 2026-09-03 qui etait
incomplete, et je la complete ici, datee.

### 4. **La correction, fournie mot pour mot** (regime du mot pour mot, 0,25)

Elle ne touche ni la mecanique, ni les items de s27, ni les trois items de selection. **Seuls les
trois items de matiere inerte changent de couple de propositions** ; les quatre items d'objets
fabriques gardent `un objet`, ce qui garde l'element *"les objets"* du libelle en position de bonne
reponse.

| Item | `bonneReponse` | `distracteurs` |
|---|---|---|
| *"Un caillou, qu'est-ce que c'est ?"* | `ce n'est pas un être vivant` | `["un être vivant"]` |
| *"Du sable, qu'est-ce que c'est ?"* | `ce n'est pas un être vivant` | `["un être vivant"]` |
| *"De la neige, qu'est-ce que c'est ?"* | `ce n'est pas un être vivant` | `["un être vivant"]` |

**Les trois indices restent inchanges** : ils disent deja le bon critere (*"La neige ne nait pas, ne
grandit pas et ne se nourrit pas"*) et ne mentionnent aucun objet.

**Titre du jeu** : *"Vivant ou pas vivant"*.

**Ce que je ne fournis pas** : l'orthographe, l'accentuation et la ponctuation de ces textes
appartiennent au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`), y
compris parce qu'ils sont lus par l'enfant. Je fournis le fond, le niveau et le ton.

### 5. Un effet de bord que je signale a `sofia-sm` sans le trancher

Le test `chacuneDesDeuxCategoriesEstRepresenteeParmiLesItemsDeCategorisation` **fige le compte
`4 vivants / 7 objets dont 3 de matiere inerte`**. Un garde-fou vert **grave donc aujourd'hui la
classification que je refuse**. Ce n'est pas un vert a tort au sens de la CI - le test dit vrai sur
ce que le fichier contient - mais c'est une illustration de ce que j'ecris a chaque sprint : **un
test ne sait pas si un contenu est juste.** La forme du correctif appartient a `theo-archi` et
`lea-dev`, pas a moi.

### 6. Ce que mon refus ne dit pas

Il ne dit **pas** que le lot est mauvais : `FER-1`, `FER-2`, `FER-3` sont tenues, les selections
sont bien construites, les indices restent les meilleurs du produit sur cette matiere. **Il dit
qu'un contenu scolaire faux ne ferme pas une ligne**, meme quand tout le reste est en place, et
meme quand la faute vient de ma propre prescription.

### 7. Consequence de sprint, dite franchement

Le sprint est au plafond de ma borne (5,0 sur 5) et **il n'y a aucune marge de rejeu** : ce refus
**ne sera pas repris dans s28**. La correction ci-dessus part a **s29**, elle vaut **0,25** au
regime du mot pour mot puisque je viens de fournir le texte, et **les trois items faux restent
servis en production jusque-la** - c'est un fait que je ne minimise pas et que `camille-po` doit
peser en priorisant.

---

## Troisieme passage - reaudit apres correction (`0c383b9`) - **VALIDE SOUS RESERVE**

- **Verdict** : **VALIDE SOUS RESERVE**, prononce le **2026-09-04** par `nadia-prof`, apres avoir
  rejoue et relu les quatorze items. **Done pedagogique ouvert.**
- **Ce que ce verdict ne fait pas** : il **n'efface pas** le refus du second passage, qui reste
  au-dessus, date, avec son motif entier. Un refus efface ne prouve rien. Ce bloc s'ajoute, il ne
  remplace pas.
- **Consequence de matrice** : **`QLM-CP-01` ferme.** `camille-po` peut passer la ligne a
  `couvert-valide`. **Aucune mention de couverture partielle n'est due sur cette ligne.**
- **Reserve nommee, non bloquante, nouvelle et datee du 2026-09-04** : `VIV-R2`, section 4.
  **Elle ne durcit pas le motif du refus** - celui-ci est paye entierement, et je le dis avant de
  dire ce qui reste.
- **Prix** : **0,25**, regime du mot pour mot, puisque j'avais fourni le texte. Total du sprint
  porte a **5,25**, au-dela de ma borne de 5, **par arbitrage de l'utilisatrice du 2026-09-04**
  rendu avec mon refus ecrit sous les yeux. J'execute cet arbitrage, je ne le rediscute pas.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `0c383b9`, jar reconstruit et lance sur une machine virtuelle
rapportant 21.0.10, **partie complete de dix manches par appels HTTP reels**, dont les trois items
de matiere inerte tires, **avec erreur volontaire sur l'item de la neige**. **Et j'ai relu les
quatorze items un par un** avec leurs distracteurs, parce que j'ai ecrit a s27 qu'un item rare peut
n'etre jamais tire. **Ce n'est pas un audit degrade.**

**Done technique acquis** : `mvn -B clean verify` sur le reacteur complet, **BUILD SUCCESS** sur les
six modules. Un jeu qui plante n'est pas evaluable ; celui-ci ne plante pas.

### 1. **Le motif du refus est paye, integralement**

Lu a l'ecran pendant la partie, sur les deux items que j'avais cites nommement :

> *"De la neige, qu'est-ce que c'est ?"* -> propositions **`un être vivant`** et
> **`ce n'est pas un être vivant`** -> la bonne reponse est la seconde.
> *"Un caillou, qu'est-ce que c'est ?"* -> meme couple, meme bonne reponse.

**Le jeu ne felicite plus l'enfant qui repond qu'une chose de matiere inerte est un objet** : cette
reponse n'existe plus sur ces trois items. Les quatre objets fabriques (chaise, ballon, voiture,
stylo) gardent `un objet` - je l'ai verifie item par item et j'en ai vu servir trois en jouant.
**Le titre est corrige** : le catalogue sert *"Vivant ou pas vivant"*. La correction est mon texte,
mot pour mot, sans ajout ni retrait.

**L'opposition est restee binaire, comme je l'avais maintenu** : deux propositions par item, jamais
plus. Et **l'enfant ne voit jamais `un objet` et `ce n'est pas un être vivant` cote a cote** - j'ai
verifie dans la mecanique que les propositions d'une manche sont la bonne reponse et les
distracteurs **de cet item-la**, melanges, jamais tires d'un vivier commun au paquet. Le risque
symetrique de ma propre correction - offrir a l'enfant deux reponses toutes deux vraies, dont une
comptee fausse - **n'existe donc pas**. C'etait le point que je devais verifier et il est verifie.

### 2. **Ce que j'ai verifie au-dela des trois items corriges**

J'avais ecrit que le defaut venait de ma prescription incomplete. Une correction ciblee sur trois
items peut laisser la meme confusion ailleurs ; j'ai donc repris le paquet entier.

- **Les quatre etres vivants** (chat, arbre, poisson, oiseau) : bonne reponse `un être vivant`,
  distracteur `un objet`. **`un objet` y est faux**, donc c'est un distracteur legitime. Rien a
  reprendre. L'arbre reste du bon cote, ce qui est le seul vrai piege de la ligne.
- **Les quatre objets fabriques** : bonne reponse `un objet`, distracteur `un être vivant`. **Une
  chaise, un ballon, une voiture, un stylo sont des objets fabriques** ; le mot est exact pour eux
  et pour eux seuls. **L'element *"les objets"* du libelle de la ligne reste servi en position de
  bonne reponse**, ce que je demandais.
- **Les trois items de selection** (chien / chaise / caillou, fleur / ballon / sable, poisson /
  voiture / neige) : bonnes reponses justes, **six distracteurs verifies un par un**, tous non
  vivants, tous pris dans le materiau deja introduit. **Aucun distracteur absurde, aucun distracteur
  vrai.** Ils ne nomment jamais une categorie, seulement une chose : la tache reste distincte de la
  categorisation.
- **La confusion que je cherchais n'est nulle part ailleurs** : aucun item ne demande a l'enfant de
  distinguer un objet fabrique d'une chose de matiere inerte, et c'est la bonne conduite - **le
  libelle de la ligne demande de distinguer le vivant *des objets et de la matiere inerte*, pas les
  objets de la matiere inerte.** Le paquet couvre la ligne telle qu'elle est ecrite.
- **Les indices** : inchanges, et ils portent toujours le critere sans donner la reponse (*"La neige
  ne nait pas, ne grandit pas et ne se nourrit pas"*). Ce sont toujours les meilleurs du produit sur
  cette matiere.
- **Traitement de l'erreur, joue** : sur ma faute volontaire, l'enfant reste sur l'item, recoit
  *"Ce n'est pas ca. Essaie encore !"* et l'indice. J'ai aussi echoue trois fois de suite sur un
  item de selection : **le jeu revele alors la reponse et demande a l'enfant de l'appuyer**
  (*"C'est le poisson. Appuie sur le poisson."*). **Un enfant bloque peut toujours avancer**, et il
  ne recoit jamais un simple *"faux"*.
- **Joue jusqu'au bout** : dix manches, ecran de fin atteint (*"C'est fini !"*), retour au
  catalogue disponible.
- **Biais** : inchange depuis s27. **`BIA-4` respectee** - caillou, sable et neige n'interrogent
  l'enfant ni sur son foyer, ni sur son lieu, ni sur ses moyens. **La neige merite d'etre nommee**
  parce qu'un enfant peut ne jamais en avoir vu : elle n'est ici ni un vecu presuppose ni la
  reponse, seulement un exemple nomme, et l'indice suffit a repondre sans l'avoir touchee. **Ce
  n'est pas un biais.**

### 3. Je corrige un de mes propres constats avant de m'en servir

Ma section 5 du refus disait que le test
`chacuneDesDeuxCategoriesEstRepresenteeParmiLesItemsDeCategorisation` **figeait** *"4 vivants /
7 objets dont 3 de matiere inerte"*. **Le nom que j'ai cite n'existe plus** : le test s'appelle
desormais `chacuneDesTroisReponsesEstRepresenteeParmiLesItemsDeCategorisation` et compte **4 / 4 /
3**. Ma phrase decrivait exactement l'etat d'alors et elle est perimee de la bonne facon - c'est ma
correction qui l'a perimee. **Je l'ecris plutot que de la laisser lire comme un reproche encore
vivant.**

### 4. **`VIV-R2` - reserve nouvelle, datee du 2026-09-04, non bloquante, et le defaut est dans mon texte**

**Ce que j'ai vu en jouant et que je ne pouvais pas voir en dictant sur le papier** : sur les trois
items de matiere inerte, les deux boutons cote a cote portent

> `un être vivant`   et   `ce n'est pas un être vivant`

**Trois mots contre six, et les trois mots courts sont contenus entiers a la fin des six.** Le seul
element qui distingue les deux propositions - *ce n'est pas* - est **en tete de la plus longue**,
alors qu'un enfant de CP qui decode lentement s'accroche aux mots qu'il reconnait, et il reconnait
*vivant* dans les deux. Sur les onze autres items, les deux propositions sont **lexicalement
disjointes** (*un objet* contre *un être vivant*, ou trois noms de choses) : l'enfant discrimine
d'un coup d'oeil. **Sur ces trois-la, il doit lire jusqu'au bout pour distinguer.**

**S'y ajoute un point de registre** : *"De la neige, qu'est-ce que c'est ?"* est une question
d'identification, et *"ce n'est pas un être vivant"* y repond par une negation plutot que par un
nom. C'est vrai, mais ce n'est pas la forme d'une reponse a *"qu'est-ce que c'est ?"*.

**Pourquoi c'est une reserve et non un refus, et je tiens mon precedent dans les deux sens.**
Le motif de mon refus etait **l'exactitude**, et l'exactitude est retablie. Ce qui reste est de la
**lisibilite et du registre**, famille que j'ai declaree non bloquante sur `COR-R1` (2026-09-03) et
sur `COR-R4` (2026-09-04), toutes deux sur du texte plus penalisant que celui-ci. **Refuser
aujourd'hui mon propre texte, applique mot pour mot, serait deplacer mon poteau apres coup et faire
de ma porte un caprice.** Je ne le fais pas. `VIV-R2` est **une reserve neuve et datee, pas un
durcissement de l'ancienne** : elle nait d'un fait que seul le jeu montre - **deux boutons cote a
cote**, que le fichier de contenu ne montre pas.

**Ce qu'une correction devra tenir, pour que `camille-po` en fasse une story actionnable** : sur un
item de categorisation, **les deux propositions ne doivent pas se terminer par les memes mots**, et
ce qui les distingue ne doit pas se trouver en position initiale seulement.

**Piste que je nomme sans la prescrire, parce que je ne l'ai pas jouee** : remplacer, sur ces trois
items seuls, `ce n'est pas un être vivant` par **`de la matière`**. Cela rend les deux propositions
disjointes, cela repond a *"qu'est-ce que c'est ?"* par un nom, cela n'ajoute **aucune** etiquette
au paquet (il en porte deja trois) et **cela recopie le troisieme terme du libelle de la ligne**.
Ce qu'il faudra verifier avant de l'ecrire, et que je verifierai en jouant : *matiere* comme
categorie est un mot abstrait pour un enfant de six ans, et il faudra que les indices le portent.
**Tant que ce n'est pas verifie, la formule actuelle reste servie et elle est juste.**

### 5. Sur quelles proprietes je reste la seule garde - dit explicitement

Le test neuf de `lea-dev` mord bien le defaut que j'ai trouve : il nomme les trois enonces de
matiere inerte et exige leur bonne reponse. **Ce qu'il garde reellement, ce sont les chaines de
caracteres que j'ai dictees, et les comptes 4 / 4 / 3.** Il ne sait pas que *"un objet"* est faux
pour du sable ; il sait que j'ai ecrit autre chose. **La verite d'un enonce scolaire n'est gardee
par aucun test de ce depot, et elle ne peut pas l'etre** : c'est moi, en jouant et en lisant. Que
`VIV-R2` porte sur du texte que j'avais moi-meme dicte, et qu'aucun garde-fou n'ait rien dit, en est
la demonstration du jour.

Restent egalement sans garde-fou sur cette ligne : la plausibilite des distracteurs, la
discriminabilite de deux propositions affichees cote a cote, l'accessibilite du vocabulaire au
niveau vise, et le fait qu'un item de selection serve vraiment sa ligne. **L'equipe ne doit pas
croire couvert ce qui ne l'est pas.**

### 6. Etat des reserves de cette ligne au 2026-09-04

- **`VIV-R1`** (`nombreDeManches: 7` sans decision ecrite) : **levee au second passage**, la valeur
  est repassee a `10`. Inchange, rien a rouvrir.
- **`VIV-R2`** (les deux propositions des items de matiere inerte se terminent par les memes mots) :
  **ouverte, non bloquante**, section 4. A traiter a un sprint suivant.
