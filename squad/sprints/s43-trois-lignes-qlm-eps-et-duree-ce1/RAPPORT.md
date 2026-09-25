# Rapport de sprint s43 - trois lignes : questionner le monde CP, education physique et sportive
CM1, mathematiques CE1, instance unique de `nadia-prof`

- **Duree** : 2026-09-25 15:35 -> 2026-09-25 18:55:30 (UTC+02:00), 3 h 20 min 30 s.
- **Objectif gele** (`DEC-0099`, Acceptee, amendee le 2026-09-25) : fermer, avec une instance
  unique de `nadia-prof`, les trois lots du perimetre rechiffre de s43 - `QLM-CP-08`,
  `EPS-CM1-10`, `MAT-CE1-12` -, cible 4,0/5,0, marge 1,0.

## L'increment livre, en une phrase

Trois lignes du programme ferment a `couvert-valide` : objets numeriques du quotidien CP, effets
de l'activite physique et regles de securite CM1 (premier paquet d'EPS du produit sur ce niveau),
calculer une duree simple CE1 - sans aucun refus pedagogique - et la couverture du programme passe
de 98/156 (62,8 %) a **101/156 (64,7 %)**.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0099` | Proposition de perimetre pour s43 et s44 ; sections 1 a 8 : etat propose (deux lignes, 3,0/5,0) ; section 9 (amendement du 2026-09-25) : perimetre rechiffre a trois lignes pour s43 apres l'arbitrage pedagogique prealable des quatorze lignes de mathematiques CE1, cible 4,0/5,0, marge 1,0. Propose egalement un perimetre pour s44 (`MUS-CP-11`, `SCI-CM1-13`, `MAT-CE1-18`), non engage par ce sprint | **Acceptee, amendee le 2026-09-25** sur le perimetre rechiffre de la section 9 |
| `ADR-0101` | Plafond de dette de longueur d'enonce au CE1 (`LON-CE1-1`, `LON-CE1-2`), cliquet exact fixe a 4 sur la dette mesuree de `ce1-longueurs.yaml`, ecrit et fusionne **avant** le premier paquet du sprint | **Accepte** |
| `ADR-0102` | Correction de la lecture des durees : les deux ecritures que `DUR-CE1-3` autorise ("2 heures et demie" et "2 h 30 min") se lisent desormais toutes deux en minutes ; toute ecriture de duree non reconnue rougit plutot que de se taire | **Accepte** |

Les trois entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/produit/DEC-index.md` et `squad/decisions/adr/ADR-index.md`. Les deux `ADR` sont
fusionnes avant le developpement, pas ecrits par une ceremonie de cloture - `theo-archi` les cite
dans son avis de faisabilite et sa confirmation ArchUnit, cette review ne fait que les rapporter.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |
| `outils` | **vert** - 9 juge(s) pur(s) executes (sept juges bash, deux juges Python), 0 echec |

**1775 tests (1769 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur
`round-two` a `804b80eb`, deux methodes independantes convergentes - identique au chiffre de
`theo-archi` et de `yanis-qa`, aucun commit de code n'etant intervenu entre leurs mesures et la
mienne. **ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35, inchange depuis s42, recompte
independamment par moi - **zero ligne de Java de production sur les trois lots**, uniquement du
contenu YAML et des tests de paquet.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.** Detail complet, y compris l'incident d'invocation corrige en cours
de mesure (deux juges Python d'abord lances a tort avec `bash`, relances avec `python`) :
`review.md`, section 3.

### Pedagogique - instance unique de `nadia-prof`, zero refus

| Ligne | Verdict | Ce qui ferme |
|---|---|---|
| Objets numeriques du quotidien (`QLM-CP-08`) | **VALIDE SOUS RESERVE** (`NUQ-CP-R1`, `NUQ-CP-R2`, non bloquantes) | `couvert-valide` |
| Effets de l'activite physique et regles de securite (`EPS-CM1-10`) | **VALIDE SOUS RESERVE** (`EPS-CM1-R1` a `-R4`, non bloquantes) | `couvert-valide`, mention de couverture partielle exigee d'avance |
| Calculer une duree simple (`MAT-CE1-12`) | **VALIDE SOUS RESERVE** (`DUR-CE1-R1`, non bloquante) | `couvert-valide` |

**Zero refus sur trois lots**, contre un refus sur cinq lots a s42 et trois refus sur cinq lots a
s41. Le motif principal, verifie sur l'historique Git et non repris de confiance : les deux
discriminants pedagogiques qu'`EPS-CM1-10` et `MAT-CE1-12` exigeaient (`EPS-9`, `DUR-CE1-7`) ont
ete ecrits par `nadia-prof` **avant** le premier paquet, sur une branche dediee, et cites comme
criteres d'acceptation opposables dans les deux fiches de conformite - au lieu d'etre decouverts a
l'audit, apres l'ecriture. Detail complet : `review.md`, sections 2.1 et 7 ; `retro.md`, section 1.

## Le changement de process - une correction plutot qu'un relais

Ce qui m'avait ete transmis pour cette cloture attribuait a l'arbitrage pedagogique prealable des
quatorze lignes de mathematiques CE1 un fonctionnement sur "trois branches separees" comme lecon
d'un incident anterieur. **Verifie sur le disque, c'est l'inverse** : les trois instances de
`nadia-prof` qui ont ecrit cet arbitrage ont travaille directement sur le meme arbre partage, sans
branche ni worktree dedie, et c'est precisement cela qui a cause un ecrasement de donnees -
trente-deux identifiants du premier lot perdus par le commit du second, `round-two` rouge sur dix
cas d'un garde-fou de registre, repare avant l'ouverture du sprint (commit `804212e6`). **Les
trois lots de developpement du sprint lui-meme, eux, ont bien ete ecrits sur trois branches
distinctes** depuis une base commune, refusionnees sans collision. Detail complet, avec les hash
verifies : `review.md`, section 2.

## Couverture du programme

**101/156 (64,7 %)**, en hausse de 1,9 point sur s42 (98/156, 62,8 %). Trois fermetures, aucune
ouverture partielle, aucun refus a lever - verifie par moi sur
`squad/programme/MATRICE-couverture.md`, commit `2c3cbf42`.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 9). Les trois lignes de s43 ne sont pas des postes de la
composition du Plan 2 (`DEC-0086`/`DEC-0087`) : verifie par recherche des identifiants de ce sprint
dans les deux textes, aucune occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : le perimetre du sprint, rechiffre et accepte apres l'arbitrage pedagogique prealable
des quatorze lignes CE1 ; l'incident d'ecrasement de donnees sur l'arbre partage de cet arbitrage,
repare avant l'ouverture du planning ; la contradiction interne de la clause `EPS-9` clause 3,
corrigee par `nadia-prof` dans son propre verdict, en l'appliquant.

**Encore ouverts** : le referentiel EPS CM1 porte toujours un exemple que sa propre regle
`EPS-9` clause 2 rend injouable (route a `nadia-prof`) ; un distracteur de `QLM-CP-08` porte une
construction fautive en francais (route au `relecteur-pedagogique`) ; aucun garde-fou du depot ne
compare deux paquets, limite deja connue depuis s42 sur laquelle reposent les cinq clauses
d'`EPS-9` et les cinq clauses de `DUR-CE1-7` (route a `theo-archi`, fond garde par `nadia-prof`) ;
la frontiere entre `MAT-CE1-18` et `MAT-CE1-06`, candidate et hors perimetre respectivement pour
s44, reste a poser avant que l'une des deux lignes ne soit ecrite (route a `sofia-sm`, qui route a
`theo-archi`) ; le materiau visuel de `MAT-CE1-17` n'est pas chiffre (route a `theo-archi`).

**Un point de process, note en retro plutot qu'amende dans `squad/README.md`** : le commit de
reparation de l'incident d'ecrasement de donnees nomme la lecon *"lancer le garde-fou avant le
commit ne suffit pas quand l'arbre est partage"*, sans proposer de remede. Rien, a ce jour, ne dit
ce que plusieurs instances concurrentes de `nadia-prof` doivent faire a la place si un futur
arbitrage en reunit de nouveau plusieurs sur le meme lot de lignes. Detail et action proposee :
`retro.md`, sections 2 et 3.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 8.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s43`, fenetre 2026-09-25 13:35 -> 2026-09-25 16:55
UTC (horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 120 | 240 | 27 482 | 404 057 | 7 537 114 |
| `sofia-sm` | claude-sonnet-5 | 180 | 360 | 117 954 | 3 119 980 | 26 885 894 |
| `theo-archi` | claude-opus-5 | 119 | 238 | 30 602 | 618 315 | 10 589 189 |
| `marc-dev` | claude-sonnet-5 | 270 | 540 | 83 917 | 2 045 492 | 50 928 781 |
| `lea-dev` | claude-sonnet-5 | 417 | 834 | 99 087 | 702 430 | 98 919 849 |
| `yanis-qa` | claude-sonnet-5 | 162 | 324 | 39 679 | 477 611 | 16 784 152 |
| `nadia-prof` | claude-opus-5 | 209 | 418 | 120 781 | 995 487 | 24 777 840 |
| orchestrateur (fil principal) | claude-sonnet-5 | 38 | 76 | 35 313 | 61 045 | 17 491 931 |
| **Total** | | **1515** | **3 030** | **554 815** | **8 424 417** | **253 914 750** |

Aucune invocation mesuree sur cette fenetre pour `relecteur-pedagogique` et `ines-design` : aucun
travail de relecture ou de design n'etait du sur ce sprint (section 6 de `review.md`). Les quatre
compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un
classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur.
