# [Sprint s35] Consommation de tokens

- **Fenetre** : 2026-09-11 12:20 a 2026-09-11 18:32 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 148 | 296 | 66 248 | 613 049 | 21 880 423 |
| `sofia-sm` | claude-sonnet-5 | 656 | 1 312 | 256 237 | 2 063 280 | 170 995 074 |
| `theo-archi` | claude-opus-5 | 164 | 328 | 63 994 | 388 635 | 18 286 465 |
| `marc-dev` | claude-sonnet-5 | 226 | 452 | 99 994 | 688 316 | 50 445 353 |
| `lea-dev` | claude-sonnet-5 | 378 | 756 | 125 742 | 1 011 333 | 108 806 046 |
| `yanis-qa` | claude-sonnet-5 | 114 | 228 | 30 341 | 281 924 | 10 645 477 |
| `relecteur-pedagogique` | claude-sonnet-5 | 158 | 316 | 45 871 | 709 202 | 17 269 467 |
| `nadia-prof` | claude-opus-5 | 284 | 568 | 159 848 | 676 848 | 58 134 583 |
| `ines-design` | claude-sonnet-5 | 149 | 298 | 79 650 | 1 170 704 | 25 116 533 |
| orchestrateur (fil principal) | claude-sonnet-5 | 57 | 114 | 148 149 | 1 088 405 | 9 407 059 |
| **Total** | | **2334** | **4 668** | **1 076 074** | **8 691 696** | **490 986 480** |
