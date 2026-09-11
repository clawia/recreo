# [Sprint 34] Review - 2026-09-11

- **Horodatage de debut** : 2026-09-10 (planning, aucune heure tracee dans ce fichier).
- **Horodatage de fin** : 2026-09-11.
- **Duree effective** : au moins un jour ; je n'invente pas d'heure de debut que le planning
  n'a pas ecrite, contrairement a s33 qui en portait une.
- **Anime par** : `sofia-sm`.

## Increment livre

Trois instances de `nadia-prof` ont audite en parallele trois terrains disjoints (histoire-
geographie et anglais CM1 plus la portee transverse, EMC CP et le premier lot du palier 2,
education physique et sportive CP) sur les dix stories que quatre developpeurs ont livrees ce
sprint ; six lignes ferment (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`,
`EPS-CP-11`), trois refus (`S34-SCO1-REMEDE`, `S34-EXC-TACHEB`, `QLM-CP-14`), portant la
couverture du programme de 69/138 (50,0 %) a 75/138 (54,3 %) une fois les six mouvements de
matrice executes - mouvements que je route a `camille-po` plutot que de les faire moi-meme
(section suivante).

## 1. Le compte des lignes fermees - recompte sur les trois verdicts, pas repris d'un chiffre annonce

**Instance A** (`verdicts-nadia-instance-A.md`, section 2) : `S34-HG-12` **VALIDE SOUS RESERVE**,
Done pedagogique prononce, `HG-CM1-12` demandee `en cours` -> `couvert-valide`. `S34-ANG-CORR`
valide sous reserve mais ne bouge aucune ligne (`LVE-CM1-07` deja `couvert-valide`).
`S34-SCO1-REMEDE` **REFUSE**, aucune ligne. `S34-TRANSVERSE-1` : aucun verdict de matrice, par
construction. **Une ligne fermee : `HG-CM1-12`.**

**Instance B** (`verdicts-nadia-instance-B.md`, section 1) : `EMC-CP-07` **VALIDE SOUS RESERVE**,
Done pedagogique prononce, `non couvert` -> `couvert-valide`. `S34-EXC-TACHEB` **REFUSE**,
`EMC-CP-03` non rouverte. `QLM-CP-14` **REFUSE**, reste `non couvert`. `S33-PAL2-TROU-A/B` payees
mais ne portent aucune ligne (mecanique transverse). **Une ligne fermee : `EMC-CP-07`.**

**Instance C** (`verdicts-nadia-instance-C.md`, section 1) : `EPS-CP-04`, `EPS-CP-08`,
`EPS-CP-10`, `EPS-CP-11` **VALIDE SOUS RESERVE** chacune, Done pedagogique prononce sur les
quatre. **Quatre lignes fermees.**

**Total : 6 lignes fermees, 3 refus** (`S34-SCO1-REMEDE`, `S34-EXC-TACHEB`, `QLM-CP-14` - ce
dernier sur une ligne de matrice, les deux premiers sur des increments transverses ou deja
fermes). **Taux d'entree** : 69/138 = 50,0 % (`DEC-0086`, section 1, repris du planning section 1,
lui-meme non recalcule sur celui de la review de s33). **Taux de sortie, une fois les six
mouvements executes : 75/138 = 54,3 %**, recompte en section suivante.

## 2. Mouvement de `squad/programme/MATRICE-couverture.md` - route a `camille-po`, non execute ici

**Je ne bouge pas la matrice dans ce document, et je le fais pour un motif ecrit, pas par
defaut.** `squad/README.md`, section 4, ligne 45, attribue explicitement la propriete de ce
fichier a `camille-po` ; la section "6. Porte pedagogique" du meme fichier dit, mot pour mot :
*"Si c'est `VALIDE`, Camille passe la ligne de matrice a `couvert-valide`."* L'historique Git de
`squad/programme/MATRICE-couverture.md` confirme cette pratique sur seize mouvements consecutifs,
de s27 a s32 ainsi qu'a s31 (`c5e7e2c9` a `76ce7017`, tous `[CAM]`) - **a une seule exception,
`c5450328` (s33, `[SOF]`)**. Cette exception est la mienne, et je ne la reconduis pas ici :
`verdicts-nadia-instance-C.md`, section 8, ecrit explicitement que les quatre mouvements de son
perimetre *"appartiennent a `camille-po`"* et que l'instance *"ne touche pas la matrice"* - la
meme phrase que les instances A et B ecrivent pour les deux leurs. Trois instances sur trois
routent a `camille-po` ; je ne me substitue pas a elle une seconde fois.

**Les six mouvements attendus, chacun avec le verdict qui l'autorise, a executer par `camille-po` :**

| Ligne | Mouvement demande | Verdict qui l'autorise | Mention de couverture partielle |
|---|---|---|---|
| `HG-CM1-12` | `en cours` -> `couvert-valide` | `verdicts-nadia-instance-A.md`, section 3 | **Aucune n'est due** (section 2 du meme document) |
| `EMC-CP-07` | `non couvert` -> `couvert-valide` | `verdicts-nadia-instance-B.md`, section 2 | Aucune mention nommee dans le verdict |
| `EPS-CP-04` | `non couvert` -> `couvert-valide` | `verdicts-nadia-instance-C.md`, section 3.1 | Deja pre-ecrite dans la matrice, a reprendre telle quelle (section 3.4 du verdict) |
| `EPS-CP-08` | `non couvert` -> `couvert-valide` | `verdicts-nadia-instance-C.md`, section 3.2 | Deja pre-ecrite dans la matrice, a reprendre telle quelle (section 3.4 du verdict) |
| `EPS-CP-10` | `non couvert` -> `couvert-valide` | `verdicts-nadia-instance-C.md`, section 3.3 | **Ecrite aujourd'hui par l'instance C, n'existait pas** - a copier mot pour mot (section 3.4 du verdict) |
| `EPS-CP-11` | `non couvert` -> `couvert-valide` | `verdicts-nadia-instance-C.md`, section 3.3 | **Ecrite aujourd'hui par l'instance C, n'existait pas** - a copier mot pour mot (section 3.4 du verdict) |

**Recompte independant, sur l'hypothese que les six mouvements sont executes tels quels** (dix-huit
tableaux de la matrice, colonne `Statut`) :

| | Avant les verdicts (ouverture de s34) | Apres, si `camille-po` execute les six mouvements |
|---|---|---|
| `couvert-valide` | 69 | **75** |
| `en cours` | 1 | **0** |
| `non couvert` | 68 | **63** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **69 / 138 = 50,0 %** | **75 / 138 = 54,3 %** |

**`squad/programme/MATRICE-couverture.md` n'est touche par aucun fichier de ce commit.** Le
taux de 54,3 % ci-dessus est une projection arithmetique du mouvement attendu, pas un fait ecrit
dans la matrice tant que `camille-po` ne l'a pas executee.

## 3. Le plafond atteint, sans l'embellir

Le plafond arithmetique de la strategie en cours (`DEC-0086`, section 3) etait de **6 lignes** a
l'ouverture de s34, porte a **8** par l'engagement du palier 2 par l'utilisatrice (planning,
section 2). **Six lignes ferment ce sprint - le plafond de base (75/138 = 54,3 %) est atteint
pour la premiere fois depuis l'ouverture de cette strategie (s32 : 3 lignes ; s33 : 3 lignes ; s34 :
6 lignes).** Dans le meme geste, **les deux lignes du palier 2 ne ferment pas** : `QLM-CP-14` est
refusee (`FRI-R1`, `FRI-R2`, bloquantes) et `QLM-CP-11` n'a jamais ete livree (planning, section
15.2 - la geometrie de `plan-classe.svg` et le plancher de lisibilite d'un libelle de zone restent
a trancher). Les deux faits sont vrais ensemble et je les ecris ensemble : le sprint atteint son
plafond de base a l'unite pres, et l'extension que le palier 2 devait apporter (+2, jusqu'a
77/138 = 55,8 %) reste entierement non realisee.

## 4. Les trois refus, de nature distincte

- **`S34-SCO1-REMEDE`** (`SCO-R1`, bloquante, instance A, section 5.2) : le remede des trois
  essais est juste et verifie sur les trois mecaniques (`bandit-manchot`, `saisie`, `designation`),
  mais l'ecran de fin de partie n'affiche que **`"Score : 6"`**, sans dire que l'enfant a fait dix
  questions. **Ce n'est pas un defaut du remede : c'est un trou de perimetre que ni la story ni
  `ADR-0090` (decision 4) ne portaient**, ecrit d'avance par `nadia-prof` dans son refus numero 6
  du 2026-09-10 et realise a l'endroit exact ou elle l'avait nomme.
- **`S34-EXC-TACHEB`** (`EXC-R10`, instance B, section 3) : la refonte de la tache B de
  `cp-emc-exclusion` reecrit les cinq `enonce`, les cinq `bonneReponse` et les dix `distracteurs`,
  mais **les cinq lignes `indice` n'ont pas ete touchees** - elles repondent encore a la question
  que la refonte supprime (*"Quel adulte est la ?"*), et aucune ne dit a l'enfant pourquoi la
  conduite qu'il a choisie ne marche pas.
- **`QLM-CP-14`** (`FRI-R1`, `FRI-R2`, bloquantes, instance B, section 4) : **neuf items sur onze
  se gagnent sans regarder le dessin** - les libelles positionnels (des rangs) et le texte
  alternatif (qui enumere les moments dans l'ordre de la frise) forment ensemble une table de
  correspondance complete ; et le pictogramme du lever ne se lit pas comme un lit. Reparation
  chiffree par l'instance elle-meme : **deux lignes de texte alternatif honnete plus un
  pictogramme reforme**, aucune des deux qu'elle n'ecrit elle-meme (`RES-2`).

## 5. `RES-1` a l'epreuve - le resultat le plus reutilisable de ce sprint

Les trois instances mesurent, chacune sur son propre terrain, la meme question : une correction
suit-elle l'objet d'une reserve ou seulement sa lettre ? Les trois convergent sur un seul resultat,
par trois chemins independants.

**Instance C** (`verdicts-nadia-instance-C.md`, section 4) mesure une population de six reserves
non bloquantes reconduites d'un sprint anterieur : **quatre portaient un objet ecrit, les quatre
ont ete payees a l'objet ; deux n'en portaient aucun, aucune des deux n'a ete payee.** *"La
correlation n'est donc pas reserve bloquante contre reserve non bloquante, c'est reserve qui porte
un objet contre reserve qui n'en porte pas."*

**Instance A** (`verdicts-nadia-instance-A.md`, section 8) trouve deux echecs sur sept reserves de
contenu, et les deux partagent une forme distincte de l'absence d'objet : **une reserve ecrite en
enumeration** (`HG-R17` clause 2, deux items nommes ; `ANG-R3`, trois items nommes) **dont une
partie seulement a ete traitee, la correction annoncant la reserve payee en entier.** L'objet
etait present et bon ; c'est le perimetre qui a ete retreci en silence sur un sous-ensemble de
l'enumeration.

**Instance B** (`verdicts-nadia-instance-B.md`, section 5) trouve un troisieme mode d'echec, sur
`cp-emc-exclusion` : une condition ecrite **le meme jour, par elle, dans la meme fiche**, mais
**rangee dans la reponse a une question (`Q3`) plutot que dans le bloc `Mode de defaillance`
de la story qu'elle aurait du gouverner.** *"Une condition rangee ailleurs que dans le bloc qui la
fait lire ne compte pas"* - meme quand elle est ecrite le jour meme par la meme personne que celle
qui juge ensuite son absence.

**Trois modes d'echec distincts, un seul dispositif, une seule lecon commune aux trois** : l'objet
protege quand il est ecrit, complet et range au bon endroit ; il ne protege ni un perimetre
partiel, ni une condition deplacee, quelle que soit sa justesse.

## 6. Ce que les garde-fous n'ont pas vu, mesure deux fois

- **`theo-archi`** (`confirmation-archunit.md`, section 5) mesure que le garde-fou du plancher de
  cible tactile (12,5 % du plus petit cote, derive de `.image-designation { max-height: 22rem }`
  et de `CIBLE_TACTILE_REM = 2,75`) est **vert sur les six cadres du plan de classe** pendant que
  **trois d'entre eux** (`tableau`, `armoire`, `porte`, mesures par `lea-dev` au jar) **rendent un
  libelle qui deborde de son bouton et chevauche les zones voisines.** *"Le plancher de cible
  tactile n'est pas la propriete `le libelle tient dans sa zone`. Deux proprietes voisines, une
  seule gardee, et c'est la seconde qui a coute une ligne de matrice ce sprint."* Le garde-fou
  mesurait autre chose que le defaut qui a fait retirer `cp-plan-de-la-classe.yaml`.
- **Instance B** (`verdicts-nadia-instance-B.md`, section 4) trouve, en regardant l'ecran rendu
  plutot que le HTML servi, que **les boutons de zone couvrent le dessin qu'ils designent** :
  `.zone` pose un fond blanc a 55 % et un texte gras de 20 px par-dessus l'image, et sur la frise
  de la journee les six boutons couvrent tout le dessin. *"Ce n'est pas un defaut du contenu de
  `lea-dev` [...] et cela frappera tous les paquets de designation a venir."*
- **`yanis-qa`** (`rapport-qualite.md`, section 4) signale, pour un troisieme sprint consecutif,
  **aucune trace dans le depot Git d'une partie jouee de bout en bout sur le jar pour les six
  reprises de contenu** (`HG-CM1-12`, `cm1-anglais`, `cp-emc-exclusion`, `cp-emc-regle-souhait-
  loi`, les trois paquets d'EPS). Il met ce trou en regard d'un fait mesure dans le meme sprint :
  **la seule partie reellement jouee au jar** (`lea-dev`, sur le palier 2, Chrome headless) **a
  trouve un vrai defaut** - les libelles qui debordent - avant tout garde-fou et avant toute
  lecture de rapport vert. *"C'est la preuve que cette pratique marche"*, et elle reste
  l'exception plutot que la regle.

## 7. Le registre de dette a la cloture - mesure, pas projete

`DEC-0086` (section 4) abandonne la projection : le registre se mesure a la cloture, jamais a
l'ouverture, et se rapporte comme un plancher. **Ouverture de s34 : 9,0** (`DEC-0086`, section 2).
Methode reprise a l'identique de celle de `camille-po` (`RECALCUL-registre-de-dette-ouverture-
s34.md`, section 1) : un paquet est **PAYE** quand la totalite de ses codes connus a l'ouverture
sont leves et que l'increment qui les leve n'est pas refuse ; sinon il reste **NON PAYE** en
entier, au prix plein du paquet.

**Composition de la dette de 9,0 a l'ouverture**, seize postes (`DEC-0086`, section 2) : six
touches ce sprint (`cm1-histoire-geographie` 0,5, `cp-emc-regle-souhait-loi` 0,5, `cp-eps-regles-
de-securite` 0,5, `cp-eps-regles-jeu-collectif` 0,5, `cp-emc-exclusion` 1,0, `cm1-anglais` 0,5),
dix non touches (`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, `cp-emc-emotions-autrui`,
`cp-emc-formules-de-politesse`, `cm1-sciences-signal-information`, `cm1-sciences-chaine-
alimentaire`, `cm1-division-posee`, `cm1-emc-fait-opinion`, `cm1-emc-droit-devoir` a 0,5 chacun,
groupe 2b a 1,0).

**Poste par poste, sur les six touches :**

| Paquet | Codes connus a l'ouverture | Verdict | PAYE ? |
|---|---|---|---|
| `cm1-histoire-geographie` | `HG-R13` a `HG-R18` (six codes) | `HG-R13`-`16` restent ouvertes, non demandees ; `HG-R17` levee sur sa seule clause 1 ; `HG-R18` levee | **NON, 0,5 reste du** - nouvelles reserves (`HG-R19` a `HG-R22`) marginales, le paquet n'etant pas clarifie |
| `cp-emc-regle-souhait-loi` | motif du refus persistant (`FD-1`, `FD-2`, `FD-3`) | Les trois modes evites, `EMC-CP-07` VALIDE SOUS RESERVE | **OUI, 0,5 retire** - nouvelles reserves `RSL-R1`, `RSL-R2` rouvrent 0,5 sur le meme paquet, remis a neuf (meme mecanique que `cm1-histoire-geographie` a s33) |
| `cp-eps-regles-de-securite` | `SEC-R4` (bloquante), `SEC-R5` | Les deux levees, `EPS-CP-04` VALIDE SOUS RESERVE | **OUI, 0,5 retire** - `SEC-R6` rouvre 0,5 (rejeu, verdict C section 5.1) |
| `cp-eps-regles-jeu-collectif` | `JEU-R9` (bloquante) a `JEU-R12` | `JEU-R9`, `R10` levees ; `JEU-R11`, `R12` reconduites non levees | **NON, 0,5 reste du** |
| `cp-emc-exclusion` | `EXC-R1`, `R6` a `R9` | `S34-EXC-TACHEB` **REFUSE** (`EXC-R10`) | **NON, 1,0 reste du** - increment refuse, la clause qui l'exige en premier (`RECALCUL`, section 1) |
| `cm1-anglais` | `ANG-R3` a `R6` | `R4`, `R5`, `R6` levees ; `R3` levee sur deux items des trois, reste ouverte | **NON, 0,5 reste du** - `ANG-R7` nouvelle, marginale |

**Poste neuf, jamais present dans le registre avant ce sprint** : `cp-eps-corps-effort`
(`EPS-CP-10`, `EPS-CP-11`), premiere livraison de contenu sur ces deux lignes. Les quatre modes de
defaillance nommes d'avance sont tous evites (verdict C, section 3.3) et l'instance tarife
elle-meme ses sept reserves neuves (`EFF-R1` a `EFF-R7`) a **0,5, un rejeu, pour le paquet
entier**. J'applique ici, pour la premiere fois, la meme regle qu'un paquet deja connu qui vient
d'etre clarifie : une dette neuve entre au registre. **C'est une extension de methode, pas un
poste que `DEC-0086` avait deja nomme** ; je le signale comme tel plutot que de le fondre en
silence dans les postes existants.

**Chiffre mesure a la cloture de s34 :**

| | Montant |
|---|---|
| Dix postes non touches (inchanges) | 6,0 + 1,0 (groupe 2b) = **7,0** |
| `cm1-histoire-geographie` (non paye) | **0,5** |
| `cp-eps-regles-jeu-collectif` (non paye) | **0,5** |
| `cp-emc-exclusion` (non paye, increment refuse) | **1,0** |
| `cm1-anglais` (non paye) | **0,5** |
| `cp-emc-regle-souhait-loi` (paye puis remis a neuf) | **0,5** |
| `cp-eps-regles-de-securite` (paye puis remis a neuf) | **0,5** |
| `cp-eps-corps-effort` (poste neuf) | **0,5** |
| **Total mesure a la cloture de s34** | **9,5** |

**Plancher, au sens de `DEC-0086`** (ouverture moins les postes dont le paquet clarifie ET dont la
ligne ferme reellement) : `cp-emc-regle-souhait-loi` et `cp-eps-regles-de-securite` sont les deux
seuls dans ce cas -> **9,0 - 1,0 = 8,0.** Le chiffre mesure (9,5) est superieur au plancher, ce qui
est attendu : le plancher est une borne basse, pas une cible.

**Le registre augmente de 9,0 a 9,5 malgre six lignes fermees**, entierement du fait du poste
neuf `cp-eps-corps-effort` (+0,5) : les deux paquets qui se clarifient (`cp-emc-regle-souhait-loi`,
`cp-eps-regles-de-securite`) rouvrent chacun une dette de meme montant dans le meme geste, sans
effet net, exactement le mecanisme que `DEC-0086` (section 4) avait nomme comme la premiere raison
d'abandonner la projection. Je l'ecris tel quel plutot que de l'adoucir.

**Non chiffrable, inchange ce sprint** : le reliquat de `CONS-1` sur `cm1-histoire-geographie`,
`CONS-3`, `CONS-4` - trois familles que `DEC-0086` (section 2) declare deja non chiffrees et qui
ne bougent ni a la hausse ni a la baisse ce sprint, faute de decision de grain.

**Ce que je ne fais pas** : je ne ratifie pas ce chiffre comme une decision produit. La methode
est celle que `camille-po` a ecrite et appliquee elle-meme pour l'ouverture de s34 ; je l'applique
a l'identique pour la cloture, et je route la ratification (ou la correction) a `camille-po`,
proprietaire du registre.

## 8. Le budget reellement depense contre 9,0 engages sur une borne de 12,0

Composition engagee au planning (section 3.1) : **9,0 sur 12,0**, pour 6 lignes de base plus 2 du
palier 2 (+8 potentielles).

| Instance | Borne engagee | Depense mesure | Idle | Ce que l'idle a couvert |
|---|---|---|---|---|
| A | 3,0 | **2,0** (`verdicts-nadia-instance-A.md`, section 9.3) | **1,0** | `S34-CONS-4` (1,0) non engagee, non demarree - aucun audit du a une story qui n'existe pas |
| B | 3,5 (1,5 EMC + 2,0 palier 2) | **3,5** | **0** | Les trois postes (`S34-EMCCP07`, `S34-EXC-TACHEB`, le palier 2) ont chacun recu un audit complet, y compris `S34-EXC-TACHEB` (refuse) et le palier 2 (audite en bloc, non proratise entre `QLM-CP-11` non livree et `QLM-CP-14` livree - meme principe que la section 15.2 du planning : *"je ne divise pas... je constate le fait tel qu'il est"*) |
| C | 2,5 | **2,5** (`verdicts-nadia-instance-C.md`, en-tete) | **0** | Les trois stories ont chacune recu un audit complet (huit parties, quatre-vingts manches, section 0 du verdict) |
| **Total** | **9,0** | **8,0** | **1,0** | |

**Seule l'instance A porte un idle**, et il est identique a celui du planning (`S34-CONS-4`,
1,0, non engagee faute de branche ouverte). Les instances B et C ont depense l'integralite de leur
borne engagee.

## 9. Les routages ouverts

| Obstacle | Categorie | Route a | Decision liee | Statut |
|---|---|---|---|---|
| Les six mouvements de matrice, avec leurs deux mentions de couverture partielle neuves | Produit | `camille-po` | - | ouvert, a executer (section 2) |
| `S34-CONS-4` non engagee ; `marc-dev` ecrit les quatre consignes, `nadia-prof` audite ensuite (le `relecteur-pedagogique` vient de corriger `docs/backlog/backlog.md`, qui portait l'inverse) | Produit / capacite | `camille-po` (story), `marc-dev` (execution), `nadia-prof` (audit) | Arbitrage prealable de s34, section 4.2 ; planning, section 15.1 | ouvert, renvoyee a s35 |
| `QLM-CP-11` : geometrie des cadres (`armoire`, `porte`) a `ines-design` (`ZON-15`), les six libelles a `nadia-prof` (`ZON-16`) - aucune moitie ne suffit seule | Design / pedagogie | `ines-design`, `nadia-prof` | planning, section 15.2 ; `verdicts-nadia-instance-B.md`, section 4 | ouvert |
| Le recouvrement des boutons de zone sur le dessin qu'ils designent (tout paquet de designation, pas seulement celui-ci) | Design / architecture | `ines-design`, `theo-archi` | `verdicts-nadia-instance-B.md`, section 4 (`ZON-15` b) | ouvert |
| `S34-SCO1-REMEDE` refuse (`SCO-R1`) : une story qui porte l'ecran de fin, pas le score | Produit | `camille-po` | `verdicts-nadia-instance-A.md`, section 11 | ouvert |
| `S34-EXC-TACHEB` refuse (`EXC-R10` bloquant, `EXC-R11`, `EXC-R12`) : les cinq indices de la tache B, a traiter dans un meme geste | Produit / pedagogie | `camille-po`, via `marc-dev` | `verdicts-nadia-instance-B.md`, section 7 | ouvert |
| `QLM-CP-14` refuse (`FRI-R1` bloquant) : deux textes alternatifs et l'indice de l'item 1 | Pedagogie / contenu | `lea-dev`, via `sofia-sm` | `verdicts-nadia-instance-B.md`, section 7 | ouvert |
| `QLM-CP-14` refuse (`FRI-R2` bloquant, `FRI-R3`, `FRI-R4`) : le pictogramme du lever, reconnaissable a la taille servie | Design | `ines-design`, via `sofia-sm` | `verdicts-nadia-instance-B.md`, section 7 | ouvert |
| La regle de poste du registre (ecrire depuis un worktree dedie) n'a tenu pour aucune des trois instances, cinquieme occurrence du defaut qu'elle devait fermer - `theo-archi` la porte contre lui-meme (bon destinataire, mauvais mecanisme) | Technique, gouvernance | `theo-archi` (contre lui-meme), orchestration (`sofia-sm`) | `confirmation-archunit.md`, section 7 | ouvert, aucun `ADR` ecrit (le motif qui en justifierait un n'est pas encore survenu) |
| Le faux vert en population vide - proxy structurel mesure (41/198 methodes), cliquet possible mais non pose | Technique, garde-fou | `theo-archi` | `confirmation-archunit.md`, section 8 | ouvert et date, refus explicite d'ecrire la regle aujourd'hui |
| L'ecart "71 titres de l'accueil" contre trois mesures independantes a "70" | Redaction / pedagogie | `nadia-prof`, via `sofia-sm` | `relecture.md`, section 3.1 | ouvert |
| **Trouve et corrige dans ce meme geste** : `PropositionsAccentueesJouablesIT` ne sait pas reproduire un caractere accentue hors Latin-1 (`œ`) ; la correction orthographique du `relecteur-pedagogique` sur `cp-eps-corps-effort.yaml` en a introduit un dans un `bonneReponse`, rouge reel mesure sur ce diff (section 10). Stopgap applique : cette seule occurrence revient a `coeur` sans ligature, en attendant la decision de fond | Technique, test | `theo-archi` (l'IT doit-elle couvrir `œ`, deja Latin-1 etendu/Windows-1252 dans son propre motif) ; `relecteur-pedagogique` (l'occurrence reste sans ligature tant que la question technique n'est pas tranchee) | - | ouvert, dette datee |

## 10. Porte technique - la CI, sur mon propre diff, les quatre jobs nommes un par un

**Ce que j'ai trouve avant de pouvoir l'annoncer verte.** `yanis-qa` et `theo-archi` ont chacun
mesure les quatre jobs verts sur `a7852398` (le commit de fusion), avant que les huit documents de
ce sprint (planning, trois verdicts, rapport qualite, confirmation ArchUnit, relecture) ne soient
tous en place. **Je ne deduis pas la porte technique de ces mesures anterieures** : je relance les
quatre jobs moi-meme, maintenant, sur l'arbre de travail complet - `a7852398` plus tous les
fichiers non commites de ce sprint. Un premier passage a trouve un rouge reel, non mesure par
personne avant moi parce que personne n'avait encore lance la CI sur ce diff complet : voir
ci-dessous.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s b45e5927..HEAD \| verifier-prefixes.sh` | **VERT.** *"Tous les commits portent un prefixe d'agent connu."* |
| `chemins` | `verifier-chemins-test.sh`, puis l'union de `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` et `git ls-files --others --exclude-standard` passee a `verifier-chemins.sh` | **VERT.** *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | la boucle du job sur ses trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **VERT. 9 juge(s) pur(s) execute(s)**, aucun motif vide |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules | **ROUGE au premier passage**, corrige, **VERT au second** - detail ci-dessous |

**Le rouge, root-cause avant correction.** Le premier `clean verify` sur l'arbre complet
(`a7852398` plus tout le diff non commite) a rendu `BUILD FAILURE` :
`PropositionsAccentueesJouablesIT.chaqueRepresentationAccentueeDuCatalogueEstJouableSansPlanterLaManche`
a leve `IllegalStateException: caractere hors Latin-1, cette IT ne sait pas le reproduire`, sur la
chaine *"pour que les muscles et le cœur récupèrent"*. Cette IT rejoue chaque proposition non-ASCII
du catalogue reel dans une requete encodee en `ISO_8859_1` strict, et refuse explicitement tout
caractere au-dela de `0xFF` - `œ` (U+0153) en est hors, contrairement aux voyelles accentuees
usuelles (`à` a `ÿ`) qui tiennent toutes dans ce plafond. `git diff a7852398 --
cp-eps-corps-effort.yaml` isole la cause exacte : la correction orthographique du `relecteur-
pedagogique` (`relecture.md`, section 1.1, *"coeur corrige en cœur (ligature)"*) a touche quatre
occurrences dans ce fichier, dont une seule vit dans un champ `bonneReponse` (ligne 194) - le seul
des quatre que cette IT scanne. **Aucun agent ne l'avait mesure avant moi, parce qu'aucun n'avait
encore lance le job `build` sur le diff complet incluant la relecture** : `yanis-qa` et
`theo-archi` ont mesure `a7852398`, avant la correction ; le grep de `relecture.md` lui-meme
verifie l'orthographe, jamais la CI. `œ` n'apparaissait auparavant dans aucun autre `bonneReponse`
ni `distracteurs` du catalogue (verifie par balayage : sa seule autre occurrence est un commentaire
YAML, hors du contenu scanne).

**Stopgap applique, minimal et reversible** : la seule occurrence en cause (ligne 194,
`bonneReponse`) revient a *"coeur"* sans ligature ; les trois autres occurrences de la meme
correction (deux `enonce`, un `indice`, aucun scanne par cette IT) gardent leur ligature. Rejoue :
`PropositionsAccentueesJouablesIT` seule (1 test, 0 echec), puis le reacteur complet - **`BUILD
SUCCESS`**, six modules `SUCCESS`. **Ce n'est pas une decision d'architecture ni de redaction** :
c'est le geste minimal qui rend ce diff committable sans laisser un rouge derriere une porte
annoncee verte. La question de fond - cette IT doit-elle couvrir `œ`, deja present ailleurs dans
le depot et deja legitime en francais - reste ouverte et routee (section 9), et l'occurrence
reste sans ligature tant qu'elle ne l'est pas.

**Compte de tests, methode dans le meme paragraphe** : somme de l'attribut `tests` sur les **196**
fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports`, mesuree sur le disque apres ce
`clean verify` - **1464 tests, 0 echec, 0 erreur**, identique au chiffre annonce par `sofia-sm`
(planning, section 14), `yanis-qa` et `theo-archi` sur `a7852398` seul : le stopgap ne change ni
n'ajoute aucun test, il corrige une donnee de contenu. **JDK 21.0.10**, rapporte par la machine
virtuelle des tests elle-meme.

**Le registre d'identifiants est stable sur cet arbre.** Le rouge transitoire que `theo-archi`
mesure dans `confirmation-archunit.md` (section 7 - huit identifiants de l'instance C non alloues
pendant que les autres instances mesuraient) concernait un etat intermediaire du checkout partage,
avant que l'instance C n'ecrive ses huit allocations. **Sur l'arbre que je committe - toutes les
allocations des trois instances en place, 640 lignes, 103 prefixes** - le job `build` inclut
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, vert dans le passage ci-dessus (aucun
echec sur les 1464 tests) : le registre a 640/103 est verifie par la CI elle-meme sur cet arbre,
pas suppose.

**Porte technique : verte, les quatre jobs nommes un par un, sur l'arbre complet, un rouge reel
trouve et corrige dans le meme geste plutot que tu.**

## 11. Rapport qualite - `yanis-qa`

- **Mesure** : les quatre jobs relances un par un sur `a7852398`, worktree principal propre.
  **Vert**, 1464 tests, 196 fichiers de rapport, 0 echec, 0 erreur (deux executions independantes
  du reacteur, convergentes).
- **Trois points precis mis a l'epreuve, pas seulement lus** : `ADR-0090` referme un rouge reel
  exactement au commit de fusion de `s34-theo` (rouge sur `2c3463b3`, vert sur `5bb6f9db`) ; les
  cinq motifs d'exemption recomptes independamment sont exacts, aucun perime ; le defaut "vert a
  vide" de `PaquetCpEmcExclusionTest` est corrige et les deux garde-fous qui le remplacent mordent
  sur mutation.
- **Non couvert, nomme plutot que suppose** : aucune trace d'une partie jouee de bout en bout pour
  le contenu corrige ce sprint, troisieme sprint consecutif (section 6 ci-dessus) ; la dette
  generale de `ADR-0089` (25 fichiers sur 95 lisant une tache par operation de chaine) inchangee ;
  une fragilite du meme registre que `PaquetCpEmcExclusionTest` (deux methodes de
  `PaquetCpEmcRegleSouhaitLoiTest` sans garde de non-vacuite propre) nommee, non corrigee.
- **Avis** : porte technique verte sur `a7852398`. Le Done pedagogique reste entier a `nadia-prof`.
  Cette mesure ne porte pas sur le stopgap de la section 10 ci-dessus, introduit apres son passage.

## 12. Confirmation ArchUnit - `theo-archi`

- **Mesure independante** : quatre jobs verts sur `a7852398`, 1464 tests, **33 regles ArchUnit
  executees** (32 `recreo-bootstrap`, 1 `recreo-adapter-web`), **34 objets `ArchRule`** au total -
  inchange depuis s32, `ADR-0090` disant lui-meme pourquoi sa regle centrale n'en est pas une.
- **Zero ligne de production dans `catalogue.yaml` ou dans un fichier de configuration n'introduit
  de dependance neuve** : 8 fichiers `src/main/java` touches, 0 `+import` ajoute.
- **La regle centrale du chantier mord, prouvee par mutation** : une conception que `ADR-0080`
  refuse, ecrite puis revertee dans `DesignationSession`, rend `uneMecaniqueIgnoreLaGeometrie`
  rouge (12 tests, 1 echec) avant d'etre restauree.
- **`ADR-0090` passe de `Propose` a `Accepte`** : le rouge construit se referme exactement a la
  fusion de `s34-theo` sur l'arbre qui porte deja le remede de `s34-sofia`, ni avant ni apres ;
  une mecanique concrete neuve sans scenario rend la regle rouge, verifie par ajout puis retrait.
- **`ADR-0080` reste `Propose`** : six des huit regles listees mordent sur du contenu reel
  (`cp-frise-de-la-journee`), **deux mordent sur un jeu fabrique d'`adapter-web`** (garde-fous 1 et
  3, plancher de cible tactile compris) - structurel, `adapter-web` ne dependant pas
  d'`adapter-content`. **Le garde-fou 3 aurait laisse passer le defaut qui a fait retirer
  `cp-plan-de-la-classe`** (section 6 ci-dessus). La clause manquante a `Accepte` est nommee dans
  l'amendement de l'`ADR`, pas ecrite dans ce geste.
- **Le registre d'identifiants** : la regle de poste ecrite au planning n'a tenu pour aucune des
  trois instances (section 9 ci-dessus, obstacle route contre lui-meme).
- **Le faux vert en population vide** : proxy structurel mesure (41/198 methodes sur `a7852398`),
  cliquet possible mais non pose, dette datee (section 9).

## 13. Relecture - `relecteur-pedagogique`

- **Six corrections directes** : la ligature `œ` sur `cp-eps-corps-effort.yaml` (quatre
  occurrences - dont celle que la section 10 ci-dessus a du reverter en partie) ; l'accord du
  participe sur `cp-emc-exclusion.yaml` (`EXC-R12`, route par `nadia-prof`) ; trois inexactitudes
  d'en-tete sur `cp-eps-regles-jeu-collectif.yaml`, nommees fausses par l'instance C elle-meme ;
  `docs/backlog/backlog.md` corrige sur l'affectation de `S34-CONS-4` (contradisait une decision
  deja rendue par `nadia-prof` et deja corrigee dans `planning.md`).
- **Toutes les references verifiees resolvent** : `ADR`, `DEC`, noms de test, chemins de fichier.
- **Un ecart trouve** : *"71 titres de l'accueil"* (`verdicts-nadia-instance-A.md`, section 6.3)
  contredit trois mesures independantes a **70** (`rapport-qualite.md`, `confirmation-archunit.md`,
  et un comptage direct du relecteur). Route a `nadia-prof`, via `sofia-sm` (section 9 ci-dessus) -
  pas retouche, un chiffre a l'interieur d'un bloc de validation pedagogique n'etant jamais corrige
  par un autre agent.
- **Conventions de redaction** : conformes, aucun tiret cadratin, aucun emoji, aucun chemin de
  poste de travail.

## 14. Porte pedagogique - `nadia-prof`

- **Verdicts, ligne par ligne** : `HG-CM1-12` VALIDE SOUS RESERVE ; `EMC-CP-07` VALIDE SOUS
  RESERVE ; `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`, `EPS-CP-11` VALIDE SOUS RESERVE chacune ;
  `S34-ANG-CORR` valide sous reserve sans mouvement de ligne ; `S34-SCO1-REMEDE` REFUSE (`SCO-R1`
  bloquante) ; `S34-EXC-TACHEB` REFUSE (`EXC-R10` bloquante) ; `QLM-CP-14` REFUSE (`FRI-R1`,
  `FRI-R2` bloquantes) ; `S33-PAL2-TROU-A` et `-B` payees, verifiees a l'ecran.
- **Fiches** : les trois verdicts d'instance, `squad/sprints/s34-.../verdicts-nadia-instance-A.md`,
  `-B.md`, `-C.md`, et les fiches de conformite qu'ils mettent a jour, dont deux neuves
  (`FICHE-bandit-manchot-corps-effort-eps-cp.md`, `FICHE-designation-frise-de-la-journee.md`).
- **Vingt-neuf reserves neuves ou reconduites au total**, dont trois bloquantes (`SCO-R1`,
  `EXC-R10`, et le couple `FRI-R1`/`FRI-R2` sur `QLM-CP-14`) : le detail complet, avec regime et
  prix, vit dans les trois documents de verdict.
- **Ce document n'invente aucun jugement pedagogique** : il recapitule ce que les trois instances
  ont deja rendu et deja ecrit.

## 15. Ce que cette review ne fait pas

- Elle ne deplace pas `squad/programme/MATRICE-couverture.md` : les six mouvements sont routes a
  `camille-po` (section 2).
- Elle ne ratifie pas le chiffre du registre de dette comme une decision produit : la mesure de la
  section 7 applique la methode de `camille-po`, la ratification lui revient.
- Elle ne prononce aucun Done pedagogique au-dela de ce que les trois instances ont deja ecrit.
- Elle ne tranche ni la geometrie du plan de classe, ni le plancher de lisibilite d'un libelle de
  zone, ni la question de fond sur `PropositionsAccentueesJouablesIT` et `œ` : les trois sont
  routees (section 9).
- Elle ne prend aucune decision produit, pedagogique ou d'architecture neuve : les deux `ADR`
  mis a jour ce sprint (`ADR-0090` accepte, `ADR-0080` amende) sont l'oeuvre de `theo-archi`, deja
  ecrite avant cette ceremonie et seulement recapitulee ici.

## Decisions prises

Aucune `DEC` n'est creee ni amendee par cette review. Deux `ADR` changent de statut ce sprint,
tous deux l'oeuvre de `theo-archi` avant l'ouverture de cette ceremonie : `ADR-0090` passe de
`Propose` a `Accepte` ; `ADR-0080` reste `Propose`, amende pour porter la mesure exacte de ce qui
lui manque. Le detail complet de ces deux mouvements, avec leur methode, vit dans
`confirmation-archunit.md`, sections 5 et 6, et dans `ADR-index.md`.
