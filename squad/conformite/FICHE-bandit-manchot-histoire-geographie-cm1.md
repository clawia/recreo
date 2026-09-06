# Fiche de conformite - bandit-manchot-histoire-geographie-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml`.
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-cm1`, titre servi *"Histoire et géographie"*, `nombreDeManches: 10`.
- **Statut global s29** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** sur l'increment de s29, composition A, stories `S29-2` et `S29-7`). **Trois lignes de plus ferment** : `HG-CM1-06`, `HG-CM1-07` et `HG-CM1-14` passent a `couvert-valide`, avec une **mention exigee d'avance sur la ligne 14**. **Six lignes du paquet restent `en cours`** (section 8 du bloc de s29). `HG-R4` et `HG-R5` sont **levees** ; deux reserves nouvelles, non bloquantes : `HG-R6` et `HG-R7`.
- **Statut global s28** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-04** sur l'increment de s28, composition B). **Deux lignes ferment** : `HG-CM1-11` et `HG-CM1-13` passent a `couvert-valide`. **Les neuf autres lignes du paquet restent `en cours`** et personne ne doit s'en etonner : je l'avais ecrit d'avance. Deux reserves nommees, non bloquantes, `HG-R4` et `HG-R5`.
- **Statut s27, conserve tel quel** : **VALIDE** (Done pedagogique prononce le 2026-09-03). **Ma reserve bloquante du 2026-09-03 sur l'item 5 est levee** : la reformulation est reprise mot pour mot. Lignes de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, aucune ligne ne se ferme.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` sections 5, 7.2, 7.4 et 7.5 (`HIS-1` a `HIS-3`, `BIA-5`) ; `ARBITRAGE-s27-s1-8-retrecie-unite-de-ma-borne-et-trois-faits-d-histoire.md` section 3.
- **Derniere mise a jour** : 2026-09-06 (**troisieme verdict de `nadia-prof` apres avoir rejoue et lu les cinquante-sept items**, s29).

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
