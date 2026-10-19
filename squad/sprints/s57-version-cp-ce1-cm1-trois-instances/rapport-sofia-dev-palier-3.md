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
| 5 | `e6c371ad` est fusionne avant tout jar rejoue et avant tout commit de lot du palier 3 (aucun lot n'est ouvert a `91ecf180`) | vert |
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
