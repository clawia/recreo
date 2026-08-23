# [Sprint s18] Consommation de tokens

- **Fenetre** : 2026-08-22 19:35 a 2026-08-23 18:37 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 212 | 424 | 99 147 | 2 155 636 | 69 198 156 |
| `sofia-sm` | claude-sonnet-5 | 204 | 408 | 74 518 | 1 829 662 | 39 675 099 |
| `theo-archi` | claude-opus-5 | 298 | 596 | 161 881 | 1 751 020 | 54 664 857 |
| `marc-dev` | claude-sonnet-5 | 131 | 262 | 38 252 | 291 196 | 13 564 763 |
| `lea-dev` | claude-sonnet-5 | 238 | 476 | 99 340 | 843 495 | 37 551 154 |
| `nadia-prof` | claude-opus-5 | 442 | 884 | 243 650 | 5 584 497 | 95 443 048 |
| `ines-design` | claude-sonnet-5 | 75 | 150 | 48 642 | 835 160 | 7 862 181 |
| orchestrateur (fil principal) | claude-opus-5 | 95 | 190 | 243 034 | 1 573 991 | 66 678 088 |
| **Total** | | **1695** | **3 390** | **1 008 464** | **14 864 657** | **384 637 346** |

Aucune invocation mesuree sur cette fenetre pour : `yanis-qa`, `relecteur-pedagogique`.
