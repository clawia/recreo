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
