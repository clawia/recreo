# [Sprint s13] Planning - 2026-08-15

- **Horodatage de debut** : 2026-08-15 16:18
- **Anime par** : sofia-sm
- **Participants** : camille-po (perimetre, `DEC-0030`), theo-archi (avis de faisabilite requis
  avant affectation sur chaque story neuve, non encore rendu - voir "Ce qui demarre maintenant" ;
  avis d'ordre de grandeur sur une seconde mecanique, sans capacite affectee), marc-dev
  (developpement), lea-dev (developpement), sofia-sm (ceremonies, casquette dev sur un lot),
  nadia-prof (arbitrage prealable de S13-2 et S13-3, quatre questions de validite de proxy, avis
  de priorite pedagogique sur une seconde mecanique, verdicts de cloture), ines-design (forme du
  chemin de catalogue de S13-6), yanis-qa (traduction des criteres en tests au fil des commits,
  rapport qualite de cloture), relecteur-pedagogique (relecture des livrables non executables).
- **Lu avant d'ecrire ce fichier** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-
  cm1-et-le-plafond-de-la-mecanique-unique.md` ; `docs/backlog/backlog.md`, section "Sprint 13" ;
  les trois fiches de conformite ouvertes (`FICHE-bandit-manchot-grands-nombres-cm1.md`,
  `FICHE-bandit-manchot-multiplications-divisions-cm1.md`,
  `FICHE-bandit-manchot-problemes-durees-cm1.md`) ; `FICHE-bandit-manchot-tables-multiplication-
  cm1.md` et `FICHE-bandit-manchot-additions-cp.md` (reserves A, B, O seconde moitie, F11) ;
  `squad/programme/MATRICE-couverture.md`, section "Perimetre de s13" ; `ADR-0013-un-garde-fou-
  derive-son-perimetre-et-se-prouve-mordant.md` ; `squad/sprints/s12-mathematiques-cm1/RAPPORT.md`
  et `retro.md` ; `DEC-index.md` et `ADR-index.md` a jour.

## Fusion du lot

Un seul lot d'instruction, sans code de production, verifie par `git diff --stat` avant fusion.
Lignee verifiee au prealable : `git merge-base --is-ancestor c06dad3 3b5b427` confirme que la
branche descend directement de la tete de `round-two` a la cloture de s12 - un seul commit
d'ecart, aucune reecriture a craindre.

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| camille-po | `s13-camille-perimetre` | `3b5b427` | `docs/backlog/backlog.md`, trois fiches de conformite neuves, `DEC-0030` (fichier neuf), `DEC-index.md`, `squad/programme/MATRICE-couverture.md`. Aucun fichier de production. |

Fusionne par `git merge --no-ff`, aucun conflit. Tete de `round-two` apres fusion : `e5305b3`.

Camille signale elle-meme une coquille dans son propre message de commit (`x10/x10` au lieu de
`x10/×10`, le vrai signe multiplie) et ne l'a pas corrigee sans demande. Je la laisse : un
message de commit se corrige par un amendement qui reecrit l'historique, pour une coquille qui ne
trompe personne. Le libelle dans les fichiers vivants (`MATRICE-couverture.md`) est, lui, deja
corrige par le lot fusionne - c'est ce qui compte.

### Porte technique verifiee avant ce planning

Le lot fusionne est documentaire : aucun fichier de production ne bouge (`git diff --stat
c06dad3 e5305b3 -- '*.java'` est vide). Je rejoue moi-meme la suite complete et le garde-fou de
chemins plutot que de recopier le chiffre annonce.

- `JAVA_HOME` positionne sur le JDK 21.0.10 le temps de la commande (`bash outils/verifier-
  jdk.sh` : `java` du `PATH` en 1.8.0_202, JDK de compilation 21.0.10, fichier de toolchains
  present sous un nom non lu par Maven - dette de poste, deja nommee a la cloture de s03,
  inchangee). `mvn -B clean verify` sur l'arbre fusionne : **BUILD SUCCESS**, six modules en
  SUCCESS.
- Compte de tests, somme de l'attribut `Tests run` de chaque module apres un `clean` : 47
  (domain) + 7 (application) + 226 (adapter-web) + 98 (adapter-content) + 80 (bootstrap,
  surefire) + 6 (bootstrap, failsafe) = **464**, inchange depuis la cloture de s12 - conforme au
  chiffre annonce dans ce brief et non simplement recopie.
- Compte de regles ArchUnit, methode : recherche de la forme `ArchRule <nom> =` dans les fichiers
  `.java` suivis par Git sous `src/test/java` des cinq modules de code, exclusion du faux module
  de contre-exemple de `LesGardeFousSontMisALEpreuveTest` (vit sous `src/test/resources`, jamais
  compile comme regle du depot) : `adapter-web` 1, `bootstrap` 27, **28** au total, inchange.
- Garde-fou de chemins rejoue comme la CI : `git ls-files -- . ':!.github/scripts/verifier-
  chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` -> "Aucun chemin de poste de
  travail ni nom de compte dans les fichiers verifies.", **code de sortie 0**.

## Objectif du sprint (gele)

**A la review, la ligne "lire, ecrire, decomposer et comparer les grands nombres jusqu'au
million" passe `en cours` a un increment juge par `nadia-prof`, les deux reserves non bloquantes
des tables de multiplication (A - repetition d'enonce, B - distracteurs hors de l'espace des
tables) sont corrigees et reverifiees par elle, un chemin vers le catalogue ne detruit plus la
partie en cours, et l'annonce "Menu principal" d'un lecteur d'ecran redevient "Les jeux". En
parallele, sans engager de capacite de developpement : `nadia-prof` rend l'arbitrage prealable de
S13-2 et S13-3, repond aux quatre questions de validite de proxy de `DEC-0030` section 4, et
donne son avis de priorite pedagogique sur une seconde mecanique ; `theo-archi` rend un avis
d'ordre de grandeur sur les trois besoins mecaniques non couverts (visuel, saisie, audio) et
publie `confirmation-archunit.md` a la review, cinquieme tentative et non une quatrieme comme
annonce par `DEC-0030` (s09, s10, s11, s12 absents - quatre sprints, celui-ci serait le
cinquieme).**

**Gele** : aucune ouverture de ligne de francais. Aucune ligne de mathematiques CM1 au-dela des
trois nommees par `DEC-0030` n'entre au perimetre. Aucun developpement sur S13-2 et S13-3 avant
l'arbitrage prealable de `nadia-prof` - ce planning n'affecte que l'arbitrage, jamais un lot de
code, exactement comme s11 l'a fait pour `DEC-0027`.

## Ce qui demarre maintenant, avant tout code

`theo-archi` n'a rendu aucun avis de faisabilite sur les cinq stories ci-dessous - je ne l'ecris
pas a sa place. C'est le premier geste du sprint, sur chacune, avant que le premier commit de
code ne soit ouvert : c'est ce que `DEC-0030` exige elle-meme pour chaque story ("avis de
faisabilite de `theo-archi` requis avant affectation, comme pour toute story neuve"). Les
affectations nominatives ci-dessous sont donc **provisoires**, tenues par competence disponible,
et deviennent fermes une fois l'avis rendu.

En parallele, et sans attendre l'avis de `theo-archi` sur ces cinq stories :

- `nadia-prof` rend l'arbitrage prealable de S13-2 et S13-3 (`DEC-0030`, section 5) - condition
  bloquante avant tout code sur ces deux lignes, aucune exception.
- `nadia-prof` repond aux quatre questions de validite de proxy de `DEC-0030` section 4
  (denombrement CP, figures planes CP, decodage syllabique CP, relecture de son texte CM1) - ne
  bloque aucune story de ce sprint, instruit le backlog de s14.
- `nadia-prof` donne son avis de priorite pedagogique entre visuel, saisie et audio (`DEC-0030`
  section 5, sixieme demande) - n'engage aucune decision, `camille-po` reste seule a trancher le
  sequencement.
- `theo-archi` rend un avis de faisabilite et d'ordre de grandeur (pas un chiffrage detaille) sur
  chacun des trois besoins mecaniques non couverts (`DEC-0030` section 6, point 3) - **sans
  capacite de developpement affectee ce sprint**, en parallele du reste de son travail.

## Ce que ce sprint developpe

| Story | Titre | Competence visee | Affecte a (provisoire) | Porte de sortie |
|---|---|---|---|---|
| S13-1 | Lire, ecrire, decomposer et comparer les grands nombres jusqu'au million | mathematiques CM1, "Nombres et calculs" - ligne entiere | `marc-dev` | Tests verts (24 items au moins, 6 par verbe, GN-1/GN-2 tenues) **et** Done pedagogique de `nadia-prof` sur `FICHE-bandit-manchot-grands-nombres-cm1.md`. |
| S13-4 | Correction de reserve - un enonce des tables ne revient plus une partie sur cinq | mathematiques CM1, tables de multiplication (ligne deja `couvert-valide` sous reserve, non rouverte) | `lea-dev` | T-1 a T-7 inchanges, protocole de 500 parties rejoue, reverifie par `nadia-prof` sur `FICHE-bandit-manchot-tables-multiplication-cm1.md`. |
| S13-5 | Correction de reserve - aucun distracteur des tables ne sort de l'espace des tables | mathematiques CM1, meme ligne que S13-4, non rouverte | `lea-dev` | Les deux distracteurs fautifs (11, 110) corriges, reverifie par `nadia-prof`, meme fiche. |
| S12-5 | L'unique lien de sortie n'est plus annonce comme un menu a un enfant qui ecoute l'ecran | transverse, aucune competence programme | `sofia-sm` (casquette dev) | Aucune occurrence de "menu" ni "principal" sur les deux canaux, jugee par `nadia-prof`. Ne leve pas la reserve F11 (voir "Obstacles"), et ne pretend pas la lever. |
| S13-6 | Un chemin vers le catalogue qui ne detruit pas la partie en cours | transverse, aucune competence programme | `marc-dev` (second lot, une fois S13-1 livre - S13-1 est un paquet YAML sans ligne de Java, il degage du temps) | Les trois criteres d'acceptation tenus (partie intacte, retour sur la meme manche, changement de jeu toujours destructeur), forme proposee par `ines-design`, texte juge par `nadia-prof`. |

**S13-2 et S13-3 ne sont pas affectees ce sprint.** Elles restent au backlog en attente de
l'arbitrage prealable de `nadia-prof`. Si cet arbitrage est rendu tot et qu'une capacite de
developpement reste disponible une fois les cinq lots ci-dessus lances, je les affecte en cours
de sprint plutot que d'attendre s14 - mais je ne les engage pas aujourd'hui, pour la raison
donnee dans "Ce qui sort, et pourquoi".

`yanis-qa` traduit chaque critere en test au fil des commits des cinq lots. `ines-design` propose
la forme du chemin de S13-6. `relecteur-pedagogique` intervient apres le Done technique de
chaque lot, avant la review.

## Ce qui sort, et pourquoi

**Cinq stories retenues au lieu de sept, et c'est un choix, pas une omission.** `camille-po`
elle-meme plafonne a trois ouvertures de contenu neuf par sprint, motif explicite dans `DEC-0030`
section 2 : "le sprint 12 a montre qu'un increment techniquement simple peut consommer autant de
temps sur ses defauts que sur son contenu". Sur les trois lignes de contenu neuf qu'elle propose,
une seule (S13-1) est aujourd'hui deblocable ; les deux autres (S13-2, S13-3) sont verrouillees
par un arbitrage qui n'existe pas encore. Les empiler comme "affectees sous condition" dans le
tableau ci-dessus aurait fabrique un catalogue de sept lignes dont deux dependent d'un evenement
qui peut ne pas se produire a temps - exactement la lecture que ce sprint doit eviter de
reproduire.

1. **S13-2 (multiplications/divisions par 10 et 100) et S13-3 (problemes de durees).**
   **Reportees, non engagees.** Aucun arbitrage prealable n'a jamais ete rendu sur ce contenu
   precis (`DEC-0030` section 1) ; l'ordre etabli par ce produit depuis l'incident de "Le bon
   verbe" (s07) est que l'arbitrage precede le code, jamais l'inverse. L'arbitrage lui-meme est
   planifie comme premiere action du sprint, sans capacite de developpement engagee en attendant
   son verdict.
2. **La priorisation de S13-2 avant S13-3 ou l'inverse, une fois l'arbitrage rendu.** Non
   tranchee ici : `camille-po` a recommande l'ordre (S13-2 d'abord, "recommandee en premier"),
   mais je n'affecte aucun dev tant que l'arbitrage n'existe pas - trancher un ordre d'affectation
   sur du code qui ne peut pas commencer est une decision sans objet.
3. **Le choix d'une seconde mecanique.** Explicitement non ouvert par `DEC-0030` elle-meme :
   seuls les avis de `theo-archi` et de `nadia-prof` sont demandes ce sprint, en parallele et
   sans capacite affectee. Je ne fixe aucun sprint d'ouverture.

## Trois points routes a ce planning

### 1. `confirmation-archunit.md`, absent pour la quatrieme fois - je le publie ce sprint

A la cloture de s11, j'ai repris ce point a mon compte : ce n'est pas un obstacle a router, c'est
une omission de ma propre ceremonie de review. Quatre sprints plus tard (s09, s10, s11, s12), le
fichier n'existe toujours pas alors qu'`ADR-0013` s'appuie dessus pour faire descendre son
plafond de dette de balayage. Je tranche : **ce sprint le publie, sans exception.**
`theo-archi` l'ecrit a la review de s13, sur l'arbre fusionne, comme il l'a fait pour s00 a s03 -
comptage des regles ArchUnit et du plafond de dette de balayage, sur ce meme modele. Je
l'inscris explicitement dans les criteres de cloture de `review.md` (voir "Prochaines etapes") et
je verifie moi-meme son existence physique sur le disque avant de clore la review : une
cinquieme absence ne sera plus une omission, ce serait un choix assume de ne pas le faire, et il
faudrait alors l'ecrire ainsi et amender `ADR-0013` en consequence. Je ne prends pas ce second
chemin aujourd'hui - le premier reste tenable et coute une revue, pas un sprint.

### 2. Reserve F11 - aucun agent de la squad ne peut faire cette ecoute, et je le dis plutot que de la laisser glisser

Ce n'est pas une decision de perimetre : c'est une action concrete - ecouter reellement
l'application avec un lecteur d'ecran - que je ne peux affecter a personne dans cette squad.
`nadia-prof` a deja refuse un `aria-label` sans avoir pu entendre ce qu'il annonce, et elle
ecrit elle-meme que si l'ecoute "n'est techniquement pas faisable sur nos postes", elle le dira
plutot que de valider en supposant. Aucun des neuf agents de `.claude/agents/` - `nadia-prof`
comprise, malgre son acces Bash en lecture/execution - ne dispose d'une perception auditive ni
d'un moyen etabli et accepte par elle de faire entendre un rendu de synthese vocale. Une lecture
d'arbre d'accessibilite automatisee n'est pas une ecoute : c'est exactement la forme
d'approximation qu'elle a deja refusee ailleurs sur ce produit ("plutot que de pretendre avoir
regarde huit ecrans").

**Ce que je route, concretement** : cette action depasse la squad d'agents et demande soit que
l'utilisatrice fasse elle-meme cette ecoute (un vrai lecteur d'ecran, sur le rendu reel) et en
rapporte le contenu a `nadia-prof`, soit que `nadia-prof` accepte explicitement, par ecrit, une
autre forme de preuve qu'elle jugerait suffisante - ce qui reste sa decision, pas la mienne. Je
ne fabrique ni l'un ni l'autre a sa place. F11 reste ouverte, non affectee a un agent, et je le
remonte a l'utilisatrice plutot que de laisser ce point glisser une cinquieme fois en silence.

### 3. Avis de faisabilite sur une seconde mecanique - retenu en parallele, sans capacite affectee

`camille-po` demande un avis d'ordre de grandeur de `theo-archi` sur les trois besoins mecaniques
non couverts (visuel, saisie, audio), en parallele de ce sprint, sans que cela consomme une
capacite de developpement. J'accepte cette demande telle quelle : elle n'entre pas en concurrence
avec les cinq lots de developpement, elle ne fixe aucun sprint d'ouverture, et elle donne a la
squad un cout connu avant que la reserve de contenu seul (quatre a six sprints, estimation de
`camille-po`) ne s'epuise. J'y ajoute la sixieme demande de `nadia-prof` (avis de priorite
pedagogique) dans le meme geste, pour la meme raison.

## Obstacles a ce planning

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| `confirmation-archunit.md` absent pour la quatrieme fois d'affilee (s09-s12) | technique / methode | `theo-archi` (redaction), `sofia-sm` (verification de l'existence physique) | **route et planifie pour la review de ce sprint** - voir "Points routes", section 1. |
| Verrou d'arbitrage prealable sur S13-2 et S13-3, non rendu | pedagogie | `nadia-prof` | **ouvert**, tache planifiee ce sprint (arbitrage seul, pas de code). |
| Quatre questions de validite de proxy (`DEC-0030` section 4) | pedagogie | `nadia-prof` | **route, non bloquant pour ce sprint** - instruit le backlog de s14. |
| Avis de priorite pedagogique sur une seconde mecanique | pedagogie | `nadia-prof` | **route**, en parallele, sans capacite affectee. |
| Avis d'ordre de grandeur sur une seconde mecanique (visuel, saisie, audio) | technique | `theo-archi` | **route**, en parallele, sans capacite affectee. |
| Reserve F11 - aucune ecoute reelle de lecteur d'ecran n'a jamais eu lieu | pedagogie / accessibilite | aucun agent de la squad - action hors de sa portee, remontee a l'utilisatrice | **ouvert**, voir "Points routes", section 2. |
| Avis de faisabilite de `theo-archi` non rendu sur S13-1, S13-4, S13-5, S12-5, S13-6 | technique | `theo-archi` | **route**, premiere action du sprint, gate les affectations provisoires. |

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant ce planning : `DEC-0030` est anterieure et deja
fusionnee ci-dessus. Les decisions prises ici m'appartiennent en tant que Scrum Master et
n'ouvrent ni `ADR` ni `DEC` :

1. **Cinq stories affectees provisoirement** (S13-1, S13-4, S13-5, S12-5, S13-6), sous reserve
   de l'avis de faisabilite de `theo-archi` sur chacune - non encore rendu, premiere action du
   sprint.
2. **S13-2 et S13-3 ne sont pas engagees ce sprint.** Seul leur arbitrage prealable est planifie.
3. **`confirmation-archunit.md` est publie a la review de ce sprint**, sans exception cette fois
   - je romps ici la serie de quatre absences plutot que de la router une cinquieme fois.
4. **La reserve F11 est remontee a l'utilisatrice** : aucun agent de la squad ne peut effectuer
   l'ecoute qu'elle exige.
5. **L'avis de faisabilite sur une seconde mecanique est retenu en parallele**, sans capacite de
   developpement affectee, avec l'avis de priorite pedagogique de `nadia-prof` dans le meme
   geste.
6. **Le repertoire de ce sprint est nomme** `s13-grands-nombres-et-chemin-de-catalogue`.

## Prochaines etapes

**Demarrent maintenant** :

- `theo-archi` rend son avis de faisabilite sur S13-1, S13-4, S13-5, S12-5 et S13-6, avant que
  quiconque n'ouvre un commit de code sur l'une d'elles.
- `nadia-prof` rend l'arbitrage prealable de S13-2 et S13-3 - ce geste ne depend d'aucun livrable
  de developpement et peut commencer immediatement, en parallele du reste.
- `nadia-prof` et `theo-archi` instruisent en parallele les avis nommes ci-dessus (validite de
  proxy, priorite pedagogique, ordre de grandeur d'une seconde mecanique) - aucun des deux
  n'engage de capacite de developpement.
- Une fois l'avis de `theo-archi` rendu sur chaque story : `marc-dev` ouvre un worktree dedie
  pour S13-1, verifie sa lignee (`outils/verifier-lignee.sh`), puis pour S13-6 en second lot ;
  `lea-dev` ouvre un worktree dedie pour S13-4 et S13-5 ; `sofia-sm`, casquette developpeuse,
  ouvre un worktree dedie pour S12-5. Tests dans le meme commit que le code, sur chaque lot.
- `ines-design` propose la forme du chemin de catalogue de S13-6.
- `yanis-qa` traduit chaque critere en test au fil des commits.

**Une fois chaque lot fusionne sur `round-two`** : `nadia-prof` joue l'increment et rend son
verdict de Done pedagogique sur chaque fiche de conformite concernee.

**Une fois l'arbre fusionne et les verdicts rendus** : `yanis-qa` fait tourner `mvn verify` sur
JDK 21 et ecrit le rapport qualite ; `theo-archi` confirme ArchUnit et publie
`confirmation-archunit.md` (voir "Points routes", section 1) ; `relecteur-pedagogique` releve les
livrables non executables ; `sofia-sm` ecrit `review.md` puis `RAPPORT.md` et lance le rapport de
tokens (`python outils/rapport-tokens.py --sprint s13`).
