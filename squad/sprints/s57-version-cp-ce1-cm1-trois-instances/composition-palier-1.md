# s57 - composition du palier 1 : proposition de `camille-po`

- **Auteure** : `camille-po`, 2026-10-14 14:29 (UTC+02:00), lu par `date`. Arbre : `round-two` a `544159d9`, worktree dedie.
- **Ce fichier n'est pas `planning.md`** : s57 n'est pas ouvert, aucun horodatage de debut, aucun objectif gele. La composition d'un palier est a `camille-po` (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 11.3 condition 6 et 11.8 ; `duree-et-paliers.md` P4').
- **Statut** : proposition. **Chaque affectation d'instance ci-dessous est proposee** : l'affectation des lots aux instances et le jugement de `PAR-13` sont a `nadia-prof` (`DEC-0120` A.33, A.36), qui les ecrit dans l'arbitrage prealable de chaque instance avant le premier lot du palier (11.3 condition 3).
- **Sources** : `DEC-0120` A.33 (repartition retenue), A.39 (elle se rouvre quand la borne est ecrite), A.44, A.50, A.53 ; `ARBITRAGE-PREALABLE-s57-criteres-...` 4.2, 4.3, 10.2, 11.3 ; `duree-et-paliers.md` ; matrice (`squad/programme/MATRICE-couverture.md`, lignes `francais` / `CE1` `non couvert`).

## 1. Ce que le palier 1 doit tenir

| Regle | Valeur | Source |
|---|---|---|
| Borne du palier, somme des budgets des instances | **8,0** | `PAR-9` ; 11.3 condition 1 |
| Plafond dur par instance | **5,0** | `PAR-8` |
| Lots par instance | **trois au plus** | `PAR-8` |
| Marge de rejeu | **0,5 par instance**, nommee a l'ouverture, allouee a aucun lot | 11.3 condition 1 |
| Arbitrage prealable | 0,5 par instance, ecrit avant le premier lot du palier | 11.3 condition 3 |
| Chiffre au-dela de 8,0 dans un palier | **aucun** : O2 non derivable, (ii) non etablie | 11.4 ; `DEC-0120` A.50 |
| Groupes | X et Y de `DEC-0120` section 1, **aucun lot ne change de groupe** ; `-08` et `-09` ne sont pas des lots de s57 | accord `PAR-2`, 4.2 conditions 1 et 4 |

## 2. Composition proposee

Prix : ceux de `nadia-prof` (arbitrage, 4.3) ; son arbitrage prealable de chaque instance les confirme ou les change.

| Instance | Poste | Prix | Nature | Affectation |
|---|---|---|---|---|
| **A (groupe X)** | arbitrage prealable | 0,5 | fixe | proposee |
| | marge de rejeu | 0,5 | fixe, allouee a aucun lot | proposee |
| | `VSJ-CE1-R1` (`S56-VSJ-CE1-R1`, `FRA-CE1-13`) | 0,5 | reserve, pas un lot ; reste a A (4.2 condition 3) ; developpeur `marc-dev` (backlog) | proposee |
| | `FRA-CE1-04`, paquet `ce1-texte-narratif` | 1,0 | lot | **proposee** |
| | **Total A** | **2,5** | 1 lot | |
| **A' (groupe Y)** | arbitrage prealable | 0,5 | fixe | proposee |
| | marge de rejeu | 0,5 | fixe, allouee a aucun lot | proposee |
| | `FRA-CE1-10`, paquet `ce1-phrases-types` | 1,0 | lot | **proposee** |
| | `FRA-CE1-15`, paquet `ce1-accord-groupe-nominal` | 1,0 | lot | **proposee** |
| | **Total A'** | **3,0** | 2 lots | |
| **B** | arbitrage prealable | 0,5 | fixe | proposee |
| | marge de rejeu | 0,5 | fixe, allouee a aucun lot | proposee (question Q-P1) |
| | rejeu d'`ART-CP-10` (`S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2`) | 0,5 | rejeu, pas un lot (`PAR-10`) | proposee |
| | `S54-OBS-SCI-CONSIGNE`, developpeur `lea-dev` (`DEC-0120` A.44) | 0,25 | poste, pas un lot | proposee |
| | non affectes | 0,75 | `Q-N1` (b) : restent non affectes | - |
| | **Total B** | **2,5** | 0 lot | |

**Verification d'arithmetique** (commande `awk` sur les prix du tableau, sortie lue le 2026-10-14) : A 2,5 ; A' 3,0 ; B 1,75 affectes ; somme 8,0 avec les 0,75 non affectes de B ; 7,25 affectes. Plafond de 5,0 : A 2,5, A' 3,0, B 2,5, aucun ne s'en approche. Lots : 1 + 2 + 0 = 3, aucune instance au-dela de trois.

## 3. Pourquoi cette composition

1. **C'est la repartition retenue en `DEC-0120` A.33, sans changement.** A.39 la rouvrait *"quand la borne est ecrite"* ; la borne par palier est 8,0 (11.3), la somme de A.33. La colonne "sans lot de `G4`" reste : l'ordre de la section 1 de `DEC-0120` met le francais CE1 (rang 1) avant `G4` (rang 4a), et A.35 dit qu'aucun lot de `G4` n'entre quand B n'a que 0,75 de libre.
2. **Un quatrieme lot de francais CE1 ne tient pas dans ce palier.** 7,25 affectes plus 1,0 = 8,25, au-dessus de 8,0. Les 0,75 de B ne portent aucun lot : *"un lot neuf vaut 1,0 a mon bareme, plus que 0,75"* (`nadia-prof`, 10.2 (b)).
3. **Les lots sont pris dans le rang 1.** Sont disponibles a s57 : X `-04`, `-05`, `-06`, `-07`, `-11` ; Y `-10`, `-15`, `-24` (`-08` et `-09` exclus, 4.2 condition 4). Matrice, lignes `non couvert` du francais CE1 : dix lignes de lot et les deux lignes plafonnees de `G3` (`FRA-CE1-03`, `-21`), hors rang 1.
4. **Choix d'un lot dans chaque groupe : je n'ai aucun motif pedagogique et je n'en invente pas.** J'ai pris l'ordre du referentiel (`-04` pour X ; `-10` puis `-15` pour Y), `-24` restant pour un palier suivant. C'est un ordre de lecture, pas un jugement : `nadia-prof` tranche, y compris si elle prefere un autre lot de X ou de Y pour la rencontre de ses conditions avec du contenu (`PAR-13`).
5. **B n'a rien d'autre a prendre.** Rang 2 epuise, rang 3 vide a s57, `G4` non place ici (point 1), aucun autre poste de `DEC-0120` n'est a B.

**Ce que le palier 1 laisse, a titre d'information, sans composer le palier 2** : cinq lots de francais CE1 (X : `-05`, `-06`, `-07`, `-11` ; Y : `-24`). La composition du palier 2 est un amendement date de `planning.md` (`duree-et-paliers.md` P4'), ecrit apres la cloture du palier 1 et l'arbitrage de `nadia-prof` ; ce fichier ne la prejuge pas.

## 4. Etat de chaque ligne, mesure le 2026-10-14

| Ligne | Etat | Consequence |
|---|---|---|
| Les trois lots (`FRA-CE1-04`, `-10`, `-15`) | **aucune story au backlog** : `grep -c '^### S57-BR' docs/backlog/backlog.md` rend 0 | **Non engages.** Une story qui n'est pas dans `docs/backlog/` n'existe pas. **Premier report de ce geste, s57, 2026-10-14** : je ne les ecris pas ici, ce n'est pas le perimetre de la demande. Elles s'ecrivent (modele : `S56-BR-FRA-CE1-13`, avec criteres verifies contre l'existant) **avant** que `planning.md` les engage |
| `VSJ-CE1-R1` | story au backlog (`S56-VSJ-CE1-R1`, ligne 21292), non engagee, critere inchange | engageable au planning |
| Rejeu d'`ART-CP-10` | stories au backlog ; la forme de la correction (`theo-archi`, `ines-design`, `lea-dev`) et la maniere de rejouer un paquet sans entree de catalogue ne sont pas ecrites (`DEC-0120` 4.2) | si la correction n'est pas fusionnee quand le rejeu devrait commencer, il glisse : **premier report de ce poste**, dit le jour meme (`DEC-0120` 4.2) |
| `S54-OBS-SCI-CONSIGNE` | placement complet (`DEC-0120` A.44) ; story au backlog | un troisieme report a s58 est interdit. **Ma lecture, a confirmer par `sofia-sm` au planning** : un glissement du palier 1 au palier 2 est un glissement de palier (11.3 condition 2), pas un report de sprint ; la cloture de s57 reste le point de l'acte ecrit d'abandon |

Aucune fiche de conformite n'est ouverte ici : les lots sont des branchements de paquets existants, et la fiche s'ouvre apres le commit de branchement (`DEC-0115` A.11).

## 5. Questions

| Code | Question | A qui |
|---|---|---|
| `Q-P1` | B ne porte aucun lot au palier 1 ; 11.3 condition 1 nomme la marge de rejeu *"par instance qui porte un lot"*, alors que 4.3 en donne une a B. Garde-t-elle la marge de 0,5 pour le rejeu d'`ART-CP-10` ? Si non, B passe a 2,0 affectes et 1,25 non affectes, la somme reste 8,0 | `nadia-prof`, routee par `sofia-sm` |
| `Q-P2` | Le choix de `-04`, `-10`, `-15` (point 4) est un ordre du referentiel. Quel lot de X et quels deux lots de Y veut-elle au palier 1 ? | `nadia-prof`, dans l'arbitrage de A et A' |

**Porte, pas une question** : avant le premier worktree d'un lot de A', `git merge-base --is-ancestor 729b0d8a HEAD`, sortie 0, citee dans le rapport du developpeur (`duree-et-paliers.md` section 5).

## 6. Ce que ce fichier ne fait pas

- N'ouvre pas s57, n'ecrit aucun `planning.md`, ne gele aucun objectif, n'engage aucune ligne.
- Ne prononce aucun Done, ne juge aucun contenu, ne tranche aucune affectation : toutes sont proposees.
- Ne compose pas le palier 2, ne pose aucun chiffre au-dela de 8,0 par palier, ne derive pas O2.
- Ne designe aucun developpeur hors ceux que le depot nomme deja (`marc-dev` pour `VSJ-CE1-R1`, `lea-dev` pour `S54-OBS-SCI-CONSIGNE`) ; les autres sont a `sofia-sm`.
- Ne touche ni la matrice, ni un catalogue, ni un paquet, ni du code.
