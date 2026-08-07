# [Sprint s05] Consommation de tokens

- **Fenetre** : 2026-08-06 11:55 a 2026-08-07 08:41 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 51 | 25 296 | 9 517 | 555 337 | 4 148 323 |
| `sofia-sm` | claude-sonnet-5 | 168 | 336 | 27 766 | 347 281 | 7 827 352 |
| `theo-archi` | claude-opus-5 | 825 | 71 296 | 327 221 | 2 205 226 | 92 616 854 |
| `marc-dev` | claude-sonnet-5 | 226 | 4 490 | 35 116 | 1 277 823 | 26 185 786 |
| `lea-dev` | claude-sonnet-5 | 723 | 32 676 | 181 291 | 1 703 853 | 99 303 620 |
| `yanis-qa` | claude-sonnet-5 | 352 | 21 626 | 108 394 | 1 386 689 | 41 238 057 |
| `relecteur-pedagogique` | claude-sonnet-5 | 141 | 11 001 | 55 323 | 739 608 | 21 506 935 |
| `nadia-prof` | claude-opus-5 | 224 | 3 414 | 103 948 | 684 219 | 22 267 996 |
| `ines-design` | claude-sonnet-5 | 223 | 7 700 | 50 211 | 722 011 | 23 122 934 |
| orchestrateur (fil principal) | claude-opus-5 | 91 | 163 | 231 482 | 1 178 203 | 28 699 509 |
| **Total** | | **3024** | **177 998** | **1 130 269** | **10 800 250** | **366 917 366** |
