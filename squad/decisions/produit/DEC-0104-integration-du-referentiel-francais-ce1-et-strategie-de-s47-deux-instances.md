# DEC-0104 - Integration du referentiel francais CE1 a la matrice, et strategie de s47 : deux
instances paralleles de `nadia-prof` sur mathematiques CE1, questionner le monde CP, enseignement
moral et civique (CP et CM1) et langues vivantes CP

- **Statut** : Acceptee.
- **Date** : 2026-10-02
- **Auteur** : `camille-po`
- **Sprint** : s47 (deux instances paralleles de `nadia-prof`)
- **Prealable** : `DEC-0103` (ouverture du perimetre francais CE1, referentiel route a
  `nadia-prof`, integration a la matrice routee a `camille-po` "des que le referentiel existe -
  jamais avant") ; `squad/programme/francais/competences-ce1.md` (ecrit par `nadia-prof` le
  2026-10-01, 26 lignes) ; `DEC-0098` (methode du dispositif a deux instances : borne 8,0, plafond
  individuel 5,0, au plus trois lots par instance, `PAR-8` ; `PAR-2`, une matiere n'est jamais
  partagee entre deux instances, quel que soit le niveau) ; `DEC-0099`/`DEC-0100`/`DEC-0102`
  (vivier hors mathematiques CE1 reverifie a chaque sprint : `QLM-CP-15`, `EMC-CP-14`,
  `EMC-CM1-10`, `LVE-CP-06` - "instruites jouables", inchangees depuis s45) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` (verdict et conditions de
  `MAT-CE1-07`) ; `squad/conformite/ARBITRAGE-PREALABLE-s46-trois-instances-refusees-le-vivier-
  borne-avant-la-capacite.md` (refus d'une troisieme instance, trois conditions cumulatives de
  reouverture, section 5) ; RAPPORT.md et retro.md de
  `squad/sprints/s46-soustraction-ce1-emc-et-sciences-cm1/`.
- **Lien** : `squad/programme/MATRICE-couverture.md` (vingtieme tableau ajoute par ce document,
  section "Francais - CE1 (cycle 2)") ; `docs/backlog/backlog.md`, section "Sprint 47" (cinq
  stories ecrites dans le meme mouvement).
- **Competence programme visee** : transverse - ce document porte sur l'integration d'un
  referentiel a la matrice et sur l'objectif, le perimetre et la borne d'un sprint, pas sur le
  contenu d'une ligne.

---

## 0. Commande et methode

Ce document fait deux choses distinctes, dans l'ordre :

1. **Integre le referentiel francais CE1 a `MATRICE-couverture.md`** (section 1) - prealable que
   `DEC-0103` routait a `camille-po` "des que le referentiel existe", et que ni `DEC-0102`, ni le
   planning de s46 (section 10), ni la retro de s46 n'ont engage. Verifie d'abord que ce n'etait
   pas deja fait : `grep -n "Francais - CE1" squad/programme/MATRICE-couverture.md`, avant ce
   document, ne rendait rien.
2. **Prepare le planning de s47** : reverifie le vivier instruit (section 2), tranche la question
   d'une troisieme instance (section 3), compose deux instances sous `PAR-1`, `PAR-2`, `PAR-7`,
   `PAR-8` et `CYC-1` (section 4), et route ce qui ne lui appartient pas (section 5).

## 1. Integration du referentiel francais CE1 - faite dans ce geste

**Verifie avant d'agir** : aucune section "Francais - CE1" n'existait dans
`squad/programme/MATRICE-couverture.md` avant ce document - la candidate que `DEC-0103` section 3
et le planning de s46 section 10 signalaient reste non engagee jusqu'ici. Je l'engage ici, sur le
meme patron que l'ouverture des mathematiques CE1 a s36 (`DEC-0089`, meme fichier, section
"Mathematiques - CE1 (cycle 2)") : vingt-six lignes `non couvert`, aucune
`couvert-valide` ni `en cours`, aucun paquet de contenu de francais CE1 n'existant a ce jour.

**Verification du referentiel avant copie** : `grep -oE "FRA-CE1-[0-9]+"
squad/programme/francais/competences-ce1.md | sort -u | wc -l` rend 26, aucun doublon, aucun trou
(`FRA-CE1-01` a `FRA-CE1-26`).

**Effet sur le denominateur, calcule et ecrit avant tout chiffrage de sprint**, exactement comme
`DEC-0089` l'exigeait pour les mathematiques CE1 :

| | Avant (2026-10-02, cloture de s46) | Apres l'integration |
|---|---|---|
| `couvert-valide` | 113 | 113 |
| `en cours` | 3 | 3 |
| `non couvert` | 40 | **66** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 227 | **253** |
| Denominateur atteignable | 156 | **182** |
| **Taux** | **113 / 156 = 72,4 %** | **113 / 182 = 62,1 %** |

Methode et detail complet : `squad/programme/MATRICE-couverture.md`, section "Francais - CE1
(cycle 2)", qui porte ce meme tableau et le recompte ligne par ligne. **Ce recul de 10,3 points
n'est pas une regression du produit** : aucun jeu de francais CE1 n'existe encore, effet de
perimetre annonce d'avance par `DEC-0103` section 4.

### 1.1 Deux points signales par `nadia-prof` en ecrivant ce referentiel

Le referentiel lui-meme, section "Ce que ce referentiel ne dit pas, et ou c'est ecrit", signale
deux manques. Je les traite l'un apres l'autre, sans les confondre.

**(B1) Cinq attendus sans ligne (langage oral, lecture a voix haute, dictee, copie manuscrite,
production d'ecrit).** Le precedent du francais CP est different : `FRA-CP-06` (lire a voix haute)
et `FRA-CP-08` (copier un mot) portent chacune un code et une ligne de matrice, au statut `non
couvrable en l'etat` (`DEC-0046`, `DEC-0066`) - visibles au denominateur, exclues du numerateur,
mais trackees comme n'importe quelle autre competence du programme. Le referentiel CE1 ne donne
**aucun code** `[FRA-CE1-XX]` a ces cinq attendus : ils restent decrits en prose, hors de toute
ligne. **Ce que je decide** : par coherence avec le principe de gouvernance de ce depot - une
competence qui n'est pas une ligne de cette matrice n'est pas suivie - je retiens que ces cinq
attendus **devraient** entrer comme lignes `non couvrable en l'etat`, sur le precedent `DEC-0046`,
plutot que rester seulement decrits en prose sans jamais entrer au denominateur. **Ce que je ne
fais pas** : je n'alloue pas moi-meme les cinq codes necessaires pour ecrire ces lignes - fixer une
competence programme, fut-ce pour la declarer aussitot hors de portee, n'est pas mon autorite
(`DEC-0071`, confirme par `DEC-0089`). **Je route donc a `nadia-prof`** l'ecart qu'elle a
elle-meme cree entre ses deux referentiels de francais : si elle souhaite la meme parite de suivi
qu'au CP, l'allocation des cinq codes lui revient, dans un geste distinct de celui-ci ; si elle
confirme son choix d'origine (ces attendus restent hors ligne et hors denominateur), la matrice
reste telle que ce document la laisse et cette note en est la trace. **Candidate non engagee pour
s47.**

**(B2) La longueur du texte support de `FRA-CE1-04` a `FRA-CE1-07` n'est bornee par rien.**
`LON-CE1-1` et `LON-CE1-2` gouvernent l'enonce d'un paquet CE1, jamais le texte narratif ou
documentaire qui le precede sur une ligne de comprehension. `nadia-prof` ecrit elle-meme que cette
borne reste a poser avant le premier paquet de ces quatre lignes. **Je ne la pose pas** : fixer une
borne de lisibilite pedagogique n'est pas mon autorite, exactement pour le motif de (B1). **Je
signale ce manque a `nadia-prof`** plutot que de le trancher : la borne est due avant tout
chiffrage d'une de ces quatre lignes, et aucune des cinq lignes retenues pour s47 (section 4) n'en
depend - ni `FRA-CE1-04` a `FRA-CE1-07` ni aucune autre ligne de francais ne sont engagees ce
sprint (section 1.2).

### 1.2 Pourquoi aucune ligne de francais CE1 n'est engagee pour s47

**Le referentiel entre dans le vivier ; aucune de ses lignes n'est instruite.** A la difference
d'EMC CM1, de questionner le monde CP ou de sciences et technologie CM1, dont les referentiels
portent un "Jugement de faisabilite" ligne par ligne ecrit par `nadia-prof` des leur ouverture, le
referentiel de francais CE1 n'en porte aucun - exactement comme celui de mathematiques CE1 ne
portait aucun jugement de faisabilite embarque a l'ouverture (`DEC-0089`, s36), et avait attendu un
geste distinct (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1/2/3.md`) avant qu'une premiere
ligne soit engageable. Aucun avis de faisabilite de `theo-archi`, aucun arbitrage prealable de
`nadia-prof` ligne par ligne n'existe a ce jour sur les 26 lignes de francais CE1. **Je ne chiffre
donc aucune ligne de francais CE1 pour s47**, sur le meme motif que `DEC-0103` section 3 l'avait
ecrit d'avance : "le chiffrage d'un premier lot de francais CE1 reste hors de ce document... aucune
story de developpement n'est engageable avant [l'instruction ligne par ligne]". Premiere candidate
pour un sprint ulterieur, apres un avis de faisabilite dedie - meme sequence que mathematiques CE1
entre s36 et s37.

## 2. Vivier instruit reverifie pour s47

### 2.1 Mathematiques CE1 - `MAT-CE1-07` retenue, regle d'une ligne par sprint reconduite une
cinquieme fois

Dix lignes `non couvert` restent au referentiel de mathematiques CE1 apres la fermeture de
`MAT-CE1-05` a s46 (`grep -c` sur `squad/programme/MATRICE-couverture.md`, section "Mathematiques -
CE1", verifie ce jour). Les dix sont deja instruites par `nadia-prof`
(`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1/2/3.md`) ; je ne les refais pas, je recite leur
verdict :

| Ligne | Verdict | Plafond atteignable |
|---|---|---|
| `MAT-CE1-01` (denombrer, lire, ecrire, decomposer) | Accepte sur *lire/ecrire/decomposer*, refuse sur *denombrer* | `en cours` seulement |
| `MAT-CE1-02` (comparer, ranger, encadrer, ligne graduee) | Non reverifie dans ce document - hors choix de ce tour | - |
| `MAT-CE1-06` (sens de la multiplication) | Accepte sur l'addition reiteree, couverture partielle si la disposition en lignes/colonnes n'est pas dessinee | `en cours` seulement |
| `MAT-CE1-07` (tables de multiplication 2, 5, 10, 3, 4) | **Accepte sous conditions `TAB-CE1-1` a `TAB-CE1-6`, entierement servable par texte seul** | **`couvert-valide` atteignable** |
| `MAT-CE1-08` (problemes a une ou deux etapes) | Accepte sous conditions `PRO-CE1-1` a `PRO-CE1-7`, servable par texte seul, sous exclusion de la forme deja fermee par `MAT-CE1-13` | `couvert-valide` atteignable |
| `MAT-CE1-10` (masses et contenances) | Couverture partielle - *mesurer* non servable par texte seul | `en cours` seulement |
| `MAT-CE1-11` (heure, calendrier) | Couverture partielle - *lire l'heure* refuse, *mois* deja ferme par `QLM-CP-13` | `en cours` seulement |
| `MAT-CE1-14` (quadrillage, deplacement) | Palier 2 requis | non instruit pour s47 |
| `MAT-CE1-15` (tracer des figures planes) | Palier 2 requis | non instruit pour s47 |
| `MAT-CE1-17` (axe de symetrie) | Refuse par texte seul, meme motif que les solides a s39 | non instruit pour s47 |

**Je retiens `MAT-CE1-07`**, seule ligne du vivier (avec `MAT-CE1-08`) atteignable
`couvert-valide` sur un paquet entierement textuel, sans aucune collision active ce sprint - les
deux portes qu'elle ferme d'avance (`TAB-CE1-5`, vers `MAT-CE1-03` deja ferme ; sa frontiere avec
`MAT-CE1-06`, non engagee ce sprint) sont deja ecrites dans l'arbitrage source, rien n'est a
instruire de plus. **Regle operationnelle reconduite une cinquieme fois** (s43, s44, s45, s46,
s47) : une seule ligne de mathematiques CE1 par sprint, motif de marge et non de fond - je le
signale explicitement plutot que de le laisser silencieux, comme a chaque fois depuis s44.
`MAT-CE1-08` reste la candidate suivante, disponible au meme tarif (1,0) et aux memes conditions.

### 2.2 Hors mathematiques CE1 - le meme vivier de quatre lignes, inchange depuis s45

**Ces quatre lignes sont citees a l'identique dans `DEC-0100` (s45), `DEC-0101` (s45) et
`DEC-0102` (s46, section 3) sans qu'aucune des trois n'ait ete retenue** : s46 a ferme
`EMC-CM1-01`/`EMC-CM1-09` (instance A) et `SCI-CM1-03`/`SCI-CM1-16` (instance B), pas ces
quatre-la, qui restent toutes `non couvert` - verifie ce jour contre `MATRICE-couverture.md`.

| Ligne | Matiere / niveau | Verdict (source) |
|---|---|---|
| `QLM-CP-15` | questionner le monde CP | jouable, `BIA-3` - "la chronologie n'est pas un progres" (`ARBITRAGE-PREALABLE-s29`, ligne 374) |
| `EMC-CP-14` | EMC CP | jouable sous `EMC-7` ; piege d'exactitude connu (18 vs 112 pour l'incendie) (`competences-cp.md`, Jugement de faisabilite, ligne 14) |
| `EMC-CM1-10` | EMC CM1 | jouable, connaissance seule, sous `EMC-1`, `EMC-2`, `EMC-5`, `EMC-8` ; aucun contenu CP existant sur l'objet miroir (`competences-cm1.md`, Jugement de faisabilite, ligne 10) |
| `LVE-CP-06` | langues vivantes (anglais) CP | jouable en francais seul, `BIA-1`/`BIA-2` opposables, mention de couverture partielle exigee d'avance (`MATRICE-couverture.md`, ligne du domaine "Decouverte culturelle") |

Aucun refus, aucune reserve nouvelle, aucun changement d'arbitrage trouve sur ces quatre lignes
depuis `DEC-0102`. **Je retiens les quatre** : rien n'impose d'en laisser une au vivier plutot
qu'une autre, et leur cout cumule (section 4) tient sous la borne a deux instances sans la saturer.

## 3. Troisieme instance - conditions de reouverture reverifiees, toujours non reunies

`squad/conformite/ARBITRAGE-PREALABLE-s46-trois-instances-refusees-...md` pose trois conditions
**cumulatives** de reouverture (section 5). Je les reverifie contre l'etat d'aujourd'hui plutot que
de supposer qu'elles restent fermees :

1. **(i) Un vivier instruit superieur a la borne a deux instances (8,0), laissant dehors au moins
   4,0.** `nadia-prof` nommait elle-meme deux leviers possibles, aucun des deux n'etant le sien :
   l'integration du referentiel francais CE1 (faite section 1 de ce document) et une decision de
   `camille-po` sur le plafond d'une ligne de mathematiques CE1 par sprint. **L'integration ne
   suffit pas a elle seule** : elle augmente le denominateur, elle n'instruit aucune ligne - aucun
   avis de faisabilite, aucun arbitrage ligne par ligne n'existe sur le francais CE1 (section 1.2).
   Le vivier instruit reste a 7,0 (section 4), **non superieur** a la borne de 8,0. **Condition (i)
   non remplie.**
2. **(ii) Le dernier sprint parallele clos sans aucune occurrence des formes (a), (b) ou (c).**
   `nadia-prof` ecrit elle-meme, dans son propre document, ne pas l'avoir verifie pour s41 ni pour
   s45, et je ne le verifie pas a sa place - c'est une garde qu'elle seule tient (section 6 du meme
   document). **Non reunie, faute de verification par sa seule garde.**
3. **(iii) L'affectation des trois terrains tient `PAR-13`** (au plus une instance sur un terrain
   sans contenu reel). Le francais CE1, "quand il existera" dans la matrice, est nomme par
   `nadia-prof` elle-meme comme ce terrain-la. Il n'est pas engage ce sprint (section 1.2) : la
   question ne se pose donc pas encore. **Sans objet tant que (i) n'est pas remplie.**

**Conclusion : les trois conditions restent non reunies, (i) la premiere. Je ne derive aucune
borne a trois instances pour s47**, et je ne rouvre pas la question aupres de `nadia-prof` - rien
dans l'etat d'aujourd'hui ne le justifie, a la difference de l'integration elle-meme qui, seule,
ne suffisait pas.

## 4. Composition retenue - deux instances, verification des clauses une par une

**Cible : 7,0 / 8,0, marge de sprint 1,0 non allouee**, meme plafond atteignable que celui que
`nadia-prof` avait elle-meme anticipe pour l'apres-s46 ("au plus 5,0 a 7,0", section 2.2 de son
arbitrage).

| Ligne | Matiere / niveau | Tarif | Instance |
|---|---|---|---|
| `MAT-CE1-07` | mathematiques CE1 | 1,0 | A |
| `QLM-CP-15` | questionner le monde CP | 1,5 | A |
| `LVE-CP-06` | langues vivantes (anglais) CP | 1,5 | A |
| `EMC-CP-14` | EMC CP | 1,5 | B |
| `EMC-CM1-10` | EMC CM1 | 1,5 | B |

**Instance A : trois lots, 4,0 / 5,0, marge individuelle 1,0. Instance B : deux lots, 3,0 / 5,0,
marge individuelle 2,0. Total du sprint : 7,0 / 8,0, marge de sprint 1,0 non allouee.**

### 4.1 `PAR-1` - aucun lot partage entre deux instances

Chacune des cinq lignes est affectee a exactement une instance. Verifie par construction.

### 4.2 `PAR-2` - aucune matiere partagee entre les deux instances, quel que soit le niveau

**Rappel du precedent `DEC-0098` section 4.1, repris par `DEC-0102` section 5** : EMC CP et EMC CM1
comptent pour une seule matiere au sens de cette clause. Instance A porte trois matieres
(mathematiques, questionner le monde, langues vivantes), toutes distinctes de la matiere unique
que porte l'instance B (EMC, sous ses deux niveaux). **Aucune matiere partagee. Tenue.**

### 4.3 `PAR-7` - terrain vierge, aucune affectation dictee

Aucune des quatre matieres retenues n'est vierge pour le produit : mathematiques CE1 porte deja
sept lignes `couvert-valide` (`MAT-CE1-03`, `-04`, `-05`, `-12`, `-13`, `-16`, `-18`) ; questionner
le monde CP en porte onze ; langues vivantes (anglais) en porte deux, a l'autre niveau (CM1,
`LVE-CM1-04`, `LVE-CM1-07`) - la matiere a deja une accumulation, seul le niveau CP en est
depourvu, ce qui est exactement le cas deja rencontre et juge non contraignant pour EMC CM1 face a
EMC CP (`DEC-0102` section 5) ; EMC (CP et CM1 confondus) en porte dix-sept. **Aucun terrain vierge
a affecter. `PAR-7` ne dicte donc aucune composition particuliere, je le dis explicitement plutot
que de laisser la clause sans reponse.**

### 4.4 `PAR-8` - plafond individuel 5,0, au plus trois lots par instance

Instance A : trois lots, 4,0/5,0. Instance B : deux lots, 3,0/5,0. Les deux sous le plafond
individuel et sous la limite de lots.

### 4.5 `CYC-1` - continuite cycle 2 / cycle 3, verifiee et non due

**Due uniquement pour une ligne de cycle 3 (CM1) qui partage son objet avec une ligne de cycle 2
(CP) deja servie en contenu.** Seule `EMC-CM1-10` est une ligne de CM1 dans cette composition.
Son objet miroir cote CP, "participer a une decision collective par un vote", est
**`non couvrable en l'etat`** (aucun paquet ne la sert) - `nadia-prof` l'ecrit elle-meme dans le
referentiel EMC CM1 : *"ici, c'est le programme du cycle 3 lui-meme qui porte la connaissance du
vote et de la representation comme un attendu propre"*, point explicitement distingue d'une
contradiction. **Aucune lecture croisee n'est due** : il n'existe aucun paquet CP a relire.
`QLM-CP-15`, `EMC-CP-14` et `LVE-CP-06` sont des lignes de CP, `CYC-1` ne s'applique jamais dans ce
sens. **Verifie, pas suppose : `CYC-1` non due sur les cinq lignes de s47.**

### 4.6 Frontieres internes a une instance

Aucune clause de frontiere croisee n'est due entre A et B : les quatre matieres retenues ne
partagent aucun objet du monde reel (critere de `nadia-prof`, repris de `DEC-0098` section 4.3) -
tables de multiplication, modes de vie autrefois/aujourd'hui, aspects culturels des pays etudies
en anglais, gestes d'alerte et sens du vote ne designent pas le meme fait. A l'interieur de
l'instance A, `MAT-CE1-07` ferme deja sa propre porte contre `MAT-CE1-03` (doubles, `TAB-CE1-5`) et
contre `MAT-CE1-06` (mise en situation, non engagee ce sprint) dans son propre arbitrage source ;
aucune frontiere neuve n'est a ecrire.

## 5. `SOP-CE1-R1` - reste route a `nadia-prof`, pas engage dans s47

`SOP-CE1-R1` (`docs/backlog/backlog.md`, section "Backlog - sprints suivants") est un arbitrage que
`nadia-prof` s'adresse a elle-meme sur sa propre condition `SOP-CE1-5` (les deux items sans
emprunt de `MAT-CE1-05` se repondent sans poser l'operation, le seul distracteur disponible - la
somme - depassant toujours le premier terme). **Ce n'est pas une ligne de contenu a coder** :
aucun developpeur ne peut l'honorer avant que `nadia-prof` ait tranche sur sa propre clause, motif
qu'elle a ecrit elle-meme (*"la limite vient de ma condition, pas du paquet"*). **Je ne l'engage
pas dans le perimetre chiffre de s47** (elle ne porte sur aucune des cinq lignes retenues, et le
paquet `bandit-manchot-soustractions-posees-ce1` n'est pas repris avant son arbitrage). Je la
signale comme **tache a router a `nadia-prof` par `sofia-sm` a l'ouverture du planning**, en
parallele du chiffrage des deux instances, pas comme un sixieme lot de developpement. Aucun sprint
ne lui est fixe par ce document : c'est a `sofia-sm`, a l'ouverture, de decider si elle l'instruit
dans la meme fenetre que les cinq lots chiffres ou separement.

## 6. Ce que ce document fait et ne fait pas

**Fait** : integre le referentiel francais CE1 a la matrice (section 1) ; route a `nadia-prof`
deux points qu'elle a elle-meme signales en l'ecrivant (B1, B2, section 1.1) ; reverifie le vivier
instruit et retient cinq lignes sur deux instances (sections 2 et 4) ; reverifie les trois
conditions de reouverture d'une troisieme instance et constate qu'elles restent non reunies
(section 3) ; route `SOP-CE1-R1` comme tache distincte du chiffrage (section 5).

**Ne fait pas** :
- Il ne fixe aucune competence francais CE1, ni les cinq codes de (B1), ni la borne de (B2) :
  les deux restent a `nadia-prof`.
- Il n'engage aucune ligne de francais CE1 dans le perimetre de s47 : aucune n'est instruite
  (section 1.2).
- Il ne derive aucune borne a trois instances (section 3).
- Il ne tranche pas le fond de `SOP-CE1-R1` : il la route (section 5).
- Il ne prononce aucun Done, technique ou pedagogique.
- Il n'ouvre pas le dossier de sprint : routage a `sofia-sm`, comme pour s46 (`DEC-0102` section
  8).

## 7. Ce que ce document demande, et a qui

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| Allocation de cinq codes `[FRA-CE1-XX]` pour les attendus de langage oral, lecture a voix haute, dictee, copie manuscrite et production d'ecrit (B1) - ou confirmation du choix de les laisser hors ligne | `nadia-prof` | Route, section 1.1 - non bloquant, non engage |
| Borne de longueur du texte support de `FRA-CE1-04` a `FRA-CE1-07` (B2) | `nadia-prof` | Signale, section 1.1 - due avant tout chiffrage de ces quatre lignes, aucune d'elles engagee ce sprint |
| Arbitrage de `SOP-CE1-R1` | `nadia-prof` | Route, section 5 - a instruire par `sofia-sm`, separement du chiffrage |
| Ouverture du dossier de sprint, avis de faisabilite, affectation nominative | `sofia-sm` puis `theo-archi` | A faire a l'ouverture de s47 |
| Stories et criteres d'acceptation des cinq lignes chiffrees | `camille-po` | Faites dans ce meme mouvement, `docs/backlog/backlog.md`, section "Sprint 47" |
