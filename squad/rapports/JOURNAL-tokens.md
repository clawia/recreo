# Journal de consommation de tokens

Cumul de tous les sprints. Genere par `outils/rapport-tokens.py`, jamais edite
a la main.

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

## Sprint s00

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 44 | 88 | 10 662 | 316 724 | 2 331 260 |
| `sofia-sm` | claude-sonnet-5 | 27 | 54 | 1 341 | 465 216 | 354 344 |
| `theo-archi` | claude-opus-5 | 320 | 11 833 | 155 891 | 995 872 | 38 802 010 |
| `marc-dev` | claude-sonnet-5 | 239 | 478 | 34 445 | 1 261 559 | 39 934 157 |
| `lea-dev` | claude-sonnet-5 | 173 | 1 732 | 41 234 | 907 925 | 21 121 377 |
| `yanis-qa` | claude-sonnet-5 | 154 | 122 386 | 31 599 | 706 928 | 19 065 319 |
| `relecteur-pedagogique` | claude-sonnet-5 | 155 | 2 144 | 24 887 | 709 691 | 14 436 633 |
| `nadia-prof` | claude-opus-5 | 68 | 4 022 | 33 689 | 183 292 | 2 853 354 |
| orchestrateur (fil principal) | claude-opus-5 | 93 | 178 | 108 093 | 503 396 | 14 607 798 |
| **Total** | | **1273** | **142 915** | **441 841** | **6 050 603** | **153 506 252** |

## Sprint s01

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 68 | 406 | 48 862 | 549 529 | 3 489 263 |
| `sofia-sm` | claude-sonnet-5 | 138 | 612 | 62 048 | 743 269 | 13 625 287 |
| `theo-archi` | claude-opus-5 | 175 | 4 402 | 64 243 | 385 915 | 18 892 435 |
| `marc-dev` | claude-sonnet-5 | 149 | 700 | 58 077 | 500 086 | 17 743 907 |
| `lea-dev` | claude-sonnet-5 | 249 | 674 | 116 870 | 836 005 | 48 304 064 |
| `yanis-qa` | claude-sonnet-5 | 258 | 16 347 | 74 193 | 524 241 | 32 816 129 |
| `relecteur-pedagogique` | claude-sonnet-5 | 128 | 2 332 | 22 211 | 505 962 | 10 495 277 |
| `nadia-prof` | claude-opus-5 | 106 | 11 234 | 60 362 | 452 545 | 9 184 135 |
| `ines-design` | claude-sonnet-5 | 66 | 132 | 21 645 | 243 765 | 3 750 764 |
| orchestrateur (fil principal) | claude-opus-5 | 107 | 619 | 138 601 | 244 036 | 36 000 994 |
| **Total** | | **1444** | **37 458** | **667 112** | **4 985 353** | **194 302 255** |

## hors sprint

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 95 | 250 | 56 882 | 538 124 | 6 028 235 |
| `sofia-sm` | claude-sonnet-5 | 125 | 250 | 54 735 | 1 281 616 | 8 196 725 |
| `theo-archi` | claude-opus-5  claude-sonnet-5 | 544 | 23 400 | 210 526 | 1 804 133 | 66 698 599 |
| `Explore` | claude-opus-5 | 82 | 26 708 | 18 957 | 629 781 | 3 538 360 |
| orchestrateur (fil principal) | claude-opus-5 | 336 | 641 | 456 191 | 1 551 392 | 68 953 608 |
| **Total** | | **1182** | **51 249** | **797 291** | **5 805 046** | **153 415 527** |

Aucune invocation mesuree sur cette fenetre pour : `marc-dev`, `lea-dev`, `yanis-qa`, `relecteur-pedagogique`, `nadia-prof`.
