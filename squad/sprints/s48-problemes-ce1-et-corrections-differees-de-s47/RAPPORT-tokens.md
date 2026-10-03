# [Sprint s48] Consommation de tokens

- **Fenetre** : 2026-10-03 07:12 a 2026-10-03 12:21 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 102 | 204 | 45 044 | 841 310 | 6 927 173 |
| `sofia-sm` | claude-sonnet-5 | 191 | 382 | 84 738 | 2 325 005 | 30 698 309 |
| `theo-archi` | claude-opus-5-5 | 143 | 286 | 47 912 | 467 001 | 21 202 529 |
| `marc-dev` | claude-sonnet-5 | 240 | 480 | 126 698 | 1 814 765 | 60 988 082 |
| `lea-dev` | claude-sonnet-5 | 95 | 190 | 20 957 | 400 033 | 9 243 957 |
| `yanis-qa` | claude-sonnet-5 | 83 | 166 | 21 378 | 302 241 | 4 995 729 |
| `relecteur-pedagogique` | claude-sonnet-5 | 72 | 144 | 14 848 | 387 660 | 6 945 458 |
| `nadia-prof` | claude-opus-5-5 | 305 | 610 | 196 480 | 1 228 072 | 47 930 458 |
| orchestrateur (fil principal) | claude-sonnet-5 | 119 | 238 | 140 476 | 248 513 | 17 368 271 |
| **Total** | | **1350** | **2 700** | **698 531** | **8 014 600** | **206 299 966** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
