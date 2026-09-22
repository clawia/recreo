# [Sprint s38] Consommation de tokens

- **Fenetre** : 2026-09-22 06:56 a 2026-09-22 12:15 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 146 | 292 | 74 654 | 1 011 866 | 14 176 722 |
| `sofia-sm` | claude-sonnet-5 | 156 | 312 | 69 328 | 867 301 | 13 790 968 |
| `theo-archi` | claude-opus-5 | 180 | 360 | 91 822 | 1 014 645 | 18 354 193 |
| `marc-dev` | claude-sonnet-5 | 278 | 556 | 91 542 | 1 107 155 | 50 998 967 |
| `lea-dev` | claude-sonnet-5 | 296 | 592 | 116 721 | 2 811 353 | 71 291 016 |
| `yanis-qa` | claude-sonnet-5 | 98 | 196 | 25 972 | 234 395 | 7 323 489 |
| `relecteur-pedagogique` | claude-sonnet-5 | 72 | 144 | 20 586 | 316 131 | 6 316 430 |
| `nadia-prof` | claude-opus-5  claude-sonnet-5 | 283 | 566 | 173 366 | 1 267 458 | 29 830 626 |
| orchestrateur (fil principal) | claude-sonnet-5 | 89 | 178 | 115 472 | 135 542 | 12 264 484 |
| **Total** | | **1598** | **3 196** | **779 463** | **8 765 846** | **224 346 895** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
