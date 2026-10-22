# s57, palier 4, poste p4-9 - rapport de `sofia-sm` (casquette développeuse, instance A'), lot `LP6`, reprise après le REFUSE

- **Story** : `S57-BR-EMC-CE1-04`, paquet `ce1-emc-interet-commun`, ligne `EMC-CE1-04`. **Prix** : 1,0 au premier passage, reprise estimée 0,5 par `nadia-prof` (non mesuré).
- **Casquette** : développeuse uniquement. Rien n'est fusionné, aucune lignée n'est jugée, aucun amendement de `planning.md` n'est animé.
- **Date de la reprise** : 2026-10-22 au soir, 2026-10-23 pour le `verify`. **Branche** : `s57-sofia-emc-ce1-04`, worktree `recreo-s57-sofia-emc-ce1-04`.
- **Départ de la reprise** : `cbfb152e`, arbre propre (`git status --short` vide), `round-two` toujours à `a65341cb` : aucune fusion n'était à faire. `git merge-base --is-ancestor a65341cb HEAD` sort 0. Aucune fusion, aucun rebase, aucun reset, aucun push.
- **Commit de code de la reprise** : `ec2d572a` (3 fichiers, 279 lignes ajoutées, 73 supprimées). **Ce rapport** est le commit suivant et ne touche que ce fichier. Le sommet de la branche est donc le commit de ce rapport, lu par `git rev-parse --short HEAD`.
- **Fiche lue** : `FICHE-bandit-manchot-interet-commun-ce1.md`, commit `705eb2b7` de la branche `s57-nadia-p4-lp6` (non fusionnée), verdict **REFUSE** sur `cbfb152e` : motifs M1 et M2, trois réserves.

## 1. Ce qui change

| Fichier | Changement |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/ce1-emc-interet-commun.yaml` | 24 items, 13 de portée tous et 11 de portée un comme avant ; 9 scènes neuves, 2 scènes déplacées de la cour à la salle, 9 retirées, 13 inchangées ; chaque item déclare une **appartenance d'objet** en plus de sa portée ; en-tête réécrit |
| `PaquetCe1EmcInteretCommunTest.java` | l'appartenance est déclarée à part de la portée ; un garde neuf ; 4 tests de plus (69 au lieu de 65) |
| `contreexemple/PaquetCe1EmcInteretCommunTest/contenu/ce1-emc-fautif.yaml` | une déclaration d'objet sur chaque item qui déclare une portée, 7 items neufs pour 3 sondes neuves (41, 42, 43) |

Aucun autre fichier. `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` et les garde-fous de `bootstrap` ne sont pas touchés. Le titre, la consigne et le nombre de manches sont inchangés.

## 2. M1 : le possessif ne donne plus la réponse

**Répartition des 24 scènes par portée et par appartenance de l'objet** (comptée sur le texte, pas sur les déclarations) :

| | objet personnel (possessif) | objet partagé (lieu ou objet commun) | scènes où le possessif se trompe |
|---|---|---|---|
| **portée une seule personne** (11) | 7 | **4** | 4 sur 11 (36 %) |
| **portée toute la classe** (13) | **5** | 8 | 5 sur 13 (38 %) |

Une règle qui répond *une seule personne* sur *son*, *sa*, *ses* et *toute la classe* sinon a bon sur **15 scènes sur 24** (au lieu de 24 sur 24 au premier passage, 30 sur 30 manches jouées par `nadia-prof`). Le seuil de la fiche, un tiers au moins par portée, est tenu avec une marge d'une scène en portée un (4 sur 11 pour 3,67 exigées) et de deux en portée tous (5 sur 13 pour 4,33).

**Scènes neuves, portée une seule personne, objet partagé** (aucun possessif, un lieu ou un objet commun, sans blâme) :
- *Aya prend un livre de la bibliothèque pour le lire.* (exemple de `nadia-prof`)
- *Enzo boit au robinet de la cour.* (exemple de `nadia-prof`)
- *Mila prend des ciseaux dans la boîte du coin dessin pour découper.*
- *Hugo cherche un mot dans le dictionnaire du coin lecture.*

**Scènes neuves, portée toute la classe, objet personnel** (un possessif, un geste qui sert tous) :
- *Samir apporte sa plante pour décorer la salle.* (exemple de `nadia-prof`, dont *la classe* est remplacé par *la salle* : *classe* est un mot plein de la proposition *pour toute la classe*, et le garde d'écho refuse qu'une scène le reprenne)
- *Lina colle son affiche des horaires au mur.*
- *Maya écrit son annonce de la sortie au tableau.*
- *Tom essuie les tables de la salle avec son chiffon.*
- *Omar accroche sa carte au tableau pour la leçon.*

Les sept scènes de portée un et d'objet personnel qui restent sont celles que `nadia-prof` a tenues au premier passage (*Léo taille son crayon*, *Aya lace ses chaussures*, *Noé efface une erreur dans son cahier*, *Samir apprend sa poésie*, *Zoé boit l'eau de sa gourde*, *Adam relit sa dictée*, *Maya met ses lunettes pour lire*), inchangées.

**Clause 1 d'`ADR-0086`** : les deux bonnes réponses sont servies avec chaque nombre d'élèves. Les trois nombres tournent à l'intérieur de chaque portée (`quelques`, `deux`, `trois` pour la portée tous ; `deux`, `trois`, `quelques` pour la portée un), chaque nombre est donc servi avec les deux bonnes réponses. `adr0086ChaqueNombreDEleveSertLesDeuxBonnesReponses` est vert et `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` aussi, fichier non modifié.

**Ce que le test fait de nouveau, à faire lire par `theo-archi` (je ne le tranche pas)** :

1. **Une déclaration neuve par item**, `INT-CE1-1 objet : personnel|partage`, à côté de `INT-CE1-1 portee : un|tous` et de `TRV-CE1-2 : a, b`. `verifierLaPortee` garde toujours que la portée déclarée est celle de la bonne réponse, puis, selon l'objet : *personnel* exige un possessif dans la scène ; *partagé* exige un mot de la liste fermée et **aucun** possessif.
2. **Ce qui est retiré**, et seulement cela : « portée un exige un possessif » et « portée tous exige un mot partagé et aucun possessif ». Ce sont les deux lignes qui enfermaient le paquet dans la forme refusée.
3. **Ce qui est conservé** : une scène de portée un et d'objet personnel ne nomme toujours aucun lieu ou objet partagé (l'ancienne sonde 07 mord toujours, son message est celui d'avant avec les mots *l'objet personnel* en plus). Un objet partagé n'a jamais de possessif (l'ancienne sonde 06 mord toujours). Une scène de portée tous et d'objet personnel peut nommer un lieu partagé (*apporte sa plante pour décorer la salle*), c'est la seule combinaison que l'ancien test refusait et que la fiche demande.
4. **La liste fermée** `PARTAGES` gagne deux mots, `bibliothèque` et `robinet`, aucun n'est retiré.
5. **Le garde neuf** `verifierQueLAppartenanceNeDecidePasDeLaPortee` : dans chacune des deux portées, trois fois le nombre de scènes où la règle du possessif se trompe est supérieur ou égal au nombre de scènes de la portée. Le calcul lit le texte des scènes (`son`, `sa`, `ses`), pas les déclarations d'auteur. Une déclaration peut donc mentir sans le tromper.
6. **Aucune assertion n'est affaiblie** au sens du dépôt : `git diff a65341cb HEAD -- '*Test.java' | grep -c '^-[^-]'` rend 0 (le test est neuf par rapport à la base). Contre `cbfb152e`, les seules lignes retirées de ce fichier sont les deux conditions de l'item 2 ci-dessus, la liste `PARTAGES` (reprise avec deux mots de plus), le Javadoc, et un message d'assertion de la sonde 06 (`déclare la portée tous et porte un possessif` devient `déclare l'objet partagé et porte un possessif`).

**Mutations sur le paquet réel, rejouées** (`-pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest`, fichier restauré après chacune, `cmp` identique à la sauvegarde) :
- **A** : *Aya prend un livre de la bibliothèque pour le lire.* devient *Aya prend son livre pour le lire.*, déclarée objet personnel : la scène de portée un à objet partagé disparaît. **Rouge, un seul échec**, `int1LAppartenanceDeLObjetNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees` : *dans la portée un, 3 scènes sur 11*.
- **B** : *Maya écrit son annonce...* devient *Maya écrit l'annonce...*, déclarée objet partagé : une scène de portée tous à objet personnel disparaît. **Rouge, un seul échec**, le même test : *dans la portée tous, 4 scènes sur 13*.

**Jeu fautif** (ADR-0013, obligation 3) : sonde 41 (objet non déclaré), 42 (trois scènes de portée un où le possessif décide de toutes), 43 (trois scènes de portée tous où l'objet partagé décide de toutes). Chacune est refusée par son vérificateur et par lui seul.

## 3. M2 : les quatre scènes du CP sont remplacées

Retirées : *Tom range les ballons dans le panier de la cour* (5), *Omar remet les livres sur l'étagère du coin lecture* (7), *Lina remet des feutres dans la boîte du coin dessin* (9), *Samir remet les chaises en place dans la salle* (19). Aucune scène de ranger ou de remettre à sa place ne reste dans le paquet. Sont aussi retirées, pour tenir 13 et 11 sans dépasser les 24 items : *Noé balaie le sol de la salle* (portée tous, un troisième geste de nettoyage), *Inès met son manteau pour sortir*, *Hugo range sa trousse dans son cartable* (la trousse est l'objet de `ce1-emc-biens-communs`), *Lina boutonne sa veste* et *Enzo colorie son dessin* (portée un, objet personnel). **Écart avec le message du commit `ec2d572a`** : il annonce 10 scènes retirées et *une de la cour* ; le compte exact est 9 retirées et 2 déplacées, ci-dessus. Les gestes qui les remplacent sont des gestes que `cp-emc-regles-de-vie` n'enseigne pas : décorer (*plante*), afficher (*affiche des horaires*, *carte*), annoncer (*annonce de la sortie*) et nettoyer avec un objet à soi (*chiffon*). Ils sont les cinq scènes de portée tous à objet personnel de la section 2, qui répondent donc à M1 et à M2 d'un même geste.

## 4. Réserves non bloquantes

1. **Items 3, 5, 23 (la cour)** : traitée **sans toucher à `INT-CE1-2`**. Aucune scène de portée tous ne nomme plus la cour (la 5 est retirée par M2, la 3 et la 23 se passent dans la salle : *Jade ramasse les papiers qui traînent dans la salle*, *Léo apporte la caisse de jeux dans la salle*). La cour n'apparaît que dans *Enzo boit au robinet de la cour*, où la bonne réponse est *une seule personne*. Je n'ai pas ajouté *pour tout le monde* comme formule : `INT-CE1-2` la dit admise, mais elle changerait l'ensemble fermé des formules du test et `ADR-0086` clause 1 sur les propositions ; c'est une question pour `theo-archi` si `nadia-prof` préfère cette variante.
2. **La troisième proposition** : ce que le paquet dit, c'est que *pour quelques élèves*, *pour deux élèves*, *pour trois élèves* **ne sont jamais la bonne réponse** (en-tête du YAML, famille b), et que chaque nombre est servi avec les deux bonnes réponses. Le test le garde : `verifierLesFormules` exige que la bonne réponse soit l'une des deux formules et que le second distracteur vienne de la famille b. Le motif M1 levé, le choix à deux ne se fait plus à la forme, mais il reste un choix à deux : je ne l'ai pas changé, la fiche dit que la forme est celle de `INT-CE1-2`. Les cinq scènes neuves de portée tous sont écrites pour qu'*aucun nombre ne soit défendable* (le geste sert tous) ; c'est à relire, voir 6.
3. **Pli** : non mesuré sur ce paquet. Énoncé de 112 caractères au plus, indice de 46 au plus, seuils de `LP4`. Le plus long énoncé neuf est *Mila prend des ciseaux dans la boîte du coin dessin pour découper. Pour qui est-ce utile ?*

## 5. Critères de la story

| Critère | Commande, sortie | Vert |
|---|---|---|
| 1, un paquet pour une ligne | `grep -c 'bonneReponse:' F` rend 24 ; `grep -l 'lignesDeReferentiel: \["EMC-CE1-04"\]' .../contenu/*.yaml \| wc -l` rend 1 | oui |
| 2, `INT-CE1-2`, mots | `grep -ciE 'intérêt général\|bien commun' F` rend 0 sur le fichier entier ; les deux formules sont dans chacun des 24 items | oui |
| 3, `INT-CE1-1` | **gardé** : portée déclarée contre bonne réponse, appartenance déclarée contre les mots de la scène, un tiers au moins de scènes qui croisent les deux dans chaque portée, aucune opposition, aucun jugement. **Non gardé** : une seule lecture défendable (`nadia-prof`) | oui pour ce qui se garde |
| 4, clauses de forme | 24 indices distincts ; propositions de six mots au plus ; bonne réponse strictement la plus longue dans 11 items sur 24 (garde : 12 au plus) | oui |
| 5, entrée de catalogue | inchangée (`catalogue.yaml` absent du diff) ; `grep -c '^  - id:' catalogue.yaml` rend 163 | oui |
| 6, Done technique | sections 6 et 7 | oui, pour les jobs nommés |

## 6. Porte technique : jobs de `ci.yml` lancés

Commit vérifié : `ec2d572a`, arbre propre (`git status --short` vide avant et après).

| Job | Commande | Sortie |
|---|---|---|
| `build`, étape Verify | `bash outils/mvn-jdk21.sh -B clean verify` à la racine du worktree, **jeton RF1 pris à 00:13:32 et rendu à 00:25:58** (`mkdir` du répertoire `s57-p4-jeton-verify` réussi du premier coup, répertoire absent après coup) | **exit 0**, `BUILD SUCCESS`, 6 modules |
| `build`, étape Package du fat jar | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` | **exit 0**, `BUILD SUCCESS`, `bootstrap/target/recreo.jar` produit. **Écart avec `ci.yml` (`mvn -B -pl bootstrap -am package`) : `-DskipTests`**, les tests de ce commit sont ceux du Verify ci-dessus |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `git log --no-merges --format=%s a65341cb..HEAD \| bash .github/scripts/verifier-prefixes.sh`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh a65341cb..HEAD` | voir section 7 |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | voir section 7 |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | voir section 7 |

**Tests** (surefire sur l'arbre de `ec2d572a`, par module, dans l'ordre du réacteur) : domain 120, application 31, adapter-web 523, adapter-content 2 166, bootstrap 381 plus 6 d'intégration : **3 227**, 0 échec, 0 erreur, 0 ignoré.

**Provenance du total** : mesuré par moi sur l'arbre de ce commit, pas relayé. Au premier passage (`e85a93fe`) le même décompte rendait 3 223 ; l'écart de 4 est exactement les 4 tests ajoutés (`git diff a65341cb HEAD -- '*Test.java' \| grep -c '^+.*@Test'` rend 69, contre 65 avant). **Le total de la base n'est pas mesuré par moi** : 3 227 moins 69 donne 3 158, une déduction. Le total après fusion est à `yanis-qa`.

**Mesures partielles** (toutes avec `-am`, périmètre nommé) : `-pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest` : 69 verts ; `-pl bootstrap -am` sur `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (9), `TexteAccentueExposeParLePortEntrantTest` (10), `ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest` (4), `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (8), `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (28) : verts, avant le commit.

**Jar** : aucun jar n'est lancé, aucun port n'est utilisé.

## 7. Jobs sans `mvn`, lancés sur le sommet

Lancés sur l'arbre de `ec2d572a` plus ce rapport (tracé par `git ls-files`), avant le commit de ce rapport :

| Job | Sortie lue |
|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` (21 cas) : exit 0 ; `git log --no-merges --format=%s a65341cb..HEAD \| bash .github/scripts/verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."* exit 0 ; `verifier-garde-fou-accent.sh a65341cb..HEAD` : *"0 commit(s) le touchent dans la plage, tous [THEO]."* exit 0 |
| `chemins` | `verifier-chemins-test.sh` exit 0 ; `verifier-chemins.sh` sur `git ls-files` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* exit 0 |
| `outils` | 13 juges purs exécutés, aucun rouge |

**Cela ne vaut pas la CI** : celle de GitHub n'a pas tourné sur ces commits (aucun push). `prefixes` est à relancer sur le commit de ce rapport et sur le commit de fusion.

## 8. Ce que chacun doit relire

| Qui | Quoi |
|---|---|
| `theo-archi` | Section 2, points 1 à 6 : la déclaration `objet`, les deux conditions retirées, le garde neuf et son calcul sur le texte, les deux mots ajoutés à `PARTAGES`. Question ouverte : la variante *pour tout le monde* (réserve 1) demande-t-elle un ensemble de formules plus large que les deux fixées dans le test ? Lignée de la branche (`git merge-base --is-ancestor a65341cb HEAD` sort 0, seul fait que je pose). |
| `yanis-qa` | Total de tests après fusion (3 227 sur la branche, base non mesurée par moi) ; les mutations A et B de la section 2 sont rejouables telles qu'écrites ; jeu fautif : sondes 41, 42, 43 et la déclaration d'objet ajoutée aux autres. |
| `nadia-prof` | Les 9 scènes neuves, en particulier **une seule lecture défendable** sur les cinq scènes de portée tous à objet personnel (*Omar accroche sa carte au tableau pour la leçon*, *Tom essuie les tables de la salle avec son chiffon* et *Lina colle son affiche des horaires au mur* sont celles où je lis le plus fragilement : un enfant peut y voir celui qui agit), et sur les quatre scènes de portée un à objet partagé (*Hugo cherche un mot dans le dictionnaire du coin lecture* : le dictionnaire du coin sert-il *quelques élèves* ?). Les 24 indices, dont les 9 neufs : *Qui peut voir cette plante ?*, *Qui lit cette affiche ?*, *Qui doit lire cette annonce ?*, *Qui s'assoit à ces tables ?*, *Qui regarde cette carte ?*, *Qui va lire ce livre ?*, *Qui a soif ?*, *Qui va découper avec ces ciseaux ?*, *Qui cherche ce mot ?*, *Qui marche dans la salle ?*. `NRC-CE1-1` contre `ce1-emc-biens-communs` : les quatre scènes à objet partagé parlent de la bibliothèque et de la cour, deux lieux que ce paquet voisin emploie pour une autre tâche (savoir à qui est l'objet). Réserve 1 : la cour a quitté les scènes de portée tous. |
| `ines-design` | Relevé à 744 x 551 de ce paquet, seuils de `LP4` non mesurés ici (réserve 3). |
| `camille-po` | Rien à trancher au branchement. La cellule `EMC-CE1-04` de la matrice n'est pas touchée. |

## 9. Ce que je n'ai pas fait

- Je n'ai pas joué le jar, ni lancé de port.
- Je n'ai pas mesuré le pli.
- Je n'ai pas lu le paquet avec le regard de `nadia-prof` : `INT-CE1-1` (une seule lecture défendable), `EMC-5`, `NRC-CE1-1` sur le fond sont à elle.
- Je n'ai pas lancé `verifier-lignee.sh`, ni fusionné, ni poussé, ni rebasé, ni écrit dans `planning.md`, ni touché la matrice, ni écrit dans la fiche de conformité (elle est de `nadia-prof`, sur une branche non fusionnée).
- Le message du commit `ec2d572a` ne porte pas la ligne de co-signature ; ce rapport et ses commits suivants la portent. Je n'ai pas réécrit le commit pour la lui ajouter (pas de réécriture d'historique).
- Je n'ai pas relancé `mvn package` sans `-DskipTests` : ce serait un second passage de la suite complète hors jeton.

## 10. Isolation

Répertoire de scratchpad utilisé : `s57-p4-sofia-lp6-reprise` (scripts de génération du paquet et du jeu fautif, journaux de mutations et de `verify`, ni l'un ni l'autre commités). Seule exception : le répertoire de jeton RF1 `s57-p4-jeton-verify`, que j'ai créé par `mkdir` à 00:13:32 (fichier `sofia-dev-lp6-reprise.txt`) et supprimé à 00:25:58 ; la racine du scratchpad ne le liste plus.
