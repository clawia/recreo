# [Sprint s25] Consommation de tokens

- **Fenetre** : 2026-08-31 16:24 a 2026-08-31 20:11 UTC (sprint en cours, fenetre ouverte jusqu'a maintenant)
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 48 | 96 | 29 537 | 678 272 | 15 216 732 |
| `sofia-sm` | claude-opus-5 | 601 | 1 202 | 379 357 | 2 501 413 | 161 591 141 |
| `marc-dev` | claude-opus-5 | 258 | 516 | 133 960 | 574 283 | 56 614 079 |
| `lea-dev` | claude-opus-5 | 314 | 628 | 170 367 | 1 521 611 | 80 211 478 |
| `nadia-prof` | claude-opus-5 | 10 | 20 | 7 618 | 643 011 | 2 566 007 |
| `ines-design` | claude-opus-5 | 30 | 60 | 31 096 | 880 419 | 12 180 588 |
| orchestrateur (fil principal) | claude-opus-5 | 33 | 66 | 80 646 | 1 288 806 | 11 609 566 |
| **Total** | | **1294** | **2 588** | **832 581** | **8 087 815** | **339 989 591** |

Aucune invocation mesuree sur cette fenetre pour : `theo-archi`, `yanis-qa`, `relecteur-pedagogique`.
