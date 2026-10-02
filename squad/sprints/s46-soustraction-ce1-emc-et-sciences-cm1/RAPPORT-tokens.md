# [Sprint s46] Consommation de tokens

- **Fenetre** : 2026-10-01 14:30 a 2026-10-02 08:31 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 254 | 508 | 149 134 | 1 737 336 | 36 843 268 |
| `sofia-sm` | claude-sonnet-5 | 432 | 864 | 199 418 | 2 095 092 | 65 440 570 |
| `theo-archi` | claude-opus-5-5 | 148 | 296 | 62 120 | 428 890 | 11 129 954 |
| `marc-dev` | claude-sonnet-5 | 179 | 358 | 106 576 | 640 991 | 25 119 182 |
| `lea-dev` | claude-sonnet-5 | 463 | 926 | 122 373 | 1 918 863 | 120 751 110 |
| `yanis-qa` | claude-sonnet-5 | 177 | 354 | 48 172 | 519 392 | 25 798 800 |
| `relecteur-pedagogique` | claude-sonnet-5 | 102 | 204 | 18 136 | 445 142 | 13 723 100 |
| `nadia-prof` | claude-opus-5-5 | 607 | 1 214 | 292 549 | 2 345 138 | 57 210 029 |
| orchestrateur (fil principal) | claude-sonnet-5 | 397 | 794 | 359 171 | 1 808 055 | 106 403 514 |
| **Total** | | **2759** | **5 518** | **1 357 649** | **11 938 899** | **462 419 527** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
