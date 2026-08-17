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

---

## Verification de la voie 4 livree, 2026-08-16 (soir) - nadia-prof

**Ce bloc s'ajoute et n'efface rien de ce qui precede, y compris mes deux refus.**

`camille-po` a leve le blocage circulaire par l'amendement de `DEC-0031` : j'ai reinstalle l'entree
de catalogue dans un worktree dedie, non fusionne, et **j'ai joue** - onze jeux, **357 items
distincts** dont les 294 items des paquets a inventaire ferme vus jusqu'au dernier, a 360 x 640,
avec un second jar portant la feuille de style que j'avais refusee pour comparer item par item
plutot que croire un avant que je n'aurais pas remesure. C'est la premiere mesure independante apres
implementation ; `lea-dev` avait dit franchement ne pas l'avoir faite.

**Trois verdicts.**

- **"Les grands nombres" - VALIDE SOUS RESERVE.** Troisieme passage, premier verdict favorable. Le
  motif de mes deux refus est ferme et mesure : **20 items sur 28 debordaient, 0 aujourd'hui**, et
  les 28 items affichent caractere pour caractere ce que le YAML ecrit, espace insecable comprise.
- **"Le perimetre" - refus leve, VALIDE SOUS RESERVE.** Un item debordait, plus aucun ; sur l'item de
  la definition les trois propositions se lisent entierement. La ligne peut revenir de `en cours` a
  `couvert-valide`.
- **"Les mesures" - Done inchange, ma reserve de rendu tombe.** 8 items debordaient, 0 aujourd'hui.

**Mes sept conditions : six tenues, une non tenue.** VR-1 (0 debord sur 357 items contre 29 avant),
VR-2 (32 px partout, 30 px sur le seul paquet a quatre colonnes), VR-5, VR-6 - que j'ai verifiee
moi-meme en remettant la feuille de style reelle de `c0c51c5` dans l'arbre : **une seule defaillance,
la bonne** - et VR-7 sont tenues. **VR-3 est tenue sur le critere que j'avais operationnalise** (0
item de forme ou de hauteur changee sur les huit jeux et leurs 283 items communs) et je la leve a ce
titre. **VR-4 n'est pas tenue** : huit items des grands nombres placent un choix entierement sous le
pli, la ou j'en avais admis un seul et nomme.

**Je ne refuse pas sur VR-4, et je dis pourquoi plutot que de le glisser.** Sur les huit items, sans
exception, une boite est coupee par le pli - l'ecran ne parait jamais complet -, le choix cache est
atteignable en 243 a 355 px, et l'enfant qui se trompe garde ses trois essais puis recoit la reponse
nommee. Je me suis trompee trois fois de suite pour le verifier. Ma frontiere, appliquee dans le meme
sens que ce matin : *un obstacle qu'on surmonte n'est pas du meme ordre qu'un texte qu'on ne peut pas
lire*. **Mais huit items, ce n'est pas "un deuxieme item"** : la difference avec le perimetre est une
difference de frequence - une manche par partie contre une manche sur six parties - et je refuse de
la maquiller en difference de principe. C'est pour cela que le Done est **sous reserve** et non plein,
avec un critere de levee ecrit.

**Deux corrections de mes propres constats, avant qu'on s'appuie dessus.**

- **Mon premier depouillement d'aujourd'hui etait faux** : il comptait le debord contre la boite de
  contenu du bouton et annoncait quatorze items debordants sur "Le bon verbe", jeu qui n'a jamais
  deborde. Refait sous la convention que j'ai moi-meme posee - texte contre boite du bouton - les
  chiffres retombent au centieme sur mes releves d'avant.
- **"Presser la plus grande boite ne fait pas gagner" est faux sur "Le perimetre".** Je l'ai ecrit ce
  matin apres 37,5 % mesures en jouant ; l'esperance exacte sur les 22 items est **48,5 %**. Mon
  echantillon etait trop petit et ma conclusion trop rapide, sur le seul point qui aurait pu me faire
  refuser la voie 4.

**Un fait neuf que la voie 4 apporte, et qui n'etait dans aucune de mes conditions.** Les colonnes ne
se partageant plus la largeur a parts egales, **la taille d'une boite est devenue une information
visible sans lire**. Avant, presser la plus petite valait exactement 33,3 % sur les onze jeux ;
aujourd'hui elle gagne 45,8 % sur "Les mesures", 39,4 % sur "Petits problemes" et 38,6 % sur "Calcul
CP", et presser la plus grande gagne 48,5 % sur "Le perimetre". **Le raccourci de longueur que j'ai
trouve ce matin dans le contenu a maintenant une face geometrique** : un enfant de CP n'a plus besoin
de dechiffrer trois mots pour l'exploiter. Reserve, pas refus - meme frontiere que ce matin - mais la
clause "largeur" de la story de sprint 14 cesse d'etre hypothetique.

**Un point de code que seule ma reinstallation pouvait reveler** : la methode de test qui reliait
l'entree de catalogue au paquet a ete retiree avec l'entree. Sur ma branche l'entree est revenue et
la suite reste a **488 tests** - donc **rien ne verifie que cette entree charge bien ce paquet**.
Elle doit revenir dans le meme geste que l'entree.

**Ce dont je reste la seule garde s'agrandit d'une entree** : qu'aucun choix ne passe sous le pli.
Rien ne le mesure, et huit items y echappent aujourd'hui.

---

## Cloture du sprint - sofia-sm, 2026-08-17

- **Horodatage de debut** : 2026-08-15 16:18 (`planning.md`)
- **Horodatage de fin** : 2026-08-17 09:16
- **Increment audite** : `round-two` a `8d846c0`, arbre propre (`git status --porcelain` vide avant
  et apres chaque commande).

Ce qui suit complete les blocs de `nadia-prof` ci-dessus, sans en effacer ni en reformuler aucun.

### Porte technique - rejouee par moi, jamais recopiee

`JAVA_HOME` pointe sur le JDK 21.0.10 le temps de chaque commande (`outils/verifier-jdk.sh` :
`java` du `PATH` en 1.8.0_202, fichier de toolchains present sous un nom non lu par Maven -
`mvn -version` confirme malgre tout `Java version: 21.0.10` une fois `JAVA_HOME` positionne, le
nom errone du fichier n'empeche donc pas la compilation, seulement sa detection automatique).

`mvn -B clean verify`, un seul passage complet, jamais un `-pl` seul : **BUILD SUCCESS**, six
modules en SUCCESS.

| Module | Tests |
|---|---|
| domain | 47 |
| application | 7 |
| adapter-web | 229 |
| adapter-content | 120 |
| bootstrap (surefire) | 80 |
| bootstrap (failsafe) | 6 |
| **total** | **489** |

**489 confirme**, chiffre identique a celui de `rapport-qualite.md` (`yanis-qa`, tete `a948785`) et
inchange depuis (aucun commit de production entre `a948785` et `8d846c0`).

**28 regles ArchUnit, recompte par une methode stricte** (motif `ArchRule <nom> =`, pas la seule
sous-chaine `ArchRule `, qui compte a tort la constante `DECLARATION_DE_REGLE` et un commentaire de
`LesGardeFousSontMisALEpreuveTest`) : 30 declarations brutes sur `src/test/java` des cinq modules,
moins 2 dans le faux module de contre-exemple (`RegleContreExemple.java`, sous
`src/test/resources`) = **28**, repartis `adapter-web` 1, `bootstrap` 27. Identique a
`confirmation-archunit.md` et `rapport-qualite.md`.

**Garde-fou de chemins**, rejoue comme la CI :
`git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh`
-> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", **code de sortie
0**.

### Rapport qualite

`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/rapport-qualite.md` (`yanis-qa`,
2026-08-17, tete `a948785`) : BUILD SUCCESS rejoue deux fois, 489 tests, 28 regles, garde-fou de
chemins a 0 - je retrouve les memes chiffres sur `8d846c0`. Deux mutations sur la regle de la voie 4
(elle mord sur l'etat reel refuse par `nadia-prof`, et reste vert a tort sur un `white-space:
nowrap` qui contourne le couple qu'elle garde). Un quatrieme axe de rang existe (la position a
l'ecran), enumerable par lecture du tirage Fisher-Yates mais mesure par aucun test. Les quatre
tests au motif fragile signales par `marc-dev` sont confirmes par mutation reelle sur
`catalogue.yaml`. Deux chiffres non corriges verifies au hasard (13/24 sur `cm1-mesures`, 18/20 sur
`cm1-perimetre`) tiennent exactement. Detail complet dans le fichier lui-meme, non reproduit ici.

### Confirmation ArchUnit - un critere de cloture que je m'etais impose n'est pas tenu, et je tranche

`theo-archi` a publie `confirmation-archunit.md` a l'**ouverture** du sprint (`9430cfd`,
2026-08-15, commit `f9c5b57`), en ecrivant explicitement qu'il l'amenderait "a la review, sur
l'arbre fusionne, section ajoutee et non reecrite". `yanis-qa` releve dans `rapport-qualite.md`
qu'a `a948785` ce fichier compte toujours 143 lignes et un seul commit : **aucune section de
cloture n'a ete ajoutee**. Je verifie moi-meme sur `8d846c0` : c'est toujours vrai, `git log
--follow` sur ce fichier ne rend qu'un seul commit.

**Je ne fabrique pas cette section a la place de `theo-archi`** - ce serait ecrire une ceremonie qui
n'a pas eu lieu, sous un nom qui n'est pas le mien. Je tranche donc ainsi :

1. **Le critere que j'avais pose n'est pas rempli.** Ce n'est ni la quatrieme ni la cinquieme
   absence de la serie s09-s12 (le fichier existe, date de l'ouverture) : c'est une **premiere**,
   d'une nature differente - une republication promise et non tenue plutot qu'une republication
   absente. Je le nomme comme tel plutot que de le confondre avec la serie qu'il devait clore.
2. **Ce que ca change sur le fond : rien.** L'amendement du 2026-08-15 a `ADR-0013` (ecrit par
   `theo-archi` dans ce meme fichier, section 4) a deja retire toute clause qui dependait de cette
   republication : "une clause dont l'execution depend d'une ceremonie n'est pas une clause". Les
   deux cliquets resserres ce sprint (`hasSize` au lieu de `hasSizeLessThanOrEqualTo`) sont des
   tests, verts sur `8d846c0` comme partout ailleurs dans ce rapport. Le compte de regles (28) et de
   tests (489) est reverifie deux fois de plus depuis l'ouverture - par `yanis-qa` puis par moi -
   sans dependre de ce fichier.
3. **Ce que je route a s14** : `theo-archi` ecrit la section de cloture qu'il avait annoncee, ou
   ecrit explicitement dans `confirmation-archunit.md` pourquoi il juge qu'elle n'apporte plus rien
   depuis que son propre amendement a decouple le mecanisme de la ceremonie - les deux reponses sont
   recevables, l'absence de reponse ne l'est pas une seconde fois.

### Corrections du relecteur-pedagogique

- **Titre de `DEC-0031` fusionne en un seul titre de niveau 1** (`3fcafa6`) : le fichier portait
  deux lignes commencant par `#`, corrige sans toucher au texte.
- **Reserve F11 : un chiffre de production perime, non corrige par lui.** `FICHE-bandit-manchot-
  additions-cp.md` porte, dans la section datee du 2026-08-15 ecrite par `nadia-prof`, la phrase
  "Les huit jeux partagent le meme gabarit de manche". Le catalogue jouable compte aujourd'hui
  **onze** jeux (`catalogue.yaml`), et en comptait dix des la reinstallation de
  `bandit-manchot-grands-nombres-cm1` cette semaine. Le relecteur ne l'a pas corrige : c'est un
  chiffre de contenu de production, pas une question de redaction, et sa correction revient a son
  autrice. **Je le route a `nadia-prof`** : la phrase ne change rien a la portee de sa demande
  (un seul jeu suffit toujours a renseigner le gabarit partage), mais le chiffre cite doit etre
  corrige par une note datee plutot que laisse faux dans un fichier qui fait foi.

### Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| `confirmation-archunit.md` publie a l'ouverture, section de cloture promise non ecrite | technique / methode | `theo-archi` | **ouvert**, route a s14, voir ci-dessus |
| Reserve F11 : "les huit jeux" est un chiffre perime (le catalogue en compte onze) | pedagogie / redaction de contenu | `nadia-prof` | **ouvert**, route par cette review |
| Done pedagogique de S13-4 et S13-5 jamais rejoue par `nadia-prof` sur `FICHE-bandit-manchot-tables-multiplication-cm1.md` apres la correction de `lea-dev` (`436b159`) | pedagogie | `nadia-prof` | **ouvert** - voir "Ce que j'ai trouve et qui n'etait annonce par personne" |
| Jugement pedagogique de S12-5 (retrait de l'`aria-label`) jamais rendu par `nadia-prof`, alors que la porte de sortie du planning l'exigeait explicitement | pedagogie | `nadia-prof` | **ouvert** - idem |
| `MATRICE-couverture.md` et `FILE-ATTENTE.md` non mis a jour depuis les verdicts du 2026-08-16 (soir) sur les grands nombres et le perimetre | produit / methode | `camille-po` | **ouvert**, a faire a cette review |
| Le quatrieme axe de rang (position a l'ecran) est enumerable par lecture de code mais n'est mesure par aucun test | technique | `theo-archi` (mecanisme), `camille-po` (story) | **route a s14** |
| Quatre tests au motif fragile (`leJeuDuCatalogue...ChargeBienLePaquet...`) et le couple retrait/restitution de methode de test non garde par un mecanisme | technique | `theo-archi` | **route a s14** |
| Le premier critere d'acceptation de S13-4, tel qu'ecrit ("se rapproche des 99 items"), n'etait pas encodable en test ; `theo-archi` l'a substitue par une forme plus forte et gratuite | methode | `camille-po` (ecriture de critere), pour reference future | **clos ce sprint sur le fond, retenu comme lecon pour s14** |
| JDK 21 declare absent par un troisieme agent ce sprint (`sofia-sm` au planning, `theo-archi`, `yanis-qa`) : le fichier de toolchains du poste s'appelle `toochains.xml`, jamais lu par Maven | infrastructure de poste, hors depot | aucun - deja signale a l'utilisatrice | **rien a faire cote depot** ; voir ci-dessous |
| Reserve H (U+202F, ecart mesure 3 a 5 % contre 20 a 35 % necessaires) et reserve I (les 7 items "lire", rang jamais la plus petite valeur) | pedagogie | `nadia-prof` | **non bloquantes**, deja tracees dans la FICHE |

**Sur le JDK 21, ce que le depot peut faire, et ce qu'il fait deja.** Le fichier fautif
(`~/.m2/toochains.xml`) vit hors du depot, sur le poste de chaque agent : aucun commit ne peut
renommer un fichier qu'il ne voit jamais. `outils/verifier-jdk.sh` est deja la reponse que ce depot
peut apporter et l'apporte depuis s06 : il detecte le JDK 21 par une methode qui ne suppose pas que
Maven lise le fichier de toolchains, rend un constat citable, et chaque agent l'utilise pour
positionner `JAVA_HOME` avant chaque commande plutot que de compter sur la detection automatique.
Rien de plus n'est faisable depuis le depot ; renommer `toochains.xml` en `toolchains.xml` reste une
action a faire une fois, sur le poste, hors de ce depot.

### Ce que j'ai trouve et qui n'etait annonce par personne

En verifiant les portes de sortie du planning ligne par ligne plutot que de les tenir pour
acquises :

**S13-4 et S13-5.** La porte de sortie du planning exigeait, pour chacune, "reverifie par
`nadia-prof` sur `FICHE-bandit-manchot-tables-multiplication-cm1.md`". La correction technique de
`lea-dev` (`436b159`, fusionnee en `38657eb`) est livree et verte. **Aucun commit posterieur ne
touche cette fiche** (`git log` sur le fichier s'arrete a `b50d4c5`, s12) : `nadia-prof` n'a jamais
rejoue l'increment corrige. Le Done pedagogique de cette ligne reste donc celui du 2026-08-15
(sprint 12), sur un contenu qui a change depuis. Je ne prononce aucun Done a sa place ; je constate
que la porte que j'avais moi-meme ecrite au planning n'est pas franchie.

**Un element du travail de `nadia-prof` sur cette meme reserve a ete perdu.** Le commit `653b915`
("reserve B des tables - 0 sur 1x1 est un distracteur legitime, S13-5 se limite a 11 et 110") existe
sur le worktree `s13-nadia-audit` (tete `cfb17dc`) mais **n'est l'ancetre d'aucun commit de
`round-two`** - a la difference du reste de ce worktree, dont le contenu equivalent a ete fusionne
par un autre commit (`a7ebb29`). La bonne nouvelle : `lea-dev` a corrige exactement dans l'esprit de
cette note (le `0` de `1 x 1` n'est pas touche, seuls `11` et `110` le sont), donc aucune matiere
n'a ete perdue sur le fond. Mais la note elle-meme n'est plus dans le depot, et c'est la preuve
concrete d'un point que ce sprint a deja nomme ailleurs (`theo-archi`, sur le contenu mesure) :
**un commit sur un worktree qui n'est jamais devenu l'ancetre de `round-two` n'existe pas pour le
depot**, meme si son auteur l'a bien ecrit une fois. Je le signale plutot que de le laisser filer :
rien ne se reouvre ici puisque le fond est sauf, mais je verifie desormais la lignee de chaque
worktree avant de conclure qu'un travail est capture, pas seulement qu'il a ete produit.

**S12-5 (retrait de l'`aria-label`).** La porte de sortie exigeait ce jugement "jugee par
`nadia-prof`". Aucun fichier de ce sprint - ni `review.md`, ni `rapport-qualite.md`, ni
`FICHE-bandit-manchot-additions-cp.md` - ne porte de verdict post-fusion sur ce point precis. Le
Done technique est atteint (`ADR-0039`, extension du garde-fou aux attributs, mesuree sur l'etat
fautif reel). Le Done pedagogique de ce point n'est pas prononce.

### Etat des worktrees

24 worktrees dedies au sprint 13 restent enregistres sous `recreo-wt/`. 23 sont des ancetres de
`round-two` (`git merge-base --is-ancestor <branche> round-two` confirme) : leur contenu est
integralement capture, ils peuvent etre supprimes sans perte. **Un seul ne l'est pas** :
`s13-nadia-audit` (tete `cfb17dc`) - voir ci-dessus, contenu equivalent capture par ailleurs sauf le
commit `653b915`. Je ne supprime aucun worktree dans ce geste : ce n'est pas demande, et la
suppression est une operation que je fais sur instruction, pas de ma seule initiative.

### Avis de `nadia-prof` et Done pedagogique - synthese, je ne prononce rien

Recapitulatif des verdicts ecrits par `nadia-prof` ci-dessus, sans reformulation :

| Fiche | Verdict le plus recent | Ce qui reste ouvert |
|---|---|---|
| `bandit-manchot-grands-nombres-cm1` | VALIDE SOUS RESERVE (2026-08-16, soir) | VR-4 non tenue (huit items sous le pli) ; reserves H et I non bloquantes |
| `bandit-manchot-perimetre-cm1` | VALIDE SOUS RESERVE, refus leve (2026-08-16, soir) | passage a `couvert-valide` sur la matrice, a la charge de `camille-po` |
| `bandit-manchot-conversions-unites-cm1` (Les mesures) | Done maintenu, reserve de rendu levee | reserve neuve sur la longueur/largeur comme signal (story s14) |
| `bandit-manchot-tables-multiplication-cm1` | inchange depuis s12 - **jamais rejoue sur le code de `lea-dev`** | reverification a faire, voir ci-dessus |
| `bandit-manchot-additions-cp` (F11) | F11 reste OUVERTE, protocole ecrit pour l'ecoute de l'utilisatrice | ecoute reelle a faire hors squad |

**Aucune ligne de `MATRICE-couverture.md` n'est mise a jour par moi.** Cette mise a jour, au vu des
verdicts ci-dessus, revient a `camille-po` (`squad/README.md`, tableau des responsabilites) et n'a
pas encore eu lieu depuis les verdicts du 2026-08-16 soir - voir le tableau des obstacles.
