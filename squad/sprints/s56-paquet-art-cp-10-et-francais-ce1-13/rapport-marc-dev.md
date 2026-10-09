# Rapport de marc-dev - s56, `S56-BR-FRA-CE1-13` (branchement de `ce1-verbe-sujet`, `FRA-CE1-13`)

Branche `s56-marc-fra-ce1-13`, issue de `round-two` au commit de base `806aeb46`. Commit de branchement : `550df967`. Rien n'est pousse, rebase ni fusionne. Depuis, `round-two` a avance (`f3de5e58` en tete) ; aucune de ses nouveautes ne touche `ce1-verbe-sujet`, `catalogue.yaml` ni l'arbitrage de `nadia-prof` (`git diff 806aeb46 round-two` suivi de `grep -c "FRA-CE1-13\|verbe-sujet"` sur le backlog et l'arbitrage rend 0).

Source des textes : `squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, section 2. Les cinq enonces, les cinq distracteurs, l'indice de `J4` et les deux lignes d'en-tete de `J1` sont extraits de l'arbitrage par script (expression reguliere sur le fichier), jamais retapes : espaces insecables comprises, verifie au diff et par la propriete 6.

## 1. Livre

| Condition | Application |
|---|---|
| `J1` | 10 lignes `bonneReponse` (items 1, 2, 4, 5, 6, 14, 16, 17, 18, 20) sans majuscule ; deux lignes d'en-tete a la suite de celle de `VSJ-CE1-3` |
| `J2` | items 9, 10, 12, 13 : `enonce` et `distracteurs` de 2.3 |
| `J3` | item 7 : `enonce` et `distracteurs` de 2.4 |
| `J4` | indice des items 1, 5, 9, 13, 17 : `Demande-toi : qui fait l'action de ce verbe ?` |
| `J5` | entree `bandit-manchot-verbe-sujet-ce1`, consigne `Lis bien, puis choisis la bonne réponse.`, titre `Le verbe et son sujet` (le titre propose par `nadia-prof`), `nombreDeManches: 10`, juste apres `bandit-manchot-classes-de-mots-ce1` |

Inventaire de `TexteAccentueExposeParLePortEntrantTest` : **3 chaines** (identifiant, titre, competence copiee de `competences-ce1.md`). La consigne y figure deja (deux fois), je n'en ajoute pas une troisieme. `H1` (indice de l'item 3) et `H2` : non appliquees, hors commande.

Fichiers du commit : `catalogue.yaml`, `ce1-verbe-sujet.yaml`, `PaquetCe1VerbeSujetTest.java`, `contreexemple/PaquetCe1VerbeSujetTest/catalogue.yaml` et `.../contenu/ce1-verbe-sujet-fautif.yaml`, `TexteAccentueExposeParLePortEntrantTest.java`. C2 : `git diff-tree --no-commit-id --name-only -r 550df967` suivi du `grep -vE` de la liste rend 0 ligne. C9 : `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` vide. Aucune fiche de conformite, aucun `REGISTRE` touche.

## 2. Les onze proprietes de 2.8 (avant : `806aeb46` ; apres : `550df967`)

`Y` = `adapters/adapter-content/src/main/resources/contenu/ce1-verbe-sujet.yaml`. Les valeurs "avant" des proprietes portees par un test sont recalculees par un script de poste hors depot (lecture independante du Java), et retrouvent celles de `nadia-prof`.

| # | Commande | Avant | Apres | Porteur apres |
|---|---|---|---|---|
| 1 | `grep -c 'bonneReponse: "[[:upper:]]' Y` | 15 | **5** (`Léa`, `Tom`, `Inès`, `Noé`, `Sofia` : items 8, 9, 10, 12, 13) | grep ; couvert aussi par 2 |
| 2 | bonne reponse seule a majuscule | 12 | **0** | `propriete2J1LaMajusculeNeDesigneJamaisLaBonneReponse` |
| 3 | propositions = suite de mots de la phrase | 24 sur 24 | **24 sur 24** | `propriete3ChaquePropositionEstUneSuiteDeMotsDeLaPhraseCitee` |
| 4 | prenoms par occurrence, feminins / masculins | 7 / 5 | **8 / 8** | `propriete4LesPrenomsFemininsEtMasculinsSontALUnPres` (listes de prenoms et de genre dans le test ; une majuscule hors listes fait echouer) |
| 5 | `grep -c 'verbe cité' Y` ; `grep -c "qui fait l'action de ce verbe" Y` | 5 ; 0 | **0 ; 5** | grep ; `propriete5LeMotCiteSortDesIndices...` |
| 6 | `grep -c` de chacun des 5 debuts (`Tom prête son livre à Lina`, `Inès montre un dessin à Sami`, `Noé écrit une carte à Awa`, `Sofia ouvre la porte à Hugo`, `Noé range ses crayons`) | 0, 0, 0, 0, 0 | **1, 1, 1, 1, 1** | `propriete6LesCinqEnoncesDeJ2EtJ3SontDansLePaquetMotPourMot` (egalite sur la ligne entiere, espaces insecables en ` `) |
| 7 | `grep -c 'consigne: "Lis bien, puis choisis la bonne réponse."' catalogue.yaml` ; `grep -cP '^ *(titre\|consigne): ".* [?:;!]' catalogue.yaml` | 5 ; 23 | **6 ; 23** | grep ; `propriete7J5LaConsigneDuJeuEstCelleDesCinqEntreesExistantes` |
| 8 | 15 sujets ; pronom / nom seul / GN ; place du sujet | 15 ; 5, 5, 5 ; 15 sur 15 | **inchange : 15 ; 5, 5, 5 ; 15 sur 15** | `vsjCe11EtCe12QuinzeItemsDeSujetAuMoinsEtCinqPourChaqueFormeDeSujet`, `vsjCe12LeSujetEstAvantLeVerbeCiteEtLesDeuxDistracteursApres` |
| 9 | indice contenant une proposition de son item | 0 | **0** | `propriete9AucunIndiceNeContientUnePropositionDeSonItem` |
| 10 | `grep -c '^ *- enonce:' Y` | 24 | **24** | grep ; `propriete10LePaquetGardeSesVingtQuatreItems...` |
| 11 | bonne reponse strictement la plus longue | 6 | **6** (borne : au plus 12) | `propriete11LaBonneReponseNEstStrictementLaPlusLongue...` |

Le test de la propriete 8 compte au moins 12 sujets et au moins la moitie de l'ensemble, et au moins 5 par forme ; il ne verrouille pas "15" (la propriete est dite inchangee, la condition est `VSJ-CE1-1`). Une proposition hors de la phrase y est ignoree : elle releve de la propriete 3.

Proprietes 5, 6 et 10 : le test porte la meme propriete que la commande, la commande reste la mesure du critere.

## 3. Mesures

Mesure sur `806aeb46` plus mon seul lot. Ce ne sont pas des totaux apres fusion ; `sofia-sm` etablit le total.

- **Delta** : **+22 tests**, tous dans `PaquetCe1VerbeSujetTest` (16 sur le paquet, 6 contre-exemples dont 5 sur le jeu fautif et 1 sur le catalogue fautif). `TexteAccentueExposeParLePortEntrantTest` : aucun test ajoute, l'inventaire s'etend. Reacteur apres : `adapter-content` 1445 tests, `bootstrap` 333, `adapter-web` 407, `domain` 120, `application` 18, integration 6. Le seul chiffre de base que je connais (1423 pour `adapter-content`, backlog, arbre `c986adcf`) n'est pas une mesure de ma part.
- **Version** : JDK **21.0.10**, rapportee par `outils/mvn-jdk21.sh` (premiere ligne de sa sortie : `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10`). Le `java` du `PATH` est un JDK 17, non utilise pour les mesures.
- **Jobs de `.github/workflows/ci.yml` lances, avant le commit, sur l'arbre du commit** :
  - `build` : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine du worktree, sans `-pl` : `BUILD SUCCESS`, code 0, six modules `SUCCESS`. Non lancee : l'etape "Verifier la presence du build Maven".
  - `prefixes` : `verifier-prefixes-test.sh` code 0 ; `commit-atteignable-test.sh` code 0 ; `verifier-prefixes.sh` sur le sujet de `550df967` code 0.
  - `chemins` : `verifier-chemins-test.sh` code 0 ; `verifier-chemins.sh` sur `git ls-files` (hors le test) code 0, rejoue avec ce rapport dans l'index : code 0.
  - `outils` : les trois motifs de la CI balayes dans l'ordre (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) : 10 juges, 0 code non nul. Ma boucle jette la sortie des juges et note le code ; elle ne reproduit pas l'echo `--- <fichier>` ni la garde "motif vide" de la CI.
- **Mutations sur le materiau reel** (un seul fichier `Y` modifie, test de classe seul avec `-pl adapters/adapter-content -am`, puis `git checkout -- Y`, arbre propre ensuite) :
  - M1, item 1 `elle` -> `Elle` : 1 echec, `propriete2J1...` (la propriete 2 seule : `Mia` d'autres items ne masque rien, les distracteurs de l'item 1 sont en minuscule).
  - M2, item 10 `Sami` -> `Lina` (enonce et distracteur) : 2 echecs, `propriete4...` (9 / 7) et `propriete6...`.
  - M3, item 2 : indice rallonge de ` parc` : 1 echec, `propriete9...`.
  - M4, item 14 distracteur `le lait` -> `le chat` : 2 echecs, `vsjCe12...` (distracteur avant le verbe) et `propriete10...` (propositions non distinctes).
  - Premiere tentative de M3 : le script n'a pas applique la mutation (`ValueError`, espace insecable dans `« Ils`), la mesure rendait 22 verts ; elle a ete refaite et c'est la seconde qui est citee. Le sort de ce faux vert n'est pas dans le tableau ci-dessus.
- **Jeu joue** : le jar construit au commit (`java -jar bootstrap/target/recreo.jar` sur un port libre, JDK 21, session HTTP par `curl`, arret du processus par son PID, port ferme ensuite) : la page `/niveaux/CE1` propose le bouton `Le verbe et son sujet` ; `POST /parties/bandit-manchot-verbe-sujet-ce1` rend 200 et une premiere manche de texte `Lis bien, puis choisis la bonne réponse.`, `« Il lit une histoire à Mia. » Quel est le sujet du verbe « lit » ?`, propositions `il`, `Mia`, `une histoire`. Reponse de l'enfant, indice, manches suivantes : non jouees.

## 4. Regles relues : "relu" ou "non relu"

"Relu" = lu par moi dans le fichier apres modification, avec la commande ou le test nomme ; le jugement pedagogique reste a `nadia-prof`.

| Regle | Etat | Preuve |
|---|---|---|
| `J1` | relu | proprietes 1 et 2 ; diff de `Y` : dix lignes `bonneReponse`, rien d'autre dans ces items |
| `J2` | relu | propriete 6 ; un prenom en distracteur par item (`Lina`, `Sami`, `Awa`, `Hugo`) ; "ne fait pas l'action" : lecture, non testee |
| `J3` | relu | propriete 6, item 7 ; propriete 4 (8 / 8) |
| `J4` | relu | propriete 5 ; "aucun distracteur de ces cinq items ne fait l'action" : lu (`le livre`, `la table` ; `les jouets`, `le sac` ; `son livre`, `Lina` ; `la porte`, `Hugo` ; `une lettre`, `Mia`), non testee |
| `J5` | relu | propriete 7 ; consigne sans signe double, titre sans signe double |
| `TRV-CE1-5` avec la barre de s54 | relu | indice de chaque item modifie lu contre ses trois propositions ; propriete 9 verte ; aucun indice des 24 ne donne un critere qu'un distracteur satisfait a ma lecture, hors `H1` |
| `TRV-CE1-6` | relu | propriete 4, 8 / 8 ; `range` une fois de chaque cote (`elles rangent`, `Noé range`) |
| `VSJ-CE1-1`, `-2` | relu | proprietes 3 et 8, toutes vertes |
| `VSJ-CE1-3` | non relu par un test | les commentaires `# VSJ-CE1-3: a, b` et `c` sont inchanges ; apres `J2`, la famille (a) et (b) des items 9, 10, 12, 13 : (a) le groupe apres le verbe (`son livre`, `un dessin`, `une carte`, `la porte`) et (b) un autre nom (`Lina`, `Sami`, `Awa`, `Hugo`) sont lus, aucun garde-fou ne les porte |

Recherche, apres chaque correction d'indice ou d'enonce, du mot de la bonne reponse dans le reste du fichier (`squad/README.md`, section 9) : les bonnes reponses des items 1, 5, 9, 13, 17 (`elle`, `elles`, `Tom`, `Sofia`, `le facteur`) et des items 7, 9, 10, 12, 13 sont absentes de l'indice neuf (mot entier). Une seule bonne reponse est aussi un mot d'indice : voir E1.

## 5. Ecarts et constats

- **E1, a trancher par `nadia-prof`.** La bonne reponse de l'item 24 est `regarde` ; le mot `regarde` ouvre aussi les indices des items 4, 8, 12, 16, 20 (`Le sujet est avant le verbe : regarde les mots devant.`). Ces indices ne sont pas ceux que je corrige, aucun n'est dans l'item 24, et `regarde` y est un verbe d'instruction, pas la reponse. Non touche, hors de la commande. Le garde-fou de la propriete 9 ne le voit pas (il compare un indice aux propositions de son propre item).
- **E2.** L'en-tete du fichier dit encore *"La question est portee par la consigne du jeu quand l'enonce ne la porte pas."* ; chaque enonce porte sa question, la consigne est generique. Non touche : `J1` ne demande qu'une ligne d'en-tete.
- **E3.** Le test ne juge pas `H2` (position seule du sujet) : le sujet est toujours le premier groupe de la phrase, ce que `nadia-prof` nomme et ne bloque pas.
- **E4.** Fins de ligne : l'arbre de travail est en CRLF (`core.autocrlf`), l'index en LF ; mes ecritures ont conserve CRLF dans les fichiers existants et le diff n'en montre aucun changement de fin de ligne.
- **E5.** Pas de contre-exemple commite pour les proprietes 6, 10, 11 et 5 (les mutations M2 et M4 les mordent sur le materiau reel). Les contre-exemples des proprietes 2, 3, 4, 8, 9 et celui de `J5` (six en tout) sont commites avec le test.

## 6. Non verifie

- La justesse des 24 sujets et des 24 verbes, l'homonymie de `porte` et de `souffle`, que les distracteurs de `J2` ne fassent pas l'action, la lisibilite des indices pour un enfant de CE1 : lecture de `nadia-prof`.
- L'ecran d'une reponse juste ou fausse, l'affichage de l'indice, les 23 autres manches, l'affichage a une largeur d'enfant (tablette), le retour a la ligne devant `?` : non joues.
- La CI elle-meme sur ce commit : non lancee, non poussee.
