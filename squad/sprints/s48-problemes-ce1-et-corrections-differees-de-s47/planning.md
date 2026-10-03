# Planning - s48 - proposition : problemes a une ou deux etapes en mathematiques CE1, et les cinq
corrections differees de s47

- **Horodatage de debut** : 2026-10-03 09:12 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s48-problemes-ce1-et-corrections-differees-de-s47`, dossier ouvert par ce planning -
  aucun document prealable ne l'avait ouvert.
- **Nature de ce document : une proposition, pas un objectif gele.** A la difference de s44 a s47,
  aucune `DEC` n'ouvre a ce jour le perimetre de s48. Je ne la fabrique pas a sa place - router le
  perimetre, la priorite et le chiffrage a `camille-po` est ma regle, pas une option (table de
  routage, `CLAUDE.md` racine). Ce document rassemble ce que le depot sait deja, propose un
  perimetre et route explicitement ce qui manque avant qu'un objectif puisse etre gele.

## 1. Lignee verifiee avant toute ecriture

Pas de worktree dedie : ce planning est un document unique, ecrit directement sur `round-two`.
Verifie avant d'ecrire : `git rev-parse --abbrev-ref HEAD` rend `round-two` ; `git status --short`
rend vide (arbre propre) ; `git log -1 --format=%H` rend `bfdc7fee0d60dd18657ebda59a9b9996f829045e`,
la fusion de la retro de s47. Ce planning ne touche aucun fichier hors de
`squad/sprints/s48-problemes-ce1-et-corrections-differees-de-s47/planning.md`.

## 2. Ce que ce planning ne peut pas faire, et pourquoi

Trois pieces manquent, et aucune des trois n'est de mon autorite :

- **Aucune `DEC` ne fixe le perimetre de s48.** Chacune des cinq stories de correction differee de
  s47 porte elle-meme la mention "a router par une `DEC` d'ouverture de sprint"
  (`docs/backlog/backlog.md`, section "Backlog - sprints suivants"). Geler un objectif sans cette
  `DEC` reviendrait a trancher une question de perimetre a la place de `camille-po`.
- **Aucun avis de faisabilite dedie de `theo-archi` n'existe pour s48.** L'arbitrage prealable de
  `nadia-prof` sur `MAT-CE1-08` (section 3) couvre le fond pedagogique, pas la confirmation
  technique (collision ArchUnit, pieges de fabrication) qu'un avis de faisabilite rend
  habituellement a l'ouverture de chaque sprint depuis s44.
- **Aucun chiffrage n'existe pour les cinq stories de correction differee ni pour `SOP-CE1-R1`.**
  Le tarif d'une ligne neuve de mathematiques CE1 est deja fixe (1,0, `DEC-0074`/`DEC-0077`,
  section 8.2) ; celui d'une correction ne l'est pas, et je ne l'invente pas.

Ce planning route ces trois manques (section 6) plutot que de les combler lui-meme.

## 3. Etat du vivier instruit, reverifie ce jour

### 3.1 Mathematiques CE1 - `MAT-CE1-08`, seule ligne instruite restante a `couvert-valide`

`MAT-CE1-08` ("Resoudre des problemes d'addition, de soustraction et de multiplication a une ou
deux etapes") est verifiee `non couvert` dans `squad/programme/MATRICE-couverture.md`, section
"Mathematiques - CE1 (cycle 2)", a ce jour. Elle est deja instruite par `nadia-prof`
(`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 2) : **ACCEPTE SOUS
CONDITIONS `PRO-CE1-1` a `PRO-CE1-7`**, servable par texte seul, `couvert-valide` atteignable sur un
paquet unique. `DEC-0104`, section 2.1, la cite nommement comme "la candidate suivante, disponible
au meme tarif (1,0) et aux memes conditions" que `MAT-CE1-07`, qui vient de fermer.

C'est la **sixieme fois** que la regle "une seule ligne de mathematiques CE1 par sprint" serait
reconduite si elle entrait a s48 (s43 a s47 l'ont deja reconduite cinq fois) - je le signale
explicitement, sur le meme motif que les plannings precedents, plutot que de le laisser silencieux.

### 3.2 Hors mathematiques CE1 - le vivier instruit est epuise

Le vivier de quatre lignes cite a l'identique dans `DEC-0100`, `DEC-0101`, `DEC-0102` et `DEC-0104`
section 2.2 (`QLM-CP-15`, `EMC-CP-14`, `EMC-CM1-10`, `LVE-CP-06`) **est integralement ferme depuis
la cloture de s47** - verifie ligne par ligne dans `MATRICE-couverture.md`, les quatre sont
`couvert-valide`. **Aucune ligne instruite a `couvert-valide` atteignable n'existe plus, a ce jour,
en dehors de mathematiques CE1.** Ouvrir une seconde instance de `nadia-prof` a s48 suppose
d'instruire une ligne neuve au prealable (avis de faisabilite de `theo-archi`, arbitrage prealable
de `nadia-prof`), travail qui n'a pas commence.

### 3.3 Francais CE1 - candidate nommee, non instruite

`DEC-0104`, section 1.2, integre le referentiel de francais CE1 (26 lignes) a la matrice mais
n'instruit aucune de ses lignes, et le nomme explicitement comme "premiere candidate pour un sprint
ulterieur, apres un avis de faisabilite dedie". Rien n'a change depuis : aucun avis de faisabilite,
aucun arbitrage prealable ligne par ligne n'existe sur ce referentiel a ce jour. Non engageable a
s48 sans ce travail prealable.

### 3.4 Les cinq stories de correction differee de s47 (huit reserves)

Deja ecrites avec besoin, competence visee et criteres d'acceptation complets dans
`docs/backlog/backlog.md`, section "Backlog - sprints suivants" :

| Story | Reserve(s) | Ligne deja fermee | Ce qu'elle corrige |
|---|---|---|---|
| `TAB-CE1-R1` | seule | `MAT-CE1-07` | l'indice des cinq items a facteur 1 enseigne l'erreur `× 1` / `+ 1`, forme deja validee au CM1 |
| `AUT-CP-R1` | seule | `QLM-CP-15` | deux indices (items 7 et 13) ne redonnent plus mot pour mot la meme reponse |
| `ANG-CP-R1` / `ANG-CP-R2` | groupees | `LVE-CP-06` | distracteurs de l'item 10 et 12 repris dans leur propre famille ; indices des items 1 et 2 remis en mots de CP |
| deux reserves (indice item 7, vocabulaire item 11) | groupees, sans identifiant dedie | `EMC-CP-14` | indice de l'item 7 prescrit la bonne conduite sans ambiguite ; vocabulaire de l'item 11 ramene au CP |
| deux reserves (distracteur hors categorie, question mal formee item 11) | groupees, sans identifiant dedie | `EMC-CM1-10` | a verifier contre `squad/conformite/FICHE-bandit-manchot-vote-election-cm1.md`, section du verdict du troisieme passage |

Chaque story cite deja, mot pour mot, la forme que `nadia-prof` attend en remplacement : le risque
pedagogique de ces corrections est deja borne, contrairement a une ligne neuve.

### 3.5 `SOP-CE1-R1` (reserve de s46) - arbitrage rendu, reprise non engagee

`nadia-prof` a deja arbitre cette reserve hors du chiffrage de s47 (section 6 du planning de s47) :
une cinquieme forme de distracteur est admise et rendue obligatoire sur les items sans emprunt du
paquet `ce1-soustractions-posees`. La reprise du paquet lui-meme n'est engagee dans aucun sprint a
ce jour. Chiffrage manquant (section 2).

### 3.6 Recherche d'une candidate CM1 symetrique a francais CE1 - aucune trouvee

En reponse au risque signale en section 6 (vivier hors mathematiques CE1 epuise), j'ai cherche dans
le depot une matiere ou un niveau deja integre a la matrice mais jamais instruit cote CM1, sur le
meme patron que francais CE1 (section 3.3). Methode : lecture des dix referentiels CM1 de
`squad/programme/MATRICE-couverture.md` (mathematiques, francais, histoire-geographie, langues
vivantes, arts plastiques, education musicale, education physique et sportive, enseignement moral
et civique, histoire des arts, sciences et technologie), et recherche du mot "candidate" dans
`squad/decisions/produit/DEC-009*.md` et `DEC-010*.md`.

**Resultat : aucune matiere ni niveau CM1 n'est nomme dans le depot comme "integre mais jamais
instruit".** Les dix referentiels CM1 ont tous recu leur Jugement de faisabilite ligne par ligne a
leur creation (`DEC-0074`, 2026-09-03) - a la difference de francais CE1, qui est entre dans la
matrice le 2026-10-01 sans ce travail (`DEC-0104`, section 1.2, le dit explicitement). Les lignes
CM1 encore `non couvert` portent chacune deja un motif d'instruction individuel ecrit (palier 2
requis, un chantier de sourcing d'images d'oeuvres route et non engage, prerequis d'un second
paquet lexical pour `LVE-CM1-05`) : ce sont des lignes candidates deja instruites et bloquees, pas
une matiere entiere en attente d'un premier passage de `nadia-prof`.

**Le point le plus proche d'une candidate nommee, mais de nature differente, est le chantier de
sourcing d'un corpus d'oeuvres du domaine public**, qui bloque plusieurs lignes a la fois en
histoire des arts CM1 et en arts plastiques (CP et CM1). Je l'avais signale a tort comme "`DEC-0075`
non tranchee" : **`camille-po` a verifie et corrige ce point (`DEC-0105`, section 2) - `DEC-0075`
est tranchee depuis le 2026-09-05 par `DEC-0077`, section 4 (option B, corpus du domaine public).**
Ce qui bloque reellement ces neuf lignes n'est pas une decision en attente, mais l'execution de
cette decision : le chantier de sourcing et de verification des oeuvres, route par `DEC-0077` a
`theo-archi` via moi-meme, n'a laisse aucune trace d'engagement depuis s29. Ce n'est pas un travail
d'instruction pedagogique au sens de ce planning (les lignes sont deja jugees par `nadia-prof`),
c'est un chantier technique en souffrance que `DEC-0105` me route, avec `theo-archi`, separement de
s48.

**Arbitrage de `camille-po` sur le choix CM1 lui-meme.** `camille-po` a repris ma recherche et
l'a reverifiee plutot que de la supposer exacte (`DEC-0105`, section 1) : les dix referentiels CM1
couvrent l'integralite des enseignements reels du cycle 3, aucun onzieme enseignement n'existe dans
le programme de l'Education nationale qui serait absent de la matrice, et les lignes CM1 encore
`non couvert` sont deja instruites sous un motif de blocage ecrit, jamais en attente d'un premier
Jugement de faisabilite. **Conclusion ecrite dans `DEC-0105` : aucune candidate CM1 n'est ouverte a
l'instance B pour s48.** Le point que j'avais laisse ouvert est desormais tranche, pas seulement
signale.

## 4. Perimetre propose a `camille-po` (non gele)

Je propose, sans le trancher :

- **`MAT-CE1-08`** (tarif 1,0, conditions `PRO-CE1-1` a `PRO-CE1-7` deja ecrites), seule ligne neuve
  du perimetre - aucune autre n'est instruite a `couvert-valide` atteignable (section 3.2, 3.3).
- **Les cinq stories de correction differee de s47** (section 3.4), pour fermer les huit reserves
  pendant qu'elles sont fraiches et que leur forme de remplacement est deja ecrite - risque faible,
  aucune ne touche a une ligne non fermee.
- **`SOP-CE1-R1`** (section 3.5) comme candidate supplementaire si `camille-po` juge que le volume
  du sprint le permet une fois les quatre points ci-dessus chiffres.

**Je propose en plus une seconde instance de `nadia-prof`, dediee a l'instruction, pas a un Done
pedagogique.** A la difference de l'instance qui audite `MAT-CE1-08` et les cinq corrections
(instance A, Done pedagogique), cette instance B ne joue aucun jar et ne prononce aucun Done : elle
ecrit un Jugement de faisabilite / arbitrage prealable, exactement comme `nadia-prof` l'a deja fait
pour mathematiques CE1 entre s36 et s37, pour reconstituer un vivier avant qu'il ne manque a s49.

**L'instance B instruit le referentiel francais CE1 seule** (26 lignes, section 3.3), memes bornes
de lisibilite deja posees par `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` a reverifier pour le CE1,
et la borne de longueur du texte support que `nadia-prof` elle-meme signale comme non posee
(`DEC-0104` section 1.1). **Aucune incertitude residuelle sur le CM1** : `camille-po` a tranche
qu'aucune candidate CM1 n'est ouverte a s48 (`DEC-0105`, section 1), les dix referentiels CM1
couvrant deja l'integralite du programme reel de cycle 3.

Cette instance B ne ferme aucun lot de developpement a s48 : son produit est un document
d'instruction (arbitrage prealable), pas un paquet jouable. Elle ne collide donc pas avec
l'instance A sur `PAR-1`/`PAR-2` (aucun lot partage), et son cout n'entre pas dans le meme budget
qu'une instance de Done pedagogique - `camille-po` reste seule a dire si elle merite son propre
budget ou si elle est hors chiffrage, sur le meme modele que `SOP-CE1-R1` a s47 (section 3.5).

## 5. Repartition des taches proposee, contingente au gel du perimetre

| Agent | Tache |
|---|---|
| `camille-po` | Ecrit la `DEC` d'ouverture de s48 (perimetre, tarif des cinq corrections et de `SOP-CE1-R1` si retenue, budget de l'instance B d'instruction) |
| `theo-archi` | Rend un avis de faisabilite dedie a s48 sur `MAT-CE1-08` (confirmation technique, pieges de fabrication) et sur les cinq stories de correction (verifie qu'aucune ne touche de code de production au-dela du contenu YAML et de son test de paquet) ; rend egalement un avis de faisabilite sur le referentiel francais CE1 une fois l'arbitrage prealable de l'instance B ecrit ; engage ou refuse explicitement par ecrit le chantier de sourcing du corpus d'oeuvres du domaine public route par `DEC-0077` (`DEC-0105`, section 2), en souffrance depuis s29 |
| `nadia-prof`, instance A (Done pedagogique) | Audite le paquet neuf de `MAT-CE1-08` une fois livre ; rejoue chacun des cinq paquets corriges et confirme la levee des huit reserves ; rejoue `ce1-soustractions-posees` si `SOP-CE1-R1` est retenue |
| `nadia-prof`, instance B (instruction, aucun Done pedagogique) | Ecrit le Jugement de faisabilite / arbitrage prealable du referentiel francais CE1, ligne par ligne (26 lignes) - seul terrain de l'instance B, `DEC-0105` |
| `marc-dev`, `lea-dev`, `sofia-sm` (casquette dev) | Affectation nominative finale une fois le perimetre gele ; proposition de repartition : un developpeur sur `MAT-CE1-08` (complexite comparable a `MAT-CE1-07`), les deux autres se partagent les cinq corrections (volume faible, un ou deux fichiers YAML et leur test par correction) |
| `yanis-qa` | Rapport qualite de cloture (`mvn -B clean verify`, delta de tests explique, catalogue) |
| `relecteur-pedagogique` | Relit les textes neufs et corriges (orthographe, accentuation, conventions de redaction) avant la cloture |
| `ines-design` | Aucune tache due a ce jour : perimetre entierement textuel, aucun rendu visuel neuf attendu |

## 6. Risques et points a trancher, par ordre de priorite

1. **Le vivier instruit hors mathematiques CE1 est epuise - ferme par `DEC-0105`.** Ce planning
   propose une instance B de `nadia-prof` dediee a l'instruction (pas au Done pedagogique) du
   referentiel francais CE1, candidate deja nommee (section 3.3, section 4). Le point que j'avais
   laisse ouvert - une eventuelle candidate CM1 symetrique - est tranche par `camille-po` :
   `DEC-0105` constate qu'aucune n'existe, les dix referentiels CM1 couvrant deja l'integralite du
   programme reel de cycle 3 et leurs lignes `non couvert` restantes etant deja instruites sous un
   motif de blocage ecrit. **Plus aucun arbitrage n'est du sur ce point pour s48.** `DEC-0105`
   route separement, a `theo-archi` et a moi-meme, l'engagement ou le refus explicite du chantier de
   sourcing d'un corpus d'oeuvres du domaine public (`DEC-0077`, en souffrance depuis s29) - hors
   perimetre de s48, repris en section 5.
2. **Aucune `DEC` n'ouvre s48.** Tant qu'elle n'existe pas, aucun developpeur ne peut etre affecte
   nominativement avec certitude sur le perimetre final.
3. **Aucun avis de faisabilite dedie de `theo-archi` n'existe pour `MAT-CE1-08` dans le contexte de
   s48** : l'arbitrage pedagogique prealable existe (section 3.1), pas la confirmation technique.
4. **Chiffrage manquant** des cinq stories de correction differee et de `SOP-CE1-R1` - sans lui,
   le budget de sprint ne peut pas etre arrete.
5. **`EMC-CM1-10`, deux reserves groupees (section 3.4)** : leur forme de remplacement n'est pas
   recopiee ici mot pour mot, a verifier contre `squad/conformite/FICHE-bandit-manchot-vote-
   election-cm1.md` avant d'affecter cette correction a un developpeur.

## 7. Ce que ce planning ne fait pas

- Il ne gele aucun objectif : aucune `DEC` ne le fonde a ce jour.
- Il ne tranche pas l'ouverture de l'instance B d'instruction, il la propose - son perimetre (francais
  CE1 seul, aucune candidate CM1) est desormais arbitre par `DEC-0105`, plus aucun choix n'y reste
  ouvert.
- Il n'engage pas le chantier de sourcing du corpus d'oeuvres du domaine public (`DEC-0077`,
  `DEC-0105` section 2) : route a `theo-archi` et a moi-meme, separement de s48 (section 5).
- Il ne chiffre aucune story : le chiffrage appartient a `camille-po`.
- Il n'affecte nominativement aucun developpeur de maniere definitive : l'affectation de la section
  5 est une proposition, pas une decision, et sera reprise une fois le perimetre gele.
- Il ne cree ni n'amende aucun `ADR` ni aucune `DEC`.

## Decisions prises

**Le perimetre de s48 est gele par `DEC-0106` (2026-10-03).** Perimetre de l'instance A de
`nadia-prof` (Done pedagogique) : `MAT-CE1-08` (1,0), les cinq stories de correction differee de s47
(`TAB-CE1-R1`, `AUT-CP-R1`, `ANG-CP-R1`/`ANG-CP-R2` groupees, les deux reserves d'`EMC-CP-14`
groupees, les deux reserves d'`EMC-CM1-10` groupees, 0,5 chacune) et `SOP-CE1-R1` (0,5) - total
**4,0/5,0**, marge 1,0. Le tarif d'une correction differee est fixe a 0,5 par story (`DEC-0106`
section 2, precedent `PAR-10` de `DEC-0085`), sauf si une correction touche en realite la majorite
des items de son paquet, auquel cas `PAR-14` reprend la main. L'instance B de `nadia-prof` entre au
perimetre pour instruire le referentiel francais CE1 seul (`DEC-0105`), hors chiffrage de la borne
de l'instance A, sans unite de mesure chiffree, bornee a rendre son Jugement de faisabilite complet
avant la cloture de s48 (`DEC-0106` section 4).

Les trois points que ce planning routait en section 6 sont donc tranches ainsi : le point 1 (vivier
epuise hors mathematiques CE1) reste ferme par `DEC-0105`, sans mouvement supplementaire ; le point 2
(aucune `DEC` n'ouvre s48) est leve par `DEC-0106` ; le point 4 (chiffrage manquant des cinq
corrections et de `SOP-CE1-R1`) est leve par `DEC-0106` section 2 et 3. **Reste ouvert, non leve par
cette decision** : le point 3 (aucun avis de faisabilite technique dedie de `theo-archi` pour s48 sur
`MAT-CE1-08` et sur les cinq corrections) et le point 5 (forme exacte de la correction d'`EMC-CM1-10`,
a verifier contre `squad/conformite/FICHE-bandit-manchot-vote-election-cm1.md` avant affectation).
Je reviens affecter nominativement les developpeurs et les deux instances de `nadia-prof` des que
l'avis de faisabilite de `theo-archi` existe dans le depot.
