# [Sprint s50] Consommation de tokens

- **Fenetre** : 2026-10-03 19:53 a 2026-10-04 20:39 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 481 | 962 | 222 638 | 2 409 589 | 51 356 251 |
| `sofia-sm` | claude-sonnet-5 | 3 | 6 | 1 463 | 1 442 | 369 203 |
| `theo-archi` | claude-opus-5-5 | 389 | 784 | 150 297 | 2 306 170 | 48 686 313 |
| `marc-dev` | claude-sonnet-5 | 886 | 1 772 | 396 243 | 5 680 625 | 177 024 548 |
| `lea-dev` | claude-sonnet-5 | 334 | 668 | 107 096 | 1 792 608 | 27 305 940 |
| `yanis-qa` | claude-sonnet-5 | 165 | 330 | 52 500 | 754 103 | 10 508 269 |
| `relecteur-pedagogique` | claude-sonnet-5 | 120 | 240 | 28 811 | 411 627 | 13 287 898 |
| `nadia-prof` | claude-opus-5-5 | 598 | 1 196 | 252 372 | 1 773 488 | 52 876 391 |
| orchestrateur (fil principal) | claude-sonnet-5 | 276 | 552 | 377 953 | 2 393 506 | 79 202 550 |
| **Total** | | **3252** | **6 510** | **1 589 373** | **17 523 158** | **460 617 363** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
