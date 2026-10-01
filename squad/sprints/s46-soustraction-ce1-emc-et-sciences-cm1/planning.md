# Planning - s46 - soustraction posee CE1, emotion et institutions d'EMC CM1 (instance A) et
mouvement/phenomenes de sciences CM1 (instance B)

- **Horodatage de debut** : 2026-10-01 16:30 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s46-soustraction-ce1-emc-et-sciences-cm1`, dossier ouvert par ce planning. Aucun des
  documents prealables (`DEC-0102`, `DEC-0103`, l'avis de faisabilite de `theo-archi`, la fiche de
  conformite de `nadia-prof`, l'arbitrage sur les trois instances) ne l'avait ouvert - `DEC-0102`,
  section 8, route l'ouverture du dossier de sprint a `sofia-sm`.

## 1. Lignee du worktree

Verifie avant toute ecriture : `git rev-parse --abbrev-ref HEAD` rend `s46-sofia-planning`, puis
`bash outils/verifier-lignee.sh round-two` rend *"le worktree sur la branche 's46-sofia-planning'
descend de la lignee de round-two (ancetre commun trouve dans son historique)"*, code de sortie 0.
Ce worktree est fonde sur `round-two` a `8b0fe8b4` (dernier commit sur `round-two` au moment de
l'ouverture, fusion de la cloture de s45), sans aucun autre ecart.

## 2. Objectif gele, et sa provenance

**Objectif** (`DEC-0102`, Acceptee, engagee le 2026-10-01) : fermer cinq lignes sur un dispositif a
**deux instances paralleles de `nadia-prof`** - `MAT-CE1-05` (instance A), `EMC-CM1-01` et
`EMC-CM1-09` (instance A), `SCI-CM1-03` et `SCI-CM1-16` (instance B) -, total engage **7,0/8,0**. Si
les cinq lignes ferment integralement, la matrice de couverture passe de 108/156 (69,2 %, etat de
sortie de s45) a **113/156 (72,4 %)**.

**Ce que ce planning ne rouvre pas** : `DEC-0102` elle-meme, ses tarifs, sa composition par
instance, ni `DEC-0103` (ouverture du perimetre francais CE1, traitee section 10 comme hors
perimetre). Les cinq lignes sont deja verifiees `non couvert` dans `MATRICE-couverture.md`
(verification repetee par `camille-po` dans `DEC-0102`, section 1 et section 3, reconfirmee par
l'avis de faisabilite de `theo-archi` et par l'arbitrage de `nadia-prof` sur les trois instances) et
resolues ligne par ligne, citation comprise, par `DEC-0102`, par l'avis de faisabilite, par
l'arbitrage prealable de s43 (section 5.2) et par la fiche de conformite amendee le 2026-10-01.

## 3. Verification des clauses `PAR-1`, `PAR-2`, `PAR-7`, `PAR-8` et `CYC-1`, reverifiees apres
les deux amendements de `camille-po`

`DEC-0102`, section 5, verifie deja ces cinq clauses sur la composition d'origine. Deux amendements
sont intervenus depuis, dans le backlog (section "Sprint 46") : (1) la story `S46-MAT-SOUSTRACTION`
est corrigee pour citer `SOP-CE1-5` dans sa version a quatre formes et sa regle de rang sur le
paquet entier (amendement de `nadia-prof`, relaye dans le texte de la story par `camille-po`) ; (2)
la story `S46-EMC-INSTITUTIONS-CM1` est precisee sur l'usage de `EMC-5`, `EMC-6` et `EMC-7` (reponse
a la question Q2 de `theo-archi`). Je reverifie les cinq clauses contre ces deux textes amendes,
plutot que de supposer qu'elles n'ont pas bouge :

- **`PAR-1` (aucun lot partage entre deux instances).** Les deux amendements changent le contenu
  d'une condition sur un lot existant ; aucun des deux ne deplace un lot d'une instance a l'autre ni
  n'en ajoute. Cinq lots, cinq instances uniques assignees. **Tenue.**
- **`PAR-2` (aucune matiere ni niveau partages entre A et B).** Instance A : mathematiques CE1,
  enseignement moral et civique CM1. Instance B : sciences et technologie CM1. Aucun des deux
  amendements ne touche a quelle matiere ou quel niveau sert une ligne : `MAT-CE1-05` reste
  mathematiques CE1, `EMC-CM1-09` reste EMC CM1. Trois matieres/niveaux distincts, aucun partage.
  **Tenue.**
- **`PAR-7` (aucun terrain vierge donne a une instance qui n'en porte pas ailleurs).** Les comptes
  d'accumulation deja chiffres par `DEC-0102` (mathematiques CE1 : six lignes `couvert-valide` ;
  EMC CM1 : cinq ; sciences et technologie CM1 : quatorze) ne bougent pas tant qu'aucune des cinq
  lignes de s46 n'est fermee : aucun amendement de contenu de condition ne change un compte de
  lignes deja closes. **Tenue, sans dicter d'affectation particuliere, comme deja constate.**
- **`PAR-8` (plafond individuel 5,0, au plus trois lots par instance).** Les tarifs (1,0 pour
  `MAT-CE1-05`, 1,5 pour chacune des quatre autres lignes) sont fixes par matiere
  (`DEC-0074`/`DEC-0077`, section 8.2) et ne dependent pas du texte d'une condition pedagogique :
  aucun des deux amendements ne les change. Instance A : trois lots, 4,0/5,0. Instance B : deux
  lots, 3,0/5,0. **Tenue.**
- **`CYC-1` (continuite cycle 2 / cycle 3).** Due et nommee sur `EMC-CM1-01` (contre
  `EMC-CP-01`, `couvert-valide` depuis s31) et sur `EMC-CM1-09` (contre `EMC-CP-13`, `couvert-valide`
  depuis s41) ; non due sur `SCI-CM1-03` ni `SCI-CM1-16`, aucun miroir de cycle 2 trouve. La
  precision apportee a `EMC-CM1-09` porte sur l'usage de `EMC-5`/`EMC-6`/`EMC-7` entre eux, pas sur
  la continuite avec le CP : elle ne touche pas la lecture obligatoire du paquet de CP avant
  l'audit. **Tenue, inchangee.**

**Conclusion** : les deux amendements de `camille-po` precisent ce qu'un paquet doit tenir pour
satisfaire une condition deja posee ; aucun des deux ne deplace un lot, une matiere, un niveau, un
tarif ou une obligation de lecture croisee. Les cinq clauses tiennent toujours, reverifiees contre
le texte amende et non supposees inchangees.

## 4. Statut de `MAT-CE1-05` - desormais debloquee, engagee avec sa condition amendee

L'avis de faisabilite de `theo-archi` (section 1, Q1) avait rendu `S46-MAT-SOUSTRACTION`
**infaisable en l'etat des conditions** : les trois formes d'erreur de `SOP-CE1-5` donnent toujours
une valeur superieure a la bonne reponse, ce qui rend la bonne reponse predictible (toujours la plus
petite) et fait rougir `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` par construction.

`nadia-prof` a leve ce blocage le 2026-10-01 par un amendement ecrit de `SOP-CE1-5`
(`squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md`, section 2) : une **quatrieme
forme admise**, l'emprunt reporte deux fois (valeur `a - b - 10^(i+1)`, toujours **inferieure** a la
bonne reponse), avec sa portee, sa regle d'admission (la colonne qui recoit l'emprunt en double ne
comporte ni emprunt de plus ni de moins que le calcul juste) et une regle de rang sur le paquet
entier (items sans emprunt compris, choisir toujours la plus petite proposition ne donne pas la
bonne reponse sur plus de la moitie du paquet). La fiche conclut en toutes lettres (section 5) :
**"`MAT-CE1-05` peut etre engagee dans s46, perimetre inchange, avec `SOP-CE1-5` amendee"**. La
story `S46-MAT-SOUSTRACTION` du backlog cite deja les quatre formes et la regle de rang sur le
paquet entier dans ses criteres d'acceptation.

**`MAT-CE1-05` est donc engagee dans ce sprint, avec sa condition `SOP-CE1-5` dans sa version
amendee a quatre formes** (section 8, affectation nominative).

## 5. Refus d'une derivation a trois instances - trace, non bloquant pour s46

`nadia-prof` a instruit, le 2026-10-01, la question d'une borne a trois instances paralleles
(`squad/conformite/ARBITRAGE-PREALABLE-s46-trois-instances-refusees-le-vivier-borne-avant-la-
capacite.md`) et a **refuse de la deriver**, ni perenne ni pour une fenetre bornee. Motif : ce qui
borne desormais le produit n'est plus sa capacite d'audit mais le nombre de lignes instruites et
jouables (au plus 5,0 a 7,0 par sprint apres s46, contre une borne a deux instances de 8,0 deja non
remplie sur trois des quatre derniers dispositifs paralleles) ; aucun terrain sans paquet neuf n'a
la taille d'un sprint ; la retenue de couture ne tombe pas. Trois conditions cumulatives
rouvriraient la question (vivier instruit superieur a la borne a deux instances, dernier sprint
parallele clos sans occurrence des formes de couture, affectation des trois terrains tenant
`PAR-13`) : aucune n'est reunie aujourd'hui.

**Ce document ne rouvre pas cette question.** `DEC-0102` engage s46 a deux instances et ce planning
le reprend tel quel (section 8).

## 6. Stories deja instruites - chaine complete, rien n'est bloquant

Les cinq stories sont ecrites avec criteres d'acceptation dans `docs/backlog/backlog.md`, section
"Sprint 46". La chaine complete, deja fusionnee sur `round-two` avant ce planning (fichiers encore
non commites au moment ou je les lis, mais presents dans l'arbre de travail que ce worktree a
heritee a l'ouverture) :

| Ordre | Fichier | Ce qu'il leve |
|---|---|---|
| 1 | `DEC-0102` | strategie, perimetre, tarif et composition des cinq lignes, deux instances |
| 2 | Avis de faisabilite `theo-archi` (`squad/rapports/avis-faisabilite-s46-cinq-lots.md`) | verdicts des cinq lots ; Q1 bloquante sur `MAT-CE1-05`, Q2 non bloquante sur `EMC-CM1-09` |
| 3 | `squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md` (`nadia-prof`) | leve Q1 : quatrieme forme de `SOP-CE1-5`, regle de rang sur le paquet entier |
| 4 | Corrections `camille-po` dans le backlog | `S46-MAT-SOUSTRACTION` corrigee (quatre formes) ; `S46-EMC-INSTITUTIONS-CM1` precisee (Q2, usage de `EMC-5`/`EMC-6`/`EMC-7`) |
| 5 | `squad/conformite/ARBITRAGE-PREALABLE-s46-trois-instances-refusees-...md` (`nadia-prof`) | refuse une troisieme instance, non bloquant pour s46 (section 5) |
| 6 | `DEC-0103` | ouvre le perimetre francais CE1, route le referentiel a `nadia-prof` - hors engagement de s46 (section 10) |

**Consequence pour ce planning : les cinq lots n'ont plus aucun prealable bloquant.** Chaque
question qui conditionnait un verdict (Q1) ou precisait un texte de story (Q2) a ete levee et
integree au backlog avant l'ouverture de ce dossier.

| Lot | Ligne | Instance | Tarif | Verdict `theo-archi` | Source |
|---|---|---|---|---|---|
| `S46-MAT-SOUSTRACTION` | `MAT-CE1-05` | A | 1,0 | **Faisable**, condition `SOP-CE1-5` amendee par `nadia-prof` leve le seul blocage (Q1) | avis, section 1 et 3 ; fiche de conformite |
| `S46-EMC-EMOTION-CM1` | `EMC-CM1-01` | A | 1,5 | **Faisable en l'etat**, zero code de production | avis, section 4 |
| `S46-EMC-INSTITUTIONS-CM1` | `EMC-CM1-09` | A | 1,5 | **Faisable en l'etat**, question Q2 non bloquante levee par precision de `nadia-prof` | avis, section 5 ; backlog precise |
| `S46-SCI-MOUVEMENT-CM1` | `SCI-CM1-03` | B | 1,5 | **Faisable en l'etat**, zero code de production, un piege de fabrication | avis, section 6 |
| `S46-SCI-PHENOMENES-CM1` | `SCI-CM1-16` | B | 1,5 | **Faisable en l'etat**, zero code de production | avis, section 7 |

Porte technique : aucune mesure `mvn verify` n'est due a ce planning sur ces cinq lots, aucun code
de production n'a encore ete touche par aucun d'eux. L'avis de faisabilite rapporte sa propre porte
(section 10) sur un perimetre de tests cibles, machine virtuelle 21.0.10 ; `mvn -B clean verify`
complet non lance a cette etape, aucun diff de code n'etant encore ouvert.

## 7. Pieges de fabrication nommes par `theo-archi` et par `nadia-prof`, a transmettre au
developpeur affecte

### `S46-MAT-SOUSTRACTION` (`MAT-CE1-05`)

- **`SOP-CE1-5` compte desormais quatre formes, et seulement celles-la** : l'emprunt oublie,
  l'emprunt non reporte, la somme au lieu de la difference, l'emprunt reporte deux fois (valeur
  `a - b - 10^(i+1)`). La quatrieme forme n'est admise sur un item que si la colonne qui recoit
  l'emprunt en double se calcule sans emprunt de plus ni de moins que le calcul juste ; sinon une
  autre forme de la liste est prise.
- **La regle de rang porte sur le paquet entier**, items a deux propositions (sans emprunt)
  compris : choisir toujours la plus petite proposition ne doit pas donner la bonne reponse sur plus
  de la moitie des items du paquet.
- **`SousPartieDeMathematiquesCe1` doit declarer une entree neuve** pour `MAT-CE1-05` dans le meme
  diff que le paquet (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`), libelle sans point
  final : "Poser et effectuer une soustraction (nombres a deux ou trois chiffres)".
- **Separation d'avec `MAT-CE1-03`** (`SOP-CE1-3`) : au moins les deux tiers des items portent un
  nombre de trois chiffres ou un premier terme superieur a 100, pour ne pas reproduire sous un autre
  code la matiere deja servie par `bandit-manchot-soustractions-ce1`.
- Aucun habillage, aucun resultat negatif (`SOP-CE1-6`), `0 <= b <= a <= 1000`.

### `S46-EMC-EMOTION-CM1` (`EMC-CM1-01`)

- Zero code de production, patron de `PaquetCm1EmcFaitOpinionTest` et
  `PaquetCpEmcEmotionsAutruiTest`. `FER-2` (au moins 11 items), `FER-3` (marqueur par item, deux
  taches - identifier l'emotion, distinguer ressentir et agir -, au moins trois sur la moins
  servie) sont automatisables. `EMC-1`, `EMC-2`, `EMC-5`, `EMC-8` restent a l'audit de `nadia-prof`,
  pas automatisables sur l'item seul.
- **`CYC-1` est une obligation de l'audit, pas une tache de fabrication** : l'instance A relit
  `bandit-manchot-emotions-autrui-cp` avant de juger ; rien n'est du cote developpeur au-dela de ne
  pas contredire les quatre emotions deja servies au CP (joie, tristesse, colere, peur).

### `S46-EMC-INSTITUTIONS-CM1` (`EMC-CM1-09`)

- **`FER-1` est calculable** : chacune des trois institutions de la parenthese (mairie, conseil
  municipal, ecole) doit etre la bonne reponse d'au moins un item.
- **Precision du 2026-10-01 sur `EMC-5`/`EMC-6`/`EMC-7`** : `EMC-6` ne garde pas seule cette ligne.
  `EMC-7` s'applique des qu'un numero d'urgence apparait (enonce, proposition, indice ou
  explication) : exact, sa source dans le commentaire du paquet, jamais deux numeros tous deux
  corrects pour la meme situation. Un service d'urgence **nomme sans numero** ("les pompiers", "le
  SAMU") n'entre pas dans `EMC-7` : c'est `EMC-5` qui le garde - deux services pouvant porter
  secours a la meme situation ne doivent jamais etre l'un le distracteur de l'autre sur un meme
  item.
- **`CYC-1`** : l'instance A relit `bandit-manchot-roles-ecole-commune-cp` avant de juger, y compris
  sur un service d'urgence deja servi en bonne reponse au CP (pompiers).

### `S46-SCI-MOUVEMENT-CM1` (`SCI-CM1-03`)

- **Refus d'avance** : aucun item ne calcule une vitesse, une duree ou une longueur - cette
  operation reste comptee sur mathematiques CM1 (`ADR-0081`, clause 5).
- **Piege de fabrication nomme par `theo-archi`** : une proposition qui commence par un nombre suivi
  de `h`, `heure`, `min` ou `minute` est lue comme une duree par le garde-fou de rang (`ADR-0102`,
  motif `RESSEMBLE_A_UNE_DUREE`), et trois propositions numeriques entreraient dans sa population.
  Preferer des propositions textuelles (`plus vite`, `en ligne droite`).
- `SCI-1` (indice qui dit pourquoi une conception est fausse, sans nommer la bonne reponse), `SCI-3`
  (au plus un item sur six restitue une valeur numerique), `SCI-4` (au moins trois propositions sur
  un item a conception fausse), `HIS-3` (source en commentaire), `CON-1` (propositions masquees),
  `FER-1` (trajectoire, vitesse, point de repere, chacun en bonne reponse d'au moins un item),
  `FER-2`/`FER-3` (au moins dix items, deux taches).

### `S46-SCI-PHENOMENES-CM1` (`SCI-CM1-16`)

- `FER-1` (phenomene geologique, meteorologique, risque associe, chacun en bonne reponse d'au moins
  un item), `BIA-2` (les phenomenes decrits ne sont jamais systematiquement situes hors de France/
  Europe), `SCI-2` (aucune responsabilite portee sur l'enfant ou son foyer face a un risque
  naturel), `SCI-1`, `SCI-3`, `SCI-4`, `HIS-3`, `CON-1`, `FER-2`/`FER-3` comme pour le lot precedent.
  **`CYC-1` non due** : aucune ligne de cycle 2 deja servie ne porte sur un seisme, une eruption ou
  une tempete (verifie par `DEC-0102`, section 5, et repris dans la story).
- **Adjacence signalee, sans collision** : `cm1-comprehension` (francais CM1) porte deja un item
  d'inference dont la bonne reponse est *"une grosse tempete"*. Un item ne sert qu'une ligne
  (`ADR-0081`, clause 5) : rien n'est compte deux fois, rien a corriger avant d'ecrire le paquet.

### Pieges communs aux cinq lots

- `TexteAccentueExposeParLePortEntrantTest` : toute chaine neuve (titre, consigne, competence,
  enonce) doit y passer.
- `AucunTitreDeJeuNePorteSonNiveauTest` : aucun titre ne porte `CP`, `CE1` ni `CM1`.
- **Aucune mecanique neuve, aucune `ArchRule` neuve n'est due** (avis, section 8 : 36
  `static final ArchRule`, 35 `@ArchTest`, compte inchange depuis s45). Le test de chaque paquet est
  la seule garde automatique des conditions listees ci-dessus.

## 8. Affectations nominatives

`docs/backlog/backlog.md` (section "Sprint 46") ne propose pas de repartition nominative ;
affectation finale par `sofia-sm` (`DEC-0102`, section 8, qui route l'ouverture du sprint a
`sofia-sm`). Decision de capacite : cinq lots, trois developpeurs. `S46-MAT-SOUSTRACTION` est le
seul des cinq a porter une complexite de test au-dela du patron deja connu (quatre formes de
distracteur, regle de rang sur le paquet entier items sans emprunt compris) ; les quatre autres sont
des paquets de choix textuel sans code de production attendu, sur le patron deja ferme de s45. Je
retiens `marc-dev` seul sur le lot de complexite moderee, et je repartis les quatre lots de basse
complexite entre `lea-dev` et `sofia-sm` (casquette dev), un de chaque instance pour chacun - meme
raisonnement qu'a s45 (section 3) : `PAR-2` gouverne le partage de matiere/niveau entre les deux
instances de `nadia-prof`, pas la charge d'un developpeur, et rien n'empeche un meme developpeur de
coder un lot de chaque instance.

| Lot | Ligne | Instance | Developpeur | Peut demarrer l'ecriture de code des maintenant ? |
|---|---|---|---|---|
| `S46-MAT-SOUSTRACTION` | `MAT-CE1-05` | A | `marc-dev` | **Oui** |
| `S46-EMC-EMOTION-CM1` | `EMC-CM1-01` | A | `lea-dev` | **Oui** |
| `S46-SCI-MOUVEMENT-CM1` | `SCI-CM1-03` | B | `lea-dev` | **Oui** |
| `S46-EMC-INSTITUTIONS-CM1` | `EMC-CM1-09` | A | `sofia-sm` (casquette dev) | **Oui** |
| `S46-SCI-PHENOMENES-CM1` | `SCI-CM1-16` | B | `sofia-sm` (casquette dev) | **Oui** |

**Pourquoi ce regroupement.** `marc-dev` prend seul `MAT-CE1-05`, le seul lot dont le test de paquet
porte une logique neuve (quatre formes de distracteur, regle de rang etendue aux items sans
emprunt). `lea-dev` et `sofia-sm` prennent chacun un lot d'EMC CM1 (instance A) et un lot de
sciences CM1 (instance B), repartition symetrique qui ne cree aucune collision : les deux Done
pedagogiques de chaque developpeur restent portes par deux instances differentes de `nadia-prof`,
qui n'auditent chacune que leurs propres lots.

## 9. Deux instances de `nadia-prof` - Done pedagogique en fin de sprint, chacune sur son
perimetre seul

**Instance A** audite `S46-MAT-SOUSTRACTION`, `S46-EMC-EMOTION-CM1` et `S46-EMC-INSTITUTIONS-CM1`.
Budget individuel 5,0, cout retenu 4,0 (1,0 + 1,5 + 1,5), marge 1,0. Elle porte, avant d'auditer, les
conditions qu'elle a elle-meme ecrites ou amendees sur ces trois lots (amendement de `SOP-CE1-5`,
precision sur `EMC-5`/`EMC-6`/`EMC-7`) et l'obligation `CYC-1` : relire
`bandit-manchot-emotions-autrui-cp` et `bandit-manchot-roles-ecole-commune-cp` avant de juger
`EMC-CM1-01` et `EMC-CM1-09` respectivement.

**Instance B** audite `S46-SCI-MOUVEMENT-CM1` et `S46-SCI-PHENOMENES-CM1`. Budget individuel 5,0,
cout retenu 3,0 (1,5 + 1,5), marge 2,0. `CYC-1` n'est due sur aucun des deux lots (verifie section
3). **Instance A n'audite jamais un lot de l'instance B, et reciproquement** - regle de `DEC-0098`
pour tout dispositif a deux instances, qu'aucun document de s46 ne touche.

Budget de sprint opposable 8,0, cout retenu 7,0, marge de sprint 1,0 non allouee des l'ouverture
(`DEC-0102`, section 6 : le plafond mecanique de 8,0 n'est pas atteignable dans le vivier instruit
sans rouvrir la regle "une seule ligne de mathematiques CE1 par sprint", reconduite une quatrieme
fois, ni depasser le plafond individuel de 5,0 par instance).

## 10. Question ouverte, non engagee dans s46 : le referentiel francais CE1 n'est pas integre a la
matrice

`DEC-0103` ouvre le perimetre francais CE1 (deuxieme matiere du niveau, apres les mathematiques) et
route l'ecriture d'un referentiel a `nadia-prof`. Ce referentiel existe desormais
(`squad/programme/francais/competences-ce1.md`, ecrit le 2026-10-01). **Verifie par lecture directe
du fichier plutot que repris de confiance : il porte 26 lignes de competence**, `FRA-CE1-01` a
`FRA-CE1-26`, reparties en quatre domaines (lecture et comprehension de l'ecrit, ecriture,
orthographe et grammaire, vocabulaire).

**Ce referentiel n'est pas encore integre a `squad/programme/MATRICE-couverture.md`** : aucune
section "Francais - CE1" n'y existe, et le denominateur atteignable (156 lignes a la sortie de s45)
ne bouge pas tant que cette integration n'est pas faite. `DEC-0103` route cette integration a
`camille-po`, "des que le referentiel existe - jamais avant" (section 3), et dit explicitement que
le recul du taux affiche qui en resultera sera ecrit d'avance comme un effet de perimetre, pas
decouvert comme une regression.

**Je ne l'engage pas dans ce planning.** Ni l'integration des 26 lignes a la matrice, ni une
premiere story de francais CE1 ne sont engagees par s46 : `DEC-0102` ne les cite pas, et aucun des
cinq lots de ce sprint ne porte sur le francais. Je la signale comme **candidate pour un sprint
futur** - l'integration a la matrice d'abord (tache de `camille-po`), un premier chiffrage de lot
ensuite, dans cet ordre, comme `DEC-0103` le dit deja pour les mathematiques CE1 a s36.

## 11. Verification `PAR-16` - point de controle poste/story

| Element | Affectation nominative | Issue |
|---|---|---|
| `S46-MAT-SOUSTRACTION` (`MAT-CE1-05`) | `marc-dev`, puis `nadia-prof`, instance A | Engagee |
| `S46-EMC-EMOTION-CM1` (`EMC-CM1-01`) | `lea-dev`, puis `nadia-prof`, instance A | Engagee |
| `S46-EMC-INSTITUTIONS-CM1` (`EMC-CM1-09`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance A | Engagee |
| `S46-SCI-MOUVEMENT-CM1` (`SCI-CM1-03`) | `lea-dev`, puis `nadia-prof`, instance B | Engagee |
| `S46-SCI-PHENOMENES-CM1` (`SCI-CM1-16`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance B | Engagee |

Aucun element n'est "non engage, budget libere" : les cinq lots de `DEC-0102` sont tous affectes.
La marge de sprint de 1,0 (section 9) n'est engagee sur aucun lot nouveau par ce planning.

## 12. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : aucune mecanique neuve n'est due sur ces
  cinq lots (avis de faisabilite, section 8).
- Il ne corrige aucun texte de story : les deux corrections dues (`MAT`, `EMC-CM1-09`) sont deja
  fusionnees par `camille-po` avant ce planning.
- Il ne pose aucun discriminant pedagogique neuf : l'amendement de `SOP-CE1-5` et la precision sur
  `EMC-5`/`EMC-6`/`EMC-7` sont deja ecrits par `nadia-prof` (sections 4 et 7).
- Il ne rouvre pas `DEC-0102`, ni `DEC-0103`.
- Il ne tranche pas la question d'une troisieme instance : `nadia-prof` l'a deja refusee (section
  5), et ce document ne fait que la tracer.
- Il n'engage ni l'integration du referentiel francais CE1 a la matrice, ni une premiere story de
  francais CE1 (section 10) : candidate pour un sprint futur, non engagee ici.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.

## Decisions prises

Aucune `DEC` ni `ADR` n'est creee ou amendee par ce planning. Une decision m'appartient en propre et
est ecrite ici : la decision de capacite de la section 8 (un developpeur seul sur le lot de
complexite moderee, les deux autres developpeurs repartis a raison d'un lot par instance chacun) et
l'affectation nominative qui en decoule. `DEC-0102`, `DEC-0103`, l'avis de `theo-archi`, la fiche de
conformite et l'arbitrage prealable de `nadia-prof` existent deja avant ce planning et ne sont pas
amendes par lui.
