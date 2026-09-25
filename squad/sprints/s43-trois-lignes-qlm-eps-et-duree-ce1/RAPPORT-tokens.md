# [Sprint s43] Consommation de tokens

- **Fenetre** : 2026-09-25 13:35 a 2026-09-25 16:55 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 120 | 240 | 27 482 | 404 057 | 7 537 114 |
| `sofia-sm` | claude-sonnet-5 | 180 | 360 | 117 954 | 3 119 980 | 26 885 894 |
| `theo-archi` | claude-opus-5 | 119 | 238 | 30 602 | 618 315 | 10 589 189 |
| `marc-dev` | claude-sonnet-5 | 270 | 540 | 83 917 | 2 045 492 | 50 928 781 |
| `lea-dev` | claude-sonnet-5 | 417 | 834 | 99 087 | 702 430 | 98 919 849 |
| `yanis-qa` | claude-sonnet-5 | 162 | 324 | 39 679 | 477 611 | 16 784 152 |
| `nadia-prof` | claude-opus-5 | 209 | 418 | 120 781 | 995 487 | 24 777 840 |
| orchestrateur (fil principal) | claude-sonnet-5 | 38 | 76 | 35 313 | 61 045 | 17 491 931 |
| **Total** | | **1515** | **3 030** | **554 815** | **8 424 417** | **253 914 750** |

Aucune invocation mesuree sur cette fenetre pour : `relecteur-pedagogique`, `ines-design`.
