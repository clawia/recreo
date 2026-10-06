# Fiche de conformité - bandit-manchot-sens-multiplication-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu(s)** : à créer, type `question`, avec `illustration` et `illustrationTexteAlternatif` sur les
  items à disposition dessinée (même forme que `ce1-solides`, `ADR-0073`). Story `S51-MAT-MULTIPLICATION`
  (`docs/backlog/backlog.md`), décision `DEC-0110` (sections 1 et 5) et `DEC-0111` (section 5). Développeur
  de paquet et de dessin : `lea-dev` (planning de s52) ; contraintes de dessin des réseaux de points :
  `squad/design/` (`ines-design`, section 3, commit `7350fb07`). Nom de fichier de contenu et identifiant
  d'entrée de catalogue fixés par le développeur au premier commit ; le nom de cette fiche est proposé par
  `camille-po` et renommé si l'identifiant diffère. Distinct de `bandit-manchot-tables-multiplication-ce1`
  (`MAT-CE1-07`).
- **Statut global** : en construction - aucun contenu écrit à l'ouverture de cette fiche.
- **Dernière mise à jour** : 2026-10-06 (ouverture, `camille-po`, s52).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Nombres et calculs | Comprendre le sens de la multiplication (addition réitérée, disposition en lignes et en colonnes) | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-06` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 6,
verdict **accepté sous conditions `MUL-CE1-1` à `MUL-CE1-7`** ; `couvert-valide` atteignable avec les
dessins de `MUL-CE1-5`, `en cours` sinon.

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `bandit-manchot`, forme d'item `question` ; aucune mécanique ni forme d'item neuve.
- [ ] Les deux facteurs sont des entiers de 2 à 10 de produit au plus 100 (`MUL-CE1-1`).
- [ ] Aucun énoncé n'est un produit nu `a x b = ?` : chaque item montre l'addition réitérée écrite ou une
  disposition dessinée (`MUL-CE1-2`) ; aucun énoncé de la forme `3 x ? = 12`, ni partage ou groupement
  posé en mots, ni *"Combien de fois 3 dans 12 ?"* ; aucun item n'est un rappel de table
  (`MAT-CE1-07`) sous un autre habillage.
- [ ] Au moins trois items vont de la somme répétée vers le produit et au moins trois du produit vers la
  somme répétée (`MUL-CE1-3`).
- [ ] Au moins deux items portent sur une même disposition lue dans les deux sens et disent que les deux
  comptes donnent le même total (`MUL-CE1-4`).
- [ ] Un dessin de disposition : ronds pleins ou points identiques, alignés en réseau régulier de au plus dix
  rangées et dix colonnes, distinguables à la taille de rendu ; l'énoncé ne donne ni le nombre de rangées ni
  celui de colonnes (`MUL-CE1-5`).
- [ ] Deux distracteurs par item, chacun d'une forme admise (`a + b`, `a x (b - 1)`, `a x (b + 1)`,
  `(a + 1) x b`, `a x (b + 2)` si `a = b`), positifs, distincts entre eux et de la bonne réponse, famille
  déclarée à côté de l'item ; `a + b` interdit quand `a = b = 2` ; la bonne réponse occupe chacun des trois
  rangs sur le paquet (`MUL-CE1-6`).
- [ ] Au moins dix items servent la ligne ; le paquet porte strictement plus d'items que de manches
  (`FER-2`, `FER-3`) ; chaque énoncé respecte `LON-CE1-1` et `LON-CE1-2`.
- [ ] `LIllustrationDeContenuResoutEtAucuneNeDortTest` vert avec le paquet.
- [ ] Mention de couverture (`MUL-CE1-7`) : si aucune disposition n'est dessinée, la matrice porte
  *"addition reiteree seule ; la disposition en lignes et en colonnes n'est servie par aucun jeu et ne peut
  pas l'etre par du texte"* (graphie de la source) et la ligne reste `en cours` ; si les dispositions sont dessinées et que
  `MUL-CE1-4` et `MUL-CE1-5` sont tenues, aucune mention n'est due.
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-06 (`nadia-prof`, s52, instance C) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** Les dispositions sont dessinees et `MUL-CE1-4` et `MUL-CE1-5` sont
tenues : **aucune mention de couverture n'est due** (`MUL-CE1-7`), et `camille-po` peut passer
`MAT-CE1-06` a `couvert-valide`. La reserve `MUL-CE1-R1` ne bloque pas le mouvement de ligne.

#### 1. Ce que j'ai fait

**Audit joue.** Meme jar que pour `bandit-manchot-comparer-ranger-ce1` : construit une fois depuis ce
worktree a `92344c23` (`bash outils/mvn-jdk21.sh -B -DskipTests package`, aucun Maven d'un autre agent en
cours), copie dans mon scratchpad, lance depuis la copie sur JDK 21, port 18870.

**Douze parties completes de dix manches, appels HTTP reels** : les **16 items** ont tous ete tires, de 5
a 10 fois chacun. Sur chaque manche je presse les propositions dans l'ordre servi jusqu'a la resolution :
erreur, deuxieme erreur et revelation sont vues sur tout le paquet. **Les 16 reponses que j'ai etablies
sont celles que le jeu credite.**

**Propositions masquees, tenues sur onze parties et pas sur la premiere.** Ma premiere partie
exploratoire affichait les propositions avec l'enonce : dix items y ont ete vus propositions ouvertes.
Les onze parties suivantes n'affichaient que les enonces, et j'ai ecrit les 16 reponses avant de lire
les propositions. **Pour les trois dessins, mon comptage n'est pas aveugle** : j'avais lu l'en-tete
commente des fichiers SVG, qui donne lignes et colonnes, avant de compter sur le rendu. Je le dis plutot
que de le taire ; le recompte sur le rendu concorde.

**Dessins lus a leur taille de rendu.** Trois manches de reseau captees dans la page reelle (gabarit,
feuille de style et fragment servis par le jar), a 320 px de fenetre (`outils/mesurer-rendu.sh 320`,
Chrome headless) : les dessins sont servis a 1:1 (`.image-item`, `width: auto`, plafond 176 px), soit
80 x 48, 64 x 96 et 128 x 80 px. Sans zone (item illustre, pas de designation), `ZON-9` ne s'applique
pas.

#### 2. Ancrage au programme

`MAT-CE1-06` : *"Comprendre le sens de la multiplication (addition réitérée, disposition en lignes et en
colonnes)."* Les deux representations de la parenthese sont servies : l'addition reiteree dans les deux
sens (10 items) et la disposition dessinee lue dans les deux sens (6 items sur 3 reseaux). **C'est bien
cette competence** : aucun item ne demande un produit (`MAT-CE1-07`), aucun ne pose un partage
(`MAT-CE1-18`).

#### 3. `MUL-CE1-1` a `MUL-CE1-7`, et les doutes de `lea-dev`

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `MUL-CE1-1` | **oui** | Facteurs de 3 a 8 dans les enonces et les reseaux, de 2 a 9 dans les distracteurs ; bonne reponse au plus 40, distracteur au plus 45 |
| `MUL-CE1-2` | **oui** | Aucun produit nu ; les reponses sont des ecritures (*"3 × 4"*, *"6 + 6 + 6 + 6"*), jamais un nombre |
| `MUL-CE1-3` | **oui** | 4 items de la somme vers le produit, 6 du produit vers la somme |
| `MUL-CE1-4` | **oui** | Chacun des trois reseaux est lu ligne par ligne et colonne par colonne, et l'enonce de chacun des six items dit que *"les deux façons de compter donnent le même total"*. Voir `MUL-CE1-R1` |
| `MUL-CE1-5` | **oui** | Disques identiques de 10 px, pas de 16 px, reseaux 3 x 5, 6 x 4 et 5 x 8 ; chaque point separe de ses voisins sur le rendu 1:1 ; aucune forme nommee ; aucun enonce ne dit le nombre de lignes ou de colonnes ; texte alternatif *"Des points rangés en lignes et en colonnes."*, celui que j'avais propose |
| `MUL-CE1-6` | **oui** | Chaque distracteur a la valeur d'une forme admise (*"une ligne oubliee"*, *"une de trop"*, *"un terme plus grand"*, `a + b` sur les seules additions, `a x (b + 2)` sur 5 x 5). Rangs de la bonne reponse **comptes sur la valeur de l'ecriture** sur les 16 items : plus petite 7, intermediaire 7, plus grande 2 ; les trois au-dessus de 10 %, aucun majoritaire |
| `MUL-CE1-7` | **sans objet** | Dispositions dessinees, `-4` et `-5` tenues : aucune mention |

**Convention "n × m" = "n fois m"** (lignes x points par ligne, colonnes x points par colonne) : je la
retiens. C'est la lecture de la classe (*"3 × 5, c'est 3 fois 5"*), l'indice la dit a l'enfant (*"Le
premier nombre dit combien de fois écrire le deuxième."*), et elle est tenue partout, y compris sur les
dessins (*3 lignes de 5* : *"3 × 5"* ; *5 colonnes de 3* : *"5 × 3"*). **Le point qui la rend juste** :
l'ecriture commutee n'est jamais proposee dans un item. Pour *"4 + 4 + 4"*, *"4 × 3"* n'est pas un
distracteur ; il aurait ete une bonne reponse comptee fausse. Je l'ai verifie sur les 16 items.

**Rangs comptes sur la valeur** : c'est ainsi que `MUL-CE1-6` les definit (*"position de la bonne
reponse"*, calculee sur des valeurs dans ma table de s43). Une ecriture *"3 × 4"* vaut 12 pour l'enfant
qui comprend la multiplication. Que le garde-fou de rang ecarte ces items (`ADR-0068`) est un choix
d'architecture ; la propriete pedagogique, elle, est tenue sur le paquet entier.

**Phrase dans l'enonce au lieu d'une explication** : recevable. `MUL-CE1-4` dit *"l'enonce ou
l'explication"*. Dans l'enonce, chaque enfant la lit ; dans l'explication, seul celui qui se trompe
l'aurait vue.

#### 4. Niveau reel

Consigne : *"Lis bien ou regarde le dessin, puis choisis la bonne écriture."* Enonces d'addition et de
multiplication de 5 a 8 mots (le signe seul ne compte pas), une phrase. Enonces de reseau : 17 mots, trois phrases, la derniere est la
question (`LON-CE1-1`, `LON-CE1-2`). Sur une erreur, *"Ce n'est pas ça. Essaie encore !"* et l'indice ; a
la revelation, *"Appuie sur la bonne réponse : 8 × 5"* et l'indice, qui dit quoi compter (*"Regarde
combien il y a de points dans une colonne, et combien de colonnes."*). L'enfant bloque avance.

Sur le rendu a 320 px, l'enonce de reseau occupe sept lignes en gros caracteres sous un dessin de
48 a 96 px de haut. Le dessin reste lisible ; la longueur de l'enonce vient pour un tiers de sa premiere
phrase, qui est l'objet de la reserve.

#### 5. Exactitude, biais et ton

16 reponses exactes, distracteurs distincts entre eux et de la bonne reponse, entiers positifs. Aucun
personnage, aucun contexte social. Ton neutre.

#### 6. La reserve

> **`MUL-CE1-R1` - La premiere phrase des six enonces de reseau parle de "deux façons" avant de les
> nommer.** *"Les deux façons de compter donnent le même total. Compte les points ligne par ligne.
> Quelle multiplication ?"* L'enfant de CE1 lit d'abord une affirmation sur deux facons qu'on ne lui a pas
> encore montrees ; la seconde n'est nommee nulle part dans l'item. Sur une partie de dix manches, il ne
> voit souvent qu'un des deux items d'un meme reseau (sur ma premiere partie masquee : le reseau a et le
> reseau b lus dans un seul sens chacun) ; la phrase reste alors une affirmation sans support.
>
> **Correction a faire** : sur les six items, nommer l'autre facon dans la phrase et la placer apres la
> consigne de comptage. Par exemple : *"Compte les points ligne par ligne. Colonne par colonne, le total
> est le même. Quelle multiplication ?"* (16 mots, trois phrases, la derniere est la question), et
> symetriquement sur les items *colonne par colonne*. `MUL-CE1-4` reste tenue, l'enonce disant toujours
> que les deux comptes donnent le meme total.
>
> **Ce n'est pas un durcissement** : `MUL-CE1-4` demande que l'enonce le dise, et il le dit. C'est une
> reserve neuve et datee, trouvee en jouant, qui porte sur la lisibilite.
>
> **Pourquoi elle n'empeche pas `couvert-valide`** : la tache demandee (compter par lignes ou par
> colonnes, ecrire la multiplication) est juste et faisable sur chaque item ; la phrase gene la lecture,
> elle ne fausse pas l'exercice.

#### 7. Ce dont je reste la seule garde

`PaquetCe1SensMultiplicationTest` et `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` portent,
d'apres leurs noms de methodes (lus, non executes par moi), les comptes de `MUL-CE1-1` a `-6` et l'accord
entre chaque dessin et ce que ses items affirment (nombre de points, rayon, lignes). **Aucun automate ne
verifie** : qu'un point se distingue de son voisin a la taille servie et qu'un reseau se compte sans se
perdre (`MUL-CE1-5`), que l'ecriture commutee ne soit jamais un distracteur, et qu'un enonce se lise
dans l'ordre ou il est ecrit (`MUL-CE1-R1`). Ces proprietes n'ont ete verifiees qu'ici.

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

**Porte technique** : non lancee par moi (le job `build` ferait un `clean` dans un worktree ou `lea-dev`
construit). Ce Done pedagogique suppose la CI verte sur l'arbre fusionne ; `sofia-sm` la verifie a la
fusion.
