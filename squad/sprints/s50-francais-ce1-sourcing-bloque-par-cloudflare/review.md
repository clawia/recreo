# Review - s50 - deux lignes neuves de francais CE1, trois corrections differees, blocage puis
levee de la dette T6 (`ADR-0082`) sur l'ensemble de la porte technique, premier paquet d'arts
plastiques CP

- **Horodatage de debut** (`planning.md`, amendement de cloture) : 2026-10-03 21:53 (UTC+02:00) -
  horodatage du commit `343f86c7`, qui a cree le fichier (`git log --follow`). La section 0 du
  planning ne portait que la date, sans heure ; complete par un amendement date, sans toucher au
  texte original (voir `planning.md`).
- **Horodatage de fin** : 2026-10-04 22:39:37 (UTC+02:00, horodatage du dernier commit du sprint,
  `01957d9a`, cloture de `camille-po`).
- **Duree effective** : 24 h 46 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Verifie avant d'ecrire** : `git status --short` rend vide, `git rev-parse --abbrev-ref HEAD`
  rend `round-two`, `git log -1 --format=%H` rend `01957d9a2dedd555dd4eb7e26d5574c99a850cd5`.
- **Arbre revu** : `round-two` a `01957d9a`, 35 commits depuis l'ouverture (`1dc095ea..01957d9a`).

---

## 1. L'increment livre, en une phrase

Trois lignes du programme passent de `non couvert` a `couvert-valide` - `FRA-CE1-02` (valeur d'une
lettre selon son contexte, VALIDE sans reserve au second passage apres un refus le meme jour),
`FRA-CE1-16` (homophones grammaticaux `a`/`à` et `et`/`est`, VALIDE SOUS RESERVE) et `ART-CP-09`
(decrire une oeuvre, premier paquet d'arts plastiques CP, VALIDE sans reserve au **troisieme**
passage, apres deux refus) - pendant qu'une dette technique deja nommee (`T6`, `ADR-0082`) s'est
revelee bloquer la porte technique de l'**ensemble** du sprint, pas seulement du chantier de
sourcing qui la portait, et qu'elle a ete levee en cours de route par l'engagement du paquet qui
manquait. La couverture programme passe de **120/182 (65,9 %)** a **123/182 (67,6 %)**.

## 2. Le rapport qualite de `yanis-qa` (resume, deux mesures)

`rapport-qualite.md` porte deux parties distinctes, l'une ne remplace pas l'autre :

- **Partie B** (premiere mesure, commit `06508d48`, arbre a `6eded9d1`, 9 commits) : trois jobs
  verts (`prefixes`, `chemins`, `outils`), le quatrieme (`build`) **rouge** - exactement les deux
  echecs de la dette `T6` deja nommee par `DEC-0108`, rien d'autre, confirme en lisant le message
  d'echec en entier. 1991 tests unitaires executes (+22 depuis s49), 0 test d'integration execute
  (le reacteur n'atteint jamais la phase `verify` qui lancerait `failsafe`). Verdict : *"Le Done
  technique de s50 n'est pas prononce vert [...] un job sur quatre est rouge."*
- **Partie A** (mesure finale, commit `bfb8e3c4`, arbre a `1ca58eb1`, 21 commits) : les quatre jobs
  sont verts, chacun reproduit individuellement avec la commande exacte du workflow. `build` rend
  `BUILD SUCCESS`, 2004 tests (1998 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur -
  confirme en relisant le journal complet, en particulier que
  `LIllustrationDeContenuResoutEtAucuneNeDortTest` passe de 2 echecs a 0. Verdict : *"La porte
  technique de s50 est verte. Le Done technique du sprint est prononce."*

Le passage du rouge au vert tient a un seul fait : la livraison du paquet `cp-decrire-une-oeuvre`
(`1db6348f`, `marc-dev`) declare enfin des identifiants `oeuvre-` que la dette `T6` attendait.

## 3. La confirmation ArchUnit finale de `theo-archi` (resume)

`confirmation-archunit.md` porte elle aussi deux mesures : les sections 0 a 4 (commit `ce72dc03`,
arbre a `6eded9d1`, avant la levee de `T6`) et la **section 5**, amendee, qui fait foi pour la
review (commit `1ca58eb1`, arbre a `1db6348f`) :

- **Reacteur complet** : `BUILD SUCCESS`, 2004 tests, 0 echec, 0 erreur, 0 ignore, `failsafe`
  compris.
- **Regles ArchUnit** : 214 tests dans `com.recreo.archi`, 214 verts (les deux qui restaient rouges
  en section 1 sont passes au vert).
- **Compte de regles inchange** : 36 `static final ArchRule`, 35 `@ArchTest`, sortie par fichier
  identique a `ce72dc03` (`diff` vide) - aucune regle ajoutee, deplacee ni retiree depuis
  l'ouverture de s50.
- **Clean Architecture tenue** : `git diff --name-only ce72dc03 1db6348f` sur `domain`,
  `application`, `*/src/main/java/**`, `pom.xml`, gabarits et scripts de CI est vide ; les deux
  garde-fous touches (`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`,
  `LIllustrationDeContenuResoutEtAucuneNeDortTest`) ne changent que des constantes de cliquet, pas
  leur logique. Le bug corrige par `marc-dev` dans `1db6348f` ne touche qu'un fichier de test
  (`LIllustrationDUnItemServiAtteintLEcranDeMancheTest`), pas le code de production qu'il verifie.

## 4. Chronologie du sprint - pourquoi il a beaucoup devie de son plan initial

1. **Ouverture** (`DEC-0108`, `camille-po`) : deux lignes neuves de francais CE1 (`FRA-CE1-02`,
   `FRA-CE1-16`), trois corrections differees (`GRC-CE1-R1`, `SOU-CE1-R1`, `DIV-CE1-R1`), le
   chantier de sourcing du corpus d'oeuvres accepte avec deux echecs de `mvn verify` assumes comme
   dette intentionnelle (`T6`, hors perimetre de s50). Deux corrections factuelles au planning
   proprement dites dans le meme document (section 1 : `T2`-`T4` d'`ADR-0082` deja clos, pas une
   priorite de dev ; section 4 : `SOP-CE1-R1` et `TAB-CE1-R1` deja fermees depuis s48, retirees du
   perimetre).
2. **Avis de faisabilite des cinq stories** (`theo-archi`) : quatre faisables en l'etat,
   `SOU-CE1-R1` infaisable telle que prescrite (le correctif demande rougit le garde-fou de rang
   sur ses deux clauses), question routee a `nadia-prof`.
3. **Arbitrage de `nadia-prof` sur `SOU-CE1-R1`** : nouvelle forme de distracteur admise
   (`a - 10 x b`, confusion unites-dizaines), et reponse a une seconde question de `theo-archi` sur
   deux assertions de garde-fou (`GRC-CE1-R1`, `DIV-CE1-R1`).
4. **Livraison technique des cinq stories**, sans toucher `src/main/java`.
5. **La decouverte bloquante** : `yanis-qa` mesure que le job `build` reste rouge sur la dette
   `T6`, deja nommee par `DEC-0108` mais jamais mesuree comme bloquant le Done technique de
   l'**ensemble** du sprint, et pas seulement du chantier de sourcing. `camille-po` le confirme et
   l'ecrit en amendement (`DEC-0108` section 9).
6. **Le choix de l'utilisatrice, recu hors des neuf agents** : recuperer a la main les trois
   oeuvres bloquees par Cloudflare depuis s49 (`oeuvre-chambre`, `oeuvre-rue-sous-la-pluie`,
   `oeuvre-grande-jatte`), verifiees `CC0-1.0` contre l'API `api.artic.edu` et inscrites au
   registre (commit `56b6b77c`). **Ce commit porte le prefixe `[THEO]` a tort** : il a ete execute
   par la session principale avec l'utilisatrice, pas par l'agent `theo-archi` - ecart de process
   traite en section 6 et en `retro.md`.
7. **La decouverte que ce commit ne suffit pas** : `theo-archi`, en rendant son avis de
   faisabilite sur `S50-ART`, trouve que son propre commit pese trop (images non redimensionnees,
   sept echecs de poids en plus) et que le garde-fou *"aucune ne dort"* exige **chaque**
   reproduction declaree, pas une seule - lecture differente de celle que `DEC-0108` et `DEC-0109`
   avaient ecrite jusque-la.
8. **L'engagement de `T6`** : `camille-po` ouvre `DEC-0109`, engage `ART-CP-09`/`ART-CP-10` en
   seconde instance de `nadia-prof` (tarif 2,0), puis retranche a trois reprises face aux faits qui
   remontent : retrait des 17 oeuvres hors vivier (option O1, restaurables a l'octet depuis
   l'historique Git), puis `ART-CP-10` refusee par `nadia-prof` (la mecanique de vue simultanee
   n'est pas engagee dans `T6`), perimetre final reduit a `ART-CP-09` seule, huit oeuvres.
9. `theo-archi` corrige le defaut de poids (redimensionnement des trois fichiers, cliquet releve),
   `marc-dev` livre le paquet `cp-decrire-une-oeuvre` et fait passer le reacteur complet en
   `BUILD SUCCESS` - trouvant au passage un bug latent dans un test de rendu jamais exerce
   jusque-la (population d'oeuvres vide).
10. **Done technique prononce** : `theo-archi` et `yanis-qa` confirment les quatre jobs verts sur
    l'etat final (section 2 et 3).
11. `relecteur-pedagogique` corrige, entre autres, une definition perimee du Done technique dans
    `squad/README.md` lui-meme.
12. **Six Done pedagogiques**, en deux instances paralleles de `nadia-prof` (section 5).
13. `camille-po` ferme la matrice et corrige une citation fautive de `DEC-0109` (section 6).

## 5. Done pedagogique - les six verdicts, avec leurs reserves

| # | Ligne / objet | Instance | Verdict final | Episode |
|---|---|---|---|---|
| 1 | `FRA-CE1-02` | A | **VALIDE**, sans reserve | Premier passage **REFUSE** (`76b934da`) : `ç` absent de tout item (`FER-1`, `VLE-CE1-1` non tenues), trois indices de `s` enseignaient un critere faux (`TRV-CE1-5`). `marc-dev` corrige (`15efbcca`) en reprenant la barre ecrite par `nadia-prof` au mot pres. Second passage le meme jour (`145307a9`) : **VALIDE** |
| 2 | `FRA-CE1-16` | A | **VALIDE SOUS RESERVE** (`b29d2680`) | Reserve neuve `HOM-CE1-R1` : les sept prenoms du paquet viennent tous du meme repertoire (origines non variees), equilibre de genre tenu par ailleurs. Non bloquante, story ecrite au backlog dans le commit de cloture |
| 3 | `GRC-CE1-R1` (reserve sur `FRA-CE1-01`, deja `couvert-valide`) | A | **Levee** (`588c8b52`) | Propriete du second passage de s49 tenue (*"aucun distracteur sur plus de deux items"*), aucun statut de ligne ne bouge |
| 4 | `DIV-CE1-R1` (reserve sur `MAT-CE1-03`, deja `couvert-valide`) | A | **Levee** (`588c8b52`) | Le diviseur recopie `b` figure desormais sur chaque item de la forme visee, aucun statut ne bouge |
| 5 | `SOU-CE1-R1` (reserve sur `MAT-CE1-03`, deja `couvert-valide`) | A | **Levee** (`588c8b52`, correctif jouaable confirme) | Arbitrage prealable (2026-10-03) : le correctif initialement prescrit rougissait le garde-fou de rang ; `nadia-prof` admet `a - 10 x b` comme nouvelle forme de distracteur, `lea-dev` l'applique (`6eded9d1`), rang confirme en jouant au second passage |
| 6 | `ART-CP-09` (`S50-ART`) | B | **VALIDE**, sans reserve, **troisieme passage** | Premier passage **REFUSE** (`e02c0437`, quatre motifs : indice faux sur `oeuvre-femme-au-luth`, `oeuvre-quatre-saisons` non visible a 176 px et trouvee par elimination, enonce d'`oeuvre-hippopotame` inadapte, libelle non servi). Second passage **REFUSE** (`cd12a90b`) : trois motifs et un point mineur leves, motif 2 maintenu et **reformule** - la reponse attendue et l'indice de `oeuvre-quatre-saisons` disaient une chose fausse du tableau (le visage est un tronc d'arbre, pas des fruits et legumes), erreur venant de la **propre fiche de `nadia-prof`** au premier passage, qu'elle corrige elle-meme (section 12.2). Troisieme passage (`fd1b0096`), apres reecriture de l'item par `lea-dev` (`ec711904`) : **VALIDE**. Mention de couverture : *"sujet et materiaux seuls ; les couleurs d'une oeuvre ne sont pas servies (`ART-1`)"*. `ART-CP-10` reste `non couvert` : elle exige une mecanique de vue simultanee, hors perimetre de `T6` |

## 6. Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|---|
| 1 | Le planning de s50 citait `T2`-`T4` d'`ADR-0082` comme "priorite de developpement de s50" alors qu'ils etaient deja clos depuis s49 | Produit / exactitude d'un planning | `camille-po` | **Resolu le jour meme** - corrige dans `DEC-0108` section 1, note datee, sans reecriture silencieuse |
| 2 | Le planning citait `SOP-CE1-R1` et `TAB-CE1-R1` comme corrections pretes a developper, alors qu'elles etaient fermees depuis s48 (`VALIDE`, reserve levee) | Produit / exactitude d'un planning | `camille-po` | **Resolu le jour meme** - `DEC-0108` section 4, verifie au disque plutot que repris de confiance |
| 3 | **`T6` (`ADR-0082`), dette deja nommee, decouverte comme bloquant la porte technique de l'ensemble du sprint et non du seul chantier de sourcing** | Technique / portee d'une dette assumee | `yanis-qa` (mesure), `camille-po` (decision), question de scoping routee a `sofia-sm` puis `theo-archi` (non tranchee comme question CI) | **Resolu autrement qu'envisage** - pas par un scoping de la dette en CI, mais par l'engagement effectif de `T6` (`DEC-0109`) et la livraison du paquet qui manquait (`1db6348f`) ; build vert confirme (`bfb8e3c4`, `1ca58eb1`) |
| 4 | Defaut de poids sur le commit `56b6b77c` : trois fichiers de l'Art Institute of Chicago non redimensionnes, cliquet non releve - neuf echecs de test, sept imputables a ce commit | Technique / garde-fou de poids | `theo-archi` | **Resolu** - trouve par `theo-archi` lui-meme en rendant son propre avis de faisabilite, corrige par lui-meme (`df135abd`, redimensionnement a 352 px et cliquet releve) |
| 5 | Lecture erronee de la clause *"aucune ne dort"* comme portant sur "au moins un identifiant `oeuvre-` declare" (singulier), alors qu'elle porte sur **chaque** reproduction servie | Produit / lecture d'une regle technique | `theo-archi` (trouve), `camille-po` (tranche) | **Resolu** - `DEC-0109` section 9 corrige la lecture, retient l'option O1 (retrait des 17 reproductions hors vivier de ce sprint, restaurables a l'octet depuis l'historique Git, zero perte de verification) |
| 6 | `ART-CP-10` refusee pedagogiquement sous forme texte : rapprocher deux oeuvres par des propositions qui les decrivent est une contrefacon, pas un rapprochement | Pedagogie | `nadia-prof` | **Tranche** - perimetre de `S50-ART` reduit a `ART-CP-09` seule (`DEC-0109` section 10.1) ; `ART-CP-10` reste `non couvert`, obstacle 13 |
| 7 | Le commit `56b6b77c` porte le prefixe `[THEO]` alors qu'il a ete execute par la session principale avec l'utilisatrice, pas par l'agent `theo-archi` | Capacite / gouvernance - attribution de commit | `sofia-sm` | **Ouvert, constate** - pas de reecriture d'historique engagee ; ecart de process nomme en `retro.md` |
| 8 | `FRA-CE1-02` refusee au premier passage (`ç` absent, trois indices de `s` enseignant un critere faux) | Pedagogie / exactitude d'un contenu neuf | `nadia-prof` (barre ecrite), `marc-dev` (correction) | **Resolu le jour meme** - second passage `VALIDE` sans reserve |
| 9 | `S50-ART` refusee au premier passage (quatre motifs d'exactitude et de lisibilite a 176 px) | Pedagogie / exactitude d'un contenu neuf | `nadia-prof` (barres ecrites), `lea-dev` (correction) | **Resolu au second passage** sur trois motifs et un point mineur ; motif 2 maintenu, reformule (obstacle 10) |
| 10 | **Erreur de lecture de `nadia-prof` elle-meme** sur `oeuvre-quatre-saisons` (elle avait ecrit que le visage est fait de fruits ; il est fait d'un tronc d'arbre), persistant de sa propre fiche jusque dans `DEC-0109` section 11 | Pedagogie / exactitude d'un constat de l'autorite pedagogique elle-meme | `nadia-prof` (trouve et corrige), `camille-po` (corrige la `DEC` qui la citait) | **Resolu** - corrigee par `nadia-prof` elle-meme au second passage (section 12.2), signalee deux fois pour correction du `DEC` (12.7, 13.4), corrigee par `camille-po` dans le commit de cloture (nouvelle section 12 de `DEC-0109`) ; troisieme passage `VALIDE` apres reecriture de l'item sur la barre corrigee |
| 11 | `squad/README.md` portait une definition perimee du Done technique (*"`mvn verify` vert"*), deja disqualifiee par l'amendement de s31 de `CLAUDE.md` | Redaction | `relecteur-pedagogique` | **Resolu** (`655b319b`) |
| 12 | Reserve `HOM-CE1-R1` (prenoms d'origines non variees sur `FRA-CE1-16`) | Pedagogie / correction differee, non bloquante | `camille-po` | **Ferme** - story ecrite au backlog dans le commit de cloture (`01957d9a`), developpeur encore a affecter |
| 13 | `ART-CP-10` reste `non couvert` : elle exige une mecanique de vue simultanee (deux oeuvres montrees ensemble), hors perimetre chiffre de `T6`, et un `ADR` prealable avant tout code | Produit puis architecture, pour un sprint futur | `camille-po` puis `theo-archi` | **Ouvert**, hors perimetre de s50 |

## 7. La couverture du programme

**120/182 (65,9 %)** a l'ouverture -> **123/182 (67,6 %)** a la cloture, verifiee par
`camille-po` sur `squad/programme/MATRICE-couverture.md` (commit `01957d9a`). Trois lignes
fermees : `FRA-CE1-02`, `FRA-CE1-16` (sous reserve non bloquante), `ART-CP-09`. `ART-CP-10` reste
`non couvert` (obstacle 13). Les trois reserves levees (`GRC-CE1-R1`, `DIV-CE1-R1`, `SOU-CE1-R1`)
ne changent aucun statut : elles portaient sur des lignes deja `couvert-valide`.

## 8. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les six verdicts (section 5) sont deja ecrits dans les
  fiches de conformite ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` : deja execute par
  `camille-po` dans le commit de cloture (section 7).
- Elle ne rejuge aucun verdict technique ou pedagogique deja rendu.
- Elle ne tranche pas le sort de `ART-CP-10` ni le motif d'attribution du commit `56b6b77c` : elle
  les constate et les route (obstacles 7 et 13).

## Decisions prises

| Decision | Statut | Objet |
|---|---|---|
| `DEC-0108` | Acceptee, amendee le jour meme de son ouverture (section 1, 4) et le 2026-10-04 (section 9) | Ouverture de s50 : deux lignes neuves de francais CE1, trois corrections differees, chantier de sourcing accepte avec dette assumee (`T6`). Amendement section 9 : `T6` bloque la porte technique de l'ensemble du sprint, pas seulement du chantier de sourcing |
| `DEC-0109` | Acceptee, amendee a quatre reprises le 2026-10-04 (sections 9, 10, 11, 12) | Reouverture de s50 : `T6` (`ART-CP-09`/`ART-CP-10`) engage en seconde instance de `nadia-prof`, tarif 2,0. Section 9 : option O1 retenue (retrait des 17 reproductions hors vivier). Section 10 : perimetre reduit a `ART-CP-09` seule apres l'arbitrage prealable de `nadia-prof` (`ART-CP-10` refusee en texte). Section 11 : l'Arcimboldo reste au paquet, reecrit plutot que retire. Section 12 (commit de cloture) : correction d'une citation fautive sur le motif reel du visage de l'Arcimboldo, signalee deux fois par `nadia-prof` |

**Arbitrages pedagogiques de `nadia-prof`, non des `DEC` mais actant des regles opposables pour
la suite** : arbitrage de `SOU-CE1-R1` (2026-10-03, forme de distracteur `a - 10 x b` admise) ;
arbitrage prealable de `S50-ART` (2026-10-04, `ART-CP-10` refusee en texte, `ART-CP-09` faisable
sans le mot *couleurs*).
