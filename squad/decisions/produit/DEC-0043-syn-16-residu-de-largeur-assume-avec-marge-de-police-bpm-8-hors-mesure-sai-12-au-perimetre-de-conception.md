# DEC-0043 - `SYN-16` : residu de largeur assume avec marge de police exigee ; `BPM-8` reste hors
# de cette mesure ; `S19-6` renvoyee a s20 ; `SAI-12` entre au perimetre de la conception de s19

- **Type** : arbitrage de perimetre produit, denouement d'un avis de faisabilite
- **Date** : 2026-08-24
- **Auteur** : camille-po
- **Sprint** : s19
- **Competence programme visee** : transverse - pas de contenu pedagogique. Aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge par cette decision.
- **Statut** : Acceptee

## Contexte

`theo-archi` a rendu `S19-6` (`SYN-16`/`BPM-8`, le pli entre 576 et 719 px de viewport)
**infaisable en l'etat sur la bande entiere** par un correctif de largeur
(`squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/avis-faisabilite.md`, section 6), sur
une arithmetique tiree des chiffres de s18 : `680 px` etait la seule largeur de rangee connue
comme *suffisante* aux seize items de `cm1-synonymes`, sa largeur *necessaire* n'avait jamais ete
mesuree, et sans elle son verdict restait entier. Il nomme lui-meme la mesure qui manque et
refuse de l'inventer.

`nadia-prof` a rendu cette mesure (branche `s19-nadia`, commit `cf78a0d`, non fusionnee -
`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, bloc du 2026-08-24, releves complets
sous `squad/conformite/releves-s19/`) : la largeur necessaire est **539 px**, pas 680 px. Elle en
tire, sur l'arithmetique meme de `theo-archi` qu'elle confirme exacte, qu'un correctif de largeur
est faisable de **579 a 719 px** de viewport et echoue seulement sur **576, 577, 578 px** - soit
**141 largeurs de la bande sur 144**. Elle pose une reserve explicite : 539 px est mesure sur la
pile de polices de sa machine, et 0,6 % de largeur de texte en plus suffit a reculer le pied de la
bande de 579 a 583 px. Elle refuse d'elle-meme de valider un plafond colle au strict necessaire
sans qu'on en ait parle, et me route la question comme un arbitrage de perimetre, pas un arbitrage
technique.

C'est a moi de dire si 141 pixels sur 144 suffisent a fermer `SYN-16`, et ce que le produit exige
de marge autour de ce chiffre. Ce que je verifie avant de repondre, et qui change la reponse : la
mesure de `nadia-prof` ne porte que sur `cm1-synonymes` (`SYN-16`). Elle ne porte pas sur
`cm1-homophones` (`BPM-8`), alors que `S19-6` bundle les deux criteres depuis son ecriture au
backlog.

## Decision

### 1. `SYN-16` : le correctif de largeur est retenu, avec une marge de police exigee, pas un plafond colle au strict necessaire

**Je retiens la piste de largeur plutot que la piste verticale pour fermer l'essentiel de la
bande.** `theo-archi` a lui-meme nomme la piste verticale comme *"plus petite"* (43,19 px contre
180 px), mais elle suppose un `ADR` neuf pour un terrain que ce depot ne garde nulle part
aujourd'hui (*"aucun garde-fou de ce depot n'epingle un terme vertical"*), et elle reste a
instruire par `ines-design` et `theo-archi` avant meme d'etre chiffree. La piste de largeur, elle,
est deja outillee (`ADR-0054` a `ADR-0056`), deja mesuree deux fois par `nadia-prof` sur des
methodes distinctes et concordantes au centieme, et ferme 141 largeurs sur 144 pour un cout que
personne n'a besoin de chiffrer a nouveau. Retenir la piste la plus couteuse a instruire pour
fermer trois pixels de plus que la piste la moins couteuse n'a pas de sens produit ; je ne ferme
pas la porte a la piste verticale pour autant, voir section 4.

**Le plafond retenu ne colle pas au strict necessaire mesure sur une seule machine.** `nadia-prof`
l'ecrit elle-meme : au sommet de la bande la marge est de 140 px et rien ne la menace, au pied
elle est nulle par construction si le plafond vaut exactement 579 px. Un plafond cale sur la
valeur mesuree aujourd'hui (539 px de rangee necessaire, sur la pile de polices d'une seule
machine) est exactement le genre de garde-fou qui tient tant que rien ne bouge et cede en silence
au premier changement de police sur un appareil reel de la cible - le mode de defaillance que ce
depot nomme deja ailleurs *ecart muet*, et qu'aucun test ne detecterait ici puisque `nadia-prof`
l'a deja ecrit : rien dans ce depot ne verifie le nombre de rangees qu'occupent des propositions a
l'ecran.

**Ce que j'exige, sans fixer la valeur** : le plafond retenu porte une marge de securite de police
au-dessus des 539 px necessaires mesures aujourd'hui, distincte du seuil brut. La valeur exacte de
cette marge est une question de forme, comme `nadia-prof` le route elle-meme : `ines-design`
propose, `nadia-prof` tranche. Ce que je fixe ici n'est pas le nombre, c'est que le produit
n'accepte pas un plafond a marge nulle sur un axe ou la seule donnee dont on dispose vient d'une
machine unique.

**Consequence attendue, non chiffree par moi** : selon la marge retenue, le residu qui reste hors
du correctif sera un peu plus large que les trois pixels mesures aujourd'hui (576-578 px). Je
l'assume par avance, au meme titre que `DEC-0025` a assume le defaut de pli de s10 - a une echelle
sans commune mesure : un residu de quelques pixels sur 144, contre -321 px sur l'item le plus
charge d'un jeu entier en s10. Ce residu sera documente avec son motif dans la fiche de conformite
au moment ou le correctif sera livre et mesure, comme n'importe quel residu assume de ce produit.

### 2. `BPM-8` n'est pas resolue par cette mesure, et je le dis pour que le denouement de `SYN-16` ne se lise pas comme un denouement de `S19-6`

**Le fait que je verifie avant de trancher, et qui n'etait pas explicite avant cette decision** :
la mesure du 2026-08-24 (`cf78a0d`) ne touche que
`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, aucun fichier de `cm1-homophones`. Elle
mesure une largeur de rangee necessaire pour un defaut de **repliage de propositions**
(`SYN-16`) ; `BPM-8` est un defaut different dans son mecanisme, deja ecrit comme tel dans
`FICHE-bandit-manchot-homophones-cm1.md` : **l'indice est coupe par le pli**, un defaut de hauteur
de contenu, pas de largeur de rangee - `cm1-homophones` sert deux propositions par item, pas
trois, et aucun repliage de rangee n'y est nomme.

L'arithmetique de `theo-archi` (section 6 de son avis) ne traite, du debut a la fin, que le
mecanisme de `cm1-synonymes` ; elle ne derive et ne mentionne a aucun moment une largeur ou une
hauteur necessaire pour `cm1-homophones`. Le titre de section groupe les deux identifiants
(`SYN-16`/`BPM-8`) parce que la story les groupe, pas parce que l'analyse les couvre tous les
deux.

**Consequence** : rien dans cette decision ne dit que `BPM-8` est faisable, infaisable, ou dans
quelle proportion de la bande. La question reste **entiere et non instruite**. Je ne la traite pas
ici a la place de `nadia-prof` et de `theo-archi` : je nomme seulement qu'elle n'est pas traitee,
pour que personne ne lise le denouement de `SYN-16` comme un denouement de `S19-6` dans son
entier.

### 3. `S19-6` est scindee en deux stories et renvoyee a s20

`S19-6` groupait `SYN-16` et `BPM-8` sous une seule story alors qu'ils relevent de deux mecanismes
distincts (largeur pour l'un, hauteur pour l'autre) et sont a deux stades differents d'instruction
(l'un mesure et pret a chiffrer un correctif, l'autre sans aucune mesure de largeur ou de hauteur
necessaire). Je scinde `docs/backlog/backlog.md` :

- **`S19-6a`** - `SYN-16`, correctif de largeur sur la bande 576-719 px avec plafond a marge de
  police, residu assume et documente sous la marge retenue. Prete a etre affectee : la mesure
  requise par `theo-archi` existe.
- **`S19-6b`** - `BPM-8`, indice coupe par le pli entre 576 et 719 px sur `cm1-homophones`. Non
  prete a etre affectee : elle a besoin de la meme etape que `SYN-16` a eue - un avis de
  faisabilite de `theo-archi` sur son propre mecanisme, puis, s'il nomme un chiffre manquant, une
  mesure de `nadia-prof` sur navigateur reel, exactement le processus qui vient de fonctionner
  pour `SYN-16`.

**Ni l'une ni l'autre n'entre au sprint 19.** `S19-6` etait deja hors sequencement au planning de
s19, qui porte cinq stories en cours (`S19-1` a `S19-5`). La mesure de `nadia-prof` rend `S19-6a`
bon marche a instruire, mais elle ne rend pas son implementation gratuite pour autant : une regle
CSS scopee a la bande, une valeur de plafond a marge de police a trancher par `ines-design` et
`nadia-prof`, une verification `SYN-14` de non-regression, une mesure finale sur navigateur reel a
plusieurs largeurs - c'est une story a affecter, pas une correction de forme a glisser dans un
sprint deja charge. Les deux stories sont candidates de s20.

### 4. `SAI-12` entre au perimetre de la conception de s19, pas a l'ouverture de la mecanique

`nadia-prof` pose `SAI-12` (branche `s19-nadia`, `cf78a0d`, `ARBITRAGE-PREALABLE-s19-mecanique-de-
saisie.md`, section 4) : aux deuxieme et troisieme essais, l'enfant doit voir la liste de ce qu'il
a deja ecrit sur la manche, faute de quoi il peut retaper au troisieme essai ce qu'il avait deja
ecrit au premier - la ou des boutons gardent trace de toutes les propositions pressees
gratuitement. Elle demande explicitement qu'elle soit chiffree a part : `theo-archi`, dans
`ADR-0058`, n'a chiffre que `SAI-9` - une chaine, au singulier - et garder trois chaines n'est pas
la meme chose.

**`SAI-12` entre au perimetre de la conception de s19, elle n'attend pas l'ouverture de la
mecanique.** Le motif n'est pas la commodite : `ADR-0058` fixe deja une forme de contrat pour la
saisie (*"aucun contrat ne porte de comportement"*, un port qui recoit une chaine). Si `SAI-12`
demande que le serveur connaisse, garde et restitue trois chaines par manche plutot qu'une, c'est
une question qui touche la forme du contrat que l'`ADR` vient de figer, pas un detail d'affichage
a regler au moment ou la premiere story de saisie s'ouvrira. Attendre l'ouverture de la mecanique
pour la poser reviendrait a decouvrir, sur le premier increment livre, qu'une piece du contrat
doit changer de forme - exactement le mode de decouverte tardive que la section 7 de `DEC-0042`
cherchait deja a eviter pour le budget d'affichage et le niveau de tolerance orthographique. Une
question qui peut faire bouger la forme d'un contrat se pose avant que l'`ADR` qui le fixe ne soit
considere clos, pas apres.

Je ne prejuge pas du chiffrage : si `theo-archi` conclut que `SAI-12` ne coute rien de plus que
`SAI-9` (le serveur garde deja la chaine, en garder trois est une question de forme et non de
mecanisme), `ADR-0058` n'a rien a rouvrir et la conception de s19 reste sur son perimetre actuel.
S'il conclut l'inverse, je veux le savoir avant que le sprint ne se cloture, pas au sprint suivant.

## Ce que je route

- **`sofia-sm`** : affecter `S19-6a` et `S19-6b` au backlog de s20, non a s19 ; transmettre a
  `theo-archi` la demande de chiffrage de `SAI-12` avant la cloture de s19.
- **`theo-archi`** : chiffrer `SAI-12` (section 4) et dire si `ADR-0058` a besoin d'un amendement
  de forme de contrat ; produire l'avis de faisabilite de `BPM-8` (section 3) sur le meme modele
  que celui deja rendu pour `SYN-16`, quand `S19-6b` sera priorisee.
- **`ines-design`** : proposer la valeur de la marge de police au-dessus de 539 px pour le plafond
  de `SYN-16` (section 1), quand `S19-6a` sera affectee.
- **`nadia-prof`** : trancher la valeur du plafond une fois la proposition d'`ines-design` recue ;
  mesurer `BPM-8` sur le meme modele que `SYN-16` si l'avis de faisabilite de `theo-archi` (a
  venir) nomme un chiffre manquant, comme elle vient de le faire pour `SYN-16`.

## Ce que je ne fais pas

- Je ne fixe aucune valeur de plafond en pixels : c'est une question de forme, routee a
  `ines-design` et `nadia-prof`.
- Je ne tranche aucun mecanisme technique : le mecanisme de `SYN-16` reste celui que `theo-archi`
  et `ines-design` instruiront a l'affectation de `S19-6a`.
- Je ne prononce aucun Done pedagogique et ne fais bouger aucun statut de
  `squad/programme/MATRICE-couverture.md`.
- Je ne ferme pas `SYN-16` : elle reste ouverte, non bloquante, jusqu'a ce qu'un correctif soit
  livre et mesure par `nadia-prof` dans les formes habituelles.
- Je ne rouvre pas `DEC-0040` ni la reference unique 744 x 551 px : cette decision ne porte que sur
  le residu d'une bande deja qualifiee d'appareil reel par `DEC-0041`.

## Impact

- `squad/decisions/produit/DEC-0043-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0043` ajoutee.
- `squad/decisions/produit/DEC-0041-perimetre-sprint-18-fermer-trois-dettes-avant-toute-couverture-
  neuve-bpm-1-reclamee-a-nadia-prof.md` : note datee du 2026-08-24 ajoutee sous l'amendement
  `SYN-16` du 2026-08-23, renvoyant a `DEC-0043`.
- `squad/decisions/produit/DEC-0042-perimetre-sprint-19-contenu-seul-conception-de-la-saisie-
  lecture-a-voix-haute-non-couvrable.md` : note datee du 2026-08-24 ajoutee sous la section
  routant `SAI-9`/`SAI-2` a `theo-archi`, renvoyant a `DEC-0043` pour `SAI-12`.
- `docs/backlog/backlog.md` : `S19-6` retiree et remplacee par `S19-6a` et `S19-6b`, toutes deux
  au backlog non affecte, candidates de s20.
- Aucun fichier de conformite, aucune ligne de matrice, aucun Done pedagogique n'est modifie.

## Amendement du 2026-08-24 (meme jour, second passage) - deux chiffres du chantier de s20 sont revises par l'audit des quatre paquets neufs

`nadia-prof` a audite `cm1-antonymes` et `cm1-accords-groupe-nominal` le meme jour (branche
`s19-nadia`, commit `ddb0fc4`), et ces deux paquets touchent directement les deux chantiers que
cette decision route a s20. Je n'y change aucun arbitrage - la piste de largeur reste retenue pour
`SYN-16` (section 1), le residu reste assume avec marge de police exigee - mais je verse les deux
chiffres a jour pour que personne ne les redecouvre plus tard.

**Le plafond de largeur.** `cm1-antonymes` demande une largeur de rangee necessaire de **547 px**
(item le plus charge, trois propositions), contre 539 px pour `cm1-synonymes` : c'est desormais
`cm1-antonymes` qui porte le pire cas de largeur de la bande. La reserve de la proposition
d'`ines-design` a 620 px de plafond, que `nadia-prof` a a trancher, passe en consequence de **41 px
a 33 px** (620 - 40 - 547 = 33). Elle reste largement au-dessus du risque de derive de police nomme
en section 1, et la proposition de 620 px couvre ce paquet-ci. Je ne change rien a l'exigence deja
posee section 1 : la marge doit exister, sa valeur reste a `ines-design` et `nadia-prof`.

**Le chantier vertical.** `cm1-accords-groupe-nominal` sert huit items a quatre propositions, mis
en deux colonnes de deux par la feuille de style (`.propositions:has(.proposition:nth-child(4))`).
Sur ces items, l'indice tombe a 619,19 px pour un pli a 551 px : **il manque 68,19 px**, et non les
43,19 px chiffres sur les synonymes seuls (`avis-faisabilite.md`, section 6.2). **Le chantier
vertical de s20, s'il est instruit, doit budgeter pour les items a quatre propositions et pas
seulement pour ceux a trois** - `nadia-prof` le nomme elle-meme dans
`FICHE-bandit-manchot-accords-groupe-nominal-cm1.md`, reserve `AGN-1`.

### Impact de cet amendement

- `squad/decisions/produit/DEC-0043-...md` (ce fichier) : cette section ajoutee.
- Aucun fichier de conformite, aucune ligne de matrice, aucun perimetre gele n'est modifie.

## Amendement du 2026-08-24 (troisieme passage) - la bande basse concerne desormais trois paquets, pas un seul

`nadia-prof` a reaudite `cm1-accord-sujet-verbe` apres correctif de `marc-dev` (branche `s19-
nadia`, commit `a81324c`) : `VALIDE SOUS RESERVE`, aucune reserve bloquante, ligne "Accorder le
verbe avec son sujet" passee `couvert-valide`
(`squad/programme/MATRICE-couverture.md`, amendement du meme jour, quatrieme passage). Ce paquet
demande une largeur de rangee necessaire de **647 px** a la reference - le pire cas connu du
sprint, devant `cm1-antonymes` (547 px) et `cm1-synonymes` (539 px) - et **ne couvre rien dans la
bande 576-719 px** : a 500 px offerts en bande basse aujourd'hui, il s'y repliera integralement
tant que `S19-6a` n'est pas traitee, exactement comme cette bande n'entrait ni dans son refus ni
dans son second verdict.

**Je ne change rien a l'arbitrage de la section 1** : la piste de largeur reste retenue, le residu
reste assume avec marge de police exigee, `S19-6a` reste candidate de s20. Je verse ce fait pour
que la decision de perimetre sur `S19-6a`, le jour ou elle sera instruite, se prenne en connaissance
du perimetre reel : **la bande basse touche desormais trois paquets nommes de francais CM1
(`cm1-synonymes`, `cm1-antonymes`, `cm1-accord-sujet-verbe`) et non plus un seul**, et le pire cas
de largeur necessaire (647 px) n'est plus celui qui fixait la reserve de police au point precedent
(547 px). **Je ne recalcule pas la reserve d'`ines-design` ici** : cela suppose de savoir d'abord
si `S19-6a` cible tous les paquets de la bande ou seulement ceux nommes a l'ouverture - question
que je poserai au moment ou `S19-6a` sera affectee, pas ici sur la foi d'un seul chiffre de plus.

### Impact de cet amendement

- `squad/decisions/produit/DEC-0043-...md` (ce fichier) : cette section ajoutee.
- Aucun fichier de conformite n'est modifie. `squad/programme/MATRICE-couverture.md` est modifiee
  par ailleurs, dans le meme geste, pour la ligne "Accorder le verbe avec son sujet" - reference
  croisee, pas une consequence de ce fichier.

## Note du 2026-08-25 (`camille-po`) - la scission de `S19-6` en deux mecanismes est etablie fausse, et la bande visee sort de la cible

`theo-archi` a mesure, en chiffrant la piste verticale routee par `DEC-0047` (branche `s20-theo`,
commit `3cf891b`), que la distinction posee ici entre `SYN-16` (largeur) et `BPM-8` (hauteur de
contenu) est **fausse sur la cause** : les deux sont le meme ecran, la meme cause (le bloc de
message qui passe sous le pli), et la meme population - dix jeux, pas deux. Je le verse ici sans
reecrire la section 3 ci-dessus, dont la scission en deux stories reste la trace correcte du
raisonnement tenu a la date ou il a ete ecrit.

**Independamment de ce fait, l'utilisatrice sort la bande 576-719 px de la cible du produit le
2026-08-25** (*"amende `DEC-0040`, la bande passe hors cible"*), reversant l'arbitrage de `DEC-0041`
qui l'y avait fait entrer. `S19-6a` et `S19-6b` sont retirees du backlog affecte
(`docs/backlog/backlog.md`). Detail complet, et le fait plus large que la mesure de `theo-archi`
decouvre a la reference du produit (95 items sur 296 sous le pli, dix jeux dont deux
`couvert-valide`, route a `nadia-prof`) : `DEC-0040`, amendement du 2026-08-25 (troisieme) ;
`DEC-0047`, amendement du meme jour (troisieme passage).

### Impact de cette note

- `squad/decisions/produit/DEC-0043-...md` (ce fichier) : cette section ajoutee.
- Aucun fichier de conformite, aucune ligne de matrice, aucun Done pedagogique n'est modifie ici.
