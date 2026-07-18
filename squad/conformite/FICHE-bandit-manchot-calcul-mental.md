# Fiche de conformité — Bandit manchot — calcul mental (CP + CM1)

- **Mécanique** : bandit manchot (`domain/mechanics/banditmanchot`, `BanditManchotMechanic`)
- **Contenu(s) pédagogique(s)** : calcul mental CP (additions/soustractions ≤10), calcul mental
  CM1 (tables de multiplication / multiplications)
- **Statut global** : ✅ Validé pédago (démo du 2026-07-18) — playtest enfants à venir (jalon)
- **Dernière mise à jour** : 2026-07-18, par camille-po

## Compétences programme couvertes

| Matière | Niveau | Domaine | Compétence (réf. programme) | Statut |
|---|---|---|---|---|
| Mathématiques | CP | Nombres et calculs | Calcul mental — additions dans la limite de 10 | en cours |
| Mathématiques | CP | Nombres et calculs | Calcul mental — soustractions dans la limite de 10 | en cours |
| Mathématiques | CM1 | Nombres et calculs | Calcul mental — tables de multiplication (jusqu'à ×10) | en cours |
| Mathématiques | CM1 | Nombres et calculs | Multiplications posées de nombres à 2-3 chiffres | en cours |

Détail des référentiels : `squad/programme/mathematiques/competences-cp.md` et
`squad/programme/mathematiques/competences-cm1.md`.

## Critères pédagogiques

- [ ] Progressivité : les items générés restent strictement dans les bornes de la
      `DifficultyConfig` du niveau (CP ≤10 ; CM1 facteurs 1-10), vérifié par test unitaire
      déterministe.
- [x] Feedback immédiat : chaque réponse (bonne ou mauvaise) produit un retour visible avant le
      round suivant. *(message affiché sous les réponses, jackpot si correct)*
- [x] Absence de piège hors-programme : les distracteurs proposés sont plausibles mais jamais
      construits sur une notion non enseignée au niveau visé.
- [x] Adaptation au niveau : aucune ligne de code de la mécanique ne diffère entre CP et CM1 —
      seule la configuration de contenu change (preuve du découplage mécanique/contenu).
- [x] Accessibilité enfant : cibles tactiles larges, pas de texte long à lire pour comprendre la
      consigne, animation courte (~1 s) et non bloquante.

## Bloc validation utilisateur (Done pédagogique)

- **Démo jouée le** : 2026-07-18
- **Validée par l'utilisatrice** : ✅ Oui
- **Date de validation** : 2026-07-18
- **Commentaires** : Conforme au programme (calcul mental CP/CM1). La présentation « machine à
  sous » a été retravaillée en cours de démo (voir DEC-0001 et le journal de cérémonie
  `2026-07-18-s01-demo-playtest.md`). Jouabilité et lisibilité jugées OK.

## Bloc playtest enfants

- **Session tenue le** : 2026-07-18
- **Niveau(x) testé(s)** : CP et CM1
- **Résultat** : ✅ Validé — jeu jugé jouable et lisible par des enfants (confirmé par
  l'utilisatrice).
- **Observations** : les rouleaux animés (symboles qui défilent vers le bas puis s'arrêtent sur
  le calcul) rendent la mécanique lisible ; réponses cliquables distinctes du calcul.
- **Suites données** : évolutions ergonomiques de la présentation appliquées pendant la démo
  (DEC-0001) ; amélioration de navigation planifiée en s02 (écran de sélection dédié, bandit
  centré, flèche retour — voir backlog).
