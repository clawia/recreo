# [Sprint s31] Consommation de tokens

- **Fenetre** : 2026-09-07 12:45 a 2026-09-08 09:46 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 237 | 474 | 60 650 | 929 255 | 25 145 684 |
| `sofia-sm` | claude-sonnet-5 | 67 | 134 | 38 224 | 2 582 528 | 30 819 366 |
| `theo-archi` | claude-opus-5 | 497 | 994 | 284 051 | 2 334 420 | 74 984 833 |
| `marc-dev` | claude-sonnet-5 | 362 | 724 | 290 775 | 2 515 645 | 112 538 670 |
| `lea-dev` | claude-sonnet-5 | 622 | 1 244 | 326 437 | 3 165 526 | 210 610 493 |
| `yanis-qa` | claude-sonnet-5 | 133 | 266 | 59 832 | 926 452 | 50 959 590 |
| `relecteur-pedagogique` | claude-sonnet-5 | 328 | 656 | 95 360 | 1 180 223 | 53 798 009 |
| `nadia-prof` | claude-opus-5 | 773 | 1 546 | 474 361 | 2 159 861 | 97 021 885 |
| orchestrateur (fil principal) | claude-opus-5 | 290 | 580 | 431 005 | 3 446 159 | 58 703 103 |
| **Total** | | **3309** | **6 618** | **2 060 695** | **19 240 069** | **714 581 633** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
