# [Sprint s49] Consommation de tokens

- **Fenetre** : 2026-10-03 12:59 a 2026-10-03 17:20 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 202 | 404 | 95 010 | 945 743 | 20 087 775 |
| `sofia-sm` | claude-sonnet-5 | 96 | 192 | 64 251 | 1 096 157 | 14 751 812 |
| `theo-archi` | claude-opus-5-5 | 132 | 264 | 57 507 | 433 049 | 18 556 218 |
| `marc-dev` | claude-sonnet-5 | 317 | 634 | 169 523 | 2 180 756 | 62 033 656 |
| `lea-dev` | claude-sonnet-5 | 144 | 288 | 46 837 | 755 358 | 16 783 712 |
| `yanis-qa` | claude-sonnet-5 | 77 | 154 | 19 947 | 279 424 | 4 228 224 |
| `relecteur-pedagogique` | claude-sonnet-5 | 47 | 94 | 6 288 | 225 144 | 2 661 391 |
| `nadia-prof` | claude-opus-5-5 | 201 | 402 | 104 943 | 757 152 | 16 824 202 |
| orchestrateur (fil principal) | claude-sonnet-5 | 69 | 138 | 75 919 | 108 247 | 18 795 296 |
| **Total** | | **1285** | **2 570** | **640 225** | **6 781 030** | **174 722 286** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
