# [Sprint s40] Consommation de tokens

- **Fenetre** : 2026-09-23 07:05 a 2026-09-23 12:36 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 267 | 534 | 108 493 | 1 134 530 | 28 773 234 |
| `sofia-sm` | claude-sonnet-5 | 97 | 194 | 32 199 | 600 285 | 9 645 599 |
| `theo-archi` | claude-opus-5 | 310 | 620 | 150 591 | 1 439 564 | 48 228 764 |
| `marc-dev` | claude-sonnet-5 | 401 | 802 | 157 199 | 2 618 594 | 115 070 792 |
| `lea-dev` | claude-sonnet-5 | 439 | 878 | 171 505 | 1 869 729 | 129 037 200 |
| `yanis-qa` | claude-sonnet-5 | 110 | 220 | 23 345 | 378 452 | 11 059 510 |
| `relecteur-pedagogique` | claude-sonnet-5 | 119 | 238 | 25 662 | 441 170 | 14 751 409 |
| `nadia-prof` | claude-sonnet-5 | 305 | 610 | 126 155 | 1 229 156 | 29 659 231 |
| orchestrateur (fil principal) | claude-sonnet-5 | 145 | 290 | 98 106 | 143 799 | 48 253 825 |
| **Total** | | **2193** | **4 386** | **893 255** | **9 855 279** | **434 479 564** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
