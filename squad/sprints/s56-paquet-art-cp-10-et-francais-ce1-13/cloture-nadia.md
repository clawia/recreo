# Cloture de s56 - bilan de `nadia-prof`

`nadia-prof`, s56, le 2026-10-11. Branche `s56-nadia-art` (worktree dedie), jeu sur `a6ca1751`, puis `round-two` fusionne
a `e2c4ea32` (releve `Q7` et garde-fous `B2` a `B8` ; aucun fichier servi ne change, `git diff --stat a6ca1751 e2c4ea32`
ne touche rien sous `src/main/`). Je reprends ici les postes de l'instance B arretee et, sur instruction de l'agent qui
me lance, les rejeux et l'audit de l'instance A. **A reprendre dans `review.md` par `sofia-sm`.**

## 1. Le Done pedagogique, poste par poste

| Poste | Ligne | Verdict | Motif en une phrase | Fiche |
|---|---|---|---|---|
| `S56-AUD-ART-CP-10` | `ART-CP-10` | **REFUSE** | a 744 x 551, a la revelation, la phrase `M4` est entierement sous le pli (626 - 656) : ma condition bloquante de `Q3` n'est pas remplie, il manque 105 px | `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` |
| `S56-BR-FRA-CE1-13` | `FRA-CE1-13` | **VALIDE SOUS RESERVE** | `J1` a `J5` tenues, 24 items justes ; reserve `VSJ-CE1-R1` (la place seule donne le sujet) | `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| `S55-ACV-CE1-R1`, `S55-ACV-CE1-R2` | `FRA-CE1-14` | **LEVEES** | indice de l'item 6 a la lettre ; 8 pluriels muets sur 12 | `FICHE-bandit-manchot-accord-sujet-verbe-ce1.md` |
| `S55-CLM-CE1-R1` | `FRA-CE1-12` | **LEVEE** | 24 indices, chacun un test de classe vrai qu'aucun distracteur ne passe | `FICHE-bandit-manchot-classes-de-mots-ce1.md` |
| `S55-EMC-CM1-R8` | `EMC-CM1-03` | **LEVEE** | deux indices a la lettre, sans mot propre a la reponse | `FICHE-bandit-manchot-respect-differences-cm1.md` |
| `S55-EMC-CM1-R9` | `EMC-CM1-06` | **LEVEE** | indice vrai de la situation, citation de 2018 exacte | `FICHE-bandit-manchot-principes-republique-cm1.md` |
| `S55-EXC-R16` et A11 | `EMC-CP-03` | **LEVEE** ; en-tete complete | l'indice de l'item 4 ecarte un choix a l'ecran | `FICHE-bandit-manchot-exclusion-cp.md` |

**Le paquet d'oeuvres, les cinq points demandes** (detail dans sa fiche, section 3) : (1) la croix de 40 px se lit, ne
depend pas de l'image, ne se confond ni avec l'anneau de focus ni avec celui de revelation ; (2) refus ci-dessus, le
releve `Q7` d'`ines-design` arrive pendant l'audit rend les memes cotes que ma mesure ; (3) les trois lectures de
`rapport-lea-dev.md` 11.2 sont **confirmees** ; (4) `B6` tenu, je reste sa seule garde ; (5) l'amendement d'`ADR-0104`
n'est pas a moi, mais l'ecran de fin parle d'oeuvres qu'il ne montre pas (`RDO-CP-R2`). **Une correction de mon propre
constat** : *"Mesure : tenu avec O1 et le meuble"* (forme, bloc du 2026-10-09 21:37) etait faux ; corrige par un bloc
date dans `ARBITRAGE-s56-forme-art-cp-10.md`.

## 2. Pour `camille-po` : la matrice

- **`FRA-CE1-13` peut passer a `couvert-valide`**, sans mention de couverture partielle.
- **`ART-CP-10` reste `non couvert`.** Aucune mention de s50 a reproduire. Le refus est du backlog : le motif,
  `RDO-CP-R1` et `RDO-CP-R2`, avec leurs chiffres, sans reformulation. Le poste d'audit est livre : ce n'est pas un report.
- Les quatre autres lignes restent `couvert-valide` ; aucune reserve n'y reste ouverte.
- Compte que j'attends apres son geste : 141 + 1 = **142 / 182**. Je ne l'ai pas mesure : c'est son chiffre.

## 3. Reserves ouvertes par moi a s56

| Reserve | Jeu | Bloquante | Ce qui la leve | Prix de rejeu |
|---|---|---|---|---|
| motif de refus (sans identifiant : c'est ma condition de `Q3`) | rapprocher deux oeuvres | **oui** | `M4` au-dessus de 551 a 744 x 551, six items | 0,5 |
| `RDO-CP-R1` | rapprocher deux oeuvres | non | indice au-dessus du pli a 744 a l'echec ; croix entiere a 992 | dans le meme rejeu |
| `RDO-CP-R2` | rapprocher deux oeuvres | non | aucun texte de l'ecran de fin ne designe une oeuvre absente | dans le meme rejeu |
| `VSJ-CE1-R1` | le verbe et son sujet | non | 3 items de sujet au moins avec une proposition fausse avant le verbe ; j'amende ma propriete 8 avec | 0,5 |

## 4. Ce que je n'ai pas pu jouer

- **Aucun enfant** ; aucun ecran de tablette ; aucun doigt ni survol reel. Captures a densite 1 dans un Chrome sans tete.
- **Barre de defilement peinte** : non essayee par moi ; le releve `Q7` la mesure (decalage de 7,5 px des cases a 744).
- Sur le paquet d'oeuvres, les sept largeurs que `Q7` n'a pas mesurees, et toute hauteur autre que 551 et 768.
- Les rejeux d'EMC et d'exclusion : une partie au navigateur par jeu, le reste par les routes servies (HTML lu).
- Les garde-fous `B2` a `B8` fusionnes pendant l'audit : ni lus en detail, ni mutes.

## 5. Ce dont je reste la seule garde

Paquet d'oeuvres : `M4`, l'indice et la croix au-dessus du pli, `RDO-CP-R2`, la regle 2, la visibilite des proprietes,
`B6`, la croix contre les anneaux, l'exactitude des explications. `-13` : qu'un distracteur ne fait pas l'action,
`VSJ-CE1-R1`. `-12` : que chaque test de classe soit vrai et qu'aucun distracteur ne le passe. Exclusion CP : qu'un
indice ecarte un choix a l'ecran. **Une CI verte ne couvre aucune de ces proprietes.**

## 6. Capacite consommee (prix de mes arbitrages, additionnes)

Audit du paquet 2,5 ; `-13` 1,0 ; `CLM-CE1-R1` 1,0 ; `ACV-CE1-R1` et `-R2` 0,5 (groupe) ; `EMC-CM1-R8`, `-R9`, `EXC-R16`
0,75 ; A11 0. **Total 5,75**, reparti selon le planning entre A (2,5) et B (3,25). Aucun rejeu du paquet a s56 : la marge
de B (0,5) n'est pas consommee par moi.

## 7. Jobs de `.github/workflows/ci.yml` lances par moi

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur `a6ca1751` : BUILD SUCCESS,
  2386 tests, 0 echec, JDK 21.0.10. Relance sur l'arbre de mon commit : voir le message de livraison.
- **`chemins`**, **`prefixes`** : lances sur mon commit, voir le message de livraison. **`outils`** : non lance (je ne
  touche aucun outil).
- Hors CI : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`-pl bootstrap -am`) sur mon diff : *"sa table en
  porte 933 et 159"*, puis vert apres la ligne d'etat.

**Signe** : `nadia-prof`, s56, 2026-10-11.
