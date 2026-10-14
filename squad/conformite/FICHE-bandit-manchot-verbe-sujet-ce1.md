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
  `VSJ-CE1-R2` ; statut inchange, **VALIDE SOUS RESERVE**.
- **Derniere mise a jour** : 2026-10-14.

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
