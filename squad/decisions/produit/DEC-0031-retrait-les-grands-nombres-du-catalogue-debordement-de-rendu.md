# DEC-0031 - "Les grands nombres" quitte le catalogue jouable : contenu juge bon, debordement de rendu a 360 px, condition de retour executable

- **Type** : perimetre produit, retrait temporaire d'un jeu du catalogue
- **Date** : 2026-08-16
- **Auteur** : camille-po
- **Sprint** : s13
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Nombres et calculs" -
  "Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million"
  (`squad/programme/mathematiques/competences-cm1.md`). Cette decision fait bouger l'etat de cette
  ligne de `squad/programme/MATRICE-couverture.md` (section "Impact" ci-dessous) ; elle ne rouvre
  aucun jugement pedagogique sur le contenu lui-meme.
- **Statut** : Acceptee

## Contexte

`S13-1` a livre `cm1-grands-nombres.yaml` (28 items, jeu `bandit-manchot-grands-nombres-cm1`,
titre de catalogue "Les grands nombres"). `nadia-prof` a rendu deux verdicts successifs sur cet
increment, tous deux ecrits dans `squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md` :

- **Verdict du 2026-08-15** : **REFUSE**, sur deux motifs - le verbe "decomposer" n'etait pas
  reellement travaille (les distracteurs ne faisaient varier que le premier terme de la
  decomposition, et trois items sur sept opposaient un chiffre substitue plutot qu'une vraie
  erreur de rang) et deux indices etaient faux sur l'item `600 040`.
- **Verdict du 2026-08-16, apres reprise (`marc-dev`, `lea-dev`, `theo-archi`)** : **REFUSE une
  seconde fois, sur un motif neuf et un seul. Ses deux motifs du 2026-08-15 sont LEVES, l'un et
  l'autre, sans reserve.** Le motif neuf est un defaut de rendu : le separateur de milliers rendu
  insecable (espace U+00A0, condition MD-4 de son propre arbitrage prealable) produit des jetons
  numeriques plus larges que la colonne d'affichage d'une proposition a 360 px (largeur de
  reference du produit depuis `DEC-0016`) - **119,3 px pour `100 000`, 146,5 px pour `1 000 000`,
  contre une colonne de 96,0 px**. Sur treize items, un ou plusieurs chiffres tombent hors de la
  colonne, parfois hors de l'ecran, en texte sombre sur le fond sombre du cabinet : sur
  `Ecris en chiffres : un million.`, la bonne reponse `1 000 000` se lit **`000 000`** a l'ecran, et
  la revelation au troisieme essai renvoie l'enfant vers un bouton ou ce nombre n'est pas ecrit.
  Une erreur de fait dans un contenu scolaire est pour `nadia-prof` un motif de refus a elle seule,
  meme quand la cause est un rendu et non le contenu.

**Ce que ce refus ne met pas en cause.** `nadia-prof` l'ecrit en toutes lettres dans son second
verdict : les neuf criteres pedagogiques de `camille-po` sont desormais tenus (les deux qui
manquaient sont ceux que les motifs du 2026-08-15 couvraient, et ces motifs sont leves), les
quatre verbes (lire, ecrire, decomposer, comparer) sont travailles a parts egales, les 28 bonnes
reponses et les 28 indices sont exacts, et les 14 distracteurs de decomposition que son
garde-fou de rang atteint sont 14 erreurs reelles de valeur de position (rang confondu, zero
avale, terme manquant), plus aucune substitution de chiffre. Elle l'ecrit d'avance : **"si le
rendu est corrige sans toucher au YAML, je n'aurai plus rien a objecter sur le fond."** Ce
retrait n'est donc pas de la meme famille que `DEC-0009` (Le bon son) ou `DEC-0014` (Le bon
verbe), qui retiraient un jeu dont le contenu enseignait quelque chose de faux. Il est de la
famille de `DEC-0024` ("Le bon petit mot") : **le contenu est juge bon, seule la forme bloque.**

`nadia-prof` mesure par ailleurs, dans ce meme verdict, que le defaut deborde le seul paquet
audite : **sept des vingt-huit items debordaient deja de leur colonne avant ce lot**, a cause de
mots francais longs ("cinquante-", "quarante-", "soixante-" dans les designations litterales des
sept items "lire"), sans rapport avec le separateur de milliers. Elle-meme l'avait manque au
premier verdict, faute d'avoir mesure la largeur plutot que la hauteur. Ce fait est traite en
section 4.

## Decision

### 1. "Les grands nombres" quitte le catalogue jouable jusqu'a ce que le rendu tienne dans sa colonne

Sur decision de l'utilisatrice du 2026-08-16, `bandit-manchot-grands-nombres-cm1` quitte le
catalogue jouable. Ce n'est pas un jugement pedagogique que je porte a la place de `nadia-prof` :
son second verdict est deja ecrit et n'est rouvert par rien de ce qui suit. Le retrait porte sur
la forme, pas sur le fond.

### 2. Le mecanisme est l'absence d'entree dans `catalogue.yaml`, execution immediate

Meme mecanisme que `DEC-0009`, `DEC-0014` et `DEC-0024` : retirer l'entree
`id: bandit-manchot-grands-nombres-cm1` de
`adapters/adapter-content/src/main/resources/catalogue.yaml`, sans drapeau ni chemin mort. Le
paquet de contenu (`adapters/adapter-content/src/main/resources/contenu/cm1-grands-nombres.yaml`)
reste au depot, inchange : c'est la matiere de travail de la correction de rendu, pas un contenu a
reprendre. Je ne modifie pas ce fichier moi-meme : il vit sous `adapters/`, hors de mon perimetre
d'ecriture. Cette action est routee a `sofia-sm` en parallele de cette decision, dans le meme
sprint, pas comme une story a planifier plus tard - `DEC-0024` a deja nomme le mode de defaillance
que je refuse de reproduire (une decision de retrait ecrite sans que le catalogue ne bouge, et un
enfant qui continue de jouer un contenu retire sur le papier).

### 3. Condition de retour, dans les mots de `nadia-prof`

Je reprends sa condition au mot pres, sans la traduire en une propriete plus commode a tester :

> **Une seule story a ecrire pour lever ce refus : que les propositions du paquet tiennent dans
> leur colonne a 360 px, sans qu'aucun chiffre sorte du bouton ni de l'ecran.**
> (`squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md`, verdict du 2026-08-16, point 10)

Deux precisions qu'elle pose elle-meme et que je ne desserre pas :

- **Ne pas revenir a l'espace ordinaire** pour corriger le debordement : ce serait rouvrir le
  motif de refus du 2026-08-15 (la coupure de ligne qui fausse la lecture d'une decomposition),
  deja leve une fois.
- **Ne pas changer le separateur pour l'espace fine (U+202F)** en croyant corriger le rendu :
  elle a mesure que le gain de largeur (4,5 a 8,8 px) ne suffit pas a faire entrer les jetons dans
  leur colonne ; la cause est la largeur de la colonne, pas le caractere du separateur.

Le retour au catalogue suppose deux choses, comme pour `DEC-0024` : cette condition de forme
mesuree en jouant (jamais sur une declaration), **et** que `nadia-prof` confirme qu'elle n'a plus
rien a objecter sur le fond une fois le rendu corrige - ce qu'elle a deja ecrit d'avance, sans
attendre un troisieme audit du contenu qui n'aura pas change.

### 4. Le debordement deborde ce jeu : dette generale, chiffrage en parallele chez `theo-archi`

Ce qui bloque "Les grands nombres" n'est pas une propriete de ce paquet. La colonne de 96 px a
360 px de large est celle des onze jeux du catalogue actuel, sur la mecanique bandit manchot
partagee ; `nadia-prof` a mesure que sept de ses propres items debordaient deja de cette colonne
avant meme le lot de reprise, a cause de mots francais longs sans rapport avec un separateur de
milliers. **Quatre lots ont traverse une CI verte avec ce defaut avant qu'il ne soit vu en
jouant** : aucun garde-fou de ce depot ne connait la largeur d'un ecran.

`theo-archi` chiffre en parallele, sur saisine de `sofia-sm`, les voies de correction possibles -
dont une demandee par l'utilisatrice, remplacer le nombre affiche par une image plutot que par du
texte rendu. Je n'anticipe pas son avis et je ne retiens ici aucune des voies : cette decision
acte seulement que la dette est generale, qu'elle depasse "Les grands nombres", et qu'elle attend
son chiffrage avant qu'une story de correction ne soit ecrite.

## Ce que cette decision ne fait pas

- Elle ne rejuge pas le contenu de `cm1-grands-nombres` : le second verdict de `nadia-prof` du
  2026-08-16 reste entierement valide et n'est pas rouvert par ce document. Les neuf criteres
  pedagogiques restent tenus.
- Elle ne prejuge pas du verdict que `nadia-prof` rendra une fois le rendu corrige, ni du chiffrage
  de `theo-archi` sur les voies de correction.
- Elle ne tranche aucune question technique : le choix de la voie de correction (largeur de
  colonne, taille de police, remplacement par une image, ou toute autre) appartient a `theo-archi`,
  saisi par `sofia-sm`.
- Elle ne prononce aucun Done pedagogique et ne rouvre aucun des neuf criteres : ce jugement reste
  entierement celui de `nadia-prof`.
- Elle ne fixe pas de seuil chiffre de caracteres ou de pixels comme condition universelle de
  retour : la condition du point 3 porte sur la visibilite effective mesuree en jouant, pas sur une
  valeur inventee a l'avance - meme discipline que `DEC-0016` et `DEC-0024`.
- Elle ne modifie aucune fiche de conformite : le bloc de validation pedagogique de
  `FICHE-bandit-manchot-grands-nombres-cm1.md` reste tel que `nadia-prof` l'a ecrit.

## Impact

- `adapters/adapter-content/src/main/resources/catalogue.yaml` : l'entree
  `id: bandit-manchot-grands-nombres-cm1` est retiree - action technique immediate de ce sprint,
  routee a `sofia-sm` dans le meme geste que cette decision, pas une story a planifier (point 2).
- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques CM1 "Lire, ecrire, decomposer et
  comparer les nombres entiers jusqu'au million" reste `en cours` - elle n'a jamais atteint
  `couvert-valide`, elle ne peut donc pas en redescendre comme la ligne homophones au sprint 10
  (`DEC-0024`). La nuance ecrite dans la matrice porte sur la nature de cet "en cours" : un contenu
  acheve et juge pedagogiquement bon (9 criteres sur 9), bloque par un seul defaut de rendu qui
  deborde du paquet lui-meme, et non un contenu encore a l'etude.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0031 ajoutee.
- Aucune fiche de conformite n'est modifiee par cette decision.
- Aucune story n'est ouverte au backlog par ce document : la voie de correction n'est pas connue
  tant que `theo-archi` n'a pas rendu son chiffrage (section 4). La story de correction sera
  ecrite, avec la condition du point 3 comme critere de sortie, des que ce chiffrage sera
  disponible.

## Ce qui reste non testable par construction

- Que le retrait soit la bonne reponse produit plutot que, par exemple, un correctif de rendu
  livre en urgence avant tout retrait, est un jugement d'opportunite de l'utilisatrice. Il n'est
  pas pedagogique : je ne pretends pas juger si un enfant perd plus a ne plus jouer "Les grands
  nombres" qu'a le jouer sous une forme qui lui montre un nombre faux.
- Que la condition du point 3 soit reellement atteinte sur le rendu corrige ne peut se verifier
  qu'en jouant, jamais sur une promesse - meme limite que `DEC-0016` et `DEC-0024`.

## Alternatives considerees

**Corriger le rendu dans ce sprint avant tout retrait, pour eviter une interruption du jeu.**
Ecartee : la voie de correction n'est pas connue avant le chiffrage de `theo-archi` (section 4),
et le defaut touche potentiellement les onze jeux du catalogue, pas seulement celui-ci - le
chiffrer avant de choisir une voie evite de corriger "Les grands nombres" seul puis de devoir
recommencer sur les sept autres items deja debordants ailleurs dans le catalogue.

**Garder le jeu au catalogue en assumant le defaut par ecrit**, sur le modele de `DEC-0025`
(pli) ou `DEC-0026` (confirmation de perte de partie). Ecartee sur la nature du defaut :
`DEC-0025` et `DEC-0026` assumaient une degradation de confort de lecture ou d'ergonomie ; ici,
l'enfant lit un nombre qui n'est pas celui ecrit dans le contenu - ce n'est pas un defaut de
confort, c'est une erreur de fait montree a un enfant en train d'apprendre.

## Amendement du 2026-08-16 (s13) - la voie de rendu est tranchee, un prealable circulaire est leve

Depuis la decision d'origine : `nadia-prof` a rendu l'arbitrage prealable qu'attendait la section 4
(`squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md`), en jouant les quatre
voies chiffrees par `theo-archi` - voie 4 retenue ("la colonne recule devant son contenu"), sous ses
conditions VR-1 a VR-7. `lea-dev` l'a livree (branche `s13-lea-voie-4`), fusionnee dans `round-two`.
Elle ecrit d'avance, dans ce meme arbitrage, que le motif de son second refus sur `cm1-grands-nombres`
"tombe le jour ou cette story est livree, sans toucher au YAML".

### 1. Le prealable qu'elle pose, et pourquoi il est fonde

`nadia-prof` refuse de considerer sa propre phrase ci-dessus comme suffisante pour rouvrir le Done de
`cm1-grands-nombres` :

> `cm1-grands-nombres` est sorti du catalogue par `DEC-0031` : je n'ai pas pu le jouer. Ce que je
> dis de son comportement sous la voie 4 est la mesure de `theo-archi`, pas la mienne. Sa remise au
> catalogue est un prealable a ma reverification, pas une consequence de mon accord.

C'est le meme principe que tout son mode d'audit sur ce depot : elle ne juge jamais un increment
qu'elle n'a pas joue elle-meme, page de catalogue comprise. Le retrait de la section 2 de cette
decision a produit, sans que je l'aie anticipe a l'ecriture, un blocage circulaire : le jeu ne
revient au catalogue que verifie, et il ne peut etre verifie que revenu au catalogue. Je le leve ici.

### 2. Ma decision : le jeu revient, mais seulement dans un chemin qu'aucun enfant ne peut atteindre

Je ne renvoie pas `id: bandit-manchot-grands-nombres-cm1` dans le
`catalogue.yaml` de `round-two`. Le chemin que je retiens est celui que `nadia-prof` emploie deja
pour tout arbitrage prealable de ce depot, et que ce document lui-meme atteste (`ARBITRAGE-PREALABLE-
s13-voie-de-rendu-des-propositions.md`, section 1) : un worktree dedie, hors de l'arbre de
`round-two`, ou l'entree est reinstallee sur une branche qui n'est jamais fusionnee tant que son
verdict n'est pas rendu. `nadia-prof` y reconstruit le jar depuis ce worktree et joue le jeu par
l'ecran de catalogue reel, exactement comme elle joue tout autre increment - ce n'est donc pas une
mesure degradee de sa part, c'est son mode de verification habituel applique a un cas ou le chemin de
production ne le permet plus.

Ce que ce choix expose, dit franchement :

- **Aucun enfant n'est expose.** Le `catalogue.yaml` livre et joue par un enfant reste celui de
  `round-two`, ou l'entree demeure absente jusqu'a un verdict favorable. Ce n'est pas un compromis
  entre le risque et la verification : c'est le point exact que `DEC-0031` visait a proteger (la
  section "Ce que cette decision ne fait pas" nommait deja le mode de defaillance de `DEC-0009`, un
  enfant continuant de jouer un contenu retire sur le papier) et je ne le rouvre pas pour resoudre ce
  prealable.
- **Ce que ca coute reellement** : ce chemin s'ecarte du mecanisme employe par `DEC-0009`, `DEC-0014`,
  `DEC-0018` et `DEC-0024`, ou le jeu corrige rentrait directement dans le catalogue joue en
  production des sa correction livree. Ce mecanisme-la ne peut pas etre reemploye ici sans recreer
  exactement le prealable que `nadia-prof` refuse : le remettre en production avant son verdict
  reviendrait a lui demander d'accorder ou de refuser un Done sur un increment qu'un enfant joue deja,
  ce que sa porte n'a jamais fait sur aucun jeu de ce catalogue. Le cout est une action technique de
  plus (une branche jetable a ouvrir, dont la duree de vie doit rester courte pour ne pas devenir une
  seconde memoire non tenue a jour) : je la route a `sofia-sm`, dans le meme geste que cet amendement,
  pas comme une intention a planifier - meme discipline que la section 2 de la decision d'origine.
- **Si le verdict est VALIDE** : la meme entree rejoint alors `catalogue.yaml` sur `round-two`, par le
  mecanisme ordinaire de `DEC-0009`/`DEC-0014`/`DEC-0018`/`DEC-0024` (fusion directe, execution
  immediate), et la branche de verification est abandonnee sans etre fusionnee - elle n'a jamais servi
  qu'a permettre le jeu, pas a porter le code final.

### 3. Si `nadia-prof` refuse une seconde fois, sur le rendu corrige

Ma decision d'origine ne le prevoyait pas et je l'ecris maintenant, pour ne pas le decouvrir a
l'usage. Un jeu qui sort, revient en verification et ressort n'est pas la meme chose qu'un jeu retire
une fois :

- La branche de verification n'est, dans ce cas, **jamais fusionnee** : `round-two` ne porte a aucun
  moment le jeu refuse une seconde fois, et le retrait de `DEC-0031` reste entierement en vigueur,
  sans avoir jamais ete interrompu.
- Je n'ecris pas un `DEC` neuf pour ce second refus : je complete cet amendement par une note datee
  supplementaire, sur le meme modele que celui-ci, pour que l'historique montre qu'il s'agit d'un
  second passage et non d'un premier retrait relu. Si un troisieme passage devait un jour se produire
  sur ce meme jeu, je le compterais explicitement comme tel, avec le numero du sprint de chaque
  passage - la lecon de `DEC-0007` sur les reports qui s'accumulent sans qu'on les compte s'applique
  ici a un cycle de retour, pas seulement a un report de perimetre.
- Un second refus sur un motif de rendu, apres que la voie 4 a ete arbitree precisement pour fermer ce
  motif, changerait la nature du probleme : ce ne serait plus une correction a finir, ce serait un
  signe que la voie 4 ne tient pas la promesse sur laquelle `nadia-prof` a fonde tout son arbitrage.
  Je le remonterais alors a `sofia-sm`, qui route a `theo-archi`, plutot que de traiter la story comme
  une simple reprise de plus.

### 4. Ce retour n'est pas le Done

La ligne de `squad/programme/MATRICE-couverture.md` ("Lire, ecrire, decomposer et comparer les
nombres entiers jusqu'au million") reste `en cours`, exactement comme la decision d'origine l'a
ecrit, et ne bouge pas du fait de cet amendement : ni la reouverture d'un chemin de verification, ni
la fusion de la voie 4 dans `round-two`, ni la phrase ecrite d'avance par `nadia-prof` dans son
arbitrage ne valent Done pedagogique. Seul un verdict qu'elle rend apres avoir joue l'increment,
ecrit dans `squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md`, peut faire bouger cette
ligne - dans un sens comme dans l'autre.

### 5. "Le perimetre" : refuse une seconde fois, sur le motif exact du retrait de "Les grands nombres" - je ne le retire pas

`nadia-prof` a rouvert et refuse, le meme jour et dans le meme arbitrage
(`squad/conformite/FICHE-bandit-manchot-perimetre-cm1.md`, verdict du 2026-08-16), le Done
pedagogique qu'elle avait accorde le 2026-08-14 sur `bandit-manchot-perimetre-cm1` : sur l'item
`Le perimetre d'une figure, c'est...`, la bonne reponse `la longueur de son contour` est percutee
lettre sur lettre par le mot `nombre` de la colonne voisine, et `interieure` sort de l'ecran - le
motif exact sur lequel "Les grands nombres" a ete retire, qu'elle nomme elle-meme comme identique
("une erreur de fait dans un contenu scolaire est un motif de refus a elle seule, que la cause soit
un YAML ou un rendu").

Elle ecrit explicitement ne pas demander le retrait : *"le perimetre du retrait appartient a
`camille-po`"*. Je ne retire pas ce jeu du catalogue, et je le distingue de "Les grands nombres" sur
un point qui n'est pas pedagogique mais d'etat du code : le correctif qui ferme ce motif (la voie 4)
est deja fusionne dans `round-two`, la ou "Les grands nombres" attendait encore ce correctif au
moment de son propre retrait. Retirer "Le perimetre" aujourd'hui pour l'y remettre des qu'un nouveau
verdict serait rendu n'aurait pas de sens operationnel et coute a l'enfant de CM1 un jeu de moins pour
une fenetre que le code a deja refermee, sur la propre mesure de `nadia-prof` en jouant l'etat de la
voie 4 (*"les trois propositions deviennent integralement lisibles"*, meme fiche).

Ce que je n'efface pas pour autant : **le Done pedagogique de cette ligne est refuse aujourd'hui**, et
tant qu'aucun nouveau verdict n'est rendu sur le code reellement fusionne dans `round-two`, un enfant
joue un jeu dont le Done est rouvert, sur la base que le correctif deja livre referme le motif -
constat de code, pas jugement pedagogique que je m'autorise a la place de `nadia-prof`. Je l'ecris
au lieu de le laisser dans l'ombre d'une ligne restee `couvert-valide` par defaut : la ligne
mathematiques CM1 "Calculer un perimetre de figures usuelles" repasse de `couvert-valide` a
`en cours` dans la matrice (detail en section Impact), et `bandit-manchot-perimetre-cm1` entre dans
`squad/validation/FILE-ATTENTE.md` comme increment dont le Done technique est atteint mais dont le
Done pedagogique attend un nouveau verdict.

### 6. La reserve sur "Les mesures" - un troisieme garde-fou de rang et un audit, au backlog du sprint 14

`nadia-prof` corrige, dans le meme arbitrage, son propre constat du 2026-08-14 sur
`bandit-manchot-conversions-unites-cm1` ("Les mesures") : presser systematiquement la proposition la
plus courte gagne 47,5 % des manches jouees (contre 33,3 % au hasard), parce que la bonne reponse est
la plus courte des trois chaines sur 13 items sur 24 - un raccourci qu'elle avait lu comme une
statistique acquise plutot que jouee comme une strategie. Ce n'est pas un motif de refus : le Done
pedagogique de cette ligne reste `couvert-valide`, non rouvert par cette reserve. Ce n'est pas non
plus une action de ce sprint : la story de correction, avec le garde-fou de rang qu'elle nomme
elle-meme (section 7, point 5 de son arbitrage : *"aucune propriete de forme d'une proposition - sa
longueur, sa largeur, son rang - ne fait gagner mieux que le hasard"*) et l'audit des onze paquets du
catalogue qu'elle en deduit, sont ecrits au backlog (`docs/backlog/backlog.md`, section "Backlog -
sprints suivants") comme candidats au sprint 14. Le sprint 13 ne les porte pas.

## Impact (complement du 2026-08-16)

- `adapters/adapter-content/src/main/resources/catalogue.yaml` de `round-two` : aucune entree neuve
  par cet amendement. Une entree `id: bandit-manchot-grands-nombres-cm1` est ouverte uniquement sur
  une branche de verification dediee et non fusionnee, routee a `sofia-sm` dans le meme geste que cet
  amendement (section 2).
- `squad/programme/MATRICE-couverture.md` : la ligne "Lire, ecrire, decomposer et comparer les
  nombres entiers jusqu'au million" reste `en cours`, inchangee par cet amendement (section 4). La
  ligne "Calculer un perimetre de figures usuelles (carre, rectangle)" repasse de `couvert-valide` a
  `en cours` (section 5).
- `squad/validation/FILE-ATTENTE.md` : `bandit-manchot-perimetre-cm1` et `bandit-manchot-grands-
  nombres-cm1` y entrent tous les deux, cette file etant restee au compte du sprint 8 malgre leurs
  refus (section 5, et section 1 pour le second).
- `docs/backlog/backlog.md` : une story neuve, candidate au sprint 14, sur le garde-fou de longueur et
  l'audit des onze paquets (section 6).
- `squad/decisions/produit/DEC-index.md` : statut de `DEC-0031` complete par la mention de cet
  amendement.
- Aucune fiche de conformite n'est modifiee par cet amendement : les verdicts cites restent tels que
  `nadia-prof` les a ecrits.
