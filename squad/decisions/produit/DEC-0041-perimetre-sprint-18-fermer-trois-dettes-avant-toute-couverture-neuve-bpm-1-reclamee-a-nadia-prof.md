# DEC-0041 - Perimetre du sprint 18 : fermer trois dettes existantes avant d'ouvrir du contenu neuf, `BPM-1` reclamee a `nadia-prof`, zero ouverture de ligne neuve

- **Type** : perimetre de sprint
- **Date** : 2026-08-22
- **Auteur** : camille-po
- **Sprint** : s18 (slug propose : `s18-fermer-avant-ouvrir` - `sofia-sm` le garde ou le renomme
  en gelant, `squad/README.md` section 2)
- **Competence programme visee** : transverse pour l'essentiel de ce document (fermeture de dette,
  reclassement de priorite) ; une seule action (`S17-4`) cible une ligne existante de
  `squad/programme/MATRICE-couverture.md` (francais, CM1, "Distinguer et utiliser correctement les
  principaux homophones grammaticaux", deja `en cours` depuis `DEC-0024`) sans en ouvrir de
  nouvelle.
- **Arbre verifie** : `round-two` a `494da1aa7841fb8ccb752f7a42573aee8b09ff94`, releve par
  `git rev-parse round-two` sur ce worktree neuf, pas repris d'un message de commit. 604 tests,
  `BUILD SUCCESS` a la derniere mesure connue (`ADR-0054`).
- **Statut** : Proposee - ce document propose un perimetre, il ne le gele pas. L'objectif du
  sprint et l'affectation restent la main de `sofia-sm`, apres l'avis de faisabilite de
  `theo-archi` sur chacune des trois actions ci-dessous.

## Contexte

L'utilisatrice ouvre s18 avec une directive en deux termes : *"le moins de dette et la plus
grande couverture"*. Je verifie l'etat reel du produit poste par poste avant de repondre, plutot
que de reprendre sur parole - trois fois cette annee un chiffre ou une reference recopies sans
reverification se sont reveles faux (`squad/README.md`, amendements de s16 et s17).

**Ce que je verifie.** Le catalogue sert 12 paquets (`adapters/adapter-content/src/main/
resources/catalogue.yaml`, 12 entrees `paquet:`) pour 13 fichiers de contenu presents sur le
disque (`ls adapters/adapter-content/src/main/resources/contenu/`) : `cm1-homophones.yaml` est
le seul present et non catalogue. `squad/programme/MATRICE-couverture.md` (synthese de cloture de
s17) porte 9 lignes `couvert-valide`, 6 `en cours`, 31 `non couvert`, sur 46 - soit 19,6 %, et
quatre sprints consecutifs (s14 a s17) sans mouvement reel de couverture programme
(`DEC-0037`, section 3 et son amendement de cloture de s17).

**Le fait qui domine cette instruction.** `DEC-0040` (2026-08-22) change la reference d'ecran de
360 x 640 a 744 x 551 px. Plusieurs refus pedagogiques ont ete prononces contre l'ancienne
reference et n'ont pas ete remesures contre la nouvelle :

- `cm1-homophones` ("Le bon petit mot") est hors catalogue depuis s10 sur `BPM-1`, une mesure de
  rendu (`DEC-0024`). Le contenu est deja juge bon : `BPM-2`, `BPM-3`, `BPM-4` sont **tenues**
  (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`), et ma reserve sur les trois indices
  qui nommaient encore la reponse est **fermee** (0 indice sur 24 le 2026-08-21). **Aucun contenu
  neuf a ecrire.**
- La famille `M4` (message, indice, revelation au-dessus du pli) porte un defaut assume par ecrit
  depuis s10 sur six jeux sur sept, jusqu'a -251 px a 360 x 640 (`DEC-0025`). Ce cout n'a jamais
  ete mesure contre 744 x 551.
- `S17-3` (`cm1-synonymes`) vient d'etre **remesuree** formellement a 744 x 551
  (`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, verdict du 2026-08-22) : `SYN-5` est
  **tenue** (seize items sur seize au-dessus du pli, 160 tirages), mais le Done pedagogique reste
  **REFUSE** sur un motif neuf, `SYN-11`, sans rapport avec la reference d'ecran elle-meme.
- `COMP-4` (comprehension, `S17-2`) et le reste ne sont pas mesures a la nouvelle reference, mais
  `S17-2` est sortie du perimetre pour un motif de mise en page (90 a 120 px manquants sur l'ecran
  de manche partage) que `DEC-0040` ne touche pas : je ne la traite pas ici.

**La nuance qui empeche d'en faire une regle, verifiee et non supposee.** Tous les refus ne sont
pas de rendu. `DEC-0004` a retire un Done pedagogique pour contournabilite ("il n'est ni faux ni
dangereux, il est trop facile a contourner") et les tables de multiplication ont ete refusees
parce que la bonne reponse n'est jamais le plus petit des trois nombres sur 99 items - une
reference d'ecran plus large ne change rien a l'un ni a l'autre. Je ne compte donc comme
"recuperable par remesure" que ce qui a ete refuse ou bloque sur un motif de rendu : `BPM-1` et
`M4`. `S17-3` a deja ete remesuree et reste refusee sur un motif distinct (`SYN-11`) : elle
n'entre pas dans cette categorie, meme si elle a beneficie de la meme remesure.

## Decision

### 1. La directive tranchee, et ce qui prime quand elle se contredit

**Les deux termes de la directive ne se contredisent pas partout, et je le dis avant de trancher
ou ils se contredisent.** Une seule action sert les deux a la fois sans arbitrage : remesurer
`BPM-1` et rendre le Done pedagogique de `S17-4` ne coute aucun contenu neuf (le contenu est deja
juge bon depuis s10) et, s'il aboutit, fait progresser une ligne de la matrice vers
`couvert-valide` - c'est le geste que je fais primer sur tout le reste de ce perimetre.

**La ou elles se contredisent reellement : fermer une dette existante et bloquante (`SYN-11`,
`S17-C`) contre ouvrir du contenu neuf pour tenter de faire progresser une autre ligne
(`S17-5`, antonymes, deja ecrite au backlog).** Je fais **primer la dette ce sprint**, pour trois
raisons ecrites, pas par defaut :

1. `S17-3` est un increment deja ecrit, deja mesure, et refuse pour un motif qui touche la
   securite pedagogique de l'enfant qui joue - `SYN-11` dit qu'un enfant qui se trompe trois fois
   sur cinq items sur seize ne voit jamais la phrase qui lui donne la reponse. Laisser cette dette
   ouverte pour ecrire un contenu neuf sur une compétence voisine reviendrait à traiter un défaut
   de sécurité comme moins urgent qu'un gain de couverture, ce que je refuse d'écrire comme un
   arbitrage silencieux.
2. `S17-C` (tabindex absent a la revelation) a huit sprints d'anciennete (M13, s10) et vient de se
   reproduire sur `cm1-synonymes` sans etre reprononcee. Le motif qui l'avait differee en s17
   ("cout non cerne sur un sprint deja charge de dix obstacles") ne tient plus a l'identique sur
   un sprint que je propose plus etroit.
3. Ouvrir `S17-5` (antonymes, 20 items neufs, arbitrage prealable de `nadia-prof` requis) dans le
   meme sprint que ces deux fermetures reproduirait exactement le risque que `DEC-0030` et
   `DEC-0037` ont deja nomme : *"un increment techniquement simple peut consommer autant de temps
   sur ses defauts que sur son contenu."* Je prefere fermer trois dettes proprement que d'ouvrir
   une quatrieme piste et risquer de ne fermer aucune des quatre.

**Ce que je n'en conclus pas.** Ce n'est pas un cinquieme sprint de stabilisation au sens de
`DEC-0037` : contrairement a s14-s16, ce perimetre vise explicitement une ligne de matrice
(`S17-4`), avec un chemin court et deja instruit. Et ce n'est pas un abandon de `S17-5` : elle
reste au backlog, candidate explicite pour le sprint qui suivra s18 une fois que le modele
"fermer une dette qui bloque une ligne deja `en cours`" aura ete prouve sur `S17-4`.

### 2. `S17-4` reactivee : `BPM-1` est reclamee a `nadia-prof`, le transfert de `DEC-0039` ne tient plus

**Je reponds a la question que je me suis moi-meme posee** (`DEC-0040`, section 3 : *"la question
de savoir ce que `BPM-1` doit mesurer desormais est rouverte, sans que je la tranche"*) en
verifiant plutot qu'en supposant.

**Le fait qui change.** `DEC-0039` (2026-08-19, section 3) a transfere la production de `BPM-1` a
l'utilisatrice pour un motif de **capacite**, ecrit par `nadia-prof` elle-meme : *"aucun outil du
depot ne pilote un navigateur reel [...] personne ne peut simuler un survol"* (`ADR-0046`), et
"les quatre defauts trouves [...] l'ont tous ete par l'utilisatrice en jouant, aucun par
l'outillage de la squad." Ce motif ne tient plus. Depuis, `nadia-prof` a produit trois mesures de
rendu sur navigateur reel, dans les formes ou hors des formes : la hauteur utile d'un poste
(`squad/conformite/mesure-hauteur-utile-nouvelle-cible.md`), et la mesure formelle de `SYN-5` a
744 x 551 sur **seize parties, cent soixante tirages**, plus neuf parties et **trois cent soixante
lectures** pour `SYN-11`. Elle mesure desormais couramment ce que `BPM-1` demande : un rendu servi,
au pire cas, sans defiler.

**Decision : le transfert de `BPM-1` a l'utilisatrice (`DEC-0039`, section 3) est leve.** `BPM-1`
redevient une mesure de la squad, produite par `nadia-prof`, exactement sur le modele qu'elle
vient d'appliquer a `SYN-5`. Ce n'est pas un rejugement du contenu de `cm1-homophones` (deja
juge bon, `BPM-2` a `BPM-4` tenues) ni une reouverture de `DEC-0024`, dont la condition de retour
reste entiere : `BPM-1` **et** un Done pedagogique complet, dans le meme geste, jamais en deux
temps.

**Ce que cela demande, cerne et petit.** `DEC-0024` (point 3) exige que la mesure de `BPM-1` et le
Done pedagogique arrivent ensemble ; `nadia-prof` ne peut mesurer un rendu servi que si l'entree
existe au catalogue. Un dev ajoute donc l'entree de `cm1-homophones` a
`adapters/adapter-content/src/main/resources/catalogue.yaml` (une entree, sur le patron des douze
deja presentes, zero ligne de Java neuf, zero mecanique neuve), dans le meme commit ou le meme lot
que la mesure de `nadia-prof`, jamais avant elle seule sur `main`/`round-two` sans mesure derriere
(le risque exact que `DEC-0024` nomme : *"restituer au catalogue maintenant reexposerait un enfant
[...] sans que personne ne l'ait verifie"*).

**Ce que je route.** `theo-archi` : confirmer que l'ajout de l'entree de catalogue ne demande
aucun mecanisme neuf (verification attendue, pas supposee) et que `outils/mesurer-rendu.sh` ou la
methode de `nadia-prof` pour `SYN-5` s'applique telle quelle aux 24 items de `cm1-homophones`.
`nadia-prof` : produire `BPM-1` sur l'entree posee par le dev, aux trois etats d'echec comme pour
`SYN-11` (message, indice, revelation), pas seulement a l'etat de repos - la lecon de `SYN-11`
elle-meme est qu'un pli mesure au repos peut cacher un defaut qui n'apparait qu'a l'echec. Si
`BPM-1` tient et si son Done complet est `VALIDE` ou `VALIDE SOUS RESERVE` sans reserve
bloquante, la ligne francais CM1 "homophones" (`squad/programme/MATRICE-couverture.md`) passe a
`couvert-valide` dans le meme geste, comme `squad/README.md` section 9 l'exige.

### 3. `SYN-11` a `SYN-13` entrent au perimetre de s18 : `S17-3` reste `S17-3`, pas une story neuve

`nadia-prof` a refuse `S17-3` une troisieme fois (`squad/conformite/FICHE-bandit-manchot-
synonymes-cm1.md`, verdict du 2026-08-22) : `SYN-5` **tenue** a 744 x 551, mais sur cinq items sur
seize, le bloc de retour juste/faux (bandeau et texte du verdict) est **entierement sous le pli**
dans les trois etats d'echec - un enfant qui se trompe une fois n'a ni rature ni mot, et au
troisieme echec ne voit jamais la phrase qui lui donne la reponse. D'ou `SYN-11` (bloquante),
`SYN-12` et `SYN-13` (non bloquantes). `S17-C` s'y reproduit egalement : une sortie au clavier
existe a la revelation, rien a l'ecran ne l'indique.

**Je ne renumerote pas `S17-3` en une story de s18** : c'est le meme besoin d'enfant, la meme
competence, le meme paquet, et `squad/README.md` section 2 attend que le tri d'un dossier reste
lisible - renommer casserait les references deja posees dans `DEC-0039`, `DEC-0040` et la fiche
de conformite. `S17-3` entre au perimetre que je propose pour s18, avec `SYN-11` a `SYN-13`
ajoutees a ses criteres au meme titre que `SYN-5` a `SYN-10` l'ont ete a s17 (voir l'impact
ci-dessous, note ajoutee a `docs/backlog/backlog.md`).

**Ce que je ne tranche pas.** Le mecanisme qui remettrait le bloc de retour au-dessus du pli sur
les cinq items concernes (deplacer le bloc, reduire ce qui le precede, autre) est une question de
mise en page et de mecanisme, routee a `ines-design` et `theo-archi` - je n'ecris ici que le
critere observable que le mecanisme doit satisfaire.

**Le mandat borne du 2026-08-21 sur le repli des rangees reste inchange par ce document.**
`ADR-0054` a mesure que ce mandat garde son declencheur a 744 px (repli sur cinq items sur seize,
independant du tirage) : il continue d'etre du ressort d'`ines-design` et `theo-archi`, je ne le
reroute pas.

### 4. `S17-C` reclasse : sur ordinateur, le clavier n'est plus un chemin secondaire

`DEC-0039` (amendement du 2026-08-19) avait differe `S17-C` au motif que le defaut, anterieur de
sept sprints, touchait une zone de dette (`static/js/recreo.js`) au cout non cerne sur un sprint
deja charge. Je reexamine ce classement plutot que de le laisser filer une seconde fois, pour deux
raisons factuelles nouvelles :

1. **`DEC-0040` retire le telephone du perimetre de support.** Le telephone etait le support ou
   l'interaction tactile dominait le plus nettement ; sur ordinateur, le clavier est un chemin de
   jeu ordinaire, pas un cas marginal - et c'est justement pour un enfant au clavier que `S17-1`
   (l'anneau de focus) a ete construite ce meme trimestre. Un defaut qui coupe ce chemin sur le
   seul support qui reste dans le perimetre pese plus lourd qu'avant `DEC-0040`.
2. **`nadia-prof` ajoute un fait qui nuance sa propre severite, et je le lis pour ce qu'il est,
   pas pour plus** : une sortie au clavier existe a la revelation - l'enfant n'est pas enferme -
   mais il doit presser volontairement une mauvaise reponse pour que la bonne lui soit rendue.
   Ce n'est donc pas un verrou total ; c'est une friction imposee a un enfant qui a deja la bonne
   reponse sous les yeux et ne peut pas l'atteindre au clavier sans un detour par l'echec. Je ne
   requalifie pas `S17-C` en non-bloquante - `nadia-prof` ne l'a pas fait, et ce jugement est le
   sien, pas le mien - mais ce fait retire l'argument qui aurait pu justifier de la laisser
   indefiniment en arriere-plan : un enfant n'est jamais completement bloque, il est genee de la
   maniere la moins acceptable pour un produit qui vient de construire un anneau de focus pour lui.

**Decision : `S17-C` entre au perimetre que je propose pour s18.** Elle garde son identifiant et
ses criteres d'acceptation deja ecrits dans `docs/backlog/backlog.md` ("Story de correction - Le
bouton nomme a la revelation est absent de l'ordre de tabulation"), inchanges. Le mecanisme
(correctif de `static/js/recreo.js`, garde-fou eventuel sur `RecreoJsDeplaceLeFocusTest`) reste
entierement la main de `theo-archi`, deja saisi par `DEC-0039`.

### 5. Ce qui reste hors du perimetre que je propose, et pourquoi

- **`S17-5` (antonymes, contenu neuf)** : reste au backlog, non affectee. Motif au point 1 -
  je ne mélange pas une ouverture de contenu neuf avec la fermeture de trois dettes dans le meme
  sprint. Elle redevient candidate prioritaire pour le sprint suivant.
- **La famille `M4` (remesure a 744 x 551)** : je la nomme, je ne l'ouvre pas ce sprint. Elle n'est
  bloquante pour aucune ligne de matrice aujourd'hui - `nadia-prof` l'a levee comme condition
  bloquante des s10 (`DEC-0025`), le cout est deja assume par ecrit. La remesurer informerait sans
  debloquer aucun statut : ce n'est ni de la dette qui bloque un enfant aujourd'hui, ni de la
  couverture qui progresse. Je la garde nommee pour la prochaine fois qu'un sprint de dette pure
  s'ouvre, plutot que de la faire concurrencer les trois actions ci-dessus sur la meme capacite de
  mesure de `nadia-prof`, deja sollicitee par `BPM-1` et par la verification du correctif de
  `SYN-11`.
- **`COMP-4` et `S17-2`** : la question ouverte par `DEC-0040` (le budget vertical manquant
  tiendrait-il a 744 x 551) reste ouverte, non instruite ici. Le blocage de `S17-2` n'est pas de
  reference mais de mise en page (90 a 120 px manquants sur l'ecran de manche partage, `DEC-0039`),
  et rien dans ce document ne le rouvre.
- **Le trou de garde-fou du seuil Bootstrap (768 px) et la question de la police** : nommes par
  `nadia-prof` et `theo-archi`, routes a eux, non affectes a s18 - ce sont des questions de
  mecanisme et de perimetre de mesure, pas des besoins d'enfant que je peux formuler en story.

### 6. Ce que s18 peut rendre a la couverture - chiffre et distingue

**Rien n'est acquis avant que `nadia-prof` ne mesure et ne juge** - je l'ecris pour ne pas
habiller un chiffre optimiste en promesse.

- **Par remesure seule, zero contenu neuf** : au plus **une ligne** (francais CM1, homophones,
  `S17-4`) peut passer `en cours` -> `couvert-valide`, si `BPM-1` tient et si le Done complet est
  prononce sans reserve bloquante. Chiffre honnete : 9/46 (19,6 %) -> 10/46 (21,7 %) au mieux, soit
  un gain de 2,1 points. Cout : une entree de catalogue (triviale) plus une mesure de rendu de
  `nadia-prof`, deja routee.
- **Par fermeture de dette, zero mouvement de matrice** : `SYN-11`/`SYN-12`/`SYN-13` et `S17-C`
  ne font progresser aucune ligne - `S17-3` reste `en cours` quel que soit son verdict (les
  antonymes manquent toujours, `nadia-prof`, 2026-08-19), et `S17-C` est transverse. Leur valeur
  est de rendre `S17-3` reellement jouable sans defaut de securite et de fermer une dette de huit
  sprints, pas d'augmenter un pourcentage.
- **Par contenu neuf, rien** : aucune ligne `non couvert` n'ouvre a s18, deliberement (point 1).

**Si aucune des trois actions n'aboutit**, s18 rejoint la famille des quatre sprints deja nommes
par `DEC-0037` sans mouvement reel de couverture - je le dis a l'avance plutot qu'a la cloture,
puisque la seule action qui porte un espoir de mouvement (`S17-4`) depend entierement d'une mesure
qui n'existe pas encore.

## Ce que je route

- **`theo-archi`** : avis de faisabilite sur les trois actions ; confirmation que l'entree de
  catalogue de `cm1-homophones` ne demande aucun mecanisme neuf ; mecanisme et garde-fou de
  `S17-C` (deja saisi par `DEC-0039`) ; mecanisme du bloc de retour juste/faux pour `SYN-11`,
  conjointement avec `ines-design`.
- **`ines-design`** : proposition de mise en page pour `SYN-11` (bloc de retour au-dessus du pli
  sur les cinq items concernes), sous le veto pedagogique de `nadia-prof`.
- **`nadia-prof`** : produire `BPM-1` sur l'entree posee par le dev, aux trois etats d'echec ;
  verifier le correctif de `SYN-11` a `SYN-13` et de `S17-C` sur `cm1-synonymes` une fois livres ;
  aucune demande de rejugement du contenu deja valide (`BPM-2` a `BPM-4`, `SYN-1` a `SYN-10`).
- **`sofia-sm`** : gel de l'objectif et affectation nominative ; decision sur le slug du repertoire
  de sprint ; verification que les trois actions ne se marchent pas dessus sur la meme capacite de
  mesure de `nadia-prof`.
- **`yanis-qa`** : traduire les criteres observables de `SYN-11`/`S17-C` en tests, sur le materiau
  reel servi, comme `ADR-0054` vient de l'exiger pour la largeur de rangee.

## Ce que je ne fais pas

- Je ne juge aucun rendu et je ne leve aucun refus pedagogique : seule une mesure de `nadia-prof`
  le peut (`DEC-0040`).
- Je ne tranche aucun mecanisme (mise en page du bloc de retour, correctif JavaScript, ajout de
  l'entree de catalogue) : routes ci-dessus.
- Je ne fais passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`
  moi-meme : ce geste reste conditionne au Done pedagogique de `nadia-prof` sur `S17-4`.
- Je ne gele pas le sprint : `sofia-sm`.

## Impact

- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0041` ajoutee.
- `squad/decisions/produit/DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md` :
  note datee du 2026-08-22 ajoutee, le transfert de `BPM-1` a l'utilisatrice (repris de `DEC-0039`)
  est leve.
- `squad/decisions/produit/DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md` :
  note datee du 2026-08-22 ajoutee sous la section 3, le transfert de `BPM-1` est leve par
  `DEC-0041`.
- `docs/backlog/backlog.md` : note ajoutee sous `S17-3` (SYN-11 a SYN-13, reproduction de S17-C) ;
  note ajoutee sous `S17-4` (transfert de BPM-1 leve, mecanisme du dev precise) ; en-tete de la
  story de correction S17-C mise a jour pour dire qu'elle entre au perimetre propose de s18.
- `squad/programme/MATRICE-couverture.md` : section "Synthese a l'ouverture de s18 (2026-08-22)"
  ajoutee, tableau inchange (31/6/9/46), aucun statut ne bouge par ce document.

## Amendement du 2026-08-22 (camille-po, s18, apres gel) - corrections issues de l'avis de faisabilite de `theo-archi`, arbre reverifie

**Arbre reverifie a l'ouverture de cet amendement** : `round-two` a `eed653ba5a7c6862c798dd8fe896f8cf6045f546`,
releve par `git rev-parse round-two` sur ce worktree neuf. C'est **un commit de plus** que le
`8f197fc` communique a l'ouverture de ce lot : `sofia-sm` a corrige, entre-temps et par note datee
dans son propre `planning.md`, deux chiffres de sa porte technique (612 tests confirme par la
methode XML, la convergence des deux methodes annoncee etait fausse ; 29 regles ArchUnit, pas 31,
erreur de methode de comptage) et a signale que mon lot etait en cours. Aucune des deux corrections
ne porte sur mon perimetre ni sur les trois actions gelees ; je le releve parce que le SHA qui m'a
ete communique n'etait deja plus la tete au moment ou je l'ai verifie moi-meme, exactement le risque
que `CLAUDE.md` nomme sous le nom d'ecart muet.

`theo-archi` a rendu son avis de faisabilite (`squad/sprints/s18-fermer-avant-ouvrir/
avis-faisabilite.md`) avant tout code de story, et `sofia-sm` a gele le sprint sur cette base
(`planning.md`, horodatage 2026-08-22 21:35) en me renvoyant trois corrections qui sont miennes.
Je les traite ici, sans rouvrir le perimetre gele des trois actions.

### 1. Les deux criteres perimes de `S17-4` sont corriges

Le critere de `S17-4` qui nommait *"360 x 640 avec `outils/mesurer-rendu.sh`"* portait deux
defauts factuels distincts, etablis par `theo-archi` (avis, sections 1.2 et 1.4) : la reference
n'existe plus (`DEC-0040` : 744 x 551) et l'outil ne peut structurellement pas atteindre un etat
d'echec, qui s'obtient en postant une mauvaise reponse dans une session de partie, jamais par une
capture d'ecran d'une URL. Corrige par note datee dans `docs/backlog/backlog.md`, sans reecrire le
critere d'origine ni le point 3 de `DEC-0024` dont il est repris - le fond du critere ne change
pas, seules sa reference et sa methode etaient perimees. **Ecart verifie et non trouve** : j'ai lu
le tableau critere par critere de la section 1.4 de l'avis ligne a ligne contre les sept criteres
de `S17-4` un a un ; aucun autre critere de cette story n'y est marque a corriger.

### 2. `SYN-13` requalifiee en observation ; `SYN-12` non touchee ; `SYN-11` non touchee

Ma propre note du 2026-08-22 sous `S17-3` disait que `SYN-12` et `SYN-13` "s'ajoutent aux criteres
de cette story au meme titre que `SYN-5` a `SYN-10`" - c'est inexact pour les deux, et `theo-archi`
l'etablit (avis, section 2.4) : `SYN-13` est **conditionnee au mecanisme retenu** pour `SYN-11`
("elle disparaitra d'elle-meme si `SYN-11` est fermee par la largeur", `nadia-prof`), ce qu'un
critere d'acceptation ne peut pas etre avant que ce mecanisme soit choisi ; `SYN-12` reste **une
question entierement jugee par `nadia-prof`**, jamais un critere de lot testable. Corrige par note
datee dans `docs/backlog/backlog.md`, sous `S17-3`. **`SYN-11` n'est pas touchee** : `theo-archi`
la juge bonne telle qu'ecrite et non encodable, et je ne la modifie pas.

### 3. L'entree de catalogue de `cm1-homophones` : ma qualification de "triviale" est confirmee, ma mise en garde routee a `theo-archi` etait fausse

J'avais route a `theo-archi`, en section 2 ci-dessus, la verification que l'ajout de l'entree
"ne demande aucun mecanisme neuf" - phrase prudente parce que je craignais qu'une entree neuve
expose le paquet a des regles de contenu qu'il n'affronte pas encore. `theo-archi` (avis, section
1.1) etablit que **cette crainte etait infondee** : la population des regles de contenu de ce depot
est le balayage du repertoire `contenu/`, pas le catalogue - `cm1-homophones.yaml` affronte deja
toutes ces regles aujourd'hui, sans etre catalogue. L'entree n'ajoute reellement d'exposition qu'aux
quatre clauses dont la population **est** le catalogue, dont trois sont satisfaites par
construction (24 items pour 10 manches, aucun doublon) et une seule demande une edition :
l'inventaire de `TexteAccentueExposeParLePortEntrantTest`. **Une edition, pas cinq.** Je retiens ce
chiffre pour toute mesure future du meme geste plutot que de reproduire ma propre mise en garde.

### 4. Le cout de `BPM-1` est reevalue a la hausse, sans changer le perimetre

Le cout que j'avais chiffre en section 6 ("une entree de catalogue (triviale) plus une mesure de
rendu de `nadia-prof`, deja routee") sous-estimait la seconde moitie. `theo-archi` (avis, section
1.3) mesure que cette campagne porte sur **24 items** aux trois etats d'echec chacun, au pire
tirage - **un ordre superieur** a la derniere campagne comparable de `nadia-prof` (`SYN-11` : 9
parties, 360 lectures, sur 16 items). Deux faits la bornent vers le bas (deux propositions par item
sur les 24, total de caracteres des deux etiquettes jamais superieur a sept) et un fait l'allonge
(la revelation s'atteint en pressant trois fois la meme mauvaise reponse, donc les trois etats
d'echec sont chacun reellement atteignables). Corrige par note datee dans `docs/backlog/
backlog.md`, sous `S17-4`. **Ce que cela ne change pas** : le perimetre que je propose reste les
trois memes actions ; c'est `sofia-sm` qui sequence la capacite de mesure de `nadia-prof` entre
`BPM-1`, `SYN-11` et `S17-C` sur le cout desormais juste, pas moi.

### 5. La migration `ADR-0049` : le motif que `theo-archi` ajoute a mon exclusion

`theo-archi` (avis, section 5) confirme que laisser cette migration hors du perimetre de s18 est le
bon arbitrage, pour un motif que je n'avais pas ecrit : **s18 est le seul sprint ou la reporter est
gratuite**. Le cout de cette dette croit d'un fichier par paquet de contenu neuf ouvert, et `DEC-0041`
ferme deliberement tout contenu neuf ce sprint - donc, exceptionnellement, attendre ne coute rien
cette fois, ce qui n'etait pas vrai en s17 (ou `cm1-synonymes` s'est ajoute au perimetre pendant le
sprint) et ne le sera plus au sprint suivant si `S17-5` (antonymes) ouvre. Il ajoute qu'elle est **le
meilleur candidat a un ajout tardif** si un developpeur se libere pendant que `nadia-prof` mesure
`BPM-1`, puisqu'elle ne demande aucune mesure de sa part - ma reserve au point 5 ci-dessus ("j'ecarte
S17-5 pour ne pas melanger contenu neuf et fermeture de dette") ne s'applique pas a elle, une
migration de graphie n'etant pas un contenu pedagogique neuf. J'inscris ce motif ici plutot que de
le laisser dans le seul avis de `theo-archi` : il complete, sans le contredire, mon exclusion de la
section 5.

### 6. Un motif de report de `S17-C` est corrige par note dans le backlog, sans consequence sur mon classement

`theo-archi` (avis, section 4.1) etablit que le motif de report de `S17-C` cite dans `docs/
backlog/backlog.md` et dans `DEC-0039` - *"`RecreoJsDeplaceLeFocusTest` ne modelise aujourd'hui
aucun attribut `tabindex`"* - est faux sur un point verifiable en une lecture : le faux DOM
assertionne deja `tabindex`. Ce qui manque reellement est la focalisabilite native d'un element,
un fait distinct qui change le mecanisme du correctif mais pas la conclusion du report (cout non
cerne en s17). Note datee ajoutee dans `docs/backlog/backlog.md`, sans reecrire le paragraphe
d'origine. **Ceci ne rouvre rien de ma section 4** : mon reclassement de `S17-C` au perimetre
propose de s18 repose sur le retrait du telephone (`DEC-0040`) et sur le fait ajoute par
`nadia-prof`, pas sur la capacite du faux DOM - inchange.

### Ce que je route (ajout)

- **`sofia-sm`** : les trois corrections ci-dessus sont faites, sans rouvrir le gel ; le cout reel
  de `BPM-1` (point 4) est a verser au sequencement si elle ne l'a pas deja fait de son cote.
- **Personne d'autre** : aucun mecanisme, aucun rendu, aucun refus ne sont touches par cet
  amendement.

## Impact de cet amendement

- `docs/backlog/backlog.md` : quatre notes datees ajoutees (correction du critere `BPM-1` de
  `S17-4` ; requalification de `SYN-12`/`SYN-13` sous `S17-3` ; reevaluation du cout de `BPM-1` ;
  correction du motif de report de `S17-C`). Aucun texte d'origine reecrit.
- `squad/decisions/produit/DEC-0041-...md` (ce fichier) : cette section ajoutee.
- Aucun autre fichier n'est modifie par cet amendement.
