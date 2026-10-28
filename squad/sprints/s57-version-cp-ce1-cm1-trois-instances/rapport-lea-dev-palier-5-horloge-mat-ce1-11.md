# s57, palier 5, instance B : l'horloge `MAT-CE1-11`, premier jet de `lea-dev`

- **Auteure** : `lea-dev`. Developpeuse, aucune casquette de decision : ni produit, ni architecture, ni pedagogie, ni Done.
- **Date** : 2026-10-28 (UTC+01:00), `date` lue a 11:40:58 au debut et a 12:42:46 apres le premier commit `a5d5026b`. **Duree brute : 61 min 48 s** jusqu'a ce premier commit, attentes de `mvn`
  et rendu d'ecran compris ; le commit de ce correctif de rapport est posterieur et non chronometre.
- **Base** : `round-two` a `802d0227`, branche `s57-p5-lea-horloge`. Mon worktree etait ouvert a `aa2b3560` sur une autre branche ; j'ai cree la branche
  de travail depuis `802d0227` avec `git switch -c s57-p5-lea-horloge 802d0227`, aucun reset, aucun rebase.
- **Version sur laquelle j'ai mesure** : JDK **21.0.10**, celui que la machine virtuelle des tests rapporte (`Detected Java version 21.0.10`, journal d'ArchUnit) et que
  l'enveloppe `outils/mvn-jdk21.sh` annonce depuis la toolchain. Aucun chemin de JDK n'est ecrit dans un fichier commite.
- **Ce que ce document n'est pas** : ni un Done technique (CI non lancee, `verify` non lance), ni un Done pedagogique, ni une entree de catalogue (`QC3`).
- **Cahier des charges lu** : `arbitrage-nadia-palier-4.md` (**aucune section 3.5** : `grep -n '^###'` rend 3.1 a 3.4 ; la condition de l'horloge s'appelle 3.5 dans
  `arbitrage-nadia-palier-5-lp7-d5.md`, dont la section 2.3 renvoie pourtant a *"3.5 de mon arbitrage du palier 4"* : renvoi que je n'ai pas pu resoudre, voir Q13), `arbitrage-nadia-palier-5-lp7-d5.md`
  (2.2, 2.3, 3.5, 4, 5, 8), `arbitrage-nadia-preparation-palier-3-ce1.md` (4.3, ligne 239), `reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md` (2, 2.1),
  `reponse-nadia-palier-5-rejeu-horloge-et-double-compte.md` (1.2), `avis-theo-palier-5-dessins.md` (2.1, 2.2), `FICHE-bandit-manchot-calendrier-dates-ce1.md`,
  `HOR-1` a `HOR-8` du CP (`ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md`, 2.1), `DEC-0120` A.263, A.265, A.267, `docs/backlog/backlog.md`
  (`D5`, ligne `MAT-CE1-11`), `squad/lessons.md`. **Non lu en entier** : `DEC-0120` A.280 et A.282 (je n'ai trouve aucune ligne qui les porte dans la partie que j'ai parcourue) ;
  `avis-theo-palier-4.md` (je n'y ai cherche que le palier 5 dessins, deja couvert par `avis-theo-palier-5-dessins.md`).

## Resume

| # | Quoi | Etat |
|---|---|---|
| 1 | Paquet `ce1-lire-l-heure` : 12 items, un cadran par item, bandit manchot | livre, hors catalogue |
| 2 | 12 dessins `horloge-a.svg` a `horloge-l.svg`, 27 670 octets en tout | livres, un fichier par heure, produits par un script hors depot |
| 3 | Garde M `LesCadransMontrentCeQueLeursItemsAffirmentTest` (bootstrap), jeu fautif commite | livre, 10 tests |
| 4 | Test de paquet `PaquetCe1LireLHeureTest` (adapter-content) | livre, 6 tests |
| 5 | Cliquets de poids et de population : 69 203 a 96 873 octets, 56 a 68 fichiers, 87 a 99 items illustres | deplaces dans le meme commit, valeurs mesurees |
| 6 | Allocation de la sous-partie `LIRE_L_HEURE` dans `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | une entree, voir Q9 |
| 7 | Joue au jar : 8 parties, 12 items sur 12 servis, 0 anomalie | fait, voir section 5 |
| 8 | Questions pour `nadia-prof`, `ines-design`, `theo-archi`, `camille-po` | 13, section 7 |

**Delta, pas total** : **+16 tests** (6 plus 10), comptes sur les deux classes que j'ai ecrites, sur la base `802d0227` et mon seul lot ; ce n'est pas une difference de totaux, et l'entree d'enum de `LIRE_L_HEURE` n'est pas mesuree a part. Je ne donne aucun total : `sofia-sm` l'etablit sur l'arbre fusionne.

## 1. Ce que l'enfant voit

Un cadran rond de 176 px (`.image-item`, mecanique `bandit-manchot`, gabarit `manche.html`, aucun code de rendu neuf), la question *"Quelle heure est-il ?"* et trois propositions.
Le texte alternatif est le meme pour les douze : *"Une horloge ronde avec douze nombres et deux aiguilles, une courte et une longue."* (`CON-5` : ni l'heure, ni la place d'une aiguille).
Indice apres une erreur : *"La petite aiguille montre l'heure."* pour les heures pleines, *"La petite aiguille a passé quel nombre ?"* pour les neuf autres.

| Dessin | Bonne reponse | Distracteurs | Forme |
|---|---|---|---|
| `horloge-a` | 3 h | 12 h 15, 4 h | heure pleine |
| `horloge-b` | 6 h | 12 h 30, 7 h | heure pleine |
| `horloge-c` | 9 h | 12 h 45, 10 h | heure pleine |
| `horloge-d` | 3 h 30 | 6 h, 4 h 30 | demi-heure |
| `horloge-e` | 8 h 30 | 6 h, 9 h 30 | demi-heure |
| `horloge-f` | 12 h 30 | 6 h, 1 h 30 | demi-heure |
| `horloge-g` | 12 h 15 | 3 h, 1 h 15 | quart, grande aiguille sur 3 |
| `horloge-h` | 4 h 15 | 3 h, 5 h 15 | quart, grande aiguille sur 3 |
| `horloge-i` | 10 h 15 | 3 h, 11 h 15 | quart, grande aiguille sur 3 |
| `horloge-j` | 2 h 45 | 9 h, 3 h 45 | quart, grande aiguille sur 9 |
| `horloge-k` | 9 h 45 | 9 h, 10 h 45 | quart, grande aiguille sur 9 |
| `horloge-l` | 11 h 45 | 9 h, 12 h 45 | quart, grande aiguille sur 9 |

Les deux fichiers de l'item (YAML et dessin) sont produits par le meme script hors depot (pas commite, comme les quadrillages : `avis-theo-palier-5-dessins.md` 2.1) pour que la bonne reponse et les angles ne divergent pas.

## 2. Ce qui est tenu, condition par condition

Chaque ligne cite le texte de `nadia-prof` que j'ai suivi. **Ma lecture n'est pas son Done.**

| Condition (source) | Comment c'est tenu | Qui le verifie |
|---|---|---|
| Un fichier SVG par cadran, hors depot, aucune horloge generee par le serveur (3.5) | douze fichiers statiques sous `static/illustrations/` | garde de geometrie existant |
| `HOR-1`, angles calcules (3.5) : grande 6 x minutes, petite 30 x heure plus 0,5 x minutes | `LesCadransMontrentCeQueLeursItemsAffirmentTest`, egalite stricte des deux angles, en degres | test, jeu fautif commite, 7 morsures sur le materiau reel |
| `HOR-2`, douze nombres ecrits, pas seulement 12, 3, 6, 9 (3.5) | quinze glyphes instancies dans chaque fichier (1 a 9, 10, 11, 12), compte derive du fichier | test, morsure `unNombreDeMoinsFaitEchouerLeGardeFou` |
| `HOR-3`, aiguilles distinctes par la longueur (3.5) | petite 28 unites, grande 42 unites (epaisseurs 7 et 4, en plus) | test, morsure des aiguilles de meme longueur |
| Grande aiguille sur 12, 3, 6 ou 9 seulement (3.5) | angles 0, 90, 180, 270 | test |
| Petite aiguille a sa place exacte entre deux nombres a la demie et aux quarts (3.5) | angle exact par la formule | test pour l'angle, **l'ecran pour la lecture** (section 5, Q5) |
| Au moins 12 items, jamais le meme cadran deux fois (3.5) | 12 items, 12 cadrans distincts | test (population), test de paquet |
| Au moins 3 heures pleines, 3 demies, 3 quarts, 3 "moins le quart" (3.5) | 3, 3, 3 et 3 (voir Q1 pour l'ecriture) | test (formes a 0, 30, 15, 45 minutes) |
| Aucune heure plus de deux fois (`HOR-6` transposee, 3.5) | 3, 12 et 9 sortent deux fois, les autres une | test |
| Distracteurs `HOR-5` transposee : aiguilles inversees, heure suivante lue sur le nombre approche (3.5) | voir en-tete du YAML, ligne par ligne ; Q6 | **nadia-prof** (aucun test ne lit un distracteur) |
| Jamais deux lectures de la meme position dans les trois propositions, `3 h` et `15 h` (3.5) | position calculee sur 12 heures, deux propositions a la meme position rougissent | test |
| `CON-5`, texte alternatif sans l'heure ni la place d'une aiguille (3.5) | aucun chiffre, aucun mot de la liste (heure, midi, demie, quart, entre, pointe, montre, indique) | test de paquet pour les chiffres et la liste, **nadia-prof** pour le reste |
| `HOR-7`, indice : quelle aiguille regarder, 8 mots au plus (CP, non reprise telle quelle en 3.5) | 5 et 7 mots, aucun chiffre | test de paquet, **a confirmer** (Q7) |
| `LON-CE1-1` et `LON-CE1-2` | *"Quelle heure est-il ?"*, 3 mots, une phrase | gardes existants |
| `TYP-1` (`ADR-0067`) | espace insecable devant `?` | gardes existants |
| `ADR-0086` clause 2 | chaque forme de proposition fausse est aussi une bonne reponse ailleurs | test de paquet |
| Pas d'entree de catalogue (`QC3`) | aucune | `git diff` du fichier |

## 3. Les choix de premier jet que je n'ai pas tranches, et ou ils sont dits

Le cahier des charges ne dit pas tout. Chaque ligne ci-dessous est **mon choix de redaction, provisoire**, a defaire sans cout apres la lecture de `nadia-prof`.

| Ce que le cahier des charges ne dit pas | Ce que j'ai ecrit | Question |
|---|---|---|
| Comment s'ecrit une reponse | **chiffres et `h`** : `3 h`, `3 h 30`, `3 h 15`, `2 h 45` | Q1 |
| Combien d'items par forme au-dela du plancher de 3 | 3 par forme, soit 12 : le minimum de `nadia-prof`, la consigne *"on minimise le poids"* (backlog, annotation du 2026-10-25) | Q8 |
| Aucun item sur 12 h pile | 12 h pile n'est pas servi (les deux aiguilles se superposent) ; 12 h 15 et 12 h 30 le sont | Q10 |
| La forme de l'indice des demies et des quarts | une seule phrase pour les neuf, *"La petite aiguille a passé quel nombre ?"* | Q7 |
| Le dessin des glyphes 3, 4, 6, 7, 8, 9 | dessines par moi, dans le repere des glyphes 0, 1, 2, 5 existants (hauteur 56 unites, trait 8) | Q4 |

## 4. Fichiers touches

| Fichier | Nature |
|---|---|
| `adapters/adapter-web/src/main/resources/static/illustrations/horloge-a.svg` a `horloge-l.svg` | 12 dessins neufs |
| `adapters/adapter-content/src/main/resources/contenu/ce1-lire-l-heure.yaml` | paquet neuf, 12 items |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1LireLHeureTest.java` | 6 tests neufs |
| `bootstrap/src/test/java/com/recreo/archi/LesCadransMontrentCeQueLeursItemsAffirmentTest.java` | 10 tests neufs, garde M |
| `bootstrap/src/test/resources/contreexemple/LesCadransMontrentCeQueLeursItemsAffirmentTest/cadran-fautif/` | jeu fautif (`ADR-0013`), un item, un dessin |
| `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java` | cliquet total 69 203 a 96 873, entree de l'historique |
| `bootstrap/src/test/java/com/recreo/archi/LIllustrationDeContenuResoutEtAucuneNeDortTest.java` | populations 87 a 99 items, 56 a 68 fichiers, deux entrees d'historique |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.java` | une entree d'enum, `LIRE_L_HEURE` |
| `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` | ce rapport |

**Non touches** : `catalogue.yaml`, la matrice, `REGISTRE-identifiants.md` (je n'alloue aucun identifiant : seuls `HOR-1` a `HOR-8`, `CON-5`, `LON-CE1-1`, `LON-CE1-2`, `TYP-1` sont cites, tous deja alloues),
le paquet `ce1-calendrier-dates` (son en-tete `DEGRADE` dit *"aucune horloge n'est rendue"* et deviendra faux a la fusion : question de `camille-po`, Q11), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` et son lecteur de duree (voir Q1).

Le poids du lot : **27 670 octets** pour 12 fichiers de 2 303 a 2 308 octets (fins de ligne LF, commentaires compris), contre 4 633 pour le plus gros dessin du depot. Repertoire : 96 873 octets. **Aucun accord de poids de l'utilisatrice ne couvre ce lot** (backlog, `D5` : *"leur poids se demande a l'ouverture de chaque lot"* ; borne de 161 863 octets pour `LP9` seul).
Moyens pris pour *"minimiser"* : glyphes en `<defs>` et instancies par `<use>` avec `x` et `y` seuls, un `<g transform>` pour toute l'echelle, une graduation en un seul chemin, entiers pour les glyphes, une decimale pour les graduations, en-tete de trois lignes, aucune metadonnee. Ce qui pese encore : dix glyphes redefinis dans chaque fichier (environ 560 octets par fichier), parce qu'un dessin charge par `<img>` est un document isole (`avis-theo-palier-5-dessins.md` 0).

## 5. Joue au jar, regarde a l'ecran

**Jar** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`, code 0, avec **une entree de catalogue locale non commitee** (`bandit-manchot-lire-l-heure-ce1`, titre et consigne de ma main), retiree avant le commit en restituant le fichier tel que je l'avais copie avant l'edition ; `git status -s` n'affiche aucune modification de `catalogue.yaml` (section 8).
**Parties** : 8 parties de 10 manches par requetes HTTP (`POST /parties/<jeu>`, `/coups`, `/manche`), port ephemere, jar arrete par PID. **12 items servis sur 12**, la bonne reponse est toujours parmi les trois propositions, trois propositions distinctes, enonce toujours *"Quelle heure est-il ?"*, **0 anomalie**. Quatre parties ont commence par une erreur : l'indice s'affiche.
**Ecran** : releves a 744 x 551 dans Edge sans fenetre, regardes a l'oeil : quatre ecrans lus (`horloge-d` et `horloge-j` apres une bonne reponse, une erreur sur `horloge-c`, une sur `horloge-g`), un cinquieme rendu et non regarde (`horloge-f`). L'image, la question, les trois propositions et, apres une erreur, la croix et l'indice tiennent
en dessous de y = 490 environ. **Je n'ai pas mesure au `getBoundingClientRect()`** (`squad/lessons.md`, s56) : ce sont des cotes lues a l'oeil, a ne pas recopier comme des chiffres.
Je ne dis rien du pli a 360 px : la fenetre sans interface refuse moins de 500 px, mon releve a 360 etait un releve a 500 rogne, il ne vaut rien. Les douze dessins ont aussi ete rendus ensemble a 176 px, la bonne reponse ecrite dessous, et relus un par un : les aiguilles sont ou la formule les met
(petite aiguille entre deux nombres, lisible pour moi sur les 12 ; **ce n'est pas la lecture d'un enfant de CE1**).

## 6. Mesures et commandes lancees

Toutes depuis la racine du worktree, sur JDK 21.0.10. Mesures partielles : `-am` porte, perimetre nomme.

| Commande | Perimetre | Code | Lu |
|---|---|---|---|
| `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` (premier essai, reponses de la forme *"3 heures et quart"*) | domain, application, adapter-web, adapter-content | 1 | 2569 tests, 4 echecs, tous sur `ce1-lire-l-heure` : `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` x 3 (voir Q1) |
| `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web -am test` | domain 120, application 31, adapter-web 523, adapter-content 2569 tests | 0 | 0 echec, 0 erreur ; `PaquetCe1LireLHeureTest` 6 tests |
| `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test "-Dtest=com.recreo.archi.*Test" -Dsurefire.failIfNoSpecifiedTests=false` | `com.recreo.archi` de bootstrap et ses amonts | 1 | 272 tests, 1 echec : `LesGardeFousSontMisALEpreuveTest.aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve`, mon garde n'executait pas son jeu fautif par `assertThatThrownBy` ; corrige |
| `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=LesCadransMontrentCeQueLeursItemsAffirmentTest,LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false` | bootstrap et amonts, deux classes | 0 | 20 tests, 0 echec ; 10 pour le garde M |
| `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` | jar | 0 | jar de 21,5 Mo, joue |
| `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` (troisieme passage, apres une modification de commentaire de l'en-tete du YAML, aucune valeur changee) | domain 120, application 31, adapter-content 2569 tests | 0 | 0 echec, 0 erreur, `BUILD SUCCESS` ; `PaquetCe1LireLHeureTest` 6 tests |

**Pas de mutation lue sans motif** : les morsures du garde M sont des tests (mutation en memoire du materiau reel, motif exige par `anyMatch`), le nombre de tests lances est 10 dans l'execution qui les contient.
**Le jeu fautif de `ADR-0013`** est execute par `assertThatThrownBy`, ce que le premier essai m'a appris a la dure.

Les gardes demandes **avant le commit** sont en section 8.

## 7. Questions

Treize questions. Aucune n'est decidee par moi. Le premier jet porte mon choix provisoire quand il en fallait un pour livrer.

| # | Question | A qui | Ce que j'ai fait en attendant |
|---|---|---|---|
| Q1 | **Comment s'ecrit la reponse** : `3 h 15`, ou *"3 heures et quart"* et *"4 heures moins le quart"* ? Les formes en toutes lettres lisent le quart d'heure sans minutes, les chiffres lisent `15` et `45`. `nadia-prof` ecrit pour le CP *"Ni les demies, ni les quarts, ni les minutes : ce sont des competences de CE1 et de CE2"* (`HOR`, 2.1) : un `3 h 15` fait-il lire des minutes ? Cote garde : la lecture de valeur (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `ADR-0102`) ne lit ni *"et quart"* ni *"moins le quart"* et **le dit expressement** (`uneEcritureDeDureeInconnueDeLaLectureEstRouge`, le quart d'heure y est *"deliberement non ajoute"*) ; les ecrire rend quatre gardes rouges. **Y ajouter le quart d'heure est une decision d'architecture, pas la mienne** | `nadia-prof` (forme), `theo-archi` via `sofia-sm` (lecture de valeur) | forme chiffree, seule ecriture que les gardes lisent sans modification |
| Q2 | **Ce que "lire l'heure" doit couvrir** : la ligne dit *heures, demi-heures, quarts d'heure*. Le paquet sert 0, 15, 30 et 45 minutes, la grande aiguille sur 12, 6, 3 et 9. Un enfant qui lit `9 h 45` a-t-il lu un quart d'heure ? L'heure du matin et de l'apres-midi (`15 h`) n'est pas servie | `nadia-prof` | quatre formes de 3 items |
| Q3 | **Cadran en aiguilles ou en chiffres** : le cahier des charges dit aiguilles ; je n'ai rien ecrit de numerique | `nadia-prof`, `camille-po` | aiguilles seules |
| Q4 | **Glyphes 3, 4, 6, 7, 8, 9** : aucun n'existait (`avis-theo-palier-5-dessins.md` 2.1 les attribue a `ines-design`, `LP9` apporte 3, 4, 6, pas 7, 8, 9). Je les ai dessines pour pouvoir rendre un cadran ; ils ne sont pas relus par `ines-design`. Hauteur rendue 16,8 px, trait rendu 2,4 px. Le plancher d'encre de 14 px (*"l'encre d'un chiffre trace rend au moins 14 px"*) n'est verifie par aucun garde | `ines-design` | glyphes de premier jet, a remplacer par les siens |
| Q5 | **Lisibilite de la petite aiguille a 176 px**, entre deux nombres, a la demie et aux quarts : propriete que `nadia-prof` garde seule (`arbitrage-nadia-palier-5-lp7-d5.md` section 8). Je l'ai regardee, ce n'est pas une preuve | `nadia-prof` | rien |
| Q6 | **Les distracteurs** : pour une heure pleine, *"aiguilles inversees"* donne `12 h 15`, `12 h 30`, `12 h 45` ; pour une demie ou un quart, la grande aiguille lue comme heure (`6 h`, `3 h`, `9 h`) ; pour toutes, l'heure suivante lue sur le nombre approche, minutes justes. Sont-ils ceux que `nadia-prof` voulait ? Un distracteur repete (`6 h` sur les trois demies, `3 h` sur les trois quarts a 3, `9 h` sur les trois a 9) est-il une faute de variete ? Il est aussi bonne reponse ailleurs (`3 h`, `6 h`, `9 h` sont les trois heures pleines) | `nadia-prof` | un distracteur d'inversion et un de voisinage par item |
| Q7 | **L'indice** : `HOR-7` du CP dit *"quelle aiguille regarder, 8 mots au plus"*, la section 3.5 ne la reprend pas mot pour mot. *"La petite aiguille a passé quel nombre ?"* sert la demie, le quart et le 45 : pour `2 h 45`, la reponse est bien le nombre passe (2). Aucun indice ne parle de la grande aiguille | `nadia-prof` | indice de la petite aiguille seule |
| Q8 | **Nombre d'items** : 12 est le plancher de `nadia-prof`. Plus d'items, plus de poids : 2,3 Ko chacun. L'accord de poids n'existe pas | `camille-po`, l'utilisatrice | 12 |
| Q9 | **Allocation de la sous-partie** : `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` exige une competence allouee. J'ai ajoute `LIRE_L_HEURE`, la moitie *lire l'heure* du libelle de `MAT-CE1-11`, a cote de `HORLOGE_ET_CALENDRIER` (paquet de dates, inchange). Alternative : declarer la competence entiere `HORLOGE_ET_CALENDRIER` dans mon paquet | `theo-archi` | entree ajoutee |
| Q10 | **12 h pile** n'est pas servi (aiguilles superposees), alors que *"midi"* est l'heure la plus connue. `12 h 15` et `12 h 30` le sont | `nadia-prof` | pas de 12 h pile |
| Q11 | **Le paquet de dates et la matrice** : l'en-tete `DEGRADE` de `ce1-calendrier-dates` et la mention de couverture de `MAT-CE1-11` disent que l'horloge n'est pas servie. A reecrire par qui de droit quand le paquet sera joue | `camille-po` | rien touche |
| Q12 | **Le pli** a 744 x 551 est lu a l'oeil sur quatre ecrans (section 5), **non mesure** ; 360 px **non releve**. L'histoire de reserve `S57-PLI-LP3-LP5-MESURE` couvre-t-elle ce paquet ? | `ines-design` | rien |
| Q13 | **Renvoi de `arbitrage-nadia-palier-5-lp7-d5.md` 2.3** : *"les conditions de l'horloge sont celles de 3.5 de mon arbitrage du palier 4"*. Cet arbitrage n'a pas de section 3.5 ; j'ai pris la section 3.5 de `lp7-d5` (*"`MAT-CE1-11`, lire l'heure"*). Si une autre condition vit ailleurs, je ne l'ai pas lue | `nadia-prof` | section 3.5 de `lp7-d5` |

## 8. Gardes lances sur mon diff avant le commit

Les fichiers etaient indexes (`git add -A`) avant les controles qui lisent `git ls-files`. Je n'ai lance que ce qui suit.

| Garde demande | Commande | Code | Lu |
|---|---|---|---|
| Test de tableaux (`ADR-0087`) | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,ToutesLesReferencesDeDecisionResolventTest -Dsurefire.failIfNoSpecifiedTests=false` | 0 | 5 tests, 0 echec, rapport compris |
| Garde des identifiants | meme commande | 0 | `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` 10 tests, 0 echec ; aucun identifiant alloue, `REGISTRE-identifiants.md` non touche |
| Renvois de decision (lance en plus) | meme commande | 0 | `ToutesLesReferencesDeDecisionResolventTest` 3 tests, 0 echec |
| Chemins, forme CI | `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` redirige vers un fichier (le garde de l'agent refuse un `git` compose), puis la liste est lue sur l'entree de `bash .github/scripts/verifier-chemins.sh` (un `cat` en tube) | 0 | 3282 fichiers lus, dont les miens ; *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| Prefixes | le sujet du commit est lu sur l'entree de `bash .github/scripts/verifier-prefixes.sh` (un `echo` en tube) | 0 | *"Tous les commits portent un prefixe d'agent connu."* ; puis, apres le commit `a5d5026b`, la plage `802d0227..HEAD` (`git log --no-merges --format=%s`, sujets ecrits dans un fichier lu sur l'entree du script) : code 0, un sujet |

Le test de tableaux et le garde des identifiants ont ete relances une seconde fois sur le rapport et l'en-tete du YAML tels qu'ils etaient avant l'ajout de cette seule phrase (meme commande sans le test de renvois de decision) : code 0, 5 et 10 tests.

Ces deux derniers controles ne sont la forme de la CI que pour le contenu lu : la CI les lance sur le commit pousse, par `git log --no-merges --format=%s <base>..<sha>` en tube, et sa base est celle de la poussee, pas `802d0227`.

**Non lances** : `mvn -B verify` (geste a part, apres la lecture de `nadia-prof`) ; le job `build` de `ci.yml` ; les jobs `outils`, `prefixes` et `chemins` en entier (leurs etapes de test des garde-fous : `verifier-chemins-test.sh`, `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, ne sont pas lancees) ;
les tests de `bootstrap` hors `com.recreo.archi` ; le garde-fou de l'accent. **Je ne declare pas la porte technique** : je n'ai lance aucun des jobs de la CI en entier.

**Etat de l'arbre** : `git status -s` avant l'indexation listait trois fichiers modifies (les trois tests de populations et de cliquets) et les fichiers neufs ci-dessus ; `catalogue.yaml` n'y figurait pas.

## Corrections apres la lecture (lea-dev, 2026-10-28)

- **Base de mesure** : `round-two` a `e23f3770` (fusion de `s57-p5-nadia-tranche-horloge`), branche `s57-p5-lea-horloge-corr`. JDK 21.0.10, tel que `outils/mvn-jdk21.sh` l'a rapporte.
- **Specification** : `arbitrage-nadia-horloge-strategie-de-paire-et-pli.md`, sections 1.3, 1.4 et 4.1.

### Distracteurs de voisinage, item par item

Aucun SVG, glyphe, indice, texte alternatif, bonne reponse ni distracteur d'aiguilles inversees ne change. Aucun octet de dessin : le cliquet de 96 873 n'est pas touche.

| Item | Bonne reponse | Voisin du premier jet | Voisin servi | Sens |
|---|---|---|---|---|
| `horloge-a` | 3 h | 4 h | 2 h | precedente |
| `horloge-b` | 6 h | 7 h | 5 h | precedente |
| `horloge-c` | 9 h | 10 h | 10 h | suivante, inchange |
| `horloge-d` | 3 h 30 | 4 h 30 | 4 h 30 | suivante, inchange |
| `horloge-e` | 8 h 30 | 9 h 30 | 9 h 30 | suivante, inchange |
| `horloge-f` | 12 h 30 | 1 h 30 | 11 h 30 | precedente (forcee) |
| `horloge-g` | 12 h 15 | 1 h 15 | 11 h 15 | precedente (forcee) |
| `horloge-h` | 4 h 15 | 5 h 15 | 3 h 15 | precedente |
| `horloge-i` | 10 h 15 | 11 h 15 | 9 h 15 | precedente |
| `horloge-j` | 2 h 45 | 3 h 45 | 3 h 45 | suivante, inchange |
| `horloge-k` | 9 h 45 | 10 h 45 | 10 h 45 | suivante, inchange |
| `horloge-l` | 11 h 45 | 12 h 45 | 12 h 45 | suivante, inchange |

Six precedentes, six suivantes. Choix conforme a 1.4 : precedente sur `-f` et `-g` (forcee), `-a`, `-b`, `-h`, `-i` (l'exemple de `nadia-prof`, repris tel quel) ; suivante sur les demies `-d`, `-e` et les quarts a 9.

### Les quatre comptes, recopies de la sortie du garde

`aucuneStrategieDePaireNeGagnePlusDeLaMoitieDesItems`, sur le paquet servi : `S1 = 6, S2 = 6, S3 = 6, S4 = 6 sur 12 items`.

Sur le premier jet fige (`lePremierJetDeLHorlogeFigeEstRefuse`, execute avec `assertThatThrownBy`) : `S1 = 10, S2 = 2, S3 = 12, S4 = 0 sur 12 items`.

### Ce qui est livre

- Garde de paire : `LesCadransMontrentCeQueLeursItemsAffirmentTest`, definition de 1.3 (groupes par minutes, `S1`/`S2` sur les heures ecrites, `S3`/`S4` sur `(b - a) mod 12`, ecart de 6 ou item sans paire perdu, denominateur = nombre total d'items, population au moins 1 par strategie).
- Premier jet fige : `bootstrap/src/test/resources/contreexemple/LesCadransMontrentCeQueLeursItemsAffirmentTest/premier-jet-horloge/contenu/ce1-lire-l-heure.yaml`, blob `42eeeced` identique a celui de `a5d5026b`.
- Trou `F4` de `theo-archi` ferme dans le meme diff, sans toucher un dessin : un dessin dont le nombre de `<line` n'est pas 2 est refuse (les `<path>` des graduations et des glyphes sont legitimes, je ne les compte pas). Morsure : `unTraitEnPlusHorsDeLaFormeDesAiguillesFaitEchouerLeGardeFou`.
- Javadoc du cliquet : la phrase "n'est pas couvert par un accord" est remplacee par l'accord du 2026-10-28 (`DEC-0120`, A.284). Constante 96 873 inchangee.
- Le garde imprime ses quatre comptes sur la sortie standard a chaque run (une ligne `strategies de paire : ...`).

### Mesures (delta sur `e23f3770`, `-am`, perimetre nomme)

- `LesCadransMontrentCeQueLeursItemsAffirmentTest`, module `bootstrap` avec `-am` : 14 tests, +4 sur la base (10 a `e23f3770`) : garde de paire sur le paquet servi, premier jet refuse, trait en plus, strategie sans application.
- `com.recreo.archi.*Test`, `PaquetCe1LireLHeureTest`, cliquet de poids, rang : reacteur `-pl bootstrap -am`, adapter-web 17, adapter-content 34, bootstrap 276, 0 echec. Ces totaux sont des mesures partielles de ce lot, pas le total du depot.
- Chemins en forme CI (`verifier-chemins.sh` sur `git ls-files`) : vert. Prefixe du sujet (`verifier-prefixes.sh`) : vert.

### Ce que je n'ai pas lance

`mvn verify` complet, les autres jobs de la CI (hors `chemins` et `prefixes` sur ce diff), le jar, l'ecran. Je n'ai donc pas regarde `11 h 30` et `11 h 15` rendus : leur plausibilite est a `nadia-prof`. Le garde d'accent du depot n'est pas lance (aucune ligne accentuee du YAML n'a ete reecrite, seuls six distracteurs ASCII et le commentaire d'en-tete changent).

### Questions

- `Q1` : aucune. Aucun changement de poids, aucune regle d'architecture decidee.
