# [Sprint 34] Retrospective - 2026-09-11

- **Anime par** : `sofia-sm`

## Ce qui a marche

- **Le plafond de base est atteint pour la premiere fois depuis l'ouverture de la strategie du
  Plan 2.** Six lignes ferment (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`,
  `EPS-CP-11`), contre trois a s32 et trois a s33. `EMC-CP-07`, refusee trois passages de suite,
  ferme au cinquieme.
- **`EMC-CP-07` et `EPS-CP-04`/`EPS-CP-08` renversent, sur les memes paquets et la meme
  developpeuse, un resultat mesure a s33.** L'instance C l'ecrit elle-meme (`verdicts-nadia-
  instance-C.md`, section 4) : sur les deux motifs bloquants qu'elle avait reecrits au format
  `RES-1` avec objet et mode de defaillance nomme d'avance, la correction a suivi l'objet deux
  fois sur deux - a s33, sur ce meme terrain, deux corrections fideles a la lettre avaient produit
  deux refus bloquants.
- **Un rouge reel de la porte technique a ete trouve avant d'annoncer la CI verte, pour la
  quatrieme ou cinquieme fois consecutive.** `yanis-qa` et `theo-archi` avaient chacun mesure les
  quatre jobs verts sur `a7852398`, avant que les huit documents du sprint ne soient tous en
  place ; `sofia-sm` relance les quatre jobs sur l'arbre de travail complet et trouve un
  `BUILD FAILURE` que personne n'avait mesure : la ligature `oe` que le `relecteur-pedagogique`
  a introduite dans un `bonneReponse` de `cp-eps-corps-effort.yaml` ne passe pas
  `PropositionsAccentueesJouablesIT`, qui rejoue chaque proposition en `ISO_8859_1` strict. Sans
  ce geste, s34 se cloturait sur un rouge non vu.
- **`nadia-prof` continue de corriger ses propres constats avant de s'en servir, et de dater la
  correction.** Perimetre de `HG-R17` recorrige (section 1 de l'instance A), abregement de `EPS-3`
  corrige (section 2.1 de l'instance C, qui aurait sinon refuse une ligne qu'elle avait
  elle-meme declaree jouable et financee), perimetres de `SEC-R5` et de `JEU-R11` corriges
  (sections 2.3 et 2.4). Cinquieme sprint consecutif ou cette discipline tient.

## `RES-1` a l'epreuve - trois modes d'echec, mesures par trois instances independantes

C'est le resultat le plus reutilisable de ce sprint parce qu'il ne vient pas d'une seule mesure :
trois instances, sur trois terrains disjoints, posent la meme question - une correction suit-elle
l'objet d'une reserve ou seulement sa lettre ? - et trouvent trois reponses de nature differente.

- **Une reserve sans objet ecrit n'est pas payee.** Instance C, sur une population de six
  reserves reconduites : **quatre portaient un objet ecrit, les quatre ont ete payees a l'objet ;
  deux n'en portaient aucun, aucune des deux n'a ete payee.** *"La correlation n'est donc pas
  reserve bloquante contre reserve non bloquante, c'est reserve qui porte un objet contre reserve
  qui n'en porte pas."*
- **Une reserve ecrite en enumeration voit une partie seule traitee, et la correction s'annonce
  payee en entier.** Instance A, sur deux echecs parmi sept reserves de contenu : `HG-R17` clause
  2 nommait deux items, un seul est corrige ; `ANG-R3` nommait trois items, deux sont corriges -
  dans les deux cas l'objet etait present et bon, c'est le perimetre qui a ete retreci en silence
  sur un sous-ensemble de l'enumeration.
- **Une condition rangee hors du bloc qui la fait lire ne compte pas, meme ecrite le meme jour
  par la meme personne.** Instance B, sur `cp-emc-exclusion` : la condition qui manque -
  *"l'indice dit pourquoi attendre ne marche pas"* - avait ete ecrite par elle-meme, la veille,
  dans la meme fiche, mais enfouie dans une reponse a une question plutot que listee dans le bloc
  *Mode de defaillance* de la story. *"Une condition rangee ailleurs que dans le bloc qui la fait
  lire ne compte pas."*

**Ce que cela change pour s35** : une reserve qui porte plus d'un element (une enumeration
d'items, une clause en deux parties) doit soit se solder element par element - chacun avec son
propre etat, leve ou non - soit un audit explicite reverifie l'ensemble avant qu'une correction ne
soit annoncee payee. `nadia-prof` l'ecrit elle-meme comme un chantier pour son propre arbitrage
prealable (instance A, section 8 ; instance C, section 2.4) ; ce n'est pas une clause neuve a
poser en cours de sprint, c'est une discipline de redaction a appliquer des le prochain arbitrage.

## Deux regles justes composees donnent un rouge

Le `relecteur-pedagogique` corrige `coeur` en `coeur` avec ligature sur quatre occurrences de
`cp-eps-corps-effort.yaml` - juste en francais, c'est du texte lu par un enfant qui apprend a
lire, et la regle de redaction du produit l'exige. `PropositionsAccentueesJouablesIT` rejoue
chaque proposition non-ASCII du catalogue reel dans une requete encodee en `ISO_8859_1` strict et
refuse tout caractere au-dela de `0xFF` - juste comme garde-fou technique, et vert depuis sa
creation parce qu'aucune ligature n'etait jamais entree dans un `bonneReponse` avant ce sprint.
**Aucun des deux n'a tort, et leur composition casse la porte technique.** `sofia-sm` l'a trouve en
lancant la commande de la CI sur son propre diff avant de commiter, pas en deduisant la porte verte
des mesures anterieures de `yanis-qa` et de `theo-archi` sur `a7852398` - c'est la quatrieme ou
cinquieme fois que ce geste, exige depuis l'amendement du 2026-09-08 a `CLAUDE.md`, attrape
quelque chose que personne n'avait vu avant. Sans lui, s34 se cloturait sur un rouge annonce vert.
Un stopgap minimal et reversible (l'occurrence en cause revient a `coeur` sans ligature) rend le
diff committable ; la question de fond - cette IT doit-elle couvrir la ligature, deja legitime
ailleurs dans le depot - reste ouverte et routee a `theo-archi`.

## Trois garde-fous qui mesurent autre chose que le defaut

Trois occurrences de la meme famille, dans un seul sprint :

- **`theo-archi`** mesure que le garde-fou du plancher de cible tactile (12,5 % du plus petit
  cote) est vert sur les six cadres du plan de classe pendant que trois d'entre eux (`tableau`,
  `armoire`, `porte`) rendent, mesures au jar par `lea-dev`, un libelle qui deborde de son bouton
  et chevauche les zones voisines. *"Le plancher de cible tactile n'est pas la propriete le
  libelle tient dans sa zone. Deux proprietes voisines, une seule gardee, et c'est la seconde qui
  a coute une ligne de matrice ce sprint."*
- **L'instance B** trouve, en regardant l'ecran rendu plutot que le HTML servi, que les boutons de
  zone couvrent le dessin qu'ils designent : `.zone` pose un fond blanc a 55 % et un texte gras de
  20 px par-dessus l'image, et sur la frise de la journee les six boutons couvrent tout le
  dessin. Ce n'est pas un defaut du contenu de `lea-dev` et cela frappera tous les paquets de
  designation a venir.
- **`marc-dev`** avait trouve, au sprint precedent sur `cp-emc-exclusion`, un test vert sur
  population vide - la meme famille, mesuree pour la troisieme fois par `theo-archi` dans son
  avis de s34.

Un garde-fou vert qui ne garde pas ce qu'on croit n'est pas un cas isole a s34 : c'est un motif qui
traverse le sprint sur trois terrains distincts (CSS de cible tactile, recouvrement visuel de
bouton, vacuite de boucle de test).

## La regle de poste du registre n'a pas tenu : cinquieme occurrence

`squad/README.md`, section 9, ecrit a l'ouverture meme de s34 : une instance de `nadia-prof` qui
ecrit dans `squad/decisions/REGISTRE-identifiants.md` le fait depuis son propre worktree, jamais
dans le checkout partage. **Les trois instances ont ecrit dans le checkout partage ; aucune n'a
ecrit dans le sien.** `theo-archi` mesure que deux des trois avaient un worktree ouvert et ont
quand meme ecrit ailleurs - ce n'est pas un manque d'outil, c'est un manque d'usage. Il le porte
contre lui-meme : *"le bon destinataire et le mauvais mecanisme"* - il avait conclu qu'aucun test
ne peut voir cette regle, et en avait deduit qu'une regle de poste ferait l'affaire ; la seconde
moitie vient d'etre dementie par l'usage, le lendemain de sa redaction.

**Une regle de conduite ecrite cinq fois et enfreinte cinq fois n'est pas une regle.** Ce qui la
remplacerait, et que `theo-archi` nomme sans le trancher : une contrainte d'orchestration - une
instance ne recoit pas le chemin du checkout partage - qui ne se decide ni dans un `ADR` ni dans
un test, mais chez qui lance les instances. C'est donc une action pour `sofia-sm`, pas une
nouvelle phrase pour `squad/README.md`. Le cout mesure reste borne : les deux modes du defaut sont
un faux rouge transitoire (nomme, il se referme des que la derniere instance ecrit) et un faux
vert emprunte (une ligne d'etat courant juste sur l'arbre d'une instance et fausse des qu'une autre
alloue apres elle) - tous deux reparables a l'arithmetique, sans qu'aucune allocation ne se perde.
`theo-archi` le dit lui-meme : le jour ou ce defaut coute autre chose qu'une correction
arithmetique, il cesse d'etre une gene locale et merite un `ADR`.

## Le trou de la partie jouee, troisieme sprint de suite

`yanis-qa` : aucune trace dans le depot Git d'une partie jouee de bout en bout sur le jar pour les
six reprises de contenu fermees ce sprint (`HG-CM1-12`, `cm1-anglais`, `cp-emc-exclusion`,
`cp-emc-regle-souhait-loi`, les trois paquets d'EPS). Meme trou signale a s32 et a s33, non paye
pour la troisieme fois. **Dans le meme sprint, la seule partie reellement jouee au jar** -
`lea-dev`, sur le palier 2, Chrome headless, avant de commiter - **a trouve un vrai defaut**, les
libelles qui debordent, avant tout garde-fou et avant toute lecture de rapport vert. *"C'est la
preuve que cette pratique marche"*, et elle reste l'exception plutot que la regle : les trois
instances de `nadia-prof` jouent bien le jar pour leur propre audit pedagogique, mais aucun
developpeur ne laisse de trace Git d'une partie jouee avant de commiter son propre lot.

## Le plafond atteint, le palier 2 manque

Six lignes sur six pour le plafond de base (75/138 = 54,3 %) : le sprint reussit exactement ou il
avait prevu de reussir. Les deux lignes du palier 2 ne ferment pas : `QLM-CP-14` est refusee
(`FRI-R1`, `FRI-R2`, bloquantes), `QLM-CP-11` n'a jamais ete livree - la geometrie de deux cadres
et le plancher de lisibilite d'un libelle de zone restent a trancher, routes a `ines-design` et a
`nadia-prof`. Le sprint echoue exactement ou il avait nomme son risque principal des le planning
(section 8, point 3) : *"si `L-c` n'est pas livre a la cloture, `L-e` ne peut pas s'ouvrir"*. Ce
n'etait pas le cas - `L-c` est livre - mais le contenu du palier 2 reste un chantier neuf, sa
premiere epreuve reelle des huit conditions `ZON-*`, et les deux motifs de refus de `QLM-CP-14`
sont tous deux anterieurs au sprint : le texte alternatif contrefaisant l'image existe depuis s29,
le pictogramme du lever n'a jamais ete regarde a la taille servie avant cette instance.

**Le registre de dette monte de 9,0 a 9,5 malgre six lignes fermees**, entierement du fait d'un
poste neuf (`cp-eps-corps-effort`, +0,5, sept reserves non soldees dans le meme sprint faute de
fenetre) : les deux paquets qui se clarifient (`cp-emc-regle-souhait-loi`, `cp-eps-regles-de-
securite`) rouvrent chacun une dette de meme montant dans le meme geste, sans effet net - exactement
le mecanisme que `DEC-0086` avait nomme comme la raison d'abandonner la projection du registre. Un
sprint qui ferme six lignes ne fait pas necessairement baisser la dette.

## Actions pour s35, nommees et affectees

| Action | Affectee a | Motif |
|---|---|---|
| Solder une reserve a plusieurs elements (enumeration d'items, clause en deux parties) element par element, ou reverifier explicitement l'ensemble avant d'annoncer une correction payee | nadia-prof | `HG-R17`, `ANG-R3` - perimetre retreci en silence sur un sous-ensemble |
| Recopier toute condition d'acceptation posee en repondant a une question dans le bloc *Mode de defaillance* de la story qu'elle gouverne, avant l'ouverture de la branche | nadia-prof | `cp-emc-exclusion` - condition ecrite le jour meme, non lue parce que rangee ailleurs |
| Trancher la question de fond sur `PropositionsAccentueesJouablesIT` et la ligature hors Latin-1 (etendre l'IT ou circonscrire la regle de redaction) | theo-archi | Rouge reel trouve sur le diff de ce sprint, stopgap applique |
| Ecrire le garde-fou de `bootstrap/rendu` qui sert l'ecran de manche du jeu reel de designation et y rejoue les criteres des garde-fous 1 et 3 d'`ADR-0080` (plancher de cible tactile, bouton du formulaire) | theo-archi | Le garde-fou 3 aurait laisse passer le defaut qui a fait retirer `cp-plan-de-la-classe` |
| Statuer sur le recouvrement des boutons de zone sur le dessin qu'ils designent, proprite transverse a tout paquet de designation | ines-design, theo-archi | Constat de l'instance B, non corrige a ce jour |
| Poser une contrainte d'orchestration qui empeche une instance de `nadia-prof` d'ecrire dans le checkout partage, plutot qu'une regle de conduite ecrite une sixieme fois | sofia-sm | Cinq occurrences du meme defaut malgre une regle ecrite et relue |
| Router la geometrie de `QLM-CP-11` (`armoire`, `porte`) a `ines-design` et le plancher de lisibilite d'un libelle a `nadia-prof`, aucune moitie ne suffisant seule | ines-design, nadia-prof | `QLM-CP-11` non livree pour un quatrieme sprint |
| Ouvrir une story qui porte l'ecran de fin de partie (ce que l'enfant lit apres le remede de score), refusee ce sprint (`SCO-R1`) | camille-po | `S34-SCO1-REMEDE` refuse, trou de perimetre nomme d'avance par `nadia-prof` |
| Traiter les cinq indices de la tache B de `cp-emc-exclusion` dans un meme geste (`EXC-R10` bloquant, `EXC-R11`, `EXC-R12`) | camille-po, marc-dev | `S34-EXC-TACHEB` refuse |
| Laisser une trace Git verifiable d'une partie jouee de bout en bout avant de commiter un lot de contenu repris, sur le meme modele que la mesure de `lea-dev` au jar sur le palier 2 | marc-dev, lea-dev, sofia-sm (casquette dev) | Troisieme sprint sans cette trace, pendant que la seule partie jouee a trouve un vrai defaut |
| Ouvrir `S34-CONS-4` (quatre entrees en remplacement de `bandit-manchot-histoire-geographie-cm1`), non engagee ce sprint | camille-po (story), marc-dev (execution), nadia-prof (audit) | Non commencee, fenetre de s34 fermee avant son ouverture |

## Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise pendant cette retro. Aucun
amendement n'est fait a `squad/README.md` : la regle de poste sur le registre d'identifiants,
ecrite a l'ouverture de s34, n'est pas remplacee ici par une nouvelle phrase de procedure - son
remplacement est une action d'orchestration affectee ci-dessus, pas un texte a inscrire tant qu'il
n'est pas ecrit.
