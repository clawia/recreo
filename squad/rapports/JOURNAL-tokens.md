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
| `sofia-sm` | claude-sonnet-5 | 168 | 672 | 71 875 | 766 493 | 19 426 717 |
| `theo-archi` | claude-opus-5 | 175 | 4 402 | 64 243 | 385 915 | 18 892 435 |
| `marc-dev` | claude-sonnet-5 | 149 | 700 | 58 077 | 500 086 | 17 743 907 |
| `lea-dev` | claude-sonnet-5 | 249 | 674 | 116 870 | 836 005 | 48 304 064 |
| `yanis-qa` | claude-sonnet-5 | 258 | 16 347 | 74 193 | 524 241 | 32 816 129 |
| `relecteur-pedagogique` | claude-sonnet-5 | 128 | 2 332 | 22 211 | 505 962 | 10 495 277 |
| `nadia-prof` | claude-opus-5 | 106 | 11 234 | 60 362 | 452 545 | 9 184 135 |
| `ines-design` | claude-sonnet-5 | 66 | 132 | 21 645 | 243 765 | 3 750 764 |
| orchestrateur (fil principal) | claude-opus-5 | 127 | 655 | 144 465 | 257 338 | 37 428 852 |
| **Total** | | **1494** | **37 554** | **682 803** | **5 021 879** | **201 531 543** |

## Sprint s03

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 173 | 346 | 105 605 | 1 417 072 | 16 488 817 |
| `sofia-sm` | claude-sonnet-5 | 266 | 27 492 | 73 046 | 983 433 | 17 748 662 |
| `theo-archi` | claude-opus-5 | 386 | 29 464 | 173 943 | 1 102 007 | 47 652 179 |
| `marc-dev` | claude-sonnet-5 | 152 | 18 778 | 43 247 | 272 227 | 15 316 668 |
| `lea-dev` | claude-sonnet-5 | 506 | 79 639 | 111 945 | 1 126 719 | 61 235 151 |
| `yanis-qa` | claude-sonnet-5 | 368 | 30 580 | 114 282 | 1 084 696 | 46 730 910 |
| `relecteur-pedagogique` | claude-sonnet-5 | 103 | 206 | 12 742 | 793 838 | 13 886 019 |
| `nadia-prof` | claude-opus-5 | 200 | 9 898 | 81 635 | 999 101 | 20 655 748 |
| `ines-design` | claude-sonnet-5 | 483 | 5 563 | 140 068 | 1 461 491 | 67 541 365 |
| orchestrateur (fil principal) | claude-opus-5 | 71 | 2 580 | 215 973 | 1 155 788 | 12 160 107 |
| **Total** | | **2708** | **204 546** | **1 072 486** | **10 396 372** | **319 415 626** |

## hors sprint

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 212 | 4 044 | 114 040 | 1 480 665 | 18 231 674 |
| `sofia-sm` | claude-sonnet-5 | 292 | 176 421 | 105 289 | 2 921 361 | 34 127 978 |
| `theo-archi` | claude-opus-5  claude-sonnet-5 | 725 | 31 721 | 328 758 | 3 006 357 | 93 682 759 |
| `yanis-qa` | claude-sonnet-5 | 120 | 2 328 | 39 542 | 303 092 | 7 620 504 |
| `nadia-prof` | claude-opus-5 | 181 | 4 820 | 66 942 | 624 463 | 17 604 119 |
| `Explore` | claude-opus-5 | 82 | 26 708 | 18 957 | 629 781 | 3 538 360 |
| `ines-design` | claude-sonnet-5 | 232 | 35 114 | 72 269 | 763 298 | 34 910 395 |
| orchestrateur (fil principal) | claude-opus-5 | 411 | 784 | 578 137 | 1 706 732 | 76 967 667 |
| **Total** | | **2255** | **281 940** | **1 323 934** | **11 435 749** | **286 683 456** |

Aucune invocation mesuree sur cette fenetre pour : `marc-dev`, `lea-dev`, `relecteur-pedagogique`.
