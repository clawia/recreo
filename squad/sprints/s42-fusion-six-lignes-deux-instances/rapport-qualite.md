# Rapport qualite de cloture - s42

**Auteur** : `yanis-qa`. **Date** : 2026-09-24. **Arbre mesure** : `s42-yanis` a `cdf0229f`,
worktree dedie (`git worktree add ../recreo-s42-yanis -b s42-yanis round-two`), lignee verifiee
(`bash outils/verifier-lignee.sh` -> *"le worktree sur la branche 's42-yanis' descend de la lignee
de round-two (ancetre commun trouve dans son historique)"*), separe de tout autre worktree partage.

Contenu du sprint : cinq lots de contenu statique, deux terrains, deux instances paralleles de
`nadia-prof` (`DEC-0098` amendee) - `S42-HG-ROIS` et `S42-QLM-MATIERE` (`[MARC]`, terrain 1,
instance A), `S42-ART-COULEURS` et `S42-EMC-SYMBOLES-CM1` (`[SOF]`, terrain 2, instance B),
`S42-MAT-TEXTUEL` (`[LEA]`, terrain 2, instance B), fusionnes dans `round-two` par trois commits de
fusion successifs (`[LEA]` `00952c9a`, `[SOF]` `9a2aae8a`, `[MARC]` `cbe35d0b`/`cdf0229f`).

Aucun verrou `mvnd` transitoire rencontre : `bash outils/mvn-jdk21.sh -B clean verify`, lance une
seule fois sur ce worktree, a rendu `BUILD SUCCESS` du premier coup, en 5 min 22 s.

---

## 1. Compte de tests autoritaire

**1727 tests : 1721 unitaires + 6 tests d'integration**, sur `s42-yanis` a `cdf0229f`. Methode :
somme des six blocs `[INFO] Results:` (un par module) du meme lancement de
`bash outils/mvn-jdk21.sh -B clean verify`, recoupee par la somme independante des attributs
`tests="..."` des **235** rapports XML Surefire/Failsafe produits par ce meme lancement. Les deux
methodes convergent a 1727 - jamais la somme des lignes `Tests run:` des fichiers `.txt`
(`squad/README.md`, amendement s11).

JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la toolchain
declaree) : **21.0.10** (Apache Maven 3.9.9), coherent avec les lignes de demarrage Spring Boot du
meme lancement (`using Java 21.0.10`) et avec le diagnostic independant d'`outils/verifier-jdk.sh`
(`java du PATH` mesure a une version **17.0.4.1**, distincte du JDK de compilation resolu a
21.0.10 - les deux verifications convergent sur la meme valeur sans partager la meme source).
`BUILD SUCCESS`, 6 modules sur 6, 0 echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 907 |
| recreo-bootstrap (surefire, unitaires) | 277 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1721** |
| **Total integration** | **6** |

`adapter-content` passe de **881** a **907**, soit **+26 tests**, portes par les cinq lots neufs.
`domain`, `application`, `adapter-web` et `bootstrap` sont inchanges - coherent avec l'avis de
`theo-archi` (section 3) : *"zero ligne de Java de production"* attendue sur les cinq lots.

**Methode et perimetre de cette mesure de base** : `881` n'est pas recopie du rapport de s41 (qui le
mesurait a `b5b07966`), parce que quatre commits de correction de contenu (`60cb9e34`, `bddd8233`,
`8860e01c`, `0d48d2a3`) s'intercalent entre `b5b07966` et `517abd9d` (base de l'avis de
`theo-archi` pour s42) sans que leur effet sur le compte de tests soit acquis d'avance. Mesure
directe, faite sur un second worktree dedie a `517abd9d`
(`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am clean test`, **module mesure** :
`adapters/adapter-content` et ses deux dependances amont, pas le depot entier) : `881`, identique
au chiffre de s41. Worktree supprime apres mesure (`git worktree remove --force`), aucune trace
laissee sur `round-two`.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 11 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 125 |
| bootstrap | 50 |

`adapter-content` passe de 121 (s41) a 125, soit exactement **+4 fichiers** - un par paquet neuf
(`PaquetCpQlmMatiereTest`, `PaquetCpArtsCouleursTest`, `PaquetCm1EmcSymbolesTest`,
`PaquetCpDeplacementsQuadrillageTest`), confirmant qu'aucun paquet neuf n'est reste sans test a son
premier commit. `S42-HG-ROIS` ne cree pas de cinquieme fichier : il etend
`PaquetCm1HistoireGeographieTest`, deux methodes de test ajoutees
(`lesQuatreRoisDuLibelleSontServisEnBonneReponseSurHgCm105`,
`hgCm105NePorteAucunJugementDeValeurSurUnRoi`), coherent avec le fait qu'il etend un paquet servi
plutot que d'en ouvrir un neuf.

Ce chiffre est un compte sur mon propre worktree, pas un total sur l'arbre fusionne `round-two`
au-dela de ce qui est deja fusionne dans `cdf0229f` : l'integration ulterieure reste a `sofia-sm`.

**ArchUnit** : **36** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales de ces deux chaines sous `src/test/java` des cinq modules declares au pom agregateur,
`target/` exclu, methode d'`ADR-0095`/`ADR-0098`. Un balayage naif (sans exclure la fixture) rend
**38** et 35 : l'ecart de deux est porte par le meme fichier de contre-exemple qu'a s41,
`bootstrap/src/test/resources/contreexemple/.../faux-module/src/test/java/com/recreo/faux/
RegleContreExemple.java`, dont le chemin contient lui-meme `src/test/java` et qui est une fixture
de contre-exemple, pas une regle du depot. Compte inchange depuis s41, coherent avec l'avis
(*"aucune regle ArchUnit neuve n'est due"*). Aucune classe ArchUnit en echec dans le lancement
complet.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (19 cas) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **Vert** - tous les cas passent, aucun chemin de poste ni nom de compte trouve |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s -20 \| bash .github/scripts/verifier-prefixes.sh` | **Vert** - tous les cas passent, les 20 derniers commits portent tous un prefixe d'agent connu |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree, et
`git status --short` est reste vide apres chacun.

## 3. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 42", cite des codes de condition
(`FER-1`, `FER-2`, `FER-3`, `ART-1`, `HIS-1`, `HIS-2`, `HIS-3`, `BIA-5`). Verification faite
methode par methode : le fichier de test du paquet porte-t-il une methode nommee sur le code, et
cette methode l'assertionne-t-elle reellement.

| Lot | Criteres encodes en test, et methode | Ce qui reste garde de `nadia-prof` (non encodable, motif ecrit dans le fichier de test ou l'avis) |
|---|---|---|
| `S42-HG-ROIS` | `lesQuatreRoisDuLibelleSontServisEnBonneReponseSurHgCm105` (FER-1, recompose sur les deux paquets), `hgCm105NePorteAucunJugementDeValeurSurUnRoi` (BIA-5) ; `HIS-1` encodee sur le lot entier (`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`, 115 items, 23 restitutions, plafond 38) | `HIS-2` (aucune reponse discutable), `HIS-3` (exactitude et source de chaque fait) - lecture de sens, non encodable (avis de `theo-archi`, section 7.1) |
| `S42-QLM-MATIERE` | `fer1LesQuatreMateriauxDuCritereSontEnBonneReponse`, `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems`, `aucunEnonceDeLaTacheDIdentificationNeRevelePasLaMatiere`, `chaqueItemDeclareUniquementQlmCp06` | que chaque distracteur soit un materiau plausible pour l'objet decrit (lecture de sens) ; les cas ou la matiere se deduit du nom sans y figurer ("une vitre", "un pull") - `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (ADR-0083) ne les prend pas (avis, section 7.2) |
| `S42-ART-COULEURS` | `fer1LesTroisCouleursPrimairesSontEnBonneReponse`, `fer1LesTroisMelangesReelsSontEnBonneReponse`, `art1AucunItemNeMontreUneCouleurPercue` (absence d'illustration + patron "de quelle couleur est" interdit), `chaqueItemDeclareUniquementArtCp05` | exactitude de chaque fait de melange (lecture de sens) |
| `S42-EMC-SYMBOLES-CM1` | `fer1LesCinqSymbolesSontEnBonneReponse`, `art1AucunMotDeCouleurDansLePaquet`, `aucunEnonceNeReprendLePatronDejaServiAuCp` (discriminant technique vis-a-vis de `cp-emc-symboles`/`cp-emc-devise`), `chaqueItemDeclareUniquementEmcCm105` | `HIS-2`, `HIS-3` ; et surtout la substance du discriminant avec le CP au-dela du seul patron d'enonce - lecture de sens explicitement hors de portee du test (avis, section 8.1) |
| `S42-MAT-TEXTUEL` | `touteLesPropositionsSontLUneDesQuatreFormesDeDeplacementEtLaBonneReponseNYFigureQuUneFois`, `aucunItemNePorteDIllustration`, `chaqueItemDeclareUniquementMatCp10` ; `LON-1`/`LON-2` mordent automatiquement par derivation de niveau (`NIVEAU_BORNE = "CP"`) | aucune condition pedagogique bloquante nommee par l'avis (section 7.5) ; la clarte de la scene posee en mots reste jouee par `nadia-prof` |

Aucun critere d'acceptation des cinq stories n'est reste sans test et sans motif explicite de
non-encodage.

**Sur le garde-fou de non-duplication cite dans la commande de cette tache** : aucun garde-fou de
non-duplication **texte/illustration** n'existe pour `S42-EMC-SYMBOLES-CM1`, et il n'y a rien a en
attendre - ni le paquet CM1 (`cm1-emc-symboles`) ni les deux paquets CP auxquels il est adjacent
(`cp-emc-symboles`, `cp-emc-devise`) ne declarent d'illustration : les trois sont du texte seul.
Le garde-fou reellement pose sur cette adjacence est **textuel**, `aucunEnonceNeReprendLePatronDejaServiAuCp`,
mis a l'epreuve section 4.4.

## 4. Mutation de garde-fous sur materiau reel

Regle appliquee : le garde-fou est mis a l'epreuve en desactivant ou en niant ce qu'il protege, sur
le fichier ou la classe reellement servi(e), jamais une fixture. Restauration par `git checkout --`
apres chaque mutation, `git status --short` verifie vide apres chaque restauration, et le test
mute re-verifie vert apres restauration.

### 4.1 `HIS-1` sur le lot histoire-geographie CM1 - plafond calcule sur le lot, pas sur un fichier

Le lot reel (quatre paquets, 115 items, 23 marqueurs de restitution) tient son tiers avec une marge
de 15 items (plafond `115 / 3 = 38`) : l'assertion est verte par construction sur le contenu
livre, ce qui ne prouve pas qu'elle mordrait si le lot depassait son tiers.

Mutation : dans `cm1-histoire-geographie-nommer-en-histoire.yaml`, 16 marqueurs
`# HIS-1: association` reecrits en `# HIS-1: restitution` (23 + 16 = 39 > 38).

- **Fichier mute** : rouge, `Tests run: 9, Failures: 3`. Message exact : *"Histoire-geographie /
  CM1 : 39 items de restitution sur 115 (4 fichier(s)), au-dessus du tiers du LOT, soit 38"*.
- **Fichier restaure** : vert, `Tests run: 9, Failures: 0`.

Confirme que `HIS-1`, calculee sur le lot entier de quatre paquets et non sur le seul fichier
etendu par `S42-HG-ROIS`, mord reellement sur le materiau servi par ce sprint.

### 4.2 `ART-1` (patron d'enonce) sur `PaquetCpArtsCouleursTest` - critere d'acceptation de `S42-ART-COULEURS`

Ce paquet est, par construction, celui qui nomme le plus de couleurs du depot (c'est son sujet) :
`ART-1` n'y interdit pas le mot de couleur, mais le patron d'enonce "de quelle couleur est" et
toute illustration. Une assertion verte par construction sur le contenu livre.

Mutation : dans `cp-arts-couleurs.yaml`, l'enonce du premier item passe de "Quelle couleur porte le
ciel [...] ?" a "De quelle couleur est le ciel [...] ?".

- **Fichier mute** : rouge, `Tests run: 7, Failures: 1` - *"item item-0 : ART-1, l'enonce ne
  demande jamais 'de quelle couleur est...'"*.
- **Fichier restaure** : vert, `Tests run: 7, Failures: 0`.

### 4.3 Absence d'illustration sur `S42-MAT-TEXTUEL` (`PaquetCpDeplacementsQuadrillageTest`)

Critere d'acceptation et section 7.5 de l'avis de faisabilite : ce paquet est en choix textuel
seul, palier 2 hors de portee. Assertion verte par construction (aucun item ne declare
d'illustration aujourd'hui).

Mutation : dans `cp-deplacements-quadrillage.yaml`, ajout de `illustration: solide-cube-a` et de
`illustrationTexteAlternatif` (les deux cles se declarent ensemble, `ADR-0073` clause 5) sur le
premier item.

- **Fichier mute** : rouge, `Tests run: 6, Failures: 1` - *"item item-0 : ce paquet est en choix
  textuel seul, aucune designation sur une image (palier 2, hors de portee ici) - Expecting an
  empty Optional but was containing value: 'solide-cube-a'"*.
- **Fichier restaure** : vert, `Tests run: 6, Failures: 0`.

Confirme que l'absence d'illustration du lot, critere d'acceptation de `S42-MAT-TEXTUEL`, est
verifiee par un test qui mord et pas seulement par un enonce de critere.

### 4.4 Discriminant `S42-EMC-SYMBOLES-CM1` vis-a-vis de `cp-emc-symboles` (avis de faisabilite, section 8.1)

C'est le garde-fou de non-duplication reellement pose sur cette adjacence (section 3) :
`aucunEnonceNeReprendLePatronDejaServiAuCp` refuse que le paquet CM1 reprenne le patron de question
deja epuise au CP ("Comment s'appelle...", "Que represente..."). Assertion verte par construction.

Mutation : dans `cm1-emc-symboles.yaml`, l'enonce du premier item remplace par "Que represente le
drapeau, historiquement ?" - le patron exact du paquet CP.

- **Fichier mute** : rouge, `Tests run: 6, Failures: 1` - *"item item-0 : ce patron de question est
  deja epuise au CP par cp-emc-symboles, ce paquet de CM1 doit poser une autre tache -
  Expecting actual: 'que represente le drapeau, historiquement ?' not to contain: 'que
  represente'"*.
- **Fichier restaure** : vert, `Tests run: 6, Failures: 0`.

Confirme que le discriminant technique pose par `theo-archi` (avis, section 8.1) mord reellement.
Il reste ce que l'avis dit : un discriminant de **forme**, qui ne juge pas si le paquet CM1 depasse
substantiellement le CP en exigence - cela reste la garde de `nadia-prof`, instance B.

## 5. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois par
etat (mute, puis restaure) ; un test lance une seule fois par etat n'est pas qualifie de stable
pour autant, et ce rapport ne l'affirme pas. Aucun verrou `mvnd` transitoire rencontre sur ce
worktree.

## 6. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof`, sur ses deux instances paralleles, seule le
fait. Il ne juge le niveau scolaire d'aucun des cinq lots. Il n'etablit aucun total sur l'arbre
fusionne `round-two` au-dela de `cdf0229f` : c'est `sofia-sm` qui l'etablit a l'integration. Il ne
modifie aucun contenu ni aucun code de production : les quatre mutations de la section 4 ont ete
restaurees par `git checkout --` avant la redaction de ce rapport, chaque restauration verifiee par
`git status --short` vide et par un second lancement du test mute, vert.
