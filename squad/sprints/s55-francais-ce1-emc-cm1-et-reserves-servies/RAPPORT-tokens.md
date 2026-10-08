# [Sprint s55] Consommation de tokens

- **Fenetre** : 2026-10-08 12:43 a 2026-10-08 20:21 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 562 | 1 124 | 322 177 | 1 985 196 | 74 964 471 |
| `sofia-sm` | claude-sonnet-5-5 | 323 | 646 | 262 369 | 1 821 573 | 64 735 757 |
| `theo-archi` | claude-opus-5-5 | 370 | 740 | 157 853 | 1 407 223 | 46 960 937 |
| `marc-dev` | claude-sonnet-5-5 | 331 | 662 | 121 403 | 1 792 484 | 22 462 525 |
| `lea-dev` | claude-sonnet-5-5 | 455 | 910 | 246 519 | 1 851 299 | 94 133 341 |
| `yanis-qa` | claude-sonnet-5-5 | 319 | 638 | 173 360 | 1 102 807 | 50 315 452 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 203 | 406 | 64 485 | 811 965 | 32 954 605 |
| `nadia-prof` | claude-opus-5-5 | 868 | 1 740 | 425 922 | 3 195 566 | 107 527 315 |
| `ines-design` | claude-sonnet-5-5 | 93 | 186 | 73 669 | 385 829 | 10 771 950 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 182 | 464 | 260 065 | 459 732 | 31 105 327 |
| **Total** | | **3706** | **7 516** | **2 107 822** | **14 813 674** | **535 931 680** |
