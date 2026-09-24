# [Sprint s42] Consommation de tokens

- **Fenetre** : 2026-09-24 15:26 a 2026-09-24 18:37 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 105 | 210 | 37 755 | 610 756 | 8 936 523 |
| `sofia-sm` | claude-sonnet-5 | 199 | 398 | 125 004 | 921 186 | 47 577 054 |
| `theo-archi` | claude-opus-5 | 152 | 304 | 64 898 | 468 506 | 18 262 674 |
| `marc-dev` | claude-sonnet-5 | 345 | 690 | 123 338 | 763 457 | 92 546 499 |
| `lea-dev` | claude-sonnet-5 | 303 | 606 | 131 077 | 993 299 | 47 043 216 |
| `yanis-qa` | claude-sonnet-5 | 213 | 426 | 54 149 | 392 713 | 28 717 104 |
| `nadia-prof` | claude-opus-5 | 292 | 584 | 137 897 | 1 298 002 | 29 797 341 |
| `ines-design` | claude-sonnet-5 | 1 | 2 | 70 | 185 733 | 7 205 |
| orchestrateur (fil principal) | claude-sonnet-5 | 53 | 106 | 62 949 | 103 948 | 15 335 790 |
| **Total** | | **1663** | **3 326** | **737 137** | **5 737 600** | **288 223 406** |

Aucune invocation mesuree sur cette fenetre pour : `relecteur-pedagogique`.
