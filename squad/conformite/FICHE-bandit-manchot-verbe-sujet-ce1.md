# Fiche de conformite - bandit-manchot-verbe-sujet-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-verbe-sujet.yaml` (24 items, type
  `question`), ecrit en s53, corrige et branche par `marc-dev` (`550df967`).
- **Entree de catalogue** : `bandit-manchot-verbe-sujet-ce1`, titre servi *"Le verbe et son sujet"*, consigne servie
  *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-13`. **Story** : `S56-BR-FRA-CE1-13`. **Decision** : `DEC-0116`.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-13`, *"Identifier le verbe
  d'une phrase et son sujet (un nom, un groupe nominal court ou un pronom personnel)."*
- **Conditions opposables** : `VSJ-CE1-1` a `VSJ-CE1-3`, `TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`), avec la barre de s54 sur `TRV-CE1-5` ; `J1` a `J5`, `H1`, `H2` et les onze
  proprietes de la section 2.8 (`ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, section 2).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-11 par `nadia-prof` (s56), apres avoir joue le jar
  servi. Fiche ouverte par ce verdict. Rejeu du 2026-10-14 (s57) : `VSJ-CE1-R1` levee sur `7dc40fb0`, reserve neuve
  `VSJ-CE1-R2` ; statut inchange, **VALIDE SOUS RESERVE**. Rejeu du 2026-10-16 (s57, palier 2) : `VSJ-CE1-R2` levee sur
  le jar du code de `0c0d2142` (forme commune fusionnee) ; aucune reserve ouverte, statut **VALIDE**.
- **Derniere mise a jour** : 2026-10-16.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-11 (`nadia-prof`, s56) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `FRA-CE1-13` a `couvert-valide`, sans mention de couverture
partielle, et faire une story de la reserve `VSJ-CE1-R1` (section 4), non bloquante.

#### 1. Ce que j'ai fait

**Audit joue.** Worktree `s56-nadia-art` a `a6ca1751` (`round-two`, qui contient `550df967`). Build, jar et arret :
`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, section 1 (`clean verify` vert, 2386 tests, JDK 21.0.10).

- **Par les routes servies** (`POST /parties/bandit-manchot-verbe-sujet-ce1`, puis `/coups`, script de poste hors
  depot) : 15 parties avec une erreur sur chacun des deux distracteurs de chaque manche, puis la bonne : **les 24 items
  tires** (1 a 11 fois chacun), **300 erreurs**, 0 ecart (propositions servies identiques au fichier, aucun indice
  avant la premiere erreur, indice servi identique au fichier apres chaque erreur, bonne reponse creditee). 3 parties
  poussees jusqu'a la revelation sur les items 1, 7 et 24 : *"Appuie sur la bonne réponse :"* et la forme ; l'ecran de
  fin redonne la phrase, la question et *"La bonne réponse : range"*.
- **Au navigateur** (meme dispositif que la fiche du paquet d'oeuvres), une partie de dix manches a 744 x 551, deux
  erreurs par manche : l'enonce tient en deux lignes, l'indice est au-dessus du pli sur les dix (bas du texte a
  y = 522).

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : 15 items demandent le sujet d'un verbe cite, 9 le verbe d'une phrase, sujets
pronom, prenom et groupe nominal court. La frontiere avec `-12` tient : la, l'enfant nomme la classe d'un mot montre ;
ici, il trouve un mot ou un groupe dans la phrase. Avec `-14` : ici, aucune forme a accorder.

#### 3. `J1` a `J5`, `H1`, `H2`

| Point | Constat |
|---|---|
| `J1`, `J2` | dix bonnes reponses sans majuscule ; prenom en distracteur aux items 9, 10, 12, 13 ; sur aucun item la bonne reponse n'est la seule proposition a majuscule (vu a l'ecran : *Tom*, *Lina*, *son livre*). **Tenu** |
| `J3` | *"Noé range ses crayons."* ; prenoms 8 / 8. **Tenu** |
| `J4` | *"Demande-toi : qui fait l'action de ce verbe ?"* sur les items 1, 5, 9, 13, 17 ; aucun distracteur de ces items ne fait l'action. **Tenu** |
| `J5` | consigne generique servie a chaque manche. **Tenu** |
| `H1` | non pris (item 3 garde *"ce que fait quelqu'un"* pour un chat). Je l'avais dit : **pas une reserve** |
| `H2` | **le jeu le montre** : voir `VSJ-CE1-R1` |

**Exactitude** : les 24 sujets et les 24 verbes sont justes, relus a l'ecran ; *porte* (15) et *souffle* (21) ont un
homonyme nom, la phrase departage. **Distracteurs** : copies de la phrase, plausibles, aucun absurde. **Ton et roles** :
rien a relever ; *la maîtresse lit*, *le facteur apporte* sont des metiers, pas des roles par genre.

#### 4. `E1` de `marc-dev` (`regarde`, items 4, 8, 12, 16, 20 et 24) : rien ne change

L'enfant qui a lu *"regarde les mots devant"* dans l'indice d'une autre manche n'a pas recu la reponse de l'item 24.
L'indice de l'item 24 (*"Cherche le mot qui dit ce qui se passe."*) ne contient aucune de ses propositions ; les cinq
autres indices ne s'affichent que sur leurs propres items, apres une erreur. Et le mot y est un verbe a l'imperatif :
s'il a laisse une trace, c'est celle d'un mot qui dit une action, ce que l'item 24 demande de reconnaitre. Ce n'est pas
une fuite de reponse ; la propriete 9 est bornee a l'item, et c'est la bonne borne.

#### 5. Reserve neuve, datee du 2026-10-11, non bloquante

**`VSJ-CE1-R1` - sur les items de sujet, la place seule donne la reponse.** Sur les 15 items de sujet, la bonne
reponse est le premier groupe de la phrase et les deux distracteurs sont apres le verbe ; l'indice de cinq d'entre eux
le dit (*"Le sujet est avant le verbe : regarde les mots devant."*). Un enfant qui choisit la proposition la plus a
gauche dans la phrase reussit les 15 sans poser la question *"Qui est-ce qui ?"*. Je l'avais nomme comme limite
(`H2`) en ecrivant *"si le jeu montre que la position seule suffit, j'ouvrirai une reserve neuve et datee"* : c'est
cette reserve, pas un durcissement de `VSJ-CE1-2`, que le paquet tient. Ce n'est pas un refus : la procedure de
position est vraie sur des phrases de CE1, rien de faux n'est appris.

**Propriete a tenir** : sur au moins 3 des items de sujet, une des deux propositions fausses est placee **avant** le
verbe cite dans la phrase (par exemple un complement en tete : *"« Ce matin, Léa lit un livre. »"*, propositions
*Léa*, *ce matin*, *un livre*), la bonne reponse restant le sujet, `LON-CE1-1` et `J1` tenues. **Ce que la levee
change chez moi, dit d'avance** : ma propriete 8 (section 2.8 de mon arbitrage, *"les deux distracteurs apres"*) est
amendee par moi sur ces items quand la story s'ecrit, et le test qui la porte change avec elle ; ce sera un
amendement date, pas un assouplissement silencieux. Les textes sont au developpeur ; je les juge en rejouant (trois
items, 0,5, `PAR-10`).

#### 6. Pour le `relecteur-pedagogique`

`E2` de `marc-dev` : l'en-tete du paquet dit encore *"La question est portee par la consigne du jeu quand l'enonce ne la
porte pas."* Chaque enonce porte sa question. Commentaire non lu par l'enfant : a lui.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1VerbeSujetTest` tient `J1` a `J5`, la longueur, la place des distracteurs et l'absence de proposition dans
un indice. **Il ne verifie pas** qu'un distracteur ne fait pas l'action, la justesse des 48 reponses, `VSJ-CE1-R1`
(il garde aujourd'hui le contraire, propriete 8), la lisibilite d'un indice pour un enfant de CE1. **Je les tiens en
jouant.**

**Identifiants** : un neuf, `VSJ-CE1-R1`. Forme copiee d'`ACV-CE1-R1` : trois segments, niveau au milieu, marqueur `R`
dans le dernier (`ADR-0100`). Prefixe `VSJ-CE1` deja alloue (`VSJ-CE1-1` a `-3`). Alloue dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme diff. Aucun renomme.

**Tarif consomme** : audit 1,0 (premier passage, instance A au planning).

**Signe** : `nadia-prof`, s56, 2026-10-11.

### Note du 2026-10-14 (`nadia-prof`, s57, palier 1) : propriete 8 amendee

La propriete 8 est amendee comme annonce en section 5 : `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, 2.8, bloc
*"Amendement du 2026-10-14"*. Mon exemple de la section 5 (*"Ce matin, Léa lit un livre."*) contredit ma propre `J1` : il est
retire comme modele, un exemple qui tient est dans l'amendement. Le critere de levee de `VSJ-CE1-R1` ne change pas. Aucun verdict
neuf.

### Rejeu du 2026-10-14 (`nadia-prof`, s57, palier 1, instance A) : `VSJ-CE1-R1` **levee** ; statut **VALIDE SOUS RESERVE**, reserve neuve `VSJ-CE1-R2`

**Objet joue** : branche `s57-marc-vsj-ce1-r1`, commit `7dc40fb0` (`marc-dev`), **non fusionne**. Trois items de sujet
reecrits (items 1, 16 et 20 du fichier) et `PaquetCe1VerbeSujetTest`. L'amendement de la propriete 8 (`7161ab69`) est ancetre
du commit (`git merge-base --is-ancestor 7161ab69 7dc40fb0` sort 0) ; `bash outils/verifier-lignee.sh` sort 0.

#### 1. Ce que j'ai fait

- **Build** : `bash outils/mvn-jdk21.sh -B clean verify` dans le worktree de la branche, racine, sans `-pl` : `BUILD
  SUCCESS`, 2504 tests (120, 31, 486, 1490, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10
  (`java.version` d'un rapport Surefire de `bootstrap`). Done technique : job `build` seul, lance par moi ; les autres jobs ne
  sont pas les miens sur ce commit.
- **Jar** : `java -jar bootstrap/target/recreo.jar --server.port=18511` (port de A, palier 1, libre avant : `netstat -ano`
  filtre sur `:18511 ` ne rendait rien) ; journal : *"Tomcat started on port 18511"*, PID 55476, arrete par ce PID seul.
  Dossier de poste `s57-p1-nadia-prof-a`, hors depot.
- **Par les routes servies** (`POST /parties/bandit-manchot-verbe-sujet-ce1`, puis `/coups`, script de poste) : 27 parties
  de dix manches, **les 24 items tires**, 555 erreurs, 105 revelations, **0 ecart** (propositions servies identiques au
  fichier, aucun indice avant la premiere erreur, indice servi identique au fichier apres chaque erreur, revelation de la
  bonne reponse). Les trois items reecrits tires 24 fois (8, 10 et 6), joues sur chacun de leurs distracteurs et jusqu'a
  la revelation.
- **Rendu a 744 x 551, apres une erreur** (methode de `P3`, s25) : page servie, fragment de la manche jouee insere dans
  `#jeu`, Chrome sans tete, `getBoundingClientRect()` ; fenetre reglee pour que `innerWidth` x `innerHeight` vaille 744 x
  551. **Methode differente de `pilote-q7.mjs`** (pas de protocole de debogage, pas de port 19222) ; etalonnee sur un item
  non modifie : bas du bloc `.indice` a 534, la ou s56 relevait le bas du texte a 522. Les 24 items, un par un.

#### 2. Le critere de levee, joue

| Point | Constat sur le jar |
|---|---|
| Au moins 3 items de sujet avec un distracteur avant le verbe | **3** : *"le soir"* (item 1), *"le toit"* (16), *"le matin"* (20). Sur ces trois items, la proposition la plus a gauche dans la phrase est un distracteur : l'enfant qui choisit le premier groupe de la phrase se trompe. **Tenu** |
| La bonne reponse reste le sujet | *elle*, *les oiseaux*, *un grand bus*. **Tenu** |
| Point 1 de l'amendement : complement en tete, de temps ou de lieu, suivi d'une virgule ; jamais une personne ni un animal | *le soir*, *le matin* (temps), *le toit* (lieu, copie raccourcie de *sur le toit*, comme *la table* ou *le sac* ailleurs dans le paquet). Aucun n'est une personne ni un animal, aucun ne fait l'action. **Tenu**. La virgule est gardee par le test ; le sens, par moi |
| Point 2 : pas *"regarde les mots devant"* sur ces items | *"Demande-toi : qui fait l'action de ce verbe ?"* (1) ; *"Pose la question « Qui est-ce qui ? » avant le verbe."* (16, 20). Servis apres chaque erreur. **Tenu** |
| `J1`, plancher, propriete 4, liste d'ouvertures de `TRV-CE1-6` | aucune majuscule dans les trois propositions des trois items ; pronom 5, prenom 5, groupe nominal 5 ; aucun prenom ajoute ; *Le* et *Sur* etaient deja dans la liste d'ouvertures du test, **qui ne change pas** (mon point 4 prevoyait d'y ajouter le mot d'ouverture : aucun n'etait neuf). **Tenu** |
| `LON-CE1-1` | 10 mots au plus pour la phrase la plus longue (*"Le matin, un grand bus emmène les élèves à l'école."*). **Tenu** |
| Exactitude, ton | les trois phrases et leurs sujets sont justes ; rien a relever |

Les trois items *"regarde les mots devant"* qui restent (4, 8, 12) n'ont aucun distracteur avant le verbe : l'indice y est
vrai, et l'enfant qui l'applique a la lettre aux items 1, 16 et 20 y trouve deux candidats. C'est ce que la reserve demandait.

#### 3. Correction de mon propre constat de s56

J'ai ecrit le 2026-10-11 (section 1) : *"l'indice est au-dessus du pli sur les dix"*. C'etait vrai des dix manches jouees,
**pas du paquet** : l'ancien item 16 (*"« Les oiseaux mangent des graines sur le toit. »"*), que je n'avais pas tire au
navigateur, met deja l'enonce sur trois lignes et le bloc de l'indice a 532 - 582. Mesure faite aujourd'hui en remplacant
le seul texte de l'enonce dans la page servie, pas sur le jar de s56.

#### 4. Reserve neuve, datee du 2026-10-14, non bloquante

**`VSJ-CE1-R2` - a 744 x 551, apres une erreur, l'indice des items 16 et 20 est coupe par le pli.** Sur 22 items, le bloc
`.indice` va de 484 a 534 ; sur les items 16 (*"Sur le toit, ..."*) et 20 (*"Le matin, un grand bus ..."*), l'enonce
passe sur trois lignes et le bloc va de **532 a 582** : l'enfant voit *"Ce n'est pas ça. Essaie encore !"* et le bord
haut d'un cadre vide. **Item 20 : introduit par ce lot** (l'ancien texte, mesure de la meme facon, laissait l'indice a 484 -
534). **Item 16 : anterieur** (section 3). Ce sont deux des trois items qui portent la methode *"Qui est-ce qui ?"* a la
place de la position. **Non bloquante** : le verdict et la croix sont au-dessus du pli, l'indice est atteint en
faisant defiler, et c'est le regime de `RDO-CP-R1`. **Ce qui la leve** : a 744 x 551, apres une erreur, le bas du bloc
`.indice` est au-dessus de y = 551 sur les 24 items. La forme est au developpeur (par exemple une phrase citee plus courte
sur ces deux items, sans perdre le complement en tete) ; je la juge en rejouant.

#### 5. Ce dont je reste la seule garde sur cette ligne

Le test garde le compte de 3, la virgule, l'indice de la place, `J1`, le plancher. **Il ne garde pas** : que le complement
en tete soit de temps ou de lieu et ne fasse pas l'action ; la position de l'indice par rapport au pli (`VSJ-CE1-R2`) ;
l'exactitude des 48 reponses. **Je les tiens en jouant.**

**Identifiants** : un neuf, `VSJ-CE1-R2`. Forme copiee de `VSJ-CE1-R1` : trois segments, niveau au milieu, marqueur `R`
dans le dernier (`ADR-0100`) ; prefixe deja alloue ; ligne de la section 4 de `REGISTRE-identifiants.md` dans le meme diff.

**Tarif consomme** : rejeu 0,5 (poste 3, instance A). Marge de rejeu de A intacte.

**Signe** : `nadia-prof`, s57, palier 1, instance A, 2026-10-14.

### Rejeu du 2026-10-16 (`nadia-prof`, s57, palier 2, instance A, p2-11) : `VSJ-CE1-R2` **levee** ; statut **VALIDE**

**Done pedagogique prononce, sans reserve ouverte.** `FRA-CE1-13` reste `couvert-valide` ; `camille-po` peut fermer la story
`S57-VSJ-CE1-R2`. Rien n'est efface au-dessus : le verdict du 2026-10-11 et le rejeu du 2026-10-14 restent la trace des deux
reserves.

**Objet joue** : le code servi par `round-two` a `e8d14ddb`, identique a celui de `0c0d2142` (`git diff --quiet 0c0d2142
e8d14ddb -- adapters domain application bootstrap pom.xml outils` sort 0), qui porte la forme commune des propositions longues
(`dc6f423d`, fusionnee). **Voie de la forme** (`Q-S9`, `reponse-nadia-q-palier-2.md` 3.8) : le paquet n'a pas change de contenu
depuis le commit que j'ai joue au palier 1 (`git diff 7dc40fb0 0c0d2142` sur `ce1-verbe-sujet.yaml` : deux lignes de commentaire
d'en-tete, aucun item).

#### 1. Ce que j'ai fait

- **Jar** : `bash outils/mvn-jdk21.sh -B clean package -DskipTests` dans le worktree `s57-nadia-p2-a2` (a `e8d14ddb`), `BUILD
  SUCCESS` ; JDK de la toolchain 21.0.10. Feuille servie par le jar, retours chariot retires : SHA-256 `c312cd1b...3ef6`, egal a
  celle de `0c0d2142` et a celle du sommet de la cote. Paquet servi par le jar : egal octet pour octet a celui de `0c0d2142`.
  Lance par `java -jar bootstrap/target/recreo.jar --server.port=18511` (port libre avant : `netstat -ano` filtre ne rendait
  rien) ; journal : *"Tomcat started on port 18511"*, PID 39296, *"using Java 21.0.12.1"* (le `java` du poste, pas celui de la
  toolchain ; sans effet sur ce que la page affiche). Arrete par ce PID seul. Dossier de poste `s57-p2-nadia-prof-a`, hors depot.
- **Done technique** : je ne l'ai pas lance. Je le lis : `sofia-sm` a passe `clean verify` a la racine sur `056f8c88` (2669
  tests, 0 echec), code identique a `e8d14ddb` (`planning.md`, amendement du 2026-10-16 15:27, section 10).
- **Joue comme un enfant, par l'ecran** : Chrome sans tete, **fenetre dont `innerWidth` x `innerHeight` vaut 744 x 551**,
  verifie a chaque manche (taille de fenetre reglee, pas d'emulation d'appareil : methode independante de celle d'`ines-design`).
  Page CE1 de francais, appui sur le bouton *"Le verbe et son sujet"*, puis sur les propositions. 10 parties de dix manches,
  100 manches : 52 justes du premier coup ; sur les 48 autres, deux erreurs, puis un troisieme appui jusqu'a la revelation.
  **Chacun des 24 items joue deux fois en erreur**, ses deux distracteurs presses. Bas de `.indice` releve apres la premiere
  erreur dans l'ordre servi, puis dans les six ordres des trois propositions (boutons reranges dans la page), et apres la
  seconde erreur. Captures regardees : items 1, 16 et 20, apres chaque erreur et a la revelation ; deux ecrans de fin.
- **Ecarts contre le fichier** : 0. Propositions servies egales au paquet, aucun indice avant la premiere erreur, indice servi
  egal au paquet apres chaque erreur.

#### 2. Le critere de levee, joue

Critere ecrit le 2026-10-14 (section 4 du rejeu precedent), inchange : *"a 744 x 551, apres une erreur, le bas du bloc
`.indice` est au-dessus de y = 551 sur les 24 items"*.

| Point | Constat sur le jar |
|---|---|
| Les 24 items, apres la premiere erreur, ordre servi | bas de `.indice` de **418 a 496** ; 24 sur 24 au plus 551 |
| Les 24 items, six ordres | **288 couples (item, ordre) sur 288** au plus 551, maximum 496 |
| Apres la seconde erreur | 24 sur 24, memes valeurs |
| Items 16 (*"Sur le toit, ..."*) et 20 (*"Le matin, un grand bus ..."*) | enonce sur trois lignes (142 - 286), verdict jusqu'a 484, **indice 416 - 496** : 55 px d'air sous la phrase d'aide. Sur la capture, l'enfant lit *"Ce n'est pas ça. Essaie encore !"* et, a cote, *"Pose la question « Qui est-ce qui ? » avant le verbe."* en entier, sans faire defiler |
| Les 22 autres | indice 368 - 418 (indice sur une ligne) ou 368 - 448 (deux lignes) |

**Tenu.** La reserve demandait que l'enfant bloque voie l'aide sur les deux items qui portent la methode *"Qui est-ce qui ?"*
a la place de la position : il la voit.

**Revelation, hors levee** (`Q-R2`, que je ne releve pas) : sur les items 16 et 20, au troisieme appui, la reponse revelee
(*"les oiseaux"*, *"un grand bus"*) est au-dessus du pli, l'indice reste affiche et descend a 582, la barre de defilement
apparait. Sur les 22 autres, l'indice reste au-dessus du pli. Observation, pas une reserve : a ce moment l'enfant a la reponse
sous les yeux.

#### 3. La clause `Q-R4`, sur la cote ecrite avant ce rejeu

Lue dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cote-de-reference-forme-commune-2026-10-16.md`, commit
`96f5e101` (`ines-design`, 15:02), fusionne par `056f8c88`, ancetre de `e8d14ddb` ; blob identique aux deux commits
(`16bb8df4`). Ecrite avant ce rejeu (15:58).

- **Ce qu'elle dit de ce jeu** (section 4.1 et section 5) : base `aa9d703c` *"534 - 582, 22 sur 24"* ; sommet `0c0d2142`
  *"418 - 496, 24 sur 24"*, *"144 / 144"* couples, *"Perdu : 0"*. **Ma mesure rend les memes bornes** (418 et 496, items 16 et
  20 a 496) par une autre methode de fenetre ; ses items 15 et 19 sont mes 16 et 20 (elle compte depuis 0).
- **Ses quatre elements** : `ce1-verbe-sujet` 22 sur 22 et *"les gateaux"* 486 -> 400 ; `ce1-texte-narratif`, 11 sur 11 de 550 a
  434 ; `additions-cp`, **65 enonces sur 65**, 486 -> 370 ; `comprehension-cm1`, aucun item ne perd le pli. Sortie de la clause
  (*"tout item dont l'indice finit au-dessus de y = 551 sur le jar de base y reste apres le commit"*) : **tenue sur les quatre**.
- **Base au sens de `Q-R5`** : `aa9d703c`, parent de `dc6f423d` (verifie par `sofia-sm`, `git rev-parse dc6f423d^`).
- **Deuxieme commande de `Q-R5`, dite et tranchee.** Elle est tenue pour `dc6f423d` (5 fichiers, la forme et son test), **pas
  pour `0c0d2142`**, que je joue (22 fichiers : deux paquets neufs, `catalogue.yaml`, `N1` et `N2`, des tests). Je ne la tiens
  pas pour un empechement, et je dis pourquoi plutot que de la taire : cette commande sert a attribuer a la forme un item qui
  perdrait le pli ; ici **aucun n'en perd**, et la comparaison n'est pas une deduction mais une mesure faite sur le jar meme que
  l'enfant aura, contre le jar de base joue seul. Je l'ai verifiee sur l'arbre : `git diff --stat aa9d703c 0c0d2142` est vide
  sur `ce1-verbe-sujet.yaml` et sur les gabarits ; le diff de `catalogue.yaml` n'ajoute que deux entrees (`familles-de-mots-ce1`,
  `pronoms-ce1`). Ce n'est pas l'exception de contenu que `Q-P4` refuse : rien n'est juge a la place d'une mesure.
- **Condition de `Q-S9`** (`PA5`) : `theo-archi` tient `C1'`, `C2`, `C3` et n'amende rien (`avis-theo-palier-2.md`, `TP1` a
  `TP3`) ; sa lecture du diff trouve la feuille fusionnee egale au candidat (`lecture-theo-p2-7-forme-commune.md`, section 2).
  Aucune regle dont depend la cote de ce jeu n'est retiree.

#### 4. Exactitude, ton

Les 24 items relus dans le paquet servi ; 72 propositions (24 bonnes reponses, 48 distracteurs), inchangees depuis mon audit
du 2026-10-14, justes. Rien a relever.

**Correction de mon propre constat** : le 2026-10-14 (section 5 de ce rejeu-la) j'ai ecrit *"l'exactitude des 48 reponses"*.
Le paquet porte 72 propositions ; 48 est le compte des distracteurs. La garde porte sur les 72.

#### 5. Ce dont je reste la seule garde sur cette ligne

**La position de l'indice par rapport au pli n'a aucun garde-fou executable** : aucun test du reacteur ne rend une page, et
`C1'` n'a pas de garde sur l'ecran de manche (`TP4`). La levee vaut pour la feuille de `0c0d2142` ; **toute modification de
`recreo.css` ou du gabarit de manche peut la defaire sans un rouge**. Restent aussi a moi, comme au 2026-10-14 : que le
complement en tete soit de temps ou de lieu et ne fasse pas l'action ; l'exactitude des 72 propositions.

**Ce que je n'ai pas joue** : une autre largeur que 744 (le critere n'en demande pas) ; un appareil reel, une autre police, un
autre navigateur ; la lecture d'ecran.

**Identifiants** : aucun neuf, aucun renomme. `VSJ-CE1-R2` est deja alloue (section 4 de `REGISTRE-identifiants.md`).

**Tarif consomme** : 0,25 (p2-11, paquet inchange, instance A).

**Signe** : `nadia-prof`, s57, palier 2, instance A, 2026-10-16.
