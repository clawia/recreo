# [Sprint s30] Consommation de tokens

- **Fenetre** : 2026-09-06 15:45 a 2026-09-07 08:03 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 238 | 476 | 109 382 | 981 674 | 35 676 912 |
| `sofia-sm` | claude-sonnet-5 | 153 | 306 | 61 527 | 1 761 399 | 31 282 627 |
| `theo-archi` | claude-opus-5 | 116 | 232 | 40 047 | 228 327 | 7 029 513 |
| `marc-dev` | claude-sonnet-5 | 221 | 442 | 141 179 | 772 396 | 57 447 143 |
| `lea-dev` | claude-sonnet-5 | 695 | 1 390 | 296 688 | 2 168 007 | 181 189 799 |
| `yanis-qa` | claude-sonnet-5 | 209 | 418 | 63 272 | 1 151 956 | 39 562 272 |
| `relecteur-pedagogique` | claude-sonnet-5 | 163 | 326 | 53 590 | 503 511 | 25 814 307 |
| `nadia-prof` | claude-opus-5 | 383 | 766 | 313 011 | 2 092 382 | 62 955 989 |
| orchestrateur (fil principal) | claude-opus-5 | 81 | 162 | 223 992 | 1 857 286 | 19 028 855 |
| **Total** | | **2259** | **4 518** | **1 302 688** | **11 516 938** | **459 987 417** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
