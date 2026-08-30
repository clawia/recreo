# [Sprint s23] Consommation de tokens

- **Fenetre** : 2026-08-30 12:15 a 2026-08-30 18:14 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 310 | 620 | 116 956 | 1 603 795 | 58 918 766 |
| `sofia-sm` | claude-sonnet-5 | 885 | 1 770 | 306 665 | 2 825 049 | 93 121 132 |
| `theo-archi` | claude-opus-5 | 110 | 220 | 70 079 | 337 395 | 12 186 852 |
| `marc-dev` | claude-sonnet-5 | 594 | 1 188 | 340 849 | 4 074 638 | 230 594 487 |
| `lea-dev` | claude-sonnet-5 | 170 | 340 | 92 381 | 568 058 | 28 133 309 |
| `yanis-qa` | claude-sonnet-5 | 188 | 376 | 70 634 | 397 458 | 22 450 386 |
| `relecteur-pedagogique` | claude-sonnet-5 | 113 | 226 | 37 631 | 460 165 | 12 474 803 |
| `nadia-prof` | claude-opus-5 | 435 | 870 | 327 431 | 1 223 203 | 42 667 507 |
| orchestrateur (fil principal) | claude-opus-5 | 80 | 160 | 222 201 | 268 320 | 40 121 289 |
| **Total** | | **2885** | **5 770** | **1 584 827** | **11 758 081** | **540 668 531** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
