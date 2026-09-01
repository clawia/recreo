# [Sprint s25] Consommation de tokens

- **Fenetre** : 2026-08-31 16:24 a 2026-09-01 05:54 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 48 | 96 | 29 537 | 678 272 | 15 216 732 |
| `sofia-sm` | claude-opus-5 | 773 | 1 546 | 471 808 | 7 225 581 | 234 417 725 |
| `theo-archi` | claude-opus-5 | 78 | 156 | 37 335 | 642 922 | 23 533 310 |
| `marc-dev` | claude-opus-5 | 327 | 654 | 208 067 | 1 432 211 | 85 219 649 |
| `lea-dev` | claude-opus-5 | 425 | 850 | 242 958 | 4 458 019 | 130 776 650 |
| `relecteur-pedagogique` | claude-opus-5 | 136 | 272 | 53 899 | 300 677 | 10 674 612 |
| `nadia-prof` | claude-opus-5 | 144 | 288 | 127 059 | 2 460 897 | 54 250 291 |
| `ines-design` | claude-opus-5 | 30 | 60 | 31 096 | 880 419 | 12 180 588 |
| orchestrateur (fil principal) | claude-opus-5 | 90 | 180 | 219 108 | 2 937 032 | 36 321 189 |
| **Total** | | **2051** | **4 102** | **1 420 867** | **21 016 030** | **602 590 746** |

Aucune invocation mesuree sur cette fenetre pour : `yanis-qa`.
