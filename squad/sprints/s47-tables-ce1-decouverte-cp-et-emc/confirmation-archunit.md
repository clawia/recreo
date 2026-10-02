# Confirmation ArchUnit - s47 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-02.
- **Objet** : etape 4 du process (`squad/README.md`, section 9). Confirmer par ma propre mesure, sur
  l'arbre de `round-two` une fois les cinq lots de `DEC-0104` fusionnes (`MAT-CE1-07`, `QLM-CP-15`,
  `LVE-CP-06`, `EMC-CP-14`, `EMC-CM1-10`), que le compte de regles n'a pas regresse, qu'aucune regle
  neuve n'etait due, que la Clean Architecture tient, que les garde-fous censes couvrir le materiau
  neuf l'ont vu (amendement s17), et que le correctif d'integration `5015d96c` est tenu.
- **Perimetre mesure** : `round-two` a **`06449adc`** (fusion de `s47-fix-oeufs`), worktree dedie sur
  la branche `s47-theo-archunit` creee depuis ce commit, `bash outils/verifier-lignee.sh round-two`
  vert avant toute ecriture. **Bases de comparaison** : `57b8d68c` (base de mon avis de faisabilite)
  et `d441027c` (fusion de `s47-nadia-q4-q6`, dernier commit avant le premier lot code).
  `git diff --name-only 57b8d68c d441027c -- . ':!squad' ':!docs'` est vide : les deux bases sont
  identiques hors `squad/` et `docs/`.
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte d'elle-meme
  (`ADR-0071`) : 20 lignes *"using Java 21.0.10"* dans le journal du reacteur, aucune autre version.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Le compte de regles a-t-il regresse depuis l'avis de faisabilite ? | **non** : **36 `static final ArchRule`, 35 `@ArchTest`**, arbres `contreexemple` exclus (38 et 35 avec eux). Sortie par fichier identique a `57b8d68c` (section 1) |
| 2. Les cinq lots devaient-ils ajouter une regle ? | **non**, verifie et non suppose : aucune classe de production, aucune cle de schema neuve, aucune mecanique neuve (section 2) |
| 3. `mvn -B clean verify` est-il vert ? | **oui** : `BUILD SUCCESS`, 6 modules sur 6, **1938 tests, 0 echec, 0 erreur, 0 ignore**, dont 209 dans `com.recreo.archi` (section 3) |
| 4. Une ligne de `DEC-0104` viole-t-elle la Clean Architecture ? | **non** : aucun fichier de `domain` ni d'`application`, aucun `src/main/java`, aucun import `org.springframework` ajoute (section 4) |
| 5. Les garde-fous du catalogue, de l'inventaire du port entrant et des sous-parties de CE1 ont-ils vu le materiau neuf ? | **oui**, pour les six examines : leur population a bouge de la taille exacte du materiau neuf (section 5) |
| 6. Le correctif `5015d96c` est-il tenu, et une autre entree presente-t-elle le meme risque ? | **tenu, et aucune autre proposition du catalogue ne porte de caractere hors Latin-1** (0 sur 5735). Mais le refus venait du **harnais** de `PropositionsAccentueesJouablesIT`, pas du produit : le contenu destine a l'enfant a ete modifie pour une limite de test (section 6) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique et n'annonce pas
la porte technique du sprint. `mvn verify` n'est que le job `build` ; les trois autres jobs sont
lances a part en section 7, sur mon diff.

## 1. Le compte de regles, avec sa methode

Methode, celle de `squad/rapports/avis-faisabilite-s47-cinq-lots.md`, section 8 : `git grep -c -F`
sur les fichiers **suivis** de `domain`, `application`, `adapters` et `bootstrap`, motifs
`static final ArchRule` et `@ArchTest`, somme des comptes par fichier, lancee deux fois par arbre :
**avec** le pathspec d'exclusion `':!**/contreexemple/**'`, puis **sans**. `target/` et worktrees
sont exclus par construction (fichiers suivis uniquement). Commande lancee par `rtk proxy git grep`,
pour que la sortie ne passe par aucun filtre.

| Compte | `57b8d68c` exclus | `57b8d68c` inclus | `d441027c` exclus | `06449adc` exclus | `06449adc` inclus |
|---|---|---|---|---|---|
| `static final ArchRule` | 36 | 38 | 36 | **36** | 38 |
| `@ArchTest` | 35 | 35 | 35 | **35** | 35 |

Fichiers portant au moins une occurrence a `06449adc`, `contreexemple` exclus : 13 pour `ArchRule`,
12 pour `@ArchTest`. La sortie par fichier de `git grep -c` est **identique ligne pour ligne** entre
`57b8d68c` et `06449adc` pour les deux motifs (`diff` vide) : s47 n'ajoute, ne deplace ni ne retire
aucune regle. L'ecart 36 / 38 reste celui du faux module de `LesGardeFousSontMisALEpreuveTest`,
jamais compile (confirmation de s45, section 2).

## 2. Aucune regle neuve n'etait due : ce qui a reellement change

`git diff --stat d441027c 06449adc -- . ':!squad' ':!docs'` rend **17 fichiers, 1979 insertions,
8 suppressions** : `catalogue.yaml`, les cinq paquets YAML neufs, leurs cinq tests, un jeu fautif sous
`contreexemple/PaquetCe1TablesMultiplicationTest`, et cinq tables de test transverses
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`,
`TexteAccentueExposeParLePortEntrantTest`).

`git diff --name-only 57b8d68c 06449adc -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
est **vide** : zero ligne de Java de production, zero `pom.xml`, zero gabarit, zero script de CI ou
d'outil sur tout le sprint. `git diff --name-status 57b8d68c 06449adc -- squad/decisions/adr` est
vide : aucun `ADR` cree ni amende.

| Ce qui aurait appele une regle ou un `ADR` | Constat a `06449adc` | Methode |
|---|---|---|
| une dependance neuve entre classes | **aucune** : aucune classe compilee de production n'est ajoutee ni modifiee | diff ci-dessus |
| un champ neuf | **aucun** : l'ensemble des cles YAML des cinq paquets, imbrication comprise, est inclus dans celui des 120 fichiers de `contenu/` a `d441027c` | script Python de poste (PyYAML), non commite : difference d'ensembles vide pour chacun des cinq |
| une mecanique neuve | **aucune** : les cinq entrees ajoutees declarent `mecanique: bandit-manchot` | lignes `+` du diff de `catalogue.yaml` |
| un schema ou un type neuf | **aucun** : `schema: 1`, `type: question` pour les cinq, portes a `d441027c` par 115 et 110 paquets | meme script |
| une matiere neuve | **aucune** : `Langues vivantes (anglais)` (1 paquet a `d441027c`, `cm1-anglais`), `Enseignement moral et civique` (17), `Questionner le monde` (11), `Mathématiques` (34) | meme script |
| un domaine neuf | **un**, `Découverte culturelle` (`cp-anglais-culture`), absent a `d441027c`. Aucune classe de production ne regroupe par domaine ; la chaine est inventoriee par `TexteAccentueExposeParLePortEntrantTest` (section 5). Les quatre autres domaines sont deja portes par 3, 2, 3 et 23 paquets de leur matiere | meme script, `git grep -F` sur `*.java` |

C'est ce que mon avis prevoyait (section 1 : *"Aucune mecanique neuve, aucune `ArchRule` neuve, aucun
`ADR`"*) ; c'est ici mesure sur l'arbre fusionne.

## 3. `mvn verify`, la commande et sa sortie

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet depuis la racine du worktree : aucune mesure partielle, aucune question de `-am`.

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  0.621 s]
[INFO] recreo-domain ...................................... SUCCESS [ 14.699 s]
[INFO] recreo-application ................................. SUCCESS [  5.278 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:29 min]
[INFO] recreo-adapter-content ............................. SUCCESS [01:17 min]
[INFO] recreo-bootstrap ................................... SUCCESS [05:07 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  08:14 min
[INFO] Finished at: 2026-10-02T19:24:54+02:00
```

**Comptes, avec leur methode** : somme des six blocs `[INFO] Results:` de ce lancement apres
`clean` (methode autoritaire de `squad/README.md`, section 9). Perimetre : les six modules du
reacteur. Base : ma confirmation de s46 (1898, mesuree a `346274c8`).

| Module | Tests | Ecart avec s46 |
|---|---|---|
| `domain` | 120 | 0 |
| `application` | 17 | 0 |
| `adapter-web` | 400 | 0 |
| `adapter-content` | 1107 | **+40** |
| `bootstrap` (surefire) | 288 | 0 |
| `bootstrap` (failsafe) | 6 | 0 |
| **total** | **1938** | **+40** |

Chaque bloc rend 0 echec, 0 erreur, 0 ignore. **Les +40 sont expliques** : le diff Java
`d441027c..06449adc` ajoute 40 lignes `@Test`, n'en retire aucune et n'ajoute aucun
`@ParameterizedTest`, toutes dans les cinq tests de paquet neufs d'`adapter-content`. Methode :
`grep -cE '^\+\s*@Test\b'` sur ce diff.

**Ce qui a tourne dans `com.recreo.archi`** : les **30** classes suivies de
`bootstrap/src/test/java/com/recreo/archi` ont chacune leur rapport `TEST-*.xml` (30 fichiers), dont
la somme des attributs donne **209 tests, 0 echec, 0 erreur, 0 ignore**. Meme chiffre qu'en s46.

## 4. La Clean Architecture sur les cinq lignes de `DEC-0104`

- **`domain` et `application`** : aucun fichier modifie entre `57b8d68c` et `06449adc` (section 2).
  Les regles qui les gardent (`domain` sans framework, ports sans Spring, dependances vers
  l'interieur) sont parmi les 209 tests verts de `com.recreo.archi`.
- **Spring hors des adapters et de `bootstrap`** : aucune classe de production neuve. Les imports
  ajoutes par le diff Java du sprint (lignes `+import` de `rtk proxy git diff d441027c 06449adc -- '*.java'`)
  se rangent en `com.recreo.domain.content`, `java.*`, `org.junit.jupiter.api`,
  `org.assertj.core.api` et `org.yaml.snakeyaml`, deja dependance d'`adapter-content`. **Aucune
  ligne ajoutee par le diff du sprint ne contient `springframework`** (`grep -c` : 0).
- **Decouplage mecanique et contenu** : aucune classe de `domain/mechanic` ni de `domain/mechanics`
  touchee, aucun import de ces paquets ajoute. Les cinq paquets passent par le chargeur unique pilote
  par schema, sans chargeur neuf.

## 5. Les garde-fous censes couvrir le materiau neuf ont-ils bouge ?

Exigence de `squad/README.md`, section 9, amendement s17. Aucun des garde-fous ci-dessous n'est
parametre : le compte qui doit bouger est leur **population**, ce qu'ils lisent ou la table qu'ils
confrontent au materiau reel. Tous les comptes sont lus par `git show <rev>:<fichier>` aux deux
bornes `d441027c` et `06449adc`.

| Garde-fou | Ce qui a bouge | Pourquoi cela prouve qu'il a vu le materiau neuf |
|---|---|---|
| le catalogue lui-meme | entrees `- id:` de `catalogue.yaml` : **115 a `d441027c`, 120 a `06449adc`** (+5) | population de tous les garde-fous qui balaient le catalogue reel (`ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` parmi les 209 de `com.recreo.archi`), tous verts sur 120 |
| `TexteAccentueExposeParLePortEntrantTest` (inventaire du port entrant) | **+21 chaines** : 5 identifiants, 5 titres, 5 consignes, 1 domaine (`Découverte culturelle`), 5 competences | garde-fou **bidirectionnel** (`touteChaineExposeeParLeCatalogueReelEstInventoriee`, `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`) : vert, l'inventaire egale l'ensemble expose. Chacun des cinq commits de lot complete l'inventaire dans le meme commit que son paquet (`git show --name-only`), donc aucun etat rouge sur materiau reel n'existe dans l'historique de s47 pour ce garde-fou ; sa morsure n'est pas reprouvee ici, elle l'a ete a `8ab9ebf1` (confirmation de s46, section 5) |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : **89 entrees a `d441027c`, 92 a `06449adc`** (+3 : `cp-anglais-culture`, `cm1-emc-vote-election`, `cp-qlm-autrefois-aujourdhui`). Dettes inchangees : `DETTE_DE_RANG` = 2 entrees pour `PLAFOND_DE_DETTE = 2`, `DETTE_DE_SEUIL_MINIMUM` = 3 entrees pour `PLAFOND_DE_DETTE_SEUIL_MINIMUM = 3` | `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` confronte la table au materiau reel, vert. `ce1-tables-multiplication` (numerique) et `cp-emc-alerte` (3 items numeriques sur 11) n'y figurent pas, donc sont classes par le garde-fou. Les deux plafonds valent exactement leur dette : aucune exemption n'a ete ouverte pour ce sprint |
| `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` | `PAQUETS_A_ITEMS_ECARTES` : **4 entrees a `d441027c`, 5 a `06449adc`** (`cp-emc-alerte`, 8 items ecartes) | le compte d'items ecartes est confronte au paquet servi ; vert sur `cp-emc-alerte`, le seul lot qui mele items numeriques et textuels |
| `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | entrees `MAT-CE1-*` de `SousPartieDeMathematiquesCe1` : **11 a `d441027c`, 12 a `06449adc`** (`TABLES_DE_MULTIPLICATION`, `MAT-CE1-07`, libelle prevu par mon avis section 2) | vert : `ce1-tables-multiplication` declare une sous-partie allouee |
| `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` | lot `Questionner le monde / CP` : **11 fichiers et 134 items a `d441027c`, 12 et 148 a `06449adc`** (+1, +14 : `cp-qlm-autrefois-aujourdhui`, 14 items) | assertions `hasSize(12)` et `isEqualTo(148)` vertes, donc mesurees sur le paquet neuf |

**Ce que la section ne couvre pas** : je n'ai pas remesure, garde-fou par garde-fou, les autres
balayages du contenu (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` sur les trois lots de CP,
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`)
au-dela du fait qu'ils sont verts dans le reacteur sur un catalogue de 120 entrees.

## 6. Le correctif `5015d96c` (`s47-fix-oeufs`)

### 6.1 Il est tenu

- **Etat rouge reel avant le correctif** : worktree detache a `b799cce6` (les cinq lots fusionnes,
  avant `s47-fix-oeufs`), supprime apres la mesure,
  `bash outils/mvn-jdk21.sh -B -pl bootstrap -am verify -Dtest=AucunTestNeCorrespond -Dsurefire.failIfNoSpecifiedTests=false -Dit.test=PropositionsAccentueesJouablesIT -Dfailsafe.failIfNoSpecifiedTests=false`
  rend **1 test, 0 echec, 1 erreur**, `BUILD FAILURE`, Java 21.0.10 :
  `IllegalStateException: caractere hors Latin-1, cette IT ne sait pas le reproduire : "on cherche des œufs en chocolat dans le jardin"`,
  levee a `corpsFormulaireAvecUnCaractereAccentueEnLatin1:141`.
- **Etat vert apres** : le meme IT est vert dans le reacteur complet de la section 3, a `06449adc`
  (rapport failsafe, `java.version` 21.0.10).
- **Aucune autre proposition n'est exposee** : script Python de poste (PyYAML), non commite, qui lit
  par `git show` les 120 fichiers de `contenu/` et, pour chaque item, `bonneReponse` et chaque
  `distracteurs`. A `06449adc` : 119 paquets a items, **5735 propositions, dont 1207 non ASCII et
  0 avec un caractere au-dela de U+00FF**. A `b799cce6` : 1 (celle du correctif). A `d441027c` : 0.
  Les seuls caracteres hors Latin-1 du contenu servi sont des `œ` (U+0153), 10 dans des `enonce` et
  8 dans des `indice`, champs que l'IT ne soumet pas. L'affirmation du message de `5015d96c` sur les
  occurrences precedentes est donc exacte.

### 6.2 Ce que le correctif a reellement contourne

Le message de `5015d96c` ecrit que l'IT *"refuse tout caractere hors Latin-1 dans une proposition
soumise par formulaire"*. Lu dans le code, ce n'est pas le produit qui refuse : c'est le harnais de
l'IT qui leve **avant d'envoyer la requete**, parce qu'il encode le corps en `ISO_8859_1`, ou `œ`
n'existe pas (message : *"cette IT ne sait pas le reproduire"*). Le chemin produit
(`RequeteFormulaireDecodeeEnUtf8`, `adapter-web`) decode par `new String(octets, UTF_8)`, qui ne leve
pas sur une sequence invalide. **La proposition `"œufs"` n'a donc jamais ete jouee contre le serveur
par ce test** : rien ne montre qu'elle faisait planter une manche.

Consequence : un texte destine a l'enfant a ete modifie (`œufs` devient `oeufs`) pour une limite de
test, et non pour un defaut du produit. Deux points ne sont pas les miens et je les route via
`sofia-sm` :

| Code | Point | A qui |
|---|---|---|
| Q1 | `oeufs` sans ligature tient-il l'exigence de `DEC-0002` (francais correct, orthographe irreprochable) dans une proposition lue par un enfant de CP ? | `nadia-prof`, `relecteur-pedagogique` |
| Q2 | Si la ligature doit revenir, le harnais doit savoir l'envoyer. Un client Windows-1252, celui que l'IT simule, encode `œ` en un octet (0x9C), aussi invalide en UTF-8 que 0xE0 : le harnais peut le reproduire sans changer ce qu'il prouve. C'est un changement de test, pas d'architecture | `yanis-qa` ou le developpeur du lot |

Je ne tranche ni l'un ni l'autre, et je n'ouvre pas de regle : la regle utile existe deja (l'IT),
c'est son harnais qui est plus etroit que le monde qu'il doit simuler.

### 6.3 Constat de processus, sans jugement sur le contenu

`round-two` a porte l'IT rouge de la fusion de `s47-sofia-dev-lve-emc` (`3a56f9d1`, qui apporte
`bc0fc46c`) jusqu'a `06449adc` : trois commits de fusion. Je n'ai mesure l'etat rouge qu'a
`b799cce6` ; pour `3a56f9d1` et `7d3cc9ee`, je le deduis de ce que `bc0fc46c` porte deja la
proposition et qu'aucun commit intermediaire ne touche `cp-anglais-culture.yaml`. C'est le meme geste
manque qu'en s46 : la commande de la CI n'a pas ete lancee sur le diff avant la fusion, puisque cet IT
tourne dans le job `build`. Un second ecart, mineur : le sujet de `bc0c69d8` annonce *"+ tests domain
et web"*, alors que le commit ne touche ni `domain` ni `adapter-web` et que leurs comptes (120 et 400)
n'ont pas bouge. Je les releve pour `sofia-sm`, je ne les arbitre pas.

## 7. Les trois autres jobs de la CI, lances sur mon diff

Lances dans le worktree, ce fichier-ci indexe, avant le commit.

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert : *"Tous les cas passent"*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, ce fichier-ci inclus, code de sortie 0 pour les deux |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis le sujet du commit de ce fichier passe a `bash .github/scripts/verifier-prefixes.sh` | vert : les deux juges *"Tous les cas passent"*, puis *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | les trois motifs de juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | vert : **9** juges purs executes (5 sous `outils/*-test.sh`, 2 sous `squad/outils/*/*-test.sh`, 2 sous `outils/*-test.py`), code de sortie 0 pour chacun, aucun motif vide |

## 8. Ce que je n'ai pas fait

- Aucun Done pedagogique, sur aucun des cinq paquets.
- Aucune annonce de la porte technique du sprint.
- Aucune mutation fabriquee : la seule preuve de morsure citee (section 6.1) est l'etat reel de
  l'historique a `b799cce6`, mesure dans un worktree detache supprime apres la mesure.
- Aucun `ADR`, aucune dette ouverte, aucune regle ecrite, aucun test ni contenu modifie.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas.
