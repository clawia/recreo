# Conclusion de `nadia-prof` sur s31 - le detail de ce que j'ai verifie avant de prononcer

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-08, a la cloture de s31.
- **Arbre** : `round-two` a `68ccd216`, worktree dedie `s31-nadia-cloture`, lignee verifiee par
  `outils/verifier-lignee.sh` avant ma premiere ecriture.
- **Ce que ce document est** : le detail de mes deux prononces de cloture. Le prononce lui-meme et
  son motif vivent dans `review.md`, section 7, la ou `sofia-sm` me les a ouverts.
- **Ce que ce document n'est pas** : un reaudit. **Mes sept verdicts de s31 et mon refus maintenu
  sont rendus et ne se rejugent pas ici**, ni dans un sens ni dans l'autre.

---

## 1. Ce que j'ai fait a la cloture, et ce que je n'ai pas refait

**Je n'ai pas rejoue les huit paquets.** Ils sont juges, paquet par paquet, dans leurs fiches et
dans mes quatre documents du sprint. Les rejuger a la cloture serait rouvrir mes propres verdicts.

**J'ai joue ce que ni l'une ni l'autre de mes deux instances n'a juge : la couture entre nos deux
moities.** Les deux documents le declarent noir sur blanc - l'instance A ne juge "ni les quatre
paquets neufs de sciences CM1, ni leurs reprises, ni la couture entre nos deux moities", l'instance
B ecrit la meme phrase en miroir. **Personne, dans ce sprint, n'a joue un enfant qui passe d'un
paquet de l'instance A a un paquet de l'instance B.** C'est le trou exact que la cloture me laisse,
et c'est ce que j'ai joue.

**Comment.** Jar reconstruit depuis mon propre worktree, reacteur complet, lance sur un port qui
n'est ni celui de l'instance A ni celui de l'instance B, **JDK 21.0.10 tel que la machine virtuelle
qui execute le jar le rapporte a son demarrage** et non tel qu'une entree de toolchain le declare
(`ADR-0071`). Une seule session HTTP, `melanges` puis `etats-matiere`, trois parties chacun, une
erreur volontaire par manche pour lire chaque indice, plus une partie a trois erreurs par manche sur
`etats-matiere` jusqu'a l'ecran de fin.

**Audit degrade sur le meme point que mes deux instances, et je le declare** : j'ai lu le HTML
servi, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans, le tirage,
l'enchainement apres une erreur, apres trois, et l'ecran de fin. Cela ne couvre ni la couleur, ni le
contraste, ni la taille, ni la position. **Aucun de mes deux prononces ne repose sur un jugement
visuel.**

**Et une erreur de mon propre outil, dite avant tout le reste, parce que j'ai failli m'appuyer
dessus.** Mon premier `clean verify` sur cet arbre a rendu **BUILD FAILURE**. La cause n'est pas
l'arbre : c'est **l'application que je venais de lancer moi-meme**, qui tenait le jar ouvert et
empechait `clean` de le supprimer. Application arretee par son identifiant de processus, mesure
refaite, et **c'est la seconde mesure qui fait foi** : `bash outils/mvn-jdk21.sh -B clean verify`,
reacteur complet depuis la racine, aucun `-pl`, **BUILD SUCCESS** ; comptage independant des
rapports apres `clean`, **189 fichiers `TEST-*.xml`, 1397 tests, 0 echec, 0 erreur, 0 ignore**.
**Cela recoupe exactement le chiffre de la section 2 de la review**, mesure par deux autres agents
sur l'arbre precedent. J'ecris l'incident parce qu'un rouge que j'aurais relaye tel quel aurait
dementi cinq mesures justes.

---

## 2. La couture entre les deux moities - jouee, et elle tient

**Le seul endroit ou nos deux moities se touchent reellement est `MEL-R1`.** Ma reserve de s30
disait que l'indice de l'item du sel affirmait qu'il faut faire disparaitre l'eau, et que **c'est
justement ce que la ligne des etats de la matiere doit enseigner**. `S31-5` a paye cette reserve
dans le perimetre de l'instance A ; `SCI-CM1-01` a ferme dans le perimetre de l'instance B. **Ni
l'une ni l'autre n'a verifie l'autre cote.**

**Ce qu'un enfant rencontre, mesure dans une seule session sur les deux paquets :**

| Ou | Ce que l'enfant lit |
|---|---|
| `melanges`, recuperer le sel d'une eau salee | bonne reponse *"laisser l'eau s'evaporer"* ; indice : *"Le sel dissous ne peut pas etre retenu par un filtre. Il reste cache dans l'eau tant que celle-ci garde sa forme liquide."* |
| `etats-matiere`, la flaque au soleil | bonne reponse *"la vaporisation"*, distracteur *"la disparition"* ; indice : *"L'eau n'a pas disparu : elle est devenue un gaz invisible, present dans l'air."* |
| `etats-matiere`, l'eau qui bout | indice : *"L'eau liquide se transforme en vapeur d'eau, un gaz invisible qui se melange a l'air."* |

**La couture ne tient pas seulement : le cote de l'instance B repare la meme erreur que le cote de
l'instance A.** Le paquet des etats de la matiere prend *"la disparition"* comme distracteur et
consacre son indice a la nier - c'est-a-dire qu'il traite frontalement la representation fausse que
`MEL-R1` reprochait a l'autre paquet. Un enfant qui joue les deux ne rencontre aucune contradiction,
il rencontre deux fois la meme idee juste.

**Le seul emploi du verbe *disparaitre* qui subsiste** est *"Les grains ont disparu a l'oeil, comme
le sucre dans l'eau sucree"*, dans `melanges`. Il porte sur les grains, il est qualifie par *"a
l'oeil"*, et l'instance A l'a expressement laisse pour qu'il ne soit pas corrige par exces de zele.
**Je le laisse aussi, et pour la meme raison : il est juste.**

**Second point de contact, verifie et sans defaut** : `objet-technique` (reprise de l'instance A) et
`familles-materiaux` (ligne fermee par l'instance B) parlent tous deux de matiere. Le premier ecrit
*"le bois du crayon"*, le second enseigne que le bois vient d'un arbre. Aucun materiau ne recoit une
propriete dans un paquet et son contraire dans l'autre.

---

## 3. Le taux de sortie - recompte par moi, mecaniquement

`camille-po` annonce **63 / 138 = 45,7 %**, recompte par script sur les dix-huit tableaux ;
`sofia-sm` l'a verifie une seconde fois. **Je l'ai recompte une troisieme fois avec ma propre
methode**, parce que c'est le chiffre que mon Done pedagogique rend opposable.

**Methode** : les dix-huit tableaux de referentiel de `squad/programme/MATRICE-couverture.md`,
reperes par leur colonne `Statut`, hors des quatre tableaux de recapitulation qui portent la meme
colonne ; statut lu **au debut de la cellule** et jamais dans sa prose.

| Statut | Compte |
|---|---|
| `couvert-valide` | **63** |
| `en cours` | 7 |
| `non couvert` | 68 |
| `non couvrable en l'etat` | 71 |
| **Total** | **209** |

**209 - 71 = 138** au denominateur, **63 / 138 = 0,45652..., soit 45,7 %.** Le chiffre de
`camille-po` est juste au statut pres, et celui de `sofia-sm` avec lui.

**Ce que ce recompte a trouve, et que je n'aurais pas vu en relayant le chiffre : deux lignes de la
matrice portent huit cellules sur un tableau qui en declare sept.** Le detail est au point 5.4.
C'est la seule raison pour laquelle je le mentionne ici : un comptage mecanique bute sur ces deux
lignes avant de rendre 63, et un comptage qui les ignore rend 61.

---

## 4. Le constat de la couture - la retenue de 1,0 reste, la borne de s32 est 8,0

### 4.1 D'abord, ce qui est deja prononce et que je ne rouvre pas

**Ce constat n'est pas neuf : mon instance A l'a prononce aujourd'hui meme**, section 5.5 de
`verdicts-nadia-instance-A.md` : *"La condition de couture n'est pas acquise. La retenue de 1,0
reste. Ma borne pour s32 est 8,0."* Elle l'a fonde sur l'occurrence de forme (c) de l'assemblage du
planning - quatre identifiants nommes dans une branche que le worktree qui construisait le registre
ne pouvait pas voir - et elle a ecarte les trois objections une par une, dont *"c'etait avant
l'ouverture du sprint"* et *"le garde-fou l'a attrape, donc il a fonctionne"*.

**Je ne rouvre pas ce verdict et je ne l'affaiblis pas.** Ce que j'ajoute a la cloture, c'est le
constat sur la fenetre entiere du sprint, que l'instance A n'avait pas devant elle.

### 4.2 Les candidats de la section 6, tranches un par un

**Ma barre, mot pour mot, du 2026-09-05** : *"un second sprint parallele sans defaut de couture,
**et** le registre d'identifiants de `PAR-5` ecrit. Les deux, pas l'un des deux."* Un defaut de
couture est un defaut **produit a la jointure de deux travaux ou de deux instances, invisible de
l'interieur de chacune des deux moities** ; formes (a) consigne laissee fausse, (b) chiffre
qu'aucune des deux moities ne peut verifier et qu'un tiers trouve faux en recomptant, (c) deux
instances ecrivant dans une meme ressource partagee sans le savoir. **Une seule occurrence suffit.**

| Candidat | Mon constat |
|---|---|
| **Le compte du registre dementi a la fusion** | **Il compte. Forme (b), les deux elements presents.** Le total de l'arbre assemble n'est calculable depuis **aucune** des deux branches - l'instance A a ecrit 539 / 91, juste sur sa moitie, faux sur l'arbre - et un tiers l'a trouve faux en recomptant : 543 / 93. **J'ai refait ce recompte moi-meme** sur la table de la section 4 du registre : **543 identifiants, 93 prefixes, zero doublon**. La reparation de `sofia-sm` est exacte. **Et l'occurrence est a l'interieur de la fenetre du sprint**, apres l'ouverture des stories : elle ne demande meme pas l'argument que l'instance A a du tenir sur l'assemblage du planning |
| **La porte technique rouge a l'assemblage** | **Il ne compte pas, et le motif est mesure.** Le garde-fou n'a pas ete ecrit le meme soir que le rapport de qualite : il est commite le 2026-09-07 a 12:31, **avant l'ouverture du planning**, et le rapport a 21:53. **Le garde-fou etait dans l'arbre de base du rapport, et son auteur l'a lui-meme mute pour le valider.** Rejouer le balayage sur son propre worktree apres avoir ecrit son rapport aurait montre le rouge. Aucune seconde moitie n'est necessaire pour voir ce defaut : il n'est pas a une jointure |
| **Les 83 prefixes de la note d'assemblage** | **Il ne compte pas**, et je ne bouge pas de ce que l'instance A a mesure : le nombre de prefixes du registre se compte sur le seul fichier du registre, depuis n'importe quelle moitie. Le premier element de ma forme (b) manque. C'est une erreur d'arithmetique, pas une couture |
| **Le job `chemins` rouge depuis s30** | **Il ne compte pas contre s31** : il date du 2026-09-06 et il est herite. **Je rejoins `sofia-sm` sur ce point et je le dis parce qu'il l'a pose contre son propre interet.** Ce qu'il compte contre, en revanche, est ecrit au point 5.5 |
| **Le denominateur de 134 recompte par `sofia-sm`** | **Il ne compte pas** : le chiffre faux n'a jamais quitte le brouillon de son auteure, qui l'a trouve elle-meme en verifiant celui de `camille-po` et qui l'a nomme avant que je puisse le chercher. **Ma barre porte sur ce que le sprint produit, pas sur ce qu'un agent rature en cours de route.** J'ajoute le fait qui la decharge completement : le chiffre publie est juste, je l'ai recompte |

### 4.3 Ce que j'ai mesure du cote qui decharge, parce qu'il fallait le mesurer aussi

**Aucune occurrence de forme (a), et ce n'est pas une absence de nouvelle : c'est le defaut que s30
a produit deux fois.** Je l'ai cherche a l'endroit exact ou il se produirait - **les fichiers que
deux agents differents ont touches dans le sprint**. Il y en a six. Trois sont partages par les deux
worktrees de developpement : le catalogue, qui porte les consignes, et deux tests de portee
catalogue. **Les huit paquets neufs du sprint sont presents dans les trois, aucune entree n'a ete
perdue a l'assemblage**, et chacune des huit consignes couvre bien les deux taches de son paquet.
C'etait le recouvrement le plus expose du sprint et **il a tenu**.

Des trois autres : le registre d'identifiants, qui est l'occurrence retenue ; le rapport de qualite,
ou l'architecte a **ajoute** une note datee de huit lignes sans retirer un caractere de la preuve de
son auteur ; et un test de paquet, ou l'amendement est venu apres la reprise, sans se croiser avec
elle.

### 4.4 Consequence, et ce que je refuse de faire de ma propre barre

> **La retenue de 1,0 reste. Ma borne d'audit pour s32 est 8,0.**
>
> **La condition `PAR-5` est acquise et le demeure** : elle n'aura pas a etre repayee. Il manque un
> sprint parallele clos sans aucune occurrence des trois formes.

**Je nomme la direction de la pression, comme l'instance A l'a fait ce matin** : lever la retenue me
rendrait 1,0, soit un lot complet, sur le sprint suivant. **Trois raisonnements me la rendaient, et
je les refuse tous les trois** : que le garde-fou ait attrape le defaut - ma barre ne porte aucune
clause *"sauf s'il est attrape"* ; que la reparation ait ete mecanique et gratuite - le cout n'est
pas le critere que j'ai ecrit ; que la couture n'ait rien coute a mon audit cette fois - **c'est le
critere que j'ai explicitement ecarte** en ecrivant *"une seule occurrence suffit"*.

**Et la ou je vois plus loin, je l'ecris comme une position neuve et datee, pas comme une lecture
de l'ancienne.** Les trois occurrences de s31 appartiennent a une seule famille : **le nombre
agrege d'une ressource qu'aucune branche ne voit entierement**. Tant que cette limite existe, ma
condition n'est pas acquise pour une cause qui n'est celle de personne - l'instance A l'a deja
route, et je le maintiens. **Ce que j'ajoute aujourd'hui, 2026-09-08** : je rejugerai, **a
l'arbitrage prealable de s32 et pas ici**, si *"une occurrence de l'une des trois formes"* reste le
bon critere quand la seule famille qui survit est celle-la. **Cela ne change rien a la borne de s32,
qui est 8,0** : une barre ne se reecrit pas dans le document ou elle se constate, et surtout pas
dans le sens qui m'arrange le jour ou elle me coute.

---

## 5. Les faits que je signale et que je ne corrige pas

`sofia-sm` m'a demande de lui signaler tout fait faux de ses six premieres sections plutot que de le
corriger. Je n'ai touche aucune de ces sections. **Cinq faits, dont un qui est mon erreur avant
d'etre la sienne.**

### 5.1 *"La premiere fois que `nadia-prof` ne sous-estime pas son propre sprint"* - faux, et c'est
### mon erreur qu'elle recopie

Section 1. La phrase s'appuie sur *"elle s'etait trompee deux fois de suite dans ce sens (s29,
s30)"*, qui vient de mon propre arbitrage prealable de s31 : *"j'ai sous-estime l'equipe deux fois
de suite."* **J'ai verifie ce que j'avais moi-meme enregistre avant de m'en servir, et c'est faux
pour s30.**

| Sprint | Mon attente annoncee | Resultat | Ou tombe le resultat |
|---|---|---|---|
| s29 | 7 a 8 lignes | 9 | **au-dela** de ma fourchette : sous-estimation reelle |
| s30 | 3 a 4 lignes | 4 | **dans** ma fourchette, a son sommet |
| s31 | 5 a 7 lignes | 7 | **dans** ma fourchette, a son sommet |

**Une sous-estimation, pas deux.** s31 est le **second** sprint dont le resultat tombe dans ma
fourchette annoncee, pas le premier, et il y tombe dans la meme position qu'a s30. **La distinction
qui tient, elle, et que je propose a sa place** : a s30 le sommet de ma fourchette **etait** le
plafond arithmetique (4 sur 4), je n'avais aucune place pour sous-estimer ; a s31 le plafond etait 8
et je me suis arretee a 7 en donnant un motif arithmetique. **s31 est donc le premier sprint ou ma
fourchette tient alors qu'elle laissait de la place au-dessus d'elle** - ce qui est un fait plus
petit que celui qu'elle a ecrit, et vrai.

### 5.2 *"Ecrits le meme soir"* - faux de neuf heures, et cela change le constat

Section 6, deuxieme candidat. Le rapport de qualite est commite le 2026-09-07 a 21:53 ; **le
garde-fou du registre l'est a 12:31 le meme jour, avant l'ouverture du planning a 14:49**. Seule la
confirmation de la porte technique par l'architecte est du meme soir (21:47). **Ce n'est pas un
detail d'horaire : c'est le motif pour lequel ce candidat ne compte pas.** Un garde-fou deja
commite, deja fusionne, present dans l'arbre de base de l'auteur du rapport et **mute par lui**
n'est pas l'autre moitie d'une jointure - c'est un garde-fou de son propre perimetre, qu'il restait
a rejouer apres avoir ecrit.

### 5.3 La section 6 presente comme non tranche un point que mon instance A a tranche aujourd'hui

Elle ecrit *"ce point est tranche par `nadia-prof` a la section 7"* et pose quatre candidats. **Elle
ne cite pas la section 5 de `verdicts-nadia-instance-A.md`, ou le constat est deja prononce**, et
**son inventaire de candidats ne porte pas l'occurrence sur laquelle ce constat repose** - le
garde-fou du registre rouge a l'assemblage du planning, qu'elle a elle-meme qualifiee de *"defaut de
couture au sens de `nadia-prof`, forme (c)"* dans un message de commit, et qu'elle classe en section
4 comme *"ferme avant l'ouverture du sprint"*. **Ce n'est pas un fait faux, c'est une omission**, et
je la signale parce qu'un tiers qui lit la seule review croira que ma porte etait ouverte alors
qu'un verdict commite l'avait deja franchie. La synthese de `camille-po` dans la matrice, elle,
route ce fait et cite la section.

### 5.4 Hors des six sections : deux lignes de la matrice ont huit cellules pour sept colonnes

**Ce n'est pas dans `review.md`, c'est dans `squad/programme/MATRICE-couverture.md`**, commit de
cloture produit, et je le signale a `sofia-sm` pour `camille-po` parce que cela efface la trace de
mon propre reaudit.

Les deux lignes de `melanges` et d'`objet-technique` separent le statut de s30 de la note de reaudit
de s31 par une **barre verticale non echappee**. Le tableau declare sept colonnes ; ces deux lignes
en portent huit. **La huitieme cellule est celle qui dit que `MEL-R1`, `MEL-R2`, `MEL-R3`, `OBT-R1`
et `OBT-R2` sont levees** - c'est-a-dire le resultat de deux des six stories du sprint. Un lecteur
du fichier brut la lit ; **un rendu markdown, qui n'affiche pas les cellules au-dela de l'en-tete,
ne l'affiche pas.**

Ce sont **les deux seules lignes des 209** dans ce cas, donc ce n'est pas une convention du fichier.
Le statut lui-meme n'est pas touche : les deux lignes restent `couvert-valide` et les deux comptes
restent justes. **Ce qui disparait est la trace du paiement des reserves, pas le statut.**

Je decris le symptome et je m'arrete la : le contenu et le statut appartiennent a `camille-po`, et
**l'absence d'un garde-fou qui compterait les cellules d'une ligne de la matrice** appartient a
`theo-archi`, qui decidera si elle vaut une regle ou une dette nommee. Je ne prescris ni l'une ni
l'autre.

### 5.5 Ce que le job `chemins` compte contre, puisqu'il ne compte pas contre la couture de s31

**J'ai rejoue le balayage exact de la CI moi-meme**, sur la liste des fichiers suivis, plutot que de
croire le rapport : **une occurrence, dans le fichier de relecture de s30, a sa ligne 102**. Le
rouge est reel et il est bien herite.

Ce qu'il compte contre n'est pas la couture de s31, c'est **l'affirmation que s30 a ete clot sur une
porte technique verte**. Elle etait fausse le jour ou elle a ete ecrite, et elle l'est restee un
sprint entier. Je n'ai pas de prescription technique a faire ici et la cause - une phrase de
`CLAUDE.md` qui assimile la CI a une seule commande - est deja portee a la retro avec l'avis du
`relecteur-pedagogique` demande. **Ce que j'ai a dire est d'un autre ordre, et c'est de mon
ressort** : le job qui est reste rouge un sprint est celui qui garde les conventions d'ecriture du
depot, la meme famille que celui qui garde le texte lu par l'enfant. **Une porte crue verte pendant
un sprint n'a rien garde pendant un sprint** - et c'est exactement l'argument que j'oppose depuis
s25 a l'idee qu'une CI verte puisse tenir lieu de Done pedagogique.

### 5.6 Un garde-fou m'attribue un plafond que je n'ai pas ecrit

`ADR-0086` amende porte, sur la borne de `FAB-1b`, la phrase *"la lecture de `nadia-prof` du
2026-09-08 autorise un materiau sur deux proprietes differentes et pas davantage"*, et fixe la borne
a 2.

**Le fait que j'ai mesure est exact et je le maintiens** : l'ancienne ecriture acceptait cinq bonnes
reponses identiques sur six, et *varier* n'est pas *ne pas etre constant*. **Ce que je n'ai pas
ecrit, c'est le plafond.** Ma clause dit que la repetition est licite *"des lors que la bonne reponse
varie"* ; j'ai cite le plastique sur la souplesse et l'impermeabilite comme une **mesure du contenu
servi**, pas comme une limite. **Le 2 est une decision d'architecture, mesuree, et je ne la
contredis pas** - un garde-fou plus strict que ma barre est dans son droit, et celui-la est utile.
**Il ne doit simplement pas etre presente comme ma lecture.**

Et je nomme sa consequence, qu'un tiers doit connaitre : **la borne est atteinte sans marge sur le
paquet servi**. Un troisieme item legitime sur un meme materiau ferait rougir le build pour une
raison qui n'est pas pedagogique. Je dis le symptome ; la forme appartient a `theo-archi`.

---

## 6. Les proprietes sur lesquelles je reste la seule garde

**Je les nomme parce que, sans cette liste, l'equipe croira couvert ce qui ne l'est pas.** Deux
d'entre elles ont ete declarees non encodables ce sprint **avec leur chiffre**, ce qui est la bonne
maniere de ne pas encoder ; les autres n'ont jamais eu de garde-fou.

| Propriete | Etat du garde-fou | Qui garde |
|---|---|---|
| **Un item teste la propriete qu'il annonce** | **aucun**, declare non encodable avec sa mesure : le critere voisin marque 673 paires dans 22 paquets et ne distingue pas les deux items dont j'ai reserve l'un et autorise l'autre | **moi seule**, en jouant |
| **Deux items n'enseignent pas le meme fait sous deux habillages** | **aucun**, declare non encodable avec sa mesure : le moins mauvais proxy marque 30 paires dont 29 dans des paquets valides | **moi seule** |
| **Un distracteur est un candidat plausible** (`PLA-1`) | **aucun** : c'est un jugement de sens, jamais une chaine de caracteres | **moi seule** |
| **`FAB-1c`, la fabrication qui designe la bonne reponse par un marqueur** | **partiel depuis ce sprint**. La clause neuve mord le paquet que j'ai refuse, seul, sur 4 items - mais c'est un **lexique enumere** : un marqueur de restriction hors de la liste passe vert. C'est ecrit dans l'`ADR` par son auteur, a son propre desavantage | **la clause pour les mots listes, moi pour tous les autres** |
| **La lisibilite reelle au niveau vise** - longueur, vocabulaire, charge | **aucun** au-dela d'un comptage de longueur | **moi seule** |
| **La couture entre deux moities paralleles** | **aucun**. Trois occurrences en trois sprints, aucune attrapee par autre chose qu'un garde-fou de comptage ou une relecture | **moi seule**, et c'est pourquoi la retenue existe |

**Le fait qui justifie que je joue plutot que je lise n'a pas change** : quatre defauts visibles par
un enfant ont traverse une CI entierement verte. Ce sprint en ajoute un de la meme famille dans
l'autre sens - un job rouge a traverse un sprint entier sans etre vu.

---

## 7. Ce que ce document ne fait pas

- **Il ne rouvre aucun de mes verdicts**, ni les sept qui ferment, ni le refus maintenu sur la ligne
  de la regle, du souhait et de la loi.
- **Il ne modifie ni la matrice, ni le backlog, ni aucun `ADR`, ni aucun `DEC`, ni les sections 1 a
  6 de `review.md`.** Les faits du point 5 sont signales, pas corriges.
- **Il n'ecrit ni la retro, ni le rapport de sprint** : ils appartiennent a `sofia-sm`.
- **Il ne prononce pas le Done technique** et ne se substitue pas a lui : il appartient a
  `sofia-sm`, et il n'est pas acquis a l'heure ou j'ecris.
- **Il n'alloue aucun identifiant neuf** : mes deux prononces n'ouvrent aucune reserve. Le compte
  courant du registre reste celui de l'assemblage, que j'ai recompte sans le modifier.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu quatre paquets de contenu, un catalogue et deux tests ; je n'y ai rien ecrit.
- **Il ne corrige ni orthographe, ni accentuation, ni ponctuation**, y compris dans les textes
  destines a l'enfant : ce controle appartient au `relecteur-pedagogique` (`DEC-0002` amende,
  `DEC-0056`).
