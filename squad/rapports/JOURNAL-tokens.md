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
| `sofia-sm` | claude-sonnet-5 | 632 | 8 760 | 174 648 | 2 111 729 | 41 736 534 |
| `marc-dev` | claude-sonnet-5 | 312 | 17 028 | 100 856 | 1 054 725 | 26 343 055 |
| `lea-dev` | claude-sonnet-5 | 235 | 6 734 | 71 470 | 597 039 | 21 260 720 |
| `yanis-qa` | claude-sonnet-5 | 202 | 11 552 | 57 121 | 555 378 | 27 731 573 |
| `relecteur-pedagogique` | claude-sonnet-5 | 91 | 182 | 24 075 | 501 865 | 9 300 158 |
| `nadia-prof` | claude-opus-5 | 305 | 610 | 133 988 | 800 709 | 19 409 766 |
| `Explore` | claude-opus-5 | 197 | 18 711 | 79 088 | 1 086 501 | 15 435 868 |
| `Plan` | claude-opus-5 | 45 | 22 353 | 19 211 | 302 356 | 3 055 259 |
| orchestrateur (fil principal) | claude-opus-5 | 148 | 283 | 380 274 | 502 227 | 57 261 299 |
| **Total** | | **2475** | **126 025** | **1 160 917** | **8 840 196** | **251 496 302** |

Aucune invocation mesuree sur cette fenetre pour : `theo-archi`, `ines-design`.

## Sprint s11

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 89 | 178 | 36 006 | 218 916 | 5 351 095 |
| `sofia-sm` | claude-sonnet-5 | 451 | 44 705 | 117 879 | 1 086 360 | 33 474 120 |
| `theo-archi` | claude-opus-5 | 121 | 242 | 57 457 | 400 380 | 13 747 793 |
| `marc-dev` | claude-sonnet-5 | 208 | 2 390 | 139 027 | 1 006 635 | 52 751 379 |
| `lea-dev` | claude-sonnet-5 | 128 | 256 | 23 441 | 217 800 | 9 111 458 |
| `yanis-qa` | claude-sonnet-5 | 246 | 492 | 78 246 | 576 285 | 42 912 802 |
| `relecteur-pedagogique` | claude-sonnet-5 | 113 | 226 | 36 190 | 416 280 | 15 867 977 |
| `nadia-prof` | claude-opus-5 | 321 | 1 599 | 125 409 | 692 428 | 28 018 933 |
| `ines-design` | claude-sonnet-5 | 252 | 504 | 73 254 | 755 868 | 29 431 116 |
| orchestrateur (fil principal) | claude-opus-5 | 63 | 118 | 136 827 | 660 148 | 10 277 382 |
| **Total** | | **1992** | **50 710** | **823 736** | **6 031 100** | **240 944 055** |

## Sprint s13

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 170 | 94 476 | 72 449 | 611 452 | 14 793 990 |
| `sofia-sm` | claude-sonnet-5 | 455 | 3 516 | 142 927 | 1 276 619 | 32 125 102 |
| `theo-archi` | claude-opus-5 | 910 | 65 978 | 414 454 | 3 188 696 | 120 301 279 |
| `marc-dev` | claude-sonnet-5 | 638 | 22 014 | 246 256 | 2 174 723 | 67 933 616 |
| `lea-dev` | claude-sonnet-5 | 675 | 42 420 | 378 785 | 2 531 553 | 80 448 731 |
| `yanis-qa` | claude-sonnet-5 | 166 | 37 616 | 50 640 | 952 131 | 22 842 540 |
| `relecteur-pedagogique` | claude-sonnet-5 | 167 | 334 | 46 145 | 689 201 | 21 982 241 |
| `nadia-prof` | claude-opus-5 | 872 | 10 372 | 399 186 | 4 940 631 | 89 648 306 |
| `ines-design` | claude-sonnet-5 | 149 | 5 614 | 77 799 | 397 803 | 16 818 205 |
| orchestrateur (fil principal) | claude-opus-5 | 170 | 318 | 275 812 | 10 368 608 | 91 960 381 |
| **Total** | | **4372** | **282 658** | **2 104 453** | **27 131 417** | **558 854 391** |

## Sprint s14

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 55 | 20 402 | 33 982 | 287 416 | 4 326 148 |
| `sofia-sm` | claude-sonnet-5 | 469 | 184 186 | 172 852 | 1 826 675 | 34 720 920 |
| `theo-archi` | claude-opus-5 | 213 | 56 976 | 178 196 | 4 034 175 | 50 286 479 |
| `marc-dev` | claude-sonnet-5 | 473 | 55 522 | 269 332 | 6 441 060 | 217 080 896 |
| `lea-dev` | claude-sonnet-5 | 197 | 20 686 | 62 383 | 397 541 | 25 178 534 |
| `yanis-qa` | claude-sonnet-5 | 204 | 408 | 45 069 | 428 385 | 25 670 873 |
| `relecteur-pedagogique` | claude-sonnet-5 | 171 | 20 634 | 44 864 | 579 059 | 29 588 674 |
| `nadia-prof` | claude-opus-5 | 106 | 212 | 65 002 | 1 433 401 | 33 334 063 |
| orchestrateur (fil principal) | claude-opus-5 | 61 | 114 | 199 551 | 298 828 | 11 894 426 |
| **Total** | | **1949** | **359 140** | **1 071 231** | **15 726 540** | **432 081 013** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s15

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 97 | 194 | 41 828 | 396 569 | 10 857 010 |
| `sofia-sm` | claude-sonnet-5 | 703 | 21 346 | 218 735 | 2 298 603 | 57 736 316 |
| `theo-archi` | claude-opus-5 | 160 | 320 | 147 058 | 5 184 978 | 49 482 013 |
| `marc-dev` | claude-sonnet-5 | 668 | 1 336 | 320 783 | 6 504 724 | 303 272 286 |
| `nadia-prof` | claude-opus-5 | 162 | 324 | 85 048 | 2 961 758 | 74 257 996 |
| `ines-design` | claude-sonnet-5 | 266 | 532 | 135 028 | 779 493 | 28 806 585 |
| orchestrateur (fil principal) | claude-opus-5 | 134 | 257 | 244 439 | 1 643 536 | 48 906 340 |
| **Total** | | **2190** | **24 309** | **1 192 919** | **19 769 661** | **573 318 546** |

Aucune invocation mesuree sur cette fenetre pour : `lea-dev`, `yanis-qa`, `relecteur-pedagogique`.

## Sprint s16

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

## Sprint s17

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 522 | 1 044 | 211 258 | 7 704 354 | 145 757 294 |
| `sofia-sm` | claude-sonnet-5 | 1157 | 2 314 | 317 029 | 13 440 734 | 284 511 531 |
| `theo-archi` | claude-opus-5 | 519 | 1 038 | 371 389 | 7 626 922 | 183 920 152 |
| `marc-dev` | claude-sonnet-5 | 429 | 858 | 253 528 | 3 643 571 | 145 295 075 |
| `lea-dev` | claude-sonnet-5 | 526 | 1 052 | 255 340 | 5 608 696 | 199 578 598 |
| `yanis-qa` | claude-sonnet-5 | 154 | 308 | 58 778 | 364 534 | 16 459 933 |
| `relecteur-pedagogique` | claude-sonnet-5 | 341 | 682 | 117 931 | 1 487 529 | 86 783 176 |
| `nadia-prof` | claude-opus-5 | 616 | 1 232 | 424 140 | 12 754 964 | 232 362 514 |
| `ines-design` | claude-sonnet-5 | 108 | 216 | 104 793 | 1 000 442 | 8 648 534 |
| orchestrateur (fil principal) | claude-opus-5 | 282 | 564 | 691 067 | 2 712 526 | 71 444 581 |
| **Total** | | **4654** | **9 308** | **2 805 253** | **56 344 272** | **1 374 761 388** |

## Sprint s18

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 212 | 424 | 99 147 | 2 155 636 | 69 198 156 |
| `sofia-sm` | claude-sonnet-5 | 148 | 296 | 61 649 | 1 745 035 | 25 062 948 |
| `theo-archi` | claude-opus-5 | 298 | 596 | 161 881 | 1 751 020 | 54 664 857 |
| `marc-dev` | claude-sonnet-5 | 131 | 262 | 38 252 | 291 196 | 13 564 763 |
| `lea-dev` | claude-sonnet-5 | 238 | 476 | 99 340 | 843 495 | 37 551 154 |
| `nadia-prof` | claude-opus-5 | 442 | 884 | 243 650 | 5 584 497 | 95 443 048 |
| `ines-design` | claude-sonnet-5 | 75 | 150 | 48 642 | 835 160 | 7 862 181 |
| orchestrateur (fil principal) | claude-opus-5 | 95 | 190 | 243 034 | 1 573 991 | 66 678 088 |
| **Total** | | **1639** | **3 278** | **995 595** | **14 780 030** | **370 025 195** |

Aucune invocation mesuree sur cette fenetre pour : `yanis-qa`, `relecteur-pedagogique`.

## Sprint s19

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 138 | 276 | 55 407 | 1 296 956 | 26 322 438 |
| `sofia-sm` | claude-sonnet-5 | 74 | 148 | 25 297 | 996 372 | 16 478 442 |
| `marc-dev` | claude-sonnet-5 | 83 | 166 | 48 929 | 763 303 | 29 861 766 |
| `relecteur-pedagogique` | claude-sonnet-5 | 214 | 428 | 57 951 | 708 936 | 50 313 982 |
| `nadia-prof` | claude-opus-5 | 78 | 156 | 73 254 | 1 006 392 | 31 463 991 |
| orchestrateur (fil principal) | claude-opus-5 | 25 | 50 | 54 732 | 1 180 821 | 7 048 490 |
| **Total** | | **612** | **1 224** | **315 570** | **5 952 780** | **161 489 109** |

Aucune invocation mesuree sur cette fenetre pour : `theo-archi`, `lea-dev`, `yanis-qa`, `ines-design`.

## Sprint s20

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 338 | 676 | 160 708 | 2 177 012 | 112 401 228 |
| `sofia-sm` | claude-sonnet-5 | 471 | 942 | 166 042 | 3 179 620 | 102 884 406 |
| `theo-archi` | claude-opus-5 | 144 | 288 | 75 558 | 1 876 882 | 19 014 605 |
| `marc-dev` | claude-sonnet-5 | 329 | 658 | 146 693 | 754 583 | 72 806 699 |
| `lea-dev` | claude-sonnet-5 | 332 | 664 | 203 994 | 2 760 726 | 89 346 905 |
| `yanis-qa` | claude-sonnet-5 | 109 | 218 | 36 481 | 298 065 | 10 031 897 |
| `relecteur-pedagogique` | claude-sonnet-5 | 137 | 274 | 40 569 | 579 554 | 22 086 709 |
| `nadia-prof` | claude-opus-5 | 372 | 744 | 227 411 | 3 371 070 | 68 479 442 |
| `ines-design` | claude-sonnet-5 | 392 | 784 | 172 802 | 6 378 907 | 147 975 583 |
| orchestrateur (fil principal) | claude-opus-5 | 178 | 356 | 247 043 | 8 002 261 | 65 995 886 |
| **Total** | | **2802** | **5 604** | **1 477 301** | **29 378 680** | **711 023 360** |

## Sprint s21

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 359 | 718 | 189 917 | 1 835 576 | 62 955 290 |
| `sofia-sm` | claude-sonnet-5 | 815 | 1 630 | 247 530 | 2 095 685 | 83 525 725 |
| `theo-archi` | claude-opus-5 | 371 | 742 | 232 212 | 3 790 941 | 115 602 611 |
| `marc-dev` | claude-sonnet-5 | 305 | 610 | 99 796 | 717 291 | 51 065 617 |
| `lea-dev` | claude-sonnet-5 | 373 | 746 | 152 263 | 1 179 161 | 51 370 568 |
| `yanis-qa` | claude-sonnet-5 | 100 | 200 | 37 667 | 269 694 | 8 654 587 |
| `relecteur-pedagogique` | claude-sonnet-5 | 234 | 468 | 65 677 | 503 717 | 27 503 381 |
| `nadia-prof` | claude-opus-5 | 694 | 1 388 | 511 133 | 8 524 957 | 176 583 704 |
| `ines-design` | claude-sonnet-5 | 162 | 324 | 37 729 | 364 681 | 19 592 689 |
| orchestrateur (fil principal) | claude-opus-5 | 121 | 242 | 269 669 | 1 850 806 | 30 829 635 |
| **Total** | | **3534** | **7 068** | **1 843 593** | **21 132 509** | **627 683 807** |

## hors sprint

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 3950 | 294 703 | 1 883 963 | 21 293 275 | 494 526 211 |
| `sofia-sm` | claude-sonnet-5 | 4865 | 332 312 | 1 610 604 | 18 905 755 | 706 709 391 |
| `theo-archi` | claude-opus-5  claude-sonnet-5 | 5944 | 171 246 | 3 132 718 | 28 515 252 | 939 500 961 |
| `marc-dev` | claude-sonnet-5 | 1741 | 146 768 | 844 415 | 7 095 943 | 434 985 485 |
| `lea-dev` | claude-sonnet-5 | 1077 | 160 701 | 466 646 | 3 667 600 | 186 704 232 |
| `yanis-qa` | claude-sonnet-5 | 787 | 3 662 | 287 897 | 1 982 030 | 112 822 199 |
| `relecteur-pedagogique` | claude-sonnet-5 | 337 | 674 | 87 852 | 1 365 276 | 38 261 529 |
| `nadia-prof` | claude-opus-5 | 4028 | 65 633 | 2 174 792 | 22 096 740 | 497 063 113 |
| `ines-design` | claude-sonnet-5 | 3169 | 165 458 | 1 448 649 | 12 820 404 | 528 094 078 |
| `Explore` | claude-opus-5 | 132 | 71 529 | 47 409 | 993 601 | 6 937 649 |
| `Plan` | claude-opus-5 | 20 | 40 | 17 138 | 76 644 | 2 950 551 |
| orchestrateur (fil principal) | claude-opus-5 | 2480 | 46 933 | 4 083 922 | 30 094 635 | 935 423 387 |
| **Total** | | **28530** | **1 459 659** | **16 086 005** | **148 907 155** | **4 883 978 786** |
