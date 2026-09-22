# [Sprint s37] Consommation de tokens

- **Fenetre** : 2026-09-21 20:05 a 2026-09-22 06:35 UTC
- **Genere par** : `outils/rapport-tokens.py`

> Les quatre compteurs sont volontairement separes. Les additionner donnerait un
> classement faux : le cache lu depasse couramment l'output de deux ordres de
> grandeur. Ce sont des compteurs de tokens, pas des euros.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 75 | 150 | 22 266 | 415 374 | 14 114 032 |
| `sofia-sm` | claude-sonnet-5 | 130 | 260 | 42 094 | 696 302 | 17 846 549 |
| `theo-archi` | claude-opus-5 | 86 | 172 | 67 820 | 1 462 130 | 21 883 902 |
| `yanis-qa` | claude-sonnet-5 | 124 | 248 | 34 580 | 347 465 | 12 194 256 |
| `nadia-prof` | claude-opus-5 | 188 | 376 | 89 791 | 586 212 | 14 337 451 |
| orchestrateur (fil principal) | claude-sonnet-5 | 26 | 52 | 28 664 | 609 732 | 6 562 403 |
| **Total** | | **629** | **1 258** | **285 215** | **4 117 215** | **86 938 593** |

Aucune invocation mesuree sur cette fenetre pour : `marc-dev`, `lea-dev`, `relecteur-pedagogique`, `ines-design`.
