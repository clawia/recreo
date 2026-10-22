# s57 - rapport de lea-dev, palier 3 (ecrit pendant le lot LP2 du palier 4)

- **Auteure** : `lea-dev`, instance B du palier 4, 2026-10-22, base `eb267ba7`. **Ce n'est pas l'instance qui a livre le palier 3** : celle-ci n'a laisse aucun journal dans le depot, et le scratchpad de la session du palier 3 n'est pas celui de cette session. Tout ce qui suit vient de `git log`, de `git show`, des fichiers du depot et de mesures faites aujourd'hui sur l'arbre ; ce qui n'est pas retrouve est dit *non retrouve*, pas reconstitue.
- **Pourquoi maintenant** : `K7` du palier 3 (`rapport-<developpeur>-palier-3.md`) manquait a la cloture. L'utilisatrice a decide (`AP1`, `composition-palier-4.md` section 12) que les worktrees de `lea-dev` s'ouvrent sans lui et qu'il s'ecrit pendant le lot. La fin du rapport (avant la fusion du lot ou avant la cloture du palier) n'est pas dite par elle : il est commite ici, sur la branche du lot `s57-lea-mat-ce1-10`.
- **Ce qu'il ne fait pas** : ne prononce aucun Done, ne modifie aucun paquet, ne touche a aucun des ecarts 40 a 44 (voir section 6).

## 1. Ce que le palier 3 a livre de `lea-dev` (depot)

`git log --grep='^\[LEA\]' --since=2026-10-19 round-two` rend cinq commits :

| SHA | Contenu | Delta de tests declare par le commit |
|---|---|---|
| `bd99abcf` | `S57-BR-MAT-CE1-01` : entree `bandit-manchot-nombres-jusqu-a-1000-ce1` au catalogue, trois chaines d'inventaire, `PaquetCe1NombresJusqua1000Test` | +30 |
| `43cd625d` | `S57-BR-EMC-CE1-01` : paquet neuf `ce1-emc-biens-communs` (24 items), entree de catalogue, inventaire, entree dans la table hors domaine numerique du garde de rang, `PaquetCe1EmcBiensCommunsTest` | +34 |
| `1d96821b` | `MAT-CE1-01` : items 15, 16 et 19 corriges (`lecture-nadia-palier-3.md` section 4) | 0 |
| `bb23e556` | `EMC-CE1-01` : tache 1 (meme article), items 14, 15, 17, 20 et 21 (`lecture-nadia-palier-3.md` section 5) | +1 |
| `d0f3b1aa` | `EMC-CE1-01` : corrections de langue (items 8, 9 et 14, espace insecable du descripteur) | 0 |

`bb23e556` est fusionne par `c7dab42b` (`s57-lea-corr-p3`, sans conflit). Les totaux apres fusion sont ceux de `sofia-sm` (`cloture-palier-3.md`) ; ces deltas sont ceux que les messages de commit annoncent, non remesures ici.

## 2. Jobs de `ci.yml` lances

- **Au palier 3 : non retrouve.** Aucun fichier du depot ne nomme les jobs que l'instance du palier 3 a lances avant de livrer ; le seul `verify` documente est celui de `sofia-sm`. Je n'ecris ni "lances" ni "non lances".
- **Aujourd'hui, sur l'arbre qui porte ces deux paquets** : voir la section 7 de ce fichier (jobs nommes avec leur sortie, ecrits apres leur lancement).

## 3. Ligne `TRV-CE1-5` par paquet

`TRV-CE1-5` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`) : l'indice donne une methode ou dit ou regarder, jamais la reponse, douze mots au plus, pas identique sur tout le paquet, aucune regle fausse. **Relu par cette instance le 2026-10-22, indice par indice, sur `eb267ba7`** (les 22 et les 24 indices lus ; pas une relecture faite au palier 3). Mesure : un script hors depot compte les mots de chaque indice et cherche chaque proposition de l'item dans son indice.

| Paquet | Ligne |
|---|---|
| `ce1-nombres-jusqu-a-1000` | **relu** : 22 items, 22 indices distincts, aucun au-dessus de douze mots, aucun indice ne contient une proposition de son item. Points de lecture, pas des refus : l'indice de `358 = 300 + 50 + ?` (*"Regarde le chiffre des unites."*) designe la place, pas le chiffre ; celui de `Lis ce nombre : 1000` (*"Compte les chiffres du nombre."*) est une methode. Aucune regle fausse vue |
| `ce1-emc-biens-communs` | **relu** : 24 items, 24 indices distincts, aucun au-dessus de douze mots, aucun ne contient une proposition de son item. Aucune regle fausse vue |

Le jugement sur le sens des indices reste a `nadia-prof`.

## 4. Racine du scratchpad au palier 3

**Non mesurable par cette instance.** La session courante ne porte pas la racine du palier 3. Ce que le depot dit : `cloture-palier-3.md` section 10 lit 23 repertoires et 69 fichiers a la racine, dont `s57-p3-lea-dev-b` et `s57-p3-lea-dev-ap` (table du palier 3). Je ne declare propre ni sale une racine que je n'ai pas listee. La racine du palier 4 est listee dans le compte rendu de fin de lot de `lea-dev` (ligne *"racine : propre"* avec sa commande), a reprendre dans `rapport-lea-dev-palier-4.md`.

## 5. Arrets du watchdog

Faits non fournis : `cloture-palier-3.md` section 10 point 6 dit qu'aucun fichier ne porte quand ni combien. Je n'ecris ni heure ni motif (`RF9`, `composition-palier-4.md` section 5.2).

## 6. Ce qui reste ouvert pour `lea-dev` et qui n'est pas dans ce lot

- **Ecart 43** (`ce1-emc-biens-communs.yaml` lignes 19 a 21, commentaire d'en-tete) et **ecart 44** (point final sur `competence` de `ce1-nombres-jusqu-a-1000.yaml`) : fichiers de `src/main`, a engager sous une story de `camille-po` (`cloture-palier-3.md` section 9). Non touches : `S57-BR-MAT-CE1-10` limite le diff du lot (K2). Si l'ecart 44 est applique, la meme question se pose pour `ce1-masses-contenances.yaml`, dont la competence n'a pas de point final et que ce lot laisse tel quel.

## 7. Jobs de `ci.yml` lances aujourd'hui (2026-10-22, JDK 21.0.10 rapporte par la machine virtuelle des tests)

Lances sur la branche `s57-lea-mat-ce1-10` (base `eb267ba7`), a la main, par les commandes du workflow. **Aucun n'a tourne sur le runner GitHub.**

| Job | Commande | Sortie |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh`, puis `verifier-prefixes.sh` sur `eb267ba7..HEAD` plus le sujet du commit de ce rapport, puis `verifier-garde-fou-accent.sh eb267ba7..HEAD` | six sorties 0 ; *"Tous les commits portent un prefixe d'agent connu."* ; *"0 commit(s) le touchent dans la plage, tous [THEO]"* |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files` filtre dans `verifier-chemins.sh` | deux sorties 0 ; *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | les treize juges (`outils/*-test.sh` 6, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 5) | treize sorties 0. **Ecart au workflow** : l'interpreteur Python est `python` et non `python3`, qui resout ici vers un faux executable du magasin d'applications |
| `build` (`mvn -B verify`) | `bash outils/mvn-jdk21.sh -B clean verify` a la racine du worktree propre, sous le jeton `RF1` | resultat dans le compte rendu de fin de lot de `lea-dev` a `sofia-sm`, repris par `rapport-lea-dev-palier-4.md` (`K7` du palier 4, pas encore ecrit) ; **pas ici** : ce fichier est commite avant la mesure qui le porte |

Lignee : `bash outils/verifier-lignee.sh` dans le worktree, sortie 0.

Le job `build` du workflow comporte aussi *"Package du fat jar"* (`mvn -B -pl bootstrap -am package`) : **non lance** a part ; `verify` produit le jar, que le lot a joue.
