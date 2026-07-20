# DEC-0006 — Externaliser le contenu pédagogique hors du code

- **Type** : Produit / Dette & roadmap (avec volet technique → ADR à venir)
- **Date** : 2026-07-19
- **Auteur** : Camille (PO), sur demande de l'utilisatrice.
- **Sprint** : noté en s07 (décision de cadrage ; **non implémenté** — backlog)
- **Compétence programme visée** : transverse (toutes matières à contenu en liste finie).
- **Statut de validation** : ACTÉE comme orientation — implémentation à planifier (backlog).

## Contexte
Aujourd'hui, deux familles de générateurs (`domain/content/impl/`) :
- **Combinatoire / à la volée** : calcul mental, compléments à 10, comparaison, égalité — le
  contenu est *calculé* au runtime (vivier infini). **Rien à externaliser** ici.
- **Liste finie en dur** : anglais (50 mots), tous les quiz (histoire, sciences, géo, questionner
  le monde), le français (syllabes, homophones, nature du mot, première lettre, conjugaison,
  accords, mot manquant, phrase à trou). Ces listes sont **écrites dans le `.java`**.

Enrichir/corriger ces listes exige aujourd'hui un dev + une recompilation. L'utilisatrice
(non-développeuse, autorité pédagogique unique) doit pouvoir **ajouter/relire du contenu sans
passer par le code**.

## Décision
1. **Externaliser les listes finies** dans des fichiers de contenu éditables (YAML ou JSON),
   chargés au démarrage — un fichier par jeu (ex. `content/quiz/histoire-cm1.yml`). Le principe
   était déjà esquissé dans le plan d'amorçage (§C.3, configs `calcul-mental-*.yml`) mais le
   contenu a d'abord été codé en dur pour valider vite les mécaniques.
2. **Séparer le format de contenu du code de mécanique** : un générateur « liste finie » unique,
   piloté par le fichier chargé, plutôt qu'une sous-classe Java par jeu.
3. **En attendant** (fait en s07) : **doubler les viviers de quiz** en dur pour donner de la marge
   pédagogique immédiate (cf. § ci-dessous).

## Volet technique (→ ADR à rédiger par Théo au moment de l'implémenter)
- Où charger (`bootstrap`, port `out` de contenu) sans violer la Clean Architecture — le `domain`
  ne doit pas lire de fichier ; le chargement/parse vit dans un **adapter**, le `domain` ne voit
  qu'un `ContentGenerator`/vivier déjà construit.
- Validation au démarrage (question ↔ 1 bonne réponse + distracteurs distincts) : reprendre les
  invariants aujourd'hui garantis par `QuizGeneratorTest` sous forme de validation de fichier.
- Rechargement à chaud (option, confort d'édition) — hors périmètre initial.

## Fait dès s07 (mesure d'attente, pas l'externalisation)
Viviers de quiz **doublés** (contenu en dur, vérifié, tests synchronisés) :

| Jeu | Avant | Après |
|---|---|---|
| Questionner le monde CP | 7 | 14 |
| Sciences CM1 | 6 | 12 |
| Histoire CM1 | 5 | 10 |
| Géographie CM1 | 6 | 12 |

## Conséquences
- **Positif** : l'utilisatrice devient autonome sur le contenu ; moins de code répétitif
  (une sous-classe par jeu disparaît) ; enrichissement du programme découplé des livraisons de code.
- **À surveiller** : un mauvais fichier ne doit pas casser le démarrage silencieusement → validation
  stricte + message clair. Migration des listes actuelles vers les fichiers à faire proprement.
- Cette DEC ne bloque aucun sprint ; elle entre au **backlog** et sera priorisée avec les prochaines
  mécaniques.

## Suivi de réalisation
- **s09 (ADR-0009)** : **famille quiz externalisée** — les 7 jeux quiz vivent dans
  `content/quiz/*.yml`, éditables sans dev, chargés + validés au démarrage (`QuizPackLoader`).
  Guide : `squad/programme/CONTENU-editable.md`.
- **À suivre** : mots→réponse du bandit manchot (le bon son, anglais, syllabes, première lettre,
  homophones, nature du mot) puis textes à trous — schémas propres à chaque famille.
