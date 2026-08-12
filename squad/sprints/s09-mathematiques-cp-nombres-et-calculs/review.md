# [Sprint s09] Review - 2026-08-12

- **Horodatage de debut** : 2026-08-12 09:37 (repris de `planning.md`)
- **Horodatage de fin** : 2026-08-12 14:20
- **Duree effective** : 4 heures 43 minutes
- **Anime par** : sofia-sm
- **Commit de tete de `round-two`** : `f8ba436a166f18839d50306f972cadee52ba53bc`

## Increment livre

Le catalogue passe de quatre a huit jeux : quatre paquets neufs de pur contenu YAML en
mathematiques CP, domaine "Nombres et calculs" - soustractions (24 items, S9-1), complements a 10
(11 items, 7 manches, S9-2), comparaison de deux nombres par les signes `<`, `>`, `=` (24 items,
S9-3), problemes additifs en situation (22 items, S9-4). Zero ligne de `recreo.css`, zero gabarit,
zero mecanique neuve : le sprint tient la contrainte de contenu pur posee par l'utilisatrice, et
livre quand meme quatre jeux. `ADR-0032`, `DEC-0019`, `DEC-0020`, `DEC-0021` accompagnent
l'increment. La matrice de couverture passe de 3 a **6 lignes `couvert-valide` sur 46**, les
`en cours` retombant de 4 (pic intermediaire du premier audit) a **2**.

**Le fait qui domine cette review n'est pas le compte de jeux, c'est ce que 371 tests verts
n'avaient pas vu.** `nadia-prof` a refuse trois des quatre increments au premier audit, sur trois
textes que l'enfant lit : 48 fautes de francais dans les 24 indices des soustractions
(`Leve`/`Lève`, `baisse-en`/`baisses-en`, un participe non accorde) ; deux enonces des problemes
additifs ou le "en" partitif contredisait la reponse attendue, la bonne reponse n'etant meme pas
proposee ; un indice de la comparaison affirmant que 56 vient juste avant 60 (c'est 59). Les trois
ont ete corriges par des gestes bornes, ecrits par `nadia-prof` elle-meme dans chaque refus, puis
revalides au second audit sur 60 parties par jeu - 1800 coups joues volontairement faux pour lire a
l'ecran la totalite des items et des indices, pas dans le fichier.

## Fusion du dernier lot (depuis la derniere ceremonie)

`round-two` etait a `ea9473d` a l'ouverture de cette cloture (planning, developpement, deux tours
de porte pedagogique et la correction de fait de `nadia-prof` deja fusionnes). Un seul lot restait
a fusionner :

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| `relecteur-pedagogique` | `s09-relecture` | `8f47a4f` | 2 fichiers, 2 insertions / 2 suppressions : une virgule ajoutee dans l'indice de l'item `45 ___ 40` de `cp-comparer-nombres.yaml`, et une forme verbale corrigee (`corris` -> `corrige`) dans `DEC-0019`. |

**Lignee verifiee avant fusion** : `git merge-base round-two s09-relecture` = tete exacte de
`round-two` avant fusion (`ea9473d`), un seul commit d'ecart, aucune derive. **Perimetre verifie par
`git diff --stat` et par lecture du diff complet**, pas sur la seule annonce de l'agent : conforme,
aucun fichier hors perimetre. Fusionne par `git merge --no-ff`, **aucun conflit**.

**Worktrees et branches nettoyes apres fusion.** Dix-huit worktrees et dix-huit branches du sprint
9 etaient encore ouverts (`git worktree list` en rendait dix-neuf lignes, le depot principal
compris) : toutes verifiees ancetres de `HEAD` par `git merge-base --is-ancestor` avant suppression,
`git worktree remove --force` puis `git branch -d` sur chacune. `git worktree list` et `git branch
-a` ne rendent plus que le depot principal et les branches anterieures a s09.

## `mvn -B clean verify`

Rejoue independamment sur l'arbre fusionne final (`f8ba436`), JDK 21 designe par `JAVA_HOME` le
temps de la commande (le fichier de toolchains du poste est enregistre sous un nom mal
orthographie et n'est donc jamais lu par Maven - voir retro, point 5) : **BUILD SUCCESS**, six
modules en SUCCESS.

| Module | Tests |
|---|---|
| domain | 43 |
| application | 7 |
| adapter-web | 178 |
| adapter-content | 63 |
| bootstrap (surefire) | 77 |
| bootstrap (failsafe) | 3 |
| **Total** | **371** |

**Methode, dans le meme paragraphe que le chiffre.** Somme de l'attribut `tests` de la balise
racine `<testsuite>` de chaque rapport XML sous `*/target/surefire-reports/` et
`*/target/failsafe-reports/` des cinq modules declares dans `<modules>` du pom agregateur, lue
apres le `clean` de cette commande - jamais un balayage racine qui verrait les worktrees encore
ouverts au moment de la mesure. **371**, conforme au chiffre annonce par `yanis-qa` et par les
quatre fiches de `nadia-prof`.

**28 regles ArchUnit, confirme par deux methodes qui se recoupent, rejouees par moi.** (1)
`git ls-files '*.java'` filtre sur `src/test/java`, motif `ArchRule <nom> =` : 30 occurrences
brutes, dont 2 dans le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest`
(chemin contenant `src/test/java` sans etre un source de module) : **30 - 2 = 28**. (2) Balayage
scope aux seuls `src/test/java` des cinq modules declares au pom (`git ls-files` filtre par
prefixe de module, `target/` exclu) : **28** directement, repartis en `bootstrap` 27 et
`adapter-web` 1 (`AssertionsWebTest`). Inchange depuis l'ouverture du sprint : `ADR-0032` porte une
clause executee en test JUnit ordinaire, pas une `ArchRule` neuve - le contenu d'un YAML charge a
l'execution n'est pas visible d'ArchUnit.

## Rapport qualite - yanis-qa

- **Fichier** : `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/rapport-qualite.md`.
- **Commit audite** : `0d3e734` (avant le lot de relecture, qui ne touche aucun test ni code de
  production - le rapport reste valable sur l'arbre final, reconcilie ci-dessus).
- **Compte de tests et de regles confirme independamment** par `yanis-qa` puis rejoue par moi :
  371 tests, 28 regles, memes methodes, meme resultat.
- **Trois pieges de comptage nommes et quantifies**, dont un fabrique par `sofia-sm` elle-meme ce
  sprint (piege 3, comptage manuel qui double les assertions de mise a l'epreuve logees dans la
  meme methode `@Test` que l'assertion de production). Recommandation : un outil de comptage
  autoritatif selon `ADR-0009` (fonction pure sur les rapports XML, enveloppe non testee qui lance
  `mvn`). Voir tableau des obstacles, point 3.
- **`ADR-0032` mute sur du materiau reel** (duplication d'un item dans `cp-comparer-nombres.yaml`,
  1 echec sur 6, restaure) : le trou de morsure d'`ADR-0023` est confirme reel et sa fermeture
  mordante sur du contenu de production, pas seulement sur ses cinq fixtures.
- **Trouvaille corrigeant les trois premieres fiches de `nadia-prof`** : les trois tests dedies de
  S9-1, S9-2, S9-3 verifient deja l'exactitude arithmetique de chaque item, contrairement a ce que
  chaque fiche affirmait alors ("aucun test ne calcule..."). Etabli par mutation (bonne reponse
  changee sur un item de chaque paquet, test rouge, restauration), pas par lecture seule.
  `nadia-prof` a corrige ses trois fiches en consequence (voir "Porte pedagogique" ci-dessous).
- **Position tranchee sur le test par paquet** : exiger un test dedie quand le paquet declare une
  operation verifiable par calcul, s'en dispenser pour un paquet purement textuel.
  `cp-problemes-additifs` est le seul paquet neuf du sprint sans test dedie, et le seul dont
  l'exactitude arithmetique n'est gardee par rien d'automatique. Voir tableau des obstacles,
  point 2.
- **Deux proprietes candidates a un garde-fou, chiffrees** : la regularite de magnitude (differee
  par `DEC-0021`) et l'atteignabilite de tous les items avec conformite des propositions au
  fichier (la moins chere des deux, de l'ordre d'une demi-journee sans code de production). Voir
  tableau des obstacles, point 4.
- **Aucun module sans test dans ce que la squad a livre** : les cinq commits de contenu touchent
  uniquement `adapter-content`, verifie par lecture des `git show --stat`. **Aucune assertion HTML
  par expression reguliere** : les quatre tests de paquets lisent des objets `Question` retournes
  par la chaine de chargement reelle.

## Confirmation ArchUnit - theo-archi

**Aucun fichier `confirmation-archunit.md` dedie n'a ete ecrit ce sprint**, a la difference de s08.
La position architecture de `theo-archi` pour s09 est portee par son `avis-faisabilite.md`
(pre-developpement, section finale : 28 regles inchangees, `ADR-0032` n'etant pas une `ArchRule`
puisqu'ArchUnit lit du bytecode et non un YAML charge a l'execution) - une prevision, pas une
confirmation posterieure au code livre. Cette prevision est confirmee sans ecart par le
developpement reel, verifiee independamment a deux reprises : par `yanis-qa` dans son rapport
qualite (section 1, deux methodes qui se recoupent) et par moi-meme ci-dessus (memes deux methodes,
memes chiffres, sur l'arbre fusionne final). Le contenu que la ceremonie exige - une position
d'architecture sur le compte de regles ArchUnit de l'increment livre - est donc couvert par la
convergence de ces trois sources et non par un document unique portant sa signature ; je le note
en retro comme une asymetrie de forme entre sprints, pas comme une ceremonie manquante puisque le
fond est atteste.

## Relecture - relecteur-pedagogique

- **Fichier** : lot fusionne ci-dessus (`s09-relecture`, `8f47a4f`), pas de fichier `relecture.md`
  distinct - la correction est portee directement dans les fichiers concernes.
- **Corrections appliquees (2 fichiers)** : une virgule ajoutee dans l'indice `45 ___ 40` de
  `cp-comparer-nombres.yaml` ("regarde les unités, 5 unités, c'est plus que 0 unité") ; une forme
  verbale corrigee dans `DEC-0019` ("je corris" -> "je corrige").
- **A refuse de reecrire une citation dans un jugement pedagogique deja rendu et date** : le
  premier verdict de `FICHE-bandit-manchot-comparer-nombres-cp.md` cite verbatim l'ancien texte de
  cet indice, avant la correction de forme. Voir tableau des obstacles, point 5.

## Porte pedagogique - nadia-prof

Quatre fiches, deux tours d'audit sur trois d'entre elles :

| Increment | Premier audit (2026-08-12) | Second audit (2026-08-12) | Ligne de matrice |
|---|---|---|---|
| `bandit-manchot-complements-10-cp` (S9-2) | **VALIDE** direct, sans reserve bloquante. | - | `couvert-valide` |
| `bandit-manchot-soustractions-cp` (S9-1) | **REFUSE** - 48 fautes de francais dans les 24 indices (`Leve`/`Lève`, `baisse-en`/`baisses-en`, un participe non accorde). Mathematique et methode des indices irreprochables. | **VALIDE SOUS RESERVE**, refus leve : les trois substitutions tenues au mot, plus une quatrieme trouvee par `marc-dev` et confirmee (`leves` -> `levés`). | `couvert-valide` |
| `bandit-manchot-problemes-additifs-cp` (S9-4) | **REFUSE** - deux enonces ("le clown", "le jardinier") ou le "en" partitif contredit la reponse attendue, qui n'est meme pas proposee. Vingt autres items bons. | **VALIDE SOUS RESERVE**, refus leve : les deux enonces corriges exactement dans la forme que `nadia-prof` avait ecrite dans son refus (`de plus`). | `couvert-valide`, **avec la reserve `DEC-0021` ouverte** (magnitude des distracteurs d'addition) |
| `bandit-manchot-comparer-nombres-cp` (S9-3) | **REFUSE** - l'indice de `56 ___ 60` affirme que 56 vient juste avant 60 (faux, c'est 59), une faussete sur exactement ce que le jeu construit. 23 autres indices et 24 comparaisons exacts. | **VALIDE SOUS RESERVE**, refus leve : le mot "juste" retire. | **reste `en cours`** - perimetre partiel pose avant tout audit, "ranger" differe par `DEC-0019`, non atteignable par cet increment quel que soit le verdict |

**Chaque refus s'est leve sur la correction bornee que `nadia-prof` avait elle-meme ecrite dans le
verdict de refus**, jamais sur une negociation ulterieure - aucune des trois corrections n'a
demande un second aller-retour de formulation. Les audits sont non degrades : jar construit et
lance depuis un worktree dedie, joue par les vraies routes HTTP. Le second tour porte sur 60
parties completes de dix manches par jeu corrige (1800 coups au total), toutes jouees
volontairement faux pour forcer l'affichage de l'indice a chaque manche - la totalite des items et
des indices des trois paquets lue a l'ecran, pas dans le fichier.

**`nadia-prof` a corrige quatre de ses propres chiffres avant de s'appuyer dessus**, dans les deux
sens : 48 fautes de francais et non 47 (un participe en bas de casse echappait a sa recherche
capitalisee, trouve par `marc-dev`) ; 9 items de comparaison tranches par les dizaines et non 10 ;
un item degenere du paquet de complements livre en `10 + ? = 10` et non `10 + ? = 0` comme on le
lui avait annonce (elle juge la forme livree, pas celle qu'on lui decrit) ; et, apres la
mutation de `yanis-qa`, la correction des trois fiches ou elle affirmait a tort qu'aucun test ne
verifiait l'exactitude arithmetique.

## Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` : la clause de production ne juge aucun indice reel. Verifie par moi dans le fichier de test - `aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet` n'entre dans sa comparaison que si `REGLE_GENERALE_NON_TEMPEREE.find()` matche, et `aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui` epingle ce compte a **zero** sur les paquets de production. Sa clause de non-vacuite affirme que la liste des **paquets** n'est pas vide, jamais qu'un **indice** a ete examine - elle garde la mauvaise population. | technique, garde-fou vide | theo-archi | **ouvert** - l'arbitrage du moment de sa correction (avant le sprint 10 ou au backlog) attend une reponse ecrite de l'utilisatrice, deja posee |
| Test dedie par paquet : `cp-problemes-additifs` est le seul paquet neuf sans test dedie, seul dont l'exactitude arithmetique n'est gardee par rien. `yanis-qa` tranche : l'exiger quand le paquet declare une operation verifiable par calcul, s'en dispenser sur un paquet purement textuel. | qualite, test | yanis-qa | **tranche par yanis-qa** en recommandation ; reste a `theo-archi` et `sofia-sm` d'en faire un critere net si le depot doit l'ecrire comme regle |
| Trois pieges de comptage nommes en deux sprints, dont un fabrique un faux ecart de sept tests ce sprint meme (comptage manuel qui double une assertion de mise a l'epreuve logee dans la meme methode `@Test` que l'assertion de production). | qualite, gouvernance de mesure | theo-archi | **ouvert** - `yanis-qa` recommande un outil de comptage autoritatif selon `ADR-0009` (fonction pure + enveloppe non testee) |
| Deux proprietes candidates a un garde-fou : la regularite de magnitude entre bonne reponse et distracteurs (differee par `DEC-0021`, story versee au backlog) ; l'atteignabilite de tous les items avec conformite des propositions servies au fichier (la moins chere des deux, environ une demi-journee sans code de production, chargeur deja mappant 1 pour 1). | technique, contenu | theo-archi | **routees au backlog** (`docs/backlog/backlog.md`) - avis de faisabilite attendu de `theo-archi`, aucun des deux gestes n'est affecte a un sprint |
| Citation devenue perimee : le premier verdict de `FICHE-bandit-manchot-comparer-nombres-cp.md` cite verbatim l'ancien texte de l'indice de `45 ___ 40`, corrige depuis par `relecteur-pedagogique`. Il a refuse de reecrire une citation dans un jugement rendu et date. | redaction, pedagogie | nadia-prof | **ouvert** - a elle de decider si une note de coherence doit etre ajoutee a sa fiche |
| Reserves de `nadia-prof` sur les quatre paquets (magnitude, `fanent` hors vocabulaire CP, metiers tous masculins, dizaines egales rares, paires de chiffres inverses absentes, trivialite des egalites `n ___ n`, `baisses-en 1 seul`, difficulte constante dans une partie - releve sur soustractions et problemes additifs, sans objet sur complements, non releve sur comparaison) | pedagogie, redaction | camille-po | **verse au backlog** (`docs/backlog/backlog.md`, section "Backlog - sprints suivants") avec le perimetre exact de chaque reserve ; non affecte a un sprint |

Rappel du gabarit : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement.
Aucun des six obstacles ci-dessus n'a d'`ADR`/`DEC` neuf a cette review, hors le routage deja acte
par `DEC-0021` pour le point 4 (magnitude) ; ils restent `ouvert` ou `route`, pas `clos`.

## Couverture programme

| Ligne de matrice | Avant s09 | Apres s09 (second audit) |
|---|---|---|
| mathematiques CP, Nombres et calculs, Calcul mental - soustractions dans la limite de 10 | `non couvert` | **`couvert-valide`** |
| mathematiques CP, Nombres et calculs, Complements a 10 | `non couvert` | **`couvert-valide`** |
| mathematiques CP, Nombres et calculs, Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | `non couvert` | **`couvert-valide`** (avec `DEC-0021` ouverte) |
| mathematiques CP, Nombres et calculs, Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`) | `non couvert` | `en cours` - "comparer" valide pedagogiquement, "ranger" differe par `DEC-0019` |

Synthese globale (`squad/programme/MATRICE-couverture.md`, 46 lignes) : **6 `couvert-valide`**
(contre 3 a la cloture de s08), **2 `en cours`** (comparer deux nombres ; francais CP graphemes/
phonemes, restee ouverte depuis s06), **38 `non couvert`**, chiffre inchange. Le pic intermediaire
du premier audit (4 `en cours` : les trois refus plus la ligne francais CP) est retombe a 2 apres
les trois levees de refus du second audit.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree par cette cloture elle-meme. `ADR-0032`, `DEC-0019`,
`DEC-0020`, `DEC-0021` sont toutes anterieures a cette review, actees pendant le sprint et deja
fusionnees. Les decisions qui m'appartiennent en tant que Scrum Master :

1. **Le lot de `relecteur-pedagogique` est fusionne apres verification de lignee et de perimetre**,
   sur `git diff --stat` puis lecture du diff complet.
2. **Dix-huit worktrees et dix-huit branches du sprint sont nettoyes**, chacun verifie ancetre de
   `HEAD` avant suppression.
3. **`mvn -B clean verify` est rejoue independamment sur l'arbre fusionne final** plutot que
   recopie sur l'annonce d'un agent : 371 tests, 28 regles, memes chiffres.
4. **Le garde-fou vide sur les indices (obstacle 1) est route a `theo-archi`**, l'arbitrage du
   calendrier de sa correction attendant une reponse de l'utilisatrice, deja posee.
5. **L'absence de `confirmation-archunit.md` dedie ce sprint n'est pas traitee comme une ceremonie
   manquante** : le contenu exige (une position d'architecture sur le compte de regles) est
   couvert par la convergence de l'avis de faisabilite de `theo-archi`, du rapport de `yanis-qa` et
   de ma propre reverification. Point porte en retro.
