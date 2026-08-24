# [Sprint s19] Consommation de tokens

- **Fenetre** : 2026-08-24 12:00 a 2026-08-24 13:23 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 138 | 276 | 55 407 | 1 296 956 | 26 322 438 |
| `sofia-sm` | claude-sonnet-5 | 74 | 148 | 25 297 | 996 372 | 16 478 442 |
| `marc-dev` | claude-sonnet-5 | 83 | 166 | 48 929 | 763 303 | 29 861 766 |
| `relecteur-pedagogique` | claude-sonnet-5 | 214 | 428 | 57 951 | 708 936 | 50 313 982 |
| `nadia-prof` | claude-opus-5 | 78 | 156 | 73 254 | 1 006 392 | 31 463 991 |
| orchestrateur (fil principal) | claude-opus-5 | 25 | 50 | 54 732 | 1 180 821 | 7 048 490 |
| **Total** | | **612** | **1 224** | **315 570** | **5 952 780** | **161 489 109** |

Aucune invocation mesuree sur cette fenetre pour : `theo-archi`, `lea-dev`, `yanis-qa`, `ines-design`.
