# [Sprint s21] Consommation de tokens

- **Fenetre** : 2026-08-27 13:45 a 2026-08-28 10:30 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

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
