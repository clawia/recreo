# Référentiel de compétences - Sciences et technologie - CM1 (cycle 3)

> Écrit par `nadia-prof` le 2026-09-03, sur arbitrage de l'utilisatrice du même jour (`DEC-0074`),
> au même grain et dans la même forme que
> `squad/programme/histoire-geographie/competences-cm1.md`, écrit le 2026-09-02 - une compétence
> par ligne, un verbe en tête, bornée par sa parenthèse. **Socle réaliste, non exhaustif et non
> officiel** : à compléter et à faire valider par l'utilisatrice au fil des sprints, en lien avec
> `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des programmes officiels de
> l'Éducation nationale.
>
> **Audit dégradé, et je le déclare** : je n'ai joué aucun incrément, parce qu'il n'en existe aucun
> sur cette matière. Ce document **ne prononce aucun Done pédagogique**, **n'écrit pas la matrice**
> (`camille-po`) et **ne priorise pas** (l'utilisatrice).
>
> *Sciences et technologie* est une matière de **cycle 3**, issue de la scission de *questionner le
> monde* avec l'histoire-géographie au sortir du cycle 2 (`nadia-prof`, arbitrage préalable de s27,
> section 2.3). Il n'existe et n'existera aucune ligne "sciences et technologie / CP", ni aucune
> ligne "questionner le monde / CM1". Le programme est écrit en **quatre thèmes de cycle**, doublés
> de compétences transversales communes (pratiquer des démarches scientifiques, concevoir et
> réaliser, s'approprier des outils et des méthodes, pratiquer des langages, adopter un
> comportement responsable). Les lignes ci-dessous sont ces compétences appliquées aux attendus de
> la **première année du cycle**.
>
> **Conditions bloquantes opposables à tout paquet de contenu sur ce référentiel**
> (`nadia-prof`, section *Conditions opposables* ci-dessous) : `SCI-1`, `SCI-2`, `SCI-3` ;
> `BIA-2`, `BIA-3` et `BIA-4` s'appliquent à l'identique ; `HIS-3` et `CON-1` s'appliquent à
> l'identique.
>
> **Correction du 2026-09-05 (`camille-po`, sur signalement de `nadia-prof`,
> `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
> section 1.2), avant qu'aucun item ne soit écrit sur ces deux lignes.** Deux erreurs de fond dans
> ce référentiel : la ligne des régimes alimentaires portait *"végétarien"* à la place d'*"herbivore"*
> (*végétarien* est un choix alimentaire humain, pas le régime d'un animal qui mange des végétaux) ;
> la ligne des grandes fonctions du corps rangeait *"nutrition"* parmi ses propres composantes, alors
> que la nutrition est la famille qui contient la digestion, la respiration et la circulation, pas une
> quatrième fonction à leur côté. Les deux lignes ci-dessous sont corrigées ; `DEC-0077`, section 8.7,
> trace la correction et précise qu'elle n'attend pas l'ouverture d'un lot de sciences.

## Matière, mouvement, énergie, information

- Décrire les états de la matière et les changements d'état (solide, liquide, gaz ; fusion, solidification, vaporisation, condensation).
- Distinguer un mélange homogène d'un mélange hétérogène et identifier un moyen de séparer les constituants d'un mélange.
- Décrire le mouvement d'un objet par sa trajectoire et par sa vitesse, par rapport à un point de repère donné.
- Identifier des sources et des formes d'énergie, et un exemple de conversion d'une forme à une autre.
- Identifier un signal et une information dans quelques objets du quotidien (feu tricolore, panneau, alarme, écran).

## Le vivant, sa diversité et les fonctions qui le caractérisent

- Classer des êtres vivants d'après un caractère commun observable, et repérer la diversité des espèces d'un milieu.
- Identifier les besoins des êtres vivants pour se nourrir et distinguer les régimes alimentaires (herbivore, carnivore, omnivore).
- Ordonner les étapes du développement d'un être vivant (naissance, croissance, reproduction, vieillissement, mort).
- Nommer les grandes fonctions de nutrition du corps humain (digestion, respiration, circulation) et l'organe principal de chacune.
- Relier l'alimentation, l'activité physique, le sommeil et l'hygiène au bon fonctionnement du corps.

## Matériaux et objets techniques

- Identifier les principales familles de matériaux et choisir un matériau d'après une propriété (résistance, isolation, souplesse, imperméabilité).
- Identifier la fonction d'un objet technique, nommer ses composants et décrire son fonctionnement.
- Repérer l'évolution d'un objet technique au cours du temps et ce qui a changé dans sa fabrication ou dans son usage.
- Connaître quelques règles d'usage d'un environnement numérique (identification, courriel, recherche d'une information, protection de ses données).

## La planète Terre. Les êtres vivants dans leur environnement

- Situer la Terre dans le système solaire et relier les mouvements de la Terre au jour, à la nuit et aux saisons.
- Identifier quelques phénomènes géologiques et météorologiques (séisme, éruption, tempête, inondation) et les risques associés.
- Décrire un milieu proche : les êtres vivants qui le peuplent et les relations alimentaires qui les relient (chaîne alimentaire).
- Identifier un effet de l'activité humaine sur l'environnement et un geste de préservation des ressources.

## Démarches et langages scientifiques

- Mener une démarche d'investigation : formuler une hypothèse, proposer une expérience pour la tester, interpréter le résultat et communiquer sa démarche.
- Extraire une information d'un document scientifique (texte, tableau, schéma, graphique) et la restituer.

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
| 1 | États et changements d'état | **jouable** au choix textuel | Exact, court, vocabulaire du niveau. Le terrain le plus sûr du lot |
| 2 | Mélange homogène / hétérogène | **jouable** au choix textuel | *Homogène* et *hétérogène* sont les mots du programme et se laissent définir dans l'item lui-même |
| 3 | Mouvement, trajectoire, vitesse | **jouable** au choix textuel, **sur la description seule** | Le produit ne montre **aucun mouvement** : le palier 1 est une image fixe. L'item décrit la situation en texte. **Recouvrement à ne pas double-compter** : le calcul d'une vitesse est un problème de durée et de longueur, déjà porté par mathématiques CM1 / Grandeurs et mesures. Cette ligne porte la **description** du mouvement, pas son calcul |
| 4 | Sources et formes d'énergie | **jouable** au choix textuel | Les distracteurs y sont naturels et plausibles, ce qui est rare |
| 5 | Signal et information | **jouable** au choix textuel | Objets du quotidien, vocabulaire accessible au CM1 |
| 6 | Classer d'après un caractère commun | **jouable** au choix textuel, **item par item** | *"Lequel des trois n'a pas de plumes ?"* travaille bien le caractère. **Mention exigée d'avance si la ligne bouge** : *"raisonnement sur un caractère, item par item ; le tri d'une collection entière reste non couvert"* - trier une collection est une manipulation, refusée au palier 1 par `V3` |
| 7 | Besoins et régimes alimentaires | **jouable** au choix textuel | |
| 8 | Étapes du développement d'un être vivant | **jouable** au choix textuel | Ordonner deux étapes se pose en texte, sans frise ni manipulation |
| 9 | Grandes fonctions du corps | **jouable** au choix textuel | L'association fonction / organe se fait en texte. **Désigner** un organe sur un schéma serait du palier 2, et n'est pas nécessaire à cette ligne |
| 10 | Santé : alimentation, activité, sommeil, hygiène | **jouable** au choix textuel, **sous `BIA-4`** | **`BIA-4` s'applique à l'identique** (posé le 2026-09-02 sur questionner le monde CP) : aucun item n'interroge l'enfant sur sa propre vie - ce qu'il mange, quand il dort, qui l'accompagne. Les items portent sur ce qui est bon pour le corps |
| 11 | Familles de matériaux et propriétés | **jouable** au choix textuel | Une propriété par item, jamais deux : la charge cognitive double sinon |
| 12 | Objet technique : fonction, composants | **jouable** au choix textuel | Nommer un composant se fait en texte ; le **désigner sur un schéma** serait du palier 2 et n'est pas exigé par la ligne |
| 13 | Évolution d'un objet technique | **jouable** au choix textuel, **sous `BIA-3`** | **`BIA-3` s'applique** : on décrit un changement de matériau, de fonction ou d'usage. Aucun item ne dit que l'objet ancien était moins bien |
| 14 | Règles d'usage d'un environnement numérique | **jouable** au choix textuel, **sur les connaissances seules** | L'usage effectif - envoyer, ranger, retrouver - suppose un environnement que le produit ne fournit pas et un état qu'il ne conserve pas (`DEC-0028`). **Mention exigée d'avance** : *"règles et connaissances d'usage seules ; la pratique d'un environnement numérique reste non couverte"* |
| 15 | Terre, système solaire, jour, nuit, saisons | **jouable** au choix textuel | **Exigence d'exactitude que je pose ici parce que la faute est quasi universelle** : la cause des saisons est **l'inclinaison de l'axe de la Terre**, pas sa distance au Soleil. *"La Terre est plus près du Soleil en été"* est le meilleur distracteur de la ligne **et une réponse fausse** ; `SCI-1` impose que le retour le dise |
| 16 | Phénomènes géologiques, météorologiques, risques | **jouable** au choix textuel, **sous `BIA-2`** | **`BIA-2` s'applique** : un paquet qui situe tous les séismes, toutes les éruptions et toutes les inondations ailleurs enseigne une géographie du malheur, pas de la géologie |
| 17 | Milieu proche et chaîne alimentaire | **jouable** au choix textuel | *"Qui mange qui ?"* à deux maillons se pose en texte et sert bien la relation alimentaire. **Construire** une chaîne entière serait une manipulation de collection, refusée par `V3` |
| 18 | Impact humain et préservation | **jouable** au choix textuel, **et c'est la ligne la plus dangereuse du lot** | `SCI-2`. *"Que fais-tu pour la planète ?"* met en cause le foyer de l'enfant, ne lui apprend aucun mécanisme, et sanctionne des conditions de vie dont il n'est pas l'auteur |
| 19 | Mener une démarche d'investigation | **`non couvrable en l'etat`**, nature **irréversible** | **Geste pratique et compte rendu libre.** Proposer une expérience, l'interpréter et *communiquer sa démarche* ne se produisent ni par un choix ni par une saisie : **jugement humain sur une production libre** (`ADR-0008`), même famille que *rédiger* et que la ligne 9 de questionner le monde CP, jugée le 2026-09-02 avec le même motif. **Et je refuse d'avance la découpe qui la sauverait** : *"choisir l'expérience qui teste cette hypothèse"* est un item honnête et jouable, mais c'est **une autre compétence**, strictement plus étroite. La découper pour gagner une ligne au dénominateur serait une contrefaçon par réduction, de la famille de `CON-1`. Si l'utilisatrice veut cette compétence-là, elle s'écrit comme une **ligne neuve et datée**, pas comme un morceau de celle-ci |
| 20 | Extraire une information d'un document scientifique | **jouable** au choix textuel, **sur le texte et le tableau** | Forme déjà servie et validée deux fois (`bandit-manchot-comprehension-cm1`, `bandit-manchot-texte-narratif-cp`). **Mention exigée d'avance** : *"document textuel et tableau seuls ; le schéma et le graphique restent non couverts"* - les lire suppose de désigner une zone, donc le palier 2 |

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

> **`SCI-1` - Après une réponse fausse, le retour dit pourquoi elle est fausse, en une phrase.**
> Cette matière se sert par ses conceptions fausses courantes placées en distracteur - c'est le
> **bon** distracteur, celui qui n'est ni absurde ni défendable. Mais un simple "faux" laisse la
> conception fausse intacte, et l'enfant repart avec elle. C'est la seule matière du lot où je pose
> cette exigence sur le **retour** et pas seulement sur l'énoncé.
>
> **`SCI-2` - Sur les risques naturels et l'impact humain, aucun item ne fait porter une
> responsabilité à l'enfant ni à son foyer** - chauffage, voiture, voyages, déchets. On décrit un
> mécanisme et un geste possible ; on n'audite pas un mode de vie. Motif de la même famille que
> `BIA-4` : tous les enfants n'ont pas le même foyer, et un item qui le met en cause exclut avant
> d'enseigner.
>
> **`SCI-3` - Aucun item ne demande une valeur numérique à restituer** (distance d'une planète,
> température d'un changement d'état, durée d'une révolution) **au-delà d'un item sur six.** C'est
> `HIS-1` transposé : le `bandit-manchot` produit spontanément du quiz de chiffres, et les sciences
> de cycle 3 ne sont pas cela.

`HIS-3` (chaque fait porte sa source dans le commentaire du paquet) et `CON-1` (propositions
masquées, l'item ne se répond pas sans l'image) s'appliquent à l'identique. **Je ne suis pas la
source en sciences comme je le suis en calcul** : l'exactitude de chaque fait se vérifie contre une
source, et c'est le coût principal de cette matière pour moi.

### Bilan chiffré - sciences et technologie CM1

| | Nombre |
|---|---|
| Lignes du référentiel | **20** |
| dont `non couvert` | **19** |
| dont `non couvrable en l'etat` | **1** (ligne 19) |
| **Entrent au dénominateur atteignable** | **19** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **19** |
| dont en attente du **palier 2** | **0** |

**C'est la matière la plus immédiatement servable des six, et de très loin.** La mise en garde qui
m'a été adressée - ne pas traiter cette matière comme de la production libre au motif qu'elle
comporte une démarche d'investigation - **est juste, et mon comptage la confirme** : dix-neuf
lignes sur vingt sont des connaissances ou des raisonnements courts, jouables par le
`bandit-manchot` sans une ligne de code neuve. Une seule tombe, et c'est la démarche elle-même.

**Ce dont je reste la seule garde sur cette matière** : l'exactitude factuelle (aucun automate ne
la voit), `SCI-1` (le retour explique la conception fausse), `SCI-2` et `BIA-2`, et le refus de la
contrefaçon par réduction posé en ligne 19. `SCI-3` devient mordable par un garde-fou **si le
contenu déclare le type de chaque item** - même remarque qu'à `HIS-1`, adressée à `theo-archi` sans
prescrire la forme.
