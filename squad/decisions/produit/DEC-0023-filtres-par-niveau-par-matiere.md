# DEC-0023 — Filtres tags sur les axes « par niveau » et « par matière »

- **Type** : Produit / Ergonomie (navigation) — **read-model / vue uniquement**
- **Date** : 2026-07-25
- **Auteur** : Camille (PO)
- **Sprint** : hors sprint numéroté (raffinement ergonomique demandé par l'utilisatrice le 2026-07-25)
- **Compétence programme visée** : **aucune** — transverse (ergonomie / navigation). La couverture
  pédagogique reste strictement inchangée.
- **Statut de validation** : ⏳ À VALIDER (utilisatrice, après démo) — Done pédagogique jamais
  prononcé par un agent.

## Contexte / besoin
Les filtres à boutons (chips) existaient déjà sur l'axe **« Par jeu »** (`/jeux`, IDEE-3 / DEC-0019).
L'utilisatrice demande la **symétrie** sur les deux autres axes de navigation :
- en entrant **par niveau** (`/niveau/{niveau}`), pouvoir **filtrer par matière** ;
- en entrant **par matière** (`/matiere/{matiere}`), pouvoir **filtrer par niveau**.

## Décision
Ajouter, sur ces deux écrans, une **barre de chips de filtre** identique à celle de « Par jeu » :
- **`/niveau/{niveau}?matiere=`** — chips des **matières présentes à ce niveau** (ordre canonique du
  catalogue), + un unique **« Toutes »** qui annule le filtre ; chip **bascule** (reclic sur la
  matière active → déselection).
- **`/matiere/{matiere}?niveau=`** — chips des **niveaux présents dans cette matière**, + un unique
  **« Tous »** ; même comportement bascule.
- Filtrage **serveur par query param** (comme `/jeux`), **read-model / vue** — aucun changement de
  domaine, **pas d'ADR**. La barre n'apparaît que s'il y a **au moins 2 options** (sinon inutile).
  Réutilise le CSS existant `.filters` / `.filter-chip`.

## Justification
Cohérence d'ergonomie entre les 3 axes ; réduit une liste longue sans quitter l'écran ; ne propose
que des options **pertinentes** (matières/niveaux réellement présents dans la tranche courante).

## Impact
- **Web** : `GameController.jeuxDuNiveau` (+ param `matiere`) et `jeuxDeLaMatiere` (+ param `niveau`) ;
  templates `niveau.html` / `matiere.html` (barre de chips + message « Aucun jeu pour ce filtre »).
- **Domaine / application** : **aucun** (réutilise `CatalogueQuery.byNiveau` / `byMatiere` /
  `niveaux` / `matieres`).
- **Tests** : `ParNiveauEtMatiereFiltersWebTest` (filtrage effectif + chips présents, dans les deux sens).
- **Matrice / couverture** : inchangée (ergonomie, aucune compétence nouvelle).
- **File d'attente** : ergonomie (technique) — **n'ajoute pas d'incrément de contenu pédagogique** ;
  la file de contenu reste à 2 (s24 + s25), sous le seuil de gel.

## À confirmer en démo
- Chips lisibles et tactiles ; bascule + « Tous/Toutes » cohérents ; retour à l'axe d'origine correct.
