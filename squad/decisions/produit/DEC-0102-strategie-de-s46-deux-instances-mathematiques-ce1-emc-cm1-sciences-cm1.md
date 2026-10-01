# DEC-0102 - Strategie de s46 : deux instances paralleles de `nadia-prof`, une ligne de mathematiques
CE1 et deux lignes chacune d'enseignement moral et civique CM1 et de sciences et technologie CM1

- **Statut** : Acceptee, engagee le 2026-10-01.
- **Date** : 2026-10-01
- **Auteur** : `camille-po`
- **Sprint** : s46 (deux instances paralleles de `nadia-prof`)
- **Prealable** : `DEC-0098` (methode du dispositif a deux instances : borne 8,0, plafond individuel
  5,0 par instance, au plus trois lots par instance, `PAR-8`) ; `DEC-0101` (dernier engagement d'un
  dispositif a deux instances, s45, meme borne reconfirmee le jour meme de ce document) ; `DEC-0099`
  section 9.4 et `DEC-0100` section 2 (regle operationnelle : une seule ligne de mathematiques CE1
  par sprint, reconduite s43/s44/s45, motif de marge et non de fond) ; `DEC-0083`/`DEC-0087` (fenetre
  a trois instances, "Plan 2", ouverte s32 et explicitement refermee s34 - voir section 5) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, section 13 (`PAR-1` a `PAR-8`, `CYC-1`) ; `squad/conformite/ARBITRAGE-PREALABLE-s43-
  mathematiques-ce1-lot-1.md` (verdict et conditions de `MAT-CE1-05`) ;
  `squad/programme/enseignement-moral-et-civique/competences-cm1.md` (verdicts et conditions de
  `EMC-CM1-01` et `EMC-CM1-09`) ; `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-
  de-sciences-et-le-prix-des-quinze-reserves.md` (verdicts et conditions de `SCI-CM1-03` et
  `SCI-CM1-16`).
- **Lien** : `squad/programme/MATRICE-couverture.md` (etat de sortie de s45) ;
  `docs/backlog/backlog.md`, section "Sprint 46" (cinq stories ecrites dans le meme mouvement).
- **Competence programme visee** : transverse - ce document porte sur l'objectif, le perimetre et la
  borne d'un sprint, pas sur le contenu d'une ligne.

---

## 0. Commande et methode

**Instruction recue directement** (meme canal que `DEC-0098`/`DEC-0101`, forme 3 de
`squad/README.md` section 2 bis) : preparer le planning de s46 avec un objectif de fermeture de
lignes CE1 et un dispositif a deux ou trois instances paralleles de `nadia-prof`. Ce document :

1. Reverifie l'etat de la matrice et la borne d'audit a la date de ce document (section 1).
2. Reverifie ligne par ligne le vivier de mathematiques CE1 et retient une ligne, sous la regle
   operationnelle deja reconduite trois fois (section 2).
3. Reverifie ligne par ligne le vivier hors mathematiques apres les quatre fermetures de s45
   (section 3).
4. Tranche entre deux et trois instances, et explique le choix (section 4).
5. Verifie une par une les clauses `PAR-1`, `PAR-2`, `PAR-7`, `PAR-8` et `CYC-1` pour la composition
   retenue (section 5).
6. Compose le sprint sous la borne (section 6) et engage directement (section 7).

## 1. Etat de la matrice et borne d'audit - verifies ce jour

**Etat de sortie de s45** : quatre lignes fermees `couvert-valide` le 2026-10-01 (`MAT-CE1-04`,
`MUS-CP-05`, `EPS-CM1-09`, `SCI-CM1-14`), verifie contre `squad/programme/MATRICE-couverture.md`
(lignes 727, et les entrees d'education musicale CP, EPS CM1, sciences et technologie CM1 citees en
section 3). `LVE-CM1-06` reste `non couvert`, refusee a la saisie (`DEC-0101` section 10, motif
`R6`).

**Borne a deux instances** : 8,0, plafond individuel dur 5,0 par instance, au plus trois lots par
instance (`PAR-8`). Verifiee non revisee le jour meme : `DEC-0101` (section 2) l'a reconfirmee ce
2026-10-01 contre le meme fichier d'arbitrage (`ARBITRAGE-PREALABLE-s29`, section 13.5), et aucun
commit ni aucune `DEC` ne s'intercale entre `DEC-0101` et ce document.

## 2. Vivier de mathematiques CE1 - reverifie ligne par ligne, une ligne retenue

Onze lignes `non couvert` sur les dix-huit du referentiel (`squad/programme/mathematiques/
competences-ce1.md`), verifiees ce jour contre `MATRICE-couverture.md`, section "Mathematiques -
CE1" (lignes 724-741) : `MAT-CE1-01` (couverture partielle nommee, trois verbes sur quatre),
`MAT-CE1-02` (couverture partielle nommee, trois verbes sur quatre), `MAT-CE1-05` (soustraction),
`MAT-CE1-06` (sens de la multiplication), `MAT-CE1-07` (tables), `MAT-CE1-08` (problemes),
`MAT-CE1-10` (masses et contenances), `MAT-CE1-11` (heure et calendrier), `MAT-CE1-14`
(quadrillage), `MAT-CE1-15` (figures planes, couverture partielle nommee), `MAT-CE1-17` (axe de
symetrie). Toutes portent deja un verdict ecrit par `nadia-prof`
(`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`,
`...-lot-2.md`, `...-lot-3.md`).

**Une seule ligne de mathematiques CE1 par sprint : quatrieme reconduction, et je l'ecris comme
telle plutot que de la laisser silencieuse.** La regle a ete posee a s43 (`DEC-0099` section 9.4),
reconduite a s44 (meme document), reconduite une troisieme fois a s45 (`DEC-0101` section 3.1, qui
la qualifie elle-meme de "reconduite une troisieme fois"). Ce document la reconduit une
**quatrieme** fois. Le motif reste celui de la marge, pas du fond : deux lignes de mathematiques CE1
dans le meme sprint, avec le tarif uniforme de 1,0, ne liberent aucune marge de rejeu propre a la
matiere si l'une des deux est refusee ou reportee - exactement le raisonnement de `DEC-0099` section
9.4, jamais invalide depuis. **Je signale neanmoins, pour que ce ne soit pas a redemander une
cinquieme fois sans l'avoir ecrit** : quatre reconductions d'une meme regle commencent a ressembler
a une direction non choisie plutot qu'a une marge sprint par sprint (le precedent cite par
`squad/README.md` est `DEC-0007`, trois reports consecutifs d'un sujet different). Je ne rouvre pas
la regle ici - rien ne justifie de fond un changement - mais je le nomme explicitement pour que la
decision de la reconduire une cinquieme fois, si elle se presente a s47, soit prise en connaissance
de cette suite plutot que sprint par sprint.

**Ligne retenue : `MAT-CE1-05` - poser et effectuer une soustraction.** Verdict **ACCEPTE SOUS
CONDITIONS**, `SOP-CE1-1` a `SOP-CE1-6` (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`,
section 5.2), deja nommee par `DEC-0099` section 9.7 et par `DEC-0100` section 2 comme "candidate
suivante, disponible au meme tarif et aux memes conditions" apres que `MAT-CE1-04` a ete choisie a
s45. Tarif 1,0 (mathematiques, `nadia-prof` source factuelle, `DEC-0074`/`DEC-0077` section 8.2).

## 3. Vivier hors mathematiques - reverifie ligne par ligne apres les fermetures de s45

**Quatre lignes sortent du vivier, fermees a s45** : `MUS-CP-05`, `EPS-CM1-09`, `SCI-CM1-14`
(`couvert-valide`) ; `LVE-CM1-06` (refusee, motif `R6`, hors vivier tant que le refus n'est pas
leve). Verifie ce jour contre `MATRICE-couverture.md`, pas repris de confiance depuis `DEC-0101`.

Reste disponible, verifie ligne par ligne ce jour, **sans ligne retiree pour motif de fond parmi les
candidates retenues ci-dessous** :

| Ligne | Matiere / niveau | Statut verifie ce jour | Verdict pedagogique prealable |
|---|---|---|---|
| `QLM-CP-15` | questionner le monde CP | `non couvert` | jouable, `BIA-3` |
| `EMC-CP-14` | EMC CP | `non couvert` | jouable sous `EMC-7` ; piege d'exactitude connu (18 vs 112) |
| `EMC-CM1-01` | EMC CM1 | `non couvert` | jouable sous `EMC-1`, `EMC-2` |
| `EMC-CM1-09` | EMC CM1 | `non couvert` | jouable sous `EMC-6` |
| `EMC-CM1-10` | EMC CM1 | `non couvert` | jouable, connaissance seule |
| `LVE-CP-06` | langues vivantes CP | `non couvert` | jouable en francais seul, `BIA-1`/`BIA-2` |
| `SCI-CM1-03` | sciences et technologie CM1 | `non couvert` | jouable, description seule |
| `SCI-CM1-16` | sciences et technologie CM1 | `non couvert` | jouable, `BIA-2` |

`QLM-CP-16`, `EMC-CP-04`, `EMC-CM1-02`, `EMC-CM1-03` restent ecartees pour motif de fond (biais ou
`FER-3` epuisee avant qu'un item n'existe), confirme inchange depuis `DEC-0100`/`DEC-0101`, non
rouvertes ici.

**Lignes retenues : `EMC-CM1-01`, `EMC-CM1-09`, `SCI-CM1-03`, `SCI-CM1-16`.** Choisies plutot que
`QLM-CP-15`, `EMC-CP-14`, `EMC-CM1-10` et `LVE-CP-06` pour un motif de clarte de continuite cycle 2 /
cycle 3, pas de fond sur les lignes ecartees, qui restent disponibles pour un sprint ulterieur :

- `EMC-CM1-01` (identifier une emotion) a pour objet du monde reel l'exact miroir de `EMC-CP-01`
  (identifier et nommer les emotions d'autrui), `couvert-valide` depuis s31 - un cas direct
  d'application de `CYC-1`, verifie en section 5.
- `EMC-CM1-09` (role des institutions proches) a pour objet du monde reel l'exact miroir de
  `EMC-CP-13` (roles de l'ecole et de la commune), `couvert-valide` depuis s41 - meme cas direct.
- `SCI-CM1-03` et `SCI-CM1-16` n'ont, verifie ligne par ligne contre les deux referentiels de
  questionner le monde CP et de mathematiques (aucune ligne de trajectoire, vitesse, seisme,
  eruption ou tempete n'y figure), **aucun miroir cycle 2 deja servi** : `CYC-1` ne s'y applique pas,
  ce qui est note explicitement plutot que suppose en section 5.

**Ce que je ne fais pas** : je n'ecarte ni `QLM-CP-15`, ni `EMC-CP-14`, ni `EMC-CM1-10`, ni
`LVE-CP-06` pour un motif de fond - elles restent au vivier. Je n'ouvre pas non plus `EMC-CP-14` aux
cotes de `EMC-CM1-01`/`EMC-CM1-09` dans ce document : `EMC` CP et `EMC` CM1 comptent pour une seule
matiere au sens de `PAR-2` (precedent `DEC-0098`), et l'ajouter porterait l'instance qui les recoit
a trois lots d'EMC, ce que rien n'impose et ce qui n'ajoute pas de diversite de matiere au sprint.

## 4. Deux instances, pas trois - et c'est chiffre, pas suppose

**La fenetre a trois instances ("Plan 2") a ete ouverte par `DEC-0083` pour trois sprints nommes
(s32 a s34) et explicitement refermee par `DEC-0087`** : *"la fenetre de trois sprints de `DEC-0083`
pour le Plan 2 se referme avec s34"* (`DEC-0087`, section 4). Le fichier qui derive la borne a trois
instances (`ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-
prix-d-une-ligne-marginale.md`) n'a recu aucun commit depuis s32 (verifie par `git log` ce jour), et
aucune `DEC` entre `DEC-0087` et ce document ne rouvre la fenetre ni ne rederive une borne a trois
instances pour un sprint hors de cette fenetre.

**Ce que j'en tire** : reactiver un dispositif a trois instances pour s46 ne serait pas une reprise
de la borne de `DEC-0083` (12,0, decotee par paire de coutures a 4,0 de budget opposable par
instance, `PAR-9`) - cette borne etait specifique a une fenetre de trois sprints nommement fermee,
pas une regle generale reutilisable a volonte. Ce serait une **borne neuve**, qui demande une
derivation neuve - exactement le geste que `nadia-prof` a fait dans `ARBITRAGE-PREALABLE-s29` pour
deux instances et dans `ARBITRAGE-PREALABLE-s32` pour trois, et que je ne fabrique pas moi-meme a sa
place (section "Frontieres - ce que tu ne fais pas", `CLAUDE.md`).

**Decision : deux instances pour s46.** L'instruction recue autorise explicitement "2 a 3" ; je
retiens deux, parce que le vivier instruit disponible (section 2 et 3) ferme a 7,0/8,0 sous la borne
a deux instances bien etablie, sans qu'aucune ligne candidate supplementaire au vivier ne force la
main vers une troisieme instance, et sans reconstruire seule une borne a trois instances que
`nadia-prof` seule peut deriver. **Ce que je route** : si l'utilisatrice souhaite explicitement
trois instances, une derivation neuve de la borne correspondante par `nadia-prof` est un prealable,
pas un geste que ce document peut se permettre d'anticiper (section 8).

## 5. Verification des clauses de non-collision, une par une

**`PAR-1` - aucun lot partage entre deux instances.** Chacune des cinq lignes retenues (section 6)
est affectee a exactement une instance. Verifie par construction.

**`PAR-2` - aucune matiere et aucun niveau partages entre les deux instances.** Instance A :
mathematiques CE1, enseignement moral et civique CM1. Instance B : sciences et technologie CM1.
Trois matieres distinctes, aucune partagee entre A et B. Verifie ligne par ligne.

**`PAR-7` - une instance porte l'accumulation, l'autre non.** Cette clause a ete ecrite a s29 pour
un cas precis : l'EMC venait d'entrer au perimetre et aucune instance n'avait encore d'accumulation
dessus, ce qui imposait de donner ce terrain vierge a l'instance qui n'en portait pas ailleurs.
**Ce cas ne se represente pas ici.** Les trois matieres retenues (mathematiques CE1, EMC CM1,
sciences et technologie CM1) portent chacune une accumulation deja ancienne et chiffree ci-dessous.
Aucune des deux instances ne recoit un terrain vierge que l'autre porterait deja : `PAR-7` ne dicte
donc aucune affectation particuliere pour ce sprint, et je le dis explicitement plutot que de
laisser la clause sans reponse.

**Chiffre, pas suppose** : mathematiques CE1 compte six lignes `couvert-valide`
(`MAT-CE1-03`, `-04`, `-12`, `-13`, `-16`, `-18`, la premiere close a s37) ; enseignement moral et
civique CM1 en compte cinq (`EMC-CM1-04`, `-05`, `-07`, `-08`, `-11`, la premiere close a s29) ;
sciences et technologie CM1 en compte quatorze (close a s30 pour les deux premieres), verifie contre
`MATRICE-couverture.md` ligne par ligne.

**`PAR-8` - plafond individuel 5,0, au plus trois lots par instance.** Instance A : trois lots,
4,0/5,0. Instance B : deux lots, 3,0/5,0. Les deux sous le plafond individuel et sous la limite de
lots.

**`CYC-1` - continuite cycle 2 / cycle 3, lecture obligatoire pour l'instance qui audite.**

- `EMC-CM1-01` (identifier une emotion, distinguer ressentir et agir) partage son objet avec
  `EMC-CP-01` (identifier et nommer les emotions d'autrui), `couvert-valide` depuis s31
  (`bandit-manchot-emotions-autrui-cp`). **Duree due** : l'instance qui audite `EMC-CM1-01` lit ce
  paquet avant de juger - aucun item de CM1 ne doit contredire les quatre emotions deja servies au
  CP (joie, tristesse, colere, peur). Note deja posee par le referentiel CM1 lui-meme comme un point
  de progression ("la seconde moitie - distinguer ce que l'on ressent de ce que l'on fait - est la
  partie utile au CM1"), pas une contradiction a lever.
- `EMC-CM1-09` (role de quelques institutions proches) partage son objet avec `EMC-CP-13`
  (roles de l'ecole et de la commune), `couvert-valide` depuis s41
  (`bandit-manchot-roles-ecole-commune-cp`). **Duree due** : l'instance qui audite `EMC-CM1-09` lit
  ce paquet avant de juger - aucun item de CM1 ne doit contredire les roles deja servis au CP
  (directeur, mairie, pompiers, medecin).
- `SCI-CM1-03` (trajectoire et vitesse) et `SCI-CM1-16` (phenomenes geologiques et
  meteorologiques) : verifie contre les deux referentiels de cycle 2 les plus proches (questionner
  le monde CP, mathematiques CP et CE1) qu'aucune ligne deja servie n'y porte sur le mouvement d'un
  objet, un seisme, une eruption ou une tempete. **Aucune occurrence trouvee : `CYC-1` ne s'applique
  pas a ces deux lignes**, note explicitement plutot que suppose absente.

Aucune clause de frontiere (objet du monde reel partage entre deux lignes de matieres differentes,
critere de `nadia-prof`, `ARBITRAGE-PREALABLE-s32` section 6.4) n'est due entre les cinq lignes
retenues : verifiee sur les six paires croisees entre l'instance A (`MAT-CE1-05`, `EMC-CM1-01`,
`EMC-CM1-09`) et l'instance B (`SCI-CM1-03`, `SCI-CM1-16`), aucune ne partage un objet.

## 6. Composition retenue

| Instance | Ligne | Matiere / niveau | Tarif | Motif |
|---|---|---|---|---|
| A | `MAT-CE1-05` Poser et effectuer une soustraction | mathematiques CE1 | 1,0 | `ACCEPTE SOUS CONDITIONS`, `SOP-CE1-1` a `SOP-CE1-6` ; candidate suivante deja nommee par `DEC-0099`/`DEC-0100` |
| A | `EMC-CM1-01` Identifier une emotion, distinguer ressentir et agir | enseignement moral et civique CM1 | 1,5 | jouable sous `EMC-1`, `EMC-2` ; `CYC-1` due contre `EMC-CP-01` (section 5) |
| A | `EMC-CM1-09` Role de quelques institutions proches | enseignement moral et civique CM1 | 1,5 | jouable sous `EMC-6` ; `CYC-1` due contre `EMC-CP-13` (section 5) |
| **Sous-total instance A** | | | **4,0 / 5,0** | marge individuelle 1,0, 3 lots |
| B | `SCI-CM1-03` Decrire un mouvement par sa trajectoire et sa vitesse | sciences et technologie CM1 | 1,5 | jouable, description seule ; recouvrement avec mathematiques CM1 exclu par construction (aucune ligne de calcul de vitesse au perimetre) |
| B | `SCI-CM1-16` Phenomenes geologiques et meteorologiques, risques | sciences et technologie CM1 | 1,5 | jouable sous `BIA-2` ; aucun miroir cycle 2, `CYC-1` non due (section 5) |
| **Sous-total instance B** | | | **3,0 / 5,0** | marge individuelle 2,0, 2 lots |
| **Total du sprint** | | | **7,0 / 8,0** | marge de sprint 1,0 non allouee |

**Pourquoi 7,0 et non 8,0 exactement** : au tarif uniforme de 1,5 pour les quatre lignes hors
mathematiques, aucune combinaison entiere du vivier instruit (section 3) n'atteint 8,0 sans l'une de
deux concessions ecartees - ouvrir une seconde ligne de mathematiques CE1 (contredirait la regle
operationnelle reconduite une quatrieme fois, section 2, sans motif de fond pour la rouvrir) ou
porter une instance a un quatrieme lot (au-dela du plafond de trois lots par instance, `PAR-8`).
**La composition retenue est donc le maximum atteignable sous la borne**, avec une marge de sprint
de 1,0 retenue par anticipation plutot qu'apprise a l'usage, meme raisonnement que `DEC-0098` et
`DEC-0101`.

## 7. Engagement direct

**Perimetre engage** : les cinq lignes de la section 6, reparties sur deux instances de
`nadia-prof`, total 7,0/8,0. Stories ecrites dans `docs/backlog/backlog.md`, section "Sprint 46",
dans le meme mouvement que ce document. `squad/programme/MATRICE-couverture.md` n'est pas modifiee :
les cinq lignes restent `non couvert` jusqu'au Done pedagogique de l'instance qui les audite.

## 8. Ce que ce document route

- **Ouverture du dossier de sprint** : a `sofia-sm`, avec affectation nominative des developpeurs et
  des deux instances de `nadia-prof`.
- **Avis de faisabilite de `theo-archi`** sur les cinq lots : aucun arbitrage pedagogique prealable
  neuf n'est du, les cinq lignes etant deja instruites (sections 2 et 3).
- **A `sofia-sm` et a l'utilisatrice, non bloquant mais a peser explicitement** : la reconduction
  d'une quatrieme fois de la regle "une seule ligne de mathematiques CE1 par sprint" (section 2) et
  la question de savoir si une troisieme instance doit etre ouverte un jour - elle demande au
  prealable une derivation de borne neuve par `nadia-prof`, que ce document ne fabrique pas
  (section 4).
- **A l'instance qui auditera `EMC-CM1-01` et `EMC-CM1-09`** : lire les paquets de cycle 2
  correspondants avant de juger (`CYC-1`, section 5) - ce n'est pas une tache distincte, c'est une
  condition de l'audit lui-meme.

## 9. Ce que ce document ne fait pas

- Il ne rouvre aucune des quatre lignes ecartees pour motif de fond (`QLM-CP-16`, `EMC-CP-04`,
  `EMC-CM1-02`, `EMC-CM1-03`).
- Il n'ouvre pas de troisieme instance et ne derive aucune borne a trois instances : la fenetre qui
  en portait une (`DEC-0083`) est fermee (`DEC-0087`), et une borne neuve reste a deriver par
  `nadia-prof` si l'utilisatrice la demande explicitement (section 4).
- Il ne fixe aucune borne de tirage, aucune forme de distracteur, ni aucun contenu pedagogique pour
  aucune des cinq lignes : ce travail reste entierement a `nadia-prof`.
- Il ne choisit aucun mecanisme technique : ce travail reste a `theo-archi`.
- Il ne garantit la fermeture d'aucune des cinq lignes retenues : l'avis de faisabilite de
  `theo-archi` et le Done pedagogique final de chaque instance de `nadia-prof` restent a rendre.
