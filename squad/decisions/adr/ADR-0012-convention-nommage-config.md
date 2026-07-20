# ADR-0012 — Convention de nommage des fichiers de contenu : `niveau-jeu.yml`

- **Statut** : Accepté
- **Date** : 2026-07-20
- **Auteur** : theo-archi
- **Sprint** : s11

## Contexte
Les fichiers de contenu externalisés (`content/**/*.yml`) avaient un nommage hétérogène
(`sciences-cm1.yml`, `le-bon-son.yml`, `calcul-mental-cp.yml`). Demande de l'utilisatrice :
uniformiser en **`<niveau>-<jeu>.yml`** (niveau en minuscules d'abord). Cf. `PLAN-renommage-config.md`.

## Décision
Tous les fichiers de contenu suivent **`<niveau>-<jeu>.yml`** (`cp-…`, `cm1-…`), dans leur dossier
de famille (`content/`, `content/quiz/`, `content/mots/`, `content/textes-trous/`,
`content/syllabes/`). 13 fichiers renommés (`git mv`, historique préservé).

- Les loaders **quiz/mots/textes-trous/syllabes** scannent par **glob** (`classpath*:…/*.yml`) →
  le renommage est transparent (seul l'ordre déterministe, trié par nom de fichier, change).
- Le **calcul mental** est chargé par **chemin explicite** → 3 `loader.load("content/…")` mis à jour.
- **Aucun changement fonctionnel** : `entryId`/`gameKey`/titres inchangés → catalogue, navigation,
  gameplay identiques. Aucun test ne référençait un nom de fichier (les tests utilisent les `entryId`).

## Règle exécutable ArchUnit
Sans objet (organisation de ressources). Garde-fou : la CI (`mvn verify`) échoue si un chemin
explicite de calcul mental n'est pas à jour, et la validation au démarrage échoue si un fichier
attendu manque.

## Conséquences
- **Positif** : rangement lisible et cohérent ; la convention s'applique aussi aux **nouveaux**
  fichiers créés par l'utilisatrice (documenté dans `CONTENU-editable.md`).
- **Coût** : renommage unique, sans impact fonctionnel.

## Alternatives considérées
- **`<jeu>-<niveau>.yml`** (statu quo partiel) : ordre moins naturel pour regrouper par niveau. Écarté.
- **Ne pas renommer** : hétérogénéité persistante. Écarté (demande utilisatrice).
