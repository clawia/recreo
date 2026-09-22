# Fiche de conformite - bandit-manchot-additions-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-additions-jusqu-a-100.yaml`
  (32 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-additions-ce1`, titre servi *"Les grandes additions"*,
  consigne servie *"Combien ca fait ?"*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-03`, sous-partie *"additions dans la limite de 100"*.
- **Statut global** : **VALIDE SOUS RESERVE**. Done pedagogique **prononce le 2026-09-22** par
  `nadia-prof`, **apres avoir joue le jar servi**. Deux reserves nommees, `ADD-R1` et `ADD-R2`,
  **non bloquantes**.
- **Mouvement de ligne autorise** : `MAT-CE1-03` passe de `non couvert` a **`en cours`**, et **pas
  au-dela**. Motif en section 8. Le mouvement est ecrit par `camille-po`, pas par moi.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` (2026-09-21), clauses
  `ADD-CE1-1` et `ADD-CE1-2`, et les six points de sa section 6.
- **Borne de longueur applicable** : `LON-CE1-1` et `LON-CE1-2`
  (`ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s37).

---

## 1. Ce que j'ai joue, et comment

**Audit non degrade.** J'ai construit le reacteur complet (`mvn -B clean verify` via
`outils/mvn-jdk21.sh`, `BUILD SUCCESS`), lance `bootstrap/target/recreo.jar`, et joue sur l'ecran
reellement servi.

Ce que j'ai fait sur cet ecran :

- **une premiere partie complete a la main**, manche par manche, en me trompant volontairement
  **deux fois de suite** sur la meme manche pour voir ce que l'enfant lit apres une erreur, apres
  deux erreurs, puis a la fin de la partie ;
- **40 parties completes de dix manches**, pour compter ce que le tirage sert reellement a un
  enfant ;
- **25 parties supplementaires en me trompant une fois sur chaque manche**, jusqu'a avoir
  **rencontre les 32 items** et **lu les 32 indices tels que l'enfant les lit**. Les 32 sont
  atteints ; aucun item n'est juge sur le YAML seul.

**Ce que l'enfant voit sur une manche** : `Score : 0`, la consigne *"Combien ca fait ?"*, l'enonce
`30 + 47 = ?`, trois propositions. Apres une erreur : *"Ce n'est pas ca. Essaie encore !"* puis
l'indice de l'item. La proposition deja pressee reste pressable, marquee `deja-essayee`. Fin de
partie : *"Tu as trouve, tout seul : 10 / Tu as joue toutes les questions : 10 / Bravo, c'est
juste ! / C'est fini !"*. Les accents sont rendus correctement, y compris les blancs insecables
devant `?` et `:`.

## 2. Exactitude - point 6.5 de mon arbitrage

**Les 32 items sont exacts, et j'ai recalcule les 32.**

| Controle | Resultat |
|---|---|
| `bonneReponse` = a + b | **32 / 32 exactes** |
| Un distracteur egal a la bonne reponse | **aucun** |
| Distracteurs dupliques dans un item | **aucun** |
| Somme > 100 | **aucune** |
| Distracteur > 100 | **aucun** |
| Couple `(a, b)` servi deux fois | **aucun**, ni dans l'ordre ni hors ordre |
| Rang de la bonne reponse dans la liste source | 10 en 1re position, 12 en 2e, 10 en 3e - **aucun biais de rang**, et la mecanique melange par-dessus |

**Plausibilite des distracteurs.** 29 items sur 32 proposent trois entiers consecutifs encadrant la
bonne reponse. Cette forme **interdit l'elimination par ordre de grandeur** et oblige au calcul
exact : c'est une qualite, pas un defaut, et c'est la structure du paquet de CP deja servie et
validee. Les trois exceptions (`27 + 15`, `36 + 28`, `59 + 23`) portent en distracteur **la retenue
oubliee** (`32`, `54`, `72`), qui est l'erreur classique du niveau : ce sont les trois meilleurs
items du paquet. Voir `ADD-R2` pour le quatrieme de cette famille, qui ne la porte pas.

## 3. Ancrage au programme - point 6.1, celui dont je restais la seule garde

**Le paquet travaille bien `MAT-CE1-03`, et pas `MAT-CE1-04` sous son nom.** C'est le glissement que
mon arbitrage du 2026-09-21 existait pour empecher, et il n'a pas eu lieu.

`MAT-CE1-03` dit : *"Calcul mental - additions et soustractions dans la limite de 100 (complements a
la dizaine, doubles et moities)"*. Les deux strategies que la parenthese nomme et qui sont dans le
perimetre des additions sont servies : **3 doubles et 5 complements a la dizaine, soit 25 % des
items**, la ou le generateur inchange en produisait 0,82 % et 9,27 %. Et la forme la plus couteuse -
`36 + 28`, celle qui bascule le paquet vers `MAT-CE1-04` joue mentalement - est a **12,5 %**, bien
en dessous du plafond de 20 % que j'avais pose pour cette raison precise.

Les huit indices de forme travaillent des **strategies de calcul mental** et jamais la pose en
colonnes : *"Pars du plus grand, 9, et avance de 7"*, *"C'est un double"*, *"Regarde les unites :
4 et 6 font une dizaine entiere"*, *"Ajoute les dizaines, puis les unites"*. C'est la ligne declaree,
pas sa voisine.

## 4. `ADD-CE1-1` et `ADD-CE1-2` - tenues, et comptees par moi avant de lire le garde-fou

**Comptage sur la liste des items elle-meme**, donc exact et non estime, comme `ADD-CE1-2` l'exige
pour un paquet statique. Je l'ai fait **avant** d'ouvrir le test de `theo-archi`, pour ne pas juger
sa mesure avec sa propre mesure.

| # | Forme | Items | Part | Cible `ADD-CE1-2` | |
|---|---|---|---|---|---|
| 1 | Operande nul | 0 | 0,00 % | 0 % a 5 % (`ADD-CE1-1`) | **tenue** |
| 2 | Somme <= 20 | 5 | 15,62 % | 10 % a 20 % | **tenue** |
| 3 | Double | 3 | 9,38 % | 5 % a 15 % | **tenue** |
| 4 | Complement a la dizaine | 5 | 15,62 % | 10 % a 20 % | **tenue** |
| 5 | Ajout d'un nombre rond | 5 | 15,62 % | 10 % a 20 % | **tenue** |
| 6 | Deux chiffres plus un chiffre | 6 | 18,75 % | 15 % a 25 % | **tenue** |
| 7 | Deux a deux chiffres, sans retenue | 4 | 12,50 % | 10 % a 25 % | **tenue** |
| 8 | Deux a deux chiffres, avec retenue | 4 | 12,50 % | 10 % a 20 % | **tenue** |

**Huit bandes sur huit, chacune avec de la marge.** `ADD-CE1-1` est tenue par zero, ce que j'avais
explicitement autorise : *"il est acceptable qu'il n'en rencontre jamais"*.

**Et `ADD-CE1-1` a bien ete tenue de la maniere que je demandais** : `ACCEPTATION_OPERANDE_NUL_SUR`
n'a pas ete touchee, et le zero n'a pas ete *ramene* dans le tirage pour retrouver une proportion de
la bande CP. C'est ce que ma section 3 refusait ; il ne s'est pas produit.

## 5. `LON-CE1-1` et `LON-CE1-2` - tenues sans discussion

Les enonces sont de la forme `30 + 47 = ?`. Sous la convention d'`ADR-0089` reprise par mon
arbitrage de s36 - **un symbole seul n'est pas un mot** - ce sont **deux mots** pour un plafond de
20, et **une phrase**, qui est la question, pour un plafond de trois. Marge maximale sur les deux
clauses, et le troisieme point que j'avais nomme (la derivation de fin de phrase, qui evite le faux
rouge sur `12 + ? = 20`) se verifie ici : ces enonces ne comptent pas deux phrases.

## 6. Une correction de mon propre chiffre, avant de m'appuyer dessus

**Ma section 4.4 du 2026-09-21 annoncait qu'une partie sur trois environ (30 %) ne contiendrait
aucune manche d'entree, au plancher strict.** Ce chiffre venait de `0,887` puissance dix - **un
calcul de tirages independants**. La mecanique ne tire pas ainsi : elle sert **dix items sur
trente-deux sans remise**, ce qui resserre la composition d'une partie autour des proportions
exactes du paquet. Mon modele etait le mauvais, et je le dis plutot que de laisser mon chiffre
tenir.

**Mesure sur 40 parties completes reellement jouees sur l'ecran servi :**

| | Generateur inchange (ma mesure du 21) | Ma prevision du 21 | **Paquet livre, mesure du 22** |
|---|---|---|---|
| Parties sans aucune manche d'entree | 59,08 % | 30 % au pire | **12 %** (5 sur 40) |
| Parties sans double ni complement | 34,82 % | - | **2 %** (1 sur 40) |

Le paquet fait **mieux que la borne que j'avais annoncee comme le pire cas acceptable**, et de
beaucoup. Aucune reserve n'est ouverte de ce cote.

**Et je tiens l'engagement que j'avais ecrit** : je ne refuse pas au motif qu'une partie donnee ne
contient pas de manche facile. `ADD-CE1-2` est tenue, cela suffit, et je ne durcis pas la clause
apres coup.

## 7. Les deux reserves

### 7.1 `ADD-R1` - l'indice des cinq items *"ajout d'un nombre rond"* s'arrete une etape trop tot

**Ouverte le 2026-09-22. Non bloquante.** C'est le seul defaut de cet increment, et il est dans le
seul endroit qu'aucun garde-fou du depot ne lit : l'indice.

**Le fait.** Les cinq items de la forme 5 portent un indice qui, **applique a la lettre par
l'enfant, produit un nombre qui n'est pas parmi les trois propositions**.

| Item | Indice servi | Ce que l'enfant obtient en le suivant | Bonne reponse | Propositions a l'ecran |
|---|---|---|---|---|
| `40 + 25` | *"40 est un nombre rond : ajoute-le aux dizaines de 25."* | 40 + 20 = **60** | 65 | 65, 66, 67 |
| `30 + 47` | *"30 est un nombre rond : ajoute-le aux dizaines de 47."* | 30 + 40 = **70** | 77 | 76, 77, 78 |
| `60 + 18` | *"60 est un nombre rond : ajoute-le aux dizaines de 18."* | 60 + 10 = **70** | 78 | 76, 77, 78 |
| `50 + 33` | *"50 est un nombre rond : ajoute-le aux dizaines de 33."* | 50 + 30 = **80** | 83 | 83, 84, 85 |
| `20 + 36` | *"20 est un nombre rond : ajoute-le aux dizaines de 36."* | 20 + 30 = **50** | 56 | 55, 56, 57 |

**Pourquoi c'est un vrai defaut et pas une preference de formulation.** Cet indice n'apparait
**qu'apres une erreur** : il est lu exactement au moment ou l'enfant est bloque, et c'est le seul
dispositif par lequel il peut repartir. L'enfant qui l'applique **correctement** obtient un nombre
absent de l'ecran. Il n'en conclut pas que l'indice est incomplet - il n'a aucun moyen de le savoir -
il en conclut **qu'il a mal calcule alors qu'il a fait exactement ce qu'on lui disait**. Un indice
qui apprend a un enfant a se mefier de son calcul juste fait plus de mal qu'un indice absent.

L'indice ne mentionne jamais les unites, alors que ce sont elles qui restent a ajouter. Comparez
avec l'indice de la forme 7, qui est complet et correct : *"Ajoute les dizaines, puis les unites :
4 et 5 restent sous dix."*

**Le texte de remplacement, que je donne parce que l'indice est nommement a moi** (section 6.4 de
mon arbitrage). Une seule phrase, meme patron pour les cinq, le mot *"puis"* portant l'etape qui
manque :

| Item | Indice a servir |
|---|---|
| `40 + 25` | *"40 est un nombre rond : ajoute d'abord 40 et 20, puis les 5 qui restent."* |
| `30 + 47` | *"30 est un nombre rond : ajoute d'abord 30 et 40, puis les 7 qui restent."* |
| `60 + 18` | *"60 est un nombre rond : ajoute d'abord 60 et 10, puis les 8 qui restent."* |
| `50 + 33` | *"50 est un nombre rond : ajoute d'abord 50 et 30, puis les 3 qui restent."* |
| `20 + 36` | *"20 est un nombre rond : ajoute d'abord 20 et 30, puis les 6 qui restent."* |

Onze a douze mots, meme longueur que l'indice actuel, meme registre, et le resultat obtenu en le
suivant est **la bonne reponse**. L'expression *"les dizaines de 47"* disparait au passage : elle
demandait a un enfant de CE1 de traduire seul *"les dizaines de 47"* en **40** et non en **4**,
ce qui est un second obstacle dans un texte cense en lever un.

### 7.2 `ADD-R2` - `48 + 26` est le seul item avec retenue qui ne porte pas la retenue oubliee

**Ouverte le 2026-09-22. Non bloquante.** Un seul nombre a changer.

Les quatre items de la forme 8 portent tous le meme indice : *"... depassent dix : n'oublie pas la
dizaine qu'ils forment."* Trois d'entre eux mettent a l'ecran **l'erreur contre laquelle l'indice
met en garde**, ce qui rend l'item diagnostique :

| Item | Bonne reponse | Distracteurs | La retenue oubliee est-elle a l'ecran ? |
|---|---|---|---|
| `27 + 15` | 42 | **32**, 43 | **oui** |
| `36 + 28` | 64 | **54**, 63 | **oui** |
| `59 + 23` | 82 | **72**, 83 | **oui** |
| `48 + 26` | 74 | 75, 76 | **non** |

Sur `48 + 26`, l'indice avertit contre une erreur qu'aucune proposition n'incarne. **Remplacer un
distracteur par `64`** (soit `74` sans la retenue) aligne cet item sur ses trois freres.

## 8. Le mouvement de ligne que ce verdict autorise, et celui qu'il n'autorise pas

**`MAT-CE1-03` : `non couvert` -> `en cours`.** Pas `couvert-valide`, et ce n'est pas une
consequence des deux reserves : **je l'avais deja ecrit le 2026-09-21**, avant qu'un seul item
n'existe. La ligne couvre *"additions **et soustractions** dans la limite de 100 (complements a la
dizaine, doubles et **moities**)"*. Ce paquet ne sert que les additions ; les soustractions, les
complements a la dizaine **pris pour eux-memes** (de la forme `47 + ? = 50`, que ce paquet ne pose
jamais) et les moities restent entiers.

Les deux reserves **n'empechent pas** ce mouvement : 27 items sur 32 sont irreprochables, les 32
enonces sont exacts, le jeu se joue de bout en bout et il travaille la ligne qu'il declare.

**Le mouvement est ecrit par `camille-po`, pas par moi**, et les deux reserves lui sont adressees
comme matiere a stories.

## 9. Ce dont je reste la seule garde, au 2026-09-22

**Deux proprietes sortent de ma main ce jour**, et je le dis explicitement pour que l'equipe sache ce
qui est desormais couvert :

| Propriete | Qui la tient |
|---|---|
| **`ADD-CE1-1`** (plafond d'operande nul) | **plus moi seule.** `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` l'execute |
| **`ADD-CE1-2`** (les huit bandes) | **plus moi seule.** Meme test. J'ai relu son `formeDe` ligne a ligne : c'est la traduction litterale de ma section 4.3, **ordre de priorite compris**, et sa population est derivee et non enumeree. J'ai compte les huit bandes moi-meme **avant** de l'ouvrir, et nos deux comptages concordent |
| **Que le paquet serve `MAT-CE1-03` et non `MAT-CE1-04`** | **moi seule.** Aucun automate ne lit une declaration de ligne pour en juger la verite. Verifie ce jour, section 3 |
| **Que l'indice aide** | **moi seule, et c'est precisement la que le seul defaut de cet increment se trouve.** Aucun garde-fou du depot ne lit un indice, ni ne le confronte aux propositions de son item. `ADD-R1` et `ADD-R2` ont toutes deux ete trouvees en jouant, sur une porte technique verte |
| **L'ordre des manches dans une partie** | **moi seule.** La table est une distribution, elle ne dit rien de l'ordre. Regarde ce jour sur 40 parties : les sept formes ouvrent des parties, aucune ne domine l'entree. **Rien n'est ouvert la-dessus** |
| **L'exactitude des 32 enonces et la plausibilite des distracteurs** | **moi**, mais recalculable par quiconque - et recalculee ce jour, section 2 |

## 10. Ce que je ne retiens pas contre cet increment, et je le nomme pour qu'on ne le rouvre pas

- **La lisibilite de trois entiers consecutifs a l'echelle 100** (`86`, `87`, `88` pour `61 + 27`).
  J'avais ecrit le 2026-09-21, comme observation neuve et datee, que je la regarderais en jouant, et
  que j'en ferais **ni une condition ni une reserve**. Je l'ai regardee : elle se lit. **Je n'ouvre
  rien**, et je ne deplace pas ce poteau.
- **La composition d'une partie donnee.** Section 6 : le paquet fait mieux que ma propre borne.
- **Le titre et la consigne**, qui sont nommement a moi. *"Les grandes additions"* est juste pour un
  enfant qui connait deja le paquet de CP borne a 10, et *"Combien ca fait ?"* est quatre mots au
  registre exact d'une classe de CE1. **Approuves tels que servis, aucun changement demande.**
- **Le libelle de l'ecran de fin** *"Tu as trouve, tout seul :"*. Il ne vient pas de `S37-1` : il est
  entre en s35 et il est partage par tous les jeux. **Ce n'est pas une reserve de cet increment** ;
  je le route a `camille-po` comme une observation transverse, a traiter la ou il vit ou pas du tout.

---

## Bloc de validation

> **Done pedagogique : VALIDE SOUS RESERVE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi** - 65 parties, les 32
> items rencontres a l'ecran, les 32 indices lus comme l'enfant les lit, erreurs jouees
> volontairement.
>
> **Ce que j'ai verifie** : les 32 sommes (exactes), les distracteurs (aucun egal a la bonne
> reponse, aucun duplique, aucun au-dessus de 100), les huit bandes de `ADD-CE1-2` (tenues, comptees
> par moi sur la liste avant lecture du garde-fou), `ADD-CE1-1` (0 %, conforme, et tenue sans
> ramener le zero dans le tirage), `LON-CE1-1` et `LON-CE1-2` (deux mots, une phrase), l'ancrage sur
> `MAT-CE1-03` et non sur `MAT-CE1-04` (25 % de doubles et complements, 12,5 % de forme 8), et la
> composition reelle de 40 parties.
>
> **Les deux reserves, non bloquantes** : `ADD-R1` - les cinq indices *"ajout d'un nombre rond"*
> s'arretent avant les unites et produisent, appliques a la lettre, un nombre absent des
> propositions ; texte de remplacement donne en 7.1. `ADD-R2` - `48 + 26` ne porte pas la retenue
> oubliee en distracteur, contrairement a ses trois freres ; valeur donnee en 7.2.
>
> **`MAT-CE1-03` : `non couvert` -> `en cours`**, jamais `couvert-valide` avec ce seul paquet. Le
> mouvement est ecrit par `camille-po`.

## S36-ORDRE-NIVEAUX - confirmation, et aucun Done

**Aucun Done pedagogique n'est du sur cette story et je n'en prononce aucun** : elle ne sert aucune
ligne de programme, et `sofia-sm` l'avait deja note au planning.

**Confirmation sur l'ecran reellement servi**, puisque j'y etais : l'en-tete rend `CP`, `CE1`, `CM1`
dans cet ordre. **Aucun signal destine a l'enfant n'est degrade.** Les trois puces sont le meme
element de gabarit, meme libelle, meme role `entree`, meme apparence ; seul leur ordre change, et il
devient celui que l'enfant et l'adulte qui l'accompagne lisent de gauche a droite comme la
progression scolaire. C'est une observation, pas un verdict.
