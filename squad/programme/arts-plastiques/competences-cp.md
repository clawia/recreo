# Référentiel de compétences - Arts plastiques - CP (cycle 2)

> Écrit par `nadia-prof` le 2026-09-03, sur arbitrage de l'utilisatrice du même jour (`DEC-0074`),
> au même grain et dans la même forme que
> `squad/programme/questionner-le-monde/competences-cp.md`, écrit le 2026-09-02 - une compétence
> par ligne, un verbe en tête, bornée par sa parenthèse. **Socle réaliste, non exhaustif et non
> officiel** : à compléter et à faire valider par l'utilisatrice au fil des sprints, en lien avec
> `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des programmes officiels de
> l'Éducation nationale.
>
> **Audit dégradé, et je le déclare** : je n'ai joué aucun incrément, parce qu'il n'en existe aucun
> sur cette matière. Ce document **ne prononce aucun Done pédagogique**, **n'écrit pas la matrice**
> (`camille-po`) et **ne priorise pas** (l'utilisatrice).
>
> Les arts plastiques sont, avec l'éducation musicale, l'un des **deux** enseignements artistiques
> du cycle 2 : ce sont deux chapitres de programme distincts, avec leurs propres attendus, et ils ne
> se fusionnent pas en une seule section "arts" (`nadia-prof`, arbitrage préalable de s27, section
> 2.3). L'*histoire des arts* est un troisième enseignement, propre au **cycle 3** : il n'existe pas
> au cycle 2 et ne doit recevoir aucune ligne CP.
>
> **Codes de ligne ajoutés le 2026-09-07 (`camille-po`, `DEC-0081`), conformément à `ADR-0081`.** Ce
> référentiel n'était codé par aucun préfixe : aucun paquet ne le citait avant ce jour. La
> numérotation suit l'ordre de lecture des quatre domaines - `ART-CP-01` à `ART-CP-11`. Aucun
> intitulé n'est modifié.

## La représentation du monde

- [ART-CP-01] Représenter le monde environnant ou donner forme à son imaginaire en explorant plusieurs domaines (dessin, peinture, collage, modelage).
- [ART-CP-02] Employer divers outils, supports et matériaux pour représenter un objet, un paysage ou un personnage.
- [ART-CP-03] Observer les effets produits par les gestes et les outils sur la matière et les nommer (frotter, gratter, tracer, superposer).

## L'expression des émotions

- [ART-CP-04] Exprimer une émotion ou une sensation par le choix des couleurs, des formes et des matières.
- [ART-CP-05] Reconnaître et nommer les couleurs, distinguer les couleurs primaires de celles obtenues par mélange.
- [ART-CP-06] Dire ce que l'on ressent devant une image ou une œuvre et écouter ce que d'autres en disent.

## La narration et le témoignage par les images

- [ART-CP-07] Raconter une histoire ou un événement par une suite d'images.
- [ART-CP-08] Transformer une image existante (découpage, ajout, association) pour en modifier le sens.

## Se repérer dans les domaines liés aux arts plastiques

- [ART-CP-09] Décrire une œuvre : dire ce qu'on y voit (sujet, couleurs, formes, matériaux).
- [ART-CP-10] Reconnaître quelques œuvres déjà rencontrées et les rapprocher les unes des autres.
- [ART-CP-11] Nommer quelques outils, supports et gestes du travail plastique (pinceau, craie, argile, esquisse).

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit, tels qu'ils existent aujourd'hui et pas tels qu'on les imagine** : la
mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**, le **palier 1 visuel** - une
image fixe, des propositions en texte (`ADR-0073`). **Aucun son. Aucun geste de tracé. Aucune
persistance** (`DEC-0028`, `DEC-0046`, `DEC-0066`). Le **palier 2** - désigner un lieu ou un objet
sur une image - n'existe pas : il est refusé au palier 1 par `V1` (2026-08-24), il est
**constructible**, et `theo-archi` en écrit l'ADR en s28. Une ligne qui l'attend est `non couvert`,
**jamais** `non couvrable en l'etat` : la distinction change le dénominateur, et je l'ai déjà
défendue le 2026-09-02.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Représenter le monde / son imaginaire | **`non couvrable en l'etat`**, nature **irréversible** | Production plastique. Deux motifs autonomes : aucun geste de tracé n'est fourni par ce produit (contrainte fondatrice, `DEC-0046`, `DEC-0066`), et une production libre ne se juge que par un humain (`ADR-0008`). Même famille que *rédiger* et *copier*. Ni un choix ni une saisie ne la produisent |
| 2 | Employer outils, supports et matériaux | **`non couvrable en l'etat`**, nature **irréversible** | Geste matériel sur une matière physique. Aucune mécanique de ce produit ne l'ouvrira, et une mécanique qui simulerait le pinceau ne ferait pas manipuler l'argile |
| 3 | Observer les effets des gestes sur la matière et les nommer | **`non couvrable en l'etat`**, nature **irréversible** | L'objet observé est **la production de l'enfant lui-même** : sans production, il n'y a rien à observer. **Recouvrement à ne pas double-compter** : la moitié purement lexicale (nommer *frotter*, *gratter*) est déjà portée par la ligne 11 ; la compter ici ferait entrer deux fois la même chose au dénominateur |
| 4 | Exprimer une émotion par couleurs, formes, matières | **`non couvrable en l'etat`**, nature **irréversible** | Production libre, jugement humain (`ADR-0008`) |
| 5 | Reconnaître et nommer les couleurs, primaires et mélanges | **jouable** au choix textuel, **sous `ART-1`** | Le mélange des couleurs est une **connaissance** : *"Quelle couleur obtient-on en mélangeant le bleu et le jaune ?"* se pose en trois propositions écrites, se vérifie, et ne demande à l'enfant de percevoir aucune couleur |
| 6 | Dire ce que l'on ressent devant une œuvre | **`non couvrable en l'etat`**, nature **irréversible** | **Cette compétence n'a pas de bonne réponse, et c'est le motif** : une mécanique dont le contrat est *une bonne réponse parmi trois* en fabriquerait une. Sanctionner le ressenti d'un enfant de six ans devant une image est l'inverse exact de ce que la ligne demande. Même famille que `HIS-2`, portée à l'extrême |
| 7 | Raconter par une suite d'images | **`non couvrable en l'etat`**, nature **irréversible** | Production plastique et narrative. Ordonner des images déjà faites serait une autre compétence, et elle ne se joue pas non plus : elle demanderait la manipulation d'une collection (palier 3), refusée par `V3` |
| 8 | Transformer une image existante | **`non couvrable en l'etat`**, nature **irréversible** | Production plastique, mêmes deux motifs que la ligne 1 |
| 9 | Décrire une œuvre | **`non couvert`** - palier 1 **illustré**, image d'œuvre requise | La forme existe : image fixe, propositions en texte, `ADR-0073`. **Ce qui manque est l'image**, et le produit ne sait dessiner que des tracés géométriques - vingt-cinq illustrations, toutes des polygones, cercles et chemins. **Un item textuel qui décrirait l'œuvre dans l'énoncé serait une contrefaçon** (`CON-1`) : décrire une œuvre qu'on ne voit pas n'est pas décrire une œuvre. `REC-1` s'applique. **Signalé à `camille-po` et non tranché par moi** : reproduire une œuvre existante pose une question de droits qui n'est pas de ma compétence |
| 10 | Reconnaître des œuvres et les rapprocher | **`non couvert`** - même motif que la ligne 9 | *"Qui a peint la Joconde ?"* sert la culture générale, pas *reconnaître une œuvre*. Même contrefaçon par description que *situer sur une carte* jugé par un quiz de noms |
| 11 | Nommer outils, supports et gestes | **jouable** au choix textuel | *"À quoi sert un pinceau ?"*, *"Avec quoi modèle-t-on ?"* : vocabulaire concret, court, du niveau du CP, exact et vérifiable |

> **Correction du 2026-10-04 (`nadia-prof`), ligne 10.** *"Même motif que la ligne 9"* était
> inexact : il manque à la ligne 10 l'image d'une œuvre **et** la vue simultanée de deux œuvres. Une
> désignation en texte de l'œuvre à rapprocher est refusée. La phrase d'origine reste lisible
> ci-dessus. Détail et conséquences :
> `squad/conformite/ARBITRAGE-PREALABLE-s50-art-cp.md`, Q1 et Q5.

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

> **`ART-1` - Aucun item d'arts plastiques ne fait porter la réponse par une couleur perçue.** Pas
> de pastille colorée, pas de *"de quelle couleur est... ?"* sur une image. La ligne 5 se sert par
> le **nom écrit** des couleurs et par la **règle de mélange**. Motif : un paquet où la couleur
> porte la réponse exclut un enfant daltonien de chaque item, sans recours. C'est l'extension à
> cette matière de `REC-3` (2026-09-02), lui-même dans la lignée de `FIG-3` et de `HOR-3`.
>
> **`ART-2` - Aucun item ne demande à l'enfant ce qu'il ressent, ni ne qualifie une œuvre de belle,
> de réussie ou de ratée.** Le goût n'a pas de bonne réponse et une mécanique de choix en
> fabriquerait une. Les items portent sur ce qui est **observable** dans l'image (sujet, formes,
> matériaux) ou sur une **connaissance** (outils, mélanges).

### Mention exigée d'avance si une ligne bouge

Si les lignes 5 et 11 passent un jour à `couvert-valide`, la matrice porte cette mention :
*"vocabulaire et connaissances de la pratique plastique seuls ; aucune production plastique n'est
servie par ce produit"*. Deux lignes vertes sur onze ne doivent pas laisser croire que le produit
enseigne les arts plastiques.

### Bilan chiffré - arts plastiques CP

| | Nombre |
|---|---|
| Lignes du référentiel | **11** |
| dont `non couvert` | **4** (lignes 5, 9, 10, 11) |
| dont `non couvrable en l'etat` | **7** (lignes 1, 2, 3, 4, 6, 7, 8) |
| **Entrent au dénominateur atteignable** | **4** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **2** (lignes 5 et 11) |
| dont en attente d'une **image d'œuvre** (palier 1 illustré) | **2** (lignes 9 et 10) |

**Sept lignes sur onze sont hors de portée définitive de ce produit, et c'est un résultat, pas un
échec.** Une production plastique ne se produit ni par un choix ni par une saisie. Ce qui reste
n'est pas l'art : c'est ce qu'on en dit.
