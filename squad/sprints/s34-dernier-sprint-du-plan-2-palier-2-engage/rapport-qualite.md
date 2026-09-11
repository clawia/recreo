# Rapport qualite - Sprint 34

**Auteur** : `yanis-qa`. **Commit mesure** : `a7852398` (tete de `round-two`, fusion des quatre
branches de s34, `git status --short` vide sur le worktree principal avant toute mesure - aucun
fichier non commite d'un autre agent n'y trainait cette fois, contrairement a s33).

**JDK mesure** : 21.0.10, rapporte par la machine virtuelle des tests elle-meme ("Starting ...
using Java 21.0.10", logs Spring Boot de `CatalogueChargeableIT` et consorts), pas par une entree
de toolchain.

**Methode.** Le worktree principal etait deja propre : je mesure directement dessus, sans worktree
isole. Trois worktrees supplementaires ont ete crees et detruits pendant cette review, pour des
mesures ponctuelles a des commits differents de `HEAD` (section 3) ; aucun n'a laisse de trace.

---

## 1. Porte technique : les quatre jobs, un par un, chacun relance par moi

| Job | Commande exacte | Verdict |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 48635ba0..HEAD \| verifier-prefixes.sh` | **VERT.** *"Tous les commits portent un prefixe d'agent connu."* (15 cas d'auto-test, plus la plage complete de s34 depuis la cloture de s33) |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| verifier-chemins.sh` (commande exacte du workflow) | **VERT.** *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* (18 cas d'auto-test un par un) |
| `outils` | boucle du workflow sur `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **VERT.** *"9 juge(s) pur(s) execute(s)."*, aucun motif vide, aucun echec |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet a six modules | **VERT.** `BUILD SUCCESS`, six modules `SUCCESS` (`domain` 22 s, `application` 6 s, `adapter-web` 1 min 05, `adapter-content` 48 s, `bootstrap` 3 min 02, total 5 min 25) |

**Une premiere tentative sur `chemins` a d'abord ete lancee sans l'exclusion que le workflow
declare** (`git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'`) : jouee sur l'union de
`git ls-files` et `git ls-files --others --exclude-standard` sans cette exclusion, elle rend 10
occurrences - toutes dans les fixtures d'auto-test du garde-fou lui-meme, qui existent precisement
pour porter ce motif et servent a prouver que le garde-fou mord. Corrigee : la commande du workflow
exclut ce fichier par construction, et la version exacte du job est verte. Nomme pour ne pas
laisser croire que la premiere tentative etait un rouge reel du depot.

Le job `build` n'est pas seul mesure : voir section 2 pour un second `mvn -B verify` (sans
`clean`), lance independamment et convergent.

---

## 2. Compte de tests, methode dans le meme paragraphe

**Methode** : somme de l'attribut `tests` des fichiers `TEST-*.xml` (`surefire-reports` et
`failsafe-reports` de chaque module) sur le disque, apres `bash outils/mvn-jdk21.sh -B clean
verify` sur la racine. Somme des `failures` et `errors` par la meme methode. **Mesure faite deux
fois, sur deux executions completes et independantes du reacteur** (`clean verify`, puis un second
`verify` sans `clean` qui reexecute tous les tests) : les deux convergent a l'identique.

- **196 fichiers `TEST-*.xml`, 1464 tests, 0 echec, 0 erreur.** Je confirme le chiffre annonce par
  `sofia-sm` (planning.md, section 14).
- Repartition par module, identique aux deux mesures et identique poste par poste a celle de
  `sofia-sm` : `domain` 111, `application` 13, `adapter-web` 397, `adapter-content` 699,
  `bootstrap` 244.
- Recoupement partiel par une troisieme lecture, les totaux Maven eux-memes (pas une somme de
  fichiers) : `adapter-content` annonce `Tests run: 699, Failures: 0, Errors: 0` dans sa propre
  sortie console du second `mvn verify` - identique au chiffre de fichier.

**Aucun module sans test** (fichiers `*.java` sous `<module>/src/test`, `git ls-files`) : `domain`
16, `application` 3, `adapters/adapter-web` 51, `adapters/adapter-content` 96, `bootstrap` 84.
**Correction d'un chiffre du rapport de s33** : il annoncait `bootstrap` a 90. Recompte sur le
commit qu'il citait lui-meme (`b45e5927`, `git ls-tree`), `bootstrap/src/test` porte 84 fichiers
`.java`, pas 90 ; 90 est exactement 84 (test) + 6 (`bootstrap/src/main`), ce qui suggere que la
mesure de s33 additionnait main et test sans le dire. Cela ne change aucune conclusion - le module
avait des tests, et en a toujours -, mais un chiffre de couverture qui melange deux populations
sans le nommer est le defaut meme que ce depot sanctionne ailleurs. Signale, pas recompte plus
loin : je n'ai pas la trace de la commande qui a produit 90.

**36 declarations `ArchRule`** (`grep -rn "static final ArchRule "`, hors `target/`) : inchange
depuis s32-s33. Coherent avec `ADR-0090` : sa Javadoc dit elle-meme pourquoi la regle centrale du
sprint n'est pas une `ArchRule` (population derivee du bytecode, jugement sur un scenario joue, pas
sur une forme de type) - aucune regle ArchUnit neuve n'etait attendue.

**Catalogue : 70 entrees** (`grep -c "^  - id:" catalogue.yaml`), +2 sur les 68 de s33 -
`cp-eps-corps-effort` (`sofia-sm`) et `cp-frise-de-la-journee` (`lea-dev`), les deux jeux neufs du
sprint, coherent avec le planning.

---

## 3. Les tests ajoutes par s34 mordent-ils reellement ? Trois points precis, mis a l'epreuve

### 3.1 `ADR-0090` : le rouge se referme-t-il exactement a la fusion, sur la mecanique `Designation` ?

`UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest` derive sa population par
`ClassFileImporter` sur `com.recreo.domain.mechanics` : `chaqueMecaniqueConcreteDuDomainePorteSonScenario`
compare cette population a la liste des trois scenarios ecrits dans le fichier
(`BanditManchot`, `Designation`, `Saisie`) - **la mecanique `Designation` porte bien son propre
scenario**, avec ses `ZoneADesigner` et son `DesignationMove`, pas seulement `BanditManchot`.

**La reussite de la fusion n'est pas prise sur la parole de `sofia-sm` : je l'ai rejouee.**

- Worktree isole sur `2c3463b3` (le commit qui introduit `ADR-0090` et son test, **avant** que la
  branche `s34-sofia` porteuse du remede des trois sessions n'y soit fusionnee) :
  `bash mvn-jdk21.sh -B -pl domain -am test -Dtest=UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest`
  rend **ROUGE** - `uneMancheTrouveeApresUnEssaiFauxNEstPasComptee` echoue sur `BanditManchot`,
  *"expected: 0 but was: 1"*.
- Worktree isole sur `5bb6f9db` (le merge commit ou `s34-theo` rejoint l'arbre qui porte deja
  `s34-sofia`, donc le remede des trois sessions) : la meme commande rend **VERT**, 3/3.

Le rouge se referme exactement au point que `sofia-sm` decrit (planning.md, section 14) : au
merge de `s34-theo`, pas avant, pas apres. Verifie sur les deux bornes, pas suppose sur l'une.

### 3.2 Les cinq motifs d'exemption a comptes perimes, recomptes

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` porte
**55 entrees** (`grep -oP` sur les cles, toutes distinctes - aucune n'apparait deux fois), contre
53 a l'arbre `48635ba0` que `theo-archi` cite dans son avis. **+2, exactement les deux entrees
neuves du sprint** (`cp-eps-corps-effort`, `cp-frise-de-la-journee`), concatenees sans perte au
conflit de fusion nomme par `sofia-sm` (planning.md, section 14) - verifie, les deux cles
coexistent.

**Les cinq motifs que `theo-archi` a corriges a l'ouverture du sprint, recomptes par moi
independamment** (comptage direct sur le paquet servi, `yaml.safe_load` + somme
`1 + len(distracteurs)`) :

| Entree | Motif ecrit dans le test | Mesure independante |
|---|---|---|
| `cp-qlm-corps-sens` | 14 items, 42 propositions | **14 items, 42 propositions** |
| `cp-qlm-etats-eau` | 14 items, 30 propositions | **14 items, 30 propositions** |
| `cp-qlm-vivant-non-vivant` | 14 items, 31 propositions | **14 items, 31 propositions** |
| `cm1-anglais` | 25 items, 75 propositions | **25 items, 75 propositions** |
| `cm1-synonymes` | 16 items, 48 propositions | **16 items, 48 propositions** |

**Aucun compte perime.** Les cinq motifs corriges tiennent toujours a la fusion, y compris apres
que `marc-dev` a touche `cm1-anglais.yaml` (`S34-ANG-CORR`, reformulation de quatre items sans
ajout ni retrait) - le changement ne touchait pas le compte, et le motif ne l'a donc pas suivi
faussement. Le garde-fou qui rendrait ce recompte inutile existe deja et est vert dans le build :
`chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` compare les 55 motifs au paquet servi a
chaque execution, et `uneDeclarationQuiSurvitASonPaquetFaitEchouerLeGardeFou` interdit qu'une
entree survive a la disparition de son paquet. Mon recompte manuel les recoupe, il ne s'y
substitue pas.

### 3.3 `PaquetCpEmcExclusionTest` : le defaut "vert sur une population vide", corrige et remis a l'epreuve

Le fait, tel que `theo-archi` l'a mesure et ecrit avant que `marc-dev` n'ecrive le code
(`avis-theo-quatre-tests-de-cp-emc-exclusion.md`) : sous une sonde de refonte, une methode du
paquet (`laTacheDAdulteNeSertPlusDeCandidatAuRabais`) devenait **verte a vide** - sa boucle
parcourait une liste que la refonte venait de vider, et son corps ne s'executait donc jamais.
`marc-dev` a reecrit les quatre methodes fautives ; la tache B se definit desormais comme le
complement de la tache A (exhaustif par construction), et le garde-fou de regression sur le bouton
au rabais est reecrit sur le **paquet entier**, plus jamais sur une des deux taches.

**Je n'ai pas pris la correction pour acquise : deux mutations, dans un worktree isole sur `HEAD`.**

1. **Reintroduction du bouton au rabais.** Un distracteur de l'item `item-6` remplace par
   `"à personne, il ne faut rien dire"`, sans toucher au reste. `mvn -pl adapters/adapter-content
   -am test -Dtest=PaquetCpEmcExclusionTest` : **ROUGE**,
   `laPropositionAuRabaisAPersonneNApparaitDansAucuneTache` echoue en nommant l'item et la
   proposition fautive. Restaure, reverifie vert (7/7).
2. **Reponse invariante de la tache B.** Les cinq bonnes reponses de la tache B forcees a la
   meme valeur (`"en parler à un surveillant de la cour"`). Meme commande : **ROUGE**,
   `laTacheBNePorteJamaisUneBonneReponseUnique` echoue - *"Expecting size ... to be greater than 1
   but was 1"*. Restaure, reverifie vert (7/7).

Les deux garde-fous mordent reellement sur les gestes qu'ils ont ete ecrits pour attraper, et le
defaut a vide que `theo-archi` avait trouve n'a pas de survivant dans les quatre methodes
reecrites : aucune des sept methodes du fichier actuel ne boucle sur une liste qui pourrait se
vider sans qu'un test dedie (`fer3...`, avec ses deux `isGreaterThan(0)`) ne le remarque d'abord.

### 3.4 Les deux tests neufs d'`adapter-web` (palier 2) : deja auto-eprouves a l'ecriture

`AucuneZoneDesignableNeDescendSousLaCibleTactileTest` et
`ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` sont les deux seuls fichiers de test neufs
d'`adapter-web` ce sprint (avec le fichier `domain` de la section 3.1, ce sont les trois seuls
fichiers de test entierement neufs du sprint - `git diff --diff-filter=A`). Chacun porte ses
propres mutations dans le meme fichier (`uneZoneRetrecieSousLePlancherFaitEchouerLeGardeFou`,
`leJeuFautifCommiteEstRefuse`, quatre "morsures" sur l'autre), et chacun garde une clause
`isNotEmpty` explicite avant de juger, avec un commentaire qui la motive ("`ADR-0003` : ce
garde-fou ne prouve rien si aucune zone/aucun bouton n'est servi"). Je n'ai rien trouve a muter
moi-meme sur ces deux fichiers que leurs propres tests ne mordent deja.

### 3.5 Une fragilite non fermee, du meme registre - nommee, pas corrigee ici

`PaquetCpEmcRegleSouhaitLoiTest` porte deux methodes qui bouclent sur une population sans leur
propre garde de non-vacuite
(`chaqueItemDeLaTacheDePorteeProposeExactementDeuxDistracteurs`,
`chaqueItemDeLaTacheDeClassementProposeUnOuDeuxDistracteurs`) : si l'une des deux taches se vidait,
ces deux methodes passeraient au vert sans rien juger, exactement la famille de defaut de la
section 3.3. Ce n'est pas vacuité aujourd'hui - `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems`,
dans le meme fichier, garde deja les deux tailles a `isGreaterThan(0)` -, mais la garantie tient
par un test **voisin**, pas par la methode elle-meme : si `fer3...` etait retiree ou modifiee
independamment, personne ne le remarquerait avant que l'une des deux boucles ne se mette a juger
zero item. Je ne le corrige pas, je le nomme : c'est exactement le motif qu'`ADR-0013` demande de
rendre visible, et cette instance-la ne l'est pas encore.

---

## 4. Ce qui n'est pas couvert, et le cout de ce que j'avais signale a s33

- **Aucune trace, dans le depot Git, d'une partie jouee de bout en bout sur le jar pour le contenu
  corrige ce sprint** (`HG-CM1-12`, `cm1-anglais`, `cp-emc-exclusion`, `cp-emc-regle-souhait-loi`,
  les trois paquets d'EPS) - le meme trou signale a s32 et a s33, **non paye pour un troisieme
  sprint**. La seule trace d'un jar reellement joue ce sprint concerne le palier 2
  (`squad/sprints/.../planning.md`, section 15.2 : `lea-dev` mesure au jar, Chrome headless, et
  retire `cp-plan-de-la-classe.yaml` avant de commiter un defaut qu'elle y a trouve) - **et c'est
  la preuve que cette pratique marche** : un defaut reel (libelles qui debordent de leur bouton) a
  ete trouve en jouant, pas en lisant un rapport vert, exactement la regle que je me donne
  moi-meme. Verifie : `cp-plan-de-la-classe.yaml` et l'illustration de production correspondante ne
  sont ni l'un ni l'autre dans le depot (`git ls-files` ne rend rien sous `adapters/*/src/main` ;
  seul un `plan-classe.svg` de reference vit sous `squad/sprints/.../`, hors production).
- **La dette generale de `ADR-0089` (25 fichiers sur 95 tests de paquet qui lisent une tache par
  operation de chaine) n'a pas ete recomptee ni reduite ce sprint.** `theo-archi` la re-cite au
  meme chiffre dans son avis du jour ; la refonte de `S34-EXC-TACHEB` deplace le critere de
  `cp-emc-exclusion` de la bonne reponse vers une sous-chaine de l'enonce (`"Qu'est-ce que
  c'est"`) - une meilleure forme au sens ou elle rend le partage exhaustif par construction (section
  3.3), mais qui reste dans la meme famille de dette, pas hors d'elle.
- **Le jugement de sens** (`CAT-1`, `HIS-2`, `HIS-3`, `BIA-5` et la justesse factuelle des six
  reprises du sprint) reste hors du perimetre de tout automate du depot - frontiere deja actee, pas
  un trou de s34.
- **`S34-CONS-4` n'a pas ete commencee** (planning.md, section 15.1) : aucun fichier de code ni de
  test ne lui appartient dans cette fusion. Rien a auditer, le poste part a s35 tel quel.
- **`squad/programme/MATRICE-couverture.md` n'a pas bouge** (`git diff` vide entre l'ouverture et
  la fusion) : conforme au planning, aucune instance de `nadia-prof` n'a encore audite ce sprint au
  moment de cette review.

---

## 5. Tests instables

**Aucun identifie.** Deux executions completes du reacteur (`clean verify`, puis `verify` sans
`clean`) rendent le meme compte de tests (1464), le meme compte de fichiers (196), 0 echec et 0
erreur dans les deux cas. Les executions ciblees de la section 3 (`UneMancheResolueApres...`,
`PaquetCpEmcExclusionTest`, mutees puis restaurees) sont deterministes d'une commande a l'autre.

---

## 6. Mon avis sur la porte technique

**Verte**, mesuree sur `a7852398` : les quatre jobs de `.github/workflows/ci.yml` passent un par
un (section 1), 1464 tests sans echec ni erreur sur deux executions independantes (section 2), et
les trois points precis que la review demandait de verifier tiennent a l'epreuve et pas seulement
a la lecture (section 3) : `ADR-0090` referme un rouge reel exactement au commit annonce et couvre
bien `Designation` ; les cinq motifs d'exemption recomptes sont exacts et aucun n'est perime ; le
defaut "vert a vide" de `PaquetCpEmcExclusionTest` est corrige et les deux garde-fous qui le
remplacent mordent sur les deux mutations que j'ai jouees.

Une fragilite du meme registre reste ouverte, nommee et non corrigee (section 3.5), et deux dettes
signalees a s33 ne sont pas payees ce sprint (section 4) : l'absence de trace d'une partie jouee
sur le contenu reformule, pour la troisieme fois, et la dette generale de lecture de tache par
operation de chaine.

Cet avis porte uniquement le Done technique. Le Done pedagogique reste entier a `nadia-prof` : ses
trois instances n'ont pas encore audite ce sprint au moment de cette review, et rien ici ne le
prejuge.

---

## 7. Fichiers touches par cette review

Toutes les mutations ont ete faites dans des worktrees isoles, jamais sur le worktree principal,
et chaque worktree a ete detruit (`git worktree remove`) une fois sa mesure terminee :

- `../recreo-verif-adr0090` (sur `2c3463b3`) et `../recreo-verif-adr0090b` (sur `5bb6f9db`) :
  section 3.1, aucune mutation, lecture seule.
- `../recreo-mut-exc` (sur `HEAD`) : section 3.3, deux mutations successives de
  `adapters/adapter-content/src/main/resources/contenu/cp-emc-exclusion.yaml`, chacune restauree
  par `git checkout --` avant la suivante, `git status --short` vide verifie avant destruction du
  worktree.

Le worktree principal du depot n'a recu aucune modification du fait de cette review ; `git status
--short` y est reste vide du debut a la fin.
