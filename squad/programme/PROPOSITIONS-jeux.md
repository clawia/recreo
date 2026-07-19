# Propositions de jeux pour couvrir le programme (CP + CM1, toutes matières)

> Document de cadrage produit (Camille). Objectif : décliner une **bibliothèque de ~8-10
> mécaniques réutilisables** sur l'ensemble du programme, alimenter `MATRICE-couverture.md` et
> prioriser les prochains sprints. Rappel du principe : **une mécanique = plusieurs jeux** via un
> `ContentGenerator` paramétré ; on n'ajoute une mécanique que quand un contenu ne rentre dans
> aucune mécanique existante.
>
> Légende technique : **htmx** = tour par tour, réalisable en pur htmx/Thymeleaf ; **îlot JS** =
> nécessite un petit composant JS/Canvas (animation continue ou geste), chargé dans le fragment
> du jeu (cf. réserve htmx du plan). Statut : ✅ fait · 🔜 proposé.

## 1. Bandit manchot — choix rapide *(mécanique existante — htmx)*
| Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|
| Calcul mental | Maths | CP, CM1 | additions/soustractions ; tables/multiplications | ✅ |
| Les syllabes | Français | CP | dénombrer les syllabes | ✅ |
| Le bon son / graphème | Français | CP | associer son ↔ graphème | 🔜 |
| Compléments à 10, comparaisons | Maths | CP | sens du nombre | 🔜 |
| Homophones (a/à, et/est, on/ont) | Français | CM1 | orthographe grammaticale | 🔜 |
| Nature d'un mot | Français | CM1 | grammaire | 🔜 |
| Le bon mot (image → mot) | Anglais | CM1 | lexique LV | 🔜 |

## 2. Glisser-déposer — classer / ranger / associer *(nouveau — îlot JS)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Ranger les nombres, quantité ↔ chiffre | Maths | CP | ordonner, dénombrer |
| Ranger fractions/décimaux, fraction ↔ dessin | Maths | CM1 | fractions et décimaux |
| Associer mot ↔ image, majuscule ↔ minuscule | Français | CP | lecture |
| Glisser la bonne terminaison (accords) ; classer nom/verbe/adjectif | Français | CM1 | orthographe, grammaire |
| Classer vivant / non-vivant | Questionner le monde | CP | le vivant |
| Ranger une frise chronologique | Histoire | CM1 | repères temporels |

## 3. Memory — paires *(nouveau — îlot JS léger, flip CSS)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Quantité ↔ chiffre, les doubles | Maths | CP | sens du nombre |
| Mot ↔ image, son ↔ lettre | Français | CP | lecture |
| Fraction ↔ représentation, opération ↔ résultat | Maths | CM1 | fractions, calcul |
| Mot FR ↔ EN | Anglais | CM1 | lexique LV |

## 4. Texte à trous — compléter *(nouveau — htmx, faible risque technique)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Compléter une égalité (3 + _ = 10) | Maths | CP/CM1 | calcul |
| Le mot manquant d'une phrase | Français | CP | compréhension |
| Conjugaison / accords à compléter | Français | CM1 | conjugaison, orthographe |

## 5. Quiz-aventure — parcours de questions *(nouveau — htmx)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Le vivant, les objets, l'espace/temps | Questionner le monde | CP | découverte du monde |
| États de la matière, le vivant | Sciences et techno | CM1 | matière/vivant |
| Repères d'histoire ; repères de géographie | Histoire / Géo | CM1 | se repérer |
| Règles de vie, respect | EMC | CP/CM1 | vivre ensemble |

## 6. Attrape-taupe — sélection sous contrainte *(nouveau — îlot JS/Canvas)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Attraper les multiples de X, les nombres pairs | Maths | CM1 | multiples, parité |
| Attraper les mots avec le son [an] / les verbes | Français | CP/CM1 | phonologie, grammaire |

## 7. Runner — course + réflexe *(nouveau — îlot JS/Canvas)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Calcul mental chronométré (sauter sur la bonne réponse) | Maths | CP/CM1 | automatismes |
| Attraper les mots contenant un son | Français | CP | phonologie |

## 8. Puzzle / assemblage *(nouveau — îlot JS)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Tangram, assembler des formes, reconstituer une figure | Géométrie | CP/CM1 | formes et espace |
| Reconstituer un mot (lettres) / une phrase (mots) | Français | CP | lecture/écriture |
| Puzzle carte de France / régions | Géographie | CM1 | se repérer |

## 9. Traçage tactile *(nouveau — îlot JS/Canvas, prioritaire CP)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Tracer lettres (cursive) et chiffres | Écriture | CP | geste graphique |
| Tracer des formes géométriques | Géométrie | CP | tracés |

## 10. Droite graduée — placer sur une ligne *(nouveau — îlot JS)*
| Jeu | Matière | Niveau | Compétence |
|---|---|---|---|
| Placer un nombre sur la droite numérique | Maths | CP | sens du nombre |
| Placer fractions/décimaux ; conversions de mesures | Maths | CM1 | fractions, grandeurs |

---

## Priorisation proposée (à arbitrer avec l'utilisatrice)
1. **Gains rapides sans nouvelle mécanique** : décliner le **bandit manchot** (homophones CM1,
   tables, le bon son CP, lexique anglais) — pur contenu, très peu de risque.
2. **Prochaines mécaniques à faible risque technique (htmx)** : **texte à trous** puis
   **quiz-aventure** — élargissent massivement la couverture (français CM1, sciences, histoire,
   géo, EMC, questionner le monde) sans îlot JS.
3. **Mécaniques à îlot JS** (drag-déposer, memory, puzzle, droite graduée) : fort rendement
   pédagogique, à cadrer par un ADR « îlot JS/Canvas » (réserve du plan) avant la 1re.
4. **Mécaniques animation-intensive** (attrape-taupe, runner, traçage tactile CP) : les plus
   coûteuses techniquement ; à planifier une fois l'îlot JS maîtrisé (traçage = clé pour l'écriture
   CP).

Chaque jeu retenu suit le rite : story + compétence visée (Camille) → éventuel ADR (Théo) → dev +
tests → CI verte (Done technique) → démo + Done pédagogique → matrice mise à jour.

## Chantier transverse — contenu éditable (DEC-0006)
Indépendant des mécaniques : **externaliser les listes finies** (quiz, anglais, français) dans des
fichiers YAML/JSON éditables, pour que l'utilisatrice enrichisse le programme **sans dev ni
recompilation**. Prérequis à une vraie montée en couverture du contenu ; à cadrer par un ADR
technique (chargement dans un adapter, validation au démarrage) au moment de l'implémenter. En
attendant, les viviers de quiz ont été **doublés** en dur (s07).
