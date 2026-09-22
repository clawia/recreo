# Review - s39 - couverture maximale sur six lignes candidates

- **Horodatage de fin** : 2026-09-22 19:31 (UTC+02:00).
- **Horodatage de debut** (`planning.md`) : 2026-09-22 14:45:37 (UTC+02:00, horodatage du commit
  `5a7a2312`).
- **Duree effective** : 4 h 46 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `7514c5ff`, `git status --porcelain` verifie vide avant et apres
  cette review. Trente commits dans la fenetre `542c1258..HEAD` (`git rev-list --count`, mesure
  brute, hors filtrage), onze d'entre eux des fusions.

**Note methodologique.** `git log --oneline 542c1258..HEAD` sans option supplementaire (hook
`rtk`, proxy token-optimise, transparent d'apres sa documentation) rend 19 lignes et omet les onze
commits de fusion, sans erreur ni avertissement - un ecart muet exactement de la famille que
`CLAUDE.md` met en garde contre pour les mesures partielles Maven. `rtk proxy git log ...`
(execution brute, sans filtrage) rend les 30 commits reels, confirmes par `git rev-list --count`.
Toute verification de cette review qui portait sur l'historique Git a ete refaite ou verifiee via
`rtk proxy` plutot que via le hook transparent.

---

## 1. L'increment livre, en une phrase

Quatre lignes du programme passent de `non couvert` a `couvert-valide` (`MAT-CE1-13` monnaie CE1,
`SCI-CM1-04` energie CM1, `ART-CP-11` outils arts plastiques CP, `MAT-CE1-16` solides usuels CE1),
deux lignes passent de `non couvert` a `en cours` sur leur seule sous-partie textuelle (`MAT-CP-01`
denombrement CP, `MAT-CE1-09` longueurs CE1) ; la couverture du programme passe de 78/155 (50,3 %)
a **82/155 (52,9 %)**.

## 2. Le Done technique - quatre jobs, verifies un par un, sur l'arbre fusionne final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un
agent qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres.
Les quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur
`round-two` a `7514c5ff` (`git status --porcelain` vide avant et apres) :

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (19 cas, tous verts) puis `rtk proxy git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, un par un | **vert**, **9 juges purs executes**, 0 echec |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas) et `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `rtk proxy git log --no-merges --format=%s 542c1258..HEAD \| bash .github/scripts/verifier-prefixes.sh` (base : `542c1258`, cloture de s38, 19 commits hors fusions) | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, blocs `[INFO]
Results:` par module** (methode imposee par `squad/README.md` section 4 depuis l'amendement de
s11) :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 809 |
| recreo-bootstrap (surefire, unitaires) | 269 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1606** |
| **Total integration** | **6** |

**1612 tests, 0 echec, 0 erreur, 0 ignore.** Ce chiffre correspond exactement au compte autoritaire
de `yanis-qa` (`rapport-qualite.md`, section 1 : memes 1606 unitaires + 6 IT), mesure par lui sur
`s39-yanis` a `5e867bc5` avant les cinq dernieres fusions (Ines, deux fusions Nadia, une fusion
Theo, Camille) - aucune ne touche de fichier Java ni de contenu YAML servi, aucun ecart attendu ni
trouve entre les deux mesures.

**ArchUnit, recompte par moi sur l'arbre fusionne, methode d'`ADR-0095`/`ADR-0097`/`ADR-0098`
(occurrences litterales dans `src/test/java` des cinq modules du pom agregateur, `target/` et les
fixtures de `resources/contreexemple/` exclus - ces dernieres portent deux occurrences
supplementaires de `static final ArchRule` dans un faux module de test, hors perimetre de la
mesure)** : **36 `static final ArchRule`, 35 `@ArchTest`** - inchange depuis la cloture de s38.
Confirme ce que `theo-archi` ecrit dans `ADR-0098` : aucune regle ArchUnit neuve, la population de
ce sprint reste du YAML et des descripteurs de contenu, jamais du bytecode.

## 3. La confirmation ArchUnit et les decisions d'architecture

`theo-archi` a accepte `ADR-0098` pendant son propre travail sur ce sprint, avant la cloture.
Verifie par moi, sur le disque :

- `squad/decisions/adr/ADR-0098-l-allocation-d-une-sous-partie-de-mathematiques-de-ce1-nomme-sa-ligne-et-un-paquet-ne-declare-qu-une-sous-partie-d-une-ligne-qu-il-sert.md`
  porte `Statut : Accepte`.
- L'entree figure dans `squad/decisions/adr/ADR-index.md`, avec sa methode citee dans la meme
  ligne (36 `ArchRule`/35 `@ArchTest`, mesure apres le changement, pas recopiee).

`ADR-0098` etend `ADR-0097` (s38) sans le remplacer : chaque entree de l'allocation des sous-parties
de mathematiques de CE1 porte desormais un couple ligne + competence, et un paquet dont la
competence est allouee sur une ligne qu'il ne declare pas est rouge. Trois lignes candidates de ce
sprint (`MAT-CE1-09`, `MAT-CE1-13`, `MAT-CE1-16`) recoivent leur entree avant leur paquet, meme
geste qu'`ADR-0097` pour `MAT-CE1-03`.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s39-yanis`, `git rev-parse --abbrev-ref HEAD`
verifie egal a `s39-yanis`, arbre `5e867bc5`) : Done technique confirme sur `build` et les trois
autres jobs, tous nommes individuellement. Trois garde-fous mordus par mutation sur le materiau
**reellement servi**, jamais une fixture, fichier restaure et `git status --short` verifie vide
apres chaque mutation :

- allocation `ADR-0098` (`ce1-solides.yaml`, competence declaree allouee sur `MAT-CE1-16` mais
  ligne non declaree) : rouge mute, 1 echec sur 14 ; vert restaure.
- anti-cercle-nu sur la boule (`solide-boule-a.svg`, cercle + ellipse d'equateur remplace par un
  cercle seul) : rouge mute, 1 echec sur 15 ; vert restaure.
- cliquet de dette typographique `TYP-1` (`ce1-longueurs.yaml`, un espace insecable sur sept
  remplace par un espace ordinaire) : rouge mute, 1 echec sur 5 ; vert restaure.

Le verrou `mvnd` transitoire signale par `marc-dev` et `lea-dev` pendant le developpement n'a pas
ete reproduit par `yanis-qa`, ni par moi lors de la verification technique finale de cette review.

## 5. Le `relecteur-pedagogique`

**L'amendement de s38 a tenu.** `relecture.md` existe physiquement dans ce dossier de sprint, ecrit
depuis un worktree dedie (`s39-relecteur`, base `round-two` a `237fb3b8`) et fusionne
(`7514c5ff`) : cinq corrections de langue appliquees (accord de genre sur `DEC-0092`, coquille sur
`planning.md`, trait d'union de "soixante et onze", quatre titres Markdown fusionnes dans le
backlog, apostrophes restituees dans une fixture de contreexemple), et une liste explicite des
verifications qui n'ont trouve aucun defaut. `mvn verify` relance sur son propre diff avant commit,
BUILD SUCCESS. A la difference de s38, aucun ecart entre activite mesuree et artefact de conclusion
sur le disque.

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Objectif de couverture maximale sur six lignes candidates recu hors depot (forme 3), ne vaut pas motif tant qu'il n'est pas formalise | Gouvernance / produit | `camille-po` (route par `planning.md`) | **Resolu** - `DEC-0092`, Acceptee, reserve de sourcage nommee explicitement (plus mince que `DEC-0091`, un seul mot cite de l'utilisatrice) |
| `MAT-CP-01` (denombrer jusqu'a 100) contredit la premisse "sans arbitrage attendu" de l'analyse recue : `DEN-2` (non negociable) interdit la fermeture a `couvert-valide` par la seule mecanique textuelle | Pedagogie (arbitrage anterieur) | `nadia-prof` (route d'office par `planning.md` puis `DEC-0092`) | **Resolu, dans le sens deja attendu** - `DEN-2` maintenue telle quelle (`ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`), la ligne ferme a `en cours` seulement, comme annonce des le planning |
| Trois lignes CE1 hors `MAT-CE1-03` (`MAT-CE1-09`, `MAT-CE1-13`, `MAT-CE1-16`) feraient echouer le garde-fou d'allocation d'`ADR-0097`, dont le perimetre couvre tout paquet CE1 mais l'allocation ne nommait que `MAT-CE1-03` | Architecture | `theo-archi` (trouve par lui-meme en avis de faisabilite) | **Resolu** - `ADR-0098`, Accepte, regle executable ecrite dans le meme geste |
| Distracteur de conversion cm/m casse par un garde-fou existant sur `S39-LONGUEUR` ; forme visuelle de la sphere (cercle nu) refusee par `ADR-0077` sur `S39-SOLIDES` | Pedagogie (forme d'un distracteur, forme visuelle) | `nadia-prof` (route par `theo-archi`) | **Resolu** - amendement d'arbitrage du 2026-09-22 : distracteur corrige, sphere en cercle + ellipse d'equateur |
| Plafond de 176 px suffisant pour les cinq solides CE1, deux contraintes de proportion (cube/pave droit, trait plein sur l'arete cachee du cylindre) | Design | `ines-design` (verification sur rendu reel, Chrome headless) | **Resolu** - `contrainte-ines-solides-ce1.md`, deux contraintes ecrites, fusionnees dans le lot `S39-SOLIDES` |
| Conflit de fusion concurrent entre les worktrees de `marc-dev` et `lea-dev` sur le checkout principal partage | Gouvernance (orchestration de fusion) | signale par `marc-dev`, resolu par `lea-dev` | **Resolu, sans perte de travail des deux cotes** |
| Verrou Windows transitoire du daemon `mvnd` partage, signale par deux devs independamment | Infrastructure | constate, non route (infrastructure, pas une faute de travail, meme famille que l'amendement s34) | **Non reproduit par `yanis-qa` ni par moi** - non bloquant, voir retro |
| **`camille-po` a de nouveau commis directement sur `round-two`** (`77e441e2`, formalisation de `DEC-0092` et instruction des six lots) au lieu d'un worktree dedie merge - meme deviation qu'a s38, cinquieme occurrence documentee de cette faute (s06, s18, s21/22, s38, s39) | Gouvernance (discipline de process, deja ecrite quatre fois) | Constate par moi a la review ; corrige pour le reste de son travail sur ce sprint (`eab574cb`, mesure de matrice, ecrit depuis le worktree dedie `s39-camille-matrice` puis fusionne en `237fb3b8`) | **Non bloquant, aucune perte de contenu - mais recurrence aggravee, voir retro** |
| Retard sur la meme instruction : le reacteur a casse (1 test `bootstrap` en echec) pendant le developpement, rapporte par l'orchestration, avant d'etre corrige | Gouvernance / qualite | non route formellement - evenement de daily, non journalise par construction | **Rapporte, non verifiable independamment sur le depot** - le daily n'est pas journalise et aucun etat rouge n'a jamais ete commis ; l'arbre fusionne final est vert (section 2), c'est le seul etat que je peux attester moi-meme |
| `MAT-CE1-18` (sens de la division) toujours sans ligne dans `MATRICE-couverture.md` | Gouvernance / produit | signale une quatrieme fois par `camille-po` (`planning.md`, section 7), non corrige | **Ouvert, non corrige - quatrieme sprint consecutif (s36, s37, s38, s39)**, voir section 8 et retro |

## 7. Le registre de dette - inchange a la cloture de s39

**Ouverture de s39 : 9,25** (`squad/sprints/s38-fermeture-mat-ce1-03/review.md`, section 7).
Verifie par moi : recherche des identifiants de ce sprint (`MAT-CP-01`, `S39-DEN`, `S39-LONGUEUR`,
`S39-MONNAIE`, `S39-SOLIDES`, `S39-ENERGIE`, `S39-OUTILS`, `DEC-0092`, `ADR-0098`) dans `DEC-0086`
et `DEC-0087` : aucune occurrence dans les deux textes. Ni la couverture maximale de s39 ni sa
gouvernance de sprint ne sont des postes de la composition du Plan 2 : ce sprint ouvre une ligne
hors du vivier `DEC-0086`/`DEC-0087`, comme `S37-1` et `MAT-CE1-03` avant lui. **Registre de dette
a l'ouverture d'un sprint futur : 9,25, inchange.**

## 8. La couverture du programme

**82/155 (52,9 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md` (section
"Fermeture de s39", recompte independant sur les dix-neuf tableaux de la matrice) : quatre
fermetures (`MAT-CE1-13`, `SCI-CM1-04`, `ART-CP-11`, `MAT-CE1-16`, `en cours`/`non couvert` ->
`couvert-valide`) et deux ouvertures partielles (`MAT-CP-01`, `MAT-CE1-09`, `non couvert` -> `en
cours`, sans toucher le numerateur). `couvert-valide` 78 -> 82, `en cours` 0 -> 2, `non couvert`
77 -> 71, `non couvrable en l'etat` inchange a 71, total 226, denominateur atteignable 155 inchange.

**Gap preexistant, non corrige a ce sprint, quatrieme sprint consecutif** : `MAT-CE1-18` (sens de
la division), ajoutee au referentiel `squad/programme/mathematiques/competences-ce1.md` a s36,
n'a toujours aucune ligne dans `squad/programme/MATRICE-couverture.md` (`grep -n "MAT-CE1-18"` sur
la matrice : aucune occurrence). Trouve par `nadia-prof` a s36, signale a s37 et a s38, signale une
quatrieme fois dans `planning.md` de ce sprint (section 7) sans devenir un motif engage. Reporte en
retro, section 2.

## 9. L'avis de `nadia-prof` et le Done pedagogique - rapporte, pas prononce par cette review

Le Done pedagogique de ce sprint est prononce dans six fiches de conformite, que cette review
rapporte sans le rendre a sa place :

| Ligne | Verdict | Fiche |
|---|---|---|
| Monnaie CE1 (`MAT-CE1-13`) | **VALIDE, sans reserve** - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-monnaie-ce1.md` |
| Energie CM1 (`SCI-CM1-04`) | **VALIDE, sans reserve** - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-energie-cm1.md` |
| Outils arts plastiques CP (`ART-CP-11`) | **VALIDE, sans reserve** - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-outils-arts-cp.md` |
| Solides usuels CE1 (`MAT-CE1-16`) | **VALIDE, sans reserve** - `couvert-valide`, boule verifiee lisible a l'ecran sur trois tailles a 176 px | `squad/conformite/FICHE-bandit-manchot-solides-ce1.md` |
| Denombrement CP (`MAT-CP-01`) | **VALIDE limite a la part textuelle livree** - `en cours`, `DEN-2` non levee | `squad/conformite/FICHE-bandit-manchot-nombres-jusqu-a-100-cp.md` |
| Longueurs CE1 (`MAT-CE1-09`) | **VALIDE limite a la part textuelle livree** - `en cours`, mesure a la regle graduee non servie | `squad/conformite/FICHE-bandit-manchot-longueurs-ce1.md` |

L'audit a ete mene reacteur construit, jar lance sur le JDK 21 de la toolchain, chaque partie jouee
a l'ecran (fragments htmx rendus avec la feuille de style reelle du jar via Chrome headless), tous
les items des six paquets recalcules a la main (`7c62d3da`).

## 10. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les six verdicts de `nadia-prof` (section 9) sont deja
  prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po`.
- Elle ne corrige pas le gap `MAT-CE1-18` : c'est un document de `camille-po`, la correction lui
  revient a un sprint ou elle est affectee a ce lot.
- Elle ne tranche pas la severite de la deviation "worktree dedie" de `camille-po` au-dela de son
  constat : la question de savoir si le process doit changer est traitee en retro, pas ici.
- Elle ne fabrique aucune confirmation du "1 test bootstrap en echec puis corrige" au-dela de ce
  que l'orchestration rapporte : le daily n'est pas journalise, et aucun etat rouge n'a jamais ete
  commis dans le depot.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** Les decisions du sprint ont ete
prises ailleurs et sont enregistrees ici pour memoire, chacune verifiee physiquement presente sur
le disque avant d'etre citee :

- `DEC-0092` - objectif et perimetre de s39 (`camille-po`, 2026-09-22) - **Acceptee**. Verifiee
  dans `squad/decisions/produit/DEC-index.md` et sur le disque
  (`squad/decisions/produit/DEC-0092-objectif-et-perimetre-de-s39-couverture-maximale-sur-six-lignes-candidates.md`).
- `ADR-0098` - l'allocation d'une sous-partie de mathematiques de CE1 nomme sa ligne de referentiel
  (`theo-archi`, 2026-09-22) - **Accepte**. Verifie dans `squad/decisions/adr/ADR-index.md`
  (section 3 ci-dessus).

**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
