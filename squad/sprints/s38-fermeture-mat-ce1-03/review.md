# Review - s38 - fermeture de MAT-CE1-03

- **Horodatage de fin** : 2026-09-22 14:15 (UTC+02:00).
- **Horodatage de debut** (`planning.md`) : 2026-09-22 08:56 (UTC+02:00).
- **Duree effective** : 5 h 19 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `847a68a9`, `git status --porcelain` verifie vide avant et apres
  cette review. Dix-huit commits dans la fenetre `686665f1..HEAD` (`git rev-list --count`), quatre
  d'entre eux des fusions (`283a144f` Marc, `65f03285` Lea, `e98f734b` Nadia, `847a68a9` Camille).

---

## 1. L'increment livre, en une phrase

`MAT-CE1-03` (calcul mental CE1 - additions, soustractions, complements a la dizaine, moities)
passe de `en cours` a `couvert-valide` : ses quatre pieces detiennent chacune un Done pedagogique
de `nadia-prof` (trois **VALIDE**, une **VALIDE SOUS RESERVE** non bloquante), la couverture du
programme passe de 77/155 (49,7 %) a 78/155 (50,3 %).

## 2. Le Done technique - quatre jobs, verifies un par un, sur l'arbre fusionne final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un
agent qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres.
Les quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur
`round-two` a `847a68a9` (`git status --porcelain` vide avant et apres).

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, un par un | **vert**, **9 juges purs executes**, 0 echec |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` et `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` (base : `686665f1`, aucun commit du sprint precedent inclus) | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, blocs `[INFO]
Results:` par module** (methode imposee par `squad/README.md` section 4 depuis l'amendement de
s11) :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 762 |
| recreo-bootstrap (surefire, unitaires) | 265 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1555** |
| **Total integration** | **6** |

**1561 tests, 0 echec, 0 erreur, 0 ignore.** Ce chiffre correspond exactement au compte autoritaire
de `yanis-qa` (`rapport-qualite.md`, section 1 : memes 1555 unitaires + 6 IT, methode blocs
`Results:` recoupee par la somme des attributs `tests=` des rapports XML), mesure par lui sur
`s38-yanis` a `65f03285` avant les deux dernieres fusions (Nadia, Camille) qui ne touchent aucun
fichier Java ni YAML de contenu - aucun ecart attendu ni trouve entre les deux mesures.

**ArchUnit, recompte par moi sur l'arbre fusionne, methode d'`ADR-0095`/`ADR-0096`/`ADR-0097`
(occurrences litterales dans `src/test/java` des cinq modules du pom agregateur, `target/`
exclu)** : `static final ArchRule` **36**, `@ArchTest` **35** - inchange depuis la cloture de s37.
Confirme ce que `theo-archi` ecrit dans `ADR-0097` : aucune regle ArchUnit neuve, la population de
ce sprint est du YAML et des descripteurs de contenu, jamais du bytecode.

## 3. La confirmation ArchUnit et les decisions d'architecture

`theo-archi` a accepte `ADR-0097` pendant son propre travail sur ce sprint, avant la cloture.
Verifie par moi, sur le disque :

- `squad/decisions/adr/ADR-0097-la-sous-partie-d-un-paquet-de-mathematiques-de-ce1-est-declaree-et-allouee-le-paquet-statique-reste-le-mecanisme.md`
  porte `Statut : Accepte`.
- L'entree figure dans `squad/decisions/adr/ADR-index.md` avec sa methode citee dans la meme
  ligne. `theo-archi` y note explicitement, sur son propre worktree, n'avoir lance que `build` et
  `verifier-chemins.sh` - pas `prefixes` ni `outils` - et ne pas les avoir annonces pour autant :
  exactement la discipline que l'amendement du 2026-09-08 a `CLAUDE.md` demande. Les quatre jobs
  sont neanmoins verifies verts sur l'arbre fusionne final par moi, section 2 ci-dessus.

`ADR-0096` (s37) reste en vigueur, non amende ce sprint ; `ADR-0097` en precise le perimetre pour
trois paquets neufs sans le contredire (section 8 de l'avis de faisabilite de s38, section 1 de
l'`ADR`).

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s38-yanis`, `git rev-parse --abbrev-ref HEAD`
verifie egal a `s38-yanis`, arbre `65f03285`) : Done technique confirme sur `build` et les trois
autres jobs, tous nommes individuellement. Deux garde-fous neufs mordus par mutation sur le
materiau **reellement servi**, jamais une fixture, fichier restaure et `git status --short` verifie
vide apres chaque mutation :

- espace insecable U+00A0 devant un point d'interrogation (`ce1-moities.yaml`) : rouge mute,
  1 echec ; vert restaure.
- second distracteur `d` (formule d'origine de l'arbitrage, avant l'amendement `d-a-1`/`d-a+1`) sur
  `ce1-complements-dizaine.yaml` : rouge mute avec **exactement les deux echecs predits par le
  calcul** de `theo-archi` (rang "la plus grande" a 0,0 %, strategie a rang constant a 55,6 %) ;
  vert restaure.

Aucun test instable observe. Les quatre classes de garde-fou derive neuves du sprint ne sont pas
re-mutees individuellement au-dela de ces deux mutations, faute de temps sur ce sprint -
`yanis-qa` le dit explicitement plutot que de le taire, et le signale comme priorite si un doute se
manifeste sur l'un des trois lots restants.

## 5. Le `relecteur-pedagogique`

**Une activite est mesuree, mais aucune conclusion n'est ecrite sur le disque.**
`RAPPORT-tokens.md` (section suivante) montre 72 tours de `relecteur-pedagogique` dans la fenetre
de ce sprint - il a donc bien ete invoque. Mais aucun commit `[REL]` n'existe dans
`686665f1..HEAD`, aucune branche ni worktree `s38-relecture*` ou `s38-rel*` (`git branch -a`,
`git worktree list`), et aucun fichier de relecture n'existe dans
`squad/sprints/s38-fermeture-mat-ce1-03/`. Une relecture qui ne trouve rien a corriger ne produit
naturellement aucun diff de contenu, donc aucun commit - mais rien sur le disque ne dit non plus
*qu'elle a conclu cela*, ni ce qu'elle a couvert. Le seul document qui atteste l'invocation est un
compteur de tokens, qui ne porte aucune conclusion par construction. Je ne peux donc pas, a partir
du depot seul, distinguer "relu integralement, rien a corriger" de "relu partiellement, rien
ecrit" : je constate l'ecart entre l'activite mesuree et l'absence d'artefact de conclusion,
plutot que de recopier un rapport verbal qui comble cet ecart pour moi. Repris en retro, section 2.

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Objectif de fermeture de `MAT-CE1-03` recu hors depot (forme 3), ne vaut pas motif tant qu'il n'est pas formalise | Gouvernance / produit | `camille-po` (route par `planning.md`) | **Resolu** - `DEC-0091` (`b480cfae`), Acceptee, reserve de sourcage nommee explicitement en section 3 |
| `S38-COMPLEMENTS-DIZAINE` infaisable en l'etat : le second distracteur prescrit (`d`) rend la bonne reponse jamais la plus grande, `DETTE_DE_SEUIL_MINIMUM` deja pleine (3 noms) | Pedagogie (forme d'un distracteur) | `nadia-prof` (route par moi, `routage-complements-dizaine-et-adr-mecanisme.md`) | **Resolu** - amendement du 2026-09-22 (`52d899ad`) : second distracteur `d-a-1` / repli `d-a+1`, neuf valeurs verifiees a la main |
| Aucun `ADR` en vigueur ne couvre le mecanisme statique pour `S38-SOUSTRACTIONS` et `S38-MOITIES` (`ADR-0096` ne tranchait que le premier paquet CE1) | Architecture | `theo-archi` (route par moi, meme fichier) | **Resolu** - `ADR-0097` (`8b4ddc3e`), Accepte, regle executable ecrite dans le meme geste |
| **La regle du worktree dedie a chaque premiere ecriture (`squad/README.md` section 9, "sans exception", deja amendee a s06, s18 et s21/s22) a de nouveau ete contournee.** Les six premiers commits de `camille-po`, `theo-archi`, `nadia-prof` et moi-meme sur ce sprint (`0c215ee4`, `11da48d6`, `b480cfae`, `71c79ba3`, `d945d3cf`, `52d899ad`, `8b4ddc3e`) sont tous des commits directs sur `round-two`, sans aucune fusion - verifie par l'absence de tout commit de merge les portant, a la difference de `s37` ou ces memes types d'agents (PO, architecte, prof) avaient chacun leur propre worktree fusionne separement | Gouvernance (discipline de process, deja ecrite) | Constate par moi a la review ; aucune perte de contenu (chaque commit existe et est complet) | **Non bloquant, verifie sans consequence de contenu - mais recurrence, voir retro section 2** |
| Activite de `relecteur-pedagogique` mesuree (72 tours) mais sans artefact de conclusion sur le disque pour s38 | Gouvernance (tracabilite) | `relecteur-pedagogique` | **Ouvert** - invocation confirmee, conclusion ni confirmee ni infirmee par le depot, voir section 5 |
| `SOU-CE1-R1` - sur quatre items de la forme "un chiffre soustrait", le second distracteur ne correspond a aucune erreur identifiable | Pedagogie | `nadia-prof`, correctif deja donne (remplacer par `a + b`) | **Ouverte, non bloquante** - story de correction differee ecrite dans `docs/backlog/backlog.md` (`75b0ac85`), identifiant alloue (`REGISTRE-identifiants.md`, 666/108) |
| `MAT-CE1-18` (sens de la division) toujours sans ligne dans `MATRICE-couverture.md` | Gouvernance / produit | signale, non corrige, deuxieme fois par `camille-po` | **Ouvert, non corrige - troisieme sprint consecutif (s36, s37, s38)**, voir section 8 et retro |

## 7. Le registre de dette - inchange a la cloture de s38

**Ouverture de s38 : 9,25** (`squad/sprints/s37-premier-paquet-mathematiques-ce1-et-ordre-des-niveaux/review.md`,
section 7). Verifie par moi : recherche des identifiants de ce sprint (`MAT-CE1-03`,
`S38-CORR`, `S38-SOUSTRACTIONS`, `S38-COMPLEMENTS-DIZAINE`, `S38-MOITIES`, `DEC-0091`, `ADR-0097`)
dans `DEC-0086` et `DEC-0087` : aucune occurrence dans les deux textes (`grep`, exit 1 sur les
deux fichiers). Ni la fermeture de `MAT-CE1-03` ni sa gouvernance de sprint ne sont des postes de
la composition du Plan 2 : ce sprint ouvre une ligne hors du vivier `DEC-0086`/`DEC-0087`, comme
`S37-1` avant lui. **Registre de dette a l'ouverture d'un sprint futur : 9,25, inchange.**

## 8. La couverture du programme

**78/155 (50,3 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, section
"Fermeture de `MAT-CE1-03` (s38)" : un seul mouvement de statut (`MAT-CE1-03`, `en cours` ->
`couvert-valide`), recompte independant fourni par `camille-po` sur les dix-neuf tableaux de la
matrice (`couvert-valide` 77 -> 78, `en cours` 1 -> 0, `non couvert` et `non couvrable en l'etat`
inchanges a 77 et 71, total 226, denominateur atteignable 155 inchange).

**Gap preexistant, non corrige a ce sprint, troisieme sprint consecutif** : `MAT-CE1-18` (sens de
la division), ajoutee au referentiel `squad/programme/mathematiques/competences-ce1.md` a s36,
n'a toujours aucune ligne dans `squad/programme/MATRICE-couverture.md`. Verifie par moi
(`grep -n "MAT-CE1-18"` sur la matrice : aucune occurrence). Signale a s36 (trouve par
`nadia-prof`), a s37 (signale de nouveau par `camille-po` sans la corriger), et de nouveau non
engage a s38 - aucune story, aucune affectation. Reporte en retro, section 2, comme motif qui se
repete significativement.

## 9. L'avis de `nadia-prof` et le Done pedagogique - rapporte, pas prononce par cette review

Le Done pedagogique de ce sprint est prononce dans quatre fiches de conformite, que cette review
rapporte sans le rendre a sa place :

| Piece de `MAT-CE1-03` | Verdict | Fiche |
|---|---|---|
| Additions (`bandit-manchot-additions-ce1`) | **VALIDE, sans reserve** - `ADD-CE1-R1` et `ADD-CE1-R2` (reserves de s37) fermees apres reaudit | `squad/conformite/FICHE-bandit-manchot-additions-ce1.md`, section 11 |
| Soustractions (`bandit-manchot-soustractions-ce1`) | **VALIDE SOUS RESERVE** - reserve non bloquante `SOU-CE1-R1` | `squad/conformite/FICHE-bandit-manchot-soustractions-ce1.md`, section 4 |
| Complements a la dizaine (`bandit-manchot-complements-dizaine-ce1`) | **VALIDE, sans reserve** | `squad/conformite/FICHE-bandit-manchot-complements-dizaine-ce1.md` |
| Moities (`bandit-manchot-moities-ce1`) | **VALIDE, sans reserve** | `squad/conformite/FICHE-bandit-manchot-moities-ce1.md` |

Sur les moities, `nadia-prof` a trouve et corrige elle-meme un trou de son propre arbitrage (le
distracteur "moitie moins deux" depassait la lettre de sa clause 3.4 d'origine, qui n'aurait
rendu conforme aucun paquet possible) plutot que de le laisser peser sur l'increment livre par
`lea-dev`, qui l'avait anticipe correctement - meme discipline que celle deja notee pour d'autres
agents a s35, s36 et s37 (`review.md` de s37, "Ce qui a marche").

## 10. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les quatre verdicts de `nadia-prof` (section 9) sont
  deja prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement
  deja execute par `camille-po`.
- Elle ne corrige pas le gap `MAT-CE1-18` : c'est un document de `camille-po`, la correction lui
  revient a un sprint ou elle est affectee a ce lot.
- Elle ne confirme ni n'infirme la relecture pedagogique de s38 (section 5) : le depot ne le dit
  pas, et je ne le fabrique pas.
- Elle ne tranche aucune priorite de sprint futur : `SOU-CE1-R1` et `MAT-CE1-18` restent des
  elements de backlog, a composer par `camille-po`.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** Les decisions du sprint ont ete
prises ailleurs et sont enregistrees ici pour memoire, chacune verifiee physiquement presente sur
le disque avant d'etre citee :

- `DEC-0091` - objectif et regle de gouvernance de s38 (`camille-po`, 2026-09-22) - **Acceptee**.
  Verifiee dans `squad/decisions/produit/DEC-index.md` et sur le disque
  (`squad/decisions/produit/DEC-0091-objectif-et-gouvernance-de-s38-fermeture-de-mat-ce1-03.md`).
- `ADR-0097` - la sous-partie de `MAT-CE1-03` se declare au descripteur et s'alloue en un seul
  endroit ; les soustractions et les moities de CE1 sont des paquets statiques (`theo-archi`,
  2026-09-22) - **Accepte**. Verifie dans `squad/decisions/adr/ADR-index.md` (section 3
  ci-dessus).

**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
