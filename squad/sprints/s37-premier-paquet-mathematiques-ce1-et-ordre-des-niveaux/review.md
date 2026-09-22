# Review - s37 - premier paquet mathematiques CE1 et ordre des niveaux

- **Horodatage de fin** : 2026-09-22 08:35 (UTC+02:00).
- **Horodatage de debut** (`planning.md`) : 2026-09-21 22:05 (UTC+02:00).
- **Duree effective** : 10 h 30 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `s37-sofia` a `45a25fe9`. Huit commits de preparation verifies ancetres de ce
  commit par `git merge-base --is-ancestor <commit> HEAD` avant cette review (`s37-camille`,
  `s37-theo`, `s37-nadia`, `s37-theo-mecanique`, `s37-yanis`, `s37-nadia-audit`,
  `s37-camille-matrice`), aucune fusion supplementaire necessaire au-dela de celles deja faites par
  moi.

---

## 1. L'increment livre, en une phrase

Le troisieme niveau du produit recoit son premier paquet jouable - additions dans la limite de 100
(`MAT-CE1-03`, `bandit-manchot-additions-ce1`, Done pedagogique **VALIDE SOUS RESERVE**) - et
l'en-tete affiche desormais les niveaux dans l'ordre de la progression scolaire (CP, CE1, CM1,
CM2) au lieu de l'ordre du catalogue, garanti par un garde-fou de completude (`ADR-0095`).

## 2. Le Done technique - quatre jobs, verifies un par un, sur l'arbre fusionne final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un
agent qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres.
Les quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur
`s37-sofia` a `45a25fe9` (`git status --porcelain` vide avant et apres).

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, un par un | **vert**, **9 juges purs executes**, 0 echec |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` et `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, blocs `[INFO]
Results:` par module** (methode imposee par `squad/README.md` section 4 depuis l'amendement de
s11 - jamais la somme des lignes `Tests run:` des rapports `.txt` unitaires, qui sous-compte les
classes melangeant `@ArchTest` et `@Test`) :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 725 |
| recreo-bootstrap (surefire, unitaires) | 265 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1518** |
| **Total integration** | **6** |

**0 echec, 0 erreur, 0 ignore.** Ce chiffre correspond exactement au compte autoritaire de
`yanis-qa` (`rapport-qualite.md`, section 2 : 1518 unitaires + 6 IT, methode blocs `Results:`
recoupee par la somme des attributs `tests=` des rapports XML) et au chiffre initialement annonce
par `theo-archi` sur son propre worktree avant fusion.

**Mon propre ecart de methode, trouve et corrige par `yanis-qa` (obstacle detaille en section 6).**
J'avais moi-meme mesure 1509 apres la fusion de `s37-theo-mecanique`, par la methode que
`squad/README.md` proscrit depuis l'amendement de s11 (somme des `Tests run:` des `.txt`
Surefire). `yanis-qa` a isole la cause sur le meme arbre (`9412f659`) : cinq classes du depot
melangent, dans une meme classe, un champ `@ArchTest ArchRule` et une ou plusieurs methodes
`@Test` - le rapport `.txt` n'ecrit alors que le compte Jupiter, le rapport XML agrege les deux
moteurs. Ce sprint ajoute une cinquieme classe de ce type
(`LOrdreDesNiveauxResteUneDonneeTest`, livree par `ADR-0095`, 2 `@ArchTest` + 3 `@Test`), portant
l'ecart total a 9 - exactement le delta entre mes deux mesures. Aucun des deux chiffres n'etait un
mensonge ; un seul suivait la methode que le README impose. Je retiens la methode correcte pour la
suite : blocs `Results:` apres `clean`, ou somme des attributs `tests=` des rapports XML - jamais
les `.txt`.

## 3. La confirmation ArchUnit et les decisions d'architecture

`theo-archi` a accepte `ADR-0095` et `ADR-0096` pendant son propre travail sur ce sprint, avant la
cloture. Verifie par moi, sur le disque, sans repasser derriere lui :

- `ADR-0095` (l'ordre des niveaux est une cle de tri et tout niveau servi est nomme dans la
  sequence) : `squad/decisions/adr/ADR-0095-l-ordre-des-niveaux-est-une-cle-de-tri-et-tout-niveau-servi-est-nomme-dans-la-sequence.md`
  porte `Statut : Accepte`.
- `ADR-0096` (la repartition par forme est la regle, le paquet statique est sa consequence) :
  `squad/decisions/adr/ADR-0096-la-repartition-par-forme-est-la-regle-le-paquet-statique-est-sa-consequence.md`
  porte `Statut : Accepte`.
- Les deux figurent dans `squad/decisions/adr/ADR-index.md` avec leur methode citee dans la meme
  ligne (mecanisme retenu, garde-fous, morsures, porte technique de leur worktree d'origine).

Comptes de regles ArchUnit, methode citee par `theo-archi` lui-meme dans les deux entrees
d'index et non recomptee ici (perimetre et methode deja explicites, inchanges d'un `ADR` a
l'autre) : `static final ArchRule` **34 -> 36**, `@ArchTest` **33 -> 35**, l'ecart d'une unite entre
les deux comptes preexistant a ce sprint (cause nommee par `ADR-0094`).

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s37-yanis`, arbre `9412f659`) : Done technique
confirme sur `build` seul (BUILD SUCCESS, 1518+6, 0 echec) - les trois autres jobs de la CI n'y
sont pas mesures, `yanis-qa` le dit explicitement en section 5 de son rapport, et c'est moi qui les
ai relances en section 2 ci-dessus. Ecart de comptage 1509/1518 trouve, mesure et explique (section
2). Deux garde-fous recents (`ADR-0095`, `ADR-0096`) mordus par mutation sur le materiau reel
servi - le seul paquet CE1 aujourd'hui en catalogue, `ce1-additions-jusqu-a-100.yaml`, jamais une
fixture en memoire - fichier restaure et verifie vide apres chaque mutation. Aucun test instable
observe.

## 5. Le `relecteur-pedagogique`

**Aucune ceremonie de relecture n'a eu lieu ce sprint.** Aucun fichier de relecture n'existe dans
le dossier de ce sprint, et aucune branche `s37-relecture*` n'a ete provisionnee. Je le constate
explicitement plutot que de laisser la section vide ou de sous-entendre une relecture qui n'a pas
eu lieu.

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| `S37-1` bloquee avant tout developpement : cible d'operande nul a l'echelle 100 | Pedagogie | `nadia-prof` (route par `theo-archi` a l'avis de faisabilite) | **Resolu** - `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`, `ADD-CE1-1` (0 % a 5 %) |
| `S37-1` bloquee avant tout developpement : table de repartition des sommes a l'echelle 100 | Pedagogie | `nadia-prof` (route par `theo-archi` a l'avis de faisabilite) | **Resolu** - meme arbitrage, `ADD-CE1-2` (huit bandes de forme), encodee en `ADR-0096` |
| Choix technique - reutiliser `additions-bornees` recalibre ou authorer un paquet statique | Architecture | `theo-archi` | **Resolu** - paquet statique retenu (`ADR-0096`), generateur inchange, cinq motifs mesures |
| Constante `ACCEPTATION_OPERANDE_NUL_SUR` partagee entre paquets `additions-bornees` | Technique | `theo-archi` | **Sans objet** - le paquet CE1 livre est statique (`type: question`), le generateur n'est pas etendu, aucun partage nouveau ne se produit |
| Ecart de comptage de tests 1509/1518 sur l'arbre fusionne | Qualite / methode | trouve par moi-meme, corrige par `yanis-qa` | **Resolu** - cause isolee (cinq classes `@ArchTest` + `@Test` melangees, methode `.txt` proscrite depuis s11), methode correcte reappliquee en section 2 |
| `MAT-CE1-18` (sens de la division, ajoutee au referentiel a s36) sans ligne dans `MATRICE-couverture.md` | Gouvernance / produit | trouve par `camille-po` en fusionnant son propre lot | **Ouvert, non corrige a ce sprint** - dette a reporter, voir section 8 |
| `ADD-CE1-R1` - l'indice des cinq items "ajout d'un nombre rond" s'arrete une etape trop tot | Pedagogie | `nadia-prof`, texte de remplacement deja donne (`FICHE-bandit-manchot-additions-ce1.md`, section 7.1) | **Ouverte, non bloquante** - matiere a story de correction pour un sprint futur |
| `ADD-CE1-R2` - `48 + 26` seul item avec retenue sans distracteur portant la retenue oubliee | Pedagogie | `nadia-prof`, valeur de remplacement deja donnee (section 7.2) | **Ouverte, non bloquante** - matiere a story de correction pour un sprint futur |
| Libelle d'ecran de fin *"Tu as trouve, tout seul :"* | Produit (transverse, hors perimetre de `S37-1`) | `camille-po` | **Route, hors perimetre de ce sprint** - constat entre en s35, non specifique a ce lot |
| `S36-ORDRE-NIVEAUX` livree entiere par `theo-archi` en meme temps que son avis de faisabilite | Gouvernance (deviation de process) | constate par moi a la fusion | **Non bloquant, verifie sans consequence** - lot livre, teste, porte technique verte des l'avis de faisabilite ; aucune action de developpement due |

## 7. Le registre de dette - inchange a la cloture de s37

**Ouverture de s37 : 9,25** (`squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/review.md`,
section 7). Verifie par recherche des identifiants de ce sprint (`ce1-additions-jusqu-a-100`,
`bandit-manchot-additions-ce1`, `MAT-CE1-03`, `OrdreDesNiveaux`, `S36-ORDRE-NIVEAUX`) dans
`DEC-0086` et `DEC-0087` : aucune occurrence dans les deux textes. Ni `S37-1` ni
`S36-ORDRE-NIVEAUX` ne sont des postes de la composition du Plan 2 : le premier ouvre une ligne
neuve hors du vivier `DEC-0086`/`DEC-0087`, le second est une story transverse sans contenu
pedagogique. **Registre de dette a l'ouverture de s38 : 9,25, inchange.**

## 8. La couverture du programme

**77/155 (49,7 %), inchangee par ce sprint.** Un seul mouvement de statut : `MAT-CE1-03` passe de
`non couvert` a `en cours` (`squad/programme/MATRICE-couverture.md`, note du 2026-09-22). Un
mouvement `non couvert -> en cours` ne touche pas le numerateur (`couvert-valide` seul) : le taux
reste arithmetiquement identique, verifie par `camille-po` sur son propre diff (`couvert-valide` 77
avant et apres, `en cours` 0 -> 1, `non couvert` 78 -> 77, total et denominateur inchanges).

**Gap preexistant signale, non corrige a ce sprint** : `MAT-CE1-18` (sens de la division), ajoutee
au referentiel `squad/programme/mathematiques/competences-ce1.md` a s36, n'a toujours aucune ligne
dans `squad/programme/MATRICE-couverture.md`. Verifie par moi (`grep -n "MAT-CE1-18"` sur la
matrice : aucune occurrence). Ce n'est pas une regression de ce sprint - la ligne manquait deja a
la cloture de s36 - et ce n'est pas corrige ici : `camille-po` l'a signale en fusionnant son propre
lot plutot que de la corriger hors du perimetre qui lui etait confie a ce sprint. Reporte comme
dette dans `retro.md`.

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : le verdict `VALIDE SOUS RESERVE` de `S37-1`
  (`nadia-prof`, `FICHE-bandit-manchot-additions-ce1.md`) et la confirmation sans Done pour
  `S36-ORDRE-NIVEAUX` (meme fiche, section finale) sont deja prononces par `nadia-prof` ; cette
  review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po` (`MAT-CE1-03`).
- Elle ne corrige pas le gap `MAT-CE1-18` : c'est un document de `camille-po`, la correction lui
  revient a un sprint ou elle est affectee a ce lot.
- Elle ne tranche aucune priorite de sprint futur : `ADD-CE1-R1`, `ADD-CE1-R2` et le libelle
  transverse d'ecran de fin restent des elements de backlog, a composer par `camille-po`.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** Les decisions du sprint ont ete
prises ailleurs et sont enregistrees ici pour memoire, chacune verifiee physiquement presente sur
le disque avant d'etre citee :

- `DEC-0089` - ouverture du perimetre CE1 (`camille-po`, s36) - **Acceptee**, non modifiee ce
  sprint. Verifiee dans `squad/decisions/produit/DEC-index.md`.
- `DEC-0090` - l'ordre des niveaux affiches dans l'en-tete devient une propriete garantie
  (`camille-po`, s36) - **Acceptee**, non modifiee ce sprint. Verifiee dans
  `squad/decisions/produit/DEC-index.md`.
- `ADR-0095` - l'ordre des niveaux est une cle de tri et tout niveau servi est nomme dans la
  sequence (`theo-archi`, 2026-09-21) - **Accepte**. Verifie dans `squad/decisions/adr/ADR-index.md`
  (section 3 ci-dessus).
- `ADR-0096` - la repartition par forme est la regle, le paquet statique est sa consequence
  (`theo-archi`, 2026-09-21) - **Accepte**. Verifie dans `squad/decisions/adr/ADR-index.md`
  (section 3 ci-dessus).

**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
