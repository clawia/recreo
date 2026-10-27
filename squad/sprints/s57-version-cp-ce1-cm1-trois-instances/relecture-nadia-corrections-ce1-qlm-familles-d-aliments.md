# s57 - relecture des corrections de `ce1-qlm-familles-d-aliments` (`S57-BR-QLM-CE1-04`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 12:08:49 (UTC+01:00), lu par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-corr04`, base `round-two` a `bdfed668`, lue par `git`.
- **Lu** : `git diff a9b1480a HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-familles-d-aliments.yaml`
  et `git show f273c6d9` sur `PaquetCe1QlmFamillesDAlimentsTest.java` et `aliments-fautifs.yaml` (correction de `marc-dev`),
  compares a la section 5 de `lecture-nadia-premier-jet-ce1-qlm-familles-d-aliments.md`. Pour la source, la page
  `https://www.alimentarium.org/fr/node/5477`, lue par moi (section 4). **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Les deux menus neufs sont joues sur le papier.
- **Mesures lancees** :
  - recompte des quatre items de menu par script (Python, hors du depot, non commite) : familles de chaque menu, regle 3, aliments
    des menus attendus et des distracteurs, bonne reponse strictement la plus longue ;
  - page de l'Alimentarium telechargee (`curl`, HTTP 200) et texte extrait par script, hors du depot ;
  - `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmFamillesDAlimentsTest -Dsurefire.failIfNoSpecifiedTests=false` :
    *"Tests run: 42, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre : ce test, module `adapter-content` et ses
    dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a la section 5 | **tous les points tiennent** ; l'en-tete de la source et quatre parentheses different de mon texte, par l'exception que j'avais ecrite, et c'est `marc-dev` qui a raison (sections 1 et 4) |
| Perimetre du diff | **seules les lignes prescrites changent** : 17 lignes, toutes dans ma section 5 (section 2) |
| `TRV-CE1-5`, distracteurs, signe d'un aliment | **tiennent** (section 3) |
| Source : *poisson* nomme ou range | **nomme**. Le fichier ne porte pas l'ecart interne relaye : la ligne *poisson* dit *"(les poissons, nommes)"* (section 4) |
| Ouverture | **aucune** (section 5) |
| Duree | section 6 |

## 1. Comparaison a la section 5, element par element

| Point | Element | Verdict | Motif |
|---|---|---|---|
| 1 | item 8, `:135` : `["lait, yaourt, fromage, banane", "riz, pâtes, saumon, œufs"]` | **tient** | Texte a l'identique, ancien absent |
| 2 | item 11, `:151` : `bonneReponse: "poulet, pâtes, yaourt"` | **tient** | Idem. Distracteurs et indice inchanges |
| 3a | en-tete de la source, `:45-53` | **tient, texte different du mien** | Huit premieres lignes et derniere phrase a l'identique. La phrase des aliments nommes differe : *"la page nomme les pommes de terre, les poissons, les oeufs, le lait, les fromages, les gateaux, la confiture et le chocolat"* au lieu de mon *"la page ne nomme que les pommes de terre, les oeufs, le lait et les fromages"*. Ma phrase etait fausse, la sienne est exacte (section 4). Sans accent, comme l'en-tete |
| 3b | parentheses des lignes pates, riz, pain, yaourt, poulet, saumon | **tient** | Les six portent `(range par le redacteur)`, le reste de chaque ligne inchange |
| 3c | parentheses des lignes poisson, gateau, confiture, chocolat | **tient, par mon exception** | Inchangees : *"(les poissons, nommes)"*, *"(les gateaux, nommes)"*, *"(la confiture, nommee)"*, *"(le chocolat, nomme)"*. Ma section 5 demandait *range par le redacteur*, sauf si `marc-dev` lisait le mot sur la page. Il l'y lit, je l'y lis aussi (section 4). Le mot tel que la page l'ecrit devait figurer dans son rapport de correction : il n'est ni dans le commit `f273c6d9` ni dans un fichier du depot ; il est desormais en section 4 de ce fichier |
| 3d | lignes pomme de terre, fromage, lait, oeufs, fruits et legumes | **tient** | Inchangees, comme prescrit |
| 4 | `:27` : *"... parmi trois menus de la cantine ou du centre de loisirs (4 items)"* | **tient** | Ligne a l'identique. Le test du paquet passe : `TACHE_DE_L_ENTETE` lit toujours *"(4 items)"* |
| 5 | rang et comptes | **tient** | Aucune autre ligne du YAML ne change ; 12 items |
| `T1` | `MOT_DE_FAMILLE` avec `feculentes?` et `produits?` ; sondes 31 et 32 | **tient** | Les deux alternatives de la forme de `theo-archi` (`659b218e`) sont ajoutees ; le groupe reste capturant parce que le message cite `mot.group(1)`, ce qui ne change pas ce que le motif refuse. Sonde 31 *"... la purée féculente ?"*, sonde 32 *"... ce produit ?"*, toutes deux dans la liste des refusees de `s21`, qui exige le message *"porte un mot d'un nom de famille"* : une sonde refusee pour un autre motif ferait echouer le test. L'enonce est passe par `plat` avant le motif, l'accent de *féculente* ne le contourne pas. Le test passe |
| `T2` | Javadoc, ecart de `I4` | **tient** | L'ecart est ecrit tel que je l'ai confirme : lecture d'un menu des deux elements, refus d'un element absent par `verifierLesAlimentsDesItems` (sonde 05), forme a `theo-archi` |

Aucun texte neuf ne porte d'espace avant `?` ou `:`.

## 2. Perimetre du diff

`git diff --stat a9b1480a HEAD` sur le YAML : *"1 file changed, 17 insertions(+), 17 deletions(-)"*, un seul commit (`f273c6d9`). Les
17 lignes : 1 ligne de tache (`:27`), 8 lignes de l'en-tete de la source, 6 parentheses, 1 ligne de distracteurs, 1 bonne reponse.
**Rien d'autre ne change** : ni enonce, ni indice, ni declaration d'item, ni ligne de compte. Le test change sur deux points, tous deux
prescrits (`T1`, `T2`) ; le jeu fautif gagne les sondes 31 et 32, et rien d'autre.

## 3. Ce que les corrections doivent tenir

**Recompte sur le fichier corrige** [script] :

| Item | Menu attendu | Distracteurs | Regle 3 |
|---|---|---|---|
| 2 | 4 familles | 3, 3 | tenue |
| 5 | 3 | 2, 2 | tenue |
| 8 | 3 | 2, 2 (*riz, pâtes, saumon, œufs* : F, V) | tenue, aucune famille commune aux deux distracteurs |
| 11 | 3 (*poulet, pâtes, yaourt* : V, F, PL) | 2, 2 | tenue, aucune famille commune aux deux distracteurs |

**Le signe d'un aliment** : *riz* est dans le menu attendu 2 fois sur 3 apparitions, *pates* 1 fois sur 5, comme l'annoncait ma 2.2.
*Poulet*, que la correction met dans le menu attendu de l'item 11, y est 2 fois, et 3 fois dans un distracteur : il ne designe rien.
Bonne reponse strictement la plus longue : 2 items sur 12 (`TRV-CE1-2`). **Tient.**

**Les distracteurs neufs, joues sur le papier.** Item 8 : *riz, pâtes, saumon, œufs* se lit comme un repas, plus de doublon a decoder,
et l'ecarter demande de voir deux feculents et deux aliments du meme groupe : c'est la competence. Item 11 : *poulet, pâtes, yaourt*
est un repas de cantine qu'un enfant reconnait ; il partage *pâtes* et *poulet* avec les distracteurs, l'enfant doit ranger pour
trancher. **Tient.**

**`TRV-CE1-5`** : les indices des items 8 et 11 ne changent pas (*"Range chaque aliment dans sa famille, puis compare."*, *"Pour chaque
menu, compte les familles différentes."*) ; ce sont deux methodes, ils ne designent aucun menu neuf. **Tient.**

## 4. La source : *poisson* nomme ou range, et quelle phrase

**Je corrige mon propre constat d'abord.** Ma phrase de la section 5, point 3, *"Des aliments de la liste, la page ne nomme que les
pommes de terre, les oeufs, le lait et les fromages"*, et mes quatre parentheses *range par le redacteur* sur poisson, gateau,
confiture et chocolat reposaient sur une lecture de la page qui m'avait ete relayee, et que je n'avais pas faite. Elles sont fausses.

**Ce que la page ecrit**, lu par moi le 2026-10-27 (telechargement HTTP 200, texte extrait par script), sous *"Les 7 groupes
alimentaires"* :

- groupe des sucreries : *"Le sucre, la confiture, le chocolat et les gâteaux, ainsi que les boissons sucrées et alcoolisées se
  trouvent dans ce groupe."*
- groupe des produits laitiers : *"Le lait, les laitages et les fromages appartiennent au groupe des produits laitiers."*
- groupe des fruits et legumes : *"Les fruits et les légumes ainsi que les jus appartiennent à ce groupe."*
- groupe des cereales et feculents : *"Les produits céréaliers ainsi que les légumineuses appartiennent à ce groupe. C'est également le
  cas des pommes de terre."*
- groupe des viandes : *"Les viandes, les poissons et les œufs composent ce groupe."*

C'est mot pour mot ce que `marc-dev` rapporte. Pates, riz, pain, yaourt, poulet, saumon, pomme, banane, poire, carottes et courgettes
n'y sont pas.

**Poisson : nomme.** La page ecrit *"les poissons"*, au pluriel, en terme generique ; l'aliment de la liste est *poisson*, le meme terme
generique. C'est le traitement que je tiens deja pour *les oeufs* et *les fromages*, que la page nomme au pluriel et que la liste ecrit
*oeufs* et *fromage*. Ce qui distingue *nomme* de *range* n'est pas le nombre, c'est que la page ecrit l'aliment lui-meme et non sa
categorie : *saumon* est range sous *les poissons*, *poulet* sous *les viandes*, *yaourt* sous *les laitages*. Meme raisonnement pour
gateau, confiture et chocolat : nommes.

**L'ecart interne relaye n'existe pas dans le fichier.** La ligne `:63` dit, a `HEAD` comme a `a9b1480a`,
`groupe des viandes (les poissons, nommes)` ; `marc-dev` ne l'a pas changee. L'en-tete qui liste *les poissons* parmi les aliments
nommes et la ligne *poisson* disent la meme chose. Il en va de meme pour gateau, confiture et chocolat.

**Phrase retenue : celle de `marc-dev`**, telle qu'ecrite a `HEAD`. Aucune correction a demander :

```
# pour produits sucres, viandes pour viande, poisson et oeufs. Des aliments de la liste, la page
# nomme les pommes de terre, les poissons, les oeufs, le lait, les fromages, les gateaux, la confiture
# et le chocolat ; chaque autre aliment est range par le redacteur dans le groupe de sa categorie, et
# sa ligne le dit (nadia-prof les confronte, QC9).
```

et les lignes concernees, inchangees :

```
#   aliment poisson : viande, poisson et oeufs ; source : Alimentarium, Les groupes alimentaires, groupe des viandes (les poissons, nommes)
#   aliment gateau : produits sucres ; source : Alimentarium, Les groupes alimentaires, groupe des sucreries (les gateaux, nommes)
#   aliment confiture : produits sucres ; source : Alimentarium, Les groupes alimentaires, groupe des sucreries (la confiture, nommee)
#   aliment chocolat : produits sucres ; source : Alimentarium, Les groupes alimentaires, groupe des sucreries (le chocolat, nomme)
```

Les sept groupes que l'en-tete enumere (*boissons, sucreries, produits laitiers, fruits et legumes, matieres grasses, cereales et
feculents, viandes*) sont ceux de la page, dans son ordre. Chaque *range par le redacteur* range l'aliment dans la categorie que son
groupe nomme (produits cerealiers, laitages, viandes, poissons, fruits, legumes) : la condition de mon `I1` tient pour les onze.

## 5. Ouvertures

**Aucune.** Aucun item, aucune formulation n'est rouvert. Les corrections tiennent ; la seule difference avec mon texte est une phrase
de source que j'avais ecrite fausse et que mon exception laissait corriger sur la foi de la page. Ma correction de section 4 porte sur
un fait de mon premier jet, pas sur une regle : *"chaque ligne dit si l'aliment est nomme par la page ou range par le redacteur"* est
inchangee, et c'est elle qui donne raison a `marc-dev`. Rien a reporter par `camille-po`.

**Ce dont je reste la seule garde sur ce paquet, apres cette relecture** : l'exactitude de chaque famille contre la classification de
l'ecole, et que chaque ligne de source dise ce que la page dit ; la plausibilite de chaque distracteur, menu compris ; qu'aucun aliment
ne devienne le signe du menu attendu ; qu'un indice soit une methode et ne definisse pas une famille. **Aucune n'est couverte par un
test.**

## 6. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-27 12:08:49**, ecriture de ce fichier 12:17:55, fin **12:22:01**, avant le commit.
**Duree : 13 min 12 s**, dont la mesure du test du paquet, celle du garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
*"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; il n'ecrit aucun
identifiant neuf) et `.github/scripts/verifier-chemins.sh`, entree standard fermee : *"Aucun chemin de poste de travail ni nom de
compte dans les fichiers verifies."* Aucun autre job de `.github/workflows/ci.yml` lance.

**Faits pour le classement par `camille-po`** : cette relecture ne porte que sur les lignes que j'ai prescrites (section 2) et sur la
source qu'en nomme le point 3 ; elle ne relit aucun item hors des items 8 et 11, ne pose aucune correction et n'ouvre aucun tour.
C'est la forme d'une seconde lecture au sens de `DEC-0120` A.272 (0,5 hors marge, A.270 point 4). La lecture de la page n'est pas une
relecture du paquet : elle tranche une question de source que ma propre prescription avait mal posee. Le classement est a
`camille-po` ; je n'en convertis aucune duree.
