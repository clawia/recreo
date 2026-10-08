# [Sprint s54] Consommation de tokens

- **Fenetre** : 2026-10-07 18:27 a 2026-10-08 11:36 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 411 | 822 | 237 547 | 1 648 872 | 41 212 303 |
| `sofia-sm` | claude-sonnet-5-5 | 130 | 260 | 71 909 | 652 909 | 13 458 069 |
| `theo-archi` | claude-opus-5-5 | 253 | 506 | 87 664 | 619 870 | 33 323 849 |
| `marc-dev` | claude-sonnet-5-5 | 152 | 304 | 74 267 | 530 848 | 11 407 751 |
| `lea-dev` | claude-sonnet-5-5 | 143 | 286 | 61 193 | 573 988 | 9 720 416 |
| `yanis-qa` | claude-sonnet-5-5 | 292 | 584 | 119 275 | 753 700 | 32 630 742 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 82 | 164 | 18 510 | 177 473 | 4 635 193 |
| `nadia-prof` | claude-opus-5-5 | 752 | 1 504 | 406 973 | 2 652 114 | 103 970 934 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 149 | 386 | 163 371 | 453 834 | 28 766 337 |
| **Total** | | **2364** | **4 816** | **1 240 709** | **8 063 608** | **279 125 594** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
