# s57, palier 4, poste p4-9 - rapport de `sofia-sm` (casquette développeuse, instance A'), lot `LP6`

- **Story** : `S57-BR-EMC-CE1-04`, paquet `ce1-emc-interet-commun`, ligne `EMC-CE1-04`. **Prix** : 1,0.
- **Casquette** : développeuse uniquement. Rien n'est fusionné, aucune lignée n'est jugée, aucun amendement de `planning.md` n'est animé.
- **Date** : 2026-10-22. **Branche** : `s57-sofia-emc-ce1-04`, worktree `recreo-s57-sofia-emc-ce1-04`.
- **Base** : `a65341cb` (sommet de `round-two` après la fusion de `LP5`). `git merge-base --is-ancestor a65341cb HEAD` sort 0. Aucune fusion, aucun rebase, aucun reset, aucun push.
- **Commit de code** : `e85a93fe` (12 fichiers, 1 853 lignes ajoutées, 0 supprimée). **Ce rapport** est un second commit qui ne touche que ce fichier.
- **Ouverture** : la condition de la composition (*"un verdict de `LP4` ou `LP5` sans refus"*) est remplie d'après `FICHE-bandit-manchot-droit-devoir-ce1.md` (rejeu du 2026-10-22, `LP4` puis `LP5` : VALIDE SOUS RESERVE). La recommandation de `nadia-prof` (`arbitrage-nadia-palier-4.md` 3.3, *"ne pas le prendre"*) est maintenue par elle et **n'est pas levée par moi** : le lot est ouvert sur la demande de l'utilisatrice du 2026-10-22, elle le dit elle-même dans la fiche (*"c'est une recommandation ; la décision est à `camille-po`"*).

## 1. Ce qui est livré

| Fichier | Rôle |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/ce1-emc-interet-commun.yaml` | le paquet : 24 items, type `question`, aucun texte support, aucune voix |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | entrée `bandit-manchot-interet-commun-ce1`, titre servi *"Pour un seul ou pour tous"*, consigne de `LP4`/`LP5` reprise, `nombreDeManches: 10` |
| `PaquetCe1EmcInteretCommunTest.java` + `contreexemple/PaquetCe1EmcInteretCommunTest/` | le test du paquet (65 tests) et ses jeux fautifs (44 items sondés, 2 en-têtes, 4 catalogues) |
| `TexteAccentueExposeParLePortEntrantTest.java` | trois ajouts (identifiant, titre, compétence), aucun retrait |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | une entrée `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, aucun retrait |

`git diff a65341cb HEAD -- '*Test.java' | grep -c '^-[^-]'` rend 0 : aucune ligne retirée d'un fichier de test, aucune assertion affaiblie. Aucun garde-fou de `bootstrap` n'est modifié. **Aucun identifiant neuf n'est écrit** : `INT-CE1-1`, `INT-CE1-2` sont dans `REGISTRE-identifiants.md` (lignes `INT-CE1-1` et `INT-CE1-2`), le reste est existant (`NRC-CE1-1`, `TRV-CE1-1`, `-2`, `-5`, `-6`, `LON-CE1-1`, `-2`, `-5`, `FER-1`, `FER-2`, `EMC-1`, `EMC-5`, `EMC-6`). Les libellés `portee un` / `portee tous` et `famille a` / `famille b` sont des étiquettes de commentaire du paquet, pas des conditions. `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` est vert. **Rien à router à `theo-archi` pour le registre.**

## 2. Le paquet

Une scène d'une phrase, un personnage nommé, une seule action ; la question est toujours *"Pour qui est-ce utile ?"* (espace insécable). Les trois propositions sont : les deux formules de `INT-CE1-2` (*pour une seule personne*, *pour toute la classe*) et un nombre d'élèves entre les deux (*pour quelques élèves*, *pour deux élèves*, *pour trois élèves*). La bonne réponse est l'une des deux formules : **13 items pour toute la classe, 11 pour une seule personne**. Chaque nombre est servi avec les deux bonnes réponses.

- **Portée un** (11 items) : une chose qui ne sert qu'à celui qui la fait, la scène porte un possessif (*Léo taille son crayon*, *Aya lace ses chaussures*, *Zoé boit l'eau de sa gourde*).
- **Portée tous** (13 items) : une chose que chacun utilise, la scène nomme un lieu ou un objet partagé, jamais un possessif (*Mila essuie le tableau avant la leçon*, *Samir remet les chaises en place dans la salle*, *Hugo tient la porte ouverte pour chacun*).
- Aucune scène ne met un personnage en balance avec le groupe ; aucune ne juge celui qui veut quelque chose pour lui.
- Aucun adulte n'est nommé, aucun lieu de vie hors de l'école (`EMC-6`), 15 prénoms dont 7 féminins et 8 masculins, les 15 servis.
- **Voisinage** : `ce1-emc-biens-communs` (`EMC-CE1-01`) sert déjà *"à tout le monde"* et *"à une seule personne"* pour des **objets**. Ici, l'action et celui qu'elle sert, jamais l'objet ni son appartenance. Les scènes de portée un ne reprennent aucun objet de ce paquet (le test `NRC-CE1-1` ne compare pas les paquets de CE1 entre eux : c'est à lire).

## 3. Critères de la story

| Critère | Commande, sortie | Vert |
|---|---|---|
| Existence d'abord | `ls adapters/adapter-content/src/main/resources/contenu \| grep -c '^ce1-emc-interet-commun'` : **0** sur `a65341cb` (`git ls-tree -r a65341cb --name-only \| grep -c ...`), **1** sur `e85a93fe` | oui |
| 1, un paquet pour une ligne | `grep -l 'lignesDeReferentiel: \["EMC-CE1-04"\]' .../contenu/*.yaml \| wc -l` rend 1 ; `grep -c 'bonneReponse:' F` rend 24 | oui |
| 2, `INT-CE1-2`, mots | `grep -ciE 'intérêt général\|bien commun' F` rend **0** sur le fichier entier, commentaires compris ; le test le garde sur chaque champ lu **et** sur le fichier (`int2AucuneExpressionInterditeNEstLueParLEnfantNiEcriteDansLeFichier`, sondes 09, 10 et un fichier fautif en commentaire) ; `nombre de formules` : les deux formules sont dans chacun des 24 items | oui |
| 3, `INT-CE1-1` | **non garde sur le fond** : *"une seule lecture defendable"* est lue par `nadia-prof`, aucune mesure. **Gardé** : la portée déclarée de chaque scène est celle de sa bonne réponse, dite par un mot d'une liste fermée écrite dans le test (possessifs ; tableau, cour, ballons, étagère, coin, craies, jeu, jeux, chacun, salle, porte), sans jamais dire les deux ; aucune conjonction d'opposition (*mais*, *alors que*, *tandis que*, *plutôt que*, *au lieu de*, *pourtant*, *contre*) ; aucun mot de jugement (*égoïste*, *honte*, *méchant*, *gentil*, *vilain*, *faute*, *tort*, *gêne*, *dérange*, *jaloux*) | oui pour ce qui se garde |
| 4, clauses de forme | `grep -vE '^\s*#' F \| grep -cE 'texteSupport\|texteAVoixHaute'` rend 0 ; propositions de six mots au plus (`eb6`) ; indices de douze mots au plus et 46 caractères au plus, **24 distincts sur 24** (`grep '^    indice:' F \| sort -u \| wc -l` rend 24) ; trois propositions distinctes, familles nommées en commentaire à côté de chaque item (`TRV-CE1-2 : a, b`) ; bonne réponse strictement la plus longue dans 11 items sur 24 (garde : 12 au plus) | oui |
| 5, entrée de catalogue | `grep -c "paquet: ce1-emc-interet-commun$" catalogue.yaml` rend 1 ; `grep -c '^  - id:' catalogue.yaml` rend **163** (162 sur `a65341cb`) ; `nombreDeManches: 10`, titre sans le niveau (`ADR-0078`), espace insécable avant `?` ; `ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest` vert, fichier non modifié | oui |
| 6, Done technique | section 5 | oui, pour les jobs nommés |

## 4. Ce que le test garde, et le jeu fautif qui chaque fois le mord

| Propriété | Garde | Sonde |
|---|---|---|
| Familles du commentaire d'en-tête : la famille a est exactement les deux formules, la famille b des nombres | `verifierLesFamillesDuCommentaire` | deux en-têtes fautifs |
| Bonne réponse = une formule ; les deux formules dans chaque item ; chaque distracteur dans sa famille déclarée | `verifierLesFormules` | 01, 02, 03, 40 |
| `FER-1` : chaque formule bonne réponse d'au moins 5 items | `verifierQueLesDeuxFormulesSontEnBonneReponse` | 36 |
| `ADR-0086` clause 1 : un nombre d'élèves n'est jamais servi avec une seule bonne réponse | `verifierQueChaqueNombreSertLesDeuxReponses` | 36 |
| `INT-CE1-1` : portée déclarée, mot de la liste fermée, jamais les deux | `verifierLaPortee` | 04, 05, 06, 07, 08, 31 |
| `INT-CE1-2` : expressions interdites, champs et fichier | `verifierLesMotsInterdits` | 09, 10, fichier fautif |
| `INT-CE1-1` : aucune mise en balance, aucun jugement | `verifierQueLaSceneNeBlameJamais` | 11, 12, 13 |
| Point d'audit commun : aucune proposition ne reprend un mot plein de la scène ni de l'indice ; ni négation, ni pronom en tête, ni majuscule, ni chevrons | `verifierQuAucunePropositionNeReprendLaScene`, `verifierLaForme` | 14, 15, 16, 17 |
| `NRC-CE1-1` : aucune proposition ni aucun énoncé d'un des six paquets (`cp-emc-regles-de-vie`, `cp-emc-regle-souhait-loi`, `cp-emc-droits-enfant`, `cm1-emc-regle-loi`, `cm1-emc-droit-devoir`, `cm1-emc-regles-vie-collective`) | `verifierQueRienNEstReprisDUnPaquetVoisin` | 37 (énoncé), 38 (proposition) |
| `TRV-CE1-1`, `LON-CE1-1`, `-2`, `-5`, `TRV-CE1-5`, `TYP-1`, `EMC-1`, `TRV-CE1-6`, pli | un verificateur chacun | 18 à 30, 32 à 35, 39 |

**Mutation sur le paquet réel, rejouée** : item 2 passé de *Léo taille son crayon.* à *Léo taille le crayon.* (la déclaration de portée reste *un*). `-pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest` : **rouge**, un seul échec, `int1LaPorteeDeclareeEstCelleDeLaBonneReponseEtDeLaScene`. Fichier restauré, `git status --short` vide ensuite.

**Contrainte rencontrée en route, tenue par le contenu et non par une exemption** : `ADR-0086` clause 1 rougit un groupe de trois items ou plus aux propositions identiques **et** à la même bonne réponse. Avec deux formules fixes, un nombre d'élèves servi avec une seule bonne réponse le ferait. Les trois nombres tournent (rang modulo 3) et chacun est servi avec les deux bonnes réponses ; clause 2 (amorce de quatre mots) : les nombres tiennent en trois mots, aucune amorce de distracteur n'existe seule. Clause 3 (marqueur *seul*) : le nombre n'en porte pas, donc aucun item n'a *seule* sur tous ses distracteurs et sur aucune part de sa bonne réponse. `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` est vert, fichier non modifié, `DETTE_D_AMORCE` non touchée.

## 5. Porte technique : jobs de `ci.yml` lancés

Commit vérifié : `e85a93fe`, arbre propre (`git status --short` vide avant le lancement).

| Job | Commande | Sortie |
|---|---|---|
| `build`, étape Verify | `bash outils/mvn-jdk21.sh -B clean verify` à la racine du worktree, **jeton RF1 pris à 23:08:14 (aucune attente : `mkdir` a réussi du premier coup) et rendu à 23:20:50** | **exit 0**, 23:08:29 à 23:20:50, `BUILD SUCCESS`, 6 modules |
| `build`, étape Package du fat jar | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` | **exit 0**, `BUILD SUCCESS`, `bootstrap/target/recreo.jar` produit. **Écart avec `ci.yml` (`mvn -B -pl bootstrap -am package`) : j'ai passé `-DskipTests`**, les tests de ce commit sont ceux du Verify ci-dessus |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `git log --no-merges --format=%s a65341cb..HEAD \| bash .github/scripts/verifier-prefixes.sh`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh a65341cb..HEAD` | **exit 0** pour les cinq (sur `e85a93fe`) |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **exit 0**, *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **exit 0**, 13 juges purs exécutés |

Les jobs `build`, `prefixes`, `chemins`, `outils` et l'étape *Package du fat jar* de `ci.yml` sont lancés en local. **Cela ne vaut pas la CI** : celle de GitHub n'a pas tourné sur ce commit (aucun push). `prefixes` et `chemins` sont à relancer sur le commit de ce rapport et sur le commit de fusion.

**Tests** (surefire sur l'arbre de `e85a93fe`, par module, dans l'ordre du réacteur) : domain 120, application 31, adapter-web 523, adapter-content 2 162, bootstrap 381 plus 6 d'intégration : **3 223**, 0 échec, 0 erreur, 0 ignoré.

**Delta** : **+65** tests, tous dans `PaquetCe1EmcInteretCommunTest` (`git diff a65341cb HEAD -- '*Test.java' | grep -c '^+.*@Test'` rend 65). **Le total de la base n'est pas mesuré par moi** : 3 223 moins 65 donne 3 158, c'est une déduction, pas une mesure. Le total après fusion est à `yanis-qa` (rapport qualité d'un lot de A', `composition-palier-4.md` 5.3 point 3).

**Mesures partielles préalables** (toutes avec `-am`, périmètre nommé) : `-pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest` : 65 verts ; `-pl bootstrap -am` sur `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (9), `TexteAccentueExposeParLePortEntrantTest` (10), `ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest` (4), `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (8), `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (28) : verts.

**Jar** : aucun jar n'est lancé, aucun port n'est utilisé.

## 6. Ce que `nadia-prof` doit juger

Aucun compteur ne le voit. `INT-CE1-1` est, de son propre mot, le critère le plus difficile des quatre lignes d'EMC : *"lu par `nadia-prof`, aucune mesure"*.

1. **J1, lecture complète** (`LC`) : `lecture complète : ce1-emc-interet-commun, 24 items lus`, à ses mains. Les 24 scènes, 48 distracteurs, 24 indices, en-tête.
2. **J2, une seule lecture défendable** (`INT-CE1-1`, `EMC-5`), scène par scène. Les scènes que je lis le plus fragilement : *Samir apprend sa poésie* et *Adam relit sa dictée* (portée un : un enfant peut les lire comme utiles aussi à celui qui écoute ou corrige) ; *Noé balaie le sol de la salle* et *Samir remet les chaises en place dans la salle* (portée tous : le geste sert ceux qui occupent la salle, pas seulement la classe nommée) ; *Hugo tient la porte ouverte pour chacun* ; *Léo porte la caisse de jeux jusqu'à la cour*.
3. **J3, la troisième proposition** : *pour quelques élèves*, *pour deux élèves*, *pour trois élèves* ne sont jamais la bonne réponse. Elles laissent à l'enfant un choix à deux, deux formules fixes dans chaque item. Est-ce un défaut de la tâche ou sa forme ? Le choix du nombre dit à l'enfant qu'une scène sert *toute la classe* ou *une seule personne*, sans zone intermédiaire : sont-elles assez peu défendables pour qu'aucune scène n'admette *deux élèves* ?
4. **J4, `TRV-CE1-6`, objet possédé** : les scènes de portée un font dire *son crayon*, *sa gourde*, *son manteau*, *sa trousse*, *son cartable*, *ses lunettes*, *sa veste*. La condition écrit *"rien qui suppose ... un objet possédé"*. Ce sont des objets d'élève à l'école (même régime que *"son cahier"* dans `ce1-emc-droit-devoir`, validé au rejeu) : à elle de dire si cela vaut ici, où le possessif **est** le moyen de dire la portée.
5. **J5, la question *"Pour qui est-ce utile ?"*** : identique sur les 24 items. *Utile* convient-il à *Mila essuie le tableau* et à *Aya lace ses chaussures* ? Le seuil de lisibilité de CE1 (*traînent*, *essuie*, *balaie*, *boutonne*, *efface*) est à elle.
6. **J6, `NRC-CE1-1` sur le fond** : mon garde compare les textes avec six paquets, jamais les sujets. `cp-emc-regle-souhait-loi` classe une situation en deux catégories (`DEC-0120`, voisin désigné dans l'instruction) ; ce paquet classe aussi, avec une question différente. Le voisin de CE1, `ce1-emc-biens-communs`, est à elle (section 2).
7. **J7, les indices** (`TRV-CE1-5`, relus par moi) : 24 questions de méthode du type *Qui se sert du tableau ?*, aucune ne contient une proposition. *Qui s'assoit sur ces chaises ?*, *Qui passe par cette porte ?* disent-elles la portée par leur sujet ? Un indice qui mène à un distracteur est ce qu'`LP5` a perdu au premier passage : je n'en vois pas ici, elle seule le dit.
8. **J8, aucun blâme** (refus écrit d'avance) : aucun mot de jugement, aucune conjonction d'opposition. Ce que le texte ne dit pas mais fait lire est à elle.

## 7. K8 : chaque point d'audit a un test ou *"non gardé"*

| Point | État |
|---|---|
| `INT-CE1-2`, les mots interdits | **gardé** (champs et fichier entier) |
| `INT-CE1-2`, les propositions disent les deux formules | **gardé** |
| `INT-CE1-1`, une seule lecture défendable | **non gardé : le sens** ; gardé : la portée déclarée contre la bonne réponse et contre une liste fermée de mots de la scène |
| `INT-CE1-1`, aucun blâme | **gardé sur les mots** (opposition, jugement) ; **non gardé : un blâme porté par le sens** |
| Aucune forme ne donne la réponse | **gardé** : majuscule, chevrons, négation, pronom en tête, mot plein repris de la scène ou de l'indice, longueur ; **non gardé : le sens de la scène lui-même** |
| `NRC-CE1-1` | **gardé sur l'égalité des textes** (propositions et énoncés) avec six paquets ; **non gardé : le sujet, les paquets de CE1 voisins** |
| `EMC-1`, `EMC-6` | **gardé** (aucune adresse à l'enfant, aucun mot du foyer, personnage nommé) ; non gardé : aucun lieu de vie hors ce que le texte dit (lecture) |
| `TRV-CE1-6` | gardé sur l'équilibre des prénoms ; non gardé : les rôles, l'objet possédé |
| `LON-CE1-1`, `-2`, `-5`, `TRV-CE1-1`, `-5` | **gardés** (test du paquet, et `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` vert) |
| Pli à 744 x 551 | seuils de `LP4` gardés (112, 46) ; **non mesuré** sur ce paquet |
| Mots de la liste fermée de portée | **déclaration d'auteur** (liste écrite dans le test, comme les mots de couple de `LP5`) : le test prouve la cohérence entre ce que l'item déclare et ce que la liste dit, jamais que la liste dit vrai |

## 8. Questions routées (RG5)

| Q | Destinataire | Question |
|---|---|---|
| Q1 | `nadia-prof` | J2 à J8 ci-dessus, dont J3 (choix à deux) et J4 (objet possédé), les deux où ma lecture est la plus fragile |
| Q2 | `ines-design` | relevé à 744 x 551 de ce paquet : énoncé de 112 caractères au plus et indice de 46 au plus sont les seuils de `LP4`, pas une mesure d'ici ; les propositions font 16 à 23 caractères |
| Q3 | `camille-po` | rien à trancher au branchement : la cellule `EMC-CE1-04` de la matrice n'est pas touchée (`git diff a65341cb HEAD --stat -- squad/programme/MATRICE-couverture.md` vide) ; la fiche `FICHE-bandit-manchot-interet-commun-ce1.md` est ouverte par `nadia-prof`. La recommandation de 3.3 (pas de lot `-04` à A') est une décision de `camille-po`, non reprise ici |
| Q4 | `theo-archi` | aucune question. Lignée de la branche à lire (`git merge-base --is-ancestor a65341cb HEAD` sort 0, seul fait que je pose) ; aucun identifiant neuf, rien à allouer |

## 9. Ce que je n'ai pas fait

- Je n'ai pas joué le jar, ni lancé de port.
- Je n'ai pas mesuré le pli, ni lu le paquet avec le regard de `nadia-prof` : `TRV-CE1-5` est relu par moi, `INT-CE1-1`, `EMC-5`, `NRC-CE1-1` sur le fond et le refus *"un personnage mis en balance avec le groupe pour lui faire honte"* sur le sens sont à elle.
- Je n'ai pas lancé `verifier-lignee.sh` : la lignée est lue par `theo-archi`.
- Je n'ai ni poussé, ni rebasé, ni fusionné, ni écrit dans `planning.md`, ni touché la matrice, ni écrit de fiche de conformité.
- Je n'ai pas relancé `mvn package` sans `-DskipTests` : ce serait un second passage de la suite complète hors jeton.

## 10. Isolation

Répertoire de scratchpad utilisé : `s57-p4-sofia-lp6` (créé par `mkdir -p` en première commande). Seule exception : le répertoire de jeton RF1 `s57-p4-jeton-verify`, créé par `mkdir` à la racine à 23:08:14 (fichier `sofia-dev-lp6-HHMM.txt`), supprimé à 23:20:50 ; `ls` de la racine après coup ne le liste plus. Rien n'a été écrit hors du répertoire de l'agent, du jeton et du worktree. Les fichiers de scratchpad (scripts de génération du paquet et du jeu fautif, journal du verify) ne sont pas commités.
