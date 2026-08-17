# [Sprint s13] Consommation de tokens

- **Fenetre** : 2026-08-15 14:18 a 2026-08-17 07:16 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

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
