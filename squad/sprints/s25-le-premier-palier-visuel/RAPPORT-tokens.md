# [Sprint s25] Consommation de tokens

- **Fenetre** : 2026-08-31 16:24 a 2026-09-01 07:45 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-opus-5 | 99 | 198 | 60 588 | 1 459 829 | 33 712 872 |
| `sofia-sm` | claude-opus-5 | 800 | 1 600 | 482 544 | 8 192 994 | 246 409 094 |
| `theo-archi` | claude-opus-5 | 78 | 156 | 37 335 | 642 922 | 23 533 310 |
| `marc-dev` | claude-opus-5 | 357 | 714 | 220 598 | 2 384 051 | 98 541 756 |
| `lea-dev` | claude-opus-5 | 425 | 850 | 242 958 | 4 458 019 | 130 776 650 |
| `yanis-qa` | claude-opus-5 | 206 | 412 | 105 242 | 639 498 | 25 760 217 |
| `relecteur-pedagogique` | claude-opus-5 | 212 | 424 | 93 741 | 714 722 | 23 925 228 |
| `nadia-prof` | claude-opus-5 | 192 | 384 | 167 717 | 3 509 471 | 77 461 742 |
| `ines-design` | claude-opus-5 | 30 | 60 | 31 096 | 880 419 | 12 180 588 |
| orchestrateur (fil principal) | claude-opus-5 | 118 | 236 | 274 741 | 3 005 534 | 50 741 590 |
| **Total** | | **2517** | **5 034** | **1 716 560** | **25 887 459** | **723 043 047** |
