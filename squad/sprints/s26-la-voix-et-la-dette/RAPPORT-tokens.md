# [Sprint s26] Consommation de tokens

- **Fenetre** : 2026-09-01 12:32 a 2026-09-01 19:49 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 240 | 480 | 139 925 | 1 080 421 | 41 018 423 |
| `sofia-sm` | claude-sonnet-5 | 484 | 968 | 168 346 | 5 227 475 | 145 104 027 |
| `theo-archi` | claude-opus-5 | 244 | 488 | 170 537 | 5 058 088 | 98 030 692 |
| `marc-dev` | claude-sonnet-5 | 739 | 1 478 | 322 504 | 4 752 822 | 314 056 560 |
| `lea-dev` | claude-sonnet-5 | 699 | 1 398 | 338 947 | 3 332 625 | 147 113 458 |
| `yanis-qa` | claude-sonnet-5 | 89 | 178 | 50 764 | 351 936 | 9 001 826 |
| `relecteur-pedagogique` | claude-sonnet-5 | 172 | 344 | 58 139 | 440 128 | 19 734 234 |
| `nadia-prof` | claude-opus-5 | 155 | 310 | 151 847 | 1 446 832 | 33 646 956 |
| orchestrateur (fil principal) | claude-opus-5 | 109 | 218 | 258 233 | 797 741 | 18 184 644 |
| **Total** | | **2931** | **5 862** | **1 659 242** | **22 488 068** | **825 890 820** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.
