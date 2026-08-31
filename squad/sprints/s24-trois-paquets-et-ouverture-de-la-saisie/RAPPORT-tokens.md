# [Sprint s24] Consommation de tokens

- **Fenetre** : 2026-08-30 20:32 a 2026-08-31 12:10 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 138 | 276 | 116 178 | 848 708 | 15 536 749 |
| `sofia-sm` | claude-opus-5 | 619 | 1 238 | 388 491 | 3 917 232 | 126 787 347 |
| `theo-archi` | claude-opus-5 | 168 | 336 | 124 735 | 3 009 600 | 74 666 638 |
| `marc-dev` | claude-opus-5 | 407 | 814 | 265 504 | 3 359 795 | 164 082 755 |
| `lea-dev` | claude-opus-5 | 208 | 416 | 127 579 | 897 851 | 28 317 579 |
| `yanis-qa` | claude-opus-5 | 218 | 436 | 77 954 | 2 100 894 | 25 448 759 |
| `relecteur-pedagogique` | claude-opus-5 | 162 | 324 | 61 099 | 340 447 | 16 047 925 |
| `nadia-prof` | claude-opus-5 | 389 | 778 | 239 369 | 2 355 952 | 94 368 127 |
| orchestrateur (fil principal) | claude-opus-5 | 103 | 206 | 286 589 | 1 070 158 | 18 896 200 |
| **Total** | | **2412** | **4 824** | **1 687 498** | **17 900 637** | **564 152 079** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
