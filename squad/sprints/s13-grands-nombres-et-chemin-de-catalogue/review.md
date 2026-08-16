# Review du sprint 13 - grands nombres et chemin de catalogue

> Ce fichier est ouvert par `nadia-prof` pour y deposer ses Done pedagogiques, comme au sprint 12.
> `sofia-sm` le complete a la ceremonie de revue : ce qui suit n'est qu'une section, pas la review
> entiere.

## Done pedagogique - nadia-prof, 2026-08-16 (second passage sur S13-1)

**Increment audite** : branche `round-two`, tete `4000bab`, arbre propre, 487 tests, 28 regles.
`mvn -B verify` vert rejoue depuis mon worktree `s13-nadia-verdict-2` avant toute mesure. Mon lot
n'ecrit que de la documentation et des releves : **487 tests et 28 regles ne bougent pas de mon
fait**.

**Mode** : j'ai joue. Jar reconstruit, serveur sur un port a moi, arrete sur le PID reel de la JVM.
80 manches jouees en echouant volontairement, les 28 items rencontres, revelations au troisieme
essai, 2 800 manches tirees pour la mesure de rang, rendu mesure a 360, 412 et 720 px.

| Story | Paquet | Verdict du 2026-08-15 | Verdict du 2026-08-16 |
|---|---|---|---|
| S13-1 | `cm1-grands-nombres` | REFUSE - 2 motifs | **REFUSE** - 1 motif neuf, les 2 anciens leves |

### Mes deux motifs du 2026-08-15 sont LEVES

- **Motif 1 - le verbe "decomposer" n'etait pas travaille.** Leve. Les sept items gardent le premier
  terme identique dans les trois propositions et deplacent une erreur reelle sur un terme suivant -
  `600 000 + 4` pour le zero avale, `600 000 + 400` pour le rang confondu. Les quatorze distracteurs
  sont quatorze erreurs de valeur de position ; plus un seul appariement de chiffre. C'est la forme
  que j'avais decrite, elle est en place, et j'ai verifie qu'aucun raccourci de forme n'a pris la
  place de l'ancien.
- **Motif 2 - deux indices faux sur `600 040`.** Leve. Les deux phrases sont exactes, lues a l'ecran
  apres un echec reellement joue. `40` n'est plus appele "des unites".

### Le motif neuf qui fonde ce second refus

**A 360 px, treize items sur vingt-huit affichent un nombre qui n'est pas le nombre ecrit.** Le
separateur etant devenu insecable, `100 000` est un jeton de 119,3 px dans une colonne de 96,0 px :
il deborde de 11,6 px de chaque cote, la gouttiere ne fait que 16 px, donc **les textes de deux
colonnes voisines se recouvrent de 7 px**, et ce qui deborde tombe en sombre sur le fond bleu nuit
du cabinet. Sur `Ecris en chiffres : un million.`, la bonne reponse `1 000 000` se lit **`000 000`**,
son `1` sur le fond sombre et son dernier `0` hors de l'ecran ; les trois colonnes montrent `00 000`,
`10 000` et `000 000`, et **il n'y a plus de bonne reponse lisible a l'ecran**. Un nombre ampute d'un
chiffre est un autre nombre : c'est une erreur de fait dans un contenu scolaire, et c'est un motif de
refus a soi seul.

Le defaut disparait a 412 px (3 px de debord, plus de recouvrement) et n'existe plus du tout a 720 px,
ou **aucun groupe n'est coupe** - la mesure de `marc-dev` est exacte et je la confirme. 360 px est la
largeur de reference de ce produit depuis `DEC-0016`.

### Le debordement contre la coupure

**Le debordement est pire, et les deux sont inacceptables.** La coupure genait la lecture, mais tous
les chiffres restaient visibles, dans l'ordre, dans leur colonne : un enfant lent finissait par lire
le bon nombre. Le debordement fait disparaitre des chiffres et melange les colonnes : un enfant qui
prend tout le temps du monde lit `000 000` la ou il est ecrit `1 000 000`. **Que le debordement soit
pire n'autorise pas le retour a l'espace ordinaire** - ce serait rouvrir mon motif du 2026-08-15. Ce
qu'il faut, c'est que le nombre entre dans sa colonne.

### Le caractere du separateur

**U+00A0 me convient, je retiens le choix de `marc-dev`.** Sa condition MD-4 est tenue par les deux
caracteres et son motif est mesure. J'ai verifie le seul point qui pouvait me faire changer d'avis,
la largeur : a la police reelle du bouton, U+202F gagne 4,5 px sur `100 000` et 8,8 px sur
`1 000 000`, soit 3 a 5 % la ou il en faudrait 20 a 35 %. **L'espace fine ne repare pas le
debordement** - que personne ne perde un lot a la croire corrective. L'argument typographique reste
juste : je le classe en reserve H, pas en condition.

### Le rang, mesure par moi

Je ne reprends ni le 3/6/5 de `marc-dev` ni le 5/4/5 d'`ADR-0041`, et je n'arbitre pas entre eux.
J'ai recompte seule, et j'ai mesure les deux axes :

- **Rang a l'ecran, 2 800 manches reellement tirees : 31,8 / 34,1 / 34,1 %.** La mecanique melange
  les propositions a chaque manche, donc le rang affiche n'est pas une propriete du paquet et
  n'est exploitable par aucun enfant.
- **Rang par valeur, recompte a la main sur les 21 items classables : 3 / 9 / 9, soit 14,3 / 42,9 /
  42,9 %.** Aucun rang ne depasse 50 % ni ne tombe sous 10 % : le seuil en vigueur ici est tenu.
  **Le rang ne fonde pas ce refus.** La barre plus haute de MD-5 (40 / 20 %) a ete ecrite pour S13-2
  et S13-3 ; je ne l'applique pas retroactivement a ce paquet.

### Deux corrections de mes propres constats

- **J'avais manque un debordement qui existait deja avant ce lot.** Ma reserve C decrivait les sept
  items "lire" comme "lisibles mais lents et laids" : en verite `cinquante-`, `quarante-` et
  `soixante-` sortaient deja de leur colonne, jusqu'a 33 px par cote. J'avais mesure la hauteur et
  pas la largeur. **Ces sept items ne sont pas une regression de `marc-dev`** ; mon motif ne porte
  que sur les treize items dont la cause est le separateur.
- **Ma prediction sur le garde-fou de rang visait le bon effet mais le mauvais axe.** Elle s'est
  realisee - la branche est sortie rouge, il a fallu deux lots pour la rouvrir. Ce que je n'avais pas
  vu : le rang **affiche** est melange a chaque manche, donc c'est le rang **par valeur** qui compte,
  et c'est celui-la que j'ai mesure.

### Ce que `camille-po` doit retenir

- La ligne mathematiques / CM1 / "Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au
  million" **ne passe pas** a `couvert-valide`. Le perimetre de la fiche reste ouvert en entier.
- **Une seule story leve ce refus** : que les propositions tiennent dans leur colonne a 360 px. C'est
  du rendu, pas du contenu - la cause est technique et revient a `theo-archi`.
- **Le contenu est bon** : neuf criteres sur neuf tenus, quatre verbes travailles, 28 bonnes reponses
  justes, 28 indices exacts, 56 distracteurs plausibles. Si le rendu est corrige sans toucher au
  YAML, je n'aurai plus rien a objecter sur le fond.
- Mes sept reserves du 2026-08-15 restent non bloquantes et non traitees ; deux reserves neuves les
  rejoignent (H : U+202F le jour ou un test saura le reproduire ; I : sur les 7 items "lire", la
  bonne reponse n'est jamais la plus petite valeur).

### La propriete dont je reste la seule garde, et celle qui en sort

**Aucun garde-fou ne connait la largeur d'un ecran.** Ce refus en est la preuve : le defaut a
traverse une CI verte a 487 tests et 28 regles. Tant que rien ne mesure la largeur, **c'est moi, en
jouant, a 360 px** - et il faut le savoir plutot que de croire cette propriete couverte.

En sens inverse, une propriete **sort** de ma garde et le credit revient a qui l'a fermee : le
classement des propositions de valeur egale et la lecture du total d'une somme sont desormais gardes
par le code (`lea-dev`, ratifie par `theo-archi`). Sur les sept items "decomposer", je ne suis plus
seule sur le rang.

**Detail complet, tableaux de mesure et historique des verdicts** :
`squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md`, blocs du 2026-08-15 et du 2026-08-16.
**Releves** : `squad/conformite/releves-s13/releve-grands-nombres-debordement-360.json`,
`releve-grands-nombres-rang.json`, et les quatre captures `grands-nombres-debordement-*.png`.
