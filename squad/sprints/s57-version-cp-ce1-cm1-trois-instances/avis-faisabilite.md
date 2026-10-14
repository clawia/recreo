# [Sprint s57] Avis de faisabilite - theo-archi

- **Date** : 2026-10-14 14:39 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `s57-theo-avis`, worktree dedie, base `544159d9` (tip de `round-two`). `git status --short` vide avant ecriture.
- **Perimetre** : les postes de s57 que nomment `DEC-0120` (section 3, 4.2, 4.4, 4.5, A.2, A.3, A.33, A.34, A.44), `preparation.md` (5.4) et `duree-et-paliers.md` (P3'),
  et les conditions 2, 5 et 6 de l'accord de `nadia-prof` sur `PAR-2` (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 4.2 et 11.3 condition 4). `round-two` a avance
  pendant l'ecriture (`a69baf0c`, composition du palier 1 de `camille-po`) : je l'ai lue par `git show 20860e75` et j'en mesure les lots en section 4, sans fusionner cette branche.
  Ce commit n'ajoute que ce fichier : aucun `ADR`, aucun Java, aucun YAML, aucune CSS.
- **Ce que cet avis ne fait pas** : il ne choisit ni la forme visuelle de la correction d'`ART-CP-10` (`ines-design` propose, `nadia-prof` juge), ni les mots lus par l'enfant
  (`nadia-prof`), ni l'identifiant d'une entree de catalogue (`camille-po`), ni la table des ports et des repertoires (`sofia-sm`), ni la composition d'un palier.
- **JDK des mesures** : voir section 6 (propriete `java.version` des rapports Surefire, `ADR-0071`). Enveloppe `bash outils/mvn-jdk21.sh`.
- **Methode des mesures sur un autre arbre** : un worktree jetable en `--detach` sur `544159d9`, voisin du checkout, supprime apres mesure. Aucune mutation n'a touche ce worktree.

## 1. Verdict par poste

| Poste | Verdict | Condition ou motif | Section |
|---|---|---|---|
| `S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1` (correction) | **faisable sous condition** | C1 a C4 : regle sans condition d'etat, portee par `.ecran-deux-oeuvres` ; pas de `@media` sans extension du garde-fou du plafond ; jamais `.cabinet` masque ; seule une mesure au jar juge les cotes | 2 |
| `S56-RDO-CP-R2` (ecran de fin) | **faisable sous condition** | trois voies techniques ; deux demandent un amendement d'`ADR-0104` de ma main dans le commit du code ; le choix est a `nadia-prof` | 2.4 |
| Rejouer un paquet sans entree de catalogue (`DEC-0120` 4.2) | **faisable en l'etat** | une branche d'audit qui porte l'entree, fusionnee apres le verdict ; conditions R1 a R3 | 3 |
| Francais CE1 a deux instances (`DEC-0120` section 3), lots proposes au palier 1 (`FRA-CE1-04`, `-10`, `-15`) | **faisable en l'etat** | prerequis techniques tenus et mesures ; un risque de fusion nomme, sans garde-fou neuf | 4 |
| Isolation des scratchpads et des ports | **faisable sous condition** | I1 a I3 au planning ; aucune partie n'est encodable dans le depot, et je le dis | 5 |
| `S54-OBS-SCI-CONSIGNE` | **faisable en l'etat** | simulation : 0 echec | 6 |
| `S56-VSJ-CE1-R1` | **faisable sous condition** | l'amendement de la propriete 8 par `nadia-prof` precede le commit | 7 |
| `S57-ADR-0065-DEUX-INSTANCES` | **livree** | fusionnee `729b0d8a`, ancetre de `round-two` | 8 |
| `HS-ACCUEIL-APM-R2` (hors sprint, moyen route a moi par `DEC-0120` 4.5) | **faisable moyennant un amendement d'`ADR-0106`** | decision 8 dit le contraire du critere | 9 |
| Regle ArchUnit neuve | **aucune** | aucun poste ne porte une decision sur un graphe de types | 10 |

**Non faisable : rien.**

## 2. Correction d'`ART-CP-10` (`S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`)

### 2.1 Ce qui doit bouger, en pixels, et sa source

Cotes du releve `Q7` d'`ines-design` (`squad/conformite/releves-s56/Q7-releve-gabarit-livre.md`, sections 3 et 4), identiques a celles de `nadia-prof` (fiche, section 3), mesurees
au navigateur sur le jar de s56, pli a 551 :

| Critere | Element | Bas mesure | Ecart au pli |
|---|---|---|---|
| Refus, 744 x 551, revelation | `.reponse-revelee` (`M4`) | 656 | **105** |
| `RDO-CP-R1`, 744 x 551, echec | `.indice` | 676 | **125** |
| `RDO-CP-R1`, 992 x 551, echec | croix sur la case | 568 | **17** |

**Le refus seul demande 105 px ; lever `RDO-CP-R1` dans le meme rejeu en demande 125 a 744.** Le backlog place les deux dans le meme rejeu (`S56-RDO-CP-R1`, *"Rejeu : dans celui de
`S56-ART-CP-10-REPRISE`"*) : la cible de la forme est donc 125, pas 105, si `R1` doit etre levee au meme passage. Ce choix est a `nadia-prof`.

**Arithmetique sur ces cotes, pas une mesure** (lecon de s56 : une cote d'un vehicule ou d'un calcul n'est pas une cote du jar) : a 744, le meuble va de 286 a 558 et les cases
commencent a 338 ; il y a 52 px entre le haut du meuble et le haut des cases, et 34 px entre le bas des croix (524) et le bas du meuble (558). **Retirer l'habillage du meuble seul
donne au plus 86 px**, moins que 105 : une forme qui ne joue que sur l'habillage ne suffit pas sur ces cotes, une seconde source est necessaire (taille de case, espacements du
bloc de message). Je ne choisis pas laquelle.

### 2.2 Ou la correction peut vivre sans toucher un autre jeu

- `fragments/manche.html` ligne 34 pose la classe `ecran-deux-oeuvres` sur la racine de la manche **seulement** quand `propositionsEnImage` est vrai. Le catalogue n'a aujourd'hui
  aucun jeu a propositions en image : `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` exige une liste exacte vide (lignes 439 a 443, *"Vide depuis le retrait"*). Une
  regle de `recreo.css` dont le selecteur commence par `.ecran-deux-oeuvres` n'atteint donc aucun des 146 jeux servis (`grep -c "^  - id:"` sur `catalogue.yaml` : 146).
- **Ce qui n'est pas disponible en l'etat** : deplacer `.message-jeu` au-dessus du meuble (`manche.html` lignes 183 a 191, conditions `M1` et `M4` de `nadia-prof` : *"sous le meuble,
  [...] jamais au-dessus"*) ; changer l'ordre du gabarit, qui toucherait tous les jeux `bandit-manchot` ; un `th:if` sur le cabinet (`ADR-0030` point 1.c, mordu par
  `CabinetDeLaMachineTest`, lignes 145 et 146 citees a s56). Chacun demanderait un amendement d'une condition de `nadia-prof` ou d'un `ADR`, que personne ne demande.

### 2.3 Conditions

- **C1. Toute regle de la correction s'applique a l'ecran a propositions en image sans condition d'etat.** Elle ne lit ni `.message-jeu`, ni `.signal`, ni `.est-juste` / `.est-faux`,
  ni `data-oeuvre-revelee`. Motif : la condition `C4` de `nadia-prof` (*"le cabinet est rigoureusement identique apres une bonne et apres une mauvaise reponse"*). Un meuble qui se
  compacte a l'echec ou a la revelation fait dire au meuble le resultat. **Aucun automate ne le verrait** : `UnEcranNeGouvernePasLApparenceDUnAutreTest` ne juge que des jetons de
  deux ecrans differents, et sa Javadoc ecrit que le couplage a un element du meme ecran *"n'est pas jugeable ici"*. Je le verifie a la lecture du diff.
- **C2. Aucune regle sous `@media` ne vise l'image d'une proposition**, sauf si le garde-fou du plafond est etendu dans le meme commit. `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest`
  ne lit pas les regles sous `@media` (Javadoc, lignes 42 et 43). Une compaction a 551 de hauteur ecrite par une requete de media serait hors de sa vue. L'extension serait de ma
  main : le besoin me revient.
- **C3. Si l'habillage du meuble est masque, ce sont ses freres decoratifs (`.cabinet-bandeau`, `.cabinet-levier`, `.cabinet-socle`), jamais `.cabinet`**, qui porte le formulaire
  (`manche.html` lignes 151 a 182 ; avis de s56, section 4, `Q5`). Les tests qui lisent ces classes (`AucunTexteNeVitDansUnSousArbreMasqueTest`, `LHabillageResteImmobileTest`,
  `UnEcranNeGouvernePasLApparenceDUnAutreTest`) sont dans le job `build` ; le developpeur les nomme dans son rapport.
- **C4. Les cotes de 2.1 ne se jugent que sur le jar, au navigateur, a 744 x 551 et 992 x 551**, par `getBoundingClientRect()` sur les six items, comme le releve `Q7`
  (`squad/conformite/releves-s56/pilote-q7.mjs` existe et le fait). Aucun automate du depot ne rend une page (`UnEcranNeGouvernePasLApparenceDUnAutreTest`, Javadoc). **Ce n'est pas
  une regle ArchUnit et ce n'est pas un test du job `build`** : le mecanisme qui garde la propriete est le releve, puis le rejeu de `nadia-prof`.

**Ce qui ne bouge pas** : le plafond de 176 px. Une image plus petite passe le garde-fou (`unPlafondPlusBasQueLeNombreDecideNeFaitPasEchouerLeGardeFou`, ligne 253) ; une image plus
grande le fait rougir.

**Prix** : non chiffre. Il depend de la forme, que je ne choisis pas. Precedent seul : le garde-fou du plafond, 0,25 j de `lea-dev` (avis de s56, 2.5).

### 2.4 `RDO-CP-R2` : l'ecran de fin ne designe pas une oeuvre absente

`fragments/retour.html` rend la question rappelee (ligne 96) et l'explication (ligne 104) ; `PartieController.retourModele` (lignes 844 a 863) les pose sur l'ecran de fin du
`bandit-manchot` sans regarder si la reponse est une image (seule `reponseAttendueVisible` en tient compte, ligne 861). Trois voies, chacune faisable :

| Voie | Ce qui change | Decision touchee |
|---|---|---|
| (a) Les textes | les 6 enonces et 6 explications de `cp-rapprocher-deux-oeuvres.yaml` (`grep -c 'celle-ci'` et `grep -c 'ces deux'` : 6 et 6, backlog) | aucune ; mais ces textes servent aussi la manche, ou l'oeuvre est a l'ecran et ou `celle-ci` designe juste |
| (b) Le controleur | `questionRappeleeVisible` faux et explication non posee quand `reponseEnImage` est vrai | amendement d'`ADR-0104` decision 4 point 5, et d'`ADR-0084` (`REV-3`) pour cet ecran ; un test de controleur dans le meme commit |
| (c) L'oeuvre a l'ecran de fin | une image de la bonne oeuvre a l'ecran de fin | prevue par `ADR-0104` decision 4 point 5 : *"une image de plus a cet ecran, une ligne de gabarit et un cran de cliquet"* ; la clause 1 (aucun identifiant en texte ni en `alt`) s'y applique |

Les mots de (a) et ce que l'ecran de fin doit montrer sont a `nadia-prof` ; la forme de (c) a `ines-design`. **Pour (b) et (c), l'amendement d'`ADR-0104` est ecrit par moi dans le
commit du code**, avec le test qui le rend verifiable.

## 3. Rejouer un paquet sans entree de catalogue (`DEC-0120` 4.2)

### 3.1 Les faits qui contraignent

1. **Le retrait est l'absence d'entree** (`ADR-0061` decision 3). Sans entree, le jeu n'est pas servi : `POST /parties/bandit-manchot-rapprocher-deux-oeuvres-cp` rend le fragment
   d'erreur (`rapport-lea-dev.md` de s56, 14.3).
2. **Le retour passe par une entree neuve apres un Done de `nadia-prof` qui n'est pas `REFUSE`** (backlog, `S56-RETRAIT-RAPPROCHER-OEUVRES`, dernier critere ; `DEC-0045` section 3).
   `round-two` ne porte donc pas l'entree avant le verdict.
3. **Son verdict se lit sur le jar qui sert l'entree** (criteres de la version, 1.2, critere 3).

Les points 2 et 3 ne se concilient que si le jar joue est construit **hors de `round-two`**, depuis un commit qui porte l'entree.

### 3.2 Le moyen : deux branches, la seconde fusionnee apres le verdict

| Branche | Contenu | Fusion |
|---|---|---|
| B1, correction | les regles de la forme retenue, sous les conditions de 2.3 ; les fichiers de 2.4 si une voie de code est retenue ; leurs tests | sur `round-two` apres le job `build` vert, avant le rejeu. Elle ne sert rien de neuf : sans entree, seul le jeu de preuve du garde-fou 4 l'exerce |
| B2, audit | issue de `round-two` apres B1 ; **un commit** : l'entree de `catalogue.yaml`, les quatre chaines de l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, et `isEmpty()` remis en `containsExactly(<identifiant>)` dans le garde-fou 4 | **apres le verdict seulement**. Non `REFUSE` : `sofia-sm` fusionne. `REFUSE` : B2 n'est pas fusionnee, rien n'est servi, une story corrective repart de B1 |

Le commit de B2 est l'inverse de `13d00bfe` sur ces trois fichiers et sur eux seuls : `B5` et `B8` lisent desormais le fichier du paquet, pas le catalogue (`rapport-lea-dev.md` de
s56, 14.2 point 3), et n'ont pas a revenir. `nadia-prof`, instance B, ouvre son worktree sur la tete de B2, lance `bash outils/mvn-jdk21.sh -B clean verify` et joue le jar produit.

**Precedent ecrit** : l'audit avant fusion du chantier hors sprint (`squad/README.md`, section 6, amendement du 2026-10-12 ; `DEC-0117` section 11) : *"Rien n'est fusionne sans
l'audit de `nadia-prof` : un `REFUSE` ne laisse alors rien en service."* Dans un sprint, c'est neuf pour ce seul poste : une ligne du planning, a `sofia-sm`.

### 3.3 Conditions

- **R1. Ce qui est fusionne est ce qui a ete joue.** Avant la fusion de B2, `git diff <commit joue> <resultat de la fusion> -- adapters/adapter-web/src/main/resources
  adapters/adapter-content/src/main/resources/contenu/cp-rapprocher-deux-oeuvres.yaml` est vide, ou ce qu'il montre est nomme a `nadia-prof` avant la fusion ; et les six lignes de
  l'entree du paquet dans `catalogue.yaml` sont identiques des deux cotes. `catalogue.yaml` n'est pas compare en entier : les lots de A et de A' y ajoutent leurs entrees pendant
  l'audit. Motif : `round-two` continue d'avancer, et un changement de gabarit ou de feuille entre le jeu et la fusion servirait un ecran qu'elle n'a pas vu.
- **R2. La fiche cite le commit joue**, et `git merge-base --is-ancestor <commit joue> <commit de fusion de B2>` sort 0 a la fusion, cite par `sofia-sm`.
- **R3. L'identifiant de l'entree** (le meme que celui retire, ou un neuf) est a `camille-po`. Techniquement les deux tiennent : l'inventaire et le garde-fou 4 suivent l'identifiant.

### 3.4 Pourquoi pas d'`ADR`, et pourquoi pas de garde-fou

L'objet est un ordre de fusion, pas un graphe de types ni un fichier : aucune `ArchRule` ne le porte. Un test qui refuserait une entree de catalogue dont la fiche dit `REFUSE` devrait
lire une fiche, qui est de la prose a blocs dates ; `nadia-prof` ecrit qu'*"aucun garde-fou ne lit une fiche"* (criteres, section 6). Il serait fragile et ne mordrait que sur un cas
que la fusion de R2 empeche deja. Le mecanisme qui garde la propriete est la commande de R2, executee par `sofia-sm` a la fusion.

**Alternatives ecartees** :

- *L'entree sur `round-two` avant le verdict*, comme a s56 : contraire au fait 2, qui vient d'une decision de l'utilisatrice (`DEC-0116` A.26). Ce n'est pas a moi de la rouvrir.
- *Une route ou un profil qui sert les paquets sans entree* : un second chemin de service, et le jar joue ne serait pas celui qui sert l'entree (fait 3). `ADR-0061` decision 3 nomme
  ce qui le ferait reconsiderer (*"le jour ou un retrait doit etre reversible sans toucher au catalogue"*) : un retrait reversible n'est pas demande ici, un retour l'est.
- *Une entree ajoutee a la main, non commitee, dans le worktree de `nadia-prof`* : aucun commit a citer dans la fiche (`squad/lessons.md`, s56 : un constat cite son commit).

## 4. Francais CE1 a deux instances : prerequis de `DEC-0120` section 3

| # | Prerequis | Etat mesure sur `544159d9` | A qui |
|---|---|---|---|
| 3.1 | Accord de `nadia-prof` sur `PAR-2` | **ecrit**, pour s57 seul, six conditions (criteres, 4.2), valable pour tous les paliers (11.3 condition 4). Sa ligne *"L'avis de `theo-archi` sur `ADR-0065` : engage, pas ecrit"* datait d'avant A2 | `nadia-prof` |
| 3.2 | Amendement d'`ADR-0065` | **fusionne** : `7a34366c` (16:49), `bac0a6d1` (17:40), `729b0d8a` (17:42, 2026-10-13), `git merge-base --is-ancestor <sha> HEAD` sort 0 pour les trois. `grep -c -i instance` sur l'`ADR` : 15 (critere du backlog : au moins 1). Le controle de chemin et de prefixe est dans le job `prefixes` (`ci.yml` lignes 49 et 66) | fait |
| 3.3 | Budget | 8,0 par palier (criteres, 11.3) | `nadia-prof`, `sofia-sm` |
| 3.4 | `A10` | ecrite (criteres, 4.1) | fait |

**Un fait qui borne le risque d'une admission d'`ADR-0065`.** Le garde-fou de l'accent juge les items lus **dans le repertoire de contenu**, pas dans le catalogue :
`chaqueMotServiEnProductionPorteSonAccent` (ligne 270) lit `textesDesItems(CONTENU_DE_PRODUCTION, "")`, puis ajoute les six champs du catalogue (lignes 219 a 235). Les dix paquets
du rang 1 sont sur le disque et deja juges verts sur `round-two`. **Brancher un paquet n'ajoute que les six champs de son entree** ; un besoin d'admission (A2, point 2) ne nait
que d'une modification d'item ou d'un texte de catalogue. A2 point 3 (un amendement a la fois) tient pour un tel cas.

**Fichiers que les lots de A et de A' touchent tous deux** : `catalogue.yaml`, l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, `REGISTRE-identifiants.md`, la matrice. Un
conflit de fusion y est visible. Ce qui le rend sur :

- l'inventaire est juge dans les deux sens (`touteChaineExposeeParLeCatalogueReelEstInventoriee`, ligne 1887 ; `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`,
  ligne 1965) : une resolution qui perd une entree **ou** ses chaines rougit ;
- le compte du registre est verifie par sa table (`ADR-0085` clause 5), ce que la condition 6 de `nadia-prof` demande a la fusion.

**Le cas non garde, nomme** : une resolution qui perd a la fois une entree et ses chaines d'inventaire passe. Le paquet reste sur le disque, et un paquet sans entree est un etat
permis (`ADR-0061`). Verification a la fusion, par `sofia-sm` : `grep -c "^  - id:"` sur `catalogue.yaml` vaut le compte d'avant (146 aujourd'hui) plus le nombre d'entrees fusionnees.
**Pas de garde-fou neuf** : aucune occurrence constatee (s54, trois couloirs : *"aucune ligne perdue"*, criteres 4.1), et une liste attendue de paquets sans entree serait une
exemption a tenir a jour a chaque lot.

**Rappel de s56 qui vaut pour chaque entree** (avis de s56, 6.5) : une consigne qui finit par `?` porte U+00A0 avant le signe (`ADR-0067`), sans quoi le cliquet d'`AucunSigneDoubleNEstDetachableDeSonMotTest` rougit.

**Les trois lots que propose la composition du palier 1** (`composition-palier-1.md`, commit `20860e75` de `camille-po`, fusionne sur `round-two` par `a69baf0c` apres la base de
cet avis ; lu par `git show`). Mesure sur `544159d9` :

| Lot | Paquet sur le disque | Items (`grep -c '^ *- enonce:'`) | Entree au catalogue | Signe double derriere une espace ordinaire hors commentaire (`grep -nP ' [?!:;]'`) |
|---|---|---|---|---|
| `FRA-CE1-04` (X) | `ce1-texte-narratif.yaml` | 20 | 0 | aucun |
| `FRA-CE1-10` (Y) | `ce1-phrases-types.yaml` | 24 | 0 | aucun |
| `FRA-CE1-15` (Y) | `ce1-accord-groupe-nominal.yaml` | 24 | 0 | aucun |

Chaque branchement est faisable en l'etat : une entree, ses chaines d'inventaire, un `nombreDeManches` strictement inferieur au nombre d'items (`ADR-0023`). Aucun des trois ne
declare d'illustration (`grep -c 'illustration:'` : 0), donc aucun cliquet d'image ne bouge. Le choix des lots est a `nadia-prof` (`Q-P2` de `camille-po`) ; un autre lot de X ou
de Y se mesure de la meme facon.

## 5. Isolation des scratchpads et des ports entre instances

### 5.1 Les deux defauts constates

1. **Ecriture a la racine du scratchpad partage** : `jouer.py` ecrit par l'instance A a 12:10:27 puis ecrase par l'instance C a 12:33:47 le 2026-10-08 (criteres, 4.1, forme (c)).
2. **Requetes vers le serveur d'un autre agent** : a s56, le jar de `lea-dev` a refuse de demarrer sur un port deja pris (*"Port 18081 was already in use"*), ses requetes ont
   atteint le processus d'un autre, plus ancien, qui servait encore le jeu retire, et elle a arrete ce processus par son PID en le croyant sien (`rapport-lea-dev.md` de s56, 14.6).
   Le mode de defaillance n'est pas l'echec, c'est une mesure juste sur le mauvais jar.

### 5.2 Ce qui est encodable, et ce qui ne l'est pas

**Rien de cette isolation n'est encodable dans le depot.** Le scratchpad est hors de l'arbre, il est ecrit par l'outil d'ecriture de l'agent et non par un script du depot, et un port
est un etat du poste. Aucune `ArchRule`, aucun test du job `build`, aucun script de `.github/` ne les voit. **La garde reste celle que `nadia-prof` ecrit etre la sienne seule**
(criteres, section 6 : *"La forme (c) sur un scratchpad ou un port : moi seule, en lisant des transcriptions hors du depot"*).

### 5.3 Conditions pour le planning (la table est a `sofia-sm`)

- **I1. Un repertoire par agent, par instance et par palier**, a un seul niveau sous la racine, nomme `s57-p<n>-<agent>-<instance>` (par exemple `s57-p1-nadia-prof-b`), jamais
  reutilise d'un palier a l'autre. Motif : la forme (c) de s54 etait une ecriture successive, pas simultanee ; la definition n'exige pas la simultaneite (criteres, 4.1).
- **I2. Un port par agent, par instance et par palier**, ecrit dans la table du planning, distinct de tout autre de la table.
- **I3. A chaque lancement de jar** : avant, le port est libre (`netstat -ano | grep ":<port> "` ne rend rien) ; apres, l'agent lit dans **son** journal de demarrage la ligne
  *"Tomcat started on port <port>"* et le PID, avant toute mesure ; il arrete ce PID et lui seul. Sans la lecture du journal, le defaut 2 se reproduit sans bruit. Chaque instance
  construit son jar dans son propre worktree, donc dans son propre `bootstrap/target`.

**Ce qui le rendrait executable, hors de mon perimetre** : un crochet du harnais qui refuserait une ecriture a la racine du scratchpad. C'est une configuration de l'outil, pas du
depot ; elle est a l'utilisatrice (`Q4`). L'avis ne la requiert pas : I1 a I3 suffisent pour engager les paliers.

## 6. `S54-OBS-SCI-CONSIGNE`

**Mesure** : worktree jetable sur `544159d9` ; les 12 lignes `enonce:` de `cm1-sciences-extraire-information.yaml` perdent leur premiere phrase par `sed` (chaque enonce tient sur une
ligne, lignes 38 a 123). `grep -c "puis réponds à la question"` rend 0 ; `git diff --numstat` rend `12 12` sur ce seul fichier. Puis
`bash outils/mvn-jdk21.sh -B clean verify -Dmaven.test.failure.ignore=true` depuis la racine, sans `-pl`.

**Resultat : `BUILD SUCCESS`, 0 echec, 0 erreur, 0 ignore sur 2498 tests** (domain 120, application 31, adapter-web 486, adapter-content 1484, bootstrap 371,
integration 6), JDK **21.0.10** (`java.version` des rapports `TEST-*.xml` de `bootstrap`, 59 rapports, une seule valeur). Les deux controles de lecture d'une mutation
(`squad/lessons.md`, s56) sont tenus : le motif a ete trouve (12 lignes changees, 0 occurrence restante) et le nombre de tests lances est superieur a zero. Les rapports
`TEST-*.xml` des trois classes ci-dessous existent dans le worktree jetable, qui a ete supprime ensuite.

Les trois tests qui nomment le paquet (`PaquetCm1SciencesExtraireInformationTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest`)
sont dans ce perimetre. Aucun mot n'est ajoute : le garde-fou de l'accent n'a rien de neuf a juger. **Faisable en l'etat**, sans `ADR`. Le critere 4 de `nadia-prof` reste au
developpeur (`lea-dev`), qui nomme les tests qu'il a lances.

## 7. `S56-VSJ-CE1-R1`

Edit de `ce1-verbe-sujet.yaml` sous les garde-fous existants ; `PaquetCe1VerbeSujetTest` garde aujourd'hui la propriete 8 (*"les deux distracteurs apres"*), donc le contraire du critere
(backlog). **Condition** : l'amendement date de la propriete 8 par `nadia-prof` precede le commit, et le test change dans le meme commit que les textes (backlog, critere). Les mots
neufs d'un complement en tete (*"Ce matin"*) sont des items : le garde-fou de l'accent les juge sur le lexique, et un refus suit la route d'A2 point 2. Aucun `ADR`.

## 8. `S57-ADR-0065-DEUX-INSTANCES`

Livree avant l'ouverture (section 4, ligne 3.2). Ce qui reste a faire n'est pas a moi : l'etape 5 de la sequence de `sofia-sm` (le premier worktree d'un lot de A' verifie
`git merge-base --is-ancestor 729b0d8a HEAD`, `duree-et-paliers.md` section 5). Ce qui reste sans automate est ecrit dans l'amendement (point 4 d'A2, prefixe declaratif, resolution de
conflit sur la ligne du lexique du pom parent).

## 9. `HS-ACCUEIL-APM-R2` (hors sprint), le moyen

**Le defaut, lu dans le code** : `PartieController.choisirLaVue` ecrit la vue (ligne 250) avant de tester la partie en cours (ligne 251) ; la confirmation renvoie ensuite vers
`/niveaux/{niveau}/confirmer` (ligne 252), qui abandonne la partie et rend la vue de la session (lignes 332 a 340).

**Le moyen** : avec une partie en cours, la route n'ecrit rien et rend la confirmation vers une route neuve, `POST /niveaux/{niveau}/vue/{vue}/confirmer`, qui abandonne la partie,
ecrit la vue recue et rend l'accueil. La vue voyage dans l'adresse du bouton, comme le niveau le fait deja (*"puisque DEC-0028/ADR-0044 interdisent de le loger en session"*, Javadoc de
`niveaux`). *"Continuer la partie"* (`/retour/annuler`) ne change pas. Le troisieme critere (*"Tout voir"* puis *"Les jeux"* rend V2) reste vert par construction.

**Pourquoi un amendement** : `ADR-0106` decision 8 ecrit que cette route *"ecrit la vue recue (`ADR-0105` decision 2), avec ou sans partie"*. Le critere exige le contraire avec une
partie. `ADR-0105` decision 2 (la vue vit en session, par le seul porteur, valeur portee par la route) n'est pas touchee. **L'amendement d'`ADR-0106` est de ma main, dans le commit
du code ou avant** ; sa regle executable est le test de route de `yanis-qa` et sa mutation (backlog, quatrieme critere). La route neuve entre d'elle-meme dans le perimetre derive du
garde-fou de tabulation (`chaqueRouteDuControleurEstExerceeParCeGardeFou`, Javadoc de `niveaux`) : elle doit y etre exercee. **Prix** : non chiffre ; une route, un test, un
amendement.

## 10. `ADR` et regles ArchUnit

**Aucun `ADR` ouvert, aucune regle ArchUnit neuve.** Aucun poste de s57 ne porte une decision sur les dependances entre types : la correction d'`ART-CP-10` est une feuille et un
gabarit, le rejeu un ordre de fusion, l'isolation un etat du poste, les lots du contenu. Amendements dus **seulement si** la voie est retenue : `ADR-0104` (section 2.4, voies b et c),
`ADR-0106` (section 9). `ADR-index.md` n'a rien a changer.

**Compte** : 41 `static final ArchRule` et 38 `@ArchTest`. Methode d'`ADR-0104` : lignes des fichiers `*.java` suivis (`git ls-files`, ce qui exclut les worktrees d'agents) qui
correspondent a `^\s*(public |private |protected )?static final ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part, sur `544159d9`. Identique au compte d'`ADR-0065` A2 sur
`9d101ea0`.

## 11. Questions ouvertes

| Code | Question | A qui |
|---|---|---|
| `Q1` | La correction d'`ART-CP-10` doit-elle lever `RDO-CP-R1` dans le meme rejeu ? Si oui, la cible a 744 est 125 px, pas 105 (2.1). Et quelle forme, dans le cadre de 2.2 et 2.3 | `nadia-prof` (cible), `ines-design` (forme), routee par `sofia-sm` |
| `Q2` | `RDO-CP-R2` : voie (a), (b) ou (c) (2.4) | `nadia-prof`, `ines-design` |
| `Q3` | L'entree neuve de B2 reprend-elle l'identifiant `bandit-manchot-rapprocher-deux-oeuvres-cp` ou en prend-elle un neuf (R3) | `camille-po` |
| `Q4` | Veut-elle un crochet du harnais qui refuse une ecriture a la racine du scratchpad (5.3) ? Ce n'est pas requis pour engager s57 | utilisatrice, routee par `sofia-sm` |
| `Q5` | La ligne de planning du rejeu d'`ART-CP-10` par la branche B2 (3.2) et la table I1 a I3 (5.3) | `sofia-sm` |

## 12. Jobs de `.github/workflows/ci.yml` lances pour ce commit

Sur ce worktree, avec ce fichier, avant commit :

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests, 0 echec, 0 erreur (120, 31, 486, 1484, 371 et 6
  d'integration), JDK 21.0.10 (`java.version` des rapports). `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` a lu ce fichier apres sa derniere modification de tableau.
  L'etape "Package du fat jar" (`mvn -B -pl bootstrap -am package`) n'est pas lancee.
- **`chemins`** : `bash .github/scripts/verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce fichier compris, passe a `verifier-chemins.sh` : sort 0.
- **`outils`** : les juges de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py` : 11 lances, 11 sortis 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet de ce commit et la plage `round-two..HEAD`
  passent dans `verifier-prefixes.sh` et `verifier-garde-fou-accent.sh` apres sa creation, hors de ce fichier.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
