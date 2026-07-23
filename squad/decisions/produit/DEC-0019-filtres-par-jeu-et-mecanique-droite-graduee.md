# DEC-0019 — Filtres « Par jeu » + mécanique « Droite graduée » (8e)

- **Type** : Produit / Ergonomie (filtres) + Nouvelle mécanique (droite graduée)
- **Date** : 2026-07-22
- **Auteur** : Camille (PO), en appui de l'ADR-0018 (Théo).
- **Sprint** : s22
- **Compétence programme visée** : Maths CP — situer / placer un nombre sur une droite graduée
  (0 → 20) ; Maths CM1 — situer / placer un nombre sur une droite graduée (0 → 100) ; transverse
  (ergonomie / navigation) pour les filtres « Par jeu » — **aucune compétence nouvelle**, la
  couverture reste inchangée.
- **Statut de validation** : **VALIDÉ (utilisatrice, 2026-07-23)** — Done pédagogique prononcé par
  l'utilisatrice après démo (validation groupée s21 + s22 + s23).

## Contexte / besoin
Deux évolutions produit avancent ensemble en s22 :
1. **Filtres « Par jeu »** (backlog **IDEE-3**, demandée par l'utilisatrice le 2026-07-22) — l'écran
   `/jeux` liste **tous** les jeux à plat ; la liste est longue. On veut la **filtrer** par **niveau**
   (CP / CM1) et par **matière** grâce à des **boutons**, filtres **combinables** et
   **réinitialisables**.
2. **8e mécanique « Droite graduée »** (roadmap §B) — première façon pour l'enfant de **situer / placer
   un nombre** sur une échelle, plutôt que de choisir une réponse, ordonner, apparier ou reconstituer.

## Décisions produit

### 1. Filtres « Par jeu » (IDEE-3)
Sur l'écran **« Par jeu »** (`/jeux`, liste plate de tous les jeux), une **barre de boutons de
filtre** :
- **niveau** : Tous / CP / CM1 ;
- **matière** : Toutes / Mathématiques / Français / … (matières couvertes).

Les filtres sont **combinables** (ex. CM1 **et** Mathématiques) et **réinitialisables** (« Tous » /
« Toutes » revient à la liste complète) ; l'état actif est visible ; un sous-ensemble vide affiche un
message clair. Le **filtrage est serveur, par query params** (`/jeux?niveau=&matiere=`) — pur
**read-model / vue** sur le catalogue enrichi (ADR-0005), **aucun changement de domaine**. Les axes
existants (par niveau, par jeu, par matière) restent disponibles et inchangés. **Aucun ADR** (cf.
note ADR-0018).

### 2. Mécanique « Droite graduée » (8e) — 1re déclinaison nombres entiers
L'enfant **place un nombre sur une droite graduée 0 → N** en **cliquant la bonne graduation**. Côté
domaine c'est un **choix** : la droite graduée **réutilise le coup `SpinAndPick`** du bandit manchot
(comme le puzzle a réutilisé `SubmitArrangement` et le memory `SubmitAssociations` — cf. ADR-0018),
**sans nouveau coup**. Interaction propre : **îlot JS de vue `droitegraduee`** (rendu de la droite,
clic de graduation, tick grisé). **Jokers automatiques** (DEC-0013 / DEC-0017) : un **tick faux =
1 cœur perdu + tick grisé** (retente) ; à 0 cœur, un tick faux termine la session — comportement natif
de `SpinAndPick`. 2 jeux **combinatoires** (bornes câblées dans `BeansConfig`, générateur
`DroiteGradueeGenerator`) :
- **`droite-graduee-cp`** — Maths CP, droite **0 → 20** ;
- **`droite-graduee-cm1`** — Maths CM1, droite **0 → 100**.

## Justification pédagogique
- **Droite graduée** : situer / placer un nombre sur une droite est un fondamental du sens du nombre
  (repérage, ordre de grandeur, distances) au CP (0 → 20) comme au CM1 (0 → 100) ; le geste de
  **placer** complète les entrées « choisir / ordonner / apparier » déjà offertes. Cette 1re
  déclinaison porte sur les **nombres entiers** ; les **fractions sur la droite** sont la suite
  naturelle (roadmap).
- **Filtres « Par jeu »** : à mesure que le catalogue grandit, réduire vite la liste à « ce qui
  m'intéresse » (mon niveau, ma matière) sans changer d'écran est un vrai gain d'ergonomie — pas une
  nouvelle compétence.

## Impact
- **Domaine** : **aucun nouveau coup** — la droite graduée réutilise `SpinAndPick` (ADR-0018) ; paquet
  isolé `mechanics/droitegraduee`. Filtres « Par jeu » : **aucun impact domaine** (read-model / vue).
- **Bootstrap** : `DroiteGradueeGenerator` **combinatoire** câblé dans `BeansConfig` (bornes 0→20 /
  0→100 — pas de YAML, contenu combinatoire) + 2 entrées catalogue (`droite-graduee-cp`,
  `droite-graduee-cm1`).
- **Web** : îlot JS de vue `droitegraduee` (droite + clic de graduation + tick grisé) ; barre de
  filtres sur `/jeux` + gestion des query params `?niveau=&matiere=` (filtrage serveur).
- **Matrice** : 2 nouvelles compétences `en cours (s22)` (Maths CP droite graduée 0→20 ; Maths CM1
  droite graduée 0→100). Filtres « Par jeu » = ergonomie, **sans nouvelle ligne de couverture**.
- **ADR** : **ADR-0018** (droite graduée). Filtres « Par jeu » **sans ADR** (read-model / vue —
  cf. note ADR-0018).

## À confirmer en démo
- Justesse et niveau des 2 jeux droite graduée : lisibilité de la droite et des graduations (0→20 au
  CP, 0→100 au CM1) ; pas d'ambiguïté sur la graduation attendue ; ergonomie du clic (souris /
  tactile) pour de jeunes enfants.
- **Jokers** : tick faux consomme bien 1 cœur et grise le tick ; à 0 cœur, un tick faux termine la
  session (cohérent DEC-0013 / DEC-0017).
- Filtres « Par jeu » : boutons niveau / matière **combinables** et **réinitialisables** ; état actif
  visible ; sous-ensemble vide = message clair ; recoupement cohérent avec les axes par niveau / par
  matière ; libellés au format contextuel.
