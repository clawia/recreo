# Référentiel de compétences - Arts plastiques - CM1 (cycle 3)

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
> Les arts plastiques sont **un enseignement distinct** de l'éducation musicale et de l'histoire
> des arts. Le mot "arts" au singulier recouvre **trois enseignements au cycle 3** : une matrice
> qui n'ouvrirait qu'une section "arts" sous-compterait le programme sans que rien ne le signale
> (`nadia-prof`, arbitrage préalable de s27, section 2.3). Le programme de cycle 3 s'organise en
> trois questions - la représentation plastique et les dispositifs de présentation ; les
> fabrications et la relation entre l'objet et l'espace ; la matérialité de la production plastique
> - doublées de compétences travaillées communes au cycle (expérimenter, produire, créer ; mettre
> en œuvre un projet ; s'exprimer et analyser sa pratique et celle des artistes ; se repérer dans
> les domaines liés aux arts plastiques).
>
> **Codes de ligne ajoutés le 2026-09-07 (`camille-po`, `DEC-0081`), conformément à `ADR-0081`.** Ce
> référentiel n'était codé par aucun préfixe : aucun paquet ne le citait avant ce jour. La
> numérotation suit l'ordre de lecture des quatre domaines - `ART-CM1-01` à `ART-CM1-10`. Aucun
> intitulé n'est modifié.

## La représentation plastique et les dispositifs de présentation

- [ART-CM1-01] Représenter le monde environnant ou donner forme à son imaginaire en explorant plusieurs domaines (dessin, collage, modelage, sculpture, photographie).
- [ART-CM1-02] Utiliser un procédé de représentation (ressemblance, écart, transformation) pour rendre compte d'un sujet.
- [ART-CM1-03] Expérimenter l'effet des couleurs, des supports, des outils et des gestes sur une représentation.

## Les fabrications et la relation entre l'objet et l'espace

- [ART-CM1-04] Fabriquer un objet ou une construction en volume et adapter sa réalisation à l'espace où elle sera vue.
- [ART-CM1-05] Prendre en compte le lieu et le spectateur dans la présentation d'une production (accrochage, mise en espace).

## La matérialité de la production plastique

- [ART-CM1-06] Choisir et employer une matière, un outil ou un geste en fonction de l'effet plastique recherché.
- [ART-CM1-07] Repérer, dans une œuvre observée, les effets produits par la matière, la couleur, le geste et le support.

## S'exprimer, analyser sa pratique et celle des artistes

- [ART-CM1-08] Décrire une œuvre d'art avec un lexique élémentaire (support, format, matière, couleur, sujet, composition).
- [ART-CM1-09] Exprimer ce qu'on perçoit d'une œuvre, formuler un avis et écouter celui des autres.
- [ART-CM1-10] Établir un lien entre sa propre production et des œuvres d'artistes rencontrées.

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit, tels qu'ils existent aujourd'hui et pas tels qu'on les imagine** : la
mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**, le **palier 1 visuel** - une
image fixe, des propositions en texte (`ADR-0073`). **Aucun son. Aucun geste de tracé. Aucune
persistance** (`DEC-0028`, `DEC-0046`, `DEC-0066`). Le **palier 2** - désigner un lieu ou un objet
sur une image - n'existe pas : il est refusé au palier 1 par `V1` (2026-08-24), il est
**constructible**, et `theo-archi` en écrit l'ADR en s28. Une ligne qui l'attend est `non couvert`,
**jamais** `non couvrable en l'etat`.

**Une règle de nature que je pose une fois et qui gouverne huit lignes de ce fichier** : quand une
ligne cumule les deux motifs - le geste que le produit ne fournit pas **et** le jugement humain sur
ce que l'enfant a produit -, **c'est le motif irréversible qui donne la nature**. Même si
l'utilisatrice rouvrait un jour le geste de tracé (`DEC-0046`), personne d'automatique ne saurait
dire si un enfant de neuf ans a rendu compte d'un sujet par un écart. Le geste est réversible ; le
jugement de la production ne l'est pas.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Représenter le monde / son imaginaire | **`non couvrable en l'etat`**, nature **irréversible** | Production plastique. Deux motifs autonomes : aucun geste de tracé n'est fourni par ce produit (contrainte fondatrice, `CLAUDE.md`, `DEC-0046`), et une production libre ne se juge que par un humain (`ADR-0008`). Même famille que *rédiger* et *copier* |
| 2 | Utiliser un procédé de représentation | **`non couvrable en l'etat`**, nature **irréversible** | La ligne dit **utiliser**, pas reconnaître. Reconnaître un procédé dans une œuvre est un autre geste, il vit en ligne 7, et je refuse qu'on compte celui-ci par celui-là |
| 3 | Expérimenter l'effet des couleurs, outils, gestes | **`non couvrable en l'etat`**, nature **irréversible** | Expérimenter est un geste sur une matière. Et sa contrefaçon la plus tentante - *"quelle couleur donne une impression de froid ?"* - tombe sous `ART-2` : elle fabrique une bonne réponse à une question de sensibilité |
| 4 | Fabriquer en volume | **`non couvrable en l'etat`**, nature **irréversible** | Geste matériel hors de tout écran, jugé par un adulte qui regarde l'objet |
| 5 | Présenter : lieu, spectateur, accrochage | **`non couvrable en l'etat`**, nature **irréversible** | Suppose une production et un lieu réels (`ADR-0008`) |
| 6 | Choisir matière, outil, geste selon l'effet recherché | **`non couvrable en l'etat`**, nature **irréversible** | L'effet recherché est celui de l'enfant : personne d'autre ne peut dire si le choix le sert. Le fragment de pure connaissance - *"quel outil fait un trait épais ?"* - ne sert pas cette ligne, et je refuse d'avance qu'on l'y compte |
| 7 | Repérer les effets dans une œuvre observée | **`non couvert`** - palier 1 **illustré**, image d'œuvre requise | **Une des deux seules lignes atteignables.** La forme existe telle quelle : image fixe, propositions en texte, `ADR-0073`. **Ce qui manque est l'image** : le dépôt sert vingt-cinq illustrations, toutes des tracés géométriques, et aucune reproduction d'œuvre. Conditions : `ART-1` à `ART-5`, et `ART-1` mord ici en premier - la couleur ne porte jamais seule la réponse. Un item textuel qui décrirait l'œuvre dans son énoncé serait une contrefaçon (`CON-1`) |
| 8 | Décrire une œuvre avec un lexique élémentaire | **`non couvert`** - même motif que la ligne 7 | **Mention exigée d'avance si la ligne bouge** : *"support, format, matière, couleur et sujet seuls ; la composition et le cadrage restent non couverts"* - les décrire suppose de désigner une zone de l'image, donc le palier 2 |
| 9 | Exprimer ce qu'on perçoit, formuler un avis | **`non couvrable en l'etat`**, nature **irréversible** | **Jugement humain sur une production libre** (`ADR-0008`), et compétence **sans bonne réponse** : `ART-2`. Une mécanique dont le contrat est *une bonne réponse parmi trois* sanctionnerait le regard d'un enfant de neuf ans |
| 10 | Lier sa production à des œuvres d'artistes | **`non couvrable en l'etat`**, nature **irréversible** | Suppose une production de l'enfant, que le produit ne recueille pas et ne conserve pas (`DEC-0028`). **Recouvrement à ne pas double-compter** : la reconnaissance et la situation d'œuvres, sans lien à une pratique personnelle, sont portées par le référentiel d'**histoire des arts** ; elles ne doivent pas entrer deux fois au dénominateur |

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

**Pourquoi la numérotation commence à `ART-3`, et je le dis plutôt que de le laisser découvrir** :
`ART-1` (aucun item ne fait porter la réponse par une **couleur perçue** - motif : un enfant
daltonien serait exclu de chaque item, sans recours) et `ART-2` ont été posés le **même jour**, sur
`squad/programme/arts-plastiques/competences-cp.md`. Ils **s'appliquent ici à l'identique**, et
j'ai renuméroté les miens pour qu'un même identifiant ne désigne pas deux barres différentes. Un
identifiant qui change de sens selon le fichier est exactement le genre d'écart que je sanctionne
ailleurs.

**`ART-1` mord fort sur la ligne 7, et c'est le point à ne pas manquer** : cette ligne porte
justement sur l'effet des couleurs. Les items y portent sur la **matière, le geste, le support et
le sujet** ; **la couleur ne porte jamais seule la réponse**, et une question du type *"quelle
couleur domine ?"* est refusée.

> **`ART-3` - Toute œuvre servie en image est identifiée dans le commentaire du paquet** : titre,
> auteur, date, lieu de conservation. Une œuvre non identifiée n'entre pas dans un paquet. **La
> question des droits d'usage des reproductions n'est pas de ma compétence** : je la signale à
> `camille-po` et à `theo-archi`, je ne la tranche pas.
>
> **`ART-2` - Aucun item ne demande à l'enfant ce qu'il ressent, ce qu'il préfère ou ce qu'il
> trouve beau, et aucun item ne qualifie une œuvre de réussie ou de ratée.** Le goût n'a pas de
> bonne réponse et une mécanique de choix en fabriquerait une. Même famille que `HIS-2` et
> `BIA-5`. C'est la même barre que celle posée le même jour sur les arts plastiques de CP.
>
> **`ART-4` - `CON-1` appliqué au corpus d'œuvres** : propositions masquées, **l'item ne doit pas
> se répondre sans regarder l'image**. Un item sur la couleur dominante d'un tableau dont l'énoncé
> nomme déjà le sujet et l'ambiance est une contrefaçon par description.
>
> **`ART-5` - Le corpus d'un paquet ne se compose ni d'une seule époque, ni d'une seule aire
> culturelle, ni d'artistes d'un seul genre.** C'est `BIA-2` transposé aux œuvres : un paquet qui
> ne montre qu'un canon enseigne ce canon comme étant l'art.

### Mention exigée d'avance si une ligne bouge

Si les lignes 7 et 8 passent un jour à `couvert-valide`, la matrice porte cette mention :
*"réception d'œuvres seule ; aucune pratique plastique n'est servie par ce produit"*. Deux lignes
vertes sur dix ne doivent pas laisser croire que le produit enseigne les arts plastiques.

### Bilan chiffré - arts plastiques CM1

| | Nombre |
|---|---|
| Lignes du référentiel | **10** |
| dont `non couvert` | **2** (lignes 7 et 8) |
| dont `non couvrable en l'etat` | **8** (lignes 1 à 6, 9, 10) |
| **Entrent au dénominateur atteignable** | **2** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **0** |
| dont en attente d'une **image d'œuvre** (palier 1 illustré) | **2** |
| dont en attente du **palier 2** | **0** |

**Huit lignes sur dix sont hors de portée définitive de ce produit. C'est un résultat, pas un
échec, et je l'écris sans l'adoucir** : les arts plastiques sont d'abord une pratique, une
production plastique ne se produit ni par un choix ni par une saisie, et les huit lignes tombées le
sont pour une raison **irréversible** - ce qui manque n'est pas un moyen technique, c'est un adulte
qui regarde ce que l'enfant a fait.

Les deux lignes qui restent portent sur la **réception** d'une œuvre, jamais sur la pratique. Et
elles ne sont pas gratuites : chacune demande un corpus de reproductions qui n'existe pas dans le
dépôt et dont les droits ne sont pas instruits.

**Ce dont je reste la seule garde** : `ART-1` à `ART-5`, et l'exactitude de l'attribution de chaque
œuvre - aucun automate ne vérifie qu'un tableau est bien de qui on le dit.
