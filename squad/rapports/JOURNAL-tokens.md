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
| `sofia-sm` | claude-sonnet-5 | 48 | 96 | 4 862 | 811 803 | 1 666 641 |
| `theo-archi` | claude-opus-5 | 320 | 11 833 | 155 891 | 995 872 | 38 802 010 |
| `marc-dev` | claude-sonnet-5 | 239 | 478 | 34 445 | 1 261 559 | 39 934 157 |
| `lea-dev` | claude-sonnet-5 | 173 | 1 732 | 41 234 | 907 925 | 21 121 377 |
| `yanis-qa` | claude-sonnet-5 | 154 | 122 386 | 31 599 | 706 928 | 19 065 319 |
| `relecteur-pedagogique` | claude-sonnet-5 | 155 | 2 144 | 24 887 | 709 691 | 14 436 633 |
| `nadia-prof` | claude-opus-5 | 68 | 4 022 | 33 689 | 183 292 | 2 853 354 |
| orchestrateur (fil principal) | claude-opus-5 | 93 | 178 | 108 093 | 503 396 | 14 607 798 |
| **Total** | | **1294** | **142 957** | **445 362** | **6 397 190** | **154 818 549** |

## hors sprint

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 95 | 250 | 56 882 | 538 124 | 6 028 235 |
| `theo-archi` | claude-opus-5 | 104 | 208 | 89 533 | 335 948 | 9 581 284 |
| `Explore` | claude-opus-5 | 82 | 26 708 | 18 957 | 629 781 | 3 538 360 |
| orchestrateur (fil principal) | claude-opus-5 | 168 | 330 | 278 079 | 843 882 | 18 923 626 |
| **Total** | | **449** | **27 496** | **443 451** | **2 347 735** | **38 071 505** |

Aucune invocation mesuree sur cette fenetre pour : `sofia-sm`, `marc-dev`, `lea-dev`, `yanis-qa`, `relecteur-pedagogique`, `nadia-prof`.
