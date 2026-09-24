# s41 - Avis de faisabilite (`theo-archi`) - six lots d'EMC CP, de questionner le monde CP et de sciences CM1

- **Date** : 2026-09-23
- **Base** : `round-two` a `998b9992`, arbre de travail a ce meme commit, aucun diff de code ni de
  contenu dans cet avis.
- **Perimetre de cet avis** : les **six** lots que `DEC-0095`, section 9.6, retient pour s41, sur
  deux terrains et deux instances de `nadia-prof` :
  - **terrain 1, EMC** : `S41-EMC-SYMBOLES` (`EMC-CP-08`), `S41-EMC-DROITS` (`EMC-CP-10`),
    `S41-EMC-ROLES` (`EMC-CP-13`) ;
  - **terrain 2, sciences CM1 et questionner le monde CP** : `S41-QLM-HYGIENE` (`QLM-CP-04`),
    `S41-QLM-SAISONS` (`QLM-CP-13`), `S41-SCI-CLASSER` (`SCI-CM1-06`).
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. Il ne fixe aucun contenu, aucun
  critere d'acceptation (`camille-po`), aucun ordre ni aucune affectation nominative (`sofia-sm`),
  et le Done pedagogique reste a `nadia-prof` seule. Les six lignes sont deja instruites
  pedagogiquement (`DEC-0095`, section 5) : je n'ouvre aucune question de contenu, je constate ce
  que le code et les garde-fous permettent aujourd'hui.

---

## 1. Verdicts

| Lot | Ligne | Verdict | Complexite |
|---|---|---|---|
| `S41-EMC-SYMBOLES` | `EMC-CP-08` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve, **a condition que la ligne reste en texte seul** - une version illustree est un lot d'une autre nature, section 4.2 | basse en texte |
| `S41-EMC-DROITS` | `EMC-CP-10` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S41-EMC-ROLES` | `EMC-CP-13` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S41-QLM-HYGIENE` | `QLM-CP-04` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve. Une adjacence de contenu **avec du contenu deja servi** est nommee en section 6.4 ; elle n'est gardee par aucun automate | basse |
| `S41-QLM-SAISONS` | `QLM-CP-13` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S41-SCI-CLASSER` | `SCI-CM1-06` | **faisable sous condition** : la clause `BIN-1` de `nadia-prof` nomme explicitement cette ligne, et **elle n'a aucun garde-fou transverse** - elle n'existe aujourd'hui que dans le test d'un seul paquet. La condition est d'encoder `BIN-1` dans le test du paquet neuf, section 6.6 | basse sur le mecanisme, moyenne sur le garde-fou |

**Aucun `ADR` neuf n'est du sur les six lots. Aucune regle ArchUnit neuve n'est due.** Les six sont
des paquets statiques de type `question` joues au bandit manchot, dans trois lots de matiere et de
niveau **deja servis**.

## 2. Verification des citations de `DEC-0095` - ouvertes, pas reprises de confiance

`DEC-0095` cite six lignes de la matrice et six passages d'arbitrages prealables. **Je les ai
ouverts aux lignes citees.** Aucun garde-fou ne verifie un numero de ligne : `ToutesLesReferencesDeDecisionResolventTest`
(`ADR-0066`) resout les identifiants `ADR-` et `DEC-`, jamais une citation de ligne dans un fichier
de conformite. La verification est donc a la main, ici comme a s40.

| Code | Ligne de `MATRICE-couverture.md` citee | Resolue ? | Arbitrage cite | Resolu ? |
|---|---|---|---|---|
| `EMC-CP-08` | 1860 | oui, statut `non couvert` | `ARBITRAGE-PREALABLE-s31`, ligne 234 | oui, ligne `EMC-CP-08` du tableau de la section 3.1 |
| `EMC-CP-10` | 1862 | oui, statut `non couvert` | idem, ligne 236 | oui |
| `EMC-CP-13` | 1865 | oui, statut `non couvert` | idem, ligne 237 | oui |
| `QLM-CP-04` | 1485 | oui, statut `non couvert`, **sans annotation** | `ARBITRAGE-PREALABLE-s29`, ligne 370 | oui |
| `QLM-CP-13` | 1494 | oui, statut `non couvert`, **sans annotation** | `ARBITRAGE-PREALABLE-s29`, ligne 368 | oui |
| `SCI-CM1-06` | 2006 | oui, statut `non couvert`, avec sa mention de couverture partielle | `ARBITRAGE-PREALABLE-s31`, section 4.3, ligne 303 | oui, rang *"les suivantes"*, *"6 (classement, mention partielle due)"* |

**Un ecart que je nomme parce qu'il change une phrase de `DEC-0095` sans changer son verdict** :
la section 9.4 motive `S41-QLM-SAISONS` par *"terrain deja eprouve par `cp-semaine`"*. C'est exact
comme precedent de **forme d'item**, et il faut savoir que `cp-semaine` n'est pas un paquet de
questionner le monde : son descripteur porte `matiere: "Mathématiques"`, `domaine: "Grandeurs et
mesures"`, et il declare `MAT-CP-09`, pas une ligne `QLM`. La consequence est favorable au lot -
`QLM-CP-13` n'est declaree par **aucun** paquet servi, donc aucune allocation de sous-partie n'est
due (section 5.2) - mais la phrase ne doit pas etre lue comme *"le lot de questionner le monde CP
porte deja cette ligne en partie"*.

## 3. Le mecanisme est deja la, et le precedent est un commit sans une ligne de Java de production

Precedent verifie : **`671f9379`** (s40, `[MARC]`, `S41-EMC-DROITS` a exactement la meme forme).
Son diff, tel que `git show --stat` le donne :

```
 .../src/main/resources/catalogue.yaml              |   6 +
 .../src/main/resources/contenu/cp-emc-devise.yaml  |  85 +++++++++++++
 ...neReponseNEstPasPredictibleSurUnPaquetTest.java |  10 +-
 .../adapter/content/PaquetCpEmcDeviseTest.java     | 136 +++++++++++++++++++++
 .../TexteAccentueExposeParLePortEntrantTest.java   |  12 +-
 5 files changed, 247 insertions(+), 2 deletions(-)
```

**Zero ligne de Java de production.** Un fichier de contenu, une entree de catalogue, un test de
paquet, deux inventaires partages etendus. C'est la forme attendue des six lots de s41, et c'est
aussi ce qui fait le risque de parallelisme de la section 7 : **trois des cinq fichiers de ce diff
sont partages par tous les lots.**

Les trois lots de matiere et de niveau vises existent deja. Mesure du 2026-09-23 sur les **90**
fichiers de `adapters/adapter-content/src/main/resources/contenu/`, comptes par la `matiere` et le
`niveau` de leur descripteur, un paquet par fichier, `target/` sans objet :

| Lot de matiere vise | Paquets servis aujourd'hui | Precedents directs |
|---|---|---|
| Enseignement moral et civique / CP | 7 | `cp-emc-devise` (`EMC-CP-09`, s40), `cp-emc-regles-de-vie`, cinq autres |
| Questionner le monde / CP | 7 | `cp-qlm-vivant-non-vivant` (`QLM-CP-01`), `cp-qlm-cycle-de-vie` (`QLM-CP-02`), cinq autres |
| Sciences et technologie / CM1 | 11 | `cm1-sciences-nutrition` (`SCI-CM1-09`, s40), `cm1-sciences-chaine-alimentaire`, neuf autres |

**Aucune matiere neuve, aucun niveau neuf, aucune mecanique neuve.**

## 4. Question 1 - forme d'item, illustration et palier visuel, ligne par ligne

### 4.1 Le critere, et il n'est pas de moi

`ADR-0058`, decision 1, et `ADR-0073`, clause 6 : **une forme d'item de plus se justifie quand la
facon dont l'enfant repond change.** Elle a change entre choisir et ecrire (`Question` /
`ReponseAEcrire`), et entre choisir un bouton et designer une zone sur une image (`ZoneADesigner`).
Elle ne change pas parce que l'enfant regarde autre chose avant de choisir : ce cas-la est une
**composante optionnelle de `Question`**, que la mecanique transporte sans la lire.

`Question` porte douze composantes, dont huit optionnelles : `indice`, `explication`,
`partDesignee`, `texteSupport`, `illustration`, `illustrationTexteAlternatif`, `texteAVoixHaute`,
`tableauSupport`. **Les six lignes de s41 se posent toutes dans la forme `question` a choix
textuel, avec `indice`, sans aucune autre composante.**

### 4.2 `EMC-CP-08` est la seule des six ou la question de l'illustration se pose vraiment, et elle est deja tranchee contre

La ligne dit *"reconnaitre et nommer"* les symboles, ce qui appelle naturellement une image du
drapeau ou de Marianne. **`nadia-prof` a ecarte cette voie d'avance**, `ARBITRAGE-PREALABLE-s31`,
section 3.1 et refus d'avance numero 8 : `ART-1`, *"aucune reponse portee par une couleur
percue"*, *"on demande le **nom**"*. La matrice, ligne 1860, porte la meme annotation. **Le lot est
donc du texte seul, et c'est un arbitrage pedagogique deja rendu, pas une economie que je propose.**

Ce que couterait la voie illustree, chiffre pour qu'on ne la rouvre pas sans le voir :

1. **Un drapeau dessine** entre dans `static/illustrations/` et deplace le cliquet de poids de
   `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`. Ce cliquet est
   une **egalite exacte**, pas un plafond de confort : `POIDS_TOTAL_MAXIMUM = 51307` et
   `POIDS_D_UN_DESSIN_MAXIMUM = 4633`, tous deux compares avec `isEqualTo`. Tout ajout les fait
   rougir et se paie dans le diff qui installe le fichier, sur la valeur mesuree - jamais sur une
   valeur predite (`ADR-0077`, amendement du 2026-09-03).
2. **Une Marianne** n'est pas un dessin geometrique : c'est une reproduction d'oeuvre, donc la
   troisieme population d'images d'`ADR-0082`, sous `static/oeuvres/`. Le garde-fou
   `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` y tient un
   **cliquet a plafond zero** (`POIDS_TOTAL_MAXIMUM = 0`) et exige la preuve **double** des droits,
   celle de l'oeuvre et celle de sa photographie. `DEC-0075` bloque neuf lignes sur exactement ce
   motif.

Verdict : **texte seul, faisable en l'etat**. Illustree, ce n'est plus un lot de contenu.

### 4.3 Les cinq autres lignes ne demandent aucun support visuel

- `EMC-CP-10` (droits de l'enfant), `EMC-CP-13` (roles ecole et commune) : les deux taches que
  `nadia-prof` decrit sont des questions de connaissance en texte. Aucune composante optionnelle
  au-dela de `indice`.
- `QLM-CP-04` (hygiene de vie) : idem. `cp-eps-corps-effort` sert deja une matiere voisine en texte
  seul, 22 items.
- `QLM-CP-13` (mois et saisons) : la tentation visuelle existe - une frise des mois, sur le patron
  de `designation-frise-de-la-journee`. **Elle coute cher et je la nomme pour qu'on ne la prenne
  pas par defaut** : un troisieme paquet de designation fait rougir deux cliquets exacts,
  `PLAFOND_DE_DESIGNATION_EN_PRODUCTION = 2`
  (`AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`) et
  `PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE = 2`
  (`AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest`), et oblige a
  etendre les trois balayages nommes par `ADR-0080`. **Rien dans la ligne ne l'exige** :
  `ARBITRAGE-PREALABLE-s29`, ligne 368, decrit la ligne en texte (*"nommer, et ordonner - quel mois
  vient apres... ?"*).
- `SCI-CM1-06` (classer des etres vivants) : la matrice, ligne 2006, ecrit *"jouable au choix
  textuel, item par item"*, et precise que le **tri d'une collection entiere** est refuse au
  palier 1 par `V3`. Le palier visuel est donc explicitement hors de la ligne telle qu'elle est
  instruite.

### 4.4 Un moyen neuf depuis s40, qui n'etait pas disponible au sprint precedent

`ADR-0099` (s40) a livre `tableauSupport` : une composante optionnelle de `Question` portant un
en-tete et des lignes, rendue en grille. **`SCI-CM1-06` est la ligne des six ou une grille de
caracteres observables est la forme naturelle**, et elle est desormais disponible **a cout de code
nul** - une cle du schema, lue par le chargeur, rendue par le gabarit, gardee par
`AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`.

**Ce que cela coute en contrepartie, dit avant d'ecrire plutot qu'au premier rouge** : `ADR-0072`
a ete etendu au tableau dans le meme geste (`ADR-0099`, decision 4). `fragmentsDeSupport` traite
desormais **chaque rangee de tableau comme une phrase de support**, et un paquet dont plus de la
moitie des items se gagnent en lisant une seule rangee est rouge. Un paquet de classement dont tous
les items interrogent la meme rangee de la grille est donc rouge, et ce n'est pas un faux rouge.
**Je ne decide pas que ce lot emploie un tableau** : c'est une forme de contenu, donc
`nadia-prof`. Je constate qu'elle est disponible et je nomme son prix.

## 5. Question 2 - les cliquets et les plafonds, mesures et non devines

### 5.1 Les cliquets que six lots de contenu neuf **ne** deplacent **pas**

Tous verifies dans le code a `998b9992`.

| Cliquet | Constante et valeur | Effet de six lots de texte |
|---|---|---|
| Poids des dessins | `POIDS_TOTAL_MAXIMUM = 51307`, `POIDS_D_UN_DESSIN_MAXIMUM = 4633`, egalite exacte | **aucun** - aucun fichier ajoute sous `static/illustrations/` |
| Poids des reproductions d'oeuvres | `POIDS_TOTAL_MAXIMUM = 0` | **aucun** |
| Paquets de designation en production | `PLAFOND_DE_DESIGNATION_EN_PRODUCTION = 2` et `PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE = 2` | **aucun** - les six lots sont du bandit manchot |
| Dette de longueur d'enonce au CP | `PLAFOND_DE_DETTE = 5`, reparti sur trois paquets nommes ; `PLAFOND_DE_DETTE_DE_FORME = 0` | **aucun** - tout paquet de CP absent de la table est borne a **zero**, section 5.3 |
| Dette de declaration de ligne | `PLAFOND_DE_DETTE = 0`, `DETTE_DE_DECLARATION` vide | **aucun** - a condition que chaque paquet declare sa ligne, ce qui est le cas nominal |
| Dette d'amorce d'`ADR-0086` | `PLAFOND_DE_DETTE = 1` | **aucun** tant qu'aucun lot n'ouvre d'exemption |
| Homonymes du registre d'identifiants | `PLAFOND_D_HOMONYMES = 2` | **aucun**, section 5.4 |
| Bornes de tirage des garde-fous | quatre bornes ecrites a la main : 150, 500, 500, 2000 | **aucun** - un paquet de fermeture porte onze a vingt items |

**Aucun plafond existant n'est atteint ni approche par six lots de contenu textuel.** C'est le
resultat de la verification, pas une impression.

### 5.2 Allocation des lignes de referentiel : aucun `ADR` n'est du, et c'est verifiable

Le garde-fou d'`ADR-0081`
(`bootstrap/src/test/java/com/recreo/archi/ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`)
derive les codes admissibles des referentiels de `squad/programme` par le motif `^- \[([^]]+)] .+`,
et le niveau par le nom du fichier qui les declare. **Aucune matiere et aucun code n'y sont
nommes** : un code neuf d'une matiere deja au referentiel est couvert le jour ou il y est ecrit.

Verifie pour les six, sous la forme exacte que le motif exige :

| Code | Referentiel | Ligne |
|---|---|---|
| `EMC-CP-08` | `squad/programme/enseignement-moral-et-civique/competences-cp.md` | 47 |
| `EMC-CP-10` | idem | 49 |
| `EMC-CP-13` | idem | 55 |
| `QLM-CP-04` | `squad/programme/questionner-le-monde/competences-cp.md` | 27 |
| `QLM-CP-13` | idem | 42 |
| `SCI-CM1-06` | `squad/programme/sciences-et-technologie/competences-cm1.md` | 55 |

Les six resolvent. **Et aucune des six n'est declaree par un paquet servi aujourd'hui** : mesure sur
les 90 fichiers de contenu, zero occurrence des six codes. La configuration qui a rendu `ADR-0098`
necessaire au CE1 - plusieurs paquets servant chacun une part de la meme ligne - **n'existe sur
aucun des six lots**. La clause 6 d'`ADR-0081` suffit : la ligne est declaree une fois au niveau du
paquet, chaque item la porte, la ligne declaree est servie par au moins un item.

**Le declencheur qui me ferait changer d'avis, ecrit d'avance** : le jour ou une **seconde** entree
de catalogue declarera l'une de ces six lignes, l'allocation de sous-partie redevient due, par le
raisonnement d'`ADR-0097` decision 2. Ce jour-la, l'`ADR` s'ecrit avant le paquet.

### 5.3 Les cinq lots de CP tombent sous la barre de longueur, sans une ligne a ecrire

`ADR-0089`, `LON-1` et `LON-2`, encodes dans
`bootstrap/src/test/java/com/recreo/archi/AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` :
au CP, un enonce compte au plus **15 mots** et au plus **2 phrases**, la seconde etant la question.
Le niveau est lu dans le descripteur (`NIVEAU_BORNE = "CP"`), jamais dans une liste de paquets :
les cinq paquets de CP de s41 sont dans la population **des leur premier commit**, bornes a zero
enonce trop long.

**Aucune barre equivalente n'existe pour le CM1** : `NIVEAU_BORNE` est la seule constante de niveau
du garde-fou, et le depot n'en porte pas de seconde. `S41-SCI-CLASSER` n'est donc pas contraint sur
cet axe - ce qui n'est pas une permission d'ecrire long, seulement un fait a connaitre.

### 5.4 Les identifiants de condition que les six lots vont citer sont deja alloues

`ADR-0085` / `PAR-5` : tout identifiant de condition ecrit dans le depot doit etre alloue dans
`squad/decisions/REGISTRE-identifiants.md`, et la ligne de compte courant doit valoir le compte
reel de la table (clause 5). Le commentaire d'entete d'un paquet cite ses conditions ; c'est donc
un cout reel du lot.

Verifie ce jour, un a un, sur les identifiants que les six lots citeront : `ART-1`, `BIA-4`,
`EMC-1`, `EMC-6`, `FER-1`, `FER-2`, `FER-3`, `BIN-1`, `PLA-1`, `FAB-1`, `CAT-1`, `SCI-1`, `SCI-4`,
`CYC-1`, `REG-R1`. **Les quinze sont deja alloues, une fois chacun.** Compte courant du registre :
`682` identifiants, `111` prefixes.

**Le seul geste qui reste du** : si `nadia-prof` ouvre une reserve neuve en auditant un lot -
un identifiant de reserve sur le prefixe de la ligne concernee -, cet identifiant doit etre alloue
**et** le compte courant remonte dans le meme diff. C'est le cas nominal, pas un obstacle - mais
c'est aussi le point ou deux instances paralleles se rencontrent, section 7.2.

*Cet avis ne donne d'exemple d'aucun identifiant de reserve possible, et c'est deliberement :
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` ne peut pas distinguer un identifiant cite
en exemple d'un identifiant pose, et l'y ecrire le rendrait rouge. Meme lecon qu'`ADR-0087` et que
la correction de l'avis de s40 : un identifiant se designe, il ne s'epelle pas.*

## 6. Question 3 - obstacles deja documentes, lot par lot

Source : `ARBITRAGE-PREALABLE-s31`, section 3.1 et section 10 (les quinze refus d'avance) ;
`ARBITRAGE-PREALABLE-s29`, lignes 368 et 370 ; `ARBITRAGE-PREALABLE-s31`, section 4.3 et section 5
(les clauses neuves du 2026-09-07) ; `MATRICE-couverture.md`, lignes 1485, 1494, 1860, 1862, 1865,
2006. **Aucun des six lots n'est bloque.** Cinq portent des conditions deja connues, un porte une
condition sans garde-fou.

### 6.1 `EMC-CP-08` - `ART-1`, refus d'avance numero 8

*"Tout item de `EMC-CP-08` dont la reponse est portee par une couleur percue"*, et l'arbitrage nomme
l'endroit ou cela cassera d'abord : *"de quelle couleur est le drapeau ?"*, *"l'item le plus evident
de la ligne et [qui] sera donc le premier ecrit"*. **Aucun garde-fou ne mord `ART-1`** : juger
qu'une reponse repose sur une couleur percue demande de lire le sens. C'est la garde de
`nadia-prof`, et le dire vaut mieux que promettre une regle qui ne tiendrait pas.

### 6.2 `EMC-CP-10` - `CAT-1` (refus 2) et `EMC-1` / `BIA-4` (refus 10)

Deux refus nommes sur cette ligne, et c'est la ligne que `nadia-prof` classe pourtant *"le terrain
le plus sur des cinq a 1,5"*. Les deux sont des conditions de redaction, pas des obstacles de
conception :

- `CAT-1` : *"tout item d'EMC dont la bonne reponse se reconnait a son ton"*, avec la mention que
  *"c'est la pente naturelle de la matiere, elle a deja emporte deux paquets"*. Non encodable.
- `EMC-1` / `BIA-4` : *"as-tu le droit de..."* est refuse d'avance. Non encodable non plus.

Ce qui **est** encode et mordra : `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`
(`ADR-0086`, `FAB-1a` a `FAB-1c` sur le depot entier) et
`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`).

### 6.3 `EMC-CP-13` - `EMC-6` (refus 10)

*"Aucun item ne suppose la commune, l'ecole ou le foyer de l'enfant"*, motive dans l'arbitrage par
un fait d'architecture et non par une preference : *"le produit ne les connait pas et ne peut pas
les connaitre (`DEC-0028`)"*. Sans persistance, il n'y a pas de commune de l'enfant a connaitre.
La condition est donc alignee sur le produit tel qu'il est bati ; elle reste non encodable.

### 6.4 `QLM-CP-04` - `BIA-4`, et une adjacence avec du contenu **deja servi** que rien ne garde

`ARBITRAGE-PREALABLE-s29`, ligne 370 : *"moyenne, sous `BIA-4`. Terrain sur si aucun item
n'interroge l'enfant sur sa propre vie"*. Condition connue, tenue ailleurs, non encodable.

**Le fait que j'ajoute, mesure et non suppose.** `cp-eps-corps-effort` est servi et valide ; il
declare `EPS-CP-10` et `EPS-CP-11`, dont *"connaitre quelques regles d'echauffement, d'hydratation
et de recuperation autour de l'effort"*. Ses 22 enonces comprennent *"Quand doit-on boire de l'eau
autour d'un effort physique ?"*, *"Juste apres un effort physique intense, de quoi le corps a-t-il
besoin ?"*, *"Pourquoi le corps a-t-il besoin de repos apres un effort physique intense ?"*.
`QLM-CP-04` porte *"sommeil, alimentation, hygiene du corps"*. **Les deux lignes se touchent sur
l'eau et sur le repos**, et le seul discriminant ecrit est que le paquet servi borne tout a
*"autour de l'effort physique"*.

C'est exactement la couture que `nadia-prof` avait nommee d'avance dans
`ARBITRAGE-PREALABLE-s32`, section 6.4 - *"l'activite physique et le corps"*, partagee entre l'EPS,
les sciences CM1 et questionner le monde CP. **`DEC-0095`, section 9.3, a raison de conclure
qu'aucune clause de frontiere n'est due *entre les deux terrains de s41*** : la verification y porte
sur les paires de lignes candidates, et il n'y en a aucune. **La couture que je nomme est
ailleurs** : elle est entre une ligne candidate et un paquet **deja servi**, ce que la verification
de `DEC-0095` n'avait pas a couvrir.

**Ce que le depot en garde aujourd'hui : rien.** Verification faite, pas supposee -
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` (`ADR-0032`) et
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` ont tous deux le **meme paquet** pour perimetre,
leur nom le dit et leur code le fait. **Aucun garde-fou du depot ne compare le contenu de deux
paquets differents.** `ADR-0081` clause 5 garantit qu'un item ne compte que sur une ligne ; elle ne
dit rien de deux items de deux paquets qui parleraient du meme fait.

Je n'en fais pas une condition d'entree et je ne propose pas de regle : comparer deux paquets sur le
sens demande de lire, et une regle qui pretendrait le faire serait decorative. **Je le route a
`nadia-prof` via `sofia-sm`**, sous la forme d'un fait a lire avant d'ecrire le lot, et non d'un
verdict.

### 6.5 `QLM-CP-13` - aucune condition bloquante

`ARBITRAGE-PREALABLE-s29`, ligne 368 : *"rentable. `FER-1` sur les deux moities, **pas sur les
douze mois**. `FER-3` : nommer, et ordonner"*. Aucun biais signale, aucun refus d'avance sur cette
ligne, matrice sans annotation. **C'est la ligne la plus simple des six du point de vue
architecture.**

Une precision de fabrication qui evite un rouge previsible : les propositions seront des noms de
mois et de saisons, donc hors du domaine numerique. Le paquet devra porter son entree dans
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
avec le compte mesure - cette table n'a **pas** de plafond, deliberement, mais elle est verifiee
dans les deux sens et ses comptes ecrits sont compares au paquet reel.

### 6.6 `SCI-CM1-06` - `BIN-1`, la seule condition des six qui demande un geste de garde-fou

**La condition.** `ARBITRAGE-PREALABLE-s31`, section 5 : *"`BIN-1` - une partie ne se joue pas
majoritairement a pile ou face. Sur une ligne dont la tache principale est un classement a deux
categories, au plus la moitie des items d'un paquet porte deux propositions."* Refus d'avance
numero 6 : *"tout paquet de classement binaire dont plus de la moitie des items portent deux
propositions"*, et il nomme sa population - *"les lignes **6**, 13, 14, 18 des sciences et
`EMC-CP-07`"*. **`SCI-CM1-06` est la ligne 6.**

**L'etat du depot, mesure.** `BIN-1` n'a **aucun garde-fou transverse**. Elle est encodee une seule
fois, dans `PaquetCpEmcRegleSouhaitLoiTest`, comme un test de ce paquet-la :

```java
assertThat(binaires)
        .as("BIN-1 : au plus la moitié des %d items du paquet portent deux propositions,"
                + " or %d les portent", items.size(), binaires)
        .isLessThanOrEqualTo(items.size() / 2);
```

**Et le mode de defaillance de cet encodage est deja visible dans le depot, sur un paquet livre au
sprint precedent.** `cm1-emc-regle-loi` (`S40-LOI`, `EMC-CM1-08`) porte **11 items dont 6 binaires**,
mesure sur le fichier servi, et son test de paquet `PaquetCm1EmcRegleLoiTest` **ne contient aucune
assertion `BIN-1`**. Sous le seuil tel qu'il est ecrit ci-dessus, `items.size() / 2` vaut 5 pour 11
items : le paquet y serait rouge. **Je ne prononce pas que `S40-LOI` viole `BIN-1`** - la portee de
`BIN-1` sur une ligne d'EMC CM1 que le refus d'avance ne nomme pas est un jugement de `nadia-prof`,
pas le mien, et elle a valide ce lot. **Ce que je constate est de mon cote** : une condition dont
l'encodage vit dans le test d'un paquet ne suit pas les paquets suivants, et le depot en porte deja
l'exemple.

**La condition du verdict, donc** : `S41-SCI-CLASSER` est faisable en l'etat **a condition que son
test de paquet encode `BIN-1`**, sur le patron exact de `PaquetCpEmcRegleSouhaitLoiTest` -
denominateur le paquet entier, jamais une tache, motif ecrit dans `ARBITRAGE-PREALABLE-s31`
section 5 et rappele par la reprise du 2026-09-10.

**Pourquoi je n'ecris pas un garde-fou transverse ici, et ce qui me ferait changer d'avis.** `BIN-1`
ne s'applique pas a tout paquet : elle porte *"sur une ligne dont la tache principale est un
classement a deux categories"*, et le depot n'a aucune donnee qui dise quelle est la tache
principale d'un paquet. Une regle transverse devrait donc soit deviner cette propriete, soit
enumerer les paquets concernes - un perimetre enumere par types, exactement la famille de defaut que
ce depot a deja payee. **Le declencheur ecrit** : le jour ou un troisieme paquet sera soumis a
`BIN-1`, la propriete *"cette ligne est un classement a deux categories"* merite d'etre declaree
dans le paquet plutot que dans trois tests, et l'`ADR` s'ecrit ce jour-la. Deux occurrences ne
fondent pas une abstraction.

**La mention de couverture partielle reste une dette, la meme qu'a s40.** La matrice, ligne 2006,
exige d'avance *"raisonnement sur un caractere, item par item ; le tri d'une collection entiere
reste non couvert"*. Comme pour `SCI-CM1-20` a s40, cette mention vivra dans un commentaire YAML et
dans la matrice, **et aucun automate ne la lit**. Je ne paie pas cette dette dans ce sprint : la
payer demande de decider ou une mention de couverture partielle vit structurellement, et cette
decision porte sur plusieurs lignes deja servies, pas sur `SCI-CM1-06` seule.

## 7. Question 4 - le risque technique de deux instances en parallele

**Precision de perimetre** : `DEC-0095` parle de deux instances de `nadia-prof` **auditant** en
parallele. L'audit lui-meme ne touche aucun fichier de code. Le risque technique que je decris est
celui du **travail de developpement** des deux terrains en parallele, qui est ce que deux audits
paralleles rendent utile. Si les six lots sont ecrits en serie sur une seule branche, rien de cette
section ne s'applique.

### 7.1 Trois fichiers partages sur cinq, et c'est mesure sur un diff reel

Le diff de `671f9379` (section 3) donne exactement la surface de contact. Sur les cinq fichiers d'un
lot de contenu, **deux sont propres au lot** et **trois sont partages par tous les lots** :

| Fichier | Propre ou partage | Ce que chaque lot y ajoute |
|---|---|---|
| `adapters/adapter-content/src/main/resources/contenu/<paquet>.yaml` | propre | le fichier entier |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/Paquet<X>Test.java` | propre | le fichier entier |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | **partage** | six lignes en fin de fichier |
| `adapters/adapter-content/src/test/java/.../LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | **partage** | une `Map.entry` en fin de `Map.ofEntries`, avec ses comptes mesures |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | **partage** | **trois** chaines, dans **trois blocs differents** du meme fichier : l'identifiant du jeu, son titre, sa consigne |

**Les trois points de contact sont en fin de liste, c'est-a-dire au pire endroit possible pour une
fusion.** Six lots ecrits en parallele produisent six ajouts au meme endroit de chacun des trois
fichiers. Ce sont des conflits de texte ordinaires, pas des defauts silencieux - ils se voient et se
resolvent - mais ils sont **certains**, pas probables, et ils tombent tous sur le dernier a
fusionner.

**Aucun de ces trois fichiers n'est un cliquet a plafond.** Leur echec est bidirectionnel, ce qui
est la bonne propriete ici : `TexteAccentueExposeParLePortEntrantTest` rougit aussi bien sur une
entree de catalogue non inventoriee (`touteChaineExposeeParLeCatalogueReelEstInventoriee`) que sur
une chaine inventoriee qui n'est plus exposee (`chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`).
**Une fusion qui perdrait la moitie d'un ajout est donc rouge, pas verte.** C'est le point qui
compte : le mode de defaillance de ces trois fichiers est visible.

### 7.2 Le seul point ou un conflit pourrait passer inapercu : le compte courant du registre

`squad/decisions/REGISTRE-identifiants.md` porte une ligne de compte courant, et
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` clause 5 verifie qu'elle vaut le compte reel
de la table. Si les deux terrains ouvrent chacun une reserve pendant leur audit, chacun ajoute une
ligne **et** incremente le meme nombre. **Une fusion qui garde les deux lignes et un seul increment
est rouge** - la clause 5 la prend. C'est donc gere, et je le note pour la raison inverse de celle
qu'on attend : c'est le seul endroit du dispositif ou une regle existante fait exactement le travail
d'un garde-fou de parallelisme.

### 7.3 Collisions d'identifiants : verifiees, aucune

- **Codes de ligne de referentiel** : les six sont distincts et aucun n'est declare par un paquet
  servi (section 5.2). Le garde-fou d'`ADR-0081` refuse qu'une ligne soit declaree par deux paquets
  sans allocation.
- **Identifiants de jeu du catalogue** : `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` mord si deux
  entrees se doublent. Le risque reel est un identifiant **plausible des deux cotes** - rien dans
  les six lots ne pousse deux terrains vers le meme nom, les prefixes de matiere differant.
- **Identifiants de condition** : clause 1 d'`ADR-0085`, *"allouer deux fois le meme identifiant"*,
  est precisement la clause ecrite pour le cas de deux instances qui ne se lisent pas. Elle est en
  place et elle est verte.

### 7.4 Ce qui n'est garde par rien, et que je nomme plutot que de le laisser trouver

**Deux instances qui ecrivent, sans le savoir, sur le meme objet du monde reel ne sont detectees par
aucun automate de ce depot** (section 6.4 : aucun garde-fou ne compare deux paquets). Pour s41, le
risque inter-terrains est faible et `DEC-0095` section 9.3 le verifie ligne par ligne contre le seul
critere que `nadia-prof` a ecrit. **Le risque reel est intra-terrain 2 et contre du contenu deja
servi**, et il est nomme en section 6.4.

## 8. Ce que je n'ai pas fait, et pourquoi

- **Aucun `ADR` ecrit.** Aucun des six lots n'en demande un. En ecrire un pour `BIN-1` sur deux
  occurrences serait une abstraction sur un usage imagine ; le declencheur qui le rendrait du est
  ecrit en section 6.6.
- **Aucune regle ArchUnit neuve.** Le compte du depot est inchange : **36 `static final ArchRule`,
  35 `@ArchTest`**, mesure du 2026-09-23. Methode, celle d'`ADR-0095` et d'`ADR-0098` : occurrences
  litterales de ces deux chaines sous `src/test/java` des cinq modules declares au pom agregateur,
  `target/` exclu et arbres de contre-exemple exclus - un balayage qui les inclut rend **38** et 35,
  les deux unites d'ecart etant portees par un seul fichier, le faux module de
  `LesGardeFousSontMisALEpreuveTest`, dont le chemin contient lui aussi `src/test/java`. L'ecart
  d'une unite entre 36 et 35 a la cause qu'`ADR-0094` a nommee : une `ArchRule` du depot n'est pas
  annotee `@ArchTest`.
- **Aucun contenu, aucun critere d'acceptation, aucune affectation, aucun arbitrage pedagogique.**
  `DEC-0095`, sections 7 et 8.
- **Je ne tranche pas la portee de `BIN-1` sur `EMC-CM1-08`** (section 6.6). Le fait est mesure et
  route ; le jugement appartient a `nadia-prof`.
- **Je ne touche a aucun fichier hors de celui-ci** : ni code, ni matrice, ni backlog, ni registre.

## 9. Mesure de la porte technique

Commande, sur l'arbre de `998b9992`, reacteur **complet** depuis la racine - donc sans la question
du `-am`, qui ne concerne que les mesures partielles :

```
bash outils/mvn-jdk21.sh -B clean verify
```

Sortie, telle qu'elle est :

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
...
[INFO] recreo ............................................. SUCCESS [  0.492 s]
[INFO] recreo-domain ...................................... SUCCESS [ 12.524 s]
[INFO] recreo-application ................................. SUCCESS [  4.674 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 57.329 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 40.823 s]
[INFO] recreo-bootstrap ................................... SUCCESS [02:35 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  04:32 min
[INFO] Finished at: 2026-09-23T16:11:20+02:00
```

**Version mesuree : 21.0.10**, telle que le JDK resolu la rapporte a l'enveloppe, et non telle que
l'entree de toolchain la declare (`ADR-0071`).

**Ce que ce vert ne dit pas, et le dire fait partie de la mesure** (`CLAUDE.md`, amendement du
2026-09-08) : `mvn verify` est le contenu du **job `build` seul**. `.github/workflows/ci.yml` porte
quatre jobs - `prefixes`, `chemins`, `outils`, `build` - et les trois premiers ne sont pas lances
par `mvn`. **Cet avis n'annonce donc pas la porte technique** ; il rapporte le resultat d'une
commande nommee, sur un arbre nomme.
