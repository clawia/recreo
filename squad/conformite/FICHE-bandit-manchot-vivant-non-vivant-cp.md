# Fiche de conformite - bandit-manchot-vivant-non-vivant-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-vivant-non-vivant.yaml`.
- **Entree de catalogue** : `bandit-manchot-vivant-non-vivant-cp`, titre servi *"Vivant ou objet"*, `nombreDeManches: 10` depuis s28 (`7` a s27).
- **Statut global** : **le premier paquet (s27, huit items) reste VALIDE** - je ne revoque rien. **L'increment de s28 (six items neufs) est REFUSE** (2026-09-04, section 7 ci-dessous) : **la ligne `QLM-CP-01` ne ferme pas** et reste `en cours`. Motif en une phrase : *"De la neige, qu'est-ce que c'est ? - un objet"* est faux, et le jeu felicite l'enfant qui le repond.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 1) et 7.1.
- **Derniere mise a jour** : 2026-09-04 (**second verdict de `nadia-prof` apres avoir rejoue**, s28).

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
