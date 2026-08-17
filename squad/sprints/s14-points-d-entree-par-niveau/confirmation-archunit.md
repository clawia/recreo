# [Sprint s14] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-17
- **Auteur** : theo-archi
- **Arbre juge** : la branche `s14-theo`, forkee de `round-two` a **`e30566b`**, **avec mon lot** -
  les deux `ADR` de s14 et leurs trois garde-fous. Lignee verifiee par `outils/verifier-lignee.sh`.
- **Provisionnement** : worktree dedie hors de l'arbre du depot.
- **Prerequis** : le `java` du `PATH` de ce poste n'est pas un JDK 21. Toutes les mesures sont prises
  sur le **JDK 21.0.10** designe par la toolchain de l'utilisateur, via `bash outils/mvn-jdk21.sh`.
- **Ce que ce fichier ne fait pas** : aucun Done pedagogique ni son anticipation, aucun arbitrage
  produit, aucune ligne dans `rapport-qualite.md`, dans `squad/conformite/` ni dans le planning de
  `sofia-sm`.
- **Ou vit l'etat de depart du sprint** : `squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/confirmation-archunit.md`,
  section 5, ecrite le meme jour - elle porte l'arbre fusionne `e30566b` **sans** mon lot, avec
  l'inventaire des cliquets. Ce fichier-ci ne porte que la **difference**, pour que les deux ne se
  contredisent jamais sur le meme arbre.

## 1. Le resultat de la commande

`bash outils/mvn-jdk21.sh -B clean verify`, jamais un perimetre partiel :

```
recreo ............................................. SUCCESS
recreo-domain ...................................... SUCCESS
recreo-application ................................. SUCCESS
recreo-adapter-web ................................. SUCCESS
recreo-adapter-content ............................. SUCCESS
recreo-bootstrap ................................... SUCCESS
BUILD SUCCESS
Total time:  01:56 min
```

**495 tests, 0 echec, 0 erreur, 0 ignore. 29 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe que le chiffre.** Somme de l'attribut
`tests` de la balise racine `testsuite` des **78 rapports XML** produits par cette seule execution,
sous `*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans
`modules`** du pom agregateur, lus **apres le `clean`** de cette commande. Jamais un balayage lance
depuis la racine du depot, qui verrait les worktrees des autres agents. Repartition : `adapter-web`
**233**, `adapter-content` **120**, `bootstrap` **88** (dont 6 en failsafe), `domain` **47**,
`application` **7**. **Exclus explicitement** : les resumes `.txt` de Surefire, qui ne conservent
qu'un seul moteur JUnit par classe et sous-comptent.

**Methode du comptage des regles, deux methodes qui doivent concorder et concordent.** Critere
`ArchRule <nom> =`.

1. tous les fichiers `.java` du depot dont le chemin contient `src/test/java`, **suivis et non
   suivis** - ce point compte ici plus que d'habitude, ma regle neuve n'etant pas encore commitee au
   moment du premier comptage, ce qui l'a rendue invisible a un `git ls-files` seul : **31 brut**.
   Deux de ces trente-et-un vivent dans le **faux depot** de contre-exemple de
   `LesGardeFousSontMisALEpreuveTest`, sous `src/test/resources`, dont le chemin contient
   `src/test/java` sans etre un source de module. **31 - 2 = 29** ;
2. balayage des seuls `src/test/java` des cinq modules declares au pom : **29** directement,
   repartis en `bootstrap` **28** et `adapter-web` **1** (`AssertionsWebTest`). `domain`,
   `application` et `adapter-content` en portent zero.

## 2. La difference que mon lot fait, ligne par ligne

| | avant (`e30566b`) | apres (mon lot) | ecart |
|---|---|---|---|
| tests | 489 | **495** | +6 |
| regles ArchUnit | 28 | **29** | +1 |
| rapports XML lus | 76 | **78** | +2 classes de test |

Les six tests, nommes plutot que comptes :

- `LeChoixDesJeuxResteDerriereLePortTest.aucunAdaptateurDeRenduNeLitLeDescripteurDeContenu` - la 29e
  regle ArchUnit (`ADR-0044`) ;
- `LesReglesMordentTest.uneVueQuiFiltreLeCatalogueParNiveauEstRefusee` - sa preuve de morsure
  permanente, exigee par `ADR-0003` et par le recensement
  `LesGardeFousSontMisALEpreuveTest.aucuneRegleArchUnitNEchappeAuContreExemple`, qui refuse toute
  `ArchRule` jamais passee a un `.check(` ;
- `LeChoixDesJeuxNeVitPasDansLaVueTest.aucunGabaritDeProductionNeLitLeDescripteurDeContenu` et
  `.unGabaritQuiFiltreLeCatalogueParNiveauFaitEchouerLeGardeFou` - le versant qu'ArchUnit ne peut pas
  voir (`ADR-0044`), et son jeu fautif ;
- `LActionNeDeclarePasMoinsQueLaSortieTest.unPointDEntreePermanentQuiDepasseLActionFaitEchouerLeGardeFou`
  et `.unPointDEntreeQueRienNeConfronteFaitEchouerLeGardeFou` - les deux clauses d'`ADR-0043`, chacune
  sur son jeu fautif.

**Aucune assertion existante n'a ete affaiblie.** Deux messages d'echec de
`LActionNeDeclarePasMoinsQueLaSortieTest` enumerent desormais les roles lus depuis la constante
plutot qu'en clair, ce qui les empeche de perimer a la valeur suivante ; les deux epinglages qui
citent des couples confrontes (`surLEcranDeRetour...`, `leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil`)
sont inchanges et verts, la production ne portant encore aucun bouton du troisieme role.

## 3. Morsure mesuree, sur du materiau reel plutot que sur mes fixtures seules

Methode commune : une mutation a la fois sur ce worktree, arbre restaure ensuite et verifie propre par
`git status --porcelain`, JDK 21.0.10.

| Mutation | Commande | Mesure |
|---|---|---|
| Le filtre par niveau ecrit dans `PartieController` (`.filter(jeu -> jeu.descripteur().niveau().equals(niveau))`) | `-pl bootstrap -am test -Dtest=LeChoixDesJeuxResteDerriereLePortTest` | **1 echec sur 1**, message *"Method `PartieController.jeuxDuNiveau(java.lang.String)` calls method `ContentDescriptor.niveau()` in (PartieController.java:310)"* |
| Deux puces de niveau dans l'en-tete, boite chiffree par `ines-design`, `data-role="sortie"` | `-pl adapters/adapter-web -am test -Dtest=LActionNeDeclarePasMoinsQueLaSortieTest,LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` | **1 echec**, `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot` ; la saillance reste verte, 11 sur 11 |
| Les memes puces, `data-role="action"` | idem | **2 echecs sur 11**, `aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran` avec 16 ecarts nommes sur quatre ecrans, et `surLEcranDeRetourAucuneActionNEstPlusConfronteeALaSortieDepuisLaDisparitionDEncore` |
| Les memes puces, `data-role="entree"`, aucun controleur derriere | `-pl adapters/adapter-web test` (233 tests) | **3 echecs**, et aucun des trois n'est un garde-fou de saillance : `TexteAccentueDesGabaritsTest` (le texte "CP" hors inventaire), `HabillageDesEcransTest` (la classe `bandeau-niveaux` que ma mutation n'avait pas stylee), `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` (le comptage fixe a un seul element actionnable dans l'en-tete) |

La derniere ligne est celle qui vaut le plus pour le planning : elle dit **exactement** ce qui rougira
quand la barre landera, et elle est detaillee dans `avis-faisabilite.md`.

## 4. Les cliquets, et ce que mon lot leur fait

**Rien.** Aucun plafond ne bouge, aucune dette ne grandit, aucune exemption n'est ajoutee. Etat
inchange par rapport a la section 5.2 du fichier de s13, ou l'inventaire complet est publie avec sa
methode : cinq cliquets d'exemption, jeu nul sur les cinq, chaque exemption nommant encore un objet
reel ; deux budgets de production sur le JavaScript maison, marge large et voulue.

Ce que mon lot **aurait** pu leur faire et ne fait pas, verifie plutot que suppose : les deux classes
de test neuves lisent `src/main/` et entrent donc dans le recensement de mise a l'epreuve de
`LesGardeFousSontMisALEpreuveTest`. Aucune des deux n'entre dans `DETTE_DE_BALAYAGE` : chacune porte
son `assertThatThrownBy` et son repertoire de jeu fautif non vide. Mesure :
`LesGardeFousSontMisALEpreuveTest` **10 tests sur 10**, dont
`aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve` et `laDetteNePeutNiGrandirNiPourrir`, qui asserte
`hasSize(PLAFOND_DE_DETTE)` - donc qu'aucune place ne s'est liberee.

## 5. Second lot de s14 - la derivation du catalogue (`ADR-0045`)

- **Arbre juge** : la branche `s14-theo-derivation`, worktree dedie, forkee de `round-two` a
  **`c1abb47`** - donc **avec** les trois lots deja fusionnes du sprint - **et avec mon second lot**.
  Lignee verifiee par `outils/verifier-lignee.sh`.
- Les sections 1 a 4 ci-dessus jugent un autre arbre (`e30566b` plus mon premier lot) et ne sont pas
  reecrites : cette section-ci porte la difference sur `c1abb47`.

### 5.1 Le resultat de la commande

`bash outils/mvn-jdk21.sh -B clean verify`, jamais un perimetre partiel :

```
recreo ............................................. SUCCESS
recreo-domain ...................................... SUCCESS
recreo-application ................................. SUCCESS
recreo-adapter-web ................................. SUCCESS
recreo-adapter-content ............................. SUCCESS
recreo-bootstrap ................................... SUCCESS
BUILD SUCCESS
Total time:  02:43 min
```

**506 tests, 0 echec, 0 erreur, 0 ignore. 29 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe que le chiffre.** Somme de l'attribut `tests`
de la balise racine `testsuite` des **81 rapports XML** produits par cette seule execution, sous
`*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans
`modules`** du pom agregateur, lus **apres le `clean`** de cette commande. Jamais un balayage lance
depuis la racine du depot, qui verrait les worktrees des autres agents. Repartition : `adapter-web`
**238**, `adapter-content` **121**, `bootstrap` **88** (dont 6 en failsafe), `domain` **48**,
`application` **11**. **Exclus explicitement** : les resumes `.txt` de Surefire, qui ne conservent
qu'un seul moteur JUnit par classe et sous-comptent.

**Methode du comptage des regles, deux methodes qui concordent.** Critere `ArchRule <nom> =`.

1. tous les fichiers `.java` de l'arbre dont le chemin contient `src/test/java`, hors `target`, suivis
   et non suivis : **31 brut**, dans 13 fichiers. Deux de ces trente-et-un vivent dans le **faux
   depot** de contre-exemple de `LesGardeFousSontMisALEpreuveTest`, sous `src/test/resources`, dont le
   chemin contient `src/test/java` sans etre un source de module. **31 - 2 = 29** ;
2. balayage des seuls `src/test/java` des cinq modules declares au pom : **29** directement.

**Ecart avec le chiffre de `sofia-sm` sur `c1abb47` (502 tests, 29 regles) : +4 tests, +0 regle**, et
les quatre sont nommes - les quatre methodes de `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`. Son
compte est donc confirme par difference sur le meme arbre.

### 5.2 Morsure mesuree, sur le catalogue de production mute

Detail et messages dans `ADR-0045`. Ce qui compte ici est le resultat des commandes, une mutation a la
fois, arbre restaure et verifie propre par `git status --porcelain` apres chacune :

| Mutation du catalogue servi | Commande | Mesure |
|---|---|---|
| l'entree des mesures designe `cm1-perimetre` | `-B -o -pl adapters/adapter-content -am test` | **1 seul echec** sur les 180 tests des trois modules construits, celui du garde-fou neuf. Les 21 autres classes de `adapter-content` sont vertes, dont `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` et `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` |
| l'entree des mesures designe `cm1-homophones` | `-B -o verify` | **BUILD SUCCESS**, 0 echec sur 506. C'est la non-preuve 1 d'`ADR-0045`, mesuree |
| trois catalogues fautifs jamais servis | `-Dtest=AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` | **4 tests, 0 echec** : deux morsures et un corrige |

**Un point de methode a garder pour tout le monde, et il invalide une forme de commande que j'ai
moi-meme employee dans l'avis de faisabilite : une mesure partielle porte `-am`.** Mesure du
2026-08-17 sur cet arbre, sans aucune mutation :
`-pl adapters/adapter-content test -Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
rend **1 echec sur 15** (`cp-additions-jusqu-a-10`, strategie a rang constant a 63,1 %), et la meme
commande avec `-am` rend **0 echec sur 15**. Sans `-am`, le module se lie au `recreo-domain`
0.1.0-SNAPSHOT installe dans le depot local de l'utilisateur, pas a celui du reacteur. Ici un faux
rouge, qui m'a coute un aller-retour ; la meme cause produira un faux vert.

### 5.3 Les cliquets, et le plafond de 150 tirages

**Aucun cliquet ne bouge** : aucun plafond, aucune dette, aucune exemption. Etat inchange par rapport
a la section 5.2 du fichier de s13 - cinq cliquets d'exemption, jeu nul sur les cinq ; deux budgets de
production sur le JavaScript maison. Verifie et pas suppose : le garde-fou neuf lit `src/main/`, entre
donc dans le recensement de mise a l'epreuve, et n'entre pas dans `DETTE_DE_BALAYAGE` - il porte son
`assertThatThrownBy` et trois catalogues fautifs. `LesGardeFousSontMisALEpreuveTest` : **10 sur 10**.

**Un rouge que j'ai produit et qu'il faut lire.** Sur une execution intermediaire de
`bash outils/mvn-jdk21.sh -B clean verify`, `aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve` a echoue
- *"Expecting empty but was: [\"ChaqueEntreeDuCatalogueChargeBienSonPaquetTest\"]"* - parce que la
methode d'un chiffre que j'avais ajoutee dans une Javadoc contenait le chemin
`src/main/resources/contenu/`, et que ce recensement reconnait un balayage de production au **litteral
`src/main/` dans la source**. Un commentaire a fait entrer une classe dans une population, sans
qu'aucune assertion ne bouge ; le litteral retire de la prose, la suite est verte. Ce n'est pas un
incident de mon lot, c'est une mesure sur le perimetre du recensement, et elle est versee en non-preuve
5 d'`ADR-0045` avec la seconde moitie du probleme : ce garde-fou-la ne peut structurellement pas
recevoir le jeu fautif que le recensement exigerait de lui.

**Le `TIRAGES = 150` de `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` : ni un cliquet d'exemption,
ni un budget de production. C'est un troisieme genre, une borne de terminaison d'echantillon, et la
distinction n'est pas une echappatoire.** Elle se lit sur le mode de defaillance, comme celle du
2026-08-17 entre cliquet et budget :

- un **cliquet d'exemption** plafonne une liste de choses exemptees : sa place libre laisse entrer en
  silence l'exemption suivante, d'ou l'exigence "plafond = taille de la dette" ;
- un **budget de production** plafonne un artefact livre (`PLAFOND_LIGNES = 100`, `PLAFOND_OCTETS =
  4096` sur le JavaScript maison) : sa marge est ce qu'`ADR-0008` autorise a dessein ;
- une **borne d'echantillon** ne plafonne rien qui existe : elle existe parce qu'un paquet
  combinatoire ne rend jamais `Optional.empty()` (`AdditionsBorneesContentSource.next`, fait connu
  depuis le contexte d'`ADR-0012`), donc une boucle "jusqu'a epuisement" n'y termine pas - `lea-dev` a
  constate un `OutOfMemoryError` reel avant son commit final. Son mode de defaillance n'est pas une
  exemption silencieuse mais une **comparaison partielle** : au-dela de la borne, les deux cotes sont
  tronques ensemble et le test reste vert.

**Ce que cette comparaison partielle couterait, mesure plutot qu'estime : rien sur la propriete
gardee.** Les deux cotes compares sont `games()` et `chargerPaquet(paquetId)`, or `games()` resout son
paquet en appelant `chargerPaquet` avec la meme chaine, sur un chargeur sans etat : une divergence
apparait au premier item ou jamais. Et la marge est large - le plus grand paquet enumere du depot est
`cm1-tables-multiplication` avec **99 items** (methode : lignes commencant par `  - ` sous la cle
`items:`, les douze paquets de `adapters/adapter-content/src/main/resources/contenu/` comptes le
2026-08-17 ; les onze autres vont de 11 a 28 items, `cp-additions-jusqu-a-10` etant combinatoire et
sans taille). **Verdict : la borne est legitime et n'a pas a etre derivee.**

**Ce qui, en revanche, etait un chiffre sans methode, et que je corrige dans ce commit** : la Javadoc
justifiait 150 par *"le plus grand paquet enumere connu du depot (cm1-tables-multiplication, 99
items)"*, sans date ni methode de ce 99 - exactement le defaut que j'ai refuse au critere "les onze
jeux" en section 7.8 de l'avis de faisabilite. La borne ne pourrit pas ; **sa justification, si**. Une
date et une methode coutent une ligne, elles sont ecrites. Ce qui la ferait bouger, ecrit d'avance :
un paquet enumere au-dela de 150 items, ou un second consommateur de cette boucle - la borne
rejoindrait alors la forme du garde-fou frere, `TIRAGES_PAR_ITEM_VISE` multiplie par un nombre derive
du catalogue.

### 5.4 Ce que cette section ne fait pas

Aucun Done pedagogique ni son anticipation, aucun arbitrage produit, aucune ligne dans
`rapport-qualite.md`, dans `squad/conformite/` ni dans le planning de `sofia-sm`. La perte de
couverture que `sofia-sm` m'a routee est tranchee dans `ADR-0045`, pas ici : ce fichier ne porte que
le resultat des commandes et l'etat des plafonds.

## 6. Accord sur le lot S14-2 de `marc-dev` - geste par geste

- **Date** : 2026-08-17. **Auteur** : `theo-archi`.
- **Arbre juge** : `s14-marc-barre7` a **`d950192`**, forkee de `round-two` a `81a43ac`.
- **Ce que cette section est** : la confirmation ecrite que `marc-dev` a demandee sur quatre gestes
  qu'il a proposes sans les trancher, et sans laquelle `S14-2` ne cloture pas. **Un accord global n'y
  figure pas** : chaque geste est juge separement, et l'un des cinq est refuse en l'etat.
- **Ce que cette section n'est pas** : un Done pedagogique, ni son anticipation. `DEC-0033` a tranche
  le produit, `nadia-prof` jugera l'increment.

### 6.1 La porte technique, mesuree et non reprise sur parole

`bash outils/mvn-jdk21.sh -B clean verify` sur son worktree, JDK 21.0.10 designe par la toolchain :
**BUILD SUCCESS**, `Total time: 02:52 min`, les six modules `SUCCESS`.

**505 tests, 0 echec, 0 erreur, 0 ignore. 29 regles ArchUnit.** Methode, dans le meme paragraphe :
somme de l'attribut `tests` de la balise racine `testsuite` des **81 rapports XML** produits par cette
seule execution, sous `*/target/surefire-reports/` et `*/target/failsafe-reports/` des cinq modules
declares au pom, lus **apres le `clean`** ; repartition `adapter-web` 237, `adapter-content` 121,
`bootstrap` 88 (dont 6 en failsafe), `domain` 48, `application` 11. Les regles par balayage des seuls
`src/test/java` de ces cinq modules, critere `ArchRule <nom> =` : **29**, inchange.

**Les chiffres de `marc-dev` se verifient**, y compris son ecart de `-1` : deux tests retires
(gestes 1 et 3), un renomme (geste 2, net nul), un ajoute (geste 2). Son arbre est reste propre
pendant ma mesure (`git status --porcelain` vide, `target/` etant ignore).

### 6.2 Geste 1 - epinglage `leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil` retire : **ACCORD**

Le patron s'applique, et `marc-dev` ne l'a pas etendu au-dela de ce que j'avais ecrit. Verifie
assertion par assertion plutot que sur la ressemblance des motifs :

- la premiere exigeait qu'un couple `.btn.demarrer` contre `.btn.lien-accueil` **existe** sur
  l'accueil. `.lien-accueil` n'existe plus dans aucun gabarit : le couple ne peut plus se former, donc
  l'assertion ne peut plus qu'echouer, et la maintenir demanderait de remettre l'element ;
- la seconde exigeait que ce lien ne soit **plus** confronte aux actions des ecrans de jeu. Elle
  devient vraie a vide, sur un role qu'aucun bouton ne porte plus. C'est la definition d'un epinglage
  decoratif.

**Ce que mon patron exige en plus, et qui est tenu ici** : que la propriete survive ailleurs, et non
que l'epinglage disparaisse seul. Verifie, pas suppose : `aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran`
garde la propriete sur les sorties restantes ; `rolesDominesJamaisConfrontes` (`ADR-0043`) empeche le
role `entree` de se derober silencieusement ; et le **mecanisme de traversee de frontiere** que
`ADR-0022` avait garde a population vide en s06 **garde la sienne**, les puces de niveau etant
permanentes et masquees par rien. L'epinglage change de porteur, la propriete ne change pas de main.

### 6.3 Geste 2 - `EnTeteEtMenuTest`, test remplace et test neuf : **ACCORD, avec une modification exigee**

Le remplacement est juste : l'ancien test affirmait une sortie dans l'en-tete, `DEC-0033` la retire, et
la forme derivee (un point d'entree par niveau distinct du catalogue, aucun effectif ecrit) est celle
que j'avais specifiee en section 7.1 de l'avis de faisabilite. Il en garde la propriete et y ajoute
`isEmpty()` sur `header [data-role=sortie]`, ce qui epingle exactement ce que `DEC-0033` decide.

**Modification exigee sur le test neuf `surLAccueilNonFiltreLEnTeteNeMontreQueLIconeLeTitreEtLesPucesDeNiveau`.**
Il compare `header button, header a[href]` a `niveauxDuCatalogue()`. Deux defauts, aucun bloquant mais
tous deux gratuits a corriger :

1. **il re-asserte la propriete du test precedent** (un point d'entree par niveau du catalogue, dans
   l'ordre) au lieu de la sienne, qui est *"rien d'actionnable dans l'en-tete hors des puces"* ;
2. **il couple le critere 2 de `S14-2` a une coincidence** : que le libelle rendu d'une puce soit
   **egal** a la valeur brute du niveau. C'est vrai de "CP" et "CM1" et ce n'est pas une propriete -
   `ADR-0044` inscrit lui-meme, dans ce qu'il ne garde pas, que *"la graphie de ces valeurs est une
   question de redaction pour un enfant"*, et c'est la question meme que je differe a s15 pour les
   matieres. Le jour ou un libelle differera de sa valeur, ce test rougira pour un motif qu'il ne
   nomme pas.

**Forme demandee, qui dit exactement le critere 2 et rien d'autre** : l'ensemble des elements
actionnables de l'en-tete est exactement l'ensemble de ses `[data-role=entree]`. Aucun libelle, aucun
catalogue, aucun effectif. Elle interdit reellement la seule chose que le critere 2 interdit - une
troisieme boite actionnable a cote des puces - et elle reste vraie quelle que soit la graphie retenue.
**Je ne l'ecris pas a la place de `marc-dev`** : elle est routee par `sofia-sm`.

### 6.4 Geste 3 - `chaqueEcranInsereDansLaZonePorteLeMarqueurQueLaFeuilleExige` retire : **REFUS EN L'ETAT**, accord conditionne

**Le retrait de ce test-la est fonde ; ce qui manque est ce qu'il emporte avec lui.** Sa premiere
assertion exige qu'au moins un masquage de la forme contractuelle soit declare en production ; la
production n'en declare plus, donc le maintenir le rendrait rouge pour un motif qu'il n'a pas choisi de
garder. Et le retrait de la regle CSS elle-meme est doublement fonde : deux garde-fous independants la
refusent, celui d'`ADR-0022` et celui d'`ADR-0031`. Sur ce point `marc-dev` a mesure les deux
strategies au lieu d'en supposer une, ce qui est exactement ce qu'on lui demande.

**Ce que le retrait emporte, et qui n'a pas ete vu.** Ce test gardait **deux** proprietes, pas une :
qu'un masquage existe (perimee), et **que tout gabarit insere dans la zone porte le marqueur d'etat**
(intacte). Or le marqueur `ecran-de-jeu` a toujours un consommateur vivant : `LEcranDeJeuDeclareCommentIlEstArriveTest`
(`ADR-0027`) **derive sa population d'ecrans par `select(".ecran-de-jeu")` sur les reponses reelles**,
et sa non-vacuite est globale - "au moins un ecran de jeu est rendu" - jamais par fragment. Verifie sur
son arbre : apres ce lot, **aucune clause du depot n'exige qu'un fragment porte le marqueur.**

**Consequence, et c'est un vert silencieux au sens exact de ce depot** : un cinquieme fragment qui
oublierait `class="ecran-de-jeu"` sortirait du perimetre d'`ADR-0027` sans qu'aucun test ne rougisse.
C'est mot pour mot la premiere ligne de la table de garde-fous d'`ADR-0022` (*"Un fragment neuf oublie
le marqueur | les ecrans sont derives du repertoire de gabarits"*), et la famille que la retro de s05 a
sanctionnee deux fois.

**Condition de mon accord** - la propriete est re-portee, **decouplee du masquage** :

> **Enonce** : tout gabarit du repertoire de gabarits qui n'est pas la coquille porte le marqueur
> d'etat de l'ecran de jeu.
> **Emplacement** : `LEcranDeJeuDeclareCommentIlEstArriveTest`, la classe qui **consomme** ce marqueur
> et dont la population serait amputee en silence. C'est le consommateur qui doit prouver que sa
> population est complete, pas la feuille qui ne le nomme plus.
> **Derivation** : la coquille est identifiee par le fait que son document **contient la zone
> `hx-target`**, jamais par son nom de fichier - la derivation d'`ADR-0022`, deja partagee dans le
> paquet par `documents` et `zoneEchangee` (`ADR-0031`), donc rien a reecrire. Le nom du marqueur se lit
> dans la constante que cette classe porte deja.
> **Derivation totale** : aucun gabarit, aucune zone, plusieurs zones distinctes, aucune coquille,
> plusieurs coquilles, aucun fragment restent des **echecs**, jamais des cas ignores.
> **Preuve de morsure** : le jeu fautif `fragment-sans-marqueur` existe deja et porte exactement cette
> faute - un ecran insere qui oublie le marqueur. Il n'y a aucune fixture a inventer, seulement a la
> juger par la clause neuve.

**Cout : un test.** Le compte passe donc de **505 a 506** ; c'est la seule consequence de mon verdict
sur les chiffres de `marc-dev`. **Je ne l'ecris pas a sa place** ; l'enonce ci-dessus est assez precis
pour qu'il n'y ait rien a redecouvrir, et `sofia-sm` le lui route.

**Ce qui n'est pas exige** : garder `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` en production. Ses deux
jeux fautifs suffisent a maintenir le mecanisme exact et eprouve, et c'est la doctrine que cet `ADR`
s'est donnee a lui-meme en s06. Le sort d'`ADR-0022` est tranche dans son second amendement du
2026-08-17 : **amendement de constat**, ni `ADR` neuf, ni changement de statut - clause par clause, avec
ce qui le rouvrirait.

### 6.5 Geste 4 - la preuve historique rendue autonome par un fixture : **ACCORD**

La question posee est la bonne : une preuve qui ne lit plus la production prouve-t-elle encore quelque
chose ? Ici, oui, et pour une raison qui distingue ce cas du defaut que j'ai nomme ce sprint.

- **L'objet de cette epreuve est un algorithme, pas la production.** Elle etablit que
  `arretsDeTabulation` plus la clause 1 rougissent sur le document que le serveur rendait avant le lot
  de s11 - un document **deja fige** depuis ce jour-la, que le serveur ne produit plus. Son materiau
  n'a jamais ete l'arbre courant.
- **Ce que le lot corrige est une incoherence, pas une perte.** Un document fige juge contre une
  feuille vivante est un attelage batard : la feuille evolue pour des motifs etrangers a la regression
  nommee, et la preuve rougit alors pour un defaut qui n'est pas le sien. C'est exactement ce qui
  vient de se produire - c'est le cinquieme echec que `marc-dev` a rencontre. Figer les deux entrees
  rend l'epreuve conforme a ce qu'elle annonce.
- **Le defaut que j'ai nomme ce sprint ne s'applique pas ici**, et il faut dire pourquoi plutot que
  l'invoquer : *"une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine"* vise un
  garde-fou dont l'**objet** est la production et qui ne la rencontre jamais. Or la famille `ADR-0038`
  garde ses preuves sur materiau reel : `unPointDeFocusDeplaceSurUnArretDestructeurFaitEchouerLaClause3`
  et `unEcranDontLePointDeFocusEstRetireFaitEchouerLaClause4` mutent des documents que le serveur rend
  vraiment, et leurs clauses de non-vacuite refusent de passer si le corpus n'en porte plus. La famille
  n'est donc pas laissee aux seules fixtures : celle-ci est la seule figee, et elle l'est parce que son
  sujet est date.
- **Le contenu du fixture est la forme exacte que la production portait** - la regle que cet `ADR`
  declarait, mot pour mot - et non une regle reecrite pour la circonstance. Verifie.

**Ce que j'ajoute, sans l'exiger** : le jour ou une regle de masquage revient en production, cette
epreuve pourra redevenir couplee a la feuille servie, et ce sera un gain. Le declencheur est ecrit dans
le second amendement d'`ADR-0022`.

### 6.6 La regle CSS vide `.ecran-de-jeu {}` : **ACCORD pour ce lot**, et la question de fond est tranchee contre elle

Le precedent de `.message-jeu` est reel, verifie dans la feuille servie : une regle vide, commentee, qui
nomme une classe pour `HabillageDesEcransTest` sans lui inventer une apparence qu'aucune story ne doit.
Le geste est donc coherent avec le depot, et il vaut mieux qu'une exemption : il garde
`aucuneClassePoseeSurUnElementNEstOrpheline` honnete plutot que de lui ajouter une liste.

**Mais la question de fond est desormais tranchee contre la classe, et par le depot lui-meme.**
`ADR-0027` ecrit noir sur blanc, a propos de son propre marqueur : *"Un attribut et non une classe, pour
la meme raison qu'ADR-0017 a choisi `data-role` : ce qu'il declare n'est pas un habillage, et une
classe aurait exige une regle de style qui ne style rien."* `ecran-de-jeu` etait une classe pour une
seule raison - le selecteur `body:has(.ecran-de-jeu)` en avait besoin. **Cette raison vient de
disparaitre.** Ce qui reste est exactement ce qu'`ADR-0027` refusait : une classe d'etat, et une regle
de style qui ne style rien pour la justifier.

**Je ne l'exige pas dans ce lot, et je dis pourquoi** : la migration vers un attribut touche quatre
gabarits de production, deux classes de test et une quinzaine de jeux fautifs sous
`src/test/resources/contreexemple/`, dont chaque preuve de morsure devrait etre reverifiee. Un second
cascadage dans le meme lot, pour un gain de forme, contre le principe de la plus petite piece mobile.
**Candidat de story, cout de l'ordre d'un petit lot**, et son declencheur est deja survenu - c'est
`sofia-sm` qui l'affecte ou non. Ce qui me ferait l'exiger plus tot : un second marqueur d'etat pose en
classe, qui ferait de l'exception une habitude.

### 6.7 Ce que j'exige de `marc-dev`, en clair et sans code

1. **Bloquant pour la cloture** : la clause de la section 6.4 - tout gabarit insere dans la zone porte
   le marqueur -, dans `LEcranDeJeuDeclareCommentIlEstArriveTest`, eprouvee sur le jeu fautif
   `fragment-sans-marqueur` qui existe deja. **+1 test : 506.**
2. **Non bloquant, a corriger dans le meme lot** : la forme du test neuf de la section 6.3 - l'ensemble
   des actionnables de l'en-tete egale l'ensemble de ses `[data-role=entree]`, sans libelle ni
   catalogue. Aucun changement de compte.

Rien d'autre. Les gestes 1, 4 et la regle vide sont acceptes tels quels, et le geste 2 est accepte dans
son principe.

### 6.8 Le doublement de perimetre - ce que cela dit de ma facon de deriver les impacts

**Oui, cela dit quelque chose, et la reponse n'est pas que les quatre etaient indevinables.** Geste par
geste, ce qui etait derivable avant execution :

| Garde-fou touche | Derivable en lisant ? | Comment |
|---|---|---|
| geste 1 | **oui, en un grep** | le test contient le litteral `.btn.lien-accueil` dans son assertion |
| geste 2 | **oui**, et `DEC-0033` le nommait deja | idem, litteral |
| geste 3 | **oui, en deux temps** | le test exige un masquage non vide, et le seul masquage servi nomme `.lien-accueil` ; la classe refuse elle-meme un masquage decoratif |
| geste 4 | **oui en principe, manque en pratique** | le couplage n'est visible qu'en lisant le **corps** de la methode : un document fige juge avec `masquages(RACINE_PRODUCTION)`. Ni son nom ni `ADR-0038` ne le laissent deviner |
| le cinquieme, conditionnel (`ADR-0031`) | **non par les seuls garde-fous** | il fallait savoir qu'une co-occurrence impossible est refusee, ce que j'avais analyse - pour l'**ajout** de la barre, ou j'avais conclu, a juste titre, qu'`ADR-0031` resterait vert |

**Le defaut de methode, nomme** : j'ai derive le perimetre du **geste du jour** - ajouter la barre - et
non celui de son inverse. Mon avis de faisabilite de `S14-1` porte une mesure du perimetre de l'ajout,
faite en posant les puces et en executant le module ; il ne porte aucune mesure du perimetre d'un
retrait, alors que `BARRE-7` etait deja une reserve routee. **Le perimetre d'un retrait n'est pas celui
d'un ajout, et je n'en avais mesure qu'un.**

**Le correctif, concret et cheap, que je m'applique** : tout avis de faisabilite qui concerne le
**retrait** d'un element de production porte, avec son resultat, le releve des sources de test qui
nomment cet element en litteral - une commande, publiee dans l'avis comme une mesure de rendu porte la
sienne. Cela aurait attrape les gestes 1, 2 et la moitie du 3. Pour le reste - un garde-fou dont la
*derivation* lit une source dont l'element est la derniere instance - il n'y a pas de commande, et je
ne pretends pas le contraire : c'est une lecture de corps de methode, et c'est la que geste 4 m'a
echappe.

**Ce que le doublement ne dit pas, et qu'il ne faut pas lui faire dire** : que les garde-fous ont
failli. Les cinq echecs sont **cinq rouges au bon moment**, pas un vert silencieux. Chacun a arrete le
lot exactement la ou une propriete perdait son objet, y compris le cinquieme, conditionnel, qui a
oppose deux garde-fous a une regle morte que personne n'aurait relue. C'est le cout que `ADR-0038`
avait accepte d'avance - *"un ecran neuf parfaitement correct fera rougir la regle [...] elle coutera un
ajout de cas"*. **Une derivation d'impact ratee a coute une surprise a l'execution ; elle n'a coute
aucun defaut.** Et le seul trou reel de ce lot - la clause 1 orpheline de la section 6.4 - n'a pas ete
produit par un garde-fou muet : il a ete produit par le **retrait** d'un garde-fou, ce qui est
precisement le geste sur lequel un accord ecrit est exige.

## 7. Le masquage retrouve des sujets - arbitrage apres le refus de `nadia-prof`

- **Date** : 2026-08-17 (soir). **Auteur** : `theo-archi`. **Arbre lu** : `round-two` a `911af6a`.
- **Declencheur** : le refus du Done pedagogique. `nadia-prof` mesure par capture **quatre** boites
  pressables sur l'ecran de confirmation au lieu des deux de BARRE-1, et **trois** sur l'ecran d'erreur
  au lieu d'une. C'est exactement le declencheur que le second amendement d'`ADR-0022` avait ecrit
  quelques heures plus tot, et il s'est produit le jour meme.
- **Ce que cette section n'est pas** : un jugement sur son verdict, que je ne rediscute pas, ni un Done
  pedagogique.

### 7.0 Deux corrections a mon compte, avant tout le reste

**Le compte autoritaire est 507, et `sofia-sm` a raison contre mon 506.** `ADR-0013` obligation 3 exige
que le jeu fautif soit **execute** : j'avais compte le fixture, qui preexistait et etait donc gratuit,
et pas la methode de test qui l'execute. Les deux methodes livrees sont
`chaqueGabaritInsereDansLaZonePorteLeMarqueurDEcranDeJeu` et
`unFragmentInsereQuiOublieLeMarqueurFaitEchouerLeGardeFou` : **505 + 2 = 507**, verifie par lecture des
deux `@Test`. L'erreur est d'autant plus mienne que ma propre doctrine dit qu'un jeu fautif que rien
n'execute ne prouve rien - c'est la raison d'etre de cette seconde methode, et je l'ai oubliee en
comptant.

**Ce qui a ete correctement diagnostique par `marc-dev`, et que je note parce que la nuance compte** :
son "tenu par construction" etait vrai du **fragment** et sans effet sur ce que l'enfant voit. L'en-tete
n'est pas reinjecte dans la confirmation, effectivement - et c'est precisement pour cela qu'il **survit**
(`DEC-0012`). Raisonner sur ce que le serveur rend, quand la propriete porte sur le document assemble,
est le defaut qu'`ADR-0020` a nomme et qu'`ADR-0022` a paye une premiere fois en s06.

### 7.1 La forme du masquage que je retiens

**La forme contractuelle s'applique, caractere pour caractere. Le marqueur, non.**

`body:has(.<marqueur>) .<habillage> { display: none }` reste le seul mecanisme disponible, et pour les
memes raisons qu'en s06, verifiees et non reprises : le serveur ne rend que `#jeu`, l'en-tete en est le
frere, donc un `th:if` ne vaudrait que pour un chargement complet et laisserait l'en-tete perime apres
chaque echange ; `hx-swap-oob` declenche la dette explicite d'`ADR-0020` ; deplacer les puces dans la
zone detruirait `DEC-0012`. Rien de ce raisonnement n'a bouge.

**Ce qui differe du cas de s06, et qui interdit de reutiliser `ecran-de-jeu`** : en s06, un habillage
disparaissait sur **la famille entiere** des ecrans de jeu. Ici les puces doivent disparaitre sur **deux
ecrans sur quatre** - la confirmation et l'erreur - et **rester** sur la manche (tout l'objet de
`S14-1`) comme sur la fin de partie. Or les quatre fragments portent `ecran-de-jeu`. Le reutiliser
entrainerait la fin de partie et la manche par erreur, et la forme contractuelle n'a pas de negation :
il n'existe aucun moyen d'ecrire "sauf celui-la" sans sortir du contrat que le garde-fou sait lire.

**Donc : un marqueur neuf, pose par le serveur sur les seuls ecrans concernes.** Je propose
`ecran-exclusif`, et je definis ce qu'il declare plutot que ce qu'il produit : **un ecran est exclusif
quand tout chemin qu'il ouvre a l'enfant vit a l'interieur de lui.** La confirmation (continuer ou
quitter) et l'erreur (recommencer) sont dans ce cas ; la manche et la fin de partie ne le sont pas, par
l'arbitrage de `nadia-prof` sur `S14-1`. **Le nom est du balisage, donc le mandat d'`ines-design`** : il
vit dans une constante de garde-fou et dans deux attributs de classe, comme `data-role` en s05, et un
autre nom ne coute rien s'il le sert mieux. Ce qui n'est pas negociable est qu'il nomme **un etat de
l'ecran** et non l'effet attendu sur l'en-tete : `ecran-sans-puces` serait une regle de presentation
deguisee en marqueur, et le prochain habillage a masquer le rendrait faux.

**Le point qui doit etre ecrit noir sur blanc, parce que je l'avais nomme comme piege avant qu'il ne
soit atteignable** (avis de faisabilite, section 2.2, point 2) : **le masquage doit nommer la classe
portee par l'element focalisable, `.chip-niveau`, jamais celle du conteneur `.bandeau-niveaux`.**
`AucunArretDeTabulationNEstDestructeurTest` evalue le masquage **element par element**
(`candidat.hasClass(habillage)`) : une regle nommant le `<nav>` laisserait les deux puces dans l'ordre
de tabulation, donc calculerait un `A[0]` faux **en restant verte**, et `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`
l'accepterait puisque la classe est bien posee. Verifie sur l'arbre : les puces sont des
`button.btn.chip-niveau`, l'element focalisable porte donc bien la classe a nommer. Le piege est
evitable a cout nul et il ne se verrait pas s'il etait commis.

**Ce que le masquage change ailleurs, verifie et non suppose** : sur la confirmation, `A[0]` redevient
`continuer-partie` (non destructeur, clause 1 verte) ; l'ecran d'erreur ne porte aucune partie en cours,
donc reste hors du perimetre des clauses d'`ADR-0038` ; et le role `entree` reste confronte a des
actions sur l'accueil (`.demarrer`) et sur la manche (`.proposition`), donc
`rolesDominesJamaisConfrontes` (`ADR-0043`) reste vert et non vacuant.

**`.ecran-de-jeu` : ma recommandation de migration vers un attribut ne change pas de statut, elle reste
differee.** Le selecteur `body:has()` revient, mais **sur le marqueur neuf**. `ecran-de-jeu` n'est
toujours nomme par aucun selecteur qui s'en serve pour styler quoi que ce soit ; son seul consommateur
reste `LEcranDeJeuDeclareCommentIlEstArriveTest`, et il ne lui reste qu'une regle vide dans la feuille.
La raison qui en faisait une classe n'est pas revenue. L'argument sort meme renforce : deux marqueurs de
**nature differente** coexistent desormais, l'un que la feuille selectionne - une classe legitime -
l'autre purement declaratif - un attribut selon la doctrine qu'`ADR-0027` a ecrite pour lui-meme. Les
avoir confondus est ce qui a fait croire que `ecran-de-jeu` pouvait porter les deux proprietes.

### 7.2 Ce que `marc-dev` doit livrer - trois clauses, aucune ecrite a sa place

Toutes dans `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`, la classe qui porte deja le mecanisme, ses
helpers partages et ses deux jeux fautifs.

**Clause A - coherence du couple, dans les deux sens, et verte sur une population vide.**

> Pour **chaque** masquage declare par la feuille servie : son marqueur est pose par **au moins un**
> gabarit insere dans la zone echangee, et la classe qu'il masque est posee par **au moins un** element
> **hors** de cette zone - c'est-a-dire que ce qu'il masque est bien un habillage permanent.
> **Aucune assertion n'exige qu'un masquage existe.** Zero masquage declare : la clause passe, il n'y a
> rien a verifier. C'est le changement de forme du troisieme amendement d'`ADR-0022`, et c'est ce qui
> l'empechera d'osciller au prochain arbitrage.

Ce qu'elle interdit reellement, et ce n'est pas rien : un masquage dont le marqueur n'est plus pose par
personne (la regle morte, que `S14-2` a rencontree), un masquage qui viserait un element de la zone
echangee au lieu d'un habillage permanent (il disparaitrait avec l'echange, la regle ne servirait a
rien), et le renommage d'un seul des deux termes. **Elle ne reprend pas** l'exigence "tout ecran insere
porte le marqueur" : elle serait fausse ici, deux ecrans sur quatre le portant a dessein.

**Clause B - la propriete que `nadia-prof` vient de mesurer, sous sa forme declarative et derivee.**

> Sur **tout** ecran qui porte le marqueur d'exclusivite, **aucun** element actionnable permanent ne
> survit aux masquages declares. Population derivee des gabarits qui portent le marqueur, jamais d'une
> liste de noms d'ecrans ; "actionnable" est le meme critere que partout ailleurs dans le module
> (`button, a[href]`) ; "permanent" est "hors de la zone echangee", la derivation d'`ADR-0022` deja
> partagee. **Non vacuite exigee** : au moins un ecran porte le marqueur, sans quoi cette clause ne
> juge rien.

C'est la clause qui remplace le comptage de boites de `nadia-prof` **sans jamais ecrire un nombre** :
deux et un ne sont pas des proprietes, ce sont les contenus du jour de deux ecrans. Ce qu'elle interdit
est la faute exacte du refus - un habillage permanent qui survit la ou l'arbitrage veut le silence - et
elle l'interdit pour le **prochain** habillage permanent autant que pour celui-ci.

**Clause C - l'inverse : aucune, et je dis pourquoi plutot que d'en ecrire une de plus.**

La question posee est de savoir si un selecteur de masquage peut viser un ecran qui n'existe pas.
**Elle est deja fermee, et par un garde-fou qui l'a deja prouve sur cet arbre** :
`UnEcranNeGouvernePasLApparenceDUnAutreTest` (`ADR-0031`) refuse *"un jeton qu'aucun ecran ne peut
poser"*, et c'est lui qui, avec celui d'`ADR-0022`, a refuse la regle morte pendant `S14-2` - deux
garde-fous independants s'accordant, ce qui est ce qui a rendu ce retrait sur. La clause A ferme le meme
cas par l'autre bout. **Ecrire une troisieme clause pour la meme propriete serait decoratif**, et je ne
la demande pas.

**Preuves de morsure.** `fragment-sans-marqueur` et `feuille-sans-masquage` existent et exercent le
mecanisme ; ils ne suffisent pas pour la clause B, dont l'objet est neuf. Ce que je demande, et rien de
plus : **une mutation sur du materiau reel plutot qu'une fixture de sa main** - le marqueur retire de
`fragments/confirmation.html`, ou le masquage nommant `.bandeau-niveaux` au lieu de `.chip-niveau`.
La seconde est la plus utile des deux : c'est le piege de la section 7.1, elle prouve la clause B et
elle documente le piege par un rouge plutot que par un commentaire.

**Effet sur le compte** : trois clauses dont deux methodes de propriete plus au moins une de morsure.
Je ne fixe pas le chiffre - la decoupe en methodes est la sienne - mais **je refuse par avance un compte
qui n'augmenterait pas** : une clause de propriete sans sa preuve de morsure est ce que `ADR-0013`
obligation 3 interdit, et c'est l'erreur que je viens de faire en comptant 506.

### 7.3 Le sort d'`ADR-0022` et l'oscillation de sa clause

**Ce n'est pas un troisieme amendement de constat. C'est un changement de forme, et l'oscillation est
le symptome qui l'a rendu lisible.** Le troisieme amendement est ecrit dans l'`ADR`.

**Le diagnostic, et il n'est pas celui que la question suggere.** Ce n'est pas la clause 2 qui oscillait :
c'est sa **population**, et une population qui suit le produit est normale - ce serait l'inverse qui
serait pathologique, un garde-fou vert pendant que le produit change sous lui. Le vrai defaut est
ailleurs, et il est precis : **le garde-fou exigeait que son propre mecanisme soit employe**
(`masquages(...).isNotEmpty()`). C'est cette assertion qui a rendu le test rouge quand `S14-2` a retire
le dernier masquage ; c'est son retrait force qui a emporte la clause du marqueur ; c'est ce retrait qui
a laisse la clause 1 orpheline et m'a oblige a exiger sa re-portee. **Une seule assertion mal placee a
produit toute la cascade de ce sprint.**

Le rapprochement avec s13 est juste, mais pas sur le point qu'on pourrait croire. En s13 j'avais
decouple un plafond de dette de la **ceremonie** qui le publiait : *"une clause dont l'execution depend
d'une ceremonie n'est pas une clause"*. Ici rien ne depend d'une ceremonie - `mvn verify` execute cette
clause a chaque fois. Ce qui dependait de l'extérieur etait son **objet** : la clause exigeait un etat du
produit que seul un arbitrage pedagogique decide. La forme generalisee corrige exactement cela :

- **avant** : une reversion de `nadia-prof` coutait un test retire ou remis, plus un amendement d'`ADR` ;
- **apres** : elle coute **un attribut de classe dans un gabarit**, et eventuellement une regle CSS.
  Zero test touche, zero `ADR` amende. L'arbitrage vit dans la donnee, ou il doit vivre.

C'est le test que je fais desormais passer a toute clause dont l'objet depend d'un jugement humain :
**si l'humain change d'avis, combien de fichiers de garde-fou bougent ?** La bonne reponse est zero.

### 7.4 La dependance de mesure de rendu - ce que le depot peut faire, et ce qu'il ne peut pas

`nadia-prof` nomme un risque reel : trois agents ont echoue sur `outils/mesurer-rendu.sh` faute de
navigateur designe, donc **aucun chiffre de rendu n'entre dans ce depot sans passer par elle**. Deux
choses sont melangees dans cette phrase et il faut les separer, parce que l'une est voulue et l'autre
non.

- **Qu'elle soit la seule autorite sur ce qu'un rendu vaut est voulu**, et aucun outil ne doit y toucher :
  `ADR-0014`, `ADR-0017`, `ADR-0022`, `ADR-0025` et `ADR-0028` lui confient tous nommement les
  proprietes de pixels, et ce refus a ete assume avec ses motifs.
- **Qu'elle soit la seule a pouvoir produire un chiffre n'est pas voulu** : c'est un facteur de bus sur
  la **mesure**, pas sur le jugement. C'est cette moitie-la que le depot peut reduire.

**Ce que le depot peut faire, et le cout est petit.** `outils/mesurer-rendu.sh` echoue aujourd'hui parce
qu'une variable d'environnement n'est pas positionnee, et son message ne dit pas assez pour qu'un agent
se depanne seul - trois l'ont demontre, ce qui est la meilleure mesure possible de la lisibilite d'un
message d'erreur. Deux gestes, dans l'esprit d'`ADR-0009` (un garde-fou de script est un script teste) :

1. **le script resout le navigateur lui-meme** parmi une liste de candidats declares - la variable
   d'environnement d'abord, puis les commandes usuelles - et n'echoue qu'apres les avoir tous essayes ;
2. **son message d'echec nomme la marche a suivre**, sur le modele de ce que
   `maven-toolchains-plugin` fait pour le JDK 21 : ce qui manque, ou le declarer, et sous quelle forme
   portable. Le juge d'invocation du script (`outils/mesurer-rendu-test.sh`, 23 cas) recoit les cas
   correspondants, dont le cas negatif "aucun candidat trouve".

**Cout : une poignee de lignes plus ses cas, un tres petit lot.** Aucun `ADR` : `ADR-0025` a deja decide
que la mesure de rendu vit hors de la CI dans une commande versionnee, et ceci ne fait que rendre cette
commande executable par quelqu'un qui ne l'a jamais lancee.

**Ce que le depot ne peut pas faire, dit explicitement.** Il ne peut pas garantir qu'un navigateur existe
sur un poste, ni faire entrer un rendu dans `mvn verify` - `ADR-0025` l'a ecarte sur trois motifs qui
tiennent tous (une dependance a un binaire que la CI ne garantit pas, un test qui se met en veille quand
le binaire manque est un vert qui ne prouve rien, et une assertion en pixels sur du texte rendu depend
des polices installees). **Le declencheur de reouverture est le meme qu'`ADR-0025` avait ecrit et il
n'est pas survenu** : un conteneur de build avec ses polices epinglees. Tant qu'il ne l'est pas, le
depot peut rendre la mesure **reproductible par n'importe qui sur un poste equipe** ; il ne peut pas la
rendre independante d'un poste equipe, et pretendre le contraire serait exactement le garde-fou en
veille qu'`ADR-0025` refuse.

### 7.5 Ce que cette section ne fait pas

Elle ne prononce aucun Done pedagogique et n'en anticipe aucun ; le verdict de `nadia-prof` n'est pas
rediscute, seulement encode. Elle ne juge pas ses deux corrections de sa main (BARRE-3 inapplicable, la
prevision de cout pessimiste de 53 px) : ce sont ses mesures et son perimetre. Elle ne repriorise rien et
n'affecte rien - les deux candidats de story qu'elle nomme (la migration du marqueur vers un attribut, la
resolution de navigateur) sont a `sofia-sm`.
