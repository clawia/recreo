# [Sprint s24] Consommation de tokens

- **Fenetre** : 2026-08-30 20:32 a 2026-08-31 09:34 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 138 | 276 | 116 178 | 848 708 | 15 536 749 |
| `sofia-sm` | claude-opus-5 | 381 | 762 | 196 514 | 851 469 | 55 870 089 |
| `theo-archi` | claude-opus-5 | 131 | 262 | 108 003 | 1 932 238 | 56 052 130 |
| `marc-dev` | claude-opus-5 | 373 | 746 | 249 255 | 2 115 558 | 144 312 735 |
| `lea-dev` | claude-opus-5 | 173 | 346 | 111 888 | 399 585 | 20 303 024 |
| `nadia-prof` | claude-opus-5 | 145 | 290 | 105 148 | 812 475 | 16 509 587 |
| orchestrateur (fil principal) | claude-opus-5 | 69 | 138 | 180 698 | 955 411 | 10 676 649 |
| **Total** | | **1410** | **2 820** | **1 067 684** | **7 915 444** | **319 260 963** |

Aucune invocation mesuree sur cette fenetre pour : `yanis-qa`, `relecteur-pedagogique`, `ines-design`.
