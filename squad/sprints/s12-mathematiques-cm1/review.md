# Review du sprint 12 - mathematiques CM1

## Done pedagogique - nadia-prof, 2026-08-14

Trois lignes de mathematiques CM1 ouvertes par `DEC-0029`, trois paquets livres, trois verdicts.
J'ai **joue les trois increments** : jar reconstruit depuis mon propre worktree avec un JDK 21,
application lancee, parties completes au navigateur a 360 x 640, manches justes et manches fausses,
trois echecs jusqu'a la revelation. Ce n'est pas un audit degrade.

Les developpeurs avaient ecrit contre mes 27 conditions, rendues avant leur code
(`squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`). Je n'ai pas reverifie a la main
ce qu'un automate garde desormais : absence de proposition en double, inventaire du texte lu par
l'enfant, part du facile a 19,19 %, cardinalite des indices distincts. J'ai porte mon audit sur ce
qui reste a moi seule - l'exactitude arithmetique, la plausibilite des distracteurs, l'utilite de
l'indice, et le niveau, qui se joue et ne se lit pas.

| Story | Paquet | Verdict | Ligne de matrice |
|---|---|---|---|
| S12-1 | `cm1-perimetre.yaml` (22 items, `marc-dev`) | **VALIDE SOUS RESERVE** | peut passer a `couvert-valide`, sans mention |
| S12-2 | `cm1-mesures.yaml` (24 items, `marc-dev`) | **VALIDE SOUS RESERVE** | peut passer a `couvert-valide`, sans mention |
| S12-3 | `cm1-tables-multiplication.yaml` (99 items, `lea-dev`) | **REFUSE** | **ne bouge pas** |

Les verdicts complets, avec leurs motifs et leurs reserves, sont dans les blocs de validation de
`squad/conformite/FICHE-bandit-manchot-perimetre-cm1.md`,
`FICHE-bandit-manchot-conversions-unites-cm1.md` et
`FICHE-bandit-manchot-tables-multiplication-cm1.md`. Releves dans `squad/conformite/releves-s12/`.

### Ce qui est juste dans les trois paquets

**Aucune erreur de fait, nulle part.** J'ai recalcule les 22 perimetres, aires et moities, les 24
conversions et les 99 produits : **145 items, 145 exacts**. C'est la propriete qu'aucun automate ne
tient et c'est celle qui pouvait tout faire tomber ; elle est tenue.

Les pieges que j'avais nommes sont tous evites : le carre de cote 4 et le rectangle 3 x 6 sont
absents du paquet de perimetre ; aucun decimal nulle part ; la comparaison de grandeurs, que
`DEC-0029` n'avait pas vue, n'a pas ete reprise dans les mesures ; le cas `2 x 2` avec la somme des
facteurs est impossible par construction dans les tables. Les indices sont des methodes executables,
pas des slogans - je les ai lus apres des echecs reels, et ceux des tables, que je verifiais avec le
plus de mefiance, sont bons un par un.

### Le motif du refus, en une phrase

Sur `cm1-tables-multiplication.yaml`, **la bonne reponse n'est jamais le plus petit des trois nombres
proposes, sur 99 items sur 99, et elle est le plus grand sur 64**. Un enfant qui ne sait aucune table
et presse le plus grand nombre gagne **64,4 % des manches** - mesure en jouant, 76 sur 118 - contre
33,3 % par hasard. Sur une ligne dont le verbe est "rappel", deux manches sur trois se gagnent sans
rappeler quoi que ce soit.

La cause : pour "a x b", les deux distracteurs sont toujours `(a-1) x b` et `a x (b-1)`, tous deux
necessairement inferieurs au produit. La correction est peu couteuse : prendre, sur une part des
items, le voisin **au-dessus**, `a x (b+1)` ou `(a+1) x b`, aussi plausible - c'est la forme de mon
propre exemple en T-5, ou 63 (= 7 x 9) accompagnait 48 pour 7 x 8. Rien d'autre n'est a changer.

`lea-dev` a tenu **les sept conditions T-1 a T-7 a la lettre**. Le refus ne porte sur aucune d'elles :
il porte sur deux proprietes que j'avais nommement gardees pour moi, la plausibilite des distracteurs
et le niveau tel qu'il se joue. J'avais ecrit qu'un lot tenant mes vingt-sept conditions pouvait
encore etre refuse pour ce que seul le jeu montre ; c'est ce cas-la.

### Les deux points que les developpeurs ont signales plutot que de les corriger seuls

Tous les deux ont eu raison de me les renvoyer, et je reponds sur les deux.

**`marc-dev`, la plausibilite des distracteurs de mesures : non, `6 000 000 kg` ne teste rien.** Un
distracteur qu'aucun enfant ne choisirait est un remplissage, et l'item devient une question a deux
choix. **12 items sur 24** portent un distracteur valant de 10 000 a 1 000 000 fois la bonne
reponse. Mais **C-7 les admettait par sa lettre** : elle nommait la nature de l'erreur, pas son
ordre de grandeur. `marc-dev` n'a pas devie, ma condition etait courte, et j'avais pose exactement
ce principe deux sections plus loin en T-5 sans penser a le reporter. Reserve neuve et datee, pas
durcissement. Correction : garder les deux erreurs de facteur a un ou deux crans (60 kg et 600 kg
pour "6000 g, combien de kg ?").

**`lea-dev`, l'uniformite de famille : la famille est un progres, sa direction unique est
l'appauvrissement.** Le choix du produit voisin est le bon, et eviter le piege T-5 par construction
plutot que par detection est legitime - je credite les deux. Ce qui appauvrit n'est pas que cent
items se fabriquent de la meme facon : un enfant ne peut pas exploiter "c'est un produit voisin"
sans connaitre sa table. Ce qui appauvrit, c'est de n'avoir jamais pris que la moitie basse de la
famille.

### Deux corrections de mes propres constats, avant de m'appuyer dessus

1. **P-6 fabrique le defaut qu'elle etait censee eviter.** Ma condition nommait deux familles de
   distracteurs et interdisait les autres : l'aire et la moitie du perimetre. Or la moitie est
   toujours inferieure au perimetre et l'aire lui est superieure des que les cotes depassent 4 : la
   bonne reponse tombe **au milieu 18 fois sur 20**, et un enfant qui presse toujours le nombre du
   milieu reussit **90 %** des manches - plus que sur les tables que je refuse. Je ne refuse pas
   `marc-dev` pour avoir applique ma condition ; ce serait deplacer mes poteaux sur son travail. Les
   deux verdicts different parce que la marge de manoeuvre differait : sur les tables, le voisin
   du dessus etait disponible, aussi plausible, et deja illustre par mon propre exemple ; sur le
   perimetre, presque toutes les erreurs reelles sont inferieures au perimetre et l'aire est la
   seule famille qui passe au-dessus. Le remede n'y est donc pas un distracteur neuf mais plus de
   petites figures, dont l'aire tombe sous le perimetre. **Le carre de cote 2 est a exclure** : aire
   4 et moitie 4, deux propositions egales.
2. **J'ai d'abord mesure un catalogue a neuf jeux, sans "Les tables de multiplication".** C'etait un
   serveur laisse par une execution perdue de ce sprint, pas l'increment. Mon propre jar en sert
   dix. Le constat est retire avant d'avoir servi a quoi que ce soit.

### Ce que je n'ai pas ferme, et que personne ne doit prendre pour un acquis

**M4 ne bloque plus depuis `DEC-0025`** et je ne la ressuscite pas sur des lots qui n'en etaient pas
avertis. Mais la mesure est neuve et elle appartient au dossier :

| Jeu | Message d'erreur a 360 x 640 | Indice a 360 x 640 |
|---|---|---|
| Le perimetre | **sous le pli 67 / 67**, de 27 a 75 px | **sous le pli 67 / 67**, de 111 a 159 px |
| Les mesures | visible 61 / 61 | **sous le pli 38 / 61**, jusqu'a 33 px |
| Les tables de multiplication | visible 70 / 70 | visible 70 / 70, marge de 3 px au pire |

"Le perimetre" entre au catalogue avec le defaut de `DEC-0025` sur **100 % de ses echecs** : ses
enonces sont les plus longs des trois paquets. La capture
`squad/conformite/releves-s12/perimetre-echec-avec-indice-360x640.png` montre ce que l'enfant voit
sur un telephone apres s'etre trompe - l'enonce sur quatre lignes, les trois propositions, la marque
barree, et rien d'autre.

### Suites hors de ma main

- `camille-po` : deux lignes de matrice a passer a `couvert-valide` (perimetre, mesures), sans
  mention de couverture partielle pour l'une comme pour l'autre. La ligne des tables **ne bouge
  pas**, et le jour ou elle s'ouvrira, elle ne pourra jamais l'etre sans la mention **"sans mesure
  d'automatisation"** : le referentiel dit "rappel automatise", l'automatisation est une affaire de
  vitesse, et ce produit ne mesure aucun temps de reponse. Couverture partielle, avec ces mots.
- `camille-po` : sept motifs de story, tous ecrits et actionnables dans les trois fiches - le rang
  de la bonne reponse sur les tables (le refus), le rang sur le perimetre (ma reserve 1), l'ordre de
  grandeur des distracteurs de mesures, les deux mesures de pli, l'absence de mise en situation dans
  le perimetre, les quantites de depart a un seul chiffre significatif dans les mesures.
- `camille-po` : deux corrections de fiche - la ligne Contenu(s) du perimetre annonce toujours huit
  items transposables la ou ils sont sept, et celle des mesures nomme
  `cm1-conversions-unites.yaml` la ou le fichier livre est `cm1-mesures.yaml`.
- `theo-archi` et `yanis-qa` : **le rang de la bonne reponse parmi les trois propositions** est la
  propriete neuve que ce sprint fait apparaitre, et c'est la plus mecanisable de ce qui me reste. Je
  la decris, je ne prescris pas le moyen. Tant qu'aucun garde-fou ne la porte, c'est moi, en jouant.

### Ce dont je reste la seule garde sur ces trois jeux

Le garde-fou de doublon de proposition, l'inventaire du texte lu par l'enfant, la part du facile et
la cardinalite des indices distincts sont desormais tenus par un automate, verifie par mutation sur
materiau reel. Je les retire de ma liste. **Restent sans automate, et c'est moi, en jouant** :
l'exactitude arithmetique de chaque reponse, la plausibilite de chaque distracteur, le rang de la
bonne reponse parmi les trois propositions, l'utilite reelle de l'indice, et le niveau - la
lisibilite de l'enonce, la charge de ce que l'enfant tient en tete, et la progression au fil d'une
partie de dix manches.
