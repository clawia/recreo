# [Sprint s33] Consommation de tokens

- **Fenetre** : 2026-09-09 16:20 a 2026-09-10 09:45 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 53 | 106 | 36 740 | 381 374 | 5 334 551 |
| `sofia-sm` | claude-sonnet-5 | 449 | 898 | 212 183 | 1 672 817 | 63 136 609 |
| `theo-archi` | claude-opus-5 | 315 | 630 | 145 357 | 768 692 | 38 088 807 |
| `marc-dev` | claude-sonnet-5 | 512 | 1 024 | 170 024 | 1 011 982 | 83 511 710 |
| `lea-dev` | claude-sonnet-5 | 341 | 682 | 134 051 | 988 884 | 72 803 326 |
| `yanis-qa` | claude-sonnet-5 | 241 | 482 | 56 470 | 515 669 | 32 632 570 |
| `relecteur-pedagogique` | claude-sonnet-5 | 170 | 340 | 40 467 | 550 333 | 34 547 088 |
| `nadia-prof` | claude-opus-5 | 497 | 994 | 343 633 | 1 891 417 | 79 906 364 |
| orchestrateur (fil principal) | claude-opus-5 | 95 | 190 | 169 776 | 1 151 965 | 25 985 338 |
| **Total** | | **2673** | **5 346** | **1 308 701** | **8 933 133** | **435 946 363** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
