# Plan — Renommer les fichiers de config selon `niveau-jeu.yml`

Statut : ✅ **EXÉCUTÉ en s11** (ADR-0012). 13 fichiers renommés en `niveau-jeu.yml`, 3 chemins de
calcul mental mis à jour, CI verte, zéro régression.

## Objectif
Uniformiser le nommage des fichiers de contenu en **`<niveau>-<jeu>.yml`** (niveau d'abord, en
minuscules : `cp`, `cm1`), pour un rangement lisible et cohérent (aujourd'hui c'est un mélange :
`sciences-cm1.yml`, `le-bon-son.yml`, `calcul-mental-cp.yml`).

## Mapping proposé (13 fichiers)

**`content/` (calcul mental — chargé par chemin EXPLICITE dans `BeansConfig`)**
| Actuel | Nouveau |
|---|---|
| `calcul-mental-cp.yml` | `cp-calcul-mental.yml` |
| `calcul-mental-cm1.yml` | `cm1-calcul-mental.yml` |
| `tables-multiplication-cm1.yml` | `cm1-tables-multiplication.yml` |

**`content/quiz/` (scanné par glob — pas de chemin en dur)**
| Actuel | Nouveau |
|---|---|
| `questionner-le-monde-cp.yml` | `cp-questionner-le-monde.yml` |
| `sciences-cm1.yml` | `cm1-sciences.yml` |
| `histoire-cm1.yml` | `cm1-histoire.yml` |
| `geographie-cm1.yml` | `cm1-geographie.yml` |
| `emc-cp.yml` | `cp-emc.yml` |
| `emc-cm1.yml` | `cm1-emc.yml` |
| `vrai-faux-sciences-cm1.yml` | `cm1-vrai-faux-sciences.yml` |

**`content/mots/` (scanné par glob)**
| Actuel | Nouveau |
|---|---|
| `le-bon-son.yml` | `cp-le-bon-son.yml` |
| `homophones.yml` | `cm1-homophones.yml` |
| `premiere-lettre.yml` | `cp-premiere-lettre.yml` |
| `nature-mot.yml` | `cm1-nature-mot.yml` |
| `anglais-lexique.yml` | `cm1-anglais-lexique.yml` |

## Impacts (analyse)
- **Fonctionnel : AUCUN.** Les `entryId`, `gameKey`, titres et matières restent inchangés → catalogue,
  navigation à deux axes et gameplay strictement identiques. C'est un renommage de fichiers, pas de contenu.
- **Chargement quiz/mots** : les loaders scannent par glob (`classpath*:content/quiz/*.yml`,
  `…/mots/*.yml`) → **aucun changement de code**. Seul l'**ordre déterministe** (tri par nom de fichier)
  change ; les tests de navigation utilisent `containsString` (insensibles à l'ordre) → sans effet.
- **Calcul mental** : chargé par **chemin explicite** dans `BeansConfig.gameCatalog`
  (`loader.load("content/calcul-mental-cp.yml")`, …). → **3 lignes à mettre à jour** vers les nouveaux noms.
- **Tests** : aucun ne référence un nom de fichier (vérifié — ils utilisent les `entryId` et des
  `ByteArrayResource` en mémoire). Rien à changer côté tests.
- **Historique Git** : utiliser `git mv` pour préserver l'historique de chaque fichier.

## Étapes d'exécution (sprint s11 proposé)
1. `git mv` des 13 fichiers selon le mapping.
2. Mettre à jour les **3 `loader.load(...)`** de `BeansConfig` (calcul mental + tables).
3. `source /Users/torched/tools/env.sh && mvn -B verify` (doit rester vert : 129 tests, ArchUnit 5/5) + fat jar.
4. Smoke : catalogue complet + un jeu par famille jouable.
5. **ADR** court (Théo) actant la convention de nommage `niveau-jeu.yml` + note dans
   `squad/programme/CONTENU-editable.md` (les nouveaux fichiers créés par l'utilisatrice suivront la convention).
6. Commits attribués (`[LEA]`/`[MARC]` renommage + wiring, `[THEO]` ADR, `[SOF]` cérémonies).

## Risque
**Très faible** (renommage + 3 lignes). Réversible. À faire de préférence **avant** d'ajouter beaucoup
de nouveaux fichiers, pour que la convention s'applique dès le départ.
