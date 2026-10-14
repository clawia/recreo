# [Sprint s57] Rapport de developpeuse - poste 5, `S57-BR-FRA-CE1-15`

- **Casquette** : developpeuse, declaree au debut de la tache. Je n'y arbitre aucun obstacle : ce que je note ci-dessous est route a un autre role, par la lecture de ce rapport.
- **Date** : 2026-10-14. **Arbre** : branche `s57-sofia-fra-ce1-15`, worktree `../recreo-s57-sofia-fra-ce1-15`, issue de `round-two` a `ea6d6f92`. `bash outils/verifier-lignee.sh` sort 0.
- **Commit du lot** : `72931c94` (code et tests dans le meme commit). Non fusionne.
- **Scratchpad et port** (tableau d'isolation de `planning.md`, ligne `sofia-sm` developpeuse, instance A') : repertoire `s57-p1-sofia-dev-ap`, port 18523. **Aucun jar n'a ete lance** pour ce lot : aucun port n'a ete ouvert.

## K1 (c), porte `P-2`

`git merge-base --is-ancestor 729b0d8a HEAD`, dans ce worktree : **sortie 0**.

## Ce qui est livre

| Fichier | Changement |
|---|---|
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | une entree : `bandit-manchot-accord-groupe-nominal-ce1`, paquet `ce1-accord-groupe-nominal`, `nombreDeManches: 10` |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | trois chaines d'inventaire : l'identifiant, le titre, le libelle de competence |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1AccordGroupeNominalTest.java` | 21 tests |
| `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1AccordGroupeNominalTest/` | un catalogue fautif et un jeu fautif de cinq items |

**Le YAML du paquet n'est pas touche.** `nadia-prof` n'a decide aucune correction (`lecture-nadia-palier-1.md`, 2.3 : *"Rien a signaler"*), donc `S5` ne s'applique pas.

**Deux textes lus par l'enfant sont de ma main et a juger par `nadia-prof`** : le titre *"Des mots bien accordés"* (sans niveau, `ADR-0078`) et la consigne *"Lis bien, puis choisis la bonne réponse."*, reprise mot pour mot des entrees `-13` et `-10` parce que la question est portee par chaque enonce (*"Quel groupe de mots est bien écrit ?"*). Je n'ai pas demande le titre a l'arbitrage ; le garde-fou de l'accent l'a accepte sans amender le lexique (`K10`).

## Criteres communs, avec les commandes

| Critere | Commande | Resultat |
|---|---|---|
| `K2` | `git diff-tree --no-commit-id --name-only -r 72931c94` suivi de `grep -vE` sur `catalogue.yaml`, `PaquetCe1AccordGroupeNominalTest`, `TexteAccentueExposeParLePortEntrantTest.java` | 5 fichiers, 0 hors liste |
| `K3` | `grep -c "paquet: ce1-accord-groupe-nominal$"` sur `catalogue.yaml` | 1 (0 avant) ; entrees : 147, soit 146 plus 1 |
| `K4` | le test de paquet et son dossier de fixtures existent | oui, 21 tests |
| `K5` | aucun garde-fou assoupli, aucune borne montee | oui : aucun test existant n'est modifie, hors trois lignes d'inventaire ajoutees |
| `K9` | `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` | vide ; aucune fiche ouverte (`camille-po`, apres le commit) |
| `K10` | `git diff-tree --no-commit-id --name-only -r 72931c94` suivi de `grep -E 'AucunMotServiNeSEcarteDeSonAccentTest\|LexiqueFrancais\|ADR-0065\|pom\.xml'` | ne rend rien |

## `GNA-CE1-1` a `GNA-CE1-3`, recomptes sur le champ `bonneReponse`

Le test ne lit aucun commentaire du fichier. Valeurs lues par moi sur le fichier : 21 groupes de trois mots sur 24 ; 12 en -x, 6 en -s, 6 au feminin. Le test asserte les seuils de `nadia-prof` (la moitie, le cinquieme), pas ces valeurs. Les commandes `awk` de la story (`G2`) sont celles de `camille-po`, dont le test s'inspire : il ne les reprend pas telles quelles, il lit les determinants et les terminaisons.

## `K7` : `TRV-CE1-5`

**Relu** : les 24 indices lus un a un, puis comptes par script de poste : 24 indices, **4 distincts**, **au plus 10 mots**, aucun renvoi a l'oreille. Resultat conforme a la condition (au plus douze mots, jamais la bonne reponse, pas identique sur tout le paquet). Garde par `trv5ChaqueIndiceCompteAuPlusDouzeMots...` et `trv5AucunIndiceNeContientUnePropositionDeSonItem`. `TRV-CE1-2` : 10 items sur 24 ou la bonne reponse est strictement la plus longue, borne du test 12.

## `K8` : un test qui mord, ou une ligne *"non garde"*

| Point | Garde-fou |
|---|---|
| `GNA-CE1-1` (proportions) | `gna1LesProportionsSontRecomptees` ; jeu fautif : *"2 groupes avec adjectif sur 5"* |
| `GNA-CE1-2`, -x et -s | `gna2LeXNeSertQuApresEauOuEu...`, `gna2BleuPneuEtLandauNePrennentJamaisUnX`, `gna2UnFemininAUnDistracteurAuMasculinLeEEnMoins` ; un jeu fautif chacun |
| `GNA-CE1-3` | `gna3ChaqueDistracteurEstUneMarqueOublieeUneMarqueSurLeMauvaisMotOuUnSAlaPlaceDuX` ; un jeu fautif |
| **non garde : exactitude de chaque marque** que les terminaisons ne decident pas (`GNA-CE1-2`) | `nadia-prof` seule (arbitrage du palier, section 7) |
| **non garde : un adjectif deja termine par -e** | le test voit le -e retire, pas l'adjectif |
| **non garde : une seule reponse juste par item** | lecture de `nadia-prof` |
| `TRV-CE1-6` | sans objet : aucun prenom dans le paquet |

Le test refuse un -x apres autre chose que -eau ou -eu (aucune ligne du paquet n'en a). **Si un lot ulterieur sert -au ou -oux, ce test s'amende avec `nadia-prof`**, il ne s'assouplit pas en silence.

## Mutations, avec les deux controles (motif trouve, tests lances superieur a zero)

Sur `ce1-accord-groupe-nominal.yaml`, test lance : `PaquetCe1AccordGroupeNominalTest`, 21 tests ; fichier restaure par `git checkout` apres chaque mutation, `git status` verifie.

| Mutation | Motif trouve | Tests lances | Resultat |
|---|---|---|---|
| `les beaux chapeaux` devient `les beaux chapeaus` en bonne reponse | 1 | 21 | 3 echecs : `gna2LeX...`, `gna3...`, `trv7...` |
| `une petit fille` devient `une petits fille` | 1 | 21 | 2 echecs : `gna2UnFeminin...`, `gna3...` |
| indice du premier item remplace par `Cherche les beaux chapeaux.` | 1 | 21 | 1 echec : `trv5AucunIndiceNeContient...` |

**Un faux vert evite, nomme** : ma premiere formulation de la troisieme mutation (motif sur plusieurs lignes) a rendu *"MOTIF TROUVE 0"* parce que le fichier a des fins de ligne `CRLF` dans le worktree ; la suite est restee verte a 21 tests et je ne l'ai pas lue comme un vert. J'ai refait la mutation sur deux lignes avec `\r\n`. Premiere version de `estLeMasculinDe` : elle comptait *un*/*une* comme masculin et acceptait donc un feminin dont seul le determinant differait ; le jeu fautif l'a revele (21 tests, 1 echec), corrige en excluant le determinant.

## Jobs de `ci.yml` lances, avec leur sortie

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur l'arbre du commit `72931c94` : `BUILD SUCCESS`. Surefire : domain 120, application 31, adapter-web 486, adapter-content 1505, bootstrap 371 ; Failsafe 6 ; **total 2519, 0 echec, 0 erreur, 0 ignore** ; JDK 21.0.10 (`java.version` des 59 rapports Surefire de `bootstrap`). Ce total est celui de ce reacteur : il egale 2498 (reacteur de `round-two`, mesure de `planning.md`, non remesuree a `ea6d6f92`) plus 21 tests de ce lot. **Il ne vaut pas pour l'arbre fusionne**, que `sofia-sm` recompte. L'etape *"Package du fat jar"* n'est pas lancee.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "HEAD^!"` : *"0 commit(s) le touchent dans la plage"*, sortie 0.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0.
- **Ce fichier** : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`-pl bootstrap -am`, 5 tests, 0 echec) relance avec ce rapport suivi dans l'index ; le `build` complet ci-dessus a ete lance avant son ecriture.
- **`outils`** : non lance, aucun outil modifie.
- La CI distante n'est pas lue.

## Conflit prevu avec `s57-fra-ce1-10` : signale, non resolu

`git merge-base round-two s57-fra-ce1-10` est `3a3ea97e` ; `s57-fra-ce1-10` porte `1f6642ab` (`marc-dev`). `git merge-tree --write-tree s57-sofia-fra-ce1-15 s57-fra-ce1-10` sort **1** avec deux conflits de contenu :

- `adapters/adapter-content/src/main/resources/catalogue.yaml` : deux entrees ajoutees en fin de fichier ;
- `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` : les trois chaines d'inventaire, ajoutees apres les memes ancres (`bandit-manchot-verbe-sujet-ce1`, `Le verbe et son sujet`, le libelle de `FRA-CE1-13`).

**Cause de ma part** : j'ai place mes trois chaines apres les memes ancres que `marc-dev`, comme `-13` et `-10` le font. Je n'ai ni resolu le conflit ni choisi un placement qui l'aurait evite. La resolution est a `sofia-sm` a la fusion, selon l'ordre de `planning.md` (premier pret fusionne le premier, ajouts des deux cotes gardes, jamais une assertion affaiblie, comptage du catalogue : 146 plus 2 egale 148 apres les deux fusions).

## Ce que ce rapport ne fait pas

Ne prononce aucun Done, ne fusionne rien, ne juge pas le titre ni la consigne que j'ai ecrits, ne repond a aucune question de `nadia-prof`.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.** Le titre du jeu et le placement des chaines d'inventaire sont les seuls choix de ma main, notes ci-dessus.
