# Review du sprint 21 - fermer le pli avant le volume

- **Horodatage de debut** : 2026-08-27 15:45 +0200 (`planning.md`)
- **Horodatage de fin** : 2026-08-28 12:30 +0200 (cette ceremonie)
- **Duree effective** : environ 20 h 45 min
- **Increment audite** : `round-two` a `9f2dc5a`, `git status` propre au moment de cette review

## Increment livre

Le pli se ferme sur le catalogue entier cote CP (40 des 56 items de l'echantillon initial degrades
par la forme et le contenu, `REP-8`) sans promettre les quatre items qui n'ont aucun levier connu ;
trois paquets neufs de volume entrent au catalogue apres arbitrage prealable de `nadia-prof`
(`cp-syllabes`, `cp-mot-manquant`, `cp-comparer`, tous trois `VALIDE SOUS RESERVE`) ; un garde-fou
CSS qui violait `ADR-0017` a travers trois CI vertes est corrige, et le correctif de son propre
correctif l'est aussi ; le cliquet du contrat de forme (`ADR-0063`) est reverifie exact et sa borne
d'echantillonnage, ecrite a la main a 60, devient derivee du paquet ; et une regle typographique
neuve (`TYP-1`) etend l'espace avant un signe double a tout le catalogue, sur mesure et non sur
principe. La couverture passe de 15/43 (34,9 %) a 18/43 (41,9 %). Le catalogue passe de 18 a 21
entrees.

## Lots fusionnes

`caad2f0` (ouverture) ; `1219336` (fusion lots 1-3 : nadia, ines, lea, 694 tests) ; `60f71b5`
(`S21-11`, paquet `cp-comparer`) ; `9ace409` (fusion seconde vague : theo, ines+marc, marc-2,
lea-2, sofia, camille, nadia) ; `8e21613` (fusion correctif porte rouge, `s21-theo-2`) ; `8ecf44d`
(fusion finale : nadia-2, lea-3, camille-2, Done pedagogique complet, 18/43, 722 tests) ; `6c06c23`
(rapport qualite de `yanis-qa`) ; `10f5b4e` (relecture pedagogique) ; puis, a cette cloture,
`bb2213a` (fusion lot `s21-theo-3` : `ADR-0063` amende, correctif du cliquet) et `970fdcd` (fusion
lot `s21-nadia-3` : comptage de refus corrige, `TYP-1` tranchee), et `9f2dc5a` (correctif de
`planning.md`, defaut route par le relecteur).

Provenance verifiee avant chaque fusion : `s21-theo-3` (`c613ee2`) et `s21-nadia-3` (`27b3ff0`,
`a78882c`) descendent toutes deux de `10f5b4e`, tete de branche au moment de leur provisionnement.
Aucun fichier commun entre les deux lots ; les deux fusions se sont faites sans conflit.

## Porte technique - rapport de `yanis-qa`

`rapport-qualite.md` (`6c06c23`) : `bash outils/mvn-jdk21.sh -B clean verify` sur `8ecf44d`,
reacteur complet, JDK 21.0.10 (toolchain) - **BUILD SUCCESS, 722 tests, 0 echec** (49 `domain` + 13
`application` + 332 `adapter-web` + 224 `adapter-content` + 98 `bootstrap` surefire + 6 `bootstrap`
failsafe). Delta contre la base confirmee de s20 (694) : **+28**. **30 regles ArchUnit reelles,
inchange par le sprint.** Catalogue a 21 entrees, matrice a 18/43 (41,9 %), verifies contre leurs
fichiers source.

**Six instruments de mesure ou garde-fous trouves defaillants ce sprint, aucun par lecture d'un
rapport vert** (section 3 du rapport) : un garde-fou CSS (`LActionNeDeclarePasMoinsQueLaSortieTest`)
qui lisait la premiere declaration de `padding` au lieu de la derniere, laissant `ADR-0017` viole a
travers trois CI vertes ; son propre correctif qui a casse un troisieme appelant, visible
seulement sur l'arbre fusionne ; `campagne-budget-vertical.sh` qui ecrivait "campagne terminee" en
ayant perdu 8 jeux sur 17, faute de verifier le code de sortie de chaque mesure ; deux outils de
mesure du pli (`mesurer-candidats-rep-8.py`, `mesurer-un-contenu-candidat.py`) qui perdaient
silencieusement leur condition d'injection entre deux parties ; une population d'echantillon (56
sur un echantillon de 167 vus sur 428) lue comme un inventaire pendant l'essentiel du sprint ; et
un `ADR` (`ADR-0029`) cite a tort dans la Javadoc de `PaquetCm1ReperesTest`. Les cinq premiers sont
corriges dans ce sprint ; le cinquieme (population d'echantillon) est corrige a cette review par
`theo-archi` (`ADR-0063`) et par moi (`planning.md`), voir plus bas.

Une septieme observation, trouvee par `yanis-qa` en confrontant un chiffre de `nadia-prof` a sa
source primaire : sa conclusion affirmait "les quatre refus prononces ce sprint" alors que les
fiches de conformite n'en portent que trois pour s21 (un quatrieme, `cm1-reperes`, date de s20).
Signale, non tranche par lui - corrige par `nadia-prof` elle-meme dans `27b3ff0`, voir plus bas.

**Fait dur du sprint, ecrit par `yanis-qa`** : la porte technique verte a 722 tests n'a vu aucun des
trois refus pedagogiques prononces ce sprint. Les trois motifs etaient tous des motifs de contenu
(compte de syllabes errone, reponse defendable refusee, distracteur hors champ) et `mvn verify`
etait vert a chacun d'eux, avant comme apres correction. Sur les 27 conditions prealables des trois
paquets neufs, 3 seulement sont partiellement couvertes par un test - il ne le compte pas comme un
manque de garde-fou technique : `ADR-0028` et le precedent de s20 etablissent qu'un classifieur
heuristique y serait pire qu'une absence de garde.

## Correctif de `theo-archi` sur son propre garde-fou (`s21-theo-3`, `c613ee2`)

En amendant `ADR-0063` pour porter la population de son chiffre de contexte, `theo-archi` a
verifie son propre cliquet plutot que de le supposer exact, et y a trouve un defaut reel :
`AuPlusTroisPropositionsParItemTest` bornait ses tirages a **60 items par paquet, valeur ecrite a
la main**, alors que `cm1-tables-multiplication` en declare **99** - **39 items n'etaient examines
par personne, en silence**. Recomptage independant sur les 21 fichiers de contenu : 478 items, 33
depassements, toujours les deux memes paquets en cause (`cm1-conjugaison-present` 25,
`cm1-accords-groupe-nominal` 8) - aucun defaut ne s'y cachait, mais **la clause portait sur un
echantillon sans le dire**, exactement le defaut que ce sprint a instruit six fois par ailleurs.
Corrige : la borne est desormais derivee du paquet. Morsure verifiee par mutation (borne ramenee a
60 : 5 tests sur 7 rouges).

**Dette voisine nommee, non corrigee** : `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` porte
la meme borne fixe de 60. Verse au tableau des obstacles ci-dessous, ouvert.

## Corrections du `relecteur-pedagogique`

`relecture-s21.md` (`10f5b4e`) : 34 commits relus, 142 fichiers, deux references de section
fausses corrigees dans les tables de correspondance de deux fiches de conformite (`FICHE-bandit-
manchot-mot-manquant-cp.md`, `FICHE-bandit-manchot-comparer-cp.md`, redaction pure). Trois points
routes, aucun corrige par le relecteur lui-meme :

1. `ADR-0063`, section Contexte, citait "56 items du catalogue" sans dire que c'est un echantillon
   de 167 vus sur 428 - **route a `theo-archi`, ferme dans `c613ee2`**.
2. `planning.md` citait "56 items" et "26 sur 56" a six reprises, jamais qualifie d'echantillon,
   alors que le qualificatif existait deja dans `DEC-0051` avant sa premiere ecriture - **route a
   `sofia-sm`, ferme dans `9f2dc5a`** (amendement date, sans reecrire, population reelle posee cote
   a cote : 478 items sur 478, 114 sous le pli avant s21, 64 apres).
3. L'absence d'espace insecable devant `?` sur les 20 occurrences de `cp-comparer.yaml` et
   `catalogue.yaml`, alors que `cm1-reperes.yaml` (s20, `REP-6`) est seul a la porter - **route a
   `nadia-prof` avec `theo-archi` en copie, ferme par `TYP-1`** (`a78882c`) : la regle s'etend a
   tout le catalogue, application bornee et ordonnee par l'effet mesure, `REP-6` recoit une portee
   locale ecrite sans etre annulee.

Les trois sont clos a cette review.

## Tableau des obstacles

| # | Obstacle | Categorie | Destinataire | Etat |
|---|---|---|---|---|
| 1 | `S21-1` - correctif outillage de mesure du pli | Technique | `theo-archi` | **CLOS**, livree et confirmee (`52f0092`) |
| 2 | `S21-2` - `REP-8`, leviers de forme (marge `.score`, rembourrage `.proposition`) | Pedagogie, forme | `marc-dev`, `ines-design` | **CLOS**, livree et verte, 26 des 56 items de l'echantillon initial fermes (confirme sur l'arbre fusionne) |
| 3 | `S21-3` - reformulation de 12 enonces sur une ligne | Pedagogie | `nadia-prof` | **CLOS, recadree.** `lea-dev` a mesure que les 12 enonces tenaient deja sur une ligne ; `nadia-prof` refute sa propre mesure et reoriente vers `S21-11`/`REP-11` a `REP-14` (largeur des libelles, pas la casse des enonces) |
| 4 | `S21-4` - correction Javadoc `PaquetCm1ReperesTest` (`ADR-0029` mal cite) | Technique, redaction | `lea-dev` | **CLOS**, livree (`74e8f4a`) |
| 5 | `S21-5` - `ADR` encodant la regle 1 du contrat de forme | Technique | `theo-archi` | **CLOS**, `ADR-0063` livree (`1e64551`), amendee a cette cloture (`c613ee2`) |
| 6 | `S21-6` - amendement date des douze `confirmation-archunit.md` (chiffre `21.0.10` exact, methode fausse) | Technique, redaction | `theo-archi` | **CLOS**, livree (`c5398c8`) |
| 7 | `S21-7` - correctif de `mesurer-candidats-rep-8.py` (regle CSS perdue a la deuxieme partie) | Qualite, outillage | `ines-design` | **CLOS**, livree (`3daf481`) |
| 8 | `S21-8` - arbitrage prealable de `nadia-prof` sur les trois paquets de volume | Pedagogie | `nadia-prof` | **CLOS**, livree |
| 9 | `S21-9` - volume, `cp-syllabes` | Pedagogie | `marc-dev`, `nadia-prof` | **CLOS**, Done pedagogique `VALIDE SOUS RESERVE`. Verdict initial `REFUSE` sur `CHEMINEE`, motif tombe par arbitrage utilisatrice du 2026-08-28 (`DEC-0052`), `SEG-1` clause (c) amendee |
| 10 | `S21-10` - volume, `cp-mot-manquant` | Pedagogie | `lea-dev`, `nadia-prof` | **CLOS**, Done pedagogique `VALIDE SOUS RESERVE` au second reaudit (`4543d8d`, `2d2cea9`), apres deux `REFUSE` le meme jour |
| 11 | `S21-11` - volume, `cp-comparer` | Pedagogie | `sofia-sm` (dev) | **CLOS**, Done pedagogique `VALIDE SOUS RESERVE` des l'audit initial |
| 12 | `LActionNeDeclarePasMoinsQueLaSortieTest` restait vert en lisant la premiere declaration de `padding` au lieu de la derniere - `ADR-0017` viole a travers trois CI vertes | Technique, garde-fou | `theo-archi` | **CLOS** (`S21-12`, `1129051`) : lecteur corrige, point de passage rendu bruyant |
| 13 | Le correctif du point 12 a lui-meme casse un troisieme appelant, visible seulement sur l'arbre fusionne complet | Technique, garde-fou | `theo-archi` | **CLOS** (`s21-theo-2`, `369e690` fusionne dans `8e21613`) : parametre de position retire de `valeurDeclaree`, 26 appels sur 12 classes convertis par le compilateur |
| 14 | `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (module `bootstrap`) porte son propre lecteur prive de feuille, meme forme de trou (premiere occurrence, pas la declaration gagnante) | Technique, garde-fou | `theo-archi` | **OUVERT, non corrige par choix.** Vert aujourd'hui, aucune regle du depot ne declare deux fois `animation-delay`. Declencheur ecrit : une regle qui le fait, ou un troisieme module qui lit des feuilles |
| 15 | `AuPlusTroisPropositionsParItemTest` bornait ses tirages a 60 par paquet (valeur ecrite a la main) alors que `cm1-tables-multiplication` en declare 99 - 39 items non examines, en silence | Technique, garde-fou | `theo-archi` | **CLOS a cette review** (`c613ee2`) : borne desormais derivee du paquet, morsure verifiee par mutation. Recomptage independant : 478 items, 33 depassements, aucun defaut cache |
| 16 | Dette voisine : `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` porte la meme borne fixe de 60 | Technique, garde-fou | `theo-archi` | **OUVERT**, nomme et non corrige a cette cloture |
| 17 | Population d'echantillon (56 items sur un echantillon de 167 vus sur 428) lue comme un inventaire dans `ADR-0063` et `planning.md`, sans le dire | Redaction, technique | `theo-archi`, `sofia-sm` | **CLOS a cette review** : `ADR-0063` amendee (`c613ee2`), `planning.md` amende (`9f2dc5a`), route par le relecteur (`10f5b4e`) |
| 18 | Comptage de refus errone dans la conclusion de `nadia-prof` ("quatre refus prononces ce sprint" au lieu de trois) | Pedagogie, redaction | `nadia-prof` | **CLOS** (`27b3ff0`) : trois refus de s21, quatre depuis s20 en comptant `cm1-reperes` (s20) - les deux comptes donnes avec leur perimetre |
| 19 | Absence d'espace insecable devant `?` sur `cp-comparer.yaml` et `catalogue.yaml` (20 occurrences), alors que `cm1-reperes.yaml` (`REP-6`, s20) est seule a la porter | Redaction, typographie | `nadia-prof` | **CLOS** (`a78882c`) : `TYP-1` tranchee, la regle s'etend a tout le catalogue, application bornee et ordonnee ; `REP-6` recoit une portee locale ecrite |
| 20 | `camille-po` refuse d'amender `DEC-0045` section 3 (retrait immediat sur `REFUSE`) sur les deux cas mesures (`cm1-reperes` s20, `cp-mot-manquant` s21) | Produit, gouvernance | `camille-po` | **CLOS pour ce sprint, verse a la retro.** Motif : ajuster une regle sur l'unique cas ou elle a bien fini reproduirait le defaut que `nadia-prof` vient de nommer contre elle-meme. Les deux couts (2 editions de catalogue + 1 test casse a s20 ; une fenetre d'exposition non materialisee a s21) sont mesures dans `DEC-0052` section 8 |
| 21 | 4 items sans levier connu (`bandit-manchot-grands-nombres-cm1` x2, `bandit-manchot-reperes-cm1` x2) | Pedagogie, produit | `camille-po` | **OUVERT, hors perimetre de s21** |
| 22 | `REP-8` reste ouverte sur CM1 : 64 items sous le pli sur le catalogue entier (478 items), concentres sur 4 jeux (`accord-sujet-verbe-cm1`, `reperes-cm1`, `grands-nombres-cm1`, `accords-groupe-nominal-cm1`) | Pedagogie, produit | `camille-po` | **OUVERT.** Volume de contenu a retravailler, pas un garde-fou technique manquant |
| 23 | `outils/mvn-jdk21.sh` pose `JAVA_HOME` depuis un fichier de toolchains different de celui que Maven lit reellement | Technique | `theo-archi` | **OUVERT, hors perimetre de s21**, sans consequence sur la CI ni sur le bytecode livre |
| 24 | Divergence non reconciliee entre deux releves de `nadia-prof` sur `cm1-reperes` (14/16 vs 16/16 items au-dessus du pli, outils differents) | Qualite, mesure | `nadia-prof` | **OUVERT, pour memoire**, ne bouge aucun verdict |

## Avis de `nadia-prof` et Done pedagogique

`avis-nadia-done-pedagogique-s21.md`, verdicts prononces le 2026-08-28, arbre audite `8e21613`,
increment construit, servi et joue depuis le worktree `s21-nadia-2` :

| Jeu | Story | Verdict | Motif, en une ligne |
|---|---|---|---|
| `bandit-manchot-syllabes-cp` | `S21-9` | **VALIDE SOUS RESERVE** *(revise le 2026-08-28 apres arbitrage utilisatrice ; d'abord REFUSE le meme jour)* | Refus prononce sur l'item `CHEMINEE`, leve apres arbitrage de l'utilisatrice ("cheminee vaut bien 3 syllabes") ; `SEG-1` amendee |
| `bandit-manchot-mot-manquant-cp` | `S21-10` | **VALIDE SOUS RESERVE** *(second reaudit, `4543d8d` ; REFUSE deux fois avant)* | Les trois items corriges et verifies proposition par proposition ; l'item 3 porte enfin le trait discriminant |
| `bandit-manchot-comparer-cp` | `S21-11` | **VALIDE SOUS RESERVE** | Dix conditions tenues, 18 items sur 18 decidables, aucune erreur factuelle |

**Trois valides sous reserve, aucun refus subsistant.** `camille-po` fait passer les lignes 89, 485
et 496 de `MATRICE-couverture.md` a `couvert-valide` : 15/43 (34,9 %) devient 18/43 (41,9 %).

**Ce que le sprint retient de son propre lot, contre elle-meme.** Deux des trois refus de s21 et
trois des quatre depuis s20 ont leur cause racine dans ses propres specifications - `SEG-1`
(quatre fois trop large, mesure sur les 650 mots du corpus CP : 39 cas de schwa attrapes, 30 a
tort), le triplet `crayon`/`feutre`/`pinceau` qu'elle avait qualifie d'"evident", et (a s20) des
libelles "dits courts" qui faisaient 34 caracteres. Le defaut qu'elle nomme : elle ecrit l'exemple
qui illustre la propriete, jamais la propriete elle-meme. Elle ne demande aucun garde-fou
supplementaire (`ADR-0028` et le precedent de s20 tranchent qu'un classifieur heuristique serait
pire qu'une absence de garde) : la contrepartie assumee est que 24 des 27 conditions prealables
des trois paquets neufs n'ont pour garde qu'elle-meme.

## Les deux Done

- **Technique : vert.** `bash outils/mvn-jdk21.sh -B clean verify` sur `9f2dc5a`, reacteur
  complet, le 2026-08-28 : **BUILD SUCCESS, 722 tests, 0 echec** (49 `domain` + 13 `application` +
  332 `adapter-web` + 224 `adapter-content` + 98 `bootstrap` surefire + 6 `bootstrap` failsafe),
  mesure independamment par moi, concordante avec `yanis-qa` (`8ecf44d`) et `theo-archi`
  (`c613ee2`). **30 regles ArchUnit, inchange par le sprint.** Aucun test ajoute par les deux
  derniers lots fusionnes : `ADR-0063` amendee lit plus d'items, elle n'en ajoute aucun.

  **Note sur l'etat de poussee.** `origin/round-two` reste 76 commits derriere `HEAD`. Comme
  etabli aux clotures precedentes, ce Done technique est une mesure locale, concordante sur trois
  executions independantes, pas un verdict de la CI sur cet arbre. Je ne pousse rien : la poussee
  reste la main de l'utilisatrice.

- **Pedagogique : trois verdicts, aucun refus ouvert.** `bandit-manchot-syllabes-cp`,
  `bandit-manchot-mot-manquant-cp` et `bandit-manchot-comparer-cp` sont **VALIDE SOUS RESERVE**.
  Couverture : 15/43 (34,9 %) en debut de sprint -> 18/43 (41,9 %) a la cloture, trois lignes
  gagnees et auditees dans le meme sprint.

## Decisions prises

Aucune decision d'architecture, de produit ou de pedagogie n'est prise par cette ceremonie : elle
agrege les decisions deja tranchees pendant le sprint (`DEC-0051`, `DEC-0052`, `ADR-0063`,
detaillees dans `RAPPORT.md`). Le seul geste que je prends moi-meme est un amendement date, jamais
une reecriture : `planning.md` (obstacle 17 ci-dessus), corrigeant l'omission que le
relecteur-pedagogique m'a routee.
