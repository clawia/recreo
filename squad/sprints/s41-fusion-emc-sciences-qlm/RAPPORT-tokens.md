# [Sprint s41] Consommation de tokens

- **Fenetre** : 2026-09-24 07:15 a 2026-09-24 10:45 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 151 | 302 | 52 667 | 836 500 | 9 791 408 |
| `sofia-sm` | claude-sonnet-5 | 99 | 198 | 25 642 | 1 354 493 | 28 072 193 |
| `theo-archi` | claude-opus-5 | 171 | 342 | 64 814 | 934 010 | 17 432 167 |
| `marc-dev` | claude-sonnet-5 | 365 | 730 | 93 285 | 1 373 115 | 69 838 783 |
| `lea-dev` | claude-sonnet-5 | 322 | 644 | 63 643 | 1 779 821 | 50 935 610 |
| `yanis-qa` | claude-sonnet-5 | 183 | 366 | 38 579 | 331 488 | 20 880 643 |
| `nadia-prof` | claude-opus-5 | 335 | 670 | 145 636 | 1 635 347 | 34 641 523 |
| orchestrateur (fil principal) | claude-sonnet-5 | 58 | 116 | 88 721 | 239 363 | 8 500 841 |
| **Total** | | **1684** | **3 368** | **572 987** | **8 484 137** | **240 093 168** |

Aucune invocation mesuree sur cette fenetre pour : `relecteur-pedagogique`, `ines-design`.
