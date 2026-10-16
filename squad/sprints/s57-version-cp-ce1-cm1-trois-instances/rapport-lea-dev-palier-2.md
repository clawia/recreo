# [Sprint s57] Palier 2, p2-7 - `lea-dev` : forme commune des propositions longues

- **Date** : 2026-10-16.
- **Story** : `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` (`docs/backlog/backlog.md`). Prix 0, hors `PAR`.
- **Base** : `aa9d703c` (`git log -1 --format=%h` au premier message, sommet de `round-two` a la coupe). Branche `s57-lea-forme-commune`, worktree dedie.
- **Commit livre (code et tests, meme commit)** : `dc6f423d`. Le commit qui porte ce fichier ne touche que `squad/`.
- **Version de mesure** : JDK 21.0.10, valeur unique de `java.version` dans les 117 rapports Surefire de `adapter-web` et de `bootstrap`. Le jar joue annonce `Java 21.0.10` dans son journal.
- **Ce que ce rapport ne fait pas** : il ne prononce aucun Done, ne leve aucune reserve (`S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2` : `nadia-prof`), ne juge pas une cote de reference (`ines-design`).

## 1. Ce qui est livre

- **`recreo.css`** : un bloc `@media (min-width: 720px)` de 34 lignes ajoute apres le bloc `L1` a `L4` de l'ecran a deux oeuvres. Ce sont les regles `R3-1` a `R3-5` de `candidat-r3.css`. Comparaison faite apres suppression des commentaires et normalisation des blancs : le texte des regles est identique au candidat (1091 caracteres de chaque cote). Seuls les commentaires sont reecrits (le candidat disait "NON versee").
- **`LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`** (adapter-web) : 4 tests.
  - Les 14 declarations de la forme sont dans la feuille, sous `@media (min-width: 720px)`, avec les valeurs de la mesure.
  - La population jugee est derivee : toute regle maison dont le selecteur contient `:not(.ecran-deux-oeuvres)`. Une regle de cette population hors du seuil, ou absente de la liste mesuree, rougit.
  - Trois feuilles fautives dans `src/test/resources/contreexemple/` : regle hors seuil, valeur changee, regle ajoutee.
  - Morsure sur la production : `row-gap: 0.5rem` remis a `0.75rem` dans `recreo.css` rend le test rouge (1 echec sur 4, message nomme le selecteur et les deux valeurs) ; fichier restaure, `git diff` revient a 34 lignes ajoutees.
- **Aucun autre fichier de `domain`, `application`, `bootstrap`, aucun paquet, aucun `catalogue.yaml`, aucune `ADR`.**

## 2. Tests

- **Delta : +4 tests, mesure sur `aa9d703c` plus ce seul lot.** `adapter-web` 492 a 496. Les autres modules sont inchanges (120, 31, 1567, 381, 6 d'integration). Le total de ce worktree est 2601 ; **le total apres fusion est a `sofia-sm`.**
- **ArchUnit : inchange.** Methode de `avis-theo-palier-2.md` section 6 (fichiers `*.java` suivis par `git ls-files`) : 41 `static final ArchRule` et 38 `@ArchTest`. Aucune regle nouvelle.
- Les quatre classes que `theo-archi` demande de nommer (`avis-theo-palier-2.md` 2.6) ont tourne dans le reacteur complet, vert : `CabinetDeLaMachineTest`, `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`, `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest`. Je ne les ai pas lancees une a une, ni mordues.
- Aucune regle de la forme ne nomme une classe en `cabinet` : le jeu fautif de `CabinetDeLaMachineTest` n'est pas touche (critere 7).

## 3. Jobs de `ci.yml` lances, sur ce worktree

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0. 2601 tests, 0 echec (120, 31, 496, 1567, 381, 6), lignes `Tests run` de la sortie Maven. Lance avant le commit, sur l'arbre qui est celui de `dc6f423d`. Relance une seconde fois avec ce fichier indexe (des tests de `bootstrap` lisent `squad/`) : meme resultat, `BUILD SUCCESS`, 2601 tests, 0 echec ; le build a lu ce fichier avant l'ajout de cette ligne. L'etape *"Package du fat jar"* (`-pl bootstrap -am package`) n'est pas lancee comme telle ; le jar que j'ai joue vient de ce `clean verify`.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0. Les sujets de `aa9d703c..dc6f423d` passent dans `verifier-prefixes.sh`, sortie 0. Le proprietaire du garde-fou de l'accent (`verifier-garde-fou-accent.sh "dc6f423d^!"`) sort 0 : aucun de ses chemins n'est touche.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0.
- **`outils`** : les trois balayages rendent 5, 2 et 5 fichiers (12 juges), aucun balayage vide. **11 sortent 0 ; `outils/mvn-jdk21-test.sh` sort 1** : 10 cas en echec (*"l'enveloppe ne refuse pas comme annonce"*, par exemple *attendu : 1|mvn non lance, obtenu : 0|mvn non lance*). **Je ne l'ai pas cause** : ce diff ne touche pas `outils/`, et le meme script, lance sur le checkout principal (tip `4004fab7`, arbre propre), rend le meme echec. Je l'ai essaye sans `JAVA_HOME`, puis sans le JDK 21 du `PATH` : meme echec. **Cause non trouvee.** Le job `outils` est donc rouge sur ma session sans que je sache pourquoi ; `sofia-sm` l'a lance vert sur la sienne (planning, section 12). A reproduire par elle.

## 4. Ce que j'ai joue (jar du commit livre)

Jar de `dc6f423d`, port 18532, PID lu dans le journal du jar et arrete par ce PID. Chrome 744 x 551, `pilote-r3.mjs` sans feuille candidate (la forme est dans la feuille servie), `agreger-r3.mjs`. `items.json` extrait du YAML des paquets, hors depot.

| Critere | Mesure | Resultat |
|---|---|---|
| 1 | bas de `.indice` apres une erreur, `ce1-phrases-types`, `ce1-accord-groupe-nominal`, `ce1-verbe-sujet`, ordre servi | 24, 24 et 24 sur 24 au plus 551 (72 sur 72). Maxima : 548, 548, 496 |
| 2 | meme mesure dans les six ordres des propositions, 144 couples par jeu | au plus 548 pour les trois jeux (maximum 548, 548, 496 ; pire ordre de chaque item au-dessus de 551 : 24 sur 24 pour chaque jeu) |
| 3, `ce1-texte-narratif` | 20 items, apres une erreur | 18 sur 20 au plus 551 en ordre servi, 17 sur 20 au pire ordre. Les trois items au-dessus sont 13, 17 et 18 (comptes depuis 0), a 552 : c'est ce que dit le critere, `S57-RCT-CE1-R1` |

- **Regarde a l'ecran** (captures de l'item 4 apres erreur et de l'item 19 a la revelation, `ce1-phrases-types`, 744 x 551) : l'indice est entier sous le meuble, sans defilement. Le score est colle sous la bande de l'en-tete (effet de `R3-1`, condition de `nadia-prof` au rejeu, `reponse-nadia-q-palier-2.md` 3.1). A la revelation, la phrase a presser touche le bord bas de la fenetre (cote 550) : c'est l'observation de `PA4`, je n'en dis rien de plus.
- Aucune reponse affichee dans un enonce, aucun texte de l'enfant modifie : le diff ne touche aucun contenu.

## 5. Ce que je n'ai pas mesure

- **Critere 3, hors `ce1-texte-narratif`** : les 65 enonces d'`additions-cp` (script de `S57-SCRIPT-ADDITIONS-CP`, `marc-dev`), les 20 items de `comprehension-cm1`, *"les gateaux"* (le critere ne dit pas a quel jeu cette expression renvoie). Les 11 items a 550 de `ce1-texte-narratif` ne sont pas identifies dans ma sortie : je n'ai pas de jar de base, je ne sais pas lesquels des 20 etaient a 550.
- **Critere 4, cote de reference (`Q-R5`)** : elle est a `ines-design`, et le fichier date n'existe pas dans mon arbre. Ce que j'ai verifie : `git merge-base --is-ancestor aa9d703c dc6f423d` sort 0 ; `git diff --stat aa9d703c dc6f423d` ne nomme aucun fichier sous `contenu/` ni `catalogue.yaml` (5 fichiers : la feuille, le test, les trois feuilles fautives). **Condition de `Q-P4` : non tenue, comme `planning.md` le dit** : `git diff --stat 6dbb855d aa9d703c -- pom.xml domain application adapters bootstrap` rend 10 fichiers, 10 lignes ajoutees et 10 supprimees (en-tetes de paquets). La cote se remesure sur `aa9d703c`, par `ines-design`.
- **Critere 5** : `git diff aa9d703c..HEAD -- domain application adapters/adapter-web bootstrap`, lignes ajoutees, filtre sur les quatre noms de paquets : 0. Mesure par commande, pas par test : le test qui garde la feuille contre un nom de paquet est `AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest` (ADR-0094), que le reacteur a lance vert.
- **Critere 6** : l'echantillon de 40 jeux a 720, 744 et 992 n'est pas rejoue. Il demande une mesure appariee avec et sans la forme, donc un second jar de la base ; je n'en ai pas construit.
- **Largeurs 720, 768, 992 et 1200** pour les trois jeux : non mesurees (744 seul). Autres polices : non mesurees.
- **`R3-1`, ascenseur a 720** (libelles qui changent de nombre de lignes) : conditions de `nadia-prof` au rejeu, pas des criteres. Non mesurees.

## 6. Ce qui reste ouvert, et a qui

- **`TP6`, `Q-T15`** : la fusion attend la trace de lecture de `theo-archi` sur `dc6f423d` (points 1 a 3 de `C1'`, `C2`, `C3`, `C1'` sur le diff livre). Elle n'est pas dans mon arbre. `git diff --stat dc6f423d <sommet de la branche> -- adapters domain application bootstrap` doit rester vide, ou le delta est relu : le commit de ce rapport ne touche que `squad/`.
- **`TP4`, `Q-T14`** : aucune garde de `C1'` n'est ecrite. Mon test ne la remplace pas : il pose les valeurs de la forme et son seuil, il ne juge pas quels etats du verdict une regle de l'ecran de manche peut nommer.
- **Question de decision, non tranchee par moi** : mon test epingle les 14 declarations. Toute retouche ulterieure de la forme (un second pixel pour `S57-RCT-CE1-R1`, une correction apres le rejeu de `R3-1`) amende ce test dans le meme commit, et c'est voulu : une valeur qui bouge change la cote. Si `theo-archi` ou `sofia-sm` prefere un epinglage plus lache (population et seuil seulement, sans les valeurs), c'est une decision d'architecture de test, pas la mienne.
- **Job `outils` rouge sur ma session** : section 3, cause non trouvee.
- **Criteres 3 (reste), 4, 6** : section 5. Aucun n'est a moi sauf si `sofia-sm` en decide autrement.
- **Rejeu** : `S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2` ne se rejouent pas avant la cote ecrite en entier (`arbitrage-nadia-palier-2.md` 8, point 4). Le jar du commit livre est le jar de leur rejeu.

## 7. Isolation

- Repertoire `s57-p2-lea-dev-ap`, port de jeu 18532, port de debogage 19232. Avant lancement : `netstat -ano` filtre sur `18532` et `19232` rend 0 ligne.
- Jar : *"Tomcat started on port 18532"* et le PID lus dans son journal ; arrete par ce PID. Apres : aucune ecoute sur 18532 ni 19232 (une ligne `TIME_WAIT` de la connexion de mesure, sans processus). Deux Chrome lances par `pilote-r3.mjs`, leurs PID lus sur sa sortie d'erreur : absents de `tasklist` a la fin du script, rien a arreter.
- Tout ce que j'ai ecrit hors depot (journaux de build et de jar, profils de Chrome, captures, sorties JSON, journaux des juges, un `TMPDIR` pour les juges) est dans ce repertoire. Je n'ai deplace ni supprime aucun fichier que je n'ai pas cree.
- **Racine du scratchpad et repertoire parent des worktrees : listes en fin de session, voir le message de rendu.**
