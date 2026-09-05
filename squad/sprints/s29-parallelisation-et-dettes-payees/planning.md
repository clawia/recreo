# [Sprint 29] Planning - 2026-09-05

- **Horodatage de debut** : 2026-09-05 21:35
- **Anime par** : sofia-sm
- **Participants** : sofia-sm (SM et dev), marc-dev, lea-dev, theo-archi (`ADR-0082` deja rendu ;
  avis de faisabilite synthetise ci-dessous, section 6), nadia-prof (arbitrage prealable deja
  rendu en deux temps, merge ci-dessous, deux instances a partir de ce sprint), camille-po
  (decisions produit deja rendues, merges ci-dessous)

## 0. Ce qui est deja entre, avant l'ouverture

Comme a s28, rien ne dort sur une branche separee : cinq commits de pre-sprint sont deja sur
`round-two`, dans l'ordre ou ils ont ete ecrits :

| Commit | Agent | Contenu |
|---|---|---|
| `758545a` | `camille-po` | `DEC-0077` - couverture maximale annoncee (5 lots, cible ~10 lignes), question ouverte sur la marge de rejeu, `DEC-0075` acceptee (option B, corpus du domaine public), deux bilans de matiere corriges |
| `11265c1` | `nadia-prof` | arbitrage prealable, sections 1 a 12 : le perimetre annonce est decline (il vaut 6,0 a 7,0, pas 5,0), quatre lots tiennent dans sa borne de 5, plafond de fermeture a huit lignes |
| `4f594d6` | `theo-archi` | `ADR-0082` - le corpus de reproductions d'oeuvres du domaine public, garde-fou de 18 tests |
| `2956c7b` | `nadia-prof` | amendement, section 13 : la borne sous parallelisation (`PAR-0` a `PAR-8`), `CYC-1`, borne de s29 portee a 8,0 |
| `961565d` | `camille-po` | `DEC-0077` amendee (section 8) : variante A retenue, neuf stories `S29-1` a `S29-9`, deux libelles de sciences corriges |

**Mesure d'entree technique**, `round-two` a `961565d` (etat courant, aucune ecriture faite par
moi) : `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet (racine, **pas de
`-pl`**). **BUILD SUCCESS.** Comptage independant des rapports XML de tous les modules du
reacteur (167 fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports`, somme des
attributs `tests`/`skipped`/`failures`/`errors` de la balise `<testsuite>`, methode de l'amendement
s11) : **1240 tests, 0 echec, 0 erreur, 0 ignore.**

Ce chiffre se recoupe avec ce qu'annoncait l'ouverture de ce sprint : la sortie de s28 etait 1222
tests a `790584e`, et les commits de pre-sprint ont ajoute le garde-fou d'`ADR-0082` (18 tests,
`PreuveDeLOeuvreEtCelleDeSaPhotographieTest`). **1222 + 18 = 1240** : le chiffre mesure confirme
exactement le chiffre annonce, ce n'est pas une reconduction non verifiee.

## 1. Objectif du sprint

**Fermer sept a huit des lignes ouvertes (questionner le monde CP, histoire-geographie CM1
composition A, enseignement moral et civique aux deux cycles), payer les quatre dettes de s28
(`POS-R1`, les six reserves textuelles, la dette des paquets a voix, l'arbitrage prealable du
palier 2), et faire tourner pour la premiere fois deux instances de `nadia-prof` en parallele sur
des lots disjoints, sans qu'aucune propriete transverse ne tombe en silence.**

Perimetre arbitre par l'utilisatrice le 2026-09-05 (`DEC-0077`, section 8, amendee le jour meme
sur l'arbitrage prealable de `nadia-prof`) : **variante A (dettes) retenue** sur variante B
(couverture), quatre lots de contenu, borne de s29 portee a **8,0** par la parallelisation,
**7,5 planifies**, **1,0 de marge de rejeu nommee et non allouee** (0,5 par instance).

**Le fait central de ce sprint, et il n'est pas masque** : la variante retenue **ne ferme pas une
ligne de plus qu'un sprint en serie a 5,0** - la composition a une seule instance plafonnait deja a
neuf lignes (arbitrage de `nadia-prof`, section 8.2). Le doublement de la borne n'achete aucune
couverture ; il achete `POS-R1` payee, les six reserves, la dette des paquets a voix payee apres
quatre sprints, et 1,0 de marge de rejeu qui n'a jamais existe dans ce depot. **L'utilisatrice a vu
la variante B, qui fermait deux lignes de plus sans payer aucune dette, et a choisi A quand meme.**

## 2. Taux d'entree du sprint

**43 / 138 = 31,2 %.** Recompte ligne a ligne des dix-huit tableaux de
`squad/programme/MATRICE-couverture.md` par `camille-po` le 2026-09-05 (`DEC-0077`, section 1) :
43 `couvert-valide`, 10 `en cours`, 85 `non couvert`, 71 `non couvrable en l'etat`, 209 lignes,
138 au denominateur atteignable. Ce chiffre converge exactement avec celui deja ecrit dans la
matrice a la cloture de s28 : **aucun ecart trouve.** Deux bilans de matiere perimes (questionner
le monde CP, anglais CM1) ont ete corriges dans le meme geste, sans effet sur le total.

## 3. Le perimetre et son plafond honnete, sous parallelisation

**La borne de `nadia-prof` passe de 5,0 a 8,0 pour ce seul sprint** (`PAR-8`), par un arbitrage de
l'utilisatrice de faire travailler deux instances d'elle en parallele sur des lots disjoints.
**Ce n'est pas un doublement de capacite d'audit** : `nadia-prof` derive elle-meme le chiffre -
10,0 de capacite brute (2 x 5,0), moins 0,5 parce que la passe transverse cesse d'etre un
sous-produit gratuit du mode serie et devient une tache payee, moins 0,5 parce que les dettes de
portee "depot entier" deviennent payables et doivent l'etre, moins 1,0 parce que la seconde
instance en est a son premier essai de parallelisation et qu'un essai a deja produit une collision
d'identifiant (`ART-1`, le 2026-09-03). **Plafond individuel : 5,0 par instance, au plus trois lots
chacune** (`PAR-8`) - sans ce plafond, les 8,0 pourraient se concentrer sur une seule instance, ce
que `PAR-0` refuse explicitement : la borne protege un jugement qui ne se divise pas, pas
seulement un debit de lecture.

**Composition retenue (variante A - dettes), 7,5 planifies sur 8,0** :

| | **Instance A** - porte l'accumulation | **Instance B** |
|---|---|---|
| **Lots** | questionner le monde CP, 1,0 (`QLM-CP-02`, `QLM-CP-07`) ; histoire-geographie CM1 composition A, 1,5 (`HG-CM1-06`, `-07`, `-14`) | EMC CP restreint aux lignes 1 a 7, 1,0 (lignes 6, 2) ; EMC CM1, 1,5 (lignes 11, 7) |
| **Transverse** | passe transverse 0,5 ; six reserves textuelles 0,25 ; dette des paquets a voix 0,25 | `POS-R1`, rejeu borne 0,5 |
| **Marge de rejeu** | 0,5, non allouee | 0,5, non allouee |
| **Total** | **4,0** / 5,0 | **3,5** / 5,0 |
| **Hors borne** | arbitrage prealable du palier 2 (`S29-9`), cout nul | - |

**Plafond arithmetique : +9 lignes, soit 52 / 138 = 37,7 %. Attente raisonnable de `nadia-prof` :
7 a 8 lignes, soit 50 a 51 / 138 (36,2 % a 37,0 %).**

**Ou cela cassera d'abord, dit avant et pas apres (`nadia-prof`, section 13.6)** :
1. **La couture** - une clause `PAR-*` non suivie a la lettre, silencieusement : rien ne rougit, la
   ligne ferme, la propriete n'a ete verifiee par personne. C'est le premier mode de defaillance et
   il n'existait pas au sprint dernier.
2. **Le lot d'EMC**, quel que soit le niveau - neuf conditions inedites qui n'ont jamais rencontre
   de contenu reel.
3. **Le premier verdict de l'instance B sur une matiere dont `nadia-prof` n'est pas la source** -
   c'est le geste ou l'accumulation compte le plus, et B n'en a pas.

## 4. Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S29-1` | Deux nouveaux paquets de questionner le monde (CP) : etre vivant, objet technique | `QLM-CP-02`, `QLM-CP-07` | `DEC-0072`, `DEC-0077` | `lea-dev` |
| `S29-2` | Second paquet d'histoire-geographie (CM1), composition A : rois, Revolution, comprendre un document | `HG-CM1-06`, `-07`, `-14` | `DEC-0072`, `DEC-0077` | `marc-dev` |
| `S29-3` | Passe transverse d'Instance A sur les proprietes de portee "depot servi entier" | transverse, aucune ligne ne bouge | `DEC-0077` (section 8.2, `PAR-4`) | `nadia-prof`, Instance A, seule |
| `S29-4` | Deux nouveaux paquets d'EMC (CP), restreints aux lignes 1 a 7 : regles de vie, permis et interdit | EMC CP, lignes 6 et 2 | `DEC-0074`, `DEC-0077` | `lea-dev` |
| `S29-5` | Deux nouveaux paquets d'EMC (CM1) : fait ou opinion, droit ou devoir | EMC CM1, lignes 11 et 7 | `DEC-0074`, `DEC-0077` | `lea-dev` |
| `S29-6` | `POS-R1` : le distracteur de division posee (CM1) oblige a diviser | leve une reserve sur une ligne deja `couvert-valide` | `DEC-0077` (section 8.4) | `marc-dev` |
| `S29-7` | Six reserves textuelles : un passage de reverification mot pour mot | transverse, aucune ligne ne bouge | `DEC-0077` (section 8.4) | `lea-dev` (`VIV-R2`, `ANG-R1`, `COR-R2`, `EAU-R1`) et `marc-dev` (`HG-R4`, `HG-R5`) |
| `S29-8` | La dette des paquets a voix : six jeux, cinq indices corriges contre `VOI-3` | transverse, aucune ligne ne bouge | `DEC-0077` (section 8.4) | `lea-dev` |
| `S29-9` | L'arbitrage prealable du palier 2 est ecrit, sans construire aucun paquet | transverse, debloque six lignes pour s30 | `ADR-0080`, `DEC-0074`, `DEC-0077` | `nadia-prof`, l'une ou l'autre instance, a sa discretion |

**Motif de l'affectation nominative, developpeurs.**

- **Continuite avec s27/s28** : `lea-dev` a ecrit les trois premiers paquets de questionner le
  monde CP (`c9b9c2b`, `2711f35`, `ad522a2`) et les cinq paquets a voix de s26 (`ab574e2`) ; elle
  reprend `S29-1` et `S29-8`. `marc-dev` a ecrit le paquet d'histoire-geographie CM1 (`c329f97`,
  `ec73c51`) et le paquet de division posee (`21f3d1d`) ; il reprend `S29-2` et `S29-6`.
- **`S29-4` et `S29-5` (EMC, matiere neuve pour les deux developpeurs) vont a `lea-dev`**, pour la
  raison ecrite en section 5 : ce sont les trois seules stories qui ecrivent des entrees neuves
  dans `adapters/adapter-content/src/main/resources/catalogue.yaml`
  (`S29-1`, `S29-4`, `S29-5`), et les regrouper chez une seule developpeuse evite d'avoir a
  synchroniser deux personnes sur un meme fichier partage en cours de sprint. `S29-2` complete un
  paquet deja catalogue (`cm1-histoire-geographie.yaml` a deja son entree depuis s27) et ne touche
  pas `catalogue.yaml` : elle peut donc courir en parallele, chez `marc-dev`, sans aucun risque de
  perte silencieuse.
- **`S29-7`** se split par fichier d'origine : `HG-R4`/`HG-R5` sont dans
  `cm1-histoire-geographie.yaml`, deja chez `marc-dev` par `S29-2` ; les quatre autres sont dans
  des fichiers de `lea-dev`. Aucun des deux ne touche `catalogue.yaml`.
- **`S29-3` et `S29-9`** sont des taches d'audit, sans code de production : elles reviennent a
  `nadia-prof` seule, comme `S28-5` etait revenue a moi au sprint precedent.

## 5. Ordonnancement : deux instances, une contrainte de serie, un mode de defaillance a surveiller

**Ceci est une specification d'ordonnancement, pas une commodite** (consigne de ce sprint).

### 5.1 L'affectation des deux instances d'audit

`PAR-7` : une instance porte l'accumulation, l'autre non, et cela s'affecte plutot que de
s'equilibrer. **Instance A** prend questionner le monde CP et l'histoire-geographie CM1, ou elle
a deja joue les paquets precedents (`PAR-3` : elle relit les items deja servis d'une ligne `en
cours` avant de juger `FER-1`). **Instance B** prend l'EMC aux deux niveaux, **la seule matiere du
perimetre ou personne n'a d'accumulation** : *"on ne demande pas a une instance de rattraper une
image qu'elle n'a pas ; on lui donne le terrain ou l'image n'existe pour personne."* Je ne
redistribue pas les lots pour equilibrer les charges (4,0 contre 3,5) : cet ecart est le prix de
la regle `PAR-7`, pas une erreur d'equilibrage a corriger.

`PAR-2` : une matiere et un niveau ne sont jamais partages entre deux instances, ni dans ce sprint
ni entre deux lots du meme sprint. EMC CP et EMC CM1 comptent pour une seule matiere au sens de
cette clause - toutes deux vont a Instance B.

### 5.2 L'ordre de sortie des lots

**Deux contraintes se combinent** : `nadia-prof` demande que le lot dont les conditions n'ont
jamais rencontre de contenu reel - l'EMC - sorte en premier (section 8.4 de son arbitrage) ;
`catalogue.yaml` n'accepte qu'une seule ecriture a la fois. Comme `S29-4`, `S29-5` et `S29-1` sont
les trois seules stories qui y ecrivent, et qu'elles vont toutes a `lea-dev`, l'ordre de sortie de
ses trois lots est **strictement sequentiel** :

1. **`S29-4` (EMC CP)** - premier lot livre, audite par Instance B des sa livraison.
2. **`S29-5` (EMC CM1)** - livre des que `S29-4` est commite, audite par Instance B des sa
   livraison. **Les deux paquets d'EMC sont ainsi livres et audites avant tout autre lot de
   contenu** : c'est la lecture directe de la demande de `nadia-prof`.
3. **`S29-1` (questionner le monde CP)** - livre des que `S29-5` est commite, audite par Instance
   A des sa livraison.

**En parallele de ces trois lots, sans jamais toucher `catalogue.yaml`** : `marc-dev` livre `S29-2`
(histoire-geographie CM1, sur un paquet deja catalogue) des l'ouverture, audite par Instance A des
sa livraison ; puis `S29-6` (`POS-R1`, sur `cm1-division-posee.yaml`), rejeu par Instance B ; puis
sa part de `S29-7` (`HG-R4`, `HG-R5`, apres son propre commit de `S29-2` sur le meme fichier).

**Les corrections transverses ferment le sprint** : `lea-dev` livre sa part de `S29-7` et `S29-8`
des que ses trois lots de catalogue sont commites (aucune des deux ne touche `catalogue.yaml`),
reverifiees par Instance A. `S29-3` (passe transverse) porte sur le **depot servi a l'ouverture de
s29** et ne depend d'aucune autre story : Instance A peut la conduire a tout moment du sprint,
y compris des le premier jour, en parallele du reste. `S29-9` (arbitrage du palier 2) est de meme
sans dependance et sans cout contre la borne : conduite par l'une ou l'autre instance des qu'elle
a un moment disponible.

### 5.3 La contrainte de serie sur le contenu, dite comme un ordonnancement et non une preference

**Les lots de contenu ecrivent tous dans `catalogue.yaml`** ; deux developpeurs qui y ecrivent en
meme temps ne produisent pas un conflit visible mais une perte silencieuse (une entree ecrasee ou
un identifiant duplique sans erreur de merge). **En affectant les trois stories qui y ecrivent
(`S29-1`, `S29-4`, `S29-5`) a une seule developpeuse et en les sequencant entre elles, ce sprint
n'a jamais deux personnes ecrivant dans ce fichier au meme instant.** `marc-dev` ne touche
`catalogue.yaml` a aucun moment du sprint : ses trois taches (`S29-2`, `S29-6`, sa part de `S29-7`)
portent sur des paquets deja catalogues.

### 5.4 Le mode de defaillance neuf, garde present tout le sprint

Repris mot pour mot, parce que le reformuler serait deja le defaut que `nadia-prof` decrit en
section 9 de son arbitrage (un texte qui redevient faux des qu'on le lit dans un autre registre) :

> *"Ca casse d'abord sur la couture, et ce mode de defaillance n'existait pas au sprint dernier. Il
> est silencieux : rien ne rougit, la ligne ferme, la propriete n'a ete verifiee par personne."*

> *"Deux paires d'yeux sur des lots disjoints, ce sont deux paires d'yeux, pas un double controle.
> Aucune des deux instances ne relit l'autre."*

**Consequence directe pour ce planning** : aucune des neuf stories ci-dessus ne doit etre lue
comme un double controle du travail d'une autre instance. `S29-3` (passe transverse) et `CYC-1`
(obligation de lecture inscrite dans les criteres d'acceptation des lots de cycle 3) sont les deux
seuls dispositifs de ce sprint qui verifient explicitement une propriete traversant plusieurs
lots ; tout le reste depend de la discipline d'affectation (`PAR-1` a `PAR-3`, `PAR-7`) plutot que
d'une verification a posteriori.

## 6. Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| `S29-1`, `S29-2`, `S29-4`, `S29-5` | Meme mecanique et meme chargeur generique que tous les lots de contenu depuis s22 (bandit manchot, chargeur pilote par schema) : pas d'obstacle technique connu | Non requis |
| `S29-3`, `S29-9` | Taches d'audit sans code de production : sans objet pour l'architecture | Non requis |
| `S29-6` | Correction de donnees dans un paquet YAML existant, sur un schema inchange | Non requis |
| `S29-7`, `S29-8` | Corrections de champs texte (`bonneReponse`, `indice`, `enonce`) dans des paquets existants, aucun champ neuf | Non requis |

**Un point non code, laisse ouvert par `nadia-prof` elle-meme et que je route en section 7** : la
propriete `POS-2` sur la division posee ("aucun reste de distracteur superieur ou egal a son
diviseur") est **calculable**, et `nadia-prof` le dit explicitement en section 10 de son
arbitrage - mais aucun garde-fou ne l'encode aujourd'hui. `S29-6` ne l'exige pas dans ses criteres
d'acceptation (la propriete y est verifiee par le rejeu de `nadia-prof`, pas par un test) : cette
story ne cree donc aucun `ArchRule` ni test de contenu neuf pour cette propriete, ce qui est
conforme a ce que `theo-archi` n'a pas encore instruit.

## 7. Ce que je route, pas ce que je tranche

- **Le registre des 147 identifiants de conditions (`PAR-5`)** : `nadia-prof` a mesure 147
  identifiants distincts sur 30 prefixes, aucun registre nulle part, vingt-quatre portant le
  numero 1. `camille-po` l'a deja route a moi sans le trancher (`DEC-0077`, section 8.7) et
  **aucune story de s29 ne le construit** - c'est une decision deja actee par `camille-po`, pas
  une omission de ma part. **Il n'entre pas dans s29.** Je le route a `theo-archi` pour
  instruction de la forme (un registre, un prefixe unique par famille, ou autre dispositif) ; la
  priorite reste a l'utilisatrice. Le risque qu'il couvre est reel et deja materialise une fois
  (la collision `ART-1`), mais ce sprint n'a ni budget ni story pour le construire.
- **La derive silencieuse des bilans de matiere** : deux totaux de `MATRICE-couverture.md` avaient
  derive apres s28 sans qu'aucun mecanisme ne les relie aux lignes qu'ils resument. `camille-po` a
  corrige les deux occurrences trouvees et route la question d'un garde-fou (`DEC-0077`, section 7
  et 8.8) - je ne la tranche pas non plus, **aucune story de s29 ne construit ce garde-fou**. Route
  a `theo-archi` : verifier mecaniquement qu'un bilan de matiere correspond au compte reel des
  lignes de son tableau, ou s'en remettre a la revue humaine de `sofia-sm` a chaque fusion de
  matrice - le choix du moyen n'est pas pedagogique, il est technique.
- **La forme d'un garde-fou pour `POS-2` sur la division** (section 6 ci-dessus) : `nadia-prof`
  dit elle-meme que la propriete est calculable et qu'elle vient de manquer onze items sur onze en
  la verifiant a la main. Ce point n'avait pas ete repris dans les quatre points routes de
  `DEC-0077` (section 8.7) ; je le signale a mon tour a `theo-archi`, avec la meme reserve que les
  deux precedents : je dis la propriete, la forme et la priorite ne m'appartiennent pas. `S29-6`
  n'attend pas ce garde-fou pour lever `POS-R1` - le rejeu de `nadia-prof` suffit a ce sprint.
- **`DEC-0075` (corpus de reproductions d'oeuvres du domaine public) est deja tranchee** (option B,
  `ADR-0082` deja ecrit avec son garde-fou) **et n'entre dans aucune story de s29** : elle debloque
  jusqu'a neuf lignes pour les sprints suivants sans en fermer aucune ici (`DEC-0077`, section 4).
  Rien a router : les quatre questions de sourcing et de forme qu'`ADR-0082` laisse ouvertes sont
  deja adressees a `theo-archi` par `camille-po`, avant ce planning.

## 8. Decisions prises

Aucune decision produit ni architecture n'est prise par moi dans ce planning : je reprends du
travail deja tranche par ses auteurs (`camille-po`, `nadia-prof`, `theo-archi`), j'affecte
nominativement les neuf stories, je precise l'ordonnancement entre les deux instances et les deux
developpeurs, et je route ce qui reste ouvert. Les seules "decisions" de ce document sont
d'organisation (affectations, ordre de sortie des lots) et n'entrent pas dans la table des
`ADR`/`DEC`.

## 9. Prochaines etapes

- `lea-dev` : `S29-4`, puis `S29-5`, puis `S29-1` (strictement en serie, seule ecriture dans
  `catalogue.yaml` de tout le sprint), puis sa part de `S29-7` et `S29-8`. Demarre dans un worktree
  dedie (`git worktree add ../recreo-s29-lea -b s29-lea round-two`), verifie par
  `outils/verifier-lignee.sh` avant le premier commit.
- `marc-dev` : `S29-2` des l'ouverture (en parallele de `lea-dev`, aucun conflit de fichier), puis
  `S29-6`, puis sa part de `S29-7`. Meme protocole de worktree, meme verification de lignee.
- `nadia-prof`, **Instance A** : audite `S29-2` puis `S29-1` des leur livraison ; conduit `S29-3` a
  tout moment du sprint (independante des autres livraisons) ; reverifie `S29-7` (sa part et celle
  de `marc-dev`) et `S29-8` en fin de sprint.
- `nadia-prof`, **Instance B** : audite `S29-4` puis `S29-5` des leur livraison, en priorite sur le
  reste du sprint (conditions inedites) ; rejoue `S29-6` des que la correction est livree.
- `nadia-prof`, l'une ou l'autre instance : conduit `S29-9` quand l'une des deux a un moment
  disponible.
- `theo-archi` : instruit, s'il le juge necessaire, la forme du registre `PAR-5`, celle d'un
  garde-fou de bilan de matiere, et celle d'un garde-fou de `POS-2` sur la division (section 7) ;
  aucune des trois n'est requise pour qu'une story de s29 ferme.
- `camille-po` : suit l'arbitrage de la question de la section 3 de `DEC-0077` si un lot de s29
  est refuse en cours de sprint (trois issues chiffrees, aucune tranchee d'avance).
