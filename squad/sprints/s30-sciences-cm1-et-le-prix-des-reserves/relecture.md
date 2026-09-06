# Relecture pedagogique - Sprint 30

**Auteur** : `relecteur-pedagogique`. **Depot mesure** : worktree dedie (`recreo-s30-relecteur`,
branche `s30-relecteur`, creee sur `dbbfe129` - `round-two` - et verifiee descendante de lui avant
toute ecriture). Livrables lus dans ce worktree ; deux fichiers ont ete lus en lecture seule dans
les worktrees d'autres agents (`recreo-s30-yanis`, `recreo-s30-theo-verif`), non encore fusionnes
dans `round-two` a la date de cette relecture - aucune ecriture n'y a ete faite.

**Perimetre lu** : `planning.md`, `avis-faisabilite-theo.md`, `ARBITRAGE-PREALABLE-s30-...md`,
`DEC-0079`, `DEC-index.md`, `ADR-0084`, `ADR-index.md`, section "Sprint 30" de
`docs/backlog/backlog.md`, section "Perimetre de s30" et table "Sciences et technologie - CM1" de
`MATRICE-couverture.md`, `squad/programme/sciences-et-technologie/competences-cm1.md`. Egalement
lus a titre de verification croisee (non listes par la commande, mais cites par les documents
ci-dessus) : `ARBITRAGE-PREALABLE-s29-le-palier-2-...md`, `ADR-0081`, `DEC-0077`, `DEC-0078`,
`DEC-0064`, `rapport-qualite.md` (worktree `recreo-s30-yanis`), `confirmation-archunit.md`
(worktree `recreo-s30-theo-verif`).

## 1. Ce que j'ai corrige

**Rien.** Aucune faute d'orthographe, d'accentuation ou de ponctuation trouvee dans les livrables
lus ; aucun tiret cadratin, aucun guillemet typographique, aucun emoji ; aucun chemin absolu de
poste de travail ni nom d'utilisateur dans les fichiers de `round-two`. Un commit vide n'aurait pas
de sens : je ne cree donc pas de commit `[REL]` pour ce sprint au-dela du present rapport.

## 2. Le renommage du repertoire de sprint - verifie, aucune reference cassee

Le repertoire a bien ete renomme de `squad/sprints/s30/` vers
`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/` (`planning.md`, section 0, point 5 du
tableau). Verification : toute occurrence de `sprints/s30` dans les fichiers `.md` du depot pointe
vers le chemin complet avec slug, dans `docs/backlog/backlog.md`, `DEC-0079` et les deux fichiers du
repertoire lui-meme - aucune occurrence de l'ancien chemin sans slug ne subsiste. L'en-tete
d'`avis-faisabilite-theo.md` garde la mention historique *"Repertoire : squad/sprints/s30/, sans
slug"* suivie de la note de renommage de `sofia-sm` juste en dessous : ce n'est pas une reference
cassee, c'est la trace assumee de l'etat avant renommage, laissee lisible a cote de sa correction.

Toutes les references de section citees dans ces documents ont ete verifiees une a une (existence
du fichier cible et existence de la section citee) : `DEC-0079`, `ADR-0084`, l'arbitrage prealable,
les fiches de conformite citees par les stories `S30-3` a `S30-10`
(`FICHE-bandit-manchot-cycle-de-vie-cp.md` section 9, `-objet-technique-cp.md` section 7,
`-histoire-geographie-cm1.md` section 7, `-division-posee-cm1.md` "Troisieme audit" section 2,
`-fait-opinion-cm1.md` sections 4/5/6, `-droit-devoir-cm1.md` sections 4/5/6,
`PASSE-TRANSVERSE-s29-...md` section 2, `FICHE-bandit-manchot-additions-cp.md` section
"Reverification du 2026-09-06" point 3, `DEC-0077` section 9.4,
`verdicts-nadia-instance-A.md` section "`VIV-R2` - ma decision"). Tout resout.

## 3. Les chiffres perimes annonces par la commande - tous correctement dates ou en cours de correction

- **1289 -> 1321 tests.** `round-two` (`dbbfe129`) ne porte que le 1289 mesure a l'entree du sprint
  (`planning.md`, `avis-faisabilite-theo.md`), correctement date et jamais presente comme final. Le
  1321 confirme trois fois (`yanis-qa` et `theo-archi`, chacun sur son propre worktree, methodes
  independantes) n'est pas encore fusionne dans `round-two` : rien a corriger cote redaction, mais
  la cloture devra l'y faire entrer.
- **Dette de referentiels sans code, onze -> huit.** Confirme, avec un residu a signaler
  (section 4.1 ci-dessous) : le fichier qui portait l'erreur (`avis-faisabilite-theo.md`,
  section 2.2) reste a "sept"/"onze" dans `round-two`, alors meme que `theo-archi` a deja prepare le
  correctif ("neuf"/"neuf" puis "dix"/"huit") sur son propre worktree.
- **Population du garde-fou de `REV-3`, 54 -> 58 ecrans.** Les deux chiffres sont exacts a leur
  date et le disent explicitement partout ou ils apparaissent : `ADR-0084` et
  `avis-faisabilite-theo.md` mesurent 54 avant les lots de sciences (avec la note ajoutee par
  `theo-archi` sur son worktree annoncant que le chiffre suivra le catalogue) ; `yanis-qa` mesure 58
  sur `dbbfe129` et explique l'ecart par la derivation de la population depuis le catalogue reel.
  Aucun endroit ne presente 54 comme le compte courant apres l'ajout des paquets de sciences.
- **Catalogue, 54 -> 58 entrees.** Meme situation : chaque document nomme sa date de mesure.

## 4. Ce que je signale sans corriger

### 4.1 `avis-faisabilite-theo.md`, section 2.2 - chiffre "sept"/"onze" toujours faux dans `round-two`

**A `theo-archi`, deja route par `sofia-sm` (`planning.md`, sections 0 et 7) mais pas encore
corrige dans le fichier lui-meme.** Le paragraphe *"sept referentiels portent des codes [...],
onze n'en portent aucun"* est toujours dans `round-two` a `dbbfe129`. `theo-archi` a deja prepare la
correction sur son propre worktree (`recreo-s30-theo-verif`, non fusionne a la date de cette
relecture) : elle porte le compte a "neuf"/"neuf" au commit de son avis, puis a "dix"/"huit" apres
l'allocation des codes `SCI-CM1-01` a `-20`, ce qui concorde avec le recompte de `sofia-sm`. Je ne
touche pas ce fichier - le chiffre est un chiffre d'audit de `theo-archi` - mais je note que
`round-two` porte encore l'erreur tant que cette branche n'est pas fusionnee : point de vigilance
pour la cloture du sprint.

### 4.2 `squad/programme/sciences-et-technologie/competences-cm1.md`, ligne 153 - "des six" contre "des dix"

**A `nadia-prof`.** Le referentiel dit *"C'est la matiere la plus immediatement servable des six"*.
`nadia-prof` releve elle-meme cet ecart dans son arbitrage prealable de s30 (section 1.5) : la
matrice dit *"des dix"*, et elle conclut explicitement *"c'est des dix qui est juste, et c'est la
formulation de la matrice"* - la population etant les dix referentiels ecrits le meme jour que
celui de sciences (arts plastiques, education musicale, EPS, EMC, histoire des arts, sciences et
technologie, deux niveaux chacun). Elle ne dit nulle part avoir corrige son propre fichier, et il
porte toujours "des six" a ce jour. Signale a `nadia-prof` : c'est son propre chiffre, dans son
propre document.

### 4.3 `ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md`, section 8.1 - "cinq dessins" contre six enumeres

**A `nadia-prof`, deja route par `theo-archi`** (`avis-faisabilite-theo.md`, section 4.1 :
*"la section 8.1 de l'arbitrage de `nadia-prof` titre 'les cinq dessins' et en enumere six [...].
Signale au `relecteur-pedagogique`, pas corrige par moi"*). Verifie : la phrase exacte est *"Les
cinq dessins dont ces six lignes ont besoin - plan de classe, plan de musee, carte de France,
planisphere, frise, quadrillage - n'existent nulle part dans le depot"* - l'enumeration compte
six elements sous le mot "cinq". C'est un chiffre d'audit de `nadia-prof`, dans un fichier de s29
deja clos ; je ne le corrige pas et je le signale, comme `theo-archi` me le demandait.

### 4.4 `rapport-qualite.md` (worktree `recreo-s30-yanis`, non fusionne) - chemin absolu de poste de travail

**A `yanis-qa`.** Ligne 3 : *"**Depot mesure** : `C:/perso/recreo-s30-yanis`"*. C'est un chemin
absolu de poste de travail, exactement la forme que `CLAUDE.md` interdit (le critere est la
portabilite : nommer le worktree par son nom - `recreo-s30-yanis`, comme le fait ce meme document
a la ligne suivante pour la commande `git worktree add` - suffit et ne depend d'aucune machine).
Ce fichier n'est pas encore fusionne dans `round-two` ; je ne le touche pas puisqu'il n'est pas
dans mon arbre, et je le signale a `yanis-qa` pour correction avant fusion.

### 4.5 `rapport-qualite.md` (worktree `recreo-s30-yanis`, non fusionne) - attribution a verifier

**A `yanis-qa`, et a `sofia-sm` pour la cloture.** Ligne 30 : *"Ce chiffre confirme exactement celui
annonce par `sofia-sm` (179 rapports, 1321 tests, 0/0/0)"*. Le seul chiffre de test que `sofia-sm`
a ecrit dans `round-two` est **1289** (`planning.md`), mesure avant les lots de developpement ; je
ne trouve nulle part un document de `sofia-sm` annoncant 179 rapports ou 1321 tests. Le chiffre
179/1321 est en revanche celui que `theo-archi` mesure et ecrit sur `dbbfe129`
(`confirmation-archunit.md`, worktree `recreo-s30-theo-verif`), commite deux minutes avant le
rapport de `yanis-qa`. Ce n'est pas une reference cassee (aucun chemin cite), mais une attribution
qui ne resout a aucun fichier de `sofia-sm` : a verifier par `yanis-qa` avant fusion, et par
`sofia-sm` puisque son nom y est engage.

## 5. Convention MASTER (`DEC-0062`)

Aucune occurrence de "MASTER" dans les livrables de s30. Tous les chiffres et affirmations de fait
portent leur provenance sous la forme 1 (agent de la squad, fichier et section) : rien a signaler.

## 6. Ce que je n'ai pas touche

Le contenu servi a l'enfant (les quatre paquets YAML de sciences, les paquets repris) reste au
`nadia-prof` : aucune des deux instances n'a encore prononce de Done pedagogique a la date de cette
relecture, et je n'ai pas a relire un contenu qui n'est pas encore audite. Je n'ai pas touche aux
worktrees `recreo-s30-nadia` et `recreo-s30-nadia-B`.
