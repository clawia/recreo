# [Sprint s32] Consommation de tokens

- **Fenetre** : 2026-09-08 18:10 a 2026-09-09 10:16 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

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
