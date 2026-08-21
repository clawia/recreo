# [Sprint s17] Consommation de tokens

- **Fenetre** : 2026-08-19 16:25 a 2026-08-21 17:41 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 522 | 1 044 | 211 258 | 7 704 354 | 145 757 294 |
| `sofia-sm` | claude-sonnet-5 | 1157 | 2 314 | 317 029 | 13 440 734 | 284 511 531 |
| `theo-archi` | claude-opus-5 | 519 | 1 038 | 371 389 | 7 626 922 | 183 920 152 |
| `marc-dev` | claude-sonnet-5 | 429 | 858 | 253 528 | 3 643 571 | 145 295 075 |
| `lea-dev` | claude-sonnet-5 | 526 | 1 052 | 255 340 | 5 608 696 | 199 578 598 |
| `yanis-qa` | claude-sonnet-5 | 154 | 308 | 58 778 | 364 534 | 16 459 933 |
| `relecteur-pedagogique` | claude-sonnet-5 | 341 | 682 | 117 931 | 1 487 529 | 86 783 176 |
| `nadia-prof` | claude-opus-5 | 616 | 1 232 | 424 140 | 12 754 964 | 232 362 514 |
| `ines-design` | claude-sonnet-5 | 108 | 216 | 104 793 | 1 000 442 | 8 648 534 |
| orchestrateur (fil principal) | claude-opus-5 | 282 | 564 | 691 067 | 2 712 526 | 71 444 581 |
| **Total** | | **4654** | **9 308** | **2 805 253** | **56 344 272** | **1 374 761 388** |
