# [Sprint s55] Lecture de testabilite des criteres - yanis-qa - 2026-10-08

Base : `2d0d10e7` (sommet de `round-two`, fusion de l'amendement du planning), branche `s55-yanis-lecture`, arbre propre au depart
(`git status --short` vide). Sources lues : `planning.md` et son amendement du 2026-10-08, `DEC-0115` (statut `Proposee`) et son amendement
A.1 a A.8, `avis-faisabilite.md`, `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` (sections 3, 4, 5) et `-instance-b-emc-cm1.md`
(sections 1 a 4), la section "Sprint 55" du backlog et les sous-sections `S54-ART-RES-R1` a `R3`, `S54-EXC-CATEGORIE`, `S54-PONCT-GN-INDICES`,
`S54-ASCII-APOSTROPHES-TESTS`. **Aucun fichier de code, de contenu ni d'outil du depot n'est edite.** Les simulations (sections 2.2 a 2.12)
ont tourne dans un clone jetable hors du depot, supprime a la fin ; les textes appliques sont ceux de `nadia-prof`, inseres par script, pas
par un developpeur. Ce fichier ne prononce aucun Done.

## 1. La base du sprint

**Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, racine du worktree, reacteur complet, sans `-pl`.
**Resultat** : `BUILD SUCCESS`, code de sortie 0, 5 min 38 s. L'enveloppe a rendu "Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10" ;
la version que la machine virtuelle des tests rapporte est lue dans les rapports XML : propriete `java.version` = **21.0.10** dans les cinq
modules (`ADR-0071`).

| Module | Surefire | Failsafe | Echecs, erreurs, ignores |
|---|---|---|---|
| `recreo-domain` | 120 | - | 0 |
| `recreo-application` | 17 | - | 0 |
| `recreo-adapter-web` | 401 | - | 0 |
| `recreo-adapter-content` | 1360 | - | 0 |
| `recreo-bootstrap` | 312 | 6 | 0 |
| **Reacteur** | **2210** | **6** | **0** |

**Total : 2216 tests.** Methode : somme des attributs `tests`, `failures`, `errors`, `skipped` de l'element racine de chaque `TEST-*.xml` sous
`target/surefire-reports` et `target/failsafe-reports` de chaque module (11, 2, 48, 167, 50 fichiers Surefire, 4 fichiers Failsafe), recoupee
avec les lignes `Tests run:` de fin de module de la sortie Maven. **Delta par rapport a la cloture de s54 (2216, `RAPPORT.md` de s54) : 0.**
Aucun commit de code n'a ete fusionne depuis. Je ne cite pas ce nombre comme total de s55 : `sofia-sm` l'etablit sur l'arbre fusionne.
Le constat bloquant de la section 0 de l'avis de `theo-archi` (`AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` rouge) est
solde sur cette base : la classe passe dans ce `verify`.

### Les quatre jobs de `.github/workflows/ci.yml`

| Job | Lance ? | Ce qui a tourne | Sortie |
|---|---|---|---|
| `prefixes` | etapes 1 et 2 : oui. Etape 3 : apres le commit de ce fichier, avec le sujet en entree explicite (section 6) | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` | voir section 6 |
| `chemins` | oui, les deux etapes (section 6) | `verifier-chemins-test.sh` ; `verifier-chemins.sh` avec la liste des fichiers suivis plus ce fichier | voir section 6 |
| `outils` | oui, avec un ecart | boucle de l'etape : `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **10 juges** (5 + 2 + 3), 10 sorties 0, aucun motif vide. Ecart : le job prend `python3` s'il existe, ici `python3` est un faux binaire, j'ai passe `python` |
| `build` | etape `mvn -B verify` : oui, via l'equivalent portable `clean verify`. Etape `mvn -B -pl bootstrap -am package` : **non lancee** | voir ci-dessus | `BUILD SUCCESS` ; l'etape de package n'est pas deduite |

Je n'ai pas interroge la CI distante : aucun verdict de CI n'est lu ici (`U3` du planning reste la reponse relayee "verte", non lue).
**Porte technique a `2d0d10e7`, telle que mesuree ici : verte sur `outils`, `chemins`, `prefixes` (etapes 1 et 2) et sur l'etape `verify` de `build`.**

## 2. Lecture de testabilite, poste par poste

Un critere est **testable** s'il se juge par une commande et un resultat attendu ; **tenable** si un developpeur qui applique le texte de
`nadia-prof` le rend vert. "Rouge aujourd'hui" = la commande lancee par moi sur `2d0d10e7` rend le contraire du resultat attendu.
"Apres" = la meme commande sur un clone ou les textes de `nadia-prof` des sections 2.1 a 2.3 de l'arbitrage B, 4 et 5 de l'arbitrage A et le
bloc d'`EXC-R15` sont appliques aux fichiers `contenu/` seuls (pas de catalogue, pas de test de paquet). **Un `grep` qui rend 0 apres prouve
qu'un texte a disparu, pas qu'il a ete corrige** ; je le dis une fois ici pour toutes les lignes "1 vers 0".

Toutes les commandes `grep` ci-dessous sont lancees en locale UTF-8. **Un `.` qui tient lieu d'un caractere accentue ne matche rien en
locale `C`** : `grep -c 'il ___ un g.teau'` et `grep -c 'Pense . deux amis qui n.aiment pas les m.mes livres'` rendent 1 en UTF-8 et 0 en `C` sur
le texte correct (mesure sur le clone). Sur un poste en locale `C`, ces commandes sont rouges apres correction (voir Q-Y7).

### 2.1 Criteres communs C1 a C9

| Critere | Testable | Commande exacte | Aujourd'hui |
|---|---|---|---|
| C1 decision d'abord | existence seule | `git show round-two:squad/decisions/produit/DEC-0115-ouverture-de-s55-quatre-branchements-deux-corrections-mecanique-art-cp-10-financee-ordre-repris-sous-motif-de-forme-1.md` code 0 | vert. **Prouve l'existence, pas l'acceptation** : `DEC-0115` est `Proposee` (section 0.5, A.1) |
| C2 diff borne | oui, par liste blanche | voir ci-dessous | vide aujourd'hui (HEAD egale `round-two`), **vide par construction, pas rouge** |
| C3 une entree par paquet | oui | `grep -c "paquet: <nom>$" adapters/adapter-content/src/main/resources/catalogue.yaml` | **rouge, mesure** : 0, 0, 0, 0 pour `ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `cm1-emc-respect-differences`, `cm1-emc-principes-republique`. Temoin positif : `ce1-futur` rend 1 |
| C4 suite verte | oui | mesure partielle `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` ; porte `bash outils/mvn-jdk21.sh -B clean verify` | vert (section 1). La mesure partielle ne couvre ni Failsafe ni les trois modules amont en test aval |
| C5 aucun garde-fou assoupli | en partie | `git diff round-two -U0 -- '*Test.java'` puis les lignes `-` | liste vide aujourd'hui. **Le texte est faux tel qu'ecrit : voir Q-Y2** |
| C6 jobs nommes | sur la forme | `grep -c` des quatre noms dans le rapport de la livraison | prouve que le nom est ecrit, pas que le job a tourne |
| C7 rapports | existence, puis lecture | `ls squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/rapport-marc-dev.md squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/rapport-lea-dev.md` | **rouge, mesure** : code 2, aucun des deux n'existe. "relu" ou "non relu" prouve qu'une ligne est ecrite, pas que la relecture a eu lieu. L'amendement (section A) remplace `EMC-9` par `EMC-4`, `EMC-8`, la regle de s53 section 4.1 et `BIA-5` pour les deux EMC : la liste du texte de C7 est perimee |
| C8 motif de refus garde ou "non garde" | oui pour P3, non pour le reste | voir 2.7 | mutation P3 **verte aujourd'hui** (2.7) : le test neuf est du |
| C9 arret au verdict | oui pour le statut | `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` doit rendre vide avant le verdict | vide. La mention exigee d'avance (EMC CM1) n'a pas de comparaison de chaine possible : voir Q-Y12 |

**C2, liste blanche mesuree.** Commande :
`git diff --name-only round-two...HEAD` suivie d'un `grep -vE` de `(^|/)(catalogue\.yaml|contenu/[a-z0-9-]+\.yaml|Paquet[A-Za-z0-9]+Test\.java|TexteAccentueExposeParLePortEntrantTest\.java)$|/Paquet[A-Za-z0-9]+Test/`.
Temoin : sur les trois commits de branchement de s54 (`95aa8f39`, `557ebd8a`, `ecf34b10`), la liste rend 0 fichier hors liste. **Limite** : `contenu/[a-z0-9-]+\.yaml`
laisse passer le YAML de n'importe quel paquet (`cm1-grands-nombres.yaml` passe, mesure). Pour borner, nommer par story le YAML autorise :
`ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `cm1-emc-respect-differences`, `cm1-emc-principes-republique` (Q-Y11). Les rapports `.md` du dossier de
sprint sortent aussi de la liste : a dire.

### 2.2 `S54-ART-RES-R1` a `R3` (instance A, `lea-dev`)

| Critere | Commande lancee | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| R1 consigne generique | `grep -c 'va avec la statue, le tableau ou la photographie'` sur `catalogue.yaml` | **1, rouge** | non simule (catalogue) | testable, tenable |
| R1 ni `reconna` ni `rapproch` dans titre et consigne | `awk '/^  - id: bandit-manchot-familles-d-oeuvres-cp/{f=1;next} f&&/^  - id:/{f=0} f' catalogue.yaml` puis `grep -ciE 'reconna\|rapproch'` | 0, **vert, borne** | - | le planning ne donne pas de commande ; celle-ci est la mienne |
| R3 `un photographe` | `grep -c 'un photographe'` sur `cp-arts-oeuvres-familles.yaml` | **3, rouge** | 0 | testable, tenable |
| R3 `une photographe` | `grep -c 'une photographe'` | **0, rouge** | 3 | idem |
| R3 un metier au feminin | `grep -cE "une peintre\|une photographe\|une sculptrice"` | **0, rouge** | 3 | idem |
| R2 indice de l'item 11 | `grep -c 'indice: "Cette .uvre est faite avec de la peinture."'` | **0, rouge** | 1 | testable en locale UTF-8 seulement (le `.` tient lieu de la ligature) |
| R2 sans `peintre` ni `pinceau` | `grep -A3 'bonneReponse: "un tableau"'` puis `grep -c 'peintre\|pinceau'` | 0, vert | 0 | borne |
| un seul commit | `git log --format=%h round-two..HEAD -- <catalogue> <paquet> <inventaire>` doit rendre un seul hash | n/a | n/a | testable a la fusion |

Garde-fous existants qui s'appliquent : `PaquetCpArtsOeuvresFamillesTest` (vert apres, sur le YAML corrige : aucune erreur dans la simulation) et
l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (rouge dans les deux sens si la consigne change sans l'inventaire, mesure de `theo-archi`,
non rejouee).
**Ce que la machine ne voit pas** : que l'indice departage reellement un tableau d'une photographie, que `une photographe` se lise naturellement
et que la consigne generique soit juste a l'ecran. `nadia-prof` rejoue (instance A, 0,25).

### 2.3 `S54-EXC-CATEGORIE` (`EXC-R15`, instance B, `marc-dev`)

| Critere | Commande lancee | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| bonne reponse "moquerie" 3 vers 1 | `grep -c 'bonneReponse: "une moquerie qui se répète"'` | **3, rouge** | 1 | testable, tenable |
| "mise a l'ecart" 0 vers 3 | `grep -c "une mise à l'écart qui se répète"` | **0, rouge** | 3 | testable, tenable |
| consigne du catalogue 1 vers 0 | `grep -c "dis si c'est une moquerie qui se répète"` sur `catalogue.yaml` | **1, rouge** | non simule | testable, tenable |
| `PaquetCpEmcExclusionTest` reste vert | `-Dtest=PaquetCpEmcExclusionTest` | vert | **rouge, mesure** : `fab1aLaTacheDeClassementSertSesDeuxCategoriesSansReponseInvariante`, 1 echec sur 9 | **faux**, comme `theo-archi` et `DEC-0115` A.6 le disent ; reproduit : 1360 tests dans `adapter-content`, 1 echec, celui-la |
| test mord la categorie neuve | `grep -c "mise . l.cart" PaquetCpEmcExclusionTest.java` doit rendre au moins 1 | 0 | **insatisfiable** | **faux** : voir Q-Y1 |

**Le critere ajoute par l'amendement ne peut jamais etre vert.** Dans `l'écart` il y a deux caracteres entre `l` et `cart` (l'apostrophe, puis `é`) ;
le motif `l.cart` n'en admet qu'un. Temoin : `printf "mise à l'écart\n" | grep -c "mise . l.cart"` rend **0** en UTF-8 comme en `C`. Le test existant
porte les accents en clair (`"une moquerie qui se répète"`, ligne 82), donc `grep -c "mise à l'écart"` est la commande qui peut rendre 1.
**Ce qu'il faut pour `EXC-R15` en l'etat** (le planning le demande) : (1) la reponse de `nadia-prof` a `QC4` (`git grep -c QC4 -- squad/conformite` rend 0 fichier) ;
(2) le critere reecrit par `camille-po` ; (3) une assertion qui ferme le faux vert de `theo-archi` : les six items du classement restent dans la tache de
classement apres le changement de table (`CLASSEMENTS_VALIDES`), sans quoi les items 1 et 3 basculent en silence dans la tache B ; je la mordrai (retirer la
categorie neuve de la table, voir le rouge, la remettre). **Ce que la machine ne voit pas** : que `une mise à l'écart qui se répète` soit le bon nom, et
que le titre servi (*"Une moquerie qui se répète"*) ne le contredise pas a l'ecran : `nadia-prof` le regarde au rejeu (arbitrage B, section 5).

### 2.4 `S55-BR-FRA-CE1-L1` (`ce1-accord-sujet-verbe`, `K1` a `K3`, `marc-dev`)

| Critere | Commande lancee (sur `ce1-accord-sujet-verbe.yaml` sauf mention) | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| K1 substitution | `grep -c 'indice: "Remplace le sujet par'` | **0** | 12 | testable, tenable |
| K1 relecture | `grep -c 'indice: "Lis le sujet avec chaque verbe'` | **0** | 5 | idem |
| K1 `compte-les` | `grep -c 'compte-les'` | **6** | 1 (item 8, qui garde son indice) | idem |
| K1 un indice par item | `grep -c '^ *indice:'` | 24 | 24 | **vert, borne** |
| K2 items 6 et 18 | `grep -c 'il ___ au march'` ; `grep -c 'il ___ un g.teau'` | **0 ; 0** | 1 ; 1 | testable en UTF-8 seulement |
| en-tete | `grep -c "satisfait par la forme d.une autre personne"` | **0** | 1 | testable, tenable |
| K3 consigne | `grep -c 'Lis bien, puis choisis la bonne forme du verbe'` sur `catalogue.yaml` | **2** | 3 (non simule) | testable ; le titre est libre : voir ci-dessous |
| C3, C7, test du paquet | voir 2.1 | rouge | - | le test du paquet n'existe pas : il est du (`T1`) |

**Mesures mecaniques sur le fichier apres `K1` a `K3`** (script de lecture seule, regle de comptage de `PaquetCe1ConjugaisonPresentTest.mots` : un jeton
compte s'il porte une lettre ou un chiffre) : indices distincts 4 avant, 6 apres ; indice le plus long **11 mots** ; **aucun indice de plus de 12 mots** ;
balayage `oreille|entends|écoute|sonne|prononce|dans ta tête` sur les lignes `indice` : 0. **Trois regles de comptage rendent trois resultats** sur les memes
indices : 11 (jeton avec lettre, coupe sur espace ordinaire), 11 (jeton avec lettre, coupe sur tout blanc), **19** (`split()` sur tout blanc, U+00A0 et guillemets compris,
20 indices au-dessus de 12). Le texte de `K1` porte U+00A0 dans les guillemets et devant `:` et `?` : la regle est a ecrire dans le test du paquet (Q-Y5).
**Une clause de `TRV-CE1-5` est rouge sur le texte de `nadia-prof` lui-meme.** Avec la regle "la bonne reponse est un mot entier de l'indice", l'item 6
(bonne reponse `va`) recoit l'indice *"Lis le sujet avec chaque verbe proposé : lequel va avec lui ?"* qui contient `va` : 1 item sur 24 (avant `K1` : 0 ;
sous-chaine : l'item 7, bonne reponse `a`, est deja dans ce cas sans que personne le compte). Le developpeur applique mot pour mot et ne peut pas le lever ;
c'est a `nadia-prof` de dire si "jamais la reponse" vise l'idiome (Q-Y5).
**Piege du titre** : celui que `nadia-prof` propose, *"Un ou plusieurs ?"*, et celui de `K5`, *"Quelle sorte de mot ?"*, portent un `?`. Mesure : une entree de catalogue
ajoutee avec ce titre et une espace ordinaire avant `?` rend `leCliquetDeLaDetteDeTyp1EstExact` **rouge** ("catalogue.yaml porte 24 occurrences, la table en declare 23") ;
avec U+00A0, **vert** (5 tests). Le critere "titre libre" doit dire U+00A0 (Q-Y6).
**Ce que la machine ne voit pas** : que chaque indice convienne a son item, qu'il ne se satisfasse d'aucun distracteur de son item (`K1` : la propriete
qui a motive le refus de `FRA-CE1-17`), l'oreille hors de la liste de mots, et les `W1` a `W3`. `nadia-prof` seule, en jouant.

### 2.5 `S55-BR-FRA-CE1-L2` (`ce1-classes-de-mots`, `K4`, `K5`, `marc-dev`)

| Critere | Commande lancee | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| K4 `Fatou` ; `Lina` | `grep -c 'Fatou'` ; `grep -c 'Lina'` | **0 ; 0** | 1 ; 1 | testable |
| K4 `Tom.*range` ; `Karim aime` | `grep -c` de chacun | **1 ; 1** | 0 ; 0 | testable |
| K5 consigne | `grep -c 'dis quelle sorte de mot est entre guillemets'` sur `catalogue.yaml` | **0** | 1 (non simule) | testable |
| cliquet `TYP-1` sans toucher sa table | `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` | vert | vert tant que le titre porte U+00A0 (2.4) | tenable |

**Le critere ne mesure pas la propriete.** `nadia-prof` demande des prenoms feminins et masculins "a un pres", comptes en occurrences. Mesure par script sur les
enonces : **5 feminins et 8 masculins aujourd'hui, 7 et 6 apres `K4`**. `grep -c 'Fatou'` rend 1 aussi si `Fatou` est ecrit dans un commentaire ou sur un autre item ;
la commande qui juge est le comptage (Q-Y13). Le test du paquet porte `CLM-CE1-1` (5, 4, 5, 5, 5 par classe) et n'est pas ecrit.
**Ce que la machine ne voit pas** : `W3` (les quatre indices ne departagent rien, reserve annoncee de `nadia-prof`), que `Fatou` et `Lina` soient des prenoms
d'enfants de CE1 naturels dans ces phrases.

### 2.6 `S55-BR-EMC-CM1-03` (`cm1-emc-respect-differences`, `lea-dev`)

| Critere | Commande lancee | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| item 4 : ancienne bonne reponse | `grep -c 'bonneReponse: "Aya, parce qu.elle utilise un fauteuil roulant et que'` | **1** | 0 | testable |
| item 4 : nouvelle | `grep -c 'bonneReponse: "Aya, parce qu.elle utilise un fauteuil roulant"'` ; `grep -c 'jeu de cartes'` | **0 ; 0** | 1 ; 1 | testable |
| item 7 | `grep -c 'Pense . deux amis qui n.aiment pas les m.mes livres'` | **0** | 1 | testable en UTF-8 seulement |
| item 8 | `grep -c 'disparaissent avec le temps'` ; `grep -c "puni si un adulte le voit"` | **1 ; 0** | 0 ; 1 | testable |
| source `HIS-3` 1948 | `grep -c 1948` | **0** | au moins 1 | **faible** : n'importe quel `1948` le satisfait (un nombre, un commentaire) ; l'en-tete n'est pas simule |
| 11 items | `grep -c '^ *- enonce:'` | 11 | 11 | borne ; le motif de `LeRang...` ne change pas |
| paragraphe `EMC-5` de l'en-tete decrit l'item 4 neuf | lecture | - | - | non automatisable : "relu par le relecteur" |

Simulation : les trois items de `nadia-prof` appliques, `adapter-content` reste **vert** sur ce fichier (aucun garde-fou generique ne rougit).
**Ce que la machine ne voit pas** : `EMC-4` (aucune capacite attribuee a une categorie : l'item 4 neuf en est le cas d'ecole), `EMC-5`, `EMC-8`, la lourdeur
de *"le respect d'autrui n'est-il pas respecte"* repete cinq fois, le doublon avec l'item 10 de `principes`. Le developpeur relit `EMC-4`, `EMC-8`, s53 4.1,
ecrit "relu" ou "non relu" : c'est une ligne, pas une preuve.

### 2.7 `S55-BR-EMC-CM1-06` (`cm1-emc-principes-republique`, `lea-dev`)

| Critere | Commande lancee | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| item 6 : ancien distracteur | `grep -c "qu.un adulte la refuse"` | **1** | 0 | testable |
| item 6 : nouveau | `grep -c "pouvoir faire tout ce que l.on veut"` | **0** | 1 | testable |
| item 6 : indice inchange | `grep -c 'indice: "Teste chaque phrase avec un exemple pris dans la classe."'` | 1 | 1 | **vert, borne** (le but est de ne rien changer) |
| item 8 | `grep -c 'bonneReponse: "se soutenir et s.entraider entre citoyens"'` ; `grep -c "s.entraider les uns les autres"` | **1 ; 0** | 0 ; 1 | testable |
| source `HIS-3` 2018 | `grep -c 2018-717` | **0** | au moins 1 | testable, faible comme `1948` |

**P3 : le test neuf est du, et la base ne le garde pas.** Mutation lancee sur le clone : l'item 4 (*"Quel principe de la République garantit que l'école
publique ne favorise aucune religion ?"*) recoit `"la liberté"` en distracteur a la place de `"la démocratie"`. Resultat : `adapter-content`, **1360 tests, 0 echec**.
La mutation survit, comme *"le printemps"* a s54. Le test que `theo-archi` place dans le poste (T2) doit la rougir ; **je rejouerai cette mutation sur la livraison**
(retirer le test ou remettre `"la liberté"`, voir le rouge, remettre). **P4** : "non garde : P4", les six indices (items 6 a 11, d'apres s53 section 4) lus et nommes
dans `rapport-lea-dev.md`. Aucun automate : un indice qui redit la bonne reponse se juge par lecture.
**Ce que la machine ne voit pas** : les cinq points de la section 3.3 de l'arbitrage B (`EMC-5` sur les items 1 a 5, les trois items de laicite ensemble, l'exactitude
des sources, le vocabulaire a l'ecran, la fuite d'un indice vers un autre item), `BIA-5`. `nadia-prof` seule, en jouant ; c'est la ligne la plus dangereuse de la matiere.

### 2.8 `S55-ART-CP-10-MECANIQUE` (T2, T3 ; `lea-dev`)

Aucun de ces criteres n'est vert sur ce que la story construit, et aucun ne l'est pour la mauvaise raison, sauf le 6 (voir plus bas).

| Critere | Commande lancee | Aujourd'hui | Verdict |
|---|---|---|---|
| 1 image, pas texte | `grep -c "oeuvres/' + ${proposition}" .../templates/fragments/manche.html` ; `grep -c 'th:text="${proposition}"'` | **0 ; 1, rouge** | **le `grep` lit le gabarit, pas la page** : un commentaire qui contient la chaine le satisfait. Le juge est le garde-fou 4 (page rendue, selecteur structurel, une balise `img` par proposition) ; le `grep` n'est qu'un filet |
| 2 aucun identifiant | `reponseAttendueVisible` aux lignes 686, 714 et 746 de `PartieController` ; `th:text="${bonneReponse}"` ligne 98 de `retour.html` | confirme, rouge | testable : selecteur sur les quatre ecrans (aucun `.reponse-attendue`, aucun `oeuvre-` dans un noeud de texte, un `alt`, un `aria-label` ou un `title`, attribut `title` absent) |
| 3 jeux en texte inchanges | `grep -c '@Test' PartieControllerTest.java` | rend 42 | **faux** : 41 annotations `@Test` (`grep -cE '^\s*@Test\s*$'`), la 42e ligne est `@TestConfiguration` (ligne 913). Le rapport Surefire donne **41 tests** : Q-Y4 |
| 4 garde-fous 1 a 4 mordent | mutations a rejouer a la livraison | n/a | testable. Les quatre mutations de la colonne "Changement" d'`ADR-0104` decision 7 sont a moi, pas au dev. Population reelle exacte 0 : la forme existe (`LIllustrationDeContenuResoutEtAucuneNeDortTest`, lignes 586 a 592) |
| 5 cliquet 3 vers 4 | `sed -n '709,730p' RessourcesTiercesTest.java` | `hasSize(3)` une fois (ligne 725) | testable. **Contredit le texte de C5** : Q-Y2 |
| 6 mots et mise en page avant le rendu | `ls squad/conformite \| grep -ci s55` ; `ls squad/design \| grep -ci s55` | **2 ; 0** | la premiere commande est **verte pour la mauvaise raison** : elle compte les deux arbitrages de `nadia-prof`, pas le fichier qui porte le nom accessible et la phrase de revelation (ces textes y sont : `Choisir cette œuvre`, `C'est cette œuvre qui va avec. Appuie dessus.`). Q-Y9. La seconde (`G-INES`) est rouge et bien posee |
| 7 perimetre de code | `git diff --name-only round-two` puis `grep -E '^(domain\|application)/\|^adapters/adapter-content/src/main/java\|^bootstrap/src/main\|(^\|/)pom\.xml$\|^\.github/'` | 0 ligne, **vert, borne** ; `ArchRule` 39 et `@ArchTest` 36 (somme de `grep -h -c` sur `git ls-files '*.java'`) | testable |
| 8 ni catalogue, ni matrice, ni paquet | `git diff round-two --stat` | vide, **vert, borne** | testable avec le chemin de QC2 |
| 9 porte | `clean verify` | vert (section 1) | tenable |

**Ce que la machine ne voit pas** (la story le dit deja, je le confirme) : `M1` (meme taille rendue, sans defilement : une propriete de mise en page, pas du serveur), `M2`,
`M4` (marque de revelation), `X2`, `X3`, `X5` en son second cas (recadrage ou miroir : la machine voit l'identite d'identifiant, pas d'image), `X6` (aucune difference de
presentation avant le geste), la visibilite de la propriete a 176 px CSS (point 6 de la forme). **Non specifie** : le `alt` des images de proposition. `X1` interdit
un identifiant, un titre ou un nom ; `M5` fixe le nom accessible du bouton ; rien ne dit si l'`alt` est vide ou porte un texte (Q-Y14).

### 2.9 `S54-PONCT-GN-INDICES` (`lea-dev`, si retenue)

| Critere | Commande lancee | Aujourd'hui | Verdict |
|---|---|---|---|
| 12 vers 0 | `grep -cP '^ *indice: ".* [:;!?]'` sur `cm1-grands-nombres.yaml` | **12, rouge** | testable, tenable |
| 0 vers 12 | meme motif avec `\x{00A0}` | **0, rouge** | testable. Temoin positif : le meme motif sur `enonce` rend 14 |
| seule l'espace change | "git diff" | n/a | **pas de commande** : voir ci-dessous |
| cliquet `TYP-1` "de 12 a 0" | `leCliquetDeLaDetteDeTyp1EstExact` | vert | **faux tel qu'ecrit** |

**Simulation du cliquet** (le YAML corrige par script, 12 lignes, trois etats de la table, une classe de test, `-am`) :

| Table | Resultat |
|---|---|
| inchangee (`cm1-grands-nombres` 12, total 502) | **rouge** : "la table declare 12 occurrences pour cm1-grands-nombres, qui n'en porte plus aucune. Retirer cette ligne" |
| entree mise a 0, total 490 | **rouge** : meme message, "declare 0" |
| entree **retiree**, total 502 | **rouge** : le total declare ne suit plus la table (mesure 490) |
| entree **retiree**, total 490 | **vert**, 5 tests |

Le critere dit "de 12 a 0" ; la seule forme verte est : retirer la ligne de `DETTE_PAR_FICHIER` **et** passer `DETTE_TOTALE` de 502 a 490 (Q-Y3). Commande pour
"seule l'espace change" : comparer `git show round-two:<yaml>` et le fichier courant apres avoir remplace U+00A0 par une espace ordinaire dans les deux ; la sortie doit
etre vide. **Ce que la machine ne voit pas** : rien de pedagogique, la story est typographique ; la relecture est celle du `relecteur-pedagogique`.

### 2.10 `S54-ASCII-APOSTROPHES-TESTS` (`marc-dev`, si engagee)

- `grep -cP '[\x{2018}\x{2019}]'` rend **1 sur chacun** des deux tests (lignes 57 et 65, mesure) : rouge, testable. Apres la forme de `theo-archi`
  (`\\u2019` dans l'expression reguliere, simulation sur le clone), `PaquetCm1EmcAtteinteAutruiTest` et `PaquetCm1SciencesActiviteHumaineTest` : **22 tests, 0 echec**.
- **La clause "reconnaissent toujours l'apostrophe typographique" n'est gardee par rien.** Mutation lancee : l'expression ne reconnait plus que l'apostrophe droite
  (`\\bt[']`), meme classe de tests : **22 tests, 0 echec**. Le critere "les deux tests restent verts" est donc vrai que l'echappement soit correct ou que la reconnaissance
  soit perdue (Q-Y8). Ce qui la garderait : un cas fabrique ou `t` est suivi de U+2019 puis de `as` (apostrophe typographique), que le garde-fou doit rejeter.

### 2.11 `S55-OUTIL-JDK-JAVA-TOOL-OPTIONS` (A7, `sofia-sm`)

- Commande du planning : `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` : **code 1** aujourd'hui, rouge, reproduit ; sans la variable, Maven se lance sur 21.0.10.
- `bash outils/mvn-jdk21-test.sh` : code 0 aujourd'hui ("Tous les cas passent"), donc **le cas neuf sera rouge avant la correction et vert apres**, ce qui est le motif d'un cas de test.
  Je ferai la mutation a la livraison : retirer le `grep -m1 'version "'`, voir le cas neuf rouge, le remettre.
- **Limite** : le test fabrique un JDK (`fabriquer_jdk`) ; il prouve la logique de lecture de la ligne, pas la sortie d'un vrai `java -version`. La commande reelle ci-dessus, elle,
  depend du poste et n'est lancee par aucun job de la CI.

### 2.12 `S55-OUTIL-TOKENS-SANS-SPRINT` (A8, `sofia-sm`)

- **Mesure sur le clone, `python outils/rapport-tokens.py` sans `--sprint`** : avec le dossier des transcriptions de ce poste (`--projects-dir` explicite), code **0**,
  **7 chemins modifies ou crees** (le journal, cinq `RAPPORT-tokens.md` suivis, un `RAPPORT-tokens.md` neuf) : rouge, le defaut est reproduit. Sans dossier utilisable
  (clone dont le chemin n'a pas de transcriptions, ou dossier vide), l'outil sort deja avec le code **1** et n'ecrit rien.
- Le critere "sort 2 et `git status --short` reste vide" **depend de l'ordre des controles** : si le refus de l'absence de `--sprint` vient apres le controle du dossier des
  transcriptions, un poste sans transcriptions rend 1, pas 2, et le critere est rouge pour un autre motif (Q-Y10). Le test doit donc se faire avec `--projects-dir` pointe
  sur un dossier vide **et** sur un dossier plein. `test_6` de `outils/rapport-tokens-journal-test.py` est le juge ; le job `outils` le lance (3 juges Python verts).

## 3. A6 : le diff de `s54-nadia-c`

**Faisable apres fusion, et verte.** La branche a ete fusionnee deux fois (`3334d5d2`, second parent `d6660a70`) ; sa copie locale a ete reecrite depuis (`46e3651c`,
hashes differents pour les memes sujets) : **le diff qui compte est celui du second parent de la fusion**, pas celui du nom de branche. Commandes :
`git log --format='%h %s' 3334d5d2^1..3334d5d2^2` (3 commits) et `git diff --name-only 3334d5d2^1...d6660a70`.

| Commit | Fichiers |
|---|---|
| `c5c2d50f` (arbitrage prealable instance C) | 1 : `squad/sprints/s54-.../arbitrage-nadia-c.md` |
| `5cdadef3` ("round-two dans s54-nadia-c") | fusion ; contre son second parent (`round-two`) elle n'ajoute que `arbitrage-nadia-c.md` : **rien d'autre n'est entre par cette fusion** |
| `d6660a70` (fiche, Done pedagogique sous reserve) | 1 : `squad/conformite/FICHE-bandit-manchot-familles-d-oeuvres-cp.md` |

Le diff complet de la branche contre sa base tient en **2 fichiers**, un par commit propre ; aucun fichier de l'instance A ni de l'instance B de s54 n'y figure
(`grep -ciE "instance-a\|instance-b\|FICHE-.*(futur\|passe-compose\|sciences\|atteinte)"` sur la liste : 0). Les contenus des deux fichiers sont identiques entre la
fusion et la copie locale reecrite (`git diff --stat` vide sur les deux). **Ce que git ne dit pas** : le nom du worktree et du scratchpad de l'instance C, jamais ecrits
(obstacle 8 de s54) ; l'isolation d'ecriture est constatee par l'absence de fichier partage, pas par les noms.

## 4. Questions a `camille-po`

Je ne corrige aucun critere ; chaque ligne est une question dont la reponse reecrit un critere ou le laisse tel quel.

| Code | Question |
|---|---|
| Q-Y1 | **`EXC-R15`** : le critere ajoute par `DEC-0115` A.6 (`grep -c "mise . l.cart" PaquetCpEmcExclusionTest.java` au moins 1) est insatisfiable (deux caracteres entre `l` et `cart`). Le remplacez-vous par `grep -c "mise à l'écart"` ? Et ecrivez-vous la propriete "les six items du classement restent dans la tache de classement" (faux vert de `theo-archi`) ? Apres la reponse de `nadia-prof` a `QC4` |
| Q-Y2 | **C5** dit "un cliquet ne monte jamais". Le critere 5 de la mecanique (3 vers 4), `T4` (8 vers 11, 176 474 vers 250 915) et la population exacte 0 qui monte au premier paquet a reproductions sont des bornes exactes qui montent avec le contenu. Distinguez-vous une dette (qui ne monte jamais) d'une population exacte (qui monte dans le commit qui l'ajoute) ? |
| Q-Y3 | **`PONCT`** : "cliquet de `TYP-1` de 12 a 0" est rouge ; la forme verte est la ligne `cm1-grands-nombres` retiree de la table et `DETTE_TOTALE` 502 vers 490 (mesure, 2.9). Reecrivez-vous le critere en ces termes, avec la commande "seule l'espace change" ? |
| Q-Y4 | **Mecanique, critere 3** : `grep -c '@Test'` rend 42 parce qu'une ligne est `@TestConfiguration` ; il y a 41 tests (annotations et rapport Surefire). Le plancher est-il 41, mesure par `grep -cE '^\s*@Test\s*$'` ? |
| Q-Y5 | **`TRV-CE1-5`** (L1, L2) : (a) quelle regle de comptage des mots fait foi pour "douze mots au plus" (11, 11 ou 19 sur les memes indices) ? (b) "jamais la reponse" se lit-il comme "la bonne reponse n'est pas un mot entier de l'indice" ? Si oui, l'indice de `K1` de l'item 6 (*"... lequel va avec lui ?"*, bonne reponse `va`) la contient : a `nadia-prof` de dire si le texte reste ou si la clause vise autre chose |
| Q-Y6 | **Titres de catalogue** de `K3` et `K5` ("libres") : un titre avec ` ?` en espace ordinaire rend le cliquet de `TYP-1` rouge (mesure, 2.4). Ajoutez-vous "U+00A0 devant le `?` du titre" au critere, et `grep -cP '^ *titre: ".* [?:;!]'` sur l'entree neuve rend 0 ? |
| Q-Y7 | Les criteres qui ecrivent un `.` pour un caractere accentue (`g.teau`, `m.mes`, `.uvre`) ne valent qu'en locale UTF-8 (0 en `C`, meme sur le texte correct). Nommez-vous la locale comme prerequis, ou remplacez-vous le `.` par une sous-chaine sans accent ? |
| Q-Y8 | **`ASCII`** : "reconnaissent toujours l'apostrophe typographique" n'est garde par aucun test (mutation verte, 2.10). Ajoutez-vous un cas fabrique au critere, ou retirez-vous la clause ? Route a `theo-archi` pour la forme |
| Q-Y9 | **Mecanique, critere 6** : `ls squad/conformite \| grep -ci s55` rend deja 2 (les deux arbitrages). Nommez-vous le fichier ou la chaine (`Choisir cette œuvre`) que la commande doit trouver ? Meme question pour `QC4` (`git grep -c QC4`) et `G-NADIA-A` (`git grep -c "amendement A.1"`) : une ligne qui cite la chaine sans repondre les satisfait. Pour `G-INES`, `git log --format=%ad` a la seconde ne dit pas l'ordre de deux commits du meme jour : `git merge-base --is-ancestor` le dit |
| Q-Y10 | **A8** : "sort 2" doit-il etre rendu avant tout controle du dossier des transcriptions ? Sinon le critere depend du poste (2.12) |
| Q-Y11 | **C2** : la liste blanche doit-elle nommer, par story, le YAML autorise (4 paquets) ? Sinon `contenu/*.yaml` laisse passer n'importe quel paquet (2.1) |
| Q-Y12 | **C9, mention exigee d'avance** (EMC CM1) : "reproduite mot pour mot" ne se compare pas par `grep -F` ; la phrase est coupee sur deux lignes dans chaque source, accentuee dans la matrice et le referentiel, **sans accent** dans l'en-tete des deux paquets (mesure apres jonction des lignes). Quelle forme fait foi pour la comparaison ? |
| Q-Y13 | **`K4`** : voulez-vous le critere de la propriete (prenoms feminins 7, masculins 6, en occurrences, sur les enonces) en plus de `grep -c Fatou` ? |
| Q-Y14 | **Mecanique** : que porte l'`alt` d'une image de proposition (vide, ou un texte) ? `X1` et `M5` ne le disent pas, et le garde-fou 4 doit l'asserter |

## 5. Ce que cette lecture n'a pas fait

- **Aucune mutation de livraison** : rien n'est livre. Les mutations a faire a la livraison sont nommees : P3 (2.7), classement d'`EXC-R15` (2.3), garde-fous 1 a 4 de la mecanique,
  cliquet de `PONCT` (deja mesure sur le clone, a rejouer sur le commit reel), cas neuf d'A7, `test_6` d'A8, apostrophes (2.10).
- Les simulations couvrent les fichiers `contenu/` seuls. Non simules : les entrees de catalogue (sauf le titre, 2.4), l'inventaire de `TexteAccentueExposeParLePortEntrantTest`,
  les tests de paquet (aucun n'existe), les en-tetes (sources `HIS-3`, `EMC-5`). La simulation a lance `adapter-content` en entier (1360 tests, 1 echec attendu : `EXC-R15`) ;
  `bootstrap` n'a pas tourne apres cet echec (`-fae` le saute). Les 6 tests Failsafe et `bootstrap` sont mesures sur la base propre, section 1.
- `-am` sur toute mesure partielle de ce fichier ; chaque total cite porte son perimetre.
- Pas de relance de la suite pour juger de la stabilite : un seul passage de `clean verify`, vert, aucun test instable observe.
- CI distante : non lue. Etape `mvn -B -pl bootstrap -am package` : non lancee.
- `lessons.md` n'existe pas dans le depot (le planning le dit) ; aucune correction a y ecrire pour l'instant.
- Aucun Done n'est prononce. Le Done technique est celui de la CI, tous les jobs ; le Done pedagogique est a `nadia-prof`.

## 6. Jobs lances pour ce commit

Renseigne apres le commit, dans le compte rendu de la tache : `chemins` et `prefixes` avec une entree explicite (liste des fichiers suivis plus ce fichier ; sujet du commit),
`outils` (10 juges, section 1), `build` (`clean verify`, section 1 ; etape de package non lancee).
