# Rapport de sprint s47 - tables de multiplication CE1, modes de vie autrefois et culture
anglophone au CP (instance A), gestes d'alerte et sens du vote en EMC CP/CM1 (instance B)

- **Duree** : 2026-10-02 16:17:00 -> 2026-10-02 23:03:35 (UTC+02:00), 6 h 46 min 35 s.
- **Objectif gele** (`DEC-0104`, Acceptee, engagee le 2026-10-02) : fermer cinq lignes avec deux
  instances paralleles de `nadia-prof` - `MAT-CE1-07`, `QLM-CP-15`, `LVE-CP-06`, `EMC-CP-14`,
  `EMC-CM1-10`. Budget de sprint opposable 8,0, total engage 7,0/8,0, marge 1,0 non allouee.

## L'increment livre, en une phrase

Cinq lignes du programme ferment a `couvert-valide` - tables de multiplication CE1, comparer des
modes de vie d'autrefois et d'aujourd'hui en CP, decouvrir des aspects culturels des pays
anglophones en CP, connaitre les gestes d'alerte en CP, comprendre le sens du vote et de la
representation en CM1 - apres un episode de correction d'orthographe qui a mene a reparer un
harnais de test plutot que degrader un contenu juste, et apres trois refus pedagogiques reels sur
trois lots distincts, tous leves dans la meme soiree par des corrections de contenu testees et
revalidees ; la couverture du programme passe de 113/182 (62,1 %) a **118/182 (64,8 %)**.

## Les decisions du sprint

**Aucune `DEC` ni aucun `ADR` n'est cree ou amende apres l'ouverture du planning** (`git diff
--name-status bac3b58e 25860e82 -- squad/decisions/produit squad/decisions/adr` est vide ; seul
`squad/decisions/REGISTRE-identifiants.md` bouge, par allocation d'identifiants, pas par
decision). Une decision est creee **avant** l'ouverture du planning, le meme jour, comme
prealable necessaire a son ouverture :

| Decision | Numero | Statut | Objet |
|---|---|---|---|
| `DEC-0104` | Produit | Acceptee, engagee le 2026-10-02 | Integration du referentiel francais CE1 a la matrice, et strategie de s47 : deux instances paralleles de `nadia-prof`, une ligne de mathematiques CE1, une ligne de questionner le monde CP, une ligne de langues vivantes (anglais) CP, et une ligne chacune d'EMC CP et d'EMC CM1 |

Aucune autre `DEC` ni `ADR` n'est touche par s47. Le sprint n'a produit aucune `DEC` ni `ADR`
nouveau pendant son execution : la seule decision du perimetre est celle qui l'a ouvert, deja
engagee avant l'horodatage de debut du planning.

Une seule decision m'appartient en propre, et elle est de capacite, pas de produit ni
d'architecture : **affecter `marc-dev` seul sur le lot de complexite moderee (`MAT-CE1-07`, cinq
familles de distracteur et double lecture d'un meme fait), et repartir les quatre autres lots de
basse complexite entre `lea-dev` et `sofia-sm` (casquette dev), un par instance chacun**
(`planning.md`, section 9).

## Etat des deux Done

### Technique - rapport de `yanis-qa` et confirmation ArchUnit de `theo-archi`, tous deux verts

`bash outils/mvn-jdk21.sh -B clean verify` : **BUILD SUCCESS**, 6 modules sur 6, JDK constate
**21.0.10**. **1938 tests (1932 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures
independamment par `yanis-qa` et par `theo-archi`, identiques. ArchUnit inchange : 36
`static final ArchRule`, 35 `@ArchTest` (`contreexemple` exclu). **Zero ligne de Java de
production sur les cinq lots** : contenu YAML et tests de paquet seulement. Les trois autres jobs
de la CI (`chemins`, `prefixes`, `outils`) sont verts, lances par `theo-archi` sur son propre
diff (`confirmation-archunit.md`, section 7).

**Deux garde-fous mis a l'epreuve par mutation reelle du materiau de production servi** (pas une
fixture) : la ligature "oe"/"oeufs" que le correctif du harnais venait de reparer, et la
cinquieme famille de distracteur de `TAB-CE1-6`. Les deux rougissent puis reverdissent comme
attendu. Detail complet, y compris l'episode "oeufs" et sa correction : `review.md`, sections 2 a
4 ; `retro.md`, section 1.

### Pedagogique - deux instances, trois refus leves dans la meme soiree, cinq Done a la cloture

| Ligne | Instance | Verdict final | Reserves | Ce qui ferme |
|---|---|---|---|---|
| Tables de multiplication (`MAT-CE1-07`) | A | **VALIDE SOUS RESERVE**, des le 1er passage | `TAB-CE1-R1` | `couvert-valide` |
| Autrefois et aujourd'hui (`QLM-CP-15`) | A | **VALIDE SOUS RESERVE**, au 3e passage (2 refus) | `AUT-CP-R1` | `couvert-valide` |
| Le monde anglophone (`LVE-CP-06`) | A | **VALIDE SOUS RESERVE**, des le 1er passage | `ANG-CP-R1`, `ANG-CP-R2` | `couvert-valide`, mention de couverture partielle |
| Donner l'alerte (`EMC-CP-14`) | B | **VALIDE SOUS RESERVE**, au 2e passage (1 refus) | deux reserves (indice de l'item 7, vocabulaire de l'item 11) | `couvert-valide` |
| Le vote et la representation (`EMC-CM1-10`) | B | **VALIDE SOUS RESERVE**, au 3e passage (2 refus) | deux reserves (distracteur hors categorie, question mal formee) | `couvert-valide`, mention de couverture partielle |

**Trois refus pedagogiques reels sur trois lots distincts (taux eleve : 3 lots sur 5), tous leves
dans la meme soiree** : `QLM-CP-15` (tache de rattachement qui se repondait par le temps du verbe
et item 14 faux, puis un distracteur juste a la correction suivante, corrige par `lea-dev` en deux
temps) ; `EMC-CP-14` (item 5 sans danger decrit et indice enoncant une regle fausse, corrige par
`lea-dev`) ; `EMC-CM1-10` (items 5 et 6 faux sur le droit de vote et sa date, puis une exclusivite
fausse introduite par la correction du premier, corrige par `sofia-sm` en deux temps). **Deux des
trois refus en seconde instance etaient des defauts neufs introduits par la correction
precedente**, pas des motifs recurrents du premier passage. Chronologie complete, commits et
horodatages : `review.md`, sections 4 et 5 ; analyse : `retro.md`, section 2.

**Huit reserves non bloquantes**, routees en cinq stories de correction differee, deja ecrites
par `camille-po` dans `docs/backlog/backlog.md` au moment de la cloture de la matrice (commit
`506c5137`) :

- `TAB-CE1-R1` seule ("Memoriser ses tables de multiplication").
- `AUT-CP-R1` seule ("Autrefois et aujourd'hui").
- `ANG-CP-R1`, `ANG-CP-R2` groupees ("Le monde anglophone").
- Deux reserves groupees, sans identifiant dedie ("Donner l'alerte").
- Deux reserves groupees, sans identifiant dedie ("Le vote et la representation").

Aucune de ces stories n'est engagee dans un planning a ce jour.

## Couverture du programme

**118/182 (64,8 %)**, en hausse de 2,7 points sur l'etat a l'ouverture de s47 (113/182, 62,1 %).
Cinq fermetures, aucune ouverture partielle, aucun refus restant a lever - verifie par moi sur
`squad/programme/MATRICE-couverture.md`, note de sprint en tete et les cinq lignes elles-memes.

## Obstacles resolus et encore ouverts

**Resolu avant l'ouverture du planning** : le blocage de `TAB-CE1-6` sur `10 × 1`
(`MAT-CE1-07`), leve par l'amendement de `nadia-prof` (cinquieme famille de distracteur) avant
l'horodatage de debut. **Resolus pendant le sprint** : l'episode "oeufs" (diagnostic corrige par
`theo-archi`, harnais repare, contenu reetabli sans degradation) et les trois refus pedagogiques
reels, chacun leve par une ou deux corrections de contenu testees et revalidees. Tableau complet,
avec commits et horodatages : `review.md`, section 6.

**Encore ouverts, aucun bloquant** : les cinq stories de correction differee (huit reserves),
non engagees dans un planning ; `SOP-CE1-R1`, reserve de s46 instruite hors du chiffrage de s47 -
`nadia-prof` admet une cinquieme forme de distracteur et garde la reserve ouverte jusqu'a ce
qu'elle rejoue le paquet repris, dont la reprise elle-meme n'est pas engagee.

## Prochain perimetre

**Non propose par cette cloture, et aucune proposition n'existe a ce jour dans le depot** :
proposer ou arbitrer un perimetre n'est pas le role de cette cloture, il appartient a
`camille-po`. Deux candidates signalees mais non engagees : l'instruction ligne par ligne du
referentiel francais CE1 (26 lignes, integre a la matrice par `DEC-0104`, aucun avis de
faisabilite ni arbitrage prealable ecrit a ce jour) et `MAT-CE1-08` (problemes a une ou deux
etapes), disponible au meme tarif et aux memes conditions que `MAT-CE1-07` (`DEC-0104`,
section 2.1).

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s47`, fenetre 2026-10-02 14:17 -> 2026-10-02 21:03
UTC (horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 56 | 112 | 35 713 | 479 914 | 5 418 598 |
| `sofia-sm` | claude-sonnet-5 | 409 | 818 | 160 582 | 2 317 606 | 66 527 443 |
| `theo-archi` | claude-opus-5-5 | 113 | 230 | 44 673 | 890 424 | 6 070 902 |
| `marc-dev` | claude-sonnet-5 | 307 | 614 | 143 535 | 3 215 722 | 73 153 287 |
| `lea-dev` | claude-sonnet-5 | 534 | 1 068 | 209 691 | 4 102 180 | 98 651 349 |
| `yanis-qa` | claude-sonnet-5 | 171 | 342 | 46 645 | 699 482 | 21 430 010 |
| `relecteur-pedagogique` | claude-sonnet-5 | 43 | 86 | 8 576 | 124 634 | 1 535 894 |
| `nadia-prof` | claude-opus-5-5 | 563 | 1 130 | 246 663 | 2 208 629 | 50 979 189 |
| orchestrateur (fil principal) | claude-sonnet-5 | 262 | 524 | 157 379 | 1 279 106 | 149 449 130 |
| **Total** | | **2458** | **4 924** | **1 053 457** | **15 317 697** | **473 215 802** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait
du sur ce sprint. Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) :
les additionner donnerait un classement faux, le cache lu depassant couramment l'output de deux
ordres de grandeur.
