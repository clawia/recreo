# [Sprint 32] Rapport de sprint - trois-instances-et-ouverture-de-l-eps

- **Debut** : 2026-09-08 20:10
- **Fin** : 2026-09-09 12:15
- **Duree effective** : 16 h 05

## Increment livre

Trois instances de `nadia-prof` ont audite en parallele, pour la premiere fois, trois terrains
disjoints du programme : histoire-geographie et anglais CM1, la dette d'EMC CP, et l'ouverture de
l'education physique et sportive CP. **Trois lignes ferment** (`HG-CM1-01`, `HG-CM1-03`,
`LVE-CM1-07`), sous les deux bornes basses des fourchettes annoncees d'avance (plafond arithmetique
8 lignes, attente raisonnable 6 a 7). Quatre refus (`HG-CM1-02`, `HG-CM1-04`, `EMC-CP-07`,
`EPS-CP-04` et `EPS-CP-08`) et un paiement de dette refuse (`CAT-1`) ne deplacent aucune ligne. La
couverture du programme passe de 63/138 (45,7 %) a 66/138 (47,8 %), recomptee independamment sur
les dix-huit tableaux de `squad/programme/MATRICE-couverture.md`. Techniquement, la porte pedagogique
neuve sur la longueur des enonces de CP (`LON-1`/`LON-2`) devient executable, et une dette de garde-fou
heritee (l'exemption `DETTE_DE_MARQUEUR`) est payee dans le meme sprint que le contenu qui la
rendait necessaire.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `ADR-0088` | Faire porter par toute classe qui juge une manche l'enonce de cette manche, retirer du verdict de designation toute trace de la zone attendue, refuser qu'un garde-fou de rendu ecarte en silence un type de paquet, et faire allouer les identifiants de condition | Architecture | Accepte (instruit hors ceremonie, fusionne a l'ouverture de s32) |
| `ADR-0080` (amendement du 2026-09-08, s32) | Prealable de conception du palier 2 - designation sur image, ajustement sur `ADR-0006` | Architecture | Propose (amendement) |
| `ADR-0086` (amendement du 2026-09-08, s32) | La clause 3 de `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` n'exempte plus rien : `DETTE_DE_MARQUEUR` retiree, morsure verifiee sur le contenu vivant | Architecture | Accepte (amendement) |
| `ADR-0089` | Borner a quinze mots et deux phrases tout enonce de CP (`LON-1`, `LON-2`), et faire lire la tache d'un item d'`exclusion` dans sa bonne reponse plutot que dans une sous-chaine de l'enonce | Architecture | Accepte |
| `DEC-0083` (amendement du 2026-09-08, section 13) | Strategie de s32 a s34 : trois instances de `nadia-prof` en parallele, borne a 12,0, Plan 2 retenu, palier 2 finance a son entree minimale | Produit | Acceptee (amendement) |
| `DEC-0084` | Cloture de s32 : trois lignes ferment sur huit visees, sous l'attente raisonnable ; le declencheur de couture de `DEC-0083` n'est pas rempli, la borne de s33 reste 12,0 | Produit | Acceptee |

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (quatre jobs de `.github/workflows/ci.yml` : `build`, `chemins`, `outils`, `prefixes`) | vert | `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/rapport-qualite.md`, `confirmation-archunit.md` |
| Pedagogique | nadia-prof | mixte, ligne par ligne : 2 VALIDE (`HG-CM1-01`, `HG-CM1-03`), 1 VALIDE SOUS RESERVE (`LVE-CM1-07`), 4 REFUSE (`HG-CM1-02`, `HG-CM1-04`, `EMC-CP-07`, et deux paquets d'EPS CP `EPS-CP-04`/`EPS-CP-08`), 1 paiement de dette REFUSE (`CAT-1`) | dix fiches de conformite citees par `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`, `-B.md`, `-C.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Exemption `DETTE_DE_MARQUEUR` a retirer une fois le defaut paye | theo-archi | ADR-0086 (amendement s32) | 2026-09-08 |
| `LON-1`/`LON-2` sans regle executable | theo-archi | ADR-0089 | 2026-09-08 |
| `EXC-R5` - test qui classait la tache par sous-chaine de l'enonce | theo-archi | ADR-0089 (decision E) | 2026-09-08 |
| Couture (c) sur `REGISTRE-identifiants.md` a la fusion des trois instances | sofia-sm | DEC-0084 section 3 | 2026-09-09 |
| Compte de `ArchRule` faux d'une unite (35 au lieu de 34) dans `rapport-qualite.md` | yanis-qa | corrige dans le fichier lui-meme, commit `1dac01ef` | 2026-09-09 |
| Reference orpheline `dcbcd65a` dans `DEC-0083` | camille-po | DEC-0084 section 6.4, corrige dans `DEC-0083` | 2026-09-09 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `EXC-R1` - la consigne du catalogue nomme encore l'ancienne tache | camille-po | s32 (obstacle technique leve par theo-archi) | le texte de la consigne, story `S33-EXC-R1` |
| Plancher `FER-3` : consigne "au moins 4" contre `MINIMUM_PAR_TACHE = 3` du depot | nadia-prof | s32 | arbitrage sur la valeur source |
| Fait faux "le sacre de Clovis en 481", `HG-CM1-02` | nadia-prof | s32 (`HG-R11`) | correction du contenu, story `S33-HG-02` |
| Terme "les Sarrasins", `HG-CM1-04` | nadia-prof | s32 (`HG-R12`) | correction du contenu, story `S33-HG-04` |
| Repere de registre remplace par un repere lexical ("maitresse"), `CAT-1` | nadia-prof | s32 (quatrieme passage) | correction du contenu, story `S33-CAT1` |
| Motif neuf sur `EMC-CP-07` (regression de l'item 4, contradiction entre deux textes de nadia-prof) | nadia-prof | s32 (troisieme passage) | correction du contenu, story `S33-EMCCP07` |
| Item de "chat perche" declarant une faute le fait de toucher un joueur perche | nadia-prof | s32 (`JEU-R1`) | correction du contenu, story `S33-EPS-JEU` |
| Tache B d'`EPS-CP-04` gagnable au champ lexical du danger | nadia-prof | s32 (`SEC-R3`) | correction du contenu, story `S33-EPS-SEC` |
| Deux tests neufs lisant une tache par sous-chaine de l'enonce (`PaquetCm1HistoireGeographieTest`) | theo-archi / marc-dev | s32 | reecriture sur le patron des deux tests voisins du meme fichier |
| Dette generale : 25 fichiers de test sur 95 lisant l'enonce par operation de chaine | theo-archi | s32 (ADR-0089, declencheur ecrit) | regle generale sur un champ de tache dedie au schema d'item |
| Absence de trace d'une partie jouee de bout en bout sur `recreo.jar` pour les paquets neufs | yanis-qa (signale) | s32 | verification a faire et a ecrire |
| Incoherence de referentiel : `LVE-CM1-07` sans la parenthese que porte `LVE-CP-06` | nadia-prof | s32 (DEC-0084 section 5) | arbitrage sur l'harmonisation du referentiel |
| `CONS-1` non levee sur `bandit-manchot-histoire-geographie-cm1`, aggravee ce sprint | nadia-prof / camille-po | s32 (DEC-0084 section 4) | decision de grain, story `S33-CONS-4` |

## Couverture programme

- `HG-CM1-01` (frise des grandes periodes) : `en cours` -> `couvert-valide`, avec mention de
  couverture partielle.
- `HG-CM1-03` (traces d'occupation ancienne) : `en cours` -> `couvert-valide`.
- `LVE-CM1-07` (decouverte culturelle CM1) : `en cours` -> `couvert-valide`, VALIDE SOUS RESERVE.
- `HG-CM1-02`, `HG-CM1-04`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08` : restent `en cours` ou
  `non couvert`, aucun mouvement.
- Taux de couverture : 63/138 (45,7 %) a l'ouverture, **66/138 = 47,8 %** a la cloture, recompte
  independamment sur les dix-huit tableaux (`squad/programme/MATRICE-couverture.md`, section
  "Synthese apres les verdicts du sprint 32").

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s32` (`squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/RAPPORT-tokens.md`).
**Fenetre partielle** : l'outil situe le debut de fenetre a l'horodatage de debut du planning
(2026-09-08, en heure UTC) mais n'a aucun moyen de detecter une cloture puisque cette cloture est en
train de s'ecrire ; la fenetre rendue s'arrete donc a l'instant de generation du rapport, pas a
l'horodatage de fin ci-dessus. Aucun chiffre n'est recopie a la main ni invente au-dela de ce que
l'outil rend.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 170 | 340 | 100 709 | 675 667 | 39 952 949 |
| `sofia-sm` | claude-sonnet-5 | 587 | 1 174 | 189 423 | 1 868 887 | 64 715 259 |
| `theo-archi` | claude-opus-5 | 175 | 350 | 113 637 | 458 681 | 24 464 212 |
| `marc-dev` | claude-sonnet-5 | 249 | 498 | 146 251 | 682 614 | 60 563 251 |
| `lea-dev` | claude-sonnet-5 | 420 | 840 | 203 528 | 1 247 998 | 87 007 623 |
| `yanis-qa` | claude-sonnet-5 | 161 | 322 | 42 593 | 455 976 | 11 981 069 |
| `relecteur-pedagogique` | claude-sonnet-5 | 162 | 324 | 43 927 | 540 269 | 22 561 161 |
| `nadia-prof` | claude-opus-5 | 556 | 1 112 | 424 913 | 2 417 696 | 85 180 464 |
| orchestrateur (fil principal) | claude-opus-5 | 84 | 168 | 212 565 | 1 905 578 | 23 161 536 |
| **Total** | | **2564** | **5 128** | **1 477 546** | **10 253 366** | **419 587 524** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
