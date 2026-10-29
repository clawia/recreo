# [Sprint s57] Consommation de tokens

- **Fenetre** : 2026-10-14 13:45 a 2026-10-29 16:08 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 5704 | 11 448 | 6 555 310 | 31 186 103 | 798 261 112 |
| `sofia-sm` | claude-sonnet-5-5 | 4908 | 9 886 | 3 485 550 | 39 527 619 | 696 058 818 |
| `theo-archi` | claude-opus-5-5 | 3903 | 7 834 | 3 249 381 | 24 586 989 | 515 860 500 |
| `marc-dev` | claude-sonnet-5-5 | 3086 | 6 206 | 3 579 186 | 22 544 953 | 514 465 284 |
| `lea-dev` | claude-sonnet-5-5 | 1719 | 3 452 | 830 924 | 17 540 307 | 335 960 055 |
| `yanis-qa` | claude-sonnet-5-5 | 1538 | 3 088 | 795 962 | 14 634 787 | 200 936 741 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 1106 | 2 212 | 758 293 | 4 634 759 | 247 172 828 |
| `nadia-prof` | claude-opus-5-5 | 6736 | 13 544 | 5 882 440 | 39 681 517 | 900 865 943 |
| `ines-design` | claude-sonnet-5-5 | 2025 | 4 072 | 1 159 867 | 13 247 118 | 338 417 780 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 3516 | 9 374 | 5 214 362 | 23 999 225 | 872 295 700 |
| **Total** | | **34241** | **71 116** | **31 511 275** | **231 583 377** | **5 420 294 761** |
