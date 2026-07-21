# ADR-0015 — Réglage « toggle » (case à cocher) réglé par l'adulte

- **Statut** : Accepté
- **Date** : 2026-07-21
- **Auteur** : theo-archi
- **Sprint** : s15

## Contexte
L'utilisatrice veut, pour « Décompose le nombre », une **case à cocher « inclure les centaines »**
dans les réglages (décoché → dizaines/unités ; coché → + centaines). C'est un vrai réglage adulte
(contrairement au sens du tri, finalement aléatoire — ADR-0014). Il faut donc un **bloc de réglage
booléen**, réutilisable, sans réintroduire toute la mécanique « choix » (options multiples) retirée
en s13.

## Décision
1. **`ToggleSettingsView(paramName, label, on)`** (record pur, `application.port.in`) : une case à
   cocher. `GameSettingsView` porte `List<ToggleSettingsView> toggles` (0..n ; défaut liste vide ;
   `hasSettings()` en tient compte). Les 3 fabriques existantes passent `List.of()`.
2. **`DecompositionGameFactory`** (bootstrap) : lit le paramètre `centaines`, construit
   `DecompositionGenerator(descriptor, inclureCentaines)`, expose le toggle (pré-coché selon le défaut
   du niveau : CP décoché, CM1 coché) + le réglage commun « nombre de questions ».
3. **Patron HTML de la case à cocher** (le point délicat) : une case décochée n'envoie **rien**. Pour
   distinguer « décochée » de « non réglée », on place un **champ caché `value="false"` AVANT** la
   case `value="true"` (même `name`). Le controller lit `@RequestParam List<String> centaines` :
   décoché → `["false"]`, coché → `["false","true"]` → on retient `contains("true")`. Absent (jeu
   lancé sans ouvrir les réglages) → la fabrique applique **son défaut**.
4. Le controller déclare un `@RequestParam` NOMMÉ par toggle (ici `centaines`) — même contrainte que
   les autres réglages (documentée).

## Règle exécutable ArchUnit
Aucune nouvelle règle. `ToggleSettingsView` est un record pur d'`application.port.in` ;
`DecompositionGameFactory` vit en `bootstrap.config` ; le générateur reste en `domain.content.impl`.
**5 règles vertes** (vérifié). Couverture : `CatalogParametersTest` (défaut par niveau + override du
toggle : CP coché → 3 chiffres, CM1 décoché → 2 chiffres).

## Conséquences
- **Positif** : primitive de réglage booléen réutilisable (futurs toggles) ; case à cocher robuste
  (le champ caché résout le « décoché = absent »).
- **Coût** : un `@RequestParam` nommé par toggle (manuel) ; binding en `List<String>` pour le motif
  caché+case.

## Alternatives considérées
- **`<select> Non/Oui`** : toujours une valeur envoyée (pas de champ caché), plus simple côté binding,
  mais l'utilisatrice a demandé une **case à cocher**. Retenu : la case + champ caché.
- **Réintroduire le « choix » générique** (ADR-0014 v1, retiré) : surdimensionné pour un booléen.
