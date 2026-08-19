# Relecture pedagogique et QA de contenu - Sprint 17

- **Auteure** : relecteur-pedagogique
- **Date** : 2026-08-19
- **Provisionnement** : worktree dedie `s17-relecteur`, cree hors de l'arbre du depot depuis
  `round-two` a `ed7eb52`. Lignee verifiee : `outils/verifier-lignee.sh` rend "le worktree sur la
  branche 's17-relecteur' descend de la lignee de round-two (ancetre commun trouve dans son
  historique)."
- **SHA de relecture : `ed7eb52`**. `round-two` continuait de recevoir des fusions au moment de la
  lecture ; ce rapport ne couvre que ce qui etait fusionne a ce commit. `FICHE-bandit-manchot-
  homophones-cm1.md` et `avis-garde-fous-routes-par-yanis.md` (ce dernier absent du depot) n'ont
  connu aucune fusion s17 a cette date - la premiere reste un fichier de reference cite, pas modifie,
  pour ce sprint.
- **Perimetre lu** : `planning.md` (avec sa note datee du 24/24), `avis-faisabilite.md`,
  `avis-mecanisme-texte-support-comp5.md`, `rapport-qualite.md`, les quatre propositions
  d'`ines-design` (`proposition-ines-focus-clavier.md`, `proposition-ines-marque-de-focus-puce-
  active.md`, `proposition-ines-mise-en-page-manche-comprehension.md`, `proposition-ines-texte-
  support-comp5.md`) (repertoire `s17-francais-cm1-et-dette`) ; `ADR-0049`, `ADR-0050`, l'amendement
  du 2026-08-19 d'`ADR-0047`, `ADR-index.md` (lecture croisee) ; `DEC-0039` avec ses quatre
  amendements, `DEC-0024` (section de correction et amendement du 2026-08-19), `DEC-index.md` ;
  `FICHE-barre-entree-niveau.md` (section "Sonde post-livraison... S17-1" et la table de
  correspondance amendee), `FICHE-bandit-manchot-comprehension-cm1.md`,
  `FICHE-bandit-manchot-synonymes-cm1.md` ; les deux `ARBITRAGE-PREALABLE-s17-*.md` (lus pour
  verifier les citations qui en sont faites, hors perimetre nommement affecte) ; `docs/backlog/
  backlog.md`, les sections ajoutees ce sprint (`S17-C`, `S17-2`, sa note de sortie de perimetre,
  `S17-3`, `S17-5`, `S17-4`, sa note de correction du 24/24, la story de migration `ADR-0049`) ;
  `cm1-synonymes.yaml` (20 items) et `cm1-homophones.yaml` (24 items) sous l'angle exclusif du
  francais correct et integralement accentue.

## Corrections appliquees (5, dans 4 fichiers)

Toutes de nature typographique ou de formatage Markdown, aucune ne touche au sens d'une decision,
d'un verdict ou d'un chiffre.

1. `squad/sprints/s17-francais-cm1-et-dette/planning.md`, section "Ce que ce sprint peut et ne peut
   pas rendre a la couverture" : parenthese ouvrante jamais fermee ("**Le taux de couverture actuel
   est 19,6 % (9 lignes `couvert-valide` sur 46, 0 lignes rouvertes ce sprint**") - `)` ajoutee avant
   le gras de fermeture. Verifie que 9/46 = 19,6 % avant de toucher la phrase : le chiffre n'est pas
   en cause, seule la ponctuation l'etait.
2. `squad/decisions/produit/DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md` :
   ligne vide manquante entre la fin de l'amendement "`S17-2` sort du perimetre" et le titre de
   l'amendement suivant ("`S17-C`... n'entre pas au perimetre") - les deux etaient concatenes sur
   deux lignes consecutives sans separation, artefact visible de la fusion de deux branches
   `camille-po` distinctes evoquee par le planning de sprint. Une ligne vide inseree.
3. Meme fichier : le quatrieme amendement ("le mandat sur le cabinet et le score...") portait un
   second titre "## Ce que je route" identique au tout premier du document (section d'origine),
   alors que les deux amendements precedents avaient deja etabli le motif "(ajout du 2026-08-19)" /
   "(ajout du 2026-08-19, second)" pour distinguer leurs propres sections de routage. Titre corrige
   en "## Ce que je route (ajout du 2026-08-19, troisieme)", par coherence avec le motif deja en
   usage dans le meme fichier - aucun contenu de la section deplace ni reformule.
4. `squad/decisions/produit/DEC-index.md`, entree `DEC-0039` : "antérieur" (seule occurrence
   accentuee de tout le document, alors que le mot revient par ailleurs sous la forme "anterieur"
   dans l'ensemble du corpus `squad/`) corrige en "anterieur", par coherence avec la convention
   d'ecriture uniformement deaccentuee tenue partout ailleurs dans ce fichier et dans ce depot.
5. `squad/sprints/s17-francais-cm1-et-dette/rapport-qualite.md` : deux titres de section coupes sur
   deux lignes dans le texte source ("## 1. `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
   sur `cm1-synonymes` : constat de" suivi d'une ligne separee "`lea-dev` confirme, garde-fou vide" ;
   meme defaut sur le titre "## 2. ..."). En Markdown, un titre est une construction a une seule
   ligne : la seconde moitie ne faisait donc pas partie du titre et se serait rendue comme un
   paragraphe ordinaire. Les deux titres ont ete rejoints sur une seule ligne, sans changer un mot.

## Signale sans corriger

1. **`planning.md` n'a pas ete mis a jour apres que `DEC-0039` a fait sortir `S17-2` du perimetre de
   s17.** Le planning, gele a `26c7d8b` et dernierement retouche a `e36d0c5` (la note datee du
   24/24), affirme toujours en section "Ce que ce sprint peut et ne peut pas rendre a la couverture"
   que "au mieux, une seule ligne peut passer `en cours` a `couvert-valide` a la cloture de s17,
   celle de la **comprehension** (`S17-2`)" et que la sortie de `S17-2` du tableau des stories reste
   listee comme affectee a `marc-dev`. Or l'amendement de `DEC-0039` du meme jour ("`S17-2` sort du
   perimetre de s17") - fusionne **apres** la derniere retouche du planning (`e36d0c5` precede
   `c81b971`/`e7214fe` dans l'historique de `round-two`) - tranche explicitement l'inverse :
   "`S17-2` n'est pas de leur ressort de fermer... `S17-2` sort du perimetre de s17 et retourne au
   backlog, non affectee" et conclut "aucune ligne de `squad/programme/MATRICE-couverture.md`
   n'atteindra `couvert-valide` a la cloture de s17". Le planning a recu une note datee pour un
   chiffre (22/24 -> 24/24) apres cette meme fusion, mais aucune note equivalente ne signale que sa
   propre section sur la couverture est perimee. Un lecteur qui ouvre uniquement `planning.md` croit
   encore `S17-2` en jeu pour `couvert-valide` ; un lecteur qui ouvre `DEC-0039` sait le contraire.
   Ce n'est pas une contradiction que je peux corriger moi-meme par une reecriture silencieuse (la
   convention du depot depuis s16, `R-BARRE-1`, est la correction par note datee) et ce n'est pas non
   plus un simple chiffre : c'est un etat de decision. Je le route a `sofia-sm`, qui gele le planning
   et clot le sprint.
2. **`FICHE-bandit-manchot-additions-cp.md` (hors perimetre affecte ce sprint, non touchee depuis
   s16) porte encore "22 sur 24" a trois endroits** (section "0 bis", section M2, section "Reserves
   nouvelles et datees du 2026-08-12"), sans aucun renvoi vers la correction du 2026-08-19. Le
   planning, `DEC-0024` et `docs/backlog/backlog.md` ont chacun recu une note datee expliquant que le
   compte juste est 24 sur 24 ; cette fiche, qui porte les memes chiffres et que `DEC-0024` cite
   nommement comme source du signalement d'origine, n'en a recu aucune. Ces trois occurrences datent
   du 2026-08-12 et rapportent une mesure de `nadia-prof` a cette date precise (elles ne sont donc pas
   fausses en tant que releve historique), mais un lecteur qui les croise avec les fichiers corriges
   ne peut pas savoir, sans lire `DEC-0024`, que le compte a ete revu depuis. Cette fiche n'appartient
   pas au lot fusionne de s17 et je ne l'ai donc pas corrigee ; je le signale pour que la note datee
   deja ecrite ailleurs y soit reprise, a `nadia-prof` elle-meme puisque ce sont ses propres
   constats en premiere personne ("je") que je ne reecris pas a sa place - exactement le motif que
   `camille-po` donne dans la meme correction du 2026-08-19 pour ne pas la corriger elle-meme.

## Verifie sans rien trouver

- **Chemins de poste de travail et noms de compte**, sur l'ensemble du perimetre liste ci-dessus :
  aucune occurrence de `C:\...`, `C:/...`, `/c/...`, `/home/...`, `/Users/...` ni du nom de compte de
  l'utilisatrice.
- **Tirets cadratins et guillemets typographiques**
  (`[\x{2014}\x{2013}\x{201C}\x{201D}\x{2018}\x{2019}]`) sur l'ensemble des documents `squad/` et
  `docs/` du perimetre : aucune occurrence.
- **Emoji et symboles Unicode decoratifs** sur le meme perimetre : aucune occurrence.
- **Parentheses non appariees** sur l'ensemble du perimetre (script de comptage de profondeur,
  hors blocs de code) : un seul cas trouve, corrige (correction 1 ci-dessus).
- **Coherence terminologique des jetons neufs de ce sprint** : `S17-A`, `S17-B`, `S17-C`, `S17-D`
  (definis une seule fois chacun, dans `FICHE-barre-entree-niveau.md`, section "Sonde post-livraison
  du 2026-08-19 (s17) sur S17-1", et repris a l'identique dans `DEC-0039`, `docs/backlog/backlog.md`
  et les propositions d'`ines-design`) ; `COMP-6`, `COMP-7` (definis une seule fois dans
  `FICHE-bandit-manchot-comprehension-cm1.md`, repris a l'identique dans la note de sortie de
  perimetre de `docs/backlog/backlog.md`) ; `FOC-1` a `FOC-6` (definis dans `ARBITRAGE-PREALABLE-
  s17-indicateur-de-focus-clavier.md`, section 7, cites a l'identique partout ailleurs) ; `BPM-1` a
  `BPM-4`, `SYN-1` a `SYN-4` : aucun jeton ne designe deux choses differentes dans deux fichiers. La
  table de correspondance unique exigee depuis s16 pour toute famille de jetons neuve existe bien
  dans `FICHE-barre-entree-niveau.md` pour `S17-A` a `S17-D` (les cinq lignes de l'amendement du
  2026-08-19 sont bien ajoutees, aucune modifiee) et dans `FICHE-bandit-manchot-comprehension-cm1.md`
  pour `COMP-1` a `COMP-7`.
- **Coherence des trois amendements successifs de `DEC-0039`** (`S17-2` sort du perimetre, `S17-C`
  sort du perimetre, mandat sur le cabinet non elargi - plus le premier amendement, non compte parmi
  les "trois", qui relaie les arbitrages prealables de `nadia-prof`) : lus dans l'ordre du fichier,
  ils ne se contredisent a aucun moment - chacun cite explicitement ce qu'il ne rouvre pas des
  precedents, et `DEC-index.md` les recense un par un ("amendee a nouveau", "une troisieme fois",
  "une quatrieme fois") de facon coherente avec le contenu du fichier source. Le seul defaut trouve
  entre deux d'entre eux est le formatage signale en correction 2 (ligne vide manquante), pas une
  divergence de fond. L'ordre de lecture est clair pour un lecteur qui va du debut a la fin du
  fichier ; il ne l'est plus si ce lecteur ne lit que `planning.md` (point signale ci-dessus).
- **Toute reference vers un fichier ou une section inexistante**, sur l'ensemble du perimetre :
  aucune trouvee. Verifie nommement : `DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-
  tete.md` (nom exact du fichier), les deux `ARBITRAGE-PREALABLE-s17-*.md`, `squad/programme/
  francais/competences-cm1.md`, `squad/README.md` sections 9 et 10, `ADR-0049`, `ADR-0050`.
- **Vocabulaire technique du sprint** (specificite CSS, luminance relative WCAG, garde-fou vacueux,
  morsure) : chaque occurrence est accompagnee, au premier usage dans un document, de sa formule ou
  de sa definition operatoire (la formule WCAG est ecrite en toutes lettres a trois reprises
  independantes ; "morsure" est systematiquement suivie de la mutation qui la prouve). Comprehensible
  par un developpeur junior sans contexte externe au document courant.
- **Contenu destine a l'enfant** (`cm1-synonymes.yaml`, 20 items ; `cm1-homophones.yaml`, 24 items,
  enonces raccourcis et indices corriges) : francais correct et integralement accentue, orthographe
  et ponctuation verifiees phrase par phrase - aucune faute trouvee. Accords, conjugaisons et
  emplois des guillemets droits sont corrects sur les deux paquets. Je ne me suis prononcee que sur
  ce registre (`DEC-0002` amende) : la justesse pedagogique des synonymes, des distracteurs et des
  indices reste enterement a `nadia-prof`.
- **Paraphrase dans les commentaires** des deux fichiers YAML de contenu et de `ADR-0050`
  (extraits de code Java cites) : aucune trouvee, chaque commentaire porte un renvoi a une decision
  ou une mesure datee plutot qu'une redite du code.

## Ce que je ne tranche pas

Les deux points de la section "Signale sans corriger" touchent a un etat de decision (perimetre
d'une story, cloture d'un sprint) : ils reviennent a `sofia-sm` pour le premier (gel du planning) et
a `nadia-prof` pour le second (ses propres constats en premiere personne). Aucun des deux n'est un
point produit ou un point d'architecture a router ailleurs.
