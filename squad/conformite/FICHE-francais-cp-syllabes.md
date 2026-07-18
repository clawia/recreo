# Fiche de conformité — Français CP — Les syllabes (bandit manchot)

- **Mécanique** : bandit manchot (`domain/mechanics/banditmanchot`, `BanditManchotMechanic`) —
  **réutilisée sans modification** (preuve du découplage : même mécanique que le calcul mental).
- **Contenu pédagogique** : `SyllabesCpGenerator` (`domain/content/impl`) — dénombrer les
  syllabes orales d'un mot.
- **Entrée catalogue** : `francais-cp` (« Français CP — Les syllabes »).
- **Statut global** : ✅ Validé pédago (démo du 2026-07-18) — playtest enfants à venir (jalon)
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
- [x] **Lisibilité confirmée en démo** après 2 retouches d'ergonomie : le **mot s'affiche en
      entier** (rouleau à largeur adaptative) et la **consigne « Combien de syllabes ? » est sortie
      de la machine** (placée entre le casier et les réponses).

## Bloc validation utilisateur (Done pédagogique)

- **Démo jouée le** : 2026-07-18
- **Validée par l'utilisatrice** : ✅ Oui
- **Date de validation** : 2026-07-18
- **Commentaires** : Validé après 2 retouches (mot entier + consigne hors machine). Conforme à la
  compétence de conscience syllabique CP.

## Bloc playtest enfants

- **Session tenue le** : — (jalon)
- **Niveau(x) testé(s)** : —
- **Observations** : —
- **Suites données** : —
