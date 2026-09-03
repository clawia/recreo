# Référentiel de compétences - Histoire des arts - CM1 (cycle 3)

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
> **Pourquoi cette matière existe dans le compte, et le critère qui l'y met.** L'histoire des arts
> est un **enseignement à part entière du cycle 3**, distinct des arts plastiques et de l'éducation
> musicale : c'est un chapitre de programme portant ses **propres attendus de fin de cycle**. Ce
> qu'il n'a pas, c'est un **horaire propre** - il se prend sur les heures d'arts, d'histoire-
> géographie et de français. C'est cette absence d'horaire qui le fait paraître douteux, et c'est
> pourquoi j'ai dû énoncer le critère avant de trancher : **je compte un enseignement par chapitre
> de programme portant ses propres attendus**, parce que c'est le seul critère qui produise des
> compétences, donc des lignes de matrice (`nadia-prof`, arbitrage préalable de s27, section 2.2
> bis, 2026-09-02). **L'histoire des arts n'existe pas au cycle 2** : elle ne doit recevoir aucune
> ligne CP.
>
> Le programme de cycle 3 s'organise en trois grandes compétences - décrire une œuvre et en
> dégager les caractéristiques ; la relier à son contexte et la situer ; donner un avis argumenté -
> auxquelles s'ajoute la fréquentation des lieux d'art et du patrimoine.

## Décrire une œuvre et en dégager les caractéristiques

- Décrire une œuvre d'art en employant un lexique simple et adapté (support, format, matière, couleur, sujet).
- Dégager d'une œuvre observée ses principales caractéristiques techniques et formelles.
- Identifier les matériaux d'une œuvre et la manière dont l'artiste leur a donné forme.

## Situer une œuvre dans le temps et dans son contexte

- Associer une œuvre à une époque et à une civilisation à partir d'éléments observés.
- Relier les caractéristiques d'une œuvre d'art à des usages et au contexte historique et culturel de sa création.
- Identifier quelques œuvres et quelques repères majeurs du patrimoine, et les situer sur une frise.

## Donner un avis argumenté

- Donner un avis argumenté sur ce qu'une œuvre représente ou exprime.
- Proposer une interprétation simple d'une œuvre et en discuter avec d'autres.

## Fréquenter les lieux d'art et le patrimoine

- Se repérer dans un musée, un lieu d'art ou un site patrimonial, et en respecter les règles.
- Rendre compte de la visite d'un lieu de conservation ou de la rencontre avec un métier du patrimoine.

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit, tels qu'ils existent aujourd'hui et pas tels qu'on les imagine** : la
mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**, le **palier 1 visuel** - une
image fixe, des propositions en texte (`ADR-0073`). **Aucun son. Aucun geste de tracé. Aucune
persistance** (`DEC-0028`, `DEC-0046`, `DEC-0066`). Le **palier 2** - désigner un lieu ou un objet
sur une image - n'existe pas : il est refusé au palier 1 par `V1` (2026-08-24), il est
**constructible**, et `theo-archi` en écrit l'ADR en s28. Une ligne qui l'attend est `non couvert`,
**jamais** `non couvrable en l'etat`.

**Le fait qui gouverne ce fichier** : la mise en garde qui m'était adressée est juste - les
attendus de **reconnaissance et de situation d'œuvres sont des connaissances**, donc jouables par
la mécanique existante ; **la production et l'avis, non**. Sept lignes sur dix entrent au
dénominateur. Mais six d'entre elles sont gouvernées par une même condition matérielle : **il faut
voir l'œuvre**, et le dépôt ne sert aujourd'hui aucune reproduction d'œuvre.

`ART-1` (aucune réponse portée par une couleur perçue) et `ART-2` (aucune question de goût), posés
le 2026-09-03 sur `squad/programme/arts-plastiques/competences-cp.md`, ainsi que `ART-3`
(identification de l'œuvre dans le commentaire du paquet), `ART-4` (`CON-1` appliqué à l'image) et
`ART-5` (pas d'époque ni d'aire culturelle unique), posés le même jour sur
`squad/programme/arts-plastiques/competences-cm1.md`, **s'appliquent ici à l'identique**. `ART-1`
mord sur la ligne 1, dont le lexique comporte la couleur : **la couleur ne porte jamais seule la
réponse**. `HIS-2` (aucun item n'a deux réponses défendables) et `HIS-3` (chaque fait porte sa
source) s'appliquent aussi : cette matière est de la famille de l'histoire pour l'exactitude
factuelle, et **je n'en suis pas la source**.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Décrire une œuvre avec un lexique adapté | **`non couvert`** - palier 1 **illustré**, image d'œuvre requise | La forme existe telle quelle : image fixe, propositions en texte, `ADR-0073`. **Ce qui manque est l'image** - le dépôt sert vingt-cinq illustrations, toutes des tracés géométriques. **Un item textuel qui décrirait l'œuvre dans son énoncé serait une contrefaçon** (`ART-4`, `CON-1`) : décrire une œuvre qu'on ne voit pas n'est pas décrire une œuvre |
| 2 | Dégager les caractéristiques techniques et formelles | **`non couvert`** - même motif que la ligne 1 | **Mention exigée d'avance si la ligne bouge** : *"œuvres visuelles seules ; l'œuvre écoutée reste non couvrable, faute de son"*. Le programme range aussi l'écoute sous cette compétence, et le produit ne diffuse aucun son |
| 3 | Identifier les matériaux et leur mise en forme | **`non couvert`** - même motif que la ligne 1 | Risque propre à cette ligne : le nom de l'œuvre porte souvent le matériau (*"la statue de bronze"*). `ART-4` mord exactement là - propositions masquées, l'item ne doit pas se répondre sans l'image |
| 4 | Associer une œuvre à une époque et à une civilisation | **`non couvert`** - même motif que la ligne 1 | **La meilleure forme d'item de la matière** : l'association a des distracteurs naturels et plausibles, ce qui est rare. La ligne dit *à partir d'éléments observés* : sans image, l'item devient un quiz de titres, qui teste la mémoire d'un nom et non le regard |
| 5 | Relier une œuvre à des usages et à son contexte | **`non couvert`** - **jouable dès aujourd'hui au choix textuel** | **La seule ligne de la matière qui ne demande aucune image.** L'énoncé peut nommer la caractéristique (*"une église dont les fenêtres sont immenses et les murs percés"*) et demander l'usage ou l'époque : le geste évalué reste bien **le lien caractéristique -> contexte**, qui est la compétence. `HIS-2` est ici la barre critique - le contexte d'une œuvre a souvent plusieurs lectures défendables, et une mécanique de choix en couronnerait une |
| 6 | Identifier des repères du patrimoine et les situer sur une frise | **`non couvert`** - palier 1 **illustré**, **sur la moitié identification** | **Mention exigée d'avance si la ligne bouge** : *"identification et ordre entre deux œuvres seuls ; le placement sur une frise reste non couvert"* - placer sur une frise est du palier 2. Même découpe que la ligne 14 de questionner le monde CP, jugée le 2026-09-02 |
| 7 | Donner un avis argumenté sur une œuvre | **`non couvrable en l'etat`**, nature **irréversible** | **Jugement humain sur une production libre** (`ADR-0008`) : *argumenter* est une production, et sa qualité ne se juge qu'en la lisant. Et la question n'a pas de bonne réponse unique : `ART-2`, même famille que `BIA-5` |
| 8 | Proposer une interprétation et en discuter | **`non couvrable en l'etat`**, nature **irréversible** | Mêmes deux motifs, plus l'échange oral avec d'autres, que rien de ce produit ne fournit |
| 9 | Se repérer dans un musée ou un site patrimonial | **`non couvert`** - **palier 2** | Se repérer suppose de **désigner un lieu sur un plan** : c'est exactement le palier 2, refusé au palier 1 par `V1`, constructible, ADR en s28. **Ce n'est pas `non couvrable en l'etat`.** Et je refuse d'avance la contrefaçon : un quiz *"que fait-on dans un musée ?"* sert les règles de comportement, pas le repérage - même refus que celui que j'ai opposé au quiz de noms censé servir *situer sur une carte* (`CON-1`) |
| 10 | Rendre compte d'une visite ou d'une rencontre | **`non couvrable en l'etat`**, nature **irréversible** | **Compte rendu libre** (`ADR-0008`), et la ligne suppose une **visite réelle** que ce produit ne fournit pas et ne remplace pas. Même famille que *rédiger* |

### Mention exigée d'avance si une ligne bouge

Si des lignes de cette matière passent à `couvert-valide`, la matrice porte cette mention :
*"reconnaissance et situation d'œuvres seules ; aucun avis argumenté ni aucune fréquentation d'un
lieu d'art n'est servi par ce produit"*.

### Bilan chiffré - histoire des arts CM1

| | Nombre |
|---|---|
| Lignes du référentiel | **10** |
| dont `non couvert` | **7** (lignes 1 à 6, 9) |
| dont `non couvrable en l'etat` | **3** (lignes 7, 8, 10), toutes de nature **irréversible** |
| **Entrent au dénominateur atteignable** | **7** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **1** (ligne 5) |
| dont en attente d'une **image d'œuvre** (palier 1 illustré) | **5** (lignes 1, 2, 3, 4, 6) |
| dont en attente du **palier 2** | **1** (ligne 9) |

**Sept lignes sur dix entrent au dénominateur : c'est le meilleur rapport des trois enseignements
artistiques, et il est cohérent avec ce que cette matière est** - un enseignement de connaissances
et de regard, pas de pratique. C'est aussi ce qui justifie qu'elle compte comme un enseignement
distinct : elle produit des lignes que ni les arts plastiques ni l'éducation musicale ne produisent.

**Mais une seule de ces sept lignes est servable sans rien construire de neuf.** Les six autres
attendent soit un corpus de reproductions d'œuvres, qui n'existe pas dans le dépôt et dont les
droits ne sont pas instruits (`ART-3`), soit le palier 2. **Ce n'est pas un coût de mécanique,
c'est un coût de contenu**, et il est à `camille-po` et à `theo-archi` de le chiffrer - pas à moi.

**Ce dont je reste la seule garde** : `ART-1` à `ART-5`, `HIS-2` appliqué au contexte des œuvres,
l'exactitude de chaque attribution, et les deux contrefaçons refusées ci-dessus (le quiz de titres
en ligne 4, le quiz de comportement en ligne 9).
