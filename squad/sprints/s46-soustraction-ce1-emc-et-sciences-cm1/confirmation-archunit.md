# Confirmation ArchUnit - s46 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-02.
- **Objet** : etape 4 du process (`squad/README.md`, section 9). Confirmer par ma propre mesure, sur
  l'arbre de `round-two` une fois les cinq lots de `DEC-0102` fusionnes (`MAT-CE1-05`, `EMC-CM1-01`,
  `EMC-CM1-09`, `SCI-CM1-03`, `SCI-CM1-16`), que le compte de regles n'a pas regresse, qu'aucune
  regle neuve n'etait due, que la Clean Architecture tient, et que les garde-fous censes couvrir le
  materiau neuf l'ont vu (amendement s17).
- **Perimetre mesure** : `round-two` a **`346274c8`**, worktree dedie sur la branche
  `s46-theo-archunit` creee depuis ce commit, `bash outils/verifier-lignee.sh round-two` vert avant
  toute ecriture. **Bases de comparaison** : `8b0fe8b4` (fusion de la cloture de s45, base du
  planning et de mon avis de faisabilite) et `836f3a63` (fusion du planning, dernier commit avant le
  premier lot code). `git diff --name-only 8b0fe8b4 836f3a63 -- . ':!squad' ':!docs'` est vide : les
  deux bases sont identiques hors `squad/` et `docs/`.
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte d'elle-meme
  (`ADR-0071`) : 20 lignes *"using Java 21.0.10"* dans le journal du reacteur, aucune autre version.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Le compte de regles a-t-il regresse depuis l'avis de faisabilite ? | **non** : **36 `static final ArchRule`, 35 `@ArchTest`**, arbres `contreexemple` exclus (38 et 35 avec eux). Sortie par fichier identique a `8b0fe8b4` (section 1) |
| 2. Les cinq lots devaient-ils ajouter une regle ? | **non**, verifie et non suppose : aucune classe de production, aucune dependance neuve, aucune mecanique neuve (section 2) |
| 3. `mvn -B clean verify` est-il vert ? | **oui** : `BUILD SUCCESS`, 6 modules sur 6, **1898 tests, 0 echec, 0 erreur, 0 ignore**, dont 209 dans `com.recreo.archi` (section 3) |
| 4. Une ligne de `DEC-0102` viole-t-elle la Clean Architecture ? | **non** : aucun fichier de `domain` ni d'`application`, aucun `src/main/java`, aucun import `org.springframework` ajoute (section 4) |
| 5. Les garde-fous du catalogue et de l'inventaire du port entrant ont-ils vu le materiau neuf ? | **oui**, pour les trois examines : leur population a bouge de la taille exacte du materiau neuf, et l'inventaire du port entrant a ete **rouge sur le materiau reel** a `8ab9ebf1` (section 5) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique et n'annonce pas
la porte technique du sprint. `mvn verify` n'est que le job `build` ; les trois autres jobs sont
lances a part en section 6, sur mon diff.

## 1. Le compte de regles, avec sa methode

Methode, celle de `squad/rapports/avis-faisabilite-s46-cinq-lots.md`, section 8 : `git grep -c -F`
sur les fichiers **suivis** de `domain`, `application`, `adapters` et `bootstrap`, motifs
`static final ArchRule` et `@ArchTest`, somme des comptes par fichier, lancee deux fois par arbre :
**avec** le pathspec d'exclusion `':!**/contreexemple/**'`, puis **sans**. `target/` et worktrees
sont exclus par construction (fichiers suivis uniquement). Commande lancee par `rtk proxy git grep`,
pour que la sortie ne passe par aucun filtre.

| Compte | `8b0fe8b4` exclus | `8b0fe8b4` inclus | `836f3a63` exclus | `346274c8` exclus | `346274c8` inclus |
|---|---|---|---|---|---|
| `static final ArchRule` | 36 | 38 | 36 | **36** | 38 |
| `@ArchTest` | 35 | 35 | 35 | **35** | 35 |

Fichiers portant au moins une occurrence a `346274c8`, `contreexemple` exclus : 13 pour `ArchRule`,
12 pour `@ArchTest`. La sortie par fichier de `git grep -c` est **identique ligne pour ligne** entre
`8b0fe8b4` et `346274c8` pour les deux motifs (`diff` vide) : s46 n'ajoute, ne deplace ni ne retire
aucune regle. Les deux `ArchRule` de l'ecart 36 / 38 sont celles du faux module de
`LesGardeFousSontMisALEpreuveTest`, jamais compile (confirmation de s45, section 2).

## 2. Aucune regle neuve n'etait due : ce qui a reellement change

`git diff --stat 836f3a63 346274c8 -- . ':!squad' ':!docs'` rend **15 fichiers, 2226 insertions,
3 suppressions** : `catalogue.yaml`, les cinq paquets YAML neufs, leurs cinq tests, un jeu fautif sous
`contreexemple/PaquetCe1SoustractionsPoseesTest`, et trois tables de test transverses
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`TexteAccentueExposeParLePortEntrantTest`).

`git diff --name-only 8b0fe8b4 346274c8 -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
est **vide** : zero ligne de Java de production, zero `pom.xml`, zero gabarit, zero script de CI ou
d'outil sur tout le sprint. `git diff --name-status 8b0fe8b4 346274c8 -- squad/decisions/adr` est
vide : aucun `ADR` cree ni amende.

| Ce qui aurait appele une regle ou un `ADR` | Constat a `346274c8` | Methode |
|---|---|---|
| une dependance neuve entre classes | **aucune** : aucune classe compilee de production n'est ajoutee ni modifiee. ArchUnit lit du bytecode de production ; il n'a rien de neuf a lire | diff ci-dessus |
| un champ neuf | **aucun** : les cinq paquets portent `schema`, `id`, `type`, `descripteur` (`matiere`, `niveau`, `domaine`, `competence`), `lignesDeReferentiel`, `items` et, par item, `enonce`, `bonneReponse`, `distracteurs`, `indice`. Aucune n'est absente des paquets servis a `836f3a63` | extraction des cles YAML des cinq fichiers, commentaires retires, comparee par `comm -23` a l'ensemble des cles des paquets de `836f3a63` : difference vide |
| une mecanique neuve | **aucune** : les cinq entrees ajoutees a `catalogue.yaml` declarent `mecanique: bandit-manchot`, servie par 106 entrees a `836f3a63` | diff de `catalogue.yaml`, `grep -c` sur `836f3a63` |
| une matiere ou un domaine neuf | **aucun** : `Mathématiques` (33 paquets a `836f3a63`), `Enseignement moral et civique` (15), `Sciences et technologie` (14) ; les cinq premiers `domaine:` sont portes a `836f3a63` par 22, 5, 2, 4 et 1 paquets | `git grep` des valeurs de `matiere` et `domaine` des paquets a `836f3a63` |

Les cinq paquets declarent `schema: 1` et `type: question`, deja servis. C'est ce que mon avis
prevoyait (section 8) ; c'est ici mesure sur l'arbre fusionne.

## 3. `mvn verify`, la commande et sa sortie

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet depuis la racine du worktree : aucune mesure partielle, aucune question de `-am`.

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  1.287 s]
[INFO] recreo-domain ...................................... SUCCESS [ 19.827 s]
[INFO] recreo-application ................................. SUCCESS [  6.443 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:44 min]
[INFO] recreo-adapter-content ............................. SUCCESS [01:13 min]
[INFO] recreo-bootstrap ................................... SUCCESS [05:06 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  08:33 min
[INFO] Finished at: 2026-10-02T07:48:09+02:00
```

**Comptes, avec leur methode** : somme des six blocs `[INFO] Results:` de ce lancement apres
`clean` (methode autoritaire de `squad/README.md`, section 9). Perimetre : les six modules du
reacteur. Base : ma confirmation de s45 (1850, mesuree a `8bf88702`).

| Module | Tests | Ecart avec s45 |
|---|---|---|
| `domain` | 120 | 0 |
| `application` | 17 | 0 |
| `adapter-web` | 400 | 0 |
| `adapter-content` | 1067 | **+48** |
| `bootstrap` (surefire) | 288 | 0 |
| `bootstrap` (failsafe) | 6 | 0 |
| **total** | **1898** | **+48** |

Chaque bloc rend 0 echec, 0 erreur, 0 ignore. **Les +48 sont expliques** : les cinq tests de paquet
neufs portent 13, 7, 7, 10 et 11 annotations `@Test` (`PaquetCe1SoustractionsPoseesTest`,
`PaquetCm1EmcEmotionRessentirAgirTest`, `PaquetCm1EmcInstitutionsTest`,
`PaquetCm1SciencesMouvementTest`, `PaquetCm1SciencesPhenomenesTest`), soit 48. Le diff Java
`836f3a63..346274c8` ajoute 48 lignes `@Test` et n'en retire aucune ; le diff Java
`8bf88702..836f3a63` (cloture de s45) n'en ajoute ni n'en retire aucune. Methode : `grep -c` des
lignes `@Test` en debut de ligne, aucun test parametre dans ces fichiers.

**Ce qui a tourne dans `com.recreo.archi`** : les **30** classes suivies de
`bootstrap/src/test/java/com/recreo/archi` ont chacune leur rapport `TEST-*.xml` (30 fichiers), dont
la somme des attributs donne **209 tests, 0 echec, 0 erreur, 0 ignore**. Meme chiffre qu'en s45.

## 4. La Clean Architecture sur les cinq lignes de `DEC-0102`

- **`domain` et `application`** : aucun fichier modifie entre `8b0fe8b4` et `346274c8` (section 2).
  Les regles qui les gardent (`domain` sans framework, ports sans Spring, dependances vers
  l'interieur) sont parmi les 209 tests verts de `com.recreo.archi`.
- **Spring hors des adapters et de `bootstrap`** : aucune classe de production neuve. Les imports
  ajoutes par le diff Java du sprint (`rtk proxy git diff 836f3a63 346274c8 -- '*.java'`, lignes
  `+import`) se rangent en `com.recreo.domain.content` (`ContentSource`, `Item`, `ItemId`,
  `Question`, `RandomSource`), `java.*`, `org.junit.jupiter`, `org.assertj.core` et
  `org.yaml.snakeyaml`, deja dependance d'`adapter-content`. **Aucune ligne ajoutee par le diff du
  sprint ne contient `springframework`** (`grep -c` : 0).
- **Decouplage mecanique et contenu** : aucune classe de `domain/mechanic` ni de `domain/mechanics`
  touchee, aucun import de ces paquets ajoute. Les cinq paquets passent par le chargeur unique pilote
  par schema, sans chargeur neuf.

## 5. Les garde-fous censes couvrir le materiau neuf ont-ils bouge ?

Exigence de `squad/README.md`, section 9, amendement s17. Aucun des garde-fous ci-dessous n'est
parametre : leur nombre de tests ne bouge pas par construction. Le compte qui doit bouger est donc
leur **population**, c'est-a-dire ce qu'ils lisent ou la table qu'ils confrontent au materiau reel.

| Garde-fou | Ce qui a bouge | Pourquoi cela prouve qu'il a vu le materiau neuf |
|---|---|---|
| le catalogue lui-meme | entrees `- id:` de `catalogue.yaml` : **110 a `836f3a63`, 115 a `346274c8`** (+5) | c'est la population de tous les garde-fous qui balaient le catalogue reel (`ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` parmi les 209 de `com.recreo.archi`), tous verts sur 115 |
| `TexteAccentueExposeParLePortEntrantTest` (inventaire du port entrant) | **+19 chaines** : 5 identifiants, 5 titres, 4 consignes, 5 competences. La cinquieme consigne, *"Pose l'opération, puis choisis le bon résultat."*, est deja exposee par deux entrees a `836f3a63` et deja inventoriee | le garde-fou est **bidirectionnel** (`touteChaineExposeeParLeCatalogueReelEstInventoriee` et `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`) : vert, l'inventaire egale l'ensemble expose. **Preuve sur materiau reel** : a `8ab9ebf1` (les cinq lots fusionnes, avant `346274c8` qui complete l'inventaire pour `MAT-CE1-05`), `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=TexteAccentueExposeParLePortEntrantTest -Dsurefire.failIfNoSpecifiedTests=false` rend **5 tests, 3 echecs**, `BUILD FAILURE`, Java 21.0.10, message : *"bandit-manchot-soustractions-posees-ce1 (chemin "id") expose "bandit-manchot-soustractions-posees-ce1", une chaine que l'inventaire de ce garde-fou ne porte pas"* |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : **85 entrees a `836f3a63`, 89 a `346274c8`** (+4 : les deux paquets d'EMC et les deux de sciences). `DETTE_DE_RANG` inchangee : `cm1-perimetre`, `cm1-mesures`, `PLAFOND_DE_DETTE = 2`, egal a sa taille | la table est confrontee au materiau reel dans les deux sens (`aucunPaquetNEstEcarteEnSilenceDuCalculDeRang`, et ses mises a l'epreuve `unPaquetDeclareAtortHorsDuDomaine...`, `uneDeclarationQuiSurvitASonPaquet...`) et ses comptes ecrits sont remesures (`chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet`). Vert : les quatre paquets non numeriques ont ete vus et declares, et `ce1-soustractions-posees`, numerique, n'y figure pas, donc il est classe par le garde-fou |
| `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | entrees `MAT-CE1-*` de `SousPartieDeMathematiquesCe1` : **10 a `836f3a63`, 11 a `346274c8`** (`SOUSTRACTIONS_POSEES`) | mon avis (section 2) notait que sans cette entree le paquet est rouge ; elle est la, et le garde-fou est vert |

**Ce que la section ne couvre pas** : je n'ai pas remesure, garde-fou par garde-fou, les autres
balayages du contenu (repartition par forme, seuils de rang) au-dela du fait qu'ils sont verts dans le
reacteur. `ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest` ne doit **pas** voir
`ce1-soustractions-posees` : sa population derive de la competence declaree (`ADR-0097`, decision 2),
qui n'est pas celle de `MAT-CE1-05` (mon avis, section 3.1). Son compte ne bouge pas, et c'est
attendu ; les conditions de `SOP-CE1-5` sont gardees par `PaquetCe1SoustractionsPoseesTest`.

**Un constat de processus, sans jugement sur le contenu** : le lot `MAT-CE1-05` a ete fusionne avec
l'inventaire du port entrant rouge sur `round-two` (mesure ci-dessus a `8ab9ebf1`), et complete par
`346274c8`. Le commentaire de ce commit dans le test en donne la cause : le lot n'avait mesure que
`adapter-content`, pas le reacteur complet. C'est le geste que `CLAUDE.md` nomme (*"lancer la
commande de la CI sur son propre diff avant de commiter"*). Je le releve, je ne l'arbitre pas :
c'est a `sofia-sm` de le porter en retro si elle le juge utile.

## 6. Les trois autres jobs de la CI, lances sur mon diff

Lances dans le worktree, ce fichier-ci indexe, avant le commit.

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert : *"Tous les cas passent"*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, ce fichier-ci inclus, code de sortie 0 pour les deux |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis le sujet du commit de ce fichier passe a `bash .github/scripts/verifier-prefixes.sh` | vert : les deux juges *"Tous les cas passent"*, puis *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | les trois motifs de juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | vert : **9** juges purs executes (5 sous `outils/*-test.sh`, 2 sous `squad/outils/*/*-test.sh`, 2 sous `outils/*-test.py`), code de sortie 0 pour chacun, aucun motif vide |

## 7. Ce que je n'ai pas fait

- Aucun Done pedagogique, sur aucun des cinq paquets.
- Aucune annonce de la porte technique du sprint.
- Aucune mutation fabriquee : la seule preuve de morsure citee (section 5) est l'etat reel de
  l'historique a `8ab9ebf1`, mesure dans un worktree detache supprime apres la mesure.
- Aucun `ADR`, aucune dette ouverte, aucune regle ecrite.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas.
