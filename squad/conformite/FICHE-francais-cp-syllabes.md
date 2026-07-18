# Fiche de conformité — Français CP — Les syllabes (bandit manchot)

- **Mécanique** : bandit manchot (`domain/mechanics/banditmanchot`, `BanditManchotMechanic`) —
  **réutilisée sans modification** (preuve du découplage : même mécanique que le calcul mental).
- **Contenu pédagogique** : `SyllabesCpGenerator` (`domain/content/impl`) — dénombrer les
  syllabes orales d'un mot.
- **Entrée catalogue** : `francais-cp` (« Français CP — Les syllabes »).
- **Statut global** : En cours (Done technique ✅) — en attente du Done pédagogique (démo).
- **Dernière mise à jour** : 2026-07-18, par camille-po

## Compétence programme couverte

| Matière | Niveau | Domaine | Compétence (réf. programme) | Statut |
|---|---|---|---|---|
| Français | CP | Langage oral / phonologie | Conscience phonologique — dénombrer les syllabes orales d'un mot | en cours (Done technique) |

Détail du référentiel : `squad/programme/francais/competences-cp.md`.

## Critères pédagogiques

- [x] Le mot présenté et le nombre de syllabes attendu sont cohérents (liste de mots **vérifiée**
      dans `SyllabesCpGenerator`, comptage oral, vérifié par test unitaire déterministe).
- [x] Distracteurs plausibles : nombres voisins de la bonne réponse (≥ 1, sans doublon), jamais
      absurdes.
- [x] Vocabulaire adapté au CP (mots courants : chat, lapin, bateau, chocolat, ordinateur…).
- [x] Adaptation au niveau sans code de mécanique dédié : `BanditManchotMechanic` **inchangée** ;
      seul le `ContentGenerator` change (vérifié par ArchUnit + test paramétré maths/français).
- [ ] **Réserve à valider en démo** : le mot sur un seul rouleau reste lisible et la consigne
      « Combien de syllabes ? » est comprise par l'enfant (à confirmer au playtest).

## Bloc validation utilisateur (Done pédagogique)

- **Démo jouée le** : — (à venir, fin de sprint s02)
- **Validée par l'utilisatrice** : Non (en attente)
- **Date de validation** : —
- **Commentaires** : —

## Bloc playtest enfants

- **Session tenue le** : — (jalon)
- **Niveau(x) testé(s)** : —
- **Observations** : —
- **Suites données** : —
