# Prix des rejeux de LP6 (`S57-BR-EMC-CE1-04`, `ce1-emc-interet-commun`) - nadia-prof, instance B

- **Date** : 2026-10-24, 09:57 (UTC+02:00, lu par `date`). s57, entre le palier 4 (clos) et le palier 5.
- **Base lue** : `round-two` a `4ff3bd7e`. `registre-prix-palier-4.txt` (20 lignes), `arbitrage-camille-cloture-palier-4.md` (P5a a
  P5c), `DEC-0120` A.176 et A.186, `FICHE-bandit-manchot-interet-commun-ce1.md` (trois verdicts et l'amendement du 2026-10-23),
  mon bareme (`arbitrage-nadia-palier-2.md` 5 ; `PAR-10`, `PAR-14`, `PAR-15` dans
  `ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md`).
- **Objet** : le prix du rejeu 2, que le registre porte `non-chiffre` et que `DEC-0120` A.186 me laisse. Ce fichier ne modifie ni le
  registre, ni une `DEC`, ni la fiche, ni la matrice.

## 1. Decision

| Poste | Prix | Etat | Changement |
|---|---|---|---|
| Premier passage (`cbfb152e`) | **1,00** | deja `facture` | confirme |
| Rejeu 1 (`ebbb2136`, refus M3) | **0,50** | deja `facture` | prix confirme ; le libelle *"non mesuree"* est corrige (section 3) |
| Rejeu 2 (`c975a7b8`, Done sous reserve) | **0,50** | `non-chiffre` vers `facture` | chiffre neuf |
| **LP6, total** | **2,00** | | |

**`PAR-8` en A', palier 4 : 6,00 sur 5,0** (5,50 plus 0,50). C'est la seconde valeur d'A.186. Total du palier 4 : 13,00 sur 20,0.

## 2. Methode

Le prix est **mon bareme applique a des faits mesures**, pas une mesure de l'effort. Aucun compteur ne mesure ce qu'un audit me
coute ; je ne presente donc aucun chiffre comme une duree ou un volume de travail.

Bareme (`arbitrage-nadia-palier-2.md` 5, `PAR-10`) : lot textuel neuf 1,0 ; rejeu borne par paquet 0,5 quand des textes ou une forme
changent ; 0,25 quand le paquet ne change pas, ou quand la correction reprend mot pour mot un texte que j'ai fourni. Deux clauses
font passer un rejeu au tarif d'un lot : `PAR-14` (plus de la moitie des items touches) et `PAR-15` (une tache reecrite en entier, ou
une consigne et sa tache changees).

Ce que je compte, pour chaque passage : les items que le commit change (mesure sur le diff du paquet), si `PAR-14` ou `PAR-15` mord,
puis ce que j'ai lu et joue (lu dans la fiche, ecrit le jour meme).

**Diff du paquet**, mesure ce jour : enonces et indices de `ce1-emc-interet-commun.yaml` extraits par `git show <commit>:<chemin>`,
tries, compares par `comm -13` ; 24 enonces et 24 indices a chaque commit.

| Passage | Enonces absents du commit precedent | Indices absents du commit precedent | `PAR-14` (plus de 12 sur 24) | `PAR-15` |
|---|---|---|---|---|
| Rejeu 1, `cbfb152e` vers `ebbb2136` | 11 | 10 | ne mord pas | ne mord pas : une tache, consigne inchangee |
| Rejeu 2, `ebbb2136` vers `c975a7b8` | 10 | 5 | ne mord pas | ne mord pas : idem |

**Ce que j'ai lu et joue** (fiche, mes mots du jour) :

| Passage | Lecture | Parties jouees, comptees dans la fiche | Autre controle |
|---|---|---|---|
| Premier passage | 24 items (enonces, bonnes reponses, 48 distracteurs, 24 indices), en-tete, catalogue ; 58 items voisins (24, 11, 11, 12) | 13 (12 parties, plus une treizieme a trois erreurs) | regle du possessif jouee, 30 sur 30 |
| Rejeu 1 | 24 items, dont les 10 indices neufs ; 35 items voisins (11, 24) | 18 (8, 1, 3, 3, 3) | trois regles de surface jouees |
| Rejeu 2 | 24 items ; 35 items voisins (24, 11) | **25** (8 a erreur d'abord, 8 de comparaison des indices, 9 de regles de surface), plus des manches a trois erreurs que la fiche ne denombre pas | 80 manches d'indices comparees au fichier, 0 ecart ; trois regles comptees sur le texte (11, 17, 13 sur 24) |

**Correction du chiffre qui m'a ete transmis** : le rejeu 2 compte au moins 25 parties, et non 18. 18 est le compte du rejeu 1.

## 3. Rejeu 1 : 0,50, confirme

Le 2026-10-22 j'ai ecrit *"je l'estime a 0,5, ce n'est pas une mesure"*, avant que le lot soit corrige. Le diff est maintenant
mesurable : 11 enonces et 10 indices changes, aucune clause de lot ne mord. C'est le cas *"des textes changent"* de `PAR-10` : 0,50.
**Le prix ne change pas ; ce qui change, c'est qu'il n'est plus une estimation** : il est le bareme applique a un diff compte. Le
libelle du registre *"estimation de nadia-prof non mesuree"* est a remplacer (section 5).

## 4. Rejeu 2 : 0,50

**Correction de mon propre constat.** Le 2026-10-23 j'ai ecrit *"Je ne l'estime pas en prix : une phrase, a relire par moi sur
l'item 7 et le compte de M1, sans rejeu complet."* Cette phrase chiffrait ce que je demandais, pas ce qui a ete livre. Le commit
joue change 10 enonces et 5 indices : l'item 7 (ma phrase, mot pour mot), et neuf scenes ecrites par `sofia-sm` pour les reserves 4
et 5. Quatre scenes changent de categorie d'objet (6, 13, 17, 22 : personnel vers partage ou l'inverse). J'ai donc fait un rejeu
complet : lecture des 24 items, jugement d'une seule lecture sur sept scenes neuves, levee de M1, M3 et des reserves 4 et 5, deux
reserves neuves (6 et 7).

Pourquoi pas 0,25 : ce tarif vaut pour un paquet inchange ou une correction mot pour mot de mon texte. Seul l'item 7 l'est ; neuf
scenes sont des textes neufs a juger. Pourquoi pas 1,0 : 10 sur 24 n'est pas plus de la moitie (`PAR-14`), et la tache comme la
consigne restent celles du premier passage (`PAR-15`).

Precedents du meme palier, lus au registre et coherents avec ce chiffre : rejeu 3 de `EMC-CE1-02` (12 items, plus un item et un
indice) 0,50 ; rejeu 4 de `EMC-CE1-02` (item 9 seul) 0,25 ; rejeu de `EMC-CE1-03` (items 14 et 24) 0,25.

## 5. Ce que `camille-po` porte au registre

Ligne a remplacer (aujourd'hui `0.00|non-chiffre`), par celle-ci :

```
Ap|rejeu 2 de EMC-CE1-04, apres le refus du 2026-10-23 (M3), 10 scenes et 5 indices changes, prix de nadia-prof (PAR-10)|0.50|facture
```

Libelle du rejeu 1, meme prix, meme etat :

```
Ap|rejeu 1 de EMC-CE1-04, apres le refus du 2026-10-22 (M1 et M2), 11 scenes et 10 indices changes, prix de nadia-prof (PAR-10)|0.50|facture
```

Effet attendu par `awk -F'|'` apres ces deux remplacements : `Ap 6.00`, total 13,00, `facture` 10,00, `non-chiffre` 0,00. Ces sommes
sont calculees par moi depuis les 20 lignes lues (`Ap` rend 5.5 ce jour) ; je ne les ai pas lancees sur un fichier modifie, puisque
je ne le modifie pas.

## 6. Ce que ce prix fait ailleurs

- **`DEC-0120` A.186** : la valeur du relevement de A' au palier 4 est **6,00**. L'amendement qui l'ecrit est a `camille-po`.
- **Surcout par lot du palier 4** (`proposition-camille-palier-5-ce1.md` 4.1). Les chiffres *0,10* (palier 3) et *0,58* (palier 4) y
  sont de `camille-po`, pas de moi : je n'ai chiffre aucun precedent par lot, seulement chaque rejeu au bareme. Avec ce prix, son
  calcul (3,50 de surcout pour 6 lots) devient 4,00 pour 6, soit 0,67 par lot. Le recompte et ses consequences sur la composition du
  palier 5 sont a elle.

**Signe** : `nadia-prof`, 2026-10-24.
