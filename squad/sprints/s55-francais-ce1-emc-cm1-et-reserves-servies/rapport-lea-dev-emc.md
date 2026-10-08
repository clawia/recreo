# Rapport de lea-dev - s55, postes S55-BR-EMC-CM1-03 et S55-BR-EMC-CM1-06

Branche `s55-lea-emc`, issue de `round-two` au commit de base `fa4ddc7e`. `bash outils/verifier-lignee.sh` sort 0 au premier geste. Rien n'est pousse, rebase ni fusionne. Aucun Done n'est prononce.

## 1. Livre

| Poste | Commit | Paquet | Corrections de nadia-prof posees dans le meme commit |
|---|---|---|---|
| `S55-BR-EMC-CM1-03` | `0839f9dc` | `cm1-emc-respect-differences` (`EMC-CM1-03`) | items 4, 7, 8 (arbitrage B, 2.1 a 2.3) ; paragraphe `EMC-5` de l'en-tete ; source `HIS-3` de 1948. **Un ecart au texte, voir E1** |
| `S55-BR-EMC-CM1-06` | `4636aa28` | `cm1-emc-principes-republique` (`EMC-CM1-06`) | items 6 et 8 (arbitrage B, 3.1 et 3.2) ; source `HIS-3` de 2018 (2018-717/718 QPC) ; indice de l'item 6 inchange |

Les cinq blocs de nadia-prof sont extraits de l'arbitrage par script (espaces insecables U+00A0 comprises) puis relus au diff. Par poste :

- une entree de `catalogue.yaml`, apres `bandit-manchot-atteinte-autrui-cm1` : ids `bandit-manchot-respect-differences-cm1` et `bandit-manchot-principes-republique-cm1`, consigne existante *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10` ;
- titres, **choisis par moi** (titre libre, arbitrage B 3.4 : sans niveau, ni groupe ni religion) : *"Ce que le respect interdit"* et *"Les principes de la République"*. Proposition de redaction, a juger par nadia-prof ;
- trois chaines par entree dans l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (id, titre, competence ; la consigne y est deja) ;
- `PaquetCm1EmcRespectDifferencesTest` et `PaquetCm1EmcPrincipesRepubliqueTest`, 12 tests chacun, et un jeu fautif commite chacun sous `adapters/adapter-content/src/test/resources/contreexemple/` (`ADR-0013` obligation 3).

Ce que chaque test garde : `FER-2` (11 items, 2 distracteurs, 3 propositions distinctes), `FER-3` (taches), `FER-1` (termes ou principes en bonne reponse), `EMC-1`/`EMC-8` (aucun pronom de deuxieme personne), le libelle de competence, et les textes que nadia-prof a refuses mot pour mot. Pour `-03` s'ajoute la forme de la proposition du fauteuil roulant (rien apres *"fauteuil roulant"*, `EMC-4`). Pour `-06` s'ajoute `P3`. Chaque verificateur a son test sur le jeu fautif (5 par classe).

## 2. Mesures avant / apres

`grep` en locale UTF-8, sur `adapters/adapter-content/src/main/resources/`. "Avant" mesure sur le worktree a `fa4ddc7e`, "apres" sur `4636aa28`.

`-03`, sur `cm1-emc-respect-differences.yaml` :

| Mesure | Avant | Apres |
|---|---|---|
| `bonneReponse: "Aya, parce qu.elle utilise un fauteuil roulant et que` | 1 | 0 |
| `bonneReponse: "Aya, parce qu.elle utilise un fauteuil roulant"` | 0 | 1 |
| `jeu de cartes` | 0 | 1 |
| `Pense . deux amis qui n.aiment pas les m.mes livres` | 0 | 1 |
| `disparaissent avec le temps` | 1 | 0 |
| `puni si un adulte le voit` | 0 | 1 |
| `1948` | 0 | 1 |
| `^ *- enonce:` | 11 | 11 |
| `paquet: cm1-emc-respect-differences$` sur `catalogue.yaml` | 0 | 1 |

`-06`, sur `cm1-emc-principes-republique.yaml` :

| Mesure | Avant | Apres |
|---|---|---|
| `qu.un adulte la refuse` | 1 | 0 |
| `pouvoir faire tout ce que l.on veut` | 0 | 1 |
| `indice: "Teste chaque phrase avec un exemple pris dans la classe."` | 1 | 1 |
| `bonneReponse: "se soutenir et s.entraider entre citoyens"` | 1 | 0 |
| `s.entraider les uns les autres` | 0 | 1 |
| `2018-717` | 0 | 1 |
| `^ *- enonce:` | 11 | 11 |
| `paquet: cm1-emc-principes-republique$` sur `catalogue.yaml` | 0 | 1 |

C2 : `git diff fa4ddc7e --name-only` filtre par la liste blanche de `lecture-testabilite.md` (section 2.1) ne rend aucun fichier hors liste ; 8 fichiers, `+666 -15`. Aucune ligne `-` dans un `*Test.java` du diff (C5).

**Mesures qui se sont ecartees avant correction** (voir E3) : sur `-03`, `jeu de cartes` rendait 2 et `disparaissent avec le temps` rendait 1 apres l'application des blocs, parce que mon propre commentaire d'en-tete repetait ces deux chaines. Reecrit avant le commit.

## 3. Tests, JDK, porte

- **Version** : JDK **21.0.10**, rapportee par `outils/mvn-jdk21.sh` (*"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*), et par le jar au demarrage (*"using Java 21.0.10"*). Commande : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sans `-pl`. Une mesure partielle (`-pl adapters/adapter-content -am`, classe nommee) n'a servi qu'a la mutation de `P3`, section 5.
- **Delta de tests** : `-03` **+12** (`PaquetCm1EmcRespectDifferencesTest`), `-06` **+12** (`PaquetCm1EmcPrincipesRepubliqueTest`), soit **+24**. Aucun test ajoute dans `bootstrap` (l'inventaire s'etend, il ne compte pas un test de plus). Mesure sur `fa4ddc7e` plus mes seuls lots : ce n'est pas un total apres fusion, `sofia-sm` l'etablit.
- `adapter-content` : 1401 tests apres `-03`, 1413 apres `-06`. Les autres modules ne bougent pas dans mon lot : domain 120, application 18, adapter-web 402, bootstrap 312, bloc final 6. Je n'ai pas lance le reacteur sur `fa4ddc7e` seul : "1389 avant" est une deduction (1401 moins 12), pas une mesure.
- `bash outils/mvn-jdk21.sh -B clean verify` : BUILD SUCCESS avant chaque commit. Il avait ete rouge une fois sur `-03` (section 4, E1).

## 4. Ecarts

- **E1, a rejuger par nadia-prof.** Le texte exact de l'item 4 (`-03`) rougit `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`, clause 2, `FOP-R4`) : *"cm1-emc-respect-differences.yaml : 4 distracteurs sur 22 commencent par "lea parce que la", qu'aucune de ses 11 bonnes reponses assez longues ne commence."* Le paquet n'avait pas d'entree de catalogue, donc ce garde-fou ne le lisait pas ; son premier branchement le lit. Quatre distracteurs partagent l'amorce : *"Malo, parce que la récréation..."* (item 1), *"Tom, parce que la partie..."* et *"Léa, parce que la récréation..."* (item 4 corrige), *"Léa, parce que c'est..."* (item 5, un seul mot d'ecart).
  Je n'ai ni assoupli le garde-fou ni ajoute d'exemption (`DETTE_D_AMORCE`, C5). J'ai applique la clause 3 de la section C du planning (un autre texte qui atteint la meme barre, ecrit dans le rapport avec son motif) : *"Tom, parce que la partie a déjà quatre joueurs"* devient *"Tom, parce que cette partie a déjà quatre joueurs"*. Les deux autres propositions et l'indice sont mot pour mot ceux de l'arbitrage. Effet de bord : le trio fait 46, 49 et 41 caracteres (le texte exact faisait 46, 46 et 41). La barre de 2.1 demande "la meme longueur" (`CAT-1`) : ni le texte exact ni le mien ne la tiennent au caractere pres, a toi de lire si l'ecart compte. Le surcout de 0,25 de l'arbitrage B section 6 ("reecrit") est a elle. Ecart dit dans l'en-tete du paquet et dans le message du commit.
- **E2, critere `C7` / `EMC-9`.** Sans objet sur `-03` et `-06` (arbitrage B 1.2) ; je ne l'ai pas etendu. La ligne "relu" de `EMC-9` n'existe donc pas ci-dessous.
- **E3, defaut de critere.** Les commandes `grep -c` du planning (`jeu de cartes`, `disparaissent avec le temps`) comptent aussi les commentaires d'en-tete : un en-tete qui explique la correction en citant le texte retire fait monter le compte "apres". A lire de la meme facon par `yanis-qa`. Le critere `grep -c 1948` / `2018-717` est faible comme `lecture-testabilite.md` le dit : n'importe quelle occurrence le satisfait.
- **E4, a juger par nadia-prof, non change.** Fuite d'un indice vers la reponse d'un autre item (point 3.3.5 de l'arbitrage B), nouvelle depuis l'item 8 corrige : l'indice de l'item 3 de `-06` (*"Ces élèves se soutiennent les uns les autres."*) reprend les trois mots de la bonne reponse de l'item 8 (*"se soutenir et s'entraider les uns les autres"*). Le couple n'etait pas dans sa liste (items 7 et 2, 9 et 4).
- **E5, non change.** Les indices des items 10 et 11 de `-03` nomment le contenu de leur bonne reponse (*"Cherche le motif qui parle du jeu."* pour *"le jeu se joue à deux et les deux places sont prises"* ; *"...ce qui s'est déjà passé."* pour *"il a déjà été choisi la veille"*) et l'indice de l'item 6 de `-03` (*"Respecter ne veut pas dire devenir semblable."*) ecarte un distracteur, le motif que s53 4.3 retient contre les items 8 et 9 de `-06`. Je ne l'ai pas corrige : texte non decide par elle a ce passage, a elle.

## 5. Relu / non relu (C7)

Regles nommees par nadia-prof (arbitrage B 1.2). Une lecture de ma part, pas une preuve : elle les juge en jouant.

**`-03`, `cm1-emc-respect-differences`**

- `EMC-4` : **relu**, sur les 11 items, distracteurs compris. Aucune proposition n'attribue un trait ou une capacite a une categorie : la difference est toujours le motif d'un traitement. Item 4 corrige : *"Aya, parce qu'elle utilise un fauteuil roulant"* ne dit rien de ce qu'elle peut faire, dans un jeu de cartes (gardee mecaniquement : `emc4LaPropositionDuFauteuilRoulantNeDitQueLeHandicap`). Restent a sa lecture : *"plus petit que les autres"* (items 5 et 11) et *"lunettes épaisses"* (item 10), motifs et non capacites.
- `EMC-8` : **relu**. Aucun item ne demande ce que l'enfant fait ou ferait ; aucun pronom de deuxieme personne (gardee). Les indices sont a l'imperatif (*"Pense à"*, *"Cherche"*) : la regle porte sur la question, je ne l'ai pas lue comme portant sur l'indice.
- Regle de s53 section 4.1 (un indice ne redit pas la bonne reponse) : **relu**, resultat mitige, E5. Items 7 et 8 : textes de nadia-prof, un exemple que l'enfant relie lui-meme ; je n'y vois pas de redite. Items 1 a 5 et 9 : l'indice nomme le critere (la personne, pas la situation) sans dire le contenu de la bonne reponse. Items 6, 10, 11 : E5.

**`-06`, `cm1-emc-principes-republique`**

- `EMC-4` : **relu**. Aucune proposition ne decrit un groupe ni ne lui attribue un trait. Les distracteurs des items de laicite decrivent ce que la Republique n'est pas (*"elle impose à tous la religion de la majorité"*), jamais un groupe de croyants.
- `EMC-8` : **relu**. Aucun item ne demande ce que l'enfant fait ou ferait ; aucun pronom de deuxieme personne (gardee).
- Regle de s53 section 4.1 : **relu**, avec P4 ci-dessous.
- `BIA-5` : **relu**. Aucun distracteur n'enonce un interdit de croire, de pratiquer ou de parler de religion, et aucun ne juge une conviction. Les trois items de laicite (4, 9, 11) sont lus ensemble ; leur effet cote a cote a l'ecran reste a elle (3.3.2).

**C8, motifs de refus ecrits mot pour mot**

- `P3` (`la liberté` hors des distracteurs de l'item 4) : **garde**, `p3LaLiberteNEstPasUnDistracteurDeLItemDeLaLaicite`. Mutation jouee par moi : `"la démocratie"` remplace par `"la liberté"` dans le YAML, `-pl adapters/adapter-content -am` classe nommee, **1 echec sur 12, celui-la** ; YAML remis, `git diff` du fichier revenu a mon seul diff. `yanis-qa` rejoue. Ne garde pas un synonyme.
- Items 6 et 8 de `-06`, items 4, 7, 8 de `-03` : le texte refuse ne revient pas : un test boucle sur les textes refuses (5 sur `-03`, 2 sur `-06`) et un test sur le jeu fautif verifie que chacun est detecte.
- **non garde : P4** (un indice ne redit pas la bonne reponse de son item, `-06`). Aucun automate. Les six indices lus, items 6 a 11, tels que s53 4.3 les nomme (le 6 de `marc-dev`, les 7 a 11 de `nadia-prof`), aucun modifie :
  - 6, *"Teste chaque phrase avec un exemple pris dans la classe."* : ne redit pas *"jusqu'à ce qu'elle nuise à autrui"* ; avec le nouveau distracteur il ne pousse plus vers *"qu'un adulte la refuse"* (retire) ;
  - 7, *"Pense à une règle de la classe qui vaut pour chaque élève."* : exemple, ne reprend pas *"mêmes droits, sans distinction"* ;
  - 8, *"Pense à des personnes qui apportent leur aide après une tempête."* : exemple ; *"apportent leur aide"* est plus proche de *"s'entraider"* que de l'ancienne reponse, a elle (voir aussi E4) ;
  - 9, *"Pense à des élèves d'une même classe aux croyances différentes."* : exemple, ne dit pas *"croire ou ne pas croire"* ;
  - 10, *"Pense à un club de sport qui refuse un enfant pour son origine."* : decrit un acte ;
  - 11, *"Pense à un arbitre de match, qui ne joue pour aucune équipe."* : analogie, en miroir de *"sans en favoriser aucune"*.

## 6. Joue

Jars construits par `verify` sur le contenu des deux commits, lances sur JDK 21.0.10, une partie complete de 10 manches par jeu via HTTP, processus arrete par PID.

- `-03` : la consigne servie est *"Lis bien, puis choisis la bonne réponse."*. J'ai vu a l'ecran l'item 4 (*"jeu de cartes"*, les trois propositions), l'item 7 avec son indice sur deux amis apres une erreur, l'item 8 repondu juste du premier coup. Aucune reponse n'est dans un enonce.
- `-06` : les items 6 et 8 s'affichent avec les textes de l'arbitrage ; l'item 4 propose `la démocratie`, `la laïcité`, `la fraternité`. Indices vus a l'ecran apres une erreur : items 2, 3, 4, 5, 6, 7, 8 et 10 ; items 1, 9 et 11 non vus (repondus juste du premier coup). L'item 8 s'affiche avec *"se soutenir et s'entraider les uns les autres"* et son indice sur la tempete.
- Titre servi du catalogue : *"Ce que le respect interdit"* ; *"Les principes de la République"* (non regarde sur l'ecran de choix de niveau pour `-06`, lu au catalogue).

## 7. Jobs de `ci.yml`

- **`build`** : `mvn verify` par `bash outils/mvn-jdk21.sh -B clean verify`, trois fois (une rouge, E1 ; une verte sur `-03` ; une verte sur `-06`).
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, et `verifier-prefixes.sh` avec entree explicite (`git log --no-merges --format=%s fa4ddc7e..HEAD`), sortie 0, sur les deux commits.
- **`chemins`** : `verifier-chemins-test.sh` et `verifier-chemins.sh` sur `git ls-files`, sortie 0, apres le commit `-03` et apres le commit `-06`.
- **`outils`** : balayage des trois motifs de la CI (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), 10 juges lances, 0 rouge, une fois, sur l'arbre apres `-06`. Aucun outil n'est modifie par mes commits.
- **Non lance** : l'etape *"Verifier la presence du build Maven"* de `build`, et l'etape *"Package du fat jar"* (`mvn -B -pl bootstrap -am package`) : le jar joue vient de `verify`.

## 8. Non verifie

- Tout le jugement pedagogique : `EMC-5` sur les items 1 a 5, l'exactitude des cinq familles de sources de l'en-tete de `-06`, la formulation *"la même valeur"* contre la Declaration de 1948 (je cite la source comme l'arbitrage la nomme, je ne l'ai pas relue), le vocabulaire a l'ecran, la lourdeur de *"le respect d'autrui n'est-il pas respecté"* repete cinq fois, le doublon des items 6 et 10 de `-03` avec l'item 10 de `-06`.
- Les indices des items 4 et 8 de `-03`, et ceux des items 1, 9 et 11 de `-06`, n'ont pas ete vus a l'ecran (repondus juste du premier coup).
- Les fixtures sont ecrites avec des fins de ligne LF ; le poste est en `core.autocrlf=true`. Git avertit, `verify` est vert.
- L'effet de E1 sur `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` : vert, non lu autrement.
