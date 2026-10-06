# Fiche de conformité - bandit-manchot-comparer-ranger-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu(s)** : à créer, type `question`, sans illustration. Story `S51-MAT-COMPARER-RANGER`
  (`docs/backlog/backlog.md`), décision `DEC-0110` (sections 1 et 5) et `DEC-0111` (sections 5.1 et 5.4).
  Développeur : `lea-dev` (planning de s52). Nom de fichier de contenu et identifiant d'entrée de catalogue
  fixés par le développeur au premier commit ; le nom de cette fiche est proposé par `camille-po` et
  renommé si l'identifiant diffère.
- **Statut global** : en construction - aucun contenu écrit à l'ouverture de cette fiche. **Couverture
  partielle nommée** : texte seul, la moitié *placer sur une ligne graduée* n'est pas servie.
- **Dernière mise à jour** : 2026-10-06 (ouverture, `camille-po`, s52).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Nombres et calculs | Comparer, ranger et encadrer les nombres jusqu'à 1000, et les placer sur une ligne graduée (moitié textuelle : les trois premiers verbes) | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-02` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 3,
**couverture partielle nommée sur texte seul**, `ORD-CE1-1` à `ORD-CE1-6`. La ligne ne ferme pas dans ce
jeu : elle passe à `en cours`, jamais à `couvert-valide`.

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `bandit-manchot`, forme d'item `question`, sans illustration ; aucun item ne demande de
  placer un nombre sur une ligne graduée ni ne donne dans l'énoncé la position d'un nombre sur une ligne
  (*"entre quelles graduations se trouve 450 ?"* est un encadrement déguisé et sert `ORD-CE1-4`).
- [ ] Nombres entiers de 0 à 1000, deux à deux distincts dans un même item ; au moins la moitié des items en
  portent un de trois chiffres (`ORD-CE1-1`).
- [ ] Items de rangement : `RAN-1` à `RAN-8`, avec `RAN-2` à 1000 et chaque proposition de trois nombres de
  16 caractères au plus, espaces comprises (`ORD-CE1-2`).
- [ ] Au moins trois items posent une comparaison de deux nombres, distincte d'un rangement (`ORD-CE1-3`).
- [ ] Au moins quatre items d'encadrement dont l'énoncé nomme l'échelle, la dizaine et la centaine chacune
  servie par au moins un item ; la bonne réponse est un encadrement, jamais un complément (`ORD-CE1-4`) ;
  aucun item d'encadrement n'a pour réponse un complément, lu à côté de
  `bandit-manchot-complements-dizaine-ce1`.
- [ ] Comparer, ranger et encadrer portent chacun au moins trois items ; au moins dix items servent la
  ligne ; le paquet porte strictement plus d'items que de manches (`FER-2`, `FER-3`) ; chaque énoncé
  respecte `LON-CE1-1` et `LON-CE1-2`.
- [ ] Mention de couverture (`ORD-CE1-5`), reprise mot pour mot dans la matrice : *"comparer, ranger et
  encadrer seuls ; le verbe 'placer sur une ligne graduee' n'est servi par aucun jeu et ne peut pas l'etre
  par du texte"* (graphie de la source, `docs/backlog/backlog.md`). Statut de la ligne : `en cours`.
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-06 (`nadia-prof`, s52, instance C) : **VALIDE**

**Done pedagogique prononce sur la moitie textuelle.** `camille-po` passe `MAT-CE1-02` a **`en cours`**,
jamais a `couvert-valide`, avec la mention d'`ORD-CE1-5` mot pour mot dans la matrice : *"comparer,
ranger et encadrer seuls ; le verbe 'placer sur une ligne graduee' n'est servi par aucun jeu et ne peut
pas l'etre par du texte"*. Aucune reserve.

#### 1. Ce que j'ai fait

**Audit joue, pas degrade.** Jar construit une fois depuis le worktree `s52-nadia-instance-c` a
`92344c23` (`bash outils/mvn-jdk21.sh -B -DskipTests package`, aucun Maven d'un autre agent en cours,
verifie sur la liste des processus avant de lancer), copie aussitot dans mon scratchpad et lance depuis la
copie sur JDK 21, port 18870. Paquet `ce1-comparer-ranger` a `4fb9afc4`, inchange a `92344c23`.

**Quatorze parties completes de dix manches, appels HTTP reels** : les **34 items** ont tous ete tires,
de 2 a 10 fois chacun. Sur chaque manche je presse les propositions dans l'ordre servi jusqu'a la
resolution : une erreur, deux erreurs, puis la revelation sont donc vues sur tout le paquet.
**Propositions masquees** : mon script n'affichait que les enonces ; j'ai etabli les 34 reponses avant
de lire les propositions, puis compare. **Les 34 reponses que j'ai etablies sont celles que le jeu
credite.** Le rendu au navigateur n'a pas ete regarde pour ce paquet : il n'a pas d'image, et ses
propositions sont des listes de 14 caracteres au plus.

#### 2. Ancrage au programme

`MAT-CE1-02` : *"Comparer, ranger et encadrer les nombres jusqu'à 1000, et les placer sur une ligne
graduée."* Les trois premiers verbes sont servis, chacun par ses items propres : ranger (20), comparer
(6), encadrer (8). **Aucun item ne place un nombre sur une ligne** et aucun ne donne une position :
`ORD-CE1-6` n'a pas de population. La moitie *placer* reste non couverte, et la mention le dit.

#### 3. `ORD-CE1-1` a `ORD-CE1-5`, et les doutes de `lea-dev`

| Condition | Tenue | Ce que j'ai verifie en jouant |
|---|---|---|
| `ORD-CE1-1` | **oui** | Entiers de 14 a 1000, distincts dans chaque item ; les 34 items portent un nombre de trois chiffres ou plus |
| `ORD-CE1-2` / `RAN-1` | **oui** | Un seul sens, *"Range du plus petit au plus grand"*, sur les 20 items |
| `RAN-2`, `RAN-3` | **oui** | Nombres de 0 a 1000 ; proposition la plus longue : *"1000, 276, 548"*, 14 caracteres |
| `RAN-4` | **oui** | Sur chaque item, un distracteur est l'ordre decroissant, l'autre l'ordre des seuls chiffres des unites (recalcule sur *624, 459, 455* : *624, 455, 459*) |
| `RAN-5b` | **oui** | 11 items sur 20 ont un distracteur qui commence par le meme nombre que la bonne reponse, plancher 10 (recompte sur le fichier, apres le jeu) |
| `RAN-6` | **oui** | 9 items opposent deux nombres de la meme dizaine (*305/308*, *455/459*, *827/828* ...), plancher 6 |
| `RAN-7` | **oui** | Trois indices de rangement, tous une methode, aucun la liste, 8 mots au plus |
| `RAN-8`, lue a la lettre | **oui** | **20 items de rangement**, 20 triplets distincts. `RAN-8` compte les items du rangement, pas ceux du paquet : je l'ai ecrite pour un paquet de rangement seul, et `ORD-CE1-2` la reprend *"sans changement"* |
| `ORD-CE1-3` | **oui** | 6 comparaisons de deux nombres, distinctes des rangements |
| `=` jamais bonne reponse | **oui, et c'est juste** | Les deux nombres sont toujours distincts (`ORD-CE1-1`), donc `=` ne peut etre que distracteur. C'est un distracteur loyal : l'enfant qui ne regarde que les chiffres presents (*451* et *415*) peut le choisir |
| `ORD-CE1-4` | **oui** | 8 encadrements, 4 a la dizaine et 4 a la centaine, echelle nommee dans chaque enonce ; la reponse est un encadrement (*"340 et 350"*), jamais un complement |
| `ORD-CE1-5` | **oui** | Voir le paragraphe qui suit |

**"Entre quelles deux dizaines"**, au sens de deux multiples de 10 : c'est la lecture que j'ai ecrite
dans `ORD-CE1-4` (*"encadrer a la dizaine (`340 < 347 < 350`)"*) et celle de la classe (*"la dizaine juste
avant, la dizaine juste apres"*, que l'indice dit). Je la retiens. *"900 et 1000"* pour 905 est juste :
1000 est la centaine qui suit 900, et le CE1 va jusqu'a 1000.

**Libelle de competence** : c'est la mention d'`ORD-CE1-5` avec ses mots exacts. Il n'est affiche
nulle part a l'enfant : je l'ai cherche dans la page du niveau CE1 et dans les 140 ecrans de manche
captes, zero occurrence ; le bouton du jeu dit *"Comparer, ranger, encadrer"*. Les accents et l'espace
insecable devant le point-virgule ne sont pas de ma porte (`DEC-0056`) ; les mots sont ceux de la
mention, et c'est ce que je verifie.

#### 4. Niveau reel

Consigne : *"Lis bien les nombres, puis choisis la bonne réponse."* Enonces de 7 a 10 mots, une phrase,
sous `LON-CE1-1` et `LON-CE1-2`. Sur une erreur, l'indice s'affiche sous *"Ce n'est pas ça. Essaie
encore !"* ; a la revelation, la bonne reponse est nommee et l'indice reste. Les indices de comparaison
sont choisis pour leur item, et je les ai verifies un par un : *"Les centaines sont les mêmes"* n'apparait
que sur 347/374, 620/602, 508/580 et 451/415, ou c'est vrai ; *"Compte les chiffres"* sur 999/1000.

**Ce que j'ai regarde et qui n'est pas un defaut.** Trois triplets sont deja ranges dans l'enonce
(*654, 707, 833*, *772, 778, 896*, *827, 828, 1000*) : la bonne reponse y recopie l'enonce. Recopier
l'enonce n'est pas une strategie gagnante sur le paquet : la copie est une proposition sur 10 items, juste
sur 3, distracteur sur 7 (moins qu'au hasard). Les distracteurs d'encadrement sont des intervalles voisins
ou proches, de meme largeur ; certains sont eloignes (*"500 et 600"* pour 738), ce qui rend l'item plus
facile sans le rendre faux.

#### 5. Exactitude, biais et ton

34 reponses exactes, distracteurs distincts de la bonne reponse. Aucun contexte, aucun personnage :
rien a biaiser. Ton neutre.

#### 6. Ce dont je reste la seule garde

`PaquetCe1ComparerRangerTest` porte, d'apres ses noms de methodes (lus, non executes par moi), les
comptes de `RAN-*` et d'`ORD-CE1-1` a `-4` et le choix de l'indice de comparaison selon son item.
**Aucun automate ne verifie** : qu'un distracteur soit une erreur qu'un enfant fait, et que le libelle de
competence ne soit montre a aucun enfant. Ces deux proprietes n'ont ete verifiees qu'ici, en jouant.

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

**Porte technique** : non lancee par moi (le job `build` ferait un `clean` dans un worktree ou `lea-dev`
construit). Ce Done pedagogique suppose la CI verte sur l'arbre fusionne ; `sofia-sm` la verifie a la
fusion.
