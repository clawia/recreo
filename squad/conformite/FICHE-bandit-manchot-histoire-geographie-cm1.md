# Fiche de conformite - bandit-manchot-histoire-geographie-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml`.
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-cm1`, titre servi *"Histoire et géographie"*, `nombreDeManches: 10`.
- **Statut global s29** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** sur l'increment de s29, composition A, stories `S29-2` et `S29-7`). **Trois lignes de plus ferment** : `HG-CM1-06`, `HG-CM1-07` et `HG-CM1-14` passent a `couvert-valide`, avec une **mention exigee d'avance sur la ligne 14**. **Six lignes du paquet restent `en cours`** (section 8 du bloc de s29). `HG-R4` et `HG-R5` sont **levees** ; deux reserves nouvelles, non bloquantes : `HG-R6` et `HG-R7`.
- **Statut global s28** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-04** sur l'increment de s28, composition B). **Deux lignes ferment** : `HG-CM1-11` et `HG-CM1-13` passent a `couvert-valide`. **Les neuf autres lignes du paquet restent `en cours`** et personne ne doit s'en etonner : je l'avais ecrit d'avance. Deux reserves nommees, non bloquantes, `HG-R4` et `HG-R5`.
- **Statut s27, conserve tel quel** : **VALIDE** (Done pedagogique prononce le 2026-09-03). **Ma reserve bloquante du 2026-09-03 sur l'item 5 est levee** : la reformulation est reprise mot pour mot. Lignes de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, aucune ligne ne se ferme.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` sections 5, 7.2, 7.4 et 7.5 (`HIS-1` a `HIS-3`, `BIA-5`) ; `ARBITRAGE-s27-s1-8-retrecie-unite-de-ma-borne-et-trois-faits-d-histoire.md` section 3.
- **Statut global s32** : **REFUSE** (Done pedagogique **refuse le 2026-09-09** sur l'increment de s32, story `S32-1`, par `nadia-prof`, **instance A**). **Le refus porte ligne par ligne** : `HG-CM1-02` (`HG-R11`) et `HG-CM1-04` (`HG-R12`) ne ferment pas et restent `en cours` ; **`HG-CM1-01` et `HG-CM1-03` ferment** et passent `couvert-valide`, la ligne 1 avec une **mention de couverture partielle** que j'ecris ce jour. `HG-R8` **levee**. Reserves nouvelles : `HG-R9`, `HG-R10` (non bloquantes), `HG-R11`, `HG-R12` (**bloquantes**), `CONS-4` (non bloquante, portee D). `CONS-1` **non levee** sur cette entree.
- **Statut global s33** : **VALIDE SOUS RESERVE sur trois lignes, REFUSE sur une** (verdicts prononces le 2026-09-10 sur l'increment de s33, stories `S33-HG-02`, `S33-HG-04` et `S33-HG-08-12`, par `nadia-prof`, **instance A**). **`HG-CM1-02`, `HG-CM1-04` et `HG-CM1-08` ferment** et passent `couvert-valide` ; **`HG-CM1-12` est refusee** (`HG-R17`, bloquante) et reste `en cours`. `HG-R9`, `HG-R10`, `HG-R11` et `HG-R12` **levees**. Reserves nouvelles : `HG-R13` a `HG-R16` et `HG-R18` (non bloquantes), `HG-R17` (**bloquante**). `CONS-1` **toujours non levee** ; `CONS-4` **toujours ouverte**. **Aucune mention de couverture partielle n'est due sur les quatre lignes.**
- **Derniere mise a jour** : 2026-09-10 (**cinquieme verdict de `nadia-prof`, instance A, apres vingt-huit parties completes, 280 manches servies et la lecture des 110 items du fichier**, s33).

## Competence visee

`Onze des douze lignes jouables du référentiel Histoire-géographie CM1`
(Histoire-géographie, CM1, les six domaines du programme de CM1).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude - le controle que j'avais facture d'avance

**Sur une matiere ou je ne suis pas la source, c'est le poste de cout principal, et je l'ai
paye.** Les faits verifies un par un sur les items joues :

| Fait | Verdict |
|---|---|
| Revolution 1789 -> XVIIIe siecle | juste |
| Chambord -> Francois Ier, Renaissance | juste |
| Edit de Nantes -> Henri IV | juste |
| Croisade -> Louis IX | juste |
| Versailles -> Louis XIV | juste |
| Prise de la Bastille -> debut de la Revolution a Paris, 1789 | juste |
| Abolition des privileges (4 aout) -> **puis** Declaration des droits (26 aout), meme annee | **juste, et l'ordre est exact** |
| Napoleon sacre empereur 1804, monarchie abolie 1792 -> Empire | juste |
| Arenes et aqueducs en Gaule -> les Romains | juste (deja confirme le 2026-09-03) |
| Bastille -> pouvoir absolu du roi | juste |
| Declaration des droits -> 1789 | juste |

**Aucune erreur de fait. Aucune date fausse.**

### 2. L'item 5 - **ma reserve bloquante est levee**

Le 2026-09-03 j'avais bloque sur *"comment appelle-t-on **l'ensemble** de ces peuples"*, parce que
les peuples entres dans l'Empire au IVe siecle **ne sont pas tous germaniques**. La reformulation
que j'avais fournie est reprise **mot pour mot** :

> *"A partir du IVe siecle, des peuples venus du nord et de l'est s'installent dans l'Empire
> romain. Les Francs sont l'un d'eux. Comment appelle-t-on ces peuples ?"*

*L'ensemble* a disparu, la reponse ne pretend plus a une couverture totale, la repetition est
retiree. **L'enonce passe de 30 a 26 mots.** Reserve **levee**.

### 3. `HIS-1` - la clause qui devait empecher le quiz de dates

**Tenue : 5 items de restitution sur 20, soit 25,0 %**, sous mon plafond d'un tiers. Compte
mecanique, et **le test echoue aussi si un item n'a pas son marqueur** - ce qui ferme la porte a un
item qui echapperait au compte en oubliant sa marque. **C'est mieux que ce que j'avais demande.**

Et l'effet se voit en jouant : sur dix manches, j'ai eu des items d'**ordre** (*"lequel a eu lieu
le premier ?"*, *"quel evenement juste apres l'abolition des privileges ?"*), d'**association**
(roi -> realisation) et de **document** (deux textes supports). **Ce n'est pas un quiz de dates.**
C'etait mon inquietude principale sur cette matiere ; elle ne se realise pas.

### 4. `HIS-2` et `BIA-5`

**`HIS-2` tenue** sur les dix items joues : aucun n'a deux reponses defendables. `marc-dev` avait
ecarte *"pourquoi la Revolution a-t-elle eclate ?"* - mon propre exemple - et evite les dates
disputees. **Une clause qui change le contenu avant l'audit vaut mieux qu'une clause qui refuse
apres.**

**`BIA-5` tenue** : aucun item ne fait porter un jugement de valeur sur un roi ni sur un acteur de
la Revolution. Les enonces decrivent et n'apprecient pas.

### 5. Le titre - **verifie sur le contenu, ma reserve tombe**

*"Histoire et geographie"* nomme la matiere plutot que le jeu, ce qui est le defaut que j'ai
reproche a *"Calcul CP"*. **J'allais poser la reserve ; je suis allee compter d'abord.** Le paquet
porte bien des items de geographie - espace urbain, espace touristique, espace rural, production
d'energie - **quatre sur vingt**. Le titre ne promet donc rien que le contenu ne tienne. **Pas de
reserve.**

### 6. Joue jusqu'au bout

Partie complete, **10 manches**, ecran de fin atteint. Les deux textes supports font 27 et 36
mots, ce qui est la forme deja validee de `bandit-manchot-comprehension-cm1`.

**Un incident de mon cote que je consigne, parce qu'il aurait pu devenir un faux refus** : mon
pilote d'audit a d'abord rendu l'item de la Declaration des droits **impossible a resoudre** - la
revelation disait *"appuie sur la Declaration des droits de l'homme et du citoyen"* et le bouton
etait refuse. **C'etait mon outil, pas le produit** : l'apostrophe est echappee dans la valeur du
bouton, et un navigateur la decode avant d'envoyer. **Verifie en reposant la valeur decodee : la
reponse est acceptee.** Je l'ecris parce que j'ai failli porter comme defaut bloquant un artefact
de ma propre mesure.

### 7. Reserves non bloquantes

- **`HG-R1` - deux enonces a 26 mots** (l'item 5 reformule et l'item sur la production
  d'electricite), contre une mediane de 13 sur les vingt. C'est long pour un CM1, mais ce sont des
  items de raisonnement dont l'enonce porte la donnee necessaire. **Non bloquant, a surveiller si
  un troisieme apparait.**
- **`HG-R2` - la matiere penche vers l'histoire** : 16 items d'histoire pour 4 de geographie. C'est
  legitime pour un premier paquet, et les lignes de geographie les plus lourdes (carte,
  planisphere) sont hors de portee du palier 1 par mon propre arbitrage. **A rappeler quand la
  matiere sera etendue**, pour que la geographie ne reste pas le quart pauvre.

### 8. Ce dont je reste la seule garde

**L'exactitude factuelle de cette matiere n'est couverte par aucun test et ne le sera pas.**
`HIS-2` non plus. `HIS-1` est la seule des trois qu'un automate mord, et il ne le fait que parce
que le contenu declare le type de chaque item.


---

## Second passage - increment de s28 (S28-2, `ec73c51`), composition B - **VALIDE SOUS RESERVE**

- **Verdict** : **VALIDE SOUS RESERVE**, prononce le **2026-09-04** par `nadia-prof`, apres avoir
  joue. **Done pedagogique ouvert.**
- **Consequence de matrice** : **`HG-CM1-11` (types d'espaces) et `HG-CM1-13` (eau, energie,
  aliments) ferment** - `en cours` -> **`couvert-valide`**. **Les neuf autres lignes servies par ce
  paquet restent `en cours`.** Aucune ligne neuve n'est ouverte.
- **Reserves nommees, non bloquantes, a traiter dans un sprint suivant** : `HG-R4` (charge de
  lecture) et `HG-R5` (un mot d'indice). `HG-R1` et `HG-R2` de s27 : etat en section 6.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit et lance, **parties completes de dix
manches par appels HTTP reels**, y compris en me trompant volontairement. **Et j'ai lu les
trente-sept items un par un** - c'est le point de methode que j'avais ecrit d'avance apres avoir
manque un item a s27 : une partie de dix manches sur trente-sept items en laisse **vingt-sept**
jamais vus. **Ce n'est pas un audit degrade.**

### 1. La distribution - comptee, pas estimee

**Trente-sept items.** Comptage par ligne declaree (`HIS-4`, `ADR-0081`), fait par moi sur le
fichier : ligne 1 : 2 ; ligne 2 : 1 ; ligne 3 : 1 ; ligne 4 : 1 ; ligne 6 : 4 ; ligne 7 : 3 ;
ligne 8 : 1 ; **ligne 11 : 10** ; ligne 12 : 1 ; **ligne 13 : 10** ; ligne 14 : 3. **Somme : 37.**

**Les deux lignes visees sont exactement a dix, et les neuf autres sont exactement au compte de
s27.** Le lot est **integralement** consacre a la fermeture, comme je l'avais pose en condition.

**Et `HIS-4` a change ce que mon audit coute** : a s27, l'attribution des vingt items aux onze
lignes etait **la mienne, faite a la main, verifiable par personne d'autre**. Elle est aujourd'hui
declaree item par item et je n'ai eu qu'a la **verifier**. C'est la condition que j'avais demandee
le 2026-09-03 ; elle est payee et elle sert.

### 2. `FER-1`, `FER-2`, `FER-3` sur les deux lignes

**Ligne 11 - *"Identifier les caracteristiques d'un lieu de vie et le distinguer d'un autre (espace
urbain, espace periurbain, espace rural)"*.**

- **`FER-1`** : les **trois** elements de la parenthese sont en **bonne reponse**. *Un espace
  urbain* (centre-ville), *un espace rural* (grandes parcelles, fermes espacees) et surtout ***un
  espace periurbain***, qui n'existait qu'en distracteur et qui est desormais la reponse d'un item
  a lui. **Tenue.**
- **`FER-2`** : **10 items**, exactement mon plancher. **Tenue.**
- **`FER-3`** : *nommer le type d'espace a partir d'une description* (quatre items) et *retrouver
  une caracteristique d'un type d'espace donne* (six items : logement, deplacement, commerces,
  activite, densite). La moins servie a quatre. **Tenue.**

**Ligne 13 - *"Identifier d'ou viennent l'eau, l'energie et les aliments consommes, et les etapes de
leur acheminement"*.**

- **`FER-1`** : les trois ressources sont servies, et **l'eau et les aliments, absents jusqu'ici,
  le sont a trois items chacun** (chateau d'eau, origine, traitement ; circuit court, aliment
  importe, aliment de saison). L'energie en porte quatre. **Tenue.**
- **`FER-2`** : **10 items**, exactement mon plancher. **Tenue.**
- **`FER-3`** : *l'origine de la ressource* et *son acheminement jusqu'a nous* - la seconde moitie
  du libelle, qui n'etait pas servie du tout a s27, porte quatre items (chateau d'eau, traitement,
  lignes a haute tension, circuit court). **Tenue.**

### 3. Exactitude - les dix-sept faits neufs, verifies un par un

**Aucune erreur de fait sur les dix-sept.** Ce que j'ai controle et signe, comme condition `HIS-3`
famille (a) - fait de manuel, sans variante, dont je suis une source recevable :

| Fait | Verdict |
|---|---|
| Centre-ville dense et commercant -> espace urbain | juste |
| Pavillons, zones commerciales, en bordure de ville, relies par les transports -> espace periurbain | juste |
| Grandes parcelles, peu d'habitations, fermes espacees -> espace rural | juste |
| Deplacement dominant en espace periurbain -> la voiture | juste |
| Logement dominant en espace periurbain -> la maison individuelle ; en espace urbain -> l'immeuble collectif | juste |
| Densite de population plus elevee en urbain qu'en rural | juste, et l'enonce **definit la densite dans la question** - bien vu pour un CM1 |
| Village cotier, bateaux chaque matin -> la peche | juste |
| Grandes surfaces commerciales -> peripherie, pres des grands axes | juste |
| Eau stockee en hauteur dans une tour -> chateau d'eau | juste |
| Origine de l'eau du robinet -> riviere ou nappe souterraine | juste |
| Etape qui rend l'eau potable -> le traitement de l'eau | juste |
| Aliment achete au producteur, peu d'intermediaires -> circuit court | juste |
| Orange en hiver, venue d'un pays chaud -> aliment importe | juste |
| Recolte et vendu quand il pousse dans sa region -> aliment de saison | juste |
| Helices mues par le vent -> eoliennes | juste |
| Charbon, petrole, gaz naturel, extraits du sous-sol, s'epuisent -> energie fossile | juste |
| Transport de l'electricite sur de longues distances -> lignes a haute tension | juste |

**`HIS-2` - aucun item a deux reponses defendables.** Verifie item par item sur les trente-sept.
**Deux items ont demande un vrai examen** et je dis pourquoi ils passent :

- *"D'ou vient le plus souvent l'eau qui arrive dans les canalisations ?"* -> *d'une riviere ou d'une
  nappe souterraine*. Le distracteur *"de la mer, sans aucun traitement"* est **elimine par sa propre
  formulation** plus que par la geographie ; l'autre (*"de la pluie recueillie dans chaque maison"*)
  est, lui, une representation reelle d'enfant. **Un distracteur fort sur deux : ca passe, ce n'est
  pas le meilleur item du lot.**
- *"Par quoi l'electricite est-elle transportee ?"* -> *des lignes a haute tension*. Un adulte
  objecterait qu'il existe des liaisons souterraines ; le distracteur dit *"canalisations"*, qui
  designe des conduites de fluide et non des cables. **Pas de seconde reponse defendable pour un
  CM1.**

### 4. **`HG-R3` - la reparation que j'avais demandee, verifiee, et je leve la reserve**

L'item de l'espace rural portait deux distracteurs absurdes (*"la banque et l'assurance"*, *"le
tourisme de croisiere"*) et reduisait l'espace rural a l'agriculture seule. **Les trois points sont
payes** :

1. **Les distracteurs sont remplaces** par *l'artisanat local* et *le tourisme vert* - deux
   activites **reellement presentes** en espace rural. L'enfant ne peut plus repondre juste en
   eliminant l'absurde : il doit savoir.
2. **L'enonce est nuance** : *"Dans de nombreux espaces ruraux"*, *"occupe traditionnellement"*. La
   phrase ne pretend plus decrire tous les espaces ruraux d'aujourd'hui, ce qui la rendait fragile
   sur le fond. **Ainsi formulee, elle est un fait de manuel et je la signe.**
3. **Un item neuf montre le contre-exemple** : le village de pecheurs, ou l'activite dominante n'est
   pas agricole. **C'est exactement `BIA-2` etendue aux types d'espace**, et c'est la meilleure
   reponse possible - non pas une nuance ajoutee a la phrase fautive, mais **un item qui enseigne
   que la regle a des exceptions**.

**`HG-R3` est levee.** **Et `BIA-2` est tenue sur les trois types** : urbain (immeubles, densite,
commerces de centre-ville), periurbain (pavillons, voiture, zones commerciales), rural (agriculture
nuancee, paysage de parcelles, peche). **Aucun des trois n'est reduit a un seul trait.**

**`HG-R2` est payee** : la matiere passe de 16 items d'histoire pour 4 de geographie a **16 pour
21**. Le desequilibre que j'avais signale *"a rappeler quand la matiere sera etendue"* est corrige
au premier lot qui pouvait le corriger.

**`BIA-5`** (aucun jugement de valeur sur un personnage) : sans objet sur les dix-sept items neufs,
qui ne parlent d'aucune personne. Aucun item d'histoire n'a ete touche.

**`HIS-1`** : 8 items de restitution sur 37, **21,6 %**, sous le tiers. Compte sur le fichier entier,
seul fichier du lot - donc sur le lot **et** sur le fichier, comme ma precision du 2026-09-03
l'exige.

### 5. Les deux reserves nouvelles

- **`HG-R4` (nouvelle, datee du 2026-09-04, non bloquante) - la charge de lecture monte, et c'est
  la suite directe de `HG-R1`.**

  **Mesure** : a s27, vingt enonces, mediane **13** mots, deux a 26 - j'avais ecrit *"a surveiller
  si un troisieme apparait"*. A s28, trente-sept enonces, **mediane 16**, et **onze des dix-sept
  items neufs depassent 18 mots**, dont un a **34** (le village de pecheurs), un a **30** (la
  densite) et un a **29** (l'orange en hiver).

  **Pourquoi ce n'est pas bloquant** : ce sont des items ou **l'enonce porte la donnee necessaire au
  raisonnement** - on ne peut pas decrire une scene geographique en huit mots -, le vocabulaire est
  celui du programme de cycle 3, et **aucune phrase longue ne cache la reponse**. Un CM1 lit cela.
  **Je ne convertis pas ma propre veille en barre retroactive** : `HG-R1` disait *"non bloquant, a
  surveiller"*, et surveiller n'est pas interdire.

  **Ce que je demande pour la suite, et c'est neuf** : que le prochain lot de cette matiere **ne
  depasse pas 25 mots** sur un enonce, et que la mediane du fichier ne monte plus. Deux
  simplifications gratuites, fournies mot pour mot : le village de pecheurs dit deux fois *"mer"*
  (*"au bord de la mer"*, *"partent chaque matin en mer"*) - la seconde suffit ; l'orange en hiver
  n'a pas besoin de *"transportee par bateau ou par avion"* pour que la question tienne.

- **`HG-R5` (nouvelle, datee du 2026-09-04, non bloquante) - un mot d'indice qui brouille la
  distinction que l'item enseigne.** L'indice du village de pecheurs dit : *"Pense aux bateaux qui
  partent tot le matin et reviennent avec leur recolte du jour."* **Une recolte est agricole**, et
  c'est precisement l'activite dont l'item veut distinguer la peche. Correction fournie mot pour mot
  (regime du mot pour mot, 0,25) : *"Pense aux bateaux qui partent tot le matin et reviennent
  charges de poissons."* L'orthographe et la ponctuation appartiennent au `relecteur-pedagogique`.

### 6. Reserves anterieures - etat au 2026-09-04

- **`HG-R1`** (deux enonces a 26 mots) : **absorbee par `HG-R4`**, qui la mesure a nouveau sur
  trente-sept items. Elle n'est pas levee, elle est **elargie et redatee**.
- **`HG-R2`** (matiere penchee vers l'histoire) : **levee**, section 4.
- **`HG-R3`** (distracteurs absurdes de l'item rural) : **levee**, section 4.

### 7. Ce dont je reste la seule garde sur cette matiere

**Inchange, et je le repete parce que rien ne l'a couvert entre-temps.** L'exactitude factuelle
n'est mordue par aucun test et ne le sera pas. `HIS-2` non plus. **Et `BIA-2` non plus** : le
rapport de `yanis-qa` a trouve qu'un test **portait le nom de `BIA-2` sans garder la propriete** ;
il a ete corrige pour l'anglais, mais **sur cette matiere-ci il n'existe pas** - aucun test ne
compte les traits par type d'espace. **Les dix-sept faits neufs de ce lot ne sont tenus que par ma
lecture**, item par item, et cette lecture ne se rejoue pas toute seule au sprint suivant.

---

## Troisieme passage - increment de s29 (`S29-2`, `63882e0` ; `S29-7`, `6b2edf1`) - **VALIDE SOUS RESERVE**

- **Verdict** : **VALIDE SOUS RESERVE**, prononce le **2026-09-06** par `nadia-prof`, **Instance A**,
  apres avoir joue. **Done pedagogique prononce.**
- **Consequence de matrice** : **`HG-CM1-06`, `HG-CM1-07` et `HG-CM1-14` ferment.** `camille-po` peut
  passer les trois lignes a `couvert-valide`.
- **Reserves** : `HG-R4` et `HG-R5` **levees**, section 6. Deux nouvelles, datees, non bloquantes :
  `HG-R6` et `HG-R7`, section 7.

## **Mention exigee d'avance, a reprendre mot pour mot dans la matrice par `camille-po`**

Sur la ligne **`HG-CM1-14`**, exigee des s27 et rappelee a s28 en composant la variante A :

> *"document textuel seul ; carte et image restent non couvertes"*

**Elle est deja ecrite mot pour mot dans le commentaire du paquet par `marc-dev`**, ce que je
constate et ce dont je le credite : les dix items de la ligne portent tous un `texteSupport` ecrit,
aucune carte, aucune image. **La ligne ferme sur sa part servie**, regime de couverture partielle
deja applique aux fractions (`FRA-9`) et exige d'avance ici.

**Ce que la mention protege** : le libelle de ma ligne dit *"un document (texte, carte, image)"*. Une
ligne verte sans cette mention laisserait croire que le produit apprend a lire une carte. Il ne le
fait pas, et il ne le fera pas avant le palier 2.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `6b2edf1`, jar reconstruit et lance, **parties completes de dix
manches par appels HTTP reels**, **avec erreur volontaire, dont plusieurs poussees jusqu'a la
revelation**. **Et j'ai lu les cinquante-sept items un par un** - les trente des trois lignes que je
ferme, et les vingt-sept autres -, enonces, textes-supports, bonnes reponses, distracteurs et
indices. **C'etait indispensable et c'est le motif que j'avais ecrit** : une partie de dix manches
sur cinquante-sept items en laisse **quarante-sept** que l'enfant ne voit jamais, et c'est comme cela
que j'ai manque un item a s27. **Ce n'est pas un audit degrade.**

**Done technique acquis, mesure par moi et non reconduit** : `bash outils/mvn-jdk21.sh -B clean
verify` sur le **reacteur complet**, **BUILD SUCCESS** ; comptage independant des rapports XML de
tous les modules : **169 rapports, 1259 tests, 0 echec, 0 erreur, 0 ignore**, machine virtuelle
rapportant **21.0.10**.

### 1. `FER-1` - les trois lignes, element par element

**`HG-CM1-06`** - le libelle enumere *(la croisade, la Renaissance, l'edit de Nantes, Versailles)*,
donc **la parenthese fait contrat** :

| Element | Item qui le sert en **bonne reponse** |
|---|---|
| la croisade | *"Quel fait caracterise le regne de Louis IX, aussi appele Saint Louis ?"* |
| la Renaissance | *"Quel fait caracterise le regne de Francois Ier, qui invite en France le peintre Leonard de Vinci ?"* |
| l'edit de Nantes | *"Quel fait caracterise le regne de Henri IV, qui met fin aux guerres de religion en 1598 ?"* |
| Versailles | *"Quel fait caracterise le regne de Louis XIV, surnomme le Roi-Soleil ?"* |

**Quatre sur quatre.** Et `marc-dev` a raison sur un point que je n'avais pas demande et qu'il a
trouve en servant la ligne : les quatre items de s27 associaient bien chaque roi a sa realisation,
mais **avec le roi en bonne reponse**, jamais la realisation. `FER-1`, lue au pied de la lettre,
n'etait donc pas tenue avant ce lot. **Il a lu ma clause mieux que je ne l'avais appliquee, et je
l'ecris.**

**`HG-CM1-07`** - le libelle enumere *(1789, l'abolition des privileges, la Declaration des droits de
l'homme et du citoyen, la fin de la monarchie)* :

| Element | Item qui le sert en **bonne reponse** |
|---|---|
| 1789 | *"En quelle annee commence la Revolution francaise ?"* -> `en 1789` |
| l'abolition des privileges | *"Le 4 aout 1789, l'Assemblee nationale supprime les droits particuliers de la noblesse et du clerge..."* |
| la Declaration des droits de l'homme et du citoyen | deux items, celui de s27 et *"Quel texte est adopte le 26 aout 1789..."* |
| la fin de la monarchie | *"En 1792, les rois cessent de gouverner la France..."* |

**Quatre sur quatre.** *Je le note parce que quelqu'un le demandera : l'Empire n'appartient pas a
cette ligne - c'est `HG-CM1-08`, qui reste `en cours`. Que le sacre de Napoleon n'apparaisse ici
qu'en distracteur est correct.*

**`HG-CM1-14`** - le libelle porte quatre composantes apres son deux-points *(nature, date, auteur,
extraire une information)* :

| Composante | Items |
|---|---|
| nature | 2 (*"Quel type de document est ce texte ?"* -> `un journal intime` ; -> `une declaration de droits`) |
| date | 2 (`en 1789`, `en 1598`) |
| auteur | 2 (`Henri IV`, `les deputes de l'Assemblee nationale`) |
| extraire une information | 4 |

**Les quatre composantes sont servies**, et **la nature et l'auteur sont exactement ce que j'avais
nomme comme manquant a s28**. **`FER-1` tenue sur les trois lignes.**

**Une lecture que je tranche ici plutot que de la laisser trainer** : la parenthese de `HG-CM1-14`
enumere *(texte, carte, image)*, et ma precision du 2026-09-05 dit qu'une parenthese qui enumere fait
contrat. **Je ne l'applique pas ici, et le motif n'est pas la commodite** : cette parenthese-la
enumere des **supports**, pas des reponses, et j'avais moi-meme ecrit a s28, en composant la variante
A, que la ligne fermerait sur *nature* et *auteur* **avec la mention de couverture partielle**. La
barre que j'ai donnee est atteinte ; **la relire plus finement aujourd'hui serait deplacer mon
poteau.**

### 2. `FER-2` - densite, comptee et non estimee

Compte derive des declarations `ligneDeReferentiel` de chaque item (`ADR-0081`, clause 4), sur les
cinquante-sept items du fichier :

| Ligne | Items | Plancher |
|---|---|---|
| `HG-CM1-06` | **10** | 10 |
| `HG-CM1-07` | **10** | 10 |
| `HG-CM1-14` | **10** | 10 |

**Exactement le plancher sur les trois. Tenue.** *Et j'ai verifie que la somme des declarations
retombe sur 57 : 2 + 1 + 1 + 1 + 10 + 10 + 1 + 10 + 1 + 10 + 10.*

### 3. `FER-3` - deux taches par ligne

- **`HG-CM1-06`** : *donner la realisation, nommer le roi* (4 items, herites de s27) contre *donner
  le roi, nommer la realisation* (4 items neufs). **Ce sont bien deux taches** : un enfant restitue
  beaucoup plus facilement le nom d'un roi celebre que la realisation qui le caracterise, et c'est
  precisement le sens que `FER-1` m'a fait demander. Deux items de plus servent une troisieme tache,
  identifier un monument a partir d'une description architecturale (Chambord, la galerie des Glaces).
  **La condition demande deux taches portant chacune au moins trois items : deux en portent quatre.**
  Le troisieme groupe, a deux items, est un **ajout**, pas un manque - lire la clause autrement
  ferait qu'ajouter une tache casserait une condition que huit items en deux taches satisfont, ce qui
  est absurde. **Tenue.**
- **`HG-CM1-07`** : *nommer l'evenement a partir d'une description ou d'une date* (7 items) contre
  *situer l'evenement par rapport aux autres* (3 items : le premier, le dernier, celui qui suit
  l'abolition des privileges). **La minorite est a trois, exactement au plancher.** Je le dis tel
  quel : c'est atteint, pas depasse. **Tenue.**
- **`HG-CM1-14`** : *identifier une propriete du document* - nature, date, auteur - (6 items) contre
  *extraire une information de son contenu* (4 items). **C'est le decoupage que le libelle propose
  lui-meme**, qui groupe les trois premieres et separe la quatrieme par un *et*. **Tenue.**

### 4. Exactitude - les trente items des trois lignes, lus un par un

**Aucune erreur de fait.** Les associations roi/realisation sont justes (Louis IX et la croisade,
Francois Ier et la Renaissance, Henri IV et l'edit de Nantes en 1598, Louis XIV et Versailles) ; la
chronologie revolutionnaire est juste (Bastille le 14 juillet 1789, abolition des privileges le 4
aout, Declaration le 26 aout, fin de la monarchie en 1792) ; l'escalier a double revolution de
Chambord et la galerie des Glaces sont justes et bien attribues.

**Le refus que j'avais pose d'avance est respecte** : ni le chene de Saint Louis ni la poule au pot
d'Henri IV n'apparaissent nulle part - ce sont des traditions, pas des faits, famille (b) de `HIS-3`.
**Verifie par lecture des cinquante-sept items, pas par confiance.**

**`HIS-1`** (au plus un tiers de restitution, sur le lot **et** sur le fichier) : **13 restitutions
sur 57 items, 22,8 %**, plafond a 19. Recompte par moi sur les marqueurs, et j'ai relu la
qualification de chaque item neuf : deux ou trois pourraient etre argumentes en *restitution* plutot
qu'en *association* - *"qui perd ses droits particuliers ?"* -, ce qui porterait le compte a 15 au
pire. **Toujours tres loin du plafond. Tenue.**

**`BIA-5`** : aucun item ne qualifie un roi ni un acteur de la Revolution de bon, mauvais, juste ou
injuste. **Verifie item par item.** Le texte-support sur le lever de Louis XIV decrit un ceremonial
sans le juger : c'est exactement la ligne a tenir, et elle est tenue.

**`CYC-1`** : **vacante** sur ce lot, et non *tenue* - les deux mots ne disent pas la meme chose.
Aucun paquet de questionner le monde CP ne sert un objet d'histoire ou de geographie (`QLM-CP-14` et
`QLM-CP-15` ne sont pas servies). Le constat de `marc-dev` - *"sans objet"* - est correct.

### 5. Lisibilite - mesuree sur les cinquante-sept enonces

**Mediane 16 mots, inchangee depuis s28. Les vingt enonces neufs vont de 6 a 21 mots.** Le vocabulaire
est celui du programme de cycle 3. Les dix textes-supports de la ligne 14 vont de 22 a 41 mots :
c'est long, mais **la longueur y est la matiere de l'exercice** - on ne fait pas comprendre un
document en trois mots.

### 6. `HG-R4` et `HG-R5` - **levees toutes les deux**, et je corrige une lecture qui m'est attribuee

- **`HG-R5`** : l'indice du village de pecheurs disait *"reviennent avec leur recolte du jour"* - une
  recolte est agricole, ce qui brouille la distinction meme que l'item enseigne. Il dit desormais
  *"Pense aux bateaux qui partent tot le matin et reviennent charges de poissons."* **C'est mon texte,
  mot pour mot. Levee.**

- **`HG-R4`** : **levee**, et le motif demande d'etre ecrit sans ambiguite.

  **Ce que ma reserve demandait, mot pour mot** : *"que le prochain lot de cette matiere ne depasse
  pas 25 mots sur un enonce, et que la mediane du fichier ne monte plus"*, plus deux simplifications
  que je fournissais.

  **Ce que je mesure** : les vingt enonces du lot vont de **6 a 21 mots**, aucun au-dessus de 25 ; la
  mediane du fichier est a **16**, identique a s28, elle n'est pas montee ; les deux simplifications
  sont faites, exactement comme je les avais dictees - le village de pecheurs perd son premier
  *"mer"*, l'orange perd *"transportee par bateau ou par avion"*. **Les trois exigences sont tenues.
  Je leve.**

  **`marc-dev` a declare un ecart, et cet ecart n'existe pas contre mon texte.** Il a lu `HG-R4` comme
  *"aucun enonce du fichier ne depasse vingt-cinq mots"* et a signale honnetement que six enonces
  herites y contreviennent, dont un a 30. **Sa lecture est plus severe que la mienne**, et la preuve
  en est dans ma propre prescription : la simplification que j'ai dictee moi-meme pour le village de
  pecheurs laisse l'enonce a **28 mots**. Une barre que ma propre correction ne franchit pas n'est pas
  la barre que j'ai posee. **Je ne convertis pas sa prudence en dette**, et je ne lui compte aucun
  ecart.

  **Constat nouveau et date du 2026-09-06, qui n'ouvre pas de reserve** : le fichier porte encore six
  enonces au-dessus de 25 mots, tous herites, maximum 30, tous sur des lignes deja fermees. Mon motif
  de s28 tient toujours sur eux - *"l'enonce porte la donnee necessaire au raisonnement, aucune phrase
  longue ne cache la reponse, un CM1 lit cela"*. **Rouvrir une reserve sur les memes items, avec la
  meme mesure, apres les avoir juges non bloquants, serait relire mon poteau au lieu de le tenir.** Je
  le constate, je ne le facture pas.

### 7. Reserves nouvelles, datees du 2026-09-06, non bloquantes

- **`HG-R6` - deux items de la ligne 14 offrent une proposition qui s'elimine sans rien savoir.**

  *"Quel type de document est ce texte ?"* propose, dans les **deux** items de nature, la reponse
  **`une carte de France`** - alors que l'enfant a un **texte** sous les yeux, affiche juste au-dessus
  de la question. La proposition ne demande aucune connaissance pour etre ecartee : elle ramene un
  item a trois choix a un item a deux. Ce n'est pas le distracteur absurde de `HG-R3`, qui decrivait
  une activite impossible dans le monde : c'est un distracteur **incoherent avec le support**, ce qui
  est une autre facon de ne rien mesurer.

  S'y ajoute que les deux items de nature se repondent en **recopiant un mot du support** : *"Cher
  journal"* pour `un journal intime`, *"vote une declaration"* pour `une declaration de droits` - et
  l'indice du second le dit lui-meme, *"Le mot qui nomme ce document se trouve dans la phrase"*.
  **La composante *nature*, celle-la meme que j'avais demandee, est donc servie sans que l'enfant ait
  a reconnaitre un genre.**

  **Ce que je demande, sans fournir de texte** : sur les deux items de nature, remplacer `une carte de
  France` par une **forme de document ecrit** que l'enfant doive ecarter en lisant, et faire porter au
  moins un des deux items sur un support **ou le mot de la reponse n'apparait pas**. Je ne dicte pas
  les textes parce qu'il faut ecrire un support neuf, et un support neuf se juge. **Regime : rejeu
  borne, 0,5**, dit d'avance plutot que decouvert en facturant.

  **Pourquoi ce n'est pas bloquant** : les deux bonnes reponses sont **justes**, la ligne porte huit
  autres items qui tiennent, et un item trop facile n'est pas un item faux.

- **`HG-R7` - une inexactitude dans un texte-support de la ligne 14.**

  Le support de l'item sur la Bastille dit : *"Sa chute est fetee dans toute la ville et devient, des
  l'annee suivante, une fete nationale."* **La Fete de la Federation du 14 juillet 1790 a bien eu
  lieu, mais le 14 juillet n'est fete nationale que depuis 1880.** Ecrit tel quel, le produit
  enseigne en passant une date fausse a un enfant de CM1.

  **Ce que je prescris, et je le fournis mot pour mot** (regime du mot pour mot, **0,25**) :

  > `texteSupport` : *"En 1789, des habitants de Paris prennent d'assaut une vieille prison qui
  > servait aussi a enfermer des opposants au roi. Sa chute est fetee dans toute la ville et, l'annee
  > suivante, une grande fete reunit la France entiere pour son anniversaire."*

  **Enonce, bonne reponse, distracteurs et indice inchanges** : la question porte sur ce que
  representait la prison, pas sur la fete. L'orthographe, l'accentuation et la ponctuation
  appartiennent au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).

  **Pourquoi ce n'est pas bloquant, et pourquoi c'est neuf** : le fait faux ne porte **aucune reponse
  attendue** - aucun item ne demande depuis quand le 14 juillet est fete nationale - et il vit dans un
  support, pas dans une question. **Cet item date de s27 et je l'avais valide** : je ne l'avais pas vu,
  la phrase n'engageant pas la reponse. **Je l'ecris comme une reserve neuve et datee, pas comme un
  durcissement**, et je porte le fait que c'est ma lecture de s27 qui l'a laissee passer.

- **`REV-1`** - la phrase de revelation, lue a l'ecran, est agrammaticale sur **un** item du fichier :
  la bonne reponse *"elle suit des regles tres strictes et se deroule en public"* donne *"C'est elle
  suit des regles tres strictes..."*. Cause de portee depot, traitee dans
  `PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`, section 1. Sur les cinquante-six autres
  items du fichier, la phrase se lit correctement.

### 8. Une correction de mon propre compte, avant que quiconque s'en serve

Mon arbitrage de s28, section 3.2, et celui de s29 parlent de **neuf lignes `en cours`** dans cette
matiere. **Apres ce lot, il en reste six** : `HG-CM1-01`, `-02`, `-03`, `-04`, `-08`, `-12`. Les
lignes `-09` et `-10` (carte, planisphere, echelles, lieu de vie) ne sont pas `en cours` du tout :
elles sont **hors de portee du palier 1** et n'ont jamais recu un item ; la ligne `-05` n'a jamais ete
servie non plus. **Le compte a jour appartient a `camille-po`** - je ne touche pas la matrice - mais
je signale que *"neuf lignes en cours"* n'est plus le bon chiffre et qu'il ne doit pas etre recopie.

**Et je maintiens ce que j'avais ecrit sur le mot lui-meme** : cinq de ces lignes portent **exactement
un item**. *En cours* reste le mot le plus optimiste qui soit exact.

### 9. Etat des reserves de cette matiere au 2026-09-06

| Reserve | Etat |
|---|---|
| `HG-R1` (deux enonces a 26 mots, s27) | absorbee par `HG-R4`, **close avec elle** |
| `HG-R2` (matiere penchee vers l'histoire) | levee a s28 |
| `HG-R3` (distracteurs absurdes de l'item rural) | levee a s28 |
| `HG-R4` (charge de lecture) | **levee le 2026-09-06**, section 6 |
| `HG-R5` (mot d'indice qui brouille la distinction) | **levee le 2026-09-06**, section 6 |
| `HG-R6` (distracteur incoherent avec le support, ligne 14) | **ouverte le 2026-09-06** |
| `HG-R7` (inexactitude du support sur le 14 juillet) | **ouverte le 2026-09-06** |
| `REV-1` (phrase de revelation, portee depot) | **ouverte le 2026-09-06**, hors de cette matiere |

### 10. Ce dont je reste la seule garde sur cette matiere

**Inchange, et je le repete parce que rien ne l'a couvert entre-temps.** L'exactitude factuelle des
**cinquante-sept** faits n'est mordue par aucun test et ne le sera pas. `HIS-2` non plus. `BIA-2` non
plus : aucun test ne compte les traits par type d'espace. `FER-1` et `FER-3` non plus - le **compte**
de `FER-2` est derive depuis `ADR-0081`, mais *"quel element du libelle est en position de bonne
reponse"* et *"est-ce la meme operation mentale"* restent des jugements.

**Et j'ajoute une propriete a cette liste, decouverte ce jour** : la **phrase que la mecanique compose
autour d'une bonne reponse** (`REV-1`). Elle n'apparait ni dans le fichier de contenu, ni dans aucun
test, ni dans aucun audit qui ne pousse pas un item jusqu'a la troisieme erreur. **Je ne l'avais
jamais nommee ; elle a traverse tous les sprints de contenu du depot.**


---

# s30 - audit de `nadia-prof`, **instance A**, 2026-09-06

- **Verdicts complets du sprint** :
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`.
- **Passe transverse** : `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`.
- **Arbre audite** : `round-two`, HEAD `dbbfe129`, joue depuis le worktree `s30-nadia-A`, port 8082.
- **Done technique, mesure par moi depuis mon worktree** : `bash outils/mvn-jdk21.sh -B clean verify`
  sur le reacteur complet, **BUILD SUCCESS** ; **179 rapports, 1321 tests, 0 echec, 0 erreur,
  0 ignore**, machine virtuelle rapportant **21.0.10**.
- **Audit degrade sur un seul point, et je le nomme** : j'ai joue **par requetes HTTP**, donc j'ai lu
  le HTML servi et non la page rendue. Cela ne couvre ni la couleur, ni le contraste, ni la taille,
  ni la position ; **aucun jugement de ce bloc n'y repose**.
- **Aucun verdict anterieur n'est efface par ce bloc.**

## Verdict : **VALIDE SOUS RESERVE** - `HG-R6` et `HG-R7` levees, `HG-R8` ouverte

**J'ai joue** `bandit-manchot-histoire-geographie-cm1`, **huit parties completes** jusqu'a l'ecran de
fin, en pressant systematiquement la premiere proposition pour declencher la revelation. **Et j'ai
relu dans le fichier les deux items de nature et l'item de la Bastille**, puisqu'une partie de dix
manches sur cinquante-sept items en laisse quarante-sept que l'enfant ne voit pas - c'est ainsi que
j'ai manque un item a s27.

### `HG-R7` - **LEVEE**, et c'etait l'erreur de fait de mon perimetre

Texte livre :

> *"En 1789, des habitants de Paris prennent d'assaut une vieille prison qui servait aussi a enfermer
> des opposants au roi. Sa chute est fetee dans toute la ville et, l'annee suivante, une grande fete
> reunit la France entiere pour son anniversaire."*

**C'est mon texte, mot pour mot.** Enonce, bonne reponse, distracteurs et indice inchanges, comme je
l'avais exige. **Le produit n'enseigne plus que le 14 juillet est fete nationale depuis 1790** : la
phrase decrit la Fete de la Federation du 14 juillet 1790 sans lui donner un statut qu'elle n'avait
pas, la fete nationale datant de 1880.

**Et j'ai verifie ce que la correction pouvait casser ailleurs**, parce qu'un fait corrige a un
endroit peut en contredire un autre. L'item *"Quelle est la date precise de la prise de la
Bastille ?"* porte l'indice *"C'est la date que la France celebre chaque annee comme fete
nationale."* : **vrai, et desormais non contredit par le support**. Aucun autre item du paquet ne
parle de la fete nationale. **Coherence retablie sur les deux items.**

### `HG-R6` - **LEVEE**, sur les deux moities de ma barre

1. *"Sur les deux items de nature, remplacer `une carte de France` par une forme de document ecrit que
   l'enfant doive ecarter en lisant."* **Fait sur les deux** : *un poeme* face au journal intime,
   *une recette de cuisine* face a la declaration de droits. Deux formes de document ecrit, dont
   aucune ne s'ecarte sans lire le support - a la difference d'une carte, que la seule presence d'un
   texte suffisait a exclure.
2. *"Faire porter au moins un des deux items sur un support ou le mot de la reponse n'apparait pas."*
   **Fait sur l'item de la declaration** : le support ne dit plus *"vote une declaration"* mais
   *"votent un texte solennel qui affirme que tous les hommes naissent et demeurent libres et egaux
   en droits"*. **Le mot qui nomme le genre a disparu** ; l'enfant doit reconnaitre un genre au lieu
   de recopier un mot, ce qui etait le fond de la reserve.

**Deux precisions pour etre exacte, dont aucune n'entraine de demande** :

- Le mot *droits* reste dans le support et dans la reponse. **Je ne l'exige pas parti** : une
  declaration de droits qui ne mentionnerait jamais les droits ne serait plus reconnaissable, et
  j'aurais alors demande l'impossible.
- Sur l'item du journal intime, le support commence toujours par *"Cher journal"*. **Ma barre disait
  *au moins un des deux*, elle est atteinte, et je ne recharge pas l'autre.**

### `HG-R8` - **reserve nouvelle, datee du 2026-09-06, non bloquante**

**Defaut cree par la correction, et c'est exactement ce que je devais chercher en rejouant.** Les deux
indices ont ete reecrits en meme temps que les supports, et **tous deux remettent l'enfant sur le mot
plutot que sur le genre** - c'est-a-dire retablissent, dans l'indice, la facilite que `HG-R6` venait
de retirer du support.

| Item | Indice livre | Ce qu'il fait |
|---|---|---|
| journal intime | *"Repense a la facon dont ce texte commence."* | **designe *"Cher journal"***, la recopie meme que `HG-R6` reprochait |
| declaration de droits | *"Repense a ce que ce texte affirme au sujet des **droits** de chaque etre humain."* | **prononce *droits***, moitie de la bonne reponse, quand **aucun** des deux distracteurs ne contient ce mot |

**C'est `M21`** : un indice qui nomme la reponse est une revelation deguisee.

**Pourquoi ce n'est pas bloquant** : les deux bonnes reponses sont justes, l'indice n'est lu qu'apres
une erreur, et il reste alors deux candidates sur trois. La ligne porte huit autres items qui
tiennent. **Un item trop facile n'est pas un item faux.**

> **`HG-R8`. Propriete a tenir** : sur les deux items de nature de `HG-CM1-14`, l'indice porte **ce
> qui caracterise le genre du document** - a qui il s'adresse, dans quel but il est ecrit, qui
> l'ecrit - et **jamais** le mot que l'enfant a a trouver, ni l'endroit du support ou le copier.
> **Je ne fournis pas les textes** : ecrire un indice de genre est le jugement qu'il faut faire ici.
> **Regime : rejeu borne, 0,5**, dit d'avance plutot que decouvert en facturant.

### `CONS-1` - **non levee sur ce jeu**

Consigne servie : *"Lis bien, puis reponds a la question d'histoire ou de geographie."*

**Elle ne nomme aucune tache.** *Reponds a la question* est ce que la forme de l'ecran dit deja, et
*d'histoire ou de geographie* est le titre du jeu (*Histoire et geographie*), que l'enfant vient de
lire pour arriver la. **C'est la consigne generique dans un autre costume**, et c'est exactement ce
que `CONS-1` existe pour supprimer - ma phrase du 2026-09-06 : *"sur ces dix jeux, la consigne ne
porte aucune tache : elle dit a l'enfant de lire et de choisir, ce que la forme de l'ecran lui dit
deja."*

**Je reconnais que c'est le paquet le plus difficile des dix** - cinquante-sept items, onze lignes de
referentiel, plusieurs taches. **Ce n'est pas une raison de ne rien nommer** : les taches reelles sont
identifiables (situer dans le temps, associer un roi a son fait, lire un document et en extraire une
information), et une consigne peut en nommer deux, comme le font *"Lis, ecris, decompose ou
compare !"* sur les grands nombres et *"Trouve la duree ou l'heure qui manque !"* sur les durees.

> **`CONS-1`, non levee sur cette entree. Regime : rejeu borne, compte dans le reliquat (0,5 pour les
> cinq entrees restantes).**

### Etat des lignes

**`HG-CM1-06`, `HG-CM1-07` et `HG-CM1-14` restent `couvert-valide`**, la ligne 14 avec sa mention de
couverture partielle deja ecrite (*"document textuel seul ; carte et image restent non couvertes"*).
**Aucune ligne ne bouge, ni dans un sens ni dans l'autre.**

### `REV-1` et `REV-3` sur ce paquet

L'item dont la bonne reponse est *"elle suit des regles tres strictes et se deroule en public"* -
**la seule occurrence agrammaticale de ce fichier a s29** - a ete atteint en fin de partie lors de
mes huit parties. **Il se lit correctement** : *"Tu n'as pas trouve."*, puis l'enonce entier, puis
*"La bonne reponse :"*, puis la proposition seule dans son element. **`REV-3` est levee**, et le
detail est en section 8 de mes verdicts de s30.


---

# s30 - reaudit de `CONS-1` par `nadia-prof`, **instance A**, 2026-09-06 (`DEC-0080`)

- **Complement de verdicts** :
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`, section
  *Complement du 2026-09-06*.
- **Arbre audite** : `round-two` a `a91070f8`, **arbre assemble**, worktree `s30-nadia-A2`, port 8082.
- **J'ai rejoue ce jeu** : partie jouee volontairement mal, **la consigne lue a l'ecran au-dessus des
  enonces reellement tires**. **Audit degrade sur le seul point visuel**, et rien ici n'en depend.
- **Aucun verdict anterieur n'est efface par ce bloc.**

## `CONS-1` - **non levee sur cette entree**, et c'est la seule des dix qui reste ouverte

Consigne servie : *"Lis bien, puis situe dans le temps, ou associe un roi a son fait."* - **14 mots**.

### Le comptage, fait par moi sur les 57 items repartis par `ligneDeReferentiel`

| Ce que la consigne annonce | Lignes servies | Items |
|---|---|---|
| *situe dans le temps* | `HG-CM1-01`, `-02`, `-03`, `-04`, `-07`, `-08` | **16** |
| *associe un roi a son fait* | `HG-CM1-06` | **10** |
| **rien** | `HG-CM1-11` (types d'espaces), `-12` (tourisme), `-13` (eau, energie, alimentation) | **21 - toute la geographie** |
| **rien** | `HG-CM1-14` (lire un document et en extraire une information) | **10** |

> **31 items sur 57 ne demandent ni l'une ni l'autre des deux taches annoncees.**

### Vu a l'ecran, en trois parties courtes

La consigne *"situe dans le temps, ou associe un roi a son fait"* s'est affichee au-dessus de :

- *"Dans un espace urbain, quel type de logement est le plus frequent ?"*
- *"Dans de nombreux espaces ruraux, quelle activite occupe traditionnellement le plus grand nombre
  d'habitants qui y travaillent ?"*
- *"Une zone d'habitations pavillonnaires et de zones commerciales [...], comment appelle-t-on cet
  espace ?"*
- *"Un aliment achete directement a un producteur pres de chez soi [...] appartient a quel type de
  circuit ?"*
- *"Qui est l'auteur de ce texte ?"*
- *"D'apres ce texte, en quelle annee ce document est-il signe ?"*

**Aucune n'est un reperage dans le temps ni une association roi-realisation.**

### Le motif

**C'est la propriete de `FOP-R1`**, que j'ai ecrite le 2026-09-06 et appliquee deux fois depuis :
*"la consigne d'un jeu ne decrit pas une tache que la moitie de ses items ne demandent pas."* **Ici
c'est plus de la moitie.** Et le jeu s'appelle *Histoire et geographie* : **la consigne a fait
disparaitre la geographie**, vingt-et-un items, du seul endroit ou l'enfant lisait qu'elle etait
servie.

**La correction a echange un defaut contre un autre, et le second est plus grave.** L'ancienne
consigne ne nommait aucune tache mais **etait vraie sur les 57 items** ; la neuve nomme deux taches et
**est fausse sur 31**.

### Ce que je porte, et il faut que ce soit ecrit

**Ma propre suggestion du 2026-09-06 disait** : *"les taches reelles sont identifiables (situer dans
le temps, associer un roi a son fait, lire un document et en extraire une information)"*. **Elle en
listait trois, dont aucune n'etait la geographie**, alors que le paquet y consacre vingt-et-un items.
`lea-dev` en a repris deux des trois. **Mon exemple etait court, et c'est en partie sur lui que la
consigne a ete ecrite.**

**Cela ne change pas le verdict** - ce n'est pas mon exemple que l'enfant lit, c'est la consigne
servie - **mais cela change la charge** : ce n'est pas une inattention de la developpeuse, c'est un
enonce de barre trop court de ma part, repris de bonne foi.

> **`CONS-1`, non levee sur cette entree. Propriete a tenir, ecrite cette fois avec son comptage** :
> la consigne couvre les **quatre** familles de taches du paquet - se reperer dans le temps (16
> items), associer un roi a son fait (10), decrire un espace ou une ressource (21), lire un document
> (10) - **ou bien elle ne nomme aucune tache plutot que d'en nommer deux qui excluent les
> trente-et-une autres.**
>
> **Une consigne vraie et vague vaut mieux qu'une consigne precise et fausse**, et c'est le seul
> endroit du catalogue ou j'ecris cela : il est le seul paquet a servir onze lignes de referentiel.
>
> **Je ne fournis pas le texte** - quatre familles en une consigne lisible au CM1 est le jugement meme
> qu'il faut faire, et mon dernier essai a coute ce reaudit. **Regime : rejeu borne, 0,5, a s31.**

**`HG-CM1-06`, `-07` et `-14` restent `couvert-valide`**, la ligne 14 avec sa mention de couverture
partielle. **Aucune ligne ne bouge. `HG-R8`, ouverte hier, n'est pas rejouee ici** : elle est au
backlog de s31.

---

# s32 - audit de `nadia-prof`, **instance A**, 2026-09-09 (`S32-1`, `90b01fdd`)

- **Verdict du lot** : **REFUSE**, prononce le **2026-09-09** par `nadia-prof`, **instance A**, apres
  avoir joue. **Le refus est nominatif et il porte ligne par ligne** : `HG-CM1-02` et `HG-CM1-04` ne
  ferment pas, `HG-CM1-01` et `HG-CM1-03` ferment. Le lot lui-meme demandait a etre juge
  *"independamment"* ligne par ligne, et **un refus sur deux lignes ne retire pas deux verdicts
  gagnes sur deux autres.**
- **Ce qui ferme** : `HG-CM1-01` (avec une **mention de couverture partielle**, section 2) et
  `HG-CM1-03` passent `en cours` -> **`couvert-valide`**.
- **Ce qui ne ferme pas** : `HG-CM1-02` (**`HG-R11`**, bloquante) et `HG-CM1-04` (**`HG-R12`**,
  bloquante) **restent `en cours`**.
- **Reserves nouvelles, datees du 2026-09-09** : `HG-R9` et `HG-R10`, non bloquantes ; `HG-R11` et
  `HG-R12`, **bloquantes**. `HG-R8` est **levee** (section 5).
- **`CONS-1` reste non levee sur cette entree**, et `CONS-4` est ouverte (section 6).
- **Relevé complet, avec les mesures et les comptes** :
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`.

## `SCO-1` sur ce jeu - **le score reste parfait par elimination**

*Mention due dans toute fiche que j'ecris a partir de la cloture de s32, par la reserve que j'ai posee
le 2026-09-08.*

**Mesure du 2026-09-09, cinq parties completes, score lu dans la reponse de chaque appui** : le score
atteint **10 / 10 a chaque partie** en pressant les propositions dans l'ordre servi jusqu'a la bonne,
pour **17, 17, 17, 21 et 24 appuis**. Manche 1 de la premiere partie : deux propositions fausses, puis
la bonne, **score 0 -> 1**.

> **`SCO-1` est vivante sur ce jeu.** Le score qu'un enfant rapporte de cette partie **ne distingue pas
> celui qui savait de celui qui a elimine.** Cette mention se retire le jour ou `SCO-1` est verte ; elle
> **n'est pas une condition du Done** et ne remet en cause aucun verdict deja prononce.

## Conditions de l'audit

**J'ai joue.** Jar reconstruit depuis le worktree `s32-nadia-a` (base `fd19d18f`), application lancee
sur un port local.

- **5 parties completes de dix manches, jusqu'a l'ecran *"C'est fini !"***, avec erreurs volontaires,
  dont une manche poussee jusqu'a la revelation.
- **26 parties supplementaires en passe ciblee**, pour voir a l'ecran les items qu'une partie de dix
  manches sur quatre-vingt-douze laisse le plus souvent de cote : l'item de Poitiers, l'item des
  Francs et son indice, les items d'ordre de `HG-CM1-02` et leurs indices. **Les trois defauts que je
  refuse ou reserve ont ete vus servis, pas seulement lus dans le fichier.**
- **Les 40 items des quatre lignes lus un par un** dans le fichier - enonce, bonne reponse,
  distracteurs, indice, marqueur `HIS-1`, marqueur `HIS-3` - parce qu'une partie de dix manches en
  laisse quatre-vingt-deux que l'enfant ne voit pas, et c'est ainsi que j'ai manque un item a s27.
- **Audit degrade sur un point, declare** : j'ai lu le **HTML servi**, pas la page rendue par un
  navigateur. Cela ne couvre ni la couleur, ni le contraste, ni la taille, ni la position. **Aucun
  verdict de ce bloc ne repose sur un jugement visuel.**

## 1. Les conditions, mesurees et non estimees

Comptes refaits par extraction sur le fichier servi, jamais repris de la note du paquet.

| Condition | Mesure | Verdict |
|---|---|---|
| `FER-2` | `HG-CM1-01` 10, `-02` 10, `-03` 10, `-04` 10 items | **tenue sur les quatre** |
| `FER-1` | `-01` : les cinq periodes chacune en bonne reponse ; `-02` : siecle/millenaire et ordonner ; `-03` : Gaulois, Grecs, Romains chacun en bonne reponse ; `-04` : les mouvements **et** leurs effets | **tenue sur les quatre** |
| `FER-3` | `-01` 5/5 ; `-02` 5/5 ; `-03` **3**/7 ; `-04` **3**/7 | **tenue sur les quatre**, deux d'entre elles exactement au plancher |
| `HIS-1` | 17 restitution sur 92 items, **18,5 %**, plafond entier(92/3) = 30 | **tenue** |
| `HIS-2` | les 40 items lus un par un | **tenue** |
| `HIS-3` | les 35 items neufs, marqueur par marqueur | **non tenue sur deux items** : `HG-R11`, `HG-R12` |
| `HG-R4` | mediane du fichier 16, maximum du lot 25, maximum du fichier 30 | **tenue sur le lot**, et je tranche la lecture en section 4 |
| `FAB-1a` | quatre lignes, tache par tache | **tenue** |
| `FAB-1b` | `-01`, `-03`, `-04` | **tenue, et je dis pourquoi** : cinq periodes et trois peuples sont des **categories fermees**, cas que `FAB-1` exclut explicitement - *"c'est licite des lors que la bonne reponse varie"*. Une lecture naive de `FAB-1b` rendrait ces lignes rouges ; ce n'est pas ce que ma clause dit |
| `FAB-1c`, `PLA-1`, `BIA-5` | quatre lignes | **tenues** |

**Le plancher de `FER-3` est trois**, et une consigne fautive a dit quatre. Ce n'est pas ce que j'ai
ecrit le 2026-09-03, ce n'est pas ce que le depot encode (`MINIMUM_PAR_TACHE = 3`), et ce n'est pas ce
que ma matrice dit de `HG-CM1-07` (*"7 contre 3, exactement au plancher"*). **`marc-dev` a construit
sur le standard ecrit du depot et il a signale l'ecart ; rien de ce lot ne se juge sur quatre.**

**Sur le plafond structurel a trois, je confirme une moitie et je corrige l'autre** :

- `HG-CM1-03` : **structurel, confirme.** Le libelle enumere trois peuples ; une quatrieme item de
  nomination repeterait une bonne reponse, ce que `FAB-1a` interdit.
- `HG-CM1-04` : **pas structurel, et je le dis contre la note du paquet.** Le libelle - *"les grands
  mouvements et deplacements de populations du IVe au Xe siecle"* - n'enumere rien et ne ferme aucune
  liste. Cette distinction decide si mon refus a une sortie. **Il en a une** (section 3).

## 2. `HG-CM1-01` - **la ligne ferme**, et une mention de couverture partielle est due

Les cinq periodes de la parenthese sont **chacune servie en bonne reponse** - deux l'etaient avant ce
lot. Les deux taches de `FER-3` different par **l'operation** et non par la phrase, ce qui est le
critere que j'ai ecrit : *ordonner* une suite d'un cote, *rattacher un fait concret a une categorie*
de l'autre. Les cinq faits d'ancrage - art parietal, amphitheatres et gladiateurs, chateaux forts et
douves, Versailles sous Louis XIV, ordinateur et telephone - sont chacun dans la bonne periode.

**`PLA-1` est tenue, et le motif merite d'etre ecrit parce qu'il n'est pas evident.** Un distracteur
comme *"le Moyen Age"* face a *"ordinateur ou telephone"* n'est pas serieux pour un enfant qui sait
quelque chose - mais il **appartient a la categorie fermee que l'item enseigne**, et `PLA-1` vise la
proposition *"qui n'appartient a rien"*.

### **Mention exigee, a reprendre mot pour mot dans la matrice par `camille-po`**

Le libelle dit *"Situer et ordonner les grandes periodes historiques **sur une frise**"*. **Le produit
ne sert aucune frise et n'en servira pas au palier 1.** C'est le regime de `FER-1` - *"la ligne ne
ferme alors que sur sa part servie, avec la mention de couverture partielle ecrite dans la matrice"* -
et c'est ce que j'ai deja fait sur `QLM-CP-14`, dont le libelle porte la meme frise, et sur
`HG-CM1-14`, fermee *"sur sa part servie"*. **Je m'applique mon precedent, je n'invente pas une
exigence pour cette ligne-ci.**

**Aucune mention n'etait pre-ecrite pour cette ligne** - celle que `FER-1` annonce *"exigee d'avance
sur la frise"* est celle de `QLM-CP-14`, dans la matrice depuis le 2026-09-02. **Je l'ecris donc
aujourd'hui et j'en suis la source** :

> *"ordre et identification des cinq grandes periodes par la lecture seule ; le placement sur une
> frise reste non couvert."*

## 3. Les deux refus, avec leur motif et sa sortie

### `HG-R11` - **bloquante**, `HG-CM1-02` : *"le sacre de Clovis"* n'a jamais eu lieu

**Vu a l'ecran le 2026-09-09, deuxieme partie, manche 2.** Item 70, **bonne reponse** : *"le sacre de
Clovis, roi des Francs, en 481"*. Item 71, **distracteur** : le meme texte.

**Le fait est faux, et il l'est deux fois.** En 481, Clovis **succede** a son pere Childeric et devient
roi des Francs saliens de Tournai : aucune ceremonie, et il n'y en aura jamais pour lui. **Le sacre
d'un roi franc commence avec Pepin le Bref en 751**, ce que le programme de cycle 3 enseigne ; de
Clovis, on enseigne le **bapteme**, vers 496, que ce paquet ne mentionne nulle part.

**Le meme fichier dit la chose juste plus haut** : l'item 66 porte *"Clovis devient roi des Francs en
481"*, et je l'ai valide. **Le paquet se contredit sur son propre fait.** Et le marqueur `HIS-3` source
*"dates 481, 800 et 1789, consensuelles, famille (a)"* : la **date** est sourcee et juste, l'**evenement**
que l'item nomme ne l'est pas. **Un marqueur qui source autre chose que ce que l'item dit ne tient pas
`HIS-3`.**

**Pourquoi c'est un refus et non une reserve, et ce n'est pas un durcissement.** Ma doctrine est ecrite
depuis le 2026-09-06, dans le bloc `ANG-R2` de `FICHE-bandit-manchot-anglais-cm1.md` : *"Pourquoi ce
n'est pas un refus, alors qu'une erreur de fait est chez moi un motif de refus a elle seule. **Aucune
reponse attendue ne depend de cette phrase** : elle vit dans une consigne, pas dans une question."*
C'est le meme motif qui a fait de `HG-R7` une reserve non bloquante, ligne fermee.

> **Ici, la reponse attendue *est* la phrase fausse.** L'enfant qui joue juste est recompense pour avoir
> choisi un evenement qui n'existe pas ; l'enfant qui joue faux se le voit presenter comme ecarte pour
> une raison de **date**, ce qui le confirme comme evenement. **La condition de ma propre doctrine est
> remplie dans le sens du refus. Je l'applique la ou je l'avais posee.**
>
> **`HG-R11`. Ce qui la leve** : reprendre, aux items 70 et 71, la formulation que l'item 66 du meme
> fichier porte deja - *"Clovis devient roi des Francs, en 481"*. **Le texte existe dans le depot** ;
> ni la date, ni la tache, ni les autres propositions n'ont a changer. **Regime : mot pour mot, 0,25.**

### `HG-R12` - **bloquante**, `HG-CM1-04` : *"les Sarrasins"* en reponse a *"Quel peuple ?"*

**Vu a l'ecran le 2026-09-09** : *"En 732, une armee venue du sud est arretee pres de Poitiers par
Charles Martel. **Quel peuple** menait cette armee ?"* -> **`les Sarrasins`**, contre *"les Vikings"* et
*"les Romains"*.

`marc-dev` a signale le terme et l'a laisse a mon arbitrage. **Je l'arbitre, et je ne le signe pas.**
Deux motifs, le premier suffit.

**1. `HIS-3`, famille (b) - ma propre precision du 2026-09-03**, qui distingue *"(a) fait de manuel sans
variante - elle en est une source recevable et signe ; (b) fait date, dispute, ou dont la formulation
engage une couverture - elle ne signe pas, il faut une source ou une reformulation qui retire le besoin
de source."* **Une designation que le developpeur route lui-meme a mon arbitrage est, par construction,
de la famille (b).** Je n'ai pas d'outil de recherche en ligne cette session, pas plus que lui, et
`HIS-3` est **bloquante** sur cette matiere par declaration du referentiel et de la matrice.

**2. Le fond, et il ne depend d'aucune source.** *Sarrasins* n'est pas le nom d'un peuple : c'est une
designation medievale chretienne, appliquee globalement aux musulmans, qu'aucun groupe n'a jamais
revendiquee pour lui-meme ; l'armee de 732 est celle du gouverneur omeyyade d'al-Andalus, aux troupes
arabes et berberes. **La question demande un *peuple* et la reponse n'en nomme pas.** Ce n'est donc pas
un debat sur un mot traditionnel : c'est un desaccord de nature entre la question et la reponse qu'elle
recompense. Et le contexte le rend plus net : **les huit autres items de ces deux lignes nomment des
peuples reels** - Romains, Gaulois, Grecs, Francs, Vikings, peuples germaniques. **Le seul qui n'en
nomme pas est celui qui designe des musulmans.** `BIA-5` ne mord pas ici - elle interdit le jugement de
valeur sur un **personnage** - et je ne l'invoque pas.

> **`HG-R12`. Propriete a tenir, et je ne fournis pas le texte** - ecrire cet item est le jugement qu'il
> faut faire. Sur `HG-CM1-04`, aucune bonne reponse ne designe un groupe humain par un exonyme qu'aucun
> groupe ne revendique. Deux directions me conviennent : **soit l'item nomme l'armee par son origine**
> plutot que par un nom de peuple, **soit l'item cesse de porter sur l'identite de l'armee** et porte
> sur le mouvement lui-meme, comme le font deja les items 90 et 92 de la meme ligne.
>
> **Et la sortie existe, contrairement a ce que la note du paquet laisse croire** : la tache de
> nomination est a trois items, mais son plafond **n'est pas structurel** sur cette ligne. Un quatrieme
> mouvement du IVe-Xe siecle s'enseigne : l'installation des Bretons venus de Grande-Bretagne en
> Armorique. **Le refus ne ferme pas la ligne, il demande un item.** **Regime : rejeu borne, 0,5.**

## 4. Les deux reserves non bloquantes, et une lecture que je tranche

### `HG-R9` - `HG-CM1-02` : les cinq indices de la tache *ordonner* designent la reponse

| Item | Indice servi apres une erreur | Ce qu'il fait |
|---|---|---|
| 70 | *"Repense a la date la plus ancienne des trois : 481."* | **une seule proposition porte 481** |
| 71 | *"Repense a la date la plus recente des trois : 1789."* | idem |
| 72, 73, 74 | meme forme, avec 800, 1804, 1598 | idem |

**C'est `M21`, et c'est la famille exacte de `HG-R8`.** L'indice ne nomme pas la reponse, il la
**designe sans ambiguite** - ce qui revient au meme puisque chaque proposition affiche sa date.

**Non bloquante** : les cinq bonnes reponses sont justes, l'indice n'est lu qu'apres une erreur, et je
me tiens a `HG-R8` - *"un item trop facile n'est pas un item faux"*. **Mais je nomme ce que cela coute,
parce que c'est plus large que `HG-R8` ne l'etait** : `HG-R8` portait sur deux items d'une ligne de dix,
celui-ci porte sur **la totalite de la seconde tache**, c'est-a-dire sur la moitie que `FER-3` compte.

> **`HG-R9`. Propriete a tenir** : sur les items d'ordre de `HG-CM1-02`, l'indice porte **la methode** -
> ce qu'il faut comparer, comment on situe une date par rapport a une autre - et **jamais la date de la
> bonne reponse** quand chaque proposition affiche la sienne. **Je ne fournis pas les textes.**
> **Regime : rejeu borne, 0,5.**

### `HG-R10` - `HG-CM1-04` : un indice faux sur la meme annee 481

**Vu a l'ecran apres deux erreurs sur l'item 87** (*"...fondent un royaume qui deviendra un grand pays
d'Europe. Lequel ?"* -> *"la France"*) : *"Leur roi Clovis unifie ce royaume en 481."*

**Faux.** 481 est l'annee ou Clovis devient roi d'un petit royaume franc autour de Tournai ;
l'unification vient de Soissons (486) et de Vouille (507). **C'est le troisieme endroit de ce fichier ou
l'annee 481 se voit attribuer un evenement qu'elle ne porte pas.**

**Non bloquante, et je m'applique mon precedent plutot que de durcir parce que c'est le meme sprint** :
l'indice n'est pas une reponse attendue, et `HG-R7` comme `ANG-R2` ont ete traitees en reserve pour ce
motif exact. **Elle doit neanmoins etre corrigee avant que la ligne ferme, la ligne etant refusee par
ailleurs.** **Regime : mot pour mot, 0,25** - la formulation juste est celle de l'item 66.

### La lecture de `HG-R4` que je tranche, et l'enonce fautif est le mien

Mesure du jour sur les 92 enonces, mot par mot, espaces insecables compris : mediane **16**, maximum du
fichier **30**, maximum des 35 items neufs **25**, maximum de mes quatre lignes **27**.

**Les quatre enonces qui depassent vingt-cinq mots sont tous anterieurs au lot, et celui de 27 mots qui
tombe sur `HG-CM1-04` est mon propre texte** - la reformulation que j'ai fournie mot pour mot le
2026-09-03 pour lever ma reserve bloquante sur l'item 5.

> **Je tranche la question que `marc-dev` m'a routee a s29 : `HG-R4` vise le lot livre, pas le fichier
> entier.** Sous cette lecture, `HG-R4` est **tenue** sur ce lot. Je retiens la plus indulgente des deux
> lectures qu'il proposait, et le motif est celui-la : **la lecture severe se retourne d'abord contre mon
> propre texte**, et une barre dont l'auteur s'exonere n'est pas une barre.

### Un point verifie qui **n'est pas** un defaut, dit pour qu'un tiers ne le compte pas comme tel

Item 67 : *"Charlemagne est couronne empereur en 800. En quel siecle cela se passe-t-il ?"* -> *"le VIIIe
siecle"*. **C'est juste** : 800 est la derniere annee du VIIIe siecle (701-800), la meme convention qui
met 1789 au XVIIIe, et l'indice l'ecrit. C'est le **seul item du paquet sur une annee en 00**, donc le
seul ou la regle scolaire usuelle donne le distracteur ; l'indice explique l'intervalle, donc l'erreur
enseigne quelque chose. **Je ne l'ouvre pas en reserve** ; je l'ecris pour qu'il ne se relise pas comme
une erreur de fait.

## 5. `HG-R8` - **LEVEE**

Reserve ouverte le 2026-09-06 : sur les deux items de nature de `HG-CM1-14`, l'indice remettait l'enfant
sur le **mot** plutot que sur le **genre** du document - l'un designait *"Cher journal"*, l'autre
prononcait *droits*.

**Textes livres, lus dans le fichier et vus servis :**

| Item | Indice servi aujourd'hui | Ce qu'il fait |
|---|---|---|
| 51, *"un journal intime"* | *"Une seule personne y raconte, jour apres jour, ce qu'elle vit et ressent, pour elle-meme."* | decrit **qui ecrit, pour qui, dans quel but** ; ne designe plus *"Cher journal"* ni l'endroit du support ou le copier |
| 53, *"une declaration de droits"* | *"Plusieurs personnes reunies proclament ensemble, dans un texte solennel, ce qui doit valoir pour tous."* | decrit le genre ; **le mot *droits* a disparu de l'indice**, alors qu'aucun distracteur ne le portait |

**Ma barre disait** : *"l'indice porte ce qui caracterise le genre du document - a qui il s'adresse, dans
quel but il est ecrit, qui l'ecrit - et jamais le mot que l'enfant a a trouver, ni l'endroit du support
ou le copier."* **Elle est atteinte sur les deux items. Je leve `HG-R8`.**

**Et je ne recharge pas la barre sur autre chose, alors que je pourrais.** L'indice de l'item 53 reprend
*"un texte solennel"*, expression que le texte-support porte deja. **Ce n'est pas ce que j'avais
interdit** : le mot a trouver est *declaration*, et il n'y est pas. **Une equipe qui atteint la barre que
j'ai ecrite recupere sa ligne, meme si je vois desormais mieux.** Si je voulais plus, ce serait une
reserve neuve et datee, pas un durcissement de celle-ci - et je n'en veux pas plus.

**`HG-CM1-14` reste `couvert-valide`** avec sa mention deja ecrite (*"document textuel seul ; carte et
image restent non couvertes"*). **Aucune ligne deja fermee ne bouge, ni dans un sens ni dans l'autre.**

## 6. `CONS-1` - **non levee**, et `CONS-4` ouverte

**Consigne servie, relevee a chacune de mes 31 parties** : *"Lis bien, puis situe dans le temps, nomme
un peuple ou un mouvement de population, associe un roi a son fait, decris un espace ou une ressource,
ou lis un document."*

**La clause neuve fait ce qu'elle annonce, et je le porte au credit du lot** : les items que je citais le
2026-09-08 comme portant une tache non nommee sur `HG-CM1-03` et `HG-CM1-04` le sont desormais.

**Et le lot a cree, dans le meme geste, quatorze items dont la tache n'est nommee par aucune des cinq
clauses** : les items 77 a 83 de `HG-CM1-03` (nommer **ce qu'un peuple a laisse** - des arenes, un
aqueduc, un reseau de routes, une colonie, la vigne, le fer, des mots de vocabulaire) et les items 86 a
92 de `HG-CM1-04` (dire **un effet du mouvement sur le territoire**). **Ce sont les taches majoritaires
des deux lignes que le lot voulait fermer.** Et les trois que je citais deja restent non nommees :
nommer un texte (item 49), nommer une installation (item 35), nommer un regime politique (item 13).

> **`CONS-1` reste non levee sur cette entree.** Le compte d'items dont la tache n'est pas nommee est
> **plus grand apres le lot qu'avant** : deux nommes, quatorze crees. **Ce n'est pas un reproche au lot**
> - refondre la consigne n'etait pas son objet - **c'est le constat que la methode ne converge pas.** Je
> ne donne pas de pourcentage : l'attribution item par tache est la mienne, faite a la main, et un
> chiffre que personne ne peut refaire ne vaut pas mieux qu'aucun chiffre.

### `CONS-4` - reserve nouvelle, datee du 2026-09-09, non bloquante, **portee D**

| Arbre | La consigne de cette entree | Mots | Taches nommees |
|---|---|---|---|
| s27-s30 | *"Lis bien, puis reponds a la question d'histoire ou de geographie."* | 12 | **0** |
| s31 | quatre taches | 22 | 4 |
| s32 | cinq taches | **31** | 5 |

**La consigne grossit d'une clause par sprint**, consequence arithmetique de payer `CONS-1` sur un jeu
unique qui sert onze lignes de referentiel. **Et l'objet de `CONS-1` cesse d'etre atteint en chemin** :
une enumeration de cinq taches alternatives en 31 mots ne dit toujours pas a l'enfant ce qu'il doit
faire, puisque la tache est decidee par l'item qu'il a devant lui et non choisie par lui dans une liste
lue avant de commencer. **L'enfant lit 31 mots pour n'en retenir aucun.**

> **`CONS-4`. Propriete a tenir** : la consigne d'un jeu nomme une tache que l'enfant reconnaitra dans la
> manche qu'il a devant lui. **La cause n'est pas la consigne, c'est le grain du jeu.** `marc-dev` a route
> ce decoupage a s28 en disant que le nombre et le grain des jeux sont une decision de contenu
> qu'`ADR-0081` reserve a `camille-po` et a moi ; **il a raison, et la question m'est revenue par un autre
> chemin.** **Je ne tranche pas le decoupage ici** - ce serait fixer un perimetre, et `ADR-0081` le confie
> a `camille-po` et a moi ensemble. **Route a `camille-po` via `sofia-sm`**, deux issues : ou la consigne
> cesse d'enumerer, ou le jeu se scinde. **Regime : je ne le chiffre pas, le geste n'est pas de mon
> ressort.**

## 7. Etat des lignes et des reserves de cette matiere au 2026-09-09

| Ligne | Avant | Apres | Mention |
|---|---|---|---|
| `HG-CM1-01` | `en cours` | **`couvert-valide`** | **due** : *"ordre et identification des cinq grandes periodes par la lecture seule ; le placement sur une frise reste non couvert."* |
| `HG-CM1-02` | `en cours` | **`en cours`** (refusee, `HG-R11`) | - |
| `HG-CM1-03` | `en cours` | **`couvert-valide`** | **aucune n'est due** |
| `HG-CM1-04` | `en cours` | **`en cours`** (refusee, `HG-R12`) | - |
| `-06`, `-07`, `-11`, `-13`, `-14` | `couvert-valide` | **inchangees** | celle de `-14` inchangee |
| `-08`, `-12` | `en cours` | **inchangees**, non touchees par ce lot | - |
| `-05`, `-09`, `-10` | `non couvert` | **inchangees** | - |

**Bilan de la matiere : 7 `couvert-valide`, 4 `en cours`, 3 `non couvert`, 14 au total.**

| Reserve | Etat au 2026-09-09 |
|---|---|
| `HG-R1` a `HG-R7` | **levees** (s28, s29, s30) |
| `HG-R8` | **levee ce jour** (section 5) |
| `HG-R9` | **ouverte**, non bloquante, rejeu borne 0,5 |
| `HG-R10` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `HG-R11` | **ouverte, BLOQUANTE**, mot pour mot 0,25 |
| `HG-R12` | **ouverte, BLOQUANTE**, rejeu borne 0,5 |
| `CONS-1` | **non levee** sur cette entree |
| `CONS-4` | **ouverte**, non bloquante, non chiffree |

## 8. Ce dont je reste la seule garde sur cette matiere

- **L'exactitude factuelle** - et les deux refus de ce bloc en sont la preuve : **les tests neufs du lot
  sont verts sur `HG-CM1-02` et sur `HG-CM1-04`.** `EXA-1`, que j'ecris ce jour, ne rend pas
  l'exactitude mordable : elle dit seulement ce qu'on fait d'une inexactitude quand quelqu'un la trouve.
- **`HIS-2`** et **`HIS-3` famille (b)** - aucune n'est mordable, et la seconde est ce qui refuse
  `HG-CM1-04`.
- **`PLA-1`** - la plausibilite pour un enfant de neuf ans ne se calcule pas.
- **`CONS-4`** - propriete de portee D qu'aucun audit de lot ne voit.
- **`FER-3` sur `HG-CM1-01` et `-02`** : **desormais mordue, mais par un proxy plus etroit que ma
  clause.** Les tests neufs comptent les items dont l'enonce **commence par** *"Quelle grande periode
  vient juste"* ou *"Parmi ces trois"* ; ma clause porte sur **l'operation mentale**, pas sur l'amorce de
  la phrase. **Le proxy derivera** le jour ou un item d'une autre operation commencera par ces mots. Je
  le nomme plutot que de laisser croire que la clause est encodee.
- **`SCO-1`** : entierement automatisable, **et rien n'est ecrit**. Voir le bloc de mention en tete.

---

# Cinquieme passage - increment de s33 (`S33-HG-02` et `S33-HG-08-12`, `4adc751f` ; `S33-HG-04`, `30726ecb`) - **VALIDE SOUS RESERVE sur trois lignes, REFUSE sur une**

> Bloc de validation pedagogique - **`nadia-prof` UNIQUEMENT, instance A de s33**. Il n'est jamais
> pre-rempli ni anticipe par un autre agent. **Les blocs de s27 a s32 ci-dessus ne sont pas
> retouches** : un refus efface n'a aucune valeur de preuve, et ceux de s32 sur `HG-CM1-02` et
> `HG-CM1-04` restent lisibles avec leur date et leur motif sous les levees d'aujourd'hui.
>
> **Le detail complet de cet audit vit dans
> `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-A.md`.** Ce bloc
> porte les verdicts, les levees et les reserves neuves ; il ne recopie pas les mesures.

## Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `b45e5927`, jar de l'arbre fusionne lance sur un port local,
**vingt-huit parties completes de dix manches, 280 manches servies**, chaque proposition fausse
pressee avant la bonne, plus un chemin de revelation joue a part. **Verifie avant de jouer** que le
paquet servi par ce jar est bien celui d'apres la fusion de la section 14 du planning : 110 items, et
l'item 85 y porte *"D'ou venait cette armee ?"*.

**Degrade sur un point, declare comme a s32** : j'ai joue par requetes HTTP reelles, donc j'ai lu le
HTML servi et non la page rendue par un navigateur. **Ni la couleur, ni le contraste, ni la taille, ni
la position a l'ecran ne sont couverts, et aucun verdict de ce bloc n'en depend.**

## 1. Les quatre verdicts

| Ligne | Story | Verdict | Etat de matrice demande |
|---|---|---|---|
| `HG-CM1-02` | `S33-HG-02` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** |
| `HG-CM1-04` | `S33-HG-04` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** |
| `HG-CM1-08` | `S33-HG-08-12` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** |
| `HG-CM1-12` | `S33-HG-08-12` | **REFUSE** (`HG-R17`, bloquante) | **reste `en cours`** |

**Aucune mention de couverture partielle n'est due sur ces quatre lignes.** Celles deja ecrites sur
`HG-CM1-01` et `HG-CM1-14` sont inchangees. **Le mouvement de la matrice appartient a `camille-po` et
a la review ; je ne l'ecris pas.**

## 2. Quatre reserves levees

- **`HG-R11`, bloquante - LEVEE.** *"le sacre de Clovis, roi des Francs, en 481"* a disparu des items
  70 et 71, remplace par la formulation de l'item 66 que ma reserve dictait mot pour mot. **Verifie
  sur le fichier entier : plus aucune occurrence.**
- **`HG-R12`, bloquante - LEVEE.** L'item 85 demande desormais *"D'ou venait cette armee ?"* ->
  *"d'Espagne"*, **la premiere des deux directions que ma reserve nommait**. Aucune bonne reponse de
  `HG-CM1-04` ne designe plus un groupe humain par un exonyme qu'aucun groupe ne revendique. **Je ne
  demande pas le quatrieme item** : je l'avais nomme pour montrer que la sortie existait, pas pour
  l'exiger, et la direction retenue garde l'item dans sa tache. *"La peninsule Iberique"*, que j'avais
  relevee sans lui allouer de code, a disparu du meme geste.
- **`HG-R9` - LEVEE.** Les cinq indices de la tache d'ordre portent la methode (*"Compare les trois
  dates : cherche celle qui est arrivee en premier."*) et **aucun ne porte plus de date**.
- **`HG-R10` - LEVEE.** L'indice de l'item 87 n'attribue plus a 481 l'unification du royaume.

## 3. Deux corrections a mes propres constats, avant de m'en servir

**Deux des quatre reserves payees ici portaient un texte que j'avais dicte moi-meme.** `marc-dev` a
applique exactement ce que j'avais ecrit ; ce que ces textes produisent est donc a moi.

1. **Le texte de `HG-R11` fabrique une proposition qui ne repond pas a sa question.** *"Clovis devient
   roi des Francs, en 481"* est une phrase a verbe conjugue placee parmi des groupes nominaux, dans
   une liste qui repond a *"lequel a eu lieu le plus tot ?"*. **Je leve `HG-R11` et j'ouvre `HG-R13`,
   neuve et datee, non bloquante. Je ne durcis pas la reserve levee.**
2. **Le texte de `HG-R10` fabrique un indice qui n'ajoute presque rien** au choix de l'item 87. Il
   oriente encore - le chemin *Francs* vers *France* est celui que l'enfant doit prendre - **donc je
   n'ouvre rien**, et je l'ecris pour que personne ne le releve plus tard comme un defaut manque.

**Et un point que je ne rouvre pas** : les cinq indices *"En quel siecle ?"* de `HG-CM1-02` nomment la
bonne reponse. **J'ai regarde exactement ces indices a s32 et j'ai ecrit qu'ils ne sont pas un
defaut**, l'indice enseignant la regle du siecle. Je m'y tiens.

## 4. `HG-CM1-08` - ce qui la ferme, et les trois reserves qui restent

**Mes deux refus d'avance sur cette ligne sont evites.** La seconde moitie du libelle - *ce que
Napoleon Bonaparte y change* - est servie **en bonne reponse par cinq items** (le Code civil, des
lycees, la Legion d'honneur, un prefet, la Banque de France), contre cinq sur le passage. `FER-1` sur
les deux moities, `FER-2` a dix items, `FER-3` a cinq contre cinq : tenues. **La charge de lecture n'a
pas bouge** : mediane du paquet a 16 mots avant comme apres les dix-huit items neufs.

**Les deux lectures de `HG-R4` tiennent toutes les deux, et je le dis plutot que de choisir apres
coup** : celle que j'ai tranchee a s30 (*le lot livre*) donne un maximum de 19 mots sur les items
neufs ; celle que mon refus d'avance de s33 ecrit (*la mediane du paquet*) donne 16 avant et 16 apres.
**Aucune des deux n'est mise en defaut**, et je ne reecris pas l'une pour l'autre.

**Exactitude** : les neuf faits neufs sont justes, un par un. Les distracteurs sont des candidats
serieux du meme genre que la bonne reponse **sur les dix items** - *"la Declaration des droits de
l'homme et du citoyen"* contre *"le Code civil"*, *"un maire"* et *"un depute"* contre *"un prefet"*.

- **`HG-R14` - neuve, datee du 2026-09-10, non bloquante.** *"Avant de devenir empereur en 1804, quel
  regime la Revolution avait-elle installe en France ?"* : le seul sujet de la phrase est *la
  Revolution*, et **Napoleon Bonaparte n'est nomme nulle part dans l'item**. A la lettre, l'enonce dit
  que la Revolution devient empereur. **C'est la classe de defaut que la retro de s32 a nommee** - une
  phrase remontee sans que le francais soit repris autour. **Propriete a tenir** : l'enonce nomme
  celui qui devient empereur, ou renonce a sa subordonnee. **Regime : mot pour mot, 0,25.**
- **`HG-R15` - neuve, datee du 2026-09-10, non bloquante.** L'indice du Code civil - *"Il porte encore
  aujourd'hui le nom de son createur dans plusieurs pays."* - **ne designe aucune des trois
  propositions** : aucune ne porte un nom de personne. L'enfant qui s'est trompe ne peut pas avancer.
  L'indice dit en outre *"son createur"* la ou l'enonce ecrit soigneusement *"fait rediger"*.
  **Propriete a tenir** : l'indice nomme une propriete qui distingue *le Code civil* des deux autres.
  **Regime : mot pour mot, 0,25.**
- **`HG-R16` - neuve, datee du 2026-09-10, non bloquante.** *"une nouvelle banque chargee de fabriquer
  la monnaie"* : la Banque de France de 1800 n'est pas chargee de fabriquer la monnaie ; elle recoit
  le privilege d'emettre des billets a Paris en 1803, et la frappe des pieces reste une affaire
  d'Etat. **Pourquoi ce n'est pas un refus, alors qu'une erreur de fait est chez moi un motif de refus
  a elle seule** : **la reponse attendue ne depend pas de cette phrase** - l'enfant repond *"la Banque
  de France"* dans les deux cas. C'est le motif exact de `HG-R7` et de `ANG-R2`, et je l'applique dans
  le sens de la reserve. **Propriete a tenir** : l'enonce decrit une mission qu'elle a reellement
  recue a sa creation, ou renonce a en decrire une. **Regime : mot pour mot, 0,25.**

## 5. `HG-CM1-12` - **REFUSEE**, et je dis d'abord ce que le refus ne porte pas sur

- **`HIS-5`, ma frontiere ecrite avant le premier item, est respectee.** Aucun des neuf items neufs ne
  reprend la cellule *se loger dans un espace urbain*, deja depensee sur `HG-CM1-11`, ligne fermee.
  **Verifie par moi sur les vingt items des deux lignes, et non sur le test** : le test n'ecarte
  qu'une chaine de caracteres, ma frontiere porte sur la cellule. **Mon refus d'avance numero 1 est
  evite.**
- **Les six cellules libres sont servies, et *on se cultive* la premiere** - un musee, des monuments
  historiques, un festival. C'etait celle des quatre que je disais que le lot oublierait. **Mon refus
  d'avance numero 2 est evite.**
- `FER-1`, `FER-2` a dix items, `FER-3` a quatre contre six : tenues. **Aucune inexactitude factuelle
  sur les neuf items neufs.**

### `HG-R17` - **reserve neuve, datee du 2026-09-10, BLOQUANTE** : le registre des distracteurs est le discriminant de la ligne entiere

**Compte, avec sa methode.** Attribution faite a la main par moi, sur les dix-huit distracteurs neufs
de la ligne, sur le fichier servi : **quatorze appartiennent au registre de la ferme, du champ, de la
peche ou de la batellerie** - *une ferme isolee*, *l'agriculture*, *la peche*, *dans une ferme*, *un
bateau de peche* (trois fois), *des champs cultives*, *le tracteur*, *une peniche*, *une ferme
d'elevage*, *dans un champ*, *une moisson*, *une peche en mer*. **Et aucune des dix bonnes reponses de
la ligne n'y appartient - zero sur dix.** Le detail item par item est dans les verdicts de l'instance
A, section 5.1.

**Devant l'ecran, cela donne une regle** : *la ferme, le champ, le bateau de peche et la moisson ne
sont jamais la bonne reponse*. **Un enfant qui a joue deux parties repond aux neuf items sans lire les
questions.**

**Le corollaire, qui est le meme fait vu de l'autre cote** : les distracteurs ne sont pas des
candidats. *"Sur un bateau de peche"* n'est pas une reponse a *"ou travaillent les employes d'une
banque ?"*. **Sur au plus deux des neuf items neufs l'enfant a devant lui un distracteur serieux du
meme genre que la bonne reponse** - contre dix sur dix pour `HG-CM1-08`, ecrit par le meme
developpeur dans le meme commit.

**Deux items ajoutent un appariement de mots par-dessus** : *"riche en histoire"* -> *"des monuments
historiques"*, et *"quel logement loue-t-on"* -> *"un chalet ou un appartement de location"*.

**Ce que le refus ne dit pas** : la ligne porte sur l'espace urbain et l'espace touristique, donc **il
est normal qu'une reponse rurale y soit fausse**. Le defaut est qu'elles soient **les seules** a
l'etre, systematiquement, de sorte que la faussete se lit dans le registre sans passer par la
question.

**Pourquoi c'est un refus, et pourquoi ce n'est pas un durcissement de `PLA-1`, de `CAT-1` ni de
`ANG-R3`.**

- **`ANG-R3`, le 2026-09-09** : trois items sur cinq etaient unaires et j'ai ferme la ligne. **Mon
  motif ecrit etait** : *"les sept autres items de la ligne tiennent"*. **Ici neuf des dix items de la
  ligne sont les items neufs** : il n'y a pas de majorite qui tienne a cote. **C'est mon propre
  critere qui echoue, pas une barre que je releve.**
- **`CAT-1`** : je n'ai pas rouvert `EMC-CP-02` ni `EMC-CP-06`, **deja `couvert-valide`**. Rouvrir une
  ligne fermee et refuser d'en fermer une neuve sont deux gestes differents.
- **Je ne refuse pas sur `PLA-1` seule.** Je refuse sur un fait qu'aucune des trois n'attrape : la
  faussete est predictible **au niveau de la ligne entiere**, sans lire l'item. Sur l'anglais l'enfant
  devait au moins lire l'enonce ; ici il n'a pas a le lire.
- **Et sur ce que la matrice dirait** : passer `HG-CM1-12` a `couvert-valide` affirmerait que le
  produit couvre *"decrire comment on se loge, on travaille, on se cultive et on se deplace dans un
  espace urbain et dans un espace touristique"*. **Une ligne dont les dix items se repondent par une
  regle sur le registre des distracteurs ne met jamais cette competence a l'epreuve.**

> **`HG-R17`. Propriete a tenir, et je ne fournis pas les textes.** Sur `HG-CM1-12`, les deux
> distracteurs de chaque item appartiennent au **meme genre que la bonne reponse et au meme espace que
> celui de l'enonce** : un autre lieu de la ville contre le musee, un autre transport urbain contre le
> metro, un autre hebergement de vacances contre le camping, un autre metier de ville contre le
> bureau. **Un enfant qui ne sait pas doit pouvoir se tromper serieusement.** Et sur les deux items
> nommes, le mot de la bonne reponse cesse d'etre appariable a un mot de l'enonce.
>
> **Ce que je ne demande pas** : aucun item neuf, aucune cellule de plus, aucun changement d'enonce
> sauf sur les deux items d'appariement. `FER-1`, `FER-2`, `FER-3` et `HIS-5` sont **tenues** et je ne
> les redemande pas. **Regime : rejeu borne, 0,5.**

### `HG-R18` - **reserve neuve, datee du 2026-09-10, non bloquante**

*"Dans un espace urbain, quel secteur emploie le plus d'habitants, **les services ou l'agriculture**
?"*, servi avec trois propositions dont *"la peche"*. **La question offre une alternative fermee entre
deux termes et l'ecran en presente trois** : l'enfant qui lit bien apprend de l'enonce que la
troisieme n'est pas a considerer, et passe de trois choix a deux. **C'est l'inverse de ce que la
lecture devrait lui rapporter.** **Propriete a tenir** : l'enonce pose sa question sans enumerer de
propositions. **Regime : mot pour mot, 0,25**, il voyage avec `HG-R17` sur le meme paquet (`PAR-10`).

### Un point verifie sur cette ligne qui ne fonde aucune demande

**Biais social** : j'ai regarde si les neuf items presupposent un contexte que tous les enfants n'ont
pas - une station de ski, un telesiege, un camping sur la Cote d'Azur, un chalet loue. **Ils ne le
presupposent pas** : chaque enonce donne l'espace dont il parle, et decrire un espace touristique est
le libelle meme de la ligne. `BIA-1` a `BIA-5` : aucun stereotype, aucun personnage juge. **Je le dis
pour que ce point ne se relise pas comme manque.**

## 6. `CONS-1` - **toujours non levee**, et ce que le remplissage y deplace

La consigne servie est **inchangee**, relevee a chacune de mes 280 manches : 31 mots, cinq clauses.

**Sur les 200 manches de ma seconde serie, appariees a leur item du fichier** (199 sur 200 appariees ;
une manche n'a pas pu l'etre par une difference d'apostrophe, et je le dis plutot que d'arrondir) :
**dix-sept manches ont servi `HG-CM1-08`, dont aucune des cinq clauses ne nomme la tache.** *Comment
appelle-t-on ce texte, cette recompense, ce representant ?* n'est ni situer dans le temps, ni nommer
un peuple, ni associer un roi a son fait, ni decrire un espace, ni lire un document - **et Napoleon
Bonaparte n'est pas un roi.** Avant s33 cette ligne portait un item sur 92 ; elle en porte dix sur
110. **L'item 85, corrige par `S33-HG-04`, sort lui aussi de la clause *nomme un peuple ou un
mouvement*.**

**Je ne refais pas le comptage a la main de mon arbitrage prealable** - neuf manches sur vingt le
2026-09-09 - **et je ne le fusionne pas avec celui-ci** : un chiffre date ne se refait pas a chaque
document, et les deux n'ont pas la meme methode.

> **`CONS-1` reste non levee sur cette entree, non bloquante.** Ce n'est pas un reproche au lot :
> refondre la consigne n'etait pas son objet, et `S33-CONS-4` est restee bloquee tout le sprint faute
> de la decision de grain. **`CONS-4` reste ouverte, non bloquante, portee D, non chiffree** - et
> cette decision m'est due autant qu'a `camille-po` (`ADR-0081`). Je ne l'ai pas rendue a s33 et je
> l'ecris. **Elle se prend desormais sur un paquet de 110 items au lieu de 92**, exactement ce que mon
> arbitrage prealable annoncait comme le prix de son report.

## 7. `SCO-1` - mesuree une quatrieme fois sur ce paquet

**28 parties completes, 28 scores de 10 / 10**, chaque proposition fausse pressee avant la bonne, de
15 a 27 appuis depenses pour dix manches. **Un enfant qui ne sait rien rapporte 10 sur 10 de ce jeu.**
`D7` reste a **0,0** : cette observation est gratuite, faite dans des parties que je jouais de toute
facon, et elle redeviendra due a 0,5 le jour ou il y aura un remede a verifier. **Le remede appartient
a `theo-archi`. L'instruction 1 de `DEC-0061` - ce que *trois essais* veut dire quand l'elimination
est possible - m'appartient, elle attend depuis neuf sprints, et je la porte a s34 comme une dette de
ma porte.**

## 8. Etat des lignes et des reserves de cette matiere au 2026-09-10

| Ligne | Avant | Apres | Mention |
|---|---|---|---|
| `HG-CM1-02` | `en cours` (refusee a s32) | **`couvert-valide`** | aucune n'est due |
| `HG-CM1-04` | `en cours` (refusee a s32) | **`couvert-valide`** | aucune n'est due |
| `HG-CM1-08` | `en cours` | **`couvert-valide`** | aucune n'est due |
| `HG-CM1-12` | `en cours` | **`en cours`** (refusee, `HG-R17`) | sans objet |
| `-01`, `-03`, `-06`, `-07`, `-11`, `-13`, `-14` | `couvert-valide` | **inchangees** | celles de `-01` et `-14` inchangees |
| `-05`, `-09`, `-10` | `non couvert` | **inchangees** | - |

**Bilan de la matiere : 10 `couvert-valide`, 1 `en cours`, 3 `non couvert`, 14 au total.**

| Reserve | Etat au 2026-09-10 |
|---|---|
| `HG-R1` a `HG-R8` | **levees** (s28, s29, s30, s32) |
| `HG-R9`, `HG-R10` | **levees ce jour** |
| `HG-R11`, `HG-R12` (bloquantes) | **levees ce jour** |
| `HG-R13` | **ouverte**, non bloquante, rejeu borne 0,25 |
| `HG-R14` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `HG-R15` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `HG-R16` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `HG-R17` | **ouverte, BLOQUANTE**, rejeu borne 0,5 |
| `HG-R18` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `CONS-1` | **non levee** sur cette entree |
| `CONS-4` | **ouverte**, non bloquante, portee D, decision due |

**Sous `PAR-10`, les six reserves neuves vivent dans un seul paquet et se soldent ensemble : 0,5 en
tout.** Les regimes disent ce que le developpeur doit faire, pas ce que je facture.

## 9. Ce dont je reste la seule garde sur cette matiere, apres s33

- **`HG-R17`** - le registre des distracteurs comme discriminant d'une ligne entiere. **Les tests du
  lot sont verts sur `HG-CM1-12`.** Aucun automate ne le voit, et je ne pose pas de barre chiffree en
  cours de sprint.
- **`HIS-5`** - le test ecrit par le lot ecarte **une chaine de caracteres** (*"des immeubles
  collectifs"*), pas une cellule. Il aurait laisse passer un item neuf disant la meme chose autrement.
  **C'est un proxy, pas ma clause**, et je le nomme plutot que de laisser croire qu'elle est encodee.
- **`FER-3` sur `HG-CM1-04`** - mordue par un ensemble de trois chaines de bonnes reponses ecrit a la
  main. **La forme est le bon choix** (`theo-archi`, avis de faisabilite : un ensemble ecrit a la main
  rougit quand le contenu change, une sous-chaine passe au vert) ; c'est sa **portee** que je nomme -
  il compte des chaines, pas des taches.
- **`FER-1` sur `HG-CM1-12`** - mordue par un proxy qui cherche trois mots dans les enonces. Il prouve
  que *se cultiver* est servie, pas que les quatre pratiques le sont.
- **`EXA-1` et l'exactitude factuelle** - `HG-R16` en est la preuve de plus : les quatre jobs sont
  verts sur un arbre ou un enonce dit que la Banque de France fabrique la monnaie.
- **`PLA-1`**, **`HIS-3` famille (b)**, **qu'une question et ses propositions forment une phrase
  correcte recollees** (`HG-R13`), **qu'un enonce garde un sujet a son infinitive** (`HG-R14`),
  **qu'un indice permette a l'enfant bloque d'avancer** (`HG-R15`) - aucune n'est mordable, et **la
  premiere de ces trois-la a ete cassee par mon propre texte**.
- **`CONS-1`, `CONS-4`** - partiellement mordables, et seulement si le paquet declarait la tache de
  chaque item : **il ne la declare toujours pas.**
- **`SCO-1`** - entierement automatisable, **et personne ne la tient**, quatrieme mesure.
- **La couleur, le contraste, la taille, la position a l'ecran** - **personne sur ce terrain ce
  sprint** (audit degrade).


---

# Sixieme passage - increment de s34 (`S34-HG-12`, `7961b7b6`) - **VALIDE SOUS RESERVE**, `HG-CM1-12` ferme

> Bloc de validation pedagogique - **`nadia-prof` UNIQUEMENT, instance A de s34**, 2026-09-11. Il
> n'est jamais pre-rempli ni anticipe par un autre agent. **Les blocs de s27 a s33 ci-dessus ne sont
> pas retouches** : un refus efface n'a aucune valeur de preuve, et celui du 2026-09-10 sur
> `HG-CM1-12` reste lisible avec sa date et son motif sous la levee d'aujourd'hui.
>
> **Le detail complet de cet audit vit dans
> `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-A.md`.** Ce
> bloc porte le verdict, les levees et les reserves neuves ; il ne recopie pas les mesures.

## Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `a7852398`, les quatre branches fusionnees, jar reconstruit depuis
un worktree dedie et lance sur un port local, arrete par son identifiant de processus. **Plus de
vingt parties completes**, **les dix items de `HG-CM1-12` vus a l'ecran un par un**, chaque
proposition fausse pressee avant la bonne, plus des parties jouees mal jusqu'a la revelation.

**Degrade sur un point, declare comme a s32 et a s33** : j'ai joue par requetes HTTP reelles, donc
j'ai lu le HTML servi et non la page rendue par un navigateur. **Ni la couleur, ni le contraste, ni
la taille, ni la position a l'ecran ne sont couverts, et aucun verdict de ce bloc n'en depend.**

## 1. Le verdict

| Ligne | Story | Verdict | Etat de matrice demande |
|---|---|---|---|
| `HG-CM1-12` | `S34-HG-12` | **VALIDE SOUS RESERVE** - **Done pedagogique prononce le 2026-09-11** | `en cours` -> **`couvert-valide`** |

**Aucune mention de couverture partielle n'est due sur cette ligne.** Celles de `HG-CM1-01` et de
`HG-CM1-14` sont inchangees. **Le mouvement de la matrice appartient a `camille-po` et a la review ;
je ne l'ecris pas.**

## 2. `HG-R17` - **levee sur sa clause 1**, et ce que le compte donne

**Methode, identique a celle du refus de s33 pour que les deux chiffres soient comparables** :
attribution faite a la main par moi, item par item, sur le fichier servi et sur les dix items vus a
l'ecran.

| Mesure | s33 (refus) | s34 |
|---|---|---|
| Items neufs portant au moins un distracteur serieux du meme genre que la bonne reponse | **2 sur 9** | **9 sur 9** |
| Items neufs dont les **deux** distracteurs sont du meme genre que la bonne reponse | 0 sur 9 | **9 sur 9** |
| Distracteurs neufs du registre de la ferme, du champ, de la peche ou de la batellerie | **14 sur 18** | **0 sur 18** |
| Items de la ligne entiere repondables sans lire la question | **9 sur 10** | **2 sur 10** (`HG-R19`, `HG-R22`) |

**Le mode de defaillance que j'avais nomme d'avance est evite** : aucun mot rural n'a ete remplace
par un mot tout aussi invraisemblable. Chaque distracteur est une chose reelle, du genre de la
reponse, ajustee a son item - deux hebergements de vacances face a un hebergement, deux secteurs
economiques face a un secteur, deux transports en commun face au metro.

**`HG-R18` est levee** : l'enonce du secteur d'activite n'enumere plus deux des trois propositions.
**La moitie de `HG-R17` qui portait sur l'appariement de l'item du logement est payee** : l'enonce ne
contient plus *loue*.

**`FER-1`, `FER-2` (dix items), `FER-3`, `HIS-5` et `HG-R4` sont reverifiees et tenues**, comme
annonce, sans etre rediscutees. Les bonnes reponses n'ont pas bouge ; les deux enonces touches ont
raccourci.

**`CONS-1` et `CONS-4` restent non levees sur cette entree** : la consigne a cinq clauses est
toujours servie sur chaque manche, `S34-CONS-4` n'ayant pas ete engagee. **Je ne bloque pas
dessus** : j'ai ferme trois lignes de ce paquet a s33 avec cette meme consigne, et relever la barre
aujourd'hui serait deplacer un poteau.

## 3. Une correction a mon propre constat, avant de m'en servir

**Mon arbitrage prealable de s34 se contredit sur le perimetre de `HG-R17`** : sa section 5.1 le
borne aux *"18 distracteurs des 9 items neufs ... et rien d'autre"*, son refus d'avance numero 1
parle de *"toute fermeture de `HG-CM1-12`"*, donc des dix items. **Le dixieme, ecrit a s27, n'a
jamais ete audite a la fermeture.** `marc-dev` a tenu exactement le perimetre que je lui avais
ecrit ; **refuser la ligne sur un item que j'en avais moi-meme sorti serait lui faire payer mon
erreur de chiffrage**. La ligne ferme, et cet item part en reserve neuve datee d'aujourd'hui -
`HG-R22` -, imputable a moi.

## 4. Les quatre reserves neuves, datees du 2026-09-11, **toutes non bloquantes**

### `HG-R19` - la seconde clause de `HG-R17`, payee sur un item des deux

*"Dans une ville touristique riche en **histoire**, que visitent surtout les touristes ?"* -> *"des
monuments **historiques**"*. `HG-R17` nommait **deux** items pour cet appariement ; celui du logement
est corrige, **celui-ci n'a pas ete touche**.

> **`HG-R19`.**
> - **Objet** : sur cet item, un enfant qui ne sait pas ce qu'est un monument historique ne doit pas
>   pouvoir trouver la reponse en reconnaissant un mot de l'enonce dans une proposition.
> - **Mode de defaillance** : retirer *riche en histoire* et laisser l'enonce ne plus dire de quelle
>   ville il parle. L'enonce doit continuer a poser son espace (`HIS-3`), **sans employer le mot que
>   la bonne reponse porte**.
> - **Perimetre, mesure sur l'etat d'arrivee** : un enonce, l'item des monuments historiques, et rien
>   d'autre. Les trois propositions ne bougent pas ; `FER-1`, `FER-3` et `HIS-5` restent tenues.
>   **Non compte** : aucun autre item de la ligne ne partage un mot entre son enonce et sa bonne
>   reponse - je les ai lus.
> - **Regime : mot pour mot, 0,25**, voyage avec les trois autres (`PAR-10`).

### `HG-R20` - un distracteur devenu serieux et devenu vrai

Deux items. *"Dans une station de ski, quel appareil fait monter les touristes vers les pistes ?"*
-> *"un telesiege"*, contre *"un tapis roulant"* : **un tapis roulant de station est une remontee
mecanique, il fait bien monter des skieurs**. *"Dans une ville touristique, quel evenement culturel
attire souvent des visiteurs en ete ?"* -> *"un festival"*, contre *"une exposition de peinture"* :
**une exposition de peinture est un evenement culturel qui attire des visiteurs en ete**. La question
ne demande ni le plus frequent ni le plus grand.

**C'est le revers de la correction, et il ne faisait pas partie des modes de defaillance que j'avais
nommes d'avance. Je le dis plutot que de le presenter comme prevu.**

> **`HG-R20`.**
> - **Objet** : un enfant qui connait les trois propositions doit pouvoir choisir sur ce qu'il sait,
>   et non deviner celle que le jeu attend. Aujourd'hui, sur ces deux items, le bon eleve est puni de
>   ce qu'il sait.
> - **Mode de defaillance** : remettre un distracteur plus faible pour retablir l'unicite - cela
>   rouvrirait `HG-R17` la ou elle vient d'etre payee. **Le geste est dans l'enonce** : il doit porter
>   le critere qui distingue la bonne reponse, comme huit des dix items de la ligne le font deja
>   (*"sous terre"*, *"en plein air"*, *"le plus souvent"*). **Et ce critere doit etre vrai** : je
>   refuse d'avance un superlatif pose pour trancher et faux dans le monde.
> - **Perimetre, mesure sur l'etat d'arrivee** : deux enonces, ceux du telesiege et du festival. Les
>   six propositions restent. `HG-R4` a reverifier, les deux enonces s'allongeant un peu. **Non
>   compte** : les enonces des autres lignes du paquet, qui sont fermees et que je ne rouvre pas.
> - **Regime : mot pour mot, 0,25**, voyage avec les trois autres (`PAR-10`).

### `HG-R21` - un indice que la correction a vide

*"Pense au logement loue seulement pour quelques jours ou quelques semaines."* servi apres un echec
sur l'item du logement de montagne, face a *"une tente de camping"* et *"une auberge de jeunesse"* :
**les trois se louent pour quelques jours**. L'indice separait les trois propositions quand les
fausses etaient *une ferme isolee* et *un immeuble de bureaux* ; **il ne separe plus rien**, et son
mot *loue* ramene l'appariement avec *location* que l'enonce vient de perdre. Second cas, plus
faible : l'indice du musee ne fait plus que redire son enonce face a *"une bibliotheque"* et *"une
mairie"*.

> **`HG-R21`.**
> - **Objet** : l'enfant qui vient de se tromper doit trouver dans l'indice un fait qui designe une
>   seule des trois propositions.
> - **Mode de defaillance** : ecrire un indice qui nomme la bonne reponse par un synonyme - gagner
>   sans savoir, un cran plus loin. L'indice doit apporter **un fait sur la chose**.
> - **Perimetre, mesure sur l'etat d'arrivee** : deux indices, celui du logement de montagne et celui
>   du musee. Enonces, bonnes reponses et distracteurs ne bougent pas. **Non compte** : les huit
>   autres indices de la ligne, lus sans qu'aucun ne m'arrete.
> - **Regime : mot pour mot, 0,25**, voyage avec les trois autres (`PAR-10`).
> - **Ce que cette reserve n'est pas** : elle ne releve aucune barre sur les indices. Sans la
>   correction des distracteurs, elle n'existerait pas.

### `HG-R22` - l'item que j'avais sorti du perimetre

*"Dans un espace touristique de montagne, quelle activite se developpe surtout en hiver ?"* -> *"le
tourisme lie au ski"*, contre *"la peche en mer"* et *"la culture de la vigne"*. Item de s27, jamais
audite a la fermeture, **sorti par moi du perimetre de `HG-R17`** (section 3).

> **`HG-R22`.**
> - **Objet** : sur cet item aussi, un enfant qui ne sait pas doit pouvoir se tromper serieusement.
>   C'est le seul item de la ligne ou il lui suffit d'ecarter ce qui est rural.
> - **Mode de defaillance** : remplacer les deux distracteurs par deux activites que l'enonce exclut
>   deja par *en hiver*. Chacun doit etre une activite qu'un enfant peut serieusement attribuer a une
>   station de montagne en hiver.
> - **Perimetre, mesure sur l'etat d'arrivee** : deux distracteurs, un seul item. Enonce et bonne
>   reponse inchanges ; `FER-1`, `FER-2`, `FER-3`, `HIS-5` restent tenues.
> - **Regime : mot pour mot, 0,25**, voyage avec les trois autres (`PAR-10`).
> - **A qui ce defaut est imputable** : **a moi.**

## 5. Ce que je verifie sur cette ligne et qui ne fonde aucune demande

- **Exactitude** : les dix bonnes reponses sont justes ; les dix-huit distracteurs neufs designent
  des choses reelles. Les deux cas ou le distracteur est vrai **pour la question posee** sont
  `HG-R20`, comptes une seule fois.
- **Niveau de langue** : *"un telesiege"*, *"une auberge de jeunesse"*, *"un entrepot"*, *"la
  construction"* sont accessibles au CM1.
- **`BIA-1` a `BIA-5`** : aucun stereotype sur les dix items. Le chalet et le camping decrivent des
  vacances que tous les enfants ne prennent pas ; **ils ne les presupposent pas**, chaque enonce
  donnant l'espace dont il parle. **Je n'ouvre rien la-dessus.**
- **`COH-1`** : les dix items ne se contredisent pas. **Je n'ai pas passe le paquet entier a cette
  condition** - elle est opposable a partir du prochain lot, et ce lot ne reecrit aucune regle.

## 6. Etat des lignes et des reserves de cette matiere au 2026-09-11

| Ligne | Avant | Apres | Mention |
|---|---|---|---|
| `HG-CM1-12` | `en cours` (refusee a s33) | **`couvert-valide`** | aucune n'est due |
| `-01`, `-02`, `-03`, `-04`, `-06`, `-07`, `-08`, `-11`, `-13`, `-14` | `couvert-valide` | **inchangees** | celles de `-01` et `-14` inchangees |
| `-05`, `-09`, `-10` | `non couvert` | **inchangees** | - |

**Bilan de la matiere : 11 `couvert-valide`, 0 `en cours`, 3 `non couvert`, 14 au total.**

| Reserve | Etat au 2026-09-11 |
|---|---|
| `HG-R1` a `HG-R12` | **levees** (s28 a s33) |
| `HG-R13` a `HG-R16` | **ouvertes**, non bloquantes, non demandees ce sprint |
| `HG-R17` (bloquante) | **levee sur sa clause 1** ; sa clause 2, impayee, vit sous `HG-R19` |
| `HG-R18` | **levee** |
| `HG-R19`, `HG-R20`, `HG-R21`, `HG-R22` | **neuves**, non bloquantes, mot pour mot 0,25 chacune |
| `CONS-1`, `CONS-4` | **non levees** sur cette entree ; `S34-CONS-4` non engagee, reportee a s35 |

**Sous `PAR-10`, les quatre reserves neuves vivent dans un seul paquet et se soldent ensemble : 0,5
en tout.**

## 7. Ce dont je reste la seule garde sur cette matiere, apres s34

- **`PLA-1`** - paye aujourd'hui **sans qu'aucun test n'ait change**, ce qui dit exactement ce que le
  depot en voit : rien.
- **Qu'un distracteur serieux ne soit pas une reponse vraie** (`HG-R20`) - **neuf a partir
  d'aujourd'hui**, et c'est la contrepartie de `PLA-1` : elle apparait des qu'on paie `PLA-1`.
  Personne ne la tient.
- **Qu'un indice separe les trois propositions** (`HG-R21`) - et **un indice peut cesser de separer
  sans qu'une seule de ses lettres ne change**, parce que les propositions ont bouge autour de lui.
- **Qu'un mot de l'enonce n'appelle pas la bonne reponse** (`HG-R19`) - meme famille que `EXC-R7`,
  routee a `theo-archi` a s33 et non payee.
- **`EXA-1` et l'exactitude factuelle**, **`HIS-5`**, **`FER-1` sur cette ligne** (mordue par un
  proxy qui cherche trois mots dans les enonces), **`CONS-1` et `CONS-4`** - inchangees depuis s33.
- **La couleur, le contraste, la taille, la position a l'ecran** - **personne sur ce terrain ce
  sprint** (audit degrade).
