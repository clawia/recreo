# Rapport de sprint s51 - trois lignes de francais CE1 et une ligne de sciences CM1 fermees sur six engagees, une correction levee, deux refus de sciences CM1

- **Duree** : 2026-10-05 20:51 -> 2026-10-06 15:39 (UTC+02:00), 18 h 48 min.
- **Objectif gele** (`DEC-0110` section 14.3, Acceptee, amendee trois fois) : deux instances de `nadia-prof`,
  A (francais CE1 : `FRA-CE1-22`, `FRA-CE1-20`, `FRA-CE1-23`, 1,0 chacune, plus la correction `HOM-CE1-R1`,
  0,5, total 3,5/5,0) et B (sciences et technologie CM1 : `SCI-CM1-10`, `SCI-CM1-15`, `SCI-CM1-18`, 1,5 chacune,
  total 4,5/5,0) ; jusqu'a six lignes a `couvert-valide`, 129/182 au mieux. Instance C (mathematiques CE1) non
  engagee, report a s52.

## L'increment livre, en une phrase

Quatre lignes du programme passent de `non couvert` a `couvert-valide` (`FRA-CE1-22`, `FRA-CE1-20`,
`FRA-CE1-23`, `SCI-CM1-10`) et la reserve `HOM-CE1-R1` est levee ; `SCI-CM1-15` et `SCI-CM1-18` sont refusees
par `nadia-prof` et restent `non couvert`. La couverture passe de **123/182 (67,6 %) a 127/182 (69,8 %)**,
deux lignes sous le meilleur cas de 129.

## Les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0110` | Ouverture de s51 a deux instances de `nadia-prof` (ouverte a trois, ramenee a deux par le refus de la borne) ; le vivier de mathematiques CE1 n'etait pas epuise | Produit | Acceptee. Amendee le 2026-10-05 (section 14 : borne a trois instances refusee, instance C non engagee), le 2026-10-06 (section 15 : verdicts de B, un seul rejeu ; section 16, cloture : etat final, budget reel, reprises reportees a s52 ; **16.9 : decision de l'utilisatrice, deux paquets refuses gardes dans le catalogue jusqu'a s52**) |
| `ADR-0065` | Un mot lu par un enfant est juge sur un lexique exterieur au depot, et la classe des homographes reste portee a la main | Architecture | Accepte, **amendement A1 du 2026-10-06** : un distracteur est admis sans etre juge s'il ne differe de la bonne reponse du meme item que par des `c` la ou elle porte `ç` (meme longueur, au moins une difference ; `AucunMotServiNeSEcarteDeSonAccentTest`, jeu fautif `cedille/`) |

Non modifiee par s51 : `ADR-0104` (cree avant le planning, statut `Propose`, attend `C5` clause 1 de
`nadia-prof` ; `git diff e4d8ba3b ca4c934a` sur son fichier est vide, selon `confirmation-archunit.md`).

Deux arbitrages de `nadia-prof` (pas des `DEC`) actent des regles opposables :
`ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` (borne a trois instances refusee, condition (ii) non remplie)
et `ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md` (A1 tient `GRL-CE1-2`). Detail : `review.md`
sections 5 et 8.

## Etat des deux Done

### Technique - quatre jobs verts en local, juge CI non encore rendu

Source : `rapport-qualite.md` de `yanis-qa`, arbre `ca4c934a`, **les quatre jobs de `.github/workflows/ci.yml`
lances** : `prefixes` vert, `chemins` vert, `outils` vert (9 juges), `build` vert (`BUILD SUCCESS`, reacteur
complet, **2076 tests**, 0 echec, 0 erreur). Reserve : le pas `package` du job `build` n'est pas rejoue.

**Ce que ce rapport ne couvre pas** : sept commits posterieurs (`ca4c934a..047b91c4`) : trois commentaires
Javadoc de tests (`db1fc6ed`), des documents (`DEC-0110`, `DEC-index.md`, matrice, backlog, rapport qualite et
confirmation ArchUnit) et un chiffre corrige dans la confirmation (`3cd6ee83`). J'ai relance les quatre jobs
sur `047b91c4` avant d'ecrire les fichiers de cloture (`review.md` section 2.2) : `build` 2076 tests, 0 echec,
`prefixes`, `chemins`, `outils` verts. Rien n'est pousse : **la CI n'a rendu aucun verdict sur ces commits.**

`theo-archi` confirme (`confirmation-archunit.md`, `ca4c934a`) : 216 tests ArchUnit verts, 39 `static final
ArchRule` et 36 `@ArchTest` (methode d'`ADR-0104`), aucune regle ajoutee, aucun `src/main/java` touche.

### Pedagogique - prononce par `nadia-prof` seule

| Ligne | Verdict final | Fiche |
|---|---|---|
| `FRA-CE1-22` | VALIDE sans reserve (REFUSE au premier passage, VALIDE au second apres rejeu) | `FICHE-bandit-manchot-graphie-ce1.md` |
| `FRA-CE1-20` | VALIDE SOUS RESERVE (`TPS-CE1-R1`) | `FICHE-bandit-manchot-temps-ce1.md` |
| `FRA-CE1-23` | VALIDE SOUS RESERVE (`ALP-CE1-R1`) | `FICHE-bandit-manchot-ordre-alphabetique-ce1.md` |
| `HOM-CE1-R1` (sur `FRA-CE1-16`) | VALIDE, reserve levee | `FICHE-bandit-manchot-homophones-ce1.md` |
| `SCI-CM1-10` | VALIDE SOUS RESERVE (`CRP-CM1-R1` a `R3`) | `FICHE-bandit-manchot-corps-cm1.md` |
| `SCI-CM1-15` | **REFUSE**, aucun rejeu finance | `FICHE-bandit-manchot-terre-cm1.md` |
| `SCI-CM1-18` | **REFUSE** deux fois, item 11 maintenu | `FICHE-bandit-manchot-activite-humaine-cm1.md` |

**Deux refus finaux.** Detail et motifs : `review.md` section 6.

## Couverture du programme

**123/182 (67,6 %) -> 127/182 (69,8 %)**, mesuree par la commande ecrite en tete de
`squad/programme/MATRICE-couverture.md` (note du 2026-10-06), que j'ai relancee : sur la matrice a `e4d8ba3b`,
`couvert-valide` 123 et `non couvert` 56 ; sur la matrice a `047b91c4`, `couvert-valide` 127 et `non couvert` 52 ;
`en cours` 3 et `non couvrable en l'etat` 76 inchanges, total 258, denominateur 182.

Budget : A 4,0 (3,5 + rejeu de `FRA-CE1-22` 0,5), B 5,0 (4,5 + rejeu de `SCI-CM1-18` 0,5) ; engage 8,0,
consomme 9,0, aucun plafond de 5,0 depasse (`DEC-0110` 16.2).

## Obstacles resolus et encore ouverts

Seize obstacles constates, tableau complet dans `review.md` section 5.

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| `FRA-CE1-22` infaisable en l'etat (distracteur sans cedille refuse par le garde-fou) | `theo-archi`, `nadia-prof` | `ADR-0065` amendement A1 ; `ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md` | 2026-10-06 (fichiers existants au disque) |
| Conflit de fusion sur `REGISTRE-identifiants.md` | `sofia-sm` | `ADR-0085` (garde-fou : 10 tests verts) | 2026-10-06 (918 + 2 + 3 = 923) |
| Ecart de +2 tests (2004 contre 2006) | `yanis-qa` | - | 2026-10-06 (`3ade55cb`, `ADR-0104`) ; total refait : 2076 |
| Deux paquets refuses restent dans le catalogue servi | utilisatrice, `camille-po` | `DEC-0110` 16.9 | 2026-10-06 : **decision de l'utilisatrice**, voir ci-dessous |

**Decision de l'utilisatrice, `DEC-0110` 16.9** (forme 3, enonce recu de MASTER, sans source dans le depot ;
il ne fonde rien d'autre que cette decision). Question : *"Retirer du catalogue servi les deux paquets refuses
par nadia-prof (activite-humaine-cm1 et terre-cm1) ?"* Reponse : *"Garder jusqu'a s52"*. Les deux paquets
(`bandit-manchot-activite-humaine-cm1`, `bandit-manchot-terre-cm1`) restent servis jusqu'a s52. **Le risque pour
l'enfant, ecrit par `nadia-prof` dans ses fiches :** il peut jouer ces paquets dans l'etat refuse et recevoir
*"Ce n'est pas ça."* sur une reponse vraie (items 4 et 11 de `terre-cm1`, item 11 de `activite-humaine-cm1`).
La `DEC` d'ouverture de s52 tranche entre retrait et reprise avant tout autre poste sur ces paquets.

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `SCI-CM1-15` refusee, reprise reportee | `nadia-prof`, `camille-po` | s51 | prix fixe par `nadia-prof` (1,0 prevu, `S51-SCI-TERRE-CM1-REPRISE`), `DEC` de s52 |
| `SCI-CM1-18` refusee deux fois | `nadia-prof`, `camille-po` | s51 | reponse de `nadia-prof` a la question de `DEC-0110` 16.4 (retirer l'item 11 leve-t-il le motif ?), `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE` |
| Reserves `TPS-CE1-R1`, `ALP-CE1-R1`, `CRP-CM1-R1` a `R3` non engagees | `camille-po` | s51 | `DEC` de s52 (premier report) |
| Instance C non engagee ; `MAT-CE1-17` a neuf sprints de report | `camille-po`, `nadia-prof` | s43 | document de derivation et verification des formes (a), (b), (c) de `nadia-prof`, absents du depot |
| Collision de scratchpad : verification de s51 non ecrite | `nadia-prof` | s50 | conclusion ecrite ; question : un sous-repertoire de scratchpad de session suffit-il ? |
| `outils/rapport-tokens.py` a efface des sections du journal de tokens a la regeneration | `theo-archi`, utilisatrice | s51 | decision (voir "Consommation de tokens") |
| Trailers "Opus 5.5" sur huit commits d'agents ; `0ce82262` en "Sonnet 5.5" | `camille-po` | s51 | regle ecrite, ou constat d'absence de regle |
| A1 commite deux fois ; rattachement de `02ba9e80` errone dans la confirmation ArchUnit | `theo-archi` | s51 | correction de son fichier |
| Ligne 89 de l'arbitrage graphie a 162 caracteres | `nadia-prof` | s51 | correction de son fichier |
| `ADR-0104` reste `Propose` ; `ART-CP-10` sans budget | `nadia-prof` | s50 | reponse sur `C5` clause 1 |
| Ecart "palier 2" de la matrice, `PAR-4` a deux instances, ecart `PAR-9`/pratique | `camille-po` | s51 | `DEC` de s52 |

## Prochain perimetre

**Non propose par cette cloture** : role de `camille-po`. Ses candidats, leur tarif et leurs conditions sont
ecrits dans `DEC-0110` section 16.10 ; l'ouverture de s52 est une `DEC` de s52.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s51`, fenetre 2026-10-05 18:51 -> 2026-10-06 13:39 UTC (debut
du planning, fin de `review.md`). Detail dans `RAPPORT-tokens.md` (ce dossier).
**Cumul dans `squad/rapports/JOURNAL-tokens.md` : non mis a jour.** La regeneration du journal remplace par
*"Aucune consommation mesuree"* les sections de s23 a s27 et la plupart de s22, qui portaient des chiffres
(`review.md`, obstacle 16) ; je ne l'ai pas commite. Les tokens que j'ai consommes apres 15:39 pour finir
ces fichiers sont hors de la fenetre.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 187 | 374 | 133 331 | 932 695 | 26 028 036 |
| `sofia-sm` | claude-sonnet-5-5 | 134 | 268 | 41 068 | 889 792 | 14 498 560 |
| `theo-archi` | claude-opus-5-5 | 131 | 262 | 44 863 | 555 120 | 7 153 321 |
| `marc-dev` | claude-sonnet-5-5 | 120 | 242 | 83 642 | 1 135 447 | 18 669 035 |
| `lea-dev` | claude-sonnet-5-5 | 121 | 246 | 83 521 | 1 452 444 | 15 514 211 |
| `yanis-qa` | claude-sonnet-5-5 | 28 | 56 | 12 557 | 88 798 | 1 056 560 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 115 | 230 | 35 544 | 396 761 | 15 148 058 |
| `nadia-prof` | claude-opus-5-5 | 388 | 780 | 208 693 | 1 289 040 | 52 285 611 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 121 | 344 | 141 267 | 1 110 041 | 24 179 971 |
| **Total** | | **1345** | **2 802** | **784 486** | **7 850 138** | **174 533 363** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design a s51. Les quatre
compteurs sont separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un classement faux.
