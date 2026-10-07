# [Sprint s53] Consommation de tokens

- **Fenetre** : 2026-10-07 10:18 a 2026-10-07 13:23 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 117 | 234 | 73 949 | 397 743 | 14 198 075 |
| `sofia-sm` | claude-sonnet-5-5 | 96 | 192 | 55 343 | 788 288 | 15 875 867 |
| `theo-archi` | claude-opus-5-5 | 179 | 358 | 73 406 | 645 493 | 20 953 059 |
| `marc-dev` | claude-sonnet-5-5 | 156 | 312 | 112 116 | 506 622 | 16 158 857 |
| `lea-dev` | claude-sonnet-5-5 | 114 | 228 | 66 671 | 440 352 | 8 624 315 |
| `yanis-qa` | claude-sonnet-5-5 | 151 | 302 | 67 849 | 461 865 | 10 925 349 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 208 | 416 | 76 207 | 513 109 | 18 992 567 |
| `nadia-prof` | claude-opus-5-5 | 88 | 176 | 71 822 | 529 824 | 14 272 612 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 58 | 158 | 100 169 | 487 195 | 12 140 466 |
| **Total** | | **1167** | **2 376** | **697 532** | **4 770 491** | **132 141 167** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
