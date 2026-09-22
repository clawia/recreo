# DEC-0092 - Objectif et perimetre de s39 : couverture maximale sur six lignes candidates

- **Statut** : Acceptee. Le detail des reserves de sourcage figure en section 3 : l'objectif et le
  lot de six lignes sont formalises sur un enonce recu hors des neuf agents (forme 3), et la
  confirmation attribuee a l'utilisatrice repose sur un seul mot effectivement recu d'elle, *"ok"* -
  pas une citation forme 2 des six lignes ni du principe, que je ne fabrique pas.
- **Date** : 2026-09-22
- **Auteur** : `camille-po`
- **Sprint** : s39 (`s39-sofia`, avant engagement des lots)
- **Prealable, en vigueur et non rouvert par ce document** : `DEC-0089` (perimetre CE1 ouvert,
  mathematiques seule, CM2 differe). Aucune des six lignes de ce document ne rouvre ni ce perimetre
  ni les neuf lignes bloquees par `ART-3`/`DEC-0075` (verifie ligne par ligne en section 4).
- **Lien** : `squad/sprints/s39-couverture-maximale-six-lignes-candidates/planning.md` (sections 2,
  3, 4) ; `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`
  (`DEN-2`, ligne 136) ; `squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-
  saisie-et-deux-points-de-satisfaisabilite.md` (`DEN-2` reconfirmee, ligne 192) ;
  `squad/programme/INSTRUCTION-s25-les-16-lignes-hors-couvert-valide.md` (ligne 57, teneur exacte
  de `DEN-2`) ; `squad/programme/MATRICE-couverture.md` (lignes 154, 590, 594, 597, 1629, 1974) ;
  `DEC-0091` (meme mecanisme pour s38) ; `squad/README.md`, section 2 bis (les trois formes
  d'attribution).
- **Competence programme visee** : transverse - ce document porte sur l'objectif et le perimetre
  d'un sprint, pas sur le contenu d'une ligne.

---

## 1. Ce que ce document fait

1. Formalise, en `DEC`, l'objectif que `planning.md` avait gele sous reserve (section 2 du
   planning) : maximiser la couverture du programme en instruisant six lignes candidates,
   identifiees individuellement, dans le respect de `DEC-0089`.
2. Nomme, sans les masquer, les limites de sourcage de ce document - section 3.
3. Cite en entier l'obstacle preexistant trouve sur la premiere ligne (`DEN-2`), verifie par mes
   soins contre les fichiers qui le portent, et dit explicitement qu'il rend la fermeture de cette
   ligne improbable dans ce sprint - section 4.
4. Route, dans le meme geste, l'instruction des six lots (`docs/backlog/backlog.md`, section
   "Sprint 39") et, pour quatre d'entre eux, un arbitrage pedagogique prealable a `nadia-prof`.

## 2. L'objectif, tel que recu

`planning.md` section 2 cite mot pour mot, sous forme 3 (recu de MASTER, sans source dans le depot,
2026-09-22) : *"Objectif a geler : maximiser la couverture du sprint s39 en fermant, autant que
possible parmi les six lignes ci-dessus, en respectant le perimetre en vigueur (DEC-0089 : CE1
mathematiques seule, CM2 differe - inchange, aucune des six lignes ne le rouvre)."*

Les six lignes visees, verifiees par `sofia-sm` puis reverifiees par moi contre
`squad/programme/MATRICE-couverture.md` en date d'aujourd'hui (toutes `non couvert`) :

| # | Ligne | Matiere / niveau | Code referentiel | Ligne matrice |
|---|---|---|---|---|
| 1 | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | mathematiques CP | `MAT-CP-01` | 154 |
| 2 | Mesurer et comparer des longueurs avec une regle graduee ; utiliser le centimetre et le metre | mathematiques CE1 | `MAT-CE1-09` | 590 |
| 3 | Utiliser la monnaie : composer une somme en euros et en centimes, savoir ce qui est rendu | mathematiques CE1 | `MAT-CE1-13` | 594 |
| 4 | Reconnaitre et nommer des solides usuels (cube, pave droit, boule, cylindre, cone) | mathematiques CE1 | `MAT-CE1-16` | 597 |
| 5 | Identifier des sources et des formes d'energie, et un exemple de conversion d'une forme a une autre | sciences et technologie CM1 | `SCI-CM1-04` | 1974 |
| 6 | Nommer quelques outils, supports et gestes du travail plastique | arts plastiques CP | `ART-CP-11` | 1629 |

## 3. Ce que ce document sourci, et ce qu'il ne sourci pas - a lire avant de s'appuyer dessus

**L'objectif de la section 2 est recu sous forme 3** (`squad/README.md`, section 2 bis) : enonce
hors des neuf agents, cite mot pour mot, non fonde en lui-meme tant qu'il n'est pas confirme par
l'utilisatrice sous forme citable (forme 2 - ses mots a elle, entre guillemets, et la date).

`planning.md` section 2 rapporte, par le meme canal hors des neuf agents, que l'utilisatrice a
confirme "le principe 'maximum de couverture' et ce lot precis de six lignes le 2026-09-22, par la
reponse 'ok' a la proposition qui lui a ete faite". **Ce que je possede reellement entre
guillemets, venant d'elle : le seul mot "ok".** Je ne connais pas la formulation exacte de la
proposition a laquelle elle a repondu, et je ne l'invente pas pour completer une citation forme 2
que je n'ai pas - exactement la reserve que `planning.md` pose lui-meme (section 2) et que je
reprends sans l'attenuer. **Ceci est une sourcage plus mince que celui de `DEC-0091`** : `DEC-0091`
recevait un enonce narre des deux points par le meme canal hors agents, sans guillemets sur les mots
de l'utilisatrice non plus, mais sans pretendre a un mot cite d'elle du tout. Ici, un mot existe
("ok") mais il ne dit rien du contenu de ce qu'il approuve au-dela de ce que le canal hors agents
rapporte. Je ne le fais pas dire plus qu'il ne dit : "ok" confirme qu'une proposition a ete
acceptee, pas quelle etait cette proposition dans le detail - et l'utilisatrice **n'a pas revu les
six lignes une par une**, ce que `planning.md` dit explicitement et que je ne masque pas.

**Ce que je retiens malgre cette reserve, et pourquoi ce document reste Acceptee plutot que
Proposee.** Comme `DEC-0091`, ce document formalise un **objectif et un perimetre de sprint**, du
meme ordre que les arbitrages de strategie deja acceptes sur enonce narre de l'utilisatrice sans
citation entre guillemets (`DEC-0083`, `DEC-0085`, `DEC-0086`, `DEC-0091`). Il ne fixe aucun
contenu pedagogique, aucune borne de tirage, aucun texte d'indice : ces objets-la, la forme 3 ne
les fonde jamais, sans exception, et rien ici ne deroge a cette regle.

**Ce que cela change concretement, en aval de ce document** : les six lots de la section 4 sont
instruisibles et routables des ce document ecrit. Si l'utilisatrice infirme ou ajuste l'objectif ou
le lot des six lignes, ce document est amende ou remplace, jamais silencieusement contourne - meme
clause que `DEC-0089` et `DEC-0091`.

## 4. L'obstacle trouve sur la ligne 1, cite en entier, et ce qu'il change pour l'objectif

**La ligne 1 (`MAT-CP-01`, denombrer jusqu'a 100) porte un arbitrage pedagogique anterieur,
non negociable au dire de son autrice, que je verifie moi-meme plutot que de le recopier d'un
resume.**

`DEN-2` (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, ligne 136) : *"La ligne
reste `en cours` dans la matrice, jamais `couvert-valide`, meme si je valide le paquet [...] Le
verbe 'denombrer', premier de cette ligne, n'est travaille par aucun jeu ; seuls 'lire', 'ecrire' et
'decomposer' le sont [...] C'est la contrepartie de mon acceptation et elle n'est pas negociable."*

Reconfirmee le 2026-08-30 (`ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-
points-de-satisfaisabilite.md`, ligne 192) : *"`DEN-2` est de moi, je la maintiens, et elle est non
negociable : la ligne reste `en cours` parce que le verbe *denombrer*, le premier de la ligne,
n'est travaille par aucun jeu."*

Teneur exacte du manque, verifiee dans `INSTRUCTION-s25-les-16-lignes-hors-couvert-valide.md`,
ligne 57 : *"denombrer exige une collection **dessinee, groupee par dix, sur laquelle l'enfant
agit**"* - et `MATRICE-couverture.md`, ligne 189 : *"'Denombrer' quitte la famille 'contenu seul'
et rejoint la famille **visuelle**, et n'y ouvrira qu'avec une interaction reelle sur la collection
(deplacer, marquer, grouper), pas un simple dessin statique."* Aucune mecanique de ce type - une
collection dessinee, groupee par dix, avec interaction - n'existe dans le depot a la date de ce
document : les mecaniques visuelles existantes (`item illustre`, `designation`) montrent une image
fixe ou font designer une zone, aucune ne fait deplacer, marquer ou grouper des elements d'une
collection.

**Ce que j'en tire, explicitement, plutot que d'instruire un critere intenable** : la fermeture de
la ligne 1 a `couvert-valide` par la seule mecanique textuelle est hors de portee de ce sprint, quel
que soit le soin mis a l'ecriture du paquet - ce n'est pas une question de qualite de contenu, c'est
une mecanique absente du depot, et la construire serait un chantier d'architecture (nouvelle
interaction, regles ArchUnit associees) qui n'entre pas dans le perimetre de ce document. **Ce
document ne retire pas la ligne 1 du lot** : il route la question a `nadia-prof`, avec `DEN-2` cite
ci-dessus en entier, pour qu'elle dise elle-meme si cet arbitrage tient encore tel quel ou si une
voie neuve existe qu'elle jugerait suffisante - je ne prejuge pas sa reponse et je ne la remplace
pas. Mais **la story qu'il instruit pour cette ligne (section 5) ne promet, au mieux, qu'un
deplacement de `non couvert` vers `en cours`**, jamais `couvert-valide`, sauf reponse contraire de
`nadia-prof` elle-meme.

**Consequence pour l'objectif de couverture maximale** : le lot fermable a s39 vise au plus cinq
lignes sur six (lignes 2 a 6), pas six, sauf si `nadia-prof` leve `DEN-2` elle-meme.

## 5. Ce que ce document route

- **Instruction des six lots**, dans `docs/backlog/backlog.md`, section "Sprint 39" : fait dans le
  meme geste que ce document (`S39-DEN`, `S39-LONGUEUR`, `S39-MONNAIE`, `S39-SOLIDES`,
  `S39-ENERGIE`, `S39-OUTILS`).
- **Arbitrage pedagogique prealable de `nadia-prof`, avant tout avis de faisabilite de
  `theo-archi`**, sur quatre des six lots :
  - `S39-DEN` (ligne 1) : `DEN-2` tient-elle encore telle quelle, ou une voie neuve existe-t-elle -
    routee d'office, section 4.
  - `S39-LONGUEUR` (ligne 2) : aucune borne de tirage ni forme de distracteur n'existe pour des
    longueurs en cm/m a cette echelle - question de meme famille que celles tranchees pour les
    additions/soustractions CE1 (`ARBITRAGE-s37-...`, `DEC-0091`).
  - `S39-MONNAIE` (ligne 3) : meme motif - aucune borne ni forme de distracteur pour une
    composition de somme en euros et centimes avec rendu de monnaie a l'echelle CE1. Un precedent
    voisin existe et lui est signale pour eviter une redecouverte a vide : la ligne monnaie CP
    (`MATRICE-couverture.md`, ligne 160, `MON-1`/`MON-3`, item illustre palier 1, deja
    `couvert-valide`) - obstacle route a deux endroits sur un meme perimetre voisin, nomme comme
    tel (amendement s19, `squad/README.md` section 9).
  - `S39-SOLIDES` (ligne 4) : question de forme, pas de tirage - `nadia-prof` a deja **refuse en
    entier** une discrimination visuelle voisine sur `MATRICE-couverture.md` (figures planes CP,
    ligne 189 : *"la discrimination que la ligne demande [...] tient a une proportion, et les
    proportions d'un glyphe sont celles de la police, pas du contenu"*). Reconnaitre un cube d'un
    pave droit souleve potentiellement le meme risque ; je ne tranche pas si le choix textuel
    suffit ou si un rendu visuel fiable est requis, `nadia-prof` le fait.
- **Aucun arbitrage pedagogique prealable route** sur les deux lots restants (`S39-ENERGIE`,
  `S39-OUTILS`) : les deux lignes portent deja, dans `MATRICE-couverture.md`, une note
  caracterisant un contenu de vocabulaire ou de connaissance fixe, jouable au choix textuel avec
  des distracteurs naturellement plausibles, du meme registre que les lignes sciences deja fermees
  sans arbitrage prealable (`SCI-CM1-01`, `SCI-CM1-02`, `SCI-CM1-05`). `theo-archi` peut rendre un
  avis de faisabilite directement ; `nadia-prof` reste seule autorite sur le Done pedagogique final,
  une fois le contenu ecrit et joue.
- **Avis de faisabilite de `theo-archi`**, sur chacun des six lots, une fois l'instruction faite et,
  pour les quatre qui en demandent un, une fois l'arbitrage pedagogique prealable rendu.
- **Affectation nominative du developpement par `sofia-sm`**, jamais directement par `theo-archi` -
  regle de gouvernance reconduite explicitement pour s39 par `planning.md`, section 5.

## 6. Ce que ce document ne fait pas

- Il ne fixe aucune borne de tirage, aucune forme de distracteur, ni aucun contenu pedagogique pour
  aucune des six lignes : ce travail reste entierement a `nadia-prof`.
- Il ne choisit aucun mecanisme technique : ce travail reste a `theo-archi`.
- Il ne rouvre ni `DEC-0089`, ni CM2, ni les neuf lignes bloquees par `ART-3`/`DEC-0075`, ni
  `MAT-CE1-18`.
- Il ne leve pas `DEN-2` : cet arbitrage reste en vigueur tel qu'ecrit tant que `nadia-prof` ne l'a
  pas reexaminee elle-meme.
- Il ne prononce aucun Done, technique ou pedagogique.
- Il ne pretend pas detenir une confirmation forme 2 de l'utilisatrice sur le detail des six lignes
  qu'il n'a pas - section 3.
- Il ne garantit pas la fermeture des six lignes, ni meme de cinq : au moins un lot par ligne
  (quatre sur six) reste bloque tant que l'arbitrage pedagogique prealable qu'il route n'est pas
  rendu.
