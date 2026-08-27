# [Sprint s20] Consommation de tokens

- **Fenetre** : 2026-08-25 15:00 a 2026-08-27 09:15 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 338 | 676 | 160 708 | 2 177 012 | 112 401 228 |
| `sofia-sm` | claude-sonnet-5 | 450 | 900 | 155 532 | 3 152 404 | 99 064 209 |
| `theo-archi` | claude-opus-5 | 144 | 288 | 75 558 | 1 876 882 | 19 014 605 |
| `marc-dev` | claude-sonnet-5 | 329 | 658 | 146 693 | 754 583 | 72 806 699 |
| `lea-dev` | claude-sonnet-5 | 332 | 664 | 203 994 | 2 760 726 | 89 346 905 |
| `yanis-qa` | claude-sonnet-5 | 109 | 218 | 36 481 | 298 065 | 10 031 897 |
| `relecteur-pedagogique` | claude-sonnet-5 | 137 | 274 | 40 569 | 579 554 | 22 086 709 |
| `nadia-prof` | claude-opus-5 | 372 | 744 | 227 411 | 3 371 070 | 68 479 442 |
| `ines-design` | claude-sonnet-5 | 392 | 784 | 172 802 | 6 378 907 | 147 975 583 |
| orchestrateur (fil principal) | claude-opus-5 | 174 | 348 | 245 503 | 7 998 022 | 65 590 622 |
| **Total** | | **2777** | **5 554** | **1 465 251** | **29 347 225** | **706 797 899** |
