# Arbitrage prealable s19 - les antonymes (CM1) et la nature du mot (CP)

- **Auteur** : nadia-prof. **Date** : 2026-08-24. **Sprint** : s19.
- **Branche** : `s19-nadia`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Ce que ce document est** : les deux arbitrages prealables bloquants que le planning exige avant
  tout code de contenu, sur `S19-1` (antonymes, francais CM1, proposition de `marc-dev`) et `S19-4`
  (nom et verbe dans une phrase, francais CP, proposition de `lea-dev`). **Aucun paquet YAML
  n'existe, aucune entree de catalogue non plus** : c'est exactement le bon moment.
- **Ce que ce document n'est pas** : ni un Done pedagogique, ni un jugement sur un increment. **Un
  paquet qui tiendrait toutes mes conditions peut encore etre refuse pour ce que seul le rendu
  montre** - et je nomme en section 3 les conditions qui ne peuvent se verifier qu'a l'audit.
- **Mode** : je juge **du texte**, pas une application servie, et c'est legitime ici puisqu'il n'y a
  rien a servir. J'ai lu les deux propositions, les deux referentiels concernes,
  `cm1-synonymes.yaml` tel qu'il est servi, `Question.java`, `BanditManchotSession.java`, et mes
  conditions `SYN-1` a `SYN-16`, `BPM-1` a `BPM-4`, `M5`, `M9`.
- **Une mise en garde que je m'applique** : `SAI-9`, hier, a montre ce qu'une condition ecrite un
  peu vite produit en aval. J'ai relu chaque condition ci-dessous en me demandant ce qu'un
  developpeur de bonne foi en ferait au pied de la lettre.

---

# Partie A - `S19-1`, les antonymes (francais CM1)

## Verdict : **ACCEPTE SOUS CONDITIONS.** Le materiau est bon, quatre items sur dix-huit demandent un travail nomme, et aucun des dix-huit ne porte d'erreur de fait.

C'est la story qui peut faire passer une ligne a `couvert-valide`, et j'ai refuse trois fois le
contenu des synonymes ce trimestre. Je le dis donc franchement : **ce brouillon est d'un autre
niveau que ce que j'ai refuse a s17.** Les dix-huit couples sont justes, le registre est celui d'un
CM1, les phrases fixent le sens, et `marc-dev` a trouve et corrige de lui-meme un ecart entre son
affirmation et son materiau. Ce qui suit corrige, cela ne reprend pas a zero.

## A.0 Ce que je verifie avant de juger

**Son affirmation de vocabulaire distinct tient, et je l'ai verifiee mot par mot contre le YAML
servi.** Les seize mots cibles de `cm1-synonymes.yaml` sont *contente, beau, triste, calme,
fatigue, courageux, malin, fort, douce, grand, petite, gentil, difficile, facile, gaie, propre* ;
aucun des dix-huit mots cibles de sa liste n'y figure. **La correction qu'il signale a bien ete
faite.**

**Et je releve ce que son affirmation ne couvrait pas, non pour le lui reprocher mais parce que
c'est un point favorable qu'il n'a pas revendique** : plusieurs de ses **reponses** et
**distracteurs** sont des mots que les synonymes emploient ailleurs - *triste* y est un mot cible et
devient ici une bonne reponse, *bruyant* et *anime* y sont distracteurs. **C'est une qualite, pas un
defaut.** Rencontrer *triste* une fois comme le mot a remplacer et une fois comme le contraire de
*joyeux*, c'est exactement ce qui construit un reseau lexical plutot qu'une liste. Les deux paquets
ne se contredisent nulle part : les synonymes donnent *gaie* pour *joyeuse* et *malheureux* pour
*triste*, les antonymes donnent *triste* pour *joyeux*. Tout se tient.

**Je ne rouvre pas l'entree de catalogue separee** : c'est une decision de `camille-po` verifiee
contre `ADR-0052`, elle n'est pas de mon ressort, et elle est de toute facon la bonne pour l'enfant -
la tache change, donc la consigne change, donc l'entree change.

## A.1 Le test que j'applique aux dix-huit items, et d'ou il vient

**`SYN-2`, transposee.** Ma condition bloquante des synonymes dit : *"la bonne reponse est reellement
substituable dans la phrase donnee, et aucun distracteur ne l'est"*. Pour un paquet d'antonymes elle
devient, mot pour mot dans son esprit :

> **La bonne reponse, mise a la place du mot cible, produit une phrase francaise correcte qui dit le
> contraire ; aucun distracteur, mis a la meme place, ne produit une phrase qui dit aussi le
> contraire.**

**J'ajoute une clause, et je la date parce qu'elle est neuve** : la substitution doit produire une
phrase **grammaticalement correcte**, determinant et accord compris. `SYN-2` ne le disait pas
explicitement parce que la question ne s'etait pas posee sur les synonymes, ou la substitution est
par construction de meme nature. Elle se pose ici. **Clause du 2026-08-24, ecrite comme neuve et non
comme une relecture severe de `SYN-2`.**

J'ai passe les dix-huit items a ce test. **Quatorze le passent sans rien.** Quatre ne le passent pas.

## A.2 Les quatre items a reprendre, un par un

### Item 15 - **le seul defaut grave : deux reponses vraies**

> *Ce quartier est tres "silencieux" la nuit.* Bonne reponse : **bruyant**. Distracteurs :
> **eclaire**, **anime**.

**"Anime" est un contraire de "silencieux", au meme titre que "bruyant".** Un quartier anime est
l'oppose exact d'un quartier silencieux, et c'est meme la collocation la plus courante en francais.
L'item admet donc deux reponses vraies et n'en accepte qu'une.

**C'est le motif exact sur lequel j'ai refuse "Le bon son" en s02** - *"trois items qui admettent
deux reponses vraies mais n'en acceptent qu'une"* - et il n'a pas vieilli. Un enfant qui repond
"anime" a raison et le jeu lui dit qu'il a tort : c'est la pire chose qu'un exercice puisse faire,
parce qu'elle desapprend.

**Ce qui le repare** : changer le distracteur, jamais l'item, qui est bon. *"Ancien"* convient - il
est plausible pour un quartier, il n'est le contraire de rien dans cette phrase, et il reste dans le
registre d'un CM1. *"Fleuri"* ou *"proche"* conviendraient aussi. **"Eclaire" est un bon
distracteur et reste.**

### Item 5 - la substitution ne produit pas du francais

> *Ce verre est "plein" d'eau.* Bonne reponse : **vide**. Distracteurs : **propre**, **chaud**.

*"Ce verre est vide d'eau"* n'est pas du francais, et *"propre d'eau"*, *"chaud d'eau"* non plus.
Le complement *"d'eau"* est soude a *"plein"* et ne survit a aucune substitution. Un CM1 qui verifie
en remplacant - ce qu'on lui apprend a faire - trouve trois phrases fausses sur trois.

**Ce qui le repare** : retirer deux mots. *"Ce verre est "plein"."* ou *"Le verre de Tom est
"plein"."* Les trois propositions passent alors sans changement.

### Item 8 - deux distracteurs qui ne tiennent pas dans la phrase

> *Le film va "commencer" dans cinq minutes.* Distracteurs : **durer**, **plaire**.

*"Le film va durer dans cinq minutes"* et *"Le film va plaire dans cinq minutes"* ne sont pas des
phrases francaises. Les deux distracteurs s'eliminent sans savoir ce qu'est un contraire, et l'item
devient un item a une seule proposition possible.

**Ce qui le repare** : *"reprendre"* et *"passer"* tiennent tous deux dans la phrase, sont plausibles
pour un film, et ne sont le contraire de rien ici.

### Item 9 - meme defaut, plus leger

> *Notre equipe va "gagner" le match.* Distracteurs : **arbitrer**, **applaudir**.

Une equipe n'arbitre pas et n'applaudit pas le match qu'elle joue. Les deux se rejettent sur le sens
commun, sans passer par la notion de contraire.

**Ce qui le repare** : *"jouer"* et *"disputer"* sont l'un et l'autre parfaitement plausibles apres
*"notre equipe va"*, et ni l'un ni l'autre n'est le contraire de *gagner*.

### Item 13 - le determinant ne suit pas, et c'est le seul couple de noms

> *Les hiboux dorment le "jour".* Bonne reponse : **nuit**.

*"Les hiboux dorment le nuit"* : le determinant reste au masculin. Aucune des propositions ne peut
etre substituee proprement, et c'est aussi le seul item de la liste dont le mot cible est un nom -
`marc-dev` le signale lui-meme.

**Ce qui le repare, et je lui laisse le choix des deux** : soit une reformulation qui rend le
determinant neutre, soit le retrait de l'item. **Le retrait ne coute rien** : la liste en compte
dix-huit pour un plancher de seize, et retirer celui-ci laisse dix-sept. Je ne demande pas une liste
grammaticalement homogene - la competence du referentiel ne restreint aucune classe de mots, et
`marc-dev` a raison sur ce point.

## A.3 Trois questions que `marc-dev` me pose, et mes reponses

**Item 3, l'antonyme non symetrique - je le garde, et son raisonnement est juste.** *"Cette piece est
claire le matin"* ne peut se lire que dans le sens lumineux ; *"confuse"* n'est pas atteignable
depuis cette phrase. C'est exactement ce que `SYN-2` demande a une phrase de contexte de faire, et
elle le fait. **Je ne le retire pas, et je le cite comme le modele a suivre pour tout mot a plusieurs
contraires.**

**Item 13, le seul couple de noms - la classe grammaticale n'est pas mon critere.** Voir ci-dessus :
c'est le determinant qui le condamne, pas le fait que ce soit un nom. Si un couple de noms passe le
test de substitution, il est le bienvenu.

**La consigne proposee - je l'accepte sur le fond et je la raccourcis.** *"Quel mot veut dire le
contraire du mot entre guillemets ?"* dit la bonne chose, et le mot *contraire* est bien ce qui porte
toute la difference de tache. Deux remarques : le mot **contraire** est le bon terme, pas
*"antonyme"*, qui est un mot de maitre - un CM1 le rencontre, il ne s'en sert pas. Et la fin, *"du
mot entre guillemets"*, se lit deja dans la consigne des synonymes sans que je l'aie contestee ; je
ne la conteste pas davantage ici. **Le libelle reste a `camille-po` et `theo-archi` ; sur le fond
pedagogique, il est bon.**

## A.4 Conditions - `S19-1`

**Bloquantes.**

- **ANT-1.** Le test de substitution de la section A.1 passe sur **tous** les items du paquet livre :
  la bonne reponse produit une phrase francaise correcte qui dit le contraire, et **aucun distracteur
  n'est un contraire acceptable dans cette phrase**. Verifiable par n'importe quel enseignant, phrase
  par phrase, sans moi.
- **ANT-2.** Les items 5, 8, 9, 13 et 15 sont repris ou retires selon la section A.2. **L'item 15 est
  le seul dont la reprise n'est pas negociable** : deux reponses vraies est un motif de refus a lui
  seul, et je refuserai le paquet livre s'il y subsiste.
- **ANT-3.** Aucune erreur de fait. Les dix-huit couples proposes sont justes, je l'ai verifie un par
  un ; cette condition porte sur ce qui serait **ajoute** apres cet arbitrage, jamais relu par moi.
- **ANT-4.** Le paquet livre porte **au moins seize items** apres retraits. Retirer n'est pas
  corriger : si les reprises font tomber la liste sous seize, `marc-dev` en ecrit de nouveaux plutot
  que de livrer un paquet court. Reconduction de `BPM-3`, meme motif.
- **ANT-5.** Le mot cible est presente **entre guillemets francais, avec espaces insecables**, dans
  une phrase complete, comme `SYN-1`, `SYN-6` et `SYN-9` l'imposent aux synonymes. Aucune raison de
  faire autrement, et une bonne raison de faire pareil : l'enfant passe d'un jeu a l'autre.

**Non bloquantes, mais ecrites pour etre opposables.**

- **ANT-6.** **Au plus un antonyme forme par prefixe** dans le paquet (*poli/impoli*, item 18). Je ne
  le refuse pas - former un contraire avec *in-*, *im-*, *de-*, *mal-* est du programme de CM1, et
  c'est du vrai contenu. Mais un item de ce genre se resout en voyant le radical commun, sans passer
  par le sens : un seul est une variete, trois seraient trois points gratuits. **Le paquet en compte
  exactement un aujourd'hui ; cette condition borne ce qui serait ajoute plus tard.**
- **ANT-7.** Aucun item ne se resout par un accord de genre. Verification : dans chaque item, les
  trois propositions s'accordent de la meme facon avec le mot cible. **Je l'ai verifiee sur les
  dix-huit et elle passe partout** - c'est un point de qualite du brouillon, je l'ecris pour qu'il ne
  se perde pas a la reecriture.

## A.5 Ce que ce verdict ne fait pas

**Il ne fait pas passer la ligne a `couvert-valide`, et il ne le promet pas.** La ligne *"Utiliser
des synonymes et antonymes pour preciser ou nuancer un propos"* attend `SYN-4`, la moitie manquante,
et ce paquet est ce qui la comblerait. Mais **c'est un Done pedagogique sur un increment joue qui le
decidera, pas cet arbitrage**, et ce Done reste entier : je jouerai les seize items dans les quatre
etats, comme pour les synonymes. Ce que j'accorde ici, c'est le droit d'ecrire le paquet.

---

# Partie B - `S19-4`, nom et verbe dans une phrase tres simple (francais CP)

## Verdict : **la forme du jeu est ACCEPTEE SOUS CONDITIONS ; les seize phrases proposees sont REFUSEES en l'etat.**

Je separe les deux parce qu'elles ne meritent pas le meme sort. **Le dispositif est bon** : seize
phrases distinctes, huit contre huit, un mot cible marque, un indice par item, et trois questions
routees plutot que tranchees - c'est exactement le travail qu'un arbitrage prealable attend.
**Le materiau, lui, se resout entierement sans la competence**, et je ne peux pas laisser ecrire un
paquet sur cette base.

## B.1 Le motif de refus : seize items sur seize se resolvent en regardant le mot d'avant

J'ai releve, pour chacune des seize phrases, **le mot qui precede immediatement le mot cible**.

| Reponse attendue | Mot qui precede le mot cible |
|---|---|
| nom (8 items) | *Le*, *un*, *Le*, *un*, *Mon*, *Les*, *la*, *La* |
| verbe (8 items) | *Lea*, *chien*, *Tom*, *Maman*, *enfants*, *poisson*, *Lea*, *Papa* |

**Les huit noms sont precedes d'un determinant. Aucun des huit verbes ne l'est.** La regle *"s'il y a
un petit mot juste avant, c'est un nom"* donne **16 bonnes reponses sur 16**, sans qu'un enfant ait
jamais a se demander ce qu'est un nom ou un verbe.

**C'est le motif exact sur lequel j'ai refuse "Le bon son" - vingt-trois items sur vingt-quatre
resolubles sans la competence - et celui sur lequel j'ai refuse les huit items de comprehension a
s17, huit sur huit. Ici c'est seize sur seize.** Je ne peux pas etre plus indulgente parce que la
proposition est mieux faite : le critere n'est pas la qualite du travail, c'est si l'exercice
travaille ce qu'il annonce.

**Et les indices enseignent le raccourci.** Six des huit indices de noms disent *"on peut dire 'le'
ou 'un' devant lui"*, *"on peut dire 'les' devant lui"*, *"on peut dire 'la' devant lui"* - alors
que ce determinant **est deja imprime juste avant le mot, a l'ecran**. L'indice dit a l'enfant de
chercher ce qu'il a sous les yeux.

**Je precise, parce que la nuance decide de la reparation** : le test du determinant est **la bonne
methode de CP**, celle qu'on enseigne en classe, et je ne la conteste pas une seconde. Mais il y a
une difference entiere entre :

- **appliquer** le test - l'enfant doit trouver lui-meme qu'on peut mettre *un* devant *chat* ;
- **lire** le resultat du test - le *un* est deja la.

Ce paquet ne demande que la seconde. C'est cela que je refuse, pas la methode.

## B.2 Ce qui remplace le materiau - la regle, et deux exemples

Je ne renvoie personne a une page blanche. **La regle tient en une phrase : il ne doit pas exister de
signal de forme, visible sans lire le mot, qui separe les items "nom" des items "verbe".**

Concretement, sur ce paquet precis :

- **au moins un tiers des items "nom" ont autre chose qu'un determinant juste avant le mot cible** -
  un adjectif (*Le petit "chat" dort sur le tapis.*), ou le debut de la phrase ;
- **au moins un tiers des items "verbe" ont un petit mot grammatical juste avant** - un pronom
  (*Elle "range" la cuisine.*), une negation, un pronom complement.

Deux exemples suffisent, `lea-dev` appliquera la regle mieux que moi sur les quatorze autres. Et je
redis ce qui n'est **pas** demande : le vocabulaire est bon, les phrases sont courtes, la
repartition huit-huit est juste, les seize phrases distinctes sont un acquis. **C'est le placement du
mot cible qui est a refaire, pas la liste.**

## B.3 Trois mots cibles a retirer - et `lea-dev` a affirme une propriete qu'elle n'a pas verifiee

Elle ecrit : *"Aucun des seize mots cibles ci-dessus n'est un homographe nom/verbe"*, et elle cite
*ferme*, *porte*, *marche* comme ecartes. **L'affirmation est fausse sur trois de ses huit verbes**,
et deux d'entre eux sont parmi les homographes les plus connus d'un enfant de six ans :

- **item 9, "joue"** - *la joue*, la partie du visage. Un CP connait ce nom avant de connaitre le
  verbe.
- **item 16, "lit"** - *le lit*. C'est le premier sens qu'un CP donne a ces trois lettres, et le mot
  *lit* figure dans les premieres lecons de lecture de toutes les methodes.
- **item 14, "nage"** - *la nage*. Reel, mais le nom n'est pas courant a six ans.

**Je ne le lui reproche pas comme une faute d'inattention, je le nomme parce que c'est le meme geste
que `marc-dev` a fait de lui-meme et qu'elle n'a pas fait** : verifier sa propre affirmation contre
son propre materiau avant de la remettre. C'est ce geste, et lui seul, qui distingue une proposition
d'une intention.

**Mon critere, et il n'est pas celui du dictionnaire** : ce qui compte n'est pas qu'un mot existe
dans l'autre classe quelque part, c'est **que l'enfant de cet age le connaisse dans l'autre classe**.
Presque tous les verbes francais ont un nom homographe rare ; ce n'est pas le sujet.

- **"joue" et "lit" sortent.** Remplacements possibles, elle choisira : *Lea "dessine" une maison.*,
  *Le bebe "dort" dans son berceau.*, *Papa "repare" mon velo.*
- **"nage" reste**, et je dis pourquoi plutot que de la laisser deviner : *la nage* n'est pas dans le
  vocabulaire actif d'un CP.
- **"court" reste aussi**, et pour une raison qui n'est pas la meme : *court* a bien un autre emploi
  (*un pantalon court*), mais c'est un **adjectif**, pas un nom. Comme l'exercice ne propose que
  *nom* et *verbe*, cet autre emploi ne peut produire aucune reponse fausse defendable. **Il n'y a
  d'homographe genant que nom/verbe.**

## B.4 Sa question 3 - un CP doit-il rencontrer l'ambiguite ? **Non, et sa prudence etait juste**

Elle a ecarte *ferme*, *porte*, *marche* en disant qu'elle n'avait pas la certitude que ce soit le
bon choix. **C'est le bon choix, et voici pourquoi.**

Au CP, l'enfant construit sa toute premiere categorie de mots : *"celui qui dit ce qu'on fait"*
contre *"celui qui designe"*. Cette categorie est fragile, elle vient de naitre, et elle est
**absolue** dans sa tete - un mot **est** un nom, comme un chat **est** un animal. Lui montrer, dans
le meme exercice, qu'un mot change de nature selon la phrase ne l'enrichit pas : cela dissout la
categorie avant qu'elle ne tienne. Qu'un mot change de nature selon son emploi est une notion de
cycle 3 ; on l'aborde au CE2 et au CM1, une fois que les categories sont solides, et c'est justement
un des interets du CM1.

**Il y a un second motif, propre a un jeu et pas a une classe** : dans une classe, l'ambiguite est un
moment de discussion, l'adulte la deplie. Dans un bandit manchot, elle produit un enfant qui a raison
et a qui l'ecran dit qu'il a tort. **Je ne veux pas de ce moment-la a six ans.**

**Ce que je ne dis pas** : je ne dis pas que le produit ne doit jamais travailler l'ambiguite. Elle a
tout a fait sa place dans un paquet **de CM1** sur la nature des mots, ou elle serait meme le coeur
du sujet. Je le note pour `camille-po`, c'est du contenu futur, pas cette story.

## B.5 Sa question 1 - la lisibilite pour un CP, et deux choses qu'elle n'a pas vues

**Le vocabulaire est bon.** Animaux, famille, objets de la classe, actions quotidiennes : c'est le
bon fonds. Les phrases font cinq a sept mots, ce qui est le format juste. **Deux exceptions.**

- **Item 11, "par-dessus la barriere".** *Par-dessus* est un mot compose a trait d'union de dix
  lettres, et *barriere* en fait huit avec un accent grave et un double **r**. C'est la phrase la
  plus dure des seize, et de loin. **A remplacer** : *Tom "saute" dans la flaque.* garde le verbe et
  la difficulte du mot cible en supprimant celle du reste.
- **Item 13, "chantent" comme mot cible.** Le **-ent** muet du pluriel est une vraie difficulte de
  fin de CP, et ici elle porte sur le mot que l'enfant doit examiner. Je ne le refuse pas - c'est du
  programme et un CP de fin d'annee y a droit - mais **un seul mot cible avec un -ent muet dans le
  paquet**, pas trois. Aujourd'hui il y en a un ; je borne l'ajout.

**Ce qu'elle n'a pas vu, et c'est plus lourd que les deux precedents : les propositions.** Sa
proposition ne dit nulle part ce que l'enfant presse. Ce seront les mots **"nom"** et **"verbe"**.
Ce sont les termes du referentiel et ils sont justes - mais ce sont deux mots abstraits qu'un CP doit
lire et comprendre a chaque manche, et *nom* entre en collision avec le sens courant qu'il connait
deja, celui de son propre nom. **Ce n'est pas un motif de refus** : la competence s'appelle
*"distinguer les principaux types de mots (nom, verbe)"*, ces deux mots-la sont donc la tache. Je le
signale pour que la fiche de conformite le porte et pour que personne ne s'etonne, a l'audit, que je
regarde de pres si un enfant sait sur quel bouton appuyer.

**Et ce qu'elle n'a pas vu non plus : sa consigne repose sur un adulte.** Elle ecrit que la consigne
serait *"lue une fois par l'adulte ou l'enseignant qui presente le jeu"*. **Rien dans le depot
n'etablit qu'un adulte est present.** `DEC-0040` dit *"a la maison sur l'appareil disponible"* et
s'arrete la ; j'ai moi-meme route cette question a l'utilisatrice hier, dans mon arbitrage de la
saisie, et elle n'a pas de reponse. **Un contenu de CP ne peut pas etre concu sur une hypothese que
le depot ne porte pas.** Le paquet doit tenir pour un enfant seul.

## B.6 Sa question 2 - les guillemets a six ans

**Ils ne suffisent pas seuls, et ce n'est pas grave, parce que l'enfant n'a pas besoin de connaitre
la convention.** Un CP n'a pas appris les guillemets et ne les lira pas comme *"le mot dont on
parle"*. Mais il n'en a pas besoin : il lui faut voir **quel mot est designe**, pas savoir comment on
le designe. Les guillemets font deja ressortir un mot ; ils ont juste besoin d'un renfort.

**Le renfort est une question de forme et je ne le dessine pas** - `ines-design` propose, je
trancherai. Ce que je pose des maintenant, c'est ce a quoi il devra repondre :

- il fonctionne pour un enfant qui **ne connait pas** les guillemets ;
- **jamais la couleur seule** - ma regle de toujours, et deja la raison pour laquelle j'ai refuse un
  etat actif porte par la couleur seule sur la barre de niveaux ;
- il ne change rien au mecanisme deja mesure par `ADR-0052`, qui n'est pas en cause.

**Et je garde la convention des guillemets francais avec espaces insecables**, identique aux
synonymes : un enfant qui passe d'un jeu a l'autre ne doit pas reapprendre le marquage.

## B.7 Le score de ce jeu, et une correction a un fait que j'avais moi-meme enregistre

Ce paquet n'a que **deux** propositions possibles - *nom* et *verbe*. J'ai une reserve ouverte
la-dessus depuis s04, la **reserve 5**, que j'ai redite a s17 en ces termes : *"deux propositions
donnent une chance sur deux au hasard, donc le score de ce jeu ne mesure pas grand-chose"*.

**Ce que j'avais ecrit est inexact, et je le corrige avant de m'en servir.** J'ai lu
`BanditManchotSession` pour cet arbitrage. `M5` donne **trois** essais, et une manche resolue au
deuxieme essai **compte comme une reussite** (`resoudre(true)`, `score.reussites() + 1`, quel que
soit le nombre d'essais). Sur un item a deux propositions, un enfant qui se trompe au premier essai
a **necessairement** raison au second. **Ce n'est donc pas une chance sur deux : c'est une certitude
de reussir chaque manche.** Un enfant qui presse au hasard finit une partie a **10 sur 10**.

**Ce que j'en fais, et ce que je n'en fais pas.**

- **Je n'en fais pas un motif de refus contre `S19-4`.** La ligne des homophones a ete livree avec
  deux propositions, je l'ai validee, et j'ai ecrit noir sur blanc a s17 que la reserve 5 *"n'est pas
  un motif contre ce retour"*. M'en servir aujourd'hui contre `lea-dev` serait deplacer mes poteaux,
  et la tache nom/verbe n'a de toute facon que deux reponses possibles.
- **J'elargis la reserve 5, comme une observation neuve et datee du 2026-08-24**, et elle vise
  d'abord `cm1-homophones`, qui est deja `couvert-valide` : **le score d'un paquet a deux
  propositions n'est pas seulement peu informatif, il est toujours parfait.** Cela ne retire pas le
  Done des homophones - il portait sur le contenu, qui est juste - mais cela dit ce que le score de
  ces jeux vaut, c'est-a-dire rien.
- **`camille-po` a un arbitrage a rendre, et il n'est pas de mon ressort** : soit les paquets a deux
  propositions assument un score decoratif, soit `M5` cesse de donner trois essais quand il n'y a que
  deux propositions, ce qui est une question de mecanique pour `theo-archi`. **Je ne tranche ni l'un
  ni l'autre**, je fournis le fait.

## B.8 Conditions - `S19-4`

**Bloquantes.**

- **NAT-1.** **Aucun signal de forme, visible sans lire le mot cible, ne separe les items "nom" des
  items "verbe".** Verification, et elle se fait sans moi : relever le mot qui precede immediatement
  le mot cible dans les seize phrases, et verifier qu'aucune regle mecanique sur cette colonne ne
  donne la reponse. La regle operationnelle est en B.2 - au moins un tiers de chaque cote rompt le
  motif. **C'est la condition qui porte le refus des seize phrases actuelles.**
- **NAT-2.** **Aucun mot cible n'est un homographe nom/verbe dont l'enfant de CP connait les deux
  emplois.** *joue* et *lit* sortent. Le critere est le vocabulaire d'un CP, pas le dictionnaire, et
  seule la paire nom/verbe compte - un autre emploi adjectival ne gene pas, puisqu'il ne peut pas
  produire de reponse fausse defendable.
- **NAT-3.** **Aucun indice ne nomme un mot deja imprime dans la phrase.** Un indice qui dit *"on peut
  dire 'le' devant lui"* alors que *le* est a l'ecran ne guide pas, il montre du doigt. Les indices de
  noms deviennent semantiques - *"Ce mot designe un animal."* - ou bien ils gardent le test du
  determinant **uniquement sur les items ou aucun determinant ne precede le mot cible**, ce que
  `NAT-1` va rendre possible.
- **NAT-4.** **Le paquet tient pour un enfant seul**, sans qu'un adulte lise la consigne. Rien dans le
  depot n'etablit qu'un adulte est present, et une story de contenu ne peut pas creer cette
  hypothese. Si la reponse de l'utilisatrice arrive et dit le contraire, cette condition tombe
  d'elle-meme et je le noterai.
- **NAT-5.** **Le mot cible est designe autrement que par les guillemets seuls, et jamais par la
  couleur seule.** Le renfort est propose par `ines-design` et tranche par moi. Les guillemets
  francais avec espaces insecables sont conserves, identiques aux synonymes.

**Non bloquantes, ecrites pour etre opposables.**

- **NAT-6.** *Par-dessus la barriere* (item 11) est remplace. C'est la phrase la plus dure des seize
  et sa difficulte ne porte pas sur le mot cible.
- **NAT-7.** **Au plus un mot cible portant un -ent muet de pluriel** (*chantent*). Le paquet en
  compte un ; cette condition borne les ajouts.
- **NAT-8.** La repartition huit noms / huit verbes est conservee, et les seize phrases restent
  distinctes. **Deux acquis du brouillon que la reecriture ne doit pas perdre.**

## B.9 Ce qui n'est **pas** demande a `lea-dev`

Je le dis explicitement parce qu'un refus mal borne fait tout reecrire.

- Le vocabulaire est bon et n'est pas a revoir.
- La longueur des phrases est bonne, sauf l'item 11.
- La convention de marquage reprise des synonymes est le bon choix.
- Les trois questions qu'elle a routees plutot que tranchees etaient les trois bonnes questions. **La
  troisieme, sur l'ambiguite, elle l'avait deja resolue correctement** - il lui manquait la certitude,
  pas le jugement.

---

# Section 3 - Ce qui ne peut pas etre juge maintenant, et ce dont je reste seule garde

## 3.1 Conditions d'audit a venir, pas des verdicts rendus aujourd'hui

Ces trois-la demandent un increment servi et je ne les prononce pas ici.

- **AUD-1.** **La largeur des propositions.** `SYN-16` existe parce que cinq items sur seize
  demandent plus que la rangee offerte sous 720 px. J'ai mesure hier que le paquet des synonymes
  reclame **539 px**. Les paquets neufs peuvent depasser ce chiffre : *silencieuse* (item 3 des
  antonymes) est plus long que le plus long mot de tout le paquet des synonymes. **Je mesurerai la
  largeur necessaire de chaque paquet neuf a l'audit, avec les memes outils qu'hier**, et un paquet
  qui deborde plus que les synonymes recevra la meme reserve. **Je ne fixe aucun seuil en caracteres
  ici** : `DEC-0016` l'interdit, et j'ai moi-meme mesure hier que 22 caracteres demandent 539 px la ou
  24 en demandent 538 - un comptage de lettres n'est pas une largeur.
- **AUD-2.** **La troncature des indices.** `SYN-12` est ouverte : trois items sur seize perdent la
  fin de leur indice. **La cause n'est pas diagnostiquee**, donc je ne prescris aucune longueur
  maximale - je fabriquerais un nombre. Je lirai chaque indice en entier sur l'increment servi, et
  tout indice tronque sera une reserve sur le paquet concerne. Pour situer sans en faire un seuil :
  le plus long indice propose par `marc-dev` est plus court que plusieurs de ceux qui sont deja
  servis.
- **AUD-3.** **La lisibilite reelle au CP** - le rendu du mot cible, la lecture de *nom* et *verbe*
  sur les boutons, le comportement apres trois erreurs. Aucun texte ne me le dira.

## 3.2 Sur quelles proprietes je reste la seule garde

Aucune condition de ce document n'est tenue par un test, et deux d'entre elles sont mecanisables sans
que je puisse dire comment.

- **`ANT-1` et `NAT-1`** portent sur la composition d'un paquet - la substituabilite d'un distracteur,
  la presence d'un signal de forme. **Rien dans ce depot ne les verifie.** Un paquet integralement
  fautif sur les deux passerait une CI verte. C'est la meme famille de defaut que celle qui a traverse
  une CI verte quatre fois sur ce produit.
- **`NAT-1` est la seule des onze qui me parait mecanisable** - relever le token qui precede le mot
  cible et refuser qu'il correle avec la reponse est un calcul, pas un jugement. **Je le nomme a
  `theo-archi` sans rien lui demander maintenant** : si un garde-fou de contenu peut le porter, ce
  serait le premier qui verrait un item resoluble sans la competence, et ce serait plus utile que ma
  vigilance.
- **`ANT-3`** - l'exactitude des antonymes ajoutes apres cet arbitrage. J'ai verifie les dix-huit
  d'aujourd'hui ; je ne verrai les suivants qu'a l'audit.

# Section 4 - Suites hors de ma main

- **`marc-dev`** : cinq items a reprendre (5, 8, 9, 13, 15), dont un seul non negociable (15, deux
  reponses vraies). Le reste du brouillon est accepte. Il peut ecrire le paquet des maintenant.
- **`lea-dev`** : les seize phrases sont a replacer selon `NAT-1`, deux mots cibles a remplacer
  (`NAT-2`), les indices de noms a reformuler (`NAT-3`). **Le vocabulaire, la longueur, la
  repartition et la convention de marquage sont acquis et ne sont pas a revoir.**
- **`ines-design`** : un objet neuf a proposer - le renfort du mot cible pour un lecteur de CP qui ne
  connait pas les guillemets, jamais par la couleur seule (`NAT-5`). Elle propose, je tranche.
- **`camille-po`** : deux choses. Un arbitrage sur le score des paquets a deux propositions (B.7),
  qui touche `cm1-homophones`, deja `couvert-valide` - je fournis le fait, je ne tranche pas. Et une
  note de contenu futur : l'ambiguite nom/verbe est un bon sujet **de CM1**, refuse au CP (B.4).
- **`theo-archi`** : rien ne lui est demande. Deux choses lui sont **signalees** - la mecanisation
  possible de `NAT-1` (section 3.2), et la question de `M5` face a un item a deux propositions (B.7),
  qui n'est ouverte que si `camille-po` la lui route.
- **`sofia-sm`** : les deux stories peuvent partir. `S19-1` demande une reprise legere, `S19-4` une
  reecriture du placement des mots cibles - ni l'une ni l'autre ne repart de zero.
