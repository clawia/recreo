# Proposition pour s56 - a `sofia-sm`, a `nadia-prof` et a l'utilisatrice

**Ce fichier n'est pas la `DEC` d'ouverture de s56 ni son planning.** Il n'engage aucun poste, n'affecte personne, n'ouvre aucun worktree, ne fixe aucun tarif
(ceux de `nadia-prof` sont cites tels qu'elle les ecrit dans les fiches) et ne tranche ni l'ordre des matieres ni le financement d'`ART-CP-10`, qui sont a
l'utilisatrice. La `DEC` d'ouverture de s56 s'ecrit apres lui (`DEC-0114` A.20, `squad/README.md` section 4).

- **Auteur** : `camille-po`, 2026-10-08, sur `af8b326e` (`round-two`, arbre fusionne de s55). Matrice : **141 / 182 (77,47 %)**, note de s55 de `squad/programme/MATRICE-couverture.md`.
- **Sources** : blocs de verdict des six fiches de s55, `audit-nadia-a.md`, `audit-nadia-b-emc.md`, `audit-nadia-b-exc.md`, `ARBITRAGE-COMPLEMENT-s55-instance-a.md`, `rapport-lea-dev-mecanique.md`,
  backlog ("Cloture de s55"), `DEC-0114` section 7, `DEC-0115` section 3 et amendement A.14 a A.17.

## 1. Ce qui reste, mesure a `af8b326e`

Commande (perimetre : le worktree entier, aucune mesure partielle) :
`comm -23 <(ls adapters/adapter-content/src/main/resources/contenu | sed 's/\.yaml$//' | sort) <(grep -oE 'paquet: [a-z0-9-]+' adapters/adapter-content/src/main/resources/catalogue.yaml | sed 's/paquet: //' | sort -u) | wc -l`
rend **37** fichiers de contenu sans entree de catalogue (182 fichiers, 145 paquets au catalogue). A la proposition de s55 : 39 paquets, sans compter deux fichiers refuses a s51 ; 39 - 4 branchements = 35, plus ces deux fichiers = 37.

| Groupe | Reste | Detail | Ferme une ligne | Source |
|---|---|---|---|---|
| `G1` + `G2` francais CE1 | **11** | groupe X (6) : `texte-narratif` (`-04`), `pronoms` (`-05`), `inference` (`-06`), `texte-documentaire` (`-07`), `negation` (`-11`), `verbe-sujet` (`-13`) ; groupe Y (5) : `ordre-des-mots` (`-08`, `DEGRADE`), `relire-une-phrase` (`-09`, `DEGRADE`), `phrases-types` (`-10`), `accord-groupe-nominal` (`-15`), `familles-de-mots` (`-24`) | oui, 11 lignes | `DEC-0114` 7.2 (groupes X et Y) moins `-12` et `-14` ; `-09` renvoie a `-14` (fait) et a `-15` (`RLP-CE1-1`) : `-15` avant `-09` |
| `G5` EMC | **0 branchable** | `cp-emc-personnes-differentes` (`EMC-CP-04`) reste **sous refus** (`DEC-0081` section 7), pas un lot | non | s55 : `-03` et `-06` branches |
| `G7` a `G11` | **16** | plafonds ecrits par `nadia-prof` (arbitrage A de s55, section 6) : 15 sur 16 `non couvert` ; **seul `cm1-lire-et-restituer` (`FRA-CM1-01`) peut fermer**, apres une mesure de pli d'`ines-design` non faite, puis un lot a 1,0 | au plus 1 | arbitrage A, section 6 |
| `G3` | **2** | `ce1-mots-irreguliers` (`FRA-CE1-03`), `ce1-mots-invariables` (`FRA-CE1-21`) : plafond `en cours`, confirme (complement, section 3) ; 1,0 par lot | non (`en cours` au mieux) | complement de A, section 3 |
| `G4` | **5** | `ce1-calendrier-dates`, `ce1-deplacements-quadrillage`, `ce1-figures-planes`, `ce1-masses-contenances`, `ce1-nombres-jusqu-a-1000` (rattachement par le nom, a confirmer par le planning de s54) ; `MUL-CE1-R1` se rouvre a la `DEC` du premier sprint qui porte une instance de mathematiques CE1 | non, selon `proposition-s55.md` de s54, non remesure ici | `DEC-0115` section 3, rang 4 |
| Fichiers refuses a s51 | **2** | `cm1-sciences-terre`, `cm1-sciences-activite-humaine` : sans entree de catalogue, hors perimetre | - | `review.md` de s54, obstacle 13 |

Total : 11 + 0 + 1 (`cp-emc-personnes-differentes`) + 16 + 2 + 5 + 2 = **37**.

**Le chemin critique reste le francais CE1 : 11 lots, une instance (`PAR-2`).** L'instance B n'a plus de paquet qui ferme une ligne dans sa matiere (l'EMC CM1 est a 11 lignes `couvert-valide` sur 11 atteignables) ;
le seul candidat de fermeture hors francais est le paquet a reproductions d'`ART-CP-10`, qui n'existe pas.

## 2. Ce que s55 a mesure et qui pese sur s56

| Mesure | Valeur | Source |
|---|---|---|
| Audits de s55 au premier passage | 6 audits, **0 refus** : 5 `VALIDE SOUS RESERVE` (quatre branchements, rejeu d'`EXC-R15`), 1 `VALIDE` (rejeu d'`ART-RES`) ; s54 : 0 sur 6 ; s52 : 1 sur 6 | fiches ; `proposition-s55.md` de s54 |
| Reserves neuves par audit | 6 reserves ouvertes sur les 5 audits qui en portent, **toutes non bloquantes** | fiches |
| Marge de rejeu de A et de B | **non utilisee** (aucun refus) ; A 3,5 consommes sur 4,0, B 3,5 selon mon addition, 4,0 selon `nadia-prof` (ecart de lecture non tranche, backlog "Cloture de s55") | `audit-nadia-a.md`, `audit-nadia-b-exc.md` |
| Cout de rejeu des reserves | **2,5** : A 1,75 (`ACV-CE1-R1` 0,25, `ACV-CE1-R2` 0,5, `CLM-CE1-R1` 1,0), B 0,75 (`EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`, 0,25 chacune) | fiches, additionnees sans recompte |
| Audit du paquet a reproductions d'`ART-CP-10` | **2,5**, "s56 au plus tot" ; l'instance qui le porte garde 0,5 et **aucun autre lot** | arbitrage A, section 3.5 ; `DEC-0115` A.4 |
| `PAR-13` sur le francais CE1 | tenue pour `-14` et `-12` (cousines de conditions servies) ; **non lue** pour `RCT`, `PRN`, `INF`, `DOC` (groupe X), `OMP`, `RLP`, `FAM` (groupe Y) | arbitrage A, section 1 ; `DEC-0114` 7.2 |
| Borne de `PAR-9` | 8,0, comptee telle qu'ecrite, pas de 9,5 ; aucune troisieme instance sans ecart ecrit | `DEC-0114` A.20 |

## 3. Reserves a lever : cout de rejeu et ordre de rejeu

Les six reserves restent ouvertes sur des lignes deja `couvert-valide`. `ACV-CE1-R1` et `EXC-R16` ont leur texte applique (`2b66a441`, `e6262ac2`) : il ne manque que le rejeu de `nadia-prof`.

| Reserve | Instance | Rejeu | Dev | Ce que lever coute en plus |
|---|---|---|---|---|
| `ACV-CE1-R1` | A | 0,25 | aucun (applique) | rien |
| `ACV-CE1-R2` | A | 0,5 (trois items, `PAR-10`) | `marc-dev` | un test de comptage a ecrire avec `yanis-qa` |
| `CLM-CE1-R1` | A | **1,0** (24 indices, `PAR-14`) | `marc-dev` | un lot de A, donc un lot de francais CE1 de moins dans le meme sprint |
| `EMC-CM1-R8`, `EMC-CM1-R9` | B | 0,25 + 0,25 | `lea-dev` | rien |
| `EXC-R16` | B | 0,25 | aucun (applique) | rien |

**Ce que je recommande, lecture de produit et non decision** : engager les cinq reserves a 0,5 ou moins (1,5 au total : A 0,75, B 0,75). `CLM-CE1-R1` est le seul vrai arbitrage : son prix est celui d'un lot, et il est
en concurrence directe avec un lot de francais CE1 qui ferme une ligne. Le defaut ne rend rien d'inexact a l'enfant (`nadia-prof` : *"Ce n'est pas un motif de refus"*). Deux issues, chiffrees section 4 : la payer a s56 (une fermeture de moins) ou la reporter, premier report
compte, avec le motif ecrit a la `DEC` d'ouverture de s56. **Je ne la reporte pas par defaut** : un report non choisi est le mode de defaillance que ce depot a deja paye (`DEC-0007`).

## 4. Composition possible de s56

Budget par instance (`PAR-9`) : 4,0 = arbitrage prealable 0,5 + marge de rejeu 0,5 + 3,0 de travail ; plafond dur 5,0 ; au plus trois lots. Les prix sont ceux de `nadia-prof` ; son arbitrage prealable de s56 les confirme.
`PAR-13` : une seule instance prend un terrain neuf ; **la forme d'`ART-CP-10` n'a aucune cousine, c'est un terrain neuf**.

| Option | A (francais CE1) | B | Somme / 8,0 | Fermetures au mieux | Ce qu'elle coute |
|---|---|---|---|---|---|
| **O1** reserves + un lot de A, sans audit d'`ART-CP-10` | 1,0 + 1,75 + 1,0 (lot `-13`, cousin de `-12`) = **3,75** | 1,0 + 0,75 = **1,75** | **5,5** | 142 / 182 (`-13`) | B presque inoccupee ; la mecanique d'`ART-CP-10` reste sans paquet et non jouee un sprint de plus |
| **O2** O1 sans `CLM-CE1-R1`, deux lots de A | 1,0 + 0,75 + 2,0 = **3,75** | 1,75 | **5,5** | 143 / 182 | `CLM-CE1-R1` : premier report compte ; le second lot doit etre cousin d'une condition servie (`PAR-13`) : `-15` (cousin de `-14`) est le candidat que je vois, a lire par `nadia-prof` |
| **O3** O1 + audit du paquet d'`ART-CP-10` sur **B** | 1,0 + 1,75 + 1,0 + 0,25 (choix des oeuvres distractrices) = **4,0** | 1,0 + 0,75 + 2,5 = **4,25** | **8,25** | 143 / 182 (`-13` et `ART-CP-10`) | **depasse `PAR-9` de 0,25 sur B et la borne de 8,0 de 0,25** ; B sous le plafond dur de 5,0 ; ou une reserve d'EMC part a s57 (premier report) et la somme tombe a 8,0 |

Lectures :
- **O3 suppose que le paquet existe avant l'audit.** Il n'a ni proprietaire, ni T4, ni choix des oeuvres : s'il n'est pas ecrit dans les premiers jours de s56, l'audit glisse et c'est un poste engage non livre (report compte). Q-U1 est a l'utilisatrice.
- **Qui audite l'arts plastiques CP** est a `nadia-prof` (`PAR-2`). A s55 toutes les arts plastiques CP etaient a A pour tenir la frontiere `ART-RES` / `ART-CP-10` ; `ART-RES` est desormais `VALIDE`, la frontiere ne commande plus. Mettre l'audit sur A (4,0 - 1,0 - 2,5 = 0,5) ne laisse aucune place aux 1,75 de reserves de A : 3,5 + 1,75 = 5,25, **au-dessus du plafond dur de 5,0**.
- **Le jugement de la forme n'a pas eu lieu** : T2 et T3 sont livrees, rien n'a ete joue sur le jar, aucun ecrit de `nadia-prof` ne lit T3. `E3` du rapport de `lea-dev` (canal de forme de `M3`, apres une erreur) est a `ines-design` et `nadia-prof`.
- **Francais CE1 : arithmetique des sprints restants** (11 lots, zero refus au-dela de la marge, hypothese que le depot n'a pas verifiee) : une instance, un lot a s56 puis trois par sprint = s56 a **s60** ; deux lots a s56 (O2) puis trois = s59 ; deux instances a partir de s57 (section 6, Q3) = **s58**.

**Ma preference de produit, que l'utilisatrice et `nadia-prof` peuvent defaire** : O1 ou O3 selon Q-U1. Si le paquet n'est pas ecrit avant s56, O1 est la seule option sans poste engage qui puisse ne pas etre livre. O2 echange une reserve non bloquante contre une ligne : defendable, a la condition que le report de `CLM-CE1-R1` soit ecrit comme un report.

## 5. Paquets restants : ce qui ne tient pas a s56

- **`G7` a `G11`** : un seul candidat, `cm1-lire-et-restituer` (`FRA-CM1-01`). Il demande d'abord une mesure de pli d'`ines-design` (hors `PAR`, non faite), puis un lot a 1,0. **A placer dans la `DEC` d'ouverture de s56** : si la mesure se fait en tete de s56, le lot est possible a s56 sur l'instance que `PAR-2` designe pour le francais (lecture de `nadia-prof`, Q2) ; sinon s57. Les 15 autres n'ont aucun lot recommande (`DEC-0009`).
- **`G3` et `G4`** : aucun lot a s56. `MUL-CE1-R1` se rouvre a la `DEC` du premier sprint qui porte une instance de mathematiques CE1 : s56 n'en porte pas dans O1, O2 ni O3.
- **`cp-emc-personnes-differentes`** : sous refus, pas un lot.
- **`S54-OBS-SCI-CONSIGNE`** : premier report (s55). A s56 le second report exige un acte ecrit dans la `DEC` d'ouverture, qui cite ce premier report ; a s57, un troisieme est interdit (`DEC-0112` section 6). Sciences CM1 n'a aucun lot dans O1 a O3 : sans instance qui l'engage et ecrive ses criteres, il faudra l'abandonner par une issue ecrite a s57 ou le placer.
- **`S55-ART-CP-10-PAQUET`** : option du backlog, voir O3.

## 6. Etat de la capacite `PAR-8` / `PAR-9` apres s55

| | Regle | Etat a la cloture de s55 |
|---|---|---|
| `PAR-8` | borne 8,0, plafond dur 5,0 par instance, au plus trois lots par instance, marge 0,5 allouee a aucun lot | 2 lots par instance a s55 ; plafond jamais approche ; marge de A non utilisee, marge de B utilisee a 0,25 (ecart de l'item 4 de `-03`) selon `audit-nadia-b-emc.md` |
| `PAR-9` | 4,0 par instance, marge comprise ; somme 8,0 | A **4,0 sur 4,0** (reserve 0 : `A10` a consomme les 0,25 de `Q-U4`) ; B **4,0 sur 4,0** selon `nadia-prof` ; **7,5 alloues a l'ouverture amendee, 8,0 ecrits a la cloture** |
| `PAR-10` | rejeu 0,5 par paquet, correction mot pour mot 0,25 | non demandee a s55 au-dela de `EXC-R15` (0,25) ; **6 rejeux dus a s56 : 2,5** (section 3) |
| `PAR-11` | marge 0,5 par lot neuf, par instance | **non tranchee**, non appliquee ; lecture amendee : 4,25 chacune a s55. Aucun refus n'a mis la question a l'epreuve |
| `PAR-13` | une instance par sprint prend un terrain neuf | tenue a s55 ; a s56 la forme d'`ART-CP-10` est le terrain neuf si le paquet est audite : tout autre lot du meme sprint doit etre cousin d'une condition servie |
| `PAR-14` | reprise au-dessus de la moitie des items = tarif d'un lot | **s'applique a `CLM-CE1-R1`** (24 items sur 24) ; non a `ACV-CE1-R2` (3 items sur 24) |
| Troisieme instance | demande son propre ecart | non demandee ici |

**La capacite de B n'est pas le probleme de s56, celle de A l'est** : A porte les 11 lots du francais CE1 et 1,75 de reserves ; B n'a que 0,75 de reserves et, sans paquet d'`ART-CP-10`, rien d'autre qui ferme une ligne.

## 7. Questions

| Code | Question | A qui |
|---|---|---|
| Q1 | **Le paquet a reproductions d'`ART-CP-10` s'ecrit-il avant l'audit de s56 ?** (Q-U1 de `DEC-0115`) ; si oui, quel proprietaire, et l'utilisatrice accorde-t-elle l'ecart de 0,25 (O3) ou une reserve d'EMC va-t-elle a s57 ? | utilisatrice |
| Q2 | Instance qui audite l'arts plastiques CP a s56 ; `PAR-13` sur `-13` et `-15` (cousines) ; `PAR-2` pour le francais CM1 (`FRA-CM1-01`) : meme matiere que le francais CE1, donc A ? ; prix de son arbitrage prealable de s56 ; un rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` coute-t-il moins que 0,75 ? | `nadia-prof` |
| Q3 | **Decoupage du francais CE1 en deux instances (`DEC-0114` 7.2) a partir de s57** : sa condition (*"un sprint ou A prend X seule, puis un sprint ou A et A' tournent ensemble"*) est remplissable si A ne prend que le groupe X a s56 (`-13`) ; B est libre de matiere. Exige l'accord de `nadia-prof` sur `PAR-2` et l'avis de `theo-archi` sur `ADR-0065` (*"deux instances n'amendent pas `ADR-0065` en meme temps"*). Gain mesure plus haut : s58 au lieu de s60. | `nadia-prof`, `theo-archi`, utilisatrice |
| Q4 | L'ordre des matieres de `DEC-0115` section 3 vaut-il pour s56 ? Son rang 2 (EMC CM1) est epuise ; le rang 3 achete au plus une ligne. L'ordre est de forme 3, a elle. | utilisatrice |
| Q5 | **Q6 de `DEC-0115` (financement du dispositif seul d'`ART-CP-10`)** : ses mots, ecrits en forme 2 avec leur date, passent la `DEC` a `Acceptee`. En attente. | utilisatrice |
| Q6 | Mesure de pli de `LIS-2` pour `cm1-lire-et-restituer` : en tete de s56 ? (hors `PAR`) | `ines-design` |
| Q7 | Relecture de ponctuation de `S54-PONCT-GN-INDICES` (12 indices) et `relecture-cloture.md` : faites a la cloture ? | `relecteur-pedagogique`, `sofia-sm` |

## 8. Ce que cette proposition ne fait pas

Elle n'ecrit aucune `DEC`, aucun `ADR`, aucun tarif, aucun plafond, aucun critere de story nouvelle (ceux des reserves sont au backlog, "Cloture de s55"). Elle n'engage aucun poste, n'affecte aucun agent, n'ouvre aucun
worktree, ne prononce aucun Done et ne modifie ni code, ni catalogue, ni paquet, ni fiche. Elle n'est pas la cloture de s55 : `review.md`, `RAPPORT.md`, `retro.md`, `RAPPORT-tokens.md` sont a `sofia-sm`.
