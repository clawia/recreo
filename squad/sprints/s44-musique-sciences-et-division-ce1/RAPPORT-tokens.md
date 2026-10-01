# [Sprint s44] Consommation de tokens

- **Fenetre** : 2026-09-30 16:48 a 2026-10-01 05:48 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 149 | 298 | 57 574 | 777 844 | 12 384 252 |
| `sofia-sm` | claude-sonnet-5 | 251 | 502 | 88 650 | 646 148 | 46 767 731 |
| `theo-archi` | claude-opus-5-5 | 67 | 134 | 20 056 | 157 911 | 3 872 532 |
| `marc-dev` | claude-sonnet-5 | 259 | 518 | 79 825 | 994 097 | 55 392 057 |
| `lea-dev` | claude-sonnet-5 | 271 | 542 | 85 273 | 736 471 | 62 007 576 |
| `yanis-qa` | claude-sonnet-5 | 113 | 226 | 26 996 | 300 299 | 8 426 107 |
| `relecteur-pedagogique` | claude-sonnet-5 | 64 | 128 | 12 180 | 272 445 | 4 561 516 |
| `nadia-prof` | claude-opus-5-5 | 191 | 382 | 107 472 | 801 339 | 22 460 822 |
| orchestrateur (fil principal) | claude-sonnet-5 | 68 | 136 | 112 487 | 550 099 | 11 285 786 |
| **Total** | | **1433** | **2 866** | **590 513** | **5 236 653** | **227 158 379** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
