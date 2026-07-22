# DEC-0018 — Externalisation frise + fractions + déclinaisons associer/classer (Français CP)

- **Type** : Produit / Contenu (externalisation + déclinaisons de mécaniques existantes)
- **Date** : 2026-07-22
- **Auteur** : Camille (PO)
- **Sprint** : s21
- **Compétence programme visée** : Français CP — principe alphabétique / correspondance
  majuscule ↔ minuscule (appariement A ↔ a) ; Français CP — genre du nom, masculin / féminin
  (classer « le / la ») ; transverse (technique) pour l'externalisation frise/fractions — aucune
  compétence nouvelle, la couverture reste inchangée.
- **Statut de validation** : **À VALIDER** (implémenté s21 ; Done pédagogique à prononcer par
  l'utilisatrice après démo).

## Contexte / besoin
Deux volets « contenu » de s21, complémentaires du gros incrément DEC-0017 :
1. **Rembourser la dette « générateurs = listes finies en dur »** (roadmap §D) : les deux derniers
   générateurs à contenu combinatoire codé en Java — `FriseChronologiqueGenerator` (événements de la
   frise) et `CompareFractionsGenerator` (pool de fractions comparées) — sont **externalisés en YAML**,
   comme l'ont été les quiz, mots, textes à trous, syllabes, associer et memory.
2. **Décliner la mécanique « Associer / Classer »** (s17) sur le **Français CP** : deux jeux qui
   travaillent des correspondances de base de la lecture/écriture.

## Décisions produit

### 1. Externalisation frise + fractions (dette remboursée — roadmap §D)
`FriseChronologiqueGenerator` et `CompareFractionsGenerator` **reçoivent leur liste par
constructeur**, chargée depuis de nouveaux fichiers YAML (`content/frise/*.yml` et
`content/fractions/*.yml`) via de **nouveaux loaders**, sur le **même patron d'externalisation** que
les contenus déjà migrés (ADR-0009 → ADR-0011). **Aucune nouvelle compétence, aucun changement de
mécanique** : c'est un refactor à comportement constant (frise chronologique CM1 et « Compare les
fractions » CM1 restent `couvert-validé`), qui permet désormais **d'enrichir la frise et le pool de
fractions sans dev**. **Aucun ADR** (cf. note ADR-0017).

### 2. Déclinaisons « Associer / Classer » — Français CP
Deux jeux **externalisés en YAML** sur la mécanique associer/classer existante (s17), sans code :
- **`cp-majuscule-minuscule`** (**appariement**) — apparier chaque lettre **majuscule** à sa
  **minuscule** (A ↔ a). Français CP — principe alphabétique.
- **`cp-masculin-feminin`** (**classer**) — classer des noms selon le déterminant **le / la**
  (masculin / féminin). Français CP — genre du nom.
Correspondance correcte unique définie et vérifiée dans le YAML (correction par encodage canonique
trié côté domaine, coup `SubmitAssociations` — comme `vivant-non-vivant-cp` / `anglais-associer-cm1`).

## Justification pédagogique
- **Majuscule ↔ minuscule** : la correspondance des deux écritures d'une même lettre est un fondamental
  du principe alphabétique au CP ; l'appariement la travaille directement.
- **Le / la (masculin / féminin)** : le tri par déterminant installe la notion de genre du nom, très
  tôt au programme de CP ; la catégorisation (comme vivant / non-vivant) s'y prête bien.
- **Externalisation frise/fractions** : dividende de maintenance (enrichissement des événements et du
  pool de fractions sans dev) et cohérence — plus aucun contenu à liste finie n'est codé en dur.

## Impact
- **Domaine** : **aucun** pour les déclinaisons (réutilisent `SubmitAssociations`) ; pour la frise et
  les fractions, injection de la liste **par constructeur** (générateurs inchangés côté mécanique).
- **Bootstrap** : 2 nouveaux loaders (frise, fractions) + fichiers `content/frise/*.yml`,
  `content/fractions/*.yml` ; 2 packs associer/classer (`cp-majuscule-minuscule`, `cp-masculin-feminin`)
  auto-enregistrés par `AssocPackLoader` ; 2 entrées catalogue.
- **Web** : rien de spécifique (vue plateau d'appariement / catégorisation existante ; vues frise et
  compare-fractions inchangées).
- **Matrice** : 2 nouvelles compétences `en cours (s21)` (Français CP majuscule/minuscule appariement ;
  Français CP le/la classer). Frise et fractions : **couverture inchangée** — note « externalisées
  (technique) ».
- **ADR** : **aucun** (externalisation = patron existant ; déclinaisons = pur contenu — cf. note
  ADR-0017).

## À confirmer en démo
- Non-régression frise chronologique CM1 et « Compare les fractions » CM1 après passage au YAML
  (contenu identique, comportement constant).
- Justesse et niveau des 2 jeux CP : couples majuscule ↔ minuscule sans ambiguïté ; noms le / la
  familiers et non piégeux au CP — contenus à relire/enrichir par l'utilisatrice dans les YAML.
- Ergonomie du plateau d'appariement / catégorisation pour de jeunes lecteurs (jokers DEC-0013 /
  DEC-0017 cohérents).
