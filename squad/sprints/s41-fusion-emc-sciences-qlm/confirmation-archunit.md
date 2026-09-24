# Confirmation ArchUnit - s41 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-24.
- **Objet** : confirmer, sur l'arbre final de `round-two` une fois les **six** lots de s41
  reellement fusionnes, ce que mon avis de faisabilite du 2026-09-23 concluait sur un arbre qui ne
  les portait pas encore.
- **Perimetre mesure** : `round-two` a **`b5b07966`**, worktree dedie `s41-theo-confirmation`
  branche depuis ce commit (amendement s18 et s21 : une branche dediee nommee pour un agent
  implique un worktree dedie du meme nom ; toute mesure du reacteur depuis son propre worktree).
  **Remesure le 2026-09-24 apres fusion de `round-two` a `a86234ed`** dans ce meme worktree : le
  diff apporte est du `squad/` seul, aucun fichier de code ni de contenu, donc tous les verdicts de
  ce fichier valent sur les deux arbres. Les chiffres cites sont ceux de la relance (section 1).
- **Version** : **JDK 21.0.10**, telle que le JDK resolu par l'enveloppe la rapporte, jamais telle
  qu'une entree de toolchain la declare (`ADR-0071`). Premiere ligne de l'enveloppe :
  *"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*. Maven 3.9.9.
- **Pourquoi ce fichier et pas un ajout date a `avis-faisabilite.md`** : la regle d'ajout date de
  `squad/README.md`, section 2, est une exception nommee **pour une ceremonie pas encore close**, et
  un avis de faisabilite n'est pas une des quatre ceremonies de la section 4. Le depot porte deja le
  nom de fichier qui convient, employe a treize sprints (`s00` a `s36`) : `confirmation-archunit.md`.
  Aucune regle de nommage neuve n'est inventee ici.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Les tests ArchUnit restent-ils verts sur le code neuf des six lots ? | **oui**, et la reponse exacte est plus forte : **les six lots ne portent aucune ligne de Java de production** (section 2). `mvn -B clean verify` sur la racine : **`BUILD SUCCESS`**, 6 modules sur 6 |
| 2. *"Aucun `ADR` neuf, aucune regle ArchUnit neuve"* tient-il une fois les six lots integres ? | **oui**. Compte du depot **inchange** : **36 `static final ArchRule`, 35 `@ArchTest`**, meme methode et meme chiffre qu'au 2026-09-23. Aucun `ADR` ecrit, `ADR-index.md` intouche par s41 (section 3) |
| 3. `BIN-1` sur `S41-SCI-CLASSER` respecte-t-elle le patron attendu ? | **oui, et prouve par mutation, pas par lecture** : le denominateur est le paquet entier (11), jamais une sous-tache. **Et un fait que je nomme au-dela de la question** : sur ce paquet-ci, `BIN-1` ne peut jamais mordre la premiere, parce que `V3` lui est strictement plus dure (section 4) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique, ne ferme aucune
ligne de matrice, ne juge aucun contenu. La porte pedagogique est a `nadia-prof` seule.

**Ce que cette confirmation ne couvre pas, et je le nomme plutot que de le deduire** (`CLAUDE.md`,
amendement du 2026-09-08) : `mvn verify` **n'est que le contenu du job `build`** de
`.github/workflows/ci.yml`, qui en porte quatre. Les trois autres sont lances a part et rapportes
en section 6. **Je n'annonce pas la porte technique du sprint** - c'est `sofia-sm` qui la prononce
en `review.md`, et `yanis-qa` qui rapporte la qualite.

## 1. La commande, sa sortie, ses comptes

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur **complet depuis la racine** - la question du `-am` ne se pose donc pas ici, elle ne
concerne que les mesures partielles (`CLAUDE.md`, prerequis de build). Les deux mesures partielles de
la section 4 portent `-am`, elles.

**Premier lancement, sur `b5b07966`** :

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  1.797 s]
[INFO] recreo-domain ...................................... SUCCESS [ 22.905 s]
[INFO] recreo-application ................................. SUCCESS [  5.822 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:33 min]
[INFO] recreo-adapter-content ............................. SUCCESS [ 59.337 s]
[INFO] recreo-bootstrap ................................... SUCCESS [03:24 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  06:28 min
[INFO] Finished at: 2026-09-24T10:24:45+02:00
```

**Relance apres la fusion de `round-two`** (rapport qualite de `yanis-qa` et six fiches de
conformite de `camille-po` : **aucun fichier de code ni de contenu au diff**, donc meme population
de tests) :

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo-domain ...................................... SUCCESS [ 14.585 s]
[INFO] recreo-application ................................. SUCCESS [  5.229 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:13 min]
[INFO] recreo-adapter-content ............................. SUCCESS [ 45.835 s]
[INFO] recreo-bootstrap ................................... SUCCESS [03:03 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  05:23 min
```

**Comptes, avec leur methode dans le meme paragraphe** : somme des blocs `[INFO] Results:` du
reacteur Maven apres `clean` complet, methode autoritaire de `squad/README.md`, section 9
(amendement s11). Chiffres de la relance ci-dessus ; 0 echec, 0 erreur, 0 ignore sur les six.

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 17 |
| `adapter-web` | 400 |
| `adapter-content` | 881 |
| `bootstrap` (surefire) | 277 |
| `bootstrap` (failsafe) | 6 |
| **total** | **1701** |

Chiffre identique a celui de `yanis-qa` (`rapport-qualite.md`, section 1), obtenu par deux methodes
independantes : blocs `Results:` du journal, et somme des attributs `tests="..."` des rapports XML.

### 1.1 Correction du 2026-09-24 (`theo-archi`), et elle porte sur ma propre mesure

**La premiere version de cette section annoncait `1692` tests**, avec `adapter-web` a `399` et
`bootstrap` (surefire) a `269`, par la methode *"somme des blocs `Tests run:` des rapports `.txt`"*.
**Ce chiffre est faux et cette methode est explicitement interdite par `squad/README.md` depuis
l'amendement s11** : *"jamais la somme des lignes `Tests run:` des rapports `.txt`"*. L'ecart avec
`yanis-qa` - 1692 contre 1701 - est ce qui l'a rendu visible, a la fusion.

**Cause, mesuree classe par classe** en comparant le journal du meme lancement aux rapports `.txt`.
Cinq classes divergent, et ce sont **exactement les cinq** qui melangent un `@ArchTest ArchRule`
(moteur `archunit-junit5`) et une methode `@Test` ordinaire (moteur Jupiter) dans une meme classe.
Surefire n'ecrit qu'un `.txt` par classe, et il n'y garde que le compte du moteur Jupiter :

| Classe | `@ArchTest` | `@Test` | journal | rapport `.txt` | perdu |
|---|---|---|---|---|---|
| `AbstractionsCiteesTest` | 4 | 1 | 5 | 1 | 4 |
| `LOrdreDesNiveauxResteUneDonneeTest` | 2 | 3 | 5 | 3 | 2 |
| `ChargementDuContenuTest` | 1 | 1 | 2 | 1 | 1 |
| `LesReglesMordentTest` | 1 | 36 | 37 | 36 | 1 |
| `AssertionsWebTest` (`adapter-web`) | 1 | 1 | 2 | 1 | 1 |
| | | | | **total perdu** | **9** |

1692 + 9 = 1701. **Aucune autre classe du depot ne diverge**, et la population des classes mixtes
est passee de **quatre** (compte de `yanis-qa` a s11) a **cinq** : `AssertionsWebTest` a rejoint le
lot depuis.

**Ce que je m'etais fait a moi-meme, et c'est le plus instructif.** J'avais ecrit ici, en toutes
lettres : *"La cause de divergence que mon propre prompt me designe - deux moteurs JUnit dont les
rapports texte n'en garderaient qu'un - est verifiee ici et ne s'applique pas"*, avec pour preuve
`ArchitectureEnCouchesTest`, **2 `@ArchTest` et 0 `@Test`**. C'est le seul temoin du depot qui ne
pouvait pas reveler le defaut : sans methode Jupiter, aucun rapport Jupiter n'est ecrit, donc rien
n'ecrase le rapport ArchUnit, et le compte est juste. **J'ai choisi le temoin qui rendait ma phrase
vraie.** C'est litteralement la faute que mon propre prompt nomme - *"une fixture ecrite par
l'auteur d'une regle ressemble a ce qu'il imagine, pas a ce que le monde ecrit"* - commise en
croyant l'eviter, et dans le paragraphe meme ou je la citais. **Le temoin qu'il fallait prendre est
une classe mixte, et le depot en porte cinq.**

**Ce que cette correction ne change pas** : le verdict des trois questions, le `BUILD SUCCESS`, les
zeros d'echec, les comptes de regles ArchUnit (36 / 35, greps de source, non concernes par les
moteurs de test), et la mesure par mutation de la section 4.2 (lue sur le journal, pas sur les
`.txt`). Les chiffres de la section 2.2 sont corriges au meme geste et portent la meme
marque ; **ceux de la section 2.3 sont inchanges**, verifies un a un contre le journal - aucune de
ces neuf classes n'est mixte.

## 2. Question 1 - les tests ArchUnit sur le code neuf des six lots

### 2.1 Le fait qui rend la question plus simple qu'elle n'en a l'air

Diff complet de `eec116b1` (mon avis) a `b5b07966` (arbre final), tel que `git diff --stat` le donne,
hors fichiers de `squad/` et de `docs/` :

```
 .../src/main/resources/catalogue.yaml                          |  36 ++
 .../cm1-sciences-classer-etres-vivants.yaml                    | 102 +++++
 .../resources/contenu/cp-emc-droits-enfant.yaml                |  91 ++++
 .../contenu/cp-emc-roles-ecole-commune.yaml                    |  85 ++++
 .../main/resources/contenu/cp-emc-symboles.yaml                |  91 ++++
 .../src/main/resources/contenu/cp-qlm-hygiene.yaml             |  97 ++++
 .../src/main/resources/contenu/cp-qlm-saisons.yaml             | 101 ++++
 ...neReponseNEstPasPredictibleSurUnPaquetTest.java             |  50 +-
 .../PaquetCm1SciencesClasserEtresVivantsTest.java              | 133 ++++
 .../content/PaquetCpEmcDroitsEnfantTest.java                   | 188 ++++++
 .../content/PaquetCpEmcRolesEcoleCommuneTest.java              | 137 ++++
 .../adapter/content/PaquetCpEmcSymbolesTest.java               | 196 ++++++
 .../adapter/content/PaquetCpQlmHygieneTest.java                | 144 ++++
 .../adapter/content/PaquetCpQlmSaisonsTest.java                | 158 ++++
 ...mpteSurLeLotDeMatiereEtNonSurUnFichierTest.java             |  13 +-
 .../TexteAccentueExposeParLePortEntrantTest.java               |  66 +-
```

**Zero ligne de Java de production sur les six lots**, exactement la forme que la section 3 de mon
avis annoncait a partir du precedent `671f9379`. Consequence directe et non triviale : les regles
ArchUnit **proprement dites** - couches, purete, decouplage mecanique/contenu, scan de composants -
n'ont **aucun objet neuf a mordre**, parce qu'elles ne modelisent que des classes et leurs
dependances, et qu'aucune classe de production n'a bouge. Elles sont vertes, et il faut savoir
qu'elles sont vertes **par absence de matiere neuve**, pas parce qu'elles auraient valide quelque
chose du contenu.

**Ce qui mord reellement les six lots** est ailleurs : les garde-fous de contenu de
`bootstrap/src/test/java/com/recreo/archi`, qui sont des tests JUnit ordinaires lisant les paquets
servis, **pas des `ArchRule`**. Confondre les deux serait annoncer une garantie qu'ArchUnit ne donne
pas. Ils sont, eux, verts sur de la matiere neuve, et c'est le resultat qui compte.

### 2.2 Les 29 classes de `com.recreo.archi`, une par une

Toutes vertes, **198 tests, 0 echec, 0 erreur, 0 ignore** (blocs du journal du reacteur, methode de la section
1.1 ; la somme des rapports `.txt` rendrait 190, en perdant les sept tests ArchUnit des trois
classes mixtes de cette table).

| Classe | Tests | Ce qu'elle a vu de neuf sur les six lots |
|---|---|---|
| `AbstractionsCiteesTest` | 5 | rien - **classe mixte**, section 1.1 |
| `AccesALEtatDeSessionTest` | 2 | rien |
| `ArchitectureEnCouchesTest` | 2 | rien - **ArchUnit pur**, aucune classe neuve |
| `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` | 5 | **6 paquets neufs** : aucune cle de schema neuve, section 3.2 |
| `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` | 5 | rien - aucun des six ne porte `tableauSupport` |
| `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` | 7 | **5 paquets de CP neufs**, bornes a zero enonce trop long des leur premier commit |
| `AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest` | 8 | rien |
| `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` | 6 | rien - les six sont du bandit manchot |
| `AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest` | 1 | rien |
| `ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest` | 6 | 6 entrees de catalogue neuves |
| `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` | 12 | **6 lignes de referentiel neuves declarees**, section 3.3 |
| `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | 18 | rien - cliquet a plafond zero intact |
| `ChargementDuContenuTest` | 2 | rien - **classe mixte**, section 1.1 |
| `ConcordanceDesTagsDAgentTest` | 1 | rien |
| `DecouplageMecaniqueContenuTest` | 12 | rien - **ArchUnit pur** |
| `DifficulteTest` | 3 | rien |
| `GestionDesErreursTest` | 4 | rien |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | 9 | **77 items neufs**, `FAB-1a` a `FAB-1c` sur le depot entier |
| `LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest` | 4 | rien |
| `LeChoixDesJeuxResteDerriereLePortTest` | 1 | rien |
| `LesGardeFousSontMisALEpreuveTest` | 10 | rien |
| `LesReglesMordentTest` | 37 | rien - **aucune morsure ajoutee ni retiree**, le fichier n'est pas au diff. **Classe mixte**, section 1.1 |
| `LIllustrationDeContenuResoutEtAucuneNeDortTest` | 15 | rien - aucun des six ne porte d'illustration |
| `LOrdreDesNiveauxResteUneDonneeTest` | 5 | rien - **classe mixte**, section 1.1 |
| `PureteDesCouchesTest` | 2 | rien - **ArchUnit pur** |
| `ScanDeComposantsTest` | 2 | rien |
| `ToutesLesReferencesDeDecisionResolventTest` | 3 | les references de `DEC-0095` et des six commentaires d'entete |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 7 | les identifiants cites par les six commentaires d'entete, section 3.4 |
| `UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest` | 4 | 6 paquets de 11 a 16 items, sous les quatre bornes |

`adapter-web` porte la seule `ArchRule` hors de `bootstrap` :
`com.recreo.adapter.web.archi.AssertionsWebTest`, **2 tests, verts** - **classe mixte**, section 1.1.

### 2.3 Les six tests de paquet et les trois inventaires partages

| Classe | Tests | Verdict |
|---|---|---|
| `PaquetCpEmcSymbolesTest` | 6 | vert |
| `PaquetCpEmcDroitsEnfantTest` | 6 | vert |
| `PaquetCpEmcRolesEcoleCommuneTest` | 5 | vert |
| `PaquetCm1SciencesClasserEtresVivantsTest` | 5 | vert |
| `PaquetCpQlmHygieneTest` | 4 | vert |
| `PaquetCpQlmSaisonsTest` | 5 | vert |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (partage) | 23 | vert |
| `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (partage) | 9 | vert |
| `TexteAccentueExposeParLePortEntrantTest` (partage) | 5 | vert |

## 3. Question 2 - le verdict *"aucun `ADR`, aucune regle ArchUnit neuve"*, reverifie sur l'arbre integre

### 3.1 Le compte de regles, meme methode qu'au 2026-09-23

**Methode, dans le meme paragraphe que le chiffre** : occurrences litterales des chaines
`static final ArchRule` et `@ArchTest` sous `src/test/java` des **cinq** modules declares au pom
agregateur, `target/` sans objet (le balayage ne descend pas dedans), arbre de contre-exemple
**exclu**.

| Module | `static final ArchRule` | `@ArchTest` |
|---|---|---|
| `domain` | 0 | 0 |
| `application` | 0 | 0 |
| `adapter-web` | 1 | 1 |
| `adapter-content` | 0 | 0 |
| `bootstrap` | 35 | 34 |
| **total** | **36** | **35** |

**Identique au chiffre de l'avis. Aucune regle neuve, aucune regle retiree.**

Les deux causes de divergence que l'avis nommait sont reverifiees et se comportent comme ecrit :

- un balayage qui **inclut** l'arbre de contre-exemple rend **38**, les deux unites d'ecart etant
  portees par le seul fichier
  `bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/src/test/java/com/recreo/faux/RegleContreExemple.java`,
  dont le chemin contient lui aussi `src/test/java` ;
- l'ecart d'une unite entre 36 et 35 est celui qu'`ADR-0094` a nomme, et je peux desormais le
  designer exactement : **`ScanDeComposantsTest`** porte **1 `ArchRule` et 0 `@ArchTest`** - la regle
  y est invoquee depuis deux methodes `@Test` ordinaires. C'est la seule classe du depot ou les deux
  comptes different.

### 3.2 Aucune cle de schema neuve - et ce n'est pas ma lecture, c'est un garde-fou

Cles employees par les 77 items des six paquets, comptees sur les six fichiers :
`bonneReponse` (77), `distracteurs` (77), `indice` (77), plus `enonce` au niveau de l'element de
liste. **Aucune** occurrence d'`illustration`, de `tableauSupport`, de `texteSupport`, de
`texteAVoixHaute` ni de `partDesignee`. Les six paquets declarent `schema: 1` et `type: question`.

C'est exactement ce que la section 4.1 de l'avis annoncait - *"la forme `question` a choix textuel,
avec `indice`, sans aucune autre composante"* - et **ce n'est pas seulement constate a la main** :
`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` rougirait sur une cle neuve que le chargeur
ignorerait. Elle est verte. Donc : **aucun mecanisme neuf, donc aucune decision d'architecture a
prendre.**

### 3.3 Le declencheur que j'avais ecrit d'avance n'est pas tire

L'avis, section 5.2 : *"le jour ou une **seconde** entree de catalogue declarera l'une de ces six
lignes, l'allocation de sous-partie redevient due, par le raisonnement d'`ADR-0097` decision 2. Ce
jour-la, l'`ADR` s'ecrit avant le paquet."*

Mesure sur les **96** fichiers de `adapters/adapter-content/src/main/resources/contenu/` (90 avant
s41, +6), un paquet par fichier :

| Code | Paquets qui le declarent |
|---|---|
| `EMC-CP-08` | 1 - `cp-emc-symboles.yaml` |
| `EMC-CP-10` | 1 - `cp-emc-droits-enfant.yaml` |
| `EMC-CP-13` | 1 - `cp-emc-roles-ecole-commune.yaml` |
| `QLM-CP-04` | 1 - `cp-qlm-hygiene.yaml` |
| `QLM-CP-13` | 1 - `cp-qlm-saisons.yaml` |
| `SCI-CM1-06` | 1 - `cm1-sciences-classer-etres-vivants.yaml` |

**Un chacune. Le declencheur n'est pas tire, la clause 6 d'`ADR-0081` suffit, aucun `ADR` n'est du.**

### 3.4 Les cliquets, relus dans le code de l'arbre final

| Constante | Fichier | Valeur au 2026-09-23 | Valeur a `b5b07966` |
|---|---|---|---|
| `POIDS_D_UN_DESSIN_MAXIMUM` | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | 4633 | **4633** |
| `POIDS_TOTAL_MAXIMUM` | idem | 51307 | **51307** |
| `PLAFOND_DE_DESIGNATION_EN_PRODUCTION` | `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` | 2 | **2** |
| `POIDS_TOTAL_MAXIMUM` | `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | 0 | **0** |
| `PLAFOND_D_HOMONYMES` | `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 2 | **2** |

**Aucun cliquet deplace.** Compte courant de `REGISTRE-identifiants.md` : **682** identifiants,
**111** prefixes - inchange, ce qui est coherent avec le fait qu'aucune reserve neuve n'a encore ete
ouverte, les audits de `nadia-prof` n'ayant pas eu lieu au moment de cette mesure.

### 3.5 `squad/decisions/` : ce que s41 y a ecrit

`git diff --stat eec116b1..b5b07966 -- squad/decisions/` rend **deux** fichiers : `DEC-0095` et
`DEC-index.md`. **`ADR-index.md` n'est pas au diff, aucun fichier `ADR-XXXX-*.md` n'est au diff.**
L'index reflete donc encore l'etat reel, et je n'ai rien a y corriger.

### 3.6 Un ecart de **mon** avis, que je corrige ici plutot que de le laisser trouver

La section 7.1 de l'avis ecrit : *"Sur les cinq fichiers d'un lot de contenu, deux sont propres au
lot et **trois sont partages** par tous les lots."* **Le diff reel en porte quatre.** Le quatrieme
est `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`, etendu par les deux lots de
questionner le monde CP :

```
-                        + " de cette ligne, soit 7 fichiers")
-                .hasSize(7);
+                        + " ... soit 9 fichiers")
+                .hasSize(9);
...
-                .isEqualTo(81);
+                        + " ... soit 112 items")
+                .isEqualTo(112);
```

**Ce sont des egalites exactes**, donc un inventaire du meme genre que les trois autres, et il a ete
etendu correctement, avec ses comptes mesures ecrits dans le message. Rien n'a casse.

**La cause de mon ecart, parce qu'elle est plus instructive que l'ecart** : j'ai enumere les
fichiers partages a partir d'**un seul diff reel**, `671f9379`, qui etait un lot d'**EMC**. Ce
garde-fou tient un inventaire **par lot de matiere et de niveau** ; le lot EMC/CP n'y a pas
d'entree, le lot QLM/CP en a une. **Ma methode etait ecrite et exacte ; c'est ma generalisation qui
excedait sa population** - un diff d'une matiere ne decrit pas les lots des trois matieres. La
lecon est celle que ce depot a deja ecrite dans l'autre sens : un perimetre enumere echoue par
omission autant que par inclusion.

**Consequence sur le verdict : aucune.** Un quatrieme fichier partage augmente la surface de conflit
de fusion d'une unite ; les six fusions ont eu lieu et sont vertes.

## 4. Question 3 - `BIN-1` sur `S41-SCI-CLASSER`

### 4.1 Le patron est respecte, au mot pres

`PaquetCm1SciencesClasserEtresVivantsTest`, methode
`bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires` :

```java
List<Question> items = items();
long binaires = items.stream().filter(item -> item.distracteurs().size() == 1).count();

assertThat(binaires)
        .as("BIN-1 : au plus la moitié des %d items du paquet portent deux propositions,"
                + " or %d les portent", items.size(), binaires)
        .isLessThanOrEqualTo(items.size() / 2);
```

`items()` est le **paquet entier** : la methode itere `contenu.next(...)` jusqu'a epuisement, sans
aucun filtre de tache. Corps identique a celui de `PaquetCpEmcRegleSouhaitLoiTest`, y compris le
message. **Le denominateur est le paquet, jamais une sous-tache** - c'est-a-dire exactement le point
que la reprise du 2026-09-10 avait du corriger sur le paquet d'origine, ou le test divisait par le
nombre d'items de la seule tache de classement et durcissait la clause d'un facteur deux.

### 4.2 Prouve par mutation sur le contenu reel, pas par lecture du test

Deux mesures partielles, **toutes deux avec `-am`**, sur le worktree dedie :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test \
  -Dtest=PaquetCm1SciencesClasserEtresVivantsTest -Dsurefire.failIfNoSpecifiedTests=false
```

| Mutation du fichier de contenu servi | `bin1...` | `chaqueItemProposeExactementDeuxDistracteurs` (`V3`) |
|---|---|---|
| aucune (arbre tel qu'il est fusionne) | **vert** | vert |
| **1** item sur 11 rendu binaire | **vert** | **rouge** |
| **6** items sur 11 rendus binaires | **rouge** | **rouge** |

Message exact du rouge de la troisieme ligne, tel que le rapport le donne :

```
[BIN-1 : au plus la moitié des 11 items du paquet portent deux propositions, or 6 les portent]
```

**`11`, c'est le paquet entier.** La preuve du denominateur n'est pas une lecture du code : c'est le
chiffre que l'assertion imprime en echouant. Le fichier de contenu a ete restaure a l'octet pres
apres les deux mutations (`git status` vide) et le test rejoue vert.

### 4.3 Ce que la question ne demandait pas, et que je nomme quand meme

**Sur ce paquet-ci, `BIN-1` ne peut jamais etre l'assertion qui mord la premiere.** La deuxieme
ligne du tableau le montre : un item binaire fait rougir `V3` immediatement, alors que `BIN-1` reste
verte jusqu'au sixieme. `V3` - *"chaque item propose exactement deux distracteurs"* - est
**strictement plus dure** que `BIN-1` et la contient.

**Ce n'est pas un defaut, et je ne demande rien.** C'est la configuration correcte pour cette ligne :
la matrice, ligne 2006, et `V3` interdisent d'avance le tri d'une collection entiere, et la forme
*"lequel de ces trois"* impose trois candidats sur chaque item. `BIN-1` y est donc tenue avec une
marge maximale, par construction.

**Ce que ce fait change dans ma lecture, et c'est la seule chose qui compte pour la suite** : la
condition `BIN-1` de mon avis est **satisfaite**, mais elle n'est pas *ce qui garde* ce paquet - ce
qui le garde est `V3`. Le jour ou un paquet soumis a `BIN-1` n'aura pas de `V3` au-dessus de lui,
`BIN-1` deviendra l'assertion mordante, et c'est ce jour-la que son encodage comptera vraiment.
Cela ne modifie pas le declencheur d'`ADR` que j'ai ecrit en section 6.6 de l'avis - **un troisieme
paquet soumis a `BIN-1`** - et je n'en ouvre pas un autre : deux occurrences ne fondent toujours pas
une abstraction.

## 5. Les trois dettes que l'avis laissait ouvertes, etat inchange

Je les rappelle pour que leur statut ne se perde pas entre l'avis et la review, sans en payer aucune
dans ce fichier :

1. **La mention de couverture partielle de `SCI-CM1-06`** vit dans un commentaire YAML et dans la
   matrice ; **aucun automate ne la lit**. Meme dette qu'a s40 sur `SCI-CM1-20`. La payer demande de
   decider ou une mention de couverture partielle vit structurellement, sur plusieurs lignes deja
   servies.
2. **L'adjacence de `QLM-CP-04` avec `cp-eps-corps-effort`** (eau, repos) n'est gardee par rien :
   aucun garde-fou du depot ne compare deux paquets differents. Routee a `nadia-prof` via
   `sofia-sm`, et `CYC-1` a ete verifiee a la main par `lea-dev` selon le meme constat.
3. **`ART-1`, `CAT-1`, `EMC-1`, `EMC-6`, `BIA-4`** restent non encodables : juger qu'une reponse
   repose sur une couleur percue ou sur un ton demande de lire le sens. Garde de `nadia-prof`.

## 6. Les trois autres jobs de la CI, lances sur mon propre diff

Le geste que `CLAUDE.md` nomme depuis le 2026-09-08 - *"lancer la commande de la CI sur son propre
diff avant de commiter"*.

| Job | Commande | Resultat |
|---|---|---|
| `prefixes` | `.github/scripts/verifier-prefixes-test.sh` puis `.github/scripts/commit-atteignable-test.sh` | **vert** |
| `chemins` | `.github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** |
| `outils` | les trois motifs de balayage du workflow, tels qu'il les ecrit | **vert** |

## 7. Sortie des trois jobs

**`chemins`**, lance sur l'index qui contient **ce fichier** - c'est le seul des trois qui juge un
contenu que je viens d'ecrire :

```
Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif.
ok 1 file changed, 409 insertions(+)
Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.
```

**`prefixes`** - les deux garde-fous du job, mis a l'epreuve avant usage :

```
Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif.
Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu.
```

Le jugement du sujet de commit lui-meme ne peut etre rendu qu'apres l'ecriture du commit ; le sujet
porte `[THEO]`, prefixe de la table de `squad/README.md`, section 8.

**`outils`** - les trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`,
`outils/*-test.py`), aucun balayage vide :

```
9 juge(s) pur(s) execute(s), tous verts.
```

**Ce que ces trois jobs ne remplacent pas** : ils ne rejouent pas `mvn verify`, et `mvn verify` ne
les lance pas. C'est la confusion exacte qui a laisse le job `chemins` rouge un sprint entier a s30.

### 7.1 Les deux garde-fous qui jugent **ce fichier**, et un vert qui ne prouvait rien

Ce fichier cite des identifiants `ADR-`, `DEC-` et des identifiants de condition. Deux garde-fous
les jugent : `ToutesLesReferencesDeDecisionResolventTest` (`ADR-0066`) et
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`ADR-0085`). Lances sur l'arbre qui porte ce
fichier :

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test \
  -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,ToutesLesReferencesDeDecisionResolventTest \
  -Dsurefire.failIfNoSpecifiedTests=false
```

```
Tests run: 3, Failures: 0, ... -- in ToutesLesReferencesDeDecisionResolventTest
Tests run: 7, Failures: 0, ... -- in ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

**Le premier essai etait un faux vert, et je l'ecris parce qu'il est de la famille que ce depot
sanctionne.** J'avais separe les noms de classes par `+` au lieu d'une virgule. Surefire n'a
selectionne **aucune** classe ; `-Dsurefire.failIfNoSpecifiedTests=false` a rendu ce vide legitime ;
`BUILD SUCCESS` s'est affiche. Sans `clean`, les rapports `.txt` du `verify` precedent etaient
encore sur le disque, **avec leurs anciens temps d'execution** - un lecteur qui aurait cite ces
rapports aurait cite une mesure qui n'avait pas eu lieu. Trouve en comparant les temps ecoules a
ceux du `verify` de la section 1 : ils etaient identiques a la centieme de seconde. **Un total de
tests qui ne bouge pas d'une execution a l'autre est le signe a regarder** ; la commande corrigee
rend 7,356 s et 27,78 s, et ce sont ces chiffres-la qui sont ci-dessus.

## 8. Ce que je n'ai pas fait, et pourquoi

- **Aucun `ADR` ecrit, aucune regle ArchUnit ecrite.** Le declencheur de chacun est ecrit dans
  l'avis (sections 5.2 et 6.6) et **aucun n'est tire** par les six lots integres.
- **Aucun fichier du code, du contenu, de la matrice, du backlog ou du registre touche.** Les deux
  mutations de la section 4.2 ont ete restaurees a l'octet pres avant ce commit.
- **Je n'ecris pas dans `review.md`.** La ceremonie n'est pas ouverte ; `sofia-sm` la tient, et
  cette confirmation y sera reprise par son proprietaire, pas par moi.
- **Je ne prononce aucun Done pedagogique et aucun verdict de contenu.** Les 77 items neufs
  attendent `nadia-prof`.

## Decisions prises

**Aucune decision d'architecture n'a ete prise dans ce fichier.** Il confirme, sur l'arbre integre,
un verdict deja rendu le 2026-09-23 : aucun `ADR` neuf, aucune regle ArchUnit neuve. La seule
ecriture neuve est la correction d'un ecart de mon propre avis (section 3.6, trois fichiers partages
annonces, quatre reels), qui ne change aucun verdict.
