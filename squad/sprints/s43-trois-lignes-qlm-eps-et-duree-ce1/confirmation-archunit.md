# Confirmation ArchUnit - s43 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-25.
- **Objet** : confirmer, sur l'arbre final de `round-two` une fois les **trois** lots de s43
  reellement fusionnes, ce que mon avis de faisabilite du meme jour concluait sur un arbre qui ne
  les portait pas encore (`5bd99d74`), et **re-verifier moi-meme** les deux chiffres que mes propres
  `ADR` ont poses la veille du developpement.
- **Perimetre mesure** : `round-two` a **`9e1622dc`**, worktree dedie `s43-theo-confirmation`
  branche depuis ce commit (amendement s18 et s21 : une branche dediee nommee pour un agent implique
  un worktree dedie du meme nom ; toute mesure du reacteur depuis son propre worktree).
- **Version** : **JDK 21.0.10**, telle que **la machine virtuelle des tests la rapporte d'elle-meme**
  et jamais telle qu'une entree de toolchain la declare (`ADR-0071`) : 20 lignes
  *"Starting ... using Java 21.0.10"* dans le journal du reacteur. Maven 3.9.9.
- **Nom du fichier** : meme nom qu'a s42 et qu'aux quatorze sprints precedents,
  `confirmation-archunit.md`. Un avis de faisabilite n'est pas une des quatre ceremonies de
  `squad/README.md` section 4 ; aucune regle de nommage neuve n'est inventee ici.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Les tests ArchUnit restent-ils verts sur le code neuf des trois lots ? | **oui**, et la reponse exacte est la meme qu'a s41 et s42 : **les trois lots ne portent aucune ligne de Java de production** (section 2). `mvn -B clean verify` sur la racine : **`BUILD SUCCESS`**, 6 modules sur 6, **1775 tests, 0 echec** |
| 2. `ADR-0101` (plafond de dette de longueur au CE1, 4) a-t-il ete depasse par `S43-MAT-DUREE` ? | **non**, et ce n'est pas une lecture de vert : le paquet neuf est **dans la population** du garde-fou, borne a zero, avec **19 mots au maximum** sur ses 24 enonces. Prouve par mutation sur le paquet reel, section 3 |
| 3. `ADR-0102` (lecture des durees) fonctionne-t-il sur le contenu neuf ? | **oui**. La population que la clause examine passe de **72 a 144** propositions, dont les **72** du paquet neuf, et **aucune** n'echappe a la lecture. Prouve par mutation, section 4 |
| 4. Un `ADR` neuf ou une regle ArchUnit neuve est-il du au-dela de ce qui est fusionne ? | **non**. Compte du depot **inchange** : **36 `static final ArchRule`, 35 `@ArchTest`**, meme methode et meme chiffre qu'a s42. Les deux discriminants de `nadia-prof` sont **encodes en tests**, pas tenus a la main (section 5) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique, ne ferme aucune
ligne de matrice, ne juge aucun contenu. La porte pedagogique est a `nadia-prof` seule.

**Ce que cette confirmation ne couvre pas, et je le nomme plutot que de le deduire** (`CLAUDE.md`,
amendement du 2026-09-08) : `mvn verify` **n'est que le contenu du job `build`** de
`.github/workflows/ci.yml`, qui en porte quatre. Les trois autres sont lances a part et rapportes en
section 6. **Je n'annonce pas la porte technique du sprint** - c'est `sofia-sm` qui la prononce en
`review.md`.

## 1. La commande, sa sortie, ses comptes

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur **complet depuis la racine** - la question du `-am` ne se pose donc pas ici, elle ne
concerne que les mesures partielles (`CLAUDE.md`, prerequis de build). Les quatre mesures partielles
des sections 3 et 4 portent toutes `-am` et nomment leur module.

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  0.875 s]
[INFO] recreo-domain ...................................... SUCCESS [ 20.452 s]
[INFO] recreo-application ................................. SUCCESS [  7.477 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:20 min]
[INFO] recreo-adapter-content ............................. SUCCESS [01:02 min]
[INFO] recreo-bootstrap ................................... SUCCESS [03:52 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  06:45 min
[INFO] Finished at: 2026-09-25T17:38:51+02:00
```

**Comptes, avec leur methode dans le meme paragraphe.** Methode autoritaire de `squad/README.md`,
section 9 (amendement s11) : somme des blocs `[INFO] Results:` du reacteur apres `clean` complet,
**jamais** la somme des lignes `Tests run:` des rapports `.txt`. Perimetre : les six modules du
reacteur, 0 echec, 0 erreur, 0 ignore sur les six.

| Module | Tests | Ecart avec s42 |
|---|---|---|
| `domain` | 120 | 0 |
| `application` | 17 | 0 |
| `adapter-web` | 400 | 0 |
| `adapter-content` | 944 | **+37** |
| `bootstrap` (surefire) | 288 | **+11** |
| `bootstrap` (failsafe) | 6 | 0 |
| **total** | **1775** | **+48** |

**Recoupe par une seconde methode independante** : somme des attributs `tests="..."` des **239**
rapports XML de surefire et failsafe, **1775** sur **239** classes distinctes. Les deux methodes
concordent.

**D'ou viennent les +48**, verifies un a un par comparaison des `@Test` et `@ParameterizedTest` de
chaque fichier modifie entre `cdf0229f` (arbre final de s42) et `9e1622dc` :

| Classe | s42 | s43 | Origine |
|---|---|---|---|
| `PaquetCe1ProblemesDureesTest` | 0 | 11 | `S43-MAT-DUREE`, neuve |
| `PaquetCm1EpsEffetsActiviteTest` | 0 | 10 | `S43-EPS-EFFETS-ACTIVITE`, neuve |
| `PaquetCpQlmObjetsNumeriquesTest` | 0 | 7 | `S43-QLM-NUMERIQUE`, neuve |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | 23 | 28 | les cinq tests d'`ADR-0102` |
| `PaquetCm1HistoireGeographieTest` | 29 | 31 | hors des trois lots |
| `PaquetCpDeplacementsQuadrillageTest` | 6 | 8 | hors des trois lots |
| `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` | 0 | 8 | les huit tests d'`ADR-0101`, neuve |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | 7 | 10 | hors des trois lots |

11 + 10 + 7 + 5 + 2 + 2 = **37** pour `adapter-content` ; 8 + 3 = **11** pour `bootstrap`. Les deux
sous-totaux valent exactement les ecarts mesures par les blocs `Results:`, ce qui est le recoupement
que je veux avant de citer un ecart.

### 1.1 La relance apres fusion de `round-two` a `e50f0dd7`

`round-two` a avance pendant que je mesurais : `cdf08c0c` (`camille-po`) y a ouvert trois fiches de
conformite. Diff apporte, verifie et non suppose : **trois fichiers, tous sous `squad/conformite/`,
258 lignes**, et `git diff --stat` restreint a l'arbre hors `squad/` et `docs/` rend **vide**. Aucun
fichier de code ni de contenu.

Je relance quand meme le reacteur complet dans ce meme worktree, parce que *"un diff de `squad/` ne
peut rien casser"* est une deduction et non une mesure :

```
[INFO] recreo-domain ...................................... SUCCESS [ 23.211 s]
[INFO] recreo-application ................................. SUCCESS [  9.018 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:10 min]
[INFO] recreo-adapter-content ............................. SUCCESS [ 56.797 s]
[INFO] recreo-bootstrap ................................... SUCCESS [02:55 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  05:36 min
```

Blocs `[INFO] Results:` : **120 / 17 / 400 / 944 / 288 / 6**, soit **1775**, 0 echec - **identiques**
a ceux du premier lancement. **Tous les verdicts de ce fichier valent donc sur les deux arbres**, et
les chiffres cites restent ceux du premier lancement.

## 2. Question 1 - les tests ArchUnit sur le code neuf des trois lots

### 2.1 Le fait qui rend la question plus simple qu'elle n'en a l'air

Diff complet de `9e388a11` (mon commit d'`ADR`) a `9e1622dc` (arbre final), hors `squad/` et
`docs/` - c'est-a-dire **exactement ce que les trois lots ont ecrit** :

```
 .../src/main/resources/catalogue.yaml              |  18 ++
 .../resources/contenu/ce1-problemes-durees.yaml    | 184 ++++++++++++
 .../resources/contenu/cm1-eps-effets-activite.yaml | 160 ++++++++++
 .../contenu/cp-qlm-objets-numeriques.yaml          |  89 ++++++
 ...eMathematiquesDeCe1DeclareSaSousPartieTest.java |   7 +-
 ...neReponseNEstPasPredictibleSurUnPaquetTest.java |  22 +-
 .../content/PaquetCe1ProblemesDureesTest.java      | 278 ++++++++++++++++
 .../content/PaquetCm1EpsEffetsActiviteTest.java    | 326 +++++++++++++++++++
 .../content/PaquetCpQlmObjetsNumeriquesTest.java   | 189 ++++++++++++
 ...mpteSurLeLotDeMatiereEtNonSurUnFichierTest.java |  10 +-
 .../TexteAccentueExposeParLePortEntrantTest.java   |  45 ++-
 11 files changed, 1321 insertions(+), 7 deletions(-)
```

**Zero fichier sous un `src/main/java`.** Les trois lots sont trois paquets YAML, trois entrees de
catalogue et leurs tests. Aucune classe, aucun paquetage, aucune dependance, aucune annotation
Spring neuve : les regles ArchUnit - qui lisent du bytecode - n'ont **structurellement** rien de neuf
a juger. C'est ce que `ADR-0096` decide (*"la repartition par forme est la regle, le paquet statique
est sa consequence"*) et c'est ici la troisieme fois de suite qu'un sprint entier de contenu se paie
sans une ligne de Java de production.

Les trois jeux declarent `mecanique: bandit-manchot`, deja servie : aucune mecanique neuve, donc
aucune reprise des regles de decouplage mecanique/contenu.

### 2.2 Ce que le reacteur a reellement execute

Les 30 classes de `bootstrap/src/test/java/com/recreo/archi` et `AssertionsWebTest` sont toutes
passees dans le `BUILD SUCCESS` de la section 1. Aucune n'a ete modifiee par les trois lots : le
diff ci-dessus ne contient **aucun** fichier de `com/recreo/archi`.

Les garde-fous qui, eux, **voient** le contenu neuf et l'ont juge vert :
`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` (aucune cle de schema neuve),
`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (le paquet de CP entre, borne a zero),
`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (section 3),
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (section 4),
`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` (`ADR-0098`, le paquet des durees declare
sa sous-partie), `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`.

## 3. Question 2 - `ADR-0101` n'a pas ete depasse, et je ne reprends pas l'affirmation de `sofia-sm`

### 3.1 Le cliquet est intouche, verifie dans le diff et non dans un rapport

`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest.java` **n'apparait pas** dans le diff de la
section 2.1. `DETTE_DE_LONGUEUR = { ce1-longueurs.yaml : 4 }`, `PLAFOND_DE_DETTE = 4` et
`PLAFOND_DE_DETTE_DE_FORME = 0` sont, caractere pour caractere, ceux du 2026-09-25. **Personne n'a
releve le plafond pour faire passer le paquet** - c'est la premiere chose a exclure, et elle
s'exclut par le diff, pas par un vert.

### 3.2 Le paquet neuf est dans la population, et c'est mesure

| Mesure | Valeur | Methode |
|---|---|---|
| Paquets de CE1 servis | **8** (7 a s42 + `ce1-problemes-durees`) | balayage des YAML servis dont le descripteur porte `niveau: "CE1"` |
| Enonces de CE1 servis | **168** (144 + 24) | le garde-fou lui-meme, assertion `hasSizeGreaterThan(100)` momentanement portee a `hasSize(-1)` pour lui faire **imprimer** sa population : *"Expected size: -1 but was: 168"* |
| Enonces de `ce1-problemes-durees` au-dela de 20 mots | **0** | comptage sous la convention d'`ADR-0089` (jetons separes par des blancs et portant au moins une lettre ou un chiffre) |
| Mot le plus long de ses 24 enonces | **19** | meme convention ; distribution : 15, 16, puis six a 17, douze a 18, cinq a 19 |

Les deux methodes de comptage de population - la mienne en dehors du garde-fou, celle du garde-fou
qu'on force a parler - rendent **168** toutes les deux.

**La marge est d'un mot sur cinq enonces.** Ce n'est pas un reproche, c'est un fait que le prochain
paquet de CE1 doit connaitre : la barre est tenue, elle n'est pas confortable.

### 3.3 Prouve par mutation sur le paquet reel, et non par lecture du vert

Un garde-fou vert sur un paquet qu'il ne voit pas est exactement le defaut qui a cree `ADR-0101` :
`ce1-longueurs` a franchi une barre arbitree pendant quatre sprints sans qu'aucun rouge ne le dise,
parce qu'un garde-fou borne au CP **ne le voyait pas**. Je ne pouvais donc pas me contenter d'un
`BUILD SUCCESS`.

Mutation : huit mots ajoutes a la premiere phrase de l'item 1 de `ce1-problemes-durees.yaml`, qui
passe de 18 a 26 mots. Le paquet est sinon intact, et le mutant a ete **annule** avant tout commit
(`git checkout --`, arbre propre verifie).

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test \
  -Dtest=AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest
```

```
[ERROR] Tests run: 8, Failures: 2, Errors: 0, Skipped: 0
[ERROR]   ...leContenuServiDeCe1EstConforme
  Expecting empty but was: ["ce1-problemes-durees.yaml : 1 enonces depassent 20 mots la ou la
  dette en tolere 0 - item 1 (26 mots). Au CE1, un enonce compte au plus 20 mots
  (LON-CE1-1, nadia-prof, 2026-09-21 ; ADR-0101)"]
[ERROR]   ...lesDeuxBarresNeMesurentPasLaMemeChose
  [la barre du CE1 n'en rend que la dette enregistree]
```

**Ce que cette mutation prouve, et que le vert seul ne prouvait pas** : le paquet de `S43-MAT-DUREE`
est bien **dans** la population, il y est **borne a zero** (la dette ne le nomme pas), et le compte
global d'enonces de CE1 au-dela de 20 mots vaut **exactement 4** - c'est la seconde assertion
rougie, celle qui compare le total au plafond. Un vingt-et-unieme mot, dans ce paquet-ci, fait deux
rouges.

**Verdict de la question 2 : le plafond de 4 n'est pas depasse, et il n'a pas pu l'etre sans
rougir.**

## 4. Question 3 - `ADR-0102` sur le contenu neuf

### 4.1 Ce que le paquet ecrit, et pourquoi ce n'est pas l'ecriture qu'`ADR-0102` a ajoutee

`DUR-CE1-3` autorisait deux ecritures ; `nadia-prof` a tranche pour `H h MM min` en clause 4 de
`DUR-CE1-7`, parce que c'est la seule qui garde l'erreur de base 100 ecrivable. Le paquet n'ecrit
donc **aucune** *"heure et demie"* : le motif `DUREE_ET_DEMIE` ajoute par `ADR-0102` n'a, sur ce
paquet, **aucun materiau**.

Je l'ecris parce que c'est le genre de fait qu'un rapport arrondit : la moitie d'`ADR-0102` qui a
paye ce sprint n'est pas le motif, **c'est la clause**. L'autre moitie garde une population vide sur
ce paquet-ci - elle reste juste, elle reste testee par `lesDeuxEcrituresDeDureeSeLisentEnMinutes`,
et elle attendra le premier paquet qui ecrira la demi-heure en toutes lettres.

### 4.2 La clause, elle, a double sa population et elle est verte

| Mesure | s43 | Avant les lots | Methode |
|---|---|---|---|
| Propositions servies qui **ressemblent** a une duree ou a un instant | **144** | 72 | le test `laClauseDeLectureDesDureesExamineUnePopulationReelle`, assertion `isGreaterThan(50)` momentanement portee a `isEqualTo(-1L)` pour lui faire imprimer son compte : *"expected: -1L but was: 144L"* |
| Parmi elles, celles du paquet neuf | **72** | - | 24 items x 3 propositions, toutes en `H h MM min` ou `MM min` |
| Celles qui **echappent** a la lecture | **0** | 0 | `aucuneEcritureDeDureeDeProductionNEchappeALaLecture`, vert dans le reacteur complet |

La clause a donc **double** le materiau qu'elle examine en un sprint, ce qui est le seul argument
qui compte contre le reproche d'`ADR-0051` (une clause verte sur une population qui se vide).

**Les deux distracteurs de base 100 sont lus comme ce qu'ils sont.** `1 h 70 min` et `2 h 70 min`
- les seules propositions du depot dont les minutes depassent 59, posees par `DUR-CE1-7` clause 5 -
sont lues **130** et **190** minutes par `DUREE_HEURES`, et non `1` et `2`. Sans `ADR-0102` la
question ne se serait pas posee sur ces deux-la ; elle se pose sur toute ecriture non prevue, et
c'est le point suivant.

### 4.3 Prouve par mutation sur le paquet reel

Mutation : le distracteur `1 h 70 min` de l'item 1 remplace par `2 heures et quart` - l'ecriture
qu'`ADR-0102` a **delibere** de ne pas ajouter aux motifs. Mutant annule avant tout commit.

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test \
  -Dtest='LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest#...'
```

```
[ERROR] Tests run: 2, Failures: 2, Errors: 0, Skipped: 0
  [ADR-0102 : ce qui ressemble a une duree se lit comme une duree, ou est rouge]
  Expecting empty but was: ["paquet ce1-problemes-durees, item item-0 : la proposition
  "2 heures et quart" ecrit une duree ou un instant que la lecture de valeur ne reconnait
  pas - elle serait lue comme le nombre nu 2, donc comparee a des minutes, et le rang
  mesure ne serait pas celui qui est servi (ADR-0102)"]
```

La clause **nomme le paquet neuf, l'item et la valeur fautive**. C'est la preuve que je voulais : sur
le contenu de s43, elle ne passe pas en regardant ailleurs.

### 4.4 Ce dont dependait `ADR-0102`, remesure a la main

`ADR-0102` existe pour une seule raison : que le taux de rang de `DUR-CE1-5` mesure **l'ordre que
l'enfant voit**. J'ai donc recalcule cet ordre en dehors du garde-fou, en rejouant les motifs de
lecture sur les 24 items du paquet :

| Rang de la bonne reponse | Items | Part | Borne |
|---|---|---|---|
| la plus petite | 10 | 41,7 % | < 50 % |
| intermediaire | 9 | 37,5 % | dans [10 %, 50 %] |
| la plus grande | 5 | 20,8 % | > 10 % |

Les trois rangs tiennent `SEUIL_MINIMUM = 0.10` et `SEUIL_MAJORITE = 0.5`, et ces chiffres sont ceux
que le commentaire du paquet annonce. Deux calculs independants, un seul resultat.

**Verdict de la question 3 : `ADR-0102` fonctionne sur le contenu neuf - non pas au sens ou son motif
neuf y sert, mais au sens ou sa clause y examine 72 propositions de plus et rougirait sur la
premiere ecriture qu'elle ne saurait pas lire.**

## 5. Question 4 - aucun `ADR` ni regle ArchUnit neuve n'est du

### 5.1 Le compte de regles, avec sa methode - et la divergence de s42 expliquee

Methode : `git grep -c` sur l'arbre `9e1622dc`, motifs `static final ArchRule` et `@ArchTest`,
extension `.java`, **le faux module de contre-exemple exclu**
(`bootstrap/src/test/resources/contreexemple/...`, qui porte 2 `ArchRule` destinees a etre jugees
fausses par `LesGardeFousSontMisALEpreuveTest`).

| Compte | s42 | s43 |
|---|---|---|
| `static final ArchRule` | 36 | **36** |
| `@ArchTest` | 35 | **35** |

Sans l'exclusion, le motif rend **38** : c'est exactement la premiere des trois causes de divergence
que j'avais nommees en annoncant 29 regles pour 25 reelles. Elle est ecrite ici pour que le prochain
qui recompte trouve la meme chose que moi, et non un chiffre proche.

Fichier par fichier, `git grep -c` rend **la meme table qu'a `cdf0229f`**, ligne pour ligne. Aucune
regle n'a ete ajoutee, deplacee ni supprimee.

### 5.2 Les deux discriminants de `nadia-prof` sont encodes, pas tenus a la main

C'est la seule question de s43 qui pouvait appeler une regle neuve : les deux collisions que mon
avis signalait (sections 6 et 7.2) ont ete tranchees par `nadia-prof` en `EPS-9` et `DUR-CE1-7`, et
un discriminant tenu a la main est precisement ce que ce depot appelle un souhait.

| Clause | Test qui la tient | Fichier |
|---|---|---|
| `EPS-9` clause 1 (les trois taches discriminantes portent au moins deux tiers des items) | `eps9Clause1LesTroisTachesDiscriminantesPortentAuMoinsDeuxTiersDesItems` | `PaquetCm1EpsEffetsActiviteTest` |
| `EPS-9` clause 2 (aucun enonce ne reprend un patron deja servi au CP) | `eps9Clause2AucunEnonceNeReprendUnPatronDejaServiAuCp` | idem |
| `EPS-9` clause 3 (aucune bonne reponse du CP ne devient un distracteur ; aucun item ne demande de nommer un organe) | `eps9Clause3AucuneBonneReponseDuCpNeDevientUnDistracteurIci`, `eps9Clause3AucunItemNeDemandeDeNommerUnOrgane` | idem |
| `DUR-CE1-7` clause 1 (aucun indice ne reprend un indice de `cm1-problemes-durees`) | `aucunIndiceNeReprendUnIndiceDeCm1` | `PaquetCe1ProblemesDureesTest` |
| `DUR-CE1-7` clause 2 (au moins un tiers du paquet porte une duree de 2 h ou plus) | `auMoinsUnTiersDuPaquetPorteUneDureeDeDeuxHeuresOuPlus` | idem |
| `DUR-CE1-7` clause 3 (aucun prenom de `cm1-problemes-durees` repris) | `aucunEnonceNeReprendUnPrenomDeCm1` | idem |
| `DUR-CE1-7` clause 5 (au moins deux distracteurs a minutes superieures a 59) | `auMoinsDeuxDistracteursPortentDesMinutesSuperieuresA59` | idem |

**Les deux discriminants comparent le paquet neuf au paquet deja servi**, dans les deux cas : c'est
ce qui fait qu'ils interdisent reellement quelque chose plutot que de decrire le paquet qu'on vient
d'ecrire. Une regle ArchUnit n'y ajouterait rien - ce sont des proprietes de contenu YAML, pas de
bytecode, et `ADR-0101` comme `ADR-0102` ont deja acte ce partage.

Un point que je nomme sans l'imputer a personne : `DUR-CE1-7` clause 4 (le choix du registre
d'ecriture) n'a pas de test qui lui soit propre. Elle n'en a pas besoin - elle est une **consequence**
de `lesInstantsSontAbregesEtLesDureesPortentToujoursLeMotMin`, qui interdit toute autre ecriture sur
ce paquet. Une clause qui est deja la consequence d'une autre n'est pas une dette.

### 5.3 Les cliquets, relus dans le code de l'arbre final

| Cliquet | Valeur a `5bd99d74` (mon avis) | Valeur a `9e1622dc` | Effet des trois lots |
|---|---|---|---|
| Dette de longueur d'enonce au CP | `PLAFOND_DE_DETTE = 5`, forme 0 | identique | aucun - le paquet de CP entre borne a zero |
| Dette de longueur d'enonce au CE1 (`ADR-0101`) | 4, forme 0 | **identique** | aucun - section 3 |
| Dette de rang | `PLAFOND_DE_DETTE = 2`, seuil minimum 3 | identique | aucun |
| Dette de declaration de ligne | 0 | identique | aucun |
| Poids des dessins, des reproductions | inchanges | identiques | aucun - aucun fichier sous `static/illustrations/` |
| Paquets de designation en production | 2 et 2 | identiques | aucun - trois paquets de bandit manchot a choix textuel |
| `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | table sans plafond | **+2 entrees** | attendu et conforme : `cp-qlm-objets-numeriques` (33 propositions) et `cm1-eps-effets-activite` (39), chacune avec son compte **mesure**. `S43-MAT-DUREE` ne s'y declare pas, ce qui est le cas nominal |

Aucun plafond n'a bouge. La seule table qui grossit est celle qui n'a deliberement pas de plafond, et
elle grossit du nombre exact de paquets textuels ajoutes.

### 5.4 `squad/decisions/` : ce que s43 y a ecrit

`ADR-0101` et `ADR-0102`, tous deux **fusionnes avant le developpement** (`9e388a11`), tous deux
`Accepte`, tous deux presents dans `ADR-index.md`. Aucun `ADR` n'a ete ecrit apres, et aucun n'est
du : les trois lots n'ont produit aucune decision d'architecture, seulement du contenu et ses tests.

**Verdict de la question 4 : non, rien n'est du.**

## 6. Les trois autres jobs de la CI, lances sur mon propre diff

`mvn verify` n'est que le job `build`. Les trois autres, lances ici avant de commiter :

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert, ce fichier-ci inclus dans le balayage |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | vert |
| `outils` | les trois motifs de juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | vert |

Les sorties exactes sont en section 6.1.

**Pourquoi je nomme ce fichier-ci dans le job `chemins`** : les mesures des sections 3 et 4 ont
produit des journaux qui contiennent le chemin du JDK resolu par l'enveloppe. **Aucun n'est recopie
ici** : les blocs cites sont tronques aux lignes d'assertion, et la version du JDK est nommee sans
son emplacement, comme `CLAUDE.md` l'exige. C'est exactement le genre de fuite qu'un rapport de
ceremonie produit quand il colle une sortie brute.

### 6.1 Les sorties

`chemins`, les deux etapes, la seconde sur `git ls-files` de cet arbre, ce fichier-ci deja indexe :

```
ok un fichier sans chemin de poste de travail est accepte
ok un dernier motif sans retour a la ligne final est bien juge
ok aucun fichier a juger n'est pas un echec
ok un fichier disparu de la liste n'est pas un echec
Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif.

Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.
```

`prefixes`, les trois etapes. La derniere est lancee sur le **sujet du commit que ce fichier va
porter**, et je le dis plutot que de laisser croire a une plage de commits : la plage
`round-two..HEAD` est vide tant que le commit n'existe pas, et un garde-fou sur une population vide
est un vert qui ne prouve rien - c'est le defaut que ce depot sanctionne le plus souvent.

```
ok une ligne vide n'est pas un commit fautif
ok un dernier sujet sans retour a la ligne final est bien juge
ok aucun commit a juger n'est pas un echec
Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif.

ok un sha inconnu du depot est refuse
ok une chaine vide est refusee
ok un sha qui existe mais n'est pas un commit (un arbre) est refuse
Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu.

Tous les commits portent un prefixe d'agent connu.
```

`outils`, les trois motifs de balayage, aucun vide :

```
--- outils/mesurer-rendu-test.sh
--- outils/mvn-jdk21-test.sh
--- outils/rapport-tokens-test.sh
--- outils/verifier-jdk-test.sh
--- outils/verifier-lignee-test.sh
--- squad/outils/nadia-prof/balayage-largeur-rangee-test.sh
--- squad/outils/sofia-sm/verifier-provenance-lots-test.sh
--- outils/rapport-tokens-horodatage-test.py
--- outils/rapport-tokens-worktree-test.py
9 juge(s) pur(s) execute(s).
```

## 7. Ce que je n'ai pas fait, et pourquoi

- **Je ne prononce aucun Done pedagogique**, ni sur les trois paquets, ni sur aucun item. Les
  mesures des sections 3 et 4 disent qu'un garde-fou tient ; elles ne disent rien de ce qu'un enfant
  apprend.
- **Je n'annonce pas la porte technique du sprint.** J'ai lance quatre jobs sur mon propre arbre ;
  la porte se prononce sur la CI, en `review.md`, par `sofia-sm`.
- **Je n'ai corrige aucun contenu.** La marge d'un mot sur cinq enonces de `ce1-problemes-durees`
  (section 3.2) est un fait mesure, pas une demande de reecriture : la decision appartient a
  `nadia-prof`.
- **Je n'ai ouvert aucune dette ni ecrit aucun `ADR`.** La seule chose que s43 m'a appris et qui
  merite d'etre retenue est en section 4.1 - la moitie d'`ADR-0102` qui a servi n'est pas celle que
  l'`ADR` met en avant dans son titre.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas. Les deux decisions de s43 sont `ADR-0101` et
`ADR-0102`, prises avant le developpement.
