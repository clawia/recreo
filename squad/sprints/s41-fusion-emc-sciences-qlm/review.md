# Review - s41 - fusion de deux sprints projetes (enseignement moral et civique, sciences et technologie CM1, questionner le monde CP)

- **Horodatage de debut** (`planning.md`) : 2026-09-24 09:15 (UTC+02:00).
- **Horodatage de fin** : 2026-09-24 12:45:30 (UTC+02:00, horodatage du dernier commit `6417c67f`).
- **Duree effective** : 3 h 30 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `6417c67f`, `git status --porcelain` verifie vide (a l'exception
  d'un fichier non suivi, `unmatched.log`, sans rapport avec ce sprint et non commite par personne
  durant s41 - je ne le touche pas, ce n'est pas mon perimetre). Fenetre `784421ed..6417c67f` :
  **30 commits** (`git rev-list --count`), dont **12 commits de fusion** et **18 commits de
  travail** (`git log --no-merges`) - a la difference de s40 (22 commits directs, aucune fusion),
  s41 a suivi la consigne de `planning.md` section 4 : *"chaque developpeur fusionne son lot des
  qu'il est vert plutot que d'accumuler les six en parallele sur une meme branche"*, sur deux
  terrains et deux instances paralleles de `nadia-prof` (`DEC-0095`).

---

## 1. L'increment livre, en une phrase

Six lignes du programme passent de `non couvert` a `couvert-valide` (`EMC-CP-08` symboles de la
Republique, `EMC-CP-10` droits de l'enfant sous reserve, `EMC-CP-13` roles de l'ecole et de la
commune, `QLM-CP-04` regles d'hygiene de vie, `QLM-CP-13` mois et saisons, `SCI-CM1-06` classer les
etres vivants en couverture partielle) ; la couverture du programme passe de 88/155 (56,8 %) a
**94/155 (60,6 %)**.

## 2. Le Done technique - quatre jobs, relances individuellement par moi, sur l'arbre final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un agent
qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres. Les quatre
jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur `round-two` a
`6417c67f` (`git status --porcelain` verifie vide avant et apres, hors `unmatched.log`) :

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** (celui que la commande resout, pas la toolchain declaree) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (22 cas, tous verts) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s 784421ed..HEAD \| bash .github/scripts/verifier-prefixes.sh` (18 commits) | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, exactement comme le workflow l'ecrit | **vert**, **9 juge(s) pur(s) execute(s)**, 0 echec |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, blocs `[INFO]
Results:` par module** (methode imposee par `squad/README.md` section 4 depuis l'amendement de
s11) :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 881 |
| recreo-bootstrap (surefire, unitaires) | 277 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1695** |
| **Total integration** | **6** |

**1701 tests, 0 echec, 0 erreur, 0 ignore.** Ce chiffre est identique, module par module, a celui
de `yanis-qa` (`rapport-qualite.md`, section 1, mesure sur `s41-yanis` a `b5b07966`) et a celui de
`theo-archi` apres sa propre correction (`confirmation-archunit.md`, section 1, mesure sur
`s41-theo-confirmation` a `b5b07966` puis reconfirme a `a86234ed`) : les six derniers commits de
cloture (deux corrections de contenu, quatre verdicts pedagogiques, une fermeture de matrice) ne
touchent aucun fichier Java ni aucun fichier de contenu YAML au-dela de ce que les fiches citent
explicitement - aucun ecart attendu ni trouve entre les trois mesures.

**ArchUnit, recompte par moi de facon independante** (occurrences litterales de `static final
ArchRule` et `@ArchTest` sous `src/test/java` des cinq modules du pom agregateur, `target/` et
l'arbre de contre-exemple exclus - methode d'`ADR-0095`/`ADR-0098`/`ADR-0099`) : **36 `static final
ArchRule`, 35 `@ArchTest`** - inchange depuis s40. Confirme ce que `theo-archi` ecrit
(`confirmation-archunit.md`, section 3.1) : aucune regle ArchUnit neuve, les six lots ne portent
**aucune ligne de Java de production** - uniquement des fichiers de contenu YAML et des tests de
paquet.

## 3. La confirmation ArchUnit de `theo-archi`

`squad/sprints/s41-fusion-emc-sciences-qlm/confirmation-archunit.md` verifie, sur l'arbre integre
des six lots, trois questions posees par son propre avis de faisabilite (`eec116b1`) : les tests
ArchUnit restent verts sur le code neuf (oui, et **zero ligne de Java de production** sur les six
lots) ; le verdict *"aucun `ADR`, aucune regle ArchUnit neuve"* tient (oui, compte inchange, aucun
`ADR` ecrit) ; `BIN-1` sur `S41-SCI-CLASSER` respecte le patron attendu, **prouve par mutation sur
le contenu reel servi**, pas par lecture du test (section 4.2 : un item binaire fait rougir `V3`
avant `BIN-1`, six items binaires font rougir `BIN-1` avec le message exact *"au plus la moitie des
11 items du paquet portent deux propositions, or 6 les portent"*).

**Un ecart trouve par `theo-archi` lui-meme, corrige dans le meme fichier (section 1.1)** : sa
premiere mesure du compte de tests annoncait 1692, par une methode explicitement interdite depuis
l'amendement s11 (*"jamais la somme des lignes `Tests run:` des rapports `.txt`"*). L'ecart avec le
compte de `yanis-qa` (1701) l'a rendu visible a la fusion. Cause identifiee classe par classe : cinq
classes melangent un moteur ArchUnit et un moteur JUnit Jupiter dans le meme fichier `.java`,
Surefire n'ecrivant qu'un rapport `.txt` par classe et n'y gardant que le compte Jupiter. `theo-archi`
nomme lui-meme sa propre faute - avoir choisi comme temoin la seule classe du depot qui ne pouvait
pas reveler le defaut - et la corrige au meme geste (`aefdfaa3`). **Ce que cette correction ne
change pas** : le verdict des trois questions, le `BUILD SUCCESS`, les comptes de regles ArchUnit
(36/35, inchanges par construction), la preuve par mutation de `BIN-1`.

**Un second ecart, mineur et sans consequence sur le verdict**, corrige dans le meme fichier
(section 3.6) : l'avis de faisabilite annoncait trois fichiers partages par tous les lots de
contenu, le diff reel en porte quatre - le quatrieme
(`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`) n'a d'entree que pour les lots
de questionner le monde CP, absents du seul diff dont l'avis avait generalise l'inventaire.

**Aucun `ADR` ni aucune regle ArchUnit neuve n'est ecrit ce sprint.** `git diff --stat
eec116b1..6417c67f -- squad/decisions/adr/` rend un diff vide, verifie par moi independamment de
`theo-archi`.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s41-yanis`, lignee verifiee par
`outils/verifier-lignee.sh`, arbre `b5b07966`) : Done technique confirme sur les quatre jobs, tous
nommes individuellement, **aucun verrou `mvnd` transitoire rencontre**. Trois garde-fous mordus par
mutation sur le materiau **reellement servi**, restauration verifiee par `git status --short` vide
apres chaque mutation :

- `BIN-1` sur `PaquetCm1SciencesClasserEtresVivantsTest` (condition de faisabilite de `theo-archi`) :
  six des onze items rendus binaires -> rouge, `Failures: 3` ; restaure -> vert.
- `ART-1` sur `PaquetCpEmcSymbolesTest` (critere de `S41-EMC-SYMBOLES`) : le mot *bleu* ajoute a un
  distracteur -> rouge, message exact cite ; restaure -> vert.
- `PAR-5` / `ADR-0085` sur l'allocation de `BIN-1` dans `REGISTRE-identifiants.md` : ligne retiree
  -> rouge, message nommant le fichier et l'identifiant ; restauree -> vert.

Tracabilite des criteres d'acceptation des six stories, lot par lot (section 3 du rapport) : chaque
code de condition (`FER-1` a `FER-3`, `ART-1`, `BIA-4`, `EMC-1`, `EMC-6`, `BIN-1`) est verifie encode
dans un test nomme, avec ce qui reste garde de `nadia-prof` explicitement note dans le fichier de
test quand un motif n'est pas automatisable.

## 5. Le `relecteur-pedagogique`

**Aucune intervention du `relecteur-pedagogique` dans s41** : `git log 784421ed..6417c67f --grep
"\[REL\]"` ne rend aucun commit. A la difference de s40, ou trois corrections de redaction avaient
ete portees dans un commit de cloture, ce sprint n'en porte aucune. Je ne le constate pas comme un
manquement : rien dans les fiches de conformite ni dans le rapport qualite ne signale de defaut de
redaction (orthographe, accentuation, ponctuation) sur les six paquets - les seules reserves
ouvertes par `nadia-prof` (section 6 ci-dessous) portent sur le registre de vocabulaire et le sens,
pas sur la conformite de `CLAUDE.md`, "Conventions de redaction".

## 6. Le Done pedagogique - six verdicts, rapportes par cette review, pas prononces par elle

Deux instances paralleles de `nadia-prof` (`DEC-0095`, section 9), chacune bornee a 8,0/8,0 au
total du sprint. Le Done pedagogique est prononce dans six fiches de `squad/conformite/`, verifiees
sur le disque :

| Ligne | Instance | Trajectoire | Verdict final | Fiche |
|---|---|---|---|---|
| Symboles de la Republique (`EMC-CP-08`) | A | **REFUSE** (item 9/10 : reponse exacte sanctionnee, deux propositions vraies dans le meme item) -> corrige (`60cb9e34`) -> **REFUSE de nouveau** (le correctif payait la non-repetition mais pas la clause de verite, et introduisait un defaut neuf : "le drapeau represente le pays et pas la Republique") -> corrige (`8860e01c`) -> **VALIDE** (troisieme audit) | **VALIDE**, sans reserve - `couvert-valide` | `FICHE-bandit-manchot-symboles-cp.md` |
| Droits de l'enfant (`EMC-CP-10`) | A | Audite une fois, aucun refus | **VALIDE SOUS RESERVE** (deux reserves non bloquantes : le verbe *illustre*, un pronom sans antecedent) - `couvert-valide` | `FICHE-bandit-manchot-droits-enfant-cp.md` |
| Roles de l'ecole et de la commune (`EMC-CP-13`) | A | **REFUSE** (item 7 : fait inexact - le directeur "remplace les enseignants absents", role d'un enseignant remplacant) -> corrige (`bddd8233`) -> **VALIDE** (second audit) | **VALIDE**, sans reserve - `couvert-valide` | `FICHE-bandit-manchot-roles-ecole-commune-cp.md` |
| Regles d'hygiene de vie (`QLM-CP-04`) | B | Audite une fois, aucun refus | **VALIDE**, sans reserve (deux poteaux non bloquants plantes) - `couvert-valide` | `FICHE-bandit-manchot-hygiene-cp.md` |
| Mois et saisons (`QLM-CP-13`) | B | Audite une fois, aucun refus | **VALIDE**, sans reserve - `couvert-valide` | `FICHE-bandit-manchot-saisons-cp.md` |
| Classer les etres vivants (`SCI-CM1-06`) | B | **REFUSE** (trois enonces de milieu affirmaient comme fait ce que leur question demandait de nier ; le tetard compte comme etre vivant distinct de la grenouille, contradiction avec `SCI-CM1-08` deja servi) -> corrige (`bddd8233`) et critere d'acceptation de `camille-po` aligne sur le motif de refus (`2fe6846f`) -> **VALIDE** (second passage) | **VALIDE**, couverture partielle (mention exigee d'avance, reprise mot pour mot) - `couvert-valide` | `FICHE-bandit-manchot-classer-etres-vivants-cm1.md` |

**Trois refus sur six lots, deux resolus au premier correctif, un (`EMC-CP-08`) resolu au second.**
Aucun des trois refus n'a ete vu par la CI : les motifs sont tous des jugements semantiques (une
reponse exacte sanctionnee, un fait inexact, un enonce qui se contredit lui-meme) qu'aucun garde-fou
de ce depot ne rend, et `nadia-prof` le dit elle-meme dans chacune des trois fiches. L'audit a ete
mene jar reconstruit et relance a chaque passage, parties jouees par appels HTTP reels ou a l'ecran,
jamais une lecture seule du YAML.

## 7. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Fusion de deux sprints projetes en un seul, recue hors depot (forme 3), passage a deux instances de `nadia-prof`, perimetre porte a six lignes sur deux terrains | Gouvernance / produit | `camille-po` | **Resolu** - `DEC-0095`, Acceptee, amendee le meme jour section 9 |
| `EMC-CP-08` : deux items sanctionnaient une reponse exacte (contenue dans la bonne reponse couronnee) | Pedagogie | `nadia-prof` (arbitre), `marc-dev` (corrige) | **Resolu apres deux refus** - troisieme audit **VALIDE** (`21658be2`) ; le premier correctif payait la forme du defaut sans payer sa clause de verite, et ajoutait un defaut neuf, que `nadia-prof` nomme et corrige dans sa propre prescription avant de redemander |
| `EMC-CP-13` : un item enseignait un fait inexact sur le role du directeur d'ecole | Pedagogie | `nadia-prof` (arbitre), `lea-dev` (corrige) | **Resolu** - second audit **VALIDE** (`fda5563b` puis `4aef8cb1`) |
| `SCI-CM1-06` : trois enonces de milieu se contredisaient eux-memes, un item confondait deux stades du meme animal avec deux etres vivants distincts (contradiction avec `SCI-CM1-08` deja servi) | Pedagogie, et une part du defaut logee dans le critere d'acceptation lui-meme | `nadia-prof` (arbitre), `lea-dev` (corrige le contenu), `camille-po` (corrige le critere) | **Resolu** - second passage **VALIDE** (`fda5563b`) |
| `theo-archi` : sa propre premiere mesure du compte de tests (1692) reposait sur une methode interdite depuis s11, cinq classes mixtes ArchUnit/Jupiter en etant la cause | Qualite / mesure | Trouve par lui-meme en comparant a la mesure de `yanis-qa` (1701), corrige par lui-meme dans le meme fichier | **Resolu** - `aefdfaa3`, methode corrigee, cause nommee classe par classe, verdicts inchanges |
| `SEM-1` (referentiel mathematiques CP, `cp-semaine`) autorise "mois et leur ordre, saisons", en contradiction avec le referentiel de questionner le monde qui reserve ces objets a `QLM-CP-13` : quatre items de `cp-semaine` travaillent la meme competence que `QLM-CP-13` | Gouvernance / produit (double-compte entre deux paquets de matieres differentes) | `nadia-prof` signale, `camille-po` consigne (`MATRICE-couverture.md`, note du 2026-09-24) | **Ouvert, non tranche** - explicitement non bloquant pour le Done de `S41-QLM-SAISONS`, route a `camille-po` pour instruction future |
| Terrain 1 (EMC) compose sans marge de rejeu individuelle (4,5/5,0, `DEC-0095` section 9.5) : un refus sur l'un des trois lots ferait, au sens du tarif ecrit, depasser la borne sans reallocation ecrite | Gouvernance / produit, route mais non tranchee par `DEC-0095` elle-meme | `camille-po` et l'utilisatrice (`DEC-0095`, section 9.7) | **Ouvert, non tranche** - le risque nomme d'avance s'est materialise : `EMC-CP-08`, un des trois lots du terrain 1, a ete refuse deux fois. Aucune `DEC` ne reconcilie ce fait avec la clause de marge nulle ; je le signale, je ne le tranche pas |
| `MAT-CE1-18` (sens de la division) toujours sans ligne dans `MATRICE-couverture.md` | Gouvernance / produit | Non signale par personne durant s41 | **Ouvert, non corrige, rupture du signalement** - signale a chacun des sprints s36 a s40 (cinq fois consecutives par `camille-po`), absent de `planning.md`, de `DEC-0095` et de la matrice ce sprint-ci. Toujours verifie absent (`grep -n "MAT-CE1-18"` sur la matrice : aucune occurrence) |

**Addendum du 2026-09-24 (`nadia-prof`), sur la sixieme ligne du tableau ci-dessus - `SEM-1` et le
recouvrement avec `QLM-CP-13`. Je n'edite pas la ligne : elle decrit l'etat a la cloture de s41 et
c'est ce qu'elle doit continuer a dire. Voici la suite, apres la cloture.**

Le point y est note *"Ouvert, non tranche"* et parle de *"quatre items de `cp-semaine`"*. **Ce chiffre
est de moi et il etait faux** : il comptait les items de `cp-qlm-saisons` en recouvrement, pas ceux de
`cp-semaine`. Apres relecture des vingt-trois enonces un par un, **onze items sortaient du perimetre
corrige**, pas quatre - la correction est ecrite dans mon amendement du 2026-09-24 (`d3216c84`).

**Le point est desormais clos, et par le depot.** `SEM-1` amendee (`d3216c84`), `DEC-0096` par
`camille-po` (`b0061230`), execution par `marc-dev` (`0d48d2a3`) : onze items retires, huit items
neufs dans le perimetre corrige, vingt items au total. **J'ai reaudite le paquet modifie en le jouant**
et j'ai prononce le 2026-09-24 un **second verdict : VALIDE SOUS RESERVE** - `SEM-1` a `SEM-9` tenues
sur les vingt items, **le Done pedagogique est confirme**, `MAT-CP-09` reste `couvert-valide` avec la
meme mention et **aucune ligne de matrice n'a a bouger**. Deux reserves neuves et datees, non
bloquantes, sont nommees dans la fiche : deux paires d'items offrent le meme triplet de propositions
avec la meme bonne reponse (mesure : deux parties sur cinq environ en contiennent au moins une), et
sur deux items neufs le distracteur voisin dans la suite des moments manque. Motifs actionnables et
resultat attendu : `squad/conformite/FICHE-bandit-manchot-semaine-cp.md`, verdict du 2026-09-24.

**Un point signale hors depot, non corrobore par le depot, que je nomme sans le laisser passer pour
verifie** : il m'a ete rapporte que `lea-dev` aurait signale, pendant le developpement, des
collisions transitoires (`mvnd`, verrous de fichiers) sur le worktree principal partage quand
plusieurs agents y lancent `mvn` en meme temps pendant une fusion. **Je ne trouve ce signalement
dans aucun commit ni aucun fichier de s41** : ni dans les messages de `lea-dev` (`c87afd7a`,
`bddd8233`), ni dans le rapport qualite de `yanis-qa`, qui ecrit explicitement le contraire -
*"Aucun verrou `mvnd` transitoire rencontre : `bash outils/mvn-jdk21.sh -B clean verify`, lance une
seule fois sur ce worktree, a rendu `BUILD SUCCESS` du premier coup"* - ni dans ma propre mesure de
cloture. Je ne peux donc pas ecrire que cet obstacle a eu lieu et a ete resolu : je ne detiens aucun
fichier qui le porte. Ce que j'en fais est traite en `retro.md`, comme point de vigilance de process
et non comme fait etabli.

## 8. Le registre de dette - inchange a la cloture de s41

**Ouverture de s41 : 9,25** (`squad/sprints/s40-couverture-maximale-emc-histoire-sciences/review.md`,
section 7). Verifie par moi : recherche des identifiants de ce sprint (`EMC-CP-08`, `EMC-CP-10`,
`EMC-CP-13`, `QLM-CP-04`, `QLM-CP-13`, `SCI-CM1-06`, `DEC-0095`) dans `DEC-0086` et `DEC-0087` :
aucune occurrence dans les deux textes. Ce sprint ouvre six lignes hors du vivier `DEC-0086`/
`DEC-0087`, comme s39 et s40. **Registre de dette a l'ouverture d'un sprint futur : 9,25,
inchange.**

## 9. La couverture du programme

**94/155 (60,6 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, commit `6417c67f`
(`[CAM] s41: six lignes couvert-valide`) : six fermetures (`EMC-CP-08`, `EMC-CP-10`, `EMC-CP-13`,
`QLM-CP-04`, `QLM-CP-13`, `SCI-CM1-06`, toutes `non couvert` -> `couvert-valide`), aucune ouverture
partielle nouvelle au-dela de la mention de couverture partielle deja exigee d'avance sur
`SCI-CM1-06`.

## 10. L'avis de `theo-archi` et le lien vers `DEC-0095`

`squad/sprints/s41-fusion-emc-sciences-qlm/avis-faisabilite.md` (`eec116b1`) rend un verdict sur les
six lots directement issus de `DEC-0095` (six lignes, deux terrains, deux instances) : faisable en
l'etat pour cinq lots, faisable sous condition pour `S41-SCI-CLASSER` (`BIN-1` a encoder dans le
test du paquet, sur le patron de `PaquetCpEmcRegleSouhaitLoiTest`). Aucun `ADR` ni aucune regle
ArchUnit neuve annoncee comme due, verdict confirme sur l'arbre integre (section 3 ci-dessus).
`DEC-0095` elle-meme (`squad/decisions/produit/DEC-0095-objectif-et-perimetre-de-s41-fusion-de-deux-
sprints-projetes-et-borne-d-audit-verifiee.md`) est **Acceptee, amendee le 2026-09-23** : la borne a
deux instances (8,0) est reconstruite depuis sa derivation d'origine (`ARBITRAGE-PREALABLE-s29`),
pas prise de seconde main, et le total du sprint l'atteint exactement, sans marge au niveau du
sprint (section 7 ci-dessus).

## 11. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les six verdicts de `nadia-prof` (section 6) sont deja
  prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po`.
- Elle ne tranche pas la question `SEM-1`/`QLM-CP-13` (double-compte entre deux paquets de matieres
  differentes) ni l'absence de marge de rejeu sur le terrain 1 : les deux restent routees, non
  bloquantes, reportees en retro.
- Elle n'affirme ni n'infirme le point signale hors depot sur des collisions `mvnd` : elle constate
  qu'aucun fichier du sprint ne le porte et qu'une mesure du sprint (le rapport qualite) ecrit le
  contraire.
- Elle ne corrige pas le gap `MAT-CE1-18` : c'est un document de `camille-po`.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** La decision du sprint a ete
prise ailleurs et est enregistree ici pour memoire, verifiee physiquement presente sur le disque,
avec son statut, avant d'etre citee :

- `DEC-0095` - objectif et perimetre de s41, fusion de deux sprints projetes, borne d'audit verifiee
  et amendee a deux instances (`camille-po`, 2026-09-23) - **Acceptee, amendee le 2026-09-23**.
  Verifiee dans `squad/decisions/produit/DEC-index.md` et sur le disque.

**Aucun `ADR` n'est cree ni amende ce sprint** (section 3 ci-dessus, verifie par diff independant).
**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
