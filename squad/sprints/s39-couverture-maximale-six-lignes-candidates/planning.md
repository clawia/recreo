# Planning - s39 - couverture maximale sur six lignes candidates

- **Horodatage de debut** : 2026-09-22 (aucune heure locale fiable transmise avec la consigne de
  ce planning - je ne fabrique pas de precision que je n'ai pas).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s39-couverture-maximale-six-lignes-candidates`, choisi par `sofia-sm` a l'ouverture -
  aucun agent n'avait encore prepare de worktree pour ce sprint.
- **Prealable, en vigueur et non rouvert par ce planning** : `DEC-0089` (Acceptee, `DEC-index.md`)
  - perimetre CE1 ouvert, mathematiques seule, CM2 differe explicitement. Ce planning ne rouvre
    aucune matiere ni aucun niveau hors de ce perimetre : verifie ligne par ligne en section 3.

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`git merge-base --is-ancestor round-two HEAD`) : `s39-sofia` (`../recreo-s39-sofia`) descend de
`round-two` a `542c1258` (dernier commit de cloture de s38), ouvert depuis ce commit sans aucun
autre ecart.

## 2. Objectif gele, et sa provenance

**Forme d'attribution (`squad/README.md`, section 2 bis)** : cet objectif ne vient d'aucun des
neuf agents. Il est porte sous la **forme 3**, cite mot pour mot, et il **ne vaut pas motif** tant
qu'il n'est pas ecrit ailleurs par l'agent dont c'est le role - meme mecanisme que `DEC-0089` et
`DEC-0091`.

> **Recu de MASTER, sans source dans le depot, le 2026-09-22** : *"camille-po a produit une
> analyse de candidats a couverture maximale (transmise hors depot, forme 3), identifiant six
> lignes fermables chacune par un seul paquet, une mecanique deja eprouvee sur ce contenu exact,
> sans arbitrage pedagogique attendu"* - suivi de la liste des six lignes (section 3 ci-dessous) et
> de : *"Objectif a geler : maximiser la couverture du sprint s39 en fermant, autant que possible
> parmi les six lignes ci-dessus, en respectant le perimetre en vigueur (DEC-0089 : CE1
> mathematiques seule, CM2 differe - inchange, aucune des six lignes ne le rouvre)."*

> **Recu de MASTER, sans source dans le depot, le 2026-09-22, sur la confirmation utilisatrice** :
> *"L'utilisatrice a confirme le principe 'maximum de couverture' et ce lot precis de six lignes le
> 2026-09-22, par la reponse 'ok' a la proposition qui lui a ete faite - un mot, pas une citation
> detaillee des six lignes elle-meme."*

**Ce que je retiens de cette seconde citation, et ce que je ne fabrique pas.** Le seul mot que je
possede entre guillemets, venant reellement de l'utilisatrice, est *"ok"*. Je ne sais pas, a partir
de ce que j'ai recu, quelle etait la formulation exacte de la proposition a laquelle elle a repondu
- je ne l'invente pas pour completer une citation forme 2 que je n'ai pas. **Ce que je peux dire
sans fabriquer** : le principe "maximum de couverture" et ce lot precis de six lignes m'arrivent
comme confirmes par l'utilisatrice le 2026-09-22, mais **elle n'a pas revu ligne par ligne** les
six candidats - la meme rigueur que `camille-po` a appliquee a `DEC-0091` (section 3) sur un
enonce recu par le meme canal. Je route donc a `camille-po`, dans le meme geste que la section 4,
la formalisation de cet objectif en `DEC` (creation), avec exactement cette meme reserve ecrite -
pas plus, pas moins que ce que je possede.

**Consequence directe pour ce planning** : je gele l'intention operationnelle qui suit comme
**perimetre de travail du sprint**, sous la meme reserve que `DEC-0091` posait pour s38 : tant que
le `DEC` qui la formalise n'existe pas physiquement, aucun lot de ce sprint n'est livrable ; il
reste instruisible et routable, ce que ce planning fait.

**Intention operationnelle gelee, sous cette reserve** : maximiser la couverture du programme en
fermant, autant que possible, les six lignes de la section 3, dans le respect de `DEC-0089`.

## 3. Les six lignes candidates - verification physique avant tout gel

**Ce que je verifie moi-meme plutot que de recopier l'analyse recue** (consigne explicite de ce
planning) : chaque ligne est bien `non couvert` dans `squad/programme/MATRICE-couverture.md`
aujourd'hui, et chaque ligne est examinee individuellement pour un obstacle deja connu du depot -
je ne presume pas que l'absence d'arbitrage pedagogique annoncee par l'analyse est vraie pour
toutes les six.

| # | Ligne | Matiere / niveau | Etat verifie dans `MATRICE-couverture.md` |
|---|---|---|---|
| 1 | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | mathematiques CP | ligne 154, `non couvert` |
| 2 | Mesurer et comparer des longueurs avec une regle graduee ; utiliser le centimetre et le metre | mathematiques CE1 | ligne 590, `non couvert` |
| 3 | Utiliser la monnaie : composer une somme en euros et en centimes, savoir ce qui est rendu | mathematiques CE1 | ligne 594, `non couvert` |
| 4 | Reconnaitre et nommer des solides usuels (cube, pave droit, boule, cylindre, cone) | mathematiques CE1 | ligne 597, `non couvert` |
| 5 | Identifier des sources et des formes d'energie, et un exemple de conversion d'une forme a une autre | sciences et technologie CM1 | ligne 1974, `non couvert - jouable au choix textuel. Distracteurs naturels et plausibles, ce qui est rare.` |
| 6 | Nommer quelques outils, supports et gestes du travail plastique (pinceau, craie, argile, esquisse) | arts plastiques CP | ligne 1629, `non couvert - jouable au choix textuel. Vocabulaire concret, court, du niveau du CP, exact et verifiable.` |

**Les six sont bien `non couvert`.** Aucune des six ne rouvre `DEC-0089` : les lignes 2, 3 et 4
sont mathematiques CE1 (perimetre deja ouvert) ; la ligne 1 est mathematiques CP (niveau ouvert
depuis `DEC-0001`, s0) ; la ligne 5 est sciences et technologie CM1 (matiere deja ouverte,
plusieurs lignes deja `couvert-valide` depuis s30/s31) ; la ligne 6 est arts plastiques CP (matiere
deja ouverte). Aucune n'entre dans le perimetre des neuf lignes bloquees par `ART-3`/`DEC-0075`
(reproductions d'oeuvres, tranchees en option B par `DEC-0077`) : ces neuf-la portent sur
"decrire"/"reconnaitre une oeuvre", pas sur le vocabulaire des outils et gestes.

### 3.1 Obstacle trouve sur la ligne 1, qui contredit la premisse de l'analyse recue - a river avant tout engagement

**La ligne 1 (Denombrer jusqu'a 100, maths CP) n'est PAS une ligne "sans arbitrage pedagogique
attendu".** Un arbitrage existe deja, il est explicite, date, et **non negociable au dire de son
autrice** : `DEN-2` (`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-
s13.md`, ligne 136 ; reconfirme le 2026-08-30,
`squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-
satisfaisabilite.md`, ligne 192 : *"`DEN-2` est de moi, je la maintiens, et elle est non
negociable"*). Sa teneur, verifiee dans `squad/programme/INSTRUCTION-s25-les-16-lignes-hors-
couvert-valide.md`, ligne 57 : un paquet de bandit manchot sur cette ligne **fait passer le statut
`non couvert` -> `en cours`, jamais `couvert-valide`**, parce que le verbe *denombrer* exige une
collection **dessinee, groupee par dix, sur laquelle l'enfant agit** - un mecanisme visuel avec
interaction qui n'existe pas dans le depot aujourd'hui. La mecanique "deja eprouvee" citee par
l'analyse (bandit manchot / choix textuel) est precisement celle que `DEN-2` exclut pour cette
ligne.

**Ce que ceci change pour ce planning** : je ne gele pas la ligne 1 comme fermable sans arbitrage.
Je la route a `nadia-prof` avant tout avis de faisabilite de `theo-archi`, avec `DEN-2` cite en
entier, pour une seule question - `DEN-2` tient-elle encore, ou une forme neuve (par exemple un
palier visuel avec interaction, si un tel palier existe desormais dans le depot) permettrait-elle
de la lever ? Je ne prejuge pas la reponse. Si `DEN-2` est maintenue telle quelle, la ligne 1 sort
de fait de ce qui est fermable a s39 par la seule mecanique textuelle, et le sprint vise au mieux
cinq lignes sur six, pas six.

### 3.2 Les cinq autres lignes - aucun obstacle documente trouve, mais aucune n'est presumee exempte d'arbitrage

Recherche menee sur `squad/conformite/`, `squad/decisions/produit/`, `squad/sprints/s36-*/
verdicts-nadia.md` et le referentiel `squad/programme/mathematiques/competences-ce1.md` : aucune
clause de la famille de `DEN-2` (un verbe de competence qui exige une manipulation ou une image que
la mecanique textuelle ne peut pas servir) n'est ecrite pour les lignes 2 a 6. La seule clause
trouvee touchant le referentiel CE1 est `LON-CE1-1`/`LON-CE1-2` (`squad/conformite/ARBITRAGE-s36-
longueur-d-enonce-au-ce1.md`), une borne de longueur d'enonce (20 mots, trois phrases) qui
s'applique a tout paquet CE1 sans en bloquer la fermeture - distincte d'un blocage de ligne.

**Ceci ne vaut pas absence d'arbitrage.** Consigne explicite de ce sprint (section 4) : chacune des
cinq lignes restantes passe par le meme filtre que `camille-po` a applique aux trois sous-parties
de `MAT-CE1-03` a s38 - verifier si elle souleve une question pedagogique (bornes de tirage, forme
des distracteurs, ou toute autre question de meme famille) avant l'avis de faisabilite de
`theo-archi`, et router a `nadia-prof` si oui. Une absence d'arbitrage deja ecrit dans le depot
n'est pas une preuve qu'aucun n'est du.

## 4. Routage

**Vers `camille-po`** :

- ecrire le `DEC` qui formalise l'objectif recu de MASTER (section 2) - creation, pas amendement,
  puisqu'aucun `DEC` existant ne porte cet objectif - avec la meme reserve de sourcage que
  `DEC-0091` (section 2) : le principe et le lot de six lignes sont portes comme confirmes par
  l'utilisatrice le 2026-09-22 sur le seul mot *"ok"*, sans qu'elle les ait revus ligne par ligne ;
- instruire les six lots (un par ligne de la section 3) dans `docs/backlog/backlog.md`, avec des
  criteres d'acceptation testables, en nommant explicitement pour chacun s'il souleve une question
  pedagogique (bornes de tirage, forme des distracteurs) et en le routant a `nadia-prof` si oui -
  **la ligne 1 est routee d'office**, sur l'obstacle de la section 3.1 ;
- verifier, au moment d'instruire, qu'aucune des six lignes ne rouvre `DEC-0089` ni les neuf
  lignes bloquees par `ART-3`/`DEC-0075` - deja fait a titre de premiere passe en section 3, a
  reverifier par elle a l'instruction.

**Vers `nadia-prof`** :

- arbitrage prealable sur la ligne 1 (Denombrer jusqu'a 100, maths CP) : `DEN-2` tient-elle encore
  telle quelle, ou une voie neuve existe-t-elle pour la lever - section 3.1 ;
- arbitrage prealable sur les lignes 2 a 6, chacune, si `camille-po` y trouve une question
  pedagogique en les instruisant - section 3.2 ;
- **obstacle route a deux endroits sur un meme perimetre voisin, nomme comme tel** (amendement
  s19, `squad/README.md` section 9) : la ligne 3 (monnaie CE1, "composer une somme... savoir ce
  qui est rendu") est proche par son theme de la ligne monnaie CP deja fermee
  (`squad/programme/MATRICE-couverture.md`, ligne 160, `MON-1`/`MON-3`, item illustre palier 1) -
  `nadia-prof` est prevenue que ce precedent existe, pour eviter de le redecouvrir a zero si elle
  instruit la ligne 3.

**Vers `theo-archi`** : rendre un avis de faisabilite ecrit sur chacune des six lignes, une fois
que `camille-po` les a instruites et, pour celles qui en demandent un, une fois l'arbitrage
pedagogique prealable de `nadia-prof` rendu.

## 5. Regle de gouvernance du sprint - reconduite explicitement, pas heritee par defaut

**Verification faite avant de gouverner ce sprint** : la regle posee par `DEC-0091`/`planning.md`
de s38 ("un avis de faisabilite rendu par `theo-archi` ne vaut jamais livraison ; le developpement
est affecte nominativement par `sofia-sm`") a ete ecrite **"pour ce sprint"** (`DEC-0091`, section
2.2 ; `planning.md` de s38, section 2 : *"Point de gouvernance gele pour ce sprint"*). `retro.md`
de s38, section 3, ne l'a pas versee en amendement permanent de `squad/README.md` : les deux seuls
amendements de cette retro portent sur autre chose (relecture qui ne trouve rien, worktree dedie
refuse). **Elle n'est donc pas en vigueur par defaut a s39** ; je la reconduis ici explicitement,
pour le meme motif qui l'a fait naitre a s38 (retro de s37, section 2 : `theo-archi` a livre du
code deux fois de suite hors de son role d'avis de faisabilite, sans qu'un planning ne l'ait
decide) et parce qu'aucun evenement de s39 ne justifie de s'en affranchir.

**Regle gelee pour s39** : des qu'un avis de faisabilite de `theo-archi` est rendu sur une des six
lignes, le developpement qui en decoule est affecte nominativement (`marc-dev`, `lea-dev`, ou
`sofia-sm` sous sa casquette dev) et n'est pas livre directement par `theo-archi`.

## 6. Affectations nominatives

**Aucune affectation de developpement a ce planning.** Les six lignes de la section 3 sont toutes
bloquees avant tout engagement de code : aucune story instruite avec criteres d'acceptation
testables, aucun avis de faisabilite rendu, et pour celles qui en demandent un, aucun arbitrage
pedagogique prealable rendu - dont la ligne 1, routee d'office sur l'obstacle `DEN-2`.

Des qu'un avis de faisabilite est rendu sur une ligne (section 4), Sofia l'affecte nominativement
au daily suivant - aucun fichier n'est du a ce moment-la, l'affectation remonte dans le tableau des
obstacles de `review.md` comme le reste du suivi de sprint.

## 7. Verification `PAR-16` - point de controle poste/story

**Ce que `PAR-16` demande litteralement** (`squad/README.md`, section 4) : verifier, poste par
poste, la table de composition de la decision de strategie en vigueur. **Cette table n'existe pas
a s39**, meme constat qu'aux quatre sprints precedents (s35 a s38) : la fenetre du Plan 2
(`DEC-0083`) reste refermee par `DEC-0087` sans qu'une strategie neuve de composition budgetaire
n'ait ete tranchee au-dela.

**Ce que `PAR-16` verifie a la place, meme fallback qu'aux quatre sprints precedents** : chaque
element retenu porte-t-il une affectation nominative ou un motif explicite de non-action ?

| Element | Affectation nominative | Issue |
|---|---|---|
| Objectif du sprint (couverture maximale, six lignes) | `camille-po` | Routee - `DEC` a ecrire avant tout lot livrable (section 2) |
| Ligne 1 - Denombrer jusqu'a 100 (maths CP) | `camille-po`, puis `nadia-prof` d'office (`DEN-2`), puis `theo-archi` | Routee, obstacle connu nomme (section 3.1) |
| Ligne 2 - Longueurs, cm et m (maths CE1) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| Ligne 3 - Monnaie CE1 | `camille-po`, puis `nadia-prof` si question pedagogique (precedent CP signale), puis `theo-archi` | Routee |
| Ligne 4 - Solides usuels (maths CE1) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| Ligne 5 - Sources et formes d'energie (sciences CM1) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| Ligne 6 - Outils et gestes du travail plastique (arts CP) | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee |
| `MAT-CE1-18` (sens de la division) | aucune | Non engage, dette connue depuis s36, signalee un quatrieme sprint consecutif (`retro.md` s38, section 2) sans devenir un motif de ce planning |

Aucun element n'est "non engage, budget libere" au sens de `PAR-16` : il n'y a pas de budget a
liberer hors d'une table de composition, qui n'existe pas a ce sprint. Chaque element porte soit
une affectation nominative de routage, soit un motif explicite de non-action.

## 8. Ce que ce planning ne fait pas

- Il n'engage aucune ligne de code, de test ni de contenu : les six lignes restent bloquees tant
  que `camille-po` ne les a pas instruites et que `theo-archi` n'a pas rendu son avis de
  faisabilite, avec l'arbitrage prealable de `nadia-prof` la ou il est du.
- Il n'ecrit ni ne cree aucun `DEC` : l'objectif de ce sprint reste sous forme 3 tant que
  `camille-po` ne l'a pas formalise.
- Il ne tranche aucune question pedagogique, y compris sur la ligne 1 : `DEN-2` reste en vigueur
  telle qu'ecrite tant que `nadia-prof` ne l'a pas reexaminee.
- Il ne rouvre ni CM2, ni aucune matiere CE1 autre que les mathematiques (`DEC-0089`), ni les neuf
  lignes bloquees par `ART-3`/`DEC-0075`, ni `MAT-CE1-18`.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.
- Il ne garantit pas la fermeture des six lignes : au moins une (la ligne 1) porte un obstacle
  pedagogique deja connu qui peut la maintenir hors de portee de ce sprint quelle que soit la
  qualite du developpement.

## Decisions prises

Aucune decision de sprint n'est prise dans ce planning. L'objectif recu de MASTER n'est pas encore
formalise en `DEC` (section 2), aucun avis de faisabilite ni arbitrage pedagogique n'a encore ete
rendu, et l'obstacle trouve sur la ligne 1 (`DEN-2`, section 3.1) n'est pas tranche ici : ce
planning route, il ne tranche rien.
