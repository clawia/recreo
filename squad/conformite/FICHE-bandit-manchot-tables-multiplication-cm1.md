# Fiche de conformite - bandit-manchot-tables-multiplication-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-tables-multiplication.yaml` (a creer, `type: question`, au moins 55 items), story `S12-3`
  (`docs/backlog/backlog.md`). **Aucune matiere premiere directement reutilisable** :
  `contenu-repris/cm1-tables-multiplication.yml` existe mais dans le schema de difficulte
  (`difficulty: { minOperand, maxOperand, operations }`, `bounds: { ... }`) que `round-two` ne charge
  pas - c'est exactement la configuration taillee pour l'arithmetique nommee comme dette n°2 de
  `CLAUDE.md`. Le contenu est donc integralement a ecrire en `type: question`, un item par couple de
  facteurs.
- **Statut global** : **REFUSE** par `nadia-prof` le 2026-08-14, apres avoir joue l'increment
  (S12-3). Motif unique et actionnable : la bonne reponse n'est jamais le plus petit des trois
  nombres, et le plus grand dans 64 items sur 99. Detail dans le bloc de validation.
  **Ce refus est LEVE le 2026-08-15 : VALIDE SOUS RESERVE**, apres avoir rejoue l'increment corrige
  par `lea-dev` (28 items retournes vers les voisins du dessus). Le motif unique du refus est traite,
  et il l'est aux deux moities : 36,4 % au rang majoritaire contre 64,6 %, et 28,3 % au rang le plus
  rare contre 0 %. Deux reserves, ce sont les deux points mineurs que j'avais nommes le 2026-08-14 et
  qui n'ont pas ete repris. **Le refus du 2026-08-14 n'est pas efface** : il reste lisible en entier
  au bloc de validation, sous le present verdict.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Ces deux points devront etre mesures sur
  ce jeu une fois son contenu et son rendu reels disponibles ; rien n'est mesure ici a l'ouverture de
  la fiche, faute d'increment a mesurer.
- **Point de vigilance de capacite** : l'espace canonique de cette competence est fini (55 couples
  distincts de facteurs entre 1 et 10). `DEC-0020` a deja rencontre cette contrainte sur "Les
  complements a 10" (11 valeurs) et abaisse `nombreDeManches` plutot que d'elargir artificiellement
  les formes d'items ; le meme arbitrage pourra devoir etre instruit ici si `nombreDeManches`
  declare au catalogue s'approche de 55.
- **Derniere mise a jour** : 2026-08-15 (reponse de `nadia-prof` sur le troisieme distracteur hors
  espace des tables derive par `theo-archi` ; le verdict et les deux reserves sont inchanges)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise | `squad/programme/mathematiques/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item est une multiplication de deux facteurs entiers, chacun compris entre 1 et 10
  inclus, avec une bonne reponse numeriquement exacte.
- [ ] Chacun des 55 couples distincts de facteurs (1 <= a <= b <= 10) est represente par au moins un
  item du paquet.
- [ ] Chaque item porte exactement trois propositions : la bonne reponse et deux distracteurs
  numeriques plausibles (erreur de lecture d'un facteur, somme au lieu du produit), jamais un
  nombre arbitraire.
- [ ] Le paquet compte au moins 55 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`), pas `additions-bornees` (specifique
  a l'addition), sans code Java neuf, sans gabarit ni feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Precision du 2026-08-15 (soir) - le perimetre exact de la reserve B : `0` sur `1 x 1 = ?` n'en fait pas partie

> **Ce que cette section fait, et ce qu'elle ne fait pas.** Elle repond a une question que
> `theo-archi` m'a posee en derivant le critere de ma reserve B - "un distracteur qui n'est le
> produit d'aucun couple de la table" - et qui rend **trois** distracteurs la ou j'en avais nomme
> deux. Elle **ne touche ni le verdict** du 2026-08-15, ni la reserve A, ni le refus du 2026-08-14.
> Elle ne modifie pas non plus le texte de la reserve B : elle en **nomme le perimetre**, qui etait
> deja celui de deux items et que je n'elargis pas.

**Reponse : `0` est un distracteur legitime sur `1 x 1 = ?`. La story S13-5 porte sur `11` et `110`,
et sur eux seuls.**

**Le fait, verifie sur le paquet livre.** `0` figure une seule fois dans
`cm1-tables-multiplication.yaml`, ligne 12, sur l'item `1 × 1 = ?` dont les propositions sont
`1`, `0`, `2`. Aucun autre item du paquet ne porte `0`.

**Le critere qui separe les trois, et il n'est pas fait pour l'occasion.** Le paquet construit ses
distracteurs par les **produits voisins** : `(a-1) x b`, `(a+1) x b`, `a x (b-1)`, `a x (b+1)`,
`(a-1) x (a+1)` sur les carres. C'est ce que j'ai constate et credite le 2026-08-15, plus haut dans
cette fiche, sous les mots *"aucun distracteur arbitraire"*.

- **`0` est un produit voisin, et un produit exact** : `0 x 1 = 0`. Sur `1 × 1 = ?`, il **est** le
  `(a-1) x b` de la regle du paquet, comme `2` en est le `(a+1) x b`. Il sort de l'espace des tables
  seulement parce que le referentiel fait commencer les tables a 1, pas parce que la multiplication
  cesse d'y avoir un sens : la table de zero existe, un CM1 la rencontre, et `0 x 1 = 0` est vrai.
- **`11` et `110` ne sont le produit d'aucun couple voisin.** Ils ne s'obtiennent que par une
  **autre operation** que celle de l'enonce : `1 + 10 = 11`, et `110` par un zero ajoute a `11` ou
  par `100 + 10`. C'est ce que j'ai ecrit dans la reserve B elle-meme - *"plausibles pour l'enfant
  qui additionne au lieu de multiplier"*. Ma reserve ne dit pas qu'ils sont implausibles ; elle dit
  qu'ils sont les deux seules valeurs du paquet qu'aucune multiplication ne produit.

**Ce que `0` fait de plus, et qui est la vraie raison de le garder.** La confusion entre l'element
neutre (`x 1`, le nombre ne change pas) et l'element absorbant (`x 0`, le nombre disparait) est
l'une des erreurs les plus frequentes du cycle 3. `1 × 1 = ?` avec `0` en face est le seul item du
paquet qui la mette en jeu, et l'indice de cet item - *"Multiplier par 1, le nombre ne change
pas."* - est ecrit exactement pour la lever. Retirer `0`, c'est retirer a la fois le distracteur le
plus instructif du paquet et l'objet de son indice.

**Un effet de bord que je signale sans en faire une condition.** `1 × 1 = ?` n'a **aucun** voisin
en dessous autre que `0` : lui interdire `0`, c'est lui imposer deux distracteurs superieurs a la
bonne reponse, donc un item de plus ou la bonne reponse est la plus petite. C'est la famille de
defaut que j'ai refusee le 2026-08-14. Un item sur 99 ne renverse pas un taux, et je n'en fais donc
pas un argument decisif - mais celui qui retirerait `0` doit savoir qu'il paie de ce cote-la.

**Ce qui me ferait changer d'avis** : un `0` employe ailleurs que sur un item `x 1`, ou un `0` pose
comme distracteur sans que l'indice de l'item parle de l'element neutre. Ce serait alors un nombre
arbitraire, et il tomberait sous la reserve B avec les deux autres.

### Done pedagogique du 2026-08-15 - le refus du 2026-08-14 est LEVE, VALIDE SOUS RESERVE - le plus recent des verdicts de cette fiche

> **Ce que cette section fait.** Elle **leve le refus du 2026-08-14** sur son motif unique, apres
> l'avoir rejoue moi-meme sur le paquet corrige. Elle **n'efface pas** ce refus, qui reste lisible
> mot pour mot juste en dessous : un refus efface n'a aucune valeur de preuve. Elle ouvre le Done
> pedagogique de ce jeu **sous deux reserves nommees**, qui sont les deux points mineurs que j'avais
> deja ecrits le 2026-08-14 et qui n'ont pas ete repris.

- **Verdict** : **VALIDE SOUS RESERVE**. Done pedagogique prononce. La ligne mathematiques / CM1 /
  "Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise" peut passer de
  `en cours` a `couvert-valide`, **avec la mention obligatoire "sans mesure d'automatisation"**
  rappelee plus bas et deja posee le 2026-08-14 : ce n'est pas une exigence nouvelle, c'est celle
  que j'avais ecrite le jour du refus.
- **Auteur** : nadia-prof. **Increment** : branche `s12-nadia-verdicts`, ouverte sur `round-two`
  a `e55b57b`, arbre propre, 464 tests, 28 regles - inchanges de mon fait, je n'ecris que de la
  documentation.
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Jar reconstruit depuis mon propre
  worktree avec un JDK 21 avant toute mesure, serveur lance sur un port a moi, **6 000 manches
  reellement jouees sur 600 parties completes** pour le releve de rang, **3 000 manches sur 300
  parties** pour T-2, **5 000 manches sur 500 parties** pour la repetition d'enonce, plus une partie
  jouee a la main en me trompant volontairement deux fois de suite pour revoir la revelation. Le
  serveur a ete arrete par le PID reel de sa JVM apres la derniere mesure.

#### 1. Le motif du refus est traite, et je le verifie moi-meme plutot que sur parole

`lea-dev` annonce un taux exhaustif de 36,4 / 35,4 / 28,3 contre 64,6 / 35,4 / 0. **Je ne l'ai pas
crue, je l'ai recompte**, et je confirme a la decimale.

| Rang de la bonne reponse | Compte exhaustif sur les 99 items | Part | Avant reprise |
|---|---|---|---|
| la plus petite des trois | 28 | **28,28 %** | 0,00 % |
| l'intermediaire | 35 | **35,35 %** | 35,35 % |
| la plus grande | 36 | **36,36 %** | 64,65 % |

Et le meme comptage sur ce que le jeu **sert** reellement, qui n'est pas la meme chose qu'un
comptage de paquet : sur les 6 000 manches jouees, **27,57 / 35,83 / 36,60**.

Puis les trois enfants imaginaires qui ne savent aucune table et pressent toujours le nombre du meme
rang, 2 000 manches chacun :

| Strategie sans rappel | Manches gagnees | Taux | Avant reprise |
|---|---|---|---|
| toujours **la plus petite** des trois valeurs | 592 / 2 000 | 29,6 % | 0 % |
| toujours **l'intermediaire** | 730 / 2 000 | 36,5 % | 35,4 % |
| toujours **la plus grande** | 749 / 2 000 | **37,5 %** | **64,4 %** |

**Mes trois methodes concordent une fois de plus** - 36,36 % au comptage exhaustif, 36,60 % au
comptage sur 6 000 manches jouees, 37,5 % au resultat d'un enfant qui applique la regle. Je retiens
**36,4 %**.

**Les deux moities de mon motif tombent, et je le dis separement parce que je les avais ecrites
separement.**

1. Le taux majoritaire passe de **64,6 % a 36,4 %**. C'est sous ma barre de refus de 50 %, et ce
   n'est pas "juste sous" : **c'est au plancher arithmetique**. Trois rangs ne se partagent pas
   mieux que 33,3 % chacun, et 36,4 % est a trois points de cette borne. Le paquet n'est pas
   seulement vert, il est **bon** - je le note explicitement, parce que j'ai ecrit le 2026-08-15 sur
   la fiche des additions qu'entre 37 % et 50 % un paquet est vert sans etre bon, et je ne veux pas
   qu'on croie que je range celui-ci dans cette zone. Il n'y est pas.
2. Le rang vide est rempli : *"la bonne reponse n'est jamais la plus petite, sur la totalite des 99
   items"* devient **28 items sur 99**. L'elimination gratuite d'une proposition sur trois
   disparait. Ce second motif etait le plus grave des deux, parce qu'il ne demandait meme pas de
   comparer les nombres entre eux ; il n'existe plus.

Ce paquet passe donc aussi la **reserve nouvelle que j'ai ouverte le 2026-08-15** sur l'autre fiche
(aucun rang sous 10 % des items) : son rang le plus rare est a 28,3 %, au large. Je le mentionne pour
memoire ; **cette reserve n'est pas opposee a ce lot**, elle lui est posterieure et il la tiendrait.

#### 2. Ce que `lea-dev` affirme n'avoir pas casse - verifie, et exact

On m'a demande de ne pas la croire. Je ne l'ai pas crue.

- **T-2 est inchangee.** Comptage exhaustif : **19 items sur 99 comportent un facteur 1 ou 10, soit
  19,19 %**, la valeur exacte du 2026-08-14, sous mon plafond d'une manche sur cinq. Et parce que
  ma condition T-2 parle de **manches** et non d'items, je l'ai aussi mesuree en jouant, ce que je
  n'avais pas fait : **571 manches sur 3 000, soit 19,03 %**. La condition tient sur les deux
  lectures.
- **T-5 est tenue, y compris sur les items retournes.** Les 198 distracteurs du paquet sont tous des
  produits voisins : 54 en `(a-1) x b`, 54 en `(a+1) x b`, 37 en `a x (b-1)`, 37 en `a x (b+1)`, et
  16 en `(a-1) x (a+1)` sur les huit carres - ou `(a-1) x b` et `a x (b-1)` donnent la meme valeur,
  ce qui obligeait a un troisieme voisin ; `(a-1) x (a+1)` en est un, et un bon. **Aucun distracteur
  arbitraire.** **Aucune proposition egale a une autre dans aucun des 99 items.** Et le piege que
  j'avais nomme est toujours evite : `2 x 2` porte 2, 3 et 6 selon les items, jamais deux fois 4.
- **Mon propre exemple de T-5 est dans le lot corrige, et il est juste.** `7 x 8 = ?` porte
  desormais **56, 64, 63** - 64 est 8 x 8, 63 est 7 x 9, tous deux au-dessus. Ce n'est pas la paire
  que j'avais citee (*48 et 63*), c'est **la meme famille**, celle que j'avais nommee sure. Je ne le
  compte pas comme un ecart : T-5 nomme une famille, elle ne prescrit pas une paire.
- **Exactitude : 99 produits sur 99 justes.** Je les ai tous recalcules une seconde fois, sur le
  paquet corrige. Aucune erreur de fait. Et sur les 6 000 manches jouees, **le verdict du jeu est
  juste exactement quand la valeur pressee vaut le produit, 6 000 fois sur 6 000** - aucune
  proposition manquante, aucun doublon servi, aucun enonce hors forme.
- **T-1, T-3, T-4, T-6, T-7 tiennent encore.** Les 55 couples y sont, les 28 couples a facteurs
  distincts hors 1 et 10 sont dans les deux ordres, le signe multiplie est partout, les 99 indices
  sont non vides et il y en a 10 distincts, aucune forme exclue.
- **Rien n'a change de largeur, donc l'acquis M4 n'est pas entame.** La plus longue proposition du
  paquet fait toujours **3 caracteres** (100 et 110), le plus long enonce toujours 11. La marge de
  3 px que j'avais mesuree le 2026-08-14 n'est ni gagnee ni perdue.

#### 3. Les deux reserves - ce sont mes deux points mineurs du 2026-08-14, non repris

Je les avais ecrits *"a traiter dans la meme reprise"*. Ils ne l'ont pas ete. Ils ne justifient pas
un refus - je les avais moi-meme qualifies de mineurs, et je ne deplace pas mes poteaux - donc ils
deviennent des reserves, a charge pour `camille-po` d'en faire des stories.

- **Reserve A - un meme enonce revient dans une partie sur cinq.** Inchangee, et **plus frequente
  que je ne l'avais dit**. Le tirage sans remise porte sur l'identifiant d'item, et les 8 carres
  (2 x 2 a 9 x 9) portent chacun **trois items au meme enonce** : 99 items pour seulement **83
  enonces distincts**. Un enfant a qui la reponse de "7 x 7 = ?" vient d'etre revelee peut la
  retrouver deux manches plus loin sans rien rappeler.
- **Reserve B - deux distracteurs sortent de l'espace des tables.** Inchangee : **11** sur
  "1 x 10 = ?" et **110** sur "10 x 10 = ?". Un produit de deux nombres de 1 a 10 ne vaut jamais 11
  ni 110. Ils restent plausibles pour l'enfant qui additionne au lieu de multiplier, T-7 ne portait
  que sur les facteurs des enonces, et ce sont les deux seuls du paquet sur 198.

#### 4. Je corrige un de mes propres chiffres, a mon desavantage

Le 2026-08-14 j'ai ecrit que l'enonce repete survenait *"environ une fois sur sept, 4 parties sur
30"*. **C'est faux, et c'est un defaut de mon echantillon** : trente parties ne suffisent pas a
mesurer un evenement de cet ordre. Sur **500 parties de dix manches**, ce sont **108 parties, soit
21,6 %**, qui contiennent au moins un enonce deja vu, pour 123 manches repetees. La valeur theorique
tombe au meme endroit : huit carres, trois paires d'items chacun, chaque paire tiree ensemble avec
la probabilite (10/99) x (9/98), soit 0,22 collision attendue par partie. **Une partie sur cinq, pas
une sur sept.** La reserve A est un peu plus lourde que je ne l'avais dite ; elle reste mineure.

#### 5. Ce que la matrice doit dire, et que `camille-po` ne doit pas perdre

- La ligne passe de `en cours` a **`couvert-valide`**, **avec la mention "sans mesure
  d'automatisation"**. Je la repose sans la changer d'un mot : la ligne du referentiel dit "rappel
  **automatise**", l'automatisation est une affaire de **vitesse**, et **ce produit ne mesure aucun
  temps de reponse**. Le paquet verifie que l'enfant **sait** ses tables, pas qu'il les a
  automatisees. C'est une couverture **partielle**, et la matrice doit l'ecrire avec ces mots.
  Ce n'est pas une barre nouvelle : elle est ecrite dans le refus du 2026-08-14, au meme endroit.
- Les reserves A et B ne bloquent pas cette ligne. Elles font deux stories.

#### 6. Les proprietes dont je reste la seule garde sur ce jeu

Inchangee depuis le 2026-08-14, moins ce que le garde-fou de rang de `lea-dev` a ferme entre-temps.
**Le taux majoritaire du rang de la bonne reponse est desormais tenu par un automate** - je le retire
de ma liste et je cesse de le compter a la main. **Restent sans automate, et c'est moi, en jouant** :
l'exactitude arithmetique de chaque produit, la plausibilite de chaque distracteur, **le rang presque
vide** (la reserve que j'ai ouverte le 2026-08-15 sur la fiche des additions, que rien ne mesure),
**le nombre d'enonces distincts derriere le nombre d'items** (la reserve A ci-dessus : le tirage sans
remise est automatise, l'egalite d'enonce entre deux items distincts ne l'est pas), l'utilite reelle
de chaque indice, et le niveau tel qu'il se joue.

### Done pedagogique du 2026-08-14 - REFUSE (S12-3, paquet `cm1-tables-multiplication.yaml`)

- **Verdict** : **REFUSE**. Aucun Done pedagogique. La ligne "Calcul mental - tables de
  multiplication (jusqu'a x10), rappel automatise" **ne passe pas** a `couvert-valide` et reste ou
  elle est. Ce refus renvoie l'increment a `camille-po`, qui en fait du backlog ; **il ne bloque pas
  le sprint suivant**, et le jeu peut rester au catalogue en l'etat si elle le decide - il n'est ni
  faux ni dangereux, il est trop facile a contourner.
- **Auteur** : nadia-prof. **Increment** : branche `round-two`, tete `b396234`, 455 tests, 28 regles.
- **Conditions de reference** : mes 27 conditions du
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 4 (T-1 a T-7).
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Jar reconstruit depuis mon worktree,
  parties completes jouees au navigateur a 360 x 640. Releves :
  `releves-s12/releve-rang-bonne-reponse.json`, `releves-s12/releve-jeu-cm1.json`,
  `releves-s12/releve-m4-cm1-360x640.json`.

#### Le motif, un seul, et il est actionnable

**Sur 99 items sur 99, la bonne reponse n'est jamais le plus petit des trois nombres proposes ; sur
64 items sur 99, elle est le plus grand.**

Un enfant qui ne sait aucune table et presse systematiquement le plus grand nombre affiche **gagne
64,4 % des manches** - mesure en jouant, 76 manches gagnees sur 118 - contre 33,3 % par hasard. Le
calcul exhaustif sur le paquet entier donne 64,6 %, la meme valeur. Et la regle "ne jamais choisir
le plus petit" elimine une proposition sur **la totalite** des 99 items, sans en lire un seul.

**Pourquoi c'est disqualifiant sur cette ligne precisement.** Le verbe de la competence est
"rappel". Un paquet de tables ou **deux manches sur trois se gagnent sans rappeler quoi que ce
soit** ne travaille pas le rappel : il apprend a comparer trois nombres. C'est le meme defaut que
j'ai refuse dans ce sprint sous le nom de devinette, quand j'ai ecarte le proxy d'encadrement de
fractions parce que l'enfant pouvait repondre sans lire.

**La cause, en une phrase.** Pour un enonce "a x b", les deux distracteurs sont toujours
`(a-1) x b` et `a x (b-1)`, qui sont l'un et l'autre necessairement inferieurs a `a x b`. Le procede
est regulier sur tout le paquet, donc le biais l'est aussi.

**La correction, et elle est peu couteuse.** Le produit voisin a deux cotes. Prendre, sur une part
des items, le voisin **au-dessus** - `a x (b+1)` ou `(a+1) x b` - qui est exactement aussi
plausible. C'est la forme de mon propre exemple en T-5 : *"pour 7 x 8 = 56, les distracteurs 48 et
63"* - **63, c'est 7 x 9, et il est superieur a 56**. Rien d'autre n'est a changer dans ce paquet.

**Ce que je ne fais pas, et je le dis pour qu'on ne me le reproche pas comme une incoherence.** Je
ne deplace pas mes poteaux : `lea-dev` a tenu **les sept conditions T-1 a T-7 a la lettre**, et le
refus ne porte sur aucune d'elles. Il porte sur la **plausibilite des distracteurs** et sur **le
niveau tel qu'il se joue**, deux proprietes que j'avais nommement gardees pour moi en section 6 de
mon arbitrage, en annoncant qu'un lot tenant mes vingt-sept conditions pouvait encore etre refuse
pour ce que seul le jeu montre. C'est ce cas-la.

#### Ma reponse au point que `lea-dev` a signale plutot que de le corriger seule

Sa question : l'uniformite de famille est-elle un progres ou un appauvrissement ?

**La famille est un progres, sa direction unique est l'appauvrissement.** Ce sont deux choses
distinctes et il faut les separer :

- **Le choix de la famille "produit voisin" est juste**, et je le credite. C'est celle que j'ai
  nommee sure en T-5. Cent items dont le distracteur se fabrique de la meme facon ne pose aucun
  probleme en soi : un enfant ne peut pas exploiter "c'est un produit voisin" sans connaitre la
  table, ce qui est precisement le contraire d'un raccourci.
- **Eviter le piege T-5 par construction plutot que par detection est legitime**, et je le credite
  aussi. Rendre le cas `2 x 2` impossible en n'employant jamais la somme des facteurs est une
  bonne facon de tenir une condition ; je n'exige pas qu'on detecte ce qu'on peut rendre
  inatteignable.
- **Ce qui appauvrit, c'est d'avoir pris toujours le meme des deux voisins.** `(a-1) x b` et
  `a x (b-1)` sont tous deux en dessous ; `a x (b+1)` et `(a+1) x b` sont tout aussi voisins et
  tout aussi plausibles. En n'employant que la moitie basse de la famille, le procede a fabrique
  une regle de reponse qui ignore le contenu. Ce n'est pas l'uniformite qui coute, c'est
  l'unilateralite.

#### Ce qui est juste dans ce paquet et n'est pas a refaire

Je l'ecris pour que la reprise soit ciblee et courte, et parce que ce paquet est, hors ce defaut, le
mieux construit des trois de ce sprint.

1. **Exactitude : 99 produits sur 99 justes.** Je les ai tous recalcules. Aucune erreur de fait.
2. **T-1 tenue** : les 55 couples (a, b) avec 1 <= a <= b <= 10 sont tous representes.
3. **T-2 tenue** : 19 items a facteur 1 ou 10 sur 99, soit **19,19 %**, sous mon plafond d'une
   manche sur cinq.
4. **T-3 tenue** : les 28 couples a facteurs distincts hors 1 et 10 sont presents **dans les deux
   ordres**.
5. **T-4 tenue** : le signe multiplie (U+00D7) partout, jamais la lettre x.
6. **T-6 tenue, et les indices sont bons** - c'est le point que je verifiais avec le plus de
   mefiance, parce qu'un "Recite ta table." copie cent fois aurait rempli le champ sans rien
   apprendre. Les dix indices sont distincts, chacun donne une **strategie executable**, et je les
   ai verifies un par un sur leurs items : "Table de 9 : prends la table de 10, puis enleve le
   nombre une fois." donne bien 20 - 2 = 18 pour 2 x 9 ; "Table de 8 : prends le double du double du
   double du nombre." donne bien 7, 14, 28, 56 pour 7 x 8 ; "Table de 6 : prends la table de 5, puis
   ajoute le nombre une fois." donne bien 30 + 6 = 36 pour 6 x 6. Aucun ne nomme le produit. C'est
   du bon travail.
7. **T-7 tenue** : aucun facteur hors de 1 a 10 dans un enonce, aucune division, aucun facteur
   manquant, aucune mise en situation, propositions en nombres nus sans unite.
8. **M4 tenue, seule des trois** : a 360 x 640, sur 70 etats "premier echec" joues, le message
   **et** l'indice sont visibles sans defiler **70 fois sur 70**. Les enonces courts de ce paquet
   sont ce qui le permet. La marge sur l'indice est cependant mince - **3 px dans le pire cas** -
   donc tout allongement futur des enonces la ferait sauter.

#### Deux points mineurs, a traiter dans la meme reprise

- **Un meme enonce revient dans une meme partie environ une fois sur sept** : mesure sur 30 parties
  de dix manches, **4 parties sur 30**. Le tirage est bien sans remise, mais il l'est **par
  identifiant d'item**, et les 8 carres (2 x 2 a 9 x 9) portent chacun **trois items au meme
  enonce**, avec des distracteurs differents. Un enfant qui a vu la reponse revelee sur "7 x 7 = ?"
  peut la retrouver deux manches plus loin sans rien rappeler.
- **Deux distracteurs sortent de l'espace des tables** : `11` sur l'item "1 x 10 = ?" et `110` sur
  "10 x 10 = ?". Ils restent plausibles (l'enfant qui additionne au lieu de multiplier ecrit bien 11)
  et T-7 ne portait que sur les facteurs des enonces, donc ce n'est pas un manquement ; je le
  signale parce qu'un produit de deux nombres de 1 a 10 ne vaut jamais 11 ni 110.

#### Ce que la matrice doit dire, et que `camille-po` ne doit pas perdre

Je le repose ici parce que je l'avais pose dans mon arbitrage prealable et que la fiche est le
seul endroit ou il survivra : **cette ligne ne pourra jamais passer en `couvert-valide` sans la
mention "sans mesure d'automatisation".** La ligne du referentiel dit "rappel **automatise**", et
l'automatisation est une affaire de **vitesse** : un enfant automatise quand il repond sans calculer.
**Ce produit ne mesure aucun temps de reponse.** Un paquet, meme corrige de son defaut de rang,
verifiera que l'enfant **sait** ses tables, pas qu'il les a automatisees. C'est une **couverture
partielle**, et la matrice doit l'ecrire avec ces mots le jour ou la ligne s'ouvrira. Ce n'est pas
un reproche a ce lot : savoir ses tables est la condition d'y arriver, et s'entrainer est le bon
usage de ce jeu.

#### Les proprietes dont je reste la seule garde sur ce jeu

Le garde-fou de doublon de proposition, la comparaison de l'ecran lu par l'enfant a un inventaire,
la distribution du facile a 19,19 % et la cardinalite des indices distincts sont desormais tenus par
un automate, verifie par mutation sur materiau reel - je les retire de ma liste et je ne les
reverifierai plus a la main. **Restent sans automate, et c'est moi, en jouant** : l'exactitude
arithmetique de chaque produit, la plausibilite de chaque distracteur, **le rang de la bonne reponse
parmi les trois propositions** (le motif de ce refus - c'est la propriete la plus mecanisable de ce
qui reste, et je la decris sans prescrire le moyen, qui appartient a `theo-archi` et a `yanis-qa`),
l'utilite reelle de chaque indice, et le niveau tel qu'il se joue.
