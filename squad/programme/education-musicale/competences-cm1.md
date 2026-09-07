# Référentiel de compétences - Éducation musicale - CM1 (cycle 3)

> Écrit par `nadia-prof` le 2026-09-03, sur arbitrage de l'utilisatrice du même jour (`DEC-0074`),
> au même grain et dans la même forme que
> `squad/programme/histoire-geographie/competences-cm1.md`, écrit le 2026-09-02. **Socle réaliste,
> non exhaustif et non officiel** : à compléter et à faire valider par l'utilisatrice au fil des
> sprints, en lien avec `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des
> programmes officiels de l'Éducation nationale.
>
> **Audit dégradé, et je le déclare** : je n'ai joué aucun incrément, parce qu'il n'en existe aucun
> sur cette matière. Ce document **ne prononce aucun Done pédagogique**, **n'écrit pas la matrice**
> (`camille-po`) et **ne priorise pas** (l'utilisatrice).
>
> L'éducation musicale est **un enseignement distinct** des arts plastiques et de l'histoire des
> arts (`nadia-prof`, arbitrage préalable de s27, section 2.3). Le programme de cycle 3 s'organise
> en quatre champs de compétences : chanter et interpréter ; écouter, comparer et commenter ;
> explorer, imaginer et créer ; échanger, partager, argumenter et débattre.
>
> **Ce fichier est celui dont le résultat est le plus dur, et il est écrit sans être adouci : les
> dix lignes sont `non couvrable en l'etat`, et cette matière n'apporte aucune ligne au
> dénominateur atteignable.** Les dix lignes sont écrites quand même, entièrement. Une matrice qui
> n'écrirait que les lignes jouables ferait disparaître un enseignement entier du programme sans
> que rien ne le signale - c'est le même motif qui m'a fait écrire les six lignes d'anglais CP le
> 2026-09-02, dont cinq tombaient.
>
> **Codes de ligne ajoutés le 2026-09-07 (`camille-po`, `DEC-0081`), conformément à `ADR-0081`.** Ce
> référentiel n'était codé par aucun préfixe : aucun paquet ne le citait avant ce jour. La
> numérotation suit l'ordre de lecture des quatre domaines - `MUS-CM1-01` à `MUS-CM1-10`. Elle ne
> porte que sur les dix lignes de compétence : les deux puces qui distinguent plus bas les natures
> réversible et irréversible ne sont pas des lignes de référentiel et ne reçoivent aucun code. Aucun
> intitulé n'est modifié.

## Chanter et interpréter

- [MUS-CM1-01] Chanter un chant mémorisé en respectant la justesse, le rythme et les nuances.
- [MUS-CM1-02] Interpréter un chant à plusieurs voix ou en canon, en tenant sa partie dans un ensemble.
- [MUS-CM1-03] Mobiliser son corps et sa voix pour interpréter (posture, respiration, articulation).

## Écouter, comparer et commenter

- [MUS-CM1-04] Décrire et comparer des éléments sonores d'une œuvre écoutée (timbre, hauteur, durée, intensité, tempo).
- [MUS-CM1-05] Repérer l'organisation d'une œuvre écoutée (thème, couplet et refrain, répétition, contraste).
- [MUS-CM1-06] Identifier la famille et le timbre d'un instrument entendu.
- [MUS-CM1-07] Situer une œuvre écoutée dans le temps, dans une aire géographique ou dans un genre musical.

## Explorer, imaginer et créer

- [MUS-CM1-08] Imaginer et réaliser une production sonore avec sa voix, son corps ou des objets sonores.
- [MUS-CM1-09] Utiliser un codage simple pour représenter une organisation sonore, et la réaliser.

## Échanger, partager, argumenter et débattre

- [MUS-CM1-10] Exprimer et justifier un avis sur une musique écoutée, et écouter celui des autres.

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit, tels qu'ils existent aujourd'hui et pas tels qu'on les imagine** : la
mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**, le **palier 1 visuel** - une
image fixe, des propositions en texte (`ADR-0073`). **Aucun son. Aucun geste de tracé. Aucune
persistance** (`DEC-0028`, `DEC-0046`, `DEC-0066`).

**Le fait qui gouverne ce fichier tient en trois mots : aucun son.** C'est une contrainte
fondatrice du produit, écrite dans `CLAUDE.md`, et l'objet même de cette matière est le son.
Aucune des dix lignes ne survit.

**Deux natures, et la distinction n'est pas cosmétique** :

- **réversible** - une décision de l'utilisatrice ouvrant le son au produit rouvrirait la ligne,
  qui redeviendrait alors `non couvert` et rentrerait au dénominateur. Cinq lignes ;
- **irréversible** - même avec le son, la ligne resterait fermée, parce que ce qui manque est un
  **adulte qui écoute l'enfant** (`ADR-0008`). Cinq lignes.

**Et je reconduis ici, sans une virgule de changement, mon refus d'avance de tout dispositif qui
vérifierait ce que l'enfant ou l'adulte prononce** (`CLAUDE.md`, section *L'enfant de CP n'est
jamais seul*). La prémisse de l'adulte accompagnant ne sauve aucune de ces lignes : elle ne fournit
ni oreille ni verdict, et l'utilisatrice a explicitement écarté son extension le 2026-09-01.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Chanter en respectant justesse, rythme, nuances | **`non couvrable en l'etat`**, nature **irréversible** | Deux motifs autonomes. **Contrainte fondatrice** : aucun son, donc aucun modèle à imiter. **Jugement humain sur une production libre** (`ADR-0008`) : la justesse d'un enfant qui chante ne se juge qu'à l'oreille. C'est le second motif qui donne la nature - le son rouvrirait le modèle, il ne fournirait pas l'oreille |
| 2 | Interpréter à plusieurs voix, tenir sa partie | **`non couvrable en l'etat`**, nature **irréversible** | Mêmes motifs, plus un troisième : la ligne suppose **un ensemble**, donc d'autres enfants. Rien de ce que ce produit peut construire ne le fournit |
| 3 | Mobiliser corps et voix (posture, respiration) | **`non couvrable en l'etat`**, nature **irréversible** | Geste corporel, jugé par un adulte qui regarde et écoute |
| 4 | Décrire et comparer des éléments sonores | **`non couvrable en l'etat`**, nature **réversible** | **Contrainte fondatrice, aucun son.** La ligne dit *œuvre écoutée* : sans l'écoute, il n'y a rien à décrire. **La condition exacte qui la rouvrirait** : une décision de l'utilisatrice ouvrant la diffusion d'un son par le produit. Ce jour-là, la ligne redevient `non couvert` et rentre au dénominateur |
| 5 | Repérer l'organisation d'une œuvre écoutée | **`non couvrable en l'etat`**, nature **réversible** | Même motif et même condition de réouverture |
| 6 | Identifier la famille et le timbre d'un instrument | **`non couvrable en l'etat`**, nature **réversible** | **Et je nomme la contrefaçon, parce qu'elle est la plus tentante du dépôt** : montrer l'image d'un violon et demander son nom **n'est pas** identifier un timbre. En effaçant le son, la réponse reste trouvable - c'est exactement le test de `CON-1`, et l'item ne testerait alors qu'une reconnaissance visuelle. **Je la refuse d'avance** |
| 7 | Situer une œuvre écoutée dans le temps, l'espace, un genre | **`non couvrable en l'etat`**, nature **réversible** | Même motif. **Recouvrement à ne pas double-compter** : situer une œuvre **connue par son titre**, sans écoute, est une compétence d'**histoire des arts** et elle y a déjà sa ligne. Ici la ligne dit *écoutée*, et c'est l'écoute qui manque |
| 8 | Imaginer et réaliser une production sonore | **`non couvrable en l'etat`**, nature **irréversible** | Production libre, jugée par un humain (`ADR-0008`), et le produit ne recueille aucun son |
| 9 | Utiliser un codage simple d'une organisation sonore | **`non couvrable en l'etat`**, nature **réversible** | Le codage est une **représentation d'un son**. Faire apparier deux motifs graphiques sans jamais les entendre n'enseigne rien de musical : c'est un exercice de motifs déguisé en musique. La ligne dit *et la réaliser*, ce que rien ne permet |
| 10 | Exprimer et justifier un avis sur une musique | **`non couvrable en l'etat`**, nature **irréversible** | **Jugement humain sur une production libre** (`ADR-0008`), et compétence **sans bonne réponse** : `ART-2` s'applique à l'identique. Une mécanique de choix sanctionnerait le goût d'un enfant |

### Bilan chiffré - éducation musicale CM1

| | Nombre |
|---|---|
| Lignes du référentiel | **10** |
| dont `non couvert` | **0** |
| dont `non couvrable en l'etat` | **10** |
| dont nature **réversible** (une décision sur le son les rouvre) | **5** (lignes 4, 5, 6, 7, 9) |
| dont nature **irréversible** | **5** (lignes 1, 2, 3, 8, 10) |
| **Entrent au dénominateur atteignable** | **0** |

**Zéro ligne sur dix. C'est un résultat, pas un échec, et je refuse de l'adoucir en trouvant une
ligne jouable qui ne le serait pas.** Deux contrefaçons me tendaient les bras et je les ai refusées
par écrit : reconnaître un instrument à l'image (ligne 6) et apparier des codages muets (ligne 9).
Chacune aurait fait entrer une ligne au dénominateur et l'aurait fait verdir, **sans qu'un enfant
ait entendu une note**.

**Ce que ce zéro dit à l'utilisatrice**, et c'est le seul fait de ce fichier qui appelle une
décision d'elle : **l'éducation musicale est la matière dont le sort dépend entièrement d'un
arbitrage sur le son.** Cinq des dix lignes se rouvriraient le jour où le produit diffuserait un
son - ce qui ne demande aucune captation, aucun micro et aucun jugement de ce que l'enfant produit,
seulement une **diffusion**. Les cinq autres resteraient fermées quoi qu'il arrive.

**Ce dont je reste la seule garde** : le refus des deux contrefaçons ci-dessus. Aucune CI ne verra
jamais qu'un paquet d'éducation musicale ne fait pas entendre de musique.
