# [Sprint s14] Consommation de tokens

- **Fenetre** : 2026-08-17 11:24 a 2026-08-17 17:34 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 55 | 20 402 | 33 982 | 287 416 | 4 326 148 |
| `sofia-sm` | claude-sonnet-5 | 469 | 184 186 | 172 852 | 1 826 675 | 34 720 920 |
| `theo-archi` | claude-opus-5 | 213 | 56 976 | 178 196 | 4 034 175 | 50 286 479 |
| `marc-dev` | claude-sonnet-5 | 473 | 55 522 | 269 332 | 6 441 060 | 217 080 896 |
| `lea-dev` | claude-sonnet-5 | 197 | 20 686 | 62 383 | 397 541 | 25 178 534 |
| `yanis-qa` | claude-sonnet-5 | 204 | 408 | 45 069 | 428 385 | 25 670 873 |
| `relecteur-pedagogique` | claude-sonnet-5 | 171 | 20 634 | 44 864 | 579 059 | 29 588 674 |
| `nadia-prof` | claude-opus-5 | 106 | 212 | 65 002 | 1 433 401 | 33 334 063 |
| orchestrateur (fil principal) | claude-opus-5 | 61 | 114 | 199 551 | 298 828 | 11 894 426 |
| **Total** | | **1949** | **359 140** | **1 071 231** | **15 726 540** | **432 081 013** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
