# [Sprint s57] Rapport de developpeur - poste 7, `S57-BR-FRA-CE1-04`

- **Auteur** : `marc-dev`. **Date** : 2026-10-14.
- **Arbre** : branche `s57-marc-fra-ce1-04`, worktree `../recreo-s57-marc-fra-ce1-04`, issue de `round-two` a `afade53a` (`git log -1 --format=%h` au premier message). `round-two` a avance depuis : `git merge-tree --write-tree round-two HEAD` sort 0, aucun conflit.
- **Commit du lot** : `4dd6f298` (code, contenu et tests dans le meme commit). Non fusionne, non pousse.
- **Scratchpad et port** (`I1` a `I3`) : repertoire `s57-p1-marc-dev-a`, port 18521. Port libre avant le lancement (`netstat` sans ligne LISTENING), ligne *"Tomcat started on port 18521"* et PID 33020 lus dans le journal du jar, arret par ce PID seul, port rendu (plus aucune ligne LISTENING).
- **JDK de mesure** : 21.0.10, `java.version` lu dans les rapports Surefire des cinq modules.
- **K1 (c)** : sans objet, le lot est de l'instance A.

## Ce qui est livre

| Fichier | Changement |
|---|---|
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | une entree : `bandit-manchot-texte-narratif-ce1`, paquet `ce1-texte-narratif`, `nombreDeManches: 10` |
| `adapters/adapter-content/src/main/resources/contenu/ce1-texte-narratif.yaml` | les items de Sara et de Nour (`D1`, `D2`) : 5 lignes changees de chaque cote, les textes ne bougent pas |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | deux chaines d'inventaire : l'identifiant et le libelle de competence |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1TexteNarratifTest.java` | 32 tests |
| `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1TexteNarratifTest/` | un catalogue fautif et un jeu fautif de neuf items |

**Deux chaines d'inventaire, pas trois** (`-10` et `-15` en ont trois) : le titre et la consigne sont ceux de l'entree du CP (`bandit-manchot-texte-narratif-cp`), deja inventories.

**Textes lus par l'enfant, de ma main, a juger par `nadia-prof`** :

1. Le titre *"Lis et réponds"* et la consigne *"Lis le petit texte, puis réponds à la question."*, copies de l'entree du CP. Le CP et le CE1 portent donc le meme titre, sur deux pages de niveau distinctes (`/niveaux/CP/matieres/francais` et `/niveaux/CE1/matieres/francais`, lues sur le jar). *"Un ou plusieurs ?"* est deja dans ce cas. Aucun garde-fou ne l'a refuse. Si `nadia-prof` veut un titre propre au CE1, c'est une ligne du catalogue et une chaine d'inventaire.
2. Les propositions et l'indice de Sara et de Nour (ci-dessous). Elles reprennent mot pour mot l'exemple de `lecture-nadia-palier-1.md` section 1.1, qu'elle donne *"a titre d'exemple"* : les textes sont au developpeur, elle les juge en jouant.

| Item | Bonne reponse | Distracteurs | Indice |
|---|---|---|---|
| Sara | `pèle une pomme` (avant : `Sara pèle une pomme`) | `la coupe en morceaux`, `la mange` | `Relis le début du texte.` (avant : `Pense à ce qu'on fait avant de couper un fruit.`) |
| Nour | `range ses jouets` (avant : `Nour range ses jouets`) | `se lave les mains`, `se met à table` | inchange |

## Criteres, avec les commandes

Les commandes de `S1` a `S3` et `S6` sont celles de l'annotation du 2026-10-14 de `backlog.md`, lancees sur le fichier corrige.

| Critere | Commande | Resultat |
|---|---|---|
| `S1` | `grep -c '^ *- enonce:'` sur le YAML | 20 |
| `S2` | le script `python -I` de l'annotation (majuscule Unicode, tous les items) | 0 (2 avant, sur le fichier d'origine `git show HEAD:...`) |
| `S3` | `grep '^ *indice:' ... \| grep -vcE 'indice: "(Relis\|Cherche)'` | 0 (1 avant) |
| `S3`, second nombre | `grep -c "Pense à ce qu'on fait avant de couper un fruit"` | 0 (1 avant) |
| `S4`, mesure de la borne | script de poste, regle de s47 (mot : une lettre ou un chiffre ; fin de phrase : `.`, `?`, `!` qu'une majuscule suit) | au plus 22 mots, 4 phrases, 10 mots par phrase, 20 textes distincts : le chiffre de `nadia-prof` |
| `S4`, garde-fou | `grep -rl 'LON-CE1-3' --include=*.java .` | `PaquetCe1TexteNarratifTest.java` (0 fichier avant) |
| `S6` | script de poste, comparaison sans casse | 0 proposition hors du texte sur 60 |
| `K2` | `git diff-tree --no-commit-id --name-only -r 4dd6f298` suivi de `grep -vE` sur la liste de `K2` augmentee du YAML du paquet | 6 fichiers, 0 hors liste |
| `K3` | `grep -c "paquet: ce1-texte-narratif$"` sur `catalogue.yaml` ; `grep -c "  - id: bandit-manchot-texte-narratif-ce1$"` | 1 ; 1 (0 avant). Entrees du catalogue : `grep -c '^  - id:'` rend 148 avant, 149 apres |
| `K3`, espace avant `?:;!` | `tail -6 catalogue.yaml \| grep -P ' [?:;!]'` | 0 (ni le titre ni la consigne n'en portent) |
| `K4` | `PaquetCe1TexteNarratifTest` et son dossier de fixtures | 32 tests, 6 fichiers dans le commit, dont 4 de test |
| `K5` | aucun garde-fou assoupli | aucun test existant n'est modifie, hors deux chaines d'inventaire ajoutees |
| `K9` | `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` | vide ; aucune fiche ouverte |
| `K10` | `git diff-tree --no-commit-id --name-only -r 4dd6f298` suivi de `grep -E 'AucunMotServiNeSEcarteDeSonAccentTest\|LexiqueFrancais\|ADR-0065\|pom\.xml'` | ne rend rien |

## `K7` : `TRV-CE1-5`

**Relu.** Les 20 indices lus un a un, puis comptes par script : 20 indices, **18 distincts**, **au plus 9 mots**, 10 commencent par *Relis* et 10 par *Cherche*, aucun renvoi a l'oreille, aucun ne contient une proposition de son item (mot entier). Conforme a la condition (douze mots au plus, jamais la reponse, pas identique sur tout le paquet). L'indice neuf de Sara, *"Relis le début du texte."*, dit ou regarder et ne cite aucune proposition. Garde par `trv5ChaqueIndiceCompteAuPlusDouzeMots...`, `trv5AucunIndiceNeContientUnePropositionDeSonItem` et `d2s3ChaqueIndiceRenvoieAuTexte...`. `TRV-CE1-2` : 5 items sur 20 ou la bonne reponse est strictement la plus longue (5 avant la correction), borne du test : la moitie.

## `K8` : un test qui mord, ou une ligne *"non garde"*

| Point d'audit | Garde-fou |
|---|---|
| 6.1 (1), majuscule | `d1s2LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems` ; jeu fautif a une majuscule accentuee (*Élise*) que l'ancien `awk` ne lisait pas ; mutations B et H |
| 6.1 (2), indice de Sara | `d2s3ChaqueIndiceRenvoieAuTexteEtLIndiceDeSaraNEstPlusCelui` (forme : *Relis* ou *Cherche*, et la phrase retiree) ; mutation C |
| 6.1 (3), `LON-CE1-3` et `LON-CE1-4` | `lon3s4...`, `lon4s4AucunTexteSupportNeDepasseQuatrePhrases`, `lon4s4AucunePhraseDuTexteSupportNeDepasseDouzeMots` ; un jeu fautif chacun ; mutations D, E, F |
| **non garde : ce que dit l'indice neuf** (qu'il fasse relire et non deduire du monde) | le test voit le premier mot, pas le sens ; `nadia-prof` en jouant (`D2`) |
| **non garde : lisibilite des propositions raccourcies de Sara et de Nour** (*"la coupe en morceaux"*) | lecture de `nadia-prof` (lecture du palier, section 5) |
| **non garde : la majuscule sur les items *"en dernier"*** | limite que `nadia-prof` regarde au jeu (`D1`, limite) |
| **non garde : aucun passe simple** (`RCT-CE1-3`) | aucune regle lexicale ecrite |
| **non garde : *"trois evenements"* au sens strict** (`RCT-CE1-3`) | le test borne par le bas le nombre de phrases (trois) d'un texte d'ordre ; un evenement par phrase est un proxy (le texte d'Ibrahim porte quatre evenements en trois phrases) |
| **non garde : la reponse est ecrite dans le texte, aucune inference** (`RCT-CE1-1`) | `RCT-CE1-2` garde que chaque proposition est dans le texte, pas que la bonne se lise sans inference |
| **non garde : aucun role ni activite par genre** (`TRV-CE1-6`, *"Aya joue au foot"*) | le test garde l'equilibre des prenoms, pas ce qu'ils font |
| **non garde : regle fausse dans un indice** (`TRV-CE1-5`) | lecture de `nadia-prof` |

Le genre des 34 prenoms est une declaration d'auteur dans le test (17 et 17), pas un lexique du depot. Un prenom hors de la table ou des ouvertures de phrase fait echouer le garde-fou en se nommant ; un prenom de la table absent des textes aussi (`trv6ChaquePrenomDeLaTableDeGenreFigureDansUnTexte`).

## Mutations

Fichier mute : `ce1-texte-narratif.yaml` ou `catalogue.yaml`, copie de sauvegarde restauree apres chaque mutation (`cmp` identique a la fin). Test lance : `PaquetCe1TexteNarratifTest`, 32 tests lances a chaque fois, via `-pl adapters/adapter-content -am`.

| Mutation | Tests lances | Resultat |
|---|---|---|
| A : les items de Sara et de Nour remis dans leur etat d'origine | 32 | 2 echecs : `d1s2...`, `d2s3...` |
| B : Nour avec son prenom et *"elle"* remis | 32 | 1 echec : `d1s2...` |
| C : l'indice de Sara remis | 32 | 1 echec : `d2s3...` |
| D : un texte a 31 mots (trois phrases de 10, 10 et 11) | 32 | 1 echec : `lon3s4...` |
| E : un texte a 5 phrases | 32 | 1 echec : `lon4s4AucunTexteSupportNeDepasseQuatrePhrases` |
| F : une phrase de 13 mots | 32 | 1 echec : `lon4s4AucunePhraseDuTexteSupportNeDepasseDouzeMots` |
| G : un distracteur absent du texte (*"la goûte"*) | 32 | 1 echec : `rct2s6...` |
| H : *"Élise pèle une pomme"* seule a majuscule, sur le texte de Sara | 32 | 2 echecs : `d1s2...` et `trv6ChaquePrenomDeLaTableDeGenreFigureDansUnTexte` (Sara n'est plus dans aucun texte : la table pourrit, le garde-fou le dit) |
| I : quatre personnages nommes dans un texte | 32 | 1 echec : `rct3AuPlusTroisPersonnagesNommesParTexte` |
| J : consigne du catalogue changee | 32 | 1 echec : `k3...` |
| K : identifiant du CP repris par l'entree | 32 | 1 echec : `k3...` |
| L : entree retiree du catalogue | 32 | 1 echec : `k3...` |

**Deux faux resultats evites, nommes.**

1. **Premier lancement de la serie, 12 mutations : `mvn exit 2`, aucun test lance, aucun bilan.** Le script de poste appelait `bash` par le `PATH` de Python, qui designe un autre interpreteur que celui de Git Bash ; `outils/mvn-jdk21.sh` n'a pas demarre. Je n'ai pas lu cette sortie comme "rien n'a rougi, donc vert" : la ligne *"tests lances"* etait vide. Relance avec le bon interpreteur.
2. **Mutation F, premiere version : 32 tests, 0 echec.** Ma phrase *"Après la classe, il range ses affaires dans son sac."* compte 10 mots, pas 13. Le test n'etait pas en cause ; la phrase l'etait. Refaite a 13 mots, le test rougit.

**Un rouge de ma premiere version, trouve par le build complet et pas par la mesure partielle** : `AucunTestNeRecopieUneAssociationDuCatalogueTest` (ADR-0061) a refuse ma premiere ecriture de `PaquetCe1TexteNarratifTest`, qui portait en constantes l'identifiant de l'entree et le nom du paquet. Corrige avant le commit (le test ne porte plus l'identifiant de l'entree, il verifie qu'il est different de celui du CP). Un `-Dtest=PaquetCe1TexteNarratifTest` ne l'aurait pas vu.

## Jeu au jar, port 18521

Jar construit par `bash outils/mvn-jdk21.sh -B clean verify` sur l'arbre du commit, lance avec `--server.port=18521`. Lu dans le HTML rendu (pas de capture d'ecran) :

- Page *Francais* du CE1 : *"Lis et réponds"* est la derniere ligne de la liste, apres *"Des mots bien accordés"*.
- **Sara** : le texte, l'enonce *"Que fait Sara avant de couper la pomme ?"* et les trois propositions `pèle une pomme`, `la coupe en morceaux`, `la mange` sont sur le meme ecran. Bonne reponse : *"Bravo !"*. Chacune des deux autres : *"Ce n'est pas ça. Essaie encore !"* puis *"Relis le début du texte."*
- **Nour** : propositions `range ses jouets`, `se lave les mains`, `se met à table` ; sur les deux mauvaises, *"Relis la première phrase."*
- **Inès** (item au pléonasme) : inchange, *"Relis le texte jusqu'à la fin."*

**Observation pour `nadia-prof`, non tranchee** : sur Sara et sur Nour, la bonne reponse est la seule proposition qui ne commence pas par *"la"* (Sara) ou par *"se"* (Nour). Ce n'est plus une majuscule, c'est un autre signe de surface. La forme est la sienne (section 1.1), je ne l'ai pas changee.

## Ce que je n'ai pas pris

- **Le pléonasme *"Elle sort dehors."*** (item d'Inès) : recommandation non bloquante de `nadia-prof`, que `camille-po` n'a pas reprise en critere. Je ne l'ai pas corrige. Si `nadia-prof` ou `camille-po` la veut, c'est une ligne du texte, deux lignes de proposition, et un test inchange.

## Jobs de `ci.yml` lances, avec leur sortie

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur l'arbre qui est celui du commit `4dd6f298` : `BUILD SUCCESS`. Surefire : domain 120, application 31, adapter-web 486, adapter-content 1566, bootstrap 371 ; Failsafe 6 ; **total 2580, 0 echec, 0 erreur, 0 ignore** ; JDK 21.0.10. **Delta : +32 tests**, ceux de `PaquetCe1TexteNarratifTest` ; base : `afade53a`, dont `planning.md` donne 2548 (adapter-content 1534), non remesure par moi. **Ce total est celui de mon reacteur, pas de l'arbre fusionne.** Un premier lancement de cette meme commande, avant la correction decrite plus haut, avait rendu 1 echec (`AucunTestNeRecopieUneAssociationDuCatalogueTest`). L'etape *"Package du fat jar"* n'est pas lancee, `verify` produit le jar joue.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "HEAD^!"` : *"0 commit(s) le touchent dans la plage"*, sortie 0.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) et les fichiers neufs passent a `verifier-chemins.sh`, sortie 0.
- **Ce fichier** : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`-pl bootstrap -am`, 5 tests, 0 echec, `BUILD SUCCESS`) relance avec ce rapport dans l'index ; le `build` complet ci-dessus a ete lance avant son ecriture. `verifier-chemins.sh` sur les fichiers suivis et neufs, ce rapport compris : sortie 0.
- **`outils`** : non lance, aucun outil modifie.
- La CI distante n'est pas lue.

## Ecarts et questions

1. **Message de `4dd6f298`** : il annonce *"quinze jeux fautifs commites"*. Le compte reel est **un jeu fautif de neuf items et un catalogue fautif, soit 12 tests de jeu fautif**. Je n'ai pas amende le commit (pas de reecriture) ; ce rapport est la correction.
2. **Question a `nadia-prof`** : le titre *"Lis et réponds"* identique a celui du CP convient-il, et l'observation sur *"la"* et *"se"* (section Jeu au jar).
3. **Question a `camille-po` ou `nadia-prof`** : le pléonasme d'Inès est-il pris ?
4. **Aucune regle d'architecture decidee, aucun critere modifie.**

## Ce que ce rapport ne fait pas

Ne prononce aucun Done, ne fusionne rien, ne juge pas le titre, la consigne ni les propositions que j'ai ecrits, ne repond a aucune question de `nadia-prof`.
