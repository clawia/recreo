# Fiche de conformité - bandit-manchot-ordre-alphabetique-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-ordre-alphabetique.yaml`
  (24 items, type `question`), écrit par `lea-dev` (`e25a1cd3`).
- **Entrée de catalogue** : `bandit-manchot-ordre-alphabetique-ce1`, titre servi *"Comme dans le
  dictionnaire"*, consigne servie *"Lis les mots, puis choisis la bonne réponse."*,
  `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-23`. **Story** : `S51-FRA-ALPHABETIQUE`. **Décision** : `DEC-0110`.
- **Compétence programme visée** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-23`,
  *"Ranger des mots dans l'ordre alphabétique (première lettre, puis deuxième lettre)."*
- **Conditions opposables** : `ALP-CE1-1` à `ALP-CE1-3` et `TRV-CE1-1` à `TRV-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 6.1), `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5` (même fichier, section 1).
- **Statut global** : **VALIDÉ SOUS RÉSERVE** le 2026-10-06 par `nadia-prof` (s51, instance A). Une
  réserve, `ALP-CE1-R1`. Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
  **Réserve `ALP-CE1-R1` levée** le 2026-10-06 par `nadia-prof` (s52, instance A), sur `3bc2e501` : la
  ligne est validée sans réserve. Le verdict de s51 reste écrit ci-dessous.
- **Dernière mise à jour** : 2026-10-06 (levée de réserve, s52).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-23` à `couvert-valide`, sans mention
de couverture partielle (`DEC-0110`, section 10), et faire une story de la réserve `ALP-CE1-R1`
(section 4).

#### 1. Ce que j'ai fait

**Audit joué.** Worktree `s51-nadia-instance-a` à `271058ec`, qui contient `e25a1cd3`. Même jar et
même méthode que pour `FICHE-bandit-manchot-graphie-ce1.md` (section 1). **Rendu non vu au
navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique** : `mvn verify` vert sur la racine du worktree, job `chemins` vert ; détail et limites dans `FICHE-bandit-manchot-graphie-ce1.md`, section 6.

**Lecture masquée d'abord** : les 24 items affichés avec leurs trois propositions triées, sans la
bonne réponse. J'ai rangé chacun moi-même avant d'ouvrir le fichier ; mes 24 réponses sont les 24
bonnes réponses du fichier.

**Soixante-dix parties complètes de dix manches**, mêmes conduites que pour le paquet de graphie.
**Les 24 items tirés et joués**, chacun avec une erreur et une révélation au moins. Sur les 700
manches : propositions servies identiques au fichier, aucun distracteur crédité juste, indice servi
identique au fichier, réponse révélée juste. Position de la bonne réponse sur les 300 manches de la
seconde série : 94, 101 et 105 aux trois places. Écran de fin atteint ; après une révélation, il
redonne la question et *"La bonne réponse : nez, pain, pomme"*.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne, et ses deux étages : 7 items *"Quel mot vient en premier"* se
décident à la première lettre (*arbre* / *rose* / *vache*), 7 à la deuxième (*table* / *tigre* /
*tomate*) ; les 10 items de listes demandent les deux à la fois. Choisir parmi trois listes vaut
*ranger* sur le précédent de `FRA-CE1-08`, à la condition que j'avais posée : des distracteurs qui
sont des erreurs réelles. Ils le sont (section 3, `ALP-CE1-3`).

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `ALP-CE1-1` | **oui** | 14 items *"Quel mot vient en premier"*, 10 items de trois listes des trois mêmes mots |
| `ALP-CE1-2` | **oui** | 17 items portent au moins deux mots de même première lettre ; aucune décision à la troisième lettre ; aucun accent, apostrophe, trait d'union ni majuscule dans les deux premières lettres (*fenêtre*, *garçon* les portent plus loin) |
| `ALP-CE1-3` | **oui** | les 10 listes : un distracteur à l'ordre inverse, un juste sur la première lettre et faux sur la deuxième ; famille déclarée au-dessus de l'item |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support |
| `TRV-CE1-2` | **oui** | trois propositions distinctes, une seule acceptable ; une liste n'est jamais plus longue qu'une autre ; le mot strictement le plus long est la bonne réponse de 4 items (*lapin*, *girafe*, *cerise*, *garçon*). L'en-tête du paquet dit cinq : écart sans effet sur la condition, signalé à `lea-dev` |
| `TRV-CE1-3` | **sans objet** | aucun son |
| `TRV-CE1-4` | **oui** | *ordre alphabétique* : admis |
| `TRV-CE1-5` | **en partie, réserve `ALP-CE1-R1`** | 5 à 10 mots, cinq formes ; aucune ne donne la réponse, aucune n'est fausse. **Trois indices envoient l'enfant à la première lettre là où elle ne décide pas** |
| `TRV-CE1-6` | **sans objet** | aucun personnage, aucun *"tu"* |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | deux énoncés, de 8 et 6 mots, une phrase ; trois mots au plus par proposition |

#### 4. La réserve, datée du 2026-10-06

**`ALP-CE1-R1` - trois indices montrent la première lettre là où seule la deuxième décide
(`TRV-CE1-5`).**

| Item | Propositions | Indice servi |
|---|---|---|
| 1 | *lapin* / *lune* / *loup* | *"Regarde la première lettre de chaque mot."* |
| 6 | *sac* / *sel* / *sol* | *"Regarde la première lettre de chaque mot."* |
| 11 | *cerise, cube, orange* / *orange, cube, cerise* / *cube, cerise, orange* | *"Regarde la première lettre de chaque mot."* |

L'enfant qui s'est trompé lit l'indice, regarde les premières lettres, et trouve trois `l`, trois
`s`, ou deux listes qui commencent toutes deux par `c`. L'indice ne dit rien de faux, mais il
l'arrête à l'étage qui ne décide pas. C'est précisément l'étage que la ligne ajoute au CP.

**Pourquoi je ne refuse pas.** Aucun indice n'est faux, aucune réponse n'est fausse, et l'enfant
bloqué avance quand même : la révélation arrive à la troisième erreur. Le défaut porte sur trois
items sur vingt-quatre, et se corrige en changeant trois chaînes.

**Ce qui lève la réserve** : sur ces trois items, l'indice mène jusqu'à la deuxième lettre, par
exemple *"Si la première lettre est la même, regarde la deuxième."*, forme déjà servie par le paquet.
Plus largement, aucun item dont la décision se fait à la deuxième lettre ne porte un indice qui
s'arrête à la première. Rien d'autre ne change.

#### 5. Exactitude, niveau, ton

**Une seule réponse acceptable sur chaque item** : vérifié par ma lecture masquée, puis au jeu.
Les 24 bonnes réponses sont justes ; aucune décision ne dépend d'un `ç` ni d'un `ê`.

**Les distracteurs de listes** sont les deux erreurs réelles de la classe : prendre l'ordre à
l'envers, et ranger sur la première lettre en oubliant la deuxième (*bol, ballon, table*).

**Niveau** : mots de la classe et de la vie courante, une à trois syllabes. Plusieurs items reprennent
les mêmes mots (*lapin*, *loup*, *lune* aux items 1 et 2) : c'est une répétition, pas une aide
décisive, puisque la question change.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1AlphabetiqueTest` tient les formes, les proportions, l'arrêt à la deuxième lettre, les
familles de distracteurs déclarées et les longueurs. **Il ne lit pas l'indice contre l'item** : il
ne sait pas qu'une phrase juste peut envoyer l'enfant au mauvais endroit (`ALP-CE1-R1`). J'en
reste la seule garde, en jouant.

**Identifiants** : un neuf, `ALP-CE1-R1`. Forme copiée de `HOM-CE1-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s51, instance A, 2026-10-06.

### Levée de la réserve `ALP-CE1-R1`, le 2026-10-06 (`nadia-prof`, s52, instance A)

**`ALP-CE1-R1` est levée.** Le verdict de s51 n'est pas rouvert : `FRA-CE1-23` reste `couvert-valide`,
désormais sans réserve.

**Ce que j'ai fait.** Commit `3bc2e501` (`marc-dev`), paquet rejoué en entier sur le jar construit au
sommet de branche `befc4128`, port 18830, quarante parties de dix manches : les 24 items tirés (12 à 26
fois chacun), chacun avec au moins une erreur et une révélation, aucune anomalie de service ; l'indice
servi est celui du fichier. **Rendu non vu au navigateur.**

**Le diff** : trois indices changent, aucune autre lettre du paquet. Items 1 (*lapin* / *lune* / *loup*),
6 (*sac* / *sel* / *sol*) et 11 (*cerise, cube, orange*) portent désormais *"Si la première lettre est la
même, regarde la deuxième."*, la forme que ma barre donnait en exemple.

**Le doute de `marc-dev` : sept autres items.** Les items 3, 5, 8, 15, 18, 20 et 23 se décident à la
deuxième lettre et portent *"Compare d'abord les premières lettres."* ou *"Lis chaque mot, puis compare
ses premières lettres."*. La seconde clause de ma barre - *"aucun item dont la décision se fait à la
deuxième lettre ne porte un indice qui s'arrête à la première"* - les couvre-t-elle ? **Non**, pour deux
motifs :

1. **Ces indices ne s'arrêtent pas à la première lettre.** *"D'abord"* annonce une étape suivante ;
   *"ses premières lettres"*, rapporté à *"chaque mot"*, désigne les premières lettres **d'un** mot,
   donc plus d'une. L'indice que j'ai fait corriger disait *"la première lettre de chaque mot"* : une
   seule lettre par mot.
2. **Je les avais lus à s51 et ne les avais pas retenus.** Ma réserve nommait trois items et la forme
   *"la première lettre"*. Les compter aujourd'hui dans la barre serait la relever après coup.

Les items 16 et 22 gardent *"Regarde la première lettre de chaque mot."* : ils se décident à la
première lettre (*crayon* / *fenêtre* / *hibou*, *garçon* / *rouge* / *lampe*), l'indice y est juste.

`MNC-1` reste opposable à ce Done, dans les termes du verdict de s51 et de `DEC-0053`.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s52, instance A, 2026-10-06.
