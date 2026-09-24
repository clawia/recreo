# Review - s42 - fusion de deux sprints projetes (histoire-geographie CM1, questionner le monde
CP, arts plastiques CP, EMC CM1, mathematiques CP), deux instances de `nadia-prof`

- **Horodatage de debut** (`planning.md`) : 2026-09-24 17:26:51 (UTC+02:00, horodatage du commit
  `edbe8582`).
- **Horodatage de fin** : 2026-09-24 20:37:19 (UTC+02:00, horodatage du dernier commit `7389e9e1`).
- **Duree effective** : 3 h 10 min 28 s.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `7389e9e1`, `git status --porcelain` verifie vide a l'exception du
  meme fichier non suivi qu'a s41, `unmatched.log`, sans rapport avec ce sprint et non commite par
  personne durant s42 - je ne le touche pas, ce n'est pas mon perimetre. Fenetre `9fed12bc..7389e9e1`
  (avis de faisabilite de `theo-archi` jusqu'au dernier commit) : 15 commits, aucun commit de fusion
  au sens Git (`git log --merges` rend une liste vide) - contrairement a s41, les trois developpeurs
  de ce sprint ont chacun commite directement sur `round-two`, un seul lot a la fois, plutot que de
  fusionner depuis un worktree separe. Le point de vigilance de `planning.md` section 8 ("chaque
  developpeur fusionne son lot des qu'il est vert") est donc tenu en substance, sous une forme legere.

---

## 1. L'increment livre, en une phrase

Quatre lignes du programme passent de `non couvert` a `couvert-valide` (`HG-CM1-05` les rois de la
construction du royaume, `QLM-CP-06` la matiere des objets, `ART-CP-05` les couleurs, `EMC-CM1-05`
les symboles de la Republique) et une cinquieme (`MAT-CP-10`, deplacements sur un quadrillage) ouvre
en `en cours` sur sa seule moitie textuelle ; la couverture du programme passe de 94/156 (60,3 %) a
**98/156 (62,8 %)**.

## 2. Un episode a documenter honnetement avant le reste : le retrait de `S42-FR-LECTURE-SILENCIEUSE`

Le perimetre chiffre par `DEC-0097` portait six lignes, dont `S42-FR-LECTURE-SILENCIEUSE`
(francais CM1, lire silencieusement et repondre a une question de comprehension globale). Ce lot
n'est pas tombe sur un refus pedagogique : il est tombe sur une **mesure de rendu concrete**, rendue
par `ines-design` avant tout premier commit de contenu, exactement comme son role le demande.

`squad/conformite/mesure-ines-lis-pli-s42.md` (commit `ff808682`, hors sprint, avant l'ouverture du
planning) mesure le paquet au plafond haut exige par le critere d'acceptation de la story (70 mots
de `texteSupport`, 12 mots d'enonce, trois propositions en phrase courte), sur la reference de
`DEC-0040` (744 x 551 px), par capture du rendu reel via le protocole de deverminage du navigateur.
Verdict : le texte et l'enonce tiennent, avec de larges marges (185 px, 85 px) ; **le bloc des trois
propositions, lui, deborde de 357 px sous le pli** - une seule des trois est partiellement visible
(36 %), les deux autres entierement hors champ, sans defiler. `ines-design` ne conclut ni sur la
fermeture de la ligne ni sur une correction de forme (hors de son mandat par `CLAUDE.md`) : elle
route le fait a moi et le jugement de la reserve pedagogique a `nadia-prof`.

**Ce que le depot en a fait** : `camille-po`, avant l'ouverture du planning, amende `DEC-0098`
(commit `517abd9d`) - aucune ligne de tarif 1,0 disponible dans le vivier de `DEC-0097` sans
arbitrage neuf ni illustration a instruire pour remplacer le lot, les candidates a 1,5 reproduiraient
la marge de 0,5 deja identifiee comme risque a s41. **`S42-FR-LECTURE-SILENCIEUSE` est retiree sans
remplacement**, le perimetre du sprint tombe a cinq lots et la borne consommee a 7,0/8,0. La ligne
francais CM1 reste `non couvert`.

Je le nomme explicitement pour ce que c'est : **une ligne annoncee comme candidate solide, qui
echoue a une verification concrete de gabarit, pas a un jugement pedagogique.** `nadia-prof` n'a
jamais eu a se prononcer dessus - c'est exactement le role qu'`ines-design` est censee tenir, evitant
d'ecrire un paquet de contenu qui n'aurait servi a rien. Le planning de ce sprint (`edbe8582`) ne
rouvre pas ce retrait, il le tient pour tranche (`planning.md`, section 2).

## 3. Le Done technique - quatre jobs, relances individuellement par moi, sur l'arbre final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un agent
qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres. Les quatre
jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur `round-two` a
`7389e9e1` (`git status --porcelain` verifie vide avant et apres, hors `unmatched.log`) :

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** (celui que la commande resout, pas la toolchain declaree) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (19 cas, tous verts) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s -20 \| bash .github/scripts/verifier-prefixes.sh` | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, exactement comme le workflow l'ecrit | **vert**, **9 juge(s) pur(s) execute(s)**, 0 echec |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, deux methodes
independantes** (blocs `[INFO] Results:` par module, et somme des blocs `Tests run: ... -- in
<classe>` du meme journal) - **jamais la somme des lignes `Tests run:` des rapports `.txt`**
(methode imposee par `squad/README.md` section 4 depuis l'amendement de s11) :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 909 |
| recreo-bootstrap (surefire, unitaires) | 277 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1723** |
| **Total integration** | **6** |

**1729 tests, 0 echec, 0 erreur, 0 ignore**, les deux methodes concordant exactement (somme des six
blocs `Results:` = 1729 ; somme des blocs par classe du journal = 1729 sur 240 blocs de classe).

**Ce chiffre n'est pas celui de `yanis-qa` ni celui de `theo-archi`, et je nomme l'ecart plutot que
de le taire** - c'est exactement le geste que ce depot exige (`squad/README.md`, section 4 ;
`confirmation-archunit.md` de s41, section 1.1). `yanis-qa` (`rapport-qualite.md`, section 1, mesure
sur `s42-yanis` a `cdf0229f`) et `theo-archi` (`confirmation-archunit.md`, sections 1 et 1.1, mesure
sur `round-two` a `cdf0229f` puis `cb07884f`) annoncent tous les deux **1727**. **L'ecart de 2 est
reel et je le retrace jusqu'a sa cause** : trois commits s'intercalent entre `cb07884f` (dernier arbre
mesure par `theo-archi`) et `7389e9e1` (arbre final) - `773de974` (`lea-dev`, correction du refus de
`nadia-prof` sur `cp-deplacements-quadrillage`), `bf9cdc11` (`lea-dev`, correction de consigne),
`d88fb20c`/`cee3a7cf`/`7389e9e1` (allocation de registre et verdicts, `.md` seuls). Le commit
`773de974` reecrit `PaquetCpDeplacementsQuadrillageTest` - *"le test adapte en consequence [...] plus
une regression explicite sur les deux motifs du refus"* - et fait passer cette classe de **6 tests**
(compte de `yanis-qa` et de `theo-archi` sur `cdf0229f`) a **8 tests**, verifie par moi sur le journal
de mon propre lancement (`Tests run: 8 [...] -- in
com.recreo.adapter.content.PaquetCpDeplacementsQuadrillageTest`). **+2, exactement l'ecart entre 1727
et 1729**, et exactement l'ecart entre les 907 tests d'`adapter-content` mesures par `theo-archi` a
`cb07884f` et les 909 que je mesure a `7389e9e1`. Aucune autre classe ne bouge : la correction du
refus est posterieure a la derniere mesure de `theo-archi`, qui ne pouvait pas la voir.

**ArchUnit, recompte par moi de facon independante** (occurrences litterales de `static final
ArchRule` et `@ArchTest` sous `src/test/java` des cinq modules du pom agregateur, `target/` et
l'arbre de contre-exemple exclus - methode d'`ADR-0095`/`ADR-0098`/`ADR-0099`) : **36 `static final
ArchRule`, 35 `@ArchTest`** - inchange depuis s41 et depuis l'avis de faisabilite. Coherent avec le
diff de `773de974` (uniquement un fichier de contenu YAML et un fichier de test de paquet, aucune
`ArchRule`).

## 4. La confirmation ArchUnit de `theo-archi`

`squad/sprints/s42-fusion-six-lignes-deux-instances/confirmation-archunit.md` verifie, sur l'arbre
integre a `cdf0229f` puis remesure a `cb07884f` (avant les corrections de `lea-dev` citees ci-dessus,
qui ne portent aucun fichier de code ni de contenu au-dela du paquet et de son test), trois questions
posees par son propre avis de faisabilite (`9fed12bc`) : les tests ArchUnit restent verts sur le code
neuf des cinq lots (oui, et **zero ligne de Java de production** sur les cinq lots) ; le verdict
*"aucun `ADR`, aucune regle ArchUnit neuve"* tient (oui, compte inchange 36/35, aucun `ADR` ecrit) ;
`HIS-1` sur les items neufs de `S42-HG-ROIS` respecte le plafond du lot HG CM1 entier (oui, **115
items, 23 restitutions, plafond 38, marge 15** - mais **la marge reellement disponible avant que le
garde-fou rougisse est 9, pas 15**, prouve par mutation directe sur le test voisin qui greffe six
items synthetiques sur le materiau reel).

**Un fait signale, non tranche par `theo-archi`, route a moi et a `nadia-prof`** :
`PaquetCm1HistoireGeographieTest` porte une methode neuve,
`hgCm105NePorteAucunJugementDeValeurSurUnRoi`, dont la Javadoc annonce garder `BIA-5` ("chaque item
de `HG-CM1-05` [...] jamais un jugement de valeur sur le personnage"), mais dont le corps ne verifie
que l'appartenance de la bonne reponse aux quatre noms de rois - **prouve par mutation** : un enonce
remplace par un jugement de valeur explicite laisse le reacteur entier vert. `nadia-prof` avait deja
constate ce meme fait de son cote, independamment, dans son verdict du terrain 1 (`1d5c00de`) : "le
test qui cite `BIA-5` ne verifie que l'appartenance [...] et ne couvre pas cette propriete : elle
reste entierement ma garde". Le fond - l'absence de jugement de valeur sur les quatre rois - est
verifie et tenu, par `nadia-prof`, en jouant. Ce qui reste ouvert est une question de **tenue de
test** (le nom et la Javadoc promettent plus que le corps ne verifie) : je la traite en obstacle
ouvert, section 7 ci-dessous, plutot que de trancher moi-meme entre les deux corrections possibles
que `theo-archi` nomme (renommer le test pour ce qu'il verifie reellement, ou y ecrire explicitement
que `BIA-5` reste la garde de `nadia-prof`).

**Aucun `ADR` ni aucune regle ArchUnit neuve n'est ecrit ce sprint.** `git diff --stat
9fed12bc..7389e9e1 -- squad/decisions/adr/` rend un diff vide, verifie par moi independamment de
`theo-archi`.

## 5. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s42-yanis`, lignee verifiee par
`outils/verifier-lignee.sh`, arbre `cdf0229f`) : Done technique confirme sur les quatre jobs, tous
nommes individuellement, **aucun verrou `mvnd` transitoire rencontre**. Quatre garde-fous mordus par
mutation sur le materiau **reellement servi**, restauration verifiee par `git status --short` vide
apres chaque mutation :

- `HIS-1` sur `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (plafond calcule sur
  le lot HG CM1 entier, pas sur un fichier) : 16 marques `association` passees en `restitution` ->
  rouge, message exact cite ; restaure -> vert.
- `ART-1` sur `PaquetCpArtsCouleursTest` (patron d'enonce interdit) : "de quelle couleur est" ->
  rouge ; restaure -> vert.
- Absence d'illustration sur `PaquetCpDeplacementsQuadrillageTest` : une illustration ajoutee -> rouge
  ; restaure -> vert.
- Discriminant textuel de `S42-EMC-SYMBOLES-CM1` vis-a-vis de `cp-emc-symboles` : le patron de
  question du CP recopie -> rouge ; restaure -> vert.

Tracabilite des criteres d'acceptation des cinq stories, lot par lot (section 3 du rapport) : chaque
code de condition (`FER-1` a `FER-3`, `ART-1`, `HIS-1` a `HIS-3`, `BIA-5`) est verifie encode dans un
test nomme, avec ce qui reste garde de `nadia-prof` explicitement note quand un motif n'est pas
automatisable - sauf sur `BIA-5`, ou le rapport range la methode nominale dans la colonne des
criteres encodes sans avoir mute le test, ce que `theo-archi` corrige en section 4 (voir section 4
ci-dessus).

## 6. Le `relecteur-pedagogique`

**Aucune intervention du `relecteur-pedagogique` dans s42** : `git log 9fed12bc..7389e9e1 --grep
"\[REL\]"` ne rend aucun commit. Rien dans les fiches de conformite ni dans le rapport qualite ne
signale de defaut de redaction (orthographe, accentuation, ponctuation) sur les cinq paquets.

## 7. Le Done pedagogique - cinq verdicts, rapportes par cette review, pas prononces par elle

Deux instances paralleles de `nadia-prof` (`DEC-0098`, amendee), bornees a 3,0/5,0 (instance A) et
4,0/5,0 (instance B), total 7,0/8,0 apres le retrait de `S42-FR-LECTURE-SILENCIEUSE`.

| Ligne | Instance | Trajectoire | Verdict final | Fiche |
|---|---|---|---|---|
| Les rois de la construction du royaume (`HG-CM1-05`) | A | Auditee une fois, aucun refus | **VALIDE SOUS RESERVE** (`HG-R23` a `HG-R25`, non bloquantes) - `couvert-valide` | `FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md`, `...situer-dans-le-temps-cm1.md` |
| La matiere des objets (`QLM-CP-06`) | A | Auditee une fois, aucun refus | **VALIDE SOUS RESERVE** (`MOB-R1` a `MOB-R4`, non bloquantes) - `couvert-valide` | `FICHE-bandit-manchot-matiere-objets-cp.md` |
| Les couleurs (`ART-CP-05`) | B | Auditee une fois, aucun refus | **VALIDE**, sans reserve - `couvert-valide` | `FICHE-bandit-manchot-couleurs-arts-cp.md` |
| Les symboles de la Republique CM1 (`EMC-CM1-05`) | B | Auditee une fois, aucun refus | **VALIDE SOUS RESERVE** (`R-EMC-CM1-05-1` a `-4` ; reserve 1 : la difference promise avec le CP ne tient reellement que sur 5 items sur 13, le reste recoupant deja le CP sans qu'aucun garde-fou technique ne le compare) - `couvert-valide` | `FICHE-bandit-manchot-symboles-cm1.md` |
| Deplacements sur un quadrillage, moitie textuelle (`MAT-CP-10`) | B | **REFUSE** (deux motifs chacun suffisant : substitution de mots sans raisonnement spatial reel, deux items sans etat initial deductible) -> corrige (`lea-dev`, `773de974`, `da1b0b61`, `7317b990`) -> **VALIDE SOUS RESERVE** au second passage | `en cours`, couverture partielle (`R-MAT-CP-10-1`, `-1 bis`, `-2`, reserves neuves du second passage, non bloquantes) | `FICHE-bandit-manchot-deplacements-quadrillage-cp.md` |

**Un refus sur cinq lots, corrige et revalide dans le meme sprint.** Comme a s41, aucun des deux
motifs de refus n'a ete vu par la CI : ce sont des jugements sur la nature de la tache reelle
proposee a l'enfant (une substitution de mots n'est pas un raisonnement spatial) et sur la
deductibilite d'une reponse depuis son enonce - des lectures de sens qu'aucun garde-fou de ce depot
ne rend. `MAT-CP-10` ne ferme pas : la mention de couverture partielle, deja prescrite par l'avis de
faisabilite de `theo-archi` (section 7 de `planning.md`) et confirmee au second passage, remplace
celle qui etait ecrite avant s42 plutot que de s'y ajouter.

**La mention exigee d'avance sur toute ligne d'EMC CM1** ("connaissances civiques et reconnaissance
de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision
collective ne sont servis par ce produit") et celle exigee d'avance sur `ART-CP-05` ("vocabulaire et
connaissances de la pratique plastique seuls ; aucune production plastique n'est servie par ce
produit") sont toutes deux portees, verifiees mot pour mot contre `squad/programme/MATRICE-couverture.md`.

## 8. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Fusion de deux sprints projetes (`DEC-0097`) en un seul sprint a deux instances (`DEC-0098`), sur le modele de s41 | Gouvernance / produit | `camille-po` | **Resolu** - `DEC-0098`, Acceptee, amendee le meme jour |
| `S42-FR-LECTURE-SILENCIEUSE` : verdict negatif de la mesure de pli d'`ines-design` (357 px de debordement sur le bloc des trois propositions) | Design / gabarit, hors du champ de `nadia-prof` | `ines-design` mesure, `camille-po` tranche le perimetre | **Resolu** - `DEC-0098` amendee (`517abd9d`), retrait sans remplacement, ligne francais CM1 reste `non couvert`. Detail : section 2 ci-dessus |
| `MAT-CP-10` : refuse au premier passage sur deux motifs (substitution de mots sans raisonnement spatial, items sans etat initial deductible) | Pedagogie | `nadia-prof` (arbitre), `lea-dev` (corrige) | **Resolu** - second passage **VALIDE SOUS RESERVE** (`cee3a7cf`), les deux motifs payes, trois reserves neuves non bloquantes |
| Consigne du catalogue laissee fausse apres la premiere correction de `lea-dev` sur `cp-deplacements-quadrillage` (parlait encore de "geste" pour un jeu qui demande desormais une case) | Qualite / coherence de contenu | Trouve et corrige par `lea-dev` elle-meme | **Resolu** - `bf9cdc11`, coherent avec `TexteAccentueExposeParLePortEntrantTest` mis a jour dans le meme commit |
| `nadia-prof` a ecrit sept identifiants de reserve (instance A, `HG-R23-25`, `MOB-R1-4`) dans ses fiches sans les allouer dans `REGISTRE-identifiants.md` dans le meme commit, causant un rouge `PAR-5` sur `round-two`, trouve par `lea-dev` | Qualite / gouvernance des identifiants | `nadia-prof` (repare elle-meme) | **Resolu** - `d88fb20c`, sept identifiants alloues, 682/111 -> 689/112, reacteur complet relance en vert. **C'est la deuxieme fois en deux sprints consecutifs que `nadia-prof` cause ce meme rouge de la meme facon** (premiere fois : s40) - elle l'ecrit elle-meme dans sa note de reparation. Detail process : `retro.md`, section 2 |
| `BIA-5` : la methode de test qui pretend garder ce critere sur `HG-CM1-05` ne verifie en realite que l'appartenance de la bonne reponse aux quatre noms de rois, pas l'absence de jugement de valeur - signale independamment par `theo-archi` (mutation) et par `nadia-prof` (lecture) | Qualite / tenue de test (nommage et Javadoc trompeurs, pas un defaut de fond) | `theo-archi` route le choix de correction a `sofia-sm` ; le fond reste la garde de `nadia-prof` | **Ouvert, non tranche a la cloture** - deux corrections possibles nommees par `theo-archi` (renommer le test et sa Javadoc pour ce qu'ils verifient reellement, ou y ecrire explicitement que `BIA-5` reste la garde de `nadia-prof`) ; aucune des deux n'a ete faite ce sprint. Non bloquant pour le Done pedagogique : `nadia-prof` a deja verifie le fond en jouant |
| Sept identifiants de reserve de l'instance B (`R-EMC-CM1-05-1` a `-4`, `R-MAT-CP-10-1`, `-1 bis`, `-2`) ne correspondent a **aucune des deux formes** que le garde-fou `PAR-5`/`ADR-0085` sait lire (`[A-Z]{2,5}-R?[0-9]{1,2}` ou son equivalent a trois segments) - verifie par moi par grep direct sur les fiches et sur `REGISTRE-identifiants.md`, absence confirmee des deux cotes, et par relecture des deux expressions regulieres du garde-fou. Ces sept identifiants echappent donc entierement a la garantie d'unicite que `PAR-5` existe pour tenir, sans qu'aucun rouge ne le signale | Technique / architecture (portee d'un garde-fou), et convention de nommage des reserves de `nadia-prof` | `theo-archi` (le garde-fou lit-il la bonne forme, doit-il l'etendre), `nadia-prof` (uniformiser la convention de nommage de ses reserves sur la forme a deux ou trois segments deja alloue-able, comme `HG-R23` ou `DVS-CP-R1`) | **Ouvert, non tranche** - trouve par moi a la cloture, aucun rouge de CI ne l'a signale, aucune collision constatee a ce jour. Non bloquant pour ce sprint, a peser avant qu'une troisieme instance parallele n'augmente le risque de collision silencieuse |
| `MAT-CE1-18` (sens de la division), signale absent de `MATRICE-couverture.md` a chacun des sprints s36 a s40, rupture du signalement a s41 | Gouvernance / produit | `camille-po` | **Resolu, hors sprint** - corrige avant l'ouverture de s42 (commit `c684f86a`, note du 2026-09-24 dans `MATRICE-couverture.md`), denominateur passe de 155 a 156. Verifie par moi : `grep -n "MAT-CE1-18"` rend desormais une occurrence |
| `SEM-1`/`QLM-CP-13` (double-compte entre `cp-semaine` et questionner le monde) | Gouvernance / produit | `camille-po`, `nadia-prof` | **Deja clos avant s42** - traite par l'addendum du 2026-09-24 a `review.md` de s41 (`DEC-0096`, `d3216c84`, second verdict VALIDE SOUS RESERVE). Non repris ici, rien de neuf ce sprint |
| Absence de marge de rejeu sur le terrain EMC de s41 (materialisee par le double refus d'`EMC-CP-08`) | Gouvernance / produit | `camille-po`, l'utilisatrice | **Toujours ouvert, non tranche a s42** - `DEC-0098` fixe une marge individuelle de 1,0 par instance sur s42 (mieux que les 0,5 de s41), mais aucune `DEC` ne reconcilie le depassement deja survenu a s41. Je ne le retranche pas ici, il reste route comme a s41 |

## 9. Le registre de dette - inchange a la cloture de s42

**Ouverture de s42 : 9,25** (`squad/sprints/s41-fusion-emc-sciences-qlm/review.md`, section 8).
Verifie par moi : recherche des identifiants de ce sprint (`HG-CM1-05`, `QLM-CP-06`, `ART-CP-05`,
`EMC-CM1-05`, `MAT-CP-10`, `DEC-0097`, `DEC-0098`) dans `DEC-0086` et `DEC-0087` : aucune occurrence
dans les deux textes. Ce sprint ouvre cinq lignes hors du vivier `DEC-0086`/`DEC-0087`, comme s39,
s40 et s41. **Registre de dette a l'ouverture d'un sprint futur : 9,25, inchange.**

## 10. La couverture du programme

**98/156 (62,8 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, commit `7389e9e1`
(`[CAM] s42: mise a jour matrice - quatre fermetures et une ouverture partielle`) : quatre fermetures
(`HG-CM1-05`, `QLM-CP-06`, `ART-CP-05`, `EMC-CM1-05`, toutes `non couvert` -> `couvert-valide`), une
ouverture partielle (`MAT-CP-10`, `non couvert` -> `en cours`), aucune fermeture supplementaire au-dela
de ce que `nadia-prof` a valide.

## 11. L'avis de `theo-archi` et le lien vers `DEC-0097`/`DEC-0098`

`squad/sprints/s42-fusion-six-lignes-deux-instances/avis-faisabilite.md` (`9fed12bc`) rend un verdict
sur les cinq lots issus de l'amendement de `DEC-0098` : trois lots faisables en l'etat
(`S42-QLM-MATIERE`, `S42-ART-COULEURS`, `S42-MAT-TEXTUEL`), deux sous condition (`S42-HG-ROIS`, dont
le fichier nomme par la story n'existe plus, et `S42-EMC-SYMBOLES-CM1`, dont aucun garde-fou ne
compare au paquet CP deja servi). Les deux conditions sont levees par mon propre `planning.md`,
sections 5 et 6 : transmises telles quelles aux developpeurs et a l'instance B de `nadia-prof`, sans
que ce planning ne les tranche lui-meme. Aucun `ADR` ni aucune regle ArchUnit neuve annoncee comme
due, verdict confirme sur l'arbre integre (section 4 ci-dessus). `DEC-0097` (proposition de
perimetre chiffre pour s42 et s43, six lignes, `squad/decisions/produit/DEC-0097-proposition-de-
perimetre-pour-s42-et-s43.md`) reste `Proposee`, non engagee en tant que telle - c'est `DEC-0098`
qui l'engage en la fusionnant a un seul sprint. `DEC-0098`
(`squad/decisions/produit/DEC-0098-fusion-de-s42-et-s43-en-un-seul-sprint-deux-instances.md`) est
**Acceptee, amendee le 2026-09-24** : la borne a deux instances (8,0) est verifiee non revisee
depuis s41, et le total reellement consomme (7,0/8,0) reste sous la borne apres le retrait de
`S42-FR-LECTURE-SILENCIEUSE`, avec une marge de 1,0 non allouee au niveau du sprint.

## 12. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les cinq verdicts de `nadia-prof` (section 7) sont deja
  prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po`.
- Elle ne tranche pas la tenue du test `BIA-5` (section 4, section 8) ni la portee du garde-fou de
  registre sur les identifiants a cinq segments de l'instance B (section 8) : les deux restent
  routes, non bloquants.
- Elle ne rouvre pas le retrait de `S42-FR-LECTURE-SILENCIEUSE` ni les tarifs et exclusions de
  `DEC-0097` : deja tranches avant l'ouverture du planning (section 2).
- Elle ne corrige pas le gap `MAT-CE1-18`, deja corrige avant l'ouverture de ce sprint par
  `camille-po`.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** La decision du sprint a ete prise
ailleurs et est enregistree ici pour memoire, verifiee physiquement presente sur le disque, avec son
statut, avant d'etre citee :

- `DEC-0098` - fusion de s42 et s43 en un seul sprint, deux instances paralleles, amendee le
  2026-09-24 pour retirer `S42-FR-LECTURE-SILENCIEUSE` apres verdict negatif de la mesure de pli
  d'`ines-design` - **Acceptee, amendee le 2026-09-24**. Verifiee dans
  `squad/decisions/produit/DEC-index.md` et sur le disque.

**`DEC-0097` reste `Proposee`**, non engagee en tant que telle, son perimetre chiffre etant repris et
amende par `DEC-0098` (verifie dans `DEC-index.md`).

**Aucun `ADR` n'est cree ni amende ce sprint** (section 4 ci-dessus, verifie par diff independant).
**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
