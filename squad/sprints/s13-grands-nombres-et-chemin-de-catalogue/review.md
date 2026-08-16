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

**Addendum du 2026-08-16.** `round-two` a avance en `34abbb7` pendant que j'ecrivais, sur de la
documentation seule (`032d34e`) : le paquet, la feuille de style et le code sont inchanges, mes
mesures tiennent. La reconciliation de `theo-archi` conclut a **3/6/5, max 42,9 % sur 14 items**, et
**mon recompte a la main, fait avant de lire sa note, donne exactement 3/6/5 sur ces memes 14
items** ("ecrire" 2/3/2 + "decomposer" 1/3/3). Les 7 items "lire" (0/3/4) sont hors du domaine du
garde-fou : c'est toute la difference entre son 14 et mon 21. Les deux mesures concordent.

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

## Arbitrage prealable du 2026-08-16 (soir) - la voie de rendu des propositions (nadia-prof)

Saisie par `theo-archi` (`avis-jeton-en-image.md`, `s13-theo-jeton`) sur quatre voies chiffrees, et
par l'utilisatrice sur la question du nombre transforme en image. **J'ai joue les voies plutot que
de les lire** : jar reconstruit a chaque etat, navigateur pilote a 360 x 640, parties reelles, tous
les items de chaque paquet vus. Detail complet :
`squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md`.

**La voie est tranchee : voie 4, la colonne recule devant son contenu.** Elle ferme les 29
debordements, elle ne touche aucun plancher de police, et **elle est gratuite pour huit jeux du
catalogue sur dix - inchanges au pixel, trois colonnes, meme hauteur de bloc.** La voie 2 meurt sur
mes planchers (elle ne ferme pas le defaut sans descendre sous 1,25 rem, et laisse un item a 2,7 px
au plancher, au-dessus des 2,01 px qui ont declenche `DEC-0018`). La voie 3 marche mais coute
+180 px aux sept jeux de CP qui n'ont jamais rien eu, et met **dix items du "Perimetre" avec un
choix entierement hors ecran** contre **un seul** pour la voie 4 : elle reste mon repli, pas mon
choix. Sept conditions VR-1 a VR-7 encadrent le lot.

**Sur la voie 1, l'image : fermee, et d'abord par une mesure.** Une image de `1 000 000` reduite
pour tenir dans une colonne de 96 px donne des glyphes de 18,9 px, soit 1,18 rem, **sous mon
plancher de 1,25 rem**. Elle n'achete pas un pixel : le probleme n'est pas la nature du nombre, c'est
la largeur de la colonne. **Ma condition C5 est maintenue sur ce cas** - mais je le dis franchement,
sur un motif neuf date du 2026-08-16 et non sur celui de 2026-08-11 : C5 visait le raccourci visuel,
et une image du nombre n'ouvre pas ce raccourci-la. Ce qui la ferme aujourd'hui, c'est que **rien ne
prouverait jamais que l'image de `1 000 000` montre `1 000 000`** : les 103 tests qui jugent la
chaine du YAML resteraient verts en jugeant un texte qui n'atteint plus l'oeil de l'enfant.

**Sur le nombre de colonnes qui varie : acceptable, et verifie plutot que suppose.** J'ai regarde
les ecrans, ils sont lisibles et la machine reste une machine. Surtout, j'ai mesure la seule chose
qui m'aurait fait refuser : **presser toujours la boite la plus large gagne 26,7 %** sur 120 manches
jouees, sous le hasard. La variation ne porte aucune information. **Ma limite devient une condition
(VR-7) : aucune proposition de forme ne peut desormais presupposer trois colonnes**, et `ines-design`
doit le savoir avant de dessiner.

**Sur les neuf items qui debordent en production, deux verdicts differents parce que la gravite
l'est.**

- **"Le perimetre" - REFUSE, nouveau verdict date, un item, un motif.** Sur `Le perimetre d'une
  figure, c'est...`, **c'est la bonne reponse qui est illisible** : `longueur` est percute lettre sur
  lettre par le `nombre` de la colonne voisine. C'est le motif exact de mes deux refus sur
  `cm1-grands-nombres`, et je ne peux pas le tenir sur un jeu et l'oublier sur un autre parce que
  celui-la porte deja mon Done. **Je ne demande pas le retrait du catalogue** : le perimetre d'un
  retrait est a `camille-po`, et mon avis non contraignant est que 21 items sur 22 sont bons.
- **"Les mesures" - Done maintenu, reserve.** Les huit items abiment un **distracteur**, jamais la
  bonne reponse. Meme frontiere que le 2026-08-16 : le debordement qui detruit la bonne reponse est
  un refus, celui qui abime un distracteur est une reserve.

**Deux corrections de mes propres constats, avant qu'on s'appuie dessus.**

- **J'ai manque ce debordement le 2026-08-14 sur "Le perimetre", en jouant a 360 x 640** : j'avais
  mesure les hauteurs et pas les largeurs. **C'est la deuxieme fois** apres les sept items de
  `cm1-grands-nombres`. La regle que j'en tire : tant qu'aucun automate ne mesure une largeur rendue,
  un audit de rendu mesure la largeur autant que la hauteur.
- **J'ai failli contredire `theo-archi` sur la voie 3 avec une mesure fausse de ma main** : j'avais
  pose la voie en ne changeant que la direction du conteneur, obtenu un bloc de 164 px et cru qu'elle
  etait deux fois moins chere qu'annonce. L'ecran mesure etait en fait illisible - texte de 32 px
  dans des boites de 44 px, propositions superposees. Refaite correctement : **254 px, soit les
  +180 px qu'il annonce.** Son chiffre est juste, le mien etait faux.

**Un fait neuf que je ne cherchais pas, et il est plus grave que le rendu.** En jouant les 120
manches destinees a verifier que la largeur des boites ne trahissait pas la reponse, j'ai trouve que
sur **"Les mesures", presser toujours la proposition la plus courte gagne 47,5 %** (57 manches sur
120) contre 33,3 % au hasard - parce que **la bonne reponse est la plus courte sur 13 items sur 24**.
"Presse le plus petit" se decouvre en trois manches et ne demande aucune conversion. Aucun autre
paquet ne depasse 25 %. **Et le constat que je corrige est le mien** : j'avais mesure exactement
cette distribution le 2026-08-14 et ecrit que *"cet equilibre-la est un acquis a preserver"*. Je
l'avais lue comme une statistique de variete au lieu de la jouer comme une strategie. **Reserve
neuve et datee, pas refus** : je ne transforme pas ma propre approbation en refus sur un chiffre
inchange.

**Ce dont je reste la seule garde s'agrandit, et il faut le savoir.** La voie que je retiens ajoute
trois proprietes qu'aucun test ne couvre : que le nombre de colonnes reste un effet du texte et
jamais un signal, qu'aucune proposition ne passe sous le pli hors l'item que j'ai nomme, et
qu'aucune propriete de forme d'une proposition - longueur, largeur - ne fasse gagner mieux que le
hasard. Le rang est garde depuis `lea-dev` ; **la longueur ne l'est par rien, et "Les mesures"
prouve aujourd'hui que ce n'est pas theorique.**

**Aucune ligne de matrice ne bouge de mon fait**, dans aucun sens.
