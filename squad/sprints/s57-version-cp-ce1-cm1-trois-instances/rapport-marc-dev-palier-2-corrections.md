# [Sprint s57] Rapport de developpeur, palier 2, corrections - `marc-dev`, postes p2-3 (correction de `-05`) et p2-10 (`RCT-CE1-R1`)

- **Auteur** : `marc-dev`. **Date** : 2026-10-16.
- **Base commune** : `0ea24709` (`git log -1 --format=%h` au premier message, sommet de `round-two`). Les deux branches en partent, `git merge-base --is-ancestor 0ea24709 <commit>` sort 0 pour chacun des deux commits livres. Aucun push, aucun rebase, aucune fusion.
- **Ordre tenu** : un worktree a la fois, commit livre avant l'ouverture du suivant. Le premier worktree est ouvert a 18:26:29 (`git reflog show --date=iso <branche> | tail -1`), le second a 18:44:11, tous deux apres le commit de l'amendement de `PN8` et apres 18:01:35.
- **JDK de mesure** : 21.0.10, `java.version` lu dans les 60 rapports Surefire de `bootstrap` de chacun des deux `verify` (une seule valeur). Le `java` du `PATH` du poste n'est pas celui qui a mesure.
- **Ou vit ce rapport** : sur la branche de p2-10, dans un second commit. Le commit livre de `RCT-CE1-R1` ne porte que son YAML (`RC1-7` rend 0) : le rapport ne peut pas y entrer.

| Poste | Story | Branche | Commit livre | Tests ajoutes |
|---|---|---|---|---|
| p2-3 | `S57-BR-FRA-CE1-05` (correction) | `s57-marc-fra-ce1-05-corr` | `42071c23` | +0 (`PaquetCe1PronomsTest` : 37 avant, 37 apres, table et jeu fautif mis a jour) |
| p2-10 | `S57-RCT-CE1-R1` | `s57-marc-rct-ce1-r1` | `c77560f2` | +0 (`PaquetCe1TexteNarratifTest` : 32 avant, 32 apres, clause) |

**Delta, pas un total** : mesure sur la base `0ea24709` plus mon seul lot, branche par branche. Aucun test n'est ajoute ni retire. Le total de l'arbre fusionne est a `sofia-sm`.

## 1. p2-3 - correction de `ce1-pronoms` (`42071c23`)

**Fichiers du commit** : `ce1-pronoms.yaml`, `PaquetCe1PronomsTest.java`, `contreexemple/PaquetCe1PronomsTest/contenu/ce1-pronoms-fautif.yaml`. Ce sont les trois fichiers de `F1`, changes ensemble. `K2` : `git diff-tree --no-commit-id --name-only -r 42071c23` suivi du `grep -vE` de `K2` rend 0.

**Ce qui est ecrit** : les neuf champs de la section 5 de la fiche, copies sans reformulation. Items 3 (`texteSupport`, `distracteurs`), 9 (`texteSupport`, `indice`), 13 (`texteSupport`), 16 (`texteSupport`, `bonneReponse`), 18 (`texteSupport`, `distracteurs`). U+00A0 dans les chevrons et devant le deux-points de l'indice de l'item 9, comme le depot l'ecrit. Les textes sont recopies de `backlog.md` (amendement de `PN8`), qui reprend la fiche, avec le meme jeu de caracteres.

**Test et jeu fautif** : `GENRE_DES_PRENOMS` perd *Hamza* et gagne *Omar* (masculin), a sa place alphabetique ; trois lignes de la table sont reflowees pour la tenir (le compte reste 21 feminins et 20 masculins). Le jeu fautif nomme *Omar* a la place de *Hamza* sur les deux lignes de l'item 16, rien d'autre.

**Controles de `PN8`, sur le commit `42071c23`, base ecrite en SHA** (commandes de `backlog.md`, telles quelles) :

| Code | Attendu | Sortie sur `42071c23` |
|---|---|---|
| `PN8-1` | 2 | 2 |
| `PN8-2` | `10 13 ` | `10 13 ` |
| `PN8-3` | 0 | 0 |
| `PN8-4` | 5 | 5 |
| `PN8-5` (`PN4`) | 0 | 0 |
| `PN8-6` (base `aa9d703c`) | 13 | 13 |
| `PN8-7` a `PN8-11` | 5 chacun | 5, 5, 5, 5, 5 |
| `PN8-12` | 0 | 0 |
| `PN8-13a` (base `e8d14ddb997965536d06689a812a998431ca3938`) | 8 | 8 |
| `PN8-13b` (meme base) | 18 | 18 |
| precondition de `PN8-13` (`git log --oneline` de la base a `42071c23` sur le YAML, compte de lignes) | 1 | 1 |

- **`PN8-14`** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1PronomsTest -Dsurefire.failIfNoSpecifiedTests=false` rend `Tests run: 37, Failures: 0, Errors: 0` et `BUILD SUCCESS` (perimetre `adapter-content` et ses amonts, `-am` present, mesure partielle).
- **`PN4`** : vert, `PN8-5` rend 0.
- **Autres clauses** : `K9` : `git diff --stat 0ea24709 42071c23` sur la matrice et sur `catalogue.yaml` est vide ; `K10` : 0 fichier du garde-fou de l'accent ni `pom.xml`. L'item 19 n'est pas touche : `PN8-13a` rend 8, pas 9 (la recommandation de son indice est declinee, comme la demande le dit).
- **Rouge avant** : je n'ai pas rejoue `PN8-7` a `PN8-13` sur `0ea24709`. Leur rouge est celui que `camille-po` a lu dans son amendement (3, 3, 4, 3, 3, 2, 0, 0).
- **Jeu au jar** : jar de `42071c23`, port propre, 12 parties de dix manches, une erreur puis la bonne reponse a chaque manche ; 20 items sur 20 vus, les cinq textes servis tels qu'ecrits ; propositions de l'item 16 : *Omar*, *un chien*, *Mina* ; indice de l'item 9 apres erreur : celui de `nadia-prof`, avec U+00A0 dans les chevrons et devant le deux-points.

## 2. p2-10 - `RCT-CE1-R1` (`c77560f2`)

**Fichier du commit** : `ce1-texte-narratif.yaml`, seul. Trois items, cinq lignes changees (la bonne reponse et les deux distracteurs d'Ines et d'Ibrahim, le second distracteur de Chloe), recopiees de `reponse-nadia-pa7-pa8.md` 1.4. Aucun enonce, texte support ni indice n'est modifie. Le test n'est pas touche (clause du critere : rien ne rougit).

| Code | Attendu | `c77560f2` |
|---|---|---|
| `RC1-1` (Ines, item 14) | 3 | 3 |
| `RC1-2` (Ibrahim, item 18) | 3 | 3 |
| `RC1-3` (Chloe, item 19) | 3 | 3 |
| `RC1-4` (hunks, base `78270b4c`) | 3 | 3 |
| `RC1-5` (lignes `-` et `+`) | 10 | 10 |
| `RC1-6` (enonce, texte support, indice) | 0 | 0 |
| `RC1-7` (fichiers du commit hors le YAML) | 0 | 0 |

- **Rouge avant**, rejoue sur `0ea24709` : `RC1-1` rend 1, `RC1-2` rend 1, `RC1-3` rend 2. `RC1-4` a `RC1-7` ne sont pas rejouees sur `0ea24709` (bornes, `RC1-7` sans objet sans commit livre).
- Precondition apres fusion : `git log --oneline 78270b4c..c77560f2 -- <YAML> | wc -l` rend 1. Assertions retirees de `PaquetCe1TexteNarratifTest.java` entre `78270b4c` et `c77560f2` : 0. `catalogue.yaml` : diff vide.
- **`PaquetCe1TexteNarratifTest`** : 32 tests, 0 echec, lus dans le `verify` complet de la branche (`Tests run: 32, Failures: 0, Errors: 0` pour cette classe).
- **Commentaire de tete du YAML** : relu, inchange. Il ne compte rien que ces textes changent (conditions `RCT-CE1-1` a `-3`, `LON-CE1-*`, prenoms) ; la relecture est la mienne, aucun test ne la garde.
- **Jeu au jar** : jar de `c77560f2`, port propre, 14 parties de dix manches, une erreur puis la bonne reponse a chaque manche ; 20 items sur 20 vus. Propositions servies : Ines *sort dehors*, *saute dans une flaque*, *enfile ses bottes* ; Ibrahim *monte*, *attend le bus*, *s'assoit près de la fenêtre* ; Chloe *Chloé plante une graine*, *une fleur pousse*, *elle l'arrose*. Les trois indices sont ceux d'avant.

## 3. Jobs de `ci.yml` lances, avec leur sortie

Sur chaque worktree, sur l'arbre du commit livre ; ce rapport entre dans l'arbre de p2-10 apres.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`. Les deux branches : `BUILD SUCCESS`, 120, 31, 501, 1630, 381, plus 6 d'integration, 0 echec. L'etape *"Package du fat jar"* n'est pas lancee : `verify` a produit le jar joue. Ce sont les comptes de mes worktrees (base plus mon lot, delta nul), pas un total.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0, sur les deux branches.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet de chaque commit passe dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "HEAD^!"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages, 13 juges (6, 2, 5), 13 sortis 0, sur les deux branches. Python lance sous la commande `python`.
- La CI distante n'est pas lue.
- **Pour ce fichier** (ajoute apres le lancement, hors tableau) : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, `ConcordanceDesTagsDAgentTest` et `ToutesLesReferencesDeDecisionResolventTest`, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 19 tests (10, 5, 1, 3), 0 echec ; `verifier-chemins.sh` sur les fichiers suivis, rapport compris : sortie 0. **Ce n'est pas `mvn verify`** : ce second commit ne touche ni code ni contenu servi.

## 4. Ecarts, questions et ce que je n'ai pas mesure

1. **`Q1` (`relecteur-pedagogique`)** : U+00A0 dans les chevrons et avant le deux-points de l'indice de l'item 9, comme le depot ; le texte de `nadia-prof` porte des espaces ordinaires. Je n'ai pas decide l'orthotypographie, j'ai suivi l'usage et le critere `PN8-8`. Les trois propositions de `RCT-CE1-R1` ne portent ni chevrons ni `:` ni `?`.
2. **`Q2` (`sofia-sm`, `camille-po`)** : le jeu fautif de `TRV-CE1-6` garde les anciens textes des items 3, 9, 13 et 18 (seul *Hamza* y change, comme la demande le dit). Il atteste un desequilibre de prenoms, pas ces textes. Si la copie doit suivre le paquet, c'est une decision a prendre, pas la mienne.
3. **Choix de forme du test** : *Omar* placé a son rang alphabetique, ce qui reflowe trois lignes de la table. Un remplacement sur place aurait change une seule ligne et sorti la table de l'ordre alphabetique. Je n'ai pas demande l'avis de `theo-archi` : ce n'est pas une regle d'architecture.
4. **Non garde** : aucun test du depot n'epingle les cinq textes de `-05` ni les cinq lignes de `RCT-CE1-R1` (`PN8-7` a `PN8-13` et `RC1-1` a `RC1-7` sont des commandes de la story, `CR28` et `CR35` les mettent en test a `yanis-qa`). **Aucun test ne garde l'absence de roles distribues par genre** (`TRV-CE1-6`) ni que les trois propositions d'Ines, d'Ibrahim et de Chloe departagent par l'ordre des faits : `nadia-prof` est seule garde.
5. **Non mesure** : le pli a 744 x 551 sur l'un ou l'autre paquet (jeu lu dans le DOM par requetes HTTP, pas dans un navigateur, aucune capture regardee a l'oeil) ; la marge de 73 px de `.indice` d'`ines-design`, que je ne remesure pas ; le compte de `TRV-CE1-2` de `ce1-texte-narratif` (la bonne reponse reste la plus longue sur Ines et Ibrahim, pas sur Chloe), repris de `nadia-prof` sans recompte ; la longueur des cinq nouveaux textes de `ce1-pronoms` au pli (elle rejoue les 20 items). Le port de debogage 19221 n'a pas servi : aucun navigateur lance.
6. **Aucun Done, aucun rejeu** : le rejeu des deux postes (0,5 chacun) et le Done pedagogique sont a `nadia-prof`. `catalogue.yaml` n'est modifie par aucun des deux commits ; le paquet de `-05` reste au catalogue (`Q-U11`, *"fenetre bornee"*).

## 5. Isolation

Repertoire `s57-p2-marc-dev-a` (ports 18521 et 19221), cree avant la premiere commande. Port 18521 verifie libre par `netstat -ano` avant chaque lancement ; ligne *"Tomcat started on port 18521"* et PID lus dans le journal du jar (deux lancements, un par poste), arret par ce PID seul, plus aucune ligne LISTENING apres. **Racine : propre** : racine du scratchpad et repertoire parent des worktrees listes a la fin, rien de moi hors mon repertoire et hors les deux worktrees que la demande m'imposait.

## 6. Ce que ce rapport ne fait pas

Ne prononce aucun Done, ne fusionne rien, ne juge ni la typographie ni les textes, ne modifie ni un critere ni une story, ne decide aucune regle d'architecture.
