# [Sprint 33] Review - 2026-09-10

- **Horodatage de debut** : 2026-09-09 18:20 (repris du planning)
- **Horodatage de fin** : 2026-09-10 11:45
- **Duree effective** : 17 h 25
- **Anime par** : sofia-sm

## Increment livre

Trois instances de `nadia-prof` ont audite en parallele trois terrains disjoints
(histoire-geographie et anglais CM1, EMC CP en entier, education physique et sportive CP) sur les
huit stories que les trois developpeurs ont livrees ce sprint ; trois lignes d'histoire-geographie
CM1 ferment (`HG-CM1-02`, `HG-CM1-04`, `HG-CM1-08`), quatre refus (`HG-CM1-12`, `EMC-CP-07` au
quatrieme passage, `EPS-CP-04` et `EPS-CP-08` au second passage), portant la couverture du
programme de 66/138 (47,8 %) a 69/138 (50,0 %).

## Le compte des lignes fermees - recompte sur les trois verdicts, pas repris d'un chiffre annonce

**Trois lignes ferment, une seule instance en produit** : `verdicts-nadia-instance-A.md`, section
10.1, prononce `HG-CM1-02`, `HG-CM1-04` et `HG-CM1-08` **VALIDE SOUS RESERVE** (`en cours` ->
`couvert-valide`) et `HG-CM1-12` **REFUSE** (reste `en cours`). `verdicts-nadia-instance-B.md`,
section 1 : "Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge sur mon terrain,
dans les deux sens" - `EMC-CP-07` refusee au quatrieme passage reste `non couvert`, `CAT-1` payee
sur deux lignes deja `couvert-valide` sans les rouvrir. `verdicts-nadia-instance-C.md`, section 14 :
"Lignes fermees par l'instance C : zero, sur quatre visees" - `EPS-CP-04` et `EPS-CP-08` refusees
au second passage restent `non couvert`.

**Total : 3 lignes fermees, 4 refusees** (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`).
**Taux d'entree** : 66/138 = 47,8 % (`DEC-0084`, repris par le planning, section 1). **Taux de
sortie : 69/138 = 50,0 %** (recompte independant en section suivante).

## Mouvement de `squad/programme/MATRICE-couverture.md`

Seul geste de cette ceremonie qui deplace la matrice ce sprint - ni `verdicts-nadia-instance-B.md`
ni `verdicts-nadia-instance-C.md` ne l'ont touchee, tous deux l'ecrivent explicitement. Trois
lignes bougent, chacune avec le verdict qui l'autorise :

| Ligne | Mouvement | Verdict qui l'autorise |
|---|---|---|
| `HG-CM1-02` | `en cours` -> `couvert-valide` | `verdicts-nadia-instance-A.md`, section 2 (`HG-R11` levee, `HG-R9` levee) |
| `HG-CM1-04` | `en cours` -> `couvert-valide` | `verdicts-nadia-instance-A.md`, section 3 (`HG-R12` levee, `HG-R10` levee) |
| `HG-CM1-08` | `en cours` -> `couvert-valide`, sur sa part servie | `verdicts-nadia-instance-A.md`, section 4 |

`HG-CM1-12` reste `en cours` (`HG-R17`, bloquante, verdict section 5) : aucune ligne ne bouge sur
un refus. Les cellules des quatre lignes sont mises a jour dans le domaine "Histoire-Geographie -
CM1" de la matrice avec le motif complet, plus un paragraphe de bilan et une synthese de cloture,
sur le meme patron que les sprints 27 a 32.

**Recompte independant**, sur les dix-huit tableaux de la matrice, colonne `Statut`, apres
application des trois mouvements :

| | Avant les verdicts (ouverture de s33) | Apres les verdicts de s33 |
|---|---|---|
| `couvert-valide` | 66 | **69** |
| `en cours` | 4 | **1** |
| `non couvert` | 68 | 68 |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **66 / 138 = 47,8 %** | **69 / 138 = 50,0 %** |

Detail complet, position dans les deux fourchettes annoncees, et routages du domaine :
`squad/programme/MATRICE-couverture.md`, section "Synthese apres les verdicts du sprint 33".

## Porte technique - confirmation finale, sofia-sm

`yanis-qa` et `theo-archi` ont chacun confirme la porte technique sur l'arbre fusionne a `b45e5927`,
avant que les huit documents de ce sprint (trois verdicts, rapport qualite, confirmation ArchUnit,
arbitrage produit, relecture, et les mouvements de matrice ci-dessus) ne soient tous en place, et
alors qu'un rouge transitoire a ete observe par deux agents sur
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` pendant que les trois instances ecrivaient
le registre en parallele. **Je ne deduis pas la porte technique de ces mesures anterieures et je ne
confonds pas la CI avec `mvn verify`** (`CLAUDE.md`, amendement du 2026-09-08) : je relance les
quatre jobs moi-meme, maintenant, sur l'arbre de travail complet (le commit `b45e5927` plus les huit
documents non commites de ce sprint et les mises a jour de la matrice ci-dessus).

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 1740807e..HEAD \| verifier-prefixes.sh` | **VERT.** *"Tous les commits portent un prefixe d'agent connu."* |
| `chemins` | `verifier-chemins-test.sh`, puis `verifier-chemins.sh` sur l'union des fichiers suivis et non suivis (`git ls-files` + `git ls-files --others --exclude-standard`), pour couvrir les huit documents de sprint pas encore commites | **VERT.** *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | la boucle du job sur les trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **VERT. 9 juges purs executes**, aucun motif vide |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules | **VERT. BUILD SUCCESS**, six modules `SUCCESS`, 03:10 min |

**Compte de tests : 1443, 0 echec.** Methode : somme de l'attribut `tests` sur les **193** fichiers
`TEST-*.xml` de `surefire-reports` et `failsafe-reports`, mesure directement sur le disque apres ce
`clean verify` - identique au chiffre annonce par `sofia-sm` (planning), `yanis-qa` et
`theo-archi`. **JDK 21.0.10**, rapporte par la machine virtuelle des tests elle-meme ("Starting
CatalogueChargeableIT using Java 21.0.10"), pas par une entree de toolchain.

**Le registre d'identifiants est stabilise.** `squad/decisions/REGISTRE-identifiants.md` porte
`601` identifiants alloues, `97` prefixes distincts (section 1 du fichier), et
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` rend **`Tests run: 7, Failures: 0, Errors:
0`** sur cette mesure - le rouge transitoire signale par les instances A et B pendant l'ecriture
concurrente du registre n'existe plus sur l'etat que je mesure. **Porte technique : verte, les
quatre jobs nommes un par un, sur l'arbre complet, maintenant.**

## Rapport qualite - yanis-qa

- **Commande** : les quatre jobs de `.github/workflows/ci.yml`, chacun relance et nomme, mesures
  sur un worktree isole positionne exactement sur `b45e5927` apres qu'une premiere mesure sur le
  worktree partage a rencontre un rouge reel mais hors perimetre (des processus `java -jar
  recreo.jar` issus d'une session de jeu manuel d'un autre agent, retrouves actifs sur le worktree
  partage).
- **Resultat** : vert sur les quatre jobs. `BUILD SUCCESS`, six modules `SUCCESS`, **1443 tests, 0
  echec, 0 erreur** (193 fichiers `TEST-*.xml`), confirme independamment par `theo-archi` et par
  `sofia-sm` ci-dessus.
- **Regression de fragilite de s32 fermee, mise a l'epreuve** : le pincement des effectifs de
  `theo-archi` (`partageDesTachesEpingle`) est mute puis restaure sur le paquet reel - reformuler un
  enonce sans changer sa tache rend le test rouge, exactement le geste qu'il devait attraper.
- **Non couvert, nomme plutot que suppose** : `S33-EMCCP07` reste partielle (un item paye sur
  trois) ; `S33-CONS-4` et `S33-SCO-1` restent bloquees, sans code ni test touche ; aucune trace
  dans le depot Git d'une partie jouee de bout en bout - trou deja signale a s32, toujours ouvert ;
  le jugement de sens (`CAT-1`, `HIS-2`, `HIS-3`, `BIA-5`, exactitude factuelle) reste hors de
  portee de tout automate, frontiere deja actee.
- **Tests instables** : aucun.
- **Avis** : porte technique verte. Le Done pedagogique reste entier a `nadia-prof`.

## Confirmation ArchUnit - theo-archi

- **Commande** : les quatre jobs, sur le checkout principal, arbre propre au lancement (09:42).
- **Resultat** : vert sur les quatre jobs. 1443 tests, raccord exact. **33 regles ArchUnit
  executees**, comptees par le moteur lui-meme (32 dans `recreo-bootstrap`, 1 dans
  `recreo-adapter-web`) ; **34 objets `ArchRule` existent au total**, la trente-quatrieme
  (`ScanDeComposantsTest`) etant verifiee a la main dans un `@Test` plutot que par le moteur.
- **Aucune ligne de code de production n'a bouge en s33** (`git diff --name-only 1740807e..HEAD`,
  0 fichier `src/main/java`, 0 import ajoute) : rien a violer par ajout de dependance.
- **Les deux regles ecrites a s33 mordent reellement**, mesure par mutation sur le paquet reel
  puis restauration : `S33-HG-04` (l'enumeration ecrite a la main refuse le retour a "les
  Sarrasins") et `FER-3` epingle (une reformulation qui deplace le partage des taches sans le
  signaler fait rougir le test).
- **Recomptage exact du motif d'exemption de `marc-dev`** (`cm1-histoire-geographie`, 110 items,
  330 propositions) et **cinq motifs perimes trouves ailleurs dans le meme registre** (`cp-qlm-
  corps-sens`, `cp-qlm-etats-eau`, `cp-qlm-vivant-non-vivant` depuis s28, `cm1-anglais` depuis s32,
  `cm1-synonymes` sur un chiffre laisse au present) - ce ne sont pas des faux verts, la propriete
  reelle est verifiee a chaque execution, mais la prose des motifs pourrit. Dette datee pour s34,
  regle et cinq corrections dans le meme commit, routee a `sofia-sm` pour le tirage.
- **`D7` (`SCO-1`)** : les deux verrous tiennent encore, le rayon remesure (1245 items, 97,3 %
  inchange), une correction au propre avis (`DesignationSession` code le meme defaut mais n'est
  jouee par aucune entree) et deux precisions techniques sur le remede A/B. **Ce qui reste du est
  une phrase de `nadia-prof`**, non rendue ce sprint.
- **Rouge trouve sur le checkout partage, non celui de s33** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`
  rouge a deux reprises pendant que les trois instances ecrivaient le registre en parallele -
  reparation arithmetique faite et datee, aucune allocation ajoutee ni retiree. Fichier isole de
  `theo-archi` verifie vert seul (confirmation reprise en section "Porte technique" ci-dessus, sur
  l'etat stabilise a 601/97).

## Arbitrage produit - camille-po

Rendu sur les deux obstacles routes par le planning, section 12 :

- **Grain de `S33-CONS-4`** : position rendue, pas une decision close. `camille-po` retient un
  decoupage par famille de tache (un jeu par type de question, chacun pouvant servir plusieurs
  lignes de referentiel qui partagent la meme tache) plutot que la phrase unique ou le decoupage
  ligne par ligne. Trois questions restent routees a `nadia-prof` (voir section dediee plus bas) :
  la story reste bloquee tant qu'elles ne sont pas rendues.
- **Grain du lot de l'effort et du corps** (`EPS-CP-10`, `EPS-CP-11`) : position rendue, **un seul
  paquet**, convergente avec la recommandation deja ecrite par `nadia-prof` - se lit comme regle
  des qu'elle confirme.
- **Trois stories ecrites pour s34**, sur les postes finances sans story a s33 (planning, section
  10) : `S34-ANG-CORR` (`cm1-anglais`, `ANG-R3` a `R6`), `S34-TRANSVERSE-1` (passe d'audit
  generique), `S34-EPS-CORPS-EFFORT` (un seul paquet, frontiere de `DEC-0083` recopiee mot pour
  mot).

## Relecture - relecteur-pedagogique

- **Correction appliquee** : `cm1-histoire-geographie.yaml`, item du festival (`HG-CM1-12`) -
  harmonisation de "évènement" (accent grave, une occurrence) sur "événement" (accent aigu, treize
  occurrences dans le meme fichier). Rejoue : `PaquetCm1HistoireGeographieTest`, 28/28.
- **Point a trancher** : la correction rend morte une branche du predicat de
  `fer1SurHgCm112LaPratiqueSeCultiverEstDesormaisServie` (la sous-chaine `"évènement culturel"` ne
  matche plus rien, le test reste vert par un autre motif). Route a `theo-archi`.
- **References verifiees** : tous les `ADR`, `DEC`, `PAR-N` et identifiants de condition cites dans
  les huit documents de sprint resolvent vers un fichier ou une definition reels ; rejoue par la CI
  elle-meme (16 tests, 0 echec).
- **Chiffres verifies** : 1443 tests, 33 regles ArchUnit, le registre d'identifiants (582 -> 601,
  97 prefixes) - tous coherents, le mecanisme de divergence entre instances deja explique par
  chacune a la source. `PAR-14` et le chiffrage de `EMC-CP-07` : `nadia-prof` corrige elle-meme ses
  propres chiffrages publies, avec leur date, sans retoucher les documents historiques - deja
  gouverne, rien a trancher.
- **Conventions de redaction** : conformes (aucun tiret cadratin, aucun guillemet typographique,
  aucun emoji, aucun chemin de poste de travail, y compris dans les fichiers non commites).

## Le budget reellement depense contre la borne de 12,0

Composition planifiee (planning, section 2 ; `DEC-0085`, section 3, non rediscutee) : **11,5 sur
12,0** engages au plan (A 4,0, B 3,5, C 4,0), pour un plafond arithmetique de +9 lignes.

| Instance | Borne | Depense reel | Idle (jamais engage) | Ce que l'idle a achete |
|---|---|---|---|---|
| A | 4,0 | **2,5** (verifie, verdict A, section 10.3) | **1,5** - `cm1-anglais` (0,5, aucune story), passe transverse (0,5, aucune story), marge `PAR-11` (0,5) | zero ligne, zero audit : les trois postes n'ont jamais recu de story (planning, section 10) |
| B | 3,5 | non auto-audite par l'instance elle-meme (aucun tableau de cout dans son verdict, a la difference de A et C) | au moins **0,5** - la marge non allouee de la section 2 du planning, jamais reaffectee pendant le sprint (aucune trace dans les sections 13-14 du planning) | zero ligne, quel que soit le montant exactement engage sur le reste (3,0 au plus) : les quatre stories de ce terrain ferment zero ligne neuve (`CAT-1` paye deux lignes deja `couvert-valide`, `EMC-CP-07` reste `non couvert`, `EXC-R5` cree trois defauts neufs) |
| C | 4,0 | **2,0** (verifie, verdict C, section 14) | **2,0** - lot de l'effort et du corps (1,5, aucune story sur ses deux lignes) et reserves du lot (0,5) | zero ligne : `EPS-CP-10` et `EPS-CP-11` n'ont jamais recu de story |

**Ce que la borne a paye pour zero ligne, etabli plutot que suppose** : sur les 11,5 planifies, au
moins **4,0 sont restes totalement inemployes** (1,5 chez A, 2,0 chez C, 0,5 chez B) et n'ont donc
rien achete du tout. Du reste effectivement engage, **seuls les 2,5 de l'instance A ont achete les
trois fermetures du sprint** ; le reste engage - au moins 2,0 chez C, et au moins 3,0 chez B si
l'integralite de sa borne restante a ete engagee - **a produit zero ligne neuve fermee**, quel que
soit le detail exact de ce que B a depense poste par poste : ses quatre stories, prises ensemble,
ne deplacent aucune ligne de matrice. **Je n'invente pas de decomposition plus fine pour B** :
aucun document du sprint ne rend le tableau de cout qu'A et C rendent chacune pour leur propre
terrain, et je ne recopie ni ne devine un chiffre qu'aucune source n'ecrit.

## Le registre de dette a la cloture, contre les 9,0 de l'ouverture

**Ouverture de s33 : 9,0** (`DEC-0085`, section 7 ; planning, section 9 - repris sans recalcul).
La strategie retenue projetait une descente a **2,5 si le plafond de 9 lignes etait atteint**
(`DEC-0085`, section 7) - et l'arbitrage prealable de s33 precisait meme que cette descente
tiendrait *"si s33 se comporte comme s32"* (3 lignes fermees, exactement le cas survenu), *"parce
que 6,5 des 9,0 payes ne dependent pas de la fermeture d'une ligne"* mais de l'engagement effectif
des paquets qui portent la dette (arbitrage prealable, section 7.3).

**Le plafond de 9 n'est pas atteint (3 lignes ferment), et la projection ne tient pas non plus
dans sa forme "meme si s33 se comporte comme s32".** Les paquets que `DEC-0085`, section 7, nomme
comme porteurs des treize reserves de s32 (`cm1-anglais`, `cp-emc-emotions-autrui`, `cp-emc-
exclusion`, `cp-emc-formules-de-politesse`, `cp-emc-permis-interdit`, `cp-emc-regles-de-vie`, plus
`cm1-histoire-geographie` qui voyage gratuitement) n'ont pas tous ete engages ce sprint, verifie
sur les trois verdicts :

- **Non touches du tout** : `cm1-anglais` (aucune story, section 8 du verdict A), `cp-emc-
  emotions-autrui` et `cp-emc-formules-de-politesse` (verdict B, section 13 : "je n'ai pas rejoue
  `emotions-autrui` ni `formules-de-politesse` ce sprint, aucune de leurs reserves n'est
  financee").
- **Touche mais sans regler sa dette** : `cp-emc-exclusion` - `EXC-R5` executee mais `EXC-R6`
  toujours ouverte et trois defauts neufs crees (`EXC-R7` a `EXC-R9`), `EXC-R1` non payee.
- **Touches et partiellement regles** : `cp-emc-permis-interdit` (`CAT-1` payee, `PER-R2` reste
  ouverte, `PER-R3` neuve) et `cp-emc-regles-de-vie` (`CAT-1` payee, `REG-R2` levee, `REG-R3` reste
  ouverte a un chiffre corrige, `REG-R4`/`REG-R5` neuves).
- **`cm1-histoire-geographie`** : `HG-R9` et `HG-R10` sont **levees** (verdict A, section 10.2),
  gratuitement comme prevu.

**Sur les sept postes qui composaient la dette payable, quatre n'ont jamais ete ouverts ou n'ont
pas ferme leur reserve d'origine.** La descente projetee a 2,5 supposait l'engagement de
l'ensemble ; elle ne s'est pas produite pour la moitie du perimetre. **Je n'invente pas le chiffre
exact du registre a la cloture** : aucun document de ce sprint (ni `nadia-prof`, ni `camille-po`,
ni `theo-archi`) ne recalcule le registre poste par poste apres coup, et la regle de conversion
d'une reserve fermee en unites de registre n'est pas assez precisement ecrite pour que je la
reconstitue sans deviner. **Ce que j'ecris sans l'adoucir** : le registre reste **au-dessus de
2,5**, la descente annoncee ne tient pas telle quelle, et le recalcul exact est du a `camille-po`
a l'ouverture de s34, sur le meme materiau que celui qui a servi a le chiffrer a 9,0.

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| `HG-CM1-12` reste `en cours`, refusee (`HG-R17`, bloquante) | Pedagogie, contenu | nadia-prof (audite), sofia-sm (story) | - | ouvert, story a ecrire pour s34 |
| `EMC-CP-07` refusee au quatrieme passage, blocage leve par `nadia-prof` elle-meme | Pedagogie, methode | nadia-prof (leve son propre blocage), sofia-sm (perimetre corrige) | - | ouvert, ligne fermable a s34 |
| `EPS-CP-04`, `EPS-CP-08` refusees au second passage | Pedagogie, exactitude et coherence de paquet | nadia-prof (audite), sofia-sm (stories) | - | ouvert, stories a ecrire pour s34 |
| **La classe de defaut nommee par l'instance C** : une correction executee a la lettre de la reserve et pas a son objet, qu'aucun garde-fou ne voit (troisieme occurrence en trois sprints) | Pedagogie, methode de correction | sofia-sm, pour la retro | - | ouvert |
| **Le defaut structurel du registre d'identifiants** : une allocation ne peut pas voir un document non commite d'une instance soeur sur un poste partage (clause 3, `ADR-0085`) | Technique, architecture | theo-archi (route par l'instance B) | ADR-0085 | ouvert |
| **Cinq motifs d'exemption a comptes perimes** sur `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | Technique, garde-fou | theo-archi | - | ouvert, dette datee pour s34 (regle et corrections dans le meme commit) |
| **L'instruction 1 de `DEC-0061`** (ce que trois essais doit vouloir dire quand l'elimination est possible) | Pedagogie, a nadia-prof seule | nadia-prof, a son propre debit | DEC-0061 | ouvert, neuf sprints d'attente, porte a s34 |
| **Le perimetre corrige d'`EMC-CP-07`** (le chiffrage "aucun item neuf" etait faux) | Produit, capacite | camille-po, sofia-sm | - | ouvert, chiffrage a reprendre au planning de s34 |
| **Le littoral mort `"evenement culturel"`** dans `PaquetCm1HistoireGeographieTest`, vide par la correction orthographique du relecteur | Technique, garde-fou | theo-archi | - | ouvert |
| **L'hygiene de poste** : sessions de jeu manuel sur le jar tournant sur le worktree partage plutot que sur un worktree dedie, serveur non arrete | Qualite, capacite | yanis-qa (signale), sofia-sm | - | ouvert |
| **Trois questions de grain de `S33-CONS-4`** rendues par `camille-po` | Produit / pedagogie | nadia-prof | ADR-0081 | ouvert, sans reponse a la cloture |
| **Grain du lot de l'effort et du corps** : position convergente entre `camille-po` et `nadia-prof` | Produit / pedagogie | nadia-prof, pour confirmation | ADR-0081 | ouvert, se lit comme regle des confirmation |
| **Registre de dette** : la descente projetee a 2,5 ne tient pas, chiffre exact non recalcule | Produit, capacite | camille-po | - | ouvert, recalcul du a s34 |
| Six reserves neuves sur l'EMC CP (`REG-R4`, `REG-R5`, `PER-R3`, `EXC-R7` a `EXC-R9`) plus `EXC-R1` toujours impayee | Pedagogie, contenu | camille-po (stories), sofia-sm | - | ouvert |
| `EPS-8`, condition neuve sur les indices d'EPS | Pedagogie, condition opposable | toute l'equipe | - | clos - allouee au registre dans le meme diff |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement.

## Porte pedagogique - nadia-prof

- **Verdict, ligne par ligne** : `HG-CM1-02` VALIDE SOUS RESERVE, `HG-CM1-04` VALIDE SOUS RESERVE,
  `HG-CM1-08` VALIDE SOUS RESERVE (sur sa part servie) ; `HG-CM1-12` REFUSE (`HG-R17`, bloquante) ;
  `EMC-CP-07` REFUSE, quatrieme passage ; `EPS-CP-04` REFUSE, second passage (`SEC-R4`,
  bloquante) ; `EPS-CP-08` REFUSE, second passage (`JEU-R9`, bloquante) ; `CAT-1` PAYEE SOUS
  RESERVE sur `EMC-CP-02` et `EMC-CP-06`, sans rouvrir ni l'une ni l'autre ; `EXC-R5` LEVEE,
  l'increment `S33-EXC-R5` REFUSE ; `EXC-R1` NON PAYEE.
- **Fiches** : les trois verdicts d'instance, `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-
  a-s34/verdicts-nadia-instance-A.md`, `-B.md`, `-C.md`, et les sept fiches de conformite qu'ils
  mettent a jour.
- **Motif, en resume** : deux corrections d'histoire-geographie reussissent a la lettre et
  ferment (fait faux sur Clovis, terme "les Sarrasins") ; le remplissage de `HG-CM1-08`/`-12`
  ferme une moitie et echoue sur l'autre a cause du registre des distracteurs, discriminant sur
  toute la ligne ; `EMC-CP-07` echoue une quatrieme fois sur un defaut que `nadia-prof` elle-meme
  avait bloque par un chiffrage errone, et qu'elle corrige et leve dans son propre document ;
  l'EPS CP echoue deux fois de plus, sur l'exactitude d'une regle de securite routiere et sur la
  coherence interne d'un paquet avec lui-meme (deux items du meme jeu se contredisent) ; `CAT-1`
  reussit enfin au cinquieme sprint de report, avec deux reserves neuves sur la meme famille de
  defaut qu'elle payait.
- **Correction a ses propres constats, faite dans ses trois documents avant de s'en servir** :
  `HG-R9`/`HG-R10` corrigees de motif, `REG-R3` corrigee de chiffre (17 contre 6, pas 17 contre 1),
  `EMC-CP-07` corrigee de perimetre ("aucun item neuf" etait faux), `PAR-14` corrigee de portee
  (s'applique aussi a `S33-EPS-SEC`).
- **Reserves neuves** : `HG-R13` a `HG-R18` (instance A), `REG-R4`, `REG-R5`, `PER-R3`, `EXC-R7` a
  `EXC-R9` (instance B), `SEC-R4`, `SEC-R5`, `JEU-R9` a `JEU-R12`, `EPS-8` (instance C). Dix-neuf
  au total, dont trois bloquantes - `HG-R17` (sur `HG-CM1-12`), `SEC-R4` (sur `EPS-CP-04`) et
  `JEU-R9` (sur `EPS-CP-08`) - les seize autres sont non bloquantes.

## Les trois questions de grain routees a `nadia-prof` - un reste, pas un acquis

`arbitrage-produit-camille.md`, section 1.6, route trois questions precises pour fermer la decision
de grain de `S33-CONS-4` : confirmer ou corriger le regroupement des cinq taches deja servies en
autant de jeux futurs ; dire si les items neufs de `HG-CM1-08` et `HG-CM1-12` rejoignent une tache
existante ou en ouvrent une sixieme ; dire si `nadia-prof` retient plutot la phrase unique pour un
sous-ensemble plus restreint. **Aucune des trois n'a de reponse a la cloture de s33.** `S33-CONS-4`
reste bloquee exactement comme le planning l'a laissee. Ce n'est pas trancher a sa place que de le
dire ici : c'est constater qu'une decision conjointe (`ADR-0081`) a une moitie rendue et une
moitie qui attend.

## Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise par cette review : elle
enregistre les verdicts des trois instances de `nadia-prof`, deja arbitres et deja rediges, la
position produit de `camille-po` sur les deux questions de grain, et les rapports qualite,
ArchUnit et de relecture deja rendus. Les decisions du sprint, s'il y en a, sont celles listees
dans `RAPPORT.md`.
