# DEC-0050 - `cm1-reperes` retourne au catalogue (`VALIDE SOUS RESERVE`) ; la ligne de matrice passe
# `couvert-valide` ; `REP-8` (indice hors de vue) est un defaut de catalogue, pas de ce paquet

- **Type** : execution de decision (retour au catalogue), mouvement de matrice, mise en backlog de
  quatre reserves non bloquantes
- **Date** : 2026-08-27
- **Auteur** : camille-po
- **Sprint** : s20 (cloture)
- **Competence programme visee** : francais, CM1 (cycle 3), "Reperer et corriger la majuscule et la
  ponctuation dans une phrase donnee..." (`squad/programme/francais/competences-cm1.md`) ;
  transverse - pas de contenu pedagogique (section 4, `REP-8`).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a reaudite `bandit-manchot-reperes-cm1` sur le paquet corrige par `lea-dev`
(`round-two` a `77dd467`) et prononce **VALIDE SOUS RESERVE** le 2026-08-27
(`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`, verdict du 2026-08-27 ;
`squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/avis-nadia-cloture.md`). Le
`REFUSE` du 2026-08-26, retire du catalogue par `DEC-0049`, est leve. Elle ne touche pas
`catalogue.yaml` et route le geste a moi et a `sofia-sm`.

**Cette decision est une nouvelle reference, pas une reecriture de `DEC-0049`.** Meme mecanisme que
`DEC-0048` pour `cp-nature-mot` : le retrait garde son propre numero et son propre motif, avec ses
chiffres du 2026-08-26 intacts ; le retour en porte un autre, pour que l'historique reste honnete
et qu'un lecteur futur puisse lire les deux etats sans que l'un efface l'autre. Une note datee est
ajoutee sous `DEC-0049` dans le meme geste, pas une reecriture sur place.

## 1. Verification personnelle avant de faire bouger quoi que ce soit

Je ne fais bouger aucune ligne sur la foi d'un titre de commit (`squad/README.md`, section 9,
etape 6) : j'ai lu la fiche entiere, le verdict du 2026-08-26 et celui du 2026-08-27, pas
seulement le dernier paragraphe.

- **La barre exigee par le refus est reprise mot pour mot, pas affaiblie.** `DEC-0049` exigeait,
  verbatim : *"un cinquieme libelle distingue le point d'interrogation du point final, et au moins
  trois items exigent la distinction"* et *"a 744 x 551, apres un echec, le verdict et l'indice sont
  entierement au-dessus du pli sur les seize items"*. Le premier point est mesure tenu : la
  simulation d'un enfant qui ignore le point d'interrogation passe de 16 sur 16 a 13 sur 16, trois
  items exigeant desormais la distinction (items 10, 11, 12) - trois sur trois, la barre exacte.
- **Le second point n'est pas tenu a la lettre, et `nadia-prof` le dit elle-meme avant de lever le
  refus quand meme.** L'indice reste sous le pli sur 16 items sur 16 (44,19 a 92,19 px). Elle ne
  leve pas cette partie de sa propre barre par complaisance : elle verifie sa propre phrase
  ("c'est la barre que j'applique depuis s17") et la trouve fausse pour l'indice - balayage du
  catalogue servi, 17 jeux, 157 items, 8 jeux sur 17 et 43 items sur 157 ne la tiennent pas non
  plus, dont deux plus bas que `cm1-reperes` corrige. Maintenir le refus sur ce seul point
  reviendrait a deplacer le poteau. Je retiens son raisonnement plutot que de rejuger le fond
  pedagogique, qui n'est pas le mien.
- **Le verdict, lui, est desormais lisible sur 14 items sur 16** (0 sur 16 au refus) : le defaut
  concret decrit dans le refus - *"l'enfant presse un bouton et ne voit pas s'il a eu raison"* -
  est ferme sur 14 items sur 16.
- **Les cinq autres conditions (`REP-1`, `REP-2`, `REP-3`, exactitude, `REP-5`, `REP-6`)** restent
  tenues ou corrigees, remesurees sur le paquet servi le 2026-08-27, pas admises sur la description
  de la correction.

**Ma lecture confirme le verdict : `VALIDE SOUS RESERVE`, aucune reserve bloquante restante.**

## 2. `cm1-reperes` retourne au catalogue

Le motif du retrait decide par `DEC-0049` est leve. Meme mecanisme que tout retour deja pratique
sur ce produit (`DEC-0048`, section 2) : l'entree `id: bandit-manchot-reperes-cm1` revient dans
`adapters/adapter-content/src/main/resources/catalogue.yaml`, dans la forme qu'elle portait avant
son retrait. **Je ne modifie pas `catalogue.yaml` moi-meme** : il vit sous `adapters/`, hors de mon
perimetre d'ecriture. Action routee a `sofia-sm`, avec le controle devenu obligatoire par
`DEC-0049` (verifier qu'une fiche de conformite avec un dernier verdict ni vide ni `REFUSE`
accompagne toute entree exposee ou maintenue).

## 3. Mouvement de matrice

**"Reperer et corriger la majuscule et la ponctuation dans une phrase donnee..."** (francais CM1)
passe `en cours` -> `couvert-valide`.

**Ce que ce statut recouvre** : la connaissance des quatre reperes de l'intitule sur une phrase
donnee, mesuree et tenue (`REP-4`) ; le pli lisible sur le verdict, 14 items sur 16 apres un echec.

**Ce que ce statut ne recouvre pas, nomme pour que personne ne le croie couvert** (dans les mots de
`nadia-prof`, repris sans les affaiblir) :

- La ligne d'origine ("Relire et corriger son texte a l'aide de reperes simples") reste `non
  couvrable en l'etat`, inchangee : ce paquet ne couvre que la connaissance des reperes sur une
  phrase donnee, jamais la relecture par l'enfant de son propre texte.
- **`REP-7`** - la composition des trois propositions par item predit la reponse sans lire la
  phrase sur 4 items sur 16 (esperance 10 sur 16 au lieu de 5,33 au hasard).
- **`REP-8`** - l'indice reste hors de vue apres un echec sur 16 items sur 16 (44,19 a 92,19 px sous
  le pli). Non retenue contre ce paquet : voir section 4.
- **`REP-9`** - le verdict est coupe en deux sur les 2 items a enonce de deux lignes.
- **`REP-10`** - l'indice de l'item 15 nomme la reponse de trop pres.

**Le chiffre, avec sa base, verifie ligne par ligne contre `squad/programme/MATRICE-couverture.md`
plutot que suppose (2026-08-27).** Le denominateur atteignable reste **43** sur **47**, inchange :
aucune ligne `non couvrable en l'etat` ne bouge. Le compte de lignes `couvert-valide` passe de
**14** a **15**. **14/43 (32,6 %) devient 15/43 (34,9 %)**.

## 4. `REP-8` est un defaut de catalogue, pas de ce paquet - je ne l'affecte pas a `lea-dev`

`nadia-prof` le dit explicitement et je le retiens sans le rejuger : *"Ce n'est pas un defaut de
`cm1-reperes` : c'est le premier defaut pedagogique du catalogue"*, mesure sur 8 jeux sur 17 et 43
items sur 157 (catalogue servi, une partie par jeu, 2026-08-27), dont deux jeux l'enfoncent plus bas
que `cm1-reperes` corrige (`perimetre-cm1`, `grands-nombres-cm1`) et un sur la totalite des items
vus (`problemes-additifs-cp`). La cause qu'elle mesure n'est pas dans un contenu : elle est dans le
budget vertical du cabinet et la taille de `.proposition`, qui plafonne le libelle utile a 10
caracteres pour trois propositions sur une rangee.

**Confrontation avec l'obstacle le plus lourd du planning de s20** (*"95 items sur 296 (32 %), sur
dix jeux, ont deja leur bloc de message sous le pli a la reference (744 x 551) ; les dix jeux sont
tous `couvert-valide`"*, `squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/
planning.md`, ligne 204, mesure de `theo-archi` du 2026-08-25,
`mesure-budget-vertical.md`) - je confronte les deux mesures chiffre a chiffre plutot que de les
supposer identiques ou distinctes :

| | Mesure de `theo-archi` (2026-08-25) | `REP-8` de `nadia-prof` (2026-08-27) |
|---|---|---|
| Perimetre (jeux) | 10 jeux, tous `couvert-valide`, isoles par l'etude de la bande 576-719 px | 17 jeux, le catalogue tel que servi a `77dd467` (`cm1-reperes` alors hors catalogue) |
| Population | 296 items, exhaustive (paquet complet lu depuis le chargeur reel) | 157 items, un **plancher** : tirage aleatoire, une partie par jeu, 7 a 10 items vus par jeu |
| Etat mesure | le plus charge : trois echecs puis revelation | apres **un seul** echec |
| Ce qui est mesure | bas du bloc entier `.message-jeu` (signal, verdict, explication, indice) sous le pli | visibilite entiere du **verdict** et de l'**indice**, mesures separement |
| Resultat | 95 sur 296 (32 %) sous le pli, pire deficit 164,19 px | indice sous le pli : 8 jeux sur 17, 43 items sur 157 ; verdict sous le pli : 3 jeux sur 17, 5 items sur 157 |

**Trois des dix jeux de la mesure de `theo-archi` figurent dans les huit jeux de `REP-8`** -
`bandit-manchot-problemes-additifs-cp`, `bandit-manchot-perimetre-cm1` et
`bandit-manchot-grands-nombres-cm1`, nommes dans les deux documents - ce qui confirme qu'il s'agit
de la **meme famille de defaut**, sur la meme cause nommee des deux cotes (budget vertical du
cabinet, taille de `.proposition`). **Les deux chiffres ne se somment pas et ne se comparent pas
terme a terme** : perimetres de jeux differents (10 contre 17, tous deux non embotables l'un dans
l'autre), population exhaustive contre plancher echantillonne, etat le plus charge contre un seul
echec, metrique combinee (un bloc) contre deux metriques separees (verdict, indice). Traiter "95 sur
296" et "43 sur 157" comme une seule population ou comme deux defauts independants serait aussi
faux l'un que l'autre.

**Je ne prononce aucun statut de couverture sur ces items, ni ici ni ailleurs**, comme je m'y suis
deja tenue au planning de s20 (`planning.md`, ligne 204 : *"personne n'a qualifie ces items,
`camille-po` ne prononce aucun statut de couverture dessus"*). Ce que je fais : j'en tire une story
de backlog non affectee (`REP-8`, `docs/backlog/backlog.md`, section "Sprint 20"), routee comme
`nadia-prof` le demande - a moi, avec `ines-design` et `theo-archi` sur la cause de forme, pas a
`lea-dev` comme une reprise de paquet.

## 5. Backlog - quatre reserves non bloquantes, non affectees a un sprint

`REP-7`, `REP-8`, `REP-9`, `REP-10` entrent dans `docs/backlog/backlog.md`, section "Sprint 20",
sans affectation de sprint : le planning de s21 n'est pas ecrit et ce sprint se cloture. `REP-7`,
`REP-9` et `REP-10` sont propres au paquet `cm1-reperes` et routees a `lea-dev` ; `REP-8` est une
story de catalogue routee a moi, `ines-design` et `theo-archi`, jamais a `lea-dev`.

**Collision de nommage signalee, pas corrigee ici.** `docs/backlog/backlog.md`, section `S20-2`,
porte deja des labels "`REP-7` (pli)" et "`REP-8` (quatrieme repere)" que j'avais moi-meme inventes
le 2026-08-25 pour nommer les deux criteres correctifs du refus - **ce ne sont pas les `REP-7` et
`REP-8` que `nadia-prof` nomme le 2026-08-27**, qui portent sur un contenu entierement different
(prediction sans lecture ; indice hors de vue, catalogue entier). Je ne renomme pas mes labels du
2026-08-25 : ils sont deja references par leur date et leur objet, et les reecrire effacerait la
trace de ce qui a motive la correction de `lea-dev`. Je signale la collision a l'endroit ou elle
se lit, dans le backlog lui-meme, pour qu'un lecteur futur ne confonde pas les deux.

## Ce que je route

- **`sofia-sm`** : retour de l'entree `id: bandit-manchot-reperes-cm1` dans `catalogue.yaml`, dans
  le meme geste que le controle rendu obligatoire par `DEC-0049`.
- **`ines-design`** et **`theo-archi`** : `REP-8`, cause de forme (budget vertical du cabinet,
  taille de `.proposition`), routee comme story de catalogue - detail dans
  `docs/backlog/backlog.md`.
- **`lea-dev`** (via `sofia-sm`) : `REP-7`, `REP-9`, `REP-10`, reprise du paquet `cm1-reperes` -
  aucune n'est bloquante, aucune n'est affectee a s21 par ce document.

## Ce que je ne fais pas

- Je ne rejuge pas le verdict de `nadia-prof` ni le raisonnement par lequel elle leve sa propre
  barre sur le pli de l'indice : je le reprends tel quel.
- Je ne modifie pas `catalogue.yaml` moi-meme.
- Je ne prononce aucun statut de couverture sur les items de la mesure de `theo-archi` ni sur ceux
  de `REP-8` au-dela de la ligne qu'ils ne font pas bouger.
- Je n'affecte `REP-7`, `REP-8`, `REP-9`, `REP-10` a aucun sprint : le planning de s21 n'est pas
  ecrit.
- Je ne renomme pas mes propres labels du 2026-08-25 dans `docs/backlog/backlog.md` : je signale la
  collision, je ne reecris pas l'historique.

## Impact

- `squad/decisions/produit/DEC-0050-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0050` ajoutee ; note datee ajoutee sous
  `DEC-0049`.
- `squad/programme/MATRICE-couverture.md` : ligne "Reperer et corriger la majuscule et la
  ponctuation..." `en cours` -> `couvert-valide` ; synthese chiffree mise a jour, 15/43 (34,9 %).
- `docs/backlog/backlog.md` : `S20-2` mise a jour (Done pedagogique acquis sous reserve, geste
  catalogue route) ; quatre reserves `REP-7` a `REP-10` ajoutees en backlog non affecte.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : `bandit-manchot-reperes-cm1`
  ajoutee - execution par `sofia-sm`.
- Aucun fichier sous `domain/`, `application/`, `adapters/` (hors le catalogue route) ni
  `bootstrap/` n'est modifie par cette decision.
