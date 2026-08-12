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

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

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

## Sprint s05

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 51 | 25 296 | 9 517 | 555 337 | 4 148 323 |
| `sofia-sm` | claude-sonnet-5 | 168 | 336 | 27 766 | 347 281 | 7 827 352 |
| `theo-archi` | claude-opus-5 | 825 | 71 296 | 327 221 | 2 205 226 | 92 616 854 |
| `marc-dev` | claude-sonnet-5 | 226 | 4 490 | 35 116 | 1 277 823 | 26 185 786 |
| `lea-dev` | claude-sonnet-5 | 723 | 32 676 | 181 291 | 1 703 853 | 99 303 620 |
| `yanis-qa` | claude-sonnet-5 | 352 | 21 626 | 108 394 | 1 386 689 | 41 238 057 |
| `relecteur-pedagogique` | claude-sonnet-5 | 141 | 11 001 | 55 323 | 739 608 | 21 506 935 |
| `nadia-prof` | claude-opus-5 | 224 | 3 414 | 103 948 | 684 219 | 22 267 996 |
| `ines-design` | claude-sonnet-5 | 223 | 7 700 | 50 211 | 722 011 | 23 122 934 |
| orchestrateur (fil principal) | claude-opus-5 | 91 | 163 | 231 482 | 1 178 203 | 28 699 509 |
| **Total** | | **3024** | **177 998** | **1 130 269** | **10 800 250** | **366 917 366** |

## Sprint s06

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 82 | 6 166 | 36 375 | 466 557 | 10 816 882 |
| `sofia-sm` | claude-sonnet-5 | 405 | 810 | 160 551 | 4 520 394 | 130 444 232 |
| `theo-archi` | claude-opus-5 | 289 | 3 120 | 146 688 | 2 070 551 | 57 166 066 |
| `marc-dev` | claude-sonnet-5 | 102 | 204 | 24 984 | 131 410 | 22 197 496 |
| `lea-dev` | claude-sonnet-5 | 378 | 13 364 | 145 313 | 1 659 569 | 84 038 948 |
| `yanis-qa` | claude-sonnet-5 | 175 | 23 639 | 42 596 | 280 134 | 14 078 491 |
| `relecteur-pedagogique` | claude-sonnet-5 | 158 | 4 834 | 32 528 | 1 066 174 | 22 478 138 |
| `nadia-prof` | claude-opus-5 | 266 | 44 522 | 132 142 | 698 990 | 29 078 647 |
| `ines-design` | claude-sonnet-5 | 252 | 504 | 111 746 | 779 430 | 61 925 526 |
| orchestrateur (fil principal) | claude-opus-5 | 87 | 161 | 266 003 | 776 653 | 20 340 892 |
| **Total** | | **2194** | **97 324** | **1 098 926** | **12 449 862** | **452 565 318** |

## Sprint s07

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `sofia-sm` | claude-sonnet-5 | 198 | 40 008 | 48 723 | 2 548 755 | 41 815 319 |
| `theo-archi` | claude-opus-5 | 139 | 22 214 | 69 696 | 399 410 | 17 321 635 |
| `marc-dev` | claude-sonnet-5 | 229 | 38 068 | 58 731 | 747 034 | 32 814 903 |
| `lea-dev` | claude-sonnet-5 | 270 | 540 | 96 607 | 835 209 | 72 167 691 |
| `yanis-qa` | claude-sonnet-5 | 196 | 53 731 | 62 249 | 681 213 | 36 688 613 |
| `relecteur-pedagogique` | claude-sonnet-5 | 168 | 336 | 34 353 | 641 567 | 27 874 856 |
| `nadia-prof` | claude-opus-5 | 180 | 360 | 82 979 | 740 117 | 21 585 090 |
| orchestrateur (fil principal) | claude-opus-5 | 48 | 87 | 145 080 | 136 685 | 24 348 487 |
| **Total** | | **1428** | **155 344** | **598 418** | **6 729 990** | **274 616 594** |

Aucune invocation mesuree sur cette fenetre pour : `camille-po`, `ines-design`.

## Sprint s08

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 271 | 593 | 76 054 | 805 013 | 35 128 641 |
| `sofia-sm` | claude-sonnet-5 | 480 | 75 108 | 118 804 | 1 503 464 | 27 038 717 |
| `theo-archi` | claude-opus-5 | 243 | 486 | 121 901 | 694 920 | 24 056 726 |
| `marc-dev` | claude-sonnet-5 | 220 | 39 296 | 129 373 | 698 465 | 44 004 654 |
| `lea-dev` | claude-sonnet-5 | 288 | 19 503 | 83 425 | 936 040 | 41 593 376 |
| `yanis-qa` | claude-sonnet-5 | 225 | 26 774 | 49 318 | 430 890 | 28 734 377 |
| `relecteur-pedagogique` | claude-sonnet-5 | 154 | 308 | 54 332 | 1 066 790 | 21 883 521 |
| `nadia-prof` | claude-opus-5 | 483 | 1 101 | 230 745 | 1 653 259 | 55 636 623 |
| `ines-design` | claude-sonnet-5 | 171 | 7 750 | 62 163 | 583 516 | 30 025 205 |
| orchestrateur (fil principal) | claude-opus-5 | 132 | 251 | 272 804 | 550 949 | 19 227 280 |
| **Total** | | **2667** | **171 170** | **1 198 919** | **8 923 306** | **327 329 120** |

## Sprint s09

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 308 | 39 812 | 120 186 | 1 327 667 | 29 962 070 |
| `sofia-sm` | claude-sonnet-5 | 619 | 8 734 | 165 889 | 2 094 166 | 38 545 366 |
| `marc-dev` | claude-sonnet-5 | 312 | 17 028 | 100 856 | 1 054 725 | 26 343 055 |
| `lea-dev` | claude-sonnet-5 | 235 | 6 734 | 71 470 | 597 039 | 21 260 720 |
| `yanis-qa` | claude-sonnet-5 | 202 | 11 552 | 57 121 | 555 378 | 27 731 573 |
| `relecteur-pedagogique` | claude-sonnet-5 | 91 | 182 | 24 075 | 501 865 | 9 300 158 |
| `nadia-prof` | claude-opus-5 | 305 | 610 | 133 988 | 800 709 | 19 409 766 |
| orchestrateur (fil principal) | claude-opus-5 | 108 | 206 | 293 756 | 313 408 | 38 715 877 |
| **Total** | | **2180** | **84 858** | **967 341** | **7 244 957** | **211 268 585** |

Aucune invocation mesuree sur cette fenetre pour : `theo-archi`, `ines-design`.

## hors sprint

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 871 | 85 114 | 440 029 | 5 582 587 | 92 927 866 |
| `sofia-sm` | claude-sonnet-5 | 1001 | 234 217 | 369 490 | 6 430 661 | 166 505 683 |
| `theo-archi` | claude-opus-5  claude-sonnet-5 | 2148 | 68 047 | 1 048 930 | 9 374 305 | 333 538 597 |
| `marc-dev` | claude-sonnet-5 | 58 | 1 280 | 48 103 | 782 243 | 6 195 125 |
| `yanis-qa` | claude-sonnet-5 | 120 | 2 328 | 39 542 | 303 092 | 7 620 504 |
| `nadia-prof` | claude-opus-5 | 527 | 38 280 | 272 017 | 3 363 981 | 51 792 472 |
| `ines-design` | claude-sonnet-5 | 554 | 78 563 | 177 781 | 2 368 217 | 80 430 943 |
| `Explore` | claude-opus-5 | 132 | 71 529 | 47 409 | 993 601 | 6 937 649 |
| orchestrateur (fil principal) | claude-opus-5 | 892 | 32 169 | 1 376 322 | 3 998 945 | 247 273 224 |
| **Total** | | **6303** | **611 527** | **3 819 623** | **33 197 632** | **993 222 063** |

Aucune invocation mesuree sur cette fenetre pour : `lea-dev`, `relecteur-pedagogique`.
