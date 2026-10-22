# s57 - rapport de lea-dev, palier 4, lot LP3 (`S57-BR-MAT-CE1-11`, `ce1-calendrier-dates`)

- **Auteure** : `lea-dev`, instance B, poste p4-5, 2026-10-22. Branche `s57-lea-mat-ce1-11`, base `51a9d957` (sommet de `round-two` contenant `LP1`, `LP2` et `LP4`). Commit du lot : `8f25fd64`.
- **Ce que ce fichier ne fait pas** : ne prononce aucun Done, ne modifie pas le paquet (K2), ne touche pas la matrice (K9), ne tranche aucune des questions de la section 5.
- **Mesure** : JDK 21.0.10, version que la machine virtuelle des tests rapporte (`java.version` du rapport Surefire). Toute mesure partielle porte `-am`, son perimetre est nomme.
- **Les resultats des jobs de `ci.yml` et du `verify` complet ne sont pas ici** : ce fichier est commite avant la mesure qui le porte. Ils sont dans le compte rendu de fin de lot de `lea-dev` a `sofia-sm`.

## 1. Ce que le lot livre

| Fichier | Contenu |
|---|---|
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | entree `bandit-manchot-calendrier-dates-ce1`, titre "Les dates et le calendrier", consigne "Pense au calendrier, puis choisis la bonne réponse.", `nombreDeManches: 10` |
| `bootstrap/.../TexteAccentueExposeParLePortEntrantTest.java` | quatre chaines ajoutees : id, titre, consigne, libelle de la competence du descripteur (la competence manquait au premier `verify` de `LP2`, elle est dans ce commit) |
| `adapters/adapter-content/.../PaquetCe1CalendrierDatesTest.java` | 50 tests |
| `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1CalendrierDatesTest/` | jeu fautif (29 items) et quatre catalogues fautifs |

- **K2** : `git diff-tree --no-commit-id --name-only -r 8f25fd64` suivi de `grep -vE 'catalogue.yaml|PaquetCe1CalendrierDatesTest|TexteAccentueExposeParLePortEntrantTest.java'` rend 0 ligne (sortie 1 de `grep`). Le YAML du paquet n'est pas modifie : `nadia-prof` ne l'a pas demande.
- **K10** : `grep -E 'AucunMotServiNeSEcarteDeSonAccentTest|LexiqueFrancais|ADR-0065|pom\.xml'` sur la meme liste rend 0 ligne.
- **K3** : `grep -c "paquet: ce1-calendrier-dates$" catalogue.yaml` rendait 0, rend 1.
- **Delta de tests** : **+50** (`PaquetCe1CalendrierDatesTest`, mesure partielle `-pl adapters/adapter-content -am`, 50 tests, 0 echec), base `51a9d957`. Aucun test existant n'est ajoute ni retire. Le total apres fusion est a `sofia-sm`.

## 2. Conditions : gardees, ou "non garde" (K8)

| Condition | Garde | Ce qui mord |
|---|---|---|
| `CAL-CE1-1`, exclusions | chaque item est d'une des quatre formes ; aucun mot d'heure, horloge, cadran, saison ou jour de la semaine dans l'enonce, les propositions, l'indice | jeu fautif : un jour de la semaine, une horloge, un item de l'ordre des mois (aucune forme) |
| `CAL-CE1-1`, ordre ou nom des mois pris pour eux-memes | **non garde au-dela de la forme** : un test lit le debut d'un enonce, pas ce qu'il demande | lecture de `nadia-prof` |
| `CAL-CE1-2` | au moins trois formes servies, un cinquieme des items chacune ; les quatre sont servies a 7, 7, 5, 6 | jeu fautif : deux formes seulement ; une forme a 1 item sur 7 |
| `CAL-CE1-3` | `bissextile` et `29 f` absents ; toute date ecrite est reelle (fevrier : 28), distracteurs compris ; la bonne reponse est recalculee : jours du mois, lendemain ou veille, premiere ou derniere ou milieu de trois dates, ecriture en chiffres et en lettres (1 a 31, "vingt et un", "trente et un") | jeu fautif : fevrier a 29, septembre a 31, bissextile en indice, 31 novembre, lendemain faux, mauvaise date premiere, proposition etrangere a l'enonce, ecriture fausse, notation inversee |
| `CAL-CE1-4`, mots et annee | `vacances`, `noel`, `rentree`, `anniversaire`, `20xx` absents | jeu fautif : `vacances`, `2025` |
| `CAL-CE1-4`, dates de fete | liste de l'en-tete du paquet ; **dette nommee de 2 entrees** (section 3, C2) | jeu fautif : 25 decembre ; dette perimee refusee |
| `CAL-CE1-5`, distracteurs | forme "nombre de jours" : nombre de jours d'un autre mois ; "lendemain ou veille" : date decalee d'un jour ou meme jour a un mois d'ecart ; "ecriture" : le jour ou le mois change, jamais les deux ; **dette nommee de 1 entree** (C1) | jeu fautif : 32 jours, 25 mars, "le sept avril" ; dette perimee refusee |
| `CAL-CE1-5`, indices | pas identiques sur tout le paquet ; douze mots au plus (`TRV-CE1-5`) ; l'indice des formes lendemain, ordre, ecriture ne contient pas la bonne reponse | jeu fautif : trois indices identiques, un indice de 19 mots, un indice qui donne "le 9 mars" |
| `CAL-CE1-5`, trois rangs | 10 % a 50 % par rang sur les **7 items de nombre de jours** : 3 haut, 3 milieu, 1 bas | jeu fautif : trois bonnes reponses au rang haut |
| `TRV-CE1-2` | la bonne reponse est strictement la plus longue sur 7 items sur 25 (50 % au plus) | jeu fautif : deux items ou elle l'est toujours |
| `LON-CE1-1`, `-2`, `TYP-1`, `NB1`, `NB8`, `K3` | comme `LP2` ; `LON-CE1-2` : la derniere phrase est la question quand l'enonce compte plusieurs phrases (regle du garde de `bootstrap`, les consignes en une phrase "Écris en chiffres : ..." n'en sont pas) | jeu fautif pour chacun, quatre catalogues fautifs |

`LON-CE1-3` a `-5` et le garde generique `E3` : non applicables, aucun support ni proposition longue ; `NB8` garde l'absence de support et de voix.

## 3. Constats sur le paquet, non corriges (K2), routes a `nadia-prof`

Le paquet est de s53, jamais audite. Ces mesures sont les miennes, les decisions sont a `nadia-prof`.

- **C1, distracteur d'aucune des trois familles de `CAL-CE1-5`** : "Quelle est la veille du 1er juin ?", distracteur "le 30 juin". Ni la date decalee d'un jour de la bonne reponse (31 mai) ou de la date donnee, ni le meme jour a un mois d'ecart (le 31 juin n'existerait pas). Les 13 autres distracteurs de la forme sont dans une famille. Remplacement possible, a elle de decider : "le 2 juin". Meme motif que la reserve 1 de `LP2`. Garde : dette de 1 entree.
- **C2, dates de la liste de l'en-tete** : l'en-tete du paquet ecarte "1er janvier, 1er avril, 1er mai, 14 juillet, 31 octobre, 25 decembre, fin d'aout". Deux occurrences, toutes deux en distracteur : "le 1er mai" (lendemain du 31 mai) et "le 31 août" (veille du 1er août). Garde : dette de 2 entrees. **Non garde** : "le 1er septembre" (distracteur du lendemain du 30 septembre) n'est pas dans cette liste mais l'en-tete et `CAL-CE1-4` ecartent la rentree : a elle de dire si ca compte.
- **C3, rang de la bonne reponse sur les 25 items** : si le rang est compte sur toutes les propositions ordonnees (nombres de jours, dates dans l'ordre du calendrier), la bonne reponse est au milieu sur **13 items sur 25 (52 %)**, haut 7, bas 5. `CAL-CE1-5` dit "au plus 50 % sur le paquet entier". Sur la forme "nombre de jours" seule, ce que le test garde et ce que `nadia-prof` a verifie a la main en s43, c'est 3, 3, 1. Calcul par un script hors depot sur le fichier du paquet, non par un test. **Je ne sais pas quelle lecture est la sienne.**
- **C4, ligne `TRV-CE1-5`, relue le 2026-10-22, indice par indice** : 25 indices, 13 distincts, onze mots au plus. Quatre indices, ceux de janvier, avril, novembre et decembre ("Ferme le poing : les creux comptent 30 jours, les bosses 31."), **contiennent la proposition "30 jours"** ; pour avril et novembre c'est la bonne reponse. Le meme texte que l'exemple d'indice de `CAL-CE1-5` ("les mois en creux ont 30 jours"), qui nomme la valeur par construction. Points de lecture : "Un seul mois est à part des autres." (fevrier) ne dit pas laquelle des trois valeurs ; "Compte les mois sur tes poings, de janvier à juillet." suppose la regle des poings connue. Aucune regle fausse vue.
- **C5, forme "ordre de trois dates"** : l'enonce ecrit les trois dates, qui sont aussi les trois propositions. La bonne reponse est donc dans l'enonce, comme ses deux distracteurs. Inherent a la forme (`CAL-CE1-2` (iii)), mais c'est le seul cas ou l'enonce affiche la reponse ; l'enonce n'indique pas laquelle.
- **C6, "28 jours"** est un distracteur de 6 items de nombre de jours sur 7 et n'est juste que pour fevrier. Accepte par `CAL-CE1-5` (janvier : 31, distracteurs 30 et 28) ; signale parce que l'enfant qui sait que seul fevrier a 28 jours elimine une proposition sur 6 items.

## 4. Jeu du jar

Jar construit sur `8f25fd64` (`-pl bootstrap -am package -DskipTests`, mesure partielle), port 18531 libre avant (`netstat -ano` filtre sur `":18531 "` : 0 ligne), "Tomcat started on port 18531" et PID 51212 lus dans le journal, arret par PID, port libere (0 ligne LISTENING). Requetes HTTP, pas de navigateur :

- le jeu "Les dates et le calendrier" est dans la vue V2 du niveau CE1, avec sa consigne ;
- 10 parties de 10 manches : **25 enonces sur 25 servis**, chaque enonce connu du paquet, les trois propositions servies egales a celles du paquet, la bonne reponse jamais ecrite dans l'enonce hors forme "ordre de trois dates" (C5) ;
- deux erreurs (indice montre a chacune, "Ce n'est pas ça. Essaie encore !"), revelation "Appuie sur la bonne réponse : le 8 octobre", la bonne reponse ; fin "Tu as trouvé, tout seul : 8 / Tu as joué toutes les questions : 10" ;
- **non fait** : releve d'ecran a 744 x 551 (`ines-design`), pli, police et appareil reels.

## 5. Questions routees

| Code | Question | A qui |
|---|---|---|
| Q1 | Corriger C1 et C2 dans le paquet (K2 : seulement si `nadia-prof` decide), ou les lever | `nadia-prof`, puis `camille-po` pour l'amendement de la story |
| Q2 | Lecture du rang de `CAL-CE1-5` : forme "nombre de jours" seule, ou les 25 items (C3, 52 %) | `nadia-prof` |
| Q3 | Le 1er septembre en distracteur compte-t-il comme rentree (C2) | `nadia-prof` |
| Q4 | Les deux dettes nommees du test (entrees listees qui font echouer le test quand elles ne designent plus rien) : technique deja employee par `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (`DETTE_DE_RANG`), appliquee ici a un test de lot. La garder ou preferer un "non garde" sans test | `sofia-sm`, qui route a `theo-archi` si c'est une regle |
| Q5 | `TRV-CE1-2` et `TRV-CE1-5` sont des conditions du francais CE1 (s48) que j'applique aussi au paquet de mathematiques, comme `LP2` pour `TRV-CE1-5` : confirmer | `nadia-prof` |

Mention de cellule (`camille-po`, le jour du verdict, `K9`) : non touchee.
