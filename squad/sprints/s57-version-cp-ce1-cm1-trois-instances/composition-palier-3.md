# s57 - composition du palier 3 : affectation des developpeurs, table d'isolation, point de controle poste/story

- **Auteure** : `sofia-sm`, casquette Scrum Master, 2026-10-19 23:05 (UTC+02:00), lu par `date`. Arbre : `round-two` a `ee9ecd44`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` ne montre que `.claude/settings.local.json` (non suivi) avant ecriture.
- **Ce que ce fichier est** : ce qui est a moi dans la composition du palier 3 : l'affectation nominative des developpeurs, la table d'isolation (condition (d) de `D2`), le point de controle poste/story (`DEC-0086` section 5, `PAR-16`). **Ce qu'il n'est pas** : la composition de `camille-po` (lots et instances : `preparation-palier-3-ce1-camille.md` section 8, `a5748847`) ni les prix et l'affectation d'instance de `nadia-prof` (`reponses-nadia-questions-stories-palier-3.md` 7.2, `c3d1ebcf`). Je les reprends sans modifier un prix ni un lot. Modele : `composition-palier-2.md`, ecrit par `camille-po` ; celui-ci est de `sofia-sm` parce que ses trois contenus sont des postes de `sofia-sm`.
- **Statut** : engage par l'amendement de `planning.md` du 2026-10-19, qui inscrit la composition avant l'ouverture de tout worktree. Ce fichier n'ouvre aucun worktree, ne cree aucune branche, n'ecrit aucun code.
- **Sources** : `arbitrage-nadia-preparation-palier-3-ce1.md` (`b8fa7f9c`, sections 1, 2, 4) ; `reponses-nadia-questions-stories-palier-3.md` (`c3d1ebcf`, sections 1 a 8) ; `preparation-palier-3-ce1-camille.md` section 8 ; `reponses-theo-questions-stories-palier-3.md` (`34f6734a`) ; `avis-theo-preparation-palier-3-ce1.md` et `decision-theo-garde-ce1-e3.md` (`08d0dc0e`) ; `cloture-palier-2.md` (`ad65827e`, sections 8, 9, 14) ; `planning.md` (sections "Charge des developpeurs", "Isolation", amendements du 2026-10-15 20:56 et du 2026-10-16 21:46) ; `docs/backlog/backlog.md` (`ee9ecd44`, annotation "Palier 3 de s57" et son amendement) ; `DEC-0120` A.66, A.115 ; `DEC-0086` section 5.

## Resume

| Code | Point | Valeur |
|---|---|---|
| CO1 | Total compose, prix de `nadia-prof` | **8,0** sur 8,0 ; 5 lots ; 1,5 de marges allouees a aucun poste ; 0 non affecte |
| CO2 | Par instance | A 4,0 (3 lots) ; A' 2,0 (1 lot) ; B 2,0 (1 lot) |
| CO3 | Hors `PAR`, engages | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (`Z1`, `Z2`) ; commit de `RL5`, `RL6`, `RL7` |
| CO4 | Developpeurs deduits des regles ecrites | `theo-archi` (`Z1`, `Z2`) ; `sofia-sm` casquette developpeuse (`RL5` a `RL7`, sous condition) ; `marc-dev` (`-06`, `-07`, `-11`) |
| CO5 | Developpeurs **a decider** | lot `MAT-CE1-01` (B) et lot `EMC-CE1-01` (A') : `lea-dev` ou `sofia-sm`, distincts. Decideuse : l'utilisatrice (section 8, `AD1`) |
| CO6 | Ordre `Z1`/`Z2`, `RL5` a `RL7`, lots | **propose** par `camille-po`, lecture confirmee par `theo-archi`, **decide par aucun fichier** (section 5) |
| CO7 | Reports a l'ouverture | 0 si `AD1` est repondue avant la fusion de l'amendement ; sinon 2, premiers reports (`DEC-0120` A.66) |
| CO8 | Contradictions trouvees | deux (section 9) : les stories de `-06` et `-07` proposent `lea-dev`, ma regle l'exclut ; `A.115` rang 2 dit *"avant tout autre commit de code"* et place un commit de test au rang 1 |

## 1. Les bornes, et comment cette composition les tient

| Regle | Source | Cette composition |
|---|---|---|
| 8,0 par palier | `PAR-9` | 8,0 compose, 0 non affecte (section 2) |
| 5,0 et trois lots au plus par instance | `PAR-8` | A 4,0 et 3 lots (borne de lots atteinte) ; A' 2,0 et 1 lot ; B 2,0 et 1 lot |
| Une matiere et un niveau ne sont jamais partages entre instances | `PAR-2` | francais CE1 a A, mathematiques CE1 a B, EMC CE1 a A' (arbitrage de `nadia-prof`, 2.2) |
| Aucun terrain neuf tant qu'A' existe | `PAR-13` | aucun lot sur ligne sans paquet instruit ; aucun lot de questionner le monde CE1 audite a s57 (`nadia-prof`, 7.4) |
| Aucun prix alloue a un poste sans story | `PAR-16` | cinq lots et la lecture complete portent une story (section 3) ; `Z1`, `Z2`, `RL5` a `RL7` sont a prix 0 |
| Trois lots de A dans l'ordre de `nadia-prof` | `A.115` rang 3 | `-06`, `-07`, `-11` |
| Aucun chiffre au-dela de 8,0 | arbitrage s57, 11.4 | aucun |

## 2. Les postes et leurs prix

Prix et affectation d'instance : `nadia-prof`, `reponses-nadia-questions-stories-palier-3.md` 7.2. Composition : variante (i) de `camille-po` (section 8.2), retenue sur la delegation de l'utilisatrice du 2026-10-19 pour obtenir la meilleure couverture (`backlog.md`, annotation "Palier 3 de s57" : *"continue au mieux pour obtenir la meilleure couverture"*, relayee par `camille-po`, forme 2 de `DEC-0062` : je n'ai pas lu ces mots de sa main).

| Instance | Poste | Prix | Nature |
|---|---|---|---|
| A (groupe X) | arbitrage prealable | 0,5 | fixe |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste |
| | `S57-BR-FRA-CE1-06`, `ce1-inference` | 1,0 | lot |
| | `S57-BR-FRA-CE1-07`, `ce1-texte-documentaire` | 1,0 | lot |
| | `S57-BR-FRA-CE1-11`, `ce1-negation` | 1,0 | lot |
| | **Total A** | **4,0** | 3 lots |
| A' | arbitrage prealable | 0,5 | fixe |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste |
| | `S57-BR-EMC-CE1-01`, `ce1-emc-biens-communs` | 1,0 | lot ; prix de l'audit, l'ecriture du paquet n'est pas un poste du bareme |
| | **Total A'** | **2,0** | 1 lot |
| B | arbitrage prealable | 0,5 | fixe |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste |
| | `S57-BR-MAT-CE1-01`, `ce1-nombres-jusqu-a-1000` | 1,0 | lot ; plafond `en cours` |
| | **Total B** | **2,0** | 1 lot |
| **Palier** | | **8,0** | 5 lots |

Arithmetique, lue le 2026-10-19 (`awk -F'|'` sur onze lignes `instance|poste|prix|etat`) : A 4,00 ; Ap 2,00 ; B 2,00 ; total 8,00 ; par etat, `engage` 4,50 ; `marge` 1,50 ; `a-decider` 2,00 (les deux lots de `AD1`).

## 3. Point de controle poste/story (`DEC-0086` section 5, `PAR-16`)

Moment : a la cloture du planning, avant le premier daily. Titres lus par `grep -c "^#\{3,4\} .*<story>" docs/backlog/backlog.md` sur `ee9ecd44` ; le compte compte aussi les annotations qui portent le meme titre.

| # | Poste | Story, titres au backlog | Inst. | Agent nominatif | Prix | Etat |
|---|---|---|---|---|---|---|
| p3-1 | Arbitrage prealable et lecture complete | `S57-ARB-PALIER-3` (2 : le titre de `Q-X6` et la story) | A, A', B | `nadia-prof` | 0,5 par instance | **engage** : part ecrite livree (`c3d1ebcf`, section 7). Reste la ligne datee *"lecture complete"* de chaque paquet avant son premier verdict |
| p3-2 | Marges de rejeu | aucune | A, A', B | aucun | 0,5 par instance | nommees, allouees a aucun poste |
| p3-3 | Garde de `C1'` : virgule dans `:has`/`:is` et CSS imbrique | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (1) | transverse, hors `PAR` | `theo-archi` (forme et commit) ; `yanis-qa` (mesure par mutation) | 0 | **engage** |
| p3-4 | Commit unique de `RL5`, `RL6`, `RL7` | **aucune** (0) | transverse, hors `PAR` | `sofia-sm`, casquette developpeuse, sous `AD2` | 0 | **engage sans story**, ecart dit : `AD3`. Meme cas que le script d'`additions-cp` au palier 2 (`Q-S5`) |
| p3-5 | Lot `ce1-inference` | `S57-BR-FRA-CE1-06` (1) | A | `marc-dev` ; audit `nadia-prof` (A) | 1,0 | **engage** |
| p3-6 | Lot `ce1-texte-documentaire` | `S57-BR-FRA-CE1-07` (1) | A | `marc-dev` ; audit `nadia-prof` (A) | 1,0 | **engage** |
| p3-7 | Lot `ce1-negation` | `S57-BR-FRA-CE1-11` (1) | A | `marc-dev` ; audit `nadia-prof` (A) | 1,0 | **engage** |
| p3-8 | Lot `ce1-nombres-jusqu-a-1000` | `S57-BR-MAT-CE1-01` (1) | B | **a decider** (`AD1`) ; audit `nadia-prof` (B) | 1,0 | **non engage tant que l'agent n'est pas nomme** |
| p3-9 | Lot `ce1-emc-biens-communs`, paquet a ecrire dans le lot | `S57-BR-EMC-CE1-01` (1) | A' | **a decider** (`AD1`) ; audit `nadia-prof` (A') | 1,0 | **non engage tant que l'agent n'est pas nomme** |

**Deux issues, jamais une troisieme** (`DEC-0086`). `p3-8` et `p3-9` ont leur story et leur instance ; il leur manque l'agent. Issue retenue en attendant : **non engage, budget non libere** : le 1,0 de chacun reste dans son instance, alloue a aucun autre poste, aucun lot n'y entre sans amendement date de `planning.md`. Chacun redevient engage par cet amendement, des que `AD1` est repondue (precedent : postes 7 a 9 du palier 1, `planning.md`, section "Postes du palier 1"). Si `AD1` est repondue avant la fusion de l'amendement d'ouverture, les deux postes s'ecrivent engages a l'ouverture.

**Hors composition, non engages, aucun developpeur designe** (aucun n'est dans la composition de `camille-po`, section 8.2 ; en designer un maintenant chargerait une chaine sans raison) :

| Story | Titres | Prealable ou motif | Ou est la decision |
|---|---|---|---|
| `S57-GARDE-PILE-DE-POLICES` | 1 | `A.115` rang 1, derriere `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` ; `DEC-0122` ; son agent est *"a designer par `sofia-sm`"* | `AD6` |
| `S57-GARDE-NOMBRE-DE-MANCHES` | 1 | `A.115` rang 4 ; `theo-archi` a trouve que sa liste fermee porte 7 entrees pour 8 dans le catalogue (`avis-theo-preparation-palier-3-ce1.md` section 2, `K4`, question `Q1` a `camille-po`) | `camille-po` (`Q1`) |
| `S57-TYP1-TEXTES-DE-GABARIT` | 2 | `A.115` rang 5 ; `Q-T12` de `theo-archi` | `camille-po` (rang) |
| `S57-MESSAGE-PARTIE-PERDUE` | 1 | `A.115` rang 5 ; mesure du nombre de lignes a 744 et 1200 | `camille-po` (rang) |
| garde generique de `LON-CE1-3` a `-5` et `TRV-CE1-1` (forme `F1`) | 0 | `decision-theo-garde-ce1-e3.md` section 5, criteres `CA1` a `CA8` ; `camille-po` a choisi *"les cinq lots avant le garde"* ; **la story n'est pas ecrite** et `CA4` demande l'avis de `nadia-prof` sur les matieres | `camille-po` (story et moment) |
| commentaires d'en-tete de `ce1-pronoms`, `ce1-texte-narratif`, `ce1-familles-de-mots` (`R6` du releve) | 0 | `cloture-palier-2.md` section 9 : fichiers de `src/main`, *"a placer a la composition du palier 3"* ; **je ne les place pas** : ils ne sont pas dans la composition qui m'est donnee | `AD7` |

**Verdict `PAR-16`** : p3-3 et p3-5 a p3-7 portent une story et une affectation nominative et sont engages ; p3-1 porte une story et un agent, sa part restante est la lecture ; p3-4 est engage sans story, dit ; p3-8 et p3-9 portent une story, une instance et un prix, et attendent un agent ; p3-2 n'est pas un poste de travail.

## 4. Affectation nominative des developpeurs

### 4.1 Les regles ecrites que j'applique

| Code | Regle | Source |
|---|---|---|
| RG1 | `lea-dev` ne recoit aucun lot de francais CE1 : 529 tours a s56 contre 183 (`marc-dev`) et 192 (`sofia-sm`). Mesure non refaite : le rapport de tokens est un seul pour le sprint, a la review | `planning.md`, section "Charge des developpeurs" ; reprise par `composition-palier-2.md` section 3 |
| RG2 | Des developpeurs distincts sur des instances distinctes, parce que les lots d'un meme palier partagent `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` | `planning.md`, amendement du 2026-10-15 20:56, section 5 |
| RG3 | `marc-dev` pour un lot a texte support : il a branche `ce1-texte-narratif`, ecrit `PaquetCe1TexteNarratifTest` et porte la regle de s47 de `LON-CE1-3` et `-4` | meme amendement, section 5, motif de `-05` ; `-06` et `-07` ont chacun 20 `texteSupport` (`decision-theo-garde-ce1-e3.md` section 2) |
| RG4 | Un developpeur n'entrelace pas ses worktrees : un a la fois, et il me le dit | meme amendement, section 5 |
| RG5 | Ma casquette developpeuse : declaree au debut de la tache, aucun arbitrage de mon propre obstacle (je le note, je change de casquette, je le route), commit `[SOF]`. Fusion de ma propre branche : lignee par `outils/verifier-lignee.sh`, liste de fichiers contre `K2`, relue par `yanis-qa` ; je n'ai pas d'autre juge, et je le dis | `planning.md`, section "Charge des developpeurs" |
| RG6 | L'agent de la story est celui que la story nomme | backlog, `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` : *"Agent : `theo-archi` ... `yanis-qa` pour le test des criteres"* ; precedent : les commits de la garde de `C1'` au palier 2 sont de `theo-archi` (`planning.md`, amendement du 2026-10-16 13:53, section 1) |

### 4.2 Ce que les regles donnent

| Developpeur | Postes, dans cet ordre | Deduit de | Etat |
|---|---|---|---|
| `theo-archi` | p3-3 (`Z1`, `Z2`) | RG6 | **deduit** |
| `sofia-sm` (casquette developpeuse) | p3-4 (`RL5` a `RL7`) | `planning.md`, amendement du 2026-10-16 21:46, section 9 : *"un developpeur que j'engage a la composition du palier 3, casquette developpeuse, tache distincte"* ; RG5 | **deduit, sous `AD2`** : au palier 2 la meme tache n'a pas ete confiee sous cette casquette (`planning.md`, 21:46, section 9) |
| `marc-dev` | p3-5 (`-06`), p3-6 (`-07`), p3-7 (`-11`), dans l'ordre d'audit de `nadia-prof` | RG1 exclut `lea-dev` ; RG3 ; RG4 ; `-11` : `ce1-negation` est de `marc-dev` (`34fdd167`, `[MARC]`), proposition de la story | **deduit** ; derogation a la proposition des stories de `-06` et `-07` (`AD4`) |
| `lea-dev` ou `sofia-sm` | p3-8 ou p3-9 | RG2 : trois instances, trois developpeurs distincts ; `marc-dev` est sur A | **a decider** (`AD1`) |

**Pourquoi `AD1` n'est pas deduite.** Les regles reduisent les deux lots a un couple, `lea-dev` sur l'un et `sofia-sm` sur l'autre, et ne disent pas lequel. Les faits, sans choix :

| Fait | `lea-dev` | `sofia-sm` |
|---|---|---|
| Postes engages au palier 3 | aucun | p3-4, plus les fusions, la cloture et la review |
| Instance au palier precedent | B (`RDO-CP-R3`), A' (forme commune) | A' (`-24`) |
| Instance au palier 1 | B | A' |
| Branches que `sofia-sm` fusionnerait elle-meme | 0 | 2 (p3-4 et le lot), relues par `yanis-qa` seul |
| Condition | aucune | le lanceur confie la casquette developpeuse (`AD2`) |

Aucun des deux n'est l'auteur d'un paquet de ces lots : `ce1-nombres-jusqu-a-1000` est de `marc-dev` (`8de2927f`), `ce1-emc-biens-communs` n'existe pas. La seule mesure de charge du depot (les tours de s56) n'est pas refaite pour le palier 3 : je ne tranche pas sur un chiffre que je n'ai pas.

## 5. Ordre et portes

**Statut de l'ordre.** Il est **propose** par `camille-po` (`cloture-palier-2.md` section 14, `DEC-0120` A.115 : *"Rien n'est decide ici"*, *"sans engagement"*), sa lecture architecturale est **confirmee** par `theo-archi` (`reponses-theo-questions-stories-palier-3.md`, ligne `ORD`), et **aucune `ADR` ni aucune `DEC` ne le decide**. L'engagement est a moi (`A.115` : *"l'engagement est a `sofia-sm` au planning du palier 3"*) : j'engage cet ordre comme ordre de planning, pas comme decision de produit ou d'architecture.

| Rang | Quoi | Agent | Porte |
|---|---|---|---|
| 1 | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (`Z1`, `Z2`) | `theo-archi`, `yanis-qa` | prealable de tout poste qui modifie `recreo.css` : **aucun lot ne le touche** (`K2` des stories ; `theo-archi`, ligne `ORD` : *"ne lie les lots que par le rang"*). Ce n'est donc pas une porte des lots, **sauf** si un lot demande une regle de feuille (le bandeau de `PA7`, `avis-theo-preparation-palier-3-ce1.md` section 2, `K2`) : il attend alors la fusion de la garde |
| 2 | commit de `RL5`, `RL6`, `RL7` | `sofia-sm` (dev) | **porte des lots** : avant le premier jar rejoue du palier 3 et avant tout commit de lot (`theo-archi` : *"un lot commite un `Paquet<Nom>Test.java`, donc du code"*). **Les branches des cinq lots sont coupees sur le sommet de `round-two` apres sa fusion**, jamais avant : les agents ne rebasent pas |
| 3 | `-06`, puis `-07`, puis `-11` (A) ; lot de B ; lot d'A' | `marc-dev` ; `AD1` | la lecture complete de `nadia-prof` precede le verdict de chaque lot, pas son commit (`S57-ARB-PALIER-3`) |

**Ordre de fusion** : le premier pret est fusionne le premier, un a la fois, `bash outils/mvn-jdk21.sh -B clean verify` entier a la racine apres chacun, regle du palier 1 reprise. Le rang est l'ordre de demarrage. La fusion de la garde et celle de `RL5` a `RL7` sont dans leur ordre de livraison : seule la seconde est une porte. `AD5` pose la phrase de `A.115` qui dit autrement.

**Fichiers partages** : `catalogue.yaml` (une entree en fin de fichier par lot ; compte avant les fusions : 152 entrees, `grep -c "^  - id:"`, lu le 2026-10-19 ; **157 apres les cinq lots**), l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, `REGISTRE-identifiants.md`. Resolution : celle des paliers 1 et 2, je resous, j'ai les ajouts des deux cotes, jamais une assertion affaiblie. **Une ligne par fusion dans `cloture-palier-3.md`** : SHA, compte d'avant, entrees fusionnees, compte d'apres ; `grep -c "paquet: <nom>$"` vaut 0 aujourd'hui pour les cinq noms (`ce1-inference`, `ce1-texte-documentaire`, `ce1-negation`, `ce1-nombres-jusqu-a-1000`, `ce1-emc-biens-communs`) et 1 apres chaque fusion. **Le paquet d'EMC est un fichier neuf** : il n'est pas encore au depot, son `grep -c` vaut 0 des deux cotes jusqu'au lot.

## 6. Table d'isolation du palier 3 (condition (d) de `D2`)

`I1` a `I3` du palier 1 s'appliquent **sans changement** (`planning.md`, section "Isolation") : un repertoire de scratchpad par agent, par instance et par palier, a un seul niveau sous la racine, nomme `s57-p3-<agent>-<instance>`, jamais reutilise d'un palier a l'autre ; un port de jeu par agent, par instance et par palier, distinct de tout autre de la table ; avant chaque lancement le port est libre (`netstat -ano` filtre sur `":<port> "`), apres, la ligne *"Tomcat started on port <port>"* et le PID sont lus dans **son** journal, arret par PID, jamais par nom d'image. Port de debogage de Chrome : port de jeu plus 700. **Remplace** la table du palier 2 pour le palier 3.

Etat de l'ecoute a l'ecriture, lu le 2026-10-19 : `netstat -ano` filtre sur les ports 185xx et 192xx rend 0 ligne.

**Les ports sont ceux du palier 2, par ligne et non par agent** : aucun numero neuf n'est invente. Les deux lignes de lot (B et A') portent le numero de leur instance au palier 2 (18531 pour B, 18532 pour A') et le nom de repertoire est celui de l'agent que `AD1` nommera.

| Agent | Instance | Postes | Repertoire de scratchpad | Port de jeu | Port de debogage |
|---|---|---|---|---|---|
| `nadia-prof` | A | audits de p3-5, p3-6, p3-7 | `s57-p3-nadia-prof-a` | 18511 | 19211 |
| `nadia-prof` | A' | audit de p3-9 | `s57-p3-nadia-prof-ap` | 18512 | 19212 |
| `nadia-prof` | B | audit de p3-8 | `s57-p3-nadia-prof-b` | 18513 | 19213 |
| `marc-dev` | A | p3-5, puis p3-6, puis p3-7 | `s57-p3-marc-dev-a` | 18521 | 19221 |
| `sofia-sm` (developpeuse) | transverse | p3-4 | `s57-p3-sofia-dev-t` | 18523 | 19223 |
| a decider (`AD1`) | B | p3-8 | `s57-p3-lea-dev-b` ou `s57-p3-sofia-dev-b` | 18531 | 19231 |
| a decider (`AD1`) | A' | p3-9 | `s57-p3-lea-dev-ap` ou `s57-p3-sofia-dev-ap` | 18532 | 19232 |
| `theo-archi` | transverse | p3-3, avis, confirmation | `s57-p3-theo-archi-t` | 18542 | 19242 |
| `yanis-qa` | transverse | p3-3 (mutations), rapport qualite | `s57-p3-yanis-qa-t` | 18541 | 19241 |
| `camille-po`, `relecteur-pedagogique`, `sofia-sm` (Scrum Master) | transverse | aucun jar | `s57-p3-camille-po-t`, `s57-p3-relecteur-t`, `s57-p3-sofia-sm-t` | aucun | aucun |

`ines-design` n'a aucune ligne : aucun poste du palier 3 ne lui revient. Un lancement qu'elle recevrait recoit une ligne ajoutee par amendement date avant son lancement. **Un agent transverse n'a qu'un lancement vivant a la fois** ; un second lancement concurrent recoit un repertoire `-t2` et un port de la table avant son lancement, par ligne ajoutee a `planning.md`. Si `sofia-sm` prend aussi un lot (`AD1`), elle a **deux lignes** (`-t` et celle de l'instance du lot) et ne les entrelace pas : un worktree a la fois.

**Collisions evitees** (mesurees par `git grep` et par les paliers precedents) :

- **18522 n'est donne a personne** : avec la regle *"jeu plus 700"* il donne 19222, que `pilote-q7.mjs` fixe en dur (ligne 6). Ce script reste a un seul agent a la fois, sa ligne s'ecrit dans `planning.md` avant l'usage.
- **19333 et 19334** (`pilote-accueil.mjs`, `capture-colle.mjs`) : fixes en dur, donnes a personne, un seul agent a la fois. `outils/mesurer-accueil.py` ouvre Chrome sur un port aleatoire : rien a allouer, a lancer seul.
- **Un meme repertoire ou un meme port pour deux instances** : `marc-dev` n'a qu'une instance au palier 3, une seule ligne. Aucun agent n'a deux lignes sauf `sofia-sm` si `AD1` la retient.
- **Noms hors format** : le nom se copie de la table, lettre pour lettre, et le repertoire se cree avant la premiere commande.

**Consigne ecrite, a repeter en tete du message de lancement de chaque agent du palier 3.** Ce n'est ni un moyen de garde ni le crochet : le crochet est `Q4` de `theo-archi`, sans reponse de l'utilisatrice dans le depot (`cloture-palier-2.md` section 13 (a)).

> 1. Ton repertoire de scratchpad est celui de la table du palier 3 de `composition-palier-3.md`, copie lettre pour lettre. Ta premiere commande le cree (`mkdir -p`). Tu n'en utilises aucun autre.
> 2. **Tout ce que tu ecris, tu l'ecris dans ce repertoire, quel que soit l'outil** : l'outil d'ecriture de fichier, chaque redirection du shell (`>`, `>>`, `2>`, `&>`, `tee`), chaque journal de build ou de jar, chaque profil de Chrome, chaque capture, chaque fichier de PID, chaque sortie d'un script Python ou Node.
> 3. **Chemin absolu, toujours, construit a partir de ton repertoire.** Le repertoire courant est reinitialise entre deux commandes.
> 4. Trois endroits sont interdits pour tes fichiers : la racine du scratchpad, le repertoire parent des worktrees, le repertoire temporaire du systeme.
> 5. Tu ne deplaces, ne renommes et ne supprimes aucun fichier que tu n'as pas cree.
> 6. Avant de rendre la main : tu listes la racine du scratchpad et le repertoire parent des worktrees ; tout fichier que tu y as ecrit est nomme dans ton rapport, puis deplace dans ton repertoire. Ton rapport porte une ligne *"racine : propre"* ou la liste des noms. Tu ne declares jamais propre une racine que tu n'as pas listee.

Au palier 2, 16 repertoires et 0 fichier a la racine de ma session (`cloture-palier-2.md` section 10) ; la lecture des transcriptions par `nadia-prof` pour le palier 2 n'est dans aucun fichier du depot. **Je mesure** a la cloture du palier 3 les racines que ma session peut lire ; je ne me substitue pas a elle.

**Branches et worktrees.** Une branche et un worktree dedies par poste, voisins du checkout (`recreo-<branche>`), sans rebase, sans push, sans fusion par les agents ; chaque agent note `git log -1 --format=%h` dans son premier message ; **je verifie la lignee avant chaque fusion** (`bash outils/verifier-lignee.sh`), meme quand l'agent dit l'avoir faite. Aucune de ces branches n'existe (`git branch --list`, 0 pour chaque nom, lu le 2026-10-19) et `git worktree list` rend deux lignes, le checkout et celui de `main`.

| Poste | Branche | Worktree | S'ouvre |
|---|---|---|---|
| p3-3 | `s57-theo-garde-c1-virgule-imbrique` | `../recreo-s57-theo-garde-c1-virgule-imbrique` | des la fusion de l'amendement |
| p3-4 | `s57-sofia-rl5-rl7` | `../recreo-s57-sofia-rl5-rl7` | des la fusion de l'amendement, sous `AD2` |
| p3-5 | `s57-marc-fra-ce1-06` | `../recreo-s57-marc-fra-ce1-06` | apres la fusion de p3-4 |
| p3-6 | `s57-marc-fra-ce1-07` | `../recreo-s57-marc-fra-ce1-07` | apres la fusion de p3-4, un worktree de `marc-dev` a la fois |
| p3-7 | `s57-marc-fra-ce1-11` | `../recreo-s57-marc-fra-ce1-11` | idem |
| p3-8 | `s57-<lea ou sofia>-mat-ce1-01` | `../recreo-s57-<lea ou sofia>-mat-ce1-01` | apres p3-4 et `AD1` |
| p3-9 | `s57-<lea ou sofia>-emc-ce1-01` | `../recreo-s57-<lea ou sofia>-emc-ce1-01` | apres p3-4 et `AD1` ; `git merge-base --is-ancestor 729b0d8a HEAD` dans le worktree sort 0 (sort 0 sur `ee9ecd44`) |
| audits de `nadia-prof` | `s57-nadia-p3-a`, `s57-nadia-p3-ap`, `s57-nadia-p3-b` | `../recreo-s57-nadia-p3-a`, `-ap`, `-b` | au fil des livraisons |
| cloture de palier | `s57-sofia-cloture-p3` | `../recreo-s57-sofia-cloture-p3` | a la cloture |

`s57-sofia-planning3` existe deja (palier 2) : la branche qui portera l'amendement d'ouverture prend un autre nom.

## 7. Compte des reports

Regles de `DEC-0120` A.66 a A.73, appliquees comme aux paliers 1 et 2 : un report est un poste retenu par la composition et non engage a l'ouverture ; un poste glisse au plus une fois ; le troisieme report est interdit.

| Poste | Etat a l'ouverture | Compte |
|---|---|---|
| `-06`, `-07`, `-11` | engages | deuxieme ecartement solde par leur entree (`DEC-0120` A.115). **Pas de report** ; il n'y a pas de troisieme |
| `S57-BR-MAT-CE1-01`, `S57-BR-EMC-CE1-01` | engages si `AD1` est repondue avant la fusion de l'amendement, sinon non engages | premiere inscription dans une composition. **Premier report chacun si non engages a l'ouverture** (A.66) ; l'engagement tardif ne l'efface pas, il evite le suivant |
| `RL5`, `RL6`, `RL7` | engages sous `AD2` | premier glissement ecrit (`planning.md`, 21:46, section 9). **Non livre a la cloture du palier 3 : second glissement, report compte**, ecrit par moi le jour de la cloture |
| `Z1`, `Z2` | engages | premiere inscription au palier 3 (`DEC-0120` A.115, rang 1) |

## 8. A decider

Je classe, je ne reponds a aucune. Un obstacle n'est clos que quand le fichier qui le tranche existe.

| Code | Question | Qui decide | Ce que le planning engage en attendant |
|---|---|---|---|
| `AD1` | Developpeurs de p3-8 (`MAT-CE1-01`, B) et de p3-9 (`EMC-CE1-01`, A') : `lea-dev` sur l'un et `sofia-sm` sur l'autre, dans quel sens ? | utilisatrice (capacite ; mon choix de capacite, inversable par elle, que je ne prends pas sans mesure de charge) | p3-8 et p3-9 non engages, 1,0 gele dans chaque instance ; premiers reports s'ils le restent a la fusion |
| `AD2` | La tache de `RL5` a `RL7` est-elle confiee a `sofia-sm` sous la casquette developpeuse ? Faute de quoi, qui ? | utilisatrice, par le lanceur | p3-4 engage a `sofia-sm` ; sans reponse, personne ne le fait (le palier 2 l'a montre) |
| `AD3` | Une story pour p3-4 : `PAR-16` en demande une pour tout poste engage ; hors `PAR` et sans prix | `camille-po` | p3-4 engage sans story, ecart dit ici ; `PAR-16` n'est pas assoupli |
| `AD4` | Les stories de `-06` et `-07` proposent `lea-dev` (auteur des paquets). `RG1` l'exclut. L'utilisatrice veut-elle deroger a `RG1` ? | utilisatrice | `marc-dev` sur les trois lots de A |
| `AD5` | `A.115` rang 2 : *"avant tout autre commit de code"*. Le commit de `Z1` et `Z2` (un test de `adapter-web` et un amendement d'`ADR-0031`, aucun octet servi : critere 9 de sa story) est-il un *"autre commit de code"* ? Si oui, `A.115` rang 1 et rang 2 se contredisent. Ma lecture, non decidee : la condition de `RL5` est celle du jar (`theo-archi`), et `Z1`/`Z2` ne change aucun octet servi | `camille-po` (`A.115` est la sienne), avis de `theo-archi` | rang = ordre de demarrage, fusion premier pret premier fusionne, seule `RL5` a `RL7` est une porte des lots |
| `AD6` | `S57-GARDE-PILE-DE-POLICES` est au rang 1 de `A.115`, derriere `Z1`/`Z2`. Entre-t-elle au palier 3 ? Elle n'est pas dans la composition qui m'est donnee | `camille-po` (rang), puis moi (engagement) | non engagee |
| `AD7` | Les trois commentaires d'en-tete de paquet (`R6` du releve) : au palier 3 ou plus tard, et par qui ? | `camille-po` (moment), puis moi (developpeur) | non engages |

## 9. Contradictions trouvees

| Code | Constat | Ce qui est fait ici |
|---|---|---|
| CT1 | Les stories `S57-BR-FRA-CE1-06` et `-07` proposent `lea-dev` ; `planning.md` interdit tout lot de francais CE1 a `lea-dev` (`RG1`), regle reprise par `camille-po` au palier 2. La proposition ne cite pas la regle | `marc-dev`, `AD4` |
| CT2 | `A.115` place `Z1`/`Z2` au rang 1 et `RL5` a `RL7` au rang 2 avec *"avant tout autre commit de code"* | `AD5` |
| CT3 | `D2` (d) dit *"l'amendement porte la table d'isolation"* ; la table est ici, a la demande du lanceur, et l'amendement la designe | une seule copie, pour qu'elle ne puisse pas deriver en deux endroits ; je le dis dans l'amendement |

## 10. Ce que ce fichier ne fait pas

- N'ouvre aucun worktree, ne cree aucune branche, n'ecrit aucun code, ne lance aucun agent, ne fusionne rien, ne commite rien.
- Ne modifie ni un prix, ni un lot, ni une instance, ni un critere de story, ni la matrice (148 / 200).
- Ne prononce aucun Done, ne juge aucun contenu. Ne decide ni le produit, ni l'architecture, ni la pedagogie.
- Ne cree ni n'amende aucune `ADR` ni aucune `DEC`. L'amendement de `DEC-0120` qui porte la composition est a `camille-po`, apres l'amendement de `planning.md` qui nomme les proprietaires, comme au palier 2.
- N'ecrit aucun identifiant de condition ou de reserve : `CO1` a `CO8`, `RG1` a `RG6`, `AD1` a `AD7` et `CT1` a `CT3` sont des reperes de ce fichier.

## 11. Jobs de `ci.yml` lances pour ce fichier

Voir l'amendement de `planning.md` du 2026-10-19, section 10 : les memes commandes couvrent les deux fichiers ; aucun commit n'est fait, donc `prefixes` n'est pas lance.

## 12. Amendement du 2026-10-19 23:44 (UTC+02:00) : AD1, AD2 et AD4 decidees, lots de B et d'A' engages

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; les lignes ci-dessous remplacent, pour la lecture, celles qu'elles nomment. Auteure : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` a `6b6d271a`. Source : l'utilisatrice a repondu *"ok"* le 2026-10-19 a ma recommandation sur `AD1`, `AD2` et `AD4` ; la reponse m'est relayee par l'agent lanceur et **je ne l'ai pas lue de sa main**. C'est un choix de capacite, ni une `ADR` ni une `DEC`. L'amendement de `planning.md` du meme jour porte le detail ; ce fichier porte ce qui est a lui.

### 12.1 Ce qui est decide

| Code | Inscrit comme decide |
|---|---|
| `AD1` | `lea-dev` prend `MAT-CE1-01` (B) et `EMC-CE1-01` (A') |
| `AD2` | `RL5` a `RL7` est confiee a `sofia-sm`, casquette developpeuse |
| `AD4` | pas de derogation a RG1 : `marc-dev` garde `-06`, `-07`, `-11` ; `lea-dev` n'a aucun lot de francais CE1 |

### 12.2 Remplace les lignes `p3-4`, `p3-8`, `p3-9` de la section 3, la ligne `lea-dev ou sofia-sm` de la section 4.2 et la contradiction CT1 de la section 9

| # | Poste | Agent nominatif | Etat |
|---|---|---|---|
| p3-4 | `RL5`, `RL6`, `RL7` | `sofia-sm`, casquette developpeuse | **engage sans story**, ecart dit (`AD3`, a `camille-po`) |
| p3-8 | `ce1-nombres-jusqu-a-1000`, B | `lea-dev` ; audit `nadia-prof` (B) | **engage** |
| p3-9 | `ce1-emc-biens-communs`, A' | `lea-dev` ; audit `nadia-prof` (A') | **engage** |
| CT1 | stories de `-06` et `-07` contre RG1 | `marc-dev` | **tranchee** par `AD4` ; les stories, de `camille-po`, ne sont pas modifiees ici |

Total recompte par `awk` le 2026-10-19 : A 4,00 ; Ap 2,00 ; B 2,00 ; total 8,00 ; `engage` 6,50 ; `marge` 1,50 ; `a-decider` 0.

### 12.3 Ecart a RG2, dit

RG2 demandait des developpeurs distincts sur des instances distinctes. `AD1` met B et A' chez `lea-dev`. L'ecart est applique, pas arbitre : `lea-dev` n'a qu'un worktree vivant a la fois (RG4), conduit `p3-8` puis `p3-9` et me dit quand il passe de l'un a l'autre ; la resolution de `catalogue.yaml` et de l'inventaire reste la mienne. `sofia-sm` n'a plus aucun lot : les deux lignes que la section 6 lui reservait si `AD1` la retenait tombent.

### 12.4 Remplace les lignes `a decider (AD1)` de la table d'isolation de la section 6

| Agent | Instance | Postes | Repertoire de scratchpad | Port de jeu | Port de debogage |
|---|---|---|---|---|---|
| `lea-dev` | B | p3-8 | `s57-p3-lea-dev-b` | 18531 | 19231 |
| `lea-dev` | A' | p3-9 | `s57-p3-lea-dev-ap` | 18532 | 19232 |

Aucun numero neuf. `lea-dev` a deux lignes et ne les entrelace pas. Branches et worktrees des lignes `p3-8` et `p3-9` de la section 6 : `s57-lea-mat-ce1-01` et `s57-lea-emc-ce1-01`, worktrees `../recreo-s57-lea-mat-ce1-01` et `../recreo-s57-lea-emc-ce1-01`, ouverts apres la fusion de `p3-4`.

### 12.5 Remplace la ligne `MAT-CE1-01`, `EMC-CE1-01` de la section 7

`p3-8` et `p3-9` sont engages apres la fusion de l'amendement de 23:09 (`6b6d271a`) : **premier report chacun** (`DEC-0120` A.66), l'engagement tardif evite le second et n'efface pas le premier. Compte a l'ouverture : 2 premiers reports. L'amendement de `DEC-0120` est a `camille-po`.

### 12.6 Section 8 : etat de chaque code

| Code | Etat |
|---|---|
| `AD1`, `AD2`, `AD4` | decidees, sur reponse relayee (12.1) |
| `AD3`, `AD5`, `AD6`, `AD7` | a decider, chez `camille-po` ; non touchees |

### 12.7 Ce que cette section ne fait pas

N'ouvre aucun worktree de lot, ne cree aucune branche, ne modifie aucun prix, lot, instance ni critere de story, ne touche ni `backlog.md` ni `preparation-palier-3-ce1-camille.md`. Ne cree ni n'amende aucune `ADR` ni aucune `DEC`. Ne prononce aucun Done.

## 13. Amendement du 2026-10-21 17:45 (UTC+02:00), cloture du palier 3 : ecarts 5 et 7 du releve du `relecteur-pedagogique`

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit.** Source : `releve-relecteur-cloture-palier-3.md` (`01105a16`), ecarts 5 et 7, a `sofia-sm`. Arbre : `round-two` a `d09655bf`. Casquette Scrum Master.

### 13.1 Ecart 5 : deux citations rangees sous une seule source

La section 5 ecrit que l'ordre est *propose* par `camille-po` (`cloture-palier-2.md` section 14, `DEC-0120` A.115 : *"Rien n'est decide ici"*, *"sans engagement"*). Les deux citations n'ont pas la meme source :

| Citation | Source | Mesure ce jour |
|---|---|---|
| *"Rien n'est decide ici"* | `cloture-palier-2.md`, section 14, ligne 278 : c'est ma phrase | `grep -n "Rien n'est decide ici"` rend la ligne 278 de `cloture-palier-2.md` et aucune ligne de `DEC-0120` |
| *"sans engagement"* | titre de `DEC-0120` A.115 : *"Proposition pour le palier 3 : ordre des restes, sans engagement"* | `grep -n "^### A.115"` sur `DEC-0120` rend ce titre |

**Lire** : *"Rien n'est decide ici"* de `sofia-sm`, `cloture-palier-2.md` section 14 ; *"sans engagement"* de `camille-po`, `DEC-0120` A.115 (titre). La meme correction vaut pour `planning.md`, amendement du 2026-10-19 23:09, section 3, annotee dans `planning.md` ce jour.

### 13.2 Ecart 7 : le renvoi a `cloture-palier-3.md`

La section 5 renvoie a `cloture-palier-3.md` pour *"une ligne par fusion : SHA, compte d'avant, entrees fusionnees, compte d'apres"*. Le fichier n'existait pas a la date du releve. **Il existe** : `cloture-palier-3.md`, section 3, porte les 21 fusions du palier et, pour chacune, le compte de `catalogue.yaml` avant et apres, plus le `grep -c "paquet: <nom>$"` des cinq paquets. Aucune ligne de cette composition n'est modifiee.

### 13.3 Decisions prises

**Aucune `ADR` ni `DEC` creee ou modifiee.** Cet amendement ne modifie ni prix, ni lot, ni instance, ni table d'isolation. Les verifications de son commit sont nommees dans `cloture-palier-3.md`, section 12.
