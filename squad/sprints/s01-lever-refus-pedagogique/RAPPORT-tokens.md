# [Sprint s01] Consommation de tokens

- **Fenetre** : 2026-08-05 11:03 a 2026-08-05 13:45 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 68 | 406 | 48 862 | 549 529 | 3 489 263 |
| `sofia-sm` | claude-sonnet-5 | 138 | 612 | 62 048 | 743 269 | 13 625 287 |
| `theo-archi` | claude-opus-5 | 175 | 4 402 | 64 243 | 385 915 | 18 892 435 |
| `marc-dev` | claude-sonnet-5 | 149 | 700 | 58 077 | 500 086 | 17 743 907 |
| `lea-dev` | claude-sonnet-5 | 249 | 674 | 116 870 | 836 005 | 48 304 064 |
| `yanis-qa` | claude-sonnet-5 | 258 | 16 347 | 74 193 | 524 241 | 32 816 129 |
| `relecteur-pedagogique` | claude-sonnet-5 | 128 | 2 332 | 22 211 | 505 962 | 10 495 277 |
| `nadia-prof` | claude-opus-5 | 106 | 11 234 | 60 362 | 452 545 | 9 184 135 |
| `ines-design` | claude-sonnet-5 | 66 | 132 | 21 645 | 243 765 | 3 750 764 |
| orchestrateur (fil principal) | claude-opus-5 | 107 | 619 | 138 601 | 244 036 | 36 000 994 |
| **Total** | | **1444** | **37 458** | **667 112** | **4 985 353** | **194 302 255** |
