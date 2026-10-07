# [Sprint s52] Consommation de tokens

- **Fenetre** : 2026-10-06 18:51 a 2026-10-07 08:01 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 324 | 648 | 148 583 | 1 012 267 | 36 634 283 |
| `sofia-sm` | claude-sonnet-5-5 | 178 | 356 | 96 676 | 883 570 | 34 790 969 |
| `theo-archi` | claude-opus-5-5 | 231 | 462 | 74 603 | 1 155 933 | 18 827 400 |
| `marc-dev` | claude-sonnet-5-5 | 297 | 598 | 167 722 | 1 335 955 | 45 995 030 |
| `lea-dev` | claude-sonnet-5-5 | 394 | 798 | 248 003 | 2 584 996 | 161 128 314 |
| `yanis-qa` | claude-sonnet-5-5 | 117 | 234 | 43 524 | 372 991 | 9 450 789 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 170 | 340 | 58 685 | 627 094 | 33 711 039 |
| `nadia-prof` | claude-opus-5-5 | 484 | 982 | 238 890 | 1 983 402 | 80 112 166 |
| `ines-design` | claude-sonnet-5-5 | 145 | 290 | 90 252 | 431 786 | 15 987 132 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 189 | 512 | 234 792 | 1 908 555 | 78 294 201 |
| **Total** | | **2529** | **5 220** | **1 401 730** | **12 296 549** | **514 931 323** |
