# [Sprint s25] Consommation de tokens

- **Fenetre** : 2026-08-31 16:24 a 2026-08-31 20:35 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 48 | 96 | 29 537 | 678 272 | 15 216 732 |
| `sofia-sm` | claude-opus-5 | 641 | 1 282 | 403 458 | 3 324 505 | 176 684 105 |
| `marc-dev` | claude-opus-5 | 258 | 516 | 133 960 | 574 283 | 56 614 079 |
| `lea-dev` | claude-opus-5 | 343 | 686 | 184 549 | 2 402 080 | 92 101 501 |
| `relecteur-pedagogique` | claude-opus-5 | 129 | 258 | 50 198 | 294 494 | 9 632 746 |
| `nadia-prof` | claude-opus-5 | 85 | 170 | 65 511 | 1 438 017 | 29 086 135 |
| `ines-design` | claude-opus-5 | 30 | 60 | 31 096 | 880 419 | 12 180 588 |
| orchestrateur (fil principal) | claude-opus-5 | 42 | 84 | 112 549 | 1 320 006 | 15 445 332 |
| **Total** | | **1576** | **3 152** | **1 010 858** | **10 912 076** | **406 961 218** |

Aucune invocation mesuree sur cette fenetre pour : `theo-archi`, `yanis-qa`.
