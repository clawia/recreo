# [Sprint s16] Consommation de tokens

- **Fenetre** : 2026-08-19 08:11 a 2026-08-19 13:20 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 75 | 150 | 25 887 | 232 783 | 5 546 401 |
| `sofia-sm` | claude-sonnet-5 | 537 | 1 074 | 247 629 | 3 501 719 | 110 084 499 |
| `theo-archi` | claude-opus-5 | 34 | 68 | 28 753 | 1 434 883 | 22 480 511 |
| `marc-dev` | claude-sonnet-5 | 133 | 266 | 54 437 | 440 595 | 16 794 285 |
| `lea-dev` | claude-sonnet-5 | 396 | 792 | 221 503 | 1 834 904 | 129 566 766 |
| `yanis-qa` | claude-sonnet-5 | 312 | 624 | 90 650 | 834 858 | 47 596 359 |
| `relecteur-pedagogique` | claude-sonnet-5 | 219 | 438 | 71 806 | 646 084 | 38 627 529 |
| `nadia-prof` | claude-opus-5 | 115 | 230 | 76 036 | 2 517 510 | 67 975 671 |
| orchestrateur (fil principal) | claude-opus-5 | 61 | 122 | 170 287 | 177 372 | 33 461 752 |
| **Total** | | **1882** | **3 764** | **986 988** | **11 620 708** | **472 133 773** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
