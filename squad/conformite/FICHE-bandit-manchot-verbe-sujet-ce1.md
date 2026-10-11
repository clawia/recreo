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
  servi. Fiche ouverte par ce verdict.
- **Derniere mise a jour** : 2026-10-11.

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
