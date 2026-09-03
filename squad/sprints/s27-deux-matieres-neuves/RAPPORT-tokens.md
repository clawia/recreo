# [Sprint s27] Consommation de tokens

- **Fenetre** : 2026-09-02 20:08 a 2026-09-03 17:13 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 283 | 566 | 105 150 | 2 620 940 | 117 256 406 |
| `sofia-sm` | claude-sonnet-5 | 476 | 952 | 143 850 | 4 169 563 | 83 463 587 |
| `theo-archi` | claude-opus-5 | 244 | 488 | 178 619 | 2 494 439 | 58 064 946 |
| `marc-dev` | claude-sonnet-5 | 212 | 424 | 111 864 | 1 130 736 | 64 071 066 |
| `lea-dev` | claude-sonnet-5 | 215 | 430 | 83 439 | 1 115 783 | 58 974 296 |
| `yanis-qa` | claude-sonnet-5 | 161 | 322 | 57 832 | 397 375 | 20 773 477 |
| `relecteur-pedagogique` | claude-sonnet-5 | 251 | 502 | 71 249 | 489 717 | 39 132 330 |
| `nadia-prof` | claude-opus-5 | 182 | 364 | 106 526 | 1 796 400 | 49 257 518 |
| `ines-design` | claude-sonnet-5 | 141 | 282 | 38 089 | 847 953 | 39 013 539 |
| orchestrateur (fil principal) | claude-opus-5 | 104 | 208 | 249 651 | 1 760 899 | 41 937 023 |
| **Total** | | **2269** | **4 538** | **1 146 269** | **16 823 805** | **571 944 188** |
