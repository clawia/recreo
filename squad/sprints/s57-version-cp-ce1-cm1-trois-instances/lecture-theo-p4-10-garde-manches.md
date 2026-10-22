# s57 palier 4 - lecture de `theo-archi` : p4-10, `S57-GARDE-NOMBRE-DE-MANCHES`, forme du garde et `ADR-0013`

- **Date** : 2026-10-22, 17:50 a 18:15 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`, transverse. La forme de ce garde est a moi (`composition-palier-4.md`, p4-10 ; `DEC-0121` section 7). **Je ne l'avais ecrite nulle part** avant que `marc-dev` code : il l'a cherchee dans trois fichiers et n'a rien trouve. Cet ecart est de ma main. La forme est ecrite ici, apres coup, et elle valide la sienne a une correction pres (section 2).
- **Lu** : branche `s57-marc-garde-nombre-de-manches`, base `51a9d957` (sommet de `round-two`), commit de code `fbe90f8b`, rapport `08500102` (`rapport-marc-dev-palier-4-garde-manches.md`). Lecture seule : je n'ai rien ecrit dans son worktree.
- **Version** : JDK 21.0.10, propriete `java.version` des rapports `TEST-*.xml` de chaque mesure (`ADR-0071`).
- **Ce que ce fichier ne fait pas** : il ne modifie ni code, ni `ADR`, ni `DEC`, ni registre, ni story. Il ne juge pas si 5, 7, 8 ou 10 est la bonne longueur de partie (`nadia-prof`), ni le Q3 de `marc-dev` (`camille-po`). Il ne fusionne rien. Les reperes `F`, `G`, `M` n'appartiennent qu'a ce fichier.

## 0. Verdict

| Code | Point | Verdict |
|---|---|---|
| `F` | Q1, la forme | **liste en constante du test, catalogue lu en `src/main`, test dans `adapter-content`** : la forme de `marc-dev` est la bonne (section 1) |
| `G` | Q2, `ADR-0013` | ni `DETTE_DE_BALAYAGE` ni `CRITERES_ENUMERES_DECLARES` : **confirme**. Mais **il manque une chose** : le test est **invisible** au recensement d'`ADR-0013`, parce qu'il ne porte pas le litteral `src/main/`. Le rapport affirme le contraire. Mesure en 2.2, correction d'une ligne en 2.3 |
| `M` | Lignee, ArchUnit, diff | lignee exit 0 ; ArchUnit 88 tests, 0 echec ; aucun fichier de production, aucune assertion affaiblie, liste egale a `DEC-0121` (section 3) |

## 1. Q1 : la forme du garde

**Decision.** La liste des derogations de `DEC-0121` vit dans une constante du test qui la garde (`Map<String, Derogation>`, identifiant de jeu vers valeur et `DEC`). Le test lit `catalogue.yaml` la ou il vit (`src/main/resources`) et habite `adapter-content`, module qui possede le catalogue. C'est la forme livree par `marc-dev`.

| Option | Ce qu'elle coute | Pourquoi ecartee ou retenue |
|---|---|---|
| Constante du test | 8 lignes, aucun lecteur neuf | **retenue.** Une seule lecture de la liste, dans le seul fichier qui la juge. Meme forme que `DETTE_DE_BALAYAGE` et que `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : une liste d'exemptions fermee des deux cotes, dont tout changement est un diff visible du test |
| YAML de ressources de test | un fichier, un lecteur, et un test du lecteur | **ecartee** : une piece mobile de plus, aucun deuxieme consommateur de la liste. Le diff d'une ligne de YAML n'est pas plus visible qu'une ligne de Java |
| Champ dans `catalogue.yaml` (`derogation: DEC-...`) | contrat de contenu, chargeur, schema | **ecartee** : elle met une donnee de gouvernance dans la donnee servie, et un auteur de contenu ecrirait la valeur et sa justification dans le meme geste. La liste a du sens parce qu'elle est **ailleurs** que la valeur |
| Test dans `bootstrap` | rien de gagne | **ecartee** : le catalogue est une ressource d'`adapter-content`, et son seul lecteur de production y vit. `bootstrap` ne se justifie que pour lire plusieurs modules, ce qui n'est pas le cas ici |

**Aucune regle ArchUnit** : la propriete porte sur une donnee YAML, pas sur du bytecode. Le mecanisme automatique est le test lui-meme, mis a l'epreuve par cinq catalogues fautifs (`ADR-0013` obligation 3) et recense par `LesGardeFousSontMisALEpreuveTest`, a condition qu'il y soit vu (2.2). **Aucun `ADR` neuf** : la decision est celle de `DEC-0121` section 5, la forme est celle d'`ADR-0013`, et une `ADR` qui ne decide rien de plus serait du texte sans regle. **Ce qui me ferait changer d'avis** : un deuxieme consommateur de la liste (un ecran, un outil) justifierait un fichier de donnees partage.

## 2. Q2 : `ADR-0013`

### 2.1 Les deux listes : confirme, il n'entre dans aucune

- **`DETTE_DE_BALAYAGE`** exempte des balayages **sans** jeu fautif (`LesGardeFousSontMisALEpreuveTest.java:44-53` a `51a9d957`). Ce test en a cinq, executes par `assertThatThrownBy`. L'y ajouter serait une place prise a tort, et le plafond (`PLAFOND_DE_DETTE = 2`) le refuserait.
- **`CRITERES_ENUMERES_DECLARES`** recense les gardes qui avouent un critere enumere **vert par defaut** sur tout objet non nomme (`:71-112`, `ADR-0039`). Ici, c'est l'inverse : une entree non nommee qui s'ecarte de 10 est **rouge**, et une ligne de la liste qui pourrit l'est aussi (valeur changee, revenue a 10, entree disparue). Les deux sens sont mordus sur jeu fautif. Ce n'est pas la famille d'`ADR-0039`.

### 2.2 Ce qui manque : le recensement ne le voit pas

Le recensement reconnait un garde de balayage au **litteral** `"src/main/"` dans sa source (`LECTURE_DE_PRODUCTION`, `:31`, filtre `:216`). Le test de `marc-dev` construit son chemin par `Path.of("src", "main", "resources", "catalogue.yaml")` et ne porte ce litteral nulle part : `git show 08500102:<test> | grep -c "src/main/"` rend **0**. Le modele qu'il a calque, `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`, le porte dans sa Javadoc a dessein (*"perimetre est le litteral "src/main/""*, `:44-47`). La copie a garde le chemin et perdu la phrase. Le rapport ecrit *"il contient `src/main/`, donc le recensement le voit"* : **c'est faux**.

**Mesure sur le materiau reel**, trois copies de `08500102` par `git archive`, `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre cette seule classe (10 tests) :

| Copie | Mutation | Resultat |
|---|---|---|
| `G1` | dossier `contreexemple/ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest/` supprime, test inchange | **exit 0, 10 tests, 0 echec** (18:01 a 18:02). Le garde perd ses cinq jeux fautifs et le recensement ne dit rien : **faux vert** |
| `G2` | meme suppression, plus le litteral `(src/main/resources)` ajoute a une phrase de la Javadoc (ligne 29, `diff` : cette seule ligne) | **exit 1, 1 echec**, `aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve`, message *"... Expecting empty but was: ["ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest"]"* (18:02 a 18:04) |
| `G3` | le litteral seul, jeux fautifs gardes | **exit 0, 10 tests, 0 echec** (18:04 a 18:06) : vu et eprouve |

### 2.3 La correction

Ajouter le litteral `src/main/` a la Javadoc du test, dans la phrase qui nomme le perimetre, comme le fait le modele. C'est une ligne, et c'est l'etat `G3`. **A `marc-dev`, dans sa branche, avant la fusion de p4-10.** Elle ne change aucune assertion. Sans elle, le garde est correct aujourd'hui, mais son epreuve n'est gardee par personne.

**Ce que cela dit du recensement, et c'est a moi.** Une detection par litteral rate tout balayage qui assemble son chemin par segments. Sur `51a9d957`, quatre sources de test font `Path.of("src", "main"...` sans porter le litteral (`git grep -l` puis `grep -q "src/main/"`) :

- `LaConsigneAdulteInterditDeMontrerLaLigneALEnfantTest` et `PaquetCpArtsOeuvresFamillesTest` : aucun dossier de jeu fautif a leur nom ;
- `LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest` et `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` : 11 et 7 fichiers de jeu fautif.

Que les deux premiers soient des balayages au sens d'`ADR-0013` est **non verifie**. Je l'ouvre comme une **dette de `theo-archi`**, non payee ici : un amendement d'`ADR-0013` qui elargit la detection a la forme par segments, avec un depot fautif tire de ces fichiers reels. Elle ne bloque pas p4-10.

## 3. Lignee, ArchUnit, diff

### 3.1 Lignee

`git merge-base --is-ancestor 51a9d957 08500102` : **exit 0**. `git merge-base --is-ancestor fbe90f8b 08500102` : exit 0. `git log --oneline 51a9d957..08500102` : 2 commits `[MARC]`, `08500102` (rapport) et `fbe90f8b` (garde et tests). `git rev-parse --short round-two` rend `51a9d957`, avant et apres mes mesures.

### 3.2 ArchUnit et gardes voisins

Copie propre de `08500102` par `git archive`, dans mon repertoire de scratchpad. `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test -Dtest=<15 classes ArchUnit de lecture-theo-lignee-palier-4.md section 2>,ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest,ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest,LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false`, 17:57 a 18:00, **exit 0**. Comptes lus dans les `TEST-*.xml` (18 rapports, une seule valeur de `java.version`) :

| Perimetre | Resultat |
|---|---|
| 15 classes ArchUnit | **88 tests, 0 echec** |
| `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | 7 tests, 0 echec |
| `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`ADR-0023`, non modifie) | 3 tests, 0 echec |
| `LesGardeFousSontMisALEpreuveTest` | 10 tests, 0 echec : vert **parce qu'il ne voit pas** le test neuf (2.2) |

Compte de regles (methode d'`ADR-0104`) : **41, 38, 16** a `08500102`, inchange.

### 3.3 Diff

`git diff --stat 51a9d957 08500102` : le test (+207), cinq catalogues fautifs et le rapport, 353 insertions, **0 suppression**. Rien sous `src/main`, aucun marqueur (`@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould`, `assume`), aucun autre test touche.

**La liste n'elargit aucune derogation.** Les entrees du catalogue qui portent une autre valeur que 10 a `51a9d957` (`awk` sur `nombreDeManches`, 160 entrees, 160 valeurs) sont exactement les huit de la constante, avec la meme valeur chacune : 7, 7, 7, 7, 7, 5, 5 et 8. Elles sont aussi les huit de `DEC-0121` section 3 (sept) et section 8 (une), avec les memes `DEC`. Aucune entree en plus, aucune en moins. La lecture des cinq branches du test (sans valeur, hors liste, valeur changee, revenue a 10, disparue) ne trouve aucune clause qui accepterait une valeur autre que celle de la liste.

**Une mutation rejouee sur le catalogue reel**, celle qui fonde la story (`C8`) : copie de `08500102`, `bandit-manchot-phrases-types-ce1` passe de 10 a 5 (ligne 902, `diff` : cette seule ligne), `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest -Dsurefire.failIfNoSpecifiedTests=false`, 18:00 a 18:01. Resultat : **exit 1, 7 tests, 1 echec**, message *"DEC-0121 : ces entrees de <catalogue servi> ne portent pas 10 et ne figurent pas dans la liste des derogations"*, puis le nom de l'entree mutee. C'est le resultat de sa mutation M1.

**Faut-il une relecture des mutations par `yanis-qa` ?** Pas pour les clauses du garde : chacune a son jeu fautif, et M1 se rejoue comme ecrit. **Oui pour le trou de 2.2**, qu'aucune des sept mutations ne pouvait voir puisqu'elles visent le garde et non son recensement. Une relecture apres la correction de 2.3 doit rejouer `G1` et attendre le rouge. Le routage est a `sofia-sm`.

## 4. Jobs de `ci.yml` lances pour ce fichier

- **`build`, mesures partielles** : sections 2.2 et 3, toutes avec `-am` et leur perimetre nomme. Puis, sur ce fichier, les quatre gardes de `squad/` (resultat dans le message de commit).
- **`chemins`** : sur ce fichier, avant commit.
- **`prefixes`** : sur ma branche, avant commit.
- **`build` complet, `package`, `outils`** : non lances par moi. Le `verify` complet est la porte de la fusion (RF4).
- **Le Done technique n'est pas prononce par moi.**
