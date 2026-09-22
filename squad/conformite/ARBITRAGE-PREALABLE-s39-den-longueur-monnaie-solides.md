# Arbitrage prealable - s39, quatre lots (`S39-DEN`, `S39-LONGUEUR`, `S39-MONNAIE`, `S39-SOLIDES`)

- **Auteur** : `nadia-prof`. **Date** : 2026-09-22. **Sprint** : s39.
- **Ce qui m'est pose** : `DEC-0092`, section 5, et les quatre stories correspondantes de
  `docs/backlog/backlog.md`, section "Sprint 39" - arbitrage pedagogique prealable, avant tout avis
  de faisabilite de `theo-archi`.
- **Ce document ne prononce aucun Done, pedagogique ou technique.** Aucun paquet n'existe encore
  pour aucune des quatre lignes (`non couvert` dans `MATRICE-couverture.md` a la date de ce
  document) : il n'y a rien a jouer. C'est un audit degrade au sens de ma methode, et je le dis
  plutot que de le laisser croire. Ce que j'ai fait a la place : relu le referentiel
  (`squad/programme/mathematiques/competences-cp.md`, `competences-ce1.md`), relu mes propres
  arbitrages anterieurs cites plus bas, et **lu le code de production** de la mecanique de
  designation pour verifier moi-meme, plutot que de recopier, ce que `DEC-0092` affirme sur son
  incapacite a servir `S39-DEN`.
- **Lien** : `DEC-0092`, `DEC-0089`, `ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`
  (`DEN-2`), `ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-
  satisfaisabilite.md` (`DEN-2` reconfirmee), `squad/programme/MATRICE-couverture.md` (figures
  planes CP, lignes 189-192 ; monnaie CP, ligne 160), `squad/conformite/FICHE-bandit-manchot-
  monnaie-cp.md` (`MON-1` a `MON-5`), `squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`
  (`LON-CE1-1`, `LON-CE1-2`), `domain/src/main/java/com/recreo/domain/mechanics/designation/
  Designation.java`, `DesignationSession.java`, `domain/src/main/java/com/recreo/domain/content/
  ZoneADesigner.java`.
- **Amendements du 2026-09-22** (apres l'avis de faisabilite de `theo-archi`,
  `squad/sprints/s39-couverture-maximale-six-lignes-candidates/avis-faisabilite.md`, sections 18.2
  et 12) : `MES-CE1-3` (distracteur "confusion d'unite", section 2) et `SOL-CE1-1`/`SOL-CE1-4`
  (forme de la boule, section 4). Voir les blocs "Amendement du 2026-09-22" dans chaque section.

---

## 1. `S39-DEN` (`MAT-CP-01`) - `DEN-2` tient telle quelle, je ne l'amende pas

**Verdict : `DEN-2` reste en vigueur, non negociable, telle qu'ecrite le 2026-08-24 et reconfirmee
le 2026-08-30. Je ne l'amende pas pour une mecanique existante, parce qu'aucune mecanique
existante ne la satisfait - je l'ai verifie moi-meme sur le code, pas sur la prose de `DEC-0092`.**

`DEN-2` exige, pour que le verbe *denombrer* soit servi, une collection **dessinee, groupee par
dix, sur laquelle l'enfant agit** (deplacer, marquer, grouper) - pas un simple dessin statique, et
pas une reconnaissance visuelle d'un dessin deja compte a sa place.

J'ai lu `Designation.java`, `DesignationSession.java` et `ZoneADesigner.java`, la seule mecanique
visuelle existante avec `item illustre`. Ce qu'elle fait reellement : une manche montre **une image
fixe** et un ensemble **fini et pre-declare** de zones ; l'enfant en designe **une seule**, en au
plus trois essais, et la manche se resout. Rien dans cette boucle ne permet une sequence d'actions
sur les elements d'une meme collection (compter en touchant chaque element, grouper des elements
entre eux, deplacer un element d'un tas a un autre) : `DesignationMove` porte un seul choix de zone
par coup, et une zone designee ne change jamais d'etat au sein d'une manche - elle est essayee ou
non, jamais deplacee ni regroupee. Une collection dessinee et deja groupee par dix, presentee comme
une des zones a designer parmi plusieurs images candidates, ne ferait pas *denombrer* : l'enfant
reconnaitrait une image parmi d'autres, sans jamais compter - exactement le defaut que
`MATRICE-couverture.md` ligne 189 exclut deja explicitement ("pas un simple dessin statique").

**Consequence** : `S39-DEN` ne peut pas fermer `MAT-CP-01` a `couvert-valide` dans ce sprint, quel
que soit le soin mis au paquet de `DEN-1` a `DEN-8`. La ligne reste `en cours` au mieux. Construire
la mecanique qui satisferait `DEN-2` est un chantier d'architecture reel (nouvelle interaction,
nouvel etat de session, nouvelles regles ArchUnit) : je ne juge pas s'il est raisonnable dans ce
sprint ou dans un sprint neuf - c'est a `theo-archi` de le faire, sur la base de ce constat verifie.

## 2. `S39-LONGUEUR` (`MAT-CE1-09`) - deux verbes distincts, un seul servable par le texte seul

La ligne dit : *"Mesurer et comparer des longueurs avec une regle graduee ; utiliser le centimetre
et le metre."* Deux verbes de nature differente y sont attaches, et je les separe plutot que de les
traiter comme un bloc, sur le meme principe que `DEN-2` separe *denombrer* de *lire/ecrire/
decomposer" :

- **"Mesurer... avec une regle graduee"** exige de lire une graduation sur un instrument. C'est un
  acte visuel et instrumente, de la meme famille que "lire l'heure sur une horloge" (`MAT-CE1-11`),
  deja note `non couvert` "faute de ce rendu" dans la matrice. **Aucun rendu visuel de regle
  graduee n'existe dans le depot.** Un paquet purement textuel ne peut pas servir ce verbe : donner
  un nombre de centimetres dans un enonce n'est pas mesurer, c'est deja avoir mesure a la place de
  l'enfant.
- **"Comparer des longueurs"** et **"utiliser le centimetre et le metre"** (convertir, ordonner,
  exprimer dans l'unite appropriee) sont servables par du texte seul : ce sont des operations
  numeriques sur des longueurs deja exprimees en chiffres, qui ne demandent pas de lire une image.

**Verdict : un paquet purement textuel ne sert que "comparer" et "utiliser cm/m", jamais "mesurer
avec une regle graduee".** Suivant le meme principe que j'ai applique a `DEN-2`, la ligne
`MAT-CE1-09` ne peut pas passer `couvert-valide` sur un paquet textuel seul - elle peut au mieux
passer `en cours`. Un rendu visuel de regle graduee (palier 2, `item illustre`, sur le meme registre
que `FIG-1` a `FIG-4`) resterait a construire pour atteindre `couvert-valide` ; je ne prejuge pas sa
faisabilite dans ce sprint, question technique pour `theo-archi`.

**Bornes pour la part textuelle, developpable des maintenant :**

> **`MES-CE1-1`** - les longueurs tirees restent entre 1 cm et 500 cm (5 m), sur des objets ou
> distances plausibles pour un enfant de CE1 (un crayon, une table, une salle de classe) - jamais
> une valeur microscopique ou disproportionnee qui ne correspond a rien de concret.

> **`MES-CE1-2`** - une conversion cm/m ne porte que sur des cas simples ou le nombre de metres est
> entier (1 m = 100 cm) ; pas de millimetres ni de fractions de centimetre, hors programme CE1.

> **`MES-CE1-3`** - les distracteurs plausibles sont une confusion d'unite (repondre en cm la
> valeur attendue en m, ou l'inverse) ou une erreur de conversion par un facteur autre que 100
> (le plus frequent chez l'enfant : diviser ou multiplier par 10 au lieu de 100) ; jamais une
> longueur negative ni une valeur absurde.

## Amendement du 2026-09-22 (apres avis de faisabilite de `theo-archi`) - `MES-CE1-3`, le distracteur "confusion d'unite"

`theo-archi` a signale que `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`
(`ADR-0041`, clause `lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture`) refuse
tout item dont les propositions laissent des restes d'unite differents, et cite explicitement le
cas *"'cm' face a 'm'"*. J'ai relu ma propre formulation avant d'arbitrer : le premier volet de
`MES-CE1-3` (*"repondre en cm la valeur attendue en m, ou l'inverse"*) demande deux propositions
d'unites differentes dans un meme item - il tombe directement dans ce cas.

Je verifie le motif du garde-fou plutot que de le contourner. Il est juste, et il me fait voir une
faute que je n'avais pas vue moi-meme : `300 cm` et `3 m` ne sont pas une bonne reponse face a une
fausse, ce sont **deux ecritures de la meme longueur**. Le distracteur que j'avais pose n'aurait
donc pas ete une erreur, il aurait ete la bonne reponse deguisee - une faute pedagogique
independante du garde-fou technique, que je n'ai vue qu'en vérifiant l'`ADR`.

**Je retire le premier volet de `MES-CE1-3`** et je ne le remplace pas par une autre forme a deux
unites : aucune forme de ce genre ne peut a la fois rester dans une seule unite (ce que le
garde-fou exige, a bon droit) et tester "l'enfant confond cm et m" par un simple changement de
label - changer le label sans changer le nombre donne soit la bonne reponse sous une autre forme,
soit un nombre sans justification.

**Le second volet couvre deja la meme famille d'erreur, sous une forme qui tient dans une seule
unite.** *"Diviser ou multiplier par 10 au lieu de 100"* est exactement l'erreur que commet en
pratique un enfant qui confond cm et m : il applique le mauvais facteur de conversion.
`MES-CE1-3` devient :

> **`MES-CE1-3` (version amendee, 2026-09-22)** - les distracteurs plausibles sont une erreur de
> facteur de conversion cm/m (diviser ou multiplier par 10, par 1 - c'est-a-dire ne pas convertir
> du tout -, ou par un facteur autre que 100), **toutes les propositions d'un meme item exprimees
> dans la meme unite que la bonne reponse** ; jamais une proposition exprimee dans une unite
> differente des deux autres ; jamais une longueur negative ni une valeur absurde.

Ce que cet amendement ne fait pas : il ne baisse pas la barre. La borne initiale visait a tester
si l'enfant sait que 1 m = 100 cm ; la borne amendee teste exactement la meme chose, par la voie
que le corpus deja servi (`cm1-mesures`, `7 cm -> 70 mm`, distracteurs `700 mm` et `7000 mm`) a
deja validee. Rien d'autre dans la section 2 n'est modifie : la ligne reste plafonnee `en cours`
pour le motif deja ecrit (absence de rendu de regle graduee).

## 3. `S39-MONNAIE` (`MAT-CE1-13`) - le precedent CP ne s'applique pas, verifie et non presume

`camille-po` signale `MON-1`/`MON-3` (CP) sans presumer leur application au CE1. Je verifie : ils
ne s'appliquent pas, et je dis pourquoi plutot que de l'affirmer.

`MON-1` (`squad/conformite/FICHE-bandit-manchot-monnaie-cp.md`, section 3) exige que les pieces et
billets soient **montres, pas nommes** - parce que la competence CP (`MAT-CP-07`, "reconnaitre et
utiliser la monnaie") porte explicitement un verbe de **reconnaissance visuelle** : l'enfant
identifie une piece a sa taille, sa couleur, sa gravure. La competence CE1 (`MAT-CE1-13`,
"utiliser la monnaie : composer une somme en euros et en centimes, savoir ce qui est rendu") ne
porte **aucun verbe de reconnaissance** : elle presuppose que l'enfant sait deja ce qu'est une
piece de 2 euros (acquis au CP) et lui demande de **composer** et de **calculer un rendu** - une
tache arithmetique, pas une tache de discrimination visuelle. `MON-1` n'a donc pas d'objet a
transposer ici.

**Verdict : un paquet textuel et numerique (sommes exprimees en chiffres, pas d'image de piece
obligatoire) peut servir la ligne entiere, sous les bornes suivantes.**

> **`MON-CE1-1`** - les montants tires restent entre 0,05 euro et 50 euros, avec centimes exprimes
> en multiples de 5 centimes reellement rencontres (0,05 ; 0,10 ; 0,20 ; 0,50, etc.) - jamais un
> montant qu'aucune piece ne permet d'atteindre exactement (par exemple 0,03 euro).

> **`MON-CE1-2`** - seules les denominations qui existent reellement sont utilisables : pieces de
> 1, 2, 5, 10, 20, 50 centimes, 1 et 2 euros ; billets de 5, 10, 20 et 50 euros. Aucune valeur
> inventee.

> **`MON-CE1-3`** - au moins un tiers des items portent explicitement sur le **rendu de monnaie**
> ("qu'est-ce qui est rendu"), pas seulement sur la composition d'une somme - pour que le second
> verbe de la ligne soit reellement servi et pas seulement le premier, meme principe que `MON-3`
> l'exigeait pour "utiliser" au CP.

> **`MON-CE1-4`** - les distracteurs plausibles sont une erreur de rendu par exces ou par defaut,
> ou une confusion entre euros et centimes (ecrire "345" pour "3,45 euros") ; jamais un distracteur
> negatif.

## 4. `S39-SOLIDES` (`MAT-CE1-16`) - le precedent des figures planes s'applique, texte seul refuse

**J'ai relu mon propre refus** (`MATRICE-couverture.md`, lignes 189-192) avant de trancher, comme
je dois le faire avant de m'appuyer dessus : la ligne "reconnaitre et nommer des figures planes"
(CP) a ete **refusee en entier** parce que la discrimination carre/rectangle "tient a une
proportion, et les proportions d'un glyphe sont celles de la police, pas du contenu" - et n'a
ouvert qu'avec un rendu visuel reel, aux proportions **declarees par le contenu** (`FIG-1` a
`FIG-4`), jamais par un texte.

La story propose, comme alternative, un choix textuel par description verbale (exemple donne :
"un solide a six faces carrees identiques"). **Je refuse cette forme comme substitut suffisant au
verbe "reconnaitre".** Deux motifs, pas un seul :

1. **Ce n'est pas de la reconnaissance, c'est de la traduction.** Reconnaitre un solide, c'est le
   voir et l'identifier. Une description verbale qui enumere deja les proprietes definissantes
   ("six faces carrees identiques") fait a la place de l'enfant le travail perceptif que la
   competence vise : l'enfant associe un nom a une definition qu'on vient de lui donner, il n'a
   jamais eu a reconnaitre une forme. Un enfant qui reussit ce jeu pourrait rester incapable de
   nommer un cube reel qu'il tient en main - exactement le risque que je refuse d'ouvrir.
2. **Le risque de fond signale par `camille-po` est reel.** La distinction cube / pave droit est,
   comme carre / rectangle, une question de proportion (aretes toutes egales contre aretes de
   longueurs differentes). La rendre en texte seul reproduit le meme defaut que j'ai deja
   sanctionne : une proportion qui devrait se voir est remplacee par un enonce qui la dit.

**Verdict : `MAT-CE1-16` s'ouvre uniquement par un rendu visuel (`item illustre`), aux proportions
declarees par le contenu, sur le meme registre que `FIG-1` a `FIG-4` :**

> **`SOL-CE1-1`** - chaque solide est dessine avec des proportions reellement declarees par le
> contenu (jamais un texte, jamais un glyphe) : un cube a ses aretes visiblement egales, un pave
> droit a au moins deux longueurs d'arete visiblement distinctes.

> **`SOL-CE1-2`** - au moins trois exemples par solide (cube, pave droit, boule, cylindre, cone),
> variant l'orientation et la taille - meme principe que `FIG-2`.

> **`SOL-CE1-3`** - la couleur n'est jamais discriminante entre deux solides.

> **`SOL-CE1-4`** - cube et pave droit ne sont jamais confondus (rapport d'aretes visiblement
> distinct) ; cylindre et cone ne sont jamais confondus (presence ou absence de pointe visible) ;
> la boule reste sans ambiguite possible.

**Ce que je ne tranche pas** : si cinq solides dessines en projection (isometrique ou cavaliere),
aux proportions fiables, sont constructibles dans la capacite de ce sprint. C'est une question
d'architecture et de charge de contenu - `item illustre` existe deja comme mecanique, seul le
materiau (les cinq dessins) manque, contrairement a `S39-DEN` ou c'est la mecanique elle-meme qui
manque. Je le nomme pour que `theo-archi` ne confonde pas les deux natures de manque.

## Amendement du 2026-09-22 (apres avis de faisabilite de `theo-archi`) - `SOL-CE1-1` / `SOL-CE1-4`, forme de la boule

`theo-archi` a trouve un trou que ni mon arbitrage ni aucun garde-fou existant ne couvrait : une
boule dessinee comme un simple cercle passerait toutes les clauses de geometrie en vigueur, alors
que ce cercle est - par la derivation d'`ADR-0077` (*"un dessin qui ne trace qu'une seule forme
rendue **est** cette forme"*) - exactement un cercle, pas une boule. C'est le meme defaut que
celui que j'ai sanctionne sous `FIG-4` pour les figures planes CP, retourne du cote du volume.

Je tranche la forme visuelle. **Une boule est dessinee comme un cercle traverse par une ellipse
figurant son equateur** - la convention du globe ou du ballon a bande, celle qui distingue
usuellement une representation en volume d'une figure plane des le cycle 2. Cette ellipse doit
etre visiblement une courbe fermee distincte du contour circulaire - ni un simple segment, ni un
arc trop plat pour se voir a 176 px de hauteur - pour qu'un enfant de CE1 y lise "un rond avec une
bande autour", et non un second cercle concentrique ou un decor sans rapport.

**Ce que cela impose concretement, pour que `theo-archi` l'encode** : le dessin porte au moins
deux formes rendues distinctes - le contour circulaire et l'ellipse d'equateur -, ce que la clause
qu'il a deja redigee (section 12 de son avis, *"au moins deux formes rendues"*) verifie
mecaniquement. **Ce que ce garde-fou ne verifie pas, et que je reste seule a garder** : que la
seconde forme est bien une ellipse d'equateur credible, et non un trait quelconque colle au cercle
pour faire passer le compte a deux. Cette part se juge item par item au Done, exactement comme
`theo-archi` l'a ecrit pour le rapport d'aretes du cube et du pave droit (`R7` de son avis) - aucun
automate ne la fera a sa place.

**Ce que je ne tranche pas ici**, hors du perimetre qui m'est pose : le nombre d'ellipses (une
seule, l'equateur, suffit a la clause ; une seconde en meridien ameliore la lisibilite sans etre
exigee), et l'orientation ou la variation des trois exemples de boule (`R6` de l'avis de
`theo-archi`), qu'il a explicitement route en dehors de cette question.

## 5. Ce que ce document ne fait pas

- Il ne prononce aucun Done, pedagogique ou technique.
- Il ne choisit aucun mecanisme technique ni aucune capacite de sprint : ce travail reste a
  `theo-archi` et `sofia-sm`.
- Il ne rouvre ni `DEC-0089`, ni `DEN-2` dans son fond (je le confirme, je ne l'amende pas), ni le
  refus des figures planes CP sur texte seul.
- Il ne garantit `couvert-valide` sur aucune des quatre lignes : `S39-DEN` reste plafonnee `en
  cours` ; `S39-LONGUEUR` reste plafonnee `en cours` tant qu'aucun rendu de regle graduee n'existe ;
  `S39-MONNAIE` et `S39-SOLIDES` peuvent atteindre `couvert-valide`, sous reserve d'un paquet qui
  tient les bornes ci-dessus et d'un Done pedagogique final que je prononce apres avoir joue le
  jar servi - jamais avant.
