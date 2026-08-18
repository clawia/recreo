# [Sprint s15] Consommation de tokens

- **Fenetre** : 2026-08-17 18:33 a 2026-08-18 15:20 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 97 | 194 | 41 828 | 396 569 | 10 857 010 |
| `sofia-sm` | claude-sonnet-5 | 703 | 21 346 | 218 735 | 2 298 603 | 57 736 316 |
| `theo-archi` | claude-opus-5 | 160 | 320 | 147 058 | 5 184 978 | 49 482 013 |
| `marc-dev` | claude-sonnet-5 | 668 | 1 336 | 320 783 | 6 504 724 | 303 272 286 |
| `nadia-prof` | claude-opus-5 | 162 | 324 | 85 048 | 2 961 758 | 74 257 996 |
| `ines-design` | claude-sonnet-5 | 266 | 532 | 135 028 | 779 493 | 28 806 585 |
| orchestrateur (fil principal) | claude-opus-5 | 134 | 257 | 244 439 | 1 643 536 | 48 906 340 |
| **Total** | | **2190** | **24 309** | **1 192 919** | **19 769 661** | **573 318 546** |

Aucune invocation mesuree sur cette fenetre pour : `lea-dev`, `yanis-qa`, `relecteur-pedagogique`.
