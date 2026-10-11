# [Sprint s56] Consommation de tokens

- **Fenetre** : 2026-10-09 18:36 a 2026-10-11 12:14 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 193 | 392 | 129 304 | 2 739 663 | 47 023 471 |
| `sofia-sm` | claude-sonnet-5-5 | 192 | 388 | 149 145 | 1 449 211 | 38 696 087 |
| `theo-archi` | claude-opus-5-5 | 381 | 772 | 185 821 | 2 332 430 | 109 522 265 |
| `marc-dev` | claude-sonnet-5-5 | 183 | 370 | 139 458 | 1 433 170 | 29 163 927 |
| `lea-dev` | claude-sonnet-5-5 | 529 | 1 072 | 280 293 | 11 720 419 | 166 529 536 |
| `yanis-qa` | claude-sonnet-5-5 | 333 | 666 | 173 756 | 981 391 | 110 030 826 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 153 | 306 | 53 249 | 699 781 | 30 796 029 |
| `nadia-prof` | claude-opus-5-5 | 480 | 966 | 281 252 | 3 310 484 | 105 452 865 |
| `ines-design` | claude-sonnet-5-5 | 170 | 340 | 100 696 | 663 876 | 15 685 512 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 142 | 376 | 101 447 | 1 199 858 | 17 159 807 |
| **Total** | | **2756** | **5 648** | **1 594 421** | **26 530 283** | **670 060 325** |
