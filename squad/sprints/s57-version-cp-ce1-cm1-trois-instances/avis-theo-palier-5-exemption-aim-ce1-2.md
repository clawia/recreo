# s57 - palier 5 : avis de theo-archi sur l'exemption de la garde d'`AIM-CE1-2` et son application a la bonne reponse

- **Auteur** : `theo-archi`. Date : 2026-10-26 09:05.
- **Arbre** : worktree `s57-p5-theo-exemption`, base `f2e45aac` (lue par `git rev-parse`).
- **Repond a** : `reponse-nadia-aim-ce1-2-q1-q2.md` (`Q1` : la negation du tout passe ; `Q2` : *"les métaux"* refuse en bonne reponse), qui complete `avis-theo-palier-5-neuf-lots-qlm-ce1.md` section 1 (`T1`).
- **Mesure** : la forme ci-dessous a ete executee telle qu'ecrite, dans un programme hors depot, sur un JDK 21.0.10 (version rapportee par `java -version`), avec SnakeYAML 2.2. Perimetre et resultats en section 4.
- **Pas d'`ADR`, pas de regle ArchUnit** : l'objet garde est du texte YAML, pas du bytecode, et la decision ne touche aucune dependance entre classes. Ce qui la mord est le test du paquet (section 5), comme pour `T1`. Ce fichier n'ecrit ni code, ni test, ni story, et ne decide rien de pedagogique.

## 1. La forme, complete

Trois motifs, drapeaux `CASE_INSENSITIVE | UNICODE_CASE | UNICODE_CHARACTER_CLASS`. `D` est la designation generale de `T1`, inchangee sauf les espaces, ecrites `\s+` pour lire aussi une espace insecable :

```
D           = (le\s+métal|métaux|métalliques|ce\s+qui\s+est\s+(en\s+métal|métallique)|(les|des)\s+\p{L}+\s+en\s+métal)
ATTRACTION  = (?<!\p{L})attir\p{L}*
DESIGNATION = (?<!\p{L})D(?!\p{L})
NEGATION_DU_TOUT =
      (?<!\p{L})pas\s+(tous|toutes|tout)\s+(les\s+(\p{L}+\s+)?)?D(?!\p{L})
    | (?<!\p{L})D\s+ne\s+sont\s+pas\s+(tous|toutes)(?!\p{L})
```

**L'exemption ne dispense pas une phrase, elle efface un segment.** `neutralise(t)` remplace par une espace chaque occurrence de `NEGATION_DU_TOUT` dans `t`. Le segment efface contient toujours la designation qu'il nie : une designation que la negation du tout ne quantifie pas reste dans le texte et reste jugee.

- **Phrase** (`enonce`, `indice`, `explication`, coupes apres `.`, `?`, `!`, comme en `T1`) : refusee si `ATTRACTION` trouve dans la phrase **et** `DESIGNATION` trouve dans `neutralise(phrase)`.
- **Bonne reponse** (cle `bonneReponse`, `QuestionContentSourceFactory.java:103`) : refusee si `ATTRACTION` trouve dans l'`enonce` de l'item **et** `DESIGNATION` trouve dans `neutralise(bonneReponse)`. La negation de l'enonce ne change rien : *"Qu'est-ce que l'aimant n'attire pas ?"* / *"les métaux"* est aussi fausse, et refusee.
- **Distracteurs** : non lus (`nadia-prof`, `Q2`).

Les deux branches de `NEGATION_DU_TOUT` sont les deux sondes vertes de `nadia-prof` : la forme active (*n'attire pas tous les métaux*, *pas tout ce qui est en métal*) et la forme ou la designation est sujet (*les objets en métal ne sont pas tous attirés*). Rien d'autre n'est exempte : ni *toujours*, ni *aucun*, ni *pas* seul.

## 2. Les douze sondes de `nadia-prof`, executees

| # | Entree | Attendu | Obtenu |
|---|---|---|---|
| 1 | *L'aimant attire les métaux.* | refusee | refusee |
| 2 | *Un aimant attire tout ce qui est en métal.* | refusee | refusee |
| 3 | *Les objets en métal sont attirés par l'aimant.* | refusee | refusee |
| 4 | *L'aimant attire-t-il le papier d'aluminium ?* | passe | passe |
| 5 | *Le clou est en fer. L'aimant l'attire-t-il ?* | passe | passe |
| 6 | *Quel objet l'aimant attire-t-il ?* | passe | passe |
| 7 | *Ce trombone est en métal : l'aimant l'attire-t-il ?* | passe | passe |
| 8 | *L'aimant n'attire pas tous les métaux.* | passe | passe |
| 9 | *Les objets en métal ne sont pas tous attirés par l'aimant.* | passe | passe |
| 10 | *L'aimant n'attire pas les métaux.* | refusee | refusee |
| 11 | enonce *Qu'attire un aimant ?*, bonne reponse *les métaux* | refusee | refusee |
| 12 | enonce *Lequel de ces objets l'aimant attire-t-il ?*, bonne reponse *le trombone* | passe | passe |

## 3. Ce que chaque sonde mord : cinq mutants executes

Chaque mutant est la forme de la section 1 avec une seule piece changee, execute sur les memes sondes.

| Mutant | Ce qu'il fait | Mordu par |
|---|---|---|
| `M1` exemption de toute negation | quantifieur `tous/toutes/tout` rendu facultatif | sonde 10 |
| `M2` exemption de la phrase entiere | toute phrase qui porte *pas tous* est dispensee | **aucune sonde de `nadia-prof`** ; sondes `T-a`, `T-b`, `T-c` ci-dessous |
| `M3` bonne reponse non lue | | sonde 11 |
| `M4` aucune exemption | forme de `T1` | sondes 8 et 9 |
| `M5` sans la forme ou la designation est sujet | | sonde 9 |

`M2` est le raccourci le plus probable d'un implementeur, et les douze sondes le laissent passer. Trois sondes de ma main le mordent ; elles entrent dans le test a cote des douze, marquees comme telles :

| # | Entree | Attendu |
|---|---|---|
| `T-a` | *L'aimant n'attire pas tous les objets en plastique, mais il attire les métaux.* | refusee |
| `T-b` | *L'aimant n'attire pas tous les métaux, mais il attire les métaux.* | refusee |
| `T-c` | *L'aimant n'attire pas tous les jours les métaux.* | refusee |

Ce sont des fixtures d'auteur de regle. Elles ne disent rien de ce qu'un developpeur ecrira : elles fixent seulement que l'exemption porte sur un segment.

## 4. Sur le materiau reel

Perimetre : les 187 fichiers `.yaml` de `adapters/adapter-content/src/main/resources/contenu` a `f2e45aac`, tout item d'une liste `items`, champs `enonce`, `indice`, `explication` (6444 champs lus) et paire `enonce` / `bonneReponse`. Exclusions : aucun fichier ; les items d'un paquet dont les champs portent d'autres noms ne sont pas lus. Resultat : **2 champs portent une forme d'*attirer*, 0 refus**. La garde ne refuse donc rien d'ecrit aujourd'hui, dans aucun paquet ; elle ne s'appliquera qu'a `ce1-qlm-aimant`, qui n'existe pas.

## 5. Qui ecrit quoi

| Qui | Quoi | Quand |
|---|---|---|
| developpeur de `S57-BR-QLM-CE1-06` | la garde de la section 1 dans `PaquetCe1QlmAimantTest` (`adapters/adapter-content/src/test/java/com/recreo/adapter/content/`), appliquee aux champs reels du paquet, et les quinze sondes (douze de `nadia-prof`, trois `T-*`) en litteraux accentues, sur le modele de `PaquetCe1EmcInteretCommunTest` | commit du premier jet, avec le YAML |
| `camille-po` | critere 2 de `S57-BR-QLM-CE1-06` (`docs/backlog/backlog.md:24158-24162`) : y reporter les sondes 8 a 12, la bonne reponse dans le perimetre, motif `Q2` = exactitude et `AIM-CE1-1`, pas une condition neuve (`reponse-nadia-aim-ce1-2-q1-q2.md:76-77`) ; remplacer *"A TRANCHER PAR `theo-archi`"* (ligne 24162) par un renvoi a ce fichier | avant engagement de `-06` |
| `nadia-prof` | les cas que la garde ne voit pas : paraphrases sans *attirer*, metaux nommes presentes comme attires, designation hors des quatre formes, negation fausse sans designation (*n'attire aucun métal*), distracteurs, et une phrase fautive reprise par un pronom (*il les attire tous*) | lecture avant `verify` (`QC9`) |
| `theo-archi` | ce fichier ; la forme si une sonde rougit pour une raison que la forme ne prevoit pas | sur demande |

## 6. Ce qui reste ouvert

| Code | Question | A qui |
|---|---|---|
| `Q7` | `QuestionContentSourceFactory` lit aussi `texteAVoixHaute` et `texteSupport` (`QuestionContentSourceFactory.java:112-115`). La condition d'`AIM-CE1-2` ne les nomme pas, donc la garde ne les lit pas. Un texte que l'adulte lit a voix haute doit-il entrer dans son perimetre ? | `nadia-prof` |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
