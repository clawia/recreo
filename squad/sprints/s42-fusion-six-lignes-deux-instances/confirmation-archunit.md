# Confirmation ArchUnit - s42 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-24.
- **Objet** : confirmer, sur l'arbre final de `round-two` une fois les **cinq** lots de s42
  reellement fusionnes, ce que mon avis de faisabilite du meme jour concluait sur un arbre qui ne
  les portait pas encore (`517abd9d`).
- **Perimetre mesure** : `round-two` a **`cdf0229f`**, worktree dedie `s42-theo-confirmation`
  branche depuis ce commit (amendement s18 et s21 : une branche dediee nommee pour un agent
  implique un worktree dedie du meme nom ; toute mesure du reacteur depuis son propre worktree).
  **Remesure apres fusion de `round-two` a `cb07884f`** dans ce meme worktree : le diff apporte est
  du `squad/` seul - rapport qualite de `yanis-qa` et six fiches de conformite de `camille-po` -,
  aucun fichier de code ni de contenu, donc tous les verdicts de ce fichier valent sur les deux
  arbres. Les chiffres cites sont ceux du premier lancement ; la relance est en section 1.1.
- **Version** : **JDK 21.0.10**, telle que le JDK resolu par l'enveloppe la rapporte, jamais telle
  qu'une entree de toolchain la declare (`ADR-0071`). Premiere ligne de l'enveloppe :
  *"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*. Maven 3.9.9.
- **Nom du fichier** : meme nom qu'a s41 et qu'aux treize sprints precedents,
  `confirmation-archunit.md`. Un avis de faisabilite n'est pas une des quatre ceremonies de
  `squad/README.md` section 4, donc la regle d'ajout date ne s'y applique pas ; aucune regle de
  nommage neuve n'est inventee ici.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Les tests ArchUnit restent-ils verts sur le code neuf des cinq lots ? | **oui**, et la reponse exacte est la meme qu'a s41 : **les cinq lots ne portent aucune ligne de Java de production** (section 2). `mvn -B clean verify` sur la racine : **`BUILD SUCCESS`**, 6 modules sur 6, **1727 tests, 0 echec** |
| 2. *"Aucun `ADR` neuf, aucune regle ArchUnit neuve"* tient-il une fois les cinq lots integres ? | **oui**. Compte du depot **inchange** : **36 `static final ArchRule`, 35 `@ArchTest`**, meme methode et meme chiffre qu'a s41 et qu'a mon avis. Aucun `ADR` ecrit, `ADR-index.md` intouche par s42, aucun cliquet deplace (section 3) |
| 3. `HIS-1` sur les items neufs de `S42-HG-ROIS` respecte-t-il le plafond du lot HG CM1 entier ? | **oui**, et le calcul est refait ici de bout en bout plutot que repris : **115 items, 23 restitutions, plafond 38, marge 15** - chiffres confirmes, mais **le plafond a bouger de 36 a 38 sous l'effet du lot lui-meme**, et **la marge reellement disponible avant que le garde-fou rougisse est 9, pas 15** (section 4) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique, ne ferme
aucune ligne de matrice, ne juge aucun contenu. La porte pedagogique est a `nadia-prof` seule.

**Ce que cette confirmation ne couvre pas, et je le nomme plutot que de le deduire** (`CLAUDE.md`,
amendement du 2026-09-08) : `mvn verify` **n'est que le contenu du job `build`** de
`.github/workflows/ci.yml`, qui en porte quatre. Les trois autres sont lances a part et rapportes
en section 7. **Je n'annonce pas la porte technique du sprint** - c'est `sofia-sm` qui la prononce
en `review.md`, et `yanis-qa` qui rapporte la qualite.

## 1. La commande, sa sortie, ses comptes

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur **complet depuis la racine** - la question du `-am` ne se pose donc pas ici, elle ne
concerne que les mesures partielles (`CLAUDE.md`, prerequis de build). Les cinq mesures partielles
de la section 4 portent toutes `-am`.

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  0.890 s]
[INFO] recreo-domain ...................................... SUCCESS [ 14.360 s]
[INFO] recreo-application ................................. SUCCESS [  4.665 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:04 min]
[INFO] recreo-adapter-content ............................. SUCCESS [ 52.517 s]
[INFO] recreo-bootstrap ................................... SUCCESS [03:03 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  05:21 min
[INFO] Finished at: 2026-09-24T18:49:01+02:00
```

**Comptes, avec leur methode dans le meme paragraphe.** Methode autoritaire de `squad/README.md`,
section 9 (amendement s11) : somme des blocs `[INFO] Results:` du reacteur apres `clean` complet,
**jamais** la somme des lignes `Tests run:` des rapports `.txt`. Perimetre : les six modules du
reacteur, 0 echec, 0 erreur, 0 ignore sur les six.

| Module | Tests | Ecart avec s41 |
|---|---|---|
| `domain` | 120 | 0 |
| `application` | 17 | 0 |
| `adapter-web` | 400 | 0 |
| `adapter-content` | 907 | **+26** |
| `bootstrap` (surefire) | 277 | 0 |
| `bootstrap` (failsafe) | 6 | 0 |
| **total** | **1727** | **+26** |

**Recoupe par deux autres methodes independantes, parce qu'un chiffre de test est exactement ce
que ce depot m'a vu rater a s41** :

1. somme des attributs `tests="..."` des **235** rapports XML de surefire et failsafe : **1727** ;
2. somme des blocs `Tests run: ... -- in <classe>` du **journal** (et non des `.txt`), agregee par
   classe : **1727** sur **235** classes.

Les trois methodes concordent. **Le piege de s41 est reverifie et il est toujours la** : le
journal expose **cinq** classes en deux blocs, celles qui melangent un moteur `archunit-junit5` et
le moteur Jupiter - `AbstractionsCiteesTest`, `LOrdreDesNiveauxResteUneDonneeTest`,
`ChargementDuContenuTest`, `LesReglesMordentTest`, `AssertionsWebTest`. Ce sont exactement les cinq
que la correction de s41 avait nommees, retrouvees ici **par le calcul** (comptage des blocs par
classe) et non recopiees de ce fichier-la.

**D'ou viennent les +26**, verifies un a un : quatre classes de paquet neuves
(`PaquetCm1EmcSymbolesTest` 6, `PaquetCpArtsCouleursTest` 7, `PaquetCpDeplacementsQuadrillageTest`
6, `PaquetCpQlmMatiereTest` 5, soit 24) et **deux methodes ajoutees** a
`PaquetCm1HistoireGeographieTest` (27 a 29). 24 + 2 = 26. Les trois inventaires partages -
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (23),
`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (9),
`TexteAccentueExposeParLePortEntrantTest` (5) - ont ete **modifies sans changer de compte**, ce qui
est la forme attendue d'un inventaire a egalite exacte.

### 1.1 La relance apres fusion de `round-two` a `cb07884f`

Meme commande, meme worktree, sur l'arbre qui porte en plus le rapport qualite de `yanis-qa` et les
six fiches de conformite de `camille-po` :

```
[INFO] recreo-domain ...................................... SUCCESS [ 11.644 s]
[INFO] recreo-application ................................. SUCCESS [  4.124 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 51.116 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 35.316 s]
[INFO] recreo-bootstrap ................................... SUCCESS [02:13 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  03:56 min
```

Blocs `[INFO] Results:` : **120 / 17 / 400 / 907 / 277 / 6**, soit **1727**, 0 echec - **identiques
a ceux du premier lancement**, ce qui est le resultat attendu d'un diff de `squad/` seul, et ce que
je verifie plutot que de le supposer.

**Et un recoupement que je n'avais pas a s41** : `yanis-qa` annonce **1727** dans
`rapport-qualite.md`, section 1, sur `s42-yanis` a `cdf0229f`, avec la meme discipline de methode
(*"jamais la somme des lignes `Tests run:` des fichiers `.txt`"*). Deux agents, deux worktrees,
cinq methodes au total, un seul chiffre.

## 2. Question 1 - les tests ArchUnit sur le code neuf des cinq lots

### 2.1 Le fait qui rend la question plus simple qu'elle n'en a l'air

Diff complet de `9fed12bc` (mon avis) a `cdf0229f` (arbre final), hors `squad/` et `docs/` :

```
 .../src/main/resources/catalogue.yaml              |  24 +++
 .../main/resources/contenu/cm1-emc-symboles.yaml   | 134 +++++++++++++
 ...cm1-histoire-geographie-nommer-en-histoire.yaml |  26 ++-
 ...1-histoire-geographie-situer-dans-le-temps.yaml |  20 ++-
 .../main/resources/contenu/cp-arts-couleurs.yaml   | 101 ++++++++++
 .../contenu/cp-deplacements-quadrillage.yaml       |  86 ++++++++++
 .../src/main/resources/contenu/cp-qlm-matiere.yaml |  74 +++++++++
 ...neReponseNEstPasPredictibleSurUnPaquetTest.java |  55 +++++--
 .../adapter/content/PaquetCm1EmcSymbolesTest.java  | 182 +++++++++++++++++++++
 .../content/PaquetCm1HistoireGeographieTest.java   |  50 +++++-
 .../adapter/content/PaquetCpArtsCouleursTest.java  | 169 +++++++++++++++++++
 .../PaquetCpDeplacementsQuadrillageTest.java       | 165 +++++++++++++++++++
 .../adapter/content/PaquetCpQlmMatiereTest.java    | 171 +++++++++++++++++++
 ...mpteSurLeLotDeMatiereEtNonSurUnFichierTest.java |  17 +-
 .../TexteAccentueExposeParLePortEntrantTest.java   |  66 +++++++-
 15 files changed, 1312 insertions(+), 28 deletions(-)
```

**Zero ligne de Java de production sur les cinq lots.** Consequence directe et non triviale, la
meme qu'a s41 : les regles ArchUnit **proprement dites** - couches, purete, decouplage
mecanique/contenu, scan de composants - n'ont **aucun objet neuf a mordre**, parce qu'elles ne
modelisent que des classes et leurs dependances, et qu'aucune classe de production n'a bouge. Elles
sont vertes, et il faut savoir qu'elles sont vertes **par absence de matiere neuve**, pas parce
qu'elles auraient valide quoi que ce soit du contenu.

**Ce qui mord reellement les cinq lots** est ailleurs : les garde-fous de contenu de
`bootstrap/src/test/java/com/recreo/archi` et d'`adapter-content`, qui sont des tests JUnit
ordinaires lisant les paquets servis, **pas des `ArchRule`**. Confondre les deux serait annoncer
une garantie qu'ArchUnit ne donne pas.

### 2.2 Les 29 classes de `com.recreo.archi`, une par une

Toutes vertes, **198 tests, 0 echec, 0 erreur, 0 ignore** (blocs du journal agreges par classe,
methode de la section 1).

| Classe | Tests | Ce qu'elle a vu de neuf sur les cinq lots |
|---|---|---|
| `AbstractionsCiteesTest` | 5 | rien - **classe mixte** |
| `AccesALEtatDeSessionTest` | 2 | rien |
| `ArchitectureEnCouchesTest` | 2 | rien - **ArchUnit pur**, aucune classe neuve |
| `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` | 5 | **4 paquets neufs et 2 etendus** : aucune cle de schema neuve, section 3.2 |
| `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` | 5 | rien - aucun des cinq ne porte `tableauSupport` |
| `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` | 7 | **3 paquets de CP neufs**, bornes a zero enonce trop long des leur premier commit, section 4.5 |
| `AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest` | 8 | rien |
| `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` | 6 | rien - les cinq sont du bandit manchot a choix textuel |
| `AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest` | 1 | rien |
| `ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest` | 6 | 4 entrees de catalogue neuves |
| `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` | 12 | **5 lignes de referentiel neuves declarees**, section 3.3 |
| `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | 18 | rien - cliquet a plafond zero intact |
| `ChargementDuContenuTest` | 2 | rien - **classe mixte** |
| `ConcordanceDesTagsDAgentTest` | 1 | rien |
| `DecouplageMecaniqueContenuTest` | 12 | rien - **ArchUnit pur** |
| `DifficulteTest` | 3 | rien |
| `GestionDesErreursTest` | 4 | rien |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | 9 | **53 items neufs** sur le depot entier |
| `LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest` | 4 | rien |
| `LeChoixDesJeuxResteDerriereLePortTest` | 1 | rien |
| `LesGardeFousSontMisALEpreuveTest` | 10 | rien |
| `LesReglesMordentTest` | 37 | rien - **aucune morsure ajoutee ni retiree**, le fichier n'est pas au diff. **Classe mixte** |
| `LIllustrationDeContenuResoutEtAucuneNeDortTest` | 15 | rien - aucun des cinq ne porte d'illustration |
| `LOrdreDesNiveauxResteUneDonneeTest` | 5 | rien - **classe mixte** |
| `PureteDesCouchesTest` | 2 | rien - **ArchUnit pur** |
| `ScanDeComposantsTest` | 2 | rien |
| `ToutesLesReferencesDeDecisionResolventTest` | 3 | les references des commentaires d'entete des cinq lots |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 7 | les identifiants cites par ces memes commentaires, section 3.4 |
| `UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest` | 4 | 4 paquets de 11 a 13 items, sous les quatre bornes |

`adapter-web` porte la seule `ArchRule` hors de `bootstrap` :
`com.recreo.adapter.web.archi.AssertionsWebTest`, **2 tests, verts** - **classe mixte**.

### 2.3 Les cinq tests de paquet et les trois inventaires partages

| Classe | Tests | Verdict |
|---|---|---|
| `PaquetCm1EmcSymbolesTest` | 6 | vert |
| `PaquetCm1HistoireGeographieTest` | 29 | vert (27 a s41, +2) |
| `PaquetCpArtsCouleursTest` | 7 | vert |
| `PaquetCpDeplacementsQuadrillageTest` | 6 | vert |
| `PaquetCpQlmMatiereTest` | 5 | vert |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (partage) | 23 | vert |
| `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (partage) | 9 | vert |
| `TexteAccentueExposeParLePortEntrantTest` (partage) | 5 | vert |

### 2.4 La fusion que mon avis annoncait comme la plus delicate a tenu

Section 9.1 de l'avis : *"son entree dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` existe deja et porte
des comptes mesures : le lot doit la modifier, pas l'ajouter (...) c'est le seul point de ce sprint
ou deux lots ecrivent dans le meme fichier a des endroits de nature differente."*

C'est exactement ce que le diff montre : `S42-HG-ROIS` **modifie deux entrees existantes au milieu
de la table** (25 a 27 items et 75 a 81 propositions sur *situer dans le temps* ; 45 a 48 et 135 a
144 sur *nommer, en histoire*) pendant que les quatre autres lots **ajoutent en fin de table**. Les
deux formes de fusion ont ete faites et le fichier est vert avec **23 tests inchanges**, ce qui est
le bon signe : cet inventaire est a egalite exacte et rougirait aussi bien sur un ajout perdu que
sur un compte non mis a jour.

## 3. Question 2 - le verdict *"aucun `ADR`, aucune regle ArchUnit neuve"*, reverifie sur l'arbre integre

### 3.1 Le compte de regles, meme methode qu'au 2026-09-24

**Methode, dans le meme paragraphe que le chiffre** : occurrences litterales des chaines
`static final ArchRule` et `@ArchTest` sous `src/test/java` des **cinq** modules declares au pom
agregateur, arbre de contre-exemple **exclu**.

| Module | `static final ArchRule` | `@ArchTest` |
|---|---|---|
| `domain` | 0 | 0 |
| `application` | 0 | 0 |
| `adapter-web` | 1 | 1 |
| `adapter-content` | 0 | 0 |
| `bootstrap` | 35 | 34 |
| **total** | **36** | **35** |

**Identique au chiffre de l'avis et a celui de s41. Aucune regle neuve, aucune regle retiree.**

**Une precision de methode que je n'avais pas ecrite assez precisement dans l'avis, et elle
change le chiffre de controle.** J'y annoncais qu'un balayage brut incluant les contre-exemples
rend **40**. Ici il rend **38**. Les deux sont exacts et la difference n'est pas dans le depot :
elle est dans **l'etat de `target/`**. Le fichier
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/src/test/java/com/recreo/faux/RegleContreExemple.java`
porte deux regles et son chemin contient `src/test/java` ; apres `clean` il n'existe qu'une fois
(38), apres la copie des ressources de test il en existe deux copies (40). **Un chiffre de controle
qui depend du moment du build n'est pas un chiffre de controle** : c'est precisement pourquoi la
methode retenue exclut `target/` et les contre-exemples, et c'est elle, pas le balayage brut, qui
rend 36 / 35 de facon stable.

L'ecart d'une unite entre 36 et 35 est celui qu'`ADR-0094` a nomme : **`ScanDeComposantsTest`**
porte 1 `ArchRule` et 0 `@ArchTest`, la regle y etant invoquee depuis deux methodes `@Test`
ordinaires. Seule classe du depot ou les deux comptes different.

### 3.2 Aucune cle de schema neuve - et ce n'est pas ma lecture, c'est un garde-fou

Cles employees par les **48** items des quatre paquets neufs : `bonneReponse` (48),
`distracteurs` (48), `indice` (48), plus `enonce` au niveau de l'element de liste. **Aucune**
occurrence d'`illustration`, de `tableauSupport`, de `texteSupport`, de `texteAVoixHaute` ni de
`partDesignee`. Les quatre declarent `schema: 1` et `type: question`. Les **5** items neufs de
`S42-HG-ROIS` portent en plus `ligneDeReferentiel`, cle deja employee par leurs paquets d'accueil.

`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` rougirait sur une cle neuve que le chargeur
ignorerait. Elle est verte. **Aucun mecanisme neuf, donc aucune decision d'architecture a prendre.**

**Un fait de mon avis qui ne s'est pas realise, et c'est moi qui l'avais annonce.** Section 6.3 :
*"Decrire un deplacement sans image oblige a poser la scene en mots (...) c'est mesurable et c'est
serre"*, avec `texteSupport` nomme comme le moyen disponible et `ADR-0072` comme son prix.
**`cp-deplacements-quadrillage` ne porte aucun `texteSupport`** : douze items, enonces de **11 mots
au plus** (mesure a la main, le garde-fou etant l'autorite et etant vert), contre une borne a 15.
Ma prediction de tension etait fausse, et elle l'etait pour une raison que je peux nommer : j'avais
suppose qu'une scene de quadrillage devait etre decrite, alors que le lot pose des deplacements
relatifs a une case de depart implicite. **Consequence sur le verdict : aucune** - un moyen
disponible et non employe ne coute rien, et `ADR-0072` n'est pas sollicite.

### 3.3 Le declencheur que j'avais ecrit d'avance : reverifie ligne par ligne, et il ne se leve pas

Mon avis de s41, section 5.2, avait ecrit : *"le jour ou une **seconde** entree de catalogue
declarera l'une de ces six lignes, l'allocation de sous-partie redevient due."* Etat sur l'arbre
final, mesure sur les **100** fichiers de `adapters/adapter-content/src/main/resources/contenu/`
(96 avant s42, +4), un paquet par fichier, une entree de catalogue par paquet (100 entrees) :

| Code | Paquets qui le declarent |
|---|---|
| `EMC-CP-08`, `EMC-CP-10`, `EMC-CP-13`, `QLM-CP-04`, `QLM-CP-13`, `SCI-CM1-06` | **1 chacune** - les six lignes de s41, inchangees |
| `QLM-CP-06`, `ART-CP-05`, `EMC-CM1-05`, `MAT-CP-10` | **1 chacune** - quatre des cinq lignes de s42 |
| `HG-CM1-05` | **2** - `cm1-histoire-geographie-nommer-en-histoire` et `...-situer-dans-le-temps` |

**Le declencheur litteral ne se leve pas** : les six lignes qu'il nommait sont toujours a une
entree. Reste le cas de `HG-CM1-05`, qui **est** une ligne servie par deux entrees, et je le traite
sur le fond plutot que sur la lettre, parce qu'un declencheur qu'on interprete a la lettre pour
s'en dispenser ne vaut rien.

Mesure de toutes les lignes servies par plus d'un paquet, sur les 101 lignes distinctes servies :
`MAT-CE1-03` (4), `FRA-CP-04` (4), `FRA-CM1-11` (2), `HG-CM1-07` (2), `MAT-CP-02` (2),
`MAT-CP-05` (2), **`HG-CM1-05` (2)**. **La configuration a plusieurs paquets sur une ligne est donc
servie sept fois, dont six avant s42, et elle est verte.** `HG-CM1-05` rejoint `HG-CM1-07` dans le
lot ou cette configuration est deja auditee.

Et le mecanisme de sous-partie d'`ADR-0097` et d'`ADR-0098` reste borne ou il a ete decide :
mesure de ce jour, la declaration de sous-partie n'apparait que dans **sept** paquets, tous
`ce1-*`, et le type `SousPartieDeMathematiquesCe1` est le seul type de sous-partie du depot.
**Rien a etendre, aucun `ADR` du.**

### 3.4 Les cliquets, relus dans le code de l'arbre final

| Constante | Fichier | Valeur a s41 | Valeur a `cdf0229f` |
|---|---|---|---|
| `POIDS_D_UN_DESSIN_MAXIMUM` | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | 4633 | **4633** |
| `POIDS_TOTAL_MAXIMUM` | idem | 51307 | **51307** |
| `PLAFOND_DE_DESIGNATION_EN_PRODUCTION` | `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` | 2 | **2** |
| `POIDS_TOTAL_MAXIMUM` | `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | 0 | **0** |
| `PLAFOND_D_HOMONYMES` | `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 2 | **2** |
| `PLAFOND_DE_DETTE` (longueur au CP) | `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` | 5 | **5** |
| `PLAFOND_DE_DETTE_DE_FORME` | idem | 0 | **0** |
| `PLAFOND_DE_DETTE` (declaration de ligne) | `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` | 0 | **0** |

**Aucun cliquet deplace.** Le seul entier neuf du diff de test est `PLAFOND_ITEMS = 20` dans
`PaquetCpDeplacementsQuadrillageTest` : ce n'est pas un cliquet de dette mais la borne de taille de
paquet, deja ecrite a l'identique dans **treize** autres classes de paquet du depot.

Compte courant de `REGISTRE-identifiants.md` : **682** identifiants, **111** prefixes - inchange.
Coherent avec le fait qu'aucune reserve neuve n'a encore ete ouverte, les audits de `nadia-prof`
n'ayant pas eu lieu au moment de cette mesure.

### 3.5 `squad/decisions/` : ce que s42 y a ecrit

`git diff --stat` de `9fed12bc` a `cdf0229f` sur `squad/decisions/`, `docs/backlog/` et
`squad/programme/` : **aucun fichier**. `git log` sur `squad/decisions/ADR-index.md` sur la meme
plage : **aucun commit**. **L'index reflete donc encore l'etat reel, et je n'ai rien a y corriger.**

### 3.6 Ce que le parallelisme des deux terrains a reellement coute

Section 9.2 de l'avis nommait le compte courant du registre comme *"le seul endroit du dispositif
ou une regle existante fait exactement le travail d'un garde-fou de parallelisme"*. Il n'a pas eu a
servir : aucune reserve n'a ete ouverte, le compte est inchange. Les trois surfaces de contact
partagees (`catalogue.yaml`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`TexteAccentueExposeParLePortEntrantTest`) ont ete fusionnees sans perte : les trois sont a egalite
exacte, donc une fusion qui aurait perdu la moitie d'un ajout serait rouge, et elles sont vertes.

## 4. Question 3 - `HIS-1` sur les items neufs de `S42-HG-ROIS`

### 4.1 Le calcul, refait de bout en bout et non repris

**Methode** : parcours des 100 fichiers de contenu servis, regroupement par le couple
(`matiere`, `niveau`) de leur descripteur, comptage des lignes portant `- enonce:` et, parmi
elles, de celles portant `# HIS-1:` puis `# HIS-1: restitution`. C'est la meme derivation que celle
du garde-fou, refaite hors de lui.

| Paquet du lot Histoire-geographie / CM1 | Items | Marques `HIS-1` | dont `restitution` |
|---|---|---|---|
| `cm1-histoire-geographie-lire-un-document` | 10 | 10 | 0 |
| `cm1-histoire-geographie-nommer-en-geographie` | 30 | 30 | 5 |
| `cm1-histoire-geographie-nommer-en-histoire` | 48 | 48 | 11 |
| `cm1-histoire-geographie-situer-dans-le-temps` | 27 | 27 | 7 |
| **lot entier** | **115** | **115** | **23** |

`115 / 3 = 38` (division entiere, `PART_DE_RESTITUTION = 3`). `38 - 23 = 15`.

**Les trois chiffres de `marc-dev` sont confirmes : 115 items, 23 restitutions, plafond 38, marge
15.** Chaque item du lot porte sa marque (115 sur 115), donc aucun fichier n'echappe au classement,
ce qui est la premiere clause d'`ADR-0079`.

Et les cinq items neufs, un a un : **trois `# HIS-1: association`** dans *nommer, en histoire* et
**deux `# HIS-1: ordre`** dans *situer dans le temps*. **Zero `restitution`**, ce qui est la raison
pour laquelle le compte de restitutions ne bouge pas.

### 4.2 Le plafond a bouge, et la question telle qu'elle m'est posee porte l'ancien

La question parle d'un *"plafond de 36 restitutions"*. **36 est le plafond d'avant s42**, celui que
mon propre avis a ecrit en section 5 sur un lot de **110** items (`110 / 3 = 36`). Sur l'arbre
final le lot en porte 115 et le plafond vaut **38**.

Les deux sont respectes par 23, donc le verdict ne change pas. Je le nomme parce que la mecanique
est le point interessant, et qu'elle est contre-intuitive : **le plafond de `HIS-1` se calcule sur
le lot apres ajout, donc ajouter des items non-restitution deserre la contrainte**. Cinq items
d'`association` et d'`ordre` ont augmente la marge de 13 a 15 sans qu'un seul item de restitution
soit ecrit. C'est exactement ce que mon avis annoncait en section 5 - *"un lot de dix items neufs
porte le plafond a 40 : meme entierement de restitution, il passerait"* - et c'est pourquoi j'y
avais ecrit que **le critere d'acceptation de la story est plus strict que la regle encodee**. Il
l'est toujours : le garde-fou tient le tiers du lot, `nadia-prof` tient le tiers par paquet.

### 4.3 Prouve par mutation sur le contenu reel, pas par lecture du test

Cinq mesures partielles, **toutes avec `-am`**, sur le worktree dedie :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test \
  -Dtest=UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest \
  -Dsurefire.failIfNoSpecifiedTests=false
```

| Mutation du contenu servi | Restitutions du lot | Classe entiere (9 tests) |
|---|---|---|
| aucune (arbre tel qu'il est fusionne) | 23 | **verte** |
| les 10 items de `lire-un-document` passes en `restitution` (le fichier est a **10 sur 10**) | 33 | **verte** |
| +9 marques `association` passees en `restitution` dans `nommer-en-histoire` | 32 | **verte** |
| +10 | 33 | **rouge**, 1 echec sur 9 |
| +16 | 39 | **rouge**, 3 echecs sur 9 |

**La deuxieme ligne est la preuve du denominateur, et elle est plus forte qu'une lecture du code** :
un fichier dont **100 %** des items sont de la restitution passe, parce que le **lot** tient son
tiers. Une lecture par fichier serait ici rouge a tort. C'est la propriete meme qu'`ADR-0079`
existe pour tenir.

**La derniere ligne est la preuve du plafond**, et ce n'est pas moi qui l'ecris, c'est l'assertion
qui l'imprime en echouant :

```
Histoire-geographie / CM1 : 39 items de restitution sur 115 (4 fichier(s)), au-dessus du
tiers du LOT, soit 38.
```

**`115`, `4 fichiers`, `38`.** Le denominateur est le lot entier et le plafond vaut 38.

Le fichier de contenu a ete restaure a l'octet pres apres chaque mutation (`git status` vide) et la
classe rejoue verte.

### 4.4 La marge reellement disponible est 9, pas 15, et ce n'est pas un detail de comptage

**Le fait, mesure aux deux bornes** : la classe est verte a **32** restitutions reelles et rouge a
**33**. Elle ne va pas jusqu'a 38.

**La cause, lue dans le code.** Le test voisin
`leMemeLotRepartiSurDeuxFichiersResteVertQuandLeLotTientSonTiers` prouve une propriete du juge en
**greffant six items entierement de restitution sur le lot reel**, apres avoir remplace le premier
paquet marque du lot (`lire-un-document`, 10 items, 0 restitution) par un paquet de 4 items sans
restitution. Le lot qu'il soumet au juge porte donc `restitutions reelles + 6`, contre le meme
plafond de 38. Sa condition de verdure est `restitutions reelles <= 32`.

C'est **le test qui rougit en premier** dans les deux mutations rouges ci-dessus, et a `+16` il
rougit en imprimant `45 items de restitution sur 115 (5 fichier(s))` - cinq fichiers, donc son lot
synthetique, pas le lot servi.

**Ce que je dis de ce fait, et ce que je n'en dis pas.** Ce n'est pas un defaut : le test fait
exactement ce que son nom annonce, et il le fait sur du materiau reel, ce qui est la bonne methode.
C'est une **consequence** de cette bonne methode : un test qui greffe sur du materiau reel herite
de la marge de ce materiau. **La consequence operationnelle est la seule chose qui compte pour la
suite** : quiconque lit *"marge de 15"* et ecrit douze items de restitution de plus sur ce lot aura
un rouge au dixieme, et le message d'echec parlera d'un lot de cinq fichiers qui n'existe pas. Je
l'ecris ici pour que ce rouge-la, le jour ou il arrive, soit lu en une minute et non en une
demi-journee.

**Je ne modifie pas ce test.** Le rendre independant du contenu demanderait de lui donner une base
synthetique, ce qui lui ferait perdre precisement la propriete pour laquelle je le trouve bon.
L'arbitrage entre les deux ne se pose pas tant que la marge de 9 n'est pas consommee, et une
abstraction sur un usage que j'imagine est ce que ce depot me demande de ne pas ecrire. **C'est une
dette nommee, avec sa borne mesuree, pas une action.**

### 4.5 Les trois lots de CP sont dans la population de `LON-1` et `LON-2`, et ils y tiennent

`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` derive sa population du niveau declare au
descripteur, jamais d'une liste de paquets : les trois paquets de CP de s42 y sont entres des leur
premier commit, bornes a **zero** enonce trop long, la table `DETTE_DE_LONGUEUR` ne nommant que
trois paquets d'EMC CP. Les sept tests de la classe sont verts. Mesure indicative a la main de
l'enonce le plus long de chacun : 11 mots (`cp-deplacements-quadrillage`), 12
(`cp-arts-couleurs`), 14 (`cp-qlm-matiere`), contre `MOTS_MAXIMUM = 15`.

Les deux lots de CM1 ne sont contraints sur aucun axe de longueur : `NIVEAU_BORNE = "CP"` est la
seule constante de niveau du garde-fou, et c'est le fait que la section 8.1 de mon avis nommait.

## 5. Un fait que la question ne demandait pas, et que je nomme parce qu'il est de la famille que ce depot sanctionne

`PaquetCm1HistoireGeographieTest` porte un test neuf,
`hgCm105NePorteAucunJugementDeValeurSurUnRoi`, dont la Javadoc annonce : *"`BIA-5` : chaque item de
`HG-CM1-05` declare un des quatre rois du libelle, sa datation ou sa situation dans le temps -
jamais un jugement de valeur sur le personnage."*

**Ce que son corps fait** : il verifie que la `bonneReponse` de chaque item de `HG-CM1-05`
appartient a l'ensemble des quatre noms de rois. C'est une assertion reelle et utile - c'est le
critere d'acceptation de la story - **mais elle ne juge aucun jugement de valeur.**

**Prouve par mutation, et sur le reacteur entier.** J'ai remplace l'enonce d'un item de
`HG-CM1-05` par une question qui est un jugement de valeur explicite sur les personnages, en
conservant sa `bonneReponse`, ses accents et son espace insecable avant le point d'interrogation,
pour qu'aucun garde-fou typographique ne rougisse a la place du bon motif :

```
bash outils/mvn-jdk21.sh -B clean verify
[INFO] BUILD SUCCESS
```

**Le reacteur entier est vert sur cet enonce.** Les 29 tests de la classe le sont aussi. Le contenu
a ete restaure a l'octet pres, `git status` vide.

**Ce que cela veut dire, et ce que cela ne veut pas dire.** `BIA-5` n'est pas gardee par ce test,
ni par aucun autre : juger qu'un enonce porte un jugement de valeur demande de lire le sens, ce que
mon avis ecrivait deja en section 7.1 et ce que je maintiens. **Le fait neuf n'est pas l'absence de
garde-fou, c'est qu'une Javadoc annonce desormais ce garde-fou.** C'est la forme exacte de la dette
numero 7 de la version precedente - *"un garde-fou annonce mais absent"* - a ceci pres qu'elle vit
ici dans un commentaire de test et non dans un document.

**Ce que cette annonce a deja produit, et c'est la raison pour laquelle je ne me contente pas de la
signaler.** `rapport-qualite.md` de `yanis-qa`, section 3, porte `hgCm105NePorteAucunJugementDeValeurSurUnRoi`
dans la colonne **"criteres encodes en test"** avec la mention `(BIA-5)`, et ne range `BIA-5` dans
aucune des colonnes de ce qui reste garde de `nadia-prof`, contrairement a `HIS-2` et `HIS-3` qui y
figurent pour ce meme lot. **Sa methode est ecrite et elle est bonne** - *"le fichier de test du
paquet porte-t-il une methode nommee sur le code, et cette methode l'assertionne-t-elle
reellement"* - et elle se fait prendre ici par le seul cas qu'elle ne peut pas voir : **une methode
dont l'assertion est reelle, mais porte sur autre chose que son nom.** Le lire methode par methode
ne suffit pas ; il faut le muter. C'est le meme mode de defaillance que celui que ce depot a
sanctionne a s05 sur une de mes propres regles, a ceci pres qu'il traverse cette fois deux agents.

**Ce que je fais et ne fais pas.** Je ne modifie pas le fichier de test : il appartient a son
auteur, et je ne propose aucune regle, parce qu'une regle qui pretendrait juger un jugement de
valeur serait decorative. Je ne modifie pas non plus le rapport de `yanis-qa`, qui est son
livrable ; je nomme l'ecart ici et il lui revient. Les deux corrections possibles sont **soit** renommer le test et sa Javadoc pour ce
qu'il verifie reellement, **soit** y ecrire que `BIA-5` reste la garde de `nadia-prof`. Le choix
entre les deux est une question de tenue de test, et je la route a `sofia-sm`. **Le fond -
l'absence de jugement de valeur sur les quatre rois - est et reste a `nadia-prof`**, qui l'audite
en jouant.

## 6. Les dettes que l'avis laissait ouvertes, etat inchange

Je les rappelle pour que leur statut ne se perde pas entre l'avis et la review, sans en payer
aucune ici :

1. **Aucun garde-fou du depot ne compare deux paquets differents.** L'adjacence de `EMC-CM1-05`
   avec `cp-emc-symboles` et `cp-emc-devise`, servis a s41 et s40, n'est gardee par rien, et les
   deux paquets ont ete audites par une autre instance de `nadia-prof` que celle qui auditera
   celui-ci. Route a `nadia-prof` via `sofia-sm`, section 8.1 de l'avis. Meme constat sur
   l'adjacence de `QLM-CP-06` avec `cm1-sciences-familles-materiaux`, ou seule la **forme** est
   discriminee, par `LON-1`.
2. **La tenue de la cellule 227 de la matrice** : la mention de couverture partielle pre-ecrite
   suppose l'ordre de livraison inverse de celui de s42. Elle appartient a `camille-po`, section 4
   de l'avis.
3. **`ART-1`, `HIS-2`, `HIS-3`, `BIA-5`** restent non encodables. Garde de `nadia-prof`.
4. **La marge de `HIS-1` sur le lot HG CM1 est de 9 et non de 15** avant que le garde-fou rougisse,
   section 4.4. Dette nommee, bornes mesurees, aucune action.

## 7. Les trois autres jobs de la CI, lances sur mon propre diff

Le geste que `CLAUDE.md` nomme depuis le 2026-09-08 - *"lancer la commande de la CI sur son propre
diff avant de commiter"*.

| Job | Commande, telle que `.github/workflows/ci.yml` l'ecrit | Resultat |
|---|---|---|
| `prefixes` | `.github/scripts/verifier-prefixes-test.sh` puis `.github/scripts/commit-atteignable-test.sh` | **vert** |
| `chemins` | `.github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** |
| `outils` | les trois motifs de balayage du workflow, tels qu'il les ecrit | **vert** |

**Ce que ces trois jobs ne remplacent pas** : ils ne rejouent pas `mvn verify`, et `mvn verify` ne
les lance pas. C'est la confusion exacte qui a laisse le job `chemins` rouge un sprint entier a s30.

### 7.1 Les deux garde-fous qui jugent **ce fichier-ci**

Ce fichier cite des identifiants `ADR-`, `DEC-` et des identifiants de condition. Deux garde-fous
les jugent, et ils vivent dans le job `build`, pas dans les trois ci-dessus :
`ToutesLesReferencesDeDecisionResolventTest` (`ADR-0066`) et
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`ADR-0085`). Lances sur l'arbre qui porte
ce fichier :

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test \
  -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,ToutesLesReferencesDeDecisionResolventTest \
  -Dsurefire.failIfNoSpecifiedTests=false
```

```
Tests run: 3, Failures: 0, ... Time elapsed: 5.163 s -- in ToutesLesReferencesDeDecisionResolventTest
Tests run: 7, Failures: 0, ... Time elapsed: 18.73 s -- in ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

**Les deux noms de classes sont separes par une virgule et les temps ecoules different de ceux du
`verify` de la section 1** : c'est le controle que la correction de s41 avait rendu obligatoire,
apres un `BUILD SUCCESS` obtenu sur **zero classe selectionnee**, dont les rapports `.txt` etaient
ceux, intacts, d'un lancement anterieur.

## 8. Ce que je n'ai pas fait, et pourquoi

- **Aucun `ADR` ecrit, aucune regle ArchUnit ecrite.** Le declencheur de mon avis de s41 a ete
  reverifie ligne par ligne et ne se leve pas ; la configuration a plusieurs paquets sur une ligne
  est servie sept fois et verte ; le mecanisme de sous-partie reste borne aux mathematiques de CE1.
- **Aucun fichier du code, du contenu, de la matrice, du backlog ou du registre touche.** Les sept
  mutations des sections 4.3 et 5 ont ete restaurees a l'octet pres avant ce commit.
- **Je n'ecris pas dans `review.md`.** La ceremonie n'est pas ouverte ; `sofia-sm` la tient.
- **Je ne prononce aucun Done pedagogique et aucun verdict de contenu.** Les 53 items neufs
  attendent `nadia-prof`.

## Decisions prises

**Aucune decision d'architecture n'a ete prise dans ce fichier.** Il confirme, sur l'arbre integre,
un verdict deja rendu le 2026-09-24 : aucun `ADR` neuf, aucune regle ArchUnit neuve. Les ecritures
neuves sont trois constats mesures - le plafond de `HIS-1` passe de 36 a 38 sous l'effet du lot
lui-meme (section 4.2), la marge reelle avant rouge est 9 et non 15 (section 4.4), et une Javadoc
annonce une garde de `BIA-5` que rien n'execute (section 5) - dont aucun ne change le verdict.
