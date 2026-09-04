# Fiche de conformite - bandit-manchot-histoire-geographie-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml`.
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-cm1`, titre servi *"Histoire et géographie"*, `nombreDeManches: 10`.
- **Statut global s28** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-04** sur l'increment de s28, composition B). **Deux lignes ferment** : `HG-CM1-11` et `HG-CM1-13` passent a `couvert-valide`. **Les neuf autres lignes du paquet restent `en cours`** et personne ne doit s'en etonner : je l'avais ecrit d'avance. Deux reserves nommees, non bloquantes, `HG-R4` et `HG-R5`.
- **Statut s27, conserve tel quel** : **VALIDE** (Done pedagogique prononce le 2026-09-03). **Ma reserve bloquante du 2026-09-03 sur l'item 5 est levee** : la reformulation est reprise mot pour mot. Lignes de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, aucune ligne ne se ferme.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` sections 5, 7.2, 7.4 et 7.5 (`HIS-1` a `HIS-3`, `BIA-5`) ; `ARBITRAGE-s27-s1-8-retrecie-unite-de-ma-borne-et-trois-faits-d-histoire.md` section 3.
- **Derniere mise a jour** : 2026-09-04 (**second verdict de `nadia-prof` apres avoir rejoue**, s28).

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
