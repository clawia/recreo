# Planning - s40 - couverture maximale sur six lignes candidates (EMC, histoire des arts, sciences)

- **Horodatage de debut** : 2026-09-23 09:05 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s40-couverture-maximale-emc-histoire-sciences`, choisi par `sofia-sm` a l'ouverture -
  aucun agent n'avait encore prepare de worktree pour ce sprint.
- **Prealable, en vigueur et non rouvert par ce planning** : `DEC-0089` (Acceptee, `DEC-index.md`)
  - perimetre CE1 ouvert, mathematiques seule, CM2 differe explicitement. Ce planning ne rouvre
    aucune matiere ni aucun niveau hors de ce perimetre : verifie ligne par ligne en section 3.
  `DEC-0075` (neuf lignes bloquees, droits d'usage de reproductions d'oeuvres non tranches) reste
  egalement non rouvert : verifie ligne par ligne en section 3, aucune des six ne s'y trouve.

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`git merge-base --is-ancestor round-two HEAD`) : `s40-sofia` (`../recreo-s40-sofia`) descend de
`round-two` a `49f6539c` (dernier commit de cloture de s39), ouvert depuis ce commit sans aucun
autre ecart.

## 2. Objectif gele, et sa provenance

**Forme d'attribution (`squad/README.md`, section 2 bis)** : cet objectif ne vient d'aucun des
neuf agents. Il est porte sous la **forme 3**, cite mot pour mot, et il **ne vaut pas motif** tant
qu'il n'est pas ecrit ailleurs par l'agent dont c'est le role - meme mecanisme que `DEC-0089`,
`DEC-0091` et `DEC-0092`.

> **Recu de MASTER, sans source dans le depot, le 2026-09-23** : *"`camille-po` a produit une
> analyse de candidats a couverture maximale (transmise hors depot, forme 3), identifiant six
> lignes deja pre-arbitrees 'jouable au choix textuel' (meme regime que monnaie/energie/outils
> fermees a s39, aucune mecanique neuve)"* - suivi de la liste des six lignes (section 3
> ci-dessous) et de : *"Objectif a geler : maximiser la couverture du sprint s40 en fermant,
> autant que possible parmi les six lignes ci-dessus, en respectant le perimetre en vigueur
> (DEC-0089 inchange, aucune des six lignes ne le rouvre)."*

> **Recu de MASTER, sans source dans le depot, le 2026-09-23, sur la confirmation utilisatrice** :
> *"L'utilisatrice a confirme le principe et ce lot precis le 2026-09-23 par la reponse 'oui
> continue le sprint jusqu'à la clôture' a la proposition qui lui a ete faite"*.

**Ce que je retiens de cette seconde citation, et ce que je ne fabrique pas.** Le seul mot que je
possede entre guillemets, venant reellement de l'utilisatrice, est *"oui continue le sprint
jusqu'à la clôture"*. Je ne sais pas, a partir de ce que j'ai recu, quelle etait la formulation
exacte de la proposition a laquelle elle a repondu, ni si elle designait litteralement les six
lignes ou le principe general de poursuite du sprint - je ne l'invente pas pour completer une
citation forme 2 que je n'ai pas. **Ce que je peux dire sans fabriquer** : le principe "maximum de
couverture" et ce lot precis de six lignes m'arrivent comme confirmes par l'utilisatrice le
2026-09-23, mais **elle n'a pas revu ligne par ligne** les six candidats - meme rigueur que
`DEC-0092`, section 1, sur le mot unique "ok" recu a s39. Je route donc a `camille-po`, dans le
meme geste que la section 4, la formalisation de cet objectif en `DEC` (creation), avec exactement
cette meme reserve ecrite - pas plus, pas moins que ce que je possede.

**Consequence directe pour ce planning** : je gele l'intention operationnelle qui suit comme
**perimetre de travail du sprint**, sous la meme reserve que `DEC-0091`/`DEC-0092` posaient pour
s38/s39 : tant que le `DEC` qui la formalise n'existe pas physiquement, aucun lot de ce sprint
n'est livrable ; il reste instruisible et routable, ce que ce planning fait.

**Intention operationnelle gelee, sous cette reserve** : maximiser la couverture du programme en
fermant, autant que possible, les six lignes de la section 3, dans le respect de `DEC-0089`.

**Explicitement hors perimetre de ce sprint**, sur consigne recue de MASTER (forme 3, non motif
a elle seule tant que non reconfirmee par un agent) :

- les deux lignes `en cours` de s39 - `MAT-CP-01` (denombrement CP) et `MAT-CE1-09` (longueurs
  CE1), toutes deux verifiees `non couvert -> en cours` dans `MATRICE-couverture.md` (lignes 5559
  et 5560) : blocages inchanges, aucune de ces deux lignes n'est retenue ici ;
- `EMC-CM1-06` ("Comprendre le sens des principes de la Republique : liberte, egalite, fraternite,
  laicite, refus des discriminations"), verifiee explicitement marquee "ligne la plus dangereuse
  de la matiere" (`MATRICE-couverture.md`, ligne 1899) et `SCI-CM1-?` ("Identifier un effet de
  l'activite humaine sur l'environnement...", `MATRICE-couverture.md`, ligne 2002, "ligne la plus
  dangereuse du lot") - les deux lignes "les plus dangereuses" citees par la consigne, chacune
  necessitant un arbitrage de redaction prealable non instruit ici ;
- `EMC-CM1-03` ("Identifier ce qu'interdit le respect d'autrui dans ses differences", forme CM1 de
  `EMC-CP-04`), au meme defaut que celui documente a s31
  (`squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-
  et-ma-borne.md`, ligne 231 : `EMC-4` interdit la forme de l'enumeration, il ne reste que la forme
  de l'interdit, donc une seule tache, `FER-3` non tenable) ;
- les neuf lignes bloquees par `ART-3`/`DEC-0075` (droits d'image non tranches) - question de
  perimetre signalee, non tranchee, non rouverte ici.

`MAT-CE1-18` (sens de la division, mathematiques CE1) reste hors perimetre de ce sprint : dette
connue depuis s36, signalee sans devenir une story cinq sprints consecutifs (`retro.md` de s38 et
s39). Non engagee ici pour la meme raison qu'aux sprints precedents - ce n'est pas une des six
lignes de la consigne recue.

## 3. Les six lignes candidates - verification physique avant tout gel

**Ce que je verifie moi-meme plutot que de recopier l'analyse recue** (consigne explicite de ce
planning, meme discipline qu'a s39 ou un ecart avait ete trouve sur la ligne 1 de l'analyse
initiale) : chaque ligne est bien `non couvert` dans `squad/programme/MATRICE-couverture.md`
aujourd'hui, l'annotation "jouable au choix textuel" citee par la consigne y figure reellement, et
chaque ligne est verifiee individuellement contre `DEC-0089`, `DEC-0075` et tout arbitrage
pedagogique deja documente dans `squad/conformite/`.

| # | Identifiant referentiel | Ligne | Matiere / niveau | Etat verifie dans `MATRICE-couverture.md` |
|---|---|---|---|---|
| 1 | `HDA-CM1-05` | Relier les caracteristiques d'une oeuvre d'art a des usages et au contexte historique et culturel de sa creation | histoire des arts CM1 | ligne 1941, `non couvert - jouable des aujourd'hui au choix textuel, seule ligne de la matiere sans image requise` |
| 2 | `SCI-CM1-20` | Extraire une information d'un document scientifique (texte, tableau, schema, graphique) et la restituer | sciences et technologie CM1 | ligne 2004, `non couvert - jouable au choix textuel, sur le texte et le tableau`, mention exigee d'avance : "document textuel et tableau seuls ; le schema et le graphique restent non couverts" |
| 3 | `SCI-CM1-09` | Nommer les grandes fonctions de nutrition du corps humain (digestion, respiration, circulation) et l'organe principal de chacune | sciences et technologie CM1 | ligne 1993, `non couvert - jouable au choix textuel` |
| 4 | `EMC-CM1-04` | Connaitre les regles de la vie collective a l'ecole et dire a quoi elles servent | enseignement moral et civique CM1 | ligne 1897, `non couvert - jouable au choix` |
| 5 | `EMC-CM1-08` | Distinguer une regle d'une loi, et identifier ce que la loi autorise, interdit et protege | enseignement moral et civique CM1 | ligne 1901, `non couvert - jouable au choix` |
| 6 | `EMC-CP-09` | Connaitre la devise de la Republique et donner un exemple concret de liberte, d'egalite et de fraternite | enseignement moral et civique CP | ligne 1845, `non couvert - jouable au choix, sous EMC-5` |

**Les six sont bien `non couvert`, et l'annotation "jouable au choix textuel" (ou "jouable au
choix" pour les lignes 4 et 5, meme famille, verifiee au sens litteral) est reellement portee par
la matrice pour chacune.** Aucune des six ne rouvre `DEC-0089` : aucune n'est CE1 - la ligne 1 est
histoire des arts CM1, les lignes 2 et 3 sont sciences et technologie CM1, les lignes 4 et 5 sont
EMC CM1, la ligne 6 est EMC CP (niveau ouvert depuis `DEC-0001`, s0). Aucune n'entre dans le
perimetre des neuf lignes bloquees par `ART-3`/`DEC-0075` : celles-ci portent sur les lignes 1, 2,
3, 4 et 6 du referentiel histoire des arts CM1 (decrire, degager les caracteristiques, identifier
les materiaux, associer a une epoque, identifier des reperes du patrimoine) et sur quatre lignes
d'arts plastiques ; la ligne 1 retenue ici est `HDA-CM1-05` ("relier ... au contexte historique et
culturel"), explicitement hors de cette liste et explicitement notee par `nadia-prof` comme "seule
ligne de la matiere sans image requise" - c'est cette absence d'image qui la sort du blocage des
neuf autres.

### 3.1 Obstacles connus verifies absents pour les six lignes

Recherche menee sur `squad/conformite/`, `squad/decisions/produit/` et les referentiels
`squad/programme/enseignement-moral-et-civique/competences-cm1.md`,
`squad/programme/enseignement-moral-et-civique/competences-cp.md`,
`squad/programme/histoire-des-arts/competences-cm1.md` et
`squad/programme/sciences-et-technologie/competences-cm1.md` : aucune clause de la famille de
`DEN-2` (s39) ou de `EMC-4` sur `EMC-CM1-03`/`EMC-CP-04` (un verbe de competence qui reduit la
ligne a une seule tache non tenable) n'est ecrite pour les lignes 1 a 6 retenues ici. Les seules
conditions deja documentees qui les touchent sont des conditions de forme d'item deja connues et
non bloquantes : `HIS-2` (contextes a lectures multiples, citee comme "barre critique" pour la
ligne 1, pas comme un blocage), `ART-1` (la reponse ne se porte jamais sur une couleur percue, deja
tenue par les paquets EMC-CP deja livres) et `EMC-5` (la devise se restitue exactement, l'exemple
doit avoir deux distracteurs nettement hors sujet). La note prealable "connaissances civiques et
reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation
a une decision collective ne sont servis par ce produit", deja posee pour toute ligne d'EMC CM1
qui bouge (`squad/programme/MATRICE-couverture.md`, ligne voisine de `EMC-CM1-07`), s'applique de
fait aux lignes 4 et 5.

**Ceci ne vaut pas absence d'arbitrage.** Comme a s38 et s39, chacune des six lignes passe par le
meme filtre que `camille-po` applique systematiquement avant l'avis de faisabilite de
`theo-archi` : verifier si elle souleve une question pedagogique (bornes de tirage, forme des
distracteurs, ou toute autre question de meme famille) et router a `nadia-prof` si oui. Une
absence d'arbitrage deja ecrit dans le depot n'est pas une preuve qu'aucun n'est du - en particulier
pour la ligne 1 (`HIS-2`, contextes a lectures multiples) et pour la ligne 5 (`EMC-CM1-08`, ou "loi"
doit rester accessible sur des exemples concrets sans definition juridique dans l'enonce).

## 4. Routage

**Vers `camille-po`** : worktree dedie deja ouvert par `sofia-sm` au moment de ce routage
(`../recreo-s40-camille`, branche `s40-camille`, descend de `round-two` a `49f6539c` - amendement
s39, `squad/README.md` section 9).

- ecrire le `DEC` qui formalise l'objectif recu de MASTER (section 2) - creation, pas amendement,
  puisqu'aucun `DEC` existant ne porte cet objectif - avec la meme reserve de sourcage que
  `DEC-0092` : le principe et le lot de six lignes sont portes comme confirmes par l'utilisatrice
  le 2026-09-23 sur la seule phrase citable "oui continue le sprint jusqu'à la clôture", sans
  qu'elle les ait revus ligne par ligne ;
- instruire les six lots (un par ligne de la section 3) dans `docs/backlog/backlog.md`, avec des
  criteres d'acceptation testables, en nommant explicitement pour chacun s'il souleve une question
  pedagogique (bornes de tirage, forme des distracteurs) et en le routant a `nadia-prof` si oui ;
- verifier, au moment d'instruire, qu'aucune des six lignes ne rouvre `DEC-0089` ni les neuf
  lignes bloquees par `ART-3`/`DEC-0075`, et qu'aucune n'est l'une des lignes explicitement
  ecartees en section 2 - deja fait a titre de premiere passe en section 3, a reverifier par elle
  a l'instruction.

**Vers `nadia-prof`** (aucun worktree ouvert a ce planning : aucune des six lignes n'a encore ete
instruite par `camille-po`, rien n'est encore route a `nadia-prof` a ce stade - `sofia-sm` ouvrira
son worktree au moment ou `camille-po` route reellement une question pedagogique vers elle) :

- arbitrage prealable sur chacune des six lignes si `camille-po` y trouve une question pedagogique
  en les instruisant, en particulier la ligne 1 (`HIS-2`, lectures multiples d'un contexte
  historique) et la ligne 5 (`EMC-CM1-08`, la frontiere entre regle et loi sur des exemples
  concrets) - section 3.1.

**Vers `theo-archi`** : rendre un avis de faisabilite ecrit sur chacune des six lignes, une fois
que `camille-po` les a instruites et, pour celles qui en demandent un, une fois l'arbitrage
pedagogique prealable de `nadia-prof` rendu.

## 5. Regle de gouvernance du sprint - reconduite explicitement, pas heritee par defaut

**Verification faite avant de gouverner ce sprint** : la regle posee par `DEC-0091`/`DEC-0092`
("un avis de faisabilite rendu par `theo-archi` ne vaut jamais livraison ; le developpement est
affecte nominativement par `sofia-sm`") a ete ecrite **"pour ce sprint"** a s38 et reconduite
explicitement a s39 (`planning.md` de s39, section 5). `retro.md` de s39, section 3, ne l'a versee
en amendement permanent de `squad/README.md` que sur un point distinct (l'ouverture du worktree au
moment du routage, deja appliquee ci-dessus, section 4) - pas sur ce point de gouvernance-ci, qui
n'est donc pas en vigueur par defaut a s40. Je la reconduis ici explicitement, pour le meme motif
qui l'a fait naitre a s38 (`theo-archi` a livre du code hors de son role d'avis de faisabilite,
sans qu'un planning ne l'ait decide) et parce qu'aucun evenement de s40 ne justifie de s'en
affranchir.

**Regle gelee pour s40** : des qu'un avis de faisabilite de `theo-archi` est rendu sur une des six
lignes, le developpement qui en decoule est affecte nominativement (`marc-dev`, `lea-dev`, ou
`sofia-sm` sous sa casquette dev) et n'est pas livre directement par `theo-archi`.

## 6. Affectations nominatives

**Aucune affectation de developpement a ce planning.** Les six lignes de la section 3 sont toutes
bloquees avant tout engagement de code : aucune story instruite avec criteres d'acceptation
testables, aucun avis de faisabilite rendu, et pour celles qui en demandent un, aucun arbitrage
pedagogique prealable rendu.

Des qu'un avis de faisabilite est rendu sur une ligne (section 4), Sofia l'affecte nominativement
au daily suivant - aucun fichier n'est du a ce moment-la, l'affectation remonte dans le tableau des
obstacles de `review.md` comme le reste du suivi de sprint.

## 7. Verification `PAR-16` - point de controle poste/story

**Ce que `PAR-16` demande litteralement** (`squad/README.md`, section 4) : verifier, poste par
poste, la table de composition de la decision de strategie en vigueur. **Cette table n'existe pas
a s40**, meme constat qu'aux cinq sprints precedents (s35 a s39) : la fenetre du Plan 2 (`DEC-0083`)
reste refermee par `DEC-0087` sans qu'une strategie neuve de composition budgetaire n'ait ete
tranchee au-dela.

**Ce que `PAR-16` verifie a la place, meme fallback qu'aux cinq sprints precedents** : chaque
element retenu porte-t-il une affectation nominative ou un motif explicite de non-action ?

| Element | Affectation nominative | Issue |
|---|---|---|
| Objectif du sprint (couverture maximale, six lignes) | `camille-po` | Routee - `DEC` a ecrire avant tout lot livrable (section 2) |
| Ligne 1 - `HDA-CM1-05` (contexte historique et culturel d'une oeuvre) | `camille-po`, puis `nadia-prof` si question pedagogique (`HIS-2` signale), puis `theo-archi` | Routee |
| Ligne 2 - `SCI-CM1-20` (extraire une information, texte/tableau) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| Ligne 3 - `SCI-CM1-09` (fonctions de nutrition) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| Ligne 4 - `EMC-CM1-04` (regles de la vie collective a l'ecole) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| Ligne 5 - `EMC-CM1-08` (distinguer une regle d'une loi) | `camille-po`, puis `nadia-prof` si question pedagogique (frontiere regle/loi signalee), puis `theo-archi` | Routee |
| Ligne 6 - `EMC-CP-09` (la devise de la Republique) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| `MAT-CE1-18` (sens de la division) | aucune | Non engage, dette connue depuis s36, signalee un cinquieme sprint consecutif (`retro.md` s38 et s39) sans devenir un motif de ce planning |

Aucun element n'est "non engage, budget libere" au sens de `PAR-16` : il n'y a pas de budget a
liberer hors d'une table de composition, qui n'existe pas a ce sprint. Chaque element porte soit
une affectation nominative de routage, soit un motif explicite de non-action.

## 8. Ce que ce planning ne fait pas

- Il n'engage aucune ligne de code, de test ni de contenu : les six lignes restent bloquees tant
  que `camille-po` ne les a pas instruites et que `theo-archi` n'a pas rendu son avis de
  faisabilite, avec l'arbitrage prealable de `nadia-prof` la ou il est du.
- Il n'ecrit ni ne cree aucun `DEC` : l'objectif de ce sprint reste sous forme 3 tant que
  `camille-po` ne l'a pas formalise.
- Il ne tranche aucune question pedagogique : ce travail reste a `nadia-prof`, une fois saisie par
  `camille-po`.
- Il ne rouvre ni CM2, ni aucune matiere CE1 autre que les mathematiques (`DEC-0089`), ni les neuf
  lignes bloquees par `ART-3`/`DEC-0075`, ni les deux lignes "les plus dangereuses" ecartees en
  section 2, ni `EMC-CM1-03`/`EMC-CP-04`, ni `MAT-CE1-18`, ni les deux lignes `en cours` de s39
  (`MAT-CP-01`, `MAT-CE1-09`).
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.
- Il ne garantit pas la fermeture des six lignes : chacune reste soumise a l'instruction de
  `camille-po`, a l'arbitrage pedagogique prealable la ou il est du, et a l'avis de faisabilite de
  `theo-archi`.

## Decisions prises

Aucune decision de sprint n'est prise dans ce planning. L'objectif recu de MASTER n'est pas encore
formalise en `DEC` (section 2), aucun avis de faisabilite ni arbitrage pedagogique n'a encore ete
rendu : ce planning route, il ne tranche rien.
