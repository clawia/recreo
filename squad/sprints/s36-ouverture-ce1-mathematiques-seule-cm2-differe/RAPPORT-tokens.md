# [Sprint s36] Consommation de tokens

- **Fenetre** : 2026-09-21 14:40 a 2026-09-21 18:36 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 39 | 78 | 21 264 | 433 178 | 7 360 885 |
| `sofia-sm` | claude-sonnet-5 | 139 | 278 | 29 740 | 672 841 | 15 757 906 |
| `theo-archi` | claude-opus-5 | 220 | 440 | 118 709 | 551 496 | 28 110 255 |
| `lea-dev` | claude-sonnet-5 | 67 | 134 | 12 763 | 163 889 | 3 361 220 |
| `yanis-qa` | claude-sonnet-5 | 71 | 142 | 24 520 | 117 337 | 10 323 485 |
| `nadia-prof` | claude-opus-5 | 191 | 382 | 79 832 | 742 360 | 21 813 083 |
| orchestrateur (fil principal) | claude-sonnet-5 | 31 | 62 | 34 024 | 471 019 | 6 278 546 |
| **Total** | | **758** | **1 516** | **320 852** | **3 152 120** | **93 005 380** |

Aucune invocation mesuree sur cette fenetre pour : `marc-dev`, `relecteur-pedagogique`, `ines-design`.
