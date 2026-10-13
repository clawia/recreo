# Référentiel de compétences - Questionner le monde - CE1 (cycle 2)

> Écrit par `nadia-prof` le 2026-10-13
> (`squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 2),
> au même grain et dans la même forme que `squad/programme/questionner-le-monde/competences-cp.md` :
> une compétence par ligne, un verbe en tête, bornée par sa parenthèse. **Socle réaliste, non
> exhaustif et non officiel** : à compléter et à faire valider par l'utilisatrice au fil des sprints,
> en lien avec `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des programmes
> officiels de l'Éducation nationale.
>
> *Questionner le monde* est une matière de cycle 2, dont le programme est écrit pour le cycle et
> non pour chaque année. **Une ligne n'est écrite ici que si un paquet de CE1 doit y servir autre
> chose qu'un paquet de CP sur la ligne voisine** : la frontière avec le CP est écrite sur chaque
> ligne qui en a une (section "Frontières"). Les compétences du CP que le CE1 reprend sans contenu
> neuf ne sont pas recopiées : les compter deux fois gonflerait la matrice sans rien servir de plus
> à un enfant.

**Codes de ligne.** Chaque compétence porte un code entre crochets, alloué une fois et
jamais réutilisé : c'est par lui qu'un paquet de contenu déclare la ligne qu'il sert, et
par lui que le nombre d'items servant une ligne se compte (`ADR-0081`). Reformuler un
intitulé ne change pas son code ; insérer une ligne prend le numéro libre suivant, sans
renuméroter les autres. Le code n'est donc pas un rang, même si les premiers ont été
alloués dans l'ordre de lecture.

## Le vivant, la matière et les objets

- [QLM-CE1-01] Ordonner les étapes du développement d'un animal ou d'une plante familiers, y compris une métamorphose (œuf, têtard, grenouille) ou une germination (graine, jeune pousse, plante).
- [QLM-CE1-02] Relier quelques animaux familiers à ce qu'ils mangent (des végétaux, d'autres animaux, ou les deux).
- [QLM-CE1-03] Identifier ce dont une plante a besoin pour vivre et grandir (eau, lumière, chaleur).
- [QLM-CE1-04] Classer des aliments courants dans leur famille (fruits et légumes, féculents, produits laitiers, viande, poisson et œufs, produits sucrés) et reconnaître un repas varié.
- [QLM-CE1-05] Reconnaître l'eau sous ses trois états (glace, eau liquide, vapeur d'eau) et relier la fonte et le gel à la température.
- [QLM-CE1-06] Reconnaître les objets qu'un aimant attire et ceux qu'il n'attire pas.
- [QLM-CE1-07] Identifier les éléments d'un circuit électrique simple (pile, ampoule, fils, interrupteur) et dire si un circuit dessiné permet à l'ampoule de briller.
- [QLM-CE1-08] Connaître les dangers de l'électricité de la maison (prise, appareil près de l'eau, fil abîmé) et les gestes qui les évitent.

## Se situer dans l'espace

- [QLM-CE1-09] Lire un plan simple à l'aide de sa légende pour situer un lieu ou suivre un itinéraire.
- [QLM-CE1-10] Identifier quelques paysages (littoral, montagne, campagne, ville) à partir de leurs éléments caractéristiques.
- [QLM-CE1-11] Situer les continents et les océans sur un planisphère ou sur un globe.

## Se situer dans le temps

- [QLM-CE1-12] Situer sur une frise les générations d'une famille (enfants, parents, grands-parents) et quelques événements de la vie d'un personnage.

---

## Frontières, avec le CP et avec le CM1

| Ligne de CE1 | Ligne voisine | Ce qui les sépare |
|---|---|---|
| `QLM-CE1-01` | `QLM-CP-02` (ce qui caractérise un être vivant) | au CP, les caractères communs à tout le vivant ; ici, l'ordre des étapes d'une espèce nommée, transformations comprises |
| `QLM-CE1-01` | `SCI-CM1-08` (étapes du développement) | même objet au cycle 3 (`CYC-1`) : au CE1, des animaux et des plantes familiers, sans le vocabulaire *reproduction* ni *vieillissement* |
| `QLM-CE1-02` | `SCI-CM1-07` (régimes alimentaires) | au CE1, ce que mange l'animal ; les mots *herbivore*, *carnivore*, *omnivore* sont ceux du CM1 et ne sont pas demandés |
| `QLM-CE1-04` | `QLM-CP-04` (hygiène de vie) | au CP, l'effet d'une habitude sur la santé ; ici, la famille d'un aliment |
| `QLM-CE1-05` | `QLM-CP-05` (états de l'eau, solide et liquide) | ici, le troisième état et le lien avec la température ; au CM1 (`SCI-CM1-01`), les noms des changements d'état de toute la matière |
| `QLM-CE1-06` | `QLM-CP-06` (trier des matériaux) | ici, une seule propriété, l'attraction par un aimant, avec son piège : tous les métaux ne sont pas attirés |
| `QLM-CE1-09` | `QLM-CP-11` (plan de la classe, de l'école, du quartier) | ici, la légende et l'itinéraire |
| `QLM-CE1-11` | `QLM-CP-12` (la France sur une carte ou un globe) | ici, les continents et les océans |
| `QLM-CE1-12` | `QLM-CP-14` (frise avant, après, en même temps) | ici, les générations d'une famille |

## Ce que ce référentiel ne reprend pas du CP, et pourquoi

Les lignes `QLM-CP-01`, `-03`, `-07`, `-08`, `-09`, `-10`, `-13`, `-15` et `-16` décrivent des
compétences que le programme du cycle 2 travaille aussi au CE1. Je n'en écris pas de jumelle :
aucun contenu propre au CE1 ne les sépare d'une ligne de CP déjà écrite, et un paquet de CE1 sur
l'une d'elles servirait la ligne de CP avec des items plus longs. **Le calendrier** (dates, jours,
mois) n'a pas de ligne ici : `MAT-CE1-11` le porte déjà, et un paquet l'y sert
(`ce1-calendrier-dates`).

## Jugement de faisabilité (`nadia-prof`, 2026-10-13)

**Audit dégradé, et je le déclare** : aucun paquet n'existe sur ces lignes, je n'ai rien joué. Ce
jugement dit la situation de chaque ligne à son entrée dans la matrice ; il ne pose aucune condition
neuve et n'est pas l'instruction des lignes. **L'instruction, ligne par ligne, avec ses conditions,
est due avant le premier paquet**, comme celle du français CE1 l'a été
(`ARBITRAGE-PREALABLE-s48-francais-ce1.md`).

| Ligne | Situation à l'entrée dans la matrice | Motif |
|---|---|---|
| `QLM-CE1-01` à `-06`, `-08`, `-10`, `-12` | **`non couvert`**, jouable au choix textuel | connaissances exactes et vérifiables en trois propositions ; `-10` se joue par les éléments du paysage nommés dans l'énoncé, sans image |
| `QLM-CE1-07` | **`non couvert`**, en attente d'un matériau | dire si un circuit est fermé demande un circuit dessiné ; la désignation existe, le dessin non. La moitié *nommer les éléments* est textuelle |
| `QLM-CE1-09` | **`non couvert`**, en attente d'un matériau | un plan avec légende ; la désignation sert déjà un plan au CP (`designation-plan-de-la-classe`) |
| `QLM-CE1-11` | **`non couvert`**, en attente d'un matériau | un planisphère ou un globe dessiné, absent du dépôt, le même manque que `QLM-CP-12` et `HG-CM1-09` |

Aucune ligne n'est `non couvrable en l'état` : douze lignes, douze au dénominateur atteignable.

**Ce dont je reste la seule garde sur cette matière, dit d'avance** : l'exactitude (le métal que
l'aimant n'attire pas, la pomme de terre que la famille des aliments range avec les féculents et
non avec les légumes, la vapeur d'eau qui ne se voit pas, la buée qui n'en est pas), un seul ordre défendable sur `QLM-CE1-01`, et sur `QLM-CE1-12` et
`QLM-CE1-04` aucun item qui interroge l'enfant sur sa propre famille ou ses propres repas (`BIA-4`,
`EMC-1` et `EMC-6` transposés).

**Aucun intitulé ci-dessus ne devient un titre de jeu.** Un titre de jeu ne porte jamais le niveau
de son paquet (`ADR-0078`) ; ces intitulés nomment une compétence pour l'adulte qui pilote la
couverture, pas un jeu pour l'enfant.

## Note du 2026-10-13, après la réponse de l'utilisatrice (`nadia-prof`)

L'utilisatrice ne veut pas de nouveaux items au CE1 par reprise du CP, ni de son (`DEC-0120` A.27).
Les lignes ci-dessus y sont conformes ; **leurs items ne l'étaient pas d'avance**. En lisant les
paquets de CP, j'ai trouvé trois lignes qui pouvaient recevoir un item de CP : `QLM-CE1-04` (le repas
varié, déjà servi par `cp-qlm-hygiene`), `QLM-CE1-05` (solide ou liquide, et la fonte, servis par
`cp-qlm-etats-eau`), `QLM-CE1-11` (la France, son continent et son océan, écrits dans
`cp-qlm-situer-la-france`), et une demi-ligne, `QLM-CE1-12` (les événements d'une journée,
`QLM-CP-14`). Aucun intitulé ne change. La frontière tient désormais item par item :
`NRC-CE1-1` et les conditions de chaque ligne, dans
`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, qui
remplace aussi la phrase *"L'instruction ... est due avant le premier paquet"* : elle est faite.
