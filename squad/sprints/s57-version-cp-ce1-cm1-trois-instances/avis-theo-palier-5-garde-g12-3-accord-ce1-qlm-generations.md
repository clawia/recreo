# s57 - palier 5 : avis de theo-archi sur la garde `G12-3` apres le releve de l'accord de `ce1-qlm-generations` (QLM-CE1-12)

- **Auteur** : `theo-archi`. Date : 2026-10-29 09:00 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-12`, base `round-two` a `78cd43c4` (`git rev-parse`).
- **Demande** : point `T1` de `releve-relecteur-palier-5-ce1-qlm-generations.md` (section 6) : forme de `G12-3` apres correction des items 3 et 8. Story `S57-QLM-CE1-12-ACCORD`, critere 3 (`docs/backlog/backlog.md`) : *"l'enonce corrige et la formule de `R12-2` que `G12-3` lit a la lettre changent ensemble, sans assouplir la garde (forme a `theo-archi`)"*. Decision de l'utilisatrice du 2026-10-29 : le passage de `QLM-CE1-12` a `couvert-valide` est traite entierement.
- **Lu** : le releve en entier ; `PaquetCe1QlmGenerationsTest.java` en entier ; le jeu fautif `contreexemple/PaquetCe1QlmGenerationsTest/contenu/generations-fautives.yaml` en entier ; `ce1-qlm-generations.yaml`, lignes 1 a 160 ; `R12-2` B dans `arbitrage-nadia-contradictions-ce1-qlm-10-12.md` (`:180-184`, et `C2`, `:147-153`) et dans `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md` (`N12-2` `:201-207`, `R12-2` `:274-281`, tableau 2.2 `:320`) ; `ADR-0013`, sections Contexte et Decision.
- **Ce que ce fichier ne fait pas** : il ne modifie ni le paquet, ni le test, ni le jeu fautif, ni les arbitrages. La forme ci-dessous a ete **appliquee, mesuree puis retiree** de l'arbre (`git checkout -- adapters/adapter-content`, `git status --short` vide ensuite) : elle est a commiter par `marc-dev` avec les deux enonces, dans le meme commit (critere 3). Aucune regle ArchUnit, aucun `ADR` : garde de contenu sur du YAML, dans la forme d'`ADR-0013`.

## 0. Verdict

| Code | Question | Reponse |
|---|---|---|
| `V1` | Ce que `G12-3` verifie du sens | Que l'enonce d'un item `ordre` ecrit **le** sens de son groupe : un sens par groupe, lu a la lettre (section 1) |
| `V2` | Forme retenue | **Une table fermee groupe -> sens**, trois constantes nommees, lue par `contains` sur le texte mis a plat : `gen` -> *de la plus agee a la plus jeune*, `pre` -> *du plus age au plus jeune*, `evt` -> *du premier au dernier* (section 2) |
| `V3` | Assouplissement au sens d'`ADR-0013` | **Non.** Chaque groupe admet une seule forme avant comme apres ; pour `gen`, la forme admise est remplacee, pas ajoutee. La variante *"au moins une des formes admises"* sans lien au groupe **est** un assouplissement, mesure (section 2.3) : je l'ecarte |
| `V4` | Jeu fautif | Deux sondes neuves (`sonde 74`, `gen` au masculin ; `sonde 75`, `pre` au feminin), et les 13 enonces `gen` du jeu passes au feminin, dont 8 sont indispensables (section 2.4) |
| `V5` | Ne pas corriger et ne pas toucher la garde | **Ecarte** : l'utilisatrice veut le passage, et le releve juge les items 3 et 8 fautifs (section 3) |
| `V6` | Autres textes qui citent la formule | Liste en section 4, pour `marc-dev` ; le commentaire d'en-tete du paquet sort du `git diff` que le critere 3 autorise : `camille-po` tranche |
| `V7` | Texte de `R12-2` | A `nadia-prof`, seule auteure de la regle ; la constante de la garde suit son texte (section 5) |

## 1. Ce que `G12-3` verifie du sens, lu dans le test

`verifierLesSuites` (`PaquetCe1QlmGenerationsTest.java:359-422`) ne juge que les items de la tache `ordre`, c'est-a-dire ceux dont la bonne reponse a trois parties (`tacheDe`, `:262-270`). Sur leur enonce mis a plat (`plat`, `:208-211` : minuscule, sans accent par decomposition `NFD`, insecables ramenes a une espace), il fait deux lectures du sens :

1. `:365-370` : l'enonce contient `SENS_DU_PLUS_AGE` (`"du plus age au plus jeune"`, `:134`) ou `SENS_DU_PREMIER` (`"du premier au dernier"`, `:136`). Mordue par la `sonde 17` (*"Range ces générations dans l'ordre."*).
2. `:371-378` : le groupe de la bonne suite (`gen`, `pre` ou `evt`, `groupeDeLaSuite`) fixe le sens attendu : `evt` exige *du premier au dernier*, `gen` et `pre` exigent *du plus age au plus jeune*. Mordue par la `sonde 26` (une vie, *"du plus âgé au plus jeune"*).

**Pourquoi** (`N12-2`, `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md:203-205`) : *"Sans le sens ecrit, "enfants, parents, grands-parents" et son inverse sont deux bonnes reponses."* Le sens ecrit fait de la suite une reponse unique ; pour les generations et les prenoms, c'est l'ordre de l'age, de l'ancien vers le jeune, et la garde le relie aux rangs de `GENERATIONS` (`:384-388`, la suite `gen` doit etre croissante en rang, 0 = arriere-grands-parents). Le lien sens-groupe (lecture 2) est ce qui empeche un enonce d'une vie d'ecrire l'age, ou l'inverse.

**Ce que la garde ne lit pas, et ne lira pas apres** : l'accord lui-meme. Elle lit une chaine ; que cette chaine soit grammaticalement juste reste au `relecteur-pedagogique`. La forme ci-dessous **encode son releve** (une forme par groupe), elle ne juge pas l'accord d'un texte neuf.

## 2. La forme

### 2.1 Le code

Trois constantes nommees et une table fermee, a la place de `SENS_DU_PLUS_AGE` :

```java
private static final String SENS_DES_GENERATIONS = "de la plus agee a la plus jeune";

private static final String SENS_DES_PRENOMS = "du plus age au plus jeune";

private static final String SENS_DU_PREMIER = "du premier au dernier";

private static final Map<String, String> SENS_DU_GROUPE = Map.of(
        GEN, SENS_DES_GENERATIONS, PRE, SENS_DES_PRENOMS, EVT, SENS_DU_PREMIER);
```

Dans `verifierLesSuites`, les deux lectures de la section 1 deviennent :

```java
assertThat(SENS_DU_GROUPE.values().stream().anyMatch(plat::contains))
        .as("G12-3 : l'énoncé « %s » n'écrit aucun des trois sens", item.enonce())
        .isTrue();
// ... groupe calcule comme aujourd'hui ...
assertThat(plat.contains(SENS_DU_GROUPE.get(groupe)))
        .as("G12-3 : le sens que '%s' écrit n'est pas celui de son groupe (%s)", item.enonce(), groupe)
        .isTrue();
```

Les constantes sont ecrites sans accent parce que `plat` les retire : *âgée à* devient *agee a*. Le message de la lecture 2 ne change pas, et la `sonde 26` le garde.

### 2.2 Pourquoi une table et non une liste

La demande proposait *"une liste fermee de formes admises, lues par contains, et la verification que chaque item B porte au moins une de ces formes"*. La liste fermee est retenue ; **la seule verification "au moins une" ne l'est pas**, parce qu'elle detache la forme de ce qu'on range. Le releve le dit (section 4, point 1) : *"La garde ne doit pas pour autant accepter l'une ou l'autre indifferemment : le sens est lie au groupe."* La table est la liste fermee **plus** son lien au groupe, que le test portait deja (`:376`) : elle n'ajoute aucune piece, elle remplace un ternaire par une recherche dans une table de trois entrees.

### 2.3 Assouplissement ou non, au sens d'`ADR-0013`

Par groupe, l'ensemble des formes que la garde admet :

| Groupe | Avant | Apres | Effet |
|---|---|---|---|
| `gen` | *du plus age au plus jeune* | *de la plus agee a la plus jeune* | remplacee : le masculin est desormais **refuse** (l'enonce que le releve juge fautif) |
| `pre` | *du plus age au plus jeune* | inchange | aucun |
| `evt` | *du premier au dernier* | inchange | aucun |

Une forme admise par groupe avant, une apres. Aucune faute que la garde refusait n'est admise apres ; une faute de plus est refusee (`gen` au masculin). **Ce n'est pas un assouplissement.**

**Mesure de la variante "au moins une"**, sur l'arbre de la section 6 : j'ai remplace la lecture 2 par `SENS_DU_GROUPE.values().stream().anyMatch(plat::contains)`. Resultat, `PaquetCe1QlmGenerationsTest` seul (perimetre et commande en section 6) : 40 tests, **2 echecs** : `leJeuFautifCommiteEstRefuseSurLOrdreInverseDeLaBonneSuiteEtSurUnEnonceSansSens` (`sonde 74`, `gen` au masculin, admise) et `leJeuFautifCommiteEstRefuseSurUneSuiteHorsDeSonOrdreDeSonSensOuDeSesElements` (`sonde 26`, une vie ecrite *du plus âgé au plus jeune*, admise). La variante laisse donc passer **le texte fautif que la story corrige** et **une sonde que la garde refuse aujourd'hui** : elle est un assouplissement, et le jeu fautif le montre rouge.

### 2.4 Ce que le jeu fautif doit contenir

Fichier : `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1QlmGenerationsTest/contenu/generations-fautives.yaml`.

**`J1` - deux sondes neuves**, ajoutees en fin de fichier, chacune lue par `refuse(verifierLesSuites, ...)` :

| Sonde | Enonce | Bonne reponse, distracteurs | Fragment attendu | Ce qu'elle prouve |
|---|---|---|---|---|
| `sonde 74` | *"Dans la famille de Lina, quelle suite range les générations du plus âgé au plus jeune ?"* (texte reel des items 3 et 8 a `78cd43c4`, item 3 a l'identique) | ceux de la `sonde 22` | `pas celui de son groupe (gen)` | le masculin sur des generations, la faute du releve, est refuse ; c'est le materiau du monde, pas une fixture inventee |
| `sonde 75` | *"Omar a une fille, Julie. Julie a un fils, Noé. Quelle suite va de la plus âgée à la plus jeune ?"* | ceux de la `sonde 14` | `pas celui de son groupe (pre)` | la forme feminine ne fuit pas vers les prenoms, que le releve garde au masculin (item 11, JUSTE) |

Les deux fragments nomment le groupe, pour qu'une sonde ne passe pas en etant refusee pour une autre raison. Espace avant `?` : `U+00A0`, comme le paquet. Le commentaire d'en-tete du jeu (`:13-14`) ajoute *"74 et 75 de G12-3 (sens lie au groupe)"*. Le fragment de la `sonde 17` (`:777-778`) devient `n'écrit aucun des trois sens`.

**`J2` - les enonces `gen` du jeu passent au feminin.** Le jeu porte 13 enonces *"... les générations du plus âgé au plus jeune ?"* (`:72`, `:87`, `:92`, `:107`, `:122`, `:127`, `:132`, `:137`, `:152`, `:157`, `:247`, `:342`, `:347`, `grep`). Huit sont lus par `verifierLesSuites` sur un groupe `gen` et rougiraient **pour la mauvaise raison** (sens refuse avant le defaut vise) : `sonde 16`, `19`, `25`, `28`, `29` (refus dont le fragment ne serait plus trouve) et `sonde 22`, `23`, `24` (passages qui deviendraient des refus). Lu dans le code (`groupeDeLaSuite` rend `gen` pour chacune) ; la section 6, ligne 2, en observe une partie. Les cinq autres (`sonde 12`, `15`, `47`, `66`, `67`) ne sont pas lus par `verifierLesSuites` sur un groupe `gen` ; je demande de les passer aussi au feminin, pour que le jeu n'ecrive la faute que la ou une sonde la vise. Remplacement mecanique de la chaine, 13 occurrences.

**Message et fragment de la suite des generations** (`:385`, `:818`) : *"n'est pas du plus âgé au plus jeune"* decrit une suite de generations. Je demande *"n'est pas de la plus âgée à la plus jeune"* aux deux lignes, dans le meme commit. Non indispensable a la morsure : la `sonde 25` passe avec l'un ou l'autre texte tant que message et fragment changent ensemble.

## 3. L'alternative ecartee : ne pas corriger, ne pas toucher la garde

Garder les items 3 et 8 au masculin et `G12-3` en l'etat laisserait le test vert et le paquet servi avec deux enonces que le releve juge **FAUTIF** ; la story `S57-QLM-CE1-12-ACCORD` (critere 2) et `DEC-0120` A.283 D3 retiennent l'entree de `-12` au catalogue tant que l'accord n'est pas regle. L'utilisatrice a decide le 2026-10-29 de faire passer `QLM-CE1-12`. **Ecartee.**

Variante voisine, ecartee aussi : corriger les deux enonces et retirer la lecture 2 (garder seulement *"au moins un sens"*). C'est la variante mesuree en 2.3 : deux sondes perdues.

## 4. Les textes qui citent la formule, pour `marc-dev`

Commande : `grep -rn -i "plus [aâ]g[eé]"` sur l'arbre a `78cd43c4`, hors `.git`. Perimetre : tout le depot ; classement de ma main.

| Code | Fichier, ligne | Texte | Action |
|---|---|---|---|
| `L1` | `ce1-qlm-generations.yaml:96`, `:131` | enonces des items 3 et 8 | textes du releve, section 3, mot pour mot (`marc-dev`) |
| `L2` | `ce1-qlm-generations.yaml:152` | enonce de l'item 11 | **inchange** (releve : JUSTE) |
| `L3` | `ce1-qlm-generations.yaml:25` | *"Generations, du plus age au plus jeune : arriere-grands-parents, ..."* | *"de la plus agee a la plus jeune"* ; voir `L9` |
| `L4` | `ce1-qlm-generations.yaml:53` | *"L'enonce dit le sens : "du plus age au plus jeune" (generations et prenoms), ..."* | *"de la plus agee a la plus jeune" (generations), "du plus age au plus jeune" (prenoms), "du premier au dernier" (une vie)* ; voir `L9` |
| `L5` | `ce1-qlm-generations.yaml:106` | indice *"les plus âgés en haut"* (des noms, donc des personnes) | **inchange** ; admis par `nadia-prof` (`lecture-nadia-premier-jet-ce1-qlm-generations.md:110`), hors du releve |
| `L6` | `PaquetCe1QlmGenerationsTest.java:134`, `:365-370`, `:376`, `:778` | constante, lectures du sens, fragment de la `sonde 17` | section 2.1, 2.4 |
| `L7` | `PaquetCe1QlmGenerationsTest.java:385`, `:818` | message et fragment de la suite des generations | section 2.4, meme commit |
| `L8` | `PaquetCe1QlmGenerationsTest.java:85` | Javadoc *"rang du plus age au plus jeune"* des generations | *"de la plus agee a la plus jeune"* ; aucun test ne le lit |
| `L9` | jeu fautif, 13 lignes | section 2.4, `J2` | `marc-dev` |

**`L9` bis, a `camille-po`.** Le critere 3 limite le `git diff` *"aux lignes `enonce:` des items 3, 8 et 11, et au test"*. Je lis *"le test"* comme la classe et son jeu fautif : `ADR-0013` (obligation 3) fait du jeu fautif une partie de la garde. `L3` et `L4` sont des lignes de commentaire du paquet, hors de ce perimetre a la lettre. Aucun test ne les lit (le seul motif d'en-tete lu est `TACHE_DE_L_ENTETE`, `:168`, qui ne reconnait que les lignes `# voisin :` et `# ordre :`). Les laisser ferait dire au fichier une regle que sa garde refuse ; les changer elargit le diff du critere. `camille-po` tranche ; mon avis est de les changer.

Hors liste, et non modifies : les mentions historiques (`backlog.md`, `planning.md`, `DEC-0120`, `FICHE-ce1-qlm-12.md`, mes avis `avis-theo-palier-5-gardes-ce1-qlm-10-12.md:115` et `avis-theo-palier-5-ajustements-gardes-ce1-qlm-10-12.md:181`). Ce sont des ecrits dates ; cet avis remplace, pour `G12-3`, la forme qu'ils decrivent.

## 5. Ce que cela change aux arbitrages de `nadia-prof`

La garde lit `R12-2` a la lettre. Trois passages ecrivent la formule masculine pour les generations : `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md` `:204` (`N12-2`), `:279` (`R12-2` B, *"du plus âgé au plus jeune (generations)"*) et `:320` (tableau 2.2, ligne `G12-3`). Le passage `arbitrage-nadia-contradictions-ce1-qlm-10-12.md:184` applique la formule aux **prenoms** (*"trois prenoms de l'enonce, [...] dans le sens du plus âgé au plus jeune"*), ce que le releve juge juste.

**Je ne reecris pas ces textes : `nadia-prof` tranche son texte**, et `camille-po` le reporte dans la story (*"camille-po reporte"*, critere 9 de la story `-12`). Ce que la garde attend d'elle :

- la forme de `R12-2` B pour les generations. Si elle retient une autre forme que *de la plus âgée à la plus jeune*, seule la valeur de `SENS_DES_GENERATIONS` et l'enonce de la `sonde 22` changent ; la table, les sondes 74 et 75 et les lectures restent ;
- que les prenoms gardent *du plus âgé au plus jeune*. S'ils changent aussi, `SENS_DES_PRENOMS` et la `sonde 75` changent.

Dependance, que je ne sequence pas (`sofia-sm`) : le critere 3 veut que l'enonce et la formule de `R12-2` changent ensemble ; le commit de `marc-dev` a donc besoin du texte de `nadia-prof`, et de sa relecture des enonces corriges (`T3` du releve) avant `verify`.

## 6. Mesures

JDK : *"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*, ligne imprimee par `outils/mvn-jdk21.sh`, qui la demande au JDK qu'il a resolu. Commande des lignes 1 a 4 : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmGenerationsTest -Dsurefire.failIfNoSpecifiedTests=false`. Perimetre : la seule classe `PaquetCe1QlmGenerationsTest`, ligne `Tests run:` de Surefire pour cette classe.

| # | Arbre | Resultat |
|---|---|---|
| 1 | `78cd43c4` tel quel | 40 tests, 0 echec |
| 2 | enonces 3 et 8 corriges, 13 enonces `gen` du jeu au feminin, sondes 74 et 75 ajoutees, **test d'origine** | 40 tests, **5 echecs** : `g123...` (l'item 3 corrige, *"n'écrit ni « du plus âgé au plus jeune »"*, le refus annonce par le releve) ; les methodes des sondes 16, 19 et 25 (fragment non trouve) ; la methode des sondes 22 a 24 (refus a la `sonde 22`). Une methode s'arrete a sa premiere sonde en echec : 23, 24, 28 et 29 ne sont pas observees, elles sont deduites du code (`J2`) |
| 3 | arbre 2 avec la forme de la section 2.1 et les deux appels `refuse` des sondes 74 et 75 | 40 tests, 0 echec |
| 4 | arbre 3, lecture 2 remplacee par *"au moins un sens"* (variante 2.3) | 40 tests, **2 echecs** : sondes 74 et 26 |
| 5 | arbre 3, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` | dernier resume Surefire, module `adapter-content` : 2569 tests, 0 echec, `BUILD SUCCESS`. Les autres gardes du module, dont `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` qui nomme ce paquet, acceptent les deux enonces allonges de 2 mots |

Non mesure : `bootstrap` (le paquet n'est pas au catalogue, `grep -c "paquet: ce1-qlm-generations$" catalogue.yaml` rend 0) et les jobs de CI autres que ces tests. Le nombre de tests de la classe reste 40 a l'arbre 3 : les deux sondes neuves s'ajoutent dans une methode existante. Les arbres 2 a 5 ont ete retires de l'arbre de travail apres mesure ; ce commit ne porte que cet avis.
