# [Sprint s51] Consommation de tokens

- **Fenetre** : 2026-10-05 18:51 a 2026-10-06 13:39 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
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

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
