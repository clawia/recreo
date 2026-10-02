# Planning - s47 - tables de multiplication CE1, modes de vie autrefois et culture anglophone au
CP (instance A), gestes d'alerte et sens du vote en EMC CP/CM1 (instance B)

- **Horodatage de debut** : 2026-10-02 16:17 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s47-tables-ce1-decouverte-cp-et-emc`, dossier ouvert par ce planning. Aucun des
  documents prealables (`DEC-0104`, l'avis de faisabilite de `theo-archi`, la fiche de conformite
  des tables de multiplication, l'arbitrage sur la longueur du texte support, l'arbitrage de
  `SOP-CE1-R1`) ne l'avait ouvert - `DEC-0104`, section 7, route l'ouverture du dossier de sprint a
  `sofia-sm`.

## 1. Lignee verifiee avant toute ecriture

Pas de worktree dedie : ce planning est un document unique, ecrit directement sur `round-two`.
Verifie avant d'ecrire : `git rev-parse --abbrev-ref HEAD` rend `round-two` ; `git status --short`
rend vide (arbre propre) ; `git log -1 --format=%H` rend `bac3b58e1451280cf9c988739af6aeae91a6d8c6`,
qui porte la fusion de la correction de `S47-MAT-TABLES` par `camille-po` (`9f8fbc92`, fusionnee
en `bac3b58e`) et l'amendement de `TAB-CE1-6` par `nadia-prof` (`a29c85b4`). Ce planning ne touche
aucun fichier hors de
`squad/sprints/s47-tables-ce1-decouverte-cp-et-emc/planning.md`.

## 2. Objectif gele, et sa provenance

**Objectif** (`DEC-0104`, Acceptee, engagee le 2026-10-02) : fermer cinq lignes sur un dispositif a
**deux instances paralleles de `nadia-prof`** - `MAT-CE1-07`, `QLM-CP-15` et `LVE-CP-06` (instance
A), `EMC-CP-14` et `EMC-CM1-10` (instance B) -, total engage **7,0/8,0**. Si les cinq lignes
ferment integralement, la matrice de couverture passe de 113/182 (62,1 %, apres l'integration du
referentiel francais CE1 par `DEC-0104` section 1) a **118/182 (64,8 %)**.

**Ce que ce planning ne rouvre pas** : `DEC-0104` elle-meme, ses tarifs, sa composition par
instance, ni l'integration du referentiel francais CE1 a la matrice (deja faite par `DEC-0104`,
section 1). Les cinq lignes sont verifiees `non couvert` dans `MATRICE-couverture.md` par lecture
directe a l'ouverture de ce planning (section 3) et resolues ligne par ligne, citation comprise, par
l'arbitrage prealable de s43 (`MAT-CE1-07`), l'arbitrage prealable de s29 (`QLM-CP-15`), l'arbitrage
prealable de s27 (`LVE-CP-06`) et les Jugements de faisabilite de `nadia-prof` dans les referentiels
EMC CP et EMC CM1 (`EMC-CP-14`, `EMC-CM1-10`).

## 3. Verification des clauses `PAR-1`, `PAR-2`, `PAR-7`, `PAR-8` et `CYC-1`, reverifiees apres le
deblocage de `MAT-CE1-07` et la correction de la story

`DEC-0104`, section 4, verifie deja ces cinq clauses sur la composition d'origine, **avant** que
`MAT-CE1-07` ne soit debloquee par l'amendement de `TAB-CE1-6` (cinquieme famille) et que
`camille-po` ne corrige la story `S47-MAT-TABLES` en consequence (commit `9f8fbc92`, fusionne en
`bac3b58e`). Je ne suppose pas que ces deux evenements ne changent rien : je reverifie les cinq
clauses contre l'etat du depot au moment ou j'ouvre ce planning.

- **`PAR-1` (aucun lot partage entre deux instances).** L'amendement de `TAB-CE1-6` et la correction
  de `S47-MAT-TABLES` changent le contenu d'une condition sur un lot existant de l'instance A ;
  aucun des deux ne deplace ce lot vers l'instance B ni n'en ajoute un sixieme. Cinq lots, cinq
  instances uniques assignees (trois pour A, deux pour B, verifie section 2). **Tenue.**
- **`PAR-2` (aucune matiere ni niveau partages entre A et B).** Instance A : mathematiques CE1,
  questionner le monde CP, langues vivantes (anglais) CP. Instance B : enseignement moral et
  civique, CP et CM1 confondus sous une seule matiere (`DEC-0098` section 4.1, repris par
  `DEC-0104` section 4.2). L'amendement de `TAB-CE1-6` ne touche a aucune matiere ni aucun niveau :
  `MAT-CE1-07` reste mathematiques CE1. Quatre matieres distinctes au total, aucune partagee entre
  les deux instances. **Tenue.**
- **`PAR-7` (aucun terrain vierge donne a une instance qui n'en porte pas ailleurs).** Comptes
  d'accumulation reverifies par lecture directe de `MATRICE-couverture.md`, et non repris de
  confiance depuis `DEC-0104` : mathematiques CE1 porte **7** lignes `couvert-valide` (un faux
  positif de 8 obtenu par une recherche naive de sous-chaine est ecarte : la ligne `en cours` sur
  les longueurs contient elle-meme la sous-chaine "couv" dans sa propre mention, pas le statut
  `couvert-valide`) ; questionner le monde CP en porte **11** ; langues vivantes (anglais) en porte
  **2**, toutes deux au CM1 (`LVE-CM1-04`, `LVE-CM1-07`) - le CP n'en porte aucune, verifie ligne
  par ligne (cinq lignes `non couvrable en l'etat`, une `non couvert` : celle de ce sprint) ;
  enseignement moral et civique (CP et CM1 confondus) en porte **17**. Aucun de ces quatre comptes
  ne bouge tant qu'aucune des cinq lignes de s47 n'est fermee, et l'amendement de `TAB-CE1-6` ne
  touche a aucune ligne deja fermee. **Aucun terrain vierge a affecter. Tenue, sans dicter
  d'affectation particuliere, reverifie et non suppose.**
- **`PAR-8` (plafond individuel 5,0, au plus trois lots par instance).** Les tarifs (1,0 pour
  `MAT-CE1-07`, 1,5 pour chacune des quatre autres lignes) sont fixes par matiere
  (`DEC-0074`/`DEC-0077`, section 8.2) et ne dependent pas du texte d'une condition pedagogique :
  l'amendement de `TAB-CE1-6` ne les change pas. Instance A : trois lots, 4,0/5,0. Instance B :
  deux lots, 3,0/5,0. **Tenue.**
- **`CYC-1` (continuite cycle 2 / cycle 3).** Due et nommee sur `EMC-CM1-10` ; non due sur
  `MAT-CE1-07`, `QLM-CP-15`, `LVE-CP-06` ni `EMC-CP-14` (lignes de CP, ou de CE1 sans miroir de
  cycle 2 inferieur). Verifie par lecture directe de la ligne miroir au CP (*"participer a une
  decision collective par un vote"*, `MATRICE-couverture.md`, ligne "Construire une culture
  civique", CP) : **`non couvrable en l'etat`**, aucun paquet ne la sert - confirme, pas suppose.
  Aucune lecture croisee n'est due. L'amendement de `TAB-CE1-6` ne touche a aucune ligne d'EMC.
  **Tenue, inchangee.**

**Conclusion** : le deblocage de `MAT-CE1-07` et la correction de sa story precisent ce qu'un
paquet doit tenir pour satisfaire une condition deja posee ; ni l'un ni l'autre ne deplacent un lot,
une matiere, un niveau, un tarif ou une obligation de lecture croisee. Les cinq clauses tiennent
toujours, reverifiees contre l'etat du depot au moment de l'ouverture de ce planning et non
supposees inchangees.

## 4. Statut de `MAT-CE1-07` - desormais debloquee, engagee avec `TAB-CE1-6` amendee (cinq
familles)

L'avis de faisabilite de `theo-archi` (`squad/rapports/avis-faisabilite-s47-cinq-lots.md`, section
1, Q1) avait rendu `S47-MAT-TABLES` **infaisable en l'etat des conditions** : le fait `10 × 1`, que
`TAB-CE1-1` exige, n'admettait qu'un seul distracteur dans les quatre familles de `TAB-CE1-6`, qui
en exigeait deux, rendant la bonne reponse predictible sur cet item et
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` rouge par construction sur ce fait.

`nadia-prof` a leve ce blocage le 2026-10-02
(`squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md`, section 3, amendement de
`TAB-CE1-6`) : **une cinquieme famille est
admise, (e) la confusion de `× 1` avec `+ 1`** (valeur `a + 1`, uniquement si `b = 1`, quel que soit
l'ordre d'ecriture), avec sa portee (recalcul sur l'une ou l'autre lecture quand les deux facteurs
sont dans {2, 3, 4, 5, 10}) et la verification que la famille reste tenable sur les 40 faits
distincts de `TAB-CE1-1` (section 6 de la fiche conclut en toutes lettres : *"Q1 est tranchee :
`MAT-CE1-07` peut etre engagee"*). `nadia-prof` corrige dans le meme geste deux omissions sur
`TAB-CE1-1` (40 faits distincts, pas 50 couples) et sur `TAB-CE1-3` (unite de compte : l'item du
paquet, pas la manche d'une partie - voir section 5). `camille-po` a repercute ces corrections dans
la story `S47-MAT-TABLES` du backlog (commit `9f8fbc92`, deja fusionne sur `round-two` avant
l'ouverture de ce planning) : les criteres d'acceptation citent desormais les 40 faits, les cinq
familles de distracteurs et les deux lectures possibles.

**`MAT-CE1-07` est donc engagee dans ce sprint, avec sa condition `TAB-CE1-6` dans sa version
amendee a cinq familles, et `TAB-CE1-1`/`TAB-CE1-3` dans leur version corrigee du 2026-10-02.**

## 5. Questions non bloquantes de `theo-archi`, deja tranchees par `nadia-prof` dans le meme geste
(Q2, Q3), et celle qui reste ouverte (Q4, `EMC-CP-14`)

L'avis de faisabilite de `theo-archi` posait sept questions (section 9). Trois d'entre elles
portaient sur `MAT-CE1-07` et ont ete tranchees par `nadia-prof` dans le meme document qui a leve
Q1 :

- **Q2** (*"`TAB-CE1-3` se lit-elle sur les items du paquet ou sur chaque partie ?"*) - **tranchee** :
  l'unite de compte est l'item du paquet (au plus 20 % des items a facteur 1), pas la manche d'une
  partie. Aucune source de contenu contrainte, aucun `ADR` n'est du (fiche de conformite, section 4).
- **Q3** (*"le tiers de `TAB-CE1-4` se compte-t-il sur les items aux deux facteurs differents et
  sans facteur 1, ou sur tout le paquet ?"*) - **tranchee** : sur les items aux deux facteurs
  differents et sans facteur 1 (fiche de conformite, section 5).

**Ces deux reponses sont deja integrees aux conditions que le developpeur affecte au lot `MAT` doit
lire** (`squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md`, sections 4 et 5, et la
story `S47-MAT-TABLES` du backlog qui les cite).

**Q4**, posee sur `EMC-CP-14` (*"le 112 peut-il apparaitre en distracteur ou en bonne reponse a
cote de 15, 17, 18 ?"*), **reste ouverte** : aucune fiche de conformite n'existe encore pour ce lot
(verifie : `squad/conformite/` ne porte aucun fichier sur `EMC-CP-14` ni sur ce sprint a l'ouverture
de ce planning, aucun paquet n'etant encore ecrit), et `nadia-prof` ne l'a pas tranchee. **Je la
signale explicitement, avec les deux pieges de fabrication qui l'accompagnent, au developpeur
affecte a ce lot plutot que de la laisser decouvrir a l'ecriture** (section 8, tableau des pieges).

## 6. `SOP-CE1-R1` (s46) - hors perimetre de s47, routee separement

`SOP-CE1-R1` (`docs/backlog/backlog.md`, section "Backlog - sprints suivants") est la reserve
ouverte par `nadia-prof` sur sa propre condition `SOP-CE1-5` du lot `MAT-CE1-05` de **s46**, deja
clos : sur un item sans emprunt, la somme est le seul distracteur disponible, et elle depasse
toujours le premier terme, si bien qu'un enfant qui sait qu'enlever donne moins peut l'ecarter sans
poser l'operation. `nadia-prof` l'a instruite elle-meme le 2026-10-02, **en dehors du chiffrage de
s47** : elle admet une cinquieme forme de distracteur sur `SOP-CE1-5` (le decalage des colonnes),
la rend obligatoire sur les items sans emprunt, et garde la reserve **ouverte** jusqu'a ce qu'elle
rejoue un paquet repris (`squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md`,
section "Arbitrage de `SOP-CE1-R1` du 2026-10-02").

**Ce que cela signifie pour s47** : l'arbitrage pedagogique est fait, mais **la reprise du paquet
`ce1-soustractions-posees` elle-meme n'est pas engagee dans s47** - aucune des cinq lignes chiffrees
de ce sprint (section 2) n'est `MAT-CE1-05`, et aucun tarif ne lui est alloue ici. C'est une
**candidate pour un sprint futur**, nommee comme telle et non engagee : la story de reprise que
`nadia-prof` demande (remplacer les deux items sans emprunt, reprendre le test du paquet pour la
cinquieme forme) reste a chiffrer par `camille-po` et a affecter par `sofia-sm` a l'ouverture de ce
sprint futur, pas par ce planning.

## 7. Stories deja instruites - chaine complete, rien n'est bloquant pour s47

Les cinq stories sont ecrites avec criteres d'acceptation dans `docs/backlog/backlog.md`, section
"Sprint 47". La chaine complete, deja fusionnee sur `round-two` avant ce planning :

| Ordre | Fichier | Ce qu'il leve |
|---|---|---|
| 1 | `DEC-0104` | integration du referentiel francais CE1 a la matrice ; strategie, perimetre, tarif et composition des cinq lignes de s47, deux instances |
| 2 | Avis de faisabilite `theo-archi` (`squad/rapports/avis-faisabilite-s47-cinq-lots.md`) | verdicts des cinq lots ; Q1 bloquante sur `MAT-CE1-07`, Q2/Q3 non bloquantes sur `MAT-CE1-07`, Q4 non bloquante sur `EMC-CP-14`, Q5 non bloquante sur `LVE-CP-06`, Q6 non bloquante sur `EMC-CM1-10` |
| 3 | `squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md` (`nadia-prof`) | leve Q1 : cinquieme famille de `TAB-CE1-6` ; tranche Q2 (unite de compte de `TAB-CE1-3`) et Q3 (population du tiers de `TAB-CE1-4`) ; corrige `TAB-CE1-1` (40 faits) |
| 4 | Correction `camille-po` dans le backlog (commit `9f8fbc92`) | `S47-MAT-TABLES` corrigee : 40 faits, cinq familles, deux lectures possibles |
| 5 | `squad/conformite/ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md` (`nadia-prof`) | pose `LON-CE1-3`/`LON-CE1-4` - contexte du francais CE1, aucune ligne de francais n'est engagee dans s47 (sans effet sur les cinq lots chiffres) |
| 6 | Arbitrage de `SOP-CE1-R1` (`nadia-prof`, dans la fiche de conformite des soustractions posees) | leve une reserve de s46, hors chiffrage de s47 (section 6 ci-dessus) |

**Consequence pour ce planning : les cinq lots n'ont plus aucun prealable bloquant.** Q1 est
tranchee, Q2 et Q3 sont integrees aux conditions lues par le developpeur, et les questions restantes
(Q4, Q5, Q6) sont non bloquantes - signalees au developpeur affecte pour chaque lot concerne
(section 8).

| Lot | Ligne | Instance | Tarif | Verdict `theo-archi` | Source |
|---|---|---|---|---|---|
| `S47-MAT-TABLES` | `MAT-CE1-07` | A | 1,0 | **Infaisable en l'etat, puis faisable** apres l'amendement de `nadia-prof` qui leve Q1 (section 4) | avis, section 1 et 3 ; fiche de conformite |
| `S47-QLM-AUTREFOIS` | `QLM-CP-15` | A | 1,5 | **Faisable en l'etat**, zero code de production | avis, section 1 et 4 |
| `S47-LVE-CULTURE-CP` | `LVE-CP-06` | A | 1,5 | **Faisable en l'etat**, zero code de production, question Q5 non bloquante | avis, section 1 et 5 |
| `S47-EMC-ALERTE-CP` | `EMC-CP-14` | B | 1,5 | **Faisable en l'etat**, zero code de production, deux pieges de fabrication, question Q4 non bloquante | avis, section 1 et 6 |
| `S47-EMC-VOTE-CM1` | `EMC-CM1-10` | B | 1,5 | **Faisable en l'etat**, zero code de production, question Q6 non bloquante | avis, section 1 et 7 |

Porte technique : aucune mesure `mvn verify` n'est due a ce planning sur ces cinq lots, aucun code
de production n'a encore ete touche par aucun d'eux. L'avis de faisabilite rapporte sa propre porte
(section 10) sur un perimetre de tests cibles, machine virtuelle 21.0.10 ; `mvn -B clean verify`
complet non lance a cette etape, aucun diff de code n'etant encore ouvert.

## 8. Pieges de fabrication a transmettre au developpeur affecte

### `S47-MAT-TABLES` (`MAT-CE1-07`)

- **`TAB-CE1-1` porte sur 40 faits distincts, pas 50 couples** : `3 × 4` et `4 × 3` sont le meme
  fait, un item de l'un couvre l'autre.
- **`TAB-CE1-6` compte desormais cinq familles de distracteurs, et seulement celles-la** : produit
  voisin par defaut, produit voisin par exces, somme des facteurs (sous condition), produit de la
  table voisine, et la cinquieme, **(e) confusion de `× 1` avec `+ 1`** (valeur `a + 1`,
  **uniquement si `b = 1`**). Quand les deux facteurs d'un fait sont dans {2, 3, 4, 5, 10}, les
  familles (a) a (d) se calculent sur l'une ou l'autre lecture du couple ; le marqueur en
  commentaire nomme la famille, le test la recalcule.
- **`TAB-CE1-3` se lit sur l'item du paquet** (au plus 20 % des items a facteur 1, plancher reel de
  cinq items imposes par `TAB-CE1-1`), **pas sur la manche d'une partie** (Q2, tranchee).
- **`TAB-CE1-4` se compte sur les items aux deux facteurs differents et sans facteur 1** (Q3,
  tranchee).
- `SousPartieDeMathematiquesCe1` doit declarer une entree neuve pour `MAT-CE1-07`
  (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`), libelle sans point final :
  `"Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et 4"`. Le titre de l'entree
  ne peut pas nommer le niveau (`AucunTitreDeJeuNePorteSonNiveauTest`) ; le choix d'un titre distinct
  de celui du CM1 (`"Les tables de multiplication"`) reste a `camille-po`.
- Tout paquet dont aucun item n'a trois propositions numeriques entre dans
  `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
  (`ADR-0051`) - ne s'applique pas a ce lot (tous les items ont trois propositions numeriques).

### `S47-QLM-AUTREFOIS` (`QLM-CP-15`)

- `BIA-3` : le critere est le mot entier, pas la sous-chaine - `dur` est dans `duree`, `dure` est a
  la fois l'adjectif et le verbe *durer*. Ne pas filtrer sur une liste de sous-chaines ; laisser la
  relecture a `nadia-prof`.
- `FER-1`, `FER-2`, `FER-3` automatisables par marqueur (`# element:`, `# tache:`).
- `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (`LON-1`, `LON-2`) : quinze mots au plus,
  deux phrases au plus, la seconde etant la question.

### `S47-LVE-CULTURE-CP` (`LVE-CP-06`)

- **Question Q5, non bloquante, a transmettre** : un nom de fete anglais en bonne reponse
  contredit-il *"sans demander de lire ou de produire un mot d'anglais"* ? `nadia-prof` ne l'a pas
  encore tranchee ; a verifier avec elle avant d'ecrire un item qui s'appuierait sur cette forme.
  `AucunMotServiNeSEcarteDeSonAccentTest` ne rougit pas sur un nom propre anglais (deja prouve par
  `cm1-anglais`), donc ce n'est pas un blocage technique.
- `BIA-1` (aucune comparaison "chez nous/chez eux" en position de norme), `BIA-2` (un pays n'est
  jamais represente par un seul trait, marqueur `# pays:`).
- La mention de couverture partielle ("decouverte culturelle seule, en francais ; aucune competence
  linguistique n'est servie") est due dans la matrice avant que la ligne passe a `couvert-valide`.

### `S47-EMC-ALERTE-CP` (`EMC-CP-14`)

- **Piege 1, a transmettre explicitement** : un item dont les trois propositions sont des numeros
  nus (`"15"`, `"17"`, `"18"`) est lu par le garde-fou de rang comme une population numerique : le
  rang de la bonne reponse serait alors fixe par la valeur du numero, et equilibrer les rangs
  revient a equilibrer les bonnes reponses. Il faut au moins trois items numeriques pour eviter
  qu'un des deux seuils de rang soit rouge par construction ; ecrire `"le 18"` sort l'item du
  domaine numerique mais ne resout rien sur le fond (le garde-fou devient muet sur une strategie qui
  existe). Le paquet melangera vraisemblablement items textuels et numeriques, donc il entrera dans
  `PAQUETS_A_ITEMS_ECARTES` de `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` avec le nombre
  exact d'items ecartes, a documenter dans le test.
- **Piege 2, a transmettre explicitement** : trois items ou plus qui portent la meme bonne reponse
  d'au moins trois mots, avec des propositions qui se correspondent une a une, font rougir
  `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (clause 1) - *"prevenir un adulte"* sur
  plusieurs situations est exactement cette forme. Varier la formulation ou les distracteurs.
- **Question Q4, non bloquante, a transmettre** : le 112 est valable dans toute situation d'urgence
  - peut-il apparaitre en distracteur d'un item dont la bonne reponse est 15, 17 ou 18, ou en bonne
  reponse a cote d'eux ? `nadia-prof` ne l'a pas tranchee ; a verifier avec elle avant d'ecrire les
  items qui porteraient un numero d'urgence autre que le 112 lui-meme.
- `EMC-7` : tout item qui porte un numero porte un marqueur `# EMC-7:` suivi d'une source non vide.
- **Adjacence, sans collision** : `cp-emc-roles-ecole-commune` (`EMC-CP-13`) sert deja *"les
  pompiers"* en bonne reponse, sans numero. Un item ne sert qu'une ligne (`ADR-0081`, clause 5),
  rien n'est compte deux fois.

### `S47-EMC-VOTE-CM1` (`EMC-CM1-10`)

- **Question Q6, non bloquante, a transmettre** : `cm1-emc-institutions` (`EMC-CM1-09`, fermee a
  s46) porte deux items dont la bonne reponse est *"le conseil municipal"* a la question *"Qui a
  voté cette décision ?"*. Un item de `EMC-CM1-10` sur l'election du conseil municipal porterait sur
  le meme objet. Rien n'est compte deux fois (`ADR-0081`), mais la frontiere entre les deux lignes
  n'est pas ecrite : a verifier avec `nadia-prof` avant d'ecrire un item sur ce point precis.
- `EMC-8` : aucun enonce ne s'adresse a l'enfant a la deuxieme personne pour lui demander ce qu'il
  ferait ou voterait.
- **`CYC-1` non due, verifie section 3** : l'objet miroir au CP (*"participer a une decision
  collective par un vote"*) est `non couvrable en l'etat`, aucune lecture croisee n'est due.

### Pieges communs aux cinq lots

- `TexteAccentueExposeParLePortEntrantTest` : toute chaine neuve (titre, consigne, competence,
  enonce) doit y passer.
- `AucunTitreDeJeuNePorteSonNiveauTest` : aucun titre ne porte `CP`, `CE1` ni `CM1`.
- `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`, `FER-2` (au moins 11 items pour une
  entree a 10 manches).
- **Aucune mecanique neuve, aucune `ArchRule` neuve n'est due** (avis, section 8 : 36
  `static final ArchRule`, 35 `@ArchTest`, compte inchange depuis s46). Le test de chaque paquet est
  la seule garde automatique des conditions listees ci-dessus.

## 9. Affectations nominatives

`docs/backlog/backlog.md` (section "Sprint 47") ne propose pas de repartition nominative ;
affectation finale par `sofia-sm` (`DEC-0104`, section 7, qui route l'ouverture du sprint a
`sofia-sm`). Decision de capacite : cinq lots, trois developpeurs. `S47-MAT-TABLES` est le seul des
cinq a porter une complexite de test au-dela du patron deja connu (cinq familles de distracteur,
deux lectures possibles d'un meme fait, 40 faits distincts) ; les quatre autres sont des paquets de
choix textuel sans code de production attendu, sur le patron deja ferme de s45/s46. Je retiens
`marc-dev` seul sur le lot de complexite moderee, et je repartis les quatre lots de basse complexite
entre `lea-dev` et `sofia-sm` (casquette dev), un de chaque instance pour chacun - meme raisonnement
qu'a s46 (section 8 de son planning) : `PAR-2` gouverne le partage de matiere/niveau entre les deux
instances de `nadia-prof`, pas la charge d'un developpeur, et rien n'empeche un meme developpeur de
coder un lot de chaque instance.

| Lot | Ligne | Instance | Developpeur | Peut demarrer l'ecriture de code des maintenant ? |
|---|---|---|---|---|
| `S47-MAT-TABLES` | `MAT-CE1-07` | A | `marc-dev` | **Oui** |
| `S47-QLM-AUTREFOIS` | `QLM-CP-15` | A | `lea-dev` | **Oui** |
| `S47-EMC-ALERTE-CP` | `EMC-CP-14` | B | `lea-dev` | **Oui, sous reserve de verifier Q4 avec `nadia-prof` avant d'ecrire un item qui porterait un numero d'urgence autre que 112** |
| `S47-LVE-CULTURE-CP` | `LVE-CP-06` | A | `sofia-sm` (casquette dev) | **Oui, sous reserve de verifier Q5 avec `nadia-prof` avant d'ecrire un item qui s'appuierait sur un nom de fete anglais en bonne reponse** |
| `S47-EMC-VOTE-CM1` | `EMC-CM1-10` | B | `sofia-sm` (casquette dev) | **Oui, sous reserve de verifier Q6 avec `nadia-prof` avant d'ecrire un item sur l'election du conseil municipal** |

**Pourquoi ce regroupement.** `marc-dev` prend seul `MAT-CE1-07`, le seul lot dont le test de paquet
porte une logique neuve (cinq familles de distracteur, double lecture). `lea-dev` et `sofia-sm`
prennent chacun un lot d'instance A et un lot d'instance B, repartition symetrique qui ne cree
aucune collision : les deux Done pedagogiques de chaque developpeur restent portes par deux
instances differentes de `nadia-prof`, qui n'auditent chacune que leurs propres lots. Charge
chiffree : `marc-dev` 1,0 ; `lea-dev` 1,5 + 1,5 = 3,0 ; `sofia-sm` 1,5 + 1,5 = 3,0.

## 10. Deux instances de `nadia-prof` - Done pedagogique en fin de sprint, chacune sur son
perimetre seul

**Instance A** audite `S47-MAT-TABLES`, `S47-QLM-AUTREFOIS` et `S47-LVE-CULTURE-CP`. Budget
individuel 5,0, cout retenu 4,0 (1,0 + 1,5 + 1,5), marge 1,0. Elle porte, avant d'auditer, les
conditions qu'elle a elle-meme amendees ou corrigees sur `MAT-CE1-07` (cinquieme famille de
`TAB-CE1-6`, correction de `TAB-CE1-1` et `TAB-CE1-3`) et les deux questions non bloquantes
restantes (Q5 sur `LVE-CP-06`).

**Instance B** audite `S47-EMC-ALERTE-CP` et `S47-EMC-VOTE-CM1`. Budget individuel 5,0, cout retenu
3,0 (1,5 + 1,5), marge 2,0. Elle porte, avant d'auditer, la question Q4 (numeros d'urgence,
`EMC-CP-14`) et la question Q6 (frontiere avec `EMC-CM1-09`, `EMC-CM1-10`), et l'obligation `CYC-1`
verifiee non due sur les deux lots (section 3). **Instance A n'audite jamais un lot de l'instance
B, et reciproquement** - regle de `DEC-0098` pour tout dispositif a deux instances, qu'aucun
document de s47 ne touche.

Budget de sprint opposable 8,0, cout retenu 7,0, marge de sprint 1,0 non allouee des l'ouverture
(`DEC-0104`, section 4 : le plafond mecanique de 8,0 n'est pas atteignable dans le vivier instruit
sans rouvrir la regle "une seule ligne de mathematiques CE1 par sprint", reconduite une cinquieme
fois, ni depasser le plafond individuel de 5,0 par instance).

## 11. Verification `PAR-16` - point de controle poste/story

| Element | Affectation nominative | Issue |
|---|---|---|
| `S47-MAT-TABLES` (`MAT-CE1-07`) | `marc-dev`, puis `nadia-prof`, instance A | Engagee |
| `S47-QLM-AUTREFOIS` (`QLM-CP-15`) | `lea-dev`, puis `nadia-prof`, instance A | Engagee |
| `S47-LVE-CULTURE-CP` (`LVE-CP-06`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance A | Engagee |
| `S47-EMC-ALERTE-CP` (`EMC-CP-14`) | `lea-dev`, puis `nadia-prof`, instance B | Engagee |
| `S47-EMC-VOTE-CM1` (`EMC-CM1-10`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance B | Engagee |

Aucun element n'est "non engage, budget libere" : les cinq lots de `DEC-0104` sont tous affectes.
La marge de sprint de 1,0 (section 10) n'est engagee sur aucun lot nouveau par ce planning.
`SOP-CE1-R1` (section 6) n'est pas un lot de ce tableau : elle est routee a `nadia-prof`
separement, sans tarif ni affectation de developpement dans ce sprint.

## 12. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : aucune mecanique neuve n'est due sur ces
  cinq lots (avis de faisabilite, section 8).
- Il ne corrige aucun texte de story : la correction due (`S47-MAT-TABLES`) est deja fusionnee par
  `camille-po` avant ce planning (commit `9f8fbc92`).
- Il ne pose aucun discriminant pedagogique neuf : l'amendement de `TAB-CE1-6`, la correction de
  `TAB-CE1-1`/`TAB-CE1-3` et l'arbitrage de `LON-CE1-3`/`LON-CE1-4` sont deja ecrits par
  `nadia-prof` (sections 4 et 7).
- Il ne rouvre pas `DEC-0104`.
- Il ne tranche pas Q4, Q5 ni Q6 : elles restent a `nadia-prof`, signalees aux developpeurs affectes
  (section 8) plutot que decidees ici.
- Il n'engage ni la reprise du paquet `ce1-soustractions-posees` (`SOP-CE1-R1`, section 6), ni
  aucune ligne de francais CE1 (deja non engagees par `DEC-0104` section 1.2) : candidates pour un
  sprint futur, non engagees ici.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.

## Decisions prises

Aucune `DEC` ni `ADR` n'est creee ou amendee par ce planning. Une decision m'appartient en propre et
est ecrite ici : la decision de capacite de la section 9 (un developpeur seul sur le lot de
complexite moderee, les deux autres developpeurs repartis a raison d'un lot par instance chacun) et
l'affectation nominative qui en decoule, ainsi que le routage de `SOP-CE1-R1` a `nadia-prof`
separement du chiffrage de ce sprint (section 6). `DEC-0104`, l'avis de `theo-archi`, la fiche de
conformite des tables de multiplication, l'arbitrage sur la longueur du texte support et
l'arbitrage de `SOP-CE1-R1` existent deja avant ce planning et ne sont pas amendes par lui.
