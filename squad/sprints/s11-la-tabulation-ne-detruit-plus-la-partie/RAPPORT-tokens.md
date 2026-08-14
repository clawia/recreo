# [Sprint s11] Consommation de tokens

- **Fenetre** : 2026-08-14 09:14 a 2026-08-14 14:28 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 89 | 178 | 36 006 | 218 916 | 5 351 095 |
| `sofia-sm` | claude-sonnet-5 | 451 | 44 705 | 117 879 | 1 086 360 | 33 474 120 |
| `theo-archi` | claude-opus-5 | 121 | 242 | 57 457 | 400 380 | 13 747 793 |
| `marc-dev` | claude-sonnet-5 | 208 | 2 390 | 139 027 | 1 006 635 | 52 751 379 |
| `lea-dev` | claude-sonnet-5 | 128 | 256 | 23 441 | 217 800 | 9 111 458 |
| `yanis-qa` | claude-sonnet-5 | 246 | 492 | 78 246 | 576 285 | 42 912 802 |
| `relecteur-pedagogique` | claude-sonnet-5 | 113 | 226 | 36 190 | 416 280 | 15 867 977 |
| `nadia-prof` | claude-opus-5 | 321 | 1 599 | 125 409 | 692 428 | 28 018 933 |
| `ines-design` | claude-sonnet-5 | 252 | 504 | 73 254 | 755 868 | 29 431 116 |
| orchestrateur (fil principal) | claude-opus-5 | 63 | 118 | 136 827 | 660 148 | 10 277 382 |
| **Total** | | **1992** | **50 710** | **823 736** | **6 031 100** | **240 944 055** |
