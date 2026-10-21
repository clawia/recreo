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

## 7. Fusions des corrections du palier 3 (K7), sur `round-two`

Casquette Scrum Master. Entree : `lecture-nadia-palier-3.md`. Sommet de `round-two` avant fusion : `7301cbbc`, verifie inchange depuis le depart ; les deux branches ont pour base de fusion `7301cbbc`. Fusion `--no-ff`, sans rebase, reset, force ni push. Fichiers touches, tous des YAML de paquets ou leurs tests : `ce1-inference.yaml`, `ce1-negation.yaml`, `ce1-texte-documentaire.yaml`, `ce1-emc-biens-communs.yaml`, `ce1-nombres-jusqu-a-1000.yaml`, `PaquetCe1EmcBiensCommunsTest.java`, `ce1-emc-fautif.yaml` (contre-exemple du test).

| Rang | Branche (sommet) | Fusion | Conflits |
|---|---|---|---|
| 1 | `s57-marc-corr-p3-fra` (`85de7b45`, trois commits : `954c3b09`, `5c999686`, `85de7b45`) | `27a8a56a` | aucun |
| 2 | `s57-lea-corr-p3` (`bb23e556`, deux commits : `1d96821b`, `bb23e556`) | `c7dab42b` | aucun |

Aucun conflit : aucune resolution a decrire, aucune assertion modifiee.

**Premier verify, rouge, sans rapport avec la fusion.** `clean verify` sur `C:` du poste (arbre de travail principal) : `BUILD FAILURE`, un echec dans `bootstrap`, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent` (PAR-5). Cause : le fichier non suivi `mesure-ines-palier-3-pli-744x551.md` (present avant mes fusions, absent de `HEAD`) ecrit les identifiants `PL-1` a `PL-8`, que `REGISTRE-identifiants.md` n'alloue pas ; le test balaie le disque, pas l'index. Je n'ai pas touche ce fichier (hors perimetre, propriete d'`ines-design`). Il reste a allouer ses identifiants ou a le commiter selon ADR-0085, clause 2 : decision a router, pas a prendre ici. Tant qu'il est non suivi dans l'arbre principal, un `verify` local y est rouge.

**Verify de reference** : worktree detache `recreo-k7` sur `c7dab42b`, arbre propre (`git status` vide), pour juger l'arbre fusionne et lui seul. Ce worktree est conserve, non supprime.

| Job | Etapes | Resultat |
|---|---|---|
| `build`, `mvn -B verify` | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`, 0 echec, 0 erreur, 0 ignore |
| `build`, `package` | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` | `BUILD SUCCESS`, `bootstrap/target/recreo.jar` produit (repackage Spring Boot) |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur `7301cbbc..HEAD` (avec et sans merges), `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh 7301cbbc..HEAD` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | balayage de `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | 13 juges, 0 echec |

Les jobs `prefixes`, `chemins` et `outils` ont ete joues dans l'arbre principal, pas dans le worktree propre. Le `BASE` de push reel n'existe pas en local : `7301cbbc` en tient lieu.

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 508 |
| `adapter-content` | 1794 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2840** |

Precedent rapport de fusion : 2839. Ecart mesure : +1, tout entier dans `adapter-content` (1793 a 1794), et dans `PaquetCe1EmcBiensCommunsTest` : 35 methodes de test contre 34 a `7301cbbc` (comptage des annotations, et 35 dans le log). Ecart attendu : +1. Le total correspond. Somme de mon propre log ; les autres lots corriges ne changent pas le nombre de tests.

**Decisions** : aucune `ADR` ni `DEC` creee ou modifiee. **Obstacle ouvert** : allocation des identifiants `PL-1` a `PL-8` du fichier d'`ines-design` (route : `theo-archi`, `ADR-0085` clause 2). **Non fait** : `relecteur-pedagogique` n'a pas relu ces fusions ; `nadia-prof` n'a pas rejoue les lots corriges ; la CI distante n'a pas juge ; matrice, stories, worktrees et branches non touches.

## 8. Fusion de la garde de pile de polices (K7), sur `round-two`

Casquette Scrum Master. `round-two` verifie a `6d2e5b38` avant la fusion, `git status` sans fichier suivi modifie.

| Branche | Tete | Coupee sur | Fusion | Conflit |
|---|---|---|---|---|
| `s57-theo-garde-pile-de-polices` | `558dd887` | `5ee20db7` (ancetre de `6d2e5b38`, verifie par `merge-base`) | `4650aeab`, `--no-ff` | aucun : `ADR-0054`, `ADR-index` et le registre fusionnent sans marqueur |

Contenu : `LaPileDePolicesServieNeChangePasEnSilenceTest` (8 tests, `adapter-web`), six fichiers de contre-exemple sous `src/test/resources/contreexemple/`, un amendement a `ADR-0054`, une ligne d'index. Aucun fichier hors de ces neuf.

**Verify de reference** : worktree detache `recreo-k7` passe de `c7dab42b` a `4650aeab` apres `git status` vide. `bash outils/mvn-jdk21.sh -B clean verify`, racine : `BUILD SUCCESS`, six modules `SUCCESS`, 6 min 23 s. Worktree conserve.

| Job de `ci.yml` | Etapes | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur `6d2e5b38..HEAD` sans merges, `verifier-garde-fou-accent-test.sh` (21 cas), `verifier-garde-fou-accent.sh 6d2e5b38..HEAD` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | 6 `outils/*-test.sh`, 2 `squad/outils/*/*-test.sh`, 5 `outils/*-test.py` | 13 juges, 0 echec |
| `build`, `mvn -B verify` | voir ci-dessus | `BUILD SUCCESS` |
| `build`, package | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` dans `recreo-k7` | `BUILD SUCCESS`, `recreo.jar` repackage, 4 min 11 s |

Les jobs `prefixes`, `chemins` et `outils` ont ete joues dans l'arbre principal, identique a `4650aeab`. Le `BASE` de push reel n'existe pas en local : `6d2e5b38` en tient lieu.

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 516 |
| `adapter-content` | 1794 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2848** |

Attendu : 2840 + 8 = 2848. Le total correspond. L'ecart avec les 2847 mesures par `theo-archi` est de +1 et il vient de sa base : `5ee20db7` ne contient pas `bb23e556` (le test ajoute a `PaquetCe1EmcBiensCommunsTest`), donc 2839 + 8 = 2847 sur son arbre. Le delta de `adapter-web` (508 a 516) est de 8, `adapter-content` ne bouge pas. Les 8 methodes de test du fichier sont comptees par annotation ; les chiffres sont la somme de mon propre log.

**Decisions** : aucune `ADR` ni `DEC` creee par cette fusion. `ADR-0054` est amendee par `theo-archi` dans la branche fusionnee (statut inchange par moi). **Obstacle ouvert** : inchange, allocation `PL-1` a `PL-8` (route : `theo-archi`). **Non fait** : la CI distante n'a pas juge ; ni worktree ni branche supprimes ; rien pousse.

## 9. Fusion des deux rapports de mesure (casquette Scrum Master)

Fusions `--no-ff` dans `round-two`, sans rebase, reset, force ni push :

| Branche | Sommet | Fusion | Fichier ajouté |
|---|---|---|---|
| `s57-ines-p3` | `42238bc5` | `46313f13` | `mesure-ines-palier-3-pli-744x551.md` |
| `s57-yanis-mutations-css` | `2459f304` | `9f8b84be` | `rapport-qualite-palier-3-mutations-css.md` |

Départ : `git status` vide, sommet `132ac960`. Les deux branches ne touchent que des fichiers sous `squad/` (une seule fiche chacune). Ce contrôle a été fait sur `git diff --name-only 132ac960 9f8b84be` après la fusion : ma commande de contrôle avant fusion était mal formée et n'a rien listé. Le résultat est le même, mais l'ordre prescrit n'a pas été tenu.

**Registre à l'état rouge attendu.** Mesure partielle, périmètre `bootstrap` avec `-am`, classe `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : 10 tests, 1 échec (`leDepotReelEstCoherent`), 0 erreur. Les deux seuls écarts rapportés sont `PL-9` et `PL-10`, écrits dans `mesure-ines-palier-3-pli-744x551.md` et non alloués dans `squad/decisions/REGISTRE-identifiants.md`. Aucun autre identifiant en écart. Ce rouge se ferme par l'allocation de `PL-9` et `PL-10`, route : `theo-archi`.

**Non lancé** : `mvn -B verify` complet, ni les jobs `prefixes`, `outils`, `build`. Seul `chemins` a été joué, sur les deux fichiers ajoutés : code de sortie 0.

**Décisions** : aucune `ADR` ni `DEC` créée ou modifiée par ces fusions. **Obstacle ouvert** : allocation `PL-9` et `PL-10`.

**Nettoyage** : `git branch --merged round-two` positif pour les deux branches, `git status` vide dans chaque worktree. Worktrees `recreo-s57-ines-p3` et `recreo-s57-yanis-mutations-css` supprimés, branches supprimées par `git branch -d`.

## 10. Porte technique de `21e994a4` (casquette Scrum Master)

Sommet de `round-two` : `21e994a4` ([THEO], allocation de `PL-9` et `PL-10` au registre). Worktree detache propre a ce SHA, `git status` vide avant lancement, `HEAD` verifie identique apres.

| Job de `ci.yml` | Etapes lancees | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur le dernier commit sans merges (pas de `BASE` local, branche `-1` du workflow), `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh` sur `HEAD^!` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | 6 `outils/*-test.sh`, 2 `squad/outils/*/*-test.sh`, 5 `outils/*-test.py` | 13 juges, 0 echec |
| `build`, verify | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`, 11 min 51 s |
| `build`, package | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` | `BUILD SUCCESS`, 10 min 22 s |

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 516 |
| `adapter-content` | 1794 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2848** |

Attendu 2848 (section 8), ecart 0 : le total correspond. Les chiffres sont la somme de mon propre log de verify. Depuis la section 8, seuls des fichiers de `squad/` ont change, ce que ce total confirme sans le demontrer par diff. Le registre, rouge en section 9, est vert dans ce verify.

**Decisions** : aucune `ADR` ni `DEC` creee ou modifiee par cette mesure. **Obstacle** : l'allocation de `PL-9` et `PL-10` est close par l'existence de l'allocation dans le registre au sommet testee ici. **Non fait** : la CI distante n'a pas juge ; rien pousse.

## 11. Fusion des trois corrections du palier 3 et porte technique de `d3fce602` (casquette Scrum Master)

Sommet de `round-two` verifie avant fusion : `644497b3`, `git status` vide. Les trois branches descendent de `a0a69859` (`git merge-base --is-ancestor`). Fusions `--no-ff`, dans cet ordre, aucun conflit sur les trois :

| Branche | Commit fusionne | Fusion | Fichiers touches |
|---|---|---|---|
| `s57-lea-corr2-p3` | `d0f3b1aa` | `bf402bc3` | `ce1-emc-biens-communs.yaml`, `TexteAccentueExposeParLePortEntrantTest.java` |
| `s57-marc-inf-il` | `62b65097` | `78ab8a5b` | `ce1-inference.yaml` |
| `s57-theo-garde-polices-r5-r6` | `19aa6c98` | `d3fce602` | `LaPileDePolicesServieNeChangePasEnSilenceTest.java`, 3 contre-exemples de gabarit, une ADR et son index |

Les deux fichiers ou un conflit etait attendu (test de lea, YAML de marc) ont fusionne sans conflit : rien a resoudre, rien affaibli. `s57-ines-p3-pli` n'est pas fusionnee (veto de `nadia-prof` a exercer).

Worktree detache propre a `d3fce602`, `git status` vide avant lancement, `HEAD` et `git status` verifies identiques apres.

| Job de `ci.yml` | Etapes lancees | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur le dernier commit sans merges (branche `-1` du workflow), `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh` sur `HEAD^!` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | 6 `outils/*-test.sh`, 2 `squad/outils/*/*-test.sh`, 5 `outils/*-test.py` | 13 juges, 0 echec |
| `build`, verify | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`, 12 min 39 s |
| `build`, package | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` | `BUILD SUCCESS`, 9 min 53 s |

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 520 |
| `adapter-content` | 1794 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2852** |

Attendu : 2848 (section 10) + 4 = 2852. L'ecart vient du seul commit `19aa6c98` : le diff de ses fichiers Java ajoute 4 methodes `@Test` a `LaPileDePolicesServieNeChangePasEnSilenceTest` et n'en retire aucune. `adapter-web` passe de 516 a 520, les autres modules ne bougent pas. Le total correspond. Les commits de lea et de marc ne touchent aucun test en nombre (une chaine modifiee dans un test pour lea, un YAML pour marc). Le "+ 8" de la consigne etait deja compris dans les 2848 : c'est le delta de la section 8. Les chiffres sont la somme de mon propre log de verify.

**Decisions** : aucune `ADR` ni `DEC` creee ou modifiee par cette fusion. `19aa6c98` porte l'amendement d'`ADR-0054` par `theo-archi`, deja inscrit dans l'index des `ADR` ; je ne l'ai pas modifie. **Obstacles** : aucun nouveau. **Non fait** : la CI distante n'a pas juge, rien pousse ; `s57-ines-p3-pli` reste a fusionner apres le veto de `nadia-prof`.

## 12. Fusion de s57-ines-p3-b383 (casquette Scrum Master)

Fusion `--no-ff` de `s57-ines-p3-b383` (`41578ff7`) dans `round-two` : `edb2ed8a`. Sommet avant fusion `859cf327`, arbre propre, branche coupee sur `859cf327`. Contenu : le cherry-pick `5302af53` d'`ines-design` (en-tete a 66 px, interligne de l'enonce et des propositions a 1,25) et sa mesure `41578ff7`.

Perimetre verifie par `git diff --name-only round-two...s57-ines-p3-b383` : `recreo.css`, `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java`, deux contre-exemples de ce test sous `adapters/adapter-web/src/test/resources/contreexemple/` (`entete-change`, `interligne-change`) et `mesure-ines-palier-3-pli-744x551.md`. Les deux contre-exemples n'etaient pas nommes dans la consigne : ils sont les fixtures du test de forme, je les ai juges inclus.

`s57-ines-p3-pli` (`edf7f344`, refuse par `nadia-prof`) n'est pas fusionnee ; son worktree et sa branche sont conserves.

Mesure du registre : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`. Resultat : `Tests run: 10, Failures: 1, Errors: 0, Skipped: 0`, `BUILD FAILURE`. L'echec est `leDepotReelEstCoherent` : `mesure-ines-palier-3-pli-744x551.md` ecrit PL-11, PL-12 et PL-13, que `REGISTRE-identifiants.md` n'alloue pas. C'est l'etat attendu. Aucun `verify` complet n'a ete lance : la porte technique reste a mesurer apres l'allocation par `theo-archi`.

**Decisions** : aucune `ADR` ni `DEC` creee ou modifiee par cette fusion. **Obstacle ouvert** : allocation de PL-11, PL-12, PL-13, a router vers `theo-archi` (`ADR` attendu sur le disque avant de le clore). **Constat** : `veto-nadia-gabarit-pli.md` est apparu modifie et non commite dans le depot principal pendant la fusion ; je ne l'ai pas commite.

Worktree `../recreo-ines-p3-b383` supprime (statut vide) et branche supprimee par `git branch -d`.

## 13. Porte technique de `ad9ef86d` (casquette Scrum Master)

Sommet de `round-two` teste : `ad9ef86d` (allocation de PL-11, PL-12 et PL-13 au registre, `theo-archi`). Worktree detache cree par `git worktree add --detach` a ce SHA, `git status` vide avant lancement, `HEAD` et `git status` verifies identiques apres les deux builds.

| Job de `ci.yml` | Etapes lancees | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur le dernier commit sans merges (branche `-1` du workflow), `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh` sur `HEAD^!` | tous 0 |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | 6 `outils/*-test.sh`, 2 `squad/outils/*/*-test.sh`, 5 `outils/*-test.py` | 13 juges, 0 echec |
| `build`, verify | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`, 11 min 56 s |
| `build`, package | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` | `BUILD SUCCESS`, 9 min 27 s, `bootstrap/target/recreo.jar` produit |

Limite de la porte : la CI distante n'a pas juge, rien n'est pousse. Les jobs ci-dessus sont ceux du workflow rejoues en local, et le motif `prefixes` n'a verifie que le dernier commit, pas la plage depuis une base de push.

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 522 |
| `adapter-content` | 1794 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2854** |

Attendu : 2852 (section 11) + 2 = 2854. L'ecart vient du seul commit `5302af53` (cherry-pick de `b383b0bb`) : `git diff d3fce602 ad9ef86d` sur `adapters/adapter-web/src/test/java` ajoute 2 lignes `@Test` et n'en retire aucune, dans `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` (les deux autres fichiers de test touches sont des fixtures CSS sans test). `adapter-web` passe de 520 a 522, les autres modules ne bougent pas. Le rapport d'`ines-design` donne 120 + 31 + 522 = 673 sur `-pl adapters/adapter-web -am` : coherent avec la ligne `adapter-web` ci-dessus. Le total correspond. Les chiffres sont la somme de mon propre log de verify.

**Decisions** : aucune `ADR` ni `DEC` creee ou modifiee par cette porte. **Obstacle** : l'obstacle de la section 12 (allocation de PL-11, PL-12, PL-13) est clos, le fichier existe : `squad/decisions/REGISTRE-identifiants.md` modifie par `ad9ef86d`, et `leDepotReelEstCoherent` passe dans ce `verify`. **Non fait** : `s57-ines-p3-pli` reste non fusionnee.

## 14. Fusion de s57-marc-doc-p3 et de s57-ines-r1, porte technique de `4d39b47d` (casquette Scrum Master)

**Fusions** (`--no-ff`, sans rebase, reset, force ni push) :

| Branche | Sommet | Base | Fusion | Perimetre verifie par `git diff --stat round-two...<branche>` |
|---|---|---|---|---|
| `s57-marc-doc-p3` | `ac986aca` | `9d892663` | `afd33e88` | un seul fichier : `adapters/adapter-content/src/main/resources/contenu/ce1-texte-documentaire.yaml`, 12 insertions, 12 suppressions (items 5, 9, 17) |
| `s57-ines-r1` | `56ce720e` | `8407f73f` | `4d39b47d` | un seul fichier : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/releve-ecran-ines-palier-3.md`, 392 insertions |

Aucun fichier de test touche par `s57-marc-doc-p3` : aucune assertion n'a change. `round-two` etait a `4d924f2d` avant fusion, `git status` vide.

Sommet de `round-two` teste : `4d39b47d`. Worktree detache cree par `git worktree add --detach`, `git status` vide avant lancement, `HEAD` et `git status` verifies identiques apres les deux builds.

| Job de `ci.yml` | Etapes lancees | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` sur `--no-merges` de `4d924f2d..HEAD`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh` sur `4d924f2d..HEAD` | tous 0 (0 commit touche le garde-fou de l'accent) |
| `chemins` | `verifier-chemins-test.sh`, `verifier-chemins.sh` sur `git ls-files` | 0 et 0 |
| `outils` | 6 `outils/*-test.sh`, 2 `squad/outils/*/*-test.sh`, 5 `outils/*-test.py` | 13 juges, 0 echec |
| `build`, verify | `bash outils/mvn-jdk21.sh -B clean verify`, racine | `BUILD SUCCESS`, six modules `SUCCESS`, 11 min 17 s |
| `build`, package | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` | `BUILD SUCCESS`, 9 min 46 s, `bootstrap/target/recreo.jar` produit (21,5 Mo) |

Limite de la porte : la CI distante n'a pas juge, rien n'est pousse. Les jobs sont ceux du workflow rejoues en local, `prefixes` sur la plage `4d924f2d..HEAD` et non sur une base de push reelle.

| Module | Tests |
|---|---|
| `domain` | 120 |
| `application` | 31 |
| `adapter-web` | 522 |
| `adapter-content` | 1794 |
| `bootstrap` | 381 (surefire) + 6 (failsafe) = 387 |
| Total | **2854** |

Ecart a 2854 (section 13) : 0, comme attendu. Aucun test ajoute ni retire, la fusion ne change que des donnees YAML et un fichier de `squad/`. Les chiffres sont la somme de mon propre log de verify.

**Decisions** : aucune `ADR` ni `DEC` creee ou modifiee par cette fusion. **Obstacles** : aucun nouveau. **Non fait** : `s57-ines-p3-pli` reste non fusionnee (commit refuse, decision a l'utilisatrice).
