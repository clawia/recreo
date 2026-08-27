# Rapport qualite - s20 (mot cible designe et conception de la saisie), yanis-qa

Mesure sur le worktree principal, branche `round-two`, a l'etat commite le plus recent au moment
de la review : `77dd467` (`arbre propre`, `git status` rendait `clean` avant toute intervention).
Perimetre du sprint tel que route par `sofia-sm` : `S20-1` (contrat `ADR-0062`, retour de
`bandit-manchot-nature-mot-cp` au catalogue), `S20-2` (`cm1-reperes`, refuse puis corrige sur
`REP-4`), les deux editions de `catalogue.yaml` (`DEC-0048`/`DEC-0049`) et la correction de
`TexteAccentueExposeParLePortEntrantTest`. Machine virtuelle des tests : JDK **21.0.10**, celui de
la toolchain declaree - le `java` du `PATH` de ce poste n'est pas celui-la, la commande passe par
`outils/mvn-jdk21.sh`. Date de toutes les mesures de ce rapport : **2026-08-27**.

Ce rapport ne porte pas de Done pedagogique : ce n'est pas mon role. `S20-1` a deja recu son
verdict de `nadia-prof` (VALIDE, `DEC-0048`) ; `S20-2` reste sous verdict REFUSE d'origine avec
correctif livre mais non encore reaudite a `77dd467` (le reaudit, `b611f1b`, arrive apres le
commit que ce rapport mesure et sort donc de mon perimetre).

## Verdict sur la porte technique

**`bash outils/mvn-jdk21.sh -B clean verify` a la racine, sur `77dd467`, reacteur complet : BUILD
SUCCESS.** Six modules : `recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`,
`recreo-adapter-content`, `recreo-bootstrap`. Aucun `-am` a citer pour ce chiffre : c'est une
mesure du reacteur entier, jamais partielle. Aucun test instable rencontre sur l'ensemble des
executions de ce rapport (chaque commande listee ci-dessous a ete executee une seule fois pour son
chiffre final ; les mutations de la section 5 sont explicitement decrites comme telles et suivies
d'une restauration verifiee).

## 1. Mon compte de tests, avec sa methode

**Methode** : somme des blocs `[INFO] Results:` du journal Maven d'un `clean verify` a la racine,
sur `77dd467`, le 2026-08-27. Comptage apres `clean`, reacteur complet, pas de `-am`.

| Module | Tests |
|---|---|
| `recreo-domain` (surefire) | 49 |
| `recreo-application` (surefire) | 13 |
| `recreo-adapter-web` (surefire) | 330 |
| `recreo-adapter-content` (surefire) | 198 |
| `recreo-bootstrap` (surefire) | 98 |
| `recreo-bootstrap` (failsafe) | 6 |
| **Total** | **694** |

**694 confirme, par ma propre execution, le chiffre annonce dans le message du commit `77dd467`**
("694 tests, 0 echec") : je ne l'ai pas relaye, je l'ai reconstruit.

**Delta cite, pas un total recompose sur un autre arbre** : `squad/sprints/s20-mot-cible-designe-
et-conception-de-la-saisie/confirmation-archunit.md` (`theo-archi`, arbre `s20-theo` a `c48c0e6`,
avant le lot `S20-1`) porte **668 tests, 0 echec**, avec sa propre methode (meme forme de somme des
blocs `Results:`) et sa propre date (celle d'`ADR-0062`, 2026-08-25). Je ne re-execute pas cette
mesure a `c48c0e6` moi-meme : le faire demanderait de checkouter un autre commit que celui qui
m'est demande en arbre propre. Je cite donc sa source plutot que de la recopier sans attribution,
et je n'annonce qu'un **delta : +26** (668 -> 694), pas un total recompose sur un arbre que je n'ai
pas mesure. Repartition du delta, verifiee ligne a ligne contre les deux tableaux de comptage
(49-48=+1 domain, 13-13=0 application, 330-328=+2 adapter-web, 198-181=+17 adapter-content,
98-92=+6 bootstrap surefire, 6-6=0 failsafe ; +1+0+2+17+6+0 = +26) : les tests neufs du sprint
(`UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest`, `LaPartDesigneeDUnItemServiEstMarqueeAL
EcranTest`, `PaquetCm1ReperesTest`, l'extension de `PartieControllerTest` et de
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, et un test neuf de `BanditManchotTest`,
`laPartDesigneeDeLaMancheSuitLItemJoueEtRienQuandIlNEnDeclareAucune`).

## 2. Mon compte de regles ArchUnit, avec sa methode

**Methode**, identique a celle utilisee dans le rapport de s19 : balayage de tous les fichiers
`.java` du worktree (`target/` et `.git/` exclus) pour la declaration d'un champ `ArchRule`, en
excluant tout chemin contenant un segment `contreexemple` :

```
grep -rn "static final ArchRule\|static ArchRule" --include=*.java . | grep -v "/contreexemple/" | wc -l
```

**Resultat, sur `77dd467`, le 2026-08-27 : 30 champs `ArchRule` reels**, repartis sur 12 fichiers
(11 dans `bootstrap`, 1 dans `adapter-web`). **Ce sprint n'ajoute aucune `ArchRule`** : `ADR-0062`
le dit explicitement (ses deux clauses sont des garde-fous de balayage sur du document rendu et du
contenu charge, pas des regles ArchUnit) et ma mesure le confirme independamment - 30 avant comme
apres le lot `S20-1`, meme chiffre que celui deja confirme par `theo-archi` dans
`confirmation-archunit.md`.

## 3. Tracabilite des criteres d'acceptation

**`S20-1`** (`docs/backlog/backlog.md`, criteres corriges le 2026-08-25) - chaque critere
automatisable porte un test qui le nomme :

- "une composante facultative unique sur l'item, jamais deduite des guillemets" ->
  `ADR-0062`, clause 1 et clause 2, plus le contrat lui-meme (`Question.partDesignee`,
  `QuestionContentSourceFactory`).
- "un item qui ne declare rien produit un segment unique, non designe" ->
  `PartieControllerTest.demarrerNAfficheAucunElementDeMarquagePourUnItemQuiNeDeclareRien`
  (assertion structurelle par selecteur CSS Jsoup, `.enonce .mot-designe` vide - pas de regex sur
  le HTML).
- "le mot cible est entoure d'un element distinct, par au moins deux canaux non colores" ->
  la presence de l'element est verifiee par
  `PartieControllerTest.demarrerAfficheUnElementDistinctPourLItemQuiDeclareUnePartDesignee` et par
  `LaPartDesigneeDUnItemServiEstMarqueeALEcranTest` (`ADR-0062`, clause 1) ; **le choix des deux
  canaux non colores eux-memes (boite de fond, rembourrage, taille) n'est verifie par aucun
  automate** - `ADR-0062` le dit lui-meme ("elles ne disent rien de la forme visuelle"), et c'est
  la porte de `nadia-prof`/`ines-design`, pas la mienne. Pas un trou de couverture technique : un
  critere que le backlog range deja hors du testable par automate.
- "aucun paquet non declare ne rend le marquage" -> clause 1, seconde moitie
  (`LaPartDesigneeDUnItemServiEstMarqueeALEcranTest`), non-vacuite verifiee (`ADR-0013`).
- "aucun item ne bascule a deux lignes du seul fait du renfort", "`cm1-synonymes`/`cm1-antonymes`
  ne portent pas le marquage" -> mesure au pixel par `ines-design`
  (`validation-ines-mot-designe-2rem.md`, `mesure-budget-vertical.md`), pas un test JUnit :
  conforme a la famille `ADR-0054`-`ADR-0059`, dont la mesure vit hors de la suite automatisee.

**`S20-2`** (`REP-1` a `REP-4`, `REP-7`/`REP-8` correctifs - `REP-7`/`REP-8`, labels de `camille-po`
du 2026-08-25 (le pli, le cinquieme libelle), distincts des `REP-7`/`REP-8` que `nadia-prof`
introduit le 2026-08-27, voir `DEC-0050`. Renvoi ajoute par `sofia-sm` a la review, ce paragraphe
etant ecrit avant que la seconde paire de labels n'existe) - `PaquetCm1ReperesTest` nomme chaque
critere dans ses assertions (`.as("REP-1 : ...")`, etc.) :
`auMoinsTroisItemsExigentLaDistinctionEntrePointFinalEtPointDInterrogation` couvre le motif exact
du refus (`DEC-0049`) ; `REP-3` reste, comme le backlog le dit lui-meme, hors de portee d'un
automate (plausibilite des propositions). `REP-7` (le pli, meme renvoi que ci-dessus) n'est couvert
par aucun test JUnit -
c'est la meme famille que `S20-1` ci-dessus, mesuree par script, pas par la suite Maven - et son
etat a `77dd467` reste un residu route a `sofia-sm` (14 items sur 16 au-dessus du pli, pas 16 sur
16 ; message de `bd87e91`), non ferme par ce commit.

## 4. Les trois garde-fous de depot, sur du materiau reel

```
bash .github/scripts/verifier-chemins-test.sh      -> tous les cas passent (14 cas nommes)
bash .github/scripts/verifier-prefixes-test.sh     -> tous les cas passent (14 cas nommes)
bash .github/scripts/commit-atteignable-test.sh    -> tous les cas passent (5 cas nommes)
```

Mis a l'epreuve sur le materiau reel du sprint, pas seulement sur leur propre suite :

- `verifier-prefixes.sh` sur les sujets reels des 33 commits non-merge de s20 (branche `round-two`,
  plage `d1cc948..77dd467`, `d1cc948` etant le dernier commit de s19 ; `git log --no-merges` sur
  cette plage compte 50 commits en tout dont 17 commits de fusion, exclus car leur sujet est
  genere par Git et non redige par un agent) :
  `git log --no-merges --format=%s d1cc948..77dd467 | bash .github/scripts/verifier-prefixes.sh`
  -> "Tous les commits portent un prefixe d'agent connu."
- `verifier-chemins.sh` sur la liste des fichiers reellement modifies par ce meme intervalle :
  `git diff --name-only d1cc948..77dd467 | ... | bash .github/scripts/verifier-chemins.sh` ->
  "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."

## 5. Mises a l'epreuve sur du materiau reel de ce sprint

Regle d'engagement : casser le correctif que le garde-fou protege, constater le rouge, remettre le
correctif, constater le vert - jamais l'inverse, jamais sur une fixture ecrite pour l'occasion.

### 5.1 `ADR-0062`, clause 2 (`UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest`)

**Mutation** : `cp-nature-mot.yaml` (production reelle), l'item `q1` ("Le « chat » dort sur le
tapis.") edite pour que sa `partDesignee` ("chat") apparaisse **deux fois** dans l'enonce ("... dort
sur le tapis a cote d'un autre chat.") - la mutation qu'`ADR-0062` nomme explicitement comme le mode
de defaillance silencieux de la derivation.

**Constat** (`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test
-Dsurefire.failIfNoSpecifiedTests=false -Dtest=UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest`,
perimetre : `adapter-content` et ses dependances) : **rouge**, 1 echec sur 5 -
`chaquePartDesigneeDeProductionApparaitUneSeuleFoisDansSonEnonce`. Fichier restaure
(`git checkout --`), verifie identique a l'original avant remesure (`diff` silencieux) : **vert**,
5 tests, 0 echec. La clause mord bien sur le contenu de production reel de `cp-nature-mot`, pas
seulement sur les cinq fixtures de contre-exemple qu'`ADR-0062` cite pour sa propre mise a
l'epreuve.

### 5.2 Le motif exact du refus `DEC-0049` sur `cm1-reperes` (`REP-4`)

**Mutation** : `cm1-reperes.yaml` (production reelle), quatre items edites pour reproduire
precisement la forme refusee - un seul des six items qui opposaient "Point final" et "Point
d'interrogation" garde les deux libelles en propositions, les cinq autres perdent la paire (un
distracteur remplace par "Majuscule initiale"). Objectif : faire retomber a moins de trois le
nombre d'items qui exigent la distinction, la mesure exacte que `nadia-prof` cite dans son refus.

**Constat** (`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test
-Dsurefire.failIfNoSpecifiedTests=false -Dtest=PaquetCm1ReperesTest`) : **rouge**, 1 echec sur 10 -
`auMoinsTroisItemsExigentLaDistinctionEntrePointFinalEtPointDInterrogation`, message citant
explicitement "le refus du 2026-08-26 a mesuree absente". Fichier restaure, verifie identique a
l'original : **vert**, 10 tests, 0 echec. Ce test mord bien sur le contenu reel du paquet livre
avec sa correction (`bd87e91`), et il nomme dans son propre message le motif du refus qu'il garde -
c'est la forme de tracabilite que ce rapport demande d'un critere issu d'un refus.

## 6. Instruction du faux vert ArchUnit de `theo-archi`

Fait deja verse au depot (`confirmation-archunit.md`, `theo-archi`, s20) et reproduit ici par moi,
independamment, le 2026-08-27, avant de le prendre pour acquis.

**La commande fautive** : `bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test
-Dtest='com.recreo.archi.*' -Dsurefire.failIfNoSpecifiedTests=false` (motif sans le suffixe
`Test`). **Reproduite a l'identique** : `BUILD SUCCESS`, et **aucune ligne `[INFO] Tests run:` ni
`[INFO] Results:` nulle part dans le journal**. Le motif `com.recreo.archi.*` ne designe aucune
classe reelle (les classes s'appellent `...Test`) ; `-Dsurefire.failIfNoSpecifiedTests=false` -
l'option meme qu'une mesure ciblee legitime doit porter pour ne pas echouer sur un module qui n'a
simplement aucun test du paquet vise - convertit alors "zero test selectionne" en succes, sans le
signaler autrement que par l'absence du bloc `Results:`.

**La commande corrigee** : meme ligne avec `-Dtest='com.recreo.archi.*Test'`. Reproduite : **71
tests, 0 echec**, meme chiffre que celui deja publie par `theo-archi`.

**Ce que ce fait engage, en conduite de mesure et pas en code** : `-Dsurefire.failIfNoSpecifiedTests=
false` est necessaire pour toute mesure ciblee par paquet ou par suffixe (je m'en sers moi-meme en
section 5), et c'est exactement l'option qui rend "zero test" indiscernable d'un succes si le motif
est mal ecrit. **Aucun plugin Maven n'arrete ce cas** - ce n'est pas un defaut d'ArchUnit, ni de
`ADR-0051` (qui interdit la vacuite *a l'interieur* d'une regle, pas au niveau de la commande qui
la lance). Ce que ce rapport instruit, pour toute mesure ciblee future citee dans un fichier
commite : **lire le nombre de tests dans le bloc `Results:` avant de citer le vert, jamais l'inverse
- un `BUILD SUCCESS` sans bloc `Results:` visible n'atteste rien.** Une commande de mesure ciblee
qui ne montre pas ce bloc, ou dont le compte de tests n'est pas cite a cote du verdict, ne doit
pas etre prise pour une preuve. Aucune correction de code n'est due : c'est une regle de lecture du
journal, au meme titre que la clause de `CLAUDE.md` sur les mesures partielles - un vert sans son
compte de tests decrit une commande qui n'a rien juge, exactement comme un chiffre sans perimetre
decrit autre chose que l'arbre qu'on croit mesurer.

## 7. Ce qui reste ouvert, hors de mon perimetre de correction

- **`REP-7` (le pli sur `cm1-reperes`, label de `camille-po` du 2026-08-25 - voir le renvoi de la
  section 3, distinct des `REP-7`/`REP-8` que `nadia-prof` introduit le 2026-08-27, `DEC-0050`)** :
  residu non ferme a `77dd467`, route par `bd87e91` a
  `sofia-sm` comme une decision de forme (police, rembourrage de `.proposition` hors du repli a
  quatre colonnes) qui appartient a `ines-design`. Aucun test JUnit ne le garde : la mesure du pli
  est un script, pas la suite Maven, meme famille que `S19-6a`/`ADR-0059`. Je ne le rouvre pas moi-
  meme, je constate qu'il n'est pas clos par les commits mesures ici.
- **Le controle catalogue-contre-audit de `DEC-0049`, section 3** ("toute fusion touchant
  `catalogue.yaml` verifie que chaque entree exposee a un dernier verdict qui n'est ni vide ni
  REFUSE") est decrit dans le message de `86a1abd` comme applique **a la main** par `sofia-sm` sur
  ce commit precis, pas comme un script ou un test que ce depot fait courir automatiquement. Ce
  n'est pas un garde-fou executable a l'heure de ce rapport - je ne le compte pas dans la section 4
  et je ne le mets pas a l'epreuve, faute d'objet a muter.
- **La dette d'`ADR-0061`** (forme lisible par machine de l'etat d'audit d'une fiche de conformite,
  pour rendre ce controle mecanique plutot que manuel) reste ouverte, sa priorite relevee par
  `DEC-0049` section 3 ; ce n'est pas a moi de la chiffrer.

## Decisions prises

Aucune. Ce rapport mesure et instruit ; il ne tranche aucune question de produit, d'architecture ou
de pedagogie. Le seul enonce que je verse a la gouvernance est celui de la section 6 (une regle de
lecture du journal Maven pour toute mesure ciblee future), qui n'est pas une decision mais une
consigne de conduite de mesure - elle ne cree, n'amende et ne clot aucun `ADR` ni `DEC`.

## Compte rendu court

**Porte technique : verte.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet,
`77dd467`, `2026-08-27` : BUILD SUCCESS, **694 tests, 0 echec** (methode : somme des blocs
`Results:`, verifiee module par module). Delta de +26 sur la base `668` confirmee par `theo-archi`
le 2026-08-25 (`c48c0e6`, source citee, non recomposee par moi). **30 regles ArchUnit reelles**,
inchange par le sprint, confirme independamment.

**Deux mutations sur materiau reel de ce sprint** (clause 2 d'`ADR-0062` sur `cp-nature-mot.yaml`,
motif du refus `DEC-0049` sur `cm1-reperes.yaml`) : les deux mordent sur la production, rouge
constate, fichier restaure et verifie identique, vert reconstate.

**Un fait de conduite de mesure instruit** (section 6) : la commande ArchUnit ciblee sans suffixe
`Test` de `theo-archi` rendait `BUILD SUCCESS` sur zero test execute, reproduit ici independamment
- pas un defaut de code, une regle de lecture du journal avant de citer un vert.

**Ce qui reste ouvert, non ferme par les commits mesures ici** : le pli de `cm1-reperes` (`REP-7`,
label de `camille-po` du 2026-08-25, meme renvoi que la section 3 - distinct des `REP-7`/`REP-8` de
`nadia-prof` du 2026-08-27, `DEC-0050` - 14/16 au-dessus du pli, pas 16/16), route a
`sofia-sm`/`ines-design` ; le controle catalogue-contre-audit de `DEC-0049` reste manuel, pas
automatise.
