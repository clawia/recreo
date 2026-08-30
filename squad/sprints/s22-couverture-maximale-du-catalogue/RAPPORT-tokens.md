# [Sprint s22] Consommation de tokens

- **Fenetre** : 2026-08-28 15:07 a 2026-08-30 08:40 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 453 | 906 | 209 306 | 1 925 462 | 58 626 823 |
| `sofia-sm` | claude-sonnet-5 | 1036 | 2 072 | 488 565 | 2 991 026 | 136 904 157 |
| `theo-archi` | claude-opus-5 | 274 | 548 | 154 250 | 1 284 590 | 48 580 577 |
| `marc-dev` | claude-sonnet-5 | 444 | 888 | 213 473 | 1 985 004 | 99 934 630 |
| `lea-dev` | claude-sonnet-5 | 334 | 668 | 131 736 | 855 996 | 47 954 057 |
| `yanis-qa` | claude-sonnet-5 | 143 | 286 | 50 056 | 477 484 | 19 354 221 |
| `relecteur-pedagogique` | claude-sonnet-5 | 130 | 260 | 41 060 | 588 197 | 23 868 910 |
| `nadia-prof` | claude-opus-5 | 302 | 604 | 204 279 | 1 934 619 | 37 484 685 |
| orchestrateur (fil principal) | claude-opus-5 | 199 | 398 | 396 010 | 3 447 473 | 47 975 278 |
| **Total** | | **3315** | **6 630** | **1 888 735** | **15 489 851** | **520 683 338** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
