# [Sprint s29] Consommation de tokens

- **Fenetre** : 2026-09-05 19:35 a 2026-09-06 13:22 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 265 | 530 | 130 798 | 913 946 | 38 733 600 |
| `sofia-sm` | claude-sonnet-5 | 75 | 150 | 37 747 | 570 783 | 7 264 825 |
| `theo-archi` | claude-opus-5 | 181 | 362 | 122 848 | 515 452 | 27 240 633 |
| `marc-dev` | claude-sonnet-5 | 658 | 1 316 | 347 954 | 2 209 004 | 94 644 326 |
| `lea-dev` | claude-sonnet-5 | 985 | 1 970 | 389 922 | 3 215 924 | 161 953 735 |
| `yanis-qa` | claude-sonnet-5 | 179 | 358 | 58 700 | 703 132 | 24 808 389 |
| `relecteur-pedagogique` | claude-sonnet-5 | 276 | 552 | 98 331 | 944 361 | 62 184 738 |
| `nadia-prof` | claude-opus-5 | 728 | 1 456 | 456 173 | 2 860 912 | 90 660 550 |
| `ines-design` | claude-sonnet-5 | 82 | 164 | 39 171 | 383 126 | 9 247 985 |
| orchestrateur (fil principal) | claude-opus-5 | 161 | 322 | 295 137 | 2 459 725 | 84 358 691 |
| **Total** | | **3590** | **7 180** | **1 976 781** | **14 776 365** | **601 097 472** |
