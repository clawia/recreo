# [Sprint s47] Consommation de tokens

- **Fenetre** : 2026-10-02 14:17 a 2026-10-02 21:03 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 56 | 112 | 35 713 | 479 914 | 5 418 598 |
| `sofia-sm` | claude-sonnet-5 | 409 | 818 | 160 582 | 2 317 606 | 66 527 443 |
| `theo-archi` | claude-opus-5-5 | 113 | 230 | 44 673 | 890 424 | 6 070 902 |
| `marc-dev` | claude-sonnet-5 | 307 | 614 | 143 535 | 3 215 722 | 73 153 287 |
| `lea-dev` | claude-sonnet-5 | 534 | 1 068 | 209 691 | 4 102 180 | 98 651 349 |
| `yanis-qa` | claude-sonnet-5 | 171 | 342 | 46 645 | 699 482 | 21 430 010 |
| `relecteur-pedagogique` | claude-sonnet-5 | 43 | 86 | 8 576 | 124 634 | 1 535 894 |
| `nadia-prof` | claude-opus-5-5 | 563 | 1 130 | 246 663 | 2 208 629 | 50 979 189 |
| orchestrateur (fil principal) | claude-sonnet-5 | 262 | 524 | 157 379 | 1 279 106 | 149 449 130 |
| **Total** | | **2458** | **4 924** | **1 053 457** | **15 317 697** | **473 215 802** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
