# s57 - composition du palier 2 : proposition de `camille-po`

- **Auteure** : `camille-po`, 2026-10-15 20:43 (UTC+02:00), lu par `date`. Arbre : `round-two` a `be48fd93`, branche `s57-camille-compo2`, worktree dedie, `git status --short` vide avant ecriture.
- **Ce fichier n'est pas `planning.md`** : le palier 2 n'est pas ouvert, aucun horodatage, aucun worktree de lot. L'amendement date de `planning.md` (conditions (c) et (d) de `D2`) est a `sofia-sm`. Modele : `composition-palier-1.md`.
- **Statut** : proposition. L'affectation des lots aux instances et le jugement de `PAR-13` sont a `nadia-prof` (`DEC-0120` A.33, A.36) ; **chaque developpeur ci-dessous est propose, `sofia-sm` confirme**. Aucune ligne n'est engagee tant que `planning.md` ne l'engage pas.
- **Sources** : `arbitrage-nadia-palier-2.md` (sections 5, 6, 7, 8, 12) ; `cloture-palier-1.md` (section 9) ; `planning.md` ; `duree-et-paliers.md` ; `rapport-ines-r8-forme-commune.md` ; `docs/backlog/backlog.md` (annotations de s57, dont celle du 2026-10-15 20:40) ; `DEC-0120` (A.91 a A.93) ; `squad/programme/MATRICE-couverture.md`.

## Resume

| Code | Point | Valeur |
|---|---|---|
| CO1 | Total engage, tous prix au maximum du registre | **7,75** sur 8,0 ; **0,25 non affecte** ; aucune marge touchee |
| CO2 | Par instance | A 3,0 (1 lot) ; A' 3,0 (1 lot) ; B 1,75 (0 lot) |
| CO3 | Lots | **deux** : `FRA-CE1-05` (A) et `FRA-CE1-24` (A') ; stories ecrites ce jour |
| CO4 | Hors lot, dans les 8,0 | `TPH-CE1-R1`, `GNA-CE1-R1`, `RCT-CE1-R1`, `VSJ-CE1-R2` (rejeux) ; `RDO-CP-R3`, `N1` et `N2` (B) |
| CO5 | Hors lot, hors des 8,0, prix 0 | forme commune (mise en oeuvre) ; `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-GARDE-NOMBRE-DE-MANCHES`. **`D5` n'y est plus : il est fait et fusionne** (`29d02bc6`, fusion `dbd4a612`, 2026-10-15 18:10, avant l'ouverture du palier 2), correction du 2026-10-16, section 13 |
| CO6 | Avant tout rejeu de la forme commune | 43 enonces d'`additions-cp` a mesurer ; cote de reference de `Q-R4` ecrite en entier ; accord de `nadia-prof` sur `R3-1` et sur l'ascenseur (section 6) |
| CO7 | Contradictions trouvees | sept (section 8) ; un critere amende (`CT3`) |
| CO8 | Compte des jeux `bandit-manchot` | **145** (constate) ; 143 est le compte du catalogue a 148 entrees (section 8, `CT2`) |

## 1. Les bornes, et comment cette composition les tient

| Regle | Source | Cette composition |
|---|---|---|
| 8,0 par palier | `PAR-9` ; 11.3 condition 1 | 7,75 engages + 0,25 non affecte |
| 5,0 au plus par instance | `PAR-8` | A 3,0 ; A' 3,0 ; B 1,75 |
| Trois lots au plus par instance | `PAR-8` | A 1 ; A' 1 ; B 0 |
| Marge de rejeu 0,5 par instance qui porte un lot ou un rejeu | `arbitrage-nadia-palier-1.md` section 2, lecture du 2026-10-14 | trois marges, **allouees a aucun poste** |
| Arbitrage prealable 0,5 par instance | 11.3 condition 3 ; arbitrage du palier 2, section 12 | trois fois 0,5, ce document compris |
| Aucun chiffre au-dela de 8,0 | 11.4 ; arbitrage, section 8 point 7 | chaque prix est le **maximum** du registre de `nadia-prof` (section 2) |
| Aucun prix pris sur les marges du palier 1 | arbitrage, section 8 point 7 | aucun |
| Un lot est une story existante ; une story a prix 0 n'est pas un lot | arbitrage, section 8 points 8 et 9 | deux lots, deux stories ecrites ce jour ; les quatre stories a prix 0 sont hors des lots |
| `-08` et `-09` ne sont pas des lots de s57 ; aucun lot ne change de groupe | 4.2 conditions 1 et 4 | `-05` est de X, `-24` de Y |
| Aucun lot sur terrain neuf tant qu'A' existe | `PAR-13` ; arbitrage, section 8 point 6 | aucun |

## 2. Composition proposee

**Prix.** Quand le registre de `nadia-prof` donne deux prix pour un poste, **j'inscris le plus haut** : le prix reel est celui de son rejeu, et l'ecart **reste non affecte, il ne se reaffecte pas**. Un troisieme lot demande 1,0 certain ; aucun prix reel n'est connu avant l'audit. Aucun prix n'est de moi : la colonne "Source" dit d'ou chacun vient.

| Instance | Poste | Prix | Nature | Source du prix |
|---|---|---|---|---|
| **A (groupe X)** | arbitrage prealable | 0,5 | fixe | arbitrage, section 12 |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste | 11.3 condition 1 |
| | `S57-BR-FRA-CE1-05`, `ce1-pronoms` | 1,0 | **lot** | bareme, arbitrage section 5 |
| | `S57-RCT-CE1-R1` | 0,5 | rejeu ; 0,25 si le paquet est inchange | `reponse-nadia-forme2-palier-1.md` 5.1 |
| | `S57-VSJ-CE1-R2` | 0,5 | rejeu ; 0,25 si le paquet est inchange | idem |
| | **Total A** | **3,0** | 1 lot | |
| **A' (groupe Y)** | arbitrage prealable | 0,5 | fixe | arbitrage, section 12 |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste | 11.3 condition 1 |
| | `S57-BR-FRA-CE1-24`, `ce1-familles-de-mots`, `S57-FRA-FAMILLES-LIVRE` comprise | 1,0 | **lot** | arbitrage section 5, ligne `-24` : *"1,0, correction comprise"* |
| | `S57-TPH-CE1-R1`, premier rejeu de la forme, clause `Q-R4` comprise | 0,5 | rejeu | `reponse-nadia-forme2-palier-1.md` 5.1 |
| | `S57-GNA-CE1-R1`, meme jar | 0,5 | rejeu ; 0,25 si la forme est la meme | idem |
| | **Total A'** | **3,0** | 1 lot | |
| **B** | arbitrage prealable | 0,5 | fixe | arbitrage, section 12 |
| | marge de rejeu | 0,5 | fixe, allouee a aucun poste | 11.3 condition 1 |
| | `S57-RDO-CP-R3` | 0,5 | rejeu | `PAR-10`, fiche section 6 ; arbitrage section 5 |
| | `S57-OBS-SCI-N1-N2` | 0,25 | rejeu, les deux ensemble | `reponse-nadia-q-palier-1.md` 4.1 ; arbitrage section 5 |
| | **Total B** | **1,75** | 0 lot | |
| hors instance | non affecte | 0,25 | **n'entre dans aucun poste** | difference a 8,0 |
| **Total** | | **8,00** | 2 lots | |

**Arithmetique** (`awk` sur les quatorze prix du tableau, sortie lue le 2026-10-15) : A 3,00 ; A' 3,00 ; B 1,75 ; **total 7,75 ; non affecte 0,25**. Avec les prix bas du registre (`RCT`, `VSJ`, `GNA` a 0,25) le total vaut 7,00 : **je ne reprends pas ces 0,75 pour un troisieme lot** (8,75 au maximum, au-dessus de 8,0 ; 8,00 au minimum, sans une marge). Plafond de 5,0 : aucune instance ne s'en approche. Lots : 1 + 1 + 0.

## 3. Les deux lots

| | `FRA-CE1-05` | `FRA-CE1-24` |
|---|---|---|
| Story | `S57-BR-FRA-CE1-05` | `S57-BR-FRA-CE1-24`, precedee de `S57-FRA-FAMILLES-LIVRE` |
| Paquet | `ce1-pronoms`, 20 items | `ce1-familles-de-mots`, 24 items |
| Instance, groupe | A, X | A', Y |
| Prix et source | 1,0, bareme | 1,0, correction comprise |
| Developpeur propose | `marc-dev` | `sofia-sm`, casquette developpeuse |
| Done pedagogique | `arbitrage-nadia-palier-2.md` 6.1 et 6.2, ligne `-05` | 6.1 et 6.2, ligne `-24` |

**Developpeurs : motifs, sans decision.** `planning.md` (section "Charge des developpeurs") donne les tours de s56 : `marc-dev` 183, `sofia-sm` 192, `lea-dev` 529. `lea-dev` ne recoit aucun lot de francais CE1 (regle du palier 1, reprise). `marc-dev` a branche `ce1-texte-narratif` et ecrit `PaquetCe1TexteNarratifTest`, dont `-05` reprend la forme (`LON-CE1-3` et `-4`) ; `sofia-sm` a fait le poste 5 de A' au palier 1, ce qui garde deux developpeurs distincts sur deux instances et deux jeux de fichiers partages (`catalogue.yaml`, inventaire : ordre de fusion de `sofia-sm`, risque nomme par `theo-archi`). La confirmation et la charge de `sofia-sm` (fusions, cloture de palier, review) sont les siennes.

**Prealables propres a chaque lot** (les communs sont dans les criteres `K1` a `K10` des stories) :

| Lot | Prealable | Etat mesure le 2026-10-15 |
|---|---|---|
| `-05` | `Q-P6` : l'item 15 de `ce1-pronoms` (bonne reponse *"Elias"*, seule proposition a majuscule) est-il corrige avant le branchement ? Propriete de `nadia-prof` (6.1 point 3) | 1 item rouge sur 20 ; texte de la correction a elle |
| `-05` | sa preference d'ordre (section 7) : audit **apres** la fusion de la forme commune | preference, pas condition ; sinon reserve de la famille `RCT-CE1-R1` (6.1 point 4) |
| `-24` | `S57-FRA-FAMILLES-LIVRE` livree dans le commit que `nadia-prof` joue | `grep -c 'bonneReponse: "librairie"'` rend 1, doit rendre 0 |
| `-24` | `Q-B2` : l'indice de l'item *livre* n'est pas celui qu'elle cite ; le motif de fond n'en depend pas | **repondue le 2026-10-14** (`lecture-nadia-palier-1.md` 1.7, `D8`, `7161ab69`) ; relecture des cinq items faite le 2026-10-16 (`reponse-nadia-q-palier-2.md` 3.7) ; correction du 2026-10-16, section 13 |
| `-24` | `git merge-base --is-ancestor 729b0d8a HEAD` sort 0 dans le worktree (A') | sort 0 sur `be48fd93` |
| les deux | ligne datee *"lecture complete : <paquet>, N items lus"* avant le premier verdict (`S57-ARB-PALIER-2`) | 0 ligne pour chacun |

## 4. Les postes qui ne sont pas des lots

Aucun n'est un lot : ce sont des rejeux de jeux deja servis ou des reserves (`PAR-10`). Ils sont **retenus dans les 8,0** et **non engages tant que leurs prealables ne sont pas remplis** (precedent : postes 7 et 8 de `planning.md`, palier 1 : le montant reste dans l'instance, alloue a aucun autre poste).

| Poste | Inst. | Prix | Developpeur propose | Prealables (section 6) | Levee, par `nadia-prof` |
|---|---|---|---|---|---|
| `S57-TPH-CE1-R1` | A' | 0,5 | celui de `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` | `PA1` a `PA6` | arbitrage 6.3 : 24 items, bas de `.indice` au plus 551 ; clause `Q-R4` sur la cote de reference ; la revelation n'entre pas (`Q-R2`) |
| `S57-GNA-CE1-R1` | A' | 0,5 | idem | idem, et jamais avant `TPH` | idem |
| `S57-RCT-CE1-R1` | A | 0,5 | `marc-dev` si des textes changent ; sinon celui de la forme | `PA7` (voie des 3 items a 552) ; la forme seule ne suffit pas | arbitrage 6.3 : 20 items |
| `S57-VSJ-CE1-R2` | A | 0,5 | `marc-dev` si des textes changent ; sinon rien : la forme la leve | voie des textes sans prealable ; voie de la forme : `PA1` a `PA6` | arbitrage 6.3 : 24 items |
| `S57-RDO-CP-R3` | B | 0,5 | `lea-dev` (proposition de la story : elle a fait la chaine B1 de cet ecran) | `PA8` (`Q-R7`, voie) | fiche, section 6 : 36 mesures sur 36 |
| `S57-OBS-SCI-N1-N2` | B | 0,25 | `marc-dev` (quatre lignes de YAML) | aucun hors `K1` ; orthotypographie des chevrons au `relecteur-pedagogique` | rejeu, 0,25 en tout |
| `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` | hors `PAR` | 0 | `lea-dev` (a confirmer : elle porte aussi `S57-RDO-CP-R3`, et 529 tours en s56) | `PA1` a `PA6` | aucun propre ; le jugement est celui du rejeu de `TPH` |
| hors `PAR`, prix 0 | | | | | |
| `S57-MESSAGE-PARTIE-PERDUE` | - | 0 | a designer par `sofia-sm` | mesure prealable du nombre de lignes a 744 et 1200 ; apres `S57-FORME-C-VERDICT` (livree) | regard de `nadia-prof` au premier rejeu qui suit, sans prix |
| `S57-TYP1-TEXTES-DE-GABARIT` | - | 0 | a designer par `sofia-sm` | `Q-T12` de `theo-archi` (perimetre `th:text`) | idem |
| `S57-GARDE-NOMBRE-DE-MANCHES` | - | 0 | a designer par `sofia-sm` | liste de depart de `DEC-0121` section 3 | aucun rejeu |
| `D5` (en-tete *"brouillon soumis"*, `P12` de `cloture-palier-1.md`) | - | 0 | **fait** : `29d02bc6` (`marc-dev`), fusionne par `dbd4a612` le 2026-10-15 18:10 | aucun : `grep -rln "brouillon soumis"` sur `contenu/` rend 0 fichier ; correction du 2026-10-16, section 13 | aucun rejeu |

**Ordre propose.** Pour l'audit, celui de `nadia-prof` (arbitrage, section 7), que je ne change pas : mesure de la forme commune, puis `TPH` avec `GNA`, `RCT`, `RDO-CP-R3`, `VSJ-CE1-R2`, `N1` et `N2`. Les lots sont derriere ces restes (`-05`, puis `-24`, qui ne depend de rien). **Pour le developpement**, les deux lots n'attendent ni la forme ni ses mesures : ils partent des l'ouverture, sous `K1`. La forme commune ne part qu'apres `PA3` et `PA5`. L'ordre de fusion est a `sofia-sm` ; nadia-prof prefere la forme fusionnee avant `-05`, et le dit non conditionnel.

## 5. Ce qui est ecarte, et pourquoi

| Code | Ecarte | Pourquoi | Source |
|---|---|---|---|
| EC1 | **`-06`, `-07`, `-11`** (X) | un troisieme lot de francais CE1 ne tient pas : 7,75 + 1,0 = 8,75 au maximum des prix du registre. Les restes (rang 1 a 6 de sa section 7) passent avant les lots neufs. Ordre de ses lots de X : `-05`, `-06`, `-07`, `-11`. **Aucune des trois n'a de story** (`grep -c "^### S57-BR-FRA-CE1-06"` rend 0, de meme pour `-07` et `-11`) : elles s'ecrivent quand un palier les compose | arbitrage 5, 7 et 8 point 9 |
| EC2 | **`-08` et `-09`** | a aucun palier de s57 | 4.2 condition 4 |
| EC3 | **`ART-CE1-01`, `ART-CE1-02`, `HDA-CM1-06`** et toute ligne sans paquet ni cousine | terrain neuf tant qu'A' existe ; une ligne non instruite n'est pas un lot | arbitrage 8 point 6 ; `PAR-13` |
| EC4 | **Lots de `G4`** | rang 4a apres le francais CE1, rang 1 (`DEC-0120` section 1) ; aucun prix ne reste (0,25 non affecte) | `DEC-0120` A.35 |
| EC5 | **Trois stories a prix 0** comme lots (quatre avant la correction du 2026-10-16 : `D5` est fait) | une story a prix 0 n'est pas un lot : `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-GARDE-NOMBRE-DE-MANCHES` ne prennent ni une place de lot ni une part des 8,0 | arbitrage 8 point 8 |
| EC6 | **Un rejeu de la forme commune avant `PA1` et `PA2`** | la mesure d'`ines-design` est faite ; la cote de reference de `Q-R4` ne l'est pas en entier | arbitrage 8 point 4 |
| EC7 | **`L1` ou `L2` accordes d'avance** | le candidat n'en a pas, et `nadia-prof` ne les accorde pas a tous les jeux (`Q-T5`) | arbitrage 8 point 5 |
| EC8 | **La regle du bandeau** (`candidat-r3-option-bandeau.css`) comme partie de la forme | elle nomme `.cabinet-bandeau` et touche l'habillage garde pour `L1` ; elle n'entre que par `PA7`, si `nadia-prof` l'accorde pour `RCT-CE1-R1` | rapport d'`ines-design`, section 7 |
| EC9 | **Un chiffre au-dela de 8,0**, un prix pris sur les marges | interdits | arbitrage 8 point 7 |

## 6. Ce qui doit etre mesure ou obtenu avant l'engagement

| Code | Quoi | Etat, mesure le 2026-10-15 | Qui | Ce que cela bloque |
|---|---|---|---|---|
| PA1 | **Les 43 enonces restants d'`additions-cp`** : *"les 65 enonces derives, mesures par script"* (`reponse-nadia-q8.md`) ; 52 parties et 513 manches selon son modele | `rapport-ines-r8-forme-commune.md` 5.2 et 8 : **22 enonces vus sur 65**, un seul cote. Le script reste a ecrire | `sofia-sm` affecte (`CR14`) ; meme jar que la cote de reference | tout rejeu de la forme (arbitrage 8 point 4) |
| PA2 | **Cote de reference de `Q-R4` ecrite en entier**, fichier date, qui nomme le commit du jar de base, la largeur, la hauteur et la methode, avec `git merge-base --is-ancestor` et `git diff --stat` | elements 1, 2 et 4 mesures (jar de `6dbb855d`, 744 x 551) ; **element 3 incomplet** (`PA1`). Le code de `be48fd93` est identique a celui de `6dbb855d` (`git diff --stat 6dbb855d be48fd93 -- adapters domain application bootstrap outils` : sortie vide). `Q-R5` : le jar de base est celui **d'ou part la branche de la forme** ; si la base change, la cote se remesure | `ines-design` mesure, un developpeur ecrit le script ; `nadia-prof` dit si une base de code identique suffit (`Q-P4`) | tout rejeu de la forme |
| PA3 | **Accord de `nadia-prof` sur `R3-1`** : `padding-top: 0` sur `.zone-jeu`, le score commence au bord de la bande de l'en-tete ; 32 des 102 px ; sans elle l'item le plus bas finit a 580 | `ines-design` : *"la seule que je ne sais pas defendre pour l'enfant"*. Non repondue | `nadia-prof` (`Q-P3`) | l'ouverture du worktree de la forme. Refus : la forme est a rechercher avec `L1` et `L2` (`reponse-nadia-forme2-palier-1.md` 3, point 3) |
| PA4 | **Lecture de l'ascenseur a la revelation** : 45 items sur 72 a 744 (1 sans la forme), cases decalees de 7,5 px ; 41 sur 72 a 720 (1 sans la forme), cases retrecies de 15 px | non repondue. Precedent : le decalage de 7,5 px d'`ART-CP-10` est une *"observation sans reserve"* (`rejeu-nadia-b2-palier-1.md` section 4) ; **je n'en conclus rien pour 45 items** | `nadia-prof` (`Q-P3`) | idem |
| PA5 | **Avis de `theo-archi` sur la forme** : `C1'`, `C2`, `C3` lus par `ines-design` (rapport, section 3), pas par lui ; amendement d'`ADR` eventuel ; `scrollbar-gutter` propose par `ines-design`, regle globale | non ecrit | `theo-archi` (`Q-P7`) | l'ouverture du worktree de la forme |
| PA6 | **Story de mise en oeuvre de la forme** | **ecrite ce jour** : `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` (backlog, annotation du 2026-10-15 20:40). `S57-TPH-CE1-R1` et `S57-GNA-CE1-R1` ne portaient aucun developpement | `camille-po` (fait) ; developpeur a confirmer par `sofia-sm` | `TPH`, `GNA` |
| PA7 | **Voie de `RCT-CE1-R1`** : la forme porte 19 items sur 20 dans l'ordre servi, 17 au pire ordre ; 3 items a 552 (un pixel) | `CT3` ; `Q-P5` | `nadia-prof` (regle du bandeau), developpeur et `theo-archi` (propositions plus courtes) | l'engagement de `RCT`, et son prix (0,25 ou 0,5) |
| PA8 | **Voie de `RDO-CP-R3`** (`Q-R7`) | non repondue | `ines-design`, `theo-archi` | l'engagement de `RDO-CP-R3` |
| PA9 | **Compte des jeux `bandit-manchot`** | **145** (`CT2`) | `camille-po` (fait) | rien |

**Ce que `sofia-sm` et `theo-archi` portent avant l'ouverture, hors de mes postes** : la table d'isolation du palier 2 (repertoires et ports neufs) et la condition 5 de `PAR-2`, non tenue au palier 1 (arbitrage, sections 2 et 3.4) : un moyen qui voie les ecritures du shell a la racine (`Q4` de `theo-archi`). Les mots de l'utilisatrice pour `Q-N4` et `S2` manquent toujours (arbitrage, section 9) ; aucun poste ci-dessus n'en depend, sauf le regime du pli.

## 7. Questions

| Code | Question | A qui |
|---|---|---|
| Q-P3 | Accord sur `R3-1` et lecture de l'ascenseur a la revelation (`PA3`, `PA4`) | `nadia-prof` |
| Q-P4 | Une base de code identique (`6dbb855d` et le commit d'ou partira la branche de la forme, sortie vide de `git diff --stat`) suffit-elle a garder la cote de reference, ou la cote se remesure-t-elle sur la base reelle (`Q-R5`) ? | `nadia-prof` |
| Q-P5 | Pour les 3 items de `ce1-texte-narratif` a 552 : regle du bandeau (0,25), propositions plus courtes (0,5), ou les deux ? | `nadia-prof`, `theo-archi` |
| Q-P6 | Item 15 de `ce1-pronoms` : correction avant le branchement, ou valeur consignee et jugee au jeu ? | `nadia-prof` |
| Q-P7 | Avis sur la forme (`C1'`, `C2`, `C3`), amendement d'`ADR` eventuel | `theo-archi` |
| Q-P8 | Qui ecrit le script des 43 enonces, qui mesure ; developpeurs des deux lots et de la forme ; ordre de fusion | `sofia-sm` |

## 8. Contradictions trouvees entre l'arbitrage et le backlog

| Code | Constat | Ce qui est fait ici |
|---|---|---|
| CT1 | **Lots sans story** : `grep -c "^### S57-BR-FRA-CE1-<nn>"` rend 0 pour `05`, `06`, `07`, `11` et `24` (arbitrage section 0 : meme mesure) | stories ecrites pour `-05` et `-24`, les deux lots retenus ; aucune pour `-06`, `-07`, `-11`, qui ne sont pas engages |
| CT2 | **143 ou 145 jeux** : `Q-R4` cite 143 (`nadia-prof`), `ines-design` 145. `grep -c "^  - id:"` rend 150 ; `grep -c "mecanique: bandit-manchot"` rend **145**, plus 3 `designation` et 2 `saisie`. A `d67204de` (148 entrees) le meme `grep` rend 143 : **les deux sont justes a leur date** | le bon chiffre est **145** ; annotation du backlog, aucun critere ne change |
| CT3 | **`S57-RCT-CE1-R1`, critere 3, premiere voie** : *"la forme commune leve les 9 items et le paquet est inchange"*. La mesure d'`ines-design` : 19 sur 20 (ordre servi), 17 sur 20 (pire ordre), 3 items a 552. La voie ne peut pas satisfaire le critere 1 | critere amende au backlog, de ma main ; `Q-P5` |
| CT4 | **Aucune story de mise en oeuvre de la forme** : `S57-TPH-CE1-R1` et `S57-GNA-CE1-R1` disent *"Dev : non designe"* sans que le code soit dans une story | `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` |
| CT5 | **`S57-FRA-FAMILLES-LIVRE`** : *"aucun prix"*, *"aucun palier ne la porte"* ; l'arbitrage la met dans le 1,0 de `-24` | amendement au backlog |
| CT6 | **Le rejeu de la forme est prevu au palier 2, la cote de reference n'est pas complete** : 22 enonces sur 65, base `6dbb855d` | `PA1`, `PA2` ; rejeu non engage avant |
| CT7 | **`S57-VSJ-CE1-R2` et `S57-RCT-CE1-R1` : "levee sans lot si la forme est livree avant"** : vrai pour `VSJ` (24 sur 24 sur le candidat), faux pour `RCT` | `CT3` |

## 9. Compte des reports

| Sujet | Premiere inscription | Ici | Compte |
|---|---|---|---|
| `-06`, `-07`, `-11` (X) | **palier 1**, `composition-palier-1.md` section 3 : *"restent pour un palier suivant"* (2026-10-14) | ecartes pour capacite (`EC1`) | **deuxieme ecartement** pour chacun. A ce rythme (un lot de X par palier), `-11` entre au palier 5. **Si `-06` n'entre pas au palier 3, c'est le troisieme : une `DEC` le dit, avec le motif** |
| `-24` | palier 1, place au palier 2 par `nadia-prof` (3.2) | retenu | pas un report |
| `RCT`, `VSJ-CE1-R2`, `TPH`, `GNA`, `RDO-CP-R3`, `N1` et `N2` | palier 2 (cibles ecrites au backlog) | retenus, non engages sous prealable | **premiere inscription** ; un glissement du palier 2 est le premier report |
| `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-GARDE-NOMBRE-DE-MANCHES` (`D5` fait, hors compte) | palier 2 au plus tot, non engagees | hors composition (prix 0) | premiere inscription |

**Aucune `DEC` n'est creee ni amendee par ce fichier.** L'amendement de `DEC-0120` qui porte cette composition (precedent : A.53 a A.62) s'ecrit **apres** l'amendement de `planning.md` de `sofia-sm`, qui nomme les proprietaires : une decision qui implique du code n'est pas prise sans proprietaire. Je ne l'ai pas ecrit ici parce que ces proprietaires ne sont pas encore nommes.

## 10. Matrice

Inchangee : **146 / 200** (`DEC-0120` A.91). Les deux lots peuvent fermer `FRA-CE1-05` et `FRA-CE1-24` au verdict de `nadia-prof`, pas avant ; aucune reserve ne change un statut.

## 11. Ce que ce fichier ne fait pas

- N'ouvre pas le palier 2, n'ecrit pas l'amendement de `planning.md`, n'engage aucune ligne, ne designe aucun developpeur.
- Ne prononce aucun Done, ne juge aucun contenu : les mesures de `PN1` a `PN8` et `FM1` a `FM5` sont des comptes, leur lecture est a `nadia-prof`.
- Ne decide aucun moyen technique : la forme commune est un candidat CSS d'`ines-design` ; le moyen, la feuille, l'`ADR` sont a `theo-archi`.
- Ne touche ni la matrice, ni un catalogue, ni un paquet, ni du code ; n'ecrit aucun identifiant de condition ou de reserve (`CO1` a `CO8`, `EC1` a `EC9`, `PA1` a `PA9`, `CT1` a `CT7` sont des reperes de ce fichier).

## 12. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les deux fichiers touches dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 (*"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*) ; `git ls-files` (moins le test du garde-fou), ces deux fichiers compris, passe a `verifier-chemins.sh` : sort 0 (*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*).
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 (21 cas pour le dernier) ; le sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 15 tests (5 et 10), 0 echec. **Premiere execution rouge** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent` a lu dans ce fichier un identifiant abrege de la reserve de `ce1-verbe-sujet` (non alloue) ; corrige, seconde execution verte. Lance parce que ces deux tests lisent `squad/` et `docs/` ; aucun identifiant n'est alloue. Le reste du `build` et l'etape *"Package du fat jar"* ne sont pas lances : ce commit ne touche ni code ni contenu servi. Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## 13. Corrections du 2026-10-16 (`camille-po`, palier 2, apres l'amendement de planning de 09:03)

Heure lue par `date` : 2026-10-16 09:25 (UTC+02:00). Arbre : `round-two` a `aa9d703c`, branche `s57-camille-p2-garde`. **Rien d'autre n'est reecrit** : aucun prix, aucun lot, aucune ligne de matrice ne change.

- **`D5` n'est pas un poste a venir : il est fait.** Quatre endroits de ce fichier le disaient non engage : le resume `CO5`, la ligne `D5` de la section 4, `EC5` (qui comptait *"quatre stories a prix 0"*) et la ligne de la section 9 ; ils sont corriges en place, avec renvoi a cette section. Preuves lues par moi le 2026-10-16 : `git merge-base --is-ancestor 29d02bc6 aa9d703c` sort 0 ; `git log -1 --format='%h %ad' --date=iso dbd4a612` rend *"2026-10-15 18:10:51"* (fusion de `s57-marc-d5`) ; la fusion de la cloture du palier 1 (`6dbb855d`) est du 2026-10-15 17:44:19 et l'ouverture du palier 2 du 2026-10-15 20:56 (`planning.md`) ; `grep -rl "brouillon soumis" adapters/adapter-content/src/main/resources/contenu` rend 0 fichier. **Le defaut est le mien** : la ligne reprenait *"perimetre a mesurer : 10 paquets"* de la cloture du palier 1 sans que je relance le `grep` le soir de la composition (20:43), 2 h 33 apres la fusion. Il reste **trois** stories a prix 0 non engagees : `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-GARDE-NOMBRE-DE-MANCHES`. Meme constat que `planning.md`, amendement du 2026-10-16, section 3.
- **`Q-B2` est repondue depuis le 2026-10-14.** `lecture-nadia-palier-1.md` 1.7 (`D8`, commit `7161ab69`, ancetre de `round-two` : `git merge-base --is-ancestor 7161ab69 aa9d703c` sort 0) dit que l'indice qu'elle citait n'etait pas celui de l'item et que le motif de fond tient. Je l'avais ecrite *"non repondue"* ici (section 3, ligne `-24`) et dans l'amendement du backlog `S57-FRA-FAMILLES-LIVRE` du 2026-10-15. **Le defaut est le mien** : j'ai cherche la reponse dans l'arbitrage du palier 2, qui ne cite pas cet indice, et j'ai pris l'absence dans ce fichier pour l'absence de reponse, sans chercher `Q-B2` dans les fichiers de `nadia-prof` du palier 1. `reponse-nadia-q-palier-2.md` 3.7 a refait la relecture promise (cinq items, trois indices) et corrige son propre constat. Son effet sur `-24` est pris en section 3 de l'annotation du backlog du 2026-10-16 et en `DEC-0120` A.103.
