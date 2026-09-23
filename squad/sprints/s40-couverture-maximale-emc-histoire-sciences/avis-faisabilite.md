# s40 - Avis de faisabilite (`theo-archi`) - cinq lots d'EMC et de sciences

- **Date** : 2026-09-23
- **Base** : `round-two` a `7c10add1`, worktree `s40-theo` a ce meme commit. Stories de `camille-po`
  dans `docs/backlog/backlog.md`, section *"Sprint 40"*, lignes 14483 a 14738.
- **Perimetre de cet avis** : les **cinq** lots que `DEC-0093` dispense d'arbitrage pedagogique
  prealable neuf - `S40-EXTRACTION` (`SCI-CM1-20`), `S40-NUTRITION` (`SCI-CM1-09`), `S40-REGLES`
  (`EMC-CM1-04`), `S40-LOI` (`EMC-CM1-08`), `S40-DEVISE` (`EMC-CP-09`). **`S40-HDA`
  (`HDA-CM1-05`) n'est pas instruit ici** : son arbitrage prealable est en cours chez `nadia-prof`,
  et instruire sa faisabilite avant ses criteres de selection d'oeuvres reviendrait a les presumer.
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. L'affectation nominative du
  developpement revient a `sofia-sm` (`DEC-0093`, section 5, reconduisant `DEC-0091`/`DEC-0092`).
  Aucune ligne de code n'est ecrite avec cet avis, aucun contenu factuel n'y est fixe, et le Done
  pedagogique reste a `nadia-prof` seule.

---

## 1. Verdicts

| Lot | Ligne | Verdict | Complexite |
|---|---|---|---|
| `S40-NUTRITION` | `SCI-CM1-09` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S40-REGLES` | `EMC-CM1-04` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S40-LOI` | `EMC-CM1-08` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S40-DEVISE` | `EMC-CP-09` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve | basse |
| `S40-EXTRACTION` | `SCI-CM1-20` | **faisable en l'etat sur sa moitie *texte*. Sa moitie *tableau* est infaisable sans `ADR`** : rien dans le produit ne rend un tableau | basse sur le texte, moyenne sur le tableau |

Quatre lots sur cinq sont le cas le plus banal du depot : un paquet statique de type `question` de
plus, dans un lot de matiere et de niveau **deja servi**. Le cinquieme l'est pour moitie, et la
section 6 dit exactement ou passe la frontiere.

## 2. Verification des cinq arbitrages cites

`camille-po` cite cinq passages d'arbitrages anterieurs. **Je les ai ouverts, aux lignes citees, et
je ne me suis pas appuye sur sa citation.** Les cinq resolvent et disent ce qu'elle en rapporte.

| Lot | Fichier | Ligne citee | Resolue ? |
|---|---|---|---|
| `S40-EXTRACTION` | `ARBITRAGE-PREALABLE-s32-...-le-prix-d-une-ligne-marginale.md` | 1033 | oui, ligne `SCI-CM1-20` du tableau de la section 17.2 |
| `S40-NUTRITION` | idem | 1032 | oui, ligne `SCI-CM1-09` du meme tableau |
| `S40-REGLES` | `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` | 427 | oui, ligne **4** du tableau de rentabilite d'EMC CM1 |
| `S40-LOI` | idem | 425 | oui, ligne **8** du meme tableau |
| `S40-DEVISE` | `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md` | 235 | oui, ligne `EMC-CP-09` du tableau de la section 3.1 |

**Ce que chaque arbitrage tranche reellement**, en un mot chacun, parce que c'est ce dont la
faisabilite depend :

1. `SCI-CM1-20` : *"La ligne ferme en texte sur sa part servie, avec sa mention"*, la mention etant
   *"document textuel et tableau seuls ; le schema et le graphique restent non couverts"*. **Le
   tableau est dans le perimetre servi, le schema et le graphique en sont dehors.** C'est ce mot-la
   qui coute, et la section 6 dit pourquoi.
2. `SCI-CM1-09` : *"Cette ligne ferme en texte, aujourd'hui, sans palier 2. Je confirme le 'non
   exige'"*. Aucune designation sur schema anatomique. Forme entierement tranchee.
3. `EMC-CM1-04` : *"rentable [...] la moitie 'a quoi elles servent' est ce qui distingue la ligne
   d'un reglement recite"*, avec renvoi explicite au patron de la ligne 6 du CP.
4. `EMC-CM1-08` : *"rentable [...] `FER-1` : les trois verbes de la parenthese. Jamais de definition
   juridique dans l'enonce"*.
5. `EMC-CP-09` : *"La devise est une restitution exacte et sure. La moitie *exemple* est ou `EMC-5`
   mord le plus fort [...] La ligne ferme, mais elle coute cher a verifier."*

**Une correction d'appui, et elle va dans le sens de `camille-po`** : elle rapporte pour `S40-LOI`
que le patron voisin `EMC-CP-07` a demande cinq passages avant de fermer. Ce fait est reel et il est
dans `squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md`. Il ne change aucun verdict
d'architecture - **c'est un cout d'audit, pas un obstacle de conception** - et je le laisse ou elle
l'a mis : signale a `nadia-prof`, pas erige en condition d'entree.

## 3. Le mecanisme est deja la, et son precedent est un commit sans une ligne de Java

Les cinq lots sont des paquets statiques de type `question` joues au bandit manchot. Le precedent
demande, verifie : **`712c293b`** (s30, `[LEA]`, deux paquets neufs de sciences CM1, `SCI-CM1-05` et
`SCI-CM1-17`). Son diff, tel que `git show --stat` le donne :

```
 .../src/main/resources/catalogue.yaml              |  12 ++
 .../contenu/cm1-sciences-chaine-alimentaire.yaml   |  88 +++++++++++++
 .../contenu/cm1-sciences-signal-information.yaml   |  97 +++++++++++++++
 ...neReponseNEstPasPredictibleSurUnPaquetTest.java |  20 ++-
 .../PaquetCm1SciencesChaineAlimentaireTest.java    | 138 +++++++++++++++++++++
 .../PaquetCm1SciencesSignalInformationTest.java    | 135 ++++++++++++++++++++
 .../TexteAccentueExposeParLePortEntrantTest.java   |  24 +++-
```

**Zero ligne de Java de production.** Deux fichiers de contenu, deux entrees de catalogue, deux
tests de paquet, deux inventaires de garde-fou etendus. C'est exactement la forme attendue de
`S40-NUTRITION`, `S40-REGLES`, `S40-LOI`, `S40-DEVISE`, et de la moitie *texte* de
`S40-EXTRACTION`.

Le lot de matiere et de niveau existe deja pour les cinq. Mesure du 2026-09-23 sur les **84** paquets
de `adapters/adapter-content/src/main/resources/contenu/`, comptes par leur descripteur, `target/`
sans objet - un paquet, un fichier :

| Lot de matiere vise | Paquets servis aujourd'hui | Precedents directs |
|---|---|---|
| Sciences et technologie / CM1 | 9 | `cm1-sciences-chaine-alimentaire`, `cm1-sciences-signal-information`, sept autres |
| Enseignement moral et civique / CM1 | 2 | `cm1-emc-droit-devoir` (`EMC-CM1-07`), `cm1-emc-fait-opinion` |
| Enseignement moral et civique / CP | 6 | `cp-emc-regle-souhait-loi`, `cp-emc-regles-de-vie`, quatre autres |

**Aucune matiere neuve, aucun niveau neuf.** C'est la difference avec `S39-OUTILS` de s39, qui
ouvrait une treizieme matiere ; ici il n'y a meme pas ce cout-la.

## 4. L'allocation de competence : aucun `ADR` n'est du, et le motif est verifiable

La question posee est la bonne, et c'est celle qu'`ADR-0098` a tranchee pour les mathematiques de
CE1. **La reponse est differente ici, et pour une raison structurelle, pas par indulgence.**

### 4.1 Le garde-fou d'allocation derive deja sa population, sans nommer aucune matiere

`bootstrap/src/test/java/com/recreo/archi/ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest.java`
(`ADR-0081`) tire les lignes admissibles des referentiels de `squad/programme`, par le motif
`^- \[([^]]+)] .+`, et le niveau d'un code par le nom du fichier qui le declare. Son propre Javadoc
le dit : *"aucune matiere, aucun niveau et aucun code ne sont nommes dans ce fichier hors de ses jeux
fautifs"*. Un code neuf d'une matiere deja au referentiel est donc couvert **le jour ou il y est
ecrit**, sans une ligne a ajouter.

Verifie pour les cinq, dans les referentiels, sous la forme exacte que le motif exige :

| Code | Referentiel | Ligne |
|---|---|---|
| `EMC-CM1-04` | `squad/programme/enseignement-moral-et-civique/competences-cm1.md` | 38 |
| `EMC-CM1-08` | idem | 45 |
| `EMC-CP-09` | `squad/programme/enseignement-moral-et-civique/competences-cp.md` | 48 |
| `SCI-CM1-09` | `squad/programme/sciences-et-technologie/competences-cm1.md` | 58 |
| `SCI-CM1-20` | idem | 78 |

Les cinq resolvent. Un paquet dont le code perdrait le zero de son numero, remplacerait ce zero par
la lettre O, ou trainerait une espace finale est rouge aujourd'hui, sans rien ecrire.

*Correction du 2026-09-23 (`theo-archi`, meme jour) : cette phrase epelait les trois formes fautives.
Deux d'entre elles ont la forme d'un identifiant de condition, et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`
(`ADR-0085`, `PAR-5`) les a comptees comme des identifiants ecrits sans allocation - a juste titre :
il ne peut pas distinguer un identifiant cite en exemple d'un identifiant pose. C'est la lecon
qu'`ADR-0087` avait deja ecrite pour lui-meme ("cet ADR-ci ne recopie aucune des lignes fautives -
il les designe"), et que cet avis n'avait pas appliquee. Les formes sont desormais designees, pas
epelees.*

### 4.2 Pourquoi la sous-partie d'`ADR-0097`/`ADR-0098` n'a pas d'equivalent a allouer ici

`ADR-0098` n'a pas ete ecrit parce que les mathematiques de CE1 etaient une matiere neuve. Il a ete
ecrit parce que **plusieurs paquets y servent la meme ligne de referentiel**, chacun une part
d'elle : `ADR-0096` fait de la repartition par forme la regle, et le paquet statique sa consequence,
si bien que `MAT-CE1-03` est servie par plusieurs paquets dont aucun ne la ferme seul. Sans
allocation nommee, rien ne distinguait un paquet qui sert sa part d'un paquet qui revendique la ligne
entiere.

**Cette configuration n'existe sur aucun des cinq lots.** Chaque ligne y est servie par un paquet et
un seul, et la clause 6 d'`ADR-0081` suffit : la ligne est declaree une fois au niveau du paquet,
chaque item la porte, et la ligne declaree doit etre servie par au moins un item. Ecrire une table
d'allocation de sous-parties d'EMC ou de sciences reviendrait a ecrire une abstraction sur un usage
que je n'ai pas constate - exactement ce que `CLAUDE.md` me demande de ne pas faire.

**Le declencheur qui me ferait changer d'avis, ecrit d'avance** : le jour ou une **seconde** entree
de catalogue declarera l'une de ces cinq lignes, l'allocation redevient due, par le raisonnement
exact d'`ADR-0097` decision 2. Ce jour-la, l'`ADR` s'ecrit avant le paquet, pas apres.

### 4.3 La mention de couverture partielle de `S40-EXTRACTION` n'est gardee par aucun automate

`S40-EXTRACTION` doit porter *"document textuel et tableau seuls ; le schema et le graphique restent
non couverts"*. **Je nomme ou vit cette mention aujourd'hui plutot que d'inventer une regle pour
elle** : dans un commentaire YAML du paquet et dans `MATRICE-couverture.md`. Precedent identique et
deja ferme, `cp-emc-formules-de-politesse.yaml` ligne 16, pour la mention *"connaissance des formules
seule"* d'`EMC-CP-05`. Aucun garde-fou ne lit ces deux phrases.

**C'est une dette, je l'ecris comme telle, et je ne la paie pas dans ce sprint.** La payer demande
d'abord de decider ou une mention de couverture partielle vit structurellement - une cle de schema,
une colonne de matrice - et cette decision porte sur au moins trois lignes deja servies, pas sur
`S40-EXTRACTION` seule. L'instruire dans un avis de faisabilite de story serait la trancher a la
sauvette. Elle est routee telle quelle, sans que ce sprint en depende.

## 5. Lot par lot

### 5.1 `S40-NUTRITION` (`SCI-CM1-09`) - faisable en l'etat

Forme entierement tranchee par l'arbitrage s32 : association fonction / organe **en texte**, la
designation sur schema etant *"non exigee"*. Les trois fonctions du referentiel (digestion,
respiration, circulation) et leurs organes donnent un vivier naturel de plus de dix items dans les
deux sens, ce qu'`ADR-0023` exige (items strictement superieurs aux manches). Le patron est celui de
`cm1-sciences-chaine-alimentaire` au caractere pres.

**Fichiers concernes** : un fichier neuf dans
`adapters/adapter-content/src/main/resources/contenu/`, une entree dans
`adapters/adapter-content/src/main/resources/catalogue.yaml`, un test de paquet dans
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/`, et les inventaires de
`bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`.

**Ce qui mordra**, a savoir avant d'ecrire plutot qu'au premier rouge : `ADR-0086` clause 1 - trois
items ou plus dont les propositions coulent dans le meme moule ne portent pas tous la meme bonne
reponse d'au moins trois mots. Sur une ligne a trois fonctions seulement, le risque d'ecrire
*"la digestion"* trois fois dans le meme moule est reel ; il se paie en variant la tache, pas en
variant le mot.

### 5.2 `S40-REGLES` (`EMC-CM1-04`) - faisable en l'etat

Le patron est nomme par l'arbitrage lui-meme : *"meme exigence qu'a la ligne 6 du CP"*. `EMC-CP-06`
est servie par `cp-emc-regles-de-vie`, `couvert-valide`. Le lot d'EMC CM1 existe (2 paquets), la
mecanique est la meme, le fichier de contenu est le seul livrable neuf de fond.

**Ce qui mordra** : rien de structurel. La moitie *"a quoi elles servent"* est une exigence
pedagogique (`FER-1`) que `nadia-prof` juge en jouant, et aucun automate ne peut distinguer un item
qui explique d'un item qui recite - je ne propose pas de regle qui pretendrait le faire, elle serait
decorative.

### 5.3 `S40-LOI` (`EMC-CM1-08`) - faisable en l'etat

Meme forme, meme lot de matiere. Precedent direct **sur le contenu de meme regime** :
`cp-emc-regle-souhait-loi.yaml`, qui sert `EMC-CP-07` au CP sur la meme distinction regle / loi. Le
CM1 en est la reprise avec un troisieme axe (autorise / interdit / protege).

**Ce qui mordra** : `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`) et le
garde-fou d'`ADR-0086`. Le cout reel du lot n'est pas la : c'est l'audit, et `camille-po` a raison de
le signaler - cinq passages sur le patron voisin de CP, tous perdus sur `EMC-5` (des propositions
vraies marquees fausses). **Aucune regle executable ne peut mordre `EMC-5`**, et le dire est plus
utile que d'en promettre une : juger qu'une proposition est vraie d'une situation decrite en francais
demande de comprendre la situation. C'est la main de `nadia-prof`, et elle le restera.

### 5.4 `S40-DEVISE` (`EMC-CP-09`) - faisable en l'etat

Sixieme paquet d'EMC CP, dans le lot le mieux fourni des trois. Rien de neuf cote mecanisme.

**Deux garde-fous existants mordent ce lot-ci plus que les autres, et tous deux derivent le CP du
descripteur, donc s'appliquent sans qu'on les arme** :

1. **`ADR-0089`, `LON-1` et `LON-2`** - au CP, un enonce compte au plus **15 mots** et au plus **deux
   phrases**, la seconde etant la question. Un enonce d'exemple concret de fraternite tient
   difficilement en 15 mots ; la contrainte est reelle et elle est deja rouge si on la manque.
2. **`ADR-0086` clause 1** - la bonne reponse de restitution est *"liberte, egalite, fraternite"*,
   trois mots. Trois items de restitution ou plus, dont les propositions coulent dans le meme moule,
   seraient rouges. Le paquet porte donc **un** item de restitution, ou des taches de restitution
   reellement distinctes.

Sur la moitie *exemple*, `EMC-5` est le cout que l'arbitrage annonce (*"elle coute cher a
verifier"*), et `nadia-prof` demande une verification item par item, pas par echantillon. Cout
d'audit, non de conception.

## 6. `S40-EXTRACTION` (`SCI-CM1-20`) - la moitie *texte* est faisable, la moitie *tableau* ne l'est pas

### 6.1 La moitie *texte* a un precedent exact, deja servi

`adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-lire-un-document.yaml`
(`HG-CM1-14`, *"lire un document fourni (texte) et en tirer une reponse"*) est le meme exercice sur
une autre matiere. Il emploie `texteSupport` (`ADR-0050`), champ optionnel d'item que la mecanique
transporte sans le lire, rendu entre la consigne et l'enonce. Quatre paquets servis l'emploient
aujourd'hui. **Rien a ecrire cote code.**

### 6.2 La moitie *tableau* n'a aucun support dans le produit

Le critere d'acceptation dit : *"un tableau rendu en texte (lignes et colonnes lisibles sans
image)"*. Trois faits mesures le 2026-09-23 sur l'arbre de `7c10add1` :

1. **Aucune cle `tableau` n'existe** dans les 84 paquets servis ni dans le schema de paquet. Et
   `ADR-0070` interdit qu'une cle de paquet servi soit ignoree du chargeur : on ne peut pas en
   ajouter une en douce et esperer qu'elle soit rendue.
2. **`texteSupport` est rendu par un `<p th:text>`** -
   `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`, ligne 66. `th:text`
   echappe, et HTML replie les blancs : un texte multiligne y devient **une seule ligne courante**.
   La regle `.texte-support` de `recreo.css` porte une taille et une marge, rien qui preserve les
   sauts de ligne.
3. Le `Question` du domaine ne porte aucune composante structuree : ses onze composantes sont des
   `String` et des `Optional<String>`.

Autrement dit, **le produit sait afficher un paragraphe, pas une grille**. Un tableau ecrit avec des
espaces d'alignement dans `texteSupport` ne marche pas : les blancs sont replies, et meme s'ils ne
l'etaient pas, `ADR-0092` a deja tranche qu'une mise en forme qui repose sur la police servie n'est
pas gardable - *"la police servie est celle de l'appareil"*.

### 6.3 Ce que cela vaut comme verdict

**Un `ADR` est du si le lot veut sa moitie tableau.** Ce qu'il aurait a trancher, et que je ne
tranche pas ici : une composante de contenu structuree pour un tableau (en-tete et lignes), le
gabarit qui la rend, et **la regle qui garde le fait qu'une ligne ne porte pas plus de cellules que
son en-tete** - qui est litteralement la decision d'`ADR-0087`, transposee du markdown du depot au
contenu servi. C'est une transposition, pas une invention : la regle a deja sa forme et son motif de
derivation.

Cout : une composante de plus au `Question` du domaine sur le patron des six precedentes
(`ADR-0024`, `ADR-0050`, `ADR-0062`, `ADR-0073`, `ADR-0075`), une cle de schema lue par
`YamlContentPackLoader`, un bloc de gabarit, une regle CSS, un garde-fou et son jeu fautif. **Ce
n'est pas un gros chantier ; ce n'en est pas un nul non plus, et le presenter comme du contenu seul
serait faux.**

**Trois voies existent, je les nomme et je ne choisis pas** - le choix appartient a `sofia-sm` pour
le decoupage et a `nadia-prof` pour ce qu'une fermeture de ligne exige :

- **V1** - livrer la moitie *texte* seule en s40, avec sa mention de couverture reduite, et laisser
  le tableau a un lot ulterieur. Zero code, verdict *faisable en l'etat*. **La mention pre-ecrite
  par l'arbitrage s32 ne convient alors plus telle quelle** : elle annonce *"document textuel et
  tableau seuls"*, et il faudrait dire *"document textuel seul"*. Modifier une mention de couverture
  est une decision de `nadia-prof`, pas la mienne, et c'est le seul point de cette voie qui ne
  m'appartient pas.
- **V2** - `ADR` et implementation du tableau dans s40, puis le paquet. Le lot cesse d'etre du
  contenu seul.
- **V3** - un tableau rendu en phrases dans `texteSupport` (*"le chene mesure 25 metres, le hetre
  30 metres"*). Zero code, mais ce n'est **pas** un tableau : le critere d'acceptation demande des
  lignes et des colonnes lisibles, et cette voie les supprime. Je la nomme pour qu'on ne l'adopte pas
  sans le voir.

### 6.4 Un garde-fou existant mord ce lot plus fort que les autres, et il faut le savoir avant d'ecrire

`ADR-0072` /
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest.java` :
**sur un paquet dont les items portent un texte support, aucune strategie qui ne lit qu'une seule
phrase de ce texte ne gagne la majorite des items.** Le predicat exact, tel qu'il est ecrit :

```java
boolean porteLaBonneReponse = phrase.contains(replier(item.bonneReponse()));
boolean sansAucunDistracteur = item.distracteurs().stream()
        .noneMatch(distracteur -> phrase.contains(replier(distracteur)));
```

**Pourquoi cela concerne `S40-EXTRACTION` au premier chef** : son critere d'acceptation demande une
information *"explicitement presente dans le document"*. La maniere naturelle d'ecrire un tel item
met la bonne reponse **mot pour mot** dans une phrase du support, et aucun distracteur ailleurs.
Chaque item ecrit ainsi est un gain decisif ; au-dela de 50 %, le paquet est rouge, seuil strict
repris de s12. Ce n'est pas un faux rouge : le garde-fou decrit exactement l'enfant qui trouve sans
lire.

Ce qui l'evite, et ce sont des faits de conception, pas des consignes de contenu : une bonne reponse
**reformulee** plutot que recopiee du support (`cm1-histoire-geographie-lire-un-document` ecrit
*"en 1789"* quand son support porte *"1789"*), ou un support dont la phrase porteuse **contient
aussi** une valeur candidate - ce qu'un tableau fait naturellement, toutes ses valeurs etant sur la
meme grille. Point notable et contre-intuitif : **la forme tableau est plus sure au regard
d'`ADR-0072` que la forme prose.** Pour memoire chiffree, `ADR-0072` mesure `cm1-comprehension` a
**0 %** sur ses 20 items a texte support.

## 7. Ce que je n'ai pas fait, et pourquoi

- **Aucun `ADR` ecrit dans ce sprint.** Quatre lots n'en demandent pas ; le cinquieme n'en demande un
  que si sa moitie tableau est retenue, et cette retenue n'est pas encore decidee. Ecrire l'`ADR`
  avant la decision de perimetre serait ecrire une regle pour un usage imagine.
- **Aucune regle ArchUnit neuve.** Le compte du depot est inchange : **36 `static final ArchRule`,
  35 `@ArchTest`**, mesure du 2026-09-23. Methode, celle d'`ADR-0095` et d'`ADR-0098` : occurrences
  litterales de ces deux chaines sous `src/test/java` des cinq modules declares au pom agregateur,
  `target/` exclu ; l'ecart d'une unite a la cause qu'`ADR-0094` a nommee, une `ArchRule` du depot
  n'etant pas annotee `@ArchTest`.
- **Aucune affectation, aucun contenu, aucun arbitrage pedagogique.** `DEC-0093`, section 5.
- **`S40-HDA` non instruit**, motif en tete de cet avis.

## 8. Mesure de la porte technique

Commande, sur le worktree `s40-theo` a `7c10add1`, reacteur **complet** depuis la racine - donc sans
la question du `-am`, qui ne concerne que les mesures partielles :

```
bash outils/mvn-jdk21.sh -B clean verify
```

Sortie, telle qu'elle est :

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
...
[INFO] recreo ............................................. SUCCESS [  0.589 s]
[INFO] recreo-domain ...................................... SUCCESS [ 14.223 s]
[INFO] recreo-application ................................. SUCCESS [  5.187 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 51.642 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 27.117 s]
[INFO] recreo-bootstrap ................................... SUCCESS [01:57 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  03:37 min
[INFO] Finished at: 2026-09-23T09:37:38+02:00
```

**Version mesuree : 21.0.10**, telle que le JDK resolu la rapporte a l'enveloppe, et non telle que
l'entree de toolchain la declare (`ADR-0071`).

**Ce que ce vert ne dit pas, et le dire fait partie de la mesure** (`CLAUDE.md`, amendement du
2026-09-08) : `mvn verify` est le contenu du **job `build` seul**. Les autres jobs de
`.github/workflows/ci.yml` - prefixes de commit, chemins de poste de travail, juges purs des outils -
ne sont pas lances par `mvn` et ne sont pas couverts par cette sortie. Cet avis n'annonce donc pas la
porte technique ; il rapporte le resultat d'une commande nommee, sur un arbre nomme.
