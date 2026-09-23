# Review - s40 - couverture maximale sur six lignes candidates (EMC, histoire des arts, sciences)

- **Horodatage de debut** (`planning.md`) : 2026-09-23 09:05 (UTC+02:00). Premier commit du sprint,
  `a32fa316` (planning), horodate 2026-09-23 09:08:55 (UTC+02:00).
- **Horodatage de fin** : 2026-09-23 14:23:30 (UTC+02:00, horodatage du dernier commit `3aad142a`).
- **Duree effective** : 5 h 18 min (de l'horodatage de debut a l'horodatage de fin).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `3aad142a`, `git status --porcelain` verifie vide. Vingt-deux
  commits dans la fenetre `49f6539c..HEAD` (`git log --oneline 49f6539c..HEAD`, mesure brute),
  aucun d'entre eux une fusion - chaque lot a ete integre par une chaine de commits directs, sans
  merge commit, contrairement aux sprints precedents.

---

## 1. L'increment livre, en une phrase

Six lignes du programme passent de `non couvert` a `couvert-valide` (`SCI-CM1-09` nutrition CM1,
`EMC-CM1-04` regles de la vie collective CM1, `HDA-CM1-05` relier une oeuvre a son contexte CM1 -
premiere ligne d'histoire des arts du produit -, `SCI-CM1-20` extraire une information CM1 en
couverture partielle texte et tableau, `EMC-CP-09` la devise CP sous reserve, `EMC-CM1-08`
distinguer une regle d'une loi CM1 apres un refus corrige dans le sprint) ; la couverture du
programme passe de 82/155 (52,9 %) a **88/155 (56,8 %)**.

## 2. Le Done technique - quatre jobs, verifies un par un, sur l'arbre final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un agent
qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres. Les quatre
jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur `round-two` a
`3aad142a` (`git status --porcelain` vide avant et apres) :

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** (celui que la commande resout, pas la toolchain declaree) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (19 cas, tous verts) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s 49f6539c..HEAD \| bash .github/scripts/verifier-prefixes.sh` (22 commits) | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, un par un | **vert**, **9 juges purs executes**, 0 echec |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, blocs `[INFO]
Results:` par module** (methode imposee par `squad/README.md` section 4 depuis l'amendement de
s11) :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 850 |
| recreo-bootstrap (surefire, unitaires) | 277 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1664** |
| **Total integration** | **6** |

**1670 tests, 0 echec, 0 erreur, 0 ignore.** Ce chiffre correspond exactement au compte autoritaire
de `yanis-qa` (`rapport-qualite.md`, section 1), mesure par lui sur `s40-yanis` a `652cafc8` avant
les six derniers commits de cloture (amendement `ADR-0099`, correction `S40-LOI`, deux Done, une
fermeture de matrice, une relecture) - aucun d'entre eux ne touche de fichier Java ni de contenu
YAML servi hors des deux lignes que la fiche de `S40-LOI` et la relecture citent explicitement,
aucun ecart attendu ni trouve entre les deux mesures.

**ArchUnit, recompte par moi de facon independante** (occurrences litterales de `static final
ArchRule` et `@ArchTest` sous `src/test/java` des cinq modules du pom agregateur, `target/` exclu -
methode d'`ADR-0095`/`ADR-0098`/`ADR-0099`) : **36 `static final ArchRule`, 35 `@ArchTest`** -
inchange depuis s39. Confirme ce qu'`ADR-0099` ecrit : aucune regle ArchUnit neuve, les objets
juges par ses quatre garde-fous (un YAML, un record du domaine, un document HTML servi) ne sont pas
modelises par ArchUnit.

## 3. La confirmation ArchUnit et les decisions d'architecture

`theo-archi` a ecrit `ADR-0099` pendant ce sprint. Verifie par moi, sur le disque :

- `squad/decisions/adr/ADR-0099-un-document-tableau-est-une-composante-structuree-et-une-ligne-y-
  porte-exactement-les-cellules-de-son-en-tete.md` porte `Statut : Accepte`.
- L'entree figure dans `squad/decisions/adr/ADR-index.md`.
- Un amendement date du 2026-09-23, dans le meme fichier, corrige deux garde-fous que le premier
  paquet reel a grille (`S40-EXTRACTION`, `lea-dev`) a trouves faux rouges le jour meme de leur
  ecriture : une clause *"seulement si"* ecrite comme un *"jamais"*, et l'ecran de revelation omis
  des valeurs de contenu lues par `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`. Les deux
  corrections sont mutees sur le materiau reel du paquet de `lea-dev` par `yanis-qa` (section 4),
  independamment de l'affirmation de `theo-archi`.

`ADR-0099` porte une septieme composante optionnelle de `Question` (`TableauSupport`), route par
`DEC-0094` sur la question de perimetre que `theo-archi` avait posee sans la trancher dans son avis
de faisabilite. Aucun contenu ni affectation n'y est fixe.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s40-yanis`, `git rev-parse --abbrev-ref HEAD`
verifie egal a `s40-yanis`, arbre `652cafc8`) : Done technique confirme sur `build` et les trois
autres jobs, tous nommes individuellement. Trois garde-fous mordus par mutation sur le materiau
**reellement servi**, jamais une fixture, restauration verifiee par `git status --short` vide apres
chaque mutation :

- `ADR-0099` decision 3, clause *"seulement si"* (`<th scope="col">` remplace par `<td>` dans
  `manche.html`) : rouge mute, 4 echecs sur 8 ; vert restaure.
- `ADR-0099` amendement, les cellules d'une grille comme valeurs de contenu (clause retiree
  d'`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`) : rouge mute, 2 echecs sur 3, 68 griefs
  sur le paquet reel ; vert restaure.
- `PAR-5` (ligne d'allocation `HDA-1` retiree de `REGISTRE-identifiants.md`) : rouge mute, 1 echec
  sur 1, message nommant l'item et l'identifiant ; vert restaure.

Aucun verrou `mvnd` transitoire rencontre sur ce worktree, ni sur ma propre verification technique
finale (section 2).

## 5. Le `relecteur-pedagogique`

`relecture.md` n'existe pas comme fichier dedie a s40 : les corrections ont ete portees directement
dans un commit de cloture (`3aad142a`, `[REL]`), verifie sur le disque. Trois corrections, toutes
verifiees par moi contre le diff reel :

- `cm1-emc-regle-loi.yaml` (item 11) et ses deux citations verbatim dans
  `FICHE-bandit-manchot-regle-loi-cm1.md` : *"croire une religion"* corrige en *"croire en une
  religion"*.
- `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` : titre de section 2 rejoint sur une
  seule ligne Markdown, qui en tronquait le rendu.
- `rapport-qualite.md` : *"les quatre mutations de la section 3"* corrige en *"les trois
  mutations"* (la section n'en documente que trois, 3.1 a 3.3).

Le reacteur complet a ete reverifie vert apres correction, cite dans le message de commit. La
correction de l'item 11 touche du contenu servi a un enfant (`cm1-emc-regle-loi.yaml`), deja
audite et valide par `nadia-prof` avant cette correction purement orthographique - je verifie que
le sens et les cinq bonnes reponses ne changent pas : *"personne ne peut obliger un enfant a
croire une religion"* et *"croire en une religion"* portent la meme proposition, seule la
preposition manquante est ajoutee.

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Objectif de couverture maximale sur six lignes candidates recu hors depot (forme 3), ne vaut pas motif tant qu'il n'est pas formalise | Gouvernance / produit | `camille-po` (route par `planning.md`) | **Resolu** - `DEC-0093`, Acceptee, meme reserve de sourcage que `DEC-0091`/`DEC-0092` |
| Mon propre premier commit de planning (`a32fa316`) n'etait pas encore fusionne sur `round-two` au moment ou j'ai ouvert le worktree de `camille-po` - l'amendement de s39 ("Sofia ouvre elle-meme le worktree de l'agent qu'elle route") suppose implicitement que ma propre ceremonie est deja sur `round-two`, ce que le texte de l'amendement ne dit pas | Gouvernance (process, mon propre geste sous casquette SM) | Constate par moi-meme, corrige par l'orchestrateur avant que `camille-po` ne commence (worktree recree a jour) | **Resolu, sans aucune perte ni aucun cout** - voir `retro.md`, amendement propose |
| `S40-HDA` (`HDA-CM1-05`) souleve `HIS-2` (contexte d'oeuvre a plusieurs lectures defendables), non tranche par aucun arbitrage existant | Pedagogie | `nadia-prof` (route par `DEC-0093`) | **Resolu** - `ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md`, criteres `HDA-1` a `HDA-4` |
| `S40-EXTRACTION` (`SCI-CM1-20`) : la moitie tableau du critere d'acceptation est infaisable sans support structure dans le produit ; question de perimetre (texte seul ou les deux formes) non tranchee par l'avis de faisabilite | Architecture, puis produit | `theo-archi` signale, `camille-po` tranche le perimetre | **Resolu** - `DEC-0094` (les deux formes sont exigees), `ADR-0099` (support tableau) |
| `PAR-5` : les identifiants `HDA-1` a `HDA-4`, ecrits par `nadia-prof` dans son arbitrage prealable, n'ont pas ete alloues dans `REGISTRE-identifiants.md` dans le meme commit - reacteur rouge sur ce garde-fou | Gouvernance (discipline d'identifiants, `ADR-0085` clause 2) | Trouve par `theo-archi` (pas cause par lui), signale par l'orchestrateur, corrige par `nadia-prof`, seule auteure legitime de l'allocation | **Resolu** - `99964517`, ligne d'allocation ajoutee, mordue et restauree par `yanis-qa` (section 4) |
| Deux garde-fous d'`ADR-0099`, ecrits le jour meme par `theo-archi`, refusaient a tort le premier paquet reel a grille de `lea-dev` (population de grilles servies nulle au moment de leur ecriture, donc indistinguable entre deux lectures possibles de la clause) | Architecture / qualite | Signale par `lea-dev` (mis en `git stash`, non patche elle-meme - discipline de role tenue), diagnostique et corrige par `theo-archi` | **Resolu** - amendement d'`ADR-0099` (`1229a516`), confirme faux rouge des deux cotes, contenu de `lea-dev` inchange, morsures verifiees par `yanis-qa` (section 4) |
| `S40-LOI` (`EMC-CM1-08`) refuse au premier audit pedagogique : le verbe de reponse attendu etait deja ecrit dans l'enonce de cinq items sur cinq de la tache B | Pedagogie | `nadia-prof` (arbitre), `marc-dev` (corrige) | **Resolu** - second audit **VALIDE**, un risque nouveau ouvert par la reformulation (confusion "protege"/"autorise") controle specifiquement et leve |
| `MAT-CE1-18` (sens de la division) toujours sans ligne dans `MATRICE-couverture.md` | Gouvernance / produit | signale une cinquieme fois par `camille-po` (`planning.md`, section 2), non corrige | **Ouvert, non corrige - cinquieme sprint consecutif (s36, s37, s38, s39, s40)** |

## 7. Le registre de dette - inchange a la cloture de s40

**Ouverture de s40 : 9,25** (`squad/sprints/s39-couverture-maximale-six-lignes-candidates/review.md`,
section 7). Verifie par moi : recherche des identifiants de ce sprint (`S40-HDA`,
`S40-EXTRACTION`, `S40-NUTRITION`, `S40-REGLES`, `S40-LOI`, `S40-DEVISE`, `DEC-0093`, `DEC-0094`,
`ADR-0099`) dans `DEC-0086` et `DEC-0087` : aucune occurrence dans les deux textes. Ce sprint
ouvre une ligne hors du vivier `DEC-0086`/`DEC-0087`, comme les sprints precedents de couverture
maximale. **Registre de dette a l'ouverture d'un sprint futur : 9,25, inchange.**

## 8. La couverture du programme

**88/155 (56,8 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md` (section
"Synthese apres les verdicts du sprint 40", recompte independant sur les dix-neuf tableaux de la
matrice) : six fermetures (`SCI-CM1-09`, `EMC-CM1-04`, `HDA-CM1-05`, `SCI-CM1-20`, `EMC-CP-09`,
`EMC-CM1-08`, toutes `non couvert` -> `couvert-valide`), aucune ouverture partielle.
`couvert-valide` 82 -> 88, `en cours` inchange a 2, `non couvert` 71 -> 65, `non couvrable en
l'etat` inchange a 71, total 226, denominateur atteignable 155 inchange.

**Gap preexistant, non corrige a ce sprint, cinquieme sprint consecutif** : `MAT-CE1-18` (sens de
la division), ajoutee au referentiel `squad/programme/mathematiques/competences-ce1.md` a s36,
n'a toujours aucune ligne dans `squad/programme/MATRICE-couverture.md` (recherche faite,
`grep -n "MAT-CE1-18"` sur la matrice : aucune occurrence). Trouve par `nadia-prof` a s36, signale
a s37, s38 et s39, signale une cinquieme fois dans `planning.md` de ce sprint (section 2) sans
devenir un motif engage. Reporte en retro.

## 9. L'avis de `nadia-prof` et le Done pedagogique - rapporte, pas prononce par cette review

Le Done pedagogique de ce sprint est prononce dans six fiches de conformite, que cette review
rapporte sans le rendre a sa place :

| Ligne | Verdict | Fiche |
|---|---|---|
| Nutrition CM1 (`SCI-CM1-09`) | **VALIDE, sans reserve** - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-nutrition-cm1.md` |
| Regles de la vie collective CM1 (`EMC-CM1-04`) | **VALIDE, sans reserve** - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-regles-vie-collective-cm1.md` |
| Relier une oeuvre a son contexte CM1 (`HDA-CM1-05`) | **VALIDE, sans reserve** - `couvert-valide`, premiere ligne d'histoire des arts du produit | `squad/conformite/FICHE-bandit-manchot-histoire-des-arts-relier-contexte-cm1.md` |
| Extraire une information CM1 (`SCI-CM1-20`) | **VALIDE, sans reserve**, couverture partielle (texte et tableau seuls) - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-sciences-extraire-information-cm1.md` |
| Devise CP (`EMC-CP-09`) | **VALIDE SOUS RESERVE** (`DVS-CP-R1`, non bloquante : verbe *illustre* trop soutenu) - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-devise-cp.md` |
| Regle ou loi CM1 (`EMC-CM1-08`) | **REFUSE au premier audit**, **corrige par `marc-dev`**, **VALIDE au second audit** - `couvert-valide` | `squad/conformite/FICHE-bandit-manchot-regle-loi-cm1.md` |

L'audit a ete mene reacteur construit, jar lance sur le JDK 21 de la toolchain, chaque partie jouee
a l'ecran, tous les items des six paquets releves un par un. Le refus de `EMC-CM1-08` a ete trouve
en jouant (le mot-reponse etait deja ecrit dans l'enonce de la tache B), pas en lisant le YAML
seul ; la correction a ete rejouee et un risque nouveau ouvert par la reformulation (confusion
"protege"/"autorise" entre deux items) a ete controle specifiquement avant de valider.

## 10. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les six verdicts de `nadia-prof` (section 9) sont deja
  prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po`.
- Elle ne corrige pas le gap `MAT-CE1-18` : c'est un document de `camille-po`, la correction lui
  revient a un sprint ou elle est affectee a ce lot.
- Elle ne tranche pas si l'obstacle de la section 6 (ma propre ouverture de worktree) appelle un
  amendement de process au-dela de son constat : la question est traitee en retro, pas ici.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** Les decisions du sprint ont ete
prises ailleurs et sont enregistrees ici pour memoire, chacune verifiee physiquement presente sur
le disque, avec son statut, avant d'etre citee :

- `DEC-0093` - objectif et perimetre de s40 (`camille-po`, 2026-09-23) - **Acceptee**. Verifiee
  dans `squad/decisions/produit/DEC-index.md` et sur le disque.
- `DEC-0094` - `SCI-CM1-20` exige texte et tableau ensemble, l'`ADR` de support tableau route a
  `theo-archi` (`camille-po`, 2026-09-23) - **Acceptee**. Verifiee dans le meme index.
- `ADR-0099` - un document tableau est une composante structuree d'item (`theo-archi`, 2026-09-23,
  amende le meme jour) - **Accepte**. Verifie dans `squad/decisions/adr/ADR-index.md`.

**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
