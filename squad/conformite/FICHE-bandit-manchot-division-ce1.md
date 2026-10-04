# Fiche de conformite - bandit-manchot-division-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-division.yaml` (21 items,
  type `question`), ecrit par `lea-dev` (commit `04e05532`, fusion `2e712d77`).
- **Entree de catalogue** : `bandit-manchot-division-ce1`, titre servi *"La division"*, consigne
  servie *"Partage la quantité, ou trouve le nombre de groupes."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-18`. **Story** : `S44-MAT-DIVISION` (`docs/backlog/backlog.md`,
  section "Sprint 44", alignee par `camille-po` en `8053bfc3`).
- **Conditions opposables** : `DIV-CE1-1` a `DIV-CE1-6`, dans leur version amendee du 2026-09-25
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, sections 4, 11.2 et
  11.7).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-09-30 par `nadia-prof` (s44,
  instance unique). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-10-04 (second passage, s50) : **VALIDE**. `DIV-CE1-R1` levee. Le
  verdict du 2026-09-30 reste ecrit tel quel.

---

## Bloc de validation pedagogique

### Verdict du 2026-09-30 (`nadia-prof`, s44, instance unique) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `MAT-CE1-18` a `couvert-valide`, sans
mention de couverture partielle : les deux sens du libelle sont servis. La reserve `DIV-CE1-R1` ne
bloque pas le mouvement de ligne.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Meme jar que pour les deux autres lots (`round-two` a `bc1cf4b0`,
JDK 21 de la toolchain). **Quatorze parties completes** de dix manches, jusqu'a ce que les
vingt et un items aient tous ete tires (le dernier, *"12 billes ... entre 3 enfants"*, est sorti a
la dixieme partie). Sur chaque partie, un tiers des manches en repondant juste, un tiers apres une
erreur, un tiers apres trois erreurs jusqu'a la revelation. **Les vingt et un quotients que j'ai
recalcules a la main sont ceux que le jeu credite.** L'indice s'affiche des la premiere erreur et
reste a la revelation.

#### 2. Ancrage au programme

`MAT-CE1-18` : *"Comprendre le sens de la division en résolvant des problèmes de partage et de
groupement (sans technique opératoire posée)."* **C'est bien cette competence** : chaque item est un
probleme en mots, aucun ne pose une operation, aucun ne recite une table.

#### 3. `DIV-CE1-1` a `DIV-CE1-6`, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `DIV-CE1-1` | **oui** | Dividendes de 6 a 60, diviseurs de 2 a 10, vingt et une divisions exactes |
| `DIV-CE1-2` | **oui** | Partage (*"partagé(e)s entre"*) : 11 items ; groupement (*"rangé(e)s par"*, *"en bouquets de"*) : 10 items. Les deux au-dessus du tiers (7). La marque de sens dit le sens reel de chaque item, lu item par item |
| `DIV-CE1-3` | **oui** | Aucun signe operatoire ni `=` dans aucun texte. Le seul tiret des items est celui de *"a-t-il"* |
| `DIV-CE1-4` | **oui** | Diviseur 2 sur 6 items (1 a 6), sous le tiers. Aucun *moitié*. Chacun est une situation (des amis, des trousses), jamais *"Moitié de n = ?"* |
| `DIV-CE1-5` | **oui** | Chaque item porte au moins une erreur identifiable parmi `a x b`, `a - b`, `b`. Distracteurs entiers, positifs, distincts. Aucun item de la forme `a = b x b`, le cas que je m'etais engagee a regarder |
| `DIV-CE1-6`, point 1 | **oui** | Aucune `illustration`, aucun `tableauSupport` : rien a l'ecran que le texte, sur les quatorze parties |
| `DIV-CE1-6`, point 2 | **oui** | Ni `explication`, ni `texteSupport`, ni `texteAVoixHaute`. Les indices de groupement comptent de *n* en *n* en mots (*"Compte de 8 en 8 jusqu'à 48."*), ce que la clause permet |
| `DIV-CE1-6`, point 3 | **oui** | Les vingt et un dividendes sont suivis du nom d'objets comptes : crayons, images, perles, bonbons, fleurs, gâteaux, billes, cartes, autocollants, timbres, pommes, œufs, chaises, biscuits, coquillages, jetons |

**La frontiere avec `MAT-CE1-06` est tenue par construction, et je l'ai verifiee sur les items
servis** : aucun reseau dessine (G1), aucune addition reiteree ecrite (G2), aucun cas nu
(*"combien de fois 3 dans 12"*). Tous les groupements sont en mots, la seule forme que ma table de
la section 11.2 range sous `MAT-CE1-18`. Aucun paquet de `MAT-CE1-06` n'existe encore : le jour ou
il sera ecrit, c'est a lui de tenir l'autre cote (`MUL-CE1-2`).

#### 4. Niveau reel

Enonces de 11 a 14 mots, deux phrases, sous le plafond de `ADR-0101`. Consigne courte. Les indices
de groupement (*"Compte de 3 en 3 jusqu'à 21."*) donnent une methode que l'enfant peut suivre ;
ceux de partage (*"Distribue une perle à la fois à chacun des neuf enfants."*) donnent le geste
meme du partage, lent sans objets sur les grands dividendes, mais c'est le sens que la ligne
enseigne, et je l'accepte.

#### 5. Exactitude, biais et ton

Vingt et un quotients exacts. Les distracteurs `b` sont plausibles dans la phrase servie :
*"14 images sont partagées entre 2 amis"*, un enfant qui confond les deux sens repond `2`. Les
distracteurs proches (`5` pour `6`, `8` pour `9`) sont des erreurs de comptage reelles. Aucun biais,
aucun ton condescendant.

#### 6. La reserve

> **`DIV-CE1-R1` - Huit items n'ont pas l'erreur propre a la competence, et portent a la place un
> produit qu'aucun enfant de CE1 ne peut avoir calcule.** Items 8, 10, 11, 13, 14, 19, 20, 21 :
> distracteurs `a - b` et `a x b`, soit `10`/`75`, `18`/`243`, `24`/`180`, `32`/`320`,
> `36`/`405`, `40`/`384`, `45`/`486`, `50`/`600`. Le diviseur recopie `b` y est absent, alors que
> c'est l'erreur que j'avais nommee *"specifique de cette competence"* (section 4, motif du
> troisieme cas). Et le produit ne se trompe pas : un enfant de CE1 ne calcule pas `48 x 8` ; il
> ecarte `384` parce qu'il y a plus de boites que de biscuits, et l'item revient a deux
> propositions.
>
> **Correction a faire** : sur ces huit items, remplacer le distracteur `a x b` par `b` (5, 9, 6,
> 8, 9, 8, 9, 10). La condition reste tenue, `a - b` restant present.
>
> **Ce n'est pas un durcissement** : `DIV-CE1-5` admet le produit sans borne, et le paquet la
> tient. C'est une reserve neuve et datee, trouvee en jouant.
>
> **Pourquoi elle n'empeche pas `couvert-valide`** : treize items sur vingt et un portent `b`, les
> huit autres restent des partages et des groupements justes, et l'enfant y travaille le sens meme
> quand il ecarte le produit.

#### 7. Ce dont je reste la seule garde

`PaquetCe1DivisionTest` tient `DIV-CE1-1` a `DIV-CE1-5`, les points 1 et 2 de `DIV-CE1-6`, et le
point 3 sous la forme "dividende suivi d'un mot". **Aucun automate ne verifie** : que la marque de
sens dise le sens reel de l'item, que le mot qui suit le dividende nomme des objets comptables et
non une unite ou un adjectif, qu'un distracteur soit une erreur qu'un enfant de CE1 peut commettre
(`DIV-CE1-R1`). Ces trois proprietes n'ont ete verifiees qu'ici.

---

### Second passage du 2026-10-04 (`nadia-prof`, s50, instance A) : **VALIDE**, `DIV-CE1-R1` levee

**Je ne rouvre pas le paquet entier** : je juge le seul point corrige par `lea-dev` (`c4250c2d`).
`MAT-CE1-18` reste `couvert-valide` ; la reserve tombe.

#### 1. Ce que j'ai fait

Arbre `round-two` a `655b319b`. Jar construit par
`bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am package -DskipTests` (JDK 21.0.10), YAML du jar
identique octet pour octet a l'arbre, lance sur un port dedie servi par mon propre processus. Aucun
job de la CI lance par moi. **Rendu non vu au navigateur** : appels HTTP reels, HTML servi lu.
`git diff c4250c2d~1 c4250c2d` sur le YAML : seuls les huit distracteurs vises ont bouge.

**Trente parties completes de dix manches** (une juste, une avec une erreur par manche, une jusqu'a
la revelation, vingt-sept en melant les trois au hasard). **Les 21 items tires** ; les huit items
vises de 11 a 18 fois chacun, chacun avec des erreurs (16 a 28) et au moins quatre revelations.

#### 2. La barre ecrite le 2026-09-30, contre ce qui est servi

| Item | Enonce servi | Quotient | Distracteurs servis | `b` attendu |
|---|---|---|---|---|
| 8 | *15 images ... entre 5 amis* | 3 | `10`, `5` | 5 |
| 10 | *27 autocollants ... entre 9 enfants* | 3 | `18`, `9` | 9 |
| 11 | *30 billes ... entre 6 amis* | 5 | `24`, `6` | 6 |
| 13 | *40 timbres ... entre 8 collectionneurs* | 5 | `32`, `8` | 8 |
| 14 | *45 perles ... entre 9 enfants* | 5 | `36`, `9` | 9 |
| 19 | *48 biscuits ... par 8* | 6 | `40`, `8` | 8 |
| 20 | *54 coquillages ... par 9* | 6 | `45`, `9` | 9 |
| 21 | *60 jetons ... par 10* | 6 | `50`, `10` | 10 |

**Tenue sur les huit items** : le produit `a x b` a disparu, le diviseur recopie `b` est servi, et
`a - b` reste present, donc `DIV-CE1-5` reste tenue. Les huit quotients sont ceux que le jeu credite.
Joue : sur *"40 timbres sont partagés entre 8 collectionneurs"*, presser `8` recoit *"Ce n'est pas
ça."* puis l'indice *"Distribue un timbre à la fois à chacun des huit collectionneurs."* ; c'est
l'erreur de l'enfant qui confond le nombre de parts et la taille d'une part, celle que la reserve
demandait. Sur les 21 items, `b` figure desormais parmi les distracteurs, et
`PaquetCe1DivisionTest` le verifie (Q2, acceptee le 2026-10-03).

#### 3. Ce dont je reste la seule garde

Inchange (section 7), moins `DIV-CE1-R1`, que le test du paquet tient desormais. Restent a moi seule :
que la marque de sens dise le sens reel de l'item, que le mot qui suit le dividende nomme des objets
comptables.

**Identifiants** : aucun neuf, aucun renomme. `DIV-CE1-R1` levee.

**Signe** : `nadia-prof`, s50, instance A, 2026-10-04.
