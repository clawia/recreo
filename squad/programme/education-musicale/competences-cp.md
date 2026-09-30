# Référentiel de compétences - Éducation musicale - CP (cycle 2)

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
> L'éducation musicale est, avec les arts plastiques, l'un des **deux** enseignements artistiques du
> cycle 2, avec son propre chapitre de programme et ses propres attendus de fin de cycle. Elle ne se
> fusionne pas avec les arts plastiques en une section "arts" (`nadia-prof`, arbitrage préalable de
> s27, section 2.3).
>
> **C'est la matière la plus lourdement touchée par la contrainte fondatrice du produit.** Le
> programme est écrit autour de deux gestes - **chanter** et **écouter** -, et le produit ne fournit
> ni l'un ni l'autre : il n'émet aucun son et n'en reçoit aucun. Toutes les lignes sont écrites, y
> compris celles qu'aucune mécanique de ce produit ne servira jamais : **un référentiel qui
> n'écrirait que les lignes jouables mentirait par omission**, et ferait passer un lexique musical
> pour l'éducation musicale.
>
> **Codes de ligne ajoutés le 2026-09-07 (`camille-po`, `DEC-0081`), conformément à `ADR-0081`.** Ce
> référentiel n'était codé par aucun préfixe : aucun paquet ne le citait avant ce jour. La
> numérotation suit l'ordre de lecture des quatre domaines - `MUS-CP-01` à `MUS-CP-11`. Aucun
> intitulé n'est modifié.

## Chanter et interpréter

- [MUS-CP-01] Chanter une comptine ou une chanson par cœur en respectant la hauteur et le rythme.
- [MUS-CP-02] Interpréter un chant avec d'autres en tenant sa place dans le groupe.
- [MUS-CP-03] Mobiliser son corps pour interpréter : frapper une pulsation, marquer un rythme simple.

## Écouter, comparer et commenter

- [MUS-CP-04] Repérer et nommer quelques caractéristiques d'un son entendu (fort/doux, aigu/grave, court/long, lent/rapide).
- [MUS-CP-05] Identifier quelques instruments de musique et les familles auxquelles ils appartiennent (cordes, vents, percussions).
- [MUS-CP-06] Comparer deux extraits musicaux et dire ce qui les distingue.
- [MUS-CP-07] Dire ce que l'on ressent en écoutant une musique et écouter ce que d'autres en disent.

## Explorer et imaginer

- [MUS-CP-08] Explorer des sons avec sa voix, son corps ou des objets et les organiser en une production sonore courte.
- [MUS-CP-09] Imaginer et coder une organisation simple de sons (plus fort, plus vite, silence).

## Échanger, partager et argumenter

- [MUS-CP-10] Respecter les règles de l'écoute et de la production collective (silence, tour de parole, entrée à un signal).
- [MUS-CP-11] Nommer quelques repères du vocabulaire musical rencontré (refrain, couplet, pulsation, silence).

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit** : la mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**,
le **palier 1 visuel** (`ADR-0073`). **Aucun son fourni, aucun son reçu.** Aucune persistance
(`DEC-0028`). Le palier 2 n'existe pas ; il est constructible et `theo-archi` en écrit l'ADR en s28.

**La prémisse de l'adulte accompagnant ne sauve pas cette matière, et je le dis avant qu'on ne le
propose.** Elle vaut, comme moyen de jeu, sur les lignes où **la voix est la compétence visée** et
où l'adulte **lit un texte** (`DEC-0068`, arbitrage du 2026-09-01). Ici, ce qu'il faudrait produire
n'est pas un texte lu mais une **hauteur**, un **timbre**, une **pulsation** : un adulte qui
chanterait faux enseignerait la mauvaise hauteur, et rien dans le produit ne le saurait ni ne le
corrigerait. **C'est le même motif que celui que j'ai opposé le 2026-09-02 à un adulte qui
prononcerait de l'anglais**, transposé à la justesse. Réserve nouvelle et datée du 2026-09-03 ; elle
n'amende aucune barre antérieure.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Chanter par cœur en respectant hauteur et rythme | **`non couvrable en l'etat`**, nature **irréversible** | Deux motifs autonomes, et **leur nature diffère** : aucun son fourni (contrainte fondatrice, **réversible** par une décision de l'utilisatrice sur le son), et jugement humain sur une production vocale libre (`ADR-0008`, **irréversible**). **Le second survit au premier** : même avec du son, personne d'autre qu'un adulte présent ne dirait si l'enfant chante juste. La ligne est donc irréversible |
| 2 | Interpréter un chant avec d'autres | **`non couvrable en l'etat`**, nature **irréversible** | Production collective en situation. Le produit est joué par un enfant seul, accompagné d'un adulte ; il n'y a pas de groupe et il n'y en aura pas |
| 3 | Mobiliser son corps, frapper une pulsation | **`non couvrable en l'etat`**, nature **irréversible** | Geste corporel dans le temps. Aucun capteur, aucune mécanique de ce produit ne mesure une pulsation frappée |
| 4 | Repérer et nommer les caractéristiques d'un son entendu | **`non couvrable en l'etat`**, nature **réversible** | Contrainte fondatrice : aucun son. **Seule une décision de l'utilisatrice sur le son rouvrirait cette ligne**, et elle est la plus rentable de la matière si elle est prise un jour. **Une version textuelle serait une contrefaçon** (`CON-1`) : *"le tonnerre fait-il un son grave ou aigu ?"* teste une connaissance du monde, pas l'écoute d'un son |
| 5 | Identifier des instruments et leurs familles | **`non couvert`** - jouable sur la seule moitié **connaissance** | *"La guitare est-elle un instrument à cordes ou à vent ?"* se pose en texte, se vérifie, est exacte. **La reconnaissance à l'oreille, qui est le cœur de la ligne, reste hors de portée.** Mention exigée d'avance si la ligne bouge : *"familles d'instruments sur le nom écrit seul ; la reconnaissance auditive reste non couverte"*. Même découpage que la ligne 14 de *questionner le monde* (antériorité / frise) |
| 6 | Comparer deux extraits musicaux | **`non couvrable en l'etat`**, nature **réversible** | Contrainte fondatrice : aucun son. Deux extraits ne se comparent pas par écrit |
| 7 | Dire ce que l'on ressent en écoutant | **`non couvrable en l'etat`**, nature **irréversible** | Aucun son, **et** aucune bonne réponse : une mécanique dont le contrat est *une bonne réponse parmi trois* sanctionnerait le ressenti d'un enfant. Deux motifs, chacun suffisant |
| 8 | Explorer des sons et produire | **`non couvrable en l'etat`**, nature **irréversible** | Production sonore libre, jugement humain (`ADR-0008`) |
| 9 | Imaginer et coder une organisation de sons | **`non couvrable en l'etat`**, nature **irréversible** | Production libre. Un codage donné à lire serait une autre compétence, et sans son il ne se lit pas |
| 10 | Respecter les règles de l'écoute collective | **`non couvrable en l'etat`**, nature **irréversible** | Comportement en situation, observé par un adulte. **Recouvrement à ne pas double-compter** : la connaissance de la règle est déjà portée par les lignes de règles de vie de l'EMC ; ce qui est propre à cette ligne-ci, c'est la **tenue** de la règle en situation collective, et elle ne s'observe pas dans ce produit |
| 11 | Nommer des repères du vocabulaire musical | **jouable** au choix textuel, **sous `MUS-1`** | *"Quelle partie d'une chanson revient après chaque couplet ?"* : connaissance lexicale exacte, courte, du niveau du CP |

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

> **`MUS-1` - Aucun item d'éducation musicale ne suppose que l'enfant a entendu quoi que ce soit.**
> Ni *"écoute puis réponds"*, ni un énoncé qui décrirait un son à la place de le faire entendre.
> C'est `CON-1` transposé : **en effaçant l'écoute, la réponse reste-t-elle trouvable ?** Si oui,
> l'item ne teste pas l'écoute et ne doit pas être présenté comme le faisant.
>
> **`MUS-2` - Aucun dispositif où un adulte chanterait, fredonnerait ou frapperait un rythme.** La
> prémisse de l'adulte accompagnant couvre la **lecture d'un texte**, pas la production d'une
> hauteur ni d'une pulsation. Motif complet ci-dessus. Réserve **nouvelle et datée du 2026-09-03**,
> pas un durcissement de `DEC-0068`.

### Mention exigée d'avance si une ligne bouge

Si les lignes 5 et 11 passent un jour à `couvert-valide`, la matrice porte cette mention :
*"vocabulaire musical et familles d'instruments seuls, sur le nom écrit ; aucune compétence
auditive ni vocale n'est servie"*. Sans elle, deux lignes vertes laisseraient croire que le produit
enseigne la musique.

*Amendement du 2026-09-30 (`nadia-prof`, s44).* La ligne 11 ferme seule, la ligne 5 restant
`non couvert`. Sa mention propre est fixee dans
`squad/conformite/FICHE-bandit-manchot-vocabulaire-musical-cp.md`, section 6. La mention ci-dessus
reste celle des deux lignes le jour ou la ligne 5 fermera aussi.

### Bilan chiffré - éducation musicale CP

| | Nombre |
|---|---|
| Lignes du référentiel | **11** |
| dont `non couvert` | **2** (lignes 5 et 11) |
| dont `non couvrable en l'etat` | **9** (lignes 1, 2, 3, 4, 6, 7, 8, 9, 10) |
| dont `non couvrable` de nature **réversible** (une décision sur le son les rouvrirait) | **2** (lignes 4 et 6) |
| dont `non couvrable` de nature **irréversible** | **7** |
| **Entrent au dénominateur atteignable** | **2** |

**Neuf lignes sur onze sont hors de portée, et c'est le résultat le plus net des quatre matières
que j'écris aujourd'hui.** Un chant ne se produit ni par un choix ni par une saisie, et une écoute
ne se simule pas par un texte qui décrirait le son. Ce qui entre au dénominateur, ce sont deux
lignes de vocabulaire - **le produit peut parler de musique, il ne peut pas en faire ni en faire
entendre**.
