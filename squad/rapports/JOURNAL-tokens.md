# Journal de consommation de tokens

Cumul de tous les sprints. Genere par `outils/rapport-tokens.py`, jamais edite
a la main.

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

## Sprint s00

Aucune consommation mesuree.

## Sprint s01

Aucune consommation mesuree.

## Sprint s03

Aucune consommation mesuree.

## Sprint s05

Aucune consommation mesuree.

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

## Sprint s22

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 453 | 906 | 209 306 | 1 925 462 | 58 626 823 |
| `sofia-sm` | claude-sonnet-5 | 1036 | 2 072 | 488 565 | 2 991 026 | 136 904 157 |
| `theo-archi` | claude-opus-5 | 274 | 548 | 154 250 | 1 284 590 | 48 580 577 |
| `marc-dev` | claude-sonnet-5 | 444 | 888 | 213 473 | 1 985 004 | 99 934 630 |
| `lea-dev` | claude-sonnet-5 | 334 | 668 | 131 736 | 855 996 | 47 954 057 |
| `yanis-qa` | claude-sonnet-5 | 143 | 286 | 50 056 | 477 484 | 19 354 221 |
| `relecteur-pedagogique` | claude-sonnet-5 | 130 | 260 | 41 060 | 588 197 | 23 868 910 |
| `nadia-prof` | claude-opus-5 | 302 | 604 | 204 279 | 1 934 619 | 37 484 685 |
| orchestrateur (fil principal) | claude-opus-5 | 199 | 398 | 396 010 | 3 447 473 | 47 975 278 |
| **Total** | | **3315** | **6 630** | **1 888 735** | **15 489 851** | **520 683 338** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s23

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 310 | 620 | 116 956 | 1 603 795 | 58 918 766 |
| `sofia-sm` | claude-sonnet-5 | 885 | 1 770 | 306 665 | 2 825 049 | 93 121 132 |
| `theo-archi` | claude-opus-5 | 110 | 220 | 70 079 | 337 395 | 12 186 852 |
| `marc-dev` | claude-sonnet-5 | 594 | 1 188 | 340 849 | 4 074 638 | 230 594 487 |
| `lea-dev` | claude-sonnet-5 | 170 | 340 | 92 381 | 568 058 | 28 133 309 |
| `yanis-qa` | claude-sonnet-5 | 188 | 376 | 70 634 | 397 458 | 22 450 386 |
| `relecteur-pedagogique` | claude-sonnet-5 | 113 | 226 | 37 631 | 460 165 | 12 474 803 |
| `nadia-prof` | claude-opus-5 | 435 | 870 | 327 431 | 1 223 203 | 42 667 507 |
| orchestrateur (fil principal) | claude-opus-5 | 80 | 160 | 222 201 | 268 320 | 40 121 289 |
| **Total** | | **2885** | **5 770** | **1 584 827** | **11 758 081** | **540 668 531** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s24

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 218 | 436 | 144 409 | 1 362 500 | 34 337 831 |
| `sofia-sm` | claude-opus-5 | 628 | 1 256 | 393 473 | 3 937 091 | 129 344 745 |
| `theo-archi` | claude-opus-5 | 168 | 336 | 124 735 | 3 009 600 | 74 666 638 |
| `marc-dev` | claude-opus-5 | 407 | 814 | 265 504 | 3 359 795 | 164 082 755 |
| `lea-dev` | claude-opus-5 | 208 | 416 | 127 579 | 897 851 | 28 317 579 |
| `yanis-qa` | claude-opus-5 | 258 | 516 | 108 472 | 2 628 497 | 34 905 165 |
| `relecteur-pedagogique` | claude-opus-5 | 162 | 324 | 61 099 | 340 447 | 16 047 925 |
| `nadia-prof` | claude-opus-5 | 389 | 778 | 239 369 | 2 355 952 | 94 368 127 |
| orchestrateur (fil principal) | claude-opus-5 | 107 | 214 | 296 545 | 1 081 912 | 19 989 214 |
| **Total** | | **2545** | **5 090** | **1 761 185** | **18 973 645** | **596 059 979** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s25

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 99 | 198 | 60 588 | 1 459 829 | 33 712 872 |
| `sofia-sm` | claude-opus-5 | 800 | 1 600 | 482 544 | 8 192 994 | 246 409 094 |
| `theo-archi` | claude-opus-5 | 78 | 156 | 37 335 | 642 922 | 23 533 310 |
| `marc-dev` | claude-opus-5 | 357 | 714 | 220 598 | 2 384 051 | 98 541 756 |
| `lea-dev` | claude-opus-5 | 425 | 850 | 242 958 | 4 458 019 | 130 776 650 |
| `yanis-qa` | claude-opus-5 | 206 | 412 | 105 242 | 639 498 | 25 760 217 |
| `relecteur-pedagogique` | claude-opus-5 | 212 | 424 | 93 741 | 714 722 | 23 925 228 |
| `nadia-prof` | claude-opus-5 | 192 | 384 | 167 717 | 3 509 471 | 77 461 742 |
| `ines-design` | claude-opus-5 | 30 | 60 | 31 096 | 880 419 | 12 180 588 |
| orchestrateur (fil principal) | claude-opus-5 | 118 | 236 | 274 741 | 3 005 534 | 50 741 590 |
| **Total** | | **2517** | **5 034** | **1 716 560** | **25 887 459** | **723 043 047** |

## Sprint s26

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 240 | 480 | 139 925 | 1 080 421 | 41 018 423 |
| `sofia-sm` | claude-sonnet-5 | 484 | 968 | 168 346 | 5 227 475 | 145 104 027 |
| `theo-archi` | claude-opus-5 | 244 | 488 | 170 537 | 5 058 088 | 98 030 692 |
| `marc-dev` | claude-sonnet-5 | 739 | 1 478 | 322 504 | 4 752 822 | 314 056 560 |
| `lea-dev` | claude-sonnet-5 | 699 | 1 398 | 338 947 | 3 332 625 | 147 113 458 |
| `yanis-qa` | claude-sonnet-5 | 89 | 178 | 50 764 | 351 936 | 9 001 826 |
| `relecteur-pedagogique` | claude-sonnet-5 | 172 | 344 | 58 139 | 440 128 | 19 734 234 |
| `nadia-prof` | claude-opus-5 | 155 | 310 | 151 847 | 1 446 832 | 33 646 956 |
| orchestrateur (fil principal) | claude-opus-5 | 109 | 218 | 258 233 | 797 741 | 18 184 644 |
| **Total** | | **2931** | **5 862** | **1 659 242** | **22 488 068** | **825 890 820** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s27

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 283 | 566 | 105 150 | 2 620 940 | 117 256 406 |
| `sofia-sm` | claude-sonnet-5 | 416 | 832 | 130 814 | 4 082 471 | 65 426 393 |
| `theo-archi` | claude-opus-5 | 244 | 488 | 178 619 | 2 494 439 | 58 064 946 |
| `marc-dev` | claude-sonnet-5 | 212 | 424 | 111 864 | 1 130 736 | 64 071 066 |
| `lea-dev` | claude-sonnet-5 | 215 | 430 | 83 439 | 1 115 783 | 58 974 296 |
| `yanis-qa` | claude-sonnet-5 | 161 | 322 | 57 832 | 397 375 | 20 773 477 |
| `relecteur-pedagogique` | claude-sonnet-5 | 251 | 502 | 71 249 | 489 717 | 39 132 330 |
| `nadia-prof` | claude-opus-5 | 182 | 364 | 106 526 | 1 796 400 | 49 257 518 |
| `ines-design` | claude-sonnet-5 | 141 | 282 | 38 089 | 847 953 | 39 013 539 |
| orchestrateur (fil principal) | claude-opus-5 | 104 | 208 | 249 651 | 1 760 899 | 41 937 023 |
| **Total** | | **2209** | **4 418** | **1 133 233** | **16 736 713** | **553 906 994** |

## Sprint s28

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 157 | 314 | 66 377 | 517 757 | 26 119 784 |
| `sofia-sm` | claude-sonnet-5 | 502 | 1 004 | 281 115 | 1 826 601 | 104 682 941 |
| `theo-archi` | claude-opus-5 | 207 | 414 | 137 754 | 739 246 | 24 104 662 |
| `marc-dev` | claude-sonnet-5 | 497 | 994 | 297 543 | 1 728 917 | 101 571 657 |
| `lea-dev` | claude-sonnet-5 | 592 | 1 184 | 245 020 | 2 364 201 | 91 585 314 |
| `yanis-qa` | claude-sonnet-5 | 121 | 242 | 38 044 | 386 810 | 13 391 841 |
| `relecteur-pedagogique` | claude-sonnet-5 | 156 | 312 | 47 533 | 734 689 | 27 498 840 |
| `nadia-prof` | claude-opus-5 | 233 | 466 | 141 135 | 637 602 | 26 134 011 |
| orchestrateur (fil principal) | claude-opus-5 | 158 | 316 | 217 346 | 2 284 345 | 40 939 763 |
| **Total** | | **2623** | **5 246** | **1 471 867** | **11 220 168** | **456 028 813** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s29

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 265 | 530 | 130 798 | 913 946 | 38 733 600 |
| `sofia-sm` | claude-sonnet-5 | 102 | 204 | 67 414 | 683 962 | 12 099 104 |
| `theo-archi` | claude-opus-5 | 181 | 362 | 122 848 | 515 452 | 27 240 633 |
| `marc-dev` | claude-sonnet-5 | 658 | 1 316 | 347 954 | 2 209 004 | 94 644 326 |
| `lea-dev` | claude-sonnet-5 | 985 | 1 970 | 389 922 | 3 215 924 | 161 953 735 |
| `yanis-qa` | claude-sonnet-5 | 179 | 358 | 58 700 | 703 132 | 24 808 389 |
| `relecteur-pedagogique` | claude-sonnet-5 | 276 | 552 | 98 331 | 944 361 | 62 184 738 |
| `nadia-prof` | claude-opus-5 | 728 | 1 456 | 456 173 | 2 860 912 | 90 660 550 |
| `ines-design` | claude-sonnet-5 | 82 | 164 | 39 171 | 383 126 | 9 247 985 |
| orchestrateur (fil principal) | claude-opus-5 | 230 | 460 | 326 264 | 2 655 168 | 92 249 560 |
| **Total** | | **3686** | **7 372** | **2 037 575** | **15 084 987** | **613 822 620** |

## Sprint s30

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 238 | 476 | 109 382 | 981 674 | 35 676 912 |
| `sofia-sm` | claude-sonnet-5 | 136 | 272 | 57 860 | 1 713 777 | 26 743 884 |
| `theo-archi` | claude-opus-5 | 116 | 232 | 40 047 | 228 327 | 7 029 513 |
| `marc-dev` | claude-sonnet-5 | 221 | 442 | 141 179 | 772 396 | 57 447 143 |
| `lea-dev` | claude-sonnet-5 | 695 | 1 390 | 296 688 | 2 168 007 | 181 189 799 |
| `yanis-qa` | claude-sonnet-5 | 209 | 418 | 63 272 | 1 151 956 | 39 562 272 |
| `relecteur-pedagogique` | claude-sonnet-5 | 163 | 326 | 53 590 | 503 511 | 25 814 307 |
| `nadia-prof` | claude-opus-5 | 383 | 766 | 313 011 | 2 092 382 | 62 955 989 |
| orchestrateur (fil principal) | claude-opus-5 | 81 | 162 | 223 992 | 1 857 286 | 19 028 855 |
| **Total** | | **2242** | **4 484** | **1 299 021** | **11 469 316** | **455 448 674** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## Sprint s31

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

## Sprint s32

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 170 | 340 | 100 709 | 675 667 | 39 952 949 |
| `sofia-sm` | claude-sonnet-5 | 587 | 1 174 | 189 423 | 1 868 887 | 64 715 259 |
| `theo-archi` | claude-opus-5 | 175 | 350 | 113 637 | 458 681 | 24 464 212 |
| `marc-dev` | claude-sonnet-5 | 249 | 498 | 146 251 | 682 614 | 60 563 251 |
| `lea-dev` | claude-sonnet-5 | 420 | 840 | 203 528 | 1 247 998 | 87 007 623 |
| `yanis-qa` | claude-sonnet-5 | 161 | 322 | 42 593 | 455 976 | 11 981 069 |
| `relecteur-pedagogique` | claude-sonnet-5 | 162 | 324 | 43 927 | 540 269 | 22 561 161 |
| `nadia-prof` | claude-opus-5 | 556 | 1 112 | 424 913 | 2 417 696 | 85 180 464 |
| orchestrateur (fil principal) | claude-opus-5 | 84 | 168 | 212 565 | 1 905 578 | 23 161 536 |
| **Total** | | **2564** | **5 128** | **1 477 546** | **10 253 366** | **419 587 524** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

## hors sprint

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5  claude-sonnet-5 | 5830 | 294 843 | 3 015 252 | 30 053 578 | 821 534 948 |
| `sofia-sm` | claude-opus-5  claude-sonnet-5 | 6045 | 105 269 | 2 037 779 | 21 989 581 | 932 527 442 |
| `theo-archi` | claude-opus-5 | 7294 | 138 941 | 4 021 178 | 34 547 789 | 1 196 816 919 |
| `marc-dev` | claude-sonnet-5 | 1801 | 146 888 | 895 005 | 7 425 887 | 440 504 018 |
| `lea-dev` | claude-sonnet-5 | 1158 | 160 863 | 501 242 | 4 029 792 | 193 164 851 |
| `yanis-qa` | claude-opus-5  claude-sonnet-5 | 684 | 1 368 | 254 351 | 1 690 538 | 109 874 555 |
| `relecteur-pedagogique` | claude-sonnet-5 | 337 | 674 | 87 852 | 1 365 276 | 38 261 529 |
| `nadia-prof` | claude-opus-5 | 5480 | 64 079 | 3 454 706 | 34 008 186 | 684 026 331 |
| `ines-design` | claude-opus-5  claude-sonnet-5 | 3724 | 131 918 | 1 828 469 | 15 095 068 | 648 505 339 |
| `Explore` | claude-opus-5 | 50 | 44 821 | 28 452 | 363 820 | 3 399 289 |
| `Plan` | claude-opus-5 | 20 | 40 | 17 138 | 76 644 | 2 950 551 |
| orchestrateur (fil principal) | claude-opus-5 | 2953 | 47 925 | 4 918 355 | 35 577 590 | 1 060 374 221 |
| **Total** | | **35376** | **1 137 629** | **21 059 779** | **186 223 749** | **6 131 939 993** |
