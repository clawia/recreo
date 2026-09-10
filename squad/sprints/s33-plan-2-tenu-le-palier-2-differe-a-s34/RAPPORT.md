# [Sprint 33] Rapport de sprint - plan-2-tenu-le-palier-2-differe-a-s34

- **Debut** : 2026-09-09 18:20
- **Fin** : 2026-09-10 11:45
- **Duree effective** : 17 h 25

## Increment livre

Trois instances de `nadia-prof` ont audite en parallele trois terrains disjoints (histoire-
geographie et anglais CM1, EMC CP en entier, education physique et sportive CP) sur les huit
stories que trois developpeurs ont livrees ce sprint. Trois lignes d'histoire-geographie CM1
ferment (`HG-CM1-02`, `HG-CM1-04`, `HG-CM1-08` sur sa part servie), quatre refus (`HG-CM1-12`,
`EMC-CP-07` a son quatrieme passage, `EPS-CP-04` et `EPS-CP-08` a leur second passage), portant la
couverture du programme de 66/138 (47,8 %) a 69/138 (50,0 %) - recompte independant sur les dix-
huit tableaux de `squad/programme/MATRICE-couverture.md`. Trois stories restent bloquees
(`S33-HG-04` s'est finalement debloquee et a paye une quatrieme ligne ; `S33-EMCCP07` reste
partielle ; `S33-CONS-4` et `S33-SCO-1` attendent toujours un arbitrage qui ne leur appartient pas).

## Decisions du sprint

Aucun `ADR` ni aucun `DEC` n'est cree ou modifie pendant ce sprint (planning `1740807e` a review
`c5450328`). `DEC-0085` (strategie de s33, Plan 2 tenu, palier 2 differe a s34) a ete acceptee
avant l'ouverture du planning, dans le prolongement de l'arbitrage prealable de `nadia-prof` - elle
gouverne ce sprint mais n'est pas un produit de ce sprint. Le seul mouvement de gouvernance
produit par ce sprint est un amendement de process (non un `ADR` ni une `DEC`), a
`squad/README.md`, section 4, ecrit dans la retro : quand un commit paye plusieurs stories
touchant le meme paquet de contenu, le paquet assemble est relu comme un tout avant de commiter.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (quatre jobs de `.github/workflows/ci.yml` : `build`, `chemins`, `outils`, `prefixes`) | vert, les quatre jobs relances et nommes par `sofia-sm` sur l'arbre de travail complet (commit `b45e5927` plus les huit documents de sprint), confirme independamment par `yanis-qa` et `theo-archi` | `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/rapport-qualite.md`, `confirmation-archunit.md`, `review.md` |
| Pedagogique | nadia-prof | mixte, ligne par ligne : 3 VALIDE SOUS RESERVE (`HG-CM1-02`, `HG-CM1-04`, `HG-CM1-08` sur sa part servie), 4 REFUSE (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`), 1 paiement de dette PAYEE SOUS RESERVE (`CAT-1`), 1 increment REFUSE (`S33-EXC-R5`) | `verdicts-nadia-instance-A.md`, `-B.md`, `-C.md`, sept fiches de conformite mises a jour |

## Obstacles

**Resolus** :

| Description | Agent route | Decision ou geste | Clos le |
|---|---|---|---|
| `HG-R11`, bloquante sur `HG-CM1-02` | nadia-prof (leve son propre blocage) | verdict instance A, section 2.1 | 2026-09-10 |
| `HG-R12`, bloquante sur `HG-CM1-04` | nadia-prof / theo-archi (avis faisabilite) | verdict instance A, section 3.1 | 2026-09-10 |
| `EXC-R5`, reserve sur `bandit-manchot-exclusion-cp` | nadia-prof | verdict instance B, section 6.1 (levee, mais increment refuse pour un autre motif) | 2026-09-10 |
| `EPS-7`, `SEC-R1` (a l'exception de l'exemple fautif) sur EPS CP | nadia-prof | verdict instance C, section correspondante | 2026-09-10 |
| Regression de fragilite signalee a s32 (`partageDesTachesEpingle`) | theo-archi | `d85cc7b0`, mise a l'epreuve par mutation puis restauration | 2026-09-09 |
| Avis de faisabilite manquants sur `S33-HG-04` et `S33-EXC-R1` | theo-archi | `d85cc7b0` | 2026-09-09 |
| `EPS-8`, condition neuve sur les indices d'EPS | toute l'equipe | allouee au registre dans le meme diff | 2026-09-10 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `HG-CM1-12` refusee, bloquante (`HG-R17`) | nadia-prof (audite), sofia-sm (story) | s33 | story pour s34 |
| `EMC-CP-07` refusee un quatrieme passage, perimetre de chiffrage corrige par `nadia-prof` elle-meme | nadia-prof (leve son propre blocage), sofia-sm | s33 (troisieme fois consecutive) | story sur le perimetre corrige |
| `EPS-CP-04`, `EPS-CP-08` refusees un second passage | nadia-prof (audite), sofia-sm | s33 | stories pour s34 |
| Classe de defaut nommee par l'instance C - correction executee a la lettre de la reserve, pas a son objet | sofia-sm (retro) | s33 | condition `EPS-8` deja ecrite ; le motif transverse reste a suivre sur les prochains lots |
| Defaut structurel du registre d'identifiants (clause 3, `ADR-0085`) | theo-archi | s33 (troisieme occurrence) | remede structurel avant la prochaine ecriture parallele |
| Cinq motifs d'exemption a comptes perimes sur `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | theo-archi | s33 | regle et corrections dans le meme commit, dette datee pour s34 |
| L'instruction 1 de `DEC-0061` (ce que trois essais doit vouloir dire quand l'elimination est possible) | nadia-prof | neuf sprints | reponse pedagogique |
| Trois questions de grain de `S33-CONS-4` | nadia-prof | s33 | reponses aux trois questions de `arbitrage-produit-camille.md`, section 1.6 |
| Confirmation du grain du lot de l'effort et du corps (position convergente) | nadia-prof | s33 | confirmation |
| Registre de dette : la descente projetee a 2,5 ne tient pas | camille-po | s33 | recalcul exact a l'ouverture de s34 |
| Six reserves neuves sur l'EMC CP, plus `EXC-R1` toujours impayee | camille-po (stories), sofia-sm | s33 | stories pour s34 |
| Deux stories du meme commit qui se defont l'une l'autre (`S33-EXC-R1`/`S33-EXC-R5`) | sofia-sm (amendement README) | s33 | amendement fait ; discipline a tenir sur les prochains lots |
| Hygiene de poste : sessions de jeu manuel sur le jar du worktree partage, serveur non arrete | yanis-qa (signale), sofia-sm | s33 | verification a l'ouverture de s34 |

## Couverture programme

- `HG-CM1-02`, `HG-CM1-04` : `en cours` -> `couvert-valide`.
- `HG-CM1-08` : `en cours` -> `couvert-valide`, sur sa part servie.
- `HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08` : restent `en cours` ou `non couvert`, aucun
  mouvement.
- Taux de couverture : 66/138 (47,8 %) a l'ouverture, **69/138 = 50,0 %** a la cloture, recompte
  independamment sur les dix-huit tableaux (`squad/programme/MATRICE-couverture.md`, section
  "Synthese apres les verdicts du sprint 33").

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s33`
(`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/RAPPORT-tokens.md`), fenetre 2026-09-09
16:20 a 2026-09-10 09:45 UTC. Aucun chiffre n'est recopie a la main ni invente au-dela de ce que
l'outil rend.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 53 | 106 | 36 740 | 381 374 | 5 334 551 |
| `sofia-sm` | claude-sonnet-5 | 449 | 898 | 212 183 | 1 672 817 | 63 136 609 |
| `theo-archi` | claude-opus-5 | 315 | 630 | 145 357 | 768 692 | 38 088 807 |
| `marc-dev` | claude-sonnet-5 | 512 | 1 024 | 170 024 | 1 011 982 | 83 511 710 |
| `lea-dev` | claude-sonnet-5 | 341 | 682 | 134 051 | 988 884 | 72 803 326 |
| `yanis-qa` | claude-sonnet-5 | 241 | 482 | 56 470 | 515 669 | 32 632 570 |
| `relecteur-pedagogique` | claude-sonnet-5 | 170 | 340 | 40 467 | 550 333 | 34 547 088 |
| `nadia-prof` | claude-opus-5 | 497 | 994 | 343 633 | 1 891 417 | 79 906 364 |
| orchestrateur (fil principal) | claude-opus-5 | 95 | 190 | 169 776 | 1 151 965 | 25 985 338 |
| **Total** | | **2673** | **5 346** | **1 308 701** | **8 933 133** | **435 946 363** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
