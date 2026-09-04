# [Sprint s28] Consommation de tokens

- **Fenetre** : 2026-09-03 20:18 a 2026-09-04 19:35 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 157 | 314 | 66 377 | 517 757 | 26 119 784 |
| `sofia-sm` | claude-sonnet-5 | 500 | 1 000 | 280 939 | 1 825 449 | 104 400 083 |
| `theo-archi` | claude-opus-5 | 207 | 414 | 137 754 | 739 246 | 24 104 662 |
| `marc-dev` | claude-sonnet-5 | 497 | 994 | 297 543 | 1 728 917 | 101 571 657 |
| `lea-dev` | claude-sonnet-5 | 592 | 1 184 | 245 020 | 2 364 201 | 91 585 314 |
| `yanis-qa` | claude-sonnet-5 | 121 | 242 | 38 044 | 386 810 | 13 391 841 |
| `relecteur-pedagogique` | claude-sonnet-5 | 156 | 312 | 47 533 | 734 689 | 27 498 840 |
| `nadia-prof` | claude-opus-5 | 233 | 466 | 141 135 | 637 602 | 26 134 011 |
| orchestrateur (fil principal) | claude-opus-5 | 158 | 316 | 217 346 | 2 284 345 | 40 939 763 |
| **Total** | | **2621** | **5 242** | **1 471 691** | **11 219 016** | **455 745 955** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
