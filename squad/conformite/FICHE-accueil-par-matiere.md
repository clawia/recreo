# Fiche de conformite - accueil par matiere (V1 deux etages, V2 cartes groupees, bascule en session)

- **Increment** : l'accueil `/niveaux/{niveau}` presente les jeux du niveau par matiere, en deux vues (V2 par
  defaut, V1) entre lesquelles un bouton de l'accueil bascule. Stories `HS-ACCUEIL-LIBELLES`,
  `HS-ACCUEIL-V2`, `HS-ACCUEIL-V1`, `HS-ACCUEIL-BASCULE`, `HS-ACCUEIL-COMPLETUDE`, `HS-ACCUEIL-MESURE`
  (`docs/backlog/backlog.md`, section "Chantier hors sprint : accueil par matiere").
- **Mecanique** : aucune. Chantier hors sprint, ouvert par `DEC-0117` (statut `Proposee`).
- **Contenu(s)** : aucun paquet nouveau. Le texte lu par l'enfant est neuf : onze libelles courts de matiere
  (nombre au 2026-10-11, derive du catalogue), leurs pictogrammes, le libelle du bouton de bascule. Aucun n'est
  ecrit par `camille-po`.
- **Statut global** : en construction
- **Derniere mise a jour** : 2026-10-11

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| transverse - pas de contenu pedagogique | CP, CE1, CM1 | accueil | aucune | aucune |

## Criteres pedagogiques attendus - camille-po

Ce sont les points ou le produit attend le jugement de `nadia-prof`. Je n'en juge aucun.

- [ ] Chaque libelle court de matiere est ecrit en francais correct et integralement accentue (`DEC-0002`) ;
  `nadia-prof` arbitre le mot et le pictogramme de chacune des matieres servies **avant le premier commit de
  code** (`HS-ACCUEIL-LIBELLES`).
- [ ] Le nom de la matiere est lu en texte sur chaque groupe (V2) et chaque tuile (V1) ; la couleur n'est jamais
  le seul signal (`M11` clause 1).
- [ ] Le libelle du bouton de bascule dit la vue en mots, et n'est pas un second mot de sortie (`BARRE-5`).
- [ ] Un enfant qui ouvre `/niveaux/CP` voit, sans defiler, au moins 10 cartes en V2 (aujourd'hui 5) ou toutes les
  tuiles de matiere en V1 (CP : 8) : critere de produit mesure par `yanis-qa` (`DEC-0117` section 6, point 3).
  `nadia-prof` dit si `DEC-0053` couvre l'accueil.
- [ ] Les 147 jeux (CP 59, CE1 30, CM1 58 au 2026-10-11) restent atteignables dans chaque vue.
- [ ] Le choix de vue vit dans la session HTTP et disparait avec elle ; `nadia-prof` dit si cela contredit
  `S15-5` (`Q-N1`) et a quoi ramene `Les jeux` depuis un jeu en V1 (`Q-N2`).
- [ ] Les garde-fous de l'accueil existants restent verts : `TailleDesTextesEtBoutonsTest` (44 px, 1,25 rem),
  `BARRE-4`, `BARRE-5`, `BARRE-6`, `BARRE-8` de `FICHE-barre-entree-niveau.md`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** :
- **Date** :
- **Audit joue** :

### Ce qui a ete verifie

### Motif

### Reserves

### Mentions de non-couverture opposables
