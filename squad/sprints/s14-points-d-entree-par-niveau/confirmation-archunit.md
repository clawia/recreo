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
