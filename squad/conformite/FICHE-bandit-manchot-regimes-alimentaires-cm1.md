# Fiche de conformite - bandit-manchot-regimes-alimentaires-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-regimes-alimentaires.yaml`.
- **Entree de catalogue** : `bandit-manchot-regimes-alimentaires-cm1`, titre servi *"Quel est son
  régime ?"*, consigne servie *"Lis bien, puis dis quel est le régime de l'animal, ou à quoi sert sa
  nourriture."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-07** par
  `nadia-prof`, instance B, **au premier passage**, apres avoir joue le paquet). **`SCI-CM1-07`
  ferme** : `non couvert` -> **`couvert-valide`**, **sans mention de couverture partielle**. Deux
  reserves neuves et datees, `RGA-R1` et `RGA-R2`, **non bloquantes**.
- **Histoire des verdicts, aucun efface** : **2026-09-07, VALIDE SOUS RESERVE**. Premier et seul
  verdict.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
  section 4.3 (ligne 7, parmi *"les plus sures"*) et section 5 (`FAB-1`, `PLA-1`, `BIN-1`), et
  `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `3181b5e6` (`[LEA] s31: S31-3 et S31-4 - second passage de sciences CM1,
  quatre lignes de plus`), le 2026-09-07.
- **Derniere mise a jour** : 2026-09-07 (`nadia-prof`, s31, instance B).

## Competence visee

`Identifier les besoins des êtres vivants pour se nourrir et distinguer les régimes alimentaires
(herbivore, carnivore, omnivore).` (`SCI-CM1-07`, sciences et technologie, CM1, *Le vivant, sa
diversite et les fonctions qui le caracterisent*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-07
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3181b5e6`, worktree dedie `s31-nadia-B`, jar reconstruit depuis
ce worktree sur le JDK de la toolchain (21.0.10, version rapportee par la machine virtuelle) et
lance sur le **port 8081**. **Partie complete de dix manches par appels HTTP reels, avec une erreur
volontaire sur chaque manche**, ecran de fin atteint, score 10. **Et j'ai lu les onze items.** **Ce
n'est pas un audit degrade.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, et **ni la validation ni les reserves
n'en dependent**.

### Ce qui a ete verifie, et ce qui tient

- **Le mot du libelle, et c'est mon refus ecrit d'avance qui est tenu.** Le paquet sert
  **herbivore, carnivore, omnivore**, et **jamais *vegetarien***. J'ai verifie les onze items :
  le mot *vegetarien* n'apparait nulle part, ni en bonne reponse, ni en distracteur, ni dans un
  indice, ni dans un commentaire. Le libelle du descripteur est **copie conforme** du referentiel
  corrige le 2026-09-05. **Ce point m'importe plus que sa taille ne le laisse croire** : le libelle
  de la matrice a porte le mauvais mot jusqu'a s30, et un paquet ecrit en obeissant au document de
  pilotage aurait enseigne qu'un lapin est vegetarien - ce qui est faux, *vegetarien* etant un choix
  alimentaire humain et non le regime d'un animal. **Le paquet a suivi le referentiel, pas le
  document de pilotage, et c'est le bon ordre.**
- **Ancrage au programme** : **`FER-1`** - les trois termes de la parenthese occupent la position de
  bonne reponse : *herbivore* (items 1, 4, 7), *carnivore* (2, 5), *omnivore* (3, 6). Aucun element
  du libelle n'est hors de portee, donc **aucune mention de couverture partielle n'est due** ; ma
  table du 2026-09-06 n'en exigeait d'avance que sur les lignes **6**, **14** et **20**, et la
  ligne 7 n'en fait pas partie. **Je n'en ajoute pas une apres coup.**
  **`FER-3`** - deux taches distinctes par l'operation mentale : *classer* un animal d'apres ce
  qu'il mange (sept items) d'un cote, *dire a quoi sert la nourriture* (quatre items) de l'autre.
  La moins servie en porte quatre, pour un plancher de trois. **`FER-2`** : onze items pour cette
  seule ligne.
- **Sept animaux distincts en tache A, verifie par denombrement** : lapin, lion, sanglier, vache,
  loup, ours brun, chevre. **Aucun item n'est un autre servi sous un autre nom** - la description
  de ce que mange chaque animal change a chaque item, et c'est elle qui porte la reponse.
- **Exactitude du contenu** : **les onze items sont exacts.** Les sept regimes sont ceux de la
  biologie de cycle 3 ; les quatre fonctions de la nourriture (energie pour bouger, construction du
  corps pendant la croissance, reparation des tissus, reserves pendant l'hibernation) sont justes,
  et les indices qui les expliquent le sont aussi - y compris le plus delicat, *"Le corps continue
  de consommer un peu d'énergie même pendant le sommeil profond de l'hibernation."*
  **`SCI-3`** : **zero item numerique sur onze.**
- **`FAB-1`, les trois branches** : **(a)** la bonne reponse varie a l'interieur de chaque tache -
  trois valeurs en tache A, quatre fonctions distinctes en tache B, et **jamais la meme sur trois
  items consecutifs** ; **(b)** en tache A les distracteurs sont les deux autres categories d'un
  classement ferme, ce que l'exception de `FAB-1` autorise nommement, et **cela n'ouvre aucune
  elimination** ; en tache B les distracteurs ne sont pas pris dans le vivier des bonnes reponses
  des autres items ; **(c)** toutes les propositions de la tache B commencent par *a*, bonne
  reponse comprise, et aucune longueur ne designe la bonne. **Aucune forme ne trahit la reponse.**
- **`BIN-1`** : sans objet. Le classement de la tache principale porte sur **trois** categories, et
  **aucun item du paquet ne porte deux propositions** (verifie par comptage : 11 items, 3
  propositions chacun).
- **`CYC-1`** : sans objet mesure. Aucun paquet de CP servi n'enseigne les regimes alimentaires ;
  `cp-qlm-cycle-de-vie.yaml` porte *se nourrir* comme caractere du vivant, sans nommer aucun regime.
  **Aucune contradiction possible, et je le dis apres avoir lu, pas apres avoir suppose.**
- **Lisibilite** : consigne d'une ligne nommant les deux taches (`DEC-0078` tenue). Les enonces de
  la tache A font une phrase courte et concrete. **Ceux de la tache B sont plus longs** - deux
  phrases, dont une mise en situation - mais restent dans ce qu'un CM1 lit sans peine.
- **Charge cognitive** : une seule propriete par item, jamais deux. Verifie sur les onze.
- **Traitement de l'erreur** : verifie a l'ecran. L'indice s'affiche des la premiere erreur et
  explique le mecanisme au lieu de nommer la reponse ; a la troisieme, la bonne reponse est
  revelee. **Aucun blocage.**
- **Biais et ton** : pas de stereotype de genre, d'origine, de milieu ni de configuration
  familiale. **Un point regarde et ecarte** : l'item 8 parle de *"la nourriture qu'il va manger ce
  soir-là"* a propos d'un enfant tiers. Il ne met en cause **ni le foyer ni la vie de l'enfant qui
  joue**, ce que `BIA-4` interdit ; il presuppose seulement un repas du soir, en troisieme personne
  et sur un cas decrit. **Je ne le retiens pas comme un biais**, et je l'ecris pour qu'on ne croie
  pas que je ne l'ai pas vu.

### Motif

**Je valide : la ligne peut fermer, et la moitie du libelle qui la nomme est excellemment servie.**

La tache A - sept items, sept animaux, trois categories - est un exercice de classement propre :
un enfant qui ne connait pas le regime du sanglier ne peut rien eliminer, doit lire ce qu'il mange
et doit savoir ce que *omnivore* veut dire. **C'est la competence, exactement.**

**Ce que la validation ne dit pas, et qui fait les reserves.** La tache B, quatre items, est
fabriquee autrement : sur ces quatre items, **la bonne reponse est la seule proposition serieuse**,
et l'enfant y arrive en ecartant ce qui n'appartient a rien. Ce n'est **pas** ce qui fait fermer ou
non la ligne - `FER-1` est portee entierement par la tache A, dont la parenthese du libelle est
l'objet - mais c'est un defaut reel, systematique a l'echelle de la tache entiere, et c'est la
famille de defaut qui a produit mes deux refus de s30. **Il se repare item par item, sans toucher
la tache A.**

### Reserves

> **`RGA-R1` - sur les quatre items de la tache B, la bonne reponse est la seule proposition
> serieuse.** Ouverte le 2026-09-07 par `nadia-prof`, sur `PLA-1` (clause du 2026-09-07, posee neuf
> heures avant que le paquet soit ecrit et citee par la developpeuse elle-meme dans le commentaire
> du paquet). **Releve item par item** :
>
> | Item | Bonne reponse | Les deux distracteurs | Ce qui ne va pas |
> |---|---|---|---|
> | 8 (l'enfant qui court) | *a lui redonner de l'energie pour bouger* | *a le faire grandir plus vite que les autres enfants* ; *a combler seulement une sensation desagreable* | le premier n'est ecarte que par le comparatif *plus vite que les autres* ; le second **n'est pas faux**, il est incomplet, et seul l'adverbe *seulement* le rend faux |
> | 9 (le bebe animal) | *a construire son corps pendant qu'il grandit* | *a le faire dormir plus longtemps chaque jour* ; *a lui donner un pelage plus colore* | aucun des deux n'est une reponse qu'un enfant donne |
> | 10 (le chat blesse) | *a reparer les tissus abimes par la blessure* | *a faire oublier la douleur au chat* ; *a rendre son pelage plus doux* | le second n'appartient a rien |
> | 11 (l'ours avant l'hiver) | *a lui fournir de l'energie pendant qu'il ne mange plus* | *a le faire grossir pour l'esthetique* ; *a remplacer le sommeil dont il a besoin* | aucun des deux n'est une reponse qu'un enfant donne |
>
> **Ce que cela coute a l'enfant** : sur ces quatre items, il gagne sans savoir a quoi sert la
> nourriture. **Ce qu'il faut** : des distracteurs qui soient de vraies reponses d'enfant - *"a le
> faire grandir"* sur l'item de l'ours, *"a lui donner des forces pour jouer"* sur l'item du chat -
> c'est-a-dire des reponses justes **pour une autre situation**, comme la tache A le fait deja avec
> ses trois categories. **Le modele existe dans le meme lot** : l'item 8 de
> `cm1-sciences-etats-matiere` (*"la disparition"*), valide sans reserve le meme jour.
> **Non bloquante** : `FER-1`, `FER-2` et `FER-3` sont tenues sans elle.

> **`RGA-R2` - deux formulations hors du registre de CM1 dans la tache B.** Ouverte le 2026-09-07
> par `nadia-prof`. Deux points, tous deux **de fond et de niveau scolaire**, pas d'orthographe -
> l'orthographe et la ponctuation appartiennent au `relecteur-pedagogique` (`DEC-0002`, amendement
> du 2026-08-04 ; `DEC-0056`) :
> **(a)** l'item 11 porte *"à le faire grossir pour l'esthétique"*. **Le mot *esthetique* n'est pas
> au vocabulaire du CM1**, et applique a un ours il ne veut rien dire. Vu a l'ecran, manche 7 ;
> **(b)** l'item 8 porte *"à combler seulement une sensation désagréable"*. La proposition est
> lourde a lire, et surtout **elle ne se distingue de la bonne reponse que par l'adverbe
> *seulement***. Un item dont la discrimination tient a un adverbe est un exercice de lecture fine,
> pas un exercice de sciences.
> **Non bloquante.** Elle voyage avec `RGA-R1` dans le meme rejeu : les deux se corrigent dans les
> memes quatre items.

**Regime de reprise des deux reserves** : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`),
comme pour toute reserve de contenu sur un paquet unique. **Elles ne rouvrent pas la ligne.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude des sept regimes et des quatre fonctions de la nourriture** | **aucun** |
| **`PLA-1`** - qu'une proposition soit un candidat et non un remplissage | **aucun**, et c'est exactement la propriete que `RGA-R1` prend en defaut. `ADR-0086` a mesure qu'elle n'est pas encodable : le mot portant est *plausible*, qui n'est pas une chaine. **Rien n'a averti la developpeuse** |
| **`FAB-1b`** - le vivier des distracteurs | **aucun.** Ici la propriete est **tenue** : verifie a la lecture des onze items |
| **Le mot *vegetarien* absent du contenu servi** | **aucun**, et c'est une chaine, donc la seule des cinq qui serait mordable. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **`CYC-1`** vers les paquets de CP | **aucun.** Verifiee a la main |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-07` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | Le compte de la matrice lui appartient ; **je ne la touche pas** |
| **`RGA-R1` et `RGA-R2`** | `camille-po`, via `sofia-sm` | En faire une story. Regime : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Les deux se corrigent dans les memes quatre items |
| **L'absence du mot *vegetarien* dans le contenu servi** | `theo-archi` | Propriete textuelle du YAML, donc mordable, et elle protege un mot que le depot a deja porte faux pendant deux sprints. Je dis la propriete, pas la forme |
