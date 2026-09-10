# Référentiel de compétences - Éducation physique et sportive - CP (cycle 2)

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
> Le programme d'EPS de cycle 2 s'organise en **quatre champs d'apprentissage**, doublés de
> compétences générales sur la sécurité, les règles, les rôles et la santé. Les quatre champs sont
> repris ci-dessous comme domaines ; les compétences de **santé**, qui traversent les quatre, sont
> rassemblées dans un cinquième domaine plutôt que répétées quatre fois - autrement elles
> gonfleraient le dénominateur sans décrire quatre choses différentes. La **règle de sécurité**,
> elle, reste dans le champ où elle est la plus prégnante, l'adaptation aux milieux, et n'est pas
> dupliquée dans les autres, pour le même motif.
>
> **Le programme d'EPS est un programme de gestes.** Il est écrit pour un corps en mouvement dans un
> espace, et ce produit n'a ni l'un ni l'autre : il ne reçoit qu'un choix ou une saisie au clavier.
> Toutes les lignes sont écrites quand même, y compris celles qu'aucune mécanique ne servira jamais.
> **Un référentiel qui n'écrirait que les lignes jouables mentirait par omission.**
>
> **Codes de ligne ajoutés le 2026-09-07 (`camille-po`, `DEC-0081`), conformément à `ADR-0081`.** Ce
> référentiel n'était codé par aucun préfixe : aucun paquet ne le citait avant ce jour. La
> numérotation suit l'ordre de lecture des cinq domaines - `EPS-CP-01` à `EPS-CP-11`. Aucun intitulé
> n'est modifié.

## Produire une performance mesurable

- [EPS-CP-01] Courir, sauter et lancer pour réaliser une performance et la mesurer (temps, distance).
- [EPS-CP-02] Comparer sa performance à la précédente et dire si elle progresse.

## Adapter ses déplacements à des environnements variés

- [EPS-CP-03] Se déplacer en s'adaptant au milieu (dans l'eau, sur un engin roulant, sur un parcours d'équilibre).
- [EPS-CP-04] Connaître et respecter les règles de sécurité propres à l'activité et au lieu de pratique.

## S'exprimer devant les autres par une prestation artistique ou acrobatique

- [EPS-CP-05] Mobiliser son corps pour exprimer une intention devant des spectateurs (danse, ronde, acrobatie).
- [EPS-CP-06] Regarder la prestation des autres et en dire quelque chose avec des mots respectueux.

## Conduire et maîtriser un affrontement individuel ou collectif

- [EPS-CP-07] Tenir un rôle dans un jeu collectif (attaquant, défenseur, arbitre).
- [EPS-CP-08] Connaître et respecter les règles d'un jeu collectif simple.
- [EPS-CP-09] Accepter le résultat d'une rencontre, gagner et perdre sans agressivité.

## Entretenir sa santé par la pratique

- [EPS-CP-10] Reconnaître les effets de l'effort sur son corps (essoufflement, cœur qui bat vite, chaleur, soif).
- [EPS-CP-11] Connaître quelques règles d'échauffement, d'hydratation et de récupération autour de l'effort.

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit** : la mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**,
le **palier 1 visuel** (`ADR-0073`). **Aucun son, aucun geste de tracé, aucune persistance**
(`DEC-0028`, `DEC-0046`, `DEC-0066`). **Et, pour cette matière-ci, la contrainte décisive n'est même
pas dans cette liste : le produit ne reçoit aucun mouvement du corps de l'enfant, et rien dans son
architecture ne s'y prête.** Ce n'est pas une lacune d'outillage, c'est ce que le produit est.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Courir, sauter, lancer et mesurer une performance | **`non couvrable en l'etat`**, nature **irréversible** | Geste moteur mesuré. Aucun capteur, aucune mécanique de ce produit n'observe un corps. Ni un choix ni une saisie ne le produisent |
| 2 | Comparer sa performance à la précédente | **`non couvrable en l'etat`**, nature **irréversible** | Deux motifs autonomes : la performance elle-même n'est pas mesurable (ligne 1, irréversible), et le produit ne conserve rien d'une session à l'autre (`DEC-0028`). **Le premier motif suffit et il est irréversible** ; lever la persistance ne rouvrirait rien tant qu'il n'y a rien à conserver |
| 3 | Se déplacer en s'adaptant au milieu | **`non couvrable en l'etat`**, nature **irréversible** | Geste moteur dans un milieu physique. Une simulation à l'écran d'un parcours d'équilibre ne fait pas tenir en équilibre : ce serait une contrefaçon (`CON-1`) et non une couverture |
| 4 | Connaître et respecter les règles de sécurité | **`non couvert`** - jouable sur la seule moitié **connaissance** | *"Que fait-on avant d'entrer dans un bassin ?"*, *"Pourquoi attend-on que le toboggan soit libre avant de glisser ?"* : connaissances exactes, courtes, du niveau du CP, et pédagogiquement utiles. **La mise en œuvre en situation reste hors de portée.** Mention exigée d'avance : *"connaissance de la règle de sécurité seule ; son respect en situation n'est pas servi"*. **Second exemple corrigé le 2026-09-10, voir plus bas** |
| 5 | Mobiliser son corps pour exprimer une intention | **`non couvrable en l'etat`**, nature **irréversible** | Production corporelle libre devant des spectateurs, jugement humain (`ADR-0008`) |
| 6 | Regarder la prestation des autres et en parler | **`non couvrable en l'etat`**, nature **irréversible** | Production orale libre, aucune bonne réponse, et il n'y a pas d'autre enfant devant l'écran |
| 7 | Tenir un rôle dans un jeu collectif | **`non couvrable en l'etat`**, nature **irréversible** | Rôle tenu en situation, dans un groupe qui n'existe pas ici |
| 8 | Connaître et respecter les règles d'un jeu collectif | **`non couvert`** - jouable sur la seule moitié **connaissance**, **sous `EPS-1`** | La règle d'un jeu est une connaissance vérifiable : *"À la balle au prisonnier, que se passe-t-il quand on est touché ?"*. **La tenue du rôle en situation ne l'est pas.** Mention exigée d'avance : *"connaissance de la règle seule ; la tenue du rôle en situation reste non couverte"* |
| 9 | Accepter le résultat, gagner et perdre | **`non couvrable en l'etat`**, nature **irréversible** | **Motif propre, et c'est le plus important de cette matière** : un item de choix sur une attitude ne mesure pas l'attitude, il mesure la **réponse attendue**. Tout enfant de six ans sait cocher *"je le félicite"* ; cela ne dit rien de ce qu'il fait quand il perd. Un exercice qui produit un score sur une conduite morale est une **contrefaçon**, et il est pire qu'une absence parce qu'il fait croire à une couverture |
| 10 | Reconnaître les effets de l'effort sur son corps | **`non couvert`** - jouable au choix textuel, **sous `BIA-4`** | *"Après avoir couru longtemps, que ressent-on ?"* est une connaissance du corps, exacte et de niveau CP. **`BIA-4` s'applique intégralement** : aucun item n'interroge l'enfant sur sa propre pratique ni sur son propre corps. **Frontière à tenir avec *questionner le monde*** (voir ci-dessous) |
| 11 | Connaître les règles d'échauffement, d'hydratation, de récupération | **`non couvert`** - jouable au choix textuel, **sous `EPS-2`** | Connaissance exacte et utile. **Frontière à tenir avec *questionner le monde*** : ces items portent sur l'**effort**, jamais sur l'hygiène de vie en général |

### Recouvrement à ne pas double-compter

*Questionner le monde* CP porte déjà, dans son domaine *Le vivant, la matière et les objets* :
*"Connaître quelques règles d'hygiène de vie (sommeil, alimentation, hygiène du corps) et leur
effet sur la santé"*. Les lignes 10 et 11 ci-dessus portent sur les **effets de l'effort** et sur
ce qui l'**encadre** - échauffement, hydratation pendant l'effort, récupération. Ce sont des
compétences distinctes et elles coexistent, **à condition que les items d'EPS ne dérivent pas vers
le sommeil, les repas ou l'hygiène du corps**, auquel cas la même compétence serait comptée deux
fois sous deux matières, dans le sens qui arrange, et sans que rien ne le signale.

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

> **`EPS-1` - Un item sur la règle d'un jeu ne porte que sur un jeu dont la règle est explicitement
> rappelée dans l'énoncé, ou universellement scolaire.** Motif : le produit ne sait pas quels jeux
> l'enfant a pratiqués, et il ne peut pas le savoir (`DEC-0028`). Un item qui suppose une pratique
> antérieure fait passer un examen qui n'a pas été enseigné - le même défaut que celui pour lequel
> j'ai refusé l'anglais au CP le 2026-09-02.
>
> **`EPS-2` - Aucun item d'EPS ne compare des corps, des performances ou des capacités entre
> enfants**, et aucun ne présente une performance chiffrée comme une norme à atteindre. Motif :
> tous les enfants n'ont pas le même corps, et certains sont en situation de handicap. Une matière
> dont le produit ne sert que le discours ne doit surtout pas servir un discours qui classe.
>
> **`EPS-3` - Aucun item ne fait porter une attitude, une émotion ou une conduite morale par une
> bonne réponse.** Motif complet à la ligne 9. Cette condition vaut aussi pour la ligne 6, et elle
> est la sœur d'`ART-2` en arts plastiques.

### Correction datée du 2026-09-10 - le second exemple de la ligne 4 était fautif, et il a été appliqué tel quel

Le second exemple de la ligne 4 était, du 2026-09-03 au 2026-09-10 : *"Où regarde-t-on avant de
traverser en trottinette ?"*. **Cet exemple est faux et il est de moi.** Un enfant de six ans qui
traverse une rue avec sa trottinette **en descend et la pousse à la main** ; regarder vient ensuite
et ne vient jamais seul. La question posait donc le geste au mauvais endroit de la règle, et un
enfant qui répond *"des deux côtés"* retient qu'on traverse une rue **sur** sa trottinette.

**Ce que cet exemple a coûté, et c'est pourquoi la correction est écrite ici plutôt que dans un
document de verdicts.** Je l'ai repris mot pour mot à s32 dans `SEC-R1` pour dire ce que
j'attendais ; il a été appliqué mot pour mot ; il est devenu l'item 3 de
`bandit-manchot-regles-securite-eps-cp` ; et il est le motif du refus de la ligne 4 au second
passage le 2026-09-10 (`SEC-R4`, `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-C.md`,
section 5.2). **L'exécution a été fidèle au texte reçu ; le texte reçu portait le défaut.**

**Ce que je change dans ma façon d'écrire un exemple** (`RES-2`, `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md`) :
**tout exemple que j'écris dans un référentiel est vérifié contre le geste réel qu'il décrit avant
d'être écrit**, et si je ne peux pas le vérifier, je nomme la connaissance visée sans en fabriquer
l'énoncé. Le second exemple ci-dessus - *"Pourquoi attend-on que le toboggan soit libre avant de
glisser ?"* - décrit une règle de cour d'école dont le geste est celui qu'il nomme : attendre.

**Ce que cette correction ne fait pas** : elle ne lève ni ne durcit `SEC-R1`, qui est **levée**
depuis le 2026-09-10, et elle ne change aucun statut de ligne. La ligne 4 reste `non couvert`.

### Mention exigée d'avance si une ligne bouge

Si les lignes 4, 8, 10 ou 11 passent un jour à `couvert-valide`, la matrice porte cette mention :
*"connaissances qui entourent la pratique physique seules ; aucune activité physique, aucun geste
et aucune performance ne sont servis par ce produit"*. **Quatre lignes vertes sur onze ne doivent
en aucun cas se lire comme une couverture partielle de l'EPS** : ce qui est couvert, c'est ce qu'on
sait à propos de l'EPS, pas l'EPS.

### Bilan chiffré - éducation physique et sportive CP

| | Nombre |
|---|---|
| Lignes du référentiel | **11** |
| dont `non couvert` | **4** (lignes 4, 8, 10, 11) |
| dont `non couvrable en l'etat` | **7** (lignes 1, 2, 3, 5, 6, 7, 9) |
| dont `non couvrable` de nature **réversible** | **0** |
| dont `non couvrable` de nature **irréversible** | **7** |
| **Entrent au dénominateur atteignable** | **4** |

**Aucune des sept lignes hors de portée n'est réversible, et c'est le fait à retenir de cette
matière.** Pour l'anglais, une décision de l'utilisatrice sur le son rouvrirait des lignes ; pour
l'éducation musicale, deux lignes attendent la même décision. **Ici, rien n'attend une décision** :
il faudrait que le produit reçoive un corps en mouvement, ce qui n'est pas une option de
configuration mais un autre produit. **Le dire maintenant vaut mieux que de le découvrir dans trois
sprints.**
