# [Sprint s57] Rapport de developpeuse - palier 3, postes p3-4 (`RL5` a `RL7`) et R6

- **Casquette** : developpeuse, tache distincte de toute ceremonie. Je n'arbitre aucun obstacle ici.
- **Date** : 2026-10-20. **Source des deux postes** : `composition-palier-3.md` sections 12.1 et 12.2 (`AD2`), `preparation-palier-3-ce1-camille.md` amendement du 2026-10-19 23:44 (`AD3`, `AD7`).
- **Rapport tardif** : K7 etait reste a ecrire apres la fusion `20470501`. Il est ecrit apres coup et ne decrit que des commits existants ; aucune ceremonie n'est reconstituee.

## 1. Commits

| Poste | Branche et worktree (supprimes apres fusion) | Commit | Fusion dans `round-two` |
|---|---|---|---|
| `RL5` a `RL7` | `s57-sofia-rl5-rl7`, coupee a `13e5d4c8` | `4bf3c359` | `20470501` |
| R6 | `s57-sofia-r6`, coupee a `b9c6cb69` (sommet de `round-two` a la coupe) | `e6c371ad` | `91ecf180` |

Un seul worktree vivant a la fois : celui de `RL5` a `RL7` a ete supprime apres `git branch --merged round-two` (branche listee, arbre propre), avant l'ouverture de celui de R6.

## 2. RL5 a RL7 (`4bf3c359`)

Trois fichiers, cinq lignes de commentaire : `fragments/manche.html` (commentaire), `TexteAccentueDesGabaritsTest.java` (commentaire), `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java` (Javadoc). Chacun cite desormais `reponse-nadia-pa7-pa8.md`, section 2.4, source de la phrase de revelation *"Voici la bonne oeuvre. Appuie dessus."*, au lieu de `M4`. Aucune chaine servie ne change. Verification de la fusion : `mvn -B clean verify` de la racine vert **sur `4bf3c359`** (dit dans le sujet de `20470501`) ; je ne l'ai pas rejoue pour ce rapport.

## 3. R6 (`e6c371ad`)

| Critere | Mesure sur `e6c371ad` | Resultat |
|---|---|---|
| 1 (a) | `grep -c "attend l'audit"` : `ce1-pronoms.yaml` 1 -> 0, `ce1-texte-narratif.yaml` 1 -> 0 | vert |
| 2 (b) | `La question est portee par la consigne du jeu` 1 -> 0 ; `Chaque enonce porte sa question` 0 -> 1, dans `ce1-familles-de-mots.yaml` | vert |
| 3 | `git diff -U0 HEAD^ HEAD` : 3 lignes `-`, 3 lignes `+`, toutes commencant par `#`. `grep -c 'bonneReponse:'` avant = apres : **20** (`ce1-pronoms`), **20** (`ce1-texte-narratif`), **24** (`ce1-familles-de-mots`) | vert |
| 4 | `git diff --stat HEAD^ HEAD` : ces trois fichiers, rien d'autre ; commit distinct de `4bf3c359` | vert |
| 5 | R6 est fusionne avant tout jar rejoue du palier 3 (je n'en ai rejoue aucun). Les worktrees de lot ouverts a la fusion (`s57-lea-mat-ce1-01`, `s57-marc-fra-ce1-06`) sont a `b9c6cb69`, sans commit propre : coupes avant la fusion, ils ne contiennent pas R6 ; le critere ne l'exige pas, mais `composition-palier-3.md` section 5 demande aux lots de se couper sur le sommet fusionne : a verifier par `sofia-sm` (Scrum Master) avant leur fusion | a verifier |
| 6 | relecture orthotypographique par `relecteur-pedagogique` | **non faite, a lancer** |
| 7 | jobs de `ci.yml`, section 4 | voir section 4 |

Texte de remplacement. (a) : *"Audit de nadia-prof : VALIDE, FICHE-bandit-manchot-pronoms-ce1.md (2026-10-16)."* et la meme ligne avec `FICHE-bandit-manchot-texte-narratif-ce1.md`. (b) : *"Chaque enonce porte sa question ; la consigne du jeu (catalogue.yaml) est generique."* Les deux fiches portent bien un Done pedagogique prononce (lignes lues dans `squad/conformite/`), la fiche des pronoms apres un premier refus (`TRV-CE1-6`) leve par le second passage.

Non verifie par moi : l'affirmation de la story que les 24 enonces de `ce1-familles-de-mots` se terminent par `?`. Je ne l'ai pas comptee ; le texte de remplacement la reprend telle que `camille-po` l'a ecrite.

## 4. Jobs de `ci.yml` lances en local, sur `e6c371ad`

| Job | Commandes | Sortie |
|---|---|---|
| `build` (etape `mvn -B verify`) | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`. Somme des six lignes `Tests run:` de fin de module : 2669, 0 echec, 0 erreur, 0 ignore. Ce total est la somme du log de cette execution, pas un chiffre relaye |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur `round-two..HEAD`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh "HEAD^!"` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, puis `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | balayage de `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | 13 juges, aucun echec |

**Non lance** : l'etape `mvn -B -pl bootstrap -am package` du job `build`. La porte technique n'est donc **pas annoncee entiere** ; la CI distante la jugera. Les etapes de `prefixes` ont ete jouees avec `round-two` comme base, equivalent local de `github.event.before`, et non avec un `BASE` reel de push.

## 5. Decisions et obstacles

Aucune `ADR` ni `DEC` creee ou modifiee par ces deux postes. Aucun obstacle ouvert. Un point de suivi : la pre-condition de rang de `AD5` (R6 avant le premier jar rejoue) est tenue a la date de la fusion ; elle reste a reverifier par `sofia-sm` (Scrum Master) au moment ou un jar est rejoue.

## 6. Fusions du palier 3 (K7), sur `round-two`

Casquette Scrum Master. Ordre de `composition-palier-3.md` section 5. Fusion `--no-ff`, sans rebase, reset ni force. Avant fusion, `git diff --name-only round-two...<branche>` : aucune des six branches ne touche `manche.html` ni `ce1-pronoms.yaml`, `ce1-texte-narratif.yaml`, `ce1-familles-de-mots.yaml` (R6). Cinq branches sont coupees sur `b9c6cb69`, avant RL5 a RL7 et R6 ; `s57-lea-emc-ce1-01` est coupee sur `d640121d`. Les fichiers de R6 sont disjoints : critere 5 de R6 verifie.

| Rang | Branche (sommet) | Fusion | Conflits |
|---|---|---|---|
| 1 | `s57-theo-garde-c1-virgule-imbrique` (`78b260e3`, Z1/Z2) | `55328470` | aucun |
| signal | `fusion-z1z2-faite.md` | `1a2388ed` | sans objet |
| 2 | `s57-marc-fra-ce1-06` (`b4ab00d6`) | `59d6f245` | aucun |
| 3 | `s57-marc-fra-ce1-07` (`fba84a0d`) | `a6b1bf1b` | `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest` |
| 4 | `s57-marc-fra-ce1-11` (`9921ddee`) | `03acf639` | idem |
| 5 | `s57-lea-mat-ce1-01` (`bd99abcf`) | `4912d211` | idem |
| 6 | `s57-lea-emc-ce1-01` (`43cd625d`) | `5d3d7a96` | idem |

**Resolution des conflits.** Aucun autre fichier n'a conflit. `catalogue.yaml` : chaque lot ajoute une entree au meme endroit (apres `bandit-manchot-pronoms-ce1`), la ligne `nombreDeManches: 10` finale est commune aux deux cotes. J'ai garde les entrees deja presentes, puis ajoute celle du lot, chacune avec sa ligne `nombreDeManches: 10`. `TexteAccentueExposeParLePortEntrantTest` : trois inventaires (identifiants, titres, competences), memes conflits d'ajout au meme endroit. Entrees existantes gardees dans leur ordre, entree du lot ajoutee a la suite. `git diff HEAD --numstat` apres chaque resolution : uniquement des lignes ajoutees, zero supprimee (catalogue +6 par lot ; test +9, +8, +11, +11). Les entrees se suivent dans l'ordre de fusion : inference, texte-documentaire, negation, nombres-jusqu-a-1000, biens-communs. Aucune assertion modifiee.

**Jobs lances sur `5d3d7a96`** (tous locaux, un seul `mvn clean verify`).

| Job | Etapes | Resultat |
|---|---|---|
| `build`, `mvn -B verify` | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`, 0 echec, 0 erreur, 0 ignore |
| `build`, `package` | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` (`-am` present) | `BUILD SUCCESS`, `bootstrap/target/recreo.jar` produit |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur `43c3e988..HEAD`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh 43c3e988..HEAD` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | balayage de `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | 13 juges, 0 echec |

Le `BASE` de push reel n'existe pas en local : `43c3e988` (sommet de `round-two` avant les fusions) en tient lieu.

**Tests, somme des lignes `Tests run:` de fin de module du log de `clean verify`.**

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 508 |
| `adapter-content` | 1793 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2839** |

Cloture du palier 2 : 2669. Ecart mesure : +170. Ecart attendu : 7 (Z1/Z2) + 32 + 36 + 31 + 30 + 34 = 170. Le total correspond. Le chiffre est ici une somme de mon propre log, pas un chiffre relaye ; les cinq lots annonces ne sont pas reventiles par module, seule la somme est verifiee.

**Non fait.** `relecteur-pedagogique` n'a pas relu ces fusions ; la CI distante n'a pas jugé ; la matrice, les stories, les worktrees et les branches ne sont pas touches.
