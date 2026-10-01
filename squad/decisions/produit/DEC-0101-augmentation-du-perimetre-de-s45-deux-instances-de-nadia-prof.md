# DEC-0101 - Augmentation du perimetre de s45, deux instances paralleles de `nadia-prof`

- **Statut** : Acceptee, engagee le 2026-10-01 (section 7) ; amendee le 2026-10-01 (section 10,
  retrait de `S45-LVE-ECRITURE`, total du sprint ramene a 5,5/8,0).
- **Date** : 2026-10-01
- **Auteur** : `camille-po`
- **Sprint** : s45 (deux instances paralleles de `nadia-prof`)
- **Prealable, en vigueur et non rouvert par ce document** : `DEC-0100` (perimetre chiffre a instance
  unique propose pour s45, trois lignes, `MAT-CE1-04`, `MUS-CP-05`, `EPS-CM1-09`, cible 4,0/5,0,
  marge 1,0, statut `Proposee`, jamais engage) - ce document ne rouvre pas le chiffrage de ces trois
  lignes, il les reprend telles quelles pour composer l'une des deux instances. `DEC-0098` (methode
  du dispositif a deux instances : borne 8,0, plafond individuel 5,0 par instance, au plus trois lots
  par instance, `PAR-2`). `DEC-0099` (methode de chiffrage ligne a ligne et logique de marge ; regle
  operationnelle reconduite trois sprints de suite - s43, s44, s45 propose - une seule ligne de
  mathematiques CE1 par sprint, motif de marge, pas de fond).
- **Lien** : `squad/decisions/produit/DEC-0100-proposition-de-perimetre-pour-s45.md` (sections 2 a 5,
  le vivier instruit et le chiffrage des trois lignes) ;
  `squad/decisions/produit/DEC-0098-fusion-de-s42-et-s43-en-un-seul-sprint-deux-instances.md`,
  sections 3 (borne), 4 (clauses de non-collision), 5 (methode de repartition), 9 (amendement,
  precedent de marge a 7,0/8,0) ; `squad/decisions/produit/DEC-0099-proposition-de-perimetre-pour-s43-
  et-s44.md`, section 9.4 (logique de marge, une seule ligne de mathematiques CE1 par sprint) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, section 13.5 (derivation de la borne a deux instances, 8,0) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-
  le-prix-d-une-ligne-marginale.md`, sections 4.2 (reconfirmation de 8,0) et 6.4 (critere de
  `nadia-prof` pour ecrire une clause de frontiere) ; `squad/programme/MATRICE-couverture.md`, etat de
  sortie de s44 (104/156, 66,7 %).
- **Competence programme visee** : transverse - ce document porte sur l'objectif, le perimetre et la
  borne d'un sprint, pas sur le contenu d'une ligne.

---

## 0. Commande et methode

Aucune `DEC` n'engageait un dispositif a deux instances pour s45 avant ce document. `DEC-0100`
proposait un perimetre a instance unique (4,0/5,0) et le disait explicitement : *"ce document
propose, il n'engage rien [...] seule une validation explicite de l'utilisatrice, dans un geste
distinct de celui-ci, engagerait ce perimetre."* L'utilisatrice a donne cette validation le
2026-10-01, mais sur un dispositif different de celui propose : deux instances, pas une. Ce document
rechiffre donc le perimetre sur le protocole de `DEC-0098` (methode du dispositif a deux instances),
avec la meme discipline de verification que `DEC-0099`/`DEC-0100` : aucune ligne n'est reprise de
confiance, chaque etat de matrice et chaque arbitrage pedagogique prealable cite est reverifie au
disque dans ce document, pas recopie depuis `DEC-0100`.

1. Nomme l'instruction recue et ce qu'elle valide reellement (section 1).
2. Reverifie, independamment, l'etat de la matrice et la borne a deux instances (section 2).
3. Reverifie ligne par ligne, sans reprise de confiance, le vivier deja instruit (section 3).
4. Verifie les clauses de non-collision (`PAR-2`, `CYC-1`, critere de frontiere) pour la composition
   retenue (section 4).
5. Compose deux instances sous la borne de 8,0 (section 5), et explique pourquoi le plafond
   mecanique n'est pas atteint (section 6).
6. Engage directement ce perimetre (statut `Acceptee`) et route l'ecriture des stories et l'ouverture
   du sprint (section 7).

## 1. L'instruction recue, et ce qu'elle valide

**Recue directement comme instruction de tache, sans fichier `planning.md` et sans relais par
`sofia-sm`**, citee mot pour mot le 2026-10-01 : *"augmente le perimetre en utilisant 2 instance de
nadia"*. C'est la **forme 3** de `squad/README.md`, section 2 bis (*"recu de MASTER, sans source dans
le depot"*), exactement le meme canal que celui par lequel `DEC-0098` a ete engagee le 2026-09-24 sur
une instruction de meme nature (*"oui fais tu dans un seul sprint avec 2 instances de nadia [...]"*).
Je la nomme comme telle plutot que de fabriquer un relais qui n'existe pas, et je ne la fais pas dire
plus qu'elle ne dit (les deux bornes de la forme 3, section 2 bis).

**Ce que l'instruction valide** : l'augmentation du perimetre de s45 en passant d'un dispositif a
instance unique (`DEC-0100`, 4,0/5,0) a un dispositif a deux instances paralleles de `nadia-prof`
(borne 8,0, `DEC-0098`). C'est l'objet explicite auquel l'instruction repond, `DEC-0100` etant le
seul document en attente de validation pour s45 au moment ou l'instruction est recue.

**Ce que je ne fais pas dire a cette instruction, comme `DEC-0098` ne l'a pas fait pour la sienne** :
le fait qu'elle engage mecaniquement les 8,0 unites de tarif de la borne. L'instruction demande
d'augmenter le perimetre, pas de l'engager au plafond quel qu'en soit le cout pedagogique ; combien
est effectivement atteignable sans rouvrir une ligne non instruite est l'objet des sections 3 a 6,
qui se verifient contre le depot, pas contre l'instruction.

**Ce que l'instruction ne rouvre pas** : le chiffrage ligne a ligne des trois lignes de `DEC-0100`
(`MAT-CE1-04`, `MUS-CP-05`, `EPS-CM1-09`) ni les exclusions de fond qu'elle pose (`QLM-CP-16`,
`EMC-CP-04`, `EMC-CM1-02`, `EMC-CM1-03`). Ce document les reprend telles quelles.

## 2. Etat de la matrice et borne a deux instances - verifies ce jour, pas repris de `DEC-0100`

**Etat de sortie de s44** : 104/156 (66,7 %), verifie contre `squad/programme/MATRICE-couverture.md`
(note du 2026-09-30 : trois lignes fermees `couvert-valide` a s44 - `MUS-CP-11`, `SCI-CM1-13`,
`MAT-CE1-18` - aucune ouverture partielle, aucun refus leve). Aucun sprint n'est ouvert pour s45 au
moment ou j'ecris ce document.

**Borne a deux instances** : verifiee non revisee depuis s41/s32, sur le meme protocole que
`DEC-0098` section 3. `git log` sur les deux fichiers d'arbitrage qui portent la derivation et la
reconfirmation de la borne (`ARBITRAGE-PREALABLE-s29`, section 13.5 ; `ARBITRAGE-PREALABLE-s32`,
section 4.2) ne montre aucun commit depuis `b1ee89f4` (s32, 2026-09-08). Aucune `DEC` entre `DEC-0098`
et ce document ne touche a la borne : `DEC-0099` (s43/s44, instance unique, confirme explicitement
section 1 que *"DEC-0098 a verifie et confirme la borne a deux instances (8,0, pour s41 et s42
seuls) sans jamais toucher a la borne a instance unique"*) et `DEC-0100` (s45, instance unique,
*"aucune demande de fusion ni d'instances paralleles n'a ete recue pour s45"*, a la date de son
ecriture) confirment toutes deux qu'aucun arbitrage nouveau n'a touche au chiffre.

> **Borne retenue pour s45 a deux instances : 8,0.** Inchangee depuis le 2026-09-08. Deux clauses
> reconduites sans changement : plafond individuel dur **5,0 par instance**, et **au plus trois lots
> par instance** (`PAR-8`, `DEC-0098` section 3).

## 3. Reverification du vivier, ligne par ligne, sans reprise de confiance

### 3.1 Groupe de mathematiques CE1

Quatre lignes portent un verdict `ACCEPTE SOUS CONDITIONS` ecrit par `nadia-prof`
(`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, sections 4 et 5 pour `MAT-CE1-04` et
`MAT-CE1-05` ; lot 2, meme fichier, pour `MAT-CE1-07` et `MAT-CE1-08`). Verifie ce jour, contre
`squad/programme/MATRICE-couverture.md` directement (pas contre la note de `DEC-0100`) : les quatre
lignes sont toujours `non couvert` (`Poser et effectuer une addition avec retenue`, `Poser et
effectuer une soustraction`, `Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et
4`, `Resoudre des problemes [...] a une ou deux etapes`, section "Mathematiques - CE1"). Aucun refus,
aucune reserve nouvelle, aucun changement d'arbitrage depuis `DEC-0100`.

**Une seule ligne de mathematiques CE1 par sprint, reconduite une troisieme fois** (s43, s44, s45
propose, meme motif que `DEC-0099` section 9.4) : la raison est une marge de rejeu, pas un motif de
fond - ecartee par la marge, pas par le fond, comme le dit explicitement `DEC-0099` section 9.7 pour
`MAT-CE1-04`. Dans un dispositif a deux instances, cette regle se lit aussi comme une consequence de
`PAR-2` : la ligne et ses trois voisines partagent toutes la meme matiere et le meme niveau
(mathematiques, CE1), et `PAR-2` interdit de partager ce couple entre deux instances. Au plus une
instance peut donc porter une ligne de mathematiques CE1 dans ce sprint ; je retiens, comme
`DEC-0100`, qu'une seule ligne y figure, pour la meme raison de marge.

Je retiens `MAT-CE1-04`, reprise telle quelle de `DEC-0100` (choisie a egalite avec `MAT-CE1-05`,
sans motif de fond sur l'ordre).

### 3.2 Vivier hors mathematiques - dix-sept lignes, reverifiees ligne par ligne

| Ligne | Matiere / niveau | Statut verifie ce jour contre `MATRICE-couverture.md` | Ecart avec `DEC-0100` |
|---|---|---|---|
| `QLM-CP-15` | questionner le monde CP | `non couvert` | aucun |
| `QLM-CP-16` | questionner le monde CP | `non couvert` | aucun - ecartee pour motif de fond (biais, `BIA-1`/`BIA-2`), non reouverte ici |
| `MUS-CP-05` | education musicale CP | `non couvert` | aucun |
| `EPS-CM1-09` | EPS CM1 | `non couvert` | aucun |
| `EMC-CP-04` | EMC CP | `non couvert` | aucun - ecartee pour motif de fond (`FER-3` epuisee avant qu'un item n'existe), non reouverte ici |
| `EMC-CP-14` | EMC CP | `non couvert` | aucun |
| `EMC-CM1-01` | EMC CM1 | `non couvert` | aucun |
| `EMC-CM1-02` | EMC CM1 | `non couvert` | aucun - ecartee pour motif de fond (ligne la plus delicate du lot avec `EMC-CM1-06`), non reouverte ici |
| `EMC-CM1-03` | EMC CM1 | `non couvert` | aucun - ecartee pour motif de fond (meme risque `EMC-4` que `EMC-CP-04`), non reouverte ici |
| `EMC-CM1-09` | EMC CM1 | `non couvert` | aucun |
| `EMC-CM1-10` | EMC CM1 | `non couvert` | aucun |
| `LVE-CP-06` | langues vivantes CP | `non couvert` | aucun |
| `LVE-CM1-06` | langues vivantes CM1 | `non couvert` | aucun |
| `SCI-CM1-03` | sciences et technologie CM1 | `non couvert` | aucun |
| `SCI-CM1-10` | sciences et technologie CM1 | `non couvert` | aucun - frontiere avec `EPS-CM1-10`, fermee a s43 (correction deja posee par `DEC-0100` section 3.1, reverifiee ici : `EPS-CM1-10` est bien `couvert-valide` depuis s43) |
| `SCI-CM1-14` | sciences et technologie CM1 | `non couvert` | aucun |
| `SCI-CM1-16` | sciences et technologie CM1 | `non couvert` | aucun |

Dix-sept lignes verifiees une par une directement contre `squad/programme/MATRICE-couverture.md` ce
jour : aucun changement de statut, aucune reserve ou refus nouveau, aucune ligne qui serait sortie du
vivier depuis `DEC-0100`.

### 3.3 Quatre lignes hors de portee - confirmees sans avis de faisabilite neuf

`QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09` requierent une illustration neuve non instruite.
Recherche faite ce jour, pas reprise de `DEC-0100` : aucun fichier `avis-faisabilite*.md` date
apres le 2026-09-25 n'existe dans `squad/sprints/` (le dernier sprint a en porter un est s43), et
aucune occurrence neuve de ces quatre codes n'apparait dans un fichier de `squad/conformite/` au-dela
de `ARBITRAGE-PREALABLE-s32`, deja connu. Les quatre lignes restent hors de portee pour ce document.

## 4. Verification des clauses de non-collision pour la composition retenue

**`PAR-2`** : les cinq lignes retenues en section 5 portent cinq couples matiere/niveau distincts
(mathematiques CE1, education musicale CP, EPS CM1, langues vivantes CM1, sciences et technologie
CM1) - aucun couple n'est partage entre les deux instances proposees, verifie par construction.

**`CYC-1`** : les deux seules lignes de cycle 2 du lot (`MAT-CE1-04`, `MUS-CP-05`) ne partagent aucun
objet du monde reel avec les trois lignes de cycle 3 (`EPS-CM1-09`, tenue des roles d'arbitre ;
`LVE-CM1-06`, vocabulaire anglais memorise ; `SCI-CM1-14`, environnement numerique) : une operation
d'addition, des familles d'instruments de musique, des roles sportifs, du vocabulaire anglais et des
regles d'usage numerique sont cinq objets distincts, verifie paire par paire.

**Critere de `nadia-prof` pour une clause de frontiere** (objet du monde reel partage entre lignes de
matieres differentes affectees a des instances differentes, `ARBITRAGE-PREALABLE-s32`, section 6.4) :
verification exhaustive des six paires croisees entre l'instance A (`MAT-CE1-04`, `MUS-CP-05`,
`EPS-CM1-09`) et l'instance B (`LVE-CM1-06`, `SCI-CM1-14`) - aucune ne porte le meme objet qu'une
autre. Aucune clause de frontiere n'est due.

## 5. Composition retenue - deux instances

**Instance A - reprise telle quelle de `DEC-0100`, sections 2 et 5, sans reouverture de son
chiffrage.**

| Instance | Ligne | Matiere / niveau | Tarif | Motif |
|---|---|---|---|---|
| A | `MAT-CE1-04` Poser et effectuer une addition avec retenue | mathematiques CE1 | 1,0 | `ACCEPTE SOUS CONDITIONS`, `ADP-CE1-1` a `ADP-CE1-6` ; reprise telle quelle de `DEC-0100` section 5 |
| A | `MUS-CP-05` Identifier des instruments et leurs familles | education musicale CP | 1,5 | jouable sur la seule moitie connaissance, sous `MUS-1` ; reprise telle quelle de `DEC-0100` section 5 |
| A | `EPS-CM1-09` Tenir les roles d'arbitre, d'observateur, de marqueur | EPS CM1 | 1,5 | jouable sur la seule moitie connaissance, sous `EPS-1` ; reprise telle quelle de `DEC-0100` section 5 |
| **Sous-total instance A** | | | **4,0 / 5,0** | marge individuelle 1,0, 3 lots |
| B | `LVE-CM1-06` Ecrire des mots et des expressions memorises | langues vivantes CM1 | 1,5 | jouable par saisie, sous `ANG-S1` et `ANG-S2` - lexique deja ferme et servi en choix, quinze mots de `LVE-CM1-04` (`couvert-valide`) |

**Amendement du 2026-10-01 (`camille-po`, s45, correction routee par `theo-archi`,
`squad/rapports/avis-faisabilite-s45-cinq-lots.md`, section 7.2 et Q6).** La ligne ci-dessus
attribuait le lexique disponible a deux lignes, `LVE-CM1-04` **et** `LVE-CM1-07`. **C'est faux** :
`LVE-CM1-07`, bien que `couvert-valide`, ne sert aucun mot anglais - ses dix items sont des questions
de decouverte culturelle en francais, sans aucune competence linguistique servie (`nadia-prof`, s32,
section 13, refus 8). Seuls les quinze mots de `LVE-CM1-04` composent le lexique ferme sur lequel
`ANG-S1` se tient. Le tarif (1,5) et le verdict de faisabilite ne changent pas : c'est une correction
de fait sur la source du lexique, pas une reouverture du chiffrage.
| B | `SCI-CM1-14` Connaitre quelques regles d'usage d'un environnement numerique | sciences et technologie CM1 | 1,5 | jouable au choix textuel, sur les connaissances seules, sous `SCI-1` a `SCI-3` |
| **Sous-total instance B** | | | **3,0 / 5,0** | marge individuelle 2,0, 2 lots |
| **Total du sprint** | | | **7,0 / 8,0** | marge de sprint 1,0 non allouee |

Chaque instance reste sous le plafond individuel de 5,0 et sous le plafond de trois lots (`PAR-8`).

## 6. Le plafond de 8,0 n'est pas atteint, et c'est chiffre, pas suppose

**Le maximum mecanique du vivier instruit, sans reouvrir aucune ligne ecartee pour motif de fond ni
aucune ligne hors de portee (section 3.3), est strictement superieur a 8,0** : l'instance A a elle
seule peut porter trois lots pour 4,0, et l'instance B, hors toute contrainte de budget, pourrait
porter jusqu'a trois lignes hors mathematiques a 1,5 chacune (4,5), pour un total brut de 8,5. **Ce
total brut depasse la borne et n'est donc pas retenu.**

**Pourquoi je m'arrete a 7,0 et non a 8,0 exactement** : la granularite des tarifs ne permet pas
d'atteindre 8,0 exactement dans ce vivier sans l'une des deux concessions suivantes, toutes deux
ecartees.

- **Ouvrir une seconde ligne de mathematiques CE1** (tarif 1,0 chacune) porterait le total a 8,0 pile
  (`MAT-CE1-04` et une seconde ligne du groupe dans l'instance A, par exemple), mais contredirait
  `PAR-2` si la seconde ligne allait dans l'instance B (meme couple matiere/niveau que `MAT-CE1-04`),
  et reproduirait dans l'instance A la marge de rejeu nulle que `DEC-0099` section 9.4 a explicitement
  ecartee trois sprints de suite, sans motif de fond nouveau qui justifierait de rouvrir cette regle
  ici. Je ne le fais pas.
- **Porter l'instance B a trois lignes** (4,5) porterait le total du sprint a 8,5, au-dessus de la
  borne de 8,0 - mecaniquement exclu.

**La composition retenue (7,0/8,0) est donc le maximum atteignable sous la borne, sans reouvrir une
ligne ecartee pour motif de fond, sans rouvrir la regle operationnelle sur les mathematiques CE1,
et sans depasser le plafond de 8,0.** Elle egale exactement le total que `DEC-0098` a atteint a la
cloture de s42 (7,0/8,0, amendement section 9), apres qu'un retrait concret y a montre que viser le
plafond mecanique sans marge de sprint coutait deux refus sur le meme lot a s41 (`DEC-0098`, section
6). Je retiens ici la meme marge par anticipation, plutot que de l'apprendre une seconde fois a
l'usage : **marge de sprint 1,0 non allouee**, en plus des marges individuelles de 1,0 (instance A) et
2,0 (instance B) - cette derniere strictement superieure a celle du terrain le plus charge de
`DEC-0098` (1,0 a l'origine, 0,5 a s41).

**Ce que je ne fais pas** : inventer une sixieme ligne non instruite pour combler l'ecart entre 7,0 et
8,0. Aucune ligne du vivier instruit (sections 3.1 a 3.3) ne permet de l'atteindre sans l'une des deux
concessions ecartees ci-dessus - le total atteignable avec les lignes deja instruites est donc
**7,0, pas 8,0**, et c'est chiffre explicitement plutot que suppose.

## 7. Engagement direct

**Confirmation de l'utilisatrice deja recue pour l'augmentation du perimetre et le passage a deux
instances** (section 1, forme 3) : ce document est **Acceptee** sur cette base, dans le meme
mouvement qui le redige, exactement comme `DEC-0099` section 10 l'a fait pour l'engagement de s44.

- **Perimetre engage** : les cinq lignes de la section 5, reparties sur deux instances de
  `nadia-prof`, total 7,0/8,0.
- **Stories ecrites** dans `docs/backlog/backlog.md`, section "Sprint 45", dans le meme mouvement que
  ce document (cinq stories, une par ligne, chacune nommant l'instance a laquelle elle est affectee).
- **`squad/programme/MATRICE-couverture.md` n'est pas modifiee** par ce document : les cinq lignes
  restent `non couvert` jusqu'au Done pedagogique de l'instance qui les audite.

## 8. Ce que ce document route

- **Ouverture du dossier de sprint** : a `sofia-sm`, avec repartition nominative du developpement et
  des deux instances de `nadia-prof` - ce document propose une affectation par instance (section 5),
  l'affectation nominative finale des developpeurs reste a `sofia-sm`, comme `DEC-0098` section 7.
- **Avis de faisabilite de `theo-archi`**, sur les cinq lots, directement : aucun arbitrage
  pedagogique prealable neuf n'est du, les cinq lignes etant deja instruites (section 3).
- **A `sofia-sm` et a l'utilisatrice, non bloquant mais a peser explicitement** : la marge de sprint
  de 1,0 est deliberement non allouee des l'ouverture (section 6), contrairement a `DEC-0098` qui ne
  l'a trouvee qu'apres un retrait concret a s42. Rien n'empeche de la consommer si l'une des deux
  instances demande un second audit sur un lot refuse, dans la limite du plafond individuel de son
  instance.
- **Rien n'est route a `nadia-prof` sur une clause de frontiere** : verifiee absente (section 4).

## 9. Ce que ce document ne fait pas

- Il ne rouvre aucun des tarifs ni le verdict d'aucune des trois lignes de `DEC-0100` : le chiffrage
  de l'instance A est repris tel quel.
- Il ne rouvre aucune des quatre lignes ecartees pour motif de fond par `DEC-0100` section 6
  (`QLM-CP-16`, `EMC-CP-04`, `EMC-CM1-02`, `EMC-CM1-03`), ni les quatre lignes hors de portee de la
  section 3.3.
- Il ne fixe aucune borne de tirage, aucune forme de distracteur, ni aucun contenu pedagogique pour
  aucune des cinq lignes : ce travail reste entierement a `nadia-prof`.
- Il ne choisit aucun mecanisme technique : ce travail reste a `theo-archi`.
- Il ne garantit la fermeture d'aucune des cinq lignes retenues : l'avis de faisabilite de
  `theo-archi` et le Done pedagogique final de chaque instance de `nadia-prof` restent a rendre.
- Il ne tranche pas si la marge de sprint non allouee de 1,0 est suffisante : c'est nomme, chiffre et
  route (section 8), pas resolu par moi.

## 10. Amendement du 2026-10-01 (`camille-po`, s45) - retrait de `S45-LVE-ECRITURE` (`LVE-CM1-06`),
motif `R6`

**Ce qui change** : `nadia-prof` a repondu aux questions bloquantes de `theo-archi`
(`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, Q5, section 5) et a tranche que le motif de
`R6` (`squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`, le refus de toute saisie qui demande de
produire les lettres d'un mot sous correction automatique de clavier) s'applique a `LVE-CM1-06` :
*"Ecrire des mots et des expressions memorises"* demande exactement ce geste, en anglais, sans
protection ni par `SAI-2b` ni par la premisse de l'adulte accompagnant au CM1. **Verdict : `LVE-CM1-06`
est refusee a la saisie, au motif de `R6`, et `S45-LVE-ECRITURE` sort du perimetre de s45**
(arbitrage, section 5.1 et 5.4).

**Cette section amende la composition retenue (section 5) et le total (section 6), sans reecrire les
sections precedentes.** La ligne B-1 ci-dessous n'est plus engagee :

| Instance | Ligne retiree | Motif | Tarif libere |
|---|---|---|---|
| B | `LVE-CM1-06` Ecrire des mots et des expressions memorises | `R6` (saisie sous correction automatique de clavier), arbitrage du 2026-10-01 | 1,5 |

**Composition de l'instance B apres retrait** : `SCI-CM1-14` seule, 1,5/5,0 (au lieu de 3,0/5,0).
**Total du sprint apres retrait : 5,5/8,0** (au lieu de 7,0/8,0), marge de sprint toujours 1,0 non
allouee, et une marge nouvelle de 1,5 liberee sur l'instance B par le retrait, qui s'ajoute a la marge
individuelle deja comptee de 2,0 pour porter la marge individuelle de l'instance B a 3,5/5,0.

**Je ne reengage aucune ligne pour combler l'instance B dans ce document.** Ce n'est pas un geste a
prendre seule ici : la marge disponible sur l'instance B (3,5 avant plafond de 5,0, ou 2,5 avant la
borne de sprint de 8,0 si l'instance A reste a 4,0) reste au vivier, a combler dans un geste distinct
si l'utilisatrice le souhaite.

**Ce que je ne fais pas non plus** : je ne tranche pas la decouverte retroactive de `nadia-prof`
(arbitrage, section 5.3) sur `saisie-mot-dicte-cp`, `couvert-valide` depuis s26 sans que `R6` y ait
jamais ete examine. Cette decouverte est hors du perimetre de s45 et routee a part (voir
`docs/backlog/backlog.md`, section "Backlog ouvert pour s46 et au-dela", story
`S46-VERIF-SAISIE-MOT-DICTE-CP`).

**Mise a jour de `docs/backlog/backlog.md`** : la story `S45-LVE-ECRITURE` reste ecrite dans la
section "Sprint 45", marquee en tete comme retiree, motif `R6`, pour servir si la ligne est
reproposee un jour - elle n'est pas supprimee.
