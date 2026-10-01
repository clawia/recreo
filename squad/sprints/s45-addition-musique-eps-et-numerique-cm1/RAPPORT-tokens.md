# [Sprint s45] Consommation de tokens

- **Fenetre** : 2026-10-01 08:58 a 2026-10-01 12:59 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 93 | 186 | 51 477 | 442 817 | 8 046 889 |
| `sofia-sm` | claude-sonnet-5 | 566 | 1 132 | 154 246 | 1 574 676 | 88 037 309 |
| `theo-archi` | claude-opus-5-5 | 117 | 234 | 35 515 | 341 933 | 6 286 463 |
| `marc-dev` | claude-sonnet-5 | 251 | 502 | 119 981 | 2 156 613 | 57 130 565 |
| `lea-dev` | claude-sonnet-5 | 435 | 870 | 95 975 | 642 343 | 77 735 596 |
| `yanis-qa` | claude-sonnet-5 | 181 | 362 | 41 407 | 372 743 | 18 730 301 |
| `relecteur-pedagogique` | claude-sonnet-5 | 71 | 142 | 20 260 | 532 557 | 8 037 322 |
| `nadia-prof` | claude-opus-5-5 | 286 | 572 | 127 012 | 854 815 | 28 384 474 |
| orchestrateur (fil principal) | claude-sonnet-5 | 50 | 100 | 110 267 | 90 848 | 15 145 515 |
| **Total** | | **2050** | **4 100** | **756 140** | **7 009 345** | **307 534 434** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
