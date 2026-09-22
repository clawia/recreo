# [Sprint s39] Consommation de tokens

- **Fenetre** : 2026-09-22 12:45 a 2026-09-22 17:31 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 172 | 344 | 72 618 | 971 138 | 14 723 826 |
| `sofia-sm` | claude-sonnet-5 | 89 | 178 | 41 132 | 383 221 | 7 594 015 |
| `theo-archi` | claude-opus-5 | 344 | 688 | 136 757 | 1 247 704 | 30 442 755 |
| `marc-dev` | claude-sonnet-5 | 395 | 790 | 145 781 | 926 207 | 110 182 560 |
| `lea-dev` | claude-sonnet-5 | 469 | 938 | 191 803 | 1 232 482 | 181 544 096 |
| `yanis-qa` | claude-sonnet-5 | 125 | 250 | 27 177 | 266 259 | 10 086 117 |
| `relecteur-pedagogique` | claude-sonnet-5 | 189 | 378 | 36 081 | 452 398 | 24 699 323 |
| `nadia-prof` | claude-sonnet-5 | 250 | 500 | 103 230 | 989 602 | 26 081 216 |
| `ines-design` | claude-sonnet-5 | 68 | 136 | 21 753 | 191 678 | 4 531 060 |
| orchestrateur (fil principal) | claude-sonnet-5 | 123 | 246 | 111 033 | 141 698 | 29 729 874 |
| **Total** | | **2224** | **4 448** | **887 365** | **6 802 387** | **439 614 842** |
